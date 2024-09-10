package com.aspl.mbsmodel;

import com.fasterxml.jackson.annotation.JsonProperty;

public class GiftCardSettingsModel {

    @JsonProperty("AllowGiftCard")
    private boolean allowGiftCard;

    @JsonProperty("EmailGC")
    private boolean emailGC;

    @JsonProperty("GCImage1")
    private String gcImage1;

    @JsonProperty("GCImage2")
    private String gcImage2;

    @JsonProperty("GCImage3")
    private String gcImage3;

    @JsonProperty("GCImage4")
    private String gcImage4;

    @JsonProperty("GCImage5")
    private String gcImage5;

    @JsonProperty("GCImage6")
    private String gcImage6;

    @JsonProperty("GCImage7")
    private String gcImage7;

    @JsonProperty("GCImage8")
    private String gcImage8;

    @JsonProperty("GCImage9")
    private String gcImage9;

    @JsonProperty("ID")
    private String id;

    @JsonProperty("IsGCDropdown")
    private boolean isGCDropdown;

    @JsonProperty("MaxGCSale")
    private String maxGCSale;

    @JsonProperty("Message")
    private String message;

    @JsonProperty("MinGCSale")
    private String minGCSale;

    @JsonProperty("PhysicalGC")
    private boolean physicalGC;

    @JsonProperty("StoreNo")
    private String storeNo;

    @JsonProperty("TextGC")
    private boolean textGC;

    // Getters and Setters

    public boolean isAllowGiftCard() {
        return allowGiftCard;
    }

    public void setAllowGiftCard(boolean allowGiftCard) {
        this.allowGiftCard = allowGiftCard;
    }

    public boolean isEmailGC() {
        return emailGC;
    }

    public void setEmailGC(boolean emailGC) {
        this.emailGC = emailGC;
    }

    public String getGcImage1() {
        return gcImage1;
    }

    public void setGcImage1(String gcImage1) {
        this.gcImage1 = gcImage1;
    }

    public String getGcImage2() {
        return gcImage2;
    }

    public void setGcImage2(String gcImage2) {
        this.gcImage2 = gcImage2;
    }

    public String getGcImage3() {
        return gcImage3;
    }

    public void setGcImage3(String gcImage3) {
        this.gcImage3 = gcImage3;
    }

    public String getGcImage4() {
        return gcImage4;
    }

    public void setGcImage4(String gcImage4) {
        this.gcImage4 = gcImage4;
    }

    public String getGcImage5() {
        return gcImage5;
    }

    public void setGcImage5(String gcImage5) {
        this.gcImage5 = gcImage5;
    }

    public String getGcImage6() {
        return gcImage6;
    }

    public void setGcImage6(String gcImage6) {
        this.gcImage6 = gcImage6;
    }

    public String getGcImage7() {
        return gcImage7;
    }

    public void setGcImage7(String gcImage7) {
        this.gcImage7 = gcImage7;
    }

    public String getGcImage8() {
        return gcImage8;
    }

    public void setGcImage8(String gcImage8) {
        this.gcImage8 = gcImage8;
    }

    public String getGcImage9() {
        return gcImage9;
    }

    public void setGcImage9(String gcImage9) {
        this.gcImage9 = gcImage9;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public boolean isGCDropdown() {
        return isGCDropdown;
    }

    public void setGCDropdown(boolean isGCDropdown) {
        this.isGCDropdown = isGCDropdown;
    }

    public String getMaxGCSale() {
        return maxGCSale;
    }

    public void setMaxGCSale(String maxGCSale) {
        this.maxGCSale = maxGCSale;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getMinGCSale() {
        return minGCSale;
    }

    public void setMinGCSale(String minGCSale) {
        this.minGCSale = minGCSale;
    }

    public boolean isPhysicalGC() {
        return physicalGC;
    }

    public void setPhysicalGC(boolean physicalGC) {
        this.physicalGC = physicalGC;
    }

    public String getStoreNo() {
        return storeNo;
    }

    public void setStoreNo(String storeNo) {
        this.storeNo = storeNo;
    }

    public boolean isTextGC() {
        return textGC;
    }

    public void setTextGC(boolean textGC) {
        this.textGC = textGC;
    }
}
