package com.example.myapp;

public class Contact {
    int image;
    String name;
    String mobile;
    String photoUri;

    public Contact(int image, String name, String mobile, String photoUri){
        this.image = image;
        this.name =name;
        this.mobile =mobile;
        this.photoUri = photoUri;

    }

    public Contact(int image, String name, String mobile){
        this.image = image;
        this.name =name;
        this.mobile =mobile;


    }
    public int getImage(){
        return image;
    }

    public String getName(){
        return name;
    }
    public String getMobile(){
        return mobile;
    }
    public String getPhotoUri()   { return photoUri; }

}
