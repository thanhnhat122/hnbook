import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class CartService {
  private baseOrderURL = "http://localhost:8080/api/Order"
  private baseOrderItem = "http://localhost:8080/api/OrderItem"

  constructor(private httpClient: HttpClient) { }

  getOrderByEmail(email : string): Observable<any>{
    const formData = new FormData();
    formData.append('email',email);
    return this.httpClient.post<any>(this.baseOrderURL + "/email",formData);
  }

  getOrderDTOByEmail(email : string): Observable<any>{
    const formData = new FormData();
    formData.append('email',email);
    return this.httpClient.post<any>(this.baseOrderURL + "/cart/email",formData);
  }

  getOrderItemList(orderId: number) : Observable<any>{
    const formData = new FormData();
    formData.append('orderId',String(orderId));
    return this.httpClient.post<any>(this.baseOrderItem + "/orderId",formData);
  }

  // reduceOrderItem(id: number) : Observable<any>{
  //   const formData = new FormData();
  //   formData.append('id',String(id));
  //   return this.httpClient.post<any>(this.baseOrderItem + "/reduce",formData);
  // }

  // increaseOrderItem(id: number) : Observable<any>{
  //   const formData = new FormData();
  //   formData.append('id',String(id));
  //   return this.httpClient.post<any>(this.baseOrderItem + "/increase",formData);
  // }

  checkValidQuantity(orderItemId: number, bookId: number, quantity: number) : Observable<any>{
    const formData = new FormData();
    formData.append('orderItemId',String(orderItemId));
    formData.append('bookId',String(bookId));
    formData.append('quantity',String(quantity));
    return this.httpClient.post<any>(this.baseOrderItem + "/validQuantity", formData);
  }

  deleteOrderItem(id: number): Observable<Object> {
    return this.httpClient.delete(`${this.baseOrderItem}/${id}`);
  }

  deleteOrder(id: number): Observable<Object>{
    return this.httpClient.delete(`${this.baseOrderURL}/${id}`);
  }

  orderOrder(id: number): Observable<Object> {
    return this.httpClient.get(`${this.baseOrderURL}/order/${id}`);
  }
  confirmOrder(id: number): Observable<Object> {
    return this.httpClient.get(`${this.baseOrderURL}/confirm/${id}`);
  }

  receivedOrder(id: number): Observable<Object> {
    return this.httpClient.get(`${this.baseOrderURL}/received/${id}`);
  }

  cancelledOrder(id: number): Observable<Object> {
    return this.httpClient.get(`${this.baseOrderURL}/cancelled/${id}`);
  }

  getCartsListForStaff(): Observable<any> {
    return this.httpClient.get(`${this.baseOrderURL}/forStaff`);
  }

  findOrderByEmail(email : string): Observable<any>{
    const formData = new FormData();
    formData.append('email',email);
    return this.httpClient.get(`${this.baseOrderURL}/findEmail/${email}`);
  }

  getTotalPrice(orderId: number):Observable<any> {
      return this.httpClient.get(`${this.baseOrderURL}/totalPrice/${orderId}`);
  }
  getOderDTOList(): Observable<any>{
    return this.httpClient.get(`${this.baseOrderURL}/orderDTOList`);
  }

  findOrderDTOByEmail(email : string, statusNumber: number): Observable<any>{
    const formData = new FormData();
    formData.append('email',email);
    formData.append('statusNumber', statusNumber.toString());
    return this.httpClient.post<any>(this.baseOrderURL + "/orderDTO",formData);
  }
}
