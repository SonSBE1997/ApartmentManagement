import { Component, OnInit, ViewChild } from '@angular/core';
import { Building } from 'src/entity/Building';
import { ApartmentService } from 'src/app/service/apartment.service';
import { MatTableDataSource, MatPaginator, MatSort } from '@angular/material';

@Component({
  selector: 'app-detail',
  templateUrl: './detail.component.html',
  styleUrls: ['./detail.component.scss']
})
export class DetailComponent implements OnInit {
  buildings: Building[] = [];
  dataSource1: MatTableDataSource<Building>;
  tab = 0;

  @ViewChild(MatPaginator) paginator2: MatPaginator;
  @ViewChild(MatSort) sort1: MatSort;

  constructor(private aptService: ApartmentService) {}

  ngOnInit() {
    this.loadData();
  }

  loadData() {
    this.aptService.getListApartment().subscribe(buildings => {
      this.buildings = buildings;
      this.dataSource1 = new MatTableDataSource(buildings);
      this.dataSource1.sort = this.sort1;
    });
  }

  applyFilter(filterValue: string) {
    this.dataSource1.filter = filterValue.trim().toLowerCase();
  }

  changeTab(e) {
    this.tab = e;
  }

  addNew() {
    if (this.tab === 0) {
      const data = this.dataSource1.data;
      let nextId = -1;
      if (data.length > 0) {
        nextId = data[data.length - 1].id + 1;
      } else {
        nextId = 1;
      }
      const building: Building = {
        id: nextId,
        name: '',
        floors: []
      };
      data.push(building);
      this.dataSource1.data = data;
    }
  }

  removeItem(id) {
    if (this.tab === 0) {
      let data = this.dataSource1.data;
      data = data.filter(v => v.id !== id);
      this.dataSource1.data = data;
    }
  }

  changeData(value, id){
    console.log(value);
    console.log(id);
    if (this.tab === 0) {

    }
  }
}
