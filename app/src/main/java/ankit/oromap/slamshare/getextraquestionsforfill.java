package ankit.oromap.slamshare;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.ProgressBar;
import android.widget.Toast;

import java.io.DataInputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by ankit on 28/12/17.
 */

public class getextraquestionsforfill extends AsyncTask<String,Void,Void> {
    private String em="";
    private String pass="";
    private String emailofsender="";
    private String slambooknumber="";
    private int numberofextras=0;
    public inianswersforfill ini;
    private String slambook="";
    private String[] questions;
    Toast t;
    private String[] extras;
    public void ini(Context con)
    {
        this.t=Toast.makeText(con,"",Toast.LENGTH_LONG);
    }
    @Override
    protected Void doInBackground(String... strings) {
        em=strings[0];
        pass=strings[1];
        slambook=strings[2];
        slambooknumber=strings[3];
        emailofsender=strings[4];
        URL u;
        int use=0;
        HttpURLConnection uc;
        InputStream is;
        DataInputStream data;
        try
        {
            u=new URL("http://oromap.000webhostapp.com/getnumberofextrasrece.php?email="+em+"&pass="+pass+"&slambooknumber="+slambooknumber);
            uc=(HttpURLConnection)u.openConnection();
            is=uc.getInputStream();
            data=new DataInputStream(is);
            String use45=data.readLine().trim();
            numberofextras=Integer.parseInt(use45);
            extras=new String[numberofextras];
            for (int i=1;i<=numberofextras;i++)
            {
                u=new URL("http://oromap.000webhostapp.com/getextras17282012.php?email="+emailofsender+"&slambook="+slambook+"&extra="+i);
                uc=(HttpURLConnection)u.openConnection();
                is=uc.getInputStream();
                data=new DataInputStream(is);
                extras[use]=data.readLine().trim();
                use++;
            }
        }catch (Exception d){}
        return null;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);
        int t24=0;
        String foruse[]={"My Name","My Friends Call Me","My Best Friend","My Idol","My Phone Number","My Email","My Birthday is on","I studied at","My hobbies","My Aim in life","My Favorite Food","Places I want to visit","My Favorite Singer","My Favorite Color","My Favorite Sport","I pass my time by","Few Words about our friendship"};
        this.questions=new String[17+numberofextras];
        for(int i=0;i<17;i++)
        {
            questions[i]=foruse[i];
        }
        if(numberofextras>0) {
            for (int i = 17; i < 17 + numberofextras; i++) {
                questions[i] = extras[t24];
            }
        }
       ini.iniquestion(questions);
    }
}
