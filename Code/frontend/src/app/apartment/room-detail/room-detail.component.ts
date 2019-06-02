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
import { User } from 'src/entity/User';
import { DataSource } from '@angular/cdk/table';

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
  selectedHousehold = -1;
  displayedColumns: string[] = [
    'fullName',
    'idCard',
    'address',
    'phoneNumber',
    'comeDate',
    'leaveDate',
    'hire',
    'statusStr',
    'employee'
  ];
  dataSource: MatTableDataSource<Household>;
  userDataSource: MatTableDataSource<User>;

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
        this.room.households.forEach(v => {
          switch (v.status) {
            case '0':
              v.statusStr = 'Chưa bàn giao';
              break;
            case '1':
              v.statusStr = 'Đã bàn giao';
              break;
            case '2':
              v.statusStr = 'Đã chuyển đi';
              break;
            case '3':
              v.statusStr = 'Sẽ chuyển đi';
              break;
            case '4':
              v.statusStr = 'Đã huỷ chuyển đến';
              break;
            default:
              break;
          }
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
      let dataStr =
        data.fullName +
        data.idCard +
        data.phoneNumber +
        data.address +
        data.employee.name;
      dataStr += data.hire === true ? 'Thuê' : 'Mua';
      dataStr += data.statusStr;
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
      if (hh.status === '1' || hh.status === '3') {
        this.notifierService.notify(
          'info',
          'Căn hộ đang có người ở, không thể đăng ký mới'
        );
        return;
      } else if (hh.status === '0') {
               this.notifierService.notify(
                 'info',
                 'Căn hộ đã được đặt cọc, không thể đăng ký mới'
               );
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

  cancel() {
    const size = this.room.households.length;
    if (size === 0) {
      return;
    }
    const h = this.room.households[0];
    if (h.status === '2' || h.status === '4') {
      this.notifierService.notify(
        'info',
        'Căn hộ đang không có người đăng ký chuyển đến / đang ở'
      );
      return;
    }

    // if (h.status === '3') {
    //   this.notifierService.notify('info', 'Đã đăng ký chuyển đi');
    //   return;
    // }

    h.room = this.room;
    const dt: Household = {
      ...h
    };
    if (h.status === '1' || h.status === '3') {
      const leaveDialog = this.dialog.open(LeaveComponent, {
        width: '400px',
        data: {
          household: dt,
          status: h.status
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
      const type = h.status === '1' ? 'chuyển đến' : '';
      const dialogRef = this.dialog.open(CancelComeComponent, {
        width: '400px',
        data: type,
        position: { top: '50px' }
      });

      dialogRef.afterClosed().subscribe(result => {
        if (result !== 'ok') {
          return;
        }

        this.householdService.cancel(dt.id, type).subscribe(
          s => {
            if (s === 'Ok') {
              this.notifierService.notify('success', 'Huỷ thành công');
              this.loadData();
            } else {
              this.notifierService.notify(
                'error',
                'Huỷ không thành công'
              );
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

  selectHouseholdChange(row: Household) {
    if (row.statusStr === '0' || row.statusStr === '4') {
      this.selectedHousehold = -1;
      return;
    }
    this.selectedHousehold = row.id;
    const users = row.users;
    this.userDataSource = new MatTableDataSource(users);
    setTimeout(() => {
      window.scrollTo({
        top: 1000,
        left: 0,
        behavior: 'smooth'
      });
    }, 1000);
  }
}
