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

  formatDateENG(date) {
    const d = new Date(date);
    let  month = '' + (d.getMonth() + 1);
    let day = '' + d.getDate();
    const  year = d.getFullYear();

    if (month.length < 2) {
      month = '0' + month;
    }
    if (day.length < 2) {
      day = '0' + day;
    }

    return [year, month, day].join('-');
  }
}
