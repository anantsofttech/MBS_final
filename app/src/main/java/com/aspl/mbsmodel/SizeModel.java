package com.aspl.mbsmodel;

import org.json.JSONObject;

/**
 * Created by new on 07/13/2017.
 */
public class SizeModel {

    public  String SizeName;
    public  String SizeUrlKey;
    public SizeModel(JSONObject jsonObject){
        this.SizeName=jsonObject.optString("SizeName");
        this.SizeUrlKey=jsonObject.optString("SizeUrlKey");
    }
}
