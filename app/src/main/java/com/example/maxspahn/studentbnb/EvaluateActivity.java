package com.example.maxspahn.studentbnb;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

/**
 * Created by Arturo on 02/06/2017.
 */

public class EvaluateActivity extends Activity {

    Trip trip;
    TextView textTitle;
    ListView listEval;
    Toast toast;
    User user;
    private StorageReference mStorageRef;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_evaluate);

        Intent i = getIntent();
        trip = (Trip) i.getSerializableExtra("trip");

        if(trip.isList_tool())
            getUser(trip.getHostUser().getUsername());
        else
            getUser(trip.getVisitingUser().getUsername());

    }

    public void getInfo(final User tempuser){

        textTitle = (TextView) findViewById(R.id.textTitle);
        textTitle.setText("Evaluate your trip to " + trip.getHostUser().getResidence().getCity() + "!");

        listEval = (ListView) findViewById(R.id.listEval);

        String[] evalElements = {Evaluation.BAD.toString(), Evaluation.NORMAL.toString(), Evaluation.GOOD.toString(), Evaluation.EXCELLENT.toString()};

        ListAdapter profileAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, evalElements);
        listEval.setAdapter(profileAdapter);
        listEval.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String option = ((TextView) view).getText().toString();

                switch (option) {
                    case "BAD":
                        trip.setEvaluation(Evaluation.BAD);
                        toast = Toast.makeText(getApplicationContext(), "Thank you for evalutaing!", Toast.LENGTH_SHORT);
                        toast.show();
                        break;

                    case "NORMAL":
                        trip.setEvaluation(Evaluation.NORMAL);
                        toast = Toast.makeText(getApplicationContext(), "Thank you for evalutaing!", Toast.LENGTH_SHORT);
                        toast.show();
                        break;

                    case "GOOD":
                        trip.setEvaluation(Evaluation.GOOD);
                        toast = Toast.makeText(getApplicationContext(), "Thank you for evalutaing!", Toast.LENGTH_SHORT);
                        toast.show();
                        break;

                    case "EXCELLENT":
                        trip.setEvaluation(Evaluation.EXCELLENT);
                        toast = Toast.makeText(getApplicationContext(), "Thank you for evalutaing!", Toast.LENGTH_SHORT);
                        toast.show();
                        break;
                }

                FirebaseDatabase database = FirebaseDatabase.getInstance();
                mStorageRef = FirebaseStorage.getInstance().getReference();
                DatabaseReference ref = database.getReference(tempuser.getUsername());
                ref.setValue(tempuser);

                toast = Toast.makeText(getApplicationContext(), "Information Saved", Toast.LENGTH_SHORT);
                toast.show();
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
