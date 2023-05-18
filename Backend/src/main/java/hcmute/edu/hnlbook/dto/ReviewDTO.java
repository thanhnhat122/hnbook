package hcmute.edu.hnlbook.dto;

import hcmute.edu.hnlbook.model.Book;
import hcmute.edu.hnlbook.model.User;

import java.util.Optional;

public class ReviewDTO {
  private int id;
  private int bookId;
  private String userEmail;
  private String comment;
  private int rate;
  private String response;
  private Optional<User> user;

  public ReviewDTO() {
  }

  public ReviewDTO(int bookId, String userEmail, String comment, int rate, String response, Optional<User> user) {
    this.bookId = bookId;
    this.userEmail = userEmail;
    this.comment = comment;
    this.rate = rate;
    this.response = response;
    this.user = user;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public int getBookId() {
    return bookId;
  }

  public void setBookId(int bookId) {
    this.bookId = bookId;
  }

  public String getUserEmail() {
    return userEmail;
  }

  public void setUserEmail(String userEmail) {
    this.userEmail = userEmail;
  }

  public String getComment() {
    return comment;
  }

  public void setComment(String comment) {
    this.comment = comment;
  }

  public int getRate() {
    return rate;
  }

  public void setRate(int rate) {
    this.rate = rate;
  }

  public String getResponse() {
    return response;
  }

  public void setResponse(String response) {
    this.response = response;
  }

  public Optional<User> getUser() {
    return user;
  }

  public void setUser(Optional<User> user) {
    this.user = user;
  }
}
