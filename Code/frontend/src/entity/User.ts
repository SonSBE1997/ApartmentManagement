import { Household } from './Household';

export interface User {
  id: number;
  name: string;
  gender: boolean;
  phoneNumber: string;
  email: string;
  address: string;
  idCard: string;
  head: boolean;
  leaveDate: Date;
  leave: boolean;
  dateOfBirth: Date;
  disable: boolean;
  household: Household;
}
