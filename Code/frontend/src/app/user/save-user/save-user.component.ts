import { Component, OnInit, Inject } from '@angular/core';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { Household } from 'src/entity/Household';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material';
import { NotifierService } from 'angular-notifier';
import { HouseholdService } from 'src/app/service/household.service';
import { UserService } from 'src/app/service/user.service';
import {
  validateIdCard,
  validatePhoneNumber
} from 'src/app/employee/save-emp/save-emp.component';
import { User } from 'src/entity/User';

@Component({
  selector: 'app-save-user',
  templateUrl: './save-user.component.html',
  styleUrls: ['./save-user.component.scss']
})
export class SaveUserComponent implements OnInit {
  frm: FormGroup;
  households: Household[] = [];
  constructor(
    public dialogRef: MatDialogRef<SaveUserComponent>,
    @Inject(MAT_DIALOG_DATA) public data: any,
    private fb: FormBuilder,
    private notifierService: NotifierService,
    private userService: UserService,
    private householdService: HouseholdService
  ) {}

  ngOnInit() {
    this.householdService.findHouseholdLive().subscribe(r => {
      r.forEach(v => {
        this.households.push(v);
      });
    });

    this.frm = this.fb.group({
      name: ['', [Validators.required, Validators.maxLength(100)]],
      gender: false,
      dateOfBirth: [null, [Validators.required]],
      idCard: ['', [validateIdCard]],
      phoneNumber: ['', [validatePhoneNumber]],
      email: ['', [Validators.maxLength(100), Validators.email]],
      address: ['', [Validators.required, Validators.maxLength(100)]],
      household: ['', Validators.required]
    });

    const user = this.data.user;
    if (user !== null) {
      this.frm.patchValue({
        name: user.name,
        gender: user.gender,
        dateOfBirth: user.dateOfBirth,
        idCard: user.idCard != null ? user.idCard : '',
        phoneNumber: user.phoneNumber != null ? user.phoneNumber : '',
        email: user.email != null ? user.email : '',
        address: user.address,
        household: user.household.id
      });
    }
  }

  save() {
    const selectedHousehold = this.households.find(
      v => v.id === this.frm.get('household').value
    );
    const data: User = {
      id: this.data.new ? 0 : this.data.user.id,
      ...this.frm.value
    };
    data.household = selectedHousehold;
    if (
      this.frm.get('name').value.toLowerCase() ===
      selectedHousehold.fullName.toLowerCase()
    ) {
      data.head = true;
    }
    const action = this.data.new ? 'Thêm ' : 'Cập nhật';
    this.userService.save(data).subscribe(
      success => console.log(success),
      error => {
        if (error.error.text === 'Ok') {
          this.notifierService.notify('success', `${action} thành công`);
          this.dialogRef.close(true);
        } else {
          this.notifierService.notify('error', `${action} thất bại`);
        }
      }
    );
  }

  onNoClick(): void {
    this.dialogRef.close();
  }
}
