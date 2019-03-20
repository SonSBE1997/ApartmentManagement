import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable, of } from 'rxjs';
import { tap, catchError } from 'rxjs/operators';
import { Floor } from 'src/entity/Floor';

@Injectable({
  providedIn: 'root'
})
export class FloorService {
  private url = 'http://localhost:8080/api/floor';
  private token = '';

  constructor(private http: HttpClient) {
    this.token = localStorage.getItem('token');
  }

  getAllFloor(): Observable<Floor[]> {
    return this.http
      .get<Floor[]>(this.url, {
        headers: new HttpHeaders().set('Authorization', this.token)
      })
      .pipe(
        tap(),
        catchError(err => of(null))
      );
  }

  saveFloors(data: Floor[]) {
    return this.http.post(this.url + '/save', data, {
      headers: new HttpHeaders().set('Authorization', this.token)
    });
  }

  deleteFloor(id: number): Observable<any> {
    return this.http.delete(this.url + `/delete/${id}`, {
      headers: new HttpHeaders().set('Authorization', this.token)
    });
  }
}
