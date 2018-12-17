package ru.stqa.pft.addressbook.model;

import com.google.gson.annotations.Expose;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamOmitField;

import java.io.File;
import java.util.Objects;

@XStreamAlias("contact")
public class ContactGeneral {
  @XStreamOmitField
  private int id = Integer.MAX_VALUE;
  @Expose
  private String name;
  @Expose
  private String lastName;
  @Expose
  private String homeNumber;
  @Expose
  private String mobileNumber;
  @Expose
  private String workNumber;
  @Expose
  private String allPhones;
  @Expose
  private String email1;
  @Expose
  private String email2;
  @Expose
  private String email3;
  @Expose
  private String allEmails;
  @Expose
  private String address;
  @Expose
  private File photo;


  public ContactGeneral withPhoto(File photo) {
    this.photo = photo;
    return this;
  }

  public ContactGeneral withAdress(String address) {
    this.address = address;
    return this;
  }

  public ContactGeneral withAllPhones(String allPhones) {
    this.allPhones = allPhones;
    return this;
  }

  public ContactGeneral withAllEmails(String allEmails) {
    this.allEmails = allEmails;
    return this;
  }

  public ContactGeneral withEmail3(String email3) {
    this.email3 = email3;
    return this;
  }

  public ContactGeneral withEmail2(String email2) {
    this.email2 = email2;
    return this;
  }

  public ContactGeneral withEmail(String email) {
    this.email1 = email;
    return this;
  }

  public ContactGeneral withId(int id) {
    this.id = id;
    return this;
  }

  public ContactGeneral withName(String name) {
    this.name = name;
    return this;
  }

  public ContactGeneral withLastname(String lastname) {
    this.lastName = lastname;
    return this;
  }

  public ContactGeneral withHomeNumber(String homeNumber) {
    this.homeNumber = homeNumber;
    return this;
  }

  public ContactGeneral withMobileNumber(String mobileNumber) {
    this.mobileNumber = mobileNumber;
    return this;
  }

  public ContactGeneral withWorkNumber(String workNumber) {
    this.workNumber = workNumber;
    return this;
  }
  public File getPhoto() {
    return photo;
  }

  public int getId() {
    return id;
  }

  public String getName() {
    return name;
  }

  public String getLastName() {
    return lastName;
  }

  public String getHomeNumber() {
    return homeNumber;
  }

  public String getMobileNumber() {
    return mobileNumber;
  }

  public String getWorkNumber() {
    return workNumber;
  }

  public String getEmail1() {
    return email1;
  }

  public String getEmail2() {
    return email2;
  }

  public String getEmail3() {
    return email3;
  }

  public String getAllPhones() {
    return allPhones;
  }

  public String getAllEmails() {
    return allEmails;
  }

  public String getAddress() {
    return address;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    ContactGeneral general = (ContactGeneral) o;
    return id == general.id &&
            Objects.equals(name, general.name) &&
            Objects.equals(lastName, general.lastName);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, name, lastName);
  }

  @Override
  public String toString() {
    return "ContactGeneral{" +
            "id='" + id + '\'' +
            ", name='" + name + '\'' +
            ", lastName='" + lastName + '\'' +
            '}';
  }

}
