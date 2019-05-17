import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable, of } from 'rxjs';
import { tap, catchError } from 'rxjs/operators';
import { ServiceType } from 'src/entity/ServiceType';
import { Service } from 'src/entity/Service';

@Injectable({
  providedIn: 'root'
})
export class ServiceService {
  private url = 'http://localhost:8080/api/service';
  private token = '';

  constructor(private http: HttpClient) {
    this.token = localStorage.getItem('token');
  }

  findAllType(): Observable<ServiceType[]> {
    return this.http
      .get<ServiceType[]>(this.url + '/type', {
        headers: new HttpHeaders().set('Authorization', this.token)
      })
      .pipe(
        tap(),
        catchError(err => of(null))
      );
  }

  findAll(): Observable<Service[]> {
    return this.http
      .get<Service[]>(this.url, {
        headers: new HttpHeaders().set('Authorization', this.token)
      })
      .pipe(
        tap(),
        catchError(err => of(null))
      );
  }

  save(data: Service) {
    return this.http.post(this.url + '/save', data, {
      headers: new HttpHeaders().set('Authorization', this.token),
      responseType: 'text'
    });
  }

  saveType(data: ServiceType) {
    return this.http.post(this.url + '/save-type', data, {
      headers: new HttpHeaders().set('Authorization', this.token),
      responseType: 'text'
    });
  }

  deleteType(id) {
    return this.http.delete(this.url + `/delete-type/${id}`, {
      headers: new HttpHeaders().set('Authorization', this.token),
      responseType: 'text'
    });
  }

  remind(id) {
    return this.http.get(this.url + `/remind/${id}`, {
      headers: new HttpHeaders().set('Authorization', this.token),
      responseType: 'text'
    });
  }

  paidByMonth(month, paid): Observable<[]> {
    return this.http.get<[]>(this.url + `/paid-by-month/${month}/${paid}`, {
      headers: new HttpHeaders().set('Authorization', this.token)
    });
  }

  pricePaidByMonth(month, paid): Observable<[]> {
    return this.http.get<[]>(
      this.url + `/price-paid-by-month/${month}/${paid}`,
      {
        headers: new HttpHeaders().set('Authorization', this.token)
      }
    );
  }
  paidByMonthAndType(month, type): Observable<[]> {
    return this.http.get<[]>(
      this.url + `/paid-by-month-type/${month}/${type}`,
      {
        headers: new HttpHeaders().set('Authorization', this.token)
      }
    );
  }

  pricePaidByMonthAndType(month, type): Observable<[]> {
    return this.http.get<[]>(
      this.url + `/price-paid-by-month-type/${month}/${type}`,
      {
        headers: new HttpHeaders().set('Authorization', this.token)
      }
    );
  }
}
