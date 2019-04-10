import { Component, OnInit } from '@angular/core';
import { Employee } from 'src/entity/Employee';
import { EmployeeService } from '../service/employee.service';
import {
  FormGroup,
  FormBuilder,
  Validators,
  AbstractControl
} from '@angular/forms';
import { Md5 } from 'ts-md5/dist/md5';
import { NotifierService } from 'angular-notifier';
import { MatDialog } from '@angular/material';
import { ChangePhotoComponent } from './change-photo/change-photo.component';

export function comparePassword(c: AbstractControl) {
  const rePass = c.value;
  if (rePass === '' || rePass === null) {
    return null;
  }
  const inPass = c.parent === undefined ? '' : c.parent.get('inPass').value;
  return rePass === inPass
    ? null
    : {
        passwordnotmatch: true
      };
}

export function comparePasswordOldNew(c: AbstractControl) {
  const newPass = c.value;
  if (newPass === '' || newPass === null) {
    return null;
  }
  const curPass = c.parent === undefined ? '' : c.parent.get('curPass').value;
  return newPass !== curPass
    ? null
    : {
        duplicatepassword: true
    };
}

export function currentPassNotMach(pass: string, md5) {
  return (c: AbstractControl) => {
    if (pass === '' || c.value === '' || c.value === null) {
      return null;
    }
    md5.start();
    md5.appendStr(c.value);
    return pass !== md5.end()
      ? {
          currentpassnotmatch: true
        }
      : null;
  };
}

@Component({
  selector: 'app-profile',
  templateUrl: './profile.component.html',
  styleUrls: ['./profile.component.scss']
})
export class ProfileComponent implements OnInit {
  passFrm: FormGroup;
  employee: Employee = null;
  page = 0;
  hidePass = true;
  hidePass1 = true;
  hidePass2 = true;

  constructor(
    private empService: EmployeeService,
    private fb: FormBuilder,
    private notifierService: NotifierService,
    private dialog: MatDialog
  ) {}

  ngOnInit() {
    const md5 = new Md5();
    this.passFrm = this.fb.group({
      curPass: [''],
      inPass: [
        '',
        [Validators.required, Validators.minLength(8), comparePasswordOldNew]
      ],
      rePass: ['', [Validators.required, comparePassword]]
    });
    const id = parseInt(localStorage.getItem('userId'), 10);
    this.empService.findById(id).subscribe(e => {
      this.employee = e;
      this.passFrm
        .get('curPass')
        .setValidators(currentPassNotMach(e.password, md5));
    });
  }

  update() {
    const md5 = new Md5();
    md5.start();
    md5.appendStr(this.passFrm.get('inPass').value);
    this.employee.password = md5.end().toString();
    const data: Employee = {
      ...this.employee
    };
    this.empService.save(data).subscribe(
      s => console.log(s),
      e => {
        if (e.error.text === 'Ok') {
          this.passFrm
            .get('curPass')
            .setValidators(currentPassNotMach(this.employee.password, md5));
          this.passFrm.reset();
          this.notifierService.notify('success', 'Đổi mật khẩu thành công');
        } else {
          this.notifierService.notify('error', 'Đổi mật khẩu thất bại');
        }
      }
    );
  }

  changePhoto() {
    const dialogRef = this.dialog.open(ChangePhotoComponent, {
      width: '600px',
      data: '',
      position: { top: '50px' }
    });

    dialogRef.afterClosed().subscribe(result => { });
  }
}
