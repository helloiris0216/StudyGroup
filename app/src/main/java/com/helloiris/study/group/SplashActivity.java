package com.helloiris.study.group;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

/*
 * Package com.helloiris.study.group
 *
 * Module: SplashActivity
 * copyright © 2021 OB APP 2.5 corporation. all rights reserved.
 * -----------------------------------------------
 *
 * Created by Iris YEN on 2021/11/19
 * Last saved on Nov-19-2021 11:39 AM
 * */

public class SplashActivity extends AppCompatActivity {
    // the log tag.
    private final String _TAG = Basic.getClassTag(SplashActivity.class);

    // duration of wait.
    final int SPLASH_DISPLAY_LENGTH = 1500;


    /**
     * Called when the activity is starting.
     *
     * Parameters:
     *      @param savedInstanceState if the activity is being re-initialized after previously being shut down
     *                                then this Bundle contains the data it most recently supplied in onSaveInstanceState(Bundle).
     * */
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        this.setTheme(R.style.SplashTheme);
        super.onCreate(savedInstanceState);
        Log.d(_TAG, "onCreate");

        /* New Handler to start the Menu-Activity
         * and close this Splash-Screen after some seconds.*/
        new Handler().postDelayed(() ->
        {
            SplashActivity.this.startActivity(new Intent(SplashActivity.this, MainActivity.class));
            SplashActivity.this.finish();
        }, SPLASH_DISPLAY_LENGTH);

        if (this.getIntent() != null) {
            if (this.getIntent().getStringExtra("data_title") != null) {
                Log.v(_TAG, String.format("onStart -> FCM data -> content-value: {data = %s}", this.getIntent().getStringExtra("data_title")));
            }
        }
    }


    /**
     * Called after onRestoreInstanceState(Bundle), onRestart(), or onPause()
     * for your activity to start interacting with the user.
     * */
    @Override
    protected void onResume() {
        super.onResume();
        Log.d(_TAG, "onResume");

        // 當 app 在背景接收到通知時，在這邊取得 data，key 要寫通知內的 json key.
        if (this.getIntent() == null) return;
        if (this.getIntent().getStringExtra("data_title") != null) {
            Log.v(_TAG, String.format("onResume -> FCM data -> content-value: {data = %s}", this.getIntent().getStringExtra("data_title")));
        }
    }


    /**
     * This is called for activities that set launchMode to "singleTop" in
     * their package, or if a client used the {@link Intent#FLAG_ACTIVITY_SINGLE_TOP}
     * flag when calling {@link #startActivity}.  In either case, when the
     * activity is re-launched while at the top of the activity stack instead
     * of a new instance of the activity being started, onNewIntent() will be
     * called on the existing instance with the Intent that was used to
     * re-launch it.
     *
     * <p>An activity can never receive a new intent in the resumed state. You can count on
     * {@link #onResume} being called after this method, though not necessarily immediately after
     * the completion this callback. If the activity was resumed, it will be paused and new intent
     * will be delivered, followed by {@link #onResume}. If the activity wasn't in the resumed
     * state, then new intent can be delivered immediately, with {@link #onResume()} called
     * sometime later when activity becomes active again.
     *
     * <p>Note that {@link #getIntent} still returns the original Intent.
     * You can use {@link #setIntent} to update it to this new Intent.
     *
     * Parameter:
     *      @param intent The new intent that was started for the activity.
     */
    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);

        setIntent(intent);

        Log.v(_TAG, String.format("onNewIntent -> FCM data -> content-value: {data = %s}", intent.getStringExtra(FCMService.IntentKey.data_title.toString())));
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(_TAG, "onDestroy");
    }
}
