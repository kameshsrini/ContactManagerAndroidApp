package com.contactmanager.controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import com.contactmanager.model.Contact;


public class ContactManagerServices {

	ArrayList<Contact> recordsList;
	
	/**
	 * Constructor of ContactManagerServices receives the ArrayList of records that is to be used for all operations.
	 */
	public ContactManagerServices(ArrayList<Contact> recoList){
		recordsList=recoList;
	}
	
	
	/**
	 * addRecord() adds one valid and unique record to the ArrayList of records and sorts them. 
	 * It takes all the 4 necessary fields as parameters.
	 */
	public int addRecord(String fname,String lname,String phone,String email){
			recordsList.add(new Contact(fname,lname,phone,email));
			sortRecords(recordsList);
			
			//soundAlert("alert1.wav");
			return 1;	//added succesfully
	}
	
	/**
	 * modifyRecord() modifies a specified record with newly supplied data, which is valid and unique.
	 * It takes the current first, last and middle names along with the new data as parameters.
	 */
	public int modifyRecord(int index,String fname,String lname,String phone,String email){
		
		recordsList.set(index, new Contact(fname,lname,phone,email));
		return 1;
	}
	
	/**
	 * removeRecord() removes the record corresponding to the given first, middle and last names.
	 */
	public int removeRecord(int index){
		
		recordsList.remove(index);
		return 1;
		
	}
	
	/**
	 * sortRecords() is used to sort the records in the ArrayList based on first name.
	 */
	void sortRecords(ArrayList<Contact> recordsList){
		Collections.sort(recordsList,new Comparator<Contact>() {
            public int compare(Contact contact1, Contact contact2) {
                return contact1.getFirstName().compareTo(contact2.getFirstName());
            }
        });
	}
	
	/**
	 * soundAlert() creates a thread to play a specified alert sound
	 */
	public static synchronized void soundAlert(final String fileName) {
		  
		}
	
}
