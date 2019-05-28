import { Component, OnInit, Inject } from '@angular/core';
import { FormControl, FormGroup, FormBuilder, Validators, AbstractControl, ValidationErrors } from '@angular/forms';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material';
import { NotifierService } from 'angular-notifier';
import { UserService } from 'src/app/service/user.service';
import { User } from 'src/entity/User';
import { HouseholdService } from 'src/app/service/household.service';
import { Household } from 'src/entity/Household';

export function validateLeaveDate(date: Date) {
  return (c: AbstractControl) => {
    if (c.value === '' || c.value === null) {
      return null;
    }
    const leaveDate = new Date(c.value);
    return leaveDate.getTime() <= date.getTime()
      ? {
          invalidleavedate: true
        }
      : null;
  };
}

@Component({
  selector: 'app-register-leave',
  templateUrl: './register-leave.component.html',
  styleUrls: ['./register-leave.component.scss']
})
export class RegisterLeaveComponent implements OnInit {
  frm: FormGroup;
  users: User[] = [];
  selectedUser: User = null;
  isChange = false;
  household: Household;
  constructor(
    public dialogRef: MatDialogRef<RegisterLeaveComponent>,
    @Inject(MAT_DIALOG_DATA) public data: any,
    private fb: FormBuilder,
    private notifierService: NotifierService,
    private userService: UserService,
    private householdService: HouseholdService
  ) {}

  ngOnInit() {
    this.frm = this.fb.group({
      leaveDate: ['', [Validators.required, validateLeaveDate(new Date())]]
    });

    this.frm.patchValue({
      leaveDate:
        this.data.user.leaveDate != null ? this.data.user.leaveDate : ''
    });

    this.householdService.findById(this.data.user.household.id).subscribe(h => {
      this.household = h;
      if (this.data.user.head === true && this.data.change !== true) {
        h.users.forEach(u => this.users.push(u));
        this.users = this.users.filter(v => {
          return (
            (new Date(v.dateOfBirth).getFullYear() <=
            (new Date().getFullYear() - 16)) && v.leaveDate === null
          );
        });
        const uId = this.data.user.id;
        this.users = this.users.filter(u => u.id !== uId);
        if (this.users.length > 0) {
          this.isChange = true;
        }
      }
    });
  }

  save() {
    const data: User = {
      ...this.data.user
    };
    const lDate = new Date(this.frm.get('leaveDate').value);
    data.leaveDate = lDate;
    data.disable = true;
    const action = this.data.change ? 'Đổi ngày' : 'Đăng ký';
    if (this.isChange === true && this.selectedUser === null) {
      this.notifierService.notify('warning', 'Bạn phải chọn chủ hộ mới cho căn hộ');
      return;
    }

    if (this.isChange === true) {
      data.head = false;
    }

    this.userService.save(data).subscribe(
      success => console.log(success),
      error => {
        if (error.error.text === 'Ok') {
          this.notifierService.notify(
            'success',
            `${action} chuyển đi thành công`
          );
          if (this.isChange === true) {
            this.selectedUser.head = true;
            this.userService.save(this.selectedUser).subscribe(s => console.log(s), e => {});
            this.household.userId = this.selectedUser.id;
          }

          let check = false;
          this.household.users.forEach(user => {
            if (user.leaveDate === null && user.id !== this.data.user.id) {
              check = true;
            }
          });
          if (!check) {
            this.household.leaveDate = lDate;
            this.household.status = '3';
          }

          if (!check || this.isChange) {
            this.householdService
              .save(this.household)
              .subscribe(
                s => console.log(s),
                e => this.dialogRef.close(true)
              );
          } else {
            this.dialogRef.close(true);
          }
        } else {
          this.notifierService.notify('error', `${action} chuyển đi thất bại`);
        }
      }
    );
  }

  selectUserHead(e) {
    this.selectedUser = this.users.find(u => u.id === parseInt(e.value, 10));
  }

  cancelLeave() {
    const data: User = {
      ...this.data.user
    };

    data.leaveDate = null;
    data.disable = false;
    if (this.household.leaveDate !== null) {
      data.head = true;
      this.household.userId = data.id;
    }
    this.userService.save(data).subscribe(
      success => console.log(success),
      error => {
        if (error.error.text === 'Ok') {
          this.notifierService.notify('success', `Huỷ chuyển đi thành công`);
          if (this.household.leaveDate !== null) {
            this.household.leaveDate = null;
            this.household.status = '1';
            this.householdService
              .save(this.household)
              .subscribe(s => console.log(s), e => console.log(e));
          }
          this.dialogRef.close(true);
        } else {
          this.notifierService.notify('error', `Huỷ chuyển đi thất bại`);
        }
      }
    );
  }

  onNoClick(): void {
    this.dialogRef.close();
  }
}
