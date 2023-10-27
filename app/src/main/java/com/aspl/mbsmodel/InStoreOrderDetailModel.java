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
        "station",
        "sku",
        "discount",
        "extprice",
        "Qty",
        "size",
        "sizename",
        "ItemName",
        "Price",
        "InvoiceID",
        "SizeFlag",
        "points",
        "bottledeposit",
        "txtDataValue"
})

public class InStoreOrderDetailModel {

    @JsonProperty("station")
    private String station;
    @JsonProperty("sku")
    private String sku;
    @JsonProperty("discount")
    private Double discount;
    @JsonProperty("extprice")
    private Double extprice;
    @JsonProperty("Qty")
    private String qty;
    @JsonProperty("size")
    private String size;
    @JsonProperty("sizename")
    private String sizename;
    @JsonProperty("ItemName")
    private String itemName;
    @JsonProperty("Price")
    private Double price;
    @JsonProperty("InvoiceID")
    private String invoiceID;
    @JsonProperty("SizeFlag")
    private Integer sizeFlag;
    @JsonProperty("points")
    private Double points;
    @JsonProperty("bottledeposit")
    private Double bottledeposit;
    @JsonProperty("txtDataValue")
    private String txtDataValue;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("station")
    public String getStation() {
        return station;
    }

    @JsonProperty("station")
    public void setStation(String station) {
        this.station = station;
    }

    @JsonProperty("sku")
    public String getSku() {
        return sku;
    }

    @JsonProperty("sku")
    public void setSku(String sku) {
        this.sku = sku;
    }

    @JsonProperty("discount")
    public Double getDiscount() {
        return discount;
    }

    @JsonProperty("discount")
    public void setDiscount(Double discount) {
        this.discount = discount;
    }

    @JsonProperty("extprice")
    public Double getExtprice() {
        return extprice;
    }

    @JsonProperty("extprice")
    public void setExtprice(Double extprice) {
        this.extprice = extprice;
    }

    @JsonProperty("Qty")
    public String getQty() {
        return qty;
    }

    @JsonProperty("Qty")
    public void setQty(String qty) {
        this.qty = qty;
    }

    @JsonProperty("size")
    public String getSize() {
        return size;
    }

    @JsonProperty("size")
    public void setSize(String size) {
        this.size = size;
    }

    @JsonProperty("sizename")
    public String getSizename() {
        return sizename;
    }

    @JsonProperty("sizename")
    public void setSizename(String sizename) {
        this.sizename = sizename;
    }

    @JsonProperty("ItemName")
    public String getItemName() {
        return itemName;
    }

    @JsonProperty("ItemName")
    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    @JsonProperty("Price")
    public Double getPrice() {
        return price;
    }

    @JsonProperty("Price")
    public void setPrice(Double price) {
        this.price = price;
    }

    @JsonProperty("InvoiceID")
    public String getInvoiceID() {
        return invoiceID;
    }

    @JsonProperty("InvoiceID")
    public void setInvoiceID(String invoiceID) {
        this.invoiceID = invoiceID;
    }

    @JsonProperty("SizeFlag")
    public Integer getSizeFlag() {
        return sizeFlag;
    }

    @JsonProperty("SizeFlag")
    public void setSizeFlag(Integer sizeFlag) {
        this.sizeFlag = sizeFlag;
    }

    @JsonProperty("points")
    public Double getPoints() {
        return points;
    }

    @JsonProperty("points")
    public void setPoints(Double points) {
        this.points = points;
    }

    @JsonProperty("bottledeposit")
    public Double getBottledeposit() {
        return bottledeposit;
    }

    @JsonProperty("bottledeposit")
    public void setBottledeposit(Double bottledeposit) {
        this.bottledeposit = bottledeposit;
    }

    @JsonProperty("txtDataValue")
    public String getTxtDataValue() {
        return txtDataValue;
    }

    @JsonProperty("txtDataValue")
    public void setTxtDataValue(String txtDataValue) {
        this.txtDataValue = txtDataValue;
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