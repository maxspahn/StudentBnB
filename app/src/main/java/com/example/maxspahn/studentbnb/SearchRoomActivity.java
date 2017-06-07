package com.example.maxspahn.studentbnb;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.example.maxspahn.studentbnb.RoomAdapter.RoomAdapterOnClickHandler;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/*
secondary activity accessed after log in activity
composed by 3 main parts
- reservation details (input data)
- room offers (output data)
- other activities (to travel through the application)
 */
public class SearchRoomActivity extends FragmentActivity implements RoomAdapterOnClickHandler,TimePickerFragment.DateButtonListener {

    private EditText destinationEditText;
    private Button searchButton;
    protected Button initialDateButton;
    protected Button finalDateButton;
    public User user;
    public String username;

    private RecyclerView mRecyclerView;
    private RoomAdapter mRoomAdapter; // adapter to fill recycler view with data

    private BottomNavigationView bottomNavigationView;
    /*
    'activity constructor'
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_room);
        /*
        ***************************************Reservation related*******************************************************
         */
        destinationEditText = (EditText) findViewById(R.id.et_destination);
        searchButton = (Button) findViewById(R.id.b_search);
        initialDateButton = (Button) findViewById(R.id.b_initdate);
        finalDateButton = (Button) findViewById(R.id.b_findate);

        username = getIntent().getStringExtra("username");
        getUser((String) getIntent().getStringExtra("username"));

        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(destinationEditText.getText().toString().equals("") || initialDateButton.getText().toString().toUpperCase().equals("INIT DATE") || finalDateButton.getText().toString().toUpperCase().equals("FINAL DATE")){
                    ShowMessage("Fill all fields");
                } else{
                    loadRoomData();
                }
            }
        });
        initialDateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TimePickerFragment newFragment = new TimePickerFragment();
                newFragment.show(getFragmentManager(), "timePicker");
                newFragment.setActivity(true, finalDateButton.getText().toString());
            }
        });

        finalDateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TimePickerFragment newFragment = new TimePickerFragment();
                newFragment.show(getFragmentManager(), "timePicker");
                newFragment.setActivity(false, initialDateButton.getText().toString());
            }
        });
        /*
        ***************************************Room offers related*******************************************************
         */
        mRecyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.setHasFixedSize(true);

        mRoomAdapter = new RoomAdapter(this);

        mRecyclerView.setAdapter(mRoomAdapter);
        /*
        ***************************************Bottom menu related*******************************************************
         */
        bottomNavigationView = (BottomNavigationView) findViewById(R.id.bottom_navigation);

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.action_search:
                        // call SearchRoom activity
                        break;
                    case R.id.action_trip:
                        // call Trip activity
                        break;
                    case R.id.action_profile:
                        launchProfileActivity();
                        break;
                }
                return true;
            }
        });
    }

    private void loadRoomData() {
        showRoomDataView();
        ArrayList<User> userData = new ArrayList<>();
        User newUser1 = new User("Pedro", "Leon", "pleonpita", "pedron", "06959599143", "pleonpita@gmail.com");
        User newUser2 = new User("Arturo", "Garrido", "pleonpita", "pedron", "06959599143", "pleonpita@gmail.com");
        User newUser3 = new User("Max", "Spahn", "pleonpita", "pedron", "06959599143", "pleonpita@gmail.com");
        User newUser4 = new User("Pedro", "Leon", "pleonpita", "pedron", "06959599143", "pleonpita@gmail.com");
        User newUser5 = new User("Pedro", "Leon", "pleonpita", "pedron", "06959599143", "pleonpita@gmail.com");
        User newUser6 = new User("Pedro", "Leon", "pleonpita", "pedron", "06959599143", "pleonpita@gmail.com");
        User newUser7 = new User("Pedro", "Leon", "pleonpita", "pedron", "06959599143", "pleonpita@gmail.com");
        User newUser8 = new User("Pedro", "Leon", "pleonpita", "pedron", "06959599143", "pleonpita@gmail.com");
        User newUser9 = new User("Pedro", "Leon", "pleonpita", "pedron", "06959599143", "pleonpita@gmail.com");
        User newUser10 = new User("Pedro", "Leon", "pleonpita", "pedron", "06959599143", "pleonpita@gmail.com");
        User newUser11 = new User("Pedro", "Leon", "pleonpita", "pedron", "06959599143", "pleonpita@gmail.com");
        User newUser12 = new User("Pedro", "Leon", "pleonpita", "pedron", "06959599143", "pleonpita@gmail.com");
        try{
            newUser1.addRoomAvailability("01/01/2017","31/12/2017");
            newUser2.addRoomAvailability("01/01/2017","31/12/2017");
            newUser3.addRoomAvailability("01/01/2017","31/12/2017");
            newUser4.addRoomAvailability("01/01/2017","31/12/2017");
            newUser5.addRoomAvailability("01/01/2017","31/12/2017");
            newUser6.addRoomAvailability("01/01/2017","31/12/2017");
            newUser7.addRoomAvailability("01/01/2017","31/12/2017");
            newUser8.addRoomAvailability("01/01/2017","31/12/2017");
            newUser9.addRoomAvailability("01/01/2017","31/12/2017");
            newUser10.addRoomAvailability("01/01/2017","31/12/2017");
            newUser11.addRoomAvailability("01/01/2017","31/12/2017");
            newUser12.addRoomAvailability("01/01/2017","31/12/2017");
        }catch(ParseException e){
            System.out.println(e.getMessage());
        }
        newUser1.registerRoom("E201");
        newUser2.registerRoom("E202");
        newUser3.registerRoom("E203");
        newUser4.registerRoom("E204");
        newUser5.registerRoom("E205");
        newUser6.registerRoom("E206");
        newUser7.registerRoom("E207");
        newUser8.registerRoom("E208");
        newUser9.registerRoom("E209");
        newUser10.registerRoom("E210");
        newUser11.registerRoom("E211");
        newUser12.registerRoom("E212");
        Residence newResidence = new Residence("ECP", "Paris", "5 Avenue Sully Prudhomme, 92290 Châtenay-Malabry");
        newUser1.setResidence(newResidence);
        newUser2.setResidence(newResidence);
        newUser3.setResidence(newResidence);
        newUser4.setResidence(newResidence);
        newUser5.setResidence(newResidence);
        newUser6.setResidence(newResidence);
        newUser7.setResidence(newResidence);
        newUser8.setResidence(newResidence);
        newUser9.setResidence(newResidence);
        newUser10.setResidence(newResidence);
        newUser11.setResidence(newResidence);
        newUser12.setResidence(newResidence);
        userData.add(newUser1);
        userData.add(newUser2);
        userData.add(newUser3);
        userData.add(newUser4);
        userData.add(newUser5);
        userData.add(newUser6);
        userData.add(newUser7);
        userData.add(newUser8);
        userData.add(newUser9);
        userData.add(newUser10);
        userData.add(newUser11);
        userData.add(newUser12);
        ArrayList<User> dataToDisplay = new ArrayList<>();

        for(User u : userData){
            if(u.getResidence().getCity().toLowerCase().equals(destinationEditText.getText().toString().toLowerCase())){
                try{
                    if(u.isAvailable(initialDateButton.getText().toString(), finalDateButton.getText().toString())){
                        dataToDisplay.add(u);
                    }
                }catch (ParseException e){
                    System.out.println(e.getMessage());
                }
            }

        }

        if(dataToDisplay.size() == 0){
            ShowMessage("No room available.");
        }else{
            mRoomAdapter.setRoomData(dataToDisplay);
        }
    }

    private void showRoomDataView(){
        mRecyclerView.setVisibility(View.VISIBLE);
    }


    @Override
    public void onClick(User u) {
        Intent intent = new Intent(this, RoomReservationActivity.class);
        intent.putExtra("user", (Serializable) u);
        startActivity(intent);

    }


    private void testing (User user){
        Log.d("CREATION", "username : " + user.getEmail());

    }


    public void ShowMessage(String message){
        Context context = this;
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
    }

    private void launchProfileActivity(){
        Intent intent = new Intent(this, ProfileActivity.class);
        intent.putExtra("username", username);
        startActivity(intent);
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
                testing(user);
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.w("CREATION", "Failed to read value.", error.toException());
            }
        });

    }

    @Override
    public void setInitDateButtonText(String date) {
        initialDateButton.setText(date);
    }

    @Override
    public void setFinDateButtonText(String date) {
        finalDateButton.setText(date);
    }
}
