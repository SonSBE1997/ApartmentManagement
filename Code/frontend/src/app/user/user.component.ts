import { Component, OnInit, ViewChild } from '@angular/core';
import { User } from 'src/entity/User';
import { Building } from 'src/entity/Building';
import { UserService } from '../service/user.service';
import { ApartmentService } from '../service/apartment.service';
import {
  MatDialog,
  MatSort,
  MatPaginator,
  MatTableDataSource
} from '@angular/material';
import { NotifierService } from 'angular-notifier';
import { DateService } from '../service/date.servce';
import { Room } from 'src/entity/Room';
import { Floor } from 'src/entity/Floor';
import { Observable } from 'rxjs';
import { FormControl } from '@angular/forms';
import { startWith, map } from 'rxjs/operators';
import { CommonServiceService } from '../common-service.service';
import { SaveUserComponent } from './save-user/save-user.component';
import { RegisterLeaveComponent } from './register-leave/register-leave.component';
import { Card } from 'src/entity/Card';
import { CardService } from '../service/card.service';
import { VehicleService } from '../service/vehicle.service';
import { DeletePopUpComponent } from '../common/delete-pop-up/delete-pop-up.component';
import { RegisterCardComponent } from './register-card/register-card.component';

@Component({
  selector: 'app-user',
  templateUrl: './user.component.html',
  styleUrls: ['./user.component.scss']
})
export class UserComponent implements OnInit {
  users: User[] = [];
  buildings: Building[] = [];
  floors: Floor[] = [];
  rooms: Room[] = [];
  now = new Date();
  cards: Card[] = [];
  selectedUser = -1;
  cardDataSource: MatTableDataSource<Card>;
  @ViewChild(MatPaginator) matPaginator: MatPaginator;
  @ViewChild(MatSort) matSort: MatSort;
  dataSource: MatTableDataSource<User>;

  // Filter
  searchStr = '';
  isFilter = false;
  filteredOptions: Observable<Room[]>;
  autocomplete = new FormControl();
  selectedBuilding: Building = null;
  selectedFloor: Floor = null;
  selectedRoom: Room = null;
  status = '';
  constructor(
    private userService: UserService,
    private buildingService: ApartmentService,
    private dialog: MatDialog,
    private notifierService: NotifierService,
    private dateService: DateService,
    private commonService: CommonServiceService,
    private cardService: CardService,
    private vehicleService: VehicleService
  ) {}

  ngOnInit() {
    this.matPaginator.pageIndex = 0;
    this.matPaginator.pageSize = 5;
    this.loadData();
  }

  loadByPaging() {
    this.users = [];
    this.userService
      .findByPaging(
        this.matPaginator.pageIndex,
        this.matPaginator.pageSize,
        this.searchStr
      )
      .subscribe(result => {
        if (result != null) {
          result.obj.forEach(v => {
            this.users.push(v);
          });
          this.matPaginator.length = result.size;
          this.dataSource = new MatTableDataSource(this.users);
          this.dataSource.sort = this.matSort;
        }
      });
  }

  downloadSample() {}

  loadData() {
    this.loadByPaging();
    this.loadBuilding();
  }

  loadBuilding() {
    this.buildings = [];
    this.buildingService.getListApartment().subscribe(buildings => {
      this.buildings = buildings;
    });
  }

  loadUser() {
    this.users = [];
    this.userService.getListUser().subscribe(users => {
      this.users = users;
      this.dataSource = new MatTableDataSource(users);
      this.dataSource.filterPredicate = (data, filter) => {
        let dataStr =
          data.id + data.address + data.email + data.household.room.name;
        dataStr += data.gender ? 'nam' : 'nữ';
        dataStr += data.name + data.phoneNumber + data.idCard;
        dataStr += data.head ? 'chủ hộ' : '';
        dataStr += data.disable ? 'đã chuyển đi' : 'đang cư trú';
        dataStr += this.dateService.toDateString(data.dateOfBirth, '-');
        return dataStr.toLowerCase().indexOf(filter) !== -1;
      };
      this.dataSource.sort = this.matSort;
      if (this.searchStr !== '') {
        this.search(this.searchStr);
      }
    });
  }

  search(key) {
    if (key === 13) {
      if (this.isFilter) {
        this.dataSource.filter = this.commonService.nonAccentVietnamese(
          this.searchStr.trim()
        );
      } else {
        this.loadByPaging();
      }
    }
  }

  clickFilter() {
    this.selectedUser = -1;
    this.isFilter = true;
    if (
      this.selectedBuilding === null &&
      this.selectedFloor === null &&
      this.selectedRoom === null &&
      (this.status === '' || this.status === undefined)
    ) {
      this.isFilter = false;
      return;
    }

    let buildingId = 0;
    let floorId = 0;
    let roomId = 0;
    if (this.selectedBuilding !== null) {
      buildingId = this.selectedBuilding.id;
    }

    if (this.selectedFloor !== null) {
      floorId = this.selectedFloor.id;
    }

    if (this.selectedRoom !== null) {
      roomId = this.selectedRoom.id;
    }

    if (this.status === undefined || this.status === '') {
      this.status = '-1';
    }
    this.userService
      .filter(
        this.matPaginator.pageIndex,
        this.matPaginator.pageSize,
        buildingId,
        floorId,
        roomId,
        this.status
      )
      .subscribe(result => {
        if (result !== null) {
          this.users = [];
          result.obj.forEach(v => {
            this.users.push(v);
          });
          this.matPaginator.length = result.size;
          this.dataSource = new MatTableDataSource(this.users);
          this.dataSource.sort = this.matSort;
          this.dataSource.filterPredicate = (data, filter) => {
            let dataStr =
              data.id + data.address + data.email + data.household.room.name;
            dataStr += data.gender ? 'nam' : 'nữ';
            dataStr += data.name + data.phoneNumber + data.idCard;
            dataStr += data.head ? 'chủ hộ' : '';
            dataStr +=
              data.disable && data.leave
                ? 'đã chuyển đi'
                : data.disable && !data.leave
                ? 'sắp chuyển đi'
                : 'đang cư trú';
            dataStr += this.dateService.toDateString(data.dateOfBirth, '-');
            dataStr = this.commonService.nonAccentVietnamese(dataStr);
            return dataStr.toLowerCase().indexOf(filter) !== -1;
          };
          if (this.searchStr !== '') {
            this.search(13);
          }
        } else {
          this.isFilter = false;
        }
      });
  }

  cancelFilter() {
    this.isFilter = false;
    this.matPaginator.pageIndex = 0;
    if (this.searchStr !== '') {
      this.search(13);
    } else {
      this.loadByPaging();
    }
  }

  selectBuildingChange(e) {
    this.floors = [];
    this.selectedBuilding = null;
    this.selectedFloor = null;
    this.selectedRoom = null;
    this.autocomplete.setValue('');
    if (e.value !== undefined) {
      const b = this.buildings.find(v => v.id === e.value);
      this.selectedBuilding = b;
      this.floors = b.floors;
    }
  }

  selectFloorChange(e) {
    this.rooms = [];
    this.selectedRoom = null;
    this.selectedFloor = null;
    this.autocomplete.reset();
    if (e.value !== undefined) {
      const f = this.floors.find(v => v.id === e.value);
      this.selectedFloor = f;
      this.rooms = f.rooms;
      this.filteredOptions = this.autocomplete.valueChanges.pipe(
        startWith(''),
        map(value =>
          this.rooms.filter(
            v => v.name.toLowerCase().indexOf(value.toLowerCase()) === 0
          )
        )
      );
    }
  }

  selectedRoomChange(r) {
    this.selectedRoom = r;
  }

  changePage(event) {
    this.reloadData();
  }

  reloadData() {
    this.selectedUser = -1;
    this.cards = [];
    if (this.isFilter) {
      this.clickFilter();
    } else {
      this.loadByPaging();
    }
  }

  addNew() {
    const dialogRef = this.dialog.open(SaveUserComponent, {
      width: '400px',
      data: {
        new: true,
        user: null
      },
      position: { top: '50px' },
      disableClose: true,
      role: 'alertdialog'
    });

    dialogRef.afterClosed().subscribe(result => {
      if (result !== true) {
        return;
      }
      this.reloadData();
    });
  }

  edit(u) {
    const dialogRef = this.dialog.open(SaveUserComponent, {
      width: '400px',
      data: {
        new: false,
        user: u
      },
      position: { top: '50px' },
      disableClose: true,
      role: 'alertdialog'
    });

    dialogRef.afterClosed().subscribe(result => {
      if (result !== true) {
        return;
      }
      this.reloadData();
    });
  }

  leave(u: User) {
    if (u.disable && new Date(u.leaveDate).getTime() <= new Date().getTime()) {
      return;
    }
    const dialogRef = this.dialog.open(RegisterLeaveComponent, {
      width: '400px',
      data: {
        user: u,
        change: u.disable
      },
      position: { top: '50px' },
      disableClose: true,
      role: 'alertdialog'
    });

    dialogRef.afterClosed().subscribe(result => {
      if (result !== true) {
        return;
      }
      this.reloadData();
    });
  }

  selectedUserChange(user: User) {
    if (user.disable && new Date(user.leaveDate).getTime() <= new Date().getTime()) {
      this.selectedUser = -1;
      return;
    }
    this.loadCard(user);
  }

  loadCard(user: User) {
    const id = user.id;
    this.selectedUser = id;
    this.cards = [];
    this.cardService.findAllByUserId(id).subscribe(cards => {
      if (cards != null) {
        cards.forEach(v => {
          this.cards.push(v);
          this.cardDataSource = new MatTableDataSource(this.cards);
          window.scrollTo({
            top: 1000,
            left: 0,
            behavior: 'smooth'
          });
        });
      }
    });
  }

  removeCard(c: Card) {
    const dialogRef = this.dialog.open(DeletePopUpComponent, {
      width: '320px',
      data: {},
      position: { top: '50px' }
    });

    dialogRef.afterClosed().subscribe(result => {
      if (result !== 'ok') {
        return;
      }
      if (c.cardType.name === 'Thẻ ra vào') {
        this.cardService.delete(c.cardNumber).subscribe(
          success => console.log(success),
          error => {
            if (error.error.text === 'Ok') {
              this.notifierService.notify('success', 'Huỷ thẻ thành công');
              this.loadCard(this.dataSource.data.find(value => value.id === this.selectedUser));
            }
          }
        );
      } else {
        this.vehicleService.delete(c.vehicle.plateNumber).subscribe(
          success => console.log(success),
          error => {
            if (error.error.text === 'Ok') {
              this.cardService.delete(c.cardNumber).subscribe(
                s => console.log(s),
                e => {
                  if (e.error.text === 'Ok') {
                    this.notifierService.notify('success', 'Huỷ thẻ thành công');
                    this.loadCard(this.dataSource.data.find (value => value.id === this.selectedUser));
                  }
                }
              );
            }
          }
        );
      }
    });
  }

  createCard() {
    if (this.selectedUser === -1) {
      this.notifierService.notify('info', 'Bạn phải chọn cư dân trước');
      return;
    }
    const u = this.dataSource.data.find(v => v.id === this.selectedUser);
    const dialogRef = this.dialog.open(RegisterCardComponent, {
      width: '400px',
      data: {
        user: u
      },
      position: { top: '50px' },
      disableClose: true
    });

    dialogRef.afterClosed().subscribe(result => {
      if (result !== true) {
        return;
      }
      this.loadCard(u);
    });
  }
}
