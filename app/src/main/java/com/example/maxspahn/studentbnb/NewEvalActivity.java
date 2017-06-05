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

        allTrips = user.getTrips(); // Other way to convert to string?
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
