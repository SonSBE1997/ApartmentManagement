import { Component, OnInit, Inject } from '@angular/core';
import { FormControl, FormGroup, FormBuilder, Validators, AbstractControl, ValidationErrors } from '@angular/forms';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material';
import { NotifierService } from 'angular-notifier';
import { UserService } from 'src/app/service/user.service';
import { User } from 'src/entity/User';
import { HouseholdService } from 'src/app/service/household.service';

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
      leaveDate: this.data.user.leaveDate != null ? this.data.user.leaveDate : ''
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
    this.userService.save(data).subscribe(
      success => console.log(success),
      error => {
        if (error.error.text === 'Ok') {
          this.notifierService.notify(
            'success',
            `${action} chuyển đi thành công`
          );
          this.householdService.findById(this.data.user.household.id).subscribe (h => {
            if (h !== null) {
              let check = false;
              console.log(h);
              h.users.forEach(user => {
                if (user.leaveDate === null) {
                  check = true;
                }
              });
              if (!check) {
                h.leaveDate = lDate;
                this.householdService.save(h).subscribe(s => console.log(s), e => console.log(e));
              }
            }
          });
          this.dialogRef.close(true);
        } else {
          this.notifierService.notify('error', `${action} chuyển đi thất bại`);
        }
      }
    );
  }

  cancelLeave() {
    const data: User = {
      ...this.data.user
    };

    data.leaveDate = null;
    data.disable = false;
    this.userService.save(data).subscribe(
      success => console.log(success),
      error => {
        if (error.error.text === 'Ok') {
          this.notifierService.notify(
            'success',
            `Huỷ chuyển đi thành công`
          );
          const h = this.data.user.household;
          if (h.leaveDate !== null) {
            h.leaveDate = null;
            this.householdService.save(h).subscribe(s => console.log(s), e => console.log(e));
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
