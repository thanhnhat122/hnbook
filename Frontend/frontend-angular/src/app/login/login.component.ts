import { Component, OnInit } from '@angular/core';
import { NgForm } from '@angular/forms';
import { Router } from '@angular/router';
import { ToastrService } from 'ngx-toastr';
import { UserAuthService } from '../_service/user-auth.service';
import { UserService } from '../_service/user.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  constructor(
    private userService: UserService,
    private userAuthService: UserAuthService,
    private router: Router,
    private toastr: ToastrService
    ) { }

  ngOnInit(): void {
  }

  login(loginForm: NgForm){
    if (this.validLogin(loginForm)) {
      this.userService.login(loginForm).subscribe(
        (response:any)=>{
          this.userAuthService.setEmail(response.data.email);
          this.userAuthService.setAccessToken(response.data.access_token);
          this.userAuthService.setRefreshToken(response.data.refresh_token);
          this.userAuthService.setRole(response.data.roles);
  
          const role = response.data.roles;
          if(role == 'ROLE_AD')
          {
            this.router.navigate(['/admin']);
          }
          else {
            if(role == 'ROLE_NV')
            {
              this.router.navigate(['/staff']);
            }
            else
            {
              this.router.navigate(['/home']) .then(() => {
                window.location.reload(); 
              });
            }
          }
  
        },
        (error) => {
          this.toastr.error('Tài khoản hoặc mật khẩu không chính xác');
          // console.log(error);
        }
      );
    }
  }

  validLogin(loginForm: NgForm) {
    if (loginForm.value['email'] == '') {
      this.toastr.warning('Email không được để trống!');
      return false;
    } else if (loginForm.value['password'] == '') {
      this.toastr.warning('Mật khẩu không được để trống!');
      return false;
    } else if (this.validEmail(loginForm.value['email']) == null) {
      this.toastr.warning('Email không đúng định dạng!');
      return false;
    }

    return true;
  }

  validEmail(email: string) {
    const validateEmail = String(email)
    .match(/^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,6}$/);

    return validateEmail;
  }
}
