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
import android.widget.LinearLayout;
import android.widget.TextView;

import com.aspl.Utils.Constant;
import com.aspl.fragment.FilterFragment;
import com.aspl.mbs.R;
import com.aspl.mbsmodel.SubDepartmentModel;

import java.util.ArrayList;

public class GridviewSubDeptAdapter extends BaseAdapter {
    private Context context;
    private SubDepartmentModel subDeptModel;
    ArrayList<SubDepartmentModel> subDeptModelList;
    TextView expandedListTextView;
    //    ArrayList<String> finalSubDeptModelList = new ArrayList<>();
//    ArrayList<String> finalDeptModelList = new ArrayList<>();
    String deptId;
    LinearLayout llmain;
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
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View gridViewRow;

//        if (convertView == null) {

//            gridView = new View(context);
        // get layout from mobile.xml
        LayoutInflater layoutInflater = (LayoutInflater) this.context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        gridViewRow = layoutInflater.inflate(R.layout.row_gridview_subdepartment, null);

//            SubDepartmentModel subDepartmentModel = subDeptModelist.get(position);

        // set image based on selected text
//            TextView imageView = (TextView) gridView
//                    .findViewById(R.id.expandedListItem);

        expandedListTextView = gridViewRow
                .findViewById(R.id.expandedListItem);

        llmain = gridViewRow.findViewById(R.id.llmain);

        Log.e("Log", "BC COLOR=" + Constant.themeModel.Backgroundcolor);
        StateListDrawable sld = new StateListDrawable();
        sld.addState(new int[]{android.R.attr.state_pressed}, new ColorDrawable(Color.GRAY));
        sld.addState(new int[]{}, new ColorDrawable(Color.parseColor(Constant.themeModel.Backgroundcolor)));
        //llmain.setBackground(sld);
//        llmain.setBackgroundColor(Color.WHITE);
        llmain.setBackgroundColor(Color.parseColor(Constant.themeModel.Backgroundcolor));

//            expandedListTextView.setVisibility(View.VISIBLE);
//            expandedListTextView.setText(subDeptModelList.get(position).getStyle());
//            Constant.finalSubDeptModelList.add(subDeptModelList.get(position).getStyle());



        /*expandedListTextView.setVisibility(View.VISIBLE);
        expandedListTextView.setText(subDeptModel.getStyle());

*/

//        for(int i=0;i<subDeptModelList.size();i++){
//            if(!Constant.finalSubDeptModelList.contains(subDeptModelList.get(i).getStyle())) {
//                Constant.finalSubDeptModelList.add(subDeptModelList.get(i).getStyle());
//                expandedListTextView.setVisibility(View.VISIBLE);
//                expandedListTextView.setText(subDeptModelList.get(position).getStyle());
//            }
//        }

        expandedListTextView.setVisibility(View.VISIBLE);
        expandedListTextView.setText(subDeptModelList.get(position).getStyle());
        expandedListTextView.setTextColor(Color.BLACK);

//        if(subDeptModelList.size()>0 && subDeptModelList.size()==1){
//            expandedListTextView.setVisibility(View.VISIBLE);
//            expandedListTextView.setText(subDeptModelList.get(position).getStyle());
//        }else if(subDeptModelList.size()>1){
//
//            for(int i=0;i<subDeptModelList.size();i++){
//
//                expandedListTextView.setVisibility(View.VISIBLE);
//                expandedListTextView.setText(subDeptModelList.get(i).getStyle());
//
//            }
////            Constant.issecondTime = true;
//        }




//            if(!Constant.finalSubDeptModelList.contains(subDeptModelList.get(position).getStyle())) {

//                Constant.finalSubDeptModelList.add(subDeptModelList.get(position).getStyle());
//            }


//             if(Constant.finalSubDeptModelList != null && Constant.finalSubDeptModelList.size()>0) {

//            if(FilterFragment.getInstance().finalSubDeptModelList != null){
//
//                if (!FilterFragment.getInstance().finalSubDeptModelList.contains(subDeptModelList.get(position).getStyle())) {
//                    expandedListTextView.setVisibility(View.VISIBLE);
//                    expandedListTextView.setText(subDeptModelList.get(position).getStyle());
//                    FilterFragment.getInstance().finalSubDeptModelList.add(subDeptModelList.get(position).getStyle());
//                }
//
//            }

//                if (!Constant.finalSubDeptModelList.contains(subDeptModelList.get(position).getStyle())) {
//                    expandedListTextView.setVisibility(View.VISIBLE);
//                    Constant.finalSubDeptModelList.add(subDeptModelList.get(position).getStyle());
//                    expandedListTextView.setText(subDeptModelList.get(position).getStyle());
//                } else {
//                    expandedListTextView.setVisibility(View.GONE);
//                }
//            }else{
//                expandedListTextView.setVisibility(View.VISIBLE);
//                Constant.finalSubDeptModelList.add(subDeptModelList.get(position).getStyle());
//                expandedListTextView.setText(subDeptModelList.get(position).getStyle());
//            }

//            expandedListTextView.setTextColor(Color.BLACK);

//        } else {
//            gridViewRow = (View) convertView;
//        }

        return gridViewRow;
    }
}

