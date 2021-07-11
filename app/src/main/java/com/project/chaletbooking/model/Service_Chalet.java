package com.project.chaletbooking.model;


public class Service_Chalet {
    int image_service;
    String name_service;
    boolean is_found;

    public Service_Chalet(int image_service, String name_service, boolean is_found) {
        this.image_service = image_service;
        this.name_service = name_service;
        this.is_found = is_found;
    }


    public boolean isIs_found() {
        return is_found;
    }

    public void setIs_found(boolean is_found) {
        this.is_found = is_found;
    }

    public int getImage_service() {
        return image_service;
    }

    public void setImage_service(int image_service) {
        this.image_service = image_service;
    }

    public String getName_service() {
        return name_service;
    }

    public void setName_service(String name_service) {
        this.name_service = name_service;
    }


}
