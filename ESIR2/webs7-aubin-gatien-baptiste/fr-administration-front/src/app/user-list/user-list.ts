import { Component, OnInit } from '@angular/core';
import { MatTableModule } from '@angular/material/table';
import { CommonModule } from '@angular/common';
import { HttpClient } from '@angular/common/http';
import { Observable, map, lastValueFrom } from 'rxjs';
import { Nav } from '../nav/nav';
import { Router, ActivatedRoute } from '@angular/router';


@Component({
  selector: 'app-user-list',
  standalone: true,
  imports: [CommonModule, MatTableModule],
  templateUrl: './user-list.html',
  styleUrls: ['./user-list.css']
})


export class UserList implements OnInit {

  displayedColumns: string[] = ['id', 'lastname', 'firstname', 'age'];
  dataSource$: Observable<any[]> | undefined;

  constructor(private http: HttpClient, private router: Router, private route: ActivatedRoute) { }

  ngOnInit(): void {
    this.route.queryParams.subscribe(params => {
      const searchName = params['name']?.toLowerCase();
      
      this.dataSource$ = this.http.get<any[]>('http://localhost:3000/users').pipe(
        map(users => {
          if (!searchName) return users;
          return users.filter(u => 
            u.lastname.toLowerCase().includes(searchName) || 
            u.firstname.toLowerCase().includes(searchName)
          );
        })
      );
    });
  }

  showUserDetail(user: any): void {
    this.router.navigate(['/users', user.id]);
  }
}