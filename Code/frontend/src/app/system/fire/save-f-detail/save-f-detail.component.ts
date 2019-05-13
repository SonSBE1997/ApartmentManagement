import { Component, OnInit, Inject, NgZone, ViewChild } from '@angular/core';
import { MAT_DIALOG_DATA, MatDialogRef } from '@angular/material';
import { EmployeeService } from 'src/app/service/employee.service';
import { MaintenancePersonal } from 'src/entity/MaintenancePersonal';
import { MaintenanceDetail } from 'src/entity/MaintenanceDetail';
import { Employee } from 'src/entity/Employee';
import { NotifierService } from 'angular-notifier';
import { DeviceService } from 'src/app/service/device.service';
import { Device } from 'src/entity/Device';
import { MaintenanceService } from 'src/app/service/maintenance.service';
import { Maintenance } from 'src/entity/Maintenance';
import { DateService } from 'src/app/service/date.servce';

@Component({
  selector: 'app-save-f-detail',
  templateUrl: './save-f-detail.component.html',
  styleUrls: ['./save-f-detail.component.scss']
})
export class SaveFDetailComponent implements OnInit {
  maintenanceDate: string;
  description = 'Kiểm tra bình chữa cháy';
  persons: MaintenancePersonal[] = [];
  emps = [];
  employees: Employee[];
  details: MaintenanceDetail[] = [];
  devices: Device[];
  devis = [];
  constructor(
    public dialogRef: MatDialogRef<SaveFDetailComponent>,
    @Inject(MAT_DIALOG_DATA) public data: any,
    private empService: EmployeeService,
    private notifierService: NotifierService,
    private deviceService: DeviceService,
    private maintenanceService: MaintenanceService,
    private dateService: DateService
  ) {}

  ngOnInit() {
    this.maintenanceDate = this.dateService.formatDateENG(new Date());
    this.employees = [];
    this.devices = [];

    this.empService.findByDeptId(2).subscribe(v => {
      v.forEach(e => {
        this.employees.push(e);
      });
    });

    this.deviceService.findAllByDeviceGroup(3).subscribe(v => {
      v.forEach(d => {
        this.devices.push(d);
      });
    });
  }

  onNoClick(): void {
    this.dialogRef.close();
  }

  addPerson() {
    this.emps.push({ id: 0, supervisor: false });
  }

  selectEmpChange(event, index) {
    const value = parseInt(event.target.value, 10);
    if (value === 0) {
      this.emps[index].id = 0;
      this.emps[index].supervisor = false;
    } else {
      const e = this.employees.find(v => v.id === value);
      const inArr = this.emps.find(v => v.id === value);
      if (inArr === null || inArr === undefined) {
        this.emps[index].id = e.id;
      } else {
        this.notifierService.notify('warning', 'Nhân viên này đã được chọn');
        this.emps[index].id = 0;
        this.emps[index].supervisor = false;
        event.target.selectedIndex = 0;
      }
    }
  }

  supervisorChange(event, index) {
    const e = this.emps.filter((v, k) => k === index)[0];
    if (e.id === 0) {
      event.target.checked = false;
      return;
    }
    this.emps.forEach((v, k) => {
      if (k === index) {
        v.supervisor = true;
      } else {
        v.supervisor = false;
      }
    });
  }

  addDevice() {
    this.devis.push({ id: this.devices[0].id, description: '', location: '' });
  }

  changeDescription(event, i) {
    this.devis[i].description = event.target.value;
  }

  changeLocation(event, i) {
    this.devis[i].location = event.target.value;
  }

  save() {
    this.emps = this.emps.filter(v => v.id !== 0);
    this.devis = this.devis.filter(v => v.description !== '');

    if (this.emps.length === 0) {
      this.notifierService.notify('warning', 'Bạn chưa thêm nhân sự');
      return;
    }
    const hasSupervisor = this.emps.find(v => v.supervisor === true);
    if (hasSupervisor == null || hasSupervisor === undefined) {
      this.notifierService.notify('warning', 'Bạn chưa chọn trưởng nhóm');
      return;
    }

    if (this.devis.length === 0) {
      this.notifierService.notify('warning', 'Bạn chưa thêm chi tiết kiểm tra');
      return;
    }
    const empLength = this.emps.length;
    for (let i = 0; i < empLength; i++) {
      const e = this.employees.find(v => v.id === this.emps[i].id);
      this.persons.push({
        id: 0,
        supervisor: this.emps[i].supervisor,
        employee: e
      });
    }

    const detailLength = this.devis.length;
    for (let i = 0; i < detailLength; i++) {
      this.details.push({
        id: 0,
        price: 0,
        description: this.devis[i].description,
        device: this.devices[0],
        location: this.devis[i].location
      });
    }
    if (this.maintenanceDate === '') {
      this.notifierService.notify('warning', 'Bạn chưa nhập ngày kiểm tra');
      return;
    }
    if (this.description === '') {
      this.notifierService.notify('warning', 'Bạn chưa nhập mô tả');
      return;
    }
    const m: Maintenance = {
      id: 0,
      maintenanceDate: new Date(this.maintenanceDate),
      description: this.description,
      maintenancePrice: 0,
      isExecuted: true,
      numberPersonnel: empLength,
      paid: true,
      deviceGroup: {
        id: 3,
        name: ''
      },
      details: this.details,
      persons: this.persons,
      supervisor: ''
    };

    this.maintenanceService.save(m).subscribe(
      s => {
        console.log(s);
        if (s === 'Ok') {
          this.notifierService.notify('success', 'Thêm thành công');
          this.dialogRef.close(true);
        } else {
          this.notifierService.notify('error', 'Thêm thất bại');
        }
      },
      e => console.log(e)
    );
  }

  changeDate(value) {
    this.maintenanceDate = value;
  }

  changeDes(value) {
    this.description = value;
  }
}

