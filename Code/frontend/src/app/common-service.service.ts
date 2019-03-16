import { Injectable } from '@angular/core';
import { Employee } from 'src/entity/Employee';
import { HttpResponse, HttpClient } from '@angular/common/http';
import { Observable, of } from 'rxjs';
import { tap, catchError } from 'rxjs/operators';

@Injectable({
  providedIn: 'root'
})
export class CommonServiceService {
  private url = 'http://localhost:8080';
  authenticated = false;

  constructor(private http: HttpClient) {}

  authentication(credentials): Observable<HttpResponse<any>> {
    return this.http
      .post(this.url + '/login', credentials, {
        observe: 'response'
      })
      .pipe(
        tap(),
        catchError(err => of(err))
      );
  }
}
