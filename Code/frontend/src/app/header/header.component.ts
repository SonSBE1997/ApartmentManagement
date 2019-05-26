import { Component, OnInit, ViewChild, ViewChildren, QueryList } from '@angular/core';
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
import { RoomService } from '../service/room.service';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.scss']
})
export class HeaderComponent implements OnInit {
  @ViewChildren(MatMenuTrigger) trigger: QueryList<MatMenuTrigger>;

  url = '';
  employee: Employee = null;
  households: Household[] = [];
  notifyNum = 0;
  noti = 0;
  userLeave: User[] = [];
  deptName = 'QL';
  constructor(
    private router: Router,
    private sharedService: SharedService,
    private householdService: HouseholdService,
    private userService: UserService,
    private pushNotificationService: PushNotificationService,
    private roomService: RoomService
  ) {
    this.sharedService.matchUrl.subscribe(u => {
      this.url = u;
    });
    const url = localStorage.getItem('url');
    if (url !== null) {
      this.url = url.split('/')[1];
      this.sharedService.changePage(this.url);
      this.router.navigateByUrl(url);
    }

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
      this.deptName = data.dept;
    }
    this.pushNotificationService.requestPermission();
  }

  checkNotify() {
    this.households = [];
    this.userLeave = [];
    this.notifyNum = this.noti;
    this.noti = 0;
    this.householdService.findHouseHoldComeToDay().subscribe(h => {
      h.forEach(v => {
        this.noti++;
        this.households.push(v);
      });
      this.saveHouseholdCome();
    });
    this.userService.leaveToday().subscribe(u => {
      u.forEach(v => {
        this.userLeave.push(v);
        this.noti++;
      });
      this.saveUserLeave();
    });
    // this.notifyNum = this.noti;
    // console.log(this.noti);
    // console.log(this.notifyNum);
  }

  saveHouseholdCome() {
    const size = this.households.length;
    if (size === 0) {
      return;
    }

    this.households.forEach(household => {
      if (household.status === '0') {
        household.status = '1';
        this.householdService.save(household).subscribe(s => console.log(s), e => console.log(e));
      }
      this.roomService.getRoomById(household.room.id).subscribe(r => {
        const status = household.hire ? '1' : '2';
        if (r.status !== status) {
          r.status = status;
          this.roomService.update(r).subscribe(success => console.log(success), error => console.log(error));
        }
      });
    });
  }

  saveUserLeave() {
    if (this.userLeave.length === 0) {
      return;
    }
    this.userLeave.forEach(user => {
        if (!user.leave) {
          user.leave = true;
          this.userService.save(user).subscribe(s => console.log(s), e => console.log(e) );
        }
        this.householdService.findById(user.household.id).subscribe(h => {
          const check = h.users.filter(u => u.leaveDate === null);
          if (check.length === 0) {
            this.roomService.getRoomById(h.room.id).subscribe(r => {
              if (r.status !== '0') {
                r.status = '0';
                this.roomService.update(r).subscribe(success => console.log(success), error => console.log(error));
              }
            });
          }
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
    localStorage.setItem('url', url);
    this.url = activeUrl;
    this.sharedService.changePage(activeUrl);
    this.router.navigateByUrl(url);
  }

  openMenu(i) {
    if (i === 0) {
      this.trigger.first.openMenu();
    } else if (i === 1) {
      this.trigger.find((v, k) => k === i).openMenu();
    } else {
      // this.trigger.last.openMenu();
    }
  }

  closeMenu(i) {
    if (i === 0) {
      this.trigger.first.closeMenu();
    } else if (i === 1) {
      this.trigger.find((v, k) => k === i).closeMenu();
    } else {
      // this.trigger.last.closeMenu();
    }
  }
}
