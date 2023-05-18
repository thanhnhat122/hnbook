import { Data } from "@angular/router";
import { User } from "./user";

export class Order {
  id!: number;
  email!: string;
  orderDate!: Date;
  totalPrice!:any;
  status!: string;
  user !: User;

  sTotalPrice !: string;
}
