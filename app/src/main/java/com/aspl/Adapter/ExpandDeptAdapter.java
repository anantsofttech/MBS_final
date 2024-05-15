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

import org.apache.http.NameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.List;

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
//    SubDepartmentModel subDeptModel;

    public ExpandDeptAdapter(Context context, ArrayList<DepartmentModel> departmentlist) {
        this.context = context;
        this.departmentlist = departmentlist;
    }

    @Override
    public Object getChild(int listPosition, int expandedListPosition) {

        return this.departmentlist.get(listPosition).subDeptModelList
                .get(expandedListPosition);
    }

    @Override
    public long getChildId(int listPosition, int expandedListPosition) {
        return expandedListPosition;
    }

    @Override
    public View getChildView(final int listPosition, final int expandedListPosition,
                             boolean isLastChild, View convertView, ViewGroup parent) {
        SubDepartmentModel subDeptModel = departmentlist.get(listPosition).subDeptModelList
                .get(expandedListPosition);
        if (convertView == null) {
//           count= 0;
            LayoutInflater layoutInflater = (LayoutInflater) this.context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = layoutInflater.inflate(R.layout.list_item, null);
        }

//
//        TextView expandedListTextView = convertView
//                .findViewById(R.id.expandedListItem);
//
//        LinearLayout llmain = convertView.findViewById(R.id.llmain);
//        //Constant.themeModel.Backgroundcolor="#0fbf00";
//        Log.e("Log", "BC COLOR=" + Constant.themeModel.Backgroundcolor);
//        StateListDrawable sld = new StateListDrawable();
//        sld.addState(new int[]{android.R.attr.state_pressed}, new ColorDrawable(Color.GRAY));
//        sld.addState(new int[]{}, new ColorDrawable(Color.parseColor(Constant.themeModel.Backgroundcolor)));
//        //llmain.setBackground(sld);
////        llmain.setBackgroundColor(Color.WHITE);
//        llmain.setBackgroundColor(Color.parseColor(Constant.themeModel.Backgroundcolor));
//        expandedListTextView.setText(subDeptModel.getStyle());
//        expandedListTextView.setTextColor(Color.BLACK);
//        count++;
//        Log.d("exp:",""+count);


        //Starts ****************************************
//        if (convertView == null) {
//            LayoutInflater layoutInflater = (LayoutInflater) this.context
//                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
//            convertView = layoutInflater.inflate(R.layout.list_item, null);
//        }

//        GridView gv = (GridView) convertView;
//        gv.setAdapter(new ImageViewAdapter(Main2Activity.this, Child.get(groupPosition), ChildPicture.get(groupPosition))); //Changed
//        return convertView;

        // if(!Constant.issecondTime){
//        ArrayList<SubDepartmentModel> TempsubDeptModelList = new ArrayList<>(departmentlist.get(listPosition).subDeptModelList);

        Collections.sort(departmentlist.get(listPosition).subDeptModelList, new Comparator<SubDepartmentModel>() {

            /* This comparator will sort AppDetail objects alphabetically. */

            @Override
            public int compare(SubDepartmentModel a1, SubDepartmentModel a2) {

                // String implements Comparable
                return (a1.getStyle().toString()).compareTo(a2.getStyle().toString());
            }
        });

        GridView gridViewSubdept = convertView.findViewById(R.id.gridViewSubdept);
        gridViewSubdept.setAdapter(new GridviewSubDeptAdapter(context,subDeptModel,departmentlist.get(listPosition).subDeptModelList ,departmentlist.get(listPosition).dept_id,expandedListPosition));
//            count++;
//            Log.d("exp:",""+count);


        //Constant.issecondTime = true;
        // }
        gridViewSubdept.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v,
                                    int position, long id) {

                SubDepartmentModel subDeptModel = departmentlist.get(listPosition).subDeptModelList
                        .get(position);

//                https://ecomtestwcf.lightningpos.com/WebStoreRestService.svc/GetInventorys/707/2301/55955/0/0/0;0/1/12/department/price/Asc/0/0/0/0
                Constant.isBackFromDeparment = true;

                if (Constant.SCREEN_LAYOUT == 1) {
                    MainActivity.getInstance().loadViewAllFragment("department", String.valueOf(subDeptModel.getDeptid()), String.valueOf(subDeptModel.getStyleId()), "0", "0", "0", MainActivity.blockDisplayedText, "", "");
                } else if (Constant.SCREEN_LAYOUT == 2) {
                    MainActivityDup.getInstance().loadViewAllFragment("department", String.valueOf(subDeptModel.getDeptid()), String.valueOf(subDeptModel.getStyleId()), "0", "0", "0", MainActivityDup.blockDisplayedText, "", "OnlyDepartment");
                }

            }
        });

        // End ***********************************************


//        String groupname= departmentlist.get(listPosition).dept_desc;
//        recyclerView = (RecyclerView) convertView.findViewById(R.id.recyclerview);
//        InnerRecyclerViewAdapter sbc=new InnerRecyclerViewAdapter(context,
//                Constant.SubDepartmentList,listPosition,groupname);
//        recyclerView.setLayoutManager(new GridLayoutManager(context,2));
//        recyclerView.setAdapter(sbc);


//        TextView expandedListTextView = convertView
//                .findViewById(R.id.expandedListItem);
//
//        LinearLayout llmain = convertView.findViewById(R.id.llmain);
//        //Constant.themeModel.Backgroundcolor="#0fbf00";
//        Log.e("Log", "BC COLOR=" + Constant.themeModel.Backgroundcolor);
//        StateListDrawable sld = new StateListDrawable();
//        sld.addState(new int[]{android.R.attr.state_pressed}, new ColorDrawable(Color.GRAY));
//        sld.addState(new int[]{}, new ColorDrawable(Color.parseColor(Constant.themeModel.Backgroundcolor)));
//        //llmain.setBackground(sld);
////        llmain.setBackgroundColor(Color.WHITE);
//        llmain.setBackgroundColor(Color.parseColor(Constant.themeModel.Backgroundcolor));
//        expandedListTextView.setText(subDeptModel.getStyle());
//        expandedListTextView.setTextColor(Color.BLACK);
//
//
//        LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams)llmain.getLayoutParams();
//
//        Display display = ((WindowManager)context.getApplicationContext().getSystemService(Context.WINDOW_SERVICE)).getDefaultDisplay();
//        int width = display.getWidth()/2;
//        layoutParams.width = width;
//        llmain.setLayoutParams(layoutParams);

//        for(int i=0;i<2;i++){
//            LinearLayout l = new LinearLayout(context);
//            l.setOrientation(LinearLayout.HORIZONTAL);
//            for(int j=0;j<2;j++){
//                TextView tv = new TextView(context);
//                tv.setText(subDeptModel.getStyle());
//                ViewGroup.LayoutParams lp = new ViewGroup.LayoutParams(width, ViewGroup.LayoutParams.WRAP_CONTENT);
//                l.addView(tv,lp);
//            }
//            llmain.addView(l);
//        }

        /*GradientDrawable prevbgShape = (GradientDrawable)llmain.getBackground();
        prevbgShape.setColor(Color.parseColor(Constant.themeModel.Backgroundcolor));*/


//        convertView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//                SubDepartmentModel subDeptModel = departmentlist.get(listPosition).subDeptModelList
//                        .get(expandedListPosition);
//
////                https://ecomtestwcf.lightningpos.com/WebStoreRestService.svc/GetInventorys/707/2301/55955/0/0/0;0/1/12/department/price/Asc/0/0/0/0
//
//                if (Constant.SCREEN_LAYOUT == 1) {
//                    MainActivity.getInstance().loadViewAllFragment("department", String.valueOf(subDeptModel.getDeptid()), String.valueOf(subDeptModel.getStyleId()), "0", "0", "0", MainActivity.blockDisplayedText, "");
//                } else if (Constant.SCREEN_LAYOUT == 2) {
//                    MainActivityDup.getInstance().loadViewAllFragment("department", String.valueOf(subDeptModel.getDeptid()), String.valueOf(subDeptModel.getStyleId()), "0", "0", "0", MainActivityDup.blockDisplayedText, "");
//                }
////                if(Constant.SCREEN_LAYOUT==1){
////                    if (UserModel.Cust_mst_ID != null){
////                        /** For Customer ID **/
////                        MainActivity.getInstance().mContainer.removeAllViews();
////                        MainActivity.getInstance().LoadWebVew(Constant.URL + "/inventory/inventoryapp"
////                                + "?customerid=" + UserModel.Cust_mst_ID
////                                + "&sessionid=" + /*"0"*/DeviceInfo.getDeviceId(MainActivity.getInstance()) + "0011"
////                                + "&storeno=" + Constant.STOREID
////                                + "&deptid=" +subDeptModel.getDeptid()
////                                + "&subdeptid=" + subDeptModel.getStyleId());
////                        MainActivity.getInstance().getSupportFragmentManager().popBackStackImmediate();
////
////                        //Toast.makeText(context, "Url : " + Constant.URL + "/inventory/inventoryapp" + "?customerid=" + UserModel.Cust_mst_ID, Toast.LENGTH_LONG).show();
////                    }else {
////                        /** For Session ID **/
////
////                        MainActivity.getInstance().mContainer.removeAllViews();
////                        MainActivity.getInstance().LoadWebVew(Constant.URL + "/inventory/inventoryapp"
////                                + "?customerid=" + "0"
////                                + "&sessionid=" + DeviceInfo.getDeviceId(MainActivity.getInstance()) + "0011"
////                                + "&storeno=" + Constant.STOREID
////                                + "&deptid=" +subDeptModel.getDeptid()
////                                + "&subdeptid=" + subDeptModel.getStyleId());
////                        MainActivity.getInstance().getSupportFragmentManager().popBackStackImmediate();
////                    }
////
////                    //Toast.makeText(MainActivity.getInstance(), "Dept Id : " +subDeptModel.deptid, Toast.LENGTH_SHORT).show();
////                    MainActivity.subDepartment = String.valueOf(subDeptModel.getStyleId());
////                    MainActivity.getInstance().mContainer.setVisibility(View.VISIBLE);
////                    MainActivity.getInstance().llsearch.setVisibility(View.VISIBLE);
////                    MainActivity.getInstance().mContent.setVisibility(View.GONE);
////                    MainActivity.getInstance().mDrawer.closeDrawers();
////                }else if(Constant.SCREEN_LAYOUT==2) {
////                    if (UserModel.Cust_mst_ID != null){
////                        /** For Customer ID **/
////                        MainActivityDup.getInstance().mContainer.removeAllViews();
////                        MainActivityDup.getInstance().LoadWebVew(Constant.URL + "/inventory/inventoryapp"
////                                + "?customerid=" + UserModel.Cust_mst_ID
////                                + "&sessionid=" + /*"0"*/DeviceInfo.getDeviceId(MainActivityDup.getInstance()) + "0011"
////                                + "&storeno=" + Constant.STOREID
////                                + "&deptid=" +subDeptModel.getDeptid()
////                                + "&subdeptid=" + subDeptModel.getStyleId());
////                        MainActivityDup.getInstance().getSupportFragmentManager().popBackStackImmediate();
////                        //Toast.makeText(context, "Url : " + Constant.URL + "/inventory/inventoryapp" + "?customerid=" + UserModel.Cust_mst_ID, Toast.LENGTH_LONG).show();
////                    }else {
////                        /** For Session ID **/
////
////                        MainActivityDup.getInstance().mContainer.removeAllViews();
////                        MainActivityDup.getInstance().LoadWebVew(Constant.URL + "/inventory/inventoryapp"
////                                + "?customerid=" + "0"
////                                + "&sessionid=" + DeviceInfo.getDeviceId(MainActivityDup.getInstance()) + "0011"
////                                + "&storeno=" + Constant.STOREID
////                                + "&deptid=" +subDeptModel.getDeptid()
////                                + "&subdeptid=" + subDeptModel.getStyleId());
////                        MainActivityDup.getInstance().getSupportFragmentManager().popBackStackImmediate();
////                    }
////                    //Toast.makeText(MainActivity.getInstance(), "Dept Id : " +subDeptModel.deptid, Toast.LENGTH_SHORT).show();
////                    MainActivityDup.subDepartment = String.valueOf(subDeptModel.getStyleId());
////                    MainActivityDup.getInstance().mContainer.setVisibility(View.VISIBLE);
////                   // MainActivityDup.getInstance().llsearch.setVisibility(View.VISIBLE);
////                    MainActivityDup.getInstance().mContent.setVisibility(View.GONE);
////                }
////
////
//            }
//        });
        return convertView;
    }

    @Override
    public int getChildrenCount(int listPosition) {
//        return this.departmentlist.get(listPosition).subDeptModelList.size();
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
        LayoutInflater layoutInflater = (LayoutInflater) this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        convertView = layoutInflater.inflate(R.layout.list_group_department, null);
        listTitle = departmentlist.get(listPosition).dept_desc;
        TextView listTitleTextView = convertView.findViewById(R.id.listTitleDepartment);
        listTitleTextView.setTextColor(Color.parseColor(Constant.themeModel.ThemeColor));
        listTitleTextView.setText(listTitle);

//        DepartmentModel model = departmentlist.get(listPosition);

        ImageView iv_subDept = convertView.findViewById(R.id.iv_subDept);

        if (FilterFragment.getInstance().filterInfoModel != null) {
            if (FilterFragment.getInstance().filterInfoModel.getStyleDisplay().equals("Y")) {
                if(departmentlist.get(listPosition).subDeptModelList != null && departmentlist.get(listPosition).subDeptModelList.size() > 0){
//                    iv_subDept.setImageResource(R.drawable.ic_plus_dept);
                    Log.d("dept","id"+ departmentlist.get(listPosition).dept_id);
                    if(isExpanded){
                        iv_subDept.setImageResource(R.drawable.ic_minus_dept);
                    }else{
                        iv_subDept.setImageResource(R.drawable.ic_plus_dept);
                    }
                }else{
                    iv_subDept.setImageResource(0);
                }
            } else {
                iv_subDept.setImageResource(0);
            }
        }

//        LinearLayout llmain = convertView.findViewById(R.id.llmain);

//        StateListDrawable sld = new StateListDrawable();
//        sld.addState(new int[]{android.R.attr.state_pressed}, new ColorDrawable(Color.GRAY));
//        sld.addState(new int[]{}, new ColorDrawable(Color.parseColor(Constant.themeModel.Backgroundcolor)));
//        //llmain.setBackground(sld);
//        llmain.setBackgroundColor(Color.WHITE);
//        llmain.setBackground(ContextCompat.getDrawable(context, R.drawable.lightgrey_simple_border));
       /* if (expandableListDetail.get(listTitle).size() == 0) {
            listTitleTextView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Toast.makeText(context, "listTitle=" + listTitle, Toast.LENGTH_SHORT).show();
                }
            });
        }*/

//            iv_subDept.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//
//                    if(isExpanded){
//                        FilterFragment.getInstance().dept_expList.collapseGroup(listPosition);
//                        iv_subDept.setImageResource(R.drawable.ic_plus_dept);
//                    }else{
//                        FilterFragment.getInstance().dept_expList.expandGroup(listPosition);
//                        iv_subDept.setImageResource(R.drawable.ic_minus_dept);
//                    }
//                }
//            });


        listTitleTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DepartmentModel model = departmentlist.get(listPosition);

                if (model.dept_id == null || model.dept_id.isEmpty()) {
                    model.dept_id = "0";
                }

                Constant.isBackFromDeparment = true;

                if (Constant.SCREEN_LAYOUT == 1) {
                    MainActivity.getInstance().loadViewAllFragment("department", model.dept_id, "0", "0", "0", "0", MainActivity.blockDisplayedText, "", "OnlyDepartment");
                    MainActivity.deptId = model.dept_id;
                } else if (Constant.SCREEN_LAYOUT == 2) {
                    MainActivityDup.getInstance().loadViewAllFragment("department", model.dept_id, String.valueOf(0), "0", "0", "0", MainActivityDup.blockDisplayedText, "","OnlyDepartment");
                    MainActivityDup.deptId = model.dept_id;
                }

//                String Url=Constant.WS_BASE_URL+Constant.GET_STYLE+Constant.STOREID+"/"+model.dept_id;
                /*if( FilterFragment.getInstance().dept_expList.isGroupExpanded(listPosition)){
                    FilterFragment.getInstance().dept_expList.collapseGroup(listPosition);
                }else {
                    for(int i=0;i<departmentlist.size();i++){
                        FilterFragment.getInstance().dept_expList.collapseGroup(i);
                    }
                    FilterFragment.getInstance().dept_expList.expandGroup(listPosition);
                    //new Async_SubDept(context, Url, model, listPosition).execute();
                }*/
//                if(Constant.SCREEN_LAYOUT==1){
//                    if (UserModel.Cust_mst_ID != null){
//                        /** For Customer ID **/
//                        MainActivity.getInstance().mContainer.removeAllViews();
//                        MainActivity.getInstance().LoadWebVew(Constant.URL + "/inventory/inventoryapp"
//                                + "?customerid=" + UserModel.Cust_mst_ID
//                                + "&sessionid=" + /*"0"*/DeviceInfo.getDeviceId(MainActivity.getInstance()) + "0011"
//                                + "&storeno=" + Constant.STOREID
//                                +  "&deptid=" +model.dept_id);
//                        MainActivity.getInstance().getSupportFragmentManager().popBackStackImmediate();
//                        //Toast.makeText(context, "Url : " + Constant.URL + "/inventory/inventoryapp" + "?customerid=" + UserModel.Cust_mst_ID, Toast.LENGTH_LONG).show();
//                    }else {
//                        /** For Session ID **/
//
//                        MainActivity.getInstance().mContainer.removeAllViews();
//                        MainActivity.getInstance().LoadWebVew(Constant.URL + "/inventory/inventoryapp"
//                                + "?customerid=" + "0"
//                                + "&sessionid=" + DeviceInfo.getDeviceId(MainActivity.getInstance()) + "0011"
//                                + "&storeno=" + Constant.STOREID
//                                + "&deptid=" +model.dept_id
//                        );
//                        MainActivity.getInstance().getSupportFragmentManager().popBackStackImmediate();
//                    }
//
//                    //Toast.makeText(MainActivity.getInstance(), "Dept Id : " +subDeptModel.deptid, Toast.LENGTH_SHORT).show();
//                    MainActivity.deptId = model.dept_id;
//
//                    MainActivity.getInstance().mContainer.setVisibility(View.VISIBLE);
//                    MainActivity.getInstance().llsearch.setVisibility(View.VISIBLE);
//                    MainActivity.getInstance().mContent.setVisibility(View.GONE);
//                    MainActivity.getInstance().mDrawer.closeDrawers();
//                }else if(Constant.SCREEN_LAYOUT==2) {
//                    if (UserModel.Cust_mst_ID != null){
//                        /** For Customer ID **/
//                        MainActivityDup.getInstance().mContainer.removeAllViews();
//                        MainActivityDup.getInstance().LoadWebVew(Constant.URL + "/inventory/inventoryapp"
//                                + "?customerid=" + UserModel.Cust_mst_ID
//                                + "&sessionid=" + /*"0"*/DeviceInfo.getDeviceId(MainActivityDup.getInstance()) + "0011"
//                                + "&storeno=" + Constant.STOREID
//                                +  "&deptid=" +model.dept_id);
//                        MainActivityDup.getInstance().getSupportFragmentManager().popBackStackImmediate();
//                        //Toast.makeText(context, "Url : " + Constant.URL + "/inventory/inventoryapp" + "?customerid=" + UserModel.Cust_mst_ID, Toast.LENGTH_LONG).show();
//                    }else {
//                        /** For Session ID **/
//
//                        MainActivityDup.getInstance().mContainer.removeAllViews();
//                        MainActivityDup.getInstance().LoadWebVew(Constant.URL + "/inventory/inventoryapp"
//                                + "?customerid=" + "0"
//                                + "&sessionid=" + DeviceInfo.getDeviceId(MainActivityDup.getInstance()) + "0011"
//                                + "&storeno=" + Constant.STOREID
//                                + "&deptid=" +model.dept_id
//                        );
//                        MainActivityDup.getInstance().getSupportFragmentManager().popBackStackImmediate();
//                    }
//
//                    //Toast.makeText(MainActivity.getInstance(), "Dept Id : " +subDeptModel.deptid, Toast.LENGTH_SHORT).show();
//                    MainActivityDup.deptId = model.dept_id;
//
//                    MainActivityDup.getInstance().mContainer.setVisibility(View.VISIBLE);
//                    //MainActivityDup.getInstance().llsearch.setVisibility(View.VISIBLE);
//                    MainActivityDup.getInstance().mContent.setVisibility(View.GONE);
//                   // MainActivityDup.getInstance().mDrawer.closeDrawers();
//                }

            }
        });
        return convertView;
    }

    @Override
    public boolean hasStableIds () {
        return false;
    }

    @Override
    public boolean isChildSelectable ( int listPosition, int expandedListPosition){
        return true;
    }


    public class Async_SubDept extends AsyncTask<String, Void, Void> {

        List<NameValuePair> nameValuePairList;
        String response;
        public ProgressDialog progressBar;
//        String username, password;

        private Context mContext;
        //        public  SharedPreferences preferences;
        XML_JSONParser parser;
        String strURL;
        DepartmentModel model;
        int position;

        public Async_SubDept(Context context, String url, DepartmentModel model, int listPosition) {
            mContext = context;
            strURL = url;
            this.model = model;
            this.position = listPosition;
        }

        @Override
        protected Void doInBackground(String... params) {
            nameValuePairList = new ArrayList<NameValuePair>();
            parser = new XML_JSONParser();

            Log.e("Log", "url=" + strURL);
            response = parser.callJSonWebService(strURL);
            Log.d("TEST", "HERE RESPONSE FROM GCMLOGIN IS : " + response);

            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            // postexecute logic
            super.onPostExecute(result);

            if (response != null) {
                try {
                    JSONArray arr = new JSONArray(response);
                    model.subDeptModelList.clear();
                    for (int i = 0; i < arr.length(); i++) {
                        JSONObject obj = arr.getJSONObject(i);
                        SubDepartmentModel SDM = new SubDepartmentModel(obj);
                        model.subDeptModelList.add(SDM);
                    }
                    departmentlist.get(position).subDeptModelList = model.subDeptModelList;
                    notifyDataSetChanged();
                    FilterFragment.getInstance().dept_expList.expandGroup(position);
                    // MainActivity.getInstance().mManage_expList.expandGroup(position);
                    //onGroupExpanded(1);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
