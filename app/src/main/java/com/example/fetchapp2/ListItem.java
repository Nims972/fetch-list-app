package com.example.fetchapp2;

public class ListItem {
    public Integer id;
    public Integer listId;
    public String name;

    public ListItem(Integer id, Integer listId, String name) {
        this.id = id;
        this.listId = listId;
        this.name = name;
    }
    public ListItem(){

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

    public void setName(String name) {
        this.name = name;
    }
    public int compareTo(ListItem li) {
        if(li.getListId()==getListId()) {
            return this.name.compareTo(li.name);
        } else
            return getListId().compareTo(li.getListId());
    }
}
