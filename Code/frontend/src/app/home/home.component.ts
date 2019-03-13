import { Component, OnInit } from '@angular/core';
import { ApartmentService } from '../service/apartment.service';
import { Building } from 'src/entity/Building';
import { Floor } from 'src/entity/Floor';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.scss']
})
export class HomeComponent implements OnInit {
  buildings: Building[] = [];
  floors: Floor[] = [];
  matrix = [];

  constructor(private aptService: ApartmentService) {}

  ngOnInit() {
    this.aptService.getListApartment().subscribe(buildings => {
      this.buildings = buildings;
    });
  }

  selectionChange(value) {
    if (value >= 0) {
      this.floors = this.buildings[value].floors.sort(
        (a, b) => a.id - b.id
      );
      this.floors.forEach(v => {
        v.rooms = v.rooms.sort((r1, r2) => r1.id - r2.id);
      });

      let max = 0;
      this.floors.forEach(v => {
        max = Math.max(max, v.rooms.length);
      });
      for (let i = 0; i < max; i++) {
        this.matrix.push(i);
      }
      return;
    } else {
      this.floors = [];
      this.matrix = [];
    }
  }
}
