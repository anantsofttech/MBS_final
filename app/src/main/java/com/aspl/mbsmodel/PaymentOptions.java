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
        "ApplePay",
        "GooglePay",
        "MasterPass",
        "PayPal"
})
public class PaymentOptions {

    @JsonProperty("ApplePay")
    private Boolean applePay;
    @JsonProperty("GooglePay")
    private Boolean googlePay;
    @JsonProperty("MasterPass")
    private Boolean masterPass;
    @JsonProperty("PayPal")
    private Boolean payPal;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("ApplePay")
    public Boolean getApplePay() {
        return applePay;
    }

    @JsonProperty("ApplePay")
    public void setApplePay(Boolean applePay) {
        this.applePay = applePay;
    }

    @JsonProperty("GooglePay")
    public Boolean getGooglePay() {
        return googlePay;
    }

    @JsonProperty("GooglePay")
    public void setGooglePay(Boolean googlePay) {
        this.googlePay = googlePay;
    }

    @JsonProperty("MasterPass")
    public Boolean getMasterPass() {
        return masterPass;
    }

    @JsonProperty("MasterPass")
    public void setMasterPass(Boolean masterPass) {
        this.masterPass = masterPass;
    }

    @JsonProperty("PayPal")
    public Boolean getPayPal() {
        return payPal;
    }

    @JsonProperty("PayPal")
    public void setPayPal(Boolean payPal) {
        this.payPal = payPal;
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
