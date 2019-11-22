package ankit.oromap.slamshare;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.widget.Toast;

import java.io.DataInputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;

/**
 * Created by ankit on 04/12/17.
 */

public class initializereceslambooks extends AsyncTask<String,Void,Void> {
    ProgressDialog pd;
    String[] slambooknames;
    String[] sentbyusernames;
    String[] sentbyemails;
    private int number;
    public inimessages ini;
    private String numberstring;
    Messages mess;
    Toast t;
    int use;
    @Override
    protected Void doInBackground(String... strings) {
        String em=strings[0];
        String pass=strings[1];
        URL u2;
        URL u3;
        URL u;
        HttpURLConnection uc;
        InputStream is;
        DataInputStream data;
        String users="";
        try {
            u=new URL("http://oromap.000webhostapp.com/getpresent.php?email="+em+"&pass="+pass);
           uc =(HttpURLConnection) u.openConnection();
            is=uc.getInputStream();
           data=new DataInputStream(is);
            numberstring=data.readLine().trim();
        number=Integer.parseInt(numberstring);
        slambooknames=new String[number];
        sentbyusernames=new String[number];
        sentbyemails=new String[number];
        use=number-1;
            for (int i = 1; i <= number; i++) {
                u2 = new URL("http://oromap.000webhostapp.com/getslambooknamerece.php?email=" + em + "&pass=" + pass + "&slambooknumber=" + i);
                HttpURLConnection uc2 = (HttpURLConnection) u2.openConnection();
                InputStream is2 = uc2.getInputStream();
                DataInputStream d = new DataInputStream(is2);
                slambooknames[use] = d.readLine().trim();
                u3 = new URL("http://oromap.000webhostapp.com/getslambooksentbyrece.php?email=" + em + "&pass=" + pass + "&slambooknumber=" + i);
                HttpURLConnection uc3 = (HttpURLConnection) u3.openConnection();
                InputStream is3 = uc3.getInputStream();
                DataInputStream d1 = new DataInputStream(is3);
                String r = d1.readLine().trim();
                sentbyemails[use]=r;
                u = new URL("http://oromap.000webhostapp.com/getusernameexternal.php?email=" + em + "&pass=" + pass + "&user=" + r);
                uc = (HttpURLConnection) u.openConnection();
                is = uc.getInputStream();
                data = new DataInputStream(is);
                users = data.readLine().trim();
                sentbyusernames[use] = users;
                use--;
            }
        }
        catch (Exception d){}
        return null;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        pd.show();
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);
        pd.dismiss();
        pd.cancel();
        if(number>0)
        {
            ini.inislambooklist(slambooknames,sentbyemails,sentbyusernames);
        }
        else
        {

        }
    }
    public void ini(ProgressDialog pd,Toast t)
    {
        this.t=t;
        this.pd=pd;
    }
}

