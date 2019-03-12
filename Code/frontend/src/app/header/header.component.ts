import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { SharedService } from '../service/sharedService.service';
import { Employee } from 'src/entity/Employee';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.scss']
})
export class HeaderComponent implements OnInit {
  url = '';
  employee: Employee = null;
  constructor(private router: Router, private sharedService: SharedService) {}

  ngOnInit() {
    const e = localStorage.getItem('user');
    if (e !== null) {
      this.employee = JSON.parse(e);
    }
    this.url = window.location.href.split('/')[3];
  }

  logout() {
    localStorage.removeItem('token');
    localStorage.removeItem('isAuthen');
    localStorage.removeItem('user');
    this.sharedService.authentic(false);
    this.router.navigateByUrl('/login');
  }
}
