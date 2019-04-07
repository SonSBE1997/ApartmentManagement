import { CardType } from './CardType';
import { User } from './User';
import { Employee } from './Employee';
import { Vehicle } from './Vehicle';

export interface Card {
  cardNumber: string;
  cardType: CardType;
  createdDate: Date;
  user: User;
  employee: Employee;
  vehicle: Vehicle;
}
