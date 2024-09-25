package com.aspl.mbsmodel;


import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "AmountPur",
        "Card_type",
        "CustomerID",
        "DeliveryDate",
        "GCQuantity",
        "OrderID",
        "Order_Status",
        "PurDate",
        "Purchaser",
        "Result",
        "SelectedGCDesign",
        "storeno"
})
public class lstGiftCardDetail {

    @JsonProperty("AmountPur")
    private String amountPur;
    @JsonProperty("Card_type")
    private String cardType;
    @JsonProperty("CustomerID")
    private Object customerID;
    @JsonProperty("DeliveryDate")
    private String deliveryDate;
    @JsonProperty("GCQuantity")
    private String gCQuantity;
    @JsonProperty("OrderID")
    private String orderID;
    @JsonProperty("Order_Status")
    private String orderStatus;
    @JsonProperty("PurDate")
    private String purDate;
    @JsonProperty("Purchaser")
    private String purchaser;
    @JsonProperty("Result")
    private String result;
    @JsonProperty("SelectedGCDesign")
    private String selectedGCDesign;
    @JsonProperty("storeno")
    private String storeno;

    public String getAmountPur() {
        return amountPur;
    }

    public void setAmountPur(String amountPur) {
        this.amountPur = amountPur;
    }

    public String getCardType() {
        return cardType;
    }

    public void setCardType(String cardType) {
        this.cardType = cardType;
    }

    public Object getCustomerID() {
        return customerID;
    }

    public void setCustomerID(Object customerID) {
        this.customerID = customerID;
    }

    public String getDeliveryDate() {
        return deliveryDate;
    }

    public void setDeliveryDate(String deliveryDate) {
        this.deliveryDate = deliveryDate;
    }

    public String getgCQuantity() {
        return gCQuantity;
    }

    public void setgCQuantity(String gCQuantity) {
        this.gCQuantity = gCQuantity;
    }

    public String getOrderID() {
        return orderID;
    }

    public void setOrderID(String orderID) {
        this.orderID = orderID;
    }

    public String getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }

    public String getPurDate() {
        return purDate;
    }

    public void setPurDate(String purDate) {
        this.purDate = purDate;
    }

    public String getPurchaser() {
        return purchaser;
    }

    public void setPurchaser(String purchaser) {
        this.purchaser = purchaser;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getSelectedGCDesign() {
        return selectedGCDesign;
    }

    public void setSelectedGCDesign(String selectedGCDesign) {
        this.selectedGCDesign = selectedGCDesign;
    }

    public String getStoreno() {
        return storeno;
    }

    public void setStoreno(String storeno) {
        this.storeno = storeno;
    }
}
