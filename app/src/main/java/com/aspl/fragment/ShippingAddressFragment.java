package com.aspl.fragment;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.Point;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.aspl.Adapter.ShippingAddressAdapter;
import com.aspl.Utils.Constant;
import com.aspl.Utils.Utils;
import com.aspl.mbs.MainActivity;
import com.aspl.mbs.MainActivityDup;
import com.aspl.mbs.R;
import com.aspl.mbsmodel.EditShippingData;
import com.aspl.mbsmodel.ShippingData;
import com.aspl.mbsmodel.UserModel;
import com.aspl.task.TaskShippingData;
import com.aspl.task.TaskUpdateBillingAddressPOS;
import com.aspl.task.TaskUpdateShippingAddress;
import com.google.android.gms.vision.text.TextRecognizer;

import java.util.List;
import java.util.Objects;

public class ShippingAddressFragment extends Fragment implements TaskShippingData.TaskShippingEvent, TaskUpdateShippingAddress.UpdateShippingAddressEvent, TaskUpdateBillingAddressPOS.UpdateBillingAddressPOSEvent {

    public static final String TAG = "shippingAddressFragment";
    public static ShippingAddressFragment shippingAddressFragment;
    RecyclerView rv_shippingAddList;
    LinearLayoutManager layoutManager;
    ShippingAddressAdapter shippingAddressAdapter;
    LinearLayout llmainShipLayout;
    TextView tv_norecord;

    public static ShippingAddressFragment getInstance(){
        return shippingAddressFragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        shippingAddressFragment = this;
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_shipping_address, container, false);
//        Bundle bundle = this.getArguments();
        tv_norecord = (TextView)view.findViewById(R.id.tv_norecord);

        onCallShippingDataTask();

        llmainShipLayout = (LinearLayout)view.findViewById(R.id.llmainShipLayout);
        llmainShipLayout.setBackgroundColor(Color.parseColor(Constant.themeModel.Backgroundcolor));
        rv_shippingAddList = (RecyclerView)view.findViewById(R.id.rv_shippingAddList);
        layoutManager = new LinearLayoutManager(getActivity());
        rv_shippingAddList.setLayoutManager(layoutManager);

        return view;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public void onCallShippingDataTask() {

        if(UserModel.Cust_mst_ID != null){
            String url = Constant.WS_BASE_URL + Constant.GET_SHIPPING_DATA + UserModel.Cust_mst_ID + "/" + Constant.STOREID;
            TaskShippingData taskShippingData = new TaskShippingData(this, true, getActivity());
            taskShippingData.execute(url);
        }else{
            String url = Constant.WS_BASE_URL + Constant.GET_SHIPPING_DATA + 0 + "/" + Constant.STOREID;
            TaskShippingData taskShippingData = new TaskShippingData(this,true,getActivity());
            taskShippingData.execute(url);
        }
    }

    @Override
    public void onShippingTaskResult(List<ShippingData> liShippingAddData) {
        if(liShippingAddData != null && liShippingAddData.size() > 0){

            if(liShippingAddData.size() == 1 && liShippingAddData.get(0).getFirstName().trim().isEmpty() &&
                    liShippingAddData.get(0).getLastName().trim().isEmpty() &&
                    liShippingAddData.get(0).getCompanyName().trim().isEmpty() &&
                    liShippingAddData.get(0).getPhonetype().trim().isEmpty() &&
                    liShippingAddData.get(0).getAddress1().trim().isEmpty() &&
                    liShippingAddData.get(0).getAddress2().trim().isEmpty()){

                tv_norecord.setVisibility(View.VISIBLE);
                rv_shippingAddList.setVisibility(View.GONE);

            }else{

                tv_norecord.setVisibility(View.GONE);
                rv_shippingAddList.setVisibility(View.VISIBLE);
                shippingAddressAdapter = new ShippingAddressAdapter(getActivity(), liShippingAddData);
                rv_shippingAddList.setAdapter(shippingAddressAdapter);
            }

        }else{

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

            MainActivityDup.getInstance().llsearch.setVisibility(View.GONE);

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
    }

    public void callUpdateShippingdata(ShippingData shippingData,String type, boolean IsDefault, String isFrom) {

        long shippingId = shippingData.getShippingId();
        String phonetype = shippingData.getPhonetype();

        if(phonetype == null || phonetype.trim().isEmpty()){
            phonetype = "null";
        }
        String url = Constant.WS_BASE_URL + Constant.UPDATE_SHIPPING_ADDRESS
                + UserModel.Cust_mst_ID + "/" + Constant.STOREID + "/" + 0 + "/" + 0 + "/" + 0 + "/" + 0 + "/" + 0 + "/" + 0 + "/" + 0 + "/" + 0 + "/"
                + 0 + "/" + type + "/" + IsDefault + "/" + shippingId + "/" + phonetype;

//        String url = Constant.WS_BASE_URL + Constant.UPDATE_SHIPPING_ADDRESS
//                + UserModel.Cust_mst_ID + "/" + Constant.STOREID + "/" + 0 + "/" + 0 + "/" + 0 + "/" + 0 + "/" + 0 + "/" + 0 + "/" + 0 + "/" + 0 + "/"
//                + 0 + "/" + "Default" + "/" + "True" + "/" + shippingId + "/" + type;

        TaskUpdateShippingAddress shippingAddress = new TaskUpdateShippingAddress(getActivity(),this,isFrom,shippingData,null);
        Log.d("Address", "Call Shipping address : " + url);
        shippingAddress.execute(url);

    }

    @Override
    public void onUpdateShippingResult(ShippingData shippingData, String isFrom, ShippingData shippingDataModelPrev, EditShippingData editShippingData) {
        if(isFrom.equalsIgnoreCase("setasDefault")){
            onCallShippingDataTask();
        }else if(isFrom.equalsIgnoreCase("delete")){
            if(shippingData!=null){
                if(shippingData.getResult()!=null && !shippingData.getResult().isEmpty() && !shippingData.getResult().equals("")){
                    if(shippingData.getResult().contains("success")){
                        String result = shippingData.getResult();
                        String[] arrayString = result.split("-");

                        String posid = arrayString[1];
                        callUpdateBillingAddressonPosdata(posid,shippingDataModelPrev);
                    }
                }
            }
        }

//        if(shippingAddressAdapter != null){
//            shippingAddressAdapter.notifyDataSetChanged();
//        }
    }

    private void callUpdateBillingAddressonPosdata(String posCustomerid, ShippingData shippingData) {

        if(posCustomerid.isEmpty() && posCustomerid.equals("")){
            posCustomerid = "0";
        }

        String fname = "",lname = "",companyname = "",addOne = "",addTwo = "",phone = "",phonetype = "",city ="",state = "",zip = "";

        if(shippingData.getFirstName()!= null && !shippingData.getFirstName().trim().isEmpty()){
            fname = shippingData.getFirstName();
        }else{
            fname = "null";
        }

        if(shippingData.getLastName()!= null && !shippingData.getLastName().trim().isEmpty()){
            lname = shippingData.getLastName();
        }else{
            lname = "null";
        }

        if(shippingData.getCompanyName()!= null && !shippingData.getCompanyName().trim().isEmpty()){
            companyname = shippingData.getCompanyName();
        }else{
            companyname = "null";
        }

        if(shippingData.getAddress1()!= null && !shippingData.getAddress1().trim().isEmpty()){
            addOne = shippingData.getAddress1();
        }else{
            addOne = "null";
        }

        if(shippingData.getAddress2()!= null && !shippingData.getAddress2().trim().isEmpty()){
            addTwo = shippingData.getAddress2();
        }else{
            addTwo = "null";
        }

        if(shippingData.getPhone()!= null && !shippingData.getPhone().trim().isEmpty()){
            phone = shippingData.getPhone();
        }else{
            phone = "null";
        }

        if(shippingData.getPhonetype()!= null && !shippingData.getPhonetype().trim().isEmpty()){
            phonetype = shippingData.getPhonetype();
        }else{
            phonetype = "null";
        }

        if(shippingData.getCity()!= null && !shippingData.getCity().trim().isEmpty()){
            city = shippingData.getCity();
        }else{
            city = "null";
        }

        if(shippingData.getState()!= null && !shippingData.getState().trim().isEmpty()){
            state = shippingData.getState();
        }else{
            state = "null";
        }

        if(shippingData.getZip()!= null && !shippingData.getZip().trim().isEmpty()){
            zip = shippingData.getZip();
        }else{
            zip = "null";
        }

        String billingAddressPOSUrl = "";
        billingAddressPOSUrl = Constant.WS_BASE_URL + Constant.UPDATE_BILLING_ADDRESS_POS + posCustomerid + "/"
                + fname + "/" + lname + "/" + companyname + "/" + addOne + "/" + addTwo + "/"
                + city + "/" + state + "/" + zip + "/" + phone + "/" + phonetype + "/" + Constant.STOREID;

        TaskUpdateBillingAddressPOS billingAddressPos = new TaskUpdateBillingAddressPOS(getActivity(), this);
        Log.d(TAG, "billing address call : " + billingAddressPOSUrl);
        billingAddressPos.execute(billingAddressPOSUrl);

    }

    @Override
    public void onUpdateBillingAddressOnPosResult(String resposeStr) {
        String str= "\"Success\"";

        if(UserModel.Cust_mst_ID != null){
            String url = Constant.WS_BASE_URL + Constant.GET_SHIPPING_DATA + UserModel.Cust_mst_ID + "/" + Constant.STOREID;
            TaskShippingData taskShippingData = new TaskShippingData(this, true, getActivity());
            taskShippingData.execute(url);
        }else{
            String url = Constant.WS_BASE_URL + Constant.GET_SHIPPING_DATA + 0 + "/" + Constant.STOREID;
            TaskShippingData taskShippingData = new TaskShippingData(this,true,getActivity());
            taskShippingData.execute(url);
        }

//        if(resposeStr != null && !resposeStr.isEmpty() && !resposeStr.equals("")) {
//            if (resposeStr.equalsIgnoreCase(str)) {
//                onCallShippingDataTask();
//            }
//        }
    }

}