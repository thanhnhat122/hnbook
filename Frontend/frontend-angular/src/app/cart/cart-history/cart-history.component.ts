import { Component, OnInit } from '@angular/core';
import { Order } from 'src/app/_class/order';
import { OrderItem } from 'src/app/_class/order-item';
import { Book } from 'src/app/_class/book';
import { CartService } from 'src/app/_service/cart.service';
import { BookService } from 'src/app/_service/book.service';
import { Router } from '@angular/router';
import { UserAuthService } from 'src/app/_service/user-auth.service';
import { data } from 'jquery';
import { Review } from 'src/app/_class/review';
import { ReviewService } from 'src/app/_service/review.service';
import { ToastrService } from 'ngx-toastr';

@Component({
  selector: 'app-cart-history',
  templateUrl: './cart-history.component.html',
  styleUrls: ['./cart-history.component.css']
})
export class CartHistoryComponent implements OnInit {

  orders !: Order[];
  orderItems !: OrderItem[];
  books !: Book[];
  email: any;
  totalPrice: any;
  book: Book = new Book();
  review: Review = new Review();

  constructor(
    private cartService: CartService,
    private bookService: BookService,
    private reviewService: ReviewService,
    private userAuthService: UserAuthService,
    private router: Router,
    private toastr: ToastrService
  ) { }

  ngOnInit(): void {
    this.email = this.userAuthService.getEmail();
    console.log(this.email);

    this.getOrder(0);
  }

  getOrder(statusNumber: number) {

    this.cartService.findOrderDTOByEmail(this.email, statusNumber).subscribe(
      (data: any) => {
        this.orders = data['data'];

        for (let order of this.orders) {
          if (order.status == 'Cho_Xac_Nhan')
            order.status = 'Chờ xác nhận';
          else if (order.status == 'Dang_Giao')
            order.status = 'Đang giao';
          else if (order.status == 'Da_Nhan')
            order.status = 'Đã nhận';
          else if (order.status == 'Da_Huy')
            order.status = 'Đã hủy';

          var number = order.totalPrice;
          order.sTotalPrice = number.toLocaleString(undefined, { minimumFractionDigits: 0, maximumFractionDigits: 2 })
        }
      }
    );
  }

  getOrderItem(orderId: number) {
    let sizeItem = this.orders.length;
    console.log(orderId);
    for (let i = 0; i < sizeItem; i++) {
      this.cartService.getOrderItemList(orderId).subscribe(
        (data: any) => {
          this.orderItems = data['data'];

          for (let orderItem of this.orderItems) {
            var number = orderItem.book.price * orderItem.quantity;
            orderItem.sPrice = number.toLocaleString(undefined, { minimumFractionDigits: 0, maximumFractionDigits: 2 })

            number = orderItem.book.price;
            orderItem.book.stringPrice = number.toLocaleString(undefined, { minimumFractionDigits: 0, maximumFractionDigits: 2 })
          }

        }
      )
    }
  }

  getNumberOfOrders() {
    if (this.orders == null || this.orders.length == 0)
      return 0;
    return 1;
  }

  isSameOrderId(orderId: number, orderItemOrderId: number) {
    return orderId == orderItemOrderId;
  }

  addReview(orderItemId: number, bookId: number) {
    this.bookService.getCustomerBookById(bookId).subscribe(
      (data: any) => { this.book = data['data']; });

    this.reviewService.getReviewByOrderItemId(orderItemId).subscribe(
      (data: any) => {
        if (data['data'] != null) {
          this.review = data['data'];
        } else {
          this.review = new Review();
          this.review.comment = '';
          this.review.rate = 1;
          this.review.bookId = bookId;
          this.review.orderItemId = orderItemId;
          var email = this.userAuthService.getEmail();
          if (email != null) {
            this.review.userEmail = email;
          } else {
            this.router.navigate(['/login'])
              .then(() => {
                window.location.reload();
              });
          }
        }
      });
  }

  updateRating(rate: number) {
    this.review.rate = rate;
  }

  sendReview() {
    this.reviewService.createReview(this.review).subscribe(data => {
      this.toastr.success('Đánh giá thành công!');
    });
  }


}
