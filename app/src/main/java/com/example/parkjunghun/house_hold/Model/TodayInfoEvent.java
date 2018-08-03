package com.example.parkjunghun.house_hold.Model;

import java.util.List;

public class TodayInfoEvent {
    private List<DayInfoModel> dayInfoModels;

    public List<DayInfoModel> getDayInfoModels() {
        return dayInfoModels;
    }

    public void setDayInfoModels(List<DayInfoModel> dayInfoModels) {
        this.dayInfoModels = dayInfoModels;
    }

    public TodayInfoEvent(List<DayInfoModel> dayInfoModels) {
        this.dayInfoModels = dayInfoModels;
    }
}
