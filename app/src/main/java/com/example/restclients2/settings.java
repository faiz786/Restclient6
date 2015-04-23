package com.example.restclients2;

import android.app.Activity;
import android.content.SharedPreferences;
import android.graphics.Point;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by faizan on 2/16/2015.
 */
public class settings extends Activity {

    TextView tv1,tv2,tv3,tv4,tv5,tv6;
    Button b1,b2;
    CheckBox cb1;
    String FONT_SIZE_KEY ;
    SharedPreferences prefs,pref1,pref2;
    String prefName = "MyPref";
    String prefName1 = "MyPref1";
    String prefName2 = "MyPref2";
    float i=0;
    float check1;
    float fontsize;
    int check11;
    boolean tab;
    int swidth ;
    int sheight;
    int density;
    Display d ;
    Point p = new Point();
    public static DisplayMetrics metrics;
    public static boolean isTablet(Activity act)
    {
        Display display = act.getWindow().getWindowManager().getDefaultDisplay();
        DisplayMetrics displayMetrics = new DisplayMetrics();
        display.getMetrics(displayMetrics);

        float width = displayMetrics.widthPixels / displayMetrics.xdpi;
        float height = displayMetrics.heightPixels / displayMetrics.ydpi;

        double screenDiagonal = Math.sqrt( width * width + height * height );

        float inch = (float) (screenDiagonal);
        Toast.makeText(act, "inch : "+ inch, Toast.LENGTH_LONG).show();
        return (inch >= 6.2 );
    }


  protected void onSaveInstanceState(Bundle b)
  {
      pref1 = getSharedPreferences(prefName1, 0);
      SharedPreferences.Editor editor1 = pref1.edit();

      editor1.putBoolean("check", cb1.isChecked());
      editor1.commit();



  }

    @Override
    protected void onRestoreInstanceState(Bundle b) {
        super.onRestoreInstanceState(b);

        pref1 = getSharedPreferences(prefName1, 0);
        Boolean check= pref1.getBoolean("check",false);
        if(check)
        {
            cb1.setChecked(true);
        }
        else
        {
            cb1.setChecked(false);
        }

    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        metrics = this.getResources().getDisplayMetrics();
        final int screen_width = metrics.widthPixels;
        final int screen_height=  metrics.heightPixels;
        density=metrics.densityDpi;
       /* if(screen_width == 2560 && screen_height == 1600) {
            setContentView(R.layout.settings);
        }
        else
        {
            setContentView(R.layout.settings1);
        }*/
        tab=isTablet(settings.this);
        if(tab)
        {
            setContentView(R.layout.settings);
            d=getWindowManager().getDefaultDisplay();
            d.getSize(p);
            swidth = p.x;
            sheight = p.y;
            Toast.makeText(settings.this, "its a tablet", Toast.LENGTH_LONG).show();
        }
        else
        {
            setContentView(R.layout.settings1);
            d=getWindowManager().getDefaultDisplay();
            d.getSize(p);
            swidth = p.x;
            sheight = p.y;
            Toast.makeText(settings.this, "its a phone", Toast.LENGTH_LONG).show();
        }
        tv1=(TextView)findViewById(R.id.tv1);
        tv2=(TextView)findViewById(R.id.tv2);
        tv3=(TextView)findViewById(R.id.tv3);
        tv4=(TextView)findViewById(R.id.tv4);
        tv5=(TextView)findViewById(R.id.tv5);
        tv6=(TextView)findViewById(R.id.tv6);
        b1=(Button)findViewById(R.id.b1);
        b2=(Button)findViewById(R.id.b2);
        cb1=(CheckBox)findViewById(R.id.cb1);


        pref1 = getSharedPreferences(prefName1, 0);
        Boolean check= pref1.getBoolean("check",false);



        if(check)
        {
            cb1.setChecked(true);
            prefs = getSharedPreferences(prefName, MODE_PRIVATE);
            SharedPreferences.Editor editor = prefs.edit();
            editor.putBoolean("myval", true);
            editor.commit();
        }
        else
        {
            cb1.setChecked(false);
            prefs = getSharedPreferences(prefName, MODE_PRIVATE);
            SharedPreferences.Editor editor = prefs.edit();
             editor.putBoolean("myval", false);
            editor.commit();
        }



        cb1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if (((CheckBox) v).isChecked())
                {

                        prefs = getSharedPreferences(prefName, MODE_PRIVATE);
                        SharedPreferences.Editor editor = prefs.edit();
                        editor.clear();
                        editor.putBoolean("myval", true);
                        editor.commit();

                    pref1 = getSharedPreferences(prefName1, 0);
                    SharedPreferences.Editor editor1 = pref1.edit();

                    editor1.putBoolean("check", cb1.isChecked());
                    editor1.commit();



                }


                else
                {


                    prefs = getSharedPreferences(prefName, MODE_PRIVATE);
                    SharedPreferences.Editor editor = prefs.edit();
                    editor.clear();
                    editor.putBoolean("myval",false);

                    editor.commit();

                    pref1 = getSharedPreferences(prefName1, 0);
                    SharedPreferences.Editor editor1 = pref1.edit();

                    editor1.putBoolean("check", cb1.isChecked());
                    editor1.commit();

                }

            }
        });

        pref2=getSharedPreferences(prefName2,MODE_PRIVATE);
        check1= pref2.getFloat(FONT_SIZE_KEY,12);


        fontsize=check1;
        check11=(int)check1;
        tv6.setText(""+check11);
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (check1<28) {
                    if (fontsize < 28) {
                        i++;
                    }
                }
                else
                {
                    if (fontsize < 28) {
                        i++;
                    }
                }
                fontsize = check1 + i;

                SharedPreferences.Editor editor=pref2.edit();
                editor.putFloat(FONT_SIZE_KEY,fontsize);

                editor.commit();
                check11=(int)fontsize;
                tv6.setText(""+check11);


            }
        });

        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (check1>12) {
                    if (fontsize > 12) {
                        i--;

                    }
                }
                else
                {
                    if (fontsize > 12) {
                        i--;
                    }
                }
               /* if (check1>13) {
                    if (fontsize >13 ) {
                        i++;
                    }
                }*/
                fontsize = check1 + i;

                SharedPreferences.Editor editor=pref2.edit();
                editor.putFloat(FONT_SIZE_KEY, fontsize);
                editor.commit();
                check11=(int)fontsize;
                tv6.setText(""+check11);

            }
        });





    }


}
