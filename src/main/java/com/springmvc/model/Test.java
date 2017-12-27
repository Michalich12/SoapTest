package com.springmvc.model;

import java.io.Serializable;

/**
 * @author Igor on 27.12.2017.
 */
public class Test implements Serializable {

    private int id;

    public Test() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
