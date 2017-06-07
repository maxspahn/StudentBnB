package com.example.maxspahn.studentbnb;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;

/*

 */
public class AvailabilityActivity extends FragmentActivity implements AvailabilityAdapter.AvailabilityAdapterOnClickHandler,TimePickerFragment.DateButtonListener {

    User user;
    Date toDelete;

    protected Button addButton;
    protected Button removeButton;
    protected Button initialDateButton;
    protected Button finalDateButton;


    private StorageReference mStorageRef;

    public String username;

    private RecyclerView mRecyclerView;
    private AvailabilityAdapter mRoomAdapter; // adapter to fill recycler view with data

    /*
    'activity constructor'
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_avalability);

        username = getIntent().getStringExtra("username");

        getUser(username);


    }

    public void getInfo(final User tempuser){

        toDelete = null;

        /*
        ***************************************Date interval related*******************************************************
         */
        addButton = (Button) findViewById(R.id.b_add);
        removeButton = (Button) findViewById(R.id.b_remove);
        initialDateButton = (Button) findViewById(R.id.b_initdate);
        finalDateButton = (Button) findViewById(R.id.b_findate);

        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try{
                    tempuser.addRoomAvailability(initialDateButton.getText().toString(),finalDateButton.getText().toString());

                    FirebaseDatabase database = FirebaseDatabase.getInstance();
                    mStorageRef = FirebaseStorage.getInstance().getReference();
                    DatabaseReference ref = database.getReference(tempuser.getUsername());
                    ref.setValue(tempuser);

                    loadAvailabilityData(tempuser);
                }catch(ParseException e){
                    System.out.println(e.getMessage());
                }
            }
        });

        removeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(toDelete == null){
                    ShowMessage("Click on availabilities");
                }else{
                    tempuser.getAvailability().remove(toDelete);

                    FirebaseDatabase database = FirebaseDatabase.getInstance();
                    mStorageRef = FirebaseStorage.getInstance().getReference();
                    DatabaseReference ref = database.getReference(username);
                    ref.setValue(tempuser);

                    loadAvailabilityData(tempuser);
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
        ***************************************Room availabilities related*******************************************************
         */
        mRecyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.setHasFixedSize(true);

        mRoomAdapter = new AvailabilityAdapter(this);

        mRecyclerView.setAdapter(mRoomAdapter);

        loadAvailabilityData(tempuser);
    }

    private void loadAvailabilityData(User tempuser) {
        showRoomDataView();



        ArrayList<Date> dataToDisplay = new ArrayList<>();

        for(Date d : tempuser.getAvailability()){
            dataToDisplay.add(d);
            Log.d("CREATION", "in load Availability");
        }

        if(dataToDisplay.size() == 0){
            ShowMessage("No room availabilities.");
        }else{
            mRoomAdapter.setRoomData(dataToDisplay);
        }
    }

    private void showRoomDataView(){
        mRecyclerView.setVisibility(View.VISIBLE);
    }


    @Override
    public void onClick(Date d) {
        toDelete = d;
        ShowMessage(d.toString());
    }

    public void ShowMessage(String message){
        Context context = this;
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
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

    @Override
    public void setInitDateButtonText(String date) {
        initialDateButton.setText(date);
    }

    @Override
    public void setFinDateButtonText(String date) {
        finalDateButton.setText(date);
    }

}