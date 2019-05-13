import { DeviceType } from './DeviceType';
import { DeviceGroup } from './DeviceGroup';
import { DeviceSpec } from './DeviceSpec';

export interface Device {
  id: number;
  name: string;
  sign: string;
  provider: string;
  installedDate: Date;
  operationDate: Date;
  unit: string;
  quantity: number;
  price: number;
  description: string;
  maintenanceCycle: number;
  status: boolean; // dang hoat dong; chua hoat dong
  deviceType: DeviceType;
  deviceGroup: DeviceGroup;
  deviceSpec: DeviceSpec[];
}
