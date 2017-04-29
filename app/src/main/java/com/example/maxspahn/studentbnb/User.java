package com.example.maxspahn.studentbnb;

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
    private Room room;
    private Residence residence;

    public User(String n, String sn, String un, String p, String t, String e){
        name = n;
        surname = sn;
        username = un;
        password = p;
        telephone = t;
        email = e;
    }

    public void registerRoom(int n, boolean b, boolean t,  boolean k, boolean s){
        room = new Room(n,b,t,k,s);
        residence.addRoom(room);
    }

    public void addRoomAvailability(Date initDate, Date finDate){
        for(Date d = initDate; d.before(finDate); d.setDate(d.getDay()+1)){
            room.addDate(d);
        }
    }
}
