import { Component, OnInit, ViewChild } from '@angular/core';
import { Router } from '@angular/router';
import { SharedService } from '../service/sharedService.service';
import { Employee } from 'src/entity/Employee';
import jwt_decode from 'jwt-decode';
import { HouseholdService } from '../service/household.service';
import { Household } from 'src/entity/Household';
import { User } from 'src/entity/User';
import { UserService } from '../service/user.service';
import {
  PushNotificationOptions,
  PushNotificationService
} from 'ngx-push-notifications';
import { MatMenuTrigger } from '@angular/material';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.scss']
})
export class HeaderComponent implements OnInit {
  @ViewChild(MatMenuTrigger) trigger: MatMenuTrigger;
  url = '';
  employee: Employee = null;
  households: Household[] = [];
  notifyNum = 0;
  userLeave: User[] = [];
  constructor(
    private router: Router,
    private sharedService: SharedService,
    private householdService: HouseholdService,
    private userService: UserService,
    private pushNotificationService: PushNotificationService
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
    this.checkNotify();
    setInterval(() => {
      this.checkNotify();
    }, 15000);

    setInterval(() => {
      const d = new Date();
      const h = d.getHours();
      if (
        (h === 8 || h === 10 || h === 14 || h === 16) &&
        d.getMinutes() === 0
      ) {
        if (
          this.pushNotificationService.isPermissionGranted &&
          this.notifyNum > 0
        ) {
          this.showNotification();
        }
      }
    }, 50000);
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
    this.pushNotificationService.requestPermission();
  }

  checkNotify() {
    this.households = [];
    this.userLeave = [];
    this.notifyNum = 0;
    this.householdService.findHouseHoldComeToDay().subscribe(h => {
      h.forEach(v => {
        this.households.push(v);
        this.notifyNum++;
      });
    });
    this.userService.leaveToday().subscribe(u => {
      u.forEach(v => {
        this.userLeave.push(v);
        this.notifyNum++;
      });
    });
  }

  showNotification() {
    const title = 'Thông báo';
    const options = new PushNotificationOptions();
    options.body = 'Native Push Notification';
    options.sound = 'drop';
    options.silent = false;

    this.pushNotificationService.create(title, options).subscribe(
      notif => {
        if (notif.event.type === 'show') {
          console.log('onshow');
          setTimeout(() => {
            notif.notification.close();
          }, 5000);
        }
        if (notif.event.type === 'click') {
          console.log('click');
          notif.notification.close();
        }
        if (notif.event.type === 'close') {
          console.log('close');
        }
      },
      err => {
        console.log(err);
      }
    );
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

  openMenuApartment() {
    this.trigger.openMenu();
  }

  closeMenuApartment() {
    this.trigger.closeMenu();
  }
}
