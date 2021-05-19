package com.example.statmentofwallet;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;

import java.util.ArrayList;
import java.util.List;

public class MyListAdapter extends ArrayAdapter<Data> {
    private Context rcontext;
    private int rresouce;
    public MyListAdapter(@NonNull Context context, int resouce, @NonNull ArrayList<Data> data) {
        super(context, resouce,(List<Data>) data);
        this.rcontext = context;
        this.rresouce = resouce;
    }
    @NonNull
    @Override
    public View getView(final int position, @Nullable View convertView, @NonNull ViewGroup parent){
        LayoutInflater layoutInflater = LayoutInflater.from(rcontext);
        convertView = layoutInflater.inflate(rresouce, parent, false);

        TextView money = (TextView) convertView.findViewById(R.id.money);
        TextView reason = (TextView) convertView.findViewById(R.id.reason);
        TextView date = (TextView) convertView.findViewById(R.id.date);
        TextView hour = (TextView) convertView.findViewById(R.id.time);

        //money.setEnabled(false);
        //reason.setEnabled(false);
        //date.setEnabled(false);
        //hour.setEnabled(false);

        money.setText(getItem(position).getMoney());
        reason.setText(getItem(position).getReason());
        date.setText(getItem(position).getDay());
        hour.setText(getItem(position).getTime());
/*
        reason.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(),"Works",Toast.LENGTH_LONG).show();
            }
        });


 */
        return convertView;
    }
}
