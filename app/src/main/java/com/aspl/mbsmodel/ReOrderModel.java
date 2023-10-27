package com.aspl.mbsmodel;

/**
 * Created by admin on 8/13/2018.
 */

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
        "BAddress1",
        "BAddress2",
        "BCity",
        "BFName",
        "BLName",
        "BPhone",
        "BState",
        "BZip",
        "CustomerID",
        "CustomerName",
        "EndRecord",
        "FlgtmpCashTip",
        "ID",
        "InvoiceNo",
        "IsDeliverHome",
        "IsHandDelivery",
        "IsPickupStore",
        "IsTotalSavingDisplay",
        "IsUberRush",
        "LoyaltyPoints",
        "LoyaltyType",
        "OrderDate",
        "OrderFinalTotal",
        "OrderID",
        "OrderReturnID",
        "OrderStatus",
        "OrderSubTotal",
        "OrderTotal",
        "PaymentMedia",
        "PaymentType",
        "PointUsed",
        "Qty",
        "Result",
        "RewardDollarAvailable",
        "RewardDollarUsed",
        "SAddress1",
        "SAddress2",
        "SCity",
        "SFName",
        "SLName",
        "SPhone",
        "SState",
        "SZip",
        "ShipmentMessage",
        "SizeFlag",
        "StartRecord",
        "StoreNo",
        "TaxExamptMessage",
        "TaxExamptNo",
        "TipValue",
        "TotalItem",
        "TotalRecord",
        "TotalSaving",
        "isShipTaxToOtherCountry",
        "lstOrderTems",
        "page_count"
})
public class ReOrderModel {

    @JsonProperty("BAddress1")
    private Object bAddress1;
    @JsonProperty("BAddress2")
    private Object bAddress2;
    @JsonProperty("BCity")
    private Object bCity;
    @JsonProperty("BFName")
    private Object bFName;
    @JsonProperty("BLName")
    private Object bLName;
    @JsonProperty("BPhone")
    private Object bPhone;
    @JsonProperty("BState")
    private Object bState;
    @JsonProperty("BZip")
    private Object bZip;
    @JsonProperty("CustomerID")
    private Object customerID;
    @JsonProperty("CustomerName")
    private Object customerName;
    @JsonProperty("EndRecord")
    private Integer endRecord;
    @JsonProperty("FlgtmpCashTip")
    private Boolean flgtmpCashTip;
    @JsonProperty("ID")
    private Object iD;
    @JsonProperty("InvoiceNo")
    private Object invoiceNo;
    @JsonProperty("IsDeliverHome")
    private Object isDeliverHome;
    @JsonProperty("IsHandDelivery")
    private Object isHandDelivery;
    @JsonProperty("IsPickupStore")
    private Object isPickupStore;
    @JsonProperty("IsTotalSavingDisplay")
    private Boolean isTotalSavingDisplay;
    @JsonProperty("IsUberRush")
    private Object isUberRush;
    @JsonProperty("LoyaltyPoints")
    private Object loyaltyPoints;
    @JsonProperty("LoyaltyType")
    private Object loyaltyType;
    @JsonProperty("OrderDate")
    private Object orderDate;
    @JsonProperty("OrderFinalTotal")
    private Object orderFinalTotal;
    @JsonProperty("OrderID")
    private Object orderID;
    @JsonProperty("OrderReturnID")
    private Object orderReturnID;
    @JsonProperty("OrderStatus")
    private Object orderStatus;
    @JsonProperty("OrderSubTotal")
    private Object orderSubTotal;
    @JsonProperty("OrderTotal")
    private Object orderTotal;
    @JsonProperty("PaymentMedia")
    private Object paymentMedia;
    @JsonProperty("PaymentType")
    private Object paymentType;
    @JsonProperty("PointUsed")
    private Object pointUsed;
    @JsonProperty("Qty")
    private Object qty;
    @JsonProperty("Result")
    private String result;
    @JsonProperty("RewardDollarAvailable")
    private Object rewardDollarAvailable;
    @JsonProperty("RewardDollarUsed")
    private Object rewardDollarUsed;
    @JsonProperty("SAddress1")
    private Object sAddress1;
    @JsonProperty("SAddress2")
    private Object sAddress2;
    @JsonProperty("SCity")
    private Object sCity;
    @JsonProperty("SFName")
    private Object sFName;
    @JsonProperty("SLName")
    private Object sLName;
    @JsonProperty("SPhone")
    private Object sPhone;
    @JsonProperty("SState")
    private Object sState;
    @JsonProperty("SZip")
    private Object sZip;
    @JsonProperty("ShipmentMessage")
    private Object shipmentMessage;
    @JsonProperty("SizeFlag")
    private Object sizeFlag;
    @JsonProperty("StartRecord")
    private Integer startRecord;
    @JsonProperty("StoreNo")
    private Object storeNo;
    @JsonProperty("TaxExamptMessage")
    private Object taxExamptMessage;
    @JsonProperty("TaxExamptNo")
    private Object taxExamptNo;
    @JsonProperty("TipValue")
    private Object tipValue;
    @JsonProperty("TotalItem")
    private Object totalItem;
    @JsonProperty("TotalRecord")
    private Integer totalRecord;
    @JsonProperty("TotalSaving")
    private Object totalSaving;
    @JsonProperty("isShipTaxToOtherCountry")
    private Boolean isShipTaxToOtherCountry;
    @JsonProperty("lstOrderTems")
    private Object lstOrderTems;
    @JsonProperty("page_count")
    private Integer pageCount;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("BAddress1")
    public Object getBAddress1() {
        return bAddress1;
    }

    @JsonProperty("BAddress1")
    public void setBAddress1(Object bAddress1) {
        this.bAddress1 = bAddress1;
    }

    @JsonProperty("BAddress2")
    public Object getBAddress2() {
        return bAddress2;
    }

    @JsonProperty("BAddress2")
    public void setBAddress2(Object bAddress2) {
        this.bAddress2 = bAddress2;
    }

    @JsonProperty("BCity")
    public Object getBCity() {
        return bCity;
    }

    @JsonProperty("BCity")
    public void setBCity(Object bCity) {
        this.bCity = bCity;
    }

    @JsonProperty("BFName")
    public Object getBFName() {
        return bFName;
    }

    @JsonProperty("BFName")
    public void setBFName(Object bFName) {
        this.bFName = bFName;
    }

    @JsonProperty("BLName")
    public Object getBLName() {
        return bLName;
    }

    @JsonProperty("BLName")
    public void setBLName(Object bLName) {
        this.bLName = bLName;
    }

    @JsonProperty("BPhone")
    public Object getBPhone() {
        return bPhone;
    }

    @JsonProperty("BPhone")
    public void setBPhone(Object bPhone) {
        this.bPhone = bPhone;
    }

    @JsonProperty("BState")
    public Object getBState() {
        return bState;
    }

    @JsonProperty("BState")
    public void setBState(Object bState) {
        this.bState = bState;
    }

    @JsonProperty("BZip")
    public Object getBZip() {
        return bZip;
    }

    @JsonProperty("BZip")
    public void setBZip(Object bZip) {
        this.bZip = bZip;
    }

    @JsonProperty("CustomerID")
    public Object getCustomerID() {
        return customerID;
    }

    @JsonProperty("CustomerID")
    public void setCustomerID(Object customerID) {
        this.customerID = customerID;
    }

    @JsonProperty("CustomerName")
    public Object getCustomerName() {
        return customerName;
    }

    @JsonProperty("CustomerName")
    public void setCustomerName(Object customerName) {
        this.customerName = customerName;
    }

    @JsonProperty("EndRecord")
    public Integer getEndRecord() {
        return endRecord;
    }

    @JsonProperty("EndRecord")
    public void setEndRecord(Integer endRecord) {
        this.endRecord = endRecord;
    }

    @JsonProperty("FlgtmpCashTip")
    public Boolean getFlgtmpCashTip() {
        return flgtmpCashTip;
    }

    @JsonProperty("FlgtmpCashTip")
    public void setFlgtmpCashTip(Boolean flgtmpCashTip) {
        this.flgtmpCashTip = flgtmpCashTip;
    }

    @JsonProperty("ID")
    public Object getID() {
        return iD;
    }

    @JsonProperty("ID")
    public void setID(Object iD) {
        this.iD = iD;
    }

    @JsonProperty("InvoiceNo")
    public Object getInvoiceNo() {
        return invoiceNo;
    }

    @JsonProperty("InvoiceNo")
    public void setInvoiceNo(Object invoiceNo) {
        this.invoiceNo = invoiceNo;
    }

    @JsonProperty("IsDeliverHome")
    public Object getIsDeliverHome() {
        return isDeliverHome;
    }

    @JsonProperty("IsDeliverHome")
    public void setIsDeliverHome(Object isDeliverHome) {
        this.isDeliverHome = isDeliverHome;
    }

    @JsonProperty("IsHandDelivery")
    public Object getIsHandDelivery() {
        return isHandDelivery;
    }

    @JsonProperty("IsHandDelivery")
    public void setIsHandDelivery(Object isHandDelivery) {
        this.isHandDelivery = isHandDelivery;
    }

    @JsonProperty("IsPickupStore")
    public Object getIsPickupStore() {
        return isPickupStore;
    }

    @JsonProperty("IsPickupStore")
    public void setIsPickupStore(Object isPickupStore) {
        this.isPickupStore = isPickupStore;
    }

    @JsonProperty("IsTotalSavingDisplay")
    public Boolean getIsTotalSavingDisplay() {
        return isTotalSavingDisplay;
    }

    @JsonProperty("IsTotalSavingDisplay")
    public void setIsTotalSavingDisplay(Boolean isTotalSavingDisplay) {
        this.isTotalSavingDisplay = isTotalSavingDisplay;
    }

    @JsonProperty("IsUberRush")
    public Object getIsUberRush() {
        return isUberRush;
    }

    @JsonProperty("IsUberRush")
    public void setIsUberRush(Object isUberRush) {
        this.isUberRush = isUberRush;
    }

    @JsonProperty("LoyaltyPoints")
    public Object getLoyaltyPoints() {
        return loyaltyPoints;
    }

    @JsonProperty("LoyaltyPoints")
    public void setLoyaltyPoints(Object loyaltyPoints) {
        this.loyaltyPoints = loyaltyPoints;
    }

    @JsonProperty("LoyaltyType")
    public Object getLoyaltyType() {
        return loyaltyType;
    }

    @JsonProperty("LoyaltyType")
    public void setLoyaltyType(Object loyaltyType) {
        this.loyaltyType = loyaltyType;
    }

    @JsonProperty("OrderDate")
    public Object getOrderDate() {
        return orderDate;
    }

    @JsonProperty("OrderDate")
    public void setOrderDate(Object orderDate) {
        this.orderDate = orderDate;
    }

    @JsonProperty("OrderFinalTotal")
    public Object getOrderFinalTotal() {
        return orderFinalTotal;
    }

    @JsonProperty("OrderFinalTotal")
    public void setOrderFinalTotal(Object orderFinalTotal) {
        this.orderFinalTotal = orderFinalTotal;
    }

    @JsonProperty("OrderID")
    public Object getOrderID() {
        return orderID;
    }

    @JsonProperty("OrderID")
    public void setOrderID(Object orderID) {
        this.orderID = orderID;
    }

    @JsonProperty("OrderReturnID")
    public Object getOrderReturnID() {
        return orderReturnID;
    }

    @JsonProperty("OrderReturnID")
    public void setOrderReturnID(Object orderReturnID) {
        this.orderReturnID = orderReturnID;
    }

    @JsonProperty("OrderStatus")
    public Object getOrderStatus() {
        return orderStatus;
    }

    @JsonProperty("OrderStatus")
    public void setOrderStatus(Object orderStatus) {
        this.orderStatus = orderStatus;
    }

    @JsonProperty("OrderSubTotal")
    public Object getOrderSubTotal() {
        return orderSubTotal;
    }

    @JsonProperty("OrderSubTotal")
    public void setOrderSubTotal(Object orderSubTotal) {
        this.orderSubTotal = orderSubTotal;
    }

    @JsonProperty("OrderTotal")
    public Object getOrderTotal() {
        return orderTotal;
    }

    @JsonProperty("OrderTotal")
    public void setOrderTotal(Object orderTotal) {
        this.orderTotal = orderTotal;
    }

    @JsonProperty("PaymentMedia")
    public Object getPaymentMedia() {
        return paymentMedia;
    }

    @JsonProperty("PaymentMedia")
    public void setPaymentMedia(Object paymentMedia) {
        this.paymentMedia = paymentMedia;
    }

    @JsonProperty("PaymentType")
    public Object getPaymentType() {
        return paymentType;
    }

    @JsonProperty("PaymentType")
    public void setPaymentType(Object paymentType) {
        this.paymentType = paymentType;
    }

    @JsonProperty("PointUsed")
    public Object getPointUsed() {
        return pointUsed;
    }

    @JsonProperty("PointUsed")
    public void setPointUsed(Object pointUsed) {
        this.pointUsed = pointUsed;
    }

    @JsonProperty("Qty")
    public Object getQty() {
        return qty;
    }

    @JsonProperty("Qty")
    public void setQty(Object qty) {
        this.qty = qty;
    }

    @JsonProperty("Result")
    public String getResult() {
        return result;
    }

    @JsonProperty("Result")
    public void setResult(String result) {
        this.result = result;
    }

    @JsonProperty("RewardDollarAvailable")
    public Object getRewardDollarAvailable() {
        return rewardDollarAvailable;
    }

    @JsonProperty("RewardDollarAvailable")
    public void setRewardDollarAvailable(Object rewardDollarAvailable) {
        this.rewardDollarAvailable = rewardDollarAvailable;
    }

    @JsonProperty("RewardDollarUsed")
    public Object getRewardDollarUsed() {
        return rewardDollarUsed;
    }

    @JsonProperty("RewardDollarUsed")
    public void setRewardDollarUsed(Object rewardDollarUsed) {
        this.rewardDollarUsed = rewardDollarUsed;
    }

    @JsonProperty("SAddress1")
    public Object getSAddress1() {
        return sAddress1;
    }

    @JsonProperty("SAddress1")
    public void setSAddress1(Object sAddress1) {
        this.sAddress1 = sAddress1;
    }

    @JsonProperty("SAddress2")
    public Object getSAddress2() {
        return sAddress2;
    }

    @JsonProperty("SAddress2")
    public void setSAddress2(Object sAddress2) {
        this.sAddress2 = sAddress2;
    }

    @JsonProperty("SCity")
    public Object getSCity() {
        return sCity;
    }

    @JsonProperty("SCity")
    public void setSCity(Object sCity) {
        this.sCity = sCity;
    }

    @JsonProperty("SFName")
    public Object getSFName() {
        return sFName;
    }

    @JsonProperty("SFName")
    public void setSFName(Object sFName) {
        this.sFName = sFName;
    }

    @JsonProperty("SLName")
    public Object getSLName() {
        return sLName;
    }

    @JsonProperty("SLName")
    public void setSLName(Object sLName) {
        this.sLName = sLName;
    }

    @JsonProperty("SPhone")
    public Object getSPhone() {
        return sPhone;
    }

    @JsonProperty("SPhone")
    public void setSPhone(Object sPhone) {
        this.sPhone = sPhone;
    }

    @JsonProperty("SState")
    public Object getSState() {
        return sState;
    }

    @JsonProperty("SState")
    public void setSState(Object sState) {
        this.sState = sState;
    }

    @JsonProperty("SZip")
    public Object getSZip() {
        return sZip;
    }

    @JsonProperty("SZip")
    public void setSZip(Object sZip) {
        this.sZip = sZip;
    }

    @JsonProperty("ShipmentMessage")
    public Object getShipmentMessage() {
        return shipmentMessage;
    }

    @JsonProperty("ShipmentMessage")
    public void setShipmentMessage(Object shipmentMessage) {
        this.shipmentMessage = shipmentMessage;
    }

    @JsonProperty("SizeFlag")
    public Object getSizeFlag() {
        return sizeFlag;
    }

    @JsonProperty("SizeFlag")
    public void setSizeFlag(Object sizeFlag) {
        this.sizeFlag = sizeFlag;
    }

    @JsonProperty("StartRecord")
    public Integer getStartRecord() {
        return startRecord;
    }

    @JsonProperty("StartRecord")
    public void setStartRecord(Integer startRecord) {
        this.startRecord = startRecord;
    }

    @JsonProperty("StoreNo")
    public Object getStoreNo() {
        return storeNo;
    }

    @JsonProperty("StoreNo")
    public void setStoreNo(Object storeNo) {
        this.storeNo = storeNo;
    }

    @JsonProperty("TaxExamptMessage")
    public Object getTaxExamptMessage() {
        return taxExamptMessage;
    }

    @JsonProperty("TaxExamptMessage")
    public void setTaxExamptMessage(Object taxExamptMessage) {
        this.taxExamptMessage = taxExamptMessage;
    }

    @JsonProperty("TaxExamptNo")
    public Object getTaxExamptNo() {
        return taxExamptNo;
    }

    @JsonProperty("TaxExamptNo")
    public void setTaxExamptNo(Object taxExamptNo) {
        this.taxExamptNo = taxExamptNo;
    }

    @JsonProperty("TipValue")
    public Object getTipValue() {
        return tipValue;
    }

    @JsonProperty("TipValue")
    public void setTipValue(Object tipValue) {
        this.tipValue = tipValue;
    }

    @JsonProperty("TotalItem")
    public Object getTotalItem() {
        return totalItem;
    }

    @JsonProperty("TotalItem")
    public void setTotalItem(Object totalItem) {
        this.totalItem = totalItem;
    }

    @JsonProperty("TotalRecord")
    public Integer getTotalRecord() {
        return totalRecord;
    }

    @JsonProperty("TotalRecord")
    public void setTotalRecord(Integer totalRecord) {
        this.totalRecord = totalRecord;
    }

    @JsonProperty("TotalSaving")
    public Object getTotalSaving() {
        return totalSaving;
    }

    @JsonProperty("TotalSaving")
    public void setTotalSaving(Object totalSaving) {
        this.totalSaving = totalSaving;
    }

    @JsonProperty("isShipTaxToOtherCountry")
    public Boolean getIsShipTaxToOtherCountry() {
        return isShipTaxToOtherCountry;
    }

    @JsonProperty("isShipTaxToOtherCountry")
    public void setIsShipTaxToOtherCountry(Boolean isShipTaxToOtherCountry) {
        this.isShipTaxToOtherCountry = isShipTaxToOtherCountry;
    }

    @JsonProperty("lstOrderTems")
    public Object getLstOrderTems() {
        return lstOrderTems;
    }

    @JsonProperty("lstOrderTems")
    public void setLstOrderTems(Object lstOrderTems) {
        this.lstOrderTems = lstOrderTems;
    }

    @JsonProperty("page_count")
    public Integer getPageCount() {
        return pageCount;
    }

    @JsonProperty("page_count")
    public void setPageCount(Integer pageCount) {
        this.pageCount = pageCount;
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