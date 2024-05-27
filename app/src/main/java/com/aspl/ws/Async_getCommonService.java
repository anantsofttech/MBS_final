package com.aspl.ws;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.text.InputType;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.util.Base64;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import com.aspl.Adapter.ExpandAdapter;
import com.aspl.Utils.Constant;
import com.aspl.Utils.DeviceInfo;
import com.aspl.Utils.DialogUtils;
import com.aspl.Utils.Utils;
import com.aspl.Utils.XML_JSONParser;
import com.aspl.fragment.CardFragment;
import com.aspl.fragment.ChnagePasswordFragment;
import com.aspl.fragment.FilterFragment;
import com.aspl.fragment.HomepageFragment;
import com.aspl.fragment.Login;
import com.aspl.fragment.ProfileFragment_layout2;
import com.aspl.mbs.MainActivity;
import com.aspl.mbs.MainActivityDup;
import com.aspl.mbs.R;
import com.aspl.mbs.SplaceScreen;
import com.aspl.mbsmodel.CardModel;
import com.aspl.mbsmodel.DataFrontModel;
import com.aspl.mbsmodel.DataHomePageBlockModel;
import com.aspl.mbsmodel.MbsDataModel;
import com.aspl.mbsmodel.OtpModel;
import com.aspl.mbsmodel.PinModel;
import com.aspl.mbsmodel.PlaceModel;
import com.aspl.mbsmodel.SubDepartmentModel;
import com.aspl.mbsmodel.ThemeModel;
import com.aspl.mbsmodel.UserModel;
import com.aspl.task.TaskBlockDataFront;
import com.aspl.task.TaskHomePageBlockData;

import org.apache.http.NameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Background Async Task to download file
 */
public class Async_getCommonService extends AsyncTask<String, Void, Void> implements TaskBlockDataFront.TaskBlockDataFrontEvent , TaskHomePageBlockData.TaskHomePageBlockDataEvent {

    List<NameValuePair> nameValuePairList;
    String response;
    public static ProgressDialog progressBar;
    String username, password;

    private Context mContext;
    public static SharedPreferences preferences;
    XML_JSONParser parser;
    String strURL;
    private boolean isHiddenPassword1 = false;
    private boolean isHiddenPassword2 = false;
    private boolean isComefromChangePwd = false;
    String comefrom = "";

    public Async_getCommonService(Context context, String url) {
        mContext = context;
        strURL = url;
    }

    public Async_getCommonService(Context context, String url, boolean isComefromChangePwd) {
        mContext = context;
        strURL = url;
        this.isComefromChangePwd = isComefromChangePwd;
    }

    public Async_getCommonService(Context context, String url, String comefromLogin) {
        mContext = context;
        strURL = url;
        this.comefrom = comefromLogin;
    }

    @Override
    protected Void doInBackground(String... params) {
        nameValuePairList = new ArrayList<NameValuePair>();
        parser = new XML_JSONParser();

        Log.e("Log", "url=" + strURL);
        response = parser.callJSonWebService(strURL);
        Log.d("TEST", "HERE RESPONSE FROM GCMLOGIN IS : " + response);

        return null;

    }

    @SuppressLint("SuspiciousIndentation")
    @Override
    protected void onPostExecute(Void result) {
        // postexecute logic
        super.onPostExecute(result);
        // Log.e("Log","New RegID Result="+response);
        if (response == null) {
            Toast.makeText(mContext, "It seems to network is not available.", Toast.LENGTH_SHORT).show();
            //Utils.isNetworkConnectionAvailable(mContext)
        } else {
            if (strURL.contains(Constant.GETTHEME)) {
                try {
                    JSONObject themObj = new JSONObject(response);
                    Constant.themeModel = new ThemeModel(themObj);
//                    Edited by Varun For Speed -up
//                    SplaceScreen.getInstance().setTheme();
                } catch (JSONException e) {
                    e.printStackTrace();

                    if (mContext != null) {
                        Toast.makeText(mContext, "Something went wrong..", Toast.LENGTH_SHORT).show();
                    }
                }


//                Edited by Varun For Speed -up

                String Url1 = Constant.WS_BASE_URL + Constant.GET_INVENTORYBLOCK_DATAFORFRONT + Constant.STOREID;
                TaskBlockDataFront taskBlockDataFront = new TaskBlockDataFront(this);
//                taskBlockDataFront.execute(Url1);
                taskBlockDataFront.executeOnExecutor(TaskBlockDataFront.THREAD_POOL_EXECUTOR, Url1);

//                String OtherUrl1 = Constant.WS_BASE_URL + Constant.GETPAGES_STATUS + Constant.STOREID;
//                Log.d("URl", "otherUrl::" + OtherUrl1);
//                new Async_getCommonService(mContext, OtherUrl1).execute();
//
//                String Url = Constant.WS_BASE_URL + Constant.GETPAGES_DETAIL_BLOG + Constant.STOREID;
//                new Async_getCommonService(mContext, Url).execute();
//
//                String Url1 = Constant.WS_BASE_URL + Constant.GET_INVENTORYBLOCK_DATAFORFRONT + Constant.STOREID;
//                TaskBlockDataFront taskBlockDataFront = new TaskBlockDataFront(this);
//                taskBlockDataFront.execute(Url1);

//                END

            }
            else if (strURL.contains(Constant.GETPAGES_FOR_ANDROID))
                try {
                JSONArray footerArr = new JSONArray(response);

                Constant.FooterList = new ArrayList<MbsDataModel>();

                for (int i = 0; i < footerArr.length(); i++) {
                    MbsDataModel mbsDataModel = new MbsDataModel(footerArr.getJSONObject(i));

                    if (mbsDataModel.PageName.trim().equalsIgnoreCase("Mobile View")) {
                        Constant.SCREEN_LAYOUT = Integer.parseInt(mbsDataModel.ID.trim());
                    }

                    if (mbsDataModel.PageName.trim().equalsIgnoreCase("Store/Delivery Hours") || mbsDataModel.PageName.trim().equalsIgnoreCase("Store Hours")) {

                        if (mbsDataModel.status && Constant.storeStatus) {
                            if (i != 0) {
                                Constant.FooterList.add(mbsDataModel);
                            }
                        }
                    } else if (mbsDataModel.PageName.trim().equalsIgnoreCase("Event Calendar")) {

                        if (mbsDataModel.status && Constant.ISSHOW_EVENTCAL) {
                            if (i != 0) {
                                Constant.FooterList.add(mbsDataModel);
                            }
                        }
                    } else {
                        if (mbsDataModel.status) {
                            if (i != 0) {
                                if(mbsDataModel.PageName.trim().equalsIgnoreCase("WishList")){
                                }else{
                                    Constant.FooterList.add(mbsDataModel);
                                }

                            }
                        }
                    }
                }

                for (int i = 0; i < footerArr.length(); i++) {
                    MbsDataModel mbsDataModel = new MbsDataModel(footerArr.getJSONObject(i));
                    Constant.FooterModelList.add(mbsDataModel);
                }
                Constant.AccountList.clear();
                Constant.AccountList2.clear();

//                Edited by Varun for guest login
                Constant.ISguest = Constant.AppPref.getBoolean("ISguest",false);
                    Log.e("", "onPostExecute:2 "+Constant.ISguest );
                if (Constant.ISguest){
                    Utils.getAccountList2();
                    Constant.LHSLIDER_LIST.put("My Account", Constant.AccountList2);
                    Log.e("dgf", "onPostExecute:1 "+Constant.AccountList2.size() );
                }else {
//                  END
                    if (!Constant.AppPref.getString("email", "").isEmpty()) {
                        Utils.getAccountList();
                        Constant.LHSLIDER_LIST.put("My Account", Constant.AccountList);
                    }
                }

//                    Edited by Varun For Speed -up
//                    SplaceScreen.getInstance().callcontactinfo();
//                    SplaceScreen.getInstance().UpdateApp();

            } catch (JSONException e) {
                e.printStackTrace();
                if (mContext != null) {
                    Toast.makeText(mContext, "Something went wrong..", Toast.LENGTH_SHORT).show();
                }
            }

                //Edited by Janvi 1st Nov
            else if (strURL.contains(Constant.GETPAGES_STATUS)) {
                try {
                    JSONArray footerArr = new JSONArray(response);
                    Constant.Otherlist = new ArrayList<MbsDataModel>();

                    for (int i = 0; i < footerArr.length(); i++) {
                        MbsDataModel mbsDataModel = new MbsDataModel(footerArr.getJSONObject(i));

                        if (mbsDataModel.status) {
                            Constant.Otherlist.add(mbsDataModel);
                        }
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                    if (mContext != null) {
                        Toast.makeText(mContext, "Something went wrong..", Toast.LENGTH_SHORT).show();
                    }
                }
            }

            //end***********

            else if (strURL.contains(Constant.GETPAGES_DETAIL_BLOG)) {
                try {
                    JSONArray footerArr = new JSONArray(response);
                    MbsDataModel mbs2 = new MbsDataModel();
                    mbs2.ID = "0";
                    mbs2.PageName = "Departments";
                    mbs2.PageTitle = "Departments";
                    mbs2.PageContent = "Departments";
                    mbs2.status = true;
                    mbs2.position = Constant.TopHeaderMenuList.size() + 1;
                    Constant.TopHeaderMenuList.add(mbs2);
                    if (footerArr.length() > 0) {
                        JSONObject dataObject = footerArr.getJSONObject(0);
                        Constant.ISDELIVERY_HOUR_DISP = dataObject.optBoolean("Setup_HandDelivery");
                        Constant.ISDISPLAY_STOREHOUR = dataObject.optBoolean("DisplayStoreHours");
//                        Constant.ISSHOW_EVENTCAL = dataObject.optBoolean("DisplayEventCalender");
                        Constant.ISSHOW_EVENTCAL = dataObject.optBoolean("ShowEventCalender");
                        Constant.storeStatus = dataObject.optBoolean("Status");
                        Iterator<?> keys = dataObject.keys();
                        while (keys.hasNext()) {
                            String key = (String) keys.next();
                            MbsDataModel mbs3 = new MbsDataModel();

                            if (key.equalsIgnoreCase("ShowEventCalender")) {
                                mbs3.ID = "1";
                                mbs3.PageName = "Event Calendar";
                                mbs3.PageTitle = "ShowEventCalender";
                                mbs3.PageContent = "ShowEventCalender";
                                mbs3.status = dataObject.getBoolean("ShowEventCalender");
                                mbs3.position = Constant.TopHeaderMenuList.size() + 1;
                                if (Constant.ISSHOW_EVENTCAL) {
                                    Constant.TopHeaderMenuList.add(mbs3);
                                }
                            } else if (Constant.storeStatus) {
                                if (key.equalsIgnoreCase("DisplayStoreHours")) {
                                    mbs3.ID = "2";
                                    if (Constant.ISDELIVERY_HOUR_DISP) {
                                        mbs3.PageName = "Store/Delivery Hours";
                                    } else {
                                        mbs3.PageName = "Store Hours";
                                    }
                                    mbs3.PageTitle = "DisplayStoreHours";
                                    mbs3.PageContent = "DisplayStoreHours";
                                    mbs3.status = dataObject.getBoolean("Status");
                                    mbs3.position = Constant.TopHeaderMenuList.size() + 1;
//
                                    Constant.TopHeaderMenuList.add(mbs3);
                                    Constant.storeDeliveryHourTagline = dataObject.optString("Tagline");
                                    Constant.Store_Delivery_Logo = dataObject.optString("logo");
//
                                }
                            }

                        }
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }
//                String Url = Constant.WS_BASE_URL + Constant.GETPAGES + Constant.STOREID;


                if (Constant.SCREEN_LAYOUT == 2) {
                    MainActivityDup.getInstance().onSetDrawerMenu();
                }
            }
            else if (strURL.contains(Constant.GET_STYLE_DEPARTMENT_LIST)) {
                try {

                    JSONArray subDepartmentArr = new JSONArray(response);
                    Constant.SubDepartmentList = new ArrayList<>();
                    for (int i = 0; i < subDepartmentArr.length(); i++) {
                        JSONObject SubdeptObj = subDepartmentArr.getJSONObject(i);
                        SubDepartmentModel model = new SubDepartmentModel(SubdeptObj);
                        Constant.SubDepartmentList.add(model);
                    }

                    FilterFragment.getInstance().callDepartmentWS(true);

//                        String Url1 = Constant.WS_BASE_URL + Constant.GETDEPARTMENT + Constant.STOREID;
//                        new Async_Dept(Url1,mContext, true).execute();

                } catch (JSONException e) {
                    e.printStackTrace();
                    if (mContext != null) {
                        Toast.makeText(mContext, "Something went wrong..", Toast.LENGTH_SHORT).show();
                    }
                }

            }
            else if (strURL.contains(Constant.CHECK_PASSWORD)) {

                try {
                    JSONObject userObj = new JSONObject(response);
                    UserModel UM = new UserModel(userObj);

                    if (isComefromChangePwd) {
                        if (Constant.SCREEN_LAYOUT == 1) {
                            ChnagePasswordFragment.getInstance().checkPasswordWSResult(UM, strURL);
                        } else if (Constant.SCREEN_LAYOUT == 2) {
                            ChnagePasswordFragment.getInstance().checkPasswordWSResult(UM, strURL);
                        }
                        isComefromChangePwd = false;
                    } else {

                        Log.e("Error Message", "onPostExecute: "+UM.Result.toString());

                        if (UM.Result.equalsIgnoreCase("success")) {

                            if (Constant.SCREEN_LAYOUT == 1) {
                                MainActivity.getInstance().callNotificationDialogWs();
                            } else if (Constant.SCREEN_LAYOUT == 2) {
                                MainActivityDup.getInstance().callNotificationDialogWs();
                            }

                            String sp[] = strURL.split("/");
                            Log.e("Log", "Password=" + sp[sp.length - 2]);
                            Log.e("Log", "User ID : " + UM.Cust_mst_ID);
                            //The CHECK_PASSWORD service call in OnResume if user status not null... So here i am just avoid success dialog in every resume call
                            //This will only comes when user successfully login or change password
//manage account
                            if (Constant.AppPref.getString("email", "").isEmpty()) {

                                if (Constant.SCREEN_LAYOUT == 1) {
                                    MainActivity.getInstance().isComeFromSigninManageAccount = true;
                                    MainActivity.getInstance().getCustomerData(UM);
                                } else if (Constant.SCREEN_LAYOUT == 2) {
                                    MainActivityDup.getInstance().isComeFromSigninManageAccount = true;
                                    MainActivityDup.getInstance().getCustomerData(UM);
                                }
                            }

                            if (Constant.SCREEN_LAYOUT == 1) {
                                if (Constant.isCart) {
                                    Constant.isCart = false;
                                    if (CardFragment.getInstance() != null) {
                                        CardFragment.getInstance().shoppinCartDetais();
                                    }
                                } else {
                                    MainActivity.getInstance().CallHomeFragment();
                                    MainActivity.moveSessionToCart();
////                                    ***************** Edited by Varun for wishlist on 29 july 2022  *****************
////                                    not to refresh when login
//                                    MainActivity.moveSessionToCart();
//                                    if (HomepageFragment.getInstance()==null){
//                                        MainActivity.getInstance().loadHomeWebPage();
//                                    }
////                                    MainActivity.getInstance().loadHomeWebPage();
////                                    MainActivity.moveSessionToCart();
////                                   ****************** END ***************************

                                }

                            } else if (Constant.SCREEN_LAYOUT == 2) {

                                if (Constant.isCart) {
                                    Constant.isCart = false;
                                    if (CardFragment.getInstance() != null) {
                                        CardFragment.getInstance().shoppinCartDetais();
                                    }
                                } else {
                                    if (Constant.isNativePage) {
                                        if (HomepageFragment.getInstance() == null) {
                                            MainActivityDup.getInstance().loadHomeWebPage();
                                        }
                                    } else {
                                        MainActivityDup.getInstance().loadHomeWebPage();

                                    }
                                    MainActivityDup.moveSessionToCart();
                                }
                            }
                            Constant.AppPref.edit().putString("email", UM.Email)
                                    .putString("password", sp[sp.length - 2]).apply();
                            if (Constant.SCREEN_LAYOUT == 1) {
                                //MainActivity.getInstance().loadHomeWebPage();
//                                MainActivity.onGetCartData(/*UM*/"");
                            } else if (Constant.SCREEN_LAYOUT == 2) {
                                //MainActivityDup.getInstance().loadHomeWebPage();
                                MainActivityDup.onGetCartData(/*UM*/);

                            }
                            Login.setUserDetail(UM);
                            Constant.LHSLIDER_LIST.clear();
                            Constant.AccountList.clear();
                            Constant.AccountList2.clear();

//                            Edited by Varun for guest login

                            Constant.ISguest = Constant.AppPref.getBoolean("ISguest",false);
                            Log.e("", "onPostExecute:2 "+Constant.ISguest );
                            Constant.AccountList2.clear();
                            if (Constant.ISguest){
                                if (Constant.SCREEN_LAYOUT==1) {
                                    MainActivity.getInstance().tvWishList.setVisibility(View.GONE);
                                }
                                Utils.getAccountList2();
                                Constant.LHSLIDER_LIST.put("My Account", Constant.AccountList2);
                                Log.e("", "onPostExecute: 11"+Constant.AccountList );
                            }else {
//                                END
                                    Utils.getAccountList();
                                    Constant.LHSLIDER_LIST.put("My Account", Constant.AccountList);
                            }

                            if (Constant.SCREEN_LAYOUT == 1) {
//                                MainActivity.getInstance().txtNotification.setVisibility(View.VISIBLE);
                                MainActivity.getInstance().mManage_expList.setVisibility(View.VISIBLE);
                                ArrayList<String> TitleList = new ArrayList<String>(Constant.LHSLIDER_LIST.keySet());
                                MainActivity.getInstance().mManage_expList.setAdapter(new ExpandAdapter(mContext, Constant.LHSLIDER_LIST, TitleList));
                            } else if (Constant.SCREEN_LAYOUT == 2) {
                                if (ProfileFragment_layout2.getInstance() != null) {
                                    ProfileFragment_layout2.getInstance().LoadAccountList();
                                }
                            }
                        } else {

                            if (comefrom.equals("comefromLogin")) {
                                Login.onLogOff();
                                comefrom = "";
                            } else {
                                Utils.showValidationDialog(mContext, UM.Result, "splash");
                            }
                        }

                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
            else if (strURL.contains(Constant.GENERATE_OTP)) {
                JSONObject OtpObj = null;
                try {
                    OtpObj = new JSONObject(response);
                    Log.e("response", "onPostExecute: " + response);
                    UserModel UM = new UserModel(OtpObj);
                    Constant.otpModel = new OtpModel(OtpObj);
                    Constant.UserModeltest = UM;

                    if (Constant.otpModel.Isguest) {
//                        Login.showGuestDialogAndCloseLogin(mContext);

//                        Edited by Varun for guest login
                        if (Constant.ISguest_Signin){
                            Constant.ISguest_Signin = false;
                            if (Constant.otpModel.Result.equalsIgnoreCase("success")){
                                Login.setOTPDesign();
                                Constant.LOGIN_TYPE = "accnotactive";
                                Constant.Guest_WD = "guest";
                            }
                        }else if (Constant.ISguest_Continue){
                            Constant.ISguest_Continue= false;
                            String deviceid = DeviceInfo.getDeviceId(mContext);
                            if (!Constant.otpModel.Cust_mst_ID .isEmpty() && !deviceid.isEmpty() ){

                                if (Constant.SCREEN_LAYOUT==1) {
                                    MainActivity.moveSessionToCart();
                                    if (HomepageFragment.getInstance()==null){
                                        MainActivity.getInstance().loadHomeWebPage();
                                    }
                                }else{
                                    MainActivityDup.moveSessionToCart();
                                    if (HomepageFragment.getInstance()==null){
                                        MainActivityDup.getInstance().loadHomeWebPage();
                                    }
                                }

                            }
                            Login.Test(UM);
//                            Login.setUserDetail(UM);
//                            Login.onLoginSuccess(UM);

                        }else{
                            DialogUtils.guest_tell_me_more("login");
                        }
//                        END
                    }
                    else if (Constant.otpModel.Result.equalsIgnoreCase("success") || Constant.otpModel.Result.equalsIgnoreCase("Account but not activated")) {
                        if (Constant.otpModel.Result.equalsIgnoreCase("Account but not activated")) {
                            Constant.LOGIN_TYPE = "accnotactive";

                            String Url = Constant.WS_BASE_URL + Constant.GENERATE_OTP + Login.strGlobalEmail + "/" + "Signin" + "/" + Constant.STOREID;
                            if (Constant.SCREEN_LAYOUT == 1) {
                                new Async_getCommonService(MainActivity.getInstance(), Url).execute();
                            } else if (Constant.SCREEN_LAYOUT == 2) {
                                new Async_getCommonService(MainActivityDup.getInstance(), Url).execute();
                            }

                        } else if (Constant.otpModel.Result.equalsIgnoreCase("success")) {
                            if (Login.isResentOTP) {
//                                Login.otpResend();
                                DialogUtils.showDialog("OTP Resent!");
                                Login.isResentOTP = false;
                            }
                            Login.strOTP = "login";
                            Login.setOTPDesign();
                        } else {
                            Login.startSignupDialog();
                        }
                    }
                    else if (Constant.otpModel.Result.equalsIgnoreCase("New Added")) {
                        Login.startSignupDialog();
                    }
                    else if (strURL.contains("SignIn/")) {
                        if (Constant.otpModel.Result.contains("you are not registered with us")) {
                            Login.startSignupDialog();
                        } else {
                            Login.CheckPassword();
                        }
                    } else {
                        Utils.showValidationDialog(mContext, Constant.otpModel.Result, "");
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
            else if (strURL.contains(Constant.CHANGE_PASSWORD)) {
                JSONObject userObj = null;
                try {
                    userObj = new JSONObject(response);
                    Log.e("response", "onPostExecute: " + response);
                    UserModel UM = new UserModel(userObj);

                    if (isComefromChangePwd) {
                        if (Constant.SCREEN_LAYOUT == 1) {
                            ChnagePasswordFragment.getInstance().InsertNewPwdResult(UM,strURL);
                        } else if (Constant.SCREEN_LAYOUT == 2) {
                            ChnagePasswordFragment.getInstance().InsertNewPwdResult(UM,strURL);
                        }
                    } else {
                            if (!UM.Result.isEmpty()) {
                                if (UM.Result.equalsIgnoreCase("success")) {
                                    byte[] data = Base64.decode(UM.Password, Base64.DEFAULT);
                                    try {
                                        String text = new String(data, "UTF-8");
                                        UM.Password = text;
                                    } catch (UnsupportedEncodingException e) {
                                        e.printStackTrace();
                                    }

                                    Log.e("Log", "UM Password=" + UM.Password);
                                    Constant.AppPref.edit().putString("email", UM.Email)
                                            .putString("password", UM.Password)
                                            .putBoolean("ISguest",false).commit();
                                    Login.setUserDetail(UM);
                                    Login.onLoginSuccess(UM);
                                    Constant.LHSLIDER_LIST.clear();
                                    Constant.AccountList.clear();
                                    Constant.AccountList2.clear();
                                    Utils.getAccountList();
                                    Constant.LHSLIDER_LIST.put("My Account", Constant.AccountList);
                                    Log.e("ghdfg", "onPostExecute: "+Constant.AccountList );
                                    if (Constant.SCREEN_LAYOUT == 1) {
                                        MainActivity.getInstance().mManage_expList.setVisibility(View.VISIBLE);
                                        ArrayList<String> TitleList = new ArrayList<String>(Constant.LHSLIDER_LIST.keySet());
                                        MainActivity.getInstance().mManage_expList.setAdapter(new ExpandAdapter(mContext, Constant.LHSLIDER_LIST, TitleList));
                                    } else if (Constant.SCREEN_LAYOUT == 2) {
//                                        Edited by Varun for guest login
                                        MainActivityDup.getInstance().onGetCartData();
                                        if (ProfileFragment_layout2.getInstance() != null) {
                                            ProfileFragment_layout2.getInstance().LoadAccountList();
                                        }
//                                        END
//                                       // MainActivityDup.getInstance().mManage_expList.setVisibility(View.VISIBLE);
                                    }

                                } else {
                                    Utils.showValidationDialog(mContext, UM.Result, "");
                                }
                            }

                        }

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
            else if (strURL.contains(Constant.CREAT_USER)) {
                JSONObject userObj = null;
                try {
                    userObj = new JSONObject(response);
                    Log.e("response", "onPostExecute: " + response);
                    UserModel UM = new UserModel(userObj);
                    Constant.otpModel = new OtpModel(userObj);
                    byte[] data = Base64.decode(UM.Password, Base64.DEFAULT);
                    try {
                        String text = new String(data, "UTF-8");
                        UM.Password = text;
                    } catch (UnsupportedEncodingException e) {
                        e.printStackTrace();
                    }
                    Log.e("Log", "UM Password=" + UM.Password);
                    Log.d("Log", "User master ID : " + UM.Cust_mst_ID);
                    Constant.Email = UM.Email;
                    Constant.Password = UM.Password;
                    Constant.Guest_storeno = Constant.STOREID ;
                    Constant.Guest_customer_id = UM. Cust_mst_ID;

//                    Edited by Varun for guest login

                    if (UM.Isguest){
                        Login.createUserafterOTP(UM);
                    }else {
                        Login.createUserafterOTP(UM);
                    }
//                    END

//                    Login.createUserafterOTP(UM);

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
            else if (strURL.contains(Constant.CHECK_OTP)) {
                JSONObject userObj = null;
                try {
                    userObj = new JSONObject(response);
                    Log.e("response", "onPostExecute: " + response);
                    UserModel UM = new UserModel(userObj);
                    Constant.otpModel = new OtpModel(userObj);

                    if (!Constant.otpModel.Result.isEmpty()) {
                        if (Constant.otpModel.Result.equalsIgnoreCase("Email and One Time Password does not match")) {
                            Login.sign_txtotperror.setVisibility(View.VISIBLE);
                            Login.sign_txt_forget_instruction.setVisibility(View.GONE);
                            Login.sign_llmiddle.setVisibility(View.GONE);
                            if (Constant.SCREEN_LAYOUT == 1) {
                                Login.sign_btnPrev.setVisibility(View.GONE);
                                Login.sign_btnContinue.setText(MainActivity.getInstance().getString(R.string.str_OK));
                                Login.sign_txtloginhead.setText(MainActivity.getInstance().getString(R.string.str_oops));
                            } else if (Constant.SCREEN_LAYOUT == 2) {
                                Login.sign_btnPrev.setVisibility(View.GONE);
                                Login.sign_btnContinue.setText(MainActivityDup.getInstance().getString(R.string.str_OK));
                                Login.sign_txtloginhead.setText(MainActivityDup.getInstance().getString(R.string.str_oops));
                            }
                            Login.sign_txt_signin.setVisibility(View.INVISIBLE);
                            Login.sign_txtotperror.setText(Constant.otpModel.Result);
                        }
                        else if (Constant.otpModel.Result.equalsIgnoreCase("successnewuser")) {

                            Constant.AppPref.edit().putString("email", Constant.Email)
                                    .putString("password", Constant.Password).commit();
                            Login.setUserDetail(UM);
                            Login.onLoginSuccess(UM);
                            Constant.LHSLIDER_LIST.clear();
                            Constant.AccountList.clear();
                            Constant.AccountList2.clear();
                            Utils.getAccountList();
                            Constant.LHSLIDER_LIST.put("My Account", Constant.AccountList);
                            if (Constant.SCREEN_LAYOUT == 1) {
                                MainActivity.getInstance().mManage_expList.setVisibility(View.VISIBLE);
                                ArrayList<String> TitleList = new ArrayList<String>(Constant.LHSLIDER_LIST.keySet());
                                MainActivity.getInstance().mManage_expList.setAdapter(new ExpandAdapter(mContext, Constant.LHSLIDER_LIST, TitleList));

                            } else if (Constant.SCREEN_LAYOUT == 2) {
                                /*MainActivityDup.getInstance().mManage_expList.setVisibility(View.VISIBLE);
                                ArrayList<String> TitleList = new ArrayList<String>(Constant.LHSLIDER_LIST.keySet());
                                MainActivityDup.getInstance().mManage_expList.setAdapter(new ExpandAdapter(mContext, Constant.LHSLIDER_LIST, TitleList));
*/
                            }
                        }
                        else {
                            Login.sign_txtotperror.setVisibility(View.GONE);
                            Login.sign_llmiddle.setVisibility(View.VISIBLE);
                            Login.txtPasswordHint.setVisibility(View.VISIBLE);

                            Login.sign_edtEmailID.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                            Login.sign_edtEmailID.setTransformationMethod(PasswordTransformationMethod.getInstance());
                            if (Constant.SCREEN_LAYOUT == 1) {
                                Login.sign_input_layout_empid_for.setHint(MainActivity.getInstance().getString(R.string.str_hint_password));
                            } else if (Constant.SCREEN_LAYOUT == 2) {
                                Login.sign_input_layout_empid_for.setHint(MainActivityDup.getInstance().getString(R.string.str_hint_password));

                            }

                            Login.sign_edtEmailID.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_open_eye, 0);
                            isHiddenPassword1 = true;
                            Login.sign_edtEmailID.setCompoundDrawablePadding(25);

                            Login.sign_edtEmailID.setOnTouchListener(new View.OnTouchListener() {
                                @Override
                                public boolean onTouch(View v, MotionEvent event) {
                                    final int DRAWABLE_LEFT = 0;
                                    final int DRAWABLE_TOP = 1;
                                    final int DRAWABLE_RIGHT = 2;
                                    final int DRAWABLE_BOTTOM = 3;

                                    if (Login.sign_edtEmailID.getCompoundDrawables()[DRAWABLE_RIGHT].getBounds() != null) {

                                        if (event.getAction() == MotionEvent.ACTION_UP) {
                                            if (event.getRawX() >= (Login.sign_edtEmailID.getRight() - Login.sign_edtEmailID.getCompoundDrawables()[DRAWABLE_RIGHT].getBounds().width())) {
                                                // your action here

                                                if (isHiddenPassword1) {
                                                    Login.sign_edtEmailID.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_close_eye, 0);
                                                    Login.sign_edtEmailID.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                                                    isHiddenPassword1 = false;

                                                } else if (!isHiddenPassword1) {
                                                    Login.sign_edtEmailID.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_open_eye, 0);
                                                    Login.sign_edtEmailID.setTransformationMethod(PasswordTransformationMethod.getInstance());
                                                    isHiddenPassword1 = true;
                                                }
                                                return true;
                                            }
                                        }

                                    }

                                    return false;
                                }
                            });


                            Login.sign_edtEmailID.setText("");
                            Login.sign_confirm_password.setText("");

                            Login.sign_confirm_password.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_open_eye, 0);
                            isHiddenPassword2 = true;
                            Login.sign_confirm_password.setCompoundDrawablePadding(25);

                            Login.sign_confirm_password.setOnTouchListener(new View.OnTouchListener() {
                                @Override
                                public boolean onTouch(View v, MotionEvent event) {
                                    final int DRAWABLE_LEFT = 0;
                                    final int DRAWABLE_TOP = 1;
                                    final int DRAWABLE_RIGHT = 2;
                                    final int DRAWABLE_BOTTOM = 3;

                                    if (Login.sign_confirm_password.getCompoundDrawables()[DRAWABLE_RIGHT].getBounds() != null) {

                                        if (event.getAction() == MotionEvent.ACTION_UP) {
                                            if (event.getRawX() >= (Login.sign_confirm_password.getRight() - Login.sign_confirm_password.getCompoundDrawables()[DRAWABLE_RIGHT].getBounds().width())) {
                                                // your action here

                                                if (isHiddenPassword2) {
                                                    Login.sign_confirm_password.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_close_eye, 0);
                                                    Login.sign_confirm_password.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                                                    isHiddenPassword2 = false;

                                                } else if (!isHiddenPassword2) {
                                                    Login.sign_confirm_password.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_open_eye, 0);
                                                    Login.sign_confirm_password.setTransformationMethod(PasswordTransformationMethod.getInstance());
                                                    isHiddenPassword2 = true;
                                                }
                                                return true;
                                            }
                                        }

                                    }

                                    return false;
                                }
                            });

                            Login.sign_confirm_password.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                            Login.sign_confirm_password.setTransformationMethod(PasswordTransformationMethod.getInstance());
                            if (Constant.SCREEN_LAYOUT == 1) {
                                Login.sign_confirm_input_layout_password.setHint(MainActivity.getInstance().getString(R.string.str_hint_confirm_password));
                                Login.sign_confirm_password.setVisibility(View.VISIBLE);
                                Login.sign_confirm_input_layout_password.setVisibility(View.VISIBLE);
                                Login.sign_btnPrev.setVisibility(View.VISIBLE);
                                Login.sign_txtloginhead.setText(MainActivity.getInstance().getString(R.string.str_hint_password));
                                Login.sign_txt_forget_instruction.setText(MainActivity.getInstance().getString(R.string.str_seturpassword));
                                Login.sign_txt_forget_instruction.setVisibility(View.VISIBLE);
                                Login.sign_btnPrev.setVisibility(View.VISIBLE);
                                Login.sign_btnPrev.setText(MainActivity.getInstance().getString(R.string.str_prev));
                                Login.sign_txt_signin.setVisibility(View.GONE);
                                Login.sign_btnContinue.setText(MainActivity.getInstance().getString(R.string.str_cap_signin));

                            } else if (Constant.SCREEN_LAYOUT == 2) {
                                Login.sign_confirm_input_layout_password.setHint(MainActivityDup.getInstance().getString(R.string.str_hint_confirm_password));
                                Login.sign_confirm_password.setVisibility(View.VISIBLE);
                                Login.sign_confirm_input_layout_password.setVisibility(View.VISIBLE);
                                Login.sign_btnPrev.setVisibility(View.VISIBLE);
                                Login.sign_txtloginhead.setText(MainActivityDup.getInstance().getString(R.string.str_hint_password));
                                Login.sign_txt_forget_instruction.setText(MainActivityDup.getInstance().getString(R.string.str_seturpassword));
                                Login.sign_txt_forget_instruction.setVisibility(View.VISIBLE);
                                Login.sign_btnPrev.setVisibility(View.VISIBLE);
                                Login.sign_btnPrev.setText(MainActivityDup.getInstance().getString(R.string.str_prev));
                                Login.sign_txt_signin.setVisibility(View.GONE);
                                Login.sign_btnContinue.setText(MainActivityDup.getInstance().getString(R.string.str_cap_signin));
                            }

                        }
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
            else if (strURL.contains(Constant.INPUT)) {
                Log.e("Log", "Places Response=" + response);
                try {
                    JSONObject placeObj = new JSONObject(response);
                    JSONArray PlaceArray = placeObj.getJSONArray("predictions");
                    Constant.PlaceArr = new PlaceModel[PlaceArray.length()];
                    String Desc[] = new String[PlaceArray.length()];
                    final String Placeid[] = new String[PlaceArray.length()];
                    for (int i = 0; i < PlaceArray.length(); i++) {
                        JSONObject plcObj = PlaceArray.getJSONObject(i);
                        PlaceModel pm = new PlaceModel();
                        pm.description = plcObj.getString("description");
                        pm.place_id = plcObj.getString("place_id");
                        Constant.PlaceArr[i] = pm;
                        Desc[i] = pm.description;
                        Placeid[i] = pm.place_id;
                    }
                    Login.autotxtAddress1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                            Login.isdhowdropdown = false;
                            new Async_getCommonService(mContext, Constant.MAP_API_URL1 + Constant.PLACE_ID + Placeid[i] + "&key=" + mContext.getString(R.string.Place_API_key)).execute();
                        }
                    });
                    Log.e("Log", "Desc size=" + Desc.length);
                    ArrayAdapter<String> adapter = new ArrayAdapter<String>(mContext,
                            android.R.layout.simple_dropdown_item_1line, Desc);
                    Login.autotxtAddress1.setAdapter(adapter);
                    Login.autotxtAddress1.setThreshold(1);
                    Login.autotxtAddress1.showDropDown();

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
            else if (strURL.contains(Constant.PLACE_ID)) {
                Log.e("Log", "STRRURL=" + strURL);
                Log.e("Log", "Response for PlacesID=" + response);
                JSONObject placeObj = null;
                try {
                    placeObj = new JSONObject(response);
                    if (placeObj.optString("status").equalsIgnoreCase("OK")) {
                        JSONObject resultarr = placeObj.getJSONObject("result");
                        JSONArray address = resultarr.getJSONArray("address_components");
                        Login.setAddress(address);
                    } else {
                        Utils.showValidationDialog(mContext, placeObj.optString("error_message"), "");
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }


            }
            else if (strURL.contains(Constant.GET_ZIP_CODE)) {
                JSONObject userObj = null;
                try {
                    userObj = new JSONObject(response);
                    PinModel pinModel = new PinModel(userObj);
                    Login.onloadAddress(pinModel);

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
//            else if (strURL.contains(Constant.GET_CUSTOMER_CARD_DATA)) {
            else if (strURL.contains(Constant.GET_CUSTOMER_CARD_DATA_V1)) {

                try {
                    JSONArray cardArray = new JSONArray(response);
                    Constant.cardArray = new ArrayList<>();
                    for (int i = 0; i < cardArray.length(); i++) {
                        JSONObject deptObj = cardArray.getJSONObject(i);
                        CardModel cardModel = new CardModel(deptObj);
                        Constant.cardArray.add(cardModel);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                    Toast.makeText(mContext, "Something went wrong..", Toast.LENGTH_SHORT).show();
                }

            }
        }
    }

    boolean contains(List<MbsDataModel> TopHeaderMenuList, String name) {
        for (MbsDataModel item : TopHeaderMenuList) {
            if (item.PageName.equals(name)) {
                return true;
            }
        }
        return false;
    }

    @Override
    protected void onPreExecute() {
        // pre execute logic
        super.onPreExecute();
        progressBar = new ProgressDialog(mContext);
        progressBar.setCancelable(true);
        progressBar.setMessage("Please wait...");
        // start progressbar
    }

    @Override
    public void onGetBlockDataFrontResult(List<DataFrontModel> BlockDataFrontList) {

    }

    @Override
    public void onGetHomePageBlockDataResult(List<DataHomePageBlockModel> BlockDataFrontList) {

    }
}