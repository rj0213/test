package com.example.reueljohn.standup;

import android.app.AlarmManager;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.SystemClock;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.Toast;
import android.widget.ToggleButton;

public class MainActivity extends AppCompatActivity {

    private static final int NOTIFICATION_ID = 0;

    private static final String ACTION_NOTIFY =
            "com.example.android.standup.ACTION_NOTIFY";

    ToggleButton alarmToggle;

    NotificationManager mNotificationManager;

    PendingIntent contentPendingIntent;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mNotificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);

        final AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);

        alarmToggle = (ToggleButton) findViewById(R.id.alarmToggle);

        Intent notifyIntent = new Intent(ACTION_NOTIFY);

        boolean alarmUp = (PendingIntent.getBroadcast(this, NOTIFICATION_ID, notifyIntent,
                PendingIntent.FLAG_NO_CREATE) != null);

        alarmToggle.setChecked(alarmUp);


        final PendingIntent notifyPendingIntent = PendingIntent.getBroadcast(
                this,NOTIFICATION_ID,notifyIntent,PendingIntent.FLAG_UPDATE_CURRENT);







        alarmToggle.setOnCheckedChangeListener(new OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                String toastMessage;

                long triggerTime = SystemClock.elapsedRealtime() + AlarmManager.INTERVAL_FIFTEEN_MINUTES;

                long repeatInterval = AlarmManager.INTERVAL_FIFTEEN_MINUTES;

                if (isChecked){
                    toastMessage = getString(R.string.alarm_on_toast);
                    alarmManager.setInexactRepeating(
                            AlarmManager.ELAPSED_REALTIME_WAKEUP,triggerTime,
                            repeatInterval,notifyPendingIntent);
                } else{

                    mNotificationManager.cancelAll();
                    alarmManager.cancel(notifyPendingIntent);
                    toastMessage = getString(R.string.alarm_off_toast);
                }

                Toast.makeText(MainActivity.this, toastMessage, Toast.LENGTH_SHORT).show();
            }
        });


    }

    private void deliverNotification(Context context){

        NotificationCompat.Builder builder = new NotificationCompat.Builder(context)
                .setSmallIcon(R.drawable.ic_alarm)
                .setContentTitle(context.getString(R.string.notification_title))
                .setContentText(context.getString(R.string.notification_text))
                .setContentIntent(contentPendingIntent)
                .setPriority(NotificationCompat.PRIORITY_HIGH)
                .setAutoCancel(true)
                .setDefaults(NotificationCompat.DEFAULT_ALL);


        mNotificationManager.notify(NOTIFICATION_ID, builder.build());
    }


}
