import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import * as Chart from 'chart.js';
import { BookService } from 'src/app/_service/book.service';
import { ChartService } from 'src/app/_service/chart.service';

@Component({
  selector: 'app-chart',
  templateUrl: './chart.component.html',
  styleUrls: ['./chart.component.css']
})
export class ChartComponent implements OnInit {

  bookSoldList !: number[];
  year = new Date().getFullYear();

  constructor(
    private ChartItems: ChartService,
    private httpClient: HttpClient,
    private bookService: BookService,
  ) { }

  ngOnInit(): void {
    this.init();
    
  }

  init() {
    this.bookService.bookSoldForYear(this.year).subscribe(
      (data: any) => {
         this.bookSoldList = data['data'];
         console.log(this.bookSoldList);
         

         this.getChartDetails();
      }
    );
  }

  getChartDetails() {
    let test = <HTMLCanvasElement> document.getElementById('myChart');
    let myChart = test.getContext('2d');
    // Global Options
    Chart.defaults.global.defaultFontFamily = 'Lato';
    Chart.defaults.global.defaultFontSize = 18;
    Chart.defaults.global.defaultFontColor = '#777';
    if (!myChart || !(myChart instanceof CanvasRenderingContext2D)) {
      throw new Error('Failed to get 2D context');
    }

    let massPopChart = new Chart(myChart, {
      type:'bar', // bar, horizontalBar, pie, line, doughnut, radar, polarArea
      data:{
        labels:['Tháng 1', 'Tháng 2', 'Tháng 3', 'Tháng 4', 'Tháng 5', 'Tháng 6', 'Tháng 7', 
        'Tháng 8', 'Tháng 9', 'Tháng 10', 'Tháng 11', 'Tháng 12'],
        datasets:[{
          label:'Sách đã bán',
          data:[
            this.bookSoldList[0],
            this.bookSoldList[1],
            this.bookSoldList[2],
            this.bookSoldList[3],
            this.bookSoldList[4],
            this.bookSoldList[5],
            this.bookSoldList[6],
            this.bookSoldList[7],
            this.bookSoldList[8],
            this.bookSoldList[9],
            this.bookSoldList[10],
            this.bookSoldList[11]
          ],

          //backgroundColor:'green',
          backgroundColor:[
            'rgba(255, 99, 132, 0.6)',
            'rgba(54, 162, 235, 0.6)',
            'rgba(255, 206, 86, 0.6)',
            'rgba(75, 192, 192, 0.6)',
            'rgba(153, 102, 255, 0.6)',
            'rgba(255, 159, 64, 0.6)',
            'rgba(255, 99, 132, 0.6)',
            'rgba(255, 99, 132, 0.6)',
            'rgba(54, 162, 235, 0.6)',
            'rgba(255, 206, 86, 0.6)',
            'rgba(75, 192, 192, 0.6)',
            'rgba(153, 102, 255, 0.6)',
          ],
          borderWidth:1,
          borderColor:'#777',
          hoverBorderWidth:3,
          hoverBorderColor:'#000'
        }]
      },
      options:{
        title:{
          display:true,
          text:'Số sách bán đã bán năm ' + this.year,
          fontSize:25
        },
        legend:{
          display:true,
          position:'right',
          labels:{
            fontColor:'#000'
          }
        },
        layout:{
          padding:{
            left:50,
            right:0,
            bottom:0,
            top:0
          }
        },
        tooltips:{
          enabled:true
        }
      }
    });
  }

}
