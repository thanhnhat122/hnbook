import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Review } from '../_class/review';

@Injectable({
  providedIn: 'root'
})
export class ReviewService {

  private baseURL = "http://localhost:8080/api/Review"

  constructor(private httpClient: HttpClient) { }

  getReviewsList(): Observable<any> {
    return this.httpClient.get(`${this.baseURL}`);
  } 

  getReviewDTOsList(): Observable<any> {
    return this.httpClient.get(`${this.baseURL}/findAll`);
  } 

  createReview(review: Review): Observable<Object> {
    return this.httpClient.post(`${this.baseURL}`, review);
  }

  getReviewById(id: number): Observable<any> {
    return this.httpClient.get(`${this.baseURL}/${id}`);
  } 

  getReviewDTOById(id: number): Observable<any> {
    return this.httpClient.get(`${this.baseURL}/details/${id}`);
  } 

  getReviewByBookId(id: number): Observable<any> {
    return this.httpClient.get(`${this.baseURL}/findByBookId/${id}`);
  } 

  getReviewByOrderItemId(id: number): Observable<any> {
    return this.httpClient.get(`${this.baseURL}/findByOrderItemId/${id}`);
  } 

  updateReview(id: number, review: Review): Observable<Object> {
    const params = {'id': id};
    return this.httpClient.put(`${this.baseURL}`, review, {params});
  } 

  updateReviewResponse(review: Review): Observable<Object> {
    return this.httpClient.put(`${this.baseURL}/updateResponse`, review);
  } 

  deleteReview(id: number): Observable<Object> {
    const params = {'id': id};
    return this.httpClient.delete(`${this.baseURL}`, {params});
  }
}
