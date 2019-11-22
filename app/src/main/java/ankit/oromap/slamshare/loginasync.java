package ankit.oromap.slamshare;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Environment;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.Toast;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;

public class loginasync extends AsyncTask<String,Void,Void> {
    static  File f;
    static File f1;
    HttpURLConnection uc;
ProgressDialog pd;
String email;
String pass;
private String re="";
Context l;
private FileWriter fw;
private FileWriter fw1;
private String em="";
private BufferedWriter bw;
private BufferedWriter bw1;
AlertDialog.Builder ad;
public void inifuc(ProgressDialog pd, String email, String pass, Context con,AlertDialog.Builder ad2)
{
    try {
            this.pd = pd;
            this.pd.setOnDismissListener(new DialogInterface.OnDismissListener() {
                @Override
                public void onDismiss(DialogInterface dialogInterface) {
                    cancel(true);
                }
            });
            this.email = email;
            this.pass = pass;
            this.l = con;
            this.ad =ad2;
        }
        catch(Exception e){}
}

    @Override
    protected Void doInBackground(String... strings) {
    em=strings[0];
    pass=strings[1];
    try {
        URL u = new URL("http://oromap.000webhostapp.com/indexlogin.php?email="+em+"&pass="+pass);
        uc=(HttpURLConnection) u.openConnection();
        uc.setConnectTimeout(30000);
        InputStream s=uc.getInputStream();
        DataInputStream d=new DataInputStream(s);
        re=d.readLine().trim();
    }
    catch (Exception e){}
        return null;

    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        pd.show();
    }

    @Override
    protected void onPostExecute(Void s) {
        super.onPostExecute(s);
        if(re.equalsIgnoreCase("success")) {
            try {
                f=new File(l.getFilesDir(),"email.txt");
                f1=new File(l.getFilesDir(),"pass.txt");
                fw=new FileWriter(f,false);
                bw=new BufferedWriter(fw);
                fw1=new FileWriter(f1,false);
                bw1=new BufferedWriter(fw1);
                bw.write(em);
                bw.close();
                bw1.write(pass);
                bw1.close();
            }catch (Exception d){}
            Intent i=new Intent(l,loginsuc.class);
            l.startActivity(i);

        }
       else if (re.equalsIgnoreCase("falsea")) {
            ad.setIcon(R.drawable.cross);
            ad.setMessage("Email not registered");
            ad.show();
            pd.cancel();
            pd.dismiss();
        }
        else if(re.equalsIgnoreCase("falsep"))
        {
            ad.setIcon(R.drawable.cross);
            ad.setMessage("Incorrect Password");
            ad.show();
            pd.cancel();
            pd.dismiss();
        }
        else
        {
            ad.setIcon(R.drawable.cross);
            ad.setMessage("Error in connection");
            ad.show();
            pd.cancel();
            pd.dismiss();
        }
pd.cancel();
        pd.dismiss();
    }

}
