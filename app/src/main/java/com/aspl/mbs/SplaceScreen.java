package com.aspl.mbs;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.content.pm.ShortcutInfo;
import android.content.pm.ShortcutManager;
import android.graphics.Color;
import android.graphics.drawable.Icon;
import android.net.Uri;
import android.net.wifi.WifiManager;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import androidx.appcompat.app.AppCompatActivity;
import android.text.format.Formatter;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.aspl.Utils.Constant;
import com.aspl.Utils.DialogUtils;
import com.aspl.Utils.Utils;
import com.aspl.mbsmodel.ContatInfo;
import com.aspl.mbsmodel.DatabaseInfo;
import com.aspl.mbsmodel.StoreLocationModel;
import com.aspl.task.TaskContactInfo;
import com.aspl.task.TaskDataBaseDetail;
import com.aspl.task.TaskFCMTokenRegister;
import com.aspl.task.TaskStoreLocationInfo;
import com.aspl.ws.Async_getCommonService;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.tasks.Task;
import com.google.android.play.core.appupdate.AppUpdateInfo;
import com.google.android.play.core.appupdate.AppUpdateManager;
import com.google.android.play.core.appupdate.AppUpdateManagerFactory;
import com.google.android.play.core.install.model.UpdateAvailability;
import com.google.firebase.crash.FirebaseCrash;


import java.io.IOException;
import java.net.ServerSocket;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static com.aspl.Utils.Constant.GET_CONTACT_INFO;
import static com.aspl.Utils.Constant.GET_CORPORATE_STORE_SUBSTORELIST_V1;
import static com.aspl.Utils.Constant.GET_DATA_BASE_DETAIL;
import static com.aspl.Utils.Constant.STOREID;
import static com.aspl.Utils.Constant.WS_BASE_URL;
import static com.aspl.Utils.Constant.contatInfo;

/**
 * Created by new on 07/11/2017.
 */
public class SplaceScreen extends AppCompatActivity implements TaskFCMTokenRegister.TaskFCMTokenRegistrationListener,
        TaskContactInfo.TaskContactInfoEvent, TaskStoreLocationInfo.TaskStoreLocationEvent,OnMapReadyCallback, TaskDataBaseDetail.TaskDataBaseDetailEvent {
    Handler handler = new Handler();
    //    LinearLayout ll_splace;
    FrameLayout fl_splace;
    public static SplaceScreen splaceScreen;
    ImageView splaceImage;
    String URL;
    String CustomerId;
    TextView tvDate;
    String latestVersionName;

    public static SplaceScreen getInstance() {
        return splaceScreen;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        splaceScreen = this;
        setContentView(R.layout.splace);

        int color = Color.parseColor("#FFFFFF");

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.setStatusBarColor(color);
        }

        Log.e("", "onCreate: Varun Start the Splash Screen On create Method");

        fl_splace = (FrameLayout)findViewById(R.id.fl_splace);
        splaceImage = (ImageView) findViewById(R.id.splaceImage);

        //Edited by Janvi 19th Oct
        tvDate = (TextView)findViewById(R.id.tvDate);

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
        }else if (StoreConstant.StoreNo.STORE400 == StoreConstant.storeNo) {
            Constant.STOREID = "400";
        }else if (StoreConstant.StoreNo.STORE1040 == StoreConstant.storeNo) {
            Constant.STOREID = "1040";
        }else if (StoreConstant.StoreNo.STORE3310 == StoreConstant.storeNo) {
            Constant.STOREID = "3310";
        }else if (StoreConstant.StoreNo.STORE178 == StoreConstant.storeNo) {
            Constant.STOREID = "178";
        }

        Constant.MainSTOREID = Constant.STOREID;
        calldatabaseWS();
        Date buildDate = com.aspl.mbs.BuildConfig.BUILD_TIME;
        SimpleDateFormat simple = new SimpleDateFormat("MM/dd/yyyy");
        tvDate.setText("Version Date: " + simple.format(buildDate));

//        Constant.AppPref = getSharedPreferences(Constant.PrefName, MODE_PRIVATE);

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

    private void calldatabaseWS() {

        if(Constant.STOREID.equals(Constant.MainSTOREID)){
            setStorelogo(Constant.STOREID);
        }else{
            setStorelogo(Constant.MainSTOREID);
        }

        String URL = WS_BASE_URL + GET_DATA_BASE_DETAIL + STOREID ;

        TaskDataBaseDetail taskDataBaseDetail = new TaskDataBaseDetail(this,this);
        taskDataBaseDetail.executeOnExecutor(TaskDataBaseDetail.THREAD_POOL_EXECUTOR, URL);

    }

    @Override
    public void DatabaseInfoEventResult(DatabaseInfo databaseInfo) {
        if (!databaseInfo.getEnableOnlineStore()) {
            // Inflate custom layout
            LayoutInflater inflater = LayoutInflater.from(splaceScreen);
            View dialogView = inflater.inflate(R.layout.dialog_custom_button, null);

            // Create AlertDialog and set the custom view
            AlertDialog.Builder builder = new AlertDialog.Builder(splaceScreen);
            builder.setView(dialogView);
            builder.setCancelable(false); // Prevent dismissing on back press

            // Find the button in the custom layout
            Button closeButton = dialogView.findViewById(R.id.dialog_button_close_logoff);
            closeButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // Close the application
                    finish();
                }
            });

            // Show the dialog
            AlertDialog dialog = builder.create();
            dialog.show();
        } else {
            callgetThemeWS();
        }
    }
    public void callgetThemeWS() {

//        callcontactinfo();

        if(!Constant.isFromChangeLocDialog) {

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

//        Edited by Varun For Speed-up

        String baseUrl = Constant.WS_BASE_URL;
        String storeId = Constant.STOREID;

        Constant.storeLocationList2.clear();
        Constant.storeLocationList.clear();

        String getContactInfoURL = WS_BASE_URL + GET_CONTACT_INFO + "/" + STOREID;
        TaskContactInfo taskContactInfo = new TaskContactInfo(this, SplaceScreen.this);
//        taskContactInfo.execute(getContactInfoURL);
        taskContactInfo.executeOnExecutor(TaskContactInfo.THREAD_POOL_EXECUTOR, getContactInfoURL);

        new Async_getCommonService(this, baseUrl + Constant.GETTHEME + storeId).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
        new Async_getCommonService(this, baseUrl + Constant.GETPAGES_FOR_ANDROID + storeId).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
        new Async_getCommonService(this, baseUrl + Constant.GETPAGES_STATUS + storeId).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
        new Async_getCommonService(this, baseUrl + Constant.GETPAGES_DETAIL_BLOG + storeId).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);

//        String Url = Constant.WS_BASE_URL + Constant.GETTHEME + Constant.STOREID;
//        new Async_getCommonService(this, Url).execute();

    }


    @Override
    public void onFCMTokenRegistrationResult(String response) {
        if (response.equals("Success")) {

        } else {

        }
    }

    public void setTheme() {
        try {
            Log.e("background color:","" +Constant.themeModel.Backgroundcolor);
            //String url = Constant.IMG_BASEURL + Constant.LOGO + Constant.STOREID + "/" + Constant.themeModel.StoreLogo;
            String url = Constant.IMG_BASEURL + Constant.LOGO + Constant.STOREID + "/" + "AppLogo_"+Constant.STOREID+".jpg";
            Log.i("web service", "Request Url : " + url);
            Utils.setImageFromUrl(this, url, splaceImage);
        }
        catch (Exception e){

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
            }else if(STOREID.equals("400")){
                Glide.with(this)
                        .load(R.drawable.splash_cheers_400)
                        .fitCenter()
                        .diskCacheStrategy(DiskCacheStrategy.ALL)
                        .into(splaceImage);
            }else if(STOREID.equals("1040")){
                Glide.with(this)
                        .load(R.drawable.splash_liquor_land_1040)
                        .fitCenter()
                        .diskCacheStrategy(DiskCacheStrategy.ALL)
                        .into(splaceImage);
            } else if(STOREID.equals("3310")){
                Glide.with(this)
                        .load(R.drawable.splash_westside_beverage_3310)
                        .fitCenter()
                        .diskCacheStrategy(DiskCacheStrategy.ALL)
                        .into(splaceImage);
            } else if(STOREID.equals("2401")){
                Glide.with(this)
                        .load(R.drawable.splash_2401)
                        .fitCenter()
                        .diskCacheStrategy(DiskCacheStrategy.ALL)
                        .into(splaceImage);
            }else if(STOREID.equals("178")){
                Glide.with(this)
                        .load(R.drawable.app_icon_178)
                        .fitCenter()
                        .diskCacheStrategy(DiskCacheStrategy.ALL)
                        .into(splaceImage);
            }


        }catch (Exception e){
            e.printStackTrace();
        }

    }

    public void gotoDeshboard() {

        if (getIntent() != null && getIntent().getAction() != null &&
                getIntent().getAction().equals(Intent.ACTION_MAIN) &&
                getIntent().hasExtra("android.intent.extra.shortcut.ID")) {

            String shortcutId = getIntent().getStringExtra("android.intent.extra.shortcut.ID");


            if ("cart".equals(shortcutId)) {
                Intent intent = new Intent(this, Constant.SCREEN_LAYOUT == 1 ? MainActivity.class : MainActivityDup.class);
                intent.putExtra("openCartFragment", true);
                startActivity(intent);
            } else if ("wishlist".equals(shortcutId)) {

                Intent intent = new Intent(this, Constant.SCREEN_LAYOUT == 1 ? MainActivity.class : MainActivityDup.class);
                intent.putExtra("openWishlistFragment", true);
                startActivity(intent);
            }
            getIntent().removeExtra("android.intent.extra.shortcut.ID");
            finish();
        } else {
            createDynamicShortcuts();

            Intent ii = null;
            if (Constant.SCREEN_LAYOUT == 1) {
                ii = new Intent(SplaceScreen.this, MainActivity.class);
            } else if (Constant.SCREEN_LAYOUT == 2) {
                ii = new Intent(SplaceScreen.this, MainActivityDup.class);
            }

            Constant.IsComeFromSplash=true;

            if (ii != null) {
                ii.putExtra("CustomerId", CustomerId);
                ii.putExtra("URL", URL);
                startActivity(ii);
                finish();
                Log.e("", "onCreateView: Varun going from Splash Screen to Main Activity" );
            }
        }
    }

    private void createDynamicShortcuts() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            ShortcutManager shortcutManager = getSystemService(ShortcutManager.class);

            if (shortcutManager != null) {
                List<ShortcutInfo> shortcuts = new ArrayList<>();

                // CartFragment shortcut
                Intent cartShortcutIntent = new Intent(getApplicationContext(), SplaceScreen.class);
                cartShortcutIntent.setAction(Intent.ACTION_MAIN);
                cartShortcutIntent.putExtra("android.intent.extra.shortcut.ID", "cart");

                ShortcutInfo cartShortcut = new ShortcutInfo.Builder(this, "dynamic_shortcut_cart")
                        .setShortLabel(getString(R.string.shortcut_short_label_open_cart))
                        .setLongLabel(getString(R.string.shortcut_long_label_open_cart))
                        .setIcon(Icon.createWithResource(this, R.drawable.ic_baseline_shopping_cart_24))
                        .setIntent(cartShortcutIntent)
                        .build();

                shortcuts.add(cartShortcut);

                // WishlistFragment shortcut
                Intent wishlistShortcutIntent = new Intent(getApplicationContext(), SplaceScreen.class);
                wishlistShortcutIntent.setAction(Intent.ACTION_MAIN);
                wishlistShortcutIntent.putExtra("android.intent.extra.shortcut.ID", "wishlist");

                ShortcutInfo wishlistShortcut = new ShortcutInfo.Builder(this, "dynamic_shortcut_wishlist")
                        .setShortLabel(getString(R.string.shortcut_short_label_open_wishlist))
                        .setLongLabel(getString(R.string.shortcut_long_label_open_wishlist))
                        .setIcon(Icon.createWithResource(this, R.drawable.ic_baseline_star_24))
                        .setIntent(wishlistShortcutIntent)
                        .build();

                shortcuts.add(wishlistShortcut);

                // Set the dynamic shortcuts
                shortcutManager.setDynamicShortcuts(shortcuts);
            }
        }
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
                    gotoDeshboard();
                }
            } catch (Exception e) {
                // Log the exception for further investigation
                Log.e("UpdateCheck", "Exception during update check", e);
                // Handle the exception accordingly, e.g., show an error message
//                Toast.makeText(splaceScreen, "Exception during update check", Toast.LENGTH_SHORT).show();
                gotoDeshboard();
            }
        }).addOnFailureListener(e -> {
            // Log the failure for further investigation
            Log.e("UpdateCheck", "Failure during update check", e);
            // Handle the failure accordingly, e.g., show an error message
//            Toast.makeText(splaceScreen, "Failure during update check", Toast.LENGTH_SHORT).show();
            gotoDeshboard();
        });

    }
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
                gotoDeshboard();
            }
        });

        // Set the dialog to not dismiss when touched outside
        AlertDialog dialog = builder.create();
        dialog.setCanceledOnTouchOutside(false);

        dialog.show();
    }

    //    Edited by Varun for lockdown feature

    public void callcontactinfo() {



    }

    @Override
    public void contactInfoEventResult(ContatInfo contatInfo) {
        loadStoreLocationWSdata();
    }

    public void loadStoreLocationWSdata() {

        String zip = " ";

        if (contatInfo.getZip() != null && !contatInfo.getZip().isEmpty()) {
            zip = contatInfo.getZip();

            String storeLocationURL = WS_BASE_URL + GET_CORPORATE_STORE_SUBSTORELIST_V1 + "/" + STOREID + "/" + zip;
            TaskStoreLocationInfo taskStoreLocationInfo = new TaskStoreLocationInfo(this, SplaceScreen.this, false);
//            Edited by Varun For Speed -up
//            taskStoreLocationInfo.execute(storeLocationURL);
            taskStoreLocationInfo.executeOnExecutor(TaskStoreLocationInfo.THREAD_POOL_EXECUTOR, storeLocationURL);

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

        if (storeLocationList.size()!=0 && !storeLocationList.isEmpty() ) {

            for (int i = 0; i < storeLocationList.size(); i++) {

                if (!storeLocationList.get(i).getCoStoreno().equals("") && !storeLocationList.get(i).getStoreLock()) {
                    Constant.storeLocationList2.add(storeLocationList.get(i));
                    Constant.storeLocationList = Constant.storeLocationList2;
                }

                if (STOREID.equals(storeLocationList.get(i).getStoreno())) {
                    if (storeLocationList.get(i).getStoreLock()) {

                        Constant.LockStoreName = storeLocationList.get(i).getName();
                        Constant.LockStoreAddress = storeLocationList.get(i).getAddress();
                        Constant.LockCity = storeLocationList.get(i).getCity();
                        Constant.LockState = storeLocationList.get(i).getSt();
                        Constant.LockZip = storeLocationList.get(i).getZip();
                        Constant.PhoneNumber = storeLocationList.get(i).getPhone();

                        Utils.LockChangeLocation(splaceScreen, Constant.storeLocationList2);

                    } else {
                        UpdateApp();
                    }
                }
            }
        }else{
            UpdateApp();
        }
    }
}
