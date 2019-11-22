package ankit.oromap.slamshare;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.AsyncTask;
import android.provider.ContactsContract;
import android.widget.AutoCompleteTextView;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileWriter;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;

/**
 * Created by ankit on 12/11/17.
 */

public class getname extends AsyncTask<String,Void,Void> {
    ProgressDialog pd;
    Toast t;
    Context l;
    private String ex="";
    private String name="";
    private String username="";
    private String number="";
    public inidetails inid;
    private String em="";
    public void ini(ProgressDialog pd, Context c)
    {
        this.pd=pd;
        this.l=c;
        t=Toast.makeText(l,"sd",Toast.LENGTH_LONG);
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        pd.show();
    }

    @Override
    protected Void doInBackground(String... strings) {
        em=strings[0];
        String pass=strings[1];
        try {
            URL u1 = new URL("http://oromap.000webhostapp.com/account.php?email=" + em + "&pass=" + pass);
            HttpURLConnection uc1=(HttpURLConnection)u1.openConnection();
            uc1.setConnectTimeout(20000);
            InputStream is1 = uc1.getInputStream();
            DataInputStream data1 = new DataInputStream(is1);
            URL u2 = new URL("http://oromap.000webhostapp.com/getnumber.php?email=" + em + "&pass=" + pass);
            HttpURLConnection uc2=(HttpURLConnection)u2.openConnection();
            uc2.setConnectTimeout(20000);
            InputStream is2 = uc2.getInputStream();
            DataInputStream data2 = new DataInputStream(is2);
            URL u3 = new URL("http://oromap.000webhostapp.com/getex.php?email=" + em + "&pass=" + pass);
            HttpURLConnection uc3=(HttpURLConnection)u3.openConnection();
            uc3.setConnectTimeout(20000);
            InputStream is3 = uc3.getInputStream();
            DataInputStream data3 = new DataInputStream(is3);
            URL u= new URL("http://oromap.000webhostapp.com/getusernameexternal.php?email="+em+"&pass="+pass+"&user="+em);
            HttpURLConnection uc=(HttpURLConnection) u.openConnection();
            InputStream is=uc.getInputStream();
            DataInputStream data=new DataInputStream(is);
            username=data.readLine().trim();
            name=data1.readLine().trim();
           number=data2.readLine().trim();
           ex="+"+data3.readLine().trim();
        }
        catch(Exception e){}
        return null;
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);
        if(em.isEmpty()||name.isEmpty()||number.isEmpty()||ex.isEmpty())
        {
            AlertDialog.Builder ad=new AlertDialog.Builder(l,R.style.Slert);
            ad.setMessage("Error in connection");
            ad.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.cancel();
                dialogInterface.dismiss();
                }
            });
            pd.dismiss();
            pd.cancel();
            ad.show();

        }
        else {
            pd.dismiss();
            pd.cancel();
            inid.inidetails(ex,em,name,number,username);

        }
    }
}
