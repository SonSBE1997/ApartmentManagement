import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { HomeComponent } from './home/home.component';
import { LoginComponent } from './login/login.component';
import { RoomDetailComponent } from './home/room-detail/room-detail.component';
import { NotFoundComponent } from './errors/not-found/not-found.component';
import { WrongComponent } from './errors/wrong/wrong.component';
import { DetailComponent } from './home/detail/detail.component';
import { UserComponent } from './user/user.component';
import { DeptComponent } from './dept/dept.component';
import { EmployeeComponent } from './employee/employee.component';
import { ProfileComponent } from './profile/profile.component';

const routes: Routes = [
  { path: '', redirectTo: '/login', pathMatch: 'full' },
  { path: 'login', component: LoginComponent },
  { path: 'apartment', component: HomeComponent },
  { path: 'apartment/detail', component: DetailComponent },
  { path: 'user', component: UserComponent },
  { path: 'apartment/room/:id', component: RoomDetailComponent },
  { path: 'dept', component: DeptComponent },
  { path: 'employee', component: EmployeeComponent },
  { path: 'profile', component: ProfileComponent },
  { path: 'errors/not-found', component: NotFoundComponent },
  { path: 'errors/wrong/:status', component: WrongComponent },
  { path: '**', component: NotFoundComponent }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule {}
