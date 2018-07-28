package com.example.parkjunghun.house_hold.Util;

import com.example.parkjunghun.house_hold.Model.UsingInfo;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class Util {

    public static Util instance = new Util();
    private UsingInfo usingInfo;
    private SimpleDateFormat simpleDateFormat;

    public Util() {
        simpleDateFormat = new SimpleDateFormat("HH:mm:ss", Locale.getDefault());
    }

    public static Util getInstance() {
        return instance;
    }

    public UsingInfo getUsingInfo() {
        return usingInfo;
    }

    public void setUsingInfo(UsingInfo usingInfo) {
        this.usingInfo = usingInfo;
    }


    public String getCurTime(){
        String date = "";
        String timer = "";
        Date time = new Date();
        try {
            date = simpleDateFormat.format(Calendar.getInstance().getTime());
            timer = simpleDateFormat.format(time);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return timer;
    }
}
