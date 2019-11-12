package com.jk.model;

import java.io.Serializable;
import java.util.Date;

public class Car implements Serializable {
    private static final long serialVersionUID = -1939880594081437076L;
    private Integer carid;

    private String carname;

    private Integer carprice;

    private Integer cartypeid;

    private Integer carsales;

    private String cartime;



    public Integer getCarid() {
        return carid;
    }

    public String getCarname() {
        return carname;
    }

    public Integer getCarprice() {
        return carprice;
    }

    public Integer getCartypeid() {
        return cartypeid;
    }

    public Integer getCarsales() {
        return carsales;
    }

    public String getCartime() {
        return cartime;
    }

    public void setCarid(Integer carid) {
        this.carid = carid;
    }

    public void setCarname(String carname) {
        this.carname = carname;
    }

    public void setCarprice(Integer carprice) {
        this.carprice = carprice;
    }

    public void setCartypeid(Integer cartypeid) {
        this.cartypeid = cartypeid;
    }

    public void setCarsales(Integer carsales) {
        this.carsales = carsales;
    }

    public void setCartime(String cartime) {
        this.cartime = cartime;
    }

    @Override
    public String toString() {
        return "Car{" +
                "carid=" + carid +
                ", carname='" + carname + '\'' +
                ", carprice=" + carprice +
                ", cartypeid=" + cartypeid +
                ", carsales=" + carsales +
                ", cartime=" + cartime +
                '}';
    }
}