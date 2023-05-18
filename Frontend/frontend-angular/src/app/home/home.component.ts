import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { HeaderComponent } from '../header/header.component';
import { Book } from '../_class/book';
import { BookService } from '../_service/book.service';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {

  books!: Book[];
  bookTemp!: Book[];
  bookPageNumber: number[] = [1];
  bookPageSize = 15;

  currency !: string;

  constructor(private bookService: BookService, private router: Router) { }

  ngOnInit(): void {
    this.getBooks();
  }


  private getBooks() {
    this.bookService.getAllSimpleBook().subscribe(
      (data: any) => {
        this.bookTemp = data['data'];
        console.log(data['data']);
        var bookNumber = this.bookTemp.length;

        for (let i = 0; i < bookNumber; i++) {
          var number = this.bookTemp[i].price;
          this.bookTemp[i].stringPrice = number.toLocaleString(undefined, { minimumFractionDigits: 0, maximumFractionDigits: 2 })
        }

        this.pagingBook(1);

        var count = Math.floor(bookNumber / this.bookPageSize);
        if (bookNumber % this.bookPageSize != 0) {
          count++;
        }
        for (var i = 2; i <= count; i++) {
          this.bookPageNumber.push(i);
        }   
      });
  }

  pagingBook(pageNo: number) {
    var fromIndex = (pageNo - 1) * this.bookPageSize;
    this.books = this.bookTemp.slice(fromIndex, Math.min(fromIndex + this.bookPageSize, this.bookTemp.length));
    //console.log(this.books);
  }
}
