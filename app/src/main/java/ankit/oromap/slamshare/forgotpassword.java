package ankit.oromap.slamshare;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.MutableContextWrapper;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.MultiAutoCompleteTextView;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.FirebaseException;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.concurrent.TimeUnit;

public class forgotpassword extends Activity {
private  Intent i;
private String ex;
private String emailforreset;
private EditText text;
private AutoCompleteTextView spin;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgotpassword);
        getWindow().setStatusBarColor(getResources().getColor(R.color.white));
       i=new Intent(getApplicationContext(),waiting.class);
        text=(EditText)findViewById(R.id.editnumber);
        spin=(AutoCompleteTextView) findViewById(R.id.spin);
        String[] arr=getResources().getStringArray(R.array.country_code);
        ArrayAdapter<String> array=new ArrayAdapter<String>(this,R.layout.countrycodeview,arr);
        spin.setAdapter(array);
    }
    public void send(View view)
    {
        final ProgressDialog pd45=new ProgressDialog(this,R.style.Slert);
        pd45.setMessage("Connecting......");
        pd45.setCancelable(false);
        final AlertDialog.Builder ad56=new AlertDialog.Builder(this,R.style.Slert);
        ad56.setCancelable(false);
       ex=spin.getText().toString().trim();
        emailforreset=text.getText().toString().trim();
        if(emailforreset.isEmpty()||emailforreset.equalsIgnoreCase(""))
        {
            text.setError("Required Field");
        }
        else {
            if (ex.isEmpty()||ex.equalsIgnoreCase("")) {
                spin.setError("Rquired Field");
            } else {
                Intent i=new Intent(this,waiting.class);
                i.putExtra("ex",ex);
                i.putExtra("em",emailforreset);
                getphone phon = new getphone();
                phon.ini(ad56, pd45,this,i);
                phon.execute(emailforreset, ex);
            }
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        finish();
    }
}
