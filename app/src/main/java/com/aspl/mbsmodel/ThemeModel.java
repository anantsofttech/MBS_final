package com.aspl.mbsmodel;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by new on 11/06/2017.
 */
public class ThemeModel {

    public String Backgroundcolor="";
    public String DetailId="";
    public String EcomURL="";
    public String FavImage="";
    public String Font="";
    public String FontColor="";
    public String IsLoyalityRewards="";
    public boolean SBActiveDisplay=false;
    public String SBBackColor="";
    public int SBBannerID=0;
    public String SBFontColor="";
    public String SBFontFamily="";
    public int SBFontSize=0;
    public boolean SBFontStyle=false;
    public String SBPageContent="";
    public String SBPageName="";
    public String SBText_Align="";
    public boolean SBfont_weight=false;
    public boolean SBtext_decoration=false;
    public String SiteAuthor="";
    public String SiteDescription="";
    public String SiteKeyword="";
    public String SiteTitle="";
    public String StoreLogo="";
    public String StoreName="";
    public String StoreNo="";
    public String StoreTagLine="";
    public String ThemeColor="";

    public ThemeModel(JSONObject themObj) {
        try {
            Backgroundcolor=themObj.getString("Backgroundcolor");
            DetailId=themObj.getString("DetailId");
            EcomURL=themObj.getString("EcomURL");
            FavImage=themObj.getString("FavImage");
            Font=themObj.getString("Font");
            IsLoyalityRewards=themObj.getString("IsLoyalityRewards");
            SBActiveDisplay=themObj.getBoolean("SBActiveDisplay");
            SBBackColor=themObj.getString("SBBackColor");
            SBBannerID=themObj.getInt("SBBannerID");
            SBFontColor=themObj.getString("SBFontColor");
            SBFontFamily=themObj.getString("SBFontFamily");
            SBFontSize=themObj.getInt("SBFontSize");
            SBFontStyle=themObj.getBoolean("SBFontStyle");
            SBPageContent=themObj.getString("SBPageContent");
            SBPageName=themObj.getString("SBPageName");
            SBText_Align=themObj.getString("SBText_Align");
            SBfont_weight=themObj.getBoolean("SBfont_weight");
            SBtext_decoration=themObj.getBoolean("SBtext_decoration");
            SiteAuthor=themObj.getString("SiteAuthor");
            SiteDescription=themObj.getString("SiteDescription");
            SiteKeyword=themObj.getString("SiteKeyword");
            SiteTitle=themObj.getString("SiteTitle");
            StoreLogo=themObj.getString("StoreLogo");
            StoreName=themObj.getString("StoreName");
            StoreNo=themObj.getString("StoreNo");
            StoreTagLine=themObj.getString("StoreTagLine");
            ThemeColor=themObj.getString("ThemeColor");
            FontColor=themObj.getString("FontColor");
        } catch (JSONException e) {
            e.printStackTrace();
        }


    }

    public ThemeModel() {

    }
}
