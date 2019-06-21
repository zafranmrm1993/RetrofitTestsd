package com.example.retrofittestsd.api;

import com.google.gson.annotations.SerializedName;

import org.json.JSONException;
import org.json.JSONObject;

public class SModel {


    @SerializedName("id")
    private int id;
    @SerializedName("name")
    private String name;
    @SerializedName("age")
    private int age;
    @SerializedName("address")
    private String address;
    @SerializedName("path")
    private String path;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public SModel(JSONObject jsonObject) throws JSONException {
        this.id = jsonObject.getInt("id");
        this.name = jsonObject.getString("name");
        this.address = jsonObject.getString("address");
        this.age = jsonObject.getInt("age");
        this.path = jsonObject.getString("path");



    }
}
