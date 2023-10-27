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
        "GiftCardBal",
        "GiftCardNo",
        "LastPurDate",
        "LastPurTime",
        "Last_Act"
})

public class CheckGiftCardBalanceModel {

    @JsonProperty("GiftCardBal")
    private String giftCardBal;
    @JsonProperty("GiftCardNo")
    private String giftCardNo;
    @JsonProperty("LastPurDate")
    private String lastPurDate;
    @JsonProperty("LastPurTime")
    private String lastPurTime;
    @JsonProperty("Last_Act")
    private String lastAct;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("GiftCardBal")
    public String getGiftCardBal() {
        return giftCardBal;
    }

    @JsonProperty("GiftCardBal")
    public void setGiftCardBal(String giftCardBal) {
        this.giftCardBal = giftCardBal;
    }

    @JsonProperty("GiftCardNo")
    public String getGiftCardNo() {
        return giftCardNo;
    }

    @JsonProperty("GiftCardNo")
    public void setGiftCardNo(String giftCardNo) {
        this.giftCardNo = giftCardNo;
    }

    @JsonProperty("LastPurDate")
    public String getLastPurDate() {
        return lastPurDate;
    }

    @JsonProperty("LastPurDate")
    public void setLastPurDate(String lastPurDate) {
        this.lastPurDate = lastPurDate;
    }

    @JsonProperty("LastPurTime")
    public String getLastPurTime() {
        return lastPurTime;
    }

    @JsonProperty("LastPurTime")
    public void setLastPurTime(String lastPurTime) {
        this.lastPurTime = lastPurTime;
    }

    @JsonProperty("Last_Act")
    public String getLastAct() {
        return lastAct;
    }

    @JsonProperty("Last_Act")
    public void setLastAct(String lastAct) {
        this.lastAct = lastAct;
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
