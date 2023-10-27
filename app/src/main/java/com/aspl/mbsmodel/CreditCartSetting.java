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
        "CardType",
        "City",
        "ClientID",
        "CompanyName",
        "CreditCardNo",
        "Cust_mst_ID",
        "Email",
        "Expiration",
        "FirstName",
        "LastName",
        "MerchantCTRStatus",
        "MerchantCode",
        "MerchantContractID",
        "MerchantCustID",
        "Password",
        "Phone",
        "PrimaryURL",
        "Result",
        "SrNo",
        "State",
        "UserName",
        "Zip"
})
public class CreditCartSetting {

    @JsonProperty("Address1")
    private String address1;
    @JsonProperty("Address2")
    private String address2;
    @JsonProperty("CardType")
    private String cardType;
    @JsonProperty("City")
    private String city;
    @JsonProperty("ClientID")
    private String clientID;
    @JsonProperty("CompanyName")
    private String companyName;
    @JsonProperty("CreditCardNo")
    private String creditCardNo;
    @JsonProperty("Cust_mst_ID")
    private String custMstID;
    @JsonProperty("Email")
    private String email;
    @JsonProperty("Expiration")
    private String expiration;
    @JsonProperty("FirstName")
    private String firstName;
    @JsonProperty("LastName")
    private String lastName;
    @JsonProperty("MerchantCTRStatus")
    private String merchantCTRStatus;
    @JsonProperty("MerchantCode")
    private String merchantCode;
    @JsonProperty("MerchantContractID")
    private String merchantContractID;
    @JsonProperty("MerchantCustID")
    private String merchantCustID;
    @JsonProperty("Password")
    private String password;
    @JsonProperty("Phone")
    private String phone;
    @JsonProperty("PrimaryURL")
    private String primaryURL;
    @JsonProperty("Result")
    private String result;
    @JsonProperty("SrNo")
    private String srNo;
    @JsonProperty("State")
    private String state;
    @JsonProperty("UserName")
    private String userName;
    @JsonProperty("Zip")
    private String zip;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("Address1")
    public String getAddress1() {
        return (address1 == null) ? "" : address1.trim();
    }

    @JsonProperty("Address1")
    public void setAddress1(String address1) {
        this.address1 = address1;
    }

    @JsonProperty("Address2")
    public String getAddress2() {
        return (address2 == null) ? "" : address2.trim();
    }

    @JsonProperty("Address2")
    public void setAddress2(String address2) {
        this.address2 = address2;
    }

    @JsonProperty("CardType")
    public String getCardType() {
        return (cardType == null) ? "" : cardType.trim();
    }

    @JsonProperty("CardType")
    public void setCardType(String cardType) {
        this.cardType = cardType;
    }

    @JsonProperty("City")
    public String getCity() {
        return (city == null) ? "" : city.trim();
    }

    @JsonProperty("City")
    public void setCity(String city) {
        this.city = city;
    }

    @JsonProperty("ClientID")
    public String getClientID() {
        return (clientID == null) ? "" : clientID.trim();
    }

    @JsonProperty("ClientID")
    public void setClientID(String clientID) {
        this.clientID = clientID;
    }

    @JsonProperty("CompanyName")
    public String getCompanyName() {
        return (companyName == null) ? "" : companyName.trim();
    }

    @JsonProperty("CompanyName")
    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    @JsonProperty("CreditCardNo")
    public String getCreditCardNo() {
        return (creditCardNo == null) ? "" : creditCardNo.trim();
    }

    @JsonProperty("CreditCardNo")
    public void setCreditCardNo(String creditCardNo) {
        this.creditCardNo = creditCardNo;
    }

    @JsonProperty("Cust_mst_ID")
    public String getCustMstID() {
        return (custMstID == null) ? "" : custMstID.trim();
    }

    @JsonProperty("Cust_mst_ID")
    public void setCustMstID(String custMstID) {
        this.custMstID = custMstID;
    }

    @JsonProperty("Email")
    public String getEmail() {
        return (email == null) ? "" : email.trim();
    }

    @JsonProperty("Email")
    public void setEmail(String email) {
        this.email = email;
    }

    @JsonProperty("Expiration")
    public String getExpiration() {
        return (expiration == null) ? "" : expiration.trim();
    }

    @JsonProperty("Expiration")
    public void setExpiration(String expiration) {
        this.expiration = expiration;
    }

    @JsonProperty("FirstName")
    public String getFirstName() {
        return (firstName == null) ? "" : firstName.trim();
    }

    @JsonProperty("FirstName")
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    @JsonProperty("LastName")
    public String getLastName() {
        return (lastName == null) ? "" : lastName.trim();
    }

    @JsonProperty("LastName")
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @JsonProperty("MerchantCTRStatus")
    public String getMerchantCTRStatus() {
        return (merchantCTRStatus == null) ? "" : merchantCTRStatus.trim();
    }

    @JsonProperty("MerchantCTRStatus")
    public void setMerchantCTRStatus(String merchantCTRStatus) {
        this.merchantCTRStatus = merchantCTRStatus;
    }

    @JsonProperty("MerchantCode")
    public String getMerchantCode() {
        return (merchantCode == null) ? "" : merchantCode.trim();
    }

    @JsonProperty("MerchantCode")
    public void setMerchantCode(String merchantCode) {
        this.merchantCode = merchantCode;
    }

    @JsonProperty("MerchantContractID")
    public String getMerchantContractID() {
        return (merchantContractID == null) ? "" : merchantContractID.trim();
    }

    @JsonProperty("MerchantContractID")
    public void setMerchantContractID(String merchantContractID) {
        this.merchantContractID = merchantContractID;
    }

    @JsonProperty("MerchantCustID")
    public String getMerchantCustID() {
        return (merchantCustID == null) ? "" : merchantCustID.trim();
    }

    @JsonProperty("MerchantCustID")
    public void setMerchantCustID(String merchantCustID) {
        this.merchantCustID = merchantCustID;
    }

    @JsonProperty("Password")
    public String getPassword() {
        return (password == null) ? "" : password.trim();
    }

    @JsonProperty("Password")
    public void setPassword(String password) {
        this.password = password;
    }

    @JsonProperty("Phone")
    public String getPhone() {
        return (phone == null) ? "" : phone.trim();
    }

    @JsonProperty("Phone")
    public void setPhone(String phone) {
        this.phone = phone;
    }

    @JsonProperty("PrimaryURL")
    public String getPrimaryURL() {
        return (primaryURL == null) ? "" : primaryURL.trim();
    }

    @JsonProperty("PrimaryURL")
    public void setPrimaryURL(String primaryURL) {
        this.primaryURL = primaryURL;
    }

    @JsonProperty("Result")
    public String getResult() {
        return (result == null) ? "" : result.trim();
    }

    @JsonProperty("Result")
    public void setResult(String result) {
        this.result = result;
    }

    @JsonProperty("SrNo")
    public String getSrNo() {
        return (srNo == null) ? "" : srNo.trim();
    }

    @JsonProperty("SrNo")
    public void setSrNo(String srNo) {
        this.srNo = srNo;
    }

    @JsonProperty("State")
    public String getState() {
        return (state == null) ? "" : state.trim();
    }

    @JsonProperty("State")
    public void setState(String state) {
        this.state = state;
    }

    @JsonProperty("UserName")
    public String getUserName() {
        return (userName == null) ? "" : userName.trim();
    }

    @JsonProperty("UserName")
    public void setUserName(String userName) {
        this.userName = userName;
    }

    @JsonProperty("Zip")
    public String getZip() {
        return (zip == null) ? "" : zip.trim();
    }

    @JsonProperty("Zip")
    public void setZip(String zip) {
        this.zip = zip;
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