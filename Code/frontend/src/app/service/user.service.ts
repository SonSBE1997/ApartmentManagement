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

  findByPaging(page, pageSize, searchStr): Observable<any> {
    let url = '';
    if (searchStr === '') {
      url = this.url + `/paginator?page=${page}&pageSize=${pageSize}`;
    } else {
      url =
        this.url +
        `/paginator?page=${page}&pageSize=${pageSize}&searchStr=${searchStr}`;
    }
    return this.http
      .get<any>(url, {
        headers: new HttpHeaders().set('Authorization', this.token)
      })
      .pipe(
        tap(),
        catchError(err => of(null))
      );
  }

  filter(page, pageSize, buildingId, floorId, roomId, status): Observable<any> {
    return this.http
      .get<any>(
        this.url +
          `/filter?page=${page}&pageSize=${pageSize}&buildingId=${buildingId}&floorId=${floorId}&roomId=${roomId}&status=${status}`,
        {
          headers: new HttpHeaders().set('Authorization', this.token)
        }
      )
      .pipe(
        tap(),
        catchError(err => of(null))
      );
  }

  save(data: User) {
    return this.http.post(this.url + '/save', data, {
      headers: new HttpHeaders().set('Authorization', this.token)
    });
  }

  leaveToday(): Observable<User[]> {
    return this.http
      .get<User[]>(this.url + '/leave', {
        headers: new HttpHeaders().set('Authorization', this.token)
      })
      .pipe(
        tap(),
        catchError(err => of(null))
      );
  }

  // deleteRoom(id: number): Observable<any> {
  //   return this.http.delete(this.url + `/api/room/delete/${id}`, {
  //     headers: new HttpHeaders().set('Authorization', this.token)
  //   });
  // }
}
