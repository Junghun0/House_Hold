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

import java.util.Date;

public class SmsReceiver extends BroadcastReceiver{
    public static final String TAG ="SmsReceiver!";
    @Override
    public void onReceive(Context context, Intent intent) {

        Bundle bundle = intent.getExtras();
        SmsMessage[] messages = parseSmsMessage(bundle);
        String using_money = null;

        if(messages != null && messages.length > 0){
            String sender = messages[0].getOriginatingAddress();
            Log.e(TAG,"SMS sender->"+sender.toString());

            String contents = messages[0].getMessageBody().toString();
            Log.e(TAG,"SMS contents->"+contents);

            String lines[] = contents.split("\\r?\\n");

            Log.e("price info","split data ->"+lines[0]+","+lines[1]+","+lines
                    [2]+","+lines[3]+","+lines[4]);

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

            Date receivedDate = new Date(messages[0].getTimestampMillis());
            Log.e(TAG,"SMS receivedDate->"+receivedDate);

            //UsingInfo smsModel = new UsingInfo(lines[4],using_money,lines[1],lines[5],"1");
            //total_using = Integer.parseInt(using_money.replaceAll(",",""));

            UsingInfo usingInfo = new UsingInfo("종민",using_money,"우리은행",lines[4],lines[5]);
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
