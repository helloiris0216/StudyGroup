package com.helloiris.study.group;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.net.Uri;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.NotificationCompat;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;
import com.google.gson.GsonBuilder;

/*
 * Package com.helloiris.study.group
 *
 * Module: FCMService
 *
 * -----------------------------------------------
 *
 * Created by Iris YEN on 2021/11/18
 * Last saved on Nov-19-2021 16:32
 * */

/**
 * The FirebaseService class for receiving messages from Firebase Cloud Messaging.
 *
 * android developer guide description:
 *      Extending this class is required to be able to handle downstream messages.
 *      It also provides functionality to automatically display notifications,
 *      and has methods that are invoked to give the status of upstream messages.
 *
 * Inherit {@link FirebaseMessagingService} class.
 *
 * see also:
 *      https://firebase.google.com/docs/reference/android/com/google/firebase/messaging/FirebaseMessagingService
 *      @see com.google.firebase.messaging.FirebaseMessagingService
 */
public class FCMService extends FirebaseMessagingService {
    // the log tag.
    private final String _TAG = Basic.getClassTag(FCMService.class);

    // intent key.
    public enum IntentKey {
        data_title(0) {
            @Override
            public int getValue(int value) {
                return 0;
            }
        },
        data_body(1),
        title(2),
        body(3);

        private int value;

        IntentKey(int value) {
            this.value = value;
        }


        public int getValue(int value) {
            return this.value = value;
        }
    }


    /**
     * There are two scenarios when onNewToken is called:
     * 1) When a new token is generated on initial app startup
     * 2) Whenever an existing token is changed
     * Under #2, there are three scenarios when the existing token is changed:
     * A) App is restored to a new device
     * B) User uninstalls/reinstalls the app
     * C) User clears app data
     *
     * Parameter:
     *      @param token the token used for sending messages to this application instance.
     */
    @Override
    public void onNewToken(@NonNull String token) {
        super.onNewToken(token);

        Log.i(_TAG, String.format("onNewToken -> content-value: {refresh token: %s}", token));
    }


    /**
     * This is also called when a notification message is received while the app is in the foreground.
     * The notification parameters can be retrieved with RemoteMessage.getNotification().
     *
     * Parameters:
     *      @param remoteMessage remote message that has been received.
     * */
    @Override
    public void onMessageReceived(@NonNull RemoteMessage remoteMessage) {
        super.onMessageReceived(remoteMessage);

        // Check if message contains a data payload.
        String dataTitle = null;
        if (remoteMessage.getData().size() > 0) {
            Log.d(_TAG, String.format("Message data payload: \n%s", new GsonBuilder().setPrettyPrinting().create().toJson(remoteMessage.getData())));

                dataTitle = remoteMessage.getData().get("data_title");
        }

        // Check if message contains a notification payload.
        if (remoteMessage.getNotification() != null) {
            Log.d(_TAG,  String.format("Message Notification: \n%s", new GsonBuilder().setPrettyPrinting().create().toJson(remoteMessage.getNotification())));

            // Also if you intend on generating your own notifications as a result of a received FCM
            // message, here is where that should be initiated. See sendNotification method below.
            String notificationTitle = remoteMessage.getNotification().getTitle();
            String notificationBody = remoteMessage.getNotification().getBody();
            Uri uri = remoteMessage.getNotification().getImageUrl();

            sendNotification(notificationTitle, notificationBody, uri, dataTitle);
        }
    }


    /**
     * Sending a notification.
     *
     * Parameters:
     *      @param title message title.
     *      @param content message content.
     *      @param imageUrl message image url.
     * */
    private void sendNotification(String title, String content, @Nullable Uri imageUrl, @Nullable String data) {
        // create activity used after intent object.
        Intent intent = new Intent().addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
        if (data != null) {
            intent.putExtra(IntentKey.data_title.toString(), data);
            intent.setClass(this, MainActivity.class);
        }

        // get returned activity object.
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);

        // build notification object.
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this, this.getResources().getString(R.string.fcm_channel_id))
                .setAutoCancel(true)
                .setContentIntent(pendingIntent)
                .setContentTitle(title)
                .setContentText(content)
                .setDefaults(Notification.DEFAULT_ALL)
                .setPriority(NotificationCompat.PRIORITY_MAX)
                .setVisibility(NotificationCompat.VISIBILITY_PRIVATE)
                .setSmallIcon(R.drawable.ic_notification)
                .setStyle(new NotificationCompat.BigTextStyle().bigText(content));

        if (imageUrl != null) {
            builder.setLargeIcon(Basic.getBitmapFromURL(imageUrl.toString()))
                    .setStyle(new NotificationCompat.BigPictureStyle()
                            .bigPicture(Basic.getBitmapFromURL(imageUrl.toString()))
                            .bigLargeIcon(null));
        }

        // use notification manager to send a notify.
        ((NotificationManager) this.getSystemService(NOTIFICATION_SERVICE)).notify(0, builder.build());

        Log.v(_TAG, "Send a notification");
    }
}
