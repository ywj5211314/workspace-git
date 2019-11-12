package com.jk.model;

import java.io.Serializable;

public class Doctor implements Serializable {

    private static final long serialVersionUID = -5832599655323297066L;
    private Integer docid;
    private String docname;
    private Integer docage;
    private Integer docsex;



    public Integer getDocid() {
        return docid;
    }

    public String getDocname() {
        return docname;
    }

    public Integer getDocage() {
        return docage;
    }

    public Integer getDocsex() {
        return docsex;
    }

    public void setDocid(Integer docid) {
        this.docid = docid;
    }

    public void setDocname(String docname) {
        this.docname = docname;
    }

    public void setDocage(Integer docage) {
        this.docage = docage;
    }

    public void setDocsex(Integer docsex) {
        this.docsex = docsex;
    }

    @Override
    public String toString() {
        return "Doctor{" +
                "docid=" + docid +
                ", docname='" + docname + '\'' +
                ", docage=" + docage +
                ", docsex=" + docsex +
                '}';
    }
}
