package com.example.parkjunghun.house_hold.Util;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;

import com.example.parkjunghun.house_hold.Model.UsingInfo;
import com.example.parkjunghun.house_hold.Model.UsinglastInfoEvent;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import org.greenrobot.eventbus.EventBus;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DatabaseManager {

    public static DatabaseManager instance = new DatabaseManager();
    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference usinginfo_databaseReference;
    private SimpleDateFormat simpleDateFormat_day = new SimpleDateFormat("yyyy_MM_dd");
    private Date date = new Date();

    public static DatabaseManager getInstance() {
        return instance;
    }

    public DatabaseManager(){
        firebaseDatabase = FirebaseDatabase.getInstance();
        usinginfo_databaseReference = firebaseDatabase.getReference("using_info");
    }

    public void setUsingInfo(UsingInfo usingInfo){
        usinginfo_databaseReference.child(simpleDateFormat_day.format(date)).child(Util.getInstance().getCurTime()).push().setValue(usingInfo);
        Util.getInstance().setUsingInfo(usingInfo);
    }

    public void getUsingInfo(){
        usinginfo_databaseReference.child(simpleDateFormat_day.format(date)).child(Util.getInstance().getCurTime()).addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                UsingInfo usingInfo = dataSnapshot.getValue(UsingInfo.class);
                int bus_usingmoney = Integer.parseInt(usingInfo.getUsing_money().replaceAll(",",""));
                int bus_getbalance = Integer.parseInt(usingInfo.getBalance().replaceAll(",",""));

                EventBus.getDefault().post(new UsinglastInfoEvent(true, bus_usingmoney, bus_getbalance, usingInfo.getUsing_place()));
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                Log.e("onchildchanged",""+dataSnapshot.getValue());
            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {
                Log.e("onchildremoved","test"+dataSnapshot.getValue());
            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                Log.e("onchildMoved","test"+dataSnapshot.getValue());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Log.e("oncancelled","test"+databaseError.toString());
            }
        });
    }
}
