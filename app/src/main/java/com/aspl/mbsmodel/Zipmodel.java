package com.aspl.mbsmodel;

/**
 * Created by admin on 8/22/2018.
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
        "Result",
        "Storezip",
        "Type",
        "address",
        "city",
        "email",
        "name",
        "phone",
        "state",
        "storeTagLine",
        "storeno",
        "zip"
})
public class Zipmodel {

    @JsonProperty("Result")
    private Object result;
    @JsonProperty("Storezip")
    private String storezip;
    @JsonProperty("Type")
    private Object type;
    @JsonProperty("address")
    private Object address;
    @JsonProperty("city")
    private String city;
    @JsonProperty("email")
    private Object email;
    @JsonProperty("name")
    private Object name;
    @JsonProperty("phone")
    private Object phone;
    @JsonProperty("state")
    private Object state;
    @JsonProperty("storeTagLine")
    private Object storeTagLine;
    @JsonProperty("storeno")
    private String storeno;
    @JsonProperty("zip")
    private String zip;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("Result")
    public Object getResult() {
        return result;
    }

    @JsonProperty("Result")
    public void setResult(Object result) {
        this.result = result;
    }

    @JsonProperty("Storezip")
    public String getStorezip() {
        return storezip;
    }

    @JsonProperty("Storezip")
    public void setStorezip(String storezip) {
        this.storezip = storezip;
    }

    @JsonProperty("Type")
    public Object getType() {
        return type;
    }

    @JsonProperty("Type")
    public void setType(Object type) {
        this.type = type;
    }

    @JsonProperty("address")
    public Object getAddress() {
        return address;
    }

    @JsonProperty("address")
    public void setAddress(Object address) {
        this.address = address;
    }

    @JsonProperty("city")
    public String getCity() {
        return city.trim();
    }

    @JsonProperty("city")
    public void setCity(String city) {
        this.city = city.trim();
    }

    @JsonProperty("email")
    public Object getEmail() {
        return email;
    }

    @JsonProperty("email")
    public void setEmail(Object email) {
        this.email = email;
    }

    @JsonProperty("name")
    public Object getName() {
        return name;
    }

    @JsonProperty("name")
    public void setName(Object name) {
        this.name = name;
    }

    @JsonProperty("phone")
    public Object getPhone() {
        return phone;
    }

    @JsonProperty("phone")
    public void setPhone(Object phone) {
        this.phone = phone;
    }

    @JsonProperty("state")
    public Object getState() {
        return state;
    }

    @JsonProperty("state")
    public void setState(Object state) {
        this.state = state;
    }

    @JsonProperty("storeTagLine")
    public Object getStoreTagLine() {
        return storeTagLine;
    }

    @JsonProperty("storeTagLine")
    public void setStoreTagLine(Object storeTagLine) {
        this.storeTagLine = storeTagLine;
    }

    @JsonProperty("storeno")
    public String getStoreno() {
        return storeno.trim();
    }

    @JsonProperty("storeno")
    public void setStoreno(String storeno) {
        this.storeno = storeno.trim();
    }

    @JsonProperty("zip")
    public String getZip() {
        return zip.trim();
    }

    @JsonProperty("zip")
    public void setZip(String zip) {
        this.zip = zip.trim();
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