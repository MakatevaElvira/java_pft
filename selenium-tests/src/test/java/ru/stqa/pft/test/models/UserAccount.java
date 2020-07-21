package ru.stqa.pft.test.models;

public class UserAccount {
    private String name;
    private String lastName;
    private String address1;
    private String postcode;
    private String city;
    private String country;
    private String state;
    private String email;
    private String phone;
    private String password;

    public String getName() {
        return name;
    }

    public UserAccount withName(String name) {
        this.name = name;
        return this;
    }

    public String getLastName() {
        return lastName;
    }

    public UserAccount withLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public String getAddress1() {
        return address1;
    }

    public UserAccount withAddress1(String address1) {
        this.address1 = address1;
        return this;
    }

    public String getPostcode() {
        return postcode;
    }

    public UserAccount withPostcode(String postcode) {
        this.postcode = postcode;
        return this;
    }

    public String getCity() {
        return city;
    }

    public UserAccount withCity(String city) {
        this.city = city;
        return this;
    }

    public String getCountry() {
        return country;
    }

    public UserAccount withCountry(String country) {
        this.country = country;
        return this;
    }

    public String getState() {
        return state;
    }

    public UserAccount withState(String state) {
        this.state = state;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public UserAccount withEmail(String email) {
        this.email = email;
        return this;
    }

    public String getPhone() {
        return phone;
    }

    public UserAccount withPhone(String phone) {
        this.phone = phone;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public UserAccount withPassword(String password) {
        this.password = password;
        return this;
    }
}
