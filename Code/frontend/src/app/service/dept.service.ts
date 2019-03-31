import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable, of } from 'rxjs';
import { tap, catchError } from 'rxjs/operators';
import { Dept } from 'src/entity/Dept';

@Injectable({
  providedIn: 'root'
})
export class DeptService {
  private url = 'http://localhost:8080/api/dept';
  private token = '';

  constructor(private http: HttpClient) {
    this.token = localStorage.getItem('token');
  }

  findAll(): Observable<Dept[]> {
    return this.http
      .get<Dept[]>(this.url, {
        headers: new HttpHeaders().set('Authorization', this.token)
      })
      .pipe(
        tap(),
        catchError(err => of(null))
      );
  }

  save(data: Dept) {
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
