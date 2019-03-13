import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
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
    return this.http.get<Building[]>(this.url + '/building').pipe(
      tap(),
      catchError(err => of(null))
    );
  }
}
