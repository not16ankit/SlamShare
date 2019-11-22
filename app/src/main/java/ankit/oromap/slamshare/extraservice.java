package ankit.oromap.slamshare;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.ProgressDialog;
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.Calendar;

public class extraservice extends Service {
    public extraservice() {
    }
public void onCreate()
{
    super.onCreate();
    String em="";
    String pass="";
    try
    {
        File f = new File(getFilesDir(), "email.txt");
        FileReader fr = new FileReader(f);
        BufferedReader br = new BufferedReader(fr);
        em = br.readLine().trim();
        br.close();
        File f1 = new File(getFilesDir(), "pass.txt");
        FileReader fr1 = new FileReader(f1);
        BufferedReader br1 = new BufferedReader(fr1);
        pass = br1.readLine().trim();
        br1.close();
    }
    catch (Exception f){}
    checkasync sc=new checkasync();
    sc.ini(this);
    sc.execute(em,pass);
}
    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }
}
