package com.project.chaletbooking.model;

public class User {

    String id_firebase;
    String name ;
    String phone ;

    String email;
    String password;

    public User() {
    }

    public User(String id_firebase, String name, String phone, String email, String password) {
        this.id_firebase = id_firebase;
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.password = password;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getId_firebase() {
        return id_firebase;
    }

    public void setId_firebase(String id_firebase) {
        this.id_firebase = id_firebase;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
