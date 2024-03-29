package com.survey.surveyapp.Notification;

import android.annotation.SuppressLint;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.core.app.NotificationCompat;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;
import com.survey.surveyapp.Delegates.Notifciationsdelegates;
import com.survey.surveyapp.Models.NotificationModels;
import com.survey.surveyapp.Netwrks.APIInterface;
import com.survey.surveyapp.Netwrks.WSContants;
import com.survey.surveyapp.R;
import com.survey.surveyapp.Ui.Activity.HomeActivity;
import com.survey.surveyapp.ViewModel.LoginViewModel;
import com.survey.surveyapp.ViewModel.NoticationsViewModel;
import com.survey.surveyapp.db.AppDatabase;


import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;

import okhttp3.MultipartBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MyFirebaseMessagingService extends FirebaseMessagingService {

    private static final String TAG = "MyFirebaseMsgService";
    private NoticationsViewModel viewModal;
    String uid;
    public AppDatabase appDatabase;
    /**
     * Called when message is received.
     *
     * @param remoteMessage Object representing the message received from Firebase Cloud Messaging.
     */
    // [START receive_message]
    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        /**
         * Create and show a simple notification containing the received FCM message.
         *
         * @param messageBody FCM message body received.
         */
        sendNotification(String.valueOf(remoteMessage.getData().get("title")));

        // [START_EXCLUDE]
        // There are two types of messages data messages and notification messages. Data messages
        // are handled
        // here in onMessageReceived whether the app is in the foreground or background. Data
        // messages are the type
        // traditionally used with GCM. Notification messages are only received here in
        // onMessageReceived when the app
        // is in the foreground. When the app is in the background an automatically generated
        // notification is displayed.
        // When the user taps on the notification they are returned to the app. Messages
        // containing both notification
        // and data payloads are treated as notification messages. The Firebase console always
        // sends notification
        // messages. For more see: https://firebase.google.com/docs/cloud-messaging/concept-options
        // [END_EXCLUDE]

        // TODO(developer): Handle FCM messages here.


        // Check if message contains a notification payload.
        if (remoteMessage.getNotification() != null) {
            Log.d(TAG, "Message Notification Body: " + remoteMessage.getNotification().getBody());

        }

        // Also if you intend on generating your own notifications as a result of a received FCM
        // message, here is where that should be initiated. See sendNotification method below.
    }
    // [END receive_message]


    // [START on_new_token]

    /**
     * Called if InstanceID token is updated. This may occur if the security of
     * the previous token had been compromised. Note that this is called when the InstanceID token
     * is initially generated so this is where you would retrieve the token.
     */
    @Override
    public void onNewToken(String token) {
        Log.d(TAG, "Refreshed token: " + token);

        // If you want to send messages to this application instance or
        // manage this apps subscriptions on the server side, send the
        // Instance ID token to your app server.
        sendRegistrationToServer(token);
    }
    // [END on_new_token]

    /**
     * Schedule async work using WorkManager.
     */


    /**
     * Handle time allotted to BroadcastReceivers.
     */
    private void handleNow() {
        Log.d(TAG, "Short lived task is done.");
    }

    /**
     * Persist token to third-party servers.
     *
     * Modify this method to associate the user's FCM InstanceID token with any server-side account
     * maintained by your application.
     *
     * @param token The new token.
     */
    private void sendRegistrationToServer(String token) {
        // TODO: Implement this method to send token to your app server.
    }
    /**
     * Create and show a simple notification containing the received FCM message.
     *
     * @param messageBody FCM message body received.
     */
    public void sendNotification(String messageBody) {

        FirebaseAuth firebaseAuth= FirebaseAuth.getInstance();
        Date c = Calendar.getInstance().getTime();
        System.out.println("Current time => " + c);
        @SuppressLint("SimpleDateFormat") SimpleDateFormat df1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        final String formattedDate1 = df1.format(c.getTime());
        final SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault());
        final String formattedDate = df.format(c);

        uid=AppDatabase.getAppDatabase(this).userDao().getLoginUser().getUid();
        Retrofit retrofit = new Retrofit.Builder().baseUrl("http://esurvey.knickglobal.com/api/").addConverterFactory(GsonConverterFactory.create())
                .build();
        final APIInterface service = retrofit.create(APIInterface.class);
     /*   progressDialog=new ProgressDialog(LoginActivity.this);
        progressDialog.setMessage("Registering......");
        progressDialog.setCancelable(false );
        progressDialog.show();*/

        ArrayList<MultipartBody.Part> arrayListMash = new ArrayList<MultipartBody.Part>();
        arrayListMash.add(MultipartBody.Part.createFormData(WSContants.PROFILE_USER_ID, uid));
        arrayListMash.add(MultipartBody.Part.createFormData(WSContants.SUBJECT, "New Notification"));
        arrayListMash.add(MultipartBody.Part.createFormData(WSContants.MESSAGE, messageBody));
        retrofit2.Call<NotificationModels> call = service.postNotification(arrayListMash);
        call.enqueue(new Callback<NotificationModels>() {
            @Override
            public void onResponse(Call<NotificationModels> call, Response<NotificationModels> response) {
                if (response.isSuccessful()) {
                    Log.d("mnlfskjfdgdlskgjfd","ggggg1"+response);
                }else {
                    Log.d("mnlfskjfdgdlskgjfd","ggggg2"+response);
                }
            }
            @Override
            public void onFailure(Call<NotificationModels> call, Throwable t) {

                Log.d("mnlfskjfdgdlskgjfd","ggggg3"+t.getLocalizedMessage());
            }
        });

        Intent intent = new Intent(getApplicationContext(), HomeActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0 /* Request code */, intent,
                PendingIntent.FLAG_ONE_SHOT);

        String channelId = getString(R.string.default_notification_channel_id);
        Uri defaultSoundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        NotificationCompat.Builder notificationBuilder =
                new NotificationCompat.Builder(this, channelId)
                        .setSmallIcon(R.drawable.ic_launcher_background)
                        .setContentTitle(getString(R.string.Result))
                        .setContentText(messageBody)
                        .setAutoCancel(true)
                        .setSound(defaultSoundUri)
                        .setContentIntent(pendingIntent);


        NotificationManager notificationManager =
                (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        // Since android Oreo notification channel is needed.
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel(channelId,
                    "Channel human readable title",
                    NotificationManager.IMPORTANCE_HIGH);
            notificationBuilder.setSmallIcon(R.drawable.ic_launcher_background);
            notificationBuilder.setColor(0x169AB9);
            assert notificationManager != null;
            notificationManager.createNotificationChannel(channel);
        }
        assert notificationManager != null;
        notificationManager.notify(0 /* ID of notification */, notificationBuilder.build());
    }
}