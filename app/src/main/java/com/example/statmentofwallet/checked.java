package com.example.statmentofwallet;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.Nullable;

import java.util.List;

public class checked extends Activity {
    Button abb, del;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.checkdel);
        abb = (Button)findViewById(R.id.abb);
        del = (Button)findViewById(R.id.delite);
        final Intent intent = getIntent();
        //DisplayMetrics dm = new DisplayMetrics();
        //getWindowManager().getDefaultDisplay().getMetrics(dm);

        abb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setResult(RESULT_CANCELED);
                finish();
            }
        });
        del.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = intent.getIntExtra("Position",-3);
                //Toast.makeText(checked.this,Integer.toString(position),Toast.LENGTH_LONG).show();
                if(position != -3){
                    Intent returning = new Intent();
                    returning.putExtra("Position",position);
                    setResult(RESULT_OK,returning);
                    finish();

                }else {
                    setResult(RESULT_CANCELED);
                }

                setResult(RESULT_OK);
                //finish();
            }
        });




    }
}
