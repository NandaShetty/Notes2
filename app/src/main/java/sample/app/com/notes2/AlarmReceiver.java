package sample.app.com.notes2;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;
import android.support.v4.content.WakefulBroadcastReceiver;
import android.util.Log;

/**
 * Created by Nanda devi shetty b on 05-12-2017.
 */

public class AlarmReceiver extends  WakefulBroadcastReceiver {
    public static final String TAG = AlarmReceiver.class.getSimpleName();

    @Override
    public void onReceive(Context context, Intent intent) {

        String name = intent.getStringExtra("names");
        String content=intent.getStringExtra("description");
        Log.d(TAG,"names"+name+""+"content"+content);

            Intent notificationIntent = new Intent(context, DetailNote.class);

            notificationIntent.putExtra("title",name);
            notificationIntent.putExtra("details",content);
            notificationIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            notificationIntent.setFlags(Intent.FLAG_ACTIVITY_MULTIPLE_TASK
                    | Intent.FLAG_ACTIVITY_BROUGHT_TO_FRONT);

        PendingIntent pi = PendingIntent.getActivity(context, 0, notificationIntent, PendingIntent.FLAG_UPDATE_CURRENT);

            NotificationCompat.Builder mBuilder =
                    new NotificationCompat.Builder(context)
                            .setContentIntent(pi)
                            .setSmallIcon(R.drawable.notifications)
                            .setContentText(content)
                            .setContentTitle(name)
                            .setDefaults(Notification.DEFAULT_ALL)
                            .setAutoCancel(true);

            NotificationManager mNotificationManager =
                    (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
            mNotificationManager.notify(0, mBuilder.build());
        }
    }
