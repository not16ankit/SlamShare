package ankit.oromap.slamshare;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.AsyncTask;
import android.widget.Toast;

import java.io.DataInputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;

/**
 * Created by ankit on 20/11/17.
 */

public class uploaddetails extends AsyncTask<String,Void,Void> {
    Context l;
    String result;
    ProgressDialog pd;
    Toast t;
    AlertDialog.Builder ad;
    @Override
    protected Void doInBackground(String... strings) {
        String em=strings[0];
        String pass=strings[1];
        String newname=strings[2];
        String newphone=strings[3];
        String ex=strings[4];
        String newusername=strings[5];
        String code=strings[6];
        ex=ex.replace("+","").trim();
        try
        {
            URL u=new URL("http://oromap.000webhostapp.com/accountreset.php?email="+em+"&pass="+pass+"&name="+newname+"&phonenumber="+newphone+"&ex="+ex+"&newusername="+newusername+"&systemcallcode="+code);
            HttpURLConnection uc=(HttpURLConnection) u.openConnection();
            uc.setConnectTimeout(30000);
            InputStream is=uc.getInputStream();
            DataInputStream d=new DataInputStream(is);
            result=d.readLine().trim();
        }
        catch (Exception e){}
        return null;
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);
        t.setText(result);
        t.show();
        if(result.equalsIgnoreCase("success"))
        {
            ad.setMessage("Details Uploaded successfully");
            ad.setNegativeButton("OK", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.cancel();
                dialogInterface.dismiss();
                }
            });
            ad.show();
        }
        else if(result.equalsIgnoreCase("pexists"))
        {
            ad.setNegativeButton("OK", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    dialogInterface.cancel();
                    dialogInterface.dismiss();
                }
            });
            ad.setMessage("Phone number already exists");
            ad.show();
        }
        else
        {
            ad.setIcon(R.drawable.cross);
            ad.setIcon(R.drawable.passtick);
            ad.setNegativeButton("OK", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    dialogInterface.cancel();
                    dialogInterface.dismiss();
                }
            });
            ad.setMessage("Error in connection");
            ad.show();
        }
        pd.cancel();
        pd.dismiss();
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        pd.show();
    }
    public void ini(Context con,ProgressDialog pd)
    {
        this.pd=pd;
        this.l=con;
        this.t=Toast.makeText(con,"",Toast.LENGTH_LONG);
        this.ad=new AlertDialog.Builder(con,R.style.Slert);
    }
}
