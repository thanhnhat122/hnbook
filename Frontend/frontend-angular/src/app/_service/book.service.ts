import { Injectable } from '@angular/core';
import { HttpClient, HttpParams } from '@angular/common/http'
import { Observable } from 'rxjs';
import { Book } from '../_class/book';
import { Filter } from '../_class/filter';

@Injectable({
  providedIn: 'root'
})
export class BookService {

  private baseURL = "http://localhost:8080/api/Book"

  constructor(private httpClient: HttpClient) { }

  getBooksList(): Observable<any> {
    return this.httpClient.get(`${this.baseURL}`);
  }

  getAllSimpleBook(): Observable<any> {
    return this.httpClient.get(`${this.baseURL}/getAllSimpleBook`);
  }

  createBook(book: Book): Observable<Object> {
    return this.httpClient.post(`${this.baseURL}`, book);
  }

  uploadBookImage(isbn: string, number: string, file: File): Observable<any> {
    const formData = new FormData();
    formData.append('isbn', isbn);
    formData.append('number', number);
    formData.append('file', file);
    return this.httpClient.post(`${this.baseURL}/uploadImage`, formData);
  }

  getBookById(id: number): Observable<any> {
    return this.httpClient.get(`${this.baseURL}/${id}`);
  }

  getCustomerBookById(id: number): Observable<any> {
    return this.httpClient.get(`${this.baseURL}/customer/${id}`);
  }

  getBookByGenre(genre: string): Observable<any> {
    const params = {'genre': genre};
    return this.httpClient.get(`${this.baseURL}/filterAllBook`, {params});
  }

  getBookByPublisherID(publisherID: number, genre: string): Observable<any> {
    // const params = { 'publisherID': publisherID};
    // //const params2 = {'genre': genre};

    let params = new HttpParams().append('publisherID', publisherID);
    params = params.append('genre', genre);

    return this.httpClient.get(`${this.baseURL}/filterAllBook`, {params});
  }

  getSimpleBookInSameSeries(title: string): Observable<any> {
    const params = {'title': title};
    return this.httpClient.get(`${this.baseURL}/findSimpleBookInSameSeries`, {params});
  }

  getSimpleBookByGenre(genre: string): Observable<any> {
    const params = {'genre': genre};
    return this.httpClient.get(`${this.baseURL}/findSimpleBookByGenre`, {params});
  }

  updateBook(id: number, book: Book): Observable<Object> {
    const params = {'id': id};
    return this.httpClient.put(`${this.baseURL}`, book, {params});
  }

  deleteBook(id: number): Observable<Object> {
    const params = {'id': id};
    return this.httpClient.delete(`${this.baseURL}`, {params});
  }

  addItemToCart(email: string, bookId: number, quantity: number): Observable<Object> {
    const formData = new FormData();
    formData.append('email', email);
    formData.append('bookId', String(bookId));
    formData.append('quantity', String(quantity));
    return this.httpClient.post(`${this.baseURL}/addItemToCart`, formData);
  }

  filterAllBook(minPrice: number, maxPrice: number,
    publisherID: number, authorID: number, genre: string, type: string, publicationYear: number): Observable<Object> {
    const params = {'minPrice': minPrice, 'maxPrice': maxPrice, 'publisherID': publisherID,
    'authorID': authorID, 'genre': genre, 'type': type, 'publicationYear': publicationYear};
    return this.httpClient.get(`${this.baseURL}/filterAllBook`, {params});

  }

  filterBook(filter: Filter): Observable<Object> {
    return this.httpClient.post(`${this.baseURL}/filter`, filter);
  }
  
  bookSold(month: number, year: number): Observable<any> {
    const params = {'month': month, 'year': year};
    
    return this.httpClient.get(`${this.baseURL}/sold`, {params});
  }

  bookPriceSold(month: number, year: number): Observable<any> {
    const params = {'month': month, 'year': year};
    
    return this.httpClient.get(`${this.baseURL}/priceSold`, {params});
  }


  bestSeller(month: number, year: number): Observable<any> {
    const params = {'month': month, 'year': year};
    
    return this.httpClient.get(`${this.baseURL}/bestSeller`, {params});
  }

  bestGenre(month: number, year: number): Observable<any> {
    const params = {'month': month, 'year': year};
    
    return this.httpClient.get(`${this.baseURL}/bestGenre`, {params});
  }

  numOfBestSeller(month: number, year: number): Observable<any> {
    const params = {'month': month, 'year': year};
    
    return this.httpClient.get(`${this.baseURL}/numOfBestSeller`, {params});
  }

  numOfBestGenre(month: number, year: number): Observable<any> {
    const params = {'month': month, 'year': year};
    
    return this.httpClient.get(`${this.baseURL}/numOfBestGenre`, {params});
  }

  priceForEachGenre(month: number, year: number): Observable<any> {
    const params = {'month': month, 'year': year};
    
    return this.httpClient.get(`${this.baseURL}/priceForEachGenre`, {params});
  }

  bookSoldForYear(year: number): Observable<any> {
    const params = {'year': year};
    
    return this.httpClient.get(`${this.baseURL}/bookSoldForYear`, {params});
  }


  totalBookSoldInYear(year: number): Observable<any> {
    const params = {'year': year};
    
    return this.httpClient.get(`${this.baseURL}/totalBookSoldInYear`, {params});
  }

  totalPriceInYear(year: number): Observable<any> {
    const params = {'year': year};
    
    return this.httpClient.get(`${this.baseURL}/totalPriceInYear`, {params});
  }

  findBook(title: string): Observable<any> {
    const params = {'title': title};
    
    return this.httpClient.get(`${this.baseURL}/findBook`, {params});
  }

  filterBookSearch(filter: Filter): Observable<Object> {
    return this.httpClient.post(`${this.baseURL}/filterSearch`, filter);
  }

}
