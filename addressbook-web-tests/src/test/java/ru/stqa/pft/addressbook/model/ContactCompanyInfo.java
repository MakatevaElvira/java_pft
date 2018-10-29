package ru.stqa.pft.addressbook.model;

public class ContactCompanyInfo {
  private final String company;
  private final String addressCompany;

  public ContactCompanyInfo(String company, String addressCompany) {
    this.company = company;
    this.addressCompany = addressCompany;
  }

  public String getCompany() {
    return company;
  }

  public String getAddressCompany() {
    return addressCompany;
  }
}
