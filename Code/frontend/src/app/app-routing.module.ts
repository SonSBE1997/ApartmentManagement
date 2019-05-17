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
import { StaircaseComponent } from './system/staircase/staircase.component';
import { ElectricComponent } from './system/electric/electric.component';
import { FireComponent } from './system/fire/fire.component';
import { WaterComponent } from './system/water/water.component';
import { SDeviceComponent } from './system/staircase/s-device/s-device.component';
import { RepairAptComponent } from './home/repair-apt/repair-apt.component';
import { ServicesComponent } from './services/services.component';
import { ServiceTypeComponent } from './services/service-type/service-type.component';
import { StatisticComponent } from './statistic/statistic.component';

const routes: Routes = [
  { path: '', redirectTo: '/login', pathMatch: 'full' },
  { path: 'login', component: LoginComponent },
  { path: 'apartment', component: HomeComponent },
  { path: 'apartment/detail', component: DetailComponent },
  { path: 'apartment/repair', component: RepairAptComponent },
  { path: 'user', component: UserComponent },
  { path: 'apartment/room/:id', component: RoomDetailComponent },
  { path: 'dept', component: DeptComponent },
  { path: 'employee', component: EmployeeComponent },
  { path: 'profile', component: ProfileComponent },
  { path: 'service', component: ServicesComponent },
  { path: 'service/service-type', component: ServiceTypeComponent },
  { path: 'system/staircase', component: StaircaseComponent },
  { path: 'system/staircase/device', component: SDeviceComponent },
  { path: 'system/electric', component: ElectricComponent },
  { path: 'system/fire-protection', component: FireComponent },
  { path: 'system/water-supplier', component: WaterComponent },
  { path: 'statistic', component: StatisticComponent },
  { path: 'errors/not-found', component: NotFoundComponent },
  { path: 'errors/wrong/:status', component: WrongComponent },
  { path: '**', component: NotFoundComponent }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule {}
