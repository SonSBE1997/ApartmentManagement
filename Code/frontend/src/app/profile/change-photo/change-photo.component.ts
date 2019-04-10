import { Component, OnInit, Inject } from '@angular/core';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material';
import { HttpClient, HttpEventType } from '@angular/common/http';
import { NotifierService } from 'angular-notifier';
import { ImageCroppedEvent } from 'ngx-image-cropper';

@Component({
  selector: 'app-change-photo',
  templateUrl: './change-photo.component.html',
  styleUrls: ['./change-photo.component.scss']
})
export class ChangePhotoComponent implements OnInit {
  file;
  cropFile: File;
  message = '';
  progress = 0;
  uploading = false;

  imageChangedEvent: any = '';
  croppedImage: any = '';
  constructor(
    public dialogRef: MatDialogRef<ChangePhotoComponent>,
    @Inject(MAT_DIALOG_DATA) public data: any,
    private http: HttpClient,
    private notifierService: NotifierService
  ) {}

  ngOnInit() {}

  imageCropped(event: ImageCroppedEvent) {
    this.croppedImage = event.base64;
    const fileName = this.file.name.substr(0, this.file.name.lastIndexOf('.')) + '.png';
    this.cropFile = this.base64ToFile(event.base64, fileName);
  }

  imageLoaded() {
    console.log('image loaded');
  }
  cropperReady() {
    console.log('cropper ready');
  }

  loadImageFailed() {
    this.message = 'Bạn phải chọn ảnh';
  }

  onNoClick(): void {
    this.dialogRef.close();
  }

  changeFile(e) {
    this.imageChangedEvent = event;
    this.file = e.target.files[0];
  }

  base64ToFile(base64Data, filename): File {
    const arr = base64Data.split(',');
    const mime = arr[0].match(/:(.*?);/)[1];
    const bstr = atob(arr[1]);
    let n = bstr.length;
    const u8arr = new Uint8Array(n);
    while (n--) {
      u8arr[n] = bstr.charCodeAt(n);
    }
    return new File([u8arr], filename, { type: mime, lastModified: Date.now() });
  }

  submit() {
    if (this.cropFile === undefined) {
      this.message = 'Bạn chưa chọn ảnh tải lên';
      setTimeout(() => {
        this.message = '';
      }, 3000);
      return;
    }
    console.log(this.file);
    console.log(this.cropFile);
    const formdata: FormData = new FormData();
    formdata.append('file', this.cropFile);
    this.uploading = true;
    this.http
      .post('http://localhost:8080/file/change-photo' + this.data, formdata, {
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
          if (result === 'upload success') {
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
