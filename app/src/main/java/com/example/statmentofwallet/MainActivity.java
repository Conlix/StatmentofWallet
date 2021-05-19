package com.example.statmentofwallet;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    ListView lv;
    Button neu;
    EditText current,change;
    DatabaseHelper databaseHelper;
    final ArrayList<Data> arrayList = new ArrayList<>();
    List<Data> stortdata;
    int capital = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        lv = findViewById(R.id.list);
        neu = findViewById(R.id.neu);
        current = findViewById(R.id.current);
        change = findViewById(R.id.change);

        current.setEnabled(false);
        change.setEnabled(false);

        databaseHelper = new DatabaseHelper(MainActivity.this);

        stortdata = databaseHelper.getAllData();

        Collections.reverse(arrayList);
        Data Dcurser;
        for (int i = 0; i < stortdata.size(); i++){
            Dcurser = stortdata.get(i);
            arrayList.add(new Data(Dcurser.getId(),Dcurser.getMoney(),Dcurser.getReason(),Dcurser.getDay(),Dcurser.getTime()));
            capital = capital + Integer.valueOf(Dcurser.getMoney().substring(0,Dcurser.getMoney().length()-1));
            //Toast.makeText(MainActivity.this,arrayList.get(i).getMoney(),Toast.LENGTH_LONG).show();
        }
        Collections.reverse(arrayList);
        current.setText(Integer.toString(capital));

        MyListAdapter adapter = new MyListAdapter(this, R.layout.row,arrayList);

        lv.setAdapter(adapter);

        neu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,popup.class);
                //intent.putExtra("data",arrayList);
                //startActivity(intent);
                startActivityForResult(intent,1);
            }
        });

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //Toast.makeText(MainActivity.this,"Test",Toast.LENGTH_SHORT).show();
                /*databaseHelper.deliteOne(arrayList.get(position));
                capital = capital + Integer.valueOf(arrayList.get(position).getMoney().substring(0,arrayList.get(position).getMoney().length()-1));
                current.setText(Integer.toString(capital));
                arrayList.remove(position);

                 */
            }
        });


    }
    public void Del(int position){
        Toast.makeText(MainActivity.this,"Tedt",Toast.LENGTH_SHORT).show();
        databaseHelper.deliteOne(arrayList.get(position));
        capital = capital + Integer.valueOf(arrayList.get(position).getMoney().substring(0,arrayList.get(position).getMoney().length()-1));
        current.setText(Integer.toString(capital));
        arrayList.remove(position);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1){
            if(resultCode==RESULT_OK){
                //Update ListAdapter arrayList
                Collections.reverse(arrayList);
                arrayList.add(new Data(1,data.getStringExtra("amount"),data.getStringExtra("reason"),data.getStringExtra("day"),data.getStringExtra("hour")));

                Collections.reverse(arrayList);
                //Update Current Capital
                capital = capital + Integer.valueOf(data.getStringExtra("amount").substring(0,data.getStringExtra("amount").length()-1));
                current.setText(Integer.toString(capital));
                //Save Data in SQLite
                //Toast.makeText(MainActivity.this,arrayList.get().getMoney(),Toast.LENGTH_LONG).show();
                databaseHelper.addData(arrayList.get(0));
            }else {
                boolean del = data.getBooleanExtra("del",false);
                //Delit old transaktion if "del" true
            }
        }
    }
}
