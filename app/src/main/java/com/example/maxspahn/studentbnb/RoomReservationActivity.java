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

    TextView usernameTextView;
    TextView residenceTextView;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_room_reservation);
        User user = (User) getIntent().getSerializableExtra("user");

        usernameTextView = (TextView) findViewById(R.id.tv_username);
        usernameTextView.setText(user.getName() + " " + user.getSurname());
        usernameTextView = (TextView) findViewById(R.id.tv_residence);
        usernameTextView.setText(user.getResidence().getName());
    }
}
