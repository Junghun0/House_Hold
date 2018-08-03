package com.example.parkjunghun.house_hold.Util;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.parkjunghun.house_hold.Model.ListViewItem;
import com.example.parkjunghun.house_hold.R;

import java.util.ArrayList;

public class ListViewAdapter extends BaseAdapter {

    private ArrayList<ListViewItem> listViewItems = new ArrayList<>();

    public ListViewAdapter(){
    }

    public void clearListview(){
        listViewItems.clear();
    }

    @Override
    public int getCount() {
        return listViewItems.size();
    }

    @Override
    public Object getItem(int position) {
        return listViewItems.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final int pos = position;
        final Context context = parent.getContext();

        if(convertView == null){
            LayoutInflater layoutInflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = layoutInflater.inflate(R.layout.listview_layout,parent,false);
        }

        TextView usingmoney_txtview = (TextView)convertView.findViewById(R.id.usingmoney_list);
        TextView usingplace_txtview = (TextView)convertView.findViewById(R.id.usingplace_list);
        TextView usingdate_txtview = (TextView)convertView.findViewById(R.id.usingdate_list);

        ListViewItem listViewItem = listViewItems.get(position);

        usingdate_txtview.setText(listViewItem.getList_using_date());
        usingplace_txtview.setText(listViewItem.getList_using_place());
        usingmoney_txtview.setText(listViewItem.getList_using_money());

        return convertView;
    }

    public void addItem(String list_using_money, String list_using_place, String list_using_date){
        ListViewItem listViewItem = new ListViewItem();
        listViewItem.setList_using_date(list_using_date);
        listViewItem.setList_using_money(list_using_money);
        listViewItem.setList_using_place(list_using_place);

        listViewItems.add(listViewItem);
    }
}
