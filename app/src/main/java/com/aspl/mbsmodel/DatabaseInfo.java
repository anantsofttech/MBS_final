package com.aspl.mbsmodel;


import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "DatabaseName",
        "EnableOnlineStore"
})

public class DatabaseInfo {

    @JsonProperty("DatabaseName")
    private String DatabaseName;
    @JsonProperty("EnableOnlineStore")
    private Boolean EnableOnlineStore;

    public String getDatabaseName() {
        return DatabaseName;
    }

    public void setDatabaseName(String databaseName) {
        DatabaseName = databaseName;
    }

    public Boolean getEnableOnlineStore() {
        return EnableOnlineStore;
    }

    public void setEnableOnlineStore(Boolean enableOnlineStore) {
        EnableOnlineStore = enableOnlineStore;
    }
}
