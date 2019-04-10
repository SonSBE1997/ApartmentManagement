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
          // this.data.household.users.forEach(user => {
          //   if (user.leaveDate === null) {
          //     user.leaveDate = lDate;
          //     user.disable = true;
          //     this.userService
          //       .save(user)
          //       .subscribe(sc => console.log(sc), er => console.log(er));
          //   }
          // });
          this.dialogRef.close(true);
        } else {
          this.notifierService.notify(
            'error',
            `Đăng ký chuyển đi thất bại`
          );
        }
      },
      error =>  console.log(error)
    );
  }

  onNoClick(): void {
    this.dialogRef.close();
  }
}
