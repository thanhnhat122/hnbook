import { Component, OnInit, Inject, } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Toast, ToastrService } from 'ngx-toastr';
import { User } from 'src/app/_class/user';
import { UserService } from 'src/app/_service/user.service';

@Component({
  selector: 'app-user-details',
  templateUrl: './user-details.component.html',
  styleUrls: ['./user-details.component.css']
})
export class UserDetailsComponent implements OnInit {

  email !: string;
  user: User = new User();

  lastName = ""
  firstName = ""
  address = ""
  province = ""
  phone = ""


  constructor(
    private route: ActivatedRoute,
    private userService: UserService,
    private toastr: ToastrService
  ) { }

  ngOnInit(): void {
    this.email = this.route.snapshot.params['email'];
    console.log(this.email);
    this.userService.getUserByEmail(this.email).subscribe(
      (data: any) => {
        this.user = data['data'];

        this.lastName = this.user.lastName;
        this.firstName = this.user.firstName;
        this.address = this.user.address;
        this.province = this.user.province;
        this.phone = this.user.phone;
      }
    );
  }

  onSubmit() {
    this.user.lastName = this.lastName;
    this.user.firstName = this.firstName;
    this.user.address = this.address;
    this.user.province = this.province;
    this.user.phone = this.phone;

    this.userService.updateUser(this.email, this.user).subscribe(
      (data: any) => {
        if (data['data'] == null) {
          this.toastr.warning('Số điện thoại không hợp lệ')
        }
        else {
          this.toastr.success('Cập nhật thành công');
          //window.location.reload();
        }
      }
    );
  }

  setFirstName(event: any) {
    this.firstName = event.target.value;
  }

  setLastName(event: any) {
    this.lastName = event.target.value;
  }

  setAddress(event: any) {
    this.address = event.target.value;
  }

  setProvince(event: any) {
    this.province = event.target.value;
  }

  setPhone(event: any) {
    this.phone = event.target.value;
  }
}
