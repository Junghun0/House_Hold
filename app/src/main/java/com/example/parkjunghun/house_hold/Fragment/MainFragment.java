package com.example.parkjunghun.house_hold.Fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.parkjunghun.house_hold.Model.UsinglastInfoEvent;
import com.example.parkjunghun.house_hold.R;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.Calendar;

import devs.mulham.horizontalcalendar.HorizontalCalendar;
import devs.mulham.horizontalcalendar.HorizontalCalendarView;
import devs.mulham.horizontalcalendar.utils.HorizontalCalendarListener;

public class MainFragment extends Fragment{

    TextView today_using_txtview;
    TextView today_remain_money;
    public static int adder_using;
    public static int adder_using_result;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.main_fragment_layout,container,false);
        EventBus.getDefault().register(this);

        today_using_txtview = (TextView)view.findViewById(R.id.today_using_money_txtview);
        today_remain_money = (TextView)view.findViewById(R.id.today_remain_money_txtview);

        adder_using = 0;

        /* starts before 1 month from now */
         Calendar startDate = Calendar.getInstance();
        startDate.add(Calendar.MONTH, -1);

        /* ends after 1 month from now */
        Calendar endDate = Calendar.getInstance();
        endDate.add(Calendar.MONTH, 1);

        HorizontalCalendar horizontalCalendar = new HorizontalCalendar.Builder(view , R.id.calendarView)
                .range(startDate, endDate)
                .datesNumberOnScreen(7)
                .configure()
                .showTopText(true)
                .end()
                .build();

        horizontalCalendar.setCalendarListener(new HorizontalCalendarListener() {
            @Override
            public void onDateSelected(Calendar date, int position) {
                //do something
            }
        });

        horizontalCalendar.setCalendarListener(new HorizontalCalendarListener() {
            @Override
            public void onDateSelected(Calendar date, int position) {

            }

            @Override
            public void onCalendarScroll(HorizontalCalendarView calendarView,
                                         int dx, int dy) {

            }

            @Override
            public boolean onDateLongClicked(Calendar date, int position) {
                return true;
            }
        });

        return view;
    }

    @Subscribe
    public void getlastBus(UsinglastInfoEvent usinglastInfoEvent){
        //마지막 사용내역 가져오기
        Log.e("get last Event BUs","succes"+usinglastInfoEvent.getBalance()+","+usinglastInfoEvent.getUsing_money());
        adder_using_result = adder_using+usinglastInfoEvent.getUsing_money();
        today_using_txtview.setText(String.valueOf(adder_using_result));
        adder_using = Integer.valueOf(today_using_txtview.getText().toString());

        Toast.makeText(getActivity(), ""+usinglastInfoEvent.getLast_using_info(), Toast.LENGTH_SHORT).show();

        today_remain_money.setText(String.valueOf(usinglastInfoEvent.getBalance()));
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }
}
