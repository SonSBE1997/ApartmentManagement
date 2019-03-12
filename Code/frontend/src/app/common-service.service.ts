import { Injectable } from '@angular/core';
import { Employee } from 'src/entity/Employee';
import { HttpResponse, HttpClient } from '@angular/common/http';
import { Observable, of } from 'rxjs';
import { tap, catchError } from 'rxjs/operators';

@Injectable({
  providedIn: 'root'
})
export class CommonServiceService {
  static token = 'token';
  private url = 'http://localhost:8080';
  authenticated = false;

  constructor(private http: HttpClient) {}

  authentication(credentials): Observable<Employee> {
    this.http
      .post(this.url + '/login', credentials, {
        observe: 'response'
      })
      .subscribe((res: HttpResponse<any>) => {
        const token = res.headers.get('token');
        if (token != null && token !== '') {
          localStorage.setItem('token', token);
        }
      });
    return this.http.post<Employee>(this.url + '/login', credentials).pipe(
      tap(),
      catchError(err =>  of(null))
    );
  }
}
