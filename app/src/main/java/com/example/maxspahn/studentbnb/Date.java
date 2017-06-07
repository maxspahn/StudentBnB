package com.example.maxspahn.studentbnb;

import java.io.Serializable;

/**
 * Created by Pedro León on 07/06/2017.
 */

public class Date implements Serializable {
    private int day;
    private int month;
    private int year;

    public Date(){}

    public Date(int d, int m, int y){
        day = d;
        month = m;
        year = y;
    }

    public boolean before(Date d){
        boolean result;
        if(d.year > year){
            if(d.month > month){
                if(d.day > day){
                    return true;
                }
                return false;
            }else{
                return false;
            }
        }else{
            return false;
        }
    }

    public boolean after(Date d){
        boolean result;
        if(year > d.year){
            if(month > d.month){
                if(day > d.day){
                    return true;
                }
                return false;
            }else{
                return false;
            }
        }else{
            return false;
        }
    }

    @Override
    public String toString() {
        return "Date{" +
                "day=" + day +
                ", month=" + month +
                ", year=" + year +
                '}';
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }
}
