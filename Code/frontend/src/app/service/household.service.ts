import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable, of } from 'rxjs';
import { tap, catchError } from 'rxjs/operators';
import { Household } from 'src/entity/Household';

@Injectable({
  providedIn: 'root'
})
export class HouseholdService {
  private url = 'http://localhost:8080/api/household';
  private token = '';

  constructor(private http: HttpClient) {
    this.token = localStorage.getItem('token');
  }

  findHouseholdLive(): Observable<Household[]> {
    return this.http
      .get<Household[]>(this.url + '/live', {
        headers: new HttpHeaders().set('Authorization', this.token)
      })
      .pipe(
        tap(),
        catchError(err => of(null))
      );
  }

  findById(id: number): Observable<Household> {
    return this.http
      .get<Household>(this.url + `/${id}`, {
        headers: new HttpHeaders().set('Authorization', this.token)
      })
      .pipe(
        tap(),
        catchError(err => of(null))
      );
  }

  save(data: Household) {
    return this.http.post(this.url + '/save', data, {
      headers: new HttpHeaders().set('Authorization', this.token)
    });
  }

  update(data: Household) {
    return this.http.put(this.url + '/update', data, {
      headers: new HttpHeaders().set('Authorization', this.token)
    });
  }

  findHouseHoldComeToDay(): Observable <Household[] > {
    return this.http
      .get<Household[]>(this.url + '/come-today', {
        headers: new HttpHeaders().set('Authorization', this.token)
      })
      .pipe(
        tap(),
        catchError(err => of(null))
      );
  }

  cancelCome(householId) {
    return this.http.post(this.url + '/cancel', { id: householId}, {
      headers: new HttpHeaders().set('Authorization', this.token),
      responseType: 'text'
    });
  }

  registerLeave(householId, date) {
    return this.http.post(this.url + '/register-leave', { id: householId, leaveDate: date }, {
      headers: new HttpHeaders().set('Authorization', this.token),
      responseType: 'text'
    });
  }
}
