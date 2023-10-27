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
        "TID",
        "ValutecClientkey"
})
public class GetValueInfoSetting {

    @JsonProperty("TID")
    private String tID;
    @JsonProperty("ValutecClientkey")
    private String valutecClientkey;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("TID")
    public String getTID() {
        return tID;
    }

    @JsonProperty("TID")
    public void setTID(String tID) {
        this.tID = tID;
    }

    @JsonProperty("ValutecClientkey")
    public String getValutecClientkey() {
        return valutecClientkey;
    }

    @JsonProperty("ValutecClientkey")
    public void setValutecClientkey(String valutecClientkey) {
        this.valutecClientkey = valutecClientkey;
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