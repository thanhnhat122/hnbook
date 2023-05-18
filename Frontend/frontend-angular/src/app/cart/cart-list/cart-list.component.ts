import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { data } from 'jquery';
import { Order } from 'src/app/_class/order';
import { OrderItem } from 'src/app/_class/order-item';
import { User } from 'src/app/_class/user';
import { CartService } from 'src/app/_service/cart.service';
import { UserService } from 'src/app/_service/user.service';

@Component({
  selector: 'app-cart-list',
  templateUrl: './cart-list.component.html',
  styleUrls: ['./cart-list.component.css']
})
export class CartListComponent implements OnInit {

  orders !: Order[];
  user = new User();
  order: Order = new Order();
  orderItems !: OrderItem[];
  sizeItem: any;
  sumMoney = 0;
  sSumMoney !: string;
  constructor(
    private cartService: CartService,
    private router: Router
  ) { }

  ngOnInit(): void {
    this.cartService.getOderDTOList().subscribe(
      (data:any) => {
        let i:any;
        this.orders = data['data'];
        for(i = 0 ;i< this.orders.length;i++)
        {
          if(this.orders[i].status == 'Cho_Xac_Nhan')
            this.orders[i].status = 'Chờ xác nhận';
          if(this.orders[i].status == 'Dang_Giao')
            this.orders[i].status = 'Đang giao';
          if(this.orders[i].status == 'Da_Nhan')
            this.orders[i].status = 'Đã nhận';
          if(this.orders[i].status == 'Da_Huy')
            this.orders[i].status = 'Đã hủy';
        }
        console.log(this.orders);
      }
    )

  }
  goToCartDetail(id: number){
    this.router.navigate(['staff-cart-detail', id]);
  }
  checkStatus(order: Order){
   const btXacnhan = document.getElementById(`btXacnhan${order.id.toString()}`) as HTMLButtonElement | null;
   const btDanhan = document.getElementById(`btDanhan${order.id.toString()}`) as HTMLButtonElement | null;
   const btDahuy = document.getElementById(`btDahuy${order.id.toString()}`) as HTMLButtonElement | null;
   if(order.status == "Chờ xác nhận")
   {
    if(btDanhan != null)
      btDanhan.disabled = true;
    if(btDahuy != null)
      btDahuy.disabled = true;
   }
   else if(order.status == "Đang giao")
   {
    if(btXacnhan != null)
      btXacnhan.disabled = true;
   }
   else
   {
    if(btXacnhan != null)
      btXacnhan.disabled = true;
    if(btDanhan != null)
      btDanhan.disabled = true;
    if(btDahuy != null)
      btDahuy.disabled = true;
   }

    return true;
  }
  Xacnhan(order: Order){
    this.cartService.confirmOrder(order.id).subscribe(
      (data:any) => {
        window.location.reload();
      }
    )
  }
  Danhan(order: Order){
    this.cartService.receivedOrder(order.id).subscribe(
      (data:any) => {
        window.location.reload();
      }
    )
  }
  Dahuy(order: Order){
    this.cartService.cancelledOrder(order.id).subscribe(
      (data:any) => {
        window.location.reload();
      }
    )
  }
  deleteOrder(id: number){
    this.cartService.deleteOrder(id).subscribe(
      (data:any) => {
        window.location.reload();
      }
    )

  }
  getOrderDetai(id: number){

    this.cartService.getOrderItemList(id).subscribe(
      (data:any) => {let i:any;
        this.orderItems = data['data'];
        this.sumMoney = 0;
        this.sizeItem= this.orderItems.length;
        for ( i = 0; i < this.sizeItem; i++){
          this.sumMoney = this.sumMoney + this.orderItems[i].book.price*this.orderItems[i].quantity;

          var number1 = this.sumMoney;
          this.sSumMoney = number1.toLocaleString(undefined, { minimumFractionDigits: 0, maximumFractionDigits: 2 })

          var number = this.orderItems[i].book.price;
          this.orderItems[i].book.stringPrice = number.toLocaleString(undefined, { minimumFractionDigits: 0, maximumFractionDigits: 2 })
        }
      }
    );
  }

}


