package ru.stqa.pft.fis.model;

public class ContactNumber {
  private  String homeNumber;
  private  String mobileNumber;
  private  String workNumber;

  public ContactNumber withHomeNumber(String homeNumber) {
    this.homeNumber = homeNumber;
    return this;
  }

  public ContactNumber withMobileNumber(String mobileNumber) {
    this.mobileNumber = mobileNumber;
    return this;
  }

  public ContactNumber withWorkNumber(String workNumber) {
    this.workNumber = workNumber;
    return this;
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
}
