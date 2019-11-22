package ankit.oromap.slamshare;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import java.util.Calendar;

public class backuprece extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        Toast.makeText(context,"triggered",Toast.LENGTH_LONG).show();
        PendingIntent pi=PendingIntent.getService(context,0,new Intent(context,extraservice.class),0);
        AlarmManager am=(AlarmManager)context.getSystemService(Context.ALARM_SERVICE);
        Calendar c=Calendar.getInstance();
        Calendar clone=(Calendar)c.clone();
        clone.set(Calendar.MINUTE,c.get(Calendar.MINUTE)+2);
        am.setRepeating(AlarmManager.RTC_WAKEUP,clone.getTimeInMillis(),30000,pi);
        // TODO: This method is called when the BroadcastReceiver is receiving
        // an Intent broadcast.
        throw new UnsupportedOperationException("Not yet implemented");
    }
}
