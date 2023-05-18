package hcmute.edu.hnlbook.model;

import javax.persistence.*;

@Entity
public class Book {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id;

  @Column(nullable = false)
  private int authorId;

  @Column(nullable = false)
  private int publisherId;

  @Column(nullable = false)
  private String title;

  @Column(nullable = false, unique = true)
  private String isbn;

  @Column(nullable = false)
  @Enumerated(EnumType.STRING)
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

  @Column(nullable = false)
  @Enumerated(EnumType.STRING)
  private typeEnum type;

  public enum typeEnum {
    Bia_Cung,
    Bia_Mem,
    NONE
  }

  @Column(nullable = false)
  private int publicationYear;

  @Column(nullable = false)
  private Double price;

  @Column(nullable = false)
  private int remain;

  @Column(nullable = false)
  private int sell;

  @Column(nullable = false)
  private String size;

  @Column(nullable = false)
  private int pageNumber;

  @Column(length = 1000)
  private String image;

  @Column(nullable = false, length = 3000)
  private String description;

  public Book() {
  }

  public Book(int authorId, int publisherId, String title, String isbn, genreEnum genre, typeEnum type, int publicationYear, Double price, int remain, int sell, String size, int pageNumber, String image, String description) {
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

  @Override
  public String toString() {
    return "Book{" +
        "id=" + id +
        ", authorId=" + authorId +
        ", publisherId=" + publisherId +
        ", title='" + title + '\'' +
        ", isbn='" + isbn + '\'' +
        ", genre='" + genre + '\'' +
        ", type='" + type + '\'' +
        ", publicationYear=" + publicationYear +
        ", price=" + price +
        ", remain=" + remain +
        ", sell=" + sell +
        ", size='" + size + '\'' +
        ", pageNumber=" + pageNumber +
        ", image='" + image + '\'' +
        ", description='" + description + '\'' +
        '}';
  }
}
