package hcmute.edu.hnlbook.model;

import javax.persistence.*;

@Entity
public class Review {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id;
  @Column(nullable = false)
  private int bookId;
  @Column(nullable = false)
  private String userEmail;
  @Column(nullable = false)
  private int orderItemId;
  @Column(length = 1000)
  private String comment;
  @Column(nullable = false)
  private int rate;
  @Column(length = 1000)
  private String response;

  public Review() {
  }

  public Review(int bookId, String userEmail, int orderItemId, String comment, int rate, String response) {
    this.bookId = bookId;
    this.userEmail = userEmail;
    this.orderItemId = orderItemId;
    this.comment = comment;
    this.rate = rate;
    this.response = response;
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

  public int getOrderItemId() {
    return orderItemId;
  }

  public void setOrderItemId(int orderItemId) {
    this.orderItemId = orderItemId;
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

  @Override
  public String toString() {
    return "Review{" +
        "id=" + id +
        ", bookId=" + bookId +
        ", userEmail=" + userEmail +
        ", orderItemId=" + orderItemId +
        ", comment='" + comment + '\'' +
        ", rate=" + rate +
        ", response=" + response +
        '}';
  }
}
