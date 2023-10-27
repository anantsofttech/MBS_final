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
        "Address1",
        "Address2",
        "AllowSurchargeDelivery",
        "BSSetup_DeliveryOption",
        "BSSetup_HandDelivery",
        "BSSetup_PayAtStore",
        "BSSetup_PickUpStore",
        "BSSetup_UberRush",
        "City",
        "CompanyName",
        "Cust_mst_ID",
        "DefineDeliveryArea",
        "DontAcceptOrder",
        "Email",
        "EncEmail",
        "EncFirstName",
        "EncLastName",
        "Encempadmin",
        "Encstoreno",
        "FavLocation",
        "FirstName",
        "HandDeliveryAreaText",
        "HandDeliveryPrice",
        "ID",
        "IsDefault",
        "IsLoyalityRewards",
        "Isguest",
        "LastName",
        "Lock",
        "Message",
        "OTP",
        "Password",
        "Phone",
        "Phonetype",
        "Result",
        "Shipping_Id",
        "State",
        "StorePhone",
        "SurchargePrice",
        "Type",
        "WarnCustomers",
        "WebStoreStatus",
        "Zip",
        "encpass",
        "encstoreinfo",
        "encver"
})
public class EditShippingData{

    @JsonProperty("Address1")
    private String address1;
    @JsonProperty("Address2")
    private String address2;
    @JsonProperty("AllowSurchargeDelivery")
    private Boolean allowSurchargeDelivery;
    @JsonProperty("BSSetup_DeliveryOption")
    private Boolean bSSetupDeliveryOption;
    @JsonProperty("BSSetup_HandDelivery")
    private Boolean bSSetupHandDelivery;
    @JsonProperty("BSSetup_PayAtStore")
    private Boolean bSSetupPayAtStore;
    @JsonProperty("BSSetup_PickUpStore")
    private Boolean bSSetupPickUpStore;
    @JsonProperty("BSSetup_UberRush")
    private Boolean bSSetupUberRush;
    @JsonProperty("City")
    private String city;
    @JsonProperty("CompanyName")
    private String companyName;
    @JsonProperty("Cust_mst_ID")
    private String custMstID;
    @JsonProperty("DefineDeliveryArea")
    private Boolean defineDeliveryArea;
    @JsonProperty("DontAcceptOrder")
    private Boolean dontAcceptOrder;
    @JsonProperty("Email")
    private Object email;
    @JsonProperty("EncEmail")
    private Object encEmail;
    @JsonProperty("EncFirstName")
    private Object encFirstName;
    @JsonProperty("EncLastName")
    private Object encLastName;
    @JsonProperty("Encempadmin")
    private Object encempadmin;
    @JsonProperty("Encstoreno")
    private Object encstoreno;
    @JsonProperty("FavLocation")
    private Object favLocation;
    @JsonProperty("FirstName")
    private String firstName;
    @JsonProperty("HandDeliveryAreaText")
    private Object handDeliveryAreaText;
    @JsonProperty("HandDeliveryPrice")
    private Object handDeliveryPrice;
    @JsonProperty("ID")
    private String id;
    @JsonProperty("IsDefault")
    private Object isDefault;
    @JsonProperty("IsLoyalityRewards")
    private Object isLoyalityRewards;
    @JsonProperty("Isguest")
    private Object isguest;
    @JsonProperty("LastName")
    private String lastName;
    @JsonProperty("Lock")
    private Object lock;
    @JsonProperty("Message")
    private Object message;
    @JsonProperty("OTP")
    private Object otp;
    @JsonProperty("Password")
    private Object password;
    @JsonProperty("Phone")
    private String phone;
    @JsonProperty("Phonetype")
    private String phonetype;
    @JsonProperty("Result")
    private Object result;
    @JsonProperty("Shipping_Id")
    private Integer shippingId;
    @JsonProperty("State")
    private String state;
    @JsonProperty("StorePhone")
    private Object storePhone;
    @JsonProperty("SurchargePrice")
    private Object surchargePrice;
    @JsonProperty("Type")
    private Object type;
    @JsonProperty("WarnCustomers")
    private Boolean warnCustomers;
    @JsonProperty("WebStoreStatus")
    private Integer webStoreStatus;
    @JsonProperty("Zip")
    private String zip;
    @JsonProperty("encpass")
    private Object encpass;
    @JsonProperty("encstoreinfo")
    private Object encstoreinfo;
    @JsonProperty("encver")
    private Object encver;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("Address1")
    public String getAddress1() {
        return address1;
    }

    @JsonProperty("Address1")
    public void setAddress1(String address1) {
        this.address1 = address1;
    }

    @JsonProperty("Address2")
    public String getAddress2() {
        return address2;
    }

    @JsonProperty("Address2")
    public void setAddress2(String address2) {
        this.address2 = address2;
    }

    @JsonProperty("AllowSurchargeDelivery")
    public Boolean getAllowSurchargeDelivery() {
        return allowSurchargeDelivery;
    }

    @JsonProperty("AllowSurchargeDelivery")
    public void setAllowSurchargeDelivery(Boolean allowSurchargeDelivery) {
        this.allowSurchargeDelivery = allowSurchargeDelivery;
    }

    @JsonProperty("BSSetup_DeliveryOption")
    public Boolean getBSSetupDeliveryOption() {
        return bSSetupDeliveryOption;
    }

    @JsonProperty("BSSetup_DeliveryOption")
    public void setBSSetupDeliveryOption(Boolean bSSetupDeliveryOption) {
        this.bSSetupDeliveryOption = bSSetupDeliveryOption;
    }

    @JsonProperty("BSSetup_HandDelivery")
    public Boolean getBSSetupHandDelivery() {
        return bSSetupHandDelivery;
    }

    @JsonProperty("BSSetup_HandDelivery")
    public void setBSSetupHandDelivery(Boolean bSSetupHandDelivery) {
        this.bSSetupHandDelivery = bSSetupHandDelivery;
    }

    @JsonProperty("BSSetup_PayAtStore")
    public Boolean getBSSetupPayAtStore() {
        return bSSetupPayAtStore;
    }

    @JsonProperty("BSSetup_PayAtStore")
    public void setBSSetupPayAtStore(Boolean bSSetupPayAtStore) {
        this.bSSetupPayAtStore = bSSetupPayAtStore;
    }

    @JsonProperty("BSSetup_PickUpStore")
    public Boolean getBSSetupPickUpStore() {
        return bSSetupPickUpStore;
    }

    @JsonProperty("BSSetup_PickUpStore")
    public void setBSSetupPickUpStore(Boolean bSSetupPickUpStore) {
        this.bSSetupPickUpStore = bSSetupPickUpStore;
    }

    @JsonProperty("BSSetup_UberRush")
    public Boolean getBSSetupUberRush() {
        return bSSetupUberRush;
    }

    @JsonProperty("BSSetup_UberRush")
    public void setBSSetupUberRush(Boolean bSSetupUberRush) {
        this.bSSetupUberRush = bSSetupUberRush;
    }

    @JsonProperty("City")
    public String getCity() {
        return city;
    }

    @JsonProperty("City")
    public void setCity(String city) {
        this.city = city;
    }

    @JsonProperty("CompanyName")
    public String getCompanyName() {
        return companyName;
    }

    @JsonProperty("CompanyName")
    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    @JsonProperty("Cust_mst_ID")
    public String getCustMstID() {
        return custMstID;
    }

    @JsonProperty("Cust_mst_ID")
    public void setCustMstID(String custMstID) {
        this.custMstID = custMstID;
    }

    @JsonProperty("DefineDeliveryArea")
    public Boolean getDefineDeliveryArea() {
        return defineDeliveryArea;
    }

    @JsonProperty("DefineDeliveryArea")
    public void setDefineDeliveryArea(Boolean defineDeliveryArea) {
        this.defineDeliveryArea = defineDeliveryArea;
    }

    @JsonProperty("DontAcceptOrder")
    public Boolean getDontAcceptOrder() {
        return dontAcceptOrder;
    }

    @JsonProperty("DontAcceptOrder")
    public void setDontAcceptOrder(Boolean dontAcceptOrder) {
        this.dontAcceptOrder = dontAcceptOrder;
    }

    @JsonProperty("Email")
    public Object getEmail() {
        return email;
    }

    @JsonProperty("Email")
    public void setEmail(Object email) {
        this.email = email;
    }

    @JsonProperty("EncEmail")
    public Object getEncEmail() {
        return encEmail;
    }

    @JsonProperty("EncEmail")
    public void setEncEmail(Object encEmail) {
        this.encEmail = encEmail;
    }

    @JsonProperty("EncFirstName")
    public Object getEncFirstName() {
        return encFirstName;
    }

    @JsonProperty("EncFirstName")
    public void setEncFirstName(Object encFirstName) {
        this.encFirstName = encFirstName;
    }

    @JsonProperty("EncLastName")
    public Object getEncLastName() {
        return encLastName;
    }

    @JsonProperty("EncLastName")
    public void setEncLastName(Object encLastName) {
        this.encLastName = encLastName;
    }

    @JsonProperty("Encempadmin")
    public Object getEncempadmin() {
        return encempadmin;
    }

    @JsonProperty("Encempadmin")
    public void setEncempadmin(Object encempadmin) {
        this.encempadmin = encempadmin;
    }

    @JsonProperty("Encstoreno")
    public Object getEncstoreno() {
        return encstoreno;
    }

    @JsonProperty("Encstoreno")
    public void setEncstoreno(Object encstoreno) {
        this.encstoreno = encstoreno;
    }

    @JsonProperty("FavLocation")
    public Object getFavLocation() {
        return favLocation;
    }

    @JsonProperty("FavLocation")
    public void setFavLocation(Object favLocation) {
        this.favLocation = favLocation;
    }

    @JsonProperty("FirstName")
    public String getFirstName() {
        return firstName;
    }

    @JsonProperty("FirstName")
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    @JsonProperty("HandDeliveryAreaText")
    public Object getHandDeliveryAreaText() {
        return handDeliveryAreaText;
    }

    @JsonProperty("HandDeliveryAreaText")
    public void setHandDeliveryAreaText(Object handDeliveryAreaText) {
        this.handDeliveryAreaText = handDeliveryAreaText;
    }

    @JsonProperty("HandDeliveryPrice")
    public Object getHandDeliveryPrice() {
        return handDeliveryPrice;
    }

    @JsonProperty("HandDeliveryPrice")
    public void setHandDeliveryPrice(Object handDeliveryPrice) {
        this.handDeliveryPrice = handDeliveryPrice;
    }

    @JsonProperty("ID")
    public String getId() {
        return id;
    }

    @JsonProperty("ID")
    public void setId(String id) {
        this.id = id;
    }

    @JsonProperty("IsDefault")
    public Object getIsDefault() {
        return isDefault;
    }

    @JsonProperty("IsDefault")
    public void setIsDefault(Object isDefault) {
        this.isDefault = isDefault;
    }

    @JsonProperty("IsLoyalityRewards")
    public Object getIsLoyalityRewards() {
        return isLoyalityRewards;
    }

    @JsonProperty("IsLoyalityRewards")
    public void setIsLoyalityRewards(Object isLoyalityRewards) {
        this.isLoyalityRewards = isLoyalityRewards;
    }

    @JsonProperty("Isguest")
    public Object getIsguest() {
        return isguest;
    }

    @JsonProperty("Isguest")
    public void setIsguest(Object isguest) {
        this.isguest = isguest;
    }

    @JsonProperty("LastName")
    public String getLastName() {
        return lastName;
    }

    @JsonProperty("LastName")
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @JsonProperty("Lock")
    public Object getLock() {
        return lock;
    }

    @JsonProperty("Lock")
    public void setLock(Object lock) {
        this.lock = lock;
    }

    @JsonProperty("Message")
    public Object getMessage() {
        return message;
    }

    @JsonProperty("Message")
    public void setMessage(Object message) {
        this.message = message;
    }

    @JsonProperty("OTP")
    public Object getOtp() {
        return otp;
    }

    @JsonProperty("OTP")
    public void setOtp(Object otp) {
        this.otp = otp;
    }

    @JsonProperty("Password")
    public Object getPassword() {
        return password;
    }

    @JsonProperty("Password")
    public void setPassword(Object password) {
        this.password = password;
    }

    @JsonProperty("Phone")
    public String getPhone() {
        return phone;
    }

    @JsonProperty("Phone")
    public void setPhone(String phone) {
        this.phone = phone;
    }

    @JsonProperty("Phonetype")
    public String getPhonetype() {
        return phonetype;
    }

    @JsonProperty("Phonetype")
    public void setPhonetype(String phonetype) {
        this.phonetype = phonetype;
    }

    @JsonProperty("Result")
    public Object getResult() {
        return result;
    }

    @JsonProperty("Result")
    public void setResult(Object result) {
        this.result = result;
    }

    @JsonProperty("Shipping_Id")
    public Integer getShippingId() {
        return shippingId;
    }

    @JsonProperty("Shipping_Id")
    public void setShippingId(Integer shippingId) {
        this.shippingId = shippingId;
    }

    @JsonProperty("State")
    public String getState() {
        return state;
    }

    @JsonProperty("State")
    public void setState(String state) {
        this.state = state;
    }

    @JsonProperty("StorePhone")
    public Object getStorePhone() {
        return storePhone;
    }

    @JsonProperty("StorePhone")
    public void setStorePhone(Object storePhone) {
        this.storePhone = storePhone;
    }

    @JsonProperty("SurchargePrice")
    public Object getSurchargePrice() {
        return surchargePrice;
    }

    @JsonProperty("SurchargePrice")
    public void setSurchargePrice(Object surchargePrice) {
        this.surchargePrice = surchargePrice;
    }

    @JsonProperty("Type")
    public Object getType() {
        return type;
    }

    @JsonProperty("Type")
    public void setType(Object type) {
        this.type = type;
    }

    @JsonProperty("WarnCustomers")
    public Boolean getWarnCustomers() {
        return warnCustomers;
    }

    @JsonProperty("WarnCustomers")
    public void setWarnCustomers(Boolean warnCustomers) {
        this.warnCustomers = warnCustomers;
    }

    @JsonProperty("WebStoreStatus")
    public Integer getWebStoreStatus() {
        return webStoreStatus;
    }

    @JsonProperty("WebStoreStatus")
    public void setWebStoreStatus(Integer webStoreStatus) {
        this.webStoreStatus = webStoreStatus;
    }

    @JsonProperty("Zip")
    public String getZip() {
        return zip;
    }

    @JsonProperty("Zip")
    public void setZip(String zip) {
        this.zip = zip;
    }

    @JsonProperty("encpass")
    public Object getEncpass() {
        return encpass;
    }

    @JsonProperty("encpass")
    public void setEncpass(Object encpass) {
        this.encpass = encpass;
    }

    @JsonProperty("encstoreinfo")
    public Object getEncstoreinfo() {
        return encstoreinfo;
    }

    @JsonProperty("encstoreinfo")
    public void setEncstoreinfo(Object encstoreinfo) {
        this.encstoreinfo = encstoreinfo;
    }

    @JsonProperty("encver")
    public Object getEncver() {
        return encver;
    }

    @JsonProperty("encver")
    public void setEncver(Object encver) {
        this.encver = encver;
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