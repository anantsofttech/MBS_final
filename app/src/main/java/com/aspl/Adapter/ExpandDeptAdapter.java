package com.aspl.Adapter;

import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Color;
import android.os.AsyncTask;
import androidx.recyclerview.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import com.aspl.Utils.Constant;
import com.aspl.Utils.XML_JSONParser;
import com.aspl.fragment.FilterFragment;
import com.aspl.mbs.MainActivity;
import com.aspl.mbs.MainActivityDup;
import com.aspl.mbs.R;
import com.aspl.mbsmodel.DepartmentModel;
import com.aspl.mbsmodel.MbsDataModel;
import com.aspl.mbsmodel.SubDepartmentModel;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import org.apache.http.NameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.List;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

/**
 * Created by new on 07/06/2017.
 */
public class ExpandDeptAdapter extends BaseExpandableListAdapter {

    private Context context;
    private List<String> expandableListTitle;
    private LinkedHashMap<String, List<MbsDataModel>> expandableListDetail;
    String listTitle;
    //    int count= 0;
    RecyclerView recyclerView;
    List<DepartmentModel> departmentlist;
    private String imgUrl = Constant.IMG_BASE + Constant.IMG_DEPARTMENT;
    private int lastExpandedPosition = -1; // Track the last expanded position
//    SubDepartmentModel subDeptModel;

    public ExpandDeptAdapter(Context context, ArrayList<DepartmentModel> departmentlist) {
        this.context = context;
        this.departmentlist = departmentlist;
    }

    @Override
    public Object getChild(int listPosition, int expandedListPosition) {
        return this.departmentlist.get(listPosition).subDeptModelList.get(expandedListPosition);
    }

    @Override
    public long getChildId(int listPosition, int expandedListPosition) {
        return expandedListPosition;
    }

    @Override
    public View getChildView(final int listPosition, final int expandedListPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        SubDepartmentModel subDeptModel = departmentlist.get(listPosition).subDeptModelList.get(expandedListPosition);
        if (convertView == null) {
            LayoutInflater layoutInflater = (LayoutInflater) this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = layoutInflater.inflate(R.layout.list_item, null);
        }

        GridView gridViewSubdept = convertView.findViewById(R.id.gridViewSubdept);
        gridViewSubdept.setAdapter(new GridviewSubDeptAdapter(context, subDeptModel, departmentlist.get(listPosition).subDeptModelList, departmentlist.get(listPosition).dept_id, expandedListPosition));

//        gridViewSubdept.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
//                SubDepartmentModel subDeptModel = departmentlist.get(listPosition).subDeptModelList.get(position);
//                Constant.isBackFromDeparment = true;
//                if (Constant.SCREEN_LAYOUT == 1) {
//                    MainActivity.getInstance().loadViewAllFragment("department", String.valueOf(subDeptModel.getDeptid()), String.valueOf(subDeptModel.getStyleId()), "0", "0", "0", MainActivity.blockDisplayedText, "", "");
//                } else if (Constant.SCREEN_LAYOUT == 2) {
//                    MainActivityDup.getInstance().loadViewAllFragment("department", String.valueOf(subDeptModel.getDeptid()), String.valueOf(subDeptModel.getStyleId()), "0", "0", "0", MainActivityDup.blockDisplayedText, "", "OnlyDepartment");
//                }
//            }
//        });
        return convertView;
    }

    @Override
    public int getChildrenCount(int listPosition) {
        return 1;
    }

    @Override
    public Object getGroup(int listPosition) {
        return departmentlist.get(listPosition);
    }

    @Override
    public int getGroupCount() {
        return departmentlist.size();
    }

    @Override
    public long getGroupId(int listPosition) {
        return listPosition;
    }

    @Override
    public View getGroupView(final int listPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        if (convertView == null) {
            LayoutInflater layoutInflater = (LayoutInflater) this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = layoutInflater.inflate(R.layout.list_group_department, null);
        }

        listTitle = departmentlist.get(listPosition).dept_desc;
        TextView listTitleTextView = convertView.findViewById(R.id.listTitleDepartment);
        listTitleTextView.setTextColor(Color.parseColor(Constant.themeModel.ThemeColor));
        listTitleTextView.setText(listTitle);

        ImageView iv_subDept = convertView.findViewById(R.id.iv_subDept);
        ImageView img_department = convertView.findViewById(R.id.img_department);

        if (departmentlist.get(listPosition).dept_img != null && !departmentlist.get(listPosition).dept_img.isEmpty()) {
            Glide.with(context)
                    .load(imgUrl + departmentlist.get(listPosition).dept_img)
                    .placeholder(R.drawable.progress_bar).placeholder(R.drawable.noimage)
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .into(img_department);
        }

        if (FilterFragment.getInstance().filterInfoModel != null) {
            if (FilterFragment.getInstance().filterInfoModel.getStyleDisplay().equals("Y")) {
                if (departmentlist.get(listPosition).subDeptModelList != null && departmentlist.get(listPosition).subDeptModelList.size() > 0) {
                    if (isExpanded) {
                        iv_subDept.setImageResource(R.drawable.baseline_keyboard_arrow_up_24);
                    } else {
                        iv_subDept.setImageResource(R.drawable.baseline_keyboard_arrow_down_24);
                    }
                } else {
                    iv_subDept.setImageResource(0);
                }
            } else {
                iv_subDept.setImageResource(0);
            }
        }

        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DepartmentModel model = departmentlist.get(listPosition);

                if (model.dept_id == null || model.dept_id.isEmpty()) {
                    model.dept_id = "0";
                }

                if (departmentlist.get(listPosition).subDeptModelList != null && departmentlist.get(listPosition).subDeptModelList.size() > 0) {
                    ExpandableListView expandableListView = (ExpandableListView) parent;
                    if (expandableListView.isGroupExpanded(listPosition)) {
                        expandableListView.collapseGroup(listPosition);
                        lastExpandedPosition = -1;
                    } else {
                        if (lastExpandedPosition != -1 && lastExpandedPosition != listPosition) {
                            expandableListView.collapseGroup(lastExpandedPosition);
                        }
                        expandableListView.expandGroup(listPosition);
                        lastExpandedPosition = listPosition;
                    }
                } else {
                    Constant.isBackFromDeparment = true;
                    if (Constant.SCREEN_LAYOUT == 1) {
                        MainActivity.getInstance().loadViewAllFragment("department", model.dept_id, "0", "0", "0", "0", MainActivity.blockDisplayedText, "", "OnlyDepartment");
                        MainActivity.deptId = model.dept_id;
                    } else if (Constant.SCREEN_LAYOUT == 2) {
                        MainActivityDup.getInstance().loadViewAllFragment("department", model.dept_id, String.valueOf(0), "0", "0", "0", MainActivityDup.blockDisplayedText, "", "OnlyDepartment");
                        MainActivityDup.deptId = model.dept_id;
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

    @Override
    public void notifyDataSetChanged() {
        super.notifyDataSetChanged();
        // Notify only the necessary views to refresh
    }
}