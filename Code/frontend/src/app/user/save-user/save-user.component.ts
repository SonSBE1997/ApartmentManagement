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
  head: User = null;
  isCurrHead = false;
  headName = '';
  selectedHousehold: Household;
  isHead = false;
  constructor(
    public dialogRef: MatDialogRef<SaveUserComponent>,
    @Inject(MAT_DIALOG_DATA) public data: any,
    private fb: FormBuilder,
    private notifierService: NotifierService,
    private userService: UserService,
    private householdService: HouseholdService
  ) {}

  ngOnInit() {
    const user = this.data.user;
    this.householdService.findHouseholdLive().subscribe(r => {
      r.forEach(v => {
        this.households.push(v);
      });
      if (user != null) {
        const h = this.households.find(v => v.id === user.household.id);
        this.selectedHousehold = h;
        const u = h.users.find(v => v.head);
        this.headName = u.id === this.data.user.id ? 'Cư dân này' : u.name;
      }
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


    if (user !== null) {
      this.isCurrHead = user.head;
      this.isHead = user.head;
      if (user.head) {
        this.head = user;
        this.headName = 'Cư dân này';
      }
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
    const data: User = {
      id: this.data.new ? 0 : this.data.user.id,
      ...this.frm.value
    };
    data.household = this.selectedHousehold;
    data.head = this.isHead;
    const action = this.data.new ? 'Thêm ' : 'Cập nhật';
    this.userService.save(data).subscribe(
      success => console.log(success),
      error => {
        if (error.error.text === 'Ok') {
          this.notifierService.notify('success', `${action} thành công`);
          if (!this.isCurrHead && this.isHead) {
            const u = this.selectedHousehold.users.find(v => v.head);
            u.head = false;
            this.userService.save(u).subscribe(s => {}, e => {
              this.dialogRef.close(true);
            });
            this.householdService.save(this.selectedHousehold).subscribe(s => {}, e => {});
          } else {
            this.dialogRef.close(true);
          }
        } else {
          this.notifierService.notify('error', `${action} thất bại`);
        }
      }
    );
  }

  changeHead(event) {
    console.log(event);
    if (!event.checked && this.isCurrHead) {
      this.isHead = true;
      return;
    }

    this.isHead = event.checked;
  }

  selectHHChange(event) {
    const h = this.households.find(v => v.id === parseInt(event.value, 10));
    this.selectedHousehold = h;
    const u = h.users.find(v => v.head);
    if (this.data.user !== null) {
      this.headName = u.id === this.data.user.id ? 'Cư dân này' : u.name;
      this.isCurrHead = this.data.user.id === u.id;
      if (this.isCurrHead) {
        this.isHead = true;
      }
    } else {
      this.headName = u.name;
    }
  }

  onNoClick(): void {
    this.dialogRef.close();
  }
}
