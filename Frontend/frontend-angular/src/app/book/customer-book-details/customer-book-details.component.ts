import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { ToastrService } from 'ngx-toastr';
import { ConnectableObservable } from 'rxjs';
import { Book } from 'src/app/_class/book';
import { Review } from 'src/app/_class/review';
import { BookService } from 'src/app/_service/book.service';
import { ReviewService } from 'src/app/_service/review.service';
import { UserAuthService } from 'src/app/_service/user-auth.service';

@Component({
  selector: 'app-customer-book-details',
  templateUrl: './customer-book-details.component.html',
  styleUrls: ['./customer-book-details.component.css']
})
export class CustomerBookDetailsComponent implements OnInit {

  id!: number;
  book: Book = new Book();
  genreTemp!: string;
  quantity = 1;
  sameGenreBooks!: Book[];
  sameGenreBookTemp!: Book[];
  sameGenreBookPageNumber: number[] = [1];
  sameGenreBookPageSize = 7;
  relateBooks!: Book[];
  reviews!: Review[];
  reviewTemp!: Review[];
  reviewPageNumber: number[] = [1];
  reviewPageSize = 5;
  review: Review = new Review();
  totalRating = 0;
  avgRating = 0;
  avgRating_5 = 0;
  avgRating_4 = 0;
  avgRating_3 = 0;
  avgRating_2 = 0;
  avgRating_1 = 0;
  mainImage!: string;

  constructor(
    private route: ActivatedRoute,
    private bookService: BookService,
    private reviewService: ReviewService,
    private userAuthService: UserAuthService,
    private router: Router,
    private toastr: ToastrService) { }

  ngOnInit(): void {
    this.id = this.route.snapshot.params['id'];

    this.bookService.getCustomerBookById(this.id).subscribe(
      (data: any) => {
        this.book = data['data'];

        this.mainImage = this.book.imageArray[0];

        this.getRelateBook(this.book.title);

        if (this.book.genre == 'Sach_Giao_Khoa') {
          this.genreTemp = 'Sách giáo khoa';
        } else {
          if (this.book.genre == 'Ky_Nang_Song') {
            this.genreTemp = 'Kỹ năng sống';
          } else {
            if (this.book.genre == 'Tieu_Thuyet') {
              this.genreTemp = 'Tiểu thuyết';
            } else {
              if (this.book.genre == 'Light_Novel') {
                this.genreTemp = 'Light Novel';
              } else {
                if (this.book.genre == 'Flash_Card') {
                  this.genreTemp = 'Flash Card';
                } else {
                  if (this.book.genre == 'Kinh_Te') {
                    this.genreTemp = 'Kinh tế';
                  } else {
                    if (this.book.genre == 'Ton_Giao') {
                      this.genreTemp = 'Tôn giáo';
                    } else {
                      if (this.book.genre == 'Tu_Dien') {
                        this.genreTemp = 'Từ điển';
                      } else {
                        if (this.book.genre == 'Bao_Tap_Chi') {
                          this.genreTemp = 'Báo - Tạp chí';
                        } else {
                          this.genreTemp = 'Manga';
                        }
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

        var number = this.book.price;
        this.book.stringPrice = number.toLocaleString(undefined, { minimumFractionDigits: 0, maximumFractionDigits: 2 });

        this.getSameGenreBook(this.book.genre);
      });

    this.getReview();
  }

  getRelateBook(title: string) {
    var titleSplit = title.split("-", 1);
    this.bookService.getSimpleBookInSameSeries(titleSplit[0]).subscribe(
      (data: any) => {
        this.relateBooks = data['data'];

        for (let i = 0; i < this.relateBooks.length; i++) {
          if (this.relateBooks[i].id == this.book.id) {
            this.relateBooks.splice(i,1);
            break;
          }
        }

        for (let i = 0; i < this.relateBooks.length; i++) {
          var number = this.relateBooks[i].price;
          this.relateBooks[i].stringPrice = number.toLocaleString(undefined, { minimumFractionDigits: 0, maximumFractionDigits: 2 })
        }
      }
    );
  }

  getSameGenreBook(genre: string) {
    this.bookService.getSimpleBookByGenre(genre).subscribe(
      (data: any) => {
        this.sameGenreBookTemp = data['data'];

        for (let i = 0; i < this.sameGenreBookTemp.length; i++) {
          if (this.sameGenreBookTemp[i].id == this.book.id) {
            this.sameGenreBookTemp.splice(i,1);
            break;
          }
        }

        for (let i = 0; i < this.sameGenreBookTemp.length; i++) {
          var number = this.sameGenreBookTemp[i].price;
          this.sameGenreBookTemp[i].stringPrice = number.toLocaleString(undefined, { minimumFractionDigits: 0, maximumFractionDigits: 2 })
        }
        
        this.pagingSameGenreBook(1);

        var count = Math.floor(this.sameGenreBookTemp.length / this.sameGenreBookPageSize);
        if (this.sameGenreBookTemp.length % this.sameGenreBookPageSize != 0) {
          count++;
        }
        for (var i = 2; i <= count; i++) {
          this.sameGenreBookPageNumber.push(i);
        }
      }
    );
  }

  getReview() {
    this.reviewService.getReviewByBookId(this.id).subscribe(
      (data: any) => {
        this.reviewTemp = data['data'];
        this.reviews = this.reviewTemp;

        this.totalRating = this.reviews.length;

        for (var i = 0; i < this.totalRating; i++) {
          this.avgRating = this.avgRating + this.reviews[i].rate;

          if (this.reviews[i].rate == 5) {
            this.avgRating_5++;
          }
          else if (this.reviews[i].rate == 4) {
            this.avgRating_4++;
          }
          else if (this.reviews[i].rate == 3) {
            this.avgRating_3++;
          }
          else if (this.reviews[i].rate == 2) {
            this.avgRating_2++;
          }
          else {
            this.avgRating_1++;
          }
        }

        this.avgRating = parseFloat((this.avgRating / this.totalRating).toFixed(2));
        this.avgRating_5 = parseFloat((this.avgRating_5 / this.totalRating * 100).toFixed(2));
        this.avgRating_4 = parseFloat((this.avgRating_4 / this.totalRating * 100).toFixed(2));
        this.avgRating_3 = parseFloat((this.avgRating_3 / this.totalRating * 100).toFixed(2));
        this.avgRating_2 = parseFloat((this.avgRating_2 / this.totalRating * 100).toFixed(2));
        this.avgRating_1 = parseFloat((this.avgRating_1 / this.totalRating * 100).toFixed(2));

        this.pagingReview(1);

        var count = Math.floor(this.totalRating / this.reviewPageSize);
        if (this.totalRating % this.reviewPageSize != 0) {
          count++;
        }
        for (var i = 2; i <= count; i++) {
          this.reviewPageNumber.push(i);
        }
        console.log(this.reviewPageNumber);
      }
    );
  }

  pagingReview(pageNo: number) {
    var fromIndex = (pageNo - 1) * this.reviewPageSize;
    this.reviews = this.reviewTemp.slice(fromIndex, Math.min(fromIndex + this.reviewPageSize, this.reviewTemp.length));
  }

  pagingSameGenreBook(pageNo: number) {
    var fromIndex = (pageNo - 1) * this.sameGenreBookPageSize;
    this.sameGenreBooks = this.sameGenreBookTemp.slice(fromIndex, Math.min(fromIndex + this.sameGenreBookPageSize, this.sameGenreBookTemp.length));
  }

  loadCustomerBookDetails(id: number) {
    this.id = id;
    this.quantity = 1;
    this.sameGenreBookPageNumber = [1];
    this.reviewPageNumber = [1];
    this.totalRating = 0;
    this.avgRating = 0;
    this.avgRating_5 = 0;
    this.avgRating_4 = 0;
    this.avgRating_3 = 0;
    this.avgRating_2 = 0;
    this.avgRating_1 = 0;

    this.bookService.getCustomerBookById(id).subscribe(
      (data: any) => {
        this.book = data['data'];

        this.mainImage = this.book.imageArray[0];

        this.getRelateBook(this.book.title);

        if (this.book.genre == 'Sach_Giao_Khoa') {
          this.genreTemp = 'Sách giáo khoa';
        } else {
          if (this.book.genre == 'Ky_Nang_Song') {
            this.genreTemp = 'Kỹ năng sống';
          } else {
            if (this.book.genre == 'Tieu_Thuyet') {
              this.genreTemp = 'Tiểu thuyết';
            } else {
              if (this.book.genre == 'Light_Novel') {
                this.genreTemp = 'Light Novel';
              } else {
                if (this.book.genre == 'Flash_Card') {
                  this.genreTemp = 'Flash Card';
                } else {
                  if (this.book.genre == 'Kinh_Te') {
                    this.genreTemp = 'Kinh tế';
                  } else {
                    if (this.book.genre == 'Ton_Giao') {
                      this.genreTemp = 'Tôn giáo';
                    } else {
                      if (this.book.genre == 'Tu_Dien') {
                        this.genreTemp = 'Từ điển';
                      } else {
                        if (this.book.genre == 'Bao_Tap_Chi') {
                          this.genreTemp = 'Báo - Tạp chí';
                        } else {
                          this.genreTemp = 'Manga';
                        }
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

        var number = this.book.price;
        this.book.stringPrice = number.toLocaleString(undefined, { minimumFractionDigits: 0, maximumFractionDigits: 2 });

        this.getSameGenreBook(this.book.genre);
      });

    this.getReview();
  }

  increaseQuantity() {
    if (this.quantity >= this.book.remain) {
      this.quantity = this.book.remain;
    }
    else {
      this.quantity++;
    }
  }

  decreaseQuantity() {
    if (this.quantity <= 1) {
      this.quantity = 1;
    }
    else {
      this.quantity--;
    }
  }

  addItemToCart() {
    var email = this.userAuthService.getEmail();
    if (email != null) {
      if (this.validQuantity(this.quantity)) {
        this.bookService.addItemToCart(email, this.id, this.quantity).subscribe(
          (data: any) => {
            this.toastr.success('Thêm vào giỏ hàng thành công')
            window.location.reload();
          }
        );
      }
    }
    else {
      this.toastr.warning('Vui lòng đăng nhập!')
      this.router.navigate(['/login'])
    }
    this.quantity = 1;
  }

  addItemToCart1() {
    var email = this.userAuthService.getEmail();
    if (email != null) {
      if (this.validQuantity(this.quantity)) {
        this.bookService.addItemToCart(email, this.id, this.quantity).subscribe(
          (data: any) => {
            this.router.navigate(['/cart-detail']).then(() => {
              window.location.reload();
            });
            this.toastr.success('Thêm vào giỏ hàng thành công')
          }
        );
      }
    }
    else {
      this.toastr.warning('Vui lòng đăng nhập!')
      this.router.navigate(['/login'])
    }
    this.quantity = 1;
  }

  validQuantity(quantity: number) {
    console.log(quantity);
    if (quantity == null) {
      this.toastr.warning('Chưa nhập số lượng mua!')
      return false;
    }
    else if (quantity < 1) {
      this.toastr.warning('Số lượng mua phải lớn hơn 0!')
      return false;
    }
    else if (quantity > this.book.remain) {
      this.toastr.warning('Chỉ còn lại ' + this.book.remain + ' sản phẩm!')
      return false;
    }
    else if (quantity % 1 != 0) {
      this.toastr.warning('Số lượng mua phải là số nguyên!')
      return false;
    }

    return true;
  }

  changeImage(imageURL: string) {
    this.mainImage = imageURL;
  }

  // action(actionNumber: number) {
  //   // Minus item button
  //   if (actionNumber == 1) {
  //     // mouse over
  //     this.action_1 = 2;
  //   } else if (actionNumber == 2) {
  //     // mouse leave
  //     this.action_1 = 1;
  //   // Plus item button
  //   } else if (actionNumber == 3) {
  //     // mouse over
  //     this.action_2 = 2;
  //   } else {
  //     // mouse leave
  //     this.action_2 = 1;
  //   }
  //   console.log(actionNumber);
  // }
}
