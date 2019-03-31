import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable, of } from 'rxjs';
import { tap, catchError } from 'rxjs/operators';
import { Dept } from 'src/entity/Dept';
import { Employee } from 'src/entity/Employee';

@Injectable({
  providedIn: 'root'
})
export class EmployeeService {
  private url = 'http://localhost:8080/api/employee';
  private token = '';

  constructor(private http: HttpClient) {
    this.token = localStorage.getItem('token');
  }

  findAll(): Observable<Employee[]> {
    return this.http
      .get<Employee[]>(this.url, {
        headers: new HttpHeaders().set('Authorization', this.token)
      })
      .pipe(
        tap(),
        catchError(err => of(null))
      );
  }

  findById(id: number): Observable<Employee> {
    return this.http
      .get<Employee>(this.url + `/${id}`, {
        headers: new HttpHeaders().set('Authorization', this.token)
      })
      .pipe(
        tap(),
        catchError(err => of(null))
      );
  }

  save(data: Employee) {
    return this.http.post(this.url + '/save', data, {
      headers: new HttpHeaders().set('Authorization', this.token)
    });
  }

  delete(id: number): Observable<any> {
    return this.http.delete(this.url + `/delete/${id}`, {
      headers: new HttpHeaders().set('Authorization', this.token)
    });
  }
}
