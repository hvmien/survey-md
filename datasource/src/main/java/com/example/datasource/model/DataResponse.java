package com.example.datasource.model;

import java.util.ArrayList;

/**
 * Created by Forev on 17/04/17.
 */

public class DataResponse<T> {
    public int error;
    public String msg;

    public ArrayList<T> data;
}
