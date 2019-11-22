package ankit.oromap.slamshare;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.nio.Buffer;

public class changepass extends AsyncTask<String,Void,Void> {
    ProgressDialog pd;
    Context l;
    BufferedWriter bw;
    public changepassinter ank;
    private String oldpass="";
    private String newpass="";
    private String email="";
    private String result;
    private  AlertDialog.Builder ad;
    String passforwrite;
    public void ini(ProgressDialog pd, Context con, AlertDialog.Builder da, String oldpassforwrite)
    {
        this.passforwrite=oldpassforwrite;
        this.pd=pd;
        this.l=con;
            this.ad=da;
    }
    @Override
    protected Void doInBackground(String... strings) {
        oldpass=strings[0];
        newpass=strings[1];
       email=strings[2];
        try {
            URL u = new URL("http://oromap.000webhostapp.com/passreset.php?oldpass=" + oldpass + "&newpass=" + newpass + "&email=" + email);
            HttpURLConnection uc=(HttpURLConnection) u.openConnection();
            uc.setConnectTimeout(30000);
            InputStream is=uc.getInputStream();
            DataInputStream d=new DataInputStream(is);
            result=d.readLine().trim();
        }catch (Exception h){}
        return null;
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);
        pd.cancel();
        pd.dismiss();
        if(result.equalsIgnoreCase("passchanged"))
        {
            passforwrite=newpass;
            ank.inipass(passforwrite);
        }
        else
        {
            ad.setIcon(R.drawable.cross);
            ad.setMessage("Wrong old password");
            ad.show();
        }
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        pd.show();
    }
}
