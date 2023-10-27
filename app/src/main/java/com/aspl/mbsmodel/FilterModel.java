package com.aspl.mbsmodel;

/**
 * Created by new on 07/17/2017.
 */
public class FilterModel {
    public String Name;
    public boolean isActive=false;

    public FilterModel(String category){
        this.Name=category;
    }

    public FilterModel(boolean isActive){
        this.isActive=isActive;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }
}
