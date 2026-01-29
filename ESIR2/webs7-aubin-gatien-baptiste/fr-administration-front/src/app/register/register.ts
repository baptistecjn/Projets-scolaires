import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { HttpClient } from '@angular/common/http';
import { Router, RouterModule } from '@angular/router';

@Component({
  selector: 'app-register',
  standalone: true,
  imports: [CommonModule, FormsModule],
  templateUrl: './register.html',
  styleUrl: './register.css',
})
export class Register {

  form = {
    firstname: '',
    lastname: '',
    age: null,
    username: '',
    password: ''
  };

  errorMessage: string = '';

  constructor(
    private http: HttpClient,
    private router: Router
  ) { }

  onSubmit(): void {
    if (!this.form.username || !this.form.password || !this.form.firstname || !this.form.lastname) {
      this.errorMessage = "Veuillez remplir tous les champs obligatoires.";
      return;
    }
    this.http.post('http://localhost:3000/users', this.form)
      .subscribe({
        next: () => {
          alert("Compte créé avec succès ! Connectez-vous.");
          this.router.navigate(['/login']);
        },
        error: (err) => {
          console.error(err);
          this.errorMessage = "Erreur lors de l'inscription (Ce nom d'utilisateur est peut-être déjà pris).";
        }
      });
  }
  goToLogin(): void {
    this.router.navigate(['/login']);
  }
}