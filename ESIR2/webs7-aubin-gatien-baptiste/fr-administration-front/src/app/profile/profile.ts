import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { TokenStorageService } from '../services/token-storage.service';
import { HttpClient } from '@angular/common/http';
import { Observable, tap } from 'rxjs';
import { ActivatedRoute } from '@angular/router';
import { FormsModule } from '@angular/forms';
import { Router } from '@angular/router';


@Component({
  selector: 'app-profile',
  imports: [CommonModule, FormsModule],
  templateUrl: './profile.html',
  styleUrl: './profile.css',
})


export class Profile implements OnInit {

  currentUser$: Observable<any> | undefined;
  userRoles$: Observable<any[]> | undefined;
  isOwnProfile: boolean = false;
  isEditing: boolean = false;
  editForm: any = {};

  constructor(private token: TokenStorageService, private http: HttpClient, private route: ActivatedRoute, private router: Router) { }

  ngOnInit(): void {
    this.route.paramMap.subscribe(params => {
    const idFromUrl = params.get('id');
    const myId = this.token.getUserId();

    if (idFromUrl) {
        const userIdToFetch = Number(idFromUrl);
        const currentUserId = Number(myId);

        this.isOwnProfile = (userIdToFetch === currentUserId);
        
        console.log("ID URL:", userIdToFetch, "Mon ID:", currentUserId, "isOwn:", this.isOwnProfile);
        
        this.currentUser$ = this.http.get<any>(`http://localhost:3000/users/${userIdToFetch}`);
        this.userRoles$ = this.http.get<any[]>(`http://localhost:3000/users/${userIdToFetch}/roles`);
    } else {
        this.isOwnProfile = true;
        this.currentUser$ = this.http.get<any>(`http://localhost:3000/users/${myId}`);
    }
    });
  }

  startEdit(user: any): void {
    this.editForm = { ...user }; 
    this.isEditing = true;
  }
  cancelEdit(): void {
    this.isEditing = false;
  }

  saveProfile():void{
    if (!this.editForm.id){
      return;
    }
    this.http.put(`http://localhost:3000/users/${this.editForm.id}`, this.editForm).subscribe({
      next: (updatedUser) => {
          alert('Profil mis à jour !');
          this.isEditing = false;
          this.currentUser$ = new Observable(subscriber => subscriber.next(this.editForm));
        },
        error: (err) => {
          console.error(err);
          alert("Erreur lors de la modification.");
        }
      });
  }

  deleteUser(id: number): void {
    const myId = this.token.getUserId();

    if (confirm("Voulez-vous vraiment supprimer cet utilisateur ?")) {
      this.http.delete(`http://localhost:3000/users/${id}`).subscribe({
        next: () => {
          alert("Utilisateur supprimé !");
          this.router.navigate(['/login']);
        },
        error: (err) => alert("Erreur lors de la suppression")
      });
    }
  }
}