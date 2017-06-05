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
    private String name;
    private String surname;
    private String username;
    private String password;
    private String telephone;
    private String email;
    private String roomNumber;
    private ArrayList<Date> availability;
    private Residence residence;
    //TODO save user pictures information as attribute to be displayed in App Activities
    private ArrayList<Trip> trips;
    private ArrayList<Evaluation> evaluations;

    public User(String n, String sn, String un, String p, String t, String e){
        name = n;
        surname = sn;
        username = un;
        password = p;
        telephone = t;
        email = e;
        availability = new ArrayList<>();
        trips = new ArrayList<Trip>();
        evaluations = new ArrayList<Evaluation>();
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
        for (Date date = start.getTime(); date.before(end.getTime()); start.add(Calendar.DATE, 1), date = start.getTime()){
            availability.add(date);
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
            if(!availability.contains(date)){
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

    public ArrayList<Evaluation> getEvaluations() {
        return evaluations;
    }

    public void setEvaluations(ArrayList<Evaluation> evaluations) {
        this.evaluations = evaluations;
    }

    public ArrayList<Trip> getTrips() {
        return trips;
    }

    public void setTrips(ArrayList<Trip> trips) {
        this.trips = trips;
    }

    public void addTrip(Trip trip) {
        this.trips.add(trip);
    }
}
