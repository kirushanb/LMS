package com.lms.app.com.lms.app.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class User_message {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	private String Fname ;
	private String Lname ;
	private String email ;
	private String Pnumber ;
	private String message ;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getFname() {
		return Fname;
	}
	public void setFname(String fname) {
		Fname = fname;
	}
	public String getLname() {
		return Lname;
	}
	public void setLname(String lname) {
		Lname = lname;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPnumber() {
		return Pnumber;
	}
	public void setPnumber(String pnumber) {
		Pnumber = pnumber;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	@Override
	public String toString() {
		return "User_message [id=" + id + ", Fname=" + Fname + ", Lname=" + Lname + ", email=" + email + ", Pnumber="
				+ Pnumber + ", message=" + message + "]";
	}
	
	
	
	
	
	
}
