import { Category } from '../categories/category';

export interface Product {
  id?: number;
  name: string;
  price: number;
  stock: number;
  category?: Category;
}
