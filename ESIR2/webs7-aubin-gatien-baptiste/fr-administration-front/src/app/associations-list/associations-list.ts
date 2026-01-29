import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { MatTableModule } from '@angular/material/table';
import { HttpClient } from '@angular/common/http';
import { Observable, map } from 'rxjs';
import { Router, ActivatedRoute } from '@angular/router';
import { FormsModule } from '@angular/forms';

export interface AssociationDTO {
  id: number;
  name: string;
}

@Component({
  selector: 'app-associations-list',
  standalone: true,
  imports: [CommonModule, MatTableModule, FormsModule],
  templateUrl: './associations-list.html',
  styleUrl: './associations-list.css'
})
export class AssociationsList implements OnInit {
  
  displayedColumns: string[] = ['id', 'name', 'actions'];
  
  dataSource$: Observable<AssociationDTO[]> | undefined;
  newAssoName: string = '';

  constructor(private http: HttpClient, private router : Router, private route: ActivatedRoute) {}

  ngOnInit(): void {
    this.route.queryParams.subscribe(params => {
      const searchName = params['name']?.toLowerCase();

      this.dataSource$ = this.http.get<AssociationDTO[]>('http://localhost:3000/associations').pipe(
        map(assos => {
          if (!searchName) return assos;
          return assos.filter(a => a.name.toLowerCase().includes(searchName));
        })
      );
    });
  }

  goToDetail(id: number): void {
    this.router.navigate(['/associations', id]);
  }

  refreshList(): void {
    this.dataSource$ = this.http.get<any[]>('http://localhost:3000/associations');
  }

  createAssociation(): void {
    if (!this.newAssoName.trim()) {
      return;
    }

    const body = { name: this.newAssoName };

    this.http.post('http://localhost:3000/associations', body)
      .subscribe({
        next: () => {
          this.refreshList();
          setTimeout(() => {
            this.newAssoName = '';
          });
        },
        error: (err) => {
          console.error(err);
          alert("Erreur lors de la création de l'association");
        }
      });
  }
}