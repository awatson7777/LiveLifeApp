package com.example.appassessment;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.media.Image;
import android.media.SoundPool;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.sql.Time;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Timer;

public class MainActivity extends AppCompatActivity {
    private Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Thread liveTime = new Thread() {
            @Override
            public void run() { // the run method allows this thread to run as a separate object
                try {
                    while (!isInterrupted()) {
                        Thread.sleep(1000); // updates every second
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() { // sets the thread as a runnable action and runs it immediately
                                TextView tDate = (TextView) findViewById(R.id.view_time);// references the xml layout file
                                long date = System.currentTimeMillis(); // updates the systems time within milliseconds
                                SimpleDateFormat dateFormat = new SimpleDateFormat("hh-mm-ss a"); // sets the layout of the clock
                                String dateString = dateFormat.format(date); // provides the class methods needed to find the date format in local time
                                        tDate.setText(dateString);
                            }
                        });
                    }
                } catch (InterruptedException e) { // call this if the thread runs into an error
                }
            }
        };
        liveTime.start(); // calls to start the thread

        Calendar calendar = Calendar.getInstance();
        String currentDate = DateFormat.getDateInstance(DateFormat.FULL).format(calendar.getTime()); // provides the class methods needed to find the date format in local
        TextView ViewDate = findViewById(R.id.view_date); // references the xml layout file
        ViewDate.setText((currentDate));

        if(getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {
        } else if(getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE); // allows the activity to configure to separate layouts if orientation is changed

        allButtons();
    }
    public void allButtons() { // cleans up the code by placing all button instances inside a function
        findViewById(R.id.button).setOnClickListener(buttonClickListener); // finds the button being referenced and sets instance on click
        findViewById(R.id.button1).setOnClickListener(buttonClickListener);
        findViewById(R.id.button2).setOnClickListener(buttonClickListener);
        findViewById(R.id.button3).setOnClickListener(buttonClickListener);
    }
      private View.OnClickListener buttonClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switch (view.getId()) {
                    case R.id.button:
                        openActivity2(); // when button is clicked it opens the different activity function
                        break;
                    case R.id.button1:
                        openActivity3();
                        break;
                    case R.id.button2:
                        openActivity4();
                        break;
                    case R.id.button3:
                        openActivity5();
                }
            }

            public void openActivity2() {
                Intent intent = new Intent(MainActivity.this, Activity2.class); // the intent is used here to link the separate activities
                startActivity(intent); // calls the intent and runs the linked activity
            }


            public void openActivity3() {
                Intent intent = new Intent(MainActivity.this, Activity3.class);
                startActivity(intent);
          }

          public void openActivity4() {
              Intent intent = new Intent(MainActivity.this, Activity4.class);
              startActivity(intent);
          }

          public void openActivity5() {
              Intent intent = new Intent(MainActivity.this, Activity5.class);
              startActivity(intent);
          }

        };
    }