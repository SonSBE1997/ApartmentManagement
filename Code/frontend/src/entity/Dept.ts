import { Employee } from './Employee';

export interface Dept {
  id: number;
  name: string;
  disable: boolean;
  employees: Employee[];
}
