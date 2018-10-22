package com.patidar.pawan.mapapp.modal;


public class User {
    String user_id ;
    String name;
    String  email;

    public User (String id, String name,String email){
        this.user_id=id;
        this.name=name;
        this.email=email;
    }

    public String  getUser_id() {
        return user_id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }
}
