package com.aspl.mbsmodel;


import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "ActualText",
        "BlockImgText",
        "DisplayText",
        "DropDownImage",
        "FontColor",
        "FontSize" ,
        "FontStyle",
        "HomeDisplayFormat" ,
        "ID",
        "Image",
        "IsDropDownMenu",
        "IsItemsonpromotionsActive",
        "IsNewAdditionsActive",
        "IsStaffPickActive",
        "ItemsonPromoInvCount",
        "MobileLayOut",
        "NewAdditionsInvCount",
        "PageContent",
        "PageName",
        "PageTitle",
        "Position",
        "StaffPickInvCount",
        "Status",
        "StoreNo",
        "inv_count",
        "isAccessibilityPolicy" ,
        "type"
})

public class DataHomePageBlockModel {


    @JsonProperty("ActualText")
    private String ActualText;
    @JsonProperty("BlockImgText")
    private String BlockImgText;
    @JsonProperty("DisplayText")
    private String DisplayText;
    @JsonProperty("DropDownImage")
    private String DropDownImage;
    @JsonProperty("FontColor")
    private String FontColor;
    @JsonProperty("FontSize")
    private String FontSize;
    @JsonProperty("FontStyle")
    private String FontStyle;
    @JsonProperty("HomeDisplayFormat")
    private String FonHomeDisplayFormattStyle;
    @JsonProperty("ID")
    private String ID;
    @JsonProperty("Image")
    private String Image;
    @JsonProperty("IsDropDownMenu")
    private String IsDropDownMenu;
    @JsonProperty("IsItemsonpromotionsActive")
    private String IsItemsonpromotionsActive;
    @JsonProperty("IsNewAdditionsActive")
    private String IsNewAdditionsActive;
    @JsonProperty("IsStaffPickActive")
    private String IsStaffPickActive;
    @JsonProperty("ItemsonPromoInvCount")
    private String ItemsonPromoInvCount;
    @JsonProperty("MobileLayOut")
    private String MobileLayOut;
    @JsonProperty("NewAdditionsInvCount")
    private String NewAdditionsInvCount;
    @JsonProperty("PageContent")
    private String PageContent;
    @JsonProperty("PageName")
    private String PageName;
    @JsonProperty("PageTitle")
    private String PageTitle;
    @JsonProperty("Position")
    private String Position;
    @JsonProperty("StaffPickInvCount")
    private String StaffPickInvCount;
    @JsonProperty("Status")
    private String Status;
    @JsonProperty("StoreNo")
    private String StoreNo;
    @JsonProperty("inv_count")
    private String inv_count;
    @JsonProperty("isAccessibilityPolicy")
    private String isAccessibilityPolicy;
    @JsonProperty("type")
    private String type;




}
