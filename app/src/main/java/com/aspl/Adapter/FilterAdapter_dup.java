package com.aspl.Adapter;

import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.aspl.Utils.Constant;
import com.aspl.Utils.Utils;
import com.aspl.mbs.MainActivityDup;
import com.aspl.mbs.R;
import com.aspl.mbsmodel.Filter;
import com.aspl.mbsmodel.FilterHomePage;
import com.aspl.mbsmodel.FilterModel;
import com.aspl.mbsmodel.LstDepartment;
import com.aspl.mbsmodel.LstSize;
import com.aspl.mbsmodel.LstStyle;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by new on 07/17/2017.
 */
public class FilterAdapter_dup extends BaseAdapter implements FilterItemAdapter_dup.FilterAdapterEvent {
    Context context;
    /*public static */List<FilterModel> filter;
    List<Filter> liFilter;
    List<LstDepartment> liDeparrtment;
    List<LstStyle> liStyle;
    List<LstSize> liSize;
    FilterItemAdapter_dup filterItemAdapter;
    List<FilterHomePage> liFilterHomePages;
    View view;
    LinearLayout llMain;

    public FilterAdapter_dup(Context context, ArrayList<FilterModel> filter, List<Filter> liFilter, List<FilterHomePage> liFilterHomePages) {
        this.context = context;
        this.filter = filter;
        this.liFilter = liFilter;
        this.liFilterHomePages = liFilterHomePages;
        //liDeparrtment = liFilter.get(0).getLstDepartment();
        //liStyle = liFilter.get(0).getLstStyle();
        //liSize = liFilter.get(0).getLstSize();
        //liSize = liFilter.get(0).getLstSpecialOffer();
    }

    @Override
    public int getCount() {
        return filter.size();
    }

    @Override
    public Object getItem(int i) {
        return getItem(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(final int position, View view, ViewGroup viewGroup) {
        this.view = view;
        if (view == null) {
            LayoutInflater layoutInflater = (LayoutInflater) this.context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = layoutInflater.inflate(R.layout.rawfilter, null);
        }
        final FilterModel filterModel = filter.get(position);

        // isActivated(0);
        final TextView listTitle = view
                .findViewById(R.id.listTitle);
        listTitle.setText(filterModel.Name);
        int filtDept=0;
        
            filtDept= MainActivityDup.filterDepartmentPosition;
        if (filterModel.isActive || position ==filtDept) {
            listTitle.setBackgroundColor(Color.GRAY);
            listTitle.setTextColor(Color.WHITE);
        } else {
            listTitle.setBackgroundColor(Color.parseColor(Constant.themeModel.ThemeColor));
            listTitle.setTextColor(Color.WHITE);
        }
        llMain = view.findViewById(R.id.llMain);

        llMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (position == 0) {
                    isActivated(position);
                    Utils.hideFilterKeyboard();
                    MainActivityDup.filterDepartmentPosition = 0;
                    MainActivityDup.getInstance().filterItemAdapter_dup = new FilterItemAdapter_dup(FilterAdapter_dup.this, liFilterHomePages, liFilter, 0);
                } else if (position == 1) {
                    isActivated(position);
                    Utils.hideFilterKeyboard();
                    MainActivityDup.filterDepartmentPosition = 1;
                    if (liFilter.get(0).getLstDepartment() != null) {
                        MainActivityDup.getInstance().filterItemAdapter_dup = new FilterItemAdapter_dup(FilterAdapter_dup.this, liFilterHomePages, liFilter, 1);
                    } else {
                        MainActivityDup.getInstance().filterItemAdapter_dup = new FilterItemAdapter_dup(FilterAdapter_dup.this, liFilterHomePages, liFilter, 1);
                    }
                } else if (position == 2) {
                    //isActivated();
                    isActivated(position);
                    Utils.hideFilterKeyboard();
                    MainActivityDup.filterDepartmentPosition = 2;
                    if (liFilter.get(0).getLstStyle() != null) {
                        //MainActivityDup.getInstance().filterChoiceAdapter = new FilterChoiceAdapter(MainActivityDup.getInstance(), liFilter, liFilter.get(0).getLstStyle().size(), 1);
                        //FilterAdapter.this.selectedSubDepartment(MainActivityDup.deptId,"0");
                        //MainActivityDup.callFilter(MainActivityDup.deptId, "0", "0");
                        MainActivityDup.getInstance().filterItemAdapter_dup = new FilterItemAdapter_dup(FilterAdapter_dup.this, liFilterHomePages, liFilter, 2);
                    } else {
                        MainActivityDup.getInstance().filterItemAdapter_dup = new FilterItemAdapter_dup(FilterAdapter_dup.this, liFilterHomePages, liFilter, 2);
                    }
                } else if (position == 3) {
                    //isActivated();
                    isActivated(position);
                    Utils.hideFilterKeyboard();
                    MainActivityDup.filterDepartmentPosition = 3;
                    if (liFilter.get(0).getLstSize() != null) {
                        MainActivityDup.getInstance().filterItemAdapter_dup = new FilterItemAdapter_dup(FilterAdapter_dup.this, liFilterHomePages, liFilter, 3);
                        //MainActivityDup.getInstance().filterChoiceAdapter = new FilterChoiceAdapter(MainActivityDup.getInstance(), liFilter, liFilter.get(0).getLstSize().size(), 2);
                    } else {
                        MainActivityDup.getInstance().filterItemAdapter_dup = new FilterItemAdapter_dup(FilterAdapter_dup.this, liFilterHomePages, liFilter, 3);
                        //MainActivityDup.getInstance().filterChoiceAdapter = new FilterChoiceAdapter(MainActivityDup.getInstance(), liFilter, 0, 2);
                    }
                    //MainActivityDup.getInstance().filterItemAdapter = new FilterItemAdapter(FilterAdapter.this, liFilter, 2);
                } else if (position == 4) {
                    MainActivityDup.filterDepartmentPosition = 4;
                    isActivated(position);
                    Utils.hideFilterKeyboard();
                    MainActivityDup.getInstance().filterItemAdapter_dup = new FilterItemAdapter_dup(FilterAdapter_dup.this, liFilterHomePages, liFilter, 4);
                } else if (position == 5) {
                    MainActivityDup.filterDepartmentPosition = 5;
                    isActivated(position);
                    MainActivityDup.getInstance().filterItemAdapter_dup = new FilterItemAdapter_dup(FilterAdapter_dup.this, liFilterHomePages, liFilter, 5);
                }
                //MainActivityDup.getInstance().filteCategoryOption.setAdapter(MainActivityDup.getInstance().filterChoiceAdapter);
                //MainActivityDup.getInstance().filterChoiceAdapter.notifyDataSetChanged();
                MainActivityDup.getInstance().rvDeptList.setAdapter(MainActivityDup.getInstance().filterItemAdapter_dup);
                MainActivityDup.getInstance().filterItemAdapter_dup.notifyDataSetChanged();

                /*StateListDrawable sld = new StateListDrawable();
                sld.addState(new int[]{android.R.attr.state_pressed}, new ColorDrawable(Color.GRAY));
                sld.addState(new int[]{android.R.attr.state_activated}, new ColorDrawable(Color.WHITE));
                *//*sld.addState(new int[]{}, new ColorDrawable(Color.WHITE));*//*
                view.setBackground(sld);*/

                //FilterModel filterModel = filter.get(position);

                //filterModel.isActive=true;

            }
        });
        return view;
    }

    @Override
    public void selectedDepartment(String deptId) {
        //MainActivityDup.filterAdapter.notifyDataSetChanged();
        //MainActivityDup.getInstance().filterItemAdapter.notifyDataSetChanged();
       /* MainActivityDup.callFilter(deptId,"0","0");*/
    }

    @Override
    public void selectedSubDepartment(String deptId, String subDeptId) {
        Log.e("subdept", "sub dept id : " + subDeptId);
        MainActivityDup.callFilter(deptId, subDeptId, "0");
    }

    @Override
    public void selectedPrice(String deptId, String subDeptId, String selectedPrice) {

    }

    @Override
    public void selectedSize(String deptId, String subDeptId, String size) {

    }

    public /*static*/ void isActivated(int i) {
        for (int j = 0; j < filter.size(); j++) {
            if (j == i) {
                filter.get(j).isActive = true;
            } else {
                filter.get(j).isActive = false;
            }
        }
        /*MainActivityDup.getInstance().filterItemAdapter.*/notifyDataSetChanged();
        //MainActivityDup.getInstance().filterItemAdapter.notifyDataSetChanged();
    }
}
