package ru.stqa.pft.addressbook.model;

import java.util.Objects;

public class ContactGeneral {
  private int id;
  private final String name;
  private final String lastname;

  public ContactGeneral(String name, String lastname) {
    this.id = 0;
    this.name = name;
    this.lastname = lastname;
  }
  public ContactGeneral(int id,String name, String lastname) {
    this.id = id;
    this.name = name;
    this.lastname = lastname;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
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
    return id == general.id &&
            Objects.equals(name, general.name) &&
            Objects.equals(lastname, general.lastname);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, name, lastname);
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
