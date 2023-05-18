import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { ToastrService } from 'ngx-toastr';
import { Book } from 'src/app/_class/book';
import { Author } from 'src/app/_class/author';
import { BookService } from 'src/app/_service/book.service';
import { AuthorService } from 'src/app/_service/author.service';
import { Publisher } from 'src/app/_class/publisher';
import { PublisherService } from 'src/app/_service/publisher.service';

@Component({
  selector: 'app-book-list',
  templateUrl: './book-list.component.html',
  styleUrls: ['./book-list.component.css']
})
export class BookListComponent implements OnInit {

  books!: Book[];
  book1: Book = new Book();
  selectedFile!: File;
  imageArray: string[] = [];
  authors!: Author[];
  publishers!: Publisher[];

  constructor(private bookService: BookService,
    private router: Router,
    private toastr: ToastrService,
    private authorService: AuthorService,
    private publisherService: PublisherService
    ) { }

  ngOnInit(): void {
    this.getBooks();
    this.book1.authorId = 1;
    this.getAuthors();
    this.book1.publisherId = 1;
    this.getPublishers();
    this.book1.type = 'Bia_Mem';
    this.book1.genre = 'Sach_Giao_Khoa';
  }
  
  getPublishers() {
    this.publisherService.getPublishersList().subscribe(
      (data: any) => this.publishers = data['data']
    );
  }

  getAuthors(){
    this.authorService.getAuthorsList().subscribe(
      (data: any) => this.authors = data['data']
    );
  }

  updateAuthorId(e: any){
    this.book1.authorId = e.target.value;
  }

  updatePublisherId(e: any){
    this.book1.publisherId = e.target.value;
  }

  updateType(e:any){
    this.book1.type = e.target.value;
  }

  updateGenre(e:any){
    this.book1.genre = e.target.value;
  }

  onFileSelected(event: any) {
    this.selectedFile = <File>event.target.files[0];
    if (this.imageArray.length == 0) {
      this.bookService.uploadBookImage(this.book1.isbn, '0', this.selectedFile).subscribe(data => {
        this.imageArray.push(data['data']);
        this.book1.image = data['data'];
        console.log(data);
        console.log(this.imageArray);
      });
    } else {
      var count = this.imageArray.length;
      this.bookService.uploadBookImage(this.book1.isbn, this.imageArray.length.toString(), this.selectedFile).subscribe(data => {
        this.imageArray.push(data['data']);
        this.book1.image = this.book1.image + ', ' + data['data'];
        console.log(data);
        console.log(this.imageArray);
      });
    }
  }

  saveBook() {
    // console.log(this.book1);
    this.bookService.createBook(this.book1).subscribe(data => {
      console.log(data);
      this.toastr.success('Thêm thành công!');
    },
    error => console.log(error));
  }

  private getBooks() {
    this.bookService.getBooksList().subscribe(
      (data: any) => { 
        this.books = data['data'];

      for(var i = 0; i < this.books.length; i++)
      {
        var bookListTemp = this.books[i].image.split(', ');
        this.books[i].image = bookListTemp[0];

        if (this.books[i].genre == 'Sach_Giao_Khoa') {
          this.books[i].genre = 'Sách giáo khoa';
        } else {
          if (this.books[i].genre == 'Ky_Nang_Song') {
            this.books[i].genre = 'Kỹ năng sống';
          } else {
            if (this.books[i].genre == 'Tieu_Thuyet') {
              this.books[i].genre = 'Tiểu thuyết';
            } else {
              if (this.books[i].genre == 'Light_Novel') {
                this.books[i].genre = 'Light Novel';
              } else {
                if (this.books[i].genre == 'Flash_Card') {
                  this.books[i].genre = 'Flash Card';
                } else {
                  if (this.books[i].genre == 'Kinh_Te') {
                    this.books[i].genre = 'Kinh tế';
                  } else {
                    if (this.books[i].genre == 'Ton_Giao') {
                      this.books[i].genre = 'Tôn giáo';
                    } else {
                      if (this.books[i].genre == 'Tu_Dien') {
                        this.books[i].genre = 'Từ điển';
                      } else {
                        if (this.books[i].genre == 'Bao_Tap_Chi')
                        this.books[i].genre = 'Báo - Tạp chí';
                      }
                    }
                  }
                }
              }
            }
          }
        }
      }
    });
  }

  bookDetails(id: number) {
    this.router.navigate(['book-details', id]);
  }

  updateBook(id: number) {
    this.router.navigate(['update-book', id]);
  }

  deleteBook(id: number) {
    this.bookService.deleteBook(id).subscribe( data => {
      console.log(data);
      this.getBooks();
    })
  }
}
