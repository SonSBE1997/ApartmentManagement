import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-wrong',
  templateUrl: './wrong.component.html',
  styleUrls: ['./wrong.component.scss']
})
export class WrongComponent implements OnInit {
  status: number;
  constructor(private router: Router, private activatedRoute: ActivatedRoute) {}

  ngOnInit() {
    this.activatedRoute.paramMap.subscribe(m => {
      this.status = +m.get('status');
    });
  }

  onClick() {
    this.router.navigateByUrl('/');
  }
}
