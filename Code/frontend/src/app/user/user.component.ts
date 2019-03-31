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
    private commonService: CommonServiceService
  ) {}

  ngOnInit() {
    // this.loadData();
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
        this.dataSource.filter = this.commonService.nonAccentVietnamese(this.searchStr.trim());
      } else {
        this.loadByPaging();
      }
    }
  }

  clickFilter() {
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

    this.matPaginator.pageIndex = 0;
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
    this.loadByPaging();
  }

  addNew() {}
}
