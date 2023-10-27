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
        "Address1",
        "Address2",
        "BSSetup_DeliveryOption",
        "BSSetup_HandDelivery",
        "BSSetup_PayAtStore",
        "BSSetup_PickUpStore",
        "BSSetup_UberRush",
        "City",
        "CompanyName",
        "Cust_mst_ID",
        "DefineDeliveryArea",
        "DontAcceptOrder",
        "Email",
        "EncEmail",
        "EncFirstName",
        "EncLastName",
        "Encempadmin",
        "Encstoreno",
        "FavLocation",
        "FirstName",
        "HandDeliveryAreaText",
        "HandDeliveryPrice",
        "ID",
        "IsDefault",
        "IsLoyalityRewards",
        "LastName",
        "Lock",
        "Message",
        "OTP",
        "Password",
        "Phone",
        "Phonetype",
        "Result",
        "Shipping_Id",
        "State",
        "StorePhone",
        "SurchargePrice",     //Edited by Janvi 29th sep ******* end***********
        "Type",
        "WarnCustomers",
        "WebStoreStatus",
        "Zip",
        "encpass",
        "encstoreinfo",
        "encver",
        "WebSiteURL",
        "SAddress1",
        "SAddress2",
        "SCity",
        "SState",
        "SZip",
        "LoginToCartURL",
        "AllowSurchargeDelivery"
})
public class ShippingData {

    @JsonProperty("AllowSurchargeDelivery")
    private Boolean AllowSurchargeDelivery;
    @JsonProperty("LoginToCartURL")
    private String LoginToCartURL;
    @JsonProperty("SAddress1")
    private String SAddress1;
    @JsonProperty("SAddress2")
    private String SAddress2;
    @JsonProperty("SCity")
    private String SCity;
    @JsonProperty("SState")
    private String SState;
    @JsonProperty("SZip")
    private String SZip;
    @JsonProperty("WebSiteURL")
    private String WebSiteURL;
    @JsonProperty("Address1")
    private String address1;
    @JsonProperty("Address2")
    private String address2;
    @JsonProperty("BSSetup_DeliveryOption")
    private Boolean bSSetupDeliveryOption;
    @JsonProperty("BSSetup_HandDelivery")
    private Boolean bSSetupHandDelivery;
    @JsonProperty("BSSetup_PayAtStore")
    private Boolean bSSetupPayAtStore;
    @JsonProperty("BSSetup_PickUpStore")
    private Boolean bSSetupPickUpStore;
    @JsonProperty("BSSetup_UberRush")
    private Boolean bSSetupUberRush;
    @JsonProperty("City")
    private String city;
    @JsonProperty("CompanyName")
    private String companyName;
    @JsonProperty("Cust_mst_ID")
    private String custMstID;

    @JsonProperty("DefineDeliveryArea")
    private String DefineDeliveryArea;

    @JsonProperty("DontAcceptOrder")
    private Boolean dontAcceptOrder;


    @JsonProperty("Email")
    private String email;

    @JsonProperty("EncEmail")
    private String EncEmail;
    @JsonProperty("EncFirstName")
    private String EncFirstName;
    @JsonProperty("EncLastName")
    private String EncLastName;
    @JsonProperty("Encempadmin")
    private String Encempadmin;
    @JsonProperty("Encstoreno")
    private String Encstoreno;
    @JsonProperty("FavLocation")
    private String favLocation;
    @JsonProperty("FirstName")
    private String firstName;
    @JsonProperty("HandDeliveryAreaText")
    private String handDeliveryAreaText;
    @JsonProperty("HandDeliveryPrice")
    private String handDeliveryPrice;
    @JsonProperty("ID")
    private String iD;
    @JsonProperty("IsDefault")
    private String isDefault;
    @JsonProperty("IsLoyalityRewards")
    private String isLoyalityRewards;
    @JsonProperty("LastName")
    private String lastName;
    @JsonProperty("Lock")
    private String lock;
    @JsonProperty("Message")
    private String message;
    @JsonProperty("OTP")
    private String oTP;
    @JsonProperty("Password")
    private String password;
    @JsonProperty("Phone")
    private String phone;
    @JsonProperty("Phonetype")
    private String phonetype;
    @JsonProperty("Result")
    private String result;
    @JsonProperty("Shipping_Id")
    private Long shippingId;
    @JsonProperty("State")
    private String state;
    @JsonProperty("StorePhone")
    private String storePhone;

    //Edited by Janvi 29th sep *******

    @JsonProperty("SurchargePrice")
    private String surchargePrice;

    //end **************

    @JsonProperty("Type")
    private String type;
    @JsonProperty("WarnCustomers")
    private Boolean warnCustomers;
    @JsonProperty("WebStoreStatus")
    private Long webStoreStatus;
    @JsonProperty("Zip")
    private String zip;

    @JsonProperty("encpass")
    private String encpass;
    @JsonProperty("encstoreinfo")
    private String encstoreinfo;
    @JsonProperty("encver")
    private String encver;

    @JsonIgnore
    private Map<String, String> additionalProperties = new HashMap<String, String>();

    @JsonProperty("Address1")
    public String getAddress1() {
        return (address1 == null) ? "" : address1;
    }

    @JsonProperty("Address1")
    public void setAddress1(String address1) {
        this.address1 = address1;
    }

    @JsonProperty("Address2")
    public String getAddress2() {
        return (address2 == null) ? "" : address2;
    }

    @JsonProperty("Address2")
    public void setAddress2(String address2) {
        this.address2 = address2;
    }

    @JsonProperty("BSSetup_DeliveryOption")
    public Boolean getBSSetupDeliveryOption() {
        return bSSetupDeliveryOption;
    }

    @JsonProperty("BSSetup_DeliveryOption")
    public void setBSSetupDeliveryOption(Boolean bSSetupDeliveryOption) {
        this.bSSetupDeliveryOption = bSSetupDeliveryOption;
    }

    @JsonProperty("BSSetup_HandDelivery")
    public Boolean getBSSetupHandDelivery() {
        return bSSetupHandDelivery;
    }

    @JsonProperty("BSSetup_HandDelivery")
    public void setBSSetupHandDelivery(Boolean bSSetupHandDelivery) {
        this.bSSetupHandDelivery = bSSetupHandDelivery;
    }

    @JsonProperty("BSSetup_PayAtStore")
    public Boolean getBSSetupPayAtStore() {
        return bSSetupPayAtStore;
    }

    @JsonProperty("BSSetup_PayAtStore")
    public void setBSSetupPayAtStore(Boolean bSSetupPayAtStore) {
        this.bSSetupPayAtStore = bSSetupPayAtStore;
    }

    @JsonProperty("BSSetup_PickUpStore")
    public Boolean getBSSetupPickUpStore() {
        return bSSetupPickUpStore;
    }

    @JsonProperty("BSSetup_PickUpStore")
    public void setBSSetupPickUpStore(Boolean bSSetupPickUpStore) {
        this.bSSetupPickUpStore = bSSetupPickUpStore;
    }

    @JsonProperty("BSSetup_UberRush")
    public Boolean getBSSetupUberRush() {
        return bSSetupUberRush;
    }

    @JsonProperty("BSSetup_UberRush")
    public void setBSSetupUberRush(Boolean bSSetupUberRush) {
        this.bSSetupUberRush = bSSetupUberRush;
    }

    @JsonProperty("City")
    public String getCity() {
        return (city == null) ? "" : city;
    }

    @JsonProperty("City")
    public void setCity(String city) {
        this.city = city;
    }

    @JsonProperty("CompanyName")
    public String getCompanyName() {
        return (companyName == null) ? "" : companyName;
    }

    @JsonProperty("CompanyName")
    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    @JsonProperty("Cust_mst_ID")
    public String getCustMstID() {
        return (custMstID == null) ? "" : custMstID;
    }

    @JsonProperty("Cust_mst_ID")
    public void setCustMstID(String custMstID) {
        this.custMstID = custMstID;
    }


    @JsonProperty("DefineDeliveryArea")
    public String getDefineDeliveryArea() {
        return (DefineDeliveryArea == null) ? "" : DefineDeliveryArea;
    }

    @JsonProperty("DefineDeliveryArea")
    public void setDefineDeliveryArea(String DefineDeliveryArea) {
        this.DefineDeliveryArea = DefineDeliveryArea;
    }


    @JsonProperty("DontAcceptOrder")
    public Boolean getDontAcceptOrder() {
        return dontAcceptOrder /*(dontAcceptOrder == null) ? "" : dontAcceptOrder*/;
    }

    @JsonProperty("DontAcceptOrder")
    public void setDontAcceptOrder(Boolean dontAcceptOrder) {
        this.dontAcceptOrder = dontAcceptOrder;
    }

    @JsonProperty("Email")
    public String getEmail() {
        return (email == null) ? "" : email;
    }

    @JsonProperty("Email")
    public void setEmail(String email) {
        this.email = email;
    }


    @JsonProperty("EncEmail")
    public String geEncEmail() {
        return (EncEmail == null) ? "" : EncEmail;
    }

    @JsonProperty("EncEmail")
    public void setEncEmail(String EncEmail) {
        this.EncEmail = EncEmail;
    }

    @JsonProperty("EncFirstName")
    public String getEncFirstName() {
        return (EncFirstName == null) ? "" : EncFirstName;
    }

    @JsonProperty("EncFirstName")
    public void setEncFirstName(String EncFirstName) {
        this.EncFirstName = EncFirstName;
    }

    @JsonProperty("EncLastName")
    public String getEncLastName() {
        return (EncLastName == null) ? "" : EncLastName;
    }

    @JsonProperty("EncLastName")
    public void setEncLastName(String EncLastName) {
        this.EncLastName = EncLastName;
    }

    @JsonProperty("Encempadmin")
    public String getEncempadmin() {
        return (Encempadmin == null) ? "" : Encempadmin;
    }

    @JsonProperty("Encempadmin")
    public void setEncempadmin(String Encempadmin) {
        this.Encempadmin = Encempadmin;
    }


    @JsonProperty("Encstoreno")
    public String getEncstoreno() {
        return (Encstoreno == null) ? "" : Encstoreno;
    }

    @JsonProperty("Encstoreno")
    public void setEncstoreno(String Encstoreno) {
        this.Encstoreno = Encstoreno;
    }


    @JsonProperty("FavLocation")
    public String getFavLocation() {
        return (favLocation == null) ? "" : favLocation;
    }

    @JsonProperty("FavLocation")
    public void setFavLocation(String favLocation) {
        this.favLocation = favLocation;
    }

    @JsonProperty("FirstName")
    public String getFirstName() {
        return (firstName == null) ? "" : firstName;
    }

    @JsonProperty("FirstName")
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    @JsonProperty("HandDeliveryAreaText")
    public String getHandDeliveryAreaText() {
        return (handDeliveryAreaText == null) ? "" : handDeliveryAreaText;
    }

    @JsonProperty("HandDeliveryAreaText")
    public void setHandDeliveryAreaText(String handDeliveryAreaText) {
        this.handDeliveryAreaText = handDeliveryAreaText;
    }

    @JsonProperty("HandDeliveryPrice")
    public String getHandDeliveryPrice() {
        return (handDeliveryPrice == null) ? "" : handDeliveryPrice;
    }

    @JsonProperty("HandDeliveryPrice")
    public void setHandDeliveryPrice(String handDeliveryPrice) {
        this.handDeliveryPrice = handDeliveryPrice;
    }


    @JsonProperty("ID")
    public String getID() {
        return (iD == null) ? "" : iD;
    }

    @JsonProperty("ID")
    public void setID(String iD) {
        this.iD = iD;
    }

    @JsonProperty("IsDefault")
    public String getIsDefault() {
        return (isDefault == null) ? "" : isDefault;
    }

    @JsonProperty("IsDefault")
    public void setIsDefault(String isDefault) {
        this.isDefault = isDefault;
    }

    @JsonProperty("IsLoyalityRewards")
    public String getIsLoyalityRewards() {
        return (isLoyalityRewards == null) ? "" : isLoyalityRewards;
    }

    @JsonProperty("IsLoyalityRewards")
    public void setIsLoyalityRewards(String isLoyalityRewards) {
        this.isLoyalityRewards = isLoyalityRewards;
    }

    @JsonProperty("LastName")
    public String getLastName() {
        return (lastName == null) ? "" : lastName;
    }

    @JsonProperty("LastName")
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @JsonProperty("Lock")
    public String getLock() {
        return (lock == null) ? "" : lock;
    }

    @JsonProperty("Lock")
    public void setLock(String lock) {
        this.lock = lock;
    }

    @JsonProperty("Message")
    public String getMessage() {
        return (message == null) ? "" : message;
    }

    @JsonProperty("Message")
    public void setMessage(String message) {
        this.message = message;
    }

    @JsonProperty("OTP")
    public String getOTP() {
        return (oTP == null) ? "" : oTP;
    }

    @JsonProperty("OTP")
    public void setOTP(String oTP) {
        this.oTP = oTP;
    }

    @JsonProperty("Password")
    public String getPassword() {
        return (password == null) ? "" : password;
    }

    @JsonProperty("Password")
    public void setPassword(String password) {
        this.password = password;
    }

    @JsonProperty("Phone")
    public String getPhone() {
        return (phone == null|| phone.trim().isEmpty()) ? "0000000000" : phone;
    }

    @JsonProperty("Phone")
    public void setPhone(String phone) {
        this.phone = phone;
    }

    @JsonProperty("Phonetype")
    public String getPhonetype() {
        return (phonetype == null) ? "" : phonetype;
    }

    @JsonProperty("Phonetype")
    public void setPhonetype(String phonetype) {
        this.phonetype = phonetype;
    }

    @JsonProperty("Result")
    public String getResult() {
        return (result == null) ? "" : result;
    }

    @JsonProperty("Result")
    public void setResult(String result) {
        this.result = result;
    }

    @JsonProperty("Shipping_Id")
    public Long getShippingId() {
        return shippingId;
    }

    @JsonProperty("Shipping_Id")
    public void setShippingId(Long shippingId) {
        this.shippingId = shippingId;
    }

    @JsonProperty("State")
    public String getState() {
        return (state == null) ? "" : state;
    }

    @JsonProperty("State")
    public void setState(String state) {
        this.state = state;
    }

    @JsonProperty("StorePhone")
    public String getStorePhone() {
        return (storePhone == null) ? "" : storePhone;
    }

    @JsonProperty("StorePhone")
    public void setStorePhone(String storePhone) {
        this.storePhone = storePhone;
    }

    //Edited by Janvi 29th sep *******

    @JsonProperty("SurchargePrice")
    public String getSurchargePrice() {
        return surchargePrice == null ? "" : surchargePrice;
    }

    @JsonProperty("SurchargePrice")
    public void setSurchargePrice(String surchargePrice) {
        this.surchargePrice = surchargePrice;
    }

    //end **************

    @JsonProperty("Type")
    public String getType() {
        return (type == null) ? "" : type;
    }

    @JsonProperty("Type")
    public void setType(String type) {
        this.type = type;
    }

    @JsonProperty("WarnCustomers")
    public Boolean getWarnCustomers() {
        return warnCustomers;
    }

    @JsonProperty("WarnCustomers")
    public void setWarnCustomers(Boolean warnCustomers) {
        this.warnCustomers = warnCustomers;
    }

    @JsonProperty("WebStoreStatus")
    public Long getWebStoreStatus() {
        return webStoreStatus;
    }

    @JsonProperty("WebStoreStatus")
    public void setWebStoreStatus(Long webStoreStatus) {
        this.webStoreStatus = webStoreStatus;
    }

    @JsonProperty("Zip")
    public String getZip() {
        return (zip == null) ? "" : zip;
    }

    @JsonProperty("Zip")
    public void setZip(String zip) {
        this.zip = zip;
    }

    @JsonProperty("encpass")
    public String getencpass() {
        return (encpass == null) ? "" : encpass;
    }

    @JsonProperty("encpass")
    public void setencpass(String encpass) {
        this.encpass = encpass;
    }

    @JsonProperty("encstoreinfo")
    public String getencstoreinfo() {
        return (encstoreinfo == null) ? "" : encstoreinfo;
    }

    @JsonProperty("encstoreinfo")
    public void setencstoreinfo(String encstoreinfo) {
        this.encstoreinfo = encstoreinfo;
    }

    @JsonProperty("encver")
    public String getencver() {
        return (encver == null) ? "" : encver;
    }

    @JsonProperty("encver")
    public void setencver(String encver) {
        this.encver = encver;
    }

    @JsonAnyGetter
    public Map<String, String> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, String value) {
        this.additionalProperties.put(name, value);
    }

    public String getWebSiteURL() {
        return WebSiteURL;
    }

    public void setWebSiteURL(String webSiteURL) {
        WebSiteURL = webSiteURL;
    }

    public String getSAddress1() {
        return SAddress1;
    }

    public void setSAddress1(String SAddress1) {
        this.SAddress1 = SAddress1;
    }

    public String getSAddress2() {
        return SAddress2;
    }

    public void setSAddress2(String SAddress2) {
        this.SAddress2 = SAddress2;
    }

    public String getSCity() {
        return SCity;
    }

    public void setSCity(String SCity) {
        this.SCity = SCity;
    }

    public String getSState() {
        return SState;
    }

    public void setSState(String SState) {
        this.SState = SState;
    }

    public String getSZip() {
        return SZip;
    }

    public void setSZip(String SZip) {
        this.SZip = SZip;
    }

    public String getLoginToCartURL() {
        return LoginToCartURL;
    }

    public void setLoginToCartURL(String loginToCartURL) {
        LoginToCartURL = loginToCartURL;
    }

    public Boolean getAllowSurchargeDelivery() {
        return AllowSurchargeDelivery;
    }

    public void setAllowSurchargeDelivery(Boolean allowSurchargeDelivery) {
        AllowSurchargeDelivery = allowSurchargeDelivery;
    }
}