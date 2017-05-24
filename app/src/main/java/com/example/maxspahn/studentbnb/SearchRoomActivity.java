package com.example.maxspahn.studentbnb;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.example.maxspahn.studentbnb.RoomAdapter.RoomAdapterOnClickHandler;

/*
secondary activity accessed after log in activity
composed by 3 main parts
- reservation details (input data)
- room offers (output data)
- other activities (to travel through the appication)
 */
public class SearchRoomActivity extends FragmentActivity implements RoomAdapterOnClickHandler,TimePickerFragment.DateButtonListener {

    private EditText destinationEditText;
    private Button searchButton;
    protected Button initialDateButton;
    protected Button finalDateButton;

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
                    case R.id.action_message:
                        // call Message activity
                    case R.id.action_trip:
                        // call Trip activity
                    case R.id.action_profile:
                        // call Profile activity
                }
                return true;
            }
        });
    }

    private void loadRoomData(){
        showRoomDataView();
        String[] roomData = new String[] {"Centrale","Madrid","Cologne","London","Rome","Paris","Copenhaguen","Lund","Amsterdam", "Bruxelles", "Munich", "Barcelone"};
        int newLength = 0;
        /*
         check destination
          */
        for (String aRoomData : roomData) {
            if (aRoomData.toLowerCase().equals(destinationEditText.getText().toString().toLowerCase())) {
                newLength++;
            }
        }
        String[] dataToDisplay = new String[newLength];
        int count = 0;
        for (String aRoomData : roomData) {
            if (aRoomData.toLowerCase().equals(destinationEditText.getText().toString().toLowerCase())) {
                dataToDisplay[count] = aRoomData;
                count++;
            }
        }
        /*
        check dates availability
         */
        //TODO 1 Check dates availability
        /*
        if no room found, show toast
         */
        if(dataToDisplay.length == 0){
            ShowMessage("No room available.");
        }
        mRoomAdapter.setRoomData(dataToDisplay);
    }

    private void showRoomDataView(){
        mRecyclerView.setVisibility(View.VISIBLE);
    }


    @Override
    public void onClick(String roomData) {
        Intent intent = new Intent(this, RoomReservationActivity.class);
        intent.putExtra("roomData", roomData);
        startActivity(intent);

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
