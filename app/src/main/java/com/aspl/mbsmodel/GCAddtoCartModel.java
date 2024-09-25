package com.aspl.mbsmodel;

import java.util.LinkedHashMap;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "Amount",
        "AmoutPur",
        "Card_type",
        "CustomerID",
        "CustomerName",
        "DeliveryDate",
        "GCImage",
        "GCQuantity",
        "ID",
        "IsTotalSavingDisplay",
        "Message",
        "OrderFinalTotal",
        "Order_Status",
        "PurDate",
        "Purchaser",
        "Quantity",
        "Result",
        "SelectedGCDesign",
        "SessionID",
        "Status",
        "StoreNo",
        "ToEmail1",
        "ToEmail2",
        "ToEmail3",
        "ToPhone",
        "UserName",
        "lstGiftCardInfo"
})

public class GCAddtoCartModel {

    @JsonProperty("Amount")
    private String amount;
    @JsonProperty("AmoutPur")
    private String amoutPur;
    @JsonProperty("Card_type")
    private String cardType;
    @JsonProperty("CustomerID")
    private String customerID;
    @JsonProperty("CustomerName")
    private String customerName;
    @JsonProperty("DeliveryDate")
    private String deliveryDate;
    @JsonProperty("GCImage")
    private String gCImage;
    @JsonProperty("GCQuantity")
    private String gCQuantity;
    @JsonProperty("ID")
    private String id;
    @JsonProperty("IsTotalSavingDisplay")
    private String isTotalSavingDisplay;
    @JsonProperty("Message")
    private String message;
    @JsonProperty("OrderFinalTotal")
    private String orderFinalTotal;
    @JsonProperty("Order_Status")
    private String orderStatus;
    @JsonProperty("PurDate")
    private String purDate;
    @JsonProperty("Purchaser")
    private String purchaser;
    @JsonProperty("Quantity")
    private String quantity;
    @JsonProperty("Result")
    private String result;
    @JsonProperty("SelectedGCDesign")
    private String selectedGCDesign;
    @JsonProperty("SessionID")
    private String sessionID;
    @JsonProperty("Status")
    private String status;
    @JsonProperty("StoreNo")
    private String storeNo;
    @JsonProperty("ToEmail1")
    private String toEmail1;
    @JsonProperty("ToEmail2")
    private String toEmail2;
    @JsonProperty("ToEmail3")
    private String toEmail3;
    @JsonProperty("ToPhone")
    private String toPhone;
    @JsonProperty("UserName")
    private String userName;
    @JsonProperty("lstGiftCardInfo")
    private String lstGiftCardInfo;
    @JsonProperty("Amount")
    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getAmoutPur() {
        return amoutPur;
    }

    public void setAmoutPur(String amoutPur) {
        this.amoutPur = amoutPur;
    }

    public String getCardType() {
        return cardType;
    }

    public void setCardType(String cardType) {
        this.cardType = cardType;
    }

    public String getCustomerID() {
        return customerID;
    }

    public void setCustomerID(String customerID) {
        this.customerID = customerID;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getDeliveryDate() {
        return deliveryDate;
    }

    public void setDeliveryDate(String deliveryDate) {
        this.deliveryDate = deliveryDate;
    }

    public String getgCImage() {
        return gCImage;
    }

    public void setgCImage(String gCImage) {
        this.gCImage = gCImage;
    }

    public String getgCQuantity() {
        return gCQuantity;
    }

    public void setgCQuantity(String gCQuantity) {
        this.gCQuantity = gCQuantity;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getIsTotalSavingDisplay() {
        return isTotalSavingDisplay;
    }

    public void setIsTotalSavingDisplay(String isTotalSavingDisplay) {
        this.isTotalSavingDisplay = isTotalSavingDisplay;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getOrderFinalTotal() {
        return orderFinalTotal;
    }

    public void setOrderFinalTotal(String orderFinalTotal) {
        this.orderFinalTotal = orderFinalTotal;
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

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
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

    public String getSessionID() {
        return sessionID;
    }

    public void setSessionID(String sessionID) {
        this.sessionID = sessionID;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getStoreNo() {
        return storeNo;
    }

    public void setStoreNo(String storeNo) {
        this.storeNo = storeNo;
    }

    public String getToEmail1() {
        return toEmail1;
    }

    public void setToEmail1(String toEmail1) {
        this.toEmail1 = toEmail1;
    }

    public String getToEmail2() {
        return toEmail2;
    }

    public void setToEmail2(String toEmail2) {
        this.toEmail2 = toEmail2;
    }

    public String getToEmail3() {
        return toEmail3;
    }

    public void setToEmail3(String toEmail3) {
        this.toEmail3 = toEmail3;
    }

    public String getToPhone() {
        return toPhone;
    }

    public void setToPhone(String toPhone) {
        this.toPhone = toPhone;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getLstGiftCardInfo() {
        return lstGiftCardInfo;
    }

    public void setLstGiftCardInfo(String lstGiftCardInfo) {
        this.lstGiftCardInfo = lstGiftCardInfo;
    }
}