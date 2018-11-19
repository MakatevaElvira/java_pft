package ru.stqa.pft.addressbook.model;

import java.util.Objects;

public class ContactGeneral {
  private final String id;
  private final String name;
  private final String lastname;

  public ContactGeneral(String name, String lastname) {
    this.id = null;
    this.name = name;
    this.lastname = lastname;
  }
  public ContactGeneral(String id,String name, String lastname) {
    this.id = id;
    this.name = name;
    this.lastname = lastname;
  }

  public String getId() {
    return id;
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
    ContactGeneral that = (ContactGeneral) o;
    return Objects.equals(id, that.id) &&
            Objects.equals(name, that.name) &&
            Objects.equals(lastname, that.lastname);
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
