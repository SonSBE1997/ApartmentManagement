import { Component, OnInit, ViewChild } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { mergeMap } from 'rxjs/operators';
import { RoomService } from 'src/app/service/room.service';
import { Room } from 'src/entity/Room';
import {
  MatTableDataSource,
  MatSort,
  MatPaginator,
  MAT_DATE_LOCALE,
  MatDialog
} from '@angular/material';
import { Household } from 'src/entity/Household';
import { FormControl } from '@angular/forms';
import { NotifierService } from 'angular-notifier';
import { DateService } from 'src/app/service/date.servce';
import { SaveHouseholdComponent } from './save-household/save-household.component';
import { EmployeeService } from 'src/app/service/employee.service';
import { Employee } from 'src/entity/Employee';

@Component({
  selector: 'app-room-detail',
  templateUrl: './room-detail.component.html',
  styleUrls: ['./room-detail.component.scss'],
  providers: [{ provide: MAT_DATE_LOCALE, useValue: 'vi-VI' }]
})
export class RoomDetailComponent implements OnInit {
  room: Room;
  minDate = new Date(2000, 0, 1);
  maxDate = new Date();
  come = new FormControl();
  leave = new FormControl();
  isFilter = false;
  searchData = '';
  employee: Employee = null;
  displayedColumns: string[] = [
    'fullName',
    'idCard',
    'address',
    'phoneNumber',
    'comeDate',
    'leaveDate',
    'hire',
    'price',
    'deposit',
    'depositDate',
    'status',
    'employee'
  ];
  dataSource: MatTableDataSource<Household>;

  @ViewChild(MatPaginator) paginator: MatPaginator;
  @ViewChild(MatSort) sort: MatSort;

  constructor(
    private activatedRoute: ActivatedRoute,
    private roomService: RoomService,
    private notifierService: NotifierService,
    private dateService: DateService,
    private dialog: MatDialog,
    private employeeService: EmployeeService
  ) {}

  ngOnInit() {
    this.loadData();
    const userLogin = localStorage.getItem('userId');
    this.employeeService.findById(parseInt(userLogin, 10)).subscribe(e => this.employee = e);
  }

  loadData() {
    this.room = null;
    this.activatedRoute.paramMap
      .pipe(
        mergeMap(params => {
          const id = +params.get('id');
          return this.roomService.getRoomById(id);
        })
      )
      .subscribe(r => {
        this.room = r;
        this.room.households.sort((a, b) => {
          return b.id - a.id;
        });
        this.dataSource = new MatTableDataSource(this.room.households);
        this.dataSource.paginator = this.paginator;
        this.dataSource.sort = this.sort;
        this.customFilter();
        if (this.searchData !== '') {
          this.applyFilter(this.searchData);
        }
      });
  }

  customFilter() {
    this.dataSource.filterPredicate = (data, filter) => {
      let dataStr = data.fullName +
        data.deposit + data.idCard +
        data.phoneNumber + data.price +
        data.address + data.employee.name;
      dataStr += data.hire === true ? 'Thuê' : 'Mua';
      dataStr += data.leaveDate === null ? (data.status === true ? 'Đã bàn giao' : 'Chưa bàn giao') : 'Đã chuyển đi';
      dataStr += this.dateService.toDateString(data.comeDate, '-');
      dataStr += this.dateService.toDateString(data.leaveDate, '-');
      dataStr += this.dateService.toDateString(data.depositDate, '-');
      dataStr = dataStr.toLowerCase();
      return dataStr.indexOf(filter) !== -1;
    };
  }

  applyFilter(filterValue: string) {
    this.dataSource.filter = filterValue.trim().toLowerCase();
    if (this.dataSource.paginator) {
      this.dataSource.paginator.firstPage();
    }
    this.searchData = filterValue;
  }

  filter() {
    if (new Date(this.come.value).toLocaleDateString() === '1/1/1970') {
      this.notifierService.notify('warning', 'Bạn chưa nhập ngày đến');
      return;
    }
    if (new Date(this.leave.value).toLocaleDateString() === '1/1/1970') {
      this.leave.patchValue(new Date());
    }

    if (this.come.value > this.leave.value) {
      const temp = this.leave.value;
      this.leave.patchValue(this.come.value);
      this.come.patchValue(temp);
    }

    const comeDate = new Date(this.come.value);
    comeDate.setHours(comeDate.getHours() + 7);
    const leaveDate = new Date(this.leave.value);
    leaveDate.setHours(leaveDate.getHours() + 7);
    this.roomService
      .filterHouseholdByRoomAndDate(
        this.room.id,
        comeDate.toISOString().substr(0, 10),
        leaveDate.toISOString().substr(0, 10)
      )
      .subscribe(h => {
        if (h === null) {
          return;
        }
        this.dataSource = new MatTableDataSource(h);
        this.dataSource.paginator = this.paginator;
        this.dataSource.paginator.firstPage();
        this.dataSource.sort = this.sort;
        this.customFilter();
        if (this.searchData !== '') {
          this.applyFilter(this.searchData);
        }
      });
    this.isFilter = true;
  }

  cancelFilter() {
    this.loadData();
    this.dataSource.paginator.firstPage();
    this.isFilter = false;
  }

  addNew() {
    const dialogRef = this.dialog.open(SaveHouseholdComponent, {
      width: '400px',
      data: {
        room: this.room,
        employee: this.employee
      },
      position: { top: '50px' },
      disableClose: true,
      role: 'alertdialog'
    });

    dialogRef.afterClosed().subscribe(result => {
      if (result !== true) {
        return;
      }
      this.loadData();
    });
  }
}
