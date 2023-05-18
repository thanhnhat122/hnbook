import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { ToastrService } from 'ngx-toastr';
import { User } from 'src/app/_class/user';
import { UserService } from 'src/app/_service/user.service';

@Component({
  selector: 'app-user-list',
  templateUrl: './user-list.component.html',
  styleUrls: ['./user-list.component.css']
})
export class UserListComponent implements OnInit {

  users!: User[];
  user1: User = new User();
  constructor(
    private userService: UserService,
    private toastr: ToastrService,
    private router: Router
  ) { }

  ngOnInit(): void {
    this.getAdmins();
  }
  private getAdmins(){
    this.userService.getAdminList().subscribe(
      (data: any) => this.users= data['data']
    );
  }
  userDetail(email: string){

    this.router.navigate(['user-details',email]);
  }
  insertUser(){
    this.userService.setRole('ROLE_AD');
    this.router.navigate(['/create-user']);
  }

  updateUser(email: string) {
    this.userService.setRole('ROLE_AD');
    this.router.navigate(['/update-user', email]);
  }

  deleteUser(email: string) {
    this.userService.deleteUser(email).subscribe(data => {
      console.log(data);
      this.getAdmins();
    });
  }
  saveAdmin(){
    this.user1.role= 'ROLE_AD';
    this.userService.createUser(this.user1).subscribe(
      data => {
        this.toastr.success('Thêm thành công!');
      },
      error => console.log(error)
    );
  }
  updateAdmin(email: string, user: User){
    this.userService.updateUser(email, user).subscribe(
      data => {
        this.toastr.success('Cập nhật thành công!');
      },
      error => console.log(error)
    );
  }
}
