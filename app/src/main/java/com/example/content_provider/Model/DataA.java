package com.example.content_provider.Model;

public class DataA {
    private int id ;
    private String name, phone;

    public DataA(String name, String phone) {
        this.name = name;
        this.phone = phone;
    }

    public DataA(int id, String name, String phone) {
        this.id = id;
        this.name = name;
        this.phone = phone;

    }

    public DataA() {

    }

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

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
