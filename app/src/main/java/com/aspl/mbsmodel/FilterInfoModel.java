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
        "DiscountGroup",
        "ItemWithImageOnlyDisplay",
        "SearchBySize",
        "SearchBy_PricePoint",
        "SearchBy_Promotion",
        "SearchBy_SpecialOffer",
        "SearchBy_Style",
        "Searchby_Department",
        "Searchby_Price",
        "Searchby_Pricegtlt",
        "SizeDisplay",
        "StyleDisplay",
        "searchby_Desc"
})
public class FilterInfoModel {

    @JsonProperty("DiscountGroup")
    private String discountGroup;
    @JsonProperty("ItemWithImageOnlyDisplay")
    private Boolean itemWithImageOnlyDisplay;
    @JsonProperty("SearchBySize")
    private Boolean searchBySize;
    @JsonProperty("SearchBy_PricePoint")
    private Boolean searchByPricePoint;
    @JsonProperty("SearchBy_Promotion")
    private Boolean searchByPromotion;
    @JsonProperty("SearchBy_SpecialOffer")
    private Boolean searchBySpecialOffer;
    @JsonProperty("SearchBy_Style")
    private Boolean searchByStyle;
    @JsonProperty("Searchby_Department")
    private Boolean searchbyDepartment;
    @JsonProperty("Searchby_Price")
    private Double searchbyPrice;
    @JsonProperty("Searchby_Pricegtlt")
    private String searchbyPricegtlt;
    @JsonProperty("SizeDisplay")
    private String sizeDisplay;
    @JsonProperty("StyleDisplay")
    private String styleDisplay;
    @JsonProperty("searchby_Desc")
    private Boolean searchbyDesc;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("DiscountGroup")
    public String getDiscountGroup() {
        return discountGroup;
    }

    @JsonProperty("DiscountGroup")
    public void setDiscountGroup(String discountGroup) {
        this.discountGroup = discountGroup;
    }

    @JsonProperty("ItemWithImageOnlyDisplay")
    public Boolean getItemWithImageOnlyDisplay() {
        return itemWithImageOnlyDisplay;
    }

    @JsonProperty("ItemWithImageOnlyDisplay")
    public void setItemWithImageOnlyDisplay(Boolean itemWithImageOnlyDisplay) {
        this.itemWithImageOnlyDisplay = itemWithImageOnlyDisplay;
    }

    @JsonProperty("SearchBySize")
    public Boolean getSearchBySize() {
        return searchBySize;
    }

    @JsonProperty("SearchBySize")
    public void setSearchBySize(Boolean searchBySize) {
        this.searchBySize = searchBySize;
    }

    @JsonProperty("SearchBy_PricePoint")
    public Boolean getSearchByPricePoint() {
        return searchByPricePoint;
    }

    @JsonProperty("SearchBy_PricePoint")
    public void setSearchByPricePoint(Boolean searchByPricePoint) {
        this.searchByPricePoint = searchByPricePoint;
    }

    @JsonProperty("SearchBy_Promotion")
    public Boolean getSearchByPromotion() {
        return searchByPromotion;
    }

    @JsonProperty("SearchBy_Promotion")
    public void setSearchByPromotion(Boolean searchByPromotion) {
        this.searchByPromotion = searchByPromotion;
    }

    @JsonProperty("SearchBy_SpecialOffer")
    public Boolean getSearchBySpecialOffer() {
        return searchBySpecialOffer;
    }

    @JsonProperty("SearchBy_SpecialOffer")
    public void setSearchBySpecialOffer(Boolean searchBySpecialOffer) {
        this.searchBySpecialOffer = searchBySpecialOffer;
    }

    @JsonProperty("SearchBy_Style")
    public Boolean getSearchByStyle() {
        return searchByStyle;
    }

    @JsonProperty("SearchBy_Style")
    public void setSearchByStyle(Boolean searchByStyle) {
        this.searchByStyle = searchByStyle;
    }

    @JsonProperty("Searchby_Department")
    public Boolean getSearchbyDepartment() {
        return searchbyDepartment;
    }

    @JsonProperty("Searchby_Department")
    public void setSearchbyDepartment(Boolean searchbyDepartment) {
        this.searchbyDepartment = searchbyDepartment;
    }

    @JsonProperty("Searchby_Price")
    public Double getSearchbyPrice() {
        return searchbyPrice;
    }

    @JsonProperty("Searchby_Price")
    public void setSearchbyPrice(Double searchbyPrice) {
        this.searchbyPrice = searchbyPrice;
    }

    @JsonProperty("Searchby_Pricegtlt")
    public String getSearchbyPricegtlt() {
        return searchbyPricegtlt;
    }

    @JsonProperty("Searchby_Pricegtlt")
    public void setSearchbyPricegtlt(String searchbyPricegtlt) {
        this.searchbyPricegtlt = searchbyPricegtlt;
    }

    @JsonProperty("SizeDisplay")
    public String getSizeDisplay() {
        return sizeDisplay;
    }

    @JsonProperty("SizeDisplay")
    public void setSizeDisplay(String sizeDisplay) {
        this.sizeDisplay = sizeDisplay;
    }

    @JsonProperty("StyleDisplay")
    public String getStyleDisplay() {
        return styleDisplay;
    }

    @JsonProperty("StyleDisplay")
    public void setStyleDisplay(String styleDisplay) {
        this.styleDisplay = styleDisplay;
    }

    @JsonProperty("searchby_Desc")
    public Boolean getSearchbyDesc() {
        return searchbyDesc;
    }

    @JsonProperty("searchby_Desc")
    public void setSearchbyDesc(Boolean searchbyDesc) {
        this.searchbyDesc = searchbyDesc;
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
