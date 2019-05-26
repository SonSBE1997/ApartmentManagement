import { Component, OnInit, Inject } from '@angular/core';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material';
import { NotifierService } from 'angular-notifier';
import { HouseholdService } from 'src/app/service/household.service';
import { validateIdCard, validatePhoneNumber } from 'src/app/employee/save-emp/save-emp.component';
import { Household } from 'src/entity/Household';
import { UserService } from 'src/app/service/user.service';
import { User } from 'src/entity/User';

@Component({
  selector: 'app-save-household',
  templateUrl: './save-household.component.html',
  styleUrls: ['./save-household.component.scss']
})
export class SaveHouseholdComponent implements OnInit {
  frm: FormGroup;
  constructor(
    public dialogRef: MatDialogRef<SaveHouseholdComponent>,
    @Inject(MAT_DIALOG_DATA) public data: any,
    private fb: FormBuilder,
    private notifierService: NotifierService,
    private householdService: HouseholdService,
    private userService: UserService
  ) { }

  ngOnInit() {
    this.frm = this.fb.group({
      fullName: ['', [Validators.required, Validators.maxLength(100)]],
      idCard: ['', [validateIdCard]],
      phoneNumber: ['', [validatePhoneNumber]],
      address: ['', [Validators.required, Validators.maxLength(100)]],
      email: ['', [Validators.required, Validators.maxLength(100), Validators.email]],
      price: [0, [Validators.required]],
      hire: false,
      deposit: [0, [Validators.required]],
      depositDate: [null, [Validators.required]],
      comeDate: [null, [Validators.required]]
    });
  }

  save() {
    const data: Household = {
      id: 0,
      status: '0',
      room: this.data.room,
      employee: this.data.employee,
      users: [],
      leaveDate: null,
      userId: 0,
      statusStr: '',
      ...this.frm.value
    };

    const user: User = {
      id: 0,
      name: this.frm.get('fullName').value,
      gender: true,
      idCard: this.frm.get('idCard').value,
      phoneNumber: this.frm.get('phoneNumber').value,
      address: this.frm.get('address').value,
      email: this.frm.get('email').value,
      head: true,
      leave: false,
      leaveDate: null,
      dateOfBirth: null,
      disable: false,
      household: null
    };

    data.users.push(user);

    this.householdService.save(data).subscribe(
      success => console.log(success),
      error => {
        if (error.error.text === 'Ok') {
          this.notifierService.notify('success', `Đăng ký thành công`);
          this.dialogRef.close(true);
        } else {
          this.notifierService.notify('error', `Đăng ký thất bại`);
        }
      }
    );
  }

  onNoClick(): void {
    this.dialogRef.close();
  }
}
