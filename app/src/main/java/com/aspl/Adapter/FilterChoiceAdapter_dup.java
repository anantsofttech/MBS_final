package com.aspl.Adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;

import com.aspl.Utils.BSTheme;
import com.aspl.Utils.Constant;
import com.aspl.mbs.MainActivity;
import com.aspl.mbs.R;
import com.aspl.mbsmodel.Filter;
import com.aspl.mbsmodel.LstDepartment;
import com.aspl.mbsmodel.LstSize;
import com.aspl.mbsmodel.LstStyle;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by new on 07/17/2017.
 */
public class FilterChoiceAdapter_dup extends BaseAdapter {
    Context context;
    List<Filter> liFilter;
    int size;
    int type;

    List<LstDepartment> liDepartment = new ArrayList<>();
    List<LstStyle> liStyle;
    List<LstSize> liSize;
    boolean isFirst = true;
    int tempPosition = -1;

    public FilterChoiceAdapter_dup(Context context, List<Filter> liFilter, int size, int type) {
        this.context = context;
        this.liFilter = liFilter;
        this.size = size;
        this.type = type;
        liDepartment = liFilter.get(0).getLstDepartment();
        liStyle = liFilter.get(0).getLstStyle();
        liSize = liFilter.get(0).getLstSize();
    }

    @Override
    public int getCount() {
        return size;
    }

    public int getSize() {
        int size = 0;
        if (type == 0) {
            if (liFilter.get(0).getLstDepartment() != null)
                size = liDepartment.size();
        } else if (type == 1) {
            if (liFilter.get(0).getLstStyle() != null)
                size = liStyle.size();
        } else if (type == 2) {
            if (liFilter.get(0).getLstSize() != null)
                size = liSize.size();
        }
        return size;
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
        if (view == null) {
            LayoutInflater layoutInflater = (LayoutInflater) this.context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = layoutInflater.inflate(R.layout.raw_checkbox_filter, null);
        }
        final CheckBox listTitle = (CheckBox) view
                .findViewById(R.id.chkOption);
        listTitle.setTag(position);

        //theme related set
        BSTheme.setCheckBoxColor(listTitle, Color.parseColor(Constant.themeModel.ThemeColor), Color.GRAY);

        if (type == 0) {

            if (liDepartment.get(position).getChecked()) {
                listTitle.setChecked(true);
            } else {
                //if (Integer.parseInt(MainActivity.deptId)  == liDepartment.get(position).getDeptId())
                  //  listTitle.setChecked(true);
                //else
                    listTitle.setChecked(false);
            }
            listTitle.setText(liDepartment.get(position).getDeptDesc() + " (" + liDepartment.get(position).getInvCount().toString() + ")");
        }
        if (type == 1) {
            if (liStyle.get(position).getChecked()) {
                listTitle.setChecked(true);
            } else {
                listTitle.setChecked(false);
            }
            listTitle.setText(liStyle.get(position).getStyle() + " (" + liStyle.get(position).getStyleCount().toString() + ")");
        }
        if (type == 2) {
            if (liSize.get(position).getChecked()) {
                listTitle.setChecked(true);
            } else {
                listTitle.setChecked(false);
            }
            listTitle.setText(liSize.get(position).getSizeName() + " (" + liSize.get(position).getSizeCount().toString() + ")");
        }
        listTitle.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (type == 0) {
                    int poss = (int) compoundButton.getTag();
                    /*for (int i = 0; i < liDepartment.size(); i++) {
                        liDepartment.get(i).setChecked(false);
                    }*/

                    if (tempPosition >= 0){
                        liDepartment.get(tempPosition).setChecked(false);
                    }
                    //notifyDataSetChanged();
                    MainActivity.deptId = String.valueOf(liDepartment.get(position).getDeptId());
                    liDepartment.get(position).setChecked(true);
                    tempPosition = position;
                    notifyDataSetChanged();
                }
                if (type == 1) {
                    int pos = (int) compoundButton.getTag();
                    if (compoundButton.isChecked())
                        liStyle.get(pos).setChecked(true);
                    else
                        liStyle.get(pos).setChecked(false);
                    notifyDataSetChanged();
                }
                if (type == 2) {
                    int pos = (int) compoundButton.getTag();
                    if (compoundButton.isChecked())
                        liSize.get(pos).setChecked(true);
                    else
                        liSize.get(pos).setChecked(false);
                   notifyDataSetChanged();
                }
            }
        });
        notifyDataSetChanged();
        return view;
    }
}
