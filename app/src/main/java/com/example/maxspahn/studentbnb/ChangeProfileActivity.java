package com.example.maxspahn.studentbnb;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

/**
 * Created by Arturo on 06/06/2017.
 */

public class ChangeProfileActivity extends Activity {

    private User user;
    private EditText editName;
    private EditText editSurname;
    private EditText editUsername;
    private EditText editPassword;
    private EditText editTelephone;
    private EditText editEmail;
    private EditText editRoom;
    private Button buttonImage;
    Toast toast;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_change_profile);

        getUser((String) getIntent().getStringExtra("username"));

        editName = (EditText) findViewById(R.id.editName);
        editSurname = (EditText) findViewById(R.id.editSurname);
        editUsername = (EditText) findViewById(R.id.editUsername);
        editPassword = (EditText) findViewById(R.id.editPassword);
        editTelephone = (EditText) findViewById(R.id.editTelephone);
        editEmail = (EditText) findViewById(R.id.editEmail);
        editRoom = (EditText) findViewById(R.id.editRoom);

        editName.setHint(user.getName());
        editSurname.setHint(user.getSurname());
        editUsername.setHint(user.getUsername());
        editPassword.setHint("*********");
        editTelephone.setHint(user.getTelephone());
        editEmail.setHint(user.getEmail());
        editRoom.setHint(user.getRoomNumber());

        buttonImage = (Button) findViewById(R.id.buttonImage);
        buttonImage.setBackgroundColor(getResources().getColor(R.color.colorPrimaryLight));

        buttonImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

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
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.w("CREATION", "Failed to read value.", error.toException());
            }
        });

    }

}
