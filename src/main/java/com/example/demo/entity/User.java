package com.example.demo.entity;

import com.sun.javafx.beans.IDProperty;

public class User {

//    public User() {
//    }
//
//    public int getId() {
//        return id;
//    }
//
//    public String getName() {
//        return name;
//    }
//
//    public void setId(int id) {
//        this.id = id;
//    }
//
//    public void setName(String name) {
//        this.name = name;
//    }
//
//    public User(String name) {
//
//        this.name = name;
//    }
//
//    private int id;
//    private String name;



    private String userId;
    private String title;
    private String body;
    private String id;

    public User() {
    }

    public User(String userId, String title, String body, String id) {
        this.userId = userId;
        this.title = title;
        this.body = body;
        this.id = id;
    }

    public User(String userId, String title, String body) {
        this.userId = userId;
        this.title = title;
        this.body = body;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
