import { Component, OnInit } from '@angular/core';
import { ServiceType } from 'src/entity/ServiceType';
import { DateAdapter, MAT_DATE_LOCALE, MAT_DATE_FORMATS, MatDatepicker } from '@angular/material';
import { MomentDateAdapter } from '@angular/material-moment-adapter';
import { MY_FORMATS } from '../services/services.component';
import * as _moment from 'moment';
import { Moment } from 'moment';
import { FormControl } from '@angular/forms';
import { ServiceService } from '../service/service.service';
import { DateService } from '../service/date.servce';
const moment = _moment;

@Component({
  selector: 'app-statistic',
  templateUrl: './statistic.component.html',
  styleUrls: ['./statistic.component.scss'],
  providers: [
    {
      provide: DateAdapter,
      useClass: MomentDateAdapter,
      deps: [MAT_DATE_LOCALE]
    },
    { provide: MAT_DATE_FORMATS, useValue: MY_FORMATS }
  ]
})
export class StatisticComponent implements OnInit {
  public barChartOptions = {
    scaleShowVerticalLines: false,
    responsive: true
  };
  public barChartLabels = [];
  public barChartType = 'bar';
  public barChartLegend = true;
  public barChartData = [
    { data: [], label: 'Đã thanh toán' },
    { data: [], label: 'Chưa thanh toán' }
  ];

  public barChartLabels1 = [];
  public barChartType1 = 'bar';
  public barChartLegend1 = true;
  public barChartData1 = [
    { data: [], label: 'Tổng tiền đã thanh toán' },
    { data: [], label: 'Tổng tiền chưa thanh toán' }
  ];

  public doughnutChartLabels = [
    'Tổng tiền đã thanh toán',
    'Tổng tiền chưa thanh toán'
  ];
  public doughnutChartData = [];
  public doughnutChartType = 'doughnut';

  public pieChartLabels = ['Đã thanh toán', 'Chưa thanh toán'];
  public pieChartData = [];
  public pieChartType = 'pie';

  public pieChartLabels1 = ['Tỉ lệ đã thanh toán', 'Tỉ lệ chưa thanh toán'];
  public pieChartData1 = [];
  public pieChartType1 = 'pie';

  types: ServiceType[] = [];
  cMonth = new FormControl(moment());
  selectedType = -1;
  month = '';
  selectedTypeName = '';
  isOk = false;
  isOk1 = false;
  constructor(
    private serviceService: ServiceService,
    private dateService: DateService
  ) {}

  ngOnInit() {
    this.types = [];
    this.serviceService.findAllType().subscribe(t => {
      if (t != null) {
        t.forEach(v => {
          if (v.increase !== '' && v.increase !== null) {
            v.priceList = v.increase
              .split(';')
              .sort(
                (a, b) =>
                  parseInt(a.split(' - ')[0], 10) -
                  parseInt(b.split(' - ')[0], 10)
              );
          }
          this.types.push(v);
        });
      }
    });
    this.month = this.dateService.toDateString(new Date(), '-').substr(3);
    this.loadData();
  }

  loadData() {
    this.barChartData[0].data = [];
    this.barChartData[1].data = [];
    this.barChartData1[0].data = [];
    this.barChartData1[1].data = [];
    this.barChartLabels = [];
    this.barChartLabels1 = [];
    this.serviceService.paidByMonth(this.month, 1).subscribe(v => {
      v.forEach(dt => {
        this.barChartLabels.push(dt[0]);
        this.barChartData[0].data.push(dt[1]);
      });
      this.serviceService.paidByMonth(this.month, 0).subscribe(vl => {
        vl.forEach(dt => {
          this.barChartData[1].data.push(dt[1]);
        });
      });
    });

    this.serviceService.pricePaidByMonth(this.month, 1).subscribe(v => {
      v.forEach(dt => {
        this.barChartLabels1.push(dt[0]);
        this.barChartData1[0].data.push(dt[1]);
      });
      this.serviceService.pricePaidByMonth(this.month, 0).subscribe(vl => {
        vl.forEach(dt => {
          this.barChartData1[1].data.push(dt[1]);
        });
      });
    });
  }

  loadDataByType() {
    this.isOk = false;
    this.isOk1 = false;
    this.doughnutChartData = [];
    this.pieChartData = [];
    this.pieChartData1 = [];
    this.serviceService
      .pricePaidByMonthAndType(this.month, this.selectedType)
      .subscribe(v => {
        let sum = 0;
        v.forEach(dt => {
          if (dt[0]) {
            this.doughnutChartData.unshift(dt[1]);
          } else {
            this.doughnutChartData.push(dt[1]);
          }
          sum += dt[1];
        });
        if (sum !== 0) {
           this.isOk = true;
        }
      });

    this.serviceService
      .paidByMonthAndType(this.month, this.selectedType)
      .subscribe(v => {
        let sum = 0;
        v.forEach(dt => {
          if (dt[0]) {
            this.pieChartData.unshift(dt[1]);
          } else {
            this.pieChartData.push(dt[1]);
          }
          sum += dt[1];
        });
        if (sum !== 0) {
          this.isOk1 = true;
        }
        if (sum > 0) {
          this.pieChartData1 = this.pieChartData.map(d =>
            parseFloat(((d / sum) * 100).toFixed(2))
          );
        }
      });
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

  changeType(e) {
    if (e.value === '-1') {
      this.selectedType = -1;
      this.selectedTypeName = '';
      this.show();
      return;
    }
    this.selectedType = parseInt(e.value, 10);
    this.show();
    this.selectedTypeName = this.types.find(
      v => v.id === this.selectedType
    ).name;
  }

  show() {
    const date = this.cMonth.value;
    if (date !== '' && date !== null && date !== undefined) {
      this.month = '0' + (date.month() + 1) + '-' + date.year();
    } else {
      this.cMonth.setValue(moment());
      this.month = this.dateService.toDateString(new Date(), '-').substr(3);
    }

    if (this.selectedType === -1) {
      this.loadData();
    } else {
      this.loadDataByType();
    }
  }
}
