import { Component, OnInit, Inject } from '@angular/core';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material';

@Component({
  selector: 'app-cancel-come',
  templateUrl: './cancel-come.component.html',
  styleUrls: ['./cancel-come.component.scss']
})
export class CancelComeComponent implements OnInit {
  constructor(
    public dialogRef: MatDialogRef<CancelComeComponent>,
    @Inject(MAT_DIALOG_DATA) public data: any
  ) {}

  ngOnInit() {}

  onNoClick(): void {
    this.dialogRef.close();
  }
}
