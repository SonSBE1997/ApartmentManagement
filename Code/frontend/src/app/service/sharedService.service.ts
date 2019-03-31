import { Injectable } from '@angular/core';
import { BehaviorSubject } from 'rxjs/BehaviorSubject';

@Injectable({
  providedIn: 'root'
})
export class SharedService {
  // authen
  private authentication = new BehaviorSubject<boolean>(false);
  isAuthentication = this.authentication.asObservable();

  // url
  private url = new BehaviorSubject<string>('login');
  matchUrl = this.url.asObservable();

  constructor() {}

  authentic(isAuthentication: boolean) {
    this.authentication.next(isAuthentication);
  }

  changePage(matchUrl: string) {
    this.url.next(matchUrl);
  }
}
