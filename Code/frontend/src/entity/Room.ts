import { Building } from './Building';
import { Floor } from './Floor';
import { Household } from './Household';

export interface Room {
  id: number;
  name: string;
  area: number;
  status: string;
  building: Building;
  floor: Floor;
  households: Household[];
  disable: boolean;
}
