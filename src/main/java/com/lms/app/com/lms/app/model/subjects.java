package com.lms.app.com.lms.app.model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class subjects {

    @Id
    private  int sid;
    private String sname;
    private int id;


   


    public int getSid() {
		return sid;
	}

	public void setSid(int sid) {
		this.sid = sid;
	}

	public String getSname() {
		return sname;
	}

	public void setSname(String sname) {
		this.sname = sname;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public subjects() {
        super();
    }

	@Override
	public String toString() {
		return "subjects [sid=" + sid + ", sname=" + sname + ", id=" + id + "]";
	}

   
}
