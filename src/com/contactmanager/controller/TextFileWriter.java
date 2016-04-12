package com.contactmanager.controller;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.ArrayList;

import android.os.Environment;
import android.util.Log;

import com.contactmanager.model.Contact;


public class TextFileWriter {

	String fileLocation;
	ArrayList<Contact> recordsList;
	
	/**
	 * Constructor for TextFileWriter receives the path of the text file and the ArrayList of records to be used.
	 */
	public TextFileWriter(String file_location,ArrayList<Contact> recoList){
		
		fileLocation=file_location;
		recordsList=recoList;
	}
	
	
	/**
	 * reWriteFile() is used to re-write the text file with the new, updated, contents of the ArrayList of records
	 */
	public void reWriteFile() throws IOException{
		//FileOutputStream fos=new FileOutputStream(fileLocation,false);
		FileWriter fileWriter=new FileWriter(fileLocation,false);
		//Log.d("chkpoint","created fileWriter");
		PrintWriter printWriter=new PrintWriter(fileWriter);
		//Log.d("chkpoint","created printWriter");	
		//printWriter.println("sjfhgahfgdsh");
		
		for(int i=0;i<recordsList.size();i++){
			//Log.d("in loop","in loop");
			Contact temp_record=recordsList.get(i);
			//Log.d("fetch element","got "+i+"th element");
			String temp=temp_record.getFirstName()+"\t"+temp_record.getLastName()+"\t"+temp_record.getPhoneNo()+"\t"+temp_record.getEmail();
			//Log.d("temp","temp");
			printWriter.println(temp);
			//Log.d("p write","p write");
		}
		printWriter.close();
		
	}
	
}
