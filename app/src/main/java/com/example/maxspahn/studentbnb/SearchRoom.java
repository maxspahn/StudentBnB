package com.example.maxspahn.studentbnb;

import android.content.res.Configuration;
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

public class SearchRoom extends FragmentActivity {

    EditText destinationEditText;
    String destinationS;
    Button searchButton;
    Button initdateButton;
    String initdateS;
    Button findateButton;
    String findateS;
    BottomNavigationView bottomNavigationView;

    private RecyclerView mRecyclerView;
    private RoomAdapter mRoomAdapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.search_room);
        /*
        Reservation related
         */
        destinationEditText = (EditText) findViewById(R.id.et_destination);
        searchButton = (Button) findViewById(R.id.b_search);
        initdateButton = (Button) findViewById(R.id.b_initdate);
        findateButton = (Button) findViewById(R.id.b_findate);

        destinationEditText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(destinationEditText.getText() != null && initdateButton.getText() != "INIT DATE" && findateButton.getText() != "FINAL DATE"){
                    loadRoomData();
                }
            }
        });
        initdateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TimePickerFragment newFragment = new TimePickerFragment();
                newFragment.show(getFragmentManager(), "timePicker");
                newFragment.setActivity(initdateButton,true);
            }
        });

        findateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TimePickerFragment newFragment = new TimePickerFragment();
                newFragment.show(getFragmentManager(), "timePicker");
                newFragment.setActivity(findateButton,false);
            }
        });
        /*
        Room offers related
         */
        mRecyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.setHasFixedSize(true);

        mRoomAdapter = new RoomAdapter();

        mRecyclerView.setAdapter(mRoomAdapter);

        /*
        Bottom menu related
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
        String[] roomData = new String[10];
        roomData[0] = "Centrale";
        roomData[1] = "Madrid";
        roomData[2] = "Cologne";
        roomData[3] = "London";
        roomData[4] = "Rome";
        roomData[5] = "Athens";
        roomData[6] = "Copenhaguen";
        roomData[7] = "Lund";
        mRoomAdapter.setRoomData(roomData);
    }

    private void showRoomDataView(){
        mRecyclerView.setVisibility(View.VISIBLE);
    }
}
