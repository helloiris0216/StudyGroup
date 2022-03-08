package com.helloiris.study.group;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.res.ResourcesCompat;

import java.util.ArrayList;
import java.util.List;

/*
 * Package com.helloiris.study.group
 *
 * Module: MainActivity.java
 *
 * -----------------------------------------------
 *
 * Created by Iris YEN on 2021/11/18
 * Last saved on Mar-08-2021 23:19
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

    private String lastIndex = null;


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

        // todo: 測試用程式碼建立畫面和單選的功能.
        LinearLayout layout = this.findViewById(R.id.button_layout);
        List<Button> buttons = new ArrayList<>();

        View.OnClickListener listener = v -> {
            if (this.lastIndex == null) {
                v.setSelected(true);
                this.lastIndex = v.getTag().toString();
                return;
            }

            if (!this.lastIndex.equals(v.getTag().toString())) {
                buttons.get(Integer.parseInt(this.lastIndex)).setSelected(false);

            }

            v.setSelected(!v.isSelected());
            this.lastIndex = v.getTag().toString();
        };

        for (int i = 0; i < 3; i++) {
            Button btn = new Button(this);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            params.setMargins(0, 0, 0, 10);
            btn.setLayoutParams(params);
            btn.setBackground(ResourcesCompat.getDrawable(this.getResources(), R.drawable.selector_button, null));
            btn.setOnClickListener(listener);
            btn.setTag(String.valueOf(i));

            layout.addView(btn);
            buttons.add(i, btn);
        }
    }
}