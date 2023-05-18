import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Publisher } from 'src/app/_class/publisher';
import { PublisherService } from 'src/app/_service/publisher.service';

@Component({
  selector: 'app-publisher-list',
  templateUrl: './publisher-list.component.html',
  styleUrls: ['./publisher-list.component.css']
})
export class PublisherListComponent implements OnInit {

  publishers!: Publisher[];
  
  constructor(private publisherService: PublisherService, private router: Router) { }

  ngOnInit(): void {
    this.getPublishers();
  }

  private getPublishers() {
    this.publisherService.getPublishersList().subscribe(
      (data: any) => this.publishers = data['data']
    );
  }

  updatePublisher(id: number) {
    this.router.navigate(['update-publisher', id]);
  } 

  deletePublisher(id: number) {
    this.publisherService.deletePublisher(id).subscribe( data => {
      console.log(data);
      this.getPublishers();
    })
  }
}
