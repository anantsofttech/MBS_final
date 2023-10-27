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
        "BAddress1",
        "BAddress2",
        "BCity",
        "BCompanyName",
        "BEmail",
        "BFName",
        "BLName",
        "BPhone",
        "BPhoneType",
        "BState",
        "BZip",
        "CustomerID",
        "IsDeliverHome",
        "IsHandDelivery",
        "IsPickupStore",
        "IsSame",
        "IsUberRush",
        "PaymentType",
        "Result",
        "SAddress1",
        "SAddress2",
        "SCity",
        "SCompanyName",
        "SFName",
        "SLName",
        "SPhone",
        "SPhoneType",
        "SState",
        "SZip",
        "StoreNo",
        "TempOrderId",
        "TipNoCCValue",
        "TipSubTotal",
        "TipValue"
})
public class OrderDetails {

    @JsonProperty("BAddress1")
    private String bAddress1;
    @JsonProperty("BAddress2")
    private String bAddress2;
    @JsonProperty("BCity")
    private String bCity;
    @JsonProperty("BCompanyName")
    private String bCompanyName;
    @JsonProperty("BEmail")
    private String bEmail;
    @JsonProperty("BFName")
    private String bFName;
    @JsonProperty("BLName")
    private String bLName;
    @JsonProperty("BPhone")
    private String bPhone;
    @JsonProperty("BPhoneType")
    private String bPhoneType;
    @JsonProperty("BState")
    private String bState;
    @JsonProperty("BZip")
    private String bZip;
    @JsonProperty("CustomerID")
    private String customerID;
    @JsonProperty("IsDeliverHome")
    private String isDeliverHome;
    @JsonProperty("IsHandDelivery")
    private String isHandDelivery;
    @JsonProperty("IsPickupStore")
    private String isPickupStore;
    @JsonProperty("IsSame")
    private Boolean isSame;
    @JsonProperty("IsUberRush")
    private String isUberRush;
    @JsonProperty("PaymentType")
    private String paymentType;
    @JsonProperty("Result")
    private String result;
    @JsonProperty("SAddress1")
    private String sAddress1;
    @JsonProperty("SAddress2")
    private String sAddress2;
    @JsonProperty("SCity")
    private String sCity;
    @JsonProperty("SCompanyName")
    private String sCompanyName;
    @JsonProperty("SFName")
    private String sFName;
    @JsonProperty("SLName")
    private String sLName;
    @JsonProperty("SPhone")
    private String sPhone;
    @JsonProperty("SPhoneType")
    private String sPhoneType;
    @JsonProperty("SState")
    private String sState;
    @JsonProperty("SZip")
    private String sZip;
    @JsonProperty("StoreNo")
    private String storeNo;
    @JsonProperty("TempOrderId")
    private Long tempOrderId;
    @JsonProperty("TipNoCCValue")
    private Boolean tipNoCCValue;
    @JsonProperty("TipSubTotal")
    private String tipSubTotal;
    @JsonProperty("TipValue")
    private String tipValue;
    @JsonIgnore
    private Map<String, String> additionalProperties = new HashMap<String, String>();

    @JsonProperty("BAddress1")
    public String getBAddress1() {
        return (bAddress1 == null) ? "" : bAddress1;
    }

    @JsonProperty("BAddress1")
    public void setBAddress1(String bAddress1) {
        this.bAddress1 = bAddress1;
    }

    @JsonProperty("BAddress2")
    public String getBAddress2() {
        return (bAddress2 == null) ? "" : bAddress2;
    }

    @JsonProperty("BAddress2")
    public void setBAddress2(String bAddress2) {
        this.bAddress2 = bAddress2;
    }

    @JsonProperty("BCity")
    public String getBCity() {
        return (bCity == null) ? "" : bCity;
    }

    @JsonProperty("BCity")
    public void setBCity(String bCity) {
        this.bCity = bCity;
    }

    @JsonProperty("BCompanyName")
    public String getBCompanyName() {
        return (bCompanyName == null) ? "" : bCompanyName;
    }

    @JsonProperty("BCompanyName")
    public void setBCompanyName(String bCompanyName) {
        this.bCompanyName = bCompanyName;
    }

    @JsonProperty("BEmail")
    public String getBEmail() {
        return (bEmail == null) ? "" : bEmail;
    }

    @JsonProperty("BEmail")
    public void setBEmail(String bEmail) {
        this.bEmail = bEmail;
    }

    @JsonProperty("BFName")
    public String getBFName() {
        return (bFName == null) ? "" : bFName;
    }

    @JsonProperty("BFName")
    public void setBFName(String bFName) {
        this.bFName = bFName;
    }

    @JsonProperty("BLName")
    public String getBLName() {
        return (bLName == null) ? "" : bLName;
    }

    @JsonProperty("BLName")
    public void setBLName(String bLName) {
        this.bLName = bLName;
    }

    @JsonProperty("BPhone")
    public String getBPhone() {
        return (bPhone == null) ? "" : bPhone;
    }

    @JsonProperty("BPhone")
    public void setBPhone(String bPhone) {
        this.bPhone = bPhone;
    }

    @JsonProperty("BPhoneType")
    public String getBPhoneType() {
        return (bPhoneType == null) ? "" : bPhoneType;
    }

    @JsonProperty("BPhoneType")
    public void setBPhoneType(String bPhoneType) {
        this.bPhoneType = bPhoneType;
    }

    @JsonProperty("BState")
    public String getBState() {
        return (bState == null) ? "" : bState;
    }

    @JsonProperty("BState")
    public void setBState(String bState) {
        this.bState = bState;
    }

    @JsonProperty("BZip")
    public String getBZip() {
        return (bZip == null) ? "" : bZip;
    }

    @JsonProperty("BZip")
    public void setBZip(String bZip) {
        this.bZip = bZip;
    }

    @JsonProperty("CustomerID")
    public String getCustomerID() {
        return (customerID == null) ? "" : customerID;
    }

    @JsonProperty("CustomerID")
    public void setCustomerID(String customerID) {
        this.customerID = customerID;
    }

    @JsonProperty("IsDeliverHome")
    public String getIsDeliverHome() {
        return (isDeliverHome == null) ? "" : isDeliverHome;
    }

    @JsonProperty("IsDeliverHome")
    public void setIsDeliverHome(String isDeliverHome) {
        this.isDeliverHome = isDeliverHome;
    }

    @JsonProperty("IsHandDelivery")
    public String getIsHandDelivery() {
        return (isHandDelivery == null) ? "" : isHandDelivery;
    }

    @JsonProperty("IsHandDelivery")
    public void setIsHandDelivery(String isHandDelivery) {
        this.isHandDelivery = isHandDelivery;
    }

    @JsonProperty("IsPickupStore")
    public String getIsPickupStore() {
        return (isPickupStore == null) ? "" : isPickupStore;
    }

    @JsonProperty("IsPickupStore")
    public void setIsPickupStore(String isPickupStore) {
        this.isPickupStore = isPickupStore;
    }

    @JsonProperty("IsSame")
    public Boolean getIsSame() {
        return isSame;
    }

    @JsonProperty("IsSame")
    public void setIsSame(Boolean isSame) {
        this.isSame = isSame;
    }

    @JsonProperty("IsUberRush")
    public String getIsUberRush() {
        return (isUberRush == null) ? "" : isUberRush;
    }

    @JsonProperty("IsUberRush")
    public void setIsUberRush(String isUberRush) {
        this.isUberRush = isUberRush;
    }

    @JsonProperty("PaymentType")
    public String getPaymentType() {
        return (paymentType == null) ? "" : paymentType;
    }

    @JsonProperty("PaymentType")
    public void setPaymentType(String paymentType) {
        this.paymentType = paymentType;
    }

    @JsonProperty("Result")
    public String getResult() {
        return (result == null) ? "" : result;
    }

    @JsonProperty("Result")
    public void setResult(String result) {
        this.result = result;
    }

    @JsonProperty("SAddress1")
    public String getSAddress1() {
        return (sAddress1 == null) ? "" : sAddress1;
    }

    @JsonProperty("SAddress1")
    public void setSAddress1(String sAddress1) {
        this.sAddress1 = sAddress1;
    }

    @JsonProperty("SAddress2")
    public String getSAddress2() {
        return (sAddress2 == null) ? "" : sAddress2;
    }

    @JsonProperty("SAddress2")
    public void setSAddress2(String sAddress2) {
        this.sAddress2 = sAddress2;
    }

    @JsonProperty("SCity")
    public String getSCity() {
        return (sCity == null) ? "" : sCity;
    }

    @JsonProperty("SCity")
    public void setSCity(String sCity) {
        this.sCity = sCity;
    }

    @JsonProperty("SCompanyName")
    public String getSCompanyName() {
        return (sCompanyName == null) ? "" : sCompanyName;
    }

    @JsonProperty("SCompanyName")
    public void setSCompanyName(String sCompanyName) {
        this.sCompanyName = sCompanyName;
    }

    @JsonProperty("SFName")
    public String getSFName() {
        return (sFName == null) ? "" : sFName;
    }

    @JsonProperty("SFName")
    public void setSFName(String sFName) {
        this.sFName = sFName;
    }

    @JsonProperty("SLName")
    public String getSLName() {
        return (sLName == null) ? "" : sLName;
    }

    @JsonProperty("SLName")
    public void setSLName(String sLName) {
        this.sLName = sLName;
    }

    @JsonProperty("SPhone")
    public String getSPhone() {
        return (sPhone == null) ? "" : sPhone;
    }

    @JsonProperty("SPhone")
    public void setSPhone(String sPhone) {
        this.sPhone = sPhone;
    }

    @JsonProperty("SPhoneType")
    public String getSPhoneType() {
        return (sPhoneType == null) ? "" : sPhoneType;
    }

    @JsonProperty("SPhoneType")
    public void setSPhoneType(String sPhoneType) {
        this.sPhoneType = sPhoneType;
    }

    @JsonProperty("SState")
    public String getSState() {
        return (sState == null) ? "" : sState;
    }

    @JsonProperty("SState")
    public void setSState(String sState) {
        this.sState = sState;
    }

    @JsonProperty("SZip")
    public String getSZip() {
        return (sZip == null) ? "" : sZip;
    }

    @JsonProperty("SZip")
    public void setSZip(String sZip) {
        this.sZip = sZip;
    }

    @JsonProperty("StoreNo")
    public String getStoreNo() {
        return (storeNo == null) ? "" : storeNo;
    }

    @JsonProperty("StoreNo")
    public void setStoreNo(String storeNo) {
        this.storeNo = storeNo;
    }

    @JsonProperty("TempOrderId")
    public Long getTempOrderId() {
        return tempOrderId;
    }

    @JsonProperty("TempOrderId")
    public void setTempOrderId(Long tempOrderId) {
        this.tempOrderId = tempOrderId;
    }

    @JsonProperty("TipNoCCValue")
    public Boolean getTipNoCCValue() {
        return tipNoCCValue;
    }

    @JsonProperty("TipNoCCValue")
    public void setTipNoCCValue(Boolean tipNoCCValue) {
        this.tipNoCCValue = tipNoCCValue;
    }

    @JsonProperty("TipSubTotal")
    public String getTipSubTotal() {
        return (tipSubTotal == null) ? "" : tipSubTotal;
    }

    @JsonProperty("TipSubTotal")
    public void setTipSubTotal(String tipSubTotal) {
        this.tipSubTotal = tipSubTotal;
    }

    @JsonProperty("TipValue")
    public String getTipValue() {
        return (tipValue == null) ? "" : tipValue;
    }

    @JsonProperty("TipValue")
    public void setTipValue(String tipValue) {
        this.tipValue = tipValue;
    }

    @JsonAnyGetter
    public Map<String, String> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, String value) {
        this.additionalProperties.put(name, value);
    }

}