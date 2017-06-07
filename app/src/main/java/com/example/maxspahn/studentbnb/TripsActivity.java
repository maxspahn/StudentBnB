package com.example.maxspahn.studentbnb;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Created by Arturo on 31/05/2017.
 */

public class TripsActivity extends Activity {

    User user;
    ListView listView;
    Button buttonEval;
    public String username;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_trips);

        username = getIntent().getStringExtra("username");

        getUser(username);

    }

    public void getInfo(User tempuser){

        listView = (ListView) findViewById(R.id.listView);

        ArrayList<Trip> host_trips = tempuser.getHost_trips();
        Collections.reverse(host_trips);
        ArrayList<Trip> visiting_trips = tempuser.getVisiting_trips(); //1 in Paris
        Collections.reverse(visiting_trips);
        ArrayList<Trip> trips = new ArrayList<Trip>();

        int k1=0;
        int k2=0;
        Boolean stop1 = false;
        Boolean stop2 = false;

        // We add the trips in Date order (from most recent to least recent)
        for(int j=0; j<(host_trips.size()+visiting_trips.size());j++) {
            if(host_trips.get(k1).getFinalDate().before(visiting_trips.get(k2).getFinalDate()) && !stop1) {
                visiting_trips.get(k2).setList_tool(false);
                trips.add(visiting_trips.get(k2));
                if(k2 < visiting_trips.size() - 1) {
                    k2++;
                } else {
                    stop1 = true;
                }
            } else {
                host_trips.get(k1).setList_tool(true);
                trips.add(host_trips.get(k1));
                if(k1 < host_trips.size() - 1)
                    k1++;
                else
                    stop2 = true;
            }
            if(stop2 && !stop1){
                while(k2 < visiting_trips.size()){
                    trips.add(visiting_trips.get(k2));
                    k2++;
                }
                j = host_trips.size()+visiting_trips.size();
            }
        }


        ListAdapter tripsAdapter = new CustomAdapter(this, trips);
        listView.setAdapter(tripsAdapter);

        buttonEval = (Button) findViewById(R.id.buttonEval);
        buttonEval.setBackgroundColor(getResources().getColor(R.color.colorSecondaryDark));
        buttonEval.setTextColor(Color.WHITE);

        buttonEval.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), NewEvalActivity.class);
                intent.putExtra("username", username);
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
                getInfo(user);
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.w("CREATION", "Failed to read value.", error.toException());
            }
        });

    }

}
