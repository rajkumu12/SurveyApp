package com.survey.surveyapp.Models;


import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Index;
import androidx.room.PrimaryKey;

@Entity(tableName = "user", indices = {@Index(value = "uid", unique = true)})
public class Result_Push {
    @PrimaryKey(autoGenerate = true)
    int _uid;

    @ColumnInfo(name = "uid")
    String uid;


    @ColumnInfo(name = "email")
    String email;

    @ColumnInfo(name = "fullname")
    String fullname;

    @ColumnInfo(name = "mobile")
    String mobile;

    @ColumnInfo(name = "city")
    String city;

    @ColumnInfo(name = "designation")
    String designation;

    @ColumnInfo(name = "about_us")
    String about_us;

    @ColumnInfo(name = "phone")
    String phone;

    @ColumnInfo(name = "gender")
    String gender;


    @ColumnInfo(name = "profile_pic")
    String profile_pic;

    @ColumnInfo(name = "token")
    String token;

    public int get_uid() {
        return _uid;
    }

    public void set_uid(int _uid) {
        this._uid = _uid;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public String getAbout_us() {
        return about_us;
    }

    public void setAbout_us(String about_us) {
        this.about_us = about_us;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getProfile_pic() {
        return profile_pic;
    }

    public void setProfile_pic(String profile_pic) {
        this.profile_pic = profile_pic;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
