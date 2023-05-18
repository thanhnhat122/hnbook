import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Book } from 'src/app/_class/book';
import { BookService } from 'src/app/_service/book.service';
import { UserAuthService } from 'src/app/_service/user-auth.service';

@Component({
  selector: 'app-update-book',
  templateUrl: './update-book.component.html',
  styleUrls: ['./update-book.component.css']
})
export class UpdateBookComponent implements OnInit {

  id!: number;
  book: Book = new Book();
  selectedFile!: File | null;
  constructor(private bookService: BookService, private route: ActivatedRoute, private router: Router, private userAuthService: UserAuthService) { }

  ngOnInit(): void {
    this.id = this.route.snapshot.params['id'];

    this.bookService.getBookById(this.id).subscribe(
      (data: any) => this.book = data['data']
    );
  }

  onFileSelected(event: any) {
    this.selectedFile = <File>event.target.files[0];
  }

  onSubmit() {
    this.bookService.updateBook(this.id, this.book).subscribe ( data => {
      console.log(data);
      if(this.selectedFile != null) {
        // this.bookService.uploadBookImage(this.book.isbn, this.selectedFile).subscribe(data => {
        //   console.log(data);
        //   });
      }
      this.goToBookList();
    }, error => console.log(error));
  }

  goToBookList() {
    this.router.navigate(['/staff'])
  }
}
