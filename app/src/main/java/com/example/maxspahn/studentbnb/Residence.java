package com.example.maxspahn.studentbnb;

import com.google.android.gms.vision.barcode.Barcode;

import java.io.Serializable;
import java.util.HashMap;

/**
 * Created by Pedro León on 29/04/2017.
 */

public class Residence implements Serializable{
    private String name;
    private String strAddress;
    private Barcode.GeoPoint p;
    private String city;
    private boolean parking;
    private HashMap<String,User> listOfUsers;

    public Residence(){

    }

    public Residence(String residenceName, String c, String ad){
        name = residenceName;
        city = c;
        strAddress = ad;
        p = null;
        listOfUsers = new HashMap<String,User>();
    }

    public Residence() {

    }

    public void addUser(User u){
        if(listOfUsers.values().contains(u)){
            //TODO display room already added to residence
        }else{
            listOfUsers.put(u.getUsername(),u);
            u.setResidence(this);
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public boolean isParking() {
        return parking;
    }

    public void setParking(boolean parking) {
        this.parking = parking;
    }

    public HashMap<String, User> getListOfUsers() {
        return listOfUsers;
    }

    public void setListOfUsers(HashMap<String, User> listOfUsers) {
        this.listOfUsers = listOfUsers;
    }

    public String getStrAddress() {
        return strAddress;
    }

    public void setStrAddress(String strAddress) {
        this.strAddress = strAddress;
    }

    public Barcode.GeoPoint getP() {
        return p;
    }

    public void setP(Barcode.GeoPoint p) {
        this.p = p;
    }
}
