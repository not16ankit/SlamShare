package ankit.oromap.slamshare;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.provider.Settings;
import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;

import org.w3c.dom.Text;
import com.google.android.material.snackbar.*;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import androidx.swiperefreshlayout.*;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import java.util.concurrent.TimeUnit;


public class account extends Activity implements asyncinterface,changepassinter,inidetails,iniforupload{
    private  String em="";
    boolean tr=false;
    Snackbar ad12;
    private AlertDialog.Builder bd;
    private String pass="";
    String test="";
    private String oldphonenumber="";
    String test2="";
    BufferedWriter bw;
    private String preusername;
    FileWriter fwnumber;
    private String newusername;
    BufferedWriter bwnumber;
    private ProgressDialog pd12;
    FileWriter fwex;
    BufferedWriter bwex;
    FileWriter fw3;
    BufferedWriter bw3;
    File f1;
    File f3;
    File f2;
    private AlertDialog ad;
    private  ProgressDialog l;
    private String nphone="";
    private String ex1s="";
    private String nname="";
    private String code="";
    private TextInputEditText newphone;
    private TextInputEditText username;
    private TextInputEditText username12;
    private AutoCompleteTextView ex;
    private ProgressDialog use;
    private AlertDialog.Builder da;
   private  TextInputEditText nameedit;
   private TextInputEditText numberedit;
    private ProgressDialog pd;
    int i=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.account);
        pd = new ProgressDialog(this, R.style.Slert);
        pd.setCancelable(false);
        pd.setMessage("Getting user details......");
        use=new ProgressDialog(this,R.style.Slert);
        pd12=new ProgressDialog(this,R.style.Slert);
        pd12.setMessage("Updating user details......");
        try {
            File f = new File(getFilesDir(), "email.txt");
            FileReader fr = new FileReader(f);
            BufferedReader br = new BufferedReader(fr);
            em = br.readLine().trim();
            br.close();
            pass=getIntent().getExtras().getString("pass");
            Toast.makeText(this,pass,Toast.LENGTH_LONG).show();
            FileWriter fw=new FileWriter(f1);
            bw=new BufferedWriter(fw);
            File name = new File(getFilesDir(), "name.txt");
            FileWriter fw2 = new FileWriter(name, false);
            BufferedWriter bw2 = new BufferedWriter(fw2);
            bw2.write("");
            bw2.close();
            File number= new File(getFilesDir(), "number.txt");
            fwnumber = new FileWriter(number, false);
            bwnumber = new BufferedWriter(fwnumber);
            bwnumber.write("");
            bwnumber.close();
            File exfile = new File(getFilesDir(), "ex.txt");
            fwex = new FileWriter(exfile, false);
            bwex= new BufferedWriter(fwex);
            bwex.write("");
            bwex.close();
        } catch (Exception e) {}
        username=(TextInputEditText)findViewById(R.id.usernameeditnew);
        nameedit=(TextInputEditText)findViewById(R.id.nameeditnew);
        numberedit=(TextInputEditText)findViewById(R.id.numbereditnew);
        ex=(AutoCompleteTextView) findViewById(R.id.ex);
        username12=(TextInputEditText)findViewById(R.id.nameeditusername12);
        ArrayAdapter<String> aa=new ArrayAdapter<String>(this,R.layout.countrycodeview,getResources().getStringArray(R.array.country_code));
        ex.setAdapter(aa);
      final  getname name12 = new getname();
      name12.inid=this;
        name12.ini(pd,this);
        name12.execute(em, pass);
        final Snackbar snack2=Snackbar.make(findViewById(R.id.accountlayout),"Email cannot be changed",Snackbar.LENGTH_LONG);
        username.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                snack2.show();
            }
        });
      final  SwipeRefreshLayout swipe=(SwipeRefreshLayout)findViewById(R.id.swipelayout);
        swipe.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                pd.setMessage("Refreshing.....");
                name12.execute(em, pass);
               swipe.setRefreshing(false);
            }
        });
    }

    @Override
    public void inidetails(String ex, String em, String name, String number,String username) {
        try
        {
            File numberfile= new File(getFilesDir(), "number.txt");
            fwnumber = new FileWriter(numberfile, false);
            bwnumber = new BufferedWriter(fwnumber);
            bwnumber.write(number);
            bwnumber.close();
            File exfile = new File(getFilesDir(), "ex.txt");
            fwex = new FileWriter(exfile, false);
            bwex= new BufferedWriter(fwex);
            bwex.write(ex);
            bwex.close();
        }catch (Exception d){}
        this.username.setText(em);
        nameedit.setText(name);
        numberedit.setText(number);
        this.ex.setText(ex);
        this.username12.setText(username);
        this.preusername=username;
        this.oldphonenumber=ex+number;
    }

    public void uploaddetails(View view) {
        l=new ProgressDialog(this,R.style.Slert);
        l.setMessage("Checking details.....");
        l.setCancelable(false);
        l.setIcon(R.drawable.icon);
        AutoCompleteTextView ex1=(AutoCompleteTextView) findViewById(R.id.ex);
        ex1s=ex1.getText().toString().trim();
        TextInputEditText newname=(TextInputEditText)findViewById(R.id.nameeditnew);
        nname=newname.getText().toString().trim().replace(" ","=");
        newphone=(TextInputEditText)findViewById(R.id.numbereditnew);
        nphone=newphone.getText().toString().trim();
      newusername=username12.getText().toString().trim();
        if(Patterns.PHONE.matcher(nphone).matches())
        {
            try
            {
                File f=new File(getFilesDir(),"number.txt");
                FileReader fr=new FileReader(f);
                BufferedReader br=new BufferedReader(fr);
                test=br.readLine().trim();
                File f2=new File(getFilesDir(),"ex.txt");
                FileReader fr2=new FileReader(f2);
                BufferedReader br2=new BufferedReader(fr2);
                test2=br2.readLine().trim();
                br.close();
                br2.close();
            }catch (Exception d){}
            if(test.equals(nphone)&&test2.equals(ex1s)&&newusername.equals(preusername))
            {
                uploaddetails up=new uploaddetails();
                up.ini(this,pd12);
                up.execute(em,pass,nname,nphone,ex1s,newusername,code);
            }
            else
            {
                l.show();
                confirmphoneavailabilty p=new confirmphoneavailabilty();
                p.asyncinterface=this;
                p.execute(ex1s+nphone,"thakurankit036@gma27394873847832",username12.getText().toString());
            }

        }
        else
        {
            newphone.setError("Invalid");
        }
    }
    public void changepass(View view) {
        View v=getLayoutInflater().inflate(R.layout.adview,null);
        final  TextInputEditText oldp=(TextInputEditText)v.findViewById(R.id.oldpass);
        final  TextInputEditText newp1=(TextInputEditText)v.findViewById(R.id.newpass1);
        final  TextInputEditText newp2=(TextInputEditText)v.findViewById(R.id.newpass2);
        final  ProgressDialog pdpass=new ProgressDialog(this,R.style.Slert);
        final Button b=(Button)v.findViewById(R.id.butnext);
        final Button b1=(Button)v.findViewById(R.id.butcan);
        pdpass.setMessage("Changing password......");
        AlertDialog.Builder ad=new AlertDialog.Builder(this,R.style.Slert);
        ad.setView(v);
      final  changepass change=new changepass();
        change.ank=this;
       final AlertDialog df=ad.create();
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String oldpass=oldp.getText().toString().trim();
                String newpass1=newp1.getText().toString().trim();
                String newpass2=newp2.getText().toString().trim();
                if(oldpass.isEmpty())
                {
                    oldp.setError("Field Required");
                }
                else
                {
                    if(newpass1.isEmpty())
                    {
                        newp1.setError("Field Required");
                    }
                    else
                    {
                        if(newpass2.isEmpty())
                        {
                            newp2.setError("Field Required");
                        }
                        else
                        {
                            if(newpass1.equals(newpass2))
                            {
                                File f=new File(getFilesDir(),"pass.txt");
                                FileReader fr;
                                BufferedReader brr=null;
                                String oldpassforwrite="";
                                try
                                {
                                    fr=new FileReader(f);
                                    brr=new BufferedReader(fr);
                                    oldpassforwrite=brr.readLine().trim();
                                    brr.close();
                                }catch (Exception ff){}
                                if(oldpass.equalsIgnoreCase(oldpassforwrite))
                                {
                                    da=new AlertDialog.Builder(getApplicationContext(),R.style.Slert);
                                    Toast t=Toast.makeText(getApplicationContext(),"",Toast.LENGTH_LONG);
                                    change.ini(pdpass,getApplicationContext(),da,oldpassforwrite);
                                    change.execute(oldpass,newpass1,em);
                                }
                                else
                                {
                                    oldp.setError("Wrong Old Password");
                                }
                            }
                            else
                            {
                                newp2.setError("Passwords do not match");
                            }
                        }
                    }
                }
            }
        });
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                df.dismiss();
                df.cancel();
            }
        });
        df.show();
    }

    @Override
    public void inipass(final String pass) {
        AlertDialog.Builder tp=new AlertDialog.Builder(this,R.style.Slert);
        tp.setIcon(R.drawable.passtick);
        tp.setCancelable(false);
        tp.setNegativeButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
           dialogInterface.dismiss();
           dialogInterface.cancel();
                try {
                    File f45=new File(getFilesDir(),"pass.txt");
                    FileWriter fw45=new FileWriter(f45,false);
                    BufferedWriter bw45=new BufferedWriter(fw45);
                    bw45.write(pass);
                    bw45.close();
                }catch (Exception d){}
            }
        });
        tp.setMessage("Password is changed");
        tp.show();
    }

    @Override
    public void processFinish(String out) {
        l.cancel();
        l.dismiss();
        if(test.equals(nphone)&&test2.equals(ex1s))
        {
            if (out.equalsIgnoreCase("availablep+e+u")||out.equalsIgnoreCase("notavailablep")) {
                uploaddetails up = new uploaddetails();
                up.ini(this, pd12);
                up.execute(em, pass, nname, nphone, ex1s, newusername,"ankit");
            }  else if (out.equalsIgnoreCase("notavailablepu")) {
                username12.setError("Username already acquired");
            }
            else if(out.equalsIgnoreCase("notavailableu"))
            {
                username12.setError("Username already acquired");
            }else {
                AlertDialog.Builder ho = new AlertDialog.Builder(this, R.style.Slert);
                ho.setMessage("Error in connection. Please Retry.");
                ho.setNegativeButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                        dialogInterface.cancel();
                    }
                });
                ho.show();
            }
        }
        else {
            if (preusername.equals(newusername)) {
                if (out.equalsIgnoreCase("availablep+e+u")||out.equalsIgnoreCase("notavailableu")) {
                  before();
                } else if (out.equalsIgnoreCase("notavailablep")) {
                    newphone.setError("Phone number already taken");
                } else if (out.equalsIgnoreCase("notavailablepu")) {
                    newphone.setError("Phone Number already registerd");
                    username12.setError("Username already acquired");
                }else {
                    AlertDialog.Builder ho = new AlertDialog.Builder(this, R.style.Slert);
                    ho.setMessage("Error in connection. Please Retry.");
                    ho.setNegativeButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            dialogInterface.dismiss();
                            dialogInterface.cancel();
                        }
                    });
                }
            } else {
                if (out.equalsIgnoreCase("availablep+e+u")) {
                    before();
                } else if (out.equalsIgnoreCase("notavailablep")) {
                    newphone.setError("Phone number already taken");
                } else if (out.equalsIgnoreCase("notavailablepu")) {
                    newphone.setError("Phone Number already registerd");
                    username12.setError("Username already acquired");
                } else if (out.equalsIgnoreCase("notavailableu")) {
                    username12.setError("Username already acquired");
                } else {
                    AlertDialog.Builder ho = new AlertDialog.Builder(this, R.style.Slert);
                    ho.setMessage("Error in connection. Please Retry.");
                    ho.setNegativeButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            dialogInterface.dismiss();
                            dialogInterface.cancel();
                        }
                    });
                    ho.show();
                }
            }
        }
    }
    public void before()
    {
        use.setMessage("Sending OTP to "+ex1s+nphone+"......");
        use.show();
        bd=new AlertDialog.Builder(this);
        bd.setCancelable(false);
        View v=getLayoutInflater().inflate(R.layout.accountverifywait,null);
      final  Button b=(Button)v.findViewById(R.id.butresend);
       final Button b1=(Button)v.findViewById(R.id.butcancel);
        final TextView tv=(TextView)v.findViewById(R.id.progress3434);
        bd.setView(v);
        ad=bd.create();
        final Context k=this;
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!tr)
                {
                    Toast.makeText(getApplicationContext(),"Please wait till auto-detection ends",Toast.LENGTH_LONG).show();
                }
                else
                {
                    use.setMessage("Sending OTP.......");
                    use.show();
                    extra(tv,b,b1);
                }
            }
        });
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ad.cancel();
                ad.dismiss();
            }
        });
        final setcode co=new setcode();
        co.up=this;
        PhoneAuthProvider.getInstance().verifyPhoneNumber(ex1s + nphone, 60, TimeUnit.SECONDS, this, new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
            @Override
            public void onVerificationCompleted(PhoneAuthCredential phoneAuthCredential) {
                code=phoneAuthCredential.getSmsCode();
                ProgressDialog pd12=new ProgressDialog(k,R.style.Slert);
                pd12.setMessage("Loading......");
                co.ini(getApplicationContext(),new AlertDialog.Builder(getApplicationContext(),R.style.Slert),pd12,getLayoutInflater());
                co.execute(oldphonenumber,phoneAuthCredential.getSmsCode(),"process");
            }

            @Override
            public void onVerificationFailed(FirebaseException e) {

            }

            @Override
            public void onCodeSent(String s, PhoneAuthProvider.ForceResendingToken forceResendingToken) {
                super.onCodeSent(s, forceResendingToken);
                use.cancel();
                use.dismiss();
                new CountDownTimer(60000,1000)
                {
                    @Override
                    public void onTick(long l) {
                        tv.setText("00:"+l/1000);
                    }

                    @Override
                    public void onFinish() {
                        tv.setText("Auto-detection timed out !");
                        tr=true;
                    }
                }.start();
                ad.show();
            }
        });
    }

    @Override
    public void upload() {
        ProgressDialog pd12=new ProgressDialog(this,R.style.Slert);
        pd12.setMessage("Updating user details......");
        uploaddetails up = new uploaddetails();
        up.ini(this, pd12);
        up.execute(em, pass, nname, nphone, ex1s,newusername,"ankit");
    }

    public void extra(final TextView tv, final Button b, final Button b1)
    {
     final Context k=this;
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!tr)
                {
                    Toast.makeText(getApplicationContext(),"Please wait till auto-detection ends",Toast.LENGTH_LONG).show();
                }
                else
                {
                    use.setMessage("Sending OTP.....");
                    use.show();
                    extra(tv,b,b1);
                }
            }
        });
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ad.dismiss();
                ad.cancel();
            }
        });
     tr=false;
        final setcode co=new setcode();
        co.up=this;
        PhoneAuthProvider.getInstance().verifyPhoneNumber(ex1s + nphone, 60, TimeUnit.SECONDS, this, new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
            @Override
            public void onVerificationCompleted(PhoneAuthCredential phoneAuthCredential) {
                code=phoneAuthCredential.getSmsCode();
                ProgressDialog pd12=new ProgressDialog(k,R.style.Slert);
                pd12.setMessage("Loading......");
                co.ini(getApplicationContext(),new AlertDialog.Builder(getApplicationContext(),R.style.Slert),pd12,getLayoutInflater());
                co.execute(oldphonenumber,phoneAuthCredential.getSmsCode(),"process");
            }

            @Override
            public void onVerificationFailed(FirebaseException e) {

            }

            @Override
            public void onCodeSent(String s, PhoneAuthProvider.ForceResendingToken forceResendingToken) {
                super.onCodeSent(s, forceResendingToken);
                use.cancel();
                use.dismiss();
                Toast.makeText(getApplicationContext(),"OTP has been sent again",Toast.LENGTH_LONG).show();
                new CountDownTimer(60000,1000)
                {
                    @Override
                    public void onTick(long l) {
                        tv.setText("00:"+l/1000);
                    }

                    @Override
                    public void onFinish() {
                        tv.setText("Auto-detection timed out !");
                        tr=true;
                    }
                }.start();
            }
        });
    }
    public void reverse123(View view)
    {
        ad.dismiss();
        ad.cancel();
    }

    @Override
    public void codedone() {

    }
}























