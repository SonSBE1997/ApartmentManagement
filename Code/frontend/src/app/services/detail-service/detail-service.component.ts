import { Component, OnInit, Inject } from '@angular/core';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material';
import { Service } from 'src/entity/Service';
import { ServiceService } from 'src/app/service/service.service';
import { FileService } from 'src/app/service/file.service';

@Component({
  selector: 'app-detail-service',
  templateUrl: './detail-service.component.html',
  styleUrls: ['./detail-service.component.scss']
})
export class DetailServiceComponent implements OnInit {
  lastmonth: string;
  isShow = false;
  index;
  constructor(
    public dialogRef: MatDialogRef<DetailServiceComponent>,
    @Inject(MAT_DIALOG_DATA) public data: Service,
    private fileService: FileService
  ) {}

  ngOnInit() {
    const m = this.data.collectMonth.split('-');
    this.lastmonth = (parseInt(m[0], 10) - 1) + '-' + m[1];
    if (this.data.serviceType.id === 1 || this.data.serviceType.id === 2) {
      this.isShow = true;
      this.index = this.data.detail.split('-');
    }
  }

  onNoClick(): void {
    this.dialogRef.close();
  }

  exportPdf() {
    console.log('123');
    this.fileService.exportPdf(this.data.id);
  }
}
