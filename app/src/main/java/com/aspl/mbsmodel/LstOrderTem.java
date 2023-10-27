package com.aspl.mbsmodel;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "Comments",
        "FlatName",
        "GiftWrap",
        "GiftWrapSetup",
        "InvLargeImage",
        "InvSmallImage",
        "IsMiscTax",
        "IsSalesTax",
        "IsWineTax",
        "Isflat",
        "ItemID",
        "ItemName",
        "ItemPrice",
        "MiscTax3",
        "OrderID",
        "OrderItemDate",
        "OrderItemStatus",
        "Points",
        "Price",
        "Qty",
        "ReturnDate",
        "ReturnReason",
        "SalesTax1",
        "Tax1Name",
        "Tax2Name",
        "Tax3Name",
        "WineTax2",
        "bottledeposit",
        "flat",
        "size",
        "txtDataValue",
        "lstReturnItems",
        "InvLargeImageFullPath",
        "InvSmallImageFullPath",
})
public class LstOrderTem {

    @JsonProperty("InvSmallImageFullPath")
    private String InvSmallImageFullPath;
    @JsonProperty("InvLargeImageFullPath")
    private String InvLargeImageFullPath;
    @JsonProperty("Comments")
    private String Comments;
    @JsonProperty("FlatName")
    private String flatName;
    @JsonProperty("GiftWrap")
    private Boolean giftWrap;
    @JsonProperty("GiftWrapSetup")
    private Boolean giftWrapSetup;
    @JsonProperty("InvLargeImage")
    private String InvLargeImage;
    @JsonProperty("InvSmallImage")
    private String InvSmallImage;
    @JsonProperty("IsMiscTax")
    private Boolean isMiscTax;
    @JsonProperty("IsSalesTax")
    private Boolean isSalesTax;
    @JsonProperty("IsWineTax")
    private Boolean isWineTax;
    @JsonProperty("Isflat")
    private Boolean isflat;
    @JsonProperty("ItemID")
    private String ItemID;
    @JsonProperty("ItemName")
    private String itemName;
    @JsonProperty("ItemPrice")
    private String itemPrice;
    @JsonProperty("MiscTax3")
    private String miscTax3;
    @JsonProperty("OrderID")
    private String orderID;
    @JsonProperty("OrderItemDate")
    private String OrderItemDate;
    @JsonProperty("OrderItemStatus")
    private String OrderItemStatus;
    @JsonProperty("Points")
    private String points;
    @JsonProperty("Price")
    private String price;
    @JsonProperty("Qty")
    private String qty;
    @JsonProperty("ReturnDate")
    private String returnDate;
    @JsonProperty("ReturnReason")
    private String ReturnReason;
    @JsonProperty("SalesTax1")
    private String salesTax1;
    @JsonProperty("Tax1Name")
    private String tax1Name;
    @JsonProperty("Tax2Name")
    private String tax2Name;
    @JsonProperty("Tax3Name")
    private String tax3Name;
    @JsonProperty("WineTax2")
    private String wineTax2;
    @JsonProperty("bottledeposit")
    private String bottledeposit;
    @JsonProperty("flat")
    private String flat;
    @JsonProperty("size")
    private String size;
    @JsonProperty("txtDataValue")
    private String txtDataValue;
    @JsonProperty("lstReturnItems")
    private List<LstReturnItems> lstReturnItems = null;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    public String getComments() {
        return Comments;
    }

    public LstOrderTem setComments(String comments) {
        Comments = comments;
        return this;
    }

    @JsonProperty("FlatName")
    public String getFlatName() {
        return (flatName == null) ? "" : flatName ;
    }

    @JsonProperty("FlatName")
    public void setFlatName(String flatName) {
        this.flatName = flatName;
    }

    @JsonProperty("GiftWrap")
    public Boolean getGiftWrap() {
        return giftWrap;
    }

    @JsonProperty("GiftWrap")
    public void setGiftWrap(Boolean giftWrap) {
        this.giftWrap = giftWrap;
    }

    @JsonProperty("GiftWrapSetup")
    public Boolean getGiftWrapSetup() {
        return giftWrapSetup;
    }

    @JsonProperty("GiftWrapSetup")
    public void setGiftWrapSetup(Boolean giftWrapSetup) {
        this.giftWrapSetup = giftWrapSetup;
    }


    @JsonProperty("InvLargeImage")
    public String getInvLargeImage() {
        return InvLargeImage;
    }

    @JsonProperty("InvLargeImage")
    public void setInvLargeImage(String InvLargeImage) {
        this.InvLargeImage = InvLargeImage;
    }


    @JsonProperty("InvSmallImage")
    public String getInvSmallImage() {
        return InvSmallImage;
    }

    @JsonProperty("InvSmallImage")
    public void setInvSmallImage(String InvSmallImage) {
        this.InvSmallImage = InvSmallImage;
    }

    @JsonProperty("IsMiscTax")
    public Boolean getIsMiscTax() {
        return isMiscTax;
    }

    @JsonProperty("IsMiscTax")
    public void setIsMiscTax(Boolean isMiscTax) {
        this.isMiscTax = isMiscTax;
    }

    @JsonProperty("IsSalesTax")
    public Boolean getIsSalesTax() {
        return isSalesTax;
    }

    @JsonProperty("IsSalesTax")
    public void setIsSalesTax(Boolean isSalesTax) {
        this.isSalesTax = isSalesTax;
    }

    @JsonProperty("IsWineTax")
    public Boolean getIsWineTax() {
        return isWineTax;
    }

    @JsonProperty("IsWineTax")
    public void setIsWineTax(Boolean isWineTax) {
        this.isWineTax = isWineTax;
    }

    @JsonProperty("Isflat")
    public Boolean getIsflat() {
        return isflat;
    }

    @JsonProperty("Isflat")
    public void setIsflat(Boolean isflat) {
        this.isflat = isflat;
    }


    @JsonProperty("ItemID")
    public String getItemID() {
        return (ItemID == null) ? "" : ItemID ;
    }

    @JsonProperty("ItemID")
    public void setItemID(String ItemID) {
        this.ItemID = ItemID;
    }

    @JsonProperty("ItemName")
    public String getItemName() {
        return (itemName == null) ? "" : itemName ;
    }

    @JsonProperty("ItemName")
    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    @JsonProperty("ItemPrice")
    public String getItemPrice() {
        return (itemPrice == null) ? "" : itemPrice ;
    }

    @JsonProperty("ItemPrice")
    public void setItemPrice(String itemPrice) {
        this.itemPrice = itemPrice;
    }

    @JsonProperty("MiscTax3")
    public String getMiscTax3() {
        return (miscTax3 == null) ? "" : miscTax3 ;
    }

    @JsonProperty("MiscTax3")
    public void setMiscTax3(String miscTax3) {
        this.miscTax3 = miscTax3;
    }

    @JsonProperty("OrderID")
    public String getOrderID() {
        return (orderID == null) ? "" : orderID ;
    }

    @JsonProperty("OrderID")
    public void setOrderID(String orderID) {
        this.orderID = orderID;
    }

    @JsonProperty("OrderItemDate")
    public String getOrderItemDate() {
        return (OrderItemDate == null) ? "" : OrderItemDate ;
    }

    @JsonProperty("OrderItemDate")
    public void setOrderItemDate(String OrderItemDate) {
        this.OrderItemDate = OrderItemDate;
    }

    @JsonProperty("OrderItemStatus")
    public String getOrderItemStatus() {
        return (OrderItemStatus == null) ? "" : OrderItemStatus;
    }

    @JsonProperty("OrderItemStatus")
    public void setOrderItemStatus(String OrderItemStatus) {
        this.OrderItemStatus = OrderItemStatus;
    }

    @JsonProperty("Points")
    public String getPoints() {
        return (points == null) ? "" : points ;
    }

    @JsonProperty("Points")
    public void setPoints(String points) {
        this.points = points;
    }

    @JsonProperty("Price")
    public String getPrice() {
        return (price == null) ? "" : price ;
    }

    @JsonProperty("Price")
    public void setPrice(String price) {
        this.price = price;
    }

    @JsonProperty("Qty")
    public String getQty() {
        return (qty == null) ? "" : qty ;
    }

    @JsonProperty("Qty")
    public void setQty(String qty) {
        this.qty = qty;
    }

    @JsonProperty("ReturnDate")
    public String getReturnDate() {
        return  (returnDate == null) ? "" : returnDate ;
    }

    @JsonProperty("ReturnDate")
    public void setReturnDate(String returnDate) {
        this.returnDate = returnDate;
    }

    public String getReturnReason() {
        return ReturnReason;
    }

    public LstOrderTem setReturnReason(String returnReason) {
        ReturnReason = returnReason;
        return this;
    }

    @JsonProperty("SalesTax1")
    public String getSalesTax1() {
        return (salesTax1 == null) ? "" : salesTax1 ;
    }

    @JsonProperty("SalesTax1")
    public void setSalesTax1(String salesTax1) {
        this.salesTax1 = salesTax1;
    }

    @JsonProperty("Tax1Name")
    public String getTax1Name() {
        return (tax1Name == null) ? "" : tax1Name ;
    }

    @JsonProperty("Tax1Name")
    public void setTax1Name(String tax1Name) {
        this.tax1Name = tax1Name;
    }

    @JsonProperty("Tax2Name")
    public String getTax2Name() {
        return (tax2Name == null) ? "" : tax2Name ;
    }

    @JsonProperty("Tax2Name")
    public void setTax2Name(String tax2Name) {
        this.tax2Name = tax2Name;
    }

    @JsonProperty("Tax3Name")
    public String getTax3Name() {
        return (tax3Name == null) ? "" : tax3Name ;
    }

    @JsonProperty("Tax3Name")
    public void setTax3Name(String tax3Name) {
        this.tax3Name = tax3Name;
    }

    @JsonProperty("WineTax2")
    public String getWineTax2() {
        return (wineTax2 == null) ? "" : wineTax2 ;
    }

    @JsonProperty("WineTax2")
    public void setWineTax2(String wineTax2) {
        this.wineTax2 = wineTax2;
    }

    @JsonProperty("bottledeposit")
    public String getBottledeposit() {
        return (bottledeposit == null) ? "" : bottledeposit ;
    }

    @JsonProperty("bottledeposit")
    public void setBottledeposit(String bottledeposit) {
        this.bottledeposit = bottledeposit;
    }

    @JsonProperty("flat")
    public String getFlat() {
        return (flat == null) ? "" : flat ;
    }

    @JsonProperty("flat")
    public void setFlat(String flat) {
        this.flat = flat;
    }

    @JsonProperty("size")
    public String getSize() {
        return (size == null) ? "" : size ;
    }

    @JsonProperty("size")
    public void setSize(String size) {
        this.size = size;
    }

    @JsonProperty("txtDataValue")
    public String getTxtDataValue() {
        return (txtDataValue == null) ? "" : txtDataValue ;
    }

    @JsonProperty("txtDataValue")
    public void setTxtDataValue(String txtDataValue) {
        this.txtDataValue = txtDataValue;
    }

    @JsonProperty("lstReturnItems")
    public List<LstReturnItems> getLstReturnItems() {
        return lstReturnItems;
    }

    @JsonProperty("lstReturnItems")
    public void setLstReturnItems(List<LstReturnItems> lstReturnItems) {
        this.lstReturnItems = lstReturnItems;
    }


    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

    public String getInvSmallImageFullPath() {
        return InvSmallImageFullPath;
    }

    public void setInvSmallImageFullPath(String invSmallImageFullPath) {
        InvSmallImageFullPath = invSmallImageFullPath;
    }

    public String getInvLargeImageFullPath() {
        return InvLargeImageFullPath;
    }

    public void setInvLargeImageFullPath(String invLargeImageFullPath) {
        InvLargeImageFullPath = invLargeImageFullPath;
    }

}