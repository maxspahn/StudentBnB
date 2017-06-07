package com.example.maxspahn.studentbnb;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.vision.barcode.Barcode;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.io.IOException;
import java.util.List;

/**
 * Created by Pedro León on 03/05/2017.
 */

public class RoomReservationActivity extends FragmentActivity implements OnMapReadyCallback{

    TextView usernameTextView;
    User userBooker;
    User userHoster;
    String initDate;
    String finalDate;
    String[] initiald;
    String[] finald;
    Date dateini;
    Date datefin;

    Button contactButton;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_room_reservation);
        String bookerAndHoster = (String) getIntent().getStringExtra("username");
        String[] s = bookerAndHoster.split("-");
        getUserBooker(s[0]);
        getUserHoster(s[1]);
        initDate = s[2];
        initiald = initDate.split("/");
        dateini = new com.example.maxspahn.studentbnb.Date(Integer.parseInt(initiald[0]), Integer.parseInt(initiald[1]), Integer.parseInt(initiald[2]));
        finalDate = s[3];
        finald = finalDate.split("/");
        datefin = new com.example.maxspahn.studentbnb.Date(Integer.parseInt(finald[0]), Integer.parseInt(finald[1]), Integer.parseInt(finald[2]));

        /*
        ***************************************Contact user related*******************************************************
         */

        contactButton = (Button) findViewById(R.id.b_contact);

        contactButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Trip trip = new Trip(dateini, datefin, userBooker, userHoster);
                userHoster.addHost_trip(trip);
                userBooker.addVisiting_trip(trip);

                ShowMessage("Room reserved");
            }
        });
    }



    @Override
    public void onMapReady(GoogleMap googleMap) {
        double lat = userHoster.getResidence().getP().lat;
        double lng = userHoster.getResidence().getP().lng;
        googleMap.addMarker(new MarkerOptions().position(new LatLng(lat, lng)).title(userHoster.getName()));
        googleMap.setMaxZoomPreference(10.0f);
        googleMap.setMinZoomPreference(10.0f);
        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLngBounds(new LatLng(lat-17, lng-20), new LatLng(lat+17, lng+20)).getCenter(), 10));
    }

    public Barcode.GeoPoint getLocationFromAddress(String strAddress){

        Geocoder coder = new Geocoder(this);
        List<Address> address;
        Barcode.GeoPoint p1 = new Barcode.GeoPoint();;

        /*address = coder.getFromLocationName(strAddress,5);
        if (address==null) {
            return null;
        }
        Address location=address.get(0);
        location.getLatitude();
        location.getLongitude();

        p1.lat =( double) (location.getLatitude());
        System.out.println(p1.lat);
        p1.lng = (double) (location.getLongitude());
        System.out.println(p1.lng);*/
        p1.lat = 48.762892;
        p1.lng = 2.289373;

        return p1;
    }

    public void ShowMessage(String message){
        Context context = this;
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
    }


    public void getUserBooker(String username){
        FirebaseDatabase database = FirebaseDatabase.getInstance();

        //Get the user.
        DatabaseReference ref  = database.getReference(username);

        // Read from the database and check if userName fits to password.
        ref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                userBooker = dataSnapshot.getValue(User.class);
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.w("CREATION", "Failed to read value.", error.toException());
            }
        });

    }

    public void getUserHoster(String username){
        FirebaseDatabase database = FirebaseDatabase.getInstance();

        //Get the user.
        DatabaseReference ref  = database.getReference(username);

        // Read from the database and check if userName fits to password.
        ref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                userHoster = dataSnapshot.getValue(User.class);


                /*
                ***************************************Google Map related*******************************************************
                 */

                Residence res = userHoster.getResidence();
                res.setP(getLocationFromAddress(res.getStrAddress()));
                userHoster.setResidence(res);

                getMap();

                usernameTextView = (TextView) findViewById(R.id.tv_username);
                usernameTextView.setText(userHoster.getName() + " " + userHoster.getSurname());
                usernameTextView = (TextView) findViewById(R.id.tv_residence);
                usernameTextView.setText(userHoster.getResidence().getName());
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.w("CREATION", "Failed to read value.", error.toException());
            }
        });

    }
    public void getMap(){

        MapFragment mapFragment = (MapFragment) getFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

    }
}
