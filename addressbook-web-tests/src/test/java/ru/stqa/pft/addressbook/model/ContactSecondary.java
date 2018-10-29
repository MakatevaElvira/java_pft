package ru.stqa.pft.addressbook.model;

public class ContactSecondary {
  private final String address2;
  private final String addressHome;
  private final String elses;

  public ContactSecondary(String address2, String addressHome, String elses) {
    this.address2 = address2;
    this.addressHome = addressHome;
    this.elses = elses;
  }

  public String getAddress2() {
    return address2;
  }

  public String getAddressHome() {
    return addressHome;
  }

  public String getElses() {
    return elses;
  }
}