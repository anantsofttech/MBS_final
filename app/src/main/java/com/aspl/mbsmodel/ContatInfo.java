package com.aspl.mbsmodel;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.util.HashMap;
import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "Date",
        "EligibleInUS",
        "Filename",
        "Fname",
        "ID",
        "Lname",
        "Message",
        "ReadFlag",
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
public class ContatInfo {

    @JsonProperty("Date")
    private Object date;
    @JsonProperty("EligibleInUS")
    private Boolean eligibleInUS;
    @JsonProperty("Filename")
    private Object filename;
    @JsonProperty("Fname")
    private Object fname;
    @JsonProperty("ID")
    private Object iD;
    @JsonProperty("Lname")
    private Object lname;
    @JsonProperty("Message")
    private Object message;
    @JsonProperty("ReadFlag")
    private Object readFlag;
    @JsonProperty("Result")
    private Object result;
    @JsonProperty("Storezip")
    private Object storezip;
    @JsonProperty("Type")
    private Object type;
    @JsonProperty("address")
    private String address;
    @JsonProperty("city")
    private String city;
    @JsonProperty("email")
    private String email;
    @JsonProperty("name")
    private String name;
    @JsonProperty("phone")
    private String phone;
    @JsonProperty("state")
    private String state;
    @JsonProperty("storeTagLine")
    private String storeTagLine;
    @JsonProperty("storeno")
    private Object storeno;
    @JsonProperty("zip")
    private String zip;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("Date")
    public Object getDate() {
        return date;
    }

    @JsonProperty("Date")
    public void setDate(Object date) {
        this.date = date;
    }

    @JsonProperty("EligibleInUS")
    public Boolean getEligibleInUS() {
        return eligibleInUS;
    }

    @JsonProperty("EligibleInUS")
    public void setEligibleInUS(Boolean eligibleInUS) {
        this.eligibleInUS = eligibleInUS;
    }

    @JsonProperty("Filename")
    public Object getFilename() {
        return filename;
    }

    @JsonProperty("Filename")
    public void setFilename(Object filename) {
        this.filename = filename;
    }

    @JsonProperty("Fname")
    public Object getFname() {
        return fname;
    }

    @JsonProperty("Fname")
    public void setFname(Object fname) {
        this.fname = fname;
    }

    @JsonProperty("ID")
    public Object getID() {
        return iD;
    }

    @JsonProperty("ID")
    public void setID(Object iD) {
        this.iD = iD;
    }

    @JsonProperty("Lname")
    public Object getLname() {
        return lname;
    }

    @JsonProperty("Lname")
    public void setLname(Object lname) {
        this.lname = lname;
    }

    @JsonProperty("Message")
    public Object getMessage() {
        return message;
    }

    @JsonProperty("Message")
    public void setMessage(Object message) {
        this.message = message;
    }

    @JsonProperty("ReadFlag")
    public Object getReadFlag() {
        return readFlag;
    }

    @JsonProperty("ReadFlag")
    public void setReadFlag(Object readFlag) {
        this.readFlag = readFlag;
    }

    @JsonProperty("Result")
    public Object getResult() {
        return result;
    }

    @JsonProperty("Result")
    public void setResult(Object result) {
        this.result = result;
    }

    @JsonProperty("Storezip")
    public Object getStorezip() {
        return storezip;
    }

    @JsonProperty("Storezip")
    public void setStorezip(Object storezip) {
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
    public String getAddress() {
        return address;
    }

    @JsonProperty("address")
    public void setAddress(String address) {
        this.address = address;
    }

    @JsonProperty("city")
    public String getCity() {
        return city;
    }

    @JsonProperty("city")
    public void setCity(String city) {
        this.city = city;
    }

    @JsonProperty("email")
    public String getEmail() {
        return email;
    }

    @JsonProperty("email")
    public void setEmail(String email) {
        this.email = email;
    }

    @JsonProperty("name")
    public String getName() {
        return name;
    }

    @JsonProperty("name")
    public void setName(String name) {
        this.name = name;
    }

    @JsonProperty("phone")
    public String getPhone() {
        return phone;
    }

    @JsonProperty("phone")
    public void setPhone(String phone) {
        this.phone = phone;
    }

    @JsonProperty("state")
    public String getState() {
        return state;
    }

    @JsonProperty("state")
    public void setState(String state) {
        this.state = state;
    }

    @JsonProperty("storeTagLine")
    public String getStoreTagLine() {
        return storeTagLine;
    }

    @JsonProperty("storeTagLine")
    public void setStoreTagLine(String storeTagLine) {
        this.storeTagLine = storeTagLine;
    }

    @JsonProperty("storeno")
    public Object getStoreno() {
        return storeno;
    }

    @JsonProperty("storeno")
    public void setStoreno(Object storeno) {
        this.storeno = storeno;
    }

    @JsonProperty("zip")
    public String getZip() {
        return zip;
    }

    @JsonProperty("zip")
    public void setZip(String zip) {
        this.zip = zip;
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