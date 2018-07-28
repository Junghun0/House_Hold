package com.example.parkjunghun.house_hold.Receiver;

import android.annotation.SuppressLint;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.telephony.SmsMessage;
import android.util.Log;

import com.example.parkjunghun.house_hold.Model.UsingInfo;
import com.example.parkjunghun.house_hold.Model.UsingInfoEvent;
import com.example.parkjunghun.house_hold.Util.Util;

import org.greenrobot.eventbus.EventBus;

public class SmsReceiver extends BroadcastReceiver{
    public static final String TAG ="SmsReceiver!";
    @Override
    public void onReceive(Context context, Intent intent) {

        Bundle bundle = intent.getExtras();
        SmsMessage[] messages = parseSmsMessage(bundle);
        String using_money = "";
        String remain_money = "";

        if(messages != null && messages.length > 0){
            String sender = messages[0].getOriginatingAddress();
            Log.e(TAG,"SMS sender->"+sender.toString());

            String contents = messages[0].getMessageBody().toString();
            Log.e(TAG,"SMS contents->"+contents);

            String lines[] = contents.split("\\r?\\n");

            Log.e("price info","split data ->"+lines[0]+","+lines[1]+","+lines
                    [2]+","+lines[3]+","+lines[4]+","+lines[5]);

            switch (lines[3].length()){
                case 8://백원
                    using_money = lines[3].substring(3,7);
                    break;
                case 9://천원
                    using_money = lines[3].substring(3,8);
                    break;
                case 10://만원
                    using_money = lines[3].substring(3,9);
                    break;
                case 11://십만원
                    using_money = lines[3].substring(3,10);
                    break;
                case 12://백만원
                    using_money = lines[3].substring(3,11);
                    break;
            }

            switch (lines[5].length()){
                case 13://잔액 백만
                    remain_money = lines[5].substring(3,12);
                    break;
                case 11://십만
                    remain_money = lines[5].substring(3,10);
                    break;
                case 10://만
                    remain_money = lines[5].substring(3,9);
                    break;
                case 9://천
                    remain_money = lines[5].substring(3,8);
                    break;
                case 7://백
                    remain_money = lines[5].substring(3,6);
                    break;
            }

            int used_money = Integer.parseInt(using_money.replaceAll(",",""));
            Log.e("used money data",""+used_money);

            int remained_money = Integer.parseInt(remain_money.replaceAll(",",""));
            Log.e("remain_money_parse",""+remained_money);

            UsingInfo usingInfo = new UsingInfo("종민",using_money,"우리은행",lines[4], String.valueOf(remained_money));
            Util.getInstance().setUsingInfo(usingInfo);
            EventBus.getDefault().post(new UsingInfoEvent(true,usingInfo));
        }
    }

    @SuppressLint("ObsoleteSdkInt")
    private SmsMessage[] parseSmsMessage(Bundle bundle){
        Object[] objects = (Object[])bundle.get("pdus");
        SmsMessage[] messages = new SmsMessage[objects.length];

        int smsCount = objects.length;
        for(int i=0; i< smsCount; i++){
            if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
                String format = bundle.getString("format");
                messages[i] = SmsMessage.createFromPdu((byte[]) objects[i], format);
            }else{
                messages[i] = SmsMessage.createFromPdu((byte[]) objects[i]);
            }
        }
        return messages;
    }

}
