package com.aspl.fragment;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.core.widget.NestedScrollView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.util.Log;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.aspl.Adapter.OrderSummaryGiftCardItemAdapter;
import com.aspl.Adapter.OrderSummaryItemAdapter;
import com.aspl.Utils.Constant;
import com.aspl.Utils.Utils;
import com.aspl.mbs.MainActivity;
import com.aspl.mbs.MainActivityDup;
import com.aspl.mbs.R;
import com.aspl.mbsmodel.InstorePurchaseDetailModel;
import com.aspl.mbsmodel.LstOrderTem;
import com.aspl.mbsmodel.OrderSummary;
import com.aspl.task.TaskGetOrderSummary;
import com.aspl.task.TaskGetPosCustomerID;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;



/**
 * Created by admin on 2/8/2018.
 */

public class OrderSummaryFragment extends Fragment implements View.OnClickListener
        /*, PaymentCartItemListAdapter.PaymentCartItemListAdapterEvent*/
        , TaskGetOrderSummary.TaskOrderSummaryEvent
        /*, TaskCustomerCard.TaskCustomerCardEvent*/ {


    public static final String TAG = OrderSummaryFragment.class.getSimpleName();
    String orderId;

    public Float _total = 0.0f, _subTotal = 0.0f, _salesTax = 0.0f, _wineTax = 0.0f, _miscTax = 0.0f, _flatTax = 0.0f, _bottleDeposit = 0.0f, _shipping = 0.0f, _totalSaving = 0.0f, _lPoints = 0.0f;

    public double _Tax1 = 0.0f, _Tax2 = 0.0f, _Tax3 = 0.0f, _Tax4 = 0.0f, _bottleDepositnew = 0.0f;
    NestedScrollView nestedScrollView;
    RecyclerView recyclerView ,recyclerViewGift_card_item;
    CardView cvOrderInfo, cv_notes, cvBillingAddressDetail, cvPayments, cvDelivery, cvShipping,
            cvDeliveryAddress, cv_pickup_time, cv_orderdetail_paidvia, cv_purchased_at;
    RelativeLayout rlRoot, rlOrderInfo, rlPaymentOption, rlFinancialData, rlDeliveryOption, rlRootBillingAddress, rlRootShippingAddress, rlRootDeliveryAddress;
    LinearLayout llTitlesCartItem, ll_order_summary_main, llSalesTax,
            llWineTax, llBottleDeposit, llMiscTax, llFlatTax, llSubTotal,
            llTotal, llShip, llTotalSaving, llLoyaltyReword, llButtonRoot,
            llMyReward, llRewardUse, llTip, llTitleOrderDetail, ll_orderdetail_pickuptime,
            ll_orderdetail_weborder, ll_order_detail_date, ll_order_detail_status ;
//            llOld_statictax_value, ll_dynamic_tax_sequence_with_value
//            ll_tax1, ll_tax2, ll_tax3, ll_tax4;

    //    Edited by Varun pick up hours and location
    LinearLayout ll_pick_up , ll_dayaftertomorrow;
    TextView tv_main_location, tv_1,tv_2,tv_3,tv_4,tv_5,tv_6,tv_7,tv_8,tv_9,tv_10,tv_11,tv_main_hours;

//    END

    View vTitleCartItem, vRV, vTotal, vSubTotal, vSalesTax, vWineTax,
            vBottleDeposit, vMiscTax, vFlatTax, vShip, vTotalSaving,
            vLoyaltyReward, vNoTaxAreBeingApplied, vMyReward, vRewardUse, vTip ;
    //            vTip,view_tax1, view_tax2, view_tax3, view_tax4;
    TextView tvTitleOrderInfo, tvTitleYourOrderSuccessful, tvTitlePayment;
    TextView tvTitleGiftWrap, tvTitleItemName, tvTitleItemPrice, tvTitleItemQuantity;
    TextView tvShipping, tvTitleSubTotal, tvTitleTotal, tvTitleSalesTax, tvTitleWineTax, tvTitleBottleDeposit, tvTitleMiscTax, tvTitleFlatTax, tvTitleTotalSaving, tvTitleLoyaltyReward, tvNoTaxAreBeingApplied, tvTitleMyReward, tvTitleRewardUse, tvTitleTip;
    TextView tvSubTotal, tvTotal, tvSalesTax, tvWineTax, tvBottleDeposit, tvMiscTax, tvFlatTax, tvShip, tvTotalSaving, tvLoyaltyReward, tvMyReward, tvRewardUse, tvApplyRewardCash, tvTip;

    TextView tvTitleBillingAddress, tvTitleShippingAddress, tvTitlePaymentOption, tvTitleDeliveryOption, tvGiftWrapFlag;
    TextView tvName, tvMobile, tvAddressOne, tvAddressTwo, tvCity, tvState, tvZip, tvPaymentOption, tvDeliveryOption;
    TextView tvsName, tvsMobile, tvsAddressOne, tvsAddressTwo, tvsCity, tvsState, tvsZip;
    TextView tvDName, tvDMobile, tvDAddressOne, tvDAddressTwo, tv_note_title, tv_note_text,
            tvDCity, tvDState, tvDZip, tv_pickup_time, tvTitleOrderDetail,
            tv_orderdetail_Delivery_method, tv_orderdetail_paidvia, tv_orderdetail_delivery_address,
            tv_date_Orderdetail_value, tv_weborder_orderDetail_value, tv_status_orderDetail_value,
            tv_cancel_order_orderdetail, tv_title_ext_order_detail, tv_orderdetail_pickuptime
//            tv_title_tax1, tv_tax1_value, tv_title_tax2, tv_tax2_value, tv_title_tax3,
//            tv_tax3_value, tv_title_tax4, tv_tax4value
            , tv_store_location_name, tv_store_address, tv_city_state_zip;

    public boolean _isWineTaxEnable = false, _isMiscTaxEnable = false, _isFlatTaxEnable = false, _isSaletexOrderDetail = false;
    public boolean _isTax1 = false, _isTax2 = false, _isTax3 = false, _isTax4 = false;

    public Button /*btnPlaceOrder, btnPrev,*/btnReturnToHome;
    String pickupTime = "";
    String pickupCurrentDay = "";
    String buttonclicked = "";
    String pickupDay = "";
    ImageView iv_close;
    boolean isFromOrderdatail = false;
    boolean isFromReturnProcessing = false;
    boolean isFromInstoreOrderdatail = false;

    String service_type = "";

    //PaymentCartItemListAdapter paymentAdapter;
    OrderSummaryItemAdapter itemAdapter;
    OrderSummaryGiftCardItemAdapter itemAdapter2;

    public static OrderSummaryFragment orderSummaryFragment;
    OrderSummaryEvent myOrderSummaryEvent;


    public interface OrderSummaryEvent {
        void continueShopping();
    }

    public static OrderSummaryFragment getInstence() {
        return orderSummaryFragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        orderSummaryFragment = this;
        setHasOptionsMenu(true);
        setRetainInstance(true);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_order_summary, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        recyclerView = view.findViewById(R.id.recycler_view_order_summary_fragment);
        recyclerViewGift_card_item = view.findViewById(R.id.recycler_view_Gift_Card_order_summary_fragment);

        LinearLayoutManager orderSummaryLayoutManager = null;
        LinearLayoutManager giftCardLayoutManager = null;

        if (Constant.SCREEN_LAYOUT == 1) {
            orderSummaryLayoutManager = new LinearLayoutManager(MainActivity.getInstance());
            giftCardLayoutManager = new LinearLayoutManager(MainActivity.getInstance());
        } else if (Constant.SCREEN_LAYOUT == 2) {
            orderSummaryLayoutManager = new LinearLayoutManager(MainActivityDup.getInstance());
            giftCardLayoutManager = new LinearLayoutManager(MainActivityDup.getInstance());
        }

        // Set the layout managers separately
        recyclerView.setLayoutManager(orderSummaryLayoutManager);
        recyclerView.setHasFixedSize(true);

        recyclerViewGift_card_item.setLayoutManager(giftCardLayoutManager);
        recyclerViewGift_card_item.setHasFixedSize(true);

/*
        itemAdapter = new PaymentCartItemListAdapter(MainActivity.getInstance(),
                this,
                Constant.liCardModel);
        recyclerView.setAdapter(paymentAdapter);
        paymentAdapter.notifyDataSetChanged();
*/

        initViews(view);

    }


    @Override
    public void onResume() {
        super.onResume();

        if(Constant.SCREEN_LAYOUT == 2){
            MainActivityDup.getInstance().showbackButton();
            MainActivityDup.getInstance().RecHorizontalList.setVisibility(View.GONE);
        }
////        **************** Edited by Varun for backbutton ********************
//        else if (Constant.SCREEN_LAYOUT==1){
//            if (Constant.check){
//                MainActivity.getInstance().ll_backbutton.setVisibility(View.VISIBLE);
//                Constant.check=false;
//            }else{
//                MainActivity.getInstance().ll_backbutton.setVisibility(View.GONE);
//                Constant.check=false;
//            }
//        }
//
////        ****************** End *******************

        if (Constant.orderSummaryTemp != null || Constant.buttonclicked_InstoreDetail.equals("Instore Purchases")) {
            if (Objects.requireNonNull(((AppCompatActivity) Objects.requireNonNull(getActivity())).getSupportActionBar()).isShowing()) {
//                Edited by Varun for Toolbar gone Issue when place order
                if (Constant.check){
                    MainActivity.getInstance().ll_backbutton.setVisibility(View.VISIBLE);
                    Constant.check=false;
                }else {
//                ?END
                    Objects.requireNonNull(((AppCompatActivity) Objects.requireNonNull(getActivity())).getSupportActionBar()).hide();
                    if (Constant.SCREEN_LAYOUT==1) {
                        MainActivity.getInstance().ll_backbutton.setVisibility(View.GONE);
//                        MainActivity.getInstance().ll_Reward_main.setVisibility(View.GONE);
                    }
                }
            }
        }

    }

    @Override
    public void onStop() {
        super.onStop();

        if (Constant.orderSummaryTemp != null || Constant.buttonclicked_InstoreDetail.equals("Instore Purchases")) {
            Objects.requireNonNull(((AppCompatActivity) Objects.requireNonNull(getActivity())).getSupportActionBar()).show();
            if (Constant.SCREEN_LAYOUT==1) {
                MainActivity.getInstance().ll_backbutton.setVisibility(View.VISIBLE);
            }
        }
    }


    /* @Override
     public void onGiftWrapChecked(int position, List<ShoppingCardModel> liShoppingCart) {

     }
 */
    private void initViews(View v) {
        // Main Titles
        tvTitleOrderInfo = v.findViewById(R.id.tv_title_order_info_order_summary_fragment);
        tvTitleOrderInfo.setTextColor(Color.parseColor(Constant.themeModel.ThemeColor));
        tvTitlePayment = v.findViewById(R.id.tv_title_detail_order_summary_fragment);
        tvTitlePayment.setTextColor(Color.parseColor(Constant.themeModel.ThemeColor));
        tv_note_title = v.findViewById(R.id.tv_note_title);
        tv_note_text = v.findViewById(R.id.tv_note_text);
        tvTitleYourOrderSuccessful = v.findViewById(R.id.tv_title_your_order_is_successfully_placed_order_summary_fragment);

        // Scroll Views
        nestedScrollView = v.findViewById(R.id.nested_scroll_order_summary_fragment);

        // Card Views
        cvOrderInfo = v.findViewById(R.id.cv_order_info_order_summary_fragment);
        cvBillingAddressDetail = v.findViewById(R.id.cv_payment_order_summary_fragment);
        cvDeliveryAddress = v.findViewById(R.id.cv_delivery_address_order_summary_fragment);
        cvPayments = v.findViewById(R.id.cv_payment_option_order_summary_fragment);
        cvDelivery = v.findViewById(R.id.cv_delivery_option_order_summary_fragment);
        cvShipping = v.findViewById(R.id.cv_shipping_address_summary_fragment);
        cv_notes = v.findViewById(R.id.cv_notes);
        cv_pickup_time = v.findViewById(R.id.cv_pickup_time);
        cv_purchased_at = v.findViewById(R.id.cv_purchased_at);

        // Relative Layout
        rlRoot = v.findViewById(R.id.rl_root_order_summary_fragment);
        rlOrderInfo = v.findViewById(R.id.rl_order_info_order_summary_fragment);
        rlFinancialData = v.findViewById(R.id.rl_financial_data_order_summary_fragment);
        rlRootBillingAddress = v.findViewById(R.id.rl_billing_address_order_summary_fragment);
        rlPaymentOption = v.findViewById(R.id.rl_payment_option_order_summary_fragment);
        rlDeliveryOption = v.findViewById(R.id.rl_delivery_option_order_summary_fragment);
        rlRootShippingAddress = v.findViewById(R.id.rl_shipping_address_order_summary_fragment);
        rlRootDeliveryAddress = v.findViewById(R.id.rl_delivery_address_order_summary_fragment);

        // Linear Layout
        ll_order_summary_main = v.findViewById(R.id.ll_order_summary_main);
        ll_order_summary_main.setBackgroundColor(Color.parseColor(Constant.themeModel.Backgroundcolor));
        llTitlesCartItem = v.findViewById(R.id.ll_title_cart_item_order_summary_fragment);
        llSalesTax = v.findViewById(R.id.ll_sales_tex_order_summary_fragment);
        llWineTax = v.findViewById(R.id.ll_wine_tex_order_summary_fragment);
        llBottleDeposit = v.findViewById(R.id.ll_bottle_deposit_order_summary_fragment);
        llMiscTax = v.findViewById(R.id.ll_misc_tax_order_summary_fragment);
        llFlatTax = v.findViewById(R.id.ll_flat_tax_order_summary_fragment);
        llSubTotal = v.findViewById(R.id.ll_sub_total_order_summary_fragment);
        llShip = v.findViewById(R.id.ll_shipping_order_summary_fragment);
        llTip = v.findViewById(R.id.ll_tip_order_summary_fragment);
        llTotal = v.findViewById(R.id.ll_total_order_summary_fragment);
        llTotalSaving = v.findViewById(R.id.ll_total_saving_order_summary_fragment);
        llLoyaltyReword = v.findViewById(R.id.ll_loyalty_reward_order_summary_fragment);
        llButtonRoot = v.findViewById(R.id.ll_button_order_summary_fragment);
        llMyReward = v.findViewById(R.id.ll_my_reward_order_summary_fragment);
//        llRewardUse = v.findViewById(R.id.ll_reward_use_order_summary_fragment);
//        llOld_statictax_value = v.findViewById(R.id.llOld_statictax_value);
//        ll_dynamic_tax_sequence_with_value = v.findViewById(R.id.ll_dynamic_tax_sequence_with_value);
        //dynamic start
//        ll_tax1 = v.findViewById(R.id.ll_tax1);
//        ll_tax2 = v.findViewById(R.id.ll_tax2);
//        ll_tax3 = v.findViewById(R.id.ll_tax3);
//        ll_tax4 = v.findViewById(R.id.ll_tax4);
        //end

        // views
        vTitleCartItem = v.findViewById(R.id.v_title_cart_item_order_summary_fragment);
        vRV = v.findViewById(R.id.view_rv_order_summary_fragment);
        vSalesTax = v.findViewById(R.id.view_sales_tex_order_summary_fragment);
        vWineTax = v.findViewById(R.id.view_wine_tex_order_summary_fragment);
        vBottleDeposit = v.findViewById(R.id.view_bottle_deposit_order_summary_fragment);
        vMiscTax = v.findViewById(R.id.view_misc_tax_order_summary_fragment);
        vFlatTax = v.findViewById(R.id.view_flat_tax_order_summary_fragment);
        //vSubTotal = v.findViewById(R.id.view_sales_tex_order_summary_fragment);
        vTotal = v.findViewById(R.id.view_total_order_summary_fragment);
        vShip = v.findViewById(R.id.view_shipping_order_summary_fragment);
        vTip = v.findViewById(R.id.view_tip_order_summary_fragment);
        vTotalSaving = v.findViewById(R.id.view_check_box);
        vLoyaltyReward = v.findViewById(R.id.view_total_saving_order_summary_fragment);
        vNoTaxAreBeingApplied = v.findViewById(R.id.view_no_tax_are_being_applied_order_summary_fragment);
//        vMyReward = v.findViewById(R.id.view_my_reward_order_summary_fragment);
//        vRewardUse = v.findViewById(R.id.view_reward_use_order_summary_fragment);
        //dynamic start
//        view_tax1 = v.findViewById(R.id.view_tax1);
//        view_tax2 = v.findViewById(R.id.view_tax2);
//        view_tax3 = v.findViewById(R.id.view_tax3);
//        view_tax4 = v.findViewById(R.id.view_tax4);
        //dynamic end

        // Title TextViews
        tvTitleGiftWrap = v.findViewById(R.id.tv_title_gift_wrap_order_summary_fragment);
        tvTitleItemName = v.findViewById(R.id.tv_title_item_order_summary_fragment);
        tv_pickup_time = v.findViewById(R.id.tv_pickup_time);

        LinearLayout.LayoutParams param = new LinearLayout.LayoutParams(
                0,
                ViewGroup.LayoutParams.WRAP_CONTENT,
                64
        );

        if (Constant.liCardModel != null) {
            Log.e("Log", "Constant.liCardModel size=" + Constant.liCardModel);
            for (int i = 0; i < Constant.liCardModel.size(); i++) {
                if (Constant.liCardModel.get(i) != null) {
                    if (Constant.liCardModel.get(i).getGiftWrap() != null && !Constant.liCardModel.get(i).getGiftWrap()) {
                        tvTitleGiftWrap.setVisibility(View.GONE);
                        tvTitleItemName.setLayoutParams(param);
                        break;
                    }
                }
            }
        }
        tvTitleItemQuantity = v.findViewById(R.id.tv_title_quantity_order_summary_fragment);
        tvTitleItemPrice = v.findViewById(R.id.tv_title_price_order_summary_fragment);

        tvTitleSalesTax = v.findViewById(R.id.tv_title_sales_tex_order_summary_fragment);
        tvTitleWineTax = v.findViewById(R.id.tv_title_wine_tex_order_summary_fragment);
        tvTitleBottleDeposit = v.findViewById(R.id.tv_title_bottle_deposit_order_summary_fragment);
        tvTitleMiscTax = v.findViewById(R.id.tv_title_misc_tax_order_summary_fragment);
        tvTitleFlatTax = v.findViewById(R.id.tv_title_flat_tax_order_summary_fragment);
        tvTitleSubTotal = v.findViewById(R.id.tv_title_sub_total_order_summary_fragment);
        tvTitleTotal = v.findViewById(R.id.tv_title_total_order_summary_fragment);
        tvTitleTip = v.findViewById(R.id.tv_title_tip_order_summary_fragment);
        tvTitleTotalSaving = v.findViewById(R.id.tv_title_total_saving_order_summary_fragment);
        tvTitleLoyaltyReward = v.findViewById(R.id.tv_title_loyalty_reward_order_summary_fragment);
        tvNoTaxAreBeingApplied = v.findViewById(R.id.tv_no_tax_being_applied_order_summary_fragment);
//        tvTitleMyReward = v.findViewById(R.id.tv_title_my_reward_order_summary_fragment);
//        tvTitleRewardUse = v.findViewById(R.id.tv_title_reward_use_order_summary_fragment);

        tvTitleBillingAddress = v.findViewById(R.id.tv_title_billing_address_order_summary_fragment);
        tvTitlePaymentOption = v.findViewById(R.id.tv_title_payment_option_order_summary_fragment);
        tvTitleDeliveryOption = v.findViewById(R.id.tv_title_delivery_option_order_summary_fragment);
        tvTitleShippingAddress = v.findViewById(R.id.tv_title_shipping_address_order_summary_fragment);
        tvShipping=v.findViewById(R.id.tv_title_shipping_order_summary_fragment);

        // TextViews
        tvSalesTax = v.findViewById(R.id.tv_sales_tex_order_summary_fragment);
        tvWineTax = v.findViewById(R.id.tv_wine_tex_order_summary_fragment);
        tvBottleDeposit = v.findViewById(R.id.tv_bottle_deposit_order_summary_fragment);
        tvMiscTax = v.findViewById(R.id.tv_misc_tax_order_summary_fragment);
        tvFlatTax = v.findViewById(R.id.tv_flat_tax_order_summary_fragment);
        tvSubTotal = v.findViewById(R.id.tv_sub_total_order_summary_fragment);
        tvTotal = v.findViewById(R.id.tv_total_order_summary_fragment);
        tvShip = v.findViewById(R.id.tv_shipping_order_summary_fragment);
        tvTip = v.findViewById(R.id.tv_tip_order_summary_fragment);
        tvTotalSaving = v.findViewById(R.id.tv_total_saving_order_summary_fragment);
        tvLoyaltyReward = v.findViewById(R.id.tv_loyalty_reward_order_summary_fragment);
//        tvMyReward = v.findViewById(R.id.tv_my_reward_order_summary_fragment);
//        tvRewardUse = v.findViewById(R.id.tv_reward_use_order_summary_fragment);
        //tvApplyRewardCash = v.findViewById(R.id.tv_reward_cash_available_order_summary_fragment);

        tvGiftWrapFlag = v.findViewById(R.id.tv_gift_wrap_flag_order_summary_fragment);
        tvName = v.findViewById(R.id.tv_name_order_summary_fragment);
        tvMobile = v.findViewById(R.id.tv_mobile_order_summary_fragment);
        tvAddressOne = v.findViewById(R.id.tv_address_one_order_summary_fragment);
        tvAddressTwo = v.findViewById(R.id.tv_address_two_order_summary_fragment);
        tvCity = v.findViewById(R.id.tv_city_order_summary_fragment);
        tvState = v.findViewById(R.id.tv_state_order_summary_fragment);
        tvZip = v.findViewById(R.id.tv_zip_order_summary_fragment);
        tvPaymentOption = v.findViewById(R.id.tv_payment_option_order_summary_fragment);
        tvDeliveryOption = v.findViewById(R.id.tv_delivery_option_order_summary_fragment);

        tvsName = v.findViewById(R.id.tv_shipping_name_order_summary_fragment);
        tvsMobile = v.findViewById(R.id.tv_shipping_mobile_order_summary_fragment);
        tvsAddressOne = v.findViewById(R.id.tv_shipping_address_one_order_summary_fragment);
        tvsAddressTwo = v.findViewById(R.id.tv_shipping_address_two_order_summary_fragment);
        tvsCity = v.findViewById(R.id.tv_shipping_city_order_summary_fragment);
        tvsState = v.findViewById(R.id.tv_shipping_state_order_summary_fragment);
        tvsZip = v.findViewById(R.id.tv_shipping_zip_order_summary_fragment);

        tvDName = v.findViewById(R.id.tv_name_delivery_order_summary_fragment);
        tvDMobile = v.findViewById(R.id.tv_mobile__delivery_order_summary_fragment);
        tvDAddressOne = v.findViewById(R.id.tv_delivery_address_one_order_summary_fragment);
        tvDAddressTwo = v.findViewById(R.id.tv_delivery_address_two_order_summary_fragment);
        tvDCity = v.findViewById(R.id.tv_city_delivery_order_summary_fragment);
        tvDState = v.findViewById(R.id.tv_state_delivery_order_summary_fragment);
        tvDZip = v.findViewById(R.id.tv_zip_delivery_order_summary_fragment);
        btnReturnToHome = v.findViewById(R.id.btn_return_to_home_order_summary_fragment);

        //order details
        llTitleOrderDetail = (LinearLayout) v.findViewById(R.id.llTitleOrderDetail);
        ll_orderdetail_pickuptime = (LinearLayout) v.findViewById(R.id.ll_orderdetail_pickuptime);
        ll_order_detail_status = (LinearLayout) v.findViewById(R.id.ll_order_detail_status);
        ll_order_detail_date = (LinearLayout) v.findViewById(R.id.ll_order_detail_date);
        ll_orderdetail_weborder = (LinearLayout) v.findViewById(R.id.ll_orderdetail_weborder);
        tvTitleOrderDetail = (TextView) v.findViewById(R.id.tvTitleOrderDetail);
        tv_weborder_orderDetail_value = v.findViewById(R.id.tv_weborder_orderDetail_value);
        tv_date_Orderdetail_value = v.findViewById(R.id.tv_date_Orderdetail_value);
        tv_status_orderDetail_value = v.findViewById(R.id.tv_status_orderDetail_value);
        tv_cancel_order_orderdetail = v.findViewById(R.id.tv_cancel_order_orderdetail);
        cv_orderdetail_paidvia = v.findViewById(R.id.cv_orderdetail_paidvia);
        cv_purchased_at = v.findViewById(R.id.cv_purchased_at);
        tv_orderdetail_Delivery_method = v.findViewById(R.id.tv_orderdetail_Delivery_method);
        tv_title_ext_order_detail = v.findViewById(R.id.tv_title_ext_order_detail);
//        dynamic start
//        tv_title_tax1 = v.findViewById(R.id.tv_title_tax1);
//        tv_tax1_value = v.findViewById(R.id.tv_tax1_value);
//        tv_title_tax2 = v.findViewById(R.id.tv_title_tax2);
//        tv_tax2_value = v.findViewById(R.id.tv_tax2_value);
//        tv_title_tax3 = v.findViewById(R.id.tv_title_tax3);
//        tv_tax3_value = v.findViewById(R.id.tv_tax3_value);
//        tv_title_tax4 = v.findViewById(R.id.tv_title_tax4);
//        tv_tax4value = v.findViewById(R.id.tv_tax4value);
        //dynamic end
        tv_store_location_name = v.findViewById(R.id.tv_store_location_name);
        tv_store_address = v.findViewById(R.id.tv_store_address);
        tv_city_state_zip = v.findViewById(R.id.tv_city_state_zip);
        tv_orderdetail_paidvia = v.findViewById(R.id.tv_orderdetail_paidvia);
        tv_orderdetail_pickuptime = v.findViewById(R.id.tv_orderdetail_pickuptime);
        ll_orderdetail_pickuptime = v.findViewById(R.id.ll_orderdetail_pickuptime);
        tv_orderdetail_delivery_address = v.findViewById(R.id.tv_title_delivery_address_order_summary_fragment);
        iv_close = v.findViewById(R.id.iv_close);

//        Edited by Varun pick up hours and location
        ll_pick_up=v.findViewById(R.id.ll_pick_up);
        ll_dayaftertomorrow=v.findViewById(R.id.dayaftertomorrow);
        tv_1 = v.findViewById(R.id.tv_1);
        tv_2 = v.findViewById(R.id.tv_2);
        tv_3 = v.findViewById(R.id.tv_3);
        tv_4 = v.findViewById(R.id.tv_4);
        tv_5 = v.findViewById(R.id.tv_5);
        tv_6 = v.findViewById(R.id.tv_6);
        tv_7 = v.findViewById(R.id.tv_7);
        tv_8 = v.findViewById(R.id.tv_8);
        tv_9 = v.findViewById(R.id.tv_9);
        tv_10 = v.findViewById(R.id.tv_10);
        tv_11 = v.findViewById(R.id.tv_11);

        Pick_up_location_and_hour();

//        END


        iv_close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (getFragmentManager() != null && getFragmentManager().getBackStackEntryCount() != 0) {
                    if (buttonclicked != null && !buttonclicked.isEmpty()) {
                        Constant.lastSelectedOrderDetailParent = buttonclicked;
                    }
                    getFragmentManager().popBackStack();
                }
            }
        });

        tv_cancel_order_orderdetail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (Constant.orderSummaryTemp.getOrderStatus().equalsIgnoreCase("Completed") && tv_cancel_order_orderdetail.getText().equals("Reorder")) {

                    if (Constant.orderSummaryTemp.getID() != null && !Constant.orderSummaryTemp.getID().isEmpty()) {
                        String orderid = Constant.orderSummaryTemp.getID();

                        if (Constant.SCREEN_LAYOUT == 1) {
                            MainActivity.getInstance().callReorderDetailsWS(orderid);

                        } else if (Constant.SCREEN_LAYOUT == 2) {
                            MainActivity.getInstance().callReorderDetailsWS(orderid);
                        }
                    }


                } else if (Constant.orderSummaryTemp.getOrderStatus().equalsIgnoreCase("Open") && tv_cancel_order_orderdetail.getText().equals("Cancel Order")) {

                    if (Constant.orderSummaryTemp.getID() != null && !Constant.orderSummaryTemp.getID().isEmpty()) {
                        String orderid = Constant.orderSummaryTemp.getID();

                        if (Constant.SCREEN_LAYOUT == 1) {
                            Utils.CancelOrderDialog(getActivity(), orderid, Constant.orderSummaryTemp);

                        } else if (Constant.SCREEN_LAYOUT == 2) {
                            Utils.CancelOrderDialog(getActivity(), orderid, Constant.orderSummaryTemp);
                        }
                    }

                }
            }
        });
    }

//    Edited by Varun for pick up hours and location

    private void Pick_up_location_and_hour() {

        if (Constant.rbPickUpAtStore && Constant.rbPayAtStore) {

            cvShipping.setVisibility(View.GONE);
            rlRootDeliveryAddress.setVisibility(View.GONE);
            cvBillingAddressDetail.setVisibility(View.GONE);
            rlRootBillingAddress.setVisibility(View.GONE);
            ll_pick_up.setVisibility(View.VISIBLE);
            tv_1.setText(Constant.contatInfo.getName().trim());
            tv_2.setText(Constant.contatInfo.getAddress().trim());
            tv_3.setText(Constant.contatInfo.getCity().trim() + "," + " " + Constant.contatInfo.getState().trim() + " " + Constant.contatInfo.getZip().trim());
            tv_4.setText(Constant.contatInfo.getPhone().trim());
            tv_5.setText(Constant.pick_up_time);

            if (Constant.liOnlyStoreHour != null && Constant.liOnlyStoreHour.size() > 0) {
                int j=1;
                int v=2;
                String today = Utils.getCurrentDay();
                String tomorrow = Utils.getNextDay(j);
                String dayaftertomorrow = Utils.getNextDay(v);
                boolean check = false;
                int pos = 0;
                for (int i = 0; i < Constant.liOnlyStoreHour.size(); i++) {
                    if (today.equals(Constant.liOnlyStoreHour.get(i).getStoreDay())) {
                        pos = i;
                        tv_6.setText(Constant.liOnlyStoreHour.get(pos).getStoreDay().trim());
                        if (!Constant.liOnlyStoreHour.get(pos).getClosed()) {
                            tv_7.setText(Constant.liOnlyStoreHour.get(pos).getOpenTime().trim() + " - " + Constant.liOnlyStoreHour.get(pos).getCloseTime().trim());
                        }else{
                            tv_7.setText("Store is closed.");
                        }                    }
                    if (tomorrow.equals(Constant.liOnlyStoreHour.get(i).getStoreDay())) {
                        pos = i;
                        tv_8.setText(Constant.liOnlyStoreHour.get(pos).getStoreDay().trim());
                        if (!Constant.liOnlyStoreHour.get(pos).getClosed()) {
                            tv_9.setText(Constant.liOnlyStoreHour.get(pos).getOpenTime().trim() + " - " + Constant.liOnlyStoreHour.get(pos).getCloseTime().trim());
                            check= false;
                        }else{
                            tv_9.setText("Store is closed.");
                            check = true;
                        }
                    }
                    j++;
//                    Edited b Varun for day after tomorrow display when tomorrow day store hours is close
                    if (check) {
                        ll_dayaftertomorrow.setVisibility(View.VISIBLE);
                        if (dayaftertomorrow.equals(Constant.liOnlyStoreHour.get(i).getStoreDay())) {
                            pos = i;
                            tv_10.setText(Constant.liOnlyStoreHour.get(pos).getStoreDay().trim());
                            if (!Constant.liOnlyStoreHour.get(pos).getClosed()) {
                                tv_11.setText(Constant.liOnlyStoreHour.get(pos).getOpenTime().trim() + " - " + Constant.liOnlyStoreHour.get(pos).getCloseTime().trim());
                            } else {
                                tv_11.setText("Store is closed.");
                            }
                        }
                        v++;
                    }
//                    END
                }
            }
        }

    }

//    END


    /**
     * Set Visibility
     **/
//    private void onSetVisibility(List<ShoppingCardModel> model) {
//        for (int i = 0; i < model.size(); i++) {
//            if (!model.get(i).getIsSalesTax()) {
//                llSalesTax.setVisibility(View.GONE);
//                vSalesTax.setVisibility(View.GONE);
//            }
//            if (!model.get(i).getIsWineTax()) {
//                llWineTax.setVisibility(View.GONE);
//                vWineTax.setVisibility(View.GONE);
//            }
//            if (!model.get(i).getIsMiscTax()) {
//                llMiscTax.setVisibility(View.GONE);
//                vMiscTax.setVisibility(View.GONE);
//            }
//            if (!model.get(i).getIsflat()) {
//                llFlatTax.setVisibility(View.GONE);
//                vFlatTax.setVisibility(View.GONE);
//            }
////            if (!model.get(i).getBSSetupDeliveryOption()) {
////                llShip.setVisibility(View.GONE);
////                vShip.setVisibility(View.GONE);
////            }
//            if (model.get(i).getIsTotalSavingDisplay()) {
//                vTotalSaving.setVisibility(View.VISIBLE);
//                llTotalSaving.setVisibility(View.VISIBLE);
//            }
//
//        }
//    }
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Bundle bundle = this.getArguments();
        if (bundle != null) {

            orderId = bundle.getString("orderId", "");

            pickupDay = bundle.getString("pickupDay", "");
            pickupTime = bundle.getString("pickupTime", "");
            pickupCurrentDay = bundle.getString("pickupCurrentDay", "");

            buttonclicked = bundle.getString("buttonclicked", "");

            isFromOrderdatail = bundle.getBoolean("isFromOrderdatail", false);

            isFromReturnProcessing = bundle.getBoolean("ReturnProcessing", false);

            isFromInstoreOrderdatail = bundle.getBoolean("isFromInstoreOrderDetail", false);

            if (Constant.twentyOneYear != null && Constant.twentyOneYear.getAllowPickUpTime()) {
                if (!pickupTime.isEmpty() && !pickupCurrentDay.isEmpty()) {
                    cv_pickup_time.setVisibility(View.VISIBLE);
                    if (pickupCurrentDay.equals("Tomorrow")) {
                        tv_pickup_time.setText("Pick Up Time:" + " " + pickupTime + " " + pickupDay);
                    } else {
                        tv_pickup_time.setText("Pick Up Time:" + " " + pickupTime + " " + pickupCurrentDay);
                    }
                }
            } else {
                cv_pickup_time.setVisibility(View.GONE);
            }

            //order details and order summary hide/unhide

            if (!isFromOrderdatail) { // ordersummary screen

                // show ordersummary field
                llTitlesCartItem.setVisibility(View.VISIBLE);
                tvTitleYourOrderSuccessful.setVisibility(View.VISIBLE);
                tvTitleOrderInfo.setVisibility(View.VISIBLE);

                llButtonRoot.setVisibility(View.VISIBLE);
                btnReturnToHome.setVisibility(View.VISIBLE);
                GradientDrawable bgShapes = (GradientDrawable) btnReturnToHome.getBackground();
                bgShapes.setColor(Color.parseColor(Constant.themeModel.ThemeColor));
                btnReturnToHome.setOnClickListener(this);
                tvTitlePayment.setVisibility(View.VISIBLE);
                tvName.setVisibility(View.VISIBLE);
                tvMobile.setVisibility(View.VISIBLE);
                tvsName.setVisibility(View.VISIBLE);
                tvsMobile.setVisibility(View.VISIBLE);
                tvDName.setVisibility(View.VISIBLE);
                tvDMobile.setVisibility(View.VISIBLE);
                cvDelivery.setVisibility(View.VISIBLE);
                cvPayments.setVisibility(View.VISIBLE);
                cv_purchased_at.setVisibility(View.GONE);
                llTitleOrderDetail.setVisibility(View.GONE);
                ll_orderdetail_weborder.setVisibility(View.GONE);
                ll_order_detail_date.setVisibility(View.GONE);
                ll_order_detail_status.setVisibility(View.GONE);
                tv_cancel_order_orderdetail.setVisibility(View.GONE);
                cv_orderdetail_paidvia.setVisibility(View.GONE);
                tv_title_ext_order_detail.setVisibility(View.GONE);


            } else { // order detail

                // orderdetail filed show
                // below are common hide and show for instore detail and online purchase details

                tvTitleYourOrderSuccessful.setVisibility(View.GONE);
                tvTitleOrderInfo.setVisibility(View.GONE);
                llButtonRoot.setVisibility(View.GONE);
                btnReturnToHome.setVisibility(View.GONE);
                tvTitlePayment.setVisibility(View.GONE);
                tvName.setVisibility(View.GONE);
                tvMobile.setVisibility(View.GONE);
                tvsName.setVisibility(View.GONE);
                tvsMobile.setVisibility(View.GONE);
                tvDName.setVisibility(View.GONE);
                tvDMobile.setVisibility(View.GONE);
                cvDelivery.setVisibility(View.GONE);
                cvPayments.setVisibility(View.GONE);

                tvTitleShippingAddress.setTextColor(getResources().getColor(R.color.black));

                tvTitleBillingAddress.setText("Billing Address: ");
                tvTitleBillingAddress.setTextColor(getResources().getColor(R.color.black));
                tv_orderdetail_delivery_address.setTextColor(getResources().getColor(R.color.black));
                tv_note_title.setTextColor(getResources().getColor(R.color.black));

                if (!isFromReturnProcessing) {
                    if (Constant.storeLocationList != null && Constant.storeLocationList.size() > 0) {
                        cv_purchased_at.setVisibility(View.VISIBLE);
                        for (int i = 0; i < Constant.storeLocationList.size(); i++) {

                            if (Constant.storeLocationList.get(i).getStoreno() != null &&
                                    !Constant.storeLocationList.get(i).getStoreno().isEmpty() &&
                                    Constant.storeLocationList.get(i).getStoreno().equals(Constant.SELECTED_LOCATION_STOREID)) {

                                if (Constant.storeLocationList.get(i).getName() != null && !Constant.storeLocationList.get(i).getName().isEmpty()) {
                                    tv_store_location_name.setVisibility(View.VISIBLE);
                                    tv_store_location_name.setText(Constant.storeLocationList.get(i).getName());
                                } else {
                                    tv_store_location_name.setVisibility(View.GONE);
                                }

                                if (Constant.storeLocationList.get(i).getAddress() != null && !Constant.storeLocationList.get(i).getAddress().isEmpty()) {
                                    tv_store_address.setVisibility(View.VISIBLE);
                                    tv_store_address.setText(Constant.storeLocationList.get(i).getAddress());
                                } else {
                                    tv_store_address.setVisibility(View.GONE);
                                }

                                String city_state_zip = "";

                                if (Constant.storeLocationList.get(i).getCity() != null && !Constant.storeLocationList.get(i).getCity().isEmpty()) {

                                    city_state_zip = Constant.storeLocationList.get(i).getCity() + ", ";
//                                tv_city_state_zip.setText(Constant.storeLocationList.get(i).getCity());
                                }

                                if (Constant.storeLocationList.get(i).getSt() != null && !Constant.storeLocationList.get(i).getSt().isEmpty()) {

                                    city_state_zip = city_state_zip + Constant.storeLocationList.get(i).getSt();
//                                tv_city_state_zip.setText(Constant.storeLocationList.get(i).getCity());
                                }

                                if (Constant.storeLocationList.get(i).getZip() != null && !Constant.storeLocationList.get(i).getZip().isEmpty()) {

                                    city_state_zip = city_state_zip + Constant.storeLocationList.get(i).getZip();

//                                tv_city_state_zip.setText(Constant.storeLocationList.get(i).getCity());
                                }

                                if (!city_state_zip.isEmpty()) {
                                    tv_city_state_zip.setText(city_state_zip);
                                    tv_city_state_zip.setVisibility(View.VISIBLE);
                                } else {
                                    tv_city_state_zip.setVisibility(View.GONE);
                                }

//                            tv_city_state_zip.setText(Constant.storeLocationList.get(i).getCity() +
//                                    Constant.storeLocationList.get(i).getSt() +
//                                    Constant.storeLocationList.get(i).getZip());
                                break;
                            }
                        }

                    } else {
                        cv_purchased_at.setVisibility(View.GONE);
                    }
                }

                if (isFromInstoreOrderdatail) { // instore purchase detail

                    if (Constant.instorePurchase_detailList != null && Constant.instorePurchase_detailList.size() > 0) {
                        tv_weborder_orderDetail_value.setVisibility(View.GONE);
                        cv_orderdetail_paidvia.setVisibility(View.GONE);
                        ll_order_detail_date.setVisibility(View.VISIBLE);
                        tv_title_ext_order_detail.setVisibility(View.VISIBLE);

                        if (Constant.instorePurchase_detailList.get(0).getOrderDate() != null && !Constant.instorePurchase_detailList.get(0).getOrderDate().isEmpty()) {
                            tv_date_Orderdetail_value.setText(Constant.instorePurchase_detailList.get(0).getOrderDate());
//                            tv_date_Orderdetail_value.setTypeface(null, Typeface.BOLD);
                        }

                        ll_order_detail_status.setVisibility(View.VISIBLE);
                        if (Constant.instorePurchase_detailList.get(0).getOrderStatus() != null && !Constant.instorePurchase_detailList.get(0).getOrderStatus().isEmpty()) {

                            tv_status_orderDetail_value.setText(Constant.instorePurchase_detailList.get(0).getOrderStatus());

//                            if (Constant.orderSummaryTemp.getOrderStatus().equalsIgnoreCase("open")) {
//                                tv_status_orderDetail_value.setTextColor(Objects.requireNonNull(getActivity()).getResources().getColor(R.color.green));
//
//                            } else if (Constant.orderSummaryTemp.getOrderStatus().equalsIgnoreCase("cancelled")) {
//                                tv_status_orderDetail_value.setTextColor(Objects.requireNonNull(getActivity()).getResources().getColor(R.color.red));
//                            } else {

                            tv_status_orderDetail_value.setTextColor(Objects.requireNonNull(getActivity()).getResources().getColor(R.color.blue_search));
//                            }
                        }

                        tv_cancel_order_orderdetail.setVisibility(View.GONE);
                    }

                } else {

                    //paid
                    tv_title_ext_order_detail.setVisibility(View.VISIBLE);

                    if (isFromReturnProcessing) {
                        cv_orderdetail_paidvia.setVisibility(View.GONE);
                    } else {
                        cv_orderdetail_paidvia.setVisibility(View.VISIBLE);
                    }
                    llTitleOrderDetail.setVisibility(View.VISIBLE);
                    ll_orderdetail_weborder.setVisibility(View.VISIBLE);

                    tv_weborder_orderDetail_value.setVisibility(View.VISIBLE);
                    if (Constant.orderSummaryTemp != null && Constant.orderSummaryTemp.getOrderID() != null && !Constant.orderSummaryTemp.getOrderID().isEmpty()) {
                        tv_weborder_orderDetail_value.setText("" + Constant.orderSummaryTemp.getOrderID());
                    }

                    ll_order_detail_date.setVisibility(View.VISIBLE);
                    if (Constant.orderSummaryTemp != null && Constant.orderSummaryTemp.getOrderDate() != null && !Constant.orderSummaryTemp.getOrderDate().isEmpty()) {
                        tv_date_Orderdetail_value.setText(Constant.orderSummaryTemp.getOrderDate());
                    }

                    ll_order_detail_status.setVisibility(View.VISIBLE);

                    if (isFromReturnProcessing) {
                        tv_status_orderDetail_value.setText("Return Invoice");
                        tv_status_orderDetail_value.setTextColor(Objects.requireNonNull(getActivity()).getResources().getColor(R.color.red));
                    } else {
                        if (Constant.orderSummaryTemp != null && Constant.orderSummaryTemp.getOrderStatus() != null && !Constant.orderSummaryTemp.getOrderStatus().isEmpty()) {
                            tv_status_orderDetail_value.setText(Constant.orderSummaryTemp.getOrderStatus());

                            if (Constant.orderSummaryTemp.getOrderStatus().equalsIgnoreCase("open")) {
                                tv_status_orderDetail_value.setTextColor(Objects.requireNonNull(getActivity()).getResources().getColor(R.color.green));

                            } else if (Constant.orderSummaryTemp.getOrderStatus().equalsIgnoreCase("cancelled")) {
                                tv_status_orderDetail_value.setTextColor(Objects.requireNonNull(getActivity()).getResources().getColor(R.color.red));
                            } else {

                                tv_status_orderDetail_value.setTextColor(Objects.requireNonNull(getActivity()).getResources().getColor(R.color.blue_search));
                            }
                        }

                        tv_cancel_order_orderdetail.setVisibility(View.VISIBLE);
                        tv_cancel_order_orderdetail.setTextColor(Color.parseColor(Constant.themeModel.ThemeColor));
                        tv_cancel_order_orderdetail.setPaintFlags(tv_cancel_order_orderdetail.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);

                        if (Constant.orderSummaryTemp.getOrderStatus().equalsIgnoreCase("open")) {
                            tv_cancel_order_orderdetail.setText("Cancel Order");

                        } else if (Constant.orderSummaryTemp.getOrderStatus().equalsIgnoreCase("Completed")) {
                            tv_cancel_order_orderdetail.setText("Reorder");

                        } else {
                            tv_cancel_order_orderdetail.setVisibility(View.GONE);
                        }
                    }

                }

            }

            //order detils variable ******

            llTitleOrderDetail.setBackgroundColor(Color.parseColor(Constant.themeModel.ThemeColor));

            tvTitleOrderDetail.setText("Order Details");
            tvTitleOrderDetail.setTextColor(Color.WHITE);
            tvTitleOrderDetail.setBackgroundColor(Color.parseColor(Constant.themeModel.ThemeColor));

            //end *******
        }

        if (Constant.orderSummaryTemp != null && isFromOrderdatail && !buttonclicked.equalsIgnoreCase("Instore Purchases")) {

//            if(isFromInstoreOrderdatail){
//                setordersummaryModel_fromInstorePurchaseDetailList(Constant.instorePurchase_detailList);
//            }else{

            if (isFromReturnProcessing) {
                //return processing
                setOrderDetailReturnProcessing(Constant.orderSummaryTemp);

            } else {
                //normal order details
                setOrderDetail(Constant.orderSummaryTemp);
            }

//            }

        } else if (isFromOrderdatail && isFromInstoreOrderdatail && buttonclicked.equalsIgnoreCase("Instore Purchases")) {

            setordersummaryModel_fromInstorePurchaseDetailList(Constant.instorePurchase_detailList);
//            buttonclicked = "";

        } else if (!orderId.isEmpty()) {

            callOrderSummaryResultWebService();
        }
    }

    private void setOrderDetailReturnProcessing(OrderSummary orderSummary) {

        onSetVisibility(orderSummary, null);
        setFinancialData(orderSummary, null);
        String orderstatus = "";

        if (orderSummary.getOrderStatus() != null && !orderSummary.getOrderStatus().isEmpty()) {
            orderstatus = orderSummary.getOrderStatus();
        }

        if (orderSummary.getLstGiftCardDetail()!=null){
            itemAdapter2 = new OrderSummaryGiftCardItemAdapter(orderSummary.getLstGiftCardDetail(), isFromOrderdatail, isFromInstoreOrderdatail, orderstatus, isFromReturnProcessing);
            recyclerViewGift_card_item.setAdapter(itemAdapter2);
            itemAdapter2.notifyDataSetChanged();
        }
        if (orderSummary.getLstOrderTems()!=null){
            itemAdapter = new OrderSummaryItemAdapter(orderSummary.getLstOrderTems(), isFromOrderdatail, isFromInstoreOrderdatail, orderstatus, isFromReturnProcessing);
            recyclerView.setAdapter(itemAdapter);
            itemAdapter.notifyDataSetChanged();
        }

    }

    private void setordersummaryModel_fromInstorePurchaseDetailList(List<InstorePurchaseDetailModel> instorePurchase_detailList) {

        List<InstorePurchaseDetailModel> ListsOf_instorePurchase_detail = instorePurchase_detailList;

        OrderSummary orderSummary_instorePurchase = new OrderSummary();

        orderSummary_instorePurchase.setBAddress1(ListsOf_instorePurchase_detail.get(0).getAddress1());
        orderSummary_instorePurchase.setBAddress2(ListsOf_instorePurchase_detail.get(0).getAddress2());
        orderSummary_instorePurchase.setBCity(ListsOf_instorePurchase_detail.get(0).getShopcity());
        orderSummary_instorePurchase.setBFName(ListsOf_instorePurchase_detail.get(0).getShopname());
        orderSummary_instorePurchase.setBLName(ListsOf_instorePurchase_detail.get(0).getShopaddress());
        orderSummary_instorePurchase.setBPhone(ListsOf_instorePurchase_detail.get(0).getShopphone());
        orderSummary_instorePurchase.setBState(ListsOf_instorePurchase_detail.get(0).getShopst());
        orderSummary_instorePurchase.setBZip(ListsOf_instorePurchase_detail.get(0).getShopzip());
        orderSummary_instorePurchase.setCustomerID(ListsOf_instorePurchase_detail.get(0).getCustEmailId());
        orderSummary_instorePurchase.setCustomerName(ListsOf_instorePurchase_detail.get(0).getCname());
        orderSummary_instorePurchase.setInvoiceNo(ListsOf_instorePurchase_detail.get(0).getInvoiceNo());
        orderSummary_instorePurchase.setOrderDate(ListsOf_instorePurchase_detail.get(0).getOrderDate());
        orderSummary_instorePurchase.setOrderStatus(ListsOf_instorePurchase_detail.get(0).getOrderStatus());
        orderSummary_instorePurchase.setOrderFinalTotal(String.valueOf(ListsOf_instorePurchase_detail.get(0).getTotal()));
        orderSummary_instorePurchase.setOrderSubTotal(String.valueOf(ListsOf_instorePurchase_detail.get(0).getSub()));
        orderSummary_instorePurchase.setLoyaltyPoints(String.valueOf(ListsOf_instorePurchase_detail.get(0).getPoints()));

        List<LstOrderTem> lstOrderTems_instore = new ArrayList<>();

        if (instorePurchase_detailList.get(0).getOrderDetailList() != null && instorePurchase_detailList.get(0).getOrderDetailList().size() > 0) {
            for (int i = 0; i < instorePurchase_detailList.get(0).getOrderDetailList().size(); i++) {
                LstOrderTem lstOrderTem = new LstOrderTem();

                lstOrderTem.setItemName(instorePurchase_detailList.get(0).getOrderDetailList().get(i).getItemName());
                lstOrderTem.setQty(instorePurchase_detailList.get(0).getOrderDetailList().get(i).getQty());
                lstOrderTem.setPrice(String.valueOf(instorePurchase_detailList.get(0).getOrderDetailList().get(i).getExtprice()));
                lstOrderTem.setItemPrice(String.valueOf(instorePurchase_detailList.get(0).getOrderDetailList().get(i).getPrice()));
                lstOrderTem.setOrderID(instorePurchase_detailList.get(0).getOrderDetailList().get(i).getInvoiceID());
                lstOrderTem.setPoints(String.valueOf(instorePurchase_detailList.get(0).getOrderDetailList().get(i).getPoints()));
                lstOrderTem.setSize(instorePurchase_detailList.get(0).getOrderDetailList().get(i).getSize());
                lstOrderTem.setSalesTax1(String.valueOf(instorePurchase_detailList.get(0).getTaxPerc()));

                lstOrderTems_instore.add(lstOrderTem);
            }
        }

        orderSummary_instorePurchase.setLstOrderTems(lstOrderTems_instore);


        setOrderDetailofDynamicdata(orderSummary_instorePurchase, ListsOf_instorePurchase_detail);

//        orderSummary_instorePurchase.setOrderTotal(ListsOf_instorePurchase_detail.get(0).getTotal());
//        orderSummary_instorePurchase.setPointUsed(ListsOf_instorePurchase_detail.get(0).getPoints());
//        orderSummary_instorePurchase.setSizeFlag(ListsOf_instorePurchase_detail.get(0).getSizeFlag());
//        orderSummary_instorePurchase.setStoreNo(ListsOf_instorePurchase_detail.get(0).getSto());


//        orderSummary_instorePurchase.setComments(ListsOf_instorePurchase_detail.get(0).);

//        orderSummary_instorePurchase.setEndRecord(ListsOf_instorePurchase_detail.get(0).getAddress2());
//        orderSummary_instorePurchase.setFlgtmpCashTip(ListsOf_instorePurchase_detail.get(0).getAddress2());
//        orderSummary_instorePurchase.setID(ListsOf_instorePurchase_detail.get(0).);

//        orderSummary_instorePurchase.setIsDeliverHome(ListsOf_instorePurchase_detail.get(0).getis());


//        orderSummary_instorePurchase.setIsHandDelivery(ListsOf_instorePurchase_detail.get(0).get());
//        orderSummary_instorePurchase.setIsPickupStore(ListsOf_instorePurchase_detail.get(0).getis());
//        orderSummary_instorePurchase.setIsTotalSavingDisplay(ListsOf_instorePurchase_detail.get(0).gettot());
//        orderSummary_instorePurchase.setIsUberRush(ListsOf_instorePurchase_detail.get(0).getAddress2());
//        orderSummary_instorePurchase.setLoyaltyPoints(ListsOf_instorePurchase_detail.get(0).get());
//        orderSummary_instorePurchase.setLoyaltyType(ListsOf_instorePurchase_detail.get(0).getAddress2());

//        orderSummary_instorePurchase.setOrderFinalTotal(ListsOf_instorePurchase_detail.get(0).getfin());
//        orderSummary_instorePurchase.setOrderID(ListsOf_instorePurchase_detail.get(0).get());

//        orderSummary_instorePurchase.setPaymentMedia(ListsOf_instorePurchase_detail.get(0).getAddress2());
//        orderSummary_instorePurchase.setPaymentType(ListsOf_instorePurchase_detail.get(0).getAddress2());

//        orderSummary_instorePurchase.setQty(ListsOf_instorePurchase_detail.get(0).qt());
//        orderSummary_instorePurchase.setResult(ListsOf_instorePurchase_detail.get(0).getAddress2());
//        orderSummary_instorePurchase.setRewardDollarAvailable(ListsOf_instorePurchase_detail.get(0).getAddress2());
//        orderSummary_instorePurchase.setRewardDollarUsed(ListsOf_instorePurchase_detail.get(0).getAddress2());
//        orderSummary_instorePurchase.setSAddress1(ListsOf_instorePurchase_detail.get(0).getAddress2());
//        orderSummary_instorePurchase.setSAddress2(ListsOf_instorePurchase_detail.get(0).getAddress2());
//        orderSummary_instorePurchase.setSCity(ListsOf_instorePurchase_detail.get(0).getAddress2());
//        orderSummary_instorePurchase.setSFName(ListsOf_instorePurchase_detail.get(0).getAddress2());
//        orderSummary_instorePurchase.setSLName(ListsOf_instorePurchase_detail.get(0).getAddress2());
//        orderSummary_instorePurchase.setSPhone(ListsOf_instorePurchase_detail.get(0).getAddress2());
//        orderSummary_instorePurchase.setSState(ListsOf_instorePurchase_detail.get(0).getAddress2());
//        orderSummary_instorePurchase.setSZip(ListsOf_instorePurchase_detail.get(0).getAddress2());
//        orderSummary_instorePurchase.setShipmentMessage(ListsOf_instorePurchase_detail.get(0).getAddress2());

//        orderSummary_instorePurchase.setStartRecord(ListsOf_instorePurchase_detail.get(0).());

//        orderSummary_instorePurchase.setTaxExamptMessage(ListsOf_instorePurchase_detail.get(0).gettaxex());
//        orderSummary_instorePurchase.setTaxExamptNo(ListsOf_instorePurchase_detail.get(0).getAddress2());
//        orderSummary_instorePurchase.setTipValue(ListsOf_instorePurchase_detail.get(0).getAddress2());
//        orderSummary_instorePurchase.setTotalItem(ListsOf_instorePurchase_detail.get(0).getAddress2());

//        orderSummary_instorePurchase.setTotalRecord(ListsOf_instorePurchase_detail.get(0).gett());
//        orderSummary_instorePurchase.setTotalSaving(ListsOf_instorePurchase_detail.get(0).get());
    }


    public void callOrderSummaryResultWebService() {
        String orderSummaryUrl;

        orderSummaryUrl = Constant.WS_BASE_URL + Constant.GET_ORDER_SUMMARY_DETAIL
                + orderId + "/" + Constant.STOREID;

        TaskGetOrderSummary orderSummary = new TaskGetOrderSummary(getActivity(), this, true, "");
//        orderSummary.execute(orderSummaryUrl);
        orderSummary.executeOnExecutor(TaskGetOrderSummary.THREAD_POOL_EXECUTOR,orderSummaryUrl);

    }

    @Override
    public void onOrderSummaryResult(OrderSummary orderSummary, String fromwhere) {
        //Toast.makeText(getActivity(), "Response : " + orderSummary.getOrderDate(), Toast.LENGTH_LONG).show();
//        onSetVisibility(orderSummary);
//        setFinancialData(orderSummary);
//
//        itemAdapter = new OrderSummaryItemAdapter(orderSummary.getLstOrderTems());
//        recyclerView.setAdapter(itemAdapter);
//        itemAdapter.notifyDataSetChanged();

        setOrderDetail(orderSummary);
//        Delivery Fee
    }


    private void setOrderDetail(OrderSummary orderSummary) {

        onSetVisibility(orderSummary, null);
        setFinancialData(orderSummary, null);
        String orderstatus = "";

        if (orderSummary.getOrderStatus() != null && !orderSummary.getOrderStatus().isEmpty()) {
            orderstatus = orderSummary.getOrderStatus();
        }

        if (orderSummary.getLstGiftCardDetail()!=null){
            itemAdapter2 = new OrderSummaryGiftCardItemAdapter(orderSummary.getLstGiftCardDetail(), isFromOrderdatail, isFromInstoreOrderdatail, orderstatus, isFromReturnProcessing);
            recyclerViewGift_card_item.setAdapter(itemAdapter2);
            itemAdapter2.notifyDataSetChanged();
        }
        if (orderSummary.getLstOrderTems()!=null){
            itemAdapter = new OrderSummaryItemAdapter(orderSummary.getLstOrderTems(), isFromOrderdatail, isFromInstoreOrderdatail, orderstatus, isFromReturnProcessing);
            recyclerView.setAdapter(itemAdapter);
            itemAdapter.notifyDataSetChanged();
        }
    }

    private void setOrderDetailofDynamicdata(OrderSummary orderSummary, List<InstorePurchaseDetailModel> listsOf_instorePurchase_detail) {

        onSetVisibility(orderSummary, listsOf_instorePurchase_detail);
        setFinancialData(orderSummary, listsOf_instorePurchase_detail);
        String orderstatus = "";

        if (orderSummary.getOrderStatus() != null && !orderSummary.getOrderStatus().isEmpty()) {
            orderstatus = orderSummary.getOrderStatus();
        }

        if (orderSummary.getLstGiftCardDetail()!=null){
            itemAdapter2 = new OrderSummaryGiftCardItemAdapter(orderSummary.getLstGiftCardDetail(), isFromOrderdatail, isFromInstoreOrderdatail, orderstatus, isFromReturnProcessing);
            recyclerViewGift_card_item.setAdapter(itemAdapter2);
            itemAdapter2.notifyDataSetChanged();
        }
        if (orderSummary.getLstOrderTems()!=null){
            itemAdapter = new OrderSummaryItemAdapter(orderSummary.getLstOrderTems(), isFromOrderdatail, isFromInstoreOrderdatail, orderstatus, isFromReturnProcessing);
            recyclerView.setAdapter(itemAdapter);
            itemAdapter.notifyDataSetChanged();
        }

    }

    public void onSetVisibility(OrderSummary orderSummary, List<InstorePurchaseDetailModel> listsOf_instorePurchase_detail) {

        if (orderSummary != null) {

            if (orderSummary.getLstOrderTems() != null && orderSummary.getLstOrderTems().size() > 0) {

                if (rlFinancialData.getVisibility() == View.GONE) {
                    rlFinancialData.setVisibility(View.VISIBLE);
                }

                if (isFromInstoreOrderdatail) {
//                    ll_dynamic_tax_sequence_with_value.setVisibility(View.VISIBLE);
//                    llOld_statictax_value.setVisibility(View.GONE);
//                    ll_dynamic_tax_sequence_with_value.setVisibility(View.GONE);
//                    llOld_statictax_value.setVisibility(View.VISIBLE);

                    if (listsOf_instorePurchase_detail != null && listsOf_instorePurchase_detail.size() > 0) {

                        if (listsOf_instorePurchase_detail.get(0).getTaxBit() != null && listsOf_instorePurchase_detail.get(0).getTaxBit()) {
                            _isTax1 = true;
                        }

                        if (listsOf_instorePurchase_detail.get(0).getTax2Bit() != null && listsOf_instorePurchase_detail.get(0).getTax2Bit()) {
                            _isTax2 = true;
                        }

                        if (listsOf_instorePurchase_detail.get(0).getTax3Bit() != null && listsOf_instorePurchase_detail.get(0).getTax3Bit()) {
                            _isTax3 = true;
                        }

                        if (listsOf_instorePurchase_detail.get(0).getFlatTaxBit() != null && listsOf_instorePurchase_detail.get(0).getFlatTaxBit()) {
                            _isTax4 = true;
                        }

                    }

                } else {
//                    llOld_statictax_value.setVisibility(View.VISIBLE);
//                    ll_dynamic_tax_sequence_with_value.setVisibility(View.GONE);

//                    llOld_statictax_value.setVisibility(View.VISIBLE);
//                    ll_dynamic_tax_sequence_with_value.setVisibility(View.GONE);

                    if (Constant.finalSalesTax > 0) {
                        llSalesTax.setVisibility(View.VISIBLE);
                        vSalesTax.setVisibility(View.VISIBLE);
                    } else {
                        llSalesTax.setVisibility(View.GONE);
                        vSalesTax.setVisibility(View.GONE);
                    }
                }

                for (int i = 0; i < orderSummary.getLstOrderTems().size(); i++) {
//                if (!orderSummary.getLstOrderTems().get(i).getIsSalesTax()) {
//                    llSalesTax.setVisibility(View.GONE);
//                    vSalesTax.setVisibility(View.GONE);
//                } else{
//                    llSalesTax.setVisibility(View.VISIBLE);
//                    vSalesTax.setVisibility(View.VISIBLE);
//                }

//                    if(!isFromInstoreOrderdatail){
                    if (orderSummary.getLstOrderTems().get(i).getIsSalesTax() != null && orderSummary.getLstOrderTems().get(i).getIsSalesTax()) {
                        _isSaletexOrderDetail = true;
                    }

                    if (orderSummary.getLstOrderTems().get(i).getIsWineTax() != null && orderSummary.getLstOrderTems().get(i).getIsWineTax()/* || _wineTax <= 0*/) {
                        _isWineTaxEnable = true;
                /*llWineTax.setVisibility(View.GONE);
                vWineTax.setVisibility(View.GONE);*/
                    }
                    if (orderSummary.getLstOrderTems().get(i).getIsMiscTax() != null && orderSummary.getLstOrderTems().get(i).getIsMiscTax() /*|| _miscTax <= 0*/) {
                        _isMiscTaxEnable = true;
                /*llMiscTax.setVisibility(View.GONE);
                vMiscTax.setVisibility(View.GONE);*/
                    }
                    if (orderSummary.getLstOrderTems().get(i).getIsflat() != null && orderSummary.getLstOrderTems().get(i).getIsflat() /*|| _flatTax <= 0*/) {
                        _isFlatTaxEnable = true;
                /*llFlatTax.setVisibility(View.GONE);
                vFlatTax.setVisibility(View.GONE);*/
                    }
//                    }

                    if (isFromOrderdatail && isFromInstoreOrderdatail) {
                        llShip.setVisibility(View.GONE);
                        vShip.setVisibility(View.GONE);
                    } else {
                        if (orderSummary.getIsShipTaxToOtherCountry() != null && !orderSummary.getIsShipTaxToOtherCountry().equals("null")&& !orderSummary.getIsShipTaxToOtherCountry()) {
                            llShip.setVisibility(View.GONE);
                            vShip.setVisibility(View.GONE);
                        } else {
                            llShip.setVisibility(View.VISIBLE);
                            vShip.setVisibility(View.VISIBLE);
                        }
                    }

            /*if (!orderSummary.getLstOrderTems().get(i).getGiftWrap()) {
                llGift.setVisibility(View.GONE);
                vGift.setVisibility(View.GONE);
            }*/
                    if (orderSummary.getIsTotalSavingDisplay() != null && orderSummary.getIsTotalSavingDisplay()
                            && !orderSummary.getIsTotalSavingDisplay().equals("null") && Float.parseFloat(orderSummary.getTotalSaving()) > 0) {
                        vTotalSaving.setVisibility(View.VISIBLE);
                        llTotalSaving.setVisibility(View.VISIBLE);
                    } else {
                        vTotalSaving.setVisibility(View.GONE);
                        llTotalSaving.setVisibility(View.GONE);
                    }

                    /** Hide Loyalty reward used layout **/
            /*if (Float.parseFloat(orderSummary.getRewardDollarUsed())  > 0) {
                vRewardUse.setVisibility(View.VISIBLE);
                llRewardUse.setVisibility(View.VISIBLE);
            }else{
                vRewardUse.setVisibility(View.GONE);
                llRewardUse.setVisibility(View.GONE);
            }*/

                    if (orderSummary.getLoyaltyPoints() != null && !orderSummary.getLoyaltyPoints().isEmpty()
                            && !orderSummary.getLoyaltyPoints().equalsIgnoreCase("null") && Float.parseFloat(orderSummary.getLoyaltyPoints()) > 0) {
                        llLoyaltyReword.setVisibility(View.VISIBLE);
                        vLoyaltyReward.setVisibility(View.VISIBLE);
                        if (isFromReturnProcessing) {
                            tvLoyaltyReward.setText("-"+orderSummary.getLoyaltyPoints());
                        }else{
                            tvLoyaltyReward.setText(orderSummary.getLoyaltyPoints());
                        }
                    } else {
                        llLoyaltyReword.setVisibility(View.GONE);
                        vLoyaltyReward.setVisibility(View.GONE);
                    }
                }
            }

            if (orderSummary.getComments() != null && !orderSummary.getComments().isEmpty() && !orderSummary.getComments().equals("null")) {
                cv_notes.setVisibility(View.VISIBLE);
                tv_note_title.setText("Order Notes: ");

//                if(orderSummary.getComments().contains("♠♥")){
//                    String notes = orderSummary.getComments().trim().replace("♠♥","\n");
//                    tv_note_text.setText(notes);
//                }

//                if(orderSummary.getComments().contains("&spades;&hearts;")){
//                    String notes = orderSummary.getComments().trim().replace("&spades;&hearts;","\n");
//                    tv_note_text.setText(notes);
//                }else{
//                    tv_note_text.setText(orderSummary.getComments().trim());
//                }

                if (orderSummary.getComments().contains("<br/>")) {
                    String notes = orderSummary.getComments().trim().replace("<br/>", "\n");
                    tv_note_text.setText(notes);
                } else {
                    tv_note_text.setText(orderSummary.getComments().trim());
                }

//                if(orderSummary.getComments() != null && !orderSummary.getComments().isEmpty()){
//                    tv_note_text.setText(orderSummary.getComments().trim());
//                }
            } else {
                cv_notes.setVisibility(View.GONE);
            }
        }
    }

    public void setFinancialData(OrderSummary orderSummary, List<InstorePurchaseDetailModel> listsOf_instorePurchase_detail) {
        DecimalFormat df = new DecimalFormat("####0.00");
        df.setMaximumFractionDigits(2);
        //df.setRoundingMode(RoundingMode.UP);


//        Edited by Janvi 2nd Oct ********

        String deliveryFee;
        if (orderSummary.getLstOrderTems() != null && orderSummary.getLstOrderTems().size() > 0
                && orderSummary.getLstOrderTems().get(orderSummary.getLstOrderTems().size() - 1).getItemName().equals("Delivery Fee")) {
//            int OrderListsize = orderSummary.getLstOrderTems().size();

//            if(orderSummary.getLstOrderTems().get(orderSummary.getLstOrderTems().size() - 1).getItemName().equals("Delivery Fee")){
            deliveryFee = orderSummary.getLstOrderTems().get(orderSummary.getLstOrderTems().size() - 1).getItemPrice();
            double TotalVal = Double.valueOf(orderSummary.getOrderSubTotal()) + Double.valueOf(deliveryFee);

            tvSubTotal.setText("$" + String.format(df.format(TotalVal)));

        } else {
            if (orderSummary.getOrderSubTotal() != null && !orderSummary.getOrderSubTotal().isEmpty()) {
                if (isFromReturnProcessing) {
                    tvSubTotal.setText("$-" + String.format(df.format(Double.parseDouble(orderSummary.getOrderSubTotal()))));
                } else {
                    tvSubTotal.setText("$" + String.format(df.format(Double.parseDouble(orderSummary.getOrderSubTotal()))));
                }
            }
        }

        //end ************

        tvTitleYourOrderSuccessful.setText(orderSummary.getBFName() + ", your order was successful!");

        if (isFromReturnProcessing) {
            tvTotal.setText("$-" + orderSummary.getOrderFinalTotal());
        } else {
            tvTotal.setText("$" + orderSummary.getOrderFinalTotal());
        }

        tvTotalSaving.setText("$" + orderSummary.getTotalSaving());


//        ***************** Edited by Varun for shipping charges ***************


        if (isFromInstoreOrderdatail){

        }else {
            if (!orderSummary.getShippingType().isEmpty()) {
                service_type = orderSummary.getShippingType();
                llShip.setVisibility(View.VISIBLE);
                tvShipping.setText(service_type);
                tvShip.setText("$" + String.format(df.format(Double.parseDouble(orderSummary.getShippingCharge()))));
            }
        }
//        tvShip.setText("$" + "0.00" /*orderSummary.getShipmentMessage()*/);

//        ********************* END ***************


//        tvRewardUse.setText("$" + orderSummary.getRewardDollarUsed());

//        if (isFromInstoreOrderdatail) {
//            if (listsOf_instorePurchase_detail != null && listsOf_instorePurchase_detail.size() > 0) {
////
////                if (listsOf_instorePurchase_detail.get(0).getTaxBit()!= null && listsOf_instorePurchase_detail.get(0).getTaxBit()) {
//                if (listsOf_instorePurchase_detail.get(0).getTaxBit() != null) {
//
//                    if (listsOf_instorePurchase_detail.get(0).getTaxPerc() != null) {
//                        _Tax1 = _Tax1 + listsOf_instorePurchase_detail.get(0).getTaxPerc();
//                    }
//
//                    if (listsOf_instorePurchase_detail.get(0).getTaxBit() && _Tax1 > 0) {
//                        ll_tax1.setVisibility(View.VISIBLE);
//                        view_tax1.setVisibility(View.VISIBLE);
//                        if (listsOf_instorePurchase_detail.get(0).getTaxName() != null && !listsOf_instorePurchase_detail.get(0).getTaxName().isEmpty()) {
//                            tv_title_tax1.setText(listsOf_instorePurchase_detail.get(0).getTaxName().trim());
//                            tv_tax1_value.setText("$" + String.format(df.format(_Tax1)));
//                        }
//
//                    } else {
//                        ll_tax1.setVisibility(View.GONE);
//                        view_tax1.setVisibility(View.GONE);
//                    }
//                }
//
//                if (listsOf_instorePurchase_detail.get(0).getTax2Bit() != null) {
//
//                    if (listsOf_instorePurchase_detail.get(0).getTax2Perc() != null) {
//                        _Tax2 = _Tax2 + listsOf_instorePurchase_detail.get(0).getTax2Perc();
//                    }
//
//                    if (listsOf_instorePurchase_detail.get(0).getTax2Bit() && _Tax2 > 0) {
//                        ll_tax2.setVisibility(View.VISIBLE);
//                        view_tax2.setVisibility(View.VISIBLE);
//
//                        if (listsOf_instorePurchase_detail.get(0).getTax2Name() != null && !listsOf_instorePurchase_detail.get(0).getTax2Name().isEmpty()) {
//                            tv_title_tax2.setText(listsOf_instorePurchase_detail.get(0).getTax2Name().trim());
//                            tv_tax2_value.setText("$" + String.format(df.format(_Tax2)));
//                        }
//                    } else {
//                        ll_tax2.setVisibility(View.GONE);
//                        view_tax2.setVisibility(View.GONE);
//                    }
//                }
//
//                if (listsOf_instorePurchase_detail.get(0).getTax3Bit() != null) {
//
//                    if (listsOf_instorePurchase_detail.get(0).getTax3Perc() != null) {
//                        _Tax3 = _Tax3 + listsOf_instorePurchase_detail.get(0).getTax3Perc();
//                    }
//
//                    if (listsOf_instorePurchase_detail.get(0).getTax3Bit() && _Tax3 > 0) {
//                        ll_tax3.setVisibility(View.VISIBLE);
//                        view_tax3.setVisibility(View.VISIBLE);
//
//                        if (listsOf_instorePurchase_detail.get(0).getTax3Name() != null && !listsOf_instorePurchase_detail.get(0).getTax3Name().isEmpty()) {
//                            tv_title_tax3.setText(listsOf_instorePurchase_detail.get(0).getTax3Name().trim());
//                            tv_tax3_value.setText("$" + String.format(df.format(_Tax3)));
//                        }
//                    } else {
//                        ll_tax3.setVisibility(View.GONE);
//                        view_tax3.setVisibility(View.GONE);
//                    }
//                }
//
//                if (listsOf_instorePurchase_detail.get(0).getFlatTaxBit() != null) {
//
//                    if (listsOf_instorePurchase_detail.get(0).getFlatTaxPerc() != null) {
//                        _Tax4 = _Tax4 + listsOf_instorePurchase_detail.get(0).getFlatTaxPerc();
//                    }
//
//                    if (listsOf_instorePurchase_detail.get(0).getFlatTaxBit() && _Tax4 > 0) {
//                        ll_tax4.setVisibility(View.VISIBLE);
//                        view_tax4.setVisibility(View.VISIBLE);
//
//                        if (listsOf_instorePurchase_detail.get(0).getFlatName() != null && !listsOf_instorePurchase_detail.get(0).getFlatName().isEmpty()) {
//                            tv_title_tax4.setText(listsOf_instorePurchase_detail.get(0).getFlatName().trim());
//                            tv_tax4value.setText("$" + String.format(df.format(_Tax4)));
//                        }
//                    } else {
//                        ll_tax4.setVisibility(View.GONE);
//                        view_tax4.setVisibility(View.GONE);
//                    }
//                }
//            }
//
//        } else {

        if (orderSummary.getLstOrderTems() != null && orderSummary.getLstOrderTems().size() > 0) {

            for (int i = 0; i < orderSummary.getLstOrderTems().size(); i++) {

                if (isFromOrderdatail) {
                    if (orderSummary.getLstOrderTems().get(i).getSalesTax1() != null && !orderSummary.getLstOrderTems().get(i).getSalesTax1().isEmpty() && !orderSummary.getLstOrderTems().get(i).getSalesTax1().equalsIgnoreCase("null")) {
                        _salesTax = _salesTax + Float.valueOf(orderSummary.getLstOrderTems().get(i).getSalesTax1());
                    }
                }
//            _salesTax = _salesTax + Float.valueOf(orderSummary.getLstOrderTems().get(i).getSalesTax1());

                if (orderSummary.getLstOrderTems().get(i).getWineTax2() != null && !orderSummary.getLstOrderTems().get(i).getWineTax2().isEmpty()) {
                    _wineTax = _wineTax + Float.valueOf(orderSummary.getLstOrderTems().get(i).getWineTax2());
                }

                if (orderSummary.getLstOrderTems().get(i).getMiscTax3() != null && !orderSummary.getLstOrderTems().get(i).getMiscTax3().isEmpty()) {
                    _miscTax = _miscTax + Float.valueOf(orderSummary.getLstOrderTems().get(i).getMiscTax3());
                }

                if (orderSummary.getLstOrderTems().get(i).getFlat() != null && !orderSummary.getLstOrderTems().get(i).getFlat().isEmpty()) {
                    _flatTax = _flatTax + Float.valueOf(orderSummary.getLstOrderTems().get(i).getFlat());
                }

//                _flatTax = _flatTax + Float.valueOf(orderSummary.getLstOrderTems().get(i).getFlat());
                //tvSalesTax.setText("$" + orderSummary.getLstOrderTems().get(i).getSalesTax1());
                //tvWineTax.setText("$" + orderSummary.getLstOrderTems().get(i).getWineTax2());
                //tvMiscTax.setText("$" + orderSummary.getLstOrderTems().get(i).getMiscTax3());
                //tvFlatTax.setText("$" + orderSummary.getLstOrderTems().get(i).getFlat());

                if (orderSummary.getLstOrderTems().get(i).getBottledeposit() != null && !orderSummary.getLstOrderTems().get(i).getBottledeposit().isEmpty()) {
                    _bottleDeposit = _bottleDeposit + Float.valueOf(orderSummary.getLstOrderTems().get(i).getBottledeposit());
                }
            }
        }

        if (_isWineTaxEnable && _wineTax > 0) {
            llWineTax.setVisibility(View.VISIBLE);
            vWineTax.setVisibility(View.VISIBLE);
        } else {
            llWineTax.setVisibility(View.GONE);
            vWineTax.setVisibility(View.GONE);
        }
        if (_isMiscTaxEnable && _miscTax > 0) {
            llMiscTax.setVisibility(View.VISIBLE);
            vMiscTax.setVisibility(View.VISIBLE);
        } else {
            llMiscTax.setVisibility(View.GONE);
            vMiscTax.setVisibility(View.GONE);
        }
        if (_isFlatTaxEnable && _flatTax > 0) {
            llFlatTax.setVisibility(View.VISIBLE);
            vFlatTax.setVisibility(View.VISIBLE);
        } else {
            llFlatTax.setVisibility(View.GONE);
            vFlatTax.setVisibility(View.GONE);
        }


        if (isFromOrderdatail) {

            if (isFromReturnProcessing) {
                llSalesTax.setVisibility(View.VISIBLE);
                vSalesTax.setVisibility(View.VISIBLE);
            } else {
                if (_isSaletexOrderDetail && _salesTax > 0) {
                    llSalesTax.setVisibility(View.VISIBLE);
                    vSalesTax.setVisibility(View.VISIBLE);
                } else {
                    llSalesTax.setVisibility(View.GONE);
                    vSalesTax.setVisibility(View.GONE);
                }
            }

            tvSalesTax.setText("$" + String.format(df.format(_salesTax)));
        } else {
            tvSalesTax.setText("$" + String.format(df.format(Constant.finalSalesTax)));
        }

        tvWineTax.setText("$" + String.format(df.format(_wineTax)));
        tvMiscTax.setText("$" + String.format(df.format(_miscTax)));
        tvFlatTax.setText("$" + String.format(df.format(_flatTax)));

//        }

        //_bottleDeposit = _bottleDeposit + Float.valueOf(orderSummary.getLstOrderTems().get(i).getBottledeposit());

        if (orderSummary.getIsHandDelivery().equals("True")) {
            vTip.setVisibility(View.VISIBLE);
            llTip.setVisibility(View.VISIBLE);
            if (orderSummary.getFlgtmpCashTip()) {
                tvTitleTip.setText("Cash TIP");
                tvTip.setText("$" + orderSummary.getTipValue());
            } else {
                tvTitleTip.setText("TIP");
                tvTip.setText("$" + orderSummary.getTipValue());
            }
        } else {
            vTip.setVisibility(View.GONE);
            llTip.setVisibility(View.GONE);
        }


        if (_bottleDeposit <= 0) {
            llBottleDeposit.setVisibility(View.GONE);
            vBottleDeposit.setVisibility(View.GONE);
        } else {
            tvBottleDeposit.setText("$" + String.format(df.format(_bottleDeposit)));
        }

        if (_lPoints > 0.0) {
            vLoyaltyReward.setVisibility(View.VISIBLE);
            llLoyaltyReword.setVisibility(View.VISIBLE);

            tvLoyaltyReward.setText("$" + orderSummary.getRewardDollarUsed());
        }

        //Set billing Address

//        ********************** Edited by Varun for billing address on Aug 2022 **********************

        if (orderSummary.getSAddress1()==""){
            if (!orderSummary.getIsPickupStore().equals("True") && !Constant.buttonclicked_InstoreDetail.equalsIgnoreCase("Instore Purchases")) {
                tvTitleBillingAddress.setText("To " + "\n" +
                        "Billing Address & Shipping Address: ");
                tvTitleBillingAddress.setTextColor(getResources().getColor(R.color.black));
            }
        }
//        else{
//            tvTitleShippingAddress.setTextColor(getResources().getColor(R.color.black));
//            tvTitleBillingAddress.setText("Billing Address ");
//        }

//        ************************* END ***************************

        tvName.setText(orderSummary.getBFName().trim() + " " + orderSummary.getBLName().trim());
        tvMobile.setText(orderSummary.getBPhone().trim());
        tvAddressOne.setText(orderSummary.getBAddress1().trim());
        if (!orderSummary.getBAddress2().trim().isEmpty())
            tvAddressTwo.setText(orderSummary.getBAddress2().trim());
        else {
            tvAddressTwo.setVisibility(View.GONE);
        }
//        tvCity.setText(orderSummary.getBCity().trim()+",");
        tvCity.setText(orderSummary.getBCity().trim() + ",");
        tvState.setText(orderSummary.getBState().trim());
        tvZip.setText(orderSummary.getBZip().trim());

        //Set delivery Address

        if (!isFromReturnProcessing) {
            if (orderSummary.getIsHandDelivery().equals("True")) {
                cvDeliveryAddress.setVisibility(View.VISIBLE);
                tvDName.setText(orderSummary.getSFName().trim() + " " + orderSummary.getSLName().trim());
                tvDMobile.setText(orderSummary.getSPhone().trim());
                tvDAddressOne.setText(orderSummary.getSAddress1().trim());
                if (!orderSummary.getSAddress2().trim().isEmpty())
                    tvDAddressTwo.setText(orderSummary.getSAddress2().trim());
                else {
                    tvDAddressTwo.setVisibility(View.GONE);
                }
//            tvDCity.setText(orderSummary.getSCity().trim()+",");
                tvDCity.setText(orderSummary.getSCity().trim() + ",");
                tvDState.setText(orderSummary.getSState().trim());
                tvDZip.setText(orderSummary.getSZip().trim());
            } else {
                cvDeliveryAddress.setVisibility(View.GONE);
            }
        } else {
            cvDeliveryAddress.setVisibility(View.GONE);
        }

        //Set Shipping Address
        if (!isFromReturnProcessing) {
            if (orderSummary.getIsShipTaxToOtherCountry() != null && orderSummary.getIsShipTaxToOtherCountry()) {
                Log.e(TAG, "setFinancialData: 22"+orderSummary.getIsShipTaxToOtherCountry().toString() );
                Log.e(TAG, "setFinancialData: 23"+orderSummary.getShipmentMessage().toString() );
                tvNoTaxAreBeingApplied.setVisibility(View.VISIBLE);
                tvNoTaxAreBeingApplied.setText(orderSummary.getShipmentMessage().trim());
                cvShipping.setVisibility(View.VISIBLE);
                tvsName.setText(orderSummary.getSFName().trim() + " " + orderSummary.getSLName().trim());
                tvsMobile.setText(orderSummary.getSPhone().trim());
                tvsAddressOne.setText(orderSummary.getSAddress1().trim());
                if (!orderSummary.getSAddress2().trim().isEmpty())
                    tvsAddressTwo.setText(orderSummary.getSAddress2().trim());
                else {
                    tvsAddressTwo.setVisibility(View.GONE);
                }
//            tvsCity.setText(orderSummary.getSCity().trim()+",");
                tvsCity.setText(orderSummary.getSCity().trim() + ",");
                tvsState.setText(orderSummary.getSState().trim());
                tvsZip.setText(orderSummary.getSZip().trim());
            }
//            Edited by Varun for shipping address to show whent he city and state is same from payment
//            else if (orderSummary.getSAddress1()!= null && !orderSummary.getSAddress1().isEmpty()
//                    && !orderSummary.getSAddress1().equalsIgnoreCase("")){
//
//                cvShipping.setVisibility(View.VISIBLE);
//                tvsName.setText(orderSummary.getSFName().trim() + " " + orderSummary.getSLName().trim());
//                tvsMobile.setText(orderSummary.getSPhone().trim());
//                tvsAddressOne.setText(orderSummary.getSAddress1().trim());
//                if (!orderSummary.getSAddress2().trim().isEmpty())
//                    tvsAddressTwo.setText(orderSummary.getSAddress2().trim());
//                else {
//                    tvsAddressTwo.setVisibility(View.GONE);
//                }
////            tvsCity.setText(orderSummary.getSCity().trim()+",");
//                tvsCity.setText(orderSummary.getSCity().trim() + ",");
//                tvsState.setText(orderSummary.getSState().trim());
//                tvsZip.setText(orderSummary.getSZip().trim());
//
//            }
//            END

            else {
                cvShipping.setVisibility(View.GONE);
                tvNoTaxAreBeingApplied.setVisibility(View.GONE);
            }
        } else {
            cvShipping.setVisibility(View.GONE);
            tvNoTaxAreBeingApplied.setVisibility(View.GONE);
        }

        /* Set Payment Option */
//        Log.d(TAG, "Payment Type: " + ""+ orderSummary.getPaymentType());
//        if (orderSummary.getPaymentType().equals("2") || orderSummary.getIsHandDelivery().equals("True")) {

        if (isFromOrderdatail && !isFromReturnProcessing) {

            if (!orderSummary.getPickupTime().trim().isEmpty()) {
                ll_orderdetail_pickuptime.setVisibility(View.VISIBLE);
                tv_orderdetail_pickuptime.setText(orderSummary.getPickupTime().trim());
            } else {
                ll_orderdetail_pickuptime.setVisibility(View.GONE);
            }

            if (orderSummary.getPaymentType() != null && orderSummary.getPaymentType().equals("2")) {

                //code for displayed card name in payment mode
                String paymentMediacard = "";
                if (orderSummary.getPaymentMedia() != null && !orderSummary.getPaymentMedia().isEmpty()) {
                    paymentMediacard = orderSummary.getPaymentMedia().trim();
                    tv_orderdetail_paidvia.setText(paymentMediacard);
                } else {
                    tv_orderdetail_paidvia.setText("Card ");
                }
                //by commenting below line uncomment thia above code

                if (isFromInstoreOrderdatail) {
                    cvBillingAddressDetail.setVisibility(View.GONE);
                } else {
                    cvBillingAddressDetail.setVisibility(View.VISIBLE);
                }
            } else if (orderSummary.getPaymentType() != null && orderSummary.getPaymentType().equals("1")) {

//                   if(orderSummary.getOrderStatus() != null && !orderSummary.getOrderStatus().isEmpty()
//                           && orderSummary.getOrderStatus().equalsIgnoreCase("processing")){
//
//                       tv_orderdetail_paidvia.setText("");
//                   }
                if (orderSummary.getOrderStatus() != null && !orderSummary.getOrderStatus().isEmpty()
                        && orderSummary.getOrderStatus().equalsIgnoreCase("Completed")) {

                    tv_orderdetail_paidvia.setText("Cash");

                } else {
                    tv_orderdetail_paidvia.setText("Payment Pending");
                }

                if (isFromInstoreOrderdatail) {
                    cvBillingAddressDetail.setVisibility(View.GONE);
                } else {
                    cvBillingAddressDetail.setVisibility(View.VISIBLE);
                }

//               cvBillingAddressDetail.setVisibility(View.VISIBLE);
            }

        } else {

            if (!isFromReturnProcessing) { //order summary
                if (orderSummary.getPaymentType() != null && orderSummary.getPaymentType().equals("2")) {

                    //code for displayed card name in payment mode
                    String paymentMediacard = "";
                    if (orderSummary.getPaymentMedia() != null && !orderSummary.getPaymentMedia().isEmpty()) {
                        paymentMediacard = orderSummary.getPaymentMedia().trim();
                        tvPaymentOption.setText(paymentMediacard);
                    } else {
                        tvPaymentOption.setText("Card ");
                    }
                    //by commenting below line uncomment thia above code

//            tvPaymentOption.setText("Card ");
                    cvBillingAddressDetail.setVisibility(View.VISIBLE);
                } else {
//               cvBillingAddressDetail.setVisibility(View.GONE);
                    cvBillingAddressDetail.setVisibility(View.VISIBLE);
                    tvPaymentOption.setText("Pay at store ");
                }
            } else if (isFromReturnProcessing) {
                cvBillingAddressDetail.setVisibility(View.GONE);
            }
        }

        if (!isFromReturnProcessing) {
            //means oreder summary

            if (orderSummary.getIsPickupStore().equals("True")) {
                tvDeliveryOption.setText("Pick up at store ");
                tv_orderdetail_Delivery_method.setText("Pick up at store ");

            } else if (orderSummary.getIsDeliverHome().equals("True")) {
                tvDeliveryOption.setText("Ship ");
                // Set Delivery Option
//            ******************** Edited by Varun for order status on 6 Aug 2022 *********************
                if(orderSummary.getOrderStatus().equalsIgnoreCase("Open")){
                    tv_orderdetail_Delivery_method.setText("Will be Shipped");
                }else if (orderSummary.getOrderStatus().equalsIgnoreCase("Completed")){
                    tv_orderdetail_Delivery_method.setText("Shipped");
                }else if (orderSummary.getOrderStatus().equalsIgnoreCase("processing")){
                    tv_orderdetail_Delivery_method.setText("Processing to be Shipped");
                }else if (orderSummary.getOrderStatus().equalsIgnoreCase("Cancelled")){
                    tv_orderdetail_Delivery_method.setText("Shipped");
                }
//            ****************** End **********************

            } else if (orderSummary.getIsUberRush().equals("True")) {
                tvDeliveryOption.setText("Uber Rush ");
                tv_orderdetail_Delivery_method.setText("Uber Rush ");

            } else if (orderSummary.getIsHandDelivery().equals("True")) {
                tvDeliveryOption.setText("Hand Delivery");
                tv_orderdetail_Delivery_method.setText("Hand Delivery");

            } else {
                tvDeliveryOption.setText("");
                tv_orderdetail_Delivery_method.setText("");

//                ***********************  END  *****************************

//            if (orderSummary.getIsPickupStore().equals("True")) {
//                tvDeliveryOption.setText("Pick up at store ");
//                tv_orderdetail_Delivery_method.setText("Pick up at store ");
//
//            } else if (orderSummary.getIsDeliverHome().equals("True")) {
//                tvDeliveryOption.setText("Ship ");
//                tv_orderdetail_Delivery_method.setText("Ship ");
//
//            } else if (orderSummary.getIsUberRush().equals("True")) {
//                tvDeliveryOption.setText("Uber Rush ");
//                tv_orderdetail_Delivery_method.setText("Uber Rush ");
//
//            } else if (orderSummary.getIsHandDelivery().equals("True")) {
//                tvDeliveryOption.setText("Hand Delivery");
//                tv_orderdetail_Delivery_method.setText("Hand Delivery");
//
//            } else {
//                tvDeliveryOption.setText("");
//                tv_orderdetail_Delivery_method.setText("");
            }
        }
    }

    @Override
    public void onClick(View v) {
        //Toast.makeText(getActivity(), "Under Development", Toast.LENGTH_SHORT).show();
        if (v.getId() == btnReturnToHome.getId()) {
//            Constant.check=false;
            //Toast.makeText(getActivity(), "OrderId" + orderId, Toast.LENGTH_SHORT).show();
           /* if (!orderId.isEmpty())
                callOrderSummaryResultWebService();*/
//            getFragmentManager().popBackStack();
//            getFragmentManager().popBackStackImmediate();

//            getFragmentManager().popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);


            int backStackEntry = 0;
            if (getFragmentManager() != null) {
                backStackEntry = getFragmentManager().getBackStackEntryCount();
            }
            if (backStackEntry > 0) {
                for (int i = 0; i < backStackEntry; i++) {
                    getFragmentManager().popBackStackImmediate();
                }
            }
//          Edited by Varun for pick up hours and location
            Constant.rbPayAtStore=false;
            Constant.rbPickUpAtStore=false;
            Constant.pick_up_time = "";
//            END
            if (Constant.SCREEN_LAYOUT == 1) {
                MainActivity.showHomePage();
                MainActivity.getInstance().loadHomeWebPage();

            } else if (Constant.SCREEN_LAYOUT == 2) {
                MainActivityDup.showHomePage();
                MainActivityDup.getInstance().loadHomeWebPage();
            }

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
        //this.menu = menu;
        //MenuItem item=menu.findItem(R.id.action_search);
        item.setVisible(false);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        myOrderSummaryEvent = (OrderSummaryEvent) context;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        myOrderSummaryEvent = (OrderSummaryEvent) activity;
    }

    @Override
    public void onDetach() {
        super.onDetach();
        myOrderSummaryEvent = null;
    }

    /*public void onBackPressed() {
        FragmentManager fm = getActivity().getSupportFragmentManager();
        fm.popBackStack();
    }*/

    public void backOrDismissFrag() {
        if (getFragmentManager() != null && getFragmentManager().getBackStackEntryCount() != 0) {
            getFragmentManager().popBackStack();
        }
    }
}
