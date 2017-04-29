package com.example.maxspahn.studentbnb;

import android.app.Activity;
import android.app.DialogFragment;
import android.os.Bundle;
import android.provider.Settings;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.widget.EditText;

public class SearchRoom extends FragmentActivity {

    EditText initdate;
    EditText findate;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.search_room);

        initdate = (EditText) findViewById(R.id.et_initdate);
        findate = (EditText) findViewById(R.id.et_findate);
    }

    public void showInitDatePickerDialog(View v) {
        TimePickerFragment newFragment = new TimePickerFragment();
        newFragment.show(getFragmentManager(), "timePicker");
        newFragment.setActivity(this, true);
        findate.setEnabled(true);
    }

    public void showFinDatePickerDialog(View v) {
        TimePickerFragment newFragment = new TimePickerFragment();
        newFragment.show(getFragmentManager(), "timePicker");
        newFragment.setActivity(this, false);
    }
}
