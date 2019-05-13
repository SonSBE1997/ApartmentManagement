import { Component, OnInit, ViewChild } from '@angular/core';
import { FileService } from '../service/file.service';
import {
  MatDialog,
  MatTableDataSource,
  MatPaginator,
  MatSort,
  MAT_DATE_FORMATS,
  MAT_DATE_LOCALE,
  DateAdapter,
  MatDatepicker
} from '@angular/material';
import { ImportServiceComponent } from './import-service/import-service.component';
import { ServiceService } from '../service/service.service';
import { ServiceType } from 'src/entity/ServiceType';
import { Service } from 'src/entity/Service';
import { NotifierService } from 'angular-notifier';
import { SaveServiceComponent } from './save-service/save-service.component';
import { ConfirmPaymentComponent } from './confirm-payment/confirm-payment.component';
import { DetailServiceComponent } from './detail-service/detail-service.component';
import { FormControl } from '@angular/forms';
import { MomentDateAdapter } from '@angular/material-moment-adapter';
import * as _moment from 'moment';
import { Moment } from 'moment';
const moment = _moment;
export const MY_FORMATS = {
  parse: {
    dateInput: 'MM/YYYY'
  },
  display: {
    dateInput: 'MM-YYYY',
    monthYearLabel: 'MMM YYYY',
    dateA11yLabel: 'LL',
    monthYearA11yLabel: 'MMMM YYYY'
  }
};
@Component({
  selector: 'app-services',
  templateUrl: './services.component.html',
  styleUrls: ['./services.component.scss'],
  providers: [
    {
      provide: DateAdapter,
      useClass: MomentDateAdapter,
      deps: [MAT_DATE_LOCALE]
    },

    { provide: MAT_DATE_FORMATS, useValue: MY_FORMATS }
  ]
})
export class ServicesComponent implements OnInit {
  types: ServiceType[] = [];
  services: Service[] = [];
  backup: Service[] = [];
  status = '-1';
  isFilter = false;
  searchStr = '';
  cMonth = new FormControl(moment());
  displayedColumns: string[] = [
    'serviceType',
    'room',
    'fullName',
    'collectMonth',
    'paymentDate',
    'price',
    'description',
    'employee',
    'action'
  ];

  dataSource: MatTableDataSource<Service>;

  @ViewChild(MatPaginator) paginator: MatPaginator;
  @ViewChild(MatSort) sort: MatSort;
  constructor(
    private fileService: FileService,
    private dialog: MatDialog,
    private notifierService: NotifierService,
    private serviceService: ServiceService
  ) {}

  ngOnInit() {
    this.loadData();
  }

  loadData() {
    this.types = [];
    this.serviceService.findAllType().subscribe(t => {
      if (t != null) {
        t.forEach(v => {
          this.types.push(v);
        });
      }
    });

    this.loadService();
  }

  loadService() {
    this.services = [];
    this.serviceService.findAll().subscribe(s => {
      if (s != null) {
        s.forEach(v => {
          const hh = v.room.households.find(
            h => h.leaveDate === null && h.status === true
          );
          if (hh !== null && hh !== undefined) {
            v.fullName = hh.fullName;
          }
          this.services.push(v);
          this.backup.push(v);
        });
        this.dataSource = new MatTableDataSource(this.services);
        this.customSearch();
        this.dataSource.paginator = this.paginator;
        this.dataSource.sort = this.sort;
      }
    });
  }

  customSearch() {
    this.dataSource.filterPredicate = (data, filter) => {
      let dataStr =
        data.collectMonth +
        data.description +
        data.fullName +
        data.paymentDate +
        data.price;
      dataStr +=
        data.room.name +
        '' +
        data.room.floor.name +
        '-' +
        data.room.building.name;
      dataStr = dataStr.toLowerCase();
      return dataStr.indexOf(filter) !== -1;
    };
  }

  search(filterValue: string) {
    this.dataSource.filter = filterValue.trim().toLowerCase();
    this.searchStr = filterValue;
  }

  addNew() {
    const dialogRef = this.dialog.open(SaveServiceComponent, {
      width: '500px',
      data: {
        types: this.types
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

  downloadSample() {
    this.fileService.generateServiceExcel().subscribe(
      s => {
        if (s === 'Ok') {
          this.fileService.downloadSample('Service.xlsx');
        } else {
          this.notifierService.notify('error', 'Tải tệp mẫu gặp lỗi :(');
        }
      },
      e => console.log(e)
    );
  }

  import() {
    const dialogRef = this.dialog.open(ImportServiceComponent, {
      data: 'upload-service',
      width: '450px',
      position: { top: '50px' }
    });

    dialogRef.afterClosed().subscribe(result => {
      if (result === true) {
        this.loadData();
      }
    });
  }

  payment(row: Service) {
    if (row.paid) {
      return;
    }
    const dialogRef = this.dialog.open(ConfirmPaymentComponent, {
      width: '370px',
      data: '',
      position: { top: '150px' }
    });

    dialogRef.afterClosed().subscribe(result => {
      if (result !== 'ok') {
        return;
      }
      row.paid = true;
      row.paymentDate = new Date();
      this.serviceService.save(row).subscribe(s => {
        if (s === 'Ok') {
          this.notifierService.notify('success', 'Thanh toán thành công');
          this.loadService();
        } else {
          this.notifierService.notify('error', 'Thanh toán thất bại');
        }
      });
    });
  }

  detail(row: Service) {
    const dialogRef = this.dialog.open(DetailServiceComponent, {
      width: '500px',
      data: row,
      position: { top: '50px' }
    });

    dialogRef.afterClosed().subscribe(result => {
      if (result !== true) {
        return;
      }
    });
  }

  clickFilter() {
    console.log(this.status);

    const date = this.cMonth.value;
    let str = '';
    if (date !== '' && date !== null && date !== undefined) {
      str = date.month() + 1 + '-' + date.year();
    }

    if (this.status === '-1' && str === '') {
      return;
    }

    let data = this.dataSource.data;
    if (this.status !== '-1') {
      const sttus = this.status === '0' ? true : false;
      data = data.filter(v => v.paid === sttus);
    }
    if (str !== '') {
      console.log(str);
      data = data.filter(v => v.collectMonth.includes(str));
    }
    this.dataSource.data = data;
    this.paginator.firstPage();
    if (this.searchStr !== '') {
      this.search(this.searchStr);
    }
    this.isFilter = true;
  }

  clearFilter() {
    let data = this.dataSource.data;
    data = [];
    this.backup.forEach(v => data.push(v));
    this.dataSource.data = data;
    this.paginator.firstPage();
    if (this.searchStr !== '') {
      this.search(this.searchStr);
    }
    this.isFilter = false;
  }

  chosenYearHandler(normalizedYear: Moment) {
    let ctrlValue = this.cMonth.value;
    if (
      ctrlValue === '' ||
      (ctrlValue === '' && ctrlValue !== null) ||
      ctrlValue !== undefined
    ) {
      ctrlValue = moment();
    }
    ctrlValue.year(normalizedYear.year());
    this.cMonth.setValue(ctrlValue);
  }

  chosenMonthHandler(
    normalizedMonth: Moment,
    datepicker: MatDatepicker<Moment>
  ) {
    const ctrlValue = this.cMonth.value;
    ctrlValue.month(normalizedMonth.month());
    this.cMonth.setValue(ctrlValue);
    datepicker.close();
  }

  remind(data) {
    if (data.paid) {
      return;
    }

    this.serviceService.remind(data.id).subscribe(s => {
        if (s === 'Ok') {
          this.notifierService.notify('success', 'Đã gửi mail nhắc nhở thành công');
        } else {
          this.notifierService.notify('warning', 'Đã gửi mail nhắc nhở thất bại');
        }
      },
      e => console.log(e));
  }
}
