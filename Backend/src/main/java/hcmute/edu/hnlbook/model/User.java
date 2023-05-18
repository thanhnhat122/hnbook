package hcmute.edu.hnlbook.model;

import hcmute.edu.hnlbook.utlis.Validate;

import javax.persistence.*;

@Entity
public class User {
  @Id
  private String email;
  @Column(nullable = false, length = 50)
  private String firstName;
  @Column(nullable = false, length = 50)
  private String lastName;

  private String address;
  @Column(nullable = false)
  private String province;
  @Column(nullable = false, unique = true)
  private String phone;
  private String password;
  private RoleEnum role;

  public enum RoleEnum{
    ROLE_AD,
    ROLE_KH,
    ROLE_NV;
  }

  public User() {
  }

  public User(String Email, String FirstName, String LastName, String Address, String Province, String Phone, String Password, RoleEnum Role) {
    email = Email;
    firstName = FirstName;
    lastName = LastName;
    address = Address;
    province = Province;
    phone = Phone;
    password = Password;
    role = Role;
  }

  public String getFirstName() {
    return firstName;
  }

  public void setFirstName(String FirstName) {
    firstName = FirstName;
  }

  public String getLastName() {
    return lastName;
  }

  public void setLastName(String LastName) {
    lastName = LastName;
  }

  public String getAddress() {
    return address;
  }

  public void setAddress(String Address) {
    address = Address;
  }

  public String getProvince() {
    return province;
  }

  public void setProvince(String Province) {
    province = Province;
  }

  public String getPhone() {
    return phone;
  }

  public void setPhone(String Phone) {
    if (Validate.isWhatever(Validate.Type.PHONE, Phone))
      this.phone = Phone;
    else
      throw new RuntimeException("Incorrect phone format");
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String Email) {

    if(Validate.isWhatever(Validate.Type.EMAIl, Email))
      this.email = Email;
    else
      throw new RuntimeException("Incorrect email format");
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String Password) {
    password = Password;
  }

  public RoleEnum getRole() {
    return role;
  }

  public void setRole(RoleEnum Role) {
    role = Role;
  }
}
