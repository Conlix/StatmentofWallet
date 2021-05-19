package com.example.statmentofwallet;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.Calendar;

public class popup extends Activity {
    Button save, abb,dayup,daydown,hourup,hourdown;
    EditText transaktion, Reason;
    TextView day, hour;
    int amount = 0, month, dayofMonth, hour24hrs;
    String dayofweek, hour_of, reason;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.popup);

        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);

        int width = dm.widthPixels;
        int height = dm.heightPixels;
        final Intent intent1 = getIntent();
        //getWindow().setLayout((int) (width*.45),(int) (height*.07));

        save = (Button)findViewById(R.id.save);
        abb = (Button)findViewById(R.id.abbrechen);
        transaktion = (EditText) findViewById(R.id.transaktion);
        Reason = (EditText) findViewById(R.id.reason);

        day = (TextView)findViewById(R.id.day);
        dayup = (Button)findViewById(R.id.dayup);
        daydown = (Button)findViewById(R.id.daydown);
        hour = (TextView)findViewById(R.id.hour);
        hourup = (Button)findViewById(R.id.hourup);
        hourdown = (Button)findViewById(R.id.hourdown);

        Calendar calendar = Calendar.getInstance();
        month = calendar.get(Calendar.MONTH);
        dayofMonth = calendar.get(Calendar.DAY_OF_MONTH);
        dayofweek = Integer.toString(month+1)+ "." + Integer.toString(dayofMonth);
        day.setText(dayofweek);

        Calendar rightNow = Calendar.getInstance();
        hour24hrs = rightNow.get(Calendar.HOUR_OF_DAY)+2;
        hour_of = Integer.toString(hour24hrs);
        hour.setText(hour_of);

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try{

                    amount = Integer.parseInt(transaktion.getText().toString());
                }catch (Exception ex){
                    amount = 0;
                }

                reason = Reason.getText().toString();

                Intent resultIntent = new Intent();
                resultIntent.putExtra("day",dayofweek);
                resultIntent.putExtra("reason",reason);
                resultIntent.putExtra("hour",hour_of+"h");
                resultIntent.putExtra("amount",Integer.toString(amount)+ "€");

                setResult(RESULT_OK,resultIntent);
                finish();
            }
        });

        abb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent resultIntent = new Intent();
                resultIntent.putExtra("del",true);
                setResult(RESULT_CANCELED,resultIntent);
                finish();
            }
        });

        hourup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                hour24hrs++;
                hour_of = Integer.toString(hour24hrs);
                hour.setText(hour_of);
            }
        });
        hourdown.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                hour24hrs--;
                hour_of = Integer.toString(hour24hrs);
                hour.setText(hour_of);
            }
        });

        dayup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dayofMonth++;
                dayofweek = Integer.toString(month+1)+ "." + Integer.toString(dayofMonth);
                day.setText(dayofweek);
            }
        });
        daydown.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dayofMonth--;
                dayofweek = Integer.toString(month+1)+ "." + Integer.toString(dayofMonth);
                day.setText(dayofweek);
            }
        });
    }
}
