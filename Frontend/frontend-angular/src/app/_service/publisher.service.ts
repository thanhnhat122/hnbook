import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Publisher } from '../_class/publisher';

@Injectable({
  providedIn: 'root'
})
export class PublisherService {

  private baseURL = "http://localhost:8080/api/Publisher"

  constructor(private httpClient: HttpClient) { }

  getPublishersList(): Observable<any> {
    return this.httpClient.get(`${this.baseURL}`);
  } 

  createPublisher(publisher: Publisher): Observable<Object> {
    return this.httpClient.post(`${this.baseURL}`, publisher);
  }

  getPublisherById(id: number): Observable<any> {
    return this.httpClient.get(`${this.baseURL}/${id}`);
  } 

  updatePublisher(id: number, publisher: Publisher): Observable<Object> {
    const params = {'id': id};
    return this.httpClient.put(`${this.baseURL}`, publisher, {params});
  } 

  deletePublisher(id: number): Observable<Object> {
    const params = {'id': id};
    return this.httpClient.delete(`${this.baseURL}`, {params});
  }
}
