package com.jk.model;

public class User {
    private Integer userid;

    private String username;

    private Integer userpwd;

    public Integer getUserid() {
        return userid;
    }

    public String getUsername() {
        return username;
    }

    public Integer getUserpwd() {
        return userpwd;
    }

    public void setUserid(Integer userid) {
        this.userid = userid;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setUserpwd(Integer userpwd) {
        this.userpwd = userpwd;
    }
}
