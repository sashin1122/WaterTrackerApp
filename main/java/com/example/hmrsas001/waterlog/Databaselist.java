package com.example.hmrsas001.waterlog;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

import static android.provider.AlarmClock.EXTRA_MESSAGE;

public class Databaselist extends AppCompatActivity {

    Button dent;
    Button homeb;


    public static final String TAG = "Databaselist";
    DatabaseHelper mdatabasehelper;
    private ListView mlistview;

    protected void onCreate(@Nullable Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_layout);
        mdatabasehelper = new DatabaseHelper(this);
        mlistview = (ListView) findViewById(R.id.listView);
        populatelistview();

        homeb = (Button)findViewById(R.id.homebutton9);
        homeb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Databaselist.this,MainActivity.class);
                startActivity(intent);
            }
        });

        dent = (Button)findViewById(R.id.diarybutton6);
        dent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Databaselist.this,DiaryActivity.class);
                startActivity(intent);
            }
        });
        mlistview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position,
                                    long id) {
                Intent intent = new Intent(Databaselist.this, DiaryEntry.class);
                String message = diarystring(Integer.toString(position+1));
                int index = position+1;
                int length = mlistview.getCount();
                System.out.println("TESTING::::::::"+message);
                intent.putExtra("EXTRA_MESSAGE", message);
                intent.putExtra("INDEX",index);
                intent.putExtra("LENGTH",length);
                startActivity(intent);
            }
        });
    }


    public void populatelistview(){
        Log.d(TAG,"populationg list");

        Cursor data = mdatabasehelper.getData();

        ArrayList<String> listdata = new ArrayList<>();
        while(data.moveToNext()){
            listdata.add("DATE: "+data.getString(0) + "  TOTAL: "+data.getString(10));
        }
        ListAdapter adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1,listdata);
        mlistview.setAdapter(adapter);
        data.close();
    }

    public String diarystring(String position){
        Cursor data = mdatabasehelper.getData();
        String data1;

        while(data.moveToNext()){
            System.out.println("TEST::::::::::::::::::::::: "+data.getString(11) + "::::::::::::::::::::;;"+position);
            if(data.getString(11).equals(position)){
                data1 ="DATE:  "+data.getString(0)+ "%SHOWER: "+data.getString(1)+"%TOILET: "+data.getString(2)+"%DRINKING: "+data.getString(3)+"%HYGIENE: "+data.getString(4)+"%LAUNDRY: "+data.getString(5)+"%DISHES: "+data.getString(6)+"%COOKING: "+data.getString(7)+"%CLEANING:"+data.getString(8)+"%OTHER: "+data.getString(9)+"%TOTAL: "+data.getString(10);
                return data1;

            }
            else{
                continue;
            }
        }
        data.close();
        return "DATE:  "+data.getString(0)+ "%SHOWER: "+data.getString(1)+"%TOILET: "+data.getString(2)+"%DRINKING: "+data.getString(3)+"%HYGIENE: "+data.getString(4)+"%LAUNDRY: "+data.getString(5)+"%DISHES: "+data.getString(6)+"%COOKING: "+data.getString(7)+"%CLEANING:"+data.getString(8)+"%OTHER: "+data.getString(9)+"%TOTAL: "+data.getString(10);


    }
    private void toastmessage(String message){
        Toast.makeText(this,message,Toast.LENGTH_SHORT).show();
    }
}
