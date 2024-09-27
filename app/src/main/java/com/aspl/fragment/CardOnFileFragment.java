package com.aspl.fragment;


import android.app.Dialog;
import android.graphics.Color;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.core.widget.NestedScrollView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.aspl.Utils.Constant;
import com.aspl.Utils.DialogUtils;
import com.aspl.Utils.Utils;
import com.aspl.mbs.MainActivity;
import com.aspl.mbs.MainActivityDup;
import com.aspl.mbs.R;
import com.aspl.mbsmodel.ActiveStatusModel;
import com.aspl.mbsmodel.CreditCartSetting;
import com.aspl.mbsmodel.CustomerCard;
import com.aspl.mbsmodel.PayWareModel;
import com.aspl.mbsmodel.UserModel;
import com.aspl.task.TaskCheckUSAePAYStatus;
import com.aspl.task.TaskCreditCard;
import com.aspl.task.TaskCustomerCard;
import com.aspl.task.TaskGetCreditCardSetting;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class CardOnFileFragment extends Fragment implements TaskCustomerCard.TaskCustomerCardEvent, TaskCreditCard.TaskCreditCardEvent,
        TaskGetCreditCardSetting.TaskGetCreditCardSettingEvent , TaskCheckUSAePAYStatus.TaskCheckUSAePAYStatusEvent {

    public static final String TAG = "CardOnFileFragment";
    TextView tvCustomerId, tvCardType, tvCardNo, tv_cardOnfile_title, tv_noCard_title;
    ImageView iv_card;
    Button btn_Edit, btn_Delete;
    public static CardOnFileFragment cardOnFileFragment;
    NestedScrollView llcardOnFile;
    LinearLayout llCardLayout;
    String cardno;
    Dialog editCardDialog = null;
    String merchantCode = "0", merchantCustomerId = "0", merchantContractId = "0";

    Boolean check = false;
//    static ArrayList<String> listOfPattern;

//    List<String> cards = new ArrayList<String>();
//    String regex = "^(?:(?<visa>4[0-9]{12}(?:[0-9]{3})?)|" +
//            "(?<mastercard>5[1-5][0-9]{14})|" +
//            "(?<discover>6(?:011|5[0-9]{2})[0-9]{12})|" +
//            "(?<amex>3[47][0-9]{13})|" +
//            "(?<diners>3(?:0[0-5]|[68][0-9])?[0-9]{11})|" +
//            "(?<jcb>(?:2131|1800|35[0-9]{3})[0-9]{11}))$";
//
//    Pattern pattern = Pattern.compile(regex);

    public static CardOnFileFragment getInstance() {
        return cardOnFileFragment;
    }

    public CardOnFileFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        cardOnFileFragment = this;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_card_on_file, container, false);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        tvCustomerId = (TextView) view.findViewById(R.id.tvCustomerId);
        tvCardType = (TextView) view.findViewById(R.id.tvCardType);
        tvCardNo = (TextView) view.findViewById(R.id.tvCardNo);
        tvCardNo.setFocusable(false);
        btn_Edit = (Button) view.findViewById(R.id.btn_Edit);
        btn_Delete = (Button) view.findViewById(R.id.btn_Delete);
        iv_card = (ImageView) view.findViewById(R.id.iv_card);
        llcardOnFile = (NestedScrollView) view.findViewById(R.id.llcardOnFile);
        llcardOnFile.setBackgroundColor(Color.parseColor(Constant.themeModel.Backgroundcolor));
        tv_cardOnfile_title = (TextView) view.findViewById(R.id.tv_cardOnfile_title);
        tv_cardOnfile_title.setTextColor(Color.parseColor(Constant.themeModel.ThemeColor));
        tv_noCard_title = (TextView) view.findViewById(R.id.tv_noCard_title);
        llCardLayout = (LinearLayout) view.findViewById(R.id.llCardLayout);

//        checkUSAePAYactivestatus();

        if(Constant.isUSAePAY){
            getCreditCardSetting();
        }else{
            getCustomerCreditCardDetails();
        }

        btn_Edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                DialogUtils.editCardDetailOnFile(cardno, getActivity());

//                DialogUtils.underDevelopmentDialog(getActivity());
            }
        });

        btn_Delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                DialogUtils.showConfirmation(getActivity(),getResources().getString(R.string.str_delete_confirmation));

//                callDeleteCreditcardWS();
//                DialogUtils.underDevelopmentDialog(getActivity());
            }
        });


    }

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

    public void getCreditCardSetting() {

        String creditCardUrl = "";
        if (UserModel.Cust_mst_ID != null) {
            creditCardUrl = Constant.WS_BASE_URL + Constant.GET_CREDIT_CARD_SETTING + Constant.STOREID + "/" + UserModel.Cust_mst_ID;
            TaskGetCreditCardSetting cardSetting = new TaskGetCreditCardSetting(this);
            Log.d(TAG, "getCreditCardSetting: " + creditCardUrl);
            cardSetting.execute(creditCardUrl);
        }
    }


    @Override
    public void creditCartSettingResult(CreditCartSetting creditCartSetting) {

        Log.d(TAG, "creditCartSettingResult: " + creditCartSetting);
        merchantCode = creditCartSetting.getMerchantCode();
        merchantCustomerId = creditCartSetting.getMerchantCustID();
        merchantContractId = creditCartSetting.getMerchantContractID();

        // for USAPAy
        if(Constant.isUSAePAY){

            String cardRequestUrl = "";

            tvCustomerId.setText("Customer ID - " + UserModel.Cust_mst_ID);
//            cardRequestUrl = Constant.WS_BASE_URL + Constant.USA_PAY_GET_CARD_DETAIL + Constant.STOREID + "/" + UserModel.Cust_mst_ID + "/" + merchantContractId.trim();

//            EDITED BY VARUN FOR NEW API WITH TOKEN ID

            cardRequestUrl = Constant.WS_BASE_URL + Constant.GET_CUSTOMER_DATA_FROM_USAEPAY_VAULT + Constant.STOREID + "/" + UserModel.Cust_mst_ID + "/" + Constant.ENCODE_TOKEN_ID;

//            END

            TaskCustomerCard customerCard = new TaskCustomerCard(getActivity(), this, true);
            Log.d(TAG, "Credit card detail : " + cardRequestUrl);
            customerCard.execute(cardRequestUrl);
        }
    }


    @Override
    public void customerCardResult(List<CustomerCard> liCustomerCard) {

        if (liCustomerCard != null && liCustomerCard.size() > 0) {

            //USAePay
           if(Constant.isUSAePAY){

               if (liCustomerCard.get(0).getCardNumber() != null && !liCustomerCard.get(0).getCardNumber().trim().isEmpty()) {

                   llCardLayout.setVisibility(View.VISIBLE);
                   tv_noCard_title.setVisibility(View.GONE);

                   cardno = liCustomerCard.get(0).getCardNumber().trim();

                   String cardType = "";
                   if(liCustomerCard.get(0).getCardType() != null && !liCustomerCard.get(0).getCardType().trim().isEmpty() && !liCustomerCard.get(0).getCardType().trim().equalsIgnoreCase("null")){
                       cardType = liCustomerCard.get(0).getCardType().trim();
                   }else{
                       cardType = getCardType(cardno);
                   }

                   Log.d("cardType", "" + cardType);

//                    Edited by Varun for space between cardnumber

                   StringBuilder s;
                   s = new StringBuilder(cardno);

                   for(int i = 4; i < s.length(); i += 5){
                       s.insert(i, " ");
                   }
                   tvCardNo.setText(s.toString());

//                    END

//                   tvCardNo.setText(cardno);
//                   tvCardType.setText(cardType.toUpperCase());

//                   Edited By Varun for Show card name
                   if (cardType.equalsIgnoreCase("M") || cardType.equalsIgnoreCase("Master")){

                       tvCardType.setText("Master Card");
                   }
                   else if (cardType.equalsIgnoreCase("V") || cardType.equalsIgnoreCase("Visa")){

                       tvCardType.setText("Visa");

                   }else if (cardType.equalsIgnoreCase("A") || cardType.equalsIgnoreCase("AmEx")){

                       tvCardType.setText("American Express");

                   }else if (cardType.equalsIgnoreCase("DN") || cardType.equalsIgnoreCase("Diners")){

                       tvCardType.setText("Diners Club");

                   }else if (cardType.equalsIgnoreCase("DS") || cardType.equalsIgnoreCase("Discover")){

                       tvCardType.setText("Discover");

                   }else if (cardType.equalsIgnoreCase("J") || cardType.equalsIgnoreCase("JCB")){

                       tvCardType.setText("JCB");
                   }
//                      END

               }

           }else {

               if (liCustomerCard.get(0).getRESPONSETEXT() != null && !liCustomerCard.get(0).getRESPONSETEXT().isEmpty()) {

                   llCardLayout.setVisibility(View.VISIBLE);
                   tv_noCard_title.setVisibility(View.GONE);

                   cardno = liCustomerCard.get(0).getRESPONSETEXT();
                   String cardType = getCardType(cardno);
                   Log.d("cardType", "" + cardType);

                   tvCardNo.setText(cardno);
//                   tvCardType.setText(cardType.toUpperCase());

//                   Edited By Varun for Show card name
                   if (cardType.equalsIgnoreCase("M") || cardType.equalsIgnoreCase("Master")){

                       tvCardType.setText("Master Card");
                   }
                   else if (cardType.equalsIgnoreCase("V") || cardType.equalsIgnoreCase("Visa")){

                       tvCardType.setText("Visa");

                   }else if (cardType.equalsIgnoreCase("A") || cardType.equalsIgnoreCase("AmEx")){

                       tvCardType.setText("American Express");

                   }else if (cardType.equalsIgnoreCase("DN") || cardType.equalsIgnoreCase("Diners")){

                       tvCardType.setText("Diners Club");

                   }else if (cardType.equalsIgnoreCase("DS") || cardType.equalsIgnoreCase("Discover")){

                       tvCardType.setText("Discover");

                   }else if (cardType.equalsIgnoreCase("J") || cardType.equalsIgnoreCase("JCB")){

                       tvCardType.setText("JCB");
                   }
//                      END


               }
           }

//            else{
//                llCardLayout.setVisibility(View.GONE);
//                tv_noCard_title.setVisibility(View.VISIBLE);
//            }

        }
//        else{
//            llCardLayout.setVisibility(View.GONE);
//            tv_noCard_title.setVisibility(View.VISIBLE);
//        }

        if(editCardDialog != null && editCardDialog.isShowing()){
            editCardDialog.dismiss();
        }
    }


    public void callDeleteCreditcardWS() {

        if (UserModel.Cust_mst_ID != null) {

            //for USAepay
            if(Constant.isUSAePAY){

//                String cardRequestUrl = Constant.WS_BASE_URL + Constant.GET_USAPAY_CANCEL_CUST_CREDITCARD + Constant.STOREID + "/" + UserModel.Cust_mst_ID;

//                EDITED BY VARUN FOR NEW API WITH TOKEN ID

                String cardRequestUrl = Constant.WS_BASE_URL + Constant.USAPAY_DELETE_CUST_CREDITCARD + Constant.STOREID + "/" + UserModel.Cust_mst_ID + "/" + Constant.ENCODE_TOKEN_ID;

//                END

                TaskCreditCard customerCard = new TaskCreditCard(getActivity(), this, "delete");
                Log.d(TAG, "Credit card detail : " + cardRequestUrl);
                customerCard.execute(cardRequestUrl);

            }else{
                String cardRequestUrl = Constant.WS_BASE_URL + Constant.GET_CANCEL_CUST_CREDITCARD + Constant.STOREID + "/" + UserModel.Cust_mst_ID;
                TaskCreditCard customerCard = new TaskCreditCard(getActivity(), this, "delete");
                Log.d(TAG, "Credit card detail : " + cardRequestUrl);
                customerCard.execute(cardRequestUrl);
            }
        }
    }

    @Override
    public void onResume() {
        super.onResume();

        if(Constant.SCREEN_LAYOUT == 2){
            MainActivityDup.getInstance().showbackButton();
            MainActivityDup.getInstance().RecHorizontalList.setVisibility(View.GONE);
        }

        if (Constant.SCREEN_LAYOUT == 1) {

            MainActivity.getInstance().llsearch.setVisibility(View.VISIBLE);

            if (Constant.isFromMic) {
                MainActivity.getInstance().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);
                MainActivity.getInstance().mSearchedt.requestFocus();
                MainActivity.getInstance().mSearchedt.setFocusableInTouchMode(true);
                Constant.isFromMic = false;
            } else {
                MainActivity.getInstance().mSearchedt.clearFocus();
                Utils.hideKeyboard();
            }

        } else if (Constant.SCREEN_LAYOUT == 2) {

            MainActivityDup.getInstance().showbackButton();

            MainActivityDup.getInstance().llsearch.setVisibility(View.GONE);

            if (Constant.isFromMicSeclayout) {
                MainActivityDup.getInstance().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);
                MainActivityDup.getInstance().mSearchedt.requestFocus();
                MainActivityDup.getInstance().mSearchedt.setFocusableInTouchMode(true);
                Constant.isFromMicSeclayout = false;
            } else {
                MainActivityDup.getInstance().mSearchedt.clearFocus();
                Utils.hideKeyboard();
            }
        }
        Utils.hideKeyboard();
    }

    public void getCustomerCreditCardDetails() {
        if (UserModel.Cust_mst_ID != null) {

            tvCustomerId.setText("Customer ID - " + UserModel.Cust_mst_ID);

                String cardRequestUrl = Constant.WS_BASE_URL + Constant.GET_CARD_DETAIL + Constant.STOREID + "/" + UserModel.Cust_mst_ID;
                TaskCustomerCard customerCard = new TaskCustomerCard(getActivity(), this, true);
                Log.d(TAG, "Credit card detail : " + cardRequestUrl);
                customerCard.execute(cardRequestUrl);
        }
    }

    private String getCardType(String cardno) {

        String cType;

        final String cardNumber = cardno;

        if (cardNumber.startsWith("4")) {
            cType = "Visa";
        } else if (cardNumber.startsWith("5")) {
            cType = "Master Card";
        } else if (cardNumber.startsWith("6")) {
            cType = "Discover";
        } else if (cardNumber.startsWith("37")) {
            cType = "American Express";
        } else {
            cType = "";
        }

        return cType;
    }

    public void showCardText() {
        llCardLayout.setVisibility(View.GONE);
        tv_noCard_title.setVisibility(View.VISIBLE);
    }

    public void callUpdateCreditCardWS(String cardno, String selectedMonth, String selectedYear, Dialog editCardDialog) {

        this.editCardDialog = editCardDialog;

        SimpleDateFormat inputFormat = new SimpleDateFormat("MMMM");
        Calendar cal = Calendar.getInstance();
        try {
            cal.setTime(inputFormat.parse(selectedMonth));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        SimpleDateFormat outputFormat = new SimpleDateFormat("MM");
        String twoDigitMonth = outputFormat.format(cal.getTime());

        String twoDigitYear = selectedYear.substring(2);

        if (UserModel.Cust_mst_ID != null) {

            if(Constant.isUSAePAY){

//                String cardRequestUrl = Constant.WS_BASE_URL + Constant.GET_USAPAY_UPDATE_CUSTOMER_CREDITCARDS + Constant.STOREID + "/" + UserModel.Cust_mst_ID
//                        + "/" + cardno + "/" + twoDigitMonth + "/" + twoDigitYear;

//                EDITED BY VARUN FOR NEW API WITH TOKEN ID


                String cardRequestUrl = Constant.WS_BASE_URL + Constant.UPADATE_CUSTMOER_IN_USAEPAY_VAULT + Constant.STOREID + "/" + UserModel.Cust_mst_ID
                        + "/" + cardno + "/" + twoDigitMonth + "/" + twoDigitYear +"/" + Constant.ENCODE_TOKEN_ID;

//                END

                TaskCreditCard customerCard = new TaskCreditCard(getActivity(), this, "");
                Log.d(TAG, "Credit card detail : " + cardRequestUrl);
                customerCard.execute(cardRequestUrl);

            }else{
                String cardRequestUrl = Constant.WS_BASE_URL + Constant.GET_UPDATE_CUSTOMER_CREDITCARDS + Constant.STOREID + "/" + UserModel.Cust_mst_ID
                        + "/" + cardno + "/" + twoDigitMonth + "/" + twoDigitYear;
                TaskCreditCard customerCard = new TaskCreditCard(getActivity(), this, "");
                Log.d(TAG, "Credit card detail : " + cardRequestUrl);
                customerCard.execute(cardRequestUrl);
            }
        }
    }


    @Override
    public void creditCardResult(List<PayWareModel> liCreditCard, String forAction) {

//        if(forAction.equals("delete")){
//            if (liCreditCard != null) {
//                if (!liCreditCard.get(0).getSuccessMessage().toString().equalsIgnoreCase("null") && !liCreditCard.get(0).getSuccessMessage().toString().isEmpty()) {
//                    if(liCreditCard.get(0).getSuccessMessage().toString().equalsIgnoreCase("SUCCESS")){
//
//                    }
//                }
//            }
//        }else{

            if (liCreditCard != null) {
                if (!liCreditCard.get(0).getSuccessMessage().toString().equalsIgnoreCase("null") && !liCreditCard.get(0).getSuccessMessage().toString().isEmpty()) {
                    if(liCreditCard.get(0).getSuccessMessage().toString().equalsIgnoreCase("SUCCESS") || (liCreditCard.get(0).getSuccessMessage().toString().equalsIgnoreCase("Card Saved Successfully")
                        || (liCreditCard.get(0).getSuccessMessage().toString().equalsIgnoreCase("Card Updated Successfully")))){
                        Utils.showValidationDialog(getContext(), "SUCCESS", "");
                    }
                }
            }

//        }
    }

}
