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
  selector: 'app-save-service',
  templateUrl: './save-service.component.html',
  styleUrls: ['./save-service.component.scss']
})
export class SaveServiceComponent implements OnInit {
  types: ServiceType[];
  frm: FormGroup;

  buildings: Building[] = [];
  floors: Floor[] = [];
  rooms: Room[] = [];
  selectedRoom: Room;
  selectedType: ServiceType;
  households: Household[];
  size = 0;
  constructor(
    public dialogRef: MatDialogRef<SaveServiceComponent>,
    @Inject(MAT_DIALOG_DATA) public data: any,
    private fb: FormBuilder,
    private notifierService: NotifierService,
    private serviceService: ServiceService,
    private buildingService: ApartmentService,
    private householdService: HouseholdService
  ) {}

  ngOnInit() {
    this.frm = this.fb.group({
      collectMonth: ['', [Validators.required]],
      paymentDate: '',
      description: '',
      price: 0,
      oldIndex: 0,
      newIndex: 0
    });
    this.types = this.data.types;
    this.types.sort((a, b) => a.id - b.id );
    this.selectedType = this.types[0];
    this.loadBuilding();
    this.households = [];
    this.householdService.findHouseholdLive().subscribe(r => {
      r.forEach(v => {
        this.households.push(v);
        this.size++;
      });
    });
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
    let dtl = '';
    if (this.frm.get('newIndex').value !== 0) {
      dtl =
        this.frm.get('oldIndex').value + '-' + this.frm.get('newIndex').value;
    }
    const userId = localStorage.getItem('userId');
    const s: Service = {
      id: 0,
      collectMonth: '',
      paymentDate: null,
      detail: dtl,
      price: this.frm.get('price').value,
      description: this.frm.get('description').value,
      paid: false,
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
    const pmDate = this.frm.get('paymentDate').value;
    if (pmDate !== '') {
      s.paymentDate = new Date(pmDate);
      s.paid = true;
    }
    const cm = this.frm.get('collectMonth').value.split('-');
    s.collectMonth = cm[1] + '-' + cm[0];

    this.serviceService.save(s).subscribe(
      success => {
        if (success === 'Ok') {
          this.notifierService.notify('success', `Thêm thành công`);
          this.dialogRef.close(true);
        } else {
          this.notifierService.notify('error', `Thêm thất bại`);
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
      for (let i = 0; i < this.size; i++) {
        const h = this.households[i];
        if (h.room.floor.id === f.id) {
          this.rooms.push(h.room);
        }
      }
    }
  }

  selectedRoomChange(e) {
    this.selectedRoom = this.rooms.find(v => v.id === e.value);
  }

  selectTypeChange(e) {
    this.selectedType = this.types.find(v => v.id === e.value);
    this.frm.get('price').setValue(this.selectedType.price.toFixed(2));
  }

  changeIndex() {
    const oldIndex = this.frm.get('oldIndex').value;
    const newIndex = this.frm.get('newIndex').value;
    if (newIndex !== 0) {
      if (this.selectedType.increase !== null && this.selectedType.increase !== '') {
        const m = [];
        const p = [];
        this.selectedType.priceList.forEach(v => {
          const arr = v.split(' - ');
          m.push(parseInt(arr[0], 10));
          p.push(parseInt(arr[1], 10));
        });
        console.log(m);
        const index = newIndex - oldIndex;
        let price = 0;
        let temp = index;
        const size = m.length;
        for (let i = 0; i < size; i++) {
          if (index > m[i]) {
            let t = m[i];
            if (i > 0) {
              t -= m[i - 1];
            }
            let pp = 0;
            if (i < size - 1) {
              temp -= t;
              pp += t * p[i];
            } else {
              pp += temp * p[i];
            }
            price += pp;
          } else {
            price += temp * p[i];
            break;
          }
        }
        this.frm.get('price').setValue((price * 1.1).toFixed(2));
      } else {
        const price = ((newIndex - oldIndex) * 1.1 * this.selectedType.price).toFixed(2);
        this.frm.get('price').setValue(price);
      }
    }
  }
}
