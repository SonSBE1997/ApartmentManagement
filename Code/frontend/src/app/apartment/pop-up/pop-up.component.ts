import { Component, OnInit, Inject } from '@angular/core';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material';
import { HttpClient, HttpEventType } from '@angular/common/http';
import { NotifierService } from 'angular-notifier';
@Component({
  selector: 'app-pop-up',
  templateUrl: './pop-up.component.html',
  styleUrls: ['./pop-up.component.scss']
})
export class PopUpComponent implements OnInit {
  file;
  message = '';
  progress = 0;
  uploading = false;
  constructor(
    public dialogRef: MatDialogRef<PopUpComponent>,
    @Inject(MAT_DIALOG_DATA) public data: any,
    private http: HttpClient,
    private notifierService: NotifierService
  ) {}

  ngOnInit() {}

  onNoClick(): void {
    this.dialogRef.close();
  }

  changeFile(e) {
    this.file = e.target.files[0];
  }

  submit() {
    if (this.file === undefined) {
      this.message = 'Bạn chưa chọn tệp tải lên';
      setTimeout(() => {
        this.message = '';
      }, 3000);
      return;
    }
    const extension = this.file.name.substr(this.file.name.lastIndexOf('.'));
    if (extension !== '.xlsx' && extension !== '.xls') {
      this.message = 'Bạn phải chọn tệp excel (.xlsx hoặc .xls)';
      setTimeout(() => {
        this.message = '';
      }, 3000);
      return;
    }

    const formdata: FormData = new FormData();
    formdata.append('file', this.file);
    this.uploading = true;
    this.http
      .post('http://localhost:8080/file/' + this.data, formdata, {
        reportProgress: true,
        observe: 'events'
      })
      .subscribe(
        event => {
          if (event.type === HttpEventType.UploadProgress) {
            this.progress = Math.round((100 * event.loaded) / event.total);
          }
          if (event.type === HttpEventType.Response) {
            console.log(event.body);
          }
          if (this.progress === 100) {
            this.message = 'Tải lên thành công.';
          }
        },
        error => {
          console.log(error);
          const result = error.error.text;
          if (result === 'Import thành công') {
            this.notifierService.notify('success', result);
            this.dialogRef.close(true);
          } else {
            this.notifierService.notify('error', result);
          }
          setTimeout(() => {
            this.uploading = false;
            this.message = '';
          }, 2000);
        }
      );
  }
}
