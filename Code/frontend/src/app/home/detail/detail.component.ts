import { Component, OnInit, ViewChild } from '@angular/core';
import { Building } from 'src/entity/Building';
import { ApartmentService } from 'src/app/service/apartment.service';
import {
  MatTableDataSource,
  MatPaginator,
  MatSort,
  MatDialog,
  MatDialogConfig
} from '@angular/material';
import { Room } from 'src/entity/Room';
import { Floor } from 'src/entity/Floor';
import { NotifierService } from 'angular-notifier';
import { DeletePopUpComponent } from 'src/app/common/delete-pop-up/delete-pop-up.component';
import { FileService } from 'src/app/service/file.service';
import { FloorService } from 'src/app/service/floor.service';

@Component({
  selector: 'app-detail',
  templateUrl: './detail.component.html',
  styleUrls: ['./detail.component.scss']
})
export class DetailComponent implements OnInit {
  // common
  tab = 0;

  // building
  buildings: Building[] = [];
  dataSource1: MatTableDataSource<Building>;
  bCheck: boolean[] = [];
  buildingsLength = 0;
  saveBuilding = false;

  // floor
  floors: Floor[] = [];
  dataSource2: MatTableDataSource<Floor>;
  fCheck: boolean[] = [];
  fLength = 0;
  fSave = false;
  @ViewChild(MatPaginator) fpaginator: MatPaginator;
  @ViewChild(MatSort) fsort: MatSort;

  constructor(
    private aptService: ApartmentService,
    private floorService: FloorService,
    private dialog: MatDialog,
    private notifierService: NotifierService,
    private fileService: FileService
  ) {}

  ngOnInit() {
    this.loadData();
  }

  // common
  loadData() {
    this.loadBuilding();
    this.loadFloor();
  }

  downloadSample() {
    this.fileService.downloadSample('Module.xlsx');
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
        floors: [],
        disable: false
      };
      data.push(building);
      this.dataSource1.data = data;
      this.bCheck.push(true);
    }
  }

  removeItem(id, row) {
    // tab 0
    if (this.tab === 0) {
      let data = this.dataSource1.data;
      if (!this.checkDeleteBuilding(data[row])) {
        this.notifierService.notify(
          'error',
          'Không thể xoá toà nhà đang có người ở'
        );
        return;
      }
      if (id === 0) {
        data = data.filter((v, k) => k !== row);
        this.bCheck = this.bCheck.filter((v, k) => k !== row);
        this.dataSource1.data = data;
        return;
      }

      const dialogRef = this.dialog.open(DeletePopUpComponent, {
        width: '350px',
        data: ''
      });

      dialogRef.afterClosed().subscribe(result => {
        if (result !== 'ok') {
          return;
        }
        this.aptService.deleteBuilding(id).subscribe(
          success => console.log(success),
          error => {
            // console.log(error);
            data = data.filter((v, k) => k !== row);
            this.bCheck = this.bCheck.filter((v, k) => k !== row);
            this.dataSource1.data = data;
            this.notifierService.notify('success', 'Xoá toà nhà thành công');
          }
        );
      });
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

  changeData(e, col, row) {
    const value = e.target.textContent;
    if (this.tab === 0) {
      const data = this.dataSource1.data;
      data[row].name = value;
      this.bCheck[row] = true;
      this.dataSource1.data = data;
    }
    e.target.innerText = value;
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
        const data = this.dataSource1.data;
        const dataChange: Building[] = [];
        const bCheckSize = this.bCheck.length;
        for (let i = 0; i < bCheckSize; i++) {
          if (this.bCheck[i]) {
            dataChange.push(data[i]);
          }
        }
        this.aptService.saveBuilding(dataChange).subscribe(
          success => console.log(success),
          error => {
            // console.log(error);
            this.loadBuilding();
            this.notifierService.notify('success', 'Lưu thành công');
            this.saveBuilding = false;
          }
        );
      }
    }
  }

  // building
  loadBuilding() {
    this.aptService.getListApartment().subscribe(buildings => {
      this.buildings = buildings;
      this.buildingsLength = buildings.length;
      this.dataSource1 = new MatTableDataSource(buildings);
      this.dataSource1.filterPredicate = (data, filter) => {
        let dataStr = data.id + data.name;
        dataStr = dataStr.toLowerCase();
        return dataStr.indexOf(filter) !== -1;
      };
      for (let i = 0; i < this.buildingsLength; i++) {
        this.bCheck.push(false);
      }
    });
  }

  applyFilter(filterValue: string) {
    this.dataSource1.filter = filterValue.trim().toLowerCase();
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
          document.getElementById('bname' + j).focus();
          return 2;
        }

        if (data[i].name === data[j].name) {
          document.getElementById('bname' + j).focus();
          return 3;
        }
      }
    }
    return 1;
  }

  // floor
  loadFloor() {
    this.floorService.getAllFloor().subscribe(floors => {
      this.floors = floors;
      this.fLength = floors.length;
      this.dataSource2 = new MatTableDataSource(floors);
      this.dataSource2.sort = this.fsort;
      this.dataSource2.paginator = this.fpaginator;
      this.dataSource2.filterPredicate = (data, filter) => {
        let dataStr = data.id + data.name;
        dataStr = dataStr.toLowerCase();
        return dataStr.indexOf(filter) !== -1;
      };
      for (let i = 0; i < this.fLength; i++) {
        this.fCheck.push(false);
      }
    });
  }

  fFilter(filterValue: string) {
    this.dataSource2.filter = filterValue.trim().toLowerCase();
  }
}
