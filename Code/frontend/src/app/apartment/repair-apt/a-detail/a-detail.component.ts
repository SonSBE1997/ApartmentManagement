import { Component, OnInit, Inject } from '@angular/core';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material';

@Component({
  selector: 'app-a-detail',
  templateUrl: './a-detail.component.html',
  styleUrls: ['./a-detail.component.scss']
})
export class ADetailComponent implements OnInit {
  constructor(
    public dialogRef: MatDialogRef<ADetailComponent>,
    @Inject(MAT_DIALOG_DATA) public data: any
  ) {}

  ngOnInit() {}

  onNoClick(): void {
    this.dialogRef.close();
  }
}
