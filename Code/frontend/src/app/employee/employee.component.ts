import { Component, OnInit, ViewChild } from '@angular/core';
import { Dept } from 'src/entity/Dept';
import { Employee } from 'src/entity/Employee';
import {
  MatTableDataSource,
  MatPaginator,
  MatSort,
  MatDialog
} from '@angular/material';
import { DeptService } from '../service/dept.service';
import { NotifierService } from 'angular-notifier';
import { EmployeeService } from '../service/employee.service';
import { DeletePopUpComponent } from '../common/delete-pop-up/delete-pop-up.component';
import { SaveEmpComponent } from './save-emp/save-emp.component';
import { DateService } from '../service/date.servce';

@Component({
  selector: 'app-employee',
  templateUrl: './employee.component.html',
  styleUrls: ['./employee.component.scss']
})
export class EmployeeComponent implements OnInit {
  emps: Employee[] = [];
  displayedColumns: string[] = [
    'dept',
    'name',
    'gender',
    'dateOfBirth',
    'idCard',
    'phoneNumber',
    'email',
    'address',
    'username',
    'role',
    'action'
  ];
  dataSource: MatTableDataSource<Employee>;

  @ViewChild(MatPaginator) paginator: MatPaginator;
  @ViewChild(MatSort) sort: MatSort;

  constructor(
    private deptService: DeptService,
    private notifierService: NotifierService,
    private dialog: MatDialog,
    private empService: EmployeeService,
    private dateService: DateService
  ) {}

  ngOnInit() {
    this.loadEmployee();
  }

  loadEmployee() {
    this.emps = [];
    this.empService.findAll().subscribe(emps => {
      emps.forEach(v => {
        this.emps.push(v);
      });
      this.dataSource = new MatTableDataSource(this.emps);
      this.dataSource.paginator = this.paginator;
      this.dataSource.sort = this.sort;
      this.dataSource.filterPredicate = (data, filter) => {
        let dataStr = data.id + data.name;
        dataStr += data.dept.name + this.dateService.toDateString(data.dateOfBirth, '-');
        dataStr += data.email + data.gender ? 'Nam' : 'Nữ';
        dataStr += data.idCard + data.phoneNumber + data.username;
        dataStr += data.role === 'Manager' ? 'Quản lý' : 'Bảo vệ';
        dataStr = dataStr.toLowerCase();
        return dataStr.indexOf(filter) !== -1;
      };
    });
  }

  search(filterValue: string) {
    this.dataSource.filter = filterValue.trim().toLowerCase();
  }

  remove(emp: Employee) {
    const loginUserId = localStorage.getItem('userId');
    if (loginUserId !== null ) {
      if (loginUserId === emp.id + '') {
        this.notifierService.notify('error', 'Không thể xoá tài khoản đang đăng nhập.');
        return;
      }
    }

    const dialogRef = this.dialog.open(DeletePopUpComponent, {
      width: '320px',
      data: '',
      position: { top: '150px' }
    });

    dialogRef.afterClosed().subscribe(result => {
      if (result !== 'ok') {
        return;
      }

      this.empService.delete(emp.id).subscribe(
        success => console.log(success),
        error => {
          if (error.error.text === 'Ok') {
            this.notifierService.notify('success', 'Xoá thành công');
            let data = this.dataSource.data;
            data = data.filter(v => v.id !== emp.id);
            this.dataSource.data = data;
            if (
              this.paginator.pageSize * this.paginator.pageIndex >
              data.length
            ) {
              this.paginator.previousPage();
            }
          }
        }
      );
    });
  }

  edit(emp) {
    const dialogRef = this.dialog.open(SaveEmpComponent, {
      width: '400px',
      data: {
        new: false,
        employee: emp
      },
      position: { top: '50px' },
      disableClose: true,
      role: 'alertdialog'
    });

    dialogRef.afterClosed().subscribe(result => {
      if (result !== true) {
        return;
      }
      this.loadEmployee();
    });
  }

  add() {
    const dialogRef = this.dialog.open(SaveEmpComponent, {
      width: '400px',
      data: {
        new: true,
        employee: null
      },
      position: { top: '50px' },
      disableClose: true,
      role: 'alertdialog'
    });

    dialogRef.afterClosed().subscribe(result => {
      if (result !== true) {
        return;
      }
      this.loadEmployee();
    });
  }
}
