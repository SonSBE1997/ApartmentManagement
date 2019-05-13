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
import { HouseholdService } from 'src/app/service/household.service';
import { CancelComeComponent } from './cancel-come/cancel-come.component';
import { LeaveComponent } from './leave/leave.component';
import { Maintenance } from 'src/entity/Maintenance';
import { MaintenanceService } from 'src/app/service/maintenance.service';
import { RepairDtlComponent } from './repair-dtl/repair-dtl.component';

@Component({
  selector: 'app-room-detail',
  templateUrl: './room-detail.component.html',
  styleUrls: ['./room-detail.component.scss'],
  providers: [{ provide: MAT_DATE_LOCALE, useValue: 'vi-VI' }]
})
export class RoomDetailComponent implements OnInit {
  room: Room;
  maintenances: Maintenance[] = [];
  minDate = new Date(2000, 0, 1);
  maxDate = new Date();
  come = new FormControl();
  leave = new FormControl();
  isFilter = false;
  searchData = '';
  employee: Employee = null;
  checked = false;
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
    private employeeService: EmployeeService,
    private householdService: HouseholdService,
    private maintenanceService: MaintenanceService
  ) {}

  ngOnInit() {
    this.loadData();
    const userLogin = localStorage.getItem('userId');
    this.employeeService
      .findById(parseInt(userLogin, 10))
      .subscribe(e => (this.employee = e));
  }

  loadData() {
    this.room = null;
    this.activatedRoute.paramMap
      .pipe(
        mergeMap(params => {
          const id = +params.get('id');
          if (this.checked === false) {
            this.maintenanceService.findByRoom(id).subscribe(v => {
              this.checked = true;
              v.forEach(m => {
                m.supervisor = m.persons[0].employee.name;
                this.maintenances.push(m);
              });
            });
          }
          return this.roomService.getRoomById(id);
        })
      )
      .subscribe(r => {
        r.households.sort((a, b) => {
          return (
            new Date(b.comeDate).getTime() - new Date(a.comeDate).getTime()
          );
        });
        this.room = r;
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
      let dataStr =
        data.fullName +
        data.deposit +
        data.idCard +
        data.phoneNumber +
        data.price +
        data.address +
        data.employee.name;
      dataStr += data.hire === true ? 'Thuê' : 'Mua';
      dataStr +=
        data.leaveDate === null
          ? data.status === true
            ? 'Đã bàn giao'
            : 'Chưa bàn giao'
          : 'Đã chuyển đi';
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
    if (this.room.households.length > 0) {
      const hh = this.room.households[0];
      if (
        hh.leaveDate === null ||
        new Date(hh.leaveDate).getTime() > new Date().getTime()
      ) {
        this.notifierService.notify(
          'info',
          'Căn hộ đang có người ở, không thể thêm mới'
        );
        return;
      }
    }
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

  cancelCome() {
    const size = this.room.households.length;
    if (size === 0) {
      return;
    }
    const h = this.room.households[0];

    if (h.leaveDate !== null) {
      if (new Date(h.leaveDate).getTime() > new Date().getTime()) {
        this.notifierService.notify('info', 'Đã đăng ký chuyển đi');
      } else {
        this.notifierService.notify(
          'info',
          'Căn hộ đang không có người đăng ký chuyển đến / đang ở'
        );
      }
      return;
    }
    h.room = this.room;
    const dt: Household = {
      ...h
    };
    if (h.status && new Date(h.comeDate).getDate() !== new Date().getDate()) {
      const leaveDialog = this.dialog.open(LeaveComponent, {
        width: '400px',
        data: {
          household: dt
        },
        position: { top: '50px' },
        disableClose: true,
        role: 'alertdialog'
      });

      leaveDialog.afterClosed().subscribe(result => {
        if (result !== true) {
          return;
        }
        this.loadData();
      });
    } else {
      const dialogRef = this.dialog.open(CancelComeComponent, {
        width: '400px',
        data: '',
        position: { top: '50px' }
      });

      dialogRef.afterClosed().subscribe(result => {
        if (result !== 'ok') {
          return;
        }
        this.householdService.cancelCome(dt.id).subscribe(
          s => {
            if (s === 'Ok') {
              this.notifierService.notify('success', 'Huỷ thành công');
              this.loadData();
            } else {
              this.notifierService.notify('error', 'Huỷ không thành công');
            }
          },
          e => {
            console.log(e);
          }
        );
      });
    }
  }

  clickShowDetailRepair() {
    if (this.maintenances.length === 0) {
      this.notifierService.notify('info', 'Căn hộ chưa sửa chữa lần nào');
      return;
    }
    this.dialog.open(RepairDtlComponent, {
      width: '700px',
      data: this.maintenances,
      position: { top: '50px' }
    });
  }
}
