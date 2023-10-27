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
        "InvCount",
        "dept_desc",
        "dept_id",
        "dept_img",
        "storeno",
        "total_record"
})
public class LstDepartment {

    @JsonProperty("InvCount")
    private Integer invCount;
    @JsonProperty("dept_desc")
    private String deptDesc;
    @JsonProperty("dept_id")
    private Integer deptId;
    @JsonProperty("dept_img")
    private String deptImg;
    @JsonProperty("storeno")
    private String storeno;
    @JsonProperty("total_record")
    private Integer totalRecord;
    @JsonIgnore
    private Map<String, String> additionalProperties = new HashMap<String, String>();
    private boolean isChecked;

    @JsonProperty("InvCount")
    public Integer getInvCount() {
        return invCount;
    }

    @JsonProperty("InvCount")
    public void setInvCount(Integer invCount) {
        this.invCount = invCount;
    }

    @JsonProperty("dept_desc")
    public String getDeptDesc() {
        return (deptDesc == null) ? "" : deptDesc;
    }

    @JsonProperty("dept_desc")
    public void setDeptDesc(String deptDesc) {
        this.deptDesc = deptDesc;
    }

    @JsonProperty("dept_id")
    public Integer getDeptId() {
        return deptId;
    }

    @JsonProperty("dept_id")
    public void setDeptId(Integer deptId) {
        this.deptId = deptId;
    }

    @JsonProperty("dept_img")
    public String getDeptImg() {
        return (deptImg == null) ? "" : deptImg;
    }

    @JsonProperty("dept_img")
    public void setDeptImg(String deptImg) {
        this.deptImg = deptImg;
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
    public Map<String, String> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, String value) {
        this.additionalProperties.put(name, value);
    }

}