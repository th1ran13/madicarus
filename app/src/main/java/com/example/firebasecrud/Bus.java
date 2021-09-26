package com.example.firebasecrud;

import com.google.firebase.database.Exclude;


public class Bus {


    @Exclude
    private String key;
    private String whereto;
    private String type;
    private String count;
    public Bus (){}

    public Bus(String whereto, String type, String count) {
        this.whereto = whereto;
        this.type = type;
        this.count = count;
    }

    public String getWhereto() {
        return whereto;
    }

    public void setWhereto(String whereto) {
        this.whereto = whereto;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getCount() {
        return count;
    }

    public void setCount(String count) {
        this.count = count;
    }
    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }
}
