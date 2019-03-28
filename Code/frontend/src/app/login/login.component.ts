import { Component, OnInit } from '@angular/core';
import { FormControl, Validators } from '@angular/forms';
import { CommonServiceService } from '../common-service.service';
import { Router } from '@angular/router';
import { SharedService } from '../service/sharedService.service';
import { CookieService } from 'ngx-cookie-service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss']
})
export class LoginComponent implements OnInit {
  username: FormControl;
  password: FormControl;
  loginFailed = false;
  message = '';

  constructor(
    private commonService: CommonServiceService,
    private router: Router,
    private sharedService: SharedService,
    private cookieService: CookieService
  ) {}

  ngOnInit() {
    this.username = new FormControl('', [Validators.required]);
    this.password = new FormControl('', [Validators.required]);
    const authen = localStorage.getItem('isAuthen');
    if (authen != null && authen !== 'false') {
      this.router.navigateByUrl('/apartment');
    }
    // if (this.cookieService.check('isAuthen')) {
    //   const authen = this.cookieService.get('isAuthen');
    //   if (authen !== 'false') {
    //     this.router.navigateByUrl('/apartment');
    //   }
    // }
  }

  login() {
    const credentials = {
      username: this.username.value,
      password: this.password.value
    };

    this.commonService.authentication(credentials).subscribe(res => {
      if (res.status === 200) {
        const token = res.headers.get('token');
        if (token != null && token !== '') {
          localStorage.setItem('token', token);
          // this.cookieService.set('token', token);
        }
        if (res.body != null) {
          this.sharedService.authentic(true);
          localStorage.setItem('isAuthen', 'true');
          // this.cookieService.set('isAuthen', 'true');
          this.router.navigateByUrl('/apartment');
        } else {
          this.loginFailed = true;
          this.message = 'Tên đăng nhập hoặc mật khẩu không đúng';
          setTimeout(() => {
            this.loginFailed = false;
          }, 3000);
        }
      }
    });
  }
}
