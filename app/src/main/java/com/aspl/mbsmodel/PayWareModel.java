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
        "AUTH_CODE",
        "AVS_CODE",
        "CTROUTD",
        "CVV2_CODE",
        "ErrorMessage",
        "INVOICE",
        "LogID",
        "MerchantKey",
        "Message",
        "PAYMENT_MEDIA",
        "RESPONSE_TEXT",
        "RESULT_CODE",
        "RequestParameter",
        "ResponseParameter",
        "Status_Code",
        "SuccessMessage",
        "TROUTD",
        "paywareURL",
        "storeno"
})
public class PayWareModel {

    @JsonProperty("AUTH_CODE")
    private Object aUTHCODE;
    @JsonProperty("AVS_CODE")
    private Object aVSCODE;
    @JsonProperty("CTROUTD")
    private Object cTROUTD;
    @JsonProperty("CVV2_CODE")
    private Object cVV2CODE;
    @JsonProperty("ErrorMessage")
    private Object errorMessage;
    @JsonProperty("INVOICE")
    private Object iNVOICE;
    @JsonProperty("LogID")
    private Object LogID;
    @JsonProperty("MerchantKey")
    private String merchantKey;
    @JsonProperty("Message")
    private String Message;
    @JsonProperty("PAYMENT_MEDIA")
    private Object pAYMENTMEDIA;
    @JsonProperty("RESPONSE_TEXT")
    private String rESPONSETEXT;
    @JsonProperty("RESULT_CODE")
    private String rESULTCODE;
    @JsonProperty("RequestParameter")
    private String requestParameter;
    @JsonProperty("ResponseParameter")
    private String responseParameter;
    @JsonProperty("Status_Code")
    private Object statusCode;
    @JsonProperty("SuccessMessage")
    private Object successMessage;
    @JsonProperty("TROUTD")
    private Object tROUTD;
    @JsonProperty("paywareURL")
    private String paywareURL;
    @JsonProperty("storeno")
    private String storeno;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("AUTH_CODE")
    public Object getAUTHCODE() {
        return aUTHCODE;
    }

    @JsonProperty("AUTH_CODE")
    public void setAUTHCODE(Object aUTHCODE) {
        this.aUTHCODE = aUTHCODE;
    }

    @JsonProperty("AVS_CODE")
    public Object getAVSCODE() {
        return aVSCODE;
    }

    @JsonProperty("AVS_CODE")
    public void setAVSCODE(Object aVSCODE) {
        this.aVSCODE = aVSCODE;
    }

    @JsonProperty("CTROUTD")
    public Object getCTROUTD() {
        return cTROUTD;
    }

    @JsonProperty("CTROUTD")
    public void setCTROUTD(Object cTROUTD) {
        this.cTROUTD = cTROUTD;
    }

    @JsonProperty("CVV2_CODE")
    public Object getCVV2CODE() {
        return cVV2CODE;
    }

    @JsonProperty("CVV2_CODE")
    public void setCVV2CODE(Object cVV2CODE) {
        this.cVV2CODE = cVV2CODE;
    }

    @JsonProperty("ErrorMessage")
    public Object getErrorMessage() {
        return errorMessage;
    }

    @JsonProperty("ErrorMessage")
    public void setErrorMessage(Object errorMessage) {
        this.errorMessage = errorMessage;
    }

    @JsonProperty("INVOICE")
    public Object getINVOICE() {
        return iNVOICE;
    }

    @JsonProperty("INVOICE")
    public void setINVOICE(Object iNVOICE) {
        this.iNVOICE = iNVOICE;
    }

    @JsonProperty("LogID")
    public Object getLogID() {
        return LogID;
    }

    @JsonProperty("LogID")
    public void setLogID(Object LogID) {
        this.LogID = LogID;
    }

    @JsonProperty("MerchantKey")
    public String getMerchantKey() {
        return merchantKey;
    }

    @JsonProperty("MerchantKey")
    public void setMerchantKey(String merchantKey) {
        this.merchantKey = merchantKey;
    }

    @JsonProperty("Message")
    public String getMessage() {
        return Message;
    }

    @JsonProperty("Message")
    public void setMessage(String Message) {
        this.Message = Message;
    }

    @JsonProperty("PAYMENT_MEDIA")
    public Object getPAYMENTMEDIA() {
        return pAYMENTMEDIA;
    }

    @JsonProperty("PAYMENT_MEDIA")
    public void setPAYMENTMEDIA(Object pAYMENTMEDIA) {
        this.pAYMENTMEDIA = pAYMENTMEDIA;
    }

    @JsonProperty("RESPONSE_TEXT")
    public String getRESPONSETEXT() {
        return rESPONSETEXT;
    }

    @JsonProperty("RESPONSE_TEXT")
    public void setRESPONSETEXT(String rESPONSETEXT) {
        this.rESPONSETEXT = rESPONSETEXT;
    }

    @JsonProperty("RESULT_CODE")
    public String getRESULTCODE() {
        return rESULTCODE;
    }

    @JsonProperty("RESULT_CODE")
    public void setRESULTCODE(String rESULTCODE) {
        this.rESULTCODE = rESULTCODE;
    }

    @JsonProperty("RequestParameter")
    public String getRequestParameter() {
        return requestParameter;
    }

    @JsonProperty("RequestParameter")
    public void setRequestParameter(String requestParameter) {
        this.requestParameter = requestParameter;
    }

    @JsonProperty("ResponseParameter")
    public String getResponseParameter() {
        return responseParameter;
    }

    @JsonProperty("ResponseParameter")
    public void setResponseParameter(String responseParameter) {
        this.responseParameter = responseParameter;
    }

    @JsonProperty("Status_Code")
    public Object getStatusCode() {
        return statusCode;
    }

    @JsonProperty("Status_Code")
    public void setStatusCode(Object statusCode) {
        this.statusCode = statusCode;
    }

    @JsonProperty("SuccessMessage")
    public Object getSuccessMessage() {
        return successMessage;
    }

    @JsonProperty("SuccessMessage")
    public void setSuccessMessage(Object successMessage) {
        this.successMessage = successMessage;
    }

    @JsonProperty("TROUTD")
    public Object getTROUTD() {
        return tROUTD;
    }

    @JsonProperty("TROUTD")
    public void setTROUTD(Object tROUTD) {
        this.tROUTD = tROUTD;
    }

    @JsonProperty("paywareURL")
    public String getPaywareURL() {
        return paywareURL;
    }

    @JsonProperty("paywareURL")
    public void setPaywareURL(String paywareURL) {
        this.paywareURL = paywareURL;
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