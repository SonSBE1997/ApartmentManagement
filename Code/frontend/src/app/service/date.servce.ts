import { Injectable } from '@angular/core';
@Injectable({
  providedIn: 'root'
})
export class DateService {
  toDateString(date: Date, seperateStr: string): string {
    if (date === null) {
      return '';
    }
    date = new Date(date);
    let dd = date.getDate() + '';
    let mm = date.getMonth() + 1 + '';
    const yyyy = date.getFullYear();
    if (+dd < 10) {
      dd = '0' + dd;
    }
    if (+mm < 10) {
      mm = '0' + mm;
    }
    if (seperateStr === '/') {
      return dd + '/' + mm + '/' + yyyy;
    }
    if (seperateStr === '-') {
      return dd + '-' + mm + '-' + yyyy;
    }
    return '';
  }
}
