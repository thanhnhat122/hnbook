import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Route, Router } from '@angular/router';
import { Author } from 'src/app/_class/author';
import { AuthorService } from 'src/app/_service/author.service';

@Component({
  selector: 'app-update-author',
  templateUrl: './update-author.component.html',
  styleUrls: ['./update-author.component.css']
})
export class UpdateAuthorComponent implements OnInit {

  id!: number;
  author: Author = new Author();
  constructor(private authorService: AuthorService, private route: ActivatedRoute, private router: Router) { }

  ngOnInit(): void {
    this.id = this.route.snapshot.params['id'];

    this.authorService.getAuthorById(this.id).subscribe(
      (data: any) => this.author = data['data']
    );
  }

  onSubmit() {
    this.authorService.updateAuthor(this.id, this.author).subscribe ( data => {
      this.goToAuthorList();
    }, error => console.log(error));
  }

  goToAuthorList() {
    this.router.navigate(['/staff'])
  }
}
