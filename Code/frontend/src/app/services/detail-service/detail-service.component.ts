import { Component, OnInit, Inject } from '@angular/core';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material';
import { Service } from 'src/entity/Service';
import { ServiceService } from 'src/app/service/service.service';
import { FileService } from 'src/app/service/file.service';

@Component({
  selector: 'app-detail-service',
  templateUrl: './detail-service.component.html',
  styleUrls: ['./detail-service.component.scss']
})
export class DetailServiceComponent implements OnInit {
  lastmonth: string;
  isShow = false;
  index;
  m = [];
  p = [];
  arr = [];
  arr1 = [];
  price = 0;
  constructor(
    public dialogRef: MatDialogRef<DetailServiceComponent>,
    @Inject(MAT_DIALOG_DATA) public data: Service,
    private fileService: FileService
  ) {}

  ngOnInit() {
    const month = this.data.collectMonth.split('-');
    this.lastmonth = parseInt(month[0], 10) - 1 + '-' + month[1];
    if (this.data.detail !== null && this.data.detail !== '') {
      this.isShow = true;
      this.index = this.data.detail.split('-');
      const index = parseInt(this.index[1], 10) - parseInt(this.index[0], 10);
      if (this.data.increase !== null && this.data.increase !== '') {
        this.data.serviceType.priceList = this.data.increase
        .split(';')
        .sort(
          (a, b) =>
            parseInt(a.split(' - ')[0], 10) -
            parseInt(b.split(' - ')[0], 10)
        );

        this.data.serviceType.priceList.forEach(v => {
          const arr = v.split(' - ');
          this.m.push(parseInt(arr[0], 10));
          this.p.push(parseInt(arr[1], 10));
        });

        let temp = index;
        const size = this.m.length;
        for (let i = 0; i < size; i++) {
          if (index > this.m[i]) {
            let t = this.m[i];
            if (i > 0) {
              t -= this.m[i - 1];
            }
            let pp = 0;
            if (i < size - 1) {
              temp -= t;
              pp += t * this.p[i];
              this.arr.push(t);
            } else {
              pp += temp * this.p[i];
              this.arr.push(temp);
            }
            this.price += pp;
            this.arr1.push(pp);
          } else {
            let pp = 0;
            pp += temp * this.p[i];
            this.price += pp;
            this.arr.push(temp);
            this.arr1.push(pp);
            break;
          }
        }
      } else {
        this.price = index * this.data.serviceType.price;
      }
    }
  }

  onNoClick(): void {
    this.dialogRef.close();
  }

  exportPdf() {
    console.log('123');
    this.fileService.exportPdf(this.data.id);
  }
}
