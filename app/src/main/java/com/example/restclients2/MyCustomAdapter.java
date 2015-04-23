

package com.example.restclients2;

/**
 * Created by faizan on 2/18/2015.
 */
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;
import com.example.restclients2.getset;
import com.example.restclients2.MainActivity.*;

import com.example.restclients2.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by faizan on 2/18/2015.
 */
public class MyCustomAdapter extends BaseAdapter {

  private ArrayList<String> list = new ArrayList<String>();
    private Context context;
    MainActivity main1;
    PopupWindow pw1;
   // int layout = R.layout.popup_layout;
    //Popup popup;
//    final MainActivity main = new MainActivity();




    public MyCustomAdapter(ArrayList<String> result,Context context) {
        this.list = result;
        this.context = context;
    }

    @Override
    public int getCount() {
        return list.size();
       // return 0;
    }

    @Override
    public Object getItem(int pos) {
       return list.get(pos);
       // return  0;
    }

    @Override
    public long getItemId(int position) {
       // return list.indexOf(position);
      return   position;

    }

  /*   @Override
     public long getItemId(int pos) {
         return list.get(pos).getId(0);
         //just return 0 if your list items do not have an Id variable.
     }
*/

    public View getView(final int position, View convertView, ViewGroup parent) {
        // int or String entry = list.get(position);
        View view = convertView;
        if (view == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.my_custom_list_layout, null);
        }



        final TextView listItemText = (TextView) view.findViewById(R.id.url);
        listItemText.setText(list.get(position));



        //Handle buttons and add onClickListeners
        Button deleteBtn = (Button) view.findViewById(R.id.delete);
        deleteBtn.setFocusableInTouchMode(false);
        deleteBtn.setFocusable(false);
        // deleteBtn.setOnClickListener(this);
        deleteBtn.setTag(0);


        deleteBtn.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                //do something

//                int index=list.indexOf(getItem(position));
//                getset g=new getset();
//                g.set(index);
//                int id1=g.get();
//                int size=list.size();
              //  System.out.println("size is:"+ size);
              // System.out.println("id is:"+ id1);
              // int id11=id1+1;

                String itemToDelete = list.get(position).trim();

                String name1[]=itemToDelete.split("\n");



                DBAdapter db = new DBAdapter(context);
               db.open();

               int id= db.getid(name1[0]);

                db.deleteContact(id);
                db.deleteContactkv(id);

                list.remove(position);

               notifyDataSetChanged();




                // list.remove(list.get(position));
                // myAdapter.notifyDataSetChanged();

            }
        });


//        main.lView=(ListView)view.findViewById(R.id.lview);
//        main.lView.setOnClickListener(new OnClickListener() {
//
//            @Override
//            public void onClick(View v) {
//                // TODO Auto-generated method stub
//                main.pw1.dismiss();
//
//            }
//        });



        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                 long id=0;
                // int index1 =    list.indexOf(getItem(position));

                Intent i = new Intent(context, edit.class);

               String list = listItemText.getText().toString();
                String f[]=list.split("\n");
                DBAdapter db = new DBAdapter(context);

                db.open();
                int id1= db.getid(f[0]);

                i.putExtra("key",f[0]);
                i.putExtra("value",f[1]);


                i.putExtra("id",id1);
//                MainActivity main=new MainActivity();
//                main.pw1.dismiss();
                   // main1.pw1.dismiss();
              //  pw1=new PopupWindow(context, layout);
                 context.startActivity(i);







            }
        });



        return view;
    }
}