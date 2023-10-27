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
        "AUTH_CODE",
        "AVS_CODE",
        "CTROUTD",
        "CVV2_CODE",
        "CardExp",
        "CardNumber",
        "CardRef",
        "CardType",
        "ErrorMessage",
        "INVOICE",
        "MerchantKey",
        "PAYMENT_MEDIA",
        "RESPONSE_TEXT",
        "RESULT_CODE",
        "RequestParameter",
        "ResponseParameter",
        "Status_Code",
        "SuccessMessage",
        "TROUTD",
        "paywareURL"
})
public class CustomerCard {

    @JsonProperty("AUTH_CODE")
    private String aUTHCODE;
    @JsonProperty("AVS_CODE")
    private String aVSCODE;
    @JsonProperty("CTROUTD")
    private String cTROUTD;
    @JsonProperty("CVV2_CODE")
    private String cVV2CODE;
    @JsonProperty("CardExp")
    private String CardExp;
    @JsonProperty("CardNumber")
    private String CardNumber;
    @JsonProperty("CardRef")
    private String CardRef;
    @JsonProperty("CardType")
    private String CardType;
    @JsonProperty("ErrorMessage")
    private String errorMessage;
    @JsonProperty("INVOICE")
    private String iNVOICE;
    @JsonProperty("MerchantKey")
    private String merchantKey;
    @JsonProperty("PAYMENT_MEDIA")
    private String pAYMENTMEDIA;
    @JsonProperty("RESPONSE_TEXT")
    private String rESPONSETEXT;
    @JsonProperty("RESULT_CODE")
    private String rESULTCODE;
    @JsonProperty("RequestParameter")
    private String requestParameter;
    @JsonProperty("ResponseParameter")
    private String responseParameter;
    @JsonProperty("Status_Code")
    private String statusCode;
    @JsonProperty("SuccessMessage")
    private String successMessage;
    @JsonProperty("TROUTD")
    private String tROUTD;
    @JsonProperty("paywareURL")
    private String paywareURL;
    @JsonIgnore
    private Map<String, String> additionalProperties = new HashMap<String, String>();

    @JsonProperty("AUTH_CODE")
    public String getAUTHCODE() {
        return (aUTHCODE == null) ? "" : aUTHCODE;
    }

    @JsonProperty("AUTH_CODE")
    public void setAUTHCODE(String aUTHCODE) {
        this.aUTHCODE = aUTHCODE;
    }

    @JsonProperty("AVS_CODE")
    public String getAVSCODE() {
        return (aVSCODE == null) ? "" : aVSCODE;
    }

    @JsonProperty("AVS_CODE")
    public void setAVSCODE(String aVSCODE) {
        this.aVSCODE = aVSCODE;
    }

    @JsonProperty("CTROUTD")
    public String getCTROUTD() {
        return (cTROUTD == null) ? "" : cTROUTD;
    }

    @JsonProperty("CTROUTD")
    public void setCTROUTD(String cTROUTD) {
        this.cTROUTD = cTROUTD;
    }

    @JsonProperty("CVV2_CODE")
    public String getCVV2CODE() {
        return (cVV2CODE == null) ? "" : cVV2CODE;
    }

    @JsonProperty("CVV2_CODE")
    public void setCVV2CODE(String cVV2CODE) {
        this.cVV2CODE = cVV2CODE;
    }

    @JsonProperty("CardExp")
    public String getCardExp() {
        return (CardExp == null) ? "" : CardExp;
    }

    @JsonProperty("CardExp")
    public void setCardExp(String CardExp) {
        this.CardExp = CardExp;
    }

    @JsonProperty("CardNumber")
    public String getCardNumber() {
        return (CardNumber == null) ? "" : CardNumber;
    }

    @JsonProperty("CardNumber")
    public void setCardNumber(String CardNumber) {
        this.CardNumber = CardNumber;
    }

    @JsonProperty("CardRef")
    public String getCardRef() {
        return (CardRef == null) ? "" : CardRef;
    }

    @JsonProperty("CardRef")
    public void setCardRef(String CardRef) {
        this.CardRef = CardRef;
    }

    @JsonProperty("CardType")
    public String getCardType() {
        return (CardType == null) ? "" : CardType;
    }

    @JsonProperty("CardType")
    public void setCardType(String CardType) {
        this.CardType = CardType;
    }

    @JsonProperty("ErrorMessage")
    public String getErrorMessage() {
        return (errorMessage == null) ? "" : errorMessage;
    }

    @JsonProperty("ErrorMessage")
    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    @JsonProperty("INVOICE")
    public String getINVOICE() {
        return (iNVOICE == null) ? "" : iNVOICE;
    }

    @JsonProperty("INVOICE")
    public void setINVOICE(String iNVOICE) {
        this.iNVOICE = iNVOICE;
    }

    @JsonProperty("MerchantKey")
    public String getMerchantKey() {
        return (merchantKey == null) ? "" : merchantKey;
    }

    @JsonProperty("MerchantKey")
    public void setMerchantKey(String merchantKey) {
        this.merchantKey = merchantKey;
    }

    @JsonProperty("PAYMENT_MEDIA")
    public String getPAYMENTMEDIA() {
        return (pAYMENTMEDIA == null) ? "" : pAYMENTMEDIA;
    }

    @JsonProperty("PAYMENT_MEDIA")
    public void setPAYMENTMEDIA(String pAYMENTMEDIA) {
        this.pAYMENTMEDIA = pAYMENTMEDIA;
    }

    @JsonProperty("RESPONSE_TEXT")
    public String getRESPONSETEXT() {
        return (rESPONSETEXT == null) ? "" : rESPONSETEXT;
    }

    @JsonProperty("RESPONSE_TEXT")
    public void setRESPONSETEXT(String rESPONSETEXT) {
        this.rESPONSETEXT = rESPONSETEXT;
    }

    @JsonProperty("RESULT_CODE")
    public String getRESULTCODE() {
        return (rESULTCODE == null) ? "" : rESULTCODE;
    }

    @JsonProperty("RESULT_CODE")
    public void setRESULTCODE(String rESULTCODE) {
        this.rESULTCODE = rESULTCODE;
    }

    @JsonProperty("RequestParameter")
    public String getRequestParameter() {
        return (requestParameter == null) ? "" : requestParameter;
    }

    @JsonProperty("RequestParameter")
    public void setRequestParameter(String requestParameter) {
        this.requestParameter = requestParameter;
    }

    @JsonProperty("ResponseParameter")
    public String getResponseParameter() {
        return (responseParameter == null) ? "" : responseParameter;
    }

    @JsonProperty("ResponseParameter")
    public void setResponseParameter(String responseParameter) {
        this.responseParameter = responseParameter;
    }

    @JsonProperty("Status_Code")
    public String getStatusCode() {
        return (statusCode == null) ? "" : statusCode;
    }

    @JsonProperty("Status_Code")
    public void setStatusCode(String statusCode) {
        this.statusCode = statusCode;
    }

    @JsonProperty("SuccessMessage")
    public String getSuccessMessage() {
        return (successMessage == null) ? "" : successMessage;
    }

    @JsonProperty("SuccessMessage")
    public void setSuccessMessage(String successMessage) {
        this.successMessage = successMessage;
    }

    @JsonProperty("TROUTD")
    public String getTROUTD() {
        return (tROUTD == null) ? "" : tROUTD;
    }

    @JsonProperty("TROUTD")
    public void setTROUTD(String tROUTD) {
        this.tROUTD = tROUTD;
    }

    @JsonProperty("paywareURL")
    public String getPaywareURL() {
        return (paywareURL == null) ? "" : paywareURL;
    }

    @JsonProperty("paywareURL")
    public void setPaywareURL(String paywareURL) {
        this.paywareURL = paywareURL;
    }

    @JsonAnyGetter
    public Map<String, String> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, String value) {
        this.additionalProperties.put(name, value);
    }

}