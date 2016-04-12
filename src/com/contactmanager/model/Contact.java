package com.contactmanager.model;

import java.io.Serializable;

public class Contact implements Serializable{
	
	String firstName, lastName, phoneNo, email;
	
	public Contact(String fname,String lname,String phone,String eMail) {
		firstName=fname;
		lastName=lname;
		phoneNo=phone;
		email=eMail;
	}
	
	public String getFirstName() {
		return firstName;
	}
	
	public String getLastName() {
		return lastName;
	}
	
	public String getPhoneNo() {
		return phoneNo;
	}
	
	public String getEmail() {
		return email;
	}
	
	public boolean isEqualTo(Contact c){
		if((c.getFirstName().equals(firstName))&&(c.getLastName().equals(lastName))&&(c.getPhoneNo().equals(phoneNo))&&(c.getEmail().equals(email)))
			return true;
		else
			return false;
	}
}
