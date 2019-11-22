package ankit.oromap.slamshare;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Color;
import android.os.AsyncTask;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.PopupMenu;
import android.widget.Toast;

import java.io.DataInput;
import java.io.DataInputStream;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;

/**
 * Created by ankit on 24/11/17.
 */

public class getusers extends AsyncTask<String,Void,Void>
{
    Toast t;
    AlertDialog.Builder ad;
    public iniarrays ini;
    ListView ls;
        ProgressDialog pd;
Context l;
friendlist2 fl;
String usernames[];
String names[];
MenuInflater mi0;
private String emails[];
private int o=0;
    protected void ini(ProgressDialog pd, Context con, ListView ls)
    {
        t=Toast.makeText(con,"",Toast.LENGTH_LONG);
        this.ad=ad;
        this.pd=pd;
        this.l=con;
        this.ls=ls;
        fl=new friendlist2();
        this.t=Toast.makeText(con,"",Toast.LENGTH_LONG);
    }
    @Override
    protected Void doInBackground(String...strings) {
        String email=strings[0];
        String pass=strings[1];
        try {
            String nameresult;
            URLConnection ucnames;
            InputStream isna;
            DataInputStream d;
            URL uv=new URL("http://oromap.000webhostapp.com/getemail.php?email="+email+"&pass="+pass);
            URLConnection uvc=uv.openConnection();
            InputStream ist=uvc.getInputStream();
            DataInputStream di=new DataInputStream(ist);
            String emailss=di.readLine().trim();
            URL number=new URL("http://oromap.000webhostapp.com/getusernumber.php?email="+email+"&pass="+pass);
            URLConnection uc1=number.openConnection();
            InputStream is1=uc1.getInputStream();
            DataInputStream data1=new DataInputStream(is1);
            int n=Integer.parseInt(data1.readLine());
            this.usernames=new String[n];
            this.names=new String[n];
            this.emails=new String[n];
            int use3=0;
            String ex1="";
            URL url;
            URLConnection uc;
            InputStream is;
            DataInputStream data;
            String users;

            for(int s=0;s<emailss.length();s++)
            {
                char c=emailss.charAt(s);
                if(c=='^')
                {
                    this.emails[use3]=ex1;
                    ex1="";
                    use3++;
                }
                else
                {
                    ex1=ex1+c;
                }
            }
            for(int s=0;s<n;s++)
            {
                url = new URL("http://oromap.000webhostapp.com/getusernameexternal.php?email="+email+"&pass="+pass+"&user="+emails[s]);
                uc=url.openConnection();
                is=uc.getInputStream();
                data=new DataInputStream(is);
                users=data.readLine().trim();
                this.usernames[s]=users;
            }
                for(int s=0;s<=use3;s++)
                {
                    url=new URL("http://oromap.000webhostapp.com/getfriendname.php?email="+email+"&pass="+pass+"&friendemail="+usernames[s]);
                    ucnames=url.openConnection();
                    isna=ucnames.getInputStream();
                    d=new DataInputStream(isna);
                    nameresult=d.readLine().trim();
                    names[s]=nameresult;
            }
        }catch (Exception e){}
        return null;
    }

    @Override
    protected void onPreExecute() {
        pd.show();
        super.onPreExecute();
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        pd.dismiss();
        pd.cancel();
        super.onPostExecute(aVoid);
        Toast.makeText(l,usernames[0]+' '+names[0]+' '+emails[0],Toast.LENGTH_LONG).show();
        ini.iniarrays(usernames,names,emails);
    }
}
