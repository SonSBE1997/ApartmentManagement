import { Component, OnInit } from '@angular/core';
import { SharedService } from './service/sharedService.service';
import { Router } from '@angular/router';
import { CookieService } from 'ngx-cookie-service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss']
})
export class AppComponent implements OnInit {
  isAuthenticated: boolean;
  constructor(
    private router: Router,
    private sharedService: SharedService
  ) {
    this.sharedService.isAuthentication.subscribe(isAuthentication => {
      this.isAuthenticated = isAuthentication;
    });
  }
  ngOnInit() {
    const authen = localStorage.getItem('isAuthen');
    if (authen === null || authen === 'false') {
      this.isAuthenticated = false;
      this.router.navigateByUrl('/');
    } else {
      localStorage.setItem('isAuthen', 'true');
      this.isAuthenticated = true;
    }

    this.sharedService.authentic(this.isAuthenticated);
  }
}
