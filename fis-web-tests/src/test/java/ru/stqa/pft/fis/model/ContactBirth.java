package ru.stqa.pft.fis.model;

public class ContactBirth {
  private  String bday;
  private  String dmonth;
  private  String byear;

  public ContactBirth withBday(String bday) {
    this.bday = bday;
    return this;
  }

  public ContactBirth withDmonth(String dmonth) {
    this.dmonth = dmonth;
    return this;
  }

  public ContactBirth withByear(String byear) {
    this.byear = byear;
    return this;
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
