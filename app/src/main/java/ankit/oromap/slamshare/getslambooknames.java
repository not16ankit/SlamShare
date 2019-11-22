package ankit.oromap.slamshare;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;

import java.io.DataInputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;

/**
 * Created by ankit on 18/12/17.
 */

public class getslambooknames extends AsyncTask<String,Void,Void> {
    public inislamnames ini;
    private ProgressDialog pd;
    private String ex1="";
    private String ex="";
    private int val;
    private String result;
    private String res="";
    private Toast t;
    private String dates[];
    private String[] slambooknames;
    private String slambook="";
    public void ini(ProgressDialog pd,Context c)
    {
        this.pd=pd;
        this.t=Toast.makeText(c,"",Toast.LENGTH_LONG);
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        int use=0;
        this.dates=new String[val];
        this.slambooknames=new String[val];
        for(int i=0;i<result.length();i++)
        {
            char c=result.charAt(i);
            if(c=='^')
            {
                slambooknames[use]=ex1;
                use++;
                ex1="";
            }
            else
            {
                ex1=ex1+c;
            }
        }
        use=0;
        for(int i=0;i<res.length();i++)
        {
            char c=res.charAt(i);
            if(c=='^')
            {
                dates[use]=ex;
                use++;
                ex="";
            }
            else
            {
                ex=ex+c;
            }
        }
        pd.cancel();
        pd.dismiss();
        if (val == 0) {

        } else if (result.isEmpty())
        {

        }
        else
        {
            ini.ininames(slambooknames,dates);
        }
        super.onPostExecute(aVoid);
    }

    @Override
    protected void onPreExecute() {
        pd.show();
        super.onPreExecute();
    }

    @Override
    protected Void doInBackground(String... strings) {
        String em=strings[0];
        String pass=strings[1];
        t.show();
        try {
            URL uo;
            HttpURLConnection uco;
            InputStream iso;
            DataInputStream datao;
            URL u = new URL("http://oromap.000webhostapp.com/getnumberofslambooks.php?email=" + em + "&pass=" + pass);
            HttpURLConnection uc = (HttpURLConnection) u.openConnection();
            uc.setConnectTimeout(30000);
            InputStream is = uc.getInputStream();
            DataInputStream d = new DataInputStream(is);
            String condition = d.readLine().trim();
            val=Integer.parseInt(condition);
            URL u1 = new URL("http://oromap.000webhostapp.com/getslambooksnames.php?email=" + em + "&pass=" + pass);
            HttpURLConnection uc1 = (HttpURLConnection) u1.openConnection();
            uc1.setConnectTimeout(30000);
            InputStream is1 = uc1.getInputStream();
            DataInputStream d1 = new DataInputStream(is1);
            result=d1.readLine().trim();
            String op="";
            for(int y=0;y<val;y++)
            {
                op="";
                for(int k=0;k<result.length();k++)
                {
                    char c=result.charAt(k);
                    if(c=='^')
                    {
                        slambook=op;
                        break;
                    }
                    else
                    {
                        op=op+c;
                    }
                }
                try
                {
                    uo=new URL("http://oromap.000webhostapp.com/getdate.php?email="+em+"&pass="+pass+"&slambook="+slambook);
                    uco=(HttpURLConnection)uo.openConnection();
                    iso=uco.getInputStream();
                    datao=new DataInputStream(iso);
                    String arr=datao.readLine().trim();
                    res=res+arr+"^";
                }catch (Exception f){}
            }
        }catch (Exception d){}
        return null;
    }

}
