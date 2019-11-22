package ankit.oromap.slamshare;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.AsyncTask;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.RequestQueue;

import java.io.DataInputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;

import javax.net.ssl.HttpsURLConnection;

/**
 * Created by ankit on 11/12/17.
 */

public class confirmphoneavailabilty extends AsyncTask<String,Void,Void> {
    private String result="";
    private Toast t;
    public asyncinterface asyncinterface=null;
    @Override
    protected Void doInBackground(String... strings) {
        String number=strings[0];
        number=number.replace("+","").trim();
        String email=strings[1];
        String username=strings[2];
        try
        {
            URL u=new URL("https://oromap.000webhostapp.com/confirmavailability.php?number="+number+"&email="+email+"&username="+username);
            HttpsURLConnection uc=(HttpsURLConnection) u.openConnection();
            uc.setConnectTimeout(30000);
            InputStream is=uc.getInputStream();
            DataInputStream da=new DataInputStream(is);
            result=da.readLine().trim();
        }
        catch (Exception f){}
        return null;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }
    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);
        asyncinterface.processFinish(result);
    }
}
