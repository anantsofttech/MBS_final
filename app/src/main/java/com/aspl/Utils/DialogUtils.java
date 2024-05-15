package com.aspl.Utils;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.LightingColorFilter;
import android.graphics.PorterDuff;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.os.Build;
import android.preference.PreferenceManager;
import com.google.android.material.textfield.TextInputEditText;
import androidx.core.text.HtmlCompat;
import androidx.core.widget.NestedScrollView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.text.Editable;
import android.text.Html;
import android.text.SpannableString;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.text.style.UnderlineSpan;
import android.util.Log;
import android.util.Patterns;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.HttpHeaderParser;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.aspl.Adapter.DrinkReceipeAdapter;
import com.aspl.Adapter.PickupTimeAdapter;
import com.aspl.fragment.CardFragment;
import com.aspl.fragment.CardOnFileFragment;
import com.aspl.fragment.DeliveryOptionsFragment;
import com.aspl.fragment.HomepageFragment;
import com.aspl.fragment.ItemDescriptionsFragment;
import com.aspl.fragment.Login;
import com.aspl.fragment.OrderHistoryFragment;
import com.aspl.fragment.PaymentFragment;
import com.aspl.fragment.ShippingAddressFragment;
import com.aspl.fragment.ViewAllFragment;
import com.aspl.mbs.MainActivity;
import com.aspl.mbs.MainActivityDup;
import com.aspl.mbs.R;
import com.aspl.mbsmodel.CheckGiftCardBalanceModel;
import com.aspl.mbsmodel.ItemDescModel;
import com.aspl.mbsmodel.LstOrderTem;
import com.aspl.mbsmodel.PickupModel;
import com.aspl.mbsmodel.ShippingData;
import com.aspl.mbsmodel.TwentyOneYear;
import com.aspl.mbsmodel.UpdateCartQuantity;
import com.aspl.mbsmodel.UserModel;
import com.aspl.mbsmodel.lstInventoryModel;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.lang.reflect.Type;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Enumeration;
import java.util.List;
import java.util.Objects;

import static com.aspl.Utils.Constant.Test_Ounces;
import static com.aspl.Utils.Constant.WS_SAVE_RETURN_ORDER;
import static com.aspl.Utils.Constant.themeModel;

/**
 * Created by admin on 12/22/2017.
 */

public class DialogUtils {

    public static Dialog twentyOneYearDialog;
    public static Dialog guestDialog;
    public static View twent_one_year_cart_view;
    public static TextView tvHi, tvOk, tvUserName, tvWelconeText;
    public static Button btnOkLoginSuccessDialog;
    public static String imgNoImageUrl = Constant.IMG_BASE + Constant.IMG_NO_IMAGE + "/";
    public static String imgUrl = Constant.IMG_BASE + Constant.IMG_BASE_URL + Constant.STOREID + "/";
    public static int drinkReceipeId = 0;
    public static DrinkReceipeAdapter drinkReceipeAdapter;
    public static PickupTimeAdapter pickupTimeAdapter;
    public static RecyclerView recyclerview;
    public static NestedScrollView ll_desc;
    public static TextView tvDescription;
    //    public static LinearLayout ll_checkbox;
    public static LinearLayout llBackEmail;
    public static Button btnClose;
    public static Dialog homeTwentyOneYearSuccessDialog;
    public static View vHomeSuccess;
    public static TextView tvTitle, tvSubTitle;

    //Twnty one Year
    public static Dialog homeTwentyOneYearDialog;
    public static View vHome;
    public static TextView tvStoreTitleOne, tvStoreTitleSecond, tvStoreTitleThree, tvOr, tv_title_legal,
            tvDate, tvAddress, tv_City, tv_Phone;
    public static Button btnSubmit, btnFacebook, btnYEs, btnNo, btn_ok_legal;
    public static Spinner spinnerMonth;
    public static EditText etDate, etYear;
    public static ImageView img_StoreLogo;
    public static Context fasterDialogContext;
    public static TextView tv_legal_title, tv_legal_msg , tv_age_msg , tv_age_title;
    public static View view_line;
    public static PickupModel selectedPickupModel;
    public static boolean isMoreitemClicked = false;
    static Dialog StartReturndialog = null;

    public static void on21Year(TwentyOneYear twentyOneYear) {

    }

    public static void on21YearCart(final TwentyOneYear twentyOneYear, String fromwhere) {
        if (Constant.SCREEN_LAYOUT == 1) {
            twentyOneYearDialog = new Dialog(MainActivity.getInstance(), R.style.DialogSlideAnim_login);
            twentyOneYearDialog.setCanceledOnTouchOutside(false);
            twent_one_year_cart_view = LayoutInflater.from(MainActivity.getInstance()).inflate(R.layout.dialog_legal_disclaimer, null);

        } else if (Constant.SCREEN_LAYOUT == 2) {
            twentyOneYearDialog = new Dialog(MainActivityDup.getInstance(), R.style.DialogSlideAnim_login);
            twentyOneYearDialog.setCanceledOnTouchOutside(false);
            twent_one_year_cart_view = LayoutInflater.from(MainActivityDup.getInstance()).inflate(R.layout.dialog_legal_disclaimer, null);

        }
        //Hide Other view that unused because this same dialog user for login success

        tv_legal_title = twent_one_year_cart_view.findViewById(R.id.tv_legal_title);
        tv_age_title = twent_one_year_cart_view.findViewById(R.id.tv_age_title);
        tv_legal_msg = twent_one_year_cart_view.findViewById(R.id.tv_legal_msg);
        tv_age_msg = twent_one_year_cart_view.findViewById(R.id.tv_age_msg);
        btn_ok_legal = twent_one_year_cart_view.findViewById(R.id.btn_ok_legal);
        view_line = twent_one_year_cart_view.findViewById(R.id.view_line);

        if (fromwhere.equals("legal")) {
            tv_legal_title.setVisibility(View.VISIBLE);
            view_line.setVisibility(View.VISIBLE);
        }else {
            tv_legal_title.setVisibility(View.GONE);
            view_line.setVisibility(View.GONE);
        }

////         Edited By Varun for Age Verification
//
        if (fromwhere.equals("legal")) {
            tv_legal_title.setVisibility(View.VISIBLE);
            tv_legal_title.setText("Legal Disclaimer");
            view_line.setVisibility(View.VISIBLE);
        } else if (fromwhere.equals("age")){
            tv_legal_title.setVisibility(View.GONE);
            tv_age_title.setVisibility(View.VISIBLE);
            tv_age_title.setText("Age Verification");
            tv_legal_msg.setVisibility(View.GONE);
            tv_age_msg.setVisibility(View.VISIBLE);
            tv_age_msg.setText("You must agree that you are 21 years old or older.");
            view_line.setVisibility(View.VISIBLE);
        }else {
            tv_legal_title.setVisibility(View.GONE);
            view_line.setVisibility(View.GONE);
        }

//        END

        if (twentyOneYear.getAgeValidMessage() != null)
            tv_legal_msg.setText(twentyOneYear.getAgeValidMessage().trim());

        GradientDrawable bgShape = (GradientDrawable) btn_ok_legal.getBackground();
        bgShape.setColor(Color.parseColor(Constant.themeModel.ThemeColor));

        btn_ok_legal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (twentyOneYear.getCustAgeValidOption() == 1) {
                    if (twentyOneYearDialog.isShowing())
                        twentyOneYearDialog.dismiss();
                    on21YearHome(twentyOneYear);
                } else if (twentyOneYear.getCustAgeValidOption() == 3) {
                    if (twentyOneYearDialog.isShowing())
                        twentyOneYearDialog.dismiss();
                    if (fasterDialogContext != null) {
                        on21YearFaster(fasterDialogContext, twentyOneYear);
                    }
                } else {
                    if (twentyOneYearDialog.isShowing())
                        twentyOneYearDialog.dismiss();
                }
            }
        });

        WindowManager.LayoutParams params = twentyOneYearDialog.getWindow().getAttributes();
        params.width = WindowManager.LayoutParams.WRAP_CONTENT;
        params.height = WindowManager.LayoutParams.WRAP_CONTENT;

        twentyOneYearDialog.setContentView(twent_one_year_cart_view);
        twentyOneYearDialog.getWindow().setGravity(Gravity.CENTER);
        WindowManager.LayoutParams layoutParam = twentyOneYearDialog.getWindow().getAttributes();
        //layoutParam.y = (MainActivity.getInstance().getStatusBarHeight(MainActivity.getInstance()))/* + (MainActivity.getInstance().getToolBarHeight()) + (MainActivity.getInstance().getToolBarHeight())*/; // bottom margin
        twentyOneYearDialog.getWindow().setAttributes(layoutParam);
        twentyOneYearDialog.setCancelable(false);
        twentyOneYearDialog.show();
    }


    public static String getIpv4() {
        try {
            for (Enumeration<NetworkInterface> en = NetworkInterface
                    .getNetworkInterfaces(); en.hasMoreElements(); ) {
                NetworkInterface intf = en.nextElement();
                for (Enumeration<InetAddress> enumIpAddr = intf
                        .getInetAddresses(); enumIpAddr.hasMoreElements(); ) {
                    InetAddress inetAddress = enumIpAddr.nextElement();
                    System.out.println("ip1--:" + inetAddress);
                    System.out.println("ip2--:" + inetAddress.getHostAddress());
                }
            }
        } catch (Exception ex) {
            Log.e("IP Address", ex.toString());
        }
        return null;
    }


    public static void on21YearHome(TwentyOneYear twentyOneYear) {
        if (Constant.SCREEN_LAYOUT == 1) {
            homeTwentyOneYearDialog = new Dialog(MainActivity.getInstance(), R.style.DialogSlideAnim_login);
            homeTwentyOneYearDialog.setCanceledOnTouchOutside(false);
            vHome = LayoutInflater.from(MainActivity.getInstance()).inflate(R.layout.dialog_twenty_one_year_home, null);

        } else if (Constant.SCREEN_LAYOUT == 2) {
            homeTwentyOneYearDialog = new Dialog(MainActivityDup.getInstance(), R.style.DialogSlideAnim_login);
            homeTwentyOneYearDialog.setCanceledOnTouchOutside(false);
            vHome = LayoutInflater.from(MainActivityDup.getInstance()).inflate(R.layout.dialog_twenty_one_year_home, null);

        }
        tvStoreTitleOne = vHome.findViewById(R.id.tv_title_toy_dialog);
        tvStoreTitleSecond = vHome.findViewById(R.id.tv_title_second_toy_dialog);
        tvStoreTitleThree = vHome.findViewById(R.id.tv_title_third_toy_dialog);
        tvOr = vHome.findViewById(R.id.tv_or_toy_dialog);
        btnSubmit = vHome.findViewById(R.id.btn_submit_toy_dialog);
        btnFacebook = vHome.findViewById(R.id.btn_facebook_toy_dialog);
        tv_title_legal = vHome.findViewById(R.id.tv_title_legal);

        etDate = vHome.findViewById(R.id.et_date_toy_dialog);
        etYear = vHome.findViewById(R.id.et_year_toy_dialog);

        spinnerMonth = vHome.findViewById(R.id.spinner_toy_dialog);
        if (Constant.SCREEN_LAYOUT == 1) {
            ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(MainActivity.getInstance(),
                    R.array.month_name, android.R.layout.simple_spinner_item);
            // Specify the layout to use when the list of choices appears
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spinnerMonth.setAdapter(adapter);
        } else if (Constant.SCREEN_LAYOUT == 2) {
            ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(MainActivityDup.getInstance(),
                    R.array.month_name, android.R.layout.simple_spinner_item);
            // Specify the layout to use when the list of choices appears
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spinnerMonth.setAdapter(adapter);
        }

        //Edited by Janvi 28thDec**************

        tv_title_legal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (homeTwentyOneYearDialog.isShowing()) {
                    homeTwentyOneYearDialog.dismiss();
                }
                DialogUtils.on21YearCart(Constant.twentyOneYear, "legal");
            }
        });
        //end*****************

        // Apply the adapter to the spinner

        GradientDrawable bgShape = (GradientDrawable) btnSubmit.getBackground();
        bgShape.setColor(Color.parseColor(Constant.themeModel.ThemeColor));

        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (validate()) {

                    final int month = spinnerMonth.getSelectedItemPosition();
                    String year = etYear.getText().toString();
                    String userDate = etDate.getText().toString();

                    int years = 0;
                    int dates = 0;

                    if (!year.equals(""))
                        years = Integer.parseInt(year);

                    if (!userDate.equals(""))
                        dates = Integer.parseInt(userDate);

                    int age = getAge(years, month, dates);
                    if (age <= 0) {
                        DialogUtils.on21YearCart(Constant.twentyOneYear, "age");
                    } else if (age >= 21) {
                        if (homeTwentyOneYearDialog.isShowing())
                            homeTwentyOneYearDialog.dismiss();
                        //on21YearSuccess();
                        //Toast.makeText(MainActivity.getInstance(), "Congratulation...", Toast.LENGTH_SHORT).show();

                        //for location

                        if (Constant.SCREEN_LAYOUT == 1) {
                            if (Constant.co_storeno_value != null && !Constant.co_storeno_value.isEmpty()) {
                                MainActivity.getInstance().takeLocationPermissionFromUser();
                            }
                        } else if (Constant.SCREEN_LAYOUT == 2) {
                            if (Constant.co_storeno_value != null && !Constant.co_storeno_value.isEmpty()) {
                                MainActivityDup.getInstance().takeLocationPermissionFromUser();
                            }
                        }

                    } else {
                        if (homeTwentyOneYearDialog.isShowing())
                            homeTwentyOneYearDialog.dismiss();

                        //display dialog and dialog is same used for age invalidate,
                        // and legal for cart,so name of the dialog is cart
                        DialogUtils.on21YearCart(Constant.twentyOneYear, "age");
                    }

                }
               /* else {

                }*/
                /*final int month = spinnerMonth.getSelectedItemPosition();
                final int year = Integer.parseInt(etYear.getText().toString());
                final int userDate = Integer.parseInt(etDate.getText().toString());
                Toast.makeText(MainActivity.getInstance(), "Age : " + getAge(year, month, userDate), Toast.LENGTH_SHORT).show();*/
            }
        });

        WindowManager.LayoutParams params = homeTwentyOneYearDialog.getWindow().getAttributes();
        params.width = WindowManager.LayoutParams.WRAP_CONTENT;
        params.height = WindowManager.LayoutParams.WRAP_CONTENT;

        homeTwentyOneYearDialog.setContentView(vHome);
        homeTwentyOneYearDialog.getWindow().setGravity(Gravity.TOP);
        WindowManager.LayoutParams layoutParam = homeTwentyOneYearDialog.getWindow().getAttributes();
        if (Constant.SCREEN_LAYOUT == 1) {
            layoutParam.y = (MainActivity.getStatusBarHeight(MainActivity.getInstance()));
        } else if (Constant.SCREEN_LAYOUT == 2) {
            layoutParam.y = (MainActivityDup.getStatusBarHeight(MainActivity.getInstance()));
        }

        //layoutParam.y = (MainActivity.getInstance().getStatusBarHeight(MainActivity.getInstance()))/* + (MainActivity.getInstance().getToolBarHeight()) + (MainActivity.getInstance().getToolBarHeight())*/; // bottom margin
        homeTwentyOneYearDialog.getWindow().setAttributes(layoutParam);
        homeTwentyOneYearDialog.setCancelable(false);
        homeTwentyOneYearDialog.show();

    }


    public static void on21YearFaster(Context context, TwentyOneYear twentyOneYear) {

        fasterDialogContext = context;

        if (Constant.SCREEN_LAYOUT == 1) {
            homeTwentyOneYearDialog = new Dialog(MainActivity.getInstance(), R.style.DialogSlideAnim_login);
            homeTwentyOneYearDialog.setCanceledOnTouchOutside(false);
            vHome = LayoutInflater.from(MainActivity.getInstance()).inflate(R.layout.dialog_twenty_one_year_fast, null);

        } else if (Constant.SCREEN_LAYOUT == 2) {
            homeTwentyOneYearDialog = new Dialog(MainActivityDup.getInstance(), R.style.DialogSlideAnim_login);
            homeTwentyOneYearDialog.setCanceledOnTouchOutside(false);
            vHome = LayoutInflater.from(MainActivityDup.getInstance()).inflate(R.layout.dialog_twenty_one_year_fast, null);
        }

        img_StoreLogo = vHome.findViewById(R.id.img_StoreLogo);
        tvStoreTitleOne = vHome.findViewById(R.id.tv_title_fast);
        tvStoreTitleSecond = vHome.findViewById(R.id.tv_title_second_toy_dialog);
        tvStoreTitleThree = vHome.findViewById(R.id.tv_title_third_toy_dialog);
        tvDate = vHome.findViewById(R.id.tv_date);
        btnYEs = vHome.findViewById(R.id.btn_Yes);
        btnNo = vHome.findViewById(R.id.btn_No);
        tvAddress = vHome.findViewById(R.id.tv_Address);
        tv_City = vHome.findViewById(R.id.tv_City);
        tv_Phone = vHome.findViewById(R.id.tv_Phone);


        //intialize values ***************

//        if (Constant.SCREEN_LAYOUT == 1) {
//            if (Constant.themeModel != null) {
//                String sturl = Constant.IMG_BASEURL + Constant.LOGO + Constant.STOREID + "/" + Constant.themeModel.StoreLogo;
////                Log.e("Log", "STURL=" + sturl);
//                Utils.setImageFromUrl(MainActivity.getInstance(), sturl, img_StoreLogo);
//
//            } else if (Constant.SCREEN_LAYOUT == 2) {
//                if (Constant.themeModel != null) {
//                    String sturl = Constant.IMG_BASEURL + Constant.LOGO + Constant.STOREID + "/" + Constant.themeModel.StoreLogo;
////                Log.e("Log", "STURL=" + sturl);
//                    Utils.setImageFromUrl(MainActivityDup.getInstance(), sturl, img_StoreLogo);
//                }
//            }
//        }

        if (Constant.themeModel != null) {

            if (Constant.themeModel != null) {

                if (Constant.themeModel.StoreLogo != null && !Constant.themeModel.StoreLogo.isEmpty()) {
                    String sturl = Constant.IMG_BASEURL + Constant.LOGO + Constant.STOREID + "/" + Constant.themeModel.StoreLogo;
//                Log.e("Log", "STURL=" + sturl);
                    Utils.setImageFromUrl(context, sturl, img_StoreLogo);
                } else {
//                    Glide.with(context)
//                            .load(context.getResources().getDrawable(R.drawable.
//                                    welcome_default_image))
//                            .fitCenter()
//                            .diskCacheStrategy(DiskCacheStrategy.ALL)
//                            .into(img_StoreLogo);

                    Glide.with(context).load(context.getResources().getIdentifier("welcome_default_image", "drawable", context.getPackageName()))
                            .into(img_StoreLogo);
                }


//                Glide.with(context).load(sturl)
//                        .fitCenter()
//                        .diskCacheStrategy(DiskCacheStrategy.ALL)
//                        .into(img_StoreLogo);
//
//                Glide.with(context).load(sturl)
//                        .fitCenter().placeholder(R.drawable.noimage)
//                        .diskCacheStrategy(DiskCacheStrategy.NONE)
//                        .into(img_StoreLogo);
            }

            if (Constant.themeModel.StoreName != null && !Constant.themeModel.StoreName.equals("")) {
                tvStoreTitleOne.setText(Constant.themeModel.StoreName);
            }
        }

        if (!dateBefore21Years().equals("")) {
            tvDate.setText(dateBefore21Years());
        }

        if (Constant.contatInfo != null) {
            tvAddress.setText(Constant.contatInfo.getAddress());
            tv_City.setText(Constant.contatInfo.getCity());
            tv_Phone.setText(Constant.contatInfo.getPhone());
        }

        btnNo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (homeTwentyOneYearDialog.isShowing())
                    homeTwentyOneYearDialog.dismiss();
                DialogUtils.on21YearCart(Constant.twentyOneYear, "cartscreen");
            }
        });

        btnYEs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (homeTwentyOneYearDialog.isShowing())
                    homeTwentyOneYearDialog.dismiss();
//                on21YearSuccess();

                //for location
                if (Constant.SCREEN_LAYOUT == 1) {
                    if (Constant.co_storeno_value != null && !Constant.co_storeno_value.isEmpty()) {
                        MainActivity.getInstance().takeLocationPermissionFromUser();
                    }

                } else if (Constant.SCREEN_LAYOUT == 2) {
                    if (Constant.co_storeno_value != null && !Constant.co_storeno_value.isEmpty()) {
                        MainActivityDup.getInstance().takeLocationPermissionFromUser();
                    }

                }
                //end
            }
        });
        // **********************

        WindowManager.LayoutParams params = homeTwentyOneYearDialog.getWindow().getAttributes();
        params.width = WindowManager.LayoutParams.WRAP_CONTENT;
        params.height = WindowManager.LayoutParams.WRAP_CONTENT;

        homeTwentyOneYearDialog.setContentView(vHome);
        homeTwentyOneYearDialog.getWindow().setGravity(Gravity.CENTER);
        WindowManager.LayoutParams layoutParam = homeTwentyOneYearDialog.getWindow().getAttributes();
        if (Constant.SCREEN_LAYOUT == 1) {
            layoutParam.y = (MainActivity.getStatusBarHeight(MainActivity.getInstance()));
        } else if (Constant.SCREEN_LAYOUT == 2) {
            layoutParam.y = (MainActivityDup.getStatusBarHeight(MainActivity.getInstance()));
        }
        homeTwentyOneYearDialog.getWindow().setAttributes(layoutParam);
        homeTwentyOneYearDialog.setCancelable(false);
        homeTwentyOneYearDialog.show();
    }

    private static String dateBefore21Years() {

        String d = "";
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.YEAR, -21);
        Date date = calendar.getTime();
        SimpleDateFormat format = new SimpleDateFormat("MMM dd, yyyy");
        d = format.format(date);
        return d;
    }


    public static boolean validate() {
        final int month = spinnerMonth.getSelectedItemPosition();
        String year = etYear.getText().toString();
        String userDate = etDate.getText().toString();

        int years = 0;
        int dates = 0;

        if (!year.equals(""))
            years = Integer.parseInt(year);

        if (!userDate.equals(""))
            dates = Integer.parseInt(userDate);

        if (etDate.getText().toString().isEmpty()) {
            etDate.requestFocus();
            etDate.setError("Day is invalid or empty");
            return false;
        }

        if (dates <= 0 || dates > 31) {
            etDate.requestFocus();
            etDate.setError("Day is invalid or empty");
            return false;
        }
        /*if (dates > 31) {
            etDate.requestFocus();
            etDate.setError("Day is invalid or empty");
            return false;
        }*/

        if (etYear.getText().toString().isEmpty()) {
            etYear.requestFocus();
            etYear.setError("Year is invalid or empty");
            return false;
        }

        if (years <= 1899 || years >= 2099) {
            etYear.requestFocus();
            etYear.setError("Year is invalid or empty");
            return false;
        }
/*
        if ( years >= 2099 ) {
            etYear.requestFocus();
            etYear.setError("Year is invalid or empty");
            return false;
        }*/
        return true;
    }

    public static int getAge(int year, int month, int day) {
        final Calendar birthDay = Calendar.getInstance();
        birthDay.set(year, month, day);
        final Calendar current = Calendar.getInstance();
        if (current.getTimeInMillis() < birthDay.getTimeInMillis())
            return 0;
        //throw new IllegalArgumentException("age < 0");
        int age = current.get(Calendar.YEAR) - birthDay.get(Calendar.YEAR);
        if (birthDay.get(Calendar.MONTH) > current.get(Calendar.MONTH) ||
                (birthDay.get(Calendar.MONTH) == current.get(Calendar.MONTH) &&
                        birthDay.get(Calendar.DATE) > current.get(Calendar.DATE)))
            age--;
        return age;
    }

    public static void on21YearSuccess() {
        if (Constant.SCREEN_LAYOUT == 1) {
            homeTwentyOneYearSuccessDialog = new Dialog(MainActivity.getInstance(), R.style.DialogSlideAnim_login);
            homeTwentyOneYearSuccessDialog.setCanceledOnTouchOutside(false);
            vHomeSuccess = LayoutInflater.from(MainActivity.getInstance()).inflate(R.layout.dialog_twent_one_year_success, null);

        } else if (Constant.SCREEN_LAYOUT == 2) {
            homeTwentyOneYearSuccessDialog = new Dialog(MainActivityDup.getInstance(), R.style.DialogSlideAnim_login);
            homeTwentyOneYearSuccessDialog.setCanceledOnTouchOutside(false);
            vHomeSuccess = LayoutInflater.from(MainActivityDup.getInstance()).inflate(R.layout.dialog_twent_one_year_success, null);
        }
        tvTitle = vHomeSuccess.findViewById(R.id.tv_title_twenty_one_year_success_dialog);
        tvSubTitle = vHomeSuccess.findViewById(R.id.tv_sub_title_twenty_one_year_success_dialog);

        Thread timerThread = new Thread() {
            public void run() {
                try {
                    sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    if (homeTwentyOneYearSuccessDialog.isShowing())
                        homeTwentyOneYearSuccessDialog.dismiss();
                }
            }
        };
        timerThread.start();

        WindowManager.LayoutParams params = homeTwentyOneYearSuccessDialog.getWindow().getAttributes();
        params.width = WindowManager.LayoutParams.WRAP_CONTENT;
        params.height = WindowManager.LayoutParams.WRAP_CONTENT;

        homeTwentyOneYearSuccessDialog.setContentView(vHomeSuccess);
        homeTwentyOneYearSuccessDialog.getWindow().setGravity(Gravity.CENTER);
        WindowManager.LayoutParams layoutParam = homeTwentyOneYearSuccessDialog.getWindow().getAttributes();
        homeTwentyOneYearSuccessDialog.getWindow().setAttributes(layoutParam);
        homeTwentyOneYearSuccessDialog.show();
    }

    public static void onCommonDialog(Context context, String title, String message, CheckGiftCardBalanceModel checkGiftCardBalanceModel) {
        Dialog commondialog = null;
        View vcommonDialog = null;

        if (Constant.SCREEN_LAYOUT == 1) {
            commondialog = new Dialog(MainActivity.getInstance(), R.style.DialogSlideAnim_login);
            commondialog.setCanceledOnTouchOutside(false);
            vcommonDialog = LayoutInflater.from(MainActivity.getInstance()).inflate(R.layout.common_dialog, null);

        } else if (Constant.SCREEN_LAYOUT == 2) {
            commondialog = new Dialog(MainActivityDup.getInstance(), R.style.DialogSlideAnim_login);
            commondialog.setCanceledOnTouchOutside(false);
            vcommonDialog = LayoutInflater.from(MainActivityDup.getInstance()).inflate(R.layout.common_dialog, null);

        }
        View view_line = (View) vcommonDialog.findViewById(R.id.view_line);
        TextView tv_title = (TextView) vcommonDialog.findViewById(R.id.tv_title);
        TextView tv_giftno_not_match = (TextView) vcommonDialog.findViewById(R.id.tv_giftno_not_match);
        TextView tv_giftResMsg = (TextView) vcommonDialog.findViewById(R.id.tv_giftResMsg);
        TextView tvmsg = (TextView) vcommonDialog.findViewById(R.id.tv_message);
        TextView tv_giftdata_is_real = (TextView) vcommonDialog.findViewById(R.id.tv_giftdata_is_real);
        TextView tv_lastpurchase_withthiscard = (TextView) vcommonDialog.findViewById(R.id.tv_lastpurchase_withthiscard);
        TextView tv_giftcard_date = (TextView) vcommonDialog.findViewById(R.id.tv_giftcard_date);
        TextView tv_giftcard_bal_amount = (TextView) vcommonDialog.findViewById(R.id.tv_giftcard_bal_amount);

        Button btnOk = (Button) vcommonDialog.findViewById(R.id.btn_ok);
        LinearLayout ll_btn_ok = (LinearLayout) vcommonDialog.findViewById(R.id.ll_btn_ok);

        if (!title.isEmpty() && title.equals("giftcard")) {
            view_line.setVisibility(View.GONE);
            tv_title.setVisibility(View.GONE);
            tv_giftno_not_match.setVisibility(View.GONE);
            tv_giftResMsg.setVisibility(View.GONE);
            tvmsg.setVisibility(View.VISIBLE);
            tvmsg.setGravity(Gravity.CENTER);
            tvmsg.setText("Please enter gift card number");
            btnOk.setText("OK");
            ll_btn_ok.setGravity(Gravity.CENTER);
            tv_giftdata_is_real.setVisibility(View.GONE);
            tv_lastpurchase_withthiscard.setVisibility(View.GONE);
            tv_giftcard_date.setVisibility(View.GONE);
            tv_giftcard_bal_amount.setVisibility(View.GONE);

        } else if (!title.isEmpty() && title.equals("GiftCardNotMatch")) {

            view_line.setVisibility(View.VISIBLE);
            tv_title.setVisibility(View.VISIBLE);
            tv_title.setText("Gift Card");
            tv_giftno_not_match.setVisibility(View.VISIBLE);
            tv_giftno_not_match.setText("Gift card number not found.");
            tv_giftResMsg.setVisibility(View.VISIBLE);
            tv_giftResMsg.setText("Gift Card #: " + message);
            tvmsg.setVisibility(View.GONE);
            btnOk.setText("CLOSE");
            ll_btn_ok.setGravity(Gravity.RIGHT);
            tv_giftdata_is_real.setVisibility(View.GONE);
            tv_lastpurchase_withthiscard.setVisibility(View.GONE);
            tv_giftcard_date.setVisibility(View.GONE);
            tv_giftcard_bal_amount.setVisibility(View.GONE);

        } else if (!title.isEmpty() && title.equals("GiftCardResult")) {

            view_line.setVisibility(View.VISIBLE);
            tv_title.setVisibility(View.VISIBLE);
            tv_title.setText("Gift Card");
            tv_giftno_not_match.setVisibility(View.VISIBLE);
            tv_giftno_not_match.setText("Gift Card Balance:");
            if (checkGiftCardBalanceModel != null) {
                tv_giftcard_bal_amount.setVisibility(View.VISIBLE);
                tv_giftcard_bal_amount.setText("$" + checkGiftCardBalanceModel.getGiftCardBal());
//                tv_giftcard_bal_amount.setText("$0.00");
            }
            tv_giftResMsg.setVisibility(View.VISIBLE);
            tv_giftResMsg.setText("Gift Card #: " + message);
            tvmsg.setVisibility(View.GONE);
            btnOk.setText("CLOSE");
            ll_btn_ok.setGravity(Gravity.RIGHT);
            tv_giftdata_is_real.setVisibility(View.VISIBLE);
            tv_giftdata_is_real.setText("Data is real time with the store");
            if (!message.isEmpty()) {
                if (checkGiftCardBalanceModel != null) {
                    tv_lastpurchase_withthiscard.setVisibility(View.VISIBLE);
                    tv_lastpurchase_withthiscard.setText("Last purchase with this card:");
                    tv_giftcard_date.setVisibility(View.VISIBLE);
                    tv_giftcard_date.setText(checkGiftCardBalanceModel.getLastPurDate() +
                            " @ " + checkGiftCardBalanceModel.getLastPurTime());
//                    tv_giftcard_date.setText("08/17/2016" +
//                            " @ " + "05:42 AM");
                }
            }

        } else {
            btnOk.setText("OK");
            tvmsg.setText(message);
        }

        Dialog finalCommondialog = commondialog;
        btnOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finalCommondialog.dismiss();
            }
        });

        WindowManager.LayoutParams params = commondialog.getWindow().getAttributes();
        params.width = WindowManager.LayoutParams.WRAP_CONTENT;
        params.height = WindowManager.LayoutParams.WRAP_CONTENT;
        commondialog.setContentView(vcommonDialog);

        WindowManager.LayoutParams layoutParam = commondialog.getWindow().getAttributes();
        commondialog.getWindow().setAttributes(layoutParam);
        commondialog.getWindow().setGravity(Gravity.CENTER);

        if (!commondialog.isShowing()) {
            commondialog.show();
        }

    }


    public static Dialog warningDialog;
    public static View vWarningDialog, v;
    public static TextView tvTitleWD, tvSubTitleWD, tv_guest_title, tv_guest_text;
    public static Button btnOkWD;
    public static LinearLayout ll_guest_title;

    public static void onWarningDialog(Context context, String title, String message) {
        if (Constant.SCREEN_LAYOUT == 1) {
            warningDialog = new Dialog(MainActivity.getInstance(), R.style.DialogSlideAnim_login);
            warningDialog.setCanceledOnTouchOutside(false);
            vWarningDialog = LayoutInflater.from(MainActivity.getInstance()).inflate(R.layout.dialog_warning, null);

        } else if (Constant.SCREEN_LAYOUT == 2) {
            warningDialog = new Dialog(MainActivityDup.getInstance(), R.style.DialogSlideAnim_login);
            warningDialog.setCanceledOnTouchOutside(false);
            vWarningDialog = LayoutInflater.from(MainActivityDup.getInstance()).inflate(R.layout.dialog_warning, null);

        }
        tvTitleWD = vWarningDialog.findViewById(R.id.tv_title_warning_dialog);
        tv_guest_title = vWarningDialog.findViewById(R.id.tv_guest_title);
        ll_guest_title = vWarningDialog.findViewById(R.id.ll_guest_title);
        ll_guest_title.setVisibility(View.GONE);
        tv_guest_title.setVisibility(View.GONE);
        tv_guest_text = vWarningDialog.findViewById(R.id.tv_guest_text);
        tv_guest_text.setVisibility(View.GONE);
        v = vWarningDialog.findViewById(R.id.v_warning_dialog);
        tvSubTitleWD = vWarningDialog.findViewById(R.id.tv_sub_title_warning_dialog);
        btnOkWD = vWarningDialog.findViewById(R.id.btn_warning_dialog);
        btnOkWD.setText("OK");

        if (title.isEmpty()) {
            tvTitleWD.setVisibility(View.GONE);
            v.setVisibility(View.GONE);
            ViewGroup.MarginLayoutParams params = (ViewGroup.MarginLayoutParams) tvSubTitleWD.getLayoutParams();
            /*params.width = 200;*/ /*params.leftMargin = 100;*/
            params.topMargin = 55;
        } else if (title.equals("Guest Checkout")) {
            tvSubTitleWD.setVisibility(View.GONE);
            tvTitleWD.setVisibility(View.GONE);
            ll_guest_title.setVisibility(View.VISIBLE);

            GradientDrawable bgShape = (GradientDrawable) ll_guest_title.getBackground();
            bgShape.setColor(Color.parseColor(Constant.themeModel.ThemeColor));

            tv_guest_title.setVisibility(View.VISIBLE);
            tv_guest_title.setTextColor(context.getResources().getColor(R.color.androidWhite));
            tv_guest_text.setVisibility(View.VISIBLE);
            tv_guest_title.setText(title);
            v.setVisibility(View.GONE);

        } else if (title.equals("Manage Account")) {
            btnOkWD.setText("CONTINUE");
            tvSubTitleWD.setVisibility(View.GONE);
            tvTitleWD.setVisibility(View.GONE);
            ll_guest_title.setVisibility(View.VISIBLE);

            GradientDrawable bgShape = (GradientDrawable) ll_guest_title.getBackground();
            bgShape.setColor(Color.parseColor(Constant.themeModel.ThemeColor));

            tv_guest_title.setVisibility(View.VISIBLE);
            tv_guest_title.setTextColor(context.getResources().getColor(R.color.androidWhite));
            tv_guest_text.setVisibility(View.VISIBLE);
            tv_guest_title.setText(title);
            v.setVisibility(View.GONE);

            if (tv_guest_text.getVisibility() == View.VISIBLE) {
                tv_guest_text.setText(message);
            } else {
                tv_guest_text.setVisibility(View.VISIBLE);
                tv_guest_text.setText(message);
            }

        } else {
            tvSubTitleWD.setVisibility(View.VISIBLE);
            tvTitleWD.setVisibility(View.VISIBLE);
            v.setVisibility(View.VISIBLE);
            tvTitleWD.setText(title);
        }

        if (message.equals(context.getResources().getString(R.string.str_payment_Option_under_develop))) {
//            tvSubTitleWD.setGravity(Gravity.NO_GRAVITY);
            tvSubTitleWD.setTextSize(context.getResources().getDimension(R.dimen._10sdp));
            tvSubTitleWD.setText(message);
        } else if (message.equals("guest")) {

            if (tv_guest_text.getVisibility() == View.VISIBLE) {
                tv_guest_text.setText(context.getString(R.string.str_guestCheckout));
            } else {
                tv_guest_text.setVisibility(View.VISIBLE);
                tv_guest_text.setText(context.getString(R.string.str_guestCheckout));
            }
        } else if (message.equals("Please select a payment option.")) {
//            tvSubTitleWD.setTextSize(context.getResources().getDimension(R.dimen._10sdp));

            if (tvSubTitleWD.getVisibility() != View.VISIBLE) {
                tvSubTitleWD.setVisibility(View.VISIBLE);
                tvSubTitleWD.setText(message);
            } else {
                tvSubTitleWD.setText(message);
            }

        } else if (!title.equalsIgnoreCase("Manage Account")) {
            if (tvSubTitleWD.getVisibility() != View.VISIBLE) {
                tvSubTitleWD.setVisibility(View.VISIBLE);
                tvSubTitleWD.setText(message);
            } else {
                tvSubTitleWD.setText(message);
            }
        }

        btnOkWD.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (title.equals("Manage Account")) {
                    if (warningDialog.isShowing()) {
                        warningDialog.dismiss();
                    }

                    if (Constant.SCREEN_LAYOUT == 1) {
                        MainActivity.getInstance().loadManageAccountFragment();
//                        MainActivity.getInstance().LoadWebVew(Constant.URL_PAGE_MANAGE_ACCOUNT +"?customerid=" + UserModel.Cust_mst_ID + "&storeno=" + Constant.STOREID);
                    } else if (Constant.SCREEN_LAYOUT == 2) {
                        MainActivityDup.getInstance().showHomePage();
                        MainActivityDup.getInstance().LoadWebVew(Constant.URL + Constant.URL_PAGE_MANAGE_ACCOUNT + "?customerid=" + UserModel.Cust_mst_ID + "&storeno=" + Constant.STOREID);
                    }

                }
//                Edited by Varun for clear the Constant and shared pref
                else if (title.equals("Guest Checkout")){
                    Constant.AppPref.edit().putString("currentCustId", "").putString("favStore", "").commit();
                    Constant.AppPref.edit().putString("email", "").putString("password", "") .putBoolean("ISguest",false).commit();
                    Constant.LHSLIDER_LIST.clear();
                    Constant.AccountList.clear();
                    Constant.AccountList2.clear();
                    Constant.LHSLIDER_LIST.remove("Account");
                    UserModel.Cust_mst_ID = null;
                    Constant.liCardModel.clear();
                    if (warningDialog.isShowing()) {
                        warningDialog.dismiss();
                    }
                }
//                END
                else {
                    if (warningDialog.isShowing()) {
                        warningDialog.dismiss();
                    }
                }
            }
        });

        WindowManager.LayoutParams params = warningDialog.getWindow().getAttributes();
        params.width = WindowManager.LayoutParams.WRAP_CONTENT;
        params.height = WindowManager.LayoutParams.WRAP_CONTENT;
        warningDialog.setContentView(vWarningDialog);

        WindowManager.LayoutParams layoutParam = warningDialog.getWindow().getAttributes();
        warningDialog.getWindow().setAttributes(layoutParam);
        warningDialog.getWindow().setGravity(Gravity.CENTER);

        if (!warningDialog.isShowing()) {
            warningDialog.show();
        }
    }

    public static Dialog wishListDialog;
    public static View vWishListDialog;
    public static TextView tvMsg;
    public static ProgressBar progressBar;

    public static void checkWishListUser() {
        if (Constant.SCREEN_LAYOUT == 1) {
            wishListDialog = new Dialog(MainActivity.getInstance(), R.style.DialogSlideAnim_login);
            wishListDialog.setCanceledOnTouchOutside(false);
            vWishListDialog = LayoutInflater.from(MainActivity.getInstance()).inflate(R.layout.progress_dialog, null);

        } else if (Constant.SCREEN_LAYOUT == 2) {
            wishListDialog = new Dialog(MainActivityDup.getInstance(), R.style.DialogSlideAnim_login);
            wishListDialog.setCanceledOnTouchOutside(false);
            vWishListDialog = LayoutInflater.from(MainActivityDup.getInstance()).inflate(R.layout.progress_dialog, null);

        }

        tvMsg = vWishListDialog.findViewById(R.id.loading_msg);
        tvMsg.setText("This feature only works for registered users.");
        progressBar = vWishListDialog.findViewById(R.id.loader);
        progressBar.setVisibility(View.GONE);

        Thread timerThread = new Thread() {
            public void run() {
                try {
                    sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    if (wishListDialog.isShowing())
                        wishListDialog.dismiss();
                }
            }
        };
        timerThread.start();


        WindowManager.LayoutParams params = wishListDialog.getWindow().getAttributes();
        params.width = WindowManager.LayoutParams.WRAP_CONTENT;
        params.height = WindowManager.LayoutParams.WRAP_CONTENT;

        wishListDialog.setContentView(vWishListDialog);
        wishListDialog.getWindow().setGravity(Gravity.CENTER);
        WindowManager.LayoutParams layoutParam = wishListDialog.getWindow().getAttributes();
        wishListDialog.getWindow().setAttributes(layoutParam);
        wishListDialog.show();
    }


    public static View progress_view;
    public static Dialog progress;

//    public static void showDialog() {
//        //AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.getInstance());
//
//        if (Constant.SCREEN_LAYOUT == 1) {
//            progress = new Dialog(MainActivity.getInstance(), R.style.DialogSlideAnim_login);
//            progress.setCanceledOnTouchOutside(false);
//            progress_view = LayoutInflater.from(MainActivity.getInstance()).inflate(R.layout.progress_dialog, null);
//
//        } else if (Constant.SCREEN_LAYOUT == 2) {
//            progress = new Dialog(MainActivityDup.getInstance(), R.style.DialogSlideAnim_login);
//            progress.setCanceledOnTouchOutside(false);
//            progress_view = LayoutInflater.from(MainActivityDup.getInstance()).inflate(R.layout.progress_dialog, null);
//
//        }
//        //View view = getLayoutInflater().inflate(R.layout.progress);
//        // builder.setView(R.layout.progress_dialog);
//        //Dialog dialog = builder.create();
//
//        WindowManager.LayoutParams params = progress.getWindow().getAttributes();
//        params.width = WindowManager.LayoutParams.WRAP_CONTENT;
//        params.height = WindowManager.LayoutParams.WRAP_CONTENT;
//
//        progress.setContentView(progress_view);
//        progress.getWindow().setGravity(Gravity.CENTER);
//        WindowManager.LayoutParams layoutParam = progress.getWindow().getAttributes();
//        progress.getWindow().setAttributes(layoutParam);
//        progress.show();
//
//        /*if (show){
//            dialog.show();
//            MainActivity.getInstance().getWindow().setFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE,
//                    WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
//        } else{
//            dialog.dismiss();
//            MainActivity.getInstance().getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
//
//        }*/
//    }

//    public static void hideDialog() {
//        if (progress.isShowing())
//            progress.dismiss();
//    }

    public static View vDialog;
    public static Dialog myDialog;
    public static Button btnCommonDialogMessage;
    public static LinearLayout llRootCommonDialog;
    public static TextView tv_root_dialog_payment_process;

    public static void showDialog(String message) {
        if (Constant.SCREEN_LAYOUT == 1) {
            myDialog = new Dialog(MainActivity.getInstance(), R.style.DialogSlideAnim_login);
            myDialog.setCanceledOnTouchOutside(false);
            vDialog = LayoutInflater.from(MainActivity.getInstance()).inflate(R.layout.dialog_common_for_app, null);

        } else if (Constant.SCREEN_LAYOUT == 2) {
            myDialog = new Dialog(MainActivityDup.getInstance(), R.style.DialogSlideAnim_login);
            myDialog.setCanceledOnTouchOutside(false);
            vDialog = LayoutInflater.from(MainActivityDup.getInstance()).inflate(R.layout.dialog_common_for_app, null);
        }
        llRootCommonDialog = vDialog.findViewById(R.id.ll_root_dialog_common_for_app);
        tv_root_dialog_payment_process = vDialog.findViewById(R.id.tv_root_dialog_payment_process);

        btnCommonDialogMessage = vDialog.findViewById(R.id.tv_message_dialog_common_for_app);
        GradientDrawable bgShape = (GradientDrawable) btnCommonDialogMessage.getBackground();
        bgShape.setColor(Color.parseColor(Constant.themeModel.ThemeColor));

        btnCommonDialogMessage.setText(message);
        tv_root_dialog_payment_process.setVisibility(View.GONE);

        WindowManager.LayoutParams params = myDialog.getWindow().getAttributes();
        params.width = WindowManager.LayoutParams.WRAP_CONTENT;
        params.height = WindowManager.LayoutParams.WRAP_CONTENT;

        myDialog.setContentView(vDialog);
        myDialog.getWindow().setGravity(Gravity.CENTER);
        WindowManager.LayoutParams layoutParam = myDialog.getWindow().getAttributes();
        myDialog.getWindow().setAttributes(layoutParam);

        Thread timerThread = new Thread() {
            public void run() {
                try {
//                    sleep(3000);
                    sleep(1500); //cut off half time
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    if (myDialog.isShowing())
                        myDialog.dismiss();
                    Constant.isDialogShow = false;
                    Constant.isDialogShowSeclayout = false;
                }
            }
        };
        timerThread.start();

        if (!myDialog.isShowing())
            myDialog.show();
    }

    public static void showEmailDialog(Context context, ItemDescModel itemDescModel, String fromWhere) {

        final Dialog dialog = new Dialog(context);
        dialog.setContentView(R.layout.email_dialog);
        dialog.getWindow().setLayout(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);

        dialog.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);

        // set the custom dialog components - text, image and button
        TextView tvItemTitleDesc1 = (TextView) dialog.findViewById(R.id.tvItemTitleDesc1);
        TextView tvPrice = (TextView) dialog.findViewById(R.id.tvPrice);
        TextView tvSKU = (TextView) dialog.findViewById(R.id.tvSKU);
        TextView tvCategory = (TextView) dialog.findViewById(R.id.tvCategory);
        TextView tvDiscountName = (TextView) dialog.findViewById(R.id.tvDiscountName);
        TextView tvItemName = (TextView) dialog.findViewById(R.id.tvItemName);
        ImageView iv_close = (ImageView) dialog.findViewById(R.id.iv_close);
        ImageView img_item = (ImageView) dialog.findViewById(R.id.img_item);
        EditText etEmail = (EditText) dialog.findViewById(R.id.etEmail);
        EditText etSecondEmail = (EditText) dialog.findViewById(R.id.etSecondEmail);
        EditText etSubject = (EditText) dialog.findViewById(R.id.etSubject);
        CheckBox checkbox_carbonCopy = (CheckBox) dialog.findViewById(R.id.checkbox_carbonCopy);
        TextInputEditText txtInput_etPersonalMsg = (TextInputEditText) dialog.findViewById(R.id.TextInputet_PersonalMsg);
        Button btnSend = (Button) dialog.findViewById(R.id.btnSend);

        GradientDrawable bgShape = (GradientDrawable) btnSend.getBackground();
        bgShape.setColor(Color.parseColor(Constant.themeModel.ThemeColor));

//
//        if (fromWhere.equalsIgnoreCase("ItemDesc")) {
//            llDescEmail.setVisibility(View.VISIBLE);
//            llDrinkEmail.setVisibility(View.GONE);
//            tvItemTitleDesc1.setText("Email A Friend");
//            btnSend.setVisibility(View.VISIBLE);
//            btnDrinkSend.setVisibility(View.GONE);
//
//        }else if(fromWhere.equalsIgnoreCase("DrinkRecipe")){
//
//            llDrinkEmail.setVisibility(View.VISIBLE);
//            if(UserModel.Cust_mst_ID != null && !Constant.Email.equals("")){
//                etDrinkEmail.setText(Constant.Email);
//            }
//            etDrinkSecondEmail.setText("My drink recipes is enclosed");
//            llDescEmail.setVisibility(View.GONE);
//            tvItemTitleDesc1.setText("Email My Drink Recipes");
//            tvItemTitleDesc1.setTextColor(context.getResources().getColor(R.color.black));
//            btnSend.setVisibility(View.GONE);
//            btnDrinkSend.setVisibility(View.VISIBLE);
//        }

        if (UserModel.Cust_mst_ID != null && !UserModel.Cust_mst_ID.equals("null") && !UserModel.Email.equals("")) {
            etEmail.setText(UserModel.Email);
            etSecondEmail.requestFocus();
        } else {
            etEmail.setHint("Your Email");
            etEmail.requestFocus();
        }

        if (itemDescModel != null) {

            if (itemDescModel.getDepartmentName() != null && !itemDescModel.getDepartmentName().isEmpty()) {
                tvCategory.setText("Category: " + itemDescModel.getDepartmentName().trim());
//                tvCategory.setGravity(Gravity.CENTER);
            }

            if (itemDescModel.getPrice() != null && !itemDescModel.getPrice().isEmpty()) {
//                tvPrice.setText("Price  " + "$" + itemDescModel.getPrice().trim());
                tvPrice.setText("Price  "  + itemDescModel.getPrice().trim());
            }

            if (itemDescModel.getItemMstId() != null && !itemDescModel.getItemMstId().isEmpty()) {
                tvSKU.setText("SKU: " + itemDescModel.getItemMstId().trim());
            }

            if (itemDescModel.getDesc1() != null && !itemDescModel.getDesc1().isEmpty()) {

                SpannableString content = new SpannableString(itemDescModel.getDesc1().trim());
                content.setSpan(new UnderlineSpan(), 0, content.length(), 0);
                tvItemName.setText(content);
//                tvItemName.setText(Utils.capitalizeEachWord(itemDescModel.getDesc1().trim()));
            }

            if (itemDescModel.getDiscountName() != null && !itemDescModel.getDiscountName().isEmpty()) {
                tvDiscountName.setVisibility(View.VISIBLE);
                tvDiscountName.setText(itemDescModel.getDiscountName().trim());
            } else {
                tvDiscountName.setVisibility(View.GONE);
            }

//            if (itemDescModel.getInvLargeImage() != null && !itemDescModel.getInvLargeImage().isEmpty()) {
//                if (itemDescModel.getInvLargeImage().contains("noimage")) {
//
//                    Glide.with(context).load(imgNoImageUrl + itemDescModel.getInvLargeImage())
//                            .placeholder(R.drawable.noimage)
//                            .diskCacheStrategy(DiskCacheStrategy.NONE)
//                            .skipMemoryCache(true).into(img_item);
//                } else {
//                    Glide.with(context).load(imgUrl + itemDescModel.getInvLargeImage())
//                            .placeholder(R.drawable.noimage)/*.placeholder(d)*/
//                            .diskCacheStrategy(DiskCacheStrategy.NONE)
//                            .skipMemoryCache(true).into(img_item);
//                }
//            }

////                    ******************* Edited by Varun on 16 Aug 2022 ************************

            if (itemDescModel.getInvLargeImageFullPath()!=null || !itemDescModel.getInvLargeImageFullPath().isEmpty()) {
                Glide.with(context).load(itemDescModel.getInvLargeImageFullPath())
                        .placeholder(R.drawable.noimage)
                        .error(R.drawable.no_image_new)
                        .diskCacheStrategy(DiskCacheStrategy.NONE)
                        .skipMemoryCache(true).into(img_item);
            }

////        ******************* END ************************

        }

        btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (isValidEmail(etEmail.getText().toString().trim())) {
                    if (isValidEmail(etSecondEmail.getText().toString())) {
                        dialog.dismiss();
                        int carboncopy = 0;
                        if (checkbox_carbonCopy.isChecked()) {
                            carboncopy = 1;
                        } else if (!checkbox_carbonCopy.isChecked()) {
                            carboncopy = 0;
                        }
                        ItemDescriptionsFragment.getInstance().SendEmailData(itemDescModel, etEmail.getText().toString().trim(), etSecondEmail.getText().toString().trim(), txtInput_etPersonalMsg.getText().toString().trim(), etSubject.getText().toString().trim(), carboncopy);

                    } else {
                        etSecondEmail.setError("Please Provide Recipient's Email!");
                        etSecondEmail.requestFocus();
                    }
                } else {
                    etEmail.setError("Please Provide From Email!");
                    etEmail.requestFocus();
                }
            }
        });

        iv_close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });

        dialog.show();

    }

    public static boolean isValidEmail(CharSequence target) {
        return (!TextUtils.isEmpty(target) && Patterns.EMAIL_ADDRESS.matcher(target).matches());
    }


    public static void showDrinkReceipeDialog(Context context, List<ItemDescModel> drinkReceipeList, ItemDescModel mainItemDescModel) {

        final Dialog dialog = new Dialog(context);
        dialog.setContentView(R.layout.drinkreceipedialog);
        dialog.getWindow().setLayout(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);

//        ll_checkbox = (LinearLayout) dialog.findViewById(R.id.ll_checkbox);
        llBackEmail = (LinearLayout) dialog.findViewById(R.id.llBackEmail);
        LinearLayout llDrinkReceipe = (LinearLayout) dialog.findViewById(R.id.llDrinkReceipe);
        LinearLayout llDrinkReceipeSendEmail = (LinearLayout) dialog.findViewById(R.id.llDrinkReceipeSendEmail);
        btnClose = (Button) dialog.findViewById(R.id.btnClose);
        Button btnBack = (Button) dialog.findViewById(R.id.btnBack);

        ImageView iv_close = (ImageView) dialog.findViewById(R.id.iv_close);
        Drawable myDrawabledesc ;
        if (Constant.SCREEN_LAYOUT==1) {
             myDrawabledesc = MainActivity.getInstance().getResources().getDrawable(R.drawable.ic_close);
        }else{
             myDrawabledesc = MainActivityDup.getInstance().getResources().getDrawable(R.drawable.ic_close);

        }
        myDrawabledesc.setColorFilter(new LightingColorFilter(Color.BLACK, Color.BLACK));

        tvTitle = (TextView) dialog.findViewById(R.id.tvTitle);
        ll_desc = (NestedScrollView) dialog.findViewById(R.id.ll_desc);
        tvDescription = (TextView) dialog.findViewById(R.id.tvDescription);
        TextView tvEmailReceipe = (TextView) dialog.findViewById(R.id.tvEmailReceipe);
//        LinearLayout llDrinkEmail = (LinearLayout)dialog.findViewById(R.id.llDrinkEmail);
//        LinearLayout llDescEmail = (LinearLayout)dialog.findViewById(R.id.llDescEmail);
        EditText etDrinkEmail = (EditText) dialog.findViewById(R.id.etDrinkEmail);
        EditText etDrinkSecondEmail = (EditText) dialog.findViewById(R.id.etDrinkSecondEmail);
        Button btnDrinkSend = (Button) dialog.findViewById(R.id.btnDrinkSend);
        TextView tvPrice = (TextView) dialog.findViewById(R.id.tvPrice);
        TextView tvSKU = (TextView) dialog.findViewById(R.id.tvSKU);
        TextView tvCategory = (TextView) dialog.findViewById(R.id.tvCategory);
        TextView tvDiscountName = (TextView) dialog.findViewById(R.id.tvDiscountName);
        TextView tvItemName = (TextView) dialog.findViewById(R.id.tvItemName);
        ImageView img_item = (ImageView) dialog.findViewById(R.id.img_item);

        recyclerview = (RecyclerView) dialog.findViewById(R.id.recyclerview);
        drinkReceipeAdapter = new DrinkReceipeAdapter(context, drinkReceipeList);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(context.getApplicationContext(), LinearLayoutManager.VERTICAL, false);
        recyclerview.setLayoutManager(layoutManager);
        recyclerview.setHasFixedSize(true);
        recyclerview.setAdapter(drinkReceipeAdapter);


        btnClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ItemDescriptionsFragment.getInstance().displayeThemeColorOnButton();
                dialog.dismiss();
            }
        });

        iv_close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ItemDescriptionsFragment.getInstance().displayeThemeColorOnButton();
                dialog.dismiss();
            }
        });

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                tvTitle.setText("Drink Recipes");
                recyclerview.setVisibility(View.VISIBLE);
                ll_desc.setVisibility(View.GONE);
                btnClose.setVisibility(View.VISIBLE);
                llBackEmail.setVisibility(View.GONE);
                drinkReceipeAdapter.notifyDataSetChanged();
            }
        });


        tvEmailReceipe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                llDrinkReceipeSendEmail.setVisibility(View.VISIBLE);
                llDrinkReceipe.setVisibility(View.GONE);

                GradientDrawable border = new GradientDrawable();
                border.setColor(context.getResources().getColor(R.color.transparent)); //white background
                border.setStroke(1, context.getResources().getColor(R.color.light_grey)); //black border with full opacity

                tvTitle.setText("Email My Drink Recipes");

                if (UserModel.Cust_mst_ID != null && !UserModel.Cust_mst_ID.equals("null") && !UserModel.Email.equals("")) {
                    etDrinkEmail.setText(UserModel.Email);
                } else {
                    etDrinkEmail.setHint("To Email Address");
                }
                etDrinkSecondEmail.setText("My drink recipes is enclosed");
                etDrinkSecondEmail.setTypeface(null, Typeface.BOLD);

                if (mainItemDescModel != null) {

                    if (mainItemDescModel.getDepartmentName() != null && !mainItemDescModel.getDepartmentName().isEmpty()) {
                        tvCategory.setText("Category: " + mainItemDescModel.getDepartmentName().trim());
                    }

                    if (mainItemDescModel.getPrice() != null && !mainItemDescModel.getPrice().isEmpty()) {
                        tvPrice.setText("Price  " + mainItemDescModel.getPrice().trim());
                    }

                    if (mainItemDescModel.getItemMstId() != null && !mainItemDescModel.getItemMstId().isEmpty()) {
                        tvSKU.setText("SKU: " + mainItemDescModel.getItemMstId().trim());
                    }

                    if (mainItemDescModel.getDesc1() != null && !mainItemDescModel.getDesc1().isEmpty()) {

                        SpannableString content = new SpannableString(mainItemDescModel.getDesc1().trim());
                        content.setSpan(new UnderlineSpan(), 0, content.length(), 0);
                        tvItemName.setText(content);
                    }

                    if (mainItemDescModel.getDiscountName() != null && !mainItemDescModel.getDiscountName().isEmpty()) {
                        tvDiscountName.setVisibility(View.VISIBLE);
                        tvDiscountName.setText(mainItemDescModel.getDiscountName().trim());
                    } else {
                        tvDiscountName.setVisibility(View.GONE);
                    }

                    if  (mainItemDescModel.getInvLargeImageFullPath()!=null || !mainItemDescModel.getInvLargeImageFullPath().isEmpty()
                            || !mainItemDescModel.getInvLargeImageFullPath().equals("") ) {
//                        if (mainItemDescModel.getInvLargeImage().contains("noimage")) {
//
//                            Glide.with(context).load(imgNoImageUrl + mainItemDescModel.getInvLargeImage())
//                                    .placeholder(R.drawable.noimage)
//                                    .diskCacheStrategy(DiskCacheStrategy.NONE)
//                                    .skipMemoryCache(true).into(img_item);
//                        } else {
//                            Glide.with(context).load(imgUrl + mainItemDescModel.getInvLargeImage())
                            Glide.with(context).load(mainItemDescModel.getInvSmallImageFullPath())
                                    .placeholder(R.drawable.noimage)/*.placeholder(d)*/
                                    .diskCacheStrategy(DiskCacheStrategy.NONE)
                                    .skipMemoryCache(true).into(img_item);
//                        }
                    }
                }
            }
        });


        btnDrinkSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (etDrinkEmail.getText().toString().trim().isEmpty()) {
                    etDrinkEmail.requestFocus();
//                    etDrinkEmail.setError("Please Provide To Email!");
                    etDrinkEmail.setError("Please enter an e-mail.");
                } else if (!etDrinkEmail.getText().toString().trim().equals("") &&
                        !etDrinkSecondEmail.getText().toString().trim().equals("")) {

                    ItemDescriptionsFragment.getInstance().onSendEmailDrinkRecipe(mainItemDescModel.getItemMstId().trim(),
                            etDrinkEmail.getText().toString().trim(),
                            etDrinkSecondEmail.getText().toString().trim(), drinkReceipeId);
                    dialog.dismiss();
                }
            }
        });

        dialog.show();
    }


    public static void NotifyDrinkReceipe(ItemDescModel itemDescModel1) {

        if (itemDescModel1 != null) {
            tvTitle.setText(itemDescModel1.getDesc1());
            recyclerview.setVisibility(View.GONE);
            ll_desc.setVisibility(View.VISIBLE);
            btnClose.setVisibility(View.GONE);
            llBackEmail.setVisibility(View.VISIBLE);
            tvDescription.setText(Html.fromHtml(itemDescModel1.getEmailContent().toString()));
            drinkReceipeId = itemDescModel1.getId();
        }

    }

    public static void Notify(List<lstInventoryModel> itemDescModel1, int clickedPosition) {

        if (itemDescModel1 != null) {
            Constant.check_multi_position=clickedPosition;
            Toast.makeText(fasterDialogContext, ""+itemDescModel1.get(clickedPosition).getExtendDesc(), Toast.LENGTH_SHORT).show();
//            Constant.Test_SKU=itemDescModel1.get(clickedPosition).getItemMstId();
        }

    }


    public static void enterCustomerNote(Context context) {

        final Dialog noteDialog = new Dialog(context);
        noteDialog.setContentView(R.layout.customer_note);
        noteDialog.getWindow().setLayout(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        int count = 0;

        TextView txtCounter = (TextView) noteDialog.findViewById(R.id.txtCounter);
        TextInputEditText textEdtCustomerNote = (TextInputEditText) noteDialog.findViewById(R.id.textEdtCustomerNote);
        if (!PaymentFragment.getInstance().customernote.isEmpty()) {
            textEdtCustomerNote.setText(PaymentFragment.getInstance().customernote);
            textEdtCustomerNote.setSelection(PaymentFragment.getInstance().customernote.length());
            count = PaymentFragment.getInstance().customernote.length();
            txtCounter.setText(count + " of 300 characters");
        } else {
            txtCounter.setText(count + " of 300 characters");
        }

        Button btn_save = (Button) noteDialog.findViewById(R.id.btn_save);
        Button btn_cancel = (Button) noteDialog.findViewById(R.id.btn_cancel);

        ImageView iv_close = (ImageView) noteDialog.findViewById(R.id.iv_close);

        iv_close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                noteDialog.dismiss();
            }
        });

        btn_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                noteDialog.dismiss();
            }
        });

        btn_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PaymentFragment.getInstance().customernote = Objects.requireNonNull(textEdtCustomerNote.getText()).toString();
                noteDialog.dismiss();
                JSONObject jsonObjRequest = saveCustomerNote(PaymentFragment.getInstance().customernote);

                com.android.volley.RequestQueue requestQueue = Volley.newRequestQueue(context);

//                final String mRequestBody = jsonObjRequest.toString();

                JsonObjectRequest jsonobj = new JsonObjectRequest(Request.Method.POST, Constant.WS_SAVE_CUSTOMER_NOTES, jsonObjRequest,
                        new Response.Listener<JSONObject>() {
                            @Override
                            public void onResponse(JSONObject response) {
                                PaymentFragment.getInstance().savednoteStatus = "Y";
                                noteDialog.dismiss();
//                                saveNoteSuccessResponse(response);
                            }
                        },
                        new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {

                            }
                        }
                ) {
                    //here I want to post data to sever
                };
                requestQueue.add(jsonobj);
            }

//            private void saveNoteSuccessResponse(JSONObject s) {
//                noteDialog.dismiss();
//            }
        });

        textEdtCustomerNote.addTextChangedListener(new TextWatcher() {

            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int start, int before, int count) {

                int length = textEdtCustomerNote.length();
                String convert = String.valueOf(length);
                txtCounter.setText(convert + " of 300 characters");
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        noteDialog.show();
    }

    public static JSONObject saveCustomerNote(String customerNote) {
        JSONObject formJsonObject = new JSONObject();

        try {
            String customerId;
            formJsonObject.put("storeno", Constant.STOREID);
            if (UserModel.Cust_mst_ID != null)
                customerId = UserModel.Cust_mst_ID;
            else
                customerId = "0";
            formJsonObject.put("CustomerID", customerId);
            formJsonObject.put("CustomerNotes", customerNote);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return formJsonObject;
    }

    public static void saveArrayList(String key, ArrayList<String> list, Context mContext) {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(mContext);
        SharedPreferences.Editor editor = prefs.edit();
        Gson gson = new Gson();
        String json = gson.toJson(list);
        editor.putString(key, json);
        editor.apply();     // This line is IMPORTANT !!!
    }

    public static ArrayList<String> getArrayList(String key, Context context) {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
        Gson gson = new Gson();
        String json = prefs.getString(key, null);
        Type type = new TypeToken<ArrayList<String>>() {
        }.getType();
        return gson.fromJson(json, type);
    }

    public static void editCardDetailOnFile(String cardno, Context context) {

        final Dialog editCardDialog = new Dialog(context);
        editCardDialog.setContentView(R.layout.edit_card_on_file);
        editCardDialog.getWindow().setLayout(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        editCardDialog.getWindow().setGravity(Gravity.TOP);

        java.util.ArrayList<String> monthString = new java.util.ArrayList<>();
        Calendar calendar2 = Calendar.getInstance();
        int month = calendar2.get(Calendar.MONTH);

        Log.e("Month", "editCardDetailOnFile: "+monthString );
        Log.e("Month", "editCardDetailOnFile:2 "+month );

        java.util.ArrayList<String> yearString = new java.util.ArrayList<>();
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        String string_year = String.valueOf(year);

        Log.e("Year", "editCardDetailOnFile:6 "+yearString );
        Log.e("Year", "editCardDetailOnFile:7 "+year );

        for (int i = 0; i <= 10; i++) {

            if (i == 0) {
                yearString.add("Select");
            } else if (i == 1) {
                String s = String.valueOf(year);
                yearString.add(s);
            } else {
                year = year + 1;
                String s1 = String.valueOf(year);
                yearString.add(s1);
            }
        }

        Button btn_publish = (Button) editCardDialog.findViewById(R.id.btn_publish);
//        btn_publish.setEnabled(false);

        ImageView iv_close = (ImageView) editCardDialog.findViewById(R.id.iv_close);
        RelativeLayout rv_MainLayout = editCardDialog.findViewById(R.id.rv_MainLayout);
        Spinner spinner_month = (Spinner) editCardDialog.findViewById(R.id.spinner_month);
        spinner_month.setEnabled(true);
        spinner_month.setSelection(0);

        Spinner spinner_year = (Spinner) editCardDialog.findViewById(R.id.spinner_year);
        spinner_year.setEnabled(true);

        if (Constant.SCREEN_LAYOUT == 1) {

            ArrayAdapter<String> adapter = new ArrayAdapter<String>(MainActivity.getInstance(), android.R.layout.simple_spinner_item, yearString);
            // Specify the layout to use when the list of choices appears
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spinner_year.setAdapter(adapter);
            spinner_year.setSelection(0);

        } else if (Constant.SCREEN_LAYOUT == 2) {

            ArrayAdapter<String> adapter = new ArrayAdapter<String>(MainActivityDup.getInstance(), android.R.layout.simple_spinner_item, yearString);
            // Specify the layout to use when the list of choices appears
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spinner_year.setAdapter(adapter);
            spinner_year.setSelection(0);

        }


        EditText et_cart_number = (EditText) editCardDialog.findViewById(R.id.et_cart_number);
        TextView card_number = editCardDialog.findViewById(R.id.card_number);
        card_number.setText("("+cardno+")");
//        et_cart_number.setText(cardno);
        et_cart_number.setCursorVisible(true);
        et_cart_number.setHint("Enter Card Number");
        et_cart_number.setFocusable(true);
        et_cart_number.requestFocus();
        et_cart_number.setText("");

//        et_cart_number.setFocusable(false);

//        et_cart_number.setSelection(et_cart_number.getText().length());


        et_cart_number.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                et_cart_number.setCursorVisible(true);

                spinner_month.setEnabled(true);
                spinner_month.setSelection(0);
                spinner_year.setEnabled(true);
                spinner_year.setSelection(0);
//                btn_publish.setEnabled(false);
                return false;
            }
        });

        rv_MainLayout.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent event) {

                String card;
                card = et_cart_number.getText().toString();
                if (!Validator.validateCard(card.trim())) {
                    et_cart_number.requestFocus();
//                    et_cart_number.setError("Invalid Card");
                    et_cart_number.setSelection(et_cart_number.getText().length());
                    spinner_month.setEnabled(true);
                    spinner_month.setSelection(0);
                    spinner_year.setEnabled(true);
                    spinner_year.setSelection(0);

                } else {
                    et_cart_number.setImeOptions(EditorInfo.IME_ACTION_DONE);
                    hidekeyborad(context, view);
                    et_cart_number.clearFocus();
                    et_cart_number.setCursorVisible(false);
                    spinner_month.setEnabled(true);
                    spinner_year.setEnabled(true);
                    spinner_month.requestFocus();

                }
                return false;

            }
        });

        et_cart_number.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView view, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_DONE || actionId == EditorInfo.IME_ACTION_NEXT) {
                    String card;
                    card = et_cart_number.getText().toString();
                    if (!Validator.validateCard(card.trim())) {
                        et_cart_number.requestFocus();
//                        et_cart_number.setError("Invalid Card");
                        spinner_month.setEnabled(true);
                        spinner_month.setSelection(0);
                        spinner_year.setEnabled(true);
                        spinner_year.setSelection(0);
//                            btn_publish.setEnabled(false);
                    } else {
                        et_cart_number.setImeOptions(EditorInfo.IME_ACTION_DONE);
                        hidekeyborad(context, view);
                        et_cart_number.clearFocus();
                        et_cart_number.setCursorVisible(false);
                        spinner_month.setEnabled(true);
                        spinner_year.setEnabled(true);
//                            btn_publish.setEnabled(true);
                        spinner_month.requestFocus();
//                            etCvv.setFocusable(true);
//                            etCvv.requestFocus();
                    }
                    return true;
                } else {

                }
                return false;
            }
        });


        et_cart_number.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean hasFocus) {

                if (hasFocus) {
                    String card;
                    card = et_cart_number.getText().toString();

                    if (card.contains("****")) {
                        spinner_month.setEnabled(true);
                        spinner_year.setEnabled(true);
//                        btn_publish.setEnabled(false);
                        et_cart_number.setCursorVisible(false);
                    } else {
                        if (!Validator.validateCard(card.trim())) {
                            et_cart_number.requestFocus();
//                            et_cart_number.setError("Invalid Card");
                            spinner_month.setEnabled(true);
                            spinner_month.setSelection(0);
                            spinner_year.setEnabled(true);
                            spinner_year.setSelection(0);
//                            btn_publish.setEnabled(false);
                        } else {
                            //valid card
                            et_cart_number.setImeOptions(EditorInfo.IME_ACTION_DONE);
                            hidekeyborad(context, view);
                            et_cart_number.setCursorVisible(false);
//                            spinner_month.setEnabled(true);
//                            spinner_year.setEnabled(true);
//                            btn_publish.setEnabled(true);
//                            spinner_month.requestFocus();

                        }
                    }

                }
            }
        });


        et_cart_number.addTextChangedListener(new TextWatcher() {
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
                if (count <= et_cart_number.getText().toString().length()
                        && (et_cart_number.getText().toString().length() == 4
                        || et_cart_number.getText().toString().length() == 9
                        || et_cart_number.getText().toString().length() == 14)) {
                    et_cart_number.setText(et_cart_number.getText().toString() + " ");
                    int pos = et_cart_number.getText().length();
                    et_cart_number.setSelection(pos);
                } else if (count >= et_cart_number.getText().toString().length()
                        && (et_cart_number.getText().toString().length() == 4
                        || et_cart_number.getText().toString().length() == 9
                        || et_cart_number.getText().toString().length() == 14)) {
                    et_cart_number.setText(et_cart_number.getText().toString().substring(0, et_cart_number.getText().toString().length() - 1));
                    int pos = et_cart_number.getText().length();
                    et_cart_number.setSelection(pos);
                }
                count = et_cart_number.getText().toString().length();

            }
        });


        btn_publish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String card;
                card = et_cart_number.getText().toString();

                if (card.contains("****")) {
                    spinner_month.setEnabled(true);
                    spinner_year.setEnabled(true);

                }else if (card == "" || card.isEmpty()){
                    et_cart_number.setError("Enter Card Number");
                }else {
                    if (!Validator.validateCard(card.trim())) {
                        et_cart_number.requestFocus();
                        et_cart_number.setError("Invalid Card");
                        spinner_month.setEnabled(true);
                        spinner_month.setSelection(0);
                        spinner_year.setEnabled(true);
                        spinner_year.setSelection(0);
//                        btn_publish.setEnabled(false);
                    } else {
                        et_cart_number.setImeOptions(EditorInfo.IME_ACTION_DONE);
                        et_cart_number.clearFocus();
                        et_cart_number.setCursorVisible(false);
                        hidekeyborad(context, view);

                        if (!spinner_month.isEnabled() && !spinner_year.isEnabled()) {
                            spinner_month.setEnabled(true);
                            spinner_year.setEnabled(true);
//                        btn_publish.setEnabled(true);
                            spinner_month.requestFocus();

                        } else if (spinner_month.isEnabled() && spinner_year.isEnabled()) {

                            int selctedYearPos = spinner_year.getSelectedItemPosition();
                            String selectedYear = spinner_year.getItemAtPosition(selctedYearPos).toString();

                            int selctedMonthPos = spinner_month.getSelectedItemPosition();
                            String selectedMonth = spinner_month.getItemAtPosition(selctedMonthPos).toString();

                            int monthPos = selctedMonthPos-1;
                           Log.e("Year", "editCardDetailOnFile:5 "+selctedYearPos );
                            Log.e("Year", "editCardDetailOnFile:5 "+selectedYear );

                            if (!selectedYear.isEmpty() && !selectedMonth.isEmpty()) {
                                if (selectedYear.equals("Select") || selectedMonth.equals("Select")) {
                                    Utils.showValidationDialog(context, "Enter Card Expiration.", "");
                                }
//                                 Edited by Varun for Credit card expire validation of month with Year
                                else if (string_year.equals(selectedYear)){
                                    Log.e("month", "editCardDetailOnFile:3 "+month );
                                    Log.e("month", "editCardDetailOnFile:4 "+monthPos );

                                    if (monthPos >= month){
                                        String cardNoNew = et_cart_number.getText().toString();
                                        CardOnFileFragment.getInstance().callUpdateCreditCardWS(cardNoNew, selectedMonth, selectedYear, editCardDialog);
                                    }else{
                                        Utils.showValidationDialog(context, "Card already expired.", "");
                                    }
                                }
////                                END
                                else {
                                    String cardNoNew = et_cart_number.getText().toString();
                                    CardOnFileFragment.getInstance().callUpdateCreditCardWS(cardNoNew, selectedMonth, selectedYear, editCardDialog);
                                }
                            } else {
                                Utils.showValidationDialog(context, "Enter Card Expiration.", "");
                            }

                        }

                    }

                }

//                DialogUtils.underDevelopmentDialog(context);

//                    CardOnFileFragment.getInstance().callUpdateCreditCardWS(cardNoNew,selectedMonth,selectedYear,editCardDialog);
//                    editCardDialog.dismiss();
            }
        });


        iv_close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editCardDialog.dismiss();
            }
        });
        editCardDialog.show();
    }


    private static void hidekeyborad(Context context, View view) {

        InputMethodManager imm = (InputMethodManager) view.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
        if (imm != null) {
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }

    public static void showConfirmation(Context context, String titlemsg) {

        //change to new reorder
        final Dialog dialog = new Dialog(context);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.dialog_confirmation);
        TextView txtErrorName = (TextView) dialog.findViewById(R.id.tv_sub_title_warning_dialog);
        Button btnOK = (Button) dialog.findViewById(R.id.btn_OK);
        GradientDrawable bgShape = (GradientDrawable) btnOK.getBackground();

        if (titlemsg.equalsIgnoreCase(context.getResources().getString(R.string.str_delete_confirmation))) {
            txtErrorName.setText(context.getResources().getString(R.string.str_delete_confirmation));
        }

        bgShape.setColor(Color.parseColor(Constant.themeModel.ThemeColor));
        btnOK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();

                if (titlemsg.equalsIgnoreCase(context.getResources().getString(R.string.str_delete_confirmation))) {
                    CardOnFileFragment.getInstance().callDeleteCreditcardWS();
                }

            }
        });
        Button btn_Cancel = (Button) dialog.findViewById(R.id.btn_Cancel);
        GradientDrawable bgShapec = (GradientDrawable) btn_Cancel.getBackground();
        bgShapec.setColor(Color.parseColor(Constant.themeModel.ThemeColor));
        btn_Cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        dialog.show();
    }

    public static void underDevelopmentDialog(Context context) {
        View underDevlopvDialog = null;

        final Dialog underDevDialog = new Dialog(context, R.style.DialogSlideAnim_login);
        underDevDialog.setCanceledOnTouchOutside(false);

        if (Constant.SCREEN_LAYOUT == 1) {
            underDevlopvDialog = LayoutInflater.from(MainActivity.getInstance()).inflate(R.layout.under_development, null);
        } else if (Constant.SCREEN_LAYOUT == 2) {
            underDevlopvDialog = LayoutInflater.from(MainActivityDup.getInstance()).inflate(R.layout.under_development, null);
        }

        Button btn_ok = (Button) underDevlopvDialog.findViewById(R.id.btn_ok);
//        TextView tv_title = (TextView) underDevDialog.findViewById(R.id.tv_title);

        btn_ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                underDevDialog.dismiss();
            }
        });

        WindowManager.LayoutParams params = underDevDialog.getWindow().getAttributes();
        params.width = WindowManager.LayoutParams.MATCH_PARENT;
        params.height = WindowManager.LayoutParams.WRAP_CONTENT;

        underDevDialog.setContentView(underDevlopvDialog);
        underDevDialog.getWindow().setGravity(Gravity.CENTER);
        WindowManager.LayoutParams layoutParam = underDevDialog.getWindow().getAttributes();
        underDevDialog.getWindow().setAttributes(layoutParam);

        underDevDialog.show();
    }

    public static void showPickupTimeDialog(Context context, ArrayList<PickupModel> storeTimelist, boolean isStoreClosedtoday, boolean isStoreClosedtomorrow, String today, String tomorrowDay) {
        selectedPickupModel = null;
        ArrayList<PickupModel> tenStorelist = new ArrayList<>();
        final Dialog dialog = new Dialog(context);
        dialog.setContentView(R.layout.pickuptime_dialog);
        dialog.getWindow().setLayout(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);

        LinearLayout llTitle = (LinearLayout) dialog.findViewById(R.id.llTitle);
        Drawable roundDrawable = context.getResources().getDrawable(R.drawable.only_three_side_border_with_radius_nobackground);
        roundDrawable.setColorFilter(Color.parseColor(Constant.themeModel.ThemeColor), PorterDuff.Mode.SRC_ATOP);
        llTitle.setBackground(roundDrawable);

        TextView tvTitle = (TextView) dialog.findViewById(R.id.tvTitle);
        tvTitle.setText("Desired Pickup Time");
        tvTitle.setTextColor(Color.WHITE);
        tvTitle.setBackgroundColor(Color.parseColor(Constant.themeModel.ThemeColor));

        Button btnBack = (Button) dialog.findViewById(R.id.btnBack);
        Button btnSubmit = (Button) dialog.findViewById(R.id.btnSubmit);
        TextView tvMoreTimes = (TextView) dialog.findViewById(R.id.tvMoreTimes);
        tvMoreTimes.setTextColor(Color.parseColor(Constant.themeModel.ThemeColor));

        if (isStoreClosedtoday && isStoreClosedtomorrow) {
            tvMoreTimes.setVisibility(View.INVISIBLE);
        } else if (storeTimelist.size() <= 10) {
            tvMoreTimes.setVisibility(View.INVISIBLE);
        } else {
            if (tvMoreTimes.getVisibility() == View.INVISIBLE) {
                tvMoreTimes.setVisibility(View.VISIBLE);
            }
        }

        RecyclerView recyclerview = (RecyclerView) dialog.findViewById(R.id.recyclerview);
        int j = 1;
        String tomorrow = Utils.getNextDay(j);

        if (tenStorelist != null && storeTimelist != null && storeTimelist.size() > 0) {

            if (isStoreClosedtoday) {
                PickupModel pickupModel1 = new PickupModel();
                pickupModel1.setTime("Closed");
                pickupModel1.setDay(today);
                pickupModel1.setCurrentday("Today");
                tenStorelist.add(pickupModel1);

                if (storeTimelist.size() > 11) {
                    for (int i = 0; i < 11; i++) {
                        tenStorelist.add(storeTimelist.get(i));
                    }
                } else {
                    for (int i = 0; i < storeTimelist.size(); i++) {
                        tenStorelist.add(storeTimelist.get(i));
                    }
                }

            } else if (isStoreClosedtomorrow) {

                if (storeTimelist.size() > 11) {
                    for (int i = 0; i < 11; i++) {
                        tenStorelist.add(storeTimelist.get(i));
                    }
                } else {
                    for (int i = 0; i < storeTimelist.size(); i++) {
                        tenStorelist.add(storeTimelist.get(i));
                    }
                }

                PickupModel pickupModel1 = new PickupModel();
                pickupModel1.setTime("Closed");
                pickupModel1.setDay(tomorrowDay);
                pickupModel1.setCurrentday("Tomorrow");
                tenStorelist.add(pickupModel1);
            } else {
                if (storeTimelist.size() > 10) {
                    for (int i = 0; i < 10; i++) {
                        tenStorelist.add(storeTimelist.get(i));
                    }
                } else {
                    for (int i = 0; i < storeTimelist.size(); i++) {
                        tenStorelist.add(storeTimelist.get(i));
                    }
                }
            }

        } else {
            PickupModel pickupModel = new PickupModel();
            PickupModel pickupModel1 = new PickupModel();

            if (isStoreClosedtoday) {
                pickupModel.setTime("Closed");
                pickupModel.setDay(today);
                pickupModel.setCurrentday("Today");
                tenStorelist.add(pickupModel);
            }

            if (isStoreClosedtomorrow) {
                pickupModel1.setTime("Closed");
                pickupModel1.setDay(tomorrowDay);
                pickupModel1.setCurrentday("Tomorrow");
                tenStorelist.add(pickupModel1);
            }

//            PickupModel pickupModel = new PickupModel();
//            PickupModel pickupModel1 = new PickupModel();
//            pickupModel.setTime("Closed");
//            pickupModel.setDay(today);
//            pickupModel.setCurrentday("Today");
//            pickupModel1.setTime("Closed");
//            pickupModel1.setDay(tomorrowDay);
//            pickupModel1.setCurrentday("Tomorrow");
//            tenStorelist.add(pickupModel);
//            tenStorelist.add(pickupModel1);
        }

        pickupTimeAdapter = new PickupTimeAdapter(context, tenStorelist, tomorrow);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(context.getApplicationContext(), LinearLayoutManager.VERTICAL, false);
        recyclerview.setLayoutManager(layoutManager);
        recyclerview.setHasFixedSize(true);
        recyclerview.setAdapter(pickupTimeAdapter);

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
                isMoreitemClicked = false;
            }
        });

        tvMoreTimes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isMoreitemClicked = true;

                if (isStoreClosedtoday) {

                    if (tenStorelist.size() > 0) {
                        PickupModel pickupModel = new PickupModel();
                        pickupModel.setDay(tenStorelist.get(0).getDay());
                        pickupModel.setCurrentday(tenStorelist.get(0).getCurrentday());
                        pickupModel.setTime(tenStorelist.get(0).getTime());
                        if (storeTimelist != null) {
                            storeTimelist.add(0, pickupModel);
                        }
                    }
                } else if (isStoreClosedtomorrow) {

                    if (tenStorelist.size() > 0) {
                        PickupModel pickupModel = new PickupModel();
                        pickupModel.setTime("Closed");
                        pickupModel.setDay(tomorrowDay);
                        pickupModel.setCurrentday("Tomorrow");
                        if (storeTimelist != null) {
                            storeTimelist.add(pickupModel);
                        }
                    }
                }
                pickupTimeAdapter = new PickupTimeAdapter(context, storeTimelist, tomorrow);
                RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(context.getApplicationContext(), LinearLayoutManager.VERTICAL, false);
                recyclerview.setLayoutManager(layoutManager);
                recyclerview.setHasFixedSize(true);
                recyclerview.setAdapter(pickupTimeAdapter);
                tvMoreTimes.setVisibility(View.INVISIBLE);

//                else{
//                    dialog.dismiss();
//                }

            }
        });

        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

//                PickupModel pickupModel = (PickupModel) .getTag();
//                if(selectedPickupModel != null){
                dialog.dismiss();
//                }

//                 Edited by Varun for Pick up time issue in order summery screen
                Constant.pick_up= true;
//                 END

                if (CardFragment.getInstance().isBypassDeliveryOption) {
                    CardFragment.getInstance().callNextFromCart();
                    CardFragment.getInstance().isBypassDeliveryOption = false;
                } else {
                    DeliveryOptionsFragment.getInstance().insertTempOrderWithBillingAndShippingData();
                }
            }
        });

        if (pickupTimeAdapter != null && pickupTimeAdapter.getItemCount() > 0) {
            dialog.show();
        }
    }

    public static void showStartReturnDialog(Context context, List<String> returnProcessModelList, LstOrderTem lstOrderTem_forstartReturn) {

        List<String> finalreturnProcessModelList = new ArrayList<>();

        finalreturnProcessModelList.add("Arrived Late Today");
        finalreturnProcessModelList.add("Damaged");
        finalreturnProcessModelList.add("Defective");
        finalreturnProcessModelList.add("Duplicate Item");
        finalreturnProcessModelList.add("Incorrect Item");
        finalreturnProcessModelList.add("No longer wanted");
        finalreturnProcessModelList.add("No longer wanted");
        finalreturnProcessModelList.add("Not as Described");
        finalreturnProcessModelList.add("Other");

        int count = 0;
        String imgUrl = Constant.IMG_BASE + Constant.IMG_BASE_URL + Constant.STOREID + "/";

        final int[] qtyCount = {1};
        StartReturndialog = new Dialog(context);
        final String[] selectedReason = {""};
        String comment = "";
//        String months[] = {"3 Months","6 Months","12 Months","All"};
        StartReturndialog.setContentView(R.layout.start_return_dialog);
        Objects.requireNonNull(StartReturndialog.getWindow()).setLayout(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);

        LinearLayout llstartreturn_title = (LinearLayout) StartReturndialog.findViewById(R.id.llstartreturn_title);
        llstartreturn_title.setBackgroundColor(Color.parseColor(Constant.themeModel.ThemeColor));

        LinearLayout llmainReturn = (LinearLayout) StartReturndialog.findViewById(R.id.llmainReturn);
        llmainReturn.setBackgroundColor(Color.parseColor(Constant.themeModel.Backgroundcolor));

        Button btnContinue = (Button) StartReturndialog.findViewById(R.id.btnContinue);

        ImageView iv_close = (ImageView) StartReturndialog.findViewById(R.id.iv_close);
        ImageView ivProdctImage = (ImageView) StartReturndialog.findViewById(R.id.ivProdctImage);
        ImageView img_plus = (ImageView) StartReturndialog.findViewById(R.id.img_plus);
        ImageView img_minus = (ImageView) StartReturndialog.findViewById(R.id.img_minus);
        TextInputEditText textEdtCustomerNote = (TextInputEditText) StartReturndialog.findViewById(R.id.textEdtCustomerNote);

        TextView tvDescription = (TextView) StartReturndialog.findViewById(R.id.tvDescription);
        TextView tv_item_quantity_lower = (TextView) StartReturndialog.findViewById(R.id.tv_item_quantity_lower);
        TextView tv_item_quantity_upper = (TextView) StartReturndialog.findViewById(R.id.tv_item_quantity_upper);

        TextView txtCounter = (TextView) StartReturndialog.findViewById(R.id.txtCounter);

        String commenttxt = Objects.requireNonNull(textEdtCustomerNote.getText()).toString();

        if (!commenttxt.isEmpty()) {
            count = textEdtCustomerNote.length();
            txtCounter.setText(count + " of 200 characters");
        } else {
            txtCounter.setText(count + " of 200 characters");
        }

        textEdtCustomerNote.addTextChangedListener(new TextWatcher() {

            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int start, int before, int count) {

                int length = textEdtCustomerNote.length();
                String convert = String.valueOf(length);
                txtCounter.setText(convert + " of 200 characters");
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });


        if (lstOrderTem_forstartReturn.getLstReturnItems() != null
                && lstOrderTem_forstartReturn.getLstReturnItems().size() > 0) {

            int actualQty = getactualQuntity(lstOrderTem_forstartReturn);

            tv_item_quantity_lower.setText("" + actualQty);

        } else {

            if (lstOrderTem_forstartReturn.getQty() != null && !lstOrderTem_forstartReturn.getQty().isEmpty()) {
                tv_item_quantity_lower.setText(lstOrderTem_forstartReturn.getQty());
            }
        }

        img_plus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (lstOrderTem_forstartReturn.getLstReturnItems() != null
                        && lstOrderTem_forstartReturn.getLstReturnItems().size() > 0) {

//                    String returnedQty = "";
//
//                    if(lstOrderTem_forstartReturn.getLstReturnItems().get(0).getRemainingQty().contains("-")){
//                        returnedQty = lstOrderTem_forstartReturn.getLstReturnItems().get(0).getRemainingQty().replace("-","");
//                    }
//
//                    int itemQty = Integer.parseInt(lstOrderTem_forstartReturn.getQty());
//
//                    int actualQty = itemQty - Integer.parseInt(returnedQty);

                    int actualQty = getactualQuntity(lstOrderTem_forstartReturn);

                    if (actualQty > qtyCount[0]) {
                        qtyCount[0] = qtyCount[0] + 1;
                        tv_item_quantity_upper.setText("" + qtyCount[0]);
                    }

                } else {
                    if (lstOrderTem_forstartReturn.getQty() != null && !lstOrderTem_forstartReturn.getQty().isEmpty()) {
                        int actualQty = Integer.parseInt(lstOrderTem_forstartReturn.getQty());

                        if (actualQty > qtyCount[0]) {
                            qtyCount[0] = qtyCount[0] + 1;
                            tv_item_quantity_upper.setText("" + qtyCount[0]);
                        }
                    }
                }

            }
        });


        img_minus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (lstOrderTem_forstartReturn.getLstReturnItems() != null
                        && lstOrderTem_forstartReturn.getLstReturnItems().size() > 0) {

                    int actualQty = getactualQuntity(lstOrderTem_forstartReturn);

                    if (qtyCount[0] > 1 && qtyCount[0] <= actualQty) {
                        qtyCount[0] = qtyCount[0] - 1;
                        tv_item_quantity_upper.setText("" + qtyCount[0]);
                    }

                } else {

                    if (lstOrderTem_forstartReturn.getQty() != null && !lstOrderTem_forstartReturn.getQty().isEmpty()) {
                        int actualQty = Integer.parseInt(lstOrderTem_forstartReturn.getQty());

                        if (qtyCount[0] > 1 && qtyCount[0] <= actualQty) {
                            qtyCount[0] = qtyCount[0] - 1;
                            tv_item_quantity_upper.setText("" + qtyCount[0]);
                        }
                    }
                }
            }
        });


        comment = textEdtCustomerNote.getText().toString();

        textEdtCustomerNote.setOnTouchListener(new View.OnTouchListener() {
            public boolean onTouch(View v, MotionEvent event) {
                if (textEdtCustomerNote.hasFocus()) {
                    v.getParent().requestDisallowInterceptTouchEvent(true);
                    switch (event.getAction() & MotionEvent.ACTION_MASK) {
                        case MotionEvent.ACTION_SCROLL:
                            v.getParent().requestDisallowInterceptTouchEvent(false);
                            return true;
                    }
                }
                return false;
            }
        });

        if (lstOrderTem_forstartReturn != null) {
            tvDescription.setText(lstOrderTem_forstartReturn.getItemName());

//            if (!lstOrderTem_forstartReturn.getInvLargeImage().isEmpty()) {
//                {
//                    if (lstOrderTem_forstartReturn.getInvLargeImage().contains("noimage")) {
//
//                        Glide.with(context).load(imgNoImageUrl + lstOrderTem_forstartReturn
//                                .getInvSmallImage()).placeholder(R.drawable.noimage)
//                                .error(R.drawable.no_image_new)
//                                .diskCacheStrategy(DiskCacheStrategy.NONE)
//                                .skipMemoryCache(true).into(ivProdctImage);
//
//                    } else {
//                        Glide.with(context).load(imgUrl + lstOrderTem_forstartReturn.getInvSmallImage())
//                                .placeholder(R.drawable.progress_bar)
//                                .error(R.drawable.no_image_new)
//                                .diskCacheStrategy(DiskCacheStrategy.NONE)
//                                .skipMemoryCache(true).into(ivProdctImage);
//                    }
//                }
//            }

////                    ******************* Edited by Varun on 16 Aug 2022 ************************

             if (lstOrderTem_forstartReturn.getInvLargeImageFullPath()!=null || !lstOrderTem_forstartReturn.getInvLargeImageFullPath().isEmpty()) {
                 Glide.with(context).load(lstOrderTem_forstartReturn.getInvLargeImageFullPath())
                         .placeholder(R.drawable.noimage)
                         .error(R.drawable.no_image_new)
                         .diskCacheStrategy(DiskCacheStrategy.NONE)
                         .skipMemoryCache(true).into(ivProdctImage);
            }

////        ******************* END ************************

        }

        Spinner spinnerReason = (Spinner) StartReturndialog.findViewById(R.id.spinnerReason);

        if (returnProcessModelList != null && returnProcessModelList.size() > 0) {
            finalreturnProcessModelList = returnProcessModelList;
        }

        ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<String>(context, R.layout.row_order_reason, R.id.textview, finalreturnProcessModelList);

//        spinnerArrayAdapter.setDropDownViewResource(R.layout.row_ordermonths);
        spinnerReason.setAdapter(spinnerArrayAdapter);


        spinnerReason.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                selectedReason[0] = spinnerReason.getSelectedItem().toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        String finalComment = comment;

        btnContinue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (selectedReason[0].equalsIgnoreCase("Choose a response")) {
                    DialogUtils.showCommonNormalDialog(context, "returnReason");
                } else {
                    String cust_id = "0";

                    if (Constant.SCREEN_LAYOUT == 1) {
                        cust_id = MainActivity.getUserId();
                    } else if (Constant.SCREEN_LAYOUT == 2) {
                        cust_id = MainActivityDup.getUserId();
                    }

//                    callSaveReturnOrderWS(context,lstOrderTem_forstartReturn,cust_id, selectedReason[0], finalComment,tv_item_quantity_lower.getText().toString());
                    callSaveReturnOrderWS(context, lstOrderTem_forstartReturn, cust_id, selectedReason[0], finalComment, tv_item_quantity_upper.getText().toString());
                }
            }
        });

        iv_close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                qtyCount[0] = 1;
                StartReturndialog.dismiss();
            }
        });

        StartReturndialog.show();
    }

    private static int getactualQuntity(LstOrderTem lstOrderTem_forstartReturn) {

        String returnedQty = "";

        if (lstOrderTem_forstartReturn.getLstReturnItems().get(0).getRemainingQty().contains("-")) {
            returnedQty = lstOrderTem_forstartReturn.getLstReturnItems().get(0).getRemainingQty().replace("-", "");
        }

        int itemQty = Integer.parseInt(lstOrderTem_forstartReturn.getQty());

        int actualQtyval = itemQty - Integer.parseInt(returnedQty);

        return actualQtyval;
    }

    private static void callSaveReturnOrderWS(Context context, LstOrderTem lstOrderTem_forstartReturn, String cust_id, String selectedReason, String finalComment, String selecteQty) {

        final int SOCKET_TIMEOUT_MS = 30000;

        JSONObject jsonObjRequest = saveReturnOrderRequest(lstOrderTem_forstartReturn, cust_id, selectedReason, finalComment, selecteQty);

        com.android.volley.RequestQueue requestQueue = Volley.newRequestQueue(context);

        final String mRequestBody = jsonObjRequest.toString();

        StringRequest stringRequest = new StringRequest(Request.Method.POST, WS_SAVE_RETURN_ORDER, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.i("LOG_RESPONSE", response);
                saveReturnOrderResponse(response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("LOG_RESPONSE", error.toString());
            }
        }) {
            @Override
            public String getBodyContentType() {
                return "application/json; charset=utf-8";
            }

            @Override
            public byte[] getBody() throws AuthFailureError {
                try {
                    return mRequestBody == null ? null : mRequestBody.getBytes("utf-8");
                } catch (UnsupportedEncodingException uee) {
                    VolleyLog.wtf("Unsupported Encoding while trying to get the bytes of %s using %s", mRequestBody, "utf-8");
                    return null;
                }
            }

            @Override
            protected Response<String> parseNetworkResponse(NetworkResponse response) {
                String responseString = "";
                if (response != null) {
                    responseString = String.valueOf(response);
                }
                return Response.success(responseString, HttpHeaderParser.parseCacheHeaders(response));
            }
        };

        requestQueue.add(stringRequest);

    }

    private static void saveReturnOrderResponse(String response) {

        if (StartReturndialog != null) {
            StartReturndialog.dismiss();
        }

        OrderHistoryFragment.getInstance().callOrderItemDetail(true);
    }

    private static JSONObject saveReturnOrderRequest(LstOrderTem lstOrderTem_forstartReturn, String cust_id, String selectedReason, String finalComment, String selecteQty) {

        JSONObject requestBody = new JSONObject();

        try {
            requestBody.put("StoreNo", Constant.STOREID);
            requestBody.put("CustomerID", cust_id);
            requestBody.put("ItemID", lstOrderTem_forstartReturn.getItemID());
            requestBody.put("OrderID", lstOrderTem_forstartReturn.getOrderID());
//            requestBody.put("Qty", lstOrderTem_forstartReturn.getQty());
            requestBody.put("Qty", selecteQty);
            requestBody.put("Notes", selectedReason);
            requestBody.put("Comments", finalComment);

            Log.e("", "==>: save return Transaction request : " + requestBody);

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return requestBody;
    }

//    private static String cardType = "";
//    public static boolean validateCard(String card) {
//
//        card = card.replace(" ", "");
//        if (card.length() == 13) {
//            if (card.substring(0, 1).equals("4")) {
//                cardType = "visa";
//                return checkCreditCard(card, cardType);
//            }else{
//                return false;
//            }
//        } else if (card.length() == 16) {
//            if (card.substring(0, 1).equals("6")) {
//                cardType = "discover";
//                return checkCreditCard(card, cardType);
//            } else if (card.substring(0, 1).equals("5")) {
//                cardType = "master";
//                return checkCreditCard(card, cardType);
//            } else if (card.substring(0, 1).equals("4")) {
//                cardType = "visa";
//                return checkCreditCard(card, cardType);
//            } else {
//                return false;
//            }
//        } else if (card.length() == 15) {
//            if (card.substring(0, 1).equals("3")) {
//                cardType = "amex";
//                return checkCreditCard(card, cardType);
//            } else {
//                return false;
//            }
//        } else {
//            return false;
//        }
//    }

    public static void showCommonNormalDialog(Context context, final String fromWhere) {

        //change to new reorder
        final Dialog dialog = new Dialog(context);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.dialog_confirmation);
        TextView txtErrorName = (TextView) dialog.findViewById(R.id.tv_sub_title_warning_dialog);

        if (fromWhere.equalsIgnoreCase("returnReason")) {
            txtErrorName.setText(context.getResources().getString(R.string.str_select_reason));
        } else {
//            txtErrorName.setText(getString(R.string.str_confirmation_text));
        }

        Button btnOK = (Button) dialog.findViewById(R.id.btn_OK);
        GradientDrawable bgShape = (GradientDrawable) btnOK.getBackground();
        bgShape.setColor(Color.parseColor(themeModel.ThemeColor));
        btnOK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        Button btn_Cancel = (Button) dialog.findViewById(R.id.btn_Cancel);
        btn_Cancel.setVisibility(View.GONE);
        GradientDrawable bgShapec = (GradientDrawable) btn_Cancel.getBackground();
        bgShapec.setColor(Color.parseColor(themeModel.ThemeColor));
        btn_Cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        dialog.show();
    }

    public static void showCommonConfirmation(Context context, String fromwhere, ShippingData shippingData) {

        //change to new reorder
        final Dialog dialog = new Dialog(context);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCanceledOnTouchOutside(false);
        dialog.setContentView(R.layout.dialog_common_confirmation);
        TextView txtErrorName = (TextView) dialog.findViewById(R.id.tv_sub_title_warning_dialog);

        Button btnOK = (Button) dialog.findViewById(R.id.btn_OK);
        GradientDrawable bgShape = (GradientDrawable) btnOK.getBackground();
        bgShape.setColor(Color.parseColor(Constant.themeModel.ThemeColor));

        Button btn_Cancel = (Button) dialog.findViewById(R.id.btn_Cancel);
        GradientDrawable bgShapec = (GradientDrawable) btn_Cancel.getBackground();
        bgShapec.setColor(Color.parseColor(Constant.themeModel.ThemeColor));

        LinearLayout llbtnLayout = (LinearLayout) dialog.findViewById(R.id.llbtnLayout);
        ViewGroup.LayoutParams params = llbtnLayout.getLayoutParams();

        params.height = ViewGroup.LayoutParams.WRAP_CONTENT;
        params.width = ViewGroup.LayoutParams.MATCH_PARENT;
        llbtnLayout.setGravity(Gravity.LEFT);
        llbtnLayout.setLayoutParams(params);
//        LinearLayout.LayoutParams params_for_btn = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
//        llbtnLayout.setLayoutParams(params_for_btn);

        if (fromwhere.equals("FromSetasDefault")) {
            txtErrorName.setGravity(Gravity.LEFT);
            btn_Cancel.setVisibility(View.VISIBLE);
            txtErrorName.setText(context.getResources().getString(R.string.str_setdefault_confirmation));

        } else if (fromwhere.equals("FromDeletionShippingAddress")) {
            btn_Cancel.setVisibility(View.GONE);
            txtErrorName.setGravity(Gravity.CENTER);

            params = llbtnLayout.getLayoutParams();

            params.height = ViewGroup.LayoutParams.WRAP_CONTENT;
            params.width = ViewGroup.LayoutParams.WRAP_CONTENT;
            llbtnLayout.setGravity(Gravity.CENTER);
            llbtnLayout.setLayoutParams(params);

            txtErrorName.setText(context.getResources().getString(R.string.str_delete_defalult_shippingAdd));

        } else if (fromwhere.equals("FromDeletionConfirmation")) {

            txtErrorName.setText(context.getResources().getString(R.string.str_delete_confirmation));

        } else {
            btn_Cancel.setVisibility(View.VISIBLE);
        }

        btnOK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();

                if (fromwhere.equals("FromSetasDefault")) {
                    ShippingAddressFragment.getInstance().callUpdateShippingdata(shippingData, "Default", true, "setasDefault");
                } else if (fromwhere.equals("FromDeletionConfirmation")) {
                    ShippingAddressFragment.getInstance().callUpdateShippingdata(shippingData, "Delete", false, "delete");
                }
            }
        });


        btn_Cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        dialog.show();
    }


    public static void showEditShippingaddress(Context context) {

        Constant.isclickedEditShipping = true;

        final Dialog dialog = new Dialog(context);

        dialog.setCanceledOnTouchOutside(false);
        dialog.setContentView(R.layout.fragment_manage_account);

        dialog.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);

        WindowManager.LayoutParams params = Objects.requireNonNull(dialog.getWindow()).getAttributes();
//        Point p = Utils.getDisplayDimensions(context);
        params.width = WindowManager.LayoutParams.MATCH_PARENT;
        params.height = LinearLayout.LayoutParams.MATCH_PARENT;
//        params.height = (p.y - (Utils.getStatusBarHeight(context) + Objects.requireNonNull(((AppCompatActivity)context).getSupportActionBar()).getHeight()));
        dialog.getWindow().setAttributes(params);

//        initManageAccount(dialog,context);

        dialog.show();
    }

    public static void notEnoughQuantityDialog(Context context, UpdateCartQuantity updateCartQuantity, int Requested_Quantity, String s, String cartQtyOfItem) {
        Dialog notEn_stock_Dialog = null;
        View view_stock_enough = null;

        notEn_stock_Dialog = new Dialog(context, R.style.DialogSlideAnim_login);
        notEn_stock_Dialog.setCanceledOnTouchOutside(true);
        view_stock_enough = LayoutInflater.from(context).inflate(R.layout.notenough_stock_dialog, null);

        TextView txt_result = view_stock_enough.findViewById(R.id.txt_result);
        TextView txtinstock = view_stock_enough.findViewById(R.id.txtinstock);
        TextView txtReqstock = view_stock_enough.findViewById(R.id.txtReqstock);
        TextView txtinstockValue = view_stock_enough.findViewById(R.id.txtinstockValue);
        TextView txtReqstockValue = view_stock_enough.findViewById(R.id.txtReqstockValue);
        TextView txtNote = view_stock_enough.findViewById(R.id.txtNote);
        Button btn_Ok = view_stock_enough.findViewById(R.id.btn_Ok);
        Button btn_Cancel = view_stock_enough.findViewById(R.id.btn_Cancel);
        GradientDrawable bgShape = (GradientDrawable) btn_Ok.getBackground();
        bgShape.setColor(Color.parseColor(Constant.themeModel.ThemeColor));

        WindowManager.LayoutParams params = notEn_stock_Dialog.getWindow().getAttributes();
        int width = (int) (context.getResources().getDisplayMetrics().widthPixels * 0.9);
        //int height = (int) (getResources().getDisplayMetrics().heightPixels * 0.94);
        params.width = width;
        params.height = WindowManager.LayoutParams.WRAP_CONTENT;

        notEn_stock_Dialog.setContentView(view_stock_enough);

        notEn_stock_Dialog.getWindow().setGravity(Gravity.CENTER);
        WindowManager.LayoutParams layoutParam = notEn_stock_Dialog.getWindow().getAttributes();
        notEn_stock_Dialog.getWindow().setAttributes(layoutParam);

        final Dialog finalZipAvailDialog = notEn_stock_Dialog;

        if (s.equalsIgnoreCase("NotenoughStock")) {

            txtinstock.setText("In Stock: ");
            txtReqstock.setText("Requested: ");
            txtinstockValue.setText("" + updateCartQuantity.getQty());
            txtReqstockValue.setVisibility(View.VISIBLE);
            txtReqstockValue.setText("" + Requested_Quantity);
            btn_Ok.setText("OK");
            btn_Cancel.setVisibility(View.GONE);

            if (!updateCartQuantity.getNote().isEmpty()) {
                txtNote.setVisibility(View.VISIBLE);
                txtNote.setText("" + updateCartQuantity.getNote());
            }
            txt_result.setText("" + updateCartQuantity.getResult());


            btn_Ok.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    finalZipAvailDialog.dismiss();
                }
            });

            if (!notEn_stock_Dialog.isShowing()) {
                notEn_stock_Dialog.show();
            }
        } else if (s.isEmpty() && updateCartQuantity != null) { //from card fragment

            txtinstock.setText("In Stock: ");
            txtReqstock.setText("Requested: ");
            txtinstockValue.setText("" + updateCartQuantity.getQty());
            txtReqstockValue.setVisibility(View.VISIBLE);
            txtReqstockValue.setText("" + Requested_Quantity);
            btn_Ok.setText("OK");
            btn_Cancel.setVisibility(View.GONE);

            if (!updateCartQuantity.getNote().isEmpty()) {
                txtNote.setVisibility(View.VISIBLE);
                txtNote.setText("" + updateCartQuantity.getNote());
            }
            txt_result.setText("" + updateCartQuantity.getResult());


            btn_Ok.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    finalZipAvailDialog.dismiss();
                    if (CardFragment.getInstance() != null) {
                        CardFragment.getInstance().onGetCartData();
                    }
                }
            });

            if (!notEn_stock_Dialog.isShowing()) {
                notEn_stock_Dialog.show();
            }

        } else if (s.equalsIgnoreCase("itemDesc")) {

            txtinstock.setText("Qty in cart: ");
            txtReqstock.setVisibility(View.VISIBLE);
            txtReqstock.setText("Add " + Requested_Quantity + "" + " more?");
            txtinstockValue.setText("" + cartQtyOfItem);
            txtReqstockValue.setVisibility(View.GONE);
            txt_result.setText("Item is already in your cart");
            btn_Ok.setText("YES");
            btn_Cancel.setVisibility(View.VISIBLE);
            btn_Cancel.setText("NO");

            int finalRequested_Quantity = Requested_Quantity;
            btn_Ok.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    finalZipAvailDialog.dismiss();
                    ItemDescriptionsFragment.getInstance().updateToCartData(finalRequested_Quantity,updateCartQuantity);
                }
            });

            btn_Cancel.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    finalZipAvailDialog.dismiss();
                    ItemDescriptionsFragment.getInstance().tvQty.setText(cartQtyOfItem);
                }
            });

            if (!notEn_stock_Dialog.isShowing()) {
                notEn_stock_Dialog.show();
            }
        } else if (s.equalsIgnoreCase("Mainactivity_buyItgain")) {

            txtinstock.setText("Qty in cart: ");
            txtReqstock.setVisibility(View.GONE);
            txtReqstockValue.setVisibility(View.VISIBLE);
            txtReqstockValue.setText("Add " + Requested_Quantity + "" +" more?");
            txtinstockValue.setText("" + cartQtyOfItem);
            txt_result.setText("Item is already in your cart");
            btn_Ok.setText("YES");
            btn_Cancel.setVisibility(View.VISIBLE);
            btn_Cancel.setText("NO");

            int finalRequested_Quantity1 = Requested_Quantity;
            btn_Ok.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    finalZipAvailDialog.dismiss();
//                    Edited by Varun for home page add to cart again pop-up

//                    MainActivity.getInstance().updateToCartData( finalRequested_Quantity1 , updateCartQuantity);
                    HomepageFragment.getInstance().updateToCartData( finalRequested_Quantity1 , updateCartQuantity);

//                    END
                }
            });

            btn_Cancel.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    finalZipAvailDialog.dismiss();
//                    ItemDescriptionsFragment.getInstance().tvQty.setText(cartQtyOfItem);
                }
            });

            if (!notEn_stock_Dialog.isShowing()) {
                notEn_stock_Dialog.show();
            }
        } else if (s.equalsIgnoreCase("viewall")) {

            txtinstock.setText("Qty in cart: ");

            if (Requested_Quantity == 0) {
                Requested_Quantity = 1;
            }
            txtReqstock.setVisibility(View.VISIBLE);
            txtReqstock.setText("Add " + Requested_Quantity + "" + " more?");

            txtinstockValue.setText("" + cartQtyOfItem);
            txtReqstockValue.setVisibility(View.GONE);
            txt_result.setText("Item is already in your cart");
            btn_Ok.setText("YES");
            btn_Cancel.setVisibility(View.VISIBLE);
            btn_Cancel.setText("NO");

            int finalRequested_Quantity = Requested_Quantity;
            btn_Ok.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    finalZipAvailDialog.dismiss();
                    ViewAllFragment.getInstance().updateToCartData(finalRequested_Quantity, updateCartQuantity);
                }
            });

            btn_Cancel.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    finalZipAvailDialog.dismiss();
//                        ViewAllFragment.getInstance().tv.setText(cartQtyOfItem);
                }
            });

            if (!notEn_stock_Dialog.isShowing()) {
                notEn_stock_Dialog.show();
            }

        } else if (s.equalsIgnoreCase("Reorder")) {
            String qtyOnStock = cartQtyOfItem;

            txtinstock.setText("In Stock: ");
            txtReqstock.setText("Requested: ");
            txtinstockValue.setText("" + qtyOnStock);
            txtReqstockValue.setVisibility(View.VISIBLE);
            txtReqstockValue.setText("" + Requested_Quantity);
            btn_Ok.setText("OK");
            btn_Cancel.setVisibility(View.GONE);

            btn_Ok.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    finalZipAvailDialog.dismiss();
                }
            });

            if (!notEn_stock_Dialog.isShowing()) {
                notEn_stock_Dialog.show();
            }
        }
    }

    public static void notEnoughQuantityNewDialog(Context context, UpdateCartQuantity updateCartQuantity, int Requested_Quantity, String s, String cartQtyOfItem, String fromwhere) {

        Dialog notEn_stock_Dialog = null;
        View view_stock_enough = null;

        notEn_stock_Dialog = new Dialog(context, R.style.DialogSlideAnim_login);
        notEn_stock_Dialog.setCanceledOnTouchOutside(true);
        view_stock_enough = LayoutInflater.from(context).inflate(R.layout.notenough_stock_new_dialog, null);

        ImageView iv_close = view_stock_enough.findViewById(R.id.iv_close);
        TextView txt_result = view_stock_enough.findViewById(R.id.txt_result);
        TextView txtinstock = view_stock_enough.findViewById(R.id.txtinstock);
        TextView txtReqstock = view_stock_enough.findViewById(R.id.txtReqstock);
        TextView txtinstockValue = view_stock_enough.findViewById(R.id.txtinstockValue);
        TextView txtReqstockValue = view_stock_enough.findViewById(R.id.txtReqstockValue);
        TextView txtNote = view_stock_enough.findViewById(R.id.txtNote);
        Button btn_add = view_stock_enough.findViewById(R.id.btn_Ok);
        Button btn_Cancel = view_stock_enough.findViewById(R.id.btn_Cancel);
        GradientDrawable bgShape = (GradientDrawable) btn_add.getBackground();
        bgShape.setColor(Color.parseColor(Constant.themeModel.ThemeColor));

        WindowManager.LayoutParams params = notEn_stock_Dialog.getWindow().getAttributes();
        int width = (int) (context.getResources().getDisplayMetrics().widthPixels * 0.9);
        //int height = (int) (getResources().getDisplayMetrics().heightPixels * 0.94);
        params.width = width;
        params.height = WindowManager.LayoutParams.WRAP_CONTENT;

        notEn_stock_Dialog.setContentView(view_stock_enough);

        notEn_stock_Dialog.getWindow().setGravity(Gravity.CENTER);
        WindowManager.LayoutParams layoutParam = notEn_stock_Dialog.getWindow().getAttributes();
        notEn_stock_Dialog.getWindow().setAttributes(layoutParam);

        final Dialog finalZipAvailDialog = notEn_stock_Dialog;

        if (s.equalsIgnoreCase("NotenoughStock")) {

            txtinstock.setText("In Stock: ");
            txtReqstock.setText("Requested: ");
//            txtinstockValue.setText("" + updateCartQuantity.getQty());
            txtReqstockValue.setVisibility(View.VISIBLE);

//           Edited by Varun for NOT ENOUGH STOcK POP-UP CHANGES FOR MULTI PACK for design show
            if (Constant.invType.equals("M")){
                txtReqstockValue.setText(""+Requested_Quantity +" @ " + Constant.Test_Ounces + " Items in Each pack");
            }else {
                txtReqstockValue.setText("" + Requested_Quantity);
            }
//            END

            if (!updateCartQuantity.getNote().isEmpty()) {
                txtNote.setVisibility(View.VISIBLE);
                txtNote.setText("" + updateCartQuantity.getNote());
            }
//            txt_result.setText("" + updateCartQuantity.getResult());

            //added for not engough stock chnages on 10thfeb2022
            int instock_qty = 0;
            int left_stock = 0;

            if (updateCartQuantity.getQty() != null && !updateCartQuantity.getQty().trim().isEmpty()) {

                int totalitemQty = Integer.parseInt(updateCartQuantity.getQty());

                if (totalitemQty > 0) {

                    if (cartQtyOfItem == null || cartQtyOfItem.isEmpty()) {
                        cartQtyOfItem = "0";
                    }
//                      instock_qty = totalitemqty  - qty which is added in cart
//                        instock_qty = Integer.parseInt(updateCartQuantity.getQty()) - Integer.parseInt(cartQtyOfItem);
//                    Edited by Varun for Multi Pack
//                    Edited by Varun for NOT ENOUGH STOcK POP-UP CHANGES FOR MULTI PACK for calculation
                    if (Constant.invType.equals("M") && !updateCartQuantity.getCartcount().equals("0")){
                        instock_qty = Integer.parseInt(updateCartQuantity.getQty()) - Integer.parseInt(updateCartQuantity.getCartcount()) * Constant.Test_Ounces;
                    }else {
//                        END
                        instock_qty = Integer.parseInt(updateCartQuantity.getQty()) - Integer.parseInt(updateCartQuantity.getCartcount());
                    }
                    if (Constant.invType.equals("M")&& Test_Ounces!=0){
                        left_stock = instock_qty / Test_Ounces;
                    }
//                    END
                    txtinstockValue.setText("" + String.valueOf(instock_qty));

                    if (Constant.invType.equals("M")){
                        if (Requested_Quantity * Test_Ounces >instock_qty){
                            if (left_stock >= 1){
                                btn_add.setVisibility(View.VISIBLE); //add to cart button display
                                btn_add.setText("ADD " + left_stock + " TO CART");
                            }else{
                                btn_add.setVisibility(View.GONE);
                            }
                        }else{
                            btn_add.setVisibility(View.GONE);
                        }
//                        if (Requested_Quantity * Constant.Test_Ounces > instock_qty && instock_qty>0){
//                            btn_add.setVisibility(View.GONE);
//                        }else  if (instock_qty > 0) {
//                            btn_add.setVisibility(View.VISIBLE); //add to cart button display
//                            btn_add.setText("ADD " + left_stock + " TO CART");
//                        } else {
//                            btn_add.setVisibility(View.GONE);
//                            txtinstockValue.setText("" + instock_qty);
//                        }
                    }else {
                        if (instock_qty > 0) {
                            btn_add.setVisibility(View.VISIBLE); //add to cart button display
                            btn_add.setText("ADD " + instock_qty + " TO CART");
                        } else {
                            btn_add.setVisibility(View.GONE);
                            txtinstockValue.setText("" + instock_qty);
                        }
                    }


                } else {
//                    ?Edited by Varun for Tom Feed back of not eneough Stock pop-up

                    btn_add.setVisibility(View.GONE);
                    txtinstockValue.setText("" + String.valueOf(instock_qty));

//                     END
                }
            } else {
                txtinstockValue.setText("" + "0");
            }
            // end

            int finalInstock_qty=0;
            if (Constant.invType.equals("M")){
                finalInstock_qty = left_stock;
            }else {
                finalInstock_qty = instock_qty;
            }
            int finalInstock_qty1 = finalInstock_qty;
            btn_add.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    //add from out of stock popup

                    if(fromwhere.equalsIgnoreCase("fromItemDesc")){

//                        ItemDescriptionsFragment.getInstance().updateToCartData(finalInstock_qty, updateCartQuantity);
                        ItemDescriptionsFragment.getInstance().addTocartData(String.valueOf(finalInstock_qty1));
                    }
                    else if(fromwhere.equalsIgnoreCase("fromViewall")){

                        ViewAllFragment.getInstance().addTocartData(String.valueOf(finalInstock_qty1));
                    }
                    // end
                    finalZipAvailDialog.dismiss();
                }
            });

            btn_Cancel.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    finalZipAvailDialog.dismiss();
                }
            });

            iv_close.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    finalZipAvailDialog.dismiss();
                }
            });

            if (!notEn_stock_Dialog.isShowing()) {
                notEn_stock_Dialog.show();
            }
        }

        else if (fromwhere.equalsIgnoreCase("FromCardFrag") && s.isEmpty() && updateCartQuantity != null) { //from card fragment

            txtinstock.setText("In Stock: ");
            txtReqstock.setText("Requested: ");

            if (Constant.Card_Fargment_Inv_type.equals("M")){
//                Edited by Varun for NOT ENOUGH STOcK POP-UP CHANGES FOR MULTI PACK for design show

                    txtReqstockValue.setText(""+Requested_Quantity +" @ " + Constant.Test_Ounces + " Items in Each pack");

                int instock_qty = 0;
                if (!updateCartQuantity.getCartcount().equals("0")){
                    instock_qty = Integer.parseInt(updateCartQuantity.getQty()) - Integer.parseInt(updateCartQuantity.getCartcount()) * Constant.Test_Ounces;
                }
//                    END
                txtinstockValue.setText("" + String.valueOf(instock_qty));
                btn_add.setVisibility(View.GONE);

                if (!updateCartQuantity.getNote().isEmpty()) {
                    txtNote.setVisibility(View.VISIBLE);
                    txtNote.setText("" + updateCartQuantity.getNote());
                }

            }else {

                txtinstockValue.setText("" + updateCartQuantity.getQty());
                txtReqstockValue.setVisibility(View.VISIBLE);
                txtReqstockValue.setText("" + Requested_Quantity);
//            btn_add.setText("OK");
                btn_add.setVisibility(View.GONE);
//            btn_Cancel.setVisibility(View.GONE);

                if (!updateCartQuantity.getNote().isEmpty()) {
                    txtNote.setVisibility(View.VISIBLE);
                    txtNote.setText("" + updateCartQuantity.getNote());
                }
//            txt_result.setText("" + updateCartQuantity.getResult());
            }
            btn_Cancel.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    finalZipAvailDialog.dismiss();
                    if (CardFragment.getInstance() != null) {
                        CardFragment.getInstance().onGetCartData();
                    }
                }
            });

            iv_close.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    finalZipAvailDialog.dismiss();
                    if (CardFragment.getInstance() != null) {
                        CardFragment.getInstance().onGetCartData();
                    }
                }
            });

            if (!notEn_stock_Dialog.isShowing()) {
                notEn_stock_Dialog.show();
            }
        }

//        else if (s.equalsIgnoreCase("itemDesc")) { //this is woeking properly
//
//            txtinstock.setText("Qty in cart: ");
//            txtReqstock.setVisibility(View.VISIBLE);
//            txtReqstock.setText("Add " + Requested_Quantity + "" + " more?");
//            txtinstockValue.setText("" + cartQtyOfItem);
//            txtReqstockValue.setVisibility(View.GONE);
//            txt_result.setText("Item is already in your cart");
//            btn_add.setText("YES");
//            btn_Cancel.setVisibility(View.VISIBLE);
//            btn_Cancel.setText("NO");
//
//            btn_add.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View view) {
//                    finalZipAvailDialog.dismiss();
//                    ItemDescriptionsFragment.getInstance().updateToCartData(updateCartQuantity);
//                }
//            });
//
//            btn_Cancel.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View view) {
//                    finalZipAvailDialog.dismiss();
//                    ItemDescriptionsFragment.getInstance().tvQty.setText(cartQtyOfItem);
//                }
//            });
//
//            if (!notEn_stock_Dialog.isShowing()) {
//                notEn_stock_Dialog.show();
//            }
//        } else if (s.equalsIgnoreCase("viewall")) { //this is woeking properly
//
//            txtinstock.setText("Qty in cart: ");
//
//            if (Requested_Quantity == 0) {
//                Requested_Quantity = 1;
//            }
//            txtReqstock.setVisibility(View.VISIBLE);
//            txtReqstock.setText("Add " + Requested_Quantity + "" + " more?");
//
//            txtinstockValue.setText("" + cartQtyOfItem);
//            txtReqstockValue.setVisibility(View.GONE);
//            txt_result.setText("Item is already in your cart");
//            btn_add.setText("YES");
//            btn_Cancel.setVisibility(View.VISIBLE);
//            btn_Cancel.setText("NO");
//
//            int finalRequested_Quantity = Requested_Quantity;
//            btn_add.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View view) {
//                    finalZipAvailDialog.dismiss();
//                    ViewAllFragment.getInstance().updateToCartData(finalRequested_Quantity, updateCartQuantity);
//                }
//            });
//
//            btn_Cancel.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View view) {
//                    finalZipAvailDialog.dismiss();
////                        ViewAllFragment.getInstance().tv.setText(cartQtyOfItem);
//                }
//            });
//
//            if (!notEn_stock_Dialog.isShowing()) {
//                notEn_stock_Dialog.show();
//            }
//
//        }
    }


//    public static void initManageAccount(Dialog dialog, Context context) {
//
//        NestedScrollView nested_ManageAccount;
//        LinearLayout ll_root_manageLayout;
//        TextView tv_Manage_title,tv_last_name,tv_phone_number,tv_first_name,tv_address_one,tv_address_two,tv_email,
//                tv_zip,tv_company_name,tv_City,tv_state;
//        EditText et_lastname;
//        EditText et_firstname;
//        EditText et_company_name;
//        TextView et_phone_number;
//        EditText et_city,et_state,et_zip,et_address_two;
//
//        AutoCompleteTextView et_address_one;
//        Button btnSave;
//
//        Spinner spinner_mobile_option;
//
//        nested_ManageAccount = (NestedScrollView) dialog.findViewById(R.id.nested_ManageAccount);
//        nested_ManageAccount.setBackgroundColor(Color.parseColor(Constant.themeModel.Backgroundcolor));
//        tv_Manage_title = (TextView) dialog.findViewById(R.id.tv_Manage_title);
//        tv_Manage_title.setTextColor(Color.parseColor(Constant.themeModel.ThemeColor));
//        et_company_name = (EditText)dialog.findViewById(R.id.et_company_name);
//        et_lastname = (EditText)dialog.findViewById(R.id.et_lastname);
//        et_firstname = (EditText)dialog.findViewById(R.id.et_firstname);
//        et_address_two = (EditText)dialog.findViewById(R.id.et_address_two);
//        et_address_two.setOnFocusChangeListener(context);
//        tv_email =  (TextView) dialog.findViewById(R.id.tv_email);
//        btnSave = (Button)dialog.findViewById(R.id.btnSave);
//
//        //Spinner
//        spinner_mobile_option = (Spinner)dialog.findViewById(R.id.spinner_mobile_option);
//        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(context,
//                R.array.number_type, android.R.layout.simple_spinner_item);
//        // Specify the layout to use when the list of choices appears
//        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//        // Apply the adapter to the spinner
//        spinner_mobile_option.setAdapter(adapter);
//
//        et_address_one = (AutoCompleteTextView)dialog.findViewById(R.id.et_address_one);
//        et_address_one.setOnFocusChangeListener(context);
//
//        et_phone_number = (TextView)dialog.findViewById(R.id.et_phone_number);
//
//        et_city = (EditText)dialog.findViewById(R.id.et_city);
//        et_state = (EditText)dialog.findViewById(R.id.et_state);
//
//        et_state.addTextChangedListener(new TextWatcher() {
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
//                if(!s.equals(s.toUpperCase()))
//                {
//                    s=s.toUpperCase();
//                    et_state.setText(s);
//                    et_state.setSelection(et_state.length()); //fix reverse texting
//                }
//            }
//        });
//
//        et_zip = (EditText)dialog.findViewById(R.id.et_zip);
//        et_zip.setOnFocusChangeListener(context);
//
//        tv_last_name = (TextView) dialog.findViewById(R.id.tv_last_name);
//        tv_last_name.setText(Html.fromHtml(context.getResources().getString(R.string.lbl_last_name)));
//
//        tv_phone_number = (TextView) dialog.findViewById(R.id.tv_phone_number);
//        tv_phone_number.setText(Html.fromHtml(context.getResources().getString(R.string.lbl_phone_number_simple)));
//
//        tv_first_name = (TextView) dialog.findViewById(R.id.tv_first_name);
//        tv_first_name.setText(Html.fromHtml(context.getResources().getString(R.string.lbl_first_name)));
//
//        tv_address_one = (TextView) dialog.findViewById(R.id.tv_address_one);
//        tv_address_one.setText(Html.fromHtml(context.getResources().getString(R.string.lbl_address1)));
//
//        tv_company_name = (TextView) dialog.findViewById(R.id.tv_company_name);
//        tv_company_name.setText(Html.fromHtml(context.getResources().getString(R.string.lbl_company_name)));
//
//        tv_zip = (TextView) dialog.findViewById(R.id.tv_zip);
//        tv_zip.setText(Html.fromHtml(context.getResources().getString(R.string.lbl_zip)));
//
//        tv_state = (TextView) dialog.findViewById(R.id.tv_state);
//        tv_state.setText(Html.fromHtml(context.getResources().getString(R.string.lbl_state_mendatory)));
//
//        tv_City = (TextView) dialog.findViewById(R.id.tv_City);
//        tv_City.setText(Html.fromHtml(context.getResources().getString(R.string.lbl_city_mendatory)));
//
//
//        btnSave.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
////                DialogUtils.underDevelopmentDialog(getActivity());
//
//                if (!validate()) {
//                    return;
//                }else{
//                    String firstName = et_firstname.getText().toString().isEmpty() ? "0" : et_firstname.getText().toString().trim();
//                    String lastName = et_lastname.getText().toString().isEmpty() ? "0" :et_lastname.getText().toString().trim();
//                    String companyName = et_company_name.getText().toString().isEmpty() ? "0" :et_company_name.getText().toString().trim();
//                    if(companyName.equals("") ||companyName.isEmpty()){
//                        companyName = "0";
//                    }
//                    String addressOne = et_address_one.getText().toString().isEmpty() ? "0" :et_address_one.getText().toString().trim();
//                    String addressTwo = et_address_two.getText().toString().isEmpty() ? "0" :et_address_two.getText().toString().trim();
//                    if(addressTwo.equals("") ||addressTwo.isEmpty()){
//                        addressTwo = "0";
//                    }
//                    String city = et_city.getText().toString().isEmpty() ? "0" :et_city.getText().toString().trim();
//                    String state = et_state.getText().toString().isEmpty() ? "0" :et_state.getText().toString().trim();
//                    String zip = et_zip.getText().toString().isEmpty() ? "0" :et_zip.getText().toString().trim();
//                    String phoneNo = et_phone_number.getText().toString().isEmpty() ? "0" :et_phone_number.getText().toString().trim();
//                    String phoneType = spinner_mobile_option.getSelectedItem().toString();
//
//                    callUpdateBillingAddress(firstName,lastName,companyName,addressOne,addressTwo,city,state,zip,phoneNo,phoneType);
//                }
//            }
//        });
//
//    }


    public static void onShareDialogOptions(Context context) {

        Dialog wDialog = null;
        View views = null;

        if (Constant.SCREEN_LAYOUT == 1) {
            wDialog = new Dialog(MainActivity.getInstance(), R.style.DialogSlideAnim_login);
            wDialog.setCanceledOnTouchOutside(false);
            views = LayoutInflater.from(MainActivity.getInstance()).inflate(R.layout.dialog_share_option_dialog, null);

        } else if (Constant.SCREEN_LAYOUT == 2) {
            //            ******************* Edited by Varun on 17 Aug 2022 *************************

            wDialog = new Dialog(MainActivityDup.getInstance(), R.style.DialogSlideAnim_login);
            wDialog.setCanceledOnTouchOutside(false);
            views = LayoutInflater.from(MainActivityDup.getInstance()).inflate(R.layout.dialog_share_option_dialog, null);

//            *********************** END *************************

//            wDialog = new Dialog(MainActivity.getInstance(), R.style.DialogSlideAnim_login);
//            wDialog.setCanceledOnTouchOutside(false);
//            views = LayoutInflater.from(MainActivity.getInstance()).inflate(R.layout.dialog_share_option_dialog, null);

        }

        if (views != null) {
            TextView tv_email = views.findViewById(R.id.tv_email_option);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                tv_email.setTextColor(context.getColor(R.color.blue_search));
            }
            tv_email.setText("Email");

            TextView tv_more = views.findViewById(R.id.tv_more);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                tv_more.setTextColor(context.getColor(R.color.blue_search));
            }
            tv_more.setText("More");

            Dialog finalWDialog = wDialog;
            tv_email.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    finalWDialog.dismiss();
                    ItemDescriptionsFragment.getInstance().openEmailDialog();
                }
            });

            tv_more.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    finalWDialog.dismiss();
                    ItemDescriptionsFragment.getInstance().shareLinkwithChooser();
                }
            });

        }

        WindowManager.LayoutParams params = null;
        if (wDialog != null) {
            params = wDialog.getWindow().getAttributes();

        params.width = WindowManager.LayoutParams.WRAP_CONTENT;
        params.height = WindowManager.LayoutParams.WRAP_CONTENT;
        wDialog.setContentView(views);

        WindowManager.LayoutParams layoutParam = wDialog.getWindow().getAttributes();
        wDialog.getWindow().setAttributes(layoutParam);
        wDialog.getWindow().setGravity(Gravity.CENTER);
        }

        if (!wDialog.isShowing()) {
            wDialog.show();
        }
        wDialog.setCanceledOnTouchOutside(true);
    }

    public static void guest_tell_me_more(String login) {

        Dialog GuestDialog = null;
        if (Constant.SCREEN_LAYOUT == 1) {
            guestDialog = new Dialog(MainActivity.getInstance(), R.style.DialogSlideAnim_login);
            guestDialog.setCanceledOnTouchOutside(false);
            twent_one_year_cart_view = LayoutInflater.from(MainActivity.getInstance()).inflate(R.layout.dialog_guest_disclaimer, null);

        } else if (Constant.SCREEN_LAYOUT == 2) {
            guestDialog = new Dialog(MainActivityDup.getInstance(), R.style.DialogSlideAnim_login);
            guestDialog.setCanceledOnTouchOutside(false);
            twent_one_year_cart_view = LayoutInflater.from(MainActivityDup.getInstance()).inflate(R.layout.dialog_guest_disclaimer, null);

        }
        //Hide Other view that unused because this same dialog user for login success
        Button close_btn ,btn_signin_guest,btn_continue_guest ;
        TextView tv_guest_msg , tv_guest_limitations , tv_email_address ,tv_guest_login_msg;
        LinearLayout ll_guest_btn;

        String htmlText = "<p>By selecting the guest option, you will not have access to features:<br></p>\n" +
                "&bull; Easy re-ordering<br>\n" +
                "&bull; Stored credit card if you select to.<br>\n" +
                "&bull; Ability to quickly cancel orders.<br>\n" +
                "&bull; Review your in-store &amp; online purchases.<br>\n" +
                "&bull; Participate in our rewards program, if applicable.<br>\n" +
                "&bull; Coupons specifically catered to you.<br>\n" +
                "&bull; Faster order processing.<br>\n" +
                "&bull; and more&#8230;..<br>";

        String htmltext_login = "<p>The email address you entered is associated with an existing &lsquo;Guest&rsquo; account. You can continue to &lsquo;Sign In&rsquo; which will require to create a password or select &lsquo;Continue as a Guest.&rsquo;</p>\n" +
                "<p><strong>Creating Account Advantages:</strong></p>" +
                "&bull; Easy re-ordering<br>\n" +
                "&bull; Stored credit card if you elect to<br>\n" +
                "&bull; Ability to quickly cancel orders.<br>\n" +
                "&bull; Review your in-store &amp; online purchases.<br>\n" +
                "&bull; Participate in our rewards program, if applicable<br>\n" +
                "&bull; Coupons specifically catered to you.<br>\n" +
                "&bull; Faster order processing.<br>\n" +
                "&bull; and more&hellip;..<br>" ;


        tv_guest_msg=twent_one_year_cart_view.findViewById(R.id.tv_guest_msg);
        close_btn = twent_one_year_cart_view.findViewById(R.id.btn_close_guest);
        tv_guest_limitations = twent_one_year_cart_view.findViewById(R.id.tv_guest_limitations);
        tv_email_address = twent_one_year_cart_view.findViewById(R.id.tv_email_address);
        ll_guest_btn = twent_one_year_cart_view.findViewById(R.id.ll_guest_btn);
        btn_continue_guest = twent_one_year_cart_view.findViewById(R.id.btn_continue_guest);
        btn_signin_guest = twent_one_year_cart_view.findViewById(R.id.btn_signin_guest);
        tv_guest_login_msg=twent_one_year_cart_view.findViewById(R.id.tv_guest_login_msg);



        if (login.equalsIgnoreCase("signup")){
            tv_guest_msg.setText(HtmlCompat.fromHtml(htmlText, 0));
        }else if (login.equalsIgnoreCase("login")){
            tv_guest_msg.setVisibility(View.GONE);
            close_btn.setVisibility(View.GONE);
            tv_guest_msg.setVisibility(View.GONE);
            tv_guest_limitations.setVisibility(View.GONE);
            tv_email_address.setVisibility(View.VISIBLE);
            ll_guest_btn.setVisibility(View.VISIBLE);
            tv_guest_login_msg.setVisibility(View.VISIBLE);
            tv_guest_login_msg.setText(HtmlCompat.fromHtml(htmltext_login, 0));
        }

        btn_continue_guest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Login.Continue_As_guest();
                guestDialog.dismiss();
            }
        });

        btn_signin_guest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               Login.Signinfromguest(guestDialog);
            }
        });



        close_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                guestDialog.dismiss();
            }
        });

        GradientDrawable bgShape = (GradientDrawable) close_btn.getBackground();
        GradientDrawable bgShape1 = (GradientDrawable) btn_continue_guest.getBackground();
        GradientDrawable bgShape2 = (GradientDrawable) btn_signin_guest.getBackground();
        bgShape.setColor(Color.parseColor(Constant.themeModel.ThemeColor));
        bgShape1.setColor(Color.parseColor(Constant.themeModel.ThemeColor));
        bgShape2.setColor(Color.parseColor(Constant.themeModel.ThemeColor));


        WindowManager.LayoutParams params = guestDialog.getWindow().getAttributes();
        params.width = WindowManager.LayoutParams.WRAP_CONTENT;
        params.height = WindowManager.LayoutParams.WRAP_CONTENT;

        guestDialog.setContentView(twent_one_year_cart_view);
        guestDialog.getWindow().setGravity(Gravity.CENTER);
        WindowManager.LayoutParams layoutParam = guestDialog.getWindow().getAttributes();
        //layoutParam.y = (MainActivity.getInstance().getStatusBarHeight(MainActivity.getInstance()))/* + (MainActivity.getInstance().getToolBarHeight()) + (MainActivity.getInstance().getToolBarHeight())*/; // bottom margin
        guestDialog.getWindow().setAttributes(layoutParam);
        guestDialog.setCancelable(false);
        guestDialog.show();

    }

    @SuppressLint("ResourceAsColor")
    public static void Delete_pop_up(Context context, String cust_mst_ID) {

        Dialog dialog;
        View view;
        dialog = new Dialog(context, R.style.DialogSlideAnim_login);
        dialog.setCanceledOnTouchOutside(false);
        view = LayoutInflater.from(context).inflate(R.layout.delete_account_pop_up, null);

        // set the custom dialog components - text, image and button
        TextView txt_delete_account_tittle = (TextView) view.findViewById(R.id.txt_delete_account_tittle);
        TextView txt_delete_account_msg = (TextView) view.findViewById(R.id.txt_delete_account_msg);

        txt_delete_account_tittle.setGravity(Gravity.CENTER);

        txt_delete_account_msg.setText("We respect your decision to delete your online account.  \n" + "\n" +
                "Data such as:  Name, address, contact details and credit card number (if applicable) will be deleted from your e-commerce account.\n"+"\n" +
//                "NOTE:  The data at the "+UserModel.FirstName.trim()+","+UserModel.LastName.trim()+" retail location will not be deleted.");
                "NOTE:  The data at the "+ themeModel.StoreName +" retail location will not be deleted.");


        Button btnOK = (Button) view.findViewById(R.id.btnOK);


        Button btnno = (Button) view.findViewById(R.id.btn_NO);


        WindowManager.LayoutParams params = dialog.getWindow().getAttributes();
        params.width = WindowManager.LayoutParams.WRAP_CONTENT;
        params.height = WindowManager.LayoutParams.WRAP_CONTENT;

        btnOK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                dialog.dismiss();57
                Constant.dialog_Delete_My_Online_Account = dialog;
                if (Constant.SCREEN_LAYOUT==1) {
                    MainActivity.getInstance().loadDeletetest(context, cust_mst_ID);
                }else{
                    MainActivityDup.getInstance().loadDeletetest(context, cust_mst_ID);
                }

            }
        });

        btnno.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        dialog.setContentView(view);
        dialog.getWindow().setAttributes(params);
        dialog.setCanceledOnTouchOutside(false);
        dialog.setCancelable(false);
        dialog.show();
    }
    
}
