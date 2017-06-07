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
        getUser("maxspahn");

        System.out.println("New user " + user.getUsername());

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
