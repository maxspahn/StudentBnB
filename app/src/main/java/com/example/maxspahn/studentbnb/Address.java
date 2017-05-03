package com.example.maxspahn.studentbnb;

import android.os.AsyncTask;

import java.net.URL;

/**
 * Created by Pedro León on 29/04/2017.
 */

public class Address {
    private String country;
    private String city;
    private String typeOfStreet;
    private String streetName;
    private String streetNumber;
    private int postalCode;

    public Address(String c,String cit, String type, String name, String number, int code){
        country = c;
        city = cit;
        typeOfStreet = type;
        streetName = name;
        streetNumber = number;
        postalCode = code;
    }
}
