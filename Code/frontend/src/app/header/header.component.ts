import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { SharedService } from '../service/sharedService.service';
import { Employee } from 'src/entity/Employee';
import jwt_decode from 'jwt-decode';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.scss']
})
export class HeaderComponent implements OnInit {
  url = '';
  employee: Employee = null;
  constructor(
    private router: Router,
    private sharedService: SharedService
  ) {
    setInterval(() => {
      this.url = window.location.href.split('/')[3];
      const token = localStorage.getItem('token');
      if (token !== null) {
        const data = jwt_decode(token);
        if (data.exp * 1000 <= new Date().getTime()) {
          alert('Phiên đăng nhập đã hết hạn. Vui lòng đăng nhập lại');
          localStorage.clear();
          window.location.href = '/';
        }
      }
    }, 50);
  }

  ngOnInit() {
    const token = localStorage.getItem('token');
    if (token !== null) {
      const data = jwt_decode(token);
      console.log(data);
      console.log(new Date().getTime());
      this.employee = {
        id: data.employeeId,
        name: data.name,
        username: data.sub,
        disable: data.disable
      };
    }

    // if (this.cookieService.check('token')) {
    //   const data = jwt_decode(this.cookieService.get('token'));
    //   this.employee = {
    //     id: data.employeeId,
    //     name: data.name,
    //     username: data.sub,
    //     disable: data.disable
    //   };
    // }
  }

  logout() {
    localStorage.clear();
    // this.cookieService.delete('token');
    // this.cookieService.delete('isAuthen');
    this.sharedService.authentic(false);
    this.router.navigateByUrl('/login');
  }
}
