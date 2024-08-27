package com.aspl.Adapter;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.StateListDrawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.core.content.ContextCompat;

import com.aspl.Utils.BSTheme;
import com.aspl.Utils.Constant;
import com.aspl.mbs.MainActivity;
import com.aspl.mbs.MainActivityDup;
import com.aspl.mbs.R;
import com.aspl.mbsmodel.SubDepartmentModel;

import java.util.ArrayList;

public class GridviewSubDeptAdapter extends BaseAdapter {
    private Context context;
    private SubDepartmentModel subDeptModel;
    ArrayList<SubDepartmentModel> subDeptModelList;
    String deptId;
    int expandedListPosition;

    public GridviewSubDeptAdapter(Context context, SubDepartmentModel subDeptModel, ArrayList<SubDepartmentModel> subDeptModelList, String dept_id, int expandedListPosition) {
        this.context = context;
        this.subDeptModel = subDeptModel;
        this.subDeptModelList = subDeptModelList;
        deptId = dept_id;
        this.expandedListPosition = expandedListPosition;
    }

    @Override
    public int getCount() {
        return subDeptModelList.size();
    }

    @Override
    public Object getItem(int position) {
        return subDeptModelList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.row_gridview_subdepartment, null);
        }

        CheckBox expandedListItemCheckbox = convertView.findViewById(R.id.expandedListItemCheckbox);
        TextView expandedListItemTextView = convertView.findViewById(R.id.expandedListItem);
        LinearLayout llmain = convertView.findViewById(R.id.llmain);

        StateListDrawable sld = new StateListDrawable();
        sld.addState(new int[]{android.R.attr.state_pressed}, new ColorDrawable(Color.GRAY));
        sld.addState(new int[]{}, new ColorDrawable(Color.parseColor(Constant.themeModel.Backgroundcolor)));

        llmain.setBackgroundColor(Color.parseColor(Constant.themeModel.Backgroundcolor));

        expandedListItemCheckbox.setText(subDeptModelList.get(position).getStyle());
        BSTheme.setCheckBoxColor(expandedListItemCheckbox, Color.parseColor(Constant.themeModel.ThemeColor), Color.GRAY);
        expandedListItemCheckbox.setTextColor(Color.parseColor(Constant.themeModel.ThemeColor));

        expandedListItemTextView.setText(subDeptModelList.get(position).getStyle());
        expandedListItemTextView.setTextColor(Color.parseColor(Constant.themeModel.ThemeColor));

        expandedListItemCheckbox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    // Pass the checkbox value
                    String checkboxValue = subDeptModelList.get(position).getStyle();
                    // Handle the checkbox value
                    Log.d("Checkbox Value", checkboxValue);

                    Constant.isBackFromDeparment = true;
                    if (Constant.SCREEN_LAYOUT == 1) {
                        MainActivity.getInstance().loadViewAllFragment("department", String.valueOf(subDeptModelList.get(position).getDeptid()), String.valueOf(subDeptModelList.get(position).getStyleId()), "0", "0", "0", MainActivity.blockDisplayedText, "", "");
                    } else if (Constant.SCREEN_LAYOUT == 2) {
                        MainActivityDup.getInstance().loadViewAllFragment("department", String.valueOf(subDeptModelList.get(position).getDeptid()), String.valueOf(subDeptModelList.get(position).getStyleId()), "0", "0", "0", MainActivityDup.blockDisplayedText, "", "OnlyDepartment");
                    }

                }
            }
        });

        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Pass the checkbox value
                String checkboxValue = subDeptModelList.get(position).getStyle();
                // Handle the checkbox value
                Log.d("Checkbox Value", checkboxValue);

                Constant.isBackFromDeparment = true;
                if (Constant.SCREEN_LAYOUT == 1) {
                    MainActivity.getInstance().loadViewAllFragment("department", String.valueOf(subDeptModelList.get(position).getDeptid()), String.valueOf(subDeptModelList.get(position).getStyleId()), "0", "0", "0", MainActivity.blockDisplayedText, "", "");
                } else if (Constant.SCREEN_LAYOUT == 2) {
                    MainActivityDup.getInstance().loadViewAllFragment("department", String.valueOf(subDeptModelList.get(position).getDeptid()), String.valueOf(subDeptModelList.get(position).getStyleId()), "0", "0", "0", MainActivityDup.blockDisplayedText, "", "OnlyDepartment");
                }
            }
        });

        return convertView;
    }
}
