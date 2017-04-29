package com.example.maxspahn.studentbnb;

import android.app.Activity;
import android.app.Dialog;
import android.app.DialogFragment;
import android.app.DatePickerDialog;
import android.app.FragmentManager;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;

import java.util.Calendar;
import java.util.Date;

public class TimePickerFragment extends DialogFragment implements DatePickerDialog.OnDateSetListener {

    private String date = null;
    View a;
    private static Date initDate = null;
    private static Date finalDate = null;
    private static Button initButton = null;
    private static Button finButton = null;
    boolean switcher;


    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // Use the current date as the default date in the picker
        final Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        int day = c.get(Calendar.DAY_OF_MONTH);

        // Create a new instance of DatePickerDialog and return it
        return new DatePickerDialog(getActivity(), this, year, month, day);
    }

    public void onDateSet(DatePicker view, int year, int month, int day) {
        date = day + "/" + month + "/" + year;
        if(switcher == true){
            initButton = (Button) a.findViewById(R.id.b_initdate);
            initDate = new Date(year,month,day);
            System.out.println(finalDate);
            if(finalDate != null){
                String finalString = String.valueOf(finButton.getText());
                String[] arrayDate = finalString.split("/");
                finalDate = new Date(Integer.valueOf(arrayDate[2]),Integer.valueOf(arrayDate[1]),Integer.valueOf(arrayDate[0]));
                if(initDate.before(finalDate)){
                    initButton.setText(date);
                }else{
                    initButton.setText(finButton.getText());
                    finButton.setText(date);
                }
            }else {
                initButton.setText(date);
            }
        }else {
            finButton = (Button) a.findViewById(R.id.b_findate);
            finalDate = new Date(year,month,day);
            if(initDate != null){
                String initString = String.valueOf(initButton.getText());
                String[] arrayDate = initString.split("/");
                initDate = new Date(Integer.valueOf(arrayDate[2]),Integer.valueOf(arrayDate[1]),Integer.valueOf(arrayDate[0]));
                if(finalDate.after(initDate)){
                    finButton.setText(date);
                }else{
                    finButton.setText(initButton.getText());
                    initButton.setText(date);
                }
            }else{
                finButton.setText(date);
            }
        }
    }

    public String getDate(){
        return date;
    }

    public void setActivity(View v, boolean b){
        a = v;
        switcher = b;
    }
}