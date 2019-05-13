import { Component, OnInit, Inject } from '@angular/core';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material';

@Component({
  selector: 'app-w-detail',
  templateUrl: './w-detail.component.html',
  styleUrls: ['./w-detail.component.scss']
})
export class WDetailComponent implements OnInit {
  constructor(
    public dialogRef: MatDialogRef<WDetailComponent>,
    @Inject(MAT_DIALOG_DATA) public data: any
  ) {}

  ngOnInit() {}

  onNoClick(): void {
    this.dialogRef.close();
  }
}
