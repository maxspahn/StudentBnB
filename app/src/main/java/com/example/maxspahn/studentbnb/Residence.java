package com.example.maxspahn.studentbnb;

import java.util.HashMap;

/**
 * Created by Pedro León on 29/04/2017.
 */

public class Residence {
    private String name;
    private Address address;
    private boolean parking;
    private HashMap<Integer,Room> listOfRooms;

    public Residence(String residenceName, String c,String cit, String type, String streetName, String number, int code){
        name = residenceName;
        address = new Address(c,cit,type,streetName,number,code);
        listOfRooms = new HashMap<Integer,Room>();
    }

    public void addRoom(Room r){
        if(listOfRooms.values().contains(r)){
            // display room already added to residence
        }else{
            listOfRooms.put(r.getNumber(),r);
        }
    }
}
