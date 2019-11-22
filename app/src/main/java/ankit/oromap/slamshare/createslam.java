package ankit.oromap.slamshare;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.provider.SyncStateContract;
import android.os.Bundle;
import android.text.Editable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.ArrayAdapter;
import android.widget.CheckedTextView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TableLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.android.material.textfield.TextInputEditText;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;

public class createslam extends Activity {
    BottomSheetBehavior bs;
    String e;
    private TextView tv;
    private ListView ls;
    String slambookname="";
    String extranames[];
    int ep=0;
    String name;
    String foruse[];
    ArrayList<String> k;
    private int i=0;
    private ListView lv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_createslam);
        extranames=new String[10];
        name="";
        String foruse[]={"My Name","My Friends Call Me","My Best Friend","My Idol","My Phone Number","My Email","My Birthday is on","I studied at","My hobbies","My Aim in life","My Favorite Food","Places I want to visit","My Favorite Singer","My Favorite Color","My Favorite Sport","I pass my time by","Few Words about our friendship"};
        k=new ArrayList<String>();
        for(int i=0;i<17;i++)
        {
            k.add(foruse[i]);
        }
        getWindow().setStatusBarColor(getResources().getColor(R.color.black));
        bs=BottomSheetBehavior.from(findViewById(R.id.sheetlayout));
        lv=(ListView)findViewById(R.id.listviewid24);
        MyAdapterforuser user=new MyAdapterforuser(this,k);
        lv.setAdapter(user);
       final EditText et=(EditText)findViewById(R.id.editnameslambook);
      e=et.getText().toString().trim();
        bs.setBottomSheetCallback(new BottomSheetBehavior.BottomSheetCallback() {
            @Override
            public void onStateChanged(@NonNull View bottomSheet, int newState) {
                switch (newState)
                {
                    case BottomSheetBehavior.STATE_EXPANDED:
                        e=et.getText().toString().trim();
                        if(e.isEmpty()||e.equals("")) {
                            bs.setState(BottomSheetBehavior.STATE_COLLAPSED);
                            et.setError("Required Field");
                        }
                        else
                        {
                            slambookname=et.getText().toString().trim();
                            InputMethodManager im=(InputMethodManager)getSystemService(INPUT_METHOD_SERVICE);
                            im.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(),0);
                        }
                }
            }

            @Override
            public void onSlide(@NonNull View bottomSheet, float slideOffset) {
                e=et.getText().toString().trim();
                if(e.isEmpty()||e.equals("")) {
                    bs.setState(BottomSheetBehavior.STATE_COLLAPSED);
                    et.setError("Required Field");
                }
                else
                {
                    if(bs.getState()==BottomSheetBehavior.STATE_EXPANDED)
                    {
                        bs.setPeekHeight(BottomSheetBehavior.PEEK_HEIGHT_AUTO);
                    }
                }
            }
        });
    }

    public void createslam1(View view) {
        EditText et=(EditText)findViewById(R.id.editnameslambook);
        e=et.getText().toString().trim();
        if(!e.isEmpty()) {
            bs.setState(BottomSheetBehavior.STATE_EXPANDED);
        }
        else
        {
            et.setError("Required Field");
        }
    }
    public void friendlist(View view)
    {
        Toast.makeText(this,String.valueOf(ep),Toast.LENGTH_LONG).show();
        EditText et=(EditText)findViewById(R.id.editnameslambook);
        name=et.getText().toString();
        Intent i=new Intent(this,friendlist2.class);
        i.putExtra("extra",String.valueOf(ep));
        i.putExtra("extras",extranames);
        i.putExtra("name",name);
        startActivity(i);
    }
    public void wrong(View view)
    {
        AlertDialog.Builder ad=new AlertDialog.Builder(this,R.style.Slert);
        ad.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
                dialogInterface.cancel();
            }
        });
        ad.setIcon(R.drawable.icon);
        ad.setMessage("These Columns are to be filled by your friends. You may only add and customize them.");
        ad.show();
    }
    public void addlabel(View view)
    {
        AlertDialog.Builder ad=new AlertDialog.Builder(this,R.style.Slert);
        final View v=getLayoutInflater().inflate(R.layout.addlabelentryview,null);
        ad.setView(v);
        ad.setCancelable(false);
        ad.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
             dialogInterface.cancel();
             dialogInterface.dismiss();
            }
        });
        ad.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                TextInputEditText et=(TextInputEditText) v.findViewById(R.id.editextra);
                extranames[ep]=et.getText().toString().trim();
                ep++;
                k.add(et.getText().toString().trim());
                MyAdapterforuser user=new MyAdapterforuser(getApplicationContext(),k);
                lv.setAdapter(user);
            }
        });
        ad.setTitle("Add Columns");
        ad.show();
    }
}
class MyAdapterforuser extends ArrayAdapter {
    ArrayList<String> questions;

    public MyAdapterforuser(@NonNull Context context, ArrayList<String> questions) {
        super(context, R.layout.listview24, R.id.textview24, questions);
        this.questions=questions;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater li = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View r = li.inflate(R.layout.listview24, parent, false);
        TextView tv=(TextView)r.findViewById(R.id.textview24);
        tv.setText(questions.get(position));
        return r;
    }
}

