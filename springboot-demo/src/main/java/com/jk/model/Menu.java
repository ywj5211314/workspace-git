package com.jk.model;

import java.util.List;

public class Menu {
    private Integer id;

    private String text;

    private String url;

    private Integer pid;

    private List<Menu> children;

    private boolean checked;

    public Integer getId() {
        return id;
    }

    public String getText() {
        return text;
    }

    public String getUrl() {
        return url;
    }

    public Integer getPid() {
        return pid;
    }

    public List<Menu> getChildren() {
        return children;
    }

    public boolean isChecked() {
        return checked;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setText(String text) {
        this.text = text;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void setPid(Integer pid) {
        this.pid = pid;
    }

    public void setChildren(List<Menu> children) {
        this.children = children;
    }

    public void setChecked(boolean checked) {
        this.checked = checked;
    }
}
