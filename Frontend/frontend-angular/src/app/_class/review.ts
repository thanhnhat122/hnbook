import { User } from "./user";

export class Review {
    id!: number;
    bookId!: number;
    userEmail!: string;
    orderItemId!: number;
    comment!: string;
    rate!: number;
    response!: string;
    user!: User;
}
