import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class UserAuthService {

  constructor() { }

  public setRole(role: string){
    localStorage.setItem('role', role);
  }
  public getRole(){
    return localStorage.getItem('role');
  }

  public setEmail(email: string) {
    localStorage.setItem('email', email);
  }

  public getEmail() {
    return localStorage.getItem('email')
  }

  public setAccessToken(access_token: string){
    localStorage.setItem('access_token', access_token);
  }
  public getAccessToken(){
    return localStorage.getItem('access_token');
  }
  public setRefreshToken(refresh_token: string){
    localStorage.setItem('refresh_token', refresh_token);
  }
  public getRefreshToken(){
    return localStorage.getItem('refresh_token');
  }

  public isLoggedIn(){
    return this.getAccessToken() && this.getRefreshToken() && this.getRole();
  }
  public clear(){
    localStorage.clear();
  }

}
