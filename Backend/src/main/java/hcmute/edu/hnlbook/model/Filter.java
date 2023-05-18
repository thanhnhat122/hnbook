package hcmute.edu.hnlbook.model;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.util.ArrayList;
import java.util.List;

public class Filter {

  private Book.genreEnum genre;
  private double minPrice;
  private double maxPrice;
  private int publicationYear;
  private ArrayList<Integer> publisherIds;
  private ArrayList<Integer> authorIds;
  private Book.typeEnum type;

  public Filter(Book.genreEnum genre, double minPrice, double maxPrice, int publicationYear, ArrayList<Integer> publisherIds, ArrayList<Integer> authorIds, Book.typeEnum type) {
    this.genre = genre;
    this.minPrice = minPrice;
    this.maxPrice = maxPrice;
    this.publicationYear = publicationYear;
    this.publisherIds = publisherIds;
    this.authorIds = authorIds;
    this.type = type;
  }

  public Book.genreEnum getGenre() {
    return genre;
  }

  public void setGenre(Book.genreEnum genre) {
    this.genre = genre;
  }

  public double getMinPrice() {
    return minPrice;
  }

  public void setMinPrice(double minPrice) {
    this.minPrice = minPrice;
  }

  public double getMaxPrice() {
    return maxPrice;
  }

  public void setMaxPrice(double maxPrice) {
    this.maxPrice = maxPrice;
  }

  public int getPublicationYear() {
    return publicationYear;
  }

  public void setPublicationYear(int publicationYear) {
    this.publicationYear = publicationYear;
  }

  public ArrayList<Integer> getPublisherIds() {
    return publisherIds;
  }

  public void setPublisherIds(ArrayList<Integer> publisherIds) {
    this.publisherIds = publisherIds;
  }

  public ArrayList<Integer> getAuthorIds() {
    return authorIds;
  }

  public void setAuthorIds(ArrayList<Integer> authorIds) {
    this.authorIds = authorIds;
  }

  public Book.typeEnum getType() {
    return type;
  }

  public void setType(Book.typeEnum type) {
    this.type = type;
  }

  @Override
  public String toString() {
    return "Filter{" +
        "genre=" + genre +
        ", minPrice=" + minPrice +
        ", maxPrice=" + maxPrice +
        ", publicationYear=" + publicationYear +
        ", publisherIds=" + publisherIds +
        ", authorIds=" + authorIds +
        ", type=" + type +
        '}';
  }
}
