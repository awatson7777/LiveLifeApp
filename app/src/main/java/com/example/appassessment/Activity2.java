package com.example.appassessment;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.media.AudioAttributes;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Build;
import android.os.Bundle;
import android.os.SystemClock;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Chronometer;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.time.Clock;

public class Activity2 extends AppCompatActivity {

    //vars

    private Chronometer chronometer; // a simple timer layout
    private boolean running;
    private long pauseDigit;
    private SoundPool sounds;
    private int ClockTick;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_2);


        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) { // calls the audio attribute builder if the build version is at least lollipop
            AudioAttributes audioAttributes = new AudioAttributes.Builder()
                    .setContentType(AudioAttributes.CONTENT_TYPE_SONIFICATION) // used when the sound is tied with an action, my implementation being a button click
                    .setUsage(AudioAttributes.USAGE_ASSISTANCE_SONIFICATION)
                    .build();
            sounds = new SoundPool.Builder() // this constructor builds a sound pool which contains all audio instances included in the activity
                    .setMaxStreams(3) // allows 3 separate audio files to play at once
                    .setAudioAttributes(audioAttributes) // allows me to define the behaviour of the audio playing
                    .build();
        }
        ClockTick = sounds.load(this, R.raw.clock, 1); // assigns the sound from the raw folder to the variable name
        chronometer = findViewById(R.id.chronometer); // assigns the variable to the chronometer
    }

    public void startChronometer(View v) { 
if (!running) {
    chronometer.start(); // this enables the chronometer to run
    chronometer.setBase(SystemClock.elapsedRealtime() - pauseDigit); // sets the starting value
    running = true;
    sounds.play(ClockTick, 1, 1, 0, -1, 1); // plays the audio file with the configured attributes, -1 enables an infinite loop
    showToast1(); // calls function
}
    }

    public void pauseChronometer(View v) {
        if (running) {
            chronometer.stop(); // pauses the chronometer
            pauseDigit = SystemClock.elapsedRealtime() - chronometer.getBase(); // recalls the starting value and pauses it on the current value
            running = false;
            sounds.pause(ClockTick); // pauses the audio playing
            showToast2();
        }
    }

    public void resetChronometer(View v) {
        chronometer.setBase(SystemClock.elapsedRealtime());
        pauseDigit = 0; // sets the timer back to 0
        sounds.pause(ClockTick);
        showToast3();
    }

    @Override
    protected  void onDestroy() { // this destroys the audio instance when not needed from the active variables to not use unneeded resources that can slow performance
        super.onDestroy();
        sounds.release();
        sounds = null;
    }

    //The custom toasts that popup when buttons clicked

    public void showToast1() {
        LayoutInflater inflater = getLayoutInflater(); // layout inflater represents an xml file to display the layout within the activity
        View layout = inflater.inflate(R.layout.toast, (ViewGroup) findViewById(R.id.toast1)); // finds the referenced xml layout file
        Toast toast = new Toast(getApplicationContext());
        toast.setGravity(Gravity.BOTTOM,0,0); // places the toast on the bottom of the screen
        toast.setDuration(Toast.LENGTH_LONG); // duration on the screen
        toast.setView(layout); // calls the layout file
        toast.show(); // displays the layout file
        TextView toastText = layout.findViewById(R.id.toast_text); // displays the text of the layout file
        ImageView toastImage = layout.findViewById(R.id.toast_image); // displays the image of the layout file
        toastText.setText("Stopwatch Started"); // establishes what the text is
        toastImage.setImageResource(R.drawable.ic_toast); // establishes what the image is
    }

    public void showToast2() {
        LayoutInflater inflater = getLayoutInflater(); // refer to showToast1()
        View layout = inflater.inflate(R.layout.toast, (ViewGroup) findViewById(R.id.toast1));
        Toast toast = new Toast(getApplicationContext());
        toast.setGravity(Gravity.BOTTOM,0,0);
        toast.setDuration(Toast.LENGTH_LONG);
        toast.setView(layout);
        toast.show();
        TextView toastText = layout.findViewById(R.id.toast_text);
        ImageView toastImage = layout.findViewById(R.id.toast_image);
        toastText.setText("Stopwatch Paused");
        toastImage.setImageResource(R.drawable.ic_toast);
    }
    public void showToast3() {
        LayoutInflater inflater = getLayoutInflater(); // refer to showToast1()
        View layout = inflater.inflate(R.layout.toast, (ViewGroup) findViewById(R.id.toast1));
        Toast toast = new Toast(getApplicationContext());
        toast.setGravity(Gravity.BOTTOM,0,0);
        toast.setDuration(Toast.LENGTH_LONG);
        toast.setView(layout);
        toast.show();
        TextView toastText = layout.findViewById(R.id.toast_text);
        ImageView toastImage = layout.findViewById(R.id.toast_image);
        toastText.setText("Stopwatch Reset");
        toastImage.setImageResource(R.drawable.ic_toast);
    }

}
