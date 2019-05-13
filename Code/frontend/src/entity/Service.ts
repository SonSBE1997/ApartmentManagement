import { Room } from './Room';
import { Employee } from './Employee';
import { ServiceType } from './ServiceType';

export interface Service {
  id: number;
  collectMonth: string;
  paymentDate: Date;
  detail: string;
  price: number;
  description: string;
  paid: boolean;
  createdDate: Date;
  room: Room;
  employee: Employee;
  serviceType: ServiceType;
  fullName: string;
}
