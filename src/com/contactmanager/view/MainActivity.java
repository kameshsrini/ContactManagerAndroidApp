package com.contactmanager.view;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.contactmanager.controller.TextFileReader;
import com.contactmanager.controller.TextFileWriter;
import com.contactmanager.model.Contact;
import com.example.contacts.R;
import com.example.contacts.R.id;
import com.example.contacts.R.layout;
import com.example.contacts.R.menu;


import android.support.v7.app.ActionBarActivity;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;


public class MainActivity extends Activity {

	ArrayList<Contact> contactsList;
	ListView list;
	Context con;
	TextFileReader fileReader;
	TextFileWriter fileWriter;
	CustomListViewAdapter adapter;
	@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        con=this;       
        fileReader=new TextFileReader(Environment.getExternalStorageDirectory()+"/contacts.txt");
        try {
			contactsList=fileReader.ReadRecordsToArrayList();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        displayList(contactsList);
        fileWriter=new TextFileWriter(Environment.getExternalStorageDirectory()+"/contacts.txt", contactsList);
        list = (ListView)findViewById(R.id.listView1);
        
        adapter = new CustomListViewAdapter(con,R.layout.activity_main, contactsList);
	    list.setAdapter(adapter);
	    list.setFastScrollEnabled(true);
	    addListViewClickListener(list);  
	    
    }
	
	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		fileReader=new TextFileReader(Environment.getExternalStorageDirectory()+"/contacts.txt");
        try {
        	contactsList.removeAll(contactsList);
			contactsList.addAll(fileReader.ReadRecordsToArrayList());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    adapter.notifyDataSetChanged();
	}
	
	void addListViewClickListener(final ListView listView){
		list.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position,
					long index) {
				// TODO Auto-generated method stub
				Contact selectedContact=(Contact) listView.getItemAtPosition(position);
				//Log.d("selected item", selectedContact.getFirstName()+selectedContact.getLastName()+selectedContact.getPhoneNo()+selectedContact.getEmail());
				Intent viewerIntent = new Intent(MainActivity.this, ContactViewer.class);
				
				viewerIntent.putExtra("index",position);
				viewerIntent.putExtra("contacts_list",contactsList);
				MainActivity.this.startActivity(viewerIntent);
			}
		});
	}
    
    void displayList(ArrayList<Contact> list){
    	for(int i=0;i<list.size();i++){
    		Log.d("entries",i+"   "+list.get(i).getFirstName()+"   "+list.get(i).getLastName()+"   "+list.get(i).getPhoneNo()+"   "+list.get(i).getEmail());
    	}
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
    	MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.activity_main_actions, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        switch(item.getItemId()){
        case R.id.action_add:
        	//Open editor
        	Intent editorIntent = new Intent(MainActivity.this, ContactEditor.class);
        	editorIntent.putExtra("contacts_list",contactsList);
        	editorIntent.putExtra("mode", "add");
        	editorIntent.putExtra("index", -1);
        	MainActivity.this.startActivity(editorIntent);
        	return true;
        default:
        	return super.onOptionsItemSelected(item);
        }
   
    }
}
