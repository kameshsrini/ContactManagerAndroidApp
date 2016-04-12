package com.contactmanager.view;

import java.util.List;

import com.contactmanager.model.Contact;
import com.example.contacts.R;

import android.R.color;
import android.app.Activity;
import android.app.LauncherActivity.ListItem;
import android.content.Context;
import android.graphics.Color;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
 
public class ContactViewerAdapter extends ArrayAdapter<String[]> {
 
    Context context;
    LinearLayout linearLayout;
    public ContactViewerAdapter(Context context, int resourceId,List<String[]> items) {
        super(context, resourceId, items);
        this.context = context;
        
    }
 
    /*private view holder class*/
    private class ViewHolder {
        TextView fieldtypeView;
        TextView fieldvalueView;
        ImageView iconView;
    }
    
    @Override
    public void notifyDataSetChanged() {
    	// TODO Auto-generated method stub
    	super.notifyDataSetChanged();
    }
 
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        
        String[] rowItem = getItem(position);
        
        //linearLayout=(LinearLayout)view.findViewById(R.id.listitem_layout);
        
 
        LayoutInflater mInflater = (LayoutInflater) context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.viewer_listitem, null);
            holder = new ViewHolder();
       
            holder.fieldtypeView = (TextView) convertView.findViewById(R.id.fieldtype);
            holder.iconView = (ImageView) convertView.findViewById(R.id.iconView);
            holder.fieldvalueView = (TextView) convertView.findViewById(R.id.fieldvalue);
            convertView.setTag(holder);
        } else
            holder = (ViewHolder) convertView.getTag();
 
        //holder.txtDesc.setText(rowItem.getDesc());
        holder.fieldtypeView.setText(rowItem[0]);
        //holder.get.setText(rowItem.getText());
        holder.fieldvalueView.setText(rowItem[1]);
        if(rowItem[2].equals("phone")){
        	holder.iconView.setImageResource(R.drawable.callicon);
        }
        else if(rowItem[2].equals("email")){
        	holder.iconView.setImageResource(R.drawable.emailicon);
        }
        return convertView;
    }
}


