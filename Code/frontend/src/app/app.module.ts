import { BrowserModule } from '@angular/platform-browser';
import { NgModule, ErrorHandler } from '@angular/core';
import { CommonModule } from '@angular/common';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { LoginComponent } from './login/login.component';
import { CookieService } from 'ngx-cookie-service';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { PushNotificationService } from 'ngx-push-notifications';
import { ChartsModule } from 'ng2-charts';
import { LoadingBarHttpClientModule } from '@ngx-loading-bar/http-client';

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
  MatListModule,
  MatExpansionModule,
  MatPaginatorIntl
} from '@angular/material';



import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { MatDatepickerModule } from '@angular/material/datepicker';
import { HttpClientModule, HTTP_INTERCEPTORS } from '@angular/common/http';
import { ApartmentComponent, AptPopUpComponent } from './apartment/apartment.component';
import { HeaderComponent } from './header/header.component';
import { RoomDetailComponent } from './apartment/room-detail/room-detail.component';
import { HttpErrorInterceptor } from './errors/http-error.interceptor';
import { NotFoundComponent } from './errors/not-found/not-found.component';
import { WrongComponent } from './errors/wrong/wrong.component';
import { NotifierModule } from 'angular-notifier';
import { PopUpComponent } from './apartment/pop-up/pop-up.component';
import { DetailComponent } from './apartment/detail/detail.component';
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
import { SaveHouseholdComponent } from './apartment/room-detail/save-household/save-household.component';
import { ImageCropperModule } from 'ngx-image-cropper';
import { ChangePhotoComponent } from './profile/change-photo/change-photo.component';
import { LeaveComponent } from './apartment/room-detail/leave/leave.component';
import { CancelComeComponent } from './apartment/room-detail/cancel-come/cancel-come.component';
import { StaircaseComponent } from './system/staircase/staircase.component';
import { WaterComponent } from './system/water/water.component';
import { ElectricComponent } from './system/electric/electric.component';
import { FireComponent } from './system/fire/fire.component';
import { SDeviceComponent } from './system/staircase/s-device/s-device.component';
import { SDetailComponent } from './system/staircase/s-detail/s-detail.component';
import { SaveSDetailComponent } from './system/staircase/save-s-detail/save-s-detail.component';
import { SaveSDeviceComponent } from './system/staircase/s-device/save-s-device/save-s-device.component';
import { EDetailComponent } from './system/electric/e-detail/e-detail.component';
import { SaveEDetailComponent } from './system/electric/save-e-detail/save-e-detail.component';
import { FDetailComponent } from './system/fire/f-detail/f-detail.component';
import { SaveFDetailComponent } from './system/fire/save-f-detail/save-f-detail.component';
import { WDetailComponent } from './system/water/w-detail/w-detail.component';
import { SaveWDetailComponent } from './system/water/save-w-detail/save-w-detail.component';
import { RepairAptComponent } from './apartment/repair-apt/repair-apt.component';
import { SaveRepairComponent } from './apartment/repair-apt/save-repair/save-repair.component';
import { ADetailComponent } from './apartment/repair-apt/a-detail/a-detail.component';
import { ServicesComponent } from './services/services.component';
import { ImportServiceComponent } from './services/import-service/import-service.component';
import { ServiceTypeComponent } from './services/service-type/service-type.component';
import { SaveTypeComponent } from './services/service-type/save-type/save-type.component';
import { SaveServiceComponent } from './services/save-service/save-service.component';
import { getDutchPaginatorIntl } from './customPaginator';
import { ConfirmPaymentComponent } from './services/confirm-payment/confirm-payment.component';
import { DetailServiceComponent } from './services/detail-service/detail-service.component';
import { RepairDtlComponent } from './apartment/room-detail/repair-dtl/repair-dtl.component';
import { StatisticComponent } from './statistic/statistic.component';
import { InfoCardComponent } from './user/info-card/info-card.component';
import { PrepaymentComponent } from './services/prepayment/prepayment.component';

@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    ApartmentComponent,
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
    SaveHouseholdComponent,
    ChangePhotoComponent,
    LeaveComponent,
    CancelComeComponent,
    StaircaseComponent,
    WaterComponent,
    ElectricComponent,
    FireComponent,
    SDeviceComponent,
    SDetailComponent,
    SaveSDetailComponent,
    SaveSDeviceComponent,
    EDetailComponent,
    SaveEDetailComponent,
    FDetailComponent,
    SaveFDetailComponent,
    WDetailComponent,
    SaveWDetailComponent,
    RepairAptComponent,
    SaveRepairComponent,
    ADetailComponent,
    ServicesComponent,
    ImportServiceComponent,
    ServiceTypeComponent,
    SaveTypeComponent,
    SaveServiceComponent,
    ConfirmPaymentComponent,
    DetailServiceComponent,
    RepairDtlComponent,
    StatisticComponent,
    InfoCardComponent,
    PrepaymentComponent
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
    ImageCropperModule,
    MatExpansionModule,
    ChartsModule,
    LoadingBarHttpClientModule,
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
    SaveHouseholdComponent,
    ChangePhotoComponent,
    LeaveComponent,
    CancelComeComponent,
    SDetailComponent,
    SaveSDetailComponent,
    SaveSDeviceComponent,
    EDetailComponent,
    SaveEDetailComponent,
    FDetailComponent,
    SaveFDetailComponent,
    WDetailComponent,
    SaveWDetailComponent,
    SaveRepairComponent,
    ADetailComponent,
    ImportServiceComponent,
    SaveTypeComponent,
    SaveServiceComponent,
    ConfirmPaymentComponent,
    DetailServiceComponent,
    RepairDtlComponent,
    PrepaymentComponent
  ],
  providers: [
    { provide: ErrorStateMatcher, useClass: ShowOnDirtyErrorStateMatcher },
    {
      provide: HTTP_INTERCEPTORS,
      useClass: HttpErrorInterceptor,
      multi: true
    },
    { provide: MAT_DATE_LOCALE, useValue: 'vi-VI' },
    {
      provide: ErrorHandler,
      useClass: ErrorsHandler
    },
    CookieService,
    PushNotificationService,
    { provide: MatPaginatorIntl, useValue: getDutchPaginatorIntl() }
  ],
  bootstrap: [AppComponent]
})
export class AppModule {}
