import { Component, OnInit } from '@angular/core';
import { NgForm } from '@angular/forms';
import { Router } from '@angular/router';
import { data } from 'jquery';
import { ToastrService } from 'ngx-toastr';
import { UserService } from '../_service/user.service';

@Component({
  selector: 'app-signup',
  templateUrl: './signup.component.html',
  styleUrls: ['./signup.component.css']
})
export class SignupComponent implements OnInit {

  constructor(
    private userService: UserService,
    private router: Router,
    private toastr: ToastrService
  ) { }

  ngOnInit(): void {
  }

  signup(signupForm: NgForm){
    console.log(signupForm.value);
    if (this.validSignup(signupForm)) {
      this.userService.signup(signupForm).subscribe(
        (response:any)=>{
          console.log(response);
          if(response['data'] != null) {
            this.toastr.success('Đăng ký thành công')
            this.router.navigate(['/login']);
          }
          else {
            if (response['errMsg'] == 'Email exist!') {
              this.toastr.error('Email đã được đăng ký!');
            } 
            else if (response['errMsg'] == 'Phone exist!') {
              this.toastr.error('Số điện thoại đã được đăng ký!');
            } 
            else if (response['errMsg'].includes('Incorrect email format')) {
              this.toastr.warning('Email không đúng định dạng!');
            } 
            else if (response['errMsg'].includes('Incorrect phone format')) {
              this.toastr.warning('Số điện thoại không đúng định dạng!');
            }
          }
        },
        (error) => {
          console.log(error);
        }
      );
    }
  }

  validSignup(signupForm: NgForm) {
    if (signupForm.value['email'] == '') {
      this.toastr.warning('Email không được để trống!');
      return false;
    } else if (signupForm.value['password'] == '') {
      this.toastr.warning('Mật khẩu không được để trống!');
      return false;
    } else if (signupForm.value['lastName'] == '') {
      this.toastr.warning('Họ không được để trống!');
      return false;
    }else if (signupForm.value['firstName'] == '') {
      this.toastr.warning('Tên không được để trống!');
      return false;
    }else if (signupForm.value['phone'] == '') {
      this.toastr.warning('Số điện thoại không được để trống!');
      return false;
    }

    return true;
  }
}
