package com.aspl.fragment;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Rect;
import android.graphics.drawable.GradientDrawable;
import android.net.Uri;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.aspl.Adapter.StoreDeliveryAdapter;
import com.aspl.Utils.Constant;
import com.aspl.Utils.Utils;
import com.aspl.mbs.MainActivity;
import com.aspl.mbs.MainActivityDup;
import com.aspl.mbs.R;
import com.aspl.mbsmodel.ContatInfo;
import com.aspl.mbsmodel.StoreHour;
import com.aspl.task.TaskContactInfo;
import com.aspl.task.TaskStoreDeliveryHours;
import com.bumptech.glide.Glide;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class StoreDeliveryHourFragment extends Fragment implements TaskStoreDeliveryHours.StoreDeliveryHoursEvent, TaskContactInfo.TaskContactInfoEvent {

    public static final String TAG = "storeDeliveryHour";
    public static StoreDeliveryHourFragment storeDeliveryHourFragment;
    Context context;
    Button btnStoreHour, btnDeliveryHour;
    LinearLayout llstoreDeliverybtn,ll_main_storeDelivery,ll_empty_view;
    TextView tv_tagline,tvAddress1,tvAddress2,tv_phone;
    ImageView img_StoreLogo;
    RecyclerView recyclerView;
    String type,Store_DeliveryBtnText;
    StoreDeliveryAdapter storeDeliveryAdapter;
    ProgressBar progressBar;

    public StoreDeliveryHourFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        storeDeliveryHourFragment = this;
        View view = inflater.inflate(R.layout.fragment_store_delivery_hour, container, false);
        Bundle bundle = this.getArguments();
        if (bundle != null) {
            Store_DeliveryBtnText = bundle.getString("store_deliveryBtnText", "");
        }
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();

        if(Constant.SCREEN_LAYOUT == 2){
            MainActivityDup.getInstance().showbackButton();
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
                MainActivity.getInstance().mSearchedt.setText("");
                Utils.hideKeyboard();
            }

        }
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
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        //save the activity to a member of this fragment
        context = activity;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        progressBar = (ProgressBar)view.findViewById(R.id.progressBar);
        progressBar.setVisibility(View.VISIBLE);
        ll_empty_view = (LinearLayout) view.findViewById(R.id.ll_empty_view);
        ll_empty_view.setVisibility(View.VISIBLE);

        img_StoreLogo = (ImageView) view.findViewById(R.id.img_StoreLogo);
        if (Constant.Store_Delivery_Logo != null && !Constant.Store_Delivery_Logo.isEmpty()) {
                String sturl = Constant.IMG_BASEURL + Constant.LOGO + Constant.STOREID + "/" + Constant.Store_Delivery_Logo;
                Utils.setImageFromUrl(context, sturl, img_StoreLogo);
        }else{
//            Glide.with(context)
//                    .load(getResources().getDrawable(R.drawable.welcome_default_image))
//                    .asBitmap()
//                    .fitCenter()
//                    .diskCacheStrategy(DiskCacheStrategy.ALL)
//                    .into(img_StoreLogo);

            Glide.with(context).load(getResources().getIdentifier("welcome_default_image", "drawable", getActivity().getPackageName()))
                    .into(img_StoreLogo);
        }

        ll_main_storeDelivery = (LinearLayout) view.findViewById(R.id.ll_main_storeDelivery);
        ll_main_storeDelivery.setBackgroundColor(Color.parseColor(Constant.themeModel.Backgroundcolor));

        btnStoreHour = (Button) view.findViewById(R.id.btnStoreHour);
        btnDeliveryHour = (Button) view.findViewById(R.id.btnDeliveryHour);

        if(!Store_DeliveryBtnText.isEmpty()){
            if(Store_DeliveryBtnText.equals("Store/Delivery Hours")){
                btnDeliveryHour.setVisibility(View.VISIBLE);

                llstoreDeliverybtn = (LinearLayout) view.findViewById(R.id.llstoreDeliverybtn);
                GradientDrawable bgShape = (GradientDrawable) llstoreDeliverybtn.getBackground();
                bgShape.setStroke(2, Color.parseColor(Constant.themeModel.ThemeColor));
                setStoreHours_ThemeColorBgWithRadius();

            }else if(Store_DeliveryBtnText.equals("Delivery Hours")){
                btnDeliveryHour.setVisibility(View.VISIBLE);

                llstoreDeliverybtn = (LinearLayout) view.findViewById(R.id.llstoreDeliverybtn);
                GradientDrawable bgShape = (GradientDrawable) llstoreDeliverybtn.getBackground();
                bgShape.setStroke(2, Color.parseColor(Constant.themeModel.ThemeColor));
//                setStoreHours_ThemeColorBgWithRadius();
                setdeliveryHours_ThemeColorBgWithRadius();

            }else if(Store_DeliveryBtnText.equals("Store Hours")){

                btnDeliveryHour.setVisibility(View.GONE);
                GradientDrawable shape = new GradientDrawable();
                shape.setShape(GradientDrawable.RECTANGLE);
                shape.setCornerRadii(new float[]{8, 8, 8, 8, 8, 8, 8, 8});
                shape.setColor(Color.parseColor(Constant.themeModel.ThemeColor));
                btnStoreHour.setBackgroundDrawable(shape);
                btnStoreHour.setTextColor(getResources().getColor(R.color.androidWhite));
            }
        }

        tv_tagline = (TextView) view.findViewById(R.id.tv_tagline);
        if(Constant.storeDeliveryHourTagline != null && !Constant.storeDeliveryHourTagline.isEmpty()){
            tv_tagline.setText(Constant.storeDeliveryHourTagline.trim());
        }else{
            tv_tagline.setText("Everything You Need!!");
        }
        tv_tagline.setTextColor(Color.parseColor(Constant.themeModel.ThemeColor));
        tvAddress1 = (TextView) view.findViewById(R.id.tvAddress1);
        tvAddress2 = (TextView) view.findViewById(R.id.tvAddress2);
        tv_phone = (TextView) view.findViewById(R.id.tv_phone);

        if (Constant.contatInfo != null && Constant.contatInfo.getAddress() != null) {
            String address2 = "";

            if (Constant.contatInfo.getAddress() != null && !Constant.contatInfo.getAddress().equals("")) {
                tvAddress1.setText(Constant.contatInfo.getAddress());
            }
            if (Constant.contatInfo.getCity() != null && !Constant.contatInfo.getCity().equals("")) {
                address2 = Constant.contatInfo.getCity()+ ",";
            }
            if (Constant.contatInfo.getState() != null && !Constant.contatInfo.getState().equals("")) {
                address2 = address2 + " " + Constant.contatInfo.getState();
            }
            if (Constant.contatInfo.getZip() != null && !Constant.contatInfo.getZip().equals("")) {
                address2 = address2 + " " + Constant.contatInfo.getZip();
            }

            if (!address2.equals("")) {
                tvAddress2.setText(address2);
            }

            if (Constant.contatInfo.getPhone() != null && !Constant.contatInfo.getPhone().equals("")) {
                tv_phone.setText(Constant.contatInfo.getPhone());
                tv_phone.setTextColor(Color.parseColor(Constant.themeModel.ThemeColor));
            }
        }
        else{
            loadContactInfoWSdata();
        }

        if(Store_DeliveryBtnText.equals("Delivery Hours")){
            type = "delivery";
            loadStoreDeliveryHoursWS(type);
        }else{
            type = "store";
            loadStoreDeliveryHoursWS(type);
        }

        recyclerView = (RecyclerView)view.findViewById(R.id.recyclerView);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);

        tv_phone.setOnClickListener(new View.OnClickListener() {
            @Override

            public void onClick(View v) {

                String phone = tv_phone.getText().toString().trim();
                Intent intent = new Intent(Intent.ACTION_DIAL, Uri.fromParts("tel", phone, null));
                startActivity(intent);
            }

        });
        btnStoreHour.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setStoreHours_ThemeColorBgWithRadius();
                type = "store";
                loadStoreDeliveryHoursWS(type);
            }
        });
//
        btnDeliveryHour.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setdeliveryHours_ThemeColorBgWithRadius();
                type = "delivery";
                loadStoreDeliveryHoursWS(type);
            }
        });

        if(Constant.SCREEN_LAYOUT==2) {

            view.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
                @Override
                public void onGlobalLayout() {
                    Rect r = new Rect();
                    view.getWindowVisibleDisplayFrame(r);

                    int heightDiff = view.getRootView().getHeight() - (r.bottom - r.top);

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

    private void loadStoreDeliveryHoursWS(String type) {

        String Url = Constant.WS_BASE_URL + Constant.GET_DELIVERY_HOURS + "/" +  Constant.STOREID + "/" + type;

        TaskStoreDeliveryHours taskStoreDeliveryHours = new TaskStoreDeliveryHours(this,getActivity(),"");
        taskStoreDeliveryHours.execute(Url);
    }

    private void setStoreHours_ThemeColorBgWithRadius() {
        GradientDrawable shape = new GradientDrawable();
        shape.setShape(GradientDrawable.RECTANGLE);
        shape.setCornerRadii(new float[]{8, 8, 0, 0, 0, 0, 8, 8});
        shape.setColor(Color.parseColor(Constant.themeModel.ThemeColor));
        btnStoreHour.setBackgroundDrawable(shape);
        btnStoreHour.setTextColor(getResources().getColor(R.color.androidWhite));
        btnDeliveryHour.setTextColor(Color.parseColor(Constant.themeModel.ThemeColor));
        btnDeliveryHour.setBackgroundColor(getResources().getColor(R.color.transparent));
    }

    private void setdeliveryHours_ThemeColorBgWithRadius() {

        GradientDrawable shape = new GradientDrawable();
        shape.setShape(GradientDrawable.RECTANGLE);
        shape.setCornerRadii(new float[]{0, 0, 8, 8, 8, 8, 0, 0});
        shape.setColor(Color.parseColor(Constant.themeModel.ThemeColor));
        btnDeliveryHour.setBackgroundDrawable(shape);
        btnDeliveryHour.setTextColor(getResources().getColor(R.color.androidWhite));
        btnStoreHour.setTextColor(Color.parseColor(Constant.themeModel.ThemeColor));
        btnStoreHour.setBackgroundColor(getResources().getColor(R.color.transparent));

        //        gradientDrawable.setCornerRadii(
//                new float[]
//                        {
//                                cornersTopLeftRadius, cornersTopLeftRadius,
//                                cornersTopRightRadius, cornersTopRightRadius,
//                                cornersBottomRightRadius, cornersBottomRightRadius,
//                                cornersBottomLeftRadius, cornersBottomLeftRadius
//                        }
//        );

    }

    @Override
    public void onGetStoreDeliveryHoursResult(List<StoreHour> storeHourList) {
        if(storeHourList != null && storeHourList.size() > 0) {
            progressBar.setVisibility(View.GONE);
            ll_empty_view.setVisibility(View.GONE);
            storeDeliveryAdapter = new StoreDeliveryAdapter(getActivity(), storeHourList);
            recyclerView.setAdapter(storeDeliveryAdapter);
        }
    }

    private void loadContactInfoWSdata() {

        String getContactInfoURL = Constant.WS_BASE_URL + Constant.GET_CONTACT_INFO + "/" + Constant.STOREID;
        TaskContactInfo taskContactInfo = new TaskContactInfo(StoreDeliveryHourFragment.this, getContext());
        taskContactInfo.execute(getContactInfoURL);
    }

    @Override
    public void contactInfoEventResult(ContatInfo contatInfo) {
        if (contatInfo != null) {
            Constant.contatInfo = contatInfo;
            String address2 = "";

            if (Constant.contatInfo.getAddress() != null && !Constant.contatInfo.getAddress().equals("")) {
                tvAddress1.setText(Constant.contatInfo.getAddress());
            }
            if (Constant.contatInfo.getCity() != null && !Constant.contatInfo.getCity().equals("")) {
                address2 = Constant.contatInfo.getCity();
            }
            if (Constant.contatInfo.getState() != null && !Constant.contatInfo.getState().equals("")) {
                address2 = address2 + " " + Constant.contatInfo.getState();
            }
            if (Constant.contatInfo.getZip() != null && !Constant.contatInfo.getZip().equals("")) {
                address2 = address2 + " " + Constant.contatInfo.getZip();
            }

            if (!address2.equals("")) {
                tvAddress2.setText(address2);
            }

            if (Constant.contatInfo.getPhone() != null && !Constant.contatInfo.getPhone().equals("")) {
                tv_phone.setText(Constant.contatInfo.getPhone());
                tv_phone.setTextColor(Color.parseColor(Constant.themeModel.ThemeColor));
            }
        }
    }

}
