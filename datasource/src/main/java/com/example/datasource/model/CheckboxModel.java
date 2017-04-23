package com.example.datasource.model;

/**
 * Created by HVM on 4/23/2017.
 */

public class CheckboxModel {
    public int id;
    public String name;
    public boolean select = false;

    public CheckboxModel(int id, String name, boolean select) {
        this.id = id;
        this.name = name;
        this.select = select;
    }
}
