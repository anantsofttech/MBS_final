package com.aspl.fragment;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.Rect;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.GradientDrawable;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.core.widget.NestedScrollView;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.text.Editable;
import android.text.Html;
import android.text.InputFilter;
import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.TextPaint;
import android.text.TextWatcher;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.text.style.ForegroundColorSpan;
import android.text.style.UnderlineSpan;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.ContextMenu;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.aspl.Adapter.AddressesAdapter;
import com.aspl.Adapter.AdvancePaymentOptionAdapter;
import com.aspl.Adapter.zipAdapter;
import com.aspl.Utils.BSTheme;
import com.aspl.Utils.Constant;
import com.aspl.Utils.DialogUtils;
import com.aspl.Utils.Utils;
import com.aspl.mbs.MainActivity;
import com.aspl.mbs.MainActivityDup;
import com.aspl.mbs.R;
import com.aspl.mbsmodel.CalculateShippingModel;
import com.aspl.mbsmodel.ContatInfo;
import com.aspl.mbsmodel.HomeItemModel;
import com.aspl.mbsmodel.OrderDetails;
import com.aspl.mbsmodel.PaymentOptions;
import com.aspl.mbsmodel.PickupModel;
import com.aspl.mbsmodel.PinModel;
import com.aspl.mbsmodel.ShippingData;
import com.aspl.mbsmodel.ShippingInfo;
import com.aspl.mbsmodel.ShippingRatesModel;
import com.aspl.mbsmodel.StoreHour;
import com.aspl.mbsmodel.TwentyOneYear;
import com.aspl.mbsmodel.UpdatePOSBillingAddress;
import com.aspl.mbsmodel.UserModel;
import com.aspl.mbsmodel.Zipmodel;
import com.aspl.task.TaskAdvancePaymentOptions;
import com.aspl.task.TaskContactInfo;
import com.aspl.task.TaskCustomerData;
import com.aspl.task.TaskDeliveryFeeSalesTax;
import com.aspl.task.TaskGetZipCode;
import com.aspl.task.TaskInsertTempOrder;
import com.aspl.task.TaskShippingData;
import com.aspl.task.TaskShippingFlatRateByStoreNo;
import com.aspl.task.TaskShippingRates;
import com.aspl.task.TaskShippingServiceDetails;
import com.aspl.task.TaskStoreDeliveryHours;
import com.aspl.task.TaskStoreHour;
import com.aspl.task.TaskTwentyOneYear;
import com.aspl.task.TaskUpdateBillingAddress;
import com.aspl.task.TaskUpdateBillingTip;
import com.aspl.task.TaskUpdatePOSBillingAddress;
import com.aspl.task.TaskUpdatePOSShippingAddress;
import com.aspl.task.TaskAddCustomerShippingAddress;
import com.aspl.ws.Async_getAddress;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;

import static com.aspl.mbs.MainActivity.actionBarDrawerToggle;

/**
 * Created by mic on 1/4/2018.
 */

public class DeliveryOptionsFragment extends Fragment
        implements View.OnClickListener
        , View.OnFocusChangeListener
        , CompoundButton.OnCheckedChangeListener
        , TaskShippingData.TaskShippingEvent
        , TaskCustomerData.TaskCustomerEvent
        , AddressesAdapter.AddressesAdapterEvent
        , TaskInsertTempOrder.TaskInsertTempOrderEvent
        , TaskUpdateBillingTip.UpdateBillingTipEvent
        , TaskAdvancePaymentOptions.TaskPaymentOptions
        , TaskUpdateBillingAddress.UpdateBillingAddressEvent
        , TaskUpdatePOSBillingAddress.UpdatePOSBillingAddressEvent
        , TaskAddCustomerShippingAddress.AddCustomerShippingAddressEvent
        , TaskUpdatePOSShippingAddress.UpdatePOSShippingAddressEvent
        , TaskStoreHour.TaskStoreHourEvent
        , TaskGetZipCode.TaskGetZipEvent
        , TaskDeliveryFeeSalesTax.TaskDeliveryFeeTax
        , TaskTwentyOneYear.TaskTwentyOneYearEvent,
        TaskStoreDeliveryHours.StoreDeliveryHoursEvent, TaskContactInfo.TaskContactInfoEvent, TaskShippingServiceDetails.TaskShippingServiceDetailsEvent, TaskShippingRates.TaskShippingRatesEvent, TaskShippingFlatRateByStoreNo.TaskShippingFlatRateByStoreNoEvent {


    public static final String TAG = "DeliveryOptionFragment";
    public static DeliveryOptionsFragment deliveryOptionsFragment;

    NestedScrollView scrollView;
    LinearLayout llMain, llPickUpAtStore,ll_main_layout;
    RelativeLayout rvMain;
    CardView cvDeliveryOption, cvBillingOrShippingDetails;
    //    VARUNN
    public RadioButton rbPickUpAtStore, rbPayAtStore, rbPayWithCart, rbUberRush, rbHandOnDelivery, rbShip;
    //    RadioGroup rgMain, rgPickUpAtStore , rgShip;
    public CheckBox cbxShip, cbx_hand_delivery; //Edited by Janvi on 29th Sep * end ***//
    Spinner spinnerMobile, spinnerBsMobile, spinnerShippingService;
    ImageView imgSpinnerIcon, imgBsSpinnerIcon;
    public Button btnPrev, btnNext;
    TextView txtDeliveryOption, txtDeliveryDetails, txtAddressLists, tvClear, txtBillingfirstview;
    TextView tvRadioGroupHandDelivery, tvMinimumAmountRadioGroupHandDelivery, tvStoreClosed, tv_store_personally_deliver;
    TextView tvTitleLastName, tvTitleFirstName, tvTitleCompanyName, tvTitlePhoneNo, tvTitleAddressOne, tvTitleAddressTwo, tvTitleZip, tvTitleCity, tvTitleState;
    TextView etBPhoneNoDO, tvBTitleLastName, tvBTitleFirstName, tvBTitleCompanyName,
            tvBTitleEmail, tvBEmail, tvBTitlePhoneNo, tvBTitleAddressOne, tvBTitleAddressTwo,
            tvBTitleZip, tvBTitleCity, tvBTitleState,tv_shipping_charges;
    public static TextView tvWarnTwoHourAgo;
    public static AutoCompleteTextView etBAddressOneDO, etAddressOneDO;
    public static boolean isdhowdropdown = true;
    TwentyOneYear twentyOneYear_Current = null;
    LinearLayout ll_shipping_Service, ll_temp;
//    public boolean isBypassDeliveryOption = false;

    public int isPickUpAtStore = 0, isPayAtStore = 0, isPayWithCard = 0, isUberRush = 0, isHandDelivery = 0, isDeliverHome = 0, isSame = 0;

    //DO = Delivery Option
    public static EditText etLastNameDO, etFirstNameDo, etCompanyDO, etPhoneNoDO, /*etAddressOneDO,*/
            etAddressTwoDO, etZIpDO, etCityDo, etStateDO, et_tipvalue;
    public static EditText etBLastNameDO, etBFirstNameDo, etBCompanyDO, etBsEmail, /*etBsAddressOneDO,*/
            etBAddressTwoDO, etBZIpDO, etBCityDo, etBStateDO;
    ShippingData shippingData;
    List<ShippingData> liShippingData;
    public Menu menu;
    public int selectedRadioButton = 0;
    public boolean isTipDialog = false, isTipDialogEnable = false;
    float fifteenResult = 0.0f, eighteenResult = 0.0f, twentyResult = 0.0f;
    float deliverysalexTax;
    public boolean isDeliveryPage = false;
    ProgressDialog loading = null;
    ArrayList<PickupModel> results;
    String zipforShipping = "", contryforshipping = "", addressForshipping = "", cityForshipping = "", stateForshipping = "";

    /**
     * Dialog Views
     **/
    // AD = Addresses Dialog
    // TD - Tip Dialog
    public static Dialog addressesDialog, tipDialog, customTipDialog;
    public static View vAddressesDialog, vAD, vAlternativeAD, vTipDialog, vTD;
    public static TextView tvTitleAD, tvAlternativeTextAD, tvTitleTD, tvTitleSubTotalTD, tvSubTotalTD, tvTipApplyTD;
    public static RecyclerView rvAD;
    public static ImageView imgCancel, iv_close , img_last_name, img_first_name, img_address_two, img_address_one, img_company_name, img_city, img_zip;
    public static ImageView img_dd_last_name, img_dd_first_name, img_dd_company_name, img_dd_phone_no,img_dd_address_one, img_dd_address_two, img_dd_city, img_dd_zip;
    public static RadioGroup rgTipTD;
    public static RadioButton rbTipFifteenTD, rbTipEighteenTD, rbTipTwentyTD, rbCustomTipTD, rbCashTipTD, rbNoTipTD;
    public static Button btnContinueTD, btn_submit_tip_dialog , btnBackTD , btnBackTD2;

    public float tipValue = 00.f, _total = 0.0f, _subTotal = 0.0f, _salesTax = 0.0f, _wineTax = 0.0f, _miscTax = 0.0f, _flatTax = 0.0f, _bottleDeposit = 0.0f, _shipping = 0.0f, _totalSaving, _lPoints = 0.0f, _minimumHandDeliveryLimit = 0.0f;
    public int tipSubTotalValue = 0;
    public float defaultVal = 3;
    int tipSelectedOption = 0, tipCCValue = 0, flagTipCashTip = 0;
    int position = -1;
    public static int selectedAddress;

    String S_name = "";

    TextView test_site_link;
    LinearLayout lin_test_site_link;

    CardView cv_ship;

    /**
     * Interface
     **/
    DeliveryOptionsEvent myDeliveryOptionsEvent;
    List<StoreHour> liDeliveryHour;

    LinearLayout ll_custom_tip, llAdvancePaymentOptions , ll_custom_tip_btn;
    boolean isNextCall = false;
    ListView listView;
    RecyclerView recyclerView;
    ArrayList<String> paymentOptionsList;
    boolean isStoreClosed = false;
    Context context;

    //    VARUNN
    RadioGroup rgMain, rgPickUpAtStore , rgShip , rbg_delivery_option_ubber, rbg_delivery_option_hand, rbg_delivery_option_ship;
    private boolean isHandlingChange = false;
    private boolean isHandlingChange2 = false;
    LinearLayout cvRadioButton;
    CardView cv_radio_button_pick_up, cv_radio_button_pick_up_sub_menu, cv_radio_button_ubber, cv_radio_button_hand_delivery, cv_radio_button_hand_delivery_Check_box, cv_radio_button_ship ;


    public interface DeliveryOptionsEvent {
        void nextFromDeliveryOption(Bundle bundle);
    }

    /**
     * Instance for fragment
     **/
    public static DeliveryOptionsFragment getInstance() {
        return deliveryOptionsFragment;
    }

    @Override
    public void onStart() {
        super.onStart();
        onCallCustomerDataTask();
        onCallDeliveryHour();
        onCallStoreHour();
        onCallpaymentOptions();
        //Edited by janvi 15th Dec ********
        onCallDeliveryFeeSalexTax();
        loadContactInfoWSdata();
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        deliveryOptionsFragment = this;
        setHasOptionsMenu(true);
        setRetainInstance(true);

        if(getActivity() != null) {
            loading = new ProgressDialog(getActivity(), R.style.MyprogressDTheme);
            loading.setCancelable(false);
//        loading.setMessage(Constant.Message.AuthenticatingUser);
            loading.setProgressStyle(ProgressDialog.STYLE_SPINNER);
            loading.show();
        }

    }

    @Override
    public void onResume() {
        super.onResume();

        Log.e("Log", "OnResume1=");
        if(Constant.SCREEN_LAYOUT == 2){
            MainActivityDup.getInstance().showbackButton();
        }else if (Constant.SCREEN_LAYOUT==1){
//            MainActivity.getInstance().llsearch.setVisibility(View.GONE);
        }
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        // Get Above Fragment data
        Bundle bundle = this.getArguments();
        if (bundle != null) {
            _total = bundle.getFloat("total", 0);
            _subTotal = bundle.getFloat("sub_total", 0);
            _salesTax = bundle.getFloat("sales_tax", 0);
            _wineTax = bundle.getFloat("wine_tax", 0);
            _miscTax = bundle.getFloat("misc_tax", 0);
            _flatTax = bundle.getFloat("flat_tac", 0);
            _bottleDeposit = bundle.getFloat("bottle_deposit", 0);
            _shipping = bundle.getFloat("shipping", 0);
            _lPoints = bundle.getFloat("loyalty_reward", 0);
//            ********* Edited by Varun*********
            _totalSaving = bundle.getFloat("total_saving",0);
//            ********* END **********

        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_delivery_option, container, false) /*super.onCreateView(inflater, container, savedInstanceState)*/;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initViews(view);

        LinearLayoutManager linearLayoutManager=null;
        if(Constant.SCREEN_LAYOUT==1){
            linearLayoutManager = new LinearLayoutManager(MainActivity.getInstance());
        }else if(Constant.SCREEN_LAYOUT==2) {
            linearLayoutManager = new LinearLayoutManager(MainActivityDup.getInstance());
        }

        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setHasFixedSize(true);

//        Edited by Janvi 27th sep **********
//        getActivity().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN | WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);
        getActivity().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);
        //end *****************

        //showDrawerButton();
    }

    private void onCallDeliveryFeeSalexTax() {

        String url = Constant.WS_BASE_URL + Constant.GET_DEL_MIN_DATA + "/" + Constant.STOREID;
        TaskDeliveryFeeSalesTax taskDeliveryFeeSalesTax = new TaskDeliveryFeeSalesTax(this);
//        taskDeliveryFeeSalesTax.execute(url);
        taskDeliveryFeeSalesTax.executeOnExecutor(TaskDeliveryFeeSalesTax.THREAD_POOL_EXECUTOR,url);
    }

    private void loadContactInfoWSdata() {

        String getContactInfoURL = Constant.WS_BASE_URL + Constant.GET_CONTACT_INFO + "/" + Constant.STOREID;
        TaskContactInfo taskContactInfo = new TaskContactInfo(this,getActivity());
//        taskContactInfo.execute(getContactInfoURL);
        taskContactInfo.executeOnExecutor(TaskContactInfo.THREAD_POOL_EXECUTOR,getContactInfoURL);
    }

    @Override
    public void onDelievryFeeSalesTaxResult(List<HomeItemModel> liHomeItemModel) {
        if (liHomeItemModel != null && liHomeItemModel.size()>0 && !String.valueOf(liHomeItemModel.get(0).getSalesTax1()).equals(null)) {
            deliverysalexTax = Float.parseFloat((String.valueOf(liHomeItemModel.get(0).getSalesTax1())));
        }
        if(loading != null && loading.isShowing()){
            loading.dismiss();
        }
    }

    private void onCallpaymentOptions() {
        String url = Constant.WS_BASE_URL + Constant.GET_ADVANCE_PAYMENT_OPTIONS + "/" + Constant.STOREID;
        TaskAdvancePaymentOptions taskpaymentOptions = new TaskAdvancePaymentOptions(this);
//        taskpaymentOptions.execute(url);
        taskpaymentOptions.executeOnExecutor(TaskAdvancePaymentOptions.THREAD_POOL_EXECUTOR,url);
    }


    @Override
    public void onPaymentOptionsResult(PaymentOptions paymentOptionsModel) {

        if (paymentOptionsModel != null) {

            if (!paymentOptionsModel.getApplePay() && !paymentOptionsModel.getGooglePay() && !paymentOptionsModel.getMasterPass() && !paymentOptionsModel.getPayPal()) {
                if (llAdvancePaymentOptions.isShown()) {
                    llAdvancePaymentOptions.setVisibility(View.GONE);
                }
            } else {

                llAdvancePaymentOptions.setVisibility(View.VISIBLE);

//                if (paymentOptionsModel.getApplePay()) {
////                paymentOptionsList.add("ApplePay");
//                }

                if (paymentOptionsModel.getGooglePay()) {
                    paymentOptionsList.add("GooglePay");
                }
                if (paymentOptionsModel.getMasterPass()) {
                    paymentOptionsList.add("Masterpass");
                }
                if (paymentOptionsModel.getPayPal()) {
                    paymentOptionsList.add("Paypal");
                }

                if (paymentOptionsList.size() > 0) {
                    paymentOptionsList.add(0, "Including:");
                }
                Constant.paymentOptionsList = paymentOptionsList;

                recyclerView.setAdapter(new AdvancePaymentOptionAdapter(getActivity(),  Constant.paymentOptionsList));
//                listView.setAdapter(new AdvancePaymentOptionAdapter(getActivity(), Constant.paymentOptionsList));
            }
        }
    }

    public void showDrawerButton() {
        if (Constant.SCREEN_LAYOUT == 1) {
            if (getActivity() instanceof MainActivity) {
                ((MainActivity) getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(false);
            }
        } else if (Constant.SCREEN_LAYOUT == 2) {
            if (getActivity() instanceof MainActivityDup) {
                ((MainActivityDup) getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(false);
            }
        }
        actionBarDrawerToggle.syncState();
    }

    /**
     * Views Initialize
     **/
    private void initViews(View v) {
        scrollView = v.findViewById(R.id.nested_scroll_delivery_option_fragment);
//        listView = v.findViewById(R.id.listview);
        recyclerView = v.findViewById(R.id.recyclerview);
        paymentOptionsList = new ArrayList<>();

        //Layouts
        rvMain = v.findViewById(R.id.rl_main_delivery_option_fragment);
        llMain = v.findViewById(R.id.ll_root_main_delivery_option_fragment);
        ll_main_layout = v.findViewById(R.id.ll_main_layout);
        ll_main_layout.setBackgroundColor(Color.parseColor(Constant.themeModel.Backgroundcolor));
        llMain.setVisibility(View.GONE);

        llAdvancePaymentOptions = v.findViewById(R.id.llAdvancePaymentOptions);
        //rvMain.setBackgroundColor(Color.parseColor(Constant.themeModel.Backgroundcolor));
        //llMain.setBackgroundColor(Color.parseColor(Constant.themeModel.Backgroundcolor));


        //CardView
        cvRadioButton = v.findViewById(R.id.cv_radio_button_delivery_option_fragment); //Root Radio Group View
        cvRadioButton.setVisibility(View.GONE);
        cvDeliveryOption = v.findViewById(R.id.cv_delivery_details_delivery_option_fragment); //Delivery Option View
        cvBillingOrShippingDetails = v.findViewById(R.id.cv_billing_or_shipping_details_delivery_option_fragment); //Billing/Shipping Options


        //Linear layout
        llPickUpAtStore = v.findViewById(R.id.ll_rg_pick_up_at_store_delivery_option_fragment);
        ll_shipping_Service = v.findViewById(R.id.ll_shipping_Service);

//        ll_temp = v.findViewById(R.id.ll_temp);

        //TextView Labels
        txtDeliveryOption = v.findViewById(R.id.tv_main_title_delivery_option_fragment);
        txtDeliveryOption.setTextColor(Color.parseColor(Constant.themeModel.ThemeColor));
        txtDeliveryDetails = v.findViewById(R.id.tv_main_Delivery_detail_delivery_option_fragment);
        txtDeliveryDetails.setTextColor(Color.parseColor(Constant.themeModel.ThemeColor));
        txtAddressLists = v.findViewById(R.id.tv_address_list_detail_delivery_option_fragment);
        txtAddressLists.setTextColor(Color.parseColor(Constant.themeModel.ThemeColor));
        txtAddressLists.setText(Utils.underlineText(getString(R.string.lbl_address_list)));
        txtAddressLists.setOnClickListener(this);
        tvClear = v.findViewById(R.id.tv_dd_clear_delivery_option_fragment);
        tvClear.setTextColor(Color.parseColor(Constant.themeModel.ThemeColor));
        tvClear.setText(Utils.underlineText(getString(R.string.lbl_clear)));
        tvClear.setOnClickListener(this);
        txtBillingfirstview = v.findViewById(R.id.tv_main_billing_and_shipping_delivery_option_fragment);
        txtBillingfirstview.setTextColor(Color.parseColor(Constant.themeModel.ThemeColor));
        //String addressList = getString(R.string.lbl_address_list);


        //TExtView Titles
        tvTitleLastName = v.findViewById(R.id.tv_dd_title_last_name_delivery_option_fragment);
        tvTitleFirstName = v.findViewById(R.id.tv_dd_title_first_name_delivery_option_fragment);
        tvTitleCompanyName = v.findViewById(R.id.tv_dd_title_company_name_delivery_option_fragment);
        tvTitlePhoneNo = v.findViewById(R.id.tv_dd_title_phone_number_delivery_option_fragment);
        tvTitleAddressOne = v.findViewById(R.id.tv_dd_title_address_one_delivery_option_fragment);
        tvTitleAddressTwo = v.findViewById(R.id.tv_dd_title_address_two_delivery_option_fragment);
        tvTitleZip = v.findViewById(R.id.tv_dd_title_zip_delivery_option_fragment);
        /*tvTitleCity = v.findViewById(R.id.tv_dd_title_city_delivery_option_fragment);
        tvTitleState = v.findViewById(R.id.tv_dd_title_state_delivery_option_fragment);*/

        tvBTitleLastName = v.findViewById(R.id.tv_bs_title_last_name_delivery_option_fragment);
        tvBTitleFirstName = v.findViewById(R.id.tv_bs_title_first_name_delivery_option_fragment);
        tvBTitleEmail = v.findViewById(R.id.tv_bs_title_email_delivery_option_fragment);
        tvBEmail = v.findViewById(R.id.tv_bs_email_delivery_option_fragment);
        tvBTitleCompanyName = v.findViewById(R.id.tv_bs_title_company_name_delivery_option_fragment);
        tvBTitlePhoneNo = v.findViewById(R.id.tv_bs_title_phone_number_delivery_option_fragment);
        tvBTitleAddressOne = v.findViewById(R.id.tv_bs_title_address_one_delivery_option_fragment);
        tvBTitleAddressTwo = v.findViewById(R.id.tv_bs_title_address_two_delivery_option_fragment);
        tvBTitleZip = v.findViewById(R.id.tv_bs_title_zip_delivery_option_fragment);
        tvWarnTwoHourAgo = v.findViewById(R.id.tv_warn_two_hour_ago_delivery_option_fragment);
       /* tvBsTitleCity = v.findViewById(R.id.tv_bs_title_city_delivery_option_fragment);
        tvBsTitleState = v.findViewById(R.id.tv_bs_title_state_delivery_option_fragment);*/


        //EditText
        etLastNameDO = v.findViewById(R.id.et_dd_last_name_delivery_option_fragment);
        etLastNameDO.setOnFocusChangeListener(this);
        etFirstNameDo = v.findViewById(R.id.et_dd_first_name_delivery_option_fragment);
        etFirstNameDo.setOnFocusChangeListener(this);
        etCompanyDO = v.findViewById(R.id.et_dd_company_name_delivery_option_fragment);
        etCompanyDO.setOnFocusChangeListener(this);
        etPhoneNoDO = v.findViewById(R.id.et_dd_phone_number_delivery_option_fragment);
        etPhoneNoDO.setOnFocusChangeListener(this);
        etAddressOneDO = v.findViewById(R.id.et_dd_address_one_delivery_option_fragment);
        etAddressOneDO.setOnFocusChangeListener(this);
        etAddressTwoDO = v.findViewById(R.id.et_dd_address_two_delivery_option_fragment);
        etAddressTwoDO.setOnFocusChangeListener(this);
        etZIpDO = v.findViewById(R.id.et_dd_zip_delivery_option_fragment);
        etZIpDO.setOnFocusChangeListener(this);

        etCityDo = v.findViewById(R.id.et_dd_city_delivery_option_fragment);
        etCityDo.setOnFocusChangeListener(this);
        etStateDO = v.findViewById(R.id.et_dd_state_delivery_option_fragment);

        etBLastNameDO = v.findViewById(R.id.et_bs_last_name_delivery_option_fragment);
        etBLastNameDO.setOnFocusChangeListener(this);
        etBFirstNameDo = v.findViewById(R.id.et_bs_first_name_delivery_option_fragment);
        etBFirstNameDo.setOnFocusChangeListener(this);
        etBCompanyDO = v.findViewById(R.id.et_bs_company_name_delivery_option_fragment);
        etBCompanyDO.setOnFocusChangeListener(this);
        etBPhoneNoDO = v.findViewById(R.id.et_bs_phone_number_delivery_option_fragment);
        etBAddressOneDO = v.findViewById(R.id.et_bs_address_one_delivery_option_fragment);
        etBAddressOneDO.setOnFocusChangeListener(this);

        etBAddressTwoDO = v.findViewById(R.id.et_bs_address_two_delivery_option_fragment);
        etBAddressTwoDO.setOnFocusChangeListener(this);
        etBZIpDO = v.findViewById(R.id.et_bs_zip_delivery_option_fragment);
        etBZIpDO.setOnFocusChangeListener(this);
        etBCityDo = v.findViewById(R.id.et_bs_city_delivery_option_fragment);
        etBCityDo.setOnFocusChangeListener(this);
        etBStateDO = v.findViewById(R.id.et_bs_state_delivery_option_fragment);
        //etBsPhoneNoDO.setText(Html.fromHtml(getString(R.string.str_mobile_hint)));
//        etBsPhoneNoDO.setOnFocusChangeListener(this);

//        etBsZIpDO.setOnFocusChangeListener(new View.OnFocusChangeListener() {
//            @Override
//            public void onFocusChange(View view, boolean b) {
//                if (b) {
//                    scrollView.scrollTo(0, scrollView.getBottom());
//                }
//            }
//        });

//        etZIpDO.setOnFocusChangeListener(new View.OnFocusChangeListener() {
//            @Override
//            public void onFocusChange(View view, boolean b) {
//                if (b) {
//                    scrollView.scrollTo(0, scrollView.getBottom());
//                }
//            }
//        });

        //SetText On TextView
        etLastNameDO.setHint(Html.fromHtml(getString(R.string.lbl_last_name)));
        etFirstNameDo.setHint(Html.fromHtml(getString(R.string.lbl_first_name)));
        etCompanyDO.setHint(Html.fromHtml(getString(R.string.lbl_company_name)));
        etPhoneNoDO.setHint(Html.fromHtml(getString(R.string.lbl_phone_number)));
        etAddressOneDO.setHint(Html.fromHtml(getString(R.string.lbl_address1)));
        etAddressTwoDO.setHint(Html.fromHtml(getString(R.string.lbl_address2)));
        etZIpDO.setHint(Html.fromHtml(getString(R.string.lbl_zip)));
        etCityDo.setHint(Html.fromHtml(getString(R.string.lbl_city)));
        etStateDO.setHint(Html.fromHtml(getString(R.string.lbl_state)));

        etBLastNameDO.setHint(Html.fromHtml(getString(R.string.lbl_last_name)));
        etBFirstNameDo.setHint(Html.fromHtml(getString(R.string.lbl_first_name)));
        etBCompanyDO.setHint(Html.fromHtml(getString(R.string.lbl_company_name)));
        tvBTitleEmail.setHint(Html.fromHtml(getString(R.string.lbl_email_title)));
        tvBTitlePhoneNo.setHint(Html.fromHtml(getString(R.string.lbl_phone_number)));
        etBAddressOneDO.setHint(Html.fromHtml(getString(R.string.lbl_address1)));
        etBAddressTwoDO.setHint(Html.fromHtml(getString(R.string.lbl_address2)));
        etBZIpDO.setHint(Html.fromHtml(getString(R.string.lbl_zip)));
        etBCityDo.setHint(Html.fromHtml(getString(R.string.lbl_city)));
        etBStateDO.setHint(Html.fromHtml(getString(R.string.lbl_state)));

        //Radio Button
        rbPickUpAtStore = v.findViewById(R.id.rb_pick_up_at_store_delivery_option_fragment);
        rbPickUpAtStore.setVisibility(View.GONE);
        rbPayAtStore = v.findViewById(R.id.rb_pay_at_store_delivery_option_fragment);
        rbPayAtStore.setVisibility(View.GONE);
        rbPayWithCart = v.findViewById(R.id.rb_pay_with_cart_delivery_option_fragment);
        rbUberRush = v.findViewById(R.id.rb_uber_rush_delivery_option_fragment);
        rbUberRush.setVisibility(View.GONE);
        rbHandOnDelivery = v.findViewById(R.id.rb_hand_on_delivery_delivery_option_fragment);
        rbHandOnDelivery.setVisibility(View.GONE);
        tv_store_personally_deliver = v.findViewById(R.id.tv_store_personally_deliver);
        tv_store_personally_deliver.setVisibility(View.GONE);
        rbShip = v.findViewById(R.id.rb_ship_delivery_option_fragment);
        rbShip.setVisibility(View.GONE);

        //    VARUNN
        cv_radio_button_ship = v.findViewById(R.id.cv_radio_button_ship);
        cv_radio_button_ship.setOnClickListener(this);
        cv_radio_button_hand_delivery = v.findViewById(R.id.cv_radio_button_hand_delivery);
        cv_radio_button_hand_delivery.setOnClickListener(this);
        cv_radio_button_hand_delivery_Check_box = v.findViewById(R.id.cv_radio_button_hand_delivery_Check_box);
        cv_radio_button_ubber = v.findViewById(R.id.cv_radio_button_ubber);
        cv_radio_button_ubber.setOnClickListener(this);
        cv_radio_button_pick_up = v.findViewById(R.id.cv_radio_button_pick_up);
        cv_radio_button_pick_up.setOnClickListener(this);
        cv_radio_button_pick_up_sub_menu = v.findViewById(R.id.cv_radio_button_pick_up_sub_menu);

        //    VARUNN
        //Radio Group
        rgMain = v.findViewById(R.id.rbg_delivery_option_fragment);
        rgMain.setOnCheckedChangeListener(listener);
        rbg_delivery_option_hand = v.findViewById(R.id.rbg_delivery_option_hand);
        rbg_delivery_option_hand.setOnCheckedChangeListener(listener);
        rbg_delivery_option_ubber = v.findViewById(R.id.rbg_delivery_option_ubber);
        rbg_delivery_option_ubber.setOnCheckedChangeListener(listener);
        rbg_delivery_option_ship = v.findViewById(R.id.rbg_delivery_option_ship);
        rbg_delivery_option_ship.setOnCheckedChangeListener(listener);
        rgPickUpAtStore = v.findViewById(R.id.rbg_pick_up_at_store_delivery_option_fragment);
        rgPickUpAtStore.setOnCheckedChangeListener(subListener);


//                Edited by Varun for Ship

        test_site_link = v.findViewById(R.id.test_site_link);
        lin_test_site_link= v.findViewById(R.id.lin_test_site_link);
        cv_ship = v.findViewById(R.id.cv_ship);


//                String vv= getResources().getString(R.string.lbl_ship)+ getResources().getString(R.string.lbl_ship_temporarily)+ liShippingData.get(0).getWebSiteURL();

//                int INT_START =0;
//                int INT_END =4;

//                SpannableStringBuilder str = new SpannableStringBuilder(vv);
//                str.setSpan(new StrikethroughSpan(), INT_START, INT_END, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
//        str.setSpan(new android.text.style.StyleSpan(android.graphics.Typeface.BOLD), INT_START, INT_END, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);

//                if (Constant.STOREID.equalsIgnoreCase("707") || Constant.STOREID.equalsIgnoreCase("7365")){
////                    rbShip.setText(str);
////            rbShip.setPaintFlags(rbShip.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
////            rbShip.setTextColor(Color.GRAY);
////            rbShip.setGravity(Gravity.CENTER);
//                }else{
//                    rbShip.setPaintFlags(rbShip.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
//                    rbShip.setTextColor(Color.GRAY);
//                    rbShip.setGravity(Gravity.CENTER);
////                    rbShip.setText(vv);
//                }

//        END

        //CheckBox


        cbxShip = v.findViewById(R.id.cbx_ship_delivery_option_fragment);
        cbx_hand_delivery = v.findViewById(R.id.cbx_hand_delivery_option_fragment); //Edited by Janvi on 29th Sep * end ***//

        BSTheme.setCheckBoxColor(cbxShip, Color.parseColor(Constant.themeModel.ThemeColor), Color.GRAY);
        BSTheme.setCheckBoxColor(cbx_hand_delivery, Color.parseColor(Constant.themeModel.ThemeColor), Color.GRAY); //Edited by Janvi on 29th Sep * end ***//

        //ColorDrawable colorDrawable = new ColorDrawable(Color.parseColor(Constant.themeModel.ThemeColor));
        //cbxShip.setButtonDrawable(colorDrawable);
        cbxShip.setOnCheckedChangeListener(this);
        cbx_hand_delivery.setOnCheckedChangeListener(this); //Edited by Janvi on 29th Sep * end ***//

        //Spinner
        spinnerMobile = v.findViewById(R.id.spinner_mobile_option_delivery_option_fragment);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getActivity(),
                R.array.number_type, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        spinnerMobile.setAdapter(adapter);

        spinnerBsMobile = v.findViewById(R.id.spinner_bs_mobile_option_delivery_option_fragment);
        ArrayAdapter<CharSequence> adapters = ArrayAdapter.createFromResource(getActivity(),
                R.array.number_type, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerBsMobile.setAdapter(adapters);

        //shipping service
//        spinnerShippingService = v.findViewById(R.id.spinnerShippingService);
//        ArrayAdapter<CharSequence> ShippingOptionadapter = ArrayAdapter.createFromResource(getActivity(),
//                R.array.shipping_service, R.layout.simple_spinner_shipping_service);
////        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//        adapter.setDropDownViewResource(R.layout.simple_spinner_shipping_service);
//        spinnerShippingService.setAdapter(ShippingOptionadapter);

        spinnerShippingService = v.findViewById(R.id.spinnerShippingService);
        tv_shipping_charges = v.findViewById(R.id.tv_shipping_charges);
        img_last_name = v.findViewById(R.id.img_last_name);
        img_last_name.setOnClickListener(this);
        img_first_name = v.findViewById(R.id.img_first_name);
        img_first_name.setOnClickListener(this);
        img_company_name = v.findViewById(R.id.img_company_name);
        img_company_name.setOnClickListener(this);
        img_address_one = v.findViewById(R.id.img_address_one);
        img_address_one.setOnClickListener(this);
        img_address_two = v.findViewById(R.id.img_address_two);
        img_address_two.setOnClickListener(this);
        img_zip = v.findViewById(R.id.img_zip);
        img_zip.setOnClickListener(this);
        img_city = v.findViewById(R.id.img_city);
        img_city.setOnClickListener(this);

        img_dd_last_name = v.findViewById(R.id.img_dd_last_name);
        img_dd_last_name.setOnClickListener(this);
        img_dd_first_name = v.findViewById(R.id.img_dd_first_name);
        img_dd_first_name.setOnClickListener(this);
        img_dd_company_name = v.findViewById(R.id.img_dd_company_name);
        img_dd_company_name.setOnClickListener(this);
        img_dd_phone_no = v.findViewById(R.id.img_dd_phone_no);
        img_dd_phone_no.setOnClickListener(this);
        img_dd_address_one = v.findViewById(R.id.img_dd_address_one);
        img_dd_address_one.setOnClickListener(this);
        img_dd_address_two = v.findViewById(R.id.img_dd_address_two);
        img_dd_address_two.setOnClickListener(this);
        img_dd_city = v.findViewById(R.id.img_dd_city);
        img_dd_city.setOnClickListener(this);
        img_dd_zip = v.findViewById(R.id.img_dd_zip);
        img_dd_zip.setOnClickListener(this);

        //Image
        imgSpinnerIcon = v.findViewById(R.id.img_mobile_option_delivery_option_fragment);
        imgBsSpinnerIcon = v.findViewById(R.id.img_bs_mobile_option_delivery_option_fragment);

        //Button
        //v.findViewById(R.id.btn_next_delivery_option_fragment).setOnClickListener(this);
        btnPrev = v.findViewById(R.id.btn_prev_delivery_option_fragment);
        GradientDrawable bgShape = (GradientDrawable) btnPrev.getBackground();
        bgShape.setColor(Color.parseColor(Constant.themeModel.ThemeColor));
        btnPrev.setOnClickListener(this);

        btnNext = v.findViewById(R.id.btn_next_delivery_option_fragment);
        GradientDrawable bgShapes = (GradientDrawable) btnNext.getBackground();
        bgShapes.setColor(Color.parseColor(Constant.themeModel.ThemeColor));
        btnNext.setOnClickListener(this);

        //TextView
        tvRadioGroupHandDelivery = v.findViewById(R.id.tv_rg_hand_delivery_delivery_option_fragment);
        tvMinimumAmountRadioGroupHandDelivery = v.findViewById(R.id.tv_minimum_amount_rg_hand_delivery_delivery_option_fragment);
        tvStoreClosed = v.findViewById(R.id.tv_store_close_delivery_option_fragment);
        tvStoreClosed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                getFragmentManager().popBackStack();
//                getFragmentManager().popBackStackImmediate();
                if (Constant.SCREEN_LAYOUT == 1) {
//                    MainActivity.showHomePage();
//                    MainActivity.getInstance().LoadWebVew(Constant.URL_PAGE_STORE_HOURS_DELIVERY_HOURS);
                    MainActivity.getInstance().loadStoreandDeliveryFragment("Delivery Hours");
                } else if (Constant.SCREEN_LAYOUT == 2) {
//                    MainActivityDup.showHomePage();
//                    MainActivityDup.getInstance().LoadWebVew(Constant.URL_PAGE_STORE_HOURS_DELIVERY_HOURS);
                    MainActivityDup.getInstance().loadStoreandDeliveryFragment("Delivery Hours");
                }
            }
        });

        if(Constant.SCREEN_LAYOUT==2) {

            v.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
                @Override
                public void onGlobalLayout() {
                    Rect r = new Rect();
                    v.getWindowVisibleDisplayFrame(r);

                    int heightDiff = v.getRootView().getHeight() - (r.bottom - r.top);

                    if (heightDiff > 244) { // if more than 100 pixels, its probably a keyboard...
                        //ok now we know the keyboard is up...
                        MainActivityDup.getInstance().navigationView.setVisibility(View.GONE);

                    } else {
                        //ok now we know the keyboard is down...
                        MainActivityDup.getInstance().navigationView.setVisibility(View.VISIBLE);
                    }

                    if(getActivity()!= null){
                        getActivity().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
                    }
                }
            });
        }
    }

    /**
     * Check focusable EdiText  and call required listener for that EditText
     **/
    @Override
    public void onFocusChange(View view, boolean b) {

//        Delivery Address
        if (view.getId() == etLastNameDO.getId()){
            if (b){
                img_dd_last_name.setVisibility(View.VISIBLE);
            }else{
                img_dd_last_name.setVisibility(View.GONE);
            }
        }

        if (view.getId() == etFirstNameDo.getId()){
            if (b){
                img_dd_first_name.setVisibility(View.VISIBLE);
            }else{
                img_dd_first_name.setVisibility(View.GONE);
            }
        }

        if (view.getId() == etCompanyDO.getId()){
            if (b){
                img_dd_company_name.setVisibility(View.VISIBLE);
            }else{
                img_dd_company_name.setVisibility(View.GONE);
            }
        }

        if (view.getId() == etAddressTwoDO.getId()){
            if (b){
                img_dd_address_two.setVisibility(View.VISIBLE);
            }else{
                img_dd_address_two.setVisibility(View.GONE);
            }
        }

        if (view.getId() == etCityDo.getId()){
            if (b){
                img_dd_city.setVisibility(View.VISIBLE);
            }else{
                img_dd_city.setVisibility(View.GONE);
            }
        }

        if (view.getId() == etPhoneNoDO.getId()){
            if (b){
                img_dd_phone_no.setVisibility(View.VISIBLE);
            }else{
                img_dd_phone_no.setVisibility(View.GONE);
            }
        }


//       Shipping and Billing Address
        if (view.getId() == etBLastNameDO.getId()){
            if (b){
                img_last_name.setVisibility(View.VISIBLE);
            }else{
                img_last_name.setVisibility(View.GONE);
            }
        }

        if (view.getId() == etBFirstNameDo.getId()){
            if (b){
                img_first_name.setVisibility(View.VISIBLE);
            }else{
                img_first_name.setVisibility(View.GONE);
            }
        }

        if (view.getId() == etBCompanyDO.getId()){
            if (b){
                img_company_name.setVisibility(View.VISIBLE);
            }else{
                img_company_name.setVisibility(View.GONE);
            }
        }

        if (view.getId() == etBAddressTwoDO.getId()){
            if (b){
                img_address_two.setVisibility(View.VISIBLE);
            }else{
                img_address_two.setVisibility(View.GONE);
            }
        }

        if (view.getId() == etBCityDo.getId()){
            if (b){
                img_city.setVisibility(View.VISIBLE);
            }else{
                img_city.setVisibility(View.GONE);
            }
        }


        if (view.getId() == etBZIpDO.getId()) {
            if (!b) {
                img_zip.setVisibility(View.GONE);
//                if (Constant.BZip.equals(etBZIpDO.getText().toString()) && Constant.BAddressOne.equals(etBAddressOneDO.getText().toString())) {
//
//                } else {
//                    if (Constant.STOREID.equalsIgnoreCase("707") || Constant.STOREID.equalsIgnoreCase("7365")) {
                if (etBZIpDO.length() == 5) {
////                            Edited by Varun for when hand delivery is selected the Shipping WS will not call
                    if (rbShip.isChecked()){
                        //    VARUNN
                        rbHandOnDelivery.setChecked(false);
                        rbUberRush.setChecked(false);
                        rbPickUpAtStore.setChecked(false);
                        rbg_delivery_option_ubber.clearCheck();
                        rgMain.clearCheck();
                        rbg_delivery_option_hand.clearCheck();

                        callShippingWService();
                        Log.e("callShippingWService", "callShippingWService: 3");
                    }
////                            END
//                            callShippingWService();
//                            Log.e("callShippingWService", "callShippingWService: 3");
                    etBZIpDO.clearFocus();
                    etBAddressOneDO.dismissDropDown();
                    etBAddressOneDO.clearFocus();
                }
//                    }
//                }
            }else{
                img_zip.setVisibility(View.VISIBLE);
            }
        }
        else if (view.getId() == etZIpDO.getId()){
            if (!b) {
                img_dd_zip.setVisibility(View.GONE);
//                if (Constant.Zip.equals(etZIpDO.getText().toString()) && Constant.AddressOne.equals(etAddressOneDO.getText().toString())) {
//
//                } else {
//                    if (Constant.STOREID.equalsIgnoreCase("707") || Constant.STOREID.equalsIgnoreCase("7365")) {
                if (etZIpDO.length() == 5) {
////                            Edited by Varun for when hand delivery is selected the Shipping WS will not call
                    if (rbShip.isChecked()){
                        callShippingWService();
                        Log.e("callShippingWService", "callShippingWService: 3");
                    }
////                            END
//                            callShippingWService();
//                            Log.e("callShippingWService", "callShippingWService: 3");
                    etZIpDO.clearFocus();
                    etAddressOneDO.dismissDropDown();
                    etAddressOneDO.clearFocus();
                }
//                    }
//                }
            }else{
                img_dd_zip.setVisibility(View.VISIBLE);
            }
        }

        if (view.getId() == etZIpDO.getId() && etZIpDO.isFocused()) {

            etZIpDO.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                }

                @Override
                public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                    if (charSequence.length() == 5) {
                        //http://192.168.172.211:889/WebStoreRestService.svc/GetZipCode/11001
                        String Url = Constant.WS_BASE_URL + Constant.GET_ZIP_CODE + charSequence.toString();
//                        new Async_getAddress(getActivity(), Url, 3).execute();
                        new Async_getAddress(getActivity(), Url, 3).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
                    }
                }

                @Override
                public void afterTextChanged(Editable editable) {

//                     Edited By Varun for Shipping Chrges
//                    when user enter the Zipcode and WS ic called for Shipping Charges

//                    if (rbShip.isChecked() && cbxShip.isChecked()){
//                        if (Constant.STOREID.equalsIgnoreCase("707")||Constant.STOREID.equalsIgnoreCase("7365")){
//                            if (editable.length()==5){
//                                callShippingWService();
//                                Log.e("callShippingWService", "callShippingWService: 2" );
////                                onUpdateDeliveryDetail(liShippingData);
//                            }
//                        }
//                    }

                }
            });

        }
        else if (view.getId() == etBZIpDO.getId() && etBZIpDO.isFocused()) {

            etBZIpDO.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                }

                @Override
                public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                    if (charSequence.length() == 5) {
                        //http://192.168.172.211:889/WebStoreRestService.svc/GetZipCode/11001
                        String Url = Constant.WS_BASE_URL + Constant.GET_ZIP_CODE + charSequence.toString();
//                        new Async_getAddress(getActivity(), Url, 4).execute();
                        new Async_getAddress(getActivity(), Url, 4).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
                    }
                }

                @Override
                public void afterTextChanged(Editable editable) {
//                     Edited By Varun for Shipping Chrges
//                    when user enter the Zipcode and WS ic called for Shipping Charges

//                    if (rbShip.isChecked() && etBZIpDO.isFocused()) {
//
//                        if (Constant.BZip.equals(etBZIpDO.getText().toString()) && Constant.BAddressOne.equals(etBAddressOneDO.getText().toString())) {
//
//                        } else {
//                            if (Constant.STOREID.equalsIgnoreCase("707") || Constant.STOREID.equalsIgnoreCase("7365")) {
//                                if (editable.length() == 5) {
//                                        callShippingWService();
//                                        etBZIpDO.clearFocus();
////                                        deliveryOptionsFragment.callShippingRateWS(Constant.Custormer_Id, Constant.additional_charges, Constant.BZip, Constant.Country_code,
////                                                Constant.Selected_ID, Constant.BAddressOne, Constant.BCity, Constant.BState, Constant.Service_name);
//                                        Log.e("callShippingWService", "callShippingWService: 3");
////                                onUpdateBillingDetail();
//                                }
//                            }
//                        }
//                    }

                }
            });

        }
        else if (view.getId() == etPhoneNoDO.getId() && etPhoneNoDO.isFocused()) {

            etPhoneNoDO.addTextChangedListener(new TextWatcher() {
                int length_before = 0;
                String temp;

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {
                }

                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                    length_before = s.length();
                }

                @Override
                public void afterTextChanged(Editable s) {
                    if (length_before < etPhoneNoDO.length()) {
                        if (etPhoneNoDO.length() == 1) {
                            etPhoneNoDO.setText("(" + s);
                            etPhoneNoDO.setSelection(2);
                        } else if (etPhoneNoDO.length() == 5) {
                            String last = etPhoneNoDO.getText().toString();
                            last = last.substring(last.length() - 1);
                            etPhoneNoDO.setText(temp + ") " + last);
                            etPhoneNoDO.setSelection(7);
                        } else if (etPhoneNoDO.length() == 10) {
                            String last = etPhoneNoDO.getText().toString();
                            last = last.substring(last.length() - 1);
                            etPhoneNoDO.setText(temp + "-" + last);
                            if (etPhoneNoDO.getText().length() > 10)
                                etPhoneNoDO.setSelection(11);
                        } else {
                            temp = etPhoneNoDO.getText().toString();
                            etPhoneNoDO.setText(s);
                            etPhoneNoDO.setSelection(etPhoneNoDO.length());
                        }
                    }
                }
            });

        }
//        else if (view.getId() == etBsPhoneNoDO.getId() && etBsPhoneNoDO.isFocused()) {
//
//            etBsPhoneNoDO.addTextChangedListener(new TextWatcher() {
//                int length_before = 0;
//                String temp;
//
//                @Override
//                public void onTextChanged(CharSequence s, int start, int before, int count) {
//                }
//
//                @Override
//                public void beforeTextChanged(CharSequence s, int start, int count, int after) {
//                    length_before = s.length();
//                }
//
//                @Override
//                public void afterTextChanged(Editable s) {
//                    if (length_before < etBsPhoneNoDO.length()) {
//                        if (etBsPhoneNoDO.length() == 1) {
//                            etBsPhoneNoDO.setText("(" + s);
//                            etBsPhoneNoDO.setSelection(2);
//                        } else if (etBsPhoneNoDO.length() == 5) {
//                            String last = etBsPhoneNoDO.getText().toString();
//                            last = last.substring(last.length() - 1);
//                            etBsPhoneNoDO.setText(temp + ") " + last);
//                            etBsPhoneNoDO.setSelection(7);
//                        } else if (etBsPhoneNoDO.length() == 10) {
//                            String last = etBsPhoneNoDO.getText().toString();
//                            last = last.substring(last.length() - 1);
//                            etBsPhoneNoDO.setText(temp + "-" + last);
//                            if (etBsPhoneNoDO.getText().length() > 10)
//                                etBsPhoneNoDO.setSelection(11);
//                        } else {
//                            temp = etBsPhoneNoDO.getText().toString();
//                            etBsPhoneNoDO.setText(s);
//                            etBsPhoneNoDO.setSelection(etBsPhoneNoDO.length());
//                        }
//                    }
//                }
//            });
//
//        }
        else if (view.getId() == etAddressOneDO.getId() && etAddressOneDO.isFocused()) {
            status = 1;
            img_dd_address_one.setVisibility(View.VISIBLE);
            etAddressOneDO.addTextChangedListener(myWatcher /*new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                    // new Async_getCommonService(MainActivity.this, Constant.MAP_API_URL+charSequence+"&key="+getString(R.string.Place_API_key)).execute();
                }

                @Override
                public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                }

                @Override
                public void afterTextChanged(Editable editable) {
                    String Name = "" + editable;
                    Name = Name.replaceAll(" ", "%20");
                    if (etAddressOneDO.isFocused()){
                        isdhowdropdown = true;
                    }
                    if (isdhowdropdown) {
                        new Async_getAddress(MainActivity.getInstance(), Constant.MAP_API_URL + Constant.INPUT + Name + "&key=" + MainActivity.getInstance().getString(R.string.Place_API_key), 1).execute();
                    }// else {
                    //    isdhowdropdown = true;
                    //}
                }
            }*/);

        }
        else if (view.getId() == etBAddressOneDO.getId() && etBAddressOneDO.isFocused()) {
            status = 2;
            img_address_one.setVisibility(View.VISIBLE);
            etBAddressOneDO.addTextChangedListener(myWatcher /*new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                    // new Async_getCommonService(MainActivity.this, Constant.MAP_API_URL+charSequence+"&key="+getString(R.string.Place_API_key)).execute();
                }

                @Override
                public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                }

                @Override
                public void afterTextChanged(Editable editable) {
                    String Name = "" + editable;
                    Name = Name.replaceAll(" ", "%20");

                    if (etBsAddressOneDO.isFocused()){
                        isdhowdropdown = true;
                    }

                    if (isdhowdropdown) {
                        Log.e(TAG, "request Call " + Name);
                        new Async_getAddress(MainActivity.getInstance(), Constant.MAP_API_URL + Constant.INPUT + Name + "&key=" + MainActivity.getInstance().getString(R.string.Place_API_key), 2).execute();
                    } //else {
                    //    isdhowdropdown = true;
                   // }
                }
            }*/);

        }
        else if (view.getId() == etBAddressOneDO.getId()){
            if (!b){
                etBAddressOneDO.dismissDropDown();
                etBAddressOneDO.clearFocus();
                img_address_one.setVisibility(View.GONE);
            }
        }
        else if (view.getId() ==  etAddressOneDO.getId()){
            if (!b){
                etAddressOneDO.dismissDropDown();
                etAddressOneDO.clearFocus();
                img_dd_address_one.setVisibility(View.GONE);
            }
        }
    }

    private static int status;
    public static TextWatcher myWatcher = new TextWatcher() {

        @Override
        public void afterTextChanged(Editable s) {
            // your logic here
        }

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            // your logic here
        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            String Name = "" + s;
            Name = Name.replaceAll(" ", "%20");

            if (etBAddressOneDO.isFocused() || etAddressOneDO.isFocused()) {
                isdhowdropdown = true;
            }

            if (isdhowdropdown) {
                Log.e(TAG, "request Call " + Name);
                if (Constant.SCREEN_LAYOUT==1) {
                    new Async_getAddress(MainActivity.getInstance(), Constant.MAP_API_URL + Constant.INPUT + Name + "&key=" + MainActivity.getInstance().getString(R.string.Place_API_key), status).execute();
                }else {
                    new Async_getAddress(MainActivityDup.getInstance(), Constant.MAP_API_URL + Constant.INPUT + Name + "&key=" + MainActivityDup.getInstance().getString(R.string.Place_API_key), status).execute();
                }
            }
        }
    };


    /**
     * Fill data when address changed
     **/
    public static void onFillAddress(JSONArray address, int position) throws JSONException {

        Log.e(TAG, "onFillAddress: " + address);
        if (position == 1) {

            etAddressOneDO.removeTextChangedListener(myWatcher);
            etAddressOneDO.setText("");
            etAddressTwoDO.setText("");
            etZIpDO.setText("");
            etStateDO.setText("");
            etCityDo.setText("");
            String address11 = "";
            for (int i = 0; i < address.length(); i++) {
                JSONObject object = address.getJSONObject(i);

                etAddressOneDO.clearFocus();
                etAddressOneDO.dismissDropDown();
                etAddressTwoDO.requestFocus();

                if (object.getString("types").contains("street_number")) {
                    address11 += object.getString("long_name");
                    etAddressOneDO.setText(address11);
                }

                if (object.getString("types").contains("route")) {
                    address11 += " " + object.getString("long_name");
                    etAddressOneDO.setText(address11);
                }
                if (object.getString("types").contains("postal_code")) {
                    etZIpDO.setText(object.getString("long_name"));
                }
                if (object.getString("types").contains("administrative_area_level_1")) {
                    etStateDO.setText(object.getString("short_name"));
                }
                if (object.getString("types").contains("locality"/*"*//*administrative_area_level_2*/)) {
                    etCityDo.setText(object.getString("short_name"));
                }
            }
            etAddressOneDO.dismissDropDown();
        }

        if (position == 2) {
            etBAddressOneDO.removeTextChangedListener(myWatcher);
            etBAddressOneDO.setText("");
            etBAddressTwoDO.setText("");
            etBZIpDO.setText("");
            etBStateDO.setText("");
            etBCityDo.setText("");
            String address11 = "";
            for (int i = 0; i < address.length(); i++) {
                JSONObject object = address.getJSONObject(i);

                etBAddressOneDO.clearFocus();
                etBAddressOneDO.dismissDropDown();
                etBAddressTwoDO.requestFocus();

                if (object.getString("types").contains("street_number")) {
                    address11 += object.getString("long_name");
                    etBAddressOneDO.setText(address11);
                }
                if (object.getString("types").contains("route")) {
                    address11 += " " + object.getString("long_name");
                    etBAddressOneDO.setText(address11);
                }
                if (object.getString("types").contains("postal_code")) {
                    etBZIpDO.setText(object.getString("long_name"));
                }
                if (object.getString("types").contains("administrative_area_level_1")) {
                    etBStateDO.setText(object.getString("short_name"));
                }
                if (object.getString("types").contains("locality"/*"*//*administrative_area_level_2*/)) {
                    etBCityDo.setText(object.getString("short_name"));
                }

            }
//            Edied by Varun for when the User Select the Address one and then its call WS
//            if (etBZIpDO.getText().length()==5){
//                deliveryOptionsFragment.callShippingWService();
//                Log.e("callShippingWService", "callShippingWService: 4" );
//            }
//            END
            etBAddressOneDO.dismissDropDown();
        }
    }

    public void onCheckListVisibility() {

    }

    /**
     * Call Shipping Address data Web service
     **/
    public void onCallShippingDataTask() {
        if (UserModel.Cust_mst_ID != null) {
            String url = Constant.WS_BASE_URL + Constant.GET_SHIPPING_DATA + UserModel.Cust_mst_ID + "/" + Constant.STOREID;
            TaskShippingData taskShippingData = new TaskShippingData(this, false, getActivity());
            taskShippingData.execute(url);
//            taskShippingData.executeOnExecutor(TaskShippingData.THREAD_POOL_EXECUTOR,url);
        }
    }


    /**
     * Hide / Show Radio Button
     **/
    @Override
    public void onShippingTaskResult(List<ShippingData> liShippingData) {
        this.liShippingData = liShippingData;

        if (liShippingData.size() > 0) {
//new added -------- bypass delivery oprtion screen - direct open payment screen
            if(liShippingData.get(0).getBSSetupPickUpStore()
                    && !liShippingData.get(0).getBSSetupPayAtStore()
                    && !liShippingData.get(0).getBSSetupHandDelivery()
                    && !liShippingData.get(0).getBSSetupUberRush()
//                    Edited by Varun for if the set-up has only pick-up store and ship true the it will redirect to pay,emt screen as per our logic
//                    but now it is resolved and it is same as E-Com.
                    && !liShippingData.get(0).getBSSetupDeliveryOption()) {
//                END

                warnTwoHourAgo(liDeliveryHour);

                isDeliveryPage = false;
                Constant.isbackFromPayment = true;

                //Pickup time popup
//                isBypassDeliveryOption = true;
//                onCallGlobalSetup();

                if (myDeliveryOptionsEvent != null)
                    myDeliveryOptionsEvent.nextFromDeliveryOption(addDataIntoBundle());

            }else{
                isDeliveryPage = true;
            }

// end ************
            if(isDeliveryPage) {
                llMain.setVisibility(View.VISIBLE);
                cvRadioButton.setVisibility(View.VISIBLE);
                cvRadioButton.setVisibility(View.VISIBLE);
                if (liShippingData.get(0).getBSSetupPickUpStore())
                    //    VARUNN
                    cv_radio_button_pick_up.setVisibility(View.VISIBLE);
                rbPickUpAtStore.setVisibility(View.VISIBLE);
                if (liShippingData.get(0).getBSSetupPayAtStore())
                    rbPayAtStore.setVisibility(View.VISIBLE);
                //uncomment below code and comment above two line to changes give by Tom.
//                    if (liShippingData.get(0).getBSSetupPayAtStore()) {
//                        rbPayAtStore.setVisibility(View.VISIBLE);
//                        rbPayAtStore.setChecked(true);
//                    } else {
//                        rbPayWithCart.setChecked(true);
//                    }

//                end above code
                if (liShippingData.get(0).getBSSetupUberRush())
                    //    VARUNN
                    cv_radio_button_ubber.setVisibility(View.VISIBLE);
                rbUberRush.setVisibility(View.VISIBLE);
                if (liShippingData.get(0).getBSSetupHandDelivery()) {
//                    boolean isStoreClosed = false;
                    isStoreClosed = false;
                    //    VARUNN
                    cv_radio_button_hand_delivery.setVisibility(View.VISIBLE);
                    rbHandOnDelivery.setVisibility(View.VISIBLE);
                    tv_store_personally_deliver.setVisibility(View.VISIBLE);
                    _minimumHandDeliveryLimit = Float.parseFloat(liShippingData.get(0).getHandDeliveryPrice());

                    if (liShippingData.get(0).getDontAcceptOrder()) {
                        if (Constant.liStoreHour != null) {
                            @SuppressLint("SimpleDateFormat") SimpleDateFormat sdf = new SimpleDateFormat("EEEE");
                            Date d = new Date();
                            String day = sdf.format(d);
                            for (int i = 0; i < Constant.liStoreHour.size(); i++) {
                                if (day.equals(Constant.liStoreHour.get(i).getStoreDay())) {
                                    position = i;
                                    if (Constant.liStoreHour.get(i).getClosed()) {
                                        isStoreClosed = true;
                                    }
                                }
                            }

                            String openTime = Constant.liStoreHour.get(position).getOpenTime();
                            String closeTime = Constant.liStoreHour.get(position).getCloseTime();
                            if (Constant.SCREEN_LAYOUT == 1) {
                                if (isStoreClosed) {
//
//                                    tvStoreClosed.setText(Html.fromHtml(MainActivity.getInstance().getString(R.string.store_close)));

                                    String str = MainActivity.getInstance().getString(R.string.delivery_hours);
                                    String storeclose = MainActivity.getInstance().getString(R.string.store_close);

                                    SpannableStringBuilder builder = new SpannableStringBuilder();

                                    SpannableString blackSpannable= new SpannableString(storeclose);
                                    blackSpannable.setSpan(new ForegroundColorSpan(Color.BLACK), 0, storeclose.length(), 0);
                                    builder.append(blackSpannable);

                                    SpannableString themeColorSpannable= new SpannableString(str);
                                    themeColorSpannable.setSpan(new ForegroundColorSpan(Color.parseColor(Constant.themeModel.ThemeColor)), 0, str.length(), 0);
                                    themeColorSpannable.setSpan(new UnderlineSpan(), 0, str.length(), 0);
                                    builder.append(themeColorSpannable);

                                    tvStoreClosed.setText(builder, TextView.BufferType.SPANNABLE);

                                    tvStoreClosed.setVisibility(View.VISIBLE);
                                    rbHandOnDelivery.setClickable(false);
                                    rbHandOnDelivery.setEnabled(false);

                                } else if (!Utils.storeTime(openTime, closeTime)) {
//                                    tvStoreClosed.setText(Html.fromHtml(MainActivity.getInstance().getString(R.string.store_close)));

                                    String str = MainActivity.getInstance().getString(R.string.delivery_hours);
                                    String storeclose = MainActivity.getInstance().getString(R.string.store_close);

                                    SpannableStringBuilder builder = new SpannableStringBuilder();

                                    SpannableString blackSpannable= new SpannableString(storeclose);
                                    blackSpannable.setSpan(new ForegroundColorSpan(Color.BLACK), 0, storeclose.length(), 0);
                                    builder.append(blackSpannable);

                                    SpannableString themeColorSpannable= new SpannableString(str);
                                    themeColorSpannable.setSpan(new ForegroundColorSpan(Color.parseColor(Constant.themeModel.ThemeColor)), 0, str.length(), 0);
                                    themeColorSpannable.setSpan(new UnderlineSpan(), 0, str.length(), 0);
                                    builder.append(themeColorSpannable);

                                    tvStoreClosed.setText(builder, TextView.BufferType.SPANNABLE);

                                    tvStoreClosed.setVisibility(View.VISIBLE);
                                    rbHandOnDelivery.setClickable(false);
                                    rbHandOnDelivery.setEnabled(false);
                                }
                            } else if (Constant.SCREEN_LAYOUT == 2) {
                                if (isStoreClosed) {
//                                    tvStoreClosed.setText(Html.fromHtml(MainActivityDup.getInstance().getString(R.string.store_close)));

                                    String str = MainActivityDup.getInstance().getString(R.string.delivery_hours);
                                    String storeclose = MainActivityDup.getInstance().getString(R.string.store_close);

                                    SpannableStringBuilder builder = new SpannableStringBuilder();

                                    SpannableString blackSpannable= new SpannableString(storeclose);
                                    blackSpannable.setSpan(new ForegroundColorSpan(Color.BLACK), 0, storeclose.length(), 0);
                                    builder.append(blackSpannable);

                                    SpannableString themeColorSpannable= new SpannableString(str);
                                    themeColorSpannable.setSpan(new ForegroundColorSpan(Color.parseColor(Constant.themeModel.ThemeColor)), 0, str.length(), 0);
                                    themeColorSpannable.setSpan(new UnderlineSpan(), 0, str.length(), 0);
                                    builder.append(themeColorSpannable);

                                    tvStoreClosed.setText(builder, TextView.BufferType.SPANNABLE);

                                    tvStoreClosed.setVisibility(View.VISIBLE);
                                    rbHandOnDelivery.setClickable(false);
                                    rbHandOnDelivery.setEnabled(false);
                                } else if (!Utils.storeTime(openTime, closeTime)) {
//                                    tvStoreClosed.setText(Html.fromHtml(MainActivityDup.getInstance().getString(R.string.store_close)));


                                    String str = MainActivityDup.getInstance().getString(R.string.delivery_hours);
                                    String storeclose = MainActivityDup.getInstance().getString(R.string.store_close);

                                    SpannableStringBuilder builder = new SpannableStringBuilder();

                                    SpannableString blackSpannable= new SpannableString(storeclose);
                                    blackSpannable.setSpan(new ForegroundColorSpan(Color.BLACK), 0, storeclose.length(), 0);
                                    builder.append(blackSpannable);

                                    SpannableString themeColorSpannable= new SpannableString(str);
                                    themeColorSpannable.setSpan(new ForegroundColorSpan(Color.parseColor(Constant.themeModel.ThemeColor)), 0, str.length(), 0);
                                    themeColorSpannable.setSpan(new UnderlineSpan(), 0, str.length(), 0);
                                    builder.append(themeColorSpannable);

                                    tvStoreClosed.setText(builder, TextView.BufferType.SPANNABLE);
                                    tvStoreClosed.setVisibility(View.VISIBLE);
                                    rbHandOnDelivery.setClickable(false);
                                    rbHandOnDelivery.setEnabled(false);
                                }
                            }

                        }
                    } else if (liShippingData.get(0).getWarnCustomers()) {

                        if (Constant.liStoreHour != null) {
                            @SuppressLint("SimpleDateFormat") SimpleDateFormat sdf = new SimpleDateFormat("EEEE");
                            Date d = new Date();
                            String day = sdf.format(d);
//                            boolean isStoreClosed = false;
                            for (int i = 0; i < Constant.liStoreHour.size(); i++) {
                                if (day.equals(Constant.liStoreHour.get(i).getStoreDay())) {
                                    position = i;
                                    if (Constant.liStoreHour.get(i).getClosed()) {
                                        isStoreClosed = true;
                                    }
                                }
                            }

                            String openTime = Constant.liStoreHour.get(position).getOpenTime();
                            String closeTime = Constant.liStoreHour.get(position).getCloseTime();
                            if (Constant.SCREEN_LAYOUT == 1) {

//                            if (isStoreClosed) {
//                                tvStoreClosed.setTextColor(Color.RED);
//                                tvStoreClosed.setText(Html.fromHtml(MainActivity.getInstance().getString(R.string.store_resume)) + Utils.getCurrentDay() + " @ " + openTime);
//                                tvStoreClosed.setVisibility(View.VISIBLE);
//                                rbHandOnDelivery.setClickable(false);
//                            } else if (!Utils.dateBefore(openTime)) {
//                                tvStoreClosed.setTextColor(Color.RED);
//                                tvStoreClosed.setText(Html.fromHtml(MainActivity.getInstance().getString(R.string.store_resume)) + Utils.getCurrentDay() + " @ " + openTime);
//                                tvStoreClosed.setVisibility(View.VISIBLE);
//                                rbHandOnDelivery.setClickable(false);
//                            }

                                if (!isStoreClosed && !Utils.dateBefore(openTime)) {

                                    String alert = Html.fromHtml(MainActivity.getInstance().getString(R.string.store_resume)) + Utils.getCurrentDay() + " @ " + openTime + ".";
                                    tvStoreClosed.setText(alert.toUpperCase());
                                    tvStoreClosed.setTextColor(Color.BLUE);
                                    tvStoreClosed.setVisibility(View.VISIBLE);
//                                    rbHandOnDelivery.setClickable(false);
//                                    rbHandOnDelivery.setEnabled(false);
                                } else if (isStoreClosed || !Utils.dateAfter(closeTime)) {
                                    int j = 1;
                                    String tomorrowDay = Utils.getNextDay(j);
                                    String nextDayOpenTime = "";

                                    for (int i = 0; i < Constant.liStoreHour.size(); i++) {
                                        if (tomorrowDay.equals(Constant.liStoreHour.get(i).getStoreDay())) {
                                            if (Constant.liStoreHour.get(i).getClosed()) {
                                                j++;
                                                tomorrowDay = Utils.getNextDay(j);
                                            } else {
                                                nextDayOpenTime = Constant.liStoreHour.get(i).getOpenTime();
                                            }
                                        }
                                    }
//                                    Edited by Varun for when the Store is closed for 2 days the reopen store timing was not coming
                                    if (nextDayOpenTime.equals("") || nextDayOpenTime.isEmpty() || nextDayOpenTime==null) {
                                        for (int i = 0; i < Constant.liStoreHour.size(); i++) {
                                            if (tomorrowDay.equals(Constant.liStoreHour.get(i).getStoreDay())){
                                                nextDayOpenTime = Constant.liStoreHour.get(i).getOpenTime();
                                            }
                                        }
                                    }
//                                    ?END

//                                    tvStoreClosed.setTextColor(Color.RED);
//                                    tvStoreClosed.setText(Html.fromHtml(MainActivity.getInstance().getString(R.string.store_resume)) + tomorrowDay + " @ " + nextDayOpenTime);
                                    String alert = Html.fromHtml(MainActivity.getInstance().getString(R.string.store_resume)) + tomorrowDay + " @ " + nextDayOpenTime + ".";
                                    tvStoreClosed.setText(alert.toUpperCase());
                                    tvStoreClosed.setTextColor(Color.BLUE);
                                    tvStoreClosed.setVisibility(View.VISIBLE);
//                                    rbHandOnDelivery.setClickable(false);
//                                    rbHandOnDelivery.setEnabled(false);
                                }
                            } else if (Constant.SCREEN_LAYOUT == 2) {
//                            if (isStoreClosed) {
//                                tvStoreClosed.setTextColor(Color.RED);
//                                tvStoreClosed.setText(Html.fromHtml(MainActivityDup.getInstance().getString(R.string.store_resume)) + Utils.getCurrentDay() + " @ " + openTime);
//                                tvStoreClosed.setVisibility(View.VISIBLE);
//                                rbHandOnDelivery.setClickable(false);
//                            } else
                                if (!isStoreClosed && !Utils.dateBefore(openTime)) {
//                                    tvStoreClosed.setTextColor(Color.RED);
//                                    tvStoreClosed.setText(Html.fromHtml(MainActivityDup.getInstance().getString(R.string.store_resume)) + Utils.getCurrentDay() + " @ " + openTime);
                                    String alert = Html.fromHtml(MainActivityDup.getInstance().getString(R.string.store_resume)) + Utils.getCurrentDay() + " @ " + openTime + ".";
                                    tvStoreClosed.setText(alert.toUpperCase());
                                    tvStoreClosed.setTextColor(Color.BLUE);
                                    tvStoreClosed.setVisibility(View.VISIBLE);
//                                    rbHandOnDelivery.setClickable(false);
//                                    rbHandOnDelivery.setEnabled(false);
                                } else if (isStoreClosed || !Utils.dateAfter(closeTime)) {
                                    int j = 1;
                                    String tomorrowDay = Utils.getNextDay(j);
                                    String nextDayOpenTime = "";

                                    for (int i = 0; i < Constant.liStoreHour.size(); i++) {
                                        if (tomorrowDay.equals(Constant.liStoreHour.get(i).getStoreDay())) {
                                            if (Constant.liStoreHour.get(i).getClosed()) {
                                                j++;
                                                tomorrowDay = Utils.getNextDay(j);
                                            } else {
                                                nextDayOpenTime = Constant.liStoreHour.get(i).getOpenTime();
                                            }
                                        }
                                    }

//                                    tvStoreClosed.setTextColor(Color.RED);
//                                    tvStoreClosed.setText(Html.fromHtml(MainActivityDup.getInstance().getString(R.string.store_resume)) + tomorrowDay + " @ " + nextDayOpenTime);
                                    String alert = Html.fromHtml(MainActivityDup.getInstance().getString(R.string.store_resume)) + tomorrowDay + " @ " + nextDayOpenTime + ".";
                                    tvStoreClosed.setText(alert.toUpperCase());
                                    tvStoreClosed.setTextColor(Color.BLUE);
                                    tvStoreClosed.setVisibility(View.VISIBLE);
//                                    rbHandOnDelivery.setClickable(false);
//                                    rbHandOnDelivery.setEnabled(false);
                                }
                            }

                        }
                    }

                    //old place of below fun
                    warnTwoHourAgo(liDeliveryHour);

                    // Minimum Delivery Amount


                    if (_minimumHandDeliveryLimit > _total && !isStoreClosed) {

                        if(tvMinimumAmountRadioGroupHandDelivery.getVisibility() == View.GONE){
                            tvMinimumAmountRadioGroupHandDelivery.setVisibility(View.VISIBLE);
                        }
                        tvMinimumAmountRadioGroupHandDelivery.setText("Delivery Minimum of $" + liShippingData.get(0).getHandDeliveryPrice() + " not met.");
                        tvMinimumAmountRadioGroupHandDelivery.setTextColor(Color.RED);

                    }else if(_minimumHandDeliveryLimit > _total && isStoreClosed && !liShippingData.get(0).getDontAcceptOrder()) {

                        if(tvMinimumAmountRadioGroupHandDelivery.getVisibility() == View.GONE){
                            tvMinimumAmountRadioGroupHandDelivery.setVisibility(View.VISIBLE);
                        }
                        tvMinimumAmountRadioGroupHandDelivery.setText("Delivery Minimum of $" + liShippingData.get(0).getHandDeliveryPrice() + " not met.");
                        tvMinimumAmountRadioGroupHandDelivery.setTextColor(Color.RED);

                    } else if(_total > _minimumHandDeliveryLimit && isStoreClosed && !liShippingData.get(0).getDontAcceptOrder()){

                        if(tvMinimumAmountRadioGroupHandDelivery.getVisibility() == View.GONE){
                            tvMinimumAmountRadioGroupHandDelivery.setVisibility(View.VISIBLE);
                        }
                        tvMinimumAmountRadioGroupHandDelivery.setText(liShippingData.get(0).getHandDeliveryAreaText());
                        tvMinimumAmountRadioGroupHandDelivery.setTextColor(Color.RED);
                    }else{

//                        if(tvRadioGroupHandDelivery.getVisibility() == View.GONE){
//                            tvRadioGroupHandDelivery.setVisibility(View.VISIBLE);
//                        }

//                        below variable no need to viible here, because it visible only on clicked on
//                        hand delivery checked so, it's right.
                        if(isAdded()){
                            tvRadioGroupHandDelivery.setText(liShippingData.get(0).getHandDeliveryAreaText());
                            tvRadioGroupHandDelivery.setTextColor(getResources().getColor(R.color.tab_indicator_text));
                        }
                    }
                }
                if (liShippingData.get(0).getBSSetupDeliveryOption()) {
                    //    VARUNN
                    cv_radio_button_ship.setVisibility(View.VISIBLE);
                    rbShip.setVisibility(View.VISIBLE);
                    lin_test_site_link.setVisibility(View.GONE);

//////                Edited by Varun for Ship
//
//                    String vv = "Ship Temporarily only available on our site:";
////                rgShip = v.findViewById(R.id.rbg_ship_delivery_option_fragment);
////                rgShip.setOnCheckedChangeListener(this);
//
//                    int INT_START = 0;
//                    int INT_END = 4;
//
//                    SpannableStringBuilder str = new SpannableStringBuilder(vv);
//                    str.setSpan(new StrikethroughSpan(), INT_START, INT_END, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
////        str.setSpan(new android.text.style.StyleSpan(android.graphics.Typeface.BOLD), 0, 4, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
//
//                    if (Constant.STOREID.equalsIgnoreCase("707") || Constant.STOREID.equalsIgnoreCase("7365")) {
//
//                        lin_test_site_link.setVisibility(View.GONE);
//
//                    } else {
//                        rbShip.setText(str);
//                        rbShip.setEnabled(false);
//
//                        lin_test_site_link.setVisibility(View.VISIBLE);
//                        test_site_link.setPaintFlags(test_site_link.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);
//                        if (liShippingData.get(0).getWebSiteURL() != null && !liShippingData.get(0).getWebSiteURL().isEmpty()) {
//                            test_site_link.setText(liShippingData.get(0).getWebSiteURL());
//                            test_site_link.setVisibility(View.VISIBLE);
//                            Uri uri = Uri.parse(liShippingData.get(0).getLoginToCartURL()); //// missing 'http://' will cause crashed
//
//                            Log.e(TAG, "onShippingTaskResult: " + uri.toString());
//
//                            test_site_link.setOnClickListener(new View.OnClickListener() {
//                                @Override
//                                public void onClick(View v) {
//
//                                    Intent intent = new Intent(Intent.ACTION_VIEW);
//                                    intent.addCategory(Intent.CATEGORY_BROWSABLE);
//                                    intent.setData(uri);
//                                    startActivity(intent);
//                                }
//                            });
//                        } else {
//                            test_site_link.setVisibility(View.GONE);
//                        }
//                    }
//                }else{
//                    lin_test_site_link.setVisibility(View.GONE);
                }

////           Edited by Varun for the if the only pick-up at store is true then the screen will direct show the pick-up at store is selected and
////                then the pay at store is selected directly
                if (!liShippingData.get(0).getBSSetupHandDelivery() && !liShippingData.get(0).getBSSetupDeliveryOption()
                        &&!liShippingData.get(0).getBSSetupUberRush() && !liShippingData.get(0).getAllowSurchargeDelivery()){

                    if (liShippingData.get(0).getBSSetupPickUpStore() && liShippingData.get(0).getBSSetupPayAtStore()){

                        //    VARUNN
                        cv_radio_button_pick_up.setVisibility(View.VISIBLE);
                        rbPickUpAtStore.setVisibility(View.VISIBLE);
                        rbPayAtStore.setVisibility(View.VISIBLE);
                        rbPickUpAtStore.setChecked(true);
                        rbPayAtStore.setChecked(true);
                        rbShip.setChecked(false);
                        rbHandOnDelivery.setChecked(false);
                        rbUberRush.setChecked(false);
                        cv_ship.setVisibility(View.GONE);

                    }

                }

////        END

                onUpdateDeliveryDetail(liShippingData);
            }

        }
    }

    ///code by viraj patel(18/11/2024)

    public static void warnTwoHourAgo(List<StoreHour> liDeliveryHour) {
  /*      *//** Warn user Before 2 Hour closing the store **//*
        int position = 0;
        @SuppressLint("SimpleDateFormat") SimpleDateFormat sdf = new SimpleDateFormat("EEEE", Locale.US);
        Date d = new Date();
        String day = sdf.format(d);
        boolean isStoreClosed = false;
        if (liDeliveryHour != null && !liDeliveryHour.isEmpty()) {
            for (int i = 0; i < liDeliveryHour.size(); i++) {
                if (day.equals(liDeliveryHour.get(i).getStoreDay())) {
                    position = i;
                    if (liDeliveryHour.get(i).getClosed()) {
                        isStoreClosed = true;
                    }
                }
            }
        } else {
            // Handle the case where liDeliveryHour is null or empty
            Log.e("DeliveryOptionsFragment", "liDeliveryHour is null or empty");
        }


        assert liDeliveryHour != null;
        String openTime = liDeliveryHour.get(position).getOpenTime();
        String closeTime = liDeliveryHour.get(position).getCloseTime();
        String pattern = "hh:mm a";
        @SuppressLint("SimpleDateFormat")
        SimpleDateFormat sdfs = new SimpleDateFormat(pattern, Locale.US);
        //Edited by Janvi 25th Oct ****
        //est conversion
//        SimpleDateFormat sdfEst = new SimpleDateFormat(pattern, Locale.US);
//        TimeZone tz1 = TimeZone.getTimeZone("America/New_York");
//        sdfEst.setTimeZone(tz1);
//        String currentDates = sdfEst.format(Calendar.getInstance().getTime().getTime());

        //Current timezone
        Calendar c = Calendar.getInstance();
        TimeZone tz = c.getTimeZone();
        sdfs.setTimeZone(tz);
        String currentDates = sdfs.format(Calendar.getInstance().getTime().getTime());

        //end *****
        Date openDate = null, closeDate = null, currentDate = null;
        try {
            openDate = sdfs.parse(openTime);
            closeDate = sdfs.parse(closeTime);
            currentDate = sdfs.parse(currentDates);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        if (!isStoreClosed && Utils.storeTime(openTime, closeTime)) {
            long mills = 0;
            if (closeDate != null && currentDate != null) {
                mills = closeDate.getTime() - currentDate.getTime();
            }
            int hours = (int) (mills / (1000 * 60 * 60));
            int minute = (int) (mills / (1000 * 60)) % 60;

            String diff = hours + " : " + minute;
            if (hours == 2 && minute == 0) {
                tvWarnTwoHourAgo.setText("Note: Store closes @ " + closeTime);
            } else if (hours == 1) {
                tvWarnTwoHourAgo.setText("Note: Store closes @ " + closeTime);
            } else if (hours == 0 && minute <= 60 && minute >= 0) {
                tvWarnTwoHourAgo.setText("Note: Store closes @ " + closeTime);
            } else {
                tvWarnTwoHourAgo.setText("");
            }
        }else if(isStoreClosed){
            tvWarnTwoHourAgo.setText("Note: Store is closed today");
        }*/

        // Check if the list is null or empty

        if (liDeliveryHour == null || liDeliveryHour.isEmpty()) {
            Log.e(TAG, "Delivery hours list is empty or null.oncreae");
            tvWarnTwoHourAgo.setText(""); // Clear the warning text if no delivery hours are available
            return; // Exit the method early
        }

/** Warn user Before 2 Hour closing the store **/
        int position = 0;
        @SuppressLint("SimpleDateFormat") SimpleDateFormat sdf = new SimpleDateFormat("EEEE", Locale.US);
        Date d = new Date();
        String day = sdf.format(d);
        boolean isStoreClosed = false;

        for (int i = 0; i < liDeliveryHour.size(); i++) {
            if (day.equals(liDeliveryHour.get(i).getStoreDay())) {
                position = i;
                if (liDeliveryHour.get(i).getClosed()) {
                    isStoreClosed = true;
                }
                break; // Exit the loop once the current day is found
            }
        }

// Ensure the position is valid
        if (position >= liDeliveryHour.size()) {
            Log.e(TAG, "Position is out of bounds: " + position);
            return; // Exit the method early
        }

        String openTime = liDeliveryHour.get(position).getOpenTime();
        String closeTime = liDeliveryHour.get(position).getCloseTime();
        String pattern = "hh:mm a";
        @SuppressLint("SimpleDateFormat")
        SimpleDateFormat sdfs = new SimpleDateFormat(pattern, Locale.US);
        Calendar c = Calendar.getInstance();
        TimeZone tz = c.getTimeZone();
        sdfs.setTimeZone(tz);
        String currentDates = sdfs.format(Calendar.getInstance().getTime().getTime());

        Date openDate = null, closeDate = null, currentDate = null;
        try {
            openDate = sdfs.parse(openTime);
            closeDate = sdfs.parse(closeTime);
            currentDate = sdfs.parse(currentDates);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        if (!isStoreClosed && Utils.storeTime(openTime, closeTime)) {
            long mills = 0;
            if (closeDate != null && currentDate != null) {
                mills = closeDate.getTime() - currentDate.getTime();
            }
            int hours = (int) (mills / (1000 * 60 * 60));
            int minute = (int) (mills / (1000 * 60)) % 60;

            String diff = hours + " : " + minute;
            if (hours == 2 && minute == 0) {
                tvWarnTwoHourAgo.setText("Note: Store closes @" + closeTime);
            } else if (hours == 1) {
                tvWarnTwoHourAgo.setText("Note: Store closes @" + closeTime);
            } else if (hours == 0 && minute <= 60 && minute >= 0) {
                tvWarnTwoHourAgo.setText("Note: Store closes @" + closeTime);
            } else {
                tvWarnTwoHourAgo.setText("");
            }
        } else if (isStoreClosed) {
            tvWarnTwoHourAgo.setText("Note: Store is closed today");
        }

    }


    //code end by viraj
    /**
     * Call Customer data/address web service
     **/
    public void onCallCustomerDataTask() {
        if (UserModel.Cust_mst_ID != null) {
            String url = Constant.WS_BASE_URL + Constant.GET_CUSTOMER_DATA + UserModel.Cust_mst_ID + "/" + Constant.STOREID;
            TaskCustomerData taskCustomerData = new TaskCustomerData(getActivity(), this);
//            taskCustomerData.execute(url);
            taskCustomerData.executeOnExecutor(TaskCustomerData.THREAD_POOL_EXECUTOR,url);
        }
    }


    /**
     * Call Delivery Hour Web service
     **/
    public void onCallDeliveryHour() {
        String url = Constant.WS_BASE_URL + Constant.GET_STORE_HOUR + "/" + Constant.STOREID + "/delivery";
        TaskStoreHour taskStoreHour = new TaskStoreHour(this, 1);
//        taskStoreHour.execute(url);
        taskStoreHour.executeOnExecutor(TaskStoreHour.THREAD_POOL_EXECUTOR,url);
    }


    /**
     * Result : Delivery Hour
     **/
    @Override
    public void onDeliveryHourResult(List<StoreHour> liStoreHour) {
        Constant.liStoreHour = liStoreHour;
        //onCallStoreHour();
    }


    /**
     * Call Store Hours web service
     **/
    public void onCallStoreHour() {
        String url = Constant.WS_BASE_URL + Constant.GET_STORE_HOUR + "/" + Constant.STOREID + "/store";
        TaskStoreHour taskStoreHour = new TaskStoreHour(this, 2);
//        taskStoreHour.execute(url);
        taskStoreHour.executeOnExecutor(TaskStoreHour.THREAD_POOL_EXECUTOR,url);
    }


    /**
     * Result : Store Hour
     **/
    @Override
    public void onStoreHourResult(List<StoreHour> liStoreHour) {
        this.liDeliveryHour = liStoreHour;
    }


    /**
     * Fill Delivery option address
     **/
    @Override
    public void onTaskCustomerResult(/*List<*/ShippingData/*>*/ liShippingData, boolean isFromfavouriteStore) {
        Constant.customerData = liShippingData;
        /*Calling Customer Data web service */
        onCallShippingDataTask();

        this.shippingData = liShippingData;

        //created because delivery details comes blank
        updateAddDeliveryDetail(shippingData);
//        onUpdateDeliveryDetail(shippingData);

        etBFirstNameDo.setText(Utils.capitalFirstLatter(liShippingData.getFirstName().trim()));
        etBLastNameDO.setText(Utils.capitalFirstLatter(liShippingData.getLastName().trim()));
        etBCompanyDO.setText(Utils.capitalFirstLatter(liShippingData.getCompanyName().trim()));
        if (!Constant.AppPref.getString("email", "").isEmpty())
            tvBEmail.setText(Constant.AppPref.getString("email", ""));
        setEmailOnclick();

        //etBsEmail.setText(liShippingData.getEmail());
        String mobile = "";
        mobile = liShippingData.getPhone().trim();
        if (mobile.length() > 0)
            etBPhoneNoDO.setText(Utils.getNumberFormat(mobile).trim());

        etBPhoneNoDO.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Utils.newDesignCommonDialog(getActivity(), "Editing not allowed", getResources().getString(R.string.Editing_not_allowed_text));
            }
        });

        etBAddressOneDO.setText(Utils.capitalFirstLatter(liShippingData.getAddress1().trim()));
        etBAddressTwoDO.setText(Utils.capitalFirstLatter(liShippingData.getAddress2().trim()));
        etBZIpDO.setText(liShippingData.getZip().trim());
        etBCityDo.setText(Utils.capitalFirstLatter(liShippingData.getCity().trim()));
        etBStateDO.setText(Utils.forceCapitalString(liShippingData.getState().trim()));
    }

    private void setEmailOnclick() {

        tvBEmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Utils.newDesignCommonDialog(getActivity(), "Editing not allowed", getResources().getString(R.string.Editing_not_allowed_text));
            }
        });
    }

    private void updateAddDeliveryDetail(ShippingData shippingData) {

        etFirstNameDo.setText(Utils.capitalFirstLatter(shippingData.getFirstName()));
        etLastNameDO.setText(Utils.capitalFirstLatter(shippingData.getLastName().trim()));
        etCompanyDO.setText(Utils.capitalFirstLatter(shippingData.getCompanyName().trim()));
        if (!Constant.AppPref.getString("email", "").isEmpty())
            tvBEmail.setText(Constant.AppPref.getString("email", ""));
        setEmailOnclick();
        //etBsEmail.setText(liShippingData.getEmail());

        String mobile = "";
        mobile = shippingData.getPhone().trim();
        if (mobile.length() > 0)
            etPhoneNoDO.setText(Utils.getNumberFormat(mobile).trim());
        etAddressOneDO.setText(Utils.capitalFirstLatter(shippingData.getAddress1().trim()));
        etAddressTwoDO.setText(Utils.capitalFirstLatter(shippingData.getAddress2().trim()));
        etZIpDO.setText(shippingData.getZip().trim());
        etCityDo.setText(Utils.capitalFirstLatter(shippingData.getCity().trim()));
        etStateDO.setText(Utils.forceCapitalString(shippingData.getState().trim()));
    }


    private void onUpdateDeliveryDetail(List<ShippingData> liShippingData) {

        if (!liShippingData.get(0).getFirstName().isEmpty() && !liShippingData.get(0).getLastName().isEmpty()) {

            if (liShippingData.get(selectedAddress).getFirstName().isEmpty())
                etFirstNameDo.setText(Utils.capitalFirstLatter(this.shippingData.getFirstName().trim()));
            else
                etFirstNameDo.setText(Utils.capitalFirstLatter(liShippingData.get(selectedAddress).getFirstName().trim()));


            if (liShippingData.get(selectedAddress).getLastName().isEmpty())
                etLastNameDO.setText(Utils.capitalFirstLatter(this.shippingData.getLastName().trim()));
            else
                etLastNameDO.setText(Utils.capitalFirstLatter(liShippingData.get(selectedAddress).getLastName().trim()));


            if (liShippingData.get(selectedAddress).getCompanyName().isEmpty())
                etCompanyDO.setText(Utils.capitalFirstLatter(this.shippingData.getCompanyName().trim()));
            else
                etCompanyDO.setText(Utils.capitalFirstLatter(liShippingData.get(selectedAddress).getCompanyName().trim()));


            String mobile = "";
            mobile = liShippingData.get(selectedAddress).getPhone().trim();
            if (mobile.isEmpty())
                etPhoneNoDO.setText(Utils.getNumberFormat(this.shippingData.getPhone().trim()));
            else
                etPhoneNoDO.setText(Utils.getNumberFormat(mobile));


            if (liShippingData.get(selectedAddress).getAddress1().isEmpty()) {
                etAddressOneDO.setText(Utils.capitalFirstLatter(this.shippingData.getAddress1().trim()));
                addressForshipping = this.shippingData.getAddress1().trim();
            } else {
                etAddressOneDO.setText(Utils.capitalFirstLatter(liShippingData.get(selectedAddress).getAddress1().trim()));
                addressForshipping = liShippingData.get(selectedAddress).getAddress1().trim();
            }

            if (liShippingData.get(selectedAddress).getAddress2().isEmpty())
                etAddressTwoDO.setText(Utils.capitalFirstLatter(this.shippingData.getAddress2().trim()));
            else
                etAddressTwoDO.setText(Utils.capitalFirstLatter(liShippingData.get(selectedAddress).getAddress2().trim()));


            if (liShippingData.get(selectedAddress).getZip().isEmpty()) {
                etZIpDO.setText(this.shippingData.getZip().trim());
                zipforShipping = this.shippingData.getZip().trim();
                Log.e(TAG, "onUpdateDeliveryDetail: 1"+zipforShipping );
            } else {
                etZIpDO.setText(liShippingData.get(selectedAddress).getZip().trim());
                zipforShipping = liShippingData.get(selectedAddress).getZip().trim();
                Log.e(TAG, "onUpdateDeliveryDetail: 2"+zipforShipping );
            }

            if (liShippingData.get(selectedAddress).getCity().isEmpty()) {
                etCityDo.setText(Utils.capitalFirstLatter(this.shippingData.getCity().trim()));
                cityForshipping = this.shippingData.getCity().trim();
                Log.e(TAG, "onUpdateDeliveryDetail: 1"+cityForshipping );
            } else {
                etCityDo.setText(Utils.capitalFirstLatter(liShippingData.get(selectedAddress).getCity().trim()));
                cityForshipping = liShippingData.get(selectedAddress).getCity().trim();
                Log.e(TAG, "onUpdateDeliveryDetail: 2"+cityForshipping );
            }

            if (liShippingData.get(selectedAddress).getState().isEmpty()) {
                etStateDO.setText(Utils.forceCapitalString(this.shippingData.getState().trim()));
                stateForshipping = this.shippingData.getState().trim();
                Log.e(TAG, "onUpdateDeliveryDetail: 1"+stateForshipping );
            } else {
                etStateDO.setText(Utils.forceCapitalString(liShippingData.get(selectedAddress).getState().trim()));
                stateForshipping = liShippingData.get(selectedAddress).getState().trim();
                Log.e(TAG, "onUpdateDeliveryDetail: 2"+stateForshipping );
            }
        }
    }


    private void onUpdateBillingDetail(List<ShippingData> liShippingData) {
        if (liShippingData.size() > 1) {
            etBFirstNameDo.setText(liShippingData.get(0).getFirstName().trim());
            etBLastNameDO.setText(liShippingData.get(0).getLastName().trim());
            etBCompanyDO.setText(liShippingData.get(0).getCompanyName().trim());
            String mobile = liShippingData.get(0).getPhone().trim();
            //mobile.replace("(", "").replace(")", "").replace(" ", "").replace("-", "");

            etBPhoneNoDO.setText(Utils.getNumberFormat(mobile));

            tvBEmail.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    Utils.newDesignCommonDialog(getActivity(), "Editing not allowed", getResources().getString(R.string.Editing_not_allowed_text));
                }
            });

            etBAddressOneDO.setText(liShippingData.get(0).getAddress1().trim());
            etBAddressTwoDO.setText(liShippingData.get(0).getAddress2().trim());
            etBZIpDO.setText(liShippingData.get(0).getZip().trim());
            etBCityDo.setText(liShippingData.get(0).getCity().trim());
            etBStateDO.setText(liShippingData.get(0).getState().trim());
            if (!Constant.AppPref.getString("email", "").isEmpty())
                tvBEmail.setText(Constant.AppPref.getString("email", ""));
            setEmailOnclick();

        } else {
            etBFirstNameDo.setText(liShippingData.get(0).getFirstName().trim());
            etBLastNameDO.setText(liShippingData.get(0).getLastName().trim());
            etBCompanyDO.setText(liShippingData.get(0).getCompanyName().trim());
            String mobile = liShippingData.get(0).getPhone().trim();
            //mobile.replace("(", "").replace(")", "").replace(" ", "").replace("-", "");

            etBPhoneNoDO.setText(Utils.getNumberFormat(mobile));

            tvBEmail.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    Utils.newDesignCommonDialog(getActivity(), "Editing not allowed", getResources().getString(R.string.Editing_not_allowed_text));
                }
            });

            etBAddressOneDO.setText(liShippingData.get(0).getAddress1().trim());
            etBAddressTwoDO.setText(liShippingData.get(0).getAddress2().trim());
            etBZIpDO.setText(liShippingData.get(0).getZip().trim());
            etBCityDo.setText(liShippingData.get(0).getCity().trim());
            etBStateDO.setText(liShippingData.get(0).getState().trim());
            if (!Constant.AppPref.getString("email", "").isEmpty())
                tvBEmail.setText(Constant.AppPref.getString("email", ""));
            setEmailOnclick();

        }
    }


    /**
     * Clear All EditText
     **/
    public void onClearData() {
        etLastNameDO.getText().clear();
        etFirstNameDo.getText().clear();
        etCompanyDO.getText().clear();
        etPhoneNoDO.getText().clear();
        etAddressOneDO.getText().clear();
        etAddressTwoDO.getText().clear();
        etZIpDO.getText().clear();
        etCityDo.getText().clear();
        etStateDO.getText().clear();
    }


    /**
     * Validation when hit next button
     **/
    public boolean validate() {
        boolean valid = true;

        if (/*cvBillingOrShippingDetails.getVisibility() == View.GONE && cvDeliveryOption.getVisibility() == View.GONE*/
                !rbPickUpAtStore.isChecked() && !rbUberRush.isChecked() && !rbHandOnDelivery.isChecked() && !rbShip.isChecked()) {
            DialogUtils.onWarningDialog(getActivity(), "", "Please select a delivery option.");
//            cvRadioButton.setRadius(10);
//            cvRadioButton.setCardElevation(10);
//            cvRadioButton.setCardBackgroundColor(Color.RED);
            //    VARUNN
            GradientDrawable drawable = new GradientDrawable();
            drawable.setShape(GradientDrawable.RECTANGLE);
            drawable.setStroke(2, Color.RED);
            cvRadioButton.setBackground(drawable);
            valid = false;
        }

        if (rbPickUpAtStore.isChecked()) {
            //    VARUNN
            rbShip.setChecked(false);
            rbHandOnDelivery.setChecked(false);
            rbUberRush.setChecked(false);
            rbg_delivery_option_hand.clearCheck();
            rbg_delivery_option_ubber.clearCheck();
            rbg_delivery_option_ship.clearCheck();
            cv_ship.setVisibility(View.GONE);
            if (!rbPayAtStore.isChecked() && !rbPayWithCart.isChecked()) {
                DialogUtils.onWarningDialog(getActivity(), "", "Please select a payment option.");
                valid = false;
            }
        }

        if (cvDeliveryOption.getVisibility() == View.VISIBLE) {
            if (etLastNameDO.getText().toString().isEmpty()) {
                etLastNameDO.setError("Last Name is required.");
                etLastNameDO.requestFocus();
                valid = false;
            } else if (etFirstNameDo.getText().toString().isEmpty()) {
                etFirstNameDo.setError("First Name is required.");
                etFirstNameDo.requestFocus();
                valid = false;
            } /*else if (etCompanyDO.getText().toString().isEmpty()) {
                etCompanyDO.setError("Company Name is required.");
                etCompanyDO.requestFocus();
                valid = false;
            }*/ else if (etPhoneNoDO.getText().toString().isEmpty()) {
                etPhoneNoDO.setError("Phone Number is required.");
                etPhoneNoDO.requestFocus();
                valid = false;
            } else if (etPhoneNoDO.getText().toString().length() < 14) {
                etPhoneNoDO.setError("Please enter 10 digit Phone Number.");
                etPhoneNoDO.requestFocus();
                valid = false;
            } else if (etAddressOneDO.getText().toString().isEmpty()) {
                etAddressOneDO.setError("Address 1 is required.");
                etAddressOneDO.requestFocus();
                valid = false;
            }/*else if (etAddressTwoDO.getText().toString().isEmpty()){
                etAddressTwoDO.setError("Address 2 is required.");
                etAddressTwoDO.requestFocus();
            }*/ else if (etZIpDO.getText().toString().isEmpty()) {
                etZIpDO.setError("Zip is required.");
                etZIpDO.requestFocus();
                valid = false;
            } else {
                etLastNameDO.setError(null);
                etFirstNameDo.setError(null);
                etCompanyDO.setError(null);
                etPhoneNoDO.setError(null);
                etAddressOneDO.setError(null);
                etAddressTwoDO.setError(null);
                etZIpDO.setError(null);
            }
        }

        if (cvBillingOrShippingDetails.getVisibility() == View.VISIBLE) {
            if (etBLastNameDO.getText().toString().isEmpty()) {
                etBLastNameDO.setError("Last Name is required.");
                etBLastNameDO.requestFocus();
                valid = false;
            } else if (etBFirstNameDo.getText().toString().isEmpty()) {
                etBFirstNameDo.setError("First Name is required.");
                etBFirstNameDo.requestFocus();
                valid = false;
            } /*else if (etBsCompanyDO.getText().toString().isEmpty()) {
                etBsCompanyDO.setError("Company Name is required.");
                etBsCompanyDO.requestFocus();
                valid = false;
//            }*/ else if (etBPhoneNoDO.getText().toString().isEmpty()) {
//                etBsPhoneNoDO.setError("Phone Number is required.");
//                etBsPhoneNoDO.requestFocus();
//                valid = false;
            }
//            else if (etBsPhoneNoDO.getText().toString().length() < 14) {
//                etBsPhoneNoDO.setError("Please enter 10 digit Phone Number.");
//                etBsPhoneNoDO.requestFocus();
//                valid = false;
//            }
            else if (etBAddressOneDO.getText().toString().isEmpty()) {
                etBAddressOneDO.setError("Address 1 is required.");
                etBAddressOneDO.requestFocus();
                valid = false;
            }/*else if (etBsAddressTwoDO.getText().toString().isEmpty()){
                etBsAddressTwoDO.setError("Address 2 is required.");
                etBsAddressTwoDO.requestFocus();
            }*/ else if (etBZIpDO.getText().toString().isEmpty()) {
                etBZIpDO.setError("Zip is required.");
                etBZIpDO.requestFocus();
                valid = false;
            } else {
                etBLastNameDO.setError(null);
                etBFirstNameDo.setError(null);
                etBCompanyDO.setError(null);
//                etBsPhoneNoDO.setError(null);
                etBAddressOneDO.setError(null);
                etBAddressTwoDO.setError(null);
                etBZIpDO.setError(null);
            }
        }
        etBZIpDO.clearFocus();
        return valid;
    }


    /**
     * Fill data when Zip code was changed
     **/
    public static void onFillZipAddress(PinModel model, int status) {
        Log.e(TAG, "onFillZipAddress: ");
        if (status == 3) {

//             Edited by Varun for Shipping Charges when zip code is type manually

            etZIpDO.clearFocus();
            if (model._state.equalsIgnoreCase("") || model._state.isEmpty() &&
                    model._city.equalsIgnoreCase("") || model._city.isEmpty()){
//                etStateDO.setText("");
//                etZIpDO.setText("");
//                etCityDo.setText("");
            }else {
                if (Constant.AddressOne.equals(etAddressOneDO.getText().toString())) {
//                    etZIpDO.setText(Constant.Zip);
                    etCityDo.setText(Constant.City);
                    etStateDO.setText(Constant.State);
                } else {
                    etStateDO.setText(model._state);
                    etCityDo.setText(model._city);
//                if (deliveryOptionsFragment.rbShip.isChecked()){
//                    deliveryOptionsFragment.callShippingWService();
//                }
                }
                etZIpDO.clearFocus();
            }
//            END
//            etStateDO.setText(model._state);
//            etCityDo.setText(model._city);


        }

        if (status == 4) {

//            Edited by Varun for Shipping Charges when zip code is type manually

            etBZIpDO.clearFocus();
            if (model._state.equalsIgnoreCase("") || model._state.isEmpty() &&
                    model._city.equalsIgnoreCase("") || model._city.isEmpty()) {
//                etBStateDO.setText("");
//                etBZIpDO.setText("");
//                etBCityDo.setText("");
            } else {
                if (Constant.BAddressOne.equals(etBAddressOneDO.getText().toString())) {
//                    etBZIpDO.setText(Constant.BZip);
                    etBCityDo.setText(Constant.BCity);
                    etBStateDO.setText(Constant.BState);
                } else {
                    etBStateDO.setText(model._state);
                    etBCityDo.setText(model._city);
                }
                etBCityDo.clearFocus();
//                if (deliveryOptionsFragment.rbShip.isChecked()){
//                    deliveryOptionsFragment.callShippingWService();
//                    etBZIpDO.clearFocus();
//                }
            }
        }
//            END

//            etBStateDO.setText(model._state);
//            etBCityDo.setText(model._city);

    }


    /**
     * Open Other Addresses Dialog
     **/
    public void onOtherAddresses(List<ShippingData> liShippingData) {

        if (Constant.SCREEN_LAYOUT == 1) {
            addressesDialog = new Dialog(MainActivity.getInstance(), R.style.DialogSlideAnim_login);
            addressesDialog.setCanceledOnTouchOutside(false);
            vAddressesDialog = LayoutInflater.from(MainActivity.getInstance()).inflate(R.layout.dialog_addresses, null);
        } else if (Constant.SCREEN_LAYOUT == 2) {
            addressesDialog = new Dialog(MainActivityDup.getInstance(), R.style.DialogSlideAnim_login);
            addressesDialog.setCanceledOnTouchOutside(false);
            vAddressesDialog = LayoutInflater.from(MainActivityDup.getInstance()).inflate(R.layout.dialog_addresses, null);
        }

        tvTitleAD = vAddressesDialog.findViewById(R.id.tv_title_addresses_dialog);
        tvAlternativeTextAD = vAddressesDialog.findViewById(R.id.tv_alternate_address_addresses_dialog);
        vAD = vAddressesDialog.findViewById(R.id.v_addresses_dialog);
        vAlternativeAD = vAddressesDialog.findViewById(R.id.v_alternate_addresses_dialog);
        rvAD = vAddressesDialog.findViewById(R.id.rv_addresses_dialog);
        imgCancel = vAddressesDialog.findViewById(R.id.img_cancel_rv_addresses_dialog);
        imgCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (addressesDialog.isShowing())
                    addressesDialog.dismiss();
            }
        });

        //Set recyclerView in GridView
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
        rvAD.setLayoutManager(layoutManager);
        rvAD.setHasFixedSize(true);


        for (int i = 0; i < liShippingData.size(); i++) {
            if (!liShippingData.get(i).getFirstName().isEmpty() && !liShippingData.get(i).getLastName().isEmpty()) {
                AddressesAdapter addressesAdapter = new AddressesAdapter(liShippingData, this);
                rvAD.setAdapter(addressesAdapter);
                addressesAdapter.notifyDataSetChanged();
            } else {
                rvAD.setVisibility(View.GONE);
                tvAlternativeTextAD.setVisibility(View.VISIBLE);
                ColorDrawable colorDrawable = new ColorDrawable(Color.parseColor(Constant.themeModel.ThemeColor));
                vAlternativeAD.setBackground(colorDrawable);
                vAlternativeAD.setVisibility(View.GONE);
            }
        }
      /*  WindowManager.LayoutParams params = addressesDialog.getWindow().getAttributes();
        params.width = WindowManager.LayoutParams.WRAP_CONTENT;
        params.height = WindowManager.LayoutParams.WRAP_CONTENT;

        addressesDialog.setContentView(vAddressesDialog);
        addressesDialog.getWindow().setGravity(Gravity.CENTER);
        WindowManager.LayoutParams layoutParam = addressesDialog.getWindow().getAttributes();
        addressesDialog.getWindow().setAttributes(layoutParam);
        addressesDialog.show();*/
//*

/// dialog buttom white spece remove by viraj lakhani  15/11/24
        DisplayMetrics displayMetrics = new DisplayMetrics();
        getActivity().getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        int width = displayMetrics.widthPixels;
        int height = displayMetrics.heightPixels;


        WindowManager.LayoutParams params = addressesDialog.getWindow().getAttributes();
        params.width = (int) (width * 0.9);  // 90% of screen width
        params.height = (int) (height * 0.9); // 60% of screen height
        addressesDialog.getWindow().setAttributes(params);

        // Show dialog
        addressesDialog.setContentView(vAddressesDialog);
        addressesDialog.getWindow().setGravity(Gravity.CENTER);
        addressesDialog.show();
        /////end

    }


    /**
     * Fill field address that choose from dialog
     **/
    @Override
    public void onAddressChoose(List<ShippingData> liShippingData, int i) {
        //this.liShippingData= (List<ShippingData>) liShippingData.get(i);
        selectedAddress = i;
        shippingData = liShippingData.get(i);

        if (rbHandOnDelivery.isChecked()) {
            Constant.hand_delivery =true;
            //    VARUNN
            rbShip.setChecked(false);
            rbUberRush.setChecked(false);
            rbPickUpAtStore.setChecked(false);
            rbg_delivery_option_ubber.clearCheck();
            rgMain.clearCheck();
            rbg_delivery_option_ship.clearCheck();
            cv_ship.setVisibility(View.GONE);
            String Url1 = Constant.WS_BASE_URL + Constant.GET_DELI_ZIP_CODES + Constant.STOREID;
            TaskGetZipCode taskzipcode = new TaskGetZipCode(getActivity(), this);
//            taskzipcode.execute(Url1);
            taskzipcode.executeOnExecutor(TaskGetZipCode.THREAD_POOL_EXECUTOR,Url1);
        }

        if (addressesDialog.isShowing())
            addressesDialog.dismiss();

//        clearaddress();

        etFirstNameDo.setText(Utils.capitalFirstLatter(liShippingData.get(i).getFirstName()));
        etLastNameDO.setText(Utils.capitalFirstLatter(liShippingData.get(i).getLastName()));
        etCompanyDO.setText(Utils.capitalFirstLatter(liShippingData.get(i).getCompanyName()));
        String mobile = liShippingData.get(i).getPhone().trim();
        //mobile.replace("(", "").replace(")", "").replace(" ", "").replace("-", "");

        etPhoneNoDO.setText(Utils.getNumberFormat(mobile));
        etAddressOneDO.setText(Utils.capitalFirstLatter(liShippingData.get(i).getAddress1()));
        etAddressTwoDO.setText(Utils.capitalFirstLatter(liShippingData.get(i).getAddress2()));
        etZIpDO.setText(liShippingData.get(i).getZip());
        etCityDo.setText(Utils.capitalFirstLatter(liShippingData.get(i).getCity()));
        etStateDO.setText(Utils.forceCapitalString(liShippingData.get(i).getState()));

//         Edited by Varun
//         For When user seltect the address from address list the shipping Rates and Zipcode of that Textview will be change as per Address

        if (rbShip.isChecked()&&cbxShip.isChecked()){
//            if (Constant.STOREID.equalsIgnoreCase("707") || Constant.STOREID.equalsIgnoreCase("7365")) {
            //    VARUNN
            rbHandOnDelivery.setChecked(false);
            rbUberRush.setChecked(false);
            rbPickUpAtStore.setChecked(false);
            rbg_delivery_option_ubber.clearCheck();
            rgMain.clearCheck();
            rbg_delivery_option_hand.clearCheck();
            onUpdateDeliveryDetail(liShippingData);
            callShippingWService();
            Log.e("callShippingWService", "callShippingWService: 5" );
//            }
        }


//        END

    }

    private void clearaddress() {
        etFirstNameDo.getText().clear();
        etLastNameDO.getText().clear();
        etCompanyDO.getText().clear();
        etPhoneNoDO.getText().clear();
        etAddressOneDO.getText().clear();
        etAddressTwoDO.getText().clear();
        etZIpDO.getText().clear();
        etCityDo.getText().clear();
        etStateDO.getText().clear();
    }

    @Override
    public void contactInfoEventResult(ContatInfo contatInfo) {

        if (contatInfo != null) {
            Constant.contatInfo = contatInfo;
        }

    }

    /**
     * show Tip Dialog
     **/
    private void showTipDialog() {
        isTipDialog = true;
        isTipDialogEnable = true;
        if (Constant.SCREEN_LAYOUT == 1) {
            tipDialog = new Dialog(MainActivity.getInstance(), R.style.DialogSlideAnim_login);
            tipDialog.setCanceledOnTouchOutside(true);
            vTipDialog = LayoutInflater.from(MainActivity.getInstance()).inflate(R.layout.dialog_tip, null);

        } else if (Constant.SCREEN_LAYOUT == 2) {
            tipDialog = new Dialog(MainActivityDup.getInstance(), R.style.DialogSlideAnim_login);
            tipDialog.setCanceledOnTouchOutside(true);
            vTipDialog = LayoutInflater.from(MainActivityDup.getInstance()).inflate(R.layout.dialog_tip, null);

        }

        DecimalFormat df = new DecimalFormat("####0.00");
        df.setMaximumFractionDigits(2);
        //df.setRoundingMode(RoundingMode.UP);

        tvTitleTD = vTipDialog.findViewById(R.id.tv_title_tip_dialog);
        vTD = vTipDialog.findViewById(R.id.v_tip_dialog);
        tvTitleSubTotalTD = vTipDialog.findViewById(R.id.tv_title_sub_total_tip_dialog);
        tvSubTotalTD = vTipDialog.findViewById(R.id.tv_sub_total_tip_dialog);
        tvTipApplyTD = vTipDialog.findViewById(R.id.tv_apply_tip_dialog);

        rgTipTD = vTipDialog.findViewById(R.id.rg_tip_dialog);

        rbTipFifteenTD = vTipDialog.findViewById(R.id.rb_fifteen_tip_dialog);
        rbTipEighteenTD = vTipDialog.findViewById(R.id.rb_eighteen_tip_dialog);
        rbTipTwentyTD = vTipDialog.findViewById(R.id.rb_twenty_tip_dialog);
        rbCustomTipTD = vTipDialog.findViewById(R.id.rb_custom_tip_dialog);
        rbCashTipTD = vTipDialog.findViewById(R.id.rb_cash_tip_dialog);
        rbNoTipTD = vTipDialog.findViewById(R.id.rb_no_tip_dialog);
        et_tipvalue = vTipDialog.findViewById(R.id.et_tipvalue);
        et_tipvalue.setText(String.valueOf(df.format(defaultVal)));
        et_tipvalue.setFilters(new InputFilter[]{new Utils.DecimalDigitsInputFilter(3, 2)});
        ll_custom_tip = vTipDialog.findViewById(R.id.ll_custom_tip);
        ll_custom_tip_btn = vTipDialog.findViewById(R.id.ll_custom_tip_btn);
        iv_close = vTipDialog.findViewById(R.id.iv_close);
        iv_close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (tipDialog.isShowing())
                    tipDialog.dismiss();
            }
        });

        onTipVisibility();

        fifteenResult = (_subTotal * 15) / 100;
        eighteenResult = (_subTotal * 18) / 100;
        twentyResult = (_subTotal * 20) / 100;

        tvSubTotalTD.setText(String.valueOf(String.valueOf(df.format(_subTotal))));
        rbTipFifteenTD.setText("15% = $ " + String.valueOf(df.format(fifteenResult)));
        rbTipEighteenTD.setText("18% = $ " + String.valueOf(df.format(eighteenResult)));
        rbTipTwentyTD.setText("20% = $ " + String.valueOf(df.format(twentyResult)));

        btnContinueTD = vTipDialog.findViewById(R.id.btn_continue_tip_dialog);
        GradientDrawable bgShape = (GradientDrawable) btnContinueTD.getBackground();
        bgShape.setColor(Color.parseColor(Constant.themeModel.ThemeColor));

        btnBackTD = vTipDialog.findViewById(R.id.btn_back_tip_dialog);
        GradientDrawable bgShape2 = (GradientDrawable) btnBackTD.getBackground();
        bgShape.setColor(Color.parseColor(Constant.themeModel.ThemeColor));

        btnBackTD2 = vTipDialog.findViewById(R.id.btn_back2_tip_dialog);
        GradientDrawable bgShape3 = (GradientDrawable) btnBackTD2.getBackground();
        bgShape.setColor(Color.parseColor(Constant.themeModel.ThemeColor));

        btn_submit_tip_dialog = vTipDialog.findViewById(R.id.btn_submit_tip_dialog);
        GradientDrawable bgShape1 = (GradientDrawable) btn_submit_tip_dialog.getBackground();
        bgShape1.setColor(Color.parseColor(Constant.themeModel.ThemeColor));

        //Edited by janvi 27th Oct*********

        rgTipTD.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.rb_custom_tip_dialog:
                        tipSelectedOption = 4;
                        defaultVal = 3;
                        btnContinueTD.setVisibility(View.GONE);
                        tvTitleSubTotalTD.setVisibility(View.GONE);
                        tvSubTotalTD.setVisibility(View.GONE);
                        tvTipApplyTD.setVisibility(View.GONE);
                        rgTipTD.setVisibility(View.GONE);
                        btnBackTD.setVisibility(View.GONE);
                        ll_custom_tip.setVisibility(View.VISIBLE);
                        ll_custom_tip_btn.setVisibility(View.VISIBLE);
//                        btn_submit_tip_dialog.setVisibility(View.VISIBLE);
                        tvTitleTD.setText("Custom Dollar Tip");
                        iv_close.setVisibility(View.VISIBLE);
//                        et_tipvalue.setText(""+ defaultVal);
                        et_tipvalue.setText(String.valueOf(df.format(defaultVal)));

                        break;
                }
            }
        });


        btn_submit_tip_dialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Utils.hideKeyboardForceFully(getActivity());
                if (!et_tipvalue.getText().toString().equals("")) {

                    defaultVal = Float.parseFloat((et_tipvalue.getText().toString()));
                    if (defaultVal >= 1 && defaultVal <= 99) {
//                        btnContinueTD.setVisibility(View.VISIBLE);
//                        tvTitleSubTotalTD.setVisibility(View.VISIBLE);
//                        tvSubTotalTD.setVisibility(View.VISIBLE);
//                        tvTipApplyTD.setVisibility(View.VISIBLE);
//                        rgTipTD.setVisibility(View.VISIBLE);
//                        ll_custom_tip.setVisibility(View.GONE);
//                        btn_submit_tip_dialog.setVisibility(View.GONE);
//                        tvTitleTD.setText("Custom dollar Tip");
//                        iv_close.setVisibility(View.GONE);
//                        et_tipvalue.clearFocus();

                        insertTempOrderWithBillingAndShippingData();

                        if (tipDialog.isShowing())
                            tipDialog.dismiss();
                    } else {
                        et_tipvalue.setError("Please enter between 1 to 99 Number");
                    }
                }

            }
        });
        //end************

        btnContinueTD.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (rgTipTD.getCheckedRadioButtonId() == rbTipFifteenTD.getId()) {
                    tipSelectedOption = 1;
                } else if (rgTipTD.getCheckedRadioButtonId() == rbTipEighteenTD.getId()) {
                    tipSelectedOption = 2;
                } else if (rgTipTD.getCheckedRadioButtonId() == rbTipTwentyTD.getId()) {
                    tipSelectedOption = 3;
                }
                else if (rgTipTD.getCheckedRadioButtonId() == rbCustomTipTD.getId()) {
                    tipSelectedOption = 4;
                }
                else if (rgTipTD.getCheckedRadioButtonId() == rbCashTipTD.getId()) {
                    tipSelectedOption = 5;
                } else if (rgTipTD.getCheckedRadioButtonId() == rbNoTipTD.getId()) {
                    tipSelectedOption = 6;
                } else {

                }

/*                if (myDeliveryOptionsEvent != null)
                    myDeliveryOptionsEvent.nextFromDeliveryOption(addDataIntoBundle());*/
                //insertEmptyTempOrderWithTipValue();
                insertTempOrderWithBillingAndShippingData();

                if (tipDialog.isShowing())
                    tipDialog.dismiss();
            }
        });

        btnBackTD.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (tipDialog.isShowing())
                    tipDialog.dismiss();
            }
        });

        btnBackTD2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (tipDialog.isShowing())
                    tipDialog.dismiss();
            }
        });

        WindowManager.LayoutParams params = tipDialog.getWindow().getAttributes();
        params.width = WindowManager.LayoutParams.WRAP_CONTENT;
        params.height = WindowManager.LayoutParams.WRAP_CONTENT;

        tipDialog.setContentView(vTipDialog);
        tipDialog.getWindow().setGravity(Gravity.CENTER);
        WindowManager.LayoutParams layoutParam = tipDialog.getWindow().getAttributes();
        tipDialog.getWindow().setAttributes(layoutParam);
        tipDialog.show();
    }


    /**
     * Tip visibility
     **/
    private void onTipVisibility() {
        if (Constant.twentyOneYear.getBSSetupTip1())
            rbTipFifteenTD.setVisibility(View.VISIBLE);
        if (Constant.twentyOneYear.getBSSetupTip2())
            rbTipEighteenTD.setVisibility(View.VISIBLE);
        if (Constant.twentyOneYear.getBSSetupTip3())
            rbTipTwentyTD.setVisibility(View.VISIBLE);
        if (Constant.twentyOneYear.getBSSetupTip4())
            rbCashTipTD.setVisibility(View.VISIBLE);
        if (Constant.twentyOneYear.getBSSetupTip5())
            rbCustomTipTD.setVisibility(View.VISIBLE);
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        myDeliveryOptionsEvent = (DeliveryOptionsEvent) context;
        if (context instanceof Activity) {//Name of your activity
            this.context = (Activity) context;
        }
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        myDeliveryOptionsEvent = (DeliveryOptionsEvent) activity;
        context = activity;
    }

    @Override
    public void onClick(View view) {

//        Delivery Address layoout onclick
        if (view.getId() == img_dd_last_name.getId()){
            etLastNameDO.getText().clear();
        }
        if (view.getId() == img_dd_first_name.getId()){
            etFirstNameDo.getText().clear();
        }
        if (view.getId() == img_dd_company_name.getId()){
            etCompanyDO.getText().clear();
        }
        if (view.getId() == img_dd_address_one.getId()){
            etAddressOneDO.getText().clear();
        }
        if (view.getId() == img_dd_address_two.getId()){
            etAddressTwoDO.getText().clear();
        }
        if (view.getId() == img_dd_city.getId()){
            etCityDo.getText().clear();
        }
        if (view.getId() == img_dd_zip.getId()){
            etZIpDO.getText().clear();
        }
        if (view.getId() == img_dd_phone_no.getId()){
            etPhoneNoDO.getText().clear();
        }

//        Billing and Shipping Address Layout onclick

        if (view.getId() == img_last_name.getId()){
            etBLastNameDO.getText().clear();
        }
        if (view.getId() == img_first_name.getId()){
            etBFirstNameDo.getText().clear();
        }
        if (view.getId() == img_company_name.getId()){
            etBCompanyDO.getText().clear();
        }
        if (view.getId() == img_address_one.getId()){
            etBAddressOneDO.getText().clear();
        }
        if (view.getId() == img_address_two.getId()){
            etBAddressTwoDO.getText().clear();
        }
        if (view.getId() == img_city.getId()){
            etBCityDo.getText().clear();
        }
        if (view.getId() == img_zip.getId()){
            etBZIpDO.getText().clear();
        }

        if (view.getId() == txtAddressLists.getId()) {
            onOtherAddresses(liShippingData);
        }

        if (view.getId() == tvClear.getId()) {
            onClearData();
        }

        if (view.getId() == btnNext.getId()) {
            isNextCall = true;
            if (rbHandOnDelivery.isChecked()) {
                //    VARUNN
                rbShip.setChecked(false);
                rbUberRush.setChecked(false);
                rbPickUpAtStore.setChecked(false);
                rbg_delivery_option_ubber.clearCheck();
                rgMain.clearCheck();
                rbg_delivery_option_ship.clearCheck();
                cv_ship.setVisibility(View.GONE);

                String Url1 = Constant.WS_BASE_URL + Constant.GET_DELI_ZIP_CODES + Constant.STOREID;
                TaskGetZipCode taskzipcode = new TaskGetZipCode(getActivity(), this);
//                taskzipcode.execute(Url1);
                taskzipcode.executeOnExecutor(TaskGetZipCode.THREAD_POOL_EXECUTOR,Url1);
            } else {
                callNext();
            }
        }

        if (view.getId() == btnPrev.getId()) {
            onBackPressed();
        }

        if (view.getId()==cv_radio_button_pick_up.getId()){
            updateRadioButtons(rbPickUpAtStore, "pick_up_store");
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                cv_radio_button_pick_up.setCardBackgroundColor(context.getColor(R.color.pressed_color_lighter));
                cv_radio_button_hand_delivery.setCardBackgroundColor(context.getColor(R.color.White));
                cv_radio_button_ship.setCardBackgroundColor(context.getColor(R.color.White));
                cv_radio_button_ubber.setCardBackgroundColor(context.getColor(R.color.White));
            }
        }

        if (view.getId()==cv_radio_button_ubber.getId()){
            updateRadioButtons(rbUberRush, "Ubber_Rush");
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                cv_radio_button_ubber.setCardBackgroundColor(context.getColor(R.color.pressed_color_lighter));
                cv_radio_button_pick_up.setCardBackgroundColor(context.getColor(R.color.White));
                cv_radio_button_hand_delivery.setCardBackgroundColor(context.getColor(R.color.White));
                cv_radio_button_ship.setCardBackgroundColor(context.getColor(R.color.White));
            }
        }

        if (view.getId()==cv_radio_button_hand_delivery.getId()){
            updateRadioButtons(rbHandOnDelivery, "Hand Delivery");
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                cv_radio_button_hand_delivery.setCardBackgroundColor(context.getColor(R.color.pressed_color_lighter));
                cv_radio_button_pick_up.setCardBackgroundColor(context.getColor(R.color.White));
                cv_radio_button_ship.setCardBackgroundColor(context.getColor(R.color.White));
                cv_radio_button_ubber.setCardBackgroundColor(context.getColor(R.color.White));
            }
        }

        if (view.getId()==cv_radio_button_ship.getId()){
            updateRadioButtons(rbShip, "Ship");
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                cv_radio_button_ship.setCardBackgroundColor(context.getColor(R.color.pressed_color_lighter));
                cv_radio_button_pick_up.setCardBackgroundColor(context.getColor(R.color.White));
                cv_radio_button_hand_delivery.setCardBackgroundColor(context.getColor(R.color.White));
                cv_radio_button_ubber.setCardBackgroundColor(context.getColor(R.color.White));
            }
        }

    }


    private String getColoredSpanned(String text, String color) {
        String input = "<font color=" + color + ">" + text + "</font>";
        return input;
    }

    public void callNext() {
        selectedRadioButton = 0;

        if (!validate()) {
            return;
        }

        if (rbPickUpAtStore.isChecked()) {
            //    VARUNN
            rbShip.setChecked(false);
            rbHandOnDelivery.setChecked(false);
            rbUberRush.setChecked(false);
            rbg_delivery_option_hand.clearCheck();
            rbg_delivery_option_ubber.clearCheck();
            rbg_delivery_option_ship.clearCheck();
            cv_ship.setVisibility(View.GONE);
            isHandDelivery = 0;
            isPickUpAtStore = 1;
            if (rbPickUpAtStore.isChecked() && rbPayAtStore.isChecked()) {
                selectedRadioButton = 1;
                isPayAtStore = 1;
                //insertEmptyTempOrder();

//                insertTempOrderWithBillingAndShippingData();

                //for pickup time commented above only one line and call this function on submit button of pickup time.

                onCallGlobalSetup();

            }
            if (rbPickUpAtStore.isChecked() && rbPayWithCart.isChecked()) {

//                displayPickupTimeFunctionality();

                selectedRadioButton = 2;
                isPayWithCard = 1;
                //insertEmptyTempOrder();
//                insertTempOrderWithBillingAndShippingData();

                onCallGlobalSetup();
                //for pickup time commented above only one line and call this function on submit button of pickup time.
//                if(Constant.twentyOneYear != null && Constant.twentyOneYear.getAllowPickUpTime()){
//                    loadOnlyStoreHoursWS();
//                }else{
//                    insertTempOrderWithBillingAndShippingData();
//                }
            }
        } else if (rbUberRush.isChecked()) {
            //    VARUNN
            rbShip.setChecked(false);
            rbHandOnDelivery.setChecked(false);
            rbPickUpAtStore.setChecked(false);
            rbg_delivery_option_hand.clearCheck();
            rgMain.clearCheck();
            rbg_delivery_option_ship.clearCheck();
            cv_ship.setVisibility(View.GONE);
            selectedRadioButton = 3;
            isUberRush = 1;
            isHandDelivery = 0;
            insertTempOrderWithBillingAndShippingData();
            Constant.pick_up=false;
            //insertEmptyTempOrder();
        } else if (rbHandOnDelivery.isChecked()) {
            //    VARUNN
            rbShip.setChecked(false);
            rbUberRush.setChecked(false);
            rbPickUpAtStore.setChecked(false);
            rbg_delivery_option_ubber.clearCheck();
            rgMain.clearCheck();
            rbg_delivery_option_ship.clearCheck();
            cv_ship.setVisibility(View.GONE);
            selectedRadioButton = 4;
            isHandDelivery = 1;
            isSame = 0;
            Constant.pick_up=false;
            if (btnNext.getText().toString().equals("CONTINUE SHOPPING")) {
                //Toast.makeText(getActivity(), "Continue Shopping",Toast.LENGTH_SHORT).show();
                getFragmentManager().popBackStack();
                getFragmentManager().popBackStackImmediate();
                if (Constant.SCREEN_LAYOUT == 1) {
                    MainActivity.showHomePage();
                    MainActivity.getInstance().loadHomeWebPage();
                } else if (Constant.SCREEN_LAYOUT == 2) {
                    MainActivityDup.showHomePage();
                    MainActivityDup.getInstance().loadHomeWebPage();
                }

            } else {
                if (Constant.twentyOneYear.getBSSetupAllowTip()) {
                    showTipDialog();
                } else {
                    insertTempOrderWithBillingAndShippingData();
                    //insertEmptyTempOrder();
                }
            }
        } else if (rbShip.isChecked()) {
            //    VARUNN
            rbHandOnDelivery.setChecked(false);
            rbUberRush.setChecked(false);
            rbPickUpAtStore.setChecked(false);
            rbg_delivery_option_ubber.clearCheck();
            rgMain.clearCheck();
            rbg_delivery_option_hand.clearCheck();
            Constant.pick_up=false;

            selectedRadioButton = 5;
            isDeliverHome = 1;
            isHandDelivery = 0; //Edited by Janvi 15th dec
            if (rbShip.isChecked() && cbxShip.isChecked()) {
                isSame = 1;
                insertTempOrderWithBillingAndShippingData();
            } else {
                if (rbShip.isChecked()) {
                    insertTempOrderWithBillingAndShippingData();
                }
            }
               /* if (myDeliveryOptionsEvent != null)
                    myDeliveryOptionsEvent.nextFromDeliveryOption(addDataIntoBundle());*/
        }

    }

    public void loadOnlyStoreHoursWS() {

        String Url = Constant.WS_BASE_URL + Constant.GET_DELIVERY_HOURS + "/" +  Constant.STOREID + "/" + "store";

        TaskStoreDeliveryHours taskStoreDeliveryHours = new TaskStoreDeliveryHours(this,getActivity(),"deliveryOption");
//        taskStoreDeliveryHours.execute(Url);
        taskStoreDeliveryHours.executeOnExecutor(TaskStoreDeliveryHours.THREAD_POOL_EXECUTOR,Url);
    }

    @Override
    public void onGetStoreDeliveryHoursResult(List<StoreHour> storeDeliveryHourList) {

        if(storeDeliveryHourList != null && storeDeliveryHourList.size()>0){
            //to display pickup time dialog
            Constant.liOnlyStoreHour = storeDeliveryHourList;
            displayPickupTimeFunctionality();
            //end***
        }
    }

    private void displayPickupTimeFunctionality() {

        if(Constant.liOnlyStoreHour != null && Constant.liOnlyStoreHour.size() > 0){
            String today = Utils.getCurrentDay();
            boolean isStoreClosedtoday = false;
            boolean isStoreClosedtomorrow = false;

            results = new ArrayList<PickupModel>();
            int pos = 0;

            SimpleDateFormat sdf = new SimpleDateFormat("hh:mm a");

            Date d = new Date();
            for (int i = 0; i < Constant.liOnlyStoreHour.size(); i++) {
                if (today.equals(Constant.liOnlyStoreHour.get(i).getStoreDay())) {
                    pos = i;
                    if (Constant.liOnlyStoreHour.get(i).getClosed()) {
                        isStoreClosedtoday = true;
                    }
                }
            }

//            String openTime = Constant.liOnlyStoreHour.get(pos).getOpenTime();
            String closeTime1 = Constant.liOnlyStoreHour.get(pos).getCloseTime();

            String[] gethourArray = closeTime1.split(":");

            String firstDigitHour = gethourArray[0].trim();
            String remainDigitHour = gethourArray[1].trim();
            String finalyDisplayhour = null;
            String closeTime = null;

            if(firstDigitHour.equals("01")){
                finalyDisplayhour = "1";
                closeTime = finalyDisplayhour + ":" + remainDigitHour;

            }else if(firstDigitHour.equals("02")){
                finalyDisplayhour = "2";
                closeTime = finalyDisplayhour + ":" + remainDigitHour;

            }else if(firstDigitHour.equals("03")){
                finalyDisplayhour = "3";
                closeTime = finalyDisplayhour +":" +remainDigitHour;

            }else if(firstDigitHour.equals("04")){
                finalyDisplayhour = "4";
                closeTime = finalyDisplayhour + ":" + remainDigitHour;

            }else if(firstDigitHour.equals("05")){
                finalyDisplayhour = "5";
                closeTime = finalyDisplayhour +":" + remainDigitHour;

            }else if(firstDigitHour.equals("06")){
                finalyDisplayhour = "6";
                closeTime = finalyDisplayhour + ":" +remainDigitHour;

            }else if(firstDigitHour.equals("07")){
                finalyDisplayhour = "7";
                closeTime = finalyDisplayhour + ":" +remainDigitHour;

            }else if(firstDigitHour.equals("08")){
                finalyDisplayhour = "8";
                closeTime = finalyDisplayhour + ":" + remainDigitHour;

            }else if(firstDigitHour.equals("09")){
                finalyDisplayhour = "9";
                closeTime = finalyDisplayhour + ":" +remainDigitHour;
            }else{
                closeTime = closeTime1;
            }

            if(!isStoreClosedtoday){

                int[] todayTimediff = getTimeDifference(closeTime,"", today);

                gethourIntervals(todayTimediff,closeTime, "", today,"");
            }

            int j = 1;
            String tomorrowDay = Utils.getNextDay(j);
            String nextDayOpenTime = "";
            String nextDayCloseTime = "";

            for (int i = 0; i < Constant.liOnlyStoreHour.size(); i++) {
                if (tomorrowDay.equals(Constant.liOnlyStoreHour.get(i).getStoreDay())) {
                    nextDayOpenTime = Constant.liOnlyStoreHour.get(i).getOpenTime();
                    nextDayCloseTime = Constant.liOnlyStoreHour.get(i).getCloseTime();
                    if (Constant.liOnlyStoreHour.get(i).getClosed()) {
                        isStoreClosedtomorrow = true;
                    }
                }
            }

            if(!isStoreClosedtomorrow){

                int minWithZeroHourTomorrow = 0;
                int[] tomorrowTimediff = getTimeDifference(nextDayCloseTime,nextDayOpenTime,"tomorrow");

                gethourIntervals(tomorrowTimediff, nextDayCloseTime,nextDayOpenTime,tomorrowDay,"tomorrow");
            }

            DialogUtils.showPickupTimeDialog(getActivity(),results,isStoreClosedtoday,isStoreClosedtomorrow,today,tomorrowDay);
        }
    }

    //pickeup time  fix by viraj patel(02/12/24)

    private int[] getTimeDifference(String closeTime, String openTime, String diffDay) {
        Date date1 = null, date2 = null;
        try {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("hh:mm a");

            // If the time difference is for tomorrow, use the open time
            if (diffDay.equals("tomorrow") && !openTime.isEmpty()) {
                date1 = simpleDateFormat.parse(openTime);
            } else {
                // Otherwise, calculate the difference from the current time
                String currentHour = simpleDateFormat.format(Calendar.getInstance().getTime());
                date1 = simpleDateFormat.parse(currentHour);
            }

            // Parse the close time
            date2 = simpleDateFormat.parse(closeTime);

            if (date1 != null && date2 != null) {
                // Calculate the difference in milliseconds viraj
                long difference = date2.getTime() - date1.getTime();

                // Convert the difference into hours and minutes viraj
                int hours = (int) (difference / (1000 * 60 * 60));
                int minutes = (int) ((difference % (1000 * 60 * 60)) / (1000 * 60));

                return new int[]{hours, minutes}; // Return both hours and minutes viraj
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return new int[]{0, 0}; // Default in case of error viraj
    }
    /// pickup time fix by viraj lakhani(27/11/24)
    private void gethourIntervals(int[] timeDifference, String closeTime, String nextDayOpenTime, String displayDay, String s) {

        SimpleDateFormat sdf = new SimpleDateFormat("h:mm a");
        Calendar calendar = new GregorianCalendar();
        int hours = timeDifference[0];
        int minutes = timeDifference[1];

        int totalinterval = (hours * 4) + (minutes / 15); // minutes add totalinterval by viraj

        String opentime24hour = "";
        String today_tomrrowday = "";

        if(!nextDayOpenTime.isEmpty() && s.equals("tomorrow")){

            if(totalinterval > 0 || totalinterval == 0 ) {

                if (totalinterval == 0 && hours == 0) {
//                       Log.d("minWithzeroHour",""+minWithzeroHour);
                    totalinterval = 4;
                }

                today_tomrrowday = "Tomorrow";
                @SuppressLint("SimpleDateFormat")
                SimpleDateFormat sdf24hourFormat = new SimpleDateFormat("HH:mm");
                try {
                    Date date1 = sdf24hourFormat.parse(nextDayOpenTime);
                    opentime24hour = sdf24hourFormat.format(date1);
                    Log.e("opentime24hour", "" + opentime24hour);

                } catch (ParseException e) {
                    e.printStackTrace();
                }

                String[] arrTime = opentime24hour.split(":");

                String openHour = arrTime[0].trim();
                String openMinute = arrTime[1].trim();
                String[] openMinarr = openMinute.split(" ");
                String openMin = openMinarr[0].trim();

                Log.e("nexthour", "" + openHour);
                Log.e("nextmin", "" + openMin);

                calendar = new GregorianCalendar();
//            int currenthour = calendar.get(Calendar.HOUR);
//            int currentmin = calendar.get(Calendar.MINUTE);
//
//            Log.e("currenthour",""+currenthour);
//            Log.e("currentmin",""+currentmin);

                int nextdayhour = Integer.parseInt(openHour);
                int nextdayMin = Integer.parseInt(openMin);
//            int nextdayMinCal = 0;

//            if(nextdayMin>0 && nextdayMin<14){
//                nextdayMinCal = 15;
//            }else if(nextdayMin>15 && nextdayMin<29){
//                nextdayMinCal = 30;
//            }else if(nextdayMin>30 && nextdayMin<44){
//                nextdayMinCal = 45;
//            }else if(nextdayMin>45 && nextdayMin<59){
//                nextdayMinCal = 0;
//                nextdayhour = nextdayhour+1;
//            }

                calendar.set(Calendar.HOUR_OF_DAY, nextdayhour);
//            calendar.set(Calendar.MINUTE, nextdayMinCal);
                calendar.set(Calendar.MINUTE, nextdayMin);
                calendar.add(Calendar.MINUTE, -15);
                calendar.set(Calendar.SECOND, 0);
            }

        }else{

            if(totalinterval > 0 || totalinterval == 0) {

                if(totalinterval == 0 && hours == 0){
//                    Log.d("minWithzeroHour",""+minWithzeroHour);
                    totalinterval = 4;
                }

                //today
                today_tomrrowday = "Today";
                calendar = new GregorianCalendar();
                int currenthour = calendar.get(Calendar.HOUR);
                Log.d("currenthour****", "" + currenthour);
                int currentmin = calendar.get(Calendar.MINUTE);
                int currentMinCal = 0;

                if (currentmin > 0 && currentmin < 14) {
                    currentMinCal = 15;
                } else if (currentmin > 15 && currentmin < 29) {
                    currentMinCal = 30;
                } else if (currentmin > 30 && currentmin < 44) {
                    currentMinCal = 45;
                } else if (currentmin > 45 && currentmin < 59) {
                    currentMinCal = 0;
                    currenthour = currenthour + 1;
                }

                calendar.set(Calendar.HOUR, currenthour);
                calendar.set(Calendar.MINUTE, currentMinCal);
                calendar.set(Calendar.SECOND, 0);
                totalinterval = (totalinterval > 0) ? totalinterval - 1 : totalinterval;
            }

        }

        PickupModel pickupModel;
        for (int i = 0; i < totalinterval; i++) {
//            String  day1 = sdf.format(calendar.getTime());
            pickupModel = new PickupModel();

            // add 15 minutes to the current time; the hour adjusts automatically!
            calendar.add(Calendar.MINUTE, 15);

            String day2 = sdf.format(calendar.getTime());

            String day = day2;

            pickupModel.setDay(displayDay);
            pickupModel.setTime(day);
            pickupModel.setCurrentday(today_tomrrowday);

            if(!day2.equals(closeTime)){
                  results.add(pickupModel);
            }

//            String[] gethourArray = day.split(":");
//
//            String firstDigitHour = gethourArray[0].trim();
//            String remainDigitHour = gethourArray[1].trim();
//            String finalyDisplayhour = null;
//            String dayTime = null;
//
//            if(firstDigitHour.equals("01")){
//                finalyDisplayhour = "1";
//                dayTime = finalyDisplayhour + ":" + remainDigitHour;
//
//            }else if(firstDigitHour.equals("02")){
//                finalyDisplayhour = "2";
//                dayTime = finalyDisplayhour + ":" + remainDigitHour;
//
//            }else if(firstDigitHour.equals("03")){
//                finalyDisplayhour = "3";
//                dayTime = finalyDisplayhour +":" +remainDigitHour;
//
//            }else if(firstDigitHour.equals("04")){
//                finalyDisplayhour = "4";
//                dayTime = finalyDisplayhour + ":" + remainDigitHour;
//
//            }else if(firstDigitHour.equals("05")){
//                finalyDisplayhour = "5";
//                dayTime = finalyDisplayhour +":" + remainDigitHour;
//
//            }else if(firstDigitHour.equals("06")){
//                finalyDisplayhour = "6";
//                dayTime = finalyDisplayhour + ":" +remainDigitHour;
//
//            }else if(firstDigitHour.equals("07")){
//                finalyDisplayhour = "7";
//                dayTime = finalyDisplayhour + ":" +remainDigitHour;
//
//            }else if(firstDigitHour.equals("08")){
//                finalyDisplayhour = "8";
//                dayTime = finalyDisplayhour + ":" + remainDigitHour;
//
//            }else if(firstDigitHour.equals("09")){
//                finalyDisplayhour = "9";
//                dayTime = finalyDisplayhour + ":" +remainDigitHour;
//            }
//
//            if(dayTime!= null && !dayTime.isEmpty())
//            {
//                if(dayTime.equals(closeTime)){
//                    Log.e("test---",""+day);
//                    break;
//                }
//            } else{
            if(day2.equals(closeTime)){
                Log.e("test---",""+day2);
                break;
            }
//            }
        }
    }
/// code end by viraj lakhani(27/11/24)
    private int getMinuteInterval(String closeTime, String opentime, String diffDay) {
        Date date1 = null,date2 = null;
        long difference;
        int days;
        int hours = 0;
        int min = 0;

        try {
            if(diffDay.equals("tomorrow")&& !opentime.isEmpty()){
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("hh:mm a");
//                String currenthour = simpleDateFormat.format(opentime);
                date1 = simpleDateFormat.parse(opentime);
                date2 = simpleDateFormat.parse(closeTime);
            }else{
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("hh:mm a");
                String currenthour = simpleDateFormat.format(Calendar.getInstance().getTime());
                // Date date1 = calendar.getTime();
                date1 = simpleDateFormat.parse(currenthour);
                date2 = simpleDateFormat.parse(closeTime);
            }

            if (date1 != null && date2 != null) {
                difference = date2.getTime() - date1.getTime();

                days = (int) (difference / (1000*60*60*24));
                hours = (int) ((difference - (1000*60*60*24*days)) / (1000*60*60));
                min = (int) (difference - (1000*60*60*24*days) - (1000*60*60*hours)) / (1000*60);

            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return min;
    }

    public void onBackPressed() {
        FragmentManager fm = getActivity().getSupportFragmentManager();
        fm.popBackStack();
    }


//   / public void insertEmptyTempOrder() {
//        onInsertTempOrder(null, null, null, null, null, null, null, null, null, null,
//                null, null, null, null, null, null,
//                String.valueOf(boolTOInt(rbPickUpAtStore.isChecked())), String.valueOf(boolTOInt(rbHandOnDelivery.isChecked())),
//                String.valueOf(boolTOInt(rbUberRush.isChecked())), null, null, null, null, null,
//                String.valueOf(boolTOInt(cbxShip.isChecked())), String.valueOf(boolTOInt(rbShip.isChecked())));
//    }

//    public void insertEmptyTempOrderWithTipValue() {
//        onInsertTempOrder(null, null, null, null, null, null, null, null, null, null,
//                null, null, null, null, null, null,
//                String.valueOf(boolTOInt(rbPickUpAtStore.isChecked())), String.valueOf(boolTOInt(rbHandOnDelivery.isChecked())),
//                String.valueOf(boolTOInt(rbUberRush.isChecked())), null, null, null, null, null,
//                String.valueOf(boolTOInt(cbxShip.isChecked())), String.valueOf(boolTOInt(rbShip.isChecked())));
//    }

    public void insertTempOrderWithBillingAndShippingData() {
        String bFirstName = etBFirstNameDo.getText().toString();
        String bLastName = etBLastNameDO.getText().toString();
        String bPhone = etBPhoneNoDO.getText().toString();
        String bCompany = (etBCompanyDO.getText().toString().isEmpty()) ? "0" : etBCompanyDO.getText().toString();
        String bAddressOne = etBAddressOneDO.getText().toString();
        String bAddressTwo = etBAddressTwoDO.getText().toString();
        String bCity = (etBCityDo.getText().toString().isEmpty()) ? "0" : etBCityDo.getText().toString();
        String bState = (etBStateDO.getText().toString().isEmpty()) ? "0" : etBStateDO.getText().toString();
        String bZip = etBZIpDO.getText().toString();
        String bPhoneType = spinnerBsMobile.getSelectedItem().toString();
        String bEmail = tvBEmail.getText().toString();

        String firstName = "0", lastName = "0", phone = "0", company = "0", addressOne = "0", addressTwo = "0", city = "0", state = "0", zip = "0", phoneType = "0";

       /* String firstName = etFirstNameDo.getText().toString();
        String lastName = etLastNameDO.getText().toString();
        String phone = etPhoneNoDO.getText().toString();
        String company = (etCompanyDO.getText().toString().isEmpty()) ? "0" : etCompanyDO.getText().toString();
        String addressOne = etAddressOneDO.getText().toString();
        String addressTwo = etAddressTwoDO.getText().toString();
        String city = (etCityDo.getText().toString().isEmpty()) ? "0" : etCityDo.getText().toString();
        String state = (etStateDO.getText().toString().isEmpty()) ? "0" : etStateDO.getText().toString();
        String zip = etZIpDO.getText().toString();
        String phoneType = spinnerMobile.getSelectedItem().toString();*/

        if (isPickUpAtStore == 1 || isUberRush == 1) {
            firstName = "0";
            lastName = "0";
            phone = "0";
            company = "0";
            addressOne = "0";
            addressTwo = "0";
            city = "0";
            state = "0";
            zip = "0";
            phoneType = "0";
        }else if(isHandDelivery == 1){
            firstName = etFirstNameDo.getText().toString();
            lastName = etLastNameDO.getText().toString();
            phone = etPhoneNoDO.getText().toString();
            company = (etCompanyDO.getText().toString().isEmpty()) ? "0" : etCompanyDO.getText().toString();
            addressOne = etAddressOneDO.getText().toString();
            addressTwo = etAddressTwoDO.getText().toString();
            city = (etCityDo.getText().toString().isEmpty()) ? "0" : etCityDo.getText().toString();
            state = (etStateDO.getText().toString().isEmpty()) ? "0" : etStateDO.getText().toString();
            zip = etZIpDO.getText().toString();
            phoneType = spinnerMobile.getSelectedItem().toString();
        }

        if (isDeliverHome == 1 && isSame == 1) {

            firstName = etFirstNameDo.getText().toString();
            lastName = etLastNameDO.getText().toString();
            phone = etPhoneNoDO.getText().toString();
            company = (etCompanyDO.getText().toString().isEmpty()) ? "0" : etCompanyDO.getText().toString();
            addressOne = etAddressOneDO.getText().toString();
            addressTwo = etAddressTwoDO.getText().toString();
            city = (etCityDo.getText().toString().isEmpty()) ? "0" : etCityDo.getText().toString();
            state = (etStateDO.getText().toString().isEmpty()) ? "0" : etStateDO.getText().toString();
            zip = etZIpDO.getText().toString();
            phoneType = spinnerMobile.getSelectedItem().toString();

        /*    firstName = (firstName.isEmpty()) ? "0" : firstName;
            lastName = (lastName.isEmpty()) ? "0" : lastName;
            phone = (phone.isEmpty()) ? "0" : phone;
            company = (company.isEmpty()) ? "0" : company;
            addressOne = (addressOne.isEmpty()) ? "0" : addressOne;
            addressTwo = (addressTwo.isEmpty()) ? "0" : addressTwo;
            city = (city.isEmpty()) ? "0" : city;
            state = (state.isEmpty()) ? "0" : state;
            zip = (zip.isEmpty()) ? "0" : zip;
            phoneType = (phoneType.isEmpty()) ? "0" : phoneType;*/

            /*
            firstName = bFirstName;
            lastName = bLastName;
            phone = bPhone;
            company = bCompany;
            addressOne = bAddressOne;
            addressTwo = bAddressTwo;
            city = bCity;
            state = bState;
            zip = bZip;
            phoneType = bPhoneType;*/
        }

        onInsertTempOrder(bFirstName, bLastName, bPhone, bAddressOne, bAddressTwo, bCity, bState, bZip, firstName, lastName,
                phone, addressOne, addressTwo, city, state, zip,
                String.valueOf(isPickUpAtStore) /*String.valueOf(boolTOInt(rbPickUpAtStore.isChecked()))*/,
                String.valueOf(isDeliverHome) /*String.valueOf(boolTOInt(rbHandOnDelivery.isChecked()))*/,
                String.valueOf(isUberRush)/*String.valueOf(boolTOInt(rbUberRush.isChecked()))*/,
                bCompany, bEmail, bPhoneType, company, phoneType,
                String.valueOf(isSame)/*String.valueOf(boolTOInt(cbxShip.isChecked()))*/,
                String.valueOf(isHandDelivery)/*String.valueOf(boolTOInt(rbHandOnDelivery.isChecked()))*/);
    }

//    public void insertTempOrderWithBillingData() {
//        String firstName = etBsFirstNameDo.getText().toString();
//        String lastName = etBsLastNameDO.getText().toString();
//        String phone = etBsPhoneNoDO.getText().toString();
//        String company = (etBsCompanyDO.getText().toString().isEmpty()) ? "0" : etBsCompanyDO.getText().toString();
//        String addressOne = etBsAddressOneDO.getText().toString();
//        String addressTwo = etBsAddressTwoDO.getText().toString();
//        String city = (etBsCityDo.getText().toString().isEmpty()) ? "0" : etBsCityDo.getText().toString();
//        String state = (etBsStateDO.getText().toString().isEmpty()) ? "0" : etBsStateDO.getText().toString();
//        String zip = etBsZIpDO.getText().toString();
//        String phoneType = spinnerBsMobile.getSelectedItem().toString();
//        String email = tvBsEmail.getText().toString();
//
//        onInsertTempOrder(firstName, lastName, phone, addressOne, addressTwo, city, state, zip, null, null,
//                null, null, null, null, null, null,
//                String.valueOf(boolTOInt(rbPickUpAtStore.isChecked())), String.valueOf(boolTOInt(rbHandOnDelivery.isChecked())),
//                String.valueOf(boolTOInt(rbUberRush.isChecked())), company, email, phoneType, null, null,
//                String.valueOf(boolTOInt(cbxShip.isChecked())), String.valueOf(boolTOInt(rbHandOnDelivery.isChecked())));
//
//    }

//    public void insertTempOrderWithShippingData() {
//        String firstName = etFirstNameDo.getText().toString();
//        String lastName = etLastNameDO.getText().toString();
//        String phone = etPhoneNoDO.getText().toString();
//        String company = (etCompanyDO.getText().toString().isEmpty()) ? "0" : etCompanyDO.getText().toString();
//        String addressOne = etAddressOneDO.getText().toString();
//        String addressTwo = etAddressTwoDO.getText().toString();
//        String city = (etCityDo.getText().toString().isEmpty()) ? "0" : etCityDo.getText().toString();
//        String state = (etStateDO.getText().toString().isEmpty()) ? "0" : etStateDO.getText().toString();
//        String zip = etZIpDO.getText().toString();
//        String phoneType = spinnerMobile.getSelectedItem().toString();
//
//        onInsertTempOrder(null, null, null, null, null, null, null, null, firstName, lastName,
//                phone, addressOne, addressTwo, city, state, zip,
//                String.valueOf(boolTOInt(rbPickUpAtStore.isChecked())), String.valueOf(boolTOInt(rbHandOnDelivery.isChecked())),
//                String.valueOf(boolTOInt(rbUberRush.isChecked())), null, null, null, company, phoneType,
//                String.valueOf(boolTOInt(cbxShip.isChecked())), String.valueOf(boolTOInt(rbHandOnDelivery.isChecked())));
//
//    }


    //    VARUNN
    RadioGroup.OnCheckedChangeListener listener = new RadioGroup.OnCheckedChangeListener() {
        @Override
        public void onCheckedChanged(RadioGroup group, int checkedId) {
            if (isHandlingChange) {
                return;
            }

            isHandlingChange = true;

            try {
                int groupId = group.getId(); // Get the ID of the clicked RadioGroup

                switch (groupId) {
                    case R.id.rbg_delivery_option_fragment:
                        if (checkedId == R.id.rb_pick_up_at_store_delivery_option_fragment) {
                            updateRadioButtons(rbPickUpAtStore, "pick_up_store");
                            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                                cv_radio_button_pick_up.setCardBackgroundColor(context.getColor(R.color.pressed_color_lighter));
                                cv_radio_button_hand_delivery.setCardBackgroundColor(context.getColor(R.color.White));
                                cv_radio_button_ship.setCardBackgroundColor(context.getColor(R.color.White));
                            }
//                            Toast.makeText(getActivity(),"selected pickup",Toast.LENGTH_SHORT).show();
                        }
                        break;

                    case R.id.rbg_delivery_option_ubber: // Uber Rush
                        if (checkedId == R.id.rb_uber_rush_delivery_option_fragment) {
                            updateRadioButtons(rbUberRush, "Ubber_Rush");
                        }
                        break;

                    case R.id.rbg_delivery_option_hand: // Hand Delivery
                        if (checkedId == R.id.rb_hand_on_delivery_delivery_option_fragment) {
                            updateRadioButtons(rbHandOnDelivery, "Hand Delivery");
                            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                                cv_radio_button_hand_delivery.setCardBackgroundColor(context.getColor(R.color.pressed_color_lighter));
                                cv_radio_button_pick_up.setCardBackgroundColor(context.getColor(R.color.White));
                                cv_radio_button_ship.setCardBackgroundColor(context.getColor(R.color.White));
                            }
//                            Toast.makeText(getActivity(),"selected Hand Delivery",Toast.LENGTH_SHORT).show();
                        }
                        break;

                    case R.id.rbg_delivery_option_ship: // Ship
                        if (checkedId == R.id.rb_ship_delivery_option_fragment) {
                            updateRadioButtons(rbShip, "Ship");
                            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                                cv_radio_button_ship.setCardBackgroundColor(context.getColor(R.color.pressed_color_lighter));
                                cv_radio_button_pick_up.setCardBackgroundColor(context.getColor(R.color.White));
                                cv_radio_button_hand_delivery.setCardBackgroundColor(context.getColor(R.color.White));
                            }
//                            Toast.makeText(getActivity(),"selected Ship",Toast.LENGTH_SHORT).show();
                        }
                        break;

                    default:
                        resetRadioButtons();
                        break;
                }
            } finally {
                isHandlingChange = false;
            }
        }
    };

    private void updateRadioButtons(RadioButton selectedButton, String message) {
        rbPickUpAtStore.setChecked(selectedButton == rbPickUpAtStore);
        rbUberRush.setChecked(selectedButton == rbUberRush);
        rbHandOnDelivery.setChecked(selectedButton == rbHandOnDelivery);
        rbShip.setChecked(selectedButton == rbShip);
        cvRadioButton.setBackground(null);

        if (selectedButton == rbPickUpAtStore){
            rbShip.setChecked(false);
            rbHandOnDelivery.setChecked(false);
            rbUberRush.setChecked(false);
            rbg_delivery_option_hand.clearCheck();
            rbg_delivery_option_ubber.clearCheck();
            rbg_delivery_option_ship.clearCheck();
            cv_ship.setVisibility(View.GONE);
            cv_radio_button_pick_up_sub_menu.setVisibility(View.VISIBLE);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                cv_radio_button_pick_up_sub_menu.setCardBackgroundColor(context.getColor(R.color.pressed_color_lighter_2_tone_light));
            }
            llPickUpAtStore.setVisibility(View.VISIBLE);

            if (liShippingData.get(0).getBSSetupPayAtStore()) {
                rbPayAtStore.setChecked(true);
            }else{
                rbPayWithCart.setChecked(true);
            }

            if (!tvWarnTwoHourAgo.getText().toString().isEmpty()) {
                if (liShippingData.get(0).getBSSetupPayAtStore()) {
                    tvWarnTwoHourAgo.setVisibility(View.VISIBLE);
                } else {
                    tvWarnTwoHourAgo.setVisibility(View.GONE);
                }
            } else {
                tvWarnTwoHourAgo.setVisibility(View.GONE);
            }

//                if (isStoreClosed && !liShippingData.get(0).getDontAcceptOrder()) {
//                    tvMinimumAmountRadioGroupHandDelivery.setVisibility(View.VISIBLE);
//                } else {
//                    tvMinimumAmountRadioGroupHandDelivery.setVisibility(View.GONE);
//                }

            cbx_hand_delivery.setVisibility(View.GONE);
            cbxShip.setVisibility(View.GONE);
            ll_shipping_Service.setVisibility(View.GONE);

            txtBillingfirstview.setVisibility(View.GONE);
            cvBillingOrShippingDetails.setVisibility(View.GONE);
            txtDeliveryDetails.setVisibility(View.GONE);
            txtAddressLists.setVisibility(View.GONE);
            tvClear.setVisibility(View.GONE);
            cvDeliveryOption.setVisibility(View.GONE);
            cv_radio_button_hand_delivery_Check_box.setVisibility(View.GONE);
            cbxShip.setChecked(false);
            cbx_hand_delivery.setChecked(false);
            btnNext.setText("NEXT");
        }
        else if (selectedButton == rbUberRush){
            rbShip.setChecked(false);
            rbHandOnDelivery.setChecked(false);
            rbPickUpAtStore.setChecked(false);
            rgMain.clearCheck();
            rbg_delivery_option_hand.clearCheck();
            rbg_delivery_option_ship.clearCheck();
            cv_ship.setVisibility(View.GONE);
            cv_radio_button_pick_up_sub_menu.setVisibility(View.GONE);
            cv_radio_button_hand_delivery_Check_box.setVisibility(View.GONE);
            llPickUpAtStore.setVisibility(View.GONE);
            tvWarnTwoHourAgo.setVisibility(View.GONE);
            cbx_hand_delivery.setVisibility(View.GONE);
            cbxShip.setVisibility(View.GONE);
            ll_shipping_Service.setVisibility(View.GONE);
            txtBillingfirstview.setVisibility(View.GONE);
            cvBillingOrShippingDetails.setVisibility(View.GONE);
            txtDeliveryDetails.setVisibility(View.GONE);
            txtAddressLists.setVisibility(View.GONE);
            tvClear.setVisibility(View.GONE);
            cvDeliveryOption.setVisibility(View.GONE);
            cbxShip.setChecked(false);
            cbx_hand_delivery.setChecked(false);
            btnNext.setText("NEXT");
        }
        else if (selectedButton == rbHandOnDelivery){
            rbShip.setChecked(false);
            rbUberRush.setChecked(false);
            rbPickUpAtStore.setChecked(false);
            rbg_delivery_option_ubber.clearCheck();
            rgMain.clearCheck();
            rbg_delivery_option_ship.clearCheck();
            cv_ship.setVisibility(View.GONE);

            if (_minimumHandDeliveryLimit > _total) {


                cv_radio_button_hand_delivery_Check_box.setVisibility(View.VISIBLE);
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    cv_radio_button_hand_delivery_Check_box.setCardBackgroundColor(context.getColor(R.color.pressed_color_lighter_2_tone_light));
                }
                cbx_hand_delivery.setVisibility(View.VISIBLE);
                cbx_hand_delivery.setText("Accept $" + liShippingData.get(0).getSurchargePrice() + " Delivery Fee since the order is below our minimum free delivery.");//janvi


                txtDeliveryDetails.setVisibility(View.GONE);
                txtAddressLists.setVisibility(View.GONE);
                tvClear.setVisibility(View.GONE);
                cvDeliveryOption.setVisibility(View.GONE);

                btnNext.setText("CONTINUE SHOPPING");
            } else {
                txtDeliveryDetails.setVisibility(View.VISIBLE);
                txtAddressLists.setVisibility(View.VISIBLE);
                tvClear.setVisibility(View.VISIBLE);
                cvDeliveryOption.setVisibility(View.VISIBLE);
                txtDeliveryDetails.setText(getString(R.string.lbl_delivery_details));
                btnNext.setText("NEXT");
            }


            cbxShip.setVisibility(View.GONE);
            ll_shipping_Service.setVisibility(View.GONE);
            cbxShip.setChecked(false);
            cv_radio_button_pick_up_sub_menu.setVisibility(View.GONE);
            llPickUpAtStore.setVisibility(View.GONE);
            tvWarnTwoHourAgo.setVisibility(View.GONE);
            txtBillingfirstview.setVisibility(View.GONE);
            cvBillingOrShippingDetails.setVisibility(View.GONE);


        }
        else if (selectedButton ==  rbShip){
            rbHandOnDelivery.setChecked(false);
            rbUberRush.setChecked(false);
            rbPickUpAtStore.setChecked(false);
            rbg_delivery_option_ubber.clearCheck();
            rgMain.clearCheck();
            rbg_delivery_option_hand.clearCheck();

            callShippingWService();
            Log.e("callShippingWService", "callShippingWService: 6");
            cbxShip.setVisibility(View.VISIBLE);
            txtBillingfirstview.setVisibility(View.VISIBLE);
            cvBillingOrShippingDetails.setVisibility(View.VISIBLE);
            txtBillingfirstview.setText(getString(R.string.lbl_billing_shipping_details));

            llPickUpAtStore.setVisibility(View.GONE);
            cv_radio_button_pick_up_sub_menu.setVisibility(View.GONE);
            cv_radio_button_hand_delivery_Check_box.setVisibility(View.GONE);
            tvWarnTwoHourAgo.setVisibility(View.GONE);
            cbx_hand_delivery.setVisibility(View.GONE);
            txtDeliveryDetails.setVisibility(View.GONE);
            txtAddressLists.setVisibility(View.GONE);
            tvClear.setVisibility(View.GONE);
            cvDeliveryOption.setVisibility(View.GONE);
            btnNext.setText("NEXT");
            cbx_hand_delivery.setChecked(false);
        }
    }

    private void resetRadioButtons() {
        rbPickUpAtStore.setChecked(false);
        rbUberRush.setChecked(false);
        rbHandOnDelivery.setChecked(false);
        rbShip.setChecked(false);
        cv_ship.setVisibility(View.GONE);

    }

    RadioGroup.OnCheckedChangeListener subListener = new RadioGroup.OnCheckedChangeListener() {
        @Override
        public void onCheckedChanged(RadioGroup group, int checkedId) {
            if (isHandlingChange2) {
                return;
            }

            isHandlingChange2 = true;

            try {
                if (checkedId == R.id.rb_pay_at_store_delivery_option_fragment) {
                    rbPayAtStore.setChecked(true);
                    rbPickUpAtStore.setChecked(true);
                } else if (checkedId == R.id.rb_pay_with_cart_delivery_option_fragment) {
                    rbPayWithCart.setChecked(true);
                    rbPickUpAtStore.setChecked(true);
                }
            } finally {
                isHandlingChange2 = false;
            }
        }
    };

//    /**
//     * Radio Button Listener
//     **/
//    @SuppressLint({"ResourceAsColor", "SuspiciousIndentation"})
//    @Override
//    public void onCheckedChanged(RadioGroup radioGroup, int i) {
//        DecimalFormat df = new DecimalFormat("####0.00");
//        df.setMaximumFractionDigits(2);
//
//        if (rbPickUpAtStore.isChecked()) {
//            cv_ship.setVisibility(View.GONE);
//
//            //add only below line to display default pay at store option
//            int selectedId = radioGroup.getCheckedRadioButtonId();
//            if(selectedId==R.id.rb_pick_up_at_store_delivery_option_fragment){
//                rbPayAtStore.setChecked(true);
//            }
////            Edited by Varun for unwanted pop-up comes when we select the pick up store and then click the next button
//
//            if (liShippingData.get(0).getBSSetupPayAtStore()){
//                if(selectedId==R.id.rb_pick_up_at_store_delivery_option_fragment){
//                    rbPayAtStore.setChecked(true);
//                    cv_ship.setVisibility(View.GONE);
//                }
//            }else {
////                if (selectedId == R.id.rb_pay_with_cart_delivery_option_fragment) {
//                rbPayWithCart.setChecked(true);
////                }
//            }
////            END
//            //end
//
//            cvRadioButton.setCardBackgroundColor(null);
//            llPickUpAtStore.setVisibility(View.VISIBLE);
//
//            if (!tvWarnTwoHourAgo.getText().toString().isEmpty()) {
////                Edited by Varun for store closes textview above the rbpayatstore will only show when the rbpayat store are enable from the back side
////                And it will not show when the rbpayatstore will disable from the set-up
//                if (liShippingData.get(0).getBSSetupPayAtStore()) {
//                    tvWarnTwoHourAgo.setVisibility(View.VISIBLE);
//                } else {
//                    tvWarnTwoHourAgo.setVisibility(View.GONE);
//                }
//            }else{
//                tvWarnTwoHourAgo.setVisibility(View.GONE);
//            }
////                END
////            tvWarnTwoHourAgo.setVisibility(View.VISIBLE);
//
////            hide this by me
//            if(isStoreClosed && !liShippingData.get(0).getDontAcceptOrder()){
////                tvRadioGroupHandDelivery.setVisibility(View.VISIBLE);
//                tvMinimumAmountRadioGroupHandDelivery.setVisibility(View.VISIBLE);
//            }else{
////                tvRadioGroupHandDelivery.setVisibility(View.GONE);
//                tvMinimumAmountRadioGroupHandDelivery.setVisibility(View.GONE);
//            }
////
//            cbx_hand_delivery.setVisibility(View.GONE); ////Edited by Janvi on 29th Sep * end ***//
//            cbxShip.setVisibility(View.GONE);
//            ll_shipping_Service.setVisibility(View.GONE);
//
//            txtBillingfirstview.setVisibility(View.GONE);
//            cvBillingOrShippingDetails.setVisibility(View.GONE);
////            ll_temp.setVisibility(View.GONE);
//            txtDeliveryDetails.setVisibility(View.GONE);
//            txtAddressLists.setVisibility(View.GONE);
//            tvClear.setVisibility(View.GONE);
//            cvDeliveryOption.setVisibility(View.GONE);
//            cbxShip.setChecked(false);
//            cbx_hand_delivery.setChecked(false);//Edited by Janvi on 29th Sep * end ***//
//            btnNext.setText("NEXT");
//
//        } else if (rbUberRush.isChecked()) {
//
//            cvRadioButton.setCardBackgroundColor(null);
//            llPickUpAtStore.setVisibility(View.GONE);
//            tvWarnTwoHourAgo.setVisibility(View.GONE);
////            tvRadioGroupHandDelivery.setVisibility(View.GONE);
////            tvMinimumAmountRadioGroupHandDelivery.setVisibility(View.GONE);
//            cbx_hand_delivery.setVisibility(View.GONE); ////Edited by Janvi on 29th Sep * end ***//
//            cbxShip.setVisibility(View.GONE);
//            ll_shipping_Service.setVisibility(View.GONE);
//            txtBillingfirstview.setVisibility(View.GONE);
//            cvBillingOrShippingDetails.setVisibility(View.GONE);
////            ll_temp.setVisibility(View.GONE);
//            txtDeliveryDetails.setVisibility(View.GONE);
//            txtAddressLists.setVisibility(View.GONE);
//            tvClear.setVisibility(View.GONE);
//            cvDeliveryOption.setVisibility(View.GONE);
//            cbxShip.setChecked(false);
//            cbx_hand_delivery.setChecked(false);////Edited by Janvi on 29th Sep * end ***//
//            btnNext.setText("NEXT");
//
//        } else if (rbHandOnDelivery.isChecked()) {
//            cvRadioButton.setCardBackgroundColor(null);
//            cv_ship.setVisibility(View.GONE);
//
//            if (_minimumHandDeliveryLimit > _total) {
//
////                below line commented by me
////                if (tvStoreClosed.getVisibility() != View.VISIBLE) {
//                tvMinimumAmountRadioGroupHandDelivery.setVisibility(View.VISIBLE);
//
////                    Edited by Janvi 29th sep **
////                Edited by Varun for the checkbox will show or not base on the set-up selection
////                if (liShippingData.get(0).getAllowSurchargeDelivery()){
////                    cbx_hand_delivery.setVisibility(View.VISIBLE);
////                    cbx_hand_delivery.setText("Accept $ " + liShippingData.get(0).getSurchargePrice() + " Delivery Fee since the order is below our minimum free delivery.");//janvi
////                }else{
////                    cbx_hand_delivery.setVisibility(View.GONE);
////                }
//                cbx_hand_delivery.setVisibility(View.VISIBLE);
//                cbx_hand_delivery.setText("Accept $ " + liShippingData.get(0).getSurchargePrice() + " Delivery Fee since the order is below our minimum free delivery.");//janvi
//                //end *********
////                }
//
//                txtDeliveryDetails.setVisibility(View.GONE);
//                txtAddressLists.setVisibility(View.GONE);
//                tvClear.setVisibility(View.GONE);
//                cvDeliveryOption.setVisibility(View.GONE);
//
//                btnNext.setText("CONTINUE SHOPPING");
//            } else {
//                //this below two lines are commented because display unnecessary sapce above ship
////                if (tvStoreClosed.getVisibility() != View.VISIBLE) {
////                    tvRadioGroupHandDelivery.setVisibility(View.VISIBLE);
////                }
//                txtDeliveryDetails.setVisibility(View.VISIBLE);
//                txtAddressLists.setVisibility(View.VISIBLE);
//                tvClear.setVisibility(View.VISIBLE);
//                cvDeliveryOption.setVisibility(View.VISIBLE);
//                txtDeliveryDetails.setText(getString(R.string.lbl_delivery_details));
//                btnNext.setText("NEXT");
//            }
//
//            //tvRadioGroupHandDelivery.setVisibility(View.VISIBLE);
//            //tvMinimumAmountRadioGroupHandDelivery.setVisibility(View.VISIBLE);
//            //lblDeliveryDetails.setVisibility(View.VISIBLE);
//            //lblAddressLists.setVisibility(View.VISIBLE);
//            //tvClear.setVisibility(View.VISIBLE);
//            //cvDeliveryOption.setVisibility(View.VISIBLE);
//            //lblDeliveryDetails.setText(getString(R.string.lbl_delivery_details));
//
//            cbxShip.setVisibility(View.GONE);
//            ll_shipping_Service.setVisibility(View.GONE);
//            cbxShip.setChecked(false);
//            llPickUpAtStore.setVisibility(View.GONE);
//            //llPickUpAtStore.setVisibility(View.GONE);
//            tvWarnTwoHourAgo.setVisibility(View.GONE);
//            txtBillingfirstview.setVisibility(View.GONE);
//            cvBillingOrShippingDetails.setVisibility(View.GONE);
////            ll_temp.setVisibility(View.GONE);
//
//            if (btnNext.getText().toString().trim().equals("NEXT")) {
//                String Url1 = Constant.WS_BASE_URL + Constant.GET_DELI_ZIP_CODES + Constant.STOREID;
//                TaskGetZipCode taskzipcode = new TaskGetZipCode(getActivity(), this);
//                taskzipcode.execute(Url1);
//            }
//        } else if (rbShip.isChecked()) {
//
////            spinnerShippingService.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
////                @Override
////                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
////
////                }
////
////                @Override
////                public void onNothingSelected(AdapterView<?> parent) {
////
////                }
////            });
////            if(Constant.STOREID.equalsIgnoreCase("707") || Constant.STOREID.equalsIgnoreCase("7365")) {
//            callShippingWService();
//            Log.e("callShippingWService", "callShippingWService: 6" );
////                ll_shipping_Service.setVisibility(View.VISIBLE); // comment when try to check shipping
////            }
//            cvRadioButton.setCardBackgroundColor(null);
//            cbxShip.setVisibility(View.VISIBLE);
//            txtBillingfirstview.setVisibility(View.VISIBLE);
//            cvBillingOrShippingDetails.setVisibility(View.VISIBLE);
////            ll_temp.setVisibility(View.VISIBLE);
//            txtBillingfirstview.setText(getString(R.string.lbl_billing_shipping_details));
//
//            llPickUpAtStore.setVisibility(View.GONE);
//            tvWarnTwoHourAgo.setVisibility(View.GONE);
////            tvRadioGroupHandDelivery.setVisibility(View.GONE);
////            tvMinimumAmountRadioGroupHandDelivery.setVisibility(View.GONE);
//            cbx_hand_delivery.setVisibility(View.GONE); //Edited by Janvi on 29th Sep * end ***//
//            txtDeliveryDetails.setVisibility(View.GONE);
//            txtAddressLists.setVisibility(View.GONE);
//            tvClear.setVisibility(View.GONE);
//            cvDeliveryOption.setVisibility(View.GONE);
//            btnNext.setText("NEXT");
//            cbx_hand_delivery.setChecked(false);//Edited by Janvi on 29th Sep * end ***//
//        } else {
//
//            llPickUpAtStore.setVisibility(View.GONE);
//            tvWarnTwoHourAgo.setVisibility(View.GONE);
//            //llPickUpAtStore.setVisibility(View.GONE);
//            tvRadioGroupHandDelivery.setVisibility(View.GONE);
//            tvMinimumAmountRadioGroupHandDelivery.setVisibility(View.GONE);
//            cbx_hand_delivery.setVisibility(View.GONE); ////Edited by Janvi on 29th Sep * end ***//
//            cbxShip.setVisibility(View.GONE);
//            ll_shipping_Service.setVisibility(View.GONE);
//            txtBillingfirstview.setVisibility(View.GONE);
//            cvBillingOrShippingDetails.setVisibility(View.GONE);
////            ll_temp.setVisibility(View.GONE);
//            txtDeliveryDetails.setVisibility(View.GONE);
//            txtAddressLists.setVisibility(View.GONE);
//            tvClear.setVisibility(View.GONE);
//            cvDeliveryOption.setVisibility(View.GONE);
//            cbxShip.setChecked(false);
//            cbx_hand_delivery.setChecked(false);//Edited by Janvi on 29th Sep * end ***//
//            btnNext.setText("NEXT");
//        }
//    }

    public void callShippingWService() {

//        if (etBZIpDO.getText().length()==5 || etZIpDO.getText().length()==5){
//            String url = Constant.WS_BASE_URL + Constant.GET_SHIPPING_SERVICE_BY_STORENO + Constant.STOREID ;
//
//            TaskShippingServiceDetails taskShippingServiceDetails = new TaskShippingServiceDetails(this,getActivity());
//            taskShippingServiceDetails.execute(url);
//        }else{
//            Toast.makeText(context, "Invalid Zip code", Toast.LENGTH_SHORT).show();
//        }


        etBZIpDO.clearFocus();
        etZIpDO.clearFocus();

        if (rbShip.isChecked()){
            cv_ship.setVisibility(View.VISIBLE);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                cv_ship.setCardBackgroundColor(context.getColor(R.color.pressed_color_lighter_2_tone_light));
            }
        }

        String url = Constant.WS_BASE_URL + Constant.GET_SHIPPING_SERVICE_BY_STORENO + Constant.STOREID ;

        TaskShippingServiceDetails taskShippingServiceDetails = new TaskShippingServiceDetails(this,getActivity());
//        taskShippingServiceDetails.execute(url);
        taskShippingServiceDetails.executeOnExecutor(TaskShippingServiceDetails.THREAD_POOL_EXECUTOR,url);


    }


    @Override
    public void onShippingServiceDetailsResult(ShippingInfo shippingInfo) {

        if(shippingInfo != null){

//            if(!shippingInfo.getShippingType().isEmpty() && shippingInfo.getShippingType().equalsIgnoreCase("Auto")){
//                ll_shipping_Service.setVisibility(View.VISIBLE);
//            }

            // ************** Edited by Varun ****************

            // for shipping rate calculator setup enable and disable from Website

            if ( shippingInfo.getShippingType().equalsIgnoreCase("auto")){

                if (shippingInfo.getFedEx().equalsIgnoreCase("true") ||
                        shippingInfo.getUsps().equalsIgnoreCase("true") ||
                        shippingInfo.getUps().equalsIgnoreCase("true")) {
                    ll_shipping_Service.setVisibility(View.VISIBLE);
                } else {
                    ll_shipping_Service.setVisibility(View.GONE);
                }

            }else if (shippingInfo.getShippingType().equalsIgnoreCase("flat")){
                ll_shipping_Service.setVisibility(View.GONE);
                callfalt(getActivity());

            }
//            if (shippingInfo.getShippingType().equalsIgnoreCase("Flat")){
//                ll_shipping_Service.setVisibility(View.GONE);
//            }else if (shippingInfo.getShippingType().equalsIgnoreCase("Auto")){
//                ll_shipping_Service.setVisibility(View.VISIBLE);
//            }

            //****************END*****************

            ArrayAdapter ShippingOptionadapter = null;
            List<String> uPSServiceNameList = new ArrayList<>();
            ArrayList<String> uPSServiceIDList = new ArrayList<>();
            ArrayList<String> fedExServiceList = new ArrayList<>();
            ArrayList<String> fedExServiceIDList = new ArrayList<>();
            ArrayList<String> uSPSServiceList = new ArrayList<>();
            ArrayList<String> uSPSServiceIDList = new ArrayList<>();

            // set values to parameters for calling webservice
            String customerId = "0";
            if (UserModel.Cust_mst_ID != null)
                customerId = UserModel.Cust_mst_ID;
            else
                customerId = "0";

            //            if (cbxShip.isChecked()) {
//                sdf = etZIpDO.getText().toString().trim();
//            }else{
//                sdf= etBZIpDO.getText().toString().trim()
//            }

            String Zipval = "";
            if (rbShip.isChecked() && cbxShip.isChecked()) {
//                Zipval = zipforShipping;
                Zipval =etZIpDO.getText().toString().trim() ;
            }else{
//                Zipval = Constant.customerData.getZip();
                Zipval = etBZIpDO.getText().toString().trim();
            }

            String contrycode = "US";

            String toaddress = "";
            if (rbShip.isChecked() && cbxShip.isChecked()) {
//                toaddress = addressForshipping;
                toaddress = etAddressOneDO.getText().toString().trim();
            }else{
//                toaddress = Constant.customerData.getAddress1();
                toaddress = etBAddressOneDO.getText().toString().trim();
            }

            String toCity = "";
            if (rbShip.isChecked() && cbxShip.isChecked()) {
//                toCity = cityForshipping;
                toCity = etCityDo.getText().toString().trim();
            }else{
//                toCity = Constant.customerData.getCity();
                toCity = etBCityDo.getText().toString().trim();
            }

            String toState = "";
            if (rbShip.isChecked() && cbxShip.isChecked()) {
//                toState = stateForshipping;
                toState = etStateDO.getText().toString().trim();
            }else{
//                toState = Constant.customerData.getState();
                toState = etBStateDO.getText().toString().trim();
            }

            if (Constant.BAddressOne.equals(toaddress)){
                toState=Constant.BState;
                toCity=Constant.BCity;
//                Zipval=Constant.BZip;
            }else if (Constant.AddressOne.equals(toaddress)){
                toState=Constant.State;
                toCity=Constant.City;
//                Zipval=Constant.Zip;


            }

            Constant.BZip="";
            Constant.BAddressOne="";
            Constant.BState="";
            Constant.BCity="";
            Constant.Zip="";
            Constant.AddressOne="";
            Constant.State="";
            Constant.City="";
//            Constant.Custormer_Id="";
//            Constant.additional_charges="";
//            Constant.Selected_ID="";
//            Constant.Service_name="";

            // end **************

            String default_shipping;
            default_shipping = shippingInfo.getDefaultShipping();

            if(default_shipping.equalsIgnoreCase("UPS")){
                if(shippingInfo.getUPSServiceList() != null && shippingInfo.getUPSServiceList().size() > 0){

                    for(int i =0; i<shippingInfo.getUPSServiceList().size() ;i++) {
                        uPSServiceNameList.add(shippingInfo.getUPSServiceList().get(i).getServiceName());
                        uPSServiceIDList.add(shippingInfo.getUPSServiceList().get(i).getService());
                    }

                    ShippingOptionadapter = new ArrayAdapter(context, R.layout.simple_spinner_shipping_service, uPSServiceNameList);
                    ShippingOptionadapter.setDropDownViewResource(R.layout.simple_spinner_shipping_service);
                    spinnerShippingService.setAdapter(ShippingOptionadapter);
                    if(Constant.selectedItemPos_UPSSpinner != 0){
                        spinnerShippingService.setSelection(Constant.selectedItemPos_UPSSpinner);
                    }

//                        String Url = Constant.WS_BASE_URL + Constant.CALCULATE_SHIPPING_RATES_UPS + "/" +  customerId +"/" + Constant.STOREID+ "/" + shippingInfo.getAdditionalCharges() + "/" + Zipval + "/" + contrycode + "/" + uPSServiceIDList.get(0) + "/"
//                               + toaddress.trim() + "/" + toCity.trim() + "/" + toState.trim();
//
//                        ShippingRatesModel taskShippingRates = new ShippingRatesModel(this,getActivity(),Constant.CALCULATE_SHIPPING_RATES_UPS);
//                        taskShippingRates.execute(Url);

                }
            }else if(default_shipping.equalsIgnoreCase("FedEx")){

                if(shippingInfo.getFedExServiceList() != null && shippingInfo.getFedExServiceList().size() > 0){

                    for(int i =0; i<shippingInfo.getFedExServiceList().size() ;i++) {
                        fedExServiceList.add(shippingInfo.getFedExServiceList().get(i).getServiceName());
                        fedExServiceIDList.add(shippingInfo.getFedExServiceList().get(i).getService());
                    }

                    ShippingOptionadapter = new ArrayAdapter(context, R.layout.simple_spinner_shipping_service, fedExServiceList);
                    ShippingOptionadapter.setDropDownViewResource(R.layout.simple_spinner_shipping_service);
                    spinnerShippingService.setAdapter(ShippingOptionadapter);

                    if(Constant.selectedItemPos_FedexSpinner != 0){
                        spinnerShippingService.setSelection(Constant.selectedItemPos_FedexSpinner);
                    }
                }

            }else if(default_shipping.equalsIgnoreCase("USPS")){

                if(shippingInfo.getUSPSServiceList() != null && shippingInfo.getUSPSServiceList().size() > 0){

                    for(int i =0; i<shippingInfo.getUSPSServiceList().size() ;i++) {
                        uSPSServiceList.add(shippingInfo.getUSPSServiceList().get(i).getServiceName());
                        uSPSServiceIDList.add(shippingInfo.getUSPSServiceList().get(i).getService());
                    }

                    ShippingOptionadapter = new ArrayAdapter(context, R.layout.simple_spinner_shipping_service, uSPSServiceList);
                    ShippingOptionadapter.setDropDownViewResource(R.layout.simple_spinner_shipping_service);
                    spinnerShippingService.setAdapter(ShippingOptionadapter);
                    if(Constant.selectedItemPos_USPSSpinner != 0){
                        spinnerShippingService.setSelection(Constant.selectedItemPos_USPSSpinner);
                    }

                }

//                    String shipping_charges_txt = "";
//                    shipping_charges_txt = getResources().getString(R.string.shipping_charge_lbl)
//                            + " $" + shippingInfo.getAdditionalCharges()
//                            + " " + getResources().getString(R.string.based_on) + " ";
//
//                    tv_shipping_charges.setText(shipping_charges_txt);

//                        shipping_option = (spinnerShippingService.getSelectedItem().toString().isEmpty()) ? "0" : spinnerShippingService.getSelectedItem().toString();

                //                        ArrayAdapter<UPSServiceModel> ShippingOptionadapter =
//                                new CustomShippingServiceArrayAdapter<UPSServiceModel>(context, R.layout.simple_spinner_shipping_service, uPSServiceList);
//                        ShippingOptionadapter.setDropDownViewResource(R.layout.simple_spinner_shipping_service);
//                        spinnerShippingService.setAdapter(ShippingOptionadapter);

//                        ArrayAdapter<CharSequence> ShippingOptionadapter =  new ArrayAdapter<CharSequence>(context, R.layout.simple_spinner_shipping_service, uPSServiceNameList);

            }

            String finalCustomerId = customerId;
            String finalZipval = Zipval;
            String finalToaddress = toaddress;
            String finalToCity = toCity;
            String finalToState = toState;
            spinnerShippingService.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

//                    int getid = parent.getSelectedItemPosition();   ///get the selected element place id
//                    Toast.makeText(context,"Position of selected element: "+String.valueOf(getid),Toast.LENGTH_LONG).show();
//
//                    String getvalue = String.valueOf(parent.getItemAtPosition(position));   // getting the selected element value
//                    Toast.makeText(context,"Value of Selected Spinner : "+getvalue,Toast.LENGTH_LONG).show();

//                    ***************** Edited by Varun for shipping charges ********************
                    String getvalue = String.valueOf(parent.getItemAtPosition(position));   // getting the selected element value
//                    Toast.makeText(context,"Value of Selected Spinner : "+getvalue,Toast.LENGTH_LONG).show();

                    S_name = getvalue ;

//                    ******************* END ****************************

                    String selectedID = "0";

                    if(default_shipping.equalsIgnoreCase("UPS")) {

                        selectedID = String.valueOf(parent.getSelectedItemPosition());

                        int selectedNamepos = parent.getSelectedItemPosition();

                        if (uPSServiceIDList.size() > selectedNamepos) {
                            selectedID = uPSServiceIDList.get(selectedNamepos);
                        }

                        Constant.selectedItemPos_UPSSpinner = parent.getSelectedItemPosition();

                        callShippingRateWS(finalCustomerId,shippingInfo.getAdditionalCharges(), finalZipval,contrycode,selectedID, finalToaddress, finalToCity, finalToState,"UPS");

                    }
                    else if(default_shipping.equalsIgnoreCase("FedEx")){

                        selectedID = String.valueOf(parent.getSelectedItemPosition());

                        int selectedNamepos = parent.getSelectedItemPosition();

                        if (fedExServiceIDList.size() > selectedNamepos) {
                            selectedID = fedExServiceIDList.get(selectedNamepos);
                        }

                        Constant.selectedItemPos_FedexSpinner = parent.getSelectedItemPosition();

                        callShippingRateWS(finalCustomerId,shippingInfo.getAdditionalCharges(), finalZipval,contrycode,selectedID, finalToaddress, finalToCity, finalToState,"FedEx");
                    }
                    else if(default_shipping.equalsIgnoreCase("USPS")){

                        selectedID = String.valueOf(parent.getSelectedItemPosition());

                        int selectedNamepos = parent.getSelectedItemPosition();

                        if (uSPSServiceIDList.size() > selectedNamepos) {
                            selectedID = uSPSServiceIDList.get(selectedNamepos);
                        }

                        Constant.selectedItemPos_USPSSpinner = parent.getSelectedItemPosition();

                        callShippingRateWS(finalCustomerId,shippingInfo.getAdditionalCharges(), finalZipval,contrycode,selectedID, finalToaddress, finalToCity, finalToState,"USPS");
                    }

                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });
//
        }
    }

    //    ******************* Edited by Varun for shipping charges *************************
    private void callfalt(FragmentActivity activity) {
        if (activity!=null){
            String Url = Constant.WS_BASE_URL + Constant.GETSHIPPING_FLAT_RATES_BYSTORENO + _total +"/"+Constant.STOREID;
            TaskShippingFlatRateByStoreNo taskShippingFlatRateByStoreNo = new TaskShippingFlatRateByStoreNo(this,getActivity(),Constant.GETSHIPPING_FLAT_RATES_BYSTORENO);
//            taskShippingFlatRateByStoreNo.execute(Url);
            taskShippingFlatRateByStoreNo.executeOnExecutor(TaskShippingFlatRateByStoreNo.THREAD_POOL_EXECUTOR,Url);
        }
    }

    @Override
    public void onShippingFlatRateResult(ShippingRatesModel shippingRatesModel) {

//        DecimalFormat df = new DecimalFormat("####0.00");
//        df.setMaximumFractionDigits(2);
//
//        String shipping_charges_txt = "";
//
//        shipping_charges_txt = " $" + df.format(shippingRatesModel.getShippingRate());

        _shipping= Float.parseFloat(shippingRatesModel.getShippingRate());

    }

//    ************* END *******************


    private void callShippingRateWS(String finalCustomerId, String additionalCharges, String finalZipval, String contrycode, String selectedID, String finalToaddress, String finalToCity, String finalToState, String servicename) {

//            Constant.Custormer_Id=finalCustomerId;
//            Constant.additional_charges=additionalCharges;
//            Constant.Selected_ID=selectedID;
//            Constant.Service_name=servicename;

        if(servicename.equalsIgnoreCase("UPS")){
            String Url = Constant.WS_BASE_URL + Constant.CALCULATE_SHIPPING_RATES_UPS_V1 + "/" +  finalCustomerId +"/" + Constant.STOREID+ "/" + additionalCharges + "/" + finalZipval + "/" + contrycode + "/" + selectedID + "/"
                    + finalToaddress.trim() + "/" + finalToCity.trim() + "/" + finalToState.trim() + "/" + Constant.cartcount;

            TaskShippingRates taskShippingRates = new TaskShippingRates(this,getActivity(),Constant.CALCULATE_SHIPPING_RATES_UPS, servicename);
//            taskShippingRates.execute(Url);
            taskShippingRates.executeOnExecutor(TaskShippingRates.THREAD_POOL_EXECUTOR,Url);

        }
        else if(servicename.equalsIgnoreCase("FedEx")){

            String Url = Constant.WS_BASE_URL + Constant.CALCULATE_SHIPPING_RATES_FEDEX_V1 + "/" +  finalCustomerId +"/" + Constant.STOREID+ "/" + additionalCharges + "/" + finalZipval + "/" + contrycode + "/" + selectedID + "/"
                    + finalToaddress.trim() + "/" + finalToCity.trim() + "/" + finalToState.trim() + "/" + Constant.cartcount;

            TaskShippingRates taskShippingRates = new TaskShippingRates(this,getActivity(),Constant.CALCULATE_SHIPPING_RATES_FEDEX,servicename);
//            taskShippingRates.execute(Url);
            taskShippingRates.executeOnExecutor(TaskShippingRates.THREAD_POOL_EXECUTOR,Url);

        }
        else if(servicename.equalsIgnoreCase("USPS")){

            String formattedDate = Utils.getCurrentDate();

            String Url = Constant.WS_BASE_URL + Constant.CALCULATE_SHIPPING_RATES_USPS_V1 + "/" +  finalCustomerId +"/" + Constant.STOREID+ "/" + additionalCharges + "/" + finalZipval + "/" + contrycode + "/" + selectedID + "/"
                    +formattedDate + "/" + finalToaddress.trim() + "/" + finalToCity.trim() + "/" + finalToState.trim();

            TaskShippingRates taskShippingRates = new TaskShippingRates(this,getActivity(),Constant.CALCULATE_SHIPPING_RATES_USPS,servicename);
//            taskShippingRates.execute(Url);
            taskShippingRates.executeOnExecutor(TaskShippingRates.THREAD_POOL_EXECUTOR,Url);
        }
    }

    @Override
    public void onShippingRatesResult(CalculateShippingModel calculateShippingModel, String method, String servicename) {

//        Edited by Varun for shipping charges
        _shipping= Float.parseFloat(String.valueOf(calculateShippingModel.getShippingCharges()));
//        END


        DecimalFormat df = new DecimalFormat("####0.00");
        df.setMaximumFractionDigits(2);

        // for displayed shiiping charge text --------
        String shipping_charges_txt = "";

        if(calculateShippingModel != null) {

            if(servicename.equalsIgnoreCase("UPS")){

                if(calculateShippingModel.getResponse().equalsIgnoreCase("SUCCESS")){
                    shipping_charges_txt = getResources().getString(R.string.shipping_charge_lbl)
                            + " $" + df.format(calculateShippingModel.getShippingCharges())
                            + " " + getResources().getString(R.string.based_on) + " ";

                    ////                   ****************** Edited by Varun on 1 Aug 2022**************
                    Setzipcode(shipping_charges_txt,calculateShippingModel,"UPS");

                    //                  tv_shipping_charges.setText(shipping_charges_txt);
//                    ****************** END ***********************


                }else if(calculateShippingModel.getResponse().equalsIgnoreCase("Hard111210The requested service is unavailable between the selected locations.")){

                    if (rbShip.isChecked() && cbxShip.isChecked()) {
                        etCityDo.setText("");
                        etStateDO.setText("");
                        etZIpDO.setText("");
                    }else{
                        etBCityDo.setText("");
                        etBStateDO.setText("");
                        etBZIpDO.setText("");
                    }
                    shipping_charges_txt = calculateShippingModel.getResponse();
                    tv_shipping_charges.setText(shipping_charges_txt);

                }else if(calculateShippingModel.getResponse().equalsIgnoreCase("Hard9264030The state is not supported in the Customer Integration Environment.")) {

                    if (rbShip.isChecked() && cbxShip.isChecked()) {
                        etCityDo.setText("");
                        etStateDO.setText("");
                        etZIpDO.setText("");
                    }else{
                        etBCityDo.setText("");
                        etBStateDO.setText("");
                        etBZIpDO.setText("");
                    }
                    shipping_charges_txt = calculateShippingModel.getResponse();
                    tv_shipping_charges.setText(shipping_charges_txt);

                }else if(calculateShippingModel.getResponse().equalsIgnoreCase("Invalid Address")) {

                    if (rbShip.isChecked() && cbxShip.isChecked()) {
                        etCityDo.setText("");
                        etStateDO.setText("");
                        etZIpDO.setText("");
                    }else{
                        etBCityDo.setText("");
                        etBStateDO.setText("");
                        etBZIpDO.setText("");
                    }
                    shipping_charges_txt = calculateShippingModel.getResponse();
                    tv_shipping_charges.setText(shipping_charges_txt);

                }else{
                    shipping_charges_txt = calculateShippingModel.getResponse();
                    tv_shipping_charges.setText(shipping_charges_txt);
                }

            }else if(servicename.equalsIgnoreCase("FedEx")){

                if(calculateShippingModel.getResponse().equalsIgnoreCase("SUCCESS")){
                    shipping_charges_txt = getResources().getString(R.string.shipping_charge_lbl)
                            + " $" + df.format(calculateShippingModel.getShippingCharges())
                            + " " + getResources().getString(R.string.based_on) + " ";

                    Setzipcode(shipping_charges_txt,calculateShippingModel,"FedEx");

                }else if(calculateShippingModel.getResponse().equalsIgnoreCase("NOT RESIDENTIAL")){

                    Utils.showValidationDialog(getContext(), context.getResources().getString(R.string.fedexhome),"");

                }else if(calculateShippingModel.getResponse().equalsIgnoreCase("Destination postal code missing or invalid.")) {

                    if (rbShip.isChecked() && cbxShip.isChecked()) {
                        etCityDo.setText("");
                        etStateDO.setText("");
                        etZIpDO.setText("");
                    }else{
                        etBCityDo.setText("");
                        etBStateDO.setText("");
                        etBZIpDO.setText("");
                    }
                    shipping_charges_txt = calculateShippingModel.getResponse();
                    tv_shipping_charges.setText(shipping_charges_txt);

                }else if(calculateShippingModel.getResponse().equalsIgnoreCase("Invalid Address")) {

                    if (rbShip.isChecked() && cbxShip.isChecked()) {
                        etCityDo.setText("");
                        etStateDO.setText("");
                        etZIpDO.setText("");
                    }else{
                        etBCityDo.setText("");
                        etBStateDO.setText("");
                        etBZIpDO.setText("");
                    }
                    shipping_charges_txt = calculateShippingModel.getResponse();
                    tv_shipping_charges.setText(shipping_charges_txt);
                }else {
                    shipping_charges_txt = calculateShippingModel.getResponse();
                    tv_shipping_charges.setText(shipping_charges_txt);
                }

            }else if(servicename.equalsIgnoreCase("USPS")){

                if(calculateShippingModel.getResponse().equalsIgnoreCase("SUCCESS")){
                    shipping_charges_txt = getResources().getString(R.string.shipping_charge_lbl)
                            + " $" + df.format(calculateShippingModel.getShippingCharges())
                            + " " + getResources().getString(R.string.based_on) + " ";

                    Setzipcode(shipping_charges_txt,calculateShippingModel,"USPS");
//                    tv_shipping_charges.setText(shipping_charges_txt);

                }else if(calculateShippingModel.getResponse().equalsIgnoreCase("The Destination ZIP Code you have entered is invalid.")){

                    if (rbShip.isChecked() && cbxShip.isChecked()) {
                        etCityDo.setText("");
                        etStateDO.setText("");
                        etZIpDO.setText("");
                    }else{
                        etBCityDo.setText("");
                        etBStateDO.setText("");
                        etBZIpDO.setText("");
                    }
                    shipping_charges_txt = calculateShippingModel.getResponse();
                    tv_shipping_charges.setText(shipping_charges_txt);

                }else if(calculateShippingModel.getResponse().equalsIgnoreCase("ZIP Codes must be 5 digits")) {

                    if (rbShip.isChecked() && cbxShip.isChecked()) {
                        etCityDo.setText("");
                        etStateDO.setText("");
                        etZIpDO.setText("");
                    }else{
                        etBCityDo.setText("");
                        etBStateDO.setText("");
                        etBZIpDO.setText("");
                    }
                    shipping_charges_txt = calculateShippingModel.getResponse();
                    tv_shipping_charges.setText(shipping_charges_txt);
                }else{
                    shipping_charges_txt = calculateShippingModel.getResponse();
                    tv_shipping_charges.setText(shipping_charges_txt);

                }
            }
        }
    }

    private void Setzipcode(String shipping_charges_txt, CalculateShippingModel calculateShippingModelval, String shippingType) {

        CalculateShippingModel calculateShippingModel = null;

        calculateShippingModel = calculateShippingModelval;

        // below is working code for zip when checked shipping to different address

        if (rbShip.isChecked() && cbxShip.isChecked()) {
//            shipping_charges_txt = shipping_charges_txt + zipforShipping;

//            Edited by varun
//            after shipping charges get and then to set the Address

//            shipping_charges_txt = shipping_charges_txt + etZIpDO.getText().toString().trim();
            shipping_charges_txt = shipping_charges_txt + calculateShippingModel.getTozip();
            etZIpDO.clearFocus();
            etAddressOneDO.clearFocus();
            etAddressOneDO.dismissDropDown();
            Constant.Zip=calculateShippingModel.getTozip();
            Constant.AddressOne=calculateShippingModel.getToaddress();
            Constant.City=calculateShippingModel.getTocity();
            Constant.State=calculateShippingModel.getTostate();
            etZIpDO.setText(calculateShippingModel.getTozip());
            etCityDo.setText(calculateShippingModel.getTocity());
            etStateDO.setText(calculateShippingModel.getTostate());
            etAddressOneDO.setText(calculateShippingModel.getToaddress());
//            END

        } else {
//            shipping_charges_txt = shipping_charges_txt + Constant.customerData.getZip();

//            Edited by varun
//            for after shipping charges get and then to set the Address

//            shipping_charges_txt = shipping_charges_txt + etBZIpDO.getText().toString().trim();
            shipping_charges_txt = shipping_charges_txt + calculateShippingModel.getTozip();
            etBZIpDO.clearFocus();
            etBAddressOneDO.clearFocus();
            etBAddressOneDO.dismissDropDown();
            Constant.BZip=calculateShippingModel.getTozip();
            Constant.BAddressOne=calculateShippingModel.getToaddress();
            Constant.BCity=calculateShippingModel.getTocity();
            Constant.BState=calculateShippingModel.getTostate();
            etBZIpDO.setText(calculateShippingModel.getTozip());
            etBCityDo.setText(calculateShippingModel.getTocity());
            etBStateDO.setText(calculateShippingModel.getTostate());
            etBAddressOneDO.setText(calculateShippingModel.getToaddress());
//            END
        }

        // end --------------------------------------------
        SpannableString ss = new SpannableString(shipping_charges_txt);

        CalculateShippingModel finalCalculateShippingModel = calculateShippingModel;
        ClickableSpan clickableSpan = new ClickableSpan() {
            @Override
            public void onClick(View textView) {
//                    startActivity(new Intent(MyActivity.this, NextActivity.class));
//                    Toast.makeText(context,"check::",Toast.LENGTH_LONG).show();
                Utils.zipcodeShippingDetails(context, finalCalculateShippingModel,shippingType);
            }
            @Override
            public void updateDrawState(TextPaint ds) {
                super.updateDrawState(ds);
                ds.setUnderlineText(true);
                ds.setColor(getResources().getColor(R.color.red));
            }
        };

        ss.setSpan(clickableSpan, shipping_charges_txt.length()-5, shipping_charges_txt.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        tv_shipping_charges.setText(ss);
        tv_shipping_charges.setMovementMethod(LinkMovementMethod.getInstance());
    }

    /**
     * CheckBox Listener
     **/
    @Override
    public void onCheckedChanged(CompoundButton compoundButton, boolean b) {

        //Edited by Janvi 1st Oct *****

//                if (cbxShip.isChecked()) {
//            lblDeliveryDetails.setText(getString(R.string.lbl_shipping_details));
//            lblBillingOrShipping.setText(getString(R.string.lbl_billing_details));
//            lblDeliveryDetails.setVisibility(View.VISIBLE);
//            lblAddressLists.setVisibility(View.VISIBLE);
//            tvClear.setVisibility(View.VISIBLE);
//            cvDeliveryOption.setVisibility(View.VISIBLE);
//            btnNext.setText("NEXT");
//        } else {
//            lblBillingOrShipping.setText(getString(R.string.lbl_billing_shipping_details));
//            btnNext.setText("NEXT");
//                /*lblDeliveryDetails.setVisibility(View.GONE);
//                lblAddressLists.setVisibility(View.GONE);
//                cvDeliveryOption.setVisibility(View.GONE);*/
//        }

        switch (compoundButton.getId()) {

            case R.id.cbx_ship_delivery_option_fragment:


//                Edited by Varun for cosmetic issue on changes to hand delivery from ship and checkbutton selected
                if (rbShip.isChecked()) {

                    //    VARUNN
                    rbHandOnDelivery.setChecked(false);
                    rbUberRush.setChecked(false);
                    rbPickUpAtStore.setChecked(false);
                    rbg_delivery_option_ubber.clearCheck();
                    rgMain.clearCheck();
                    rbg_delivery_option_hand.clearCheck();

//                if (Constant.STOREID.equalsIgnoreCase("707") || Constant.STOREID.equalsIgnoreCase("7365")) {
                    callShippingWService();
                    Log.e("callShippingWService", "callShippingWService: 7" );
//                    }

                    if (cbxShip.isChecked()) {

//                    if( ) || Constant.STOREID.equalsIgnoreCase("7365")){
//                        callShippingWService();
//                    }

                        txtDeliveryDetails.setText(getString(R.string.lbl_shipping_details));
                        txtBillingfirstview.setText(getString(R.string.lbl_billing_details));
                        txtDeliveryDetails.setVisibility(View.VISIBLE);
                        txtAddressLists.setVisibility(View.VISIBLE);
                        tvClear.setVisibility(View.VISIBLE);
                        cvDeliveryOption.setVisibility(View.VISIBLE);
                        btnNext.setText("NEXT");

                    } else {

                        txtBillingfirstview.setText(getString(R.string.lbl_billing_shipping_details));
                        txtDeliveryDetails.setVisibility(View.GONE);
                        txtAddressLists.setVisibility(View.GONE);
                        tvClear.setVisibility(View.GONE);
                        cvDeliveryOption.setVisibility(View.GONE);
                        btnNext.setText("NEXT");
                    }
                }

                break;

            case R.id.cbx_hand_delivery_option_fragment:

                if (cbx_hand_delivery.isChecked()) {

//                    Edited by Varun for when text comes empty don't show
                    if(liShippingData.get(0).getHandDeliveryAreaText().equals("") || liShippingData.get(0).getHandDeliveryAreaText().isEmpty()){
                        tvRadioGroupHandDelivery.setVisibility(View.GONE);
                    }else {
                        tvRadioGroupHandDelivery.setVisibility(View.VISIBLE);
                        tvRadioGroupHandDelivery.setText(liShippingData.get(0).getHandDeliveryAreaText());
                    }

//                    tvRadioGroupHandDelivery.setVisibility(View.VISIBLE);
//                    tvRadioGroupHandDelivery.setText(liShippingData.get(0).getHandDeliveryAreaText());
//                    ? END
                    txtDeliveryDetails.setVisibility(View.VISIBLE);
                    txtAddressLists.setVisibility(View.VISIBLE);
                    tvClear.setVisibility(View.VISIBLE);
                    cvDeliveryOption.setVisibility(View.VISIBLE);
                    txtDeliveryDetails.setText(getString(R.string.lbl_delivery_details));
                    btnNext.setText("NEXT");

                } else {
                    tvRadioGroupHandDelivery.setVisibility(View.GONE);
                    txtDeliveryDetails.setVisibility(View.GONE);
                    txtAddressLists.setVisibility(View.GONE);
                    tvClear.setVisibility(View.GONE);
                    cvDeliveryOption.setVisibility(View.GONE);
                    btnNext.setText("CONTINUE SHOPPING");
                }
                break;
        }


        //end*********
    }


    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public void onPrepareOptionsMenu(Menu menu) {
        MenuItem item = menu.findItem(R.id.menu_Cart);
        this.menu = menu;
        //MenuItem item=menu.findItem(R.id.action_search);
        item.setVisible(false);
    }

    @Override
    public void onDetach() {
        super.onDetach();
        myDeliveryOptionsEvent = null;
        position = -1;
    }

    /**
     * Add Data into Bundle
     **/
    public Bundle addDataIntoBundle(/*Bundle i*/) {

        Bundle i = new Bundle();
        if(isHandDelivery == 1 && cbx_hand_delivery.isChecked()){
            i.putFloat("total", _total + deliverysalexTax);
        }else{
            i.putFloat("total", _total);
        }

        i.putFloat("sub_total", _subTotal);
//        i.putFloat("sales_tax", _salesTax);

        //Edited by Janvi on 15th dec*****

        if(isHandDelivery == 1 && cbx_hand_delivery.isChecked()){
            i.putFloat("sales_tax", _salesTax + deliverysalexTax);
            Constant.finalSalesTax = _salesTax + deliverysalexTax;
        }else{
            i.putFloat("sales_tax", _salesTax);
            Constant.finalSalesTax = _salesTax;
        }
        //******

//        ***************** Edited by Varun for shipping charges ********************
        i.putString("s_name", S_name);
        i.putBoolean("rb_pick_up_store", rbPickUpAtStore.isChecked());
        Constant.rbPickUpAtStore = rbPickUpAtStore.isChecked();
        Constant.rbPayAtStore = rbPayAtStore.isChecked();
//        ****************** END **********************

        i.putFloat("wine_tax", _wineTax);
        i.putFloat("misc_tax", _miscTax);
        i.putFloat("flat_tac", _flatTax);
        i.putFloat("bottle_deposit", _bottleDeposit);
//        Edited by varun for Shipping charges not to go forward
        if (rbShip.isChecked()) {
            i.putFloat("shipping", _shipping);
        }
//        END
//         i.putFloat("shipping", _shipping);
        i.putFloat("total_saving", _totalSaving);
        i.putFloat("loyalty_reward", _lPoints);
//        Edited by varun for hand delivery charges not to go forward
        if (rbHandOnDelivery.isChecked()) {
            i.putFloat("tip_value", tipValue);
        }
//        END
        i.putInt("tip_option", tipSelectedOption);
        i.putInt("tip_cc_option", tipCCValue);
        i.putInt("selected_delivery_option", selectedRadioButton);
        i.putBoolean("pay_at_store", rbPayAtStore.isChecked());
        i.putBoolean("pay_with_cart", rbPayWithCart.isChecked());

        i.putBoolean("uber_rush", rbUberRush.isChecked());
        i.putBoolean("hand_on_delivery", rbHandOnDelivery.isChecked());
        i.putBoolean("ship", rbShip.isChecked());
        if (rbHandOnDelivery.isChecked()) {
            i.putBoolean("isTip", isTipDialogEnable);
        } else {
            i.putBoolean("isTip", false);
        }

        i.putBoolean("ship_with_different_address", cbxShip.isSelected());

        //Edited by Janvi on 15th dec*****
        i.putString("SurchargePrice", liShippingData.get(0).getSurchargePrice());
//        i.putString("SurchargePrice", String.valueOf(deliveryWithSalesTax));
        i.putBoolean("isDeliveryFee", cbx_hand_delivery.isChecked());
        if(!isDeliveryPage){
            i.putString("storeAlert",tvWarnTwoHourAgo.getText().toString());
        }else{
            i.putString("storeAlert","");
        }
        //end **********

        //for PickupTime
        if(DialogUtils.selectedPickupModel != null){
            i.putString("pickupDay",DialogUtils.selectedPickupModel.getDay());

            String[] gethourArray = DialogUtils.selectedPickupModel.getTime().split(":");

            String firstDigitHour = gethourArray[0].trim();
            String remainDigitHour = gethourArray[1].trim();
            String finalyDisplayhour = null;
            String dayTime = null;

            if(firstDigitHour.equals("01")){
                finalyDisplayhour = "1";
                dayTime = finalyDisplayhour + ":" + remainDigitHour;

            }else if(firstDigitHour.equals("02")){
                finalyDisplayhour = "2";
                dayTime = finalyDisplayhour + ":" + remainDigitHour;

            }else if(firstDigitHour.equals("03")){
                finalyDisplayhour = "3";
                dayTime = finalyDisplayhour +":" +remainDigitHour;

            }else if(firstDigitHour.equals("04")){
                finalyDisplayhour = "4";
                dayTime = finalyDisplayhour + ":" + remainDigitHour;

            }else if(firstDigitHour.equals("05")){
                finalyDisplayhour = "5";
                dayTime = finalyDisplayhour +":" + remainDigitHour;

            }else if(firstDigitHour.equals("06")){
                finalyDisplayhour = "6";
                dayTime = finalyDisplayhour + ":" +remainDigitHour;

            }else if(firstDigitHour.equals("07")){
                finalyDisplayhour = "7";
                dayTime = finalyDisplayhour + ":" +remainDigitHour;

            }else if(firstDigitHour.equals("08")){
                finalyDisplayhour = "8";
                dayTime = finalyDisplayhour + ":" + remainDigitHour;

            }else if(firstDigitHour.equals("09")){
                finalyDisplayhour = "9";
                dayTime = finalyDisplayhour + ":" +remainDigitHour;

            }else{
                dayTime = DialogUtils.selectedPickupModel.getTime();
            }

            i.putString("pickupTime",dayTime);
            i.putString("pickupCurrentDay",DialogUtils.selectedPickupModel.getCurrentday());

        }
        //end **********

        return i;
    }

    /**
     * Convert Null string into blank string
     **/
    public static String blankIfNull(String s) {
        return s == null ? "" : s;
    }

    /**
     * Insert Temp Order with required fields
     **/
    public void onInsertTempOrder(String firstName, String lastName, String phone, String addressOne, String addressTwo, String city, String state, String zip
            , String sFirstName, String sLastName, String sPhone, String sAddressOne, String sAddressTwo, String sCity, String sState, String sZip
            , String isPickUpAtStore, String isDeliveryHome, String isUberRush, String companyName, String email, String phoneType, String sCompanyName, String sPhoneType, String isSame, String isHandDelivery) {

        if (blankIfNull(firstName).isEmpty())
            firstName = "0";
        if (blankIfNull(lastName).isEmpty())
            lastName = "0";
        if (blankIfNull(phone).isEmpty())
            phone = "0";
        if (blankIfNull(addressOne).isEmpty())
            addressOne = "0";
        if (blankIfNull(addressTwo).isEmpty())
            addressTwo = "0";
        if (blankIfNull(city).isEmpty())
            city = "0";
        if (blankIfNull(state).isEmpty())
            state = "0";
        if (blankIfNull(zip).isEmpty())
            zip = "0";
        if (blankIfNull(sFirstName).isEmpty())
            sFirstName = "0";
        if (blankIfNull(sLastName).isEmpty())
            sLastName = "0";
        if (blankIfNull(sPhone).isEmpty())
            sPhone = "0";
        if (blankIfNull(sAddressOne).isEmpty())
            sAddressOne = "0";
        if (blankIfNull(sAddressTwo).isEmpty())
            sAddressTwo = "0";
        if (blankIfNull(sCity).isEmpty())
            sCity = "0";
        if (blankIfNull(sState).isEmpty())
            sState = "0";
        if (blankIfNull(sZip).isEmpty())
            sZip = "0";
        if (blankIfNull(companyName).isEmpty())
            companyName = "0";
        if (blankIfNull(phoneType).isEmpty())
            phoneType = "0";
        if (blankIfNull(email).isEmpty())
            email = "0";
        if (blankIfNull(sCompanyName).isEmpty())
            sCompanyName = "0";
        if (blankIfNull(sPhoneType).isEmpty())
            sPhoneType = "0";
        if (blankIfNull(isSame).isEmpty())
            isSame = "0";

        String tempUrl = "";
        if (UserModel.Cust_mst_ID != null) {
            tempUrl = Constant.WS_BASE_URL + Constant.INSERT_TEMP_ORDER_DETAILS + UserModel.Cust_mst_ID + "/"
                    + firstName + "/" + lastName + "/" + phone + "/" + addressOne + "/" + addressTwo + "/" + city + "/" + state + "/" + zip + "/"
                    + sFirstName + "/" + sLastName + "/" + sPhone + "/" + sAddressOne + "/" + sAddressTwo + "/" + sCity + "/" + sState + "/" + sZip + "/"
                    + Constant.STOREID + "/" + "add" + "/" + isPickUpAtStore + "/" + isDeliveryHome + "/" + isUberRush + "/"
                    + sCompanyName + "/" + email + "/" + sPhoneType + "/" + companyName + "/" + phoneType + "/"
                    + /* isSame */isSame + "/" + isHandDelivery;

            TaskInsertTempOrder insertTempOrder = new TaskInsertTempOrder(this);
            Log.d("Address", "Temp Order : " + tempUrl);
//            insertTempOrder.execute(tempUrl);
            insertTempOrder.executeOnExecutor(TaskInsertTempOrder.THREAD_POOL_EXECUTOR,tempUrl);
        }

    }

    /**
     * Convert Boolean value into Integer value
     **/
    public static int boolTOInt(boolean val) {
        if (val) {
            return 1;
        } else {
            return 0;
        }
    }

    /**
     * Result : Temp order web service
     **/
    @Override
    public void onTempOrderResult(OrderDetails orderDetails) {

        /** Check Tip value if tip is unable also only call if Tip Dialog is showing else pass all parameter default 0 value **/
        if (isTipDialog) {
            tipSubTotalValue = (int) _subTotal;

            if (tipSelectedOption == 1) {
                tipValue = fifteenResult;
            } else if (tipSelectedOption == 2) {
                tipValue = eighteenResult;
            } else if (tipSelectedOption == 3) {
                tipValue = twentyResult;
            } else if (tipSelectedOption == 4) {
                tipValue = defaultVal;
            } else if (tipSelectedOption == 5) {
                tipValue = 0;
                flagTipCashTip = 1;
                // TODO: 2/13/2018 Check Tip CC Value on Boxsalt web
                //tipCCValue = 1;
            } else if (tipSelectedOption == 6) {
                tipCCValue = 1;
            } else {

            }
            isTipDialog = false;
        }

        /** Tip Web service call **/
        String tipUrl = "";
        tipUrl = Constant.WS_BASE_URL + Constant.UPDATE_BILLING_TIP_VALUE + UserModel.Cust_mst_ID + "/" + Constant.STOREID + "/" +
                tipValue + "/" + tipSubTotalValue + "/" + tipCCValue + "/" + flagTipCashTip;

        TaskUpdateBillingTip taskUpdateBillingTip = new TaskUpdateBillingTip(this);
//        taskUpdateBillingTip.execute(tipUrl);
        taskUpdateBillingTip.executeOnExecutor(TaskUpdateBillingTip.THREAD_POOL_EXECUTOR,tipUrl);
    }

    /**
     * Tip Web service result
     **/
    @Override
    public void onBillingTipResult(ShippingData shippingData) {
       /* if (myDeliveryOptionsEvent != null)
            myDeliveryOptionsEvent.nextFromDeliveryOption(addDataIntoBundle());*/
        /** Call Update billing address web service **/
        updateBillingAddress();
    }


    String firstName, lastName, companyName, addressOne, addressTwo, city, state, zip, phoneNo,
            phoneType,shipping_option;
    String sFirstName, sLastName, sCompanyName, sAddressOne, sAddressTwo, sCity, sState, sZip, sPhoneNo, sPhoneType;
    String id = "0";

    /**
     * Update Billing address Web service call
     **/
    public void updateBillingAddress() {

        firstName = (etBFirstNameDo.getText().toString().isEmpty()) ? "0" : etBFirstNameDo.getText().toString();
        lastName = (etBLastNameDO.getText().toString().isEmpty()) ? "0" : etBLastNameDO.getText().toString();
        companyName = (etBCompanyDO.getText().toString().isEmpty()) ? "0" : etBCompanyDO.getText().toString();
        addressOne = (etBAddressOneDO.getText().toString().isEmpty()) ? "0" : etBAddressOneDO.getText().toString();
        addressTwo = (etBAddressTwoDO.getText().toString().isEmpty()) ? "0" : etBAddressTwoDO.getText().toString();
        city = (etBCityDo.getText().toString().isEmpty()) ? "0" : etBCityDo.getText().toString();
        state = (etBStateDO.getText().toString().isEmpty()) ? "0" : etBStateDO.getText().toString();
        zip = (etBZIpDO.getText().toString().isEmpty()) ? "0" : etBZIpDO.getText().toString();
        phoneNo = (etBPhoneNoDO.getText().toString().isEmpty()) ? "0" : etBPhoneNoDO.getText().toString();
        phoneType = (spinnerBsMobile.getSelectedItem().toString().isEmpty()) ? "0" : spinnerBsMobile.getSelectedItem().toString();

        /** Update Billing address web service **/
        String billingAddressUrl = "";
        billingAddressUrl = Constant.WS_BASE_URL + Constant.UPDATE_BILLING_ADDRESS + UserModel.Cust_mst_ID + "/"
                + firstName + "/" + lastName + "/" + companyName + "/" + addressOne + "/" + addressTwo + "/"
                + city + "/" + state + "/" + zip + "/" + phoneNo + "/" + phoneType + "/" + Constant.STOREID;

        TaskUpdateBillingAddress billingAddress = new TaskUpdateBillingAddress(getActivity(), this);
        Log.d(TAG, "billing address call : " + billingAddressUrl);
//        billingAddress.execute(billingAddressUrl);
        billingAddress.executeOnExecutor(TaskUpdateBillingAddress.THREAD_POOL_EXECUTOR,billingAddressUrl);
    }


    /**
     * Result : Update Billing address result
     **/
    @Override
    public void onBillingAddressResult(ShippingData shippingData) {
        /** Move to next screen **/

        Log.d("Address", "ID : " + shippingData.getResult());
        String strexp = shippingData.getResult();
        String[] arrexp = strexp.split("-");

        id = arrexp[1].trim();
        /*if (arrexp.length == 2) {
        }*/
        String shippingAddress;

        //http://192.168.172.211:140/CsCode/WebStoreOnlineService.asmx/UpdateBillingAddress?
        // custID=188731
        // &fName=Nidhi
        // &lName=Modi
        // &companyName=Anant
        // &address1=7853%20Princes%20Highway%20City
        // &address2=
        // &city=Narrawong
        // &state=VI
        // &zip=7853%20Princes%20Highway%20City
        // &phoneNo=
        // &phoneType=M
        // &storeNo=707

        id = (id.equals("0")) ? UserModel.Cust_mst_ID : id;
        shippingAddress = Constant.WS_BASE_POS_URL + Constant.POS_UPDATE_BILLING_ADDRESS
                + "custID=" + id
                + "&fName=" + firstName
                + "&lName=" + lastName
                + "&companyName=" + companyName
                + "&address1=" + addressOne
                + "&address2=" + addressTwo
                + "&city=" + city
                + "&state=" + state
                + "&zip=" + zip
                + "&phoneNo=" + phoneNo
                + "&phoneType=" + phoneType
                + "&storeNo=" + Constant.STOREID;

        TaskUpdatePOSBillingAddress posBillingAddress = new TaskUpdatePOSBillingAddress(this);
        Log.d("Address", "URL :  " + shippingAddress);
//        posBillingAddress.execute(shippingAddress);
        posBillingAddress.executeOnExecutor(TaskUpdatePOSBillingAddress.THREAD_POOL_EXECUTOR,shippingAddress);

        if (myDeliveryOptionsEvent != null)
            myDeliveryOptionsEvent.nextFromDeliveryOption(addDataIntoBundle());
    }


    @Override
    public void onPOSBillingAddressResult(UpdatePOSBillingAddress updatePOSBillingAddress) {
        if (updatePOSBillingAddress!=null){
            Log.e("Address", "Result : " + updatePOSBillingAddress.getResult());
            if (updatePOSBillingAddress.getResult().equals("success")) {
                //Update Shipping Address Here... In Boxsalt


                sFirstName = (etFirstNameDo.getText().toString().isEmpty()) ? "0" : etFirstNameDo.getText().toString();
                sLastName = (etLastNameDO.getText().toString().isEmpty()) ? "0" : etLastNameDO.getText().toString();
                sCompanyName = (etCompanyDO.getText().toString().isEmpty()) ? "0" : etCompanyDO.getText().toString();
                sAddressOne = (etAddressOneDO.getText().toString().isEmpty()) ? "0" : etAddressOneDO.getText().toString();
                sAddressTwo = (etAddressTwoDO.getText().toString().isEmpty()) ? "0" : etAddressTwoDO.getText().toString();
                sCity = (etCityDo.getText().toString().isEmpty()) ? "0" : etCityDo.getText().toString();
                sState = (etStateDO.getText().toString().isEmpty()) ? "0" : etStateDO.getText().toString();
                sZip = (etZIpDO.getText().toString().isEmpty()) ? "0" : etZIpDO.getText().toString();
                sPhoneNo = (etPhoneNoDO.getText().toString().isEmpty()) ? "0" : etPhoneNoDO.getText().toString();
                sPhoneType = (spinnerMobile.getSelectedItem().toString().isEmpty()) ? "0" : spinnerMobile.getSelectedItem().toString();

                String url;


            /*AddCustomerShippingAddress
            /CustomerId
            /fName
            /lName
            /companyName
            /address1
            /address2
            /city
            /state
            /zip
            /phone
            /phoneType
            /storeno
            /IsDefault */


                if(liShippingData != null && liShippingData.size()>0){
                    url = Constant.WS_BASE_URL + Constant.ADD_CUSTOMER_SHIPPING_ADDRESS
                            + UserModel.Cust_mst_ID /* CustomerId */
                            + "/" + sFirstName      /* fName */
                            + "/" + sLastName       /* lName */
                            + "/" + sCompanyName    /* companyName */
                            + "/" + sAddressOne     /* address! */
                            + "/" + sAddressTwo     /* address2 */
                            + "/" + sCity           /* city */
                            + "/" + sState          /* state */
                            + "/" + sZip            /* zip */
                            + "/" + sPhoneNo        /* phone */
                            + "/" + sPhoneType      /* phoneType */
                            + "/" + Constant.STOREID/* storeno */
                            + "/" + 0;              /*  */

                }else{

                    url = Constant.WS_BASE_URL + Constant.ADD_CUSTOMER_SHIPPING_ADDRESS
                            + UserModel.Cust_mst_ID /* CustomerId */
                            + "/" + sFirstName      /* fName */
                            + "/" + sLastName       /* lName */
                            + "/" + sCompanyName    /* companyName */
                            + "/" + sAddressOne     /* address! */
                            + "/" + sAddressTwo     /* address2 */
                            + "/" + sCity           /* city */
                            + "/" + sState          /* state */
                            + "/" + sZip            /* zip */
                            + "/" + sPhoneNo        /* phone */
                            + "/" + sPhoneType      /* phoneType */
                            + "/" + Constant.STOREID/* storeno */
                            + "/" + 1;              /* isDefault*/
                }


                TaskAddCustomerShippingAddress shippingAddress = new TaskAddCustomerShippingAddress(this);
                Log.d("Address", "Call Shipping address : " + url);
//                shippingAddress.execute(url);
                shippingAddress.executeOnExecutor(TaskAddCustomerShippingAddress.THREAD_POOL_EXECUTOR,url);
            }
        }
    }


    @Override
    public void onShippingAddressResult(ShippingData shippingData) {
        Log.e("Address", "Shipping Result : " + shippingData.getResult());

        /*http://192.168.172.211:140/CsCode/WebStoreOnlineService.asmx
        /UpdateBillingAddress?
        custID=188731
        &fName=Nidhi
        &lName=Modi
        &companyName=Anant
        &address1=7853%20Princes%20Highway%20City
        &address2=
        &city=Narrawong
        &state=VI
        &zip=7853%20Princes%20Highway%20City&phoneNo=
        &phoneType=M
        &storeNo=707
        &isDefault=1*/

        String posShippingAddressUrl;

        if(liShippingData != null && liShippingData.size()>0){
            posShippingAddressUrl = Constant.WS_BASE_POS_URL + Constant.POS_UPDATE_BILLING_ADDRESS
                    + "custID=" + id
                    + "&fName=" + firstName
                    + "&lName=" + lastName
                    + "&companyName=" + companyName
                    + "&address1=" + addressOne
                    + "&address2=" + addressTwo
                    + "&city=" + city
                    + "&state=" + state
                    + "&zip=" + zip
                    + "&phoneNo=" + phoneNo
                    + "&phoneType=" + phoneType
                    + "&storeNo=" + Constant.STOREID
                    + "&isDefault=" + "0";

        }else{
            posShippingAddressUrl = Constant.WS_BASE_POS_URL + Constant.POS_UPDATE_BILLING_ADDRESS
                    + "custID=" + id
                    + "&fName=" + firstName
                    + "&lName=" + lastName
                    + "&companyName=" + companyName
                    + "&address1=" + addressOne
                    + "&address2=" + addressTwo
                    + "&city=" + city
                    + "&state=" + state
                    + "&zip=" + zip
                    + "&phoneNo=" + phoneNo
                    + "&phoneType=" + phoneType
                    + "&storeNo=" + Constant.STOREID
                    + "&isDefault=" + "1";

        }

        TaskUpdatePOSShippingAddress posShippingAddress = new TaskUpdatePOSShippingAddress(this);
        Log.d("Address", "POS Shipping Address Call : " + posShippingAddressUrl);
//        posShippingAddress.execute(posShippingAddressUrl);
        posShippingAddress.executeOnExecutor(TaskUpdatePOSShippingAddress.THREAD_POOL_EXECUTOR,posShippingAddressUrl);
    }

    @Override
    public void onPOSShippingAddressResult(UpdatePOSBillingAddress updatePOSBillingAddress) {
        Log.d("Address", "POS Shipping Address Result : " + updatePOSBillingAddress.getResult());
    }

    private void showZipAvailabilityDialog(List<Zipmodel> zipList, String zip) {
        String Zip_err = "The ZIP Code of " + zip + " is outside our delivery zone. Our current delivery zone is:";
        Dialog ZipAvailDialog = null;
        View vZipavil = null;
        // if (Constant.SCREEN_LAYOUT == 1) {
        ZipAvailDialog = new Dialog(getActivity(), R.style.DialogSlideAnim_login);
        ZipAvailDialog.setCanceledOnTouchOutside(true);
        vZipavil = LayoutInflater.from(getActivity()).inflate(R.layout.zipavail_dialog, null);

        ListView Ziplist = vZipavil.findViewById(R.id.Ziplist);

//        ViewGroup.LayoutParams params1 = Ziplist.getLayoutParams();
//        params1.height = (40 * zipList.size());
//        Ziplist.setLayoutParams(params1);

        zipAdapter zipAdapter = new zipAdapter(zipList, getActivity());
        Ziplist.setAdapter(zipAdapter);

        TextView tv_first = vZipavil.findViewById(R.id.tv_first);
        TextView txtLast = vZipavil.findViewById(R.id.txtLast);
        txtLast.setText(shippingData.getHandDeliveryAreaText());
        tv_first.setText(Zip_err);
        Button btn_Ok = vZipavil.findViewById(R.id.btn_Ok);
        GradientDrawable bgShape = (GradientDrawable) btn_Ok.getBackground();
        bgShape.setColor(Color.parseColor(Constant.themeModel.ThemeColor));

        WindowManager.LayoutParams params = ZipAvailDialog.getWindow().getAttributes();
        params.width = WindowManager.LayoutParams.WRAP_CONTENT;
        params.height = WindowManager.LayoutParams.WRAP_CONTENT;

        ZipAvailDialog.setContentView(vZipavil);

        ZipAvailDialog.getWindow().setGravity(Gravity.CENTER);
        WindowManager.LayoutParams layoutParam = ZipAvailDialog.getWindow().getAttributes();
        ZipAvailDialog.getWindow().setAttributes(layoutParam);

        if (!ZipAvailDialog.isShowing()) {
            ZipAvailDialog.show();
        }
        final Dialog finalZipAvailDialog = ZipAvailDialog;
        btn_Ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finalZipAvailDialog.dismiss();
                etZIpDO.requestFocus();
                //etBsZIpDO.setFocusable(true);
                // etBsZIpDO.callOnClick();
            }
        });
    }

    @Override
    public void onGetZipResult(List<Zipmodel> ZipList) {
        String Zip = etZIpDO.getText().toString().trim();

        if (!zipContain(ZipList, Zip) && liShippingData.get(0).getDefineDeliveryArea().trim().equalsIgnoreCase("true")) {
            shippingData = liShippingData.get(0);
            showZipAvailabilityDialog(ZipList, Zip);
        }
        if (isNextCall && (zipContain(ZipList, Zip) || liShippingData.get(0).getDefineDeliveryArea().trim().equalsIgnoreCase("false"))) {
            //if(liShippingData.get(0).getDefineDeliveryArea().trim().equalsIgnoreCase("false")) {
            callNext();
            //}
        }
        isNextCall = false;
    }

    public boolean zipContain(List<Zipmodel> ZipList, String zip) {
        boolean iszipcontain = false;
        for (int i = 0; i < ZipList.size(); i++) {
            if (ZipList.get(i).getZip().trim().equals(zip.trim())) {
                iszipcontain = true;
            }
        }
        return iszipcontain;
    }

    public void onCallGlobalSetup() {

        if(Constant.SCREEN_LAYOUT==1){
            String twentyOneYearUrl = Constant.WS_BASE_URL + Constant.GET_GLOBALSETTING + Constant.STOREID;
            TaskTwentyOneYear taskTwentyOneYear = new TaskTwentyOneYear(this);
//            taskTwentyOneYear.execute(twentyOneYearUrl);
            taskTwentyOneYear.executeOnExecutor(TaskTwentyOneYear.THREAD_POOL_EXECUTOR,twentyOneYearUrl);

        }else if(Constant.SCREEN_LAYOUT==2) {
            String twentyOneYearUrl = Constant.WS_BASE_URL + Constant.GET_GLOBALSETTING + Constant.STOREID;
            TaskTwentyOneYear taskTwentyOneYear = new TaskTwentyOneYear(this);
//            taskTwentyOneYear.execute(twentyOneYearUrl);
            taskTwentyOneYear.executeOnExecutor(TaskTwentyOneYear.THREAD_POOL_EXECUTOR,twentyOneYearUrl);
        }
    }

    @Override
    public void onTwentyOneYearResult(TwentyOneYear twentyOneYear) {
        this.twentyOneYear_Current = twentyOneYear;
        Constant.twentyOneYear = twentyOneYear;

//        if(isBypassDeliveryOption){
//
//            if(Constant.twentyOneYear != null && Constant.twentyOneYear.getAllowPickUpTime()){
//                loadOnlyStoreHoursWS();
//            }else{
//                callnextFlowFromDeliveryOption();
//            }
//
//        }else {

        Log.e(TAG, "onTwentyOneYearResult: 12"+twentyOneYear.getAllowPickUpTime() );
        if(twentyOneYear.getAllowPickUpTime() != null && twentyOneYear.getAllowPickUpTime().toString().equalsIgnoreCase("true")){
            loadOnlyStoreHoursWS();
        }else{
            insertTempOrderWithBillingAndShippingData();
        }
//        }

//        if(Constant.twentyOneYear != null && Constant.twentyOneYear.getAllowPickUpTime()){
//            loadOnlyStoreHoursWS();
//        }else{
//            insertTempOrderWithBillingAndShippingData();
//        }
    }

    public void callnextFlowFromDeliveryOption() {

        if (myDeliveryOptionsEvent != null)
            myDeliveryOptionsEvent.nextFromDeliveryOption(addDataIntoBundle());
//        isBypassDeliveryOption = false;
    }

}




