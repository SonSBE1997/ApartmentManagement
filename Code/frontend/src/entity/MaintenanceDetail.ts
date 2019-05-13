import {Device} from './Device';
import { Room } from './Room';

export class MaintenanceDetail {
  id: number;
  price: number;
  description: string;
  device: Device;
  location: string;
}
