package ru.stqa.pft.test.models;

import java.io.File;

public class Product {
    private String name;
    private String photo;
    private String discription;
    private String price;
    private String currency;
    private int value;

    public String getName() {
        return name;
    }

    public Product withName(String name) {
        this.name = name;
        return this;
    }

    public File getPhoto() {
        return new File(photo);
    }

    public Product withPhoto(File photo) {
        this.photo = photo.getPath();
        return this;
    }
    public String getDiscription() {
        return discription;
    }

    public Product withDiscription(String discription) {
        this.discription = discription;
        return this;
    }
    public String getPrice() {
        return price;
    }

    public Product withPrice(String price) {
        this.price = price;
        return this;
    }
    public String getCurrency() {
        return currency;
    }

    public Product withCurrency(String currency) {
        this.currency = currency;
        return this;
    }
    public int getValue(){return value;}
    public Product withValue(int value){
        this.value = value;
        return this;
    }
}
