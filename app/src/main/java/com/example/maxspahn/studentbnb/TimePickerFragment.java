package com.example.maxspahn.studentbnb;

import android.app.Dialog;
import android.app.DialogFragment;
import android.app.DatePickerDialog;
import android.content.Context;
import android.os.Bundle;
import android.widget.DatePicker;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class TimePickerFragment extends DialogFragment implements DatePickerDialog.OnDateSetListener {

    boolean switcher;
    String other;
    DateButtonListener buttonListener;

    interface DateButtonListener{
        void setInitDateButtonText(String date);
        void setFinDateButtonText(String date);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if(context instanceof DateButtonListener){
            buttonListener = (DateButtonListener) context;
        }
    }

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
    /*
    method run once 'OK' is clicked
     */
    public void onDateSet(DatePicker view, int year, int month, int day) {
        String sDay = Integer.toString(day);
        String sMonth = Integer.toString(month);
        if(day<10){
            sDay = "0" + day;
        }
        if(month<10) {
            sMonth = "0" + month;
        }
        String date = sDay + "/" + sMonth + "/" + year; // save selected date to String
        /*
        if sentence to differentiate between initial date and final date, switcher parameter is set in setActivity()
        - true = initial date
        - false = final date
         */
        if(switcher){
            Date initDate = new Date(year,month,day);
            buttonListener.setInitDateButtonText(date);
            /*
            if sentence to verify id there is an existing final date
            if so we must verify that initial date entered is before this pre-existent final date
             */
            if(!other.toLowerCase().equals("final date")){
                String finalString = other;
                String[] arrayDate = finalString.split("/");
                Date finalDate = new Date(Integer.valueOf(arrayDate[2]),Integer.valueOf(arrayDate[1]),Integer.valueOf(arrayDate[0]));
                /*
                in case initial date is after then we re-ask for an final date
                 */
                if(initDate.after(finalDate)){
                    TimePickerFragment newFragment = new TimePickerFragment();
                    newFragment.show(getFragmentManager(), "timePicker");
                    newFragment.setActivity(false, date);
                }
            }
        }else {
            Date finalDate = new Date(year,month,day);
            buttonListener.setFinDateButtonText(date);
            /*
            if sentence to verify id there is an existing initial date
            if so we must verify that initial date entered is after this pre-existent initial date
             */
            if(!other.toLowerCase().equals("init date")){
                String initString = other;
                String[] arrayDate = initString.split("/");
                Date initDate = new Date(Integer.valueOf(arrayDate[2]),Integer.valueOf(arrayDate[1]),Integer.valueOf(arrayDate[0]));
                /*
                in case final date is before then we re-ask for an initial date
                 */
                if(finalDate.before(initDate)){
                    TimePickerFragment newFragment = new TimePickerFragment();
                    newFragment.show(getFragmentManager(), "timePicker");
                    newFragment.setActivity(true, date);
                }
            }
        }
    }

    public void setActivity(boolean b, String otherDate){
        switcher = b;
        other = otherDate;
    }
}