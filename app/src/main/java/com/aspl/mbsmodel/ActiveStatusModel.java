package com.aspl.mbsmodel;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "ISUSAePayActive",
        "Result",
        "USAePay"
})

public class ActiveStatusModel {
    @JsonProperty("ISUSAePayActive")
    private String iSUSAePayActive;
    @JsonProperty("Result")
    private String result;
    @JsonProperty("USAePay")
    private String uSAePay;


    public String getiSUSAePayActive() {
        return iSUSAePayActive;
    }

    public void setiSUSAePayActive(String iSUSAePayActive) {
        this.iSUSAePayActive = iSUSAePayActive;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getuSAePay() {
        return uSAePay;
    }

    public void setuSAePay(String uSAePay) {
        this.uSAePay = uSAePay;
    }
}
