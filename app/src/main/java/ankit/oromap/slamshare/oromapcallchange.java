package ankit.oromap.slamshare;

import android.app.ProgressDialog;
import android.content.Intent;
import android.app.AlertDialog;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

public class oromapcallchange extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        File f=new File(getFilesDir(),"email.txt");
        if(f.exists())
        {
            try {
                FileReader fr = new FileReader(f);
                BufferedReader br=new BufferedReader(fr);
                if(br.readLine().trim().equalsIgnoreCase("ankit"))
                {
                    Intent i=new Intent(this,Container2.class);
                    i.putExtra("valueout","yes");
                    startActivity(i);
                }
                else
                {
                    Intent i=new Intent(this,loginsuc.class);
                    startActivity(i);
                }
                br.close();
            }
            catch(Exception e){}
        }
        else {
            Intent i=new Intent(this,Container2.class);
            startActivity(i);
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        finish();
    }
}