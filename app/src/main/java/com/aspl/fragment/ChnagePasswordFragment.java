package com.aspl.fragment;
import android.app.ProgressDialog;
import android.graphics.Color;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.core.widget.NestedScrollView;
import android.text.InputFilter;
import android.text.Spanned;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.aspl.Utils.Constant;
import com.aspl.Utils.Utils;
import com.aspl.mbs.MainActivity;
import com.aspl.mbs.MainActivityDup;
import com.aspl.mbs.R;
import com.aspl.mbsmodel.UserModel;
import com.aspl.ws.Async_getCommonService;

/**
 * A simple {@link Fragment} subclass.
 */
public class ChnagePasswordFragment extends Fragment {
    public static final String TAG = "ChangePassword";
    EditText edCurrentPwd, edNewPwd, edConfirmPwd;
    TextView tv_email,tv_ChangePasseword_title;
    NestedScrollView nested_ChangePwd;
    Button btnSend, btnCancel;
    LinearLayout ll_root_chnagePasswordlayout;
    boolean isHiddenCurrent = false;
    boolean isHiddenNew = false;
    boolean isHiddenConfirm = false;
    ImageView ivCurrentPwd;
    public static ChnagePasswordFragment chnagePasswordFragment;
    ProgressDialog progress;

    public static ChnagePasswordFragment getInstance() {
        return chnagePasswordFragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        chnagePasswordFragment = this;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_chnage_password, container, false);
        getActivity().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        nested_ChangePwd = view.findViewById(R.id.nested_ChangePwd);
        nested_ChangePwd.setBackgroundColor(Color.parseColor(Constant.themeModel.Backgroundcolor));
        ll_root_chnagePasswordlayout = view.findViewById(R.id.ll_root_chnagePasswordlayout);

        edCurrentPwd = (EditText) view.findViewById(R.id.edCurrentPwd);
        edNewPwd = (EditText) view.findViewById(R.id.edNewPwd);
        edConfirmPwd = (EditText) view.findViewById(R.id.edConfirmPwd);

        edCurrentPwd.setFocusable(false);
        edCurrentPwd.setFocusableInTouchMode(true);

        edNewPwd.setFocusable(false);
        edNewPwd.setFocusableInTouchMode(true);

        edConfirmPwd.setFocusable(true);
        edConfirmPwd.setFocusableInTouchMode(true);

//        progress = new ProgressDialog(getActivity());
        progress = new ProgressDialog(getActivity(), R.style.MyprogressDTheme);
        progress.setCancelable(false);
        progress.setCanceledOnTouchOutside(false);

//        progress.setProgressStyle(ProgressDialog.STYLE_SPINNER);


//        ivCurrentPwd = (ImageView)view.findViewById(R.id.ivCurrentPwd);
//        ivCurrentPwd.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if(ivCurrentPwd.getDrawable().equals(getResources().getDrawable(R.drawable.ic_open_eye))){
//                    ivCurrentPwd.setImageDrawable(getResources().getDrawable(R.drawable.ic_close_eye));
//                    edCurrentPwd.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
//                }
//            }
//        });

//        passwordVisibility(edCurrentPwd,true, "currentPassword");

        InputFilter filter = new InputFilter() {
            public CharSequence filter(CharSequence source, int start, int end, Spanned dest, int dstart, int dend) {
                for (int i = start; i < end; i++) {
                    if (Character.isWhitespace(source.charAt(i))) {
                        return "";
                    }
                }
                return null;
            }
        };

        edCurrentPwd.setFilters(new InputFilter[] { filter, new InputFilter.LengthFilter(15) });
        edConfirmPwd.setFilters(new InputFilter[] { filter, new InputFilter.LengthFilter(15) });
        edNewPwd.setFilters(new InputFilter[] { filter, new InputFilter.LengthFilter(15) });

        edCurrentPwd.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(!hasFocus){
                    edCurrentPwd.setCursorVisible(false);
                }else{
                    edCurrentPwd.setCursorVisible(true);
                    edCurrentPwd.setSelection(edCurrentPwd.getText().length());
                }
            }
        });

        edConfirmPwd.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(!hasFocus){
                    edConfirmPwd.setCursorVisible(false);
                }else{
                    edConfirmPwd.setCursorVisible(true);
                    edConfirmPwd.setSelection(edConfirmPwd.getText().length());
                }
            }
        });

        edNewPwd.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(!hasFocus){
                    edNewPwd.setCursorVisible(false);
                }else {
                    edNewPwd.setCursorVisible(true);
                    edNewPwd.setSelection(edNewPwd.getText().length());
                }
            }
        });

        edCurrentPwd.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_open_eye, 0);
        isHiddenCurrent = true;
        edCurrentPwd.setCompoundDrawablePadding(25);

        edCurrentPwd.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                final int DRAWABLE_LEFT = 0;
                final int DRAWABLE_TOP = 1;
                final int DRAWABLE_RIGHT = 2;
                final int DRAWABLE_BOTTOM = 3;

                if(edCurrentPwd.getCompoundDrawables()[DRAWABLE_RIGHT].getBounds()!= null){

                    if(event.getAction() == MotionEvent.ACTION_UP) {
                        if(event.getRawX() >= (edCurrentPwd.getRight() - edCurrentPwd.getCompoundDrawables()[DRAWABLE_RIGHT].getBounds().width())) {
                            // your action here

//                            if(edConfirmPwd.isCursorVisible()){
//                                edConfirmPwd.setFocusable(false);
//                                edConfirmPwd.setCursorVisible(false);
//                            }
//                            if(edNewPwd.isCursorVisible()){
//                                edNewPwd.setFocusable(false);
//                                edNewPwd.setCursorVisible(false);
//                            }
//                                edCurrentPwd.setFocusable(true);
//                                edCurrentPwd.setCursorVisible(true);

                            edCurrentPwd.setFocusable(true);
                            edCurrentPwd.setFocusableInTouchMode(true);

                            if(isHiddenCurrent){
                                edCurrentPwd.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_close_eye, 0);
                                edCurrentPwd.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                                edCurrentPwd.setSelection(edCurrentPwd.getText().length());
                                isHiddenCurrent = false;

                            }else if(!isHiddenCurrent){
                                edCurrentPwd.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_open_eye, 0);
                                edCurrentPwd.setTransformationMethod(PasswordTransformationMethod.getInstance());
                                edCurrentPwd.setSelection(edCurrentPwd.getText().length());
                                isHiddenCurrent = true;
                            }

                            return true;
                        }
                    }

                }
                return false;
            }
        });

        edNewPwd.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_open_eye, 0);
        isHiddenNew = true;
        edNewPwd.setCompoundDrawablePadding(25);

        edNewPwd.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                final int DRAWABLE_LEFT = 0;
                final int DRAWABLE_TOP = 1;
                final int DRAWABLE_RIGHT = 2;
                final int DRAWABLE_BOTTOM = 3;

                if(edNewPwd.getCompoundDrawables()[DRAWABLE_RIGHT].getBounds()!= null){

                    if(event.getAction() == MotionEvent.ACTION_UP) {
                        if(event.getRawX() >= (edNewPwd.getRight() - edNewPwd.getCompoundDrawables()[DRAWABLE_RIGHT].getBounds().width())) {
                            // your action here

//                            if(edConfirmPwd.isCursorVisible()){
//                                edConfirmPwd.setFocusable(false);
//                                edConfirmPwd.setCursorVisible(false);
//                            }
//                                edNewPwd.setFocusable(true);
//                                edNewPwd.setCursorVisible(true);
//
//                            if(edCurrentPwd.isCursorVisible()){
//                                edCurrentPwd.setFocusable(false);
//                                edCurrentPwd.setCursorVisible(false);
//                            }

                            edNewPwd.setFocusable(true);
                            edNewPwd.setFocusableInTouchMode(true);


                            if(isHiddenNew){
                                edNewPwd.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_close_eye, 0);
                                edNewPwd.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                                edNewPwd.setSelection(edNewPwd.getText().length());
                                isHiddenNew = false;

                            }else if(!isHiddenNew){
                                edNewPwd.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_open_eye, 0);
                                edNewPwd.setTransformationMethod(PasswordTransformationMethod.getInstance());
                                edNewPwd.setSelection(edNewPwd.getText().length());
                                isHiddenNew = true;
                            }

                            return true;
                        }
                    }

                }
                return false;
            }
        });

        edConfirmPwd.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_open_eye, 0);
        isHiddenConfirm = true;
        edConfirmPwd.setCompoundDrawablePadding(25);

        edConfirmPwd.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                final int DRAWABLE_LEFT = 0;
                final int DRAWABLE_TOP = 1;
                final int DRAWABLE_RIGHT = 2;
                final int DRAWABLE_BOTTOM = 3;

                if(edConfirmPwd.getCompoundDrawables()[DRAWABLE_RIGHT].getBounds()!= null){

                    if(event.getAction() == MotionEvent.ACTION_UP) {
                        if(event.getRawX() >= (edConfirmPwd.getRight() - edConfirmPwd.getCompoundDrawables()[DRAWABLE_RIGHT].getBounds().width())) {
                            // your action here

                            edConfirmPwd.setFocusable(true);
                            edConfirmPwd.setFocusableInTouchMode(true);


                            if(isHiddenConfirm){
                                edConfirmPwd.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_close_eye, 0);
                                edConfirmPwd.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                                edConfirmPwd.setSelection(edConfirmPwd.getText().length());
                                isHiddenConfirm = false;

                            }else if(!isHiddenConfirm){
                                edConfirmPwd.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_open_eye, 0);
                                edConfirmPwd.setTransformationMethod(PasswordTransformationMethod.getInstance());
                                edConfirmPwd.setSelection(edConfirmPwd.getText().length());
                                isHiddenConfirm = true;
                            }

                            return true;
                        }
                    }
                }
                return false;
            }
        });

//        passwordVisibility(edNewPwd,true,"newPassword");


//        passwordVisibility(edConfirmPwd,true,"confirmPassword");

        btnSend = (Button)view.findViewById(R.id.btnSend);
        btnCancel = (Button)view.findViewById(R.id.btnCancel);

        tv_ChangePasseword_title = (TextView) view.findViewById(R.id.tv_ChangePasseword_title);
        tv_ChangePasseword_title.setTextColor(Color.parseColor(Constant.themeModel.ThemeColor));

        tv_email = (TextView) view.findViewById(R.id.tv_email);
        if (UserModel.Cust_mst_ID != null && !UserModel.Cust_mst_ID.equals("null") && !UserModel.Email.equals("")) {
            tv_email.setText(UserModel.Email);
        }
        tv_email.setTextColor(Color.parseColor(Constant.themeModel.ThemeColor));

        btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveCheckPasswordValidation();
            }
        });


        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity.getInstance().onBackPressed();
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

            MainActivityDup.getInstance().showbackButton();

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

    private void saveCheckPasswordValidation() {

        if (edCurrentPwd != null && !edCurrentPwd.getText().toString().equals("")) {

            if (edNewPwd != null && !edNewPwd.getText().toString().equals("")) {

                if (edNewPwd.getText().toString().length() > 7 && edNewPwd.getText().toString().length() < 16) {

                    if (edConfirmPwd != null && !edConfirmPwd.getText().toString().equals("")) {

                        if(edNewPwd.getText().toString().equals(edConfirmPwd.getText().toString())){

                            progress.show();

                            callCheckPasswordWS();

                        }else{
                            Utils.showValidationDialog(getContext(), "Password and Confirm Password must be the same.", "");
                        }
                    }else{
                        Utils.showValidationDialog(getContext(), "Enter confirm password.", "");
                    }
                }else{
                    Utils.showValidationDialog(getContext(), "Sorry, your password does not match our rules of between 8 – 15 characters.", "");
                }
            }else{
                Utils.showValidationDialog(getContext(), "Enter new password.", "");
            }
        }else{
            Utils.showValidationDialog(getContext(), "Enter current password.", "");
        }
    }

    private void callCheckPasswordWS() {

        if(UserModel.Email!= null && !UserModel.Email.isEmpty() && !UserModel.Email.equals("null")){
            String Url = Constant.WS_BASE_URL + Constant.CHECK_PASSWORD + UserModel.Email + "/" + edCurrentPwd.getText().toString() + "/" + Constant.STOREID;
            new Async_getCommonService(getActivity(), Url, true).execute();
        }else{

            String email = "";
            if(!Constant.AppPref.getString("email", "").isEmpty()){
                email = Constant.AppPref.getString("email", "");
            }

            String Url = Constant.WS_BASE_URL + Constant.CHECK_PASSWORD + email + "/" + edCurrentPwd.getText().toString() + "/" + Constant.STOREID;
            new Async_getCommonService(getActivity(), Url, true).execute();
        }

    }

    public void checkPasswordWSResult(UserModel UM, String strURL) {
        if(UM != null) {
            if (UM.Result.equalsIgnoreCase("success")) {

                String sp[] = strURL.split("/");
                Log.e("Log", "Password=" + sp[sp.length - 2]);
                Log.e("Log", "User ID : " + UM.Cust_mst_ID);
                //The CHECK_PASSWORD service call in OnResume if user status not null... So here i am just avoid success dialog in every resume call
                //This will only comes when user successfully login or change password
//manage account
                if (Constant.AppPref.getString("email", "").isEmpty()) {

                    if (Constant.SCREEN_LAYOUT == 1) {
                        MainActivity.getInstance().isComeFromSigninManageAccount = true;
                        MainActivity.getInstance().getCustomerData(UM);
                    } else if (Constant.SCREEN_LAYOUT == 2) {
                        MainActivityDup.getInstance().isComeFromSigninManageAccount = true;
                        MainActivityDup.getInstance().getCustomerData(UM);
                    }
                }


                Constant.AppPref.edit().putString("email", UM.Email)
                        .putString("password", sp[sp.length - 2]).apply();


                Login.setUserDetail(UM);

//                Constant.LHSLIDER_LIST.clear();
//                Constant.AccountList.clear();
//                Utils.getAccountList();
//                Constant.LHSLIDER_LIST.put("My Account", Constant.AccountList);
//
//                if (Constant.SCREEN_LAYOUT == 1) {
////                    MainActivity.getInstance().txtNotification.setVisibility(View.VISIBLE);
//                    MainActivity.getInstance().mManage_expList.setVisibility(View.VISIBLE);
//                    ArrayList<String> TitleList = new ArrayList<String>(Constant.LHSLIDER_LIST.keySet());
//                    MainActivity.getInstance().mManage_expList.setAdapter(new ExpandAdapter(getActivity(), Constant.LHSLIDER_LIST, TitleList));
//                } else if (Constant.SCREEN_LAYOUT == 2) {
//                    if (ProfileFragment_layout2.getInstance() != null) {
//                        ProfileFragment_layout2.getInstance().LoadAccountList();
//                    }
//                }

                callInsertNewPasswordWS();
            } else {
                Log.e("test:","");
                if(progress != null && progress.isShowing()){
                   progress.dismiss();
                }
                Utils.showValidationDialog(getContext(), UM.Result, "");
            }
        }
    }

    private void callInsertNewPasswordWS() {

        String Url = Constant.WS_BASE_URL + Constant.CHANGE_PASSWORD + UserModel.Email + "/" + edNewPwd.getText().toString() + "/New/" + Constant.STOREID;
       if (Constant.SCREEN_LAYOUT==1){
           new Async_getCommonService(MainActivity.getInstance(), Url, true).execute();
       }else{
           new Async_getCommonService(MainActivityDup.getInstance(), Url, true).execute();
       }
    }

//    private void passwordVisibility(EditText editText, boolean flagStatus, String edittextName) {
//
//        editText.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_open_eye, 0);
//        isHidden = flagStatus;
//        editText.setCompoundDrawablePadding(25);
//
//        editText.setOnTouchListener(new View.OnTouchListener() {
//            @Override
//            public boolean onTouch(View v, MotionEvent event) {
//                final int DRAWABLE_LEFT = 0;
//                final int DRAWABLE_TOP = 1;
//                final int DRAWABLE_RIGHT = 2;
//                final int DRAWABLE_BOTTOM = 3;
//
////                if(edittextName.equals("currentPassword")){
////                    edCurrentPwd.setFocusable(true);
////                    edCurrentPwd.setCursorVisible(true);
////                    edConfirmPwd.setFocusable(false);
////                    edConfirmPwd.setCursorVisible(false);
////                    edNewPwd.setFocusable(false);
////                    edNewPwd.setCursorVisible(false);
////
////                }else if(edittextName.equals("newPassword")){
////                    edCurrentPwd.setFocusable(false);
////                    edCurrentPwd.setCursorVisible(false);
////                    edConfirmPwd.setFocusable(false);
////                    edConfirmPwd.setCursorVisible(false);
////                    edNewPwd.setFocusable(true);
////                    edNewPwd.setCursorVisible(true);
////
////                }else if(edittextName.equals("confirmPassword")){
////                    edCurrentPwd.setFocusable(false);
////                    edCurrentPwd.setCursorVisible(false);
////                    edNewPwd.setFocusable(false);
////                    edNewPwd.setCursorVisible(false);
////                    edConfirmPwd.setFocusable(true);
////                    edConfirmPwd.setCursorVisible(true);
////                }
//
//                if(editText.getCompoundDrawables()[DRAWABLE_RIGHT].getBounds()!= null){
//
//                    if(event.getAction() == MotionEvent.ACTION_UP) {
//                        if(event.getRawX() >= (editText.getRight() - editText.getCompoundDrawables()[DRAWABLE_RIGHT].getBounds().width())) {
//                            // your action here
//
//                            if(isHidden){
//                                editText.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_close_eye, 0);
//                                editText.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
//                                isHidden = false;
//
//                            }else if(!isHidden){
//                                editText.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_open_eye, 0);
//                                editText.setTransformationMethod(PasswordTransformationMethod.getInstance());
//                                isHidden = true;
//                            }
//
//                            return true;
//                        }
//                    }
//
//                }
//                return false;
//            }
//        });
//
//    }

    public void InsertNewPwdResult(UserModel um, String strURL) {
        if(um != null) {
            if(progress != null && progress.isShowing()){
                progress.dismiss();
            }
            if (um.Result.equalsIgnoreCase("success")) {

                String sp[] = strURL.split("/");
                String pwd = sp[sp.length - 3];
//                Log.e("Log", "Password=" + sp[sp.length - 2]);
                if(pwd != null && !pwd.isEmpty() && !pwd.equals("null")) {
                    Constant.AppPref.edit().putString("password", pwd).apply();
                }

                Utils.showValidationDialog(getContext(), "Password Changed Successfully!","");
            }else if(um.Result != null && !um.Result.isEmpty()){
                Utils.showValidationDialog(getContext(), um.Result, "");
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
                Log.e(TAG, "onReturnToHome: "+backStackEntry );
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

}
