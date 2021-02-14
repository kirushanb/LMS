package com.lms.app.com.lms.app.model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Students {

    @Id
    private  int id;
    private String Name;
    private String Email;
    private String Username;
    private String Password;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getUsername() {
        return Username;
    }

    public void setUsername(String username) {
        Username = username;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }


    public Students(String name, String email, String username, String password) {
        Name = name;
        Email = email;
        Username = username;
        Password = password;
    }

    public Students() {
        super();
    }

    @Override
    public String toString() {
        return "students{" +
                "id=" + id +
                ", Name='" + Name + '\'' +
                ", Email='" + Email + '\'' +
                ", Username='" + Username + '\'' +
                ", Password='" + Password + '\'' +
                '}';
    }
}
