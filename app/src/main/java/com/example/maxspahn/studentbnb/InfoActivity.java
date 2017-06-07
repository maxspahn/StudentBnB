package com.example.maxspahn.studentbnb;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
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

/**
 * Created by Arturo on 31/05/2017.
 */

public class InfoActivity extends AppCompatActivity {
    User user;
    ListView listView;
    Button buttonChange;
    String username;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);



        setContentView(R.layout.activity_info);

        username = getIntent().getStringExtra("username");

        getUser(username);

    }

    public void getInfo(User tempuser){

        listView = (ListView) findViewById(R.id.listView);

        String[] infoElements = {"Name: " + tempuser.getName() + tempuser.getSurname(), "Username: " + tempuser.getUsername(), "Telephone: " + tempuser.getTelephone(), "Email: " + tempuser.getEmail(), "City: " + tempuser.getResidence().getCity(), "Residence: " + tempuser.getResidence().getName(), "Room: " + tempuser.getRoomNumber()};

        ListAdapter infoAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, infoElements);
        listView.setAdapter(infoAdapter);

        buttonChange = (Button) findViewById(R.id.buttonChange);
        buttonChange.setBackgroundColor(getResources().getColor(R.color.colorSecondaryDark));
        buttonChange.setTextColor(Color.WHITE);

        buttonChange.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), ChangeProfileActivity.class);
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
