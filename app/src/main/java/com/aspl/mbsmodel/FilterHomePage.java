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
        "Block_Actualtext",
        "Block_Description",
        "Block_Displaytext",
        "Block_Endprice",
        "Block_Specialoffer",
        "Block_Stratprice",
        "Block_id",
        "Block_storeno"
})
public class FilterHomePage {

    @JsonProperty("Block_Actualtext")
    private String blockActualtext;
    @JsonProperty("Block_Description")
    private Object blockDescription;
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
    private Object blockStoreno;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();
    private boolean isChecked;

    @JsonProperty("Block_Actualtext")
    public String getBlockActualtext() {
        return blockActualtext;
    }

    @JsonProperty("Block_Actualtext")
    public void setBlockActualtext(String blockActualtext) {
        this.blockActualtext = blockActualtext;
    }

    @JsonProperty("Block_Description")
    public Object getBlockDescription() {
        return blockDescription;
    }

    @JsonProperty("Block_Description")
    public void setBlockDescription(Object blockDescription) {
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
    public Object getBlockStoreno() {
        return blockStoreno;
    }

    @JsonProperty("Block_storeno")
    public void setBlockStoreno(Object blockStoreno) {
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

    public void setChecked(boolean isChecked) {
        this.isChecked = isChecked;
    }

    public boolean getChecked() {
        return isChecked/*(tax66 == null) ? "" : tax66 */;
    }

}