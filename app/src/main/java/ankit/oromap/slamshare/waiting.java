package ankit.oromap.slamshare;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.CountDownTimer;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;

import org.w3c.dom.Text;

import java.util.concurrent.TimeUnit;


public class waiting extends Activity {
private String number;
private String em;
private String ex;
private boolean done=false;
private String h="";
private ProgressDialog pd;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_waiting);
        getWindow().setStatusBarColor(getResources().getColor(R.color.white));
        ex=getIntent().getStringExtra("ex");
        em=getIntent().getStringExtra("em");
        number=ex+em;
        TextView tv=(TextView)findViewById(R.id.titi);
        tv.setText("Verify "+number);
        final Button b=(Button)findViewById(R.id.but);
       final TextView count=(TextView)findViewById(R.id.progress34);
        new CountDownTimer(60000,1000)
        {
            @Override
            public void onTick(long l) {
             count.setText("00:"+String.valueOf(l/1000));
            }

            @Override
            public void onFinish() {
                done=true;
                count.setText("Auto-Detection Timed Out !");
                b.setBackgroundColor(getResources().getColor(R.color.darkg));
            }
        }.start();
      pd=new ProgressDialog(this,R.style.Slert);
       pd.setMessage("Please wait......");
       pd.setCancelable(false);
       final AlertDialog.Builder ad=new AlertDialog.Builder(this,R.style.Slert);
      final Context t=this;
    final    LayoutInflater li=getLayoutInflater();
        PhoneAuthProvider.getInstance().verifyPhoneNumber(number, 60, TimeUnit.SECONDS, this, new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
            @Override
            public void onVerificationCompleted(PhoneAuthCredential phoneAuthCredential) {
                RelativeLayout rl=(RelativeLayout)findViewById(R.id.rltick);
                rl.setVisibility(View.INVISIBLE);
              final  ImageView im=(ImageView)findViewById(R.id.imtick);
                Animation a=AnimationUtils.loadAnimation(t, R.anim.tick);
                a.setAnimationListener(new Animation.AnimationListener() {
                    @Override
                    public void onAnimationStart(Animation animation) {
                        im.setVisibility(View.VISIBLE);
                    }

                    @Override
                    public void onAnimationEnd(Animation animation) {

                    }

                    @Override
                    public void onAnimationRepeat(Animation animation) {

                    }
                });
                im.startAnimation(a);
                TextInputEditText tied=(TextInputEditText)findViewById(R.id.edity);
                tied.setText(phoneAuthCredential.getSmsCode());
                setcode set=new setcode();
                set.ini(t,ad,pd,li);
                set.execute(number,phoneAuthCredential.getSmsCode());
            }

            @Override
            public void onVerificationFailed(FirebaseException e) {

            }

            @Override
            public void onCodeSent(String s, PhoneAuthProvider.ForceResendingToken forceResendingToken) {
                super.onCodeSent(s, forceResendingToken);
            }

            @Override
            public void onCodeAutoRetrievalTimeOut(String s) {
                super.onCodeAutoRetrievalTimeOut(s);
            }
        });
    }
    public void reverse(View view)
    {
        Intent i=new Intent(this,forgotpassword.class);
        startActivity(i);
    }
public void resend1(View view)
{
    if(done)
    {
        PhoneAuthProvider.getInstance().verifyPhoneNumber(number, 60, TimeUnit.SECONDS, this, new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
            @Override
            public void onVerificationCompleted(PhoneAuthCredential phoneAuthCredential) {
                Toast.makeText(getApplicationContext(),phoneAuthCredential.getSmsCode(),Toast.LENGTH_LONG).show();
                AlertDialog.Builder ad=new AlertDialog.Builder(getApplicationContext(),R.style.Slert);
                setcode set=new setcode();
                set.ini(getApplicationContext(),ad,pd,getLayoutInflater());
                set.execute(em,phoneAuthCredential.getSmsCode());
            }

            @Override
            public void onVerificationFailed(FirebaseException e) {

            }

            @Override
            public void onCodeSent(String s, PhoneAuthProvider.ForceResendingToken forceResendingToken) {
                super.onCodeSent(s, forceResendingToken);
            }

            @Override
            public void onCodeAutoRetrievalTimeOut(String s) {
                super.onCodeAutoRetrievalTimeOut(s);
            }
        });
    }
    else
    {
        Toast.makeText(this,"Please Wait while the auto-detection ends",Toast.LENGTH_LONG).show();
    }
}
    @Override
    protected void onStop() {
        super.onStop();
        finish();
    }
}
