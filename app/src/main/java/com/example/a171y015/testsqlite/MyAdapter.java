package com.example.a171y015.testsqlite;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.ArrayList;

/**
 * Created by 171y015 on 2018/07/19.
 */

public class MyAdapter extends BaseAdapter {
    Context context;
    LayoutInflater layoutInflater = null;
    ArrayList<ListActivity> foodlist;

    public MyAdapter(Context context){
        this.context = context;
        this.layoutInflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    public void setFoodlist(ArrayList<ListActivity> foodlist){
        this.foodlist = foodlist;
    }

    @Override
    public int getCount() {
        return foodlist.size();
    }

    @Override
    public Object getItem(int position){
        return foodlist.get(position);
    }

    @Override
    public long getItemId(int position){
        return foodlist.get(position).getId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        convertView = layoutInflater.inflate(R.layout.activity_list,parent,false);

//        ((TextView)convertView.findViewById(R.id.name)).setText(foodlist.get(position).getName());
//        ((TextView)convertView.findViewById(R.id.price)).setText(String.valueOf(foodlist.get(position).getPrice()));

        return convertView;
    }

}
