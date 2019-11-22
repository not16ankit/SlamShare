package ankit.oromap.slamshare;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.AsyncTask;
import android.widget.ProgressBar;

import java.io.DataInputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by ankit on 29/12/17.
 */

public class submit extends AsyncTask<String,Void,Void> {
    private String em="";
    private String pass="";
    private String url="";
    private String slambook="";
    private String sentby="";
    private String numberofanswers="";
    private Context l;
    private String result="";
    public inisubmit ini;
    public void ini(Context con)
    {
        this.l=con;
    }
    @Override
    protected Void doInBackground(String... strings) {
        em=strings[0];
        pass=strings[1];
        url=strings[2];
        slambook=strings[3];
        sentby=strings[4];
        try
        {
            URL u=new URL("http://oromap.000webhostapp.com/onsubmit.php?useremail="+sentby+"&friendemail="+em+"&slambook="+slambook+url);
            HttpURLConnection uc=(HttpURLConnection)u.openConnection();
            InputStream is=uc.getInputStream();
            DataInputStream data=new DataInputStream(is);
            result=data.readLine().trim();
        }catch (Exception d){}
        return null;
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);
     ini.ini(result);
    }
}
