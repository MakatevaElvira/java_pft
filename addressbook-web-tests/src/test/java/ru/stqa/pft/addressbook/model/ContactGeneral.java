package ru.stqa.pft.addressbook.model;

public class ContactGeneral {
  private final String name;
  private final String lastname;

  public ContactGeneral(String name, String lastname) {
    this.name = name;
    this.lastname = lastname;
  }

  public String getName() {
    return name;
  }

  public String getLastname() {
    return lastname;
  }
}
