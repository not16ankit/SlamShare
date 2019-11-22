package ankit.oromap.slamshare;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;

import java.io.DataInputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by ankit on 20/12/17.
 */

public class getdetailsofslambook extends AsyncTask<String,Integer,Void> {
    private ProgressDialog pd;
    private String sharedto[];
    private String ucon="";
    private String filledby[];
    private boolean bb=false;
    private String em="";
    private String pass="";
    private String friendlist="";
    public inidetailsofslambook inidetailsofslambook;
    private String filledlist="";
    private int friendlistcontrol=0;
    private String extra="";
    private int filledcontrol=0;
    private String nameresult="";
    private String slambook="";
    private String[] emails;
    private String users="";
    private String[] filledbyemails;
    private String[] filledbyusernames;
    private String[] filledbynames;
    private String[] usernames;
    private String[] names;
    private Toast t;
    public void ini(ProgressDialog pd,Toast t)
    {
        this.t=t;
this.pd=pd;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        pd.show();
    }

    @Override
    protected Void doInBackground(String... strings) {
        em=strings[0];
        pass=strings[1];
        slambook=strings[2];
        URL u;
        HttpURLConnection uc;
        DataInputStream data;
        InputStream is;
        try
        {
            u=new URL("http://oromap.000webhostapp.com/getfriendsharedtolist.php?email="+em+"&pass="+pass+"&slambook="+slambook);
            uc=(HttpURLConnection)u.openConnection();
            is=uc.getInputStream();
            data=new DataInputStream(is);
            friendlist=data.readLine().trim();
            u=new URL("http://oromap.000webhostapp.com/getnumberoffriendsshared.php?email="+em+"&pass="+pass+"&slambook="+slambook);
            uc=(HttpURLConnection)u.openConnection();
            is=uc.getInputStream();
            data=new DataInputStream(is);
            extra=data.readLine().trim();
            friendlistcontrol=Integer.parseInt(extra);
            this.usernames=new String[friendlistcontrol];
            this.names=new String[friendlistcontrol];
            this.emails=new String[friendlistcontrol];
           u=new URL("http://oromap.000webhostapp.com/getnumberoffriendsfilled.php?email="+em+"&pass="+pass+"&slambook="+slambook);
            uc=(HttpURLConnection)u.openConnection();
            is=uc.getInputStream();
            data=new DataInputStream(is);
            ucon=data.readLine().trim();
            filledcontrol=Integer.parseInt(ucon);
            this.filledbyusernames = new String[filledcontrol];
            this.filledbynames = new String[filledcontrol];
            this.filledbyemails = new String[filledcontrol];
            u=new URL("http://oromap.000webhostapp.com/getfilledbylist.php?email="+em+"&pass="+pass+"&slambook="+slambook);
            uc=(HttpURLConnection)u.openConnection();
            is=uc.getInputStream();
            data=new DataInputStream(is);
            filledlist=data.readLine().trim();
            int use3=0;
            String ex1="";
            for(int s=0;s<friendlist.length();s++)
            {
                char c=friendlist.charAt(s);
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
            for(int s=0;s<friendlistcontrol;s++)
            {
                u= new URL("http://oromap.000webhostapp.com/getusernameexternal.php?email="+em+"&pass="+pass+"&user="+emails[s]);
                uc=(HttpURLConnection) u.openConnection();
                is=uc.getInputStream();
                data=new DataInputStream(is);
                users=data.readLine().trim();
                this.usernames[s]=users;
            }
            for(int s=0;s<friendlistcontrol;s++) {
                u = new URL("http://oromap.000webhostapp.com/getfriendname.php?email=" + em + "&pass=" + pass + "&friendemail=" + usernames[s]);
                uc = (HttpURLConnection) u.openConnection();
                is = uc.getInputStream();
                data = new DataInputStream(is);
                nameresult = data.readLine().trim();
                names[s] = nameresult;
            }
            if(filledcontrol>0)
            {
                int   use4=0;
                String ex4="";
                for(int s=0;s<filledlist.length();s++)
                {
                    char c=filledlist.charAt(s);
                    if(c=='^')
                    {
                        this.filledbyemails[use4]=ex4;
                        ex4="";
                        use4++;
                    }
                    else
                    {
                        ex4=ex4.concat(String.valueOf(c));
                    }
                }
                for(int s=0;s<filledcontrol;s++)
                {
                    u= new URL("http://oromap.000webhostapp.com/getusernameexternal.php?email="+em+"&pass="+pass+"&user="+filledbyemails[s]);
                    uc=(HttpURLConnection) u.openConnection();
                    is=uc.getInputStream();
                    data=new DataInputStream(is);
                    users=data.readLine().trim();
                    this.filledbyusernames[s]=users;
                }
                for(int s=0;s<filledcontrol;s++) {
                    u = new URL("http://oromap.000webhostapp.com/getfriendname.php?email=" + em + "&pass=" + pass + "&friendemail=" + filledbyusernames[s]);
                    uc = (HttpURLConnection) u.openConnection();
                    is = uc.getInputStream();
                    data = new DataInputStream(is);
                    nameresult = data.readLine().trim();
                    filledbynames[s] = nameresult;
                }
            }
        }catch (Exception f){}
        bb=true;
        return null;
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);
        pd.cancel();
        pd.dismiss();
        if(bb) {
            inidetailsofslambook.ini(usernames, names, filledbyusernames, filledbynames, filledbyemails);
        }else
        {
            t.setText("Failed to load");
        }
    }

    }