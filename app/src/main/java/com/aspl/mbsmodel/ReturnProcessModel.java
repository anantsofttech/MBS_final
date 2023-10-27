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
        "ActualReason",
        "DisplayReason",
        "Flage",
        "Storeno",
        "id"
})
public class ReturnProcessModel {

    @JsonProperty("ActualReason")
    private String actualReason;
    @JsonProperty("DisplayReason")
    private String displayReason;
    @JsonProperty("Flage")
    private Boolean flage;
    @JsonProperty("Storeno")
    private Object storeno;
    @JsonProperty("id")
    private Integer id;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("ActualReason")
    public String getActualReason() {
        return actualReason;
    }

    @JsonProperty("ActualReason")
    public void setActualReason(String actualReason) {
        this.actualReason = actualReason;
    }

    @JsonProperty("DisplayReason")
    public String getDisplayReason() {
        return displayReason;
    }

    @JsonProperty("DisplayReason")
    public void setDisplayReason(String displayReason) {
        this.displayReason = displayReason;
    }

    @JsonProperty("Flage")
    public Boolean getFlage() {
        return flage;
    }

    @JsonProperty("Flage")
    public void setFlage(Boolean flage) {
        this.flage = flage;
    }

    @JsonProperty("Storeno")
    public Object getStoreno() {
        return storeno;
    }

    @JsonProperty("Storeno")
    public void setStoreno(Object storeno) {
        this.storeno = storeno;
    }

    @JsonProperty("id")
    public Integer getId() {
        return id;
    }

    @JsonProperty("id")
    public void setId(Integer id) {
        this.id = id;
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
