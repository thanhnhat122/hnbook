import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { User } from 'src/app/_class/user';
import { UserService } from 'src/app/_service/user.service';

@Component({
  selector: 'app-create-user',
  templateUrl: './create-user.component.html',
  styleUrls: ['./create-user.component.css']
})
export class CreateUserComponent implements OnInit {

  user: User = new User();
  constructor(
    private userService: UserService,
    private router: Router
  ) { }

  ngOnInit(): void {
  }

  saveUser(){
    this.userService.createUser(this.user).subscribe(
      data => {
        this.goToAdmin();
      },
      error => console.log(error)
    );

  }
  goToAdmin(){
    this.router.navigate(['/admin']);
  }
  onSubmit(){
      this.user.role= this.userService.getRole();
      this.saveUser();

  }

}
