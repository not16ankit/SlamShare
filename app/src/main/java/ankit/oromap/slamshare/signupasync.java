package ankit.oromap.slamshare;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.viewpager.widget.ViewPager;

import com.google.zxing.aztec.encoder.AztecCode;
import com.google.zxing.aztec.encoder.Encoder;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;

/**
 * Created by ankit on 07/11/17.
 */

public class signupasync extends AsyncTask<Void,Void,Void> {
 private    String firstn;
   private String lastn;
    private  String email;
    private  String phone;
    private Intent i;
    private String pass="";
    private  File f;
    private AlertDialog.Builder error;
    private ViewPager vp;
    private File f1;
    private FileWriter fw1;
    private BufferedWriter bw1;
    private FileWriter fw;
    private BufferedWriter bw;
    private  ProgressDialog pd;
    private String ex;
    private String usernames;
    private Context l;
    private String re;
    private AlertDialog.Builder da;
    private String code;
    @Override
    protected Void doInBackground(Void... voids) {
        try {
            URL u = new URL("http://oromap.000webhostapp.com/indexsignup.php?firstname=" + firstn + "&lastname=" + lastn + "&email=" + email + "&phonenumber=" + phone +  "&pass=" + pass+"&ex="+ex+"&username="+usernames+"&systemcallcode="+code);
            HttpURLConnection uc=(HttpURLConnection) u.openConnection();
            uc.setConnectTimeout(30000);
            InputStream is=uc.getInputStream();
            DataInputStream d=new DataInputStream(is);
            re=d.readLine().trim();
        }
        catch (Exception e){}
        return null;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        pd.setMessage("Signing Up......");
        pd.show();
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);
        if(re.equalsIgnoreCase("eexists"))
        {
            pd.cancel();
            da.setIcon(R.drawable.cross);
            da.setMessage("Email Already Registered");
            da.show();
        }
        else if (re.equalsIgnoreCase("pexists")) {
            da.setIcon(R.drawable.cross);
            pd.cancel();
            da.setMessage("Phone number already registered");
            da.show();
        }

     else if(re.equalsIgnoreCase("success")) {
            pd.cancel();
            try {
                fw = new FileWriter(f,false);
                bw=new BufferedWriter(fw);
                fw1=new FileWriter(f1,false);
                bw1=new BufferedWriter(fw1);
                bw1.write(pass);
                bw1.close();
                bw.write(email);
                bw.close();
                da.setIcon(R.drawable.passtick);
                da.setMessage("Signup Successful");
                da.show();
            }catch (Exception f){}
        }
        else {

        }
    }
    public void ini(String fn, String ln, String em, String phone, String pass,String ex, Context l, ProgressDialog pd,Context c,AlertDialog.Builder da,String usernames,String code)
    {
        this.usernames=usernames;
        this.da=da;
        this.error=error;
        this.l=c;
     this.firstn=fn;
     this.lastn=ln;
     this.email=em;
     this.phone=phone;
     this.pass= pass;
     this.code=code;
        this.pd=pd;
        this.ex=ex;
        i=new Intent(l.getApplicationContext(),loginsuc.class);
        f=new File(l.getFilesDir(),"email.txt");
        f1=new File(l.getFilesDir(),"pass.txt");
    }
}

