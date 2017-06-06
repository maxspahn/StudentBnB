package com.example.maxspahn.studentbnb;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

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

    private RecyclerView mRecyclerView;
    private AvailabilityAdapter mRoomAdapter; // adapter to fill recycler view with data

    /*
    'activity constructor'
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_avalability);

        Intent i = getIntent();
        user = (User) i.getSerializableExtra("user");
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
                    user.addRoomAvailability(initialDateButton.getText().toString(),finalDateButton.getText().toString());
                    loadAvailabilityData();
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
                    user.getAvailability().remove(toDelete);
                    loadAvailabilityData();
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

        loadAvailabilityData();
    }

    private void loadAvailabilityData() {
        showRoomDataView();

        ArrayList<Date> dataToDisplay = new ArrayList<>();

        for(Date d : user.getAvailability()){
            dataToDisplay.add(d);
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
    }

    public void ShowMessage(String message){
        Context context = this;
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
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