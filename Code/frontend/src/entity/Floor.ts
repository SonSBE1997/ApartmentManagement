import { Room } from './Room';
import { Building } from './Building';

export interface Floor {
  id: number;
  name: string;
  rooms: Room[];
  building: Building;
  disable: boolean;
}
