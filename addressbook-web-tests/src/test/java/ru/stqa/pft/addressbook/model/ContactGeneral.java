package ru.stqa.pft.addressbook.model;

import java.util.Objects;

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

  @Override
  public String toString() {
    return "ContactGeneral{" +
            "name='" + name + '\'' +
            ", lastname='" + lastname + '\'' +
            '}';
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    ContactGeneral general = (ContactGeneral) o;
    return Objects.equals(name, general.name) &&
            Objects.equals(lastname, general.lastname);
  }

  @Override
  public int hashCode() {
    return Objects.hash(name, lastname);
  }
}
