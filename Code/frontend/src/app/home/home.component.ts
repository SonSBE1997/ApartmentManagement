import { Component, OnInit, Inject } from '@angular/core';
import { ApartmentService } from '../service/apartment.service';
import { Building } from 'src/entity/Building';
import { Floor } from 'src/entity/Floor';
import { MatDialogRef, MAT_DIALOG_DATA, MatDialog } from '@angular/material';
import { Room } from 'src/entity/Room';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.scss']
})
export class HomeComponent implements OnInit {
  buildings: Building[] = [];
  floors: Floor[] = [];
  matrix = [];

  constructor(
    private aptService: ApartmentService,
    private dialog: MatDialog
  ) {}

  ngOnInit() {
    this.aptService.getListApartment().subscribe(buildings => {
      this.buildings = buildings;
    });
  }

  selectionChange(value) {
    if (value >= 0) {
      this.floors = this.buildings[value].floors.sort((a, b) => a.id - b.id);
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

  openDialog(r): void {
    console.log(r);
    const dialogRef = this.dialog.open(AptPopUpComponent, {
      width: '300px',
      data: r
    });

    dialogRef.afterClosed().subscribe(result => {});
  }
}

@Component({
  selector: 'apt-pop-up',
  templateUrl: 'apt-pop-up.html',
  styleUrls: ['apt-pop-up.scss']
})
export class AptPopUpComponent implements OnInit {
  constructor(
    public dialogRef: MatDialogRef<AptPopUpComponent>,
    @Inject(MAT_DIALOG_DATA) public data: Room
  ) {}

  ngOnInit() {}

  onNoClick(): void {
    this.dialogRef.close();
  }
}
