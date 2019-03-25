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
  constructor(private router: Router, private sharedService: SharedService) {
    setInterval(() => {
      this.url = window.location.href.split('/')[3];
    }, 100);
  }

  ngOnInit() {
    const token = localStorage.getItem('token');
    if (token !== null) {
      const data = jwt_decode(token);
      this.employee = {
        id: data.employeeId,
        name: data.name,
        username: data.sub,
        disable: data.disable
      };
    }
  }

  logout() {
    localStorage.removeItem('token');
    localStorage.removeItem('isAuthen');
    this.sharedService.authentic(false);
    this.router.navigateByUrl('/login');
  }
}
