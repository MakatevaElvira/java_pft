package ru.stqa.pft.addressbook;

public class GroupCompanyInfo {
  private final String company;
  private final String addressCompany;

  public GroupCompanyInfo(String company, String addressCompany) {
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
