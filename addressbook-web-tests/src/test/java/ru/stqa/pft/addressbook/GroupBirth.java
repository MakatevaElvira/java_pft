package ru.stqa.pft.addressbook;

public class GroupBirth {
  private final String bday;
  private final String dmonth;
  private final String byear;

  public GroupBirth(String bday, String dmonth, String byear) {
    this.bday = bday;
    this.dmonth = dmonth;
    this.byear = byear;
  }

  public String getBday() {
    return bday;
  }

  public String getDmonth() {
    return dmonth;
  }

  public String getByear() {
    return byear;
  }
}
