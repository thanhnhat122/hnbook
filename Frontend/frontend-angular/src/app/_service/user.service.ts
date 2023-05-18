import { HttpClient, HttpHeaders, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { NgForm } from '@angular/forms';
import { Observable } from 'rxjs';
import { User } from '../_class/user';
import { UserAuthService } from './user-auth.service';

@Injectable({
  providedIn: 'root'
})
export class UserService {
  PATH_OF_API = "http://localhost:8080/api";
  private baseURL = "http://localhost:8080/api/User"

  requestHeader = new HttpHeaders(
    { "No_Auth": "True" }
  );


  constructor(
    private httpclient: HttpClient,
    private userAuthService: UserAuthService
  ) { }


  public updatePassword(newPassword: string, user: User) {
    const params = { 'newPassword': newPassword };
    return this.httpclient.post(`${this.baseURL}` + "/updatePassword", user, { params });
  }

  public isMatchPassword(password: string, userPassword: string) {
    const params = new HttpParams().set('password', password).set('userPassword', userPassword);
    return this.httpclient.post(this.baseURL + "/matchPassword", params);
  }

  public login(loginData: NgForm) {
    const headers = new HttpHeaders({
      'Content-Type': 'application/x-www-form-urlencoded',
      'No_Auth': 'True'
    });
    const params = new HttpParams()
      .set('email', loginData.value.email)
      .set('password', loginData.value.password);
    return this.httpclient.post(this.PATH_OF_API + "/login", params.toString(), { headers });
  }
  public signup(signupData: NgForm) {
    return this.httpclient.post(this.PATH_OF_API + "/User/signup", signupData.value);
  }
  public roleMatch(allowedRole: any): boolean {
    let isMatch = false;
    const userRole: any = this.userAuthService.getRole()
    if (userRole === allowedRole) {
      isMatch = true;
    }
    return isMatch;
  }
  public setRole(role: string) {
    localStorage.setItem('roleInsert', role);
  }
  public getRole() {
    return localStorage.getItem('roleInsert');
  }


  getAdminList(): Observable<any> {
    return this.httpclient.get(`${this.baseURL}/admin`);
  }
  getStaffList(): Observable<any> {
    return this.httpclient.get(`${this.baseURL}/staff`);
  }
  getCustomerList(): Observable<any> {
    return this.httpclient.get(`${this.baseURL}/customer`);
  }
  getUserByEmail(email: string) {
    const formData = new FormData();
    formData.append('email', email);
    return this.httpclient.post<any>(this.baseURL + "/getUser", formData);
  }

  createUser(user: User): Observable<Object> {
    return this.httpclient.post(`${this.baseURL}`, user);
  }

  updateUser(email: string, user: User): Observable<Object> {
    const params = { 'email': email };
    return this.httpclient.put(`${this.baseURL}`, user, { params });
  }

  deleteUser(email: string): Observable<Object> {
    const params = { 'email': email };
    return this.httpclient.delete(`${this.baseURL}`, { params });
  }
}
