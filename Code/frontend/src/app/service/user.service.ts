import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable, of } from 'rxjs';
import { tap, catchError } from 'rxjs/operators';
import { User } from 'src/entity/User';

@Injectable({
  providedIn: 'root'
})
export class UserService {
  private url = 'http://localhost:8080/api/user';
  private token = '';

  constructor(private http: HttpClient) {
    this.token = localStorage.getItem('token');
  }

  getUserById(id: number): Observable<User> {
    const token = localStorage.getItem('token');
    return this.http
      .get<User>(this.url + `/${id}`, {
        headers: new HttpHeaders().set('Authorization', token)
      })
      .pipe(
        tap(),
        catchError(err => of(null))
      );
  }

  getListUser(): Observable<User[]> {
    return this.http
      .get<User[]>(this.url, {
        headers: new HttpHeaders().set('Authorization', this.token)
      })
      .pipe(
        tap(),
        catchError(err => of(null))
      );
  }

  // saveRoom(data: Room[]) {
  //   return this.http.post(this.url + '/api/room/save', data, {
  //     headers: new HttpHeaders().set('Authorization', this.token)
  //   });
  // }

  // deleteRoom(id: number): Observable<any> {
  //   return this.http.delete(this.url + `/api/room/delete/${id}`, {
  //     headers: new HttpHeaders().set('Authorization', this.token)
  //   });
  // }
}
