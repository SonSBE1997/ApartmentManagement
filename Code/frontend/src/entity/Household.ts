import { Employee } from './Employee';
import { User } from './User';
import { Room } from './Room';

export interface Household {
  id: number;
  fullName: string;
  idCard: string;
  address: string;
  phoneNumber: string;
  email: string;
  comeDate: Date;
  leaveDate: Date;
  hire: boolean;
  price: number;
  deposit: number;
  depositDate: Date;
  status: string;
  employee: Employee;
  disable: boolean;
  users: User[];
  room: Room;
  userId: number;
  statusStr: string;
}
