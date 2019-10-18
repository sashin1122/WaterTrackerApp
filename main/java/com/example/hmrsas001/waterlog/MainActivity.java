package com.example.hmrsas001.waterlog;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
//import android.view.View;

public class MainActivity extends AppCompatActivity {






    public Button button;
    public Button diary ;
    public TextView total;
    public TextView daily;
    public DatabaseHelper helper;

    public void init() {
        button = (Button) findViewById(R.id.button2);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.i("MyApp","Button was Pressed");
                openActivity();
            }
        });


    }
    private TextView mTextMessage;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    mTextMessage.setText(R.string.title_home);
                    return true;
                case R.id.navigation_dashboard:
                    mTextMessage.setText(R.string.title_dashboard);
                    return true;
                case R.id.navigation_notifications:
                    mTextMessage.setText(R.string.title_notifications);
                    return true;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mTextMessage = (TextView) findViewById(R.id.message);
        total = (TextView)findViewById(R.id.textView);
        daily = (TextView)findViewById(R.id.textView24);
        helper = new DatabaseHelper(this);
        int t = helper.getTotal();
        int a = helper.getDailyAv();
        total.setText("TOTAL WATER USED :"+Integer.toString(t));
        daily.setText("AVERAGE DIALY WATER USAGE : "+Integer.toString(a));
        init();


        diary = (Button)findViewById(R.id.button3);
        diary.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,Databaselist.class);
                startActivity(intent);
            }
        });

    }
    public void openActivity(){
        Intent intent1 = new Intent(this, DiaryActivity.class);
        startActivity(intent1);
    }

}
