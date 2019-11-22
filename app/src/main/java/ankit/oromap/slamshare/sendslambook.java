package ankit.oromap.slamshare;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;

import java.io.DataInputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;

/**
 * Created by ankit on 26/11/17.
 */

public class sendslambook extends AsyncTask<String,Void,Void> {
    int friendcount;
    int extra;
    String slambookname;
    private int use=1;
    String[] extras;
    String[] friends;
    String urlextra="";
    String url="";
    Context l;
    ProgressDialog pd;
    public void ini(int friendcount, int extra, String slambookname, String[] extras,String[] friends, Context c,ProgressDialog pd)
    {
        this.friendcount=friendcount;
        this.extra=extra;
        this.slambookname=slambookname;
        this.extras=extras;
        this.friends=friends;
        this.l=c;
        this.pd=pd;
    }
    @Override
    protected Void doInBackground(String... strings) {
        url="http://oromap.000webhostapp.com/createslambook.php?email="+strings[0]+"&pass="+strings[1]+"&extra="+String.valueOf(extra)+"&friendcount="+friendcount+"&slambookname="+slambookname;
        int use3=0;
        for(int i=1;i<=extra;i++)
        {
            url=url+"&extra"+i+"="+extras[use3];
            use3++;
        }
        for(int i=0;i<=friendcount;i++)
        {
            url=url+"&friend"+use+"="+friends[i];
            use++;
        }
            try {
                URL u = new URL(url);
                HttpURLConnection ucon=(HttpURLConnection) u.openConnection();
                ucon.setConnectTimeout(30000);
                InputStream is = ucon.getInputStream();
                DataInputStream data = new DataInputStream(is);
            }catch (Exception el){}
        return null;
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);
        pd.dismiss();
        pd.cancel();
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        pd.show();
    }
}
