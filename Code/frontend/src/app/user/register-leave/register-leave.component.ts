import { Component, OnInit, Inject } from '@angular/core';
import { FormControl, FormGroup, FormBuilder, Validators, AbstractControl, ValidationErrors } from '@angular/forms';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material';
import { NotifierService } from 'angular-notifier';
import { UserService } from 'src/app/service/user.service';
import { User } from 'src/entity/User';

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
    private userService: UserService
  ) {}

  ngOnInit() {
    this.frm = this.fb.group({
      leaveDate: ['', [Validators.required, validateLeaveDate(new Date())]]
    });
  }

  save() {
    const data: User = {
      ...this.data.user
    };

    data.leaveDate = new Date(this.frm.get('leaveDate').value);
    data.disable = true;

    this.userService.save(data).subscribe(
      success => console.log(success),
      error => {
        if (error.error.text === 'Ok') {
          this.notifierService.notify('success', `Đăng ký chuyển đi thành công`);
          this.dialogRef.close(true);
        } else {
          this.notifierService.notify('error', `Đăng ký chuyển đi thất bại`);
        }
      }
    );
  }

  onNoClick(): void {
    this.dialogRef.close();
  }
}
