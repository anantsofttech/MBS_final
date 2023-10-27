package com.aspl.fragment;


import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.Point;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.aspl.Utils.Constant;
import com.aspl.Utils.Utils;
import com.aspl.mbs.MainActivity;
import com.aspl.mbs.MainActivityDup;
import com.aspl.mbs.R;
import com.aspl.mbsmodel.RewardModel;
import com.aspl.mbsmodel.UserModel;
import com.aspl.task.TaskReward;
import com.google.zxing.WriterException;

import androidmads.library.qrgenearator.QRGContents;
import androidmads.library.qrgenearator.QRGEncoder;

import static android.content.Context.WINDOW_SERVICE;


/**
 * A simple {@link Fragment} subclass.
 */
public class RewardFragment extends Fragment implements TaskReward.TaskRewardEvent {

    public static final String TAG = "Reward";
    public static RewardFragment rewardFragment;
    TextView tv_root_reward_fragment, tv_member, tv_memberName, tv_rewardpoint, tv_rewardPointValue, tv_rebateAvailable,
            tv_rebateAvailableVal, tv_lastPurchase, tv_lastPurchase_Val, tv_rewards, tv_rewardsVal, tv_program, tv_programtxt;
    LinearLayout ll_root_rewardlayout;

    private ImageView qrCodeIV;
    Bitmap bitmap;
    QRGEncoder qrgEncoder;

    public RewardFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        rewardFragment = this;
        View view = inflater.inflate(R.layout.fragment_reward, container, false);
        return view;

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        loadRewardWSData();
        ll_root_rewardlayout = (LinearLayout)view.findViewById(R.id.ll_root_rewardlayout);
        ll_root_rewardlayout.setBackgroundColor(Color.parseColor(Constant.themeModel.Backgroundcolor));
        tv_root_reward_fragment = (TextView) view.findViewById(R.id.tv_root_reward_fragment);
        tv_root_reward_fragment.setTextColor(Color.parseColor(Constant.themeModel.ThemeColor));
        tv_member = (TextView) view.findViewById(R.id.tv_member);
        tv_memberName = (TextView) view.findViewById(R.id.tv_memberName);
        tv_rewardpoint = (TextView) view.findViewById(R.id.tv_rewardpoint);
        tv_rewardPointValue = (TextView) view.findViewById(R.id.tv_rewardPointValue);
        tv_rebateAvailable = (TextView) view.findViewById(R.id.tv_rebateAvailable);
        tv_rebateAvailableVal = (TextView) view.findViewById(R.id.tv_rebateAvailableVal);
        tv_lastPurchase = (TextView) view.findViewById(R.id.tv_lastPurchase);
        tv_lastPurchase_Val = (TextView) view.findViewById(R.id.tv_lastPurchase_Val);
        tv_rewards = (TextView) view.findViewById(R.id.tv_rewards);
        tv_rewardsVal = (TextView) view.findViewById(R.id.tv_rewardsVal);
        tv_program = (TextView) view.findViewById(R.id.tv_program);
        tv_programtxt = (TextView) view.findViewById(R.id.tv_programtxt);

        qrCodeIV = view.findViewById(R.id.idIVQrcode);

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
    private void loadRewardWSData() {

        String url = null;
        if (UserModel.Cust_mst_ID != null) {
            //URL :- http://192.168.172.211:889/WebStoreRestService.svc/GetCustomerCartData/188856/wishlist/707
            url = Constant.WS_BASE_URL + Constant.GET_LOYALTY_INFO + UserModel.Cust_mst_ID + "/" + Constant.STOREID;
            TaskReward taskReward = new TaskReward(RewardFragment.this,getContext());
            taskReward.execute(url);
            
        }
    }

    @Override
    public void onRewardResult(RewardModel rewardModel) {
        if (rewardModel != null) {

            if(rewardModel.getCustomerName() != null && !rewardModel.getCustomerName().equals("")){
                tv_memberName.setText(rewardModel.getCustomerName());
            }

            if (rewardModel.getLastPurchaseDate() != null && !rewardModel.getLastPurchaseDate().equals("")){
                tv_lastPurchase.setVisibility(View.VISIBLE);
                tv_lastPurchase_Val.setVisibility(View.VISIBLE);
                tv_lastPurchase_Val.setText(rewardModel.getLastPurchaseDate());
            }else{
                tv_lastPurchase.setVisibility(View.GONE);
                tv_lastPurchase_Val.setVisibility(View.GONE);
            }

            if (rewardModel.getLoyaltyCard().equals("") || rewardModel.getLoyaltyCard().equals(null)) {
                tv_rewardsVal.setText("You are not signed up for our rewards programs!");
                tv_rewardsVal.setTextColor(Color.RED);
                qrCodeIV.setVisibility(View.GONE);
            } else {
                tv_rewardsVal.setText(rewardModel.getLoyaltyCard());


            }

            if (rewardModel.getLoyaltyCard().equals("") || rewardModel.getLoyaltyCard().equals(null)){
                qrCodeIV.setVisibility(View.GONE);
            }else{

//                QR CODE
                qrCodeIV.setVisibility(View.VISIBLE);

                WindowManager manager = (WindowManager) getContext().getSystemService(WINDOW_SERVICE);
                Display display = manager.getDefaultDisplay();
                Point point = new Point();
                display.getSize(point);
                int width = point.x;
                int height = point.y;
                int smallerDimension = width < height ? width : height;
                smallerDimension = smallerDimension * 3 / 4;

                qrgEncoder = new QRGEncoder(rewardModel.getLoyaltyCard(), null, QRGContents.Type.TEXT, smallerDimension);

                try {
                    bitmap = qrgEncoder.encodeAsBitmap();
                    qrCodeIV.setImageBitmap(bitmap);
                } catch (WriterException e) {
                    Log.e(TAG,"QRCODE"+e.toString());
                }
//                 END
            }

            if (rewardModel.getProgramingRule() != null && !rewardModel.getProgramingRule().equals("")) {
                tv_programtxt.setText(rewardModel.getProgramingRule());
            }

            if (rewardModel.getLoyaltyType().equals("Internal")) {

                if (rewardModel.getPoints() != null && !rewardModel.getPoints().equals("") && !rewardModel.getPoints().equals("0")) {
                    tv_rewardpoint.setVisibility(View.VISIBLE);
                    tv_rewardPointValue.setVisibility(View.VISIBLE);
                    tv_rewardPointValue.setText(rewardModel.getPoints());
                }else{
                    tv_rewardpoint.setVisibility(View.GONE);
                    tv_rewardPointValue.setVisibility(View.GONE);
                }

                if (rewardModel.getRebate() != null && !rewardModel.getRebate().equals("")) {

                    double rebate = Double.parseDouble(rewardModel.getRebate());

                    if (rebate > 0) {
                        tv_rebateAvailable.setVisibility(View.VISIBLE);
                        tv_rebateAvailableVal.setVisibility(View.VISIBLE);
                        tv_rebateAvailableVal.setText("$" + rewardModel.getRebate());
                    }else{
                        tv_rebateAvailable.setVisibility(View.GONE);
                        tv_rebateAvailableVal.setVisibility(View.GONE);
                    }
                }
                else{
                    tv_rebateAvailable.setVisibility(View.GONE);
                    tv_rebateAvailableVal.setVisibility(View.GONE);
                }

            } else if (rewardModel.getLoyaltyType().equals("Online")) {

                if (rewardModel.getRebate() != null && !rewardModel.getRebate().equals("")) {

                    double rebate = Double.parseDouble(rewardModel.getRebate());

                    if (rebate > 0) {
                        tv_rebateAvailable.setVisibility(View.VISIBLE);
                        tv_rebateAvailableVal.setVisibility(View.VISIBLE);
                        tv_rebateAvailableVal.setText("$" + rewardModel.getRebate());
                    }else{
                        tv_rebateAvailable.setVisibility(View.GONE);
                        tv_rebateAvailableVal.setVisibility(View.GONE);
                    }
                }else {
                    tv_rebateAvailable.setVisibility(View.GONE);
                    tv_rebateAvailableVal.setVisibility(View.GONE);
                }

            } else if (rewardModel.getLoyaltyType().equals("Skoop")) {

                if (rewardModel.getPhoneNo().equals("") || !rewardModel.getPhoneNo().equals("M")) {
                    tv_rewards.setVisibility(View.GONE);
                    tv_rewardsVal.setVisibility(View.GONE);
                } else {
                    if (rewardModel.getRebate() != null && !rewardModel.getRebate().equals("")) {

                        double rebate = Double.parseDouble(rewardModel.getRebate());

                        if (rebate > 0) {
                            tv_rebateAvailable.setVisibility(View.VISIBLE);
                            tv_rebateAvailableVal.setVisibility(View.VISIBLE);
                            tv_rebateAvailableVal.setText("$" + rewardModel.getRebate());

                        } else {
                            tv_rebateAvailable.setVisibility(View.GONE);
                            tv_rebateAvailableVal.setVisibility(View.GONE);
                        }
                    }else{
                        tv_rebateAvailable.setVisibility(View.GONE);
                        tv_rebateAvailableVal.setVisibility(View.GONE);
                    }
                }
            }
        }
    }
}





