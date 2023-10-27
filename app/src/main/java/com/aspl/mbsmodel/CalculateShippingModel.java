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
        "AdditionalCharges",
        "BillingWeight",
        "FedExBaseCharges",
        "FedExNetCharges",
        "FedExSurcharges",
        "HandlingCharges",
        "OriginalCharges",
        "Response",
        "ShippingCharges",
        "UPSRatedShipmentAlert",
        "UPSServiceCharges",
        "UPSTransportationCharges",
        "toaddress",
        "tocity",
        "tostate",
        "tozip",
        "TotalSignatureFees",
        "AdditionalSignatureFees",
        "FedExAdultlSignatureFees",
        "UPSAdultSignatureFees",
        "UPSSignatureFees"
})

public class CalculateShippingModel {

    @JsonProperty("TotalSignatureFees")
    private Double TotalSignatureFees;
    @JsonProperty("AdditionalSignatureFees")
    private Double AdditionalSignatureFees;
    @JsonProperty("FedExAdultlSignatureFees")
    private Double FedExAdultlSignatureFees;
    @JsonProperty("UPSAdultSignatureFees")
    private String UPSAdultSignatureFees;
    @JsonProperty("UPSSignatureFees")
    private String UPSSignatureFees;

    @JsonProperty("AdditionalCharges")
    private Double additionalCharges;
    @JsonProperty("BillingWeight")
    private String billingWeight;
    @JsonProperty("FedExBaseCharges")
    private Double fedExBaseCharges;
    @JsonProperty("FedExNetCharges")
    private Double fedExNetCharges;
    @JsonProperty("FedExSurcharges")
    private Double fedExSurcharges;
    @JsonProperty("HandlingCharges")
    private Double handlingCharges;
    @JsonProperty("OriginalCharges")
    private Double originalCharges;
    @JsonProperty("Response")
    private String response;
    @JsonProperty("ShippingCharges")
    private Double shippingCharges;
    @JsonProperty("UPSRatedShipmentAlert")
    private String uPSRatedShipmentAlert;
    @JsonProperty("UPSServiceCharges")
    private String uPSServiceCharges;
    @JsonProperty("UPSTransportationCharges")
    private String uPSTransportationCharges;
    @JsonProperty("toaddress")
    private String toaddress;
    @JsonProperty("tocity")
    private String tocity;
    @JsonProperty("tostate")
    private String tostate;
    @JsonProperty("tozip")
    private String tozip;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("AdditionalCharges")
    public Double getAdditionalCharges() {
        return additionalCharges;
    }

    @JsonProperty("AdditionalCharges")
    public void setAdditionalCharges(Double additionalCharges) {
        this.additionalCharges = additionalCharges;
    }

    @JsonProperty("BillingWeight")
    public String getBillingWeight() {
        return billingWeight;
    }

    @JsonProperty("BillingWeight")
    public void setBillingWeight(String billingWeight) {
        this.billingWeight = billingWeight;
    }

    @JsonProperty("FedExBaseCharges")
    public Double getFedExBaseCharges() {
        return fedExBaseCharges;
    }

    @JsonProperty("FedExBaseCharges")
    public void setFedExBaseCharges(Double fedExBaseCharges) {
        this.fedExBaseCharges = fedExBaseCharges;
    }

    @JsonProperty("FedExNetCharges")
    public Double getFedExNetCharges() {
        return fedExNetCharges;
    }

    @JsonProperty("FedExNetCharges")
    public void setFedExNetCharges(Double fedExNetCharges) {
        this.fedExNetCharges = fedExNetCharges;
    }

    @JsonProperty("FedExSurcharges")
    public Double getFedExSurcharges() {
        return fedExSurcharges;
    }

    @JsonProperty("FedExSurcharges")
    public void setFedExSurcharges(Double fedExSurcharges) {
        this.fedExSurcharges = fedExSurcharges;
    }

    @JsonProperty("HandlingCharges")
    public Double getHandlingCharges() {
        return handlingCharges;
    }

    @JsonProperty("HandlingCharges")
    public void setHandlingCharges(Double handlingCharges) {
        this.handlingCharges = handlingCharges;
    }

    @JsonProperty("OriginalCharges")
    public Double getOriginalCharges() {
        return originalCharges;
    }

    @JsonProperty("OriginalCharges")
    public void setOriginalCharges(Double originalCharges) {
        this.originalCharges = originalCharges;
    }

    @JsonProperty("Response")
    public String getResponse() {
        return response;
    }

    @JsonProperty("Response")
    public void setResponse(String response) {
        this.response = response;
    }

    @JsonProperty("ShippingCharges")
    public Double getShippingCharges() {
        return shippingCharges;
    }

    @JsonProperty("ShippingCharges")
    public void setShippingCharges(Double shippingCharges) {
        this.shippingCharges = shippingCharges;
    }

    @JsonProperty("UPSRatedShipmentAlert")
    public String getUPSRatedShipmentAlert() {
        return uPSRatedShipmentAlert;
    }

    @JsonProperty("UPSRatedShipmentAlert")
    public void setUPSRatedShipmentAlert(String uPSRatedShipmentAlert) {
        this.uPSRatedShipmentAlert = uPSRatedShipmentAlert;
    }

    @JsonProperty("UPSServiceCharges")
    public String getUPSServiceCharges() {
        return uPSServiceCharges;
    }

    @JsonProperty("UPSServiceCharges")
    public void setUPSServiceCharges(String uPSServiceCharges) {
        this.uPSServiceCharges = uPSServiceCharges;
    }

    @JsonProperty("UPSTransportationCharges")
    public String getUPSTransportationCharges() {
        return uPSTransportationCharges;
    }

    @JsonProperty("UPSTransportationCharges")
    public void setUPSTransportationCharges(String uPSTransportationCharges) {
        this.uPSTransportationCharges = uPSTransportationCharges;
    }

    @JsonProperty("toaddress")
    public String getToaddress() {
        return toaddress;
    }

    @JsonProperty("toaddress")
    public void setToaddress(String toaddress) {
        this.toaddress = toaddress;
    }

    @JsonProperty("tocity")
    public String getTocity() {
        return tocity;
    }

    @JsonProperty("tocity")
    public void setTocity(String tocity) {
        this.tocity = tocity;
    }

    @JsonProperty("tostate")
    public String getTostate() {
        return tostate;
    }

    @JsonProperty("tostate")
    public void setTostate(String tostate) {
        this.tostate = tostate;
    }

    @JsonProperty("tozip")
    public String getTozip() {
        return tozip;
    }

    @JsonProperty("tozip")
    public void setTozip(String tozip) {
        this.tozip = tozip;
    }

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

    public Double getTotalSignatureFees() {
        return TotalSignatureFees;
    }

    public void setTotalSignatureFees(Double totalSignatureFees) {
        TotalSignatureFees = totalSignatureFees;
    }

    public Double getAdditionalSignatureFees() {
        return AdditionalSignatureFees;
    }

    public void setAdditionalSignatureFees(Double additionalSignatureFees) {
        AdditionalSignatureFees = additionalSignatureFees;
    }

    public Double getFedExAdultlSignatureFees() {
        return FedExAdultlSignatureFees;
    }

    public void setFedExAdultlSignatureFees(Double fedExAdultlSignatureFees) {
        FedExAdultlSignatureFees = fedExAdultlSignatureFees;
    }

    public String getUPSAdultSignatureFees() {
        return UPSAdultSignatureFees;
    }

    public void setUPSAdultSignatureFees(String UPSAdultSignatureFees) {
        this.UPSAdultSignatureFees = UPSAdultSignatureFees;
    }

    public String getUPSSignatureFees() {
        return UPSSignatureFees;
    }

    public void setUPSSignatureFees(String UPSSignatureFees) {
        this.UPSSignatureFees = UPSSignatureFees;
    }
}