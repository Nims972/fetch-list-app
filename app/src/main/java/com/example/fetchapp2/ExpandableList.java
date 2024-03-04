package com.example.fetchapp2;

import java.util.ArrayList;
import java.util.List;

public class ExpandableList {

    public String string;
    public final List<String> children = new ArrayList<String>();

    public ExpandableList(String string) {
        this.string = string;
    }

}