import { Book } from "./book";

export class OrderItem {
  id!: number;
  orderId!: number;
  bookId!: number;
  quantity!: number;
  book !: Book;
  totalPrice!: number;

  sPrice !: string;
  sTotalPrice!: string;
}
