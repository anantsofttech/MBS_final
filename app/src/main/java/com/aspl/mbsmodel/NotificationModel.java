package com.aspl.mbsmodel;

/**
 * Created by admin on 8/29/2018.
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
        "Description",
        "EndDate",
        "NewNotificationCount",
        "NotificationID",
        "NotificationImage",
        "NotificationType",
        "Result",
        "StartDate",
        "Title",
        "page_count"
})
public class NotificationModel {

    @JsonProperty("Description")
    private String description;
    @JsonProperty("EndDate")
    private Object endDate;
    @JsonProperty("NewNotificationCount")
    private Integer newNotificationCount;
    @JsonProperty("NotificationID")
    private Integer notificationID;
    @JsonProperty("NotificationImage")
    private String notificationImage;
    @JsonProperty("NotificationType")
    private String notificationType;
    @JsonProperty("Result")
    private Object result;
    @JsonProperty("StartDate")
    private Object startDate;
    @JsonProperty("Title")
    private String title;
    @JsonProperty("page_count")
    private Integer pageCount;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("Description")
    public String getDescription() {
        return description;
    }

    @JsonProperty("Description")
    public void setDescription(String description) {
        this.description = description;
    }

    @JsonProperty("EndDate")
    public Object getEndDate() {
        return endDate;
    }

    @JsonProperty("EndDate")
    public void setEndDate(Object endDate) {
        this.endDate = endDate;
    }

    @JsonProperty("NewNotificationCount")
    public Integer getNewNotificationCount() {
        return newNotificationCount;
    }

    @JsonProperty("NewNotificationCount")
    public void setNewNotificationCount(Integer newNotificationCount) {
        this.newNotificationCount = newNotificationCount;
    }

    @JsonProperty("NotificationID")
    public Integer getNotificationID() {
        return notificationID;
    }

    @JsonProperty("NotificationID")
    public void setNotificationID(Integer notificationID) {
        this.notificationID = notificationID;
    }

    @JsonProperty("NotificationImage")
    public String getNotificationImage() {
        return notificationImage;
    }

    @JsonProperty("NotificationImage")
    public void setNotificationImage(String notificationImage) {
        this.notificationImage = notificationImage;
    }

    @JsonProperty("NotificationType")
    public String getNotificationType() {
        return notificationType;
    }

    @JsonProperty("NotificationType")
    public void setNotificationType(String notificationType) {
        this.notificationType = notificationType;
    }

    @JsonProperty("Result")
    public Object getResult() {
        return result;
    }

    @JsonProperty("Result")
    public void setResult(Object result) {
        this.result = result;
    }

    @JsonProperty("StartDate")
    public Object getStartDate() {
        return startDate;
    }

    @JsonProperty("StartDate")
    public void setStartDate(Object startDate) {
        this.startDate = startDate;
    }

    @JsonProperty("Title")
    public String getTitle() {
        return title;
    }

    @JsonProperty("Title")
    public void setTitle(String title) {
        this.title = title;
    }

    @JsonProperty("page_count")
    public Integer getPageCount() {
        return pageCount;
    }

    @JsonProperty("page_count")
    public void setPageCount(Integer pageCount) {
        this.pageCount = pageCount;
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