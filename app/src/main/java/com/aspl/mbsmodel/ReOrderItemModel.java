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
        "BAddress1",
        "BAddress2",
        "BCity",
        "BFName",
        "BLName",
        "BPhone",
        "BState",
        "BZip",
        "Comments",
        "CustomerID",
        "CustomerName",
        "EndRecord",
        "FlgtmpCashTip",
        "ID",
        "InventoryQuantity",
        "InvoiceNo",
        "IsDeliverHome",
        "IsHandDelivery",
        "IsLoyaltyRewardEnable",
        "IsPickupStore",
        "IsTotalSavingDisplay",
        "IsUberRush",
        "ItemID",
        "ItemName",
        "LoyaltyPoints",
        "LoyaltyType",
        "NotDisplayOnWebsite",
        "Notes",
        "OrderDate",
        "OrderFinalTotal",
        "OrderID",
        "OrderReturnID",
        "OrderStatus",
        "OrderSubTotal",
        "OrderTotal",
        "PaymentMedia",
        "PaymentType",
        "PickupTime",
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
        "SN",
        "SPhone",
        "SState",
        "SZip",
        "ShipmentMessage",
        "ShowItemButoutofStock",
        "SizeFlag",
        "StartRecord",
        "StoreNo",
        "TaxExamptMessage",
        "TaxExamptNo",
        "TipValue",
        "TotalItem",
        "TotalRecord",
        "TotalSaving",
        "WebsiteName",
        "errorresult",
        "isShipTaxToOtherCountry",
        "lstOrderTems",
        "page_count"
})
public class ReOrderItemModel {

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
    @JsonProperty("Comments")
    private Object comments;
    @JsonProperty("CustomerID")
    private String customerID;
    @JsonProperty("CustomerName")
    private Object customerName;
    @JsonProperty("EndRecord")
    private Integer endRecord;
    @JsonProperty("FlgtmpCashTip")
    private Boolean flgtmpCashTip;
    @JsonProperty("ID")
    private String iD;
    @JsonProperty("InventoryQuantity")
    private String inventoryQuantity;
    @JsonProperty("InvoiceNo")
    private Object invoiceNo;
    @JsonProperty("IsDeliverHome")
    private Object isDeliverHome;
    @JsonProperty("IsHandDelivery")
    private Object isHandDelivery;
    @JsonProperty("IsLoyaltyRewardEnable")
    private Object isLoyaltyRewardEnable;
    @JsonProperty("IsPickupStore")
    private Object isPickupStore;
    @JsonProperty("IsTotalSavingDisplay")
    private Boolean isTotalSavingDisplay;
    @JsonProperty("IsUberRush")
    private Object isUberRush;
    @JsonProperty("ItemID")
    private String itemID;
    @JsonProperty("ItemName")
    private String itemName;
    @JsonProperty("LoyaltyPoints")
    private Object loyaltyPoints;
    @JsonProperty("LoyaltyType")
    private Object loyaltyType;
    @JsonProperty("NotDisplayOnWebsite")
    private String notDisplayOnWebsite;
    @JsonProperty("Notes")
    private Object notes;
    @JsonProperty("OrderDate")
    private String orderDate;
    @JsonProperty("OrderFinalTotal")
    private Object orderFinalTotal;
    @JsonProperty("OrderID")
    private String orderID;
    @JsonProperty("OrderReturnID")
    private Object orderReturnID;
    @JsonProperty("OrderStatus")
    private String orderStatus;
    @JsonProperty("OrderSubTotal")
    private Object orderSubTotal;
    @JsonProperty("OrderTotal")
    private String orderTotal;
    @JsonProperty("PaymentMedia")
    private Object paymentMedia;
    @JsonProperty("PaymentType")
    private Object paymentType;
    @JsonProperty("PickupTime")
    private String PickupTime;
    @JsonProperty("PointUsed")
    private Object pointUsed;
    @JsonProperty("Qty")
    private String qty;
    @JsonProperty("Result")
    private Object result;
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
    @JsonProperty("SN")
    private String sN;
    @JsonProperty("SPhone")
    private Object sPhone;
    @JsonProperty("SState")
    private Object sState;
    @JsonProperty("SZip")
    private Object sZip;
    @JsonProperty("ShipmentMessage")
    private Object shipmentMessage;
    @JsonProperty("ShowItemButoutofStock")
    private String showItemButoutofStock;
    @JsonProperty("SizeFlag")
    private String sizeFlag;
    @JsonProperty("StartRecord")
    private Integer startRecord;
    @JsonProperty("StoreNo")
    private String storeNo;
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
    @JsonProperty("WebsiteName")
    private Object websiteName;
    @JsonProperty("errorresult")
    private Object errorresult;
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

    @JsonProperty("Comments")
    public Object getComments() {
        return comments;
    }

    @JsonProperty("Comments")
    public void setComments(Object comments) {
        this.comments = comments;
    }

    @JsonProperty("CustomerID")
    public String getCustomerID() {
        return customerID;
    }

    @JsonProperty("CustomerID")
    public void setCustomerID(String customerID) {
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
    public String  getID() {
        return iD;
    }

    @JsonProperty("ID")
    public void setID(String iD) {
        this.iD = iD;
    }

    @JsonProperty("InventoryQuantity")
    public String getInventoryQuantity() {
        return inventoryQuantity;
    }

    @JsonProperty("InventoryQuantity")
    public void setInventoryQuantity(String inventoryQuantity) {
        this.inventoryQuantity = inventoryQuantity;
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

    @JsonProperty("IsLoyaltyRewardEnable")
    public Object getIsLoyaltyRewardEnable() {
        return isLoyaltyRewardEnable;
    }

    @JsonProperty("IsLoyaltyRewardEnable")
    public void setIsLoyaltyRewardEnable(Object isLoyaltyRewardEnable) {
        this.isLoyaltyRewardEnable = isLoyaltyRewardEnable;
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

    @JsonProperty("ItemID")
    public String getItemID() {
        return itemID;
    }

    @JsonProperty("ItemID")
    public void setItemID(String itemID) {
        this.itemID = itemID;
    }

    @JsonProperty("ItemName")
    public String getItemName() {
        return itemName;
    }

    @JsonProperty("ItemName")
    public void setItemName(String itemName) {
        this.itemName = itemName;
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

    @JsonProperty("NotDisplayOnWebsite")
    public String getNotDisplayOnWebsite() {
        return notDisplayOnWebsite;
    }

    @JsonProperty("NotDisplayOnWebsite")
    public void setNotDisplayOnWebsite(String notDisplayOnWebsite) {
        this.notDisplayOnWebsite = notDisplayOnWebsite;
    }

    @JsonProperty("Notes")
    public Object getNotes() {
        return notes;
    }

    @JsonProperty("Notes")
    public void setNotes(Object notes) {
        this.notes = notes;
    }

    @JsonProperty("OrderDate")
    public String getOrderDate() {
        return orderDate;
    }

    @JsonProperty("OrderDate")
    public void setOrderDate(String orderDate) {
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
    public String getOrderID() {
        return orderID;
    }

    @JsonProperty("OrderID")
    public void setOrderID(String orderID) {
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
    public String getOrderStatus() {
        return orderStatus;
    }

    @JsonProperty("OrderStatus")
    public void setOrderStatus(String orderStatus) {
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
    public String getOrderTotal() {
        return orderTotal;
    }

    @JsonProperty("OrderTotal")
    public void setOrderTotal(String orderTotal) {
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

    @JsonProperty("PickupTime")
    public String getPickupTime() {
        return PickupTime;
    }

    @JsonProperty("PickupTime")
    public void setPickupTime(String pickupTime) {
        this.PickupTime = pickupTime;
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
    public String getQty() {
        return qty;
    }

    @JsonProperty("Qty")
    public void setQty(String qty) {
        this.qty = qty;
    }

    @JsonProperty("Result")
    public Object getResult() {
        return result;
    }

    @JsonProperty("Result")
    public void setResult(Object result) {
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

    @JsonProperty("SN")
    public String getSN() {
        return sN;
    }

    @JsonProperty("SN")
    public void setSN(String sN) {
        this.sN = sN;
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

    @JsonProperty("ShowItemButoutofStock")
    public String getShowItemButoutofStock() {
        return showItemButoutofStock;
    }

    @JsonProperty("ShowItemButoutofStock")
    public void setShowItemButoutofStock(String showItemButoutofStock) {
        this.showItemButoutofStock = showItemButoutofStock;
    }

    @JsonProperty("SizeFlag")
    public String getSizeFlag() {
        return sizeFlag;
    }

    @JsonProperty("SizeFlag")
    public void setSizeFlag(String sizeFlag) {
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
    public String getStoreNo() {
        return storeNo;
    }

    @JsonProperty("StoreNo")
    public void setStoreNo(String storeNo) {
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

    @JsonProperty("WebsiteName")
    public Object getWebsiteName() {
        return websiteName;
    }

    @JsonProperty("WebsiteName")
    public void setWebsiteName(Object websiteName) {
        this.websiteName = websiteName;
    }

    @JsonProperty("errorresult")
    public Object getErrorresult() {
        return errorresult;
    }

    @JsonProperty("errorresult")
    public void setErrorresult(Object errorresult) {
        this.errorresult = errorresult;
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