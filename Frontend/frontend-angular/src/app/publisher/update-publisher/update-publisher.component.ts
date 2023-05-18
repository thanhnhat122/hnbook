import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Publisher } from 'src/app/_class/publisher';
import { PublisherService } from 'src/app/_service/publisher.service';

@Component({
  selector: 'app-update-publisher',
  templateUrl: './update-publisher.component.html',
  styleUrls: ['./update-publisher.component.css']
})
export class UpdatePublisherComponent implements OnInit {

  id!: number;
  publisher: Publisher = new Publisher();
  constructor(private publisherService: PublisherService, private route: ActivatedRoute, private router: Router) { }

  ngOnInit(): void {
    this.id = this.route.snapshot.params['id'];

    this.publisherService.getPublisherById(this.id).subscribe(
      (data: any) => this.publisher = data['data']
    );
  }

  onSubmit() {
    this.publisherService.updatePublisher(this.id, this.publisher).subscribe ( data => {
      this.goToPublisherList();
    }, error => console.log(error));
  }

  goToPublisherList() {
    this.router.navigate(['/staff'])
  }
}
