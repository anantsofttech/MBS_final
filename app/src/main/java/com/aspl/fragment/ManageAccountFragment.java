package com.aspl.fragment;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.NestedScrollView;
import android.text.Editable;
import android.text.Html;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import com.aspl.Utils.Constant;
import com.aspl.Utils.Utils;
import com.aspl.mbs.MainActivity;
import com.aspl.mbs.MainActivityDup;
import com.aspl.mbs.R;
import com.aspl.mbsmodel.ContatInfo;
import com.aspl.mbsmodel.PinModel;
import com.aspl.mbsmodel.ShippingData;
import com.aspl.mbsmodel.UserModel;
import com.aspl.task.TaskContactInfo;
import com.aspl.task.TaskCustomerData;
import com.aspl.task.TaskUpdateBillingAddress;
import com.aspl.task.TaskUpdateBillingAddressPOS;
import com.aspl.ws.Async_getAddress;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Objects;


/**
 * A simple {@link Fragment} subclass.
 */
public class ManageAccountFragment extends Fragment implements View.OnFocusChangeListener, TaskCustomerData.TaskCustomerEvent, TaskUpdateBillingAddress.UpdateBillingAddressEvent, TaskUpdateBillingAddressPOS.UpdateBillingAddressPOSEvent, TaskContactInfo.TaskContactInfoEvent {

    public static final String TAG = "ManageAccountFragment";
    public static ManageAccountFragment manageAccountFragment;
    NestedScrollView nested_ManageAccount;
    LinearLayout ll_root_manageLayout;
    TextView tv_Manage_title,tv_last_name,tv_phone_number,tv_first_name,tv_address_one,tv_address_two,tv_email,
            tv_zip,tv_company_name,tv_City,tv_state;
    EditText et_lastname;
    EditText et_firstname;
    EditText et_company_name;
    TextView et_phone_number;
    static EditText et_city;
    static EditText et_state;
    static EditText et_zip;
    static EditText et_address_two;
    public static AutoCompleteTextView et_address_one;
    Button btnSave;

    public static boolean isdhowdropdown = true;
    Spinner spinner_mobile_option;


    public static ManageAccountFragment getInstance() {
        return manageAccountFragment;
    }

    public ManageAccountFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        manageAccountFragment = this;
//        if(Constant.SCREEN_LAYOUT == 2){
//            MainActivityDup.getInstance().hidebackbutton();
//        }
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_manage_account, container, false);
        Objects.requireNonNull(getActivity()).getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE );
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        initview(view);
//        DialogUtils.initManageAccount(view,getActivity());
    }

    public void initview(View view) {

        nested_ManageAccount = (NestedScrollView) view.findViewById(R.id.nested_ManageAccount);
        nested_ManageAccount.setBackgroundColor(Color.parseColor(Constant.themeModel.Backgroundcolor));
        tv_Manage_title = (TextView) view.findViewById(R.id.tv_Manage_title);
        tv_Manage_title.setTextColor(Color.parseColor(Constant.themeModel.ThemeColor));
        et_company_name = (EditText)view.findViewById(R.id.et_company_name);
        et_lastname = (EditText)view.findViewById(R.id.et_lastname);
        et_firstname = (EditText)view.findViewById(R.id.et_firstname);
        et_address_two = (EditText)view.findViewById(R.id.et_address_two);
        et_address_two.setOnFocusChangeListener(this);
        tv_email =  (TextView) view.findViewById(R.id.tv_email);
        btnSave = (Button)view.findViewById(R.id.btnSave);

        //Spinner
        spinner_mobile_option = (Spinner)view.findViewById(R.id.spinner_mobile_option);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getActivity(),
                R.array.number_type, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        spinner_mobile_option.setAdapter(adapter);

        et_address_one = (AutoCompleteTextView)view.findViewById(R.id.et_address_one);
        et_address_one.setOnFocusChangeListener(this);

        et_phone_number = (TextView)view.findViewById(R.id.et_phone_number);
//        et_phone_number.setOnFocusChangeListener(this);

        et_city = (EditText)view.findViewById(R.id.et_city);
        et_state = (EditText)view.findViewById(R.id.et_state);

        et_state.addTextChangedListener(new TextWatcher() {

            @Override
            public void onTextChanged(CharSequence arg0, int arg1, int arg2, int arg3) {

            }
            @Override
            public void beforeTextChanged(CharSequence arg0, int arg1, int arg2,
                                          int arg3) {
            }
            @Override
            public void afterTextChanged(Editable et) {
                String s=et.toString();
                if(!s.equals(s.toUpperCase()))
                {
                    s=s.toUpperCase();
                    et_state.setText(s);
                    et_state.setSelection(et_state.length()); //fix reverse texting
                }
            }
        });

        et_zip = (EditText)view.findViewById(R.id.et_zip);
        et_zip.setOnFocusChangeListener(this);

        tv_last_name = (TextView) view.findViewById(R.id.tv_last_name);
        tv_last_name.setText(Html.fromHtml(getResources().getString(R.string.lbl_last_name)));

        tv_phone_number = (TextView) view.findViewById(R.id.tv_phone_number);
        tv_phone_number.setText(Html.fromHtml(getResources().getString(R.string.lbl_phone_number_simple)));

        tv_first_name = (TextView) view.findViewById(R.id.tv_first_name);
        tv_first_name.setText(Html.fromHtml(getResources().getString(R.string.lbl_first_name)));

        tv_address_one = (TextView) view.findViewById(R.id.tv_address_one);
        tv_address_one.setText(Html.fromHtml(getResources().getString(R.string.lbl_address1)));

        tv_company_name = (TextView) view.findViewById(R.id.tv_company_name);
        tv_company_name.setText(Html.fromHtml(getResources().getString(R.string.lbl_company_name)));

        tv_zip = (TextView) view.findViewById(R.id.tv_zip);
        tv_zip.setText(Html.fromHtml(getResources().getString(R.string.lbl_zip)));

        tv_state = (TextView) view.findViewById(R.id.tv_state);
        tv_state.setText(Html.fromHtml(getResources().getString(R.string.lbl_state_mendatory)));

        tv_City = (TextView) view.findViewById(R.id.tv_City);
        tv_City.setText(Html.fromHtml(getResources().getString(R.string.lbl_city_mendatory)));


        getCustomerData();

        loadContactInfoWSdata();

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                DialogUtils.underDevelopmentDialog(getActivity());

                if (!validate()) {
                    return;
                }else{
                    String firstName = et_firstname.getText().toString().isEmpty() ? "0" : et_firstname.getText().toString().trim();
                    String lastName = et_lastname.getText().toString().isEmpty() ? "0" :et_lastname.getText().toString().trim();
                    String companyName = et_company_name.getText().toString().isEmpty() ? "0" :et_company_name.getText().toString().trim();
                    if(companyName.equals("") ||companyName.isEmpty()){
                        companyName = "0";
                    }
                    String addressOne = et_address_one.getText().toString().isEmpty() ? "0" :et_address_one.getText().toString().trim();
                    String addressTwo = et_address_two.getText().toString().isEmpty() ? "0" :et_address_two.getText().toString().trim();
                    if(addressTwo.equals("") ||addressTwo.isEmpty()){
                        addressTwo = "0";
                    }
                    String city = et_city.getText().toString().isEmpty() ? "0" :et_city.getText().toString().trim();
                    String state = et_state.getText().toString().isEmpty() ? "0" :et_state.getText().toString().trim();
                    String zip = et_zip.getText().toString().isEmpty() ? "0" :et_zip.getText().toString().trim();
                    String phoneNo = et_phone_number.getText().toString().isEmpty() ? "0" :et_phone_number.getText().toString().trim();
                    String phoneType = spinner_mobile_option.getSelectedItem().toString();

                    callUpdateBillingAddress(firstName,lastName,companyName,addressOne,addressTwo,city,state,zip,phoneNo,phoneType);
                }
            }
        });
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

    private void loadContactInfoWSdata() {

        String getContactInfoURL = Constant.WS_BASE_URL + Constant.GET_CONTACT_INFO + "/" + Constant.STOREID;
        TaskContactInfo taskContactInfo = new TaskContactInfo(ManageAccountFragment.this, getContext());
        taskContactInfo.execute(getContactInfoURL);
    }

    @Override
    public void contactInfoEventResult(ContatInfo contatInfo) {

        if (contatInfo != null) {
            Constant.contatInfo = contatInfo;
        }
    }

    public void getCustomerData() {

        if (UserModel.Cust_mst_ID != null) {
            String url = Constant.WS_BASE_URL + Constant.GET_CUSTOMER_DATA + UserModel.Cust_mst_ID + "/" + Constant.STOREID;
            TaskCustomerData taskCustomerData = new TaskCustomerData(getActivity(), this,true);
            Log.d("", "Customer data : " + url);
            taskCustomerData.execute(url);
        }
    }

    private boolean validate() {

        boolean valid = true;

        if (et_lastname.getText().toString().isEmpty()) {
            et_lastname.setError("Last Name is required.");
            et_lastname.requestFocus();
            valid = false;
        } else if (et_firstname.getText().toString().isEmpty()) {
            et_firstname.setError("First Name is required.");
            et_firstname.requestFocus();
            valid = false;
        }
//        else if (et_phone_number.getText().toString().isEmpty()) {
//            et_phone_number.setError("Phone Number is required.");
//            et_phone_number.requestFocus();
//            valid = false;
//        }
//        else if (et_phone_number.getText().toString().length() < 14) {
//            et_phone_number.setError("Please enter 10 digit Phone Number.");
//            et_phone_number.requestFocus();
//            valid = false;
//        }
        else if (et_address_one.getText().toString().isEmpty()) {
            et_address_one.setError("Address 1 is required.");
            et_address_one.requestFocus();
            valid = false;
        }
        else if (et_zip.getText().toString().isEmpty()) {
            et_zip.setError("Enter zip code");
            et_zip.requestFocus();
            valid = false;
        } else if (!et_zip.getText().toString().isEmpty() && et_zip.getText().toString().length()<5) {
            et_zip.setError("Invalid zip code");
            et_zip.requestFocus();
            valid = false;
        }
        else if (et_city.getText().toString().isEmpty()) {

            et_city.setError("Enter City");
            et_city.requestFocus();
            valid = false;

        }else if (et_state.getText().toString().isEmpty()) {

            et_state.setError("Enter State");
            et_state.requestFocus();
            valid = false;

        }else {
            et_lastname.setError(null);
            et_firstname.setError(null);
            et_company_name.setError(null);
//            et_phone_number.setError(null);
            et_address_one.setError(null);
            et_address_two.setError(null);
            et_zip.setError(null);
            et_state.setError(null);
            et_city.setError(null);
        }

        return valid;
    }

    @Override
    public void onFocusChange(View view, boolean hasFocus) {
        if (view.getId() == et_zip.getId() && et_zip.isFocused()) {

            et_state.setError(null);
            et_city.setError(null);

            et_zip.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                }

                @Override
                public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                    if (charSequence.length() == 5) {
                        //http://192.168.172.211:889/WebStoreRestService.svc/GetZipCode/11001
                        String Url = Constant.WS_BASE_URL + Constant.GET_ZIP_CODE + charSequence.toString();
                        new Async_getAddress(getActivity(), Url, 5).execute();
                    }
                }

                @Override
                public void afterTextChanged(Editable editable) {

                }
            });
        }

//        else if (view.getId() == et_phone_number.getId() && et_phone_number.isFocused()) {
//
//            et_phone_number.addTextChangedListener(new TextWatcher() {
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
//                    if (length_before < et_phone_number.length()) {
//                        if (et_phone_number.length() == 1) {
//                            et_phone_number.setText("(" + s);
//                            et_phone_number.setSelection(2);
//                        } else if (et_phone_number.length() == 5) {
//                            String last = et_phone_number.getText().toString();
//                            last = last.substring(last.length() - 1);
//                             et_phone_number.setText(temp + ") " + last);
//                            et_phone_number.setSelection(7);
//                        } else if (et_phone_number.length() == 10) {
//                            String last = et_phone_number.getText().toString();
//                            last = last.substring(last.length() - 1);
//                            et_phone_number.setText(temp + "-" + last);
//                            if (et_phone_number.getText().length() > 10)
//                                et_phone_number.setSelection(11);
//                        } else {
//                            temp = et_phone_number.getText().toString();
//                            et_phone_number.setText(s);
//                            et_phone_number.setSelection(et_phone_number.length());
//                        }
//                    }
//                }
//            });
//
//        }

        else if (view.getId() == et_address_one.getId() && et_address_one.isFocused()) {
            status = 5;

            et_address_one.addTextChangedListener(myWatcher /*new TextWatcher() {
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

            if (et_address_one.isFocused() || et_address_one.isFocused()) {
                isdhowdropdown = true;
            }

            if (isdhowdropdown) {
                Log.e(TAG, "request Call " + Name);
                new Async_getAddress(MainActivity.getInstance(), Constant.MAP_API_URL + Constant.INPUT + Name + "&key=" + MainActivity.getInstance().getString(R.string.Place_API_key), status).execute();
            }
        }
    };

    public static void onFillAddress(JSONArray address, int status) throws JSONException {

        Log.e(TAG, "onFillAddress: " + address);

            et_address_one.removeTextChangedListener(myWatcher);
            et_address_one.setText("");
            et_address_two.setText("");
            et_zip.setText("");
            et_state.setText("");
            et_city.setText("");
            String address11 = "";
            for (int i = 0; i < address.length(); i++) {
                JSONObject object = address.getJSONObject(i);

                et_address_one.clearFocus();
                et_address_one.dismissDropDown();
                et_address_two.requestFocus();

                if (object.getString("types").contains("street_number")) {
                    address11 += object.getString("long_name");
                    et_address_one.setText(address11);
                }
                if (object.getString("types").contains("route")) {
                    address11 += " " + object.getString("long_name");
                    et_address_one.setText(address11);
                }
                if (object.getString("types").contains("postal_code")) {
                    et_zip.setText(object.getString("long_name"));
                }
                if (object.getString("types").contains("administrative_area_level_1")) {
                    et_state.setText(object.getString("short_name"));
                }
                if (object.getString("types").contains("locality"/*"*//*administrative_area_level_2*/)) {
                    et_city.setText(object.getString("short_name"));
                }
            et_address_one.dismissDropDown();
        }
    }

    public static void onFillZipAddress(PinModel model, int status) {
        Log.e(TAG, "onFillZipAddress: ");

//        et_state.setText(model._state);
//        et_city.setText(model._city);

        if(model != null){
            if(!model._state.isEmpty()){
                et_state.setText(model._state);
                et_state.setError(null);
            }
            if(!model._city.isEmpty()) {
                et_city.setText(model._city);
                et_city.setError(null);
            }

            if(model._state.isEmpty() || model._city.isEmpty()){
                et_zip.setError("Data not found");
                et_zip.setText("");
                et_city.setText("");
                et_state.setText("");
                et_zip.requestFocus();
            }else{
                et_zip.setError(null);
            }
        }
    }

    @Override
    public void onTaskCustomerResult(ShippingData liShippingData, boolean isFromfavouriteStore) {

        if(liShippingData != null){
            if(liShippingData.getLastName()!= null && !liShippingData.getLastName().equals("null")){
                et_lastname.setText(liShippingData.getLastName().trim());
            }
            if(liShippingData.getFirstName()!= null && !liShippingData.getFirstName().equals("null")){
                et_firstname.setText(liShippingData.getFirstName().trim());
            }
            if(liShippingData.getCompanyName()!= null && !liShippingData.getCompanyName().equals("null")){
                et_company_name.setText(liShippingData.getCompanyName().trim());
            }
            if(liShippingData.getEmail()!= null && !liShippingData.getEmail().equals("null")){
                tv_email.setText(liShippingData.getEmail().trim());

                tv_email.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Utils.newDesignCommonDialog(getActivity(), "Editing not allowed", getResources().getString(R.string.Editing_not_allowed_text));
                    }
                });
            }
            if(liShippingData.getPhone()!= null && !liShippingData.getPhone().equals("null")){
                et_phone_number.setText(liShippingData.getPhone().trim());

                et_phone_number.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Utils.newDesignCommonDialog(getActivity(), "Editing not allowed", getResources().getString(R.string.Editing_not_allowed_text));
                    }
                });
            }
            if(liShippingData.getAddress1()!= null && !liShippingData.getAddress1().equals("null")){
                et_address_one.setText(liShippingData.getAddress1().trim());
            }
            if(liShippingData.getAddress2()!= null && !liShippingData.getAddress2().equals("null")){
                et_address_two.setText(liShippingData.getAddress2().trim());
            }
            if(liShippingData.getCity()!= null && !liShippingData.getCity().equals("null")){
                et_city.setText(liShippingData.getCity().trim());
            }
            if(liShippingData.getState()!= null && !liShippingData.getState().equals("null")){
                et_state.setText(liShippingData.getState().trim());
            }
            if(liShippingData.getZip()!= null && !liShippingData.getZip().equals("null")){
                et_zip.setText(liShippingData.getZip().trim());
            }

            if(liShippingData.getPhonetype().equals("M")){
                spinner_mobile_option.setSelection(0);
            }else if(liShippingData.getPhonetype().equals("H")){
                spinner_mobile_option.setSelection(1);
            }else if(liShippingData.getPhonetype().equals("W")){
                spinner_mobile_option.setSelection(2);
            }
        }

    }

    private void callUpdateBillingAddress(String firstName, String lastName, String companyName, String addressOne, String addressTwo, String city, String state, String zip, String phoneNo, String phoneType) {

        String billingAddressUrl = "";
        billingAddressUrl = Constant.WS_BASE_URL + Constant.UPDATE_BILLING_ADDRESS + UserModel.Cust_mst_ID + "/"
                + firstName + "/" + lastName + "/" + companyName + "/" + addressOne + "/" + addressTwo + "/"
                + city + "/" + state + "/" + zip + "/" + phoneNo + "/" + phoneType + "/" + Constant.STOREID;

        TaskUpdateBillingAddress billingAddress = new TaskUpdateBillingAddress(getActivity(), this);
        Log.d(TAG, "billing address call : " + billingAddressUrl);
        billingAddress.execute(billingAddressUrl);
    }

    @Override
    public void onBillingAddressResult(ShippingData shippingData) {

        if(shippingData!=null){
            if(shippingData.getResult()!=null && !shippingData.getResult().isEmpty() && !shippingData.getResult().equals("")){
                if(shippingData.getResult().contains("success")){
                    String result = shippingData.getResult();
                    String[] arrayString = result.split("-");

                    String posid = arrayString[1];
                    callUpdateBillingAddressonPosdata(posid);
                }
            }
        }
    }

    private void callUpdateBillingAddressonPosdata(String posCustomerid) {

        String firstName = et_firstname.getText().toString().isEmpty() ? "0" : et_firstname.getText().toString();
        String lastName = et_lastname.getText().toString().isEmpty()? "0" : et_lastname.getText().toString();
        String companyName = et_company_name.getText().toString().isEmpty()? "0" : et_company_name.getText().toString().trim();
        if(companyName.equals("") ||companyName.isEmpty()){
            companyName = "0";
        }
        String addressOne = et_address_one.getText().toString().isEmpty()? "0" :et_address_one.getText().toString().trim();
        String addressTwo = et_address_two.getText().toString().isEmpty()? "0" :et_address_two.getText().toString().trim();
        if(addressTwo.equals("") ||addressTwo.isEmpty()){
            addressTwo = "0";
        }
        String city = et_city.getText().toString().isEmpty()? "0" :et_city.getText().toString().trim();
        String state = et_state.getText().toString().isEmpty()? "0" :et_state.getText().toString().trim();
        String zip = et_zip.getText().toString().isEmpty()? "0" :et_zip.getText().toString().trim();
        String phoneNo = et_phone_number.getText().toString().isEmpty()? "0" :et_phone_number.getText().toString().trim();
        String phoneType = spinner_mobile_option.getSelectedItem().toString();

        if(posCustomerid.isEmpty() && posCustomerid.equals("")){
            posCustomerid = "0";
        }

        String billingAddressPOSUrl = "";
        billingAddressPOSUrl = Constant.WS_BASE_URL + Constant.UPDATE_BILLING_ADDRESS_POS + posCustomerid + "/"
                + firstName + "/" + lastName + "/" + companyName + "/" + addressOne + "/" + addressTwo + "/"
                + city + "/" + state + "/" + zip + "/" + phoneNo + "/" + phoneType + "/" + Constant.STOREID;

        TaskUpdateBillingAddressPOS billingAddressPos = new TaskUpdateBillingAddressPOS(getActivity(), this);
        Log.d(TAG, "billing address call : " + billingAddressPOSUrl);
        billingAddressPos.execute(billingAddressPOSUrl);

    }

    @Override
    public void onUpdateBillingAddressOnPosResult(String resposeStr) {

        String str= "\"Success\"";

        if(resposeStr != null && !resposeStr.isEmpty() && !resposeStr.equals("")){
            if(resposeStr.equalsIgnoreCase(str)){

                UserModel.FirstName = et_firstname.getText().toString().trim();
//
                if (Constant.SCREEN_LAYOUT == 1) {
                    if(MainActivity.txtPersonName != null){
                        MainActivity.txtPersonName.setText("");
                        MainActivity.txtPersonName.setText("Hi " + et_firstname.getText().toString().trim());
                    }
                }

//                else if(Constant.SCREEN_LAYOUT == 2){
//                    if(ProfileFragment_layout2.txtPersonName != null){
//                        ProfileFragment_layout2.txtPersonName.setText("");
//                        ProfileFragment_layout2.txtPersonName.setText("Hi " + et_firstname.getText().toString().trim());
//                    }
//                }
                Utils.showValidationDialog(getContext(), "Details saved successfully!", "");
            }

        }

    }

    public void onReturnToHome() {

        int backStackEntry = 0;
        if (getFragmentManager() != null) {
            backStackEntry = getFragmentManager().getBackStackEntryCount();
        }
        if (backStackEntry > 0) {
            for (int i = 0; i < backStackEntry; i++) {
                if(getFragmentManager()!=null){
                    getFragmentManager().popBackStackImmediate();
                }
            }
        }

        if (Constant.SCREEN_LAYOUT == 1) {
            MainActivity.showHomePage();
            MainActivity.getInstance().loadHomeWebPage();

        } else if (Constant.SCREEN_LAYOUT == 2) {
            MainActivityDup.showHomePage();
            MainActivityDup.getInstance().loadHomeWebPage();
        }

    }


}
