package com.example.maxspahn.studentbnb;

import android.graphics.Bitmap;

import java.io.Serializable;
import java.text.ParseException;
import 	java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.logging.FileHandler;

/**
 * Created by Pedro León on 29/04/2017.
 */

public class User implements Serializable{
    static final long serialVersionUID = 42L;
    private static int counter = 0;
    private int userID;
    private String name;
    private String surname;
    private String username;
    private String password;
    private String telephone;
    private String email;
    private String roomNumber;
    private ArrayList<com.example.maxspahn.studentbnb.Date> availability = new ArrayList<>();
    private Residence residence;
    //TODO save user pictures information as attribute to be displayed in App Activities
    private ArrayList<Trip> host_trips = new ArrayList<>();
    private ArrayList<Trip> visiting_trips = new ArrayList<>();
    private ArrayList<Evaluation> evaluations = new ArrayList<>();



    public User(){
        name = "default";
    }

    public User(String n, String sn, String un, String p, String t, String e){
        name = n;
        surname = sn;
        username = un;
        password = p;
        telephone = t;
        email = e;
        counter++;
        this.userID = this.counter;
    }

    /*
    to be used in ProfileActivity
     */
    public void registerRoom(String n){
        roomNumber = n;
    }

    /*
    to be used in ProfileActivity
     */
    public void addRoomAvailability(String initDate, String finDate) throws ParseException{
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        Date iDate = sdf.parse(initDate);
        Date fDate = sdf.parse(finDate);
        Calendar start = Calendar.getInstance();
        start.setTime(iDate);
        Calendar end = Calendar.getInstance();
        end.setTime(fDate);
        end.add(Calendar.DATE, 1);
        for (Date date = start.getTime(); date.before(end.getTime()); start.add(Calendar.DATE, 1), date = start.getTime()){
            com.example.maxspahn.studentbnb.Date myDate = new com.example.maxspahn.studentbnb.Date(date.getDay(),date.getMonth(),date.getYear());
            if(!availability.contains(myDate)){
                availability.add(myDate);
            }
        }
    }

    /*
    to be used in RoomReservationActivity
     */
    public boolean isAvailable(String initDate, String finDate) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        Date iDate = sdf.parse(initDate);
        Date fDate = sdf.parse(finDate);
        Calendar start = Calendar.getInstance();
        start.setTime(iDate);
        Calendar end = Calendar.getInstance();
        end.setTime(fDate);
        for (Date date = start.getTime(); date.before(end.getTime()); start.add(Calendar.DATE, 1), date = start.getTime()){
            com.example.maxspahn.studentbnb.Date myDate = new com.example.maxspahn.studentbnb.Date(date.getDay(),date.getMonth(),date.getYear());
            if(!availability.contains(myDate)){
                return false;
            }
        }
        return true;
    }


    /********************************************Getters and setters*************************************************/

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

    public ArrayList<com.example.maxspahn.studentbnb.Date> getAvailability() {
        return availability;
    }

    public void setAvailability(ArrayList<com.example.maxspahn.studentbnb.Date> availability) {
        this.availability = availability;
    }

    public Residence getResidence() {
        return residence;
    }

    public void setResidence(Residence residence) {
        this.residence = residence;
    }

    public ArrayList<Evaluation> getEvaluations() {
        return evaluations;
    }

    public void setEvaluations(ArrayList<Evaluation> evaluations) {
        this.evaluations = evaluations;
    }

    public ArrayList<Trip> getHost_trips() {
        return host_trips;
    }

    public void setHost_trips(ArrayList<Trip> host_trips) {
        this.host_trips = host_trips;
    }

    public void addHost_trip(Trip trip) {
        this.host_trips.add(trip);
    }

    public ArrayList<Trip> getVisiting_trips() {
        return visiting_trips;
    }

    public void setVisiting_trips(ArrayList<Trip> visiting_trips) {
        this.visiting_trips = visiting_trips;
    }

    public void addVisiting_trip(Trip trip) {
        this.visiting_trips.add(trip);
    }

    public int getUserID(){
        return this.userID;
    }
}
