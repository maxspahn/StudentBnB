package com.example.maxspahn.studentbnb;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;

/**
 * Created by Arturo on 02/06/2017.
 */

public class NewEvalActivity extends Activity {

    ListView listView;
    User user;
    ArrayList<Trip> allTrips;
    ArrayList<Trip> nonEvalTrips;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_new_eval);

        getUser((String) getIntent().getStringExtra("username"));

        listView = (ListView) findViewById(R.id.listView);

        ArrayList<Trip> host_trips = user.getHost_trips();
        Collections.reverse(host_trips);
        ArrayList<Trip> visiting_trips = user.getVisiting_trips();
        Collections.reverse(visiting_trips);
        allTrips = new ArrayList<Trip>();

        int k1=0;
        int k2=0;
        Boolean stop1 = false;
        Boolean stop2 = false;

        // We add the trips in Date order (from most recent to least recent)
        for(int j=0; j<(host_trips.size()+visiting_trips.size());j++) {
            if(host_trips.get(k1).getFinalDate().before(visiting_trips.get(k2).getFinalDate()) && !stop1) {
                visiting_trips.get(k2).setList_tool(false);
                allTrips.add(visiting_trips.get(k2));
                if(k2 < visiting_trips.size() - 1) {
                    k2++;
                } else {
                    stop1 = true;
                }
            } else {
                host_trips.get(k1).setList_tool(true);
                allTrips.add(host_trips.get(k1));
                if(k1 < host_trips.size() - 1)
                    k1++;
                else
                    stop2 = true;
            }
            if(stop2 && !stop1){
                while(k2 < visiting_trips.size()){
                    allTrips.add(visiting_trips.get(k2));
                    k2++;
                }
                j = host_trips.size()+visiting_trips.size();
            }
        }

        nonEvalTrips = new ArrayList<Trip>();

        for(Trip trip:allTrips) {
            if(trip.getEvaluation() == null)
                nonEvalTrips.add(trip);
        }

        ListAdapter newEvalAdapter = new CustomAdapterNewEval(this, nonEvalTrips);
        listView.setAdapter(newEvalAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Trip chosenTrip = (Trip) listView.getItemAtPosition(i);

                Intent intent = new Intent(getApplicationContext(), EvaluateActivity.class);
                intent.putExtra("trip", (Serializable) chosenTrip);
                startActivity(intent);
            }
        });

    }

    public void getUser(String username){
        FirebaseDatabase database = FirebaseDatabase.getInstance();

        //Get the user.
        DatabaseReference ref  = database.getReference(username);

        // Read from the database and check if userName fits to password.
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                user = dataSnapshot.getValue(User.class);
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.w("CREATION", "Failed to read value.", error.toException());
            }
        });

    }

}
