package com.jk.model;

public class Role {
    private Integer roleid;

    private String rolename;

    private String status;

    public Integer getRoleid() {
        return roleid;
    }

    public String getRolename() {
        return rolename;
    }

    public String getStatus() {
        return status;
    }

    public void setRoleid(Integer roleid) {
        this.roleid = roleid;
    }

    public void setRolename(String rolename) {
        this.rolename = rolename;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
