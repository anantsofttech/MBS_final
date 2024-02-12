package com.aspl.fragment;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.support.design.widget.TextInputLayout;
import android.text.Editable;
import android.text.Html;
import android.text.InputFilter;
import android.text.InputType;
import android.text.Spanned;
import android.text.TextWatcher;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.aspl.Adapter.ExpandAdapter;
import com.aspl.Adapter.FooterAdapter;
import com.aspl.Utils.Constant;
import com.aspl.Utils.DialogUtils;
import com.aspl.Utils.Utils;
import com.aspl.fcm.FCMServerRegistration;
import com.aspl.mbs.MainActivity;
import com.aspl.mbs.MainActivityDup;
import com.aspl.mbs.R;
import com.aspl.mbsmodel.MbsDataModel;
import com.aspl.mbsmodel.PinModel;
import com.aspl.mbsmodel.UserModel;
import com.aspl.ws.Async_getCommonService;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static android.content.Context.MODE_PRIVATE;

/**
 * Created by mic on 11/28/2017.
 */

public class Login {


    public static Dialog loginDialog;
    public static boolean isHidden = false;
    public static boolean isHiddenSignup = false;
    public static boolean isHiddenSignupConfirm = false;

    public static String EmailID = "";
    public static String Password = "";
    public static String Tag;
    public static String strGlobalEmail = "";
    public static String strOTP = "";

    public static View sign_view, login_success_view;
    public static TextView sign_txtclose, sign_txt_recover, sign_txt_signin, sign_txt_forget_instruction, sign_txtloginhead;
    public static Button sign_btnContinue, sign_btnPrev;
    public static EditText sign_edtEmailID,  sign_confirm_password;
    public static  AutoCompleteTextView sign_edtEmailIDFirst;
    public static TextInputLayout sign_input_layout_empid_for,sign_input_layout_empid_for_email,sign_confirm_input_layout_password;
    public static LinearLayout sign_llmiddle;
    public static TextView sign_txtotperror, txtPasswordHint;
    public static String OtpFrom = "";
    public static Dialog Signupdialog;
    public static AutoCompleteTextView autotxtAddress1;
    public static EditText edtzip;
    public static EditText edtcity;
    public static EditText edtstate;
    public static EditText edtaddress2;
    public static boolean isdhowdropdown = true;
    public static boolean guest = true;
    //public static TextView txtPersonName, txtPersonEmail;

    /*Login sucess dialog*/
    private static Dialog loginSuccessDialog;
    private static TextView tvHi, tvUserName, tvWelconeText, tvOk,tvRegisterToWishlist; //tvRegisterToWishlist Edited by Janvi 4th oct end*******
    private static Button btnOkLoginSuccessDialog;
    static Context mContext;
    public static boolean isResentOTP = false;

    public static boolean isSetAddress = true;


    public static final String PREFS_NAME = "MyPrefs";
    public static final String EMAIL_LIST_KEY = "emailList";

    public static SharedPreferences sharedPreferences;
    public static ArrayAdapter<String> adapter;

    private /*static*/ String blockCharacters = "(~*#^|$%&!+-/";

    public static void StartLoginDialog(String s, Context context) {
        mContext = context;
        Tag = "signin";

        if (Constant.SCREEN_LAYOUT == 1) {
            loginDialog = new Dialog(MainActivity.getInstance(), R.style.DialogSlideAnim_login);
            loginDialog.setCanceledOnTouchOutside(false);
            sign_view = LayoutInflater.from(MainActivity.getInstance()).inflate(R.layout.rawlogin, null);
        } else if (Constant.SCREEN_LAYOUT == 2) {
            loginDialog = new Dialog(MainActivityDup.getInstance(), R.style.DialogSlideAnim_login);
            loginDialog.setCanceledOnTouchOutside(false);
            sign_view = LayoutInflater.from(MainActivityDup.getInstance()).inflate(R.layout.rawlogin, null);
        }
        sign_txtclose = sign_view.findViewById(R.id.txtclose);
        sign_txtotperror = sign_view.findViewById(R.id.txtotperror);
        txtPasswordHint = sign_view.findViewById(R.id.txtPasswordHint);
        sign_txt_recover = sign_view.findViewById(R.id.txt_recover);
        sign_txt_signin = sign_view.findViewById(R.id.txt_signin);
        sign_txt_forget_instruction = sign_view.findViewById(R.id.txt_forget_instruction);
        sign_txtloginhead = sign_view.findViewById(R.id.txtloginhead);
        sign_btnContinue = sign_view.findViewById(R.id.btnContinue);
        sign_btnPrev = sign_view.findViewById(R.id.btnPrev);

        /*txtPersonEmail = (TextView) sign_view.findViewById(R.id.txtPersonEmail);
        txtPersonName = (TextView) sign_view.findViewById(R.id.txtPersonName);
        txtPersonName.setText(Constant.themeModel.StoreName);
*/

        sign_llmiddle = sign_view.findViewById(R.id.llmiddle);

        sign_input_layout_empid_for = sign_view.findViewById(R.id.input_layout_empid_for);
        sign_input_layout_empid_for.setVisibility(View.GONE);
        sign_input_layout_empid_for_email = sign_view.findViewById(R.id.input_layout_empid_for_email);


        sign_edtEmailID = sign_view.findViewById(R.id.edtEmailID);

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

        sign_edtEmailID.setFilters(new InputFilter[] { filter, new InputFilter.LengthFilter(15) });
//        sign_edtEmailID.setFilters(new InputFilter[]{Utils.ignoreFirstWhiteSpace()});

        sign_edtEmailIDFirst = sign_view.findViewById(R.id.edtEmailIDFirst);
        sign_edtEmailIDFirst.setFilters(new InputFilter[] {filter});
        sign_edtEmailIDFirst.requestFocus();

//        Edted by Varun for suggestion email when user tries to login
        sharedPreferences = mContext.getSharedPreferences(PREFS_NAME, MODE_PRIVATE);

        // Retrieve the list of emails from SharedPreferences
        Set<String> emailSet = sharedPreferences.getStringSet(EMAIL_LIST_KEY, new HashSet<>());
        List<String> emailList = new ArrayList<>(emailSet);

        Log.e("", "StartLoginDialog: "+emailList.size() );
        // Create the adapter with emailList
        adapter = new ArrayAdapter<>(mContext, android.R.layout.simple_dropdown_item_1line, emailList);
        sign_edtEmailIDFirst.setAdapter(adapter);
//        sign_edtEmailIDFirst.setThreshold(0);
//        sign_edtEmailIDFirst.showDropDown();


//        END

//        sign_edtEmailIDFirst.setFilters(new InputFilter[]{Utils.ignoreFirstWhiteSpace()});

//        sign_edtEmailIDFirst.addTextChangedListener(new TextWatcher() {
//
//            @Override
//            public void onTextChanged(CharSequence arg0, int arg1, int arg2, int arg3) {
//
//            }
//            @Override
//            public void beforeTextChanged(CharSequence arg0, int arg1, int arg2,
//                                          int arg3) {
//            }
//            @Override
//            public void afterTextChanged(Editable et) {
//                String s=et.toString();
//                if(!s.equals(s.toLowerCase()))
//                {
//                    s=s.toLowerCase();
//                    sign_edtEmailIDFirst.setText(s);
//                    sign_edtEmailIDFirst.setSelection(sign_edtEmailIDFirst.length()); //fix reverse texting
//                }
//            }
//        });


//        sign_edtEmailID.setOnTouchListener(new View.OnTouchListener() {
//            @Override
//            public boolean onTouch(View v, MotionEvent event) {
//
//////                Constant.userNameList = DialogUtils.getArrayList("userlist",context);
////
////                    Constant.userNameList.add("A");
////                    Constant.userNameList.add("B");
////                    Constant.userNameList.add("C");
////
//////                if(Constant.userNameList != null && Constant.userNameList.size() > 0){
////
//
////                    MainActivity.getInstance().showEmailPopup(v);
////                }
//                return false;
//            }
//        });

        sign_confirm_password = sign_view.findViewById(R.id.confirm_password);
        sign_confirm_password.setInputType(InputType.TYPE_TEXT_FLAG_NO_SUGGESTIONS);
        sign_confirm_password.setFilters(new InputFilter[] { filter, new InputFilter.LengthFilter(15) });

        sign_confirm_input_layout_password = sign_view.findViewById(R.id.confirm_input_layout_password);

        //Edited by Janvi 4thOct *******
        tvRegisterToWishlist = sign_view.findViewById(R.id.tvRegisterToWishlist);


        if(!s.equals("") && s.equals("wishlist")){

            if (Constant.SCREEN_LAYOUT == 1) {
                sign_txtloginhead.setText(MainActivity.getInstance().getString(R.string.str_wishlistHeader));
                tvRegisterToWishlist.setVisibility(View.VISIBLE);
                tvRegisterToWishlist.setText(MainActivity.getInstance().getString(R.string.str_registerToWishList));
                sign_edtEmailID.setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, 0);
//                sign_edtEmailIDFirst.setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, 0);

            }else if (Constant.SCREEN_LAYOUT == 2) {
                sign_txtloginhead.setText(MainActivityDup.getInstance().getString(R.string.str_wishlistHeader));
                tvRegisterToWishlist.setVisibility(View.VISIBLE);
                tvRegisterToWishlist.setText(MainActivityDup.getInstance().getString(R.string.str_registerToWishList));
                sign_edtEmailID.setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, 0);
//                sign_edtEmailIDFirst.setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, 0);
            }
        }else{

            if (Constant.SCREEN_LAYOUT == 1) {
                sign_txtloginhead.setText(MainActivity.getInstance().getString(R.string.str_signinHeader));
                tvRegisterToWishlist.setVisibility(View.GONE);
                sign_edtEmailID.setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, 0);
//                sign_edtEmailIDFirst.setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, 0);

            }else if (Constant.SCREEN_LAYOUT == 2) {
                sign_txtloginhead.setText(MainActivityDup.getInstance().getString(R.string.str_signinHeader));
                tvRegisterToWishlist.setVisibility(View.GONE);
                sign_edtEmailID.setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, 0);
//                sign_edtEmailIDFirst.setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, 0);
            }

            if(!s.equals("") && !s.equals("cart")) {
//                sign_edtEmailID.setText(s);
                sign_edtEmailIDFirst.setText(s);
            }
        }
        //end ************

        sign_txt_forget_instruction.setVisibility(View.GONE);
        sign_txt_signin.setVisibility(View.GONE);
        sign_confirm_password.setVisibility(View.GONE);
        sign_confirm_input_layout_password.setVisibility(View.GONE);

        if (Constant.SCREEN_LAYOUT == 1) {
            sign_btnContinue.setText(MainActivity.getInstance().getString(R.string.str_next));
        } else if (Constant.SCREEN_LAYOUT == 2) {
            sign_btnContinue.setText(MainActivityDup.getInstance().getString(R.string.str_next));
        }

        GradientDrawable bgShape = (GradientDrawable) sign_btnPrev.getBackground();
        bgShape.setColor(Color.parseColor(Constant.themeModel.ThemeColor));


        sign_txtclose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loginDialog.dismiss();
//                Edited by Varun for lockup issue given by tom on 3 march
                UserModel.Cust_mst_ID = null;
//                END
            }
        });
        sign_txt_signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sign_edtEmailIDFirst.setFilters(new InputFilter[] {filter});
//                sign_edtEmailID.setFilters(new InputFilter[]{Utils.ignoreFirstWhiteSpace()});
//                sign_edtEmailIDFirst.setFilters(new InputFilter[]{Utils.ignoreFirstWhiteSpace()});

//                sign_edtEmailID.setCustomSelectionActionModeCallback(new ActionMode.Callback() {
//
//                    public boolean onPrepareActionMode(ActionMode mode, Menu menu) {
//                        return false;
//                    }
//
//                    public void onDestroyActionMode(ActionMode mode) {
//                    }
//
//                    public boolean onCreateActionMode(ActionMode mode, Menu menu) {
//                        return false;
//                    }
//
//                    public boolean onActionItemClicked(ActionMode mode, MenuItem item) {
//                        return false;
//                    }
//                });
//
//                sign_edtEmailID.setLongClickable(false);

                String CurrentTAG = sign_txt_signin.getText().toString();
                sign_edtEmailIDFirst.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
                    // edtEmailID.setTransformationMethod(PasswordTransformationMethod.getInstance());
                    txtPasswordHint.setVisibility(View.GONE);
                    if (Constant.SCREEN_LAYOUT == 1) {
                        sign_input_layout_empid_for_email.setHint(MainActivity.getInstance().getString(R.string.str_email));
                        if (CurrentTAG.contains(MainActivity.getInstance().getString(R.string.str_signin))) {
                            Tag = "signin";

//              Edited by Janvi 5th Oct ***************************
//                            sign_btnContinue.setText(MainActivity.getInstance().getString(R.string.str_next));
//                            sign_txtloginhead.setText(MainActivity.getInstance().getString(R.string.str_signinHeader));
//                            sign_txt_signin.setVisibility(View.GONE);
//                            sign_txt_recover.setVisibility(View.VISIBLE);
//                            sign_txt_forget_instruction.setVisibility(View.GONE);
//                            sign_edtEmailID.setText("");

                            if(sign_edtEmailIDFirst.getError()!=null && sign_edtEmailIDFirst.getText().length() == 0){
                                sign_edtEmailIDFirst.setError(null);
                            }

                            if(!s.equals("") && s.equals("wishlist")){
                                sign_btnContinue.setText(MainActivity.getInstance().getString(R.string.str_next));
                                sign_txtloginhead.setText(MainActivity.getInstance().getString(R.string.str_wishlistHeader));
                                tvRegisterToWishlist.setVisibility(View.VISIBLE);
                                tvRegisterToWishlist.setText(MainActivity.getInstance().getString(R.string.str_registerToWishList));
                                sign_txt_signin.setVisibility(View.GONE);
                                sign_txt_recover.setVisibility(View.VISIBLE);
                                sign_txt_forget_instruction.setVisibility(View.GONE);
//                                sign_edtEmailID.setText("");
                                sign_edtEmailIDFirst.setText("");
                                sign_edtEmailID.setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, 0);
                                sign_edtEmailIDFirst.setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, 0);


                            }else{
                                tvRegisterToWishlist.setVisibility(View.GONE);
                                sign_btnContinue.setText(MainActivity.getInstance().getString(R.string.str_next));
                                sign_txtloginhead.setText(MainActivity.getInstance().getString(R.string.str_signinHeader));
                                sign_txt_signin.setVisibility(View.GONE);
                                sign_txt_recover.setVisibility(View.VISIBLE);
                                sign_txt_forget_instruction.setVisibility(View.GONE);
//                                sign_edtEmailID.setText("");
                                sign_edtEmailIDFirst.setText("");
//                                sign_edtEmailID.setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, 0);
                                sign_edtEmailIDFirst.setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, 0);
                            }
//                            end *************************
                            //done
                            sign_btnPrev.setVisibility(View.GONE);
                        } else if (CurrentTAG.contains(MainActivity.getInstance().getString(R.string.str_forgetPassword))) {
                            Tag = "recover";
                            sign_btnContinue.setText(MainActivity.getInstance().getString(R.string.str_recover_passwrod));
                            sign_txtloginhead.setText(MainActivity.getInstance().getString(R.string.str_forgetPassword));
                            sign_txt_signin.setVisibility(View.VISIBLE);
                            sign_txt_forget_instruction.setText("Enter your Email and we will send you the Instructions.");
                            sign_txt_forget_instruction.setVisibility(View.VISIBLE);
                            sign_txt_recover.setVisibility(View.GONE);
//                            sign_edtEmailID.setText("");
                            sign_input_layout_empid_for_email.setVisibility(View.VISIBLE);
                            sign_edtEmailIDFirst.setText("");
                            sign_edtEmailIDFirst.requestFocus();
                            sign_input_layout_empid_for.setVisibility(View.GONE);
                            sign_btnPrev.setVisibility(View.GONE);
                            sign_edtEmailID.setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, 0);
//                            sign_edtEmailIDFirst.setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, 0);
                            sign_txt_signin.setText(MainActivity.getInstance().getString(R.string.str_signin));
                        //done
                        } else if (CurrentTAG.contains("Resend One Time Password")) {
                            isResentOTP = true;
//                            sign_txt_forget_instruction.setVisibility(View.GONE);
                            String Url;
                            if (Constant.LOGIN_TYPE.equalsIgnoreCase("accnotactive")) {
                                Url = Constant.WS_BASE_URL + Constant.GENERATE_OTP + EmailID + "/" + "SignIn/" + Constant.STOREID;
                            } else if (Constant.LOGIN_TYPE.equalsIgnoreCase("forgotpassword")) {
                                Url = Constant.WS_BASE_URL + Constant.GENERATE_OTP + EmailID + "/" + "forgotpassword/" + Constant.STOREID;
                            } else {
                                Url = Constant.WS_BASE_URL + Constant.GENERATE_OTP + EmailID + "/" + "SignInNew/" + Constant.STOREID;
                            }
                            new Async_getCommonService(MainActivity.getInstance(), Url).execute();
                            sign_edtEmailID.setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, 0);
//                            sign_edtEmailIDFirst.setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, 0);
                        } else {
                            //Resend Password logic come

                        }
//                        sign_btnPrev.setVisibility(View.GONE);
                    } else if (Constant.SCREEN_LAYOUT == 2) {

                        sign_input_layout_empid_for_email.setHint(MainActivityDup.getInstance().getString(R.string.str_email));
                        if (CurrentTAG.contains(MainActivityDup.getInstance().getString(R.string.str_signin))) {
                            Tag = "signin";

//              Edited by Janvi 5th Oct ***************************
//                            sign_btnContinue.setText(MainActivityDup.getInstance().getString(R.string.str_next));
//                            sign_txtloginhead.setText(MainActivityDup.getInstance().getString(R.string.str_signinHeader));
//                            sign_txt_signin.setVisibility(View.GONE);
//                            sign_txt_recover.setVisibility(View.VISIBLE);
//                            sign_txt_forget_instruction.setVisibility(View.GONE);
//                            sign_edtEmailID.setText("");


                            if(sign_edtEmailIDFirst.getError()!=null && sign_edtEmailIDFirst.getText().length() == 0){
                                sign_edtEmailIDFirst.setError(null);
                            }


                            if(!s.equals("") && s.equals("wishlist")){
                                sign_btnContinue.setText(MainActivityDup.getInstance().getString(R.string.str_next));
                                sign_txtloginhead.setText(MainActivityDup.getInstance().getString(R.string.str_wishlistHeader));
                                tvRegisterToWishlist.setVisibility(View.VISIBLE);
                                tvRegisterToWishlist.setText(MainActivityDup.getInstance().getString(R.string.str_registerToWishList));
                                sign_txt_signin.setVisibility(View.GONE);
                                sign_txt_recover.setVisibility(View.VISIBLE);
                                sign_txt_forget_instruction.setVisibility(View.GONE);
//                                sign_edtEmailID.setText("");
                                sign_edtEmailIDFirst.setText("");
                                sign_edtEmailID.setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, 0);
                                sign_edtEmailIDFirst.setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, 0);

                            }else{
                                sign_btnContinue.setText(MainActivityDup.getInstance().getString(R.string.str_next));
                                sign_txtloginhead.setText(MainActivityDup.getInstance().getString(R.string.str_signinHeader));
                                sign_txt_signin.setVisibility(View.GONE);
                                sign_txt_recover.setVisibility(View.VISIBLE);
                                sign_txt_forget_instruction.setVisibility(View.GONE);
//                                sign_edtEmailID.setText("");
//                                sign_edtEmailID.setText("");
                                sign_edtEmailIDFirst.setText("");
                                sign_edtEmailIDFirst.setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, 0);                  sign_edtEmailIDFirst.setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, 0);
                            }

                            sign_btnPrev.setVisibility(View.GONE);
              //end*******************************

                        } else if (CurrentTAG.contains(MainActivityDup.getInstance().getString(R.string.str_forgetPassword))) {
                            Tag = "recover";
                            sign_btnContinue.setText(MainActivityDup.getInstance().getString(R.string.str_recover_passwrod));
                            sign_txtloginhead.setText(MainActivityDup.getInstance().getString(R.string.str_forgetPassword));
                            sign_txt_signin.setVisibility(View.VISIBLE);
                            sign_txt_forget_instruction.setText("Enter your Email and we will send you the Instructions.");
                            sign_txt_forget_instruction.setVisibility(View.VISIBLE);
                            sign_txt_recover.setVisibility(View.GONE);
//                            sign_edtEmailID.setText("");
                            sign_input_layout_empid_for_email.setVisibility(View.VISIBLE);
                            sign_edtEmailIDFirst.setText("");
                            sign_edtEmailIDFirst.requestFocus();
                            sign_input_layout_empid_for.setVisibility(View.GONE);
                            sign_btnPrev.setVisibility(View.GONE);
                            sign_edtEmailID.setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, 0);
//                            sign_edtEmailIDFirst.setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, 0);
                            sign_txt_signin.setText(MainActivity.getInstance().getString(R.string.str_signin));
                        } else if (CurrentTAG.contains("Resend One Time Password")) {
//                            sign_txt_forget_instruction.setVisibility(View.GONE);
                            String Url;
                            if (Constant.LOGIN_TYPE.equalsIgnoreCase("accnotactive")) {
                                Url = Constant.WS_BASE_URL + Constant.GENERATE_OTP + EmailID + "/" + "SignIn/" + Constant.STOREID;
                            } else if (Constant.LOGIN_TYPE.equalsIgnoreCase("forgotpassword")) {
                                Url = Constant.WS_BASE_URL + Constant.GENERATE_OTP + EmailID + "/" + "forgotpassword/" + Constant.STOREID;
                            } else {
                                Url = Constant.WS_BASE_URL + Constant.GENERATE_OTP + EmailID + "/" + "SignInNew/" + Constant.STOREID;
                            }
                            sign_edtEmailID.setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, 0);
                            sign_edtEmailIDFirst.setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, 0);

                            new Async_getCommonService(MainActivityDup.getInstance(), Url).execute();

                            // CheckPassword();
                        } else {
                            //Resend Password logic come

                        }
//                        sign_btnPrev.setVisibility(View.GONE);
                    }

                    //Signin After

            }
        });
        sign_btnPrev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sign_input_layout_empid_for.setVisibility(View.GONE);
                sign_input_layout_empid_for_email.setVisibility(View.VISIBLE);

//                sign_edtEmailID.setFilters(new InputFilter[]{Utils.ignoreFirstWhiteSpace()});
//                sign_edtEmailIDFirst.setFilters(new InputFilter[]{Utils.ignoreFirstWhiteSpace()});
                sign_edtEmailIDFirst.setFilters(new InputFilter[] {filter});

                //for password visibility icons
                sign_edtEmailID.setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, 0);
                sign_edtEmailIDFirst.setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, 0);
                //end ///

                txtPasswordHint.setVisibility(View.GONE);
//                sign_edtEmailID.setInputType(InputType.TYPE_CLASS_TEXT);
                if (Constant.SCREEN_LAYOUT == 1) {
                    if (sign_txtloginhead.getText().toString().equalsIgnoreCase(MainActivity.getInstance().getString(R.string.str_enter_otp))) {
                        if (Constant.LOGIN_TYPE.equalsIgnoreCase("accnotactive")) {
                            Tag = "signin";
                            sign_input_layout_empid_for_email.setHint(MainActivity.getInstance().getString(R.string.str_email));
                            sign_btnContinue.setText(MainActivity.getInstance().getString(R.string.str_next));
                            sign_txtloginhead.setText(MainActivity.getInstance().getString(R.string.str_signinHeader));
                            sign_txt_signin.setVisibility(View.GONE);
                            sign_txt_recover.setVisibility(View.VISIBLE);
                            sign_txt_forget_instruction.setVisibility(View.GONE);
//                            sign_edtEmailID.setText(strGlobalEmail);
                            sign_edtEmailIDFirst.setText(strGlobalEmail);
                            sign_edtEmailIDFirst.requestFocus();
                            if(sign_edtEmailIDFirst.getVisibility() == View.VISIBLE) {
                                if (sign_edtEmailIDFirst.getText().toString().length() > 0) {
                                    sign_edtEmailIDFirst.setSelection(sign_edtEmailIDFirst.getText().length());
                                }
                            }
                            sign_btnPrev.setVisibility(View.GONE);
                            sign_edtEmailID.setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, 0);
                            sign_edtEmailIDFirst.setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, 0);
                        } else if (Constant.LOGIN_TYPE.equalsIgnoreCase("forgotpassword")) {
                            Tag = "recover";

                            sign_input_layout_empid_for.setVisibility(View.GONE);
                            sign_input_layout_empid_for_email.setVisibility(View.VISIBLE);
                            sign_edtEmailIDFirst.requestFocus();
                            if(sign_edtEmailIDFirst.getVisibility() == View.VISIBLE) {
                                if (sign_edtEmailIDFirst.getText().toString().length() > 0) {
                                    sign_edtEmailIDFirst.setSelection(sign_edtEmailIDFirst.getText().length());
                                }
                            }
                            sign_input_layout_empid_for_email.setHint(MainActivity.getInstance().getString(R.string.str_email));
                            sign_btnContinue.setText(MainActivity.getInstance().getString(R.string.str_recover_passwrod));
                            sign_txtloginhead.setText(MainActivity.getInstance().getString(R.string.str_forgetPassword));
                            sign_txt_signin.setVisibility(View.VISIBLE);
                            sign_txt_signin.setText(MainActivity.getInstance().getString(R.string.str_signin));
                            sign_txt_forget_instruction.setText("Enter your Email and we will send you the Instructions.");
                            sign_txt_forget_instruction.setVisibility(View.VISIBLE);
                            sign_txt_recover.setVisibility(View.GONE);
                            //sign_edtEmailID.setText(strGlobalEmail);
//                            sign_edtEmailID.setText("");
                            sign_edtEmailIDFirst.setText(strGlobalEmail);
                            sign_edtEmailIDFirst.requestFocus();
                            if(sign_edtEmailIDFirst.getVisibility() == View.VISIBLE) {
                                if (sign_edtEmailIDFirst.getText().toString().length() > 0) {
                                    sign_edtEmailIDFirst.setSelection(sign_edtEmailIDFirst.getText().length());
                                }
                            }
                            sign_btnPrev.setVisibility(View.GONE);
                            sign_edtEmailID.setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, 0);
//                            sign_edtEmailIDFirst.setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, 0);

                        }else if (Constant.LOGIN_TYPE.equalsIgnoreCase("newuser")) {

                            sign_input_layout_empid_for.setVisibility(View.GONE);
                            sign_input_layout_empid_for_email.setVisibility(View.VISIBLE);

                            sign_input_layout_empid_for_email.setHint(MainActivity.getInstance().getString(R.string.str_email));
                            sign_btnContinue.setText(MainActivity.getInstance().getString(R.string.str_next));
                            sign_txtloginhead.setText(MainActivity.getInstance().getString(R.string.str_signinHeader));
                            sign_txt_signin.setVisibility(View.GONE);
                            sign_txt_recover.setVisibility(View.VISIBLE);
                            sign_txt_forget_instruction.setVisibility(View.GONE);
//                        sign_edtEmailID.setText(strGlobalEmail);
                            sign_edtEmailIDFirst.setText(strGlobalEmail);
                            if(sign_edtEmailIDFirst.getVisibility() == View.VISIBLE) {
                                if (sign_edtEmailIDFirst.getText().toString().length() > 0) {
                                    sign_edtEmailIDFirst.setSelection(sign_edtEmailIDFirst.getText().length());
                                }
                            }
                            sign_btnPrev.setVisibility(View.GONE);
                        }else{

                        }
                        //done

                    } else if (sign_txtloginhead.getText().toString().equalsIgnoreCase(MainActivity.getInstance().getString(R.string.str_enterpassword))) {
                        Tag = "signin";
                        sign_input_layout_empid_for_email.setHint(MainActivity.getInstance().getString(R.string.str_email));
                        sign_btnContinue.setText(MainActivity.getInstance().getString(R.string.str_next));
                        sign_txtloginhead.setText(MainActivity.getInstance().getString(R.string.str_signinHeader));
                        sign_txt_signin.setVisibility(View.GONE);
                        sign_txt_recover.setVisibility(View.VISIBLE);
                        sign_txt_forget_instruction.setVisibility(View.GONE);
//                        sign_edtEmailID.setText(strGlobalEmail);
                        sign_edtEmailIDFirst.setText(strGlobalEmail);
                        if(sign_edtEmailIDFirst.getVisibility() == View.VISIBLE) {
                            if (sign_edtEmailIDFirst.getText().toString().length() > 0) {
                                sign_edtEmailIDFirst.setSelection(sign_edtEmailIDFirst.getText().length());
                            }
                        }
                        sign_btnPrev.setVisibility(View.GONE);
                        sign_edtEmailID.setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, 0);
                        sign_edtEmailIDFirst.setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, 0);
//                        sign_edtEmailIDFirst.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_open_eye, 0);


                    } else if (sign_txtloginhead.getText().toString().equalsIgnoreCase(MainActivity.getInstance().getString(R.string.str_hint_password))) {
                        setOTPDesign();
//                        sign_edtEmailID.setText("" + strGlobalEma il);
                        sign_edtEmailIDFirst.setText("" + strGlobalEmail);
                        if(sign_edtEmailIDFirst.getVisibility() == View.VISIBLE) {
                            if (sign_edtEmailIDFirst.getText().toString().length() > 0) {
                                sign_edtEmailIDFirst.setSelection(sign_edtEmailIDFirst.getText().length());
                            }
                        }
                        sign_confirm_password.setVisibility(View.GONE);
                        sign_confirm_input_layout_password.setVisibility(View.GONE);
                        sign_edtEmailID.setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, 0);
                        sign_edtEmailIDFirst.setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, 0);
//                        sign_edtEmailID.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_open_eye, 0);
                    }
                } else if (Constant.SCREEN_LAYOUT == 2) {
                    if (sign_txtloginhead.getText().toString().equalsIgnoreCase(MainActivityDup.getInstance().getString(R.string.str_enter_otp))) {
                        if (Constant.LOGIN_TYPE.equalsIgnoreCase("accnotactive")) {
                            Tag = "signin";
                            sign_input_layout_empid_for_email.setHint(MainActivityDup.getInstance().getString(R.string.str_email));
                            sign_btnContinue.setText(MainActivityDup.getInstance().getString(R.string.str_next));
                            sign_txtloginhead.setText(MainActivityDup.getInstance().getString(R.string.str_signinHeader));
                            sign_txt_signin.setVisibility(View.GONE);
                            sign_txt_recover.setVisibility(View.VISIBLE);
                            sign_txt_forget_instruction.setVisibility(View.GONE);
//                            sign_edtEmailID.setText(strGlobalEmail);
                            sign_edtEmailIDFirst.setText(strGlobalEmail);
                            if(sign_edtEmailIDFirst.getVisibility() == View.VISIBLE) {
                                if (sign_edtEmailIDFirst.getText().toString().length() > 0) {
                                    sign_edtEmailIDFirst.setSelection(sign_edtEmailIDFirst.getText().length());
                                }
                            }
                            sign_btnPrev.setVisibility(View.GONE);
                            sign_edtEmailID.setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, 0);
                            sign_edtEmailIDFirst.setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, 0);
                        } else if (Constant.LOGIN_TYPE.equalsIgnoreCase("forgotpassword")) {
                            Tag = "recover";
                            sign_input_layout_empid_for_email.setHint(MainActivityDup.getInstance().getString(R.string.str_email));
                            sign_btnContinue.setText(MainActivityDup.getInstance().getString(R.string.str_recover_passwrod));
                            sign_txtloginhead.setText(MainActivityDup.getInstance().getString(R.string.str_forgetPassword));
                            sign_txt_signin.setVisibility(View.VISIBLE);
                            sign_txt_signin.setText(MainActivityDup.getInstance().getString(R.string.str_signin));
                            sign_txt_forget_instruction.setText("Enter your Email and we will send you the Instructions.");
                            sign_txt_forget_instruction.setVisibility(View.VISIBLE);
                            sign_txt_recover.setVisibility(View.GONE);
                            //sign_edtEmailID.setText(strGlobalEmail);
//                            sign_edtEmailID.setText("");
                            sign_edtEmailIDFirst.setText(strGlobalEmail);
                            if(sign_edtEmailIDFirst.getVisibility() == View.VISIBLE) {
                                if (sign_edtEmailIDFirst.getText().toString().length() > 0) {
                                    sign_edtEmailIDFirst.setSelection(sign_edtEmailIDFirst.getText().length());
                                }
                            }
                            sign_btnPrev.setVisibility(View.GONE);
                            sign_edtEmailID.setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, 0);
//                            sign_edtEmailIDFirst.setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, 0);
                        } else {

                        }

                    } else if (sign_txtloginhead.getText().toString().equalsIgnoreCase(MainActivityDup.getInstance().getString(R.string.str_enterpassword))) {
                        Tag = "signin";
                        sign_input_layout_empid_for_email.setHint(MainActivityDup.getInstance().getString(R.string.str_email));
                        sign_btnContinue.setText(MainActivityDup.getInstance().getString(R.string.str_next));
                        sign_txtloginhead.setText(MainActivityDup.getInstance().getString(R.string.str_signinHeader));
                        sign_txt_signin.setVisibility(View.GONE);
                        sign_txt_recover.setVisibility(View.VISIBLE);
                        sign_txt_forget_instruction.setVisibility(View.GONE);
//                        sign_edtEmailID.setText(strGlobalEmail);
                        sign_edtEmailIDFirst.setText(strGlobalEmail);
                        if(sign_edtEmailIDFirst.getVisibility() == View.VISIBLE) {
                            if (sign_edtEmailIDFirst.getText().toString().length() > 0) {
                                sign_edtEmailIDFirst.setSelection(sign_edtEmailIDFirst.getText().length());
                            }
                        }
                        sign_btnPrev.setVisibility(View.GONE);
                        sign_edtEmailID.setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, 0);
                        sign_edtEmailIDFirst.setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, 0);
//                        sign_edtEmailIDFirst.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_open_eye, 0);

                    } else if (sign_txtloginhead.getText().toString().equalsIgnoreCase(MainActivityDup.getInstance().getString(R.string.str_hint_password))) {
                        setOTPDesign();
//                        sign_edtEmailID.setText("" + strGlobalEmail);
                        sign_edtEmailIDFirst.setText("" + strGlobalEmail);
                        if(sign_edtEmailIDFirst.getVisibility() == View.VISIBLE) {
                            if (sign_edtEmailIDFirst.getText().toString().length() > 0) {
                                sign_edtEmailIDFirst.setSelection(sign_edtEmailIDFirst.getText().length());
                            }
                        }
                        sign_confirm_password.setVisibility(View.GONE);
                        sign_confirm_input_layout_password.setVisibility(View.GONE);
                        sign_edtEmailID.setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, 0);
                        sign_edtEmailIDFirst.setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, 0);
//                        sign_edtEmailID.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_open_eye, 0);
                    }
                }

                //startSignupDialog();
            }
        });
        //done
        sign_txt_recover.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //Edited by Janvi 5th oct********
                tvRegisterToWishlist.setVisibility(View.GONE);
                //end *********

                Tag = "recover";

                if(sign_edtEmailIDFirst.getError()!=null && sign_edtEmailIDFirst.getText().length() == 0){
                    sign_edtEmailIDFirst.setError(null);
                }
                sign_edtEmailIDFirst.requestFocus();
                sign_edtEmailIDFirst.requestFocus();
                if(sign_edtEmailIDFirst.getVisibility() == View.VISIBLE) {
                    if (sign_edtEmailIDFirst.getText().toString().length() > 0) {
                        sign_edtEmailIDFirst.setSelection(sign_edtEmailIDFirst.getText().length());
                    }
                }

                if (Constant.SCREEN_LAYOUT == 1) {
                    sign_btnContinue.setText(MainActivity.getInstance().getString(R.string.str_recover_passwrod));
                    sign_txtloginhead.setText(MainActivity.getInstance().getString(R.string.str_forgetPassword));
                    sign_txt_signin.setVisibility(View.VISIBLE);
                    sign_txt_signin.setText(MainActivity.getInstance().getString(R.string.str_signin));
                    sign_txt_forget_instruction.setVisibility(View.VISIBLE);
                    sign_txt_recover.setVisibility(View.GONE);
//                    sign_edtEmailIDFirst.setText("");
                    sign_btnPrev.setVisibility(View.GONE);
//                    sign_edtEmailID.setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, 0);
                } else if (Constant.SCREEN_LAYOUT == 2) {
                    sign_btnContinue.setText(MainActivityDup.getInstance().getString(R.string.str_recover_passwrod));
                    sign_txtloginhead.setText(MainActivityDup.getInstance().getString(R.string.str_forgetPassword));
                    sign_txt_signin.setVisibility(View.VISIBLE);
                    sign_txt_signin.setText(MainActivityDup.getInstance().getString(R.string.str_signin));
                    sign_txt_forget_instruction.setVisibility(View.VISIBLE);
                    sign_txt_recover.setVisibility(View.GONE);
//                    sign_edtEmailIDFirst.setText("");
                    sign_btnPrev.setVisibility(View.GONE);
//                    sign_edtEmailID.setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, 0);
                }

            }
        });
        sign_btnContinue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Edited by Janvi 5th oct********

//                sign_edtEmailID.setVisibility(View.VISIBLE);
//                sign_edtEmailIDFirst.setVisibility(View.GONE);

//                sign_input_layout_empid_for.setVisibility(View.VISIBLE);
//                sign_input_layout_empid_for_email.setVisibility(View.GONE);
                tvRegisterToWishlist.setVisibility(View.GONE);

                //end *********
                sign_txtotperror.setVisibility(View.GONE);
                sign_llmiddle.setVisibility(View.VISIBLE);
                sign_confirm_password.setVisibility(View.GONE);
                sign_confirm_input_layout_password.setVisibility(View.GONE);
                txtPasswordHint.setVisibility(View.GONE);
                //if(Tag)

//                String email = sign_edtEmailID.getText().toString().trim();

                String lowerEmail = sign_edtEmailIDFirst.getText().toString().trim().toLowerCase();
                String email = lowerEmail;
                final String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";

                //Edited by Janvi 5th oct **********
                String str_signinHeader = null, str_recover_passwrod = null, str_Congratulation_indtruct = null, str_wishlist = null;
                if (Constant.SCREEN_LAYOUT == 1) {
                    str_wishlist = MainActivity.getInstance().getString(R.string.str_wishlistHeader);
                    str_signinHeader = MainActivity.getInstance().getString(R.string.str_signinHeader);
                    str_recover_passwrod = MainActivity.getInstance().getString(R.string.str_recover_passwrod);
                    str_Congratulation_indtruct = MainActivity.getInstance().getString(R.string.str_Congratulation_indtruct);
                    if (sign_txtloginhead.getText().toString().equalsIgnoreCase(str_signinHeader) || sign_txtloginhead.getText().toString().equalsIgnoreCase(str_wishlist)) {
                     //end *********************
//                        strGlobalEmail = sign_edtEmailID.getText().toString();
                        strGlobalEmail = sign_edtEmailIDFirst.getText().toString().toLowerCase();
                        if (email.matches(emailPattern) && email.length() > 0) {
                            EmailID = email;

                            Constant.Guest_Email = EmailID ;

//                            if(Constant.userNameList != null && Constant.userNameList.size() > 0) {
//                                if (!Constant.userNameList.contains(email)) {
//                                    Constant.userNameList.add(email);
//                                }
//                            }else{
//                                Constant.userNameList = new ArrayList<>();
//                                Constant.userNameList.add(email);
//                            }

//                            DialogUtils.saveArrayList("userlist", Constant.userNameList, mContext);
                            saveEmailToSharedPreferences(EmailID);

                            String Url = Constant.WS_BASE_URL + Constant.GENERATE_OTP + EmailID + "/" + "SignIn/" + Constant.STOREID;
                            new Async_getCommonService(MainActivity.getInstance(), Url).execute();
                            //dialog.dismiss();
                            //startSignupDialog();
                        } else {
//                            sign_edtEmailID.setError("Invalid email");
                            sign_edtEmailIDFirst.setError("Invalid email");
                        }

                        sign_edtEmailID.setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, 0);
//                        sign_edtEmailIDFirst.setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, 0);

                    //done
                    }
                    else if (sign_btnContinue.getText().toString().equalsIgnoreCase(str_recover_passwrod)) {

                        if(sign_edtEmailIDFirst.getError()!=null && sign_edtEmailIDFirst.getText().length() == 0){
                            sign_edtEmailIDFirst.setError(null);
                        }
                        sign_edtEmailIDFirst.requestFocus();
                        OtpFrom = "login";
                        Constant.LOGIN_TYPE = "forgotpassword";
//                        strGlobalEmail = sign_edtEmailID.getText().toString();
                        strGlobalEmail = sign_edtEmailIDFirst.getText().toString();
//                        if(!Constant.userNameList.contains(strGlobalEmail)){
//                            Constant.userNameList.add(strGlobalEmail);
//                        }
//                        EmailID = sign_edtEmailID.getText().toString();
                        EmailID = sign_edtEmailIDFirst.getText().toString();

                        if(EmailID.isEmpty()||EmailID.equals(" ")|| EmailID==null|| EmailID.equals("null")){
                            sign_edtEmailIDFirst.setError("Invalid email");
                        }else{

                            if (EmailID.matches(emailPattern) && EmailID.length() > 0) {
                                String Url = Constant.WS_BASE_URL + Constant.GENERATE_OTP + EmailID + "/" + "forgotpassword" + "/" + Constant.STOREID;
                                new Async_getCommonService(MainActivity.getInstance(), Url).execute();

                            } else {
                                sign_edtEmailIDFirst.setError("Invalid email");
                            }
                        }
                        //done

                        sign_edtEmailID.setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, 0);
//                        sign_edtEmailIDFirst.setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, 0);
                    }
                    else if (sign_txtloginhead.getText().toString().equalsIgnoreCase(MainActivity.getInstance().getString(R.string.str_enter_otp))) {

//                        strGlobalEmail = sign_edtEmailIDFirst.getText().toString();

                        if(!sign_edtEmailID.getText().toString().isEmpty()){
                            String enterdOtp = sign_edtEmailID.getText().toString();
                            String Url = Constant.WS_BASE_URL + Constant.CHECK_OTP + EmailID + "/" + Constant.LOGIN_TYPE + "/" + enterdOtp + "/" + Constant.STOREID;
                            new Async_getCommonService(MainActivity.getInstance(), Url).execute();
//
                        }else{
                            sign_edtEmailID.setError("Please enter otp");
                        }
//
                  /*  if (strGlobalEmail.equalsIgnoreCase(Constant.otpModel.OTP)) {
                        Log.e("Log","@@@@ OTPFROM="+OtpFrom);*/

//                        sign_edtEmailID.setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, 0);
//                        sign_edtEmailIDFirst.setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, 0);


                    }
                    else if (sign_txtloginhead.getText().toString().equalsIgnoreCase(MainActivity.getInstance().getString(R.string.str_oops))) {
                        setOTPDesign();
//                        sign_edtEmailID.setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, 0);
//                        sign_edtEmailIDFirst.setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, 0);
                    //done


                    }
                    else if (sign_txtloginhead.getText().toString().equalsIgnoreCase(str_Congratulation_indtruct)) {
                        loginDialog.dismiss();
//                        sign_edtEmailID.setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, 0);
                        sign_edtEmailIDFirst.setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, 0);

                    }
                    else if (sign_txtloginhead.getText().toString().equalsIgnoreCase(MainActivity.getInstance().getString(R.string.str_hint_password))) {

                        //baki
                        String Url;
                        String str_psw = sign_edtEmailID.getText().toString();
                        String str_cnf_psw = sign_confirm_password.getText().toString();
                        sign_confirm_password.setVisibility(View.VISIBLE);
                        sign_confirm_input_layout_password.setVisibility(View.VISIBLE);
                        if (str_psw.length() < 8) {
                            Utils.showValidationDialog(MainActivity.getInstance(), MainActivity.getInstance().getString(R.string.str_psw_validation1), "");
                        } else if (!str_cnf_psw.equals(str_psw)) {
                            Utils.showValidationDialog(MainActivity.getInstance(), MainActivity.getInstance().getString(R.string.str_psw_validation2), "");
                        } else {
                            //changePassword WS
//                            Edited by Varun for guest login for layout 2
                            if (Constant.Guest_WD.equalsIgnoreCase("guest")){
                                Constant.Guest_WD = "";
                                Constant.LOGIN_TYPE ="newuser";
                                 Url = Constant.WS_BASE_URL + Constant.CHANGE_PASSWORD + EmailID + "/" + str_psw + "/"+Constant.LOGIN_TYPE +"/" + Constant.STOREID;
                            }else {
                                 Url = Constant.WS_BASE_URL + Constant.CHANGE_PASSWORD + EmailID + "/" + str_psw + "/GET/" + Constant.STOREID;
                            }
//                            END
//                            Url = Constant.WS_BASE_URL + Constant.CHANGE_PASSWORD + EmailID + "/" + str_psw + "/GET/" + Constant.STOREID;
                            new Async_getCommonService(MainActivity.getInstance(), Url).execute();
                        }

                    }
                    else {
                        String loginPassword = sign_edtEmailID.getText().toString();
                        String Url = Constant.WS_BASE_URL + Constant.CHECK_PASSWORD + EmailID + "/" + loginPassword + "/" + Constant.STOREID;
                        //loooo
                        if(!s.equals("") && s.equals("cart")){
                            Constant.isCart= true;
                        }
                        new Async_getCommonService(MainActivity.getInstance(), Url).execute();
                    }
                } else if (Constant.SCREEN_LAYOUT == 2) {

                    //Edited by Janvi 5th oct **********
                    str_wishlist = MainActivityDup.getInstance().getString(R.string.str_wishlistHeader);
                    str_signinHeader = MainActivityDup.getInstance().getString(R.string.str_signinHeader);
                    str_recover_passwrod = MainActivityDup.getInstance().getString(R.string.str_recover_passwrod);
                    str_Congratulation_indtruct = MainActivityDup.getInstance().getString(R.string.str_Congratulation_indtruct);
                    if (sign_txtloginhead.getText().toString().equalsIgnoreCase(str_signinHeader) || sign_txtloginhead.getText().toString().equalsIgnoreCase(str_wishlist)) {
                        //end ************************
//                         strGlobalEmail = sign_edtEmailID.getText().toString();
                         strGlobalEmail = sign_edtEmailIDFirst.getText().toString().toLowerCase();
                        if (email.matches(emailPattern) && email.length() > 0) {
                            //Toast.makeText(getApplicationContext(),"valid email address",Toast.LENGTH_SHORT).show();
                            // or
                            //textView.setText("valid email");
                            EmailID = email;

                            Constant.Guest_Email = EmailID ;

                            String Url = Constant.WS_BASE_URL + Constant.GENERATE_OTP + EmailID + "/" + "SignIn/" + Constant.STOREID;
                            if (Constant.SCREEN_LAYOUT == 1) {
                                new Async_getCommonService(MainActivity.getInstance(), Url).execute();
                            } else if (Constant.SCREEN_LAYOUT == 2) {
                                new Async_getCommonService(MainActivityDup.getInstance(), Url).execute();
                            }

                            sign_edtEmailID.setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, 0);
//                            sign_edtEmailIDFirst.setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, 0);

                            //dialog.dismiss();
                            //startSignupDialog();
                        } else {
//                            sign_edtEmailID.setError("Invalid email");
                            sign_edtEmailIDFirst.setError("Invalid email");
                        }
                    } else if (sign_btnContinue.getText().toString().equalsIgnoreCase(str_recover_passwrod)) {

                        if(sign_edtEmailIDFirst.getError()!=null && sign_edtEmailIDFirst.getText().length() == 0){
                            sign_edtEmailIDFirst.setError(null);
                        }
                        OtpFrom = "login";
                        Constant.LOGIN_TYPE = "forgotpassword";
//                        strGlobalEmail = sign_edtEmailID.getText().toString();
                        strGlobalEmail = sign_edtEmailIDFirst.getText().toString();

//                        EmailID = sign_edtEmailID.getText().toString();
                        EmailID = sign_edtEmailIDFirst.getText().toString();

                        if(EmailID.isEmpty()||EmailID.equals(" ")|| EmailID==null|| EmailID.equals("null")){
                            sign_edtEmailIDFirst.setError("Invalid email");
                        }else{

                            if (EmailID.matches(emailPattern) && EmailID.length() > 0) {
                                String Url = Constant.WS_BASE_URL + Constant.GENERATE_OTP + EmailID + "/" + "forgotpassword" + "/" + Constant.STOREID;
                                new Async_getCommonService(MainActivityDup.getInstance(), Url).execute();

                            } else {
                                sign_edtEmailIDFirst.setError("Invalid email");
                            }
                        }
                        //done

                        sign_edtEmailID.setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, 0);
//


                    } else if (sign_txtloginhead.getText().toString().equalsIgnoreCase(MainActivityDup.getInstance().getString(R.string.str_enter_otp))) {
//                        strGlobalEmail = sign_edtEmailID.getText().toString();

                        if(!sign_edtEmailID.getText().toString().isEmpty()){
                            String enterdOtp = sign_edtEmailID.getText().toString();
                            String Url = Constant.WS_BASE_URL + Constant.CHECK_OTP + EmailID + "/" + Constant.LOGIN_TYPE + "/" + enterdOtp + "/" + Constant.STOREID;
                            new Async_getCommonService(MainActivityDup.getInstance(), Url).execute();
//
                        }else{
                            sign_edtEmailID.setError("Please enter otp");
                        }
//
                    } else if (sign_txtloginhead.getText().toString().equalsIgnoreCase(MainActivityDup.getInstance().getString(R.string.str_oops))) {
                        setOTPDesign();
                        sign_edtEmailID.setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, 0);
                        sign_edtEmailIDFirst.setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, 0);

                    } else if (sign_txtloginhead.getText().toString().equalsIgnoreCase(str_Congratulation_indtruct)) {
                        loginDialog.dismiss();
                        sign_edtEmailID.setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, 0);
                        sign_edtEmailIDFirst.setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, 0);

                    } else if (sign_txtloginhead.getText().toString().equalsIgnoreCase(MainActivityDup.getInstance().getString(R.string.str_hint_password))) {
                        String str_psw = sign_edtEmailID.getText().toString();
                        String str_cnf_psw = sign_confirm_password.getText().toString();
                        sign_confirm_password.setVisibility(View.VISIBLE);
                        sign_confirm_input_layout_password.setVisibility(View.VISIBLE);
                        sign_edtEmailID.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_open_eye, 0);
                        sign_edtEmailIDFirst.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_open_eye, 0);

                        if (str_psw.length() < 8) {
                            Utils.showValidationDialog(MainActivityDup.getInstance(), MainActivityDup.getInstance().getString(R.string.str_psw_validation1), "");
                        } else if (!str_cnf_psw.equals(str_psw)) {
                            Utils.showValidationDialog(MainActivityDup.getInstance(), MainActivityDup.getInstance().getString(R.string.str_psw_validation2), "");
                        } else {
                            //changePassword WS
                            String Url;
//                            Edited by Varun for guest login
                            if (Constant.Guest_WD.equalsIgnoreCase("guest")){
                                Constant.Guest_WD = "";
                                Constant.LOGIN_TYPE ="newuser";
                                Url = Constant.WS_BASE_URL + Constant.CHANGE_PASSWORD + EmailID + "/" + str_psw + "/"+Constant.LOGIN_TYPE +"/" + Constant.STOREID;
                            }else {
                                Url = Constant.WS_BASE_URL + Constant.CHANGE_PASSWORD + EmailID + "/" + str_psw + "/GET/" + Constant.STOREID;
                            }
//                            END
//                            String Url = Constant.WS_BASE_URL + Constant.CHANGE_PASSWORD + EmailID + "/" + str_psw + "/GET/" + Constant.STOREID;
                            new Async_getCommonService(MainActivityDup.getInstance(), Url).execute();
                        }

                    } else {
                        String loginPassword = sign_edtEmailID.getText().toString();
                        String Url = Constant.WS_BASE_URL + Constant.CHECK_PASSWORD + EmailID + "/" + loginPassword + "/" + Constant.STOREID;

                        if(!s.equals("") && s.equals("cart")){
                            Constant.isCart= true;
                        }
                        new Async_getCommonService(MainActivityDup.getInstance(), Url).execute();
                    }
                }

            }
        });

        WindowManager.LayoutParams params = loginDialog.getWindow().getAttributes();
        params.width = WindowManager.LayoutParams.WRAP_CONTENT;
        params.height = WindowManager.LayoutParams.WRAP_CONTENT;

        loginDialog.setContentView(sign_view);
        loginDialog.getWindow().setGravity(Gravity.TOP);

        WindowManager.LayoutParams layoutParam = loginDialog.getWindow().getAttributes();

        if (Constant.SCREEN_LAYOUT == 1) {
            layoutParam.y = (MainActivity.getStatusBarHeight(MainActivity.getInstance()))/* + (MainActivity.getInstance().getToolBarHeight()) + (MainActivity.getInstance().getToolBarHeight())*/; // bottom margin
        } else if (Constant.SCREEN_LAYOUT == 2) {
            layoutParam.y = (MainActivityDup.getStatusBarHeight(MainActivityDup.getInstance()))/* + (MainActivity.getInstance().getToolBarHeight()) + (MainActivity.getInstance().getToolBarHeight())*/; // bottom margin
        }
        loginDialog.getWindow().setAttributes(layoutParam);
        loginDialog.show();
    }

    public static void CongratulationDialog() {
        Constant.LHSLIDER_LIST.clear();
        Constant.AccountList.clear();
        Utils.getAccountList();
        if (Constant.SCREEN_LAYOUT == 1) {
            MainActivity.getInstance().mManage_expList.setVisibility(View.VISIBLE);
        } else if (Constant.SCREEN_LAYOUT == 2) {
            //MainActivity.getInstance().mManage_expList.setVisibility(View.VISIBLE);
        }

        Constant.LHSLIDER_LIST.put("My Account", Constant.AccountList);
        ArrayList<String> TitleList = new ArrayList<String>(Constant.LHSLIDER_LIST.keySet());
        Log.e("Log", "Ed-2");

        if (Constant.SCREEN_LAYOUT == 1) {
            MainActivity.getInstance().mManage_expList.setAdapter(new ExpandAdapter(MainActivity.getInstance(), Constant.LHSLIDER_LIST, TitleList));
        } else if (Constant.SCREEN_LAYOUT == 2) {
            //MainActivityDup.getInstance().mManage_expList.setAdapter(new ExpandAdapter(MainActivityDup.getInstance(), Constant.LHSLIDER_LIST, TitleList));
        }
        sign_txtotperror.setVisibility(View.VISIBLE);
        sign_txt_forget_instruction.setVisibility(View.GONE);
        sign_llmiddle.setVisibility(View.GONE);
        sign_btnContinue.setText(MainActivity.getInstance().getString(R.string.str_OK));
        //sign_btnContinue.set
        sign_txtotperror.setText(MainActivity.getInstance().getString(R.string.str_Congratulation_indtruct));

        sign_txtloginhead.setText(MainActivity.getInstance().getString(R.string.str_Congratulation));
        sign_txt_signin.setVisibility(View.INVISIBLE);
        sign_btnContinue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loginDialog.dismiss();
            }
        });
    }

    //done
    public static void setOTPDesign() {
//        Constant.isFromLogin = true;
//        sign_input_layout_empid_for.setVisibility(View.VISIBLE);
//        sign_edtEmailID.setVisibility(View.VISIBLE);
        sign_input_layout_empid_for_email.setVisibility(View.GONE);
        sign_input_layout_empid_for.setVisibility(View.VISIBLE);
        sign_edtEmailID.requestFocus();
        sign_edtEmailIDFirst.requestFocus();
        if(sign_edtEmailIDFirst.getVisibility() == View.VISIBLE) {
            if (sign_edtEmailIDFirst.getText().toString().length() > 0) {
                sign_edtEmailIDFirst.setSelection(sign_edtEmailIDFirst.getText().length());
            }
        }

        if(sign_edtEmailID.getVisibility() == View.VISIBLE) {
            if (sign_edtEmailID.getText().toString().length() > 0) {
                sign_edtEmailID.setSelection(sign_edtEmailID.getText().length());
            }
        }
        if(sign_edtEmailIDFirst.getError()!=null && sign_edtEmailIDFirst.getText().length() == 0){
            sign_edtEmailIDFirst.setError(null);
        }

        if(sign_edtEmailID.getError()!=null && sign_edtEmailID.getText().length() == 0){
            sign_edtEmailID.setError(null);
        }


        if (Constant.SCREEN_LAYOUT == 1) {
            sign_edtEmailID.requestFocus();
            sign_txtloginhead.setText(MainActivity.getInstance().getString(R.string.str_enter_otp));
            sign_txt_forget_instruction.setText(MainActivity.getInstance().getString(R.string.str_otp_ins) + Login.strGlobalEmail);
            sign_txt_forget_instruction.setVisibility(View.VISIBLE);
            sign_edtEmailID.setInputType(InputType.TYPE_CLASS_NUMBER);
            sign_edtEmailID.setText("");
            sign_input_layout_empid_for.setHint(MainActivity.getInstance().getString(R.string.str_otp));
            sign_btnPrev.setVisibility(View.VISIBLE);
            sign_btnPrev.setText(MainActivity.getInstance().getString(R.string.str_prev));
            sign_txt_recover.setVisibility(View.GONE);
            sign_txt_signin.setVisibility(View.VISIBLE);
            sign_btnContinue.setText(MainActivity.getInstance().getString(R.string.str_next));
            sign_btnContinue.setVisibility(View.VISIBLE);
//            sign_edtEmailID.setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, 0);
            sign_txt_signin.setText(Html.fromHtml(MainActivity.getInstance().getString(R.string.str_resend_otp)));
        } else if (Constant.SCREEN_LAYOUT == 2) {
            sign_edtEmailID.requestFocus();
            sign_txtloginhead.setText(MainActivityDup.getInstance().getString(R.string.str_enter_otp));
            sign_txt_forget_instruction.setText(MainActivityDup.getInstance().getString(R.string.str_otp_ins) + Login.strGlobalEmail);
            sign_txt_forget_instruction.setVisibility(View.VISIBLE);
            sign_edtEmailID.setInputType(InputType.TYPE_CLASS_NUMBER);
            sign_edtEmailID.setText("");
            sign_input_layout_empid_for.setHint(MainActivityDup.getInstance().getString(R.string.str_otp));
            sign_btnPrev.setVisibility(View.VISIBLE);
            sign_btnPrev.setText(MainActivityDup.getInstance().getString(R.string.str_prev));
            sign_txt_recover.setVisibility(View.GONE);
            sign_txt_signin.setVisibility(View.VISIBLE);
//            sign_edtEmailID.setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, 0);
            sign_btnContinue.setText(MainActivityDup.getInstance().getString(R.string.str_next));
            sign_btnContinue.setVisibility(View.VISIBLE);
            sign_txt_signin.setText(Html.fromHtml(MainActivityDup.getInstance().getString(R.string.str_resend_otp)));
        }

    }

    public static void CheckPassword() {
//        Constant.isFromLogin = true;

        ///////////////////////////////

//        if (sign_txtloginhead.getText().toString().equalsIgnoreCase(MainActivity.getInstance().getString(R.string.str_enterpassword))){
//            sign_edtEmailID.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_open_eye, 0);
            passwordVisibility();
//        }

//        sign_edtEmailID.setFilters(new InputFilter[] {});

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

        sign_edtEmailID.setFilters(new InputFilter[] { filter, new InputFilter.LengthFilter(15) });

        //end ************

        sign_btnPrev.setVisibility(View.VISIBLE);
        sign_input_layout_empid_for.setVisibility(View.VISIBLE);
        sign_edtEmailID.requestFocus();
        sign_input_layout_empid_for_email.setVisibility(View.GONE);

        if (Constant.SCREEN_LAYOUT == 1) {
            sign_txtloginhead.setText(MainActivity.getInstance().getString(R.string.str_enterpassword));
            sign_edtEmailID.setInputType(InputType.TYPE_TEXT_VARIATION_PASSWORD);
            sign_btnContinue.setText(MainActivity.getInstance().getString(R.string.str_cap_signin));
            sign_txt_signin.setText(MainActivity.getInstance().getString(R.string.str_forgetPassword));
            sign_txt_recover.setVisibility(View.GONE);
            sign_txt_signin.setVisibility(View.VISIBLE);
            sign_edtEmailID.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
            sign_edtEmailID.setTransformationMethod(PasswordTransformationMethod.getInstance());
            sign_input_layout_empid_for.setHint(MainActivity.getInstance().getString(R.string.str_hint_password));
            sign_edtEmailID.setText("");

        } else if (Constant.SCREEN_LAYOUT == 2) {
            sign_txtloginhead.setText(MainActivityDup.getInstance().getString(R.string.str_enterpassword));
            sign_edtEmailID.setInputType(InputType.TYPE_TEXT_VARIATION_PASSWORD);
            sign_btnContinue.setText(MainActivityDup.getInstance().getString(R.string.str_cap_signin));
            sign_txt_signin.setText(MainActivityDup.getInstance().getString(R.string.str_forgetPassword));
            sign_txt_recover.setVisibility(View.GONE);
            sign_txt_signin.setVisibility(View.VISIBLE);
            sign_edtEmailID.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
            sign_edtEmailID.setTransformationMethod(PasswordTransformationMethod.getInstance());
            sign_input_layout_empid_for.setHint(MainActivityDup.getInstance().getString(R.string.str_hint_password));
            sign_edtEmailID.setText("");

        }
        Log.e("test","test");
    }

    private static void passwordVisibility() {

        //for password visibility icons
        sign_edtEmailID.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_open_eye, 0);
        isHidden = true;
        sign_edtEmailID.setCompoundDrawablePadding(25);

        try {
        sign_edtEmailID.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                final int DRAWABLE_LEFT = 0;
                final int DRAWABLE_TOP = 1;
                final int DRAWABLE_RIGHT = 2;
                final int DRAWABLE_BOTTOM = 3;

                    if (sign_edtEmailID.getCompoundDrawables()[DRAWABLE_RIGHT].getBounds() != null) {

                        if (event.getAction() == MotionEvent.ACTION_UP) {
                            if (event.getRawX() >= (sign_edtEmailID.getRight() - sign_edtEmailID.getCompoundDrawables()[DRAWABLE_RIGHT].getBounds().width())) {
                                // your action here

                                if (isHidden) {
                                    sign_edtEmailID.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_close_eye, 0);
                                    sign_edtEmailID.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                                    sign_edtEmailID.setSelection(sign_edtEmailID.getText().length());
                                    isHidden = false;

                                } else if (!isHidden) {
                                    sign_edtEmailID.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_open_eye, 0);
                                    sign_edtEmailID.setTransformationMethod(PasswordTransformationMethod.getInstance());
                                    sign_edtEmailID.setSelection(sign_edtEmailID.getText().length());
                                    isHidden = true;
                                }

//                      Drawable emailDrawable = mContext.getResources().getDrawable(R.drawable.ic_email);
//
//                        if(sign_edtEmailID.getDrawableState().equals(R.drawable.ic_camera)){
//                            sign_edtEmailID.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_email, 0);
//                        }else if(sign_edtEmailID.getDrawableState().equals(R.drawable.ic_email)){
//                            sign_edtEmailID.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_camera, 0);
//                        }

//                        Drawable[] compoundDrawables = sign_edtEmailID.getCompoundDrawables();
//                        Drawable topCompoundDrawable = compoundDrawables[1];
//
//                        if(topCompoundDrawable.equals(R.drawable.ic_camera)){
//                            sign_edtEmailID.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_email, 0);
//                        }else if(topCompoundDrawable.equals(R.drawable.ic_email)){
//                            sign_edtEmailID.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_camera, 0);
//                        }

                                return true;
                            }
                        }

                    }


                return false;
            }
        });
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public static void startSignupDialog() {
//        Constant.isFromLogin = true;
        if (loginDialog.isShowing()) {
            loginDialog.dismiss();
        }
        View view = null;
                if (Constant.SCREEN_LAYOUT == 1) {
            Signupdialog = new Dialog(MainActivity.getInstance(), R.style.DialogSlideAnim_Signup);
            view = LayoutInflater.from(MainActivity.getInstance()).inflate(R.layout.sign_up_dialog, null);
        } else if (Constant.SCREEN_LAYOUT == 2) {
            Signupdialog = new Dialog(MainActivityDup.getInstance(), R.style.DialogSlideAnim_Signup);
            view = LayoutInflater.from(MainActivityDup.getInstance()).inflate(R.layout.sign_up_dialog, null);
        }
        final EditText edt_cnf_email = view.findViewById(R.id.edt_cnf_email);

        edt_cnf_email.setFilters(new InputFilter[]{Utils.filter});
        TextView txtEmailDisp = view.findViewById(R.id.txtEmailDisp);

        txtEmailDisp.setText(strGlobalEmail);
        final EditText edfirstname = view.findViewById(R.id.edfirstname);
        final EditText edtlastname = view.findViewById(R.id.edtlastname);
        final EditText edtCompanyname = view.findViewById(R.id.edtCompanyname);
        //final EditText edtaddress1 = (EditText) view.findViewById(R.id.edtaddress1);
        autotxtAddress1 = view.findViewById(R.id.autotxtAddress1);
        edtaddress2 = view.findViewById(R.id.edtaddress2);

//        Edited by Vrun for guest login

        CheckBox checkBox ;
        LinearLayout ll_guest, ll_password ;
        TextView tv_tell_me_more ,bottomLable1;


        checkBox= view.findViewById(R.id.checkbox_guest);
        ll_guest = view.findViewById(R.id.ll_guest);
        ll_password = view.findViewById(R.id.ll_password);
        tv_tell_me_more = view.findViewById(R.id.tell_me_more);
        bottomLable1= view.findViewById(R.id.bottomLable1);

        if (Constant.twentyOneYear.getEnableGuestCheckout()){
            ll_guest.setVisibility(View.VISIBLE);
        }else{
            ll_guest.setVisibility(View.GONE);
        }

        tv_tell_me_more.setTextColor(Color.parseColor(Constant.themeModel.ThemeColor));

        tv_tell_me_more.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogUtils.guest_tell_me_more("signup");
            }
        });

        checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(buttonView.isChecked()){
                    ll_password.setVisibility(View.GONE);
                    bottomLable1.setVisibility(View.GONE);

                } else {
                    ll_password.setVisibility(View.VISIBLE);
                    bottomLable1.setVisibility(View.VISIBLE);

                }
            }
        });

//        END

        edtzip = view.findViewById(R.id.edtzip);
        edtzip.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (charSequence.length() == 5) {
                    //http://192.168.172.211:889/WebStoreRestService.svc/GetZipCode/11001
                    String Url = Constant.WS_BASE_URL + Constant.GET_ZIP_CODE + charSequence.toString();
                    if (Constant.SCREEN_LAYOUT == 1) {
                        new Async_getCommonService(MainActivity.getInstance(), Url).execute();
                    } else if (Constant.SCREEN_LAYOUT == 2) {
                        new Async_getCommonService(MainActivityDup.getInstance(), Url).execute();
                    }

                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        edtcity = view.findViewById(R.id.edtcity);

        edtstate = view.findViewById(R.id.edtstate);
        edtstate.addTextChangedListener(new TextWatcher() {

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
                    edtstate.setText(s);
                    edtstate.setSelection(edtstate.length()); //fix reverse texting
                }
            }
        });


        final EditText edtMobile = view.findViewById(R.id.edtMobile);

        edtMobile.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {

                if(!hasFocus) {

                    if(!edtMobile.getText().toString().isEmpty() && !edtMobile.getText().toString().equals("")) {

                        String numberStr = edtMobile.getText().toString();

                        for (int i= 0; i<numberStr.length();i++){
                            if(numberStr.charAt(i)== '(' || numberStr.charAt(i)== ')' ||
                                    numberStr.charAt(i)== '-'){
                                numberStr = numberStr.replace(numberStr.charAt(i),' ');
                            }
                        }
                        numberStr = numberStr.replaceAll("\\s", "");

                        String number = numberStr.replaceFirst("(\\d{3})(\\d{3})(\\d+)", "($1) $2-$3");

                        edtMobile.setText(number);
                    }
                }
            }
        });

        edtMobile.addTextChangedListener(new TextWatcher() {

            String lastChar = "";
            int length_before = 0;
            String temp;

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

//                if (edtMobile.getText().charAt(edtMobile.length()-5) != ' ') {
//                    String sa = edtMobile.getText().toString();
//                    String last = edtMobile.getText().toString();
//                    String lastChar = last.substring(last.length() - 1);
//                    sa.replace(lastChar," ");
//                }
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count,
                                          int after) {
                length_before = s.length();
               /* int digits = edtMobile.getText().toString().length();
                if (digits > 1)
                    lastChar = edtMobile.getText().toString().substring(digits-1);
*/
            }

            @Override
            public void afterTextChanged(Editable s) {
                //lastChar = String.valueOf(s);
                if (length_before < edtMobile.length()) {
                    //temp = edtMobile.getText().toString();
                    if (edtMobile.length() == 1) {
                        edtMobile.setText("(" + s);
                        edtMobile.setSelection(2);
                    } else if (edtMobile.length() == 5) {
                        //edtMobile.setText("");

                            String last = edtMobile.getText().toString();
                            last = last.substring(last.length() - 1);

                            edtMobile.setText(temp + ") " + last);
                            edtMobile.setSelection(7);

//                        if(edtMobile.length() == 6){
//
//                            String last = edtMobile.getText().toString();
//                            last = last.substring(last.length() - 1);
//
//                            if(!last.equals(" ")){
//                                String txt = edtMobile.getText().toString().replace(last," ");
//
//                                edtMobile.setText(txt + last);
//                                edtMobile.setSelection(7);
//                            }
//                        }
                    }
//                    else if(edtMobile.length() == 6){
//
//                        String last = edtMobile.getText().toString();
//                            last = last.substring(last.length() - 1);
//
//                            if(!last.equals(" ")) {
//
//                                String lastChar = last.substring(last.length() - 1);
//                                edtMobile.setText(temp + " " + lastChar);
//                                edtMobile.setSelection(7);
//                            }
//                    }
                    else if (edtMobile.length() == 10) {
                        String last = edtMobile.getText().toString();
                        last = last.substring(last.length() - 1);

                        edtMobile.setText(temp + "-" + last);
                        //edtMobile.setText(s + "-");
                        edtMobile.setSelection(11);
                    } else {
                        temp = edtMobile.getText().toString();
                        if(!s.equals("") && s.equals("cart")){
                            edtMobile.setText(s);
                            edtMobile.setSelection(edtMobile.length());
                        }
                    }

//                    String fullstr = edtMobile.getText().toString();
//                    String s2 = "";
//
//                    if(fullstr.length()>=12) {
//                        Character c = fullstr.charAt(4);
//                        if (c != ')') {
//                            s2 = fullstr.substring(0, 4) + ")" + fullstr.substring(5);
//                        }
//
//                        Character c1 = fullstr.charAt(5);
//                        if(c1 != ' '){
//                            s2 = fullstr.substring(0, 5) + " " + fullstr.substring(6);
//                        }
//
//                        Character c2 = fullstr.charAt(9);
//                        if(c2 != '-'){
//                            s2 = s2.substring(0, 9) + "-" + s2.substring(10);
//                        }
//
//                        if(!s2.equals("")){
//                            edtMobile.setText(s2);
//                            edtMobile.setSelection(edtMobile.length());
//                        }
//                    }

                }

//                if(edtMobile.length() >= 10){
//                    PhoneNumberUtils phoneUtil = new PhoneNumberUtils();
//                    try {
//                        String numberProto = phoneUtil.parse(numberStr, "US");
//                        //Since you know the country you can format it as follows:
//
//                        String str = phoneUtil.format(numberProto, PhoneNumberFormat.NATIONAL));
//                    } catch (NumberFormatException e) {
//                        System.err.println("NumberParseException was thrown: " + e.toString());
//                    }
//                }

            }
        });

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

        final EditText signup_edtpassword = view.findViewById(R.id.edtpassword);
        final EditText signup_edtcnfPassword = view.findViewById(R.id.edtcnfPassword);

        signup_edtpassword.setFocusable(false);
        signup_edtpassword.setFocusableInTouchMode(true);

        signup_edtcnfPassword.setFocusable(false);
        signup_edtcnfPassword.setFocusableInTouchMode(true);

        signup_edtpassword.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(!hasFocus){
                    signup_edtpassword.setCursorVisible(false);
                }else{
                    signup_edtpassword.setCursorVisible(true);
                    signup_edtpassword.setSelection(signup_edtpassword.getText().length());
                }
            }
        });

        signup_edtpassword.setFilters(new InputFilter[] { filter, new InputFilter.LengthFilter(15) });

        signup_edtpassword.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_open_eye, 0);
        isHiddenSignup = true;
        signup_edtpassword.setCompoundDrawablePadding(25);

        signup_edtpassword.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                final int DRAWABLE_LEFT = 0;
                final int DRAWABLE_TOP = 1;
                final int DRAWABLE_RIGHT = 2;
                final int DRAWABLE_BOTTOM = 3;

                if(signup_edtpassword.getCompoundDrawables()[DRAWABLE_RIGHT].getBounds()!= null){

                    if(event.getAction() == MotionEvent.ACTION_UP) {
                        if(event.getRawX() >= (signup_edtpassword.getRight() - signup_edtpassword.getCompoundDrawables()[DRAWABLE_RIGHT].getBounds().width())) {
                            // your action here

                            if(isHiddenSignup){
                                signup_edtpassword.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_close_eye, 0);
                                signup_edtpassword.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                                signup_edtpassword.setSelection(signup_edtpassword.getText().length());
                                isHiddenSignup = false;

                            }else if(!isHiddenSignup){
                                signup_edtpassword.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_open_eye, 0);
                                signup_edtpassword.setTransformationMethod(PasswordTransformationMethod.getInstance());
                                signup_edtpassword.setSelection(signup_edtpassword.getText().length());
                                isHiddenSignup = true;
                            }
                            return true;
                        }
                    }

                }

                return false;
            }
        });



        signup_edtcnfPassword.setFilters(new InputFilter[] { filter, new InputFilter.LengthFilter(15) });

        signup_edtcnfPassword.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_open_eye, 0);
        isHiddenSignupConfirm = true;
        signup_edtcnfPassword.setCompoundDrawablePadding(25);

        signup_edtcnfPassword.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(!hasFocus){
                    signup_edtcnfPassword.setCursorVisible(false);
                }else{
                    signup_edtcnfPassword.setCursorVisible(true);
                    signup_edtcnfPassword.setSelection(signup_edtcnfPassword.getText().length());
                }
            }
        });

        signup_edtcnfPassword.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                final int DRAWABLE_LEFT = 0;
                final int DRAWABLE_TOP = 1;
                final int DRAWABLE_RIGHT = 2;
                final int DRAWABLE_BOTTOM = 3;

                if(signup_edtcnfPassword.getCompoundDrawables()[DRAWABLE_RIGHT].getBounds()!= null){

                    if(event.getAction() == MotionEvent.ACTION_UP) {
                        if(event.getRawX() >= (signup_edtcnfPassword.getRight() - signup_edtcnfPassword.getCompoundDrawables()[DRAWABLE_RIGHT].getBounds().width())) {
                            // your action here

                            signup_edtcnfPassword.requestFocus();
                            if(isHiddenSignupConfirm){
                                signup_edtcnfPassword.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_close_eye, 0);
                                signup_edtcnfPassword.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                                signup_edtcnfPassword.setSelection(signup_edtcnfPassword.getText().length());
                                isHiddenSignupConfirm = false;

                            }else if(!isHiddenSignupConfirm){
                                signup_edtcnfPassword.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_open_eye, 0);
                                signup_edtcnfPassword.setTransformationMethod(PasswordTransformationMethod.getInstance());
                                signup_edtcnfPassword.setSelection(signup_edtcnfPassword.getText().length());
                                isHiddenSignupConfirm = true;
                            }
                            return true;
                        }
                    }

                }

                return false;
            }
        });

        Button btnPrevious = view.findViewById(R.id.btnPrevious);
        Button btncreateAccount = view.findViewById(R.id.btncreateAccount);

       /* btnPrevious.setBackgroundColor(Color.parseColor(Constant.themeModel.ThemeColor));
        btncreateAccount.setBackgroundColor(Color.parseColor(Constant.themeModel.ThemeColor));*/

        GradientDrawable prevbgShape = (GradientDrawable) btnPrevious.getBackground();
        prevbgShape.setColor(Color.parseColor(Constant.themeModel.ThemeColor));

        GradientDrawable btncreatebgShape = (GradientDrawable) btncreateAccount.getBackground();
        btncreatebgShape.setColor(Color.parseColor(Constant.themeModel.ThemeColor));
        if (Constant.SCREEN_LAYOUT == 1) {
            edt_cnf_email.setHint(Html.fromHtml(MainActivity.getInstance().getString(R.string.confirm_email)));
            edfirstname.setHint(Html.fromHtml(MainActivity.getInstance().getString(R.string.str_firstname)));
            edtlastname.setHint(Html.fromHtml(MainActivity.getInstance().getString(R.string.str_lastname)));
            edtCompanyname.setHint(Html.fromHtml(MainActivity.getInstance().getString(R.string.str_companyname)));
            autotxtAddress1.setHint(Html.fromHtml(MainActivity.getInstance().getString(R.string.str_addressline1)));
            edtaddress2.setHint(Html.fromHtml(MainActivity.getInstance().getString(R.string.str_addressline2)));
            edtzip.setHint(Html.fromHtml(MainActivity.getInstance().getString(R.string.str_zip)));
            edtcity.setHint(Html.fromHtml(MainActivity.getInstance().getString(R.string.str_city)));

            edtstate.setHint(Html.fromHtml(MainActivity.getInstance().getString(R.string.str_state)));
            edtMobile.setHint(Html.fromHtml(MainActivity.getInstance().getString(R.string.str_mobile)));
            signup_edtpassword.setHint(Html.fromHtml(MainActivity.getInstance().getString(R.string.str_password)));
            signup_edtcnfPassword.setHint(Html.fromHtml(MainActivity.getInstance().getString(R.string.str_cnfpassword)));
        } else if (Constant.SCREEN_LAYOUT == 2) {
            edt_cnf_email.setHint(Html.fromHtml(MainActivityDup.getInstance().getString(R.string.confirm_email)));
            edfirstname.setHint(Html.fromHtml(MainActivityDup.getInstance().getString(R.string.str_firstname)));
            edtlastname.setHint(Html.fromHtml(MainActivityDup.getInstance().getString(R.string.str_lastname)));
            edtCompanyname.setHint(Html.fromHtml(MainActivityDup.getInstance().getString(R.string.str_companyname)));
            autotxtAddress1.setHint(Html.fromHtml(MainActivityDup.getInstance().getString(R.string.str_addressline1)));
            edtaddress2.setHint(Html.fromHtml(MainActivityDup.getInstance().getString(R.string.str_addressline2)));
            edtzip.setHint(Html.fromHtml(MainActivityDup.getInstance().getString(R.string.str_zip)));
            edtcity.setHint(Html.fromHtml(MainActivityDup.getInstance().getString(R.string.str_city)));

            edtstate.setHint(Html.fromHtml(MainActivityDup.getInstance().getString(R.string.str_state)));
            edtMobile.setHint(Html.fromHtml(MainActivityDup.getInstance().getString(R.string.str_mobile)));
            signup_edtpassword.setHint(Html.fromHtml(MainActivityDup.getInstance().getString(R.string.str_password)));
            signup_edtcnfPassword.setHint(Html.fromHtml(MainActivityDup.getInstance().getString(R.string.str_cnfpassword)));
        }


        TextView txtclose = view.findViewById(R.id.txtclose);
        txtclose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Signupdialog.dismiss();
//                Edited by Varun for lockup issue given by tom on 3 march
                UserModel.Cust_mst_ID = null;
//                END
//                Clear_Id();
            }
        });
        WindowManager.LayoutParams params = Signupdialog.getWindow().getAttributes();
        params.width = WindowManager.LayoutParams.WRAP_CONTENT;
        params.height = WindowManager.LayoutParams.WRAP_CONTENT;


        Signupdialog.getWindow().setGravity(Gravity.TOP);
        if (Constant.SCREEN_LAYOUT == 1) {
            params.y = (MainActivity.getStatusBarHeight(MainActivity.getInstance()) /** 2*/); // top margin
        } else if (Constant.SCREEN_LAYOUT == 2) {
            params.y = (MainActivityDup.getStatusBarHeight(MainActivityDup.getInstance()) /** 2*/); // top margin
        }

        Signupdialog.getWindow().setAttributes(params);
        Signupdialog.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE );


        //Log.e("Log", "Toolbarheight=" + MainActivity.getInstance().getToolBarHeight());

        autotxtAddress1.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                String Name = "" + editable;
                if (Name.trim().length() > 0) {
                    Name = Name.replaceAll(" ", "%20");
                    if (isSetAddress) {
                        if (Constant.SCREEN_LAYOUT == 1) {
                            new Async_getCommonService(MainActivity.getInstance(), Constant.MAP_API_URL + Constant.INPUT + Name + "&key=" + MainActivity.getInstance().getString(R.string.Place_API_key)).execute();
                        } else if (Constant.SCREEN_LAYOUT == 2) {
                            new Async_getCommonService(MainActivityDup.getInstance(), Constant.MAP_API_URL + Constant.INPUT + Name + "&key=" + MainActivityDup.getInstance().getString(R.string.Place_API_key)).execute();
                        }
                    }
                }

            }
        });

        btnPrevious.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Signupdialog.dismiss();
                Login.StartLoginDialog("" + strGlobalEmail, mContext);
            }
        });
        btncreateAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //dialog.dismiss();

                String password = signup_edtpassword.getText().toString();
                String conf_password = signup_edtcnfPassword.getText().toString();

                String strfirstname = edfirstname.getText().toString();
                String strLastname = edtlastname.getText().toString();
                String strCompanyname = edtCompanyname.getText().toString();
                String address1 = autotxtAddress1.getText().toString();
                String address2 = edtaddress2.getText().toString();
                String zip = edtzip.getText().toString();
                String city = edtcity.getText().toString();
                String state = edtstate.getText().toString();
                String mobile = edtMobile.getText().toString();
                String onlynumbers = getonlyNumber(mobile);
                boolean g = false;

//                Edited by Varun for guest login
                if (checkBox.isChecked()){
                   Constant.ISguest=true;
                }else{
                    Constant.ISguest=false;
                }
//                END

                if(!mobile.isEmpty()){
                    mobile = Utils.getNumberFormat(mobile);
                }

//                String mobile = Utils.getNumberFormat(edtMobile.getText().toString());

                if (Constant.SCREEN_LAYOUT == 1) {

//                    Edited by Varun for guest login

                    if (Constant.ISguest){
                        if (!edt_cnf_email.getText().toString().equalsIgnoreCase(strGlobalEmail)) {
                            Utils.showValidationDialog(MainActivity.getInstance(), MainActivity.getInstance().getString(R.string.str_signup_validation1), "");
                        } else if (strfirstname.isEmpty() || strLastname.isEmpty() || address1.isEmpty() || zip.isEmpty() ||
                                city.isEmpty() || state.isEmpty() || mobile.isEmpty() ) {

                            Utils.showValidationDialog(MainActivity.getInstance(), MainActivity.getInstance().getString(R.string.str_signup_validation2), "");
                        }else if (onlynumbers.length() < 10 || onlynumbers.length() > 10) {
                            Utils.showValidationDialog(MainActivity.getInstance(), MainActivity.getInstance().getString(R.string.valid_phone_number_simple), "");
                        } else {
                            if (address2.isEmpty())
                                address2 = "null";

                            if(strCompanyname.isEmpty()){
                                strCompanyname = "null";
                            }

                            if(!address1.isEmpty() && address1.contains("/")){
                                address1 = address1.replaceAll("/", "");
                            }
                            if (password.isEmpty()){
                                password = "null";
                            }
                            if (conf_password.isEmpty()){
                                conf_password = "null";
                            }
//                        mobile = mobile.replace("(", "").replace(")", "").replace(" ", "").replace("-", "");
//                        Edited by Varun for guest login
//                        +"/" + Constant.ISguest
//                        String Url = Constant.WS_BASE_URL + Constant.CREAT_USER + strGlobalEmail + "/" + strfirstname + "/" + strLastname + "/" + password + "/" + Constant.STOREID + "/" + strCompanyname + "/" + address1 + "/" + address2 + "/" + zip + "/" + city + "/" + state + "/" + mobile ;
                            String Url = Constant.WS_BASE_URL + Constant.CREAT_USER + strGlobalEmail + "/" + strfirstname + "/" + strLastname + "/" + password + "/" + Constant.STOREID + "/" + strCompanyname + "/" + address1 + "/" + address2 + "/" + zip + "/" + city + "/" + state + "/" + mobile + "/" + Constant.ISguest ;
                            Url = Url.replaceAll(" ", "%20");
                            new Async_getCommonService(MainActivity.getInstance(), Url).execute();
                        }
                    }else {
//                        END
                        if (!edt_cnf_email.getText().toString().equalsIgnoreCase(strGlobalEmail)) {
                            Utils.showValidationDialog(MainActivity.getInstance(), MainActivity.getInstance().getString(R.string.str_signup_validation1), "");
                        } else if (strfirstname.isEmpty() || strLastname.isEmpty() || address1.isEmpty() || zip.isEmpty() ||
                                city.isEmpty() || state.isEmpty() || mobile.isEmpty() || password.isEmpty() || conf_password.isEmpty()) {

                            Utils.showValidationDialog(MainActivity.getInstance(), MainActivity.getInstance().getString(R.string.str_signup_validation2), "");
                        } else if (password.length() < 8) {
                            Utils.showValidationDialog(MainActivity.getInstance(), MainActivity.getInstance().getString(R.string.str_psw_validation1), "");
                        } else if (!password.equals(conf_password)) {
                            Utils.showValidationDialog(MainActivity.getInstance(), MainActivity.getInstance().getString(R.string.str_psw_validation2), "");

                        } else if (onlynumbers.length() < 10 || onlynumbers.length() > 10) {
                            Utils.showValidationDialog(MainActivity.getInstance(), MainActivity.getInstance().getString(R.string.valid_phone_number_simple), "");

                        } else {
                            if (address2.isEmpty())
                                address2 = "null";

                            if (strCompanyname.isEmpty()) {
                                strCompanyname = "null";
                            }

                            if (!address1.isEmpty() && address1.contains("/")) {
                                address1 = address1.replaceAll("/", "");
                            }
//                        mobile = mobile.replace("(", "").replace(")", "").replace(" ", "").replace("-", "");
//                        Edited by Varun for guest login
//                        +"/" + Constant.ISguest
//                        String Url = Constant.WS_BASE_URL + Constant.CREAT_USER + strGlobalEmail + "/" + strfirstname + "/" + strLastname + "/" + password + "/" + Constant.STOREID + "/" + strCompanyname + "/" + address1 + "/" + address2 + "/" + zip + "/" + city + "/" + state + "/" + mobile ;
                            String Url = Constant.WS_BASE_URL + Constant.CREAT_USER + strGlobalEmail + "/" + strfirstname + "/" + strLastname + "/" + password + "/" + Constant.STOREID + "/" + strCompanyname + "/" + address1 + "/" + address2 + "/" + zip + "/" + city + "/" + state + "/" + mobile + "/" + Constant.ISguest;
                            Url = Url.replaceAll(" ", "%20");
                            new Async_getCommonService(MainActivity.getInstance(), Url).execute();
                        }
                    }
                } else if (Constant.SCREEN_LAYOUT == 2) {

//                    Edited by Varun for guest login

                    if (Constant.ISguest){
                        if (!edt_cnf_email.getText().toString().equalsIgnoreCase(strGlobalEmail)) {
                            Utils.showValidationDialog(MainActivityDup.getInstance(), MainActivityDup.getInstance().getString(R.string.str_signup_validation1), "");
                        } else if (strfirstname.isEmpty() || strLastname.isEmpty() || address1.isEmpty() || zip.isEmpty() ||
                                city.isEmpty() || state.isEmpty() || mobile.isEmpty() ) {

                            Utils.showValidationDialog(MainActivityDup.getInstance(), MainActivityDup.getInstance().getString(R.string.str_signup_validation2), "");
                        }else if (onlynumbers.length() < 10 || onlynumbers.length() > 10) {
                            Utils.showValidationDialog(MainActivityDup.getInstance(), MainActivityDup.getInstance().getString(R.string.valid_phone_number_simple), "");
                        } else {
                            if (address2.isEmpty())
                                address2 = "null";

                            if(strCompanyname.isEmpty()){
                                strCompanyname = "null";
                            }

                            if(!address1.isEmpty() && address1.contains("/")){
                                address1 = address1.replaceAll("/", "");
                            }
                            if (password.isEmpty()){
                                password = "null";
                            }
                            if (conf_password.isEmpty()){
                                conf_password = "null";
                            }
//                        mobile = mobile.replace("(", "").replace(")", "").replace(" ", "").replace("-", "");
//                        Edited by Varun for guest login
//                        +"/" + Constant.ISguest
//                        String Url = Constant.WS_BASE_URL + Constant.CREAT_USER + strGlobalEmail + "/" + strfirstname + "/" + strLastname + "/" + password + "/" + Constant.STOREID + "/" + strCompanyname + "/" + address1 + "/" + address2 + "/" + zip + "/" + city + "/" + state + "/" + mobile ;
                            String Url = Constant.WS_BASE_URL + Constant.CREAT_USER + strGlobalEmail + "/" + strfirstname + "/" + strLastname + "/" + password + "/" + Constant.STOREID + "/" + strCompanyname + "/" + address1 + "/" + address2 + "/" + zip + "/" + city + "/" + state + "/" + mobile + "/" + Constant.ISguest ;
                            Url = Url.replaceAll(" ", "%20");
                            new Async_getCommonService(MainActivityDup.getInstance(), Url).execute();
                        }
                    }else {
//                        END

                    if (!edt_cnf_email.getText().toString().equalsIgnoreCase(strGlobalEmail)) {
                        Utils.showValidationDialog(MainActivityDup.getInstance(), MainActivityDup.getInstance().getString(R.string.str_signup_validation1), "");
                    } else if (strfirstname.isEmpty() || strLastname.isEmpty() || address1.isEmpty() || zip.isEmpty() ||
                            city.isEmpty() || state.isEmpty() || mobile.isEmpty() || password.isEmpty() || conf_password.isEmpty()) {

                        Utils.showValidationDialog(MainActivityDup.getInstance(), MainActivityDup.getInstance().getString(R.string.str_signup_validation2), "");
                    } else if (password.length() < 8) {
                        Utils.showValidationDialog(MainActivityDup.getInstance(), MainActivityDup.getInstance().getString(R.string.str_psw_validation1), "");
                    } else if (!password.equals(conf_password)) {
                        Utils.showValidationDialog(MainActivityDup.getInstance(), MainActivityDup.getInstance().getString(R.string.str_psw_validation2), "");
                    } else if (onlynumbers.length() < 10 || onlynumbers.length() > 10) {
                        Utils.showValidationDialog(MainActivity.getInstance(), MainActivity.getInstance().getString(R.string.valid_phone_number_simple), "");
                    } else {
                        if (address2.isEmpty())
                            address2 = "null";

                        if (strCompanyname.isEmpty()) {
                            strCompanyname = "null";
                        }
//                        mobile = mobile.replace("(", "").replace(")", "").replace(" ", "").replace("-", "");
//                        Edited by Varun for guest login
//                        +"/" + Constant.ISguest
//                        String Url = Constant.WS_BASE_URL + Constant.CREAT_USER + strGlobalEmail + "/" + strfirstname + "/" + strLastname + "/" + password + "/" + Constant.STOREID + "/" + strCompanyname + "/" + address1 + "/" + address2 + "/" + zip + "/" + city + "/" + state + "/" + mobile;
                        String Url = Constant.WS_BASE_URL + Constant.CREAT_USER + strGlobalEmail + "/" + strfirstname + "/" + strLastname + "/" + password + "/" + Constant.STOREID + "/" + strCompanyname + "/" + address1 + "/" + address2 + "/" + zip + "/" + city + "/" + state + "/" + mobile + "/" + Constant.ISguest;
                        Url = Url.replaceAll(" ", "%20");
                        new Async_getCommonService(MainActivityDup.getInstance(), Url).execute();
//                        END
                     }
                    }
                }

            }
        });
        Signupdialog.setContentView(view);
        Signupdialog.show();
    }

    private static void Clear_Id() {
        MainActivity.txtPersonName.setText(Constant.themeModel.StoreName);
        Constant.AppPref.edit().putString("email", "").putString("password", "") .putBoolean("ISguest",false).commit();
        Constant.ISguest=false;
        MainActivity.getInstance().tvWishList.setVisibility(View.VISIBLE);
        Constant.AppPref.edit().putString("currentCustId", "").putString("favStore", "").commit();
        MainActivity.getInstance().updateShoppingCartItemCount(0);
        MainActivity.getInstance().invalidateOptionsMenu();
        Constant.LHSLIDER_LIST.clear();
        Constant.AccountList.clear();
        Constant.AccountList2.clear();
        Constant.LHSLIDER_LIST.remove("Account");
        UserModel.Cust_mst_ID = null;
        Constant.liCardModel.clear();
        CardFragment.onSetEmpty();
        MainActivity.txtPersonEmail.setText(MainActivity.getInstance().getString(R.string.str_cap_signin));
        MainActivity.getInstance().onSetDrawerMenu();
        ArrayList<String> TitleList = new ArrayList<String>(Constant.LHSLIDER_LIST.keySet());
        MainActivity.getInstance().mManage_expList.setAdapter(new ExpandAdapter(MainActivity.getInstance(), Constant.LHSLIDER_LIST, TitleList));
        MainActivity.getInstance().mManage_expList.setVisibility(View.GONE);
        MainActivity.getInstance().txtNotification.setVisibility(View.GONE);
//        MainActivity.showHomePage();
//        MainActivity.getInstance().loadHomeWebPage();
    }

    private static String getonlyNumber(String mobileNo) {

        String numberStr = mobileNo;

        if(mobileNo != null && !mobileNo.equals("") && !mobileNo.isEmpty()) {

            for (int i = 0; i < numberStr.length(); i++) {
                if (numberStr.charAt(i) == '(' || numberStr.charAt(i) == ')' ||
                        numberStr.charAt(i) == '-') {
                    numberStr = numberStr.replace(numberStr.charAt(i), ' ');
                }
            }
            numberStr = numberStr.replaceAll("\\s", "");
        }

        return numberStr;
    }

    public static void createUserafterOTP(UserModel UM) {
//        Constant.isFromLogin = true;
        strOTP = UM.OTP;
        Signupdialog.dismiss();
        OtpFrom = "signup";
        Constant.LOGIN_TYPE = "newuser";

//        Edited by Varun for guest login

        if (UM.Isguest){
            Constant.ISguest=true;
            Constant.AppPref.edit().putString("email", Constant.Email)
                    .putString("password", Constant.Password)
                    .putBoolean("ISguest",Constant.ISguest).apply();
            Login.setUserDetail(UM);
            Login.onLoginSuccess(UM);
            Constant.LHSLIDER_LIST.clear();
            Constant.AccountList2.clear();
            Utils.getAccountList2();
            Constant.LHSLIDER_LIST.put("My Account", Constant.AccountList2);
            if (Constant.SCREEN_LAYOUT == 1) {
                MainActivity.getInstance().tvWishList.setVisibility(View.GONE);
                MainActivity.getInstance().mManage_expList.setVisibility(View.VISIBLE);
                ArrayList<String> TitleList = new ArrayList<String>(Constant.LHSLIDER_LIST.keySet());
                MainActivity.getInstance().mManage_expList.setAdapter(new ExpandAdapter(mContext, Constant.LHSLIDER_LIST, TitleList));
            } else if (Constant.SCREEN_LAYOUT == 2) {
//              Edited by Varun for guest login
                MainActivityDup.getInstance().onGetCartData();
                if (ProfileFragment_layout2.getInstance() != null) {
                    ProfileFragment_layout2.getInstance().LoadAccountList();
                }

                                /*MainActivityDup.getInstance().mManage_expList.setVisibility(View.VISIBLE);
                                ArrayList<String> TitleList = new ArrayList<String>(Constant.LHSLIDER_LIST.keySet());
                                MainActivityDup.getInstance().mManage_expList.setAdapter(new ExpandAdapter(mContext, Constant.LHSLIDER_LIST, TitleList));
*/
            }
        }else{
//            END
            Constant.ISguest=false;
            Login.StartLoginDialog("", mContext);
            Login.setOTPDesign();
        }
//        Login.setOTPDesign();
    }

    public static void onloadAddress(PinModel model) {
        //edtzip.setText(model._zip);
        edtstate.setText(model._state);
        edtcity.setText(model._city);
    }

    public static void setAddress(JSONArray address) throws JSONException {
//        Constant.isFromLogin = true;
        edtzip.setText("");
        edtstate.setText("");
        edtcity.setText("");
        autotxtAddress1.setText("");
        String address11 = "";
        for (int i = 0; i < address.length(); i++) {
            JSONObject object = address.getJSONObject(i);
            isSetAddress = false;
            if (object.getString("types").contains("street_number")) {
                address11 += object.getString("long_name");
                autotxtAddress1.setText(address11);
            }
            if (object.getString("types").contains("route")) {
                address11 += " " + object.getString("long_name");
                autotxtAddress1.setText(address11);
            }
            if (object.getString("types").contains("postal_code")) {
                edtzip.setText(object.getString("long_name"));
            }
            if (object.getString("types").contains("administrative_area_level_1")) {
                edtstate.setText(object.getString("short_name"));
            }
            if (object.getString("types").contains("locality"/*"*//*administrative_area_level_2*/)) {
                edtcity.setText(object.getString("short_name"));
            }

            edtaddress2.requestFocus();
            autotxtAddress1.dismissDropDown();

            //isdhowdropdown=true;
            Log.e("Log", "Address1=" + address11);
        }
        isSetAddress = true;
        //isdhowdropdown=true;
        /*edtzip = (EditText) view.findViewById(R.id.edtzip);
        edtcity = (EditText) view.findViewById(R.id.edtcity);
        edtstate = (EditText) view.findViewById(R.id.edtstate);*/
    }

    public static void setUserDetail(UserModel model) {
//        Constant.isFromLogin = true;

        if (Constant.SCREEN_LAYOUT == 1) {
            MainActivity.txtPersonName.setText("Hi " + model.FirstName.trim());
            MainActivity.txtPersonEmail.setText("Log Off");
        } else if (Constant.SCREEN_LAYOUT == 2) {
            if (ProfileFragment_layout2.getInstance() != null) {
                ProfileFragment_layout2.txtPersonName.setText("Hi " + model.FirstName.trim());
                ProfileFragment_layout2.txtPersonEmail.setText("Log Off");
            }
        }
        if (loginDialog != null) {
            loginDialog.dismiss();
        }
    }

    public static void onLogOff() {
//        Constant.isFromLogin = true;

        //Set Default textView instends of Username
        if (Constant.SCREEN_LAYOUT == 1) {
            MainActivity.txtPersonName.setText(Constant.themeModel.StoreName);
            Constant.AppPref.edit().putString("email", "").putString("password", "") .putBoolean("ISguest",false).commit();
            Constant.ISguest=false;
            MainActivity.getInstance().tvWishList.setVisibility(View.VISIBLE);
            Constant.AppPref.edit().putString("currentCustId", "").putString("favStore", "").commit();
            MainActivity.getInstance().updateShoppingCartItemCount(0);
            MainActivity.getInstance().invalidateOptionsMenu();
            Constant.LHSLIDER_LIST.clear();
            Constant.AccountList.clear();
            Constant.AccountList2.clear();
            Constant.LHSLIDER_LIST.remove("Account");
            UserModel.Cust_mst_ID = null;
            Constant.liCardModel.clear();
            CardFragment.onSetEmpty();
            MainActivity.txtPersonEmail.setText(MainActivity.getInstance().getString(R.string.str_cap_signin));
            MainActivity.getInstance().onSetDrawerMenu();
            ArrayList<String> TitleList = new ArrayList<String>(Constant.LHSLIDER_LIST.keySet());
            MainActivity.getInstance().mManage_expList.setAdapter(new ExpandAdapter(MainActivity.getInstance(), Constant.LHSLIDER_LIST, TitleList));
            MainActivity.getInstance().mManage_expList.setVisibility(View.GONE);
            MainActivity.getInstance().txtNotification.setVisibility(View.GONE);
            MainActivity.showHomePage();
            MainActivity.getInstance().loadHomeWebPage();

        } else if (Constant.SCREEN_LAYOUT == 2) {
            ProfileFragment_layout2.txtPersonName.setText(Constant.themeModel.StoreName);
            Constant.AppPref.edit().putString("email", "").putString("password", "").commit();
            Constant.ISguest=false;
            Constant.AppPref.edit().putString("currentCustId", "").putString("favStore", "").commit();
            MainActivityDup.getInstance().updateShoppingCartItemCount(0);
            MainActivityDup.getInstance().invalidateOptionsMenu();
            Constant.LHSLIDER_LIST.clear();
            Constant.AccountList.clear();
            Constant.AccountList2.clear();
            Constant.LHSLIDER_LIST.remove("Account");
            ArrayList<String> TitleList = new ArrayList<String>(Constant.LHSLIDER_LIST.keySet());
            Log.e("Log", "Ed-3");
            //MainActivityDup.getInstance().mManage_expList.setAdapter(new ExpandAdapter(MainActivity.getInstance(), Constant.LHSLIDER_LIST, TitleList));
            UserModel.Cust_mst_ID = null;
            Constant.liCardModel.clear();
            CardFragment.onSetEmpty();
            ProfileFragment_layout2.txtPersonEmail.setText(MainActivityDup.getInstance().getString(R.string.str_cap_signin));
            MainActivityDup.getInstance().invalidateOptionsMenu();
            MbsDataModel mbs4 = new MbsDataModel();
            mbs4.ID = "0";
            mbs4.PageName = "Notifications";
            mbs4.PageTitle = "Notifications";
            mbs4.PageContent = "Notifications";
            mbs4.status = true;
            mbs4.position = Constant.TopHeaderMenuList.size();
            Constant.TopHeaderMenuList.remove(mbs4);

            //Edited by Janvi 23th Oct *****
            MainActivityDup.showHomePage();
            MainActivityDup.getInstance().loadHomeWebPage();
            //end ************

            //     MainActivity.getInstance().mManage_expList.setVisibility(View.GONE);
        }

    }

    public static void onLoginSuccess(UserModel model) {
//        Constant.isFromLogin = true;
        //looooo
//        String TAG_LOGIN_SUCCESS = "Login success";

        if (Constant.SCREEN_LAYOUT == 1) {
            MainActivity.getInstance().getCustomerData(model);
        }else if (Constant.SCREEN_LAYOUT == 2) {

        }

        if (Constant.SCREEN_LAYOUT == 1) {
            if (UserModel.Cust_mst_ID != null) {
                FCMServerRegistration.onFCMTokenRegistration(MainActivity.getInstance(), UserModel.Cust_mst_ID);
            }
            loginSuccessDialog = new Dialog(MainActivity.getInstance(), R.style.DialogSlideAnim_login);
            loginSuccessDialog.setCanceledOnTouchOutside(false);
            login_success_view = LayoutInflater.from(MainActivity.getInstance()).inflate(R.layout.login_success_dialog, null);

        } else if (Constant.SCREEN_LAYOUT == 2) {
            if (UserModel.Cust_mst_ID != null) {
                FCMServerRegistration.onFCMTokenRegistration(MainActivityDup.getInstance(), UserModel.Cust_mst_ID);
            }
            loginSuccessDialog = new Dialog(MainActivityDup.getInstance(), R.style.DialogSlideAnim_login);
            loginSuccessDialog.setCanceledOnTouchOutside(false);
            login_success_view = LayoutInflater.from(MainActivityDup.getInstance()).inflate(R.layout.login_success_dialog, null);

        }

        tvHi = login_success_view.findViewById(R.id.tv_hi_login_success_dialog);
        tvOk = login_success_view.findViewById(R.id.tv_oky_login_success_dialog);
        tvUserName = login_success_view.findViewById(R.id.tv_username_login_success_dialog);
        tvUserName.setText(model.FirstName.trim());
        tvWelconeText = login_success_view.findViewById(R.id.tv_welcome_to_store_login_success_dialog);
        btnOkLoginSuccessDialog = login_success_view.findViewById(R.id.btn_ok_login_success_dialog);

        GradientDrawable bgShape = (GradientDrawable) btnOkLoginSuccessDialog.getBackground();
        bgShape.setColor(Color.parseColor(Constant.themeModel.ThemeColor));

        btnOkLoginSuccessDialog.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("SuspiciousIndentation")
            @Override
            public void onClick(View view) {
                if (loginSuccessDialog.isShowing())
                    loginSuccessDialog.dismiss();

                    try{
                        if (CardFragment.getInstance().isComeFromCard) {
//                            //************ Edited by Varun for shopping cart on 29 july 2022 **********
//                            //                                    not to refresh when login
//
                            if (Constant.SCREEN_LAYOUT==1){
//                                MainActivity.getInstance().loadCardFragment();
//                                CardFragment.getInstance().getCustomerData();
//                                CardFragment.getInstance().onGetCartData();
                                CardFragment.getInstance().oncall();
                            }else if (Constant.SCREEN_LAYOUT==2){
                                //     CardFragment.getInstance().oncall();
                                // MainActivityDup.getInstance().loadCardFragment();
                                CardFragment.getInstance().oncall();
                            }
//                            //  CardFragment.getInstance().redirectToHome();
//                            //*********** END ***********
//                            CardFragment.getInstance().redirectToHome();
                        }
                    }catch (NullPointerException e){

                    }catch (Exception e){

                    }
            }
        });

        WindowManager.LayoutParams params = loginSuccessDialog.getWindow().getAttributes();
        params.width = WindowManager.LayoutParams.WRAP_CONTENT;
        params.height = WindowManager.LayoutParams.WRAP_CONTENT;

        loginSuccessDialog.setContentView(login_success_view);
        loginSuccessDialog.getWindow().setGravity(Gravity.CENTER);
        // WindowManager.LayoutParams layoutParam = loginDialog.getWindow().getAttributes();
        //loginDialog.getWindow().setAttributes(layoutParam);


        /*if (Constant.SCREEN_LAYOUT == 1) {
            MainActivity.getInstance().loadHomeWebPage();
            MainActivity.moveSessionToCart();
        } else if (Constant.SCREEN_LAYOUT == 2) {
            MainActivityDup.getInstance().loadHomeWebPage();
            MainActivityDup.moveSessionToCart();
        }*/
        loginSuccessDialog.show();
    }

    public static void showGuestDialogAndCloseLogin(Context mContext) {

        if(loginDialog.isShowing()){
            loginDialog.dismiss();
        }

        if(!loginDialog.isShowing()){
            DialogUtils.onWarningDialog(mContext, "Guest Checkout", "guest");
        }
    }

    public static void otpResend() {
        Toast toast = Toast.makeText(mContext, "OTP Resend", Toast.LENGTH_LONG);
        View view = toast.getView();
        view.setPadding(15,15,15,15);
        view.setBackgroundColor(Color.parseColor(Constant.themeModel.ThemeColor));
//        toast.setGravity(Gravity.TOP,70,70);
        toast.show();
    }

//    Edited by Varun for guest login

    public static void Signinfromguest(Dialog guestDialog) {

        if (Constant.SCREEN_LAYOUT==1) {
            String Url = Constant.WS_BASE_URL + Constant.GENERATE_OTP + Constant.Guest_Email + "/" + "SignInGuest/" + Constant.STOREID;
            new Async_getCommonService(MainActivity.getInstance(), Url).execute();
        }else{
            String Url = Constant.WS_BASE_URL + Constant.GENERATE_OTP + Constant.Guest_Email + "/" + "SignInGuest/" + Constant.STOREID;
            new Async_getCommonService(MainActivityDup.getInstance(), Url).execute();
        }
        Constant.ISguest_Signin=true;

        guestDialog.dismiss();
    }

    public static void Continue_As_guest() {

        if (Constant.SCREEN_LAYOUT==1) {
            String Url = Constant.WS_BASE_URL + Constant.GENERATE_OTP + Constant.Guest_Email + "/" + "SignIn/" + Constant.STOREID;
            new Async_getCommonService(MainActivity.getInstance(), Url).execute();
        }else{
            String Url = Constant.WS_BASE_URL + Constant.GENERATE_OTP + Constant.Guest_Email + "/" + "SignIn/" + Constant.STOREID;
            new Async_getCommonService(MainActivityDup.getInstance(), Url).execute();
        }
        Constant.ISguest_Continue = true;

    }

    public static void Test(UserModel otpModel) {
        loginDialog.dismiss();
//        Edited by Varun for guest login

            Constant.ISguest=true;
            Constant.AppPref.edit().putString("email", Constant.Guest_Email)
                    .putString("password", otpModel.Password)
                    .putBoolean("ISguest",Constant.ISguest).apply();
            Login.setUserDetail(otpModel);
            Login.onLoginSuccess(otpModel);
            Constant.LHSLIDER_LIST.clear();
            Constant.AccountList.clear();
            Constant.AccountList2.clear();
            Utils.getAccountList2();

            Constant.LHSLIDER_LIST.put("My Account", Constant.AccountList2);
            Log.e("ghdfg", "onPostExecute: "+Constant.AccountList2 );
            if (Constant.SCREEN_LAYOUT == 1) {
                MainActivity.getInstance().tvWishList.setVisibility(View.GONE);
                MainActivity.getInstance().loadHomeWebPage();
                MainActivity.getInstance().onGetCartData("");
                MainActivity.getInstance().mManage_expList.setVisibility(View.VISIBLE);
                ArrayList<String> TitleList = new ArrayList<String>(Constant.LHSLIDER_LIST.keySet());
                MainActivity.getInstance().mManage_expList.setAdapter(new ExpandAdapter(mContext, Constant.LHSLIDER_LIST, TitleList));

            } else if (Constant.SCREEN_LAYOUT == 2) {
                MainActivityDup.getInstance().loadHomeWebPage();
                MainActivityDup.getInstance().onGetCartData();
                if (ProfileFragment_layout2.getInstance() != null) {
                    ProfileFragment_layout2.getInstance().LoadAccountList();
                }
                //MainActivityDup.getInstance().mManage_expList.setVisibility(View.VISIBLE);
            }


    }
//    END

    private static void saveEmailToSharedPreferences(String email) {
        // Retrieve the current list of emails from SharedPreferences
        Set<String> emailSet = sharedPreferences.getStringSet(EMAIL_LIST_KEY, new HashSet<>());

        // Check if the email is already in the set
        if (emailSet.contains(email)) {
            // The email is already stored, so no need to add it again.
            return;
        }

        // The email is not in the set, so we can proceed to add it.
        Set<String> newEmailSet = new HashSet<>(emailSet);

        // Add the new email to the set
        newEmailSet.add(email);

        // Save the updated email set back to SharedPreferences
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putStringSet(EMAIL_LIST_KEY, newEmailSet);
        editor.apply();

        // Update the adapter to reflect the new email list
        adapter.clear();
        adapter.addAll(newEmailSet);
        adapter.notifyDataSetChanged();
    }



}
