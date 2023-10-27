package com.aspl.mbsmodel;


import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "Message",
        "POSCustID",
        "Result"

})

public class SignupLoyaltyInfo {
    @JsonProperty("Message")
    private String Message;
    @JsonProperty("POSCustID")
    private String POSCustID;
    @JsonProperty("Result")
    private String Result;

    public String getMessage() {
        return Message;
    }

    public void setMessage(String message) {
        Message = message;
    }

    public String getPOSCustID() {
        return POSCustID;
    }

    public void setPOSCustID(String POSCustID) {
        this.POSCustID = POSCustID;
    }

    public String getResult() {
        return Result;
    }

    public void setResult(String result) {
        Result = result;
    }
}
