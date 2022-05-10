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

    public String getImageURL() {
        return imageURL;
    }

    public String getSize() {
        return size;
    }

    public String getPrice() {
        return price;
    }
}
