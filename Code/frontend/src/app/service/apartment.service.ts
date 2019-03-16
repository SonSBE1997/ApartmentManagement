import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable, of } from 'rxjs';
import { tap, catchError } from 'rxjs/operators';
import { Building } from 'src/entity/Building';

@Injectable({
  providedIn: 'root'
})
export class ApartmentService {
  private url = 'http://localhost:8080';

  constructor(private http: HttpClient) {}

  getListApartment(): Observable<Building[]> {
    const token = localStorage.getItem('token');
    return this.http.get<Building[]>(this.url + '/api/building', {
      headers: new HttpHeaders().set('Authorization', token)
    }).pipe(
      tap(),
      catchError(err => of(null))
    );
  }
}
