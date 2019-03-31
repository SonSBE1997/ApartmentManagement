import { Component, OnInit, Inject } from '@angular/core';
import { MAT_DIALOG_DATA, MatDialogRef } from '@angular/material';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { NotifierService } from 'angular-notifier';
import { DeptService } from 'src/app/service/dept.service';
import { Dept } from 'src/entity/Dept';

@Component({
  selector: 'app-save-dept',
  templateUrl: './save-dept.component.html',
  styleUrls: ['./save-dept.component.scss']
})
export class SaveDeptComponent implements OnInit {
  deptFrm: FormGroup;
  constructor(
    public dialogRef: MatDialogRef<SaveDeptComponent>,
    @Inject(MAT_DIALOG_DATA) public data: any,
    private fb: FormBuilder,
    private notifierService: NotifierService,
    private deptService: DeptService
  ) {}

  ngOnInit() {
    this.deptFrm = this.fb.group({
      id: '',
      name: [
        '',
        [
          Validators.required,
          Validators.minLength(5),
          Validators.maxLength(100)
        ]
      ]
    });
    if (this.data !== null) {
      this.deptFrm.patchValue({
        id: this.data.id,
        name: this.data.name
      });
    }
  }

  save() {
    console.log(this.deptFrm.value);
    const dept: Dept = {
      id: this.deptFrm.get('id').value === '' ? 0 : this.deptFrm.get('id').value,
      name: this.deptFrm.get('name').value,
      disable: false,
      employees: this.data === null ? [] : this.data.employees
    };
    const action = this.data === null ? 'Thêm ' : 'Cập nhật';
    this.deptService
      .save(dept)
      .subscribe(success => console.log(success), error => {
        if (error.error.text === 'Ok') {
          this.notifierService.notify('success', `${action} thành công`);
          this.dialogRef.close(true);
        } else {
          this.notifierService.notify('error', `${action} thất bại`);
        }
      });
  }

  onNoClick(): void {
    this.dialogRef.close();
  }
}
