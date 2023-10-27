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
        "Department",
        "DepartmentName",
        "FilterDescription",
        "FilterID",
        "FilterType",
        "InvLargeImage",
        "InvLargeImageFullPath",
        "InvSmallImage",
        "InvSmallImageFullPath",
        "Price",
        "PromoPrice",
        "Size",
        "Style"
})
public class GetSearchData {

    @JsonProperty("Department")
    private String department;
    @JsonProperty("DepartmentName")
    private String departmentName;
    @JsonProperty("FilterDescription")
    private String filterDescription;
    @JsonProperty("FilterID")
    private String filterID;
    @JsonProperty("FilterType")
    private String filterType;
    @JsonProperty("InvLargeImage")
    private String InvLargeImage;
    @JsonProperty("InvLargeImageFullPath")
    private String InvLargeImageFullPath;
    @JsonProperty("InvSmallImage")
    private String InvSmallImage;
    @JsonProperty("InvSmallImageFullPath")
    private String InvSmallImageFullPath;
    @JsonProperty("Price")
    private String Price;
    @JsonProperty("PromoPrice")
    private String PromoPrice;
    @JsonProperty("Size")
    private String Size;
    @JsonProperty("Style")
    private String style;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("Department")
    public String getDepartment() {
        return (department == null) ? "" : department;
    }

    @JsonProperty("Department")
    public void setDepartment(String department) {
        this.department = department;
    }

    @JsonProperty("DepartmentName")
    public String getDepartmentName() {
        return (departmentName == null) ? "" : departmentName;
    }

    @JsonProperty("DepartmentName")
    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    @JsonProperty("FilterDescription")
    public String getFilterDescription() {
        return (filterDescription == null) ? "" : filterDescription;
    }

    @JsonProperty("FilterDescription")
    public void setFilterDescription(String filterDescription) {
        this.filterDescription = filterDescription;
    }

    @JsonProperty("FilterID")
    public String getFilterID() {
        return (filterID == null) ? "" : filterID;
    }

    @JsonProperty("FilterID")
    public void setFilterID(String filterID) {
        this.filterID = filterID;
    }

    @JsonProperty("FilterType")
    public String getFilterType() {
        return (filterType == null) ? "" : filterType;
    }

    @JsonProperty("FilterType")
    public void setFilterType(String filterType) {
        this.filterType = filterType;
    }


    @JsonProperty("InvLargeImage")
    public String getInvLargeImage() {
        return InvLargeImage;
    }

    @JsonProperty("InvLargeImage")
    public void setInvLargeImage(String invLargeImage) {
        InvLargeImage = invLargeImage;
    }

    @JsonProperty("InvSmallImage")
    public String getInvSmallImage() {
        return InvSmallImage;
    }

    @JsonProperty("InvSmallImage")
    public void setInvSmallImage(String invSmallImage) {
        InvSmallImage = invSmallImage;
    }

    @JsonProperty("Price")
    public String getPrice() {
        return Price;
    }

    @JsonProperty("Price")
    public void setPrice(String price) {
        Price = price;
    }

    @JsonProperty("PromoPrice")
    public String getPromoPrice() {
        return PromoPrice;
    }

    @JsonProperty("PromoPrice")
    public void setPromoPrice(String promoPrice) {
        PromoPrice = promoPrice;
    }

    @JsonProperty("Size")
    public String getSize() {
        return Size;
    }

    @JsonProperty("Size")
    public void setSize(String size) {
        Size = size;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

    @JsonProperty("Style")
    public String getStyle() {
        return (style == null) ? "" : style;
    }

    @JsonProperty("Style")
    public void setStyle(String style) {
        this.style = style;
    }

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    public String getInvLargeImageFullPath() {
        return InvLargeImageFullPath;
    }

    public void setInvLargeImageFullPath(String invLargeImageFullPath) {
        InvLargeImageFullPath = invLargeImageFullPath;
    }

    public String getInvSmallImageFullPath() {
        return InvSmallImageFullPath;
    }

    public void setInvSmallImageFullPath(String invSmallImageFullPath) {
        InvSmallImageFullPath = invSmallImageFullPath;
    }
}