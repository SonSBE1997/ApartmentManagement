import { Component, OnInit, Inject } from '@angular/core';
import { ApartmentService } from '../service/apartment.service';
import { Building } from 'src/entity/Building';
import { Floor } from 'src/entity/Floor';
import { MatDialogRef, MAT_DIALOG_DATA, MatDialog } from '@angular/material';
import { Room } from 'src/entity/Room';

@Component({
  selector: 'app-apt-pop-up',
  templateUrl: 'apt-pop-up.html',
  styleUrls: ['apt-pop-up.scss']
})
export class AptPopUpComponent implements OnInit {
  constructor(
    public dialogRef: MatDialogRef<AptPopUpComponent>,
    @Inject(MAT_DIALOG_DATA) public data: Room
  ) { }

  ngOnInit() { }

  onNoClick(): void {
    this.dialogRef.close();
  }
}

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.scss']
})
export class HomeComponent implements OnInit {
  buildings: Building[] = [];
  floors: Floor[] = [];
  matrix = [];
  isSelected = false;
  selectedValue = 0;

  constructor(
    private aptService: ApartmentService,
    private dialog: MatDialog
  ) {}

  ngOnInit() {
    this.aptService.getListApartment().subscribe(buildings => {
      buildings.forEach((v, i) => {
        this.buildings.push(v);
        if (i === 0) {
          this.selectionChange(0);
        }
      });
    });
  }

  selectionChange(value) {
    this.selectedValue = value;
    this.floors = [];
    this.matrix = [];
    if (value >= 0) {
      this.floors = this.buildings[value].floors.sort((a, b) => a.id - b.id);
      this.floors.forEach(v => {
        v.rooms = v.rooms.sort((r1, r2) => r1.id - r2.id);
      });

      let max = 0;
      this.floors.forEach(v => {
        v.rooms = v.rooms.filter(r => !r.disable);
        max = Math.max(max, v.rooms.length);
      });
      for (let i = 0; i < max; i++) {
        this.matrix.push(i);
      }
      this.isSelected = true;
      return;
    } else {
      this.isSelected = false;
    }
  }

  openDialog(r): void {
    const dialogRef = this.dialog.open(AptPopUpComponent, {
      width: '300px',
      data: r,
      position: {top: '50px'}
    });

    dialogRef.afterClosed().subscribe(result => {});
  }
}
