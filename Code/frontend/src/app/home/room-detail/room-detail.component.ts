import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { mergeMap } from 'rxjs/operators';
import { RoomService } from 'src/app/service/room.service';
import { Room } from 'src/entity/Room';

@Component({
  selector: 'app-room-detail',
  templateUrl: './room-detail.component.html',
  styleUrls: ['./room-detail.component.scss']
})
export class RoomDetailComponent implements OnInit {
  room: Room;

  constructor(
    private activatedRoute: ActivatedRoute,
    private roomService: RoomService
  ) {}

  ngOnInit() {
    this.activatedRoute.paramMap
      .pipe(
        mergeMap(params => {
          const id = +params.get('id');
          return this.roomService.getRoomById(id);
        })
      )
      .subscribe(r => {
        this.room = r;
        // console.log(r);
      });
  }
}
