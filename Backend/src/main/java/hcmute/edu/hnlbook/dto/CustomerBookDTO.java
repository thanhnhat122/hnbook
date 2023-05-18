package hcmute.edu.hnlbook.dto;

import hcmute.edu.hnlbook.model.Author;
import hcmute.edu.hnlbook.model.Publisher;

import java.util.Optional;

public class CustomerBookDTO {
  private int id;
  private int authorId;
  private int publisherId;
  private String title;
  private String isbn;
  private genreEnum genre;
  public enum genreEnum {
    Sach_Giao_Khoa,
    Ky_Nang_Song,
    Tieu_Thuyet,
    Light_Novel,
    Manga,
    Kinh_Te,
    Tu_Dien,
    NONE
  }
  private typeEnum type;
  public enum typeEnum {
    Bia_Cung,
    Bia_Mem,
    NONE
  }
  private int publicationYear;
  private Double price;
  private int remain;
  private int sell;
  private String size;
  private int pageNumber;
  private String image;
  private String description;
  private Optional<Author> author;
  private Optional<Publisher> publisher;
  private Double totalRate;
  private String[] imageArray;

  public CustomerBookDTO() {
  }

  public CustomerBookDTO(int authorId, int publisherId, String title, String isbn, genreEnum genre, typeEnum type, int publicationYear, Double price, int remain, int sell, String size, int pageNumber, String image, String description, Optional<Author> author, Optional<Publisher> publisher, Double totalRate, String[] imageArray) {
    this.authorId = authorId;
    this.publisherId = publisherId;
    this.title = title;
    this.isbn = isbn;
    this.genre = genre;
    this.type = type;
    this.publicationYear = publicationYear;
    this.price = price;
    this.remain = remain;
    this.sell = sell;
    this.size = size;
    this.pageNumber = pageNumber;
    this.image = image;
    this.description = description;
    this.author = author;
    this.publisher = publisher;
    this.totalRate = totalRate;
    this.imageArray = imageArray;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public int getAuthorId() {
    return authorId;
  }

  public void setAuthorId(int authorId) {
    this.authorId = authorId;
  }

  public int getPublisherId() {
    return publisherId;
  }

  public void setPublisherId(int publisherId) {
    this.publisherId = publisherId;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public String getIsbn() {
    return isbn;
  }

  public void setIsbn(String isbn) {
    this.isbn = isbn;
  }

  public genreEnum getGenre() {
    return genre;
  }

  public void setGenre(genreEnum genre) {
    this.genre = genre;
  }

  public typeEnum getType() {
    return type;
  }

  public void setType(typeEnum type) {
    this.type = type;
  }

  public int getPublicationYear() {
    return publicationYear;
  }

  public void setPublicationYear(int publicationYear) {
    this.publicationYear = publicationYear;
  }

  public Double getPrice() {
    return price;
  }

  public void setPrice(Double price) {
    this.price = price;
  }

  public int getRemain() {
    return remain;
  }

  public void setRemain(int remain) {
    this.remain = remain;
  }

  public int getSell() {
    return sell;
  }

  public void setSell(int sell) {
    this.sell = sell;
  }

  public String getSize() {
    return size;
  }

  public void setSize(String size) {
    this.size = size;
  }

  public int getPageNumber() {
    return pageNumber;
  }

  public void setPageNumber(int pageNumber) {
    this.pageNumber = pageNumber;
  }

  public String getImage() {
    return image;
  }

  public void setImage(String image) {
    this.image = image;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public Optional<Author> getAuthor() {
    return author;
  }

  public void setAuthor(Optional<Author> author) {
    this.author = author;
  }

  public Optional<Publisher> getPublisher() {
    return publisher;
  }

  public void setPublisher(Optional<Publisher> publisher) {
    this.publisher = publisher;
  }

  public Double getTotalRate() {
    return totalRate;
  }

  public void setTotalRate(Double totalRate) {
    this.totalRate = totalRate;
  }

  public String[] getImageArray() {
    return imageArray;
  }

  public void setImageArray(String[] imageArray) {
    this.imageArray = imageArray;
  }
}
