import { Component, OnInit } from '@angular/core';
import { NgForm } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { data } from 'jquery';
import { ToastrService } from 'ngx-toastr';
import { max } from 'rxjs';
import { Author } from '../_class/author';
import { Book } from '../_class/book';
import { Filter } from '../_class/filter';
import { Publisher } from '../_class/publisher';
import { AuthorService } from '../_service/author.service';
import { BookService } from '../_service/book.service';
import { PublisherService } from '../_service/publisher.service';

@Component({
  selector: 'app-book-type',
  templateUrl: './book-type.component.html',
  styleUrls: ['./book-type.component.css']
})
export class BookTypeComponent implements OnInit {
  genre !: string;
  genreTitle!: string;
  books !: Book[];
  bookTemp!: Book[];
  bookPageNumber: number[] = [1];
  bookPageSize = 9;
  filter: Filter = new Filter();
  publishers !: Publisher[];
  authors !: Author[];


  constructor(
    private route: ActivatedRoute,
    private bookService: BookService,
    private publisherService: PublisherService,
    private authorService: AuthorService,
    private toastr: ToastrService
    ) { }

  ngOnInit(): void {
    this.createFilter();
    this.genre = this.route.snapshot.params['type'];
    this.filter.genre = this.genre;
    this.loadData(this.filter);
    this.publisherService.getPublishersList().subscribe(
      (data: any) => {
        this.publishers= data['data'];
      }
    )
    this.authorService.getAuthorsList().subscribe(
      (data: any) => {
        this.authors= data['data'];
      }
    )

    if (this.genre == 'Sach_Giao_Khoa') {
      this.genreTitle = 'Sách giáo khoa';
    } else {
      if (this.genre == 'Ky_Nang_Song') {
        this.genreTitle = 'Kỹ năng sống';
      } else {
        if (this.genre == 'Tieu_Thuyet') {
          this.genreTitle = 'Tiểu thuyết';
        } else {
          if (this.genre == 'Light_Novel') {
            this.genreTitle = 'Light Novel';
          } else {
            if (this.genre == 'Flash_Card') {
              this.genreTitle = 'Flash Card';
            } else {
              if (this.genre == 'Kinh_Te') {
                this.genreTitle = 'Kinh tế';
              } else {
                if (this.genre == 'Ton_Giao') {
                  this.genreTitle = 'Tôn giáo';
                } else {
                  if (this.genre == 'Tu_Dien') {
                    this.genreTitle = 'Từ điển';
                  } else {
                    if (this.genre == 'Bao_Tap_Chi') {
                      this.genreTitle = 'Báo - Tạp chí';
                    } else {
                      this.genreTitle = 'Manga';
                    }
                  }
                }
              }
            }
          }
        }
      }
    }
  }
  loadData(filter:Filter){
    console.log(filter);
    this.bookService.filterBook(filter).subscribe(
      (data: any) => {
        this.bookTemp = data['data'];
        this.bookPageNumber = [1];
        console.log(data);

        if(data['data'] == null) {
          this.toastr.info('Không tìm thấy sách phù hợp');
        }

        if (this.bookTemp != null) {
          this.pagingBook(1);

          var bookNumber = this.bookTemp.length;

          var count = Math.floor(bookNumber / this.bookPageSize);
          if (bookNumber % this.bookPageSize != 0) {
            count++;
          }
          for (var i = 2; i <= count; i++) {
            this.bookPageNumber.push(i);
          } 
        } else {
          this.books = this.bookTemp;
        }
      }
    );
  }

  pagingBook(pageNo: number) {
      var fromIndex = (pageNo - 1) * this.bookPageSize;
      this.books = this.bookTemp.slice(fromIndex, Math.min(fromIndex + this.bookPageSize, this.bookTemp.length));
  }

  createFilter(){
    this.filter.minPrice = -1;
    this.filter.maxPrice = -1;
    this.filter.publicationYear = -1;
    this.filter.publisherIds = [];
    this.filter.authorIds = [];
    this.filter.type= "Bia_Mem";
  }

  price100000(){
    this.filter.maxPrice = 100000;
    this.filter.minPrice = -1;
    this.loadData(this.filter);
  }
  priceto100000from200000(){
    this.filter.minPrice = 100000;
    this.filter.maxPrice = 200000;
    this.loadData(this.filter);
  }
  priceGreater200000(){
    this.filter.maxPrice = -1;
    this.filter.minPrice = 200000;
    this.loadData(this.filter);
  }
  priceEnter(price: NgForm){
    this.filter.minPrice = price.value.minPrice;
    this.filter.maxPrice = price.value.maxPrice;
    this.loadData(this.filter);
  }
  yearEnter(year: NgForm){
    this.filter.publicationYear = year.value.publicationYear;
    this.loadData(this.filter);
  }

  checkPublisher(id: number){
    if(this.filter.publisherIds.length == 0){
      this.filter.publisherIds.push(id);
    }
    else {
      let temp = 0;
      for(let i = 0 ;i<this.filter.publisherIds.length;i++){
        if(this.filter.publisherIds[i] == id){
          temp = 1;
          this.filter.publisherIds.splice(i,1);
          break;
        }
      }
      if(temp == 0){
        this.filter.publisherIds.push(id);
      }

    }
    this.loadData(this.filter);
  }

  checkAuthor(id: number){
    if(this.filter.authorIds.length == 0){
      this.filter.authorIds.push(id);
    }
    else {
      let temp = 0;
      for(let i = 0 ;i<this.filter.authorIds.length;i++){
        if(this.filter.authorIds[i] == id){
          temp = 1;
          this.filter.authorIds.splice(i,1);
          break;
        }
      }
      if(temp == 0){
        this.filter.authorIds.push(id);
      }

    }
    this.loadData(this.filter);
  }
  
  checkType(type: string){
    this.filter.type= type;
    this.loadData(this.filter);
  }
}
