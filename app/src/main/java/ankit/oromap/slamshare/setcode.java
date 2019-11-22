package ankit.oromap.slamshare;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;

import java.io.DataInputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;

/**
 * Created by ankit on 11/12/17.
 */

public class setcode extends AsyncTask<String,Void,Void> {
    private String number="";
    private String code="";
    private String result="";
    private Toast t;
    private Context l;
    private ProgressDialog pd;
    private AlertDialog.Builder ad;
    public iniforupload up;
    private LayoutInflater li;
    private String use="";
    @Override
    protected Void doInBackground(String... strings) {
        number=strings[0];
        number=number.replace("+","").trim();
        code=strings[1];
        use=strings[2];
        try
        {
            URL u=new URL("http://oromap.000webhostapp.com/setcode123456789987654321.php?number="+number+"&systemcallcode="+code);
            HttpURLConnection uc=(HttpURLConnection) u.openConnection();
            uc.setConnectTimeout(30000);
            InputStream is=uc.getInputStream();
            DataInputStream data=new DataInputStream(is);
            result=data.readLine().trim();
        }catch (Exception d){}
        return null;
    }
public void ini(Context context, AlertDialog.Builder ad,ProgressDialog pd,LayoutInflater li)
{
    this.pd=pd;
    this.l=context;
    this.li=li;
    this.ad=ad;
    this.t=Toast.makeText(context,"Error",Toast.LENGTH_LONG);
}
    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);
        if(use.equalsIgnoreCase("process"))
        {
            if(result.equalsIgnoreCase("setted"))
            {
                pd.cancel();
                pd.dismiss();
                up.upload();
            }
            else
            {
                pd.cancel();
                pd.dismiss();
                ad.setNegativeButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                        dialogInterface.cancel();
                    }
                });
                ad.setMessage("Error in connection");
                ad.show();
            }
        }
        else {
            if (result.equalsIgnoreCase("setted")) {
                pd.dismiss();
                pd.cancel();
                ad.setCancelable(false);
                final View v = li.inflate(R.layout.passreset, null);
                ad.setView(v);
                Button b = (Button) v.findViewById(R.id.buttonforchange);
                b.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        TextInputEditText et = (TextInputEditText) v.findViewById(R.id.resetnewpass1);
                        TextInputEditText et1 = (TextInputEditText) v.findViewById(R.id.resetnewpass2);
                        String pass1 = et.getText().toString();
                        String pass2 = et.getText().toString();
                        ProgressDialog dp = new ProgressDialog(l, R.style.Slert);
                        AlertDialog.Builder da = new AlertDialog.Builder(l, R.style.Slert);
                        if (pass1.isEmpty()) {
                            et.setError("Required Field");
                        } else {
                            if (pass2.isEmpty()) {
                                et1.setError("Required Field");
                            } else {
                                if (!pass1.equals(pass2)) {
                                    et1.setError("Password does not match");
                                } else {
                                    da.setCancelable(false);
                                    da.setMessage("Password reset successfully");
                                    dp.setCancelable(false);
                                    dp.setMessage("Confirming......");
                                    setnewpass n = new setnewpass();
                                    n.ini(l, dp, da);
                                    n.execute(number, code, pass1);
                                }
                            }
                        }
                    }
                });
                ad.show();
            } else {
                pd.cancel();
                pd.dismiss();
                ad.setMessage("Error in connection");
                ad.show();
            }
        }
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        pd.show();
    }
}
