package ru.stqa.pft.addressbook.model;

public class ContactSecondary {
  private  String address2;
  private  String addressHome;
  private  String elses;


  public ContactSecondary withAddress2(String address2) {
    this.address2 = address2;
    return this;
  }

  public ContactSecondary withAddressHome(String addressHome) {
    this.addressHome = addressHome;
    return this;
  }

  public ContactSecondary withElses(String elses) {
    this.elses = elses;
    return this;
  }

  public String getAddress2() {
    return address2;
  }

  public String getAddressHome() {
    return addressHome;
  }

  public String getElses() {     return elses;
  }
}
