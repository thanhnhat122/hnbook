import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { data } from 'jquery';
import { ToastrService } from 'ngx-toastr';
import { HeaderComponent } from 'src/app/header/header.component';
import { Book } from 'src/app/_class/book';
import { Order } from 'src/app/_class/order';
import { OrderItem } from 'src/app/_class/order-item';
import { BookService } from 'src/app/_service/book.service';
import { CartService } from 'src/app/_service/cart.service';
import { UserAuthService } from 'src/app/_service/user-auth.service';

@Component({
  selector: 'app-cart-detail',
  templateUrl: './cart-detail.component.html',
  styleUrls: ['./cart-detail.component.css']
})
export class CartDetailComponent implements OnInit {

  order: Order = new Order();
  orderItems !: OrderItem[];
  book: Book = new Book();
  email : any;
  sizeItem: any;
  sumMoney = 0;
  sSumMoney !: string;
  constructor(
    private cartService: CartService,
    private userAuthService: UserAuthService,
    private bookService: BookService,
    private router: Router,
    private toastr: ToastrService,
    ) { }

  ngOnInit(): void {
    this.email = this.userAuthService.getEmail();
    this.getOrder();
  }

  getOrder(){
    this.cartService.getOrderDTOByEmail(this.email).subscribe(
      (data: any) => {
        this.order = data['data'];
        this.getOrderItemList();
      }
    );
  }

  getOrderItemList(){
    this.cartService.getOrderItemList(this.order.id).subscribe(
      (data:any) => {let i:any;
        this.orderItems = data['data'];
        console.log(this.orderItems);
        this.sizeItem= this.orderItems.length;
        for ( i = 0; i < this.sizeItem; i++){
          // this.sumMoney = this.sumMoney + this.orderItems[i].book.price*this.orderItems[i].quantity;

          // var number1 = this.sumMoney;
          // this.sSumMoney = number1.toLocaleString(undefined, { minimumFractionDigits: 0, maximumFractionDigits: 2 })

          var number = this.orderItems[i].book.price;
          this.orderItems[i].book.stringPrice = number.toLocaleString(undefined, { minimumFractionDigits: 0, maximumFractionDigits: 2 })

          var number1 = this.orderItems[i].totalPrice;
          this.orderItems[i].sTotalPrice = number1.toLocaleString(undefined, { minimumFractionDigits: 0, maximumFractionDigits: 2 })

          var number2 = this.order.totalPrice;
          this.order.sTotalPrice = number2.toLocaleString(undefined, { minimumFractionDigits: 0, maximumFractionDigits: 2 })
        }
      }
    );
  }

  checkValidQuantity(orderItemId: number, bookId: number, quantity: number) {
    console.log(orderItemId);
    console.log(bookId);
    console.log(quantity);

    this.cartService.checkValidQuantity(orderItemId, bookId, quantity).subscribe(
        (data:any) => {
          if (data['data'] == null) {
            if (data['errMsg'] == 'Quantity < 0') {
              // this.toastr.warning('Xóa à?');
            } else {
              var errMsg = data['errMsg'].split(': ');
              if (errMsg[0] == 'Remain') {
                this.toastr.warning('Chỉ còn lại ' + errMsg[1] + ' sản phẩm!');
              }
            }
          }
          // window.location.reload();
          this.getOrder();
        }
      )
  }

  reduce(orderItemId: number, bookId: number, quantity: number){
    this.cartService.checkValidQuantity(orderItemId, bookId, quantity - 1).subscribe(
      (data:any) => {
        if (data['data'] == null) {
          if (data['errMsg'] == 'Quantity < 0') {
            // this.toastr.warning('Xóa à?');
          } else {
            var errMsg = data['errMsg'].split(': ');
            if (errMsg[0] == 'Remain') {
              this.toastr.warning('Chỉ còn lại ' + errMsg[1] + ' sản phẩm!');
            }
          }
        }
        // window.location.reload();
        this.getOrder();
      }
    )
  }

  increase(orderItemId: number, bookId: number, quantity: number){
    this.cartService.checkValidQuantity(orderItemId, bookId, quantity + 1).subscribe(
      (data:any) => {
        if (data['data'] == null) {
          if (data['errMsg'] == 'Quantity < 0') {
            // this.toastr.warning('Xóa à?');
          } else {
            var errMsg = data['errMsg'].split(': ');
            if (errMsg[0] == 'Remain') {
              this.toastr.warning('Chỉ còn lại ' + errMsg[1] + ' sản phẩm!');
            }
          }
        }
        // window.location.reload();
        this.getOrder();
      }
    )
  }

  delete(orderItem: OrderItem){
    this.cartService.deleteOrderItem(orderItem.id).subscribe(
      (data:any) => {
        // window.location.reload();
        this.getOrder();
      }
    )
  }

  orderOrder(order: Order){
    this.cartService.orderOrder(order.id).subscribe(
      (data:any) => {
        this.toastr.success('Đặt hàng thành công');
        this.router.navigate(['/home']);
      }
    )
  }
}
