package ankit.oromap.slamshare;

import android.os.AsyncTask;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ProgressBar;
import android.widget.Toast;

import java.io.DataInputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by ankit on 21/12/17.
 */

public class getanswers extends AsyncTask<String,Integer,Void>{
    private ProgressBar pb;
    private String em="";
    private String pass="";
    private String friendemail="";
    private String slambook="";
    private FrameLayout fl;
    private String answers="";
    private String[] answerarray;
    private int answercontrol=0;
    private String[] questions;
    private String[] extras;
    public inianswers inians;
    private Toast t;
    public void ini(ProgressBar pb,FrameLayout fl,Toast t)
    {
        this.t=t;
        this.fl=fl;
        this.pb=pb;
    }
    @Override
    protected Void doInBackground(String... strings) {
        em=strings[0];
        pass=strings[1];
        slambook=strings[2];
        friendemail=strings[3];
        URL u;
        HttpURLConnection uc;
        InputStream is;
        DataInputStream data;
        try
        {
            u=new URL("http://oromap.000webhostapp.com/getnumberofextras.php?email="+em+"&pass="+pass+"&slambook="+slambook);
            uc=(HttpURLConnection)u.openConnection();
            is=uc.getInputStream();
            data=new DataInputStream(is);
            String answercontrol1=data.readLine().trim();
            answercontrol=Integer.parseInt(answercontrol1);
            this.answerarray=new String[17+answercontrol];
            int use=0;
            this.extras=new String[answercontrol];
            for(int i=1;i<=17+answercontrol;i++) {
                u = new URL("http://oromap.000webhostapp.com/getanswers.php?email=" + em + "&pass=" + pass + "&slambook=" + slambook + "&friendemail=" + friendemail+"&answernumber="+i);
                uc = (HttpURLConnection) u.openConnection();
                is = uc.getInputStream();
                data = new DataInputStream(is);
                answers = data.readLine().trim();
                answerarray[use]=answers;
                use++;
            }
            t.setText(answers);
            t.show();
            int o=0;
            int slam=1;
            for(int t=0;t<answercontrol;t++)
            {
                u=new URL("http://oromap.000webhostapp.com/getextras.php?email="+em+"&pass="+pass+"&slambook="+slambook+"&extra="+slam);
                uc=(HttpURLConnection)u.openConnection();
                is=uc.getInputStream();
                data=new DataInputStream(is);
                extras[o]=data.readLine().trim();
                slam++;
                o++;
            }
        }catch (Exception f){}
        return null;
    }
    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);
        pb.setVisibility(View.INVISIBLE);
        fl.setVisibility(View.VISIBLE);
        String ex="";
        int yop=0;
        this.questions=new String[17+answercontrol];
        String foruse[]={"My Name","My Friends Call Me","My Best Friend","My Idol","My Phone Number","My Email","My Birthday is on","I studied at","My hobbies","My Aim in life","My Favorite Food","Places I want to visit","My Favorite Singer","My Favorite Color","My Favorite Sport","I pass my time by","Few Words about our friendship"};
        for(int p=0;p<17;p++)
        {
            questions[p]=foruse[p];
        }
        if(answercontrol>0) {
            for (int i = 17; i <= 16 + answercontrol; i++) {
                questions[i] = extras[yop];
                yop++;
            }
        }
        inians.inianswers(questions,answerarray);
    }

}
