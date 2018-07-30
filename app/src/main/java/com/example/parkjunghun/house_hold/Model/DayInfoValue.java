package com.example.parkjunghun.house_hold.Model;

import java.util.ArrayList;

public class DayInfoValue {
    public String day;
    public ArrayList<DayInfoModel> dayInfoModelArrayList;

    public DayInfoValue(String day, ArrayList<DayInfoModel> dayInfoModelArrayList) {
        this.day = day;
        this.dayInfoModelArrayList = dayInfoModelArrayList;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public ArrayList<DayInfoModel> getDayInfoModelArrayList() {
        return dayInfoModelArrayList;
    }

    public void setDayInfoModelArrayList(ArrayList<DayInfoModel> dayInfoModelArrayList) {
        this.dayInfoModelArrayList = dayInfoModelArrayList;
    }
}
