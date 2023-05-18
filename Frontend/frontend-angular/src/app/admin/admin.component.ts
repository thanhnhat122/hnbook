import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { UserAuthService } from '../_service/user-auth.service';

@Component({
  selector: 'app-admin',
  templateUrl: './admin.component.html',
  styleUrls: ['./admin.component.css']
})
export class AdminComponent implements OnInit {

  constructor(
    private userAuthService: UserAuthService,
    private router: Router
  ) { }

  ngOnInit(): void {
    //this.router.navigate(['/users']);
  }

  public logout(){
    this.userAuthService.clear();
    this.router.navigate(['/login']);
    console.log(this.userAuthService.getAccessToken());
  }

}
