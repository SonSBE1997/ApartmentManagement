import {Employee} from './Employee';

export interface MaintenancePersonal {
  id: number;
  supervisor: boolean;
  employee: Employee;
}
