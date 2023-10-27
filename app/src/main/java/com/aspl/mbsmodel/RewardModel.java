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
        "AwardDollar",
        "AwardPoint",
        "BSPoints",
        "CustomerName",
        "LastPurchaseDate",
        "LoyaltyCard",
        "LoyaltyCardNo",
        "LoyaltyType",
        "Message",
        "PhoneNo",
        "PhoneType",
        "Points",
        "ProgramingRule",
        "Rebate",
        "pointBasis"
})
public class RewardModel {

    @JsonProperty("AwardDollar")
    private String awardDollar;
    @JsonProperty("AwardPoint")
    private String awardPoint;
    @JsonProperty("BSPoints")
    private String bSPoints;
    @JsonProperty("CustomerName")
    private String customerName;
    @JsonProperty("LastPurchaseDate")
    private String lastPurchaseDate;
    @JsonProperty("LoyaltyCard")
    private String loyaltyCard;
    @JsonProperty("LoyaltyCardNo")
    private String loyaltyCardNo;
    @JsonProperty("LoyaltyType")
    private String loyaltyType;
    @JsonProperty("Message")
    private Object message;
    @JsonProperty("PhoneNo")
    private String phoneNo;
    @JsonProperty("PhoneType")
    private String phoneType;
    @JsonProperty("Points")
    private String points;
    @JsonProperty("ProgramingRule")
    private String programingRule;
    @JsonProperty("Rebate")
    private String rebate;
    @JsonProperty("pointBasis")
    private String pointBasis;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("AwardDollar")
    public String getAwardDollar() {
        return awardDollar;
    }

    @JsonProperty("AwardDollar")
    public void setAwardDollar(String awardDollar) {
        this.awardDollar = awardDollar;
    }

    @JsonProperty("AwardPoint")
    public String getAwardPoint() {
        return awardPoint;
    }

    @JsonProperty("AwardPoint")
    public void setAwardPoint(String awardPoint) {
        this.awardPoint = awardPoint;
    }

    @JsonProperty("BSPoints")
    public String getBSPoints() {
        return bSPoints;
    }

    @JsonProperty("BSPoints")
    public void setBSPoints(String bSPoints) {
        this.bSPoints = bSPoints;
    }

    @JsonProperty("CustomerName")
    public String getCustomerName() {
        return customerName;
    }

    @JsonProperty("CustomerName")
    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    @JsonProperty("LastPurchaseDate")
    public String getLastPurchaseDate() {
        return lastPurchaseDate;
    }

    @JsonProperty("LastPurchaseDate")
    public void setLastPurchaseDate(String lastPurchaseDate) {
        this.lastPurchaseDate = lastPurchaseDate;
    }

    @JsonProperty("LoyaltyCard")
    public String getLoyaltyCard() {
        return loyaltyCard;
    }

    @JsonProperty("LoyaltyCard")
    public void setLoyaltyCard(String loyaltyCard) {
        this.loyaltyCard = loyaltyCard;
    }

    @JsonProperty("LoyaltyCardNo")
    public String getLoyaltyCardNo() {
        return loyaltyCardNo;
    }

    @JsonProperty("LoyaltyCardNo")
    public void setLoyaltyCardNo(String loyaltyCardNo) {
        this.loyaltyCardNo = loyaltyCardNo;
    }

    @JsonProperty("LoyaltyType")
    public String getLoyaltyType() {
        return loyaltyType;
    }

    @JsonProperty("LoyaltyType")
    public void setLoyaltyType(String loyaltyType) {
        this.loyaltyType = loyaltyType;
    }

    @JsonProperty("Message")
    public Object getMessage() {
        return message;
    }

    @JsonProperty("Message")
    public void setMessage(Object message) {
        this.message = message;
    }

    @JsonProperty("PhoneNo")
    public String getPhoneNo() {
        return phoneNo;
    }

    @JsonProperty("PhoneNo")
    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }

    @JsonProperty("PhoneType")
    public String getPhoneType() {
        return phoneType;
    }

    @JsonProperty("PhoneType")
    public void setPhoneType(String phoneType) {
        this.phoneType = phoneType;
    }

    @JsonProperty("Points")
    public String getPoints() {
        return points;
    }

    @JsonProperty("Points")
    public void setPoints(String points) {
        this.points = points;
    }

    @JsonProperty("ProgramingRule")
    public String getProgramingRule() {
        return programingRule;
    }

    @JsonProperty("ProgramingRule")
    public void setProgramingRule(String programingRule) {
        this.programingRule = programingRule;
    }

    @JsonProperty("Rebate")
    public String getRebate() {
        return rebate;
    }

    @JsonProperty("Rebate")
    public void setRebate(String rebate) {
        this.rebate = rebate;
    }

    @JsonProperty("pointBasis")
    public String getPointBasis() {
        return pointBasis;
    }

    @JsonProperty("pointBasis")
    public void setPointBasis(String pointBasis) {
        this.pointBasis = pointBasis;
    }

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}