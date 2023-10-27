package com.aspl.mbsmodel;

import org.json.JSONObject;

/**
 * Created by new on 11/13/2017.
 */
public class OtpModel {
    public String Address1;
    public String Address2;
    public String BSSetup_DeliveryOption;
    public String BSSetup_HandDelivery;
    public String BSSetup_PayAtStore;
    public String BSSetup_PickUpStore;
    public String BSSetup_UberRush;
    public String City;
    public String CompanyName;
    public String Cust_mst_ID;
    public String Email;
    public String FirstName;
    public String HandDeliveryAreaText;
    public String ID;
    public String IsDefault;
    public String IsLoyalityRewards;
    public Boolean Isguest;
    public String LastName;
    public String Lock;
    public String Message;
    public String OTP;
    public String Password;
    public String Phone;
    public String Phonetype;
    public String Result;
    public String Shipping_Id;
    public String State;
    public String StorePhone;
    public String Type;
    public String WebStoreStatus;
    public String Zip;

    public OtpModel(JSONObject otpObj){
        Address1=otpObj.optString("Address1");
        Address2=otpObj.optString("Address2");
        BSSetup_DeliveryOption=otpObj.optString("BSSetup_DeliveryOption");
        BSSetup_HandDelivery=otpObj.optString("BSSetup_HandDelivery");
        BSSetup_PayAtStore=otpObj.optString("BSSetup_PayAtStore");
        BSSetup_PickUpStore=otpObj.optString("BSSetup_PickUpStore");
        BSSetup_UberRush=otpObj.optString("BSSetup_UberRush");
        City=otpObj.optString("City");
        CompanyName=otpObj.optString("CompanyName");
        Cust_mst_ID=otpObj.optString("Cust_mst_ID");
        Email=otpObj.optString("Email");
        FirstName=otpObj.optString("FirstName");
        HandDeliveryAreaText=otpObj.optString("HandDeliveryAreaText");
        ID=otpObj.optString("ID");
        IsDefault=otpObj.optString("IsDefault");
        IsLoyalityRewards=otpObj.optString("IsLoyalityRewards");
        Isguest = otpObj.optBoolean("Isguest");
        LastName=otpObj.optString("LastName");
        Lock=otpObj.optString("Lock");
        Message=otpObj.optString("Message");
        OTP=otpObj.optString("OTP");
        Password=otpObj.optString("Password");
        Phone=otpObj.optString("Phone");
        Phonetype=otpObj.optString("Phonetype");
        Result=otpObj.optString("Result");
        Shipping_Id=otpObj.optString("Shipping_Id");
        State=otpObj.optString("State");
        StorePhone=otpObj.optString("StorePhone");
        Type=otpObj.optString("Type");
        WebStoreStatus=otpObj.optString("WebStoreStatus");
        Zip=otpObj.optString("Zip");
    }
}
