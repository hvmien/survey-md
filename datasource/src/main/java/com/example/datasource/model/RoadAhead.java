package com.example.datasource.model;

/**
 * Created by Forev on 17/04/21.
 */

public class RoadAhead {
    public int id;
    public String name;
    public boolean select = false;

    public RoadAhead(int id, String name, boolean select) {
        this.id = id;
        this.name = name;
        this.select = select;
    }
}
