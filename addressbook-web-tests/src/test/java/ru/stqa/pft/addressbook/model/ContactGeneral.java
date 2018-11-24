package ru.stqa.pft.addressbook.model;

import java.util.Objects;

public class ContactGeneral {
  private int id = Integer.MAX_VALUE;
  private  String name;
  private  String lastname;

  public int getId() {
    return id;
  }

  public ContactGeneral withId(int id) {
    this.id = id;
    return this;
  }

  public ContactGeneral withName(String name) {
    this.name = name;
    return this;
  }

  public ContactGeneral withLastname(String lastname) {
    this.lastname = lastname;
    return this;
  }

  public String getName() {
    return name;
  }

  public String getLastname() {
    return lastname;
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

  @Override
  public String toString() {
    return "ContactGeneral{" +
            "id='" + id + '\'' +
            ", name='" + name + '\'' +
            ", lastname='" + lastname + '\'' +
            '}';
  }

}
