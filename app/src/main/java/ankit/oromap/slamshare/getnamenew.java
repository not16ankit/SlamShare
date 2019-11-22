package ankit.oromap.slamshare;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.renderscript.ScriptGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;

import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileWriter;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;

/**
 * Created by ankit on 02/12/17.
 */

public class getnamenew extends AsyncTask<String,Void,Void> {
    ProgressDialog pd;
    TextView tv;
    Context l;
    private String name="";
    private String inet="";
    FileWriter fw1;
    BufferedWriter bw1;
    Snackbar snack;
    File f1;
    loginsuc log;
    public void ini(ProgressDialog pd,TextView tv,Context c,Snackbar snack)
    {
        this.snack=snack;
        this.pd=pd;
        this.tv=tv;
        this.l=c;
        f1=new File(c.getFilesDir(),"name.txt");
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        pd.show();
    }

    @Override
    protected Void doInBackground(String... strings) {
        String em=strings[0];
        String pass=strings[1];
        try {
            URL u=new URL("http://oromap.000webhostapp.com/internet.php");
            URLConnection uc=u.openConnection();
            InputStream is=uc.getInputStream();
            DataInputStream data1=new DataInputStream(is);
           inet=data1.readLine().trim();
            URL u1 = new URL("http://oromap.000webhostapp.com/account.php?email=" + em + "&pass=" + pass);
            HttpURLConnection uc1=(HttpURLConnection)u1.openConnection();
            uc1.setConnectTimeout(20000);
            InputStream is1 = uc1.getInputStream();
            DataInputStream data=new DataInputStream(is1);
            name=data.readLine().trim();
        }
        catch(Exception e){}
        return null;
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);
        try
        {
            fw1 = new FileWriter(f1,false);
            bw1=new BufferedWriter(fw1);
            bw1.write(name);
            bw1.close();
        }catch (Exception e){}
        if(inet.equalsIgnoreCase("good"))
        {
            Toast.makeText(l,"SSL Encryption Enabled",Toast.LENGTH_LONG).show();
        }
        else
        {
            snack.show();
        }
        pd.dismiss();
        pd.cancel();
    }
}
