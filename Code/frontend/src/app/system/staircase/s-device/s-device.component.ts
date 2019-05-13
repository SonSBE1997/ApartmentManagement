import { Component, OnInit } from '@angular/core';
import { Device } from 'src/entity/Device';
import { DeviceService } from 'src/app/service/device.service';
import { NotifierService } from 'angular-notifier';
import { MatDialog } from '@angular/material';
import { SaveSDeviceComponent } from './save-s-device/save-s-device.component';

@Component({
  selector: 'app-s-device',
  templateUrl: './s-device.component.html',
  styleUrls: ['./s-device.component.scss']
})
export class SDeviceComponent implements OnInit {
  selectedValue = 0;
  isSelected = false;
  devices: Device[];
  selected: Device;
  constructor(
    private deviceService: DeviceService,
    private notifierService: NotifierService,
    private dialog: MatDialog
  ) {}

  ngOnInit() {
    this.loadData();
  }

  loadData() {
    this.devices = [];
    this.deviceService.findAllByDeviceGroup(1).subscribe(v => {
      v.forEach((d, i) => {
        this.devices.push(d);
        if (i === 1) {
          this.selectionChange(d.id);
        }
      });
    });
  }

  selectionChange(value) {
    value = parseInt(value, 10);
    if (value !== 0) {
      this.selected = this.devices.find(v => v.id === value);
      this.selected.deviceSpec.sort((a, b) => a.id - b.id);
      this.isSelected = true;
    } else {
      this.isSelected = false;
    }
    this.selectedValue = value;
  }

  save() {
    const dialogRef = this.dialog.open(SaveSDeviceComponent, {
      width: '600px',
      data: {
        type: 'new',
        device: ''
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

  edit() {
    if (!this.isSelected) {
      this.notifierService.notify('warning', 'Bạn phải một chọn thang máy');
    }

    const dialogRef = this.dialog.open(SaveSDeviceComponent, {
      width: '600px',
      data: {
        type: 'edit',
        device: this.selected
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
}
