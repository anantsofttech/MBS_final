package com.aspl.fragment;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.aspl.Adapter.FooterAdapter;
import com.aspl.Utils.Constant;
import com.aspl.mbs.MainActivity;
import com.aspl.mbs.MainActivityDup;
import com.aspl.mbs.R;
import com.aspl.mbsmodel.UserModel;
import com.aspl.ws.Async_getCommonService;

/**
 * Created by sonu on 14/01/17.
 */

public class ProfileFragment_layout2 extends Fragment implements View.OnClickListener {

    public static final String FRAGMENT_TITLE = "profileFragment_layout2";
    public static final String TAG = "ProfileFragment_layout2";
    private String fragmentTitle;
    LinearLayout view_container;
    public static TextView txtPersonName, txtPersonEmail;
    // ImageView imgpersonlogo;
    public ListView bottom_list;
    public ExpandableListView mManage_expList;

    public ProfileFragment_layout2() {
    }

    public static ProfileFragment_layout2 profileFragment_layout2;

    public static ProfileFragment_layout2 getInstance() {
        return profileFragment_layout2;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(Constant.SCREEN_LAYOUT == 2){
            MainActivityDup.getInstance().hidebackbutton();
        }
        //Get the fragment title via Argument
        //fragmentTitle = getArguments().getString(FRAGMENT_TITLE);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.profile_fragment_layout2, container, false);
    }

    @Override
    public void onResume() {
        super.onResume();

        if(Constant.SCREEN_LAYOUT == 2){
            MainActivityDup.getInstance().hidebackbutton();
        }
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        profileFragment_layout2 = this;
        // AppCompatTextView textView = (AppCompatTextView) view.findViewById(R.id.dummy_fragment_title);
        //textView.setText(fragmentTitle);//Set the fetched argument via Argument
        view_container = view.findViewById(R.id.view_container);
        view_container.setBackgroundColor(Color.parseColor(Constant.themeModel.ThemeColor));
        txtPersonEmail = view.findViewById(R.id.txtPersonEmail);
        txtPersonName = view.findViewById(R.id.txtPersonName);
        bottom_list = view.findViewById(R.id.bottom_list);
        //imgpersonlogo = view.findViewById(R.id.imgpersonlogo);
        txtPersonName.setText(Constant.themeModel.StoreName);
        mManage_expList = view.findViewById(R.id.Manage_expList);
        /*ViewTreeObserver vto = mManage_expList.getViewTreeObserver();

        vto.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                mManage_expList.setIndicatorBounds(mManage_expList.getRight() - 100, mManage_expList.getWidth() - 50);
            }
        });*/
        mManage_expList.setVisibility(View.GONE);
        txtPersonEmail.setOnClickListener(this);
//        Edited by Varun for Guest feature in layout 2
        if (!Constant.ISguest){
            String Url = Constant.WS_BASE_URL + Constant.CHECK_PASSWORD + Constant.AppPref.getString("email", "") + "/" + Constant.AppPref.getString("password", "") + "/" + Constant.STOREID;
            new Async_getCommonService(getActivity(), Url).execute();
        }else{
            LoadAccountList();
            Login.setUserDetail(Constant.UserModeltest);
        }
//        END
//          String Url = Constant.WS_BASE_URL + Constant.CHECK_PASSWORD + Constant.AppPref.getString("email", "") + "/" + Constant.AppPref.getString("password", "") + "/" + Constant.STOREID;
//            new Async_getCommonService(getActivity(), Url).execute();
        String sturl = Constant.IMG_BASEURL + Constant.LOGO + Constant.STOREID + "/" + Constant.themeModel.StoreLogo;
        Log.e("Log", "STURL=" + sturl);
        //Utils.setImageFromUrl(getActivity(), sturl, imgpersonlogo);
        /**/
        /*Constant.AppPref.edit().putString("email", UM.Email)
                .putString("password", sp[sp.length - 2]).commit();*/
        /*txtPersonName.setText("Hi " + model.FirstName.trim());
        txtPersonEmail.setText("Log Off");*/
    }

    public void LoadAccountList() {
        if (isAdded()) {
            ColorDrawable sage = new ColorDrawable(getActivity().getResources().getColor(R.color.dark_gray));
            bottom_list.setDivider(sage);
            bottom_list.setDividerHeight(1);

            if (Constant.ISguest){
                FooterAdapter footerAdapter = new FooterAdapter(getActivity(), Constant.AccountList2);
                bottom_list.setAdapter(footerAdapter);
            }else {
                FooterAdapter footerAdapter = new FooterAdapter(getActivity(), Constant.AccountList);
                bottom_list.setAdapter(footerAdapter);
            }
        }
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.txtPersonEmail:
                if (Constant.AppPref.getString("email", "").isEmpty()) {
                    Login.StartLoginDialog("", getActivity());
                } else {
                    Login.onLogOff();
                }
                break;
            default:
                break;
        }
    }
}
