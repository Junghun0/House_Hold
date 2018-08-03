package com.example.parkjunghun.house_hold.Model;

import java.util.ArrayList;

public class DayInfoValue {
    public ArrayList<UsingInfo> dayInfoModelArrayList;

    public DayInfoValue(){}

    public DayInfoValue(ArrayList<UsingInfo> dayInfoModelArrayList) {
        this.dayInfoModelArrayList = dayInfoModelArrayList;
    }

    public ArrayList<UsingInfo> getDayInfoModelArrayList() {
        return dayInfoModelArrayList;
    }

    public void setDayInfoModelArrayList(ArrayList<UsingInfo> dayInfoModelArrayList) {
        this.dayInfoModelArrayList = dayInfoModelArrayList;
    }
}
