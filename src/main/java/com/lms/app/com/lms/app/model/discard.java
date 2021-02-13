package com.lms.app.com.lms.app.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class discard {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int fid;
    private  int id;
    private String message;
    private String Name;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getFid() {
        return fid;
    }

    public void setFid(int fid) {
        this.fid = fid;
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
        return "discad{" +
                "fid=" + fid +
                ", id=" + id +
                ", message='" + message + '\'' +
                ", Name='" + Name + '\'' +
                '}';
    }
}
