package com.example.parkjunghun.house_hold.Model;

public class StoreParseModel {
    private String bank;
    private String using_time;
    private String balance;
    private String place;
    private String using_money;

    public StoreParseModel(){
    }

    public StoreParseModel(String bank, String using_time, String balance, String place, String using_money) {
        this.bank = bank;
        this.using_time = using_time;
        this.balance = balance;
        this.place = place;
        this.using_money = using_money;
    }

    public String getBank() {
        return bank;
    }

    public void setBank(String bank) {
        this.bank = bank;
    }

    public String getUsing_time() {
        return using_time;
    }

    public void setUsing_time(String using_time) {
        this.using_time = using_time;
    }

    public String getBalance() {
        return balance;
    }

    public void setBalance(String balance) {
        this.balance = balance;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public String getUsing_money() {
        return using_money;
    }

    public void setUsing_money(String using_money) {
        this.using_money = using_money;
    }
}
