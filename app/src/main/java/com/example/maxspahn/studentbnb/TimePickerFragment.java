package com.example.maxspahn.studentbnb;

import android.app.Activity;
import android.app.Dialog;
import android.app.DialogFragment;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.DatePicker;
import android.widget.EditText;

import java.util.Calendar;

public class TimePickerFragment extends DialogFragment implements DatePickerDialog.OnDateSetListener {

    private String date = null;
    Activity a;
    EditText auxInit;
    EditText auxFin;
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
        date = String.valueOf(day) + "/" + String.valueOf(month) + "/" +String.valueOf(year);
        if(switcher == true){
            auxInit = (EditText) a.findViewById(R.id.et_initdate);
            auxInit.setText(date);
        }else {
            auxInit = (EditText) a.findViewById(R.id.et_initdate);
            String initDate = String.valueOf(auxInit.getText());
            String[] arrayDate = initDate.split("/");
            if(arrayDate.length == 3){
                if(Integer.valueOf(arrayDate[0]) < day || Integer.valueOf(arrayDate[1]) < month || Integer.valueOf(arrayDate[2]) < year){
                    auxFin = (EditText) a.findViewById(R.id.et_findate);
                    auxFin.setText(date);
                }else{
                    final Calendar c = Calendar.getInstance();
                    int new_year = c.get(Calendar.YEAR);
                    int new_month = c.get(Calendar.MONTH);
                    int new_day = c.get(Calendar.DAY_OF_MONTH);

                    // Create a new instance of DatePickerDialog and return it
                    Dialog d = new DatePickerDialog(getActivity(), this, new_year, new_month, new_day);
                    d.show();
                }
            }
        }
    }

    public String getDate(){
        return date;
    }

    public void setActivity(Activity activity, boolean b){
        a = activity;
        switcher = b;
    }
}