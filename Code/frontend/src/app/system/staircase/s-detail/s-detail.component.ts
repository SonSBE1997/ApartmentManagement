import { Component, OnInit, Inject } from '@angular/core';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material';

@Component({
  selector: 'app-s-detail',
  templateUrl: './s-detail.component.html',
  styleUrls: ['./s-detail.component.scss']
})
export class SDetailComponent implements OnInit {
  constructor(
    public dialogRef: MatDialogRef<SDetailComponent>,
    @Inject(MAT_DIALOG_DATA) public data: any
  ) {}

  ngOnInit() {
  }

  onNoClick(): void {
    this.dialogRef.close();
  }
}
