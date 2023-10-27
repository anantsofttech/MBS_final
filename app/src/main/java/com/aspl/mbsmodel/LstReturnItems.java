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
        "OrderReturnID",
        "OrderStatus",
        "RemainingQty"
})

public class LstReturnItems {

    @JsonProperty("OrderReturnID")
    private String orderReturnID;
    @JsonProperty("OrderStatus")
    private String orderStatus;
    @JsonProperty("RemainingQty")
    private String remainingQty;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("OrderReturnID")
    public String getOrderReturnID() {
        return orderReturnID;
    }

    @JsonProperty("OrderReturnID")
    public void setOrderReturnID(String orderReturnID) {
        this.orderReturnID = orderReturnID;
    }

    @JsonProperty("OrderStatus")
    public String getOrderStatus() {
        return orderStatus;
    }

    @JsonProperty("OrderStatus")
    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }

    @JsonProperty("RemainingQty")
    public String getRemainingQty() {
        return remainingQty;
    }

    @JsonProperty("RemainingQty")
    public void setRemainingQty(String remainingQty) {
        this.remainingQty = remainingQty;
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

