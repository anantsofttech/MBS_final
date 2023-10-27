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
        "SizeCount",
        "SizeName",
        "Size_id",
        "deptid",
        "storeno",
        "total_record"
})
public class LstSize {

    @JsonProperty("SizeCount")
    private Integer sizeCount;
    @JsonProperty("SizeName")
    private String sizeName;
    @JsonProperty("Size_id")
    private Integer sizeId;
    @JsonProperty("deptid")
    private Integer deptid;
    @JsonProperty("storeno")
    private String storeno;
    @JsonProperty("total_record")
    private Integer totalRecord;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();
    private boolean isChecked;

    @JsonProperty("SizeCount")
    public Integer getSizeCount() {
        return sizeCount;
    }

    @JsonProperty("SizeCount")
    public void setSizeCount(Integer sizeCount) {
        this.sizeCount = sizeCount;
    }

    @JsonProperty("SizeName")
    public String getSizeName() {
        return (sizeName == null) ? "" : sizeName;
    }

    @JsonProperty("SizeName")
    public void setSizeName(String sizeName) {
        this.sizeName = sizeName;
    }

    @JsonProperty("Size_id")
    public Integer getSizeId() {
        return sizeId;
    }

    @JsonProperty("Size_id")
    public void setSizeId(Integer sizeId) {
        this.sizeId = sizeId;
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
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}