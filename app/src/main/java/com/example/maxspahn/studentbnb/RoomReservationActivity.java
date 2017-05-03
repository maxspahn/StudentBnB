package com.example.maxspahn.studentbnb;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.widget.TextView;

/**
 * Created by Pedro León on 03/05/2017.
 */

public class RoomReservationActivity extends Activity {

    TextView roomDataTextView;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState, @Nullable PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
        setContentView(R.layout.activity_room_reservation);

        Intent i = getIntent();
        String roomData = i.getStringExtra("roomData");
        System.out.println(roomData);

        roomDataTextView = (TextView) findViewById(R.id.tv_room);
        roomDataTextView.setText(roomData);
    }
}
