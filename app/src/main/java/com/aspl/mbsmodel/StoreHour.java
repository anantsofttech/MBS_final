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
        "CloseTime",
        "Closed",
        "Comment",
        "Commentid",
        "OpenTime",
        "StoreDay",
        "Storeno",
        "TimeZone",
        "commentdate",
        "id"
})
public class StoreHour {

    @JsonProperty("CloseTime")
    private String closeTime;
    @JsonProperty("Closed")
    private Boolean closed;
    @JsonProperty("Comment")
    private String comment;
    @JsonProperty("Commentid")
    private Integer commentid;
    @JsonProperty("OpenTime")
    private String openTime;
    @JsonProperty("StoreDay")
    private String storeDay;
    @JsonProperty("Storeno")
    private String storeno;
    @JsonProperty("TimeZone")
    private String timeZone;
    @JsonProperty("commentdate")
    private String commentdate;
    @JsonProperty("id")
    private Integer id;
    @JsonIgnore
    private Map<String, String> additionalProperties = new HashMap<String, String>();

    @JsonProperty("CloseTime")
    public String getCloseTime() {
        return closeTime;
    }

    @JsonProperty("CloseTime")
    public void setCloseTime(String closeTime) {
        this.closeTime = closeTime;
    }

    @JsonProperty("Closed")
    public Boolean getClosed() {
        return closed;
    }

    @JsonProperty("Closed")
    public void setClosed(Boolean closed) {
        this.closed = closed;
    }

    @JsonProperty("Comment")
    public String getComment() {
        return comment;
    }

    @JsonProperty("Comment")
    public void setComment(String comment) {
        this.comment = comment;
    }

    @JsonProperty("Commentid")
    public Integer getCommentid() {
        return commentid;
    }

    @JsonProperty("Commentid")
    public void setCommentid(Integer commentid) {
        this.commentid = commentid;
    }

    @JsonProperty("OpenTime")
    public String getOpenTime() {
        return openTime;
    }

    @JsonProperty("OpenTime")
    public void setOpenTime(String openTime) {
        this.openTime = openTime;
    }

    @JsonProperty("StoreDay")
    public String getStoreDay() {
        return storeDay;
    }

    @JsonProperty("StoreDay")
    public void setStoreDay(String storeDay) {
        this.storeDay = storeDay;
    }

    @JsonProperty("Storeno")
    public String getStoreno() {
        return storeno;
    }

    @JsonProperty("Storeno")
    public void setStoreno(String storeno) {
        this.storeno = storeno;
    }

    @JsonProperty("TimeZone")
    public String getTimeZone() {
        return timeZone;
    }

    @JsonProperty("TimeZone")
    public void setTimeZone(String timeZone) {
        this.timeZone = timeZone;
    }

    @JsonProperty("commentdate")
    public String getCommentdate() {
        return commentdate;
    }

    @JsonProperty("commentdate")
    public void setCommentdate(String commentdate) {
        this.commentdate = commentdate;
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
    public Map<String, String> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, String value) {
        this.additionalProperties.put(name, value);
    }

}