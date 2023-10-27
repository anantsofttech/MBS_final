package com.aspl.mbsmodel;
/*-----------------------------------com.aspl.CardModel.java-----------------------------------*/

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class CardModel {

    private Boolean activateOnlineCC;
    private Object altTag;
    private String awardDollar;
    private String awardPoints;
    private Boolean bSSetupCard;
    private Boolean bSSetupDeliveryOption;
    private Boolean bSSetupPayAtStore;
    private Object caption;
    private Long cartID;
    private String customerName;
    private Boolean drinkRecipes;
    private Object emailContent;
    private Long employeeId;
    private Object extendDesc;
    private Object facebookURL;
    private String flatName;
    private String giftWrap;
    private Object googlePlusURL;
    private Object header1;
    private Object header2;
    private Boolean iSHandDelivery;
    private String invLargeImage;
    private String invSmallImage;
    private Object inventoryRating;
    private Long isDefault;
    private Boolean isDelete;
    private Boolean isDeliverHome;
    private String isLoyaltyRewardEnable;
    private Boolean isMiscTax;
    private Boolean isSalesTax;
    private Boolean isSubscribed;
    private Boolean isSubscriptionDisplay;
    private Boolean isTotalSavingDisplay;
    private Boolean isVarifoneVault;
    private Boolean isWineTax;
    private Boolean isflat;
    private String itemMstId;
    private String largeImage;
    private Object linkedInURL;
    private String lock;
    private String loyaltyCardNo;
    private String loyaltyOn;
    private String loyaltyPointsOnItem;
    private String loyaltyType;
    private Object maxprice;
    private Object message;
    private Object minprice;
    private String miscTax3;
    private Boolean newAddition;
    private Object ounces;
    private String qty;
    private Object qtyOnHand;
    private Object returnDesc;
    private Long returnPolicyId;
    private String rewardPoints;
    private String salesTax1;
    private Object shipmentMessage;
    private Object shipmentTaxable;
    private Object shippingDesc;
    private Long shippingPolicyId;
    private Object showItemButOutOfStock;
    private String smallImage;
    private Boolean socialSharing;
    private Object specialComments;
    private Boolean staffPick;
    private String storePhone;
    private String tax1Name;
    private String tax2Name;
    private String tax3Name;
    private Object taxExamptMessage;
    private Object taxExamptNo;
    private Boolean tipCCValue;
    private Object tipSubTotalValue;
    private Object tipValue;
    private Object twitterURL;
    private String type;
    private String wineTax2;
    private String wishlistCount;
    private String bottledeposit;
    private String cartPrice;
    private String cost;
    private Object date;
    private Object departmentID;
    private Object departmentName;
    private String desc1;
    private Long discountID;
    private String discountName;
    private String flat;
    private Long id;
    private Long invCount;
    private String invType;
    private Object invexpecteddate;
    private Long invreturnid;
    private Long itemWithImageCount;
    private Object keywords;
    private String lastCost;
    private Object lstDepartment;
    private Object lstSize;
    private Object lstSpecialOffer;
    private Object lstStyle;
    private Long pageCount;
    private String price;
    private String promoPrice;
    private Long promotionCount;
    private Object sizedata;
    private Object storeno;
    private Object styleID;
    private String styleName;
    private String txtDataValue;
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    /**
     * No args constructor for use in serialization
     */
    public CardModel() {
    }

    /**
     * @param isTotalSavingDisplay
     * @param promoPrice
     * @param facebookURL
     * @param discountName
     * @param returnPolicyId
     * @param loyaltyCardNo
     * @param awardDollar
     * @param isSalesTax
     * @param desc1
     * @param iSHandDelivery
     * @param isDelete
     * @param extendDesc
     * @param itemMstId
     * @param isDefault
     * @param invLargeImage
     * @param lstStyle
     * @param giftWrap
     * @param isMiscTax
     * @param shipmentMessage
     * @param altTag
     * @param pageCount
     * @param cartPrice
     * @param isDeliverHome
     * @param price
     * @param shippingPolicyId
     * @param wineTax2
     * @param drinkRecipes
     * @param tipSubTotalValue
     * @param inventoryRating
     * @param newAddition
     * @param date
     * @param header2
     * @param header1
     * @param wishlistCount
     * @param isSubscriptionDisplay
     * @param tax1Name
     * @param lstDepartment
     * @param smallImage
     * @param isSubscribed
     * @param ounces
     * @param txtDataValue
     * @param largeImage
     * @param caption
     * @param loyaltyPointsOnItem
     * @param departmentName
     * @param googlePlusURL
     * @param taxExamptMessage
     * @param taxExamptNo
     * @param isLoyaltyRewardEnable
     * @param awardPoints
     * @param isWineTax
     * @param sizedata
     * @param minprice
     * @param invSmallImage
     * @param isVarifoneVault
     * @param discountID
     * @param bSSetupDeliveryOption
     * @param employeeId
     * @param tax2Name
     * @param styleID
     * @param tipValue
     * @param twitterURL
     * @param salesTax1
     * @param rewardPoints
     * @param activateOnlineCC
     * @param socialSharing
     * @param type
     * @param invreturnid
     * @param shipmentTaxable
     * @param bSSetupPayAtStore
     * @param returnDesc
     * @param shippingDesc
     * @param departmentID
     * @param keywords
     * @param flat
     * @param qty
     * @param maxprice
     * @param cartID
     * @param cost
     * @param lastCost
     * @param styleName
     * @param loyaltyOn
     * @param isflat
     * @param invexpecteddate
     * @param lstSpecialOffer
     * @param linkedInURL
     * @param invCount
     * @param storeno
     * @param promotionCount
     * @param storePhone
     * @param id
     * @param invType
     * @param itemWithImageCount
     * @param bSSetupCard
     * @param lstSize
     * @param flatName
     * @param customerName
     * @param bottledeposit
     * @param specialComments
     * @param lock
     * @param showItemButOutOfStock
     * @param staffPick
     * @param message
     * @param qtyOnHand
     * @param loyaltyType
     * @param emailContent
     * @param tax3Name
     * @param miscTax3
     * @param tipCCValue
     */
    public CardModel(Boolean activateOnlineCC, Object altTag, String awardDollar, String awardPoints, Boolean bSSetupCard, Boolean bSSetupDeliveryOption, Boolean bSSetupPayAtStore, Object caption, Long cartID, String customerName, Boolean drinkRecipes, Object emailContent, Long employeeId, Object extendDesc, Object facebookURL, String flatName, String giftWrap, Object googlePlusURL, Object header1, Object header2, Boolean iSHandDelivery, String invLargeImage, String invSmallImage, Object inventoryRating, Long isDefault, Boolean isDelete, Boolean isDeliverHome, String isLoyaltyRewardEnable, Boolean isMiscTax, Boolean isSalesTax, Boolean isSubscribed, Boolean isSubscriptionDisplay, Boolean isTotalSavingDisplay, Boolean isVarifoneVault, Boolean isWineTax, Boolean isflat, String itemMstId, String largeImage, Object linkedInURL, String lock, String loyaltyCardNo, String loyaltyOn, String loyaltyPointsOnItem, String loyaltyType, Object maxprice, Object message, Object minprice, String miscTax3, Boolean newAddition, Object ounces, String qty, Object qtyOnHand, Object returnDesc, Long returnPolicyId, String rewardPoints, String salesTax1, Object shipmentMessage, Object shipmentTaxable, Object shippingDesc, Long shippingPolicyId, Object showItemButOutOfStock, String smallImage, Boolean socialSharing, Object specialComments, Boolean staffPick, String storePhone, String tax1Name, String tax2Name, String tax3Name, Object taxExamptMessage, Object taxExamptNo, Boolean tipCCValue, Object tipSubTotalValue, Object tipValue, Object twitterURL, String type, String wineTax2, String wishlistCount, String bottledeposit, String cartPrice, String cost, Object date, Object departmentID, Object departmentName, String desc1, Long discountID, String discountName, String flat, Long id, Long invCount, String invType, Object invexpecteddate, Long invreturnid, Long itemWithImageCount, Object keywords, String lastCost, Object lstDepartment, Object lstSize, Object lstSpecialOffer, Object lstStyle, Long pageCount, String price, String promoPrice, Long promotionCount, Object sizedata, Object storeno, Object styleID, String styleName, String txtDataValue) {
        super();
        this.activateOnlineCC = activateOnlineCC;
        this.altTag = altTag;
        this.awardDollar = awardDollar;
        this.awardPoints = awardPoints;
        this.bSSetupCard = bSSetupCard;
        this.bSSetupDeliveryOption = bSSetupDeliveryOption;
        this.bSSetupPayAtStore = bSSetupPayAtStore;
        this.caption = caption;
        this.cartID = cartID;
        this.customerName = customerName;
        this.drinkRecipes = drinkRecipes;
        this.emailContent = emailContent;
        this.employeeId = employeeId;
        this.extendDesc = extendDesc;
        this.facebookURL = facebookURL;
        this.flatName = flatName;
        this.giftWrap = giftWrap;
        this.googlePlusURL = googlePlusURL;
        this.header1 = header1;
        this.header2 = header2;
        this.iSHandDelivery = iSHandDelivery;
        this.invLargeImage = invLargeImage;
        this.invSmallImage = invSmallImage;
        this.inventoryRating = inventoryRating;
        this.isDefault = isDefault;
        this.isDelete = isDelete;
        this.isDeliverHome = isDeliverHome;
        this.isLoyaltyRewardEnable = isLoyaltyRewardEnable;
        this.isMiscTax = isMiscTax;
        this.isSalesTax = isSalesTax;
        this.isSubscribed = isSubscribed;
        this.isSubscriptionDisplay = isSubscriptionDisplay;
        this.isTotalSavingDisplay = isTotalSavingDisplay;
        this.isVarifoneVault = isVarifoneVault;
        this.isWineTax = isWineTax;
        this.isflat = isflat;
        this.itemMstId = itemMstId;
        this.largeImage = largeImage;
        this.linkedInURL = linkedInURL;
        this.lock = lock;
        this.loyaltyCardNo = loyaltyCardNo;
        this.loyaltyOn = loyaltyOn;
        this.loyaltyPointsOnItem = loyaltyPointsOnItem;
        this.loyaltyType = loyaltyType;
        this.maxprice = maxprice;
        this.message = message;
        this.minprice = minprice;
        this.miscTax3 = miscTax3;
        this.newAddition = newAddition;
        this.ounces = ounces;
        this.qty = qty;
        this.qtyOnHand = qtyOnHand;
        this.returnDesc = returnDesc;
        this.returnPolicyId = returnPolicyId;
        this.rewardPoints = rewardPoints;
        this.salesTax1 = salesTax1;
        this.shipmentMessage = shipmentMessage;
        this.shipmentTaxable = shipmentTaxable;
        this.shippingDesc = shippingDesc;
        this.shippingPolicyId = shippingPolicyId;
        this.showItemButOutOfStock = showItemButOutOfStock;
        this.smallImage = smallImage;
        this.socialSharing = socialSharing;
        this.specialComments = specialComments;
        this.staffPick = staffPick;
        this.storePhone = storePhone;
        this.tax1Name = tax1Name;
        this.tax2Name = tax2Name;
        this.tax3Name = tax3Name;
        this.taxExamptMessage = taxExamptMessage;
        this.taxExamptNo = taxExamptNo;
        this.tipCCValue = tipCCValue;
        this.tipSubTotalValue = tipSubTotalValue;
        this.tipValue = tipValue;
        this.twitterURL = twitterURL;
        this.type = type;
        this.wineTax2 = wineTax2;
        this.wishlistCount = wishlistCount;
        this.bottledeposit = bottledeposit;
        this.cartPrice = cartPrice;
        this.cost = cost;
        this.date = date;
        this.departmentID = departmentID;
        this.departmentName = departmentName;
        this.desc1 = desc1;
        this.discountID = discountID;
        this.discountName = discountName;
        this.flat = flat;
        this.id = id;
        this.invCount = invCount;
        this.invType = invType;
        this.invexpecteddate = invexpecteddate;
        this.invreturnid = invreturnid;
        this.itemWithImageCount = itemWithImageCount;
        this.keywords = keywords;
        this.lastCost = lastCost;
        this.lstDepartment = lstDepartment;
        this.lstSize = lstSize;
        this.lstSpecialOffer = lstSpecialOffer;
        this.lstStyle = lstStyle;
        this.pageCount = pageCount;
        this.price = price;
        this.promoPrice = promoPrice;
        this.promotionCount = promotionCount;
        this.sizedata = sizedata;
        this.storeno = storeno;
        this.styleID = styleID;
        this.styleName = styleName;
        this.txtDataValue = txtDataValue;
    }

    public CardModel(JSONObject fotterObj) throws JSONException {
        this.desc1=fotterObj.getString("desc1");
        this.qty=fotterObj.getString("Qty");
        this.price=fotterObj.getString("price");
        this.invLargeImage = fotterObj.getString("InvLargeImage");
        this.invSmallImage = fotterObj.getString("InvSmallImage");



    }

    public Boolean getActivateOnlineCC() {
        return activateOnlineCC;
    }

    public void setActivateOnlineCC(Boolean activateOnlineCC) {
        this.activateOnlineCC = activateOnlineCC;
    }

    public Object getAltTag() {
        return altTag;
    }

    public void setAltTag(Object altTag) {
        this.altTag = altTag;
    }

    public String getAwardDollar() {
        return awardDollar;
    }

    public void setAwardDollar(String awardDollar) {
        this.awardDollar = awardDollar;
    }

    public String getAwardPoints() {
        return awardPoints;
    }

    public void setAwardPoints(String awardPoints) {
        this.awardPoints = awardPoints;
    }

    public Boolean getBSSetupCard() {
        return bSSetupCard;
    }

    public void setBSSetupCard(Boolean bSSetupCard) {
        this.bSSetupCard = bSSetupCard;
    }

    public Boolean getBSSetupDeliveryOption() {
        return bSSetupDeliveryOption;
    }

    public void setBSSetupDeliveryOption(Boolean bSSetupDeliveryOption) {
        this.bSSetupDeliveryOption = bSSetupDeliveryOption;
    }

    public Boolean getBSSetupPayAtStore() {
        return bSSetupPayAtStore;
    }

    public void setBSSetupPayAtStore(Boolean bSSetupPayAtStore) {
        this.bSSetupPayAtStore = bSSetupPayAtStore;
    }

    public Object getCaption() {
        return caption;
    }

    public void setCaption(Object caption) {
        this.caption = caption;
    }

    public Long getCartID() {
        return cartID;
    }

    public void setCartID(Long cartID) {
        this.cartID = cartID;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public Boolean getDrinkRecipes() {
        return drinkRecipes;
    }

    public void setDrinkRecipes(Boolean drinkRecipes) {
        this.drinkRecipes = drinkRecipes;
    }

    public Object getEmailContent() {
        return emailContent;
    }

    public void setEmailContent(Object emailContent) {
        this.emailContent = emailContent;
    }

    public Long getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Long employeeId) {
        this.employeeId = employeeId;
    }

    public Object getExtendDesc() {
        return extendDesc;
    }

    public void setExtendDesc(Object extendDesc) {
        this.extendDesc = extendDesc;
    }

    public Object getFacebookURL() {
        return facebookURL;
    }

    public void setFacebookURL(Object facebookURL) {
        this.facebookURL = facebookURL;
    }

    public String getFlatName() {
        return flatName;
    }

    public void setFlatName(String flatName) {
        this.flatName = flatName;
    }

    public String getGiftWrap() {
        return giftWrap;
    }

    public void setGiftWrap(String giftWrap) {
        this.giftWrap = giftWrap;
    }

    public Object getGooglePlusURL() {
        return googlePlusURL;
    }

    public void setGooglePlusURL(Object googlePlusURL) {
        this.googlePlusURL = googlePlusURL;
    }

    public Object getHeader1() {
        return header1;
    }

    public void setHeader1(Object header1) {
        this.header1 = header1;
    }

    public Object getHeader2() {
        return header2;
    }

    public void setHeader2(Object header2) {
        this.header2 = header2;
    }

    public Boolean getISHandDelivery() {
        return iSHandDelivery;
    }

    public void setISHandDelivery(Boolean iSHandDelivery) {
        this.iSHandDelivery = iSHandDelivery;
    }

    public String getInvLargeImage() {
        return invLargeImage;
    }

    public void setInvLargeImage(String invLargeImage) {
        this.invLargeImage = invLargeImage;
    }

    public String getInvSmallImage() {
        return invSmallImage;
    }

    public void setInvSmallImage(String invSmallImage) {
        this.invSmallImage = invSmallImage;
    }

    public Object getInventoryRating() {
        return inventoryRating;
    }

    public void setInventoryRating(Object inventoryRating) {
        this.inventoryRating = inventoryRating;
    }

    public Long getIsDefault() {
        return isDefault;
    }

    public void setIsDefault(Long isDefault) {
        this.isDefault = isDefault;
    }

    public Boolean getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(Boolean isDelete) {
        this.isDelete = isDelete;
    }

    public Boolean getIsDeliverHome() {
        return isDeliverHome;
    }

    public void setIsDeliverHome(Boolean isDeliverHome) {
        this.isDeliverHome = isDeliverHome;
    }

    public String getIsLoyaltyRewardEnable() {
        return isLoyaltyRewardEnable;
    }

    public void setIsLoyaltyRewardEnable(String isLoyaltyRewardEnable) {
        this.isLoyaltyRewardEnable = isLoyaltyRewardEnable;
    }

    public Boolean getIsMiscTax() {
        return isMiscTax;
    }

    public void setIsMiscTax(Boolean isMiscTax) {
        this.isMiscTax = isMiscTax;
    }

    public Boolean getIsSalesTax() {
        return isSalesTax;
    }

    public void setIsSalesTax(Boolean isSalesTax) {
        this.isSalesTax = isSalesTax;
    }

    public Boolean getIsSubscribed() {
        return isSubscribed;
    }

    public void setIsSubscribed(Boolean isSubscribed) {
        this.isSubscribed = isSubscribed;
    }

    public Boolean getIsSubscriptionDisplay() {
        return isSubscriptionDisplay;
    }

    public void setIsSubscriptionDisplay(Boolean isSubscriptionDisplay) {
        this.isSubscriptionDisplay = isSubscriptionDisplay;
    }

    public Boolean getIsTotalSavingDisplay() {
        return isTotalSavingDisplay;
    }

    public void setIsTotalSavingDisplay(Boolean isTotalSavingDisplay) {
        this.isTotalSavingDisplay = isTotalSavingDisplay;
    }

    public Boolean getIsVarifoneVault() {
        return isVarifoneVault;
    }

    public void setIsVarifoneVault(Boolean isVarifoneVault) {
        this.isVarifoneVault = isVarifoneVault;
    }

    public Boolean getIsWineTax() {
        return isWineTax;
    }

    public void setIsWineTax(Boolean isWineTax) {
        this.isWineTax = isWineTax;
    }

    public Boolean getIsflat() {
        return isflat;
    }

    public void setIsflat(Boolean isflat) {
        this.isflat = isflat;
    }

    public String getItemMstId() {
        return itemMstId;
    }

    public void setItemMstId(String itemMstId) {
        this.itemMstId = itemMstId;
    }

    public String getLargeImage() {
        return largeImage;
    }

    public void setLargeImage(String largeImage) {
        this.largeImage = largeImage;
    }

    public Object getLinkedInURL() {
        return linkedInURL;
    }

    public void setLinkedInURL(Object linkedInURL) {
        this.linkedInURL = linkedInURL;
    }

    public String getLock() {
        return lock;
    }

    public void setLock(String lock) {
        this.lock = lock;
    }

    public String getLoyaltyCardNo() {
        return loyaltyCardNo;
    }

    public void setLoyaltyCardNo(String loyaltyCardNo) {
        this.loyaltyCardNo = loyaltyCardNo;
    }

    public String getLoyaltyOn() {
        return loyaltyOn;
    }

    public void setLoyaltyOn(String loyaltyOn) {
        this.loyaltyOn = loyaltyOn;
    }

    public String getLoyaltyPointsOnItem() {
        return loyaltyPointsOnItem;
    }

    public void setLoyaltyPointsOnItem(String loyaltyPointsOnItem) {
        this.loyaltyPointsOnItem = loyaltyPointsOnItem;
    }

    public String getLoyaltyType() {
        return loyaltyType;
    }

    public void setLoyaltyType(String loyaltyType) {
        this.loyaltyType = loyaltyType;
    }

    public Object getMaxprice() {
        return maxprice;
    }

    public void setMaxprice(Object maxprice) {
        this.maxprice = maxprice;
    }

    public Object getMessage() {
        return message;
    }

    public void setMessage(Object message) {
        this.message = message;
    }

    public Object getMinprice() {
        return minprice;
    }

    public void setMinprice(Object minprice) {
        this.minprice = minprice;
    }

    public String getMiscTax3() {
        return miscTax3;
    }

    public void setMiscTax3(String miscTax3) {
        this.miscTax3 = miscTax3;
    }

    public Boolean getNewAddition() {
        return newAddition;
    }

    public void setNewAddition(Boolean newAddition) {
        this.newAddition = newAddition;
    }

    public Object getOunces() {
        return ounces;
    }

    public void setOunces(Object ounces) {
        this.ounces = ounces;
    }

    public String getQty() {
        return qty;
    }

    public void setQty(String qty) {
        this.qty = qty;
    }

    public Object getQtyOnHand() {
        return qtyOnHand;
    }

    public void setQtyOnHand(Object qtyOnHand) {
        this.qtyOnHand = qtyOnHand;
    }

    public Object getReturnDesc() {
        return returnDesc;
    }

    public void setReturnDesc(Object returnDesc) {
        this.returnDesc = returnDesc;
    }

    public Long getReturnPolicyId() {
        return returnPolicyId;
    }

    public void setReturnPolicyId(Long returnPolicyId) {
        this.returnPolicyId = returnPolicyId;
    }

    public String getRewardPoints() {
        return rewardPoints;
    }

    public void setRewardPoints(String rewardPoints) {
        this.rewardPoints = rewardPoints;
    }

    public String getSalesTax1() {
        return salesTax1;
    }

    public void setSalesTax1(String salesTax1) {
        this.salesTax1 = salesTax1;
    }

    public Object getShipmentMessage() {
        return shipmentMessage;
    }

    public void setShipmentMessage(Object shipmentMessage) {
        this.shipmentMessage = shipmentMessage;
    }

    public Object getShipmentTaxable() {
        return shipmentTaxable;
    }

    public void setShipmentTaxable(Object shipmentTaxable) {
        this.shipmentTaxable = shipmentTaxable;
    }

    public Object getShippingDesc() {
        return shippingDesc;
    }

    public void setShippingDesc(Object shippingDesc) {
        this.shippingDesc = shippingDesc;
    }

    public Long getShippingPolicyId() {
        return shippingPolicyId;
    }

    public void setShippingPolicyId(Long shippingPolicyId) {
        this.shippingPolicyId = shippingPolicyId;
    }

    public Object getShowItemButOutOfStock() {
        return showItemButOutOfStock;
    }

    public void setShowItemButOutOfStock(Object showItemButOutOfStock) {
        this.showItemButOutOfStock = showItemButOutOfStock;
    }

    public String getSmallImage() {
        return smallImage;
    }

    public void setSmallImage(String smallImage) {
        this.smallImage = smallImage;
    }

    public Boolean getSocialSharing() {
        return socialSharing;
    }

    public void setSocialSharing(Boolean socialSharing) {
        this.socialSharing = socialSharing;
    }

    public Object getSpecialComments() {
        return specialComments;
    }

    public void setSpecialComments(Object specialComments) {
        this.specialComments = specialComments;
    }

    public Boolean getStaffPick() {
        return staffPick;
    }

    public void setStaffPick(Boolean staffPick) {
        this.staffPick = staffPick;
    }

    public String getStorePhone() {
        return storePhone;
    }

    public void setStorePhone(String storePhone) {
        this.storePhone = storePhone;
    }

    public String getTax1Name() {
        return tax1Name;
    }

    public void setTax1Name(String tax1Name) {
        this.tax1Name = tax1Name;
    }

    public String getTax2Name() {
        return tax2Name;
    }

    public void setTax2Name(String tax2Name) {
        this.tax2Name = tax2Name;
    }

    public String getTax3Name() {
        return tax3Name;
    }

    public void setTax3Name(String tax3Name) {
        this.tax3Name = tax3Name;
    }

    public Object getTaxExamptMessage() {
        return taxExamptMessage;
    }

    public void setTaxExamptMessage(Object taxExamptMessage) {
        this.taxExamptMessage = taxExamptMessage;
    }

    public Object getTaxExamptNo() {
        return taxExamptNo;
    }

    public void setTaxExamptNo(Object taxExamptNo) {
        this.taxExamptNo = taxExamptNo;
    }

    public Boolean getTipCCValue() {
        return tipCCValue;
    }

    public void setTipCCValue(Boolean tipCCValue) {
        this.tipCCValue = tipCCValue;
    }

    public Object getTipSubTotalValue() {
        return tipSubTotalValue;
    }

    public void setTipSubTotalValue(Object tipSubTotalValue) {
        this.tipSubTotalValue = tipSubTotalValue;
    }

    public Object getTipValue() {
        return tipValue;
    }

    public void setTipValue(Object tipValue) {
        this.tipValue = tipValue;
    }

    public Object getTwitterURL() {
        return twitterURL;
    }

    public void setTwitterURL(Object twitterURL) {
        this.twitterURL = twitterURL;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getWineTax2() {
        return wineTax2;
    }

    public void setWineTax2(String wineTax2) {
        this.wineTax2 = wineTax2;
    }

    public String getWishlistCount() {
        return wishlistCount;
    }

    public void setWishlistCount(String wishlistCount) {
        this.wishlistCount = wishlistCount;
    }

    public String getBottledeposit() {
        return bottledeposit;
    }

    public void setBottledeposit(String bottledeposit) {
        this.bottledeposit = bottledeposit;
    }

    public String getCartPrice() {
        return cartPrice;
    }

    public void setCartPrice(String cartPrice) {
        this.cartPrice = cartPrice;
    }

    public String getCost() {
        return cost;
    }

    public void setCost(String cost) {
        this.cost = cost;
    }

    public Object getDate() {
        return date;
    }

    public void setDate(Object date) {
        this.date = date;
    }

    public Object getDepartmentID() {
        return departmentID;
    }

    public void setDepartmentID(Object departmentID) {
        this.departmentID = departmentID;
    }

    public Object getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(Object departmentName) {
        this.departmentName = departmentName;
    }

    public String getDesc1() {
        return desc1;
    }

    public void setDesc1(String desc1) {
        this.desc1 = desc1;
    }

    public Long getDiscountID() {
        return discountID;
    }

    public void setDiscountID(Long discountID) {
        this.discountID = discountID;
    }

    public String getDiscountName() {
        return discountName;
    }

    public void setDiscountName(String discountName) {
        this.discountName = discountName;
    }

    public String getFlat() {
        return flat;
    }

    public void setFlat(String flat) {
        this.flat = flat;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getInvCount() {
        return invCount;
    }

    public void setInvCount(Long invCount) {
        this.invCount = invCount;
    }

    public String getInvType() {
        return invType;
    }

    public void setInvType(String invType) {
        this.invType = invType;
    }

    public Object getInvexpecteddate() {
        return invexpecteddate;
    }

    public void setInvexpecteddate(Object invexpecteddate) {
        this.invexpecteddate = invexpecteddate;
    }

    public Long getInvreturnid() {
        return invreturnid;
    }

    public void setInvreturnid(Long invreturnid) {
        this.invreturnid = invreturnid;
    }

    public Long getItemWithImageCount() {
        return itemWithImageCount;
    }

    public void setItemWithImageCount(Long itemWithImageCount) {
        this.itemWithImageCount = itemWithImageCount;
    }

    public Object getKeywords() {
        return keywords;
    }

    public void setKeywords(Object keywords) {
        this.keywords = keywords;
    }

    public String getLastCost() {
        return lastCost;
    }

    public void setLastCost(String lastCost) {
        this.lastCost = lastCost;
    }

    public Object getLstDepartment() {
        return lstDepartment;
    }

    public void setLstDepartment(Object lstDepartment) {
        this.lstDepartment = lstDepartment;
    }

    public Object getLstSize() {
        return lstSize;
    }

    public void setLstSize(Object lstSize) {
        this.lstSize = lstSize;
    }

    public Object getLstSpecialOffer() {
        return lstSpecialOffer;
    }

    public void setLstSpecialOffer(Object lstSpecialOffer) {
        this.lstSpecialOffer = lstSpecialOffer;
    }

    public Object getLstStyle() {
        return lstStyle;
    }

    public void setLstStyle(Object lstStyle) {
        this.lstStyle = lstStyle;
    }

    public Long getPageCount() {
        return pageCount;
    }

    public void setPageCount(Long pageCount) {
        this.pageCount = pageCount;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getPromoPrice() {
        return promoPrice;
    }

    public void setPromoPrice(String promoPrice) {
        this.promoPrice = promoPrice;
    }

    public Long getPromotionCount() {
        return promotionCount;
    }

    public void setPromotionCount(Long promotionCount) {
        this.promotionCount = promotionCount;
    }

    public Object getSizedata() {
        return sizedata;
    }

    public void setSizedata(Object sizedata) {
        this.sizedata = sizedata;
    }

    public Object getStoreno() {
        return storeno;
    }

    public void setStoreno(Object storeno) {
        this.storeno = storeno;
    }

    public Object getStyleID() {
        return styleID;
    }

    public void setStyleID(Object styleID) {
        this.styleID = styleID;
    }

    public String getStyleName() {
        return styleName;
    }

    public void setStyleName(String styleName) {
        this.styleName = styleName;
    }

    public String getTxtDataValue() {
        return txtDataValue;
    }

    public void setTxtDataValue(String txtDataValue) {
        this.txtDataValue = txtDataValue;
    }

    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}