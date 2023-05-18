import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { UserAuthService } from '../_service/user-auth.service';

@Component({
  selector: 'app-staff',
  templateUrl: './staff.component.html',
  styleUrls: ['./staff.component.css']
})

export class StaffComponent implements OnInit {

  constructor(
    private router: Router,
    private userAuthService: UserAuthService) { }

  ngOnInit(): void {
  }

  public logout(){
    this.userAuthService.clear();
    this.router.navigate(['/login']);
    console.log(this.userAuthService.getAccessToken());
  }

}
