package com.example.parkjunghun.house_hold.Model;

public class UsingInfo {

    private String user_name;
    private String using_money;
    private String using_bank;
    private String using_place;
    private String balance;
    private String using_time;

    public UsingInfo(){}

    public UsingInfo(String user_name, String using_money, String using_bank, String using_place, String balance, String using_time) {
        this.user_name = user_name;
        this.using_money = using_money;
        this.using_bank = using_bank;
        this.using_place = using_place;
        this.balance = balance;
        this.using_time = using_time;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public String getUsing_money() {
        return using_money;
    }

    public void setUsing_money(String using_money) {
        this.using_money = using_money;
    }

    public String getUsing_bank() {
        return using_bank;
    }

    public void setUsing_bank(String using_bank) {
        this.using_bank = using_bank;
    }

    public String getUsing_place() {
        return using_place;
    }

    public void setUsing_place(String using_place) {
        this.using_place = using_place;
    }

    public String getBalance() {
        return balance;
    }

    public void setBalance(String balance) {
        this.balance = balance;
    }

    public String getUsing_time() {
        return using_time;
    }

    public void setUsing_time(String using_time) {
        this.using_time = using_time;
    }
}
