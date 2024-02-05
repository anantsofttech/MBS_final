package com.aspl.Utils;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Build;
import android.os.Handler;
import android.os.Vibrator;
import android.support.annotation.ColorInt;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.InputFilter;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextWatcher;
import android.text.method.ScrollingMovementMethod;
import android.text.style.StrikethroughSpan;
import android.text.style.UnderlineSpan;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.TranslateAnimation;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.aspl.Adapter.RealTimeInvAdpater;
import com.aspl.Adapter.StoreLocationAdapter;
import com.aspl.fragment.CardOnFileFragment;
import com.aspl.fragment.ChnagePasswordFragment;
import com.aspl.fragment.ContactUsFragment;
import com.aspl.fragment.DeliveryOptionsFragment;
import com.aspl.fragment.HomepageFragment;
import com.aspl.fragment.Login;
import com.aspl.fragment.ManageAccountFragment;
import com.aspl.fragment.OrderHistoryFragment;
import com.aspl.fragment.PaymentFragment;
import com.aspl.fragment.ShippingAddressFragment;
import com.aspl.mbs.MainActivity;
import com.aspl.mbs.MainActivityDup;
import com.aspl.mbs.R;
import com.aspl.mbs.SplaceScreen;
import com.aspl.mbsmodel.CalculateShippingModel;
import com.aspl.mbsmodel.LstOrderTem;
import com.aspl.mbsmodel.MbsDataModel;
import com.aspl.mbsmodel.OrderSummary;
import com.aspl.mbsmodel.PayWareModel;
import com.aspl.mbsmodel.ShoppingCardModel;
import com.aspl.mbsmodel.StoreHour;
import com.aspl.mbsmodel.StoreLocationModel;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Objects;
import java.util.TimeZone;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static android.graphics.Color.BLACK;
import static android.graphics.Paint.ANTI_ALIAS_FLAG;

/**
 * Created by mic on 06/26/2017.
 */
public class Utils{
    private static final int REQUEST_LOCATION = 1;
    private static Dialog newCommonDialog;
//    private static Dialog locationChangeDialog;
    public static View dialog_view;
    public static String FACEBOOK_URL = "https://www.facebook.com"; //https://www.facebook.com/pinaksoftware
    public static String FACEBOOK_PAGE_ID = ""; //pinaksoftware
//    public static List<StoreLocationModel> storeLocationList;
//    public static StoreLocationAdapter storeLocationAdapter;
//    public static RecyclerView rvStoreLocation;
    private static View viewh;
    private static Dialog changeLocationdialog;
    public static StoreLocationModel selectedStoreModel = null;
    public static ProgressBar progressBarStore;
    public static LinearLayout ll_empty_view;
    public static EditText edSearchText;
    public static Dialog cancelDialog;
//    public static TextView tvNoRecord;

    //    static LocationManager locationManager;
//    static String latitude;
//    static String longitude;

    public static void hideKeyboard() {
        if (Constant.SCREEN_LAYOUT == 1) {
            View v = MainActivity.getInstance().getCurrentFocus();
            if (v != null) {
                InputMethodManager imm = (InputMethodManager) MainActivity.getInstance().getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
            }
        } else if (Constant.SCREEN_LAYOUT == 2) {
            View v = MainActivityDup.getInstance().getCurrentFocus();
            if (v != null) {
                InputMethodManager imm = (InputMethodManager) MainActivityDup.getInstance().getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
            }
        }
    }

//  /  public static void showSortPopup(Context context, String type) {
//        Dialog dialog = new Dialog(context);
//        View view = LayoutInflater.from(context).inflate(R.layout.short_dialog, null);
//        //ListView popupList = view.findViewById(R.id.popupList);
//        RadioGroup rgShorting = view.findViewById(R.id.rg_shorting_filter);
//        Button btnApplyShorting = view.findViewById(R.id.btn_apply_short_dialog);
//        btnApplyShorting.setBackgroundColor(Color.parseColor(Constant.themeModel.ThemeColor));
//        final RadioButton rbPriceAccentDing = view.findViewById(R.id.rb_price_lowest_first);
//        final RadioButton rbPriceDescending = view.findViewById(R.id.rb_price_highest_first);
//        final RadioButton rbAtoZ = view.findViewById(R.id.rb_title_a_to_z);
//        final RadioButton rbZtoA = view.findViewById(R.id.rb_title_z_to_a);
//
//        if (MainActivity.shortingCheckBoxPosition == 0) {
//            rbPriceAccentDing.setChecked(true);
//        } else if (MainActivity.shortingCheckBoxPosition == 1) {
//            rbPriceDescending.setChecked(true);
//        } else if (MainActivity.shortingCheckBoxPosition == 2) {
//            rbAtoZ.setChecked(true);
//        } else if (MainActivity.shortingCheckBoxPosition == 3) {
//            rbZtoA.setChecked(true);
//        }
//
//        btnApplyShorting.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if (rbPriceAccentDing.isChecked()) {
//                    MainActivity.shortingCheckBoxPosition = 0;
//                    MainActivity.shortCall = "price";
//                    MainActivity.shortDept = "Asc";
//                } else if (rbPriceDescending.isChecked()) {
//                    MainActivity.shortingCheckBoxPosition = 1;
//                    MainActivity.shortCall = "price";
//                    MainActivity.shortDept = "Desc";
//                } else if (rbAtoZ.isChecked()) {
//                    MainActivity.shortingCheckBoxPosition = 2;
//                    MainActivity.shortCall = "desc1";
//                    MainActivity.shortDept = "Asc";
//                } else if (rbZtoA.isChecked()) {
//                    MainActivity.shortingCheckBoxPosition = 3;
//                    MainActivity.shortCall = "desc1";
//                    MainActivity.shortDept = "Desc";
//                }
//
//                ViewAllFragment.getInstance().callSortItemWS(MainActivity.shortCall)
//
////                   / loadSortedWebPage(deptId,subDepartment,shortCall, shortDept);
////                loadFilteredWebPage(deptId, subDepartment, deptSizes, priceRange, isPriceChanged, onlyImage, type, valueOne, valueTwo, shortCall, shortDept);
//                dialog.dismiss();
//            }
//        });
//        dialog.setContentView(view);
//        dialog.show();
//    }

    public static void hideFilterKeyboard() {
        if (Constant.SCREEN_LAYOUT == 1) {
            InputMethodManager imm = (InputMethodManager) MainActivity.getInstance().getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(MainActivity.etMinPrice.getWindowToken(), 0);

        } else if (Constant.SCREEN_LAYOUT == 2) {
            InputMethodManager imm = (InputMethodManager) MainActivityDup.getInstance().getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(MainActivityDup.etMinPrice.getWindowToken(), 0);
        }
    }

    public static void hideKeyboardForceFully(Activity activity) {

        if (Constant.SCREEN_LAYOUT == 1) {
            View v = MainActivity.getInstance().getCurrentFocus();
            if (v != null) {
                InputMethodManager imm = (InputMethodManager) activity.getSystemService(Activity.INPUT_METHOD_SERVICE);
                imm.toggleSoftInput(InputMethodManager.HIDE_IMPLICIT_ONLY, 0);
            }
        } else if (Constant.SCREEN_LAYOUT == 2) {
            View v = MainActivityDup.getInstance().getCurrentFocus();
            if (v != null) {
                InputMethodManager imm = (InputMethodManager) activity.getSystemService(Activity.INPUT_METHOD_SERVICE);
                imm.toggleSoftInput(InputMethodManager.HIDE_IMPLICIT_ONLY, 0);
            }
        }

    }


    // ignore enter First space on edittext
    public static InputFilter ignoreFirstWhiteSpace() {
        return new InputFilter() {
            public CharSequence filter(CharSequence source, int start, int end,
                                       Spanned dest, int dstart, int dend) {
                for (int i = start; i < end; i++) {
                    if (Character.isWhitespace(source.charAt(i))) {
//                        if (dstart == 0)
                        return "";
                    }
                }
                return null;
            }
        };
    }

    public static void showDiscountgroupDialog(Context context, String desc1, String grpcomment, String s, LstOrderTem lstOrderTem) {

        final Dialog dialog = new Dialog(context, R.style.DialogSlideAnim_login);
        dialog.setCanceledOnTouchOutside(true);
        dialog.setContentView(R.layout.dialog_discountgroup);
        WindowManager.LayoutParams params = dialog.getWindow().getAttributes();
        params.width = WindowManager.LayoutParams.WRAP_CONTENT;
        params.height = WindowManager.LayoutParams.WRAP_CONTENT;

        TextView txtTitle = (TextView) dialog.findViewById(R.id.tvTitle);
        TextView tvDiscountDesc = (TextView) dialog.findViewById(R.id.tvDiscountDesc);
        tvDiscountDesc.setVisibility(View.GONE);
        TextView tv_item_quantity = (TextView) dialog.findViewById(R.id.tv_item_quantity_change);

        ImageView iv_close = (ImageView) dialog.findViewById(R.id.iv_close);
        iv_close.setVisibility(View.GONE);
        ImageView iv_minus = (ImageView) dialog.findViewById(R.id.img_minus_rv_card_fragment);
        ImageView iv_plus = (ImageView) dialog.findViewById(R.id.img_plus_rv_card_fragment);

        Button btnClose = (Button) dialog.findViewById(R.id.btnClose);
        Button btnAdd = (Button) dialog.findViewById(R.id.btnAdd);
        Button btncancel = (Button) dialog.findViewById(R.id.btnCancel);

        LinearLayout llYesNo = (LinearLayout)dialog.findViewById(R.id.llYesNo);
        llYesNo.setVisibility(View.VISIBLE);

        LinearLayout ll_quantity_count = (LinearLayout)dialog.findViewById(R.id.ll_quantity_count);
        ll_quantity_count.setVisibility(View.GONE);
        LinearLayout llAddCancel_buyitagain = (LinearLayout)dialog.findViewById(R.id.llAddCancel_buyitagain);
        llAddCancel_buyitagain.setVisibility(View.GONE);

//        GradientDrawable bgShape = (GradientDrawable) btnClose.getBackground();
//        bgShape.setColor(Color.parseColor(Constant.themeModel.ThemeColor));

        if(s != null && !s.isEmpty() && s.equalsIgnoreCase("buyitagain")){

            llYesNo.setVisibility(View.GONE);
            ll_quantity_count.setVisibility(View.VISIBLE);
            llAddCancel_buyitagain.setVisibility(View.VISIBLE);

            if(lstOrderTem != null){
                txtTitle.setText(lstOrderTem.getItemName().trim());
            }

            btnAdd.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

//                    if (Constant.SCREEN_LAYOUT == 1) {
//                        MainActivity.getInstance().callBuyItAgainWS(orderid, itemid,requestdQty);
//
//                    } else if (Constant.SCREEN_LAYOUT == 2) {
//                        MainActivityDup.getInstance().callBuyItAgainWS(orderid, itemid,requestdQty);
//                    }

                    //working buy_it_again_code
                    String itemid = lstOrderTem.getItemID();
                    String requestdQty = tv_item_quantity.getText().toString();

                    if (Constant.SCREEN_LAYOUT == 1) {
                        MainActivity.getInstance().CallWSForItemDescriptionDetails(itemid,requestdQty);
                        dialog.dismiss();

                    }
//                    else if (Constant.SCREEN_LAYOUT == 2) {
//                        MainActivityDup.getInstance().CallWSForItemDescriptionDetails(itemid,requestdQty);
//                    }
                    //working buy_it_again_code_end
                }
            });

            btncancel.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    dialog.dismiss();
                }
            });

            iv_minus.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    int current_qty = Integer.parseInt(tv_item_quantity.getText().toString());

                    if(current_qty > 1){
                        int requested_qty = current_qty - 1;
                        tv_item_quantity.setText(""+requested_qty);
                    }
                }
            });

            iv_plus.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    int current_qty = Integer.parseInt(tv_item_quantity.getText().toString());

                    if(current_qty < 999){
                        int requested_qty = current_qty + 1;
                        tv_item_quantity.setText(""+requested_qty);
                    }
                }
            });

        }else{

            llYesNo.setVisibility(View.VISIBLE);
            ll_quantity_count.setVisibility(View.GONE);
            llAddCancel_buyitagain.setVisibility(View.GONE);

            //discount group
            txtTitle.setText(desc1);

            tvDiscountDesc.setVisibility(View.VISIBLE);
            tvDiscountDesc.setMovementMethod(new ScrollingMovementMethod());
            tvDiscountDesc.setText(grpcomment);

            iv_close.setVisibility(View.VISIBLE);
            iv_close.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    dialog.dismiss();
                }
            });

            btnClose.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    dialog.dismiss();
                }
            });
        }

        dialog.getWindow().setAttributes(params);
        dialog.setCancelable(false);
        dialog.show();
    }

    public static String getFacebookPageURL(Context context) {

        Log.e("Log", "Facebook URL=" + FACEBOOK_URL);
        Log.e("Log", "Facebook URL= ID" + FACEBOOK_PAGE_ID);
        PackageManager packageManager = context.getPackageManager();
        try {
            int versionCode = packageManager.getPackageInfo("com.facebook.katana", 0).versionCode;
            boolean activated = packageManager.getApplicationInfo("com.facebook.katana", 0).enabled;
            if (activated) {
                if ((versionCode >= 3002850)) { //newer versions of fb app
                    return "fb://facewebmodal/f?href=" + FACEBOOK_URL;
                } else { //older versions of fb app
                    return "fb://page/" + FACEBOOK_PAGE_ID;
                }
            } else {
                return FACEBOOK_URL; //normal web url
            }
        } catch (PackageManager.NameNotFoundException e) {
            return FACEBOOK_URL; //normal web url
        }

    }

    public static void changeLocationDialog(Context context) {

        changeLocationdialog = new Dialog(context,R.style.DialogSlideAnimStoreLocation);
        Objects.requireNonNull(changeLocationdialog.getWindow()).setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_NOTHING);

        viewh = LayoutInflater.from(context).inflate(R.layout.layout_change_location, null);


        //load storelocation ws
//        HomepageFragment.getInstance().loadStoreLocationWSdata(context);

        progressBarStore = (ProgressBar)viewh.findViewById(R.id.progressBarStore);
        ll_empty_view = (LinearLayout)viewh.findViewById(R.id.ll_empty_view);

        InputMethodManager mgr = (InputMethodManager)context.getSystemService(Context.INPUT_METHOD_SERVICE);
        if (mgr != null) {
            mgr.hideSoftInputFromWindow(viewh.getWindowToken(), 0);
        }

        Button btnSaveLocation = (Button)viewh.findViewById(R.id.btnSaveLocation);
        GradientDrawable bgShape = (GradientDrawable) btnSaveLocation.getBackground();
        bgShape.setColor(Color.parseColor(Constant.themeModel.ThemeColor));

        edSearchText = (EditText)viewh.findViewById(R.id.edSearchText);
        edSearchText.setFocusableInTouchMode(true);

//        tvNoRecord = (TextView)viewh.findViewById(R.id.tvNoRecord);
        ImageView iv_close = (ImageView) viewh.findViewById(R.id.iv_close);
        iv_close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(changeLocationdialog != null && changeLocationdialog.isShowing()){
                    changeLocationdialog.dismiss();
                }
            }
        });

        btnSaveLocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(selectedStoreModel!= null){
                    if(Constant.STOREID.equals(selectedStoreModel.getStoreno())){
                        changeLocationdialog.dismiss();

                    }else{

                        int cartVal = 0;
                        if (Constant.SCREEN_LAYOUT == 1) {
                            cartVal = MainActivity.mNotifCount;
                        }else if (Constant.SCREEN_LAYOUT == 2) {
                            cartVal = MainActivityDup.mNotifCount;
                        }
                        if(cartVal > 0){
                            //uncomment below line for display departments items
                            Constant.PREVIOUS_STOREID = Constant.STOREID;
                            //end
                            Utils.location_change_newdesign_twobutton_dialog(context, "Location Change", context.getResources().getString(R.string.Location_change_message));

                        }else{
                            //working code to change store
                            //uncomment below line for display departments items
                             Constant.PREVIOUS_STOREID = Constant.STOREID ;
                             //end
                             Constant.STOREID = selectedStoreModel.getStoreno();
                             changeLocationdialog.dismiss();
                             HomepageFragment.getInstance().fromSavelocation();
//                        //end working code
                        }
//
                    }

                }else{
                    changeLocationdialog.dismiss();
                }
            }
        });

//        tvTitle.setHint(" City, State or Zip");
//        "&#x1f50d" +

//        StoreLocationAdapter storeLocationAdapter = new StoreLocationAdapter(context,Constant.storeLocationList);
//
//        RecyclerView rvStoreLocation = (RecyclerView)viewh.findViewById(R.id.rvStoreLocation);
//        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(context.getApplicationContext(), LinearLayoutManager.VERTICAL, false);
//        rvStoreLocation.setLayoutManager(layoutManager);
//        rvStoreLocation.setHasFixedSize(true);
//        rvStoreLocation.setAdapter(storeLocationAdapter);

        callStorehoursws(context);

        TextView iv_searchlocation = (TextView) viewh.findViewById(R.id.iv_searchlocation);

        Drawable roundDrawable = context.getResources().getDrawable(R.drawable.circleshape);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            roundDrawable.setColorFilter(context.getColor(R.color.blue_search), PorterDuff.Mode.SRC_ATOP);
        }
        iv_searchlocation.setBackground(roundDrawable);


        TextView iv_searchclose = (TextView) viewh.findViewById(R.id.iv_searchclose);

        Drawable roundDrawable1 = context.getResources().getDrawable(R.drawable.circleshape);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            roundDrawable1.setColorFilter(context.getColor(R.color.lighter_grey), PorterDuff.Mode.SRC_ATOP);
        }
        iv_searchclose.setBackground(roundDrawable1);

//        Drawable myDrawable = context.getResources().getDrawable(R.drawable.ic_action_searchclose_small);
//        myDrawable.setColorFilter(new LightingColorFilter(Color.BLACK, Color.WHITE));

        iv_searchclose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                edSearchText.setText("");
                edSearchText.clearFocus();

                //to hide keyboard
                InputMethodManager mgr = (InputMethodManager)context.getSystemService(Context.INPUT_METHOD_SERVICE);
                if (mgr != null) {
                    mgr.hideSoftInputFromWindow(viewh.getWindowToken(), 0);
                }
//                end******

                //call search store ws
                if (Constant.SCREEN_LAYOUT == 1) {
                    String searchtext = edSearchText.getText().toString();
                    if (Constant.storeLocationList != null && Constant.storeLocationList.size() > 0) {
                        MainActivity.getInstance().loadSearchLocationWSdata(context,searchtext);
                    }
                }else if (Constant.SCREEN_LAYOUT == 2) {
                    String searchtext = edSearchText.getText().toString();
                    if (Constant.storeLocationList != null && Constant.storeLocationList.size() > 0) {
                        MainActivityDup.getInstance().loadSearchLocationWSdata(context,searchtext);
                    }
                }
                //end
            }
        });

        iv_searchlocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(iv_searchclose.getVisibility() == View.VISIBLE){
                    iv_searchclose.setVisibility(View.GONE);
                }

                edSearchText.clearFocus();

                //to hide keyboard
                InputMethodManager mgr = (InputMethodManager)context.getSystemService(Context.INPUT_METHOD_SERVICE);
                if (mgr != null) {
                    mgr.hideSoftInputFromWindow(viewh.getWindowToken(), 0);
                }
                //end******

                //call search store ws
                if (Constant.SCREEN_LAYOUT == 1) {
                    String searchtext = edSearchText.getText().toString();
                    if (Constant.storeLocationList != null && Constant.storeLocationList.size() > 0) {
                        MainActivity.getInstance().loadSearchLocationWSdata(context,searchtext);
                    }
                }else if (Constant.SCREEN_LAYOUT == 2) {
                    String searchtext = edSearchText.getText().toString();
                    if (Constant.storeLocationList != null && Constant.storeLocationList.size() > 0) {
                        MainActivityDup.getInstance().loadSearchLocationWSdata(context,searchtext);
                    }
                }
                //end
            }
        });

        edSearchText.addTextChangedListener(/*searchWatcher */new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.length() > 0) {
                    iv_searchclose.setVisibility(View.VISIBLE);
                } else {
                    iv_searchclose.setVisibility(View.GONE);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        edSearchText.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {

                    if(iv_searchclose.getVisibility() == View.VISIBLE){
                        iv_searchclose.setVisibility(View.GONE);
                    }

                    edSearchText.clearFocus();

                    //to hide keyboard
                    InputMethodManager mgr = (InputMethodManager)context.getSystemService(Context.INPUT_METHOD_SERVICE);
                    if (mgr != null) {
                        mgr.hideSoftInputFromWindow(viewh.getWindowToken(), 0);
                    }
                    //end******


                    //call search store ws
                    if (Constant.SCREEN_LAYOUT == 1) {
                        String searchtext = edSearchText.getText().toString();
                        if (Constant.storeLocationList != null && Constant.storeLocationList.size() > 0) {
                            MainActivity.getInstance().loadSearchLocationWSdata(context,searchtext);
                        }
                    }else if (Constant.SCREEN_LAYOUT == 2) {
                        String searchtext = edSearchText.getText().toString();
                        if (Constant.storeLocationList != null && Constant.storeLocationList.size() > 0) {
                            MainActivityDup.getInstance().loadSearchLocationWSdata(context,searchtext);
                        }
                    }

//                    edSearchText.clearFocus();
//                    MainActivity.getInstance().hidekeyboard();

                    //end
                    return true;
                }
                return false;
            }
        });

        changeLocationdialog.setContentView(viewh);
        WindowManager.LayoutParams params = Objects.requireNonNull(changeLocationdialog.getWindow()).getAttributes();
        Point p = Utils.getDisplayDimensions(context);
        params.width = WindowManager.LayoutParams.MATCH_PARENT;
        params.height = (p.y - (Utils.getStatusBarHeight(context) + Objects.requireNonNull(((AppCompatActivity)context).getSupportActionBar()).getHeight()));
        changeLocationdialog.getWindow().setAttributes(params);

        changeLocationdialog.getWindow().setGravity(Gravity.BOTTOM);
        changeLocationdialog.show();

//        Point p = Utils.getDisplayDimensions(getActivity());
//        params.width = (p.x - 20);


        //working code below actionbar
//        params.width = WindowManager.LayoutParams.MATCH_PARENT;
//        params.height = (p.y - (Utils.getStatusBarHeight(getActivity()) + Objects.requireNonNull(((AppCompatActivity) getActivity()).getSupportActionBar()).getHeight()));
        // end *******


//        params.y = (Utils.getStatusBarHeight(getActivity())+getActivity().getActionBar().getHeight()); /** 2*///); // top margin;
//        Log.e("Log", "stHeight=" + p.y);
//        Log.e("Log", "stHeight1=" + Utils.getStatusBarHeight(getActivity()));
//        Log.e("Log", "stHeight2=" + Objects.requireNonNull(((AppCompatActivity) getActivity()).getSupportActionBar()).getHeight());
//        Log.e("Log", "stHeight3=" + params.height);

//        params.y = 20;

    }


    private static void location_change_newdesign_twobutton_dialog(Context context, String title, String message) {

        Dialog qtyonlocationChangeDialog = new Dialog(context, R.style.DialogSlideAnim_login);
        qtyonlocationChangeDialog.setCanceledOnTouchOutside(false);
        View dialog_view = LayoutInflater.from(context).inflate(R.layout.location_change_common_new_design_twobutton_dialog, null);
        TextView tv_title = (TextView)dialog_view.findViewById(R.id.tv_title);
        View view_line = (View)dialog_view.findViewById(R.id.view_line);
        TextView tv_message = (TextView)dialog_view.findViewById(R.id.tv_message);
        TextView tv_message2 = (TextView)dialog_view.findViewById(R.id.tv_message2);
        TextView tv_storeaddress = (TextView)dialog_view.findViewById(R.id.storeaddress);
        Button btn_ok = (Button)dialog_view.findViewById(R.id.btn_ok);
        btn_ok.setText("Yes, Change Location");
        Button btn_cancel = (Button)dialog_view.findViewById(R.id.btn_cancel);
        btn_cancel.setText("Don't Change/Back");

        tv_title.setText(title);
        String cartVal = "";
        if (Constant.SCREEN_LAYOUT == 1) {
            cartVal = String.valueOf(MainActivity.mNotifCount);
        }else if (Constant.SCREEN_LAYOUT == 2) {
            cartVal = String.valueOf(MainActivityDup.mNotifCount);
        }
        tv_message.setText("There are"+ " " + cartVal + " " +message);
        tv_message2.setText(context.getResources().getString(R.string.Location_change_locate));

        tv_storeaddress.setText(HomepageFragment.storeaddress);

        btn_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                qtyonlocationChangeDialog.dismiss();
            }
        });

        btn_ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                qtyonlocationChangeDialog.dismiss();
                Constant.STOREID = selectedStoreModel.getStoreno();
                HomepageFragment.getInstance().fromSavelocation();

                Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        //working code to change store
                        changeLocationdialog.dismiss();
                        //end working code
                    }
                }, 1000);

//                //working code to change store
//                Constant.STOREID = selectedStoreModel.getStoreno();
//                changeLocationdialog.dismiss();
//                HomepageFragment.getInstance().fromSavelocation();
//                //end working code
            }
        });

        WindowManager.LayoutParams params = qtyonlocationChangeDialog.getWindow().getAttributes();
        params.width = WindowManager.LayoutParams.WRAP_CONTENT;
        params.height = WindowManager.LayoutParams.WRAP_CONTENT;

        qtyonlocationChangeDialog.setContentView(dialog_view);
        qtyonlocationChangeDialog.getWindow().setGravity(Gravity.CENTER);

        WindowManager.LayoutParams layoutParam = qtyonlocationChangeDialog.getWindow().getAttributes();

        if (Constant.SCREEN_LAYOUT == 1) {
            layoutParam.y = (MainActivity.getStatusBarHeight(context))/* + (MainActivity.getInstance().getToolBarHeight()) + (MainActivity.getInstance().getToolBarHeight())*/; // bottom margin
        }else if (Constant.SCREEN_LAYOUT == 2) {
            layoutParam.y = (MainActivityDup.getStatusBarHeight(context))/* + (MainActivity.getInstance().getToolBarHeight()) + (MainActivity.getInstance().getToolBarHeight())*/; // bottom margin
        }
        qtyonlocationChangeDialog.getWindow().setAttributes(layoutParam);
        qtyonlocationChangeDialog.show();

    }

    private static void callStorehoursws(Context context) {

        if(Constant.storeLocationList != null && Constant.storeLocationList.size()>0){
//            tvNoRecord.setVisibility(View.GONE);

            if (Constant.SCREEN_LAYOUT == 1) {
                if (Constant.storeLocationList != null && Constant.storeLocationList.size() > 0) {
                    MainActivity.getInstance().callStorehoursWSForAllStores(context, false, Constant.storeLocationList2);
                }
            }else if (Constant.SCREEN_LAYOUT == 2) {
                if (Constant.storeLocationList != null && Constant.storeLocationList.size() > 0) {
                    MainActivityDup.getInstance().callStorehoursWSForAllStores(context,false, Constant.storeLocationList);
                }
            }
        }
//        else{
//            tvNoRecord.setVisibility(View.VISIBLE);
//        }

    }

    public static void setChangeLocationadpater(Context context, List<StoreLocationModel> storeLocationList) {

//        StoreLocationAdapter storeLocationAdapter = new StoreLocationAdapter(context,Constant.storeLocationList);

        TextView tvNoRecord = (TextView)viewh.findViewById(R.id.tvNoRecord);
        RecyclerView rvStoreLocation = (RecyclerView)viewh.findViewById(R.id.rvStoreLocation);

//        edSearchText.clearFocus();
////        hideKeyboardofSpecificView(edSearchText);
//
//        //to hide keyboard
//        InputMethodManager mgr = (InputMethodManager)context.getSystemService(Context.INPUT_METHOD_SERVICE);
//        if (mgr != null) {
//            mgr.hideSoftInputFromWindow(viewh.getWindowToken(), 0);
//        }
//        //end******

        if(storeLocationList!= null && storeLocationList.size()>0) {

            rvStoreLocation.setVisibility(View.VISIBLE);
            tvNoRecord.setVisibility(View.GONE);
            StoreLocationAdapter storeLocationAdapter = new StoreLocationAdapter(context,storeLocationList);
            RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(context.getApplicationContext(), LinearLayoutManager.VERTICAL, false);
            rvStoreLocation.setLayoutManager(layoutManager);
            rvStoreLocation.setHasFixedSize(true);

            progressBarStore.setVisibility(View.GONE);
            ll_empty_view.setVisibility(View.GONE);

            rvStoreLocation.setAdapter(storeLocationAdapter);

        }else{

            rvStoreLocation.setVisibility(View.GONE);
            tvNoRecord.setVisibility(View.VISIBLE);
            progressBarStore.setVisibility(View.GONE);
            ll_empty_view.setVisibility(View.GONE);
        }

    }

    private static void hideKeyboardofSpecificView(EditText edSearchText) {

    }

    @SuppressLint("NewApi")
    @NonNull
    public static Point getDisplayDimensions(Context context) {
        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        Display display = wm.getDefaultDisplay();

        DisplayMetrics metrics = new DisplayMetrics();
        display.getMetrics(metrics);
        int screenWidth = metrics.widthPixels;
        int screenHeight = metrics.heightPixels;

        // find out if status bar has already been subtracted from screenHeight
        display.getRealMetrics(metrics);
//        int physicalHeight = metrics.heightPixels;

        return new Point(screenWidth, screenHeight);
    }

    public static int getStatusBarHeight(Context c) {
        Resources resources = c.getResources();
        int resourceId = resources.getIdentifier("status_bar_height", "dimen", "android");
        return (resourceId > 0) ? resources.getDimensionPixelSize(resourceId) : 0;
    }

    public static String getTodaysCloseTime(List<StoreHour> storeHourList) {

        String closeTimeofStore = "";
        String today = Utils.getCurrentDay();
        boolean isStoreClosedtoday = false;

        int pos = 0;

        for (int i = 0; i < storeHourList.size(); i++) {
            if (today.equals(storeHourList.get(i).getStoreDay())) {
                pos = i;
                if (storeHourList.get(i).getClosed()) {
                    isStoreClosedtoday = true;
                }
                break;
            }
        }

        String closeTime = "";
        if(isStoreClosedtoday){
            int j = 1;
            String tomorrowDay = Utils.getNextDay(j);
            String nextDayOpenTime = "";

            for (int i = 0; i < storeHourList.size(); i++) {
                if (tomorrowDay.equals(storeHourList.get(i).getStoreDay())) {
                    if (storeHourList.get(i).getClosed()) {
                        j++;
                        tomorrowDay = Utils.getNextDay(j);
                    } else {
                        nextDayOpenTime = storeHourList.get(i).getOpenTime();
                    }
                }
            }

            closeTimeofStore = "Reopens " + nextDayOpenTime +" "+tomorrowDay;

        }else{
            closeTime = storeHourList.get(pos).getCloseTime();
            closeTimeofStore = "Closes at " + closeTime;
        }

//        closeTimeofStore = storeHourList.get(pos).getCloseTime();
        return closeTimeofStore;
    }

    public static void setImageFromUrlHome(Context c, String url, ImageView v) {

        Glide.with(c)
                .load(url)
                .fitCenter()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(v);

    }

    public static void CancelOrderDialog(Context context, String orderid, OrderSummary orderSummaryTemp) {
        cancelDialog = new Dialog(context);
        cancelDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        cancelDialog.setContentView(R.layout.cancel_order_dialog);
        WindowManager.LayoutParams params = Objects.requireNonNull(cancelDialog.getWindow()).getAttributes();
        params.width = WindowManager.LayoutParams.MATCH_PARENT;
        params.height = WindowManager.LayoutParams.WRAP_CONTENT;

        TextView tvWeborderVal = (TextView) cancelDialog.findViewById(R.id.tvWeborderVal);
        TextView tvDateorderVal = (TextView) cancelDialog.findViewById(R.id.tvDateorderVal);
        TextView tvCustomerVal = (TextView) cancelDialog.findViewById(R.id.tvCustomerVal);
        TextView tvAmountVal = (TextView) cancelDialog.findViewById(R.id.tvAmountVal);
        TextView tvDeliveryVal = (TextView) cancelDialog.findViewById(R.id.tvDeliveryVal);
        TextView tvPaymentVal = (TextView) cancelDialog.findViewById(R.id.tvPaymentVal);
        TextView tvcreditcard = (TextView) cancelDialog.findViewById(R.id.tvcreditcard);
        tvcreditcard.setVisibility(View.GONE);

        tvWeborderVal.setText(orderSummaryTemp.getOrderID());
        tvDateorderVal.setText(orderSummaryTemp.getOrderDate());
        tvCustomerVal.setText(orderSummaryTemp.getBFName());
        //amount
        tvAmountVal.setText("$" +orderSummaryTemp.getOrderFinalTotal());

        if (orderSummaryTemp.getPaymentType().equals("2")){

            //code for displayed card name in payment mode
            String paymentMediacard  = "";
            if(orderSummaryTemp.getPaymentMedia()!=null && !orderSummaryTemp.getPaymentMedia().isEmpty()){
                paymentMediacard = orderSummaryTemp.getPaymentMedia().trim();
                tvPaymentVal.setText(paymentMediacard);
                tvcreditcard.setVisibility(View.VISIBLE);
            }else{
                tvPaymentVal.setText("Card ");
                tvcreditcard.setVisibility(View.VISIBLE);
            }
            //by commenting below line uncomment thia above code
        } else {

            String paymentMediacard  = "";
            if(orderSummaryTemp.getPaymentMedia()!=null && !orderSummaryTemp.getPaymentMedia().isEmpty()){
                paymentMediacard = orderSummaryTemp.getPaymentMedia().trim();
                tvPaymentVal.setText(paymentMediacard);

                if(paymentMediacard.equalsIgnoreCase("Cash")){
                    tvcreditcard.setVisibility(View.GONE);
                }else{
                    tvcreditcard.setVisibility(View.VISIBLE);
                }

            }
//            tvPaymentVal.setText("Cash");
        }



        if (orderSummaryTemp.getIsPickupStore().equals("True")) {
            tvDeliveryVal.setText("Pick up at store ");

        }else if (orderSummaryTemp.getIsDeliverHome().equals("True")) {
//            tvDeliveryVal.setText("Ship ");
            // Set Delivery Option

//        ******************* Edited by Varun for order status on 6 Aug 2022 *********************

        if (orderSummaryTemp.getOrderStatus().equalsIgnoreCase("open")){
            tvDeliveryVal.setText("Will be shipped");
        }else if (orderSummaryTemp.getOrderStatus().equalsIgnoreCase("completed")){
            tvDeliveryVal.setText("Shpped");
        }

//        ************************  END  ********************
        }else if (orderSummaryTemp.getIsUberRush().equals("True")) {
            tvDeliveryVal.setText("Uber Rush ");

        } else if (orderSummaryTemp.getIsHandDelivery().equals("True")) {
            tvDeliveryVal.setText("Hand Delivery");

        }else {
            tvDeliveryVal.setText("");
        }
        Button btncancel = (Button) cancelDialog.findViewById(R.id.btncancel);
        Button btnDontcancel = (Button) cancelDialog.findViewById(R.id.btnDontcancel);
        ImageView iv_close = (ImageView) cancelDialog.findViewById(R.id.iv_close);

        iv_close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cancelDialog.dismiss();
            }
        });

        btnDontcancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cancelDialog.dismiss();
            }
        });

        btncancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(orderSummaryTemp != null && orderSummaryTemp.getPaymentMedia() != null && !orderSummaryTemp.getPaymentMedia().isEmpty()){

                    if(orderSummaryTemp.getPaymentMedia().equalsIgnoreCase("Cash")){

                        OrderHistoryFragment.getInstance().callCancelOrderWS(orderid);

                    }else{

                        if(Constant.isUSAePAY){

                            OrderHistoryFragment.getInstance().callOrderDetailWS(orderid);
                        }else{
                            OrderHistoryFragment.getInstance().callVoidTransactionOrderWS(orderid, orderSummaryTemp);
                        }
                    }
                }
            }
        });

        GradientDrawable bgShape = (GradientDrawable)btnDontcancel.getBackground();
        bgShape.setColor(Color.parseColor(Constant.themeModel.ThemeColor));

        GradientDrawable bgShape1 = (GradientDrawable)btncancel.getBackground();
        bgShape1.setColor(Color.parseColor(Constant.themeModel.ThemeColor));

        cancelDialog.getWindow().setAttributes(params);
        cancelDialog.getWindow().setGravity(Gravity.CENTER);
        cancelDialog.setCanceledOnTouchOutside(false);
        cancelDialog.setCancelable(false);
        cancelDialog.show();
    }

    public static void showRequestResponseDialog(Context context, PayWareModel payWareModel) {

        Dialog requestResponseDialog = new Dialog(context);
        requestResponseDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        requestResponseDialog.setContentView(R.layout.request_response_dialog);
        WindowManager.LayoutParams params = Objects.requireNonNull(cancelDialog.getWindow()).getAttributes();
        params.width = WindowManager.LayoutParams.MATCH_PARENT;
        params.height = WindowManager.LayoutParams.WRAP_CONTENT;

        TextView tv_request = (TextView) requestResponseDialog.findViewById(R.id.tv_request);
        TextView tv_response = (TextView) requestResponseDialog.findViewById(R.id.tv_response);

        if(Constant.isUSAePAY){
            tv_request.setText(payWareModel.getSuccessMessage().toString());
            tv_response.setText(payWareModel.getSuccessMessage().toString());

        }else{
            tv_request.setText(payWareModel.getRequestParameter());
            tv_response.setText(payWareModel.getResponseParameter());
        }

        Button btnok = (Button)requestResponseDialog.findViewById(R.id.btnok);
        GradientDrawable bgShape1 = (GradientDrawable)btnok.getBackground();
        bgShape1.setColor(Color.parseColor(Constant.themeModel.ThemeColor));

        btnok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                requestResponseDialog.dismiss();
                if(cancelDialog != null && cancelDialog.isShowing()){
                    cancelDialog.dismiss();
                }
            }
        });

        requestResponseDialog.getWindow().setAttributes(params);
        requestResponseDialog.getWindow().setGravity(Gravity.CENTER);
        requestResponseDialog.setCanceledOnTouchOutside(false);
        requestResponseDialog.setCancelable(false);
        requestResponseDialog.show();

    }

    public static void zipcodeShippingDetails(Context context, CalculateShippingModel finalCalculateShippingModel, String shippingType) {

        DecimalFormat df = new DecimalFormat("####0.00");
        df.setMaximumFractionDigits(2);

        Dialog zipecodeDialog = new Dialog(context);
        zipecodeDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        zipecodeDialog.setContentView(R.layout.zipcodeshippingdetails);
        WindowManager.LayoutParams params = Objects.requireNonNull(zipecodeDialog.getWindow()).getAttributes();
        params.width = WindowManager.LayoutParams.MATCH_PARENT;
        params.height = WindowManager.LayoutParams.WRAP_CONTENT;

        LinearLayout ll_dashed_line = zipecodeDialog.findViewById(R.id.ll_dashed_line);

        LinearLayout ll_additional_charges = zipecodeDialog.findViewById(R.id.ll_additional_charges);
        TextView tv_additional = (TextView) zipecodeDialog.findViewById(R.id.tv_additional);
        TextView tv_additional_price = (TextView) zipecodeDialog.findViewById(R.id.tv_price_additional);

        LinearLayout ll_handling_charges = zipecodeDialog.findViewById(R.id.ll_handling_charges);
        TextView tv_total_handling = (TextView) zipecodeDialog.findViewById(R.id.tv_total_handling);
        TextView tv_total_handling_price = (TextView) zipecodeDialog.findViewById(R.id.tv_total_handling_price);

        LinearLayout ll_shipping_charges = zipecodeDialog.findViewById(R.id.ll_shipping_charges);
        TextView tv_shipping_charges = (TextView) zipecodeDialog.findViewById(R.id.tv_shipping_charges);
        TextView tv_Shipping_charges_price = (TextView) zipecodeDialog.findViewById(R.id.tv_Shipping_charges_price);

        LinearLayout ll_base_charges = zipecodeDialog.findViewById(R.id.ll_base_charges);
        TextView tv_base_charges = (TextView) zipecodeDialog.findViewById(R.id.tv_base_charges);
        TextView tv_base_charges_price = (TextView) zipecodeDialog.findViewById(R.id.tv_base_charges_price);

        LinearLayout ll_surchages = zipecodeDialog.findViewById(R.id.ll_surcharges);
        TextView tv_surcharges = (TextView) zipecodeDialog.findViewById(R.id.tv_surcharges);
        TextView tv_surcharges_price = (TextView) zipecodeDialog.findViewById(R.id.tv_surcharges_price);

        LinearLayout ll_billing_weight = zipecodeDialog.findViewById(R.id.ll_billing_weight);
        TextView tv_billing_weight = (TextView) zipecodeDialog.findViewById(R.id.tv_billing_weight);
        TextView billing_weight_price = (TextView) zipecodeDialog.findViewById(R.id.billing_weight_price);

        LinearLayout ll_AdultSign_Charges_Per_PKG = zipecodeDialog.findViewById(R.id.ll_AdultSign_Charges_Per_PKG);
        TextView AdultSign_Charges_Per_PKG = (TextView) zipecodeDialog.findViewById(R.id.AdultSign_Charges_Per_PKG);
        TextView AdultSign_Charges_Per_PKG_price = (TextView) zipecodeDialog.findViewById(R.id.AdultSign_Charges_Per_PKG_price);

        LinearLayout ll_AdultSign_Total_Charges = zipecodeDialog.findViewById(R.id.ll_AdultSign_Total_Charges);
        TextView AdultSign_Total_Charges = (TextView) zipecodeDialog.findViewById(R.id.AdultSign_Total_Charges);
        TextView AdultSign_Total_Charges_price = (TextView) zipecodeDialog.findViewById(R.id.AdultSign_Total_Charges_price);

        LinearLayout ll_Additional_Sign_charges = zipecodeDialog.findViewById(R.id.ll_Additional_Sign_charges);
        TextView Additional_Sign_charges = (TextView) zipecodeDialog.findViewById(R.id.Additional_Sign_charges);
        TextView Additional_Sign_charges_price = (TextView) zipecodeDialog.findViewById(R.id.Additional_Sign_charges_price);

//                ************************** Edited by Varun on 1 Aug 2022*****************************
        LinearLayout ll_rate_shipment_alert = zipecodeDialog.findViewById(R.id.ll_Rate_Shipment_Alert);
        TextView tv_rate_shipment_alert = (TextView) zipecodeDialog.findViewById(R.id.Rate_Shipment_Alert);

        LinearLayout ll_transportation_charges = zipecodeDialog.findViewById(R.id.ll_Transportation_charges);
        TextView tv_trasnportation_charges = (TextView) zipecodeDialog.findViewById(R.id.Transportation_charges);
        TextView tv_trasnportation_charges_price = (TextView) zipecodeDialog.findViewById(R.id.Transportation_charges_price);
//          *************************** END **************************

        LinearLayout ll_shipping_charges_2 = zipecodeDialog.findViewById(R.id.ll_shipping_charges_2);
        TextView tv_shipping_charges_2 = (TextView) zipecodeDialog.findViewById(R.id.tv_shipping_charges_2);
        TextView tv_Shipping_charges_price_2 = (TextView) zipecodeDialog.findViewById(R.id.tv_Shipping_charges_price_2);

        LinearLayout ll_total_charges = zipecodeDialog.findViewById(R.id.ll_total_charges);
        TextView tv_total_charges = (TextView) zipecodeDialog.findViewById(R.id.tv_total_charges);
        TextView tv_total_charges_price = (TextView) zipecodeDialog.findViewById(R.id.tv_total_charges_price);

        if(finalCalculateShippingModel != null) {

            if(shippingType.equalsIgnoreCase("FedEx")){

                double base_charges,totalSignatureFees,surcharges , test , shipping_charges_2;
                 base_charges = finalCalculateShippingModel.getFedExBaseCharges();
                if (finalCalculateShippingModel.getTotalSignatureFees()!=null && !finalCalculateShippingModel.getTotalSignatureFees().equals("0") && !finalCalculateShippingModel.getTotalSignatureFees().equals("")){

                    ll_AdultSign_Total_Charges.setVisibility(View.VISIBLE);
                    ll_AdultSign_Charges_Per_PKG.setVisibility(View.VISIBLE);
                    ll_Additional_Sign_charges.setVisibility(View.VISIBLE);
                    ll_shipping_charges_2.setVisibility(View.VISIBLE);
                    ll_total_charges.setVisibility(View.VISIBLE);
                    ll_billing_weight.setVisibility(View.VISIBLE);

                    totalSignatureFees = finalCalculateShippingModel.getTotalSignatureFees();
                    test = Double.parseDouble(df.format(base_charges + totalSignatureFees));

                    tv_shipping_charges.setText("Shipping Charges (Base + Signing Service):");
                    tv_Shipping_charges_price.setText("$" + String.format(df.format(test)));

                    tv_base_charges.setText(">Base:");
                    tv_base_charges_price.setText("$" + String.format(df.format(finalCalculateShippingModel.getFedExBaseCharges())));

                    AdultSign_Charges_Per_PKG.setText(">FedEx Adult Sign Per PKG:");
                    AdultSign_Charges_Per_PKG_price.setText("$" + String.format(df.format(finalCalculateShippingModel.getFedExAdultlSignatureFees())));

                    AdultSign_Total_Charges.setText("FedEx Adult Sign Total:");
                    AdultSign_Total_Charges_price.setText("$" + String.format(df.format(finalCalculateShippingModel.getTotalSignatureFees())));

                    Additional_Sign_charges.setText("Customer Billed for signing service:");
                    Additional_Sign_charges_price.setText("$" + String.format(df.format(finalCalculateShippingModel.getAdditionalSignatureFees())));

                    shipping_charges_2 = test + Double.parseDouble(df.format(finalCalculateShippingModel.getAdditionalCharges()));
                    tv_shipping_charges_2.setText("Shipping Charges:");
                    tv_Shipping_charges_price_2.setText("$" + shipping_charges_2);

                    tv_total_charges.setText("Total Shipping Fees to Customer:");
                    tv_total_charges_price.setText("$" + String.format(df.format(finalCalculateShippingModel.getShippingCharges())));

                }else{
                    ll_dashed_line.setVisibility(View.GONE);
                    ll_Additional_Sign_charges.setVisibility(View.GONE);
                    ll_AdultSign_Total_Charges.setVisibility(View.GONE);
                    ll_AdultSign_Charges_Per_PKG.setVisibility(View.GONE);
                    ll_surchages.setVisibility(View.VISIBLE);
                    ll_billing_weight.setVisibility(View.VISIBLE);

                    surcharges = finalCalculateShippingModel.getFedExSurcharges();
                    test = base_charges + surcharges;
                    tv_surcharges.setText("SurCharges:");
                    tv_surcharges_price.setText("$" + String.format(df.format(finalCalculateShippingModel.getFedExSurcharges())));

                    tv_shipping_charges.setText("Shipping Charges (Base + Surch):");
                    tv_Shipping_charges_price.setText("$" + String.format(df.format(test)));

                    tv_base_charges.setText("Base Charges:");
                    tv_base_charges_price.setText("$" + String.format(df.format(finalCalculateShippingModel.getFedExBaseCharges())));
                }

                tv_billing_weight.setText("Billing Weight:");
                billing_weight_price.setText(finalCalculateShippingModel.getBillingWeight());

                tv_additional.setText("Additional Charges:");
                tv_additional_price.setText("$"+String.format(df.format(finalCalculateShippingModel.getAdditionalCharges())));

                tv_total_handling.setText("Handling Charges:" );
                tv_total_handling_price.setText("$"+ String.format(df.format(finalCalculateShippingModel.getHandlingCharges())));

//                tv_surcharges.setText("SurCharges : $" + String.format(df.format(finalCalculateShippingModel.getFedExSurcharges())));
//                tv_billing_weight.setText("Billing Weight : " + finalCalculateShippingModel.getBillingWeight());

            }
//            else if(shippingType.equalsIgnoreCase("UPS")){
//
//                tv_additional.setText("Additional Charges : $" + String.format(df.format(finalCalculateShippingModel.getAdditionalCharges())));
//                tv_total_handling.setText("Total Handling Charges : $" + String.format(df.format(finalCalculateShippingModel.getHandlingCharges())));
//                tv_shipping_charges.setText("Shipping Charges : $" + String.format(df.format(finalCalculateShippingModel.getOriginalCharges())));
//                tv_base_charges.setText("Base Charges : $" + String.format(df.format(finalCalculateShippingModel.getFedExBaseCharges())));
//                tv_surcharges.setText("SurCharges : $" + String.format(df.format(finalCalculateShippingModel.getFedExSurcharges())));
//                tv_billing_weight.setText("Billing Weight : " + finalCalculateShippingModel.getBillingWeight());
//
//            }

            else if (shippingType.equalsIgnoreCase("UPS")){

                ll_surchages.setVisibility(View.GONE);
                ll_base_charges.setVisibility(View.GONE);
                ll_billing_weight.setVisibility(View.VISIBLE);
                ll_transportation_charges.setVisibility(View.VISIBLE);
                ll_rate_shipment_alert.setVisibility(View.VISIBLE);

                if ( finalCalculateShippingModel.getUPSSignatureFees().equals("0.00") && finalCalculateShippingModel.getUPSAdultSignatureFees().equals("0.00")){

                    ll_dashed_line.setVisibility(View.GONE);
                    ll_Additional_Sign_charges.setVisibility(View.GONE);
                    ll_AdultSign_Total_Charges.setVisibility(View.GONE);
                    ll_AdultSign_Charges_Per_PKG.setVisibility(View.GONE);
                    ll_total_charges.setVisibility(View.VISIBLE);

                    tv_additional.setText("Additional Charges:" );
                    tv_additional_price.setText("$" + String.format(df.format(finalCalculateShippingModel.getAdditionalCharges())));

                    tv_total_handling.setText("Total Handling Charges:" );
                    tv_total_handling_price.setText("$" + String.format(df.format(finalCalculateShippingModel.getHandlingCharges())));

                    tv_shipping_charges.setText("Shipping Charges:");
                    tv_Shipping_charges_price.setText("$" + String.format(df.format(finalCalculateShippingModel.getOriginalCharges())));

                    tv_trasnportation_charges.setText("Transportation Charges:");
                    tv_trasnportation_charges_price.setText("$"+finalCalculateShippingModel.getUPSTransportationCharges());

                    tv_total_charges.setText("Total Shipping Fee to Customer:");
                    tv_total_charges_price.setText("$" + String.format(df.format(finalCalculateShippingModel.getShippingCharges())));

                    tv_billing_weight.setText("Billing Weight:");
                    billing_weight_price.setText(finalCalculateShippingModel.getBillingWeight());

                    tv_rate_shipment_alert.setText("Rate Shipment Alert : "+finalCalculateShippingModel.getUPSRatedShipmentAlert());

                }else{

                    ll_Additional_Sign_charges.setVisibility(View.VISIBLE);
                    ll_AdultSign_Total_Charges.setVisibility(View.VISIBLE);
                    ll_AdultSign_Charges_Per_PKG.setVisibility(View.VISIBLE);
                    ll_shipping_charges_2.setVisibility(View.VISIBLE);
                    ll_total_charges.setVisibility(View.VISIBLE);

                    double shipping_charges_2,transportationCharges,totalSignatureFees,test;

                    transportationCharges = Double.parseDouble(finalCalculateShippingModel.getUPSTransportationCharges());
                    totalSignatureFees = finalCalculateShippingModel.getTotalSignatureFees();
                    test = Double.parseDouble(df.format(transportationCharges + totalSignatureFees));

                    String UPSSignatureFees,UPSAdultSignatureFees;
                    UPSSignatureFees = String.valueOf(finalCalculateShippingModel.getUPSSignatureFees());
                    UPSAdultSignatureFees = String.valueOf(finalCalculateShippingModel.getUPSAdultSignatureFees());

                    tv_additional.setText("Additional Charges:");
                    tv_additional_price.setText("$" + String.format(df.format(finalCalculateShippingModel.getAdditionalCharges())));

                    tv_total_handling.setText("Total Handling Charges:");
                    tv_total_handling_price.setText("$" + String.format(df.format(finalCalculateShippingModel.getHandlingCharges())));

//                tv_shipping_charges.setText("Shipping Charges (Base + Sign): $" + String.format(df.format(finalCalculateShippingModel.getOriginalCharges())));
                    tv_shipping_charges.setText("Shipping Charges (Trans + Sign):");
                    tv_Shipping_charges_price.setText("$" + String.format(df.format(test)));

                    tv_trasnportation_charges.setText(">Transportation Charges:");
                    tv_trasnportation_charges_price.setText("$"+finalCalculateShippingModel.getUPSTransportationCharges());

                    if (UPSSignatureFees.equalsIgnoreCase("0.00")){
                        AdultSign_Charges_Per_PKG.setText(">UPS AdultSign Charges Per PKG:");
                        AdultSign_Charges_Per_PKG_price.setText("$" + UPSAdultSignatureFees);
                    }else{
                        AdultSign_Charges_Per_PKG.setText(">UPS AdultSign Charges Per PKG:");
                        AdultSign_Charges_Per_PKG_price.setText("$" + UPSSignatureFees);
                    }

                    AdultSign_Total_Charges.setText("UPS Sign Total Charges:");
                    AdultSign_Total_Charges_price.setText("$" + String.format(df.format(finalCalculateShippingModel.getTotalSignatureFees())));

                    shipping_charges_2 = test + Double.parseDouble(df.format(finalCalculateShippingModel.getAdditionalCharges()));
                    tv_shipping_charges_2.setText("Shipping Charges:");
                    tv_Shipping_charges_price_2.setText("$" + shipping_charges_2);

                    Additional_Sign_charges.setText("Customer Billed for Singing Charges:");
                    Additional_Sign_charges_price.setText("$" + String.format(df.format(finalCalculateShippingModel.getAdditionalSignatureFees())));

                    tv_total_charges.setText("Total Shipping Fee to Customer:");
                    tv_total_charges_price.setText("$" + String.format(df.format(finalCalculateShippingModel.getShippingCharges())));

                    tv_billing_weight.setText("Billing Weight:");
                    billing_weight_price.setText(finalCalculateShippingModel.getBillingWeight());

                    tv_rate_shipment_alert.setText("Rate Shipment Alert : "+finalCalculateShippingModel.getUPSRatedShipmentAlert());

                }
            }


            else if(shippingType.equalsIgnoreCase("USPS")){

                ll_total_charges.setVisibility(View.VISIBLE);

                tv_additional.setText("Additional Charges:");
                tv_additional_price.setText("$" + String.format(df.format(finalCalculateShippingModel.getAdditionalCharges())));

                tv_total_handling.setText("Total Handling Charges:");
                tv_total_handling_price.setText("$" + String.format(df.format(finalCalculateShippingModel.getHandlingCharges())));

                tv_shipping_charges.setText("Shipping Charges:");
                tv_Shipping_charges_price.setText("$" + String.format(df.format(finalCalculateShippingModel.getOriginalCharges())));

                tv_total_charges.setText("Total Shipping Fees to Customer:");
                tv_total_charges_price.setText("$" + String.format(df.format(finalCalculateShippingModel.getShippingCharges())));

                ll_dashed_line.setVisibility(View.GONE);
                ll_base_charges.setVisibility(View.GONE);
                ll_surchages.setVisibility(View.GONE);
                ll_billing_weight.setVisibility(View.GONE);

//                tv_base_charges.setText("Base Charges : $" + String.format(df.format(finalCalculateShippingModel.getFedExBaseCharges())));
//                tv_surcharges.setText("SurCharges : $" + String.format(df.format(finalCalculateShippingModel.getFedExSurcharges())));
//                tv_billing_weight.setText("Billing Weight : " + finalCalculateShippingModel.getBillingWeight());

            }

        }

        Button btnclose = (Button)zipecodeDialog.findViewById(R.id.btnclose);
        GradientDrawable bgShape1 = (GradientDrawable)btnclose.getBackground();
        bgShape1.setColor(Color.parseColor(Constant.themeModel.ThemeColor));

        btnclose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                zipecodeDialog.dismiss();
            }
        });

        zipecodeDialog.getWindow().setAttributes(params);
        zipecodeDialog.getWindow().setGravity(Gravity.CENTER);
        zipecodeDialog.setCanceledOnTouchOutside(false);
        zipecodeDialog.setCancelable(false);
        zipecodeDialog.show();
    }

    public static void LockChangeLocation(Context context, List<StoreLocationModel> storeLocationList2) {

        Dialog LockDialog = new Dialog(context);
        LockDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        LockDialog.setContentView(R.layout.lockstoredialog);
        WindowManager.LayoutParams params = Objects.requireNonNull(LockDialog.getWindow()).getAttributes();
        params.width = WindowManager.LayoutParams.MATCH_PARENT;
        params.height = WindowManager.LayoutParams.WRAP_CONTENT;

        TextView tvNoRecord = (TextView)LockDialog.findViewById(R.id.tvNoRecord);
        TextView tvStoreName = (TextView)LockDialog.findViewById(R.id.tvStoreName);
        TextView tvStoreadd = (TextView)LockDialog.findViewById(R.id.tvStoreadd);
        TextView tvStore_cityZip = (TextView)LockDialog.findViewById(R.id.tvStore_cityZip);
        TextView tvtittle = (TextView)LockDialog.findViewById(R.id.tv_tittle);
        RecyclerView rvStoreLocation = (RecyclerView)LockDialog.findViewById(R.id.rvStoreLocation);
        ProgressBar progressBar = (ProgressBar) LockDialog.findViewById(R.id.progressBarStore);
        LinearLayout ll_empty_view = (LinearLayout)LockDialog.findViewById(R.id.ll_empty_view);
        LinearLayout ll_single_store = (LinearLayout)LockDialog.findViewById(R.id.ll_single_store);
        LinearLayout ll_listing = (LinearLayout)LockDialog.findViewById(R.id.ll_listing);

        Button btnSaveLocation = (Button)LockDialog.findViewById(R.id.btnSaveLocation);
        GradientDrawable bgShape = (GradientDrawable) btnSaveLocation.getBackground();
        bgShape.setColor(Color.parseColor(Constant.themeModel.ThemeColor));

        tvStoreName.setText(Constant.LockStoreName);
        tvStoreadd.setText(Constant.LockStoreAddress);
        tvStore_cityZip.setText(Constant.LockCity+ "," +Constant.LockState + "," + Constant.LockZip);

//        For Single store when store is not attached to the coroporate office

        TextView tv_single_store_tittle = (TextView)LockDialog.findViewById(R.id.tv_single_store_tittle);
        TextView tv_store_Phone_number = (TextView)LockDialog.findViewById(R.id.tv_store_Phone_number);

        Button btnCloseApp = (Button)LockDialog.findViewById(R.id.btnCloseApp);
        bgShape.setColor(Color.parseColor(Constant.themeModel.ThemeColor));

        tv_single_store_tittle.setText(Constant.LockStoreName + " " + "is experiencing a technical issue on their end.  No further information is available.");
        tv_store_Phone_number.setText(Constant.PhoneNumber);

        if(storeLocationList2!= null && storeLocationList2.size()>0) {

            rvStoreLocation.setVisibility(View.VISIBLE);
            tvNoRecord.setVisibility(View.GONE);
            StoreLocationAdapter storeLocationAdapter = new StoreLocationAdapter(context,storeLocationList2);
            RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(context.getApplicationContext(), LinearLayoutManager.VERTICAL, false);
            rvStoreLocation.setLayoutManager(layoutManager);
            rvStoreLocation.setHasFixedSize(true);

            progressBar.setVisibility(View.GONE);
            ll_empty_view.setVisibility(View.GONE);

            rvStoreLocation.setAdapter(storeLocationAdapter);

        }else{
            ll_single_store.setVisibility(View.VISIBLE);
            ll_listing.setVisibility(View.GONE);

            rvStoreLocation.setVisibility(View.GONE);
//            tvNoRecord.setVisibility(View.VISIBLE);
            progressBar.setVisibility(View.GONE);
            ll_empty_view.setVisibility(View.GONE);
        }

        btnSaveLocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(selectedStoreModel!= null){
                    if(Constant.STOREID.equals(selectedStoreModel.getStoreno())){
                        LockDialog.dismiss();

                    }else{

                        int cartVal = 0;
                        if (Constant.SCREEN_LAYOUT == 1) {
                            cartVal = MainActivity.mNotifCount;
                        }else if (Constant.SCREEN_LAYOUT == 2) {
                            cartVal = MainActivityDup.mNotifCount;
                        }
                        if(cartVal > 0){
                            //uncomment below line for display departments items
                            Constant.PREVIOUS_STOREID = Constant.STOREID;
                            //end
                            Utils.location_change_newdesign_twobutton_dialog(context, "Location Change", context.getResources().getString(R.string.Location_change_message));

                        }else{
                            //working code to change store
                            //uncomment below line for display departments items
                            Constant.PREVIOUS_STOREID = Constant.STOREID ;
                            //end
                            Constant.STOREID = selectedStoreModel.getStoreno();
                            LockDialog.dismiss();
                            SplaceScreen.getInstance().fromSavelocation();
//                        //end working code
                        }
//
                    }

                }else{
//                    LockDialog.dismiss();
//                    Toast.makeText(context, "Please Select Option", Toast.LENGTH_SHORT).show();
                    Validation_Simple_Dialog(context, "Please select another store" , "lockdown feature");
                }
            }
        });

        btnCloseApp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LockDialog.dismiss();
                SplaceScreen.getInstance().finish();
            }
        });

        LockDialog.getWindow().setAttributes(params);
        LockDialog.getWindow().setGravity(Gravity.CENTER);
        LockDialog.setCanceledOnTouchOutside(false);
        LockDialog.setCancelable(false);
        LockDialog.show();
    }


//    public static void getcurrentLatLag(Context context) {
//
//        LocationManager nManager = (LocationManager)context.getSystemService(Context.LOCATION_SERVICE);
//        if (nManager != null) {
//            if (!nManager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
//                OnGPS(context);
//            } else {
//                getLocationData(context);
//            }
//        }
//    }
//
//    private static void getLocationData(Context context) {
//
//        if (ActivityCompat.checkSelfPermission(
//                context, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
//                context, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
//            ActivityCompat.requestPermissions((Activity) context, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, REQUEST_LOCATION);
//        } else {
//            Location locationGPS = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
//            if (locationGPS != null) {
//                double lat = locationGPS.getLatitude();
//                double longi = locationGPS.getLongitude();
//                latitude = String.valueOf(lat);
//                longitude = String.valueOf(longi);
//                Toast.makeText(context, "Your Location: " + "\n" + "Latitude: " + latitude + "\n" + "Longitude: " + longitude, Toast.LENGTH_SHORT).show();
//            } else {
//                Toast.makeText(context, "Unable to find location.", Toast.LENGTH_SHORT).show();
//            }
//        }
//    }

//    private static void OnGPS(Context context) {
//        final AlertDialog.Builder builder = new AlertDialog.Builder(context);
//        builder.setMessage("Enable GPS").setCancelable(false).setPositiveButton("Yes", new  DialogInterface.OnClickListener() {
//            @Override
//            public void onClick(DialogInterface dialog, int which) {
//                context.startActivity(new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS));
//            }
//        }).setNegativeButton("No", new DialogInterface.OnClickListener() {
//            @Override
//            public void onClick(DialogInterface dialog, int which) {
//                dialog.cancel();
//            }
//        });
//        final AlertDialog alertDialog = builder.create();
//        alertDialog.show();
//    }


    //put validation for only 2 digits before and 2 digits after decimal point is valid
    public static class DecimalDigitsInputFilter implements InputFilter {

        Pattern mPattern;

        public DecimalDigitsInputFilter(int digitsBeforeZero, int digitsAfterZero) {
            mPattern = Pattern.compile("[0-9]{0," + (digitsBeforeZero - 1) + "}+((\\.[0-9]{0," + (digitsAfterZero - 1) + "})?)||(\\.)?");
        }

        @Override
        public CharSequence filter(CharSequence source, int start, int end, Spanned dest, int dstart, int dend) {

            Matcher matcher = mPattern.matcher(dest);
            if (!matcher.matches())
                return "";
            return null;
        }

    }

    public static boolean storeTime(String startTime, String endTime) {

        String pattern = "hh:mm a";
        @SuppressLint("SimpleDateFormat")
        //Edited by Janvi 25th Oct *******

//        String currentDate = sdf.format(Calendar.getInstance().getTime().getTime());

                //est conversion
//        SimpleDateFormat sdfEst = new SimpleDateFormat(pattern, Locale.US);
//        TimeZone tz1 = TimeZone.getTimeZone("America/New_York");
//        sdfEst.setTimeZone(tz1);
//        String currentDateEst = sdfEst.format(Calendar.getInstance().getTime().getTime());
//        System.out.println("CurrentEstTime::"+currentDateEst);

                SimpleDateFormat sdf = new SimpleDateFormat(pattern, Locale.US);
        Calendar c = Calendar.getInstance();
        TimeZone tz = c.getTimeZone();
        sdf.setTimeZone(tz);
        String currentDate = sdf.format(Calendar.getInstance().getTime().getTime());

        try {
            Date date1 = sdf.parse(startTime);
            Date date2 = sdf.parse(endTime);
            Date date = sdf.parse(currentDate);

            if (date.before(date1)) {
                return false;
            } else if (date.after(date2)) {
                return false;
            } else {

            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return true;

        // end**********
    }


    public static float round(float number, int decimalPlace) {
        BigDecimal bd = new BigDecimal(number);
        bd = bd.setScale(decimalPlace, BigDecimal.ROUND_HALF_UP);
        return bd.floatValue();
    }

    public static boolean dateBefore(String startTime) {

        String pattern = "hh:mm a";
        @SuppressLint("SimpleDateFormat")
        //Edited by Janvi 25th oct
                SimpleDateFormat sdf = new SimpleDateFormat(pattern, Locale.US);

//        //est conversion
//        SimpleDateFormat sdfEst = new SimpleDateFormat(pattern, Locale.US);
//        TimeZone tz1 = TimeZone.getTimeZone("America/New_York");
//        sdfEst.setTimeZone(tz1);
//        String currentDateEst = sdfEst.format(Calendar.getInstance().getTime().getTime());
//        System.out.println("CurrentEstTime::" + currentDateEst);
        //end **

        String currentDate = sdf.format(Calendar.getInstance().getTime().getTime());
        SimpleDateFormat sdfEst = new SimpleDateFormat(pattern, Locale.US);
        Calendar c = Calendar.getInstance();
        TimeZone tz = c.getTimeZone();
        sdfEst.setTimeZone(tz);
        String currentDateEst = sdfEst.format(Calendar.getInstance().getTime().getTime());


        try {
            Date date1 = sdf.parse(startTime);
            Date date = sdf.parse(currentDateEst);
            if (date.before(date1)) {
                return false;
            } else {
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return true;
    }

    public static boolean dateAfter(String endTime) {

        String pattern = "hh:mm a";
        @SuppressLint("SimpleDateFormat")
        //Edited by Janvi 25th oct
                SimpleDateFormat sdf = new SimpleDateFormat(pattern, Locale.US);
        //est conversion
//        SimpleDateFormat sdfEst = new SimpleDateFormat(pattern, Locale.US);
//        TimeZone tz1 = TimeZone.getTimeZone("America/New_York");
//        sdfEst.setTimeZone(tz1);
//        String currentDateEst = sdfEst.format(Calendar.getInstance().getTime().getTime());
//        System.out.println("CurrentEstTime::" + currentDateEst);

        SimpleDateFormat sdfEst = new SimpleDateFormat(pattern, Locale.US);
        Calendar c = Calendar.getInstance();
        TimeZone tz = c.getTimeZone();
        sdfEst.setTimeZone(tz);
        String currentDateEst = sdfEst.format(Calendar.getInstance().getTime().getTime());

        //end **
//        String currentDate = sdf.format(Calendar.getInstance().getTime().getTime());
        try {
            Date date2 = sdf.parse(endTime);
            Date date = sdf.parse(currentDateEst);
            if (date.after(date2)) {
                return false;
            } else {
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return true;
    }


    public static String getCurrentDay() {
        String day;
        String pattern = "hh:mm a";
        @SuppressLint("SimpleDateFormat")
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
        String currentDate = sdf.format(Calendar.getInstance().getTime().getTime());

        Calendar sCalendar = Calendar.getInstance();
        day = sCalendar.getDisplayName(Calendar.DAY_OF_WEEK, Calendar.LONG, Locale.getDefault());

        return day;
    }

    public static String getNextDay(int i) {
        String tomorrowDay;
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DAY_OF_YEAR, i);
        Date tomorrow = calendar.getTime();
        @SuppressLint("SimpleDateFormat") SimpleDateFormat sdf = new SimpleDateFormat("EEEE");
        tomorrowDay = sdf.format(tomorrow);
        return tomorrowDay;
    }

//    public static String getdayaftertomorrow(int i) {
//        String tomorrowDay;
//        Calendar calendar = Calendar.getInstance();
//        calendar.add(Calendar.DAY_OF_YEAR, i);
//        Date tomorrow = calendar.getTime();
//        @SuppressLint("SimpleDateFormat") SimpleDateFormat sdf = new SimpleDateFormat("EEEE");
//        tomorrowDay = sdf.format(tomorrow);
//        return tomorrowDay;
//    }

    public static boolean isTimeBetweenTwoHours(int fromHour, int toHour, Calendar now) {
        //Start Time
        Calendar from = Calendar.getInstance();
        from.set(Calendar.HOUR_OF_DAY, fromHour);
        from.set(Calendar.MINUTE, 0);
        //Stop Time
        Calendar to = Calendar.getInstance();
        to.set(Calendar.HOUR_OF_DAY, toHour);
        to.set(Calendar.MINUTE, 0);

        if (to.before(from)) {
            if (now.after(to)) to.add(Calendar.DATE, 1);
            else from.add(Calendar.DATE, -1);
        }
        return now.after(from) && now.before(to);
    }

    public static void ShowDialog(Context c) {
        Activity activity = (Activity) c;
        Constant.pDialog = new ProgressDialog(activity, ProgressDialog.THEME_DEVICE_DEFAULT_LIGHT);
        Constant.pDialog.setMessage(c.getString(R.string.dialog_message));
        Constant.pDialog.setTitle(c.getString(R.string.dialog_title));
        Constant.pDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        //  Constant.pDialog.show();
    }

    public static String getOnlyDigits(String s) {
        Pattern pattern = Pattern.compile("[^0-9]");
        Matcher matcher = pattern.matcher(s);
        String number = matcher.replaceAll("");
        if (number.isEmpty()) {
            number = "0";
        }
        return number;
    }

    public static String getNumberFormat(String number) {

        Long phoneFmt = Long.valueOf(getOnlyDigits(number));

        DecimalFormat phoneDecimalFmt = new DecimalFormat("0000000000");
        String phoneRawString = phoneDecimalFmt.format(phoneFmt);

        java.text.MessageFormat phoneMsgFmt = new java.text.MessageFormat("({0}) {1}-{2}");
        //suposing a grouping of 3-3-4
        String[] phoneNumArr = {phoneRawString.substring(0, 3),
                phoneRawString.substring(3, 6),
                phoneRawString.substring(6)};
        return String.valueOf(phoneMsgFmt.format(phoneNumArr));
    }

    /**
     * Capitalize First latter of string
     **/
    public static String capitalFirstLatter(String s) {
        if (s.length() == 0)
            s = s.toUpperCase();
        else if (s.length() > 0)
            s = s.substring(0, 1).toUpperCase() + s.substring(1).toLowerCase();
        return s;
    }

    /**
     * Force Capitalize complete string
     **/
    public static String forceCapitalString(String s) {
        s = s.toUpperCase();
        return s;
    }

    /**
     * UnderLine Text
     **/
    public static SpannableString underlineText(String text) {
        if (text == null) text = "";
        SpannableString str = new SpannableString(text);
        str.setSpan(new UnderlineSpan(), 0, str.length(), Spanned.SPAN_PARAGRAPH);
        return str;
    }

    /**
     * Strike Text
     **/
    public static SpannableString strikeText(String text) {
        if (text == null) text = "";
        SpannableString str = new SpannableString(text);
        str.setSpan(new StrikethroughSpan(), 0, str.length(), Spanned.SPAN_PARAGRAPH);
        return str;
    }

    public static String capitalizeEachWord(String capString) {
        StringBuffer capBuffer = new StringBuffer();
        Matcher capMatcher = Pattern.compile("([a-z])([a-z]*)", Pattern.CASE_INSENSITIVE).matcher(capString);
        while (capMatcher.find()) {
            capMatcher.appendReplacement(capBuffer, capMatcher.group(1).toUpperCase() + capMatcher.group(2).toLowerCase());
        }

        return capMatcher.appendTail(capBuffer).toString();
    }

    public static Bitmap textAsBitmap(String text, float textSize/*, int textColor*/) {
        Paint paint = new Paint(ANTI_ALIAS_FLAG);
        paint.setTextSize(textSize);
        paint.setColor(BLACK);
        paint.setTextAlign(Paint.Align.LEFT);
        float baseline = -paint.ascent(); // ascent() is negative
        int width = (int) (paint.measureText(text) + 0.5f); // round
        int height = (int) (baseline + paint.descent() + 0.5f);
        Bitmap image = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(image);
        canvas.drawText(text, 0, baseline, paint);
        return image;

        /*final Paint textPaint = new Paint() {
            {
                setColor(Color.WHITE);
                setTextAlign(Paint.Align.LEFT);
                setTextSize(20f);
                setAntiAlias(true);
            }
        };
        final Rect bounds = new Rect();
        textPaint.getTextBounds(text, 0, text.length(), bounds);

        final Bitmap bmp = Bitmap.createBitmap(bounds.width(), bounds.height(), Bitmap.Config.RGB_565); //use ARGB_8888 for better quality
        final Canvas canvas = new Canvas(bmp);
        canvas.drawText(text, 0, 20f, textPaint);*/
       /* FileOutputStream stream = new FileOutputStream(...); //create your FileOutputStream here
        bmp.compress(Bitmap.CompressFormat.PNG, 85, stream);
        bmp.recycle();
        stream.close();*/
        //return bmp;
    }

    public static void HideDialog() {
        Constant.pDialog.hide();
    }

    public static void expand(final View v, int duration, int targetHeight) {

        int prevHeight = v.getHeight();

        v.setVisibility(View.VISIBLE);
        ValueAnimator valueAnimator = ValueAnimator.ofInt(0, targetHeight);
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                v.getLayoutParams().height = (int) animation.getAnimatedValue();
                v.requestLayout();
            }
        });
        valueAnimator.setInterpolator(new DecelerateInterpolator());
        valueAnimator.setDuration(duration);
        valueAnimator.start();
        valueAnimator.addListener(new AnimatorListenerAdapter() {

            @Override
            public void onAnimationEnd(Animator animation) {
                v.clearAnimation();
            }
        });

    }

    public static double roundMyData(double Rval, int no) {
        //no = No of decimal after . you want to print
        double p = (float) Math.pow(10, no);
        Rval = Rval * p;
        double tmp = Math.floor(Rval);
        return tmp / p;
    }

    public static double round(double time) {
        time = Math.round(100 * time);
        return time /= 100;
    }

    public static void collapse(final View v, int duration, int targetHeight) {
        /*if (position == (data.size() - 1)) {
            return;
        }*/
        int prevHeight = v.getHeight();
        ValueAnimator valueAnimator = ValueAnimator.ofInt(prevHeight, targetHeight);
        valueAnimator.setInterpolator(new DecelerateInterpolator());
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                v.getLayoutParams().height = (int) animation.getAnimatedValue();
                v.requestLayout();

            }
        });
        valueAnimator.setInterpolator(new DecelerateInterpolator());
        valueAnimator.setDuration(duration);
        valueAnimator.start();
        valueAnimator.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                // animBoolArray.put(position, false);
                v.clearAnimation();
                v.setVisibility(View.GONE);
            }
        });

    }

    public static LinkedHashMap<String, List<String>> getData() {
        LinkedHashMap<String, List<String>> expandableListDetail = new LinkedHashMap<String, List<String>>();

        List<String> cricket = new ArrayList<String>();
        cricket.add("India");
        cricket.add("Pakistan");
        cricket.add("Australia");
        cricket.add("England");
        cricket.add("South Africa");

        List<String> football = new ArrayList<String>();
        football.add("Brazil");
        football.add("Spain");
        football.add("Germany");
        football.add("Netherlands");
        football.add("Italy");

        List<String> basketball = new ArrayList<String>();
        basketball.add("United States");
        basketball.add("Spain");
        basketball.add("Argentina");
        basketball.add("France");
        basketball.add("Russia");
        List<String> Employee = new ArrayList<String>();

        expandableListDetail.put("CRICKET TEAMS", cricket);
        expandableListDetail.put("FOOTBALL TEAMS", football);
        expandableListDetail.put("BASKETBALL TEAMS", basketball);
        expandableListDetail.put("Employee", Employee);
        return expandableListDetail;
    }

    public static String loadJSONFromAsset(String fileName, Context c) {
        String json = null;
        try {
            InputStream is = c.getAssets().open(fileName);
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
        return json;
    }

    private float calculateProgression(WebView content) {
        float positionTopView = content.getTop();
        float contentHeight = content.getContentHeight();
        float currentScrollPosition = content.getScrollY();
        float percentWebview = (currentScrollPosition - positionTopView) / contentHeight;
        return percentWebview;
    }

    public static boolean isNetworkConnectionAvailable(Context c) {
        ConnectivityManager cm = (ConnectivityManager) c.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();

        if (netInfo != null && netInfo.isConnectedOrConnecting() && canHit()) {
            return true;
        }
        return false;
    }

    public static boolean canHit() {

        try {

            URL url = new URL("http://www.google.com/");
            HttpURLConnection urlConnection = (HttpURLConnection) url
                    .openConnection();
            urlConnection.setConnectTimeout(3000);
            urlConnection.connect();
            urlConnection.disconnect();
            return true;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public static void setImageFromUrl(Context c, String url, ImageView v) {
        Glide.with(c)
                .load(url)
                .fitCenter()
                .error(R.drawable.welcome_default_image)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(v);
    }

    public static void showValidationDialog(Context context, String Validate_instruct, String s) {

        Dialog dialog;
        View view;
        dialog = new Dialog(context, R.style.DialogSlideAnim_login);
        dialog.setCanceledOnTouchOutside(false);
        view = LayoutInflater.from(context).inflate(R.layout.validate_disp, null);

//        final Dialog dialog = new Dialog(context);
//        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
//        dialog.setContentView(R.layout.validate_disp);
        //dialog.setTitle("Title...");

        // set the custom dialog components - text, image and button
        TextView txtErrorName = (TextView) view.findViewById(R.id.txtErrorName);
       txtErrorName.setText(Validate_instruct);
        Button btnOK = (Button) view.findViewById(R.id.btnOK);

        GradientDrawable bgShape = (GradientDrawable) btnOK.getBackground();
        bgShape.setColor(Color.parseColor(Constant.themeModel.ThemeColor));

        WindowManager.LayoutParams params = dialog.getWindow().getAttributes();
        params.width = WindowManager.LayoutParams.WRAP_CONTENT;
        params.height = WindowManager.LayoutParams.WRAP_CONTENT;

        if(s.equalsIgnoreCase("EditshippingAddress")){

            params.width = 550;
            params.height = WindowManager.LayoutParams.WRAP_CONTENT;
            dialog.getWindow().setGravity(Gravity.CENTER);
            txtErrorName.setGravity(Gravity.CENTER);

        }else if (s.equals("Delete My Online Account")){
            txtErrorName.setText(Validate_instruct);
        }else if (s.equals("Delete Account fail")){
            txtErrorName.setText(Validate_instruct);
        }
        else{
                if(Validate_instruct.equalsIgnoreCase("Details saved successfully!")){

                params.width = 550;
                params.height = WindowManager.LayoutParams.WRAP_CONTENT;
                dialog.getWindow().setGravity(Gravity.CENTER);
                txtErrorName.setGravity(Gravity.CENTER);
            }

            if(Validate_instruct.equalsIgnoreCase("Success")){

                params.width = 400;
                params.height = WindowManager.LayoutParams.WRAP_CONTENT;
                dialog.getWindow().setGravity(Gravity.CENTER);
                txtErrorName.setGravity(Gravity.CENTER);
            }
        }

        btnOK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
                if (Validate_instruct.equals("You will be returned to the home page")) {

                    ContactUsFragment.getInstance().onReturnToHome();

//                    if (Constant.SCREEN_LAYOUT == 1) {
//                        MainActivity.getInstance().loadHomeWebPage();
//                    } else if (Constant.SCREEN_LAYOUT == 2) {
//                        MainActivityDup.getInstance().loadHomeWebPage();
//                    }
                }else if(Validate_instruct.equals("Password Changed Successfully!")){

                    ChnagePasswordFragment.getInstance().onReturnToHome();

                }else if(Validate_instruct.equals("You order was canceled and the items have been cleared from your cart.")){

                    PaymentFragment.getInstance().onReturnToHome();
                }
                else if(Validate_instruct.equalsIgnoreCase("Success")){

                    if (Constant.isUSAePAY) {
                        CardOnFileFragment.getInstance().getCreditCardSetting();
                    }else{
                        CardOnFileFragment.getInstance().getCustomerCreditCardDetails();
                    }
                }
                else if(Validate_instruct.equals("Details saved successfully!")){
                    ManageAccountFragment.getInstance().onReturnToHome();

                }else if(s.equalsIgnoreCase("EditshippingAddress")){

                    ShippingAddressFragment.getInstance().onCallShippingDataTask();

                }else if(Validate_instruct.equalsIgnoreCase(context.getResources().getString(R.string.fedexhome))){
//                    Constant.isopenpopup = true;
                    Constant.selectedItemPos_FedexSpinner = 0;
                    DeliveryOptionsFragment.getInstance().callShippingWService();
                    Log.e("callShippingWService", "callShippingWService: 1" );
                }else if (s.equalsIgnoreCase("Delete My Online Account")){
                    Login.onLogOff();
                    Constant.dialog_Delete_My_Online_Account.dismiss();
                }else if (s.equalsIgnoreCase("Delete Account Fail")){
                    dialog.dismiss();
                    Constant.dialog_Delete_My_Online_Account.dismiss();
                }
            }
        });

        dialog.setContentView(view);
        dialog.getWindow().setAttributes(params);
        dialog.setCanceledOnTouchOutside(false);
        dialog.setCancelable(false);

        dialog.show();
    }


    public static void showCardValidationDialog(Context context, String Validate_instruct, String fourDigitCardNumber, String _authCode) {
        final Dialog dialog = new Dialog(context);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        //        dialog.setContentView(R.layout.validate_card_disp);

        dialog.setContentView(R.layout.validate_card_disp_dialog);

        //dialog.setTitle("Title...");
        // set the custom dialog components - text, image and button
        TextView txtTitle = (TextView) dialog.findViewById(R.id.txtTitle);
        txtTitle.setText(Validate_instruct);

        LinearLayout llmainCard = (LinearLayout) dialog.findViewById(R.id.llmainCard);
//        LinearLayout llCardApproval = (LinearLayout) dialog.findViewById(R.id.llCardApproval);

        TextView txtCardEnding = (TextView) dialog.findViewById(R.id.txtCardEnding);
        txtCardEnding.setText("Card Ending In: ");

        TextView txtCardnumber = (TextView) dialog.findViewById(R.id.txtCardnumber);
        txtCardnumber.setText(fourDigitCardNumber);

        TextView txtApprovalCode = (TextView) dialog.findViewById(R.id.txtApprovalCode);
        txtApprovalCode.setText("Approval#: ");

        TextView txtApprovalNumber = (TextView) dialog.findViewById(R.id.txtApprovalNumber);
        txtApprovalNumber.setText(_authCode);

        TextView txtTemp = (TextView) dialog.findViewById(R.id.txtTemp);

        Button btnOK = (Button) dialog.findViewById(R.id.btnOK);
        Button btncancelOrder = (Button) dialog.findViewById(R.id.btncancel);

        if (Validate_instruct.equalsIgnoreCase("APPROVED")) {
            txtApprovalCode.setVisibility(View.VISIBLE);
            txtApprovalNumber.setVisibility(View.VISIBLE);
        } else {
            txtApprovalCode.setVisibility(View.GONE);
            txtApprovalNumber.setVisibility(View.GONE);
        }

        if (Validate_instruct.equalsIgnoreCase("DECLINED")) {
            llmainCard.setVisibility(View.GONE);
            txtTemp.setVisibility(View.INVISIBLE);
            txtTitle.setTextColor(context.getResources().getColor(R.color.red));
            txtTitle.setGravity(Gravity.CENTER);
            btncancelOrder.setVisibility(View.VISIBLE);
            btnOK.setText("BACK");

        } else {
            llmainCard.setVisibility(View.VISIBLE);
            txtTemp.setVisibility(View.GONE);
            txtTitle.setTextColor(context.getResources().getColor(R.color.black));
            txtTitle.setGravity(Gravity.CENTER);
            btnOK.setText("OK");
            btncancelOrder.setVisibility(View.GONE);
        }

        GradientDrawable bgShape = (GradientDrawable) btnOK.getBackground();
        bgShape.setColor(Color.parseColor(Constant.themeModel.ThemeColor));

        GradientDrawable bgShape1 = (GradientDrawable) btncancelOrder.getBackground();
        bgShape1.setColor(Color.parseColor(Constant.themeModel.ThemeColor));

        btnOK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(Validate_instruct.equalsIgnoreCase("APPROVED")){
                    PaymentFragment.getInstance().loadOrderSummary();
                    dialog.dismiss();
                }else{
                    dialog.dismiss();
                }

            }
        });

        btncancelOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PaymentFragment.getInstance().getCustomerCarddataWS();
                dialog.dismiss();

            }
        });

        dialog.setCanceledOnTouchOutside(false);
        dialog.setCancelable(false);
        dialog.show();
    }


    public static void getAccountList() {
        MbsDataModel mbsDataModel1 = new MbsDataModel();
        mbsDataModel1.ID = "0";
//        mbsDataModel1.PageName = "Manage Account Details"; //Manage Account
        mbsDataModel1.PageName = "Manage Account"; //Manage Account
        mbsDataModel1.PageContent = Constant.URL + "Home/AndroidManageAccount";
        mbsDataModel1.status = true;
        Constant.AccountList.add(mbsDataModel1);

        MbsDataModel mbsDataModel2 = new MbsDataModel();
        mbsDataModel2.ID = "1";
        mbsDataModel2.PageName = "Order History"; //Order History
        mbsDataModel2.PageContent = Constant.URL + "home/AndroidOrderHistory";
        mbsDataModel2.status = true;
        Constant.AccountList.add(mbsDataModel2);

        MbsDataModel mbsDataModel3 = new MbsDataModel();
        mbsDataModel3.ID = "2";
        mbsDataModel3.PageName = "Shipping Addresses"; //Shipping Addresses
        mbsDataModel3.status = true;
        mbsDataModel3.PageContent = Constant.URL + "home/AndroidShippingAddress";
        Constant.AccountList.add(mbsDataModel3);

        MbsDataModel mbsDataModel4 = new MbsDataModel();
        mbsDataModel4.ID = "3";
        mbsDataModel4.PageName = "Card On File"; //Card On File
        mbsDataModel4.PageContent = Constant.URL + "Home/AndroidCreditCard";
        mbsDataModel4.status = true;
        Constant.AccountList.add(mbsDataModel4);

        MbsDataModel mbsDataModel5 = new MbsDataModel();
        mbsDataModel5.ID = "4";
        mbsDataModel5.PageName = "Change Password"; //Change Password
        mbsDataModel5.PageContent = Constant.URL + "Home/AndroidChangePassword";
        mbsDataModel5.status = true;
        Constant.AccountList.add(mbsDataModel5);

        MbsDataModel mbsDataModel6 = new MbsDataModel();
        mbsDataModel6.ID = "5";
        mbsDataModel6.PageName = "Rewards"; //Rewards
        mbsDataModel6.PageContent = Constant.URL + "Home/AndroidLoyaltyReward";
        //http://192.168.172.211:888/Home/AndroidLoyaltyReward?customerid=188731&storeno=707
        mbsDataModel6.status = true;
        Constant.AccountList.add(mbsDataModel6);

        //Edited by Janvi 23th Oct *****

        if (Constant.SCREEN_LAYOUT == 2) {

            MbsDataModel mbsDataModel7 = new MbsDataModel();
            mbsDataModel7.ID = "6";
            mbsDataModel7.PageName = "Wish List";
//        mbsDataModel6.PageContent = Constant.URL + "Home/AndroidLoyaltyReward";
            mbsDataModel7.status = true;
            Constant.AccountList.add(mbsDataModel7);


            MbsDataModel mbsDataModel8 = new MbsDataModel();
            mbsDataModel8.ID = "7";
            mbsDataModel8.PageName = "Log Off";
//        mbsDataModel6.PageContent = Constant.URL + "Home/AndroidLoyaltyReward";
            mbsDataModel8.status = true;
            Constant.AccountList.add(mbsDataModel8);

//            Edited by Varun for Delete Account

            MbsDataModel mbsDataModel0 = new MbsDataModel();
            mbsDataModel0.ID = "8";
            mbsDataModel0.PageName = "Delete My Online Account";
            mbsDataModel0.status = true;
            Constant.AccountList.add(mbsDataModel0);

        }else{
            MbsDataModel mbsDataModel0 = new MbsDataModel();
            mbsDataModel0.ID = "6";
            mbsDataModel0.PageName = "Delete My Online Account";
            mbsDataModel0.status = true;
            Constant.AccountList.add(mbsDataModel0);

//            END
        }

        //end*************

    }

    public static void getAccountList2() {
        MbsDataModel mbsDataModel1 = new MbsDataModel();
        mbsDataModel1.ID = "0";
//        mbsDataModel1.PageName = "Manage Account Details"; //Manage Account
        mbsDataModel1.PageName = "Manage Account"; //Manage Account
        mbsDataModel1.PageContent = Constant.URL + "Home/AndroidManageAccount";
        mbsDataModel1.status = true;
        Constant.AccountList2.add(mbsDataModel1);

        //Edited by Janvi 23th Oct *****

        if (Constant.SCREEN_LAYOUT == 2) {

//            MbsDataModel mbsDataModel7 = new MbsDataModel();
//            mbsDataModel7.ID = "6";
//            mbsDataModel7.PageName = "Wish List";
////        mbsDataModel6.PageContent = Constant.URL + "Home/AndroidLoyaltyReward";
//            mbsDataModel7.status = true;
//            Constant.AccountList2.add(mbsDataModel7);


            MbsDataModel mbsDataModel8 = new MbsDataModel();
            mbsDataModel8.ID = "7";
            mbsDataModel8.PageName = "Log Off";
//        mbsDataModel6.PageContent = Constant.URL + "Home/AndroidLoyaltyReward";
            mbsDataModel8.status = true;
            Constant.AccountList2.add(mbsDataModel8);

//            Edited by Varun for Delete Account

            MbsDataModel mbsDataModel9 = new MbsDataModel();
            mbsDataModel9.ID = "8";
            mbsDataModel9.PageName = "Delete My Online Account";
            mbsDataModel9.status = true;
            Constant.AccountList2.add(mbsDataModel9);

//            END
        }else{

            MbsDataModel mbsDataModel9 = new MbsDataModel();
            mbsDataModel9.ID = "8";
            mbsDataModel9.PageName = "Delete My Online Account";
            mbsDataModel9.status = true;
            Constant.AccountList2.add(mbsDataModel9);

        }

        //end*************

    }

    public static String[] splitInEqualParts(final String s, final int n) {
        if (s == null) {
            return null;
        }
        final int strlen = s.length();
        if (strlen < n) {
            // this could be handled differently
            throw new IllegalArgumentException("String too short");
        }
        final String[] arr = new String[n];
        final int tokensize = strlen / n + (strlen % n == 0 ? 0 : 1);
        for (int i = 0; i < n; i++) {
            arr[i] =
                    s.substring(i * tokensize,
                            Math.min((i + 1) * tokensize, strlen));
        }
        return arr;
    }

    //set ButtonTintColor
    public static InputFilter filter = new InputFilter() {
        public CharSequence filter(CharSequence source, int start, int end, Spanned dest, int dstart, int dend) {
            for (int i = start; i < end; i++) {
                if (!Character.isLetterOrDigit(source.charAt(i)) && !Character.toString(source.charAt(i)).equals("@") && !Character.toString(source.charAt(i)).equals("_") && !Character.toString(source.charAt(i)).equals(".") /*&& !Character.toString(source.charAt(i)).equals(" ")*/) {
                    if (Character.isWhitespace(source.charAt(i))) {
                        return "";
                    } else {
                        return "";
                    }
                }

            }
            return null;
        }
    };


    @RequiresApi(api = Build.VERSION_CODES.M)
    @ColorInt
    public static final int getColor(@NonNull Context context, /*@ColorRes */int id) {
        return context.getColor(id);
        /*if (Build.VERSION.SDK_INT >= 23) {
            return context.getColor(id);
        } else {
            return context.getResources().getColor(id);
        }*/
    }

    public static Activity getMainActivit() {
        Activity activity = null;
        if (Constant.SCREEN_LAYOUT == 1) {

        } else if (Constant.SCREEN_LAYOUT == 2) {

        }
        return activity;
    }

    public static void setThemtoToast(Context c, String msg) {
        Toast toast = Toast.makeText(c, msg, Toast.LENGTH_SHORT);
        View view = toast.getView();
//Gets the actual oval background of the Toast then sets the colour filter
        view.getBackground().setColorFilter(Color.parseColor(Constant.themeModel.ThemeColor), PorterDuff.Mode.SRC_IN);
//Gets the TextView from the Toast so it can be editted
        TextView text = view.findViewById(android.R.id.message);
        text.setTextColor(Color.WHITE);
        //text.setTypeface(null, Typeface.BOLD);
        toast.show();
    }

    public static void collapseWithAnimation(LinearLayout llsortandfilter) {

        TranslateAnimation animate = new TranslateAnimation(
                0,                 // fromXDelta
                0,                 // toXDelta
                0,  // fromYDelta
                llsortandfilter.getHeight());                // toYDelta
        animate.setDuration(500);
        animate.setFillAfter(true);
        llsortandfilter.startAnimation(animate);
        llsortandfilter.setVisibility(View.INVISIBLE);

    }

    public static void expandWithAnimation(LinearLayout llsortandfilter) {
        TranslateAnimation animate = new TranslateAnimation(
                0,                 // fromXDelta
                0,                 // toXDelta
                llsortandfilter.getHeight(),  // fromYDelta
                0);                // toYDelta
        animate.setDuration(500);
        animate.setFillAfter(true);
        llsortandfilter.startAnimation(animate);
        llsortandfilter.setVisibility(View.VISIBLE);
    }


    public static void newDesignCommonDialog(Context context, String title, String msg){

        newCommonDialog = new Dialog(context, R.style.DialogSlideAnim_login);
        newCommonDialog.setCanceledOnTouchOutside(false);
        dialog_view = LayoutInflater.from(context).inflate(R.layout.new_design_common_dialog, null);
        TextView tv_title = (TextView)dialog_view.findViewById(R.id.tv_title);
        View view_line = (View)dialog_view.findViewById(R.id.view_line);
        TextView tv_message = (TextView)dialog_view.findViewById(R.id.tv_message);
        TextView tv_message2 = (TextView)dialog_view.findViewById(R.id.tv_message2);
        Button btn_ok = (Button)dialog_view.findViewById(R.id.btn_ok);

        if(!title.isEmpty() && title.equals("Editing not allowed")){
            tv_title.setVisibility(View.VISIBLE);
            tv_title.setText(title);
            view_line.setVisibility(View.VISIBLE);
            tv_message.setVisibility(View.VISIBLE);
            tv_message.setText(msg);
            if (Constant.contatInfo.getPhone() != null && !Constant.contatInfo.getPhone().equals("")) {
                tv_message2.setVisibility(View.VISIBLE);
                tv_message2.setText(Constant.contatInfo.getPhone());
                tv_message2.setTextColor(Color.parseColor(Constant.themeModel.ThemeColor));

                tv_message2.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String phone = tv_message2.getText().toString().trim();
                        Intent intent = new Intent(Intent.ACTION_DIAL, Uri.fromParts("tel", phone, null));
                        context.startActivity(intent);
                    }
                });
            }
        }

        btn_ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                newCommonDialog.dismiss();
            }
        });

        WindowManager.LayoutParams params = newCommonDialog.getWindow().getAttributes();
        params.width = WindowManager.LayoutParams.WRAP_CONTENT;
        params.height = WindowManager.LayoutParams.WRAP_CONTENT;

        newCommonDialog.setContentView(dialog_view);
        newCommonDialog.getWindow().setGravity(Gravity.CENTER);

        WindowManager.LayoutParams layoutParam = newCommonDialog.getWindow().getAttributes();

        layoutParam.y = (MainActivity.getStatusBarHeight(context))/* + (MainActivity.getInstance().getToolBarHeight()) + (MainActivity.getInstance().getToolBarHeight())*/; // bottom margin

        newCommonDialog.getWindow().setAttributes(layoutParam);
        newCommonDialog.show();
    }


    public static void showRealTimeInventoryDialog(Context context, String Validate_instruct, List<ShoppingCardModel> realTimeInventoryList) {

        Dialog dialog;
        View view;
        dialog = new Dialog(context, R.style.DialogSlideAnim_login);
        dialog.setCanceledOnTouchOutside(false);
        view = LayoutInflater.from(context).inflate(R.layout.real_time_inventory_popup, null);

        RecyclerView rv_realtime = (RecyclerView) view.findViewById(R.id.rv_realtime);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(context.getApplicationContext(), LinearLayoutManager.VERTICAL, false);
        rv_realtime.setLayoutManager(layoutManager);
        RealTimeInvAdpater rv_realtimeAdpater = new RealTimeInvAdpater(context,realTimeInventoryList , Validate_instruct);
        rv_realtime.setAdapter(rv_realtimeAdpater);
        rv_realtimeAdpater.notifyDataSetChanged();


        // set the custom dialog components - text, image and button

        TextView txtErrorName = (TextView) view.findViewById(R.id.txtErrorName);
        TextView txtErrorName_price = (TextView) view.findViewById(R.id.txtErrorName_price);
        LinearLayout ll_Real_time_inventory = (LinearLayout) view.findViewById(R.id.ll_Real_time_inventory);

        if (Validate_instruct.equals("Price")){
            ll_Real_time_inventory.setVisibility(View.GONE);
            txtErrorName.setVisibility(View.GONE);
            txtErrorName_price.setVisibility(View.VISIBLE);
            txtErrorName_price.setText("The inventory availability has changed at the store. Please review the revised order before continuing");
        }else if (Validate_instruct.equals("Real time inventory")){
            txtErrorName.setText("Updated Item");
        }

        Button btnOK = (Button) view.findViewById(R.id.btnOK);
        GradientDrawable bgShape = (GradientDrawable) btnOK.getBackground();
        bgShape.setColor(Color.parseColor(Constant.themeModel.ThemeColor));

        WindowManager.LayoutParams params = dialog.getWindow().getAttributes();
        params.width = WindowManager.LayoutParams.WRAP_CONTENT;
        params.height = WindowManager.LayoutParams.WRAP_CONTENT;

        btnOK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();

                PaymentFragment.getInstance().callWSFromRealTime_Okbutton();
//                if (Validate_instruct.equals("You will be returned to the home page")) {
//
//                    ContactUsFragment.getInstance().onReturnToHome();
//
////                    if (Constant.SCREEN_LAYOUT == 1) {
////                        MainActivity.getInstance().loadHomeWebPage();
////                    } else if (Constant.SCREEN_LAYOUT == 2) {
////                        MainActivityDup.getInstance().loadHomeWebPage();
////                    }
//                }else if(Validate_instruct.equals("Password Changed Successfully!")){
//
//                    ChnagePasswordFragment.getInstance().onReturnToHome();
//
//                }else if(Validate_instruct.equals("You order was canceled and the items have been cleared from your cart.")){
//
//                    PaymentFragment.getInstance().onReturnToHome();
//                }
//                else if(Validate_instruct.equalsIgnoreCase("Success")){
//
//                    CardOnFileFragment.getInstance().getCustomerCreditCardDetails();
//                }
//                else if(Validate_instruct.equals("Details saved successfully!")){
//                    ManageAccountFragment.getInstance().onReturnToHome();
//
//                }else if(s.equalsIgnoreCase("EditshippingAddress")){
//
//                    ShippingAddressFragment.getInstance().onCallShippingDataTask();
//                }
            }
        });

        dialog.setContentView(view);
        dialog.getWindow().setAttributes(params);
        dialog.setCanceledOnTouchOutside(false);
        dialog.setCancelable(false);

        dialog.show();
    }

    public static void ValidationDialog(Context context, String please_select_item) {

            Dialog dialog;
            View view;
            dialog = new Dialog(context, R.style.DialogSlideAnim_login);
            dialog.setCanceledOnTouchOutside(false);
            view = LayoutInflater.from(context).inflate(R.layout.validate_card_multi_dialog, null);

            // set the custom dialog components - text, image and button
            TextView txtErrorName = (TextView) view.findViewById(R.id.txtErrorName);
            txtErrorName.setText(please_select_item);
            txtErrorName.setGravity(Gravity.CENTER);

            Button btnOK = (Button) view.findViewById(R.id.btnOK);
            GradientDrawable bgShape = (GradientDrawable) btnOK.getBackground();
            bgShape.setColor(Color.parseColor(Constant.themeModel.ThemeColor));

            WindowManager.LayoutParams params = dialog.getWindow().getAttributes();
            params.width = WindowManager.LayoutParams.WRAP_CONTENT;
            params.height = WindowManager.LayoutParams.WRAP_CONTENT;

            btnOK.setOnClickListener(new View.OnClickListener() {
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

    public static void Validation_Simple_Dialog(Context context, String please_select_item, String string) {

        Dialog dialog;
        View view;
        dialog = new Dialog(context, R.style.DialogSlideAnim_login);
        dialog.setCanceledOnTouchOutside(false);
        view = LayoutInflater.from(context).inflate(R.layout.validate_card_multi_dialog, null);

        // set the custom dialog components - text, image and button
        TextView txtErrorName = (TextView) view.findViewById(R.id.txtErrorName);
        txtErrorName.setText(please_select_item);
        txtErrorName.setGravity(Gravity.CENTER);

        Button btnOK = (Button) view.findViewById(R.id.btnOK);
        GradientDrawable bgShape = (GradientDrawable) btnOK.getBackground();
        bgShape.setColor(Color.parseColor(Constant.themeModel.ThemeColor));

        WindowManager.LayoutParams params = dialog.getWindow().getAttributes();
        params.width = WindowManager.LayoutParams.WRAP_CONTENT;
        params.height = WindowManager.LayoutParams.WRAP_CONTENT;

        btnOK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();

                if (string.equals("sign_up_loyalty_success")){
                    PaymentFragment.getInstance().cbx_join_reward_payment_fragment.setChecked(false);
                    PaymentFragment.getInstance().ll_join_reward_payment_fragment.setVisibility(View.GONE);
                    Constant.iscomefromSignuployalty=true;
                    PaymentFragment.getInstance().getLoyaltyReward();
                    HomepageFragment.getInstance().loadRewardWSData();
                }else if (string.equals("sign_up_loyalty_fail")){
                    PaymentFragment.getInstance().cbx_join_reward_payment_fragment.setChecked(false);
                }

            }
        });

        dialog.setContentView(view);
        dialog.getWindow().setAttributes(params);
        dialog.setCanceledOnTouchOutside(false);
        dialog.setCancelable(false);

        dialog.show();
    }

    public static String getCurrentDate() {
        // Get the current date
        Date currentDate = new Date();

        // Create a SimpleDateFormat object to format the date
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());

        // Format the date and return it
        return sdf.format(currentDate);
    }

    public static void vibrateDevice(Context context) {
        Vibrator vibrator = (Vibrator) context.getSystemService(Context.VIBRATOR_SERVICE);

        // Check if the device has a vibrator
        if (vibrator != null && vibrator.hasVibrator()) {
            // Vibrate for the predefined duration
            vibrator.vibrate(500);
        }
    }

}
