import { Employee } from './Employee';
import { User } from './User';
import { Room } from './Room';

export interface Household {
  id: number;
  fullName: string;
  idCard: string;
  address: string;
  phoneNumber: string;
  comeDate: Date;
  leaveDate: Date;
  hire: boolean;
  price: number;
  deposit: number;
  depositDate: Date;
  status: boolean;
  employee: Employee;
  disable: boolean;
  user: User[];
  room: Room;
}
