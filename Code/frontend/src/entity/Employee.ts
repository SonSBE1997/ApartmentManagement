import { Dept } from './Dept';

export interface Employee {
  id: number;
  username: string;
  name: string;
  disable: boolean;
  dateOfBirth: Date;
  gender: boolean;
  idCard: string;
  phoneNumber: string;
  email: string;
  address: string;
  password: string;
  role: string;
  dept: Dept;
}
