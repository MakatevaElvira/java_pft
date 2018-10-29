package ru.stqa.pft.addressbook.model;

public class ContactNumber {
  private final String homeNumber;
  private final String modileNumber;
  private final String workNumber;

  public ContactNumber(String homeNumber, String modileNumber, String workNumber) {
    this.homeNumber = homeNumber;
    this.modileNumber = modileNumber;
    this.workNumber = workNumber;
  }

  public String getHomeNumber() {
    return homeNumber;
  }

  public String getModileNumber() {
    return modileNumber;
  }

  public String getWorkNumber() {
    return workNumber;
  }
}
