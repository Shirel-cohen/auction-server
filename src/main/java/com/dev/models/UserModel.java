package com.dev.models;

import com.dev.objects.User;

public class UserModel {


    private String username;

    public UserModel(){}

    public UserModel(User user){
        this.username = user.getUsername();
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
