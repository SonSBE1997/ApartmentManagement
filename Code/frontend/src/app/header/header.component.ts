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
    this.sharedService.matchUrl.subscribe(u => {
      this.url = u;
    });
    const url = localStorage.getItem('url');
    if (url !== null) {
      this.url = url;
      this.sharedService.changePage(url);
    }
    setInterval(() => {
      const token = localStorage.getItem('token');
      if (token !== null) {
        const data = jwt_decode(token);
        if (data.exp * 1000 <= new Date().getTime()) {
          alert('Phiên đăng nhập đã hết hạn. Vui lòng đăng nhập lại');
          localStorage.clear();
          window.location.href = '/';
        }
      }
    }, 500);
  }

  ngOnInit() {
    const token = localStorage.getItem('token');
    if (token !== null) {
      const data = jwt_decode(token);
      localStorage.setItem('userId', data.employeeId + '');
      this.employee = {
        id: data.employeeId,
        name: data.name,
        username: data.sub,
        disable: data.disable,
        role: '',
        phoneNumber: '',
        password: '',
        idCard: '',
        gender: false,
        email: '',
        dateOfBirth: null,
        address: '',
        dept: null
      };
    }
  }

  logout() {
    localStorage.clear();
    this.sharedService.authentic(false);
    this.sharedService.changePage('login');
    this.router.navigateByUrl('/login');
  }

  changePage(activeUrl, url) {
    localStorage.setItem('url', activeUrl);
    this.url = activeUrl;
    this.sharedService.changePage(activeUrl);
    this.router.navigateByUrl(url);
  }
}
