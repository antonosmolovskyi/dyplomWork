package com.example.dyplom5_0;

public class Universities {

    private int id;
    private String city;
    private String name;

    public Universities(int id, String city, String name){
        this.id = id;
        this.name = name;
        this.city = city;
    }

    public int getId() {
        return id;
    }

    public String getCity() {
        return city;
    }

    public String getName() {
        return name;
    }
}
