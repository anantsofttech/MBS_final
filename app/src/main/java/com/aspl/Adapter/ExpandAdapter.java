package com.aspl.Adapter;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.StateListDrawable;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.BaseExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.aspl.Utils.Constant;
import com.aspl.fragment.ProfileFragment_layout2;
import com.aspl.mbs.MainActivity;
import com.aspl.mbs.MainActivityDup;
import com.aspl.mbs.R;
import com.aspl.mbsmodel.MbsDataModel;
import com.aspl.mbsmodel.UserModel;

import java.util.LinkedHashMap;
import java.util.List;

/**
 * Created by new on 07/06/2017.
 */
public class ExpandAdapter extends BaseExpandableListAdapter {

    private Context context;
    private List<String> expandableListTitle;
    private LinkedHashMap<String, List<MbsDataModel>> expandableListDetail;
    String listTitle;
    int height;
   /* public ExpandAdapter(Context context, List<String> expandableListTitle,
                         LinkedHashMap<String, List<String>> expandableListDetail) {

        this.expandableListTitle = expandableListTitle;
        this.expandableListDetail = expandableListDetail;
    }*/

    public ExpandAdapter(Context context, LinkedHashMap<String, List<MbsDataModel>> expandableListDetail, List<String> titleList) {
        this.context = context;
        this.expandableListDetail = expandableListDetail;
        this.expandableListTitle = titleList;
    }

    public ExpandAdapter(){}

    @Override
    public Object getChild(int listPosition, int expandedListPosition) {

        return this.expandableListDetail.get(expandableListTitle.get(listPosition))
                .get(expandedListPosition);

    }

    @Override
    public long getChildId(int listPosition, int expandedListPosition) {
        return expandedListPosition;
    }

    @Override
    public View getChildView(final int listPosition, final int expandedListPosition,
                             boolean isLastChild, View convertView, ViewGroup parent) {
        MbsDataModel expandedListText = (MbsDataModel) getChild(listPosition, expandedListPosition);
        if (convertView == null) {
            LayoutInflater layoutInflater = (LayoutInflater) this.context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = layoutInflater.inflate(R.layout.list_item_account, null);
        }
        LinearLayout llmain = (LinearLayout) convertView.findViewById(R.id.llmain);
        /*StateListDrawable sld = new StateListDrawable();
        sld.addState(new int[] { android.R.attr.state_pressed }, new ColorDrawable(Color.WHITE));
        sld.addState(new int[] {}, new ColorDrawable(Color.WHITE));*/
        // llmain.setBackground(sld);

        llmain.setBackgroundColor(Color.WHITE);

        TextView expandedListTextView = (TextView) convertView
                .findViewById(R.id.expandedListItemAccount);
        //expandedListTextView.setTypeface(null, Typeface.NORMAL);
        expandedListTextView.setBackgroundColor(Color.WHITE);
        expandedListTextView.setTextColor(ContextCompat.getColor(context, R.color.Header));
        expandedListTextView.setText(expandedListText.PageName);
        Log.d("name", "getChildView: " + expandedListText.PageName);
        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listTitle = String.valueOf(getGroup(listPosition));
                MbsDataModel expandedListText = (MbsDataModel) getChild(listPosition, expandedListPosition);

//                Edited by Varun for Delete Account
                if(expandedListText.PageName.equalsIgnoreCase("Delete My Online Account")) {
                    if (Constant.SCREEN_LAYOUT == 1) {
                        MainActivity.getInstance().loadDeleteAccountWS();
                        MainActivity.getInstance().mDrawer.closeDrawers();
                    } else if (Constant.SCREEN_LAYOUT == 2) {
//                                MainActivityDup.getInstance().loadCardOnFileFragment();
                        MainActivityDup.getInstance().loadDeleteAccountWS();
                    }
                }
//                END

               else if(!expandedListText.PageContent.equalsIgnoreCase("null")) {
                    if (UserModel.Cust_mst_ID != null){
                        if (expandedListText.PageContent.contains("home/AndroidOrderHistory")){

                            if(Constant.SCREEN_LAYOUT==1){
                                MainActivity.getInstance().loadOrderHistoryFragment("Online Purchases");

//                                MainActivity.getInstance().LoadWebVew(expandedListText.PageContent +"?customerid=" + UserModel.Cust_mst_ID  + "&storeno=" + Constant.STOREID + "&type=online");
                            }
//                            else if(Constant.SCREEN_LAYOUT==2) {
//                                MainActivityDup.getInstance().showHomePage();
//                                MainActivityDup.getInstance().LoadWebVew(expandedListText.PageContent +"?customerid=" + UserModel.Cust_mst_ID  + "&storeno=" + Constant.STOREID + "&type=online");
//                            }

                        }else  if (expandedListText.PageContent.contains("home/AndroidShippingAddress")){

                            if(Constant.SCREEN_LAYOUT==1){
                                MainActivity.getInstance().loadShippingAddressFragment();
                            }
//                            else if(Constant.SCREEN_LAYOUT==2) {
//                                MainActivityDup.getInstance().showHomePage();
//                            }

                        }else if(expandedListText.PageName.equalsIgnoreCase("Manage Account")) {

                            if (Constant.SCREEN_LAYOUT == 1) {
                                MainActivity.getInstance().loadManageAccountFragment();
                            } else if (Constant.SCREEN_LAYOUT == 2) {
//                                MainActivityDup.getInstance().loadCardOnFileFragment();
                            }

                        }else if(expandedListText.PageName.equals("Card On File")) {

                            if (Constant.SCREEN_LAYOUT == 1) {
                                MainActivity.getInstance().loadCardOnFileFragment();
                            } else if (Constant.SCREEN_LAYOUT == 2) {
                                MainActivityDup.getInstance().loadCardOnFileFragment();
                            }

                        }else if(expandedListText.PageContent.contains("AndroidLoyaltyReward")){
                                if (Constant.SCREEN_LAYOUT == 1) {
                                    MainActivity.getInstance().loadRewardFragment();
                                } else if (Constant.SCREEN_LAYOUT == 2) {
                                    MainActivityDup.getInstance().loadRewardFragment();
                                }
                        }else if(expandedListText.PageContent.contains("AndroidChangePassword")) {

                            if (Constant.SCREEN_LAYOUT == 1) {
                                MainActivity.getInstance().loadChangePasswordFragment();

                            }else if(Constant.SCREEN_LAYOUT==2) {
                                MainActivityDup.getInstance().showHomePage();
                                MainActivityDup.getInstance().LoadWebVew(expandedListText.PageContent + "?customerid=" + UserModel.Cust_mst_ID + "&storeno=" + Constant.STOREID);
                            }
                        } else{

                            if (Constant.SCREEN_LAYOUT == 1) {
                                MainActivity.getInstance().showHomePage();
                                MainActivity.getInstance().LoadWebVew(expandedListText.PageContent +"?customerid=" + UserModel.Cust_mst_ID + "&storeno=" + Constant.STOREID);

                            }else if(Constant.SCREEN_LAYOUT==2) {
                                MainActivityDup.getInstance().showHomePage();
                                MainActivityDup.getInstance().LoadWebVew(expandedListText.PageContent +"?customerid=" + UserModel.Cust_mst_ID + "&storeno=" + Constant.STOREID);
                            }
                        }

                        if(Constant.SCREEN_LAYOUT==1) {
                            MainActivity.getInstance().mDrawer.closeDrawers();
                        }
                        //189013707
                    }

                }

                if(Constant.SCREEN_LAYOUT==1){
                    if(MainActivity.getInstance().mManage_expList.isGroupExpanded(listPosition)){
                        ViewGroup.LayoutParams params = MainActivity.getInstance().mManage_expList.getLayoutParams();
                        params.height = height;
                        MainActivity.getInstance().mManage_expList.setLayoutParams(params);
                        MainActivity.getInstance().mManage_expList.collapseGroup(listPosition);
                    }else{
                        ViewGroup.LayoutParams params = MainActivity.getInstance().mManage_expList.getLayoutParams();
                        params.height = (int) (height * 6.8);
                        MainActivity.getInstance().mManage_expList.setLayoutParams(params);
                        MainActivity.getInstance().mManage_expList.expandGroup(listPosition);
                    }
                }else if(Constant.SCREEN_LAYOUT==2) {
                    if(ProfileFragment_layout2.getInstance().mManage_expList.isGroupExpanded(listPosition)){
                        ViewGroup.LayoutParams params = ProfileFragment_layout2.getInstance().mManage_expList.getLayoutParams();
                        params.height = height;
                        ProfileFragment_layout2.getInstance().mManage_expList.setLayoutParams(params);
                        ProfileFragment_layout2.getInstance().mManage_expList.collapseGroup(listPosition);
                    }else{
                        ViewGroup.LayoutParams params = ProfileFragment_layout2.getInstance().mManage_expList.getLayoutParams();
                        params.height = (int) (height * 6.8);
                        ProfileFragment_layout2.getInstance().mManage_expList.setLayoutParams(params);
                        ProfileFragment_layout2.getInstance().mManage_expList.expandGroup(listPosition);
                    }
                }
            }
        });
        return convertView;
    }

    @Override
    public int getChildrenCount(int listPosition) {
        return /*this.expandableListDetail.size();*/ this.expandableListDetail.get(expandableListTitle.get(listPosition)).size();
    }

    @Override
    public Object getGroup(int listPosition) {
        if(expandableListTitle.size()>0){
            return expandableListTitle.get(listPosition);
        }else{
            return 0;
        }

    }

    @Override
    public int getGroupCount() {
        return expandableListDetail.size();
    }

    @Override
    public long getGroupId(int listPosition) {
        return listPosition;
    }

    @Override
    public View getGroupView(final int listPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        LayoutInflater layoutInflater = (LayoutInflater) this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        convertView = layoutInflater.inflate(R.layout.list_group, null);
        final LinearLayout llmain = (LinearLayout) convertView.findViewById(R.id.llmain);
        StateListDrawable sld = new StateListDrawable();
        /*sld.addState(new int[] { android.R.attr.state_pressed }, new ColorDrawable(Color.WHITE));
        sld.addState(new int[] {}, new ColorDrawable(Color.WHITE));*/
        llmain.setBackgroundColor(Color.WHITE);
        listTitle = String.valueOf(getGroup(listPosition));
        TextView listTitleTextView = (TextView) convertView.findViewById(R.id.listTitle);
        //listTitleTextView.setTypeface(null, Typeface.NORMAL);
        listTitleTextView.setTextColor(ContextCompat.getColor(context, R.color.Header));
        listTitleTextView.setBackgroundColor(Color.WHITE);
        Log.e("Log","expandableListDetail.get(listTitle)="+expandableListDetail.get(listTitle).size());
        if (expandableListDetail.get(listTitle).size() == 0) {
            listTitleTextView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Toast.makeText(context, "listTitle=" + listTitle, Toast.LENGTH_SHORT).show();
                }
            });
        }
        listTitleTextView.setText(listTitle);
        llmain.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                llmain.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                height = llmain.getHeight();
                Log.e("height of llmain","llmain:"+height);
            }
        });
        /*convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.e("Log","expandableListDetail.get(listTitle)="+expandableListDetail.get(listTitle).size());
                MainActivity.getInstance().mManage_expList.expandGroup(listPosition);
            }
        });*/
        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(Constant.SCREEN_LAYOUT==1){
                    if(MainActivity.getInstance().mManage_expList.isGroupExpanded(listPosition)){
                        ViewGroup.LayoutParams params = MainActivity.getInstance().mManage_expList.getLayoutParams();
                        params.height = height;
                        MainActivity.getInstance().mManage_expList.setLayoutParams(params);
                        MainActivity.getInstance().mManage_expList.collapseGroup(listPosition);
                    }else{
                        ViewGroup.LayoutParams params = MainActivity.getInstance().mManage_expList.getLayoutParams();
//                        Edited by Varun for guest login
                        if(Constant.ISguest){
                            params.height = (int) (height * 2.8);
                        }else {
                            params.height = (int) (height * 7.8);
                        }
//                        END
//                        params.height = (int) (height * 6.8);
                        MainActivity.getInstance().mManage_expList.setLayoutParams(params);
                        MainActivity.getInstance().mManage_expList.expandGroup(listPosition);
                    }
                }else if(Constant.SCREEN_LAYOUT==2) {
                    if(ProfileFragment_layout2.getInstance().mManage_expList.isGroupExpanded(listPosition)){
                        ViewGroup.LayoutParams params = ProfileFragment_layout2.getInstance().mManage_expList.getLayoutParams();
                        params.height = height;
                        ProfileFragment_layout2.getInstance().mManage_expList.setLayoutParams(params);
                        ProfileFragment_layout2.getInstance().mManage_expList.collapseGroup(listPosition);
                    }else{
                        ViewGroup.LayoutParams params = ProfileFragment_layout2.getInstance().mManage_expList.getLayoutParams();
//                        Edited by Varun for guest login
                        if(Constant.ISguest){
                            params.height = (int) (height * 1.8);
                        }else {
                            params.height = (int) (height * 6.8);
                        }
//                        END
//                        params.height = (int) (height * 6.8);
                        ProfileFragment_layout2.getInstance().mManage_expList.setLayoutParams(params);
                        ProfileFragment_layout2.getInstance().mManage_expList.expandGroup(listPosition);
                    }
                }

            }
        });
        return convertView;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public boolean isChildSelectable(int listPosition, int expandedListPosition) {
        return true;
    }

}
