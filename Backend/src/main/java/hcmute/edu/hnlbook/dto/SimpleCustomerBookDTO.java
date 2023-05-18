package hcmute.edu.hnlbook.dto;

import hcmute.edu.hnlbook.model.Author;
import hcmute.edu.hnlbook.model.Publisher;

import java.util.Optional;

public class SimpleCustomerBookDTO {
  private int id;
  private String title;
  private Double price;
  private int sell;
  private String image;
  private Double totalRate;
  private int ratingNumber;

  public SimpleCustomerBookDTO() {
  }

  public SimpleCustomerBookDTO(String title, Double price, int sell, String image, Double totalRate, int ratingNumber) {
    this.title = title;
    this.price = price;
    this.sell = sell;
    this.image = image;
    this.totalRate = totalRate;
    this.ratingNumber = ratingNumber;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public Double getPrice() {
    return price;
  }

  public void setPrice(Double price) {
    this.price = price;
  }

  public int getSell() {
    return sell;
  }

  public void setSell(int sell) {
    this.sell = sell;
  }

  public String getImage() {
    return image;
  }

  public void setImage(String image) {
    this.image = image;
  }

  public Double getTotalRate() {
    return totalRate;
  }

  public void setTotalRate(Double totalRate) {
    this.totalRate = totalRate;
  }

  public int getRatingNumber() {
    return ratingNumber;
  }

  public void setRatingNumber(int ratingNumber) {
    this.ratingNumber = ratingNumber;
  }
}
