import { Component, OnInit, Inject } from '@angular/core';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material';
import { NotifierService } from 'angular-notifier';
import { DeviceService } from 'src/app/service/device.service';
import { DateService } from 'src/app/service/date.servce';
import { FormBuilder, FormGroup, Validators, FormControl } from '@angular/forms';
import { DeviceType } from 'src/entity/DeviceType';
import { Device } from 'src/entity/Device';
import { DeviceSpec } from 'src/entity/DeviceSpec';

@Component({
  selector: 'app-save-s-device',
  templateUrl: './save-s-device.component.html',
  styleUrls: ['./save-s-device.component.scss']
})
export class SaveSDeviceComponent implements OnInit {
  frm: FormGroup;

  deviceType: DeviceType;

  constructor(
    public dialogRef: MatDialogRef<SaveSDeviceComponent>,
    @Inject(MAT_DIALOG_DATA) public data: any,
    private notifierService: NotifierService,
    private deviceService: DeviceService,
    private dateService: DateService,
    private fb: FormBuilder
  ) {}

  ngOnInit() {
    this.frm = this.fb.group({
      name: ['', [Validators.required]],
      sign: ['', [Validators.required]],
      provider: ['', [Validators.required]],
      installedDate: [null, [Validators.required]],
      operationDate: [null, [Validators.required]],
      unit: ['', [Validators.required]],
      quantity: [0, [Validators.required]],
      price: [0, [Validators.required]],
      description: ['', [Validators.required]],
      maintenanceCycle: [0, [Validators.required]],
      status: false
    });



    const device = this.data.device;
    if (device !== null && device !== '') {
      this.frm.patchValue({
        name: device.name,
        sign: device.sign,
        provider: device.provider,
        installedDate: device.installedDate,
        operationDate: device.operationDate,
        unit: device.unit,
        quantity: device.quantity,
        price: device.price,
        description: device.description,
        maintenanceCycle: device.maintenanceCycle,
        status: device.status
      });
    }

    this.deviceService.findTypeById(1).subscribe(type => {
      this.deviceType = type;

      type.specs.forEach(v => {
          this.frm.addControl(v.name, this.fb.control('', Validators.required));
      });

      if (device !== null && device !== '') {
        device.deviceSpec.forEach(v => {
          this.frm.controls[v.specName].setValue(v.val);
        });
      }
    });
  }

  save() {
    const deviceSpecs: DeviceSpec[] = [];
    if (
      this.data.device !== null &&
      this.data.device !== '' &&
      this.data.device.deviceSpec.length > 0
    ) {
      this.data.device.deviceSpec.forEach(s => {
        deviceSpecs.push({
          id: s.id,
          specName: s.specName,
          val: this.frm.controls[s.specName].value
        });
      });
    } else {
      this.deviceType.specs.forEach(v => {
        deviceSpecs.push({
          id: 0,
          specName: v.name,
          val: this.frm.controls[v.name].value
        });
      });
    }
    const data: Device = {
      deviceType: this.deviceType,
      deviceGroup: {
        id: 1,
        name: ''
      },
      deviceSpec: deviceSpecs,
      ...this.frm.value
    };

    if (this.data.device !== null && this.data.device !== '') {
      data.id = this.data.device.id;
    }

    const pageType = this.data.type === 'new' ? 'Thêm' : 'Sửa';
    this.deviceService.save(data).subscribe(s => {
      if (s === 'Ok') {
        this.notifierService.notify('success', `${pageType} thành công`);
        this.dialogRef.close(true);
      } else {
        this.notifierService.notify('success', `${pageType} thất bại`);
      }
    }, err => console.log (err));
  }

  onNoClick(): void {
    this.dialogRef.close();
  }
}
