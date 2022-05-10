package com.example.skateshopapp.model;

public class Item {

    private String name;
    private String imageURL;
    private String size;
    private String price;

    public Item(String name, String imageURL, String size, String price) {
        this.name = name;
        this.imageURL = imageURL;
        this.size = size;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public String getImageURL() {
        return imageURL;
    }

    public String getSize() {
        return size;
    }

    public String getPrice() {
        return price;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public void setPrice(String price) {
        this.price = price;
    }
}
