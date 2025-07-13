import { Routes } from '@angular/router';
import {Student} from './student/student';

export const routes: Routes = [
  { path: '', redirectTo: 'students', pathMatch: 'full' },
  { path: '/api/students', component: Student },
  { path: '**', redirectTo: 'students' }
];
