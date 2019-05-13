import { Component, OnInit, ViewChild } from '@angular/core';
import { Maintenance } from 'src/entity/Maintenance';
import { MatTableDataSource, MatPaginator, MatSort, MatDialog, MAT_DATE_LOCALE } from '@angular/material';
import { NotifierService } from 'angular-notifier';
import { MaintenanceService } from 'src/app/service/maintenance.service';
import { DateService } from 'src/app/service/date.servce';
import { FormControl } from '@angular/forms';
import { SDetailComponent } from './s-detail/s-detail.component';
import { SaveSDetailComponent } from './save-s-detail/save-s-detail.component';

@Component({
  selector: 'app-staircase',
  templateUrl: './staircase.component.html',
  styleUrls: ['./staircase.component.scss'],
  providers: [{ provide: MAT_DATE_LOCALE, useValue: 'vi-VI' }]
})
export class StaircaseComponent implements OnInit {
  maintenances: Maintenance[] = [];
  backup: Maintenance[] = [];
  displayedColumns: string[] = [
    'maintenanceDate',
    'description',
    'numberPersonnel',
    'supervisor',
    'action'
  ];
  minDate = new Date(2000, 0, 1);
  maxDate = new Date();
  come = new FormControl();
  leave = new FormControl();
  isFilter = false;
  searchData = '';

  dataSource: MatTableDataSource<Maintenance>;

  @ViewChild(MatPaginator) paginator: MatPaginator;
  @ViewChild(MatSort) sort: MatSort;

  constructor(
    private notifierService: NotifierService,
    private dialog: MatDialog,
    private maintenanceService: MaintenanceService,
    private dateService: DateService
  ) {}

  ngOnInit() {
    this.loadData();
  }

  loadData() {
    this.maintenances = [];
    this.maintenanceService.findByDeviceGroup(1).subscribe(m => {
      m.forEach(v => {
        v.persons.forEach(p => {
          if (p.supervisor) {
            v.supervisor = p.employee.name;
          }
        });
        this.maintenances.push(v);
        this.backup.push(v);
      });
      this.dataSource = new MatTableDataSource(this.maintenances);
      this.dataSource.paginator = this.paginator;
      this.dataSource.sort = this.sort;
      this.dataSource.filterPredicate = (data, filter) => {
        let dataStr = this.dateService.toDateString(data.maintenanceDate, '-');
        dataStr += data.description + data.supervisor;
        dataStr = dataStr.toLowerCase();
        return dataStr.indexOf(filter) !== -1;
      };
      if (this.searchData !== '') {
        this.applySearch(this.searchData);
      }
    });
  }

  search(event) {
    if (event.keyCode === 13) {
      const filterValue = event.target.value;
      this.applySearch(filterValue);
    }
  }

  applySearch(filterValue: string) {
    this.dataSource.filter = filterValue.trim().toLowerCase();
    if (this.dataSource.paginator) {
      this.dataSource.paginator.firstPage();
    }
    this.searchData = filterValue;
  }

  filter() {
    if (new Date(this.come.value).toLocaleDateString() === '1/1/1970') {
      this.notifierService.notify('warning', 'Bạn chưa nhập ngày kiểm tra');
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

    this.maintenances = this.maintenances.filter(
      v => {
        const time = new Date(v.maintenanceDate).getTime();
        return (
          time >= comeDate.getTime() && time <= leaveDate.getTime()
        );
      }
    );
    this.dataSource.data = this.maintenances;
    if (this.searchData !== '') {
      this.applySearch(this.searchData);
    }
    this.isFilter = true;
  }

  cancelFilter() {
    this.maintenances = [];
    this.backup.forEach(v => this.maintenances.push(v));
    this.dataSource.data = this.maintenances;
    if (this.searchData !== '') {
      this.applySearch(this.searchData);
    }
    this.dataSource.paginator.firstPage();
    this.isFilter = false;
  }

  showDetail(row) {
     const dialogRef = this.dialog.open(SDetailComponent, {
       width: '600px',
       data: row,
       position: { top: '50px' }
     });

     dialogRef.afterClosed().subscribe(result => {
       if (result !== true) {
         return;
       }
     });
  }

  save() {
      const dialogRef = this.dialog.open(SaveSDetailComponent, {
        width: '700px',
        data: '',
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
