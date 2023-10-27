package com.aspl.mbsmodel;

public class CardType {

    private String name;
    private String length;
    private String prefixes;
    private Boolean checkDigit;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLength() {
        return length;
    }

    public void setLength(String length) {
        this.length = length;
    }

    public String getPrefixes() {
        return prefixes;
    }

    public void setPrefixes(String prefixes) {
        this.prefixes = prefixes;
    }

    public Boolean getCheckDigit() {
        return checkDigit;
    }

    public void setCheckDigit(Boolean checkDigit) {
        this.checkDigit = checkDigit;
    }
}