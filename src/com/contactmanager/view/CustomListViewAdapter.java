package com.contactmanager.view;

import java.util.HashMap;
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
import android.widget.SectionIndexer;
import android.widget.TextView;
 
public class CustomListViewAdapter extends ArrayAdapter<Contact> implements SectionIndexer{
 
    Context context;
    LinearLayout linearLayout;
    HashMap<String, Integer> alphaIndexer;
    String[] sections;
    public CustomListViewAdapter(Context context, int resourceId,List<Contact> items) {
        super(context, resourceId, items);
        this.context = context;
        
    }
 
    /*private view holder class*/
    private class ViewHolder {
        TextView nameView;
        TextView numberView;
      
    }
    
    @Override
    public void notifyDataSetChanged() {
    	// TODO Auto-generated method stub
    	super.notifyDataSetChanged();
    }
 
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        
        Contact rowItem = getItem(position);
        
        //linearLayout=(LinearLayout)view.findViewById(R.id.listitem_layout);
        
 
        LayoutInflater mInflater = (LayoutInflater) context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.list_item, null);
            holder = new ViewHolder();
       
            holder.nameView = (TextView) convertView.findViewById(R.id.name);
         
            holder.numberView = (TextView) convertView.findViewById(R.id.number);
            convertView.setTag(holder);
        } else
            holder = (ViewHolder) convertView.getTag();
 
        //holder.txtDesc.setText(rowItem.getDesc());
        if(rowItem.getFirstName().equals("\u0000")&&rowItem.getLastName().equals("\u0000")){
        	holder.nameView.setTextColor(Color.parseColor("#7F7F7F"));
        	holder.nameView.setText("<No name>");
        }
        else{
        	holder.nameView.setTextColor(Color.parseColor("#000000"));
        	holder.nameView.setText(rowItem.getFirstName()+" "+rowItem.getLastName());
        }
        //holder.get.setText(rowItem.getText());
        holder.numberView.setText("☏ "+rowItem.getPhoneNo());
        //if((position%2)!=0){
        //	convertView.setBackgroundColor(Color.rgb(221, 227, 230));
        //}
        return convertView;
    }

	@Override
	public int getPositionForSection(int arg0) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getSectionForPosition(int arg0) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Object[] getSections() {
		// TODO Auto-generated method stub
		return null;
	}
}


