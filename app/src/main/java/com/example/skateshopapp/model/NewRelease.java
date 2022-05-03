package com.example.skateshopapp.model;

public class NewRelease {

    private String name;
    private String imageURL;
    private String size;
    private String price;

    public NewRelease(String name, String imageURL, String size, String price) {
        this.name = name;
        this.imageURL = imageURL;
        this.size = size;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImageURL() {
        return imageURL;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }
}
