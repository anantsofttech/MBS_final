package com.aspl.mbsmodel;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.util.HashMap;
import java.util.Map;

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
public class GiftWrapModel {

    @JsonProperty("CustomerID")
    private Object customerID;
    @JsonProperty("ID")
    private Integer iD;
    @JsonProperty("ItemID")
    private Object itemID;
    @JsonProperty("Qty")
    private Object qty;
    @JsonProperty("Result")
    private String result;
    @JsonProperty("StoreNo")
    private Object storeNo;
    @JsonProperty("Type")
    private Object type;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("CustomerID")
    public Object getCustomerID() {
        return customerID;
    }

    @JsonProperty("CustomerID")
    public void setCustomerID(Object customerID) {
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
    public Object getItemID() {
        return itemID;
    }

    @JsonProperty("ItemID")
    public void setItemID(Object itemID) {
        this.itemID = itemID;
    }

    @JsonProperty("Qty")
    public Object getQty() {
        return qty;
    }

    @JsonProperty("Qty")
    public void setQty(Object qty) {
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
    public Object getStoreNo() {
        return storeNo;
    }

    @JsonProperty("StoreNo")
    public void setStoreNo(Object storeNo) {
        this.storeNo = storeNo;
    }

    @JsonProperty("Type")
    public Object getType() {
        return type;
    }

    @JsonProperty("Type")
    public void setType(Object type) {
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