package com.lms.app.com.lms.app.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Negative {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
    private int fid;
    
    private  int id;
    private String message;
    private String Name;
	public int getFid() {
		return fid;
	}
	public void setFid(int fid) {
		this.fid = fid;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	
	
	
	
	public String getName() {
		return Name;
	}
	public void setName(String name) {
		Name = name;
	}
	@Override
	public String toString() {
		return "Negative [fid=" + fid + ", id=" + id + ", message=" + message + ", Name=" + Name + "]";
	}
	
	
	
    
    
    
    
}
