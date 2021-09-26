package com.example.ticketbooking;

import com.google.firebase.database.Exclude;

import java.io.Serializable;

public class Concert implements Serializable {

    @Exclude
    private String key;
    private String name;
    private String ticketType;
    private String ticketCount;

    public Concert(){}

    public Concert(String name, String ticketType, String ticketCount) {
        this.name = name;
        this.ticketType = ticketType;
        this.ticketCount = ticketCount;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTicketType() {
        return ticketType;
    }

    public void setTicketType(String ticketType) {
        this.ticketType = ticketType;
    }

    public String getTicketCount() {
        return ticketCount;
    }

    public void setTicketCount(String ticketCount) {
        this.ticketCount = ticketCount;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }
}
