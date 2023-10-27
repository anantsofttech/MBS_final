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
public class InsertOrderDetailed {

    @JsonProperty("BAddress1")
    private String bAddress1;
    @JsonProperty("BAddress2")
    private String bAddress2;
    @JsonProperty("BCity")
    private String bCity;
    @JsonProperty("BFName")
    private String bFName;
    @JsonProperty("BLName")
    private String bLName;
    @JsonProperty("BPhone")
    private String bPhone;
    @JsonProperty("BState")
    private String bState;
    @JsonProperty("BZip")
    private String bZip;
    @JsonProperty("CustomerID")
    private String customerID;
    @JsonProperty("CustomerName")
    private String customerName;
    @JsonProperty("EndRecord")
    private Integer endRecord;
    @JsonProperty("FlgtmpCashTip")
    private Boolean flgtmpCashTip;
    @JsonProperty("ID")
    private String iD;
    @JsonProperty("InvoiceNo")
    private String invoiceNo;
    @JsonProperty("IsDeliverHome")
    private String isDeliverHome;
    @JsonProperty("IsHandDelivery")
    private String isHandDelivery;
    @JsonProperty("IsPickupStore")
    private String isPickupStore;
    @JsonProperty("IsTotalSavingDisplay")
    private Boolean isTotalSavingDisplay;
    @JsonProperty("IsUberRush")
    private String isUberRush;
    @JsonProperty("LoyaltyPoints")
    private String loyaltyPoints;
    @JsonProperty("LoyaltyType")
    private String loyaltyType;
    @JsonProperty("OrderDate")
    private String orderDate;
    @JsonProperty("OrderFinalTotal")
    private String orderFinalTotal;
    @JsonProperty("OrderID")
    private String orderID;
    @JsonProperty("OrderStatus")
    private String orderStatus;
    @JsonProperty("OrderSubTotal")
    private String orderSubTotal;
    @JsonProperty("OrderTotal")
    private String orderTotal;
    @JsonProperty("PaymentMedia")
    private String paymentMedia;
    @JsonProperty("PaymentType")
    private String paymentType;
    @JsonProperty("PointUsed")
    private String pointUsed;
    @JsonProperty("Qty")
    private String qty;
    @JsonProperty("Result")
    private String result;
    @JsonProperty("RewardDollarAvailable")
    private String rewardDollarAvailable;
    @JsonProperty("RewardDollarUsed")
    private String rewardDollarUsed;
    @JsonProperty("SAddress1")
    private String sAddress1;
    @JsonProperty("SAddress2")
    private String sAddress2;
    @JsonProperty("SCity")
    private String sCity;
    @JsonProperty("SFName")
    private String sFName;
    @JsonProperty("SLName")
    private String sLName;
    @JsonProperty("SPhone")
    private String sPhone;
    @JsonProperty("SState")
    private String sState;
    @JsonProperty("SZip")
    private String sZip;
    @JsonProperty("ShipmentMessage")
    private String shipmentMessage;
    @JsonProperty("SizeFlag")
    private String sizeFlag;
    @JsonProperty("StartRecord")
    private Integer startRecord;
    @JsonProperty("StoreNo")
    private String storeNo;
    @JsonProperty("TaxExamptMessage")
    private String taxExamptMessage;
    @JsonProperty("TaxExamptNo")
    private String taxExamptNo;
    @JsonProperty("TipValue")
    private String tipValue;
    @JsonProperty("TotalItem")
    private String totalItem;
    @JsonProperty("TotalRecord")
    private Integer totalRecord;
    @JsonProperty("TotalSaving")
    private String totalSaving;
    @JsonProperty("isShipTaxToOtherCountry")
    private Boolean isShipTaxToOtherCountry;
    @JsonProperty("lstOrderTems")
    private String lstOrderTems;
    @JsonProperty("page_count")
    private Integer pageCount;
    @JsonIgnore
    private Map<String, String> additionalProperties = new HashMap<String, String>();

    @JsonProperty("BAddress1")
    public String getBAddress1() {
        return (bAddress1==null) ? "" :bAddress1;
    }

    @JsonProperty("BAddress1")
    public void setBAddress1(String bAddress1) {
        this.bAddress1 = bAddress1;
    }

    @JsonProperty("BAddress2")
    public String getBAddress2() {
        return (bAddress2 == null) ? "" : bAddress2 ;
    }

    @JsonProperty("BAddress2")
    public void setBAddress2(String bAddress2) {
        this.bAddress2 = bAddress2;
    }

    @JsonProperty("BCity")
    public String getBCity() {
        return (bCity == null) ? "" : bCity ;
    }

    @JsonProperty("BCity")
    public void setBCity(String bCity) {
        this.bCity = bCity;
    }

    @JsonProperty("BFName")
    public String getBFName() {
        return (bFName == null) ? "" : bFName ;
    }

    @JsonProperty("BFName")
    public void setBFName(String bFName) {
        this.bFName = bFName;
    }

    @JsonProperty("BLName")
    public String getBLName() {
        return (bLName == null) ? "" : bLName ;
    }

    @JsonProperty("BLName")
    public void setBLName(String bLName) {
        this.bLName = bLName;
    }

    @JsonProperty("BPhone")
    public String getBPhone() {
        return (bPhone == null) ? "" : bPhone ;
    }

    @JsonProperty("BPhone")
    public void setBPhone(String bPhone) {
        this.bPhone = bPhone;
    }

    @JsonProperty("BState")
    public String getBState() {
        return (bState == null) ? "" : bState ;
    }

    @JsonProperty("BState")
    public void setBState(String bState) {
        this.bState = bState;
    }

    @JsonProperty("BZip")
    public String getBZip() {
        return (bZip == null) ? "" : bZip ;
    }

    @JsonProperty("BZip")
    public void setBZip(String bZip) {
        this.bZip = bZip;
    }

    @JsonProperty("CustomerID")
    public String getCustomerID() {
        return (customerID == null) ? "" : customerID ;
    }

    @JsonProperty("CustomerID")
    public void setCustomerID(String customerID) {
        this.customerID = customerID;
    }

    @JsonProperty("CustomerName")
    public String getCustomerName() {
        return (customerName == null) ? "" : customerName ;
    }

    @JsonProperty("CustomerName")
    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    @JsonProperty("EndRecord")
    public Integer getEndRecord() {
        return (endRecord == null) ? 0 : endRecord ;
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
    public String getID() {
        return (iD == null) ? "" : iD ;
    }

    @JsonProperty("ID")
    public void setID(String iD) {
        this.iD = iD;
    }

    @JsonProperty("InvoiceNo")
    public String getInvoiceNo() {
        return (invoiceNo == null) ? "" : invoiceNo ;
    }

    @JsonProperty("InvoiceNo")
    public void setInvoiceNo(String invoiceNo) {
        this.invoiceNo = invoiceNo;
    }

    @JsonProperty("IsDeliverHome")
    public String getIsDeliverHome() {
        return (isDeliverHome == null) ? "" : isDeliverHome ;
    }

    @JsonProperty("IsDeliverHome")
    public void setIsDeliverHome(String isDeliverHome) {
        this.isDeliverHome = isDeliverHome;
    }

    @JsonProperty("IsHandDelivery")
    public String getIsHandDelivery() {
        return (isHandDelivery == null) ? "" : isHandDelivery ;
    }

    @JsonProperty("IsHandDelivery")
    public void setIsHandDelivery(String isHandDelivery) {
        this.isHandDelivery = isHandDelivery;
    }

    @JsonProperty("IsPickupStore")
    public String getIsPickupStore() {
        return (isPickupStore == null) ? "" : isPickupStore ;
    }

    @JsonProperty("IsPickupStore")
    public void setIsPickupStore(String isPickupStore) {
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
    public String getIsUberRush() {
        return (isUberRush == null) ? "" : isUberRush ;
    }

    @JsonProperty("IsUberRush")
    public void setIsUberRush(String isUberRush) {
        this.isUberRush = isUberRush;
    }

    @JsonProperty("LoyaltyPoints")
    public String getLoyaltyPoints() {
        return (loyaltyPoints == null) ? "" : loyaltyPoints ;
    }

    @JsonProperty("LoyaltyPoints")
    public void setLoyaltyPoints(String loyaltyPoints) {
        this.loyaltyPoints = loyaltyPoints;
    }

    @JsonProperty("LoyaltyType")
    public String getLoyaltyType() {
        return (loyaltyType == null) ? "" : loyaltyType ;
    }

    @JsonProperty("LoyaltyType")
    public void setLoyaltyType(String loyaltyType) {
        this.loyaltyType = loyaltyType;
    }

    @JsonProperty("OrderDate")
    public String getOrderDate() {
        return (orderDate == null) ? "" : orderDate ;
    }

    @JsonProperty("OrderDate")
    public void setOrderDate(String orderDate) {
        this.orderDate = orderDate;
    }

    @JsonProperty("OrderFinalTotal")
    public String getOrderFinalTotal() {
        return (orderFinalTotal == null) ? "" : orderFinalTotal ;
    }

    @JsonProperty("OrderFinalTotal")
    public void setOrderFinalTotal(String orderFinalTotal) {
        this.orderFinalTotal = orderFinalTotal;
    }

    @JsonProperty("OrderID")
    public String getOrderID() {
        return (orderID == null) ? "" : orderID ;
    }

    @JsonProperty("OrderID")
    public void setOrderID(String orderID) {
        this.orderID = orderID;
    }

    @JsonProperty("OrderStatus")
    public String getOrderStatus() {
        return (orderStatus == null) ? "" : orderStatus ;
    }

    @JsonProperty("OrderStatus")
    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }

    @JsonProperty("OrderSubTotal")
    public String getOrderSubTotal() {
        return (orderSubTotal == null) ? "" : orderSubTotal ;
    }

    @JsonProperty("OrderSubTotal")
    public void setOrderSubTotal(String orderSubTotal) {
        this.orderSubTotal = orderSubTotal;
    }

    @JsonProperty("OrderTotal")
    public String getOrderTotal() {
        return (orderTotal == null) ? "" : orderTotal ;
    }

    @JsonProperty("OrderTotal")
    public void setOrderTotal(String orderTotal) {
        this.orderTotal = orderTotal;
    }

    @JsonProperty("PaymentMedia")
    public String getPaymentMedia() {
        return (paymentMedia == null) ? "" : paymentMedia ;
    }

    @JsonProperty("PaymentMedia")
    public void setPaymentMedia(String paymentMedia) {
        this.paymentMedia = paymentMedia;
    }

    @JsonProperty("PaymentType")
    public String getPaymentType() {
        return (paymentType == null) ? "" : paymentType ;
    }

    @JsonProperty("PaymentType")
    public void setPaymentType(String paymentType) {
        this.paymentType = paymentType;
    }

    @JsonProperty("PointUsed")
    public String getPointUsed() {
        return (pointUsed == null) ? "" : pointUsed ;
    }

    @JsonProperty("PointUsed")
    public void setPointUsed(String pointUsed) {
        this.pointUsed = pointUsed;
    }

    @JsonProperty("Qty")
    public String getQty() {
        return (qty == null) ? "" : qty ;
    }

    @JsonProperty("Qty")
    public void setQty(String qty) {
        this.qty = qty;
    }

    @JsonProperty("Result")
    public String getResult() {
        return (result == null) ? "" : result ;
    }

    @JsonProperty("Result")
    public void setResult(String result) {
        this.result = result;
    }

    @JsonProperty("RewardDollarAvailable")
    public String getRewardDollarAvailable() {
        return (rewardDollarAvailable == null) ? "" : rewardDollarAvailable ;
    }

    @JsonProperty("RewardDollarAvailable")
    public void setRewardDollarAvailable(String rewardDollarAvailable) {
        this.rewardDollarAvailable = rewardDollarAvailable;
    }

    @JsonProperty("RewardDollarUsed")
    public String getRewardDollarUsed() {
        return (rewardDollarUsed == null) ? "" : rewardDollarUsed ;
    }

    @JsonProperty("RewardDollarUsed")
    public void setRewardDollarUsed(String rewardDollarUsed) {
        this.rewardDollarUsed = rewardDollarUsed;
    }

    @JsonProperty("SAddress1")
    public String getSAddress1() {
        return (sAddress1 == null) ? "" : sAddress1 ;
    }

    @JsonProperty("SAddress1")
    public void setSAddress1(String sAddress1) {
        this.sAddress1 = sAddress1;
    }

    @JsonProperty("SAddress2")
    public String getSAddress2() {
        return (sAddress2 == null) ? "" : sAddress2 ;
    }

    @JsonProperty("SAddress2")
    public void setSAddress2(String sAddress2) {
        this.sAddress2 = sAddress2;
    }

    @JsonProperty("SCity")
    public String getSCity() {
        return (sCity == null) ? "" : sCity ;
    }

    @JsonProperty("SCity")
    public void setSCity(String sCity) {
        this.sCity = sCity;
    }

    @JsonProperty("SFName")
    public String getSFName() {
        return (sFName == null) ? "" : sFName ;
    }

    @JsonProperty("SFName")
    public void setSFName(String sFName) {
        this.sFName = sFName;
    }

    @JsonProperty("SLName")
    public String getSLName() {
        return (sLName == null) ? "" : sLName ;
    }

    @JsonProperty("SLName")
    public void setSLName(String sLName) {
        this.sLName = sLName;
    }

    @JsonProperty("SPhone")
    public String getSPhone() {
        return (sPhone == null) ? "" : sPhone ;
    }

    @JsonProperty("SPhone")
    public void setSPhone(String sPhone) {
        this.sPhone = sPhone;
    }

    @JsonProperty("SState")
    public String getSState() {
        return (sState == null) ? "" : sState ;
    }

    @JsonProperty("SState")
    public void setSState(String sState) {
        this.sState = sState;
    }

    @JsonProperty("SZip")
    public String getSZip() {
        return (sZip == null) ? "" : sZip ;
    }

    @JsonProperty("SZip")
    public void setSZip(String sZip) {
        this.sZip = sZip;
    }

    @JsonProperty("ShipmentMessage")
    public String getShipmentMessage() {
        return (shipmentMessage == null) ? "" : shipmentMessage ;
    }

    @JsonProperty("ShipmentMessage")
    public void setShipmentMessage(String shipmentMessage) {
        this.shipmentMessage = shipmentMessage;
    }

    @JsonProperty("SizeFlag")
    public String getSizeFlag() {
        return (sizeFlag == null) ? "" : sizeFlag ;
    }

    @JsonProperty("SizeFlag")
    public void setSizeFlag(String sizeFlag) {
        this.sizeFlag = sizeFlag;
    }

    @JsonProperty("StartRecord")
    public Integer getStartRecord() {
        return (startRecord == null) ? 0 : startRecord ;
    }

    @JsonProperty("StartRecord")
    public void setStartRecord(Integer startRecord) {
        this.startRecord = startRecord;
    }

    @JsonProperty("StoreNo")
    public String getStoreNo() {
        return (storeNo == null) ? "" : storeNo ;
    }

    @JsonProperty("StoreNo")
    public void setStoreNo(String storeNo) {
        this.storeNo = storeNo;
    }

    @JsonProperty("TaxExamptMessage")
    public String getTaxExamptMessage() {
        return (taxExamptMessage == null) ? "" : taxExamptMessage ;
    }

    @JsonProperty("TaxExamptMessage")
    public void setTaxExamptMessage(String taxExamptMessage) {
        this.taxExamptMessage = taxExamptMessage;
    }

    @JsonProperty("TaxExamptNo")
    public String getTaxExamptNo() {
        return (taxExamptNo == null) ? "" : taxExamptNo ;
    }

    @JsonProperty("TaxExamptNo")
    public void setTaxExamptNo(String taxExamptNo) {
        this.taxExamptNo = taxExamptNo;
    }

    @JsonProperty("TipValue")
    public String getTipValue() {
        return (tipValue == null) ? "" : tipValue ;
    }

    @JsonProperty("TipValue")
    public void setTipValue(String tipValue) {
        this.tipValue = tipValue;
    }

    @JsonProperty("TotalItem")
    public String getTotalItem() {
        return (totalItem == null) ? "" : totalItem ;
    }

    @JsonProperty("TotalItem")
    public void setTotalItem(String totalItem) {
        this.totalItem = totalItem;
    }

    @JsonProperty("TotalRecord")
    public Integer getTotalRecord() {
        return (totalRecord == null) ? 0 : totalRecord ;
    }

    @JsonProperty("TotalRecord")
    public void setTotalRecord(Integer totalRecord) {
        this.totalRecord = totalRecord;
    }

    @JsonProperty("TotalSaving")
    public String getTotalSaving() {
        return (totalSaving == null) ? "" : totalSaving ;
    }

    @JsonProperty("TotalSaving")
    public void setTotalSaving(String totalSaving) {
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
    public String getLstOrderTems() {
        return (lstOrderTems == null) ? "" : lstOrderTems ;
    }

    @JsonProperty("lstOrderTems")
    public void setLstOrderTems(String lstOrderTems) {
        this.lstOrderTems = lstOrderTems;
    }

    @JsonProperty("page_count")
    public Integer getPageCount() {
        return (pageCount == null) ? 0 : pageCount ;
    }

    @JsonProperty("page_count")
    public void setPageCount(Integer pageCount) {
        this.pageCount = pageCount;
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