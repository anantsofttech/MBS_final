package com.aspl.mbsmodel;

/**
 * Created by admin on 8/29/2018.
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
        "AllItem",
        "Announcement1",
        "Announcement2",
        "CartItems",
        "HomeContent",
        "HomePageData",
        "ItemsOnPromotion",
        "NewAddition",
        "PopularDepartment",
        "Promotion1",
        "Promotion2",
        "RecentViewedItems",
        "ShopByDepartment",
        "SpecialOffer",
        "SpecialOffer1",
        "SpecialOffer2",
        "SpecialOffer3",
        "SpecialOffer4",
        "StaffPick",
        "WishListItems",
        "YouMightAlsoLike"
})
public class TemplateModel {

    @JsonProperty("AllItem")
    private Boolean allItem;
    @JsonProperty("Announcement1")
    private Boolean announcement1;
    @JsonProperty("Announcement2")
    private Boolean announcement2;
    @JsonProperty("CartItems")
    private Boolean cartItems;
    @JsonProperty("HomeContent")
    private Boolean homeContent;
    @JsonProperty("HomePageData")
    private Boolean homePageData;
    @JsonProperty("ItemsOnPromotion")
    private Boolean itemsOnPromotion;
    @JsonProperty("NewAddition")
    private Boolean newAddition;
    @JsonProperty("PopularDepartment")
    private Boolean popularDepartment;
    @JsonProperty("Promotion1")
    private Boolean promotion1;
    @JsonProperty("Promotion2")
    private Boolean promotion2;
    @JsonProperty("RecentViewedItems")
    private Boolean recentViewedItems;
    @JsonProperty("ShopByDepartment")
    private Boolean shopByDepartment;
    @JsonProperty("SpecialOffer")
    private Boolean specialOffer;
    @JsonProperty("SpecialOffer1")
    private Boolean specialOffer1;
    @JsonProperty("SpecialOffer2")
    private Boolean specialOffer2;
    @JsonProperty("SpecialOffer3")
    private Boolean specialOffer3;
    @JsonProperty("SpecialOffer4")
    private Boolean specialOffer4;
    @JsonProperty("StaffPick")
    private Boolean staffPick;
    @JsonProperty("WishListItems")
    private Boolean wishListItems;
    @JsonProperty("YouMightAlsoLike")
    private Boolean youMightAlsoLike;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("AllItem")
    public Boolean getAllItem() {
        return allItem;
    }

    @JsonProperty("AllItem")
    public void setAllItem(Boolean allItem) {
        this.allItem = allItem;
    }

    @JsonProperty("Announcement1")
    public Boolean getAnnouncement1() {
        return announcement1;
    }

    @JsonProperty("Announcement1")
    public void setAnnouncement1(Boolean announcement1) {
        this.announcement1 = announcement1;
    }

    @JsonProperty("Announcement2")
    public Boolean getAnnouncement2() {
        return announcement2;
    }

    @JsonProperty("Announcement2")
    public void setAnnouncement2(Boolean announcement2) {
        this.announcement2 = announcement2;
    }

    @JsonProperty("CartItems")
    public Boolean getCartItems() {
        return cartItems;
    }

    @JsonProperty("CartItems")
    public void setCartItems(Boolean cartItems) {
        this.cartItems = cartItems;
    }

    @JsonProperty("HomeContent")
    public Boolean getHomeContent() {
        return homeContent;
    }

    @JsonProperty("HomeContent")
    public void setHomeContent(Boolean homeContent) {
        this.homeContent = homeContent;
    }

    @JsonProperty("HomePageData")
    public Boolean getHomePageData() {
        return homePageData;
    }

    @JsonProperty("HomePageData")
    public void setHomePageData(Boolean homePageData) {
        this.homePageData = homePageData;
    }

    @JsonProperty("ItemsOnPromotion")
    public Boolean getItemsOnPromotion() {
        return itemsOnPromotion;
    }

    @JsonProperty("ItemsOnPromotion")
    public void setItemsOnPromotion(Boolean itemsOnPromotion) {
        this.itemsOnPromotion = itemsOnPromotion;
    }

    @JsonProperty("NewAddition")
    public Boolean getNewAddition() {
        return newAddition;
    }

    @JsonProperty("NewAddition")
    public void setNewAddition(Boolean newAddition) {
        this.newAddition = newAddition;
    }

    @JsonProperty("PopularDepartment")
    public Boolean getPopularDepartment() {
        return popularDepartment;
    }

    @JsonProperty("PopularDepartment")
    public void setPopularDepartment(Boolean popularDepartment) {
        this.popularDepartment = popularDepartment;
    }

    @JsonProperty("Promotion1")
    public Boolean getPromotion1() {
        return promotion1;
    }

    @JsonProperty("Promotion1")
    public void setPromotion1(Boolean promotion1) {
        this.promotion1 = promotion1;
    }

    @JsonProperty("Promotion2")
    public Boolean getPromotion2() {
        return promotion2;
    }

    @JsonProperty("Promotion2")
    public void setPromotion2(Boolean promotion2) {
        this.promotion2 = promotion2;
    }

    @JsonProperty("RecentViewedItems")
    public Boolean getRecentViewedItems() {
        return recentViewedItems;
    }

    @JsonProperty("RecentViewedItems")
    public void setRecentViewedItems(Boolean recentViewedItems) {
        this.recentViewedItems = recentViewedItems;
    }

    @JsonProperty("ShopByDepartment")
    public Boolean getShopByDepartment() {
        return shopByDepartment;
    }

    @JsonProperty("ShopByDepartment")
    public void setShopByDepartment(Boolean shopByDepartment) {
        this.shopByDepartment = shopByDepartment;
    }

    @JsonProperty("SpecialOffer")
    public Boolean getSpecialOffer() {
        return specialOffer;
    }

    @JsonProperty("SpecialOffer")
    public void setSpecialOffer(Boolean specialOffer) {
        this.specialOffer = specialOffer;
    }

    @JsonProperty("SpecialOffer1")
    public Boolean getSpecialOffer1() {
        return specialOffer1;
    }

    @JsonProperty("SpecialOffer1")
    public void setSpecialOffer1(Boolean specialOffer1) {
        this.specialOffer1 = specialOffer1;
    }

    @JsonProperty("SpecialOffer2")
    public Boolean getSpecialOffer2() {
        return specialOffer2;
    }

    @JsonProperty("SpecialOffer2")
    public void setSpecialOffer2(Boolean specialOffer2) {
        this.specialOffer2 = specialOffer2;
    }

    @JsonProperty("SpecialOffer3")
    public Boolean getSpecialOffer3() {
        return specialOffer3;
    }

    @JsonProperty("SpecialOffer3")
    public void setSpecialOffer3(Boolean specialOffer3) {
        this.specialOffer3 = specialOffer3;
    }

    @JsonProperty("SpecialOffer4")
    public Boolean getSpecialOffer4() {
        return specialOffer4;
    }

    @JsonProperty("SpecialOffer4")
    public void setSpecialOffer4(Boolean specialOffer4) {
        this.specialOffer4 = specialOffer4;
    }

    @JsonProperty("StaffPick")
    public Boolean getStaffPick() {
        return staffPick;
    }

    @JsonProperty("StaffPick")
    public void setStaffPick(Boolean staffPick) {
        this.staffPick = staffPick;
    }

    @JsonProperty("WishListItems")
    public Boolean getWishListItems() {
        return wishListItems;
    }

    @JsonProperty("WishListItems")
    public void setWishListItems(Boolean wishListItems) {
        this.wishListItems = wishListItems;
    }

    @JsonProperty("YouMightAlsoLike")
    public Boolean getYouMightAlsoLike() {
        return youMightAlsoLike;
    }

    @JsonProperty("YouMightAlsoLike")
    public void setYouMightAlsoLike(Boolean youMightAlsoLike) {
        this.youMightAlsoLike = youMightAlsoLike;
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
