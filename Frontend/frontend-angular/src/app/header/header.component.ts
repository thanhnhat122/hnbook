import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { HomeComponent } from '../home/home.component';
import { Order } from '../_class/order';
import { OrderItem } from '../_class/order-item';
import { User } from '../_class/user';
import { CartService } from '../_service/cart.service';
import { UserAuthService } from '../_service/user-auth.service';
import { UserService } from '../_service/user.service';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css']
})
export class HeaderComponent implements OnInit {
  email !: any;
  order: Order = new Order();
  user !: User;
  userName !: any;
  sizeItem: any;
  orderItems !: OrderItem[];
  number : number = 0;

  title !: string;
  static staticTitle = "";

  constructor(
    private userAuthService: UserAuthService,
    private router: Router,
    public userService: UserService,
    private cartService: CartService,
    private route: ActivatedRoute,
    ) { }

  ngOnInit(): void {
    this.getUser();
    this.getOrder();

  }
  getOrder(){
    this.cartService.getOrderByEmail(this.email).subscribe(
      (data: any) => {
        this.order = data['data'];
        this.getNumberCartItem();
      
        
    }
    );
  }
  public getNumberCartItem(){
    this.cartService.getOrderItemList(this.order.id).subscribe(
      (data:any) => {let i:any;
        this.orderItems = data['data'];
        this.sizeItem= this.orderItems.length;
        for ( i = 0; i < this.sizeItem; i++){
          this.number=this.number + this.orderItems[i].quantity;
        }
      }
    );
  }

  public getUser() {
    this.email = this.userAuthService.getEmail();
    console.log(this.email);
    this.userService.getUserByEmail(this.email).subscribe(
      (data: any) => {
        //window.location.reload();
        this.user = data['data'];
        this.userName = this.user.lastName + " " +  this.user.firstName;
        console.log(this.userName);
      }
    );


  }

  public isLoggedIn(){
    return this.userAuthService.isLoggedIn();
  }
  public logout(){
    this.userAuthService.clear();
    this.router.navigate(['/home']);
    console.log(this.userAuthService.getAccessToken());
  }
  public isCustomer(){
    return this.userService.roleMatch('ROLE_KH');
  }

  goToUserDetail() {
    this.email = this.userAuthService.getEmail();
    this.router.navigate(['/customer-details', this.email]);
  }

  searchForm() {
    if(this.title != null || this.title == "") {
      HeaderComponent.staticTitle = this.title;
      this.router.navigateByUrl('/', {skipLocationChange: true}).then(()=>
      this.router.navigate(['search', this.title]));
    }
  }

  onKey(event : any) {this.title = event.target.value;}
}
