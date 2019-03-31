import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { SharedService } from 'src/app/service/sharedService.service';

@Component({
  selector: 'app-not-found',
  templateUrl: './not-found.component.html',
  styleUrls: ['./not-found.component.scss']
})
export class NotFoundComponent implements OnInit {
  constructor(private router: Router, private sharedService: SharedService) {}

  ngOnInit() {}

  onClick() {
    this.router.navigateByUrl('/');
    this.sharedService.changePage('apartment');
  }
}
