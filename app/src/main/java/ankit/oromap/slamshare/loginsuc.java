package ankit.oromap.slamshare;

import android.app.AlarmManager;
import android.app.AlertDialog;
import android.app.PendingIntent;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.provider.Settings;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TableLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.google.android.material.navigation.NavigationView;
import com.google.android.material.snackbar.Snackbar;

import org.w3c.dom.Text;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Calendar;

public class loginsuc extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    static ProgressDialog pd;
    static TextView pro;
    private int v = 1;
    static private String em = "";
    static Intent io;
    static private String pass;
    static private String name;
    static private String number;
    private BufferedReader br1;
    boolean mobiledata=false;
    boolean wifi=false;
    Snackbar ad12;
private BufferedWriter bw2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loginsuc);
        ad12=Snackbar.make(findViewById(R.id.mainsuccess),"No Internet Connection",Snackbar.LENGTH_LONG);
        try {
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
            File name = new File(getFilesDir(), "name.txt");
            FileWriter fw2 = new FileWriter(name, false);
            bw2 = new BufferedWriter(fw2);
            bw2.write("");
            bw2.close();

        } catch (Exception e) {
        }
       final Context c = getApplicationContext();
        pd = new ProgressDialog(this,R.style.AppCompactAlertDialogStyle);
        pd.setCancelable(false);
        pd.setMessage("Loading....");
        ConnectivityManager cm=(ConnectivityManager)getSystemService(CONNECTIVITY_SERVICE);
        NetworkInfo[] netinfo=cm.getAllNetworkInfo();
        for(NetworkInfo ni:netinfo)
        {
            if(ni.getTypeName().equalsIgnoreCase("WIFI"))
            {
                if(ni.isConnected()&&ni.isAvailable())
                {
                    wifi=true;
                }
            }
            if(ni.getTypeName().equalsIgnoreCase("MOBILE"))
            {
                if(ni.isConnected()&&ni.isAvailable())
                {
                    mobiledata=true;
                }
            }
        }
        boolean b= Settings.Secure.getInt(getContentResolver(),"mobile_data",1)==1;
        if(mobiledata==false&&wifi==false)
        {
            Toast.makeText(this,"Could Not Load",Toast.LENGTH_LONG).show();
            ad12.show();
        }
        Intent  p=new Intent(c,extraservice.class);
        PendingIntent pi=PendingIntent.getService(c,0,p,PendingIntent.FLAG_CANCEL_CURRENT);
        AlarmManager am=(AlarmManager)getSystemService(ALARM_SERVICE);
        Calendar can=Calendar.getInstance();
        am.setRepeating(AlarmManager.RTC_WAKEUP,can.getTimeInMillis(),60000,pi);
        startService(new Intent(this,extraservice.class));
        pro = (TextView) findViewById(R.id.name);
        TextView y = (TextView) findViewById(R.id.storedemail);
        getnamenew name = new getnamenew();
        name.ini(pd, pro, c,ad12);
        name.execute(em, pass);
        getWindow().setStatusBarColor(getResources().getColor(R.color.colorPrimaryDark));
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setBackgroundColor(getResources().getColor(R.color.colorPrimaryDark));
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close)
        {
            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                try {
                    File f5 = new File(getFilesDir(), "email.txt");
                    File f1 = new File(getFilesDir(), "name.txt");
                    FileReader fr1 = new FileReader(f1);
                    BufferedReader br1 = new BufferedReader(fr1);
                    FileReader fr5 = new FileReader(f5);
                    BufferedReader br5 = new BufferedReader(fr5);
                    TextView tv1=(TextView)findViewById(R.id.storednamedrawer);
                    tv1.setText(br1.readLine().trim());
                    TextView tv2=(TextView)findViewById(R.id.storedemaildrawer);
                    tv2.setText(br5.readLine().trim());
                    br1.close();
                    br5.close();
                }
                catch (Exception exc) {}
            }
        };
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        try
        {
            File f1 = new File(getFilesDir(), "pass.txt");
            FileReader fr1 = new FileReader(f1);
            br1 = new BufferedReader(fr1);
            pass = br1.readLine().trim();
            br1.close();
        }catch(Exception g){}
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
      final SwipeRefreshLayout swipe=(SwipeRefreshLayout)findViewById(R.id.swiperefresh);
        swipe.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                pd.setMessage("Refreshing.....");
                getnamenew name = new getnamenew();
                name.ini(pd, pro, getApplicationContext(),ad12);
                name.execute(em, pass);
                swipe.setRefreshing(false);

            }
        });
        }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
            if (v == 0) {

            } else {
                Toast t = Toast.makeText(this, "Press Back Again To Exit", Toast.LENGTH_LONG);
                t.show();
                v = 0;
            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.loginsuc, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.messages) {
            Intent i = new Intent(this, Messages.class);
            startActivity(i);
        }

        if (id == R.id.logout1) {
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
                    i.putExtra("valueout","no");
                    startActivity(i);
                } else {

                }
                br.close();
            } catch (Exception e) {
            }
        }
        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        int ii = 0;
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        if (id == R.id.accountbutton) {
           Intent o=new Intent(this,account.class);
           o.putExtra("pass",pass);
           startActivity(o);

        }
        if (id == R.id.logout) {
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
                    i.putExtra("valueout","no");
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
    protected void onResume() {
        super.onResume();
        try {
            File frf=new File(getFilesDir(),"pass.txt");
            FileReader frr=new FileReader(frf);
            BufferedReader brr=new BufferedReader(frr);
            pass=brr.readLine();
            brr.close();
            File f = new File(getFilesDir(), "email.txt");
            FileReader fr = new FileReader(f);
            BufferedReader br = new BufferedReader(fr);
            if (br.readLine().trim().equalsIgnoreCase("ankit")) {
                finish();
            }
            br.close();
        } catch (Exception e) {
        }
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        try {
            File frf=new File(getFilesDir(),"pass.txt");
            FileReader frr=new FileReader(frf);
            BufferedReader brr=new BufferedReader(frr);
            pass=brr.readLine();
            brr.close();
            File f = new File(getFilesDir(), "email.txt");
            FileReader fr = new FileReader(f);
            BufferedReader br = new BufferedReader(fr);
            if (br.readLine().trim().equalsIgnoreCase("ankit")) {
                finish();
            }
            br.close();
        } catch (Exception e) {
        }
    }

    public void create(View view) throws IOException{
       final int id=view.getId();
        if(id==R.id.b1)
        {
            io=new Intent(getApplicationContext(),createslam.class);
        }
        else
        {
            io=new Intent(this,viewmyslambooks.class);
        }
        startActivity(io);
    }


}
