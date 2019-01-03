package ru.stqa.pft.fis.model;

public class ContactCompanyInfo {
  private  String company;
  private  String addressCompany;

  public String getCompany() {
    return company;
  }

  public String getAddressCompany() {
    return addressCompany;
  }

  public ContactCompanyInfo withCompany(String company) {
    this.company = company;
    return this;
  }

  public ContactCompanyInfo withAddressCompany(String addressCompany) {
    this.addressCompany = addressCompany;
    return this;
  }
}
