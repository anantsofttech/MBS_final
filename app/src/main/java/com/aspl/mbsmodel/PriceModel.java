package com.aspl.mbsmodel;

import org.json.JSONObject;

/**
 * Created by new on 07/13/2017.
 */
public class PriceModel {

    public  String PriceName;
    public  String PriceUrlKey;
    public PriceModel(JSONObject jsonObject){
        this.PriceName=jsonObject.optString("PriceName");
        this.PriceUrlKey=jsonObject.optString("PriceUrlKey");
    }

}
