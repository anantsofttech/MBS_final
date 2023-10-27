package com.aspl.Adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.aspl.Utils.Constant;
import com.aspl.mbs.MainActivity;
import com.aspl.mbs.MainActivityDup;
import com.aspl.mbs.R;
import com.aspl.mbsmodel.SubDeptModel;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

/**
 * Created by new on 07/06/2017.
 */
public class ExpandFilterAdapter extends BaseExpandableListAdapter {

    private Context context;
    private List<String> expandableListTitle;
    private LinkedHashMap<String, List<SubDeptModel>> lhsfilter_list;
    String listTitle;
    ImageView imggrpind;
   /* public ExpandAdapter(Context context, List<String> expandableListTitle,
                         LinkedHashMap<String, List<String>> expandableListDetail) {

        this.expandableListTitle = expandableListTitle;
        this.expandableListDetail = expandableListDetail;
    }*/

   /* public ExpandFilterAdapter(Context context, LinkedHashMap<String, List<SubDeptModel>> expandableListDetail, List<String> titleList) {
        this.context = context;
        this.expandableListDetail = expandableListDetail;
        this.expandableListTitle=titleList;
    }*/

    public ExpandFilterAdapter(Context context, LinkedHashMap<String, List<SubDeptModel>> lhsfilter_list, ArrayList<String> titleList) {
        this.context = context;
        this.lhsfilter_list=lhsfilter_list;
        expandableListTitle=titleList;
    }

    @Override
    public Object getChild(int listPosition, int expandedListPosition) {

        return this.lhsfilter_list.get(expandableListTitle.get(listPosition))
                .get(expandedListPosition);

    }

    @Override
    public void onGroupExpanded(int groupPosition) {
        super.onGroupExpanded(groupPosition);


    }

    @Override
    public void onGroupCollapsed(int groupPosition) {
        super.onGroupCollapsed(groupPosition);
    }

    @Override
    public long getChildId(int listPosition, int expandedListPosition) {
        return expandedListPosition;
    }

    @Override
    public View getChildView(final int listPosition, final int expandedListPosition,
                             boolean isLastChild, View convertView, ViewGroup parent) {
        final SubDeptModel expandedListText = (SubDeptModel) getChild(listPosition, expandedListPosition);
        if (convertView == null) {
            LayoutInflater layoutInflater = (LayoutInflater) this.context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = layoutInflater.inflate(R.layout.list_item, null);
        }
        TextView expandedListTextView = (TextView) convertView
                .findViewById(R.id.expandedListItem);
        expandedListTextView.setText(expandedListText.SubDept_Name);
        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SubDeptModel expandedListText = (SubDeptModel) getChild(listPosition, expandedListPosition);
                if(Constant.SCREEN_LAYOUT==1){
                    MainActivity.getInstance().LoadWebVew(expandedListText.SubDept_Url);
                    MainActivity.getInstance().mDrawer.closeDrawers();
                }else if(Constant.SCREEN_LAYOUT==2) {
                    MainActivityDup.getInstance().LoadWebVew(expandedListText.SubDept_Url);
                    //MainActivity.getInstance().mDrawer.closeDrawers();
                }

            }
        });
        return convertView;
    }

    @Override
    public int getChildrenCount(int listPosition) {
        return this.lhsfilter_list.get(expandableListTitle.get(listPosition))
                .size();
    }

    @Override
    public Object getGroup(int listPosition) {
        return expandableListTitle.get(listPosition);
    }

    @Override
    public int getGroupCount() {
        return expandableListTitle.size();
    }

    @Override
    public long getGroupId(int listPosition) {
        return listPosition;
    }

    @Override
    public View getGroupView(final int listPosition, boolean isExpanded,View convertView, ViewGroup parent) {
        LayoutInflater layoutInflater = (LayoutInflater) this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        convertView = layoutInflater.inflate(R.layout.list_group, null);
        listTitle = (String) getGroup(listPosition);
        TextView listTitleTextView = (TextView) convertView.findViewById(R.id.listTitle);
        imggrpind= (ImageView) convertView.findViewById(R.id.imggrpind);
        if (lhsfilter_list.get(listTitle).size() == 0) {
            listTitleTextView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Toast.makeText(context, "listTitle=" + listTitle, Toast.LENGTH_SHORT).show();
                }
            });
        }
        listTitleTextView.setText(listTitle);
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
