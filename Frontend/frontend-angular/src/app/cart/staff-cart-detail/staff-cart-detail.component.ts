import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Order } from 'src/app/_class/order';
import { OrderItem } from 'src/app/_class/order-item';
import { CartService } from 'src/app/_service/cart.service';

@Component({
  selector: 'app-staff-cart-detail',
  templateUrl: './staff-cart-detail.component.html',
  styleUrls: ['./staff-cart-detail.component.css']
})
export class StaffCartDetailComponent implements OnInit {

  orderItems !: OrderItem[];
  id!: number;
  constructor(
    private route: ActivatedRoute,
    private cartService: CartService
  ) { }

  ngOnInit(): void {
    this.id = this.route.snapshot.params['id'];
    this.getOrderItemList();
  }
  getOrderItemList(){
    this.cartService.getOrderItemList(this.id).subscribe(
      (data:any) => {let i:any;
        this.orderItems = data['data'];
      }
    );
  }

}
