package com.helloiris.study.group;

import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class AppbarManager {
    private static final String _TAG = "AppbarManager";
    private static AppbarManager appbarManager;
    private Toolbar actionBar;
    private Button favorites;
    private Button shoppingCart;
    private TextView badgesTxt;
    private int badges = 0;


    private enum BadgeType {
        SHOPPING_CART,
        FAVORITES
    }

    private AppbarManager() {
    }


    public static synchronized AppbarManager getInstance() {
        if (appbarManager == null) {
            appbarManager = new AppbarManager();
        }

        return appbarManager;
    }


    public void initActionbar(AppCompatActivity activity) {
//        this.actionBar = activity.findViewById(R.id.toolbar);

        activity.setSupportActionBar(this.actionBar);

        this.favorites = this.actionBar.findViewById(R.id.favorites);
        this.shoppingCart = this.actionBar.findViewById(R.id.shopping_cart);
        this.badgesTxt = this.actionBar.findViewById(R.id.badge_shopping_cart);

        if (this.badges == 0) {
            this.badgesTxt.setVisibility(View.GONE);
        }
        else {
            this.badgesTxt.setVisibility(View.VISIBLE);
        }

        this.favorites.setOnClickListener(v -> {
            Log.v(_TAG, "onClick: favorites");
        });

        this.shoppingCart.setOnClickListener(v -> {
            Log.v(_TAG, "onClick: shopping cart");

            this.badges+=20;

            this.addBadge(BadgeType.SHOPPING_CART);

        });
    }


    private void addBadge(BadgeType badgeType) {
        Log.v(_TAG, "addBadge: badges = " + this.badges);

        switch (badgeType) {
            case FAVORITES:
                break;

            case SHOPPING_CART:
                this.badgesTxt.setVisibility(View.VISIBLE);
                this.badgesTxt.setText(String.valueOf(this.badges));
                break;
        }
    }
}