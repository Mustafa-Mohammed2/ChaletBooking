package com.project.chaletbooking.model;
import java.io.Serializable;
import java.util.ArrayList;


public class Chalet implements Serializable  {

    String id_firebase;

    String name;
    String about_chalet;
    String site;
    int  price ;
    float rate;
    String time;
    int image;
    ArrayList<Service_Chalet >list_service_chalets;

//    String date;

    public Chalet() {
    }

//    used to firebase in class MainActivty
    public Chalet(String id_firebase, String name, String about_chalet, String site, int price, float rate, String time) {
        this.id_firebase = id_firebase;
        this.name = name;
        this.about_chalet = about_chalet;
        this.site = site;
        this.price = price;
        this.rate = rate;
        this.time = time;
    }

    public Chalet(String name, String site, int price, float rate, String time, int image) {
        this.name = name;
        this.site = site;
        this.price = price;
        this.rate = rate;
        this.time = time;
        this.image = image;
    }

    public String getId_firebase() {
        return id_firebase;
    }

    public void setId_firebase(String id_firebase) {
        this.id_firebase = id_firebase;
    }

    public String getAbout_chalet() {
        return about_chalet;
    }

    public void setAbout_chalet(String about_chalet) {
        this.about_chalet = about_chalet;
    }

    public ArrayList<Service_Chalet> getList_service_chalets() {
        return list_service_chalets;
    }

    public void setList_service_chalets(ArrayList<Service_Chalet> list_service_chalets) {
        this.list_service_chalets = list_service_chalets;
    }



    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSite() {
        return site;
    }

    public void setSite(String site) {
        this.site = site;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public float getRate() {
        return rate;
    }

    public void setRate(float rate) {
        this.rate = rate;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
