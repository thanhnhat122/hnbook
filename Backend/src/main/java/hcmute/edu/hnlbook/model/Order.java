package hcmute.edu.hnlbook.model;

import hcmute.edu.hnlbook.utlis.Validate;
import hcmute.edu.hnlbook.utlis.Validate.Type;
import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "Orders")
public class Order {
  @Id
  @GeneratedValue (strategy = GenerationType.IDENTITY)
  private int id;
  @Column(nullable = false)
  private String email;
  @Temporal(TemporalType.DATE)
  private final Date orderDate = new Date();
  @Column(nullable = false)
  @Enumerated(EnumType.STRING)
  private StatusEnum status;

  public enum StatusEnum {
    Chua_Dat_Hang,
    Cho_Xac_Nhan,
    Dang_Giao,
    Da_Nhan,
    Da_Huy;
  }

  public Order() {
  }

  public Order(String Email, StatusEnum Status) {
    email = Email;
    status = Status;
  }

  public int getID() {
    return id;
  }

  public void setID(int ID) {
    this.id = ID;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String Email) {
    if(Validate.isWhatever(Type.EMAIl, Email))
      this.email = Email;
    else
      throw new RuntimeException("Incorrect email format");
  }

  public Date getOrderDate() {
    return orderDate;
  }

  public StatusEnum getStatus() {
    return status;
  }

  public void setStatus(StatusEnum Status) {
    status = Status;
  }
}
