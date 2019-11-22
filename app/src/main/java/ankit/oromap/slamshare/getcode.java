package ankit.oromap.slamshare;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;

import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;

/**
 * Created by ankit on 18/12/17.
 */

public class getcode extends AsyncTask<Void,Void,Void> {
    private FileWriter fw;
    private String result;
    private BufferedWriter bw;
    private File f;
    private Toast t;
    public asyncinterface as;
    private ProgressDialog pd;
    public void ini(ProgressDialog pd,Context c)
    {
        this.pd=pd;
        t=Toast.makeText(c,"",Toast.LENGTH_LONG);
        f=new File(c.getFilesDir(),"code.txt");
    }

    @Override
    protected Void doInBackground(Void... voids) {
        try
        {
            URL u=new URL("http://oromap.000webhostapp.com/getcode.php");
            HttpURLConnection uc=(HttpURLConnection)u.openConnection();
            DataInputStream d=new DataInputStream(uc.getInputStream());
            result=d.readLine().trim();
        }catch (Exception f){}
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
        if(result.isEmpty())
        {
            t.setText("Error in connection");
            t.show();
        }
        else {
            try {
                fw = new FileWriter(f,false);
                bw = new BufferedWriter(fw);
                bw.write(result);
                bw.close();
            } catch (Exception d) {
            }
            as.codedone();
        }
        pd.cancel();
        pd.dismiss();
    }

}
