package com.example.maxspahn.studentbnb;

import android.app.Dialog;
import android.app.DialogFragment;
import android.app.DatePickerDialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;

import java.util.Calendar;
import java.util.Date;

public class TimePickerFragment extends DialogFragment implements DatePickerDialog.OnDateSetListener {

    private String date = null;
    private static Date initDate = null;
    private static Date finalDate = null;
    boolean switcher;
    DateButtonLitener buttonLitener;

    public interface DateButtonLitener{
        public void setInitDateButtonText(String date);
        public void setFinDateButtonText(String date);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if(context instanceof DateButtonLitener){
            buttonLitener = (DateButtonLitener) context;
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

    public void onDateSet(DatePicker view, int year, int month, int day) {
        date = day + "/" + month + "/" + year;
        if(switcher == true){
            initDate = new Date(year,month,day);
            if(finalDate != null){
                String finalString = String.valueOf(SearchRoomActivity.findateButton.getText());
                String[] arrayDate = finalString.split("/");
                finalDate = new Date(Integer.valueOf(arrayDate[2]),Integer.valueOf(arrayDate[1]),Integer.valueOf(arrayDate[0]));
                if(initDate.before(finalDate)){
                    buttonLitener.setInitDateButtonText(date);
                }else{
                    buttonLitener.setInitDateButtonText(SearchRoomActivity.findateButton.getText().toString());
                    buttonLitener.setFinDateButtonText(date);
                }
            }else {
                buttonLitener.setInitDateButtonText(date);
            }
        }else {
            finalDate = new Date(year,month,day);
            if(initDate != null){
                String initString = String.valueOf(SearchRoomActivity.initdateButton.getText());
                String[] arrayDate = initString.split("/");
                initDate = new Date(Integer.valueOf(arrayDate[2]),Integer.valueOf(arrayDate[1]),Integer.valueOf(arrayDate[0]));
                if(finalDate.after(initDate)){
                    buttonLitener.setFinDateButtonText(date);
                }else{
                    buttonLitener.setFinDateButtonText(SearchRoomActivity.initdateButton.getText().toString());
                    buttonLitener.setInitDateButtonText(date);
                }
            }else{
                buttonLitener.setFinDateButtonText(date);
            }
        }
    }

    public String getDate(){
        return date;
    }

    public void setActivity( boolean b){
        switcher = b;
    }
}