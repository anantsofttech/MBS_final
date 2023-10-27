package com.aspl.mbsmodel;

import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "AgeValidMessage",
        "AllowPickUpTime",
        "BSSetup_AllowTip",
        "BSSetup_Tip1",
        "BSSetup_Tip2",
        "BSSetup_Tip3",
        "BSSetup_Tip4",
        "BSSetup_Tip5",
        "CVVBypass",
        "CustAgeValidOption",
        "DisplayStoreHours",
        "GoogleAnalyticCode",
        "invRatings",
        "objGlobal",
        "storeno",
        "IsEnableGuestCheckout"
})
public class TwentyOneYear {

    @JsonProperty("IsEnableGuestCheckout")
    private Boolean IsEnableGuestCheckout;
    @JsonProperty("AgeValidMessage")
    private String ageValidMessage;
    @JsonProperty("AllowPickUpTime")
    private Boolean allowPickupTime;
    @JsonProperty("BSSetup_AllowTip")
    private Boolean bSSetupAllowTip;
    @JsonProperty("BSSetup_Tip1")
    private Boolean bSSetupTip1;
    @JsonProperty("BSSetup_Tip2")
    private Boolean bSSetupTip2;
    @JsonProperty("BSSetup_Tip3")
    private Boolean bSSetupTip3;
    @JsonProperty("BSSetup_Tip4")
    private Boolean bSSetupTip4;
    //Edited by Janvi 26th Oct**********
    @JsonProperty("BSSetup_Tip5")
    private Boolean bSSetupTip5;
    //end
    @JsonProperty("CVVBypass")
    private Boolean CVVBypass;
    @JsonProperty("CustAgeValidOption")
    private Integer custAgeValidOption;
    @JsonProperty("DisplayStoreHours")
    private Boolean displayStoreHours;
    @JsonProperty("GoogleAnalyticCode")
    private String googleAnalyticCode;
    @JsonProperty("invRatings")
    private Boolean invRatings;
    @JsonProperty("objGlobal")
    private Object objGlobal;
    @JsonProperty("storeno")
    private String storeno;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("AgeValidMessage")
    public String getAgeValidMessage() {
        return ageValidMessage;
    }

    @JsonProperty("AgeValidMessage")
    public void setAgeValidMessage(String ageValidMessage) {
        this.ageValidMessage = ageValidMessage;
    }

    @JsonProperty("AllowPickUpTime")
    public Boolean getAllowPickUpTime() {
        return allowPickupTime;
    }

    @JsonProperty("AllowPickUpTime")
    public void setAllowPickUpTime(Boolean allowPickupTime) {
        this.allowPickupTime = allowPickupTime;
    }

    @JsonProperty("BSSetup_AllowTip")
    public Boolean getBSSetupAllowTip() {
        return bSSetupAllowTip;
    }

    @JsonProperty("BSSetup_AllowTip")
    public void setBSSetupAllowTip(Boolean bSSetupAllowTip) {
        this.bSSetupAllowTip = bSSetupAllowTip;
    }

    @JsonProperty("BSSetup_Tip1")
    public Boolean getBSSetupTip1() {
        return bSSetupTip1;
    }

    @JsonProperty("BSSetup_Tip1")
    public void setBSSetupTip1(Boolean bSSetupTip1) {
        this.bSSetupTip1 = bSSetupTip1;
    }

    @JsonProperty("BSSetup_Tip2")
    public Boolean getBSSetupTip2() {
        return bSSetupTip2;
    }

    @JsonProperty("BSSetup_Tip2")
    public void setBSSetupTip2(Boolean bSSetupTip2) {
        this.bSSetupTip2 = bSSetupTip2;
    }

    @JsonProperty("BSSetup_Tip3")
    public Boolean getBSSetupTip3() {
        return bSSetupTip3;
    }

    @JsonProperty("BSSetup_Tip3")
    public void setBSSetupTip3(Boolean bSSetupTip3) {
        this.bSSetupTip3 = bSSetupTip3;
    }

    @JsonProperty("BSSetup_Tip4")
    public Boolean getBSSetupTip4() {
        return bSSetupTip4;
    }

    @JsonProperty("BSSetup_Tip4")
    public void setBSSetupTip4(Boolean bSSetupTip4) {
        this.bSSetupTip4 = bSSetupTip4;
    }

    //Edited by janvi 26th OCt******

    @JsonProperty("BSSetup_Tip5")
    public Boolean getBSSetupTip5() {
        return bSSetupTip5;
    }

    @JsonProperty("BSSetup_Tip5")
    public void setBSSetupTip5(Boolean bSSetupTip5) {
        this.bSSetupTip5 = bSSetupTip5;
    }

    //end*********

    @JsonProperty("CVVBypass")
    public Boolean getCVVBypass() {
        return CVVBypass;
    }

    @JsonProperty("CVVBypass")
    public void setCVVBypass(Boolean CVVBypass) {
        this.CVVBypass = CVVBypass;
    }

    @JsonProperty("CustAgeValidOption")
    public Integer getCustAgeValidOption() {
        return custAgeValidOption;
    }

    @JsonProperty("CustAgeValidOption")
    public void setCustAgeValidOption(Integer custAgeValidOption) {
        this.custAgeValidOption = custAgeValidOption;
    }

    @JsonProperty("DisplayStoreHours")
    public Boolean getDisplayStoreHours() {
        return displayStoreHours;
    }

    @JsonProperty("DisplayStoreHours")
    public void setDisplayStoreHours(Boolean displayStoreHours) {
        this.displayStoreHours = displayStoreHours;
    }

    @JsonProperty("GoogleAnalyticCode")
    public String getGoogleAnalyticCode() {
        return googleAnalyticCode;
    }

    @JsonProperty("GoogleAnalyticCode")
    public void setGoogleAnalyticCode(String googleAnalyticCode) {
        this.googleAnalyticCode = googleAnalyticCode;
    }

    @JsonProperty("invRatings")
    public Boolean getInvRatings() {
        return invRatings;
    }

    @JsonProperty("invRatings")
    public void setInvRatings(Boolean invRatings) {
        this.invRatings = invRatings;
    }

    @JsonProperty("objGlobal")
    public Object getObjGlobal() {
        return objGlobal;
    }

    @JsonProperty("objGlobal")
    public void setObjGlobal(Object objGlobal) {
        this.objGlobal = objGlobal;
    }

    @JsonProperty("storeno")
    public String getStoreno() {
        return storeno;
    }

    @JsonProperty("storeno")
    public void setStoreno(String storeno) {
        this.storeno = storeno;
    }

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

    public Boolean getEnableGuestCheckout() {
        return IsEnableGuestCheckout;
    }

    public void setEnableGuestCheckout(Boolean enableGuestCheckout) {
        IsEnableGuestCheckout = enableGuestCheckout;
    }
}