import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable, of } from 'rxjs';
import { tap, catchError } from 'rxjs/operators';
import { Maintenance } from 'src/entity/Maintenance';

@Injectable({
  providedIn: 'root'
})
export class MaintenanceService {
  private url = 'http://localhost:8080/api/maintenance';
  private token = '';

  constructor(private http: HttpClient) {
    this.token = localStorage.getItem('token');
  }

  findByDeviceGroup(groupID): Observable<Maintenance[]> {
    return this.http
      .get<Maintenance[]>(this.url + `/group/${groupID}`, {
        headers: new HttpHeaders().set('Authorization', this.token)
      })
      .pipe(
        tap(),
        catchError(err => of(null))
      );
  }

  save(data: Maintenance) {
    return this.http.post(this.url + '/save', data, {
      headers: new HttpHeaders().set('Authorization', this.token),
      responseType: 'text'
    });
  }

  findByRoom(roomId): Observable<Maintenance[]> {
    return this.http
      .get<Maintenance[]>(this.url + `/room/${roomId}`, {
        headers: new HttpHeaders().set('Authorization', this.token)
      })
      .pipe(
        tap(),
        catchError(err => of(null))
      );
  }
}
