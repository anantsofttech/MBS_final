package com.aspl.mbsmodel;

/**
 * Created by admin on 8/31/2018.
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
        "AltTag",
        "BannerId",
        "BannerNo",
        "BannerStatus",
        "BannerType",
        "Caption",
        "Image",
        "StoreNo",
        "imagepath",
        "status",
        "type"
})
public class BannerModel {

    @JsonProperty("AltTag")
    private String altTag;
    @JsonProperty("BannerId")
    private Integer bannerId;
    @JsonProperty("BannerNo")
    private Integer bannerNo;
    @JsonProperty("BannerStatus")
    private Boolean bannerStatus;
    @JsonProperty("BannerType")
    private String bannerType;
    @JsonProperty("Caption")
    private String caption;
    @JsonProperty("Image")
    private String image;
    @JsonProperty("StoreNo")
    private String storeNo;
    @JsonProperty("imagepath")
    private Object imagepath;
    @JsonProperty("status")
    private Object status;
    @JsonProperty("type")
    private Object type;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("AltTag")
    public String getAltTag() {
        return altTag;
    }

    @JsonProperty("AltTag")
    public void setAltTag(String altTag) {
        this.altTag = altTag;
    }

    @JsonProperty("BannerId")
    public Integer getBannerId() {
        return bannerId;
    }

    @JsonProperty("BannerId")
    public void setBannerId(Integer bannerId) {
        this.bannerId = bannerId;
    }

    @JsonProperty("BannerNo")
    public Integer getBannerNo() {
        return bannerNo;
    }

    @JsonProperty("BannerNo")
    public void setBannerNo(Integer bannerNo) {
        this.bannerNo = bannerNo;
    }

    @JsonProperty("BannerStatus")
    public Boolean getBannerStatus() {
        return bannerStatus;
    }

    @JsonProperty("BannerStatus")
    public void setBannerStatus(Boolean bannerStatus) {
        this.bannerStatus = bannerStatus;
    }

    @JsonProperty("BannerType")
    public String getBannerType() {
        return bannerType;
    }

    @JsonProperty("BannerType")
    public void setBannerType(String bannerType) {
        this.bannerType = bannerType;
    }

    @JsonProperty("Caption")
    public String getCaption() {
        return caption;
    }

    @JsonProperty("Caption")
    public void setCaption(String caption) {
        this.caption = caption;
    }

    @JsonProperty("Image")
    public String getImage() {
        return image;
    }

    @JsonProperty("Image")
    public void setImage(String image) {
        this.image = image;
    }

    @JsonProperty("StoreNo")
    public String getStoreNo() {
        return storeNo;
    }

    @JsonProperty("StoreNo")
    public void setStoreNo(String storeNo) {
        this.storeNo = storeNo;
    }

    @JsonProperty("imagepath")
    public Object getImagepath() {
        return imagepath;
    }

    @JsonProperty("imagepath")
    public void setImagepath(Object imagepath) {
        this.imagepath = imagepath;
    }

    @JsonProperty("status")
    public Object getStatus() {
        return status;
    }

    @JsonProperty("status")
    public void setStatus(Object status) {
        this.status = status;
    }

    @JsonProperty("type")
    public Object getType() {
        return type;
    }

    @JsonProperty("type")
    public void setType(Object type) {
        this.type = type;
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