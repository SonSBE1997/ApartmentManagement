import { Component, OnInit, Inject } from '@angular/core';
import { FormControl, FormGroup, FormBuilder, Validators } from '@angular/forms';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material';
import { NotifierService } from 'angular-notifier';
import { UserService } from 'src/app/service/user.service';
import { validateLeaveDate } from 'src/app/user/register-leave/register-leave.component';
import { Household } from 'src/entity/Household';
import { HouseholdService } from 'src/app/service/household.service';
@Component({
  selector: 'app-leave',
  templateUrl: './leave.component.html',
  styleUrls: ['./leave.component.scss']
})
export class LeaveComponent implements OnInit {
  frm: FormGroup;
  constructor(
    public dialogRef: MatDialogRef<LeaveComponent>,
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
        this.data.household.leaveDate != null ? this.data.household.leaveDate : ''
    });
  }

  save() {
    const h = this.data.household as Household;
    const lDate = new Date(this.frm.get('leaveDate').value);
    h.leaveDate = lDate;
    this.householdService.registerLeave(h.id, lDate).subscribe(
      success => {
        if (success === 'Ok') {
          this.notifierService.notify(
            'success',
            `Đăng ký chuyển đi thành công`
          );
          this.dialogRef.close(true);
        } else {
          this.notifierService.notify('error', `Đăng ký chuyển đi thất bại`);
        }
      },
      error => console.log(error)
    );
  }

  onNoClick(): void {
    this.dialogRef.close();
  }

  cancelLeave() {
      this.householdService
        .cancel(this.data.household.id, 'chuyển đi')
        .subscribe(
          s => {
            if (s === 'Ok') {
              this.notifierService.notify('success', 'Huỷ thành công');
              this.dialogRef.close(true);
            } else {
              this.notifierService.notify(
                'error',
                'Huỷ không thành công'
              );
            }
          },
          e => {
            console.log(e);
          }
        );
  }
}
