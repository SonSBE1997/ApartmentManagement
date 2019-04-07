import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable, of } from 'rxjs';
import { tap, catchError } from 'rxjs/operators';
import { VehicleType } from 'src/entity/VehicleType';
import { Vehicle } from 'src/entity/Vehicle';

@Injectable({
  providedIn: 'root'
})
export class VehicleService {
  private url = 'http://localhost:8080/api/vehicle';
  private token = '';

  constructor(private http: HttpClient) {
    this.token = localStorage.getItem('token');
  }

  findAllVehicleType(): Observable<VehicleType[]> {
    return this.http
      .get<VehicleType[]>(this.url + '/type', {
        headers: new HttpHeaders().set('Authorization', this.token)
      })
      .pipe(
        tap(),
        catchError(err => of(null))
      );
  }

  findAllByUserId(id: number): Observable<Vehicle[]> {
    return this.http
      .get<Vehicle[]>(this.url + `/vehicle/${id}`, {
        headers: new HttpHeaders().set('Authorization', this.token)
      })
      .pipe(
        tap(),
        catchError(err => of(null))
      );
  }

  findById(id: string): Observable<Vehicle> {
    return this.http
      .get<Vehicle>(this.url + `/${id}`, {
        headers: new HttpHeaders().set('Authorization', this.token)
      })
      .pipe(
        tap(),
        catchError(err => of(null))
      );
  }

  save(data: Vehicle) {
    return this.http
      .post(this.url + '/save', data, {
        headers: new HttpHeaders().set('Authorization', this.token)
      })
      .pipe(
        tap(),
        catchError(err => of(null))
      );
  }

  delete(id: string): Observable<any> {
    return this.http.delete(this.url + `/delete/${id}`, {
      headers: new HttpHeaders().set('Authorization', this.token)
    });
  }
}
