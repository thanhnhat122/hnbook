import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { ToastrService } from 'ngx-toastr';
import { User } from 'src/app/_class/user';
import { UserService } from 'src/app/_service/user.service';

@Component({
  selector: 'app-customer-list',
  templateUrl: './customer-list.component.html',
  styleUrls: ['./customer-list.component.css']
})
export class CustomerListComponent implements OnInit {

  users!: User[];
  constructor(
    private userService: UserService,
    private toastr: ToastrService,
    private router: Router
  ) { }

  ngOnInit(): void {
    this.getCustomers();
  }
  private getCustomers(){
    this.userService.getCustomerList().subscribe(
      (data: any) => this.users= data['data']
    );
  }
  userDetail(email: string){

    this.router.navigate(['user-details',email]);
  }
  updateCustomer(email: string, user: User){
    this.userService.updateUser(email, user).subscribe(
      data => {
        this.toastr.success('Cập nhật thành công!');
      },
      error => console.log(error)
    );
  }

  insertUser(){
    this.userService.setRole('ROLE_KH');
    this.router.navigate(['/create-user']);
  }

  updateUser(email: string) {
    this.userService.setRole('ROLE_KH');
    this.router.navigate(['/update-user', email]);
  }

  deleteUser(email: string) {
    this.userService.deleteUser(email).subscribe(data => {
      console.log(data);
      this.getCustomers();
    });
  }
}
