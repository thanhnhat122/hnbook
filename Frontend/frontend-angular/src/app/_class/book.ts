import { Author } from "./author";
import { Publisher } from "./publisher";

export class Book {
    id!: number;
    authorId!: number;
    publisherId!: number;
    title!: string;
    isbn!: string;
    genre!: string;
    type!: string;
    publicationYear!: number;
    price!: number;
    remain!: number;
    sell!: number;
    size!: string;
    pageNumber!: number;
    image!: string;
    description!: string;
    author!: Author;
    publisher!: Publisher
    totalRate!: number;
    ratingNumber!: number;

    stringPrice !: string;
    imageArray!: string[];
}
