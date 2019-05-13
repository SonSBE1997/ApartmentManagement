import {
  HttpEvent,
  HttpInterceptor,
  HttpHandler,
  HttpRequest,
  HttpErrorResponse
} from '@angular/common/http';
import { Observable, throwError, of } from 'rxjs';
import { retry, catchError } from 'rxjs/operators';

export class HttpErrorInterceptor implements HttpInterceptor {
  isExpired = false;
  intercept(
    request: HttpRequest<any>,
    next: HttpHandler
  ): Observable<HttpEvent<any>> {
    return next.handle(request).pipe(
      retry(0),
      catchError((error: HttpErrorResponse) => {
        console.log(error);
        if (error.status === 401) {
          alert('Phiên đăng nhập đã hết hạn. Vui lòng đăng nhập lại');
          localStorage.clear();
          window.location.href = '/';
        } else if (error.status === 404) {
          // window.location.href = '/errors/not-found';
        } else {
          console.log(error);
          if (error.error.message === '401 Incorrect or expired!') {
            if (!this.isExpired) {
              alert('Phiên đăng nhập đã hết hạn. Vui lòng đăng nhập lại');
              localStorage.clear();
              window.location.href = '/';
              this.isExpired = true;
            }
          } else {
            // window.location.href = `/errors/wrong/${error.status}`;
          }
        }
        return throwError(error);
      })
    );
  }
}
