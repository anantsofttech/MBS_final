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
        "CustomerID",
        "ID",
        "ItemID",
        "Qty",
        "Result",
        "StoreNo",
        "Type"
})
public class GiftWrap {

    @JsonProperty("CustomerID")
    private String customerID;
    @JsonProperty("ID")
    private Integer iD;
    @JsonProperty("ItemID")
    private String itemID;
    @JsonProperty("Qty")
    private String qty;
    @JsonProperty("Result")
    private String result;
    @JsonProperty("StoreNo")
    private String storeNo;
    @JsonProperty("Type")
    private String type;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("CustomerID")
    public String getCustomerID() {
        return customerID;
    }

    @JsonProperty("CustomerID")
    public void setCustomerID(String customerID) {
        this.customerID = customerID;
    }

    @JsonProperty("ID")
    public Integer getID() {
        return iD;
    }

    @JsonProperty("ID")
    public void setID(Integer iD) {
        this.iD = iD;
    }

    @JsonProperty("ItemID")
    public String getItemID() {
        return itemID;
    }

    @JsonProperty("ItemID")
    public void setItemID(String itemID) {
        this.itemID = itemID;
    }

    @JsonProperty("Qty")
    public String getQty() {
        return qty;
    }

    @JsonProperty("Qty")
    public void setQty(String qty) {
        this.qty = qty;
    }

    @JsonProperty("Result")
    public String getResult() {
        return result;
    }

    @JsonProperty("Result")
    public void setResult(String result) {
        this.result = result;
    }

    @JsonProperty("StoreNo")
    public String getStoreNo() {
        return storeNo;
    }

    @JsonProperty("StoreNo")
    public void setStoreNo(String storeNo) {
        this.storeNo = storeNo;
    }

    @JsonProperty("Type")
    public String getType() {
        return type;
    }

    @JsonProperty("Type")
    public void setType(String type) {
        this.type = type;
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