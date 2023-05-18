import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { HomeComponent } from './home/home.component';
import { HeaderComponent } from './header/header.component';
import { AdminComponent } from './admin/admin.component';
import { LoginComponent } from './login/login.component';
import { FormsModule } from '@angular/forms';
import { HttpClientModule, HTTP_INTERCEPTORS } from '@angular/common/http'
import { RouterModule } from '@angular/router';
import { StaffComponent } from './staff/staff.component';
import { SignupComponent } from './signup/signup.component';
import { BookListComponent } from './book/book-list/book-list.component';
import { CreateBookComponent } from './book/create-book/create-book.component';
import { UpdateBookComponent } from './book/update-book/update-book.component';
import { BookDetailsComponent } from './book/book-details/book-details.component';
import { AuthorListComponent } from './author/author-list/author-list.component';
import { CreateAuthorComponent } from './author/create-author/create-author.component';
import { UpdateAuthorComponent } from './author/update-author/update-author.component';
import { PublisherListComponent } from './publisher/publisher-list/publisher-list.component';
import { CreatePublisherComponent } from './publisher/create-publisher/create-publisher.component';
import { UpdatePublisherComponent } from './publisher/update-publisher/update-publisher.component';
import { ReviewListComponent } from './review/review-list/review-list.component';
import { CreateReviewComponent } from './review/create-review/create-review.component';
import { UpdateReviewComponent } from './review/update-review/update-review.component';
import { AuthGuard } from './_auth/auth.guard';
import { AuthInterceptor } from './_auth/auth.interceptor';
import { UserService } from './_service/user.service';
import { ForbiddenComponent } from './forbidden/forbidden.component';
import { BookTypeComponent } from './book-type/book-type.component';
import { UserListComponent } from './user/user-list/user-list.component';
import { UserDetailsComponent } from './user/user-details/user-details.component';
import { StaffListComponent } from './user/staff-list/staff-list.component';
import { CustomerListComponent } from './user/customer-list/customer-list.component';
import { CreateUserComponent } from './user/create-user/create-user.component';
import { CartDetailComponent } from './cart/cart-detail/cart-detail.component';
import { CartHistoryComponent } from './cart/cart-history/cart-history.component';
import { CustomerBookDetailsComponent } from './book/customer-book-details/customer-book-details.component';
import { CustomerDetailComponent } from './customer/customer-detail/customer-detail.component';
import { CustomerSettingComponent } from './customer/customer-setting/customer-setting.component';
import { CartListComponent } from './cart/cart-list/cart-list.component';
import { StaffCartDetailComponent } from './cart/staff-cart-detail/staff-cart-detail.component';
import { UpdateUserComponent } from './user/update-user/update-user.component';

import {BrowserAnimationsModule} from '@angular/platform-browser/animations';
import { ToastrModule } from 'ngx-toastr';
import { AuthorComponent } from './filter/author/author.component';
import { StatisticComponent } from './statistic/statistic.component';
import { ChartComponent } from './statistic/chart/chart.component';
import { SearchComponent } from './book/search/search.component';

@NgModule({
  declarations: [
    AppComponent,
    HomeComponent,
    HeaderComponent,
    AdminComponent,
    LoginComponent,
    StaffComponent,
    SignupComponent,
    BookListComponent,
    CreateBookComponent,
    UpdateBookComponent,
    BookDetailsComponent,
    AuthorListComponent,
    CreateAuthorComponent,
    UpdateAuthorComponent,
    PublisherListComponent,
    CreatePublisherComponent,
    UpdatePublisherComponent,
    ReviewListComponent,
    CreateReviewComponent,
    UpdateReviewComponent,
    ForbiddenComponent,
    BookTypeComponent,
    UserListComponent,
    UserDetailsComponent,
    StaffListComponent,
    CustomerListComponent,
    CreateUserComponent,
    CartDetailComponent,
    CartHistoryComponent,
    CustomerBookDetailsComponent,
    CustomerDetailComponent,
    CustomerSettingComponent,
    CartListComponent,
    StaffCartDetailComponent,
    UpdateUserComponent,
    AuthorComponent,
    StatisticComponent,
    ChartComponent,
    SearchComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    HttpClientModule,
    RouterModule,
    BrowserAnimationsModule,
    ToastrModule.forRoot(
      {
        timeOut: 2000,
      }
    )
  ],
  providers: [
    AuthGuard,
    {
      provide: HTTP_INTERCEPTORS,
      useClass: AuthInterceptor,
      multi: true
    },
    UserService,
    
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
