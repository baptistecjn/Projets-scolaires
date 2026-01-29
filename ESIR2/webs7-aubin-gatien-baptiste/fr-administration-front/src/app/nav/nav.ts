import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { Router, RouterModule, RouterLink } from '@angular/router';
import { TokenStorageService } from '../services/token-storage.service';

@Component({
  selector: 'app-nav',
  imports: [CommonModule, RouterLink, RouterModule],
  templateUrl: './nav.html',
  styleUrls: ['./nav.css']
})
export class Nav implements OnInit {
  isLogged: boolean = false;

  constructor(
    private tokenStorageService: TokenStorageService,
    private router: Router
  ) {}

  ngOnInit(): void {
    this.tokenStorageService.isLogged$.subscribe(status => {
        this.isLogged = status;
    });
    this.isLogged = this.tokenStorageService.isLogged();
  }

  quickSearch(id: string): void {
    if (!id) return;

    console.log("Recherche de l'ID:", id);
    this.router.navigate(['/search', id]);
  }
  
  logout(): void {
    this.tokenStorageService.clear();
    this.isLogged = false;
    this.router.navigateByUrl('/login');
  }

  goToDetail(type: string, id: string): void {
    if (!id) {
      alert("Veuillez entrer un ID");
      return;
    }
    
    this.router.navigate([`/${type}`, id]);
  }

  onSearch(type: string, query: string): void {
  const cleanQuery = query.trim();
  if (!cleanQuery) return;

  if (!isNaN(Number(cleanQuery))) {
    this.router.navigate([`/${type}`, cleanQuery]);
  } 
  else {
    this.router.navigate([`/${type}`], { queryParams: { name: cleanQuery } });
  }
}

}







