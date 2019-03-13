import { Floor } from './Floor';

export interface Building {
  id: number;
  name: string;
  floors: Floor[];
}
