package com.example.hmrsas001.waterlog;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import static android.provider.AlarmClock.EXTRA_MESSAGE;

public class DiaryEntry extends Activity {

    Intent intent5;
    DatabaseHelper mdatabasehelper;

    String datamessage;
    int index;
    int length;

    Button next;
    Button previous;
    Button home;
    TextView date;
    TextView shower;
    TextView toilet;
    TextView drinking;
    TextView hygene;
    TextView laundry;
    TextView dishes;
    TextView cooking;
    TextView cleaning;
    TextView other;
    TextView total;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.diarypage);
        mdatabasehelper = new DatabaseHelper(this);

        home =(Button)findViewById(R.id.home);
        previous =(Button)findViewById(R.id.prebutton);
        next =(Button)findViewById(R.id.nestbutton);
        date = (TextView) findViewById(R.id.textView13);
        shower = (TextView) findViewById(R.id.textView14);
        toilet = (TextView) findViewById(R.id.textView15);
        drinking = (TextView) findViewById(R.id.textView16);
        hygene = (TextView) findViewById(R.id.textView17);
        laundry = (TextView) findViewById(R.id.textView18);
        dishes = (TextView) findViewById(R.id.textView19);
        cooking = (TextView) findViewById(R.id.textView20);
        cleaning = (TextView) findViewById(R.id.textView21);
        other = (TextView) findViewById(R.id.textView22);
        total = (TextView) findViewById(R.id.textView23);

        intent5 = getIntent();
        datamessage = intent5.getStringExtra("EXTRA_MESSAGE");
        index = intent5.getIntExtra("INDEX",0);
        length = intent5.getIntExtra("LENGTH",0);

        String [] data;
        System.out.println(datamessage);
        data = datamessage.split("%");



        date.setText(data[0]);
        shower.setText(data[1]);
        toilet.setText(data[2]);
        drinking.setText(data[3]);
        hygene.setText(data[4]);
        laundry.setText(data[5]);
        dishes.setText(data[6]);
        cooking.setText(data[7]);
        cleaning.setText(data[8]);
        other.setText(data[9]);
        total.setText(data[10]);

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                index = index+1;
                if(index<=length) {
                    String nextentry = diarystring(Integer.toString(index));

                    String[] data;
                    System.out.println(nextentry);
                    data = nextentry.split("%");


                    date.setText(data[0]);
                    shower.setText(data[1]);
                    toilet.setText(data[2]);
                    drinking.setText(data[3]);
                    hygene.setText(data[4]);
                    laundry.setText(data[5]);
                    dishes.setText(data[6]);
                    cooking.setText(data[7]);
                    cleaning.setText(data[8]);
                    other.setText(data[9]);
                    total.setText(data[10]);
                }
                else{
                    index=1;

                    String nextentry = diarystring(Integer.toString(index));

                    String[] data;
                    System.out.println(nextentry);
                    data = nextentry.split("%");


                    date.setText(data[0]);
                    shower.setText(data[1]);
                    toilet.setText(data[2]);
                    drinking.setText(data[3]);
                    hygene.setText(data[4]);
                    laundry.setText(data[5]);
                    dishes.setText(data[6]);
                    cooking.setText(data[7]);
                    cleaning.setText(data[8]);
                    other.setText(data[9]);
                    total.setText(data[10]);
                }
            }
        });

        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DiaryEntry.this,MainActivity.class);
                startActivity(intent);
            }
        });

        previous.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                index = index -1;
                if (index >= 1) {

                    String nextentry = diarystring(Integer.toString(index));

                    String[] data;
                    System.out.println(nextentry);
                    data = nextentry.split("%");


                    date.setText(data[0]);
                    shower.setText(data[1]);
                    toilet.setText(data[2]);
                    drinking.setText(data[3]);
                    hygene.setText(data[4]);
                    laundry.setText(data[5]);
                    dishes.setText(data[6]);
                    cooking.setText(data[7]);
                    cleaning.setText(data[8]);
                    other.setText(data[9]);
                    total.setText(data[10]);
                }
                else{
                    index = length;

                    String nextentry = diarystring(Integer.toString(index));

                    String[] data;
                    System.out.println(nextentry);
                    data = nextentry.split("%");


                    date.setText(data[0]);
                    shower.setText(data[1]);
                    toilet.setText(data[2]);
                    drinking.setText(data[3]);
                    hygene.setText(data[4]);
                    laundry.setText(data[5]);
                    dishes.setText(data[6]);
                    cooking.setText(data[7]);
                    cleaning.setText(data[8]);
                    other.setText(data[9]);
                    total.setText(data[10]);
                }
            }
        });


    }
    public String diarystring(String position){
        Cursor data = mdatabasehelper.getData();
        String data1;

        while(data.moveToNext()){

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
}
