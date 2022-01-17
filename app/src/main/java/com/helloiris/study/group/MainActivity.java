package com.helloiris.study.group;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.appbar.MaterialToolbar;

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

    // a flag when the device receives the notification data from the Intent.
    private boolean hasNotificationData = false;


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


//        // create a notification channel for the Android Oreo+
//        // if you don't, the device won't get the notification in the foreground.
//        createNotificationChannel();
//
//        // get the FCM token.
//        FirebaseMessaging.getInstance().getToken().addOnCompleteListener(task -> Log.i(_TAG, String.format("%s -> FCM token: %s", logTag, task.getResult())));

//        onNewIntent(getIntent());


//        ActionBar actionBar = getActionBar();
//        actionBar.setCustomView(R.layout.view_actionbar);
//        actionBar.setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
//        actionBar.setBackgroundDrawable(new ColorDrawable(0xF505050));
    }


    @Override
    protected void onStart() {
        super.onStart();

        MaterialToolbar toolbar = this.findViewById(R.id.actionbar);
        this.setSupportActionBar(toolbar);

        this.getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    /**
     * Called after onRestoreInstanceState(Bundle), onRestart(), or onPause()
     * for your activity to start interacting with the user.
     * */
    @Override
    protected void onResume() {
        super.onResume();
        Log.d(_TAG, "onResume");

//        // 當 app 在背景接收到通知時，在這邊取得 data，key 要寫通知內的 json key.
//        if (this.getIntent() == null | this.hasNotificationData) return;
//        if (this.getIntent().getStringExtra(FCMService.IntentKey.data_title.toString()) != null) {
//            String dataTitle = this.getIntent().getStringExtra(FCMService.IntentKey.data_title.toString());
//            Log.v(_TAG, String.format("onResume -> FCM data -> content-value: {data title = %s}", dataTitle));
//        }
    }


    /**
     * Called when you are no longer visible to the user.
     */
    @Override
    protected void onStop() {
        super.onStop();

        this.hasNotificationData = false;
        Log.d(_TAG, "onStop -> hasNotificationData = " + this.hasNotificationData);
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

        // 當 app 在背景接收到通知時，在這邊取得 data，key 要寫通知內的 json key.
        Log.v(_TAG, String.format("onNewIntent -> FCM data -> content-value: {data title = %s}",
                intent.getStringExtra(FCMService.IntentKey.data_title.toString())));
        this.hasNotificationData = true;
    }


    /**
     * To generate a channel for the notification.
     */
    public void createNotificationChannel() {
        // Create the NotificationChannel, but only on API 26+ because
        // the NotificationChannel class is new and not in the support library
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            CharSequence name = getString(R.string.channel_name);
            String description = getString(R.string.channel_description);
            int importance = NotificationManager.IMPORTANCE_DEFAULT;

            NotificationChannel channel = new NotificationChannel(this.getResources().getString(R.string.fcm_channel_id), name, importance);
            channel.setDescription(description);

            // Register the channel with the system; you can't change the importance
            // or other notification behaviors after this
            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }
    }
}