import { Component, OnInit, Inject } from '@angular/core';
import { MAT_DIALOG_DATA, MatDialogRef } from '@angular/material';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { NotifierService } from 'angular-notifier';
import { ServiceService } from 'src/app/service/service.service';
import { HouseholdService } from 'src/app/service/household.service';
import { ServiceType } from 'src/entity/ServiceType';
import { Building } from 'src/entity/Building';
import { Floor } from 'src/entity/Floor';
import { Room } from 'src/entity/Room';
import { ApartmentService } from 'src/app/service/apartment.service';
import { Service } from 'src/entity/Service';
import { Household } from 'src/entity/Household';


@Component({
  selector: 'app-prepayment',
  templateUrl: './prepayment.component.html',
  styleUrls: ['./prepayment.component.scss']
})
export class PrepaymentComponent implements OnInit {
  types: ServiceType[];
  frm: FormGroup;

  buildings: Building[] = [];
  floors: Floor[] = [];
  rooms: Room[] = [];
  selectedRoom: Room;
  selectedType: ServiceType;
  constructor(
    public dialogRef: MatDialogRef<PrepaymentComponent>,
    @Inject(MAT_DIALOG_DATA) public data: any,
    private fb: FormBuilder,
    private notifierService: NotifierService,
    private serviceService: ServiceService,
    private buildingService: ApartmentService
  ) {}

  ngOnInit() {
    this.frm = this.fb.group({
      month: [1, [Validators.required, Validators.min(1)]],
      description: '',
      price: 0
    });
    this.types = [];
    this.serviceService.findAllTypeFixed().subscribe(types => {
      types.sort((a, b) => a.id - b.id);
      types.forEach(t => {
        this.types.push(t);
      });
    });

    this.loadBuilding();
  }

  save() {
    if (this.selectedType === null || this.selectedType === undefined) {
      this.notifierService.notify('warning', 'Bạn phải chọn loại dịch vụ');
      return;
    }

    if (this.selectedRoom === null || this.selectedRoom === undefined) {
      this.notifierService.notify('warning', 'Bạn phải chọn căn hộ');
      return;
    }

    const month = Math.round(this.frm.get('month').value);
    if (month < 1) {
      this.notifierService.notify(
        'warning',
        'Bạn phải nhập số tháng thanh toán trước'
      );
      return;
    }
    const userId = localStorage.getItem('userId');

    const arr: Service[] = [];
    for (let i = 0; i < month; i++) {
      const now = new Date();
      now.setMonth(now.getMonth() + i + 2);
      const m = now.getMonth();
      const y = now.getFullYear();
      let collectMonth = '';
      if (m < 10) {
        collectMonth += '0' + m + '-' + y;
      } else {
        collectMonth += m + '-' + y;
      }

      const s: Service = {
        id: 0,
        collectMonth: '',
        paymentDate: new Date(),
        detail: '',
        price: this.selectedType.price,
        description: this.frm.get('description').value,
        paid: true,
        createdDate: new Date(),
        room: this.selectedRoom,
        employee: {
          id: parseInt(userId, 10),
          name: '',
          username: '',
          disable: false,
          role: '',
          phoneNumber: '',
          password: '',
          idCard: '',
          gender: false,
          email: '',
          dateOfBirth: null,
          address: '',
          dept: null
        },
        serviceType: this.selectedType,
        fullName: '',
        increase: this.selectedType.increase
      };
      s.collectMonth = collectMonth;
      arr.push(s);
    }
    console.log(arr);

    this.serviceService.saveMany(arr).subscribe(
      success => {
        if (success === 'Ok') {
          this.notifierService.notify('success', `Thanh toán trước thành công`);
          this.dialogRef.close(true);
        } else {
          this.notifierService.notify('error', `Thanh toán trước thất bại`);
        }
      },
      error => console.log(error)
    );
  }

  onNoClick(): void {
    this.dialogRef.close();
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
    if (e.value !== undefined) {
      const b = this.buildings.find(v => v.id === e.value);
      this.floors = b.floors;
    }
  }

  selectFloorChange(e) {
    this.rooms = [];
    if (e.value !== undefined) {
      const f = this.floors.find(v => v.id === e.value);
      f.rooms.forEach(r => {
        if (r.household > 0) {
          this.rooms.push(r);
        }
      });
    }
  }

  selectedRoomChange(e) {
    this.selectedRoom = this.rooms.find(v => v.id === e.value);
  }

  selectTypeChange(e) {
    this.selectedType = this.types.find(v => v.id === e.value);
    if (this.frm.get('month').value > 0) {
      const month = Math.round(this.frm.get('month').value);
      this.frm.get('price').setValue(month * this.selectedType.price);
    } else {
      this.frm.get('price').setValue(Math.round(this.selectedType.price));
    }
  }

  monthChange(value) {
    if (this.selectedType !== null && this.selectedType !== undefined) {
      const month = Math.round(value);
      this.frm.get('price').setValue(month * this.selectedType.price);
    }
  }
}
