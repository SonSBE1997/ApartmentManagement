import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { LoginComponent } from './login/login.component';

import { BrowserAnimationsModule } from '@angular/platform-browser/animations';

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
  MatTabsModule
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
    DeletePopUpComponent
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
    NotifierModule.withConfig({
      position: {
        horizontal: {
          position: 'right',
          distance: 20
        },
        vertical: {
          position: 'top',
          distance: 50,
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
  entryComponents: [AptPopUpComponent, DeletePopUpComponent],
  providers: [
    { provide: ErrorStateMatcher, useClass: ShowOnDirtyErrorStateMatcher },
    // {
    //   provide: HTTP_INTERCEPTORS,
    //   useClass: HttpErrorInterceptor,
    //   multi: true
    // },
    { provide: MAT_DATE_LOCALE, useValue: 'vi-VI' }
  ],
  bootstrap: [AppComponent]
})
export class AppModule {}
