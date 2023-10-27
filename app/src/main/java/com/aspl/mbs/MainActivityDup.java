package com.aspl.mbs;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.res.ColorStateList;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.LightingColorFilter;
import android.graphics.Point;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.media.ToneGenerator;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.PersistableBundle;
import android.speech.RecognizerIntent;
import android.speech.tts.TextToSpeech;
import android.speech.tts.UtteranceProgressListener;
import android.speech.tts.Voice;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.support.v4.graphics.drawable.DrawableCompat;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.ContextThemeWrapper;
import android.view.Display;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.webkit.CookieManager;
import android.webkit.CookieSyncManager;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.PopupMenu;
import android.widget.PopupWindow;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.HttpHeaderParser;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.aspl.Adapter.CustomizedSpinnerAdapter;
import com.aspl.Adapter.DepartmentAdapter;
import com.aspl.Adapter.FilterAdapter_dup;
import com.aspl.Adapter.FilterChoiceAdapter;
import com.aspl.Adapter.FilterItemAdapter_dup;
import com.aspl.Adapter.FilterSelectedItemAdapter_dup;
import com.aspl.Adapter.FooterRecAdapter;
import com.aspl.Adapter.NotificationListAdapter;
import com.aspl.Adapter.ReorderItemAdapter;
import com.aspl.Utils.BottomNavigationViewHelper;
import com.aspl.Utils.Constant;
import com.aspl.Utils.DeviceInfo;
import com.aspl.Utils.DialogUtils;
import com.aspl.Utils.DrawableClickListener;
import com.aspl.Utils.EditTextCloseEvent;
import com.aspl.Utils.GpsTracker;
import com.aspl.Utils.ObservableWebView;
import com.aspl.Utils.Res;
import com.aspl.Utils.Utils;
import com.aspl._scanner.OcrCaptureActivity;
import com.aspl.fragment.AboutUsFragment;
import com.aspl.fragment.CardFragment;
import com.aspl.fragment.CardOnFileFragment;
import com.aspl.fragment.ChnagePasswordFragment;
import com.aspl.fragment.ContactUsFragment;
import com.aspl.fragment.DeliveryOptionsFragment;
import com.aspl.fragment.EditShippingAddressFragment;
import com.aspl.fragment.FilterFragment;
import com.aspl.fragment.GiftCardFragment;
import com.aspl.fragment.HomepageFragment;
import com.aspl.fragment.ItemDescriptionsFragment;
import com.aspl.fragment.Login;
import com.aspl.fragment.ManageAccountFragment;
import com.aspl.fragment.OrderHistoryFragment;
import com.aspl.fragment.OrderSummaryFragment;
import com.aspl.fragment.PaymentFragment;
import com.aspl.fragment.ProfileFragment_layout2;
import com.aspl.fragment.RewardFragment;
import com.aspl.fragment.ShippingAddressFragment;
import com.aspl.fragment.StoreDeliveryHourFragment;
import com.aspl.fragment.ViewAllFragment;
import com.aspl.fragment.WishListFragment;
import com.aspl.mbsmodel.ContatInfo;
import com.aspl.mbsmodel.DeleteAccountModel;
import com.aspl.mbsmodel.EditShippingData;
import com.aspl.mbsmodel.Filter;
import com.aspl.mbsmodel.FilterHomePage;
import com.aspl.mbsmodel.FilterInfoModel;
import com.aspl.mbsmodel.FilterModel;
import com.aspl.mbsmodel.FilterSelectedItems;
import com.aspl.mbsmodel.GetSearchData;
import com.aspl.mbsmodel.LstDepartment;
import com.aspl.mbsmodel.LstSize;
import com.aspl.mbsmodel.MbsDataModel;
import com.aspl.mbsmodel.NotificationModel;
import com.aspl.mbsmodel.ReOrderItemModel;
import com.aspl.mbsmodel.ReOrderModel;
import com.aspl.mbsmodel.ShippingData;
import com.aspl.mbsmodel.ShoppingCardModel;
import com.aspl.mbsmodel.StoreHour;
import com.aspl.mbsmodel.StoreLocationModel;
import com.aspl.mbsmodel.StoreclosesModel;
import com.aspl.mbsmodel.TwentyOneYear;
import com.aspl.mbsmodel.UserModel;
import com.aspl.mbsmodel.WishList;
import com.aspl.task.TaskAllStoreHours;
import com.aspl.task.TaskCart;
import com.aspl.task.TaskContactInfo;
import com.aspl.task.TaskCustomerData;
import com.aspl.task.TaskDeleteAccount;
import com.aspl.task.TaskDeleteWishList;
import com.aspl.task.TaskEditShipping;
import com.aspl.task.TaskFCMTokenRegister;
import com.aspl.task.TaskFilter;
import com.aspl.task.TaskFilterHomePage;
import com.aspl.task.TaskFilterInfo;
import com.aspl.task.TaskNotificationList;
import com.aspl.task.TaskReOrder;
import com.aspl.task.TaskSearch;
import com.aspl.task.TaskSessionToCart;
import com.aspl.task.TaskStoreDeliveryHours;
import com.aspl.task.TaskStoreLocationInfo;
import com.aspl.task.TaskTwentyOneYear;
import com.aspl.ws.Async_getCommonService;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.ui.PlaceAutocomplete;
import com.google.firebase.crash.FirebaseCrash;
import com.google.firebase.iid.FirebaseInstanceId;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Set;

import static com.aspl.Utils.Constant.Custormer_Id;
import static com.aspl.Utils.Constant.ENCODE_TOKEN_ID;
import static com.aspl.Utils.Constant.ISguest;
import static com.aspl.Utils.Constant.STOREID;
import static com.aspl.Utils.Constant.WS_BASE_URL;
import static com.aspl.Utils.Constant.WS_INSERT_REORDER;
import static com.aspl.Utils.Constant.selectedReorderList;


/**
 * Created by admin on 7/10/2018.
 */
public class MainActivityDup extends BaseActivity implements View.OnClickListener
        , EditShippingAddressFragment.EditShippingAddressFragmentListener
        , FragmentManager.OnBackStackChangedListener
        , CardFragment.CardEvent
        , TaskTwentyOneYear.TaskTwentyOneYearEvent
        , TaskCart.TaskCardEvent
        , WishListFragment.WishListFragmentEvent
        , DeliveryOptionsFragment.DeliveryOptionsEvent
        , PaymentFragment.PaymentEvent
        , OrderSummaryFragment.OrderSummaryEvent
        , TaskSearch.TaskSearchEvent
        , TaskFilter.TaskFilterEvent
        , TaskFilterHomePage.TaskFilterHomePageEvent
        , FilterItemAdapter_dup.FilterAdapterEvent
        , TaskFCMTokenRegister.TaskFCMTokenRegistrationListener
        , BottomNavigationView.OnNavigationItemSelectedListener, TaskDeleteAccount.TaskDeleteAccountEvent
        , TextToSpeech.OnInitListener, TextToSpeech.OnUtteranceCompletedListener, TaskReOrder.TaskReOrderEvent
        , NotificationListAdapter.NotificationListAdapterEvent, TaskNotificationList.TaskNotificationEvent, TaskDeleteWishList.TaskDeleteCartItemEvent, TaskContactInfo.TaskContactInfoEvent, TaskFilterInfo.TaskFilterInfoEvent, TaskCustomerData.TaskCustomerEvent, TaskStoreDeliveryHours.StoreDeliveryHoursEvent
        , TaskStoreLocationInfo.TaskStoreLocationEvent, TaskAllStoreHours.StoreHoursEvent, TaskEditShipping.TaskEditShippingEvent {
    public static boolean isPromotion;
    public static String blockDisplayedText;
    public ObservableWebView mContainer;
    public LinearLayout mContent, llsortandfilter/*, llsearch*/, llcheckInternet;
    public RelativeLayout llsearch;
    Toolbar mToolbar;
    UserModel userModeltemp;
    //public static ActionBarDrawerToggle actionBarDrawerToggle;
    //public DrawerLayout mDrawer;
    public /*EditText*/ AutoCompleteTextView mSearchedt;
    // public ExpandableListView mManage_expList;
    //public ListView bottom_list;
    //private TextView txthome, txtevent_cal;
    ImageView imgHome;
    //RelativeLayout view_container;
    ImageButton btndept;
    public PopupWindow listPopupWindow;
    public static MainActivityDup MainActivityDup;
    LinearLayout llFilter, llSort;
    HomepageFragment homepageFragment;
    CardOnFileFragment cardOnFileFragment;
    ManageAccountFragment manageAccountFragment;
    FilterFragment filterFragment;
    CardFragment cardFragment;
    WishListFragment wishListFragment;
    AboutUsFragment aboutUsFragment;
    ChnagePasswordFragment changePassword;
    GiftCardFragment giftCardFragment;
    ContactUsFragment contactUsFragment;
    public static DeliveryOptionsFragment deliveryOptionsFragment;
    public static PaymentFragment paymentFragment;
    public static OrderSummaryFragment orderSummaryFragment;
    public FilterChoiceAdapter filterChoiceAdapter;
    ShippingAddressFragment shippingAddressFragment;
    ProfileFragment_layout2 profileFragment_layout2;
    public static String searchText = "";
    RewardFragment rewardFragment;
    ViewAllFragment viewAllFragment;
    public static Dialog reorderDialog;
    public RecyclerView rvReorder;
    public ReorderItemAdapter reorderAdapter;
    public static boolean iscomfromSort = false;
    public static boolean iscomeFromHomeViewall = false,isComeFromDepartmentFilter = false,
            isComeFromSubdepartmentFilter = true;
    public static boolean isSearchicon = false;
    StoreDeliveryHourFragment storeDeliveryHourFragment;
    public String isComeFromManageAccount = "";
    public boolean isComeFromSigninManageAccount = false;
    public boolean isComeFromCartIconManageAccount = false;
    public boolean isSearchLocationVal = false;

//    ?edited by Varun for reward point show below the search bar
    public LinearLayout   ll_Reward_main;
    public TextView tv_Reward_point_main ,tv_points_main , tv_rebate_point_main , tv_rebate_main;
//    END

    /**
     * Filter Adapter
     **/
    public FilterItemAdapter_dup filterItemAdapter_dup;
    public static FilterSelectedItemAdapter_dup filterSelectedItemAdapter_dup;
    public static List<FilterSelectedItems> liFilterSelectedItems = new ArrayList<>();
    public RecyclerView rvDeptList;
    boolean isFileterDialogOpen = false;
    public Dialog filterDialog;
    public static int filterDepartmentPosition = 0;
    public static FilterAdapter_dup FilterAdapter_dup;
    List<FilterHomePage> liFilterHomePages;
    //public static String subDepartment = "",deptSizes = "",priceRange = "0",isPriceChanged = "N",onlyImage = "0";

    boolean isExpand = true;
    boolean isExpand1 = true;
    //ImageView imgpersonlogo;
    //public static TextView txtPersonName, txtPersonEmail;
    //TextView txtdepartment,
    // TextView tvWishList;
    ImageView searchClear, searchCamera;
    private static final int MY_PERMISSIONS_REQUEST_READ_EXTERNAL_STORAGE = 29;
    private final int REQ_CODE_SPEECH_INPUT = 100;
    public final static int RECORD_AUDIO_PERMITION = 105;
    public final static int ACCESS_FINE_LOCATION = 101;
    public final static int ACCESS_COARSE_LOCATION = 103;
    CoordinatorLayout coordinatorLayout;
    public boolean isActiveSearchDept = false;
    String checkFilterURL = "", innerFilterUrl = "";
    public Bundle bundle;
    TaskSearch taskSearch;
    public FilterInfoModel filterInfoModel;
//    public ListView filteCategoryOption;
    Res res;
    Menu Mymenu;
    public static MenuItem countMenu;
    static Button notifCount;
    public static int mNotifCount = 0;
    int height, heightsrc;
    List<Filter> liFilter;
    public static String deptId = "0", subDepartment = "", deptSizes = "", priceRange = "", isPriceChanged = "", onlyImage = "0", min = "0.0", max = "0.0", specialOfferGroup = "0", type = "", valueOne = "", valueTwo = "", filterType = "DEPARTMENT", tempSpecialOfferSelected = "", shortCall = "", shortDept = "";
    public static int shortingCheckBoxPosition = 0;
    private TextToSpeech myTTS;
    ItemDescriptionsFragment itemDescriptionsFragment;
    /*Sign in Variable are as below*/

    /**
     * Type of action track on Android WebView
     **/
    int ANCHOR_TYPE;           // 1 HitTestResult for hitting a HTML::a tag
    int EDIT_TEXT_TYPE;        // 9 HitTestResult for hitting an edit text area
    int EMAIL_TYPE;            // 4 HitTestResult for hitting an email address
    int GEO_TYPE;              // 3 HitTestResult for hitting a map address
    int IMAGE_ANCHOR_TYPE;     // 6 HitTestResult for hitting a HTML::a tag which contains HTML::img
    int IMAGE_TYPE;            // 5 HitTestResult for hitting an HTML::img tag
    int PHONE_TYPE;            // 2 HitTestResult for hitting a phone number
    int SRC_ANCHOR_TYPE;       // 7 HitTestResult for hitting a HTML::a tag with src=http
    int SRC_IMAGE_ANCHOR_TYPE; // 8 HitTestResult for hitting a HTML::a tag with src=http + HTML::img
    int UNKNOWN_TYPE;          // 0 Default HitTestResult, where the target is unknown

    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */

    /*
     *
     * Notification veriable declaration.
     * */
    public static String URL;
    String CustomerId;
    private final int MY_DATA_CHECK_CODE = 0;
    private static final int REQ_CODE_SPEECH_INPUT1 = 200;
    public LinearLayout RecHorizontalList;
    TextView txtfooterName1,txtfooterName2,txtfooterName3;
    FooterRecAdapter footerRecAdapter;
    private TwentyOneYear twentyOneYear;
    public boolean isClickedSetting = false;
    public GpsTracker gpsTracker;
    public double current_latitude = 0.0;
    public double current_longitude = 0.0;
    public double miles = 0.0;
    double store_longi = 0.0;
    double store_lat = 0.0;

    public static MainActivityDup getInstance() {
        return MainActivityDup;
    }

    public static BottomNavigationView navigationView;
    ArrayList<String> tempOtherslist;
    OrderHistoryFragment orderHistoryFragment;

    @SuppressLint({"JavascriptInterface", "RestrictedApi", "ResourceType"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_dup);

        FirebaseCrash.setCrashCollectionEnabled(false);

        if (getIntent().getExtras() != null) {
            URL = getIntent().getExtras().getString("URL");
            CustomerId = getIntent().getExtras().getString("CustomerId");
        }
        Intent checkTTSIntent = new Intent();
        checkTTSIntent.setAction(TextToSpeech.Engine.ACTION_CHECK_TTS_DATA);
        startActivityForResult(checkTTSIntent, MY_DATA_CHECK_CODE);
        MainActivityDup = this;

        //we can take Audio permission using below function
//        setAudio_Record_Permition();

//        ?edited by Varun for reward point show below the search bar
        ll_Reward_main= findViewById(R.id.ll_Reward_main);
        tv_points_main= findViewById(R.id.tv_points_main);
        tv_rebate_main= findViewById(R.id.tv_rebate_main);
        tv_rebate_point_main = findViewById(R.id.tv_rebate_point_main);
        tv_Reward_point_main = findViewById(R.id.tv_Reward_point_main);
//        END

        navigationView = findViewById(R.id.navigationView);
        //BottomNavigationView bottomNavigationView = (BottomNavigationView)findViewById(R.id.bottomBar);
        //navigationView.getMenu().removeItem(R.id.action_contactus);
        BottomNavigationViewHelper.removeShiftMode(navigationView);
        RecHorizontalList = findViewById(R.id.RecHorizontalList);
        RecHorizontalList.setBackgroundColor((Color.parseColor(Constant.themeModel.Backgroundcolor)));

        txtfooterName1 = findViewById(R.id.txtheaderbtn1);
        txtfooterName2 = findViewById(R.id.txtheaderbtn2);
        txtfooterName3 = findViewById(R.id.txtheaderbtn3);

        Drawable myDrawableHeader = getResources().getDrawable(R.drawable.rounded_corner_all);
        myDrawableHeader.setColorFilter(new LightingColorFilter(Color.BLACK, Color.parseColor(Constant.themeModel.ThemeColor)));
        txtfooterName1.setBackground(myDrawableHeader);
        txtfooterName2.setBackground(myDrawableHeader);
        txtfooterName3.setBackground(myDrawableHeader);

        // horizontalAdapter=new CustomAdapter(horizontalList);
//        LinearLayoutManager horizontalLayoutManagaer = new LinearLayoutManager(MainActivityDup.this, LinearLayoutManager.HORIZONTAL, false);
//        RecHorizontalList.setLayoutManager(horizontalLayoutManagaer);

        String sturl = Constant.IMG_BASEURL + Constant.LOGO + Constant.STOREID + "/" + Constant.themeModel.StoreLogo;
        Log.e("Log", "STURL=" + sturl);

        coordinatorLayout = findViewById(R.id.coordinator_layout);

        mContent = findViewById(R.id.mContent);
        llFilter = findViewById(R.id.llFilter);
        llsearch = findViewById(R.id.llsearch);

        /*TextView tvShareApp = findViewById(R.id.tv_share_app);
        tvShareApp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                shareApp();
            }
        });
        TextView tvRateApp = findViewById(R.id.tv_rate_app);
        tvRateApp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                launchMarket();
            }
        });*/

        loadOnlyStoreHoursWS();
        onCallGlobalSetup();
//        hidebackbutton();
        Drawable myDrawable = getInstance().getResources().getDrawable(R.drawable.places_ic_search);

        if (Constant.themeModel.ThemeColor.isEmpty()) {
            Constant.themeModel.ThemeColor = "#000000";
        }
        if (!Constant.themeModel.ThemeColor.isEmpty()) {
            myDrawable.setColorFilter(new LightingColorFilter(Color.BLACK, Color.parseColor(Constant.themeModel.ThemeColor)));
        }
        imgHome = findViewById(R.id.imgHome);

        if(Constant.themeModel.StoreLogo != null && !Constant.themeModel.StoreLogo.isEmpty() && !Constant.themeModel.StoreLogo.equals("")){
            String url = Constant.IMG_BASEURL + Constant.LOGO + Constant.STOREID + "/" + Constant.themeModel.StoreLogo;
            Log.e("Log", "Url=" + url);
            Utils.setImageFromUrlHome(this, url, imgHome);
        }else{
            imgHome.setClickable(false);
            imgHome.setVisibility(View.GONE);
        }
//        else{
////            Glide.with(this)
////                    .load(getResources().getDrawable(R.drawable.welcome_default_image))
////                    .fitCenter()
////                    .diskCacheStrategy(DiskCacheStrategy.ALL)
////                    .into(imgHome);
//
//            Glide.with(this).load(getResources().getIdentifier("welcome_default_image", "drawable", this.getPackageName()))
//                    .into(imgHome);
//        }


        llcheckInternet = findViewById(R.id.llcheckInternet);
        llSort = findViewById(R.id.llSort);
        llsortandfilter = findViewById(R.id.llsortandfilter);
        btndept = findViewById(R.id.btndept);
        mContainer = findViewById(R.id.Container);
        cardFragment = new CardFragment();
        deliveryOptionsFragment = new DeliveryOptionsFragment();
        paymentFragment = new PaymentFragment();

        mContainer.clearCache(true);
        mContainer.getSettings().setCacheMode(WebSettings.LOAD_NO_CACHE);
//        mContainer.getSettings().setAppCacheEnabled(false);

        mContainer.setOnScrollChangeListener(new ObservableWebView.OnScrollChangeListener() {
            @Override
            public void onScrollChange(WebView v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {
                Log.e("lOG", "debugii1=" + scrollY);
                Log.e("lOG", "debugii3=" + oldScrollY);
                Log.e("Log", "checkFilterURL=" + checkFilterURL);
                //http://192.168.172.211:888/Home/IndexApp?customerid=189055&storeno=707&sessionid=3g060e0oee1yj2lf1avacirts
                //
                /*if (checkFilterURL.contains("inventory") || innerFilterUrl.contains("InventoryAppList")) {
                    Log.e("log", "db=1");
                    if (scrollY > oldScrollY && scrollY > 0) {
                        Log.e("log", "db=2");
                        if (isExpand1) {
                            Utils.collapse(llsortandfilter, 500, 0);
                            isExpand1 = false;
                            Log.e("log", "db=3");
                        }
                    }
                    if (scrollY < oldScrollY) {
                        Log.e("log", "db=4");
                        if (!llsortandfilter.isShown()) {
                            isExpand1 = false;
                            Log.e("log", "db=5");

                        }
                        if (!isExpand1) {
                            Log.e("log", "db=6");
                            Utils.expand(llsortandfilter, 500, height);
                            isExpand1 = true;
                        }
                    }
                } else {
                    llsortandfilter.setVisibility(View.INVISIBLE);
                }*/
                Log.e("Log", "checkFilterURL=" + checkFilterURL);
                if (checkFilterURL.contains("inventory") || innerFilterUrl.contains("InventoryAppList")) {
                    Log.e("Log", "scrollY=" + scrollY + " >" + "=oldScrollY=" + oldScrollY);
                    if (scrollY > oldScrollY && llsortandfilter.isShown()) {
                        llsortandfilter.setVisibility(View.VISIBLE);
                        //Utils.collapse(llsortandfilter, 500, 0);
                        // Utils.collapseWithAnimation(llsortandfilter);
                    } else if (scrollY < oldScrollY && !llsortandfilter.isShown()) {
                        //Utils.expandWithAnimation(llsortandfilter);
                        //Utils.expand(llsortandfilter, 500, height);
                        llsortandfilter.setVisibility(View.VISIBLE);
                    }
                    Log.e("Log", "Hide-ff1");
                }
            }
        });
        llsortandfilter.setVisibility(View.INVISIBLE);
        llsortandfilter.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                llsortandfilter.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                height = llsortandfilter.getHeight();
            }
        });
        llsearch.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                llsearch.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                // mSearchedt.getHeight(); //height is ready
                heightsrc = llsearch.getHeight();
                Log.e("Log", "heightSearch=" + heightsrc);
            }
        });

        getSupportFragmentManager().addOnBackStackChangedListener(this);
        mToolbar = findViewById(R.id.toolbar);

        // temp hide back button
//        if(getSupportActionBar() != null){
//            getSupportActionBar().setDisplayHomeAsUpEnabled(false);
//            getSupportActionBar().setDisplayShowHomeEnabled(false);
//            getSupportActionBar().setDisplayShowTitleEnabled(false);
//            getSupportActionBar().setHomeButtonEnabled(false);
//        }
        // end

        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//               onBackPressed();
               Log.e("backpressed","mtoolbarclicked");
            }
        });

        //img_microphone = findViewById(R.id.img_microphone);
        //  DrawableCompat.setTint(img_microphone.getDrawable(), Color.parseColor(Constant.themeModel.ThemeColor) /*Utils.getColor(this, *//*656565*//* *//*Color.parseColor(*//* Integer.parseInt(Constant.themeModel.ThemeColor) *//*)*//* *//*R.color.red*//*)*/);
        //img_microphone.setOnClickListener(this);

        searchClear = findViewById(R.id.search_clear);
        searchClear.setOnClickListener(this);
        searchCamera = findViewById(R.id.img_search_camera);
        if (!Constant.themeModel.ThemeColor.isEmpty()) {
            DrawableCompat.setTint(searchCamera.getDrawable(), Color.parseColor(Constant.themeModel.ThemeColor) /*Utils.getColor(this, *//*656565*//* *//*Color.parseColor(*//* Integer.parseInt(Constant.themeModel.ThemeColor) *//*)*//* *//*R.color.red*//*)*/);
        }
        searchCamera.setOnClickListener(this);

        mSearchedt = findViewById(R.id.searchedt);
        mSearchedt.addTextChangedListener(/*searchWatcher */new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.length() > 0) {
                    searchClear.setVisibility(View.VISIBLE);
//                    searchCamera.setVisibility(View.GONE);
                    // img_microphone.setVisibility(View.GONE);
                } else {
                    searchClear.setVisibility(View.GONE);
//                    searchCamera.setVisibility(View.VISIBLE);
                    //img_microphone.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
                if (s.length() >= 3) {
                    onStartSearch(s.toString());
                    tempSpecialOfferSelected = "0";
                    deptId = "0";
                } else {
                    mSearchedt.dismissDropDown();
                }
            }
        });

        mSearchedt.setOnTouchListener(new DrawableClickListener.RightDrawableClickListener(mSearchedt) {
            @Override
            public boolean onDrawableClick() {
                //Toast.makeText(MainActivityDup.getInstance(), "Search is Clicked", Toast.LENGTH_SHORT).show();
                onSearchIconClick();
                //Intent intent = new Intent(MainActivityDup.this, OcrCaptureActivity.class);
                //startActivity(intent);
                return true;
            }
        });

        mSearchedt.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                    onSearchIconClick();
                    return true;
                }
                return false;
            }
        });

        float width = (float) (getResources().getDisplayMetrics().widthPixels / 1.3);

        // for show back button
//        if (getSupportActionBar() != null) {
//            getSupportActionBar().setDisplayShowTitleEnabled(false);
//            getSupportActionBar().setHomeButtonEnabled(false);
//            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
//            getSupportActionBar().setDisplayShowHomeEnabled(true);
//
//        }
        // end

        llFilter.setOnClickListener(this);
        llSort.setOnClickListener(this);
        imgHome.setOnClickListener(this);
        btndept.setOnClickListener(this);

        //loadHomeWebPage();
        /*try {
            JsonParsing();
        } catch (JSONException e) {
            e.printStackTrace();
        }*/
        onSetDrawerMenu();
        Log.e("Log", "Order ID==" + URL);
        Log.e("Log", "customer ID==" + CustomerId);
        navigationView.setBackgroundColor(Color.parseColor(Constant.themeModel.ThemeColor));

        setListeners();
        llsearch.setVisibility(View.GONE);
    }

    private void loadOnlyStoreHoursWS() {
        String Url = Constant.WS_BASE_URL + Constant.GET_DELIVERY_HOURS + "/" +  Constant.STOREID + "/" + "store";
        TaskStoreDeliveryHours taskStoreDeliveryHours = new TaskStoreDeliveryHours(this,MainActivityDup.this,"");
        taskStoreDeliveryHours.execute(Url);
    }

    @Override
    public void onGetStoreDeliveryHoursResult(List<StoreHour> storeHourList) {
        if(storeHourList != null && storeHourList.size()>0){
            Constant.liOnlyStoreHour = storeHourList;
        }
    }

    @Override
    public boolean onSupportNavigateUp() {
//        onBackPressed();
        Log.e("backpressed","onSupportNavigate");
        return true;
    }


    public void callResume() {
        String token = FirebaseInstanceId.getInstance().getToken();
        Log.e("Log", "token=" + token);
        if (!Constant.themeModel.ThemeColor.isEmpty()) {
            mToolbar.setBackgroundColor(Color.parseColor(Constant.themeModel.ThemeColor));
            llsearch.setBackgroundColor(Color.parseColor(Constant.themeModel.ThemeColor));
        }
        if (URL == null) {
            if (getIntent().getExtras() != null) {
                URL = getIntent().getExtras().getString("URL");
                CustomerId = getIntent().getExtras().getString("CustomerId");
            }
        }

        //edited for checking user enable gps or not edited on - 22th march 2021
        if(isClickedSetting){
            getLocation();
            isClickedSetting = false;
        }
        //end *********
    }

    private void getLocation() {

        gpsTracker = new GpsTracker(MainActivityDup.this);
        if(gpsTracker.canGetLocation()){

            //for current location
            double latitude = gpsTracker.getLatitude();
            double longitude = gpsTracker.getLongitude();
            Log.e("latitude",String.valueOf(latitude));
            Log.e("longitude",String.valueOf(longitude));
//
            current_latitude = latitude;
            current_longitude = longitude;
            //end for current location


            //get store address from contact info ws response
            String state_Zipvar = "";
            String add = "";
            String fullAddress = "";

            if (Constant.contatInfo.getAddress() != null && !Constant.contatInfo.getAddress().isEmpty()) {
                add = Constant.contatInfo.getAddress();
            }

            if (Constant.contatInfo.getCity() != null && !Constant.contatInfo.getCity().isEmpty()) {
                add = add + "\n" + Constant.contatInfo.getCity();
            }

            if (Constant.contatInfo.getState() != null && !Constant.contatInfo.getState().isEmpty()) {
                state_Zipvar = Constant.contatInfo.getState();
            }

            if (Constant.contatInfo.getZip() != null && !Constant.contatInfo.getZip().isEmpty()) {
//
                state_Zipvar = state_Zipvar + " " + Constant.contatInfo.getZip();
                add = add + ", " + state_Zipvar;
            }
            fullAddress = add;

            getLatandLongFromAddress(fullAddress);

        }else{
            gpsTracker.showSettingsAlert();
        }
    }

    public void getLatandLongFromAddress(String address) {
        Geocoder geoCoder = new Geocoder(MainActivityDup.this);
        if (address != null && !address.isEmpty()) {
            try {
                List<Address> addressList = geoCoder.getFromLocationName(address, 1);
                if (addressList != null && addressList.size() > 0) {
                    store_lat = addressList.get(0).getLatitude();
                    store_longi = addressList.get(0).getLongitude();

                    getDistanceOfStoreLoaction(store_lat,store_longi);
                }
            } catch (Exception e) {
                e.printStackTrace();
            } // end catch
        } // end if
    } // end c


    private void getDistanceOfStoreLoaction(double store_lat, double store_longi) {

        Location loc1_currentloc = new Location("");
        loc1_currentloc.setLatitude(current_latitude);
        loc1_currentloc.setLongitude(current_longitude);

        Location loc2_storeloc = new Location("");
        loc2_storeloc.setLatitude(store_lat);
        loc2_storeloc.setLongitude(store_longi);

        float distanceInMeters = loc1_currentloc.distanceTo(loc2_storeloc);
        miles = distanceInMeters * 0.00062137119;
        Log.e("miles:",""+miles);

        HomepageFragment.getInstance().setMiles(miles);

    }


    private void setListeners() {
        navigationView.setOnNavigationItemSelectedListener(this);
    }

    public void CheckUserLogin() {
        // loadHomeWebPage();
        Constant.AppPref = getSharedPreferences(Constant.PrefName, MODE_PRIVATE);
        if (!Constant.BAR_IMG_DISP) {
            //Log.e("Log","Constant.AppPref="+Constant.AppPref);

            //            Edited by Varun for guest login
//            Here we check user email and password and if the email is guest is not
//           if(Constant.isFromLogin){
            Constant.ISguest = Constant.AppPref.getBoolean("ISguest",false);
            if (!ISguest) {
                if (!Constant.AppPref.getString("email", "").isEmpty() && !Constant.AppPref.getString("password", "").isEmpty()) {
                    String Url = Constant.WS_BASE_URL + Constant.CHECK_PASSWORD + Constant.AppPref.getString("email", "") + "/" + Constant.AppPref.getString("password", "") + "/" + Constant.STOREID;
                    new Async_getCommonService(MainActivityDup.this, Url, "comefromLogin").execute();
                }else{
                    loadHomeWebPage();
                }
            }else {
                loadHomeWebPage();
            }
            Constant.BAR_IMG_DISP = false;
        }
    }

    /**
     * Initializing Drawer Menu(footer menu)
     **/
    public void onSetDrawerMenu() {

        if(Constant.TopHeaderMenuList != null && Constant.TopHeaderMenuList.size() > 0){
            RecHorizontalList.setVisibility(View.VISIBLE);

            for(int i=0; i<Constant.TopHeaderMenuList.size(); i++){
                if(Constant.TopHeaderMenuList.get(i).PageName.equals("Departments")){
                    txtfooterName1.setVisibility(View.VISIBLE);
                    txtfooterName1.setText(Constant.TopHeaderMenuList.get(i).PageName);
//                    txtfooterName2.setVisibility(View.GONE);
//                    txtfooterName3.setVisibility(View.GONE);
                }else if(Constant.TopHeaderMenuList.get(i).PageName.equals("Event Calendar")){
                    txtfooterName2.setVisibility(View.VISIBLE);
                    txtfooterName2.setText(Constant.TopHeaderMenuList.get(i).PageName);
//                    txtfooterName1.setVisibility(View.GONE);
//                    txtfooterName3.setVisibility(View.GONE);
                }else if(Constant.TopHeaderMenuList.get(i).PageTitle.equals("DisplayStoreHours")){
                    txtfooterName3.setVisibility(View.VISIBLE);
                    txtfooterName3.setText(Constant.TopHeaderMenuList.get(i).PageName);
//                    txtfooterName1.setVisibility(View.GONE);
//                    txtfooterName2.setVisibility(View.GONE);
                }
            }

            txtfooterName1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    MainActivityDup.getInstance().isActiveSearchDept = false;
                    MainActivityDup.getInstance().callFilterFragment();
                }
            });

            txtfooterName2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    MainActivityDup.getInstance().LoadWebVew(Constant.URL_PAGE_EVENTS + Constant.STOREID);
                }
            });

            txtfooterName3.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    MainActivityDup.getInstance().loadStoreandDeliveryFragment(txtfooterName3.getText().toString());
                }
            });

        }else{
            RecHorizontalList.setVisibility(View.GONE);
        }

//        footerRecAdapter = new FooterRecAdapter(this, Constant.TopHeaderMenuList);
//        RecHorizontalList.setAdapter(footerRecAdapter);
    }

    /**
     * Text
     **/
    public void imageToTextResult(String textFromImage) {
        Constant.BAR_IMG_DISP = true;

        //onStartSearch(textFromImage);
        Log.e("Log", "Searched text=" + textFromImage);
        String customerId = "0";
        if (UserModel.Cust_mst_ID != null)
            customerId = UserModel.Cust_mst_ID;
        else
            customerId = "0";

        getInstance().mContainer.removeAllViews();
        getInstance().LoadWebVew(Constant.URL + "/inventory/inventoryapp"
                + "?customerid=" + customerId
                + "&sessionid=" + DeviceInfo.getDeviceId(getInstance()) + "0011"
                + "&storeno=" + Constant.STOREID
                + "&deptid=" + "0"
                + "&subdeptid=" + "0"
                + "&type=" + "allitem"
                + "&filtertext=" + textFromImage);
        mSearchedt.setText(textFromImage);
    }

    /**
     * Call : Search web service
     **/
    public void onStartSearch(String search) {
        String url;
        url = Constant.WS_BASE_URL + Constant.GET_SEARCH_DATA + "/" + Constant.STOREID + "/" + search;
        taskSearch = new TaskSearch(this);
        taskSearch.execute(url);
    }

    private void hidekeyboard() {

        View view = this.getCurrentFocus();

        if (view != null) {
            InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            assert imm != null;
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }

    }
    /**
     * Call : Search when Search ICON click
     **/
    public void onSearchIconClick() {
        //http://192.168.172.211:888/Inventory/AndroidSearchItemDescription?CustomerId=189013&Storeno=707&sessionid=&ItemId=72081591053&Item=CABO WABO ANEJO 20(.750L)
        mSearchedt.dismissDropDown();
        //callClearFilter();
        hidekeyboard();
        searchText = (mSearchedt.getText().toString().isEmpty()) ? "" : mSearchedt.getText().toString();
        try {
            searchText = URLEncoder.encode(searchText, "utf-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        mSearchedt.clearFocus();
        Utils.hideKeyboard();
        if (searchText.length() == 0) {
            tempSpecialOfferSelected = "";
            deptId = "";
            subDepartment = "";
            deptSizes = "";
            priceRange = "";
            FilterItemAdapter_dup.tempPosition = -1;
        }

        String customerId = "0";
        if (UserModel.Cust_mst_ID != null)
            customerId = UserModel.Cust_mst_ID;
        else
            customerId = "0";

        isSearchicon = true;

        loadViewAllFragment("0","0","0","0","0","0","0", searchText, "");


//        getInstance().mContainer.removeAllViews();
//        showHomePage();
//        getInstance().LoadWebVew(Constant.URL + "/inventory/inventoryapp"
//                + "?customerid=" + customerId
//                + "&sessionid=" + DeviceInfo.getDeviceId(getInstance()) + "0011"
//                + "&storeno=" + Constant.STOREID
//                + "&deptid=" + "0"
//                + "&subdeptid=" + "0"
//                + "&type=" + "allitem"
//                + "&filtertext=" + searchText); //&type=""
    }

    /**
     * Result : Search list
     **/
    @Override
    public void onSearchResult(final List<GetSearchData> liSearchData) {

        String result[] = new String[liSearchData.size()];
        for (int i = 0; i < liSearchData.size(); i++) {
            result[i] = liSearchData.get(i).getFilterDescription();
        }
        //        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
//                android.R.layout.simple_spinner_dropdown_item, result);

//        ****************** Edited by Varun on 17 Aug 2022 *************************

        final ArrayAdapter<GetSearchData> adapter = new CustomizedSpinnerAdapter(
                this, android.R.layout.simple_spinner_item,
                liSearchData);

//        ******************* END *************************

        mSearchedt.setAdapter(adapter);
        mSearchedt.setThreshold(1);
        //mSearchedt.setDropDownAnchor(llsearch.getId());
        if(!mSearchedt.isPopupShowing()) {
            mSearchedt.showDropDown();
        }

        //http://192.168.172.211:888/Inventory/AndroidSearchItemDescription?CustomerId=189013&Storeno=707&sessionid=&ItemId=72081591053&Item=CABO WABO ANEJO 20(.750L)

        mSearchedt.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                mSearchedt.dismissDropDown();
                Utils.hideKeyboard();
                mSearchedt.clearFocus();
                mSearchedt.setText("");

                String customerId = "0";
                if (UserModel.Cust_mst_ID != null)
                    customerId = UserModel.Cust_mst_ID;
                else
                    customerId = "0";

                String itemsku = liSearchData.get(i).getFilterID();

                if(!itemsku.equals("")&&itemsku != null){
                    loadItemDescriptionFragment(itemsku, "fragment");
                }

                //commented code to load webpage of product detail page
//                getInstance().mContainer.removeAllViews();
//                /*MainActivityDup.getInstance().LoadWebVew(Constant.URL + "inventory/AndroidSearchItemDescription"
//                        + "?CustomerId=" + customerId
//                        + "&Storeno=" + Constant.STOREID
//                        + "&sessionid=" + *//*"0"*//*DeviceInfo.getDeviceId(MainActivityDup.getInstance()) + "0011"
//                        + "&ItemId=" *//*+ "0" *//* + liSearchData.get(i).getFilterID()
//                        + "&Item=" + liSearchData.get(i).getFilterDescription());*/
//                //http://192.168.172.211:888/Inventory/ItemDescriptionApp?customerID=188971&storeno=707
//                showHomePage();
//                getInstance().LoadWebVew(Constant.URL + "inventory/AndroidSearchItemDescription"
//                        + "?CustomerId=" + customerId
//                        + "&Storeno=" + Constant.STOREID
//                        + "&sessionid=" + /*"0"*/DeviceInfo.getDeviceId(getInstance()) + "0011"
//                        + "&ItemId=" /*+ "0" */ + liSearchData.get(i).getFilterID()
//                        + "&Item=" + liSearchData.get(i).getFilterDescription());
//
//                Log.e("Log", "SearchedURL=" + Constant.URL + "inventory/AndroidSearchItemDescription"
//                        + "?CustomerId=" + customerId
//                        + "&Storeno=" + Constant.STOREID
//                        + "&sessionid=" + /*"0"*/DeviceInfo.getDeviceId(getInstance()) + "0011"
//                        + "&ItemId=" /*+ "0" */ + liSearchData.get(i).getFilterID()
//                        + "&Item=" + liSearchData.get(i).getFilterDescription());
            }
        });
    }

    /**
     * Call : Special Offers Filter web service
     **/
    public static void callOfferFilter() {
        //http://192.168.172.211:889/WebStoreRestService.svc/GetHomePageFilter/707
        String filterUrl;
        filterUrl = Constant.WS_BASE_URL + Constant.FILTER_HOME_PAGE + Constant.STOREID;
        TaskFilterHomePage taskFilterHomePage = new TaskFilterHomePage(getInstance());
        taskFilterHomePage.execute(filterUrl);
    }

    @Override
    public void onFilterHomePageResult(List<FilterHomePage> liFilterHomePages) {
        this.liFilterHomePages = liFilterHomePages;
//        if (tempSpecialOfferSelected.isEmpty()) {
//            callFilter(deptId, subDepartment, deptSizes);
//        } else {
//            callSpecialOfferFilter(deptId, subDepartment, deptSizes, specialOfferGroup, type, valueOne, valueTwo);
//        }

        if(!type.isEmpty()){
            callSpecialOfferFilter(deptId, subDepartment, deptSizes, specialOfferGroup, MainActivityDup.type, valueOne, valueTwo, MainActivityDup.blockDisplayedText);
        }
    }

    /**
     * Call : Filter web service
     **/
    public static void callFilter(String deptId, String styleId, String sizeId) {

        //GetFilterByDepartmentStyleSizePrice_v2/{storeNo}/{deptId}/{styleId}/{sizeId}/{pricerange}/{descGroupId}/{filterType}/{IsItemWithImages}/{StartDiscountRange}/{EndDiscountRange}
        //http://192.168.172.211:889/WebStoreRestService.svc/GetFilterByDepartmentStyleSizePrice_v2/707/1846/0/0/0/0/DEPARTMENT/0/0/0
//        Log.e("deptId",deptId);
//        Log.e("styleId",styleId);
//        Log.e("sizeId",sizeId);

        deptId = (deptId.isEmpty()) ? "0" : deptId;
        if(deptId.equals("0")){
            deptId = String.valueOf(Constant.clickedDeptIdfromhome);
        }
        styleId = (styleId.isEmpty()) ? "0" : styleId;
        sizeId = (sizeId.isEmpty()) ? "0" : sizeId;
        String tempMin = (min.isEmpty()) ? "0.00" : min;
        String tempMax = (max.isEmpty()) ? "0.00" : max;
        String tempPriceRange = tempMin + ";" + tempMax;
        //String tempFilterType =
        //Toast.makeText(MainActivityDup, "dept id : " + deptId, Toast.LENGTH_SHORT).show();

        String filterUrl;
        filterUrl = Constant.WS_BASE_URL + Constant.FILTER_URL + Constant.STOREID + "/"
                + deptId /*"1846"*/ + "/"          /*deptId*/
                + styleId   /*"0"*/ + "/"             /*styleId*/
                + sizeId    /*"0"*/ + "/"             /*sizeId*/
                + "0" + "/"             /*pricerange*/
                + "0" + "/"             /*descGroupId*/
                + "DEPARTMENT" + "/"    /*filterType*/
                + "0" + "/"             /*IsItemWithImages*/
                + "0" + "/"             /*StartDiscountRange*/
                + "0"                   /*EndDiscountRange*/;
        //}

        TaskFilter taskFilter = new TaskFilter(getInstance());
        taskFilter.execute(filterUrl);
        Log.e("Filter", "callFilter: " + filterUrl);
    }

    /**
     * Call : Filter web service
     **/
    public static void callSpecialOfferFilter(String deptId, String styleId, String sizeId, String specialOfferGroup, String type, String startDiscount, String endDiscount, String blockDisplayedText) {

        //GetFilterByDepartmentStyleSizePrice_v2/{storeNo}/{deptId}/{styleId}/{sizeId}/{pricerange}/{descGroupId}/{filterType}/{IsItemWithImages}/{StartDiscountRange}/{EndDiscountRange}
        //http://192.168.172.211:889/WebStoreRestService.svc/GetFilterByDepartmentStyleSizePrice_v2/707/1846/0/0/0/0/DEPARTMENT/0/0/0
        deptId = (deptId.isEmpty()) ? "0" : deptId;

        if(deptId.equals("0")){
            deptId = String.valueOf(Constant.clickedDeptIdfromhome);
        }

        styleId = (styleId.isEmpty()) ? "0" : styleId;
        sizeId = (sizeId.isEmpty()) ? "0" : sizeId;
        String tempMin = (min.isEmpty()) ? "0.00" : min;
        String tempMax = (max.isEmpty()) ? "0.00" : max;
        String tempPriceRange = tempMin + ";" + tempMax;
        priceRange = (priceRange.isEmpty()) ? tempPriceRange : priceRange;

        /*if (FilterItemAdapter.tempPosition < 0){
            if (!MainActivity.etMaxPrice.getText().toString().isEmpty() && !MainActivity.etMinPrice.getText().toString().isEmpty()){
                String tempMins = MainActivity.etMinPrice.getText().toString();
                String tempMaxs = MainActivity.etMaxPrice.getText().toString();
                priceRange = tempMins + ";" + tempMaxs;
                tempPriceRange = MainActivity.priceRange;
                MainActivity.isPriceChanged = "Y";
            }
        }else{*/
        //priceRange = (priceRange.isEmpty()) ? "0" : priceRange;
        //}

        specialOfferGroup = (specialOfferGroup == null) ? "" : specialOfferGroup;
        type = (type == null) ? "" : type;
        startDiscount = (startDiscount == null) ? "" : startDiscount;
        endDiscount = (endDiscount == null) ? "" : endDiscount;

        specialOfferGroup = (specialOfferGroup.isEmpty()) ? "0" : specialOfferGroup;
        type = (type.isEmpty()) ? "DEPARTMENT" : type;
        //String tempFilterType =
        //Toast.makeText(mainActivity, "dept id : " + deptId, Toast.LENGTH_SHORT).show();

        if (!specialOfferGroup.equals("0")) {
            type = "specialoffer";
        }

        if (!startDiscount.isEmpty() && !endDiscount.isEmpty() && !startDiscount.equals("0") && !endDiscount.equals("0")) {
            type = "promotion";
        }

        MainActivityDup.type = type;

//        if(type.equals("Promotion 1")||type.equals("Promotion 2")){
//            type = "promotion";
//        }
//
//        if(type.equals("SpecialOffer 1")||type.equals("SpecialOffer 2")|| type.equals("SpecialOffer 3")||type.equals("SpecialOffer 4")){
//            type = "specialoffer";
//        }

        endDiscount = (endDiscount.isEmpty()) ? "0" : endDiscount;
        startDiscount = (startDiscount.isEmpty()) ? "0" : startDiscount;

        String filterUrl;
        filterUrl = Constant.WS_BASE_URL + Constant.FILTER_URL + Constant.STOREID + "/"
                + deptId /*"1846"*/ + "/"          /*deptId*/
                + styleId   /*"0"*/ + "/"             /*styleId*/
                + sizeId    /*"0"*/ + "/"             /*sizeId*/
                + MainActivityDup.priceRange + "/"             /*pricerange*/
                + specialOfferGroup /*"0"*/ + "/"  /*descGroupId*/
                + type + "/"    /*filterType*/
                + "0" + "/"             /*IsItemWithImages*/
                + startDiscount + "/"             /*StartDiscountRange*/
                + endDiscount                   /*EndDiscountRange*/;
        //}

        TaskFilter taskFilter = new TaskFilter(MainActivityDup.getInstance());
        Log.e("Filter", "callFilter: " + taskFilter);
        taskFilter.execute(filterUrl);
    }

    /**
     * Call : Filter For clear all data web service
     **/
    public static void callClearFilter() {

        //GetFilterByDepartmentStyleSizePrice_v2/{storeNo}/{deptId}/{styleId}/{sizeId}/{pricerange}/{descGroupId}/{filterType}/{IsItemWithImages}/{StartDiscountRange}/{EndDiscountRange}
        //http://192.168.172.211:889/WebStoreRestService.svc/GetFilterByDepartmentStyleSizePrice_v2/707/1846/0/0/0/0/DEPARTMENT/0/0/0
        String filterUrl;
        filterUrl = Constant.WS_BASE_URL + Constant.FILTER_URL + Constant.STOREID + "/0/0/0/0;1000/0/allItem/0/0/0"                   /*EndDiscountRange*/;
        TaskFilter taskFilter = new TaskFilter(getInstance());

        onlyImage = "0";
        tempSpecialOfferSelected = "0";
        deptId = "0";
        type = "allItem";
        specialOfferGroup = "0";
        valueTwo = "";
        valueOne = "";
        taskFilter.execute(filterUrl);
    }

    /**
     * Result Filter
     **/
    @Override
    public void onFilterResult(List<Filter> liFilter) {
        this.liFilter = liFilter;

        String url;

        url = Constant.WS_BASE_URL + Constant.GET_FILTER_INFO
                + "/" + Constant.STOREID;

        TaskFilterInfo taskFilterInfo = new TaskFilterInfo(this,this,"");
        taskFilterInfo.execute(url);
    }

    @Override
    public void onTaskFilterInfoResult(FilterInfoModel filterInfoModel) {
        this.filterInfoModel = filterInfoModel;
        showFilterDialog();

    }

    @Override
    public void onTaskFilterInfoStyleResult(FilterInfoModel filterInfoModel) {

        this.filterInfoModel = filterInfoModel;

        callFilterDetailFragment();
    }

    public static LinearLayout llPriceRange;
    //public static EditText etMinPrice;
//public static EditText etMaxPrice;
    public static EditTextCloseEvent etMinPrice, etMaxPrice;
    public static boolean etMinIsFocused;
    public static boolean etMaxIsFocused;

    /**
     * Showing Filter Dialog
     **/
    @SuppressLint({"WrongViewCast", "ClickableViewAccessibility"})
    public void showFilterDialog() {

        if(!mSearchedt.getText().toString().isEmpty()){
            mSearchedt.setText("");
        }

        isFileterDialogOpen = true;
     /*   WindowManager wm = (WindowManager) this.getSystemService(Context.WINDOW_SERVICE);
        int width = wm.getDefaultDisplay().getWidth();
        int height = wm.getDefaultDisplay().getHeight();*/
        if (filterDialog == null) {
            filterDialog = new Dialog(this, R.style.DialogSlideAnim);
        }
        View view = LayoutInflater.from(this).inflate(R.layout.filter_dialog, null);
        TextView txtclear = view.findViewById(R.id.txtclear);

        ListView filteCategory = view.findViewById(R.id.filteCategory);
        //filteCategoryOption = view.findViewById(R.id.filteCategoryOption);
        ImageView imgClose = view.findViewById(R.id.btnClose);
        Button btnApply = view.findViewById(R.id.btnApply);

        RecyclerView rvSelectedItems = view.findViewById(R.id.rv_selected_items_filter_dialog);
        //Set recyclerView in GridView
        RecyclerView.LayoutManager layoutManagers = new LinearLayoutManager(this);
        rvSelectedItems.setLayoutManager(layoutManagers);
        rvSelectedItems.setHasFixedSize(true);

        FilterSelectedItems selectedItems = new FilterSelectedItems();

        /*selectedItems.setName("Nidhi");
        selectedItems.setName("Vasant");
        selectedItems.setName("Nikhil");
        liFilterSelectedItems.add(selectedItems);*/

        /*for (int i = 0; i < 10; i++) {
            FilterSelectedItems FSI=new FilterSelectedItems();
            FSI.setName("Vasant " + i);
            liFilterSelectedItems.add(FSI);
        }*/

        /*if (liFilterSelectedItems != null)*/
        filterSelectedItemAdapter_dup = new FilterSelectedItemAdapter_dup(liFilterSelectedItems);
        rvSelectedItems.setAdapter(filterSelectedItemAdapter_dup);
        filterSelectedItemAdapter_dup.notifyDataSetChanged();

        llPriceRange = view.findViewById(R.id.ll_root_price_range_filter_dialog);
        etMinPrice = view.findViewById(R.id.et_min_price_filter_dialog);

        etMinPrice.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                etMinIsFocused = true;
                etMaxIsFocused = false;
            }

            @Override
            public void afterTextChanged(Editable s) {

                if (s.length() > 0) {
                    FilterItemAdapter_dup.tempPosition = -1;
                    FilterAdapter_dup = new FilterAdapter_dup(MainActivityDup.this, Constant.FILTERlIST, liFilter, liFilterHomePages);
                    getInstance().rvDeptList.setAdapter(getInstance().filterItemAdapter_dup);
                    getInstance().filterItemAdapter_dup.notifyDataSetChanged();
                }
            }
        });

        etMaxPrice = view.findViewById(R.id.et_max_price_filter_dialog);

        etMinPrice.setKeyImeChangeListener(new EditTextCloseEvent.KeyImeChange() {
            @Override
            public void onKeyIme(int keyCode, KeyEvent event) {
                // All keypresses with the keyboard open will come through here!
                // You could also bubble up the true/false if you wanted
                // to disable propagation.
            }
        });

        etMaxPrice.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                etMaxIsFocused = true;
                etMinIsFocused = false;
            }

            @Override
            public void afterTextChanged(Editable s) {

                if (s.length() > 0) {
                    FilterItemAdapter_dup.tempPosition = -1;
                    FilterAdapter_dup = new FilterAdapter_dup(MainActivityDup.this, Constant.FILTERlIST, liFilter, liFilterHomePages);
                    getInstance().rvDeptList.setAdapter(getInstance().filterItemAdapter_dup);
                    getInstance().filterItemAdapter_dup.notifyDataSetChanged();
                }
            }
        });

        btnApply.setBackgroundColor(Color.parseColor(Constant.themeModel.ThemeColor));

        Constant.FILTERlIST.clear();/*
        Constant.FILTERlIST.get(0).isActive = true;
        Constant.FILTERlIST.add(new FilterModel(true));*/
        FilterModel filterm = new FilterModel("Special Offers");
        //filterm.isActive = true;
        Constant.FILTERlIST.add(filterm);
        //Constant.FILTERlIST.add(new FilterModel("Special Offers"));
        Constant.FILTERlIST.add(new FilterModel("Departments"));


        if(filterInfoModel != null) {
            if(filterInfoModel.getStyleDisplay() != null) {
                if (filterInfoModel.getStyleDisplay().equals("Y")) {
                    Constant.FILTERlIST.add(new FilterModel("Sub Departments"));
                }
            }

            if(filterInfoModel.getSizeDisplay() != null && filterInfoModel.getSizeDisplay().equals("Y")) {
                if(liFilter.get(0).getLstSize() != null && liFilter.get(0).getLstSize().size() > 0){
                    Constant.FILTERlIST.add(new FilterModel("Sizes"));
                }
            }


            if(filterInfoModel.getItemWithImageOnlyDisplay() != null) {
                if (filterInfoModel.getItemWithImageOnlyDisplay()) {
                    Constant.FILTERlIST.add(new FilterModel("Image"));
                }
            }

            if(filterInfoModel.getSearchByPricePoint() != null) {
                if (filterInfoModel.getSearchByPricePoint()) {
                    Constant.FILTERlIST.add(new FilterModel("Price"));
                }
            }

//            Constant.FILTERlIST.add(new FilterModel("Sizes"));
//            Constant.FILTERlIST.add(new FilterModel("Image"));
//            Constant.FILTERlIST.add(new FilterModel("Price"));
        }

        FilterAdapter_dup = new FilterAdapter_dup(MainActivityDup.this, Constant.FILTERlIST, liFilter, liFilterHomePages);

        filteCategory.setAdapter(FilterAdapter_dup);

        rvDeptList = view.findViewById(R.id.rvFilterDepartment);
        //Set recyclerView in GridView
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        rvDeptList.setLayoutManager(layoutManager);
        rvDeptList.setHasFixedSize(true);

        //FilterItemAdapter filterItemAdapter = new FilterItemAdapter(this,this,liFilter,0);
        /*FilterHomePage fhp=new FilterHomePage();
        fhp.setBlockDisplaytext("All Items");
        liFilterHomePages.add(0,fhp);*/

        if (liFilter != null && !liFilter.get(0).getLstDepartment().get(0).getDeptDesc().equals("All")) {
            if (liFilter.get(0).getLstDepartment() != null) {
                LstDepartment department = new LstDepartment();
                department.setDeptDesc("All");
                department.setDeptId(0);
                department.setInvCount(0);
                liFilter.get(0).getLstDepartment().add(0, department);
            }

            if(liFilter.get(0).getLstSize() != null && !liFilter.get(0).getLstSize().get(0).getSizeName().equals("Select All")){
                LstSize lstSize = new LstSize();
                lstSize.setSizeName("Select All");
                lstSize.setDeptid(0);
                lstSize.setSizeId(0);
                lstSize.setSizeCount(0);
                lstSize.setChecked(true);
                liFilter.get(0).getLstSize().add(0, lstSize);
            }

        }

        filterItemAdapter_dup = new FilterItemAdapter_dup(this, liFilterHomePages, liFilter/*.get(0).getLstDepartment()*/, filterDepartmentPosition);
        rvDeptList.setAdapter(filterItemAdapter_dup);
        filterItemAdapter_dup.notifyDataSetChanged();


        //filterChoiceAdapter = new FilterChoiceAdapter(MainActivityDup.this, liFilter, liFilter.get(0).getLstDepartment().size(), 0);
        //filteCategoryOption.setAdapter(filterChoiceAdapter);
        WindowManager.LayoutParams params = filterDialog.getWindow().getAttributes();
        params.width = WindowManager.LayoutParams.MATCH_PARENT;
        params.height = WindowManager.LayoutParams.MATCH_PARENT;

        filterDialog.setContentView(view);
        filterDialog.getWindow().setGravity(Gravity.BOTTOM);
        btnApply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                isFileterDialogOpen = false;
                filterDialog.dismiss();
                deptId = (deptId != null && deptId.isEmpty()) ? "0" : deptId;
                subDepartment = (subDepartment != null && subDepartment.isEmpty()) ? "0" : subDepartment;
                deptSizes = (deptSizes != null && deptSizes.isEmpty()) ? "0" : deptSizes;
                if (FilterItemAdapter_dup.tempPosition < 0) {
                    if (!etMaxPrice.getText().toString().isEmpty() && !etMinPrice.getText().toString().isEmpty()) {
                        String tempMins = etMinPrice.getText().toString();
                        String tempMaxs = etMaxPrice.getText().toString();
                        priceRange = tempMins + ";" + tempMaxs;
                        //tempPriceRange = MainActivityDup.priceRange;

                        isPriceChanged = "Y";
                    }
                } else {
                    if (priceRange.equals("Select All")) {
                        priceRange = min + ";" + max;
                    } else {
                        priceRange = (priceRange.isEmpty() || priceRange.equals("0")) ? min + ";" + max : priceRange;
                    }
                }
                isPriceChanged = (isPriceChanged.isEmpty()) ? "N" : isPriceChanged;
                onlyImage = (onlyImage.isEmpty()) ? "0" : onlyImage;
                type = (type.isEmpty()) ? "" : type;
                valueOne = (valueOne != null && valueOne.isEmpty()) ? "" : valueOne;
                valueTwo = (valueTwo != null && valueTwo.isEmpty()) ? "" : valueTwo;

                if (specialOfferGroup != null && !specialOfferGroup.equals("0") && !specialOfferGroup.isEmpty()) {
                    type = "specialoffer";
//                    valueOne = specialOfferGroup;
                }

//                if (valueOne != null && !valueOne.isEmpty() && !valueTwo.isEmpty()) {
//                    type = "promotion";
//                }

                shortCall = (shortCall != null && shortCall.isEmpty()) ? "price" : shortCall;
                shortDept = (shortDept != null && shortDept.isEmpty()) ? "Asc" : shortDept;


                if(type.equals("promotion")){
                    if(blockDisplayedText.equalsIgnoreCase("Items On Promotions")){
                        ViewAllFragment.getInstance().loadItemListingWS(type,true);
                    }else{
                        getblockActualtextFromBlocklistWS(blockDisplayedText);
                    }
                }else if(type.equals("specialoffer")){
                    if(blockDisplayedText.equalsIgnoreCase("Special Offers")){
                        ViewAllFragment.getInstance().loadItemListingWS(type,true);
                    }else{
                        getblockActualtextFromBlocklistWS(blockDisplayedText);
                    }
                }else{
                    ViewAllFragment.getInstance().loadItemListingWS(type,true);
                }


//                loadFilteredWebPage(deptId, subDepartment, deptSizes, priceRange, isPriceChanged, onlyImage, type, valueOne, valueTwo, shortCall, shortDept);

            }
        });
        imgClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //FilterAdapter_dup.isActivated(0);
                clearFilterSelection();
                isFileterDialogOpen = false;
                filterDialog.dismiss();
            }
        });
        txtclear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                clearFilterSelection();
                callClearFilter();
                FilterAdapter_dup.notifyDataSetChanged();
                filterItemAdapter_dup.notifyDataSetChanged();
                filterSelectedItemAdapter_dup.notifyDataSetChanged();
            }
        });
        if (!filterDialog.isShowing())
            filterDialog.show();

    }

    public void clearFilterSelection() {

        if (liFilterHomePages != null) {
            for (int i = 0; i < liFilterHomePages.size(); i++) {
                liFilterHomePages.get(i).setChecked(false);
                tempSpecialOfferSelected = "0";
            }
        }

        if (liFilter != null) {
            for (int i = 0; i < liFilter.size(); i++) {
                liFilter.get(0).setChecked(false);
                if (liFilter.get(0).getLstDepartment() != null) {
                    for (int j = 0; j < liFilter.get(0).getLstDepartment().size(); j++) {
                        liFilter.get(0).getLstDepartment().get(j).setChecked(false);
                        deptId = "0";
                    }
                }
                if (liFilter.get(0).getLstStyle() != null) {
                    for (int k = 0; k < liFilter.get(0).getLstStyle().size(); k++) {
                        liFilter.get(0).getLstStyle().get(k).setChecked(false);
                        subDepartment = "";
                    }
                }

                if (liFilter.get(0).getLstSize() != null) {
                    for (int l = 0; l < liFilter.get(0).getLstSize().size(); l++) {
                        liFilter.get(0).getLstSize().get(l).setChecked(false);
                        deptSizes = "";
                    }
                }
            }
        }
        onlyImage = "0";
        filterDepartmentPosition = 0;
        //FilterAdapter_dup.isActivated(0);
        FilterItemAdapter_dup.tempPosition = -1;
        FilterItemAdapter_dup.priceRange = "";
        MainActivityDup.priceRange = "";
        shortCall = "";
        shortDept = "";
    }

    private void getblockActualtextFromBlocklistWS(String blockDisplayedText) {

        String type = "";

        if(!blockDisplayedText.isEmpty()){

            for(int i=0; i<Constant.BLOCKDATAFRONTLIST.size() ; i++){
                if(blockDisplayedText.equals(Constant.BLOCKDATAFRONTLIST.get(i).getBlockDisplaytext())){
                    type = Constant.BLOCKDATAFRONTLIST.get(i).getBlockActualtext();
                }
            }

            if(!type.isEmpty()) {
                ViewAllFragment.getInstance().loadItemListingWS(type,true);
            }
        }

    }

    public static void loadFilteredWebPage(String departmentId
            , String subDepartmentId, String sizes, String priceRange, String isPriceChanged, String isImage, String specialOffers, String valueOne, String valueTwo, String sortCol, String sortStatus) {
        //http://192.168.172.211:888/inventory/inventoryapp?storeno=707&deptid=1846&subdeptid=54886,54881,&Sizeid=1987,2045&pricerange=15;25&haspriceChange=Y&ItemWithImage=0
        //http://192.168.172.211:888/inventory/inventoryapp?storeno=707&type=specialoffer&value1=0&value1=100
        getInstance().mContainer.removeAllViews();

       /* departmentId = (departmentId == null || departmentId.isEmpty()) ? "0" : departmentId;
        subDepartmentId = (subDepartmentId == null || subDepartmentId.isEmpty()) ? "0" : subDepartmentId;
        sizes = (sizes == null || sizes.isEmpty()) ? "0" : sizes ;
        priceRange = (priceRange == null || priceRange.isEmpty()) ? "0" : priceRange ;
        isPriceChanged = (isPriceChanged == null || isPriceChanged.isEmpty()) ? "N" : isPriceChanged ;
        isImage = (isImage == null || isImage.isEmpty()) ? "0" : isImage ;*/

        getInstance().LoadWebVew(Constant.URL + "/inventory/inventoryapp"
                        + "?customerid=" + getUserId()
                        + "&sessionid=" + DeviceInfo.getDeviceId(getInstance()) + "0011"
                        + "&storeno=" + Constant.STOREID
                        + "&deptid=" + departmentId
                        + "&subdeptid=" + subDepartmentId
                        + "&Sizeid=" + sizes
                        + "&pricerange=" + priceRange
                        + "&haspriceChange=" + isPriceChanged
                        + "&ItemWithImage=" + isImage
                        + "&type=" + specialOffers
                        + "&value1=" + valueOne
                        + "&value2=" + valueTwo
                        + "&SortColumn=" + sortCol
                        + "&SortStatus=" + sortStatus
                        + "&filtertext=" + searchText
//+ "&filtertext=" + "0"
                //SortColumn=price&SortStatus=asc
        );
    }

    public static void onUpdate() {
        getInstance().filterItemAdapter_dup.notifyDataSetChanged();
    }

    @Override
    public void selectedDepartment(String deptId) {

    }

    @Override
    public void selectedSubDepartment(String deptId, String subDeptId) {

    }

    @Override
    public void selectedPrice(String deptId, String subDeptId, String selectedPrice) {

    }

    @Override
    public void selectedSize(String deptId, String subDeptId, String size) {

    }

    public void JsonParsing() throws JSONException {
        String jsondata = Utils.loadJSONFromAsset("config.json", MainActivityDup.this);
        JSONArray Arrdata = new JSONArray(jsondata);
        JSONObject MainObj = Arrdata.getJSONObject(0);

        JSONArray Arrsort = MainObj.getJSONArray("Sort");
        JSONArray AccountManage = MainObj.getJSONArray("Account_Manage");
        JSONArray Footer = MainObj.getJSONArray("Footer");
        JSONArray Departments = MainObj.getJSONArray("Departments");

        for (int i = 0; i < Departments.length(); i++) {
            MbsDataModel mbsDataModel = new MbsDataModel(Departments.getJSONObject(i), 4);
            Constant.DEPARTMENTLIST.add(mbsDataModel);
        }
        Constant.FILTERlIST.add(new FilterModel("Departments"));
        Constant.FILTERlIST.add(new FilterModel("Sub Departments"));
        Constant.FILTERlIST.add(new FilterModel("Price"));
        Constant.FILTERlIST.add(new FilterModel("Size"));

        for (int i = 0; i < Arrsort.length(); i++) {
            MbsDataModel mbsDataModel = new MbsDataModel(Arrsort.getJSONObject(i), 3);
            Constant.SORTLIST.add(mbsDataModel);
        }

//        FooterAdapter footerAdapter = new FooterAdapter(this, Constant.FooterList);

//        ArrayList<String> TitleList = new ArrayList<String>(Constant.LHSLIDER_LIST.keySet());
//        Log.e("Log", "Ed-5");
    }

    public static void justifyListViewHeightBasedOnChildren(ListView listView) {

        ListAdapter adapter = listView.getAdapter();
        if (adapter == null) {
            return;
        }
        ViewGroup vg = listView;
        int totalHeight = 0;
        for (int i = 0; i < adapter.getCount(); i++) {
            View listItem = adapter.getView(i, null, vg);
            listItem.measure(0, 0);
            totalHeight += listItem.getMeasuredHeight();
        }

        ViewGroup.LayoutParams par = listView.getLayoutParams();
        par.height = totalHeight + (listView.getDividerHeight() * (adapter.getCount() - 1));
        listView.setLayoutParams(par);
        listView.requestLayout();
    }

    @Override
    public void onClick(View v) {
        // llsearch.setVisibility(View.VISIBLE);
        switch (v.getId()) {
            case R.id.llFilter:
                //mDrawer.closeDrawers();
                //showFilterDialog();
                callOfferFilter();
                //callFilter(deptId, subDepartment, deptSizes);
                break;
            case R.id.llSort:
                //mDrawer.closeDrawers();
                showSortPopup();
                break;
            case R.id.imgHome:
                Utils.hideKeyboard();
                navigationView.setSelectedItemId(R.id.action_home);
                if (llsortandfilter.getVisibility() == View.VISIBLE) {
                    llsortandfilter.setVisibility(View.GONE);
                }
                onBackPressed();
                showHomePage();
                URL = null;
                loadHomeWebPage();
                break;
            case R.id.search_clear:
                mSearchedt.setText("");
                break;

            case R.id.img_search_camera:
                Intent intent = new Intent(this, OcrCaptureActivity.class);
                startActivity(intent);
                break;
           /* case R.id.img_microphone:
                speakWords("Voice command please.", "@@@");
                break;
*/
            default:
                break;
        }
    }

    public int getToolBarHeight() {
        int[] attrs = new int[]{R.attr.actionBarSize};
        TypedArray ta = this.obtainStyledAttributes(attrs);
        int toolBarHeight = ta.getDimensionPixelSize(0, -1);
        ta.recycle();
        return toolBarHeight;
    }

    @NonNull
    public static Point getDisplayDimensions(Context context) {
        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        Display display = wm.getDefaultDisplay();

        DisplayMetrics metrics = new DisplayMetrics();
        display.getMetrics(metrics);
        int screenWidth = metrics.widthPixels;
        int screenHeight = metrics.heightPixels;

        // find out if status bar has already been subtracted from screenHeight
        display.getRealMetrics(metrics);
        int physicalHeight = metrics.heightPixels;
        int statusBarHeight = getStatusBarHeight(context);
        int navigationBarHeight = getNavigationBarHeight(context);
        int heightDelta = physicalHeight - screenHeight;
        if (heightDelta == 0 || heightDelta == navigationBarHeight) {
            //screenHeight -= statusBarHeight;
        }

        return new Point(screenWidth, screenHeight);
    }

    public static int getStatusBarHeight(Context context) {

        int resourceId = 0;
        Resources resources = null;
        if(context != null && context.getResources() != null){
            resources = context.getResources();
            resourceId = resources.getIdentifier("status_bar_height", "dimen", "android");
        }
        return (resourceId > 0) ? resources.getDimensionPixelSize(resourceId) : 0;
    }

    public static int getNavigationBarHeight(Context context) {
        Resources resources = context.getResources();
        int resourceId = resources.getIdentifier("navigation_bar_height", "dimen", "android");
        return (resourceId > 0) ? resources.getDimensionPixelSize(resourceId) : 0;
    }

    /*public void showSortDeptFilterPopup() {

        final View popUpView = getLayoutInflater().inflate(R.layout.exp, null);
        listPopupWindow = new PopupWindow(popUpView, 300,
                LinearLayout.LayoutParams.WRAP_CONTENT, true);
        ListView popupList = popUpView.findViewById(R.id.popupList);
        ImageView leftImg = popUpView.findViewById(R.id.leftImg);
        leftImg.setVisibility(View.INVISIBLE);
        popupList.setVisibility(View.GONE);
        ExpandableListView expandableListView = popUpView.findViewById(R.id.expandableListView);
        expandableListView.setVisibility(View.VISIBLE);
        ArrayList<String> TitleList = new ArrayList<String>(Constant.DEPARTMENTLIST.get(Constant.GROUP_POSITION).LHSFILTER_LIST.keySet());
        expandableListView.setAdapter(new ExpandFilterAdapter_dup(MainActivityDup, Constant.DEPARTMENTLIST.get(Constant.GROUP_POSITION).LHSFILTER_LIST, TitleList));
    }*/

    public Dialog dialog;

    public void showSortPopup() {
        Dialog dialog = new Dialog(this);
        View view = LayoutInflater.from(this).inflate(R.layout.short_dialog, null);
        //ListView popupList = view.findViewById(R.id.popupList);
        RadioGroup rgShorting = view.findViewById(R.id.rg_shorting_filter);
        Button btnApplyShorting = view.findViewById(R.id.btn_apply_short_dialog);
        btnApplyShorting.setBackgroundColor(Color.parseColor(Constant.themeModel.ThemeColor));
        final RadioButton rbPriceAccentDing = view.findViewById(R.id.rb_price_lowest_first);
        final RadioButton rbPriceDescending = view.findViewById(R.id.rb_price_highest_first);
        final RadioButton rbAtoZ = view.findViewById(R.id.rb_title_a_to_z);
        final RadioButton rbZtoA = view.findViewById(R.id.rb_title_z_to_a);

        if (shortingCheckBoxPosition == 0) {
            rbPriceAccentDing.setChecked(true);
        } else if (shortingCheckBoxPosition == 1) {
            rbPriceDescending.setChecked(true);
        } else if (shortingCheckBoxPosition == 2) {
            rbAtoZ.setChecked(true);
        } else if (shortingCheckBoxPosition == 3) {
            rbZtoA.setChecked(true);
        }

        btnApplyShorting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (rbPriceAccentDing.isChecked()) {
                    shortingCheckBoxPosition = 0;
                    shortCall = "price";
                    shortDept = "Asc";
                } else if (rbPriceDescending.isChecked()) {
                    shortingCheckBoxPosition = 1;
                    shortCall = "price";
                    shortDept = "Desc";
                } else if (rbAtoZ.isChecked()) {
                    shortingCheckBoxPosition = 2;
                    shortCall = "desc1";
                    shortDept = "Asc";
                } else if (rbZtoA.isChecked()) {
                    shortingCheckBoxPosition = 3;
                    shortCall = "desc1";
                    shortDept = "Desc";
                }

                iscomfromSort = true;

                //current

                if(deptId.isEmpty()){
                    deptId = "0";
                }
                if(subDepartment.isEmpty()){
                    subDepartment = "0";
                }

                loadViewAllFragment(type,deptId,subDepartment,valueOne,valueTwo,specialOfferGroup,MainActivityDup.blockDisplayedText, "", "");
//                loadSortedWebPage(deptId,subDepartment,shortCall, shortDept);

//                loadFilteredWebPage(deptId, subDepartment, deptSizes, priceRange, isPriceChanged, onlyImage, type, valueOne, valueTwo, shortCall, shortDept);
                dialog.dismiss();


            }
        });
        dialog.setContentView(view);
        dialog.show();
    }

    private void loadSortedWebPage(String deptId, String subdeptId, String sortcolumn, String sortstatus) {

        MainActivityDup.getInstance().mContainer.removeAllViews();

        try {
            searchText = URLEncoder.encode(searchText, "utf-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        MainActivityDup.getInstance().LoadWebVew(Constant.URL + "/inventory/inventoryapp"
                + "?customerid=" + getUserId()
                + "&sessionid=" + 0
                + "&storeno=" + Constant.STOREID
                + "&deptid=" + Constant.clickedDeptIdfromhome
                + "&subdeptid=" + subdeptId
                + "&SortColumn=" + sortcolumn
                + "&SortStatus=" + sortstatus

        );

    }

    public void onShowShoppingCartIcon() {
        final FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        if (cardFragment.isVisible()) {
            new Handler().post(new Runnable() {
                public void run() {
                    fragmentTransaction.remove(cardFragment).commit();
                    invalidateOptionsMenu();
                }
            });
        }
    }

    // CLEAR BACK STACK.
    public void clearBackStack() {

       if(!isFinishing()) {
           final FragmentManager fragmentManager = getSupportFragmentManager();
           try {
               while (fragmentManager.getBackStackEntryCount() != 0) {
                   fragmentManager.popBackStackImmediate();
               }
           } catch (IllegalStateException ignored) {
               // There's no way to avoid getting this if saveInstanceState has already been called.
           }
       }
    }

    public static void showHomePage() {
        getInstance().mContainer.setVisibility(View.VISIBLE);
        //MainActivityDup.getInstance().llsearch.setVisibility(View.GONE);
        getInstance().mContent.setVisibility(View.GONE);
    }

    private void loadMenuSetting() {
        // selecting appropriate nav menu item
        //selectNavMenu();
        invalidateOptionsMenu();
    }

    public void updateShoppingCartItemCount(int count) {
        mNotifCount = count;
        Log.e("Log", "Count=" + mNotifCount);
        invalidateOptionsMenu();
    }

    private void intializingFragments(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        Fragment topFragment = fragmentManager.findFragmentById(R.id.mContent);
        if (topFragment != null) {
            fragmentTransaction.remove(topFragment);
            fragmentTransaction.add(R.id.mContent, fragment);
            fragmentTransaction.addToBackStack(null);
            fragmentTransaction.commit();
        } else {
            fragmentTransaction.add(R.id.mContent, fragment);
            fragmentTransaction.addToBackStack(null);
            fragmentTransaction.commit();
        }

    }


    /**
     * Common method for loading fragments
     **/
    public void loadFragment(Fragment fragment, String tag) {
//        showbackButton();
        llsortandfilter.setVisibility(View.GONE);
        llsearch.setVisibility(View.GONE);
        mContainer.setVisibility(View.GONE);
        llcheckInternet.setVisibility(View.GONE);
        mContent.setVisibility(View.VISIBLE);
        //cardFragment = new CardFragment();
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.setCustomAnimations(android.R.anim.fade_in, android.R.anim.fade_out);
        fragmentTransaction.replace(R.id.mContent, fragment, tag);
        fragmentTransaction.addToBackStack(fragment.getClass().getSimpleName());
//        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commitAllowingStateLoss();
    }

    /**
     * Shopping Card Fragment
     **/
    public void loadCardFragment() {
        //Hide search filter if shows

//        showbackButton();

        if (UserModel.Cust_mst_ID != null && !UserModel.Cust_mst_ID.equals("0")){
            //Manage Account

            getCustomerData();
            MainActivityDup.getInstance().isComeFromCartIconManageAccount = true;

        }else{
            loadCardFragmentDetails();
        }

    }

    private void getCustomerData() {
        if (UserModel.Cust_mst_ID != null) {
            String url = Constant.WS_BASE_URL + Constant.GET_CUSTOMER_DATA + UserModel.Cust_mst_ID + "/" + Constant.STOREID;
            TaskCustomerData taskCustomerData = new TaskCustomerData(this, this);
            Log.d("", "Customer data : " + url);
            taskCustomerData.execute(url);
        }
    }

    private void loadCardFragmentDetails() {
        // llsearch.setVisibility(View.GONE);
        mContainer.setVisibility(View.GONE);
        llcheckInternet.setVisibility(View.GONE);
        mContent.setVisibility(View.VISIBLE);

        cardFragment = new CardFragment();
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.setCustomAnimations(android.R.anim.fade_in, android.R.anim.fade_out);
        fragmentTransaction.replace(R.id.mContent, cardFragment, CardFragment.TAG);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commitAllowingStateLoss();
        llsortandfilter.setVisibility(View.GONE);

        invalidateOptionsMenu();

    }

    @Override
    public void onContinueShoppingCartClicked() {
        mContent.setVisibility(View.GONE);

//        Log.e("Log", "getSupportFragmentManager().beginTransaction()Count=" + getSupportFragmentManager().getBackStackEntryCount());
//        getSupportFragmentManager().beginTransaction().remove(getSupportFragmentManager().findFragmentByTag(CardFragment.TAG)).commit();
//        getSupportFragmentManager().popBackStackImmediate();
//
//        //Edited by Janvi 26th April *********
//        MainActivityDup.showHomePage();
//        MainActivityDup.getInstance().loadHomeWebPage();
////        loadInventoryPage();
//
//        // end *********

        CardFragment.getInstance().redirectToHome();
    }


    private void replaceFragment(Fragment fragment) {
        String backStateName = fragment.getClass().getName();
        String fragmentTag = backStateName;

        FragmentManager manager = getSupportFragmentManager();
        boolean fragmentPopped = manager.popBackStackImmediate(backStateName, 0);

        if (!fragmentPopped && manager.findFragmentByTag(fragmentTag) == null) { //fragment not in back stack, create it.
            FragmentTransaction ft = manager.beginTransaction();
            ft.replace(R.id.mContent, fragment, fragmentTag);
            ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
            ft.addToBackStack(backStateName);
            ft.commit();
        }
    }

    /**
     * Load Delivery Option Fragment
     **/
    @Override
    public void onNextShoppingCart(Bundle bundle) {
        this.bundle = bundle;
        //loadDeliveryOptionFragment();

        deliveryOptionsFragment = new DeliveryOptionsFragment();
        deliveryOptionsFragment.setArguments(bundle);

        llsortandfilter.setVisibility(View.GONE);

        llsearch.setVisibility(View.GONE);
        mContainer.setVisibility(View.GONE);
        llcheckInternet.setVisibility(View.GONE);
        mContent.setVisibility(View.VISIBLE);

        //replaceFragment(deliveryOptionsFragment);
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.setCustomAnimations(android.R.anim.fade_in, android.R.anim.fade_out);
        fragmentTransaction.replace(R.id.mContent, deliveryOptionsFragment, DeliveryOptionsFragment.TAG);
        fragmentTransaction.addToBackStack(DeliveryOptionsFragment.class.getSimpleName());
        fragmentTransaction.commitAllowingStateLoss();

        invalidateOptionsMenu();
    }

    public String getCurrentFragment() {
        if (getSupportFragmentManager().getBackStackEntryCount() > 0)
            return getSupportFragmentManager().findFragmentById(R.id.mContent).getClass().getSimpleName();
        else return "";
    }

    /**
     * Load Payment Fragment
     **/
    @Override
    public void nextFromDeliveryOption(Bundle bundle) {
        paymentFragment = new PaymentFragment();
        paymentFragment.setArguments(bundle);

        llsortandfilter.setVisibility(View.GONE);
         llsearch.setVisibility(View.GONE);
        mContainer.setVisibility(View.GONE);
        llcheckInternet.setVisibility(View.GONE);
        mContent.setVisibility(View.VISIBLE);

        //replaceFragment(paymentFragment);

        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.setCustomAnimations(android.R.anim.fade_in, android.R.anim.fade_out);
        fragmentTransaction.replace(R.id.mContent, paymentFragment, PaymentFragment.TAG);
        fragmentTransaction.addToBackStack(PaymentFragment.class.getSimpleName());
        fragmentTransaction.commitAllowingStateLoss();

        invalidateOptionsMenu();
    }

    /**
     * Load Order Summary Fragment
     **/
    @Override
    public void loadOrderSummaryFragment(Bundle bundle, String orderDetail, String buttonclicked, String returnProcessing) {
        orderSummaryFragment = new OrderSummaryFragment();
        orderSummaryFragment.setArguments(bundle);

        llsortandfilter.setVisibility(View.GONE);
         llsearch.setVisibility(View.GONE);
        mContainer.setVisibility(View.GONE);
        llcheckInternet.setVisibility(View.GONE);
        mContent.setVisibility(View.VISIBLE);
        // llsortandfilter.setVisibility(View.GONE);

        /*if (orderId != null && getSupportFragmentManager().getBackStackEntryCount() <= 0) {
            FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
            fragmentTransaction.setCustomAnimations(android.R.anim.fade_in, android.R.anim.fade_out);
            fragmentTransaction.replace(R.id.mContent, orderSummaryFragment, OrderSummaryFragment.TAG);
            //fragmentTransaction.addToBackStack(OrderSummaryFragment.class.getSimpleName());
            fragmentTransaction.addToBackStack("Detail");
            fragmentTransaction.commitAllowingStateLoss();

            invalidateOptionsMenu();
        } else {*/
        String name = getSupportFragmentManager().getBackStackEntryAt(0).getName();
        getSupportFragmentManager().popBackStack(name, FragmentManager.POP_BACK_STACK_INCLUSIVE);


        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.setCustomAnimations(android.R.anim.fade_in, android.R.anim.fade_out);
        fragmentTransaction.replace(R.id.mContent, orderSummaryFragment, OrderSummaryFragment.TAG);
        //fragmentTransaction.addToBackStack(OrderSummaryFragment.class.getSimpleName());
        fragmentTransaction.addToBackStack(name);
        fragmentTransaction.commitAllowingStateLoss();

        invalidateOptionsMenu();
        // }
    }

    @Override
    public void continueShopping() {

    }

    /**
     * Load WishList Fragment
     **/
    public void loadWishListFragment() {
        if (UserModel.Cust_mst_ID != null) {
            /*WishListFragment*/
            llsortandfilter.setVisibility(View.GONE);
             llsearch.setVisibility(View.GONE);
            mContainer.setVisibility(View.GONE);
            llcheckInternet.setVisibility(View.GONE);
            mContent.setVisibility(View.VISIBLE);

            wishListFragment = new WishListFragment();
            loadFragment(wishListFragment, WishListFragment.TAG);

            invalidateOptionsMenu();
        } else {

//            Edited by Janvi 5th oct ****
//            DialogUtils.checkWishListUser();

            Login.StartLoginDialog("wishlist", this);

            //end ***********


        }
    }

    public void loadRewardFragment() {

        llsortandfilter.setVisibility(View.GONE);
        mContainer.setVisibility(View.GONE);
        llcheckInternet.setVisibility(View.GONE);
        mContent.setVisibility(View.VISIBLE);
        llsearch.setVisibility(View.GONE);

        rewardFragment = new RewardFragment();
        loadFragment(rewardFragment, RewardFragment.TAG);

        invalidateOptionsMenu();
    }

    @Override
    public void onContinueShoppingFromWishList() {
        mContent.setVisibility(View.GONE);
        getSupportFragmentManager().beginTransaction().remove(getSupportFragmentManager().findFragmentByTag(WishListFragment.TAG)).commit();
        getSupportFragmentManager().popBackStackImmediate();

        MainActivityDup.showHomePage();
        MainActivityDup.getInstance().loadHomeWebPage();

//        loadInventoryPage();
        //LoadWebVew(Constant.URL + "/inventory/inventoryapp" + "?customerid=" + UserModel.Cust_mst_ID + "&storeno=" + Constant.STOREID);
    }

    /**
     * Load Delivery option Fragment
     **/
    public void loadDeliveryOptionFragment() {
        deliveryOptionsFragment = new DeliveryOptionsFragment();
        loadFragment(deliveryOptionsFragment, DeliveryOptionsFragment.TAG);
    }

    public static String getUserId() {
        String userId;
        if (UserModel.Cust_mst_ID != null) {
            userId = UserModel.Cust_mst_ID;
        } else {
            userId = "0";
        }

        return userId;
    }

    public void loadHomeWebPage() {
        /*
         * 0011 added because of session not work while log off
         * */
        //llsortandfilter.setVisibility(View.GONE);
        if (URL == null) {
            if (Constant.isNativePage) {
                CallHomeFragment();
            } else {
                LoadWebVew(Constant.URL + Constant.HOME
                        + "?customerid=" + getUserId()
                        + "&storeno=" + Constant.STOREID
                        + "&sessionid=" + DeviceInfo.getDeviceId(getInstance()) + "0011");
            }
        } else {
            if (UserModel.Cust_mst_ID == null) {
                Login.StartLoginDialog("", this);
            } else if (URL.equalsIgnoreCase("cart")) {
                loadCardFragment();
            } else if (URL.equalsIgnoreCase("Wishlist")) {
                loadWishListFragment();
            } else if (URL.equalsIgnoreCase("home")) {
                if (Constant.isNativePage) {
                    CallHomeFragment();
                } else {
                    LoadWebVew(Constant.URL + Constant.HOME
                            + "?customerid=" + getUserId()
                            + "&storeno=" + Constant.STOREID
                            + "&sessionid=" + DeviceInfo.getDeviceId(getInstance()) + "0011");
                }
            } else if (URL.equalsIgnoreCase("Special Offers")) {

            } else if (URL.equalsIgnoreCase("Payment & Refund")) {

            } else {
                LoadWebVew(URL);
            }
        }
    }

    public static void loadInventoryPage() {
        getInstance().mContainer.removeAllViews();
        getInstance().LoadWebVew(Constant.URL + "/inventory/inventoryapp"
                + "?customerid=" + getUserId()
                + "&sessionid=" + DeviceInfo.getDeviceId(getInstance()) + "0011"
                + "&storeno=" + Constant.STOREID);
    }

    @SuppressLint("ClickableViewAccessibility")
    public void LoadWebVew(String url) {
        Log.i("web page url : ", "webpage : " + url);

        Constant.isloadWebview = false;

        showbackButton();

        RecHorizontalList.setVisibility(View.GONE);

        Handler mHandler = new Handler();

        Runnable r = new Runnable() {
            public void run() {
                onShowShoppingCartIcon();
                clearBackStack();
                mContent.removeAllViews();
            }
        };
        mHandler.postDelayed(r, 1000);
        /*if(CardFragment.getInstance()==null) {
            onShowShoppingCartIcon();
        }else{
            new Handler().post(new Runnable() {
                public void run() {
                    mContent.removeAllViews();
                    invalidateOptionsMenu();
                }
            });

        }*/

        Utils.ShowDialog(MainActivityDup.this);
        mContainer.clearView();
        mContainer.clearHistory();
        // mContent.setVisibility(View.GONE);
        mContent.setVisibility(View.GONE);
        CookieSyncManager.createInstance(this);
        CookieManager cookieManager = CookieManager.getInstance();
        cookieManager.removeAllCookie();

        mContainer.setVisibility(View.VISIBLE);
        WebSettings settings = mContainer.getSettings();
        settings.setJavaScriptEnabled(true);
        mContainer.setScrollBarStyle(WebView.SCROLLBARS_OUTSIDE_OVERLAY);
        mContainer.setWebChromeClient(new WebChromeClient());
        mContainer.getSettings().setRenderPriority(WebSettings.RenderPriority.HIGH);
        if (Build.VERSION.SDK_INT >= 19) {
            mContainer.setLayerType(View.LAYER_TYPE_HARDWARE, null);
        } else {
            mContainer.setLayerType(View.LAYER_TYPE_SOFTWARE, null);
        }
        checkFilterURL = "";
        /** Handle Touch Listener **/
        mContainer.setOnTouchListener(new View.OnTouchListener() {

            public final static int FINGER_RELEASED = 0;
            public final static int FINGER_TOUCHED = 1;
            public final static int FINGER_DRAGGING = 2;
            public final static int FINGER_UNDEFINED = 3;

            private int fingerState = FINGER_RELEASED;

            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {

                WebView.HitTestResult hr = ((WebView) view).getHitTestResult();
                Log.i("webview touch", "getExtra = " + hr.getExtra() + "\t\t Type=" + hr.getType());
                switch (motionEvent.getAction()) {

                    case MotionEvent.ACTION_DOWN:
                        if (fingerState == FINGER_RELEASED) fingerState = FINGER_TOUCHED;
                        else fingerState = FINGER_UNDEFINED;
                        break;

                    case MotionEvent.ACTION_UP:
                        if (fingerState != FINGER_DRAGGING) {
                            fingerState = FINGER_RELEASED;

                            Handler handler = new Handler();
                            handler.postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    onGetCartData();
                                }
                            }, 500);
                        } else if (fingerState == FINGER_DRAGGING) fingerState = FINGER_RELEASED;
                        else fingerState = FINGER_UNDEFINED;
                        break;

                    case MotionEvent.ACTION_MOVE:
                        if (fingerState == FINGER_TOUCHED || fingerState == FINGER_DRAGGING)
                            fingerState = FINGER_DRAGGING;
                        else fingerState = FINGER_UNDEFINED;
                        break;

                    default:
                        fingerState = FINGER_UNDEFINED;
                }
                return false;
            }
        });

        mContainer.setWebViewClient(new WebViewClient() {
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                Log.e("Web Page", "Over LoadWebVew: " + url);
                String afterDecode = "";
                Log.e("Log", "1=1" + url);
                try {
                    afterDecode = URLDecoder.decode(url, "UTF-8");
                    innerFilterUrl = afterDecode;
                    Log.i("Log", "viewAll" + afterDecode);
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
                // checkFilterURL = url;
                if (url.contains("Cart?")) {
                    loadCardFragment();
                    Utils.hideKeyboard();
                } else if (url.contains("buyitagain")) {
                    //buyitagain/1786
                    mContainer.stopLoading();
                    Log.e("Log", "URLBuy==" + url);
                    showReOrderConfirmation(url, "", 1);
                } else if (url.contains("ReOrder")) {
                    Log.e("Log", "URLBuy==" + url);
                    mContainer.stopLoading();
//                    showReOrderConfirmation(url, 2);

                    final String itemNo = url.substring(url.indexOf("ReOrder/")).trim();
                    String paraList[] = itemNo.split("/");
                    callReorderDetailsWS(paraList[1]);


                } else if (url.contains("WishList?")) {
                    loadWishListFragment();
                    Utils.hideKeyboard();
                } else if (url.contains("Home/StoreIndex") || (url.contains("/Home/IndexApp"))) {
                    CallHomeFragment();
                }

//              Edited by Janvi 15thoct *************
////                else if (UserModel.Cust_mst_ID == null && url.contains("inventory/Wishlist")) {
////                        Login.StartLoginDialog("wishlist");
////                        Utils.hideKeyboard();
////                }
//
                else if (url.contains("inventory/Wishlist") || url.contains("Inventory/Wishlist")) {
                    mContainer.stopLoading();

                    if (UserModel.Cust_mst_ID == null) {
                        Login.StartLoginDialog("wishlist", MainActivityDup.this);
                        Utils.hideKeyboard();
                    } else {
                        String itemId = url.substring(url.lastIndexOf("/") + 1);
                        if (!Constant.isDialogShowSeclayout) {
                            callAddToWishlistWS(itemId);
                        }
                        Constant.isDialogShowSeclayout = true;
                    }

                } else if (url.contains(/*"inventory"*/"inventory/inventoryapp")) {
                    view.loadUrl(url);
                    llsortandfilter.setVisibility(View.VISIBLE);
                    Utils.hideKeyboard();
                    view.clearHistory();
                } else if (afterDecode.contains(/*"InventoryAppList"*/"inventory/itemdescriptionapp/")) {

//                        Toast.makeText(MainActivity.this,url,Toast.LENGTH_LONG).show();
                    String itemId = url.substring(url.lastIndexOf("/") + 1);

                    if (!itemId.isEmpty()) {
                        getInstance().loadItemDescriptionFragment(itemId, "fragment");
                    }

                } else if (afterDecode.contains(/*"InventoryAppList"*/"Inventory/ItemDescriptionApp?customerid=")) {
                    view.loadUrl(url);
                    llsortandfilter.setVisibility(View.INVISIBLE);
                    Utils.hideKeyboard();
                } else {
                    llsortandfilter.setVisibility(View.INVISIBLE);
                    if (!url.contains("404")) {
                        view.loadUrl(url);
                    }
                    Log.e("Log", "llsortandfilter=3");
                }
                // Utils.ShowDialog(MainActivityDup.this);
                return true;
            }

            public void onPageFinished(WebView view, String url) {
                // Log.e("Log", "Url11111=" + url);
                Log.e("Log", "1=2" + url);
                //checkFilterURL = url;
                innerFilterUrl = url;
                /*if (url.contains("inventory")) {
                    if (getFragmentManager().getBackStackEntryCount() == 0)
                        llsortandfilter.setVisibility(View.VISIBLE);
                } else {
                    llsortandfilter.setVisibility(View.GONE);
                }*/
                Utils.HideDialog();
            }

            @Override
            public void onLoadResource(WebView view, String url) {
                Log.e("Log", "1=3" + url);
                if (url.contains("InsertCartData")) {
                    Log.d("cart", "Cart Clied Url: " + url);
                }
                if (url.equals("www.addtocart.com")) {
                    Toast.makeText(MainActivityDup.this, "Clicked : " + url, Toast.LENGTH_SHORT).show();
                }
                if (url.contains("www.addtocart.com")) {

                }
                if (url.contains(/*"inventory"*/"inventory/inventoryapp")) {
                    checkFilterURL = url;
                    Log.e("Log", "llsortandfilter=4");
                    llsortandfilter.setVisibility(View.VISIBLE);
                    Utils.hideKeyboard();
                    view.clearHistory();
                } else if (url.contains(/*"InventoryAppList"*/"Inventory/ItemDescriptionApp?customerid=")) {
                    checkFilterURL = url;
                    Log.e("Log", "llsortandfilter=5");
                    llsortandfilter.setVisibility(View.INVISIBLE);
                    Utils.hideKeyboard();
                }
            }
        });

        mContainer.loadUrl(url);
        mContainer.clearHistory();
    }

    private void loadData() {
    }

    public void callAddToWishlistWS(String itemId) {

        String wishlistWSurl = Constant.WS_BASE_URL + Constant.DELETE_CART + "0" + "/" + "Wishlist" + "/" + UserModel.Cust_mst_ID +
                "/" + itemId + "/" + "1" +
                "/" + Constant.STOREID + "/" + "0" + "/" + "add" + "/" + Constant.invType;

        TaskDeleteWishList deleteWishList = new TaskDeleteWishList(this, "");
        deleteWishList.execute(wishlistWSurl);
    }


    private void callReorderDetailsWS(String orderid) {
        String url = Constant.WS_BASE_URL + Constant.RE_ORDER_DETAILS + orderid + "/" + Constant.STOREID;
        TaskReOrder taskCart = new TaskReOrder(this,orderid);
        taskCart.execute(url);
    }

    @Override
    public void onReOrderResult(ReOrderModel model, String fromwhere, String requestdQty) {

        if(!fromwhere.isEmpty() && fromwhere.equalsIgnoreCase("buyItAgain")){

            if (model.getResult().equalsIgnoreCase("success") || model.getResult().equalsIgnoreCase("Already added")) {

                loadCardFragment();
            }


        }else{

            if (model.getResult().equalsIgnoreCase("success") || model.getResult().equalsIgnoreCase("Already added")) {

//            OrderSummaryFragment.getInstence().backOrDismissFrag();
                loadCardFragment();
            }

        }
    }

    @Override
    public void onReorderListResult(List<ReOrderItemModel> reorderList,String orderid) {
        Context context = this;

        reorderDialog = new Dialog(this);
        reorderDialog.setContentView(R.layout.reorder_detail_dialog);
        reorderDialog.getWindow().setLayout(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);

//        RecyclerView rvReorder = dialog.findViewById(R.id.recyclerview);
        TextView tvTitle = (TextView) reorderDialog.findViewById(R.id.tvTitle);
//        TextView tvPrice = (TextView) dialog.findViewById(R.id.tvPrice);
//        TextView tvSKU = (TextView) dialog.findViewById(R.id.tvSKU);
//        TextView tvCategory = (TextView) dialog.findViewById(R.id.tvCategory);
//        TextView tvDiscountName = (TextView) dialog.findViewById(R.id.tvDiscountName);
//        TextView tvItemName = (TextView) dialog.findViewById(R.id.tvItemName);
        ImageView iv_close = (ImageView) reorderDialog.findViewById(R.id.iv_close);
//        ImageView img_item = (ImageView) dialog.findViewById(R.id.img_item);
//        EditText etEmail = (EditText) dialog.findViewById(R.id.etEmail);
//        EditText etSecondEmail = (EditText) dialog.findViewById(R.id.etSecondEmail);
//        EditText etSubject = (EditText) dialog.findViewById(R.id.etSubject);
//        CheckBox checkbox_carbonCopy = (CheckBox) dialog.findViewById(R.id.checkbox_carbonCopy);
//        TextInputEditText txtInput_etPersonalMsg = (TextInputEditText) dialog.findViewById(R.id.TextInputet_PersonalMsg);
        Button btnBack = (Button) reorderDialog.findViewById(R.id.btn_back);
        Button btnReorder = (Button) reorderDialog.findViewById(R.id.btn_Reorder);

        GradientDrawable bgShape = (GradientDrawable) btnBack.getBackground();
        bgShape.setColor(Color.parseColor(Constant.themeModel.ThemeColor));

        GradientDrawable bgShape1 = (GradientDrawable) btnReorder.getBackground();
        bgShape1.setColor(Color.parseColor(Constant.themeModel.ThemeColor));


        rvReorder = (RecyclerView) reorderDialog.findViewById(R.id.recyclerview);
        reorderAdapter = new ReorderItemAdapter(this, reorderList);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        rvReorder.setLayoutManager(layoutManager);
        rvReorder.setHasFixedSize(true);
        rvReorder.setAdapter(reorderAdapter);
        iv_close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                reorderDialog.dismiss();
                Constant.selectedReorderList.clear();
            }
        });

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                reorderDialog.dismiss();
                Constant.selectedReorderList.clear();
            }
        });

        btnReorder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final int SOCKET_TIMEOUT_MS = 30000;
                insertReorderRequest(Constant.selectedReorderList);


                JSONObject jsonObjRequest = insertReorderRequest(Constant.selectedReorderList);

                com.android.volley.RequestQueue requestQueue = Volley.newRequestQueue(context);


                final String mRequestBody = jsonObjRequest.toString();

                StringRequest stringRequest = new StringRequest(Request.Method.POST, Constant.WS_INSERT_REORDER, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.i("LOG_RESPONSE", response);
                        reOrderSuccessResponse(response);
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.e("LOG_RESPONSE", error.toString());
                    }
                }) {
                    @Override
                    public String getBodyContentType() {
                        return "application/json; charset=utf-8";
                    }

                    @Override
                    public byte[] getBody() throws AuthFailureError {
                        try {
                            return mRequestBody == null ? null : mRequestBody.getBytes("utf-8");
                        } catch (UnsupportedEncodingException uee) {
                            VolleyLog.wtf("Unsupported Encoding while trying to get the bytes of %s using %s", mRequestBody, "utf-8");
                            return null;
                        }
                    }

                    @Override
                    protected Response<String> parseNetworkResponse(NetworkResponse response) {
                        String responseString = "";
                        if (response != null) {
                            responseString = String.valueOf(response);
                        }
                        return Response.success(responseString, HttpHeaderParser.parseCacheHeaders(response));
                    }
                };

                requestQueue.add(stringRequest);

            }

            private void reOrderSuccessResponse(String s) {

                showReOrderConfirmation(orderid, "", 2);
            }
        });

        reorderDialog.show();
    }



    /*
     *
     * buy=1
     * ReOrder=2
     *
     * */
    public void showReOrderConfirmation(final String orderid, String itemid, final int buy_or_reorder) {
        final Dialog dialog = new Dialog(MainActivityDup.this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.dialog_confirmation);

        TextView txtErrorName = dialog.findViewById(R.id.tv_sub_title_warning_dialog);
        txtErrorName.setText(getString(R.string.str_confirmation_text));

        Button btnOK = dialog.findViewById(R.id.btn_OK);
        GradientDrawable bgShape = (GradientDrawable) btnOK.getBackground();
        bgShape.setColor(Color.parseColor(Constant.themeModel.ThemeColor));
        btnOK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
                if(reorderDialog != null && reorderDialog.isShowing()){
                    reorderDialog.dismiss();
                }

//                if (buy_or_reorder == 1) {
//
//                    if (Constant.SCREEN_LAYOUT == 1) {
//                        MainActivity.getInstance().callBuyItAgainWS(orderid, itemid);
//
//                    } else if (Constant.SCREEN_LAYOUT == 2) {
//                        MainActivityDup.getInstance().callBuyItAgainWS(orderid, itemid);
//                    }
//
//
//                } else {

//                    String orderid = reorderurl;
//                    Reorderurl = WS_BASE_URL + RE_ORDER_DATA + orderid + "/" + STOREID;
                    callInsertReporderWS(MainActivityDup.this);
//                }

            }
        });
        Button btn_Cancel = dialog.findViewById(R.id.btn_Cancel);
        GradientDrawable bgShapec = (GradientDrawable) btn_Cancel.getBackground();
        bgShapec.setColor(Color.parseColor(Constant.themeModel.ThemeColor));
        btn_Cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        dialog.show();
    }


    public void callFilterFragment() {
        //llsearch.setVisibility(View.GONE);
//        mContainer.setVisibility(View.GONE);
//        Log.e("Log", "llsortandfilter=6");
//        llsortandfilter.setVisibility(View.GONE);
//        llcheckInternet.setVisibility(View.GONE);
//        mContent.setVisibility(View.VISIBLE);
//
//        if (filterFragment == null) {
//            filterFragment = new FilterFragment();
//        }
//        if (Constant.SubDepartmentList.size() == 0 ) {
//            String Url1 = Constant.WS_BASE_URL + Constant.GET_STYLE_DEPARTMENT_LIST + Constant.STOREID;
//            new Async_getCommonService(this, Url1).execute();
//
//        } else {
//            FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
//            fragmentTransaction.setCustomAnimations(android.R.anim.fade_in, android.R.anim.fade_out);
//            fragmentTransaction.replace(R.id.mContent, filterFragment, "fragment");
//            fragmentTransaction.addToBackStack(FilterFragment.class.getSimpleName());
//            fragmentTransaction.commitAllowingStateLoss();
//        }

        callFilterInfoForSubdepartment();

    }

    public void CallHomeFragment() {

//        clearFilterSelection();
        llsearch.setVisibility(View.GONE);
        mContainer.setVisibility(View.GONE);
        Log.e("Log", "llsortandfilter=7");
        llsortandfilter.setVisibility(View.GONE);
        llcheckInternet.setVisibility(View.GONE);
        mContent.setVisibility(View.VISIBLE);
        //if (fragment == null) {
        homepageFragment = new HomepageFragment();

        clearBackStack();
//        navigationView.getMenu().findItem(R.id.action_home).setChecked(true);

        /*}
        if (Constant.DepartmentList.size() == 0) {
            String Url1 = Constant.WS_BASE_URL + Constant.GETDEPARTMENT + Constant.STOREID;
            new Async_getCommonService(this, Url1).execute();
        } else {*/
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.setCustomAnimations(android.R.anim.fade_in, android.R.anim.fade_out);
        fragmentTransaction.replace(R.id.mContent, homepageFragment, "fragment");

        //fragmentTransaction.addToBackStack(FilterFragment.class.getSimpleName());
        //fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commitAllowingStateLoss();
        //mDrawer.setDrawerListener(actionBarDrawerToggle);
        //}
    }

    public void displayDepartmentList() {
        final View popUpView = getLayoutInflater().inflate(R.layout.raw_dept_popup, null);

        listPopupWindow = new PopupWindow(popUpView, 350,
                LinearLayout.LayoutParams.WRAP_CONTENT, true);
        ListView popupList = popUpView.findViewById(R.id.popupList);
      /*  ImageView rightimg = (ImageView) popUpView.findViewById(R.id.rightimg);
        rightimg.setVisibility(View.INVISIBLE);*/
        DepartmentAdapter sortAdapter = new DepartmentAdapter(MainActivityDup.this, Constant.DepartmentList);
        popupList.setAdapter(sortAdapter);
        // listPopupWindow.showAsDropDown(btndept, -15, -10);
        listPopupWindow.setOutsideTouchable(true);
        View.OnTouchListener listener = new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {

                listPopupWindow.dismiss();
                getSupportFragmentManager().popBackStackImmediate();
                return false;
            }
        };
        listPopupWindow.setTouchable(true);
        listPopupWindow.setBackgroundDrawable(new BitmapDrawable(getResources(), ""));
        listPopupWindow.setTouchInterceptor(listener);
        listPopupWindow.showAsDropDown(btndept, 0, 0);
    }

    public static void onGetCartData() {
        String url = null;
        if (UserModel.Cust_mst_ID != null) {
//            url = Constant.WS_BASE_URL + Constant.GET_CUSTOMER_CARD_DATA + UserModel.Cust_mst_ID + "/" + Constant.MY_CART + Constant.STOREID;
            url = Constant.WS_BASE_URL + Constant.GET_CUSTOMER_CARD_DATA_V1 + UserModel.Cust_mst_ID + "/" + Constant.MY_CART + Constant.STOREID + Constant.ENCODE_TOKEN_ID;
            TaskCart taskCart = new TaskCart(getInstance(), "");
            taskCart.execute(url);
        } else {
//            url = Constant.WS_BASE_URL + Constant.GET_CUSTOMER_CARD_DATA + DeviceInfo.getDeviceId(getInstance()) + "0011" + "/" + Constant.SESSION + Constant.STOREID;
            url = Constant.WS_BASE_URL + Constant.GET_CUSTOMER_CARD_DATA_V1 + DeviceInfo.getDeviceId(getInstance()) + "0011" + "/" + Constant.SESSION + Constant.STOREID + Constant.ENCODE_TOKEN_ID;
            TaskCart taskCart = new TaskCart(getInstance(), "");
            taskCart.execute(url);
        }
    }

    @Override
    public void onShoppingCardResult(List<ShoppingCardModel> liShoppingCard, String s) {
        int quantity = 0;
        for (int i = 0; i < liShoppingCard.size(); i++) {
            if (liShoppingCard.get(i).getQty() != null)
                quantity = quantity + Integer.parseInt(liShoppingCard.get(i).getQty());
        }
        if (countMenu != null)
            countMenu.setTitle(String.valueOf(quantity));
        getInstance().updateShoppingCartItemCount(quantity);
    }

    public static void onCallGlobalSetup() {
        String twentyOneYearUrl = Constant.WS_BASE_URL + Constant.GET_GLOBALSETTING + Constant.STOREID;
        TaskTwentyOneYear taskTwentyOneYear = new TaskTwentyOneYear(getInstance());
        taskTwentyOneYear.execute(twentyOneYearUrl);
    }

    @Override
    public void onTwentyOneYearResult(TwentyOneYear twentyOneYear) {

        this.twentyOneYear = twentyOneYear;

        String getContactInfoURL = Constant.WS_BASE_URL + Constant.GET_CONTACT_INFO + "/" + Constant.STOREID;
        TaskContactInfo taskContactInfo = new TaskContactInfo(MainActivityDup.getInstance(),this);
        taskContactInfo.execute(getContactInfoURL);
    }

    private void callStoreLocationListWs() {


    }

    public static void moveSessionToCart() {
        String seesion = DeviceInfo.getDeviceId(getInstance()) + "0011";
        String seesionUrl;
        TaskSessionToCart sessionToCard = new TaskSessionToCart();

        if (UserModel.Cust_mst_ID != null && !seesion.isEmpty()) {
            seesionUrl = Constant.WS_BASE_URL + Constant.UPDATE_SESSION_TO_CART
                    + seesion + "/" + UserModel.Cust_mst_ID + "/" + Constant.STOREID;
            sessionToCard.execute(seesionUrl);
        }
    }

    @Override
    public void onStart() {
        super.onStart();
        onGetCartData();
    }

    /**
     * Inside On Resume Check if username password is available on Shared Prefrence so go for login
     **/
    @Override
    protected void onResume() {
        super.onResume();
        callResume();
    }

    @Override
    public void onStop() {
        super.onStop();
    }

    /**
     * use for handling navigation drawer button state
     **/
    @Override
    protected void onPostCreate(@Nullable Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
    }

    /**
     * use for handling navigation drawer button state
     **/
    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);

    }

    /**
     * use for handling navigation back stack with back Button
     **/
    @Override
    public void onBackStackChanged() {


        //for back button uncomment below line

//        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(getSupportFragmentManager().getBackStackEntryCount() > 0);


    }

    /**
     * Handle Back event for fragment and webview both
     **/
    @Override
    public void onBackPressed() {
        onShowShoppingCartIcon();
        RecHorizontalList.setVisibility(View.VISIBLE);
        if (getSupportFragmentManager().getBackStackEntryCount() > 0) {
            Log.e("Log", "llsortandfilter=8");
            llsortandfilter.setVisibility(View.GONE);
            getSupportFragmentManager().popBackStackImmediate();
            if (getSupportFragmentManager().getBackStackEntryCount() == 0) {
                if (Constant.isNativePage) {
                    mContainer.setVisibility(View.GONE);
                    mContent.setVisibility(View.VISIBLE);
                } else {
                    mContainer.setVisibility(View.VISIBLE);
                    mContent.setVisibility(View.GONE);
                }
//                if (mContainer.getUrl() == null) {
//                    URL = null;
//                    loadHomeWebPage();
//                }
            } else {
                llsortandfilter.setVisibility(View.GONE);
            }
            //
            return;
        } else if (mContent.isShown() && !Constant.isNativePage) {
            mContainer.setVisibility(View.VISIBLE);
            //llsearch.setVisibility(View.VISIBLE);
            mContent.setVisibility(View.GONE);
        } else {

            if (mContainer.isShown() && mContainer.canGoBack() && Constant.Detail_webview_Url) {
                CallHomeFragment();
                Constant.Detail_webview_Url = false;

            }
            else if (mContainer.isShown() && mContainer.canGoBack()&& Constant.isviewall_page) {
                CallHomeFragment();
                Constant.isviewall_page = false;
            }
            else if (mContainer.isShown() && mContainer.canGoBack()) {

                Utils.ShowDialog(MainActivityDup.this);
                mContainer.goBack();
                return;
            } else {

                //super.onBackPressed();
                CallHomeFragment();
            }

        }
    }

    /**
     * Get Resource
     **/
    @Override
    public Resources getResources() {
        //return super.getResources();
        if (res == null) {
            res = new Res(super.getResources());
        }
        return res;
    }

    /**
     * Option Menu
     **/
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.action_menu, menu);
        Mymenu = menu;
        countMenu = menu.findItem(R.id.menu_Count);

        MenuItem item = menu.findItem(R.id.menu_Cart);
        MenuItemCompat.setActionView(item, R.layout.icon_shopping_cart_count);
        RelativeLayout rlCount = (RelativeLayout) MenuItemCompat.getActionView(item);
        notifCount = rlCount.findViewById(R.id.notif_count);
        ImageView imgIcon = rlCount.findViewById(R.id.img_icon_count);
        GradientDrawable bgShape1 = (GradientDrawable) notifCount.getBackground();
        item.setVisible(true);
        bgShape1.setColor(Color.parseColor(Constant.themeModel.ThemeColor));
        if (mNotifCount > 0)
            notifCount.setVisibility(View.VISIBLE);
        else
            notifCount.setVisibility(View.GONE);

        notifCount.setText(String.valueOf(mNotifCount));

        imgIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loadCardFragment();
                //loadDeliveryOptionFragment();
            }
        });
        notifCount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loadCardFragment();
                //loadDeliveryOptionFragment();
            }
        });
        return super.onCreateOptionsMenu(menu);
        //return true;
    }

    /**
     * Option Menu Item Click
     **/
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        for (int i = 0; i < Mymenu.size(); i++) {
            Mymenu.getItem(i).setChecked(false);
        }
        item.setChecked(true);
        //llsearch.setVisibility(View.VISIBLE);
        //showHomePage();
        switch (id) {


//            case android.R.id.home:
//                // todo: goto back activity from here
//
//                Intent intent = new Intent(this, MainActivityDup.class);
//                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
//                startActivity(intent);
//                finish();
//                return true;
            case android.R.id.home:
                onBackPressed();
                Log.e("backpressed","android.r.home");
                break;

            case R.id.menu_search:
                if (mSearchedt.isShown()) {
                    Utils.collapse(mSearchedt, 500, 0);
                } else {
                    Utils.expand(mSearchedt, 500, height);
                }
                break;

            case R.id.menu_Cart:
                llsearch.setVerticalGravity(View.GONE);
                loadCardFragment();
                break;
//            case R.id.menu_Mic:
//                //speakWords("Voice command please.", "@@@");
//                Intent intent = new Intent(this, MicActivity.class);
//                startActivity(intent);
//                break;
            case R.id.menu_wishlist:
                getInstance().LoadWebVew(Constant.URL_PAGE_WISHLIST);
                //Toast.makeText(MainActivityDup.this, "Wait.. under process...", Toast.LENGTH_SHORT).show();
//                loadWishListFragment();
                break;
            case R.id.menu_Count:
                break;

            default:
                return super.onOptionsItemSelected(item);
        }
        return false;
    }


    public int setAudio_Record_Permition() {
        int val = 0;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (ContextCompat.checkSelfPermission(this, android.Manifest.permission.RECORD_AUDIO) ==
                    PackageManager.PERMISSION_GRANTED) {
                // put your code for Version>=Marshmallow
                val = 1;
            } else {
                if (shouldShowRequestPermissionRationale(android.Manifest.permission.RECORD_AUDIO)) {
                    Toast.makeText(this,
                            "App required access to audio", Toast.LENGTH_SHORT).show();
                }
                requestPermissions(new String[]{android.Manifest.permission.RECORD_AUDIO
                }, RECORD_AUDIO_PERMITION);
            }

        }
        return val;
    }

    /**
     * Request permission
     **/
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String permissions[], @NonNull int[] grantResults) {
        switch (requestCode) {
            case MY_PERMISSIONS_REQUEST_READ_EXTERNAL_STORAGE: {
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    Log.d("Home", "Permission Granted");
                    /*intRecord();*/
                } else {
                    Log.d("Home", "Permission Failed");
                   /* Toast.makeText(getActivity().getBaseContext(), "You must allow permission record audio to your mobile device.", Toast.LENGTH_SHORT).show();
                    getActivity().finish();*/
                }
            }
            break;
            case RECORD_AUDIO_PERMITION: {
                if (grantResults[0] != PackageManager.PERMISSION_GRANTED) {
                    Toast.makeText(this,
                            "Application will not have audio on record", Toast.LENGTH_SHORT).show();
                }
            }

           break;
            case ACCESS_FINE_LOCATION: {

                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
//
//                  All good!
//                    Toast.makeText(this, "got location!", Toast.LENGTH_LONG).show();
                    getLocation();
                } else {
//                    Toast.makeText(this, "Need your location!", Toast.LENGTH_SHORT).show();
                }
            }

            // Add additional cases for other permissions you may have asked for
        }
    }

    /**
     * On Activity Result
     **/
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        switch (requestCode) {
            case REQ_CODE_SPEECH_INPUT: {
                if (resultCode == RESULT_OK && null != data) {

                    ArrayList<String> result = data
                            .getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
                    Toast.makeText(MainActivityDup.this, "" + result.get(0), Toast.LENGTH_SHORT).show();
                }
                break;
            }
            case MY_DATA_CHECK_CODE: {
                if (resultCode == TextToSpeech.Engine.CHECK_VOICE_DATA_PASS) {
                    //the user has the necessary data - create the TTS
                    myTTS = new TextToSpeech(this, this);
                } else {
                    //no data - install it now
                    Intent installTTSIntent = new Intent();
                    installTTSIntent.setAction(TextToSpeech.Engine.ACTION_INSTALL_TTS_DATA);
                    startActivity(installTTSIntent);
                }
                break;
            }
            case 1: {
                if (resultCode == RESULT_OK) {
                    Place place = PlaceAutocomplete.getPlace(this, data);
                    // Log.i(TAG, "Place: " + place.getName());
                } else if (resultCode == PlaceAutocomplete.RESULT_ERROR) {
                    Status status = PlaceAutocomplete.getStatus(this, data);
                    // TODO: Handle the error.
                    // Log.i(TAG, status.getStatusMessage());
                } else if (resultCode == RESULT_CANCELED) {
                    // The user canceled the operation.
                }
                break;
            }
            case REQ_CODE_SPEECH_INPUT1: {
                if (resultCode == RESULT_OK && null != data) {
                    ArrayList<String> result = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
                    String sp = result.get(0);

                    String CARTLIST = "shopping,shopping card,cartlist,cart,cart list,cardlist,card,card list,open cart,display cart,show cart,show me cart,opencart";
                    String WISHLIST = "wishlist,wish,wish list,open wishlist,give me my wish,give me my wishlist,give me my wish list";
                    String DEPTLIST = "department,department list,list department";
                    String IMGREADLIST = "search barcode,search image,barcode,image, image read,barcode read,read barcode,read image,capture barcode,read barcode";
                    String SIGNIN = "sign in,signin,sign in please,login,log in,logged in";
                    String SIGNOUT = "sign out,signout,sign out please,logout,log out,logged out,logoff,log off";
                    String CLOSEAPP = "close,exit,quit,finish,stop,bye,good bye,close app,exit from app,quit app,finish app,stop app";
                    String ABOUTUS = "about,aboutus,about us";
                    String CONTACTUS = "contact,contactus,contact us";
                    String HOME = "home,dashboard,homepage,open home,open dashboard,open homepage";
                    String BLOG = "blog,diary,bloggar";
                    String FAQ = "f a q,faq,frequent question,frequent";
                    String LEGAL = "ratified,jural,sanctioned,judicial,juristic,statutory,sub judice,legitimate,lawful,licit,legality";
                    String PRIVACY = "Privacy,covertness,confidentiality,privateness,secrecy,hiddenness,concealment,hiding";
                    String SHIPPING = "shipping,shipment,ship,shipped";
                    String SUPPORT = "Support,Support and Customer Service,service";
                    String TERMS = "terms,term,policy,condition,aggreement";
                    String HELP = "help,assist,help out";
                    String STORE_HOUR = "store hour,delivery hour,hours,hour,storehour,deliveryhour,store hours,delivery hours";

                    String[] arrcart = CARTLIST.split(",");
                    String[] arrWish = WISHLIST.split(",");
                    String[] arrdept = DEPTLIST.split(",");
                    String[] arrimgread = IMGREADLIST.split(",");
                    String[] arrsignin = SIGNIN.split(",");
                    String[] arrsignout = SIGNOUT.split(",");
                    String[] arrclose = CLOSEAPP.split(",");
                    String[] arrhome = HOME.split(",");
                    String[] arraboutus = ABOUTUS.split(",");
                    String[] arrcontactus = CONTACTUS.split(",");
                    String[] arrblog = BLOG.split(",");
                    String[] arrfaq = FAQ.split(",");
                    String[] arrlegal = LEGAL.split(",");
                    String[] arrprivacy = PRIVACY.split(",");
                    String[] arrshipping = SHIPPING.split(",");
                    String[] arrsupport = SUPPORT.split(",");
                    String[] arrterms = TERMS.split(",");
                    String[] arrhelp = HELP.split(",");
                    String[] arrstore = STORE_HOUR.split(",");

                    List cartlist = Arrays.asList(arrcart);
                    List wishlist = Arrays.asList(arrWish);
                    List deptlist = Arrays.asList(arrdept);
                    List imgreadlist = Arrays.asList(arrimgread);
                    List signinList = Arrays.asList(arrsignin);
                    List signoutList = Arrays.asList(arrsignout);
                    List closeList = Arrays.asList(arrclose);
                    List homeList = Arrays.asList(arrhome);
                    List aboutus = Arrays.asList(arraboutus);
                    List contactus = Arrays.asList(arrcontactus);
                    List blogLis = Arrays.asList(arrblog);
                    List faqLis = Arrays.asList(arrfaq);
                    List legalLis = Arrays.asList(arrlegal);
                    List privacyLis = Arrays.asList(arrprivacy);
                    List termsLis = Arrays.asList(arrterms);
                    List helpLis = Arrays.asList(arrhelp);
                    List shippingList = Arrays.asList(arrshipping);
                    List supportList = Arrays.asList(arrsupport);
                    List storehourtList = Arrays.asList(arrstore);
                    Log.e("Log", "**Constant.STOREID=" + Constant.STOREID);
                    String speechLower = sp.trim().toLowerCase();
                    if (cartlist.contains(speechLower)) {
                        loadCardFragment();
                    } else if (wishlist.contains(speechLower)) {
                        loadWishListFragment();
                    } else if (deptlist.contains(speechLower)) {
                        callFilterFragment();
                    } else if (imgreadlist.contains(speechLower)) {
                        Intent intent = new Intent(this, OcrCaptureActivity.class);
                        startActivity(intent);
                    } else if (signinList.contains(speechLower)) {
                        Login.StartLoginDialog("", this);
                    } else if (signoutList.contains(speechLower)) {
                        Login.onLogOff();
                    } else if (closeList.contains(speechLower)) {
                        finish();
                    } else if (homeList.contains(speechLower)) {
                        loadHomeWebPage();
                    } else if (aboutus.contains(speechLower)) {
                        LoadWebVew(Constant.URL_PAGE_ABOUT_US + Constant.STOREID);
                    } else if (contactus.contains(speechLower)) {
                        getInstance().LoadWebVew(Constant.URL_PAGE_CONTACT_US);
                    } else if (blogLis.contains(speechLower)) {
                        getInstance().LoadWebVew(Constant.URL_PAGE_BLOG + Constant.STOREID);
                    } else if (faqLis.contains(speechLower)) {
                        getInstance().LoadWebVew(Constant.URL_PAGE_FAQ + Constant.STOREID);
                    } else if (legalLis.contains(speechLower)) {
                        getInstance().LoadWebVew(Constant.URL_PAGE_LEGAL + Constant.STOREID);
                    } else if (privacyLis.contains(speechLower)) {
                        getInstance().LoadWebVew(Constant.URL_PAGE_PRIVACY + Constant.STOREID);
                    } else if (shippingList.contains(speechLower)) {
                        getInstance().LoadWebVew(Constant.URL_PAGE_SHIPPING + Constant.STOREID);
                    } else if (supportList.contains(speechLower)) {
                        getInstance().LoadWebVew(Constant.URL_PAGE_SUPPORT + Constant.STOREID);
                    } else if (termsLis.contains(speechLower)) {
                        getInstance().LoadWebVew(Constant.URL_PAGE_TERMS + Constant.STOREID);
                    } else if (helpLis.contains(speechLower)) {
                        getInstance().LoadWebVew(Constant.URL_PAGE_HELP + Constant.STOREID);
                    } else if (storehourtList.contains(speechLower)) {
                        getInstance().LoadWebVew(Constant.URL_PAGE_STORE_HOURS + Constant.STOREID);
                    } else {
                        String customerId = "0";
                        if (UserModel.Cust_mst_ID != null)
                            customerId = UserModel.Cust_mst_ID;
                        else
                            customerId = "0";

                        getInstance().mContainer.removeAllViews();
                        getInstance().LoadWebVew(Constant.URL + "/inventory/inventoryapp"
                                + "?customerid=" + customerId
                                + "&sessionid=" + DeviceInfo.getDeviceId(getInstance()) + "0011"
                                + "&storeno=" + Constant.STOREID
                                + "&deptid=" + "0"
                                + "&subdeptid=" + "0"
                                + "&type=" + "allitem"
                                + "&filtertext=" + sp);
                    }
                }
                break;
            }

        }
    }

    @Override
    protected void onPause() {
        super.onPause();

    }

    public void launchMarket() {
        /*Uri uri = Uri.parse("market://details?id=" + getPackageName());
        Intent myAppLinkToMarket = new Intent(Intent.ACTION_VIEW, uri);
        try {
            startActivity(myAppLinkToMarket);
        } catch (ActivityNotFoundException e) {
            Toast.makeText(this, " unable to find market app", Toast.LENGTH_LONG).show();
        }*/

        try {
            Uri uri = Uri.parse("market://details?id=" + getPackageName() /*"com.healthcare_plus"*/ + "");
            Intent goMarket = new Intent(Intent.ACTION_VIEW, uri);
            startActivity(goMarket);
        } catch (ActivityNotFoundException e) {
            Uri uri = Uri.parse("https://play.google.com/store/apps/details?id=" + getPackageName() + "");
            Intent goMarket = new Intent(Intent.ACTION_VIEW, uri);
            startActivity(goMarket);
        }
    }

    public void shareApp() {
        try {
            Intent i = new Intent(Intent.ACTION_SEND);
            i.setType("text/plain");
            i.putExtra(Intent.EXTRA_SUBJECT, "Mobile Boxsalt");
            String sAux = "\nLet me recommend you this application\n\n";
            sAux = sAux + "https://play.google.com/store/apps/details?id=" + getApplication().getPackageName() + "\n\n";
            i.putExtra(Intent.EXTRA_TEXT, sAux);
            startActivity(Intent.createChooser(i, "choose one"));
        } catch (Exception e) {
            //e.toString();
        }
    }

    @Override
    public void onFCMTokenRegistrationResult(String response) {
        if (response != null) {
            if (response.equals("Success")) {
                // Toast.makeText(this, "Main : Token Registered Successfully", Toast.LENGTH_SHORT).show();
            } else {
//                Toast.makeText(MainActivityDup.this, "FCM failed", Toast.LENGTH_SHORT).show();
            }
        }
    }

    @Override
    public void onInit(int status) {
        if (status == TextToSpeech.SUCCESS) {

            myTTS.setOnUtteranceProgressListener(new UtteranceProgressListener() {
                @Override
                public void onDone(String utteranceId) {
                    if (utteranceId.contains("@@@")) {
                        startVoiceInput1();
                    }
                }

                @Override
                public void onError(String utteranceId) {
                }

                @Override
                public void onStart(String utteranceId) {

                }
            });
            if (myTTS.isLanguageAvailable(Locale.US) == TextToSpeech.LANG_AVAILABLE)
                myTTS.setLanguage(Locale.US);

            List<TextToSpeech.EngineInfo> egs = myTTS.getEngines();

            for (int i = 0; i < egs.size(); i++) {
                Log.e("Log", "Engines=" + egs.get(i).name);
            }
            // myTTS.setEngineByPackageName("");
        } else if (status == TextToSpeech.ERROR) {
            Toast.makeText(this, "Sorry! Text To Speech failed...", Toast.LENGTH_LONG).show();
        }
    }


    @android.support.annotation.RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private void speakWords(String speech, String utterenceKyeword) {
        // myTTS.setLanguage(new Locale("hin", "IND", "variant"));
        //if (Constant.LANG_FLAG == 1) {
        // myTTS.setLanguage(new Locale("ENG", "IND", "eng-ind-male"));
       /* } else if (Constant.LANG_FLAG == 3) {
            myTTS.setLanguage(new Locale("guj", "IND", "eng-ind-male"));
        } else if (Constant.LANG_FLAG == 2) {
            myTTS.setLanguage(new Locale("hin", "IND", "eng-ind-male"));
        }*/
        /*
         * set Voice male or female
         * */
        Set<String> a = new HashSet<>();
        a.add("female");
        Voice v = new Voice("en-us-x-sfg#female_2-local", new Locale("en", "US"), 400, 200, true, a);
        myTTS.setVoice(v);
        HashMap<String, String> map = new HashMap<String, String>();

        /*getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if (ByintentOrDroidSpeech == 1) {
                    imgRecord.setVisibility(View.GONE);
                    imgRecordStop.setVisibility(View.VISIBLE);
                } else {
                    onstop();
                }
                txtMicText.setText(getString(R.string.taptostop));
            }
        });*/
        try {
           /* if(speech.trim().contains("$#")){

            }
            else */
            //speech=
            if (speech.trim().equalsIgnoreCase("$#")) {
                speech = speech.replace("$#", "");
                try {
                    playBipSound();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                map.put(TextToSpeech.Engine.KEY_PARAM_UTTERANCE_ID, "DBCheck-YN");
                myTTS.speak(speech, TextToSpeech.QUEUE_FLUSH, map);
            } else {
                String[] splitspeech = null;
                int posi1 = 0;

                // speech="Let me check status of recent goals setup by you for reducing smoking.$# \\u003cbr/\\u003e I appreciate your concern about smoking habit and your desire to come out of that.";

                if (speech.length() > 3500) {
                    posi1 = speech.length() / 3500;
                    splitspeech = Utils.splitInEqualParts(speech, (posi1 + 1));
                } else {
                    splitspeech = speech.split("\\$#");
                }
                //splitspeech=splitspeech[0]+sp
                if (!utterenceKyeword.isEmpty()) {
                    map.put(TextToSpeech.Engine.KEY_PARAM_UTTERANCE_ID, utterenceKyeword);
                } else {
                    map.put(TextToSpeech.Engine.KEY_PARAM_UTTERANCE_ID, "null");
                }
                for (int i = 0; i < splitspeech.length; i++) {
                    if (i == 0) { // Use for the first splited text to flush on audio stream
                        if (i == (splitspeech.length - 1)) {
                            myTTS.speak(splitspeech[i].trim(), TextToSpeech.QUEUE_FLUSH, map);
                        } else {
                            myTTS.speak(splitspeech[i].trim(), TextToSpeech.QUEUE_FLUSH, map);
                        }
                    } else {
                        if (i == (splitspeech.length - 1)) {
                            myTTS.speak(splitspeech[i].trim(), TextToSpeech.QUEUE_ADD, map);
                        } else {
                            myTTS.speak(splitspeech[i].trim(), TextToSpeech.QUEUE_ADD, map);
                        }
                    }
                }

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    ToneGenerator tg;

    public void playBipSound() throws InterruptedException {
        tg.startTone(ToneGenerator.TONE_PROP_BEEP);
        Thread.sleep(1000);
        tg.startTone(ToneGenerator.TONE_PROP_BEEP);
        Thread.sleep(1000);
        tg.startTone(ToneGenerator.TONE_PROP_BEEP);
        Thread.sleep(1000);
    }

    private void startVoiceInput1() {
        Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault());
        intent.putExtra(RecognizerIntent.EXTRA_PROMPT, "BoxSalt");
        // intent.putExtra(RecognizerIntent.EXTRA_SPEECH_INPUT_COMPLETE_SILENCE_LENGTH_MILLIS,60000);
        //  intent1.putExtra(RecognizerIntent.EXTRA_SPEECH_INPUT_POSSIBLY_COMPLETE_SILENCE_LENGTH_MILLIS,60000);
        intent.putExtra("android.speech.extra.DICTATION_MODE", true);//Last added
        intent.putExtra(RecognizerIntent.EXTRA_PARTIAL_RESULTS, false);//Last added
        startActivityForResult(intent, REQ_CODE_SPEECH_INPUT1);
    }

    @Override
    public void onUtteranceCompleted(String utteranceId) {
        Log.e("Log", "utteranceId=" + utteranceId);
    }

    public void setBottomBack() {
        int[] colors = new int[]{
                getResources().getColor(R.color.black),
                getResources().getColor(R.color.black),
                getResources().getColor(R.color.black),
                getResources().getColor(R.color.black),
                getResources().getColor(R.color.black),
                getResources().getColor(R.color.black)

        };

        ColorStateList csl = new ColorStateList(new int[][]{new int[0]}, new int[]{colors[0]});

        navigationView.setItemIconTintList(csl);
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        if (item.isChecked())
            item.setChecked(false);
//        llsearch.setVisibility(View.GONE);
        RecHorizontalList.setVisibility(View.VISIBLE);
        switch (item.getItemId()) {

            case R.id.action_home:
                //Check the Item
                //setBottomBack();
                llsearch.setVisibility(View.GONE);
                if (llsortandfilter.getVisibility() == View.VISIBLE) {
                    llsortandfilter.setVisibility(View.GONE);
                }
                item.setChecked(true);
//                showHomePage();
//                loadhomedirectly();
                showHomePage();
                loadHomeWebPage();
                ActivityCompat.invalidateOptionsMenu(this);
                break;
            //Edited by Janvi 22th oct

//            case R.id.action_wishlist:
//                //Check the Item
//                item.setChecked(true);
//                //loadCardFragment();
//                loadWishListFragment();
//                //replaceDummyFragment(item);
//                ActivityCompat.invalidateOptionsMenu(this);
//                mContent.setVisibility(View.VISIBLE);
//                mContainer.setVisibility(View.GONE);
//                break;
            //end
            case R.id.action_search:
                item.setChecked(true);
                showHomePage();
                llsearch.setVisibility(View.VISIBLE);
                onShowShoppingCartIcon();
                ActivityCompat.invalidateOptionsMenu(this);
                mContent.setVisibility(View.GONE);

                mContainer.setVisibility(View.VISIBLE);
                // replaceDummyFragment(item);
                //onCreateOptionsMenu(Mymenu);
                break;
            case R.id.action_contactus:
                if (llsortandfilter.getVisibility() == View.VISIBLE) {
                    llsortandfilter.setVisibility(View.GONE);
                }
                item.setChecked(true);
                llsearch.setVisibility(View.GONE);
                //onShowShoppingCartIcon();
                ActivityCompat.invalidateOptionsMenu(this);
                //mContent.setVisibility(View.GONE);
                // mContainer.setVisibility(View.VISIBLE);

                loadContactUsFragment();
//                getInstance().LoadWebVew(Constant.URL_PAGE_CONTACT_US + Constant.STOREID);

                break;
            case R.id.action_profile:
                //Check the Item

                //Edited by Janvi 23th oct
                item.setChecked(true);
                llsearch.setVisibility(View.GONE);
                if (UserModel.Cust_mst_ID == null || UserModel.Cust_mst_ID.equals("0")) {
                    Login.StartLoginDialog(" ", this);
                }else{
                    mContent.setVisibility(View.VISIBLE);
                    mContainer.setVisibility(View.GONE);
                    replaceProfileFragment(item);
                }
                break;

            case R.id.action_others:
                //Check the Item

//                ActivityCompat.invalidateOptionsMenu(this);
//                mContent.setVisibility(View.VISIBLE);
//                mContainer.setVisibility(View.GONE);


                View view = findViewById(R.id.action_profile);
                item.setChecked(true);

                Context wrapper = new ContextThemeWrapper(this, R.style.MyPopupMenu);
                PopupMenu attachFilePopup = new PopupMenu(wrapper, view,Gravity.BOTTOM);


                //Edited by Janvi 24th Oct*************
                tempOtherslist = new ArrayList<>();

//                String OtherUrl1 = Constant.WS_BASE_URL + Constant.GETPAGES_STATUS + Constant.STOREID;
//                Log.d("URl","otherUrl::" +OtherUrl1);
//                new Async_getCommonService(this, OtherUrl1).execute();

                for (int i=0;i<Constant.Otherlist.size();i++){

                    if (Constant.Otherlist.get(i).PageName.equalsIgnoreCase("AboutUs") || Constant.Otherlist.get(i).PageName.equalsIgnoreCase("About Us"))
                        tempOtherslist.add(Constant.Otherlist.get(i).PageName);
                    else if (Constant.Otherlist.get(i).PageName.equalsIgnoreCase("FAQ"))
                        tempOtherslist.add(Constant.Otherlist.get(i).PageName);
                    else if (Constant.Otherlist.get(i).PageName.equals("legal") || Constant.Otherlist.get(i).PageName.equals("Legal"))
                        tempOtherslist.add(Constant.Otherlist.get(i).PageName);
                    else if (Constant.Otherlist.get(i).PageName.equals("pay_and_refund") || Constant.Otherlist.get(i).PageName.equals("Payments & Refunds"))
                        tempOtherslist.add(Constant.Otherlist.get(i).PageName);
                    else if (Constant.Otherlist.get(i).PageName.equalsIgnoreCase("Privacy"))
                        tempOtherslist.add(Constant.Otherlist.get(i).PageName);
                    else if (Constant.Otherlist.get(i).PageName.equalsIgnoreCase("Terms"))
                        tempOtherslist.add(Constant.Otherlist.get(i).PageName);
                    else if (Constant.Otherlist.get(i).PageName.equals("Supp_or_Cust") || (Constant.Otherlist.get(i).PageName.equals("Support & Customer Service")))
                        tempOtherslist.add(Constant.Otherlist.get(i).PageName);
                    else if (Constant.Otherlist.get(i).PageName.equalsIgnoreCase("Shipping"))
                        tempOtherslist.add(Constant.Otherlist.get(i).PageName);
                    else if(Constant.Otherlist.get(i).PageName.equalsIgnoreCase("WishList") || Constant.Otherlist.get(i).PageName.equalsIgnoreCase("Help"))
                        tempOtherslist.add("Help");

                    //end **********
                }


                for (String s : tempOtherslist) {
                    attachFilePopup.getMenu().add(s);
                }
//                attachFilePopup.inflate(R.menu.drawer_menu);
                attachFilePopup.setOnMenuItemClickListener(new OnPopupMenuItemClickListener());
                attachFilePopup.show();
                break;
            //end

            default:
                return super.onOptionsItemSelected(item);

        }
        return true;
    }

    private void loadhomedirectly() {

        int backStackEntry = 0;
        if (getFragmentManager() != null) {
            backStackEntry = getFragmentManager().getBackStackEntryCount();
        }
        if (backStackEntry > 0) {
            for (int i = 0; i < backStackEntry; i++) {
                if (getFragmentManager() != null) {
                    getFragmentManager().popBackStackImmediate();
                } else {
                    if (Constant.SCREEN_LAYOUT == 1) {
                        MainActivity.getInstance().onBackPressed();
                    } else if (Constant.SCREEN_LAYOUT == 2) {
                        MainActivityDup.getInstance().onBackPressed();
                    }

                }
            }
        }

        if (Constant.SCREEN_LAYOUT == 1) {
            MainActivity.showHomePage();
            MainActivity.getInstance().loadHomeWebPage();
            MainActivity.getInstance().clearFilterSelection();

        } else if (Constant.SCREEN_LAYOUT == 2) {
            MainActivityDup.showHomePage();
            MainActivityDup.getInstance().loadHomeWebPage();
            MainActivityDup.getInstance().clearFilterSelection();
        }

    }


    private void replaceProfileFragment(@NonNull MenuItem item) {
        llsortandfilter.setVisibility(View.GONE);
        mContainer.setVisibility(View.GONE);
        llcheckInternet.setVisibility(View.GONE);
        mContent.setVisibility(View.VISIBLE);

//        Edited by Janvi 26th Oct *****
//        RecHorizontalList.setVisibility(View.GONE); //end


        //Edited by Janvi 22th Oct *****

//        if (UserModel.Cust_mst_ID == null){
//            Login.StartLoginDialog("");
//        }else{
//
//            //popup
//        }

        //current
//        Bundle b = new Bundle();
//        b.putString(ProfileFragment_layout2.FRAGMENT_TITLE, item.getTitle().toString());
//        ProfileFragment_layout2 profileFragment_layout2 = new ProfileFragment_layout2();
//        profileFragment_layout2.setArguments(b);
//
//        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
//        fragmentTransaction.setCustomAnimations(android.R.anim.fade_in, android.R.anim.fade_out);
//        fragmentTransaction.replace(R.id.mContent, profileFragment_layout2).commitAllowingStateLoss();
//        fragmentTransaction.commitAllowingStateLoss();
//        invalidateOptionsMenu();
        // end


        // new added for profile by commenting above code
        llsortandfilter.setVisibility(View.GONE);
        mContainer.setVisibility(View.GONE);
        llcheckInternet.setVisibility(View.GONE);
        llsearch.setVisibility(View.GONE);
        mContent.setVisibility(View.VISIBLE);

        profileFragment_layout2 = new ProfileFragment_layout2();
        loadFragment(profileFragment_layout2, ProfileFragment_layout2.TAG);

        invalidateOptionsMenu();
        //end
    }

    @Override
    public void onSaveInstanceState(Bundle outState, PersistableBundle outPersistentState) {
        super.onSaveInstanceState(outState, outPersistentState);
    }

    @Override
    public void onRestoreInstanceState(Bundle savedInstanceState, PersistableBundle persistentState) {
        super.onRestoreInstanceState(savedInstanceState, persistentState);
    }

    /*public void replaceProfileFragment(Fragment fragment, String tag) {
        llsortandfilter.setVisibility(View.GONE);
        //llsearch.setVisibility(View.GONE);
        mContainer.setVisibility(View.GONE);
        llcheckInternet.setVisibility(View.GONE);
        mContent.setVisibility(View.VISIBLE);
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.setCustomAnimations(android.R.anim.fade_in, android.R.anim.fade_out);
        fragmentTransaction.replace(R.id.mContent, fragment, tag);
        //fragmentTransaction.addToBackStack(fragment.getClass().getSimpleName());
        fragmentTransaction.commitAllowingStateLoss();
    }*/

    public void callNotificationDialogWs() {
        String Url3 = Constant.WS_BASE_URL + Constant.GET_NOTIFICATION_CuSTERMER + Constant.STOREID + "/" + getUserId() + "/1/25";
        TaskNotificationList notificationList = new TaskNotificationList(this);
        notificationList.execute(Url3);
    }

    public void callNotificationDialog(List<NotificationModel> notifList) {
        Dialog NotificationDialog = null;
        View notifView = null;
        NotificationDialog = new Dialog(this, R.style.DialogSlideAnim_login);
        NotificationDialog.setCanceledOnTouchOutside(true);
        notifView = LayoutInflater.from(this).inflate(R.layout.notificaton_dialog, null);
        RecyclerView rec_notif = notifView.findViewById(R.id.rec_notif);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        rec_notif.setLayoutManager(layoutManager);
        rec_notif.setHasFixedSize(true);
        NotificationListAdapter notificationListAdapter = new NotificationListAdapter(this, this, notifList);
        rec_notif.setAdapter(notificationListAdapter);
        TextView txtNotification = notifView.findViewById(R.id.txtNotification);
        TextView txtclose = notifView.findViewById(R.id.txtclose);
        txtNotification.setTextColor(Color.parseColor(Constant.themeModel.ThemeColor));
        /*WindowManager.LayoutParams params = NotificationDialog.getWindow().getAttributes();
        params.width = WindowManager.LayoutParams.MATCH_PARENT;
        params.height = WindowManager.LayoutParams.MATCH_PARENT;*/
        int width = (int) (getResources().getDisplayMetrics().widthPixels * 0.94);
        int height = (int) (getResources().getDisplayMetrics().heightPixels * 0.94);

        NotificationDialog.getWindow().setLayout(width, height);
        NotificationDialog.setContentView(notifView);
        NotificationDialog.getWindow().setGravity(Gravity.CENTER);
        WindowManager.LayoutParams layoutParam = NotificationDialog.getWindow().getAttributes();
        NotificationDialog.getWindow().setAttributes(layoutParam);

        if (!NotificationDialog.isShowing()) {
            NotificationDialog.show();
        }
        final Dialog finalNotificationDialog = NotificationDialog;
        txtclose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finalNotificationDialog.dismiss();

            }
        });
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
    public void onGetNotificationResult(List<NotificationModel> NotificationList) {
        //if()
        Constant.NOTIFICATION_LIST = NotificationList;
        if (!getUserId().trim().equalsIgnoreCase("0")) {
            MbsDataModel mbs4 = new MbsDataModel();
            mbs4.ID = "0";
            mbs4.PageName = "Notifications";
            mbs4.PageTitle = "Notifications";
            mbs4.PageContent = "Notifications";
            mbs4.status = true;
            mbs4.position = Constant.TopHeaderMenuList.size() + 1;
            if (!contains(Constant.TopHeaderMenuList, "Notifications") && Constant.NOTIFICATION_LIST.size() > 0) {
                Constant.TopHeaderMenuList.add(mbs4);
            }
        }
        //onSetDrawerMenu();
        //callNotificationDialog(NotificationList);
    }

    //Edited by Janvi 15thOct
    @Override
    public void onWishListResult(WishList wishList, String string) {

        if (wishList.getResult().equals("success")) {
            DialogUtils.showDialog("Added in WishList!");
        } else {
            DialogUtils.showDialog("Already in WishList!");
        }

    }

    public void loadGiftCardFragment() {

        llsortandfilter.setVisibility(View.GONE);
        mContainer.setVisibility(View.GONE);
        llcheckInternet.setVisibility(View.GONE);
        mContent.setVisibility(View.VISIBLE);
        llsearch.setVisibility(View.GONE);

        giftCardFragment = new GiftCardFragment();
        loadFragment(giftCardFragment, GiftCardFragment.TAG);

        invalidateOptionsMenu();
    }

    public void loadContactUsFragment() {
//
        llsortandfilter.setVisibility(View.GONE);
        mContainer.setVisibility(View.GONE);
        llcheckInternet.setVisibility(View.GONE);
        llsearch.setVisibility(View.GONE);
        mContent.setVisibility(View.VISIBLE);

        contactUsFragment = new ContactUsFragment();
        loadFragment(contactUsFragment, ContactUsFragment.TAG);

        invalidateOptionsMenu();
    }

    public void loadAboutusFragment() {

        llsortandfilter.setVisibility(View.GONE);
        llsearch.setVisibility(View.GONE);
        mContainer.setVisibility(View.GONE);
        llcheckInternet.setVisibility(View.GONE);
        mContent.setVisibility(View.VISIBLE);

        aboutUsFragment = new AboutUsFragment();
        loadFragment(aboutUsFragment, AboutUsFragment.TAG);

        invalidateOptionsMenu();
    }

    public void loadChangePasswordFragment() {

        llsortandfilter.setVisibility(View.GONE);
        mContainer.setVisibility(View.GONE);
        llsearch.setVisibility(View.GONE);
        llcheckInternet.setVisibility(View.GONE);
        mContent.setVisibility(View.VISIBLE);

        changePassword = new ChnagePasswordFragment();
        loadFragment(changePassword, ChnagePasswordFragment.TAG);

        invalidateOptionsMenu();
    }


    public void loadViewAllFragment(String type, String deptid, String styleId, String blockStratprice, String blockEndprice, String blockdisountGroup, String blockDisplayedText, String s, String departmentVal) {

        llsortandfilter.setVisibility(View.VISIBLE);
        llsearch.setVisibility(View.GONE);
        mContainer.setVisibility(View.GONE);
        llcheckInternet.setVisibility(View.GONE);
        mContent.setVisibility(View.VISIBLE);
        Constant.isviewall_page = true;

        Bundle bundle = new Bundle();
        bundle.putString("type", type);
        bundle.putString("deptId", String.valueOf(deptid));
        bundle.putString("SubId", String.valueOf(styleId));
        bundle.putString("blockStratprice",blockStratprice);
        bundle.putString("blockEndprice", blockEndprice);
        bundle.putString("blockdisountGroup", blockdisountGroup);
        bundle.putString("blockDisplayedText", blockDisplayedText);
        bundle.putString("shortcall",shortCall);
        bundle.putString("shortdept",shortDept);
        bundle.putString("searchtext", searchText);
        bundle.putBoolean("iscomefromSearchIcon",isSearchicon);
        bundle.putString("OnlyDepartment",departmentVal);


        viewAllFragment = new ViewAllFragment();
        viewAllFragment.setArguments(bundle);

//        String name = getSupportFragmentManager().getBackStackEntryAt(0).getName();
//        getSupportFragmentManager().popBackStack(name, FragmentManager.POP_BACK_STACK_INCLUSIVE);

        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.setCustomAnimations(android.R.anim.fade_in, android.R.anim.fade_out);
        fragmentTransaction.replace(R.id.mContent, viewAllFragment, ViewAllFragment.TAG);
        fragmentTransaction.addToBackStack(null);
//        fragmentTransaction.addToBackStack("viewallTag");
        fragmentTransaction.commitAllowingStateLoss();

    }

    public void sendViewalldata(String deptid, String subdeptId, String type, String blockdisountGroup, String blockStratprice, String blockEndprice, String blockDisplayedText) {
        deptId = deptid;
        subDepartment = subdeptId;
        MainActivityDup.type = type;
        specialOfferGroup = blockdisountGroup;
        valueOne = blockStratprice;
        valueTwo = blockEndprice;
        this.blockDisplayedText = blockDisplayedText;

    }

    public void loadStoreandDeliveryFragment(String store_deliveryBtnText) {

        llsortandfilter.setVisibility(View.GONE);
        mContainer.setVisibility(View.GONE);
        llcheckInternet.setVisibility(View.GONE);
        mContent.setVisibility(View.VISIBLE);
        llsearch.setVisibility(View.GONE);

        storeDeliveryHourFragment = new StoreDeliveryHourFragment();
        loadFragment(storeDeliveryHourFragment, StoreDeliveryHourFragment.TAG);

        Bundle bundle = new Bundle();
        bundle.putString("store_deliveryBtnText", store_deliveryBtnText);
        storeDeliveryHourFragment.setArguments(bundle);

        invalidateOptionsMenu();
    }

    public void getCustomerData(UserModel userModel) {
        userModeltemp = userModel;

        if (userModel.Cust_mst_ID != null) {
            String url = Constant.WS_BASE_URL + Constant.GET_CUSTOMER_DATA + userModel.Cust_mst_ID + "/" + Constant.STOREID;
            TaskCustomerData taskCustomerData = new TaskCustomerData(MainActivityDup.this, this);
            Log.d("", "Customer data : " + url);
            taskCustomerData.execute(url);
        }
    }

    @Override
    public void onTaskCustomerResult(ShippingData liShippingData, boolean isFromfavouriteStore) {

        Constant.liShippingData = liShippingData;

        if (isFromfavouriteStore) {
            if(liShippingData != null){
                if(liShippingData.getResult() != null && !liShippingData.getResult().isEmpty()){
                    if(liShippingData.getResult().equalsIgnoreCase("success")){
                        getCustomerData();
                    }
                }
            }

        } else {

            if(liShippingData != null){

                if(liShippingData.getFavLocation() != null && !liShippingData.getFavLocation().isEmpty()){
                    Constant.favstorelocation = liShippingData.getFavLocation();
                    Constant.AppPref.edit().putString("favStore",Constant.favstorelocation).apply();
                }
            }

            if (isComeFromSigninManageAccount || isComeFromCartIconManageAccount) {

            if (Constant.liShippingData != null) {

                if (Constant.liShippingData.getPhone() == null || Constant.liShippingData.getPhone().trim().isEmpty() || Constant.liShippingData.getPhone().trim().equals(" ")) {
                    DialogUtils.onWarningDialog(this, "Manage Account", "Your profile needs to be updated.");

                } else if (Constant.liShippingData.getAddress1() == null || Constant.liShippingData.getAddress1().trim().isEmpty() || Constant.liShippingData.getAddress1().trim().equals(" ")) {
                    DialogUtils.onWarningDialog(this, "Manage Account", "Your profile needs to be updated.");

                }
//                    else if (Constant.liShippingData.getAddress2() == null || Constant.liShippingData.getAddress2().trim().isEmpty() || Constant.liShippingData.getAddress2().trim().equals(" ")) {
//                        DialogUtils.onWarningDialog(this, "Manage Account", "Your profile needs to be updated.");
//
//                    }
                else if (Constant.liShippingData.getCity() == null || Constant.liShippingData.getCity().trim().isEmpty() || Constant.liShippingData.getCity().trim().equals(" ")) {
                    DialogUtils.onWarningDialog(this, "Manage Account", "Your profile needs to be updated.");

                } else if (Constant.liShippingData.getState() == null || Constant.liShippingData.getState().trim().isEmpty() || Constant.liShippingData.getState().trim().equals(" ")) {
                    DialogUtils.onWarningDialog(this, "Manage Account", "Your profile needs to be updated.");

                } else if (Constant.liShippingData.getZip() == null || Constant.liShippingData.getZip().trim().isEmpty() || Constant.liShippingData.getZip().trim().equals(" ")) {
                    DialogUtils.onWarningDialog(this, "Manage Account", "Your profile needs to be updated.");
                } else {
                    if (isComeFromSigninManageAccount) {
                        Login.onLoginSuccess(userModeltemp);
                        isComeFromSigninManageAccount = false;

                    } else if (isComeFromCartIconManageAccount) {
                        loadCardFragmentDetails();
                        isComeFromCartIconManageAccount = false;
                    }
                }

                if (isComeFromSigninManageAccount) {
                    isComeFromSigninManageAccount = false;
                }
                if (isComeFromCartIconManageAccount) {
                    isComeFromCartIconManageAccount = false;
                }

            } else if (isComeFromSigninManageAccount) {
                Login.onLoginSuccess(userModeltemp);
                isComeFromSigninManageAccount = false;
            }

        }
    }
    }

    public void callFilterDetailFragment() {

        llsearch.setVisibility(View.GONE);
        mContainer.setVisibility(View.GONE);
        Log.e("Log", "llsortandfilter=6");
        llsortandfilter.setVisibility(View.GONE);
        llcheckInternet.setVisibility(View.GONE);
        mContent.setVisibility(View.VISIBLE);

        if (filterFragment == null) {
            filterFragment = new FilterFragment();
        }

            FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
            fragmentTransaction.setCustomAnimations(android.R.anim.fade_in, android.R.anim.fade_out);
            fragmentTransaction.replace(R.id.mContent, filterFragment, "fragment");
//            fragmentTransaction.addToBackStack(FilterFragment.class.getSimpleName());
            fragmentTransaction.addToBackStack(null);
            fragmentTransaction.commitAllowingStateLoss();

    }

    public void callFilterInfoForSubdepartment() {

        String url;

        url = Constant.WS_BASE_URL + Constant.GET_FILTER_INFO
                + "/" + Constant.STOREID;

        TaskFilterInfo taskFilterInfo = new TaskFilterInfo(this,this,"isForDepartment");
        taskFilterInfo.execute(url);
    }

    public void loadCardOnFileFragment() {

        llsortandfilter.setVisibility(View.GONE);
        mContainer.setVisibility(View.GONE);
        llsearch.setVisibility(View.GONE);
        llcheckInternet.setVisibility(View.GONE);
        mContent.setVisibility(View.VISIBLE);

        cardOnFileFragment = new CardOnFileFragment();
        loadFragment(cardOnFileFragment, CardOnFileFragment.TAG);

        invalidateOptionsMenu();
    }

    public void loadManageAccountFragment() {
        llsortandfilter.setVisibility(View.GONE);
        mContainer.setVisibility(View.GONE);
        llcheckInternet.setVisibility(View.GONE);
        mContent.setVisibility(View.VISIBLE);
        llsearch.setVisibility(View.GONE);

        manageAccountFragment = new ManageAccountFragment();
        loadFragment(manageAccountFragment, ManageAccountFragment.TAG);

        invalidateOptionsMenu();

    }


    public void loadOrderHistoryFragment(String orderHistoryBtnText) {
        llsortandfilter.setVisibility(View.GONE);
        mContainer.setVisibility(View.GONE);
        llcheckInternet.setVisibility(View.GONE);
        mContent.setVisibility(View.VISIBLE);
        llsearch.setVisibility(View.GONE);

        orderHistoryFragment = new OrderHistoryFragment();
        loadFragment(orderHistoryFragment, OrderHistoryFragment.TAG);

        Bundle bundle = new Bundle();
        bundle.putString("orderHistoryBtnText", orderHistoryBtnText);
        orderHistoryFragment.setArguments(bundle);

        invalidateOptionsMenu();

    }

    public void loadSearchLocationWSdata(Context context, String searchtext) {

        String storeLocationURL = Constant.WS_BASE_URL + Constant.GET_CORPORATE_STORE_SUBSTORELIST + "/" + Constant.STOREID + "/" + searchtext + "/" +"search" ;
        TaskStoreLocationInfo taskStoreLocationInfo = new TaskStoreLocationInfo(this,context,true);
        taskStoreLocationInfo.execute(storeLocationURL);
    }

    public void callCusDefaultFavLocationWS(Context context, String storeno) {

        if (userModeltemp.Cust_mst_ID != null) {
            String url = Constant.WS_BASE_URL + Constant.SAVE_CUS_DEFAULT_LOCATION + "/" + storeno + "/" + userModeltemp.Cust_mst_ID;
            TaskCustomerData taskCustomerData = new TaskCustomerData(context, this, true, true);
            Log.d("", "Customer data : " + url);
            taskCustomerData.execute(url);
        }

    }

    public void callBuyItAgainWS(String orderid, String itemid, String requestdQty) {

        String buyitAgainUrl;
        buyitAgainUrl = WS_BASE_URL + Constant.RE_BUYITAGAIN_DATA + orderid + "/" + itemid + "/" + STOREID;
        TaskReOrder taskCart = new TaskReOrder(MainActivityDup.this,this,"buyItAgain",requestdQty);
        taskCart.execute(buyitAgainUrl);
    }

    public void loadShippingAddressFragment() {

        llsortandfilter.setVisibility(View.GONE);
        mContainer.setVisibility(View.GONE);
        llcheckInternet.setVisibility(View.GONE);
        mContent.setVisibility(View.VISIBLE);
        llsearch.setVisibility(View.GONE);

        shippingAddressFragment = new ShippingAddressFragment();
        loadFragment(shippingAddressFragment, ShippingAddressFragment.TAG);

//        Bundle bundle = new Bundle();
//        bundle.putString("shippingaddBtnText", shippingaddBtnText);
//        shippingAddressFragment.setArguments(bundle);

        invalidateOptionsMenu();
    }


    public void loadEditShippingFragmentDialog(String shippingId) {

        callshippingAddressByID(shippingId);
    }

    private void callshippingAddressByID(String shippingId) {

        if(!shippingId.trim().isEmpty()){
            String url = Constant.WS_BASE_URL + Constant.GET_SHIPPING_ADDRESS_BY_ID + "/" + UserModel.Cust_mst_ID + "/" + Constant.STOREID + "/" + shippingId;
            TaskEditShipping taskCustomerData = new TaskEditShipping(this, this);
            Log.d("", "shipping data : " + url);
            taskCustomerData.execute(url);
        }
    }

    @Override
    public void onTaskEditShippingResult(List<EditShippingData> liShippingData) {

        FragmentManager manager = getSupportFragmentManager();
        EditShippingAddressFragment mydialog = new EditShippingAddressFragment();
        Constant.liShippingEditData = liShippingData;
//        Bundle bundle = new Bundle();
//        bundle.putString("shippingId",shippingId);
//        mydialog.setArguments(bundle);
        mydialog.show(manager, "mydialog");
    }

    @Override
    public void onEditShippingFragmentDialog(String data) {
//        Toast.makeText(getApplicationContext(), "data" + " button clicked",
////                Toast.LENGTH_SHORT).show();
    }

    public void hidebackbutton() {

//        Toolbar toolbar = findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);
        setSupportActionBar(mToolbar);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayShowTitleEnabled(false);
            getSupportActionBar().setHomeButtonEnabled(false);
            getSupportActionBar().setDisplayHomeAsUpEnabled(false);
            getSupportActionBar().setDisplayShowHomeEnabled(false);
        }
    }


    public void showbackButton() {

//        Toolbar toolbar = findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);
        setSupportActionBar(mToolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayShowTitleEnabled(false);
            getSupportActionBar().setHomeButtonEnabled(false);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }
    }


    private class OnPopupMenuItemClickListener implements PopupMenu.OnMenuItemClickListener{

        @Override
        public boolean onMenuItemClick(MenuItem item) {

            String slideURL = null;

            if(item.toString().equals("About Us")){
//                slideURL = Constant.URL_PAGE_ABOUT_US + Constant.STOREID;

                if (Constant.SCREEN_LAYOUT == 1) {
                    MainActivity.getInstance().loadAboutusFragment();
                } else if (Constant.SCREEN_LAYOUT == 2) {
                    MainActivityDup.getInstance().loadAboutusFragment();
                }

            }else if(item.toString().equals("FAQ")){
                slideURL = Constant.URL_PAGE_FAQ + Constant.STOREID;
            }else if(item.toString().equals("legal") || item.toString().equals("Legal")){
                slideURL = Constant.URL_PAGE_LEGAL + Constant.STOREID;
            }else if(item.toString().equals("pay_and_refund") || item.toString().equals("Payments & Refunds")){
                slideURL = Constant.URL_PAGE_PAYMENTS_REFUNDS + Constant.STOREID;
            }else if(item.toString().equals("Privacy")){
                slideURL = Constant.URL_PAGE_PRIVACY + Constant.STOREID;
            }else if(item.toString().equals("Terms")){
                slideURL = Constant.URL_PAGE_TERMS + Constant.STOREID;
            }else if(item.toString().equals("Support & Customer Service") || item.toString().equals("Supp_or_Cust")){
                slideURL = Constant.URL_PAGE_SUPPORT + Constant.STOREID;
            }else if(item.toString().equals("Shipping")){
                slideURL = Constant.URL_PAGE_SHIPPING + Constant.STOREID;
            }else if(item.toString().equals("Help")){
                slideURL = Constant.URL_PAGE_HELP + Constant.STOREID;
            }

            if (slideURL != null) {
                Log.d("pages","SlideUrl::"+slideURL);
                if(llsortandfilter.isShown()){
                    llsortandfilter.setVisibility(View.GONE);
                }
                LoadWebVew(slideURL);
            }

            return false;
        }
    }
    //end***********

    public void loadItemDescriptionFragment(String itemId, String fragmentname_forhide) {

        llsortandfilter.setVisibility(View.GONE);
        mContainer.setVisibility(View.GONE);
        llcheckInternet.setVisibility(View.GONE);
        mContent.setVisibility(View.VISIBLE);
        llsearch.setVisibility(View.GONE);


        Bundle bundle = new Bundle();
        bundle.putString("itemMstId", itemId);

        itemDescriptionsFragment = new ItemDescriptionsFragment();
        itemDescriptionsFragment.setArguments(bundle);

        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.setCustomAnimations(android.R.anim.fade_in, android.R.anim.fade_out);
        fragmentTransaction.replace(R.id.mContent, itemDescriptionsFragment, ItemDescriptionsFragment.TAG);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commitAllowingStateLoss();
        llsortandfilter.setVisibility(View.GONE);

        invalidateOptionsMenu();

    }

    @Override
    public void contactInfoEventResult(ContatInfo contatInfo) {

        Constant.contatInfo = contatInfo;

        loadStoreLocationWSdata();

//        DialogUtils.on21YearFaster(MainActivityDup.this,twentyOneYear);
    }

    private void loadStoreLocationWSdata() {

        String zip = " ";

        if(Constant.contatInfo.getZip() != null && !Constant.contatInfo.getZip().isEmpty()){
            zip = Constant.contatInfo.getZip();

            String storeLocationURL = Constant.WS_BASE_URL + Constant.GET_CORPORATE_STORE_SUBSTORELIST + "/" + Constant.STOREID + "/" + zip;
            TaskStoreLocationInfo taskStoreLocationInfo = new TaskStoreLocationInfo(this,MainActivityDup.this, false);
            taskStoreLocationInfo.execute(storeLocationURL);
        }
    }

    @Override
    public void storeLocationInfoResult(List<StoreLocationModel> storeLocationList, Boolean isSearchLocation) {

        if(isSearchLocation){
            Constant.storeSearchLocationList.clear();

            if(storeLocationList != null && storeLocationList.size()>0) {
                Constant.storeSearchLocationList = storeLocationList;
            }

            callStorehoursWSForAllStores(MainActivityDup.this,isSearchLocation,Constant.storeSearchLocationList);

        }else if(storeLocationList != null && storeLocationList.size()>0){
            Constant.storeLocationList = storeLocationList;

            for(int i=0;i<Constant.storeLocationList.size();i++){
                if(Constant.STOREID.equals(Constant.storeLocationList.get(i).getStoreno())){
                    Constant.co_storeno_value = Constant.storeLocationList.get(i).getCoStoreno();
                }
            }
        }

        if (this.twentyOneYear != null) {

            if (twentyOneYear.getCustAgeValidOption() == 1) {
                DialogUtils.on21YearHome(twentyOneYear);

            } else if (twentyOneYear.getCustAgeValidOption() == 2 ||
                    twentyOneYear.getCustAgeValidOption() == 0) {

                //get store location from resonse of this ws
                // check for location permission
                if(Constant.co_storeno_value != null && !Constant.co_storeno_value.isEmpty()){
                    takeLocationPermissionFromUser();
                }

            }else if(twentyOneYear.getCustAgeValidOption() == 3){
                DialogUtils.on21YearFaster(MainActivityDup.this, twentyOneYear);
            }
        }

        CheckUserLogin();
    }

    public void takeLocationPermissionFromUser() {

         try {
            if (ContextCompat.checkSelfPermission(getApplicationContext(), android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED ) {
                ActivityCompat.requestPermissions(this, new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION}, 101);
            }else{
                getLocation();
            }

        } catch (Exception e){
            e.printStackTrace();
        }

    }

    public void callStorehoursWSForAllStores(Context context, Boolean isSearchLocation, List<StoreLocationModel> storeLocationList) {

        Constant.StoreCloseList.clear();
        isSearchLocationVal = isSearchLocation;

        int lastpos = storeLocationList.size() - 1;
        boolean isLastStore = false;

        for (int i = 0; i < storeLocationList.size(); i++) {
            if (storeLocationList.get(i).getStoreno() != null && !storeLocationList.get(i).getStoreno().isEmpty()) {

                String currentStoreNo = storeLocationList.get(i).getStoreno();

                if (lastpos == i) {
                    isLastStore = true;
                } else {
                    isLastStore = false;
                }
                String Url = Constant.WS_BASE_URL + Constant.GET_DELIVERY_HOURS + "/" + currentStoreNo + "/" + "store";
                TaskAllStoreHours allStoreHours = new TaskAllStoreHours(this, context, currentStoreNo, isLastStore);
                allStoreHours.execute(Url);
            }
        }
    }

    @Override
    public void onGetStoreHoursResult(List<StoreHour> storeHourList, String currentStoreNo, boolean isLastStoreForWSCall, Context context) {

        StoreclosesModel storeclosesModel = new StoreclosesModel();

        String closetime = Utils.getTodaysCloseTime(storeHourList);
        storeclosesModel.setClosetime(closetime);
        storeclosesModel.setStoreno(currentStoreNo);

        Constant.StoreCloseList.add(storeclosesModel);

        if(isLastStoreForWSCall){
            if(isSearchLocationVal){
                Utils.setChangeLocationadpater(context,Constant.storeSearchLocationList);
            }else{
                Utils.setChangeLocationadpater(context,Constant.storeLocationList);
            }

        }
    }


    private JSONObject insertReorderRequest(List<ReOrderItemModel> selectedReorderList) {

        final JSONObject requestBody = new JSONObject();

        try {

            JSONArray jsonArray = new JSONArray();

            JSONObject formJsonObject = new JSONObject();
            for (int i = 0; i < selectedReorderList.size(); i++) {
                ReOrderItemModel reOrderItemModel = selectedReorderList.get(i);
                formJsonObject.put("CustomerID", reOrderItemModel.getCustomerID());
                formJsonObject.put("StoreNo", reOrderItemModel.getStoreNo());
                formJsonObject.put("ItemID", reOrderItemModel.getItemID());
                formJsonObject.put("Qty", reOrderItemModel.getQty());
            }
            jsonArray.put(formJsonObject);
            requestBody.put("ReOrderDetails",jsonArray);


            Log.e("" , "==>: Insert Transaction request : " + requestBody);

        }catch (JSONException e){
            e.printStackTrace();
        }

        return  requestBody;
    }


    private void callInsertReporderWS(Context context)  {

        final int SOCKET_TIMEOUT_MS = 30000;
        insertReorderRequest(selectedReorderList);

        JSONObject jsonObjRequest = insertReorderRequest(selectedReorderList);

        com.android.volley.RequestQueue requestQueue = Volley.newRequestQueue(context);

        final String mRequestBody = jsonObjRequest.toString();

        StringRequest stringRequest = new StringRequest(Request.Method.POST, WS_INSERT_REORDER, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.i("LOG_RESPONSE", response);
                reOrderSuccessResponse(response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("LOG_RESPONSE", error.toString());
            }
        }) {
            @Override
            public String getBodyContentType() {
                return "application/json; charset=utf-8";
            }

            @Override
            public byte[] getBody() throws AuthFailureError {
                try {
                    return mRequestBody == null ? null : mRequestBody.getBytes("utf-8");
                } catch (UnsupportedEncodingException uee) {
                    VolleyLog.wtf("Unsupported Encoding while trying to get the bytes of %s using %s", mRequestBody, "utf-8");
                    return null;
                }
            }

            @Override
            protected Response<String> parseNetworkResponse(NetworkResponse response) {
                String responseString = "";
                if (response != null) {
                    responseString = String.valueOf(response);
                }
                return Response.success(responseString, HttpHeaderParser.parseCacheHeaders(response));
            }
        };

        requestQueue.add(stringRequest);


    }


    private void reOrderSuccessResponse(String s) {

//        showReOrderConfirmation(orderid, 2);

        OrderSummaryFragment.getInstence().backOrDismissFrag();
        loadCardFragment();
    }

//    Edited by Varun for Delete Account

    public void loadDeleteAccountWS() {

//        Toast.makeText(MainActivityDup, "dsgsfg", Toast.LENGTH_SHORT).show();
//        DialogUtils.Delete_pop_up(getApplicationContext(), Custormer_Id);
        DialogUtils.Delete_pop_up(this, Custormer_Id);

    }

    public void loadDeletetest(Context context, String cust_mst_id) {
        String url = Constant.WS_BASE_URL + Constant.GET_DELETE_CUSTOMER_ACCOUNT_FROM_ECOM + "/" + Constant.STOREID+ "/" + UserModel.Cust_mst_ID  + "/" + ENCODE_TOKEN_ID;
        TaskDeleteAccount taskDeleteAccount = new TaskDeleteAccount((TaskDeleteAccount.TaskDeleteAccountEvent) this);
        Log.d("", "Delete Account : " + url);
        taskDeleteAccount.execute(url);
    }

    @Override
    public void onGetDeleteAccountResult(DeleteAccountModel deleteAccountModel) {
        if (deleteAccountModel.getResult().equalsIgnoreCase("Success")){
//            Toast.makeText(this, "Success", Toast.LENGTH_SHORT).show();
            Utils.showValidationDialog(this,deleteAccountModel.getMessage(),"Delete my online Account");
//            Login.onLogOff();
        }else{
//            Toast.makeText(this, "Fail" + deleteAccountModel.getMessage(), Toast.LENGTH_SHORT).show();
            Utils.showValidationDialog(this,deleteAccountModel.getMessage(),"Delete Account Fail");
        }
    }
//END
}
