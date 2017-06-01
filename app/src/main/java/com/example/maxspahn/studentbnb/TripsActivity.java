package com.example.maxspahn.studentbnb;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;

import java.util.ArrayList;

/**
 * Created by Arturo on 31/05/2017.
 */

public class TripsActivity extends Activity {

    User user;
    ListView listView;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_trips);

        Intent i = getIntent();
        user = (User) i.getSerializableExtra("user");

        listView = (ListView) findViewById(R.id.listView);

        ArrayList<Trip> trips = user.getTrips(); // Other way to convert to string?

        ListAdapter tripsAdapter = new CustomAdapter(this, trips);
        listView.setAdapter(tripsAdapter);

    }

}
