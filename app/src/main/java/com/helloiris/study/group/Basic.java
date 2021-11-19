package com.helloiris.study.group;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.NetworkOnMainThreadException;
import android.provider.Settings;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

import androidx.annotation.NonNull;
import androidx.core.app.NotificationManagerCompat;
import androidx.fragment.app.Fragment;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.StringWriter;
import java.io.Writer;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.zip.CRC32;

/*
 * Package: com.helloiris.study.group
 *
 * Module: Basic.java
 *
 * ----------------------------------------------------------
 *
 * Created by Iris YEN on 2021/11/18
 * Last saved on Nov-18-2021 16:23
 * */

/**
 *
 * Define the class of basic methods.
 *
 */
public final class Basic {
    // declare log tag symbol.
    private static final String _TAG = getClassTag(Basic.class);


    /**
     * To get the class tag.
     * Parameter:
     *      @param objectClass class
     *
     * Return:
     *      @return package name
     */
    public static String getClassTag(Class<?> objectClass) {
        return (objectClass.getName()).substring(25);
    }


    /**
     *
     * Get drawable image resource from url.
     * Warning: should use thread to call, otherwise occur exception.
     *
     * parameters:
     *      @param src drawable resource url.
     *
     * return:
     *      @return bitmap object.
     *
     * */
    public static Bitmap getBitmapFromURL(String src) {
        Bitmap result = null;

        try {
            // disable connect timeout.
            final int _TIMEOUT = 0;

            // create object instance, and settings properties.
            HttpURLConnection httpConn = (HttpURLConnection) (new URL(src)).openConnection();
            httpConn.setDoInput(true);
            httpConn.setConnectTimeout(_TIMEOUT);
            httpConn.setReadTimeout(_TIMEOUT);

            // set decode stream to bitmap.
            result = BitmapFactory.decodeStream(httpConn.getInputStream());
        }
        catch (NetworkOnMainThreadException | IOException ex) {
            // output to logcat with error type.
            Log.e(_TAG, String.format("get bitmap resource is failure, exception: %s", ex.getMessage()));
        }

        return result;
    }
}
