import { Component, OnInit, Inject } from '@angular/core';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material';
import { Maintenance } from 'src/entity/Maintenance';

@Component({
  selector: 'app-repair-dtl',
  templateUrl: './repair-dtl.component.html',
  styleUrls: ['./repair-dtl.component.scss']
})
export class RepairDtlComponent implements OnInit {
  constructor(
    public dialogRef: MatDialogRef<RepairDtlComponent>,
    @Inject(MAT_DIALOG_DATA) public data: Maintenance[]
  ) {}

  ngOnInit() {}

  onNoClick(): void {
    this.dialogRef.close();
  }
}
