package ru.stqa.pft.addressbook.model;

import java.util.Objects;

public class ContactGeneral {
  private int id = Integer.MAX_VALUE;
  private  String name;
  private  String lastname;
  private  String homeNumber;
  private  String mobileNumber;
  private  String workNumber;
  private String AllPhones;
  private  String email1;
  private  String email2;
  private  String email3;
  private String AllEmails;


  public ContactGeneral withAllPhones(String allPhones) {
    AllPhones = allPhones;
    return this;
  }
  public ContactGeneral withAllEmails(String allEmails) {
    AllEmails = allEmails;
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
    this.lastname = lastname;
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

  public int getId() {
    return id;
  }

  public String getName() {
    return name;
  }

  public String getLastname() {
    return lastname;
  }

  public String getHomeNumber() { return homeNumber;  }

  public String getMobileNumber() { return mobileNumber;  }

  public String getWorkNumber() { return workNumber;  }

  public String getEmail1() { return email1;  }

  public String getEmail2() { return email2;  }

  public String getEmail3() { return email3;  }
  public String getAllPhones() { return AllPhones;  }

  public String getAllEmails() { return AllEmails;  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    ContactGeneral general = (ContactGeneral) o;
    return Objects.equals(name, general.name) &&
            Objects.equals(lastname, general.lastname);
  }

  @Override
  public int hashCode() {
    return Objects.hash(name, lastname);
  }

  @Override
  public String toString() {
    return "ContactGeneral{" +
            "id='" + id + '\'' +
            ", name='" + name + '\'' +
            ", lastname='" + lastname + '\'' +
            '}';
  }

}
