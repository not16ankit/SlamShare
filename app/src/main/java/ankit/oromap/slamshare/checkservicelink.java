package ankit.oromap.slamshare;

import android.app.AlarmManager;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.PeriodicSync;
import android.media.MediaPlayer;
import android.os.IBinder;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.Calendar;

public class checkservicelink extends Service {
    public checkservicelink() {
    }

    @Override
    public void onCreate() {
        super.onCreate();
        NotificationManager nm=(NotificationManager)getSystemService(NOTIFICATION_SERVICE);
        Notification.Builder nb=new Notification.Builder(this);
        nb.setSmallIcon(R.drawable.icon);
        nb.setContentTitle("New Slambooks Available");
        nb.setContentText("Tap here");
        nb.setContentIntent(PendingIntent.getActivity(this,2,new Intent(this,Messages.class),0));
        nm.notify(0,nb.build());
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }
}
