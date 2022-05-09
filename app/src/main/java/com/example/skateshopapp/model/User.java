package com.example.skateshopapp.model;

public class User {
    private String firstName;
    private String lastName;
    private String streetAddress;
    private String country;
    private String postCode;
    private String email;
    private String password;

    public User() {
    }

    public User(String firstName, String lastName, String streetAddress, String country, String postCode, String email, String password) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.streetAddress = streetAddress;
        this.country = country;
        this.postCode = postCode;
        this.email = email;
        this.password = password;
    }
}
