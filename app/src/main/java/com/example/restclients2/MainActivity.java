package com.example.restclients2;


/**
 * Created by faizan on 2/16/2015.
 */

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.graphics.Point;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.preference.PreferenceManager;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.SimpleAdapter;
import android.view.ViewGroup.LayoutParams;
import android.widget.SimpleCursorAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;
import android.view.View.OnClickListener;

import org.apache.http.Header;
import org.apache.http.HeaderElement;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
//import org.lucasr.twowayview.*;
//import com.devsmart.android.ui.HorizontalListView;
import com.meetme.android.horizontallistview.HorizontalListView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;



public class MainActivity extends Activity  {
    private long id,id1;
    public final String title="Save URL";
    private SharedPreferences prefs,pref2;
    private String prefName = "MyPref";
    private String prefName2 = "MyPref2";
    public static DisplayMetrics metrics;

    String FONT_SIZE_KEY;
    final ArrayList<String> keyvalue = new ArrayList();
    final ArrayList<String> list11=new ArrayList();
    int screen_width ;
    int screen_height;
    int swidth ;
    int sheight;
    Boolean tab;

    DBAdapter db = new DBAdapter(this);

    Button b,b1,b2,b3,b4,b5,b6,b7;
    TextView tv1,tv2,tv3,tv4;
    Spinner sp1;
    ImageButton ib1;
    ListView lv1,lv2,lView;
    EditText et1;
    PopupWindow pw,pw1;
    String[] action;
    public String action2,gp="get";
    float font1;
    double font11;
    int density;
    float d1 ;
    float w111;
    float h111;
    Display d ;
    Point p = new Point();

    void initiatePopupWindow(){
        try {
            //We need to get the instance of the LayoutInflater, use the context of this activity
           LayoutInflater inflater = (LayoutInflater) MainActivity.this
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            //Inflate the view from a predefined XML layout
            View layout1 = inflater.inflate(R.layout.popup_layout,
                    (ViewGroup) findViewById(R.id.popup_element));

            final String[] from = new String[] {
                    DBAdapter.KEY_URL, DBAdapter.KEY_URLADDRESS };

            final int[] to = new int[] {R.id.urlname,R.id.url};

            lv2=(ListView)layout1.findViewById(R.id.lv2);
            db.open();

            final Cursor c= db.getAllContacts();
            SimpleCursorAdapter adapter1=new SimpleCursorAdapter(this, R.layout.my_custom_list_layout1, c, from, to, 0);




            lv2.setAdapter(adapter1);

            lv2.setOnItemClickListener(new OnItemClickListener() {

                public void onItemClick(AdapterView<?> arg0, View arg1,
                                        int arg2, long arg3) {
                    // TODO Auto-generated method stub


                    TextView url=(TextView)arg1.findViewById(R.id.url);
                    TextView url11=(TextView)arg1.findViewById(R.id.urlname);
                    String url1=url.getText().toString();
                    String url12=url11.getText().toString();
                    int id3=db.getid(url12);
                    db.open();
                    Cursor c= db.getContactkv(id3);
                    int key=c.getColumnIndex(DBAdapter.KEY_URLKEY);
                    int val=c.getColumnIndex(DBAdapter.KEY_VALUE);

                    for (c.moveToFirst(); !c.isAfterLast(); ) {

                        keyvalue.add(c.getString(key)+"\n"+c.getString(val)+"\n");

                        c.moveToNext();
                    }
                    MyCustomAdapter11 adapter313 = new MyCustomAdapter11(keyvalue, MainActivity.this);
                    // TwoWayView lvTest = (TwoWayView)findViewById(R.id.lvItems);
                    // lvTest.setAdapter(adapter12);
                    HorizontalListView list = (HorizontalListView) findViewById(R.id.HorizontalListView);
                    list.setAdapter(adapter313);
                    // HorizontalListView list=(HorizontalListView)findViewById(R.id.listview);
                    // list.setAdapter(adapter12);

                    et1.setText(url1);

                     pw.dismiss();
                }
            });



            pw = new PopupWindow(layout1, LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT, true);
            if(tab)
            {
                if(density == 320 && screen_height >= 1250)//nexus 9 and 10
                {
                    pw.setHeight(1200);//pw height 600
                }
                if(density == 320 && screen_height <= 1280) //nexus 7
                {
                    pw.setHeight(800);//pw height 400
                }
                if(density == 160 && screen_height>= 400)//7" wsvga
                {
                    pw.setHeight(400);//pw height 400
                }
                if(density == 160 && screen_height>= 600)// 10.1" wxga
                {
                    pw.setHeight(600);//pw height 600
                }
                if(density == 213 || density<=213 && density>=200)//tvdpi
                {
                   pw.setHeight(400);//pw height 400
                }



            }
            else
            {
                if(density ==320)
                {
                    pw.setHeight(400);
                }
                if (density == 480)
                {
                    pw.setHeight(600);
                }
                if (density ==160)
                {
                    pw.setHeight(225);
                }
                if(density ==560)
                {
                    pw.setHeight(700);
                }
                if(density >560)
                {
                    pw1.setHeight(800);
                }
            }
            // display the popup in the center
            pw.showAtLocation(layout1, Gravity.CENTER,0 , 0);

            Button cancelButton = (Button) layout1.findViewById(R.id.cancel);

            cancelButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                  pw.dismiss();
                }
            });



        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    void initiatePopupWindow1(){
        try {
            //We need to get the instance of the LayoutInflater, use the context of this activity
            LayoutInflater inflater = (LayoutInflater) MainActivity.this
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            //Inflate the view from a predefined XML layout
            View layout1 = inflater.inflate(R.layout.popup_layout1,
                    (ViewGroup) findViewById(R.id.popup_element1));

            db.open();
            Cursor c= db.getAllContacts();
            int url=c.getColumnIndex(DBAdapter.KEY_URL);
            int add=c.getColumnIndex(DBAdapter.KEY_URLADDRESS);
            ArrayList<String> result = new ArrayList();
            for (c.moveToFirst(); !c.isAfterLast(); ) {



                result.add(c.getString(url)+"\n"+c.getString(add)+"\n");

                c.moveToNext();
            }


            final MyCustomAdapter adapter11 = new MyCustomAdapter(result ,this);





            //handle listview and assign adapter
            lView = (ListView)layout1.findViewById(R.id.lview);
            //lView.setClickable(true);

            lView.setAdapter(adapter11);



           /* lView.setOnItemClickListener(new OnItemClickListener() {

                public void onItemClick(AdapterView<?> arg0, View arg1,
                                        int arg2, long arg3) {
                    // TODO Auto-generated method stub
                   pw1.dismiss();
                }
            });*/
          /*  lView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

                public void onItemClick(AdapterView<?> arg0, View arg1,
                                        int arg2, long arg3) {
                    // TODO Auto-generated method stub
                    System.out.println("Item Clicked");
                    pw1.dismiss();
                }
            });*/



            pw1 = new PopupWindow(layout1, LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT, true);
            if(tab)
            {
                if(density == 320 && screen_height >= 1250)//nexus 9 and 10
                {
                    pw1.setHeight(1200);//pw height 600
                }

                if(density == 320  && screen_height <= 1280) //nexus 7
                {
                    pw1.setHeight(800);//pw height 400
                }
                if(density == 160 && screen_height>= 400)//7" wsvga
                {
                    pw1.setHeight(400);//pw height 400
                }
                if(density == 160 && screen_height>= 600)// 10.1" wxga
                {
                    pw1.setHeight(600);//pw height 600
                }
                if(density == 213 || density<=213 && density>=200)//tvdpi
                {
                    pw1.setHeight(400);//pw height 400
                }
            }
            else
            {
                if(density ==320)
                {
                    pw1.setHeight(400);
                }
                if (density == 480)
                {
                    pw1.setHeight(600);
                }
                if (density ==160)
                {
                    pw1.setHeight(225);
                }
                if(density ==560)
                {
                    pw1.setHeight(700);
                }
                if(density >560)
                {
                    pw1.setHeight(800);
                }
            }
            // display the popup in the center
            pw1.showAtLocation(layout1, Gravity.CENTER, 0, 0);

            Button cancelButton = (Button) layout1.findViewById(R.id.cancel1);

            cancelButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    pw1.dismiss();
                }
            });
           /* View layout2=(View) findViewById(R.id.popuplist);
            TextView textView1 = (TextView)layout2.findViewById(R.id.url);
            textView1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    pw1.dismiss();
                }
            });*/

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

  //  4.

 /*   private boolean isTablet()
    {
        Display display = getWindowManager().getDefaultDisplay();
        DisplayMetrics displayMetrics = new DisplayMetrics();
        display.getMetrics(displayMetrics);

        int width = displayMetrics.widthPixels / displayMetrics.densityDpi;
        int height = displayMetrics.heightPixels / displayMetrics.densityDpi;

        double screenDiagonal = Math.sqrt( width * width + height * height );
        return (screenDiagonal >= 7.0 );
    }*/

  //  5.

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





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        metrics = this.getResources().getDisplayMetrics();
        screen_width = metrics.widthPixels;
        screen_height=  metrics.heightPixels;
        density=metrics.densityDpi;
        d1 =   metrics.density;
        w111=  metrics.xdpi;
        h111=  metrics.ydpi;

        int spacing = (int) (5 * d1 + 0.5f);
        int spacing1 = (int) (10 * d1 + 0.5f);


         tab=isTablet(MainActivity.this);
        if(tab)
        {
            setContentView(R.layout.activity_main);
            d=getWindowManager().getDefaultDisplay();
            d.getSize(p);
            swidth = p.x;
            sheight = p.y;
            Toast.makeText(MainActivity.this, "its a tablet", Toast.LENGTH_LONG).show();
        }
        else
        {
            setContentView(R.layout.activity_main1);
            d=getWindowManager().getDefaultDisplay();
            d.getSize(p);
            swidth = p.x;
            sheight = p.y;
            Toast.makeText(MainActivity.this, "its a phone", Toast.LENGTH_LONG).show();
        }
//        1.
//        float yInches= metrics.heightPixels/metrics.ydpi;
//        float xInches= metrics.widthPixels/metrics.xdpi;
//        double diagonalInches = Math.sqrt(xInches*xInches + yInches*yInches);
//        if (diagonalInches>=5){
//            // 5inch device or bigger
//        }else{
//            // smaller device
//        }
//
//
//        2.
//        DisplayMetrics dm = new DisplayMetrics();
//        getWindowManager().getDefaultDisplay().getMetrics(dm);
//        double x = Math.pow(dm.widthPixels/dm.xdpi,2);
//        double y = Math.pow(dm.heightPixels/dm.ydpi,2);
//        double screenInches = Math.sqrt(x+y);
//        Log.d("debug","Screen inches : " + screenInches);
//
//        screenInches=  (double)Math.round(screenInches * 10) / 10;
//
//
//        3.
//        DisplayMetrics dm = new DisplayMetrics();
//        getWindowManager().getDefaultDisplay().getMetrics(dm);
//        double x = Math.pow(mWidthPixels/dm.xdpi,2);
//        double y = Math.pow(mHeightPixels/dm.ydpi,2);
//        double screenInches = Math.sqrt(x+y);
//        Log.d("debug","Screen inches : " + screenInches);

        tv1 = (TextView) findViewById(R.id.tv1);
        tv2 = (TextView) findViewById(R.id.tv2);
        tv3 = (TextView) findViewById(R.id.tv3);
        tv4 = (TextView) findViewById(R.id.tv4);

        b = (Button) findViewById(R.id.b1);
        b1 = (Button) findViewById(R.id.b2);
        b2 = (Button) findViewById(R.id.b3);
        b3 = (Button) findViewById(R.id.b4);
        b4 = (Button) findViewById(R.id.b5);
        b5 = (Button) findViewById(R.id.b6);
        b6=(Button)findViewById(R.id.badd);
        b7=(Button)findViewById(R.id.bclr);


        sp1 = (Spinner) findViewById(R.id.sp1);
        lv1 = (ListView) findViewById(R.id.lv1);
        et1 = (EditText) findViewById(R.id.et1);
        ib1 = (ImageButton) findViewById(R.id.ib1);
       // Toast.makeText(MainActivity.this,"screen width:"+swidth,Toast.LENGTH_LONG).show();
       // Toast.makeText(MainActivity.this,"screen density:"+density,Toast.LENGTH_LONG).show();
       // Toast.makeText(MainActivity.this,"screen width:"+screen_width,Toast.LENGTH_LONG).show();

       // if(screen_width == 1280  && density == 213  )//nexus 7(2012)//exact width
        if(screen_width == 1280 || screen_width== 1224 && density == 213  )//nexus 7(2012)//exact width
        {
            double width1=(screen_width*0.0665);
           //  double width111=(screen_width*0.0665);
            double width111=(screen_width*0.075);
            double w1= (screen_width*0.79);
            // double w111= (screen_width*0.075);
            //   int w = ((int)w111);
            double lw = screen_width-(screen_width * 2/3) ;
            int lw1= ((int)lw);
            int w11 = ((int)w1);
            int w111 = ((int)width111);

            int width11 = ((int)width1);

           /* LayoutParams paramssp = null;
            paramssp = sp1.getLayoutParams();
            paramssp.width = width11;
            sp1.setLayoutParams(paramssp);*/

            LayoutParams paramsib=null;
            paramsib=ib1.getLayoutParams();
            paramsib.width=45;
            paramsib.height=45;
            ib1.setLayoutParams(paramsib);

            LayoutParams paramsb = null;
            paramsb = b.getLayoutParams();
            paramsb.width = w111;
            b.setLayoutParams(paramsb);

            LayoutParams paramset = null;
            paramset = et1.getLayoutParams();
            paramset.width = w11;
            et1.setLayoutParams(paramset);

            int width = (screen_width / 5) - (spacing*2);

            LayoutParams params = null;
            params = b1.getLayoutParams();
            params.width = width;
            b1.setLayoutParams(params);

            LayoutParams params1 = null;
            params1 = b2.getLayoutParams();
            params1.width = width;
            b2.setLayoutParams(params1);


            LayoutParams params2 = null;
            params2 = b3.getLayoutParams();
            params2.width = width;
            b3.setLayoutParams(params2);

            LayoutParams params3 = null;
            params3 = b4.getLayoutParams();
            params3.width = width;
            b4.setLayoutParams(params3);

            LayoutParams params4 = null;
            params4 = b5.getLayoutParams();
            params4.width = width;
            b5.setLayoutParams(params4);

            LayoutParams params5 = null;
            params5 = lv1.getLayoutParams();
            params5.width = lw1;
            lv1.setLayoutParams(params5);
        }
      /*  if (screen_width ==1280) //for nexus 7 in api 16
        {

        }*/

        if(screen_width ==854 && density == 160 )//5.4" //exact width
        {
            double width1=(screen_width*0.075);

            double w1= (screen_width*0.75);
            // double w111= (screen_width*0.075);
            //   int w = ((int)w111);
            double lw = screen_width-(screen_width * 2/3) ;
            int lw1= ((int)lw);
            int w11 = ((int)w1);

            int width11 = ((int)width1);

           /* LayoutParams paramssp = null;
            paramssp = sp1.getLayoutParams();
            paramssp.width = width11;
            sp1.setLayoutParams(paramssp);*/

            LayoutParams paramsib=null;
            paramsib=ib1.getLayoutParams();
            paramsib.width=27;
            paramsib.height=27;
            ib1.setLayoutParams(paramsib);

            LayoutParams paramsb = null;
            paramsb = b.getLayoutParams();
            paramsb.width = width11+23;// 23 means 854*0.02694
            b.setLayoutParams(paramsb);

            LayoutParams paramset = null;
            paramset = et1.getLayoutParams();
            paramset.width = w11;
            et1.setLayoutParams(paramset);

            int width = (screen_width / 5) - (spacing1*2);

            LayoutParams params = null;
            params = b1.getLayoutParams();
            params.width = width;
            b1.setLayoutParams(params);

            LayoutParams params1 = null;
            params1 = b2.getLayoutParams();
            params1.width = width;
            b2.setLayoutParams(params1);


            LayoutParams params2 = null;
            params2 = b3.getLayoutParams();
            params2.width = width;
            b3.setLayoutParams(params2);

            LayoutParams params3 = null;
            params3 = b4.getLayoutParams();
            params3.width = width;
            b4.setLayoutParams(params3);

            LayoutParams params4 = null;
            params4 = b5.getLayoutParams();
            params4.width = width;
            b5.setLayoutParams(params4);

            LayoutParams params5 = null;
            params5 = lv1.getLayoutParams();
            params5.width = lw1;
            lv1.setLayoutParams(params5);

        }

        if(screen_width ==800 && density == 160 )//5.1" //exact width
        {

            double width1=(screen_width*0.07620);

            double w1= (screen_width*0.75);
            // double w111= (screen_width*0.075);
            //   int w = ((int)w111);
            double lw = screen_width-(screen_width * 2/3) ;
            int lw1= ((int)lw);
            int w11 = ((int)w1);

            int width11 = ((int)width1);

           /* LayoutParams paramssp = null;
            paramssp = sp1.getLayoutParams();
            paramssp.width = width11;
            sp1.setLayoutParams(paramssp);*/

            LayoutParams paramsib=null;
            paramsib=ib1.getLayoutParams();
            paramsib.width=27;
            paramsib.height=27;
            ib1.setLayoutParams(paramsib);

            LayoutParams paramsb = null;
            paramsb = b.getLayoutParams();
            paramsb.width = width11+19;//19 means 800*0.02375
            b.setLayoutParams(paramsb);

            LayoutParams paramset = null;
            paramset = et1.getLayoutParams();
            paramset.width = w11;
            et1.setLayoutParams(paramset);

            int width = (screen_width / 5) - (spacing1*2);

            LayoutParams params = null;
            params = b1.getLayoutParams();
            params.width = width;
            b1.setLayoutParams(params);

            LayoutParams params1 = null;
            params1 = b2.getLayoutParams();
            params1.width = width;
            b2.setLayoutParams(params1);


            LayoutParams params2 = null;
            params2 = b3.getLayoutParams();
            params2.width = width;
            b3.setLayoutParams(params2);

            LayoutParams params3 = null;
            params3 = b4.getLayoutParams();
            params3.width = width;
            b4.setLayoutParams(params3);

            LayoutParams params4 = null;
            params4 = b5.getLayoutParams();
            params4.width = width;
            b5.setLayoutParams(params4);

            LayoutParams params5 = null;
            params5 = lv1.getLayoutParams();
            params5.width = lw1;
            lv1.setLayoutParams(params5);

        }

        if(screen_width ==1280 && density == 160 )//10.1" //exact width
        {
            double width1=(screen_width*0.05);

            double w1= (screen_width*0.85);
            // double w111= (screen_width*0.075);
            //   int w = ((int)w111);
            double lw = screen_width-(screen_width * 2/3) ;
            int lw1= ((int)lw);
            int w11 = ((int)w1);

            int width11 = ((int)width1);

           /* LayoutParams paramssp = null;
            paramssp = sp1.getLayoutParams();
            paramssp.width = width11;
            sp1.setLayoutParams(paramssp);*/

            LayoutParams paramsib=null;
            paramsib=ib1.getLayoutParams();
            paramsib.width=27;
            paramsib.height=27;
            ib1.setLayoutParams(paramsib);

            LayoutParams paramsb = null;
            paramsb = b.getLayoutParams();
            paramsb.width = width11+4;// 4 means 0.00313 add in width11
            b.setLayoutParams(paramsb);

            LayoutParams paramset = null;
            paramset = et1.getLayoutParams();
            paramset.width = w11;
            paramset.height = 38;
            et1.setLayoutParams(paramset);

            int width = (screen_width / 5) - (spacing*2);

            LayoutParams params = null;
            params = b1.getLayoutParams();
            params.width = width;
            b1.setLayoutParams(params);

            LayoutParams params1 = null;
            params1 = b2.getLayoutParams();
            params1.width = width;
            b2.setLayoutParams(params1);


            LayoutParams params2 = null;
            params2 = b3.getLayoutParams();
            params2.width = width;
            b3.setLayoutParams(params2);

            LayoutParams params3 = null;
            params3 = b4.getLayoutParams();
            params3.width = width;
            b4.setLayoutParams(params3);

            LayoutParams params4 = null;
            params4 = b5.getLayoutParams();
            params4.width = width;
            b5.setLayoutParams(params4);

            LayoutParams params5 = null;
            params5 = lv1.getLayoutParams();
            params5.width = lw1;
            lv1.setLayoutParams(params5);

        }
        if( screen_width == 1024 && density == 160 ) //7" wsvga //exact width
        {

            double width1=(screen_width*0.0625);

            double w1= (screen_width*0.8);
            double w111= (screen_width*0.075);
            int w = ((int)w111);
            int w11 = ((int)w1);
            double lw = screen_width-(screen_width * 2/3) ;
            int lw1= ((int)lw);

            int width11 = ((int)width1);

          /*  LayoutParams paramssp = null;
            paramssp = sp1.getLayoutParams();
            paramssp.width = width11;
            sp1.setLayoutParams(paramssp);*/

        LayoutParams paramsib=null;
        paramsib=ib1.getLayoutParams();
        paramsib.width=27;
        paramsib.height=27;
        ib1.setLayoutParams(paramsib);


            LayoutParams paramsb = null;
            paramsb = b.getLayoutParams();
            paramsb.width = w+3;//3 means 0.002930 or 0.002929 add in w
            b.setLayoutParams(paramsb);


            LayoutParams paramset = null;
            paramset = et1.getLayoutParams();
            paramset.width = w11;
            et1.setLayoutParams(paramset);

            int width = (screen_width / 5) - (spacing*2);

            LayoutParams params = null;
            params = b1.getLayoutParams();
            params.width = width;
            b1.setLayoutParams(params);

            LayoutParams params1 = null;
            params1 = b2.getLayoutParams();
            params1.width = width;
            b2.setLayoutParams(params1);


            LayoutParams params2 = null;
            params2 = b3.getLayoutParams();
            params2.width = width;
            b3.setLayoutParams(params2);

            LayoutParams params3 = null;
            params3 = b4.getLayoutParams();
            params3.width = width;
            b4.setLayoutParams(params3);

            LayoutParams params4 = null;
            params4 = b5.getLayoutParams();
            params4.width = width;
            b5.setLayoutParams(params4);

            LayoutParams params5 = null;
            params5 = lv1.getLayoutParams();
            params5.width = lw1;
            lv1.setLayoutParams(params5);

        }

if( screen_width == 2048 ) //nexus 9 //exact width
{
    double width1=(screen_width*0.0625);

    double w1= (screen_width*0.8);
    double w111= (screen_width*0.075);
    int w = ((int)w111);
    int w11 = ((int)w1);
    double lw = screen_width-(screen_width * 2/3) ;
    int lw1= ((int)lw);

    int width11 = ((int)width1);

    LayoutParams paramssp = null;
    paramssp = sp1.getLayoutParams();
    paramssp.width = width11;
    sp1.setLayoutParams(paramssp);

     /*   LayoutParams paramstv=null;
        paramstv=tv1.getLayoutParams();
        paramstv.width=width11;
        tv1.setLayoutParams(paramstv);*/

    LayoutParams paramsb = null;
    paramsb = b.getLayoutParams();
    paramsb.width = w;
    b.setLayoutParams(paramsb);

    LayoutParams paramset = null;
    paramset = et1.getLayoutParams();
    paramset.width = w11;
    et1.setLayoutParams(paramset);

    int width = (screen_width / 5) - (spacing*2);

    LayoutParams params = null;
    params = b1.getLayoutParams();
    params.width = width;
    b1.setLayoutParams(params);

    LayoutParams params1 = null;
    params1 = b2.getLayoutParams();
    params1.width = width;
    b2.setLayoutParams(params1);


    LayoutParams params2 = null;
    params2 = b3.getLayoutParams();
    params2.width = width;
    b3.setLayoutParams(params2);

    LayoutParams params3 = null;
    params3 = b4.getLayoutParams();
    params3.width = width;
    b4.setLayoutParams(params3);

    LayoutParams params4 = null;
    params4 = b5.getLayoutParams();
    params4.width = width;
    b5.setLayoutParams(params4);

    LayoutParams params5 = null;
    params5 = lv1.getLayoutParams();
    params5.width = lw1;
    lv1.setLayoutParams(params5);

}
        if( screen_width > 2560 ) //nexus 10 above //exact width
        {
            double width1=(screen_width*0.05);

            double w1= (screen_width*0.85);
           // double w111= (screen_width*0.075);
         //   int w = ((int)w111);
            double lw = screen_width-(screen_width * 2/3) ;
            int lw1= ((int)lw);
            int w11 = ((int)w1);

            int width11 = ((int)width1);

            LayoutParams paramssp = null;
            paramssp = sp1.getLayoutParams();
            paramssp.width = width11;
            sp1.setLayoutParams(paramssp);

     /*   LayoutParams paramstv=null;
        paramstv=tv1.getLayoutParams();
        paramstv.width=width11;
        tv1.setLayoutParams(paramstv);*/

            LayoutParams paramsb = null;
            paramsb = b.getLayoutParams();
            paramsb.width = width11;
            b.setLayoutParams(paramsb);

            LayoutParams paramset = null;
            paramset = et1.getLayoutParams();
            paramset.width = w11;
            et1.setLayoutParams(paramset);

            int width = (screen_width / 5) - (spacing*2);

            LayoutParams params = null;
            params = b1.getLayoutParams();
            params.width = width;
            b1.setLayoutParams(params);

            LayoutParams params1 = null;
            params1 = b2.getLayoutParams();
            params1.width = width;
            b2.setLayoutParams(params1);


            LayoutParams params2 = null;
            params2 = b3.getLayoutParams();
            params2.width = width;
            b3.setLayoutParams(params2);

            LayoutParams params3 = null;
            params3 = b4.getLayoutParams();
            params3.width = width;
            b4.setLayoutParams(params3);

            LayoutParams params4 = null;
            params4 = b5.getLayoutParams();
            params4.width = width;
            b5.setLayoutParams(params4);

            LayoutParams params5 = null;
            params5 = lv1.getLayoutParams();
            params5.width = lw1;
            lv1.setLayoutParams(params5);

        }
        if( screen_width == 2560 ) //nexus 10 //exact width
        {
            double width1=(screen_width*0.05);

            double w1= (screen_width*0.85);
            // double w111= (screen_width*0.075);
            //   int w = ((int)w111);
            double lw = screen_width-(screen_width * 2/3) ;
            int lw1= ((int)lw);
            int w11 = ((int)w1);

            int width11 = ((int)width1);

            LayoutParams paramssp = null;
            paramssp = sp1.getLayoutParams();
            paramssp.width = width11;
            sp1.setLayoutParams(paramssp);

     /*   LayoutParams paramstv=null;
        paramstv=tv1.getLayoutParams();
        paramstv.width=width11;
        tv1.setLayoutParams(paramstv);*/

            LayoutParams paramsb = null;
            paramsb = b.getLayoutParams();
            paramsb.width = width11;
            b.setLayoutParams(paramsb);

            LayoutParams paramset = null;
            paramset = et1.getLayoutParams();
            paramset.width = w11;
            et1.setLayoutParams(paramset);

            int width = (screen_width / 5) - (spacing*2);

            LayoutParams params = null;
            params = b1.getLayoutParams();
            params.width = width;
            b1.setLayoutParams(params);

            LayoutParams params1 = null;
            params1 = b2.getLayoutParams();
            params1.width = width;
            b2.setLayoutParams(params1);


            LayoutParams params2 = null;
            params2 = b3.getLayoutParams();
            params2.width = width;
            b3.setLayoutParams(params2);

            LayoutParams params3 = null;
            params3 = b4.getLayoutParams();
            params3.width = width;
            b4.setLayoutParams(params3);

            LayoutParams params4 = null;
            params4 = b5.getLayoutParams();
            params4.width = width;
            b5.setLayoutParams(params4);

            LayoutParams params5 = null;
            params5 = lv1.getLayoutParams();
            params5.width = lw1;
            lv1.setLayoutParams(params5);

        }
       if(screen_width == 1794 || screen_width == 1080 || screen_width == 1920 && density == 480)//  nexus 5 // not exact width round off
      //  if(screen_width == 1794  && density == 480)*done
        {
            double width1=(screen_width*0.1);
            double width111=(screen_width*0.12); //or multiply by 0.13 and see
            double w1= (screen_width*0.70);
            double lw = screen_width-(screen_width * 2/3) ;
            int lw1= ((int)lw);
            int w = ((int)width111);
            int w11 = ((int)w1);

            int width11 = ((int)width1);

            LayoutParams paramssp = null;
            paramssp = sp1.getLayoutParams();
            paramssp.width = width11;
            sp1.setLayoutParams(paramssp);

        /* LayoutParams paramstv=null;
        paramstv=tv1.getLayoutParams();
        paramstv.width=width11;
        tv1.setLayoutParams(paramstv);*/

            LayoutParams paramsb = null;
            paramsb = b.getLayoutParams();
            paramsb.width = w;
            b.setLayoutParams(paramsb);

            LayoutParams paramset = null;
            paramset = et1.getLayoutParams();
            paramset.width = w11;
            paramset.height=102;
            et1.setLayoutParams(paramset);

            int width = (screen_width / 5) - (spacing1*2);

            LayoutParams params = null;
            params = b1.getLayoutParams();
            params.width = width;
            b1.setLayoutParams(params);

            LayoutParams params1 = null;
            params1 = b2.getLayoutParams();
            params1.width = width;
            b2.setLayoutParams(params1);


            LayoutParams params2 = null;
            params2 = b3.getLayoutParams();
            params2.width = width;
            b3.setLayoutParams(params2);

            LayoutParams params3 = null;
            params3 = b4.getLayoutParams();
            params3.width = width;
            b4.setLayoutParams(params3);

            LayoutParams params4 = null;
            params4 = b5.getLayoutParams();
            params4.width = width;
            b5.setLayoutParams(params4);

            LayoutParams params5 = null;
            params5 = lv1.getLayoutParams();
            params5.width = lw1;
            lv1.setLayoutParams(params5);

        }
        if( screen_width == 1920  && density == 320)//nexus 7 //exact width * hope done
        // also present with 1280 width but with api 16
        //if( screen_width == 942)
        {
            double width1=(screen_width*0.066675);

            double w1= (screen_width*0.75);
            double lw = screen_width-(screen_width * 2/3) ;
            int lw1= ((int)lw);
            int w11 = ((int)w1);

            int width11 = ((int)width1);

            LayoutParams paramssp = null;
            paramssp = sp1.getLayoutParams();
            paramssp.width = width11;
            sp1.setLayoutParams(paramssp);

        /* LayoutParams paramstv=null;
        paramstv=tv1.getLayoutParams();
        paramstv.width=width11;
        tv1.setLayoutParams(paramstv);*/

          /*  LayoutParams paramsb = null;
            paramsb = b.getLayoutParams();
            paramsb.width = width11;
            b.setLayoutParams(paramsb);*/

            LayoutParams paramset = null;
            paramset = et1.getLayoutParams();
            paramset.width = w11;
            et1.setLayoutParams(paramset);

            int width = (screen_width / 5) - (spacing*2);

            LayoutParams params = null;
            params = b1.getLayoutParams();
            params.width = width;
            b1.setLayoutParams(params);

            LayoutParams params1 = null;
            params1 = b2.getLayoutParams();
            params1.width = width;
            b2.setLayoutParams(params1);


            LayoutParams params2 = null;
            params2 = b3.getLayoutParams();
            params2.width = width;
            b3.setLayoutParams(params2);

            LayoutParams params3 = null;
            params3 = b4.getLayoutParams();
            params3.width = width;
            b4.setLayoutParams(params3);

            LayoutParams params4 = null;
            params4 = b5.getLayoutParams();
            params4.width = width;
            b5.setLayoutParams(params4);

            LayoutParams params5 = null;
            params5 = lv1.getLayoutParams();
            params5.width = lw1;
            lv1.setLayoutParams(params5);

        }

      //  if(screen_width == 1038 || screen_width == 638 || screen_width == 1024 || screen_width ==608 || screen_width == 1044 || screen_width == 628 || screen_width ==768 || screen_width == 1196 || screen_width ==1184&& density == 320)//nexus 4
        //not exact width round off//more available width in potrait view 1024 and 1044 *done
        if(screen_width == 1196  && density == 320)
        {
            double lw = screen_width-(screen_width * 2/3) ;
            int lw1= ((int)lw);
            LayoutParams paramsb = null;
            paramsb = b.getLayoutParams();
            paramsb.height = 76;
            b.setLayoutParams(paramsb);
            LayoutParams paramsib = null;
            paramsib = ib1.getLayoutParams();
            paramsib.width = 54;
            paramsib.height = 54;
            ib1.setLayoutParams(paramsib);


            LayoutParams params5 = null;
            params5 = lv1.getLayoutParams();
            params5.width = lw1;
            lv1.setLayoutParams(params5);

        }

        if( screen_width == 1038 || screen_width == 1196 && density == 320)//galaxy nexus //not exact widht round off
        //  if(screen_width == 1794  && density == 480)* done
        {
            double lw = screen_width-(screen_width * 2/3) ;
            int lw1= ((int)lw);
            LayoutParams paramsb = null;
            paramsb = b.getLayoutParams();
            paramsb.height = 76;
            b.setLayoutParams(paramsb);
            LayoutParams paramsib = null;
            paramsib = ib1.getLayoutParams();
            paramsib.width = 54;
            paramsib.height = 54;
            ib1.setLayoutParams(paramsib);


            LayoutParams params5 = null;
            params5 = lv1.getLayoutParams();
            params5.width = lw1;
            lv1.setLayoutParams(params5);

        }

        if( density == 560 || screen_width == 2392)//nexus 6  not exact width round off
        //  if(screen_width == 1794  && density == 480) *hope done
        {
            //width is 2560 so nexus 10 measurement can be copied for layout across width
           // double width1=(screen_width*0.0875);/his should be real height for nexus 6 with 560 dpi as emulator is showing 640 dpi
            double width1=(screen_width*0.1);
           // double w1= (screen_width*0.75);//this should be width if  nexus 6 is proper 560
            double w1= (screen_width*0.70);
            double lw = screen_width-(screen_width * 2/3) ;
            int lw1= ((int)lw);
            int w11 = ((int)w1);

            int width11 = ((int)width1);

            LayoutParams paramssp = null;
            paramssp = sp1.getLayoutParams();
            paramssp.width = width11;
            sp1.setLayoutParams(paramssp);

            if (density>=560)
            {
                LayoutParams paramsib = null;
                paramsib = ib1.getLayoutParams();
                paramsib.width = 95;
                paramsib.height = 95;
                ib1.setLayoutParams(paramsib);
            }
            LayoutParams paramsb = null;
            paramsb = b.getLayoutParams();
            paramsb.width = width11+32;
            b.setLayoutParams(paramsb);

            LayoutParams paramset = null;
            paramset = et1.getLayoutParams();
            paramset.width = w11;
            paramset.height=136;//34*3.5 =119
            et1.setLayoutParams(paramset);

            int width = (screen_width / 5) - (spacing1*2);

            LayoutParams params = null;
            params = b1.getLayoutParams();
            params.width = width;
            b1.setLayoutParams(params);

            LayoutParams params1 = null;
            params1 = b2.getLayoutParams();
            params1.width = width;
            b2.setLayoutParams(params1);


            LayoutParams params2 = null;
            params2 = b3.getLayoutParams();
            params2.width = width;
            b3.setLayoutParams(params2);

            LayoutParams params3 = null;
            params3 = b4.getLayoutParams();
            params3.width = width;
            b4.setLayoutParams(params3);

            LayoutParams params4 = null;
            params4 = b5.getLayoutParams();
            params4.width = width;
            b5.setLayoutParams(params4);

            LayoutParams params5 = null;
            params5 = lv1.getLayoutParams();
            params5.width = lw1;
            lv1.setLayoutParams(params5);

        }




        prefs = getSharedPreferences(prefName, MODE_PRIVATE);
            final Boolean check = prefs.getBoolean("myval", false);

            if (check) {
               // et1.setText("http://");
            }
            else {
              //  et1.setText("check box not checked");
            }

        pref2=  getSharedPreferences(prefName2, MODE_PRIVATE);
        float fontSize1 = pref2.getFloat(FONT_SIZE_KEY, 11);
        tv4.setTextSize(fontSize1);
       // tv4.setText("fontsize is:"+fontSize1);
      Toast.makeText(MainActivity.this,"Text size is:"+fontSize1,Toast.LENGTH_LONG).show();



        action = getResources().getStringArray(R.array.action);

        ArrayAdapter<String> adapter
                = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, action); // find other layout parameters
        sp1.setAdapter(adapter);

        if(tab)
        {
            if(density >= 240)
            {
                sp1.setPadding(15, 10, 10, 10);
            }
            if(density == 160 || density == 213)
            {
                sp1.setPadding(9, 10, 0, 10);//try with padding  6 instead of 9 if not looking good
            }

        }
       else
       {
           if (density > 200)
           {
               sp1.setPadding(20, 10, 0, 10);//try with padding 17 instead of 20 if not looking good
           }
           if (density >= 560)
           {
               sp1.setPadding(20,5, 0, 5);
           }

           if(density < 200)
           {
               sp1.setPadding(10,5, 0, 5);
           }
       }
       // sp1.setGravity(17);

        sp1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
                int index = arg0.getSelectedItemPosition();

        // Toast.makeText(getBaseContext(), action[index] + " : Opted as your state of domicile", Toast.LENGTH_SHORT).show();
               action2= action[index];
               // Toast.makeText(getBaseContext(), action2 + " : Opted as your state of domicile", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
                action2="get";
            }
        });

        b4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



                initiatePopupWindow();



            }
        });
        b5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



                initiatePopupWindow1();



            }
        });
        b2.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                et1.setText("");
                tv4.setText("");
                list11.clear();
                ArrayAdapter<String> adapter3
                        = new ArrayAdapter<String>(MainActivity.this, android.R.layout.simple_list_item_1,list11);
                lv1.setAdapter(adapter3);

                keyvalue.clear();


                ArrayAdapter<String> adapter6
                        = new ArrayAdapter<String>(MainActivity.this,R.layout.simple_list_item_1,keyvalue);


                // TwoWayView lvTest = (TwoWayView)findViewById(R.id.lvItems);
                // lvTest.setAdapter(adapter12);
                HorizontalListView list = (HorizontalListView) findViewById(R.id.HorizontalListView);
                list.setAdapter(adapter6);

                // HorizontalListView list=(HorizontalListView)findViewById(R.id.listview);
                // list.setAdapter(adapter12);


            }
        });

        ib1.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                //  DBAdapter db = new DBAdapter(MainActivity.this);

                et1.setText("");


            }
        });









        b3.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent i=new Intent(MainActivity.this,settings.class);
            pref2=  getSharedPreferences(prefName2, MODE_PRIVATE);
            SharedPreferences.Editor editor = pref2.edit();


            float font=  tv4.getTextSize();
            if(density == 213 )//tvdpi
            {
                font11 = font / 1.33125;//1.33125 or 1.33

                font1= (float)font11;
               //  font1 = Float.parseFloat(new Double(font11).toString());
               //   Toast.makeText(MainActivity.this,""+font,Toast.LENGTH_LONG).show();
               //   Toast.makeText(MainActivity.this,""+font11,Toast.LENGTH_LONG).show();
            }
            if(density == 240 || density<=319 && density >= 240)//hdpi
            {
               font11 = font / 1.5;
                font1= (float)font11;
            }
            if(density == 320 || density<=340 && density>=320)//xhdpi
            {
                 font1 = font / 2;
            }
            if (density == 480)//xxhdpi
            {
                font1 = font / 3;
            }
            if (density == 640)//xxxhdpi
            {
                font1 = font / 4;
            }
            if (density == 560)//special nexus 6
            {
                font11 = font/3.5;
                font1= (float)font11;
            }
            if(density == 160 || density<=199 && density>=141)//mdpi
            {
                font1 = font/1;
            }
          /*  else
            {
                font1 = font/1;
            }*/

            editor.putFloat(FONT_SIZE_KEY, font1);

            editor.commit();



            startActivity(i);
        }
    });


    b1.setOnClickListener(new View.OnClickListener() {


            public void onClick(View arg0) {
                // TODO Auto-generated method stub
                final Dialog dialog = new Dialog(MainActivity.this);
                dialog.setContentView(R.layout.popup);
                dialog.setTitle("Save Url ");
                // set the custom dialog components - text, image and button
                final EditText text = (EditText) dialog.findViewById(R.id.ptv2);
                text.setHint("url name");
                // text.setText("Android custom dialog example!");
                final EditText text1 = (EditText) dialog.findViewById(R.id.ptv3);
                text1.setHint("url");
                //text1.setText("Android custom dialog example!");

                Button dialogButton = (Button) dialog.findViewById(R.id.pbok);
                // if button is clicked, close the custom dialog
                dialogButton.setOnClickListener(new OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        //dialog.dismiss();
                        String name1 = text.getText().toString();
                        String value1 = text1.getText().toString();
                        if(name1 != null && name1.trim().length() ==0|| value1 != null && value1.trim().length() ==0)
                        {
                            dialog.setCancelable(false);
                            text.setHint("keyvalue cannot be empty");
                            text1.setHint("keyvalue cannot be empty");
                        }
                        else {

                            DBAdapter db = new DBAdapter(MainActivity.this);
                            id = db.insertContact(name1,value1);
                            int fk_id=db.getid(name1);
                            if (keyvalue != null)
                            {

                                for (String s:keyvalue)
                                {
                                    String kv[]=s.split("\n");

                                    for (int f=0;f<kv.length;f=f+2)
                                    {
                                        id1 = db.insertContact1(kv[f], kv[f+1],fk_id);
                                    }
                                }
                            }
                            dialog.dismiss();
                        }

                    }
                });
                Button dialogButton1 = (Button) dialog.findViewById(R.id.pbc);
                // if button is clicked, close the custom dialog
                dialogButton1.setOnClickListener(new OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });

                dialog.show();



            }
        });



        b6.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View arg0) {

                // custom dialog
                final Dialog dialog = new Dialog(MainActivity.this);
                dialog.setContentView(R.layout.popup);
                dialog.setTitle("save key value");

                // set the custom dialog components - text, image and button
                final EditText text = (EditText) dialog.findViewById(R.id.ptv2);
                text.setHint("key");
               // text.setText("Android custom dialog example!");
                final EditText text1 = (EditText) dialog.findViewById(R.id.ptv3);
                text1.setHint("value");
                //text1.setText("Android custom dialog example!");



                Button dialogButton = (Button) dialog.findViewById(R.id.pbok);
                // if button is clicked, close the custom dialog
                dialogButton.setOnClickListener(new OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        //dialog.dismiss();
                        String name1 = text.getText().toString();
                        String value1 = text1.getText().toString();
                        if(name1 != null && name1.trim().length() ==0|| value1 != null && value1.trim().length() ==0)
                        {
                            dialog.setCancelable(false);
                            text.setHint("keyvalue cannot be empty");
                            text1.setHint("keyvalue cannot be empty");
                        }
                        else {

                            keyvalue.add(name1 + "\n" + value1 + "\n");
                            MyCustomAdapter11 adapter12 = new MyCustomAdapter11(keyvalue, MainActivity.this);
                            // TwoWayView lvTest = (TwoWayView)findViewById(R.id.lvItems);
                            // lvTest.setAdapter(adapter12);
                            HorizontalListView list = (HorizontalListView) findViewById(R.id.HorizontalListView);
                            list.setAdapter(adapter12);
                            // HorizontalListView list=(HorizontalListView)findViewById(R.id.listview);
                            // list.setAdapter(adapter12);
                            dialog.dismiss();
                        }

                    }
                });
                Button dialogButton1 = (Button) dialog.findViewById(R.id.pbc);
                // if button is clicked, close the custom dialog
                dialogButton1.setOnClickListener(new OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });

                dialog.show();
            }
        });




            b7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                keyvalue.clear();


                ArrayAdapter<String> adapter6
                        = new ArrayAdapter<String>(MainActivity.this,R.layout.simple_list_item_1,keyvalue);


                // TwoWayView lvTest = (TwoWayView)findViewById(R.id.lvItems);
                // lvTest.setAdapter(adapter12);
                  HorizontalListView list = (HorizontalListView) findViewById(R.id.HorizontalListView);
                list.setAdapter(adapter6);

                // HorizontalListView list=(HorizontalListView)findViewById(R.id.listview);
                // list.setAdapter(adapter12);

            }
        });







        b.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                try
                {
                    // CONNECTIVITY_SERVICE returns ConnectivityManager object
                    ConnectivityManager c=(ConnectivityManager)getSystemService(Context.CONNECTIVITY_SERVICE);
                    NetworkInfo n=c.getActiveNetworkInfo();// requires permission ACCESS_NETWORK_STATE
                    if(n!=null && n.isConnected())
                    {

                        String address=et1.getText().toString();
                      //  StringBuilder add=new StringBuilder(et1.getText().toString());// another method
                      // if(action2.compareToIgnoreCase(gp)== 0) {
                          // Toast.makeText(MainActivity.this,"get selected",Toast.LENGTH_LONG).show();
                           if (check) {
                               // add.insert(0,"http://");  // another method
                               //  String addr=add.toString(); // another method
                               String s = "http://" + address;
                               new Background().execute(s);
                           } else {
                               new Background().execute(address);
                           }
                     //  }
                      /*  else
                       {
                           if (check) {
                               Toast.makeText(MainActivity.this,"post selected",Toast.LENGTH_LONG).show();
                               // add.insert(0,"http://");  // another method
                               //  String addr=add.toString(); // another method
                               String s = "http://" + address;
                               new Background1().execute(s);
                           } else {
                               new Background1().execute(address);
                           }
                       }*/
                    }
                }catch(Exception e){ System.out.print("Error : "+e.toString());}

            }
        });
    }
    @Override
    protected void onResume() {
        super.onResume();
        Toast.makeText(MainActivity.this,"we are back",Toast.LENGTH_LONG).show();
        prefs = getSharedPreferences(prefName, MODE_PRIVATE);
        Boolean check = prefs.getBoolean("myval", false);
       float fontSize1 = pref2.getFloat(FONT_SIZE_KEY, 0);
       tv4.setTextSize(fontSize1);
      // tv4.setText("fontsize is:"+fontSize1);

        if (check) {
           // et1.setText("http://");

        } else {

           // et1.setText("check box not checked");

        }

    }

    @Override
    protected void onPause() {
        super.onPause();

              /* if (pw1 != null) {
            if (pw1.isShowing() ) {

                pw1.dismiss();


            }
        }
        else
        {
            super.onPause();

        }*/





    }


    class Background extends AsyncTask<String, Void, String>
    {
        String check="";


        protected void onPreExecute()
        {
            // This method runs on UI thread

            Toast.makeText(getBaseContext(), "Calling Background Thread", Toast.LENGTH_LONG).show();
            super.onPreExecute();
        }

        @Override
        protected String doInBackground(String... params) {
            // TODO Auto-generated method stub

            // This method runs on Background Thread

            HttpClient httpclient = new DefaultHttpClient();




            HttpGet httpget = new HttpGet(params[0]);
           HttpPost httpPost=new HttpPost(params[0]);
            HttpResponse response;





            try
            {
                if(action2.compareToIgnoreCase(gp)== 0)
                {
                    response = httpclient.execute(httpget);
                }
               else
                {
                    response = httpclient.execute(httpPost);
                }



                 Header[] headers=     response.getAllHeaders();
                for (Header h:headers)
                {

                    list11.add ("Key:"+h.getName()+"\n"+"Value:"+h.getValue());

                }




                //this execute makes the connection with app and server using URL and returns HttpResponse obj.
                if(response!=null)
                {
                    if(response.getStatusLine().getStatusCode() == 200)
                    {

                        list11.add(0,"key:status"+"\n"+"Value:200");
                        HttpEntity entity = response.getEntity();// obtain entity from the response

                      //  response.getHeaders(String [s]);


                        if (entity != null)
                        {
                            // A Simple JSON Response Read
                            InputStream instream = entity.getContent();



                            check =convertStreamToString(instream);

                            if(check.equals(""))
                            {
                                check="no data found from file";
                            }
                        }
                    }
                }

            }
            catch(Exception e)
            {
                Log.d("---////////////---", e.toString());
            }
            return check;
        }

        public void onProgressUpdate()
        {
            //This method runs on UI thread
            // Not Used In this Program
            // Used to show user the progress of work being done in the Background Thread
        }
        public void onPostExecute(String s)
        {
            //This method runs on UI thread

          // System.out.println("response"+s);
           // adapter1.addAll(list11);
            ArrayAdapter<String> adapter2
                                   = new ArrayAdapter<String>(MainActivity.this, android.R.layout.simple_list_item_1,list11);
            lv1.setAdapter(adapter2);

            tv4.setText(s);
            //Toast.makeText(getBaseContext(), s, Toast.LENGTH_LONG).show();
        }





    }

  /*  class Background1 extends AsyncTask<String, Void, String>
    {
        String check="";


        protected void onPreExecute()
        {
            // This method runs on UI thread

            Toast.makeText(getBaseContext(), "Calling Background Thread", Toast.LENGTH_LONG).show();
            super.onPreExecute();
        }

        @Override
        protected String doInBackground(String... params) {
            // TODO Auto-generated method stub

            // This method runs on Background Thread

            HttpClient httpclient = new DefaultHttpClient();




           // HttpGet httpget = new HttpGet(params[0]);
            HttpPost httpPost=new HttpPost(params[0]);
            HttpResponse response;





            try
            {

                response = httpclient.execute(httpPost);




                Header[] headers=     response.getAllHeaders();
                for (Header h:headers)
                {

                    list11.add ("Key:"+h.getName()+"\n"+"Value:"+h.getValue());


                }

              //  Toast.makeText(MainActivity.this,""+response.getStatusLine().getStatusCode(),Toast.LENGTH_LONG);

               // list11.add(1,);
                //this execute makes the connection with app and server using URL and returns HttpResponse obj.
                if(response!=null)
                {
                    if(response.getStatusLine().getStatusCode() == 200) {

                        list11.add(0, "key:status" + "\n" + "Value:200");
                        HttpEntity entity = response.getEntity();// obtain entity from the response
                        //  response.getHeaders(String [s]);


                        if (entity != null) {
                            // A Simple JSON Response Read
                            InputStream instream1 = entity.getContent();


                            check = convertStreamToString(instream1);

                            if (check.equals("")) {
                                check = "no data found from file";
                            }
                        }

                    }
                }

            }
            catch(Exception e)
            {
                Log.d("---////////////---", e.toString());
            }
            return check;
        }

        public void onProgressUpdate()
        {
            //This method runs on UI thread
            // Not Used In this Program
            // Used to show user the progress of work being done in the Background Thread
        }
        public void onPostExecute(String s)
        {
            //This method runs on UI thread

            // System.out.println("response"+s);
            // adapter1.addAll(list11);
            ArrayAdapter<String> adapter2
                    = new ArrayAdapter<String>(MainActivity.this, android.R.layout.simple_list_item_1,list11);
            lv1.setAdapter(adapter2);

            tv4.setText(s);
            //Toast.makeText(getBaseContext(), s, Toast.LENGTH_LONG).show();
        }





    }*/



    private String convertStreamToString(InputStream is)
    {
        BufferedReader reader = new BufferedReader(new InputStreamReader(is));
        String data = "";

        String line = null;
        try
        {
            while ((line = reader.readLine()) != null)
            {
                if(data == "")
                {
                    data = line;
                    Log.d("--???????--", data);
                }
                else
                {
                    data += "\n" + line;
                    Log.d("--!!!!!!!!!--", data);
                }
            }
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        finally
        {
            try
            {
                is.close();
            } catch (IOException e)
            {
                e.printStackTrace();
            }
        }
        return data;
    }







    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
