package com.example.maxspahn.studentbnb;

import java.util.HashMap;

/**
 * Created by Pedro León on 29/04/2017.
 */

public class Residence {
    private String name;
    private Address address;
    private boolean parking;
    private HashMap<String,User> listOfUsers;

    public Residence(String residenceName, String c,String cit, String type, String streetName, String number, int code){
        name = residenceName;
        address = new Address(c,cit,type,streetName,number,code);
        listOfUsers = new HashMap<String,User>();
    }

    public void addUser(User u){
        if(listOfUsers.values().contains(u)){
            // display room already added to residence
        }else{
            listOfUsers.put(u.getUsername(),u);
        }
    }
}
