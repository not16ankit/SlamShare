package ankit.oromap.slamshare;

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
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

public class viewmyslambooks extends AppCompatActivity implements inislamnames{
private ListView  ls;
private String em;
private String pass;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_viewmyslambooks);
        getWindow().setStatusBarColor(getResources().getColor(R.color.colorPrimary));
        try
        {
            File f=new File(getFilesDir(),"email.txt");
            FileReader fr=new FileReader(f);
            BufferedReader br=new BufferedReader(fr);
            em=br.readLine().trim();
            br.close();
            File f1=new File(getFilesDir(),"pass.txt");
            FileReader fr1=new FileReader(f1);
            BufferedReader br1=new BufferedReader(fr1);
            pass=br1.readLine();
            br1.close();
        }catch (Exception d){}
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ls=(ListView)findViewById(R.id.listview24);
        ProgressDialog pd=new ProgressDialog(this,R.style.Slert);
        pd.setMessage("Connecting.....");
        getslambooknames names=new getslambooknames();
        names.ini=this;
        names.ini(pd,this);
        names.execute(em,pass);
    }

    @Override
    public void ininames(String[] slamnames,String[] dates) {
        MyAdapter2 ad=new MyAdapter2(this,slamnames,dates);
        ls.setAdapter(ad);
    }
}
class MyAdapter2 extends ArrayAdapter {
    String slamnamearray[];
    String datesarray[];
Toast t;
    public MyAdapter2(@NonNull Context context, String[] slamnames, String[] dates) {
        super(context, R.layout.cardviewlistview, R.id.slambooknameoncardview, slamnames);
        this.slamnamearray =slamnames;
        this.datesarray=dates;
    }

    @NonNull
    @Override
    public View getView(final int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater li = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View r = li.inflate(R.layout.cardviewlistview, parent, false);
        final TextView slamname = (TextView) r.findViewById(R.id.slambooknameoncardview);
        slamname.setText("Name of Slambook : "+slamnamearray[position]);
        TextView dateofslam=(TextView)r.findViewById(R.id.date);
        dateofslam.setText("Date Created : "+datesarray[position]);
        Button b=(Button)r.findViewById(R.id.b1);
        Button del=(Button)r.findViewById(R.id.del);
        final Intent i=new Intent(getContext().getApplicationContext(),showdetailsofslambook.class);
        t=Toast.makeText(getContext().getApplicationContext(),"",Toast.LENGTH_LONG);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                i.putExtra("name",slamnamearray[position]);
               getContext().startActivity(i);
            }
        });
        del.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                t.setText("del"+slamnamearray[position]);
                t.show();
            }
        });
        return r;
    }
}
