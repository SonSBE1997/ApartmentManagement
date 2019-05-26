import { Component, OnInit, Inject } from '@angular/core';
import { FormGroup, FormBuilder, Validators, EmailValidator, AbstractControl, ValidationErrors } from '@angular/forms';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material';
import { NotifierService } from 'angular-notifier';
import { EmployeeService } from 'src/app/service/employee.service';
import { DeptService } from 'src/app/service/dept.service';
import { Dept } from 'src/entity/Dept';
import { Employee } from 'src/entity/Employee';

export function validatePhoneNumber(c: AbstractControl): ValidationErrors | null {
  if (c.value === '' || c.value === undefined) {
    return null;
  }
  const pattern = /^0(\d{9})$/;
  return !pattern.test(c.value)
    ? {
        validPhone: true
      }
    : null;
}

export function validateIdCard(c: AbstractControl): ValidationErrors | null {
  const idCard = c.value;
  if (idCard === '' || idCard === undefined) {
    return null;
  }
  if (idCard.length !== 9 && idCard.length !== 12) {
    return {
      validIdCard: true
    };
  }
  let pattern = /(\d{9})$/;

  if (pattern.test(idCard) && idCard.length === 9) {
    return null;
  }

  pattern = /(\d{12})$/;

  return !(pattern.test(idCard) && idCard.length === 12) ? {
    validIdCard: true
  } : null;
}

@Component({
  selector: 'app-save-emp',
  templateUrl: './save-emp.component.html',
  styleUrls: ['./save-emp.component.scss']
})
export class SaveEmpComponent implements OnInit {
  empFrm: FormGroup;
  depts: Dept[] = [];
  constructor(
    public dialogRef: MatDialogRef<SaveEmpComponent>,
    @Inject(MAT_DIALOG_DATA) public data: any,
    private fb: FormBuilder,
    private notifierService: NotifierService,
    private empService: EmployeeService,
    private deptService: DeptService
  ) {}

  ngOnInit() {
    this.deptService.findAll().subscribe(depts => {
      depts.forEach(v => {
        this.depts.push(v);
      });
    });

    this.empFrm = this.fb.group({
      name: ['', [Validators.required, Validators.maxLength(100)]],
      gender: false,
      dateOfBirth: [null, [Validators.required]],
      idCard: ['', [Validators.required, validateIdCard]],
      phoneNumber: ['', [Validators.required, validatePhoneNumber]],
      email: ['', [Validators.required, Validators.maxLength(100), Validators.email]],
      address: ['', [Validators.required, Validators.maxLength(100)]],
      username: ['', [Validators.required, Validators.minLength(3)]],
      role: ['Normal'],
      dept: ['', Validators.required]
    });
    const employee = this.data.employee;
    if (employee !== null) {
      this.empFrm.patchValue({
        name: employee.name,
        gender: employee.gender,
        dateOfBirth: employee.dateOfBirth,
        idCard: employee.idCard,
        phoneNumber: employee.phoneNumber,
        email: employee.email,
        address: employee.address,
        username: employee.username,
        role: employee.role,
        dept: employee.dept.id
      });
    }
  }

  save() {
    const selectDept = this.depts.find(v => v.id === this.empFrm.get('dept').value);
    const data: Employee = {
      id: this.data.new ? 0 : this.data.employee.id,
      ...this.empFrm.value
    };
    data.dept = selectDept;
    console.log(data);
    const action = this.data.new ? 'Thêm ' : 'Cập nhật';
    this.empService.save(data).subscribe(
      success => console.log(success),
      error => {
        if (error.error.text === 'Ok') {
          this.notifierService.notify('success', `${action} thành công`);
          this.dialogRef.close(true);
        } else if (error.error.text === 'Duplicate username') {
          this.notifierService.notify('error', `Tên đăng nhập đã tồn tại`);
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
