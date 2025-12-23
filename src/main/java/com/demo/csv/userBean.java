package com.demo.csv;

import com.opencsv.bean.CsvBindByName;

public class userBean {

    @CsvBindByName(column = "username")
    public String x;

    @CsvBindByName(column = "password")
    public String y;

    // required no-arg constructor
    public userBean() {
    }

    public userBean(String username, String password) {
        this.x = username;
        this.y = password;
    }

    public String getUsername() {
        return x;
    }

    public void setUsername(String username) {
        this.x = username;
    }

    public String getPassword() {
        return y;
    }

  
    public void setPassword(String password) {
        this.y = password;
    }

    @Override
    public String toString() {
        return "userBean [username=" + x + ", password=" + y + "]";
    }
}

