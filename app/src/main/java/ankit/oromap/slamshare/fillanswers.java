package ankit.oromap.slamshare;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.mindorks.placeholderview.SwipeDecor;
import com.mindorks.placeholderview.SwipePlaceHolderView;

import org.w3c.dom.Text;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
public class fillanswers extends AppCompatActivity implements inianswersforfill,implementation,inisubmit{
private String name="";
private String em="";
private String pass="";
private ProgressBar pb;
private String slambook="";
private String slambooknumber="";
private String[] questions;
private String sentby="";
private TextView tv;
private ProgressDialog pd;
private RelativeLayout b;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fillanswers);
        getWindow().setStatusBarColor(getResources().getColor(R.color.colorPrimary));
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        tv=(TextView)findViewById(R.id.remaining);
        b=(RelativeLayout)findViewById(R.id.primary);
        sentby=getIntent().getExtras().getString("sentby");
        slambook=getIntent().getExtras().getString("slambook");
        pd=new ProgressDialog(this,R.style.Slert);
        pd.setMessage("Loading......");
        slambooknumber=getIntent().getExtras().getString("slambooknumber");
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
        getextraquestionsforfill ex=new getextraquestionsforfill();
        ex.ini(this);
        ex.ini=this;
        ex.execute(em,pass,slambook,slambooknumber,sentby);
        pb=(ProgressBar)findViewById(R.id.progressb);
    }
public void io()
{
    getextraquestionsforfill ex=new getextraquestionsforfill();
    ex.ini(this);
    ex.ini=this;
    ex.execute(em,pass,slambook,slambooknumber,sentby);
}
    @Override
    public void iniquestion(String[] questions) {
        pb.setVisibility(View.INVISIBLE);
        this.questions=questions;
        View v=getLayoutInflater().inflate(R.layout.answerswipe,(ViewGroup)findViewById(R.id.ankit),true);
        SwipePlaceHolderView sp=(SwipePlaceHolderView)v.findViewById(R.id.answerswipe);
        sp.getBuilder().setDisplayViewCount(questions.length).setSwipeDecor(new SwipeDecor().setSwipeAnimTime(1000).setPaddingTop(15).setRelativeScale(0.03f)).setIsUndoEnabled(true);
        String answerarray[]=new String[questions.length];
        cardbehaviour be=null;
        tv.setText(String.valueOf(questions.length));
        tv.setVisibility(View.VISIBLE);
        for(int i=0;i<questions.length;i++)
        {
            be=new cardbehaviour(this,questions[i],i+1,sp,answerarray,tv,b);
            be.imp=this;
            sp.addView(be);
        }
    }

    @Override
    public void imp(String[] answers) {
        String url="";
        int use=0;
        for(int i=1;i<=answers.length;i++)
        {
            url=url+"&answer"+i+"="+answers[use];
            use++;
        }
        url=url+"&answers="+String.valueOf(answers.length);
        Toast.makeText(this,url,Toast.LENGTH_LONG).show();
        submit sub=new submit();
        pd.show();
        sub.ini=this;
        sub.ini(this);
        sub.execute(em,pass,url,slambook,sentby,String.valueOf(answers.length));
    }
public void recheck(String answers[],RelativeLayout bu)
{
  final  submit sub=new submit();
    pd.show();
    sub.ini=this;
    sub.ini(this);
    View v=getLayoutInflater().inflate(R.layout.answerswipe2,(ViewGroup)findViewById(R.id.ankit),true);
    SwipePlaceHolderView sp=(SwipePlaceHolderView)v.findViewById(R.id.answerswipe);
    sp.getBuilder().setDisplayViewCount(questions.length).setSwipeDecor(new SwipeDecor().setSwipeAnimTime(1000).setPaddingTop(12).setRelativeScale(0.03f)).setIsUndoEnabled(true);
    cardbehaviour2 be=null;
    be.imp=new implementation() {
        @Override
        public void imp(String[] answers) {
            String url="";
            int use=0;
            for(int i=1;i<=answers.length;i++)
            {
                url=url+"&answer"+i+"="+answers[use];
                use++;
            }
            url=url+"&answers="+String.valueOf(answers.length);
            Toast.makeText(getApplicationContext(),url,Toast.LENGTH_LONG).show();
            sub.execute(em,pass,url,slambook,sentby,String.valueOf(answers.length));
        }
    };
    tv.setText(String.valueOf(questions.length));
    tv.setVisibility(View.VISIBLE);
    for(int i=0;i<questions.length;i++)
    {
        be=new cardbehaviour2(this,questions[i],answers[i],i,answers,tv,bu,sp);
        sp.addView(be);
    }
}

    @Override
    public void ini(String result) {
        pd.cancel();
        pd.dismiss();
        AlertDialog.Builder ad=new AlertDialog.Builder(this);
        ad.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.cancel();
                dialogInterface.dismiss();
                finish();
            }
        });
        if(result.equalsIgnoreCase("success"))
        {
            ad.setMessage("Slambook has been successfully submitted.");
            ad.show();
        }
        else
        {
            ad.setMessage("Error in connection");
            ad.show();
        }
    }
}
