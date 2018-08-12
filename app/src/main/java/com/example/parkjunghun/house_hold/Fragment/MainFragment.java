package com.example.parkjunghun.house_hold.Fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.parkjunghun.house_hold.Model.StoreInfoEvent;
import com.example.parkjunghun.house_hold.Model.TodayInfoEvent;
import com.example.parkjunghun.house_hold.Model.UsinglastInfoEvent;
import com.example.parkjunghun.house_hold.R;
import com.example.parkjunghun.house_hold.Util.FirebaseStoreManager;
import com.example.parkjunghun.house_hold.Util.ListViewAdapter;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import devs.mulham.horizontalcalendar.HorizontalCalendar;
import devs.mulham.horizontalcalendar.HorizontalCalendarView;
import devs.mulham.horizontalcalendar.utils.HorizontalCalendarListener;

public class MainFragment extends Fragment{

    TextView today_using_txtview;
    TextView today_remain_money;
    public static int adder_using;
    public static int adder_using_result;
    ListView listView;
    ListViewAdapter adapter;
    TodayInfoEvent todayInfoEvent;
    private int sum;

    private SimpleDateFormat simpleDateFormat_day = new SimpleDateFormat("yyyy_MM_dd");
    private Date cur_date = new Date();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.main_fragment_layout,container,false);

        today_using_txtview = view.findViewById(R.id.today_using_money_txtview);
        today_remain_money = view.findViewById(R.id.today_remain_money_txtview);
        listView = view.findViewById(R.id.main_listview);
        adapter = new ListViewAdapter();
        listView.setAdapter(adapter);
        adder_using = 0;

        FirebaseStoreManager.getInstance().getUsingInfo(simpleDateFormat_day.format(cur_date));

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
                Toast.makeText(getActivity(), "on dateselected"+simpleDateFormat_day.format(date), Toast.LENGTH_SHORT).show();
            }
        });

        horizontalCalendar.setCalendarListener(new HorizontalCalendarListener() {
            @Override
            public void onDateSelected(Calendar date, int position) {
                Toast.makeText(getActivity(), ""+simpleDateFormat_day.format(date.getTime()), Toast.LENGTH_SHORT).show();
                FirebaseStoreManager.getInstance().cleartestlist();
                FirebaseStoreManager.getInstance().getUsingInfo(simpleDateFormat_day.format(date.getTime()));
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

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        EventBus.getDefault().register(this);
        super.onCreate(savedInstanceState);
    }

    @Subscribe
    public void getlastBus(UsinglastInfoEvent usinglastInfoEvent){
        //마지막 사용내역 가져오기
        adder_using = Integer.valueOf(today_using_txtview.getText().toString());
        today_remain_money.setText(String.valueOf(usinglastInfoEvent.getBalance()));
    }

    @Subscribe(sticky = true)
    public void getstoreInfoEvent(StoreInfoEvent storeInfoEvent){
        if(storeInfoEvent.isResult()){
            adapter.clearListview();
            for(int i=0; i<storeInfoEvent.getRoofsize() ; i++){
                listView.setAdapter(adapter);
                adapter.addItem(storeInfoEvent.getParseModelArrayList().get(i).getUsing_money(),storeInfoEvent.getParseModelArrayList().get(i).getPlace(),storeInfoEvent.getParseModelArrayList().get(i).getUsing_time());
                adapter.notifyDataSetChanged();
            }

            for(int j=0 ; j<storeInfoEvent.getRoofsize(); j++){
                storeInfoEvent.getParseModelArrayList().get(j).getUsing_money().replaceAll(",","");
                sum = sum + Integer.valueOf(storeInfoEvent.getParseModelArrayList().get(j).getUsing_money().replaceAll(",",""));
            }

            today_using_txtview.setText(String.valueOf(sum));
            sum=0;

        }else{
            adapter.clearListview();
            adapter.notifyDataSetInvalidated();
        }

    }

    @Subscribe(sticky = true)
    public void getTodayInfoevent(TodayInfoEvent todayInfoEvent){
        adapter.clearListview();
        for(int i=0; i < todayInfoEvent.getDayInfoModels().size(); i++){
            adapter.addItem(todayInfoEvent.getDayInfoModels().get(i).using_money,todayInfoEvent.getDayInfoModels().get(i).using_place, todayInfoEvent.getDayInfoModels().get(i).using_time);
            adapter.notifyDataSetChanged();
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }
}
