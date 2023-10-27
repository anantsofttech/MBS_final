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
        "ActivateOnlineCC",
        "AltTag",
        "AwardDollar",
        "AwardPoints",
        "BSSetup_Card",
        "BSSetup_DeliveryOption",
        "BSSetup_PayAtStore",
        "Caption",
        "CartID",
        "CustomerName",
        "DrinkRecipes",
        "EmailContent",
        "EmployeeId",
        "Extend_desc",
        "FacebookURL",
        "FlatName",
        "FlgtmpCashTip",
        "GiftWrap",
        "GooglePlusURL",
        "GrpMemo",
        "Grpcomment",
        "Header1",
        "Header2",
        "ISHandDelivery",
        "ImagePath",
        "InvLargeImage",
        "InvLargeImageFullPath",
        "InvSmallImage",
        "InvSmallImageFullPath",
        "InventoryRating",
        "IsDefault",
        "IsDelete",
        "IsDeliverHome",
        "IsLoyaltyRewardEnable",
        "IsMiscTax",
        "IsSalesTax",
        "IsSubscribed",
        "IsSubscriptionDisplay",
        "IsTotalSavingDisplay",
        "IsVarifoneVault",
        "IsWineTax",
        "Isflat",
        "Item_mst_id",
        "LargeImage",
        "LinkedInURL",
        "Lock",
        "LoyaltyCardNo",
        "LoyaltyOn",
        "LoyaltyPointsOnItem",
        "LoyaltyType",
        "Maxprice",
        "Message",
        "Minprice",
        "MiscTax3",
        "NewAddition",
        "Ounces",
        "Qty",
        "QtyOnHand",
        "RealTime_Inventory",
        "ReturnDesc",
        "ReturnPolicyId",
        "RewardPoints",
        "SN",
        "SalesTax1",
        "ShipmentMessage",
        "ShipmentTaxable",
        "ShippingDesc",
        "ShippingPolicyId",
        "ShowItemButOutOfStock",
        "SmallImage",
        "SocialSharing",
        "SpecialComments",
        "StaffPick",
        "StorePhone",
        "Tax1Name",
        "Tax2Name",
        "Tax3Name",
        "TaxExamptMessage",
        "TaxExamptNo",
        "TipCCValue",
        "TipSubTotalValue",
        "TipValue",
        "TwitterURL",
        "Type",
        "WineTax2",
        "WishlistCount",
        "bottledeposit",
        "cartPrice",
        "cost",
        "date",
        "departmentID",
        "departmentName",
        "desc1",
        "discountID",
        "discountName",
        "flat",
        "id",
        "inv_count",
        "inv_type",
        "invexpecteddate",
        "invreturnid",
        "itemWithImageCount",
        "keywords",
        "last_cost",
        "lstDepartment",
        "lstSize",
        "lstSpecialOffer",
        "lstStyle",
        "page_count",
        "price",
        "promo_price",
        "promotionCount",
        "sizedata",
        "storeno",
        "styleID",
        "styleName",
        "tax11",
        "tax22",
        "tax33",
        "tax44",
        "tax55",
        "tax66",
        "ItemonPromotionIndicator",
        "FormatcartPrice"
})
public class ShoppingCardModel {

    @JsonProperty("FormatcartPrice")
    private String FormatcartPrice;
    @JsonProperty("ItemonPromotionIndicator")
    private String ItemonPromotionIndicator;
    @JsonProperty("ActivateOnlineCC")
    private Boolean activateOnlineCC;
    @JsonProperty("AltTag")
    private Object altTag;
    @JsonProperty("AwardDollar")
    private String awardDollar;
    @JsonProperty("AwardPoints")
    private String awardPoints;
    @JsonProperty("BSSetup_Card")
    private Boolean bSSetupCard;
    @JsonProperty("BSSetup_DeliveryOption")
    private Boolean bSSetupDeliveryOption;
    @JsonProperty("BSSetup_PayAtStore")
    private Boolean bSSetupPayAtStore;
    @JsonProperty("Caption")
    private Object caption;
    @JsonProperty("CartID")
    private Long cartID;
    @JsonProperty("CustomerName")
    private String customerName;
    @JsonProperty("DrinkRecipes")
    private Boolean drinkRecipes;
    @JsonProperty("EmailContent")
    private Object emailContent;
    @JsonProperty("EmployeeId")
    private Long employeeId;
    @JsonProperty("Extend_desc")
    private Object extendDesc;
    @JsonProperty("FacebookURL")
    private Object facebookURL;
    @JsonProperty("FlatName")
    private String flatName;
    @JsonProperty("FlgtmpCashTip")
    private Boolean flgtmpCashTip;
    @JsonProperty("GiftWrap")
    private Boolean giftWrap;
    @JsonProperty("GooglePlusURL")
    private Object googlePlusURL;
    @JsonProperty("GrpMemo")
    private String GrpMemo;
    @JsonProperty("Grpcomment")
    private String Grpcomment;
    @JsonProperty("Header1")
    private Object header1;
    @JsonProperty("Header2")
    private Object header2;
    @JsonProperty("ISHandDelivery")
    private Boolean iSHandDelivery;
    @JsonProperty("ImagePath")
    private String ImagePath;
    @JsonProperty("InvLargeImage")
    private String invLargeImage;
    @JsonProperty("InvLargeImageFullPath")
    private String InvLargeImageFullPath;
    @JsonProperty("InvSmallImage")
    private String invSmallImage;
    @JsonProperty("InvSmallImageFullPath")
    private String InvSmallImageFullPath;
    @JsonProperty("InventoryRating")
    private Object inventoryRating;
    @JsonProperty("IsDefault")
    private Long isDefault;
    @JsonProperty("IsDelete")
    private Boolean isDelete;
    @JsonProperty("IsDeliverHome")
    private Boolean isDeliverHome;
    @JsonProperty("IsLoyaltyRewardEnable")
    private String isLoyaltyRewardEnable;
    @JsonProperty("IsMiscTax")
    private Boolean isMiscTax;
    @JsonProperty("IsSalesTax")
    private Boolean isSalesTax;
    @JsonProperty("IsSubscribed")
    private Boolean isSubscribed;
    @JsonProperty("IsSubscriptionDisplay")
    private Boolean isSubscriptionDisplay;
    @JsonProperty("IsTotalSavingDisplay")
    private Boolean isTotalSavingDisplay;
    @JsonProperty("IsVarifoneVault")
    private Boolean isVarifoneVault;
    @JsonProperty("IsWineTax")
    private Boolean isWineTax;
    @JsonProperty("Isflat")
    private Boolean isflat;
    @JsonProperty("Item_mst_id")
    private String itemMstId;
    @JsonProperty("LargeImage")
    private String largeImage;
    @JsonProperty("LinkedInURL")
    private Object linkedInURL;
    @JsonProperty("Lock")
    private String lock;
    @JsonProperty("LoyaltyCardNo")
    private String loyaltyCardNo;
    @JsonProperty("LoyaltyOn")
    private String loyaltyOn;
    @JsonProperty("LoyaltyPointsOnItem")
    private String loyaltyPointsOnItem;
    @JsonProperty("LoyaltyType")
    private String loyaltyType;
    @JsonProperty("Maxprice")
    private Object maxprice;
    @JsonProperty("Message")
    private Object message;
    @JsonProperty("Minprice")
    private Object minprice;
    @JsonProperty("MiscTax3")
    private String miscTax3;
    @JsonProperty("NewAddition")
    private Boolean newAddition;
    @JsonProperty("Ounces")
    private Object ounces;
    @JsonProperty("Qty")
    private String qty;
    @JsonProperty("QtyOnHand")
    private String qtyOnHand;
    @JsonProperty("RealTime_Inventory")
    private String RealTime_Inventory;
    @JsonProperty("ReturnDesc")
    private Object returnDesc;
    @JsonProperty("ReturnPolicyId")
    private Long returnPolicyId;
    @JsonProperty("RewardPoints")
    private String rewardPoints;
    @JsonProperty("SalesTax1")
    private String salesTax1;
    @JsonProperty("SN")
    private String SN;
    @JsonProperty("ShipmentMessage")
    private String shipmentMessage;
    @JsonProperty("ShipmentTaxable")
    private String shipmentTaxable;
    @JsonProperty("ShippingDesc")
    private Object shippingDesc;
    @JsonProperty("ShippingPolicyId")
    private Long shippingPolicyId;
    @JsonProperty("ShowItemButOutOfStock")
    private Object showItemButOutOfStock;
    @JsonProperty("SmallImage")
    private String smallImage;
    @JsonProperty("SocialSharing")
    private Boolean socialSharing;
    @JsonProperty("SpecialComments")
    private Object specialComments;
    @JsonProperty("StaffPick")
    private Boolean staffPick;
    @JsonProperty("StorePhone")
    private String storePhone;
    @JsonProperty("Tax1Name")
    private String tax1Name;
    @JsonProperty("Tax2Name")
    private String tax2Name;
    @JsonProperty("Tax3Name")
    private String tax3Name;
    @JsonProperty("TaxExamptMessage")
    private String taxExamptMessage;
    @JsonProperty("TaxExamptNo")
    private String taxExamptNo;
    @JsonProperty("TipCCValue")
    private Boolean tipCCValue;
    @JsonProperty("TipSubTotalValue")
    private String tipSubTotalValue;
    @JsonProperty("TipValue")
    private String tipValue;
    @JsonProperty("TwitterURL")
    private Object twitterURL;
    @JsonProperty("Type")
    private String type;
    @JsonProperty("WineTax2")
    private String wineTax2;
    @JsonProperty("WishlistCount")
    private String wishlistCount;
    @JsonProperty("bottledeposit")
    private String bottledeposit;
    @JsonProperty("cartPrice")
    private String cartPrice;
    @JsonProperty("cost")
    private String cost;
    @JsonProperty("date")
    private Object date;
    @JsonProperty("departmentID")
    private Object departmentID;
    @JsonProperty("departmentName")
    private Object departmentName;
    @JsonProperty("desc1")
    private String desc1;
    @JsonProperty("discountID")
    private Long discountID;
    @JsonProperty("discountName")
    private String discountName;
    @JsonProperty("flat")
    private String flat;
    @JsonProperty("id")
    private Long id;
    @JsonProperty("inv_count")
    private Long invCount;
    @JsonProperty("inv_type")
    private String invType;
    @JsonProperty("invexpecteddate")
    private Object invexpecteddate;
    @JsonProperty("invreturnid")
    private Long invreturnid;
    @JsonProperty("itemWithImageCount")
    private Long itemWithImageCount;
    @JsonProperty("keywords")
    private Object keywords;
    @JsonProperty("last_cost")
    private String lastCost;
    @JsonProperty("lstDepartment")
    private Object lstDepartment;
    @JsonProperty("lstSize")
    private Object lstSize;
    @JsonProperty("lstSpecialOffer")
    private Object lstSpecialOffer;
    @JsonProperty("lstStyle")
    private Object lstStyle;
    @JsonProperty("page_count")
    private Long pageCount;
    @JsonProperty("price")
    private String price;
    @JsonProperty("promo_price")
    private String promoPrice;
    @JsonProperty("promotionCount")
    private Long promotionCount;
    @JsonProperty("sizedata")
    private Object sizedata;
    @JsonProperty("storeno")
    private Object storeno;
    @JsonProperty("styleID")
    private Object styleID;
    @JsonProperty("styleName")
    private String styleName;
    @JsonProperty("tax11")
    private String tax11;
    @JsonProperty("tax22")
    private String tax22;
    @JsonProperty("tax33")
    private String tax33;
    @JsonProperty("tax44")
    private String tax44;
    @JsonProperty("tax55")
    private String tax55;
    @JsonProperty("tax66")
    private String tax66;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("ActivateOnlineCC")
    public Boolean getActivateOnlineCC() {
        return activateOnlineCC;
    }

    @JsonProperty("ActivateOnlineCC")
    public void setActivateOnlineCC(Boolean activateOnlineCC) {
        this.activateOnlineCC = activateOnlineCC;
    }

    @JsonProperty("AltTag")
    public Object getAltTag() {
        return altTag;
    }

    @JsonProperty("AltTag")
    public void setAltTag(Object altTag) {
        this.altTag = altTag;
    }

    @JsonProperty("AwardDollar")
    public String getAwardDollar() {
        return awardDollar;
    }

    @JsonProperty("AwardDollar")
    public void setAwardDollar(String awardDollar) {
        this.awardDollar = awardDollar;
    }

    @JsonProperty("AwardPoints")
    public String getAwardPoints() {
        return awardPoints;
    }

    @JsonProperty("AwardPoints")
    public void setAwardPoints(String awardPoints) {
        this.awardPoints = awardPoints;
    }

    @JsonProperty("BSSetup_Card")
    public Boolean getBSSetupCard() {
        return bSSetupCard;
    }

    @JsonProperty("BSSetup_Card")
    public void setBSSetupCard(Boolean bSSetupCard) {
        this.bSSetupCard = bSSetupCard;
    }

    @JsonProperty("BSSetup_DeliveryOption")
    public Boolean getBSSetupDeliveryOption() {
        return bSSetupDeliveryOption;
    }

    @JsonProperty("BSSetup_DeliveryOption")
    public void setBSSetupDeliveryOption(Boolean bSSetupDeliveryOption) {
        this.bSSetupDeliveryOption = bSSetupDeliveryOption;
    }

    @JsonProperty("BSSetup_PayAtStore")
    public Boolean getBSSetupPayAtStore() {
        return bSSetupPayAtStore;
    }

    @JsonProperty("BSSetup_PayAtStore")
    public void setBSSetupPayAtStore(Boolean bSSetupPayAtStore) {
        this.bSSetupPayAtStore = bSSetupPayAtStore;
    }

    @JsonProperty("Caption")
    public Object getCaption() {
        return caption;
    }

    @JsonProperty("Caption")
    public void setCaption(Object caption) {
        this.caption = caption;
    }

    @JsonProperty("CartID")
    public Long getCartID() {
        return cartID;
    }

    @JsonProperty("CartID")
    public void setCartID(Long cartID) {
        this.cartID = cartID;
    }

    @JsonProperty("CustomerName")
    public String getCustomerName() {
        return customerName;
    }

    @JsonProperty("CustomerName")
    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    @JsonProperty("DrinkRecipes")
    public Boolean getDrinkRecipes() {
        return drinkRecipes;
    }

    @JsonProperty("DrinkRecipes")
    public void setDrinkRecipes(Boolean drinkRecipes) {
        this.drinkRecipes = drinkRecipes;
    }

    @JsonProperty("EmailContent")
    public Object getEmailContent() {
        return emailContent;
    }

    @JsonProperty("EmailContent")
    public void setEmailContent(Object emailContent) {
        this.emailContent = emailContent;
    }

    @JsonProperty("EmployeeId")
    public Long getEmployeeId() {
        return employeeId;
    }

    @JsonProperty("EmployeeId")
    public void setEmployeeId(Long employeeId) {
        this.employeeId = employeeId;
    }

    @JsonProperty("Extend_desc")
    public Object getExtendDesc() {
        return extendDesc;
    }

    @JsonProperty("Extend_desc")
    public void setExtendDesc(Object extendDesc) {
        this.extendDesc = extendDesc;
    }

    @JsonProperty("FacebookURL")
    public Object getFacebookURL() {
        return facebookURL;
    }

    @JsonProperty("FacebookURL")
    public void setFacebookURL(Object facebookURL) {
        this.facebookURL = facebookURL;
    }

    @JsonProperty("FlatName")
    public String getFlatName() {
        return flatName;
    }

    @JsonProperty("FlatName")
    public void setFlatName(String flatName) {
        this.flatName = flatName;
    }

    @JsonProperty("FlgtmpCashTip")
    public Boolean getFlgtmpCashTip() {
        return flgtmpCashTip;
    }

    @JsonProperty("FlgtmpCashTip")
    public void setFlgtmpCashTip(Boolean flgtmpCashTip) {
        this.flgtmpCashTip = flgtmpCashTip;
    }

    @JsonProperty("GiftWrap")
    public Boolean getGiftWrap() {
        return giftWrap;
    }

    @JsonProperty("GiftWrap")
    public void setGiftWrap(Boolean giftWrap) {
        this.giftWrap = giftWrap;
    }

    @JsonProperty("GooglePlusURL")
    public Object getGooglePlusURL() {
        return googlePlusURL;
    }

    @JsonProperty("GooglePlusURL")
    public void setGooglePlusURL(Object googlePlusURL) {
        this.googlePlusURL = googlePlusURL;
    }

    @JsonProperty("GrpMemo")
    public String getGrpMemo() {
        return GrpMemo;
    }

    @JsonProperty("GrpMemo")
    public void setGrpMemo(String GrpMemo) {
        this.GrpMemo = GrpMemo;
    }

    @JsonProperty("Grpcomment")
    public String getGrpcomment() {
        return Grpcomment;
    }

    @JsonProperty("Grpcomment")
    public void setGrpcomment(String Grpcomment) {
        this.Grpcomment = Grpcomment;
    }


    @JsonProperty("Header1")
    public Object getHeader1() {
        return header1;
    }

    @JsonProperty("Header1")
    public void setHeader1(Object header1) {
        this.header1 = header1;
    }

    @JsonProperty("Header2")
    public Object getHeader2() {
        return header2;
    }

    @JsonProperty("Header2")
    public void setHeader2(Object header2) {
        this.header2 = header2;
    }

    @JsonProperty("ISHandDelivery")
    public Boolean getISHandDelivery() {
        return iSHandDelivery;
    }

    @JsonProperty("ISHandDelivery")
    public void setISHandDelivery(Boolean iSHandDelivery) {
        this.iSHandDelivery = iSHandDelivery;
    }

    @JsonProperty("InvLargeImage")
    public String getInvLargeImage() {
        return invLargeImage;
    }

    @JsonProperty("InvLargeImage")
    public void setInvLargeImage(String invLargeImage) {
        this.invLargeImage = invLargeImage;
    }

    @JsonProperty("InvLargeImageFullPath")
    public String getInvLargeImageFullPath() {
        return InvLargeImageFullPath;
    }

    @JsonProperty("InvLargeImageFullPath")
    public void setInvLargeImageFullPath(String InvLargeImageFullPath) {
        this.InvLargeImageFullPath = InvLargeImageFullPath;
    }

    @JsonProperty("InvSmallImage")
    public String getInvSmallImage() {
        return invSmallImage;
    }

    @JsonProperty("InvSmallImage")
    public void setInvSmallImage(String invSmallImage) {
        this.invSmallImage = invSmallImage;
    }

    @JsonProperty("InvSmallImageFullPath")
    public String getInvSmallImageFullPath() {
        return InvSmallImageFullPath;
    }

    @JsonProperty("InvSmallImageFullPath")
    public void setInvSmallImageFullPath(String InvSmallImageFullPath) {
        this.InvSmallImageFullPath = InvSmallImageFullPath;
    }

    @JsonProperty("InventoryRating")
    public Object getInventoryRating() {
        return inventoryRating;
    }

    @JsonProperty("InventoryRating")
    public void setInventoryRating(Object inventoryRating) {
        this.inventoryRating = inventoryRating;
    }

    @JsonProperty("IsDefault")
    public Long getIsDefault() {
        return isDefault;
    }

    @JsonProperty("IsDefault")
    public void setIsDefault(Long isDefault) {
        this.isDefault = isDefault;
    }

    @JsonProperty("IsDelete")
    public Boolean getIsDelete() {
        return isDelete;
    }

    @JsonProperty("IsDelete")
    public void setIsDelete(Boolean isDelete) {
        this.isDelete = isDelete;
    }

    @JsonProperty("IsDeliverHome")
    public Boolean getIsDeliverHome() {
        return isDeliverHome;
    }

    @JsonProperty("IsDeliverHome")
    public void setIsDeliverHome(Boolean isDeliverHome) {
        this.isDeliverHome = isDeliverHome;
    }

    @JsonProperty("IsLoyaltyRewardEnable")
    public String getIsLoyaltyRewardEnable() {
        return (isLoyaltyRewardEnable == null) ? "" : isLoyaltyRewardEnable ;
    }

    @JsonProperty("IsLoyaltyRewardEnable")
    public void setIsLoyaltyRewardEnable(String isLoyaltyRewardEnable) {
        this.isLoyaltyRewardEnable = isLoyaltyRewardEnable;
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

    @JsonProperty("IsSubscribed")
    public Boolean getIsSubscribed() {
        return isSubscribed;
    }

    @JsonProperty("IsSubscribed")
    public void setIsSubscribed(Boolean isSubscribed) {
        this.isSubscribed = isSubscribed;
    }

    @JsonProperty("IsSubscriptionDisplay")
    public Boolean getIsSubscriptionDisplay() {
        return isSubscriptionDisplay;
    }

    @JsonProperty("IsSubscriptionDisplay")
    public void setIsSubscriptionDisplay(Boolean isSubscriptionDisplay) {
        this.isSubscriptionDisplay = isSubscriptionDisplay;
    }

    @JsonProperty("IsTotalSavingDisplay")
    public Boolean getIsTotalSavingDisplay() {
        return isTotalSavingDisplay;
    }

    @JsonProperty("IsTotalSavingDisplay")
    public void setIsTotalSavingDisplay(Boolean isTotalSavingDisplay) {
        this.isTotalSavingDisplay = isTotalSavingDisplay;
    }

    @JsonProperty("IsVarifoneVault")
    public Boolean getIsVarifoneVault() {
        return isVarifoneVault;
    }

    @JsonProperty("IsVarifoneVault")
    public void setIsVarifoneVault(Boolean isVarifoneVault) {
        this.isVarifoneVault = isVarifoneVault;
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

    @JsonProperty("Item_mst_id")
    public String getItemMstId() {
        return itemMstId;
    }

    @JsonProperty("Item_mst_id")
    public void setItemMstId(String itemMstId) {
        this.itemMstId = itemMstId;
    }

    @JsonProperty("LinkedInURL")
    public Object getLinkedInURL() {
        return linkedInURL;
    }

    @JsonProperty("LinkedInURL")
    public void setLinkedInURL(Object linkedInURL) {
        this.linkedInURL = linkedInURL;
    }

    @JsonProperty("Lock")
    public String getLock() {
        return lock;
    }

    @JsonProperty("Lock")
    public void setLock(String lock) {
        this.lock = lock;
    }

    @JsonProperty("LoyaltyCardNo")
    public String getLoyaltyCardNo() {
        return (loyaltyCardNo == null) ? "" : loyaltyCardNo ;
    }

    @JsonProperty("LoyaltyCardNo")
    public void setLoyaltyCardNo(String loyaltyCardNo) {
        this.loyaltyCardNo = loyaltyCardNo;
    }

    @JsonProperty("LoyaltyOn")
    public String getLoyaltyOn() {
        return (loyaltyOn == null) ? "" : loyaltyOn ;
    }

    @JsonProperty("LoyaltyOn")
    public void setLoyaltyOn(String loyaltyOn) {
        this.loyaltyOn = loyaltyOn;
    }

    @JsonProperty("LoyaltyPointsOnItem")
    public String getLoyaltyPointsOnItem() {
        return loyaltyPointsOnItem;
    }

    @JsonProperty("LoyaltyPointsOnItem")
    public void setLoyaltyPointsOnItem(String loyaltyPointsOnItem) {
        this.loyaltyPointsOnItem = loyaltyPointsOnItem;
    }

    @JsonProperty("LoyaltyType")
    public String getLoyaltyType() {
        return (loyaltyType == null) ? "" : loyaltyType ;
    }

    @JsonProperty("LoyaltyType")
    public void setLoyaltyType(String loyaltyType) {
        this.loyaltyType = loyaltyType;
    }

    @JsonProperty("Maxprice")
    public Object getMaxprice() {
        return maxprice;
    }

    @JsonProperty("Maxprice")
    public void setMaxprice(Object maxprice) {
        this.maxprice = maxprice;
    }

    @JsonProperty("Message")
    public Object getMessage() {
        return message;
    }

    @JsonProperty("Message")
    public void setMessage(Object message) {
        this.message = message;
    }

    @JsonProperty("Minprice")
    public Object getMinprice() {
        return minprice;
    }

    @JsonProperty("Minprice")
    public void setMinprice(Object minprice) {
        this.minprice = minprice;
    }

    @JsonProperty("MiscTax3")
    public String getMiscTax3() {
        return miscTax3;
    }

    @JsonProperty("MiscTax3")
    public void setMiscTax3(String miscTax3) {
        this.miscTax3 = miscTax3;
    }

    @JsonProperty("NewAddition")
    public Boolean getNewAddition() {
        return newAddition;
    }

    @JsonProperty("NewAddition")
    public void setNewAddition(Boolean newAddition) {
        this.newAddition = newAddition;
    }

    @JsonProperty("Ounces")
    public Object getOunces() {
        return ounces;
    }

    @JsonProperty("Ounces")
    public void setOunces(Object ounces) {
        this.ounces = ounces;
    }

    @JsonProperty("Qty")
    public String getQty() {
        return qty;
    }

    @JsonProperty("Qty")
    public void setQty(String qty) {
        this.qty = qty;
    }

    @JsonProperty("QtyOnHand")
    public String  getQtyOnHand() {
        return (qtyOnHand == null) ? "0" : qtyOnHand ;
    }

    @JsonProperty("QtyOnHand")
    public void setQtyOnHand(String  qtyOnHand) {
        this.qtyOnHand = qtyOnHand;
    }

    @JsonProperty("RealTime_Inventory")
    public String getRealTime_Inventory() {
        return RealTime_Inventory;
    }

    @JsonProperty("RealTime_Inventory")
    public void setRealTime_Inventory(String realTime_Inventory) {
        RealTime_Inventory = realTime_Inventory;
    }

    @JsonProperty("ReturnDesc")
    public Object getReturnDesc() {
        return returnDesc;
    }

    @JsonProperty("ReturnDesc")
    public void setReturnDesc(Object returnDesc) {
        this.returnDesc = returnDesc;
    }

    @JsonProperty("ReturnPolicyId")
    public Long getReturnPolicyId() {
        return returnPolicyId;
    }

    @JsonProperty("ReturnPolicyId")
    public void setReturnPolicyId(Long returnPolicyId) {
        this.returnPolicyId = returnPolicyId;
    }

    @JsonProperty("RewardPoints")
    public String getRewardPoints() {
        return rewardPoints;
    }

    @JsonProperty("RewardPoints")
    public void setRewardPoints(String rewardPoints) {
        this.rewardPoints = rewardPoints;
    }

    @JsonProperty("SN")
    public String getSN() {
        return SN;
    }

    @JsonProperty("SN")
    public void setSN(String SN) {
        this.SN = SN;
    }

    @JsonProperty("SalesTax1")
    public String getSalesTax1() {
        return salesTax1;
    }

    @JsonProperty("SalesTax1")
    public void setSalesTax1(String salesTax1) {
        this.salesTax1 = salesTax1;
    }

    @JsonProperty("ShipmentMessage")
    public String getShipmentMessage() {
        return shipmentMessage;
    }

    @JsonProperty("ShipmentMessage")
    public void setShipmentMessage(String shipmentMessage) {
        this.shipmentMessage = shipmentMessage;
    }

    @JsonProperty("ShipmentTaxable")
    public String getShipmentTaxable() {
        return shipmentTaxable;
    }

    @JsonProperty("ShipmentTaxable")
    public void setShipmentTaxable(String shipmentTaxable) {
        this.shipmentTaxable = shipmentTaxable;
    }

    @JsonProperty("ShippingDesc")
    public Object getShippingDesc() {
        return shippingDesc;
    }

    @JsonProperty("ShippingDesc")
    public void setShippingDesc(Object shippingDesc) {
        this.shippingDesc = shippingDesc;
    }

    @JsonProperty("ShippingPolicyId")
    public Long getShippingPolicyId() {
        return shippingPolicyId;
    }

    @JsonProperty("ShippingPolicyId")
    public void setShippingPolicyId(Long shippingPolicyId) {
        this.shippingPolicyId = shippingPolicyId;
    }

    @JsonProperty("ShowItemButOutOfStock")
    public Object getShowItemButOutOfStock() {
        return showItemButOutOfStock;
    }

    @JsonProperty("ShowItemButOutOfStock")
    public void setShowItemButOutOfStock(Object showItemButOutOfStock) {
        this.showItemButOutOfStock = showItemButOutOfStock;
    }

    @JsonProperty("SmallImage")
    public String getSmallImage() {
        return smallImage;
    }

    @JsonProperty("SmallImage")
    public void setSmallImage(String smallImage) {
        this.smallImage = smallImage;
    }

    @JsonProperty("SocialSharing")
    public Boolean getSocialSharing() {
        return socialSharing;
    }

    @JsonProperty("SocialSharing")
    public void setSocialSharing(Boolean socialSharing) {
        this.socialSharing = socialSharing;
    }

    @JsonProperty("SpecialComments")
    public Object getSpecialComments() {
        return specialComments;
    }

    @JsonProperty("SpecialComments")
    public void setSpecialComments(Object specialComments) {
        this.specialComments = specialComments;
    }

    @JsonProperty("StaffPick")
    public Boolean getStaffPick() {
        return staffPick;
    }

    @JsonProperty("StaffPick")
    public void setStaffPick(Boolean staffPick) {
        this.staffPick = staffPick;
    }

    @JsonProperty("StorePhone")
    public String getStorePhone() {
        return storePhone;
    }

    @JsonProperty("StorePhone")
    public void setStorePhone(String storePhone) {
        this.storePhone = storePhone;
    }

    @JsonProperty("Tax1Name")
    public String getTax1Name() {
        return tax1Name;
    }

    @JsonProperty("Tax1Name")
    public void setTax1Name(String tax1Name) {
        this.tax1Name = tax1Name;
    }

    @JsonProperty("Tax2Name")
    public String getTax2Name() {
        return tax2Name;
    }

    @JsonProperty("Tax2Name")
    public void setTax2Name(String tax2Name) {
        this.tax2Name = tax2Name;
    }

    @JsonProperty("Tax3Name")
    public String getTax3Name() {
        return tax3Name;
    }

    @JsonProperty("Tax3Name")
    public void setTax3Name(String tax3Name) {
        this.tax3Name = tax3Name;
    }

    @JsonProperty("TaxExamptMessage")
    public String getTaxExamptMessage() {
        return taxExamptMessage;
    }

    @JsonProperty("TaxExamptMessage")
    public void setTaxExamptMessage(String taxExamptMessage) {
        this.taxExamptMessage = taxExamptMessage;
    }

    @JsonProperty("TaxExamptNo")
    public String getTaxExamptNo() {
        return taxExamptNo;
    }

    @JsonProperty("TaxExamptNo")
    public void setTaxExamptNo(String taxExamptNo) {
        this.taxExamptNo = taxExamptNo;
    }

    @JsonProperty("TipCCValue")
    public Boolean getTipCCValue() {
        return tipCCValue;
    }

    @JsonProperty("TipCCValue")
    public void setTipCCValue(Boolean tipCCValue) {
        this.tipCCValue = tipCCValue;
    }

    @JsonProperty("TipSubTotalValue")
    public String getTipSubTotalValue() {
        return tipSubTotalValue;
    }

    @JsonProperty("TipSubTotalValue")
    public void setTipSubTotalValue(String tipSubTotalValue) {
        this.tipSubTotalValue = tipSubTotalValue;
    }

    @JsonProperty("TipValue")
    public String getTipValue() {
        return tipValue;
    }

    @JsonProperty("TipValue")
    public void setTipValue(String tipValue) {
        this.tipValue = tipValue;
    }

    @JsonProperty("TwitterURL")
    public Object getTwitterURL() {
        return twitterURL;
    }

    @JsonProperty("TwitterURL")
    public void setTwitterURL(Object twitterURL) {
        this.twitterURL = twitterURL;
    }

    @JsonProperty("Type")
    public String getType() {
        return type;
    }

    @JsonProperty("Type")
    public void setType(String type) {
        this.type = type;
    }

    @JsonProperty("WineTax2")
    public String getWineTax2() {
        return wineTax2;
    }

    @JsonProperty("WineTax2")
    public void setWineTax2(String wineTax2) {
        this.wineTax2 = wineTax2;
    }

    @JsonProperty("WishlistCount")
    public String getWishlistCount() {
        return wishlistCount;
    }

    @JsonProperty("WishlistCount")
    public void setWishlistCount(String wishlistCount) {
        this.wishlistCount = wishlistCount;
    }

    @JsonProperty("bottledeposit")
    public String getBottledeposit() {
        return bottledeposit;
    }

    @JsonProperty("bottledeposit")
    public void setBottledeposit(String bottledeposit) {
        this.bottledeposit = bottledeposit;
    }

    @JsonProperty("cartPrice")
    public String getCartPrice() {
        return cartPrice;
    }

    @JsonProperty("cartPrice")
    public void setCartPrice(String cartPrice) {
        this.cartPrice = cartPrice;
    }

    @JsonProperty("cost")
    public String getCost() {
        return cost;
    }

    @JsonProperty("cost")
    public void setCost(String cost) {
        this.cost = cost;
    }

    @JsonProperty("date")
    public Object getDate() {
        return date;
    }

    @JsonProperty("date")
    public void setDate(Object date) {
        this.date = date;
    }

    @JsonProperty("departmentID")
    public Object getDepartmentID() {
        return departmentID;
    }

    @JsonProperty("departmentID")
    public void setDepartmentID(Object departmentID) {
        this.departmentID = departmentID;
    }

    @JsonProperty("departmentName")
    public Object getDepartmentName() {
        return departmentName;
    }

    @JsonProperty("departmentName")
    public void setDepartmentName(Object departmentName) {
        this.departmentName = departmentName;
    }

    @JsonProperty("desc1")
    public String getDesc1() {
        return desc1;
    }

    @JsonProperty("desc1")
    public void setDesc1(String desc1) {
        this.desc1 = desc1;
    }

    @JsonProperty("discountID")
    public Long getDiscountID() {
        return discountID;
    }

    @JsonProperty("discountID")
    public void setDiscountID(Long discountID) {
        this.discountID = discountID;
    }

    @JsonProperty("discountName")
    public String getDiscountName() {
        return discountName;
    }

    @JsonProperty("discountName")
    public void setDiscountName(String discountName) {
        this.discountName = discountName;
    }

    @JsonProperty("flat")
    public String getFlat() {
        return flat;
    }

    @JsonProperty("flat")
    public void setFlat(String flat) {
        this.flat = flat;
    }

    @JsonProperty("id")
    public Long getId() {
        return id;
    }

    @JsonProperty("id")
    public void setId(Long id) {
        this.id = id;
    }

    @JsonProperty("inv_count")
    public Long getInvCount() {
        return invCount;
    }

    @JsonProperty("inv_count")
    public void setInvCount(Long invCount) {
        this.invCount = invCount;
    }

    @JsonProperty("inv_type")
    public String getInvType() {
        return invType;
    }

    @JsonProperty("inv_type")
    public void setInvType(String invType) {
        this.invType = invType;
    }

    @JsonProperty("invexpecteddate")
    public Object getInvexpecteddate() {
        return invexpecteddate;
    }

    @JsonProperty("invexpecteddate")
    public void setInvexpecteddate(Object invexpecteddate) {
        this.invexpecteddate = invexpecteddate;
    }

    @JsonProperty("invreturnid")
    public Long getInvreturnid() {
        return invreturnid;
    }

    @JsonProperty("invreturnid")
    public void setInvreturnid(Long invreturnid) {
        this.invreturnid = invreturnid;
    }

    @JsonProperty("itemWithImageCount")
    public Long getItemWithImageCount() {
        return itemWithImageCount;
    }

    @JsonProperty("itemWithImageCount")
    public void setItemWithImageCount(Long itemWithImageCount) {
        this.itemWithImageCount = itemWithImageCount;
    }

    @JsonProperty("keywords")
    public Object getKeywords() {
        return keywords;
    }

    @JsonProperty("keywords")
    public void setKeywords(Object keywords) {
        this.keywords = keywords;
    }

    @JsonProperty("last_cost")
    public String getLastCost() {
        return lastCost;
    }

    @JsonProperty("last_cost")
    public void setLastCost(String lastCost) {
        this.lastCost = lastCost;
    }

    @JsonProperty("lstDepartment")
    public Object getLstDepartment() {
        return lstDepartment;
    }

    @JsonProperty("lstDepartment")
    public void setLstDepartment(Object lstDepartment) {
        this.lstDepartment = lstDepartment;
    }

    @JsonProperty("lstSize")
    public Object getLstSize() {
        return lstSize;
    }

    @JsonProperty("lstSize")
    public void setLstSize(Object lstSize) {
        this.lstSize = lstSize;
    }

    @JsonProperty("lstSpecialOffer")
    public Object getLstSpecialOffer() {
        return lstSpecialOffer;
    }

    @JsonProperty("lstSpecialOffer")
    public void setLstSpecialOffer(Object lstSpecialOffer) {
        this.lstSpecialOffer = lstSpecialOffer;
    }

    @JsonProperty("lstStyle")
    public Object getLstStyle() {
        return lstStyle;
    }

    @JsonProperty("lstStyle")
    public void setLstStyle(Object lstStyle) {
        this.lstStyle = lstStyle;
    }

    @JsonProperty("page_count")
    public Long getPageCount() {
        return pageCount;
    }

    @JsonProperty("page_count")
    public void setPageCount(Long pageCount) {
        this.pageCount = pageCount;
    }

    @JsonProperty("price")
    public String getPrice() {
        return price;
    }

    @JsonProperty("price")
    public void setPrice(String price) {
        this.price = price;
    }

    @JsonProperty("promo_price")
    public String getPromoPrice() {
        return promoPrice;
    }

    @JsonProperty("promo_price")
    public void setPromoPrice(String promoPrice) {
        this.promoPrice = promoPrice;
    }

    @JsonProperty("promotionCount")
    public Long getPromotionCount() {
        return promotionCount;
    }

    @JsonProperty("promotionCount")
    public void setPromotionCount(Long promotionCount) {
        this.promotionCount = promotionCount;
    }

    @JsonProperty("sizedata")
    public Object getSizedata() {
        return sizedata;
    }

    @JsonProperty("sizedata")
    public void setSizedata(Object sizedata) {
        this.sizedata = sizedata;
    }

    @JsonProperty("storeno")
    public Object getStoreno() {
        return storeno;
    }

    @JsonProperty("storeno")
    public void setStoreno(Object storeno) {
        this.storeno = storeno;
    }

    @JsonProperty("styleID")
    public Object getStyleID() {
        return styleID;
    }

    @JsonProperty("styleID")
    public void setStyleID(Object styleID) {
        this.styleID = styleID;
    }

    @JsonProperty("styleName")
    public String getStyleName() {
        return styleName;
    }

    @JsonProperty("styleName")
    public void setStyleName(String styleName) {
        this.styleName = styleName;
    }

    @JsonProperty("tax11")
    public String getTax11() {
        return tax11;
    }

    @JsonProperty("tax11")
    public void setTax11(String tax11) {
        this.tax11 = tax11;
    }

    @JsonProperty("tax22")
    public String getTax22() {
        return tax22;
    }

    @JsonProperty("tax22")
    public void setTax22(String tax22) {
        this.tax22 = tax22;
    }

    @JsonProperty("tax33")
    public String getTax33() {
        return tax33;
    }

    @JsonProperty("tax33")
    public void setTax33(String tax33) {
        this.tax33 = tax33;
    }

    @JsonProperty("tax44")
    public String getTax44() {
        return tax44;
    }

    @JsonProperty("tax44")
    public void setTax44(String tax44) {
        this.tax44 = tax44;
    }

    @JsonProperty("tax55")
    public String getTax55() {
        return tax55;
    }

    @JsonProperty("tax55")
    public void setTax55(String tax55) {
        this.tax55 = tax55;
    }

    @JsonProperty("tax66")
    public String getTax66() {
        return tax66;
    }

    @JsonProperty("tax66")
    public void setTax66(String tax66) {
        this.tax66 = tax66;
    }

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

    public String getFormatcartPrice() {
        return FormatcartPrice;
    }

    public void setFormatcartPrice(String formatcartPrice) {
        FormatcartPrice = formatcartPrice;
    }

    public String getItemonPromotionIndicator() {
        return ItemonPromotionIndicator;
    }

    public void setItemonPromotionIndicator(String itemonPromotionIndicator) {
        ItemonPromotionIndicator = itemonPromotionIndicator;
    }
}


/*
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
        "ActivateOnlineCC",
        "AltTag",
        "AwardDollar",
        "AwardPoints",
        "BSSetup_Card",
        "BSSetup_DeliveryOption",
        "BSSetup_PayAtStore",
        "Caption",
        "CartID",
        "CustomerName",
        "DrinkRecipes",
        "EmailContent",
        "EmployeeId",
        "Extend_desc",
        "FacebookURL",
        "FlatName",
        "GiftWrap",
        "GooglePlusURL",
        "Header1",
        "Header2",
        "ISHandDelivery",
        "InvLargeImage",
        "InvSmallImage",
        "InventoryRating",
        "IsDefault",
        "IsDelete",
        "IsDeliverHome",
        "IsLoyaltyRewardEnable",
        "IsMiscTax",
        "IsSalesTax",
        "IsSubscribed",
        "IsSubscriptionDisplay",
        "IsTotalSavingDisplay",
        "IsVarifoneVault",
        "IsWineTax",
        "Isflat",
        "Item_mst_id",
        "LargeImage",
        "LinkedInURL",
        "Lock",
        "LoyaltyCardNo",
        "LoyaltyOn",
        "LoyaltyPointsOnItem",
        "LoyaltyType",
        "Maxprice",
        "Message",
        "Minprice",
        "MiscTax3",
        "NewAddition",
        "Ounces",
        "Qty",
        "QtyOnHand",
        "ReturnDesc",
        "ReturnPolicyId",
        "RewardPoints",
        "SalesTax1",
        "ShipmentMessage",
        "ShipmentTaxable",
        "ShippingDesc",
        "ShippingPolicyId",
        "ShowItemButOutOfStock",
        "SmallImage",
        "SocialSharing",
        "SpecialComments",
        "StaffPick",
        "StorePhone",
        "Tax1Name",
        "Tax2Name",
        "Tax3Name",
        "TaxExamptMessage",
        "TaxExamptNo",
        "TipCCValue",
        "TipSubTotalValue",
        "TipValue",
        "TwitterURL",
        "Type",
        "WineTax2",
        "WishlistCount",
        "bottledeposit",
        "cartPrice",
        "cost",
        "date",
        "departmentID",
        "departmentName",
        "desc1",
        "discountID",
        "discountName",
        "flat",
        "id",
        "inv_count",
        "inv_type",
        "invexpecteddate",
        "invreturnid",
        "itemWithImageCount",
        "keywords",
        "last_cost",
        "lstDepartment",
        "lstSize",
        "lstSpecialOffer",
        "lstStyle",
        "page_count",
        "price",
        "promo_price",
        "promotionCount",
        "sizedata",
        "storeno",
        "styleID",
        "styleName",
        "txtDataValue"
})
public class ShoppingCardModel {

    @JsonProperty("ActivateOnlineCC")
    private Boolean activateOnlineCC;
    @JsonProperty("AltTag")
    private Object altTag;
    @JsonProperty("AwardDollar")
    private String awardDollar;
    @JsonProperty("AwardPoints")
    private String awardPoints;
    @JsonProperty("BSSetup_Card")
    private Boolean bSSetupCard;
    @JsonProperty("BSSetup_DeliveryOption")
    private Boolean bSSetupDeliveryOption;
    @JsonProperty("BSSetup_PayAtStore")
    private Boolean bSSetupPayAtStore;
    @JsonProperty("Caption")
    private Object caption;
    @JsonProperty("CartID")
    private Long cartID;
    @JsonProperty("CustomerName")
    private String customerName;
    @JsonProperty("DrinkRecipes")
    private Boolean drinkRecipes;
    @JsonProperty("EmailContent")
    private Object emailContent;
    @JsonProperty("EmployeeId")
    private Long employeeId;
    @JsonProperty("Extend_desc")
    private Object extendDesc;
    @JsonProperty("FacebookURL")
    private Object facebookURL;
    @JsonProperty("FlatName")
    private String flatName;
    @JsonProperty("GiftWrap")
    private String giftWrap;
    @JsonProperty("GooglePlusURL")
    private Object googlePlusURL;
    @JsonProperty("Header1")
    private Object header1;
    @JsonProperty("Header2")
    private Object header2;
    @JsonProperty("ISHandDelivery")
    private Boolean iSHandDelivery;
    @JsonProperty("InvLargeImage")
    private String invLargeImage;
    @JsonProperty("InvSmallImage")
    private String invSmallImage;
    @JsonProperty("InventoryRating")
    private Object inventoryRating;
    @JsonProperty("IsDefault")
    private Long isDefault;
    @JsonProperty("IsDelete")
    private Boolean isDelete;
    @JsonProperty("IsDeliverHome")
    private Boolean isDeliverHome;
    @JsonProperty("IsLoyaltyRewardEnable")
    private String isLoyaltyRewardEnable;
    @JsonProperty("IsMiscTax")
    private Boolean isMiscTax;
    @JsonProperty("IsSalesTax")
    private Boolean isSalesTax;
    @JsonProperty("IsSubscribed")
    private Boolean isSubscribed;
    @JsonProperty("IsSubscriptionDisplay")
    private Boolean isSubscriptionDisplay;
    @JsonProperty("IsTotalSavingDisplay")
    private Boolean isTotalSavingDisplay;
    @JsonProperty("IsVarifoneVault")
    private Boolean isVarifoneVault;
    @JsonProperty("IsWineTax")
    private Boolean isWineTax;
    @JsonProperty("Isflat")
    private Boolean isflat;
    @JsonProperty("Item_mst_id")
    private String itemMstId;
    @JsonProperty("LargeImage")
    private String largeImage;
    @JsonProperty("LinkedInURL")
    private Object linkedInURL;
    @JsonProperty("Lock")
    private String lock;
    @JsonProperty("LoyaltyCardNo")
    private String loyaltyCardNo;
    @JsonProperty("LoyaltyOn")
    private String loyaltyOn;
    @JsonProperty("LoyaltyPointsOnItem")
    private String loyaltyPointsOnItem;
    @JsonProperty("LoyaltyType")
    private String loyaltyType;
    @JsonProperty("Maxprice")
    private Object maxprice;
    @JsonProperty("Message")
    private Object message;
    @JsonProperty("Minprice")
    private Object minprice;
    @JsonProperty("MiscTax3")
    private String miscTax3;
    @JsonProperty("NewAddition")
    private Boolean newAddition;
    @JsonProperty("Ounces")
    private Object ounces;
    @JsonProperty("Qty")
    private String qty;
    @JsonProperty("QtyOnHand")
    private Object qtyOnHand;
    @JsonProperty("ReturnDesc")
    private Object returnDesc;
    @JsonProperty("ReturnPolicyId")
    private Long returnPolicyId;
    @JsonProperty("RewardPoints")
    private String rewardPoints;
    @JsonProperty("SalesTax1")
    private String salesTax1;
    @JsonProperty("ShipmentMessage")
    private Object shipmentMessage;
    @JsonProperty("ShipmentTaxable")
    private Object shipmentTaxable;
    @JsonProperty("ShippingDesc")
    private Object shippingDesc;
    @JsonProperty("ShippingPolicyId")
    private Long shippingPolicyId;
    @JsonProperty("ShowItemButOutOfStock")
    private Object showItemButOutOfStock;
    @JsonProperty("SmallImage")
    private String smallImage;
    @JsonProperty("SocialSharing")
    private Boolean socialSharing;
    @JsonProperty("SpecialComments")
    private Object specialComments;
    @JsonProperty("StaffPick")
    private Boolean staffPick;
    @JsonProperty("StorePhone")
    private String storePhone;
    @JsonProperty("Tax1Name")
    private String tax1Name;
    @JsonProperty("Tax2Name")
    private String tax2Name;
    @JsonProperty("Tax3Name")
    private String tax3Name;
    @JsonProperty("TaxExamptMessage")
    private Object taxExamptMessage;
    @JsonProperty("TaxExamptNo")
    private Object taxExamptNo;
    @JsonProperty("TipCCValue")
    private Boolean tipCCValue;
    @JsonProperty("TipSubTotalValue")
    private Object tipSubTotalValue;
    @JsonProperty("TipValue")
    private Object tipValue;
    @JsonProperty("TwitterURL")
    private Object twitterURL;
    @JsonProperty("Type")
    private String type;
    @JsonProperty("WineTax2")
    private String wineTax2;
    @JsonProperty("WishlistCount")
    private String wishlistCount;
    @JsonProperty("bottledeposit")
    private String bottledeposit;
    @JsonProperty("cartPrice")
    private String cartPrice;
    @JsonProperty("cost")
    private String cost;
    @JsonProperty("date")
    private Object date;
    @JsonProperty("departmentID")
    private Object departmentID;
    @JsonProperty("departmentName")
    private Object departmentName;
    @JsonProperty("desc1")
    private String desc1;
    @JsonProperty("discountID")
    private Long discountID;
    @JsonProperty("discountName")
    private String discountName;
    @JsonProperty("flat")
    private String flat;
    @JsonProperty("id")
    private Long id;
    @JsonProperty("inv_count")
    private Long invCount;
    @JsonProperty("inv_type")
    private String invType;
    @JsonProperty("invexpecteddate")
    private Object invexpecteddate;
    @JsonProperty("invreturnid")
    private Long invreturnid;
    @JsonProperty("itemWithImageCount")
    private Long itemWithImageCount;
    @JsonProperty("keywords")
    private Object keywords;
    @JsonProperty("last_cost")
    private String lastCost;
    @JsonProperty("lstDepartment")
    private Object lstDepartment;
    @JsonProperty("lstSize")
    private Object lstSize;
    @JsonProperty("lstSpecialOffer")
    private Object lstSpecialOffer;
    @JsonProperty("lstStyle")
    private Object lstStyle;
    @JsonProperty("page_count")
    private Long pageCount;
    @JsonProperty("price")
    private String price;
    @JsonProperty("promo_price")
    private String promoPrice;
    @JsonProperty("promotionCount")
    private Long promotionCount;
    @JsonProperty("sizedata")
    private Object sizedata;
    @JsonProperty("storeno")
    private Object storeno;
    @JsonProperty("styleID")
    private Object styleID;
    @JsonProperty("styleName")
    private String styleName;
    @JsonProperty("txtDataValue")
    private String txtDataValue;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("ActivateOnlineCC")
    public Boolean getActivateOnlineCC() {
        return activateOnlineCC;
    }

    @JsonProperty("ActivateOnlineCC")
    public void setActivateOnlineCC(Boolean activateOnlineCC) {
        this.activateOnlineCC = activateOnlineCC;
    }

    @JsonProperty("AltTag")
    public Object getAltTag() {
        return altTag;
    }

    @JsonProperty("AltTag")
    public void setAltTag(Object altTag) {
        this.altTag = altTag;
    }

    @JsonProperty("AwardDollar")
    public String getAwardDollar() {
        return awardDollar;
    }

    @JsonProperty("AwardDollar")
    public void setAwardDollar(String awardDollar) {
        this.awardDollar = awardDollar;
    }

    @JsonProperty("AwardPoints")
    public String getAwardPoints() {
        return awardPoints;
    }

    @JsonProperty("AwardPoints")
    public void setAwardPoints(String awardPoints) {
        this.awardPoints = awardPoints;
    }

    @JsonProperty("BSSetup_Card")
    public Boolean getBSSetupCard() {
        return bSSetupCard;
    }

    @JsonProperty("BSSetup_Card")
    public void setBSSetupCard(Boolean bSSetupCard) {
        this.bSSetupCard = bSSetupCard;
    }

    @JsonProperty("BSSetup_DeliveryOption")
    public Boolean getBSSetupDeliveryOption() {
        return bSSetupDeliveryOption;
    }

    @JsonProperty("BSSetup_DeliveryOption")
    public void setBSSetupDeliveryOption(Boolean bSSetupDeliveryOption) {
        this.bSSetupDeliveryOption = bSSetupDeliveryOption;
    }

    @JsonProperty("BSSetup_PayAtStore")
    public Boolean getBSSetupPayAtStore() {
        return bSSetupPayAtStore;
    }

    @JsonProperty("BSSetup_PayAtStore")
    public void setBSSetupPayAtStore(Boolean bSSetupPayAtStore) {
        this.bSSetupPayAtStore = bSSetupPayAtStore;
    }

    @JsonProperty("Caption")
    public Object getCaption() {
        return caption;
    }

    @JsonProperty("Caption")
    public void setCaption(Object caption) {
        this.caption = caption;
    }

    @JsonProperty("CartID")
    public Long getCartID() {
        return cartID;
    }

    @JsonProperty("CartID")
    public void setCartID(Long cartID) {
        this.cartID = cartID;
    }

    @JsonProperty("CustomerName")
    public String getCustomerName() {
        return customerName;
    }

    @JsonProperty("CustomerName")
    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    @JsonProperty("DrinkRecipes")
    public Boolean getDrinkRecipes() {
        return drinkRecipes;
    }

    @JsonProperty("DrinkRecipes")
    public void setDrinkRecipes(Boolean drinkRecipes) {
        this.drinkRecipes = drinkRecipes;
    }

    @JsonProperty("EmailContent")
    public Object getEmailContent() {
        return emailContent;
    }

    @JsonProperty("EmailContent")
    public void setEmailContent(Object emailContent) {
        this.emailContent = emailContent;
    }

    @JsonProperty("EmployeeId")
    public Long getEmployeeId() {
        return employeeId;
    }

    @JsonProperty("EmployeeId")
    public void setEmployeeId(Long employeeId) {
        this.employeeId = employeeId;
    }

    @JsonProperty("Extend_desc")
    public Object getExtendDesc() {
        return extendDesc;
    }

    @JsonProperty("Extend_desc")
    public void setExtendDesc(Object extendDesc) {
        this.extendDesc = extendDesc;
    }

    @JsonProperty("FacebookURL")
    public Object getFacebookURL() {
        return facebookURL;
    }

    @JsonProperty("FacebookURL")
    public void setFacebookURL(Object facebookURL) {
        this.facebookURL = facebookURL;
    }

    @JsonProperty("FlatName")
    public String getFlatName() {
        return flatName;
    }

    @JsonProperty("FlatName")
    public void setFlatName(String flatName) {
        this.flatName = flatName;
    }

    @JsonProperty("GiftWrap")
    public String getGiftWrap() {
        return giftWrap;
    }

    @JsonProperty("GiftWrap")
    public void setGiftWrap(String giftWrap) {
        this.giftWrap = giftWrap;
    }

    @JsonProperty("GooglePlusURL")
    public Object getGooglePlusURL() {
        return googlePlusURL;
    }

    @JsonProperty("GooglePlusURL")
    public void setGooglePlusURL(Object googlePlusURL) {
        this.googlePlusURL = googlePlusURL;
    }

    @JsonProperty("Header1")
    public Object getHeader1() {
        return header1;
    }

    @JsonProperty("Header1")
    public void setHeader1(Object header1) {
        this.header1 = header1;
    }

    @JsonProperty("Header2")
    public Object getHeader2() {
        return header2;
    }

    @JsonProperty("Header2")
    public void setHeader2(Object header2) {
        this.header2 = header2;
    }

    @JsonProperty("ISHandDelivery")
    public Boolean getISHandDelivery() {
        return iSHandDelivery;
    }

    @JsonProperty("ISHandDelivery")
    public void setISHandDelivery(Boolean iSHandDelivery) {
        this.iSHandDelivery = iSHandDelivery;
    }

    @JsonProperty("InvLargeImage")
    public String getInvLargeImage() {
        return invLargeImage;
    }

    @JsonProperty("InvLargeImage")
    public void setInvLargeImage(String invLargeImage) {
        this.invLargeImage = invLargeImage;
    }

    @JsonProperty("InvSmallImage")
    public String getInvSmallImage() {
        return invSmallImage;
    }

    @JsonProperty("InvSmallImage")
    public void setInvSmallImage(String invSmallImage) {
        this.invSmallImage = invSmallImage;
    }

    @JsonProperty("InventoryRating")
    public Object getInventoryRating() {
        return inventoryRating;
    }

    @JsonProperty("InventoryRating")
    public void setInventoryRating(Object inventoryRating) {
        this.inventoryRating = inventoryRating;
    }

    @JsonProperty("IsDefault")
    public Long getIsDefault() {
        return isDefault;
    }

    @JsonProperty("IsDefault")
    public void setIsDefault(Long isDefault) {
        this.isDefault = isDefault;
    }

    @JsonProperty("IsDelete")
    public Boolean getIsDelete() {
        return isDelete;
    }

    @JsonProperty("IsDelete")
    public void setIsDelete(Boolean isDelete) {
        this.isDelete = isDelete;
    }

    @JsonProperty("IsDeliverHome")
    public Boolean getIsDeliverHome() {
        return isDeliverHome;
    }

    @JsonProperty("IsDeliverHome")
    public void setIsDeliverHome(Boolean isDeliverHome) {
        this.isDeliverHome = isDeliverHome;
    }

    @JsonProperty("IsLoyaltyRewardEnable")
    public String getIsLoyaltyRewardEnable() {
        return isLoyaltyRewardEnable;
    }

    @JsonProperty("IsLoyaltyRewardEnable")
    public void setIsLoyaltyRewardEnable(String isLoyaltyRewardEnable) {
        this.isLoyaltyRewardEnable = isLoyaltyRewardEnable;
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

    @JsonProperty("IsSubscribed")
    public Boolean getIsSubscribed() {
        return isSubscribed;
    }

    @JsonProperty("IsSubscribed")
    public void setIsSubscribed(Boolean isSubscribed) {
        this.isSubscribed = isSubscribed;
    }

    @JsonProperty("IsSubscriptionDisplay")
    public Boolean getIsSubscriptionDisplay() {
        return isSubscriptionDisplay;
    }

    @JsonProperty("IsSubscriptionDisplay")
    public void setIsSubscriptionDisplay(Boolean isSubscriptionDisplay) {
        this.isSubscriptionDisplay = isSubscriptionDisplay;
    }

    @JsonProperty("IsTotalSavingDisplay")
    public Boolean getIsTotalSavingDisplay() {
        return isTotalSavingDisplay;
    }

    @JsonProperty("IsTotalSavingDisplay")
    public void setIsTotalSavingDisplay(Boolean isTotalSavingDisplay) {
        this.isTotalSavingDisplay = isTotalSavingDisplay;
    }

    @JsonProperty("IsVarifoneVault")
    public Boolean getIsVarifoneVault() {
        return isVarifoneVault;
    }

    @JsonProperty("IsVarifoneVault")
    public void setIsVarifoneVault(Boolean isVarifoneVault) {
        this.isVarifoneVault = isVarifoneVault;
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

    @JsonProperty("Item_mst_id")
    public String getItemMstId() {
        return itemMstId;
    }

    @JsonProperty("Item_mst_id")
    public void setItemMstId(String itemMstId) {
        this.itemMstId = itemMstId;
    }

    @JsonProperty("LargeImage")
    public String getLargeImage() {
        return largeImage;
    }

    @JsonProperty("LargeImage")
    public void setLargeImage(String largeImage) {
        this.largeImage = largeImage;
    }

    @JsonProperty("LinkedInURL")
    public Object getLinkedInURL() {
        return linkedInURL;
    }

    @JsonProperty("LinkedInURL")
    public void setLinkedInURL(Object linkedInURL) {
        this.linkedInURL = linkedInURL;
    }

    @JsonProperty("Lock")
    public String getLock() {
        return lock;
    }

    @JsonProperty("Lock")
    public void setLock(String lock) {
        this.lock = lock;
    }

    @JsonProperty("LoyaltyCardNo")
    public String getLoyaltyCardNo() {
        return loyaltyCardNo;
    }

    @JsonProperty("LoyaltyCardNo")
    public void setLoyaltyCardNo(String loyaltyCardNo) {
        this.loyaltyCardNo = loyaltyCardNo;
    }

    @JsonProperty("LoyaltyOn")
    public String getLoyaltyOn() {
        return loyaltyOn;
    }

    @JsonProperty("LoyaltyOn")
    public void setLoyaltyOn(String loyaltyOn) {
        this.loyaltyOn = loyaltyOn;
    }

    @JsonProperty("LoyaltyPointsOnItem")
    public String getLoyaltyPointsOnItem() {
        return loyaltyPointsOnItem;
    }

    @JsonProperty("LoyaltyPointsOnItem")
    public void setLoyaltyPointsOnItem(String loyaltyPointsOnItem) {
        this.loyaltyPointsOnItem = loyaltyPointsOnItem;
    }

    @JsonProperty("LoyaltyType")
    public String getLoyaltyType() {
        return loyaltyType;
    }

    @JsonProperty("LoyaltyType")
    public void setLoyaltyType(String loyaltyType) {
        this.loyaltyType = loyaltyType;
    }

    @JsonProperty("Maxprice")
    public Object getMaxprice() {
        return maxprice;
    }

    @JsonProperty("Maxprice")
    public void setMaxprice(Object maxprice) {
        this.maxprice = maxprice;
    }

    @JsonProperty("Message")
    public Object getMessage() {
        return message;
    }

    @JsonProperty("Message")
    public void setMessage(Object message) {
        this.message = message;
    }

    @JsonProperty("Minprice")
    public Object getMinprice() {
        return minprice;
    }

    @JsonProperty("Minprice")
    public void setMinprice(Object minprice) {
        this.minprice = minprice;
    }

    @JsonProperty("MiscTax3")
    public String getMiscTax3() {
        return miscTax3;
    }

    @JsonProperty("MiscTax3")
    public void setMiscTax3(String miscTax3) {
        this.miscTax3 = miscTax3;
    }

    @JsonProperty("NewAddition")
    public Boolean getNewAddition() {
        return newAddition;
    }

    @JsonProperty("NewAddition")
    public void setNewAddition(Boolean newAddition) {
        this.newAddition = newAddition;
    }

    @JsonProperty("Ounces")
    public Object getOunces() {
        return ounces;
    }

    @JsonProperty("Ounces")
    public void setOunces(Object ounces) {
        this.ounces = ounces;
    }

    @JsonProperty("Qty")
    public String getQty() {
        return qty;
    }

    @JsonProperty("Qty")
    public void setQty(String qty) {
        this.qty = qty;
    }

    @JsonProperty("QtyOnHand")
    public Object getQtyOnHand() {
        return qtyOnHand;
    }

    @JsonProperty("QtyOnHand")
    public void setQtyOnHand(Object qtyOnHand) {
        this.qtyOnHand = qtyOnHand;
    }

    @JsonProperty("ReturnDesc")
    public Object getReturnDesc() {
        return returnDesc;
    }

    @JsonProperty("ReturnDesc")
    public void setReturnDesc(Object returnDesc) {
        this.returnDesc = returnDesc;
    }

    @JsonProperty("ReturnPolicyId")
    public Long getReturnPolicyId() {
        return returnPolicyId;
    }

    @JsonProperty("ReturnPolicyId")
    public void setReturnPolicyId(Long returnPolicyId) {
        this.returnPolicyId = returnPolicyId;
    }

    @JsonProperty("RewardPoints")
    public String getRewardPoints() {
        return rewardPoints;
    }

    @JsonProperty("RewardPoints")
    public void setRewardPoints(String rewardPoints) {
        this.rewardPoints = rewardPoints;
    }

    @JsonProperty("SalesTax1")
    public String getSalesTax1() {
        return salesTax1;
    }

    @JsonProperty("SalesTax1")
    public void setSalesTax1(String salesTax1) {
        this.salesTax1 = salesTax1;
    }

    @JsonProperty("ShipmentMessage")
    public Object getShipmentMessage() {
        return shipmentMessage;
    }

    @JsonProperty("ShipmentMessage")
    public void setShipmentMessage(Object shipmentMessage) {
        this.shipmentMessage = shipmentMessage;
    }

    @JsonProperty("ShipmentTaxable")
    public Object getShipmentTaxable() {
        return shipmentTaxable;
    }

    @JsonProperty("ShipmentTaxable")
    public void setShipmentTaxable(Object shipmentTaxable) {
        this.shipmentTaxable = shipmentTaxable;
    }

    @JsonProperty("ShippingDesc")
    public Object getShippingDesc() {
        return shippingDesc;
    }

    @JsonProperty("ShippingDesc")
    public void setShippingDesc(Object shippingDesc) {
        this.shippingDesc = shippingDesc;
    }

    @JsonProperty("ShippingPolicyId")
    public Long getShippingPolicyId() {
        return shippingPolicyId;
    }

    @JsonProperty("ShippingPolicyId")
    public void setShippingPolicyId(Long shippingPolicyId) {
        this.shippingPolicyId = shippingPolicyId;
    }

    @JsonProperty("ShowItemButOutOfStock")
    public Object getShowItemButOutOfStock() {
        return showItemButOutOfStock;
    }

    @JsonProperty("ShowItemButOutOfStock")
    public void setShowItemButOutOfStock(Object showItemButOutOfStock) {
        this.showItemButOutOfStock = showItemButOutOfStock;
    }

    @JsonProperty("SmallImage")
    public String getSmallImage() {
        return smallImage;
    }

    @JsonProperty("SmallImage")
    public void setSmallImage(String smallImage) {
        this.smallImage = smallImage;
    }

    @JsonProperty("SocialSharing")
    public Boolean getSocialSharing() {
        return socialSharing;
    }

    @JsonProperty("SocialSharing")
    public void setSocialSharing(Boolean socialSharing) {
        this.socialSharing = socialSharing;
    }

    @JsonProperty("SpecialComments")
    public Object getSpecialComments() {
        return specialComments;
    }

    @JsonProperty("SpecialComments")
    public void setSpecialComments(Object specialComments) {
        this.specialComments = specialComments;
    }

    @JsonProperty("StaffPick")
    public Boolean getStaffPick() {
        return staffPick;
    }

    @JsonProperty("StaffPick")
    public void setStaffPick(Boolean staffPick) {
        this.staffPick = staffPick;
    }

    @JsonProperty("StorePhone")
    public String getStorePhone() {
        return storePhone;
    }

    @JsonProperty("StorePhone")
    public void setStorePhone(String storePhone) {
        this.storePhone = storePhone;
    }

    @JsonProperty("Tax1Name")
    public String getTax1Name() {
        return tax1Name;
    }

    @JsonProperty("Tax1Name")
    public void setTax1Name(String tax1Name) {
        this.tax1Name = tax1Name;
    }

    @JsonProperty("Tax2Name")
    public String getTax2Name() {
        return tax2Name;
    }

    @JsonProperty("Tax2Name")
    public void setTax2Name(String tax2Name) {
        this.tax2Name = tax2Name;
    }

    @JsonProperty("Tax3Name")
    public String getTax3Name() {
        return tax3Name;
    }

    @JsonProperty("Tax3Name")
    public void setTax3Name(String tax3Name) {
        this.tax3Name = tax3Name;
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

    @JsonProperty("TipCCValue")
    public Boolean getTipCCValue() {
        return tipCCValue;
    }

    @JsonProperty("TipCCValue")
    public void setTipCCValue(Boolean tipCCValue) {
        this.tipCCValue = tipCCValue;
    }

    @JsonProperty("TipSubTotalValue")
    public Object getTipSubTotalValue() {
        return tipSubTotalValue;
    }

    @JsonProperty("TipSubTotalValue")
    public void setTipSubTotalValue(Object tipSubTotalValue) {
        this.tipSubTotalValue = tipSubTotalValue;
    }

    @JsonProperty("TipValue")
    public Object getTipValue() {
        return tipValue;
    }

    @JsonProperty("TipValue")
    public void setTipValue(Object tipValue) {
        this.tipValue = tipValue;
    }

    @JsonProperty("TwitterURL")
    public Object getTwitterURL() {
        return twitterURL;
    }

    @JsonProperty("TwitterURL")
    public void setTwitterURL(Object twitterURL) {
        this.twitterURL = twitterURL;
    }

    @JsonProperty("Type")
    public String getType() {
        return type;
    }

    @JsonProperty("Type")
    public void setType(String type) {
        this.type = type;
    }

    @JsonProperty("WineTax2")
    public String getWineTax2() {
        return wineTax2;
    }

    @JsonProperty("WineTax2")
    public void setWineTax2(String wineTax2) {
        this.wineTax2 = wineTax2;
    }

    @JsonProperty("WishlistCount")
    public String getWishlistCount() {
        return wishlistCount;
    }

    @JsonProperty("WishlistCount")
    public void setWishlistCount(String wishlistCount) {
        this.wishlistCount = wishlistCount;
    }

    @JsonProperty("bottledeposit")
    public String getBottledeposit() {
        return bottledeposit;
    }

    @JsonProperty("bottledeposit")
    public void setBottledeposit(String bottledeposit) {
        this.bottledeposit = bottledeposit;
    }

    @JsonProperty("cartPrice")
    public String getCartPrice() {
        return cartPrice;
    }

    @JsonProperty("cartPrice")
    public void setCartPrice(String cartPrice) {
        this.cartPrice = cartPrice;
    }

    @JsonProperty("cost")
    public String getCost() {
        return cost;
    }

    @JsonProperty("cost")
    public void setCost(String cost) {
        this.cost = cost;
    }

    @JsonProperty("date")
    public Object getDate() {
        return date;
    }

    @JsonProperty("date")
    public void setDate(Object date) {
        this.date = date;
    }

    @JsonProperty("departmentID")
    public Object getDepartmentID() {
        return departmentID;
    }

    @JsonProperty("departmentID")
    public void setDepartmentID(Object departmentID) {
        this.departmentID = departmentID;
    }

    @JsonProperty("departmentName")
    public Object getDepartmentName() {
        return departmentName;
    }

    @JsonProperty("departmentName")
    public void setDepartmentName(Object departmentName) {
        this.departmentName = departmentName;
    }

    @JsonProperty("desc1")
    public String getDesc1() {
        return desc1;
    }

    @JsonProperty("desc1")
    public void setDesc1(String desc1) {
        this.desc1 = desc1;
    }

    @JsonProperty("discountID")
    public Long getDiscountID() {
        return discountID;
    }

    @JsonProperty("discountID")
    public void setDiscountID(Long discountID) {
        this.discountID = discountID;
    }

    @JsonProperty("discountName")
    public String getDiscountName() {
        return discountName;
    }

    @JsonProperty("discountName")
    public void setDiscountName(String discountName) {
        this.discountName = discountName;
    }

    @JsonProperty("flat")
    public String getFlat() {
        return flat;
    }

    @JsonProperty("flat")
    public void setFlat(String flat) {
        this.flat = flat;
    }

    @JsonProperty("id")
    public Long getId() {
        return id;
    }

    @JsonProperty("id")
    public void setId(Long id) {
        this.id = id;
    }

    @JsonProperty("inv_count")
    public Long getInvCount() {
        return invCount;
    }

    @JsonProperty("inv_count")
    public void setInvCount(Long invCount) {
        this.invCount = invCount;
    }

    @JsonProperty("inv_type")
    public String getInvType() {
        return invType;
    }

    @JsonProperty("inv_type")
    public void setInvType(String invType) {
        this.invType = invType;
    }

    @JsonProperty("invexpecteddate")
    public Object getInvexpecteddate() {
        return invexpecteddate;
    }

    @JsonProperty("invexpecteddate")
    public void setInvexpecteddate(Object invexpecteddate) {
        this.invexpecteddate = invexpecteddate;
    }

    @JsonProperty("invreturnid")
    public Long getInvreturnid() {
        return invreturnid;
    }

    @JsonProperty("invreturnid")
    public void setInvreturnid(Long invreturnid) {
        this.invreturnid = invreturnid;
    }

    @JsonProperty("itemWithImageCount")
    public Long getItemWithImageCount() {
        return itemWithImageCount;
    }

    @JsonProperty("itemWithImageCount")
    public void setItemWithImageCount(Long itemWithImageCount) {
        this.itemWithImageCount = itemWithImageCount;
    }

    @JsonProperty("keywords")
    public Object getKeywords() {
        return keywords;
    }

    @JsonProperty("keywords")
    public void setKeywords(Object keywords) {
        this.keywords = keywords;
    }

    @JsonProperty("last_cost")
    public String getLastCost() {
        return lastCost;
    }

    @JsonProperty("last_cost")
    public void setLastCost(String lastCost) {
        this.lastCost = lastCost;
    }

    @JsonProperty("lstDepartment")
    public Object getLstDepartment() {
        return lstDepartment;
    }

    @JsonProperty("lstDepartment")
    public void setLstDepartment(Object lstDepartment) {
        this.lstDepartment = lstDepartment;
    }

    @JsonProperty("lstSize")
    public Object getLstSize() {
        return lstSize;
    }

    @JsonProperty("lstSize")
    public void setLstSize(Object lstSize) {
        this.lstSize = lstSize;
    }

    @JsonProperty("lstSpecialOffer")
    public Object getLstSpecialOffer() {
        return lstSpecialOffer;
    }

    @JsonProperty("lstSpecialOffer")
    public void setLstSpecialOffer(Object lstSpecialOffer) {
        this.lstSpecialOffer = lstSpecialOffer;
    }

    @JsonProperty("lstStyle")
    public Object getLstStyle() {
        return lstStyle;
    }

    @JsonProperty("lstStyle")
    public void setLstStyle(Object lstStyle) {
        this.lstStyle = lstStyle;
    }

    @JsonProperty("page_count")
    public Long getPageCount() {
        return pageCount;
    }

    @JsonProperty("page_count")
    public void setPageCount(Long pageCount) {
        this.pageCount = pageCount;
    }

    @JsonProperty("price")
    public String getPrice() {
        return price;
    }

    @JsonProperty("price")
    public void setPrice(String price) {
        this.price = price;
    }

    @JsonProperty("promo_price")
    public String getPromoPrice() {
        return promoPrice;
    }

    @JsonProperty("promo_price")
    public void setPromoPrice(String promoPrice) {
        this.promoPrice = promoPrice;
    }

    @JsonProperty("promotionCount")
    public Long getPromotionCount() {
        return promotionCount;
    }

    @JsonProperty("promotionCount")
    public void setPromotionCount(Long promotionCount) {
        this.promotionCount = promotionCount;
    }

    @JsonProperty("sizedata")
    public Object getSizedata() {
        return sizedata;
    }

    @JsonProperty("sizedata")
    public void setSizedata(Object sizedata) {
        this.sizedata = sizedata;
    }

    @JsonProperty("storeno")
    public Object getStoreno() {
        return storeno;
    }

    @JsonProperty("storeno")
    public void setStoreno(Object storeno) {
        this.storeno = storeno;
    }

    @JsonProperty("styleID")
    public Object getStyleID() {
        return styleID;
    }

    @JsonProperty("styleID")
    public void setStyleID(Object styleID) {
        this.styleID = styleID;
    }

    @JsonProperty("styleName")
    public String getStyleName() {
        return styleName;
    }

    @JsonProperty("styleName")
    public void setStyleName(String styleName) {
        this.styleName = styleName;
    }

    @JsonProperty("txtDataValue")
    public String getTxtDataValue() {
        return txtDataValue;
    }

    @JsonProperty("txtDataValue")
    public void setTxtDataValue(String txtDataValue) {
        this.txtDataValue = txtDataValue;
    }

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}*/
