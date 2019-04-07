import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable, of } from 'rxjs';
import { tap, catchError } from 'rxjs/operators';
import { CardType } from 'src/entity/CardType';
import { Card } from 'src/entity/Card';

@Injectable({
  providedIn: 'root'
})
export class CardService {
  private url = 'http://localhost:8080/api/card';
  private token = '';

  constructor(private http: HttpClient) {
    this.token = localStorage.getItem('token');
  }

  findAllCardType(): Observable<CardType[]> {
    return this.http
      .get<CardType[]>(this.url + '/type', {
        headers: new HttpHeaders().set('Authorization', this.token)
      })
      .pipe(
        tap(),
        catchError(err => of(null))
      );
  }


  findById(id: string): Observable<Card> {
    return this.http
      .get<Card>(this.url + `/card/${id}`, {
        headers: new HttpHeaders().set('Authorization', this.token)
      })
      .pipe(
        tap(),
        catchError(err => of(null))
      );
  }


  findAllByUserId(id: number): Observable<Card[]> {
    return this.http
      .get<Card[]>(this.url + `/card-user/${id}`, {
        headers: new HttpHeaders().set('Authorization', this.token)
      })
      .pipe(
        tap(),
        catchError(err => of(null))
      );
  }

  save(data: Card) {
    return this.http.post(this.url + '/save', data, {
      headers: new HttpHeaders().set('Authorization', this.token)
    }).pipe(
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
