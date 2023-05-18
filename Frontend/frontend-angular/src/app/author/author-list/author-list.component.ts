import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Author } from 'src/app/_class/author';
import { AuthorService } from 'src/app/_service/author.service';

@Component({
  selector: 'app-author-list',
  templateUrl: './author-list.component.html',
  styleUrls: ['./author-list.component.css']
})
export class AuthorListComponent implements OnInit {

  authors!: Author[];
  
  constructor(private authorService: AuthorService, private router: Router) { }

  ngOnInit(): void {
    this.getAuthors();
  }

  private getAuthors() {
    this.authorService.getAuthorsList().subscribe(
      (data: any) => this.authors = data['data']
    );
  }

  updateAuthor(id: number) {
    this.router.navigate(['update-author', id]);
  } 

  deleteAuthor(id: number) {
    this.authorService.deleteAuthor(id).subscribe( data => {
      console.log(data);
      this.getAuthors();
    })
  }
}
