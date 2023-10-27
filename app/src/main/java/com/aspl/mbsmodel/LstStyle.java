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
        "dept_desc",
        "deptid",
        "storeno",
        "style",
        "styleCount",
        "style_id",
        "total_record"
})
public class LstStyle {

    @JsonProperty("dept_desc")
    private String deptDesc;
    @JsonProperty("deptid")
    private Integer deptid;
    @JsonProperty("storeno")
    private String storeno;
    @JsonProperty("style")
    private String style;
    @JsonProperty("styleCount")
    private Integer styleCount;
    @JsonProperty("style_id")
    private Integer styleId;
    @JsonProperty("total_record")
    private Integer totalRecord;
    @JsonIgnore
    private Map<String, String> additionalProperties = new HashMap<String, String>();
    private boolean isChecked;

    @JsonProperty("dept_desc")
    public String getDeptDesc() {
        return (deptDesc == null) ? "" : deptDesc;
    }

    @JsonProperty("dept_desc")
    public void setDeptDesc(String deptDesc) {
        this.deptDesc = deptDesc;
    }

    @JsonProperty("deptid")
    public Integer getDeptid() {
        return deptid;
    }

    @JsonProperty("deptid")
    public void setDeptid(Integer deptid) {
        this.deptid = deptid;
    }

    @JsonProperty("storeno")
    public String getStoreno() {
        return (storeno == null) ? "" : storeno;
    }

    @JsonProperty("storeno")
    public void setStoreno(String storeno) {
        this.storeno = storeno;
    }

    @JsonProperty("style")
    public String getStyle() {
        return (style == null) ? "" : style;
    }

    @JsonProperty("style")
    public void setStyle(String style) {
        this.style = style;
    }

    @JsonProperty("styleCount")
    public Integer getStyleCount() {
        return styleCount;
    }

    @JsonProperty("styleCount")
    public void setStyleCount(Integer styleCount) {
        this.styleCount = styleCount;
    }

    @JsonProperty("style_id")
    public Integer getStyleId() {
        return styleId;
    }

    @JsonProperty("style_id")
    public void setStyleId(Integer styleId) {
        this.styleId = styleId;
    }

    @JsonProperty("total_record")
    public Integer getTotalRecord() {
        return totalRecord;
    }

    @JsonProperty("total_record")
    public void setTotalRecord(Integer totalRecord) {
        this.totalRecord = totalRecord;
    }

    public void setChecked(boolean isChecked) {
        this.isChecked = isChecked;
    }

    public boolean getChecked() {
        return isChecked/*(tax66 == null) ? "" : tax66 */;
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