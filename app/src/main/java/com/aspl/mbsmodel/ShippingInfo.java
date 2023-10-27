package com.aspl.mbsmodel;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "AdditionalCharges",
        "AutoId",
        "BottomRange",
        "DefaultShipping",
        "FedEx",
        "FedExServiceList",
        "FedExServices",
        "FedExServicesName",
        "ShippingPrice",
        "ShippingType",
        "StoreNo",
        "TopRange",
        "UPS",
        "UPSServiceList",
        "UPSServices",
        "UPSServicesName",
        "USPS",
        "USPSServiceList",
        "USPSServices",
        "USPSServicesName"
})

public class ShippingInfo {

    @JsonProperty("AdditionalCharges")
    private String additionalCharges;
    @JsonProperty("AutoId")
    private Object autoId;
    @JsonProperty("BottomRange")
    private Object bottomRange;
    @JsonProperty("DefaultShipping")
    private String defaultShipping;
    @JsonProperty("FedEx")
    private String fedEx;
    @JsonProperty("FedExServiceList")
    private List<FedExServiceModel> fedExServiceList = null;
    @JsonProperty("FedExServices")
    private String fedExServices;
    @JsonProperty("FedExServicesName")
    private Object fedExServicesName;
    @JsonProperty("ShippingPrice")
    private Object shippingPrice;
    @JsonProperty("ShippingType")
    private String shippingType;
    @JsonProperty("StoreNo")
    private String storeNo;
    @JsonProperty("TopRange")
    private Object topRange;
    @JsonProperty("UPS")
    private String ups;
    @JsonProperty("UPSServiceList")
    private List<UPSServiceModel> uPSServiceList = null;
    @JsonProperty("UPSServices")
    private String uPSServices;
    @JsonProperty("UPSServicesName")
    private Object uPSServicesName;
    @JsonProperty("USPS")
    private String usps;
    @JsonProperty("USPSServiceList")
    private List<USPSServiceModel> uSPSServiceList = null;
    @JsonProperty("USPSServices")
    private String uSPSServices;
    @JsonProperty("USPSServicesName")
    private Object uSPSServicesName;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("AdditionalCharges")
    public String getAdditionalCharges() {
        return additionalCharges;
    }

    @JsonProperty("AdditionalCharges")
    public void setAdditionalCharges(String additionalCharges) {
        this.additionalCharges = additionalCharges;
    }

    @JsonProperty("AutoId")
    public Object getAutoId() {
        return autoId;
    }

    @JsonProperty("AutoId")
    public void setAutoId(Object autoId) {
        this.autoId = autoId;
    }

    @JsonProperty("BottomRange")
    public Object getBottomRange() {
        return bottomRange;
    }

    @JsonProperty("BottomRange")
    public void setBottomRange(Object bottomRange) {
        this.bottomRange = bottomRange;
    }

    @JsonProperty("DefaultShipping")
    public String getDefaultShipping() {
        return defaultShipping;
    }

    @JsonProperty("DefaultShipping")
    public void setDefaultShipping(String defaultShipping) {
        this.defaultShipping = defaultShipping;
    }

    @JsonProperty("FedEx")
    public String getFedEx() {
        return fedEx;
    }

    @JsonProperty("FedEx")
    public void setFedEx(String fedEx) {
        this.fedEx = fedEx;
    }

    @JsonProperty("FedExServiceList")
    public List<FedExServiceModel> getFedExServiceList() {
        return fedExServiceList;
    }

    @JsonProperty("FedExServiceList")
    public void setFedExServiceList(List<FedExServiceModel> fedExServiceList) {
        this.fedExServiceList = fedExServiceList;
    }

    @JsonProperty("FedExServices")
    public String getFedExServices() {
        return fedExServices;
    }

    @JsonProperty("FedExServices")
    public void setFedExServices(String fedExServices) {
        this.fedExServices = fedExServices;
    }

    @JsonProperty("FedExServicesName")
    public Object getFedExServicesName() {
        return fedExServicesName;
    }

    @JsonProperty("FedExServicesName")
    public void setFedExServicesName(Object fedExServicesName) {
        this.fedExServicesName = fedExServicesName;
    }

    @JsonProperty("ShippingPrice")
    public Object getShippingPrice() {
        return shippingPrice;
    }

    @JsonProperty("ShippingPrice")
    public void setShippingPrice(Object shippingPrice) {
        this.shippingPrice = shippingPrice;
    }

    @JsonProperty("ShippingType")
    public String getShippingType() {
        return shippingType;
    }

    @JsonProperty("ShippingType")
    public void setShippingType(String shippingType) {
        this.shippingType = shippingType;
    }

    @JsonProperty("StoreNo")
    public String getStoreNo() {
        return storeNo;
    }

    @JsonProperty("StoreNo")
    public void setStoreNo(String storeNo) {
        this.storeNo = storeNo;
    }

    @JsonProperty("TopRange")
    public Object getTopRange() {
        return topRange;
    }

    @JsonProperty("TopRange")
    public void setTopRange(Object topRange) {
        this.topRange = topRange;
    }

    @JsonProperty("UPS")
    public String getUps() {
        return ups;
    }

    @JsonProperty("UPS")
    public void setUps(String ups) {
        this.ups = ups;
    }

    @JsonProperty("UPSServiceList")
    public List<UPSServiceModel> getUPSServiceList() {
        return uPSServiceList;
    }

    @JsonProperty("UPSServiceList")
    public void setUPSServiceList(List<UPSServiceModel> uPSServiceList) {
        this.uPSServiceList = uPSServiceList;
    }

    @JsonProperty("UPSServices")
    public String getUPSServices() {
        return uPSServices;
    }

    @JsonProperty("UPSServices")
    public void setUPSServices(String uPSServices) {
        this.uPSServices = uPSServices;
    }

    @JsonProperty("UPSServicesName")
    public Object getUPSServicesName() {
        return uPSServicesName;
    }

    @JsonProperty("UPSServicesName")
    public void setUPSServicesName(Object uPSServicesName) {
        this.uPSServicesName = uPSServicesName;
    }

    @JsonProperty("USPS")
    public String getUsps() {
        return usps;
    }

    @JsonProperty("USPS")
    public void setUsps(String usps) {
        this.usps = usps;
    }

    @JsonProperty("USPSServiceList")
    public List<USPSServiceModel> getUSPSServiceList() {
        return uSPSServiceList;
    }

    @JsonProperty("USPSServiceList")
    public void setUSPSServiceList(List<USPSServiceModel> uSPSServiceList) {
        this.uSPSServiceList = uSPSServiceList;
    }

    @JsonProperty("USPSServices")
    public String getUSPSServices() {
        return uSPSServices;
    }

    @JsonProperty("USPSServices")
    public void setUSPSServices(String uSPSServices) {
        this.uSPSServices = uSPSServices;
    }

    @JsonProperty("USPSServicesName")
    public Object getUSPSServicesName() {
        return uSPSServicesName;
    }

    @JsonProperty("USPSServicesName")
    public void setUSPSServicesName(Object uSPSServicesName) {
        this.uSPSServicesName = uSPSServicesName;
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
