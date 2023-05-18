import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Publisher } from 'src/app/_class/publisher';
import { PublisherService } from 'src/app/_service/publisher.service';

@Component({
  selector: 'app-create-publisher',
  templateUrl: './create-publisher.component.html',
  styleUrls: ['./create-publisher.component.css']
})
export class CreatePublisherComponent implements OnInit {

  publisher: Publisher = new Publisher();
  constructor(private publisherService: PublisherService, private router: Router) { }

  ngOnInit(): void {
  }

  savePublisher() {
      this.publisherService.createPublisher(this.publisher).subscribe(data => {
      console.log(data);
      this.goToPublisherList();
    },
    error => console.log(error));
  }

  goToPublisherList() {
    this.router.navigate(['/staff'])
  }

  onSubmit() {
    console.log(this.publisher);
    this.savePublisher();
  }
}