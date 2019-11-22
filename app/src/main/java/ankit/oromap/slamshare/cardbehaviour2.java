package ankit.oromap.slamshare;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Handler;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.MotionEvent;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.mindorks.placeholderview.SwipeDecor;
import com.mindorks.placeholderview.SwipeDirection;
import com.mindorks.placeholderview.SwipePlaceHolderView;
import com.mindorks.placeholderview.annotations.Layout;
import com.mindorks.placeholderview.annotations.Resolve;
import com.mindorks.placeholderview.annotations.View;
import com.mindorks.placeholderview.annotations.swipe.SwipeIn;
import com.mindorks.placeholderview.annotations.swipe.SwipeInDirectional;
import com.mindorks.placeholderview.annotations.swipe.SwipeInState;
import com.mindorks.placeholderview.annotations.swipe.SwipeOut;
import com.mindorks.placeholderview.annotations.swipe.SwipeOutDirectional;

/**
 * Created by ankit on 30/12/17.
 */
@Layout(R.layout.cards)
public class cardbehaviour2 {
    @View(R.id.ques)
    private TextView ques;
    @View(R.id.ans)
    private EditText et;
    private int position=0;
    Context l;
   String answer;
    String question;
    private String[] answers;
    public  implementation imp;
    private TextView tv;
    private RelativeLayout bu;
    private SwipePlaceHolderView sp;

    public cardbehaviour2(Context con, String question,String answer,int p,String[] answers,TextView tv,RelativeLayout rl,SwipePlaceHolderView so) {
        this.sp=so;
        this.bu=rl;
        this.tv=tv;
        this.l = con;
        this.position=p;
        this.question = question;
        this.answer=answer;
        this.answers=answers;
    }

    @Resolve
    private void onResolved() {
        ques.setText(question);
        et.setText(answer);
        et.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                answers[position]=String.valueOf(charSequence);
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }
    @SwipeOut
    private void onSwipeIn()
    {
        final fillanswers fill=new fillanswers();

        if(et.getText().toString().isEmpty())
        {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try
                    {
                        Thread.sleep(2000);
                    }catch (Exception d){}
                    AlertDialog.Builder ad=new AlertDialog.Builder(l,R.style.Slert);
                    ad.setMessage("Skip this question");
                    ad.setPositiveButton("YES", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            dialogInterface.dismiss();
                            dialogInterface.cancel();
                            String nam=tv.getText().toString().trim();
                            int i23=Integer.parseInt(nam)-1;
                            tv.setText(String.valueOf(i23));
                            if(i23==0)
                            {
                                bu.setVisibility(android.view.View.VISIBLE);
                                Button b1 = (Button) bu.findViewById(R.id.buttonrecheck);
                                Button b2 = (Button) bu.findViewById(R.id.buttonsubmit);
                                b1.setOnClickListener(new android.view.View.OnClickListener() {
                                    @Override
                                    public void onClick(android.view.View view) {
                                        bu.setVisibility(android.view.View.INVISIBLE);
                                        fill.recheck(answers,bu);
                                    }
                                });
                                b2.setOnClickListener(new android.view.View.OnClickListener() {
                                    @Override
                                    public void onClick(android.view.View view) {
                                        imp.imp(answers);
                                    }
                                });
                            }
                        }
                    });
                    ad.setNegativeButton("NO", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            sp.undoLastSwipe();
                            Toast.makeText(l,"Question Reloaded in the list",Toast.LENGTH_LONG).show();
                        }
                    });
                    ad.show();
                }
            });
        }
        else {
            String nam=tv.getText().toString().trim();
            int i23=Integer.parseInt(nam)-1;
            tv.setText(String.valueOf(i23));
            if(i23==0)
            {
                bu.setVisibility(android.view.View.VISIBLE);
                Button b1 = (Button) bu.findViewById(R.id.buttonrecheck);
                Button b2 = (Button) bu.findViewById(R.id.buttonsubmit);
                b1.setOnClickListener(new android.view.View.OnClickListener() {
                    @Override
                    public void onClick(android.view.View view) {
                        bu.setVisibility(android.view.View.INVISIBLE);
                        fill.recheck(answers,bu);
                    }
                });
                b2.setOnClickListener(new android.view.View.OnClickListener() {
                    @Override
                    public void onClick(android.view.View view) {
                        imp.imp(answers);
                    }
                });
            }
        }
    }
    @SwipeOut
    private void OnSwipeOut()
    {
        final fillanswers fill=new fillanswers();
        if(et.getText().toString().isEmpty())
        {
                    AlertDialog.Builder ad=new AlertDialog.Builder(l,R.style.AppCompactAlertDialogStyle);
                    ad.setMessage("Are you sure about leaving this answer column empty ?");
                    ad.setPositiveButton("YES", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            dialogInterface.dismiss();
                            dialogInterface.cancel();
                            String nam=tv.getText().toString().trim();
                            int i23=Integer.parseInt(nam)-1;
                            tv.setText(String.valueOf(i23));
                            if(i23==0)
                            {
                                bu.setVisibility(android.view.View.VISIBLE);
                                Button b1 = (Button) bu.findViewById(R.id.buttonrecheck);
                                Button b2 = (Button) bu.findViewById(R.id.buttonsubmit);
                                b1.setOnClickListener(new android.view.View.OnClickListener() {
                                    @Override
                                    public void onClick(android.view.View view) {
                                        bu.setVisibility(android.view.View.INVISIBLE);
                                        fill.recheck(answers,bu);
                                    }
                                });
                                b2.setOnClickListener(new android.view.View.OnClickListener() {
                                    @Override
                                    public void onClick(android.view.View view) {
                                        imp.imp(answers);
                                    }
                                });
                            }
                        }
                    });
                    ad.setNegativeButton("NO", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            sp.undoLastSwipe();
                            Toast.makeText(l,"Question Reloaded in the list",Toast.LENGTH_LONG).show();
                        }
                    });
                    ad.show();
        }
        else {
            String nam=tv.getText().toString().trim();
            int i23=Integer.parseInt(nam)-1;
            tv.setText(String.valueOf(i23));
            if(i23==0)
            {
                bu.setVisibility(android.view.View.VISIBLE);
                Button b1 = (Button) bu.findViewById(R.id.buttonrecheck);
                Button b2 = (Button) bu.findViewById(R.id.buttonsubmit);
                b1.setOnClickListener(new android.view.View.OnClickListener() {
                    @Override
                    public void onClick(android.view.View view) {
                        bu.setVisibility(android.view.View.INVISIBLE);
                        fill.recheck(answers,bu);
                    }
                });
                b2.setOnClickListener(new android.view.View.OnClickListener() {
                    @Override
                    public void onClick(android.view.View view) {
                        imp.imp(answers);
                    }
                });
            }
        }
    }
}
