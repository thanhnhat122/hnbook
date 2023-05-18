import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { User } from 'src/app/_class/user';
import { UserService } from 'src/app/_service/user.service';

@Component({
  selector: 'app-update-user',
  templateUrl: './update-user.component.html',
  styleUrls: ['./update-user.component.css']
})
export class UpdateUserComponent implements OnInit {

  email!: string;
  user: User = new User();
  constructor(
    private userService: UserService,
    private router: Router,
    private route: ActivatedRoute,
  ) { }

  ngOnInit(): void {
    this.email = this.route.snapshot.params['email'];

    this.userService.getUserByEmail(this.email).subscribe(
      (data: any) => this.user = data['data']
    );
  }

  updateUser(){
    this.userService.updateUser(this.email, this.user).subscribe(
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
      this.updateUser();

  }
}
