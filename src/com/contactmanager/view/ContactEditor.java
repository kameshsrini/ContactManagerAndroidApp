package com.contactmanager.view;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import com.example.contacts.R;
import com.contactmanager.controller.TextFileWriter;
import com.contactmanager.model.Contact;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;

@SuppressLint("NewApi") public class ContactEditor extends Activity{
	
	ArrayList<Contact> contactsList;
	int index;
	String mode;
	EditText fnameText,lnameText,phoneText,emailText;
	Button saveButton,cancelButton;
	TextFileWriter textFileWriter;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.editor_contact);
		getActionBar().hide();
		//Getting Intent extras from parent activity
		contactsList=(ArrayList<Contact>) getIntent().getSerializableExtra("contacts_list");
		index=getIntent().getIntExtra("index", -1);
		mode=getIntent().getStringExtra("mode");
				
		textFileWriter=new TextFileWriter(Environment.getExternalStorageDirectory()+"/contacts.txt", contactsList);
		
		fnameText=(EditText)findViewById(R.id.fnameText);
		lnameText=(EditText)findViewById(R.id.lnameText);
		phoneText=(EditText)findViewById(R.id.phoneText);
		emailText=(EditText)findViewById(R.id.emailText);
		saveButton=(Button)findViewById(R.id.saveButton);
		cancelButton=(Button)findViewById(R.id.cancelButton);
		
		getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);
		addSaveButtonListener();
		addCancelButtonListener();
		
		
		//If in edit mode, populate text fields.
		if(mode.equals("edit")){	
			populateFields();
		}
	}
	
	@Override
	public void onBackPressed() {
		// TODO Auto-generated method stub
		super.onBackPressed();
		if(mode.equals("edit")){
			showContactViewer(0);
		}
	}
	
	void addSaveButtonListener(){
		//Click listener for SAVE button
				saveButton.setOnClickListener(new OnClickListener() {
					
					@Override
					public void onClick(View v) {
						// TODO Auto-generated method stub
					  if(allFieldsEmpty()){
						  finish();		//Do nothing and close if all fields are empty
						  if(mode.equals("edit")){
							  showContactViewer(0);
						  }
					  }	
					  else
					  {
						if(mode.equals("edit")){
							//edit mode
							String[] toWrite=getWritableData();
							//Contact temp=new Contact(fnameText.getText().toString(),lnameText.getText().toString(),phoneText.getText().toString(),emailText.getText().toString());
							Contact temp=new Contact(toWrite[0],toWrite[1],toWrite[2],toWrite[3]);
							contactsList.set(index, temp);
							
							finish();
							showContactViewer(0);							
						}
						else if(mode.equals("add")){
							//add mode
							String[] toWrite=getWritableData();
							//Contact temp=new Contact(fnameText.getText().toString(),lnameText.getText().toString(),phoneText.getText().toString(),emailText.getText().toString());
							Contact temp=new Contact(toWrite[0],toWrite[1],toWrite[2],toWrite[3]);
							contactsList.add(temp);
							
							finish();
							showContactViewer(1);
						}
						
						sortRecords(contactsList);
						try {
							textFileWriter.reWriteFile();
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					  }
					}
				});
	}
	
	void addCancelButtonListener(){
		//click listener for CANCEL button
		cancelButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				finish();
				if(mode.equals("edit")){
					showContactViewer(0);
				}
			}
		});
	}
	
	String[] getWritableData(){
		
		String[] record=new String[4];
		String fname=fnameText.getText().toString();
		record[0]=fname.equals("")?"\u0000":fname;
		String lname=lnameText.getText().toString();
		record[1]=lname.equals("")?"\u0000":lname;
		String phone=phoneText.getText().toString();
		record[2]=phone.equals("")?"\u0000":phone;
		String email=emailText.getText().toString();
		record[3]=email.equals("")?"\u0000":email;
		
		return record;
	}
	
	void showContactViewer(int mode){ 	//mode=0 : Edited/no change/cancel button; mode=1 : new contact added 

		Intent viewerIntent = new Intent(ContactEditor.this, ContactViewer.class);
		if(mode==0){
			viewerIntent.putExtra("index",index);
			viewerIntent.putExtra("contacts_list",contactsList);
		}
		else{
			viewerIntent.putExtra("index",(contactsList.size()-1));
			viewerIntent.putExtra("contacts_list",contactsList);
		}
		ContactEditor.this.startActivity(viewerIntent);
	}
	
	void populateFields(){

		fnameText.setText(contactsList.get(index).getFirstName().trim());
		lnameText.setText(contactsList.get(index).getLastName().trim());
		phoneText.setText(contactsList.get(index).getPhoneNo().trim());
		emailText.setText(contactsList.get(index).getEmail().trim());	
		fnameText.setSelection(fnameText.length());
	}
	
	boolean allFieldsEmpty(){
		if((fnameText.getText().toString().equals(""))&&(lnameText.getText().toString().equals(""))&&(phoneText.getText().toString().equals(""))&&(emailText.getText().toString().equals(""))){
			return true;
		}
		else{
			return false;
		}
	}
	
	void sortRecords(ArrayList<Contact> recordsList){
		Collections.sort(recordsList,new Comparator<Contact>() {
            public int compare(Contact contact1, Contact contact2) {
            	if(contact1.getFirstName().equals("\u0000")&&contact1.getLastName().equals("\u0000")){
            		return 1;
            	}
            	else{
            		return contact1.getFirstName().toUpperCase().compareTo(contact2.getFirstName().toUpperCase());
            	}
            }
        });
	}
	
	
}
