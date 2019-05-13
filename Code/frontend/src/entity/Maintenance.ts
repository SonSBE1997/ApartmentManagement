import {DeviceGroup} from './DeviceGroup';
import {MaintenanceDetail} from './MaintenanceDetail';
import {MaintenancePersonal} from './MaintenancePersonal';

export interface Maintenance {
  id: number;
  maintenanceDate: Date;
  description: string;
  maintenancePrice: number;
  isExecuted: boolean;
  numberPersonnel: number;
  paid: boolean;
  deviceGroup: DeviceGroup;
  details: MaintenanceDetail[];
  persons: MaintenancePersonal[];
  supervisor: string;
}
