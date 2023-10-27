package com.aspl.mbsmodel;

/**
 * Created by admin on 12/1/2017.
 */

public class TestModel {
    private String image;
    private String name;
    private String prise;
    private String quantity;

    public TestModel (){}

    public TestModel(String image, String name, String prise, String quantity) {
        this.image = image;
        this.name = name;
        this.prise = prise;
        this.quantity = quantity;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrise() {
        return prise;
    }

    public void setPrise(String prise) {
        this.prise = prise;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }
}
