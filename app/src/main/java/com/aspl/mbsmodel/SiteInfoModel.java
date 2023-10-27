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
        "Backgroundcolor",
        "DetailId",
        "EcomURL",
        "FavImage",
        "Font",
        "FontColor",
        "IsGiftBal",
        "IsLoyalityRewards",
        "LiveWebSite",
        "SBActiveDisplay",
        "SBBackColor",
        "SBBannerID",
        "SBFontColor",
        "SBFontFamily",
        "SBFontSize",
        "SBFontStyle",
        "SBPageContent",
        "SBPageName",
        "SBText_Align",
        "SBfont_weight",
        "SBtext_decoration",
        "SiteAuthor",
        "SiteDescription",
        "SiteKeyword",
        "SiteTitle",
        "StoreLogo",
        "StoreName",
        "StoreNo",
        "StoreTagLine",
        "ThemeColor",
        "UnderConstruction"
})
public class SiteInfoModel {

    @JsonProperty("Backgroundcolor")
    private Object backgroundcolor;
    @JsonProperty("DetailId")
    private Integer detailId;
    @JsonProperty("EcomURL")
    private String ecomURL;
    @JsonProperty("FavImage")
    private String favImage;
    @JsonProperty("Font")
    private Object font;
    @JsonProperty("FontColor")
    private Object fontColor;
    @JsonProperty("IsGiftBal")
    private String isGiftBal;
    @JsonProperty("IsLoyalityRewards")
    private String isLoyalityRewards;
    @JsonProperty("LiveWebSite")
    private Object liveWebSite;
    @JsonProperty("SBActiveDisplay")
    private Boolean sBActiveDisplay;
    @JsonProperty("SBBackColor")
    private Object sBBackColor;
    @JsonProperty("SBBannerID")
    private Integer sBBannerID;
    @JsonProperty("SBFontColor")
    private Object sBFontColor;
    @JsonProperty("SBFontFamily")
    private Object sBFontFamily;
    @JsonProperty("SBFontSize")
    private Integer sBFontSize;
    @JsonProperty("SBFontStyle")
    private Boolean sBFontStyle;
    @JsonProperty("SBPageContent")
    private Object sBPageContent;
    @JsonProperty("SBPageName")
    private Object sBPageName;
    @JsonProperty("SBText_Align")
    private Object sBTextAlign;
    @JsonProperty("SBfont_weight")
    private Boolean sBfontWeight;
    @JsonProperty("SBtext_decoration")
    private Boolean sBtextDecoration;
    @JsonProperty("SiteAuthor")
    private String siteAuthor;
    @JsonProperty("SiteDescription")
    private String siteDescription;
    @JsonProperty("SiteKeyword")
    private String siteKeyword;
    @JsonProperty("SiteTitle")
    private String siteTitle;
    @JsonProperty("StoreLogo")
    private String storeLogo;
    @JsonProperty("StoreName")
    private String storeName;
    @JsonProperty("StoreNo")
    private String storeNo;
    @JsonProperty("StoreTagLine")
    private String storeTagLine;
    @JsonProperty("ThemeColor")
    private Object themeColor;
    @JsonProperty("UnderConstruction")
    private Object underConstruction;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("Backgroundcolor")
    public Object getBackgroundcolor() {
        return backgroundcolor;
    }

    @JsonProperty("Backgroundcolor")
    public void setBackgroundcolor(Object backgroundcolor) {
        this.backgroundcolor = backgroundcolor;
    }

    @JsonProperty("DetailId")
    public Integer getDetailId() {
        return detailId;
    }

    @JsonProperty("DetailId")
    public void setDetailId(Integer detailId) {
        this.detailId = detailId;
    }

    @JsonProperty("EcomURL")
    public String getEcomURL() {
        return ecomURL;
    }

    @JsonProperty("EcomURL")
    public void setEcomURL(String ecomURL) {
        this.ecomURL = ecomURL;
    }

    @JsonProperty("FavImage")
    public String getFavImage() {
        return favImage;
    }

    @JsonProperty("FavImage")
    public void setFavImage(String favImage) {
        this.favImage = favImage;
    }

    @JsonProperty("Font")
    public Object getFont() {
        return font;
    }

    @JsonProperty("Font")
    public void setFont(Object font) {
        this.font = font;
    }

    @JsonProperty("FontColor")
    public Object getFontColor() {
        return fontColor;
    }

    @JsonProperty("FontColor")
    public void setFontColor(Object fontColor) {
        this.fontColor = fontColor;
    }

    @JsonProperty("IsGiftBal")
    public String getIsGiftBal() {
        return isGiftBal;
    }

    @JsonProperty("IsGiftBal")
    public void setIsGiftBal(String isGiftBal) {
        this.isGiftBal = isGiftBal;
    }

    @JsonProperty("IsLoyalityRewards")
    public String getIsLoyalityRewards() {
        return isLoyalityRewards;
    }

    @JsonProperty("IsLoyalityRewards")
    public void setIsLoyalityRewards(String isLoyalityRewards) {
        this.isLoyalityRewards = isLoyalityRewards;
    }

    @JsonProperty("LiveWebSite")
    public Object getLiveWebSite() {
        return liveWebSite;
    }

    @JsonProperty("LiveWebSite")
    public void setLiveWebSite(Object liveWebSite) {
        this.liveWebSite = liveWebSite;
    }

    @JsonProperty("SBActiveDisplay")
    public Boolean getSBActiveDisplay() {
        return sBActiveDisplay;
    }

    @JsonProperty("SBActiveDisplay")
    public void setSBActiveDisplay(Boolean sBActiveDisplay) {
        this.sBActiveDisplay = sBActiveDisplay;
    }

    @JsonProperty("SBBackColor")
    public Object getSBBackColor() {
        return sBBackColor;
    }

    @JsonProperty("SBBackColor")
    public void setSBBackColor(Object sBBackColor) {
        this.sBBackColor = sBBackColor;
    }

    @JsonProperty("SBBannerID")
    public Integer getSBBannerID() {
        return sBBannerID;
    }

    @JsonProperty("SBBannerID")
    public void setSBBannerID(Integer sBBannerID) {
        this.sBBannerID = sBBannerID;
    }

    @JsonProperty("SBFontColor")
    public Object getSBFontColor() {
        return sBFontColor;
    }

    @JsonProperty("SBFontColor")
    public void setSBFontColor(Object sBFontColor) {
        this.sBFontColor = sBFontColor;
    }

    @JsonProperty("SBFontFamily")
    public Object getSBFontFamily() {
        return sBFontFamily;
    }

    @JsonProperty("SBFontFamily")
    public void setSBFontFamily(Object sBFontFamily) {
        this.sBFontFamily = sBFontFamily;
    }

    @JsonProperty("SBFontSize")
    public Integer getSBFontSize() {
        return sBFontSize;
    }

    @JsonProperty("SBFontSize")
    public void setSBFontSize(Integer sBFontSize) {
        this.sBFontSize = sBFontSize;
    }

    @JsonProperty("SBFontStyle")
    public Boolean getSBFontStyle() {
        return sBFontStyle;
    }

    @JsonProperty("SBFontStyle")
    public void setSBFontStyle(Boolean sBFontStyle) {
        this.sBFontStyle = sBFontStyle;
    }

    @JsonProperty("SBPageContent")
    public Object getSBPageContent() {
        return sBPageContent;
    }

    @JsonProperty("SBPageContent")
    public void setSBPageContent(Object sBPageContent) {
        this.sBPageContent = sBPageContent;
    }

    @JsonProperty("SBPageName")
    public Object getSBPageName() {
        return sBPageName;
    }

    @JsonProperty("SBPageName")
    public void setSBPageName(Object sBPageName) {
        this.sBPageName = sBPageName;
    }

    @JsonProperty("SBText_Align")
    public Object getSBTextAlign() {
        return sBTextAlign;
    }

    @JsonProperty("SBText_Align")
    public void setSBTextAlign(Object sBTextAlign) {
        this.sBTextAlign = sBTextAlign;
    }

    @JsonProperty("SBfont_weight")
    public Boolean getSBfontWeight() {
        return sBfontWeight;
    }

    @JsonProperty("SBfont_weight")
    public void setSBfontWeight(Boolean sBfontWeight) {
        this.sBfontWeight = sBfontWeight;
    }

    @JsonProperty("SBtext_decoration")
    public Boolean getSBtextDecoration() {
        return sBtextDecoration;
    }

    @JsonProperty("SBtext_decoration")
    public void setSBtextDecoration(Boolean sBtextDecoration) {
        this.sBtextDecoration = sBtextDecoration;
    }

    @JsonProperty("SiteAuthor")
    public String getSiteAuthor() {
        return siteAuthor;
    }

    @JsonProperty("SiteAuthor")
    public void setSiteAuthor(String siteAuthor) {
        this.siteAuthor = siteAuthor;
    }

    @JsonProperty("SiteDescription")
    public String getSiteDescription() {
        return siteDescription;
    }

    @JsonProperty("SiteDescription")
    public void setSiteDescription(String siteDescription) {
        this.siteDescription = siteDescription;
    }

    @JsonProperty("SiteKeyword")
    public String getSiteKeyword() {
        return siteKeyword;
    }

    @JsonProperty("SiteKeyword")
    public void setSiteKeyword(String siteKeyword) {
        this.siteKeyword = siteKeyword;
    }

    @JsonProperty("SiteTitle")
    public String getSiteTitle() {
        return siteTitle;
    }

    @JsonProperty("SiteTitle")
    public void setSiteTitle(String siteTitle) {
        this.siteTitle = siteTitle;
    }

    @JsonProperty("StoreLogo")
    public String getStoreLogo() {
        return storeLogo;
    }

    @JsonProperty("StoreLogo")
    public void setStoreLogo(String storeLogo) {
        this.storeLogo = storeLogo;
    }

    @JsonProperty("StoreName")
    public String getStoreName() {
        return storeName;
    }

    @JsonProperty("StoreName")
    public void setStoreName(String storeName) {
        this.storeName = storeName;
    }

    @JsonProperty("StoreNo")
    public String getStoreNo() {
        return storeNo;
    }

    @JsonProperty("StoreNo")
    public void setStoreNo(String storeNo) {
        this.storeNo = storeNo;
    }

    @JsonProperty("StoreTagLine")
    public String getStoreTagLine() {
        return storeTagLine;
    }

    @JsonProperty("StoreTagLine")
    public void setStoreTagLine(String storeTagLine) {
        this.storeTagLine = storeTagLine;
    }

    @JsonProperty("ThemeColor")
    public Object getThemeColor() {
        return themeColor;
    }

    @JsonProperty("ThemeColor")
    public void setThemeColor(Object themeColor) {
        this.themeColor = themeColor;
    }

    @JsonProperty("UnderConstruction")
    public Object getUnderConstruction() {
        return underConstruction;
    }

    @JsonProperty("UnderConstruction")
    public void setUnderConstruction(Object underConstruction) {
        this.underConstruction = underConstruction;
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



