import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Review } from 'src/app/_class/review';
import { ReviewService } from 'src/app/_service/review.service';

@Component({
  selector: 'app-update-review',
  templateUrl: './update-review.component.html',
  styleUrls: ['./update-review.component.css']
})
export class UpdateReviewComponent implements OnInit {
  
  id!: number;
  review: Review = new Review();
  constructor(private reviewService: ReviewService, private route: ActivatedRoute, private router: Router) { }

  ngOnInit(): void {
    this.id = this.route.snapshot.params['id'];

    this.reviewService.getReviewById(this.id).subscribe(
      (data: any) => this.review = data['data']
    );
  }

  onSubmit() {
    this.reviewService.updateReview(this.id, this.review).subscribe ( data => {
      this.goToReviewList();
    }, error => console.log(error));
  }

  goToReviewList() {
    this.router.navigate(['/reviews'])
  }
}
