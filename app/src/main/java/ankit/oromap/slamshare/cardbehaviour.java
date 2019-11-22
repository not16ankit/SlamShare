package ankit.oromap.slamshare;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Handler;
import android.os.Message;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.MotionEvent;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
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
public class cardbehaviour {
    @View(R.id.ques)
    private TextView ques;
    @View(R.id.ans)
    private EditText et;
    private SwipePlaceHolderView sp;
    Context l;
    int answernumber;
    String question;
    public implementation imp;
    private String[] answers;
    private TextView tv;
    private fillanswers fill;
    private int in;
    private RelativeLayout bu;

    public cardbehaviour(Context con, String question, int questionnumber, SwipePlaceHolderView sp, String array[], TextView tc, RelativeLayout b) {
        this.bu = b;
        this.tv = tc;
        this.answers = array;
        this.l = con;
        this.question = question;
        this.sp = sp;
        this.answernumber = questionnumber;
        this.fill=new fillanswers();
    }

    @Resolve
    private void onResolved() {
        ques.setText(question);
    }

    @SwipeOut
    private void onSwipedOut() {
        if(et.getText().toString().isEmpty())
        {
               final  AlertDialog.Builder ad=new AlertDialog.Builder(l,R.style.Slert);
               ad.setTitle(question);
            ad.setMessage("Are you sure about leaving this answer column empty ?");
                    ad.setPositiveButton("YES", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            dialogInterface.dismiss();
                            dialogInterface.cancel();
                            answers[answernumber - 1] = "I have nothing to write in this column";
                            String pre = tv.getText().toString().trim();
                            int in = Integer.valueOf(pre) - 1;
                            String s = String.valueOf(in);
                            tv.setText(s);
                            if (in == 0) {
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
                 final Handler h=new Handler()
                    {
                        @Override
                        public void handleMessage(Message msg) {
                            super.handleMessage(msg);
                            ad.show();
                        }
                    };
                    Thread t=new Thread(new Runnable() {
                        @Override
                        public void run() {
                            try {
                                Thread.sleep(1300);
                            } catch (Exception d) {}
                            h.sendEmptyMessage(0);
                        }
                    });
                    t.start();
        }
        else
        {
            answers[answernumber - 1] = et.getText().toString().trim();
            String pre = tv.getText().toString().trim();
            int in = Integer.valueOf(pre) - 1;
            String s = String.valueOf(in);
            tv.setText(s);
            if (in == 0) {
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

    @SwipeIn
    private void onSwipeIn() {
        if(et.getText().toString().isEmpty())
        {
             final       AlertDialog.Builder ad = new AlertDialog.Builder(l, R.style.Slert);
             ad.setTitle(question);
            ad.setMessage("Are you sure about leaving this answer column empty ?");
                    ad.setPositiveButton("YES", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            dialogInterface.dismiss();
                            dialogInterface.cancel();
                            answers[answernumber - 1] = "I have nothing to write in this column";
                            String pre = tv.getText().toString().trim();
                            int in = Integer.valueOf(pre) - 1;
                            String s = String.valueOf(in);
                            tv.setText(s);
                            if (in == 0) {
                                bu.setVisibility(android.view.View.VISIBLE);
                                Button b1 = (Button) bu.findViewById(R.id.buttonrecheck);
                                Button b2 = (Button) bu.findViewById(R.id.buttonsubmit);
                                b1.setOnClickListener(new android.view.View.OnClickListener() {
                                    @Override
                                    public void onClick(android.view.View view) {
                                        bu.setVisibility(android.view.View.INVISIBLE);
                                        fill.recheck(answers, bu);
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
                            Toast.makeText(l, "Question Reloaded in the list", Toast.LENGTH_LONG).show();
                        }
                    });
            final Handler h=new Handler()
            {
                @Override
                public void handleMessage(Message msg) {
                    super.handleMessage(msg);
                    ad.show();
                }
            };
            Thread t=new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        Thread.sleep(1300);
                    } catch (Exception d) {}
                    h.sendEmptyMessage(0);
                }
            });
            t.start();
                }
        else {
            answers[answernumber - 1] = et.getText().toString().trim();
            String pre = tv.getText().toString().trim();
            int in = Integer.valueOf(pre) - 1;
            String s = String.valueOf(in);
            tv.setText(s);
            if (in == 0) {
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
