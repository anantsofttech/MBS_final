package com.aspl.mbsmodel;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "InvoiceNo",
        "sub",
        "pay1",
        "pay2",
        "pay3",
        "total",
        "tip",
        "sta",
        "sto",
        "OrderDate",
        "InvTime",
        "transid",
        "company_name",
        "cname",
        "address1",
        "address2",
        "city",
        "state",
        "zip",
        "TaxBit",
        "Tax2Bit",
        "Tax3Bit",
        "FlatTaxBit",
        "TaxName",
        "Tax2Name",
        "Tax3Name",
        "FlatName",
        "TaxPerc",
        "Tax2Perc",
        "Tax3Perc",
        "FlatTaxPerc",
        "NoSigRequired",
        "Deposit",
        "Memo_Desc",
        "CustEmailId",
        "shopname",
        "shopaddress",
        "shopcity",
        "shopst",
        "shopzip",
        "shopphone",
        "OrderStatus",
        "points",
        "SizeFlag"
})

public class InstorePurchaseDetailModel {

    @JsonProperty("InvoiceNo")
    private String invoiceNo;
    @JsonProperty("sub")
    private Double sub;
    @JsonProperty("pay1")
    private String pay1;
    @JsonProperty("pay2")
    private String pay2;
    @JsonProperty("pay3")
    private String pay3;
    @JsonProperty("total")
    private Double total;
    @JsonProperty("tip")
    private Double tip;
    @JsonProperty("sta")
    private String sta;
    @JsonProperty("sto")
    private String sto;
    @JsonProperty("OrderDate")
    private String orderDate;
    @JsonProperty("InvTime")
    private String invTime;
    @JsonProperty("transid")
    private String transid;
    @JsonProperty("company_name")
    private String companyName;
    @JsonProperty("cname")
    private String cname;
    @JsonProperty("address1")
    private String address1;
    @JsonProperty("address2")
    private String address2;
    @JsonProperty("city")
    private String city;
    @JsonProperty("state")
    private String state;
    @JsonProperty("zip")
    private String zip;
    @JsonProperty("TaxBit")
    private Boolean taxBit;
    @JsonProperty("Tax2Bit")
    private Boolean tax2Bit;
    @JsonProperty("Tax3Bit")
    private Boolean tax3Bit;
    @JsonProperty("FlatTaxBit")
    private Boolean flatTaxBit;
    @JsonProperty("TaxName")
    private String taxName;
    @JsonProperty("Tax2Name")
    private String tax2Name;
    @JsonProperty("Tax3Name")
    private String tax3Name;
    @JsonProperty("FlatName")
    private String flatName;
    @JsonProperty("TaxPerc")
    private Double taxPerc;
    @JsonProperty("Tax2Perc")
    private Double tax2Perc;
    @JsonProperty("Tax3Perc")
    private Double tax3Perc;
    @JsonProperty("FlatTaxPerc")
    private Double flatTaxPerc;
    @JsonProperty("NoSigRequired")
    private String noSigRequired;
    @JsonProperty("Deposit")
    private Double deposit;
    @JsonProperty("Memo_Desc")
    private String memoDesc;
    @JsonProperty("CustEmailId")
    private String custEmailId;
    @JsonProperty("shopname")
    private String shopname;
    @JsonProperty("shopaddress")
    private String shopaddress;
    @JsonProperty("shopcity")
    private String shopcity;
    @JsonProperty("shopst")
    private String shopst;
    @JsonProperty("shopzip")
    private String shopzip;
    @JsonProperty("shopphone")
    private String shopphone;
    @JsonProperty("OrderStatus")
    private String orderStatus;
    @JsonProperty("points")
    private Double points;
    @JsonProperty("SizeFlag")
    private Double sizeFlag;

    @JsonProperty("OrderDetails")
    private List<InStoreOrderDetailModel> orderDetailList = null;

    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("InvoiceNo")
    public String getInvoiceNo() {
        return invoiceNo;
    }

    @JsonProperty("InvoiceNo")
    public void setInvoiceNo(String invoiceNo) {
        this.invoiceNo = invoiceNo;
    }

    @JsonProperty("sub")
    public Double getSub() {
        return sub;
    }

    @JsonProperty("sub")
    public void setSub(Double sub) {
        this.sub = sub;
    }

    @JsonProperty("pay1")
    public String getPay1() {
        return pay1;
    }

    @JsonProperty("pay1")
    public void setPay1(String pay1) {
        this.pay1 = pay1;
    }

    @JsonProperty("pay2")
    public String getPay2() {
        return pay2;
    }

    @JsonProperty("pay2")
    public void setPay2(String pay2) {
        this.pay2 = pay2;
    }

    @JsonProperty("pay3")
    public String getPay3() {
        return pay3;
    }

    @JsonProperty("pay3")
    public void setPay3(String pay3) {
        this.pay3 = pay3;
    }

    @JsonProperty("total")
    public Double getTotal() {
        return total;
    }

    @JsonProperty("total")
    public void setTotal(Double total) {
        this.total = total;
    }

    @JsonProperty("tip")
    public Double getTip() {
        return tip;
    }

    @JsonProperty("tip")
    public void setTip(Double tip) {
        this.tip = tip;
    }

    @JsonProperty("sta")
    public String getSta() {
        return sta;
    }

    @JsonProperty("sta")
    public void setSta(String sta) {
        this.sta = sta;
    }

    @JsonProperty("sto")
    public String getSto() {
        return sto;
    }

    @JsonProperty("sto")
    public void setSto(String sto) {
        this.sto = sto;
    }

    @JsonProperty("OrderDate")
    public String getOrderDate() {
        return orderDate;
    }

    @JsonProperty("OrderDate")
    public void setOrderDate(String orderDate) {
        this.orderDate = orderDate;
    }

    @JsonProperty("InvTime")
    public String getInvTime() {
        return invTime;
    }

    @JsonProperty("InvTime")
    public void setInvTime(String invTime) {
        this.invTime = invTime;
    }

    @JsonProperty("transid")
    public String getTransid() {
        return transid;
    }

    @JsonProperty("transid")
    public void setTransid(String transid) {
        this.transid = transid;
    }

    @JsonProperty("company_name")
    public String getCompanyName() {
        return companyName;
    }

    @JsonProperty("company_name")
    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    @JsonProperty("cname")
    public String getCname() {
        return cname;
    }

    @JsonProperty("cname")
    public void setCname(String cname) {
        this.cname = cname;
    }

    @JsonProperty("address1")
    public String getAddress1() {
        return address1;
    }

    @JsonProperty("address1")
    public void setAddress1(String address1) {
        this.address1 = address1;
    }

    @JsonProperty("address2")
    public String getAddress2() {
        return address2;
    }

    @JsonProperty("address2")
    public void setAddress2(String address2) {
        this.address2 = address2;
    }

    @JsonProperty("city")
    public String getCity() {
        return city;
    }

    @JsonProperty("city")
    public void setCity(String city) {
        this.city = city;
    }

    @JsonProperty("state")
    public String getState() {
        return state;
    }

    @JsonProperty("state")
    public void setState(String state) {
        this.state = state;
    }

    @JsonProperty("zip")
    public String getZip() {
        return zip;
    }

    @JsonProperty("zip")
    public void setZip(String zip) {
        this.zip = zip;
    }

    @JsonProperty("TaxBit")
    public Boolean getTaxBit() {
        return taxBit;
    }

    @JsonProperty("TaxBit")
    public void setTaxBit(Boolean taxBit) {
        this.taxBit = taxBit;
    }

    @JsonProperty("Tax2Bit")
    public Boolean getTax2Bit() {
        return tax2Bit;
    }

    @JsonProperty("Tax2Bit")
    public void setTax2Bit(Boolean tax2Bit) {
        this.tax2Bit = tax2Bit;
    }

    @JsonProperty("Tax3Bit")
    public Boolean getTax3Bit() {
        return tax3Bit;
    }

    @JsonProperty("Tax3Bit")
    public void setTax3Bit(Boolean tax3Bit) {
        this.tax3Bit = tax3Bit;
    }

    @JsonProperty("FlatTaxBit")
    public Boolean getFlatTaxBit() {
        return flatTaxBit;
    }

    @JsonProperty("FlatTaxBit")
    public void setFlatTaxBit(Boolean flatTaxBit) {
        this.flatTaxBit = flatTaxBit;
    }

    @JsonProperty("TaxName")
    public String getTaxName() {
        return taxName;
    }

    @JsonProperty("TaxName")
    public void setTaxName(String taxName) {
        this.taxName = taxName;
    }

    @JsonProperty("Tax2Name")
    public String getTax2Name() {
        return tax2Name;
    }

    @JsonProperty("Tax2Name")
    public void setTax2Name(String tax2Name) {
        this.tax2Name = tax2Name;
    }

    @JsonProperty("Tax3Name")
    public String getTax3Name() {
        return tax3Name;
    }

    @JsonProperty("Tax3Name")
    public void setTax3Name(String tax3Name) {
        this.tax3Name = tax3Name;
    }

    @JsonProperty("FlatName")
    public String getFlatName() {
        return flatName;
    }

    @JsonProperty("FlatName")
    public void setFlatName(String flatName) {
        this.flatName = flatName;
    }

    @JsonProperty("TaxPerc")
    public Double getTaxPerc() {
        return taxPerc;
    }

    @JsonProperty("TaxPerc")
    public void setTaxPerc(Double taxPerc) {
        this.taxPerc = taxPerc;
    }

    @JsonProperty("Tax2Perc")
    public Double getTax2Perc() {
        return tax2Perc;
    }

    @JsonProperty("Tax2Perc")
    public void setTax2Perc(Double tax2Perc) {
        this.tax2Perc = tax2Perc;
    }

    @JsonProperty("Tax3Perc")
    public Double getTax3Perc() {
        return tax3Perc;
    }

    @JsonProperty("Tax3Perc")
    public void setTax3Perc(Double tax3Perc) {
        this.tax3Perc = tax3Perc;
    }

    @JsonProperty("FlatTaxPerc")
    public Double getFlatTaxPerc() {
        return flatTaxPerc;
    }

    @JsonProperty("FlatTaxPerc")
    public void setFlatTaxPerc(Double flatTaxPerc) {
        this.flatTaxPerc = flatTaxPerc;
    }

    @JsonProperty("NoSigRequired")
    public String getNoSigRequired() {
        return noSigRequired;
    }

    @JsonProperty("NoSigRequired")
    public void setNoSigRequired(String noSigRequired) {
        this.noSigRequired = noSigRequired;
    }

    @JsonProperty("Deposit")
    public Double getDeposit() {
        return deposit;
    }

    @JsonProperty("Deposit")
    public void setDeposit(Double deposit) {
        this.deposit = deposit;
    }

    @JsonProperty("Memo_Desc")
    public String getMemoDesc() {
        return memoDesc;
    }

    @JsonProperty("Memo_Desc")
    public void setMemoDesc(String memoDesc) {
        this.memoDesc = memoDesc;
    }

    @JsonProperty("CustEmailId")
    public String getCustEmailId() {
        return custEmailId;
    }

    @JsonProperty("CustEmailId")
    public void setCustEmailId(String custEmailId) {
        this.custEmailId = custEmailId;
    }

    @JsonProperty("shopname")
    public String getShopname() {
        return shopname;
    }

    @JsonProperty("shopname")
    public void setShopname(String shopname) {
        this.shopname = shopname;
    }

    @JsonProperty("shopaddress")
    public String getShopaddress() {
        return shopaddress;
    }

    @JsonProperty("shopaddress")
    public void setShopaddress(String shopaddress) {
        this.shopaddress = shopaddress;
    }

    @JsonProperty("shopcity")
    public String getShopcity() {
        return shopcity;
    }

    @JsonProperty("shopcity")
    public void setShopcity(String shopcity) {
        this.shopcity = shopcity;
    }

    @JsonProperty("shopst")
    public String getShopst() {
        return shopst;
    }

    @JsonProperty("shopst")
    public void setShopst(String shopst) {
        this.shopst = shopst;
    }

    @JsonProperty("shopzip")
    public String getShopzip() {
        return shopzip;
    }

    @JsonProperty("shopzip")
    public void setShopzip(String shopzip) {
        this.shopzip = shopzip;
    }

    @JsonProperty("shopphone")
    public String getShopphone() {
        return shopphone;
    }

    @JsonProperty("shopphone")
    public void setShopphone(String shopphone) {
        this.shopphone = shopphone;
    }

    @JsonProperty("OrderStatus")
    public String getOrderStatus() {
        return orderStatus;
    }

    @JsonProperty("OrderStatus")
    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }

    @JsonProperty("points")
    public Double getPoints() {
        return points;
    }

    @JsonProperty("points")
    public void setPoints(Double points) {
        this.points = points;
    }

    @JsonProperty("SizeFlag")
    public Double getSizeFlag() {
        return sizeFlag;
    }

    @JsonProperty("SizeFlag")
    public void setSizeFlag(Double sizeFlag) {
        this.sizeFlag = sizeFlag;
    }

    @JsonProperty("OrderDetails")
    public List<InStoreOrderDetailModel> getOrderDetailList() {
        return orderDetailList;
    }

    @JsonProperty("OrderDetails")
    public InstorePurchaseDetailModel setOrderDetailList(List<InStoreOrderDetailModel> orderDetailList) {
        this.orderDetailList = orderDetailList;
        return this;
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
