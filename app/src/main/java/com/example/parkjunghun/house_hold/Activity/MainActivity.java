package com.example.parkjunghun.house_hold.Activity;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.FrameLayout;

import com.example.parkjunghun.house_hold.Fragment.MainFragment;
import com.example.parkjunghun.house_hold.Model.StoreUsingInfo;
import com.example.parkjunghun.house_hold.Model.UsingInfoEvent;
import com.example.parkjunghun.house_hold.R;
import com.example.parkjunghun.house_hold.Util.DatabaseManager;
import com.example.parkjunghun.house_hold.Util.FirebaseStoreManager;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.HashMap;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.main_frame)
    FrameLayout main_frame;
    HashMap<String, Object> datas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        EventBus.getDefault().register(this);

        datas = new HashMap<>();

        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.RECEIVE_SMS)
                != PackageManager.PERMISSION_GRANTED) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                    Manifest.permission.RECEIVE_SMS)) {
            } else {
                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.RECEIVE_SMS},
                        23);
            }
        }
        getSupportFragmentManager().beginTransaction().replace(R.id.main_frame , new MainFragment()).commit();
    }


    @Subscribe
    public void getEventBus(UsingInfoEvent usingInfoEvent) {
        if (usingInfoEvent.isResult()) {
            //Firebase database 데이터넣음
            DatabaseManager.getInstance().setUsingInfo(usingInfoEvent.getUsingInfo());
            DatabaseManager.getInstance().getUsingInfo();
            DatabaseManager.getInstance().gettodayInfo();

            datas.put("balance",usingInfoEvent.getUsingInfo().getBalance());
            datas.put("using_money",usingInfoEvent.getUsingInfo().getUsing_money());
            datas.put("bank",usingInfoEvent.getUsingInfo().getUsing_bank());
            datas.put("place",usingInfoEvent.getUsingInfo().getUsing_place());
            datas.put("using_time",usingInfoEvent.getUsingInfo().getUsing_time());

            StoreUsingInfo storeUsingInfo = new StoreUsingInfo();
            storeUsingInfo.setUsingmap(datas);

            Log.i("usinginfo 이벤트@@@@@","@@@@");

            FirebaseStoreManager.getInstance().setUsingInfo(storeUsingInfo);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onDestroy() {
        EventBus.getDefault().unregister(this);
        super.onDestroy();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String permissions[], int[] grantResults) {
        switch (requestCode) {
            case 23: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    Log.e("permission", "권한 오케이");
                } else {
                    Log.e("permission", "권한 안됨");
                }
                return;
            }
        }
    }
}
