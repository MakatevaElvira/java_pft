package ru.stqa.pft.addressbook.model;

import com.google.gson.annotations.Expose;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamOmitField;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.io.File;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@XStreamAlias("contact")
@Entity
@Table(name = "addressbook")
public class ContactGeneral {
  @XStreamOmitField
  @Id
  private int id = Integer.MAX_VALUE;
  @Expose
  @Column(name = "firstname")
  private String name;
  @Expose
  @Column(name = "lastname")
  private String lastName;
  @Expose
  @Column(name = "home")
  @Type(type = "text")
  private String homeNumber;
  @Expose
  @Column(name = "mobile")
  @Type(type = "text")
  private String mobileNumber;
  @Expose
  @Column(name = "work")
  @Type(type = "text")
  private String workNumber;
  @Expose
  @Transient
  private String allPhones;
  //@Expose
  // @Transient // пропустить, не извлекать из базы
  // private String group; // или в начале строки- transient
  @Expose
  @Column(name = "email")
  @Type(type = "text")
  private String email1;
  @Expose
  @Column(name = "email2")
  @Type(type = "text")
  private String email2;
  @Expose
  @Column(name = "email3")
  @Type(type = "text")
  private String email3;
  @Expose
  @Transient
  private String allEmails;
  @Expose
  @Column(name = "address")
  @Type(type = "text")
  private String address;
  @Expose
  @Column(name = "photo")
  @Type(type = "text")
  private String photo;
  @ManyToMany(fetch = FetchType.EAGER)
  @JoinTable (name = "address_in_groups", joinColumns = @JoinColumn (name = "id"),
          inverseJoinColumns =@JoinColumn(name = "group_id") ) //таблица связей Контакта с Группой, текущий столбец Контакты, обратный столбец Групп

  private Set<GroupData> groups= new HashSet<GroupData>();

  public Groups getGroups() {
    return new Groups(groups);
  }

  public ContactGeneral withPhoto(File photo) {
    this.photo = photo.getPath();
    return this;
  }

  public ContactGeneral withAddress(String address) {
    this.address = address;
    return this;
  }

  public ContactGeneral withAllPhones(String allPhones) {
    this.allPhones = allPhones;
    return this;
  }

  public ContactGeneral withAllEmails(String allEmails) {
    this.allEmails = allEmails;
    return this;
  }

  public ContactGeneral withEmail3(String email3) {
    this.email3 = email3;
    return this;
  }

  public ContactGeneral withEmail2(String email2) {
    this.email2 = email2;
    return this;
  }

  public ContactGeneral withEmail(String email) {
    this.email1 = email;
    return this;
  }

  public ContactGeneral withId(int id) {
    this.id = id;
    return this;
  }

  public ContactGeneral withName(String name) {
    this.name = name;
    return this;
  }

  public ContactGeneral withLastName(String lastname) {
    this.lastName = lastname;
    return this;
  }

  public ContactGeneral withHomeNumber(String homeNumber) {
    this.homeNumber = homeNumber;
    return this;
  }

  public ContactGeneral withMobileNumber(String mobileNumber) {
    this.mobileNumber = mobileNumber;
    return this;
  }

  public ContactGeneral withWorkNumber(String workNumber) {
    this.workNumber = workNumber;
    return this;
  }

  public File getPhoto() {
    return new File(photo);
  }

  public int getId() {
    return id;
  }

  public String getName() {
    return name;
  }

  public String getLastName() {
    return lastName;
  }

  public String getHomeNumber() {
    return homeNumber;
  }

  public String getMobileNumber() {
    return mobileNumber;
  }

  public String getWorkNumber() {
    return workNumber;
  }

  public String getEmail1() {
    return email1;
  }

  public String getEmail2() {
    return email2;
  }

  public String getEmail3() {
    return email3;
  }

  public String getAllPhones() {
    return allPhones;
  }

  public String getAllEmails() {
    return allEmails;
  }

  public String getAddress() {
    return address;
  }

  @Override
  public String toString() {
    return "ContactGeneral{" +
            "id='" + id + '\'' +
            ", name='" + name + '\'' +
            ", lastName='" + lastName + '\'' +
            '}';
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    ContactGeneral general = (ContactGeneral) o;
    return id == general.id &&
            Objects.equals(name, general.name) &&
            Objects.equals(lastName, general.lastName) &&
            Objects.equals(mobileNumber, general.mobileNumber) &&
            Objects.equals(address, general.address);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, name, lastName, mobileNumber, address);
  }

  public ContactGeneral inGroup(GroupData group) {
    groups.add(group);
    return this;
  }
}
