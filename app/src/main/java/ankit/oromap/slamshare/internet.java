package ankit.oromap.slamshare;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.webkit.HttpAuthHandler;
import android.widget.Toast;

import java.io.DataInputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by ankit on 13/12/17.
 */

public class internet extends AsyncTask<Void,Void,Void> {
   private AlertDialog.Builder snak;
   private ProgressDialog pd;
   private String re="";
    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);
        pd.cancel();
        pd.dismiss();
    }
public void ini(AlertDialog.Builder snack,ProgressDialog pd)
{
    this.snak=snack;
    this.pd=pd;
}
    @Override
    protected Void doInBackground(Void... voids) {
        try
        {
            URL u=new URL("http://oromap.000webhostapp.com/internet.php");
            HttpURLConnection uc=(HttpURLConnection)u.openConnection();
            uc.setConnectTimeout(30000);
            InputStream is=uc.getInputStream();
            DataInputStream d=new DataInputStream(is);
            this.re=d.readLine().trim();
        }catch (Exception d){}
        return null;
    }
}
