import { ErrorHandler, Injectable} from '@angular/core';
import { HttpErrorResponse } from '@angular/common/http';
@Injectable()
export class ErrorsHandler implements ErrorHandler {
  handleError(error: Error | HttpErrorResponse) {
    if (error instanceof HttpErrorResponse) {
      // Server or connection error happened
      if (!navigator.onLine) {
        // Handle offline error
        console.log('Mat mang roi');
      } else {
        // Handle Http Error (error.status === 403, 404...)
        if (error.status === 403) {
          alert('Phiên đăng nhập đã hết hạn. Vui lòng đăng nhập lại');
          localStorage.removeItem('token');
          localStorage.removeItem('isAuthen');
          window.location.href = '/';
        } else if (error.status === 404) {
          // window.location.href = '/errors/not-found';
        } else {
          if (error.error.message === '403 Incorrect or expired!') {
            alert('Phiên đăng nhập đã hết hạn. Vui lòng đăng nhập lại');
            localStorage.removeItem('token');
            localStorage.removeItem('isAuthen');
            window.location.href = '/';
          } else {
            // window.location.href = `/errors/wrong/${error.status}`;
          }
        }
      }
    } else {
      // Handle Client Error (Angular Error, ReferenceError...)
    }
    console.log('It happens: ', error);
  }
}
