package com.aspl.fragment;


import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.aspl.Utils.Constant;
import com.aspl.Utils.DialogUtils;
import com.aspl.Utils.Utils;
import com.aspl.mbs.MainActivity;
import com.aspl.mbs.MainActivityDup;
import com.aspl.mbs.R;
import com.aspl.mbsmodel.CheckGiftCardBalanceModel;
import com.aspl.mbsmodel.GiftcardModel;
import com.aspl.task.TaskGiftcard;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class GiftCardFragment extends Fragment implements TaskGiftcard.TaskGiftcardEvent {


    public static final String TAG = "GiftcardBalance";
    public static GiftCardFragment giftCardFragment;
    List<GiftcardModel> GiftCardList;
    Button btnCheck;
    TextView tv_BalanceCheck,tv_giftCardBal;
    EditText edBalancecheck;
    ImageView img_giftcard;
    LinearLayout llMainLayout;
    Context context;
    private String giftc_default_imgUrl = Constant.URL + "img/" + "cards-hero-gift.png";
    private String giftc_imgUrl = Constant.IMG_BASE + "/WebStoreImages/" + "logo/" + "giftcard/" +Constant.STOREID + "/";

    public GiftCardFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        giftCardFragment = this;
        View view = inflater.inflate(R.layout.fragment_gift_card, container, false);
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

            if(Constant.isFromMic){
                MainActivity.getInstance().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);
                MainActivity.getInstance().mSearchedt.requestFocus();
                MainActivity.getInstance().mSearchedt.setFocusableInTouchMode(true);
                Constant.isFromMic = false;
            }else{
                MainActivity.getInstance().mSearchedt.clearFocus();
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
                Utils.hideKeyboard();
            }
        }

        Utils.hideKeyboard();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        loadGiftcardWSdata();

        btnCheck = (Button) view.findViewById(R.id.btnCheck);
        GradientDrawable bgShape = (GradientDrawable) btnCheck.getBackground();
        bgShape.setColor(Color.parseColor(Constant.themeModel.ThemeColor));

//        nested_aboutus = view.findViewById(R.id.nested_aboutus);
//        nested_aboutus.setBackgroundColor(Color.parseColor(Constant.themeModel.Backgroundcolor));
//        ll_root_aboutuslayout = view.findViewById(R.id.ll_root_aboutuslayout);

        tv_BalanceCheck = (TextView) view.findViewById(R.id.tv_BalanceCheck);
        tv_giftCardBal = (TextView) view.findViewById(R.id.tv_giftCardBal);
        edBalancecheck = (EditText)view.findViewById(R.id.edBalancecheck);
        img_giftcard = (ImageView)view.findViewById(R.id.img_giftcard);
        llMainLayout = (LinearLayout) view.findViewById(R.id.llMainLayout);
        llMainLayout.setBackgroundColor(Color.parseColor(Constant.themeModel.Backgroundcolor));

//        if (context != null) {
//            Glide.with(context).load(imgUrl + itemDescModel.getInvLargeImage())
//                    .placeholder(R.drawable.no_image_new)/*.placeholder(d)*/
//                    .diskCacheStrategy(DiskCacheStrategy.NONE)
//                    .skipMemoryCache(true).into(img_item);
//        }

        btnCheck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(edBalancecheck.getText().toString().isEmpty()) {
                    DialogUtils.onCommonDialog(getActivity(),"giftcard","", null);
                }else{
                    String url = null;
                    url = Constant.WS_BASE_URL + Constant.CHECK_GIFTCARD_BALANCE + "/" + Constant.STOREID +"/" + edBalancecheck.getText().toString().trim();
                    TaskGiftcard taskGiftcard = new TaskGiftcard(GiftCardFragment.this,getContext());
                    taskGiftcard.execute(url);
                }
            }
        });
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof Activity) {//Name of your activity
            this.context = (Activity) context;
        }
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        //save the activity to a member of this fragment
        context = activity;
    }

    private void loadGiftcardWSdata() {

        String url = null;
        url = Constant.WS_BASE_URL + Constant.GETPAGES_DETAIL + "/GiftCard/" + Constant.STOREID;
        TaskGiftcard taskGiftcard = new TaskGiftcard(GiftCardFragment.this,getContext());
        taskGiftcard.execute(url);
    }

    @Override
    public void onGetGiftcardResult(List<GiftcardModel> GiftCardList) {
        if(GiftCardList != null && GiftCardList.size() > 0){
            for(int i=0; i< GiftCardList.size();i++){
                GiftcardModel giftcardModel = GiftCardList.get(i);

                if (context != null) {
                    if(giftcardModel.getLogo() != null && !giftcardModel.getLogo().isEmpty()) {
                        Glide.with(context).load(giftc_imgUrl + giftcardModel.getLogo())
                                .diskCacheStrategy(DiskCacheStrategy.ALL)
                                .skipMemoryCache(true).into(img_giftcard);
                    }else{

                        Glide.with(context).load(giftc_default_imgUrl)
                                .diskCacheStrategy(DiskCacheStrategy.ALL)
                                .skipMemoryCache(true).into(img_giftcard);
                    }
                }

            }
        }

    }

    @Override
    public void onGetGiftcardCheckBalResult(CheckGiftCardBalanceModel checkGiftCardBalanceModel) {
        if(checkGiftCardBalanceModel != null){

            if(checkGiftCardBalanceModel.getGiftCardNo() != null && !checkGiftCardBalanceModel.getGiftCardNo().isEmpty()) {

                if (checkGiftCardBalanceModel.getGiftCardNo().equals(edBalancecheck.getText().toString().trim())) {
                    DialogUtils.onCommonDialog(getActivity(), "GiftCardResult", edBalancecheck.getText().toString().trim(),checkGiftCardBalanceModel);
                    edBalancecheck.setText("");
                }
            }else {
                DialogUtils.onCommonDialog(getActivity(), "GiftCardNotMatch", edBalancecheck.getText().toString().trim(),null);
                edBalancecheck.setText("");

            }

        }
    }
}
