import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AdminComponent } from './admin/admin.component';
import { AuthorListComponent } from './author/author-list/author-list.component';
import { CreateAuthorComponent } from './author/create-author/create-author.component';
import { UpdateAuthorComponent } from './author/update-author/update-author.component';
import { BookTypeComponent } from './book-type/book-type.component';
import { BookDetailsComponent } from './book/book-details/book-details.component';
import { BookListComponent } from './book/book-list/book-list.component';
import { CreateBookComponent } from './book/create-book/create-book.component';
import { UpdateBookComponent } from './book/update-book/update-book.component';
import { CartHistoryComponent } from './cart/cart-history/cart-history.component';
import { CartDetailComponent } from './cart/cart-detail/cart-detail.component';
import { ForbiddenComponent } from './forbidden/forbidden.component';
import { HomeComponent } from './home/home.component';
import { LoginComponent } from './login/login.component';
import { CreatePublisherComponent } from './publisher/create-publisher/create-publisher.component';
import { PublisherListComponent } from './publisher/publisher-list/publisher-list.component';
import { UpdatePublisherComponent } from './publisher/update-publisher/update-publisher.component';
import { CreateReviewComponent } from './review/create-review/create-review.component';
import { ReviewListComponent } from './review/review-list/review-list.component';
import { UpdateReviewComponent } from './review/update-review/update-review.component';
import { SignupComponent } from './signup/signup.component';
import { StaffComponent } from './staff/staff.component';
import { CreateUserComponent } from './user/create-user/create-user.component';
import { UserDetailsComponent } from './user/user-details/user-details.component';
import { UserListComponent } from './user/user-list/user-list.component';
import { AuthGuard } from './_auth/auth.guard';
import { CustomerBookDetailsComponent } from './book/customer-book-details/customer-book-details.component';
import { CustomerDetailComponent } from './customer/customer-detail/customer-detail.component';
import { UpdateUserComponent } from './user/update-user/update-user.component';
import { StaffCartDetailComponent } from './cart/staff-cart-detail/staff-cart-detail.component';
import { AuthorComponent } from './filter/author/author.component';
import { SearchComponent } from './book/search/search.component';

const routes: Routes = [
  {path: "home", component:HomeComponent},
  {path: "admin", component:AdminComponent, canActivate:[AuthGuard], data:{role:'ROLE_AD'}},
  {path: "login",component:LoginComponent},
  {path: "staff",component:StaffComponent, canActivate:[AuthGuard], data:{role:'ROLE_NV'}},
  {path: "signup",component:SignupComponent},
  {path: 'books', component: BookListComponent, canActivate:[AuthGuard], data:{role:'ROLE_NV'}},
  {path: 'create-book', component: CreateBookComponent, canActivate:[AuthGuard], data:{role:'ROLE_NV'}},
  {path: 'update-book/:id', component: UpdateBookComponent, canActivate:[AuthGuard], data:{role:'ROLE_NV'}},
  {path: 'book-details/:id', component: BookDetailsComponent, canActivate:[AuthGuard], data:{role:'ROLE_NV'}},
  {path: 'authors', component: AuthorListComponent, canActivate:[AuthGuard], data:{role:'ROLE_NV'}},
  {path: 'create-author', component: CreateAuthorComponent, canActivate:[AuthGuard], data:{role:'ROLE_NV'}},
  {path: 'update-author/:id', component: UpdateAuthorComponent, canActivate:[AuthGuard], data:{role:'ROLE_NV'}},
  {path: 'publishers', component: PublisherListComponent, canActivate:[AuthGuard], data:{role:'ROLE_NV'}},
  {path: 'create-publisher', component: CreatePublisherComponent, canActivate:[AuthGuard], data:{role:'ROLE_NV'}},
  {path: 'update-publisher/:id', component: UpdatePublisherComponent, canActivate:[AuthGuard], data:{role:'ROLE_NV'}},
  {path: 'reviews', component: ReviewListComponent, canActivate:[AuthGuard], data:{role:'ROLE_NV'}},
  {path: 'create-review', component: CreateReviewComponent},
  {path: 'update-review/:id', component: UpdateReviewComponent},
  {path: 'forbidden', component:ForbiddenComponent},
  {path: 'book-type/:type', component: BookTypeComponent},
  {path: 'users', component: UserListComponent},
  {path: 'user-details/:email', component: UserDetailsComponent},
  {path: 'create-user', component: CreateUserComponent, canActivate:[AuthGuard], data:{role:'ROLE_AD'}},
  {path: 'update-user/:email', component: UpdateUserComponent, canActivate:[AuthGuard], data:{role:'ROLE_AD'}},
  {path: 'cart-detail',component:CartDetailComponent},
  {path: 'cart-history', component: CartHistoryComponent},
  {path: 'customer-book-details/:id', component: CustomerBookDetailsComponent},
  {path: 'customer-detail', component: CustomerDetailComponent},
  {path: 'customer-details/:email', component: CustomerDetailComponent},
  {path: 'staff-cart-detail/:id', component: StaffCartDetailComponent,canActivate:[AuthGuard], data:{role:'ROLE_NV'}},
  {path: 'author/:id', component: AuthorComponent},
  {path: 'search/:title', component: SearchComponent},
  {path: '', redirectTo: 'home', pathMatch: 'full'}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
