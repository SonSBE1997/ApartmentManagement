import { Component, OnInit, Inject } from '@angular/core';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material';
import { NotifierService } from 'angular-notifier';
import { CardService } from 'src/app/service/card.service';
import { VehicleService } from 'src/app/service/vehicle.service';
import { CardType } from 'src/entity/CardType';
import { VehicleType } from 'src/entity/VehicleType';
import { EmployeeService } from 'src/app/service/employee.service';
import { Employee } from 'src/entity/Employee';
import { Card } from 'src/entity/Card';
import { Vehicle } from 'src/entity/Vehicle';

@Component({
  selector: 'app-register-card',
  templateUrl: './register-card.component.html',
  styleUrls: ['./register-card.component.scss']
})
export class RegisterCardComponent implements OnInit {
  frm: FormGroup;
  cardTypes: CardType[] = [];
  vehicleTypes: VehicleType[] = [];
  employee: Employee = null;
  constructor(
    public dialogRef: MatDialogRef<RegisterCardComponent>,
    @Inject(MAT_DIALOG_DATA) public data: any,
    private fb: FormBuilder,
    private notifierService: NotifierService,
    private cardService: CardService,
    private vehicleService: VehicleService,
    private empService: EmployeeService
  ) {}

  ngOnInit() {
    this.frm = this.fb.group({
      cardType: '',
      vehicleType: '',
      cardNumber: ['', [Validators.required, Validators.maxLength(50)]],
      plateNumber: ['']
    });

    this.cardService.findAllCardType().subscribe(v => {
      if (v != null) {
        v.forEach(t => {
          this.cardTypes.push(t);
        });
        this.frm.patchValue({
          cardType: this.cardTypes[0].id
        });
      }
    });

    this.vehicleService.findAllVehicleType().subscribe(v => {
      if (v != null) {
        v.forEach(t => {
          this.vehicleTypes.push(t);
        });
        this.frm.patchValue({
          vehicleType: this.vehicleTypes[0].id
        });
      }
    });

    const empId = localStorage.getItem('userId');
    if (empId != null) {
      this.empService.findById(parseInt(empId, 10)).subscribe(v => {
        if (v != null) {
          this.employee = v;
        }
      });
    }
  }

  cardSelectionChange() {
    if (this.frm.get('cardType').value === 2) {
      this.frm.get('plateNumber').setValidators([Validators.required, Validators.maxLength(50)]);
    } else {
      this.frm.get('plateNumber').clearValidators();
    }
    this.frm.get('plateNumber').updateValueAndValidity();
  }

  save() {
    const cType = this.cardTypes.find(v => v.id === this.frm.get('cardType').value);
    const cNumber = this.frm.get('cardNumber').value;
    const card: Card = {
      cardNumber: cNumber,
      cardType: cType,
      employee: this.employee,
      vehicle: null,
      createdDate: new Date(),
      user: this.data.user
    };
    this.cardService.findById(cNumber).subscribe(c => {
      if (c != null) {
        this.notifierService.notify('warning', 'Số thẻ bị trùng!!');
        return;
      }

      if (cType.id === 1) {
        this.saveCard(card);
      } else {

        this.vehicleService.findById(this.frm.get('plateNumber').value).subscribe(v => {
          if (v != null) {
            this.notifierService.notify('warning', 'Biển số xe bị trùng!!');
            return;
          }
          this.cardService.save(card).subscribe(registedCard => {
            if (registedCard != null) {
              this.saveVehicle(registedCard);
            } else {
              this.notifierService.notify('error', 'Đăng ký thẻ thất bại');
            }
          });
        });
      }
    });
  }

  saveCard(card: Card) {
    this.cardService.save(card).subscribe(v => {
      if (v != null) {
        this.notifierService.notify('success', 'Đăng ký thẻ thành công');
        this.dialogRef.close(true);
      } else {
        this.notifierService.notify('error', 'Đăng ký thẻ thất bại');
      }
    });
  }

  saveVehicle(c: Card) {
    const vType = this.vehicleTypes.find(v => v.id === this.frm.get('vehicleType').value);
    const vehicle: Vehicle = {
      vehicleType: vType,
      plateNumber: this.frm.get('plateNumber').value,
      user: this.data.user,
      card: c
    };
    this.vehicleService.save(vehicle).subscribe(v => {
      if (v != null) {
        this.notifierService.notify('success', 'Đăng ký thẻ thành công');
        this.dialogRef.close(true);
      } else {
        this.notifierService.notify('error', 'Đăng ký thẻ thất bại');
        this.cardService.delete(c.cardNumber);
      }
    });
  }

  onNoClick(): void {
    this.dialogRef.close();
  }
}
