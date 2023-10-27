package com.aspl.mbsmodel;

import org.json.JSONObject;

/**
 * Created by new on 11/10/2017.
 */
public class UserModel {

    public String Address1;
    public String Address2;
    public String BSSetup_DeliveryOption;
    public String BSSetup_HandDelivery;
    public String BSSetup_PayAtStore;
    public String BSSetup_PickUpStore;
    public String BSSetup_UberRush;
    public String City;
    public String CompanyName;
    public static String Cust_mst_ID;
    public static String Email;
    public static String FirstName;
    public String HandDeliveryAreaText;
    public String ID;
    public String IsDefault;
    public String IsLoyalityRewards;
    public static String LastName;
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

    public Boolean Isguest;

    public UserModel(){}

    public UserModel(JSONObject userObj) {
        Address1=userObj.optString("Address1");
        Address2=userObj.optString("Address2");
        BSSetup_DeliveryOption=userObj.optString("BSSetup_DeliveryOption");
        BSSetup_HandDelivery=userObj.optString("BSSetup_HandDelivery");
        BSSetup_PayAtStore=userObj.optString("BSSetup_PayAtStore");
        BSSetup_PickUpStore=userObj.optString("BSSetup_PickUpStore");
        BSSetup_UberRush=userObj.optString("BSSetup_UberRush");
        City=userObj.optString("City");
        CompanyName=userObj.optString("CompanyName");
        Cust_mst_ID=userObj.optString("Cust_mst_ID");
        if(Cust_mst_ID.isEmpty() || Cust_mst_ID.equals("null")){
            Cust_mst_ID = "0";
        }
        Email=userObj.optString("Email");
        FirstName=userObj.optString("FirstName");
        HandDeliveryAreaText=userObj.optString("HandDeliveryAreaText");
        ID=userObj.optString("ID");
        IsDefault=userObj.optString("IsDefault");
        IsLoyalityRewards=userObj.optString("IsLoyalityRewards");
        LastName=userObj.optString("LastName");
        Lock=userObj.optString("Lock");
        Message=userObj.optString("Message");
        OTP=userObj.optString("OTP");
        Password=userObj.optString("Password");
        Phone=userObj.optString("Phone");
        Phonetype=userObj.optString("Phonetype");
        Result=userObj.optString("Result");
        Shipping_Id=userObj.optString("Shipping_Id");
        State=userObj.optString("State");
        StorePhone=userObj.optString("StorePhone");
        Type=userObj.optString("Type");
        WebStoreStatus=userObj.optString("WebStoreStatus");
        Zip=userObj.optString("Zip");

        Isguest = userObj.optBoolean("Isguest");

    }
}
