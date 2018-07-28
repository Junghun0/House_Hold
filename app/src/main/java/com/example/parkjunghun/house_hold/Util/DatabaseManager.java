package com.example.parkjunghun.house_hold.Util;

import com.example.parkjunghun.house_hold.Model.UsingInfo;
import com.example.parkjunghun.house_hold.Model.UsingInfoEvent;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

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
        //EventBus.getDefault().post(new UsingInfoEvent(true,usingInfo));
    }

    public void getUsingInfo(String username){
        usinginfo_databaseReference.child(username).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                UsingInfo usingInfo = dataSnapshot.getValue(UsingInfo.class);
                EventBus.getDefault().post(new UsingInfoEvent(true, usingInfo));
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                EventBus.getDefault().post(new UsingInfoEvent(false, new UsingInfo()));
            }
        });
    }
}
