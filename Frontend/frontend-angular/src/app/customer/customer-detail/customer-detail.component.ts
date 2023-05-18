import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { ToastrService } from 'ngx-toastr';
import { User } from 'src/app/_class/user';
import { UserAuthService } from 'src/app/_service/user-auth.service';
import { UserService } from 'src/app/_service/user.service';

@Component({
  selector: 'app-customer-detail',
  templateUrl: './customer-detail.component.html',
  styleUrls: ['./customer-detail.component.css']
})
export class CustomerDetailComponent implements OnInit {

  email !: string;
  user: User = new User();
  oldPassword = ""
  newPassword = ""
  reEnterNewPassword = ""


  constructor(
    private route: ActivatedRoute,
    private userAuthService: UserAuthService,
    private router: Router,
    private userService: UserService,
    private toastr: ToastrService) { }

  ngOnInit(): void {
    this.email = this.route.snapshot.params['email'];
    console.log(this.email);
    this.userService.getUserByEmail(this.email).subscribe(
      (data: any) => {
        this.user = data['data'];
      }
    );
  }

  setOldPassword(event: any) {
    this.oldPassword = event.target.value;
  }

  setNewPassword(event: any) {
    this.newPassword = event.target.value;
  }

  setReEnterNewPassword(event: any) {
    this.reEnterNewPassword = event.target.value;
  }

  onSubmit() {
    this.userService.isMatchPassword(this.oldPassword.trim(), this.user.password).subscribe(
      (data: any) => {
        let isMatch = data['data'];

        if (isMatch) {
          if (this.newPassword != "" && this.reEnterNewPassword != "") {
            if (this.newPassword == this.reEnterNewPassword) {
              this.user.password = this.newPassword;
              
              
              this.userService.updateUser(this.email, this.user).subscribe((data: any) => {
                this.toastr.success("Lưu thay đổi thành công")
                window.location.reload();
              });
             
            }
            else {
              this.toastr.error("Nhập lại mật khẩu sai")
            }
          }
        }
        else {
          this.toastr.error("Sai mật khẩu")
        }

      }
    )
  }

  public logout(){
    this.userAuthService.clear();
    this.router.navigate(['/home']);
    console.log(this.userAuthService.getAccessToken());
  }
}
