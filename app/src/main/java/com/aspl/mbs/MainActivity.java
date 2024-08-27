package com.aspl.mbs;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
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
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.speech.RecognizerIntent;
import android.speech.tts.TextToSpeech;
import android.speech.tts.UtteranceProgressListener;
import android.speech.tts.Voice;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.drawable.DrawableCompat;
import androidx.core.view.GravityCompat;
import androidx.core.view.MenuItemCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.appcompat.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.DisplayMetrics;
import android.util.Log;
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
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.ExpandableListView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
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
import com.aspl.Adapter.ExpandAdapter;
import com.aspl.Adapter.FilterAdapter;
import com.aspl.Adapter.FilterChoiceAdapter;
import com.aspl.Adapter.FilterItemAdapter;
import com.aspl.Adapter.FilterSelectedItemAdapter;
import com.aspl.Adapter.FooterAdapter;
import com.aspl.Adapter.NotificationListAdapter;
import com.aspl.Adapter.ReorderItemAdapter;
import com.aspl.Adapter.UsernameAdapter;
import com.aspl.Utils.Constant;
import com.aspl.Utils.DeviceInfo;
import com.aspl.Utils.DialogUtils;
import com.aspl.Utils.DrawableClickListener;
import com.aspl.Utils.EditTextCloseEvent;
import com.aspl.Utils.GpsTracker;
import com.aspl.Utils.ObservableWebView;
import com.aspl.Utils.Res;
import com.aspl.Utils.SwipeHelper;
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
import com.aspl.mbsmodel.ItemDescModel;
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
import com.aspl.mbsmodel.UpdateCartQuantity;
import com.aspl.mbsmodel.UserModel;
import com.aspl.mbsmodel.WishList;
import com.aspl.task.TaskAddtoCart;
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
import com.aspl.task.TaskItemDescription;
import com.aspl.task.TaskNotificationList;
import com.aspl.task.TaskReOrder;
import com.aspl.task.TaskSearch;
import com.aspl.task.TaskSessionToCart;
import com.aspl.task.TaskStoreDeliveryHours;
import com.aspl.task.TaskStoreLocationInfo;
import com.aspl.task.TaskTwentyOneYear;
import com.aspl.task.TaskUpdatetoCart;
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
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Objects;
import java.util.Set;

import static com.aspl.Utils.Constant.*;

public class MainActivity extends BaseActivity implements View.OnClickListener
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
        , FilterItemAdapter.FilterAdapterEvent
        , TaskFCMTokenRegister.TaskFCMTokenRegistrationListener,
        TextToSpeech.OnInitListener, TextToSpeech.OnUtteranceCompletedListener
        , TaskReOrder.TaskReOrderEvent
        , TaskNotificationList.TaskNotificationEvent, TaskDeleteAccount.TaskDeleteAccountEvent
        , NotificationListAdapter.NotificationListAdapterEvent, TaskDeleteWishList.TaskDeleteCartItemEvent, TaskItemDescription.TaskItemDescInterface, TaskContactInfo.TaskContactInfoEvent, TaskFilterInfo.TaskFilterInfoEvent, TaskCustomerData.TaskCustomerEvent, TaskStoreDeliveryHours.StoreDeliveryHoursEvent, TaskStoreLocationInfo.TaskStoreLocationEvent, TaskAllStoreHours.StoreHoursEvent, TaskAddtoCart.TaskAddToCartEvent, TaskUpdatetoCart.TaskUpdateCart, TaskEditShipping.TaskEditShippingEvent {
    public static boolean isPromotion;
    public static boolean isswiped = false;
    public static String TempdeptId = "0";
    private static boolean isNoNeedToOpenFilterDialog = false;
    public RecyclerView rvReorder;
    //    public UsernameAdapter usernameAdapter;
//    RecyclerView rvUsername;
    public ReorderItemAdapter reorderAdapter;
    public ObservableWebView mContainer;
    public LinearLayout lldrawer, mContent, llsortandfilter/*, llsearch*/, llcheckInternet;
    public RelativeLayout llsearch ;
//    Edited by Varun for reward point show below the search bar
    public LinearLayout   ll_Reward_main;
    public TextView tv_Reward_point_main ,tv_points_main , tv_rebate_point_main , tv_rebate_main;
//    END
    Toolbar mToolbar;
    public static ActionBarDrawerToggle actionBarDrawerToggle;
    public DrawerLayout mDrawer;
    public  /*EditText*/ AutoCompleteTextView mSearchedt;
    public ExpandableListView mManage_expList;
    public ListView bottom_list;
    private TextView txthome, txtevent_cal;
    ImageView imgHome;
    RelativeLayout view_container;
    ImageButton btndept;
    public PopupWindow listPopupWindow;
    public static MainActivity mainActivity;
    LinearLayout llFilter, llSort ;
    //Fragment fragment = null;
    CardFragment cardFragment;
    public PopupWindow popupWindow;
    WishListFragment wishListFragment;
    GiftCardFragment giftCardFragment;
    RewardFragment rewardFragment;
    AboutUsFragment aboutUsFragment;
    ChnagePasswordFragment changePassword;
    CardOnFileFragment cardOnFileFragment;
    ManageAccountFragment manageAccountFragment;
    HomepageFragment homepageFragment;
    ContactUsFragment contactUsFragment;
    StoreDeliveryHourFragment storeDeliveryHourFragment;
    OrderHistoryFragment orderHistoryFragment;
    ShippingAddressFragment shippingAddressFragment;
    FilterFragment filterFragment;
    ItemDescriptionsFragment itemDescriptionsFragment;
    ViewAllFragment viewAllFragment;
    public static DeliveryOptionsFragment deliveryOptionsFragment;
    public static PaymentFragment paymentFragment;
    public static OrderSummaryFragment orderSummaryFragment;
    public FilterChoiceAdapter filterChoiceAdapter;
    private TwentyOneYear twentyOneYear;
    //    public static String llfilterType = "";
    public FilterInfoModel filterInfoModel;
    public static boolean iscomeFromHomeViewall = false, isComeFromDepartmentFilter = false,
            isComeFromSubdepartmentFilter = true;
    /**
     * Filter Adapter
     **/
    public FilterItemAdapter filterItemAdapter;
    public static FilterSelectedItemAdapter filterSelectedItemAdapter;
    public static List<FilterSelectedItems> liFilterSelectedItems = new ArrayList<>();
    public RecyclerView rvDeptList;
    boolean isFileterDialogOpen = false;
    public Dialog filterDialog;
    public static int filterDepartmentPosition = 0;
    public static FilterAdapter filterAdapter;
    List<FilterHomePage> liFilterHomePages;
    public String isComeFromManageAccount = "";
    public boolean isComeFromSigninManageAccount = false;
    public boolean isComeFromCartIconManageAccount = false;
    double store_longi = 0.0;
    double store_lat = 0.0;
    public boolean isClickedSetting = false;
    public boolean isSearchLocationVal = false;

    //public static String subDepartment = "",deptSizes = "",priceRange = "0",isPriceChanged = "N",onlyImage = "0";

    //    boolean isExpand = true;
//    boolean isExpand1 = true;
    public static boolean iscomfromSort = false;
    ImageView imgpersonlogo;
    public static TextView txtPersonName, txtPersonEmail;
    public TextView txtdepartment, tvWishList, txtNotification;
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
    //    public ListView filteCategoryOption;
    Res res;
    Menu Mymenu;
    public static MenuItem countMenu;
    static Button notifCount;
    public static int mNotifCount = 0;
    int height, heightsrc;
    List<Filter> liFilter;
    public static String deptId = "0";
    public static String subDepartment = "";
    public static String deptSizes = "";
    public static String priceRange = "";
    public static String isPriceChanged = "";
    public static String onlyImage = "0";
    public static String min = "0.0";
    public static String max = "0.0";
    public static String specialOfferGroup = "0";
    public static String type = "";
    public static String valueOne = "";
    public static String valueTwo = "";
    public static String filterType = "DEPARTMENT";
    public static String tempSpecialOfferSelected = "";
    public static String shortCall = "";
    public static String shortDept = "";
    public static int shortingCheckBoxPosition = 0;
    private TextToSpeech myTTS;
    public static String searchText = "";
    public static Dialog reorderDialog;
    public static String blockDisplayedText;
    public static boolean isSearchicon = false;
    public static ArrayList<String> userEmailList;
    UsernameAdapter usernameAdapter;
    RecyclerView rvUsername;
    private View view;
    UserModel userModeltemp;
    public GpsTracker gpsTracker;
    public double current_latitude = 0.0;
    public double current_longitude = 0.0;
    public double miles = 0.0;
    int tempReqQty = 1;
    ItemDescModel itemDescModel_forbuyItagain = null;
    String cartQtyOfItem = "";

     String NewItemSku = "";

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
    DrawerLayout rootView;
    private UsernameAdapter mAdapter;

//     Edited by Varun for faster Shopping Experience in item description
    LinearLayout ll_fast ;

    public int count = 1;



    //      END

//    // ************* Edited by Varun for backbutton ***********
    ImageView image2  ;
//
    public LinearLayout ll_backbutton;
////    ************* END ***************

    public static MainActivity getInstance() {
        return mainActivity;
    }

    @SuppressLint({"JavascriptInterface", "RestrictedApi", "ResourceType"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Log.e("", "onCreate: Varun reach the MAin Activity On create method");

        setContentView(R.layout.activity_main);
//     Edited by Varun for faster Shopping Experience in item description

        ll_fast = findViewById(R.id.ll_fast);
        ll_fast.setVisibility(View.GONE);
        fast2();
//      END

        FirebaseCrash.setCrashCollectionEnabled(false);

        if (getIntent().getExtras() != null) {
            URL = getIntent().getExtras().getString("URL");
            CustomerId = getIntent().getExtras().getString("CustomerId");
        }
        Intent checkTTSIntent = new Intent();
        checkTTSIntent.setAction(TextToSpeech.Engine.ACTION_CHECK_TTS_DATA);
        startActivityForResult(checkTTSIntent, MY_DATA_CHECK_CODE);
        mainActivity = this;


        mManage_expList = findViewById(R.id.Manage_expList);
        ViewTreeObserver vto = mManage_expList.getViewTreeObserver();

        vto.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                mManage_expList.setIndicatorBounds(mManage_expList.getRight() - 100, mManage_expList.getWidth() - 50);
            }
        });
        // TODO: 3/19/2018 Add Group-indicator for Account pages
        //mManage_expList.setGroupIndicator(getResources().getDrawable(R.drawable.my_group_statelist));
        txtdepartment = findViewById(R.id.txtdepartment);
        txtNotification = findViewById(R.id.txtNotification);
        bottom_list = findViewById(R.id.bottom_list);
        imgpersonlogo = findViewById(R.id.imgpersonlogo);
        view_container = findViewById(R.id.view_container);
        view_container.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mDrawer.closeDrawer(GravityCompat.START);
            }
        });

        txthome = findViewById(R.id.txthome);
        txthome.setBackgroundColor(Color.WHITE);
        txthome.setTextColor(ContextCompat.getColor(this, R.color.Header));

        coordinatorLayout = findViewById(R.id.coordinator_layout);

        tvWishList = findViewById(R.id.tv_menu_wishlist);
        tvWishList.setBackgroundColor(Color.WHITE);
        tvWishList.setTextColor(ContextCompat.getColor(this, R.color.Header));
        txtdepartment.setBackgroundColor(Color.WHITE);
        txtdepartment.setTextColor(ContextCompat.getColor(this, R.color.Header));

        txtNotification.setBackgroundColor(Color.WHITE);
        txtNotification.setTextColor(ContextCompat.getColor(this, R.color.Header));

        bottom_list.setBackgroundColor(Color.WHITE);

        txtevent_cal = findViewById(R.id.txtevent_cal);
        txtevent_cal.setVisibility(View.GONE);
        txtPersonEmail = findViewById(R.id.txtPersonEmail);
        txtPersonName = findViewById(R.id.txtPersonName);
        txtPersonName.setText(themeModel.StoreName);
        lldrawer = findViewById(R.id.lldrawer);
        mContent = findViewById(R.id.mContent);
        llFilter = findViewById(R.id.llFilter);
        llsearch = findViewById(R.id.llsearch);

        ll_backbutton=findViewById(R.id.ll_main_top_bar);

        image2=findViewById(R.id.image2);

        llsortandfilter = findViewById(R.id.llsortandfilter);
        btndept = findViewById(R.id.btndept);
        mContainer = findViewById(R.id.Container);

        llcheckInternet = findViewById(R.id.llcheckInternet);
        llSort = findViewById(R.id.llSort);
//        ?edited by Varun for reward point show below the search bar
        ll_Reward_main= findViewById(R.id.ll_Reward_main);
        tv_points_main= findViewById(R.id.tv_points_main);
        tv_rebate_main= findViewById(R.id.tv_rebate_main);
        tv_rebate_point_main = findViewById(R.id.tv_rebate_point_main);
        tv_Reward_point_main = findViewById(R.id.tv_Reward_point_main);
//        END
        mToolbar = findViewById(R.id.toolbar);

        mDrawer = findViewById(R.id.drawer_layout);
        float width = (float) (getResources().getDisplayMetrics().widthPixels / 1.3);
        lldrawer.getLayoutParams().width = (int) width;

//        Edited by Varun For Speed -up
        if (IsComeFromSplash){
            AppPref = getSharedPreferences(PrefName, MODE_PRIVATE);
            if (!BAR_IMG_DISP) {
                Constant.ISguest = Constant.AppPref.getBoolean("ISguest",false);
                if (!ISguest) {
//                END
                    if (!AppPref.getString("email", "").isEmpty() && !AppPref.getString("password", "").isEmpty()) {
                        String Url = WS_BASE_URL + CHECK_PASSWORD + AppPref.getString("email", "") + "/" + AppPref.getString("password", "") + "/" + STOREID;
//                   Edited by Varun For Speed -up
//                    new Async_getCommonService(MainActivity.this, Url, "comefromLogin").execute();
                        new Async_getCommonService(this, Url,"comefromLogin").executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
//                    END
                    }else{
                        loadHomeWebPage();
                    }
                } else {
                    loadHomeWebPage();
                }
                BAR_IMG_DISP = false;
            }
            onSetDrawerMenu();
        }
        onCallGlobalSetup();
//        loadOnlyStoreHoursWS();

//        END

        Drawable myDrawable = MainActivity.getInstance().getResources().getDrawable(R.drawable.ic_search_home);

        if (themeModel.ThemeColor.isEmpty()) {
            themeModel.ThemeColor = "#000000";
        }
        if (!themeModel.ThemeColor.isEmpty()) {
            myDrawable.setColorFilter(new LightingColorFilter(Color.BLACK, Color.parseColor(themeModel.ThemeColor)));
        }
        imgHome = findViewById(R.id.imgHome);

        if (themeModel.StoreLogo != null && !themeModel.StoreLogo.isEmpty() && !themeModel.StoreLogo.equals("")) {
            String url = IMG_BASEURL + LOGO + STOREID + "/" + themeModel.StoreLogo;
            Log.e("Log", "Url=" + url);
            Utils.setImageFromUrlHome(this, url, imgHome);
        }else{
            imgHome.setClickable(false);
            imgHome.setVisibility(View.GONE);
        }

        cardFragment = new CardFragment();

//        itemDescriptionsFragment = new ItemDescriptionsFragment();
        deliveryOptionsFragment = new DeliveryOptionsFragment();
        paymentFragment = new PaymentFragment();

        mContainer.clearCache(true);
        mContainer.getSettings().setCacheMode(WebSettings.LOAD_NO_CACHE);

        mContainer.getSettings().setDomStorageEnabled(true);

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

        setSupportActionBar(mToolbar);

        int color = Color.parseColor(Constant.themeModel.ThemeColor);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.setStatusBarColor(color);
        }

        searchClear = findViewById(R.id.search_clear);
        searchClear.setOnClickListener(this);
        searchCamera = findViewById(R.id.img_search_camera);
        if (!themeModel.ThemeColor.isEmpty()) {
            DrawableCompat.setTint(searchCamera.getDrawable(), Color.parseColor(themeModel.ThemeColor) /*Utils.getColor(this, *//*656565*//* *//*Color.parseColor(*//* Integer.parseInt(Constant.themeModel.ThemeColor) *//*)*//* *//*R.color.red*//*)*/);
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
                } else {
                    searchClear.setVisibility(View.GONE);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
                if (s.length() >= 3) {
                    onStartSearch(s.toString());
                    MainActivity.tempSpecialOfferSelected = "0";
                    MainActivity.deptId = "0";
                } else {
                    mSearchedt.dismissDropDown();
                }
            }
        });

        mSearchedt.setOnTouchListener(new DrawableClickListener.RightDrawableClickListener(mSearchedt) {
            @Override
            public boolean onDrawableClick() {
                //Toast.makeText(MainActivity.getInstance(), "Search is Clicked", Toast.LENGTH_SHORT).show();
                onSearchIconClick();
                //Intent intent = new Intent(MainActivity.this, OcrCaptureActivity.class);
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


////        ********************* Edited by Varun for backbutton *********************
//
        image2.setVisibility(View.GONE);

        image2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Toast.makeText(MainActivity.this, "backkk", Toast.LENGTH_LONG).show();
                onBackPressed();
            }
        });
//
////      ****************** END ********************

        actionBarDrawerToggle = new ActionBarDrawerToggle(this, mDrawer, mToolbar, R.string.openDrawer, R.string.closeDrawer) {

            @Override
            public void onDrawerClosed(View mDrawerView) {
                super.onDrawerClosed(mDrawerView);
            }

            @Override
            public void onDrawerOpened(View mDrawerView) {
                super.onDrawerOpened(mDrawerView);
            }
        };

        actionBarDrawerToggle.setToolbarNavigationClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

////        ************************ Edited by Varun for backbutton *************************
        actionBarDrawerToggle.getDrawerArrowDrawable().setColor(getResources().getColor(R.color.androidWhite));
////          ********************* END *******************

        mDrawer.setDrawerListener(actionBarDrawerToggle);

        if (getSupportActionBar() != null)
            getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        txthome.setOnClickListener(this);
        tvWishList.setOnClickListener(this);
        txtevent_cal.setOnClickListener(this);
        llFilter.setOnClickListener(this);
        llSort.setOnClickListener(this);
        txtdepartment.setOnClickListener(this);
        txtNotification.setOnClickListener(this);
        imgHome.setOnClickListener(this);
        txtPersonEmail.setOnClickListener(this);
        btndept.setOnClickListener(this);

        Log.e("Log", "Order ID==" + URL);
        Log.e("Log", "customer ID==" + CustomerId);

        txtNotification.setVisibility(View.GONE);
    }


    private void loadOnlyStoreHoursWS() {
        String Url = WS_BASE_URL + GET_DELIVERY_HOURS + "/" + STOREID + "/" + "store";
        TaskStoreDeliveryHours taskStoreDeliveryHours = new TaskStoreDeliveryHours(this, MainActivity.this, "");
//        Edited by Varun For Speed -up
//        taskStoreDeliveryHours.execute(Url);
        taskStoreDeliveryHours.executeOnExecutor(TaskStoreDeliveryHours.THREAD_POOL_EXECUTOR, Url);
//        END
    }

    public void CheckUserLogin() {
        AppPref = getSharedPreferences(PrefName, MODE_PRIVATE);
        if (!BAR_IMG_DISP) {
            Constant.ISguest = Constant.AppPref.getBoolean("ISguest",false);
            if (!ISguest) {
//                END
                if (!AppPref.getString("email", "").isEmpty() && !AppPref.getString("password", "").isEmpty()) {
                    String Url = WS_BASE_URL + CHECK_PASSWORD + AppPref.getString("email", "") + "/" + AppPref.getString("password", "") + "/" + STOREID;
//                   Edited by Varun For Speed -up
//                    new Async_getCommonService(MainActivity.this, Url, "comefromLogin").execute();
                    new Async_getCommonService(this, Url,"comefromLogin").executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
//                    END
                }else{
                    loadHomeWebPage();
                }
            } else {
                loadHomeWebPage();
            }
            BAR_IMG_DISP = false;
        }
        onSetDrawerMenu();
    }


    public void getLocation() {
        gpsTracker = new GpsTracker(MainActivity.this);
        if (gpsTracker.canGetLocation()) {

            //for current location
            double latitude = gpsTracker.getLatitude();
            double longitude = gpsTracker.getLongitude();
            Log.e("latitude", String.valueOf(latitude));
            Log.e("longitude", String.valueOf(longitude));
//            Toast.makeText(MainActivity.this,"latitude"+String.valueOf(latitude),Toast.LENGTH_LONG).show();
//            /Toast.makeText(MainActivity.this,"longitude"+String.valueOf(longitude),Toast.LENGTH_LONG).show();
            current_latitude = latitude;
            current_longitude = longitude;
            //end for current location


            //get store address from contact info ws response
            String state_Zipvar = "";
            String add = "";
            String fullAddress = "";

            if (contatInfo.getAddress() != null && !contatInfo.getAddress().isEmpty()) {
                add = contatInfo.getAddress();
            }

            if (contatInfo.getCity() != null && !contatInfo.getCity().isEmpty()) {
                add = add + "\n" + contatInfo.getCity();
            }

            if (contatInfo.getState() != null && !contatInfo.getState().isEmpty()) {
                state_Zipvar = contatInfo.getState();
            }

            if (contatInfo.getZip() != null && !contatInfo.getZip().isEmpty()) {
//
                state_Zipvar = state_Zipvar + " " + contatInfo.getZip();
                add = add + ", " + state_Zipvar;
            }
            fullAddress = add;

            getLatandLongFromAddress(fullAddress);

        } else {
            gpsTracker.showSettingsAlert();
        }
    }

    public void getLatandLongFromAddress(String address) {
        Geocoder geoCoder = new Geocoder(MainActivity.this);
        if (address != null && !address.isEmpty()) {
            try {
                List<Address> addressList = geoCoder.getFromLocationName(address, 1);
                if (addressList != null && addressList.size() > 0) {
                    store_lat = addressList.get(0).getLatitude();
                    store_longi = addressList.get(0).getLongitude();

                    getDistanceOfStoreLoaction(store_lat, store_longi);
                }
            } catch (Exception e) {
                e.printStackTrace();
            } // end catch
        } // end if
    } // end c

    private void getDistanceOfStoreLoaction(double store_lat, double store_longi) {

        try {

            Location loc1_currentloc = new Location("");
            loc1_currentloc.setLatitude(current_latitude);
            loc1_currentloc.setLongitude(current_longitude);

            Location loc2_storeloc = new Location("");
            loc2_storeloc.setLatitude(store_lat);
            loc2_storeloc.setLongitude(store_longi);

            float distanceInMeters = loc1_currentloc.distanceTo(loc2_storeloc);
            miles = distanceInMeters * 0.00062137119;
            Log.e("miles:", "" + miles);

                HomepageFragment.getInstance().setMiles(miles);

        } catch (Exception e) {
            e.printStackTrace();
        }
//        Toast.makeText(MainActivity.this,"miles:"+miles,Toast.LENGTH_LONG).show();
    }


    /**
     * Initializing Drawer Menu(footer menu)
     **/
    public void onSetDrawerMenu() {

//        Collections.sort(Constant.FooterList, new SortBasedOnName());

        Collections.sort(Constant.FooterList, new Comparator<MbsDataModel>() {
            @Override
            public int compare(MbsDataModel o1, MbsDataModel o2) {
                return o1.position - o2.position;
            }
        });

        FooterAdapter footerAdapter = new FooterAdapter(this, FooterList);
        bottom_list.setAdapter(footerAdapter);
        bottom_list.setOnScrollListener(new ListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView absListView, int i) {
                justifyListViewHeightBasedOnChildren(bottom_list);
            }

            @Override
            public void onScroll(AbsListView absListView, int i, int i1, int i2) {
                justifyListViewHeightBasedOnChildren(bottom_list);
            }
        });
        ArrayList<String> TitleList = new ArrayList<String>(LHSLIDER_LIST.keySet());
        Log.e("Log", "Ed-4");
        if (TitleList.size() > 0)
            mManage_expList.setAdapter(new ExpandAdapter(MainActivity.this, LHSLIDER_LIST, TitleList));
    }

    /**
     * Text
     **/
    public void imageToTextResult(String textFromImage) {
        BAR_IMG_DISP = true;

        //onStartSearch(textFromImage);
        Log.e("Log", "Searched text=" + textFromImage);
        String customerId = "0";
        if (UserModel.Cust_mst_ID != null)
            customerId = UserModel.Cust_mst_ID;
        else
            customerId = "0";

        MainActivity.getInstance().mContainer.removeAllViews();
        MainActivity.getInstance().LoadWebVew(Constant.URL + "/inventory/inventoryapp"
                + "?customerid=" + customerId
                + "&sessionid=" + DeviceInfo.getDeviceId(MainActivity.getInstance()) + "0011"
                + "&storeno=" + STOREID
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
        url = WS_BASE_URL + GET_SEARCH_DATA + "/" + STOREID + "/" + search;
        taskSearch = new TaskSearch(this);
//        Edited by Varun For Speed -up
//        taskSearch.execute(url);
        taskSearch.executeOnExecutor(TaskSearch.THREAD_POOL_EXECUTOR,url);
    }

    /**
     * Call : Search when Search ICON click
     **/
    public void onSearchIconClick() {
        //http://192.168.172.211:888/Inventory/AndroidSearchItemDescription?CustomerId=189013&Storeno=707&sessionid=&ItemId=72081591053&Item=CABO WABO ANEJO 20(.750L)
        mSearchedt.dismissDropDown();
        //hide keyboard using below code
        hidekeyboard();

        //callClearFilter();

//        Log.e("search:",""+mSearchedt.getText().toString().trim());
        searchText = (mSearchedt.getText().toString().isEmpty()) ? "" : mSearchedt.getText().toString().trim();
//        try {
//            searchText = URLEncoder.encode(searchText, "utf-8");
//        } catch (UnsupportedEncodingException e) {
//            e.printStackTrace();
//        }
        mSearchedt.clearFocus();
        Utils.hideKeyboard();
        if (searchText.length() == 0) {
            MainActivity.tempSpecialOfferSelected = "";
            MainActivity.deptId = "";
            MainActivity.subDepartment = "";
            MainActivity.deptSizes = "";
            MainActivity.priceRange = "";
            FilterItemAdapter.tempPosition = -1;
        }

        String customerId = "0";
        if (UserModel.Cust_mst_ID != null)
            customerId = UserModel.Cust_mst_ID;
        else
            customerId = "0";

        isSearchicon = true;

        loadViewAllFragment("0", "0", "0", "0", "0", "0", "0", searchText, "");

//        MainActivity.getInstance().mContainer.removeAllViews();
//        MainActivity.getInstance().LoadWebVew(Constant.URL + "/inventory/inventoryapp"
//                + "?customerid=" + customerId
//                + "&sessionid=" + DeviceInfo.getDeviceId(MainActivity.getInstance()) + "0011"
//                + "&storeno=" + Constant.STOREID
//                + "&deptid=" + "0"
//                + "&subdeptid=" + "0"
//                + "&type=" + "allitem"
//                + "&filtertext=" + searchText); //&type=""
    }

    public void hidekeyboard() {

        View view = this.getCurrentFocus();

        if (view != null) {
            InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            assert imm != null;
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }

    }

    /**
     * Result : Search list
     **/
    @Override
    public void onSearchResult(final List<GetSearchData> liSearchData) {

//        String result[] = new String[liSearchData.size()];
//        for (int i = 0; i < liSearchData.size(); i++) {
//            result[i] = liSearchData.get(i).getFilterDescription();
//        }

        final ArrayAdapter<GetSearchData> adapter = new CustomizedSpinnerAdapter(
                this, android.R.layout.simple_spinner_item,
                liSearchData);

        mSearchedt.setAdapter(adapter);
        mSearchedt.setThreshold(1);
//
//        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
//                android.R.layout.simple_spinner_dropdown_item, result);
//        mSearchedt.setAdapter(adapter);
//        mSearchedt.setThreshold(1);


        //mSearchedt.setDropDownAnchor(llsearch.getId());
        if (!mSearchedt.isPopupShowing()) {
            mSearchedt.showDropDown();
        }

        //http://192.168.172.211:888/Inventory/AndroidSearchItemDescription?CustomerId=189013&Storeno=707&sessionid=&ItemId=72081591053&Item=CABO WABO ANEJO 20(.750L)

        mSearchedt.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                mSearchedt.dismissDropDown();
                Utils.hideKeyboard();
                mSearchedt.clearFocus();

                String customerId = "0";
                if (UserModel.Cust_mst_ID != null)
                    customerId = UserModel.Cust_mst_ID;
                else
                    customerId = "0";

                String itemsku = liSearchData.get(i).getFilterID();

                if (!itemsku.equals("") && itemsku != null) {
//                    loadItemDescriptionFragment(itemsku, "");
                    loadItemDescriptionFragment(itemsku, "fragment");
                }

//                MainActivity.getInstance().mContainer.removeAllViews();
//                /*MainActivity.getInstance().LoadWebVew(Constant.URL + "inventory/AndroidSearchItemDescription"
//                        + "?CustomerId=" + customerId
//                        + "&Storeno=" + Constant.STOREID
//                        + "&sessionid=" + *//*"0"*//*DeviceInfo.getDeviceId(MainActivity.getInstance()) + "0011"
//                        + "&ItemId=" *//*+ "0" *//* + liSearchData.get(i).getFilterID()
//                        + "&Item=" + liSearchData.get(i).getFilterDescription());*/
//                //http://192.168.172.211:888/Inventory/ItemDescriptionApp?customerID=188971&storeno=707
//                MainActivity.getInstance().LoadWebVew(Constant.URL + "inventory/AndroidSearchItemDescription"
//                        + "?CustomerId=" + customerId
//                        + "&Storeno=" + Constant.STOREID
//                        + "&sessionid=" + /*"0"*/DeviceInfo.getDeviceId(MainActivity.getInstance()) + "0011"
//                        + "&ItemId=" /*+ "0" */ + liSearchData.get(i).getFilterID()
//                        + "&Item=" + liSearchData.get(i).getFilterDescription());
//
//                Log.e("Log", "SearchedURL=" + Constant.URL + "inventory/AndroidSearchItemDescription"
//                        + "?CustomerId=" + customerId
//                        + "&Storeno=" + Constant.STOREID
//                        + "&sessionid=" + /*"0"*/DeviceInfo.getDeviceId(MainActivity.getInstance()) + "0011"
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
        filterUrl = WS_BASE_URL + FILTER_HOME_PAGE + STOREID;
        TaskFilterHomePage taskFilterHomePage = new TaskFilterHomePage(MainActivity.getInstance());
//        Edited by Varun For Speed -up
//        taskFilterHomePage.execute(filterUrl);
        taskFilterHomePage.executeOnExecutor(TaskFilterHomePage.THREAD_POOL_EXECUTOR,filterUrl);
    }

    @Override
    public void onFilterHomePageResult(List<FilterHomePage> liFilterHomePages) {
        this.liFilterHomePages = liFilterHomePages;
//        if (tempSpecialOfferSelected.isEmpty()) {
//            callFilter(deptId, subDepartment, deptSizes);
//        } else {

//            if(!llfilterType.isEmpty()){
//                callSpecialOfferFilter(deptId, subDepartment, deptSizes, specialOfferGroup, llfilterType, valueOne, valueTwo);
//            }else {
//                callSpecialOfferFilter(deptId, subDepartment, deptSizes, specialOfferGroup, type, valueOne, valueTwo);
//            }
//        }

//        if(!viewAlltype.isEmpty()){
//
//        }
        if (!MainActivity.type.isEmpty()) {
            callSpecialOfferFilter(deptId, subDepartment, deptSizes, specialOfferGroup, MainActivity.type, valueOne, valueTwo, MainActivity.blockDisplayedText);
        }
    }

    /**
     * Call : Filter web service
     **/
    public static void callFilter(String deptId, String styleId, String sizeId) {

        //GetFilterByDepartmentStyleSizePrice_v2/{storeNo}/{deptId}/{styleId}/{sizeId}/{pricerange}/{descGroupId}/{filterType}/{IsItemWithImages}/{StartDiscountRange}/{EndDiscountRange}
        //http://192.168.172.211:889/WebStoreRestService.svc/GetFilterByDepartmentStyleSizePrice_v2/707/1846/0/0/0/0/DEPARTMENT/0/0/0
        deptId = (deptId.isEmpty()) ? "0" : deptId;

        if (deptId.equals("0")) {
            deptId = String.valueOf(clickedDeptIdfromhome);
        }

        styleId = (styleId.isEmpty()) ? "0" : styleId;
        sizeId = (sizeId.isEmpty()) ? "0" : sizeId;
        String tempMin = (min.isEmpty()) ? "0.00" : min;
        String tempMax = (max.isEmpty()) ? "0.00" : max;
        String tempPriceRange = tempMin + ";" + tempMax;
        //String tempFilterType =
        //Toast.makeText(mainActivity, "dept id : " + deptId, Toast.LENGTH_SHORT).show();

        String filterUrl;
        filterUrl = WS_BASE_URL + FILTER_URL + STOREID + "/"
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

        TaskFilter taskFilter = new TaskFilter(MainActivity.getInstance());
        Log.e("Filter", "callFilter: " + taskFilter);
//        Edited by Varun For Speed -up
//        taskFilter.execute(filterUrl);
        taskFilter.executeOnExecutor(TaskFilter.THREAD_POOL_EXECUTOR,filterUrl);
    }

    /**
     * Call : Filter web service
     **/
    public static void callSpecialOfferFilter(String deptId, String styleId, String sizeId, String specialOfferGroup, String type, String startDiscount, String endDiscount, String blockDisplayedText) {

        //GetFilterByDepartmentStyleSizePrice_v2/{storeNo}/{deptId}/{styleId}/{sizeId}/{pricerange}/{descGroupId}/{filterType}/{IsItemWithImages}/{StartDiscountRange}/{EndDiscountRange}
        //http://192.168.172.211:889/WebStoreRestService.svc/GetFilterByDepartmentStyleSizePrice_v2/707/1846/0/0/0/0/DEPARTMENT/0/0/0
        deptId = (deptId.isEmpty()) ? "0" : deptId;

        if (deptId.equals("0")) {
            deptId = String.valueOf(clickedDeptIdfromhome);
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

        MainActivity.type = type;

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
        filterUrl = WS_BASE_URL + FILTER_URL + STOREID + "/"
                + deptId /*"1846"*/ + "/"          /*deptId*/
                + styleId   /*"0"*/ + "/"             /*styleId*/
                + sizeId    /*"0"*/ + "/"             /*sizeId*/
                + MainActivity.priceRange + "/"             /*pricerange*/
                + specialOfferGroup /*"0"*/ + "/"  /*descGroupId*/
                + type + "/"    /*filterType*/
                + "0" + "/"             /*IsItemWithImages*/
                + startDiscount + "/"             /*StartDiscountRange*/
                + endDiscount                   /*EndDiscountRange*/;
        //}

        TaskFilter taskFilter = new TaskFilter(MainActivity.getInstance());
        Log.e("Filter", "callFilter: " + taskFilter);
//        Edited by Varun For Speed -up
//        taskFilter.execute(filterUrl);
        taskFilter.executeOnExecutor(TaskFilter.THREAD_POOL_EXECUTOR,filterUrl);
    }

    /**
     * Call : Filter For clear all data web service
     **/
    public static void callClearFilter() {

        //GetFilterByDepartmentStyleSizePrice_v2/{storeNo}/{deptId}/{styleId}/{sizeId}/{pricerange}/{descGroupId}/{filterType}/{IsItemWithImages}/{StartDiscountRange}/{EndDiscountRange}
        //http://192.168.172.211:889/WebStoreRestService.svc/GetFilterByDepartmentStyleSizePrice_v2/707/1846/0/0/0/0/DEPARTMENT/0/0/0
        String filterUrl;
        filterUrl = WS_BASE_URL + FILTER_URL + STOREID + "/0/0/0/0;1000/0/allItem/0/0/0"                   /*EndDiscountRange*/;
        TaskFilter taskFilter = new TaskFilter(MainActivity.getInstance());

        onlyImage = "0";
        MainActivity.tempSpecialOfferSelected = "0";
        MainActivity.deptId = "0";
        MainActivity.type = "allItem";
        MainActivity.specialOfferGroup = "0";
        valueTwo = "";
        valueOne = "";
//        Edited by Varun For Speed -up
//        taskFilter.execute(filterUrl);
        taskFilter.executeOnExecutor(TaskFilter.THREAD_POOL_EXECUTOR,filterUrl);
    }

    /**
     * Result Filter
     **/
    @Override
    public void onFilterResult(List<Filter> liFilter) {
        this.liFilter = liFilter;

        String url;

        url = WS_BASE_URL + GET_FILTER_INFO
                + "/" + STOREID;

        TaskFilterInfo taskFilterInfo = new TaskFilterInfo(this, this, "");
//        Edited by Varun For Speed -up
//        taskFilterInfo.execute(url);
        taskFilterInfo.executeOnExecutor(TaskFilterInfo.THREAD_POOL_EXECUTOR,url);
    }

    @Override
    public void onTaskFilterInfoResult(FilterInfoModel filterInfoModel) {
        this.filterInfoModel = filterInfoModel;
        showFilterDialog();
//        if(!isNoNeedToOpenFilterDialog) {
//            showFilterDialog();
//        }else{
//            isNoNeedToOpenFilterDialog = false;
//        }

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

        if (!mSearchedt.getText().toString().isEmpty()) {
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

//        FilterSelectedItems selectedItems = new FilterSelectedItems();

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
        filterSelectedItemAdapter = new FilterSelectedItemAdapter(liFilterSelectedItems);
        rvSelectedItems.setAdapter(filterSelectedItemAdapter);
        filterSelectedItemAdapter.notifyDataSetChanged();

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
                    FilterItemAdapter.tempPosition = -1;
                    filterAdapter = new FilterAdapter(MainActivity.this, FILTERlIST, liFilter, liFilterHomePages);
                    //MainActivity.getInstance().filterItemAdapter = new FilterItemAdapter(FilterAdapter.this, liFilterHomePages, liFilter, 5);
                    MainActivity.getInstance().rvDeptList.setAdapter(MainActivity.getInstance().filterItemAdapter);
                    MainActivity.getInstance().filterItemAdapter.notifyDataSetChanged();
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
                    FilterItemAdapter.tempPosition = -1;
                    filterAdapter = new FilterAdapter(MainActivity.this, FILTERlIST, liFilter, liFilterHomePages);
                    //MainActivity.getInstance().filterItemAdapter = new FilterItemAdapter(FilterAdapter.this, liFilterHomePages, liFilter, 5);
                    MainActivity.getInstance().rvDeptList.setAdapter(MainActivity.getInstance().filterItemAdapter);
                    MainActivity.getInstance().filterItemAdapter.notifyDataSetChanged();
                }
            }
        });

        btnApply.setBackgroundColor(Color.parseColor(themeModel.ThemeColor));

        FILTERlIST.clear();/*
        Constant.FILTERlIST.get(0).isActive = true;
        Constant.FILTERlIST.add(new FilterModel(true));*/

        FilterModel filterm = new FilterModel("Special Offers");
        //filterm.isActive = true;
        FILTERlIST.add(filterm);
        //Constant.FILTERlIST.add(new FilterModel("Special Offers"));
        FILTERlIST.add(new FilterModel("Departments"));
//        Constant.FILTERlIST.add(new FilterModel("Sub Departments"));

        if (filterInfoModel != null) {

            if (filterInfoModel.getStyleDisplay() != null) {
                if (filterInfoModel.getStyleDisplay().equals("Y")) {
                    FILTERlIST.add(new FilterModel("Sub Departments"));
                }
            }

//            if(filterInfoModel.getSearchBySize() != null) {
//                if (filterInfoModel.getSearchBySize()) {
//                    Constant.FILTERlIST.add(new FilterModel("Sizes"));
//                }
//            }


            if (filterInfoModel.getSizeDisplay() != null && filterInfoModel.getSizeDisplay().equals("Y")) {
                if (liFilter.get(0).getLstSize() != null && liFilter.get(0).getLstSize().size() > 0) {
                    FILTERlIST.add(new FilterModel("Sizes"));
                }
            }


            if (filterInfoModel.getItemWithImageOnlyDisplay() != null) {
                if (filterInfoModel.getItemWithImageOnlyDisplay()) {
                    FILTERlIST.add(new FilterModel("Image"));
                }
            }

            if (filterInfoModel.getSearchByPricePoint() != null) {
                if (filterInfoModel.getSearchByPricePoint()) {
                    FILTERlIST.add(new FilterModel("Price"));
                }
            }

//            Constant.FILTERlIST.add(new FilterModel("Sizes"));
//            Constant.FILTERlIST.add(new FilterModel("Image"));
//            Constant.FILTERlIST.add(new FilterModel("Price"));
        }
        filterAdapter = new FilterAdapter(MainActivity.this, FILTERlIST, liFilter, liFilterHomePages);

        filteCategory.setAdapter(filterAdapter);

        rvDeptList = view.findViewById(R.id.rvFilterDepartment);
        //Set recyclerView in GridView
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        rvDeptList.setLayoutManager(layoutManager);
        rvDeptList.setHasFixedSize(true);

        //FilterItemAdapter filterItemAdapter = new FilterItemAdapter(this,this,liFilter,0);
        /*FilterHomePage fhp=new FilterHomePage();
        fhp.setBlockDisplaytext("All Items");
        liFilterHomePages.add(0,fhp);*/

        if (liFilter != null) {
            if (liFilter.get(0).getLstDepartment() != null && !liFilter.get(0).getLstDepartment().get(0).getDeptDesc().equals("All")) {
                LstDepartment department = new LstDepartment();
                department.setDeptDesc("All");
                department.setDeptId(0);
                department.setInvCount(0);
                liFilter.get(0).getLstDepartment().add(0, department);
            }
            if (liFilter.get(0).getLstSize() != null && !liFilter.get(0).getLstSize().get(0).getSizeName().equals("Select All")) {
                LstSize lstSize = new LstSize();
                lstSize.setSizeName("Select All");
                lstSize.setDeptid(0);
                lstSize.setSizeId(0);
                lstSize.setSizeCount(0);
                lstSize.setChecked(true);
                liFilter.get(0).getLstSize().add(0, lstSize);
            }
        }

        filterItemAdapter = new FilterItemAdapter(this, liFilterHomePages, liFilter/*.get(0).getLstDepartment()*/, filterDepartmentPosition);
        rvDeptList.setAdapter(filterItemAdapter);
        filterItemAdapter.notifyDataSetChanged();

        //filterChoiceAdapter = new FilterChoiceAdapter(MainActivity.this, liFilter, liFilter.get(0).getLstDepartment().size(), 0);
        //filteCategoryOption.setAdapter(filterChoiceAdapter);
        WindowManager.LayoutParams params = filterDialog.getWindow().getAttributes();
        params.width = WindowManager.LayoutParams.MATCH_PARENT;
        params.height = WindowManager.LayoutParams.MATCH_PARENT;

        filterDialog.setContentView(view);
        filterDialog.getWindow().setGravity(Gravity.BOTTOM);

//        if (MainActivity.priceRange.isEmpty()) {
//            MainActivity.priceRange = "0;0";
//
//        }else {
//
//            String[] price = MainActivity.priceRange.split(";");
//            String min = price[0];
//            String max = price[1];
//
//            etMinPrice.setText(min);
//            etMaxPrice.setText(max);
//            etMaxPrice.clearFocus();
//        }

        btnApply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                isFileterDialogOpen = false;
                filterDialog.dismiss();
                Constant.filter_click = false;
//                if(!MainActivity.TempdeptId.equals(MainActivity.deptId)){
//                    MainActivity.deptId = MainActivity.TempdeptId;
//                }else{
//                    MainActivity.isNoNeedToOpenFilterDialog = true;
//                    MainActivity.callSpecialOfferFilter(MainActivity.deptId, "0", "0", MainActivity.specialOfferGroup, MainActivity.type, MainActivity.valueOne, MainActivity.valueTwo, MainActivity.blockDisplayedText);
//                }
                deptId = (deptId != null && deptId.isEmpty()) ? "0" : deptId;
                subDepartment = (subDepartment != null && subDepartment.isEmpty()) ? "0" : subDepartment;
                deptSizes = (deptSizes != null && deptSizes.isEmpty()) ? "0" : deptSizes;
                if (FilterItemAdapter.tempPosition < 0) {
                    if (!MainActivity.etMaxPrice.getText().toString().isEmpty() && !MainActivity.etMinPrice.getText().toString().isEmpty()) {
                        String tempMins = MainActivity.etMinPrice.getText().toString();
                        String tempMaxs = MainActivity.etMaxPrice.getText().toString();
                        priceRange = tempMins + ";" + tempMaxs;
                        //tempPriceRange = MainActivity.priceRange;

                        MainActivity.isPriceChanged = "Y";
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


                if (type.equals("promotion")) {
                    if (blockDisplayedText.equalsIgnoreCase("Items On Promotions")) {
                        ViewAllFragment.getInstance().loadItemListingWS(type, true);
                    } else {
                        getblockActualtextFromBlocklistWS(blockDisplayedText);
                    }
                } else if (type.equals("specialoffer")) {
                    if (blockDisplayedText.equalsIgnoreCase("Special Offers")) {
                        ViewAllFragment.getInstance().loadItemListingWS(type, true);
                    } else {
                        getblockActualtextFromBlocklistWS(blockDisplayedText);
                    }
                } else {
                    ViewAllFragment.getInstance().loadItemListingWS(type, true);
                }

//                loadFilteredWebPage(deptId, subDepartment, deptSizes, priceRange, isPriceChanged, onlyImage, type, valueOne, valueTwo, shortCall, shortDept);

//                clearFilterSelection();
//                onlyImage = "0";
//                shortCall = "";
//                shortDept = "";
//                deptSizes = "0";

            }
        });
        imgClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //FilterAdapter.isActivated(0);
                clearFilterSelection();
                isFileterDialogOpen = false;
                filterDialog.dismiss();
                filter_click=false;
            }
        });
        txtclear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                clearFilterSelection();
                callClearFilter();
                filterAdapter.notifyDataSetChanged();
                filterItemAdapter.notifyDataSetChanged();
                filterSelectedItemAdapter.notifyDataSetChanged();
            }
        });
        if (!filterDialog.isShowing())
            filterDialog.show();

    }

    private void getblockActualtextFromBlocklistWS(String blockDisplayedText) {

        String type = "";

        if (!blockDisplayedText.isEmpty()) {

            for (int i = 0; i < BLOCKDATAFRONTLIST.size(); i++) {
                if (blockDisplayedText.equals(BLOCKDATAFRONTLIST.get(i).getBlockDisplaytext())) {
                    type = BLOCKDATAFRONTLIST.get(i).getBlockActualtext();
                }
            }

            if (!type.isEmpty()) {
                ViewAllFragment.getInstance().loadItemListingWS(type, true);
            }
        }
    }

//    private void loadFilterInfoWS() {
//
//            String url;
//
//            url = Constant.WS_BASE_URL + Constant.GET_FILTER_INFO
//                    + "/" + Constant.STOREID;
//
//            TaskFilterInfo taskFilterInfo = new TaskFilterInfo(this,this);
//            taskFilterInfo.execute(url);
//    }


    public void clearFilterSelection() {

        if (liFilterHomePages != null) {
            for (int i = 0; i < liFilterHomePages.size(); i++) {
                liFilterHomePages.get(i).setChecked(false);
                MainActivity.tempSpecialOfferSelected = "0";
            }
        }

        if (liFilter != null) {
            for (int i = 0; i < liFilter.size(); i++) {
                liFilter.get(0).setChecked(false);
                if (liFilter.get(0).getLstDepartment() != null) {
                    for (int j = 0; j < liFilter.get(0).getLstDepartment().size(); j++) {
                        liFilter.get(0).getLstDepartment().get(j).setChecked(false);
                        MainActivity.deptId = "0";
//                        MainActivity.deptId = MainActivity.TempdeptId;
                    }
                }

                if (liFilter.get(0).getLstStyle() != null) {
                    for (int k = 0; k < liFilter.get(0).getLstStyle().size(); k++) {
                        liFilter.get(0).getLstStyle().get(k).setChecked(false);
                        MainActivity.subDepartment = "";
                    }
                }

                if (liFilter.get(0).getLstSize() != null) {
                    for (int l = 0; l < liFilter.get(0).getLstSize().size(); l++) {
                        liFilter.get(0).getLstSize().get(l).setChecked(false);
                        MainActivity.deptSizes = "";
                    }
                }
            }
        }
        onlyImage = "0";
        MainActivity.filterDepartmentPosition = 0;
        //FilterAdapter.isActivated(0);
        FilterItemAdapter.tempPosition = -1;
        FilterItemAdapter.priceRange = "";
        MainActivity.priceRange = "";
        shortCall = "";
        shortDept = "";
    }

    public static void loadFilteredWebPage(String departmentId, String subDepartmentId, String sizes, String priceRange, String isPriceChanged, String isImage, String specialOffers, String valueOne, String valueTwo, String sortCol, String sortStatus) {
        //http://192.168.172.211:888/inventory/inventoryapp?storeno=707&deptid=1846&subdeptid=54886,54881,&Sizeid=1987,2045&pricerange=15;25&haspriceChange=Y&ItemWithImage=0
        //http://192.168.172.211:888/inventory/inventoryapp?storeno=707&type=specialoffer&value1=0&value1=100
        MainActivity.getInstance().mContainer.removeAllViews();

       /* departmentId = (departmentId == null || departmentId.isEmpty()) ? "0" : departmentId;
        subDepartmentId = (subDepartmentId == null || subDepartmentId.isEmpty()) ? "0" : subDepartmentId;
        sizes = (sizes == null || sizes.isEmpty()) ? "0" : sizes ;
        priceRange = (priceRange == null || priceRange.isEmpty()) ? "0" : priceRange ;
        isPriceChanged = (isPriceChanged == null || isPriceChanged.isEmpty()) ? "N" : isPriceChanged ;
        isImage = (isImage == null || isImage.isEmpty()) ? "0" : isImage ;*/
        try {
            searchText = URLEncoder.encode(searchText, "utf-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        MainActivity.getInstance().LoadWebVew(Constant.URL + "/inventory/inventoryapp"
                        + "?customerid=" + getUserId()
                        + "&sessionid=" + DeviceInfo.getDeviceId(MainActivity.getInstance()) + "0011"
                        + "&storeno=" + STOREID
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
        MainActivity.getInstance().filterItemAdapter.notifyDataSetChanged();
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
        String jsondata = Utils.loadJSONFromAsset("config.json", MainActivity.this);
        JSONArray Arrdata = new JSONArray(jsondata);
        JSONObject MainObj = Arrdata.getJSONObject(0);

        JSONArray Arrsort = MainObj.getJSONArray("Sort");
        JSONArray AccountManage = MainObj.getJSONArray("Account_Manage");
        JSONArray Footer = MainObj.getJSONArray("Footer");
        JSONArray Departments = MainObj.getJSONArray("Departments");

        for (int i = 0; i < Departments.length(); i++) {
            MbsDataModel mbsDataModel = new MbsDataModel(Departments.getJSONObject(i), 4);
            DEPARTMENTLIST.add(mbsDataModel);
        }
        FILTERlIST.add(new FilterModel("Departments"));
        FILTERlIST.add(new FilterModel("Sub Departments"));
        FILTERlIST.add(new FilterModel("Price"));
        FILTERlIST.add(new FilterModel("Size"));

        for (int i = 0; i < Arrsort.length(); i++) {
            MbsDataModel mbsDataModel = new MbsDataModel(Arrsort.getJSONObject(i), 3);
            SORTLIST.add(mbsDataModel);
        }

        FooterAdapter footerAdapter = new FooterAdapter(this, FooterList);
        bottom_list.setAdapter(footerAdapter);
        bottom_list.setOnScrollListener(new ListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView absListView, int i) {
                justifyListViewHeightBasedOnChildren(bottom_list);
            }

            @Override
            public void onScroll(AbsListView absListView, int i, int i1, int i2) {
                justifyListViewHeightBasedOnChildren(bottom_list);
            }
        });
        ArrayList<String> TitleList = new ArrayList<String>(LHSLIDER_LIST.keySet());
        Log.e("Log", "Ed-5");
        if (TitleList.size() > 0)
            mManage_expList.setAdapter(new ExpandAdapter(MainActivity.this, LHSLIDER_LIST, TitleList));
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

    public void callNotificationDialogWs() {
        String Url3 = WS_BASE_URL + GET_NOTIFICATION_CuSTERMER + STOREID + "/" + getUserId() + "/1/25";
        TaskNotificationList notificationList = new TaskNotificationList(this);
//        Edited by Varun For Speed -up
//        notificationList.execute(Url3);
        notificationList.executeOnExecutor(TaskNotificationList.THREAD_POOL_EXECUTOR,Url3);
    }

    @Override
    public void onClick(View v) {
        llsearch.setVisibility(View.VISIBLE);
        switch (v.getId()) {
            case R.id.txthome:
                if (mSearchedt.isShown())
                    mSearchedt.setText("");
                Utils.hideKeyboard();
                mDrawer.closeDrawers();
                showHomePage();
                URL = null;
                loadHomeWebPage();
                break;
            case R.id.txtdepartment:
                isActiveSearchDept = false;
                mDrawer.closeDrawers();
                callFilterFragment();
                break;
//            case R.id.txtNotification:
//                isActiveSearchDept = false;
//                mDrawer.closeDrawers();
//                callNotificationDialog(NOTIFICATION_LIST);
//                break;
            case R.id.tv_menu_wishlist:
                isActiveSearchDept = false;
                mDrawer.closeDrawers();
                //shareApp();
                //launchMarket();
                loadWishListFragment();
                break;
            case R.id.txtevent_cal:
                mDrawer.closeDrawers();
                LoadWebVew(Constant.URL + EVENT_CALENDER);
                break;
            case R.id.btndept:
                isActiveSearchDept = true;
                mDrawer.closeDrawers();
                String Url = WS_BASE_URL + GETDEPARTMENT + STOREID;
                MainActivity.getInstance().displayDepartmentList();
                break;
            case R.id.llFilter:
                mDrawer.closeDrawers();
                // old //showFilterDialog();
//                callOfferFilter();
                //old //callFilter(deptId, subDepartment, deptSizes);
                break;
            case R.id.llSort:
                mDrawer.closeDrawers();
//                showSortPopup();
                break;
            case R.id.txtPersonEmail:
                if (AppPref.getString("email", "").isEmpty()) {
                    mDrawer.closeDrawers();
                    Login.StartLoginDialog("", MainActivity.this);
                } else {
                    mDrawer.closeDrawers();
                    Login.onLogOff();
                }
                break;
            case R.id.imgHome:
                if (mSearchedt.isShown())
                    mSearchedt.setText("");
                Utils.hideKeyboard();
                clearBackStack();
                if (getSupportFragmentManager().getBackStackEntryCount() > 0) {
                    Log.e("Log", "fragment=" + getSupportFragmentManager().getBackStackEntryCount());

                }
                fast2();
                //comment three lines for reload home when in home screen
                onBackPressed(); //1
                showHomePage(); //2
                URL = null;
                loadHomeWebPage(); //3
                //end for reload
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

//            Edited by Varun for faster shopping in Item Description Screen

          /*  case R.id.img_plus:
                if (count < 999) {
                    count++;
                }
                tv_item_quantity.setText("" + count);
                requestedQty = Integer.parseInt(tv_item_quantity.getText().toString());
                break;

            case R.id.img_minus:
                if (count > 1) {
                    count--;
                }
                tv_item_quantity.setText("" + count);
                requestedQty = Integer.parseInt(tv_item_quantity.getText().toString());
                break;

            case R.id.ivAddtoCart:
                requestedQty = Integer.parseInt(tv_item_quantity.getText().toString());
                ItemDescriptionsFragment.getInstance().addTocartData(String.valueOf(requestedQty));
                isComeFomAddTocartBtn = true;
                break;*/

//            END

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
        Resources resources = context.getResources();
        int resourceId = resources.getIdentifier("status_bar_height", "dimen", "android");
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
        expandableListView.setAdapter(new ExpandFilterAdapter(mainActivity, Constant.DEPARTMENTLIST.get(Constant.GROUP_POSITION).LHSFILTER_LIST, TitleList));
    }*/

    public Dialog dialog;

    public void showSortPopup() {
        Dialog dialog = new Dialog(this);
        View view = LayoutInflater.from(this).inflate(R.layout.short_dialog, null);
        //ListView popupList = view.findViewById(R.id.popupList);
        RadioGroup rgShorting = view.findViewById(R.id.rg_shorting_filter);
        Button btnApplyShorting = view.findViewById(R.id.btn_apply_short_dialog);
        btnApplyShorting.setBackgroundColor(Color.parseColor(themeModel.ThemeColor));
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

                if (deptId.isEmpty()) {
                    deptId = "0";
                }
                if (subDepartment.isEmpty()) {
                    subDepartment = "0";
                }

                //today
                String s1 = mSearchedt.getText().toString().trim();
//                try {
//                    searchText = URLEncoder.encode(s1, "utf-8");
//                } catch (UnsupportedEncodingException e) {
//                    e.printStackTrace();
//                }

                loadViewAllFragment(type, deptId, subDepartment, valueOne, valueTwo, specialOfferGroup, MainActivity.blockDisplayedText, searchText, "");

//                loadSortedWebPage(deptId,subDepartment,shortCall, shortDept);

//                loadFilteredWebPage(deptId, subDepartment, deptSizes, priceRange, isPriceChanged, onlyImage, type, valueOne, valueTwo, shortCall, shortDept);
                dialog.dismiss();

            }
        });
        dialog.setContentView(view);

        dialog.show();
    }

    private void loadSortedWebPage(String deptId, String subdeptId, String sortcolumn, String sortstatus) {

        MainActivity.getInstance().mContainer.removeAllViews();

        try {
            searchText = URLEncoder.encode(searchText, "utf-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        MainActivity.getInstance().LoadWebVew(Constant.URL + "/inventory/inventoryapp"
                + "?customerid=" + getUserId()
                + "&sessionid=" + 0
                + "&storeno=" + STOREID
                + "&deptid=" + clickedDeptIdfromhome
                + "&subdeptid=" + subdeptId
                + "&SortColumn=" + sortcolumn
                + "&SortStatus=" + sortstatus
        );

    }

    public void onShowShoppingCartIcon() {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        if (cardFragment.isVisible()) {
            fragmentTransaction.remove(cardFragment).commit();
            invalidateOptionsMenu();
        }
    }

    // CLEAR BACK STACK.
    public void clearBackStack() {
        final FragmentManager fragmentManager = getSupportFragmentManager();
        while (fragmentManager.getBackStackEntryCount() != 0) {
//            if (Constant.x){
//                Constant.x =false;
//            }else {
                fragmentManager.popBackStackImmediate();
//            }
        }
    }

    public static void showHomePage() {
        MainActivity.getInstance().mContainer.setVisibility(View.VISIBLE);
        MainActivity.getInstance().llsearch.setVisibility(View.VISIBLE);
        MainActivity.getInstance().mContent.setVisibility(View.GONE);
    }

    private void loadMenuSetting() {
        // selecting appropriate nav menu item
        //selectNavMenu();
        mDrawer.closeDrawers();
        invalidateOptionsMenu();
    }

    public void updateShoppingCartItemCount(int count) {

        mNotifCount = count;
        Log.e("Log", "Count=" + mNotifCount);
        cartcount = mNotifCount;
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
        llsortandfilter.setVisibility(View.GONE);

//        Edited by Varun for backbutton

        ll_backbutton.setVisibility(View.VISIBLE);
        Log.e("Backbutton", "loadFragment: backbutton" );

//     END
        llsearch.setVisibility(View.GONE);
        mContainer.setVisibility(View.GONE);
        llcheckInternet.setVisibility(View.GONE);
        mContent.setVisibility(View.VISIBLE);
        //cardFragment = new CardFragment();
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.setCustomAnimations(android.R.anim.fade_in, android.R.anim.fade_out);
        fragmentTransaction.replace(R.id.mContent, fragment, tag);
        fragmentTransaction.addToBackStack(fragment.getClass().getSimpleName());
        fragmentTransaction.commitAllowingStateLoss();
    }

    //Edited by janvi 16thjan ********

    public void loadItemDescriptionFragment(String itemID, String fragmentname_forhide) {
        //Hide search filter if shows

        llsearch.setVisibility(View.GONE);
        mContainer.setVisibility(View.GONE);
        llcheckInternet.setVisibility(View.GONE);
        mContent.setVisibility(View.VISIBLE);

        Bundle bundle = new Bundle();
//        bundle.putString("cost", model.getCost());
//        bundle.putString("desc1", model.getDesc1());
//        bundle.putString("discount", model.getDiscountName());
//        bundle.putString("InvlargeImg", model.getInvLargeImage());
//        bundle.putString("InvsmallImag", model.getInvSmallImage());
//        bundle.putString("price", model.getPrice());
        bundle.putString("itemMstId", itemID);
//        bundle.putInt("ID", model.getId());
//        bundle.putString("rating", model.getInventoryRating());
//        bundle.putString("header1", String.valueOf(model.getHeader1()));

        itemDescriptionsFragment = new ItemDescriptionsFragment();
        itemDescriptionsFragment.setArguments(bundle);

//        working code previously
//        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
//        fragmentTransaction.setCustomAnimations(android.R.anim.fade_in, android.R.anim.fade_out);
//        fragmentTransaction.replace(R.id.mContent, itemDescriptionsFragment, ItemDescriptionsFragment.TAG);
//        fragmentTransaction.addToBackStack(null);
//        fragmentTransaction.commitAllowingStateLoss();
//        llsortandfilter.setVisibility(View.GONE);
        //end **********

        // below code to is working code
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.setCustomAnimations(android.R.anim.fade_in, android.R.anim.fade_out);
        fragmentTransaction.replace(R.id.mContent, itemDescriptionsFragment, ItemDescriptionsFragment.TAG);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commitAllowingStateLoss();
        llsortandfilter.setVisibility(View.GONE);
        //end ***************

        //below code is for dont reload screen when back pressed from item description screen
//        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
//        fragmentTransaction.setCustomAnimations(android.R.anim.fade_in, android.R.anim.fade_out);
////        fragmentTransaction.hide(Objects.requireNonNull(getSupportFragmentManager().findFragmentByTag("ViewAllFragment")));
//        fragmentTransaction.hide(Objects.requireNonNull(getSupportFragmentManager().findFragmentByTag(fragmentname_forhide)));
//        fragmentTransaction.add(R.id.mContent, itemDescriptionsFragment, ItemDescriptionsFragment.TAG);
//        fragmentTransaction.addToBackStack(null);
//        fragmentTransaction.commit();
//        llsortandfilter.setVisibility(View.GONE);

        invalidateOptionsMenu();
    }


    /**
     * Shopping Card Fragment
     **/
    public void loadCardFragment() {
        //Hide search filter if shows

        if (UserModel.Cust_mst_ID != null && !UserModel.Cust_mst_ID.equals("0")) {
            getCustomerData();
            MainActivity.getInstance().isComeFromCartIconManageAccount = true;
        } else {
            loadCardFragmentDetails();
        }
    }

    public void getCustomerData() {
        if (UserModel.Cust_mst_ID != null) {
            String url = WS_BASE_URL + GET_CUSTOMER_DATA + UserModel.Cust_mst_ID + "/" + STOREID;
            TaskCustomerData taskCustomerData = new TaskCustomerData(this, this, true);
            Log.d("", "Customer data : " + url);
//            Edited by Varun For Speed -up
            taskCustomerData.executeOnExecutor(TaskCustomerData.THREAD_POOL_EXECUTOR,url);
//            taskCustomerData.execute(url);
        }
    }

    private void loadCardFragmentDetails() {

        llsearch.setVisibility(View.GONE);
        mContainer.setVisibility(View.GONE);
        llcheckInternet.setVisibility(View.GONE);
        mContent.setVisibility(View.VISIBLE);

        cardFragment = new CardFragment();
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.setCustomAnimations(android.R.anim.fade_in, android.R.anim.fade_out);
        fragmentTransaction.replace(R.id.mContent, cardFragment, CardFragment.TAG);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commitAllowingStateLoss();
        //fragmentTransaction.commit();
        llsortandfilter.setVisibility(View.GONE);

        invalidateOptionsMenu();
    }

    @Override
    public void onContinueShoppingCartClicked() {
        mContent.setVisibility(View.GONE);
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
        //  fragmentTransaction.add(R.id.mContent, paymentFragment, PaymentFragment.TAG);
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

        if (buttonclicked.equalsIgnoreCase("Instore Purchases")) {
            buttonclicked_InstoreDetail = buttonclicked;
        }
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

        if (!orderDetail.isEmpty() && orderDetail.equals("comefromOrderDetail")) {

            FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
            fragmentTransaction.setCustomAnimations(android.R.anim.fade_in, android.R.anim.fade_out);
            fragmentTransaction.replace(R.id.mContent, orderSummaryFragment, OrderSummaryFragment.TAG);
            //fragmentTransaction.addToBackStack(OrderSummaryFragment.class.getSimpleName());
            fragmentTransaction.addToBackStack(null);
            fragmentTransaction.commitAllowingStateLoss();

            invalidateOptionsMenu();

        } else {
            String name = getSupportFragmentManager().getBackStackEntryAt(0).getName();
            getSupportFragmentManager().popBackStack(name, FragmentManager.POP_BACK_STACK_INCLUSIVE);

            FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
            fragmentTransaction.setCustomAnimations(android.R.anim.fade_in, android.R.anim.fade_out);
            fragmentTransaction.replace(R.id.mContent, orderSummaryFragment, OrderSummaryFragment.TAG);
            //fragmentTransaction.addToBackStack(OrderSummaryFragment.class.getSimpleName());
            fragmentTransaction.addToBackStack(name);
            fragmentTransaction.commitAllowingStateLoss();

            invalidateOptionsMenu();
        }

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

//            Edited by Janvi 4th oct ****
//            DialogUtils.checkWishListUser();

            Login.StartLoginDialog("wishlist", MainActivity.this);

            //end ***********
            //Toast.makeText(mainActivity, "This feature only works for registered user.", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onContinueShoppingFromWishList() {
        mContent.setVisibility(View.GONE);
        getSupportFragmentManager().beginTransaction().remove(getSupportFragmentManager().findFragmentByTag(WishListFragment.TAG)).commit();
        getSupportFragmentManager().popBackStackImmediate();

        MainActivity.showHomePage();
        MainActivity.getInstance().loadHomeWebPage();
////        loadInventoryPage();  // commented to remove webpage call

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

        Log.d("llurl::", Constant.URL + HOME
                + "?customerid=" + getUserId()
                + "&storeno=" + STOREID
                + "&sessionid=" + DeviceInfo.getDeviceId(MainActivity.getInstance()) + "0011");

        if (URL == null) {
            if (isNativePage) {
                Log.e("test:::","datais:");
                CallHomeFragment();
            } else {
                LoadWebVew(Constant.URL + HOME
                        + "?customerid=" + getUserId()
                        + "&storeno=" + STOREID
                        + "&sessionid=" + DeviceInfo.getDeviceId(MainActivity.getInstance()) + "0011");
            }
        } else {
            if (UserModel.Cust_mst_ID == null) {
                Login.StartLoginDialog("", MainActivity.this);
            } else if (URL.equalsIgnoreCase("cart")) {
                loadCardFragment();
            } else if (URL.equalsIgnoreCase("Wishlist")) {
                loadWishListFragment();
            } else if (URL.equalsIgnoreCase("home")) {
                if (isNativePage) {
                    CallHomeFragment();
                    Log.e("test:::","datais1:");
                } else {
                    LoadWebVew(Constant.URL + HOME
                            + "?customerid=" + getUserId()
                            + "&storeno=" + STOREID
                            + "&sessionid=" + DeviceInfo.getDeviceId(MainActivity.getInstance()) + "0011");
                }
            } else if (URL.equalsIgnoreCase("Special Offers")) {

            } else if (URL.equalsIgnoreCase("Payment & Refund")) {

            } else {
                LoadWebVew(URL);
            }
        }
    }

    public static void loadInventoryPage() {
        MainActivity.getInstance().mContainer.removeAllViews();
        MainActivity.getInstance().LoadWebVew(Constant.URL + "/inventory/inventoryapp"
                + "?customerid=" + getUserId()
                + "&sessionid=" + DeviceInfo.getDeviceId(MainActivity.getInstance()) + "0011"
                + "&storeno=" + STOREID);
    }

    @SuppressLint("ClickableViewAccessibility")
    public void LoadWebVew(String url) {
        Log.i("web page url : ", "webpage : " + url);
        Handler mHandler = new Handler();

////        ******************** Edited by Varun for backbutton ****************
        showbackbutton();
////        ******************** END ********************

        Runnable r = new Runnable() {
            public void run() {
                onShowShoppingCartIcon();
                clearBackStack();
                mContent.removeAllViews();
            }
        };
        mHandler.postDelayed(r, 1000);

        // onShowShoppingCartIcon();
        Utils.ShowDialog(MainActivity.this);
        mContainer.clearView();
        mContainer.clearHistory();
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
                                    onGetCartData("buy it again");
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

//                Log.e("Log", "1=1" + url);
                try {
                    afterDecode = URLDecoder.decode(url, "UTF-8");
                    innerFilterUrl = afterDecode;
//                    Log.i("Log", "viewAll" + afterDecode);
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
//                    Log.e("Log", "URLBuy==" + url);
//                    showReOrderConfirmation(url, "", 1);

                } else if (url.contains("ReOrder")) {
//                    Log.e("Log", "URLBuy==" + url);
                    mContainer.stopLoading();
//                    showReOrderConfirmation(url, 2);
//                    final String itemNo = url.substring(url.indexOf("ReOrder/")).trim();
//                    String paraList[] = itemNo.split("/");
//                    callReorderDetailsWS(paraList[1]);

                } else if (url.contains("WishList?")) {
                    loadWishListFragment();
                    Utils.hideKeyboard();
                } else if (url.contains("Home/StoreIndex") || (url.contains("/Home/IndexApp"))) {
                    CallHomeFragment();
                    Log.e("test:::","datais2:");
                }

////              Edited by Janvi 15thoct *************
//////                else if (UserModel.Cust_mst_ID == null && url.contains("inventory/Wishlist")) {
//////                        Login.StartLoginDialog("wishlist");
//////                        Utils.hideKeyboard();
//////                }
////
                else if (url.contains("inventory/Wishlist") || url.contains("Inventory/Wishlist")) {
                    mContainer.stopLoading();

                    if (UserModel.Cust_mst_ID == null) {
                        Login.StartLoginDialog("wishlist", MainActivity.this);
                        Utils.hideKeyboard();
                    } else {

                        String itemId = url.substring(url.lastIndexOf("/") + 1);
                        if (!isDialogShow) {
                            callAddToWishlistWS(itemId);
                        }
                        isDialogShow = true;
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
                        MainActivity.getInstance().loadItemDescriptionFragment(itemId, "");
                    }

                } else if (afterDecode.contains(/*"InventoryAppList"*/"Inventory/ItemDescriptionApp?customerid=")) {
//
                    view.loadUrl(url);
                    llsortandfilter.setVisibility(View.INVISIBLE);
                    Utils.hideKeyboard();
                }
//                Edited by Varun for when user click on delivery and store time then it reload the home page
//                but now we show the timing of this page.
                else if(afterDecode.contains("GetDeliveryHours")){
                    Log.e("", "shouldOverrideUrlLoading: "+url );
                    view.loadUrl(url);
                    llsortandfilter.setVisibility(View.INVISIBLE);
                    Utils.hideKeyboard();
                }
                else {
                    llsortandfilter.setVisibility(View.INVISIBLE);
                    view.loadUrl(url);
                    //llsortandfilter.setVisibility(View.GONE);
                }
                // Utils.ShowDialog(MainActivity.this);
                return true;
            }

            public void onPageFinished(WebView view, String url) {
                // Log.e("Log", "Url11111=" + url);
                Log.e("Log", "1=2" + url);
                //checkFilterURL = url;
                innerFilterUrl = url;
                //mSearchedt.setText("");
                /*if (url.contains("inventory")) {
                    // if (getFragmentManager().getBackStackEntryCount() == 0)
                    llsortandfilter.setVisibility(View.VISIBLE);
                } else {
                    llsortandfilter.setVisibility(View.GONE);
                }*/
////                ****************** Edited by Varun for backbutton *****************
                if (url.isEmpty() || url.contains("blank")){
                    CallHomeFragment();
                    clearBackStack();
                }
////              ******************* END ****************
                Utils.HideDialog();
            }


            @Override
            public void onLoadResource(WebView view, String url) {
//                Toast.makeText(MainActivity.this,url,Toast.LENGTH_LONG).show();
                Log.e("Log", "1=3" + url);
                if (url.contains("InsertCartData")) {
                    Log.d("cart", "Cart Clied Url: " + url);
                }
                if (url.equals("www.addtocart.com")) {
//                    Toast.makeText(MainActivity.this, "Clicked : " + url, Toast.LENGTH_SHORT).show();
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

    public void callAddToWishlistWS(String itemId) {

        if (Constant.invType.equals("M")){
            String sku = null;

            if (Constant.Test_SKU.isEmpty() || Constant.Test_SKU==null || Constant.Test_SKU.equals("")){

                Utils.ValidationDialog(getInstance(), "Please Select Item");

            }else {
                sku = Constant.Test_SKU;

                if (UserModel.Cust_mst_ID != null) {
                    String wishlistWSurl = WS_BASE_URL + DELETE_CART + "0" + "/" + "Wishlist" + "/" + UserModel.Cust_mst_ID +
                            "/" + itemId + "/" + "1" +
                            "/" + STOREID + "/" + "0" + "/" + "add" + "/" + invType;

                    TaskDeleteWishList deleteWishList = new TaskDeleteWishList(this, "");
//                    Edited by Varun For Speed -up
                    deleteWishList.executeOnExecutor(TaskDeleteWishList.THREAD_POOL_EXECUTOR,wishlistWSurl);
//                    deleteWishList.execute(wishlistWSurl);
                } else {
                    String wishlistWSurl = WS_BASE_URL + DELETE_CART + "0" + "/" + "Wishlist" + "/" + "0" +
                            "/" + itemId + "/" + "1" +
                            "/" + STOREID + "/" + "0" + "/" + "add" + "/" + invType;

                    TaskDeleteWishList deleteWishList = new TaskDeleteWishList(this, "");
//                    Edited by Varun For Speed -up
                    deleteWishList.executeOnExecutor(TaskDeleteWishList.THREAD_POOL_EXECUTOR,wishlistWSurl);
//                    deleteWishList.execute(wishlistWSurl);
                }

            }

        }else {

            if (UserModel.Cust_mst_ID != null) {

                String wishlistWSurl = WS_BASE_URL + DELETE_CART + "0" + "/" + "Wishlist" + "/" + UserModel.Cust_mst_ID +
                        "/" + itemId + "/" + "1" +
                        "/" + STOREID + "/" + "0" + "/" + "add" + "/" + invType;

                TaskDeleteWishList deleteWishList = new TaskDeleteWishList(this, "");
//                Edited by Varun For Speed -up
                deleteWishList.executeOnExecutor(TaskDeleteWishList.THREAD_POOL_EXECUTOR,wishlistWSurl);
//                deleteWishList.execute(wishlistWSurl);

            } else {
                String wishlistWSurl = WS_BASE_URL + DELETE_CART + "0" + "/" + "Wishlist" + "/" + "0" +
                        "/" + itemId + "/" + "1" +
                        "/" + STOREID + "/" + "0" + "/" + "add" + "/" + invType;

                TaskDeleteWishList deleteWishList = new TaskDeleteWishList(this, "");
//                Edited by Varun For Speed -up
                deleteWishList.executeOnExecutor(TaskDeleteWishList.THREAD_POOL_EXECUTOR,wishlistWSurl);
//                deleteWishList.execute(wishlistWSurl);
            }
        }
    }

    public void showReOrderConfirmation(final String orderid, String itemid, final int buy_or_reorder) {

        //change to new reorder
        final Dialog dialog = new Dialog(MainActivity.this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.dialog_confirmation);
        TextView txtErrorName = (TextView) dialog.findViewById(R.id.tv_sub_title_warning_dialog);
//        if (buy_or_reorder == 1) {
//            txtErrorName.setText(getResources().getString(R.string.str_confirmation_buyitagain));
//        } else {

        txtErrorName.setText(getString(R.string.str_confirmation_text));
//        }

        Button btnOK = (Button) dialog.findViewById(R.id.btn_OK);
        GradientDrawable bgShape = (GradientDrawable) btnOK.getBackground();
        bgShape.setColor(Color.parseColor(themeModel.ThemeColor));
        btnOK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
                if (reorderDialog != null && reorderDialog.isShowing()) {
                    reorderDialog.dismiss();
                }

                if (buy_or_reorder == 1) {

                    if (Constant.SCREEN_LAYOUT == 1) {
                        MainActivity.getInstance().callBuyItAgainWS(orderid, itemid, "");

                    } else if (Constant.SCREEN_LAYOUT == 2) {
                        MainActivityDup.getInstance().callBuyItAgainWS(orderid, itemid, "");
                    }
                } else {
                    callInsertReporderWS(MainActivity.this);
                }
            }
        });
        Button btn_Cancel = (Button) dialog.findViewById(R.id.btn_Cancel);
        GradientDrawable bgShapec = (GradientDrawable) btn_Cancel.getBackground();
        bgShapec.setColor(Color.parseColor(themeModel.ThemeColor));
        btn_Cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        dialog.show();
    }

    private void callInsertReporderWS(Context context) {

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


//                StringRequest sr = new StringRequest(Request.Method.POST, Constant.WS_INSERT_REORDER, new Response.Listener<String>() {
//                    @Override
//                    public void onResponse(String response) {
////                        mView.showMessage(response);
//
//                        Log.e("","Response:"+response);
//                    }
//                }, new Response.ErrorListener() {
//                    @Override
//                    public void onErrorResponse(VolleyError error) {
////                        mView.showMessage(error.getMessage());
//                        Log.e("","error:"+error);
//                    }
//                }) {
//                    @Override
//                    public byte[] getBody() throws AuthFailureError {
////                        HashMap<String, String> params2 = new HashMap<String, String>();
////                        params2.put("name", "Val");
////                        params2.put("subject", "Test Subject");
//
//
//                        ObjectMapper mapper = new ObjectMapper();
//                        String json = jsonObjRequest.toString();
//                        Map<String, String> map = null;
//
//                        try {
//
//                            // convert JSON string to Map
//                            map = mapper.readValue(json, Map.class);
//
//                            // it works
//                            //Map<String, String> map = mapper.readValue(json, new TypeReference<Map<String, String>>() {});
//
//                            Log.e("","mapres::"+map);
////                            System.out.println(map);
//
//
//                        } catch (IOException e) {
//                            e.printStackTrace();
//                        }
//                        return new JSONObject(map).toString().getBytes();
//
//                    }
//
//                    @Override
//                    public String getBodyContentType() {
//                        return "application/json";
//                    }
//                };
//


//                JsonObjectRequest jsObjRequest = new JsonObjectRequest(
//                        Request.Method.POST, Constant.WS_INSERT_REORDER, jsonObjRequest,
//                        new Response.Listener<JSONObject>() {
//
//                            @Override
//                            public void onResponse(final JSONObject response) {
//
//                                String res = new String(String.valueOf(response));
////                                Log.e("",
////                                        "Response :--> "
////                                                + response.toString());
//                                reOrderSuccessResponse(res);
//                            }
//                        }, new Response.ErrorListener() {
//
//                    @Override
//                    public void onErrorResponse(VolleyError e) {
//                        e.printStackTrace();
//                        Log.e("", "ERROR:---> " + e.getMessage());
////                        listener.volleyErrorResponse(e);
//                    }
//
//                });
//                jsObjRequest.setRetryPolicy(new DefaultRetryPolicy(SOCKET_TIMEOUT_MS,
//                        DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
//                        DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
//                queue.add(jsObjRequest);


//                StringRequest request = new StringRequest(StringRequest.Method.POST, Constant.WS_INSERT_REORDER, new Response.Listener<String>() {
//                    @Override
//                    public void onResponse(String response) {
//
//                        Log.e("",
//                                        "Response :--> "
//                                                + response.toString());
//                    }
//                }, new Response.ErrorListener() {
//                    @Override
//                    public void onErrorResponse(VolleyError e) {
//
//                        e.printStackTrace();
//                        Log.e("", "ERROR:---> " + e.getMessage());
//                    }
//                }) {
//                    @Override
//                    public String getBodyContentType() {
//                        return "application/json; charset=utf-8";
//                    }
//
//                    @Override
//                    public byte[] getBody() {
//                        try {
//                            JSONObject jsonObject = new JSONObject();
//                            /* fill your json here */
//                            return jsonObjRequest.toString().getBytes("utf-8");
//                        } catch (Exception e) { }
//
//                        return null;
//                    }
//                };


//                CustomRequest jsObjRequest = new CustomRequest(
//                        Request.Method.POST, Constant.WS_INSERT_REORDER, jsonObjRequest, new Response.Listener<String>() {
//                    @Override
//                    public void onResponse(String response) {
//                        Log.e("", "response:---> " + response);
//                    }
//                }, new Response.ErrorListener() {
//
//                    @Override
//                    public void onErrorResponse(VolleyError e) {
//                        e.printStackTrace();
//                        Log.e("", "ERROR:---> " + e.getMessage());
////                        listener.volleyErrorResponse(e);
//                    }
//
//                });
//                jsObjRequest.setRetryPolicy(new DefaultRetryPolicy(SOCKET_TIMEOUT_MS,
//                        DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
//                        DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
//                queue.add(jsObjRequest);

    }

    private void reOrderSuccessResponse(String s) {

//        showReOrderConfirmation(orderid, 2);

        OrderSummaryFragment.getInstence().backOrDismissFrag();
        loadCardFragment();
    }

    private void callNotificationDialog(List<NotificationModel> notifList) {
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
        txtNotification.setTextColor(Color.parseColor(themeModel.ThemeColor));
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

    public void callFilterFragment() {
//        llsearch.setVisibility(View.GONE);
//        mContainer.setVisibility(View.GONE);
//        llsortandfilter.setVisibility(View.GONE);
//        llcheckInternet.setVisibility(View.GONE);
//        mContent.setVisibility(View.VISIBLE);
//        if (filterFragment == null) {
//            filterFragment = new FilterFragment();
//        }

//old --------------
//            String Url1 = Constant.WS_BASE_URL + Constant.GETDEPARTMENT + Constant.STOREID;
//            new Async_getCommonService(this, Url1).execute();
        // end -----------

        callFilterInfoForSubdepartment();

//        if (Constant.SubDepartmentList.size() == 0 && Constant.DepartmentList.size()== 0) {
//            String Url1 = Constant.WS_BASE_URL + Constant.GET_STYLE_DEPARTMENT_LIST + Constant.STOREID;
//            new Async_getCommonService(this, Url1).execute();
//        } else {
//            FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
//            fragmentTransaction.setCustomAnimations(android.R.anim.fade_in, android.R.anim.fade_out);
//            fragmentTransaction.replace(R.id.mContent, filterFragment, FilterFragment.Tag);
//            fragmentTransaction.addToBackStack(FilterFragment.class.getSimpleName());
//            fragmentTransaction.commitAllowingStateLoss();
//        }
    }

    public void callFilterInfoForSubdepartment() {

        String url;

        url = WS_BASE_URL + GET_FILTER_INFO
                + "/" + STOREID;

        TaskFilterInfo taskFilterInfo = new TaskFilterInfo(this, this, "isForDepartment");
//        Edited by Varun For Speed -up
//        taskFilterInfo.execute(url);
        taskFilterInfo.executeOnExecutor(TaskFilterInfo.THREAD_POOL_EXECUTOR,url);
    }


    @Override
    public void onTaskFilterInfoStyleResult(FilterInfoModel filterInfoModel) {
        this.filterInfoModel = filterInfoModel;

        callFilterDetailFragment();

//        if(filterInfoModel != null) {
//
//            if(filterInfoModel.getStyleDisplay()!= null && filterInfoModel.getStyleDisplay().equals("Y")) {
////                if (Constant.SubDepartmentList.size() == 0 || Constant.DepartmentList.size() == 0) {
//                    String Url1 = Constant.WS_BASE_URL + Constant.GET_STYLE_DEPARTMENT_LIST + Constant.STOREID;
//                    new Async_getCommonService(this, Url1).execute();
////                }
////                else{
////                    callFilterDetailFragment();
////                }
//            }else{
//                String Url1 = Constant.WS_BASE_URL + Constant.GETDEPARTMENT + Constant.STOREID;
//                new Async_Dept(Url1,this,false).execute();
//            }
//        }
    }


    public void callFilterDetailFragment() {
        llsearch.setVisibility(View.GONE);
        mContainer.setVisibility(View.GONE);
        llsortandfilter.setVisibility(View.GONE);
        llcheckInternet.setVisibility(View.GONE);
        mContent.setVisibility(View.VISIBLE);
        if (filterFragment == null) {
            filterFragment = new FilterFragment();
        }

        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.setCustomAnimations(android.R.anim.fade_in, android.R.anim.fade_out);
        fragmentTransaction.replace(R.id.mContent, filterFragment, FilterFragment.Tag);
//            fragmentTransaction.addToBackStack(null);
//            fragmentTransaction.addToBackStack(FilterFragment.class.getSimpleName());
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commitAllowingStateLoss();

    }


    public void CallHomeFragment() {

        if (getIntent().getBooleanExtra("openCartFragment", false)) {
            getIntent().putExtra("openCartFragment", false);
            loadCardFragment();
        } else if (getIntent().getBooleanExtra("openWishlistFragment", false)) {
            getIntent().putExtra("openWishlistFragment", false);
            if (UserModel.Cust_mst_ID == null || UserModel.Cust_mst_ID.isEmpty()) {
                CallHomeFragment();
            }
            loadWishListFragment();
        }
        else {
            clearFilterSelection();

//        ************ Edited by Varun for backbutton and shopping cart ****************
            hidebackbutton();
            onGetCartData("");

            rbPayAtStore = false;
            rbPickUpAtStore = false;
            pick_up_time = "";
//        **************** END ************************

            MainActivity.iscomfromSort = false;
            llsearch.setVisibility(View.VISIBLE);
            mContainer.setVisibility(View.GONE);
            llsortandfilter.setVisibility(View.GONE);
            llcheckInternet.setVisibility(View.GONE);
            mContent.setVisibility(View.VISIBLE);
            //if (fragment == null) {
            homepageFragment = new HomepageFragment();

            clearBackStack();
        /*}
        if (Constant.DepartmentList.size() == 0) {
            String Url1 = Constant.WS_BASE_URL + Constant.GETDEPARTMENT + Constant.STOREID;
            new Async_getCommonService(this, Url1).execute();
        } else {*/
            FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
            fragmentTransaction.setCustomAnimations(android.R.anim.fade_in, android.R.anim.fade_out);
            fragmentTransaction.replace(R.id.mContent, homepageFragment, "fragment");

            Log.e("", "onCreateView: Varun going from Main Activity to Home Fragment" );
            //fragmentTransaction.addToBackStack(FilterFragment.class.getSimpleName());
            //fragmentTransaction.addToBackStack(null);
            fragmentTransaction.commitAllowingStateLoss();
            mDrawer.setDrawerListener(actionBarDrawerToggle);
            //}
        }
    }

    public void displayDepartmentList() {
        final View popUpView = getLayoutInflater().inflate(R.layout.raw_dept_popup, null);

        listPopupWindow = new PopupWindow(popUpView, 350,
                LinearLayout.LayoutParams.WRAP_CONTENT, true);
        ListView popupList = popUpView.findViewById(R.id.popupList);
      /*  ImageView rightimg = (ImageView) popUpView.findViewById(R.id.rightimg);
        rightimg.setVisibility(View.INVISIBLE);*/
        DepartmentAdapter sortAdapter = new DepartmentAdapter(MainActivity.this, DepartmentList);
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

    public static void onGetCartData(String buyitagain) {
        String url = null;
        if (UserModel.Cust_mst_ID != null && !UserModel.Cust_mst_ID.equals("null")) {
//            url = WS_BASE_URL + GET_CUSTOMER_CARD_DATA + UserModel.Cust_mst_ID + "/" + MY_CART + STOREID;
            url = WS_BASE_URL + GET_CUSTOMER_CARD_DATA_V1 + UserModel.Cust_mst_ID + "/" + MY_CART + STOREID + Constant.ENCODE_TOKEN_ID;
            TaskCart taskCart = new TaskCart(MainActivity.getInstance(), buyitagain);
//            Edited by Varun For Speed -up
            taskCart.executeOnExecutor(TaskCart.THREAD_POOL_EXECUTOR,url);
//            taskCart.execute(url);
        } else {
//            url = WS_BASE_URL + GET_CUSTOMER_CARD_DATA + DeviceInfo.getDeviceId(MainActivity.getInstance()) + "0011" + "/" + SESSION + STOREID;
            url = WS_BASE_URL + GET_CUSTOMER_CARD_DATA_V1 + DeviceInfo.getDeviceId(MainActivity.getInstance()) + "0011" + "/" + SESSION + STOREID + Constant.ENCODE_TOKEN_ID;
            TaskCart taskCart = new TaskCart(MainActivity.getInstance(), buyitagain);
//            Edited by Varun For Speed -up
            taskCart.executeOnExecutor(TaskCart.THREAD_POOL_EXECUTOR,url);
//            taskCart.execute(url);
        }
    }

    @SuppressLint("SuspiciousIndentation")
    @Override
    public void onShoppingCardResult(List<ShoppingCardModel> liShoppingCard, String s) {
        int quantity = 0;
        for (int i = 0; i < liShoppingCard.size(); i++) {
            if (liShoppingCard.get(i).getQty() != null)
                quantity = quantity + Integer.parseInt(liShoppingCard.get(i).getQty());

            if (!s.isEmpty() && s.equalsIgnoreCase("buyitagain")) {

                if (!liShoppingCard.get(i).getItemMstId().isEmpty() && itemDescModel_forbuyItagain != null && !itemDescModel_forbuyItagain.getItemMstId().isEmpty()) {

                    if (liShoppingCard.get(i).getItemMstId().equalsIgnoreCase(itemDescModel_forbuyItagain.getItemMstId())) {

                        cartQtyOfItem = liShoppingCard.get(i).getQty();
                    }
                }
            }
        }

        if (MainActivity.countMenu != null)
            MainActivity.countMenu.setTitle(String.valueOf(quantity));
            MainActivity.getInstance().updateShoppingCartItemCount(quantity);
    }

    public static void onCallGlobalSetup() {
        String twentyOneYearUrl = WS_BASE_URL + GET_GLOBALSETTING + STOREID;
        TaskTwentyOneYear taskTwentyOneYear = new TaskTwentyOneYear(MainActivity.getInstance());
//        Edited by Varun For Speed -up
//        taskTwentyOneYear.execute(twentyOneYearUrl);
        taskTwentyOneYear.executeOnExecutor(TaskTwentyOneYear.THREAD_POOL_EXECUTOR, twentyOneYearUrl);
//        END

    }

    @Override
    public void onTwentyOneYearResult(TwentyOneYear twentyOneYear) {

        this.twentyOneYear = twentyOneYear;

//        Edited by Varun for lockdown feature
        if (Constant.isSearchLocation) {
            Constant.storeSearchLocationList.clear();

            if (Constant.storeLocationList != null && storeLocationList.size() > 0) {
                Constant.storeSearchLocationList = Constant.storeLocationList;
            } else{
                Utils.setChangeLocationadpater(MainActivity.this, Constant.storeSearchLocationList);
            }

            callStorehoursWSForAllStores(MainActivity.this, Constant.isSearchLocation, Constant.storeSearchLocationList);

        } else {
            if (!IsComeFromSplash) {
                CheckUserLogin();
            }
            if (storeLocationList != null && storeLocationList.size() > 0) {
                for (int i = 0; i < Constant.storeLocationList.size(); i++) {
                    if (Constant.STOREID.equals(Constant.storeLocationList.get(i).getStoreno())) {
                        Constant.co_storeno_value = Constant.storeLocationList.get(i).getCoStoreno();
                    }
                }
            }

            if (this.twentyOneYear != null) {

                if (twentyOneYear.getCustAgeValidOption() == 1) {
                    DialogUtils.on21YearHome(twentyOneYear);

                } else if (twentyOneYear.getCustAgeValidOption() == 2 ||
                        twentyOneYear.getCustAgeValidOption() == 0) {

                    if (co_storeno_value != null && !co_storeno_value.isEmpty()) {
                        takeLocationPermissionFromUser();
                    }

                } else if (twentyOneYear.getCustAgeValidOption() == 3) {
                    DialogUtils.on21YearFaster(MainActivity.this, twentyOneYear);
                }
            }
        }
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

            String storeLocationURL = WS_BASE_URL + GET_CORPORATE_STORE_SUBSTORELIST + "/" + STOREID + "/" + zip;
            TaskStoreLocationInfo taskStoreLocationInfo = new TaskStoreLocationInfo(this, MainActivity.this, false);
//            Edited by Varun For Speed -up
            taskStoreLocationInfo.executeOnExecutor(TaskStoreLocationInfo.THREAD_POOL_EXECUTOR,storeLocationURL);
//            taskStoreLocationInfo.execute(storeLocationURL);
        }
    }


    @Override
    public void storeLocationInfoResult(List<StoreLocationModel> storeLocationList, Boolean isSearchLocation) {

        if (isSearchLocation) {
            Constant.storeSearchLocationList.clear();

            if (storeLocationList != null && storeLocationList.size() > 0) {
                Constant.storeSearchLocationList = storeLocationList;
            } else{
                Utils.setChangeLocationadpater(MainActivity.this, Constant.storeSearchLocationList);
            }

            callStorehoursWSForAllStores(MainActivity.this, isSearchLocation, Constant.storeSearchLocationList);

        }
        else {
            CheckUserLogin();
            if (storeLocationList != null && storeLocationList.size() > 0) {
                Constant.storeLocationList = storeLocationList;
                for (int i = 0; i < Constant.storeLocationList.size(); i++) {
                    if (Constant.STOREID.equals(Constant.storeLocationList.get(i).getStoreno())) {
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
                    if (co_storeno_value != null && !co_storeno_value.isEmpty()) {
                        takeLocationPermissionFromUser();
                    }

                } else if (twentyOneYear.getCustAgeValidOption() == 3) {
                    DialogUtils.on21YearFaster(MainActivity.this, twentyOneYear);
                }
            }

        }
    }


    public static void moveSessionToCart() {
        String seesion = DeviceInfo.getDeviceId(MainActivity.getInstance()) + "0011";
        String seesionUrl;
        TaskSessionToCart sessionToCard = new TaskSessionToCart();
        if (UserModel.Cust_mst_ID != null && !seesion.isEmpty()) {
            seesionUrl = WS_BASE_URL + UPDATE_SESSION_TO_CART
                    + seesion + "/" + UserModel.Cust_mst_ID + "/" + STOREID;
//            Edited by Varun For Speed -up
//            sessionToCard.execute(seesionUrl);
            sessionToCard.executeOnExecutor(TaskSessionToCart.THREAD_POOL_EXECUTOR,seesionUrl);
        }
    }

    public void callResume() {

        Utils.hideKeyboard();
        String token = FirebaseInstanceId.getInstance().getToken();
        Log.e("Log", "token=" + token);
        if (!themeModel.ThemeColor.isEmpty()) {

////            Edited by Varun for backbutton
            ll_backbutton.setBackgroundColor(Color.parseColor(themeModel.ThemeColor));
////            End

            mToolbar.setBackgroundColor(Color.parseColor(themeModel.ThemeColor));
            llsearch.setBackgroundColor(Color.parseColor(themeModel.ThemeColor));
            view_container.setBackgroundColor(Color.parseColor(themeModel.ThemeColor));
        }
        if (URL == null) {
            if (getIntent().getExtras() != null) {
                URL = getIntent().getExtras().getString("URL");
                CustomerId = getIntent().getExtras().getString(
                        "CustomerId");
            }
            /*if (URL != null) {

            }*/
        }
        // CheckUserLogin();//by vasant

    }

    @Override
    public void onStart() {
        super.onStart();
//        onGetCartData("");
    }

    /**
     * Inside On Resume Check if username password is available on Shared Prefrence so go for login
     **/
    @Override
    protected void onResume() {
        super.onResume();

        callResume();

        //edited for checking user enable gps or not edited on - 22th march 2021
        if (isClickedSetting) {
//            gpsTracker = new GpsTracker(MainActivity.this);
//            if(gpsTracker.canGetLocation()){
//
//            }
            getLocation();
            isClickedSetting = false;
        }
        //end *********


       /* String token = FirebaseInstanceId.getInstance().getToken();
        Log.e("Log", "token=" + token);
        if (!Constant.themeModel.ThemeColor.isEmpty()) {
            mToolbar.setBackgroundColor(Color.parseColor(Constant.themeModel.ThemeColor));
            llsearch.setBackgroundColor(Color.parseColor(Constant.themeModel.ThemeColor));
            view_container.setBackgroundColor(Color.parseColor(Constant.themeModel.ThemeColor));
        }
        lldrawer.setBackgroundColor(Color.WHITE);
        if (URL == null) {
            if (getIntent().getExtras() != null) {
                URL = getIntent().getExtras().getString("URL");
                CustomerId = getIntent().getExtras().getString("CustomerId");
            }
            if (URL != null) {
                CheckUserLogin();
            }
        }*/
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
        actionBarDrawerToggle.syncState();
    }

    /**
     * use for handling navigation drawer button state
     **/
    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);

        actionBarDrawerToggle.onConfigurationChanged(newConfig);
    }

    /**
     * use for handling navigation back stack with back Button
     **/
    @Override
    public void onBackStackChanged() {

////         getSupportActionBar().setDisplayHomeAsUpEnabled(getSupportFragmentManager().getBackStackEntryCount() > 0);
//        actionBarDrawerToggle.setDrawerIndicatorEnabled(getSupportFragmentManager().getBackStackEntryCount() == 0);
//
//        getSupportActionBar().setDisplayHomeAsUpEnabled(getSupportFragmentManager().getBackStackEntryCount() >= 1);

////        ************************ Edited by Varun for backbutton ***********************
//
        actionBarDrawerToggle.setDrawerIndicatorEnabled(true);
        if (getSupportFragmentManager().getBackStackEntryCount()>0){
            showbackbutton();
        }else if (getSupportFragmentManager().getBackStackEntryCount()==0){
            hidebackbutton();
        }
//
////        *********************** END ******************

        actionBarDrawerToggle.syncState();

    }

    /**
     * Handle Back event for fragment and webview both
     **/
    @Override
    public void onBackPressed() {

        if (isbackFromPayment) {

            FragmentManager fm = getSupportFragmentManager();
            for (int i = fm.getBackStackEntryCount() - 1; i > 0; i--) {
                if (fm != null && fm.getBackStackEntryAt(i).getName() != null && !Objects.requireNonNull(fm.getBackStackEntryAt(i).getName()).equalsIgnoreCase("DeliveryOptionsFragment.class.getSimpleName()")) {
                    fm.popBackStack();
                } else {
                    break;
                }
            }

            isbackFromPayment = false;
//            if (getSupportFragmentManager().findFragmentByTag("PaymentFragment.class.getSimpleName()") != null) {
//                getSupportFragmentManager().popBackStack("CardFragment",
//                        FragmentManager.POP_BACK_STACK_INCLUSIVE);
//            } else {
//                FragmentManager fm = getSupportFragmentManager();
//                fm.popBackStack();
//            }
        } else {

            onShowShoppingCartIcon();
            if (mDrawer.isDrawerOpen(GravityCompat.START)) {
                mDrawer.closeDrawers();
                return;
            }

            if (getSupportFragmentManager().getBackStackEntryCount() > 0) {
                llsortandfilter.setVisibility(View.GONE);
                getSupportFragmentManager().popBackStackImmediate();
                if (getSupportFragmentManager().getBackStackEntryCount() == 0) {

                    if (isNativePage) {
                        mContainer.setVisibility(View.GONE);
                        llsearch.setVisibility(View.VISIBLE);
                        mContent.setVisibility(View.VISIBLE);
                        CallHomeFragment();
                    } else {
                        mContainer.setVisibility(View.VISIBLE);
                        llsearch.setVisibility(View.VISIBLE);
                        mContent.setVisibility(View.GONE);
                    }
//                    if (mContainer.getUrl() == null) {
//                        URL = null;
//                        //if(SplaceScreen)
//                        //if(homepageFragment.
//                        loadHomeWebPage();
//                    }
                } else {
                    llsortandfilter.setVisibility(View.GONE);
                }
                return;
            } else if (mContent.isShown() && !isNativePage) {
                mContainer.setVisibility(View.VISIBLE);
                llsearch.setVisibility(View.VISIBLE);
                mContent.setVisibility(View.GONE);
            } else {
                if (mContainer.isShown() && mContainer.canGoBack() && Detail_webview_Url) {
                    CallHomeFragment();
                    Log.e("test:::","datais3:");
                    Detail_webview_Url = false;

                } else if (mContainer.isShown() && mContainer.canGoBack() && isviewall_page) {
                    CallHomeFragment();
                    Log.e("test:::","datais4:");
                    isviewall_page = false;
                }
//                else if (mContainer.isShown() && mContainer.canGoBack() && Constant.isBackFromDeparment) {
////                    CallHomeFragment();
//                    Constant.isBackFromDeparment = false;
//                }
                else if (mContainer.isShown() && mContainer.canGoBack()) {
                    Utils.ShowDialog(MainActivity.this);
                    mContainer.goBack();
                    return;
                } else {
                    //super.onBackPressed();
                    CallHomeFragment();
                    Log.e("test:::","datais:6");
                    //home frag
                }
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
        notifCount = (Button) rlCount.findViewById(R.id.notif_count);
        ImageView imgIcon = (ImageView) rlCount.findViewById(R.id.img_icon_count);
        GradientDrawable bgShape1 = (GradientDrawable) notifCount.getBackground();
        bgShape1.setColor(Color.parseColor(themeModel.ThemeColor));
        if (mNotifCount > 0)
            notifCount.setVisibility(View.VISIBLE);
        else
            notifCount.setVisibility(View.GONE);

        notifCount.setText(String.valueOf(mNotifCount));

        imgIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mDrawer.closeDrawers();
                loadCardFragment();
                //loadDeliveryOptionFragment();
            }
        });
        notifCount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mDrawer.closeDrawers();
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

            case R.id.menu_search:
                if (mSearchedt.isShown()) {
                    Utils.collapse(mSearchedt, 500, 0);
                } else {
                    Utils.expand(mSearchedt, 500, height);
                }
                break;
            case R.id.menu_Cart:
                mDrawer.closeDrawers();
                loadCardFragment();
                break;
//            case R.id.menu_Mic:
//                mDrawer.closeDrawers();
//                //speakWords("Voice command please.", "@@@");
//                Intent intent = new Intent(this, MicActivity.class);
//                startActivity(intent);
//                break;
            case R.id.menu_wishlist:
                mDrawer.closeDrawers();
                MainActivity.getInstance().LoadWebVew(URL_PAGE_WISHLIST + STOREID);
                //Toast.makeText(MainActivity.this, "Wait.. under process...", Toast.LENGTH_SHORT).show();
                break;
            case R.id.menu_Count:
                mDrawer.closeDrawers();
                break;
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

//            break;
//            case ACCESS_COARSE_LOCATION: {
//                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
//                    // All good!
//                    Toast.makeText(this, "got location!", Toast.LENGTH_LONG).show();
//                    Log.e("","location-----");
//                } else {
//                    Toast.makeText(this, "Need your location!", Toast.LENGTH_SHORT).show();
//                }
//            }

            // Add additional cases for other permissionsoyou may have asked for
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
                    Toast.makeText(MainActivity.this, "" + result.get(0), Toast.LENGTH_SHORT).show();
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
                        Login.StartLoginDialog("", MainActivity.this);
                    } else if (signoutList.contains(speechLower)) {
                        Login.onLogOff();
                    } else if (closeList.contains(speechLower)) {
                        finish();
                    } else if (homeList.contains(speechLower)) {
                        loadHomeWebPage();
                    } else if (aboutus.contains(speechLower)) {
                        LoadWebVew(URL_PAGE_ABOUT_US + STOREID);
                    } else if (contactus.contains(speechLower)) {
                        MainActivity.getInstance().LoadWebVew(URL_PAGE_CONTACT_US);
                    } else if (blogLis.contains(speechLower)) {
                        MainActivity.getInstance().LoadWebVew(URL_PAGE_BLOG + STOREID);
                    } else if (faqLis.contains(speechLower)) {
                        MainActivity.getInstance().LoadWebVew(URL_PAGE_FAQ + STOREID);
                    } else if (legalLis.contains(speechLower)) {
                        MainActivity.getInstance().LoadWebVew(URL_PAGE_LEGAL + STOREID);
                    } else if (privacyLis.contains(speechLower)) {
                        MainActivity.getInstance().LoadWebVew(URL_PAGE_PRIVACY + STOREID);
                    } else if (shippingList.contains(speechLower)) {
                        MainActivity.getInstance().LoadWebVew(URL_PAGE_SHIPPING + STOREID);
                    } else if (supportList.contains(speechLower)) {
                        MainActivity.getInstance().LoadWebVew(URL_PAGE_SUPPORT + STOREID);
                    } else if (termsLis.contains(speechLower)) {
                        MainActivity.getInstance().LoadWebVew(URL_PAGE_TERMS + STOREID);
                    } else if (helpLis.contains(speechLower)) {
                        MainActivity.getInstance().LoadWebVew(URL_PAGE_HELP + STOREID);
                    } else if (storehourtList.contains(speechLower)) {
                        MainActivity.getInstance().LoadWebVew(URL_PAGE_STORE_HOURS + STOREID);
                    } else {
                        String customerId = "0";
                        if (UserModel.Cust_mst_ID != null)
                            customerId = UserModel.Cust_mst_ID;
                        else
                            customerId = "0";

                        MainActivity.getInstance().mContainer.removeAllViews();
                        MainActivity.getInstance().LoadWebVew(Constant.URL + "/inventory/inventoryapp"
                                + "?customerid=" + customerId
                                + "&sessionid=" + DeviceInfo.getDeviceId(MainActivity.getInstance()) + "0011"
                                + "&storeno=" + STOREID
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
        if (MainActivity.getInstance().mManage_expList != null && LHSLIDER_LIST.size() > 0) {
            try {
                if (MainActivity.getInstance().mManage_expList.isGroupExpanded(0)) {
                    ViewGroup.LayoutParams params = MainActivity.getInstance().mManage_expList.getLayoutParams();
                    params.height = height - 30;
                    MainActivity.getInstance().mManage_expList.setLayoutParams(params);
                    MainActivity.getInstance().mManage_expList.collapseGroup(0);
                    mDrawer.closeDrawers();
                }
            } catch (Exception e) {

            }
        }
    }

    public String getCurrentFragment() {
        if (getSupportFragmentManager().getBackStackEntryCount() > 0) {
            return getSupportFragmentManager().findFragmentById(R.id.mContent).getClass().getSimpleName();
        } else {
            return "";
        }
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
//                Toast.makeText(MainActivity.this, "FCM failed", Toast.LENGTH_SHORT).show();
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
        Voice v = null;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            v = new Voice("en-us-x-sfg#female_2-local", new Locale("en", "US"), 400, 200, true, a);
            myTTS.setVoice(v);
        }

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
                            myTTS.speak(splitspeech[i].toString().trim(), TextToSpeech.QUEUE_FLUSH, map);
                        } else {
                            myTTS.speak(splitspeech[i].toString().trim(), TextToSpeech.QUEUE_FLUSH, map);
                        }
                    } else {
                        if (i == (splitspeech.length - 1)) {
                            myTTS.speak(splitspeech[i].toString().trim(), TextToSpeech.QUEUE_ADD, map);
                        } else {
                            myTTS.speak(splitspeech[i].toString().trim(), TextToSpeech.QUEUE_ADD, map);
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

    public void callReorderDetailsWS(String orderid) {

        String url = WS_BASE_URL + RE_ORDER_DETAILS + orderid + "/" + STOREID;
        TaskReOrder taskCart = new TaskReOrder(this, orderid);
//        Edited by Varun For Speed -up
        taskCart.executeOnExecutor(TaskReOrder.THREAD_POOL_EXECUTOR,url);
//        taskCart.execute(url);
    }

    @Override
    public void onReOrderResult(ReOrderModel model, String fromwhere, String requestdQty) {

        if (!fromwhere.isEmpty() && fromwhere.equalsIgnoreCase("buyItAgain")) {

//            //came here
//            if (model.getResult().equalsIgnoreCase("success") || model.getResult().equalsIgnoreCase("Already added")) {
//
//                loadCardFragment();
//            }

//
        } else {

            if (model.getResult().equalsIgnoreCase("success") || model.getResult().equalsIgnoreCase("Already added")) {

//            OrderSummaryFragment.getInstence().backOrDismissFrag();
                loadCardFragment();
            }
        }
    }


//

    @Override
    public void onReorderListResult(List<ReOrderItemModel> reorderList, String orderid) {
        Context context = this;

        reorderDialog = new Dialog(this);
        reorderDialog.setContentView(R.layout.reorder_detail_dialog);
        reorderDialog.getWindow().setLayout(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);

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
        bgShape.setColor(Color.parseColor(themeModel.ThemeColor));

        GradientDrawable bgShape1 = (GradientDrawable) btnReorder.getBackground();
        bgShape1.setColor(Color.parseColor(themeModel.ThemeColor));

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
                selectedReorderList.clear();
            }
        });

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                reorderDialog.dismiss();
                selectedReorderList.clear();
            }
        });

        //post request
        btnReorder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                showReOrderConfirmation(orderid, "", 2);

            }
        });

        reorderDialog.show();
    }


    @Override
    public void onGetNotificationResult(List<NotificationModel> NotificationList) {
        NOTIFICATION_LIST = NotificationList;
        if (NOTIFICATION_LIST.size() == 0) {
            txtNotification.setVisibility(View.GONE);
        }
        // callNotificationDialog(NotificationList);
    }

    //Edited by Janvi 15thOct
    @Override
    public void onWishListResult(WishList wishList, String string) {

        if (wishList.getResult().equals("success")) {
            DialogUtils.showDialog("Added in Wish List!");
//            if(Constant.isclickedwishlistFromViewall){
//                ViewAllFragment.getInstance().colorwishlistIcon();
//            }
        } else {
            DialogUtils.showDialog("Already in Wish List!");
        }
    }


    public void loadGiftCardFragment() {

        llsortandfilter.setVisibility(View.GONE);
        mContainer.setVisibility(View.GONE);
        llcheckInternet.setVisibility(View.GONE);
        mContent.setVisibility(View.VISIBLE);

        giftCardFragment = new GiftCardFragment();
        loadFragment(giftCardFragment, GiftCardFragment.TAG);

        invalidateOptionsMenu();
    }

    public void loadRewardFragment() {

        llsortandfilter.setVisibility(View.GONE);
        mContainer.setVisibility(View.GONE);
        llcheckInternet.setVisibility(View.GONE);
        mContent.setVisibility(View.VISIBLE);

        rewardFragment = new RewardFragment();
        loadFragment(rewardFragment, RewardFragment.TAG);

        invalidateOptionsMenu();

    }

    public void loadAboutusFragment() {

        llsortandfilter.setVisibility(View.GONE);
        mContainer.setVisibility(View.GONE);
        llcheckInternet.setVisibility(View.GONE);
        mContent.setVisibility(View.VISIBLE);

        aboutUsFragment = new AboutUsFragment();
        loadFragment(aboutUsFragment, AboutUsFragment.TAG);

        invalidateOptionsMenu();
    }

    public void loadContactUsFragment() {
        llsortandfilter.setVisibility(View.GONE);
        mContainer.setVisibility(View.GONE);
        llcheckInternet.setVisibility(View.GONE);
        mContent.setVisibility(View.VISIBLE);

        contactUsFragment = new ContactUsFragment();
        loadFragment(contactUsFragment, ContactUsFragment.TAG);

        invalidateOptionsMenu();
    }


    public void loadStoreandDeliveryFragment(String store_deliveryBtnText) {

        llsortandfilter.setVisibility(View.GONE);
        mContainer.setVisibility(View.GONE);
        llcheckInternet.setVisibility(View.GONE);
        mContent.setVisibility(View.VISIBLE);

        storeDeliveryHourFragment = new StoreDeliveryHourFragment();
        loadFragment(storeDeliveryHourFragment, StoreDeliveryHourFragment.TAG);

        Bundle bundle = new Bundle();
        bundle.putString("store_deliveryBtnText", store_deliveryBtnText);
        storeDeliveryHourFragment.setArguments(bundle);

        invalidateOptionsMenu();
    }


    public void loadOrderHistoryFragment(String orderHistoryBtnText) {

        llsortandfilter.setVisibility(View.GONE);
        mContainer.setVisibility(View.GONE);
        llcheckInternet.setVisibility(View.GONE);
        mContent.setVisibility(View.VISIBLE);

        orderHistoryFragment = new OrderHistoryFragment();
        loadFragment(orderHistoryFragment, OrderHistoryFragment.TAG);

        Bundle bundle = new Bundle();
        bundle.putString("orderHistoryBtnText", orderHistoryBtnText);
        orderHistoryFragment.setArguments(bundle);

        invalidateOptionsMenu();
    }

    public void loadShippingAddressFragment() {

        llsortandfilter.setVisibility(View.GONE);
        mContainer.setVisibility(View.GONE);
        llcheckInternet.setVisibility(View.GONE);
        mContent.setVisibility(View.VISIBLE);

        shippingAddressFragment = new ShippingAddressFragment();
        loadFragment(shippingAddressFragment, ShippingAddressFragment.TAG);

//        Bundle bundle = new Bundle();
//        bundle.putString("shippingaddBtnText", shippingaddBtnText);
//        shippingAddressFragment.setArguments(bundle);

        invalidateOptionsMenu();
    }


    public void loadViewAllFragment(String type, String deptid, String styleId, String blockStratprice, String blockEndprice, String blockdisountGroup, String blockDisplayedText, String searchText, String departmentVal) {

//        if(llfilterType.isEmpty()){
//            llfilterType = type;
//            deptId = String.valueOf(deptid);
//            subDepartment = String.valueOf(styleId);
//        }else{
//            llfilterType = "";
//            deptId = "";
//            subDepartment = "";
//            llfilterType = type;
//            deptId = String.valueOf(deptid);
//            subDepartment = String.valueOf(styleId);
//        }

//        llsortandfilter.setVisibility(View.VISIBLE);
        try {
            llsearch.setVisibility(View.GONE);
            mContainer.setVisibility(View.GONE);
            llcheckInternet.setVisibility(View.GONE);
            mContent.setVisibility(View.VISIBLE);
            isviewall_page = true;

            Bundle bundle = new Bundle();
            bundle.putString("type", type);
            bundle.putString("deptId", String.valueOf(deptid));
            bundle.putString("SubId", String.valueOf(styleId));
            bundle.putString("blockStratprice", blockStratprice);
            bundle.putString("blockEndprice", blockEndprice);
            bundle.putString("blockdisountGroup", blockdisountGroup);
            bundle.putString("blockDisplayedText", blockDisplayedText);
            bundle.putString("shortcall", shortCall);
            bundle.putString("shortdept", shortDept);
            bundle.putString("searchtext", searchText);
            bundle.putBoolean("iscomefromSearchIcon", isSearchicon);
            bundle.putString("OnlyDepartment", departmentVal);

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
        }catch(Exception e){
            Toast.makeText(getApplicationContext(), "Please try again later.", Toast.LENGTH_SHORT).show();
        }
    }

//    private void loadViewAllFragmentForSearchIcon(String searchText) {
//
//        llsortandfilter.setVisibility(View.VISIBLE);
//        llsearch.setVisibility(View.GONE);
//        mContainer.setVisibility(View.GONE);
//        llcheckInternet.setVisibility(View.GONE);
//        mContent.setVisibility(View.VISIBLE);
//        Constant.isviewall_page = true;
//
//        Bundle bundle = new Bundle();
//        bundle.putString("searchtext", searchText);
//        bundle.putBoolean("isComefromSearchIcon",true);
//
//        viewAllFragment = new ViewAllFragment();
//        viewAllFragment.setArguments(bundle);
//
//        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
//        fragmentTransaction.setCustomAnimations(android.R.anim.fade_in, android.R.anim.fade_out);
//        fragmentTransaction.replace(R.id.mContent, viewAllFragment, ViewAllFragment.TAG);
//        fragmentTransaction.commitAllowingStateLoss();
//
//    }


    private JSONObject insertReorderRequest(List<ReOrderItemModel> selectedReorderList) {

        final JSONObject requestBody = new JSONObject();

        try {

            JSONArray jsonArray = new JSONArray();

//            JSONObject formJsonObject = new JSONObject();

            for (int i = 0; i < selectedReorderList.size(); i++) {
                ReOrderItemModel reOrderItemModel = selectedReorderList.get(i);
                JSONObject formJsonObject = new JSONObject();
                formJsonObject.put("CustomerID", reOrderItemModel.getCustomerID());
                formJsonObject.put
                        ("StoreNo", reOrderItemModel.getStoreNo());
                formJsonObject.put("ItemID", reOrderItemModel.getItemID());
                formJsonObject.put("Qty", reOrderItemModel.getQty());

                jsonArray.put(formJsonObject);
            }

//            jsonArray.put(formJsonObject);
            requestBody.put("ReOrderDetails", jsonArray);

            Log.e("", "==>: Insert Transaction request : " + requestBody);

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return requestBody;
    }

    public void sendViewalldata(String deptid, String subdeptId, String type, String blockdisountGroup, String blockStratprice, String blockEndprice, String blockDisplayedText) {
        deptId = deptid;
        subDepartment = subdeptId;
        MainActivity.type = type;
        specialOfferGroup = blockdisountGroup;
        valueOne = blockStratprice;
        valueTwo = blockEndprice;
        this.blockDisplayedText = blockDisplayedText;

    }

    public void showEmailPopup(View view) {

        // initialize a pop up window type
        View popupView = LayoutInflater.from(MainActivity.this).inflate(R.layout.useremail_popup, null);
//        popupWindow = new PopupWindow(
//                popupView,
//                ViewGroup.LayoutParams.MATCH_PARENT,
//                ViewGroup.LayoutParams.WRAP_CONTENT);
//
//        popupWindow.setBackgroundDrawable(new BitmapDrawable());
//        popupWindow.setOutsideTouchable(true);
//        popupWindow.showAsDropDown(popupView);
//
//        popupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
//            @Override
//            public void onDismiss() {
//                //TODO do sth here on dismiss
//            }
//        });

        final PopupWindow popupWindow = new PopupWindow(
                popupView, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        popupWindow.showAsDropDown(view);

        rvUsername = (RecyclerView) popupView.findViewById(R.id.rvUsername);
        rvUsername.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        rvUsername.setLayoutManager(layoutManager);

//        usernameAdapter = new UsernameAdapter(this,Constant.userNameList);
//
//        rvUsername.setAdapter(usernameAdapter);
//        usernameAdapter.notifyDataSetChanged();

        initSwipe1();

    }

    private void initSwipe1() {

        SwipeHelper swipeHelper = new SwipeHelper(this, rvUsername) {

            @Override
            public void instantiateUnderlayButton(final RecyclerView.ViewHolder viewHolder, List<UnderlayButton> underlayButtons) {
                underlayButtons.add(new SwipeHelper.UnderlayButton(
                        "Delete",
                        0,
                        Color.parseColor("#FF3C30"),
                        new SwipeHelper.UnderlayButtonClickListener() {
                            @Override
                            public void onClick(int pos) {
                                // TODO: onDelete
//                                removeView();

                            }
                        }
                ));
//                underlayButtons.add(new SwipeHelper.UnderlayButton(
//                        "Transfer",
//                        0,
//                        Color.parseColor("#FF9502"),
//                        new SwipeHelper.UnderlayButtonClickListener() {
//                            @Override
//                            public void onClick(int pos) {
//                                // TODO: OnTransfer
//                            }
//                        }
//                ));
//                underlayButtons.add(new SwipeHelper.UnderlayButton(
//                        "Unshare",
//                        0,
//                        Color.parseColor("#C7C7CB"),
//                        new SwipeHelper.UnderlayButtonClickListener() {
//                            @Override
//                            public void onClick(int pos) {
//                                // TODO: OnUnshare
//                            }
//                        }
//                ));
            }
//
//            private void removeView(){
//                if(view.getParent()!=null) {
//                    ((ViewGroup) view.getParent()).removeView(view);
//                }
//            }
        };

    }

    public void getCustomerData(UserModel userModel) {
        userModeltemp = userModel;

        if (userModel.Cust_mst_ID != null) {
            String url = WS_BASE_URL + GET_CUSTOMER_DATA + userModel.Cust_mst_ID + "/" + STOREID;
            TaskCustomerData taskCustomerData = new TaskCustomerData(MainActivity.this, this);
            Log.d("", "Customer data : " + url);
//            Edited by Varun For Speed -up
//            taskCustomerData.execute(url);
            taskCustomerData.executeOnExecutor(TaskCustomerData.THREAD_POOL_EXECUTOR,url);
//            END
        }

    }

    @Override
    public void onTaskCustomerResult(ShippingData liShippingData, boolean isFromfavouriteStore) {
        Constant.liShippingData = liShippingData;
//
        if (isFromfavouriteStore) {
            if (liShippingData != null) {
                if (liShippingData.getResult() != null && !liShippingData.getResult().isEmpty()) {
                    if (liShippingData.getResult().equalsIgnoreCase("success")) {
                        getCustomerData();
                    }
                }
            }

        } else {

            if (liShippingData != null) {

                if (liShippingData.getFavLocation() != null && !liShippingData.getFavLocation().isEmpty()) {
                    favstorelocation = liShippingData.getFavLocation();
                    AppPref.edit().putString("favStore", favstorelocation).apply();
                    if(Constant.isclickedFavLocation){
                        DialogUtils.showDialog("Favorite location is updated!");
                        Constant.isclickedFavLocation = false;
                    }
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

    public void loadChangePasswordFragment() {

        llsortandfilter.setVisibility(View.GONE);
        mContainer.setVisibility(View.GONE);
        llcheckInternet.setVisibility(View.GONE);
        mContent.setVisibility(View.VISIBLE);

        changePassword = new ChnagePasswordFragment();
        loadFragment(changePassword, ChnagePasswordFragment.TAG);

        invalidateOptionsMenu();
    }

    public void loadCardOnFileFragment() {

        llsortandfilter.setVisibility(View.GONE);
        mContainer.setVisibility(View.GONE);
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

        manageAccountFragment = new ManageAccountFragment();
        loadFragment(manageAccountFragment, ManageAccountFragment.TAG);

        invalidateOptionsMenu();
    }

    @Override
    public void onGetStoreDeliveryHoursResult(List<StoreHour> storeHourList) {

        if (storeHourList != null && storeHourList.size() > 0) {
            liOnlyStoreHour = storeHourList;
        }
    }

//        popupWindowUserEmail = new PopupWindow(this);
    //instantiate popup window
//        popupWindowUserEmail = new PopupWindow(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
////        popupWindowUserEmail.setFocusable(true);
////        popupWindowUserEmail.showAsDropDown(customView);
////        popupWindowUserEmail.update(customView, 0, 0, ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
////
//        popupWindowUserEmail.setWidth(WindowManager.LayoutParams.MATCH_PARENT);
//        popupWindowUserEmail.setHeight(WindowManager.LayoutParams.WRAP_CONTENT);

    // set the list view as pop up window content
//        popupWindowUserEmail.setContentView(rvUsername);


//    @Override
//    public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction, int position) {
//
//        if(viewHolder instanceof UsernameAdapter.MyViewHolder){
//
//            ((UsernameAdapter.MyViewHolder) viewHolder).viewBackground.setOnTouchListener(new View.OnTouchListener() {
//                @Override
//                public boolean onTouch(View v, MotionEvent event) {
////                    usernameAdapter.removeItem(viewHolder.getAdapterPosition());
//                    Toast.makeText(MainActivity.this,"touch",Toast.LENGTH_LONG).show();
//                    return false;
//                }
//            });
//
//            ((UsernameAdapter.MyViewHolder) viewHolder).viewBackground.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
////                    usernameAdapter.removeItem(viewHolder.getAdapterPosition());
//                    Toast.makeText(MainActivity.this,"click",Toast.LENGTH_LONG).show();
//
//                    // showing snack bar with Undo option
////                    Snackbar snackbar = Snackbar
////                            .make(coordinatorLayout, name + " removed from cart!", Snackbar.LENGTH_LONG);
////            snackbar.setAction("UNDO", new View.OnClickListener() {
////                @Override
////                public void onClick(View view) {
////
////                    // undo is selected, restore the deleted item
////                    mAdapter.restoreItem(deletedItem, deletedIndex);
////                }
////            });
////                    snackbar.setActionTextColor(Color.YELLOW);
////                    snackbar.show();
//                }
//            });
//        }
//
////        if (viewHolder instanceof UsernameAdapter.MyViewHolder) {
//            // get the removed item name to display it in snack bar
////            String name = Constant.userNameList.get(viewHolder.getAdapterPosition()).toString();
////
////            // backup of removed item for undo purpose
//////            final Item deletedItem = Constant.userNameList.get(viewHolder.getAdapterPosition());
//////            final int deletedIndex = viewHolder.getAdapterPosition();
////
////            // remove the item from recycler view
////            usernameAdapter.removeItem(viewHolder.getAdapterPosition());
////
////            // showing snack bar with Undo option
////            Snackbar snackbar = Snackbar
////                    .make(coordinatorLayout, name + " removed from cart!", Snackbar.LENGTH_LONG);
//////            snackbar.setAction("UNDO", new View.OnClickListener() {
//////                @Override
//////                public void onClick(View view) {
//////
//////                    // undo is selected, restore the deleted item
//////                    mAdapter.restoreItem(deletedItem, deletedIndex);
//////                }
//////            });
////            snackbar.setActionTextColor(Color.YELLOW);
////            snackbar.show();
////        }
//
//    }

    public void takeLocationPermissionFromUser() {

        try {
            if (ContextCompat.checkSelfPermission(getApplicationContext(), android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(this, new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION}, 101);
            } else {
                getLocation();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        //old working code start

//        if (ContextCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION)
//                != PackageManager.PERMISSION_GRANTED) {
//            ActivityCompat.requestPermissions(this, new String[] { android.Manifest.permission.ACCESS_COARSE_LOCATION },
//                    ACCESS_COARSE_LOCATION);
//        }

        //old working code end
    }

//    public void refreshActivity() {
//        Intent i = new Intent(MainActivity.this, MainActivity.class);
//        finish();
//        overridePendingTransition(0, 0);
//        startActivity(i);
//        overridePendingTransition(0, 0);
//    }

    public void callStorehoursWSForAllStores(Context context, Boolean isSearchLocation, List<StoreLocationModel> storeLocationList) {

        StoreCloseList.clear();
        isSearchLocationVal = isSearchLocation;

//        if(isSearchLocation){
//            isSearchLocationVal = isSearchLocation;
//        }
//        else {
        int lastpos = storeLocationList.size() - 1;
        boolean isLastStore = false;

        for (int i = 0; i < storeLocationList.size(); i++) {
            if (storeLocationList.get(i).getStoreno() != null && !storeLocationList.get(i).getStoreno().isEmpty()) {
                if (!storeLocationList.get(i).getStoreLock()){
                    String currentStoreNo = storeLocationList.get(i).getStoreno();

                    if (lastpos == i) {
                        isLastStore = true;
                    } else {
                        isLastStore = false;
                    }
                    String Url = WS_BASE_URL + GET_DELIVERY_HOURS + "/" + currentStoreNo + "/" + "store";
                    TaskAllStoreHours allStoreHours = new TaskAllStoreHours(this, context, currentStoreNo, isLastStore);
//                    Edited by Varun For Speed -up
//                    allStoreHours.execute(Url);
                    allStoreHours.executeOnExecutor(TaskAllStoreHours.THREAD_POOL_EXECUTOR, Url);
//                    END
                }
            }
        }
//        }
    }

    @Override
    public void onGetStoreHoursResult(List<StoreHour> storeHourList, String currentStoreNo, boolean isLastStoreForWSCall, Context context) {

        StoreclosesModel storeclosesModel = new StoreclosesModel();

        String closetime = Utils.getTodaysCloseTime(storeHourList);
        storeclosesModel.setClosetime(closetime);
        storeclosesModel.setStoreno(currentStoreNo);

        StoreCloseList.add(storeclosesModel);

        if (isLastStoreForWSCall) {
            if (isSearchLocationVal) {
                Utils.setChangeLocationadpater(context, storeSearchLocationList);
            } else {
                Utils.setChangeLocationadpater(context, storeLocationList);
            }
        }
//        Edited by Varun for lockdown feature
//        else{
//            Utils.setChangeLocationadpater(context, storeSearchLocationList);
//        }
//        END
    }

    public void loadSearchLocationWSdata(Context context, String searchtext) {

        String storeLocationURL = WS_BASE_URL + GET_CORPORATE_STORE_SUBSTORELIST + "/" + STOREID + "/" + searchtext + "/" + "search";
        TaskStoreLocationInfo taskStoreLocationInfo = new TaskStoreLocationInfo(this, context, true);
//        Edited by Varun For Speed -up
//        taskStoreLocationInfo.execute(storeLocationURL);
        taskStoreLocationInfo.executeOnExecutor(TaskStoreLocationInfo.THREAD_POOL_EXECUTOR,storeLocationURL);
    }

    public void callCusDefaultFavLocationWS(Context context, String storeno) {

        if (userModeltemp.Cust_mst_ID != null) {
            String url = WS_BASE_URL + SAVE_CUS_DEFAULT_LOCATION + "/" + storeno + "/" + userModeltemp.Cust_mst_ID;
            TaskCustomerData taskCustomerData = new TaskCustomerData(context, this, true, true);
            Log.d("", "Customer data : " + url);
//            Edited by Varun For Speed -up
//            taskCustomerData.execute(url);
            taskCustomerData.executeOnExecutor(TaskCustomerData.THREAD_POOL_EXECUTOR,url);
        }
    }

    public void callBuyItAgainWS(String orderId, String itemid, String requestdQty) {

        String buyitAgainUrl;
        buyitAgainUrl = WS_BASE_URL + Constant.RE_BUYITAGAIN_DATA + orderId + "/" + itemid + "/" + STOREID;
        TaskReOrder taskCart = new TaskReOrder(MainActivity.this, this, "buyItAgain", requestdQty);
//        Edited by Varun For Speed -up
        taskCart.executeOnExecutor(TaskReOrder.THREAD_POOL_EXECUTOR,buyitAgainUrl);
//        taskCart.execute(buyitAgainUrl);
//        END
    }


    public void CallWSForItemDescriptionDetails(String itemIdSku, String requestdQty) {

        Log.e("itemid", itemIdSku);
        tempReqQty = Integer.parseInt(requestdQty);

        try {
            itemIdSku = URLEncoder.encode(itemIdSku.trim(), "utf-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
            NewItemSku = itemIdSku;
        if (!itemIdSku.equals("")) {
//            String url = Constant.WS_BASE_URL + Constant.GET_INVERNTORY_BY_ID + "/" + itemIdSku + "/" + Constant.STOREID;
            String url = Constant.WS_BASE_URL + GET_INVERNTORY_BY_ID_NEW + "/" + itemIdSku + "/" + Constant.STOREID;
            TaskItemDescription taskItemDescription = new TaskItemDescription(this, MainActivity.this);
//            Edited by Varun For Speed -up
//            taskItemDescription.execute(url);
            taskItemDescription.executeOnExecutor(TaskItemDescription.THREAD_POOL_EXECUTOR,url);
//            END
        }
    }


    @Override
    public void onItemDescResult(ItemDescModel itemDescModel) {

        if (itemDescModel != null) {
            addTocartData(itemDescModel, tempReqQty);
        }
    }


    public void addTocartData(ItemDescModel itemDescModel, int resquantity) {
//        this.homeItemModel = homeItemModel;
//        this.isComeFomAddTocartBtn = isComeFomAddTocartBtn;
//        iscomeFromUpdate = false;
//        requestedQty = resquantity;

        this.itemDescModel_forbuyItagain = itemDescModel;
//        Edited by Varun for multi pack when add to cart is click from BUY IT AGAIN
        if (itemDescModel_forbuyItagain.getInvType().equalsIgnoreCase("M")){
            String sku = null;
            try {
                sku = URLEncoder.encode(itemDescModel.getItemMstId().trim(), "utf-8");
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }

            String invType = itemDescModel.getInvType();

            if (UserModel.Cust_mst_ID != null) {

                String cartWSurl = Constant.WS_BASE_URL + Constant.DELETE_CART + "0" + "/" + "Cart" + "/" + UserModel.Cust_mst_ID +
                        "/" + NewItemSku + "/" + resquantity +
                        "/" + Constant.STOREID + "/" + "0" + "/" + "add" + "/" + invType;

                TaskAddtoCart taskAddToCart = new TaskAddtoCart(this);
//                Edited by Varun For Speed -up
                taskAddToCart.executeOnExecutor(TaskAddtoCart.THREAD_POOL_EXECUTOR,cartWSurl);
//                taskAddToCart.execute(cartWSurl);
            } else {
                String cartWSurl = Constant.WS_BASE_URL + Constant.DELETE_CART + "0" + "/" + "Cart" + "/" + "0" +
                        "/" + NewItemSku + "/" + resquantity +
                        "/" + Constant.STOREID + "/" + DeviceInfo.getDeviceId(MainActivity.this) + "0011" + "/" + "add" + "/" + invType;

                TaskAddtoCart taskAddToCart = new TaskAddtoCart(this);
//                Edited by Varun For Speed -up
                taskAddToCart.executeOnExecutor(TaskAddtoCart.THREAD_POOL_EXECUTOR,cartWSurl);
//                taskAddToCart.execute(cartWSurl);
            }

//            Toast.makeText(mainActivity, "MMMMMMMMMMM", Toast.LENGTH_SHORT).show();
        }else {
//            END
            if (itemDescModel != null && !itemDescModel.getItemMstId().isEmpty()) {

                String sku = null;
                try {
                    sku = URLEncoder.encode(itemDescModel.getItemMstId().trim(), "utf-8");
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
//
                String invType = itemDescModel.getInvType();

                if (UserModel.Cust_mst_ID != null) {

                    String cartWSurl = Constant.WS_BASE_URL + Constant.DELETE_CART + "0" + "/" + "Cart" + "/" + UserModel.Cust_mst_ID +
                            "/" + sku + "/" + resquantity +
                            "/" + Constant.STOREID + "/" + "0" + "/" + "add" + "/" + invType;

                    TaskAddtoCart taskAddToCart = new TaskAddtoCart(this);
//                    Edited by Varun For Speed -up
                    taskAddToCart.executeOnExecutor(TaskAddtoCart.THREAD_POOL_EXECUTOR,cartWSurl);
//                    taskAddToCart.execute(cartWSurl);
                } else {
                    String cartWSurl = Constant.WS_BASE_URL + Constant.DELETE_CART + "0" + "/" + "Cart" + "/" + "0" +
                            "/" + sku + "/" + resquantity +
                            "/" + Constant.STOREID + "/" + DeviceInfo.getDeviceId(MainActivity.this) + "0011" + "/" + "add" + "/" + invType;

                    TaskAddtoCart taskAddToCart = new TaskAddtoCart(this);
//                    Edited by Varun For Speed -up
                    taskAddToCart.executeOnExecutor(TaskAddtoCart.THREAD_POOL_EXECUTOR,cartWSurl);
//                    taskAddToCart.execute(cartWSurl);
                }

            }
        }
    }

    @Override
    public void addToCartEventResult(UpdateCartQuantity addToCart) {

        try {
            if (addToCart != null) {

                if (addToCart.getResult().equalsIgnoreCase("success")) {
                    DialogUtils.showDialog("Added to cart!");
                    onGetCartData("buyitagain");

                } else if (addToCart.getResult().equalsIgnoreCase("Already added")) {

//                if (isComeFomAddTocartBtn) {
                    if (cartQtyOfItem.isEmpty()) {
                        DialogUtils.notEnoughQuantityDialog(MainActivity.this, addToCart, tempReqQty, "Mainactivity_buyItgain", addToCart.getQty());
                    } else {
                        DialogUtils.notEnoughQuantityDialog(MainActivity.this, addToCart, tempReqQty, "Mainactivity_buyItgain", cartQtyOfItem);
                    }
//                    isComeFomAddTocartBtn = false;/
                } else if (addToCart.getResult().equalsIgnoreCase(
                        "Not enough Stock")) {
                    DialogUtils.notEnoughQuantityDialog(MainActivity.this, addToCart, tempReqQty, "NotenoughStock", cartQtyOfItem);
//                tvQty.setText(addToCart.getQty());
                }
            }
            else {
              Toast.makeText(getApplicationContext(), "Please try again later.", Toast.LENGTH_SHORT).show();
            }
        }catch (Exception e){
            Toast.makeText(getApplicationContext(), "Please try again later.", Toast.LENGTH_SHORT).show();
        }
    }


    public void updateToCartData(int finalRequested_Quantity, UpdateCartQuantity updateCartQuantity) {

        String noteCartId = "0";

        if (updateCartQuantity != null && updateCartQuantity.getNote() != null) {
            noteCartId = updateCartQuantity.getNote();
        } else{
            long cartId = 0;
            noteCartId = String.valueOf(cartId);
        }

        if (itemDescModel_forbuyItagain != null && !itemDescModel_forbuyItagain.getItemMstId().isEmpty()) {

            String sku = null;
            try {
                sku = URLEncoder.encode(this.itemDescModel_forbuyItagain.getItemMstId().trim(), "utf-8");
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }

            if (UserModel.Cust_mst_ID != null) {

                String cartWSurl = Constant.WS_BASE_URL + Constant.DELETE_CART + noteCartId + "/" + "Cart" + "/" + UserModel.Cust_mst_ID +
                        "/" + sku + "/" + finalRequested_Quantity +
                        "/" + Constant.STOREID + "/" + "0" + "/" + "Updatemoreincart" + "/" + Constant.invType;

                TaskUpdatetoCart taskUpdatetoCart = new TaskUpdatetoCart(this);
//                Edited by Varun For Speed -up
//                taskUpdatetoCart.execute(cartWSurl);
                taskUpdatetoCart.executeOnExecutor(TaskUpdatetoCart.THREAD_POOL_EXECUTOR,cartWSurl);
            } else {

                String cartWSurl = Constant.WS_BASE_URL + Constant.DELETE_CART + noteCartId + "/" + "Cart" + "/" + "0" +
                        "/" + sku + "/" + finalRequested_Quantity +
                        "/" + Constant.STOREID + "/" + "0" + "/" + "Updatemoreincart" + "/" + Constant.invType;
                TaskUpdatetoCart taskUpdatetoCart = new TaskUpdatetoCart(this);
//                Edited by Varun For Speed -up
//                taskUpdatetoCart.execute(cartWSurl);
                taskUpdatetoCart.executeOnExecutor(TaskUpdatetoCart.THREAD_POOL_EXECUTOR,cartWSurl);
            }
        }
    }

    @Override
    public void updateCartResult(UpdateCartQuantity updateCart) {

        if (updateCart.getResult().equalsIgnoreCase("success")) {
            onGetCartData("buyitagain");
        }
    }


    public void loadEditShippingFragmentDialog(String shippingId) {

        callshippingAddressByID(shippingId);
    }

    private void callshippingAddressByID(String shippingId) {

        if(!shippingId.trim().isEmpty()){
            String url = Constant.WS_BASE_URL + Constant.GET_SHIPPING_ADDRESS_BY_ID + "/" + UserModel.Cust_mst_ID + "/" + Constant.STOREID + "/" + shippingId;
            TaskEditShipping taskCustomerData = new TaskEditShipping(this, this);
            Log.d("", "shipping data : " + url);
//            Edited by Varun For Speed -up
//            taskCustomerData.execute(url);
            taskCustomerData.executeOnExecutor(TaskEditShipping.THREAD_POOL_EXECUTOR,url);
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
//
//        Toast.makeText(getApplicationContext(), "data" + " button clicked",
//                Toast.LENGTH_SHORT).show();
    }

//    Edited by Varun for faster shopping in item description page

    public void fast2() {
//          For Gone
        ll_fast.setVisibility(View.GONE);
    }

//     END

////    ****************** Edited by Varun for backbutton ********************
    public void showbackbutton(){
        image2.setVisibility(View.VISIBLE);
    }

    public void hidebackbutton(){
        image2.setVisibility(View.GONE);
    }

//    Edited by Varun for Delete Account
    public void loadDeleteAccountWS() {

//        Toast.makeText(mainActivity, "fghdfgh", Toast.LENGTH_SHORT).show();
        DialogUtils.Delete_pop_up(this, Custormer_Id);


    }

    public void loadDeletetest(Context context, String cust_mst_id) {

        String url = Constant.WS_BASE_URL + Constant.GET_DELETE_CUSTOMER_ACCOUNT_FROM_ECOM + "/" + Constant.STOREID+ "/" + UserModel.Cust_mst_ID  + "/" + ENCODE_TOKEN_ID;
        TaskDeleteAccount taskDeleteAccount = new TaskDeleteAccount((TaskDeleteAccount.TaskDeleteAccountEvent) this);
        Log.d("", "Delete Account : " + url);
//        Edited by Varun For Speed -up
//        taskDeleteAccount.execute(url);
        taskDeleteAccount.executeOnExecutor(TaskDeleteAccount.THREAD_POOL_EXECUTOR,url);
    }

    @Override
    public void onGetDeleteAccountResult(DeleteAccountModel deleteAccountModel) {
        if (deleteAccountModel.getResult().equalsIgnoreCase("Success")){
//            Toast.makeText(mainActivity, "Success", Toast.LENGTH_SHORT).show();
            Utils.showValidationDialog(this,deleteAccountModel.getMessage(),"Delete my online Account");
//            Login.onLogOff();
        }else{
//            Toast.makeText(mainActivity, "Fail"+deleteAccountModel.getMessage(), Toast.LENGTH_SHORT).show();
            Utils.showValidationDialog(this,deleteAccountModel.getMessage(),"Delete Account Fail");
        }
    }




////    ********************* END ***********************

}

