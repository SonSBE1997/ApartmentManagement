import { Room } from './Room';

export interface Floor {
  id: number;
  name: string;
  rooms: Room[];
}
