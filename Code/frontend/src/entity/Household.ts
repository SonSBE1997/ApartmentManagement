import { Employee } from './Employee';

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
  status: false;
  employee: Employee;
}
