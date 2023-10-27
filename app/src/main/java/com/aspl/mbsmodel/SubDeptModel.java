package com.aspl.mbsmodel;

import org.json.JSONObject;

/**
 * Created by new on 07/13/2017.
 */
public class SubDeptModel {

    public  String SubDeptId;
    public  String Subdept_flag;
    public  String SubDept_Name;
    public  String SubDept_Url;

    public  String PriceName;
    public  String PriceUrlKey;

    public  String SizeName;
    public  String SizeUrlKey;

    public  boolean isChecked=false;
    public SubDeptModel(JSONObject jsonObject, int i) {
        if(1==1) {
            this.SubDeptId = jsonObject.optString("SubDeptId");
            this.Subdept_flag = jsonObject.optString("Subdept_flag");
            this.SubDept_Name = jsonObject.optString("SubDept_Name");
            this.SubDept_Url = jsonObject.optString("SubDept_Url");
        }
        if(i==2){
            this.SubDept_Name=jsonObject.optString("PriceName");
            this.SubDept_Url=jsonObject.optString("PriceUrlKey");
        }
        if(i==3){
            this.SubDept_Name=jsonObject.optString("SizeName");
            this.SubDept_Url=jsonObject.optString("SizeUrlKey");
        }
    }
}
