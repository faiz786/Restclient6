package com.example.restclients2;

/**
 * Created by faizan on 2/18/2015.
 */

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.graphics.Point;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import android.view.ViewGroup.LayoutParams;

//import com.devsmart.android.ui.HorizontalListView;
import com.meetme.android.horizontallistview.HorizontalListView;

import java.util.ArrayList;


/**
 * Created by faizan on 2/16/2015.
 */
public class edit extends Activity {

    TextView tv1,tv2;
    Button b1,b2,b3,b4;
    Spinner sp1;
    EditText et1,et2;
    String[] action;
    ImageButton ib1;
    boolean tab;
    int swidth ;
    int sheight;
    int density;
    float d1;
    float w111;
    float h111;
    final ArrayList<String> keyvalue = new ArrayList();
    public static DisplayMetrics metrics;
    Display d ;
    Point p = new Point();

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
        final int screen_width = metrics.widthPixels;
        final int screen_height=  metrics.heightPixels;
        density=metrics.densityDpi;
        d1 =   metrics.density;
        w111=  metrics.xdpi;
        h111=  metrics.ydpi;

       /* if(screen_width == 2560 && screen_height == 1600) {
            setContentView(R.layout.edit);

        }
        else
        {
            setContentView(R.layout.edit1);
        }*/

        tab=isTablet(edit.this);
        if(tab)
        {
            setContentView(R.layout.edit);
            d=getWindowManager().getDefaultDisplay();
            d.getSize(p);
            swidth = p.x;
            sheight = p.y;
            Toast.makeText(edit.this, "its a tablet", Toast.LENGTH_LONG).show();
        }
        else
        {
            setContentView(R.layout.edit1);
            d=getWindowManager().getDefaultDisplay();
            d.getSize(p);
            swidth = p.x;
            sheight = p.y;
            Toast.makeText(edit.this, "its a phone", Toast.LENGTH_LONG).show();
        }
        tv1=(TextView)findViewById(R.id.tv1);
        tv2=(TextView)findViewById(R.id.tv_url_name);

        b1=(Button)findViewById(R.id.b1);
        b2=(Button)findViewById(R.id.b2);
        b3=(Button)findViewById(R.id.badd);
        b4=(Button)findViewById(R.id.bclr);
        sp1=(Spinner)findViewById(R.id.sp1);
        et1=(EditText)findViewById(R.id.et1);
        et2=(EditText)findViewById(R.id.et2);
        ib1=(ImageButton)findViewById(R.id.ib1);
        final Intent intent = getIntent();
        final Bundle b=intent.getExtras();
        metrics = this.getResources().getDisplayMetrics();
       // int screen_width = metrics.widthPixels;

        if(screen_width ==1280 && density == 213   )//nexus 7(2012)
        {
            LayoutParams paramsib=null;
            paramsib=ib1.getLayoutParams();
            paramsib.width=36;
            paramsib.height=36;
            ib1.setLayoutParams(paramsib);
        }

        if(screen_width ==854 && density == 160 )//5.4"
        {
            LayoutParams paramsib=null;
            paramsib=ib1.getLayoutParams();
            paramsib.width=27;
            paramsib.height=27;
            ib1.setLayoutParams(paramsib);
        }

        if(screen_width ==800 && density == 160 )//5.1"
        {
            LayoutParams paramsib=null;
            paramsib=ib1.getLayoutParams();
            paramsib.width=27;
            paramsib.height=27;
            ib1.setLayoutParams(paramsib);

        }

        if(screen_width ==1280 && density == 160 )//10.1"
        {
            LayoutParams paramsib=null;
            paramsib=ib1.getLayoutParams();
            paramsib.width=27;
            paramsib.height=27;
            ib1.setLayoutParams(paramsib);
        }

            if( screen_width == 1024 && density == 160 ) //7" wsvga
        {
           LayoutParams paramsib=null;
            paramsib=ib1.getLayoutParams();
            paramsib.width=27;
            paramsib.height=27;
            ib1.setLayoutParams(paramsib);
        }


        if(screen_width == 2048) {
           /* double width1=(screen_width*0.0625);

            double w1= (screen_width*0.88);
            int w11 = ((int)w1);

            int width11 = ((int)width1);
            ViewGroup.LayoutParams paramssp = null;
            paramssp = sp1.getLayoutParams();
            paramssp.width = width11;
            sp1.setLayoutParams(paramssp);

     /*   LayoutParams paramstv=null;
        paramstv=tv1.getLayoutParams();
        paramstv.width=width11;
        tv1.setLayoutParams(paramstv);


            ViewGroup.LayoutParams paramset = null;
            paramset = et2.getLayoutParams();
            paramset.width = w11;
            et2.setLayoutParams(paramset);*/
        }
        if(screen_width == 2560  ) {
          /*  double width1=(screen_width*0.05);

            double w1= (screen_width*0.90);
            int w11 = ((int)w1);

            int width11 = ((int)width1);
            ViewGroup.LayoutParams paramssp = null;
            paramssp = sp1.getLayoutParams();
            paramssp.width = width11;
            sp1.setLayoutParams(paramssp);

     /*   LayoutParams paramstv=null;
        paramstv=tv1.getLayoutParams();
        paramstv.width=width11;
        tv1.setLayoutParams(paramstv);


            ViewGroup.LayoutParams paramset = null;
            paramset = et2.getLayoutParams();
            paramset.width = w11;
            et2.setLayoutParams(paramset);*/
        }
        if(screen_width == 1794 || screen_width == 1080 || screen_width == 1920 && density == 480)//nexus 5
        {
          /*  ViewGroup.LayoutParams paramset = null;
            paramset = et2.getLayoutParams();
            paramset.height=102;
            et2.setLayoutParams(paramset);*/
          //  ib1.setTop(20);
           // ib1.setPadding(0,15,0,0);
            LayoutParams paramsib=null;
            paramsib=ib1.getLayoutParams();
            paramsib.width=68;
            paramsib.height=68;
            ib1.setLayoutParams(paramsib);
        }
        if( screen_width == 1920  && density == 320)//nexus 7
        {

        }
     /*   if(screen_width == 942 || screen_width == 1662 && density == 320)//nexus 7
        {

        }*/
       // if(screen_width == 1038 || screen_width == 638 || screen_width == 1024 || screen_width ==608 || screen_width == 1044 || screen_width == 628 || screen_width ==768 || screen_width == 1196 || screen_width ==1184&& density == 320)//nexus 4
        if(screen_width == 1196  && density == 320)
        {
            LayoutParams paramsib = null;
            paramsib = ib1.getLayoutParams();
            paramsib.width = 54;
            paramsib.height = 54;
            ib1.setLayoutParams(paramsib);
        }
        if(screen_width == 1196 || screen_width == 1038 && density == 320)//galaxy nexus
        //  if(screen_width == 1794  && density == 480)
        {
            LayoutParams paramsib = null;
            paramsib = ib1.getLayoutParams();
            paramsib.width = 54;
            paramsib.height = 54;
            ib1.setLayoutParams(paramsib);

        }
        if( density == 560 || screen_width == 2392)//nexus 6
        //  if(screen_width == 1794  && density == 480)
        {
            LayoutParams paramsib=null;
            paramsib=ib1.getLayoutParams();
            paramsib.width=95;
            paramsib.height=95;
            ib1.setLayoutParams(paramsib);
        }


        String url1=b.getString("key");
        String addr1=b.getString("value");
        et1.setText(url1);
        et2.setText(addr1);
        DBAdapter db = new DBAdapter(edit.this);
        //final int fk_id=db.getid(url1);
        int fk_id=db.getid(url1);
        db.open();
        Cursor c= db.getContactkv(fk_id);
        int key=c.getColumnIndex(DBAdapter.KEY_URLKEY);
        int val=c.getColumnIndex(DBAdapter.KEY_VALUE);

        for (c.moveToFirst(); !c.isAfterLast(); ) {

            keyvalue.add(c.getString(key)+"\n"+c.getString(val)+"\n");

            c.moveToNext();
        }
        MyCustomAdapter11 adapter313 = new MyCustomAdapter11(keyvalue, edit.this);
        // TwoWayView lvTest = (TwoWayView)findViewById(R.id.lvItems);
        // lvTest.setAdapter(adapter12);
        HorizontalListView list = (HorizontalListView) findViewById(R.id.HorizontalListView);
        list.setAdapter(adapter313);
        // HorizontalListView list=(HorizontalListView)findViewById(R.id.listview);
        // list.setAdapter(adapter12);

        action = getResources().getStringArray(R.array.action);

        ArrayAdapter<String> adapter
                = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, action); // find other layout parameters

        sp1.setAdapter(adapter);
        if(tab) {
            if(density >= 250)
            {
                sp1.setPadding(15, 10, 10, 10);
            }
            if(density == 160 || density == 213)
            {
                sp1.setPadding(9, 10, 0, 10);//try with padding  6 instead of 9 if not looking good
            }
        }
        else {
            if (density > 200)
            {
                sp1.setPadding(20, 10, 0, 10);//try with padding 17 instead of 20 if not looking good
            }
            else
            {
                sp1.setPadding(10,5, 0, 5);
            }//try with padding 17 instead of 20 if not looking good
             }
        //sp1.setPadding(20,10,5,10);
        sp1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
                int index = arg0.getSelectedItemPosition();
                // Toast.makeText(getBaseContext(), action[index] + " : Opted as your state of domicile", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
            }
        });

        ib1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //  DBAdapter db = new DBAdapter(MainActivity.this);

                et2.setText("");


            }
        });
        b3.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {

                // custom dialog
                final Dialog dialog = new Dialog(edit.this);
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
                dialogButton.setOnClickListener(new View.OnClickListener() {
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
                            MyCustomAdapter11 adapter12 = new MyCustomAdapter11(keyvalue, edit.this);
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
                dialogButton1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });

                dialog.show();
            }
        });
        b4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                keyvalue.clear();
                ArrayAdapter<String> adapter6
                        = new ArrayAdapter<String>(edit.this,R.layout.simple_list_item_1,keyvalue);
                // TwoWayView lvTest = (TwoWayView)findViewById(R.id.lvItems);
                // lvTest.setAdapter(adapter12);
                HorizontalListView list = (HorizontalListView) findViewById(R.id.HorizontalListView);
                list.setAdapter(adapter6);
                // HorizontalListView list=(HorizontalListView)findViewById(R.id.listview);
                // list.setAdapter(adapter12);

            }
        });


        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DBAdapter db = new DBAdapter(edit.this);
                db.open();
                String url1 = et1.getText().toString();
                String urlname = et2.getText().toString();
                int fk_id=db.getid(url1);
                int id=b.getInt("id");

              boolean check=  db.updateContact(id,url1,urlname);
             //   boolean check1=  db.updateContact1(id,key,value);
                if (keyvalue != null)
                {

//                    String kv1=keyvalue.toString();
//                    String kv[] =  kv1.split("\n");
//                    for (int f=0;f<kv.length;f++){
                       //  if db.getContact1(id)!= null;
                        //   boolean check2=  db.updateContact1(id,key,value);
                        // aks for insert update
                        //                                String kv1=keyvalue.toString().trim();
//                                String kv[] =  kv1.split("\n");
//                                       for (int f=0;f<kv.length;f++)
//                                       {
//                                id1 = db.insertContact1(kv[f], kv[f+1],fk_id);
//                                       }
                        for (String s:keyvalue)
                        {
                            String kv11[]=s.split("\n");
                            for (int f1=0;f1<kv11.length;f1=f1+2)
                            {
                                long id1 = db.insertContact1(kv11[f1], kv11[f1+1],fk_id);
                            }
                        }

                    }


                if(check)
                {
                    Toast.makeText(edit.this,"update successful",Toast.LENGTH_LONG).show();
                }
                else
                {
                    Toast.makeText(edit.this,"update unsuccessful",Toast.LENGTH_LONG).show();
                }

            }
        });

        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1=new Intent(edit.this,MainActivity.class);
                //edit.this.startActivity(intent1);

                edit.this.finish();


                }

        });





    }
}
