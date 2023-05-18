import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Author } from 'src/app/_class/author';
import { AuthorService } from 'src/app/_service/author.service';

@Component({
  selector: 'app-create-author',
  templateUrl: './create-author.component.html',
  styleUrls: ['./create-author.component.css']
})
export class CreateAuthorComponent implements OnInit {

  author: Author = new Author();
  constructor(private authorService: AuthorService, private router: Router) { }

  ngOnInit(): void {
  }

  saveAuthor() {
      this.authorService.createAuthor(this.author).subscribe(data => {
      console.log(data);
      this.goToAuthorList();
    },
    error => console.log(error));
  }

  goToAuthorList() {
    this.router.navigate(['/staff'])
  }

  onSubmit() {
    console.log(this.author);
    this.saveAuthor();
  }
}
