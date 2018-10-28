package ru.stqa.pft.addressbook;

public class GroupNumber {
  private final String homeNumber;
  private final String modileNumber;
  private final String workNumber;

  public GroupNumber(String homeNumber, String modileNumber, String workNumber) {
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
