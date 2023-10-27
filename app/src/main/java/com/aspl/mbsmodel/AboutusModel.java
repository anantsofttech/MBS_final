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
        "CheckComments",
        "CheckEligibleWorkInUS",
        "CheckMobileNumber",
        "CheckResume",
        "CheckUploadRequired",
        "DisplayEventCalender",
        "DisplayStoreHours",
        "FontSize",
        "FontStyle",
        "PageContent",
        "PageName",
        "PageTitle",
        "SearchEngineStatus",
        "Setup_HandDelivery",
        "ShowEventCalender",
        "Status",
        "StoreNo",
        "Tagline",
        "logo"
})
public class AboutusModel {

    @JsonProperty("CheckComments")
    private Boolean checkComments;
    @JsonProperty("CheckEligibleWorkInUS")
    private Boolean checkEligibleWorkInUS;
    @JsonProperty("CheckMobileNumber")
    private Boolean checkMobileNumber;
    @JsonProperty("CheckResume")
    private Boolean checkResume;
    @JsonProperty("CheckUploadRequired")
    private Boolean checkUploadRequired;
    @JsonProperty("DisplayEventCalender")
    private Boolean displayEventCalender;
    @JsonProperty("DisplayStoreHours")
    private Boolean displayStoreHours;
    @JsonProperty("FontSize")
    private Integer fontSize;
    @JsonProperty("FontStyle")
    private Object fontStyle;
    @JsonProperty("PageContent")
    private String pageContent;
    @JsonProperty("PageName")
    private String pageName;
    @JsonProperty("PageTitle")
    private String pageTitle;
    @JsonProperty("SearchEngineStatus")
    private Boolean searchEngineStatus;
    @JsonProperty("Setup_HandDelivery")
    private Boolean setupHandDelivery;
    @JsonProperty("ShowEventCalender")
    private Boolean showEventCalender;
    @JsonProperty("Status")
    private Boolean status;
    @JsonProperty("StoreNo")
    private String storeNo;
    @JsonProperty("Tagline")
    private Object tagline;
    @JsonProperty("logo")
    private String logo;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("CheckComments")
    public Boolean getCheckComments() {
        return checkComments;
    }

    @JsonProperty("CheckComments")
    public void setCheckComments(Boolean checkComments) {
        this.checkComments = checkComments;
    }

    @JsonProperty("CheckEligibleWorkInUS")
    public Boolean getCheckEligibleWorkInUS() {
        return checkEligibleWorkInUS;
    }

    @JsonProperty("CheckEligibleWorkInUS")
    public void setCheckEligibleWorkInUS(Boolean checkEligibleWorkInUS) {
        this.checkEligibleWorkInUS = checkEligibleWorkInUS;
    }

    @JsonProperty("CheckMobileNumber")
    public Boolean getCheckMobileNumber() {
        return checkMobileNumber;
    }

    @JsonProperty("CheckMobileNumber")
    public void setCheckMobileNumber(Boolean checkMobileNumber) {
        this.checkMobileNumber = checkMobileNumber;
    }

    @JsonProperty("CheckResume")
    public Boolean getCheckResume() {
        return checkResume;
    }

    @JsonProperty("CheckResume")
    public void setCheckResume(Boolean checkResume) {
        this.checkResume = checkResume;
    }

    @JsonProperty("CheckUploadRequired")
    public Boolean getCheckUploadRequired() {
        return checkUploadRequired;
    }

    @JsonProperty("CheckUploadRequired")
    public void setCheckUploadRequired(Boolean checkUploadRequired) {
        this.checkUploadRequired = checkUploadRequired;
    }

    @JsonProperty("DisplayEventCalender")
    public Boolean getDisplayEventCalender() {
        return displayEventCalender;
    }

    @JsonProperty("DisplayEventCalender")
    public void setDisplayEventCalender(Boolean displayEventCalender) {
        this.displayEventCalender = displayEventCalender;
    }

    @JsonProperty("DisplayStoreHours")
    public Boolean getDisplayStoreHours() {
        return displayStoreHours;
    }

    @JsonProperty("DisplayStoreHours")
    public void setDisplayStoreHours(Boolean displayStoreHours) {
        this.displayStoreHours = displayStoreHours;
    }

    @JsonProperty("FontSize")
    public Integer getFontSize() {
        return fontSize;
    }

    @JsonProperty("FontSize")
    public void setFontSize(Integer fontSize) {
        this.fontSize = fontSize;
    }

    @JsonProperty("FontStyle")
    public Object getFontStyle() {
        return fontStyle;
    }

    @JsonProperty("FontStyle")
    public void setFontStyle(Object fontStyle) {
        this.fontStyle = fontStyle;
    }

    @JsonProperty("PageContent")
    public String getPageContent() {
        return pageContent;
    }

    @JsonProperty("PageContent")
    public void setPageContent(String pageContent) {
        this.pageContent = pageContent;
    }

    @JsonProperty("PageName")
    public String getPageName() {
        return pageName;
    }

    @JsonProperty("PageName")
    public void setPageName(String pageName) {
        this.pageName = pageName;
    }

    @JsonProperty("PageTitle")
    public String getPageTitle() {
        return pageTitle;
    }

    @JsonProperty("PageTitle")
    public void setPageTitle(String pageTitle) {
        this.pageTitle = pageTitle;
    }

    @JsonProperty("SearchEngineStatus")
    public Boolean getSearchEngineStatus() {
        return searchEngineStatus;
    }

    @JsonProperty("SearchEngineStatus")
    public void setSearchEngineStatus(Boolean searchEngineStatus) {
        this.searchEngineStatus = searchEngineStatus;
    }

    @JsonProperty("Setup_HandDelivery")
    public Boolean getSetupHandDelivery() {
        return setupHandDelivery;
    }

    @JsonProperty("Setup_HandDelivery")
    public void setSetupHandDelivery(Boolean setupHandDelivery) {
        this.setupHandDelivery = setupHandDelivery;
    }

    @JsonProperty("ShowEventCalender")
    public Boolean getShowEventCalender() {
        return showEventCalender;
    }

    @JsonProperty("ShowEventCalender")
    public void setShowEventCalender(Boolean showEventCalender) {
        this.showEventCalender = showEventCalender;
    }

    @JsonProperty("Status")
    public Boolean getStatus() {
        return status;
    }

    @JsonProperty("Status")
    public void setStatus(Boolean status) {
        this.status = status;
    }

    @JsonProperty("StoreNo")
    public String getStoreNo() {
        return storeNo;
    }

    @JsonProperty("StoreNo")
    public void setStoreNo(String storeNo) {
        this.storeNo = storeNo;
    }

    @JsonProperty("Tagline")
    public Object getTagline() {
        return tagline;
    }

    @JsonProperty("Tagline")
    public void setTagline(Object tagline) {
        this.tagline = tagline;
    }

    @JsonProperty("logo")
    public String getLogo() {
        return logo;
    }

    @JsonProperty("logo")
    public void setLogo(String logo) {
        this.logo = logo;
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