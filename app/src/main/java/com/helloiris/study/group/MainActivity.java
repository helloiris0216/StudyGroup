package com.helloiris.study.group;

import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

/*
 * Package com.helloiris.study.group
 *
 * Module: MainActivity.java
 *
 * -----------------------------------------------
 *
 * Created by Iris YEN on 2021/11/18
 * Last saved on Nov-19-2021 16:35
 * */

/**
 * The study group main activity class.
 *
 * android developer guide description:
 *      Base class for activities that wish to use some of the newer platform features on older Android devices.
 *
 * Inherit {@link AppCompatActivity} class.
 * Implement ShakeManager.ShakeListener
 *
 * see also:
 *      https://developer.android.com/reference/androidx/appcompat/app/AppCompatActivity
 *      @see android.app.Activity (lifecycle part)
 *      @see androidx.appcompat.app.AppCompatActivity
 * */
public class MainActivity extends AppCompatActivity {
    // the log tag.
    private final String _TAG = Basic.getClassTag(MainActivity.class);


    /**
     * Called when the activity is starting.
     *
     * Parameters:
     *      @param savedInstanceState if the activity is being re-initialized after previously being shut down
     *                                then this Bundle contains the data it most recently supplied in onSaveInstanceState(Bundle).
     * */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        String logTag = "onCreate";

        Button selectorBtn = this.findViewById(R.id.selector_btn);
        selectorBtn.setOnClickListener(v -> {
            selectorBtn.setSelected(true);
            Log.d(_TAG, "onCreate: pressed!");
        });

        TextView tv = this.findViewById(R.id.text);
        tv.setOnClickListener(v -> selectorBtn.setSelected(false));
    }


    @Override
    protected void onStart() {
        super.onStart();
    }

    /**
     * Called after onRestoreInstanceState(Bundle), onRestart(), or onPause()
     * for your activity to start interacting with the user.
     * */
    @Override
    protected void onResume() {
        super.onResume();
        Log.d(_TAG, "onResume");
    }


    /**
     * Called when you are no longer visible to the user.
     */
    @Override
    protected void onStop() {
        super.onStop();
    }
}