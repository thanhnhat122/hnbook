package hcmute.edu.hnlbook.dto;

import hcmute.edu.hnlbook.model.Book;

import java.util.Optional;

public class OrderItemDTO {
  private int id;
  private int orderId;
  private int bookId;
  private int quantity;
  private Optional<Book> book;
  private Double totalPrice;

  public OrderItemDTO() {
  }

  public OrderItemDTO(int id, int orderId, int bookId, int quantity, Book book, Double totalPrice) {
    this.id = id;
    this.orderId = orderId;
    this.bookId = bookId;
    this.quantity = quantity;
    this.book = Optional.ofNullable(book);
    this.totalPrice = totalPrice;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public int getOrderId() {
    return orderId;
  }

  public void setOrderId(int orderId) {
    this.orderId = orderId;
  }

  public int getBookId() {
    return bookId;
  }

  public void setBookId(int bookId) {
    this.bookId = bookId;
  }

  public int getQuantity() {
    return quantity;
  }

  public void setQuantity(int quantity) {
    this.quantity = quantity;
  }

  public Optional<Book> getBook() {
    return book;
  }

  public void setBook(Optional<Book> book) {
    this.book = book;
  }

  public Double getTotalPrice() {
    return totalPrice;
  }

  public void setTotalPrice(Double totalPrice) {
    this.totalPrice = totalPrice;
  }
}
