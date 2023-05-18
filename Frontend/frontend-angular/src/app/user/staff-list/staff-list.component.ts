import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { ToastrService } from 'ngx-toastr';
import { User } from 'src/app/_class/user';
import { UserService } from 'src/app/_service/user.service';

@Component({
  selector: 'app-staff-list',
  templateUrl: './staff-list.component.html',
  styleUrls: ['./staff-list.component.css']
})
export class StaffListComponent implements OnInit {

  users!: User[];
  user1: User = new User();
  constructor(
    private userService: UserService,
    private toastr: ToastrService,
    private router: Router
  ) { }

  ngOnInit(): void {
    this.getStaffs();
  }
  private getStaffs(){
    this.userService.getStaffList().subscribe(
      (data: any) => this.users= data['data']
    );
  }
  updateStaff(email: string, user: User){
    this.userService.updateUser(email, user).subscribe(
      data => {
        this.toastr.success('Cập nhật thành công!');
      },
      error => console.log(error)
    );
  }
  saveStaff(){
    this.user1.role= 'ROLE_NV';
    this.userService.createUser(this.user1).subscribe(
      data => {
        this.toastr.success('Thêm thành công!');
      },
      error => console.log(error)
    );
  }
  userDetail(email: string){

    this.router.navigate(['user-details',email]);
  }

  insertUser(){
    this.userService.setRole('ROLE_NV');
    this.router.navigate(['/create-user']);
  }

  updateUser(email: string) {
    this.userService.setRole('ROLE_NV');
    this.router.navigate(['/update-user', email]);
  }

  deleteUser(email: string) {
    this.userService.deleteUser(email).subscribe(data => {
      console.log(data);
      this.getStaffs();
    });
  }
}
