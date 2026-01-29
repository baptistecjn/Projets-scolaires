import { Component, OnInit, ChangeDetectorRef } from '@angular/core';
import { CommonModule } from '@angular/common';
import { HttpClient } from '@angular/common/http';
import { ActivatedRoute, Router } from '@angular/router';
import { TokenStorageService } from '../services/token-storage.service';
import { FormsModule } from '@angular/forms';

@Component({
  selector: 'app-association-detail',
  standalone: true,
  imports: [CommonModule, FormsModule],
  templateUrl: './association-detail.html',
  styleUrls: ['./association-detail.css']
})
export class AssociationDetail implements OnInit {

  association: any = null;
  id: string | null = null;
  isMember: boolean = false;
  minutes: any[] = [];

  newMinuteContent: string = '';
  isEditing: boolean = false;
  editName: string = '';

  selectedRole: string = 'Membre';
  availableRoles: string[] = ['Membre', 'Trésorier', 'Secrétaire', 'Président'];
  selectedVoters: number[] = [];

  constructor(
    private http: HttpClient,
    private route: ActivatedRoute,
    private token: TokenStorageService,
    private router: Router,
    private cd: ChangeDetectorRef
  ) { }

  ngOnInit(): void {
    this.route.paramMap.subscribe((params) => {
      const idParam = params.get('id');
      if (idParam) {
        this.id = idParam;
        this.association = null;
        this.minutes = [];
        this.isMember = false;
        this.loadAssociationData(this.id);
      }
    });
  }


  loadAssociationData(id: string): void {
    this.http.get<any>(`http://localhost:3000/associations/${id}`).subscribe({
      next: (data) => {
        this.association = data;
        this.loadMembers(id);
        this.loadMinutes(id);
        this.cd.detectChanges();
      },
      error: (err) => {
        console.error(err);
        this.router.navigate(['/associations']);
      }
    });
  }

  loadMembers(id: string): void {
    this.http.get<any[]>(`http://localhost:3000/associations/${id}/members`).subscribe({
      next: (membersData) => {
        if (this.association) {
          this.association.members = membersData;
          this.checkIfMember();
          this.cd.detectChanges();
        }
      }
    });
  }

  loadMinutes(id: string): void {
    this.http.get<any[]>(`http://localhost:3000/associations/${id}/minutes`).subscribe({
      next: (data) => {
        this.minutes = data ? data.sort((a: any, b: any) => b.id - a.id) : [];
        this.cd.detectChanges();
      },
      error: () => {
        this.minutes = [];
      }
    });
  }


  checkIfMember(): void {
    const myId = this.token.getUserId();
    if (this.association && this.association.members) {
      this.isMember = this.association.members.some((m: any) => m.id == myId || m.userId == myId || (m.user && m.user.id == myId));
    }
  }

  canPostMinute(): boolean {
    const myId = this.token.getUserId();
    if (!this.association || !this.association.members) return false;
    const currentUser = this.association.members.find((m: any) => m.id == myId || m.userId == myId || (m.user && m.user.id == myId));
    if (!currentUser) return false;

    const authorizedRoles = ['Trésorier', 'Secrétaire', 'Président'];
    return authorizedRoles.includes(currentUser.role);
  }

  isPresident(): boolean {
    const myId = this.token.getUserId();
    if (!this.association || !this.association.members) return false;
    const currentUser = this.association.members.find((m: any) => m.id == myId || m.userId == myId || (m.user && m.user.id == myId));
    return currentUser?.role === 'Président';
  }


  startEdit(currentName: string): void {
    this.editName = currentName;
    this.isEditing = true;
  }

  cancelEdit(): void {
    this.isEditing = false;
  }

  saveAsso(): void {
    if (!this.editName.trim() || !this.id) return;
    this.http.put(`http://localhost:3000/associations/${this.id}`, { name: this.editName })
      .subscribe({
        next: () => {
          this.isEditing = false;
          this.loadAssociationData(this.id!);
        },
        error: (err) => {
          console.error(err);
          alert("Erreur lors de la modification");
        }
      });
  }


  joinAssociation(): void {
    if (!this.id) return;
    const userId = this.token.getUserId();
    if (!userId) {
      alert(`Erreur : Vous devez être connecté pour rejoindre.`);
      return;
    }
    const body = {
      userId: userId,
      role: this.selectedRole
    };
    this.http.post(`http://localhost:3000/associations/${this.id}/members`, body).subscribe({
      next: () => {
        alert(`Félicitations ! Vous avez rejoint en tant que ${this.selectedRole}.`);
        this.loadMembers(this.id!);
      },
      error: (err) => {
        if (err.status === 400 || err.status === 409) {
          alert('Impossible de rejoindre (Déjà membre ?)');
          this.isMember = true;
        } else {
          alert("Erreur lors de l'adhésion.");
        }
      }
    });
  }

  leaveAssociation(): void {
    if (!this.association || !this.id) return;

    // Sécurité président
    if (this.isPresident()) {
      alert("Le Président ne peut pas quitter l'association.\nVous devez d'abord nommer un successeur ou supprimer l'association.");
      return;
    }

    if (!confirm("Voulez-vous vraiment quitter cette association ?")) {
      return;
    }

    const userId = this.token.getUserId();
    const assoId = this.association.id;

    this.http.delete(`http://localhost:3000/associations/${assoId}/members/${userId}`).subscribe({
      next: () => {
        alert("Vous avez quitté l'association.");
        this.isMember = false;
        this.selectedRole = 'Membre';
        this.loadMembers(String(assoId));
      },
      error: (err) => {
        console.error(err);
        alert("Erreur lors de la tentative de départ.");
      }
    });
  }

  createMinute(): void {
    if (!this.newMinuteContent.trim() || !this.association) return;

    if (!this.canPostMinute()) {
      alert("Seuls le Président, le Secrétaire ou le Trésorier peuvent publier un PV.");
      return;
    }

    let voters = this.selectedVoters;
    if (!voters || voters.length === 0) {
      const myId = Number(this.token.getUserId());
      voters = [myId];
    }

    const body = {
      content: this.newMinuteContent,
      idVoters: voters.map(id => Number(id)),
      date: new Date().toLocaleDateString('fr-FR'),
      idAssociation: Number(this.association.id)
    };

    this.http.post('http://localhost:3000/minutes', body).subscribe({
      next: () => {
        this.newMinuteContent = '';
        this.selectedVoters = [];
        this.loadMinutes(String(this.association.id));
        alert("PV publié !");
      },
      error: (err) => {
        console.error(err);
        alert("Erreur lors de la création du PV.");
      }
    });
  }

  deleteAssociation(): void {
    if (!this.association) return;
    if (!this.isPresident()) {
      alert("Seul le Président a le droit de supprimer cette association.");
      return;
    }
    if (confirm(`Voulez-vous vraiment supprimer l'association "${this.association.name}" ?`)) {
      this.http.delete(`http://localhost:3000/associations/${this.association.id}`).subscribe({
        next: () => {
          alert('Association supprimée.');
          this.router.navigate(['/associations']);
        },
        error: (err) => {
          console.error(err);
          alert('Erreur lors de la suppression');
        },
      });
    }
  }
}