package ankit.oromap.slamshare;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;

import java.io.DataInputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;

/**
 * Created by ankit on 11/12/17.
 */

public class setnewpass extends AsyncTask<String,Void,Void> {
    private String number;
    private String code;
    private Context l;
    private ProgressDialog pd;
    private String result;
    private String password;
    private AlertDialog.Builder ad;
    @Override
    protected Void doInBackground(String... strings) {
        number=strings[0];
        code=strings[1];
        password=strings[2];
        try
        {
            URL u=new URL("http://oromap.000webhostapp.com/ultimatepassreset.php?number="+number+"&systemcallcode="+code+"&password="+password);
            HttpURLConnection uc=(HttpURLConnection) u.openConnection();
            uc.setConnectTimeout(30000);
            InputStream is=uc.getInputStream();
            DataInputStream data=new DataInputStream(is);
            result=data.readLine().trim();
        }catch (Exception f){}
        return null;
    }
public void ini(Context context, ProgressDialog pd, AlertDialog.Builder ad)
{
    this.ad=ad;
    this.l=context;
    this.pd=pd;
}
    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);
        if(result.equalsIgnoreCase("yes"))
        {
            ad.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    Intent iu=new Intent(l,Container2.class);
                    l.startActivity(iu);
                }
            });
            ad.setMessage("Password changed");
            ad.setIcon(R.drawable.passtick);
            ad.show();
        }
        else
        {
            ad.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    dialogInterface.cancel();
                    dialogInterface.dismiss();
                }
            });
            ad.setMessage("Error in connection");
            ad.show();
        }
        pd.dismiss();
        pd.cancel();
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        pd.show();
    }
}
