import { Routes } from '@angular/router';
import { Login } from './pages/login/login';
import { Register } from './pages/register/register';
import { Dashboard } from './pages/dashboard/dashboard';
import { Teams } from './pages/teams/teams';
import { Layout } from './layout/layout';
import { authGuard } from './guards/auth-guard';
import { Tasks } from './pages/tasks/tasks';

export const routes: Routes = [
  { path: 'login', component: Login },
  { path: 'register', component: Register },
  {
    path: '',
    component: Layout,
    canActivate: [authGuard],
    children: [
      { path: 'dashboard', component: Dashboard },
      { path: 'teams', component: Teams },
      { path: 'tasks', component: Tasks },
    ],
  },
  { path: '', redirectTo: '/login', pathMatch: 'full' },
];
