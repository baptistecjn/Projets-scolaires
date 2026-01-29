import { Injectable, Inject, PLATFORM_ID } from '@angular/core';
import { isPlatformBrowser } from '@angular/common';
import { BehaviorSubject } from 'rxjs';

const TOKEN_KEY = 'token';
const USERNAME_KEY = 'username';
const IS_LOGGED_IN = 'isLoggedIn';
const IS_LOGGED = 'true';

@Injectable({
  providedIn: 'root'
})
export class TokenStorageService {
  
  private isLoggedSubject = new BehaviorSubject<boolean>(false);
  isLogged$ = this.isLoggedSubject.asObservable();

  constructor(@Inject(PLATFORM_ID) private platformId: Object) {
    if (isPlatformBrowser(this.platformId)) {
      this.isLoggedSubject.next(this.isLogged());
    }
  }

  public clear(): void {
    if (isPlatformBrowser(this.platformId)) {
      localStorage.clear();
      sessionStorage.clear()
    }
    this.isLoggedSubject.next(false);
  }

  public save(token: string): void {
    if (isPlatformBrowser(this.platformId)) {
      localStorage.removeItem(TOKEN_KEY);
      localStorage.removeItem(USERNAME_KEY);
      localStorage.removeItem(IS_LOGGED_IN);
      localStorage.setItem(TOKEN_KEY, token);
      localStorage.setItem(IS_LOGGED_IN, IS_LOGGED);
    }
  }

  public saveToken(token: string): void {
    if (isPlatformBrowser(this.platformId)) {
      sessionStorage.setItem('auth-token', token);
      localStorage.setItem(TOKEN_KEY, token); 
      localStorage.setItem(IS_LOGGED_IN, 'true');
      this.isLoggedSubject.next(true); 
    } 
  }

  public getToken(): string {
    if (isPlatformBrowser(this.platformId)) {
      return localStorage.getItem(TOKEN_KEY) || '';
    }
    return '';
  }

  public isLogged(): boolean {
    if (isPlatformBrowser(this.platformId)) {
      return localStorage.getItem(IS_LOGGED_IN) === 'true';    }
    return false;
  }

  public getUserId(): number | null {
    const token = this.getToken();
    if (!token) return null;
    
    try {
      const payload = parseJwt(token);
      return payload.sub;
    } catch (e) {
      return null;
    }
  }
}


function parseJwt(token: string) {
    const base64Url = token.split('.')[1];
    const base64 = base64Url.replace(/-/g, '+').replace(/_/g, '/');
    const jsonPayload = decodeURIComponent(window.atob(base64).split('').map(function(c) {
        return '%' + ('00' + c.charCodeAt(0).toString(16)).slice(-2);
    }).join(''));
    return JSON.parse(jsonPayload);
}