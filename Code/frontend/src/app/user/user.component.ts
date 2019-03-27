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
import { SelectionModel } from '@angular/cdk/collections';
import { DateService } from '../service/date.servce';
import { Room } from 'src/entity/Room';
import { Floor } from 'src/entity/Floor';
import { Observable } from 'rxjs';
import { FormControl } from '@angular/forms';
import { startWith, map } from 'rxjs/operators';

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

  @ViewChild(MatPaginator) matPaginator: MatPaginator;
  @ViewChild(MatSort) matSort: MatSort;
  dataSource: MatTableDataSource<User>;
  selection = new SelectionModel<User>(true, []);

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
    private dateService: DateService
  ) {}

  ngOnInit() {
    this.loadData();
  }

  downloadSample() {}

  loadData() {
    this.loadUser();
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
      this.dataSource.paginator = this.matPaginator;
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

  search(filterValue: string) {
    this.searchStr = filterValue.trim().toLowerCase();
    this.dataSource.filter = this.searchStr;
  }

  isAllSelected() {
    const numSelected = this.selection.selected.length;
    if (this.dataSource === undefined) {
      return false;
    }
    const numRows = this.dataSource.data.length;
    return numSelected === numRows;
  }

  masterToggle() {
    this.isAllSelected()
      ? this.selection.clear()
      : this.dataSource.data.forEach(row => this.selection.select(row));
  }

  checkboxLabel(user?: User): string {
    if (!user) {
      return `${this.isAllSelected() ? 'select' : 'deselect'} all`;
    }
    const index = this.dataSource.data.indexOf(user);
    return `${
      this.selection.isSelected(user) ? 'deselect' : 'select'
    } row ${index + 1}`;
  }

  clickFilter() {
    this.isFilter = true;
    let data = this.dataSource.data;
    if (this.selectedBuilding !== null && this.selectedFloor === null) {
      data = data.filter(
        v => v.household.room.building.id === this.selectedBuilding.id
      );
    } else if (this.selectedFloor !== null && this.selectedRoom === null) {
      data = data.filter(
        v => v.household.room.floor.id === this.selectedFloor.id
      );
    } else if (this.selectedRoom !== null) {
      data = data.filter(v => v.household.room.id === this.selectedRoom.id);
    }

    if (!(this.status === undefined) && this.status !== '') {
      const disable = this.status !== 'Đang cư trú';
      data = data.filter(v => v.disable === disable);
    }

    this.dataSource.data = data;

    if (this.searchStr !== '') {
      this.search(this.searchStr);
    }
  }

  cancelFilter() {
    this.isFilter = false;
    this.loadUser();
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
    this.selectedFloor = null;
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

  addNew() {
    console.log(this.selection);
  }
}
