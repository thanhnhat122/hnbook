import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Book } from 'src/app/_class/book';
import { BookService } from 'src/app/_service/book.service';

@Component({
  selector: 'app-book-details',
  templateUrl: './book-details.component.html',
  styleUrls: ['./book-details.component.css']
})
export class BookDetailsComponent implements OnInit {

  id!: number;
  book: Book = new Book();
  mainImage!: string;

  constructor(private route: ActivatedRoute, private bookService: BookService) { }

  ngOnInit(): void {
    this.id = this.route.snapshot.params['id'];

    this.bookService.getCustomerBookById(this.id).subscribe(
      (data: any) => { 
        this.book = data['data'];

      this.mainImage = this.book.imageArray[0];

      if (this.book.genre == 'Sach_Giao_Khoa') {
        this.book.genre = 'Sách giáo khoa';
      } else {
        if (this.book.genre == 'Ky_Nang_Song') {
          this.book.genre = 'Kỹ năng sống';
        } else {
          if (this.book.genre == 'Tieu_Thuyet') {
            this.book.genre = 'Tiểu thuyết';
          } else {
            if (this.book.genre == 'Light_Novel') {
              this.book.genre = 'Light Novel';
            } else {
              if (this.book.genre == 'Flash_Card') {
                this.book.genre = 'Flash Card';
              } else {
                if (this.book.genre == 'Kinh_Te') {
                  this.book.genre = 'Kinh tế';
                } else {
                  if (this.book.genre == 'Ton_Giao') {
                    this.book.genre = 'Tôn giáo';
                  } else {
                    if (this.book.genre == 'Tu_Dien') {
                      this.book.genre = 'Từ điển';
                    } else {
                      if (this.book.genre == 'Bao_Tap_Chi')
                      this.book.genre = 'Báo - Tạp chí';
                    } 
                  } 
                } 
              } 
            } 
          } 
        }
      }

      if (this.book.type == 'Bia_Cung') {
        this.book.type = 'Bìa cứng'
      } else {
        this.book.type = 'Bìa mềm'
      }
    });
  }
}
