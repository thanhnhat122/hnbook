import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Review } from 'src/app/_class/review';
import { ReviewService } from 'src/app/_service/review.service';
import { User } from 'src/app/_class/user';
import { ToastrService } from 'ngx-toastr';

@Component({
  selector: 'app-review-list',
  templateUrl: './review-list.component.html',
  styleUrls: ['./review-list.component.css']
})
export class ReviewListComponent implements OnInit {

  reviews!: Review[];
  reviewTemp: Review = new Review();
  user: User = new User();

  constructor(
    private reviewService: ReviewService, 
    private router: Router,
    private toastr: ToastrService) 
    { }

  ngOnInit(): void {
    this.reviewTemp.id = 1;
    this.reviewTemp.bookId = 1;
    this.reviewTemp.userEmail = 'abc@gmail.com';
    this.reviewTemp.orderItemId = 1;
    this.reviewTemp.comment = '...';
    this.reviewTemp.rate = 1;
    this.user.firstName = 'Long';
    this.user.lastName = 'Hoàng';
    this.user.phone = '0922113344';
    this.reviewTemp.user = this.user;
    this.getReviews();
  }

  private getReviews() {
    this.reviewService.getReviewDTOsList().subscribe(
      (data: any) => this.reviews = data['data']
    );
  }

  updateReview(id: number) {
    this.router.navigate(['update-review', id]);
  } 

  deleteReview(id: number) {
    this.reviewService.deleteReview(id).subscribe( data => {
      console.log(data);
      this.getReviews();
    })
  }

  reviewDetails(id: number) {
    this.reviewService.getReviewDTOById(id).subscribe(
      (data: any) => {
        this.reviewTemp = data['data']
        console.log(data);
    });
  }

  sendResponse() {
    this.reviewService.updateReviewResponse(this.reviewTemp).subscribe(
      (data: any) => {
        this.toastr.success('Thêm phản hồi thành công!');
        console.log(data);
      }
    )
  }
}
