package com.aspl.fcm;

/**
 * Created by new on 11/17/2017.
 */

import android.annotation.TargetApi;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.Matrix;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import androidx.core.app.NotificationCompat;
import android.util.Log;
import android.view.View;

import com.aspl.mbs.R;
import com.aspl.mbs.SplaceScreen;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Map;

public class MyFirebaseMessagingService extends FirebaseMessagingService {

    private static final String TAG = "MyFirebaseMsgService";

    /**
     * Called when message is received.
     *
     * @param remoteMessage Object representing the message received from Firebase Cloud Messaging.
     */
    // [START receive_message]
    public static int NCount = 0;

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        // [START_EXCLUDE]
        // There are two types of messages data messages and notification messages. Data messages are handled
        // here in onMessageReceived whether the app is in the foreground or background. Data messages are the type
        // traditionally used with GCM. Notification messages are only received here in onMessageReceived when the app
        // is in the foreground. When the app is in the background an automatically generated notification is displayed.
        // When the user taps on the notification they are returned to the app. Messages containing both notification
        // and data payloads are treated as notification messages. The Firebase console always sends notification
        // messages. For more see: https://firebase.google.com/docs/cloud-messaging/concept-options
        // [END_EXCLUDE]

        // TODO(developer): Handle FCM messages here.
        // Not getting messages here? See why this may be: https://goo.gl/39bRNJ
        Log.d("fcm", "From: " + remoteMessage.getFrom());
        Log.d("fcm", "From: " + remoteMessage);

        // Check if message contains a data payload.
        if (remoteMessage.getData().size() > 0) {
            Log.d("fcm", "Message data payload: " + remoteMessage.getData());

            if (/* Check if data needs to be processed by long running job */ true) {
                // For long-running tasks (10 seconds or more) use Firebase Job Dispatcher.
                //scheduleJob();
            } else {
                // Handle message within 10 seconds
                handleNow();
            }

        }

        // Check if message contains a notification payload.
        if (remoteMessage.getNotification() != null) {
            Log.d("fcm", "Message Notification Body: " + remoteMessage);
            Log.d("fcm", "Message Notification Body: " + remoteMessage.getNotification().getBody());
           /* Log.d("fcm", "Message Notification Data: " + remoteMessage.getData().getOrDefault("data",""));
            Log.d("fcm", "Message Notification Data only: " + remoteMessage.getData());
*/            // Log.d("fcm", "Message Notification Title: " + remoteMessage.getNotification().getTitle());
            String name = "", id = "", title = "", jsonBody = "";

            title = remoteMessage.getNotification().getBody();
            jsonBody = remoteMessage.getNotification().getBody();
            String notificationData = remoteMessage.getData() + "";

            //remoteMessage.getNotification().getTitle();
            //remoteMessage.getNotification().getBody();


            /*try {
                JSONObject obj = new JSONObject(jsonBody);
                name = obj.getString("message");
                id = obj.getString("email");
                id = obj.getString("order_id");
            } catch (JSONException e) {
                e.printStackTrace();
            }*/

            /*assert title != null;
            title = (title.isEmpty()) ?  "MBS Notification" : title;
            if (name.isEmpty()){
                sendNotification(title,jsonBody);
            }else{
                sendNotification(title,name);
            }*/

            sendNotification(remoteMessage.getNotification().getTitle(), remoteMessage.getNotification().getBody(), remoteMessage.getData());
        }

        // Also if you intend on generating your own notifications as a result of a received FCM
        // message, here is where that should be initiated. See sendNotification method below.
    }
    // [END receive_message]

    /**
     * Schedule a job using FirebaseJobDispatcher.
     */
   /* private void scheduleJob() {
        // [START dispatch_job]
        FirebaseJobDispatcher dispatcher = new FirebaseJobDispatcher(new GooglePlayDriver(this));
        Job myJob = dispatcher.newJobBuilder()
                .setService(MyJobService.class)
                .set"fcm"("my-job-"fcm"")
                .build();
        dispatcher.schedule(myJob);
        // [END dispatch_job]
    }*/

    /**
     * Handle time allotted to BroadcastReceivers.
     */
    private void handleNow() {
        Log.d("fcm", "Short lived task is done.");
    }

    /**
     * Create and show a simple notification containing the received FCM message.
     *
     * @param messageBody FCM message body received.
     * @param data
     */
    private void sendNotification(String title, String messageBody, Map<String, String> data) {

        Log.e("Log", "Data=" + data + " -orderid=" + data.get("CustomerId"));
        //{CustomerId=189013, OrderId=1512}
        if(data.size()>0) {
            new generatePictureStyleNotification(this, title, messageBody,
                    data.get("logo").replace(" ", ""), data).execute();
        }
        /*Intent intent = new Intent(this, SplaceScreen.class);
        intent.putExtra("CustomerId",""+data.get("CustomerId"));
        intent.putExtra("OrderId",""+data.get("OrderId"));
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0 , intent,
                PendingIntent.FLAG_ONE_SHOT);

        String channelId = getString(R.string.default_notification_channel_id);
        Uri defaultSoundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        NotificationCompat.Builder notificationBuilder =
                new NotificationCompat.Builder(this, channelId)
                        .setSmallIcon(R.drawable.applogo)
                        .setContentTitle(title)
                        .setContentText(messageBody)
                        .setAutoCancel(true)
                        .setSound(defaultSoundUri)
                        .setContentIntent(pendingIntent);
        NotificationManager notificationManager =
                (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

        notificationManager.notify(0 , notificationBuilder.build());*/
    }

    public class generatePictureStyleNotification extends AsyncTask<String, Void, Bitmap> {

        private Context mContext;
        private String title, message, imageUrl;
        Map<String, String> data;

        public generatePictureStyleNotification(Context context, String title, String message, String imageUrl, Map<String, String> data) {
            super();
            this.mContext = context;
            this.title = title;
            this.message = message;
            this.imageUrl = imageUrl;
            this.data = data;
        }

        @Override
        protected Bitmap doInBackground(String... params) {

            InputStream in;
            try {
                URL url = new URL(this.imageUrl);
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setDoInput(true);
                connection.connect();
                in = connection.getInputStream();
                Bitmap myBitmap = BitmapFactory.decodeStream(in);
                return myBitmap;
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }

        @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
        @Override
        protected void onPostExecute(Bitmap result) {
            super.onPostExecute(result);
            //Drawable d = new BitmapDrawable(getResources(), result);

         /*   int w = result.getWidth() + ((result.getWidth() * 90) / 100);
            int h = result.getHeight() + ((result.getHeight() * 90) / 100);
            Log.e("Log","Width=="+w);
            Log.e("Log","Height=="+h);
           // result = getResizedBitmap(result, w, h);

            Log.e("Log", "Data=" + data + " -orderid=" + data.get("CustomerId"));
*/            //{CustomerId=189013, OrderId=1512}
            Intent intent = new Intent(MyFirebaseMessagingService.this, SplaceScreen.class);
            intent.putExtra("CustomerId", "" + data.get("CustomerId"));
            Log.e("Log", "Data URL=" + data.get("URL").replace(" ", ""));

            //intent.putExtra("URL", "cart");
            intent.putExtra("URL", "" + data.get("URL").replace(" ", ""));

            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

            int pendingIntentFlags = Build.VERSION.SDK_INT >= Build.VERSION_CODES.S
                    ? PendingIntent.FLAG_ONE_SHOT | PendingIntent.FLAG_IMMUTABLE
                    : PendingIntent.FLAG_ONE_SHOT;

            PendingIntent pendingIntent = PendingIntent.getActivity(MyFirebaseMessagingService.this, 0 /* Request code */, intent,
                    pendingIntentFlags);

            String channelId = getString(R.string.default_notification_channel_id);
            Uri defaultSoundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
            /*NotificationCompat.Builder notificationBuilder =
                    new NotificationCompat.Builder(MyFirebaseMessagingService.this, channelId)*/
            NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(
                    mContext);

            Notification notificationBuilder = mBuilder
                    .setSmallIcon(R.drawable.ic_launcher)
                    .setTicker(title)
                    .setContentTitle(title)
                    .setContentText(message)
                    .setAutoCancel(true)
                    .setColor(Color.parseColor("#126eb5"))//#126eb5
                    .setLargeIcon(result)
                    .setChannelId(channelId)
                    .setStyle(new NotificationCompat.BigTextStyle().bigText(message))
                   /* .setStyle(new Notification.BigPictureStyle().bigPicture(result))*/
                    .setSound(defaultSoundUri)
                    .setContentIntent(pendingIntent).build();
            //.setSmallIcon(R.drawable.ic_stat_ic_notification)
            int smallIconId = getApplicationContext().getResources().getIdentifier("right_icon", "id", android.R.class.getPackage().getName());
            if (smallIconId != 0) {
                if (notificationBuilder.contentView != null) {
                    // notificationBuilder.contentView.setViewVisibility(smallIconId, View.INVISIBLE);
                    notificationBuilder.bigContentView.setViewVisibility(smallIconId, View.INVISIBLE);
                }
            }
            NotificationManager notificationManager =
                    (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                CharSequence name = getString(R.string.Channle_name);// The user-visible name of the channel.
                int importance = NotificationManager.IMPORTANCE_HIGH;
                NotificationChannel mChannel = new NotificationChannel(channelId, name, importance);
                notificationManager.createNotificationChannel(mChannel);
            }
            //notificationBuilder.flags |= Notification.FLAG_AUTO_CANCEL;
            notificationManager.notify(NCount++, notificationBuilder);



           /* Intent intent = new Intent(mContext, MyOpenableActivity.class);
            intent.putExtra("key", "value");
            PendingIntent pendingIntent = PendingIntent.getActivity(mContext, 100, intent, PendingIntent.FLAG_ONE_SHOT);

            NotificationManager notificationManager = (NotificationManager) mContext.getSystemService(Context.NOTIFICATION_SERVICE);
            Notification notif = new Notification.Builder(mContext)
                    .setContentIntent(pendingIntent)
                    .setContentTitle(title)
                    .setContentText(message)
                    .setSmallIcon(R.mipmap.ic_launcher)
                    .setLargeIcon(result)
                    .setStyle(new Notification.BigPictureStyle().bigPicture(result))
                    .build();
            notif.flags |= Notification.FLAG_AUTO_CANCEL;
            notificationManager.notify(1, notif);*/
        }
    }

    public Bitmap getResizedBitmap(Bitmap bm, int newWidth, int newHeight) {
        int width = bm.getWidth();
        int height = bm.getHeight();
        float scaleWidth = ((float) newWidth) / width;
        float scaleHeight = ((float) newHeight) / height;
        // CREATE A MATRIX FOR THE MANIPULATION
        Matrix matrix = new Matrix();
        // RESIZE THE BIT MAP
        matrix.postScale(scaleWidth, scaleHeight);

        // "RECREATE" THE NEW BITMAP
        Bitmap resizedBitmap = Bitmap.createBitmap(
                bm, 0, 0, width, height, matrix, false);
        bm.recycle();
        return resizedBitmap;
    }
}