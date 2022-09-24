package com.example.appassessment;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;

import android.media.MediaPlayer;
import android.media.MediaRecorder;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.io.IOException;

public class Activity5 extends AppCompatActivity {

    //vars//

    private Button play;
    private Button record;
    private Button stop;
    private MediaRecorder AudioRecord;
    private String output;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_5);

        play = (Button) findViewById(R.id.play); // establishes the play button
        record = (Button) findViewById(R.id.record); // establishes the record button
        stop = (Button) findViewById(R.id.stop); // establishes the stop button
        stop.setEnabled(false);
        play.setEnabled(false);

        output = Environment.getExternalStorageDirectory().getAbsolutePath() + "recording";

        AudioRecord = new MediaRecorder(); // creates a new media recorder
        AudioRecord.setAudioSource(MediaRecorder.AudioSource.MIC); // uses mic as the standard audio source for the recorder
        AudioRecord.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP); // sets the output format as a 3GPP media file format
        AudioRecord.setAudioEncoder(MediaRecorder.OutputFormat.AMR_NB); // replaces the RAW_AMR file format from API Level 16
        AudioRecord.setOutputFile(output); // uses the output string

        record.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    AudioRecord.prepare(); // prepares the recorder to begin recording data
                    AudioRecord.start(); // starts the recording data process
                } catch (IllegalStateException ise) {

                } catch (IOException ioe) {

                }

                record.setEnabled(false);
                stop.setEnabled(true);

                Toast.makeText(getApplicationContext(), "Recording Audio", Toast.LENGTH_LONG).show();
            }
        });

        stop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AudioRecord.stop(); // transfers a methods state to the Stopped state
                AudioRecord.release(); // the object is no longer created
                AudioRecord = null;
                record.setEnabled(true);
                stop.setEnabled(false);
                play.setEnabled(true);

                Toast.makeText(getApplicationContext(), "Audio Recorded", Toast.LENGTH_LONG).show(); // sets a basic toast
;            }
        });

        play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MediaPlayer mediaPlayer = new MediaPlayer(); // creates a new media player
                try {
                    mediaPlayer.setDataSource(output);
                    mediaPlayer.prepare();
                    mediaPlayer.start();
                    Toast.makeText(getApplicationContext(), "Playing Recorded Audio", Toast.LENGTH_LONG).show();

                } catch (Exception e) {

                }
            }
        });
    }
}
