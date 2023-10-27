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
        "tax66"
})
public class Filter {

    @JsonProperty("ActivateOnlineCC")
    private Boolean activateOnlineCC;
    @JsonProperty("AltTag")
    private String altTag;
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
    private String caption;
    @JsonProperty("CartID")
    private Integer cartID;
    @JsonProperty("CustomerName")
    private String customerName;
    @JsonProperty("DrinkRecipes")
    private Boolean drinkRecipes;
    @JsonProperty("EmailContent")
    private String emailContent;
    @JsonProperty("EmployeeId")
    private Integer employeeId;
    @JsonProperty("Extend_desc")
    private String extendDesc;
    @JsonProperty("FacebookURL")
    private String facebookURL;
    @JsonProperty("FlatName")
    private String flatName;
    @JsonProperty("FlgtmpCashTip")
    private Boolean flgtmpCashTip;
    @JsonProperty("GiftWrap")
    private String giftWrap;
    @JsonProperty("GooglePlusURL")
    private String googlePlusURL;
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
    private String linkedInURL;
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
    private String maxprice;
    @JsonProperty("Message")
    private String message;
    @JsonProperty("Minprice")
    private String minprice;
    @JsonProperty("MiscTax3")
    private String miscTax3;
    @JsonProperty("NewAddition")
    private Boolean newAddition;
    @JsonProperty("Ounces")
    private String ounces;
    @JsonProperty("Qty")
    private String qty;
    @JsonProperty("QtyOnHand")
    private String qtyOnHand;
    @JsonProperty("ReturnDays")
    private Integer returnDays;
    @JsonProperty("ReturnDesc")
    private String returnDesc;
    @JsonProperty("ReturnPolicyId")
    private Integer returnPolicyId;
    @JsonProperty("RewardPoints")
    private String rewardPoints;
    @JsonProperty("SalesTax1")
    private String salesTax1;
    @JsonProperty("ShipmentMessage")
    private String shipmentMessage;
    @JsonProperty("ShipmentTaxable")
    private String shipmentTaxable;
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
    private String specialComments;
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
    private String twitterURL;
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
    private String date;
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
    private String flat;
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
    private String keywords;
    @JsonProperty("last_cost")
    private String lastCost;
    @JsonProperty("lstDepartment")
    private List<LstDepartment> lstDepartment = null;
    @JsonProperty("lstSize")
    private List<LstSize> lstSize = null;
    @JsonProperty("lstSpecialOffer")
    private String lstSpecialOffer;
    @JsonProperty("lstStyle")
    private List<LstStyle> lstStyle = null;
    @JsonProperty("page_count")
    private Integer pageCount;
    @JsonProperty("price")
    private String price;
    @JsonProperty("promo_price")
    private String promoPrice;
    @JsonProperty("promotionCount")
    private Integer promotionCount;
    @JsonProperty("sizedata")
    private String sizedata;
    @JsonProperty("storeno")
    private String storeno;
    @JsonProperty("styleID")
    private String styleID;
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
    private Map<String, String> additionalProperties = new HashMap<String, String>();
    private boolean isChecked;
    private boolean isPriceChecked;

    @JsonProperty("ActivateOnlineCC")
    public Boolean getActivateOnlineCC() {
        return activateOnlineCC;
    }

    @JsonProperty("ActivateOnlineCC")
    public void setActivateOnlineCC(Boolean activateOnlineCC) {
        this.activateOnlineCC = activateOnlineCC;
    }

    @JsonProperty("AltTag")
    public String getAltTag() {
        return (altTag == null) ? "" : altTag ;
    }

    @JsonProperty("AltTag")
    public void setAltTag(String altTag) {
        this.altTag = altTag;
    }

    @JsonProperty("AwardDollar")
    public String getAwardDollar() {
        return (awardDollar == null) ? "" : awardDollar ;
    }

    @JsonProperty("AwardDollar")
    public void setAwardDollar(String awardDollar) {
        this.awardDollar = awardDollar;
    }

    @JsonProperty("AwardPoints")
    public String getAwardPoints() {
        return (awardPoints == null) ? "" : awardPoints ;
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
    public String getCaption() {
        return (caption == null) ? "" : caption ;
    }

    @JsonProperty("Caption")
    public void setCaption(String caption) {
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
    public String getCustomerName() {
        return (customerName == null) ? "" : customerName ;
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
    public String getEmailContent() {
        return (emailContent == null) ? "" : emailContent ;
    }

    @JsonProperty("EmailContent")
    public void setEmailContent(String emailContent) {
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
        return (extendDesc == null) ? "" : extendDesc ;
    }

    @JsonProperty("Extend_desc")
    public void setExtendDesc(String extendDesc) {
        this.extendDesc = extendDesc;
    }

    @JsonProperty("FacebookURL")
    public String getFacebookURL() {
        return (facebookURL == null) ? "" : facebookURL ;
    }

    @JsonProperty("FacebookURL")
    public void setFacebookURL(String facebookURL) {
        this.facebookURL = facebookURL;
    }

    @JsonProperty("FlatName")
    public String getFlatName() {
        return (flatName == null) ? "" : flatName ;
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
    public String getGiftWrap() {
        return (giftWrap == null) ? "" : giftWrap ;
    }

    @JsonProperty("GiftWrap")
    public void setGiftWrap(String giftWrap) {
        this.giftWrap = giftWrap;
    }

    @JsonProperty("GooglePlusURL")
    public String getGooglePlusURL() {
        return (googlePlusURL == null) ? "" : googlePlusURL ;
    }

    @JsonProperty("GooglePlusURL")
    public void setGooglePlusURL(String googlePlusURL) {
        this.googlePlusURL = googlePlusURL;
    }

    @JsonProperty("Header1")
    public String getHeader1() {
        return (header1 == null) ? "" : header1 ;
    }

    @JsonProperty("Header1")
    public void setHeader1(String header1) {
        this.header1 = header1;
    }

    @JsonProperty("Header2")
    public String getHeader2() {
        return (header2 == null) ? "" : header2 ;
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
        return (invLargeImage == null) ? "" : invLargeImage ;
    }

    @JsonProperty("InvLargeImage")
    public void setInvLargeImage(String invLargeImage) {
        this.invLargeImage = invLargeImage;
    }

    @JsonProperty("InvSmallImage")
    public String getInvSmallImage() {
        return (invSmallImage == null) ? "" : invSmallImage ;
    }

    @JsonProperty("InvSmallImage")
    public void setInvSmallImage(String invSmallImage) {
        this.invSmallImage = invSmallImage;
    }

    @JsonProperty("InventoryRating")
    public String getInventoryRating() {
        return (inventoryRating == null) ? "" : inventoryRating ;
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
        return (itemMstId == null) ? "" : itemMstId ;
    }

    @JsonProperty("Item_mst_id")
    public void setItemMstId(String itemMstId) {
        this.itemMstId = itemMstId;
    }

    @JsonProperty("LargeImage")
    public String getLargeImage() {
        return (largeImage == null) ? "" : largeImage ;
    }

    @JsonProperty("LargeImage")
    public void setLargeImage(String largeImage) {
        this.largeImage = largeImage;
    }

    @JsonProperty("LinkedInURL")
    public String getLinkedInURL() {
        return (linkedInURL == null) ? "" : linkedInURL ;
    }

    @JsonProperty("LinkedInURL")
    public void setLinkedInURL(String linkedInURL) {
        this.linkedInURL = linkedInURL;
    }

    @JsonProperty("Lock")
    public String getLock() {
        return (lock == null) ? "" : lock ;
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
        return (loyaltyPointsOnItem == null) ? "" : loyaltyPointsOnItem ;
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
    public String getMaxprice() {
        return (maxprice == null) ? "0" : maxprice ;
    }

    @JsonProperty("Maxprice")
    public void setMaxprice(String maxprice) {
        this.maxprice = maxprice;
    }

    @JsonProperty("Message")
    public String getMessage() {
        return (message == null) ? "" : message ;
    }

    @JsonProperty("Message")
    public void setMessage(String message) {
        this.message = message;
    }

    @JsonProperty("Minprice")
    public String getMinprice() {
        return (minprice == null) ? "0" : minprice ;
    }

    @JsonProperty("Minprice")
    public void setMinprice(String minprice) {
        this.minprice = minprice;
    }

    @JsonProperty("MiscTax3")
    public String getMiscTax3() {
        return (miscTax3 == null) ? "" : miscTax3 ;
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
    public String getOunces() {
        return (ounces == null) ? "" : ounces ;
    }

    @JsonProperty("Ounces")
    public void setOunces(String ounces) {
        this.ounces = ounces;
    }

    @JsonProperty("Qty")
    public String getQty() {
        return (qty == null) ? "" : qty ;
    }

    @JsonProperty("Qty")
    public void setQty(String qty) {
        this.qty = qty;
    }

    @JsonProperty("QtyOnHand")
    public String getQtyOnHand() {
        return (qtyOnHand == null) ? "" : qtyOnHand ;
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
        return (returnDesc == null) ? "" : returnDesc ;
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
    public String getRewardPoints() {
        return (rewardPoints == null) ? "" : rewardPoints ;
    }

    @JsonProperty("RewardPoints")
    public void setRewardPoints(String rewardPoints) {
        this.rewardPoints = rewardPoints;
    }

    @JsonProperty("SalesTax1")
    public String getSalesTax1() {
        return (salesTax1 == null) ? "" : salesTax1 ;
    }

    @JsonProperty("SalesTax1")
    public void setSalesTax1(String salesTax1) {
        this.salesTax1 = salesTax1;
    }

    @JsonProperty("ShipmentMessage")
    public String getShipmentMessage() {
        return (shipmentMessage == null) ? "" : shipmentMessage ;
    }

    @JsonProperty("ShipmentMessage")
    public void setShipmentMessage(String shipmentMessage) {
        this.shipmentMessage = shipmentMessage;
    }

    @JsonProperty("ShipmentTaxable")
    public String getShipmentTaxable() {
        return (shipmentTaxable == null) ? "" : shipmentTaxable ;
    }

    @JsonProperty("ShipmentTaxable")
    public void setShipmentTaxable(String shipmentTaxable) {
        this.shipmentTaxable = shipmentTaxable;
    }

    @JsonProperty("ShippingDesc")
    public String getShippingDesc() {
        return (shippingDesc == null) ? "" : shippingDesc ;
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
        return (showItemButOutOfStock == null) ? "" : showItemButOutOfStock ;
    }

    @JsonProperty("ShowItemButOutOfStock")
    public void setShowItemButOutOfStock(String showItemButOutOfStock) {
        this.showItemButOutOfStock = showItemButOutOfStock;
    }

    @JsonProperty("SmallImage")
    public String getSmallImage() {
        return (smallImage == null) ? "" : smallImage ;
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
    public String getSpecialComments() {
        return (specialComments == null) ? "" : specialComments ;
    }

    @JsonProperty("SpecialComments")
    public void setSpecialComments(String specialComments) {
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
        return (storePhone == null) ? "" : storePhone ;
    }

    @JsonProperty("StorePhone")
    public void setStorePhone(String storePhone) {
        this.storePhone = storePhone;
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
        return (tipSubTotalValue == null) ? "" : tipSubTotalValue ;
    }

    @JsonProperty("TipSubTotalValue")
    public void setTipSubTotalValue(String tipSubTotalValue) {
        this.tipSubTotalValue = tipSubTotalValue;
    }

    @JsonProperty("TipValue")
    public String getTipValue() {
        return (tipValue == null) ? "" : tipValue ;
    }

    @JsonProperty("TipValue")
    public void setTipValue(String tipValue) {
        this.tipValue = tipValue;
    }

    @JsonProperty("TwitterURL")
    public String getTwitterURL() {
        return (twitterURL == null) ? "" : twitterURL ;
    }

    @JsonProperty("TwitterURL")
    public void setTwitterURL(String twitterURL) {
        this.twitterURL = twitterURL;
    }

    @JsonProperty("Type")
    public String getType() {
        return (type == null) ? "" : type ;
    }

    @JsonProperty("Type")
    public void setType(String type) {
        this.type = type;
    }

    @JsonProperty("WineTax2")
    public String getWineTax2() {
        return (wineTax2 == null) ? "" : wineTax2 ;
    }

    @JsonProperty("WineTax2")
    public void setWineTax2(String wineTax2) {
        this.wineTax2 = wineTax2;
    }

    @JsonProperty("WishlistCount")
    public String getWishlistCount() {
        return (wishlistCount == null) ? "" : wishlistCount ;
    }

    @JsonProperty("WishlistCount")
    public void setWishlistCount(String wishlistCount) {
        this.wishlistCount = wishlistCount;
    }

    @JsonProperty("bottledeposit")
    public String getBottledeposit() {
        return (bottledeposit == null) ? "" : bottledeposit ;
    }

    @JsonProperty("bottledeposit")
    public void setBottledeposit(String bottledeposit) {
        this.bottledeposit = bottledeposit;
    }

    @JsonProperty("cartPrice")
    public String getCartPrice() {
        return (cartPrice == null) ? "" : cartPrice ;
    }

    @JsonProperty("cartPrice")
    public void setCartPrice(String cartPrice) {
        this.cartPrice = cartPrice;
    }

    @JsonProperty("cost")
    public String getCost() {
        return (cost == null) ? "" : cost ;
    }

    @JsonProperty("cost")
    public void setCost(String cost) {
        this.cost = cost;
    }

    @JsonProperty("date")
    public String getDate() {
        return (date == null) ? "" : date ;
    }

    @JsonProperty("date")
    public void setDate(String date) {
        this.date = date;
    }

    @JsonProperty("departmentID")
    public String getDepartmentID() {
        return (departmentID == null) ? "" : departmentID ;
    }

    @JsonProperty("departmentID")
    public void setDepartmentID(String departmentID) {
        this.departmentID = departmentID;
    }

    @JsonProperty("departmentName")
    public String getDepartmentName() {
        return (departmentName == null) ? "" : departmentName ;
    }

    @JsonProperty("departmentName")
    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    @JsonProperty("desc1")
    public String getDesc1() {
        return (desc1 == null) ? "" : desc1 ;
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
        return (discountName == null) ? "" : discountName ;
    }

    @JsonProperty("discountName")
    public void setDiscountName(String discountName) {
        this.discountName = discountName;
    }

    @JsonProperty("flat")
    public String getFlat() {
        return (flat == null) ? "" : flat ;
    }

    @JsonProperty("flat")
    public void setFlat(String flat) {
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
        return (invType == null) ? "" : invType ;
    }

    @JsonProperty("inv_type")
    public void setInvType(String invType) {
        this.invType = invType;
    }

    @JsonProperty("invexpecteddate")
    public String getInvexpecteddate() {
        return (invexpecteddate == null) ? "" : invexpecteddate ;
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
    public String getKeywords() {
        return (keywords == null) ? "" : keywords ;
    }

    @JsonProperty("keywords")
    public void setKeywords(String keywords) {
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
    public List<LstDepartment> getLstDepartment() {
        return lstDepartment;
    }

    @JsonProperty("lstDepartment")
    public void setLstDepartment(List<LstDepartment> lstDepartment) {
        this.lstDepartment = lstDepartment;
    }

    @JsonProperty("lstSize")
    public List<LstSize> getLstSize() {
        return lstSize;
    }

    @JsonProperty("lstSize")
    public void setLstSize(List<LstSize> lstSize) {
        this.lstSize = lstSize;
    }

    @JsonProperty("lstSpecialOffer")
    public String getLstSpecialOffer() {
        return (lstSpecialOffer == null) ? "" : lstSpecialOffer ;
    }

    @JsonProperty("lstSpecialOffer")
    public void setLstSpecialOffer(String lstSpecialOffer) {
        this.lstSpecialOffer = lstSpecialOffer;
    }

    @JsonProperty("lstStyle")
    public List<LstStyle> getLstStyle() {
        return lstStyle;
    }

    @JsonProperty("lstStyle")
    public void setLstStyle(List<LstStyle> lstStyle) {
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
        return (price == null) ? "" : price ;
    }

    @JsonProperty("price")
    public void setPrice(String price) {
        this.price = price;
    }

    @JsonProperty("promo_price")
    public String getPromoPrice() {
        return (promoPrice == null) ? "" : promoPrice ;
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
    public String getSizedata() {
        return (sizedata == null) ? "" : sizedata ;
    }

    @JsonProperty("sizedata")
    public void setSizedata(String sizedata) {
        this.sizedata = sizedata;
    }

    @JsonProperty("storeno")
    public String getStoreno() {
        return (storeno == null) ? "" : storeno ;
    }

    @JsonProperty("storeno")
    public void setStoreno(String storeno) {
        this.storeno = storeno;
    }

    @JsonProperty("styleID")
    public String getStyleID() {
        return (styleID == null) ? "" : styleID ;
    }

    @JsonProperty("styleID")
    public void setStyleID(String styleID) {
        this.styleID = styleID;
    }

    @JsonProperty("styleName")
    public String getStyleName() {
        return (styleName == null) ? "" : styleName ;
    }

    @JsonProperty("styleName")
    public void setStyleName(String styleName) {
        this.styleName = styleName;
    }

    @JsonProperty("tax11")
    public String getTax11() {
        return (tax11 == null) ? "" : tax11 ;
    }

    @JsonProperty("tax11")
    public void setTax11(String tax11) {
        this.tax11 = tax11;
    }

    @JsonProperty("tax22")
    public String getTax22() {
        return (tax22 == null) ? "" : tax22 ;
    }

    @JsonProperty("tax22")
    public void setTax22(String tax22) {
        this.tax22 = tax22;
    }

    @JsonProperty("tax33")
    public String getTax33() {
        return (tax33 == null) ? "" : tax33 ;
    }

    @JsonProperty("tax33")
    public void setTax33(String tax33) {
        this.tax33 = tax33;
    }

    @JsonProperty("tax44")
    public String getTax44() {
        return (tax44 == null) ? "" : tax44 ;
    }

    @JsonProperty("tax44")
    public void setTax44(String tax44) {
        this.tax44 = tax44;
    }

    @JsonProperty("tax55")
    public String getTax55() {
        return (tax55 == null) ? "" : tax55 ;
    }

    @JsonProperty("tax55")
    public void setTax55(String tax55) {
        this.tax55 = tax55;
    }

    @JsonProperty("tax66")
    public String getTax66() {
        return (tax66 == null) ? "" : tax66 ;
    }

    public void setChecked(boolean isChecked) {
        this.isChecked = isChecked;
    }

    public boolean getChecked() {
        return isChecked/*(tax66 == null) ? "" : tax66 */;
    }

    public void setPriceChecked(boolean isPriceChecked) {
        this.isPriceChecked = isPriceChecked;
    }

    public boolean getPriceChecked() {
        return isPriceChecked/*(tax66 == null) ? "" : tax66 */;
    }

    @JsonProperty("tax66")
    public void setTax66(String tax66) {
        this.tax66 = tax66;
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