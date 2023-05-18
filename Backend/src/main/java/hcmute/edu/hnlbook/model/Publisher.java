package hcmute.edu.hnlbook.model;

import javax.persistence.*;

@Entity
public class Publisher {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id;
  @Column(nullable = false)
  private String name;
  @Column(nullable = false)
  private String country;

  public Publisher() {
  }

  public Publisher(String name, String country) {
    this.name = name;
    this.country = country;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getCountry() {
    return country;
  }

  public void setCountry(String country) {
    this.country = country;
  }

  @Override
  public String toString() {
    return "Publisher{" +
        "id=" + id +
        ", name='" + name + '\'' +
        ", country='" + country + '\'' +
        '}';
  }
}
