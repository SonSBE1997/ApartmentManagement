import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable, of } from 'rxjs';
import { tap, catchError } from 'rxjs/operators';
import { Room } from 'src/entity/Room';

@Injectable({
  providedIn: 'root'
})
export class RoomService {
  private url = 'http://localhost:8080';

  constructor(private http: HttpClient) {}

  getRoomById(id: number): Observable<Room> {
    const token = localStorage.getItem('token');
    return this.http
      .get<Room>(this.url + `/api/room/${id}`, {
        headers: new HttpHeaders().set('Authorization', token)
      })
      .pipe(
        tap(),
        catchError(err => of(null))
      );
  }
}
