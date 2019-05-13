import { Component, OnInit, Inject } from '@angular/core';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material';

@Component({
  selector: 'app-e-detail',
  templateUrl: './e-detail.component.html',
  styleUrls: ['./e-detail.component.scss']
})
export class EDetailComponent implements OnInit {
  constructor(
    public dialogRef: MatDialogRef<EDetailComponent>,
    @Inject(MAT_DIALOG_DATA) public data: any
  ) {}

  ngOnInit() {}

  onNoClick(): void {
    this.dialogRef.close();
  }
}
