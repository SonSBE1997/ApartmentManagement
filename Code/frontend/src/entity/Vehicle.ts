import { Card } from './Card';
import { User } from './User';
import { VehicleType } from './VehicleType';

export interface Vehicle {
  plateNumber: string;
  card: Card;
  vehicleType: VehicleType;
  user: User;
}
