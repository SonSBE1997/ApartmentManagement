import { Component, OnInit, ViewChild } from '@angular/core';
import {
  MatTableDataSource,
  MatPaginator,
  MatSort,
  MatDialog
} from '@angular/material';
import { Dept } from 'src/entity/Dept';
import { NotifierService } from 'angular-notifier';
import { DeptService } from '../service/dept.service';
import { SaveDeptComponent } from './save-dept/save-dept.component';
import { DeletePopUpComponent } from '../common/delete-pop-up/delete-pop-up.component';

@Component({
  selector: 'app-dept',
  templateUrl: './dept.component.html',
  styleUrls: ['./dept.component.scss']
})
export class DeptComponent implements OnInit {
  depts: Dept[] = [];
  displayedColumns: string[] = ['id', 'name', 'action'];
  dataSource: MatTableDataSource<Dept>;

  @ViewChild(MatPaginator) paginator: MatPaginator;
  @ViewChild(MatSort) sort: MatSort;

  constructor(
    private deptService: DeptService,
    private notifierService: NotifierService,
    private dialog: MatDialog
  ) {}

  ngOnInit() {
    this.loadData();
  }

  loadData() {
    this.depts = [];
    this.deptService.findAll().subscribe(depts => {
      depts.forEach(v => {
        this.depts.push(v);
      });
      this.dataSource = new MatTableDataSource(this.depts);
      this.dataSource.paginator = this.paginator;
      this.dataSource.sort = this.sort;
    });
  }

  search(filterValue: string) {
    this.dataSource.filter = filterValue.trim().toLowerCase();
  }

  remove(dept: Dept) {
    if (dept.employees.length > 0) {
      this.notifierService.notify('error', 'Không thể xoá phòng ban này');
      return;
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

      this.deptService.delete(dept.id).subscribe(
        success => console.log(success),
        error => {
          if (error.error.text === 'Ok') {
            this.notifierService.notify('success', 'Xoá thành công');
            let data = this.dataSource.data;
            data = data.filter(v => v.id !== dept.id);
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

  edit(dept) {
    const dialogRef = this.dialog.open(SaveDeptComponent, {
      width: '400px',
      data: dept,
      position: { top: '150px' },
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

  add() {
    const dialogRef = this.dialog.open(SaveDeptComponent, {
      width: '400px',
      data: null,
      position: { top: '150px' },
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
