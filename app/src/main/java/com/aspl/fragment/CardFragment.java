package com.aspl.fragment;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.aspl.Adapter.CardAdapter;
import com.aspl.Utils.BSTheme;
import com.aspl.Utils.Constant;
import com.aspl.Utils.DeviceInfo;
import com.aspl.Utils.DialogUtils;
import com.aspl.Utils.Utils;
import com.aspl.mbs.MainActivity;
import com.aspl.mbs.MainActivityDup;
import com.aspl.mbs.R;
import com.aspl.mbsmodel.PickupModel;
import com.aspl.mbsmodel.ShippingData;
import com.aspl.mbsmodel.ShoppingCardModel;
import com.aspl.mbsmodel.StoreHour;
import com.aspl.mbsmodel.TwentyOneYear;
import com.aspl.mbsmodel.UpdateCartQuantity;
import com.aspl.mbsmodel.UserModel;
import com.aspl.task.TaskCart;
import com.aspl.task.TaskCustomerData;
import com.aspl.task.TaskDeleteCartItem;
import com.aspl.task.TaskShippingData;
import com.aspl.task.TaskStoreDeliveryHours;
import com.aspl.task.TaskTwentyOneYear;
import com.aspl.task.TaskUpdateCartQuantity;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

/**
 * Created by mic on 12/1/2017.
 */

public class CardFragment extends Fragment implements View.OnClickListener
        , TaskCart.TaskCardEvent
        , CardAdapter.CardAdapterEvent
       /* , TaskTwentyOneYear.TaskTwentyOneYearEvent*/
        , TaskUpdateCartQuantity.TaskUpdateCartQuantityEvent, TaskCustomerData.TaskCustomerEvent, TaskShippingData.TaskShippingEvent, TaskStoreDeliveryHours.StoreDeliveryHoursEvent, TaskTwentyOneYear.TaskTwentyOneYearEvent {

    public static final String TAG = "CardFragment";

    //Double _subTotal = 0.0, _salesTax = 0.0, _wineTax = 0.0, _bottleDeposit = 0.0, _miscTax = 0.0, _flatTax = 0.0, _total = 0.0;
    Float _subTotal = 0.0f, _salesTax = 0.0f, _wineTax = 0.0f, _bottleDeposit = 0.0f, _miscTax = 0.0f, _flatTax = 0.0f, _shipping = 0.0f, _total = 0.0f, _totalSaving = 0.0f, _actualTotal = 0.0f, _lPoints = 0.0f;
    Float final_point = 0.0f;
    public static SwipeRefreshLayout swipeRefreshLayout;
    public static View vSalesTax, vWineTax, vBottleDeposit, vMiscTax, vFlatTax, vShipping, vCheckBox, vTotal, vButton, vLegend;
    static RelativeLayout rlRoot,rlLegend;
    static LinearLayout llRootMain, llCheckBox;
    static LinearLayout llRootEmpty;
    public static RelativeLayout llRootTotal;
    public static LinearLayout llSubTotal, llSalesTax, llWineTax, llBottleDeposit, llMiscTax, llFlatTax, llShipping, llTotal/*, llCheckBox*//*,llRootTotal*/;
    public static TextView tvSubtotal, tvSalesTex, tvWineTax, tvBottleDeposit, tvMiscTax, tvFlatTax, tvShipping, tvTotal,tv_title_legal;
    public static TextView tvTitleSubtotal, tvTitleSalesTex, tvTitleWineTax, tvTitleBottleTax, tvTitleMiscTax, tvTitleFlatTax, tvTitleShipping, tvTitleTotal, tvTitleTotalSaving, tvTotalSaving, tvTitleLoyaltyReward, tvLoyaltyReward, tvTitleCheckBox;
    public static TextView tvFragmentTitle, tvFragmentEmptyTitle;
    public static TextView tvlegendSalesTax, tvlegendMiscTax, tvlegendFlatTax, tvlegendWineTax, tvlegendBottleDeposite, tvlegendNonTaxable;
    public static CheckBox checkbox;
    public static Button btnNext, btnContinueShopping, btnContinueShoppingEmpty;
    public static RecyclerView recyclerView;
    static CardView cvTotal;
    //List<CardModel> cardModels = new ArrayList<>();
    public static List<ShoppingCardModel> liShoppingCard = new ArrayList<>();
    public boolean _isWineTaxEnable = false, _isMiscTaxEnable = false, _isFlatTaxEnable = false;
    ShippingData liShippingData;
    boolean isComeFromCard = false;
    public boolean isBypassDeliveryOption = false;
    ArrayList<PickupModel> results;
    String Price;

    private static CardAdapter cardAdapter;
    public Menu menu;

    int Requested_Quantity=0;
    public CardFragment() {
    }

    /*TwentyOneYear twentyOneYear;*/

    @SuppressLint("ValidFragment")
    public CardFragment(TwentyOneYear twentyOneYear) {
        /*this.twentyOneYear = twentyOneYear;*/
    }

    CardEvent mCardEvent;
        public static CardFragment cardFragment;

    public static CardFragment getInstance()     {
        return cardFragment;
    }

    public void redirectToHome() {

//        int backStackEntry = 0;
//        if (getFragmentManager() != null) {
//            backStackEntry = getFragmentManager().getBackStackEntryCount();
//        }
//        if (backStackEntry > 0) {
//            for (int i = 0; i < backStackEntry; i++) {
//                getFragmentManager().popBackStackImmediate();
//            }
//        }

        if (Constant.SCREEN_LAYOUT == 1) {
            final FragmentManager fragmentManager = getFragmentManager();
            if (fragmentManager != null) {
                while (fragmentManager.getBackStackEntryCount() != 0) {
                    fragmentManager.popBackStackImmediate();
                }
            }
            if (getFragmentManager() != null && getFragmentManager().getBackStackEntryCount() > 0) {
                MainActivity.getInstance().onBackPressed();
            }
            MainActivity.showHomePage();
            MainActivity.getInstance().loadHomeWebPage();

        } else if (Constant.SCREEN_LAYOUT == 2) {
            final FragmentManager fragmentManager = getFragmentManager();
            if (fragmentManager != null) {
                while (fragmentManager.getBackStackEntryCount() != 0) {
                    fragmentManager.popBackStackImmediate();
                }
            }
            if (getFragmentManager() != null && getFragmentManager().getBackStackEntryCount() > 0) {
                MainActivityDup.getInstance().onBackPressed();
            }
            MainActivityDup.showHomePage();
            MainActivityDup.getInstance().loadHomeWebPage();
        }
        CardFragment.getInstance().isComeFromCard = false;
    }


    public interface CardEvent {
        void onContinueShoppingCartClicked();

        void onNextShoppingCart(Bundle bundle);
    }

    @Override
    public void onStart() {
        super.onStart();

        //String urls = "http://192.168.172.211:889/WebStoreRestService.svc/GetCustomerCartData/188723/Cart/707";
        //UserModel userModel = new UserModel();


//        ************** Edited by Varun for shopping cart on 29 july 2022 ********************
//        not to refresh when login

//        oncall();
        onGetCartData();

        //******************* END **************************


        //onCartWebApiCall();

        /*String url = null;
        if (UserModel.Cust_mst_ID != null) {
            url = Constant.WS_BASE_URL + Constant.GET_CUSTOMER_CARD_DATA + UserModel.Cust_mst_ID + "/" + Constant.MY_CART + Constant.STOREID;
            TaskCart taskCart = new TaskCart(cardFragment);
            taskCart.execute(url);
        } else {
            url = Constant.WS_BASE_URL + Constant.GET_CUSTOMER_CARD_DATA + "1000100025" + "/" + Constant.SESSION + Constant.STOREID;
            TaskCart taskCart = new TaskCart(cardFragment);
            taskCart.execute(url);
            //onSetEmpty();
        }*/
        //new Async_getCommonService(MainActivity.getInstance(),urls).execute();
    }

////    *************** Edited by Varun for shopping cart on 29 july 2022 *********************
////          not to refresh when login
//    public void oncall() {
//
//
//        if (Constant.SCREEN_LAYOUT == 1){
//            MainActivity.moveSessionToCart();
//        }else if (Constant.SCREEN_LAYOUT == 2){
//            MainActivityDup.moveSessionToCart();
//        }
//        onGetCartData();
//        getCustomerData();
//
//    }
//
////     ********************** END ************************


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mCardEvent = (CardEvent) context;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        mCardEvent = (CardEvent) activity;
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        cardFragment = this;
        setHasOptionsMenu(true);
        setRetainInstance(true);
        getCustomerData();
        //getActivity().invalidateOptionsMenu();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //return super.onCreateView(inflater, container, savedInstanceState);
        return inflater.inflate(R.layout.fragment_card, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //Initialize the views
        initViews(view);
        LinearLayoutManager linearLayoutManager=null;
        if(Constant.SCREEN_LAYOUT==1){
             linearLayoutManager = new LinearLayoutManager(MainActivity.getInstance());
        }else if(Constant.SCREEN_LAYOUT==2) {
             linearLayoutManager = new LinearLayoutManager(MainActivityDup.getInstance());
        }

        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setHasFixedSize(true);
        swipeRefreshLayout.setOnRefreshListener(
                new SwipeRefreshLayout.OnRefreshListener() {
                    @Override
                    public void onRefresh() {
                        onGetCartData();
                        //onCartWebApiCall();
                    }
                }
        );
        onCheckTwentyOneYear();
    }

    void getCustomerData() {
        if (UserModel.Cust_mst_ID != null) {
            String url = Constant.WS_BASE_URL + Constant.GET_CUSTOMER_DATA + UserModel.Cust_mst_ID + "/" + Constant.STOREID;
            TaskCustomerData taskCustomerData = new TaskCustomerData(getActivity(),this);
            Log.d(TAG, "Customer data : " + url);
            taskCustomerData.execute(url);
        }
    }

    @Override
    public void onTaskCustomerResult(ShippingData liShippingData, boolean isFromfavouriteStore) {

        if(liShippingData != null){
            this.liShippingData = liShippingData;
        }
    }

    public void onGetCartData() {
        String url = null;
        if (UserModel.Cust_mst_ID != null) {
//            url = Constant.WS_BASE_URL + Constant.GET_CUSTOMER_CARD_DATA + UserModel.Cust_mst_ID + "/" + Constant.MY_CART + Constant.STOREID;
            url = Constant.WS_BASE_URL + Constant.GET_CUSTOMER_CARD_DATA_V1 + UserModel.Cust_mst_ID + "/" + Constant.MY_CART + Constant.STOREID + Constant.ENCODE_TOKEN_ID;
            TaskCart taskCart = new TaskCart(cardFragment, "");
            taskCart.execute(url);
        } else {
//            url = Constant.WS_BASE_URL + Constant.GET_CUSTOMER_CARD_DATA + DeviceInfo.getDeviceId(getActivity()) + "0011" + "/" + Constant.SESSION + Constant.STOREID;
            url = Constant.WS_BASE_URL + Constant.GET_CUSTOMER_CARD_DATA_V1 + DeviceInfo.getDeviceId(getActivity()) + "0011" + "/" + Constant.SESSION + Constant.STOREID  + Constant.ENCODE_TOKEN_ID;
            TaskCart taskCart = new TaskCart(cardFragment, "");
            taskCart.execute(url);
        }
    }

    @Override
    public void onShoppingCardResult(List<ShoppingCardModel> liShoppingCard, String s) {

        swipeRefreshLayout.setRefreshing(false);
        CardFragment.liShoppingCard = liShoppingCard;

        if (CardFragment.liShoppingCard.size() > 0) {
            if(Constant.SCREEN_LAYOUT==1){
                if (CardFragment.liShoppingCard.get(0).getCartID() == 0) {
                    /** Clear Shopping Cart Icon Count **/
                    MainActivity.getInstance().updateShoppingCartItemCount(0);
                    rlRoot.setVisibility(View.VISIBLE);
                    llRootMain.setVisibility(View.GONE);
                    llRootEmpty.setVisibility(View.VISIBLE);
                } else {
                    int quntity = 0;
                    for (int i = 0; i < liShoppingCard.size(); i++) {
                        quntity = quntity + Integer.parseInt(liShoppingCard.get(i).getQty());
                    }
                        MainActivity.countMenu.setTitle(String.valueOf(quntity));
                    //below line added newly for counter qty update
                    MainActivity.getInstance().updateShoppingCartItemCount(quntity);
                    /////
                    llRootEmpty.setVisibility(View.GONE);
                    rlRoot.setVisibility(View.VISIBLE);
                    cvTotal.setVisibility(View.VISIBLE);
                    cardAdapter = new CardAdapter(MainActivity.getInstance(), this, liShoppingCard);
                    recyclerView.setAdapter(cardAdapter);
                    onCalculateTotal(liShoppingCard);
                    cardAdapter.notifyDataSetChanged();
                }
            }else if(Constant.SCREEN_LAYOUT==2) {
                if (CardFragment.liShoppingCard.get(0).getCartID() == 0) {
                    /** Clear Shopping Cart Icon Count **/
                    MainActivityDup.getInstance().updateShoppingCartItemCount(0);

                    rlRoot.setVisibility(View.VISIBLE);
                    llRootMain.setVisibility(View.GONE);
                    llRootEmpty.setVisibility(View.VISIBLE);
                } else {
                    int quntity = 0;
                    for (int i = 0; i < liShoppingCard.size(); i++) {
                        quntity = quntity + Integer.parseInt(liShoppingCard.get(i).getQty());
                    }
                        MainActivityDup.countMenu.setTitle(String.valueOf(quntity));
                    llRootEmpty.setVisibility(View.GONE);
                    rlRoot.setVisibility(View.VISIBLE);
                    cvTotal.setVisibility(View.VISIBLE);
                    cardAdapter = new CardAdapter(MainActivityDup.getInstance(), this, liShoppingCard);
                    recyclerView.setAdapter(cardAdapter);
                    onCalculateTotal(liShoppingCard);
                    cardAdapter.notifyDataSetChanged();
                }
            }

        } else {
            rlRoot.setVisibility(View.VISIBLE);
            llRootMain.setVisibility(View.GONE);
            llRootEmpty.setVisibility(View.VISIBLE);
        }

    }

    /**
     * 21 Year Condition Check
     **/
    /*public static void onCallGlobalSetup() {
        String twentyOneYearUrl = Constant.WS_BASE_URL + Constant.GET_GLOBALSETTING + Constant.STOREID;
        TaskTwentyOneYear taskTwentyOneYear = new TaskTwentyOneYear(CardFragment.getInstence());
        taskTwentyOneYear.execute(twentyOneYearUrl);
    }*/

    /**
     * Task 21 Year Condition check result
     **/
    public void onCheckTwentyOneYear() {
        /*this.twentyOneYear = twentyOneYear;*/
        if (Constant.twentyOneYear.getCustAgeValidOption() != null) {
            if (Constant.twentyOneYear.getCustAgeValidOption() == 2) {
                CardFragment.llCheckBox.setVisibility(View.VISIBLE);
                CardFragment.vCheckBox.setVisibility(View.VISIBLE);
                tv_title_legal.setTextColor(Color.parseColor(Constant.themeModel.ThemeColor));
                tv_title_legal.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

//                        if (homeTwentyOneYearDialog.isShowing()) {
//                            homeTwentyOneYearDialog.dismiss();
//                        }
                        DialogUtils.on21YearCart(Constant.twentyOneYear,"legal");
                    }
                });

//                tvCheckBoxMessage.setText(Constant.twentyOneYear.getAgeValidMessage());
//                tvCheckBoxMessage.setVisibility(View.GONE);

            } else {
                CardFragment.llCheckBox.setVisibility(View.GONE);
                CardFragment.vCheckBox.setVisibility(View.GONE);
//                tvCheckBoxMessage.setVisibility(View.GONE);
            }
        }
    }
    /*@Override
    public void onTwentyOneYearResult(TwentyOneYear twentyOneYear) {
        *//*this.twentyOneYear = twentyOneYear;*//*
        if (twentyOneYear.getCustAgeValidOption() == 2) {
            CardFragment.llCheckBox.setVisibility(View.VISIBLE);
            CardFragment.vCheckBox.setVisibility(View.VISIBLE);
            tvCheckBoxMessage.setText(twentyOneYear.getAgeValidMessage());
            tvCheckBoxMessage.setVisibility(View.GONE);

        } else {
            CardFragment.llCheckBox.setVisibility(View.GONE);
            CardFragment.vCheckBox.setVisibility(View.GONE);
            tvCheckBoxMessage.setVisibility(View.GONE);
        }
    }*/

    public static boolean bottleStatus = false;

    public void onCalculateTotal(List<ShoppingCardModel> liShoppingCard) {

        CardFragment.liShoppingCard = liShoppingCard;
        _subTotal = 0.0f;
        _salesTax = 0.0f;
        _wineTax = 0.0f;
        _bottleDeposit = 0.0f;
        _miscTax = 0.0f;
        _flatTax = 0.0f;
        _shipping = 0.0f;
        _total = 0.0f;
        _totalSaving = 0.0f;
        _actualTotal = 0.0f;

        for (int i = 0; i < liShoppingCard.size(); i++) {
            if (liShoppingCard.get(i).getCartPrice() != null && !liShoppingCard.get(i).toString().isEmpty()) {
                if (Float.parseFloat(liShoppingCard.get(i).getCartPrice()) > 0 && liShoppingCard.get(i).getQty() != null) {
                    //_subTotal = _subTotal + Double.valueOf(liShoppingCard.get(i).getCartPrice()) * Double.valueOf(liShoppingCard.get(i).getQty());
                    _subTotal = _subTotal + Float.parseFloat(liShoppingCard.get(i).getCartPrice()) * Float.parseFloat(liShoppingCard.get(i).getQty());
                }
            }
            if (liShoppingCard.get(i).getIsSalesTax()) {
                vSalesTax.setVisibility(View.VISIBLE);
                llSalesTax.setVisibility(View.VISIBLE);
                _salesTax = _salesTax + Float.parseFloat(liShoppingCard.get(i).getSalesTax1());
            } else {
                vSalesTax.setVisibility(View.GONE);
                llSalesTax.setVisibility(View.GONE);
            }

            if (liShoppingCard.get(i).getIsWineTax()) {
                _isWineTaxEnable = true;
                vWineTax.setVisibility(View.VISIBLE);
                llWineTax.setVisibility(View.VISIBLE);
                _wineTax = _wineTax + Float.parseFloat(liShoppingCard.get(i).getWineTax2());
            } else {
                vWineTax.setVisibility(View.GONE);
                llWineTax.setVisibility(View.GONE);
            }

            //Bottle Deposit
            if (Double.valueOf(liShoppingCard.get(i).getBottledeposit()) != null)
                _bottleDeposit = _bottleDeposit + Float.parseFloat(liShoppingCard.get(i).getBottledeposit()) * Float.parseFloat(liShoppingCard.get(i).getQty());

            //Bottle Deposit visibility
            if (_bottleDeposit > 0) {
                llBottleDeposit.setVisibility(View.VISIBLE);
                vBottleDeposit.setVisibility(View.VISIBLE);
            } else {
                llBottleDeposit.setVisibility(View.GONE);
                vBottleDeposit.setVisibility(View.GONE);
            }

            if (liShoppingCard.get(i).getIsMiscTax()) {
                _isMiscTaxEnable = true;
                vMiscTax.setVisibility(View.VISIBLE);
                llMiscTax.setVisibility(View.VISIBLE);
                _miscTax = _miscTax + Float.valueOf(liShoppingCard.get(i).getMiscTax3());
            } else {
                vMiscTax.setVisibility(View.GONE);
                llMiscTax.setVisibility(View.GONE);
            }

            if (liShoppingCard.get(i).getIsflat()/*Double.valueOf(liShoppingCard.get(i).getFlat()) != null*/) {
                _isFlatTaxEnable = true;
                vFlatTax.setVisibility(View.VISIBLE);
                llFlatTax.setVisibility(View.VISIBLE);
                _flatTax = _flatTax + Float.parseFloat(liShoppingCard.get(i).getFlat());
            } else {
                vFlatTax.setVisibility(View.GONE);
                llFlatTax.setVisibility(View.GONE);
            }

            if (liShoppingCard.get(i).getBSSetupDeliveryOption()) {

                _shipping = _shipping + 0;

                if(_shipping > 0){
                    vShipping.setVisibility(View.VISIBLE);
                    llShipping.setVisibility(View.VISIBLE);
                }else{
                    vShipping.setVisibility(View.GONE);
                    llShipping.setVisibility(View.GONE);
                }
            } else {
                vShipping.setVisibility(View.GONE);
                llShipping.setVisibility(View.GONE);
            }
            if (liShoppingCard.get(i).getIsTotalSavingDisplay()) {
                tvTotalSaving.setVisibility(View.VISIBLE);
                tvTitleTotalSaving.setVisibility(View.VISIBLE);
            } else {
                tvTitleTotalSaving.setVisibility(View.GONE);
                tvTotalSaving.setVisibility(View.GONE);
            }
//            Edited by Varun for to remove the $ from the price
//            _actualTotal = _actualTotal + Float.parseFloat(liShoppingCard.get(i).getPrice()) * Float.parseFloat(liShoppingCard.get(i).getQty());
            if (liShoppingCard.get(i).getPrice().contains("$")){
                Price = liShoppingCard.get(i).getPrice().replace("$","");
                _actualTotal = _actualTotal + Float.parseFloat(Price) * Float.parseFloat(liShoppingCard.get(i).getQty());
            }else {
                _actualTotal = _actualTotal + Float.parseFloat(liShoppingCard.get(i).getCartPrice()) * Float.parseFloat(liShoppingCard.get(i).getQty());
            }
            /*if (Double.valueOf(liShoppingCard.get(i).getPromoPrice()) != null){
                _totalSaving = Float.parseFloat(liShoppingCard.get(i).getPrice()) - Float.parseFloat(liShoppingCard.get(i).getPromoPrice());
            }*/
        }

        if (_miscTax > 0 && _isMiscTaxEnable) {
            vMiscTax.setVisibility(View.VISIBLE);
            llMiscTax.setVisibility(View.VISIBLE);
        } else {
            vMiscTax.setVisibility(View.GONE);
            llMiscTax.setVisibility(View.GONE);
        }
        if (_flatTax > 0 && _isFlatTaxEnable) {
            vFlatTax.setVisibility(View.VISIBLE);
            llFlatTax.setVisibility(View.VISIBLE);
        } else {
            vFlatTax.setVisibility(View.GONE);
            llFlatTax.setVisibility(View.GONE);
        }
        if (_wineTax > 0 && _isWineTaxEnable) {
            vWineTax.setVisibility(View.VISIBLE);
            llWineTax.setVisibility(View.VISIBLE);
        } else {
            vWineTax.setVisibility(View.GONE);
            llWineTax.setVisibility(View.GONE);
        }

        _total = _subTotal + _salesTax + _wineTax + _bottleDeposit + _miscTax + _flatTax;
        _totalSaving = _actualTotal - _subTotal;

        DecimalFormat df = new DecimalFormat("####0.00");
        df.setMaximumFractionDigits(2);
        //df.setRoundingMode(RoundingMode.UP);

        tvSubtotal.setText("$" + String.valueOf(df.format(_subTotal)));
        tvSalesTex.setText("$" + /*String.valueOf(_salesTax) + " " +*/ String.valueOf(df.format(_salesTax)));
        tvWineTax.setText("$" + String.valueOf(df.format(_wineTax)));
        tvBottleDeposit.setText("$" + String.valueOf(df.format(_bottleDeposit)));
        tvMiscTax.setText("$" + String.valueOf(df.format(_miscTax)));
        tvFlatTax.setText("$" + String.valueOf(df.format(_flatTax)));
        tvShipping.setText("$" + String.valueOf(df.format(_shipping)));
        tvTotal.setText("$" + String.valueOf(df.format(_total)));

        if (_totalSaving > 0.0) {
            //tvTotalSaving.setVisibility(View.VISIBLE);
            //tvTitleTotalSaving.setVisibility(View.VISIBLE);
            tvTitleTotalSaving.setText("Total Savings  ");
            tvTotalSaving.setText("$" + String.valueOf(df.format(_totalSaving)));
        } else {
            tvTitleTotalSaving.setVisibility(View.GONE);
            tvTotalSaving.setVisibility(View.GONE);
        }

        if (UserModel.Cust_mst_ID != null) {
            loyaltyReward(liShoppingCard);
        }
        onCheckLegend();
    }

    private void loyaltyReward(List<ShoppingCardModel> liShoppingCard) {
        Float _loyaltyPoint = 0.0f;
        //tvLoyaltyReward.setVisibility(View.VISIBLE);

        Integer subTotal;

        //isAdded();

        subTotal = Math.round(_subTotal);

        DecimalFormat df = new DecimalFormat("####0.00");
        df.setMaximumFractionDigits(2);
        //df.setRoundingMode(RoundingMode.UP);

        Log.e(TAG, "result  :  " + Math.round(_subTotal) + " instends of " + _subTotal);
        Log.e(TAG, "result  :  " + ((_subTotal % 1) > 0.5));
        Log.e(TAG, "result  :  " + ((_subTotal % 0) > 0.5));
        Log.e(TAG, "result  :  " + ((_subTotal % 1) > 0.5));
        //Log.e(TAG, "result  :  " + ((Math.round(_subTotal) % 0) > 0.5) );

        for (int i = 0; i < liShoppingCard.size(); i++) {

            // if (!liShoppingCard.get(i).getLoyaltyOn().isEmpty())
            if (liShoppingCard.get(i).getLoyaltyOn().trim().equals("onItem")) {
                //tvLoyaltyReward.setText("Loyalty Reward is on");
                _lPoints = Float.valueOf(liShoppingCard.get(i).getLoyaltyPointsOnItem()) + _total;
                if (((Float.valueOf(liShoppingCard.get(i).getLoyaltyPointsOnItem()) * _total) % 1) > 0.5) {
                    _lPoints = Float.valueOf(liShoppingCard.get(i).getLoyaltyPointsOnItem()) * _total;
                }
            }

//            Edited by Varun for not going in when loyalityreward enable or disable
            if (!liShoppingCard.get(i).getIsLoyaltyRewardEnable().equals("N")) {
//                END

                if (liShoppingCard.get(i).getIsLoyaltyRewardEnable().equals("Y") && !liShoppingCard.get(i).getLoyaltyCardNo().equals("")){
                    if(liShoppingCard.get(i).getLoyaltyType().equals("Internal") && liShoppingCard.get(i).getLoyaltyOn().equals("onItem")) {

                        _loyaltyPoint = _loyaltyPoint + _lPoints;
                        final_point = _loyaltyPoint;
                    }
                }

                if (liShoppingCard.get(i).getIsLoyaltyRewardEnable().equals("Y") && !liShoppingCard.get(i).getLoyaltyCardNo().equals("")){
                    if ( liShoppingCard.get(i).getLoyaltyType().equals("Internal")
                            && liShoppingCard.get(i).getLoyaltyOn().equals("onTotal") || liShoppingCard.get(i).getLoyaltyOn().equals("onItem")) {

                        //if ((Math.round(_subTotal) % 1) > 0.5){
                        _loyaltyPoint = _subTotal;
                        _lPoints = _loyaltyPoint;
                        final_point = _lPoints;
                        if (!isAdded())
                            return;
                        tvTitleLoyaltyReward.setText(getResources().getString(R.string.legend_loyalty_reward) + " ");
                        tvLoyaltyReward.setText(String.valueOf(Math.round(_subTotal)));
                        //}

                    }
                }else{
                    Constant._lPoints = _subTotal;
                }
            }

            for (int j = 0; j < liShoppingCard.size(); j++) {
                if (liShoppingCard.get(i).getIsLoyaltyRewardEnable().equals("Y") && _loyaltyPoint > 0) {
                    tvTitleLoyaltyReward.setVisibility(View.VISIBLE);
                    tvLoyaltyReward.setVisibility(View.VISIBLE);
                } else if (liShoppingCard.get(i).getIsLoyaltyRewardEnable().equals("N") && _loyaltyPoint < 0) {
                    tvTitleLoyaltyReward.setVisibility(View.GONE);
                    tvLoyaltyReward.setVisibility(View.GONE);
                } else {

                }
            }
            /*if (_loyaltyPoint > 0){
                tvTitleLoyaltyReward.setVisibility(View.VISIBLE);
                tvLoyaltyReward.setVisibility(View.VISIBLE);
            }*/
        }
    }

    public static void onCheckLegend() {
        vLegend.setVisibility(View.VISIBLE);
        rlLegend.setVisibility(View.VISIBLE);
        tvlegendSalesTax.setVisibility(View.GONE);
        tvlegendWineTax.setVisibility(View.GONE);
        tvlegendMiscTax.setVisibility(View.GONE);
        tvlegendFlatTax.setVisibility(View.GONE);
        tvlegendBottleDeposite.setVisibility(View.GONE);
        tvlegendNonTaxable.setVisibility(View.GONE);
        boolean _salesTax = false, _wineTax = false, _bottleDeposite = false;
        for (int i = 0; i < CardFragment.liShoppingCard.size(); i++) {
            //tvlegendSalesTax.setVisibility(View.GONE);
            if (!CardFragment.liShoppingCard.get(i).getTax11().isEmpty())
                tvlegendSalesTax.setVisibility(View.VISIBLE);
            if (!CardFragment.liShoppingCard.get(i).getTax22().isEmpty())
                tvlegendWineTax.setVisibility(View.VISIBLE);
            if (!CardFragment.liShoppingCard.get(i).getTax33().isEmpty())
                tvlegendMiscTax.setVisibility(View.VISIBLE);
            if (!CardFragment.liShoppingCard.get(i).getTax44().isEmpty())
                tvlegendFlatTax.setVisibility(View.VISIBLE);
            if (!CardFragment.liShoppingCard.get(i).getTax55().isEmpty())
                tvlegendBottleDeposite.setVisibility(View.VISIBLE);
            if (!CardFragment.liShoppingCard.get(i).getTax66().isEmpty())
                tvlegendNonTaxable.setVisibility(View.VISIBLE);
            /*if (CardFragment.liShoppingCard.get(i).getTax11().equals("T")) {
                tvlegendSalesTax.setVisibility(View.VISIBLE);
            }
            if (CardFragment.liShoppingCard.get(i).getTax22().equals("T2")) {
                tvlegendSalesTax.setVisibility(View.VISIBLE);
                tvlegendBottleDeposite.setVisibility(View.VISIBLE);
            }
            if (CardFragment.liShoppingCard.get(i).getTax33().equals("T T2")) {
                tvlegendWineTax.setVisibility(View.VISIBLE);
                tvlegendSalesTax.setVisibility(View.VISIBLE);
            }
            if (CardFragment.liShoppingCard.get(i).getTax44().equals("T T2 D")) {
                tvlegendWineTax.setVisibility(View.VISIBLE);
                tvlegendSalesTax.setVisibility(View.VISIBLE);
                tvlegendBottleDeposite.setVisibility(View.VISIBLE);
                break;
            }*/
            /*if (CardFragment.liShoppingCard.get(i).getIsSalesTax()) {
                tvlegendSalesTax.setVisibility(View.VISIBLE);
            }*/
            /*else {
                vLegend.setVisibility(View.GONE);
                rlLegend.setVisibility(View.GONE);
            }*/

        }
    }


    /*public static double roundMyData(double Rval, int no) {
        //no = No of decimal after . you want to print
        double p = (float) Math.pow(10, no);
        Rval = Rval * p;
        double tmp = Math.floor(Rval);
        return tmp / p;
    }

    public static double round(double time) {
        time = Math.round(100 * time);
        return time /= 100;
    }*/


    public static void onSetEmpty() {
        if (rlRoot != null)
            rlRoot.setVisibility(View.VISIBLE);
        if (llRootMain != null)
            llRootMain.setVisibility(View.GONE);
        if (llRootEmpty != null)
            llRootEmpty.setVisibility(View.VISIBLE);

    }

    @Override
    public void onCardItemRemoved(int position) {
        onRemoveFromWeb(position);
        liShoppingCard.remove(position);
        Constant.liCardModel = liShoppingCard;
//        Edited By Varun for pop-up of Added
//        DialogUtils.showDialog("Removed from Cart!");
//        END
        if (liShoppingCard.size() == 0) {
            onSetEmpty();
        } else {
            onCalculateTotal(liShoppingCard);
            recyclerView.removeViewAt(position);
            cardAdapter.notifyItemRemoved(position);
            cardAdapter.notifyItemRangeChanged(position, liShoppingCard.size());
            cardAdapter.notifyDataSetChanged();
        }
    }

    public void onRemoveFromWeb(int position) {
        String url;
        String invType;
        if(liShoppingCard.get(position).getInvType() != null){
            invType = liShoppingCard.get(position).getInvType();
        }else{
            invType = "I";
        }

        String itemIdSku = null;
        try {
            itemIdSku = URLEncoder.encode(liShoppingCard.get(position).getItemMstId().trim(), "utf-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
//              Edited by Varun for Crash Issue in Cart when we try to remove item when Sign in is not Done

        if (UserModel.Cust_mst_ID!=null && !UserModel.Cust_mst_ID.isEmpty()){
            String id = String.valueOf(liShoppingCard.get(position).getCartID());
            url = Constant.WS_BASE_URL + Constant.DELETE_CART + id + "/" + "Cart" + "/" + UserModel.Cust_mst_ID +
                    "/" + itemIdSku + "/" + "1" +
                    "/" + Constant.STOREID + "/" + "0" + "/" + "remove" + "/" + invType;
        }else{
            String id = String.valueOf(liShoppingCard.get(position).getCartID());
            url = Constant.WS_BASE_URL + Constant.DELETE_CART + id + "/" + "Cart" + "/" + "0" +
                    "/" + itemIdSku + "/" + "1" +
                    "/" + Constant.STOREID + "/" + "0" + "/" + "remove" + "/" + invType;
        }

//        /END

        // Test Url = "/InsertCartData/{ID}/{Type}/{CustomerID}/{ItemID}/{Qty}/{StoreNo}/{SessionId}/{OperationType}/{invType}";


        TaskDeleteCartItem deleteCartItem = new TaskDeleteCartItem();
        deleteCartItem.execute(url);
    }

    @Override
    public void onCardItemPlus(int position, int quantity) {
        onUpdateQuantityTask(position, quantity);
    }

    @Override
    public void onCardItemMinus(int position, int quantity) {
        onUpdateQuantityTask(position, quantity);
    }

    public void onUpdateQuantityTask(int position, int quantity) {
        Requested_Quantity=quantity;
        String updateQuantityUrl = "";
        String invType = "";
        if(liShoppingCard.get(position).getInvType() == null){
            invType = "I";
        }else{
            invType = liShoppingCard.get(position).getInvType();
        }

        Constant.Card_Fargment_Inv_type = invType;

        String itemIdSku = null;
        try {
            itemIdSku = URLEncoder.encode(liShoppingCard.get(position).getItemMstId().trim(), "utf-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        updateQuantityUrl = Constant.WS_BASE_URL + Constant.DELETE_CART          /*  http://192.168.172.211:889/WebStoreRestService.svc/InsertCartData/    */
                + liShoppingCard.get(position).getCartID() + "/"                 /*  3829/         */
                + "cart/" + UserModel.Cust_mst_ID + "/"                          /*  cart/188723/  */
                + itemIdSku + "/"                 /*  Item Id added  discussion after mamta/         */
                + quantity + "/"                                                 /*  4/            */
                + Constant.STOREID + "/" + "0/"                                 /*  707/0/        */
                + "updatecart" + "/" +invType                                                  /*  updatecart/I  */
        ;
        TaskUpdateCartQuantity updateCartQuantity = new TaskUpdateCartQuantity(this);
        updateCartQuantity.execute(updateQuantityUrl);
    }

    @Override
    public void onUpdateQuantityResult(UpdateCartQuantity updateCartQuantity) {
        if (updateCartQuantity.getResult().equalsIgnoreCase("success")) {
            onGetCartData();
        } else if(updateCartQuantity.getResult().equalsIgnoreCase("Not enough Stock")){
            //updateCartQuantity.
            Log.e("Log","Qty="+updateCartQuantity.getQty());
//            DialogUtils.notEnoughQuantityDialog(getActivity(),updateCartQuantity,Requested_Quantity,"", "");
            DialogUtils.notEnoughQuantityNewDialog(getActivity(),updateCartQuantity,Requested_Quantity,"", "","FromCardFrag");
            // Utils.setThemtoToast(getActivity(),"Not enough Stock");
            //Toast.makeText(getActivity(), ""+updateCartQuantity.getResult(), Toast.LENGTH_SHORT).show();

        }else {
//            Toast.makeText(getActivity(), getString(R.string.str_network_message), Toast.LENGTH_SHORT).show();
            //Toast.makeText(MainActivity.getInstance(), getString(R.string.str_network_message), Toast.LENGTH_SHORT).show();
        }
    }


    public static void initViews(View view) {

        swipeRefreshLayout = view.findViewById(R.id.swipe_to_refresh);
        swipeRefreshLayout.setBackgroundColor(Color.parseColor(Constant.themeModel.Backgroundcolor));

        //Fragment title
        tvFragmentTitle = view.findViewById(R.id.tv_card_title_card_fragment);
        tvFragmentTitle.setTextColor(Color.parseColor(Constant.themeModel.ThemeColor));
        tvFragmentEmptyTitle = view.findViewById(R.id.tv_empty_card_fragment);
        tvFragmentEmptyTitle.setTextColor(Color.parseColor(Constant.themeModel.ThemeColor));

        //Views for separator line
        vTotal = view.findViewById(R.id.view_total_card_fragment);
        vSalesTax = view.findViewById(R.id.view_sales_tex_card_fragment);
        vWineTax = view.findViewById(R.id.view_wine_tex_card_fragment);
        vBottleDeposit = view.findViewById(R.id.view_bottle_deposit_card_fragment);
        vMiscTax = view.findViewById(R.id.view_misc_tax_card_fragment);
        vFlatTax = view.findViewById(R.id.view_flat_tax_card_fragment);
        vShipping = view.findViewById(R.id.view_shipping_card_fragment);
        vButton = view.findViewById(R.id.view_button_next_card_fragment);
        vCheckBox = view.findViewById(R.id.view_check_box);
        vLegend = view.findViewById(R.id.view_legend);


        //LinearLayout for hide/show and grouping the layouts
        rlRoot = view.findViewById(R.id.rl_root);
        rlRoot.setVisibility(View.GONE);
        rlLegend = view.findViewById(R.id.rv_legend_card_fragment);

        llRootMain = view.findViewById(R.id.ll_root_item_card_fragment);
        llRootEmpty = view.findViewById(R.id.ll_root_empty_card_fragment);
        llRootTotal = view.findViewById(R.id.ll_root_total_card_fragment);
        llSubTotal = view.findViewById(R.id.ll_sub_totle_card_fragment);
        llSalesTax = view.findViewById(R.id.ll_sales_tex_card_fragment);
        llWineTax = view.findViewById(R.id.ll_wine_tex_card_fragment);
        llBottleDeposit = view.findViewById(R.id.ll_bottle_deposit_card_fragment);
        llMiscTax = view.findViewById(R.id.ll_misc_tax_card_fragment);
        llFlatTax = view.findViewById(R.id.ll_flat_tax_card_fragment);
        llShipping = view.findViewById(R.id.ll_shipping_card_fragment);
        llCheckBox = view.findViewById(R.id.ll_check_box_card_fragment);
        llTotal = view.findViewById(R.id.ll_total_card_fragment);

        //TextViews for title
        tvTitleSubtotal = view.findViewById(R.id.tv_title_sub_total_card_fragment);
        tvTitleSalesTex = view.findViewById(R.id.tv_title_sales_tex_card_fragment);
        tvTitleWineTax = view.findViewById(R.id.tv_title_wine_tex_card_fragment);
        tvTitleBottleTax = view.findViewById(R.id.tv_title_bottle_deposit_card_fragment);
        tvTitleMiscTax = view.findViewById(R.id.tv_title_misc_tax_card_fragment);
        tvTitleFlatTax = view.findViewById(R.id.tv_title_flat_tax_card_fragment);
        tvTitleShipping = view.findViewById(R.id.tv_title_shipping_card_fragment);
        tvTitleTotal = view.findViewById(R.id.tv_title_total_card_fragment);
        // tvTitleCheckBox = view.findViewById(R.id.tv_chechbox_title_card_fragment);

        //TextView for actual value
        tvSalesTex = view.findViewById(R.id.tv_sales_tex_card_fragment);
        tvSubtotal = view.findViewById(R.id.tv_sub_total_card_fragment);
        tvWineTax = view.findViewById(R.id.tv_wine_tex_card_fragment);
        tvBottleDeposit = view.findViewById(R.id.tv_bottle_deposit_card_fragment);
        tvMiscTax = view.findViewById(R.id.tv_misc_tax_card_fragment);
        tvFlatTax = view.findViewById(R.id.tv_flat_tax_card_fragment);
        tvShipping = view.findViewById(R.id.tv_shipping_card_fragment);
        tvTotal = view.findViewById(R.id.tv_total_card_fragment);
        tvTotalSaving = view.findViewById(R.id.tv_total_saving_card_fragment);
        tvTitleTotalSaving = view.findViewById(R.id.tv_title_total_saving_card_fragment);
        tvLoyaltyReward = view.findViewById(R.id.tv_loyalty_reward_card_fragment);
        tvTitleLoyaltyReward = view.findViewById(R.id.tv_title_loyalty_reward_card_fragment);
//        tvCheckBoxMessage = view.findViewById(R.id.tv_title_legal);
        tv_title_legal = view.findViewById(R.id.tv_title_legal);


        //TextView for Legend
        tvlegendSalesTax = view.findViewById(R.id.tv_legend_sales_tax_total_card_fragment);
        tvlegendWineTax = view.findViewById(R.id.tv_legend_wine_tax_card_fragment);
        tvlegendMiscTax = view.findViewById(R.id.tv_legend_misc_tax_card_fragment);
        tvlegendFlatTax = view.findViewById(R.id.tv_legend_flat_tax_card_fragment);
        tvlegendBottleDeposite = view.findViewById(R.id.tv_legend_bottle_deposit_card_fragment);
        tvlegendNonTaxable = view.findViewById(R.id.tv_legend_non_taxable_card_fragment);

        checkbox = view.findViewById(R.id.cbx_i_agree_card_fragment);
        BSTheme.setCheckBoxColor(checkbox, Color.parseColor(Constant.themeModel.ThemeColor), Color.GRAY);
        checkbox.setOnClickListener(CardFragment.getInstance());

        btnNext = view.findViewById(R.id.btn_next_card_fragment);
        GradientDrawable bgShape = (GradientDrawable) btnNext.getBackground();
        bgShape.setColor(Color.parseColor(Constant.themeModel.ThemeColor));
        btnNext.setOnClickListener(CardFragment.getInstance());

        btnContinueShopping = view.findViewById(R.id.btn_continue_shoping_card_fragment);
        GradientDrawable bgShapes = (GradientDrawable) btnContinueShopping.getBackground();
        bgShapes.setColor(Color.parseColor(Constant.themeModel.ThemeColor));
        btnContinueShopping.setOnClickListener(CardFragment.getInstance());

        btnContinueShoppingEmpty = view.findViewById(R.id.btn_continue_shopping_empty_card_fragment);
        GradientDrawable bgShapeEmpty = (GradientDrawable) btnContinueShoppingEmpty.getBackground();
        bgShapeEmpty.setColor(Color.parseColor(Constant.themeModel.ThemeColor));
        btnContinueShoppingEmpty.setOnClickListener(CardFragment.getInstance());

        recyclerView = view.findViewById(R.id.card_recycler_view);
        cvTotal = view.findViewById(R.id.card_total_rv_shopping_card);
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == btnNext.getId()) {
            if (Constant.twentyOneYear.getCustAgeValidOption() == 2) {
                if (!validate()) {
                    onNextPress();
                    //Toast.makeText(getActivity(), "coming soon : ", Toast.LENGTH_SHORT).show();
                } else {

                    return;
                }
            } else {
                onNextPress();
                //Toast.makeText(getActivity(), "feature is coming soon : ", Toast.LENGTH_SHORT).show();
            }
        }
        if (view.getId() == btnContinueShopping.getId()) {
            if (mCardEvent != null)
                mCardEvent.onContinueShoppingCartClicked();
        }
        if (view.getId() == btnContinueShoppingEmpty.getId()) {
            if (mCardEvent != null)
                mCardEvent.onContinueShoppingCartClicked();
        }
        //ColorDrawable colorDrawable = new ColorDrawable(Color.parseColor(Constant.themeModel.ThemeColor));

        /*if (view.getId() == checkbox.getId()) {
            if (checkbox.isChecked())
                checkbox.setDrawingCacheBackgroundColor(Color.parseColor(Constant.themeModel.ThemeColor));
                //tvCheckBoxMessage.setVisibility(View.GONE);
            //else
                //tvCheckBoxMessage.setVisibility(View.VISIBLE);
        }*/
    }

    private void onNextPress() {
//        Edited by Varun for lock up issue from tom in feedback 3 march

        if (UserModel.Cust_mst_ID == null || UserModel.Cust_mst_ID.equals("0")) {
            isComeFromCard = true;
            Login.StartLoginDialog("cart", getActivity());
        } else {
            shoppinCartDetais();
        }
    }

    public void shoppinCartDetais() {

        if(liShippingData != null){
//            if(liShippingData.getLastName() == null || liShippingData.getLastName().trim().isEmpty()|| liShippingData.getLastName().trim().equals(" ")){
//                DialogUtils.onWarningDialog(getActivity(), "Manage Account", "Your profile needs to be updated.");
//
//            }else if(liShippingData.getFirstName() == null || liShippingData.getFirstName().trim().isEmpty()|| liShippingData.getFirstName().trim().equals(" ")){
//                DialogUtils.onWarningDialog(getActivity(), "Manage Account", "Your profile needs to be updated.");
//
//            }else if(liShippingData.getCompanyName() == null || liShippingData.getCompanyName().trim().isEmpty()|| liShippingData.getCompanyName().trim().equals(" ")){
//                DialogUtils.onWarningDialog(getActivity(), "Manage Account", "Your profile needs to be updated.");
//
//            }else
            if(liShippingData.getPhone() == null || liShippingData.getPhone().trim().isEmpty()|| liShippingData.getPhone().trim().equals(" ")){
                DialogUtils.onWarningDialog(getActivity(), "Manage Account", "Your profile needs to be updated.");

            }else if(liShippingData.getAddress1() == null || liShippingData.getAddress1().trim().isEmpty()|| liShippingData.getAddress1().trim().equals(" ")){
                DialogUtils.onWarningDialog(getActivity(), "Manage Account", "Your profile needs to be updated.");

            }
//            else if(liShippingData.getAddress2() == null || liShippingData.getAddress2().trim().isEmpty()|| liShippingData.getAddress2().trim().equals(" ")){
//                DialogUtils.onWarningDialog(getActivity(), "Manage Account", "Your profile needs to be updated.");
//
//            }
            else if(liShippingData.getCity() == null || liShippingData.getCity().trim().isEmpty()|| liShippingData.getCity().trim().equals(" ")){
                DialogUtils.onWarningDialog(getActivity(), "Manage Account", "Your profile needs to be updated.");

            }else if(liShippingData.getState() == null || liShippingData.getState().trim().isEmpty() || liShippingData.getState().trim().equals(" ")){
                DialogUtils.onWarningDialog(getActivity(), "Manage Account", "Your profile needs to be updated.");

            }else if(liShippingData.getZip() == null || liShippingData.getZip().trim().isEmpty() || liShippingData.getZip().trim().equals(" ")){
                DialogUtils.onWarningDialog(getActivity(), "Manage Account", "Your profile needs to be updated.");

            }else{
//new added -------- bypass delivery oprtion screen - direct open payment screen

                onCallShippingListDataTask();

                //end **********
            }
        }
    }

    public void onCallShippingListDataTask() {
        if (UserModel.Cust_mst_ID != null) {
            String url = Constant.WS_BASE_URL + Constant.GET_SHIPPING_DATA + UserModel.Cust_mst_ID + "/" + Constant.STOREID;
            TaskShippingData taskShippingData = new TaskShippingData(this, false, getActivity());
            taskShippingData.execute(url);
        }
    }

    @Override
    public void onShippingTaskResult(List<ShippingData> liShippingData) {

        if (liShippingData.size() > 0) {
            if (liShippingData.get(0).getBSSetupPickUpStore()
                    && !liShippingData.get(0).getBSSetupPayAtStore()
                    && !liShippingData.get(0).getBSSetupHandDelivery()
                    && !liShippingData.get(0).getBSSetupUberRush()
//            Edited by Varun for if the set-up has only pick-up store and ship true the it will redirect to pay,emt screen as per our logic
//                    but now it is resolved and it is same as E-Com.
                    && !liShippingData.get(0).getBSSetupDeliveryOption()) {
//                END

//                if(Constant.twentyOneYear != null && Constant.twentyOneYear.getAllowPickUpTime()){
//                    loadOnlyStoreHoursWS();
//                    isBypassDeliveryOption = true;
//                }else{
//                    callNextFromCart();
//                }
                onCallGlobalSetup();
            }else{
                callNextFromCart();
            }
        }else{
            callNextFromCart();
        }
    }

    public void callNextFromCart() {

        Bundle i = new Bundle();
        i.putFloat("total", _total);
        i.putFloat("sub_total", _subTotal);
        i.putFloat("sales_tax", _salesTax);
        i.putFloat("wine_tax", _wineTax);
        i.putFloat("misc_tax", _miscTax);
        i.putFloat("flat_tac", _flatTax);
        i.putFloat("bottle_deposit", _bottleDeposit);
        i.putFloat("shipping", _shipping);
        i.putFloat("total_saving", _totalSaving);
//        Edited by Varun for not passing the loyalty reward point when it's not enable
        if (!final_point.equals(0.0f)) {
            i.putFloat("loyalty_reward", _lPoints);
        }
//        i.putFloat("loyalty_reward", _lPoints);
//        END

        if (mCardEvent != null)
            mCardEvent.onNextShoppingCart(i);
    }

    public boolean validate() {
        if (checkbox.isChecked()) {
            return false;
        } else {
            //Increase CheckBox Line height
            RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams) vButton.getLayoutParams();
            params.height = 5;
            vButton.setLayoutParams(params);
            //Change CheckBox line Color
            ColorDrawable colorDrawable = new ColorDrawable(getResources().getColor(R.color.red));
            vButton.setBackground(colorDrawable);
            //vLegend.setBackgroundColor(Color.parseColor(Constant.themeModel.ThemeColor));
            DialogUtils.on21YearCart(Constant.twentyOneYear,"age");
            return true;
        }
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
        mCardEvent = null;
    }

    @Override
    public void onResume() {
        super.onResume();

        if (checkbox != null) {
            checkbox.setChecked(false);
        }

        Constant.isloadWebview = false;

        if (Constant.SCREEN_LAYOUT == 1) {

            MainActivity.getInstance().llsearch.setVisibility(View.VISIBLE);

            if(Constant.isFromMic){
                MainActivity.getInstance().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);
                MainActivity.getInstance().mSearchedt.requestFocus();
                MainActivity.getInstance().mSearchedt.setFocusableInTouchMode(true);
                Constant.isFromMic = false;
            }else{
                MainActivity.getInstance().mSearchedt.clearFocus();
                MainActivity.getInstance().mSearchedt.setText("");
                Utils.hideKeyboard();
            }

        }else if (Constant.SCREEN_LAYOUT == 2) {

            MainActivityDup.getInstance().showbackButton();

            if(!MainActivityDup.getInstance().RecHorizontalList.isShown()){
                MainActivityDup.getInstance().RecHorizontalList.setVisibility(View.VISIBLE);
            }

            MainActivityDup.getInstance().llsearch.setVisibility(View.VISIBLE);

            if(Constant.isFromMicSeclayout){
                MainActivityDup.getInstance().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);
                MainActivityDup.getInstance().mSearchedt.requestFocus();
                MainActivityDup.getInstance().mSearchedt.setFocusableInTouchMode(true);
                Constant.isFromMicSeclayout = false;
            }else{
                MainActivityDup.getInstance().mSearchedt.clearFocus();
                MainActivityDup.getInstance().mSearchedt.setText("");
                Utils.hideKeyboard();
            }
        }

        Utils.hideKeyboard();
    }

    /*
    public static void onCardResult(List<CardModel> cardModels){
        //initViews(view);
        //this.cardModels = cardModels;
    *//*    cvTotal.setVisibility(View.VISIBLE);
        cardAdapter = new CardAdapter(MainActivity.getInstance(),cardModels);
        recyclerView.setAdapter(cardAdapter);
        cardAdapter.notifyDataSetChanged();
    *//*
    }*/

    public void loadStoreDeliveryHoursWS() {

        String Url = Constant.WS_BASE_URL + Constant.GET_DELIVERY_HOURS + "/" +  Constant.STOREID + "/" + "store";

        TaskStoreDeliveryHours taskStoreDeliveryHours = new TaskStoreDeliveryHours(this,getActivity(),"deliveryOption");
        taskStoreDeliveryHours.execute(Url);
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
                int todayTimediff = getTimeDifference(closeTime,"", today);

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

                int tomorrowTimediff = getTimeDifference(nextDayCloseTime,nextDayOpenTime,"tomorrow");

                gethourIntervals(tomorrowTimediff, nextDayCloseTime,nextDayOpenTime,tomorrowDay,"tomorrow");
            }

            DialogUtils.showPickupTimeDialog(getActivity(),results,isStoreClosedtoday,isStoreClosedtomorrow,today,tomorrowDay);
        }
    }

    private int getTimeDifference(String closeTime,String opentime, String diffDay) {

        Date date1 = null,date2 = null;
        long difference;
        int days;
        int hours = 0;
//            int min;

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
//                min = (int) (difference - (1000*60*60*24*days) - (1000*60*60*hours)) / (1000*60);
//                hours = (hours < 0 ? -hours : hours);
            }

        } catch (ParseException e) {
            e.printStackTrace();
        }
        return hours;
    }

    private void gethourIntervals(int hours, String closeTime, String nextDayOpenTime, String displayDay, String s) {

        SimpleDateFormat sdf = new SimpleDateFormat("h:mm a");
        Calendar calendar = null;

        int totalinterval = hours*5;
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


    public void onCallGlobalSetup() {

        if(Constant.SCREEN_LAYOUT==1){
            String twentyOneYearUrl = Constant.WS_BASE_URL + Constant.GET_GLOBALSETTING + Constant.STOREID;
            TaskTwentyOneYear taskTwentyOneYear = new TaskTwentyOneYear(this);
            taskTwentyOneYear.execute(twentyOneYearUrl);

        }else if(Constant.SCREEN_LAYOUT==2) {
            String twentyOneYearUrl = Constant.WS_BASE_URL + Constant.GET_GLOBALSETTING + Constant.STOREID;
            TaskTwentyOneYear taskTwentyOneYear = new TaskTwentyOneYear(this);
            taskTwentyOneYear.execute(twentyOneYearUrl);
        }
    }

    @Override
    public void onTwentyOneYearResult(TwentyOneYear twentyOneYear) {
//        this.twentyOneYear_Current = twentyOneYear;
        Constant.twentyOneYear = twentyOneYear;

        if(Constant.twentyOneYear != null && Constant.twentyOneYear.getAllowPickUpTime().toString().equalsIgnoreCase("true")){
            loadStoreDeliveryHoursWS();
            isBypassDeliveryOption = true;
        }else{
            callNextFromCart();
        }

    }


}
