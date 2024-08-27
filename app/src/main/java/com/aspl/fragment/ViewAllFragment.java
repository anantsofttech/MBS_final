package com.aspl.fragment;


import android.app.ProgressDialog;
import android.graphics.Color;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.aspl.Adapter.ViewallAdapter;
import com.aspl.Utils.Constant;

import android.view.WindowManager;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.aspl.Utils.DeviceInfo;
import com.aspl.Utils.DialogUtils;
import com.aspl.Utils.Utils;
import com.aspl.mbs.MainActivity;
import com.aspl.mbs.MainActivityDup;
import com.aspl.mbs.R;
import com.aspl.mbsmodel.HomeItemModel;
import com.aspl.mbsmodel.ShoppingCardModel;
import com.aspl.mbsmodel.UpdateCartQuantity;
import com.aspl.mbsmodel.UserModel;
import com.aspl.task.TaskAddtoCart;
import com.aspl.task.TaskCart;
import com.aspl.task.TaskUpdatetoCart;
import com.aspl.task.TaskViewAll;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class ViewAllFragment extends Fragment implements
        TaskUpdatetoCart.TaskUpdateCart, TaskViewAll.TaskViewAllEvent,
        TaskCart.TaskCardEvent, ViewallAdapter.CardAdapterEvent,
        TaskAddtoCart.TaskAddToCartEvent {

    public static boolean isFromadpter_whenclickedonaddtocart = false;
    RecyclerView recyclerView;
    String type;
    Integer SubId = 0;
    Integer deptId = 0;
    public static final String TAG = "ViewAllFragment";
    ViewallAdapter viewallAdapter;
    LinearLayout llFilter, llSort;
    public static ViewAllFragment viewAllFragment;
    public int shortingCheckBoxPosition = 0;
    String shortCall = "price",shortDept = "Asc";
    long cartId = 0;
    String cartQtyOfItem = "";
    int requestedQty = 1;
    HomeItemModel homeItemModel;
    boolean isComeFomAddTocartBtn = false;
    boolean iscomeFromUpdate = false;
    String blockStratprice;
    String blockEndprice;
    String blockdisountGroup;
    String blockDisplayedText;
    public String searchtext;
    String departmentVal;
    boolean loadmore = false, isneedtoloadAgain = false;
    LinearLayoutManager manager;
    int count = 1;
    List<HomeItemModel> viewAllList = new ArrayList<>();
    boolean isComeforSortFilter = false;
    boolean iscomefromSearchIcon = false;
    ProgressDialog loading = null;
    String priceRangeInt = "0";
    LinearLayout ll_main,llmainfilter,ll_no_result,ll_main_viewall;
    Button btn_return_to_home;
    boolean loadItemsAlerady = false;
    String fromWhereClicked = "";
    UpdateCartQuantity addToCarttemp = null;

    public static ViewAllFragment getInstance() {
        return viewAllFragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_view_all, container, false);

        initViews(view);

        Bundle bundle = this.getArguments();
        if (bundle != null) {

            iscomefromSearchIcon = bundle.getBoolean("iscomefromSearchIcon", false);
            searchtext = bundle.getString("searchtext", "");
            if(iscomefromSearchIcon){
                searchtext = bundle.getString("searchtext", "");

                callSearchIconWS(searchtext);

                if (Constant.SCREEN_LAYOUT == 1) {
                    MainActivity.isSearchicon = false;
                }else if (Constant.SCREEN_LAYOUT == 2) {
                    MainActivityDup.isSearchicon = false;
                }

            }else{

                try {
                    type = bundle.getString("type", "");
                    deptId = Integer.valueOf(bundle.getString("deptId", ""));
                    SubId = Integer.valueOf(bundle.getString("SubId", ""));
                    blockStratprice = bundle.getString("blockStratprice", "");
                    blockEndprice = bundle.getString("blockEndprice", "");
                    blockdisountGroup = bundle.getString("blockdisountGroup", "");
                    blockDisplayedText = bundle.getString("blockDisplayedText", "");
                    departmentVal = bundle.getString("OnlyDepartment", "");
                }catch (Exception e) {

                }

                if (Constant.SCREEN_LAYOUT == 1) {
                    if (type == null || type.isEmpty()) {
                        if (MainActivity.type != null && !MainActivity.type.isEmpty()) {
                            type = MainActivity.type;
                            type = type.trim();
                        }
                    }

//                    if (deptId == null || deptId == 0) {
//                        if (!MainActivity.deptId.isEmpty()) {
//                            deptId = Integer.valueOf(MainActivity.deptId);
//                        }
//                    }

                    if (SubId == null || SubId == 0) {
                        if (MainActivity.subDepartment != null && !MainActivity.subDepartment.isEmpty()) {
                            if(!type.equals("department")){
                                SubId = 0;
                            }else if(type.equals("department")&& departmentVal.equalsIgnoreCase("onlyDepartment")){
                                SubId = 0;
                            }else{
                                SubId = Integer.valueOf(MainActivity.subDepartment);
                            }
                        }
                    }

                    if (blockStratprice == null || blockStratprice.isEmpty()) {

                        if (MainActivity.valueOne == null || MainActivity.valueOne.isEmpty()) {
                            MainActivity.valueOne = "0";
                            blockStratprice = MainActivity.valueOne;
                        }else{
                            blockStratprice = MainActivity.valueOne;
                        }

                    }

                    if (blockEndprice == null || blockEndprice.isEmpty()) {

                        if (MainActivity.valueTwo == null || MainActivity.valueTwo.isEmpty()) {
                            MainActivity.valueTwo = "0";
                            blockEndprice = MainActivity.valueTwo;
                        }else{
                            blockEndprice = MainActivity.valueTwo;
                        }
                    }

                    if (blockdisountGroup == null || blockdisountGroup.isEmpty()) {

                        if (MainActivity.specialOfferGroup == null || MainActivity.specialOfferGroup.isEmpty()) {
                            MainActivity.specialOfferGroup = "0";
                            blockdisountGroup = MainActivity.specialOfferGroup;
                        }else{
                            blockdisountGroup = MainActivity.specialOfferGroup;
                        }
                    }

                    if (MainActivity.iscomfromSort) {
                        if (MainActivity.shortDept.isEmpty()) {
                            shortDept = "Asc";
                        } else {
                            shortDept = MainActivity.shortDept;
                        }

                        if (MainActivity.shortCall.isEmpty()) {
                            shortCall = "price";
                        } else {
                            shortCall = MainActivity.shortCall;
                        }

                    }
                } else if (Constant.SCREEN_LAYOUT == 2) {
                    if (type == null || type.isEmpty()) {
                        if (MainActivityDup.type != null && !MainActivityDup.type.isEmpty()) {
                            type = MainActivityDup.type;
                            type = type.trim();
                        }
                    }

//                    if (deptId == null || deptId == 0) {
//                        if (!MainActivityDup.deptId.isEmpty()) {
//                            deptId = Integer.valueOf(MainActivityDup.deptId);
//                        }
//                    }

                    if (SubId == null || SubId == 0) {
                        if (!MainActivityDup.subDepartment.isEmpty()) {
                            if(!type.equals("department")){
                                SubId = 0;

                            } else if(type.equals("department")&& departmentVal.equalsIgnoreCase("onlyDepartment")){
                                SubId = 0;

                            }else {
                                SubId = Integer.valueOf(MainActivityDup.subDepartment);
                            }
                        }
                    }

                    if (blockStratprice == null || blockStratprice.isEmpty()) {

                        if (MainActivityDup.valueOne == null || MainActivityDup.valueOne.isEmpty()) {
                            MainActivityDup.valueOne = "0";
                            blockStratprice = MainActivityDup.valueOne;
                        }else{
                            blockStratprice = MainActivityDup.valueOne;
                        }
                    }

                    if (blockEndprice == null || blockEndprice.isEmpty()) {

                        if (MainActivityDup.valueTwo == null || MainActivityDup.valueTwo.isEmpty()) {
                            MainActivityDup.valueTwo = "0";
                            blockEndprice = MainActivityDup.valueTwo;
                        }else{
                            blockEndprice = MainActivityDup.valueTwo;
                        }
                    }

                    if (blockdisountGroup == null || blockdisountGroup.isEmpty()) {

                        if (MainActivityDup.specialOfferGroup == null || MainActivityDup.specialOfferGroup.isEmpty()) {
                            MainActivityDup.specialOfferGroup = "0";
                            blockdisountGroup = MainActivityDup.specialOfferGroup;
                        }else{
                            blockdisountGroup = MainActivityDup.specialOfferGroup;
                        }

                    }

                    if (MainActivityDup.iscomfromSort) {
                        if (MainActivityDup.shortDept.isEmpty()) {
                            shortDept = "Asc";
                        } else {
                            shortDept = MainActivityDup.shortDept;
                        }

                        if (MainActivityDup.shortCall.isEmpty()) {
                            shortCall = "price";
                        } else {
                            shortCall = MainActivityDup.shortCall;
                        }

                    }
                }

                loadItemListingWS(type, false);
            }
        }

        return  view;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewAllFragment = this;
    }

    @Override
    public void onResume() {
        super.onResume();
//        mMapView.onResume();

        if(Constant.SCREEN_LAYOUT == 2){
            MainActivityDup.getInstance().showbackButton();
        }

        if (Constant.SCREEN_LAYOUT == 1) {

            MainActivity.getInstance().llsearch.setVisibility(View.VISIBLE);

            if (Constant.isFromMic) {
                MainActivity.getInstance().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);
                MainActivity.getInstance().mSearchedt.requestFocus();
                MainActivity.getInstance().mSearchedt.setFocusableInTouchMode(true);
                Constant.isFromMic = false;
            } else {
                MainActivity.getInstance().mSearchedt.clearFocus();
                Utils.hideKeyboard();
            }

        }
//        else if (Constant.SCREEN_LAYOUT == 2) {
//
//            MainActivityDup.getInstance().llsearch.setVisibility(View.VISIBLE);
//
//            if (Constant.isFromMicSeclayout) {
//                MainActivityDup.getInstance().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);
//                MainActivityDup.getInstance().mSearchedt.requestFocus();
//                MainActivityDup.getInstance().mSearchedt.setFocusableInTouchMode(true);
//                Constant.isFromMicSeclayout = false;
//            } else {
//                MainActivityDup.getInstance().mSearchedt.clearFocus();
//                Utils.hideKeyboard();
//            }
//        }

        Utils.hideKeyboard();
    }

    private void initViews(View view) {
        count = 1;
        if(getActivity() != null) {
            loading = new ProgressDialog(getActivity(), R.style.MyprogressDTheme);
            loading.setCancelable(false);
            loading.setProgressStyle(ProgressDialog.STYLE_SPINNER);
            loading.show();
        }

        ll_main = view.findViewById(R.id.ll_main);
        ll_main_viewall = view.findViewById(R.id.ll_main_viewall);
        ll_main_viewall.setBackgroundColor(Color.parseColor(Constant.themeModel.Backgroundcolor));
        llmainfilter = view.findViewById(R.id.llmainfilter);
        ll_no_result = view.findViewById(R.id.ll_no_result);
//        ll_main_viewall = view.findViewById(R.id.ll_main_viewall);
//        ll_main_viewall.setBackgroundColor(Color.parseColor(Constant.themeModel.Backgroundcolor));

        btn_return_to_home = view.findViewById(R.id.btn_return_to_home);

        recyclerView = view.findViewById(R.id.recyclerView);
        manager = new GridLayoutManager(getActivity(), 2);
        recyclerView.setLayoutManager(manager);

        llFilter = view.findViewById(R.id.llFilter);
        llSort = view.findViewById(R.id.llSort);

        llSort.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (Constant.SCREEN_LAYOUT == 1) {
                    MainActivity.getInstance().showSortPopup();
                }else if(Constant.SCREEN_LAYOUT == 2){
                    MainActivityDup.getInstance().showSortPopup();
                }
            }
        });

        llFilter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Constant.filter_click = true;
                if (Constant.SCREEN_LAYOUT == 1) {
                    if (Constant.filter_click) {
                        Constant.filter_click = false;
                        MainActivity.getInstance().callOfferFilter();
                    }
                }else if(Constant.SCREEN_LAYOUT == 2){
                    if (Constant.filter_click) {
                        Constant.filter_click = false;
                        MainActivityDup.getInstance().callOfferFilter();
                    }
                }
            }
        });
    }
//


    public void loadItemListingWS(String type, Boolean iscomefromsortFilter) {

//        http://192.168.172.244:889/WebStoreRestService.svc/GetInventorys/707/0/0/0/0/0;0/1/12/homepageitem/Price/Asc/0/0/0/0
//Parameters : storeno, deptid,styleid,sizeid,discount_group,minprice;maxprice,CountData,10,selMaintype,SortColumn,SortStatus,StartPrice,EndPrice,imagecount;

        isComeforSortFilter = iscomefromsortFilter;
        if(isComeforSortFilter){

            if (Constant.SCREEN_LAYOUT == 1) {

                if (MainActivity.deptId.isEmpty()) {
                    MainActivity.deptId = "0";
                }

                if (MainActivity.subDepartment.isEmpty()) {
                    MainActivity.subDepartment = "0";
                }

                if (MainActivity.valueOne == null || MainActivity.valueOne.isEmpty()) {
                    MainActivity.valueOne = "0";
                }

                if (MainActivity.valueTwo == null || MainActivity.valueTwo.isEmpty()) {
                    MainActivity.valueTwo = "0";
                }

                if (MainActivity.specialOfferGroup == null || MainActivity.specialOfferGroup.isEmpty()) {
                    MainActivity.specialOfferGroup = "0";
                }

                if (MainActivity.shortDept.isEmpty()) {
                    MainActivity.shortDept = "Asc";
                }

                if (MainActivity.shortCall.isEmpty()) {
                    MainActivity.shortCall = "price";
                }

                if(MainActivity.priceRange.isEmpty()){
                    MainActivity.priceRange = "0;0";
                }

                if(MainActivity.deptSizes.isEmpty()){
                    MainActivity.deptSizes = "0";
                }

                if(MainActivity.onlyImage.isEmpty()){
                    MainActivity.onlyImage = "0";
                }

                if (MainActivity.priceRange.isEmpty()) {
                    priceRangeInt = "0;0";

                }else{

                    String[] price = MainActivity.priceRange.split(";");
                    String min = price[0];
                    String max = price[1];
                    priceRangeInt = (int)Double.parseDouble(min) + ";" + (int)Double.parseDouble(max);
                }

//                    String Url = Constant.WS_BASE_URL + Constant.GET_INVENTORYS + "/" +  Constant.STOREID + "/" + MainActivity.deptId + "/" + MainActivity.subDepartment + "/" + MainActivity.deptSizes
                    String Url = Constant.WS_BASE_URL + Constant.GET_INVENTORY_LIST_BY_PAGING_NEW + "/" +  Constant.STOREID + "/" + MainActivity.deptId + "/" + MainActivity.subDepartment + "/" + MainActivity.deptSizes
                            + "/" + MainActivity.specialOfferGroup + "/" + priceRangeInt + "/" + count + "/" + "12" + "/" + type + "/" + MainActivity.shortCall + "/" + MainActivity.shortDept + "/" + MainActivity.valueOne + "/" + MainActivity.valueTwo + "/" + MainActivity.onlyImage;
//                + "/" +"0" ;

                    Log.e("","filterurl"+Url);
                    TaskViewAll taskViewAll = new TaskViewAll(this,getActivity(),type);
//                    taskViewAll.execute(Url);
                    taskViewAll.executeOnExecutor(TaskViewAll.THREAD_POOL_EXECUTOR,Url);



            }else if (Constant.SCREEN_LAYOUT == 2) {

                if (MainActivityDup.deptId.isEmpty()) {
                    MainActivityDup.deptId = "0";
                }

                if (MainActivityDup.subDepartment.isEmpty()) {
                    MainActivityDup.subDepartment = "0";
                }

                if (MainActivityDup.valueOne == null || MainActivityDup.valueOne.isEmpty()) {
                    MainActivityDup.valueOne = "0";
                }

                if (MainActivityDup.valueTwo == null || MainActivityDup.valueTwo.isEmpty()) {
                    MainActivityDup.valueTwo = "0";
                }

                if (MainActivityDup.specialOfferGroup == null || MainActivityDup.specialOfferGroup.isEmpty()) {
                    MainActivityDup.specialOfferGroup = "0";
                }

                if (MainActivityDup.shortDept.isEmpty()) {
                    MainActivityDup.shortDept = "Asc";
                }

                if (MainActivityDup.shortCall.isEmpty()) {
                    MainActivityDup.shortCall = "price";
                }

                if(MainActivityDup.deptSizes.isEmpty()){
                    MainActivityDup.deptSizes = "0";
                }

                if(MainActivityDup.onlyImage.isEmpty()){
                    MainActivityDup.onlyImage = "0";
                }

                if (MainActivityDup.priceRange.isEmpty()) {
                    priceRangeInt = "0;0";

                }else{
                    String[] price = MainActivityDup.priceRange.split(";");
                    String min = price[0];
                    String max = price[1];
                    priceRangeInt = (int)Double.parseDouble(min) + ";" + (int)Double.parseDouble(max);
                }


//                String Url = Constant.WS_BASE_URL + Constant.GET_INVENTORYS + "/" +  Constant.STOREID + "/" + MainActivityDup.deptId + "/" + MainActivityDup.subDepartment + "/" + MainActivityDup.deptSizes
                String Url = Constant.WS_BASE_URL + Constant.GET_INVENTORY_LIST_BY_PAGING_NEW + "/" +  Constant.STOREID + "/" + MainActivityDup.deptId + "/" + MainActivityDup.subDepartment + "/" + MainActivityDup.deptSizes
                        + "/" + MainActivityDup.specialOfferGroup + "/" + priceRangeInt + "/" + count + "/" + "12" + "/" + type + "/" + MainActivityDup.shortCall + "/" + MainActivityDup.shortDept + "/" + MainActivityDup.valueOne + "/" + MainActivityDup.valueTwo + "/" + MainActivityDup.onlyImage;
//                + "/" +"0" ;
                TaskViewAll taskViewAll = new TaskViewAll(this,getActivity(),type);
//                taskViewAll.execute(Url);
                taskViewAll.executeOnExecutor(TaskViewAll.THREAD_POOL_EXECUTOR,Url);

            }

        }else if(!searchtext.isEmpty()){
            //sorting data with search keyword

            if (Constant.SCREEN_LAYOUT == 1) {

                if (MainActivity.iscomfromSort) {

//                    String Url = Constant.WS_BASE_URL + Constant.GET_INVENTORY_BY_FILTER + "/" + Constant.STOREID + "/" + "0" + "/" + "0" + "/" + "0"
                    String Url = Constant.WS_BASE_URL + Constant.GET_INVENTORY_BY_FILTER_NEW + "/" + Constant.STOREID + "/" + "0" + "/" + "0" + "/" + "0"
                            + "/" + "0" + "/" + "0;0" + "/" + count + "/" + "12" + "/" + "allitems" + "/" + MainActivity.shortCall + "/" + MainActivity.shortDept + "/" + "0" + "/" + "0" + "/" + "0" + "/" + searchtext;

                    Log.e("", "filterurl" + Url);
                    TaskViewAll taskViewAll = new TaskViewAll(this, getActivity(), type);
                    taskViewAll.executeOnExecutor(TaskViewAll.THREAD_POOL_EXECUTOR,Url);
//                    taskViewAll.execute(Url);

                    MainActivity.iscomfromSort = false;

                }
            }else if (Constant.SCREEN_LAYOUT == 2) {
                if (MainActivityDup.iscomfromSort) {

//                    String Url = Constant.WS_BASE_URL + Constant.GET_INVENTORY_BY_FILTER + "/" + Constant.STOREID + "/" + "0" + "/" + "0" + "/" + "0"
                    String Url = Constant.WS_BASE_URL + Constant.GET_INVENTORY_BY_FILTER_NEW + "/" + Constant.STOREID + "/" + "0" + "/" + "0" + "/" + "0"
                            + "/" + "0" + "/" + "0;0" + "/" + count + "/" + "12" + "/" + "allitems" + "/" + MainActivityDup.shortCall + "/" + MainActivityDup.shortDept + "/" + "0" + "/" + "0" + "/" + "0" + "/" + searchtext;

                    Log.e("", "filterurl" + Url);
                    TaskViewAll taskViewAll = new TaskViewAll(this, getActivity(), type);
                    taskViewAll.executeOnExecutor(TaskViewAll.THREAD_POOL_EXECUTOR,Url);
//                    taskViewAll.execute(Url);

                    MainActivityDup.iscomfromSort = false;

                }
            }

        }else{

//            String Url = Constant.WS_BASE_URL + Constant.GET_INVENTORYS + "/" +  Constant.STOREID + "/" + deptId + "/" + SubId + "/" + "0"
            String Url = Constant.WS_BASE_URL + Constant.GET_INVENTORY_LIST_BY_PAGING_NEW + "/" +  Constant.STOREID + "/" + deptId + "/" + SubId + "/" + "0"
                    + "/" + blockdisountGroup + "/" + "0;0" + "/" + count + "/" + "12" + "/" + type + "/" + shortCall + "/" + shortDept + "/" + blockStratprice + "/" + blockEndprice + "/" + "0";
//                + "/" +"0" ;
            TaskViewAll taskViewAll = new TaskViewAll(this,getActivity(),type);
            taskViewAll.executeOnExecutor(TaskViewAll.THREAD_POOL_EXECUTOR,Url);
//            taskViewAll.execute(Url);
        }

    }


//    public void loadapplyFilterListing(String deptId, String subDepartment, String deptSizes, String priceRange, String isPriceChanged, String onlyImage, String type, String valueOne, String valueTwo, String shortCall, String shortDept){
//
//        String Url = Constant.WS_BASE_URL + Constant.GET_INVENTORYS + "/" +  Constant.STOREID + "/" + deptId + "/" + subDepartment + "/" + deptSizes
//                + "/" + "0" + "/" + priceRange + "/" + "1" + "/" + "12" + "/" + type + "/" + shortCall + "/" + shortDept + "/" + "0" + "/" +"0" + "/" + "0"
//                + "/" +"0" ;
//        TaskViewAll taskViewAll = new TaskViewAll(this,getActivity(),type);
//        taskViewAll.execute(Url);
//
//    }


    @Override
    public void onGetViewallResult(List<HomeItemModel> tempviewAllList, String type) {

        if(loading != null && loading.isShowing()){
            loading.dismiss();
        }

        ll_main.setVisibility(View.VISIBLE);
//        llmainfilter.setVisibility(View.INVISIBLE);
        ll_no_result.setVisibility(View.GONE);

//        if (Constant.SCREEN_LAYOUT == 1) {

//            if (MainActivity.getInstance().llsortandfilter.getVisibility()== View.GONE) {
//                MainActivity.getInstance().llsortandfilter.setVisibility(View.VISIBLE);
//            }
//        }

        if(tempviewAllList != null && tempviewAllList.size() > 0) {

            loadItemsAlerady = true;

            if (tempviewAllList.size() >= 12) {
                isneedtoloadAgain = true;
                loadmore = true;
            } else {
                isneedtoloadAgain = false;
                loadmore = false;
            }

            if (Constant.SCREEN_LAYOUT == 1) {
                if(!isComeforSortFilter)
                    MainActivity.getInstance().sendViewalldata(String.valueOf(deptId), String.valueOf(SubId), type, blockdisountGroup, blockStratprice, blockEndprice, blockDisplayedText);
            } else if (Constant.SCREEN_LAYOUT == 2) {
                if(!isComeforSortFilter)
                    MainActivityDup.getInstance().sendViewalldata(String.valueOf(deptId), String.valueOf(SubId), type, blockdisountGroup, blockStratprice, blockEndprice, blockDisplayedText);
            }

            if (count == 1 && tempviewAllList != null && tempviewAllList.size() > 0) {
                viewAllList = tempviewAllList;
                viewallAdapter = new ViewallAdapter(getActivity(), this, viewAllList);
                recyclerView.setAdapter(viewallAdapter);

            } else {

                int startpos = viewAllList.size();
                int newItemcount = tempviewAllList.size();
                viewAllList.addAll(tempviewAllList);
                viewallAdapter.notifyItemRangeInserted(startpos, newItemcount);

            }

            recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
                @Override
                public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                    // Handle scroll state changes if needed
                }

                @Override
                public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                    if (isneedtoloadAgain) {
                        LinearLayoutManager layoutManager = (LinearLayoutManager) recyclerView.getLayoutManager();
                        int lastVisiblePosition = layoutManager.findLastVisibleItemPosition();
                        int totalItemCount = recyclerView.getAdapter().getItemCount();

                        // Check if the last visible item position is close to the total item count
                        if (lastVisiblePosition >= totalItemCount - 6 && loadmore) {
                            count = count + 1;
                            loadmore = false;
                            if (isComeforSortFilter) {
                                loadItemListingWS(type, true);
                            } else {
                                if (!searchtext.isEmpty()) {
                                    callSearchIconWS(searchtext);
                                } else {
                                    loadItemListingWS(type, false);
                                }
                            }
                        } else {
                            if (isComeforSortFilter) {
                                isComeforSortFilter = false;
                            }
                        }
                    }
                }
            });
        }else {
            if(!loadItemsAlerady) {
                ll_main.setVisibility(View.GONE);
                llmainfilter.setVisibility(View.GONE);
                ll_no_result.setVisibility(View.VISIBLE);

                if (Constant.SCREEN_LAYOUT == 1) {
                    MainActivity.getInstance().llsortandfilter.setVisibility(View.GONE);
                } else if (Constant.SCREEN_LAYOUT == 2) {
                    MainActivityDup.getInstance().llsortandfilter.setVisibility(View.GONE);
                }

                btn_return_to_home.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        int backStackEntry = 0;
                        if (getFragmentManager() != null) {
                            backStackEntry = getFragmentManager().getBackStackEntryCount();
                        }
                        if (backStackEntry > 0) {
                            for (int i = 0; i < backStackEntry; i++) {
                                if (getFragmentManager() != null) {
                                    getFragmentManager().popBackStackImmediate();
                                } else {
                                    if (Constant.SCREEN_LAYOUT == 1) {
                                        MainActivity.getInstance().onBackPressed();
                                    } else if (Constant.SCREEN_LAYOUT == 2) {
                                        MainActivityDup.getInstance().onBackPressed();
                                    }

                                }
                            }
                        }

                        if (Constant.SCREEN_LAYOUT == 1) {
                            MainActivity.showHomePage();
                            MainActivity.getInstance().loadHomeWebPage();
                            MainActivity.getInstance().clearFilterSelection();

                        } else if (Constant.SCREEN_LAYOUT == 2) {
                            MainActivityDup.showHomePage();
                            MainActivityDup.getInstance().loadHomeWebPage();
                            MainActivityDup.getInstance().clearFilterSelection();
                        }

//
//                        if (Constant.SCREEN_LAYOUT == 1) {
//                            MainActivity.getInstance().CallHomeFragment();
//                            MainActivity.getInstance().clearFilterSelection();
//
//
//                        } else if (Constant.SCREEN_LAYOUT == 2) {
//                            MainActivityDup.getInstance().CallHomeFragment();
//                            MainActivityDup.getInstance().clearFilterSelection();
//                        }

                    }
                });

            }
        }
    }


    public void updateToCartData(int finalRequested_Quantity, UpdateCartQuantity updateCartQuantity) {

        String noteCartId = "0";

        if(updateCartQuantity != null && updateCartQuantity.getNote()!= null  && !updateCartQuantity.getNote().trim().isEmpty()){
            noteCartId = updateCartQuantity.getNote();
        }else{
            noteCartId = String.valueOf(cartId);
        }

        if (homeItemModel != null && !homeItemModel.getItemMstId().isEmpty()) {

            String sku = null;
            try {
                sku = URLEncoder.encode(this.homeItemModel.getItemMstId().trim(), "utf-8");
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }


            if (UserModel.Cust_mst_ID != null) {

                String cartWSurl = Constant.WS_BASE_URL + Constant.DELETE_CART + noteCartId + "/" + "Cart" + "/" + UserModel.Cust_mst_ID +
                        "/" + sku + "/" + finalRequested_Quantity +
                        "/" + Constant.STOREID + "/" + "0" + "/" + "Updatemoreincart" + "/" + Constant.invType;

                TaskUpdatetoCart taskUpdatetoCart = new TaskUpdatetoCart(this);
                taskUpdatetoCart.executeOnExecutor(TaskUpdatetoCart.THREAD_POOL_EXECUTOR,cartWSurl);
//                taskUpdatetoCart.execute(cartWSurl);
            } else {

                String cartWSurl = Constant.WS_BASE_URL + Constant.DELETE_CART + noteCartId + "/" + "Cart" + "/" + "0" +
                        "/" + sku + "/" + finalRequested_Quantity +
                        "/" + Constant.STOREID + "/" + "0" + "/" + "Updatemoreincart"+ "/" + Constant.invType;


                TaskUpdatetoCart taskUpdatetoCart = new TaskUpdatetoCart(this);
                taskUpdatetoCart.executeOnExecutor(TaskUpdatetoCart.THREAD_POOL_EXECUTOR,cartWSurl);
//                taskUpdatetoCart.execute(cartWSurl);
            }
        }
    }


    @Override
    public void updateCartResult(UpdateCartQuantity updateCart) {

        if (updateCart.getResult().equalsIgnoreCase("success")) {
            Utils.vibrateDevice(getContext());
            DialogUtils.showDialog("Added to cart!");
            onGetCartData("", null);
        }
    }

    public void onGetCartData(String fromwhere, UpdateCartQuantity addToCart) {

        String url = null;
        if (UserModel.Cust_mst_ID != null) {
//            url = Constant.WS_BASE_URL + Constant.GET_CUSTOMER_CARD_DATA + UserModel.Cust_mst_ID + "/" + Constant.MY_CART + Constant.STOREID;
            url = Constant.WS_BASE_URL + Constant.GET_CUSTOMER_CARD_DATA_V1 + UserModel.Cust_mst_ID + "/" + Constant.MY_CART + Constant.STOREID + Constant.ENCODE_TOKEN_ID;
            TaskCart taskCart = new TaskCart(this, "");
            taskCart.executeOnExecutor(TaskCart.THREAD_POOL_EXECUTOR,url);
//            taskCart.execute(url);
        } else {
            if (isAdded()) {
//                url = Constant.WS_BASE_URL + Constant.GET_CUSTOMER_CARD_DATA + DeviceInfo.getDeviceId(getActivity()) + "0011" + "/" + Constant.SESSION + Constant.STOREID;
                url = Constant.WS_BASE_URL + Constant.GET_CUSTOMER_CARD_DATA_V1 + DeviceInfo.getDeviceId(getActivity()) + "0011" + "/" + Constant.SESSION + Constant.STOREID + Constant.ENCODE_TOKEN_ID;
                TaskCart taskCart = new TaskCart(this, "");
                taskCart.executeOnExecutor(TaskCart.THREAD_POOL_EXECUTOR,url);
//                taskCart.execute(url);
            }
        }

        fromWhereClicked = fromwhere;
        this.addToCarttemp = addToCart;
    }

    @Override
    public void onShoppingCardResult(List<ShoppingCardModel> liShoppingCard, String s) {

        if (liShoppingCard.size() > 0) {

            if (Constant.SCREEN_LAYOUT == 1) {
                if (liShoppingCard.get(0).getCartID() == 0) {
                    /** Clear Shopping Cart Icon Count **/
                    MainActivity.getInstance().updateShoppingCartItemCount(0);
                } else {
                    int quntity = 0;
                    for (int i = 0; i < liShoppingCard.size(); i++) {
                        quntity = quntity + Integer.parseInt(liShoppingCard.get(i).getQty());

                        if (!liShoppingCard.get(i).getItemMstId().isEmpty() && !homeItemModel.getItemMstId().isEmpty()) {

                            if (liShoppingCard.get(i).getItemMstId().equalsIgnoreCase(homeItemModel.getItemMstId())) {

                                cartQtyOfItem = liShoppingCard.get(i).getQty();
                                cartId = liShoppingCard.get(i).getCartID();
                            }
                        }
                    }

                    MainActivity.countMenu.setTitle(String.valueOf(quntity));
                    MainActivity.getInstance().onShoppingCardResult(liShoppingCard, s);
                }

                openPopupOnclcik(cartId);

            } else if (Constant.SCREEN_LAYOUT == 2) {
                if (liShoppingCard.get(0).getCartID() == 0) {
                    /** Clear Shopping Cart Icon Count **/
                    MainActivityDup.getInstance().updateShoppingCartItemCount(0);
                } else {
                    int quntity = 0;
                    for (int i = 0; i < liShoppingCard.size(); i++) {
                        quntity = quntity + Integer.parseInt(liShoppingCard.get(i).getQty());

                        if (!liShoppingCard.get(i).getItemMstId().isEmpty() && !homeItemModel.getItemMstId().isEmpty()) {

                            if (liShoppingCard.get(i).getItemMstId().equalsIgnoreCase(homeItemModel.getItemMstId())) {

                                cartQtyOfItem = liShoppingCard.get(i).getQty();
                                cartId = liShoppingCard.get(i).getCartID();
                            }
                        }
                    }
                    MainActivityDup.countMenu.setTitle(String.valueOf(quntity));
                    MainActivityDup.getInstance().onShoppingCardResult(liShoppingCard, s);
                }

                openPopupOnclcik(cartId);
            }
        }else{
            openPopupOnclcik(cartId);
        }
    }

    private void openPopupOnclcik(long cartId) {

        if(isFromadpter_whenclickedonaddtocart){

            if(fromWhereClicked.equalsIgnoreCase("Already added")){

                if (isComeFomAddTocartBtn) {
                    if (cartQtyOfItem.isEmpty() && addToCarttemp != null) {

                        DialogUtils.notEnoughQuantityDialog(getActivity(), addToCarttemp, requestedQty, "viewall", addToCarttemp.getQty());
                    } else {
                        DialogUtils.notEnoughQuantityDialog(getActivity(), addToCarttemp, requestedQty, "viewall", cartQtyOfItem);
                    }
                    isComeFomAddTocartBtn = false;
                } else {
                }

            }else if(fromWhereClicked.equalsIgnoreCase("Not enough Stock")){

                DialogUtils.notEnoughQuantityNewDialog(getActivity(), addToCarttemp, requestedQty, "NotenoughStock", cartQtyOfItem, "fromViewall");

            }

            isFromadpter_whenclickedonaddtocart = false;
        }

    }


    @Override
    public void onCardItemPlus(int position, int quantity,HomeItemModel homeItemModel) {
//        this.homeItemModel = homeItemModel;
//        iscomeFromUpdate = true;
//
//        onUpdateQuantityTask(position, quantity, homeItemModel.getItemMstId(),iscomeFromUpdate);
    }

    @Override
    public void onCardItemMinus(int position, int quantity, HomeItemModel homeItemModel) {
//        this.homeItemModel = homeItemModel;
//        iscomeFromUpdate = true;
//
//        onUpdateQuantityTask(position, quantity, homeItemModel.getItemMstId(),iscomeFromUpdate);
    }


    public void addTocartData(HomeItemModel homeItemModel, boolean isComeFomAddTocartBtn, int resquantity)  {
        this.homeItemModel = homeItemModel;
        this.isComeFomAddTocartBtn = isComeFomAddTocartBtn;
        iscomeFromUpdate = false;
        requestedQty = resquantity;

        if (this.homeItemModel != null && !this.homeItemModel.getItemMstId().isEmpty()) {

            String sku = null;
            try {
                sku = URLEncoder.encode(this.homeItemModel.getItemMstId().trim(), "utf-8");
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }

            if (UserModel.Cust_mst_ID != null) {

                String cartWSurl = Constant.WS_BASE_URL + Constant.DELETE_CART + "0" + "/" + "Cart" + "/" + UserModel.Cust_mst_ID +
                        "/" + sku + "/" + resquantity +
                        "/" + Constant.STOREID + "/" + "0" + "/" + "add" + "/" + Constant.invType;

                TaskAddtoCart taskAddToCart = new TaskAddtoCart(this);
                taskAddToCart.executeOnExecutor(TaskAddtoCart.THREAD_POOL_EXECUTOR,cartWSurl);
//                taskAddToCart.execute(cartWSurl);
            } else {
                String cartWSurl = Constant.WS_BASE_URL + Constant.DELETE_CART + "0" + "/" + "Cart" + "/" + "0" +
                        "/" + sku + "/" + resquantity +
                        "/" + Constant.STOREID + "/" + DeviceInfo.getDeviceId(viewAllFragment.getContext()) + "0011" + "/" + "add" + "/" + Constant.invType;;

                TaskAddtoCart taskAddToCart = new TaskAddtoCart(this);
                taskAddToCart.executeOnExecutor(TaskAddtoCart.THREAD_POOL_EXECUTOR,cartWSurl);
//                taskAddToCart.execute(cartWSurl);
            }

        }
    }

    @Override
    public void addToCartEventResult(UpdateCartQuantity addToCart) {

        try {
            if (addToCart != null) {

                if (addToCart.getResult().equalsIgnoreCase("success")) {
                    DialogUtils.showDialog("Added to cart!");
                    Utils.vibrateDevice(getContext());
                    onGetCartData("", addToCart);
                    if (isFromadpter_whenclickedonaddtocart) {
                        isFromadpter_whenclickedonaddtocart = false;
                    }

                } else if (addToCart.getResult().equalsIgnoreCase("Already added")) {

                    if (isFromadpter_whenclickedonaddtocart) {

                        onGetCartData("Already added", addToCart);

                    }

//                if (isComeFomAddTocartBtn) {
//                    if (cartQtyOfItem.isEmpty()) {
//
//                        DialogUtils.notEnoughQuantityDialog(getActivity(), addToCart, requestedQty, "viewall", addToCart.getQty());
//                    } else {
//                        DialogUtils.notEnoughQuantityDialog(getActivity(), addToCart, requestedQty, "viewall", cartQtyOfItem);
//                    }
//                    isComeFomAddTocartBtn = false;
//                } else {
//                }

                } else if (addToCart.getResult().equalsIgnoreCase(
                        "Not enough Stock")) {
//                DialogUtils.notEnoughQuantityDialog(getActivity(), addToCart, requestedQty, "NotenoughStock", cartQtyOfItem);

//                DialogUtils.notEnoughQuantityNewDialog(getActivity(), addToCart, requestedQty, "NotenoughStock", cartQtyOfItem);


                    if (isFromadpter_whenclickedonaddtocart) {

                        onGetCartData("Not enough Stock", addToCart);
                    }

                } else {
//                     Toast.makeText(getContext(), getString(R.string.str_network_message), Toast.LENGTH_SHORT).show();
                }
            } else {
                Toast.makeText(getContext(), "Please try again later.", Toast.LENGTH_SHORT).show();
            }
        }catch (Exception e){
            Toast.makeText(getContext(), "Please try again later.", Toast.LENGTH_SHORT).show();
        }
    }

    public void callSearchIconWS(String searchText) {

        type = "allitems";

        if(searchText != null && !searchText.equals("")){
//            String Url = Constant.WS_BASE_URL + Constant.GET_INVENTORY_BY_FILTER + "/" +  Constant.STOREID + "/" + "0" + "/" + "0" + "/" + "0"
            String Url = Constant.WS_BASE_URL + Constant.GET_INVENTORY_BY_FILTER_NEW + "/" +  Constant.STOREID + "/" + "0" + "/" + "0" + "/" + "0"
                    + "/" + "0" + "/" + "0;0" + "/" + count + "/" + "12" + "/" + "allitems" + "/" + "Price" + "/" + "Asc" + "/" + "0" + "/" + "0"
                    + "/" + "0" + "/" + searchText ;
            TaskViewAll taskViewAll = new TaskViewAll(this,getActivity(),type);
            taskViewAll.executeOnExecutor(TaskViewAll.THREAD_POOL_EXECUTOR,Url);
//            taskViewAll.execute(Url);
        }else{
//            String Url = Constant.WS_BASE_URL + Constant.GET_INVENTORY_BY_FILTER + "/" +  Constant.STOREID + "/" + "0" + "/" + "0" + "/" + "0"
            String Url = Constant.WS_BASE_URL + Constant.GET_INVENTORY_BY_FILTER_NEW + "/" +  Constant.STOREID + "/" + "0" + "/" + "0" + "/" + "0"
                    + "/" + "0" + "/" + "0;0" + "/" + count + "/" + "12" + "/" + "allitems" + "/" + "Price" + "/" + "Asc" + "/" + "0" + "/" + "0"
                    + "/" + "0" + "/" + "0" ;
            TaskViewAll taskViewAll = new TaskViewAll(this,getActivity(),type);
            taskViewAll.executeOnExecutor(TaskViewAll.THREAD_POOL_EXECUTOR,Url);
//            taskViewAll.execute(Url);
        }

//        storeno + "/" + deptid + "/" + styleid + "/" + sizeid + "/" +
//                DiscGroup_id + "/" + pricerange + "/" + pageIndex + "/" +
//                RecordPerPage + "/" + selMaintype + "/" + SortColumn + "/" +
//                SortStatus + "/0/0" + "/" + IsItemWithImage + "/" + Filtertext;

    }

    public void callWSForSearchTextMoreData() {

        if (Constant.SCREEN_LAYOUT == 1) {

//                String Url = Constant.WS_BASE_URL + Constant.GET_INVENTORY_BY_FILTER + "/" + Constant.STOREID + "/" + "0" + "/" + "0" + "/" + "0"
                String Url = Constant.WS_BASE_URL + Constant.GET_INVENTORY_BY_FILTER_NEW + "/" + Constant.STOREID + "/" + "0" + "/" + "0" + "/" + "0"
                        + "/" + "0" + "/" + "0;0" + "/" + count + "/" + "12" + "/" + "allitems" + "/" + MainActivity.shortCall + "/" + MainActivity.shortDept + "/" + "0" + "/" + "0" + "/" + "0" + "/" + searchtext;

                Log.e("", "filterurl" + Url);
                TaskViewAll taskViewAll = new TaskViewAll(this, getActivity(), type);
                taskViewAll.executeOnExecutor(TaskViewAll.THREAD_POOL_EXECUTOR,Url);
//                taskViewAll.execute(Url);

        }else if (Constant.SCREEN_LAYOUT == 2) {

//                String Url = Constant.WS_BASE_URL + Constant.GET_INVENTORY_BY_FILTER + "/" + Constant.STOREID + "/" + "0" + "/" + "0" + "/" + "0"
                String Url = Constant.WS_BASE_URL + Constant.GET_INVENTORY_BY_FILTER_NEW + "/" + Constant.STOREID + "/" + "0" + "/" + "0" + "/" + "0"
                        + "/" + "0" + "/" + "0;0" + "/" + count + "/" + "12" + "/" + "allitems" + "/" + MainActivityDup.shortCall + "/" + MainActivityDup.shortDept + "/" + "0" + "/" + "0" + "/" + "0" + "/" + searchtext;

                Log.e("", "filterurl" + Url);
                TaskViewAll taskViewAll = new TaskViewAll(this, getActivity(), type);
                taskViewAll.executeOnExecutor(TaskViewAll.THREAD_POOL_EXECUTOR,Url);
//                taskViewAll.execute(Url);

            }

    }

    public void addTocartData(String requestedQtydata) {

        isFromadpter_whenclickedonaddtocart = true;

        this.requestedQty = Integer.parseInt(requestedQtydata);

        if (homeItemModel != null && !homeItemModel.getItemMstId().isEmpty()) {

            String sku = null;
            try {
                sku = URLEncoder.encode(homeItemModel.getItemMstId().trim(), "utf-8");
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }

            if (UserModel.Cust_mst_ID != null) {

                String cartWSurl = Constant.WS_BASE_URL + Constant.DELETE_CART + "0" + "/" + "Cart" + "/" + UserModel.Cust_mst_ID +
                        "/" + sku + "/" + requestedQty +
                        "/" + Constant.STOREID + "/" + "0" + "/" + "add" + "/" + Constant.invType;

                TaskAddtoCart taskAddToCart = new TaskAddtoCart(this);
                taskAddToCart.executeOnExecutor(TaskAddtoCart.THREAD_POOL_EXECUTOR,cartWSurl);
//                taskAddToCart.execute(cartWSurl);
            } else {

                String cartWSurl = Constant.WS_BASE_URL + Constant.DELETE_CART + "0" + "/" + "Cart" + "/" + "0" +
                        "/" + sku + "/" + requestedQty +
                        "/" + Constant.STOREID + "/" + DeviceInfo.getDeviceId(getActivity()) + "0011" + "/" + "add" + "/" + Constant.invType;;

                TaskAddtoCart taskAddToCart = new TaskAddtoCart(this);
                taskAddToCart.executeOnExecutor(TaskAddtoCart.THREAD_POOL_EXECUTOR,cartWSurl);
//                taskAddToCart.execute(cartWSurl);
            }

        }

    }

}
