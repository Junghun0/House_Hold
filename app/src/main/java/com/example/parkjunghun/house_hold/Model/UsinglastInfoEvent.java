package com.example.parkjunghun.house_hold.Model;

public class UsinglastInfoEvent {
    public boolean result;
    public int using_money;
    public int balance;
    public String last_using_info;

    public UsinglastInfoEvent(boolean result, int using_money, int balance, String last_using_info) {
        this.result = result;
        this.using_money = using_money;
        this.balance = balance;
        this.last_using_info = last_using_info;
    }

    public boolean isResult() {
        return result;
    }

    public void setResult(boolean result) {
        this.result = result;
    }

    public int getUsing_money() {
        return using_money;
    }

    public void setUsing_money(int using_money) {
        this.using_money = using_money;
    }

    public int getBalance() {
        return balance;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }

    public String getLast_using_info() {
        return last_using_info;
    }

    public void setLast_using_info(String last_using_info) {
        this.last_using_info = last_using_info;
    }
}
