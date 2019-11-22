package ankit.oromap.slamshare;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.widget.Toast;

import java.io.DataInputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.Calendar;
public class checkasync extends AsyncTask<String,Void,Void> {
    Toast t;
    Intent i;
    Context l;
    PendingIntent pi;
    Intent p;
    int y=0;
    AlarmManager am;
    public void ini(Context c)
    {
        this.l=c;
        i=new Intent(c,checkservicelink.class);
        am=(AlarmManager)c.getSystemService(Context.ALARM_SERVICE);
    }
    @Override
    protected Void doInBackground(String... strings) {
        String em= strings[0];
        String pass=strings[1];
        int previous=0;
        String ex="";
        int present=0;
        int time=1;
        try {
            URL u = new URL("http://oromap.000webhostapp.com/getcondition.php?email=" + em + "&pass=" + pass);
            HttpURLConnection uc=(HttpURLConnection) u.openConnection();
            uc.setConnectTimeout(30000);
            InputStream is=uc.getInputStream();
            DataInputStream d=new DataInputStream(is);
            String condition=d.readLine().trim()+"^";
            for(int i=0;i<condition.length();i++)
            {
                char c=condition.charAt(i);
                if(c=='^')
                {
                    if(time==1)
                    {
                        previous=Integer.valueOf(ex);
                        ex="";
                        time++;
                    }
                    else
                    {
                        present=Integer.valueOf(ex);
                    }
                }
                else
                {
                    ex=ex+c;
                }
            }
            if(previous<present)
            {
                l.startService(i);
            }
            else

            {
                y++;
            }
        }catch (Exception e){}
        return null;
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);
        if(y==1)
        {
            Toast.makeText(l,"No new Slambooks found",Toast.LENGTH_LONG).show();
        }
    }
}
