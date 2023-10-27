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
        "HandlingSurcharge",
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
        "ReturnDays",
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
        "invReturnDays",
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
        "InvLargeImageFullPath",
        "InvSmallImageFullPath",
        "ImagePath",
        "ItemonPromotionIndicator"

})

public class MultiPackModel {

    @JsonProperty("ItemonPromotionIndicator")
    private String ItemonPromotionIndicator;
    @JsonProperty("ImagePath")
    private String imagepath;
    @JsonProperty("InvSmallImageFullPath")
    private String InvSmallImageFullPath;
    @JsonProperty("InvLargeImageFullPath")
    private String InvLargeImageFullPath;
    @JsonProperty("ActivateOnlineCC")
    private Boolean activateOnlineCC;
    @JsonProperty("AltTag")
    private Object altTag;
    @JsonProperty("AwardDollar")
    private Object awardDollar;
    @JsonProperty("AwardPoints")
    private Object awardPoints;
    @JsonProperty("BSSetup_Card")
    private Boolean bSSetupCard;
    @JsonProperty("BSSetup_DeliveryOption")
    private Boolean bSSetupDeliveryOption;
    @JsonProperty("BSSetup_PayAtStore")
    private Boolean bSSetupPayAtStore;
    @JsonProperty("Caption")
    private Object caption;
    @JsonProperty("CartID")
    private Integer cartID;
    @JsonProperty("CustomerName")
    private Object customerName;
    @JsonProperty("DrinkRecipes")
    private Boolean drinkRecipes;
    @JsonProperty("EmailContent")
    private Object emailContent;
    @JsonProperty("EmployeeId")
    private Integer employeeId;
    @JsonProperty("Extend_desc")
    private String extendDesc;
    @JsonProperty("FacebookURL")
    private String facebookURL;
    @JsonProperty("FlatName")
    private Object flatName;
    @JsonProperty("FlgtmpCashTip")
    private Boolean flgtmpCashTip;
    @JsonProperty("GiftWrap")
    private Object giftWrap;
    @JsonProperty("GooglePlusURL")
    private String googlePlusURL;
    @JsonProperty("GrpMemo")
    private String GrpMemo;
    @JsonProperty("Grpcomment")
    private String Grpcomment;
    @JsonProperty("HandlingSurcharge")
    private Object handlingSurcharge;
    @JsonProperty("Header1")
    private String header1;
    @JsonProperty("Header2")
    private String header2;
    @JsonProperty("ISHandDelivery")
    private Boolean iSHandDelivery;
    @JsonProperty("InvLargeImage")
    private String invLargeImage;
    @JsonProperty("InvSmallImage")
    private String invSmallImage;
    @JsonProperty("InventoryRating")
    private String inventoryRating;
    @JsonProperty("IsDefault")
    private Integer isDefault;
    @JsonProperty("IsDelete")
    private Boolean isDelete;
    @JsonProperty("IsDeliverHome")
    private Boolean isDeliverHome;
    @JsonProperty("IsLoyaltyRewardEnable")
    private Object isLoyaltyRewardEnable;
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
    private String linkedInURL;
    @JsonProperty("Lock")
    private Object lock;
    @JsonProperty("LoyaltyCardNo")
    private Object loyaltyCardNo;
    @JsonProperty("LoyaltyOn")
    private Object loyaltyOn;
    @JsonProperty("LoyaltyPointsOnItem")
    private Object loyaltyPointsOnItem;
    @JsonProperty("LoyaltyType")
    private Object loyaltyType;
    @JsonProperty("Maxprice")
    private Object maxprice;
    @JsonProperty("Message")
    private Object message;
    @JsonProperty("Minprice")
    private Object minprice;
    @JsonProperty("MiscTax3")
    private Object miscTax3;
    @JsonProperty("NewAddition")
    private Boolean newAddition;
    @JsonProperty("Ounces")
    private Object ounces;
    @JsonProperty("Qty")
    private Object qty;
    @JsonProperty("QtyOnHand")
    private String qtyOnHand;
    @JsonProperty("ReturnDays")
    private Integer returnDays;
    @JsonProperty("ReturnDesc")
    private String returnDesc;
    @JsonProperty("ReturnPolicyId")
    private Integer returnPolicyId;
    @JsonProperty("RewardPoints")
    private Object rewardPoints;
    @JsonProperty("SalesTax1")
    private Object salesTax1;
    @JsonProperty("ShipmentMessage")
    private Object shipmentMessage;
    @JsonProperty("ShipmentTaxable")
    private Object shipmentTaxable;
    @JsonProperty("ShippingDesc")
    private String shippingDesc;
    @JsonProperty("ShippingPolicyId")
    private Integer shippingPolicyId;
    @JsonProperty("ShowItemButOutOfStock")
    private String showItemButOutOfStock;
    @JsonProperty("SmallImage")
    private String smallImage;
    @JsonProperty("SocialSharing")
    private Boolean socialSharing;
    @JsonProperty("SpecialComments")
    private Object specialComments;
    @JsonProperty("StaffPick")
    private Boolean staffPick;
    @JsonProperty("StorePhone")
    private Object storePhone;
    @JsonProperty("Tax1Name")
    private Object tax1Name;
    @JsonProperty("Tax2Name")
    private Object tax2Name;
    @JsonProperty("Tax3Name")
    private Object tax3Name;
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
    private String twitterURL;
    @JsonProperty("Type")
    private Object type;
    @JsonProperty("WineTax2")
    private Object wineTax2;
    @JsonProperty("WishlistCount")
    private Object wishlistCount;
    @JsonProperty("bottledeposit")
    private Object bottledeposit;
    @JsonProperty("cartPrice")
    private Object cartPrice;
    @JsonProperty("cost")
    private String cost;
    @JsonProperty("date")
    private Object date;
    @JsonProperty("departmentID")
    private String departmentID;
    @JsonProperty("departmentName")
    private String departmentName;
    @JsonProperty("desc1")
    private String desc1;
    @JsonProperty("discountID")
    private Integer discountID;
    @JsonProperty("discountName")
    private String discountName;
    @JsonProperty("flat")
    private Object flat;
    @JsonProperty("id")
    private Integer id;
    @JsonProperty("invReturnDays")
    private Integer invReturnDays;
    @JsonProperty("inv_count")
    private Integer invCount;
    @JsonProperty("inv_type")
    private String invType;
    @JsonProperty("invexpecteddate")
    private String invexpecteddate;
    @JsonProperty("invreturnid")
    private Integer invreturnid;
    @JsonProperty("itemWithImageCount")
    private Integer itemWithImageCount;
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
    private Integer pageCount;
    @JsonProperty("price")
    private String price;
    @JsonProperty("promo_price")
    private String promoPrice;
    @JsonProperty("promotionCount")
    private Integer promotionCount;
    @JsonProperty("sizedata")
    private Object sizedata;
    @JsonProperty("storeno")
    private String storeno;
    @JsonProperty("styleID")
    private String styleID;
    @JsonProperty("styleName")
    private String styleName;
    @JsonProperty("tax11")
    private Object tax11;
    @JsonProperty("tax22")
    private Object tax22;
    @JsonProperty("tax33")
    private Object tax33;
    @JsonProperty("tax44")
    private Object tax44;
    @JsonProperty("tax55")
    private Object tax55;
    @JsonProperty("tax66")
    private Object tax66;
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
    public Object getAwardDollar() {
        return awardDollar;
    }

    @JsonProperty("AwardDollar")
    public void setAwardDollar(Object awardDollar) {
        this.awardDollar = awardDollar;
    }

    @JsonProperty("AwardPoints")
    public Object getAwardPoints() {
        return awardPoints;
    }

    @JsonProperty("AwardPoints")
    public void setAwardPoints(Object awardPoints) {
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
    public Integer getCartID() {
        return cartID;
    }

    @JsonProperty("CartID")
    public void setCartID(Integer cartID) {
        this.cartID = cartID;
    }

    @JsonProperty("CustomerName")
    public Object getCustomerName() {
        return customerName;
    }

    @JsonProperty("CustomerName")
    public void setCustomerName(Object customerName) {
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
    public Integer getEmployeeId() {
        return employeeId;
    }

    @JsonProperty("EmployeeId")
    public void setEmployeeId(Integer employeeId) {
        this.employeeId = employeeId;
    }

    @JsonProperty("Extend_desc")
    public String getExtendDesc() {
        return extendDesc;
    }

    @JsonProperty("Extend_desc")
    public void setExtendDesc(String extendDesc) {
        this.extendDesc = extendDesc;
    }

    @JsonProperty("FacebookURL")
    public String getFacebookURL() {
        return facebookURL;
    }

    @JsonProperty("FacebookURL")
    public void setFacebookURL(String facebookURL) {
        this.facebookURL = facebookURL;
    }

    @JsonProperty("FlatName")
    public Object getFlatName() {
        return flatName;
    }

    @JsonProperty("FlatName")
    public void setFlatName(Object flatName) {
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
    public Object getGiftWrap() {
        return giftWrap;
    }

    @JsonProperty("GiftWrap")
    public void setGiftWrap(Object giftWrap) {
        this.giftWrap = giftWrap;
    }

    @JsonProperty("GooglePlusURL")
    public String getGooglePlusURL() {
        return googlePlusURL;
    }

    @JsonProperty("GooglePlusURL")
    public void setGooglePlusURL(String googlePlusURL) {
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

    @JsonProperty("HandlingSurcharge")
    public Object getHandlingSurcharge() {
        return handlingSurcharge;
    }

    @JsonProperty("HandlingSurcharge")
    public void setHandlingSurcharge(Object handlingSurcharge) {
        this.handlingSurcharge = handlingSurcharge;
    }

    @JsonProperty("Header1")
    public String getHeader1() {
        return header1;
    }

    @JsonProperty("Header1")
    public void setHeader1(String header1) {
        this.header1 = header1;
    }

    @JsonProperty("Header2")
    public String getHeader2() {
        return header2;
    }

    @JsonProperty("Header2")
    public void setHeader2(String header2) {
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
    public String getInventoryRating() {
        return inventoryRating;
    }

    @JsonProperty("InventoryRating")
    public void setInventoryRating(String inventoryRating) {
        this.inventoryRating = inventoryRating;
    }

    @JsonProperty("IsDefault")
    public Integer getIsDefault() {
        return isDefault;
    }

    @JsonProperty("IsDefault")
    public void setIsDefault(Integer isDefault) {
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
    public Object getIsLoyaltyRewardEnable() {
        return isLoyaltyRewardEnable;
    }

    @JsonProperty("IsLoyaltyRewardEnable")
    public void setIsLoyaltyRewardEnable(Object isLoyaltyRewardEnable) {
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
    public String getLinkedInURL() {
        return linkedInURL;
    }

    @JsonProperty("LinkedInURL")
    public void setLinkedInURL(String linkedInURL) {
        this.linkedInURL = linkedInURL;
    }

    @JsonProperty("Lock")
    public Object getLock() {
        return lock;
    }

    @JsonProperty("Lock")
    public void setLock(Object lock) {
        this.lock = lock;
    }

    @JsonProperty("LoyaltyCardNo")
    public Object getLoyaltyCardNo() {
        return loyaltyCardNo;
    }

    @JsonProperty("LoyaltyCardNo")
    public void setLoyaltyCardNo(Object loyaltyCardNo) {
        this.loyaltyCardNo = loyaltyCardNo;
    }

    @JsonProperty("LoyaltyOn")
    public Object getLoyaltyOn() {
        return loyaltyOn;
    }

    @JsonProperty("LoyaltyOn")
    public void setLoyaltyOn(Object loyaltyOn) {
        this.loyaltyOn = loyaltyOn;
    }

    @JsonProperty("LoyaltyPointsOnItem")
    public Object getLoyaltyPointsOnItem() {
        return loyaltyPointsOnItem;
    }

    @JsonProperty("LoyaltyPointsOnItem")
    public void setLoyaltyPointsOnItem(Object loyaltyPointsOnItem) {
        this.loyaltyPointsOnItem = loyaltyPointsOnItem;
    }

    @JsonProperty("LoyaltyType")
    public Object getLoyaltyType() {
        return loyaltyType;
    }

    @JsonProperty("LoyaltyType")
    public void setLoyaltyType(Object loyaltyType) {
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
    public Object getMiscTax3() {
        return miscTax3;
    }

    @JsonProperty("MiscTax3")
    public void setMiscTax3(Object miscTax3) {
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
    public Object getQty() {
        return qty;
    }

    @JsonProperty("Qty")
    public void setQty(Object qty) {
        this.qty = qty;
    }

    @JsonProperty("QtyOnHand")
    public String getQtyOnHand() {
        return qtyOnHand;
    }

    @JsonProperty("QtyOnHand")
    public void setQtyOnHand(String qtyOnHand) {
        this.qtyOnHand = qtyOnHand;
    }

    @JsonProperty("ReturnDays")
    public Integer getReturnDays() {
        return returnDays;
    }

    @JsonProperty("ReturnDays")
    public void setReturnDays(Integer returnDays) {
        this.returnDays = returnDays;
    }

    @JsonProperty("ReturnDesc")
    public String getReturnDesc() {
        return returnDesc;
    }

    @JsonProperty("ReturnDesc")
    public void setReturnDesc(String returnDesc) {
        this.returnDesc = returnDesc;
    }

    @JsonProperty("ReturnPolicyId")
    public Integer getReturnPolicyId() {
        return returnPolicyId;
    }

    @JsonProperty("ReturnPolicyId")
    public void setReturnPolicyId(Integer returnPolicyId) {
        this.returnPolicyId = returnPolicyId;
    }

    @JsonProperty("RewardPoints")
    public Object getRewardPoints() {
        return rewardPoints;
    }

    @JsonProperty("RewardPoints")
    public void setRewardPoints(Object rewardPoints) {
        this.rewardPoints = rewardPoints;
    }

    @JsonProperty("SalesTax1")
    public Object getSalesTax1() {
        return salesTax1;
    }

    @JsonProperty("SalesTax1")
    public void setSalesTax1(Object salesTax1) {
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
    public String getShippingDesc() {
        return shippingDesc;
    }

    @JsonProperty("ShippingDesc")
    public void setShippingDesc(String shippingDesc) {
        this.shippingDesc = shippingDesc;
    }

    @JsonProperty("ShippingPolicyId")
    public Integer getShippingPolicyId() {
        return shippingPolicyId;
    }

    @JsonProperty("ShippingPolicyId")
    public void setShippingPolicyId(Integer shippingPolicyId) {
        this.shippingPolicyId = shippingPolicyId;
    }

    @JsonProperty("ShowItemButOutOfStock")
    public String getShowItemButOutOfStock() {
        return showItemButOutOfStock;
    }

    @JsonProperty("ShowItemButOutOfStock")
    public void setShowItemButOutOfStock(String showItemButOutOfStock) {
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
    public Object getStorePhone() {
        return storePhone;
    }

    @JsonProperty("StorePhone")
    public void setStorePhone(Object storePhone) {
        this.storePhone = storePhone;
    }

    @JsonProperty("Tax1Name")
    public Object getTax1Name() {
        return tax1Name;
    }

    @JsonProperty("Tax1Name")
    public void setTax1Name(Object tax1Name) {
        this.tax1Name = tax1Name;
    }

    @JsonProperty("Tax2Name")
    public Object getTax2Name() {
        return tax2Name;
    }

    @JsonProperty("Tax2Name")
    public void setTax2Name(Object tax2Name) {
        this.tax2Name = tax2Name;
    }

    @JsonProperty("Tax3Name")
    public Object getTax3Name() {
        return tax3Name;
    }

    @JsonProperty("Tax3Name")
    public void setTax3Name(Object tax3Name) {
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
    public String getTwitterURL() {
        return twitterURL;
    }

    @JsonProperty("TwitterURL")
    public void setTwitterURL(String twitterURL) {
        this.twitterURL = twitterURL;
    }

    @JsonProperty("Type")
    public Object getType() {
        return type;
    }

    @JsonProperty("Type")
    public void setType(Object type) {
        this.type = type;
    }

    @JsonProperty("WineTax2")
    public Object getWineTax2() {
        return wineTax2;
    }

    @JsonProperty("WineTax2")
    public void setWineTax2(Object wineTax2) {
        this.wineTax2 = wineTax2;
    }

    @JsonProperty("WishlistCount")
    public Object getWishlistCount() {
        return wishlistCount;
    }

    @JsonProperty("WishlistCount")
    public void setWishlistCount(Object wishlistCount) {
        this.wishlistCount = wishlistCount;
    }

    @JsonProperty("bottledeposit")
    public Object getBottledeposit() {
        return bottledeposit;
    }

    @JsonProperty("bottledeposit")
    public void setBottledeposit(Object bottledeposit) {
        this.bottledeposit = bottledeposit;
    }

    @JsonProperty("cartPrice")
    public Object getCartPrice() {
        return cartPrice;
    }

    @JsonProperty("cartPrice")
    public void setCartPrice(Object cartPrice) {
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
    public String getDepartmentID() {
        return departmentID;
    }

    @JsonProperty("departmentID")
    public void setDepartmentID(String departmentID) {
        this.departmentID = departmentID;
    }

    @JsonProperty("departmentName")
    public String getDepartmentName() {
        return departmentName;
    }

    @JsonProperty("departmentName")
    public void setDepartmentName(String departmentName) {
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
    public Integer getDiscountID() {
        return discountID;
    }

    @JsonProperty("discountID")
    public void setDiscountID(Integer discountID) {
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
    public Object getFlat() {
        return flat;
    }

    @JsonProperty("flat")
    public void setFlat(Object flat) {
        this.flat = flat;
    }

    @JsonProperty("id")
    public Integer getId() {
        return id;
    }

    @JsonProperty("id")
    public void setId(Integer id) {
        this.id = id;
    }

    @JsonProperty("invReturnDays")
    public Integer getInvReturnDays() {
        return invReturnDays;
    }

    @JsonProperty("invReturnDays")
    public void setInvReturnDays(Integer invReturnDays) {
        this.invReturnDays = invReturnDays;
    }

    @JsonProperty("inv_count")
    public Integer getInvCount() {
        return invCount;
    }

    @JsonProperty("inv_count")
    public void setInvCount(Integer invCount) {
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
    public String getInvexpecteddate() {
        return invexpecteddate;
    }

    @JsonProperty("invexpecteddate")
    public void setInvexpecteddate(String invexpecteddate) {
        this.invexpecteddate = invexpecteddate;
    }

    @JsonProperty("invreturnid")
    public Integer getInvreturnid() {
        return invreturnid;
    }

    @JsonProperty("invreturnid")
    public void setInvreturnid(Integer invreturnid) {
        this.invreturnid = invreturnid;
    }

    @JsonProperty("itemWithImageCount")
    public Integer getItemWithImageCount() {
        return itemWithImageCount;
    }

    @JsonProperty("itemWithImageCount")
    public void setItemWithImageCount(Integer itemWithImageCount) {
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
    public Integer getPageCount() {
        return pageCount;
    }

    @JsonProperty("page_count")
    public void setPageCount(Integer pageCount) {
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
    public Integer getPromotionCount() {
        return promotionCount;
    }

    @JsonProperty("promotionCount")
    public void setPromotionCount(Integer promotionCount) {
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
    public String getStoreno() {
        return storeno;
    }

    @JsonProperty("storeno")
    public void setStoreno(String storeno) {
        this.storeno = storeno;
    }

    @JsonProperty("styleID")
    public String getStyleID() {
        return styleID;
    }

    @JsonProperty("styleID")
    public void setStyleID(String styleID) {
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
    public Object getTax11() {
        return tax11;
    }

    @JsonProperty("tax11")
    public void setTax11(Object tax11) {
        this.tax11 = tax11;
    }

    @JsonProperty("tax22")
    public Object getTax22() {
        return tax22;
    }

    @JsonProperty("tax22")
    public void setTax22(Object tax22) {
        this.tax22 = tax22;
    }

    @JsonProperty("tax33")
    public Object getTax33() {
        return tax33;
    }

    @JsonProperty("tax33")
    public void setTax33(Object tax33) {
        this.tax33 = tax33;
    }

    @JsonProperty("tax44")
    public Object getTax44() {
        return tax44;
    }

    @JsonProperty("tax44")
    public void setTax44(Object tax44) {
        this.tax44 = tax44;
    }

    @JsonProperty("tax55")
    public Object getTax55() {
        return tax55;
    }

    @JsonProperty("tax55")
    public void setTax55(Object tax55) {
        this.tax55 = tax55;
    }

    @JsonProperty("tax66")
    public Object getTax66() {
        return tax66;
    }

    @JsonProperty("tax66")
    public void setTax66(Object tax66) {
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

    public String getImagepath() {
        return imagepath;
    }

    public void setImagepath(String imagepath) {
        this.imagepath = imagepath;
    }


    public String getItemonPromotionIndicator() {
        return ItemonPromotionIndicator;
    }

    public void setItemonPromotionIndicator(String itemonPromotionIndicator) {
        ItemonPromotionIndicator = itemonPromotionIndicator;
    }
}
