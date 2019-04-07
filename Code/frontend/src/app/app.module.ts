import { BrowserModule } from '@angular/platform-browser';
import { NgModule, ErrorHandler } from '@angular/core';
import { CommonModule } from '@angular/common';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { LoginComponent } from './login/login.component';
import { CookieService } from 'ngx-cookie-service';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { PushNotificationService } from 'ngx-push-notifications';
import {
  MatInputModule,
  MatSelectModule,
  MatButtonModule,
  MatTableModule,
  MatPaginatorModule,
  MatFormFieldModule,
  MatNativeDateModule,
  MatDialogModule,
  ErrorStateMatcher,
  ShowOnDirtyErrorStateMatcher,
  MatRadioModule,
  MatCheckboxModule,
  MatCardModule,
  MatTooltipModule,
  MatMenuModule,
  MatIconModule,
  MatBadgeModule,
  MatSortModule,
  MAT_DATE_LOCALE,
  MatTabsModule,
  MatProgressBarModule,
  MatAutocompleteModule,
  MatSliderModule,
  MatSidenavModule,
  MatListModule
} from '@angular/material';

import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { MatDatepickerModule } from '@angular/material/datepicker';
import { HttpClientModule, HTTP_INTERCEPTORS } from '@angular/common/http';
import { HomeComponent, AptPopUpComponent } from './home/home.component';
import { HeaderComponent } from './header/header.component';
import { RoomDetailComponent } from './home/room-detail/room-detail.component';
import { HttpErrorInterceptor } from './errors/http-error.interceptor';
import { NotFoundComponent } from './errors/not-found/not-found.component';
import { WrongComponent } from './errors/wrong/wrong.component';
import { NotifierModule } from 'angular-notifier';
import { PopUpComponent } from './home/pop-up/pop-up.component';
import { DetailComponent } from './home/detail/detail.component';
import { DeletePopUpComponent } from './common/delete-pop-up/delete-pop-up.component';
import { UserComponent } from './user/user.component';
import { RegisterCardComponent } from './user/register-card/register-card.component';
import { ErrorsHandler } from './errors/error-handler';
import { EmployeeComponent } from './employee/employee.component';
import { DeptComponent } from './dept/dept.component';
import { SaveDeptComponent } from './dept/save-dept/save-dept.component';
import { SaveEmpComponent } from './employee/save-emp/save-emp.component';
import { ProfileComponent } from './profile/profile.component';
import { SaveUserComponent } from './user/save-user/save-user.component';
import { RegisterLeaveComponent } from './user/register-leave/register-leave.component';
import { SaveHouseholdComponent } from './home/room-detail/save-household/save-household.component';

@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    HomeComponent,
    HeaderComponent,
    AptPopUpComponent,
    RoomDetailComponent,
    NotFoundComponent,
    WrongComponent,
    PopUpComponent,
    DetailComponent,
    DeletePopUpComponent,
    UserComponent,
    RegisterCardComponent,
    EmployeeComponent,
    DeptComponent,
    SaveDeptComponent,
    SaveEmpComponent,
    ProfileComponent,
    SaveUserComponent,
    RegisterLeaveComponent,
    SaveHouseholdComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    BrowserAnimationsModule,
    HttpClientModule,
    MatInputModule,
    MatSelectModule,
    MatButtonModule,
    MatTableModule,
    MatPaginatorModule,
    MatFormFieldModule,
    MatDatepickerModule,
    MatNativeDateModule,
    MatDialogModule,
    FormsModule,
    ReactiveFormsModule,
    MatRadioModule,
    MatCheckboxModule,
    CommonModule,
    FormsModule,
    MatCardModule,
    MatTooltipModule,
    MatMenuModule,
    MatIconModule,
    MatBadgeModule,
    MatDialogModule,
    MatSortModule,
    MatDatepickerModule,
    MatTabsModule,
    ReactiveFormsModule,
    MatProgressBarModule,
    MatListModule,
    MatSidenavModule,
    MatAutocompleteModule,
    NotifierModule.withConfig({
      position: {
        horizontal: {
          position: 'right',
          distance: 20
        },
        vertical: {
          position: 'top',
          distance: 125,
          gap: 10
        }
      },
      theme: 'material',
      behaviour: {
        autoHide: 3000,
        onClick: 'hide',
        onMouseover: 'pauseAutoHide',
        showDismissButton: true,
        stacking: 4
      }
    })
  ],
  entryComponents: [
    AptPopUpComponent,
    DeletePopUpComponent,
    PopUpComponent,
    SaveDeptComponent,
    SaveEmpComponent,
    SaveUserComponent,
    RegisterLeaveComponent,
    RegisterCardComponent,
    SaveHouseholdComponent
  ],
  providers: [
    { provide: ErrorStateMatcher, useClass: ShowOnDirtyErrorStateMatcher },
    // {
    //   provide: HTTP_INTERCEPTORS,
    //   useClass: HttpErrorInterceptor,
    //   multi: true
    // },
    { provide: MAT_DATE_LOCALE, useValue: 'vi-VI' },
    {
      provide: ErrorHandler,
      useClass: ErrorsHandler
    },
    CookieService,
    PushNotificationService
  ],
  bootstrap: [AppComponent]
})
export class AppModule {}
