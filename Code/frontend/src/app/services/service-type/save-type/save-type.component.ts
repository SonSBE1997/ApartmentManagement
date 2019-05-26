import { Component, OnInit, Inject, ViewChild } from '@angular/core';
import { MAT_DIALOG_DATA, MatDialogRef } from '@angular/material';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { NotifierService } from 'angular-notifier';
import { ServiceService } from 'src/app/service/service.service';
import { ServiceType } from 'src/entity/ServiceType';

@Component({
  selector: 'app-save-type',
  templateUrl: './save-type.component.html',
  styleUrls: ['./save-type.component.scss']
})
export class SaveTypeComponent implements OnInit {
  frm: FormGroup;
  type = '0';
  prices = [];
  fixed = false;

  constructor(
    public dialogRef: MatDialogRef<SaveTypeComponent>,
    @Inject(MAT_DIALOG_DATA) public data: any,
    private fb: FormBuilder,
    private notifierService: NotifierService,
    private serviceService: ServiceService
  ) {}

  ngOnInit() {
    this.frm = this.fb.group({
      name: ['', [Validators.required]],
      unit: ['', [Validators.required]],
      supplier: ['', [Validators.required]],
      price: 0
    });
    if (this.data !== null && this.data !== undefined) {
      this.frm.patchValue({
        name: this.data.name,
        unit: this.data.unit,
        price: this.data.price,
        supplier: this.data.supplier
      });
      this.fixed = this.data.fixed;
      if (this.data.increase !== '' && this.data.increase !== null) {
        this.type = '1';
        this.data.priceList.forEach(v => {
          const arr = v.split(' - ');
          this.prices.push({
            m: parseInt(arr[0], 10),
            p: parseInt(arr[1], 10)
          });
        });
      }
    }
  }

  save() {
    if (this.type === '1') {
      this.prices = this.prices.filter(v => v.m !== 0);
      if (this.prices.length === 0) {
        this.notifierService.notify('warning', 'Bạn phải nhập luỹ kế dịch vụ');
        return;
      }
    }
    const s: ServiceType = {
      id: 0,
      name: this.frm.get('name').value,
      unit: this.frm.get('unit').value,
      price: this.frm.get('price').value,
      supplier: this.frm.get('supplier').value,
      services: null,
      increase: '',
      fixed: this.fixed,
      priceList: []
    };
    if (this.type === '1') {
      const lst: string[] = [];
      this.prices.forEach(v => {
        lst.push(v.m + ' - ' + v.p);
      });
      s.increase = lst.join(';');
    }

    if (this.data !== null && this.data !== undefined) {
      s.services = this.data.services;
      s.id = this.data.id;
    }

    const action =
      this.data === null || this.data === undefined ? 'Thêm ' : 'Cập nhật';
    this.serviceService.saveType(s).subscribe(
      success => {
        if (success === 'Ok') {
          this.notifierService.notify('success', `${action} thành công`);
          this.dialogRef.close(true);
        } else {
          this.notifierService.notify('error', `${action} thất bại`);
        }
      },
      error => console.log(error)
    );
  }

  onNoClick(): void {
    this.dialogRef.close();
  }

  typeChange(e) {
    console.log(e);
    if (this.fixed === true) {
      // e.source.options._results[0]._selected = true;
      // e.source.options._results[1]._selected = false;
      setTimeout(() => {
        this.type = '0';
      }, 100);
      console.log(this.type);
      return;
    }
    if (e.value === '0') {
      this.prices = [];
    } else {
      this.prices.push({ m: 0, p: 0 });
    }
  }

  addPrice() {
    this.prices.push({ m: 0, p: 0 });
  }

  changeM(v, i) {
    this.prices[i].m = v;
  }

  changeP(v, i) {
    this.prices[i].p = v;
  }

  changeFixed(e) {
    if (e.checked === true) {
      this.type = '0';
    }
    this.fixed = e.checked;
  }
}
