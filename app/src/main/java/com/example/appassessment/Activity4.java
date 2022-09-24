package com.example.appassessment;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Locale;

public class Activity4<MyTimer> extends AppCompatActivity {
private static final long START_TIME_MS = 3600000; // sets the timer value at 1 hour by default

    // vars //

        private TextView TextViewTimer;
        private Button StartPauseButton;
        private Button ResetButton;
        private MyTimer MyTimer;
        private boolean TimerIsRunning;
        private long TimeInMS = START_TIME_MS;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_4);

        // set attributes //

        TextViewTimer = findViewById(R.id.text_view_timer);
        StartPauseButton = findViewById(R.id.start_pause);
        ResetButton = findViewById(R.id.reset_button);

        StartPauseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (TimerIsRunning) {
                    try { // try sets the following code to be tested for errors while the if statement is being executed
                        pauseTimer();
                    } catch (InterruptedException exception) { // catch is executed if an error occurs in the tested statement
                        exception.printStackTrace(); // throwable class that prints alongside the other details within the exception
                    }
                } else {
                    startTimer(); // calls function
                }
            }
        });

        ResetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resetTimer();
            }
        }); // on reset click, it calls the functions

        updateCountDown();
    }
    private void startTimer() {
        MyTimer = (MyTimer) new CountDownTimer(TimeInMS, 1000) { // casts to MyTimer and counts down in 1 second intervals
            @Override
            public void onTick(long msUntilFinished) { // synchronization to ensure each call is finished before a previous one is complete
            TimeInMS = msUntilFinished;
            updateCountDown();
            }
            @Override
            public void onFinish() { // when timer is finished (= 00:00) the call is finished
                TimerIsRunning = false;
                StartPauseButton.setText("Start");
                StartPauseButton.setVisibility(View.INVISIBLE);
                ResetButton.setVisibility(View.VISIBLE);
            }
        }.start();
        showToast1();

        TimerIsRunning = true;
        StartPauseButton.setText("pause");
        ResetButton.setVisibility(View.INVISIBLE);
    }
   private void pauseTimer() throws InterruptedException {
       MyTimer.wait(); //MyTimer.cancel(); should be the proper code but due to an issue with the cast an error occurs
        TimerIsRunning = false;
        StartPauseButton.setText("Start");
        ResetButton.setVisibility(View.VISIBLE);
    }

    /* private void pauseTimer() throws InterruptedException { // a different attempt to make the MyTime.cancel() work
        public void onTick(long msUntilFinished){
            TimeInMS = msUntilFinished;
            updateCountDown();
        }MyTimer.cancel();
        showToast1();
        TimerIsRunning = true;
        StartPauseButton.setText("pause");
        ResetButton.setVisibility(View.INVISIBLE);
    } */

     private void resetTimer() {
        TimeInMS = START_TIME_MS; // sets the time back to the original value
        updateCountDown();
        ResetButton.setVisibility(View.INVISIBLE);
        StartPauseButton.setVisibility(View.VISIBLE);
     }

     private void updateCountDown() {
        int minutes = (int) (TimeInMS / 1000) / 60; // updates the minutes uses a refresh of 1000 milliseconds
        int seconds = (int) (TimeInMS / 1000) % 60; // updates the seconds uses a refresh of 1000 milliseconds

        String timeLeft = String.format(Locale.getDefault(), "%02d:%02d", minutes, seconds); // get local timezone
        TextViewTimer.setText(timeLeft);

    }

    public void showToast1() { // custom toast
        LayoutInflater inflater = getLayoutInflater();
        View layout = inflater.inflate(R.layout.toast, (ViewGroup) findViewById(R.id.toast1));
        Toast toast = new Toast(getApplicationContext());
        toast.setGravity(Gravity.BOTTOM,0,0);
        toast.setDuration(Toast.LENGTH_LONG);
        toast.setView(layout);
        toast.show();
        TextView toastText = layout.findViewById(R.id.toast_text);
        ImageView toastImage = layout.findViewById(R.id.toast_image);
        toastText.setText("Timer Started!");
        toastImage.setImageResource(R.drawable.ic_toast);
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putLong("milliseconds_remaining", TimeInMS);
        outState.putBoolean("running", TimerIsRunning);
    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);

    }
}
