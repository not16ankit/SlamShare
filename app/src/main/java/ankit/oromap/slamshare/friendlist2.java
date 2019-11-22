package ankit.oromap.slamshare;

import android.app.Activity;
import android.app.ProgressDialog;
import android.app.SearchManager;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CheckedTextView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

public class friendlist2 extends AppCompatActivity implements iniarrays {
    private int friendcount;
    private String[] selectedname;
    ListView ls;
    private ProgressDialog pd1;
    CheckedTextView ct;
    LayoutInflater li;
    static String op = "";
    private String[] emails;
    String em = "";
    static String[] usernames;
    static String[] names;
    private String name;
    private String[] extras;
    String pass = "";
    private int tip = 0;
    private MyAdapter mya;
    private ProgressDialog pd;
    private int extranumber=0;
    private ArrayAdapter<String> adapter;
    private String[] selected;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_friendlist2);
        pd1 = new ProgressDialog(this, R.style.AppCompactAlertDialogStyle);
        pd1.setMessage("Proccessing....");
        extranumber=Integer.parseInt(getIntent().getExtras().getString("extra"));
        name=getIntent().getExtras().getString("name");
        extras=getIntent().getExtras().getStringArray("extras");
        li = getLayoutInflater();
        Toast.makeText(this,String.valueOf(extranumber)+' '+name,Toast.LENGTH_LONG).show();
        Toolbar tool = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(tool);
        getWindow().setStatusBarColor(getResources().getColor(R.color.colorPrimary));
        selected = new String[10000];
        selectedname = new String[10000];
        friendcount = 0;
        ls = (ListView) findViewById(R.id.list);
        ls.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                CheckedTextView ctv = (CheckedTextView)view.findViewById(R.id.usernametext);
                if (ctv.isChecked()) {
                    ctv.setChecked(false);
                } else {
                    ctv.setChecked(true);
                }
                int yu=0;
                String username=ctv.getText().toString().trim();
                for(int y=0;y<usernames.length;y++)
                {
                    if(usernames[y].equals(username))
                    {
                        yu=y;
                    }
                }
                int p =yu;
                String test = emails[p];
                for (int u = 0; u <= friendcount; u++) {
                    if (test.equals(selected[u])) {
                        for (int y = u; y <= friendcount; y++) {
                            selected[y] = selected[y + 1];
                        }
                        friendcount=friendcount-1;
                        tip++;
                    }
                }
                if (tip == 0) {
                    selected[friendcount] = emails[p];
                    friendcount=friendcount+1;
                }
                tip=0;
                Toast.makeText(getApplicationContext(),selected[0]+' '+selected[1]+' '+friendcount,Toast.LENGTH_LONG).show();
            }
        });
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
        } catch (Exception ex) {
        }
        pd = new ProgressDialog(this, R.style.AppCompactAlertDialogStyle);
        pd.setMessage("Fetching Friend List....");
        getusers u = new getusers();
        u.ini = this;
        u.ini(pd, getApplicationContext(), ls);
        u.execute(em, pass);
        final SwipeRefreshLayout swipe = (SwipeRefreshLayout) findViewById(R.id.swipe2);
        swipe.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                pd.setMessage("Refreshing......");
                getusers u = new getusers();
                u.ini(pd, getApplicationContext(), ls);
                u.execute(em, pass);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_container2, menu);
        MenuItem mi = menu.findItem(R.id.action);
        SearchView sv = (SearchView) mi.getActionView();
        sv.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                adapter.getFilter().filter(s);
                return false;
            }
        });
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getTitle().toString().equalsIgnoreCase("done"))
        {
            sendslambook slam=new sendslambook();
            slam.ini(friendcount,extranumber,name,extras,selected,this,pd1);
            slam.execute(em,pass);
        }
        return super.onOptionsItemSelected(item);
    }



    @Override
    public void iniarrays(String[] usernames, String[] names, String[] emails) {
        this.usernames = usernames;
        this.names = names;
        this.emails = emails;
        mya = new MyAdapter(this, usernames, names);
        adapter = mya;
        ls.setAdapter(mya);
    }
}
    class MyAdapter extends ArrayAdapter {
        String usernamearray[];
        String namesarray[];
        public MyAdapter(@NonNull Context context, String[] usernames, String[] names) {
            super(context, R.layout.listview, R.id.usernametext, usernames);
            this.usernamearray = usernames;
            this.namesarray = names;
        }

        @NonNull
        @Override
        public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
            LayoutInflater li = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View r = li.inflate(R.layout.listview, parent, false);
            ImageView im = (ImageView) r.findViewById(R.id.imglist);
            final CheckedTextView usern = (CheckedTextView) r.findViewById(R.id.usernametext);
            im.setImageResource(R.drawable.icon);
            TextView namen = (TextView) r.findViewById(R.id.nametext);
            usern.setText(usernamearray[position]);
            namen.setText(namesarray[position]);
            return r;
        }
    }
