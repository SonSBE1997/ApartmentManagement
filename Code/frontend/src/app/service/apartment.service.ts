import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable, of } from 'rxjs';
import { tap, catchError, publishReplay, shareReplay } from 'rxjs/operators';
import { Building } from 'src/entity/Building';

@Injectable({
  providedIn: 'root'
})
export class ApartmentService {
  private url = 'http://localhost:8080/api/';
  private token = '';

  constructor(private http: HttpClient) {
    this.token = localStorage.getItem('token');
  }

  getListApartment(): Observable<Building[]> {
    return this.http
      .get<Building[]>(this.url + 'building', {
        headers: new HttpHeaders().set('Authorization', this.token)
      })
      .pipe(
        tap(),
        catchError(err => of(null))
      );
  }

  saveBuilding(data: Building[]) {
    return this.http
      .post(this.url + 'building/save', data, {
        headers: new HttpHeaders().set('Authorization', this.token)
      });
  }

  deleteBuilding(id: number): Observable<any> {
    return this.http
      .delete(this.url + `building/delete/${id}`, {
        headers: new HttpHeaders().set('Authorization', this.token)
      });
  }
}
