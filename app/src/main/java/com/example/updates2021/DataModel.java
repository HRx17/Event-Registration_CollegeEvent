package com.example.updates2021;

import android.content.SharedPreferences;

public class DataModel {
    private String name;
    private String[] enroll;

    public DataModel() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String[] getEnroll() {
        return enroll;
    }

    public void setEnroll(String[] enroll) {
        this.enroll = enroll;
    }

    public DataModel(String name, String[] enroll) {
        this.name = name;
        this.enroll = enroll;
    }
//private String email;
    //private String phone;

    /*public DataModel(String name, String enroll) {

    }


    public void DataModel(String name, String enroll, String email, String phone) {
        this.name = name;
        this.enroll = enroll;
        //this.email = email;
        //this.phone = phone;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEnroll() {
        return enroll;
    }

    /*ublic void setEnroll(String job) {
        this.enroll = job;
    }
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }*/
}
