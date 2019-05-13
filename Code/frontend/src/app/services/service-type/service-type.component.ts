import { Component, OnInit, ViewChild } from '@angular/core';
import {
  MatTableDataSource,
  MatPaginator,
  MatSort,
  MatDialog
} from '@angular/material';
import { Dept } from 'src/entity/Dept';
import { NotifierService } from 'angular-notifier';
import { ServiceService } from 'src/app/service/service.service';
import { ServiceType } from 'src/entity/ServiceType';
import { DeletePopUpComponent } from 'src/app/common/delete-pop-up/delete-pop-up.component';
import { SaveTypeComponent } from './save-type/save-type.component';

@Component({
  selector: 'app-service-type',
  templateUrl: './service-type.component.html',
  styleUrls: ['./service-type.component.scss']
})
export class ServiceTypeComponent implements OnInit {
  types: ServiceType[] = [];
  displayedColumns: string[] = ['name', 'price', 'unit', 'supplier', 'action'];
  dataSource: MatTableDataSource<ServiceType>;

  @ViewChild(MatPaginator) paginator: MatPaginator;
  @ViewChild(MatSort) sort: MatSort;

  constructor(
    private serviceService: ServiceService,
    private notifierService: NotifierService,
    private dialog: MatDialog
  ) {}

  ngOnInit() {
    this.loadData();
  }

  loadData() {
      this.types = [];
      this.serviceService.findAllType().subscribe(v => {
        if (v != null) {
          v.forEach(t => {
            this.types.push(t);
          });
          this.dataSource = new MatTableDataSource(this.types);
          this.dataSource.paginator = this.paginator;
          this.dataSource.sort = this.sort;
        }
      });
  }

  search(filterValue: string) {
    this.dataSource.filter = filterValue.trim().toLowerCase();
  }

  remove(t: ServiceType) {
    if (t.services.length > 0) {
      this.notifierService.notify('error', 'Không thể xoá loại này');
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

      this.serviceService.deleteType(t.id).subscribe(
        success => {
          if (success === 'Ok') {
            this.notifierService.notify('success', 'Xoá thành công');
            let data = this.dataSource.data;
            data = data.filter(v => v.id !== t.id);
            this.dataSource.data = data;
            if (
              this.paginator.pageSize * this.paginator.pageIndex >
              data.length
            ) {
              this.paginator.previousPage();
            }
          } else {
            this.notifierService.notify('success', 'Xoá thất bại');
          }
        },
        error => {
          console.log(error);
        }
      );
    });
  }

  edit(type) {
    const dialogRef = this.dialog.open(SaveTypeComponent, {
      width: '400px',
      data: type,
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
    const dialogRef = this.dialog.open(SaveTypeComponent, {
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
