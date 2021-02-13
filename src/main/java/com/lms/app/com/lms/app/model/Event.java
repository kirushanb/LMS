package com.lms.app.com.lms.app.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Event {

    @Id
    @GeneratedValue
    private int id;
    private String name;
    private String fdate;
    private String edate;
    private String comment;



    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFdate() {
        return fdate;
    }

    public void setFdate(String fdate) {
        this.fdate = fdate;
    }

    public String getEdate() {
        return edate;
    }

    public void setEdate(String edate) {
        this.edate = edate;
    }


    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    @Override
    public String toString() {
        return "event{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", fdate='" + fdate + '\'' +
                ", edate='" + edate + '\'' +
                ", comment='" + comment + '\'' +
                '}';
    }
}
