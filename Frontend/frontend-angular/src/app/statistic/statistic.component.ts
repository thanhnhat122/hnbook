import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { data } from 'jquery';
import { Book } from '../_class/book';
import { StatisticGenre } from '../_class/statistic-genre';
import { BookService } from '../_service/book.service';

@Component({
  selector: 'app-statistic',
  templateUrl: './statistic.component.html',
  styleUrls: ['./statistic.component.css']
})
export class StatisticComponent implements OnInit {

  bookSold !: number;
  priceSold !: number;
  numOfBestSeller !: number[];
  numOfBestGenre !: number[];
  bestGenre !: string[];
  priceEachGenre !: number[];
  bestSeller !: Book[];
  statisticGenre !: Array<StatisticGenre>;

  curdate !: string;


  month = new Date().getMonth()+1;
  year = new Date().getFullYear();

  showPriceSold !: string;

  constructor(
    private route: ActivatedRoute, 
    private bookService: BookService
    ) { }

  ngOnInit(): void {

    this.curdate = (new Date().getMonth() + 1).toString() + '/' + new Date().getFullYear().toString();

    this.getBookSold(this.month, this.year);
    this.getBookPriceSold(this.month, this.year);
    this.getBestSeller(this.month, this.year);
    this.getNumOfBestGenre(this.month, this.year);
    
  }

  getBookSold(month: number, year: number) {
    this.bookService.totalBookSoldInYear(year).subscribe(
      (data: any) => {
        this.bookSold = data['data'];
      }
    );
  }

  getBookPriceSold(month: number, year: number) {
    this.bookService.totalPriceInYear(year).subscribe(
      (data: any) => {
        this.priceSold = data['data'];

        var number = this.priceSold;
        this.showPriceSold = number.toLocaleString(undefined, { minimumFractionDigits: 0, maximumFractionDigits: 2 })
      }
    );
  }



  getBestSeller(month: number, year: number) {
    this.bookService.bestSeller(month, year).subscribe(
      (data: any) => {
        this.bestSeller = data['data'];

        this.getNumOfBestSeller(month, year);

      }
    );
  }

  getNumOfBestSeller(month: number, year: number) {
    this.bookService.numOfBestSeller(month, year).subscribe(
      (data: any) => {
        this.numOfBestSeller = data['data'];

        console.log(this.numOfBestSeller);

        for(let i = 0; i < this.numOfBestSeller.length; i++) {
          this.bestSeller[i].sell = this.numOfBestSeller[i];
        }
        
      }
    );
  }

  getNumOfBestGenre(month: number, year: number) {
    this.bookService.numOfBestGenre(month, year).subscribe(
      (data: any) => {
        this.numOfBestGenre = data['data'];

        this.getBestGenre(this.month, this.year);
    
      }
    );
  }

  getBestGenre(month: number, year: number) {
    this.bookService.bestGenre(month, year).subscribe(
      (data: any) => {
        this.bestGenre = data['data'];

        this.getTotalPriceForEachGenre(this.month, this.year);
  
      }
    );
  }

  getTotalPriceForEachGenre(month: number, year: number) {
    this.bookService.priceForEachGenre(month, year).subscribe(
      (data: any) => {
        this.priceEachGenre = data['data'];

        this.getStatisticGenre();
      }
    );
  }

  getStatisticGenre() {
    this.statisticGenre = new Array();
    for(let i = 0; i < this.numOfBestGenre.length; i++) {
      let sttGenre = new  StatisticGenre();
      sttGenre.genre = this.bestGenre[i];
      if (sttGenre.genre == 'Sach_Giao_Khoa') {
        sttGenre.genre = 'Sách giáo khoa';
      } else {
        if (sttGenre.genre == 'Ky_Nang_Song') {
          sttGenre.genre = 'Kỹ năng sống';
        } else {
          if (sttGenre.genre == 'Tieu_Thuyet') {
            sttGenre.genre = 'Tiểu thuyết';
          } else {
            if (sttGenre.genre == 'Light_Novel') {
              sttGenre.genre = 'Light Novel';
            } else {
              if (sttGenre.genre == 'Flash_Card') {
                sttGenre.genre = 'Flash Card';
              } else {
                if (sttGenre.genre == 'Kinh_Te') {
                  sttGenre.genre = 'Kinh tế';
                } else {
                  if (sttGenre.genre == 'Ton_Giao') {
                    sttGenre.genre = 'Tôn giáo';
                  } else {
                    if (sttGenre.genre == 'Tu_Dien') {
                      sttGenre.genre = 'Từ điển';
                    } else {
                      if (sttGenre.genre == 'Bao_Tap_Chi') {
                        sttGenre.genre = 'Báo - Tạp chí';
                      }
                    }
                  }
                }
              }
            }
          }
        }
      }
      sttGenre.quantity = this.numOfBestGenre[i];
      var number = this.priceEachGenre[i];
      sttGenre.price= number.toLocaleString(undefined, { minimumFractionDigits: 0, maximumFractionDigits: 2 })

      this.statisticGenre.push(sttGenre);
    }
  }

}


