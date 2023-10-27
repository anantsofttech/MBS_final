package com.aspl.mbsmodel;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by new on 11/06/2017.
 */
public class FooterModel {
    public String ID;
    public String PageContent;
    public String PageName;
    public String PageTitle;

    public FooterModel(JSONObject fotterObj) throws JSONException {
        this.ID=fotterObj.getString("ID");
        this.PageContent=fotterObj.getString("PageContent");
        this.PageName=fotterObj.getString("PageName");
        this.PageTitle=fotterObj.getString("PageTitle");
    }
}
