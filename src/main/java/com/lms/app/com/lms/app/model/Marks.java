package com.lms.app.com.lms.app.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Marks {

	
	@Id
	@GeneratedValue
	private int marksid;
	private String marks;
	private int id;
	private int sid;
	public int getMarksid() {
		return marksid;
	}
	public void setMarksid(int marksid) {
		this.marksid = marksid;
	}
	public String getMarks() {
		return marks;
	}
	public void setMarks(String marks) {
		this.marks = marks;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getSid() {
		return sid;
	}
	public void setSid(int sid) {
		this.sid = sid;
	}
	@Override
	public String toString() {
		return "Marks [marksid=" + marksid + ", marks=" + marks + ", id=" + id + ", sid=" + sid + "]";
	}
	
	
	
	
	
	
}
