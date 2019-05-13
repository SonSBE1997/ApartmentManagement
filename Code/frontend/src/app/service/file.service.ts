import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class FileService {
  private url = 'http://localhost:8080/file/';

  constructor(private http: HttpClient) {}

  downloadSample(fileName: string) {
    window.location.href = this.url + `download-sample/${fileName}`;
  }

  generateServiceExcel() {
    return this.http.get(this.url + 'gen-service-excel', {
      responseType: 'text'
    });
  }

  exportPdf(id) {
    window.location.href = this.url + `export-invoice-pdf/${id}`;
  }
}
