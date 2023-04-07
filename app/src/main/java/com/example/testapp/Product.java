package com.example.testapp;


public class Product {
    private String Name;
    private Double Price;
    private String Image;
    private String Tag;

    public Product(){}
    public Product(String name, Double price, String image, String tag) {
        this.Name = name;
        this.Price = price;
        this.Image = image;
        this.Tag = tag;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        this.Name = name;
    }

    public Double getPrice() {
        return Price;
    }

    public void setPrice(Double price) {
        this.Price = price;
    }

    public String getImage() {
        return Image;
    }

    public void setImage(String image) {
        this.Image = image;
    }

    public String getTag() {
        return Tag;
    }

    public void setTag(String tag) {
        this.Tag = tag;
    }
}
