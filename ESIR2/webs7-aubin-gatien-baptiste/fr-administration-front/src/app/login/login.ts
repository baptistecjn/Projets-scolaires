import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { ApiHelperService } from '../services/api-helper.service';
import { TokenStorageService } from '../services/token-storage.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-login',
  standalone: true,
  imports: [CommonModule, FormsModule], 
  templateUrl: './login.html',
  styleUrl: './login.css', 
})
export class Login {

  username: string = '';
  password: string = '';
  errorMessage: string = '';

  constructor(
    private api: ApiHelperService,
    private tokenStorageService: TokenStorageService,
    private router: Router
  ) { }

  login(): void {
    if (this.username && this.password) {
      
      this.api.post({ 
        endpoint: '/auth/login', 
        data: { username: this.username, password: this.password } 
      })
      .then(response => {
        this.tokenStorageService.saveToken(response.access_token);

        if (this.tokenStorageService.isLogged()) {
          this.router.navigateByUrl('/profile');
        }
      })
      .catch(error => {
        console.error('Erreur de login', error);
        this.errorMessage = 'Identifiant ou mot de passe incorrect.';
        this.tokenStorageService.clear();
      });

    } else {
      this.errorMessage = 'Veuillez remplir tous les champs.';
    }
  }
  goToRegister(): void {
    this.router.navigate(['/register']);
  }
}