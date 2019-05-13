import { Component, OnInit, Inject } from '@angular/core';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material';

@Component({
  selector: 'app-f-detail',
  templateUrl: './f-detail.component.html',
  styleUrls: ['./f-detail.component.scss']
})
export class FDetailComponent implements OnInit {
  constructor(
    public dialogRef: MatDialogRef<FDetailComponent>,
    @Inject(MAT_DIALOG_DATA) public data: any
  ) {}

  ngOnInit() {}

  onNoClick(): void {
    this.dialogRef.close();
  }
}
