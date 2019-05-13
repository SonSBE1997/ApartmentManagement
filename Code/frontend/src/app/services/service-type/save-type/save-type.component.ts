import { Component, OnInit, Inject } from '@angular/core';
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
    }
  }

  save() {
    const s: ServiceType = {
      id: 0,
      name: this.frm.get('name').value,
      unit: this.frm.get('unit').value,
      price: this.frm.get('price').value,
      supplier: this.frm.get('supplier').value,
      services: null
    };

    if (this.data !== null && this.data !== undefined ) {
      s.services = this.data.services;
      s.id = this.data.id;
    }

    const action = this.data === null || this.data === undefined ? 'Thêm ' : 'Cập nhật';
    this.serviceService.saveType(s).subscribe(
      success => {
        if (success === 'Ok') {
          this.notifierService.notify(
            'success',
            `${action} thành công`
          );
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
}
