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
            "Customer",
            "InvoiceNo",
            "OrderDate",
            "OrderTotal",
            "RowNum",
            "Status",
            "TotalRow"
})

public class LightningOrderModel {

        @JsonProperty("Customer")
        private String customer;
        @JsonProperty("InvoiceNo")
        private String invoiceNo;
        @JsonProperty("OrderDate")
        private String orderDate;
        @JsonProperty("OrderTotal")
        private String orderTotal;
        @JsonProperty("RowNum")
        private String rowNum;
        @JsonProperty("Status")
        private String status;
        @JsonProperty("TotalRow")
        private String totalRow;
        @JsonIgnore
        private Map<String, Object> additionalProperties = new HashMap<String, Object>();

        @JsonProperty("Customer")
        public String getCustomer() {
            return customer;
        }

        @JsonProperty("Customer")
        public void setCustomer(String customer) {
            this.customer = customer;
        }

        @JsonProperty("InvoiceNo")
        public String getInvoiceNo() {
            return invoiceNo;
        }

        @JsonProperty("InvoiceNo")
        public void setInvoiceNo(String invoiceNo) {
            this.invoiceNo = invoiceNo;
        }

        @JsonProperty("OrderDate")
        public String getOrderDate() {
            return orderDate;
        }

        @JsonProperty("OrderDate")
        public void setOrderDate(String orderDate) {
            this.orderDate = orderDate;
        }

        @JsonProperty("OrderTotal")
        public String getOrderTotal() {
            return orderTotal;
        }

        @JsonProperty("OrderTotal")
        public void setOrderTotal(String orderTotal) {
            this.orderTotal = orderTotal;
        }

        @JsonProperty("RowNum")
        public String getRowNum() {
            return rowNum;
        }

        @JsonProperty("RowNum")
        public void setRowNum(String rowNum) {
            this.rowNum = rowNum;
        }

        @JsonProperty("Status")
        public String getStatus() {
            return status;
        }

        @JsonProperty("Status")
        public void setStatus(String status) {
            this.status = status;
        }

        @JsonProperty("TotalRow")
        public String getTotalRow() {
            return totalRow;
        }

        @JsonProperty("TotalRow")
        public void setTotalRow(String totalRow) {
            this.totalRow = totalRow;
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
