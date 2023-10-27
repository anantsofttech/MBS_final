package com.aspl.fragment;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.aspl.Adapter.ExpandDeptAdapter;
import com.aspl.Adapter.GridviewSubDeptAdapter;
import com.aspl.Utils.Constant;
import com.aspl.Utils.Utils;
import com.aspl.mbs.MainActivity;
import com.aspl.mbs.MainActivityDup;
import com.aspl.mbs.R;
import com.aspl.mbsmodel.FilterInfoModel;
import com.aspl.mbsmodel.JackDepartmentModel;
import com.aspl.task.TaskDepartmentList;
import com.aspl.task.TaskDepartments;
import com.aspl.task.TaskFilterInfo;
import com.aspl.ws.Async_Dept;
import com.aspl.ws.Async_getCommonService;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by new on 07/12/2017.
 */
public class FilterFragment extends Fragment implements TaskFilterInfo.TaskFilterInfoEvent,TaskDepartmentList.TaskDepartmentListEvent {
    public FilterFragment() {}
    public FilterInfoModel filterInfoModel;
    public static ArrayList<String> finalSubDeptModelList = new ArrayList<>();

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
        setRetainInstance(true);
        //getActivity().invalidateOptionsMenu();
    }

    private static ExpandDeptAdapter adapter;
    public ExpandableListView dept_expList;
    TextView txtdepartment;
    LinearLayout llMain;
    public static FilterFragment filterFragment;
    public static String Tag="FilterFragment";
    public static FilterFragment getInstance() {
        return filterFragment;
    }

//    @Override
//    public void onStart() {
//        super.onStart();
//
//        if (Constant.SubDepartmentList.size() == 0 || Constant.DepartmentList.size()== 0) {
//            String Url1 = Constant.WS_BASE_URL + Constant.GET_STYLE_DEPARTMENT_LIST + Constant.STOREID;
//            new Async_getCommonService(getActivity(), Url1).execute();
//        }
//    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        filterFragment = this;
        View rootView = inflater.inflate(R.layout.fragment_filter, container, false);

        callWSForFilterDetails();

        dept_expList = (ExpandableListView) rootView.findViewById(R.id.dept_expList);
        dept_expList.setGroupIndicator(null);

        //dept_expList.setChildDivider(getResources().getDrawable(Color.WHITE));

        txtdepartment = (TextView) rootView.findViewById(R.id.txtDepartmentTitle);
        txtdepartment.setTextColor(Color.parseColor(Constant.themeModel.ThemeColor));

        llMain = (LinearLayout) rootView.findViewById(R.id.llMain);

        llMain.setBackgroundColor(Color.parseColor(Constant.themeModel.Backgroundcolor));
//        llMain.setBackgroundColor(Color.WHITE);
        //ArrayList<String> TitleList= new ArrayList<String>(Constant.LHSLIDER_LIST.keySet());

//        Log.e("Log", "Department Size=" + Constant.DEPARTMENTLIST.size());
//        dept_expList.setAdapter(new ExpandDeptAdapter(getActivity(), Constant.DepartmentList));
//
//        for(int i=0;i<Constant.DepartmentList.size();i++){
//            FilterFragment.getInstance().dept_expList.expandGroup(i);
//        }

//        //Constant.DEPARTMENTLIST
        return rootView;
    }

    private void setListener() {

        dept_expList.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {

            @Override
            public boolean onGroupClick(ExpandableListView listview, View view,
                                        int group_pos, long id) {

//                Toast.makeText(getActivity(),
//                        "You clicked group: " + adapter.getGroup(group_pos),
//                        Toast.LENGTH_SHORT).show();
                return false;
            }
        });


        // This listener will expand one group at one time
        // You can remove this listener for expanding all groups
        dept_expList.setOnGroupExpandListener(new ExpandableListView.OnGroupExpandListener() {

            // Default position
            int previousGroup = -1;

            @Override
            public void onGroupExpand(int groupPosition) {
                if (groupPosition != previousGroup) {

                    // Collapse the expanded group
//                    dept_expList.collapseGroup(previousGroup);

//                            GridView gridViewSubdept = convertView.findViewById(R.id.gridViewSubdept);
//            gridViewSubdept.setAdapter(new GridviewSubDeptAdapter(context,subDeptModel,departmentlist.get(listPosition).subDeptModelList,departmentlist.get(listPosition).dept_id,expandedListPosition));

//                            GridView gridViewSubdept = convertView.findViewById(R.id.gridViewSubdept);
//                            gridViewSubdept.setAdapter(new GridviewSubDeptAdapter(context,subDeptModel,departmentlist.get(listPosition).subDeptModelList,departmentlist.get(listPosition).dept_id,expandedListPosition));


//                            Toast.makeText(
//                                    getActivity(),
//                                    "collapse : " + groupPosition,
//                                    Toast.LENGTH_SHORT).show();
                }
                previousGroup = groupPosition;
            }

        });

        // This listener will show toast on child click
        dept_expList.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {

            @Override
            public boolean onChildClick(ExpandableListView listview, View view,
                                        int groupPos, int childPos, long id) {
//                Toast.makeText(
//                        getActivity(),
//                        "You clicked child: " + adapter.getChild(groupPos, childPos),
//                        Toast.LENGTH_SHORT).show();
                return false;
            }
        });
    }

    private void callWSForFilterDetails() {

        if(Constant.SCREEN_LAYOUT==1){
            if(MainActivity.getInstance().filterInfoModel != null) {
                filterInfoModel = MainActivity.getInstance().filterInfoModel;

                callDepartmentSubDeptWS(filterInfoModel);

            }else{
                callFilterInfoWS();
            }
        }else if(Constant.SCREEN_LAYOUT==2) {

            if(MainActivityDup.getInstance().filterInfoModel != null) {
                filterInfoModel = MainActivityDup.getInstance().filterInfoModel;

                callDepartmentSubDeptWS(filterInfoModel);

            }else{
                callFilterInfoWS();
            }
        }

    }

    private void callDepartmentSubDeptWS(FilterInfoModel filterInfoModel) {

        if(filterInfoModel!= null && filterInfoModel.getStyleDisplay().equals("Y")) {

            String Url1 = Constant.WS_BASE_URL + Constant.GET_STYLE_DEPARTMENT_LIST + Constant.STOREID;
            new Async_getCommonService(getActivity(), Url1).execute();

        }else{

            FilterFragment.getInstance().callDepartmentWS(false);

//            String Url1 = Constant.WS_BASE_URL + Constant.GETDEPARTMENT + Constant.STOREID;
//            new Async_Dept(Url1,getActivity(),false).execute();
        }

    }

    private void callFilterInfoWS() {

        String url;

        url = Constant.WS_BASE_URL + Constant.GET_FILTER_INFO
                + "/" + Constant.STOREID;

        TaskFilterInfo taskFilterInfo = new TaskFilterInfo(this,getActivity(),"isForDepartment");
        taskFilterInfo.execute(url);
    }


    @Override
    public void onResume() {
        super.onResume();

        if (Constant.SCREEN_LAYOUT == 1) {

            MainActivity.getInstance().llsearch.setVisibility(View.VISIBLE);

            if (Constant.isFromMic) {
                MainActivity.getInstance().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);
                MainActivity.getInstance().mSearchedt.requestFocus();
                MainActivity.getInstance().mSearchedt.setFocusableInTouchMode(true);
                Constant.isFromMic = false;
            } else {
                MainActivity.getInstance().mSearchedt.clearFocus();
                MainActivity.getInstance().mSearchedt.setText("");
                Utils.hideKeyboard();
            }

        }
        Utils.hideKeyboard();
        //txtdepartment.setTextColor(Color.parseColor(Constant.themeModel.FontColor));
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
    }

    @Override
    public void onTaskFilterInfoResult(FilterInfoModel filterInfoModel) {
        if(filterInfoModel != null){
            this.filterInfoModel = filterInfoModel;
        }
    }

    @Override
    public void onTaskFilterInfoStyleResult(FilterInfoModel filterInfoModel) {

        if(filterInfoModel != null){
            callDepartmentSubDeptWS(filterInfoModel);
        }
    }

    public void callDepartmentWS(boolean isWithSubDept) {
        String Url5 = Constant.WS_BASE_URL + Constant.GET_DEPARTMETS + Constant.STOREID;
        TaskDepartmentList taskDepartments = new TaskDepartmentList(getActivity(),this,isWithSubDept);
        taskDepartments.execute(Url5);
    }

    @Override
    public void onGetDepartmentListResult() {

//        Log.e("Log", "Department Size=" + Constant.DEPARTMENTLIST.size());
//        dept_expList.setAdapter(new ExpandDeptAdapter(getActivity(), Constant.DepartmentList));

        adapter = new ExpandDeptAdapter(getActivity(), Constant.DepartmentList);
        dept_expList.setAdapter(adapter);

        setListener();

//        for(int i=0;i<Constant.DepartmentList.size();i++){
//            FilterFragment.getInstance().dept_expList.expandGroup(i);
//        }
    }
}
