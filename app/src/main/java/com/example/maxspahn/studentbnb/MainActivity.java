package com.example.maxspahn.studentbnb;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity {

    public Button loginButton;
    public Button registerButton;
    public EditText username;
    public EditText password;
    public static User user;
    String userNAME;
    DatabaseReference ref;

    ValueEventListener listenerFire;
    Toast toast;
    Toast toast2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        username = (EditText) findViewById(R.id.usernameL);
        password = (EditText) findViewById(R.id.passwordL);

        loginButton = (Button) findViewById(R.id.login);
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId()) {
                    case R.id.login:
                        login();
                        break;

                }
            }
        });


        registerButton = (Button) findViewById(R.id.register);
        registerButton.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                switch (v.getId()) {
                    case R.id.register:
                        register();
                        break;
                }
            }
        });

        listenerFire = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                User user = dataSnapshot.getValue(User.class);
                if (user == null) {
                    toast = Toast.makeText(getApplicationContext(), "This user does not exist!", Toast.LENGTH_SHORT);
                    toast.show();
                } else {
                    String passWORD = password.getText().toString();

                    // if password is correct the new activity is started.
                    if (user.getPassword().equals(passWORD)) {
                        launch(userNAME);
                        testing(user);
                    } else {
                        toast2 = Toast.makeText(getApplicationContext(), "The password is not correct", Toast.LENGTH_SHORT);
                        toast2.show();
                    }
                }
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.w("CREATION", "Failed to read value.", error.toException());
            }
        };
    }

    private void login(){


        FirebaseDatabase database = FirebaseDatabase.getInstance();

        //Get the user name from EditText.
        userNAME = username.getText().toString();
        ref  = database.getReference(userNAME);
        ref.addValueEventListener(listenerFire);

    }


    private void testing (User user){
        Log.d("CREATION", "username in main : " + user.getEmail());
    }



    /*
    Activity that starts the app after a successful login.
     */
    private void launch(String username){
        Intent intent = new Intent(this, SearchRoomActivity.class);
        intent.putExtra("username", username);
        ref.removeEventListener(listenerFire);
        startActivity(intent);
    }

    /*
    Activity to register a new user is started.
     */
    private void register(){
        Intent intent = new Intent(this, RegisterActivity.class);
        startActivity(intent);
    }
}
