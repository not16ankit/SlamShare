package ankit.oromap.slamshare;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.facebook.AccessToken;
import com.facebook.GraphRequestAsyncTask;
import com.facebook.Profile;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import androidx.annotation.*;
import androidx.fragment.app.*;
import android.content.DialogInterface;
import android.content.Intent;
import android.media.FaceDetector;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.provider.Settings;

import androidx.annotation.CallSuper;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;
import android.util.Log;
import android.util.Patterns;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.EditText;
import android.widget.MultiAutoCompleteTextView;
import android.widget.RelativeLayout;
import android.widget.TableLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookDialog;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.login.Login;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.bottomnavigation.BottomNavigationItemView;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.FirebaseException;
import com.google.firebase.FirebaseTooManyRequestsException;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FacebookAuthProvider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseAuthProvider;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import org.json.JSONObject;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.lang.reflect.Method;
import java.sql.Array;
import java.util.Arrays;
import java.util.Collection;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
public class Container2 extends AppCompatActivity implements asyncinterface{
static  int v=0;
static View rootView =null;
Pattern p;
Matcher m;
static Context vo;
static int tr=0;
final String emailpattern="^[_A-Za-z0-9-]+(.[_A-Za-z0-9-]+)*@[A-Za-z0-9]+(//.[A-Za-z0-9]+)*(//.[A-Za-z{2,})$";
FirebaseAuth fire;
String firstn="";
    String lastn="";
    static FragmentActivity fa;
    EditText et1;
    EditText et2;
    RequestQueue rq;
    private boolean trr=false;
    EditText et3;
    private ProgressDialog use;
    private AlertDialog.Builder bd;
    private EditText username;
    private String usernames;
    private AlertDialog.Builder da;
    EditText et4;
    EditText et5;
    EditText et6;
    EditText et7;
    EditText et8;
    private AlertDialog ad1;
    String email="";
    String phone="";
    String secques="";
    String ex="";
    private PhoneAuthProvider.ForceResendingToken force;
    String verid;
    String secans="";
    String pass="";
    private CallbackManager cm;
    private static LoginManager lm;
    static Toast t;
    static  String e="ankit";
    static CallbackManager cb;
    AlertDialog.Builder ad;
    ProgressDialog pd;
    boolean mobiledata;
    private ProgressDialog ko;
    boolean wifi;
    private SectionsPagerAdapter mSectionsPagerAdapter;
static BottomNavigationView bm;
ViewGroup vg;
    private ViewPager mViewPager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_container2);
        rq= Volley.newRequestQueue(this);
        getWindow().setStatusBarColor(getResources().getColor(R.color.colorPrimaryDark));
        final ViewGroup vg = this.mViewPager;
        Button b = (Button) findViewById(R.id.next);
        final ViewGroup con = this.mViewPager;
        vo=this;
        da=new AlertDialog.Builder(this,R.style.Slert);
        bd=new AlertDialog.Builder(this,R.style.Slert);
        use=new ProgressDialog(this,R.style.Slert);
        bm = (BottomNavigationView) findViewById(R.id.an123);
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());
        mViewPager = (ViewPager) findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);
        fire=FirebaseAuth.getInstance();
        CallbackManager cb=CallbackManager.Factory.create();
        String res=getIntent().getStringExtra("valueout");
        if(true) {
            AlertDialog.Builder bg = new AlertDialog.Builder(this, R.style.Slert);
            bg.setMessage("Error in connection");
            ProgressDialog df = new ProgressDialog(this, R.style.Slert);
            df.setCancelable(false);
            df.setMessage("Connecting.....");
            df.show();
            internet inet = new internet();
            inet.ini(bg, df);
            inet.execute();
            PhoneAuthCredential credits;
            ko = new ProgressDialog(this, R.style.Slert);
            ko.setMessage("Checking details......");
        }
        else {
            PhoneAuthCredential credits;
            ko = new ProgressDialog(this, R.style.Slert);
            ko.setMessage("Checking details......");
        }
    }
    public void passwordreset(View view)
    {
        Intent i=new Intent(this,forgotpassword.class);
        startActivity(i);
    }
    public void facebook(AccessToken ac)
    {
        AuthCredential authCredential= FacebookAuthProvider.getCredential(ac.getToken());
        fire.signInWithCredential(authCredential).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
             if(!task.isSuccessful())
             {
                 Toast.makeText(getApplicationContext(),"Error",Toast.LENGTH_LONG).show();
             }
            }
    });
    }

    @Override
    public void processFinish(String out) {
        Toast.makeText(vo, out, Toast.LENGTH_SHORT).show();
        pd=new ProgressDialog(this,R.style.Slert);
        ko.cancel();
        ko.dismiss();
        et3=(EditText)findViewById(R.id.ed3);
        et4=(EditText)findViewById(R.id.ed4);
        if(out.equalsIgnoreCase("availablep+e+u"))
        {
            da.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    dialogInterface.cancel();
                    dialogInterface.dismiss();
                }
            });
            bd=new AlertDialog.Builder(this);
            bd.setCancelable(false);
            View v=getLayoutInflater().inflate(R.layout.accountverifywait,null);
            final  Button b=(Button)v.findViewById(R.id.butresend);
            final Button b1=(Button)v.findViewById(R.id.butcancel);
            final TextView tv=(TextView)v.findViewById(R.id.progress3434);
            bd.setView(v);
            ad1=bd.create();
            new CountDownTimer(60000,1000)
            {
                @Override
                public void onTick(long l) {
                    tv.setText("00:"+l/1000);
                }

                @Override
                public void onFinish() {
                    tv.setText("Auto-detection timed out !");
                    trr=true;
                }
            }.start();
            final Context k=this;
            b.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(!trr)
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
                    ad1.cancel();
                    ad1.dismiss();
                }
            });
          final  getcode co=new getcode();
       final ProgressDialog fd=new ProgressDialog(this,R.style.AppCompactAlertDialogStyle);
          fd.setMessage("Please Wait.....");
         final Context tat=this;
            co.as=this;
            final AlertDialog.Builder confirm=new AlertDialog.Builder(this,R.style.Slert);
            confirm.setMessage("SlamShare would send a verification OTP to " + String.valueOf(ex + phone));
            confirm.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(final DialogInterface dialogInterface, int i) {
                    use.setMessage("Sending OTP.....");
                    use.show();
                    dialogInterface.cancel();
                    dialogInterface.dismiss();
                    PhoneAuthProvider.getInstance().verifyPhoneNumber(ex + phone, 60, TimeUnit.SECONDS, Container2.this, new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
                        @Override
                        public void onVerificationCompleted(PhoneAuthCredential phoneAuthCredential) {
                            co.ini(fd,tat);
                            co.execute();
                        }


                        @Override
                        public void onVerificationFailed(FirebaseException e) {

                        }

                        @Override
                        public void onCodeSent(String s, PhoneAuthProvider.ForceResendingToken forceResendingToken) {
                            super.onCodeSent(s, forceResendingToken);
                            use.cancel();
                            use.dismiss();
                            ad1.show();
                        }
                    });
                }
            });
            confirm.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    dialogInterface.cancel();
                    dialogInterface.dismiss();
                }
            });
            confirm.show();
        }
        else if(out.equalsIgnoreCase("notavailablep"))
        {
            et4.setError("Phone Number already registerd");
        }
        else if(out.equalsIgnoreCase("notavailablepe"))
        {
            et3.setError("Email already exists");
            et4.setError("Phone Number already registerd");
        }
        else if(out.equalsIgnoreCase("notavailablepeu"))
        {
            et3.setError("Email already exists");
            et4.setError("Phone Number already registerd");
            username.setError("Usesrname already taken");
        }
        else if(out.equalsIgnoreCase("notavailablepu"))
        {
            et4.setError("Phone Number already registerd");
            username.setError("Username already acquired");
        }
        else if(out.equalsIgnoreCase("notavailableeu"))
        {
            et3.setError("Email already registerd");
            username.setError("Username already acquired");
        }
        else if(out.equalsIgnoreCase("notavailableu"))
        {
            username.setError("Username already acquired");
        }
        else if(out.equalsIgnoreCase("notavailablee"))
        {
            et3.setError("Username already acquired");
        }
        else
        {
            AlertDialog.Builder ho=new AlertDialog.Builder(this,R.style.Slert);
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

    @Override
    public void codedone() {
        FileReader fr20;
        FileWriter fw20;
        BufferedWriter bw20;
        BufferedReader br20;
        String code="";
        File f20=new File(getFilesDir(),"code.txt");
        try
        {
         fr20=new FileReader(f20);
         br20=new BufferedReader(fr20);
         code =br20.readLine().trim();
         br20.close();
         fw20=new FileWriter(f20);
         bw20=new BufferedWriter(fw20);
         bw20.write("");
         bw20.close();
        }
        catch (Exception d){}
        Toast.makeText(getApplicationContext(), "Verified Successfully", Toast.LENGTH_LONG).show();
        signupasync s = new signupasync();
        ad1.cancel();
        ad1.dismiss();
        s.ini(firstn, lastn, email, phone, pass,ex, getApplicationContext(), pd, getApplicationContext(), da,usernames,code);
        s.execute();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        cm.onActivityResult(requestCode, resultCode, data);
    }
    public void next(View view)
    {
        et1=(EditText)findViewById(R.id.ed1);
        et2=(EditText)findViewById(R.id.ed2);
        et3=(EditText)findViewById(R.id.ed3);
        et4=(EditText)findViewById(R.id.ed4);
        et6=(EditText)findViewById(R.id.ed6);
        et7=(EditText)findViewById(R.id.ed7);
        et8=(EditText)findViewById(R.id.countrycode);
        username=(EditText)findViewById(R.id.username);
        if(et1.getText().toString().isEmpty()||et1.getText().toString().contains("^"))
        {
            if(et1.getText().toString().isEmpty()) {
                et1.setError("Field Required");
            }
            else
            {
                et1.setError("Invalid Name");
            }
        }
        else {
            if(et2.getText().toString().isEmpty()||et2.getText().toString().contains("^"))
            {
                if(et2.getText().toString().isEmpty()) {
                    et2.setError("Field Required");
                }
                else
                {
                    et2.setError("Invalid Last Name");
                }
            }
            else {
                if (username.getText().toString().isEmpty()) {
username.setError("Required Field");
                } else {
                    if (et3.getText().toString().isEmpty()) {
                        et3.setError("Field Required");
                    } else {
                        if (et8.getText().toString().isEmpty()) {
                            et8.setError("Field Required");
                        } else {
                            if (et4.getText().toString().isEmpty()) {
                                et4.setError("Field Required");
                            } else {
                                if (et6.getText().toString().isEmpty()) {
                                    et6.setError("Field Required");
                                } else {
                                    if (et7.getText().toString().isEmpty()) {
                                        et7.setError("Field Required");
                                    } else {
                                        if (!et6.getText().toString().equals(et7.getText().toString())) {
                                            et6.setError("Passwords does not match");
                                        } else {
                                            if (Patterns.EMAIL_ADDRESS.matcher(et3.getText().toString().trim()).matches()) {
                                                if (Patterns.PHONE.matcher(et4.getText().toString().trim()).matches()) {
                                                    firstn = et1.getText().toString().trim();
                                                    lastn = et2.getText().toString().trim();
                                                    email = et3.getText().toString().trim();
                                                    phone = et4.getText().toString().trim();
                                                    pass = et7.getText().toString().trim();
                                                    ex = et8.getText().toString().trim();
                                                    usernames=username.getText().toString().trim();
                                                    String finalparse = ex + phone;
                                                    confirmphoneavailabilty cony = new confirmphoneavailabilty();
                                                    cony.asyncinterface = this;
                                                    cony.execute(finalparse, email,usernames);
                                                    ko.show();
                                                } else {
                                                    et4.setError("Enter A Valid Number");
                                                }
                                            } else {
                                                et3.setError("Enter A Valid Email");
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        if(tr==0)
        {

        }
        else {
            finish();
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        if(tr==0)
        {

        }
        else {
            finish();
        }
    }

    public void login(View view)
    {
        EditText em=(EditText)findViewById(R.id.email);
        EditText pass=(EditText)findViewById(R.id.password);
        if(em.getText().toString().isEmpty())
        {
            em.setError("Required Field");
        }
        else
        {
            if(pass.getText().toString().isEmpty())
            {
                pass.setError("Required Field");
            }
            else
            {
                tr=1;
                AlertDialog.Builder h=new AlertDialog.Builder(this,R.style.Slert);
                h.setNegativeButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                    dialogInterface.cancel();
                    dialogInterface.dismiss();
                    }
                });
                pd=new ProgressDialog(this,R.style.AppCompactAlertDialogStyle);
                pd.setMessage("Logging you in.......");
                Context con=this;
                loginasync l=new loginasync();
                l.inifuc(pd,em.getText().toString().trim(),em.getText().toString().trim(),con,h);
                l.execute(em.getText().toString().trim(),pass.getText().toString().trim());
            }
        }

    }

    public void fliptologin(MenuItem item) {
        mViewPager.setCurrentItem(R.layout.activity_signup, true);
    }

    public void fliptosignup(MenuItem item) {
        mViewPager.setCurrentItem(mViewPager.getCurrentItem()-1,true);
    }

    public void shifttosignup(View view) {
        mViewPager.setCurrentItem(R.layout.activity_signup,true);
    }
    public void shifttologin(View view) {
        mViewPager.setCurrentItem(mViewPager.getCurrentItem()-1,true);
    }

    public void cancel(View view) {
        RelativeLayout tl=(RelativeLayout)findViewById(R.id.verify);
        tl.setVisibility(View.INVISIBLE);
    }


    public void extra(final TextView tv,final Button b,final Button b1)
    {
        final Context k=this;
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!trr)
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
                ad1.dismiss();
                ad1.cancel();
            }
        });
        trr=false;
        final  getcode co=new getcode();
        final ProgressDialog fd=new ProgressDialog(this,R.style.AppCompactAlertDialogStyle);
        fd.setMessage("Please Wait.....");
        final Context tat=this;
        co.as=this;
        PhoneAuthProvider.getInstance().verifyPhoneNumber(ex + phone, 60, TimeUnit.SECONDS, this, new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
            @Override
            public void onVerificationCompleted(PhoneAuthCredential phoneAuthCredential) {
                co.ini(fd,tat);
                co.execute();
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
                        trr=true;
                    }
                }.start();
            }
        });
    }


    public static class PlaceholderFragment extends Fragment {
        private static final String ARG_SECTION_NUMBER = "section_number";
        public PlaceholderFragment() {
        }
        public static PlaceholderFragment newInstance(int sectionNumber) {
            PlaceholderFragment fragment = new PlaceholderFragment();
            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            fragment.setArguments(args);
            return fragment;
        }

        @Override
        public View onCreateView(final LayoutInflater inflater, final ViewGroup container,
                                 Bundle savedInstanceState) {
            fa=getActivity();
            if(getArguments().getInt(ARG_SECTION_NUMBER)==1)
            {
                rootView= inflater.inflate(R.layout.activity_login, container, false);
            }
            else {
                    rootView = inflater.inflate(R.layout.activity_signup, container, false);
                    String[] a=getResources().getStringArray(R.array.country_code);
                AutoCompleteTextView auto=(AutoCompleteTextView)rootView.findViewById(R.id.countrycode);
                ArrayAdapter<String> ar=new ArrayAdapter<String>(getActivity(),R.layout.countrycodeview,a);
                auto.setAdapter(ar);
            }
            return rootView;
        }
}
    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return PlaceholderFragment.newInstance(position + 1);
        }

        @Override
        public int getCount() {
            return 2;
        }
    }
}
