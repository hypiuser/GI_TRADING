package com.lsjgitrading.entity;

import java.io.Serializable;

public class Circles implements Serializable {
    private static final long serialVersionUID = 1L;
    private Integer id;
    private String img;
    private String name;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}