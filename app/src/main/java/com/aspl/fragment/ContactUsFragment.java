package com.aspl.fragment;


import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.location.Address;
import android.location.Geocoder;
import android.net.Uri;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.google.android.material.textfield.TextInputEditText;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.core.widget.NestedScrollView;
import androidx.cardview.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.aspl.Utils.BSTheme;
import com.aspl.Utils.Constant;
import com.aspl.Utils.Utils;
import com.aspl.mbs.MainActivity;
import com.aspl.mbs.MainActivityDup;
import com.aspl.mbs.R;
import com.aspl.mbsmodel.ContatInfo;
import com.aspl.mbsmodel.UserModel;
import com.aspl.task.TaskContactInfo;
import com.aspl.task.TaskSaveContactInfo;
import com.aspl.task.TaskSendMailInfo;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.List;
import java.util.Locale;

/**
 * A simple {@link Fragment} subclass.
 */
public class ContactUsFragment extends Fragment implements TaskContactInfo.TaskContactInfoEvent,
        TaskSaveContactInfo.TaskSaveContactInfoEvent,
        OnMapReadyCallback, TaskSendMailInfo.TaskSendMailInfoEvent {

    public static final String TAG = "ContactUs";
    TextView tv_contactus_title, tv_fname, tv_lname, tv_email, tv_message, tvAppname, tvAddresstitle, tvAddress1, tvAddress2, tv_phone;
    EditText edfirstname, edlastname, edemail;
    TextInputEditText textinput_pers_Msg;
    NestedScrollView nested_contactus;
    LinearLayout ll_root_contactuslayout;
    CheckBox checkbox_addmy;
    Button btnSend;
    GoogleMap mGoogleMap;
    String addressTocontact = "";
    MapView mMapView;
    GoogleMap mMap;
    CardView cvAddress;
    String cusName = "",message = "",customerEmailid = "", receiverEmailId = "";
    Geocoder geocoder;
    double addLat,addLng;
    ProgressDialog loading = null;
    public static ContactUsFragment contactUsFragment;


    public ContactUsFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        contactUsFragment = this;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_contact_us, container, false);

        try{
            SupportMapFragment mapFragment = (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.mainMap);
            mapFragment.getMapAsync(ContactUsFragment.this);
        }catch (Exception e){
            e.printStackTrace();
        }
        return view;
    }

    public static ContactUsFragment getInstance() {
        return contactUsFragment;
    }

    @Override
    public void onResume() {
        super.onResume();

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

        }

        if(Constant.SCREEN_LAYOUT == 2){
            MainActivityDup.getInstance().hidebackbutton();
        }
//        if(Constant.SCREEN_LAYOUT == 2){
//            MainActivityDup.getInstance().showbackButton();
//        }
//        else if (Constant.SCREEN_LAYOUT == 2) {
//
//            MainActivityDup.getInstance().llsearch.setVisibility(View.VISIBLE);
//
//            if (Constant.isFromMicSeclayout) {
//                MainActivityDup.getInstance().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);
//                MainActivityDup.getInstance().mSearchedt.requestFocus();
//                MainActivityDup.getInstance().mSearchedt.setFocusableInTouchMode(true);
//                Constant.isFromMicSeclayout = false;
//            } else {
//                MainActivityDup.getInstance().mSearchedt.clearFocus();
//                Utils.hideKeyboard();
//            }
//        }

        Utils.hideKeyboard();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        if(getActivity() != null) {
            loading = new ProgressDialog(getActivity(), R.style.MyprogressDTheme);
            loading.setCancelable(false);
            loading.setProgressStyle(ProgressDialog.STYLE_SPINNER);
            loading.show();
        }

        loadContactInfoWSdata();

        nested_contactus = view.findViewById(R.id.nested_contactus);
        nested_contactus.setBackgroundColor(Color.parseColor(Constant.themeModel.Backgroundcolor));
        ll_root_contactuslayout = view.findViewById(R.id.ll_root_contactuslayout);
        tv_contactus_title = (TextView) view.findViewById(R.id.tv_contactus_title);
        tv_contactus_title.setTextColor(Color.parseColor(Constant.themeModel.ThemeColor));
        tv_fname = (TextView) view.findViewById(R.id.tv_fname);
        tv_lname = (TextView) view.findViewById(R.id.tv_lname);
        tv_email = (TextView) view.findViewById(R.id.tv_email);
        tv_message = (TextView) view.findViewById(R.id.tv_message);
        edfirstname = (EditText) view.findViewById(R.id.edfirstname);
        edlastname = (EditText) view.findViewById(R.id.edlastname);
        edemail = (EditText) view.findViewById(R.id.edemail);
        textinput_pers_Msg = (TextInputEditText) view.findViewById(R.id.TextInputet_PersonalMsg);
        checkbox_addmy = (CheckBox) view.findViewById(R.id.checkbox_addmy);
        BSTheme.setCheckBoxColor(checkbox_addmy, Color.parseColor(Constant.themeModel.ThemeColor), Color.GRAY);

        if (UserModel.Cust_mst_ID != null && !UserModel.Cust_mst_ID.equals("null") && !UserModel.Email.equals("")) {
            checkbox_addmy.setVisibility(View.GONE);
        }else{
            checkbox_addmy.setVisibility(View.VISIBLE);
        }

        btnSend = (Button) view.findViewById(R.id.btnSend);
        GradientDrawable bgShape = (GradientDrawable) btnSend.getBackground();
        bgShape.setColor(Color.parseColor(Constant.themeModel.ThemeColor));
        tv_phone = (TextView) view.findViewById(R.id.tv_phone);
        tv_phone.setTextColor(Color.parseColor(Constant.themeModel.ThemeColor));

        tv_phone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(!tv_phone.getText().toString().isEmpty()){
                    String phone = tv_phone.getText().toString().trim();
                    Intent intent = new Intent(Intent.ACTION_DIAL, Uri.fromParts("tel", phone, null));
                    startActivity(intent);
                }
            }
        });
        tvAppname = (TextView) view.findViewById(R.id.tvAppname);
        tvAddress1 = (TextView) view.findViewById(R.id.tvAddress1);
        tvAddress2 = (TextView) view.findViewById(R.id.tvAddress2);
        tvAddresstitle = (TextView) view.findViewById(R.id.tvAddresstitle);
        cvAddress = (CardView) view.findViewById(R.id.cvAddress);

            if (UserModel.Cust_mst_ID != null && !UserModel.Cust_mst_ID.equals("null") && !UserModel.Email.equals("")) {
                edemail.setText(UserModel.Email);

                if (UserModel.FirstName != null && !UserModel.FirstName.equals("null") && !UserModel.FirstName.equals("")) {
                    edfirstname.setText(UserModel.FirstName);
                }
                if (UserModel.LastName != null && !UserModel.LastName.equals("null") && !UserModel.LastName.equals("")) {
                    edlastname.setText(UserModel.LastName);
                }
            }
    }

    private void loadContactInfoWSdata() {

        String getContactInfoURL = Constant.WS_BASE_URL + Constant.GET_CONTACT_INFO + "/" + Constant.STOREID;
        TaskContactInfo taskContactInfo = new TaskContactInfo(ContactUsFragment.this, getContext());
        taskContactInfo.execute(getContactInfoURL);
    }

    @Override
    public void contactInfoEventResult(ContatInfo contatInfo) {

        if(loading != null && loading.isShowing()){
            loading.dismiss();
        }

        if (contatInfo != null) {
            cvAddress.setVisibility(View.VISIBLE);
            String address2 = "";
            if (contatInfo.getName() != null && !contatInfo.getName().equals("")) {
                tvAppname.setText(contatInfo.getName());
            }
            if (contatInfo.getAddress() != null && !contatInfo.getAddress().equals("")) {
                tvAddress1.setText(contatInfo.getAddress());
            }
            if (contatInfo.getCity() != null && !contatInfo.getCity().equals("")) {
                address2 = contatInfo.getCity();
            }
            if (contatInfo.getState() != null && !contatInfo.getState().equals("")) {
                address2 = address2 + " " + contatInfo.getState();
            }
            if (contatInfo.getZip() != null && !contatInfo.getZip().equals("")) {
                address2 = address2 + " " + contatInfo.getZip();
            }

            if(contatInfo.getEmail() != null && !contatInfo.getEmail().equals("")){
                receiverEmailId = contatInfo.getEmail();
            }

            if (!address2.equals("")) {
                tvAddress2.setText(address2);
            }

            if (contatInfo.getPhone() != null && !contatInfo.getPhone().equals("")) {
                tv_phone.setText(contatInfo.getPhone());
            }

            if (!tvAddress1.getText().toString().equals("") || !tvAddress2.getText().toString().equals("")) {
                tvAddresstitle.setText("Address");
            }

            addressTocontact = tvAddress1.getText().toString() + " " + tvAddress2.getText().toString();

            try {
                if (getContext() != null) {
                    geocoder = new Geocoder(getContext(), Locale.getDefault());
//
                    if (addressTocontact != null && !addressTocontact.isEmpty()) {
                        try {
                            List<Address> addressList = geocoder.getFromLocationName(addressTocontact, 1);
                            if (addressList != null && addressList.size() > 0) {
                                addLat = addressList.get(0).getLatitude();
                                addLng = addressList.get(0).getLongitude();

                                onMapReady(mMap);

                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }
            } catch (IllegalStateException ignored) {
                // There's no way to avoid getting this if saveInstanceState has already been called.
            }
        }

        btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saveContactMailInfoWS();
            }
        });
    }


    private void saveContactMailInfoWS() {

        String fname = edfirstname.getText().toString();
        String lname = edlastname.getText().toString();
        String email = edemail.getText().toString();
        String msg = textinput_pers_Msg.getText().toString();
        boolean updateToPos;
        if (checkbox_addmy.isChecked()) {
            updateToPos = true;
        } else {
            updateToPos = false;
        }
        final String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";

        if (fname != null && !fname.equals("")) {
            if (lname != null && !lname.equals("")) {
                if (email != null && !email.equals("")){
                    if(email.matches(emailPattern) && email.length() > 0) {
                        if (msg != null && !msg.equals("")) {
                            loadSaveContactIndoWS(fname, lname, msg, email, updateToPos);
                        } else {
                            Utils.showValidationDialog(getContext(), "Enter all details.", "");
                        }
                    }else{
                        Utils.showValidationDialog(getContext(), "Enter valid email address.", "");
                    }
                } else {
                    Utils.showValidationDialog(getContext(), "Enter all details.", "");
                }
            } else {
                Utils.showValidationDialog(getContext(), "Enter all details.", "");
            }
        } else {
            Utils.showValidationDialog(getContext(), "Enter all details.", "");
        }

    }

    private void loadSaveContactIndoWS(String fname, String lname, String msg, String email, boolean updateToPos) {
        cusName = fname;
        customerEmailid = email;
        message = msg;

        String getContactInfoURL = Constant.WS_BASE_URL + Constant.SAVE_CONTACT_MAIL_INFO + fname + "/" + lname + "/" + Constant.STOREID +"/" + msg + "/" + email + "/" + updateToPos;
        TaskSaveContactInfo taskSaveContactInfo = new TaskSaveContactInfo(ContactUsFragment.this, getContext());
        taskSaveContactInfo.execute(getContactInfoURL);
    }

    @Override
    public void taskSaveContactInfoResult(String successvalue) {

        if (Integer.parseInt(successvalue) == 1) {

            String getSendMailURL = Constant.WS_BASE_URL + Constant.SAND_EMAIL_FROM_CONTACTUS + Constant.STOREID + "/" + customerEmailid + "/" + receiverEmailId + "/" + message + "/" + cusName;
//            Log.d("url","" + getSendMailURL);

            TaskSendMailInfo taskSendMailInfo = new TaskSendMailInfo(ContactUsFragment.this, getContext());
            taskSendMailInfo.execute(getSendMailURL);

//            http://192.168.172.244:889/WebStoreRestService.svc/SendEmailFromContactUs/storeno/customeremailid/receivermailid/msg/name
        }
    }


    @Override
    public void onLowMemory() {
        super.onLowMemory();
        mMapView.onLowMemory();
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {


        try {
            mMap = googleMap;

            // Add a marker in Sydney and move the camera
            LatLng croplatlng = new LatLng(addLat, addLng);

//            MarkerOptions markerOptions = new MarkerOptions();
//            markerOptions.position(croplatlng);
//            markerOptions.title(croplatlng + addressTocontact);
            mMap.addMarker(new MarkerOptions().position(croplatlng).title(addressTocontact)).showInfoWindow();

            CameraUpdate cameraUpdate = CameraUpdateFactory.newLatLngZoom(croplatlng , 16);
            //mMap.addMarker(new MarkerOptions().position(location).title(""));
            //mMap.moveCamera(CameraUpdateFactory.newLatLng(currentLocation ));
            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(croplatlng,16));
            mMap.animateCamera(cameraUpdate,2000,null);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void onDestroyView()
    {
        try {
            Fragment fragment = (getChildFragmentManager().findFragmentById(R.id.mainMap));
            if (fragment != null) {
                FragmentTransaction ft = getActivity().getSupportFragmentManager().beginTransaction();
                ft.remove(fragment);
                ft.commit();
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        super.onDestroyView();
    }


    @Override
    public void taskSendMailInfoResult(String successvalue) {

//        Utils.showValidationDialog(getContext(), "You will be returned to the home page");

        if(successvalue != null && !successvalue.equals("")){
            String successval = "\"Success\"";
            if(successvalue.equals(successval)){
                Utils.showValidationDialog(getContext(), "You will be returned to the home page", "");
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
                getFragmentManager().popBackStackImmediate();
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

//    @Override
//    public void onSaveInstanceState(Bundle outState) {
//    }



}











