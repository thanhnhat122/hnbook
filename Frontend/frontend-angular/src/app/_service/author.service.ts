import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Author } from '../_class/author';

@Injectable({
  providedIn: 'root'
})
export class AuthorService {

  private baseURL = "http://localhost:8080/api/Author"

  constructor(private httpClient: HttpClient) { }

  getAuthorsList(): Observable<any> {
    return this.httpClient.get(`${this.baseURL}`);
  } 

  createAuthor(author: Author): Observable<Object> {
    return this.httpClient.post(`${this.baseURL}`, author);
  }

  getAuthorById(id: number): Observable<any> {
    return this.httpClient.get(`${this.baseURL}/${id}`);
  } 

  updateAuthor(id: number, author: Author): Observable<Object> {
    const params = {'id': id};
    return this.httpClient.put(`${this.baseURL}`, author, {params});
  } 

  deleteAuthor(id: number): Observable<Object> {
    const params = {'id': id};
    return this.httpClient.delete(`${this.baseURL}`, {params});
  }
}
