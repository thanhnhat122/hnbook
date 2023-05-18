import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';


const httpOptions = {
  headers: new HttpHeaders({
    'Content-Type': 'application/json',
  })
};

@Injectable({
  providedIn: 'root'
})
export class ChartService {

  httpOptions: any;
  baseUrl: any;
  headers: any;
  constructor(private http: HttpClient) {
    this.headers = new Headers({ 'content-type': 'application/json' });
    // this.httpOptions = new RequestOptions( { headers: this.headers } );
  }

  getChart() {
    console.log('SUCCESS!!!!!!!!!!!!!!!!!!!!!!!!!!!!!');
    return this.http.get('https://api.myjson.com/bins/8au55');

  }
}
