package com.aspl.mbs;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.ActivityNotFoundException;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.net.wifi.WifiManager;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.text.format.Formatter;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.aspl.Utils.Constant;
import com.aspl.Utils.DialogUtils;
import com.aspl.Utils.Utils;
import com.aspl.mbsmodel.ContatInfo;
import com.aspl.mbsmodel.StoreLocationModel;
import com.aspl.mbsmodel.TwentyOneYear;
import com.aspl.task.TaskContactInfo;
import com.aspl.task.TaskFCMTokenRegister;
import com.aspl.task.TaskStoreLocationInfo;
import com.aspl.task.TaskTwentyOneYear;
import com.aspl.ws.Async_getCommonService;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.play.core.appupdate.AppUpdateInfo;
import com.google.android.play.core.appupdate.AppUpdateManager;
import com.google.android.play.core.appupdate.AppUpdateManagerFactory;
import com.google.android.play.core.install.model.UpdateAvailability;
import com.google.android.play.core.tasks.Task;
import com.google.firebase.crash.FirebaseCrash;
import com.intuit.sdp.BuildConfig;


import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.net.ServerSocket;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import static com.aspl.Utils.Constant.GET_CONTACT_INFO;
import static com.aspl.Utils.Constant.GET_CORPORATE_STORE_SUBSTORELIST;
import static com.aspl.Utils.Constant.GET_CORPORATE_STORE_SUBSTORELIST_V1;
import static com.aspl.Utils.Constant.STOREID;
import static com.aspl.Utils.Constant.WS_BASE_URL;
import static com.aspl.Utils.Constant.contatInfo;
import static com.aspl.Utils.DialogUtils.progressBar;

/**
 * Created by new on 07/11/2017.
 */
public class SplaceScreen extends AppCompatActivity implements TaskFCMTokenRegister.TaskFCMTokenRegistrationListener,
       TaskContactInfo.TaskContactInfoEvent, TaskStoreLocationInfo.TaskStoreLocationEvent,OnMapReadyCallback {
    Handler handler = new Handler();
    //    LinearLayout ll_splace;
    FrameLayout fl_splace;
    public static SplaceScreen splaceScreen;
    ImageView splaceImage;
    String URL;
    String CustomerId;
    TextView tvDate;
    /////////////////////
    public static View vPaymentProcessDialog;
    public static Dialog paymentProcess;
    int sCurrentVersion = 0;
    int sLatestVersion = 0;
    /////////////////////
    String latestVersionName;

    public static SplaceScreen getInstance() {
        return splaceScreen;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        splaceScreen = this;
        setContentView(R.layout.splace);

        Intent intent = getIntent();
        Uri data = intent.getData();
        String uri = this.getIntent().getDataString();
        Log.i("MyApp", "Deep link clicked " + uri);

        FirebaseCrash.setCrashCollectionEnabled(false);

        WifiManager wm = (WifiManager) getApplicationContext().getSystemService(WIFI_SERVICE);
        assert wm != null;
        String ip = Formatter.formatIpAddress(wm.getConnectionInfo().getIpAddress());
        Log.e("ip","ip" + ip);
        ServerSocket s = null;
        try {
            s = new ServerSocket(0);
        } catch (IOException e) {
            e.printStackTrace();
        }
        assert s != null;
        System.out.println("listening on port: " + s.getLocalPort());

        System.out.println("ipv4: " + DialogUtils.getIpv4());

////////////////////////////////////

//            if(Constant.SCREEN_LAYOUT==1){
//                paymentProcess = new Dialog(MainActivity.getInstance()/*, R.style.DialogSlideAnim_login*/);
//                paymentProcess.setCanceledOnTouchOutside(false);
//                vPaymentProcessDialog = LayoutInflater.from(MainActivity.getInstance()).inflate(R.layout.dialog_payment_process_new, null);
//
//            }else if(Constant.SCREEN_LAYOUT==2) {
//                paymentProcess = new Dialog(MainActivityDup.getInstance()/*, R.style.DialogSlideAnim_login*/);
//                paymentProcess.setCanceledOnTouchOutside(false);
//                vPaymentProcessDialog = LayoutInflater.from(MainActivityDup.getInstance()).inflate(R.layout.dialog_payment_process_new, null);
//
//            }
//
//            WindowManager.LayoutParams params = paymentProcess.getWindow().getAttributes();
//            params.width = WindowManager.LayoutParams.WRAP_CONTENT;
//            params.height = WindowManager.LayoutParams.WRAP_CONTENT;
//
//            paymentProcess.setContentView(vPaymentProcessDialog);
//            paymentProcess.getWindow().setGravity(Gravity.CENTER);
//            WindowManager.LayoutParams layoutParam = paymentProcess.getWindow().getAttributes();
//            paymentProcess.getWindow().setAttributes(layoutParam);
//            paymentProcess.show();



/////////////////////////////////////////

        //Constant.STOREID

        /*
         *
         *
         * Code for Flavour store assign
         *
         *
         *
         *
         * */

        if (StoreConstant.StoreNo.STORE707 == StoreConstant.storeNo){
            Constant.STOREID="707";
        }else if (StoreConstant.StoreNo.STORE1000 == StoreConstant.storeNo){
            Constant.STOREID="1000";
        }else if (StoreConstant.StoreNo.STORE315 == StoreConstant.storeNo){
            Constant.STOREID="315";
        }else if (StoreConstant.StoreNo.STORE327 == StoreConstant.storeNo){
            Constant.STOREID="327";
        }else if (StoreConstant.StoreNo.STORE57 == StoreConstant.storeNo) {
            Constant.STOREID = "57";
        }else if (StoreConstant.StoreNo.STORE44 == StoreConstant.storeNo) {
            Constant.STOREID = "44";
        }else if (StoreConstant.StoreNo.STORE99 == StoreConstant.storeNo) {
            Constant.STOREID = "99";
        }else if (StoreConstant.StoreNo.STORE7365 == StoreConstant.storeNo) {
            Constant.STOREID = "7365";
        }else if (StoreConstant.StoreNo.STORE105 == StoreConstant.storeNo) {
            Constant.STOREID = "105";
        }else if (StoreConstant.StoreNo.STORE90 == StoreConstant.storeNo) {
            Constant.STOREID = "90";
        }else if (StoreConstant.StoreNo.STORE3824 == StoreConstant.storeNo) {
            Constant.STOREID = "3824";
        }else if (StoreConstant.StoreNo.STORE96 == StoreConstant.storeNo) {
            Constant.STOREID = "96";
        }else if (StoreConstant.StoreNo.STORE540 == StoreConstant.storeNo) {
            Constant.STOREID = "540";
        }else if (StoreConstant.StoreNo.STORE1802 == StoreConstant.storeNo) {
            Constant.STOREID = "1802";
        }else if (StoreConstant.StoreNo.STORE2105 == StoreConstant.storeNo) {
            Constant.STOREID = "2105";
        }else if (StoreConstant.StoreNo.STORE224 == StoreConstant.storeNo) {
            Constant.STOREID = "224";
        }else if (StoreConstant.StoreNo.STORE4450 == StoreConstant.storeNo) {
            Constant.STOREID = "4450";
        }else if (StoreConstant.StoreNo.STORE4473 == StoreConstant.storeNo) {
            Constant.STOREID = "4473";
        }else if (StoreConstant.StoreNo.STORE2401 == StoreConstant.storeNo) {
            Constant.STOREID = "2401";
        }

//        Edited by Varun for to check the playstore version code and mobile phone version code if less then playstore version code then pop up of update

        Call_Update_App_Verification();

//        END
        //for main store id
        Constant.MainSTOREID = Constant.STOREID;

        Constant.URL_PAGE_STORE_HOURS_DELIVERY_HOURS = Constant.URL + "pages/storehours?storeno=" + Constant.STOREID + "&type=delivery";
        Log.e("Log","STOREID="+Constant.STOREID);
        Log.e("Log", "Constant URL_PAGE_STORE_HOURS_DELIVERY_HOURS=" + Constant.URL_PAGE_STORE_HOURS_DELIVERY_HOURS);
        if (getIntent().getExtras() != null) {
            Constant.liCardModel.clear();
            URL = getIntent().getExtras().getString("URL");
            CustomerId = getIntent().getExtras().getString("CustomerId");
            //if(MainActivity.get)
        }
        Calendar calendar = Calendar.getInstance();
        Log.e("Log", "--**OrderID--" + URL);
        Log.e("Log", "--**CustomerId--" + CustomerId);
        Log.e("Log", "Timezone=" + calendar.getTimeZone().getDisplayName());
        //onFCMTokenRegistration();
        //FCMServerRegistration.onFCMTokenRegistration(this,"");

        /*runOnUiThread(new Runnable() {
            @Override
            public void run() {
                try {
                    Log.e("fcm","fcm registration key : "+ FirebaseInstanceId.getInstance().getToken("357626466339", "FCM"));
                } catch (IOException e) {
                    Log.d("fcm", "fcm exception : "+ e.getMessage());
                    e.printStackTrace();
                }
            }
        });*/


        Constant.AppPref = getSharedPreferences(Constant.PrefName, MODE_PRIVATE);

        //Edited by Janvi 19th Oct
//        ll_splace = (LinearLayout) findViewById(R.id.ll_splace);
        fl_splace = (FrameLayout)findViewById(R.id.fl_splace);
        //end *****
        splaceImage = (ImageView) findViewById(R.id.splaceImage);

        //Edited by Janvi 19th Oct
        tvDate = (TextView)findViewById(R.id.tvDate);
        Date buildDate = com.aspl.mbs.BuildConfig.BUILD_TIME;
//        Date buildDate = BuildConfig.BUILD_TIME;
        SimpleDateFormat simple = new SimpleDateFormat("MM/dd/yyyy");
        tvDate.setText("Version Date: " + simple.format(buildDate));
        //end

//        callgetThemeWS();

//        Edited by Varun for lockdown feature
//        callcontactinfo();
//        END

        //String Url1 = Constant.WS_BASE_URL + Constant.GETDEPARTMENT + Constant.STOREID;
        // new Async_getCommonService(this, Url1).execute();


        // Start the initial runnable task by posting through the handler
        //

        new Thread(() -> {
            try {
                SupportMapFragment mf = SupportMapFragment.newInstance();
                getSupportFragmentManager().beginTransaction()
                        .add(R.id.dummy_map_view, mf)
                        .commit();
                runOnUiThread(() -> mf.getMapAsync(SplaceScreen.this));
            }catch (Exception ignored){
            }
        }).start();
    }



    public void callgetThemeWS() {

//        setstorelogo(Constant.STOREID)

        if(!Constant.isFromChangeLocDialog) {

            //to set fav store location with existing user
            //when re open app from splash screen
//            Constant.MainSTOREID = Constant.STOREID;
            Constant.AppPref = getSharedPreferences(Constant.PrefName, MODE_PRIVATE);

            if (!Constant.AppPref.getString("currentCustId", "").isEmpty()) {
                String currentCusId = Constant.AppPref.getString("currentCustId", "");
                if (currentCusId != null && !currentCusId.isEmpty()) {
                    String favStore = Constant.AppPref.getString("favStore", "");
                    if (favStore != null && !favStore.isEmpty()) {
                        Constant.STOREID = favStore;
                    }
                }
            }
            //end
        }
//        else{
//            Constant.isFromChangeLocDialog = false;
//        }



        String Url = Constant.WS_BASE_URL + Constant.GETTHEME + Constant.STOREID;
        Log.i("web service", "Request Url : " + Url);
        new Async_getCommonService(this, Url).execute();

    }


    /*public void onFCMTokenRegistration(){
        Log.d("fcm", "fcm token : " + FirebaseInstanceId.getInstance().getToken());
        String fcmToken = "";
        fcmToken = FirebaseInstanceId.getInstance().getToken();

        if (fcmToken != null && !fcmToken.isEmpty()) {
            //http://192.168.172.211:888/Home/InsertDeviceDetails?storeno=707&DeviceID=etre&&TokenNo=0007&customerid=123
            String url = "";
            url = Constant.URL + Constant.INSERT_FCM_TOKEN + Constant.STOREID
                    + "&DeviceID=" + DeviceInfo.getDeviceId(SplaceScreen.this)
                    + "&TokenNo=" + fcmToken
                    *//*+ "&customerid=" + ""*//*;

            TaskFCMTokenRegister tokenRegister = new TaskFCMTokenRegister(this);
            tokenRegister.execute(url);
        }
    }*/

    @Override
    public void onFCMTokenRegistrationResult(String response) {
        if (response.equals("Success")) {
            //Toast.makeText(splaceScreen, "Token Registered Successfully", Toast.LENGTH_SHORT).show();
        } else {
//            Toast.makeText(splaceScreen, "FCM failed", Toast.LENGTH_SHORT).show();
        }
    }

    public void setTheme() {
//        ll_splace.setBackgroundColor(Color.parseColor(Constant.themeModel.Backgroundcolor));

        //for set dynamic logo start
        try {
//            fl_splace.setBackgroundColor(Color.parseColor(Constant.themeModel.Backgroundcolor));

            Log.e("background color:","" +Constant.themeModel.Backgroundcolor);
            //String url = Constant.IMG_BASEURL + Constant.LOGO + Constant.STOREID + "/" + Constant.themeModel.StoreLogo;
            String url = Constant.IMG_BASEURL + Constant.LOGO + Constant.STOREID + "/" + "AppLogo_"+Constant.STOREID+".jpg";
            Log.i("web service", "Request Url : " + url);
            Utils.setImageFromUrl(this, url, splaceImage);

        }
        catch (Exception e){

        }
        //end above

        //we have created setstorelogo method to provide main store's logo of their sub store
        try {
            if(Constant.STOREID.equals(Constant.MainSTOREID)){
                setStorelogo(Constant.STOREID);
                //        //temp *****************
            }else{
                setStorelogo(Constant.MainSTOREID);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private void setStorelogo(String STOREID) {

        try{

            if(STOREID.equals("57")){
                FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(500, 500);
                layoutParams.gravity = Gravity.CENTER;
                splaceImage.setLayoutParams(layoutParams);
                Glide.with(this)
//                        .load(R.drawable.img_store57)
                        .load(R.drawable.app_logo_storeno57_new)
                        .fitCenter()
                        .diskCacheStrategy(DiskCacheStrategy.ALL)
                        .into(splaceImage);
            }else if(STOREID.equals("44")){
                Glide.with(this)
                        .load(R.drawable.store44_applogo)
                        .fitCenter()
                        .diskCacheStrategy(DiskCacheStrategy.ALL)
                        .into(splaceImage);
            }else if(STOREID.equals("99")){
                Glide.with(this)
                        .load(R.drawable.img_store99_default)
                        .fitCenter()
                        .diskCacheStrategy(DiskCacheStrategy.ALL)
                        .into(splaceImage);
            }else if(STOREID.equals("7365")){
                Glide.with(this)
                        .load(R.drawable.store7365_applogo)
                        .fitCenter()
                        .diskCacheStrategy(DiskCacheStrategy.ALL)
                        .into(splaceImage);
            }
            else if(STOREID.equals("105")){
                Glide.with(this)
                        .load(R.drawable.store105_applogo)
                        .fitCenter()
                        .diskCacheStrategy(DiskCacheStrategy.ALL)
                        .into(splaceImage);
            }
            else if(STOREID.equals("104")){
                Glide.with(this)
                        .load(R.drawable.applogo_store104)
                        .fitCenter()
                        .diskCacheStrategy(DiskCacheStrategy.ALL)
                        .into(splaceImage);
                fl_splace.setBackgroundColor(getResources().getColor(R.color.store_104color));
            }

            else if(STOREID.equals("90")){
                Glide.with(this)
                        .load(R.drawable.app_iconstore90_new)
                        .fitCenter()
                        .diskCacheStrategy(DiskCacheStrategy.ALL)
                        .into(splaceImage);
            }
            else if(STOREID.equals("3824")){
                Glide.with(this)
                        .load(R.drawable.applogo3824_big)
                        .fitCenter()
                        .diskCacheStrategy(DiskCacheStrategy.ALL)
                        .into(splaceImage);
            }else if(STOREID.equals("315")){
                Glide.with(this)
                        .load(R.drawable.pws_app_large_icon315)
                        .fitCenter()
                        .diskCacheStrategy(DiskCacheStrategy.ALL)
                        .into(splaceImage);
            }else if(STOREID.equals("96")){
                Glide.with(this)
                        .load(R.drawable.applogo_wineroom_fh_store96)
                        .fitCenter()
                        .diskCacheStrategy(DiskCacheStrategy.ALL)
                        .into(splaceImage);
            } else if(STOREID.equals("540")){
                Glide.with(this)
                        .load(R.drawable.applogo_store540)
                        .fitCenter()
                        .diskCacheStrategy(DiskCacheStrategy.ALL)
                        .into(splaceImage);

            }
//            Edited by Varun for app logo and splash screen image change
            else if(STOREID.equals("327")){

                FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(500, 500);
                layoutParams.gravity = Gravity.CENTER;
                splaceImage.setLayoutParams(layoutParams);

                Glide.with(this)
//                        .load(R.drawable.applogo_store327)
                        .load(R.drawable.applogo_327_new)
                        .fitCenter()
                        .diskCacheStrategy(DiskCacheStrategy.ALL)
                        .into(splaceImage);

            }else if(STOREID.equals("707")){

                FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(500, 500);
                layoutParams.gravity = Gravity.CENTER;
                splaceImage.setLayoutParams(layoutParams);

                Glide.with(this)
                        .load(R.drawable.applogo_327_new)
                        .fitCenter()
                        .diskCacheStrategy(DiskCacheStrategy.ALL)
                        .into(splaceImage);

            }
//            END
            else if(STOREID.equals("1802")){
                Glide.with(this)
                        .load(R.drawable.app_loading_screen_1802)
                        .fitCenter()
                        .diskCacheStrategy(DiskCacheStrategy.ALL)
                        .into(splaceImage);
            } else if(STOREID.equals("2105")){
                Glide.with(this)
                        .load(R.drawable.applogo_splashscreen_2105)
                        .fitCenter()
                        .diskCacheStrategy(DiskCacheStrategy.ALL)
                        .into(splaceImage);

            }else if(STOREID.equals("224")){
                Glide.with(this)
                        .load(R.drawable.app_icon_224)
                        .fitCenter()
                        .diskCacheStrategy(DiskCacheStrategy.ALL)
                        .into(splaceImage);
            }else if(STOREID.equals("4450")){
                Glide.with(this)
                        .load(R.drawable.splash_4450_new)
                        .fitCenter()
                        .diskCacheStrategy(DiskCacheStrategy.ALL)
                        .into(splaceImage);
            }else if(STOREID.equals("4473")){
                Glide.with(this)
                        .load(R.drawable.splash_4473)
                        .fitCenter()
                        .diskCacheStrategy(DiskCacheStrategy.ALL)
                        .into(splaceImage);
            }else if(STOREID.equals("2401")){
                Glide.with(this)
                        .load(R.drawable.splash_2401)
                        .fitCenter()
                        .diskCacheStrategy(DiskCacheStrategy.ALL)
                        .into(splaceImage);
            }

//            if(Constant.STOREID.equals("3824")){
//                splaceImage.setBackgroundColor(getResources().getColor(R.color.bg_colorBig_three));
//            }else{
//                splaceImage.setBackgroundColor(getResources().getColor(R.color.androidWhite));
//            }

//            if(Constant.STOREID.equals("57")){
//                Glide.with(this)
//                        .load(R.drawable.img_store57)
//                        .fitCenter()
//                        .diskCacheStrategy(DiskCacheStrategy.ALL)
//                        .into(splaceImage);
//            }else if(Constant.STOREID.equals("44")){
//                Glide.with(this)
//                        .load(R.drawable.store44_applogo)
//                        .fitCenter()
//                        .diskCacheStrategy(DiskCacheStrategy.ALL)
//                        .into(splaceImage);
//            }else if(Constant.STOREID.equals("99")){
//                Glide.with(this)
//                        .load(R.drawable.img_store99_default)
//                        .fitCenter()
//                        .diskCacheStrategy(DiskCacheStrategy.ALL)
//                        .into(splaceImage);
//            }else if(Constant.STOREID.equals("7365")){
//                Glide.with(this)
//                        .load(R.drawable.store7365_applogo)
//                        .fitCenter()
//                        .diskCacheStrategy(DiskCacheStrategy.ALL)
//                        .into(splaceImage);
//            }
//            else if(Constant.STOREID.equals("105")){
//                Glide.with(this)
//                        .load(R.drawable.store105_applogo)
//                        .fitCenter()
//                        .diskCacheStrategy(DiskCacheStrategy.ALL)
//                        .into(splaceImage);
//            }
//
//            else if(Constant.STOREID.equals("90")){
//                Glide.with(this)
//                        .load(R.drawable.app_iconstore90_new)
//                        .fitCenter()
//                        .diskCacheStrategy(DiskCacheStrategy.ALL)
//                        .into(splaceImage);
//            }
//            else if(Constant.STOREID.equals("3824")){
//                Glide.with(this)
//                        .load(R.drawable.applogo3824_big)
//                        .fitCenter()
//                        .diskCacheStrategy(DiskCacheStrategy.ALL)
//                        .into(splaceImage);
//            }else if(Constant.STOREID.equals("315")){
//                Glide.with(this)
//                        .load(R.drawable.pws_app_large_icon315)
//                        .fitCenter()
//                        .diskCacheStrategy(DiskCacheStrategy.ALL)
//                        .into(splaceImage);
//            }else if(Constant.STOREID.equals("96")){
//                Glide.with(this)
//                        .load(R.drawable.applogo_wineroom_fh_store96)
//                        .fitCenter()
//                        .diskCacheStrategy(DiskCacheStrategy.ALL)
//                        .into(splaceImage);
//            } else if(Constant.STOREID.equals("540")){
//                Glide.with(this)
//                        .load(R.drawable.applogo_store540)
//                        .fitCenter()
//                        .diskCacheStrategy(DiskCacheStrategy.ALL)
//                        .into(splaceImage);
//
//            }
//            else if(Constant.STOREID.equals("327")||(Constant.STOREID.equals("707"))){
//
//                FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(500, 500);
//                layoutParams.gravity = Gravity.CENTER;
//                splaceImage.setLayoutParams(layoutParams);
//
//                Glide.with(this)
//                        .load(R.drawable.applogo_store327)
//                        .fitCenter()
//                        .diskCacheStrategy(DiskCacheStrategy.ALL)
//                        .into(splaceImage);
//
//            }

        }catch (Exception e){
            e.printStackTrace();
        }

    }

    public void gotoDeshboard() {
        //handler.postDelayed(runnableCode, 5000);

        Intent ii=null;
        if(Constant.SCREEN_LAYOUT==1) {
            ii = new Intent(SplaceScreen.this, MainActivity.class);
        }else if(Constant.SCREEN_LAYOUT==2){
            ii = new Intent(SplaceScreen.this, MainActivityDup.class);
        }

//        try{
        if(ii != null){
            ii.putExtra("CustomerId", CustomerId);
            ii.putExtra("URL", URL);
            startActivity(ii);
            finish();
        }

//        }catch (Exception e){
//
//        }
//
    }

    private Runnable runnableCode = new Runnable() {
        @Override
        public void run() {
            Intent ii=null;
            if(Constant.SCREEN_LAYOUT==1) {
                ii = new Intent(SplaceScreen.this, MainActivity.class);
            }else if(Constant.SCREEN_LAYOUT==2){
                ii = new Intent(SplaceScreen.this, MainActivityDup.class);
            }
            startActivity(ii);

        }
    };

    @Override
    public void onMapReady(GoogleMap googleMap) {

    }

//    Edited by Varun for lockdown feature

    public void callcontactinfo() {

        Constant.storeLocationList2.clear();
        Constant.storeLocationList.clear();

        String getContactInfoURL = WS_BASE_URL + GET_CONTACT_INFO + "/" + STOREID;
        TaskContactInfo taskContactInfo = new TaskContactInfo(this, SplaceScreen.this);
        taskContactInfo.execute(getContactInfoURL);

    }

    @Override
    public void contactInfoEventResult(ContatInfo contatInfo) {

        Constant.contatInfo = contatInfo;

        loadStoreLocationWSdata();

    }

    public void loadStoreLocationWSdata() {

        String zip = " ";

        if (contatInfo.getZip() != null && !contatInfo.getZip().isEmpty()) {
            zip = contatInfo.getZip();

            String storeLocationURL = WS_BASE_URL + GET_CORPORATE_STORE_SUBSTORELIST_V1 + "/" + STOREID + "/" + zip;
            TaskStoreLocationInfo taskStoreLocationInfo = new TaskStoreLocationInfo(this, SplaceScreen.this, false);
            taskStoreLocationInfo.execute(storeLocationURL);
        }
    }

    @Override
    public void storeLocationInfoResult(List<StoreLocationModel> storeLocationList, Boolean isSearchLocation) {


        Constant.LockStoreName = "" ;
        Constant.LockStoreAddress = "" ;
        Constant.LockCity = "" ;
        Constant.LockState ="" ;
        Constant.LockZip = "" ;

        Constant.isSearchLocation = isSearchLocation;
        Boolean B =true;

        if (storeLocationList.size()!=0 && !storeLocationList.isEmpty() ) {

            for (int i = 0; i < storeLocationList.size(); i++) {
                if (!storeLocationList.get(i).getCoStoreno().equals("") && !storeLocationList.get(i).getStoreLock()) {
                    Constant.storeLocationList2.add(storeLocationList.get(i));
                    Constant.storeLocationList = Constant.storeLocationList2;
                }
            }

            for (int i = 0; i < storeLocationList.size(); i++) {

                if (STOREID.equals(storeLocationList.get(i).getStoreno())) {
                    if (storeLocationList.get(i).getStoreLock()) {
//                if (B){
//                    Toast.makeText(splaceScreen, "Lockk", Toast.LENGTH_SHORT).show();

                        Constant.LockStoreName = storeLocationList.get(i).getName();
                        Constant.LockStoreAddress = storeLocationList.get(i).getAddress();
                        Constant.LockCity = storeLocationList.get(i).getCity();
                        Constant.LockState = storeLocationList.get(i).getSt();
                        Constant.LockZip = storeLocationList.get(i).getZip();
                        Constant.PhoneNumber = storeLocationList.get(i).getPhone();

                        Utils.LockChangeLocation(splaceScreen, Constant.storeLocationList2);

                    } else {
//                    callgetThemeWS();
                        gotoDeshboard();
                    }
                }
//                else {
//                    gotoDeshboard();
//                }
            }
        }else{
            gotoDeshboard();
        }
    }


    public void fromSavelocation() {

        if (Constant.SCREEN_LAYOUT == 1) {
//            progressBar.setVisibility(View.VISIBLE);
            Constant.isFromChangeLocDialog = true;
            callgetThemeWS();
//            callcontactinfo();

        } else if (Constant.SCREEN_LAYOUT == 2) {
            Constant.isFromChangeLocDialog = true;
//            progressBar.setVisibility(View.VISIBLE);
            callgetThemeWS();
//            callcontactinfo();

        }
    }


//    END


    private void Call_Update_App_Verification() {

//        new FetchLatestVersionCodeTask().execute();
        UpdateApp();

    }

    public void UpdateApp() {

        AppUpdateManager appUpdateManager = AppUpdateManagerFactory.create(this);
        Task<AppUpdateInfo> appUpdateInfoTask = appUpdateManager.getAppUpdateInfo();

        appUpdateInfoTask.addOnSuccessListener(result -> {
            Log.e("UpdateApp", "UpdateApp: "+result );
            try {
                if (result.updateAvailability() == UpdateAvailability.UPDATE_AVAILABLE) {
                    latestVersionName = String.valueOf(result.availableVersionCode());
                    showUpdateDialog();
                } else {
                    // Handle the case when no update is available
                   callgetThemeWS();
                }
            } catch (Exception e) {
                // Log the exception for further investigation
                Log.e("UpdateCheck", "Exception during update check", e);
                // Handle the exception accordingly, e.g., show an error message
                Toast.makeText(splaceScreen, "Exception during update check", Toast.LENGTH_SHORT).show();
                callgetThemeWS();
            }
        }).addOnFailureListener(e -> {
            // Log the failure for further investigation
            Log.e("UpdateCheck", "Failure during update check", e);
            // Handle the failure accordingly, e.g., show an error message
            Toast.makeText(splaceScreen, "Failure during update check", Toast.LENGTH_SHORT).show();
            callgetThemeWS();
        });

    }


//    public class FetchLatestVersionCodeTask extends AsyncTask<String,String,String> {
//
//        @Override
//        protected String doInBackground(String... strings) {
//            try {
//                Document document = Jsoup.connect("https://play.google.com/store/apps/details?id=" + getPackageName() + "&hl=en")
//                        .timeout(30000)
//                        .userAgent("Mozilla/5.0 (Windows; U; WindowsNT 5.1; en-US; rv1.8.1.6) Gecko/20070725 Firefox/2.0.0.6")
//                        .referrer("http://www.google.com")
//                        .ignoreHttpErrors(true)
//                        .get();
//
//                Element versionElement = document.select("span.htlgb:nth-child(4) > .htlgb span").first();
//                if (versionElement != null) {
//                    String version = versionElement.ownText();
//                    return version;
//                } else {
//                    return "0";
//                }
//
//            } catch (Exception e) {
//                return "";
//            }
//        }
//
//        @Override
//        protected void onPostExecute(String version) {
//            super.onPostExecute(version);
//            sLatestVersion = Integer.parseInt(version);
//
//
//            if (sLatestVersion != 0 ) {
//                int sCurrentVersion = BuildConfig.VERSION_CODE;
//                if (sLatestVersion > sCurrentVersion) {
//                    Toast.makeText(SplaceScreen.this, "Update Available", Toast.LENGTH_SHORT).show();
//                    showUpdateDialog();
//                } else {
//                    Toast.makeText(SplaceScreen.this, "Update not Available", Toast.LENGTH_SHORT).show();
//                    callgetThemeWS();
//                }
//            }else{
//                Toast.makeText(SplaceScreen.this, "Failed to fetch version code", Toast.LENGTH_SHORT).show();
//                callgetThemeWS();
//            }
//        }
//    }

//    private class FetchLatestVersionCodeTask extends AsyncTask<Void, Void, Integer> {
//
//        @Override
//        protected Integer doInBackground(Void... voids) {
//            try {
//                sLatestVersion= Integer.parseInt(Jsoup
//                        .connect("https://play.google.com//store/apps/details?id="
//                                +getPackageName())
//                        .timeout(30000)
//                        .get()
//                        .select("div.hAyfc:nth-child(4)>"+
//                                "span:nth-child(2) > div:nth-child(1)"+
//                                "> span:nth-child(1)")
//                        .first()
//                        .ownText());
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//
//            return sLatestVersion;
//        }
//
//        @Override
//        protected void onPostExecute(Integer latestVersionCode) {
//            if (latestVersionCode != null) {
//
//                int currentVersionCode = BuildConfig.VERSION_CODE;
//                if (latestVersionCode > currentVersionCode) {
//                    Toast.makeText(SplaceScreen.this, "Update Available", Toast.LENGTH_SHORT).show();
//                    showUpdateDialog();
//                } else {
//                    Toast.makeText(SplaceScreen.this, "Up to Date", Toast.LENGTH_SHORT).show();
//                    callgetThemeWS();
//                }
//            } else {
//                Toast.makeText(SplaceScreen.this, "Failed to fetch version code", Toast.LENGTH_SHORT).show();
//                callgetThemeWS();
//            }
//        }
//    }

    private void showUpdateDialog() {


        PackageManager packageManager = getApplicationContext().getPackageManager();
        ApplicationInfo applicationInfo = null;
        try {
            applicationInfo = packageManager.getApplicationInfo(getPackageName(), 0);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }

        String appName = (String) packageManager.getApplicationLabel(applicationInfo);

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Update Available");
        builder.setMessage("A new version of "+ appName +" is available. Please update to new version now.");

        builder.setPositiveButton("Update", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // Redirect user to the Play Store for updating the app
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse("market://details?id=" + getPackageName()));

                // Verify if the Play Store app is available
                List<ResolveInfo> resolveInfoList = packageManager.queryIntentActivities(intent, PackageManager.MATCH_DEFAULT_ONLY);

                if (resolveInfoList.size() > 0) {
                    startActivity(intent);
                } else {
                    // If Play Store is not available, open the app's Play Store web page
                    intent.setData(Uri.parse("https://play.google.com/store/apps/details?id=" + getPackageName()));
                    startActivity(intent);
                }
                finish();
            }
        });

        builder.setNegativeButton("Later", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
                callgetThemeWS();
            }
        });

        // Set the dialog to not dismiss when touched outside
        AlertDialog dialog = builder.create();
        dialog.setCanceledOnTouchOutside(false);

        dialog.show();
    }


}
