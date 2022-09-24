package com.example.appassessment;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;

import static com.example.appassessment.MyApp.NOTIFY_CHANNEL;

public class Activity3 extends AppCompatActivity {

    //vars//

    private static final String FILE_NAME = "notes.txt";
private NotificationManagerCompat notificationManager; // assigns the variable with a compatibility library for the notification manager
    private EditText EditTextMemo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_3);

        EditTextMemo = findViewById(R.id.edit_text);
        notificationManager = NotificationManagerCompat.from(this); // calls a static function
    }

 public void sendToNotifyChannel(View v) {
        String memo = EditTextMemo.getText().toString();
     Notification notification = new NotificationCompat.Builder(this, NOTIFY_CHANNEL) // sends to MyAPP notification creator
             .setSmallIcon(R.drawable.ic_notfiy) // sets an icon for the notification bar
             .setContentText(memo)
             .build();

     notificationManager.notify(1, notification);

 }

    public void save(View v) {
        String text = EditTextMemo.getText().toString();
        FileOutputStream outputStream = null; // enables the text to be saved as bytes
        try {
            outputStream = openFileOutput(FILE_NAME, MODE_PRIVATE); // opens the private file within the context of the application package
            outputStream.write(text.getBytes()); // it writes data to a file as bytes
            EditTextMemo.getText().clear();
            showToast1();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (outputStream != null) {
                try {
                    outputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public void showToast1() {
        LayoutInflater inflater = getLayoutInflater();
        View layout = inflater.inflate(R.layout.toast, (ViewGroup) findViewById(R.id.toast1));
        Toast toast = new Toast(getApplicationContext());
        toast.setGravity(Gravity.BOTTOM,0,0);
        toast.setDuration(Toast.LENGTH_LONG);
        toast.setView(layout);
        toast.show();
        TextView toastText = layout.findViewById(R.id.toast_text);
        ImageView toastImage = layout.findViewById(R.id.toast_image);
        toastText.setText("Note saved to phone!");
        toastImage.setImageResource(R.drawable.ic_toast);
    }

    public void showToast2() {
        LayoutInflater inflater = getLayoutInflater();
        View layout = inflater.inflate(R.layout.toast, (ViewGroup) findViewById(R.id.toast1));
        Toast toast = new Toast(getApplicationContext());
        toast.setGravity(Gravity.BOTTOM,0,0);
        toast.setDuration(Toast.LENGTH_LONG);
        toast.setView(layout);
        toast.show();
        TextView toastText = layout.findViewById(R.id.toast_text);
        ImageView toastImage = layout.findViewById(R.id.toast_image);
        toastText.setText("Note loaded from phone!");
        toastImage.setImageResource(R.drawable.ic_toast);
    }



public void load(View v) {
            FileInputStream inputStream = null; // retrieves the input bytes within the system


try {
    inputStream = openFileInput(FILE_NAME);
    InputStreamReader streamReader = new InputStreamReader(inputStream); //bridges byte streams to character streams and shows the representation as a character
    BufferedReader buffer = new BufferedReader(streamReader); // reads text from the bridge of characters streams, buffers characters to provide reading of lines
    StringBuilder buildString = new StringBuilder(); // rebuilds the string, replaces the string buffer
    String text;
    showToast2();
    while ((text = buffer.readLine()) !=null) {
        buildString.append(text).append("\n");
    }
    EditTextMemo.setText(buildString.toString());
} catch (FileNotFoundException exception) {
    exception.printStackTrace();
} catch (IOException exception) {
    exception.printStackTrace();
} finally {
    if (inputStream != null) {
        try {
            inputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
    }
}
