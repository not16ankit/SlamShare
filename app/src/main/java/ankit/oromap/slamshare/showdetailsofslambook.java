package ankit.oromap.slamshare;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.CheckedTextView;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.google.android.material.textfield.TextInputEditText;

import org.w3c.dom.Text;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.sql.Array;

public class showdetailsofslambook extends AppCompatActivity implements inidetailsofslambook{
private String name="";
private   ListView ls;
private String pass;
private ListView ls2;
   private Toast t;
private String em;
private ProgressDialog fa;
private AlertDialog.Builder ad23;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_showdetailsofslambook);
        getWindow().setStatusBarColor(getResources().getColor(R.color.colorPrimary));
        try
        {
            File f = new File(getFilesDir(), "email.txt");
            FileReader fr = new FileReader(f);
            BufferedReader br = new BufferedReader(fr);
            em = br.readLine().trim();
            br.close();
            File f1 = new File(getFilesDir(), "pass.txt");
            FileReader fr1 = new FileReader(f1);
            BufferedReader br1 = new BufferedReader(fr1);
            pass = br1.readLine().trim();
            br1.close();
        }catch (Exception s){}
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        name=getIntent().getExtras().getString("name");
        TextView tv=(TextView)findViewById(R.id.slamnameshow);
        tv.setText(name);
        fa=new ProgressDialog(this,R.style.Slert);
        fa.setMessage("Fetching slambook details....");
        fa.setCancelable(false);
        ls=(ListView)findViewById(R.id.senttolist);
      ls2=(ListView)findViewById(R.id.filledbylist);
      t=Toast.makeText(this,"",Toast.LENGTH_LONG);
      ad23=new AlertDialog.Builder(this);
      getdetailsofslambook details=new getdetailsofslambook();
      details.ini(fa,t);
      details.inidetailsofslambook=this;
      details.execute(em,pass,name);
    }
    @Override
    public void ini(String[] sharedtousernames, String[] sharedtonames, String[] filledbyusernames, String[] filledbynames, final String[] filledbyemails) {
        MyAdapter3 ad=new MyAdapter3(this,sharedtousernames,sharedtonames);
        ls.setAdapter(ad);
        View view=getLayoutInflater().inflate(R.layout.viewanswers,(ViewGroup)findViewById(R.id.alertlayout23));
        ad23.setView(view);
        final  ProgressBar pb=(ProgressBar)view.findViewById(R.id.progressb);
        final FrameLayout fl=(FrameLayout)view.findViewById(R.id.setforchange);
        final ListView lv=(ListView)view.findViewById(R.id.listforanswers);
        MyAdapter4 myad4=new MyAdapter4(this,filledbyusernames,filledbynames,filledbyemails);
        ls2.setAdapter(myad4);
        ls2.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                ad23.show();
                getanswers ans=new getanswers();
                ans.ini(pb,fl,Toast.makeText(getApplicationContext(),"",Toast.LENGTH_LONG));
                ans.inians=new inianswers() {
                    @Override
                    public void inianswers(String[] questions, String[] answers) {
                        MyAdapterforans listans=new MyAdapterforans(getApplicationContext(),questions,answers);
                        lv.setAdapter(listans);
                    }
                };
                ans.execute(em,pass,name,filledbyemails[adapterView.getSelectedItemPosition()+1]);
            }
        });
    }
}
class MyAdapter3 extends ArrayAdapter {
    String sentousernames[];
    String sentonames[];
    Toast t;
    public MyAdapter3(@NonNull Context context, String[] sentousernames, String[] sentonames) {
        super(context, R.layout.listview2, R.id.usernametext, sentousernames);
        this.sentousernames = sentousernames;
        this.sentonames = sentonames;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater li = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View r = li.inflate(R.layout.listview2, parent, false);
        ImageView im = (ImageView) r.findViewById(R.id.imglist);
        final TextView usern = (TextView) r.findViewById(R.id.usernametext);
        im.setImageResource(R.drawable.icon);
        TextView name=(TextView)r.findViewById(R.id.nametext);
        usern.setText(sentousernames[position]);
        name.setText(sentonames[position]);
        return r;
    }
}
class MyAdapter4 extends ArrayAdapter {
    String filledbyusernames[];
    String filledbynames[];
    String filledbyemails[];
    Toast t;
    public MyAdapter4(@NonNull Context context, String[] filledbyusernames, String[] filledbynames,String[] filledbyemails) {
        super(context, R.layout.listview2, R.id.usernametext, filledbyusernames);
        this.filledbyusernames = filledbyusernames;
        this.filledbynames = filledbynames;
        this.filledbyemails=filledbyemails;
    }

    @NonNull
    @Override
    public View getView(final int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater li = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View r = li.inflate(R.layout.listview2, parent, false);
        ImageView im = (ImageView) r.findViewById(R.id.imglist);
        final TextView usern = (TextView) r.findViewById(R.id.usernametext);
      im.setImageResource(R.drawable.icon);
        TextView name=(TextView)r.findViewById(R.id.nametext);
        usern.setText(filledbyusernames[position]);
        name.setText(filledbynames[position]);
        return r;
    }
}
class MyAdapterforans extends ArrayAdapter
{
    String questions[];
    String[] answers;
    public MyAdapterforans(@NonNull Context context, String[] questions,String[] answers) {
        super(context, R.layout.listviewforans, R.id.question, answers);
        this.answers=answers;
        this.questions=questions;
    }

    @NonNull
    @Override
    public View getView(final int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater li = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View r = li.inflate(R.layout.listviewforans, parent, false);
        TextView ques=(TextView)r.findViewById(R.id.question);
        ques.setText(questions[position]);
        TextInputEditText answerstext=(TextInputEditText) r.findViewById(R.id.answer);
        answerstext.setText("   "+answers[position]);
        return r;
    }
}