package com.example.fetchapp2;

import android.util.Log;

public class ListItem {
    public Integer id;
    public Integer listId;
    public String name;
    public Integer itemNo=0;

    public ListItem(Integer id, Integer listId, String name) {
        this.id = id;
        this.listId = listId;
        this.name = name;

    }
    public ListItem(){

    }
    public void customBusLogicNumber(String name){
        if(name!=null && name.length()>0) {
            boolean flag = false;
            String number = "";
            for (Character c : name.toCharArray()) {
                if (Character.isDigit(c)) {
                    flag = true;
                    number = number + c;
                } else {
                    if (flag) break;
                } // item 67 item 57
            }
            Log.d("Itemnodebug",number);
            this.itemNo = Integer.valueOf(number);
        }

    }
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getListId() {
        return listId;
    }

    public void setListId(Integer listId) {
        this.listId = listId;
    }

    public String getName() {
        return name;
    }

    public Integer getItemNo() {
        return itemNo;
    }

    public void setItemNo(Integer itemNo) {
        this.itemNo = itemNo;
    }

    public void setName(String name) {
        this.name = name;
        customBusLogicNumber(name);
    }
    public int compareTo(ListItem li) {
        if(li.getListId()==getListId()) {
            return this.name.compareTo(li.name);
        } else
            return getListId().compareTo(li.getListId());
    }
}
