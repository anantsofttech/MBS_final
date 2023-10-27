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
        "Block_Actualtext",
        "Block_Description",
        "Block_Displaytext",
        "Block_Endprice",
        "Block_Specialoffer",
        "Block_Stratprice",
        "Block_id",
        "Block_storeno",
        "HomeDisplayFormat",
        "Image",
        "type",
        "BlockImgText",
        "FontColor",
        "FontSize",
        "FontStyle",
        "IsItemsonpromotionsActive",
        "IsNewAdditionsActive",
        "IsStaffPickActive",
        "ItemsonPromoInvCount",
        "NewAdditionsInvCount",
        "StaffPickInvCount",


})
public class DataFrontModel {

    @JsonProperty("IsItemsonpromotionsActive")
    private String IsItemsonpromotionsActive;
    @JsonProperty("IsNewAdditionsActive")
    private String IsNewAdditionsActive;
    @JsonProperty("IsStaffPickActive")
    private String IsStaffPickActive;
    @JsonProperty("BlockImgText")
    private String BlockImgText;
    @JsonProperty("FontColor")
    private String FontColor;
    @JsonProperty("FontSize")
    private String FontSize;
    @JsonProperty("FontStyle")
    private String FontStyle;
    @JsonProperty("type")
    private String type;
    @JsonProperty("Image")
    private String Image;
    @JsonProperty("HomeDisplayFormat")
    private String HomeDisplayFormat;
    @JsonProperty("Block_Actualtext")
    private String blockActualtext;
    @JsonProperty("Block_Description")
    private String blockDescription;
    @JsonProperty("Block_Displaytext")
    private String blockDisplaytext;
    @JsonProperty("Block_Endprice")
    private String blockEndprice;
    @JsonProperty("Block_Specialoffer")
    private String blockSpecialoffer;
    @JsonProperty("Block_Stratprice")
    private String blockStratprice;
    @JsonProperty("Block_id")
    private Integer blockId;
    @JsonProperty("Block_storeno")
    private String blockStoreno;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("Block_Actualtext")
    public String getBlockActualtext() {
        return blockActualtext;
    }

    @JsonProperty("Block_Actualtext")
    public void setBlockActualtext(String blockActualtext) {
        this.blockActualtext = blockActualtext;
    }

    @JsonProperty("Block_Description")
    public String getBlockDescription() {
        return blockDescription;
    }

    @JsonProperty("Block_Description")
    public void setBlockDescription(String blockDescription) {
        this.blockDescription = blockDescription;
    }

    @JsonProperty("Block_Displaytext")
    public String getBlockDisplaytext() {
        return blockDisplaytext;
    }

    @JsonProperty("Block_Displaytext")
    public void setBlockDisplaytext(String blockDisplaytext) {
        this.blockDisplaytext = blockDisplaytext;
    }

    @JsonProperty("Block_Endprice")
    public String getBlockEndprice() {
        return blockEndprice;
    }

    @JsonProperty("Block_Endprice")
    public void setBlockEndprice(String blockEndprice) {
        this.blockEndprice = blockEndprice;
    }

    @JsonProperty("Block_Specialoffer")
    public String getBlockSpecialoffer() {
        return blockSpecialoffer;
    }

    @JsonProperty("Block_Specialoffer")
    public void setBlockSpecialoffer(String blockSpecialoffer) {
        this.blockSpecialoffer = blockSpecialoffer;
    }

    @JsonProperty("Block_Stratprice")
    public String getBlockStratprice() {
        return blockStratprice;
    }

    @JsonProperty("Block_Stratprice")
    public void setBlockStratprice(String blockStratprice) {
        this.blockStratprice = blockStratprice;
    }

    @JsonProperty("Block_id")
    public Integer getBlockId() {
        return blockId;
    }

    @JsonProperty("Block_id")
    public void setBlockId(Integer blockId) {
        this.blockId = blockId;
    }

    @JsonProperty("Block_storeno")
    public String getBlockStoreno() {
        return blockStoreno;
    }

    @JsonProperty("Block_storeno")
    public void setBlockStoreno(String blockStoreno) {
        this.blockStoreno = blockStoreno;
    }

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

    public String getHomeDisplayFormat() {
        return HomeDisplayFormat;
    }

    public void setHomeDisplayFormat(String homeDisplayFormat) {
        HomeDisplayFormat = homeDisplayFormat;
    }

    public String getImage() {
        return Image;
    }

    public void setImage(String image) {
        Image = image;
    }

    public String getT() {
        return type;
    }

    public void setT(String t) {
        this.type = t;
    }

    public String getBlockImgText() {
        return BlockImgText;
    }

    public void setBlockImgText(String blockImgText) {
        BlockImgText = blockImgText;
    }

    public String getFontColor() {
        return FontColor;
    }

    public void setFontColor(String fontColor) {
        FontColor = fontColor;
    }

    public String getFontSize() {
        return FontSize;
    }

    public void setFontSize(String fontSize) {
        FontSize = fontSize;
    }

    public String getFontStyle() {
        return FontStyle;
    }

    public void setFontStyle(String fontStyle) {
        FontStyle = fontStyle;
    }

    public String getIsItemsonpromotionsActive() {
        return IsItemsonpromotionsActive;
    }

    public void setIsItemsonpromotionsActive(String isItemsonpromotionsActive) {
        IsItemsonpromotionsActive = isItemsonpromotionsActive;
    }

    public String getIsNewAdditionsActive() {
        return IsNewAdditionsActive;
    }

    public void setIsNewAdditionsActive(String isNewAdditionsActive) {
        IsNewAdditionsActive = isNewAdditionsActive;
    }

    public String getIsStaffPickActive() {
        return IsStaffPickActive;
    }

    public void setIsStaffPickActive(String isStaffPickActive) {
        IsStaffPickActive = isStaffPickActive;
    }
}
