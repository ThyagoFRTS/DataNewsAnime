package com.example.datanewsanime.models;

import com.example.datanewsanime.settings.SettingsDatabase;
import com.google.firebase.database.DatabaseReference;

public class User {
    private String id;
    private String userName;
    private String url_img_profile;
    private String email;
    private String pass;

    public User(String userName, String email, String pass) {
        this.id = "";
        this.userName = userName;
        this.url_img_profile = "";
        this.email = email;
        this.pass = pass;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUrl_img_profile() {
        return url_img_profile;
    }

    public void setUrl_img_profile(String url_img_profile) {
        this.url_img_profile = url_img_profile;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public void saveData() {
        DatabaseReference firebase = SettingsDatabase.getFirebaseDatabase();
        firebase.child("Users").child(this.id).setValue(this);

    }
}
