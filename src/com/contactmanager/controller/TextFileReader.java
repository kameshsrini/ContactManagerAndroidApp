package com.contactmanager.controller;

import java.io.File;
import java.io.IOException;
import java.io.FileReader;
import java.io.BufferedReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import com.contactmanager.model.Contact;


public class TextFileReader {
	
	private String fileLocation;
	
	/**
	 * Constructor for TextFileReader receives the path of the text file to be used.
	 */
	public TextFileReader(String file_location){
		fileLocation=file_location;
	}
	
	/**
	 * noOfLines is used to compute the total number of records stored in the text file.
	 */
	int noOfLines() throws IOException{
		FileReader fileReader=new FileReader(fileLocation);
		BufferedReader bufferedReader=new BufferedReader(fileReader);
		int numOfLines=0;
		
		while ((bufferedReader.readLine())!=null){
			numOfLines++;
			}
		
		bufferedReader.close();
		return numOfLines;
	}

	/**
	 * ReadLinesToArray() returns an ArrayList containing all the records in the text file. 
	 * Each entry in the ArrayList is a Contact object.   
	 */
	public ArrayList<Contact> ReadRecordsToArrayList() throws IOException{
		FileReader fileReader=new FileReader(fileLocation);
		BufferedReader bufferedReader=new BufferedReader(fileReader);
		int numOfLines=noOfLines();
		ArrayList<Contact> recordList=new ArrayList<Contact>();
		
		for(int i=0;i<numOfLines;i++){
			String temp_record=bufferedReader.readLine();
			String[] record=temp_record.split("\t");
			recordList.add(new Contact(record[0],record[1],record[2],record[3]));
		}
		sortRecords(recordList);
		
		bufferedReader.close();
		return recordList;
	}
	
	/**
	 * sortRecords() is used to sort the records in the ArrayList based on first name.
	 */
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
