package ankit.oromap.slamshare;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.google.android.material.navigation.NavigationView;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;

public class Messages extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener,inimessages {
ProgressDialog pd;
private String em;
private String[] slambooknames;
private ListView ls;
private String[] sentby;
private String pass;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_messages);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setBackgroundColor(getResources().getColor(R.color.colorPrimaryDark));
       DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        pd=new ProgressDialog(this,R.style.Slert);
        ls=(ListView)findViewById(R.id.scrollcard);
        pd.setMessage("Loading Slambooks......");
        try
        {
            File f = new File(getFilesDir(), "email.txt");
            FileReader fr = new FileReader(f);
            BufferedReader br = new BufferedReader(fr);
            em = br.readLine().trim();
            File f1 = new File(getFilesDir(), "pass.txt");
            FileReader fr1 = new FileReader(f1);
            BufferedReader br1 = new BufferedReader(fr1);
            pass = br1.readLine().trim();
            br1.close();
            br.close();
        }catch (Exception e){}
        initializereceslambooks rece=new initializereceslambooks();
        rece.ini=this;
        rece.ini(pd,Toast.makeText(getApplicationContext(),"",Toast.LENGTH_LONG));
        rece.execute(em,pass);
     final SwipeRefreshLayout swipe=(SwipeRefreshLayout)findViewById(R.id.scroll3);
        swipe.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                pd.setMessage("Refreshing.....");
                initializereceslambooks rece=new initializereceslambooks();
                rece.ini(pd,Toast.makeText(getApplicationContext(),"",Toast.LENGTH_LONG));
                rece.execute(em,pass);
                swipe.setRefreshing(false);
            }
        });
    }

    @Override
    public void inislambooklist(String[] names, String[] sentbyemails,String sentbyusernames[]) {
        Toast.makeText(this,names[0],Toast.LENGTH_LONG).show();
        MyAdapterforslambooks slam=new MyAdapterforslambooks(this,names,sentbyemails,sentbyusernames);
        ls.setAdapter(slam);
    }

    public void makecards(int v, String[] slambooknames, String[] sentby)
{
    this.slambooknames=slambooknames;
    this.sentby=sentby;
    Toast.makeText(this,v+""+slambooknames.length,Toast.LENGTH_LONG).show();
}
    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.messages, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        if(id==R.id.logout1)
        {
            try {
                File f = new File(getFilesDir(), "email.txt");
                File f1 = new File(getFilesDir(), "pass.txt");
                FileWriter fw = new FileWriter(f,false);
                BufferedWriter bw=new BufferedWriter(fw);
                bw.write("ankit");
                bw.close();
                FileWriter fw1=new FileWriter(f1,false);
                BufferedWriter bw1=new BufferedWriter(fw1);
                bw1.write("ankit");
                bw1.close();
                FileReader fr=new FileReader(f);
                BufferedReader br=new BufferedReader(fr);
                if(br.readLine().trim().equalsIgnoreCase("ankit"))
                {
                    Intent i=new Intent(this,Container2.class);
                    startActivity(i);
                }
                else
                {

                }
                br.close();
            }catch (Exception e){}
        }
        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        if(id==R.id.logout) {
            try {
                File f = new File(getFilesDir(), "email.txt");
                File f1 = new File(getFilesDir(), "pass.txt");
                FileWriter fw = new FileWriter(f, false);
                BufferedWriter bw = new BufferedWriter(fw);
                bw.write("ankit");
                bw.close();
                FileWriter fw1 = new FileWriter(f1, false);
                BufferedWriter bw1 = new BufferedWriter(fw1);
                bw1.write("ankit");
                bw1.close();
                FileReader fr = new FileReader(f);
                BufferedReader br = new BufferedReader(fr);
                if (br.readLine().trim().equalsIgnoreCase("ankit")) {
                    Intent i = new Intent(this, Container2.class);
                    startActivity(i);
                } else {

                }
                br.close();
            } catch (Exception e) {
            }
        }
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    protected void onPause() {
        super.onPause();
        finish();
    }

    @Override
    protected void onStop() {
        super.onStop();
        finish();
    }
}
class MyAdapterforslambooks extends ArrayAdapter {
    String slambooknames[];
    String sentbyemails[];
    String sentbyusernames[];
    Toast t;
    public MyAdapterforslambooks(@NonNull Context context, String[] slambooknames, String[] sentbyemails, String[] sentbyusernames) {
        super(context, R.layout.listviewformessages, R.id.text, slambooknames);
        this.slambooknames=slambooknames;
        this.sentbyemails=sentbyemails;
        this.sentbyusernames=sentbyusernames;
    }

    @NonNull
    @Override
    public View getView(final int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater li = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View r = li.inflate(R.layout.listviewformessages, parent, false);
        final TextView usern = (TextView) r.findViewById(R.id.text);
        final TextView name=(TextView)r.findViewById(R.id.name);
        Button b=(Button)r.findViewById(R.id.b1);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(getContext().getApplicationContext(),fillanswers.class);
                int slambooknumber=0;
                i.putExtra("sentby",sentbyemails[position]);
                for(int o=0;o<slambooknames.length;o++)
                {
                    if(slambooknames[o].equals(slambooknames[position]))
                    {
                        slambooknumber=slambooknames.length-position;
                        break;
                    }
                }
                i.putExtra("slambooknumber",String.valueOf(slambooknumber));
                i.putExtra("slambook",slambooknames[position]);
                getContext().startActivity(i);
            }
        });
        usern.setText("New Slambook from "+sentbyusernames[position]);
        name.setText("Name of Slambook     : "+slambooknames[position]);
        return r;
    }
}
