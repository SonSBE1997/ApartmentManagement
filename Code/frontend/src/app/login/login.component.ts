import { Component, OnInit } from '@angular/core';
import { FormControl, Validators } from '@angular/forms';
import { CommonServiceService } from '../common-service.service';
import { Router } from '@angular/router';
import { SharedService } from '../service/sharedService.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss']
})
export class LoginComponent implements OnInit {
  username: FormControl;
  password: FormControl;
  loginFailed = false;

  constructor(
    private commonService: CommonServiceService,
    private router: Router,
    private sharedService: SharedService
  ) {}

  ngOnInit() {
    this.username = new FormControl('', [Validators.required]);
    this.password = new FormControl('', [Validators.required]);
    const authen = localStorage.getItem('isAuthen');
    if (authen != null && authen !== 'false') {
      this.router.navigateByUrl('/apartment');
    }
  }

  login() {
    const credentials = {
      username: this.username.value,
      password: this.password.value
    };

    this.commonService.authentication(credentials).subscribe(e => {
      if (e != null) {
        localStorage.setItem('user', JSON.stringify(e));
        this.sharedService.authentic(true);
        localStorage.setItem('isAuthen', 'true');
        this.router.navigateByUrl('/apartment');
      } else {
        this.loginFailed = true;
        setTimeout(() => {
          this.loginFailed = false;
        }, 3000);
      }
    });
  }
}
