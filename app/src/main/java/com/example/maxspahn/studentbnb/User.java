package com.example.maxspahn.studentbnb;

import java.util.ArrayList;
import java.util.Date;

/**
 * Created by Pedro León on 29/04/2017.
 */

public class User {
    private String name;
    private String surname;
    private String username;
    private String password;
    private String telephone;
    private String email;
    private String roomNumber;
    private ArrayList<Date> availability;
    private Residence residence;

    public User(String n, String sn, String un, String p, String t, String e){
        name = n;
        surname = sn;
        username = un;
        password = p;
        telephone = t;
        email = e;
    }

    public void registerRoom(String n){
        roomNumber = n;
        residence.addUser(this);
    }

    public void addRoomAvailability(Date initDate, Date finDate){
        for(Date d = initDate; d.before(finDate); d.setDate(d.getDay()+1)){
            availability.add(d);
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRoomNumber() {
        return roomNumber;
    }

    public void setRoomNumber(String roomNumber) {
        this.roomNumber = roomNumber;
    }

    public ArrayList<Date> getAvailability() {
        return availability;
    }

    public void setAvailability(ArrayList<Date> availability) {
        this.availability = availability;
    }

    public Residence getResidence() {
        return residence;
    }

    public void setResidence(Residence residence) {
        this.residence = residence;
    }
}
