package hcmute.edu.hnlbook.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class OrderItem {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id;
  private int orderId;
  private int bookId;
  private int quantity;

  public OrderItem() {
  }

  public OrderItem(int OrderID, int BookID, int Quantity) {
    orderId = OrderID;
    bookId = BookID;
    quantity = Quantity;
  }

  public int getID() {
    return id;
  }

  public void setID(int ID) {
    this.id = ID;
  }

  public int getOrderID() {
    return orderId;
  }

  public void setOrderID(int OrderID) {
    orderId = OrderID;
  }

  public int getBookID() {
    return bookId;
  }

  public void setBookID(int BookID) {
    bookId = BookID;
  }

  public int getQuantity() {
    return quantity;
  }

  public void setQuantity(int Quantity) {
    quantity = Quantity;
  }
}
