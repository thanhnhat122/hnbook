import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Book } from 'src/app/_class/book';
import { BookService } from 'src/app/_service/book.service';

@Component({
  selector: 'app-create-book',
  templateUrl: './create-book.component.html',
  styleUrls: ['./create-book.component.css']
})
export class CreateBookComponent implements OnInit {

  book: Book = new Book();
  selectedFile!: File;
  imageArray: string[] = [];
  constructor(private bookService: BookService, private router: Router) { }

  ngOnInit(): void {
  }

  onFileSelected(event: any) {
    this.selectedFile = <File>event.target.files[0];
    if (this.imageArray.length == 0) {
      this.bookService.uploadBookImage(this.book.isbn, '0', this.selectedFile).subscribe(data => {
        this.imageArray.push(data['data']);
        this.book.image = data['data'];
        console.log(data);
        console.log(this.imageArray);
      });
    } else {
      var count = this.imageArray.length;
      this.bookService.uploadBookImage(this.book.isbn, this.imageArray.length.toString(), this.selectedFile).subscribe(data => {
        this.imageArray.push(data['data']);
        this.book.image = this.book.image + ', ' + data['data'];
        console.log(data);
        console.log(this.imageArray);
      });
    }
  }

  saveBook() {
    this.bookService.createBook(this.book).subscribe(data => {
      console.log(data);
      this.goToBookList();
    },
    error => console.log(error));
  }

  goToBookList() {
    this.router.navigate(['/staff'])
  }

  onSubmit() {
    console.log(this.book);
    this.saveBook();
  }
}
