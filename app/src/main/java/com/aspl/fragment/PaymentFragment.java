package com.aspl.fragment;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.Rect;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.Html;
import android.text.InputFilter;
import android.text.InputType;
import android.text.TextWatcher;
import android.util.Log;
import android.view.ContextMenu;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.view.WindowManager;
import android.view.inputmethod.EditorInfo;
import android.widget.AdapterView;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.aspl.Adapter.ImageAdapter;
import com.aspl.Adapter.PaymentCartItemListAdapter;
import com.aspl.Utils.BSTheme;
import com.aspl.Utils.Constant;
import com.aspl.Utils.CustomGridView;
import com.aspl.Utils.DeviceInfo;
import com.aspl.Utils.DialogUtils;
import com.aspl.Utils.Utils;
import com.aspl.Utils.Validator;
import com.aspl.mbs.MainActivity;
import com.aspl.mbs.MainActivityDup;
import com.aspl.mbs.R;
import com.aspl.mbsmodel.ActiveStatusModel;
import com.aspl.mbsmodel.CreditCardExpiryModel;
import com.aspl.mbsmodel.CreditCartSetting;
import com.aspl.mbsmodel.CustomerCard;
import com.aspl.mbsmodel.GiftWrap;
import com.aspl.mbsmodel.HomeItemModel;
import com.aspl.mbsmodel.InsertOrderDetailed;
import com.aspl.mbsmodel.LoyaltyInfo;
import com.aspl.mbsmodel.PayWareModel;
import com.aspl.mbsmodel.PaymentOptions;
import com.aspl.mbsmodel.PickupModel;
import com.aspl.mbsmodel.PinModel;
import com.aspl.mbsmodel.SaveCard;
import com.aspl.mbsmodel.ShippingData;
import com.aspl.mbsmodel.ShoppingCardModel;
import com.aspl.mbsmodel.SignupLoyaltyInfo;
import com.aspl.mbsmodel.UpdatePOSBillingAddress;
import com.aspl.mbsmodel.UserModel;
import com.aspl.task.TaskAdvancePaymentOptions;
import com.aspl.task.TaskCardExpireCheck;
import com.aspl.task.TaskCart;
import com.aspl.task.TaskCartListItem;
import com.aspl.task.TaskCartPayment;
import com.aspl.task.TaskCheckUSAePAYStatus;
import com.aspl.task.TaskCustomerCard;
import com.aspl.task.TaskCustomerData;
import com.aspl.task.TaskDeleteOrderForDemoStore;
import com.aspl.task.TaskGetCreditCardSetting;
import com.aspl.task.TaskGetScoopSetting;
import com.aspl.task.TaskGetValueInfoSetting;
import com.aspl.task.TaskGiftWrap;
import com.aspl.task.TaskInsertOrderDetail;
import com.aspl.task.TaskLoyaltyInfo;
import com.aspl.task.TaskPayWare;
import com.aspl.task.TaskSaveCard;
import com.aspl.task.TaskSign_Up_For_Loyalty;
import com.aspl.task.TaskUpdateBillingAddress;
import com.aspl.task.TaskUpdatePOSBillingAddress;
import com.aspl.ws.Async_getAddress;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.DateFormatSymbols;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Objects;

/**
 * Created by mic on 1/9/2018.
 */

public class PaymentFragment extends Fragment
        implements PaymentCartItemListAdapter.PaymentCartItemListAdapterEvent
        , View.OnClickListener
        , TaskPayWare.TaskPayWarEvent
        , TaskGiftWrap.TaskGiftWrapEvent
        , CompoundButton.OnCheckedChangeListener
        , TaskCustomerData.TaskCustomerEvent
        , TaskLoyaltyInfo.TaskLoyaltyInfoEvent
        , TaskGetCreditCardSetting.TaskGetCreditCardSettingEvent
        , TaskInsertOrderDetail.OrderDetailEvent
        , TaskSaveCard.TaskSaveCardEvent
        , TaskCustomerCard.TaskCustomerCardEvent
        , View.OnFocusChangeListener
        , TaskUpdateBillingAddress.UpdateBillingAddressEvent
        , TaskUpdatePOSBillingAddress.UpdatePOSBillingAddressEvent , TaskSign_Up_For_Loyalty.TaskSign_Up_For_Loyalty_Event
        , TaskCardExpireCheck.TaskCardExpiryCheckEvent, TaskCart.TaskCardEvent, TaskCartPayment.TaskCardPaymentEvent, TaskCartListItem.TaskCartListEvent,
        TaskAdvancePaymentOptions.TaskPaymentOptions,TaskCheckUSAePAYStatus.TaskCheckUSAePAYStatusEvent {

    public static final String TAG = PaymentFragment.class.getSimpleName();
    public static boolean isDhopDown = true;
    public Float _tipValue = 0.0f, _total = 0.0f, _subTotal = 0.0f, _salesTax = 0.0f, _wineTax = 0.0f, _miscTax = 0.0f, _flatTax = 0.0f, _bottleDeposit = 0.0f, _shipping = 0.0f, _totalSaving = 0.0f, _lPoints = 0.0f,_actualTotal = 0.0f;
    int selectedRadioButton = 0, tipOption = 0, _tipCCValue = 0;
    double base = 0.0;
    Float rewardDollar, rewardDollarUse = 0.0f, rewardAvailable, pointUsed, loyaltyPoints = 0.0f;
    Float finalTotal;
    String fourDigitCardNoValue = "";

    String S_name = "";

    Float deliveryFeeSurchargeVal;
    boolean isDeliveryFee;

    ProgressBar progressBar;
    NestedScrollView nestedScrollView;
    RecyclerView recyclerView;
    CardView cvOrderInfo, cvPayment, cvPaymentOptions,cv_pickup_time;
    RelativeLayout rlRoot, rl_main_payment_layout, rlOrderInfo, rlFinancialData, rlRootRewardCashAvailable, rlRootSaveCard, rlRootCardDetail, rlRootBillingAddress;
    LinearLayout llTitlesCartItem, llSalesTax, llWineTax, llBottleDeposit, llMiscTax, llFlatTax, llSubTotal, llTotal, llShip, llTotalSaving, llLoyaltyReword, llButtonRoot, llMyReward,  llTip, ll_Delivery_fee; //ll_Delivery_fee Edited by Janvi 1stOct end ******
    public static LinearLayout llHideCheckBox;
    View vTitleCartItem, vTotal, vSalesTax, vWineTax, vBottleDeposit, vMiscTax, vFlatTax, vShip, vTotalSaving, vLoyaltyReward, vNoTaxAreBeingApplied, vMyReward, vTip, view_subtotal; //Edited by Janvi 2nd Oct ** end*****
    TextView tvTitleOrderInfo, tvTitlePayment;
    TextView tvTitleGiftWrap, tvTitleItemName, tvTitleItemPrice, tvTitleItemQuantity,tv_pickup_time;
    TextView tvTitleSubTotal, tvTitleTotal, tvTitleSalesTax, tvTitleWineTax, tvTitleBottleDeposit, tvTitleMiscTax, tvTitleFlatTax, tvTitleShip, tvTitleTotalSaving, tvTitleLoyaltyReward, tvNoTaxAreBeingApplied, tvTitleMyReward, tvTitleTip;
    TextView tvS_name, tvSubTotal, tvTotal, tvSalesTax, tvWineTax, tvBottleDeposit, tvMiscTax, tvFlatTax,
            tvShip, tvTotalSaving, tvLoyaltyReward, tvMyReward, tvRewardCashAvailable,
            tvApplyRewardCash, tvTip , tv_warn_alert,
            tv_Delivery_fee,tv_notes; //tv_Delivery_fee Edited by Janvi 1stOct end ******

    TextView tvNewCard, tvTitleCartNumber, tvTitleCvv, tvTitleCartHolderName, tvTitleExpiration, tvTitleBillingAddress, tvTitleAddressOne, tvTitleAddressTwo, tvTitleZipOrPostal, tvNoteCartAlreadyOnFile, tvTitleRewardCashAvailable;
    public static EditText etCardNumber, etCvv, etCardHolderName, etExpiration, etAddressTwo, etZip, etCity, etState;
    public static CheckBox cbxSaveCard, cbxApplyRewardCash;
    public static AutoCompleteTextView etAddressOne;

    public Button btnPlaceOrder, btnPrev;

//    Edited by Varun for pick up hours and locally
    LinearLayout ll_pick_up , ll_dayaftertomorrow;
    TextView tv_main_location, tv_1,tv_2,tv_3,tv_4,tv_5,tv_6,tv_7,tv_8,tv_9,tv_10,tv_11,tv_main_hours;
//    END

//    Edited by Varun for join loyalty reward in e-com
    public LinearLayout ll_join_reward_payment_fragment;
    TextView tv_title_join_reward_payment_fragment;
    public CheckBox cbx_join_reward_payment_fragment;
//    ?END

    RelativeLayout rl_shipping_address;
    TextView tv_shipping_address , tv_shipping_address_one ,tv_Shipping_address_two , tv_Shipping_zip;
    EditText et_shipping_address_two , et_shipping_zip , et_shipping_city,et_shipping_state;
    AutoCompleteTextView autoCompleteTextView_address_one;

    boolean rbPickUpAtStore, rbPayAtStore, rbPayWithCart, rbUberRush, rbHandOnDelivery, rbShip, cbxShipDifferentAddress,isTipDialog;
    boolean isSaveCard = false;
    String storeAlert = "";
    String pickupTime = "";
    String pickupCurrentDay = "";
    String pickupDay = "";

    PaymentCartItemListAdapter paymentAdapter;
    public static PaymentFragment paymentFragment;
    public PaymentEvent myPaymentEvent;
    LoyaltyInfo l;

    String merchantCode = "0", merchantCustomerId = "0", merchantContractId = "0";
    String _orderId, _ctroutd, _troutd, _authCode, _paymentMedia, _paymentOption;
    String expMonth = "", expYear = "";
    String _tempCardNumber = "";
    String _tempDate = "";

    String currentPromoPrice , currentPrice , pPromoPrice, pPrice;


    Bundle i = new Bundle();

    TaskCustomerCard customerCard;
    TaskCardExpireCheck taskCardExpireCheck;
    CustomGridView gridView;
//    int notesCount = 0;

    //    static final String[] MOBILE_OS = new String[] {
//            "Android", "iOS","Windows", "Blackberry" };
    public String customernote = "";
    public String savednoteStatus = "N";
    List<ShoppingCardModel> realTimeInventoryList = new ArrayList<>();
    List<ShoppingCardModel> realTimePriceList = new ArrayList<>();

    public static PaymentFragment getInstance() {
        return paymentFragment;
    }


    public void loadOrderSummary() {
        if (myPaymentEvent != null) {

            myPaymentEvent.loadOrderSummaryFragment(i, "", "", "ReturnProcessing");
            Constant.check = true;
            //Hide dialog when order completed...
            //hideDialog();
        }
    }

    public void getCustomerCarddataWS() {

//        if (UserModel.Cust_mst_ID != null) {
//            String url = Constant.WS_BASE_URL + Constant.GET_CUSTOMER_CARD_DATA + UserModel.Cust_mst_ID + "/" + "Delete" + "/" + Constant.STOREID;
//            TaskCart taskCart = new TaskCart(this, "");
//            taskCart.execute(url);
//        }

        if (UserModel.Cust_mst_ID != null) {
//            String url = Constant.WS_BASE_URL + Constant.GET_CUSTOMER_CARD_DATA + UserModel.Cust_mst_ID + "/" + "Delete" + "/" + Constant.STOREID;
            String url = Constant.WS_BASE_URL + Constant.GET_CUSTOMER_CARD_DATA_V1 + UserModel.Cust_mst_ID + "/" + "Delete" + "/" + Constant.STOREID + Constant.ENCODE_TOKEN_ID;
            TaskCart taskCart = new TaskCart(this, "delete");
            taskCart.execute(url);
        }
    }

    public void getCustomerCarddataPaymentWS() {

        if (UserModel.Cust_mst_ID != null) {
            String url = Constant.WS_BASE_URL + Constant.GET_CUSTOMER_CARD_DATA_PAYMENT + UserModel.Cust_mst_ID + "/" + "Cart" + "/" + Constant.STOREID;
            TaskCartPayment taskCartPayment = new TaskCartPayment(getActivity(), this);
            taskCartPayment.execute(url);
        }
    }

    @Override
    public void onCardPaymentDataResult(List<ShoppingCardModel> liShoppingCard, String s) {
        boolean isNeedtoDisplayUpdate_popup = false;
        realTimeInventoryList.clear();
        realTimePriceList.clear();
        if(!liShoppingCard.get(0).getRealTime_Inventory().isEmpty() && liShoppingCard.get(0).getRealTime_Inventory().equalsIgnoreCase("True")) {
            for (int i = 0; i < liShoppingCard.size(); i++) {
                if (liShoppingCard.get(i).getSN().equalsIgnoreCase("Y")) {
                    ShoppingCardModel shoppingCardModel = liShoppingCard.get(i);
//                    Edited by Varun for Real time Inventory of Price
//                    for the Price and qty both change in one inventory
                    Float v = 0.0f;
                    v= v + Float.parseFloat(liShoppingCard.get(i).getCartPrice()) * Float.parseFloat(liShoppingCard.get(i).getQty());
//                    Edited by Varun for multi pack real time inventory check
                    if (!liShoppingCard.get(i).getInvType().equals("M")) {
                        if (Integer.parseInt(liShoppingCard.get(i).getQty()) > Integer.parseInt(liShoppingCard.get(i).getQtyOnHand())) {
//                        if (!_subTotal.equals(v)) {
                            if (!liShoppingCard.get(i).getPromoPrice().equals(Constant.Test.get(i).getPromoPrice())) {
//                                realTimeInventoryList.add(shoppingCardModel);
                                Constant.isCHECKOUT = true;
                            } else if (!liShoppingCard.get(i).getCartPrice().equals(Constant.Test.get(i).getCartPrice())) {
//                                realTimeInventoryList.add(shoppingCardModel);
                                Constant.isCHECKOUT = true;
                            }
//                        }
//
                            realTimeInventoryList.add(shoppingCardModel);
                            isNeedtoDisplayUpdate_popup = true;
                        }
////                    Edited by Varun for Real time Inventory of Price
//                        else if (!_subTotal.equals(v)) {
//                            if (!liShoppingCard.get(i).getPromoPrice().equals(Constant.Test.get(i).getPromoPrice())) {
//                                realTimeInventoryList.add(shoppingCardModel);
//                                Constant.isCHECKOUT = true;
//                            } else if (!liShoppingCard.get(i).getCartPrice().equals(Constant.Test.get(i).getCartPrice())) {
//                                realTimeInventoryList.add(shoppingCardModel);
//                                Constant.isCHECKOUT = true;
//                            }
//                        }
                    }else {
//                          END
                        if (Integer.parseInt(liShoppingCard.get(i).getQty()) * Integer.parseInt((String) liShoppingCard.get(i).getOunces()) > Integer.parseInt(liShoppingCard.get(i).getQtyOnHand())) {
                            if (!liShoppingCard.get(i).getPromoPrice().equals(Constant.Test.get(i).getPromoPrice())) {
                                Constant.isCHECKOUT = true;
                            } else if (!liShoppingCard.get(i).getCartPrice().equals(Constant.Test.get(i).getCartPrice())) {
                                Constant.isCHECKOUT = true;
                            }
                            realTimeInventoryList.add(shoppingCardModel);
                            isNeedtoDisplayUpdate_popup = true;
                        }
                    }
//                    Edited by Varun for Real time Inventory of Price
                    if (!_subTotal.equals(v)) {
                        if (!liShoppingCard.get(i).getPromoPrice().equals(Constant.Test.get(i).getPromoPrice())) {
                            realTimeInventoryList.add(shoppingCardModel);
                            Constant.isCHECKOUT = true;
                        } else if (!liShoppingCard.get(i).getCartPrice().equals(Constant.Test.get(i).getCartPrice())) {
                            realTimeInventoryList.add(shoppingCardModel);
                            Constant.isCHECKOUT = true;
                        }
                    }

//                     END
                }
            }
//            if (realTimeInventoryList.size() > 0 && isNeedtoDisplayUpdate_popup) {
//                Utils.showRealTimeInventoryDialog(getContext(), "Real time inventory", realTimeInventoryList);
//                isNeedtoDisplayUpdate_popup = false;
//
//            } else {
//                savePlaceOrder();
//            }
        }
        if (realTimeInventoryList.size() > 0 && isNeedtoDisplayUpdate_popup) {
            Utils.showRealTimeInventoryDialog(getContext(), "Real time inventory", realTimeInventoryList);
            isNeedtoDisplayUpdate_popup = false;
        }
////        Edited by Varun for real time inventory of Price
        else if (realTimeInventoryList.size() > 0 && Constant.isCHECKOUT){
//            Utils.showRealTimePriceDialog(getContext(), "Price", realTimePriceList);
            Utils.showRealTimeInventoryDialog(getContext(), "Price", realTimeInventoryList);
            Constant.isCHECKOUT=false;
        }
////        END
        else {
            savePlaceOrder();
        }
    }


    public void onReturnToHome() {

//        int backStackEntry = 0;
//        if (getFragmentManager() != null) {
//            backStackEntry = getFragmentManager().getBackStackEntryCount();
//        }
//        if (backStackEntry > 0) {
//            for (int i = 0; i < backStackEntry; i++) {
//                    getFragmentManager().popBackStackImmediate();
//            }
//        }


        FragmentManager fm = getActivity().getSupportFragmentManager();
        for(int i = 0; i < fm.getBackStackEntryCount(); ++i) {
            fm.popBackStack();
        }

        if (Constant.SCREEN_LAYOUT == 1) {
            MainActivity.showHomePage();
            MainActivity.getInstance().loadHomeWebPage();

        } else if (Constant.SCREEN_LAYOUT == 2) {
            MainActivityDup.showHomePage();
            MainActivityDup.getInstance().loadHomeWebPage();
        }
    }

    public void callWSFromRealTime_Okbutton() {

//        String Url7 = Constant.WS_BASE_URL + Constant.GET_CUSTOMERCARTDATA + UserModel.Cust_mst_ID + "/QtyHandling/" + Constant.STOREID;
        String Url7 = Constant.WS_BASE_URL + Constant.GET_CUSTOMER_CARD_DATA_V1 + UserModel.Cust_mst_ID + "/QtyHandling/" + Constant.STOREID +Constant.ENCODE_TOKEN_ID;
        TaskCartListItem taskCartListItem = new TaskCartListItem(this);
        taskCartListItem.execute(Url7);
    }

    @Override
    public void onGetCartListResult(List<HomeItemModel> HomeItemList) {
        if(HomeItemList != null && HomeItemList.size() > 0){
            if(HomeItemList.get(0).getMessage().toString().equalsIgnoreCase("Success")){
//                savePlaceOrder();
//                refresh same page again
//                onDemoStore();
                callcardwsForQuntityUpdate();

//                getCustomerData();
//                onSetVisibility(Constant.liCardModel);
//                showDialog();
//                setFinancialData();

                // getCustomerCardDetail();
//                setAdvancePaymentOptions();
            }
        }
    }

    private void callcardwsForQuntityUpdate() {

        String url = null;
        if (UserModel.Cust_mst_ID != null) {
//            url = Constant.WS_BASE_URL + Constant.GET_CUSTOMER_CARD_DATA + UserModel.Cust_mst_ID + "/" + Constant.MY_CART + Constant.STOREID;
            url = Constant.WS_BASE_URL + Constant.GET_CUSTOMER_CARD_DATA_V1 + UserModel.Cust_mst_ID + "/" + Constant.MY_CART + Constant.STOREID + Constant.ENCODE_TOKEN_ID;
            TaskCart taskCart = new TaskCart(paymentFragment, "realtime");
            taskCart.execute(url);
        } else {
//            url = Constant.WS_BASE_URL + Constant.GET_CUSTOMER_CARD_DATA + DeviceInfo.getDeviceId(getActivity()) + "0011" + "/" + Constant.SESSION + Constant.STOREID;
            url = Constant.WS_BASE_URL + Constant.GET_CUSTOMER_CARD_DATA_V1 + DeviceInfo.getDeviceId(getActivity()) + "0011" + "/" + Constant.SESSION + Constant.STOREID + Constant.ENCODE_TOKEN_ID;
            TaskCart taskCart = new TaskCart(paymentFragment, "realtime");
            taskCart.execute(url);
        }
    }

    @Override
    public void onShoppingCardResult(List<ShoppingCardModel> liShoppingCard, String s) {

//        Edited by Varun for crash issue in Place Order BTN
        if (liShoppingCard.get(0).getCartID()==0){
            MainActivity.getInstance().CallHomeFragment();
        }else {
            if (s.equalsIgnoreCase("delete")) {

                Utils.showValidationDialog(getContext(), "You order was canceled and the items have been cleared from your cart.", "");

            } else if (s.equalsIgnoreCase("realtime")) {

                Constant.isfromRealTime = true;

                Constant.forRealtimeList = liShoppingCard;

                if (getFragmentManager() != null) {
                    getFragmentManager().beginTransaction().detach(this).attach(this).commit();
                }

//
////            if (liShoppingCard.size() > 0) {
////                if (Constant.SCREEN_LAYOUT == 1) {
////                    if (liShoppingCard.get(0).getCartID() == 0) {
////                        /** Clear Shopping Cart Icon Count **/
////                        MainActivity.getInstance().updateShoppingCartItemCount(0);
//////                        rlRoot.setVisibility(View.VISIBLE);
//////                        llRootMain.setVisibility(View.GONE);
//////                        llRootEmpty.setVisibility(View.VISIBLE);
////                    } else {
////                        int quntity = 0;
////                        for (int i = 0; i < liShoppingCard.size(); i++) {
////                            quntity = quntity + Integer.parseInt(liShoppingCard.get(i).getQty());
////                        }
////                        MainActivity.countMenu.setTitle(String.valueOf(quntity));
////                        //below line added newly for counter qty update
////                        MainActivity.getInstance().updateShoppingCartItemCount(quntity);
////                        /////
//////                        llRootEmpty.setVisibility(View.GONE);
//////                        rlRoot.setVisibility(View.VISIBLE);
//////                        cvTotal.setVisibility(View.VISIBLE);
//////                        cardAdapter = new CardAdapter(MainActivity.getInstance(), this, liShoppingCard);
//////                        recyclerView.setAdapter(cardAdapter);
////                        onCalculateTotal(liShoppingCard);
//////                        cardAdapter.notifyDataSetChanged();
////                    }
////                }
////            }else if(Constant.SCREEN_LAYOUT==2) {
//                if (CardFragment.liShoppingCard.get(0).getCartID() == 0) {
//                    /** Clear Shopping Cart Icon Count **/
//                    MainActivityDup.getInstance().updateShoppingCartItemCount(0);
//
////                    rlRoot.setVisibility(View.VISIBLE);
////                    llRootMain.setVisibility(View.GONE);
////                    llRootEmpty.setVisibility(View.VISIBLE);
//                } else {
//                    int quntity = 0;
//                    for (int i = 0; i < liShoppingCard.size(); i++) {
//                        quntity = quntity + Integer.parseInt(liShoppingCard.get(i).getQty());
//                    }
//                    MainActivityDup.countMenu.setTitle(String.valueOf(quntity));
////                    llRootEmpty.setVisibility(View.GONE);
////                    rlRoot.setVisibility(View.VISIBLE);
////                    cvTotal.setVisibility(View.VISIBLE);
////                    cardAdapter = new CardAdapter(MainActivityDup.getInstance(), this, liShoppingCard);
////                    recyclerView.setAdapter(cardAdapter);
//                    onCalculateTotal(liShoppingCard);
////                    cardAdapter.notifyDataSetChanged();000
//                }
//            }
            }
        }
    }

    public void onCalculateTotal(List<ShoppingCardModel> liShoppingCard) {

//        if(liShoppingCard != null && liShoppingCard.size() > 0){
//            Constant.forRealtimeList = liShoppingCard;
//        }

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
//                _isWineTaxEnable = true;
                        vWineTax.setVisibility(View.VISIBLE);
                        llWineTax.setVisibility(View.VISIBLE);
                        _wineTax = _wineTax + Float.parseFloat(liShoppingCard.get(i).getWineTax2());
                    } else {
                        vWineTax.setVisibility(View.GONE);
                        llWineTax.setVisibility(View.GONE);
                    }

                    //Bottle Deposit
                    if (liShoppingCard.get(i).getBottledeposit().trim() != null) {
                        if (Double.valueOf(liShoppingCard.get(i).getBottledeposit()) != null)
                            _bottleDeposit = _bottleDeposit + Float.parseFloat(liShoppingCard.get(i).getBottledeposit()) * Float.parseFloat(liShoppingCard.get(i).getQty());
                    }
                    //Bottle Deposit visibility
                    if (_bottleDeposit > 0) {
                        llBottleDeposit.setVisibility(View.VISIBLE);
                        vBottleDeposit.setVisibility(View.VISIBLE);
                    } else {
                        llBottleDeposit.setVisibility(View.GONE);
                        vBottleDeposit.setVisibility(View.GONE);
                    }

                    if (liShoppingCard.get(i).getIsMiscTax()) {
//                _isMiscTaxEnable = true;
                        vMiscTax.setVisibility(View.VISIBLE);
                        llMiscTax.setVisibility(View.VISIBLE);
                        _miscTax = _miscTax + Float.valueOf(liShoppingCard.get(i).getMiscTax3());
                    } else {
                        vMiscTax.setVisibility(View.GONE);
                        llMiscTax.setVisibility(View.GONE);
                    }

                    if (liShoppingCard.get(i).getIsflat()/*Double.valueOf(liShoppingCard.get(i).getFlat()) != null*/) {
//                _isFlatTaxEnable = true;
                        vFlatTax.setVisibility(View.VISIBLE);
                        llFlatTax.setVisibility(View.VISIBLE);
                        _flatTax = _flatTax + Float.parseFloat(liShoppingCard.get(i).getFlat());
                    } else {
                        vFlatTax.setVisibility(View.GONE);
                        llFlatTax.setVisibility(View.GONE);
                    }

                    if (liShoppingCard.get(i).getIsTotalSavingDisplay()) {
                        tvTotalSaving.setVisibility(View.VISIBLE);
                        tvTitleTotalSaving.setVisibility(View.VISIBLE);
                    } else {
                        tvTitleTotalSaving.setVisibility(View.GONE);
                        tvTotalSaving.setVisibility(View.GONE);
                    }

                    pPrice = liShoppingCard.get(i).getPrice();
                    currentPrice=pPrice.replace("$", "");
                    pPromoPrice = liShoppingCard.get(i).getPromoPrice();
                    currentPromoPrice=pPromoPrice.replace("$", "");
                    _actualTotal = _actualTotal + Float.parseFloat(currentPrice) * Float.parseFloat(liShoppingCard.get(i).getQty());
                }

//
//        if (_miscTax > 0 && _isMiscTaxEnable) {
//            vMiscTax.setVisibility(View.VISIBLE);
//            llMiscTax.setVisibility(View.VISIBLE);
//        } else {
//            vMiscTax.setVisibility(View.GONE);
//            llMiscTax.setVisibility(View.GONE);
//        }
//        if (_flatTax > 0 && _isFlatTaxEnable) {
//            vFlatTax.setVisibility(View.VISIBLE);
//            llFlatTax.setVisibility(View.VISIBLE);
//        } else {
//            vFlatTax.setVisibility(View.GONE);
//            llFlatTax.setVisibility(View.GONE);
//        }
//        if (_wineTax > 0 && _isWineTaxEnable) {
//            vWineTax.setVisibility(View.VISIBLE);
//            llWineTax.setVisibility(View.VISIBLE);
//        } else {
//            vWineTax.setVisibility(View.GONE);
//            llWineTax.setVisibility(View.GONE);
//        }
        _total = _subTotal + _salesTax + _wineTax + _bottleDeposit + _miscTax + _flatTax;
        _totalSaving = _actualTotal - _subTotal;
    }




    public interface PaymentEvent {
        void loadOrderSummaryFragment(Bundle bundle, String orderDetail, String buttonclicked, String returnProcessing);
    }

    /*PaymentFragment paymentFragment;
    public static PaymentFragment getInstance() {

        return f;
    }*/

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Constant.isfromRealTime = false;

        paymentFragment = this;
        setHasOptionsMenu(true);
        setRetainInstance(true);
        //setHasOptionsMenu(true);
        //setRetainInstance(true);
    }

    private void setAdvancePaymentOptions() {

        if(Constant.paymentOptionsList != null && Constant.paymentOptionsList.size() > 0){
            Constant.paymentOptionsList.remove(0);
            cvPaymentOptions.setVisibility(View.VISIBLE);

            gridView.setAdapter(new ImageAdapter(getActivity(), Constant.paymentOptionsList));

            gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                public void onItemClick(AdapterView<?> parent, View v,
                                        int position, long id) {

                    DialogUtils.onWarningDialog(getActivity(), "", getResources().getString(R.string.str_payment_Option_under_develop));
                }
            });
        }else{
            cvPaymentOptions.setVisibility(View.GONE);
        }
    }

    @Override
    public void onResume() {
        super.onResume();

        if(Constant.SCREEN_LAYOUT == 2){
            MainActivityDup.getInstance().showbackButton();
        }else if (Constant.SCREEN_LAYOUT==1){
//            MainActivity.getInstance().llsearch.setVisibility(View.GONE);
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //return super.onCreateView(inflater, container, savedInstanceState);
        return inflater.inflate(R.layout.fragment_payment, container, false);
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        gridView = view.findViewById(R.id.gridView1);

        recyclerView = view.findViewById(R.id.recycler_view_payment_fragment);
        LinearLayoutManager linearLayoutManager=null;
        if(Constant.SCREEN_LAYOUT==1){
            linearLayoutManager = new LinearLayoutManager(MainActivity.getInstance());
            paymentAdapter = new PaymentCartItemListAdapter(MainActivity.getInstance(),
                    this,
                    Constant.liCardModel);
        }else if(Constant.SCREEN_LAYOUT==2) {
            linearLayoutManager = new LinearLayoutManager(MainActivityDup.getInstance());
            paymentAdapter = new PaymentCartItemListAdapter(MainActivityDup.getInstance(),
                    this,
                    Constant.liCardModel);
        }

        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(paymentAdapter);
        paymentAdapter.notifyDataSetChanged();

        checkUSAePAYactivestatus();

        initViews(view);
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Bundle bundle = this.getArguments();
        if (bundle != null) {

//            Edited by Janvi 1stOct *****
            DecimalFormat df = new DecimalFormat("####0.00");
            df.setMaximumFractionDigits(2);

            isDeliveryFee = bundle.getBoolean("isDeliveryFee",false);
            if(isDeliveryFee){
                String deliveryFeeval = bundle.getString("SurchargePrice", "0");
                deliveryFeeSurchargeVal = Float.parseFloat(deliveryFeeval);
            }else{
                deliveryFeeSurchargeVal = 0.0f;
            }
//            end **********

            storeAlert = bundle.getString("storeAlert", "");
            if(storeAlert.isEmpty() || storeAlert.equals("")){
                tv_warn_alert.setVisibility(View.GONE);
            }else{
                tv_warn_alert.setVisibility(View.VISIBLE);
                tv_warn_alert.setText(storeAlert);
            }

            if(Constant.isfromRealTime){

                if(Constant.forRealtimeList != null && Constant.forRealtimeList.size() > 0){
                    onCalculateTotal(Constant.forRealtimeList );
                }
                Constant.isfromRealTime = false;

            }else{

//                ***************** Edited by Varun for shipping charges ********************

                S_name = bundle.getString("s_name", "");



//                Toast.makeText(getContext(), ""+S_name, Toast.LENGTH_LONG).show();
//                 **************** END ****************


                _total = bundle.getFloat("total", 0);
                _subTotal = bundle.getFloat("sub_total", 0);
                _salesTax = bundle.getFloat("sales_tax", 0);
                _wineTax = bundle.getFloat("wine_tax", 0);
                _miscTax = bundle.getFloat("misc_tax", 0);
                _flatTax = bundle.getFloat("flat_tac", 0);
                _bottleDeposit = bundle.getFloat("bottle_deposit", 0);
                _shipping = bundle.getFloat("shipping", 0);
                _totalSaving = bundle.getFloat("total_saving", 0);
            }
//            _total = bundle.getFloat("total", 0);
//            _subTotal = bundle.getFloat("sub_total", 0);
//            _salesTax = bundle.getFloat("sales_tax", 0);
//            _wineTax = bundle.getFloat("wine_tax", 0);
//            _miscTax = bundle.getFloat("misc_tax", 0);
//            _flatTax = bundle.getFloat("flat_tac", 0);
//            _bottleDeposit = bundle.getFloat("bottle_deposit", 0);
//            _shipping = bundle.getFloat("shipping", 0);
//            _totalSaving = bundle.getFloat("total_saving", 0);
            _tipValue = bundle.getFloat("tip_value", 0.00f);
            _tipCCValue = bundle.getInt("tip_cc_value", 0);
            tipOption = bundle.getInt("tip_option", 0);
            if (_totalSaving <= 0) {
                vTotalSaving.setVisibility(View.GONE);
                llTotalSaving.setVisibility(View.GONE);
            }
            _lPoints = bundle.getFloat("loyalty_reward", 0);
            selectedRadioButton = bundle.getInt("selected_delivery_option", 0);

            rbPayAtStore = bundle.getBoolean("pay_at_store", false);
            rbPickUpAtStore = bundle.getBoolean("rb_pick_up_store", false);
            rbPayWithCart = bundle.getBoolean("pay_with_cart", false);
            rbUberRush = bundle.getBoolean("uber_rush", false);
            rbHandOnDelivery = bundle.getBoolean("hand_on_delivery", false);
            Log.e(TAG, "onActivityCreated: 23"+bundle.getBoolean("pay_at_store") );
            rbShip = bundle.getBoolean("ship", false);
            cbxShipDifferentAddress = bundle.getBoolean("ship_with_different_address", false);
            isTipDialog = bundle.getBoolean("isTip", false);

            pickupDay = bundle.getString("pickupDay", "");
            pickupTime = bundle.getString("pickupTime", "");
            pickupCurrentDay = bundle.getString("pickupCurrentDay", "");

            if(Constant.twentyOneYear != null && Constant.twentyOneYear.getAllowPickUpTime()){
                if(!pickupTime.isEmpty()&&!pickupCurrentDay.isEmpty()){
//                    cv_pickup_time.setVisibility(View.VISIBLE);
                    tv_pickup_time.setVisibility(View.VISIBLE);
                    if(pickupCurrentDay.equals("Tomorrow")){
                        tv_pickup_time.setText("Pick Up Time:" + " " + pickupTime + " " + pickupDay);
                    }else{
                        tv_pickup_time.setText("Pick Up Time:" + " " + pickupTime + " " + pickupCurrentDay);
                    }
                }
            }else{
//                cv_pickup_time.setVisibility(View.GONE);
                tv_pickup_time.setVisibility(View.GONE);
            }
//Edited by Varun for Pickup time hide in screen
            if (!Constant.pick_up){
                tv_pickup_time.setVisibility(View.GONE);
            }
//            END
            Log.e("Log","rbHandOnDelivery="+rbHandOnDelivery);
            /*if (selectedRadioButton == 1) {
                cvPayment.setVisibility(View.GONE);
                tvTitlePayment.setVisibility(View.GONE);
            } else {*/
            cvPayment.setVisibility(View.VISIBLE);
            tvTitlePayment.setVisibility(View.VISIBLE);
            //}

            onDemoStore();
            setFinancialData();
            getCustomerData();

        }
    }


    /**
     * Init Views
     **/
    public void initViews(View v) {

//        Edited by Varun for join loyalty reward in e-com
        ll_join_reward_payment_fragment = v.findViewById(R.id.ll_join_reward_payment_fragment);
        tv_title_join_reward_payment_fragment = v.findViewById(R.id.tv_title_join_reward_payment_fragment);
        tv_title_join_reward_payment_fragment.setTextColor(Color.parseColor(Constant.themeModel.ThemeColor));
        cbx_join_reward_payment_fragment = v.findViewById(R.id.cbx_join_reward_payment_fragment);
        BSTheme.setCheckBoxColor(cbx_join_reward_payment_fragment, Color.parseColor(Constant.themeModel.ThemeColor), Color.GRAY);
        cbx_join_reward_payment_fragment.setOnCheckedChangeListener(this);
//        END

        // Main Titles
        tvTitleOrderInfo = v.findViewById(R.id.tv_title_order_info_payment_fragment);
        tvTitleOrderInfo.setTextColor(Color.parseColor(Constant.themeModel.ThemeColor));
        tvTitlePayment = v.findViewById(R.id.tv_title_payment_payment_fragment);
        tvTitlePayment.setTextColor(Color.parseColor(Constant.themeModel.ThemeColor));
        progressBar = v.findViewById(R.id.progressbar_payment_fragment);
        //progressBar.setVisibility(View.VISIBLE);
        //MainActivity.getInstance().getWindow().setFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE, WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);

        /*GradientDrawable progressShape = (GradientDrawable) progressBar.getBackground();
        progressShape.setColor(Color.parseColor(Constant.themeModel.ThemeColor));*/

        // Scroll Views
        nestedScrollView = v.findViewById(R.id.nested_scroll_payment_fragment);

        // Card Views
        cvOrderInfo = v.findViewById(R.id.cv_order_info_payment_fragment);
        cvPayment = v.findViewById(R.id.cv_payment_payment_fragment);
        cv_pickup_time = v.findViewById(R.id.cv_pickup_time);
        cvPaymentOptions = v.findViewById(R.id.cv_payment_options_fragment);

        // Relative Layout
        rl_main_payment_layout = v.findViewById(R.id.rl_main_payment_layout);
        rl_main_payment_layout.setBackgroundColor(Color.parseColor(Constant.themeModel.Backgroundcolor));

        rlRoot = v.findViewById(R.id.rl_root_payment_fragment);
        rlOrderInfo = v.findViewById(R.id.rl_order_info_payment_fragment);
//        rlPayment = v.findViewById(R.id.rl_payment_main_payment_fragment);
        rlFinancialData = v.findViewById(R.id.rl_financial_data_payment_fragment);
        rlRootRewardCashAvailable = v.findViewById(R.id.rl_reward_cash_available_payment_fragment);
        rlRootSaveCard = v.findViewById(R.id.rl_save_cart_payment_fragment);
        rlRootBillingAddress = v.findViewById(R.id.rl_billing_address_payment_fragment);
        rlRootCardDetail = v.findViewById(R.id.rl_card_detail_payment_fragment);

        // Linear Layout
        llTitlesCartItem = v.findViewById(R.id.ll_title_cart_item_payment_fragment);
        llSalesTax = v.findViewById(R.id.ll_sales_tex_payment_fragment);
        llWineTax = v.findViewById(R.id.ll_wine_tex_payment_fragment);
        llBottleDeposit = v.findViewById(R.id.ll_bottle_deposit_payment_fragment);
        llMiscTax = v.findViewById(R.id.ll_misc_tax_payment_fragment);
        llFlatTax = v.findViewById(R.id.ll_flat_tax_payment_fragment);
        llShip = v.findViewById(R.id.ll_shipping_payment_fragment);
        llTip = v.findViewById(R.id.ll_tip_payment_fragment);
        llSubTotal = v.findViewById(R.id.ll_sub_total_payment_fragment);
        llTotal = v.findViewById(R.id.ll_total_payment_fragment);
        llTotalSaving = v.findViewById(R.id.ll_total_saving_payment_fragment);
        llLoyaltyReword = v.findViewById(R.id.ll_loyalty_reward_payment_fragment);
        llButtonRoot = v.findViewById(R.id.ll_button_payment_fragment);
        llMyReward = v.findViewById(R.id.ll_my_reward_payment_fragment);

        //Edited by Janvi 1stOct ****
        ll_Delivery_fee = v.findViewById(R.id.ll_Delivery_fee_payment_fragment);
        tv_Delivery_fee = v.findViewById(R.id.tv_Delivery_fee_payment_fragment);
        tv_warn_alert = v.findViewById(R.id.tv_warn_two_hour_ago_delivery_option_fragment);
        tv_notes = v.findViewById(R.id.tv_notes);
        tv_notes.setText("Enter a memo for this order(Optional)");
        tv_notes.setTextColor(Color.parseColor(Constant.themeModel.ThemeColor));

        tv_notes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DialogUtils.enterCustomerNote(getActivity());
            }
        });

//        end *****************
        // views
        vTitleCartItem = v.findViewById(R.id.v_title_cart_item_payment_fragment);
//        vRV = v.findViewById(R.id.view_rv_payment_fragment);
        vSalesTax = v.findViewById(R.id.view_sales_tex_payment_fragment);
        vWineTax = v.findViewById(R.id.view_wine_tex_payment_fragment);
        vBottleDeposit = v.findViewById(R.id.view_bottle_deposit_payment_fragment);
        vMiscTax = v.findViewById(R.id.view_misc_tax_payment_fragment);
        vFlatTax = v.findViewById(R.id.view_flat_tax_payment_fragment);
        vShip = v.findViewById(R.id.view_shipping_payment_fragment);
        //vSubTotal = v.findViewById(R.id.view_sales_tex_payment_fragment);
        vTotal = v.findViewById(R.id.view_total_payment_fragment);
        vTip = v.findViewById(R.id.view_tip_payment_fragment);
        vTotalSaving = v.findViewById(R.id.view_check_box);
        vLoyaltyReward = v.findViewById(R.id.view_total_saving_payment_fragment);
        vNoTaxAreBeingApplied = v.findViewById(R.id.view_no_tax_are_being_applied_payment_fragment);
        vMyReward = v.findViewById(R.id.view_my_reward_payment_fragment);

        view_subtotal = v.findViewById(R.id.view_subtotal_payment_fragment); //Edited by Janvi 2nd Oct ****** end ****

        // Title TextViews
        tvTitleGiftWrap = v.findViewById(R.id.tv_title_gift_wrap_payment_fragment);
        tvTitleItemName = v.findViewById(R.id.tv_title_item_payment_fragment);

        LinearLayout.LayoutParams param = new LinearLayout.LayoutParams(
                0,
                ViewGroup.LayoutParams.WRAP_CONTENT,
                64
        );
        for (int i = 0; i < Constant.liCardModel.size(); i++) {
            if (Constant.liCardModel.get(i).getGiftWrap() != null && !Constant.liCardModel.get(i).getGiftWrap()) {
                tvTitleGiftWrap.setVisibility(View.GONE);
                tvTitleItemName.setLayoutParams(param);
                break;
            }
        }

        tvTitleItemQuantity = v.findViewById(R.id.tv_title_quantity_payment_fragment);
        tvTitleItemPrice = v.findViewById(R.id.tv_title_price_payment_fragment);
        tv_pickup_time = v.findViewById(R.id.tv_pickup_time);

        tvS_name = v.findViewById(R.id.tv_title_shipping_payment_fragment);
        tvTitleSalesTax = v.findViewById(R.id.tv_title_sales_tex_payment_fragment);
        tvTitleWineTax = v.findViewById(R.id.tv_title_wine_tex_payment_fragment);
        tvTitleBottleDeposit = v.findViewById(R.id.tv_title_bottle_deposit_payment_fragment);
        tvTitleMiscTax = v.findViewById(R.id.tv_title_misc_tax_payment_fragment);
        tvTitleFlatTax = v.findViewById(R.id.tv_title_flat_tax_payment_fragment);
        tvTitleSubTotal = v.findViewById(R.id.tv_title_sub_total_payment_fragment);
        tvTitleTotal = v.findViewById(R.id.tv_title_total_payment_fragment);
        tvTitleTip = v.findViewById(R.id.tv_title_tip_payment_fragment);
        tvTitleTotalSaving = v.findViewById(R.id.tv_title_total_saving_payment_fragment);
        tvTitleLoyaltyReward = v.findViewById(R.id.tv_title_loyalty_reward_payment_fragment);
        tvNoTaxAreBeingApplied = v.findViewById(R.id.tv_no_tax_being_applied_payment_fragment);
        tvTitleMyReward = v.findViewById(R.id.tv_title_my_reward_payment_fragment);

        tvNewCard = v.findViewById(R.id.tv_add_cart_payment_fragment);
        tvNewCard.setText(Utils.underlineText(String.valueOf(getString(R.string.lbl_new_cart))));
        tvNewCard.setTextColor(Color.parseColor(Constant.themeModel.ThemeColor));
        tvNewCard.setOnClickListener(this);
        tvNewCard.setVisibility(View.GONE);

        tvTitleCartNumber = v.findViewById(R.id.tv_title_cart_number_payment_fragment);
        tvTitleCvv = v.findViewById(R.id.tv_title_cvv_payment_fragment);
        tvTitleCartHolderName = v.findViewById(R.id.tv_title_card_holder_name_payment_fragment);
        tvTitleExpiration = v.findViewById(R.id.tv_title_expiration_payment_fragment);
        tvTitleBillingAddress = v.findViewById(R.id.tv_title_billing_address_payment_fragment);
        tvTitleAddressOne = v.findViewById(R.id.tv_title_address_one_payment_fragment);
        tvTitleAddressOne.setText(Html.fromHtml(getString(R.string.lbl_address1)));
        tvTitleAddressTwo = v.findViewById(R.id.tv_title_address_two_payment_fragment);
        tvTitleZipOrPostal = v.findViewById(R.id.tv_title_zip_payment_fragment);
        tvTitleZipOrPostal.setText(Html.fromHtml(getString(R.string.lbl_zip_postal)));
        /*tvTitleCity = v.findViewById(R.id.tv_title_city_payment_fragment);*/
//        tvTitleState = v.findViewById(R.id.tv_title_state_payment_fragment);
        tvNoteCartAlreadyOnFile = v.findViewById(R.id.tv_note_payment_fragment);
        tvTitleRewardCashAvailable = v.findViewById(R.id.tv_title_reward_cash_available_payment_fragment);
        tvRewardCashAvailable = v.findViewById(R.id.tv_rewards_cash_available_payment_fragment);

        // TextViews
        tvSalesTax = v.findViewById(R.id.tv_sales_tex_payment_fragment);
        tvWineTax = v.findViewById(R.id.tv_wine_tex_payment_fragment);
        tvBottleDeposit = v.findViewById(R.id.tv_bottle_deposit_payment_fragment);
        tvMiscTax = v.findViewById(R.id.tv_misc_tax_payment_fragment);
        tvFlatTax = v.findViewById(R.id.tv_flat_tax_payment_fragment);
        tvSubTotal = v.findViewById(R.id.tv_sub_total_payment_fragment);

        tvTotal = v.findViewById(R.id.tv_total_payment_fragment);
        tvShip = v.findViewById(R.id.tv_shipping_payment_fragment);
        tvTip = v.findViewById(R.id.tv_tip_payment_fragment);
        tvTotalSaving = v.findViewById(R.id.tv_total_saving_payment_fragment);
        tvLoyaltyReward = v.findViewById(R.id.tv_loyalty_reward_payment_fragment);
        tvMyReward = v.findViewById(R.id.tv_my_reward_payment_fragment);
        tvApplyRewardCash = v.findViewById(R.id.tv_reward_cash_available_payment_fragment);

        // EditText
        etCardNumber = v.findViewById(R.id.et_cart_number_payment_fragment);
        etCvv = v.findViewById(R.id.et_cvv_payment_fragment);
        etCardHolderName = v.findViewById(R.id.et_card_holder_name_payment_fragment);
        etExpiration = v.findViewById(R.id.et_expiration_payment_fragment);
        etAddressOne = v.findViewById(R.id.et_address_one_payment_fragment);

        etAddressOne.setOnFocusChangeListener(this);

        etAddressTwo = v.findViewById(R.id.et_address_two_payment_fragment);
        etZip = v.findViewById(R.id.et_zip_payment_fragment);
        etZip.setOnFocusChangeListener(this);

//        Edited by Varun for Shipping and deliver address

        rl_shipping_address = v.findViewById(R.id.rl_shipping_address_payment_fragment);
        tv_shipping_address =v.findViewById(R.id.tv_title_shipping_address_payment_fragment);
        tv_shipping_address_one =v.findViewById(R.id.tv_title_shipping_address_one_payment_fragment);

//        Autotext of Address one in shipping
        autoCompleteTextView_address_one =v.findViewById(R.id.et_shipping_address_one_payment_fragment);
        autoCompleteTextView_address_one.setFocusable(false);
        autoCompleteTextView_address_one.setFocusable(false);
        autoCompleteTextView_address_one.setFocusableInTouchMode(false);
        autoCompleteTextView_address_one.setClickable(false);
        autoCompleteTextView_address_one.setPressed(false);
        autoCompleteTextView_address_one.setBackgroundResource(R.drawable.border_edittext_disable);
        autoCompleteTextView_address_one.setLongClickable(false);

//         Address two of shipping
        tv_Shipping_address_two =v.findViewById(R.id.tv_title_shipping_address_two_payment_fragment);
        et_shipping_address_two =v.findViewById(R.id.et_shipping_address_two_payment_fragment);
        et_shipping_address_two.setFocusable(false);
        et_shipping_address_two.setFocusable(false);
        et_shipping_address_two.setFocusableInTouchMode(false);
        et_shipping_address_two.setClickable(false);
        et_shipping_address_two.setPressed(false);
        et_shipping_address_two.setBackgroundResource(R.drawable.border_edittext_disable);
        et_shipping_address_two.setLongClickable(false);

//        City shipping
        tv_Shipping_zip =v.findViewById(R.id.tv_title_shipping_zip_payment_fragment);
        et_shipping_city =v.findViewById(R.id.et_shipping_city_payment_fragment);
        et_shipping_city.setFocusable(false);
        et_shipping_city.setFocusable(false);
        et_shipping_city.setFocusableInTouchMode(false);
        et_shipping_city.setClickable(false);
        et_shipping_city.setPressed(false);
        et_shipping_city.setBackgroundResource(R.drawable.border_edittext_disable);
        et_shipping_city.setLongClickable(false);

//        state Shipping
        et_shipping_state =v.findViewById(R.id.et_shipping_state_payment_fragment);
        et_shipping_state.setFocusable(false);
        et_shipping_state.setFocusable(false);
        et_shipping_state.setFocusableInTouchMode(false);
        et_shipping_state.setClickable(false);
        et_shipping_state.setPressed(false);
        et_shipping_state.setBackgroundResource(R.drawable.border_edittext_disable);
        et_shipping_state.setLongClickable(false);

//        Zip Shipping
        et_shipping_zip =v.findViewById(R.id.et_shipping_zip_payment_fragment);
        et_shipping_zip.setFocusable(false);
        et_shipping_zip.setFocusableInTouchMode(false);
        et_shipping_zip.setClickable(false);
        et_shipping_zip.setPressed(false);
        et_shipping_zip.setBackgroundResource(R.drawable.border_edittext_disable);
        et_shipping_zip.setLongClickable(false);



        String cardTypeVal = tvNewCard.getText().toString().trim();

//        if(Constant.twentyOneYear != null){
//            if(!Constant.twentyOneYear.getCVVBypass() && tvNewCard.getVisibility()== View.VISIBLE && cardTypeVal.equals("New Card")){
//                etCvv.setVisibility(View.GONE);
//            }else{
//                etCvv.setVisibility(View.VISIBLE);
//            }
//        }

//        etZip.addTextChangedListener(new TextWatcher() {
//            @Override
//            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
//
//            }
//
//            @Override
//            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
//                if (charSequence.length() == 5) {
//                    //http://192.168.172.211:889/WebStoreRestService.svc/GetZipCode/11001
//                    String Url = Constant.WS_BASE_URL + Constant.GET_ZIP_CODE + charSequence.toString();
//                    new Async_getAddress(getActivity(), Url, 12).execute();
//                }
//            }
//
//            @Override
//            public void afterTextChanged(Editable editable) {
//
//            }
//        });

        etCity = v.findViewById(R.id.et_city_payment_fragment);
        etState = v.findViewById(R.id.et_state_payment_fragment);

        // EditText Hint
        etCardNumber.setHint(Html.fromHtml(getString(R.string.lbl_card_number)));
        etCvv.setHint(Html.fromHtml(getString(R.string.lbl_cvv)));
        etCardHolderName.setHint(Html.fromHtml(getString(R.string.lbl_card_holder_name)));
        etExpiration.setHint(Html.fromHtml(getString(R.string.lbl_expiration)));
        etAddressOne.setHint(Html.fromHtml(getString(R.string.lbl_address1)));
        etAddressTwo.setHint(Html.fromHtml(getString(R.string.lbl_address2)));
        etCity.setHint(Html.fromHtml(getString(R.string.lbl_city)));
        etState.setHint(Html.fromHtml(getString(R.string.lbl_state)));
        etZip.setHint(Html.fromHtml(getString(R.string.lbl_zip_postal)));

        // CheckBox
        llHideCheckBox = v.findViewById(R.id.ll_hide_check_box);
        cbxSaveCard = v.findViewById(R.id.cbx_save_cart_for_future_update_payment_fragment);
        BSTheme.setCheckBoxColor(cbxSaveCard, Color.parseColor(Constant.themeModel.ThemeColor), Color.GRAY);
        cbxSaveCard.setOnCheckedChangeListener(this);
        cbxApplyRewardCash = v.findViewById(R.id.cbx_reward_cash_available_payment_fragment);
        BSTheme.setCheckBoxColor(cbxApplyRewardCash, Color.parseColor(Constant.themeModel.ThemeColor), Color.GRAY);
        cbxApplyRewardCash.setOnCheckedChangeListener(this);

//        Edited by Varun for pick up hours and location

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
        tv_main_location = v.findViewById(R.id.tv_main_location);
        tv_main_hours = v.findViewById(R.id.tv_main_location);

//        END


        // Button
        btnPrev = v.findViewById(R.id.btn_prev_payment_fragment);
        GradientDrawable bgShapes = (GradientDrawable) btnPrev.getBackground();
        bgShapes.setColor(Color.parseColor(Constant.themeModel.ThemeColor));
        btnPrev.setOnClickListener(this);

        btnPlaceOrder = v.findViewById(R.id.btn_place_payment_fragment);
        GradientDrawable bgShape = (GradientDrawable) btnPlaceOrder.getBackground();
        bgShape.setColor(Color.parseColor(Constant.themeModel.ThemeColor));
        btnPlaceOrder.setOnClickListener(this);

        etCardNumber.addTextChangedListener(new TextWatcher() {
            private static final char space = ' ';
            int count = 0;

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                // Remove spacing char
                if (count <= etCardNumber.getText().toString().length()
                        && (etCardNumber.getText().toString().length() == 4
                        || etCardNumber.getText().toString().length() == 9
                        || etCardNumber.getText().toString().length() == 14)) {
                    etCardNumber.setText(etCardNumber.getText().toString() + " ");
                    int pos = etCardNumber.getText().length();
                    etCardNumber.setSelection(pos);
                } else if (count >= etCardNumber.getText().toString().length()
                        && (etCardNumber.getText().toString().length() == 4
                        || etCardNumber.getText().toString().length() == 9
                        || etCardNumber.getText().toString().length() == 14)) {
                    etCardNumber.setText(etCardNumber.getText().toString().substring(0, etCardNumber.getText().toString().length() - 1));
                    int pos = etCardNumber.getText().length();
                    etCardNumber.setSelection(pos);
                }
//               Edited by Varun for Validator for USAePAY

                if (etCardNumber.getText().length()==19){
                    if (!Validator.validateCard(etCardNumber.getText().toString().trim())){
                         etCvv.clearFocus();
                         etCardNumber.requestFocus();
                         etCardNumber.setError("Invalid Card");
//                         btnPlaceOrder.setEnabled(false);
                    }else {
//                        btnPlaceOrder.setEnabled(true);
                    }
                }

//                 END

                count = etCardNumber.getText().toString().length();
            }
        });

        etCvv.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {

                if (hasFocus){
                    String card;
                    card = etCardNumber.getText().toString();
                    if (!tvNewCard.getText().toString().equals("New Card")){
                        if (!Validator.validateCard(card.trim())){
                            etCvv.clearFocus();
                            etCardNumber.requestFocus();
                            etCardNumber.setError("Invalid Card");
                        }
                    }
                }
            }
        });

        etCardNumber.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView view, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_DONE || actionId == EditorInfo.IME_ACTION_NEXT ) {
                    String card;
                    card = etCardNumber.getText().toString();
                    if (!tvNewCard.getText().toString().equals("New Card")){
                        if (!Validator.validateCard(card.trim())){
                            etCardNumber.requestFocus();
                            etCardNumber.setError("Invalid Card");
                        }else {
                            etCardNumber.setImeOptions(EditorInfo.IME_ACTION_NEXT);
                            etCardNumber.clearFocus();
                            etCvv.setFocusable(true);
                            etCvv.requestFocus();
                        }
                    }
                    return true;
                }else {

                }
                return false;
            }});

        etExpiration.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(etExpiration.getText().toString().isEmpty()){
                    etExpiration.setSelection(0);
                }else {
                    etExpiration.setSelection(etExpiration.getText().length());
                }
            }
        });

        etExpiration.addTextChangedListener(new TextWatcher() {
            private static final char space = ' ';
            int count = 0;

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                // Remove spacing char
                if (count <= etExpiration.getText().toString().length()
                        && (etExpiration.getText().toString().length() == 2
                )) {
                    etExpiration.setText(etExpiration.getText().toString() + "/");
                    int pos = etExpiration.getText().length();
                    etExpiration.setSelection(pos);
                } else if (count >= etExpiration.getText().toString().length()
                        && (etExpiration.getText().toString().length() == 3)) {

                    etExpiration.setText(etExpiration.getText().toString().substring(0, etExpiration.getText().toString().length() - 2));
                    int pos = etExpiration.getText().length();
                    etExpiration.setSelection(pos);
                }
                count = etExpiration.getText().toString().length();

            }
        });

        //onDemoStore();
        onSetVisibility(Constant.liCardModel);
        //  setFinancialData();
        //getCustomerData();
        // getCustomerCardDetail();

        showDialog();
        onCallpaymentOptions();

//        setAdvancePaymentOptions();

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
     * Call : GET USAePAY ACTIVE STATUS Edited by Varun
     **/

    private void checkUSAePAYactivestatus() {
        String URL = "";

        URL = Constant.WS_BASE_URL + Constant.GET_USAEPAY_ACTIVE_STATUS + Constant.STOREID + "/" + Constant.ENCODE_TOKEN_ID;

        TaskCheckUSAePAYStatus taskCheckUSAePAYStatus = new TaskCheckUSAePAYStatus(this,getContext());
        taskCheckUSAePAYStatus.execute(URL);

    }

    @Override
    public void onTaskUASePAYStatus(ActiveStatusModel activeStatusModel) {
        Log.e(TAG, "onTaskUASePAYStatus: "+activeStatusModel.getuSAePay() );
        if (activeStatusModel.getResult().equalsIgnoreCase("Success")){
            if (activeStatusModel.getuSAePay().equalsIgnoreCase("True")){
                Constant.isUSAePAY = true;
            }
        }

    }

//    END

    private void onCallpaymentOptions() {
        String url = Constant.WS_BASE_URL + Constant.GET_ADVANCE_PAYMENT_OPTIONS + "/" + Constant.STOREID;
        TaskAdvancePaymentOptions taskpaymentOptions = new TaskAdvancePaymentOptions(this);
        taskpaymentOptions.execute(url);
    }


    @Override
    public void onPaymentOptionsResult(PaymentOptions paymentOptionsModel) {

        ArrayList<String> paymentOptionsList = new ArrayList<>();

        if (paymentOptionsModel != null) {
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
//
//                recyclerView.setAdapter(new AdvancePaymentOptionAdapter(getActivity(),  Constant.paymentOptionsList));
////                listView.setAdapter(new AdvancePaymentOptionAdapter(getActivity(), Constant.paymentOptionsList));
        }

        if(Constant.paymentOptionsList != null && Constant.paymentOptionsList.size() > 0){
            Constant.paymentOptionsList.remove(0);
            cvPaymentOptions.setVisibility(View.VISIBLE);

            gridView.setAdapter(new ImageAdapter(getActivity(), Constant.paymentOptionsList));

            gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                public void onItemClick(AdapterView<?> parent, View v,
                                        int position, long id) {

                    DialogUtils.onWarningDialog(getActivity(), "", getResources().getString(R.string.str_payment_Option_under_develop));
                }
            });
        }else{
            cvPaymentOptions.setVisibility(View.GONE);
        }
    }

    @Override
    public void onFocusChange(View v, boolean hasFocus) {
        if (v.getId() == etAddressOne.getId() && etAddressOne.isFocused()){
            etAddressOne.addTextChangedListener(myWatcher /*new TextWatcher() {
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
                //if (isdhowdropdown) {
                if (etAddressOne.isFocused())
                    new Async_getAddress(MainActivity.getInstance(), Constant.MAP_API_URL + Constant.INPUT + Name + "&key=" + MainActivity.getInstance().getString(R.string.Place_API_key), 11).execute();
                //} else {
                //    isdhowdropdown = true;
                //}
            }
        }*/);
        }else if(v.getId() == etZip.getId() && etZip.isFocused()){

            etZip.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                }

                @Override
                public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                    if (charSequence.length() == 5) {
                        //http://192.168.172.211:889/WebStoreRestService.svc/GetZipCode/11001
                        String Url = Constant.WS_BASE_URL + Constant.GET_ZIP_CODE + charSequence.toString();
                        new Async_getAddress(getActivity(), Url, 12).execute();
                    }
                }

                @Override
                public void afterTextChanged(Editable editable) {

                }
            });

        }
    }


    /**
     * Fill data when address changed
     **/
    public static void onFillAddress(JSONArray address, int position) throws JSONException {

        Log.e(TAG, "onFillAddress: " + address);
        // if (position == 11) {

        etAddressOne.removeTextChangedListener(myWatcher);

        etAddressOne.setText("");
        etZip.setText("");
        etState.setText("");
        etCity.setText("");
        String address11 = "";
        for (int i = 0; i < address.length(); i++) {
            JSONObject object = address.getJSONObject(i);

            if (object.getString("types").contains("street_number")) {
                address11 += object.getString("long_name");
                etAddressOne.setText(address11);
            }
            if (object.getString("types").contains("route")) {
                address11 += " " + object.getString("long_name");
                etAddressOne.setText(address11);
            }
            if (object.getString("types").contains("postal_code")) {
                etZip.setText(object.getString("long_name"));
            }
            if (object.getString("types").contains("administrative_area_level_1")) {
                etState.setText(object.getString("short_name"));
            }
            if (object.getString("types").contains("locality"/*"*//*administrative_area_level_2*//*"*/)) {
                etCity.setText(object.getString("short_name"));
            }
        }
        etAddressTwo.requestFocus();
        etAddressOne.dismissDropDown();
        //}
    }

    public static void onFillZipAddress(PinModel model, int status) {
        Log.e(TAG, "onFillZipAddress: ");
        if (status == 12) {
        etState.setText(model._state);
        etCity.setText(model._city);
        }
    }

    /**
     * Check Demo Store
     * Remove Order If Current store is demo store
     **/
    private void onDemoStore() {
        if (Constant.STOREID.equals("1000")) {
            String deleteOrderForDemoStore = "";
            if (UserModel.Cust_mst_ID != null) {
                deleteOrderForDemoStore = Constant.WS_BASE_URL + Constant.DELETE_ORDER_FOR_BOXSALT_FOR_DEMO_STORE + Constant.STOREID + "/" + UserModel.Cust_mst_ID;
                TaskDeleteOrderForDemoStore taskDeleteOrderForDemoStore = new TaskDeleteOrderForDemoStore();
                taskDeleteOrderForDemoStore.execute(deleteOrderForDemoStore);
            }
        }
    }

    /**
     * Set Visibility
     **/
    private void onSetVisibility(List<ShoppingCardModel> model) {

        for (int i = 0; i < model.size(); i++) {
            if (!model.get(i).getIsSalesTax()) {
                llSalesTax.setVisibility(View.GONE);
                vSalesTax.setVisibility(View.GONE);
            }

            if (!model.get(i).getIsWineTax()) {
                llWineTax.setVisibility(View.GONE);
                vWineTax.setVisibility(View.GONE);
            }else{
                llWineTax.setVisibility(View.VISIBLE);
                vWineTax.setVisibility(View.VISIBLE);
            }

            if (!model.get(i).getIsMiscTax()) {
                llMiscTax.setVisibility(View.GONE);
                vMiscTax.setVisibility(View.GONE);
            }else{
                llMiscTax.setVisibility(View.VISIBLE);
                vMiscTax.setVisibility(View.VISIBLE);
            }

            if (!model.get(i).getIsflat()) {
                llFlatTax.setVisibility(View.GONE);
                vFlatTax.setVisibility(View.GONE);
            }else{
                llFlatTax.setVisibility(View.VISIBLE);
                vFlatTax.setVisibility(View.VISIBLE);
            }

            if (!model.get(i).getBSSetupDeliveryOption()) {
                llShip.setVisibility(View.GONE);
                vShip.setVisibility(View.GONE);
            }else{
                llShip.setVisibility(View.VISIBLE);
                vShip.setVisibility(View.VISIBLE);
            }

            if (model.get(i).getIsTotalSavingDisplay()) {
                vTotalSaving.setVisibility(View.VISIBLE);
                llTotalSaving.setVisibility(View.VISIBLE);
            }

        }
    }

    /**
     * Set Financial data
     **/
    private void setFinancialData() {

//        Edited by Janvi 2nd oct*******

        if(isDeliveryFee){
            ll_Delivery_fee.setVisibility(View.VISIBLE);
            view_subtotal.setVisibility(View.VISIBLE);
        }else{
            ll_Delivery_fee.setVisibility(View.GONE);
            view_subtotal.setVisibility(View.GONE);
        }

        //end ***************

        DecimalFormat df = new DecimalFormat("####0.00");
        df.setMaximumFractionDigits(2);
        //df.setRoundingMode(RoundingMode.UP);

        //Edited by Janvi 1st Oct ***

        if(isDeliveryFee && ll_Delivery_fee.isShown()){
            tv_Delivery_fee.setText("$" + String.valueOf(df.format(deliveryFeeSurchargeVal)));
            tvSubTotal.setText("$" + String.valueOf(df.format(_subTotal + deliveryFeeSurchargeVal)));
//            Log.d("total","total::"+_total);
//            Log.d("deliveryFeeSurchargeVal","deliveryFeeSurchargeVal::"+_total);
            float total = _total + _tipValue + deliveryFeeSurchargeVal + _shipping;
            tvTotal.setText("$" + String.valueOf(df.format(total)));
        }else{
            tvSubTotal.setText("$" + String.valueOf(df.format(_subTotal)));
            tvTotal.setText("$" + String.valueOf(df.format(_total + _tipValue + _shipping)));
        }
        //end *********

        tvSalesTax.setText("$" + String.valueOf(df.format(_salesTax)));
        tvWineTax.setText("$" + String.valueOf(df.format(_wineTax)));
        tvBottleDeposit.setText("$" + String.valueOf(df.format(_bottleDeposit)));
        tvMiscTax.setText("$" + String.valueOf(df.format(_miscTax)));
        tvFlatTax.setText("$" + String.valueOf(df.format(_flatTax)));

        if(_shipping > 0){

//            ***************** Edited by Varun for shipping charges ********************

            if (!S_name.isEmpty()){
                tvS_name.setText(S_name);
            }
            llShip.setVisibility(View.VISIBLE);
            vShip.setVisibility(View.VISIBLE);
            tvShip.setText("$" + String.valueOf(df.format(_shipping)));
            }else{
                llShip.setVisibility(View.GONE);
                vShip.setVisibility(View.GONE);
                tvShip.setText("$" + String.valueOf(df.format(_shipping)));
            }

//            **************** END ***********************

//            llShip.setVisibility(View.VISIBLE);
//            vShip.setVisibility(View.VISIBLE);
//            tvShip.setText("$" + String.valueOf(df.format(_shipping)));
//        }else{
//            llShip.setVisibility(View.GONE);
//            vShip.setVisibility(View.GONE);

        if (_bottleDeposit <= 0) {
            llBottleDeposit.setVisibility(View.GONE);
            vBottleDeposit.setVisibility(View.GONE);
        }

        if (_totalSaving > 0.0) {
            tvTotalSaving.setText("$" + String.valueOf(df.format(_totalSaving)));
        }

        if (_lPoints > 0.0) {
            vLoyaltyReward.setVisibility(View.VISIBLE);
            llLoyaltyReword.setVisibility(View.VISIBLE);
            tvLoyaltyReward.setText(String.valueOf(Math.round(_lPoints)));
        }

        if (_wineTax <= 0.00) {
            llWineTax.setVisibility(View.GONE);
            vWineTax.setVisibility(View.GONE);
        }
        if (_miscTax <= 0.00) {
            llMiscTax.setVisibility(View.GONE);
            vMiscTax.setVisibility(View.GONE);
        }
        if (_flatTax <= 0.00) {
            llFlatTax.setVisibility(View.GONE);
            vFlatTax.setVisibility(View.GONE);
        }

        //Previous Fragment Radio button is Checked/unChecked
        //Pay at Store is Clicked
        if (rbPayAtStore && rbPickUpAtStore && !rbHandOnDelivery && !rbShip ) {
            tvTitlePayment.setVisibility(View.GONE);
            cvPayment.setVisibility(View.GONE);
            cvPaymentOptions.setVisibility(View.GONE);
            rlRootCardDetail.setVisibility(View.GONE);
            rlRootBillingAddress.setVisibility(View.GONE);
            rlRootSaveCard.setVisibility(View.GONE);
            rl_shipping_address.setVisibility(View.GONE);
//            Edited by Varun for pick up hours and location
            pickup();
//            END
        } else {
            getCreditCardSetting();
//          Edited by Varun for UASePAY with Token ID
            if (Constant.isUSAePAY){
                getCustomerCardDetail();
            }else {
                getExpireCardDetail();
            }
//            END
//            getExpireCardDetail();
        }

        getLoyaltyReward();
        //Pay With Cart is Clicked
        if (rbPayWithCart) {
            //vNoTaxAreBeingApplied.setVisibility(View.VISIBLE);
            //tvNoTaxAreBeingApplied.setVisibility(View.VISIBLE);
        }

        //Uber Rush is Clicked
        if (rbUberRush) {

        }

        //Hand On Delivery is Clicked
        if (rbHandOnDelivery) {

        }

        //Ship is Clicked
        if (rbShip) {

            // vNoTaxAreBeingApplied.setVisibility(View.VISIBLE);
            //tvNoTaxAreBeingApplied.setVisibility(View.VISIBLE);
            //Call When Ship A Different Address is Checked
            if (cbxShipDifferentAddress) {

            } else {

            }
        }

        //Toast.makeText(getActivity(), "Tip Dialog : " + isTipDialog, Toast.LENGTH_SHORT).show();
        if (isTipDialog){
            vTip.setVisibility(View.VISIBLE);
            llTip.setVisibility(View.VISIBLE);

            tvTitleTip.setText((tipOption == 4) ? "TIP" : "TIP");
            tvTip.setText(String.valueOf( "$" + df.format(_tipValue)));
        }
    }

//    Edited by Varun for pick up hours and location
    private void pickup() {

        ll_pick_up.setVisibility(View.VISIBLE);
        tv_1.setText(Constant.contatInfo.getName().trim());
        tv_2.setText(Constant.contatInfo.getAddress().trim());
        tv_3.setText(Constant.contatInfo.getCity().trim() + ","+ " " + Constant.contatInfo.getState().trim() + " " + Constant.contatInfo.getZip().trim());
        tv_4.setText(Constant.contatInfo.getPhone().trim());
        Constant.pick_up_time = tv_pickup_time.getText().toString().trim();
        tv_5.setText(tv_pickup_time.getText().toString().trim());


        if(Constant.liOnlyStoreHour != null && Constant.liOnlyStoreHour.size() > 0) {
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
                    }
                }
                if (tomorrow.equals(Constant.liOnlyStoreHour.get(i).getStoreDay())) {
                    pos = i;
                    tv_8.setText(Constant.liOnlyStoreHour.get(pos).getStoreDay().trim());
                    if (!Constant.liOnlyStoreHour.get(pos).getClosed()) {
                        tv_9.setText(Constant.liOnlyStoreHour.get(pos).getOpenTime().trim() + " - " + Constant.liOnlyStoreHour.get(pos).getCloseTime().trim());
                        check =false;
                    }else{
                        tv_9.setText("Store is closed.");
                        check=true;
                    }
                }
                j++;
//                Edited b Varun for day after tomorrow display when tomorrow day store hours is close
                if (check){
                    ll_dayaftertomorrow.setVisibility(View.VISIBLE);
                    if (dayaftertomorrow.equals(Constant.liOnlyStoreHour.get(i).getStoreDay())) {
                        pos = i;
                        tv_10.setText(Constant.liOnlyStoreHour.get(pos).getStoreDay().trim());
                        if (!Constant.liOnlyStoreHour.get(pos).getClosed()) {
                            tv_11.setText(Constant.liOnlyStoreHour.get(pos).getOpenTime().trim() + " - " + Constant.liOnlyStoreHour.get(pos).getCloseTime().trim());
                        }else{
                            tv_11.setText("Store is closed.");
                        }
                    }
                    v++;
                }
//                END
            }
        }

    }

//    END

//    public String getCurrentDay(){
//
//        String daysArray[] = {"Sunday","Monday","Tuesday", "Wednesday","Thursday","Friday", "Saturday"};
//
//        Calendar calendar = Calendar.getInstance();
//        int day = calendar.get(Calendar.DAY_OF_WEEK - 1);
//
//        return daysArray[day];
//
//    }
//    public String getNextDay(){
//
//        String daysArray[] = {"Sunday","Monday","Tuesday", "Wednesday","Thursday","Friday", "Saturday"};
//
//        Calendar calendar = Calendar.getInstance();
//        int day = calendar.get(Calendar.DAY_OF_WEEK);
//
//        return daysArray[day];
//
//    }

    /**
     * Call : Get Customer Expire Credit Card Detail
     **/
    private void getExpireCardDetail() {
        String cardRequestUrl = "";
        if (UserModel.Cust_mst_ID != null) {
            cardRequestUrl = Constant.WS_BASE_URL + Constant.GET_EXPIRE_CARD_DETAIL + Constant.STOREID + "/" + UserModel.Cust_mst_ID;
            taskCardExpireCheck = new TaskCardExpireCheck(this);
            Log.d(TAG, "Credit card detail : " + cardRequestUrl);
            taskCardExpireCheck.execute(cardRequestUrl);
        }
    }


    @Override
    public void customerCardExpireResult(List<CreditCardExpiryModel>  LiExpireCard) {
        etExpiration.setFilters(new InputFilter[] { new InputFilter.LengthFilter(5) });
        if(LiExpireCard.get(0).getMessage().equalsIgnoreCase("No Record")){
            getCustomerCardDetail();
        }else if(LiExpireCard.get(0).getMessage().contains("Card will expires")){
            DialogUtils.onWarningDialog(getActivity(), "", LiExpireCard.get(0).getMessage());
            etExpiration.setFilters(new InputFilter[] { new InputFilter.LengthFilter(50) });
            etExpiration.setText(LiExpireCard.get(0).getMessage());
            getCustomerCardDetail();
        }else{
            DialogUtils.onWarningDialog(getActivity(), "", LiExpireCard.get(0).getMessage());
            tvNewCard.setVisibility(View.GONE);
            tvNewCard.setText(Utils.underlineText(String.valueOf(getString(R.string.lbl_existing_card))));
            BSTheme.setCheckBoxColor(cbxSaveCard, Color.parseColor(Constant.themeModel.ThemeColor), Color.GRAY);
//            Edited by Varun for guest login
            if (Constant.ISguest){
                rlRootSaveCard.setVisibility(View.GONE);
            }else {
                rlRootSaveCard.setVisibility(View.VISIBLE);
            }
//            END
            cbxSaveCard.setClickable(true);
            llHideCheckBox.setVisibility(View.GONE);
            etCardNumber.requestFocus();
        }
    }

    /**
     * Call : Get Customer Credit Card Detail
     **/
    private void getCustomerCardDetail() {
        String cardRequestUrl = "";

        if(Constant.isUSAePAY){

            if (UserModel.Cust_mst_ID != null) {
//                cardRequestUrl = Constant.WS_BASE_URL + Constant.USA_PAY_GET_CARD_DETAIL + Constant.STOREID + "/" + UserModel.Cust_mst_ID + "/" + merchantContractId.trim();


//                Edited by Varun FOR NEW API WITH TOKEN ID

                cardRequestUrl = Constant.WS_BASE_URL + Constant.GET_CUSTOMER_DATA_FROM_USAEPAY_VAULT + Constant.STOREID + "/" + UserModel.Cust_mst_ID + "/" + Constant.ENCODE_TOKEN_ID;

//                END
                customerCard = new TaskCustomerCard(getActivity(), this, false);
                Log.d(TAG, "Credit card detail : " + cardRequestUrl);
                customerCard.execute(cardRequestUrl);
            }
//            merchantCode = creditCartSetting.getMerchantCode();
//            merchantCustomerId = creditCartSetting.getMerchantCustID();
//            merchantContractId = creditCartSetting.getMerchantContractID();
        }else{

            if (UserModel.Cust_mst_ID != null) {
                cardRequestUrl = Constant.WS_BASE_URL + Constant.GET_CARD_DETAIL + Constant.STOREID + "/" + UserModel.Cust_mst_ID;
                customerCard = new TaskCustomerCard(getActivity(), this, false);
                Log.d(TAG, "Credit card detail : " + cardRequestUrl);
                customerCard.execute(cardRequestUrl);
            }
        }

    }

    /**
     * Result : Customer Credit Card Result
     **/
    @Override
    public void customerCardResult(List<CustomerCard> liCustomerCard) {
        Log.d(TAG, "Credit card Result : " + liCustomerCard);
        for (int i = 0; i < liCustomerCard.size(); i++) {
            if (liCustomerCard.get(i).getSuccessMessage().equals("No Record")) {
                tvNewCard.setVisibility(View.GONE);
                tvNewCard.setText(Utils.underlineText(String.valueOf(getString(R.string.lbl_existing_card))));
                BSTheme.setCheckBoxColor(cbxSaveCard, Color.parseColor(Constant.themeModel.ThemeColor), Color.GRAY);
//                Edited by Varun for guest login
                if (Constant.ISguest){
                    rlRootSaveCard.setVisibility(View.GONE);
                }else {
                    rlRootSaveCard.setVisibility(View.VISIBLE);
                }
//                END
                cbxSaveCard.setClickable(true);
                llHideCheckBox.setVisibility(View.GONE);
                etCardNumber.requestFocus();
                //cbxSaveCard.setChecked(false);
                //cbxSaveCard.toggle();
            } else {

                rlRootSaveCard.setVisibility(View.GONE);
                llHideCheckBox.setVisibility(View.VISIBLE);
                //BSTheme.setCheckBoxColor(cbxSaveCard, R.drawable.my_chk_checked, Color.GRAY);
                cbxSaveCard.setClickable(false);
                GradientDrawable d = new GradientDrawable();
                d.setColor(R.drawable.border_edittext_disable);
                tvNewCard.setText(Utils.underlineText(String.valueOf(getString(R.string.lbl_new_cart))));
                //cbxSaveCard.setButtonDrawable(R.drawable.applogo);
                //cbxSaveCard.setChecked(true);

                if(Constant.isUSAePAY){

//                    Edited by Varun FOR NEW API WITH TOKEN ID for expire month

                String value =liCustomerCard.get(0).getCardExp() ;
                String[] dateParts = value.split("-");
                String year = dateParts[0];
                String month = dateParts[1];

                Log.d(TAG, "customerCardResultmonth: " + month);
                Log.d(TAG, "customerCardResultyear: " + year);

                String lastTwo = null;
                if (year != null && year.length() >= 2) {
                    lastTwo = year.substring(year.length() - 2);
                }

//               END



                    etCardNumber.setText(liCustomerCard.get(0).getCardNumber());
                    _tempCardNumber = liCustomerCard.get(0).getCardNumber();
                    etExpiration.setText(month +"/"+ lastTwo);
                    Log.e(TAG, "customerCardResult: if"+ _tempCardNumber);
                    _tempDate =(month +"/"+ lastTwo);
                    tvNewCard.setVisibility(View.VISIBLE);
                }else{
                    etCardNumber.setText(liCustomerCard.get(0).getRESPONSETEXT());
                    _tempCardNumber = liCustomerCard.get(0).getRESPONSETEXT();
                    Log.e(TAG, "customerCardResult: else"+_tempCardNumber );
                    etExpiration .setText(liCustomerCard.get(0).getRESPONSETEXT());
                    tvNewCard.setVisibility(View.VISIBLE);
                }

                if(Constant.twentyOneYear != null){
                    if(Constant.twentyOneYear.getCVVBypass()){
                         etCvv.setVisibility(View.GONE);
                    }else{
                         etCvv.setVisibility(View.VISIBLE);
                    }
                }

                etCardNumber.setFocusable(false);
                etCardNumber.setFocusableInTouchMode(false);
                etCardNumber.setClickable(false);
                etCardNumber.setPressed(false);
                etCardNumber.setBackgroundResource(R.drawable.border_edittext_disable);
                etCardNumber.setLongClickable(false);

                etExpiration.setFocusable(false);
                etExpiration.setFocusableInTouchMode(false);
                etExpiration.setClickable(false);
                etExpiration.setPressed(false);
                etExpiration.setBackgroundResource(R.drawable.border_edittext_disable);
                etExpiration.setLongClickable(false);
                etExpiration.setHint("mm/yy (Existing/Current)");

                etCardHolderName.setFocusable(false);
                etCardHolderName.setFocusableInTouchMode(false);
                etCardHolderName.setClickable(false);
                etCardHolderName.setPressed(false);
                etCardHolderName.setBackgroundResource(R.drawable.border_edittext_disable);
                etCardHolderName.setLongClickable(false);
                etCvv.requestFocus();
            }
        }
    }

    /**
     * Call Get Customer data web service
     **/
    private void getCustomerData() {
        if (UserModel.Cust_mst_ID != null) {
            String url = Constant.WS_BASE_URL + Constant.GET_CUSTOMER_DATA + UserModel.Cust_mst_ID + "/" + Constant.STOREID;
            TaskCustomerData taskCustomerData = new TaskCustomerData(getActivity(), this);
            Log.d(TAG, "Customer data : " + url);
            taskCustomerData.execute(url);
        }
    }


    ShippingData liShippingData;
    /**
     * Result : Customer Data Result
     **/
    @Override
    public void onTaskCustomerResult(ShippingData liShippingData, boolean isFromfavouriteStore) {

        hideDialog();
        // progressBar.setVisibility(View.GONE);
        // MainActivity.getInstance().getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
        this.liShippingData = liShippingData;

        Log.d(TAG, "Customer Result : " + liShippingData);
        //Toast.makeText(getActivity(), "result : " + liShippingData.getFirstName().trim() + " " + liShippingData.getLastName().trim(), Toast.LENGTH_SHORT).show();
        if (liShippingData != null) {
            etCardHolderName.setText(liShippingData.getFirstName().trim() + " " + liShippingData.getLastName().trim());
            etAddressOne.setText(liShippingData.getAddress1().trim());
            etAddressTwo.setText(liShippingData.getAddress2().trim());
            etCity.setText(liShippingData.getCity().trim());
            etState.setText(liShippingData.getState().trim());
            etZip.setText(liShippingData.getZip().trim());

//            Edited by Varun for Shippping Address

            if (rbPayAtStore&&rbPickUpAtStore||rbPayWithCart&&rbPickUpAtStore){
                rl_shipping_address.setVisibility(View.GONE);
            }else if (rbHandOnDelivery){
                tv_shipping_address.setText(R.string.lbl_delivery_address);
            }else if (rbShip){
                tv_shipping_address.setText(R.string.lbl_shipping_address);
            }

//            Edited by Varun for App crash on direct come to paymr=ent screen from delivery screen when email is with guest id
//            if (!rbPayAtStore&&!rbPickUpAtStore&&!rbPayWithCart&&!rbShip&&!rbHandOnDelivery&&!rbUberRush){
            if (rbPayAtStore&&rbPickUpAtStore||rbPayWithCart&&rbPickUpAtStore){
                rl_shipping_address.setVisibility(View.GONE);
            }else {
//                END

                if (liShippingData.getSAddress1().trim() != null && !liShippingData.getSAddress1().trim().isEmpty() && !liShippingData.getSAddress1().trim().equalsIgnoreCase("")) {
                    autoCompleteTextView_address_one.setText(liShippingData.getSAddress1().trim());
                } else {
                    autoCompleteTextView_address_one.setText(etAddressOne.getText().toString());
                }
                if (liShippingData.getSAddress2().trim() != null && !liShippingData.getSAddress2().trim().isEmpty() && !liShippingData.getSAddress2().trim().equalsIgnoreCase("")) {
                    et_shipping_address_two.setText(liShippingData.getSAddress2().trim());
                } else {
                    et_shipping_address_two.setText(etAddressTwo.getText().toString());
                }

                if (liShippingData.getSCity().trim() != null && !liShippingData.getSCity().trim().isEmpty() && !liShippingData.getSCity().trim().equalsIgnoreCase("")) {
                    et_shipping_city.setText(liShippingData.getSCity().trim());
                } else {
                    et_shipping_city.setText(etCity.getText().toString());
                }

                if (liShippingData.getSState().trim() != null && !liShippingData.getSState().trim().isEmpty() && !liShippingData.getSState().trim().equalsIgnoreCase("")) {
                    et_shipping_state.setText(liShippingData.getSState().trim());
                } else {
                    et_shipping_state.setText(etState.getText().toString());
                }

                if (liShippingData.getSZip().trim() != null && !liShippingData.getSZip().trim().isEmpty() && !liShippingData.getSZip().trim().equalsIgnoreCase("")) {
                    et_shipping_zip.setText(liShippingData.getSZip().trim());
                } else {
                    et_shipping_zip.setText(etZip.getText().toString());
                }
//          END
            }

            // getLoyaltyReward();
        }
    }

    /**
     * Get Credit Card Setting
     **/
    private void getCreditCardSetting() {
        String creditCardUrl = "";
        if (UserModel.Cust_mst_ID != null) {
            creditCardUrl = Constant.WS_BASE_URL + Constant.GET_CREDIT_CARD_SETTING + Constant.STOREID + "/" + UserModel.Cust_mst_ID;
            TaskGetCreditCardSetting cardSetting = new TaskGetCreditCardSetting(this);
            Log.d(TAG, "getCreditCardSetting: " + creditCardUrl);
            cardSetting.execute(creditCardUrl);
        }
    }


    /**
     * Interface : Credit Card Setting Result
     **/
    @Override
    public void creditCartSettingResult(CreditCartSetting creditCartSetting) {
        //Toast.makeText(getActivity(), "Result : " + creditCartSetting.getMerchantCode(), Toast.LENGTH_SHORT).show();

        Log.d(TAG, "creditCartSettingResult: " + creditCartSetting);
        merchantCode = creditCartSetting.getMerchantCode();
        merchantCustomerId = creditCartSetting.getMerchantCustID();
        merchantContractId = creditCartSetting.getMerchantContractID();

    }

    /**
     * Task : Loyalty Reward point web service
     **/
    public void getLoyaltyReward() {
        if (UserModel.Cust_mst_ID != null) {
            String url = Constant.WS_BASE_URL + Constant.GET_LOYALTY_INFO + UserModel.Cust_mst_ID + "/" + Constant.STOREID;
            TaskLoyaltyInfo loyaltyInfo = new TaskLoyaltyInfo(getActivity(),this);
            Log.d(TAG, "getLoyaltyReward: " + url);
            loyaltyInfo.execute(url);
        }
    }


    /**
     * Result : Loyalty Reward info
     **/
    @Override
    public void onLoyaltyInfoResult(LoyaltyInfo l) {
        Log.d(TAG, "onLoyaltyInfoResult: " + l);
        DecimalFormat df = new DecimalFormat("####0.00");
        df.setMaximumFractionDigits(2);
        //df.setRoundingMode(RoundingMode.UP);

        // Loyalty reward always
        // String query = " select tbl_loyalty_reward from customer_master_id where user_local_id";
        // where is never
        this.l = l;
        if (l != null/* && !rbPayAtStore*/) {

            if(l.getPoints() != null && !l.getPoints().isEmpty()){
                if (Integer.parseInt(l.getPoints()) >= Integer.parseInt(l.getAwardPoint()) &&
                        Integer.parseInt(l.getAwardPoint()) > 0) {
                    if (!l.getLoyaltyCard().equalsIgnoreCase("")&&l.getLoyaltyCard()!=null) {
//                    base = Math.floor((Float.parseFloat(l.getPoints()) / Float.parseFloat(l.getAwardPoint())) * Float.parseFloat(l.getAwardDollar()));
                        base = Double.parseDouble(l.getRebate());
                    }
                }
            }
            //Toast.makeText(getActivity(), "Loyalty : " + base, Toast.LENGTH_SHORT).show();

            if (l.getLoyaltyType().trim().equals("Internal")) {

            } else if (l.getLoyaltyType().trim().equals("Online")) {

            } else if (l.getLoyaltyType().trim().equals("Skoop")) {
                if (l.getPhoneNo().trim().equals("") || !l.getPhoneNo().equals("M")) {

                }
            } else {

            }

            //Reward functionality old comment on 16/7/2020
            if (base > 0) {
//                Edited by Varun when loyality is not active from set-up then it will not show apply my loyalty rebate in it
                if (l.getLoyaltyRewardStatus().equals("Y")){
//                    END
                    tvTitlePayment.setVisibility(View.VISIBLE);
                    cvPayment.setVisibility(View.VISIBLE);
//                cvPaymentOptions.setVisibility(View.VISIBLE);
                    rlRootRewardCashAvailable.setVisibility(View.VISIBLE);
                    tvTitleRewardCashAvailable.setText(String.valueOf(getString(R.string.lbl_reward_cash_available) + "$" /*+ base*/));
                    tvRewardCashAvailable.setText(String.valueOf(df.format(base)));
                }

            } else {
                if (rbPayAtStore && rbPickUpAtStore) {
                    tvTitlePayment.setVisibility(View.GONE);
                    cvPayment.setVisibility(View.GONE);
                    cvPaymentOptions.setVisibility(View.GONE);
                }
//               Edited by Varun for join loyalty reward in e-com
                if (!Constant.iscomefromSignuployalty && l.getLoyaltyRewardStatus().equals("Y") && l.getLoyaltyCard().equalsIgnoreCase("")
                        && l.getLoyaltyCard()!=null && l.getLoyaltyType().equals("Internal") ){
                    ll_join_reward_payment_fragment.setVisibility(View.VISIBLE);
                    Constant.iscomefromSignuployalty=false;
                }else{
                    ll_join_reward_payment_fragment.setVisibility(View.GONE);
                    Constant.iscomefromSignuployalty=false;
                }

            }// end and changed by below

//            if (rbPayAtStore && rbPickUpAtStore) {
//                tvTitlePayment.setVisibility(View.GONE);
//                cvPayment.setVisibility(View.GONE);
//                cvPaymentOptions.setVisibility(View.GONE);
//            }
            //end changes added above

            // TODO: 2/15/2018 This need to check if there is need to apply _subTotal only or need to do else


            //Edited by Janvi 3rd Oct ********
            if (base > _subTotal)
                tvApplyRewardCash.setText(String.valueOf("$" + df.format(_subTotal)));
            else {
                 tvApplyRewardCash.setText(String.valueOf("$" + df.format(base)));
            }
//        Edited by Varun for to see loyalty reward of pucharsed shown in que at the end .
//            It will only show when the join program is call and then
            if (!(llLoyaltyReword.getVisibility() == View.VISIBLE) && l.getLoyaltyRewardStatus().equals("Y") && !l.getLoyaltyCardNo().equals("")
                && !l.getLoyaltyCardNo().isEmpty() && l.getLoyaltyType().equals("Internal")){
                if (Constant._lPoints>0.0f){
                    vLoyaltyReward.setVisibility(View.VISIBLE);
                    llLoyaltyReword.setVisibility(View.VISIBLE);
                    tvLoyaltyReward.setText(String.valueOf(Math.round(Constant._lPoints)));
                }
            }
            //Reward functionality old comment on 16/7/2020
//            if(isDeliveryFee && ll_Delivery_fee.isShown()) {
//                double subtotalVal = _subTotal + deliveryFeeSurchargeVal;
//                if (base > subtotalVal){
//                    tvApplyRewardCash.setText("$" + String.valueOf(df.format(subtotalVal)));
//                }else{
//                    tvApplyRewardCash.setText(String.valueOf("$" + df.format(base)));
//                }
//            }else{
//                if (base > _subTotal){
//                    tvApplyRewardCash.setText(String.valueOf("$" + df.format(_subTotal)));
//                }else{
//                    tvApplyRewardCash.setText(String.valueOf("$" + df.format(base)));
//                }
//            }
            //end old comment on 16/7/2020  *************************

        }
    }

    private void sign_up_customer_for_loyalty_reward_from_ecom() {

        String url = Constant.WS_BASE_URL + Constant.SIGN_UP_CUSTOMER_FOR_LOYALTY_REWARD_FROM_ECOM + Constant.STOREID +"/" + UserModel.Cust_mst_ID +"/" + Constant.ENCODE_TOKEN_ID;
        TaskSign_Up_For_Loyalty sign_up_for_loyalty = new TaskSign_Up_For_Loyalty(getActivity(),this);
        Log.d("", "getSign_up_Loyalty: " + url);
        sign_up_for_loyalty.execute(url);

    }

    @Override
    public void on_Sign_up_Loyalty_Info_Result(SignupLoyaltyInfo signupLoyaltyInfo) {

        if (signupLoyaltyInfo.getResult().equalsIgnoreCase("Success")){
//            Toast.makeText(getActivity(), "Fail", Toast.LENGTH_SHORT).show();
            Utils.Validation_Simple_Dialog(getActivity(),signupLoyaltyInfo.getMessage(),"sign_up_loyalty_success");
        }else{
            Utils.Validation_Simple_Dialog(getActivity(),signupLoyaltyInfo.getMessage(),"sign_up_loyalty_fail");
        }

    }

    /**
     * GiftWrap Checked / UnChecked...
     **/
    @Override
    public void onGiftWrapChecked(int position, List<ShoppingCardModel> liShoppingCart) {

        int giftWrap = liShoppingCart.get(position).getGiftWrap() ? 1 : 0;
        String url = Constant.WS_BASE_URL + Constant.UPDATE_CART_DATA_OF_GIFT_WRAP + liShoppingCart.get(position).getCartID() + "/" + giftWrap + "/" + Constant.STOREID;
        TaskGiftWrap loyaltyInfo = new TaskGiftWrap(this);
        loyaltyInfo.execute(url);
        Log.e("Log", "URL=" + url);
    }


    /**
     * Result : Gift Wrap web service result
     **/
    @Override
    public void onGiftWrapResponse(GiftWrap giftWrap) {
        Log.d(TAG, "onGiftWrapResponse: " + giftWrap);
    }



    @Override
    public void onClick(View view) {
        if (view.getId() == btnPrev.getId()) {
            onBackPressed();
        }

        if (view.getId() == btnPlaceOrder.getId()) {
            /*if (cbxSaveCard.isChecked()){
                callSaveCard("0","0","0",etAddressOne.getText().toString(),etCity.getText().toString(),
                        etState.getText().toString().trim(),etZip.getText().toString().trim(),"0","0"
                        ,etCardNumber.getText().toString().replace(" ",""),"11","20");
            }*/

//            Utils.showDiscountgroupDialog(getActivity(), recommandedItemList.get(position).getDesc1(), recommandedItemList.get(position).getGrpMemo(), "", null);

            // for real time inventory uncomment below line and comment save place order function

//         ****************    Edited by Varun Validator for USAePAY on btn click **************
//              getCustomerCarddataPaymentWS();
            if (rbPayAtStore){
                getCustomerCarddataPaymentWS();
            }else{
//                Edited Varun for zip not match
                if (etZip.getText().toString().equals("") || etZip.getText().toString()==null){
                    etZip.setError("Invalid Zip");
                }else {
//                END
                    if (etCardNumber.isFocusable()) {
                        if (!Validator.validateCard(etCardNumber.getText().toString().trim())) {
                            etCvv.clearFocus();
                            etCardNumber.requestFocus();
                            etCardNumber.setError("Invalid Card");
                        } else {
                            getCustomerCarddataPaymentWS();
                        }
                    } else {
                        getCustomerCarddataPaymentWS();
                    }
                }
            }
//            *************     END  *****************

//            savePlaceOrder();

            // real time end  *********

            /*if (myPaymentEvent!=null)
                myPaymentEvent.loadOrderSummaryFragment();*/
        }

        if (view.getId() == tvNewCard.getId()) {
            newCard();
        }
    }


    /**
     * Set new card or Existing Card data
     **/
    public void newCard() {
        if (tvNewCard.getText().toString().equals("Existing Card")) {
            tvNewCard.setText(Utils.underlineText(String.valueOf(getString(R.string.lbl_new_cart))));
            if (!_tempCardNumber.isEmpty()) {
                etCardNumber.setText(_tempCardNumber);
//   *************   Edited by Varun for to show then expiration date when user click the new card and after that click the new card *************
                etExpiration.setText(_tempDate);
//           ***************     END **********************
            }
            etCvv.setVisibility(View.GONE);

            GradientDrawable d = new GradientDrawable();
            d.setColor(R.drawable.border_edittext_disable);
            etCardNumber.setFocusable(false);
            etCardNumber.setFocusableInTouchMode(false);
            etCardNumber.setClickable(false);
            etCardNumber.setPressed(false);

//******      Edited by Varun for to remove the error when Existing card is there the secnerio is when user click the new card and add wrong card it gives the error msg
//            and then when user click the existing card the Error will not gone so for..**********

            etCardNumber.setError(null);

//       ******************     END ******************
            //etCardNumber.setBackground(d);
            etCardNumber.setBackgroundResource(R.drawable.border_edittext_disable);
            etCardNumber.setLongClickable(false);

            if(Constant.twentyOneYear != null){
                if(Constant.twentyOneYear.getCVVBypass()){
                    etCvv.setVisibility(View.GONE);
                }else{
                    etCvv.setVisibility(View.VISIBLE);
                }
            }

            etExpiration.setFocusable(false);
            etExpiration.setFocusableInTouchMode(false);
            etExpiration.setClickable(false);
            etExpiration.setPressed(false);
            etExpiration.setBackgroundResource(R.drawable.border_edittext_disable);
            etExpiration.setLongClickable(false);
            etExpiration.setHint("mm/yy (Existing/Current)");

            etCardHolderName.setFocusable(false);
            etCardHolderName.setFocusableInTouchMode(false);
            etCardHolderName.setClickable(false);
            etCardHolderName.setPressed(false);
            etCardHolderName.setBackgroundResource(R.drawable.border_edittext_disable);
            etCardHolderName.setLongClickable(false);

            cbxSaveCard.setClickable(false);
            //cbxSaveCard.setBackgroundTintMode(Color.GRAY);
            //cbxSaveCard.setButtonDrawable(R.drawable.applogo);
            //BSTheme.setCheckBoxColor(cbxSaveCard, Color.GRAY, Color.GRAY);

            rlRootSaveCard.setVisibility(View.GONE);
            llHideCheckBox.setVisibility(View.VISIBLE);
            BSTheme.setCheckBoxColor(cbxSaveCard, Color.GRAY, Color.GRAY);
            //cbxSaveCard.setChecked(false);

        } else {

            etCvv.setVisibility(View.VISIBLE);

            //cbxSaveCard.toggle();
            cbxSaveCard.setClickable(true);
            llHideCheckBox.setVisibility(View.GONE);
            BSTheme.setCheckBoxColor(cbxSaveCard, Color.parseColor(Constant.themeModel.ThemeColor), Color.GRAY);
            //cbxSaveCard.setChecked(false);

            GradientDrawable old = new GradientDrawable();
            old.setColor(R.drawable.border_edittext);

            etCardNumber.setText("");
            etCvv.setText("");
            etExpiration.setText("");
            //cbxSaveCard.setClickable(false);

            etExpiration.setFocusable(true);
            etExpiration.setFocusableInTouchMode(true);
            etExpiration.setClickable(true);
            etExpiration.setBackgroundResource(R.drawable.border_edittext);
            etExpiration.setEnabled(true);
            etExpiration.setHint("mm/yy");

            etCardNumber.setFocusable(true);
            etCardNumber.setFocusableInTouchMode(true);
            etCardNumber.setClickable(true);
            etCardNumber.setBackgroundResource(R.drawable.border_edittext);
            etCardNumber.setEnabled(true);
            etCardNumber.requestFocus();

            etCardHolderName.setFocusable(true);
            etCardHolderName.setFocusableInTouchMode(true);
            etCardHolderName.setClickable(true);
            etCardHolderName.setEnabled(true);
            etCardHolderName.setBackgroundResource(R.drawable.border_edittext);
            tvNewCard.setText(Utils.underlineText(String.valueOf(getString(R.string.lbl_existing_card))));
//            Edited by Varun
            if (Constant.ISguest){
                rlRootSaveCard.setVisibility(View.GONE);
            }else {
                rlRootSaveCard.setVisibility(View.VISIBLE);
            }
//            END
        }
    }

    void disableInput(EditText editText) {
        editText.setInputType(InputType.TYPE_NULL);
        editText.setTextIsSelectable(false);
        editText.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                return true;  // Blocks input from hardware keyboards.
            }
        });
    }

    public void onBackPressed() {

//        if(!DeliveryOptionsFragment.getInstance().isDeliveryPage){
//            CardFragment cardFragment = new CardFragment();
//            getFragmentManager().popBackStack();
//
//            cardFragment = new CardFragment();
//            FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
//            fragmentTransaction.setCustomAnimations(android.R.anim.fade_in, android.R.anim.fade_out);
//            fragmentTransaction.replace(R.id.mContent, cardFragment, "fragment");
//            fragmentTransaction.addToBackStack(null);
//            fragmentTransaction.commit();
//        }else{
//    }
//
//            FragmentManager fm = getActivity().getSupportFragmentManager();
//            fm.popBackStack();

        if(Constant.isbackFromPayment) {

            FragmentManager fm = Objects.requireNonNull(getActivity()).getSupportFragmentManager();
            for (int i = fm.getBackStackEntryCount() - 1; i > 0; i--) {
                if(fm.getBackStackEntryAt(i).getName() != null && !Objects.requireNonNull(fm.getBackStackEntryAt(i).getName()).equalsIgnoreCase("null")){
                    if(!Objects.requireNonNull(fm.getBackStackEntryAt(i).getName()).equalsIgnoreCase("DeliveryOptionsFragment.class.getSimpleName()")){
                        fm.popBackStack();
                    }else{
                        break;
                    }
                }else{
                    break;
                }

//                if (!Objects.requireNonNull(fm.getBackStackEntryAt(i).getName()).equalsIgnoreCase("null") && !Objects.requireNonNull(fm.getBackStackEntryAt(i).getName()).equalsIgnoreCase("DeliveryOptionsFragment.class.getSimpleName()")) {
//                    fm.popBackStack();
//                } else {
//                    break;
//                }
            }
            Constant.isbackFromPayment = false;

        }else{
            FragmentManager fm = Objects.requireNonNull(getActivity()).getSupportFragmentManager();
            fm.popBackStack();
        }

//        if (getActivity().getSupportFragmentManager().findFragmentByTag("CardFragment") != null) {
//            getActivity().getSupportFragmentManager().popBackStack("PaymentFragment.class.getSimpleName()",
//                    FragmentManager.POP_BACK_STACK_INCLUSIVE);
//        } else{
//            FragmentManager fm = getActivity().getSupportFragmentManager();
//              fm.popBackStack();
//        }

    }

    /**
     * Task : Place Order Web Service call
     **/
    private void savePlaceOrder() {


        //Edited by janvi 9th jan ***********
//        String cardNo, fourdigitcardnumber = "";
//
//        if(etCardNumber.getText().toString().isEmpty()){
//            cardNo = "0";
//        }
//        else{
//
//            cardNo = etCardNumber.getText().toString().trim();
//            fourdigitcardnumber = cardNo.substring(cardNo.length() - 4);
//        }
//
//        //***************************
//
//
//        String cvv = etCvv.getText().toString().trim();
//        String strexp = etExpiration.getText().toString().trim();
//        String[] arrexp = strexp.split("/");
//
//        if (arrexp.length == 2) {
//            expMonth = arrexp[0].trim();
//            expYear = arrexp[1].trim();
//        }
//
//        String Amount = "0";
//        String address = etAddressOne.getText().toString().trim();
//        address = address.replaceAll(" ", "%20");
//        String zip = etZip.getText().toString().trim().trim();
//        Amount = "" + tvTotal.getText().toString().trim();
//
//        Amount = Amount.replace("$", "");
//        cardNo = cardNo.replace(" ", "");
//        String MerchantCustID = etCvv.getText().toString().trim();
//        String MerchantContractID = etCvv.getText().toString().trim();
//        String cardType = tvNewCard.getText().toString().trim();
//
        if (!rbPayAtStore) {
            _paymentOption = "2";
//            if (cardType.equals("New Card")) {
//                if (etCvv.getText().toString().trim().isEmpty()) {
//                    etCvv.setError("Enter CVV.");
//                    etCvv.requestFocus();
//                    return;
//                }
//            } else {
//                if (!cardValidate(expMonth)) {
//                    return;
//                }
//            }
//
//            if (merchantCode.isEmpty() || merchantCode == null) {
//                merchantCode = "0";
//            }
//            if (merchantCustomerId.isEmpty() || merchantCustomerId == null) {
//                merchantCustomerId = "0";
//            }
//            if (merchantContractId.isEmpty() || merchantContractId == null) {
//                merchantContractId = "0";
//            }
//
//            String url = "";


            callInsertOrderDataBeforePayware(null, null, null, null, null);


            //start to call ws GetpreAuthCustomerCard ******************

//            if (cardType.equals("New Card")) {
//
//                //Edited by janvi 1stJan2019
//
////                url = Constant.WS_BASE_URL + Constant.SAVE_PLACE_ORDER + Constant.STOREID + "/"
////                        + "0" + "/" + cvv + "/" + "0" + "/" + "0" + "/" + address + "/" + zip + "/" + Amount + "/"
////                        + merchantCustomerId + "/" + merchantContractId;
//                //end ********
//
//                url = Constant.WS_BASE_URL + Constant.SAVE_PLACE_ORDER + Constant.STOREID + "/"
//                        + cardNo + "/" + cvv + "/" + "0" + "/" + "0" + "/" + address.trim() + "/" + zip + "/" + Amount + "/"
//                        + merchantCustomerId + "/" + merchantContractId;
//
//            } else if (cardType.equals("Existing Card")) {
//                url = Constant.WS_BASE_URL + Constant.SAVE_PLACE_ORDER + Constant.STOREID + "/"
//                        + cardNo + "/" + cvv + "/" + expMonth + "/" + expYear + "/" + address + "/" + zip + "/" + Amount + "/" + "0" + "/" + "0";
//            }
//
//            TaskPayWare taskPayWare = new TaskPayWare(this,fourdigitcardnumber);
//            Log.d("existingCartUrl", "savePlaceOrder: URl : " + url);
//            //showDialog();
//            showPaymentProcessDialog(fourdigitcardnumber,Amount);
//            //janvi
//
//            Log.d("dialog ", "Showing dialog during competed transaction ");
//            taskPayWare.execute(url);

            //end ******************************************

        } else {
            _paymentOption = "1";
            //showDialog();
            btnPlaceOrder.setClickable(false);
            Log.d("dialog ", "Show dialog when PayAtStore ");
            callInsertOrderDetailed("0", "0", "0", "0", "0");
        }

    }

    private void callInsertOrderDataBeforePayware(String orderId, String ctroutd, String troutd, String paymentMedia, String authCode) {

        String orderUrl = "";
        String cardType = tvNewCard.getText().toString().trim();
        String rootUrl = Constant.WS_BASE_URL + Constant.INSERT_ORDER_DATA_BEFORE_PAYWARE + UserModel.Cust_mst_ID + "/"
                + Constant.STOREID + "/";

        String card;
        String cardHolderName;
        String expiration;

        //Edited by Janvi 9th jan --- // commented existing card condition
//        if (cardType.equals("Existing Card")) {
        card = (etCardNumber.getText().toString().trim().isEmpty()) ? "0" : etCardNumber.getText().toString().trim().replace(" ", "");
        if(!card.equals("0")){
            card = card.substring(card.length() - 5);
        }
        cardHolderName = (etCardHolderName.getText().toString().trim().isEmpty()) ? "0" : etCardHolderName.getText().toString().trim();
        expiration = (etExpiration.getText().toString().isEmpty()) ? "0" : etExpiration.getText().toString().replace("/", " ");
//        } else {
//            card = "0";
//            cardHolderName = "0";
//            expiration = "0";
//        }
        // end *************************


        /*String loyaltyPoint ;
        if (base > _subTotal){
            loyaltyPoint = _subTotal;
        }else{
            loyaltyPoint = base;
        }*/
        String loyaltyPoint = tvLoyaltyReward.getText().toString();
        String spnReward = tvRewardCashAvailable.getText().toString();
        String spnRewardUse = tvMyReward.getText().toString().replace("$", "").trim();
        String total = tvTotal.getText().toString().replace("$", "").trim();
//        Edited by Varun for shipping charges
        String ss =  tvS_name.getText().toString();
        String s_charges = tvShip.getText().toString().replace("$", "").trim();
//        End

        loyaltyPoint = (loyaltyPoint.equals("0.00")) ? "0" : loyaltyPoint;
        total = (total.isEmpty()) ? "0" : total;
        spnReward = (spnReward.isEmpty()) ? "0.00" : spnReward ;
        spnRewardUse = (spnRewardUse.isEmpty()) ? "0" : spnRewardUse ;
//        Edited by Varun for shipping charges
        s_charges = (s_charges.isEmpty()) ? "0.00" : s_charges;
//        End

        if(customernote.isEmpty()){
            customernote = "null";
        }
//        else{
//            if(customernote.contains("\n")){
////                customernote = customernote.replace("\n","♠♥");
//                customernote = customernote.replace("\n","&spades;&hearts;");
//            }
//        }
        if (cbxApplyRewardCash.isChecked()) {

//            Edited by Varun for shipping charges

//            orderUrl = rootUrl + orderId + "/" + _subTotal + "/" + _totalSaving + "/" + total + "/"
//                    + _paymentOption + "/" + /*"0"*/ loyaltyPoint /*loyalty point*/ + "/" + "mobile" /*#Browser*/ + "/" + /*"0.00"*/ spnReward /*spnRewards*/ + "/"
//                    + /*"0" */ spnRewardUse /*rewardDollarUse*/ + "/" + /*"0"*/ pointUsed + "/" + card
//                    + "/" + expiration + "/" + cardHolderName + "/" + ctroutd + "/" + troutd + "/"
//                    + authCode + "/" + paymentMedia + "/" + _tipValue /*Tip value */ + "/" + _tipCCValue + "/" + deliveryFeeSurchargeVal + "/" + "Android App" + "/" + savednoteStatus;;
            orderUrl = rootUrl + orderId + "/" + _subTotal + "/" + _totalSaving + "/" + total + "/"
                    + _paymentOption + "/" + /*"0"*/ loyaltyPoint /*loyalty point*/ + "/" + "mobile" /*#Browser*/ + "/" + /*"0.00"*/ spnReward /*spnRewards*/ + "/"
                    + /*"0" */ spnRewardUse /*rewardDollarUse*/ + "/" + /*"0"*/ pointUsed + "/" + card
                    + "/" + expiration + "/" + cardHolderName + "/" + ctroutd + "/" + troutd + "/"
                    + authCode + "/" + paymentMedia + "/" + _tipValue /*Tip value */ + "/" + _tipCCValue + "/" + deliveryFeeSurchargeVal + "/" + "Android App" + "/" + savednoteStatus
                    + "/" + "0" + "/" + s_charges + "/" + ss;;

        } else {

//            orderUrl = rootUrl + orderId + "/" + _subTotal + "/" + _totalSaving + "/" + total + "/"
//                    + _paymentOption + "/" + loyaltyPoint /*loyalty point*/ + "/" + "mobile" /*#Browser*/ + "/" + spnReward /*spnRewards*/ + "/"
//                    + "0" /*rewardDollarUse*/ + "/" + "0" /*pointUsed*/ + "/" + card /*etCardNumber.getText().toString().trim().replace(" ", "")*/
//                    + "/" + expiration /*etExpiration.getText().toString().replace(" / ", "")*/ /*Expiration*/ + "/" + cardHolderName /*etCardHolderName.getText().toString().trim()*/ + "/" + ctroutd + "/" + troutd + "/"
//                    + authCode + "/" + paymentMedia + "/" + _tipValue /*Tip value */ + "/" + _tipCCValue + "/" + deliveryFeeSurchargeVal + "/" + "Android App" + "/" + savednoteStatus;

            orderUrl = rootUrl + orderId + "/" + _subTotal + "/" + _totalSaving + "/" + total + "/"
                    + _paymentOption + "/" + loyaltyPoint /*loyalty point*/ + "/" + "mobile" /*#Browser*/ + "/" + spnReward /*spnRewards*/ + "/"
                    + "0" /*rewardDollarUse*/ + "/" + "0" /*pointUsed*/ + "/" + card /*etCardNumber.getText().toString().trim().replace(" ", "")*/
                    + "/" + expiration /*etExpiration.getText().toString().replace(" / ", "")*/ /*Expiration*/ + "/" + cardHolderName /*etCardHolderName.getText().toString().trim()*/ + "/" + ctroutd + "/" + troutd + "/"
                    + authCode + "/" + paymentMedia + "/" + _tipValue /*Tip value */ + "/" + _tipCCValue + "/" + deliveryFeeSurchargeVal + "/" + "Android App" + "/" + savednoteStatus
                    + "/" + "0" + "/" + s_charges + "/" + ss;;
        }

        //end *********************

        TaskInsertOrderDetail orderDetail = new TaskInsertOrderDetail(getActivity(), this);
        Log.e(TAG, "url" + orderUrl);
        orderDetail.execute(orderUrl);
    }


    public boolean cardValidate(String month) {
        boolean valid = true;

        if (etCardNumber.getText().toString().trim().isEmpty()) {
            etCardNumber.setError("Enter Card Number.");
            etCardNumber.requestFocus();
            valid = false;
        }
//        else if (etCardNumber.getText().toString().length() < 19) {
//            etCardNumber.setError("Card Number Must be 16 Character.");
//            etCardNumber.requestFocus();
//            valid = false;
//        }
        else if (etCvv.getVisibility() == View.VISIBLE && etCvv.getText().toString().trim().isEmpty()) {
            etCvv.setError("Enter CVV.");
            etCvv.requestFocus();
            valid = false;
        } else if (etExpiration.getText().toString().trim().isEmpty()) {
            etExpiration.setError("Enter Card Expiration.");
            etExpiration.requestFocus();
            valid = false;
        } else if (!month.isEmpty() && Integer.parseInt(month) > 12) {
            etExpiration.setError("Card Expiration Month is Invalid!");
            etExpiration.requestFocus();
            valid = false;
        } else if (etExpiration.getText().toString().trim().length() < 5) {
            etExpiration.setError("Enter Card Expiration.");
            etExpiration.requestFocus();
            valid = false;
        } else {
            etCardNumber.setError(null);
            etCvv.setError(null);
            etExpiration.setError(null);
        }
        return valid;
    }

    /**
     * Payware web service Response
     **/

    public void callSaveCard(String companyName, String firstName, String lastName, String address, String city, String state,
                             String zip, String primaryPhone, String email, String cartNo, String expMonth, String expYear) {

        companyName = (companyName.isEmpty()) ? "0" : companyName;
        firstName = (firstName.isEmpty()) ? "0" : firstName;
        lastName = (lastName.isEmpty()) ? "0" : lastName;
        address = (address.isEmpty()) ? "0" : address;
        city = (city.isEmpty()) ? "0" : city;
        state = (state.isEmpty()) ? "0" : state;
        zip = (zip.isEmpty()) ? "0" : zip;
        primaryPhone = (primaryPhone.isEmpty()) ? "0" : primaryPhone;
        email = (email.isEmpty()) ? "0" : email;
        cartNo = (cartNo.isEmpty()) ? "0" : cartNo;
        expMonth = (expMonth.isEmpty()) ? "0" : expMonth;
        expYear = (expYear.isEmpty()) ? "0" : expYear;


        if(Constant.isUSAePAY){

            String saveCardUrl = "";
            if (UserModel.Cust_mst_ID != null) {
                ///InsertCustomerCreditCardsXML/{storeno}/{CustomerID}/{CompanyName}/{CustFirstName}/{CustLastName}/{Address}/{City}/{State}/{Zip}/{PrimaryPhone}/{Email}/{PrimaryAccount}/{PrimaryExpMnth}/{PrimaryExpYear}
//                saveCardUrl = Constant.WS_BASE_URL + Constant.USAEPAY_SAVECUSTOMERCARD + Constant.STOREID + "/" + UserModel.Cust_mst_ID + "/"
//                        + cartNo + "/" + expMonth + "/" + expYear;

//                Edited by Varun FOR NEW API WITH TOKEN ID

                saveCardUrl = Constant.WS_BASE_URL + Constant.SAVE_CUSTOMER_DATA_IN_USAEPAY_VAULT + Constant.STOREID + "/" + UserModel.Cust_mst_ID + "/"
                        + cartNo + "/" + expMonth + "/" + expYear + "/" + Constant.ENCODE_TOKEN_ID;

//                END

                TaskSaveCard taskSaveCard = new TaskSaveCard(this);
                taskSaveCard.execute(saveCardUrl);
            }

        }else {

            String saveCardUrl = "";
            if (UserModel.Cust_mst_ID != null) {
                ///InsertCustomerCreditCardsXML/{storeno}/{CustomerID}/{CompanyName}/{CustFirstName}/{CustLastName}/{Address}/{City}/{State}/{Zip}/{PrimaryPhone}/{Email}/{PrimaryAccount}/{PrimaryExpMnth}/{PrimaryExpYear}
                saveCardUrl = Constant.WS_BASE_URL + Constant.SAVE_CARD + Constant.STOREID + "/" + UserModel.Cust_mst_ID + "/"
                        + companyName + "/" + firstName + "/" + lastName + "/" + address + "/" + city + "/" + state + "/"
                        + zip + "/" + primaryPhone + "/" + email + "/" + cartNo + "/" + expMonth + "/" + expYear;
                TaskSaveCard taskSaveCard = new TaskSaveCard(this);
                taskSaveCard.execute(saveCardUrl);
            }
        }
    }


    @Override
    public void onSaveCardResult(List<SaveCard> liSaveCard) {
        Log.d(TAG, "onSaveCardResult: " + liSaveCard);

        //now billing
    }

    /**
     * Call : Insert Order Detail
     **/
    public void callInsertOrderDetailed(String orderId, String ctroutd, String troutd, String paymentMedia, String authCode) {
        String orderUrl = "";
        String cardType = tvNewCard.getText().toString().trim();

        //Test URL for pay at store (without card)
        //http://192.168.172.211:889/WebStoreRestService.svc/InsertOrderDetails/188716/707/null/20.00/0.00/20.20/1/0/Chrome/0.00/0/0/0/0/0/null/null/null/null/0.00/0

        /* Test Url with Card but not applied loyalty reward */
        //http://192.168.172.211:889/WebStoreRestService.svc/InsertOrderDetails/188716/707/20180207112603/20.0/0.0/20.2/1/0/Chrome/0.00/0/0/5499990123456781/11/20/Mehul Solanki/2457/5950056/010499/MC/0/0

       /* orderUrl = Constant.WS_BASE_URL + Constant.INSERT_ORDER_DETAIL + UserModel.Cust_mst_ID + "/"
                + Constant.STOREID + "/" + orderId + "/" + _subTotal + "/" + _totalSaving + "/" + _total + "/"
                + _paymentOption + "/" + "0" *//*loyalty point*//*  + "/" + "Chrome" *//*#Browser*//* + "/" + "0.00" *//*spnRewards*//*  + "/"
                + "0" *//*rewardDollarUse*//* + "/" + "0" *//*pointUsed*//* + "/" + etCardNumber.getText().toString().trim().replace(" ","")
                + "/" + "11/20" *//*Expiration*//* + "/" + etCardHolderName.getText().toString().trim() + "/" + ctroutd + "/" + troutd + "/"
                + authCode + "/" + paymentMedia + "/" + "0" *//*Tip value *//* + "/" + "0" *//* Tip CC Value*//*;*/


        // TODO: 2/7/2018 Fix Loyalty point variable

        String rootUrl = Constant.WS_BASE_URL + Constant.INSERT_ORDER_DETAIL + UserModel.Cust_mst_ID + "/"
                + Constant.STOREID + "/";

        String card;
        String cardHolderName;
        String expiration;

        //Edited by Janvi 9th jan --- // commented existing card condition
//        if (cardType.equals("Existing Card")) {
        card = (etCardNumber.getText().toString().trim().isEmpty()) ? "0" : etCardNumber.getText().toString().trim().replace(" ", "");
        if(!card.equals("0")){
            card = card.substring(card.length() - 5);
        }
        cardHolderName = (etCardHolderName.getText().toString().trim().isEmpty()) ? "0" : etCardHolderName.getText().toString().trim();
        expiration = (etExpiration.getText().toString().isEmpty()) ? "0" : etExpiration.getText().toString().replace("/", " ");
//        } else {
//            card = "0";
//            cardHolderName = "0";
//            expiration = "0";
//        }
        // end *************************


        /*String loyaltyPoint ;
        if (base > _subTotal){
            loyaltyPoint = _subTotal;
        }else{
            loyaltyPoint = base;
        }*/
        String loyaltyPoint = tvLoyaltyReward.getText().toString();
        String spnReward = tvRewardCashAvailable.getText().toString();
        String spnRewardUse = tvMyReward.getText().toString().replace("$", "").trim();
        String total = tvTotal.getText().toString().replace("$", "").trim();

        loyaltyPoint = (loyaltyPoint.equals("0.00")) ? "0" : loyaltyPoint;
        total = (total.isEmpty()) ? "0" : total;
        spnReward = (spnReward.isEmpty()) ? "0.00" : spnReward ;
        spnRewardUse = (spnRewardUse.isEmpty()) ? "0" : spnRewardUse ;

        if(customernote.isEmpty()){
            customernote = "null";
        }
//        else{
//            if(customernote.contains("\n")){
////                customernote = customernote.replace("\n","♠♥");
//                customernote = customernote.replace("\n","&spades;&hearts;");
//            }
//        }

        if (rbPayAtStore && rbPickUpAtStore) {

            //Edited by Janvi 2nd Oct append deliveryFeeSurchargeVal in orderUrl **********
            if (cbxApplyRewardCash.isChecked()){
                orderUrl = rootUrl + "null" /*orderId*/ + "/" + _subTotal + "/" + _totalSaving + "/" + total + "/"
                        + _paymentOption + "/" + loyaltyPoint /*loyalty point*/ + "/" + "mobile" /*#Browser*/ + "/"
                        + spnReward /*spnRewards*/ + "/" + spnRewardUse /*rewardDollarUse*/ + "/"
                        + pointUsed /*point Use*/ + "/" + "0" /*Card No*/ + "/" + "0" /*expiration*/ + "/" + "0" /*cardHolderName*/ + "/"
                        + "null" /*ctroutd*/ + "/" + "null" /*troutd*/ + "/" + "null" /*authCode*/ + "/" + "null" /*paymentMedia*/ + "/"
                        + "0.00" /*Tip value */ + "/" + "0"  + "/" + deliveryFeeSurchargeVal + "/" + "Android App" + "/" + customernote;
            }else{
                orderUrl = rootUrl + "null" /*orderId*/ + "/" + _subTotal + "/" + _totalSaving + "/" + total + "/"
                        + _paymentOption + "/" + loyaltyPoint /*loyalty point*/ + "/" + "mobile" /*#Browser*/ + "/"
                        + spnReward /*spnRewards*/ + "/" + "0" /*spnRewardUse*/ /*rewardDollarUse*/ + "/"
                        + "0" /*point Use*/ + "/" + "0" /*Card No*/ + "/" + "0" /*expiration*/ + "/" + "0" /*cardHolderName*/ + "/"
                        + "null" /*ctroutd*/ + "/" + "null" /*troutd*/ + "/" + "null" /*authCode*/ + "/" + "null" /*paymentMedia*/ + "/"
                        + "0.00" /*Tip value */ + "/" + "0" + "/" + deliveryFeeSurchargeVal + "/" + "Android App" + "/" + customernote;
            }
           /* orderUrl = rootUrl + "null" + "/" + _subTotal + "/" + _totalSaving + "/" + _total + "/"
                    + _paymentOption + "/" + "0" *//*loyalty point*//* + "/" + "mobile" *//*#Browser*//* + "/"
                    + "0.00" *//*spnRewards*//* + "/" + "0" *//*rewardDollarUse*//* + "/" + "0" *//*pointUsed*//* + "/"
                    + "0" + "/" + "0" + "/" + "0" + "/" + "null" + "/" + "null" + "/" + "null" + "/"
                    + "null" + "/" + "0.00" *//*Tip value *//* + "/" + "0" *//* Tip CC Value*//*;*/


        } else if (cbxApplyRewardCash.isChecked()) {

            orderUrl = rootUrl + orderId + "/" + _subTotal + "/" + _totalSaving + "/" + total + "/"
                    + _paymentOption + "/" + /*"0"*/ loyaltyPoint /*loyalty point*/ + "/" + "mobile" /*#Browser*/ + "/" + /*"0.00"*/ spnReward /*spnRewards*/ + "/"
                    + /*"0" */ spnRewardUse /*rewardDollarUse*/ + "/" + /*"0"*/ pointUsed + "/" + card
                    + "/" + expiration + "/" + cardHolderName + "/" + ctroutd + "/" + troutd + "/"
                    + authCode + "/" + paymentMedia + "/" + _tipValue /*Tip value */ + "/" + _tipCCValue + "/" + deliveryFeeSurchargeVal + "/" + "Android App" + "/" + savednoteStatus;;
        } else {

            orderUrl = rootUrl + orderId + "/" + _subTotal + "/" + _totalSaving + "/" + total + "/"
                    + _paymentOption + "/" + loyaltyPoint /*loyalty point*/ + "/" + "mobile" /*#Browser*/ + "/" + spnReward /*spnRewards*/ + "/"
                    + "0" /*rewardDollarUse*/ + "/" + "0" /*pointUsed*/ + "/" + card /*etCardNumber.getText().toString().trim().replace(" ", "")*/
                    + "/" + expiration /*etExpiration.getText().toString().replace(" / ", "")*/ /*Expiration*/ + "/" + cardHolderName /*etCardHolderName.getText().toString().trim()*/ + "/" + ctroutd + "/" + troutd + "/"
                    + authCode + "/" + paymentMedia + "/" + _tipValue /*Tip value */ + "/" + _tipCCValue + "/" + deliveryFeeSurchargeVal + "/" + "Android App" + "/" + savednoteStatus;;
        }

        //end *********************

        TaskInsertOrderDetail orderDetail = new TaskInsertOrderDetail(getActivity(),this);
        Log.e(TAG, "url" + orderUrl);
        orderDetail.execute(orderUrl);

    }

    /**
     * Result : Insert Order Detail (Complete inserting order and payment)
     **/
    @Override
    public void OrderDetailedResult(InsertOrderDetailed insertOrderDetailed) {

        Log.d(TAG, "OrderDetailedResult: " + insertOrderDetailed);
        String response = "", orderId = "", email = "";
        if (insertOrderDetailed.getResult().contains("success")) {
            /** Clear Shopping Cart Icon Count **/

            if(rbPayAtStore && rbPickUpAtStore){

                if(Constant.SCREEN_LAYOUT==1){
                    MainActivity.getInstance().updateShoppingCartItemCount(0);
                }else if(Constant.SCREEN_LAYOUT==2) {
                    MainActivityDup.getInstance().updateShoppingCartItemCount(0);
                }

                //Toast.makeText(getActivity(), "result : " + insertOrderDetailed.getResult(), Toast.LENGTH_SHORT).show();
                String insertOrderResult = insertOrderDetailed.getResult();
                String[] resultArray = insertOrderResult.split("/");

                if (resultArray.length != 0 && resultArray.length >1) {
                    response = resultArray[0].trim();
                    if(!resultArray[1].equals(""))
                        orderId = resultArray[1].trim();
                    email = resultArray[2].trim();
                }

                //Bundle i = new Bundle();
                i.putString("orderId", orderId);

                //for PickupTime
                    i.putString("pickupDay",pickupDay);
                    i.putString("pickupTime",pickupTime);
                    i.putString("pickupCurrentDay",pickupCurrentDay);
                //end **********

                updateBillingAddress();
            }else{

                if(Constant.SCREEN_LAYOUT==1){
                    MainActivity.getInstance().updateShoppingCartItemCount(0);
                }else if(Constant.SCREEN_LAYOUT==2) {
                    MainActivityDup.getInstance().updateShoppingCartItemCount(0);
                }

                String insertOrderResult = insertOrderDetailed.getResult();
                String[] resultArray = insertOrderResult.split("/");

                if (resultArray.length != 0 && resultArray.length >1) {
                    response = resultArray[0].trim();
                    if(!resultArray[1].equals(""))
                        orderId = resultArray[1].trim();
                    email = resultArray[2].trim();
                }

                i.putString("orderId", orderId);

                if(Constant.twentyOneYear!=null){
                    CallPreAuthCustomerCardNew(orderId);
                }

                //start to call ws GetpreAuthCustomerCard ******************

//            if (cardType.equals("New Card")) {
//
//                //Edited by janvi 1stJan2019
//
////                url = Constant.WS_BASE_URL + Constant.SAVE_PLACE_ORDER + Constant.STOREID + "/"
////                        + "0" + "/" + cvv + "/" + "0" + "/" + "0" + "/" + address + "/" + zip + "/" + Amount + "/"
////                        + merchantCustomerId + "/" + merchantContractId;
//                //end ********
//
//                url = Constant.WS_BASE_URL + Constant.SAVE_PLACE_ORDER + Constant.STOREID + "/"
//                        + cardNo + "/" + cvv + "/" + "0" + "/" + "0" + "/" + address.trim() + "/" + zip + "/" + Amount + "/"
//                        + merchantCustomerId + "/" + merchantContractId;
//
//            } else if (cardType.equals("Existing Card")) {
//                url = Constant.WS_BASE_URL + Constant.SAVE_PLACE_ORDER + Constant.STOREID + "/"
//                        + cardNo + "/" + cvv + "/" + expMonth + "/" + expYear + "/" + address + "/" + zip + "/" + Amount + "/" + "0" + "/" + "0";
//            }
//
//            TaskPayWare taskPayWare = new TaskPayWare(this,fourdigitcardnumber);
//            Log.d("existingCartUrl", "savePlaceOrder: URl : " + url);
//            //showDialog();
//            showPaymentProcessDialog(fourdigitcardnumber,Amount);
//            //janvi
//
//            Log.d("dialog ", "Showing dialog during competed transaction ");
//            taskPayWare.execute(url);

                //end ******************************************
            }
        } else {
//            Toast.makeText(getActivity(), "Fail : " + insertOrderDetailed.getResult(), Toast.LENGTH_SHORT).show();
        }


        /**
         * This functions call loyalty reward related web services
         * here... we are created all web services and implemented but not calling
         * because here those web services call not make sense
         * here... we are implemented because Boxsalt web DID
         * if need to call loyalty related web services just uncomment bellow method and everything working well...
         * **/
        //appliedLoyaltyReward();
    }

    private void CallPreAuthCustomerCardNew(String orderId) {

        String cardType = tvNewCard.getText().toString().trim();

        int cvvByPassval;
        String cvv;
        if(Constant.twentyOneYear.getCVVBypass() && cardType.equals("New Card")){
            cvvByPassval = 0;
            cvv = "0";
        }else{
            cvvByPassval = 1;
            cvv = etCvv.getText().toString().trim();
        }
//        Edited by janvi 9th jan ***********
        String cardNo, fourdigitcardnumber = "";

        if(etCardNumber.getText().toString().isEmpty()){
            cardNo = "0";
        }
        else{
            cardNo = etCardNumber.getText().toString().trim();
            fourdigitcardnumber = cardNo.substring(cardNo.length() - 4);
        }

        //***************************

        String strexp = etExpiration.getText().toString().trim();
        String[] arrexp = strexp.split("/");

        if (arrexp.length == 2) {
            expMonth = arrexp[0].trim();
            expYear = arrexp[1].trim();
        }

        String Amount = "0";
        String address = etAddressOne.getText().toString().trim();
        address = address.replaceAll(" ", "%20");
        String zip = etZip.getText().toString().trim().trim();
        Amount = "" + tvTotal.getText().toString().trim();

        Amount = Amount.replace("$", "");
        cardNo = cardNo.replace(" ", "");
        String MerchantCustID = etCvv.getText().toString().trim();
        String MerchantContractID = etCvv.getText().toString().trim();


        String address2 = etAddressTwo.getText().toString().trim();
        if(address2.isEmpty()){
            address2 = "null";
        }

        String city = etCity.getText().toString().trim();
        if(city.isEmpty()){
            city = "null";
        }

        String state = etState.getText().toString().trim();
        if(state.isEmpty()){
            state = "null";
        }


        _paymentOption = "2";
        if (cardType.equals("New Card")) {
            if (etCvv.getVisibility() == View.VISIBLE && etCvv.getText().toString().trim().isEmpty()) {
                etCvv.setError("Enter CVV.");
                etCvv.requestFocus();
                return;
            }
        } else {
            if (!cardValidate(expMonth)) {
                return;
            }
        }

        if (merchantCode.isEmpty() || merchantCode == null) {
            merchantCode = "0";
        }
        if (merchantCustomerId.isEmpty() || merchantCustomerId == null) {
            merchantCustomerId = "0";
        }
        if (merchantContractId.isEmpty() || merchantContractId == null) {
            merchantContractId = "0";
        }

        String url = "";
        //start to call ws GetpreAuthCustomerCardNew******************

        if(Constant.isUSAePAY){

//            Edited by Varun FOR NEW API WITH TOKEN ID FOR MERCHANT CONCTRACT ID

            Log.e(TAG, "merchantContractId: " +merchantContractId );

            if (etCardNumber.isFocusable()){
                if (!merchantContractId.isEmpty() || merchantContractId != null) {
                    merchantContractId = "2";
                }
            }else{
                if (!merchantContractId.isEmpty() || merchantContractId != null) {
                    merchantContractId = "0";
                }
            }
//          END


            //for USAePAY

            if (cardType.equals("New Card")) {

                url = Constant.WS_BASE_URL + Constant.WS_USAPAY_PAYMENT_TRANSACTION_DETAILS + Constant.STOREID + "/"
                        + cardNo + "/" + cvv + "/" + "0" + "/" + "0" + "/" + address.trim() + "/" + zip + "/" + Amount + "/"
                        + merchantCustomerId + "/" + merchantContractId + "/" + orderId + "/" + cvvByPassval + "/"
                        + UserModel.Cust_mst_ID + "/" + address2 + "/" + city + "/" + state;

            } else if (cardType.equals("Existing Card")) {
                url = Constant.WS_BASE_URL + Constant.WS_USAPAY_PAYMENT_TRANSACTION_DETAILS + Constant.STOREID + "/"
                        + cardNo + "/" + cvv + "/" + expMonth + "/" + expYear + "/" + address + "/" + zip + "/" + Amount + "/"
                        + "0" + "/" + merchantContractId + "/" + orderId + "/" + cvvByPassval + "/"
                        + UserModel.Cust_mst_ID + "/" + address2 + "/" + city + "/" + state;
            }

            TaskPayWare taskPayWare = new TaskPayWare(getActivity(), this, fourdigitcardnumber);
            Log.d("existingCartUrl", "savePlaceOrder: URl : " + url);

            showPaymentProcessDialog(fourdigitcardnumber, Amount);
            Log.d("dialog ", "Showing dialog during competed transaction ");
            taskPayWare.execute(url);

        }else {

            if (cardType.equals("New Card")) {

                //Edited by janvi 1stJan2019

//                url = Constant.WS_BASE_URL + Constant.SAVE_PLACE_ORDER + Constant.STOREID + "/"
//                        + "0" + "/" + cvv + "/" + "0" + "/" + "0" + "/" + address + "/" + zip + "/" + Amount + "/"
//                        + merchantCustomerId + "/" + merchantContractId;
                //end ********

                url = Constant.WS_BASE_URL + Constant.WS_PAYWARE_PAYMENT_TRANSACTION_DETAILS + Constant.STOREID + "/"
                        + cardNo + "/" + cvv + "/" + "0" + "/" + "0" + "/" + address.trim() + "/" + zip + "/" + Amount + "/"
                        + merchantCustomerId + "/" + merchantContractId + "/" + orderId + "/" + cvvByPassval + "/"
                        + UserModel.Cust_mst_ID + "/" + address2 + "/" + city + "/" + state;

            } else if (cardType.equals("Existing Card")) {
                url = Constant.WS_BASE_URL + Constant.WS_PAYWARE_PAYMENT_TRANSACTION_DETAILS + Constant.STOREID + "/"
                        + cardNo + "/" + cvv + "/" + expMonth + "/" + expYear + "/" + address + "/" + zip + "/" + Amount + "/"
                        + "0" + "/" + "0" + "/" + orderId + "/" + cvvByPassval + "/"
                        + UserModel.Cust_mst_ID + "/" + address2 + "/" + city + "/" + state;
            }

            TaskPayWare taskPayWare = new TaskPayWare(getActivity(), this, fourdigitcardnumber);
            Log.d("existingCartUrl", "savePlaceOrder: URl :2 " + url);
            //showDialog();
            showPaymentProcessDialog(fourdigitcardnumber, Amount);
            //janvi

            Log.d("dialog ", "Showing dialog during competed transaction ");
            taskPayWare.execute(url);
        }
    }


    @Override
    public void onPaywareResponseHandle(List<PayWareModel> payWareModel,String fourdigitCardnumFromEdttxt) {

        fourDigitCardNoValue = "";
        String fourDigitCardNumber = "";
        fourDigitCardNumber = fourdigitCardnumFromEdttxt;

//        if(!_tempCardNumber.isEmpty() || !_tempCardNumber.equals("")) {
//
//            if(_tempCardNumber.length()>4) {
//
//                if (!_tempCardNumber.substring(_tempCardNumber.length() - 4).equals("")) {
//                    fourDigitCardNumber = _tempCardNumber.substring(_tempCardNumber.length() - 4);
//                }
//            }
//        }else{
//            if(!fourdigitCardnumFromEdttxt.equals("") || !fourdigitCardnumFromEdttxt.isEmpty()){
//                fourDigitCardNumber = fourdigitCardnumFromEdttxt;
//            }
//        }

        fourDigitCardNoValue = fourDigitCardNumber;
        Log.e("cardnumber_CardNoValue",":" +fourDigitCardNoValue);
        Log.e("cardnumber_CardNumber",":" +fourDigitCardNumber);

        if (payWareModel != null) {

            if(Constant.isUSAePAY){

                for (int i = 0; i < payWareModel.size(); i++) {
                    if (payWareModel.get(i).getRESULTCODE().equals("A")) {

                        _orderId = String.valueOf(payWareModel.get(i).getINVOICE());
                        _ctroutd = String.valueOf(payWareModel.get(i).getCTROUTD());
                        _troutd = String.valueOf(payWareModel.get(i).getTROUTD());
                        _paymentMedia = String.valueOf(payWareModel.get(i).getPAYMENTMEDIA());
                        _authCode = String.valueOf(payWareModel.get(i).getAUTHCODE());
                        if (rbPayAtStore && rbPickUpAtStore)
                            _paymentOption = "1";
                        else
                            _paymentOption = "2";

                        Log.d(TAG, "Card Type : " + tvNewCard.getText().toString());
                        Log.d(TAG, "Card Checkbox  : " + isSaveCard);

                        if (isSaveCard && tvNewCard.getText().toString().equals("Existing Card")) {

                            callSaveCard("0", "0", "0", etAddressOne.getText().toString().trim(), etCity.getText().toString().trim(),
                                    etState.getText().toString().trim(), etZip.getText().toString().trim(), "0", "0"
                                    , etCardNumber.getText().toString().replace(" ", "").trim(), expMonth, expYear);

                        }

                        updateBillingAddress();

                    } else {
                        //cvv

                        if (payWareModel.get(i).getCVV2CODE() != null && payWareModel.get(i).getCVV2CODE().equals('N') && payWareModel.get(i).getAVSCODE() != null && payWareModel.get(i).getAVSCODE().equals('N')) {

                            hidePaymentProcessDialog();
                            etCvv.setError("Invalid CVV Code.");
//
                        }else if (payWareModel.get(i).getCVV2CODE() != null && payWareModel.get(i).getCVV2CODE().equals('N')) {
                            hidePaymentProcessDialog();
                            etCvv.setError("Declined - CVV Failed");

                        } else if (payWareModel.get(i).getCVV2CODE() != null && payWareModel.get(i).getCVV2CODE().equals('P')) {

                            hidePaymentProcessDialog();
                            etCvv.setError("Not processed");

                        } else if (payWareModel.get(i).getCVV2CODE() != null && payWareModel.get(i).getCVV2CODE().equals('S')) {

                            hidePaymentProcessDialog();
                            etCvv.setError("Declined - CVV Failed");

                        } else if (payWareModel.get(i).getAVSCODE() != null && payWareModel.get(i).getAVSCODE().equals("NYZ")) {
                            hidePaymentProcessDialog();
                            etCvv.setError("Declined - Address not verified");

                        }  else if (payWareModel.get(i).getAVSCODE() != null && payWareModel.get(i).getAVSCODE().equals("YNA")) {
                            hidePaymentProcessDialog();
                            etCvv.setError("Declined - Address format error");
                        }
                        else if (payWareModel.get(i).getAVSCODE() != null && payWareModel.get(i).getAVSCODE().equals("NNN")) {
                            hidePaymentProcessDialog();
                            etCvv.setError("Declined - Invalid");
                        }
                        else if (payWareModel.get(i).getAVSCODE() != null && payWareModel.get(i).getAVSCODE().equals("XXU")) {
                            hidePaymentProcessDialog();
                            etCvv.setError("Address unavailable");
                        }
                        else {
                            hidePaymentProcessDialog();
                            Utils.showCardValidationDialog(getActivity(), getResources().getString(R.string.str_Declined),fourDigitCardNumber,_authCode);
                        }

                    }
                }

            }else{
                for (int i = 0; i < payWareModel.size(); i++) {
                    if (payWareModel.get(i).getRESULTCODE().equals("4") || payWareModel.get(i).getRESULTCODE().equals("5")) {

                        _orderId = String.valueOf(payWareModel.get(i).getINVOICE());
                        _ctroutd = String.valueOf(payWareModel.get(i).getCTROUTD());
                        _troutd = String.valueOf(payWareModel.get(i).getTROUTD());
                        _paymentMedia = String.valueOf(payWareModel.get(i).getPAYMENTMEDIA());
                        _authCode = String.valueOf(payWareModel.get(i).getAUTHCODE());
                        if (rbPayAtStore && rbPickUpAtStore)
                            _paymentOption = "1";
                        else
                            _paymentOption = "2";

//                    callInsertOrderDetailed(_orderId, _ctroutd, _troutd, _paymentMedia, _authCode);

                        Log.d(TAG, "Card Type : " + tvNewCard.getText().toString());
                        Log.d(TAG, "Card Checkbox  : " + isSaveCard);

                        if (isSaveCard && tvNewCard.getText().toString().equals("Existing Card")) {

                            callSaveCard("0", "0", "0", etAddressOne.getText().toString().trim(), etCity.getText().toString().trim(),
                                    etState.getText().toString().trim(), etZip.getText().toString().trim(), "0", "0"
                                    , etCardNumber.getText().toString().replace(" ", "").trim(), expMonth, expYear);

                        }

                        updateBillingAddress();

                        //Utils.showValidationDialog(getActivity(), payWareModel.get(i).getRequestParameter() + "\n\n" + payWareModel.get(i).getResponseParameter());
//                    if(!_tempCardNumber.equals("") && _tempCardNumber.length() > 4){

//                    Utils.showCardValidationDialog(getActivity(), "APPROVED",fourDigitCardNumber,_authCode);

//                    Utils.showCardValidationDialog(getActivity(), "APPROVED" /*payWareModel.get(i).getRESPONSETEXT()*/);

                    } else {
                        //cvv

                        if (payWareModel.get(i).getCVV2CODE() != null && payWareModel.get(i).getCVV2CODE().equals('N') && payWareModel.get(i).getAVSCODE() != null && payWareModel.get(i).getAVSCODE().equals('N')) {
//                        ("Invalid CVV Code." + '\n' + "Address doesn't match billing address." + '\n' + "ZIP doesn't match billing address.");

                            hidePaymentProcessDialog();
                            etCvv.setError("Invalid CVV Code.");
//                        DialogUtils.onCommonDialog(getActivity(),"","Invalid CVV Code.");
//
                        }else if (payWareModel.get(i).getCVV2CODE() != null && payWareModel.get(i).getCVV2CODE().equals('N')) {
                            hidePaymentProcessDialog();
                            etCvv.setError("CVV not Matched");

                        } else if (payWareModel.get(i).getCVV2CODE() != null && payWareModel.get(i).getCVV2CODE().equals('P')) {

                            hidePaymentProcessDialog();
                            etCvv.setError("Not processed");

                        } else if (payWareModel.get(i).getCVV2CODE() != null && payWareModel.get(i).getCVV2CODE().equals('S')) {

                            hidePaymentProcessDialog();
                            etCvv.setError("Merchant indicated that CVV2 was not present on card");

                        } else if (payWareModel.get(i).getCVV2CODE() != null && payWareModel.get(i).getCVV2CODE().equals('I')) {
                            hidePaymentProcessDialog();
                            etCvv.setError("Int’l – address not verified (Visa)");

                        } else if (payWareModel.get(i).getCVV2CODE() != null && payWareModel.get(i).getCVV2CODE().equals('P')) {
                            hidePaymentProcessDialog();
                            etCvv.setError("Int’l – postal match, address format error");

                        } else if (payWareModel.get(i).getAVSCODE() != null && payWareModel.get(i).getAVSCODE().equals('N')) {
                            hidePaymentProcessDialog();
                            etCvv.setError("Address and/or ZIP don't match billing address.");
                        }
                        else if (payWareModel.get(i).getAVSCODE() != null && payWareModel.get(i).getAVSCODE().equals('U')) {
                            hidePaymentProcessDialog();
                            etCvv.setError("Address unavailable");
                        }
                        else {
                            hidePaymentProcessDialog();
                            Utils.showCardValidationDialog(getActivity(), getResources().getString(R.string.str_Declined),fourDigitCardNumber,_authCode);
                        }

//                    hidePaymentProcessDialog();
//                    //hideDialog();
//                    //Toast.makeText(getActivity(), "Result : " + payWareModel.get(i).getRESPONSETEXT(), Toast.LENGTH_SHORT).show();
//                    //Utils.showValidationDialog(getActivity(), payWareModel.get(i).getRequestParameter() + "\n\n" + payWareModel.get(i).getResponseParameter());
//
//                    //Edited by Janvi 31st dec ************
////                    Utils.showValidationDialog(getActivity(), payWareModel.get(i).getRESPONSETEXT());
//
//                    Utils.showCardValidationDialog(getActivity(), getResources().getString(R.string.str_Declined),fourDigitCardNumber,_authCode);
//                    //************
                    }
                }

            }

        }else{
            hidePaymentProcessDialog();

            Utils.showCardValidationDialog(getActivity(), getResources().getString(R.string.str_Declined),fourDigitCardNumber,_authCode);

        }
    }


    String id = "0";
    String firstName, lastName, companyName, addressOne, addressTwo, city, state, zip, phoneNo, phoneType;


    /**
     * Update Billing address Web service call
     **/
    public void updateBillingAddress() {

        firstName = (liShippingData.getFirstName().trim().isEmpty()) ? "0" : liShippingData.getFirstName().trim();
        lastName = (liShippingData.getLastName().trim().isEmpty()) ? "0" : liShippingData.getLastName().trim();
        companyName = (liShippingData.getCompanyName().trim().isEmpty()) ? "0" : liShippingData.getCompanyName().trim();
        addressOne = (etAddressOne.getText().toString().isEmpty()) ? "0" : etAddressOne.getText().toString();
        addressTwo = (etAddressTwo.getText().toString().isEmpty()) ? "0" : etAddressTwo.getText().toString();
        city = (etCity.getText().toString().isEmpty()) ? "0" : etCity.getText().toString();
        state = (etState.getText().toString().isEmpty()) ? "0" : etState.getText().toString();
        zip = (etZip.getText().toString().isEmpty()) ? "0" : etZip.getText().toString();
        phoneNo = (liShippingData.getPhone().trim().isEmpty()) ? "0" : liShippingData.getPhone().trim();
        phoneType = (liShippingData.getPhonetype().trim().isEmpty()) ? "0" : liShippingData.getPhonetype().trim();

        /** Update Billing address web service **/
        String billingAddressUrl = "";
        billingAddressUrl = Constant.WS_BASE_URL + Constant.UPDATE_BILLING_ADDRESS + UserModel.Cust_mst_ID + "/"
                + firstName + "/" + lastName + "/" + companyName + "/" + addressOne + "/" + addressTwo + "/"
                + city + "/" + state + "/" + zip + "/" + phoneNo + "/" + phoneType + "/" + Constant.STOREID;

        TaskUpdateBillingAddress billingAddress = new TaskUpdateBillingAddress(getActivity(),this);
        Log.d(TAG, "billing address call : " + billingAddressUrl);
        billingAddress.execute(billingAddressUrl);
    }


    /**
     * Result : Update Billing address result
     **/
    @Override
    public void onBillingAddressResult(ShippingData shippingData) {
        /** Move to next screen **/

        if(shippingData != null) {
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
            posBillingAddress.execute(shippingAddress);
        }else{
            hidePaymentProcessDialog();
        }

        /*if (myDeliveryOptionsEvent != null)
            myDeliveryOptionsEvent.nextFromDeliveryOption(addDataIntoBundle());*/
    }


    @Override
    public void onPOSBillingAddressResult(UpdatePOSBillingAddress updatePOSBillingAddress) {

        if(rbPayAtStore && rbPickUpAtStore){
            if (myPaymentEvent != null) {
                myPaymentEvent.loadOrderSummaryFragment(i, "", "", "ReturnProcessing");
                Constant.check=true;
                //Hide dialog when order completed...
                //hideDialog();
            }

        }else if(!rbPayAtStore ){

            hidePaymentProcessDialog();

            Log.d("Address", "Result : " + updatePOSBillingAddress.getResult());
            if (updatePOSBillingAddress != null && !updatePOSBillingAddress.getResult().isEmpty() && updatePOSBillingAddress.getResult().equals("success")) {

                Utils.showCardValidationDialog(getActivity(), "APPROVED",fourDigitCardNoValue,_authCode);


// if (myPaymentEvent != null) {
//                myPaymentEvent.loadOrderSummaryFragment(i);
//                //Hide dialog when order completed...
//                //hideDialog();
//            }
            }
        }

    }



    /**
     * Call : this when loyalty reward was applied on transaction
     **/
    private void appliedLoyaltyReward() {
        if (cbxApplyRewardCash.isChecked()) {
            if (Constant.liCardModel != null) {
                if (Constant.liCardModel.get(0).getLoyaltyType().equals("Online")) {
                    getValueInfoSetting();
                } else if (Constant.liCardModel.get(0).getLoyaltyType().equals("Skoop")) {
                    getScoopSetting();
                }
            }
        }
    }


    /**
     * Call When loyalty type is online
     **/
    public void getValueInfoSetting() {
        String settingUrl;
        settingUrl = Constant.WS_BASE_URL + Constant.GET_VALUE_INFO_SETTING + Constant.STOREID;
        TaskGetValueInfoSetting setting = new TaskGetValueInfoSetting();
        setting.execute(settingUrl);
    }

    /**
     * Call when loyalty type is Skoop
     **/
    private void getScoopSetting() {
        String scoopUrl;
        scoopUrl = Constant.WS_BASE_URL + Constant.GET_SKOOP_SETTING + Constant.STOREID;
        TaskGetScoopSetting scoopSetting = new TaskGetScoopSetting();
        scoopSetting.execute(scoopUrl);
    }


    /**
     * CheckBox : Checked Change Listener (for hide/show Loyalty reward point count)
     **/
    @Override
    public void onCheckedChanged(CompoundButton compoundButton, boolean b) {

        //Float useDollar = 0.0f;

        if (cbxSaveCard.isChecked()){
            isSaveCard = true;
        }

        if (cbx_join_reward_payment_fragment.isChecked()){
            sign_up_customer_for_loyalty_reward_from_ecom();
        }

        DecimalFormat df = new DecimalFormat("####0.00");
        df.setMaximumFractionDigits(2);
        //df.setRoundingMode(RoundingMode.UP);
        float subtotalval = 0.0f;


        if (cbxApplyRewardCash.isChecked()) {
            vMyReward.setVisibility(View.VISIBLE);
            llMyReward.setVisibility(View.VISIBLE);
            //Float value = 0.0f, rewardPoint = 35.00f;
            //tvTitleMyReward.setText("My Rewards (" + String.valueOf(value) + "pts)");
            //tvMyReward.setText(String.valueOf("$" + rewardPoint));

            rewardDollar = (float) base;
            rewardAvailable = rewardDollar;

            if(isDeliveryFee && ll_Delivery_fee.isShown()){
                subtotalval = _subTotal + deliveryFeeSurchargeVal;

                if (rewardDollar > subtotalval) {
                    rewardDollar = rewardDollar - subtotalval;
                    subtotalval = 0.00f;
                } else {
                    subtotalval = (subtotalval - rewardDollar);
                    rewardDollar = 0.0f;
                }

//                Edited by  Varun for to add shipping and tip price when we chech the the check of Apply Reqard Cash Available
//                finalTotal = subtotalval + _salesTax + _wineTax + _flatTax + _miscTax + _bottleDeposit ;
                finalTotal = subtotalval + _salesTax + _wineTax + _flatTax + _miscTax + _bottleDeposit + _shipping + _tipValue;
//                END
                tvSubTotal.setText(String.valueOf("$" + df.format(subtotalval)));

            }else{
                if (rewardDollar > _subTotal) {
                    rewardDollar = rewardDollar - _subTotal;
                    _subTotal = 0.00f;
                } else {
                    _subTotal = (_subTotal - rewardDollar);
                    rewardDollar = 0.0f;
                }

//                Edited by  Varun for to add shipping and tip price when we chech the the check of Apply Reward Cash Available
//                finalTotal = subtotalval + _salesTax + _wineTax + _flatTax + _miscTax + _bottleDeposit ;
                finalTotal = _subTotal + _salesTax + _wineTax + _flatTax + _miscTax + _bottleDeposit + _shipping + _tipValue;
//                END
                tvSubTotal.setText(String.valueOf("$" + df.format(_subTotal)));
            }


            rewardDollarUse = rewardAvailable - rewardDollar;

//            finalTotal = _subTotal + _salesTax + _wineTax + _flatTax + _miscTax + _bottleDeposit;
//            tvSubTotal.setText(String.valueOf("$" + df.format(_subTotal)));

            tvTitleRewardCashAvailable.setText(String.valueOf(getString(R.string.lbl_reward_cash_available) + "$"/* + df.format(rewardDollar)*/));/*
            tvTitleRewardCashAvailable.setText(String.valueOf(getString(R.string.lbl_reward_cash_available) + "$" *//*+ base*//*));*/
            tvRewardCashAvailable.setText(String.valueOf(df.format(rewardDollar)));

            tvTotal.setText(String.valueOf("$" + df.format(finalTotal)));
            pointUsed = (float) Math.round((Float.parseFloat(l.getAwardPoint()) * rewardDollarUse) / Double.parseDouble(l.getAwardDollar()));
            if (l.getLoyaltyType().equals("Skoop")) {
                tvTitleMyReward.setText("My Rewards");
            } else {
                tvTitleMyReward.setText("My Rewards (" + Math.round(pointUsed) + "pts)");
            }
            //useDollar = rewardDollarUse;
            tvMyReward.setText(String.valueOf("$" + df.format(rewardDollarUse)));

            if (l.getPointBasis().equals("onTotal")) {
                loyaltyPoints = (float) Math.round(_subTotal);
            }
            tvLoyaltyReward.setText(String.valueOf(/*df.format(*/ Math.round(loyaltyPoints))/*)*/);

        } else {

            rewardDollar = 0.0f;
            rewardDollar = loyaltyPoints /*Float.valueOf(tvLoyaltyReward.getText().toString())*/;
            rewardDollar = rewardDollar + rewardDollarUse;

            if(isDeliveryFee && ll_Delivery_fee.isShown()){

                subtotalval = Float.valueOf((tvSubTotal.getText().toString().contains("$") ? tvSubTotal.getText().toString().replace("$", "") : tvSubTotal.getText().toString()));
                subtotalval = subtotalval + rewardDollarUse /*Float.valueOf(Utils.getOnlyDigits(tvMyReward.getText().toString()))*/;
//                Edited by Varun for when we uncheck the checkbox of Apply Reward Cash Available
//                finalTotal = subtotalval + _salesTax + _wineTax + _flatTax + _miscTax + _bottleDeposit ;
                finalTotal = subtotalval + _salesTax + _wineTax + _flatTax + _miscTax + _bottleDeposit + _shipping + _tipValue;
//                END
                tvLoyaltyReward.setText(String.valueOf( /*"$" + */Math.round(subtotalval)));
                tvSubTotal.setText(String.valueOf("$" + df.format(subtotalval)));

            }else{
                _subTotal = Float.valueOf((tvSubTotal.getText().toString().contains("$") ? tvSubTotal.getText().toString().replace("$", "") : tvSubTotal.getText().toString()));
                _subTotal = _subTotal + rewardDollarUse /*Float.valueOf(Utils.getOnlyDigits(tvMyReward.getText().toString()))*/;
//                Edited by Varun for when we uncheck the checkbox of Apply Reward Cash Available
//                finalTotal = subtotalval + _salesTax + _wineTax + _flatTax + _miscTax + _bottleDeposit ;
                finalTotal = _subTotal + _salesTax + _wineTax + _flatTax + _miscTax + _bottleDeposit + _shipping + _tipValue;
//                END
                tvLoyaltyReward.setText(String.valueOf( /*"$" + */Math.round(_subTotal)));
                tvSubTotal.setText(String.valueOf("$" + df.format(_subTotal)));
            }

            if (base > 0) {
                tvTitleRewardCashAvailable.setText(String.valueOf(getString(R.string.lbl_reward_cash_available) + "$" /*+ base*/));
                tvRewardCashAvailable.setText(String.valueOf(df.format(base)));
            }

            tvTotal.setText(String.valueOf("$" + df.format(finalTotal)));
            vMyReward.setVisibility(View.GONE);
            llMyReward.setVisibility(View.GONE);
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
        myPaymentEvent = (PaymentEvent) context;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        myPaymentEvent = (PaymentEvent) activity;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        myPaymentEvent = null;
    }


    //https://stackoverflow.com/questions/6270484/how-to-remove-all-listeners-added-with-addtextchangedlistener
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
            // your logic here

            if (etAddressOne.isFocused()){
                isDhopDown = true;
            }

            String Name = "" + s;
            Name = Name.replaceAll(" ", "%20");
            if (isDhopDown) {
                if(Constant.SCREEN_LAYOUT==1){
                    new Async_getAddress(MainActivity.getInstance(), Constant.MAP_API_URL + Constant.INPUT + Name + "&key=" + MainActivity.getInstance().getString(R.string.Place_API_key), 11).execute();
                }else if(Constant.SCREEN_LAYOUT==2) {
                    new Async_getAddress(MainActivityDup.getInstance(), Constant.MAP_API_URL + Constant.INPUT + Name + "&key=" + MainActivityDup.getInstance().getString(R.string.Place_API_key), 11).execute();
                }

            }
            //else {
            //    isdhowdropdown = true;
            //}
        }
    };


    public static View progress_view;
    public static Dialog progress;
    private void showDialog(){
        //AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.getInstance());
        if(Constant.SCREEN_LAYOUT==1){
            progress = new Dialog(MainActivity.getInstance(), R.style.DialogSlideAnim_login);
            progress.setCanceledOnTouchOutside(false);
            progress_view = LayoutInflater.from(MainActivity.getInstance()).inflate(R.layout.progress_dialog, null);

        }else if(Constant.SCREEN_LAYOUT==2) {
            progress = new Dialog(MainActivityDup.getInstance(), R.style.DialogSlideAnim_login);
            progress.setCanceledOnTouchOutside(false);
            progress_view = LayoutInflater.from(MainActivityDup.getInstance()).inflate(R.layout.progress_dialog, null);

        }

        //View view = getLayoutInflater().inflate(R.layout.progress);
        // builder.setView(R.layout.progress_dialog);
        //Dialog dialog = builder.create();

        WindowManager.LayoutParams params = progress.getWindow().getAttributes();
        params.width = WindowManager.LayoutParams.WRAP_CONTENT;
        params.height = WindowManager.LayoutParams.WRAP_CONTENT;

        progress.setContentView(progress_view);
        progress.getWindow().setGravity(Gravity.CENTER);
        WindowManager.LayoutParams layoutParam = progress.getWindow().getAttributes();
        progress.getWindow().setAttributes(layoutParam);
        progress.show();

        /*if (show){
            dialog.show();
            MainActivity.getInstance().getWindow().setFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE,
                    WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
        } else{
            dialog.dismiss();
            MainActivity.getInstance().getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);

        }*/
    }

    private void hideDialog(){
        if (progress.isShowing())
            progress.dismiss();
    }

    public static View vPaywareResponce;
    public static Dialog paymentResponce;
    public  void showCardResponseDialog(){
        Log.e("Log","SET BLANK");
        cbxSaveCard.setClickable(true);
        llHideCheckBox.setVisibility(View.GONE);
        BSTheme.setCheckBoxColor(cbxSaveCard, Color.parseColor(Constant.themeModel.ThemeColor), Color.GRAY);
        //cbxSaveCard.setChecked(false);

        GradientDrawable old = new GradientDrawable();
        old.setColor(R.drawable.border_edittext);

        etCardNumber.setText("");
        etCvv.setText("");
        etExpiration.setText("");
        //cbxSaveCard.setClickable(false);

        etExpiration.setFocusable(true);
        etExpiration.setFocusableInTouchMode(true);
        etExpiration.setClickable(true);
        etExpiration.setBackgroundResource(R.drawable.border_edittext);
        etExpiration.setEnabled(true);
        etExpiration.setHint("mm/yy");

        etCardNumber.setFocusable(true);
        etCardNumber.setFocusableInTouchMode(true);
        etCardNumber.setClickable(true);
        etCardNumber.setBackgroundResource(R.drawable.border_edittext);
        etCardNumber.setEnabled(true);
        etCardNumber.requestFocus();


        etCardHolderName.setFocusable(true);
        etCardHolderName.setFocusableInTouchMode(true);
        etCardHolderName.setClickable(true);
        etCardHolderName.setEnabled(true);
        etCardHolderName.setBackgroundResource(R.drawable.border_edittext);
        if(isAdded())
            tvNewCard.setText(Utils.underlineText(String.valueOf(getString(R.string.lbl_existing_card))));
//         Edited by Varun for guest login
        if (Constant.ISguest){
            rlRootSaveCard.setVisibility(View.GONE);
        }else {
            rlRootSaveCard.setVisibility(View.VISIBLE);
        }
//          END
        cbxSaveCard.setClickable(true);
        llHideCheckBox.setVisibility(View.GONE);
        BSTheme.setCheckBoxColor(cbxSaveCard, Color.parseColor(Constant.themeModel.ThemeColor), Color.GRAY);

        //paymentResponce.dismiss();
       /* PaymentFragment.getInstance().customerCard.cancel(true);

        paymentResponce = new Dialog(MainActivity.getInstance(), R.style.DialogSlideAnim_login);
        paymentResponce.setCanceledOnTouchOutside(false);
        vPaywareResponce = LayoutInflater.from(MainActivity.getInstance()).inflate(R.layout.common_dialog, null);

        Button btn_ok = (Button) vPaywareResponce.findViewById(R.id.btn_ok_dialog_payment_not_responding);
        GradientDrawable bgShape = (GradientDrawable) btn_ok.getBackground();
        bgShape.setColor(Color.parseColor(Constant.themeModel.ThemeColor));
        btn_ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cbxSaveCard.setClickable(true);
                llHideCheckBox.setVisibility(View.GONE);
                BSTheme.setCheckBoxColor(cbxSaveCard, Color.parseColor(Constant.themeModel.ThemeColor), Color.GRAY);

                paymentResponce.dismiss();
            }
        });
        WindowManager.LayoutParams params = paymentResponce.getWindow().getAttributes();
        params.width = WindowManager.LayoutParams.WRAP_CONTENT;
        params.height = WindowManager.LayoutParams.WRAP_CONTENT;

        paymentResponce.setContentView(vPaywareResponce);
        paymentResponce.getWindow().setGravity(Gravity.CENTER);
        WindowManager.LayoutParams layoutParam = paymentResponce.getWindow().getAttributes();
        paymentResponce.getWindow().setAttributes(layoutParam);
        paymentResponce.show();*/
    }


    public static View vPaymentProcessDialog;
    public static Dialog paymentProcess;
    private void showPaymentProcessDialog(String fourdigitcardnumber, String amount){
        if(Constant.SCREEN_LAYOUT==1){
            paymentProcess = new Dialog(MainActivity.getInstance()/*, R.style.DialogSlideAnim_login*/);
            paymentProcess.setCanceledOnTouchOutside(false);
//            vPaymentProcessDialog = LayoutInflater.from(MainActivity.getInstance()).inflate(R.layout.dialog_payment_process, null);
            vPaymentProcessDialog = LayoutInflater.from(MainActivity.getInstance()).inflate(R.layout.dialog_payment_process_new, null);

        }else if(Constant.SCREEN_LAYOUT==2) {
            paymentProcess = new Dialog(MainActivityDup.getInstance()/*, R.style.DialogSlideAnim_login*/);
            paymentProcess.setCanceledOnTouchOutside(false);
//            vPaymentProcessDialog = LayoutInflater.from(MainActivityDup.getInstance()).inflate(R.layout.dialog_payment_process, null);
            vPaymentProcessDialog = LayoutInflater.from(MainActivityDup.getInstance()).inflate(R.layout.dialog_payment_process_new, null);
        }

        TextView txtCardEnding = (TextView) vPaymentProcessDialog.findViewById(R.id.tv_cardnumber);
        txtCardEnding.setText(fourdigitcardnumber);

        TextView txtCardnumber = (TextView) vPaymentProcessDialog.findViewById(R.id.tv_Amount);
        txtCardnumber.setText("$"+amount);

        WindowManager.LayoutParams params = Objects.requireNonNull(paymentProcess.getWindow()).getAttributes();
        params.width = WindowManager.LayoutParams.WRAP_CONTENT;
        params.height = WindowManager.LayoutParams.WRAP_CONTENT;

        paymentProcess.setContentView(vPaymentProcessDialog);
        paymentProcess.getWindow().setGravity(Gravity.CENTER);
        WindowManager.LayoutParams layoutParam = paymentProcess.getWindow().getAttributes();
        paymentProcess.getWindow().setAttributes(layoutParam);
        paymentProcess.setCanceledOnTouchOutside(false);
        paymentProcess.setCancelable(false);
        paymentProcess.show();
    }

    private void hidePaymentProcessDialog(){
        if (paymentProcess.isShowing())
            paymentProcess.dismiss();
    }
}
