import { Service } from './Service';

export interface ServiceType {
  id: number;
  name: string;
  unit: string;
  price: number;
  supplier: string;
  services: Service[];
  increase: string;
  priceList: string[];
}
