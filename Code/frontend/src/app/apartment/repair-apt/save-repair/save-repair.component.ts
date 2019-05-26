import { Component, OnInit, Inject, NgZone, ViewChild } from '@angular/core';
import { MAT_DIALOG_DATA, MatDialogRef } from '@angular/material';
import { EmployeeService } from 'src/app/service/employee.service';
import { FormControl } from '@angular/forms';
import { MaintenancePersonal } from 'src/entity/MaintenancePersonal';
import { MaintenanceDetail } from 'src/entity/MaintenanceDetail';
import { Employee } from 'src/entity/Employee';
import { NotifierService } from 'angular-notifier';
import { DeviceService } from 'src/app/service/device.service';
import { Device } from 'src/entity/Device';
import { MaintenanceService } from 'src/app/service/maintenance.service';
import { Maintenance } from 'src/entity/Maintenance';
import { DateService } from 'src/app/service/date.servce';
import { Building } from 'src/entity/Building';
import { Floor } from 'src/entity/Floor';
import { Room } from 'src/entity/Room';
import { ApartmentService } from 'src/app/service/apartment.service';

@Component({
  selector: 'app-save-repair',
  templateUrl: './save-repair.component.html',
  styleUrls: ['./save-repair.component.scss']
})
export class SaveRepairComponent implements OnInit {
  maintenanceDate: string;
  description = '';
  waterType = [];
  persons: MaintenancePersonal[] = [];
  emps = [];
  employees: Employee[];
  details: MaintenanceDetail[] = [];
  devices: Device[];
  devis = [];

  buildings: Building[] = [];
  floors: Floor[] = [];
  rooms: Room[] = [];

  constructor(
    public dialogRef: MatDialogRef<SaveRepairComponent>,
    @Inject(MAT_DIALOG_DATA) public data: any,
    private empService: EmployeeService,
    private notifierService: NotifierService,
    private deviceService: DeviceService,
    private maintenanceService: MaintenanceService,
    private dateService: DateService,
    private buildingService: ApartmentService
  ) {}

  ngOnInit() {
    this.maintenanceDate = this.dateService.formatDateENG(new Date());
    this.employees = [];
    this.devices = [];
    this.loadBuilding();

    this.empService.findByDeptId(2).subscribe(v => {
      v.forEach(e => {
        this.employees.push(e);
      });
    });

    this.deviceService.findAllByDeviceGroup(4).subscribe(v => {
      v.forEach(d => {
        this.devices.push(d);
      });
    });
    this.addPerson();
  }

  onNoClick(): void {
    this.dialogRef.close();
  }

  addPerson() {
    this.emps.push({ id: 0, supervisor: true });
  }

  selectEmpChange(event, index) {
    const value = parseInt(event.target.value, 10);
    if (value === 0) {
      this.emps[index].id = 0;
    } else {
      this.emps[index].id = value;
    }
  }

  addDevice() {
    this.devis.push({ id: this.devices[0].id, description: '' });
  }

  changeDescription(event, i) {
    this.devis[i].description = event.target.value;
  }

  save() {
    this.emps = this.emps.filter(v => v.id !== 0);
    this.devis = this.devis.filter(v => v.description !== '');
    if (this.emps.length === 0) {
      this.notifierService.notify('warning', 'Bạn chưa chọn người giám sát');
      this.addPerson();
      return;
    }

    if (this.devis.length === 0) {
      this.notifierService.notify('warning', 'Bạn chưa thêm chi tiết sửa chữa');
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
        location: ''
      });
    }
    if (this.maintenanceDate === '') {
      this.notifierService.notify('warning', 'Bạn chưa nhập ngày sửa chữa');
      return;
    }

    if (this.description === '') {
      this.notifierService.notify('warning', 'Bạn chưa chọn căn hộ');
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
        id: 5,
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

  loadBuilding() {
    this.buildings = [];
    this.buildingService.getListApartment().subscribe(buildings => {
      this.buildings = buildings;
    });
  }

  selectBuildingChange(e) {
    this.floors = [];
    this.rooms = [];
    this.description = '';
    if (e.value !== undefined) {
      const b = this.buildings.find(v => v.id === e.value);
      this.floors = b.floors;
    }
  }

  selectFloorChange(e) {
    this.rooms = [];
    this.description = '';
    if (e.value !== undefined) {
      const f = this.floors.find(v => v.id === e.value);
      this.rooms = f.rooms;
    }
  }

  selectedRoomChange(e) {
    const selectedRoom = this.rooms.find(v => v.id === e.value);
    this.description = selectedRoom.building.name + ' - ' + selectedRoom.floor.name + ' - ' + selectedRoom.name;
  }
}
