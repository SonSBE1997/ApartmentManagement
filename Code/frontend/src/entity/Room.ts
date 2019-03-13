import { Building } from './Building';
import { Floor } from './Floor';

export interface Room {
  id: number;
  name: string;
  area: number;
  status: string;
  building: Building;
  floor: Floor;
}
