

package com.example.restclients2;

/**
 * Created by faizan on 3/9/2015.
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
import android.widget.TextView;
import com.example.restclients2.getset;

import com.example.restclients2.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by faizan on 3/9/2015.
 */
public class MyCustomAdapter11 extends BaseAdapter {

    private ArrayList<String> list = new ArrayList<String>();
    private Context context;




    public MyCustomAdapter11(ArrayList<String> result,Context context) {
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
            view = inflater.inflate(R.layout.simple_list_item_1, null);
        }



        final TextView listItemText = (TextView) view.findViewById(R.id.htext);
        listItemText.setText(list.get(position));


         return view;
    }
}
