package com.example.maxspahn.studentbnb;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;

import java.util.ArrayList;

/**
 * Created by Arturo on 31/05/2017.
 */

public class EvaluationsActivity extends Activity {

    User user;
    ListView listView;
    Button buttonEval;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_evaluations);

        Intent i = getIntent();
        user = (User) i.getSerializableExtra("user");

        listView = (ListView) findViewById(R.id.listView);

        ArrayList<Trip> trips = user.getTrips(); // Other way to convert to string?

        ListAdapter tripsAdapter = new CustomAdapter(this, trips);
        listView.setAdapter(tripsAdapter);

        buttonEval = (Button) findViewById(R.id.buttonEval);
        buttonEval.setBackgroundColor(Color.BLUE);

        buttonEval.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), NewEvalActivity.class);
                intent.putExtra("user", user);
                startActivity(intent);
            }
        });

    }

}
