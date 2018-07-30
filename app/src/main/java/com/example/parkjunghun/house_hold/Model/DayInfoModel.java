package com.example.parkjunghun.house_hold.Model;

public class DayInfoModel {
    public int today_using_money;
    public int today_balance;

    public DayInfoModel(int today_using_money, int today_balance) {
        this.today_using_money = today_using_money;
        this.today_balance = today_balance;
    }

    public int getToday_using_money() {
        return today_using_money;
    }

    public void setToday_using_money(int today_using_money) {
        this.today_using_money = today_using_money;
    }

    public int getToday_balance() {
        return today_balance;
    }

    public void setToday_balance(int today_balance) {
        this.today_balance = today_balance;
    }
}
