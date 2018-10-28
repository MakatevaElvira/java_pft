package ru.stqa.pft.addressbook;

public class GroupGeneral {
  private final String name;
  private final String lastname;

  public GroupGeneral(String name, String lastname) {
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
