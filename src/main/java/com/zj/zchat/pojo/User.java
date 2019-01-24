package com.zj.zchat.pojo;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.List;

public class User {
    private String username;
    private String email;
    private String mobile;
    @JsonIgnore
    private String password;

    public void setUsername(String username) {
        this.username = username;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setMobile(String  mobile) {
        this.mobile = mobile;
    }

    public void setPassword(String password) {
        this.password = password;
    }


    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }

    public String getMobile() {
        return mobile;
    }

    public String getPassword() {
        return password;
    }


}
