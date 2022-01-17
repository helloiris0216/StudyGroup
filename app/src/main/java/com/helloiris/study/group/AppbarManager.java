package com.helloiris.study.group;

import android.util.Log;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class ActionbarManager {
    private static final String _TAG = "ActionbarManager";
    public static ActionbarManager actionbarManager;
    private AppCompatActivity context;
    private Toolbar actionBar;
    private Button favorites;
    private Button shoppingCart;

    private ActionbarManager(AppCompatActivity context) {
        this.context = context;
    }

    public static ActionbarManager getInstance(AppCompatActivity context) {
        if (actionbarManager == null) {
            actionbarManager = new ActionbarManager(context);
        }

        return actionbarManager;
    }


    public void initActionbar() {
        this.actionBar = this.context.findViewById(R.id.toolbar);

        this.context.setSupportActionBar(this.actionBar);

        this.favorites = this.actionBar.findViewById(R.id.favorites);
        this.shoppingCart = this.actionBar.findViewById(R.id.shopping_cart);

        this.favorites.setOnClickListener(v -> {
            Log.d(_TAG, "onClick: favorites");
        });

        this.shoppingCart.setOnClickListener(v -> {
            Log.d(_TAG, "onClick: shopping cart");
        });
    }
}