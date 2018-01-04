/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bakishan.user;

/**
 *
 * @author ss
 */
public class User {
 
    int id;
    String name;
    String email;
 
    public User(int id, String name, String email) {
        this.id = id;
        this.name = name;
        this.email = email;
    }
 
    //getters, setters and toString()

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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    
    
}