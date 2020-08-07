package com.example.bottom_nav.noti;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.media.RingtoneManager;
import android.net.Uri;

import androidx.core.app.NotificationCompat;

import com.example.bottom_nav.MainActivity;
import com.example.bottom_nav.R;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

public class MyFirebaseMessagingService extends FirebaseMessagingService {
      public static int NOTIFICATION_ID=1;

    public static String msg;

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        /**Extra work*/
       msg= remoteMessage.getNotification().getBody().toString();
/**End extra work*/

        generateNotification(remoteMessage.getNotification().getBody(),remoteMessage.getNotification().getTitle());
    }

    private void generateNotification(String body, String title) {


        Intent intent=new Intent(this, MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        intent.putExtra("body",msg);
        startActivity(intent);
        PendingIntent pendingIntent=PendingIntent.getActivity(this,0,intent,PendingIntent.FLAG_ONE_SHOT);



        Uri soundUri= RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);

        NotificationCompat.Builder notificationBuilder=new NotificationCompat.Builder(this)
                .setSmallIcon(R.drawable.ic_notifications_active)
                .setContentTitle(title)
                .setContentText(body)
                .setAutoCancel(true)
                .setSound(soundUri)
                .setContentIntent(pendingIntent);
        NotificationManager notificationManager=(NotificationManager)getSystemService(Context.NOTIFICATION_SERVICE);

        if(NOTIFICATION_ID > 1073741824){
            NOTIFICATION_ID=0;
        }
        notificationManager.notify(NOTIFICATION_ID++,notificationBuilder.build());

    }

//    public String getNotification(){
//        if(msg!=null){
//            return msg;
//        }
//        else {
//            return "no message";
//        }
//
//    }
}
