import { Component, OnInit, ViewChild } from '@angular/core';
import { CardService } from 'src/app/service/card.service';
import { Card } from 'src/entity/Card';
import { CardType } from 'src/entity/CardType';
import { MatPaginator, MatSort, MatTableDataSource } from '@angular/material';

@Component({
  selector: 'app-info-card',
  templateUrl: './info-card.component.html',
  styleUrls: ['./info-card.component.scss']
})
export class InfoCardComponent implements OnInit {
  cards: Card[] = [];
  backup: Card[] = [];
  cardTypes: CardType[] = [];
  @ViewChild(MatPaginator) paginator: MatPaginator;
  @ViewChild(MatSort) sort: MatSort;
  dataSource: MatTableDataSource<Card>;
  isFilter = false;
  searchStr = '';
  selectedType = 0;
  constructor(private cardService: CardService) {}

  ngOnInit() {
    this.loadData();
  }

  loadData() {
    this.cardService.findAll().subscribe(cards => {
      cards.forEach(c => {
        this.cards.push(c);
        this.backup.push(c);
      });
      this.dataSource = new MatTableDataSource(this.cards);
      this.dataSource.paginator = this.paginator;
      this.dataSource.sort = this.sort;
      this.dataSource.filterPredicate = (data, filter) => {
        let dataStr = data.cardNumber + data.cardType.name;
        dataStr += data.user.name;
        dataStr +=
          data.vehicle !== null
            ? data.vehicle.plateNumber + data.vehicle.vehicleType.name
            : '';
        dataStr = dataStr.toLowerCase();
        return dataStr.indexOf(filter) !== -1;
      };
    });

    this.cardService.findAllCardType().subscribe(types => {
      types.forEach(t => this.cardTypes.push(t));
    });
  }

  search(filterValue: string) {
    this.searchStr = filterValue;
    this.dataSource.filter = filterValue.trim().toLowerCase();
  }

  clickFilter() {
    if (this.selectedType === 0) {
      return;
    }
    let data = this.dataSource.data;
    data = data.filter(v => v.cardType.id === this.selectedType);
    this.dataSource.data = data;
    this.isFilter = true;
    if (this.searchStr !== '') {
      this.search(this.searchStr);
    }
  }

  cancelFilter() {
    this.dataSource.data = this.backup;
    this.isFilter = false;
    if (this.searchStr !== '') {
      this.search(this.searchStr);
    }
  }

  selectTypeChange(value) {
    if (value === '-1') {
      this.selectedType = 0;
    } else {
      this.selectedType = parseInt(value, 10);
    }
  }
}
