import { Component, OnInit, ViewChild } from '@angular/core';
import { Building } from 'src/entity/Building';
import { ApartmentService } from 'src/app/service/apartment.service';
import { MatTableDataSource, MatPaginator, MatSort } from '@angular/material';
import { Room } from 'src/entity/Room';
import { Floor } from 'src/entity/Floor';
import { NotifierService } from 'angular-notifier';

@Component({
  selector: 'app-detail',
  templateUrl: './detail.component.html',
  styleUrls: ['./detail.component.scss']
})
export class DetailComponent implements OnInit {
  buildings: Building[] = [];
  dataSource1: MatTableDataSource<Building>;
  tab = 0;
  buildingsLength = 0;
  saveBuilding = false;

  @ViewChild(MatPaginator) paginator2: MatPaginator;
  @ViewChild(MatSort) sort2: MatSort;

  constructor(
    private aptService: ApartmentService,
    private notifierService: NotifierService
  ) {}

  ngOnInit() {
    this.loadData();
  }

  loadData() {
    this.loadBuilding();
  }

  loadBuilding() {
    this.aptService.getListApartment().subscribe(buildings => {
      this.buildings = buildings;
      this.buildingsLength = buildings.length;
      this.dataSource1 = new MatTableDataSource(buildings);
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
      const building: Building = {
        id: 0,
        name: '',
        floors: []
      };
      data.push(building);
      this.dataSource1.data = data;
    }
  }

  removeItem(id, row) {
    if (this.tab === 0) {
      let data = this.dataSource1.data;
      if (!this.checkDeleteBuilding(data[row])) {
        this.notifierService.notify(
          'error',
          'Không thể xoá toà nhà đang có người ở'
        );
        return;
      }
      data = data.filter((v, k) => k !== row);
      this.dataSource1.data = data;
    }
  }

  checkDeleteBuilding(b: Building): boolean {
    const size = b.floors.length;
    for (let i = 0; i < size; i++) {
      if (!this.checkDeleteFloor(b.floors[i])) {
        return false;
      }
    }
    return true;
  }

  checkDeleteFloor(f: Floor): boolean {
    const size = f.rooms.length;
    for (let i = 0; i < size; i++) {
      if (!this.checkDeleteRoom(f.rooms[i])) {
        return false;
      }
    }
    return true;
  }

  checkDeleteRoom(r: Room): boolean {
    if (r.status === '1' || r.status === '2') {
      return false;
    }
    return true;
  }

  changeData(value, col, row) {
    value = value.target.textContent;
    if (this.tab === 0) {
      const data = this.dataSource1.data;
      data[row].name = value;
      this.dataSource1.data = data;
    }
  }

  save() {
    if (this.tab === 0) {
      if (this.saveBuilding) {
        return;
      }
      const check = this.checkValidBuilding();
      if (check === 2) {
        this.notifierService.notify('warning', 'Bạn phải nhập tên toà nhà');
        return;
      }
      if (check === 3) {
        this.notifierService.notify('error', 'Hai toà nhà không nên trùng tên');
        return;
      }
      if (check !== 4) {
        this.saveBuilding = true;
        this.notifierService.notify('success', 'Thêm toà nhà thành công');
      }
    }
  }

  checkValidBuilding(): number {
    const data = this.dataSource1.data;
    const size = data.length;
    if (this.buildingsLength === size) {
      return 4;
    }
    for (let i = 0; i < size - 1; i++) {
      for (let j = i + 1; j < size; j++) {
        if (data[j].name === '') {
          return 2;
        }

        if (data[i].name === data[j].name) {
          return 3;
        }
      }
    }
    return 1;
  }
}
