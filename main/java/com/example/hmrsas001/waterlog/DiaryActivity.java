package com.example.hmrsas001.waterlog;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import static java.lang.Integer.parseInt;

public class DiaryActivity extends AppCompatActivity {


    private static final String TAG = "DiaryActivity";
    DatabaseHelper mdatabasehelper;


    Button saveb;
    Button clearb;
    Button cancel;
    EditText shower;
    EditText toilet;
    EditText drinking;
    EditText hygene;
    EditText laundry;
    EditText dishes;
    EditText cooking;
    EditText cleaning;
    EditText other;
    TextView total;
    int running_total;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_diary);

        mdatabasehelper = new DatabaseHelper(this);

        cancel = (Button)findViewById(R.id.cancelbutton);
        saveb = (Button) findViewById(R.id.savebutton);
        clearb =(Button) findViewById(R.id.clearbutton);
        shower = (EditText) findViewById(R.id.editText5);
        toilet = (EditText) findViewById(R.id.editText6);
        drinking = (EditText) findViewById(R.id.editText7);
        hygene = (EditText) findViewById(R.id.editText8);
        laundry = (EditText) findViewById(R.id.editText9);
        dishes = (EditText) findViewById(R.id.editText10);
        cooking = (EditText) findViewById(R.id.editText11);
        cleaning = (EditText) findViewById(R.id.editText12);
        other = (EditText) findViewById(R.id.editText13);
        total = (TextView) findViewById(R.id.textView11);


        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent2 = new Intent(DiaryActivity.this,MainActivity.class);
                startActivity(intent2);

            }
        });

        clearb.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        shower.setText(Integer.toString(0));
                        toilet.setText(Integer.toString(0));
                        drinking.setText(Integer.toString(0));
                        hygene.setText(Integer.toString(0));
                        laundry.setText(Integer.toString(0));
                        dishes.setText(Integer.toString(0));
                        cooking.setText(Integer.toString(0));
                        cleaning.setText(Integer.toString(0));
                        other.setText(Integer.toString(0));
                    }
                }
        );



        saveb.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Log.v("shower_amount", shower.getText().toString());
                        Log.v("toilet_amount", toilet.getText().toString());
                        Log.v("drinking_amount", drinking.getText().toString());
                        Log.v("hygene_amount", hygene.getText().toString());
                        Log.v("laundry_amount", laundry.getText().toString());
                        Log.v("dishes_amount", dishes.getText().toString());
                        Log.v("cooking_amount", cooking.getText().toString());
                        Log.v("cleaning_amount", cleaning.getText().toString());
                        Log.v("other_amount", other.getText().toString());

                        int total = parseWithDefault(shower.getText().toString().trim(),0)+parseWithDefault(toilet.getText().toString().trim(),0)+
                                parseWithDefault(drinking.getText().toString().trim(),0)+parseWithDefault(hygene.getText().toString().trim(),0)+
                                parseWithDefault(cooking.getText().toString().trim(),0)+parseWithDefault(cleaning.getText().toString().trim(),0)+
                                parseWithDefault(dishes.getText().toString().trim(),0)+parseWithDefault(laundry.getText().toString().trim(),0)+
                                parseWithDefault(other.getText().toString().trim(),0);

                        addData(shower.getText().toString(),toilet.getText().toString()
                                ,drinking.getText().toString(),hygene.getText().toString(),
                                laundry.getText().toString(),dishes.getText().toString(),cooking.getText().toString(),cleaning.getText().toString(),
                                other.getText().toString(),Integer.toString(total));

                        Intent intent2 = new Intent(DiaryActivity.this,Databaselist.class);
                        startActivity(intent2);
                    }
                }
        );



        shower.addTextChangedListener(new TextWatcher() {




                private String s;
                private long after;
                private Thread t;
                private Runnable runnable_EditTextWatcher = new Runnable() {
                    @Override
                    public void run() {
                        while (true) {
                            if ((System.currentTimeMillis() - after) > 600)
                            {
                                Log.d("Debug_EditTEXT_watcher", "(System.currentTimeMillis()-after)>600 ->  " + (System.currentTimeMillis() - after) + " > " + s);
                             /*   Log.v("App", shower.getText().toString());
                                Log.v("App", toilet.getText().toString());
                                Log.v("App", dishes.getText().toString());
                                Log.v("App", hygene.getText().toString());
                                Log.v("App", cleaning.getText().toString());
                                Log.v("App", cooking.getText().toString());
                                Log.v("App", drinking.getText().toString());
                                Log.v("App", laundry.getText().toString());
                                Log.v("App", other.getText().toString());*/






                                running_total = parseWithDefault(shower.getText().toString().trim(),0)+parseWithDefault(toilet.getText().toString().trim(),0)+
                                        parseWithDefault(drinking.getText().toString().trim(),0)+parseWithDefault(hygene.getText().toString().trim(),0)+
                                        parseWithDefault(cooking.getText().toString().trim(),0)+parseWithDefault(cleaning.getText().toString().trim(),0)+
                                        parseWithDefault(dishes.getText().toString().trim(),0)+parseWithDefault(laundry.getText().toString().trim(),0)+
                                        parseWithDefault(other.getText().toString().trim(),0);



                                runOnUiThread(new Runnable() {

                                    @Override
                                    public void run() {

                                        // Stuff that updates the UI
                                        total.setText(Integer.toString(running_total));
                                    }
                                });
                                t = null;
                                break;
                            }
                        }
                    }
                };










            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence ss, int start, int before, int count) {
                s = ss.toString();
            }

            @Override
            public void afterTextChanged(Editable ss) {
                after = System.currentTimeMillis();
                if (t == null)
                {
                    t = new Thread(runnable_EditTextWatcher);
                    t.start();
                }

            }
        });


        toilet.addTextChangedListener(new TextWatcher() {




            private String s;
            private long after;
            private Thread t;
            private Runnable runnable_EditTextWatcher = new Runnable() {
                @Override
                public void run() {
                    while (true) {
                        if ((System.currentTimeMillis() - after) > 600)
                        {
                            Log.d("Debug_EditTEXT_watcher", "(System.currentTimeMillis()-after)>600 ->  " + (System.currentTimeMillis() - after) + " > " + s);
                             /*   Log.v("App", shower.getText().toString());
                                Log.v("App", toilet.getText().toString());
                                Log.v("App", dishes.getText().toString());
                                Log.v("App", hygene.getText().toString());
                                Log.v("App", cleaning.getText().toString());
                                Log.v("App", cooking.getText().toString());
                                Log.v("App", drinking.getText().toString());
                                Log.v("App", laundry.getText().toString());
                                Log.v("App", other.getText().toString());*/






                            running_total = parseWithDefault(shower.getText().toString().trim(),0)+parseWithDefault(toilet.getText().toString().trim(),0)+
                                    parseWithDefault(drinking.getText().toString().trim(),0)+parseWithDefault(hygene.getText().toString().trim(),0)+
                                    parseWithDefault(cooking.getText().toString().trim(),0)+parseWithDefault(cleaning.getText().toString().trim(),0)+
                                    parseWithDefault(dishes.getText().toString().trim(),0)+parseWithDefault(laundry.getText().toString().trim(),0)+
                                    parseWithDefault(other.getText().toString().trim(),0);



                            runOnUiThread(new Runnable() {

                                @Override
                                public void run() {

                                    // Stuff that updates the UI
                                    total.setText(Integer.toString(running_total));
                                }
                            });
                            t = null;
                            break;
                        }
                    }
                }
            };










            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence ss, int start, int before, int count) {
                s = ss.toString();
            }

            @Override
            public void afterTextChanged(Editable ss) {
                after = System.currentTimeMillis();
                if (t == null)
                {
                    t = new Thread(runnable_EditTextWatcher);
                    t.start();
                }

            }
        });

        drinking.addTextChangedListener(new TextWatcher() {




            private String s;
            private long after;
            private Thread t;
            private Runnable runnable_EditTextWatcher = new Runnable() {
                @Override
                public void run() {
                    while (true) {
                        if ((System.currentTimeMillis() - after) > 600)
                        {
                            Log.d("Debug_EditTEXT_watcher", "(System.currentTimeMillis()-after)>600 ->  " + (System.currentTimeMillis() - after) + " > " + s);
                             /*   Log.v("App", shower.getText().toString());
                                Log.v("App", toilet.getText().toString());
                                Log.v("App", dishes.getText().toString());
                                Log.v("App", hygene.getText().toString());
                                Log.v("App", cleaning.getText().toString());
                                Log.v("App", cooking.getText().toString());
                                Log.v("App", drinking.getText().toString());
                                Log.v("App", laundry.getText().toString());
                                Log.v("App", other.getText().toString());*/






                            running_total = parseWithDefault(shower.getText().toString().trim(),0)+parseWithDefault(toilet.getText().toString().trim(),0)+
                                    parseWithDefault(drinking.getText().toString().trim(),0)+parseWithDefault(hygene.getText().toString().trim(),0)+
                                    parseWithDefault(cooking.getText().toString().trim(),0)+parseWithDefault(cleaning.getText().toString().trim(),0)+
                                    parseWithDefault(dishes.getText().toString().trim(),0)+parseWithDefault(laundry.getText().toString().trim(),0)+
                                    parseWithDefault(other.getText().toString().trim(),0);



                            runOnUiThread(new Runnable() {

                                @Override
                                public void run() {

                                    // Stuff that updates the UI
                                    total.setText(Integer.toString(running_total));
                                }
                            });
                            t = null;
                            break;
                        }
                    }
                }
            };










            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence ss, int start, int before, int count) {
                s = ss.toString();
            }

            @Override
            public void afterTextChanged(Editable ss) {
                after = System.currentTimeMillis();
                if (t == null)
                {
                    t = new Thread(runnable_EditTextWatcher);
                    t.start();
                }

            }
        });

        dishes.addTextChangedListener(new TextWatcher() {




            private String s;
            private long after;
            private Thread t;
            private Runnable runnable_EditTextWatcher = new Runnable() {
                @Override
                public void run() {
                    while (true) {
                        if ((System.currentTimeMillis() - after) > 600)
                        {
                            Log.d("Debug_EditTEXT_watcher", "(System.currentTimeMillis()-after)>600 ->  " + (System.currentTimeMillis() - after) + " > " + s);
                             /*   Log.v("App", shower.getText().toString());
                                Log.v("App", toilet.getText().toString());
                                Log.v("App", dishes.getText().toString());
                                Log.v("App", hygene.getText().toString());
                                Log.v("App", cleaning.getText().toString());
                                Log.v("App", cooking.getText().toString());
                                Log.v("App", drinking.getText().toString());
                                Log.v("App", laundry.getText().toString());
                                Log.v("App", other.getText().toString());*/






                            running_total = parseWithDefault(shower.getText().toString().trim(),0)+parseWithDefault(toilet.getText().toString().trim(),0)+
                                    parseWithDefault(drinking.getText().toString().trim(),0)+parseWithDefault(hygene.getText().toString().trim(),0)+
                                    parseWithDefault(cooking.getText().toString().trim(),0)+parseWithDefault(cleaning.getText().toString().trim(),0)+
                                    parseWithDefault(dishes.getText().toString().trim(),0)+parseWithDefault(laundry.getText().toString().trim(),0)+
                                    parseWithDefault(other.getText().toString().trim(),0);



                            runOnUiThread(new Runnable() {

                                @Override
                                public void run() {

                                    // Stuff that updates the UI
                                    total.setText(Integer.toString(running_total));
                                }
                            });
                            t = null;
                            break;
                        }
                    }
                }
            };










            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence ss, int start, int before, int count) {
                s = ss.toString();
            }

            @Override
            public void afterTextChanged(Editable ss) {
                after = System.currentTimeMillis();
                if (t == null)
                {
                    t = new Thread(runnable_EditTextWatcher);
                    t.start();
                }

            }
        });

        laundry.addTextChangedListener(new TextWatcher() {




            private String s;
            private long after;
            private Thread t;
            private Runnable runnable_EditTextWatcher = new Runnable() {
                @Override
                public void run() {
                    while (true) {
                        if ((System.currentTimeMillis() - after) > 600)
                        {
                            Log.d("Debug_EditTEXT_watcher", "(System.currentTimeMillis()-after)>600 ->  " + (System.currentTimeMillis() - after) + " > " + s);
                             /*   Log.v("App", shower.getText().toString());
                                Log.v("App", toilet.getText().toString());
                                Log.v("App", dishes.getText().toString());
                                Log.v("App", hygene.getText().toString());
                                Log.v("App", cleaning.getText().toString());
                                Log.v("App", cooking.getText().toString());
                                Log.v("App", drinking.getText().toString());
                                Log.v("App", laundry.getText().toString());
                                Log.v("App", other.getText().toString());*/






                            running_total = parseWithDefault(shower.getText().toString().trim(),0)+parseWithDefault(toilet.getText().toString().trim(),0)+
                                    parseWithDefault(drinking.getText().toString().trim(),0)+parseWithDefault(hygene.getText().toString().trim(),0)+
                                    parseWithDefault(cooking.getText().toString().trim(),0)+parseWithDefault(cleaning.getText().toString().trim(),0)+
                                    parseWithDefault(dishes.getText().toString().trim(),0)+parseWithDefault(laundry.getText().toString().trim(),0)+
                                    parseWithDefault(other.getText().toString().trim(),0);



                            runOnUiThread(new Runnable() {

                                @Override
                                public void run() {

                                    // Stuff that updates the UI
                                    total.setText(Integer.toString(running_total));
                                }
                            });
                            t = null;
                            break;
                        }
                    }
                }
            };










            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence ss, int start, int before, int count) {
                s = ss.toString();
            }

            @Override
            public void afterTextChanged(Editable ss) {
                after = System.currentTimeMillis();
                if (t == null)
                {
                    t = new Thread(runnable_EditTextWatcher);
                    t.start();
                }

            }
        });

        hygene.addTextChangedListener(new TextWatcher() {




            private String s;
            private long after;
            private Thread t;
            private Runnable runnable_EditTextWatcher = new Runnable() {
                @Override
                public void run() {
                    while (true) {
                        if ((System.currentTimeMillis() - after) > 600)
                        {
                            Log.d("Debug_EditTEXT_watcher", "(System.currentTimeMillis()-after)>600 ->  " + (System.currentTimeMillis() - after) + " > " + s);
                             /*   Log.v("App", shower.getText().toString());
                                Log.v("App", toilet.getText().toString());
                                Log.v("App", dishes.getText().toString());
                                Log.v("App", hygene.getText().toString());
                                Log.v("App", cleaning.getText().toString());
                                Log.v("App", cooking.getText().toString());
                                Log.v("App", drinking.getText().toString());
                                Log.v("App", laundry.getText().toString());
                                Log.v("App", other.getText().toString());*/






                            running_total = parseWithDefault(shower.getText().toString().trim(),0)+parseWithDefault(toilet.getText().toString().trim(),0)+
                                    parseWithDefault(drinking.getText().toString().trim(),0)+parseWithDefault(hygene.getText().toString().trim(),0)+
                                    parseWithDefault(cooking.getText().toString().trim(),0)+parseWithDefault(cleaning.getText().toString().trim(),0)+
                                    parseWithDefault(dishes.getText().toString().trim(),0)+parseWithDefault(laundry.getText().toString().trim(),0)+
                                    parseWithDefault(other.getText().toString().trim(),0);



                            runOnUiThread(new Runnable() {

                                @Override
                                public void run() {

                                    // Stuff that updates the UI
                                    total.setText(Integer.toString(running_total));
                                }
                            });
                            t = null;
                            break;
                        }
                    }
                }
            };










            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence ss, int start, int before, int count) {
                s = ss.toString();
            }

            @Override
            public void afterTextChanged(Editable ss) {
                after = System.currentTimeMillis();
                if (t == null)
                {
                    t = new Thread(runnable_EditTextWatcher);
                    t.start();
                }

            }
        });

        cooking.addTextChangedListener(new TextWatcher() {




            private String s;
            private long after;
            private Thread t;
            private Runnable runnable_EditTextWatcher = new Runnable() {
                @Override
                public void run() {
                    while (true) {
                        if ((System.currentTimeMillis() - after) > 600)
                        {
                            Log.d("Debug_EditTEXT_watcher", "(System.currentTimeMillis()-after)>600 ->  " + (System.currentTimeMillis() - after) + " > " + s);
                             /*   Log.v("App", shower.getText().toString());
                                Log.v("App", toilet.getText().toString());
                                Log.v("App", dishes.getText().toString());
                                Log.v("App", hygene.getText().toString());
                                Log.v("App", cleaning.getText().toString());
                                Log.v("App", cooking.getText().toString());
                                Log.v("App", drinking.getText().toString());
                                Log.v("App", laundry.getText().toString());
                                Log.v("App", other.getText().toString());*/






                            running_total = parseWithDefault(shower.getText().toString().trim(),0)+parseWithDefault(toilet.getText().toString().trim(),0)+
                                    parseWithDefault(drinking.getText().toString().trim(),0)+parseWithDefault(hygene.getText().toString().trim(),0)+
                                    parseWithDefault(cooking.getText().toString().trim(),0)+parseWithDefault(cleaning.getText().toString().trim(),0)+
                                    parseWithDefault(dishes.getText().toString().trim(),0)+parseWithDefault(laundry.getText().toString().trim(),0)+
                                    parseWithDefault(other.getText().toString().trim(),0);



                            runOnUiThread(new Runnable() {

                                @Override
                                public void run() {

                                    // Stuff that updates the UI
                                    total.setText(Integer.toString(running_total));
                                }
                            });
                            t = null;
                            break;
                        }
                    }
                }
            };










            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence ss, int start, int before, int count) {
                s = ss.toString();
            }

            @Override
            public void afterTextChanged(Editable ss) {
                after = System.currentTimeMillis();
                if (t == null)
                {
                    t = new Thread(runnable_EditTextWatcher);
                    t.start();
                }

            }
        });

        cleaning.addTextChangedListener(new TextWatcher() {




            private String s;
            private long after;
            private Thread t;
            private Runnable runnable_EditTextWatcher = new Runnable() {
                @Override
                public void run() {
                    while (true) {
                        if ((System.currentTimeMillis() - after) > 600)
                        {
                            Log.d("Debug_EditTEXT_watcher", "(System.currentTimeMillis()-after)>600 ->  " + (System.currentTimeMillis() - after) + " > " + s);
                             /*   Log.v("App", shower.getText().toString());
                                Log.v("App", toilet.getText().toString());
                                Log.v("App", dishes.getText().toString());
                                Log.v("App", hygene.getText().toString());
                                Log.v("App", cleaning.getText().toString());
                                Log.v("App", cooking.getText().toString());
                                Log.v("App", drinking.getText().toString());
                                Log.v("App", laundry.getText().toString());
                                Log.v("App", other.getText().toString());*/






                            running_total = parseWithDefault(shower.getText().toString().trim(),0)+parseWithDefault(toilet.getText().toString().trim(),0)+
                                    parseWithDefault(drinking.getText().toString().trim(),0)+parseWithDefault(hygene.getText().toString().trim(),0)+
                                    parseWithDefault(cooking.getText().toString().trim(),0)+parseWithDefault(cleaning.getText().toString().trim(),0)+
                                    parseWithDefault(dishes.getText().toString().trim(),0)+parseWithDefault(laundry.getText().toString().trim(),0)+
                                    parseWithDefault(other.getText().toString().trim(),0);



                            runOnUiThread(new Runnable() {

                                @Override
                                public void run() {

                                    // Stuff that updates the UI
                                    total.setText(Integer.toString(running_total));
                                }
                            });
                            t = null;
                            break;
                        }
                    }
                }
            };










            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence ss, int start, int before, int count) {
                s = ss.toString();
            }

            @Override
            public void afterTextChanged(Editable ss) {
                after = System.currentTimeMillis();
                if (t == null)
                {
                    t = new Thread(runnable_EditTextWatcher);
                    t.start();
                }

            }
        });

        other.addTextChangedListener(new TextWatcher() {




            private String s;
            private long after;
            private Thread t;
            private Runnable runnable_EditTextWatcher = new Runnable() {
                @Override
                public void run() {
                    while (true) {
                        if ((System.currentTimeMillis() - after) > 600)
                        {
                            Log.d("Debug_EditTEXT_watcher", "(System.currentTimeMillis()-after)>600 ->  " + (System.currentTimeMillis() - after) + " > " + s);
                             /*   Log.v("App", shower.getText().toString());
                                Log.v("App", toilet.getText().toString());
                                Log.v("App", dishes.getText().toString());
                                Log.v("App", hygene.getText().toString());
                                Log.v("App", cleaning.getText().toString());
                                Log.v("App", cooking.getText().toString());
                                Log.v("App", drinking.getText().toString());
                                Log.v("App", laundry.getText().toString());
                                Log.v("App", other.getText().toString());*/






                            running_total = parseWithDefault(shower.getText().toString().trim(),0)+parseWithDefault(toilet.getText().toString().trim(),0)+
                                    parseWithDefault(drinking.getText().toString().trim(),0)+parseWithDefault(hygene.getText().toString().trim(),0)+
                                    parseWithDefault(cooking.getText().toString().trim(),0)+parseWithDefault(cleaning.getText().toString().trim(),0)+
                                    parseWithDefault(dishes.getText().toString().trim(),0)+parseWithDefault(laundry.getText().toString().trim(),0)+
                                    parseWithDefault(other.getText().toString().trim(),0);



                            runOnUiThread(new Runnable() {

                                @Override
                                public void run() {

                                    // Stuff that updates the UI
                                    total.setText(Integer.toString(running_total));
                                }
                            });
                            t = null;
                            break;
                        }
                    }
                }
            };










            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence ss, int start, int before, int count) {
                s = ss.toString();
            }

            @Override
            public void afterTextChanged(Editable ss) {
                after = System.currentTimeMillis();
                if (t == null)
                {
                    t = new Thread(runnable_EditTextWatcher);
                    t.start();
                }

            }
        });


    }

    public static int parseWithDefault(String number, int defaultVal) {
        try {
            return Integer.parseInt(number);
        } catch (NumberFormatException e) {
            return defaultVal;
        }
    }

    public void openactivity(){
        Intent intent1 = new Intent(this, DiaryEntry.class);
        startActivity(intent1);
    }

    public void addData(String shower,String toilet, String drinking, String hygiene, String laundry, String dishes, String cooking, String cleaning, String other, String total)
    {
        boolean insertdata = mdatabasehelper.addData(shower,toilet,drinking,hygiene,laundry,dishes,cooking,cleaning,other,total);
        if (insertdata){
            toastmessage("data inserted succesfully");
        }else{
            toastmessage("data insertion failed");
        }
    }

    public void toastmessage(String string){
        Toast.makeText(this,string,Toast.LENGTH_SHORT).show();
    }


}
