package com.example.maxspahn.studentbnb;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;

/**
 * Created by Pedro León on 29/04/2017.
 */

public class Room {
    private int number;
    private boolean bed;
    private boolean toilet;
    private boolean kitchen;
    private boolean shower;
    private boolean parking;
    private Date registerDate;
    private LinkedList<Date> availability;

    public Room(int n, boolean b, boolean t,  boolean k, boolean s){
        number = n;
        bed = b;
        toilet = t;
        kitchen = k;
        shower = s;
        Calendar c = Calendar.getInstance();
        registerDate = new Date(c.getTime().getYear(),c.getTime().getMonth(),c.getTime().getDay());
        availability = new LinkedList<Date>();
    }

    public void addDate(Date date){
        availability.add(date);
    }
    public void removeDate(Date date){
        availability.remove(date);
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public boolean isBed() {
        return bed;
    }

    public void setBed(boolean bed) {
        this.bed = bed;
    }

    public boolean isToilet() {
        return toilet;
    }

    public void setToilet(boolean toilet) {
        this.toilet = toilet;
    }

    public boolean isKitchen() {
        return kitchen;
    }

    public void setKitchen(boolean kitchen) {
        this.kitchen = kitchen;
    }

    public boolean isShower() {
        return shower;
    }

    public void setShower(boolean shower) {
        this.shower = shower;
    }

    public boolean isParking() {
        return parking;
    }

    public void setParking(boolean parking) {
        this.parking = parking;
    }
}
