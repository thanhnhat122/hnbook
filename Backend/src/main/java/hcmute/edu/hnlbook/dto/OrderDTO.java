package hcmute.edu.hnlbook.dto;

import hcmute.edu.hnlbook.model.Order;
import hcmute.edu.hnlbook.model.User;

import javax.persistence.*;
import java.util.Date;

public class OrderDTO {
    private int id;
    private String email;

    private Date orderDate;
    private Order.StatusEnum status;
    private  double totalPrice;
    private User user;

    public enum StatusEnum {
        Chua_Dat_Hang,
        Cho_Xac_Nhan,
        Dang_Giao,
        Da_Nhan,
        Da_Huy;
    }

    public OrderDTO() {
    }

    public OrderDTO(int id, String email, Order.StatusEnum status, double totalPrice) {
        this.id = id;
        this.email = email;
        this.status = status;
        this.totalPrice = totalPrice;
    }


    public OrderDTO(int id, String email, Order.StatusEnum status, double totalPrice, User user) {
        this.id = id;
        this.email = email;
        this.status = status;
        this.totalPrice = totalPrice;
        this.user = user;
    }
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getOrderDate() {
        return orderDate;
    }
    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    public Order.StatusEnum getStatus() {
        return status;
    }

    public void setStatus(Order.StatusEnum status) {
        this.status = status;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }
}
