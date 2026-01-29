import { Routes } from '@angular/router';
import { UserList } from './user-list/user-list';
import { Login } from './login/login';
import { AssociationsList } from './associations-list/associations-list';
import { authGuard } from './guards/auth-guard';
import { Profile } from './profile/profile';
import { AssociationDetail } from './association-detail/association-detail';
import { Register } from './register/register';



export const routes: Routes = [
    { path: 'login', component: Login },
    { path: 'associations', component: AssociationsList, canActivate: [authGuard] },
    { path: 'users', component: UserList, canActivate: [authGuard] },
    { path: '', redirectTo: 'login', pathMatch: 'full' },
    { path: 'profile', component: Profile, canActivate: [authGuard] },
    { path: 'users/:id', component: Profile, canActivate: [authGuard] },
    { path: 'associations/:id', component: AssociationDetail },
    { path: 'register', component: Register },
];