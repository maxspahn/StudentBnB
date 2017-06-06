package com.example.maxspahn.studentbnb;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

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

        Intent i = getIntent();
        user = (User) i.getSerializableExtra("user");

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

}
