import { Component, OnInit, ViewChild } from '@angular/core';
import { Building } from 'src/entity/Building';
import { ApartmentService } from 'src/app/service/apartment.service';
import {
  MatTableDataSource,
  MatPaginator,
  MatSort,
  MatDialog
} from '@angular/material';
import { Room } from 'src/entity/Room';
import { Floor } from 'src/entity/Floor';
import { NotifierService } from 'angular-notifier';
import { DeletePopUpComponent } from 'src/app/common/delete-pop-up/delete-pop-up.component';
import { FileService } from 'src/app/service/file.service';
import { FloorService } from 'src/app/service/floor.service';
import { RoomService } from 'src/app/service/room.service';
import { PopUpComponent } from '../pop-up/pop-up.component';

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
  bId = -1;

  // floor
  floors: Floor[] = [];
  dataSource2: MatTableDataSource<Floor>;
  fCheck: boolean[] = [];
  fLength = 0;
  fSave = false;
  fIsFilter = false;
  fFilterData = 0;
  fsearchData = '';
  @ViewChild('fpaginator') fpaginator: MatPaginator;
  fId = -1;

  // Room
  rooms: Room[] = [];
  dataSource3: MatTableDataSource<Room>;
  rCheck: boolean[] = [];
  rLength = 0;
  rSave = false;
  rsearchData = '';
  @ViewChild('rpaginator') rpaginator: MatPaginator;
  @ViewChild(MatSort) rsort: MatSort;
  rId = -1;

  constructor(
    private aptService: ApartmentService,
    private floorService: FloorService,
    private dialog: MatDialog,
    private notifierService: NotifierService,
    private fileService: FileService,
    private roomService: RoomService
  ) {}

  ngOnInit() {
    this.loadData();
  }

  // ===================================== common
  loadData() {
    this.loadBuilding();
    this.loadFloor();
    this.loadRoom();
  }

  downloadSample() {
    this.fileService.downloadSample('Apartment.xlsx');
  }

  changeTab(e) {
    this.tab = e;
  }

  addNew() {
    if (this.tab === 0) {
      const data = this.dataSource1.data;
      const building: Building = {
        id: this.bId,
        name: '',
        floors: [],
        disable: false
      };
      data.push(building);
      this.dataSource1.data = data;
      this.bCheck.push(true);
      this.bId--;
    } else if (this.tab === 1) {
      const data = this.dataSource2.data;
      const floor: Floor = {
        id: this.fId--,
        name: '',
        rooms: [],
        building: null,
        disable: false,
        deletable: true
      };
      this.fId--;
      data.unshift(floor);
      this.dataSource2.data = data;
      this.fCheck.unshift(true);
      if (this.fpaginator.pageIndex !== 0) {
        this.fpaginator.firstPage();
      }
    } else if (this.tab === 2) {
      const data = this.dataSource3.data;
      const room: Room = {
        id: this.bId--,
        name: '',
        floor: null,
        building: null,
        disable: false,
        deletable: true,
        area: 0,
        status: '0',
        households: [],
        roomType: 'B'
      };
      this.rId--;
      data.unshift(room);
      this.dataSource3.data = data;
      this.rCheck.unshift(true);
      if (this.rpaginator.pageIndex !== 0) {
        this.rpaginator.firstPage();
      }
    }
  }

  removeItem(id) {
    // tab 0
    if (this.tab === 0) {
      this.deleteBuilding(id);
    } else if (this.tab === 1) {
      this.deleteFloor(id);
    } else if (this.tab === 2) {
      this.deleteRoom(id);
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

  changeData(e, col, id) {
    let value = e.target.textContent;
    if (col === 'area') {
      value = e.target.value;
    }
    let row = -1;
    if (this.tab === 0) {
      const data = this.dataSource1.data;
      const b = data.find(v => v.id === id);
      row = data.indexOf(b);
      b.name = value;
      this.bCheck[row] = true;
      this.dataSource1.data = data;
    } else if (this.tab === 1) {
      const data = this.dataSource2.data;
      const floor = data.find(v => v.id === id);
      row = data.indexOf(floor);
      data[row].name = value;
      this.fCheck[row] = true;
      this.dataSource2.data = data;
    } else if (this.tab === 2) {
      const data = this.dataSource3.data;
      const room = data.find(v => v.id === id);
      row = data.indexOf(room);
      if (col === 'name') {
        data[row].name = value;
      } else if (col === 'area') {
        data[row].area = value;
      }
      this.rCheck[row] = true;
      this.dataSource3.data = data;
    }
    e.target.innerText = value;
  }

  save() {
    if (this.tab === 0) {
      if (this.saveBuilding) {
        return;
      }
      if (this.checkValidBuilding()) {
        this.saveBuilding = true;
        const data = this.dataSource1.data;
        const dataChange: Building[] = [];
        const bCheckSize = this.bCheck.length;
        for (let i = 0; i < bCheckSize; i++) {
          if (this.bCheck[i]) {
            dataChange.push(data[i]);
          }
        }
        if (dataChange.length === 0) {
          return;
        }
        this.aptService.saveBuilding(dataChange).subscribe(
          success => console.log(success),
          error => {
            // console.log(error);
            this.loadData();
            this.notifierService.notify('success', 'Lưu thành công');
            this.saveBuilding = false;
          }
        );
      }
    } else if (this.tab === 1) {
      if (this.fSave) {
        return;
      }
      if (this.checkValidFloor()) {
        this.fSave = true;
        const data = this.dataSource2.data;
        const dataChange: Floor[] = [];
        const fCheckSize = this.fCheck.length;
        // console.log(fCheckSize);
        for (let i = 0; i < fCheckSize; i++) {
          if (this.fCheck[i]) {
            dataChange.push(data[i]);
          }
        }
        if (dataChange.length === 0) {
          return;
        }
        this.floorService.saveFloors(dataChange).subscribe(
          success => console.log(success),
          error => {
            // console.log(error);
            this.loadData();
            this.notifierService.notify('success', 'Lưu thành công');
            this.fSave = false;
          }
        );
      }
    } else if (this.tab === 2) {
      if (this.rSave) {
        return;
      }
      if (this.checkValidRoom()) {
        this.rSave = true;
        const data = this.dataSource3.data;
        const dataChange: Room[] = [];
        const rCheckSize = this.rCheck.length;
        for (let i = 0; i < rCheckSize; i++) {
          if (this.rCheck[i]) {
            dataChange.push(data[i]);
          }
        }
        if (dataChange.length === 0) {
          return;
        }
        this.roomService.saveRoom(dataChange).subscribe(
          success => console.log(success),
          error => {
            console.log(error);
            if (error.error.text === 'Ok') {
              this.loadRoom();
              this.notifierService.notify('success', 'Lưu thành công');
              this.rSave = false;
            } else {
              this.notifierService.notify('error', 'Lưu không thành công');
            }

          }
        );
      }
    }
  }

  // ================================== Building
  checkValidBuilding(): boolean {
    const data = this.dataSource1.data;
    const size = data.length;
    const isChange = this.bCheck.filter(v => v === true);
    if (isChange.length === 0) {
      return;
    }
    for (let i = 0; i < size - 1; i++) {
      for (let j = i + 1; j < size; j++) {
        if (data[j].name === '') {
          document.getElementById('bname' + j).focus();
          this.notifierService.notify('warning', 'Bạn phải nhập tên toà nhà');
          return false;
        }

        if (data[i].name === data[j].name) {
          document.getElementById('bname' + j).focus();
          this.notifierService.notify(
            'error',
            'Hai toà nhà không nên trùng tên'
          );
          return false;
        }
      }
    }
    return true;
  }

  loadBuilding() {
    this.buildings = [];
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

  deleteBuilding(id) {
    let data = this.dataSource1.data;
    const b = data.find(v => v.id === id);
    const row = data.indexOf(b);
    if (!this.checkDeleteBuilding(b)) {
      this.notifierService.notify(
        'error',
        'Không thể xoá toà nhà đang có người ở'
      );
      return;
    }
    if (id < 0) {
      data = data.filter((v, k) => v.id !== b.id);
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
          this.loadFloor();
        }
      );
    });
  }

  // =============================== Floor
  loadFloor() {
    this.floors = [];
    this.floorService.getAllFloor().subscribe(floors => {
      this.fLength = floors.length;
      for (let i = 0; i < this.fLength; i++) {
        this.fCheck.push(false);
        this.floors.push({
          ...floors[i],
          deletable: this.checkDeleteFloor(floors[i])
        });
      }
      this.dataSource2 = new MatTableDataSource(this.floors);
      this.dataSource2.paginator = this.fpaginator;
      this.dataSource2.filterPredicate = (data, filter) => {
        let dataStr = data.id + data.name + data.building.name;
        dataStr = dataStr.toLowerCase();
        return dataStr.indexOf(filter) !== -1;
      };
      if (this.fsearchData !== '') {
        this.fFilter(this.fsearchData);
      }
    });
  }

  fFilter(filterValue: string) {
    this.dataSource2.filter = filterValue.trim().toLowerCase();
    this.fsearchData = filterValue;
  }

  fClickFilter() {
    this.fIsFilter = true;
    let data = this.dataSource2.data;
    data = data.filter(v => v.building.id === this.fFilterData);
    this.dataSource2.data = data;
    if (this.fsearchData !== '') {
      this.fFilter(this.fsearchData);
    }
  }

  fClickCancelFilter() {
    this.fIsFilter = false;
    this.loadFloor();
  }

  selectBuildingChange(e, id) {
    const data = this.dataSource2.data;
    const f = data.find(v => v.id === id);
    const row = data.indexOf(f);
    data[row].building = this.buildings.find(v => v.id === e.value);
    this.dataSource2.data = data;
    this.fCheck[row] = true;
  }

  deleteFloor(id) {
    let data = this.dataSource2.data;
    const floor = data.find(v => v.id === id);
    const row = data.indexOf(floor);
    if (!data[row].deletable) {
      this.notifierService.notify(
        'error',
        'Không thể xoá tầng đang có người ở'
      );
      return;
    }
    if (id < 0) {
      data = data.filter((v, k) => k !== row);
      this.fCheck = this.fCheck.filter((v, k) => k !== row);
      this.dataSource2.data = data;
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
      this.floorService.deleteFloor(id).subscribe(
        success => console.log(success),
        error => {
          // console.log(error);
          data = data.filter((v, k) => k !== row);
          this.fCheck = this.fCheck.filter((v, k) => k !== row);
          this.dataSource2.data = data;
          if (
            this.fpaginator.pageIndex * this.fpaginator.pageSize ===
              data.length &&
            this.fpaginator.pageIndex > 0
          ) {
            this.fpaginator.previousPage();
          }
          this.notifierService.notify('success', 'Xoá thành công');
          this.loadRoom();
        }
      );
    });
  }

  checkValidFloor(): boolean {
    const data = this.dataSource2.data;
    const size = data.length;
    const isChange = this.fCheck.filter(v => v === true);
    if (isChange.length === 0) {
      return;
    }
    for (let i = 0; i < size - 1; i++) {
      if (i === 0) {
        if (data[i].building === null) {
          document.getElementById('fname' + i).focus();
          this.notifierService.notify('warning', 'Bạn phải chọn toà nhà');
          return false;
        }

        if (data[i].name === '') {
          document.getElementById('fname' + i).focus();
          this.notifierService.notify('warning', 'Bạn phải nhập tên tầng');
          return false;
        }
      }
      for (let j = i + 1; j < size; j++) {
        if (data[j].building === null) {
          document.getElementById('fname' + j).focus();
          this.notifierService.notify('warning', 'Bạn phải chọn toà nhà');
          return false;
        }

        if (data[j].name === '') {
          document.getElementById('fname' + j).focus();
          this.notifierService.notify('warning', 'Bạn phải nhập tên tầng');
          return false;
        }

        if (
          data[i].name === data[j].name &&
          data[i].building.id === data[j].building.id
        ) {
          document.getElementById('fname' + j).focus();
          this.notifierService.notify(
            'warning',
            'Hai tầng trong một toà nhà không được trùng tên'
          );
          return false;
        }
      }
    }
    return true;
  }

  // =============================== ROOM ===================================================
  loadRoom() {
    this.rooms = [];
    this.roomService.getListRoom().subscribe(rooms => {
      this.rLength = rooms.length;
      for (let i = 0; i < this.rLength; i++) {
        this.rCheck.push(false);
        this.rooms.push({
          ...rooms[i],
          deletable: this.checkDeleteRoom(rooms[i])
        });
      }
      this.dataSource3 = new MatTableDataSource(this.rooms);
      this.dataSource3.paginator = this.rpaginator;
      this.dataSource3.filterPredicate = (data, filter) => {
        let dataStr =
          data.id +
          data.name +
          data.area +
          data.building.name +
          data.floor.name;
        dataStr +=
          data.status === '0'
            ? 'Trống'
            : data.status === '1'
            ? 'Đang cho thuê'
            : 'Đã bán';
        dataStr += data.disable ? 'Mở' : 'Đóng';
        dataStr = dataStr.toLowerCase();
        return dataStr.indexOf(filter) !== -1;
      };
      if (this.rsearchData !== '') {
        this.rFilter(this.rsearchData);
      }
      this.dataSource3.sort = this.rsort;
    });
  }

  rFilter(filterValue: string) {
    this.dataSource3.filter = filterValue.trim().toLowerCase();
    this.rsearchData = filterValue;
  }

  selectFloorChange(e, id) {
    const data = this.dataSource3.data;
    const r = data.find(v => v.id === id);
    const row = data.indexOf(r);
    data[row].floor = this.floors.find(v => v.id === e.value);
    data[row].building = data[row].floor.building;
    this.dataSource3.data = data;
    this.rCheck[row] = true;
  }

  selectRoomTypeChange(e, id) {
    const data = this.dataSource3.data;
    const r = data.find(v => v.id === id);
    const row = data.indexOf(r);
    data[row].roomType = e.value;
    this.dataSource3.data = data;
    this.rCheck[row] = true;
  }

  deleteRoom(id) {
    let data = this.dataSource3.data;
    const room = data.find(v => v.id === id);
    const row = data.indexOf(room);
    if (!data[row].deletable) {
      this.notifierService.notify(
        'error',
        'Không thể đóng căn hộ đang có người ở'
      );
      return;
    }

    if (id < 0) {
      data = data.filter((v, k) => k !== row);
      this.dataSource3.data = data;
      return;
    }

    this.roomService.deleteRoom(id).subscribe(
      success => console.log(success),
      error => {
        data[row].disable = !data[row].disable;
        this.dataSource3.data = data;
      }
    );
  }

  checkValidRoom(): boolean {
    const data = this.dataSource3.data;
    const size = data.length;
    const isChange = this.rCheck.filter(v => v === true);
    if (isChange.length === 0) {
      return;
    }
    for (let i = 0; i < size - 1; i++) {
      if (i === 0) {
        if (data[i].floor === null) {
          document.getElementById('rname' + i).focus();
          this.notifierService.notify('warning', 'Bạn phải toà nhà và tầng');
          return false;
        }

        if (data[i].name === '') {
          document.getElementById('rname' + i).focus();
          this.notifierService.notify('warning', 'Bạn phải nhập tên căn hộ');
          return false;
        }
        if (data[i].area <= 0) {
          document.getElementById('rarea' + i).focus();
          this.notifierService.notify(
            'warning',
            'Bạn phải nhập diện tích căn hộ lớn hơn 0'
          );
          return false;
        }
      }
      for (let j = i + 1; j < size; j++) {
        if (data[j].floor === null) {
          document.getElementById('rname' + i).focus();
          this.notifierService.notify(
            'warning',
            'Bạn phải chọn toà nhà và tầng'
          );
          return false;
        }

        if (data[j].name === '') {
          document.getElementById('rname' + i).focus();
          this.notifierService.notify('warning', 'Bạn phải nhập tên căn hộ');
          return false;
        }

        if (data[j].area <= 0) {
          document.getElementById('rarea' + i).focus();
          this.notifierService.notify(
            'warning',
            'Bạn phải nhập diện tích căn hộ lớn hơn 0'
          );
          return false;
        }
        if (
          data[i].name === data[j].name &&
          data[i].building.id === data[j].building.id &&
          data[i].floor.id === data[j].floor.id
        ) {
          const name = document.getElementById('rname' + i);
          if (name !== null) {
            name.focus();
          }
          this.notifierService.notify(
            'warning',
            'Hai căn hộ trong một toà nhà không được trùng tên'
          );
          return false;
        }
      }
    }
    return true;
  }

  import() {
    const dialogRef = this.dialog.open(PopUpComponent, {
      data: 'upload-room',
      width: '450px',
      // role: 'alertdialog',
      // disableClose: true,
      position: { top: '50px' }
    });

    dialogRef.afterClosed().subscribe(result => {
      if (result === true) {
        this.loadData();
      }
    });
  }
}
