package com.frame.ssm.domain;

import javax.servlet.http.HttpSessionBindingEvent;
import javax.servlet.http.HttpSessionBindingListener;
import java.io.Serializable;

public class Person implements HttpSessionBindingListener, Serializable {
    private String username;
    private String password;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    public void valueBound(HttpSessionBindingEvent hsbe) {
        System.out.println("valueBound name: "+hsbe.getName());
    }

    public void valueUnbound(HttpSessionBindingEvent hsbe) {
        System.out.println("valueUnbound name: "+hsbe.getName());
    }
}
