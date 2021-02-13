package com.lms.app.com.lms.app.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class MiddleComments {
	
	
	@Id
	@GeneratedValue
     private int fid;
    
    private  int id;
    private String message;
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
	@Override
	public String toString() {
		return "MiddleComments [fid=" + fid + ", id=" + id + ", message=" + message + "]";
	}
    
    
    
    
    

}
