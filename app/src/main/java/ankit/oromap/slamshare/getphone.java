package ankit.oromap.slamshare;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.widget.Toast;

import java.io.DataInputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;

/**
 * Created by ankit on 09/12/17.
 */

public class getphone extends AsyncTask<String,Void,Void> {
    private String phone="";
    AlertDialog.Builder ad;
    ProgressDialog pd;
    String em="";
    String ex="";
    Toast t;
    Intent ji;
    Context l;
    @Override
    protected Void doInBackground(String... strings) {
        em=strings[0];
        ex=strings[1];
        try {
            URL u = new URL("http://oromap.000webhostapp.com/getphone.php?phone="+ex+em);
            HttpURLConnection uc=(HttpURLConnection) u.openConnection();
            uc.setConnectTimeout(30000);
            InputStream is=uc.getInputStream();
            DataInputStream data=new DataInputStream(is);
            phone=data.readLine().trim();
        }catch (Exception f){}

        return null;
    }
public void ini(AlertDialog.Builder ad, ProgressDialog pd,Context con,Intent i)
{
    this.l=con;
    this.ad=ad;
    this.pd=pd;
   this.ji=i;
}

    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);
        if(phone.equalsIgnoreCase("exists")){
            ad.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    l.startActivity(ji);
                }
            });
            ad.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    dialogInterface.cancel();
                }
            });
            ad.setMessage("Send a verification code to "+String.valueOf(ex+em));
            ad.show();
        }
        else if(phone.equalsIgnoreCase("fail"))
        {
            ad.setIcon(R.drawable.cross);
            ad.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    dialogInterface.dismiss();
                    dialogInterface.cancel();
                }
            });
            ad.setMessage("Phone number not registered");
            ad.show();
        }
        else {
            ad.setIcon(R.drawable.cross);
            ad.setMessage("Error in connection");
            ad.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    dialogInterface.dismiss();
                    dialogInterface.cancel();
                }
            });
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
