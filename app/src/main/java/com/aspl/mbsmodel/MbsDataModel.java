package com.aspl.mbsmodel;

import com.aspl.Utils.Constant;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

/**
 * Created by new on 07/13/2017.
 */
public class MbsDataModel {

    /*   public  String AccountOptionID ;
       public  String AccountOption_Name ;
       public  String AccountOption_Url ;
       public  String AccountOption_flag ;*/
    public  String FooterId;
    public  String Footer_Name;
    public  String Footer_Url;
    public  String Footer_flag;

    public  String SortId;
    public  String Sort_Name;
    public  String Sort_Key;
    public  String Sort_flag;

    public  String deptId;
    public  String dept_Name;
    public  String dept_flag;
    public  String dept_Url;
    public  boolean isChecked=false;

    public ArrayList<SubDeptModel> SubDeptList=new ArrayList<>();
    public ArrayList<SubDeptModel> PriceList=new ArrayList<>();
    public ArrayList<SubDeptModel> SizeList=new ArrayList<>();
    public LinkedHashMap<String, List<SubDeptModel>> LHSFILTER_LIST = new LinkedHashMap<String, List<SubDeptModel>>();

    public String ID;
    public String PageName;
    public String PageTitle;
    public String PageContent;
    public int position;
    public boolean status;
    public int inv_count;


    public MbsDataModel(JSONObject fotterObj) throws JSONException {
        this.ID=fotterObj.optString("ID");
        this.PageName=fotterObj.optString("PageName");
        this.PageTitle=fotterObj.optString("PageTitle");
        this.PageContent=fotterObj.optString("PageContent");
        this.position=fotterObj.optInt("Position");
        this.status=fotterObj.optBoolean("Status");
        this.inv_count=fotterObj.optInt("inv_count");

        if(PageName.contains("AboutUs")){
            PageName="About Us";
            PageTitle="About Us";
        }else if(PageName.contains("Blog")){
            if(Constant.ISDELIVERY_HOUR_DISP) {
                PageName = "Store/Delivery Hours";
                PageTitle= "Store/Delivery Hours";
            }else{
                PageTitle= "Store Hours";
                PageName = "Store Hours";
            }
//            status=Constant.ISDISPLAY_STOREHOUR;
            if(Constant.SCREEN_LAYOUT == 2) {
                status = Constant.storeStatus;
            }
        }else if(PageName.contains("Event Calendar")){
            if(Constant.SCREEN_LAYOUT == 2){
                status=Constant.ISSHOW_EVENTCAL;
            }
        }else if(PageName.contains("legal")){
            PageName="Legal";
            PageTitle="Legal";
        }else if(PageName.contains("pay_and_refund")){
            PageName="Payments & Refunds";
            PageTitle="Payments & Refunds";
        }else if(PageName.contains("Privacy")){
            PageName="Privacy";
            PageTitle="Privacy";
        }else if(PageName.contains("Supp_or_Cust")){
            PageName="Support & Customer Service";
            PageTitle="Support & Customer Service";
        }
//        else if(PageName.contains("WishList")){
//            PageName="Help";
//            PageTitle="Help";
//        }
    }

    public MbsDataModel(JSONObject jsonObject, int i) throws JSONException {
        if(i==1) {
            this.ID = jsonObject.optString("AccountOptionID");
            this.PageName = jsonObject.optString("AccountOption_Name");
            this.PageTitle = jsonObject.optString("AccountOption_Url");
            this.PageContent = jsonObject.optString("AccountOption_flag");
        }
        if(i==2) {
            this.FooterId = jsonObject.optString("FooterId");
            this.Footer_Name = jsonObject.optString("Footer_Name");
            this.Footer_Url = jsonObject.optString("Footer_Url");
            this.Footer_flag = jsonObject.optString("Footer_flag");
        }
        if(i==3) {
            this.SortId = jsonObject.optString("SortId");
            this.Sort_Name = jsonObject.optString("Sort_Name");
            this.Sort_Key = jsonObject.optString("Sort_Key");
            this.Sort_flag = jsonObject.optString("Sort_flag");
        }
        if(i==4) {
            this.deptId = jsonObject.optString("deptId");
            this.dept_Name = jsonObject.optString("dept_Name");
            this.dept_flag = jsonObject.optString("dept_flag");
            this.dept_Url = jsonObject.optString("dept_Url");

            JSONArray ArrSubdept=jsonObject.getJSONArray("SubDepartment");
            for(int j=0;j<ArrSubdept.length();j++){
                SubDeptModel subDeptModel=new SubDeptModel(ArrSubdept.getJSONObject(j),1);

                this.SubDeptList.add(subDeptModel);
            }
            LHSFILTER_LIST.put(""+dept_Name,SubDeptList);
            JSONArray ArrPrice=jsonObject.getJSONArray("Price");
            for(int j=0;j<ArrPrice.length();j++){
                SubDeptModel priceModel=new SubDeptModel(ArrPrice.getJSONObject(j),2);
                this.PriceList.add(priceModel);
            }

            LHSFILTER_LIST.put("Price",PriceList);
            JSONArray ArrSize=jsonObject.getJSONArray("Size");
            for(int j=0;j<ArrSize.length();j++){
                SubDeptModel sizeModel=new SubDeptModel(ArrSize.getJSONObject(j),3);
                this.SizeList.add(sizeModel);
            }

            LHSFILTER_LIST.put("Size",SizeList);
        }
    }

    public MbsDataModel() {

    }
}
