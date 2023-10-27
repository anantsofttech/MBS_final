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
        "APIKey",
        "GroupNo",
        "MerchantID",
        "PrimaryUrl",
        "StoreNo",
        "TerminalID"
})
public class GetScoopSetting {

    @JsonProperty("APIKey")
    private String aPIKey;
    @JsonProperty("GroupNo")
    private String groupNo;
    @JsonProperty("MerchantID")
    private String merchantID;
    @JsonProperty("PrimaryUrl")
    private String primaryUrl;
    @JsonProperty("StoreNo")
    private String storeNo;
    @JsonProperty("TerminalID")
    private String terminalID;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("APIKey")
    public String getAPIKey() {
        return aPIKey;
    }

    @JsonProperty("APIKey")
    public void setAPIKey(String aPIKey) {
        this.aPIKey = aPIKey;
    }

    @JsonProperty("GroupNo")
    public String getGroupNo() {
        return groupNo;
    }

    @JsonProperty("GroupNo")
    public void setGroupNo(String groupNo) {
        this.groupNo = groupNo;
    }

    @JsonProperty("MerchantID")
    public String getMerchantID() {
        return merchantID;
    }

    @JsonProperty("MerchantID")
    public void setMerchantID(String merchantID) {
        this.merchantID = merchantID;
    }

    @JsonProperty("PrimaryUrl")
    public String getPrimaryUrl() {
        return primaryUrl;
    }

    @JsonProperty("PrimaryUrl")
    public void setPrimaryUrl(String primaryUrl) {
        this.primaryUrl = primaryUrl;
    }

    @JsonProperty("StoreNo")
    public String getStoreNo() {
        return storeNo;
    }

    @JsonProperty("StoreNo")
    public void setStoreNo(String storeNo) {
        this.storeNo = storeNo;
    }

    @JsonProperty("TerminalID")
    public String getTerminalID() {
        return terminalID;
    }

    @JsonProperty("TerminalID")
    public void setTerminalID(String terminalID) {
        this.terminalID = terminalID;
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