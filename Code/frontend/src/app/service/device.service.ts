import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable, of } from 'rxjs';
import { tap, catchError } from 'rxjs/operators';
import { CardType } from 'src/entity/CardType';
import { Card } from 'src/entity/Card';
import { Device } from 'src/entity/Device';
import { DeviceType } from 'src/entity/DeviceType';

@Injectable({
  providedIn: 'root'
})
export class DeviceService {
  private url = 'http://localhost:8080/api/device';
  private token = '';

  constructor(private http: HttpClient) {
    this.token = localStorage.getItem('token');
  }

  findAllByDeviceGroup(groupId): Observable<Device[]> {
    return this.http
      .get<Device[]>(this.url + `/group/${groupId}`, {
        headers: new HttpHeaders().set('Authorization', this.token)
      })
      .pipe(
        tap(),
        catchError(err => of(null))
      );
  }

  findTypeById(typeId): Observable<DeviceType> {
    return this.http
      .get<DeviceType>(this.url + `/type/${typeId}`, {
        headers: new HttpHeaders().set('Authorization', this.token)
      })
      .pipe(
        tap(),
        catchError(err => of(null))
      );
  }

  save(data: Device) {
    return this.http.post(this.url + '/save', data, {
      headers: new HttpHeaders().set('Authorization', this.token),
      responseType: 'text'
    });
  }
}
