package com.aspl.fragment;


import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import com.aspl.Adapter.ItemDetailAdapter;
import com.aspl.Adapter.OnlinePurchaseAdapter;
import com.aspl.Utils.Constant;
import com.aspl.Utils.DialogUtils;
import com.aspl.Utils.Utils;
import com.aspl.mbs.MainActivity;
import com.aspl.mbs.MainActivityDup;
import com.aspl.mbs.R;
import com.aspl.mbsmodel.InstorePurchaseDetailModel;
import com.aspl.mbsmodel.LightningOrderModel;
import com.aspl.mbsmodel.LstOrderTem;
import com.aspl.mbsmodel.OrderSummary;
import com.aspl.mbsmodel.PayWareModel;
import com.aspl.mbsmodel.ReOrderItemModel;
import com.aspl.mbsmodel.ReturnProcessModel;
import com.aspl.mbsmodel.ShippingData;
import com.aspl.mbsmodel.StoreLocationModel;
import com.aspl.mbsmodel.UserModel;
import com.aspl.task.TaskCancelOrder;
import com.aspl.task.TaskGetLightningOrders;
import com.aspl.task.TaskGetOrderItemDetailData;
import com.aspl.task.TaskGetOrderSummary;
import com.aspl.task.TaskGetPosCustomerID;
import com.aspl.task.TaskInstorePurchaseDetail;
import com.aspl.task.TaskOrderDetails;
import com.aspl.task.TaskOrderHistory;
import com.aspl.task.TaskReturnProcess;
import com.aspl.task.TaskUSAPayVoidTranscation;
import com.aspl.task.TaskVoidTranscation;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


/**
 * A simple {@link Fragment} subclass.
 */
public class OrderHistoryFragment extends Fragment implements TaskOrderHistory.TaskOrderHistoryEvent, TaskGetOrderSummary.TaskOrderSummaryEvent, TaskCancelOrder.TaskCancelOrderEvent, TaskVoidTranscation.TaskVoidTranscationEvent, TaskGetPosCustomerID.TaskGetPosCustomerIDEvent, TaskGetLightningOrders.TaskGetLightningOrdersEvent, TaskGetOrderItemDetailData.TaskGetOrderItemDetailDataEvent, TaskInstorePurchaseDetail.TaskInstorePurchaseDetailEvent, TaskReturnProcess.TaskReturnProcessEvent, TaskOrderDetails.TaskOrderDetailsEvent, TaskUSAPayVoidTranscation.TaskUSAPayVoidTranscationEvent {

    public static final String TAG = "orderhistoryfragment";
    public static OrderHistoryFragment orderHistoryFragment;
    String orderHistoryBtnText;
    Context context;
    LinearLayout llOnlinestore;
    View view_between_online_Instore,view_between_storepurchase_Item;
    Button btnOnlinePurchase,btnStorePurchase,btnItemDetails;
    String months[] = {"3 Months","6 Months","12 Months","All"};
    ArrayAdapter<String> spinnerArrayAdapter;
    ArrayAdapter<String> spinnerLocationArrayAdapter;
    Spinner spinner,spinnerLocation;
//    OnlinePurchaseAdapter onlinePurchaseAdapter;
    ItemDetailAdapter itemDetailAdapter;
    RecyclerView recyclerView;
    LinearLayoutManager layoutManager;
    String selected_month = "2";
    TextView tv_Norecord;
    String buttonclicked = "";
    public static SwipeRefreshLayout swipeRefreshLayout;
    int count = 1;
    int countItemDetail = 1;
    boolean loadItemsAlerady = false;
    boolean loadItemsAleradyItemDetail = false;
    boolean loadmore = false, isneedtoloadAgain = false;
    boolean loadmoreItemDetail = false, isneedtoloadAgainItemDetail = false;
    List<ReOrderItemModel> onlinePurchaseOrdersListing = new ArrayList<>();
    LinearLayout llmainlayout;
    List<LstOrderTem> lstOrderTemsList = new ArrayList<>();
    List<LstOrderTem> lstOrderTemsTempList = new ArrayList<>();
    OnlinePurchaseAdapter onlinePurchaseAdapter;
    OnlinePurchaseAdapter instorePurchaseAdapter;
    List<String> returnResonList = new ArrayList<>();
    LstOrderTem lstOrderTem_forstartReturn;
    String storeno_selected = Constant.STOREID;
    CardView cv_location;

    public OrderHistoryFragment() {
        // Required empty public constructor
    }

    public static OrderHistoryFragment getInstance() {
        return orderHistoryFragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        Constant.SELECTED_LOCATION_STOREID = Constant.STOREID;
        orderHistoryFragment = this;
        View view = inflater.inflate(R.layout.fragment_order_history, container, false);
        Bundle bundle = this.getArguments();
//        if (bundle != null) {
//            orderHistoryBtnText = bundle.getString("orderHistoryBtnText", "");
//        }

        if(!Constant.lastSelectedOrderDetailParent.isEmpty()){

            //remove below if condition for instore purchase selection from close
            orderHistoryBtnText = Constant.lastSelectedOrderDetailParent;
            Constant.lastSelectedOrderDetailParent = "";

        }else{
            if (bundle != null) {
                orderHistoryBtnText = bundle.getString("orderHistoryBtnText", "");
            }
        }
        return view;
    }


    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        //save the activity to a member of this fragment
        context = activity;
    }

    @Override
    public void onResume() {
        super.onResume();

        if(Constant.SCREEN_LAYOUT == 2){
            MainActivityDup.getInstance().showbackButton();
            MainActivityDup.getInstance().RecHorizontalList.setVisibility(View.GONE);
        }

        if (Constant.SCREEN_LAYOUT == 1) {

            MainActivity.getInstance().llsearch.setVisibility(View.VISIBLE);

////            ************* Edited by Varun for backbutton ****************
//
            MainActivity.getInstance().ll_backbutton.setVisibility(View.VISIBLE);
//            if (Constant.isloyaltyreward){
//                MainActivity.getInstance().ll_Reward_main.setVisibility(View.VISIBLE);
//            }
//            MainActivity.getInstance().ll_Reward_main.setVisibility(View.VISIBLE);
//
////            *************END******************

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
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        cv_location = (CardView) view.findViewById(R.id.cv_location);
        llmainlayout = (LinearLayout) view.findViewById(R.id.llMainLayout);
        llmainlayout.setBackgroundColor(Color.parseColor(Constant.themeModel.Backgroundcolor));

        spinner = (Spinner) view.findViewById(R.id.spinner);
        spinnerArrayAdapter = new ArrayAdapter<String>(Objects.requireNonNull(getActivity()), R.layout.row_ordermonths, R.id.textview, months);

//        spinnerArrayAdapter.setDropDownViewResource(R.layout.row_ordermonths);
        spinner.setAdapter(spinnerArrayAdapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> arg0, View view, int arg2, long arg3) {

                selected_month = "";
                selected_month = spinner.getSelectedItem().toString();

//                if(selected_month.contains("Months")){
//
//                    String[] str = selected_month.split(" ");
//                    String strMon = str[0];
//                    selected_month = strMon;
//                }

                count = 1;
                countItemDetail = 1;

                if (selected_month.contains("3")) {
                    selected_month = "2";
                } else if (selected_month.contains("6")) {
                    selected_month = "5";
                } else if (selected_month.contains("12")) {
                    selected_month = "11";
                } else if (selected_month.contains("All")) {
                    selected_month = "1";
                }

                if (buttonclicked.equalsIgnoreCase("Online Purchases")) {
                    callOnlineOrderHistory(true);

                } else if (buttonclicked.equalsIgnoreCase("Instore Purchases")) {
                    callGetPosCustomerID(true);

                } else if (buttonclicked.equalsIgnoreCase("Item Details")) {
                    callOrderItemDetail(true);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
                // TODO Auto-generated method stub
            }
        });


        spinnerLocation = (Spinner) view.findViewById(R.id.spinnerLocation);

//        ******************** Edited by Varun for store location *************************
//        for store location spinner
        if (Constant.co_storeno_value != null && !Constant.co_storeno_value.isEmpty()) {
            if (Constant.storeLocationList != null && Constant.storeLocationList.size() > 0) {

                ArrayList<String> storeList = new ArrayList<>();
                ArrayList<String> storeListfinal = new ArrayList<>();
                Constant.storeLocationListfinal.clear();

                for (int i = 0; i < Constant.storeLocationList.size(); i++) {

                    String add = "";
                    if (Constant.storeLocationList.get(i).getAddress() != null && !Constant.storeLocationList.get(i).getAddress().isEmpty()) {
                        add = add + Constant.storeLocationList.get(i).getAddress();
                    }

                    if (Constant.storeLocationList.get(i).getCity() != null && !Constant.storeLocationList.get(i).getCity().isEmpty()) {
                        add = add + ", " + Constant.storeLocationList.get(i).getCity();
                    }

                    if (Constant.storeLocationList.get(i).getSt() != null && !Constant.storeLocationList.get(i).getSt().isEmpty()) {
                        add = add + ", " + Constant.storeLocationList.get(i).getSt();
                    }
                    if (Constant.STOREID.equals(Constant.storeLocationList.get(i).getStoreno())) {
                        storeListfinal.add(add);
                        Constant.storeLocationListfinal.add(Constant.storeLocationList.get(i));
                    } else {
                        storeList.add(add);
                        Constant.storeLocationListtemp.add(Constant.storeLocationList.get(i));
                    }
                }
                if (storeListfinal.size() != Constant.storeLocationList.size()) {
                    storeListfinal.addAll(storeList);
                }
                if (Constant.storeLocationList.size() != Constant.storeLocationListfinal.size()) {
                    Constant.storeLocationListfinal.addAll(Constant.storeLocationListtemp);
                }
                spinnerLocationArrayAdapter = new ArrayAdapter<String>(Objects.requireNonNull(getActivity()), R.layout.row_ordermonths, R.id.textview, storeListfinal);
                spinnerLocation.setVisibility(View.VISIBLE);
                spinnerLocation.setAdapter(spinnerLocationArrayAdapter);
            }
        } else {
            spinnerLocation.setVisibility(View.GONE);
        }

//         ********************** END *************************
//        if(Constant.co_storeno_value != null && !Constant.co_storeno_value.isEmpty()) {
//        if(Constant.storeLocationList!= null && Constant.storeLocationList.size() > 0){
//
//            ArrayList<String> storeList = new ArrayList<>();
//
//            for(int i =0;i<Constant.storeLocationList.size(); i++){
//
//                String add = "";
//                if(Constant.storeLocationList.get(i).getAddress() != null && !Constant.storeLocationList.get(i).getAddress().isEmpty()){
//                    add = add + Constant.storeLocationList.get(i).getAddress();
//                }
//
//                if(Constant.storeLocationList.get(i).getCity() != null && !Constant.storeLocationList.get(i).getCity().isEmpty()){
//                    add = add + ", " + Constant.storeLocationList.get(i).getCity();
//                }
//
//                if(Constant.storeLocationList.get(i).getSt() != null && !Constant.storeLocationList.get(i).getSt().isEmpty()){
//                    add = add + ", " + Constant.storeLocationList.get(i).getSt();
//                }
//
//                storeList.add(add);
//            }
//            spinnerLocationArrayAdapter = new ArrayAdapter<String>(Objects.requireNonNull(getActivity()), R.layout.row_ordermonths,R.id.textview, storeList);
//            spinnerLocation.setVisibility(View.VISIBLE);
//            spinnerLocation.setAdapter(spinnerLocationArrayAdapter);
//        }else{
//            spinnerLocation.setVisibility(View.GONE);
//        }
//    }

        spinnerLocation.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                if(Constant.storeLocationListfinal.get(position).getStoreno() != null && !Constant.storeLocationListfinal.get(position).getStoreno().isEmpty()){
                    storeno_selected = Constant.storeLocationListfinal.get(position).getStoreno();
                    Constant.SELECTED_LOCATION_STOREID = storeno_selected;
                }

                if(buttonclicked.equalsIgnoreCase("Online Purchases")){
                    callOnlineOrderHistory(true);
                } else if(buttonclicked.equalsIgnoreCase("Instore Purchases")){
                    callGetPosCustomerID(true);
                } else if(buttonclicked.equalsIgnoreCase("Item Details")){
                    callOrderItemDetail(true);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        recyclerView = (RecyclerView)view.findViewById(R.id.recyclerView1);
        layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);

        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
                @Override
                public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
//                super.onScrollStateChanged(recyclerView, newState);
                }

                @Override
                public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
//                super.onScrolled(recyclerView, dx, dy);

                    if(buttonclicked.equalsIgnoreCase("Online Purchases")){
                        onlinePurchasePagination();
                    }else if(buttonclicked.equalsIgnoreCase("Instore Purchases")){

                    }else if(buttonclicked.equalsIgnoreCase("Item Details")){
                        itemDetailOrdersPagination();
                    }
                }
            });

        swipeRefreshLayout = view.findViewById(R.id.swipe_to_refresh);

        swipeRefreshLayout.setOnRefreshListener(
                new SwipeRefreshLayout.OnRefreshListener() {
                    @Override
                    public void onRefresh() {

                        if(buttonclicked.equalsIgnoreCase("Online Purchases")){
                            callOnlineOrderHistory(false);
                        }else if(buttonclicked.equalsIgnoreCase("Instore Purchases")){
                            callGetPosCustomerID(false);
                        }else if(buttonclicked.equalsIgnoreCase("Item Details")){
                            countItemDetail = 1;
                            callOrderItemDetail(false);
                        }
                    }
                }
        );

        tv_Norecord = (TextView) view.findViewById(R.id.tv_Norecord);
        llOnlinestore = (LinearLayout) view.findViewById(R.id.llOnlinestore);

        btnOnlinePurchase = (Button) view.findViewById(R.id.btnOnlinePurchase);
        btnStorePurchase = (Button) view.findViewById(R.id.btnStorePurchase);
        btnItemDetails = (Button) view.findViewById(R.id.btnItemDetails);

        view_between_storepurchase_Item = (View)view.findViewById(R.id.view_between_storepurchase_Item);
        view_between_online_Instore = (View)view.findViewById(R.id.view_between_online_Instore);
        view_between_online_Instore.setBackgroundColor(Color.parseColor(Constant.themeModel.ThemeColor));
        view_between_storepurchase_Item.setBackgroundColor(Color.parseColor(Constant.themeModel.ThemeColor));

        if(!orderHistoryBtnText.isEmpty()){
            if(orderHistoryBtnText.equals("Online Purchases")){

                count = 1;
                GradientDrawable shape = new GradientDrawable();
                shape.setShape(GradientDrawable.RECTANGLE);
                shape.setCornerRadii(new float[]{8, 8, 8, 8, 8, 8, 8, 8});
                shape.setColor(Color.parseColor(Constant.themeModel.ThemeColor));
                btnOnlinePurchase.setBackgroundDrawable(shape);
                btnOnlinePurchase.setTextColor(getResources().getColor(R.color.androidWhite));
                btnStorePurchase.setTextColor(Color.parseColor(Constant.themeModel.ThemeColor));
                btnStorePurchase.setBackgroundColor(getResources().getColor(R.color.transparent));
                btnItemDetails.setTextColor(Color.parseColor(Constant.themeModel.ThemeColor));
                btnItemDetails.setBackgroundColor(getResources().getColor(R.color.transparent));

                llOnlinestore = (LinearLayout) view.findViewById(R.id.llOnlinestore);

                GradientDrawable bgShape51 = (GradientDrawable) llOnlinestore.getBackground();
                bgShape51.setStroke(2, Color.parseColor(Constant.themeModel.ThemeColor));
//
//                view_between_storepurchase_Item.setVisibility(View.VISIBLE);
//                view_between_online_Instore.setVisibility(View.GONE);
                callOnlineOrderHistory(true);

                buttonclicked = "Online Purchases";

            }else if(orderHistoryBtnText.equals("Instore Purchases")){

                llOnlinestore = (LinearLayout) view.findViewById(R.id.llOnlinestore);

                GradientDrawable bgShape11 = (GradientDrawable) llOnlinestore.getBackground();
                bgShape11.setStroke(2, Color.parseColor(Constant.themeModel.ThemeColor));
                setStorePurchases_ThemeColorBgWithRadius();

                buttonclicked = "Instore Purchases";

            }else if(orderHistoryBtnText.equals("Item Details")){

                llOnlinestore = (LinearLayout) view.findViewById(R.id.llOnlinestore);

                GradientDrawable bgShape1 = (GradientDrawable) llOnlinestore.getBackground();
                bgShape1.setStroke(2, Color.parseColor(Constant.themeModel.ThemeColor));
                setItemDetails_ThemeColorBgWithRadius();

                buttonclicked = "Item Details";
            }

        }

        btnOnlinePurchase.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                swipeRefreshLayout.setRefreshing(false);

                setOnlinePurchase_ThemeColorBgWithRadius();

                view_between_storepurchase_Item.setVisibility(View.VISIBLE);
                view_between_online_Instore.setVisibility(View.GONE);

                buttonclicked = "Online Purchases";

                callOnlineOrderHistory(true);
            }
        });


        btnItemDetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                swipeRefreshLayout.setRefreshing(false);

                countItemDetail = 1;

                setItemDetails_ThemeColorBgWithRadius();

                view_between_storepurchase_Item.setVisibility(View.GONE);
                view_between_online_Instore.setVisibility(View.VISIBLE);

                buttonclicked = "Item Details";

                callOrderItemDetail(true);
            }
        });


        btnStorePurchase.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//
                swipeRefreshLayout.setRefreshing(false);

                buttonclicked = "Instore Purchases";

                setStorePurchases_ThemeColorBgWithRadius();

                callGetPosCustomerID(true);
            }
        });
    }

    private void itemDetailOrdersPagination() {

        if (isneedtoloadAgainItemDetail) {

            int visibleItemCount = layoutManager.getChildCount();
            int totalItemCount = layoutManager.getItemCount();
            int firstVisibleItemPosition = layoutManager.findFirstVisibleItemPosition();
            if (loadmoreItemDetail) {
                if ((visibleItemCount + firstVisibleItemPosition) >= totalItemCount
                        && firstVisibleItemPosition >= 0) {
                    countItemDetail = countItemDetail + 1;
                    loadmoreItemDetail = false;
                    callOrderItemDetail(true);
                }
            }
        }
    }

    private void onlinePurchasePagination() {

        if (isneedtoloadAgain) {
//                        int lastVisiblePosition = layoutManager.findLastVisibleItemPosition();
//                        if (lastVisiblePosition == 11 && loadmore) {/*viewalllist.size-1 */
////                            if (loadmore) {
//                            count = count + 1;
//                            loadmore = false;
//                        }

            int visibleItemCount = layoutManager.getChildCount();
            int totalItemCount = layoutManager.getItemCount();
            int firstVisibleItemPosition = layoutManager.findFirstVisibleItemPosition();
            if (loadmore) {
                if ((visibleItemCount + firstVisibleItemPosition) >= totalItemCount
                        && firstVisibleItemPosition >= 0) {
                    count = count + 1;
                    loadmore = false;
                    callOnlineOrderHistory(true);
                }
            }

        }
    }

    public void callOrderItemDetail(boolean showProgressbar) {

//        String Url = Constant.WS_BASE_URL + Constant.GET_ORDER_ITEM_DETAIL_DATA  + "/" +  UserModel.Cust_mst_ID + "/" + Constant.STOREID + "/" + countItemDetail + "/" + "12" ;
        String Url = Constant.WS_BASE_URL + Constant.GET_ORDER_ITEM_DETAIL_DATA  + "/" +  UserModel.Cust_mst_ID + "/" + storeno_selected + "/" + countItemDetail + "/" + "12" ;

        TaskGetOrderItemDetailData taskGetOrderItemDetailData = new TaskGetOrderItemDetailData(getActivity(),this, showProgressbar);
        taskGetOrderItemDetailData.execute(Url);
    }


    @Override
    public void onTaskGetOrderItemDetailDataResult(OrderSummary orderSummary) {

        swipeRefreshLayout.setRefreshing(false);

        if(orderSummary != null && orderSummary.getLstOrderTems() != null && orderSummary.getLstOrderTems().size() > 0){

            lstOrderTemsList = orderSummary.getLstOrderTems();

            tv_Norecord.setVisibility(View.GONE);
            recyclerView.setVisibility(View.VISIBLE);

            loadItemsAleradyItemDetail = true;

            if (lstOrderTemsList.size() >= 12) {
                isneedtoloadAgainItemDetail = true;
                loadmoreItemDetail = true;
            } else {
                isneedtoloadAgainItemDetail = false;
                loadmoreItemDetail = false;
            }

            if (countItemDetail == 1 && lstOrderTemsList != null && lstOrderTemsList.size() > 0) {
                lstOrderTemsTempList = lstOrderTemsList;

                itemDetailAdapter = new ItemDetailAdapter(getActivity(), lstOrderTemsList,storeno_selected);
                recyclerView.setAdapter(itemDetailAdapter);

            } else {

                int startpos = lstOrderTemsTempList.size();
                int newItemcount = lstOrderTemsList.size();
                lstOrderTemsTempList.addAll(lstOrderTemsList);
                itemDetailAdapter.notifyItemRangeInserted(startpos, newItemcount);
            }

        } else{
            tv_Norecord.setVisibility(View.VISIBLE);
            recyclerView.setVisibility(View.GONE);
        }

    }

    private void callGetPosCustomerID(boolean showProgressbar) {

        if(UserModel.Cust_mst_ID != null){
//            String url = Constant.WS_BASE_URL + Constant.GET_POS_CUSTOMER_ID + "/" + UserModel.Cust_mst_ID + "/" + Constant.STOREID;
            String url = Constant.WS_BASE_URL + Constant.GET_POS_CUSTOMER_ID + "/" + UserModel.Cust_mst_ID + "/" + storeno_selected;
            TaskGetPosCustomerID taskGetPosCustomerID = new TaskGetPosCustomerID(this,showProgressbar);
            taskGetPosCustomerID.execute(url);
        }else{
//            String url = Constant.WS_BASE_URL + Constant.GET_POS_CUSTOMER_ID + "/" + 0 + "/" + Constant.STOREID;
            String url = Constant.WS_BASE_URL + Constant.GET_POS_CUSTOMER_ID + "/" + 0 + "/" + storeno_selected;
            TaskGetPosCustomerID taskGetPosCustomerID = new TaskGetPosCustomerID(this,showProgressbar);
            taskGetPosCustomerID.execute(url);
        }
    }


    @Override
    public void onGetPosCustomerId(ShippingData shippingData, boolean showProgressbar) {

        if(shippingData != null){
             if(shippingData.getResult() != null && !shippingData.getResult().isEmpty()){
                 callGetLightningOrders(shippingData.getResult(),showProgressbar);
             }
        }
    }

    private void callGetLightningOrders(String result, boolean showProgressbar) {

        String poscusId = result;

        if(poscusId != null && poscusId.equals("0")){

                swipeRefreshLayout.setRefreshing(false);

                tv_Norecord.setVisibility(View.VISIBLE);
                recyclerView.setVisibility(View.GONE);

        }else{

            if(result != null && !poscusId.equalsIgnoreCase("null")){

//                String url = Constant.WS_BASE_URL + Constant.GET_LIGHTNING_ORDERS + "/" + poscusId + "/" + Constant.STOREID + "/" + "1" + "/" + "12" +"/" + "invoice_date"
                String url = Constant.WS_BASE_URL + Constant.GET_LIGHTNING_ORDERS + "/" + poscusId + "/" + storeno_selected + "/" + "1" + "/" + "12" +"/" + "invoice_date"
                        +"/" + "desc" +"/" + selected_month;
                TaskGetLightningOrders taskGetLightningOrders = new TaskGetLightningOrders(context,this,showProgressbar);
                taskGetLightningOrders.execute(url);
            }
        }

//        if(poscusId != null){
//            String url = Constant.WS_BASE_URL + Constant.GET_LIGHTNING_ORDERS + "/" + poscusId + "/" + Constant.STOREID + "/" + "1" + "/" + "12" +"/" + "invoice_date"
//                    +"/" + "desc" +"/" + selected_month;
//            TaskGetLightningOrders taskGetLightningOrders = new TaskGetLightningOrders(context,this,showProgressbar);
//            taskGetLightningOrders.execute(url);
//        }

//        else{
//            String url = Constant.WS_BASE_URL + Constant.GET_LIGHTNING_ORDERS + "/" + 0 + "/" + Constant.STOREID + "/" + "1" + "/" + "12" +"/" + "invoice_date" +"/" + "desc" +"/" + selected_month;
//            TaskGetLightningOrders taskGetLightningOrders = new TaskGetLightningOrders(context,this,showProgressbar);
//            taskGetLightningOrders.execute(url);
//        }
    }


    @Override
    public void onTaskGetLightningOrdersResult(List<LightningOrderModel> lightningOrderList) {

        swipeRefreshLayout.setRefreshing(false);

//        instorepurchase

        if(lightningOrderList != null && lightningOrderList.size() > 0){
            tv_Norecord.setVisibility(View.GONE);
            recyclerView.setVisibility(View.VISIBLE);

            instorePurchaseAdapter = new OnlinePurchaseAdapter(getActivity(), lightningOrderList,"InstorePurchase");
            recyclerView.setAdapter(instorePurchaseAdapter);

        }else{
            tv_Norecord.setVisibility(View.VISIBLE);
            recyclerView.setVisibility(View.GONE);
        }

    }

    private void callOnlineOrderHistory(boolean showProgressloader) {

//        String Url = Constant.WS_BASE_URL + Constant.GET_ORDERHISTORY_DATA +  UserModel.Cust_mst_ID + "/" + Constant.STOREID + "/" + count + "/" + "12" + "/" + selected_month;
        String Url = Constant.WS_BASE_URL + Constant.GET_ORDERHISTORY_DATA +  UserModel.Cust_mst_ID + "/" + storeno_selected + "/" + count + "/" + "12" + "/" + selected_month;

        TaskOrderHistory taskOrderHistory = new TaskOrderHistory(this,getActivity(),"", showProgressloader);
        taskOrderHistory.execute(Url);
    }


    @Override
    public void onGetOrderHistoryResult(List<ReOrderItemModel> onlinePurchaseOrderList, String type) {

        swipeRefreshLayout.setRefreshing(false);

        if(onlinePurchaseOrderList != null && onlinePurchaseOrderList.size() > 0){

            tv_Norecord.setVisibility(View.GONE);
            recyclerView.setVisibility(View.VISIBLE);

            loadItemsAlerady = true;

            if (onlinePurchaseOrderList.size() >= 12) {
                isneedtoloadAgain = true;
                loadmore = true;
            } else {
                isneedtoloadAgain = false;
                loadmore = false;
            }

            if (count == 1 && onlinePurchaseOrderList != null && onlinePurchaseOrderList.size() > 0) {
                onlinePurchaseOrdersListing = onlinePurchaseOrderList;

                onlinePurchaseAdapter = new OnlinePurchaseAdapter(getActivity(), onlinePurchaseOrdersListing);
                recyclerView.setAdapter(onlinePurchaseAdapter);

            } else {

//                int startpos = onlinePurchaseOrdersListing.size();
//                int newItemcount = onlinePurchaseOrderList.size();
//                onlinePurchaseOrdersListing.addAll(onlinePurchaseOrderList);
//                onlinePurchaseAdapter.notifyItemRangeInserted(startpos, newItemcount);

//                int startpos = onlinePurchaseOrdersListing.size();
//                int newItemcount = onlinePurchaseOrderList.size();

                onlinePurchaseOrdersListing.addAll(onlinePurchaseOrderList);
                onlinePurchaseAdapter = new OnlinePurchaseAdapter(getActivity(), onlinePurchaseOrdersListing);
                recyclerView.setAdapter(onlinePurchaseAdapter);
            }

//            recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
//                @Override
//                public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
////                super.onScrollStateChanged(recyclerView, newState);
//                }
//
//                @Override
//                public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
////                super.onScrolled(recyclerView, dx, dy);
//
//                    if (isneedtoloadAgain) {
////                        int lastVisiblePosition = layoutManager.findLastVisibleItemPosition();
////                        if (lastVisiblePosition == 11 && loadmore) {/*viewalllist.size-1 */
//////                            if (loadmore) {
////                            count = count + 1;
////                            loadmore = false;
////                        }
//
//                        int visibleItemCount = layoutManager.getChildCount();
//                        int totalItemCount = layoutManager.getItemCount();
//                        int firstVisibleItemPosition = layoutManager.findFirstVisibleItemPosition();
//                        if (loadmore) {
//                            if ((visibleItemCount + firstVisibleItemPosition) >= totalItemCount
//                                    && firstVisibleItemPosition >= 0) {
//                                count = count + 1;
//                            loadmore = false;
//                                callOnlineOrderHistory(true);
//                            }
//                        }
//
//                    }
//
//                }
//            });

        }else{

            tv_Norecord.setVisibility(View.VISIBLE);
            recyclerView.setVisibility(View.GONE);
        }
    }


    public void callOrderSummaryResultWebService(String orderId, String fromwhere) {
        String orderSummaryUrl;

//        orderSummaryUrl = Constant.WS_BASE_URL + Constant.GET_ORDER_SUMMARY_DETAIL
//                + orderId + "/" + Constant.STOREID;

        orderSummaryUrl = Constant.WS_BASE_URL + Constant.GET_ORDER_SUMMARY_DETAIL
                + orderId + "/" + storeno_selected;

        TaskGetOrderSummary orderSummary = new TaskGetOrderSummary(getActivity(),this,true,fromwhere);
        orderSummary.execute(orderSummaryUrl);
    }

    @Override
    public void onOrderSummaryResult(OrderSummary orderSummary, String fromwhere) {

        if(orderSummary != null){
            if(!fromwhere.isEmpty()&& fromwhere.equalsIgnoreCase("ReturnProcessing")){
                //for return invoice

                Bundle bundle = new Bundle();
                Constant.orderSummaryTemp = null;
                Constant.orderSummaryTemp = orderSummary;
                bundle.putBoolean("isFromOrderdatail",true);
                bundle.putString("buttonclicked",buttonclicked);
                bundle.putBoolean("ReturnProcessing",true);

                if(Constant.SCREEN_LAYOUT == 1) {
                    MainActivity.getInstance().loadOrderSummaryFragment(bundle, "comefromOrderDetail", buttonclicked, "ReturnProcessing");
                }else if(Constant.SCREEN_LAYOUT == 2){
                    MainActivityDup.getInstance().loadOrderSummaryFragment(bundle, "comefromOrderDetail", buttonclicked, "ReturnProcessing");
                }
            }else{
//                showOrderDetailDialog(getActivity(),orderSummary);
                Bundle bundle = new Bundle();
                Constant.orderSummaryTemp = null;
                Constant.orderSummaryTemp = orderSummary;
                bundle.putBoolean("isFromOrderdatail",true);
                bundle.putString("buttonclicked",buttonclicked);
                bundle.putBoolean("ReturnProcessing",false);

                if(Constant.SCREEN_LAYOUT == 1) {
                    MainActivity.getInstance().loadOrderSummaryFragment(bundle, "comefromOrderDetail", buttonclicked, "ReturnProcessing");
                }else if(Constant.SCREEN_LAYOUT == 2) {
                    MainActivityDup.getInstance().loadOrderSummaryFragment(bundle, "comefromOrderDetail", buttonclicked, "ReturnProcessing");
                }
            }
        }
    }

    public void callCancelOrderWS(String orderid) {

        String orderId = orderid;
        String cancelOderUrl;

        if(orderId != null && !orderId.isEmpty()){

//            cancelOderUrl = Constant.WS_BASE_URL + Constant.GET_CANCEL_ORDER
//                    + orderId + "/" + Constant.STOREID;
//
            cancelOderUrl = Constant.WS_BASE_URL + Constant.GET_CANCEL_ORDER
                    + orderId + "/" + storeno_selected;

            TaskCancelOrder taskCancelOrder = new TaskCancelOrder(getActivity(),this);
            taskCancelOrder.execute(cancelOderUrl);
        }
    }

    @Override
    public void onTaskCancelOrderResult(OrderSummary orderSummary) {

        if(orderSummary != null && orderSummary.getResult()!=null && !orderSummary.getResult().isEmpty()) {

            String[] result = orderSummary.getResult().split("/");
            String resMSg = result[0];

            if (resMSg.equalsIgnoreCase(context.getResources().getString(R.string.successMsg))) {
                if(Utils.cancelDialog != null && Utils.cancelDialog.isShowing()){
                    Utils.cancelDialog.dismiss();

                    if (getFragmentManager() != null && getFragmentManager().getBackStackEntryCount() != 0) {
                        getFragmentManager().popBackStack();
                    }
                }
            }
        }
    }


    public void callVoidTransactionOrderWS(String orderId, OrderSummary orderSummaryTemp) {

        String voidTransactionUrl;

        if(orderId != null && !orderId.isEmpty()){

            String weborderId = orderSummaryTemp.getOrderID();

            if(weborderId != null && !weborderId.equalsIgnoreCase("null")&& !weborderId.isEmpty()){

//                voidTransactionUrl = Constant.WS_BASE_URL + Constant.VoidTranscation
//                        + orderId + "/" + Constant.STOREID + "/" + weborderId;

                voidTransactionUrl = Constant.WS_BASE_URL + Constant.VoidTranscation
                        + orderId + "/" + storeno_selected + "/" + weborderId;

                TaskVoidTranscation taskVoidTranscation= new TaskVoidTranscation(getActivity(),this,false, orderId);
                taskVoidTranscation.execute(voidTransactionUrl);
            }
        }
    }


    @Override
    public void onTaskVoidTranscationResult(PayWareModel payWareModel, Boolean isRequestResponse, String orderId) {

        if(!isRequestResponse){ // void transcation ws

            if(payWareModel!= null && payWareModel.getSuccessMessage()!= null
                    && !payWareModel.getSuccessMessage().toString().isEmpty()
                    && !payWareModel.getSuccessMessage().toString().equalsIgnoreCase("null")){

                if(payWareModel.getSuccessMessage().toString().equalsIgnoreCase("7")){

                    //callcancelorderWs
                    callCancelOrderWS(orderId);

                }else{

                    callRequestResponseWS(payWareModel);
                }
            }
        }else{
            //requestResponseWs's response

//            like success or which nsg used to hide cancel order dialog

            Utils.showRequestResponseDialog(context,payWareModel);

        }

    }

    private void callRequestResponseWS(PayWareModel payWareModel) {

        String logid = payWareModel.getLogID().toString();
        String requestResponseUrl = "";

        if(logid != null && !logid.equalsIgnoreCase("null")&& !logid.isEmpty()){

            requestResponseUrl = Constant.WS_BASE_URL + Constant.RequestResponse + logid;

            TaskVoidTranscation taskVoidTranscation= new TaskVoidTranscation(getActivity(),this,true, "");
            taskVoidTranscation.execute(requestResponseUrl);
        }
    }


    private void setItemDetails_ThemeColorBgWithRadius() {

        GradientDrawable shape = new GradientDrawable();
        shape.setShape(GradientDrawable.RECTANGLE);
        shape.setCornerRadii(new float[]{8, 8, 0, 0, 0, 0, 8, 8});
        shape.setColor(Color.parseColor(Constant.themeModel.ThemeColor));
        btnItemDetails.setBackgroundDrawable(shape);
        btnItemDetails.setTextColor(getResources().getColor(R.color.androidWhite));
        btnOnlinePurchase.setTextColor(Color.parseColor(Constant.themeModel.ThemeColor));
        btnOnlinePurchase.setBackgroundColor(getResources().getColor(R.color.transparent));
        btnStorePurchase.setTextColor(Color.parseColor(Constant.themeModel.ThemeColor));
        btnStorePurchase.setBackgroundColor(getResources().getColor(R.color.transparent));

    }

    private void setStorePurchases_ThemeColorBgWithRadius() {

        GradientDrawable shape = new GradientDrawable();
        shape.setShape(GradientDrawable.RECTANGLE);
        shape.setCornerRadii(new float[]{8, 8, 0, 0, 0, 0, 8, 8});
        shape.setColor(Color.parseColor(Constant.themeModel.ThemeColor));
        btnStorePurchase.setBackgroundDrawable(shape);
        btnStorePurchase.setTextColor(getResources().getColor(R.color.androidWhite));
        btnOnlinePurchase.setTextColor(Color.parseColor(Constant.themeModel.ThemeColor));
        btnOnlinePurchase.setBackgroundColor(getResources().getColor(R.color.transparent));
        btnItemDetails.setTextColor(Color.parseColor(Constant.themeModel.ThemeColor));
        btnItemDetails.setBackgroundColor(getResources().getColor(R.color.transparent));

    }


    private void setOnlinePurchase_ThemeColorBgWithRadius() {

        GradientDrawable shape = new GradientDrawable();
        shape.setShape(GradientDrawable.RECTANGLE);
        shape.setCornerRadii(new float[]{8, 8, 8, 8, 8, 8, 8, 8});
        shape.setColor(Color.parseColor(Constant.themeModel.ThemeColor));
        btnOnlinePurchase.setBackgroundDrawable(shape);
        btnOnlinePurchase.setTextColor(getResources().getColor(R.color.androidWhite));
        btnStorePurchase.setTextColor(Color.parseColor(Constant.themeModel.ThemeColor));
        btnStorePurchase.setBackgroundColor(getResources().getColor(R.color.transparent));
        btnItemDetails.setTextColor(Color.parseColor(Constant.themeModel.ThemeColor));
        btnItemDetails.setBackgroundColor(getResources().getColor(R.color.transparent));
    }


    public void callInstorePurchaseDetail(String invoiceNo, int position) {

        String lightningOrderDetailUrl;

//        lightningOrderDetailUrl = Constant.WS_BASE_FOR_POS_LIGHTNING + Constant.GetLightningOrdersDetails_Json
//                +"&storeNo=" + Constant.STOREID + "&InvoiceNO="  + invoiceNo ;
//
        lightningOrderDetailUrl = Constant.WS_BASE_FOR_POS_LIGHTNING + Constant.GetLightningOrdersDetails_Json
                +"&storeNo=" + storeno_selected + "&InvoiceNO="  + invoiceNo ;

        TaskInstorePurchaseDetail taskInstorePurchaseDetail = new TaskInstorePurchaseDetail(getActivity(),this,position);
        taskInstorePurchaseDetail.execute(lightningOrderDetailUrl);
    }

    @Override
    public void onInstorePurchaseDetailResult(List<InstorePurchaseDetailModel> liInstorePurchaseModelList, int positi) {

        if(liInstorePurchaseModelList != null && liInstorePurchaseModelList.size() > 0){

                Bundle bundle = new Bundle();
                Constant.instorePurchase_detailList.clear();
                Constant.instorePurchase_detailList = liInstorePurchaseModelList;
                bundle.putBoolean("isFromOrderdatail",true);
                bundle.putBoolean("isFromInstoreOrderDetail",true);
                bundle.putString("buttonclicked",buttonclicked);
                MainActivity.getInstance().loadOrderSummaryFragment(bundle,"comefromOrderDetail",buttonclicked, "ReturnProcessing");
        }
    }

    public void callReturnProcessWs(LstOrderTem lstOrderTem) {

        lstOrderTem_forstartReturn = lstOrderTem;

//        String Url1 = Constant.WS_BASE_URL + Constant.GET_RETURNPROCESS + Constant.STOREID;
        String Url1 = Constant.WS_BASE_URL + Constant.GET_RETURNPROCESS + storeno_selected;
        TaskReturnProcess taskReturnProcess = new TaskReturnProcess(this);
        taskReturnProcess.execute(Url1);
    }

    @Override
    public void onGetReturnProcessResult(List<ReturnProcessModel> returnProcessModelList) {

        returnResonList.clear();

        returnResonList.add(0,"Choose a response");

        if(returnProcessModelList != null && returnProcessModelList.size() > 0){

            for(int i=0;i<returnProcessModelList.size(); i++){
                if(returnProcessModelList.get(i).getFlage() != null && returnProcessModelList.get(i).getFlage()) {
                    returnResonList.add(returnProcessModelList.get(i).getActualReason());
                }
            }
        }
        DialogUtils.showStartReturnDialog(getActivity(),returnResonList,lstOrderTem_forstartReturn);
    }

    public void callOrderDetailWS(String orderId) {

        if(orderId != null && !orderId.isEmpty()){

            if(orderId != null && !orderId.equalsIgnoreCase("null")&& !orderId.isEmpty()){

                String Url = Constant.WS_BASE_URL + Constant.GETORDER_DETAILS
                        + orderId + "/" + storeno_selected;

                TaskOrderDetails taskOrderDetails= new TaskOrderDetails(this,context,orderId);
                taskOrderDetails.execute(Url);
            }
        }
    }

    @Override
    public void onGetOrderDetailsResult(PayWareModel payWareModel, String orderID) {

        if(payWareModel != null && payWareModel.getTROUTD() != null && !payWareModel.getTROUTD().toString().isEmpty()){
            OrderHistoryFragment.getInstance().callUSAePayVoidTransactionOrderWS(orderID,payWareModel.getTROUTD().toString());
        }
    }

    private void callUSAePayVoidTransactionOrderWS(String orderID, String troutid) {

        String voidTransactionUrl;

        if(orderID != null && !orderID.isEmpty()){

            if(troutid != null && !troutid.equalsIgnoreCase("null")&& !troutid.isEmpty()){

                voidTransactionUrl = Constant.WS_BASE_URL + Constant.USAPAYVoidTranscation
                        + orderID + "/" + storeno_selected + "/" + troutid;

                TaskUSAPayVoidTranscation taskUSAPayVoidTranscation= new TaskUSAPayVoidTranscation(getActivity(),this,orderID);
                taskUSAPayVoidTranscation.execute(voidTransactionUrl);
            }
        }

    }

    @Override
    public void onTaskUSAePAYVoidTranscationResult(PayWareModel payWareModel, String orderID) {

        if(payWareModel!= null && payWareModel.getSuccessMessage()!= null
                && !payWareModel.getSuccessMessage().toString().isEmpty()
                && !payWareModel.getSuccessMessage().toString().equalsIgnoreCase("null")
                && payWareModel.getSuccessMessage().toString().equalsIgnoreCase("Success")){

            //callcancelorderWs
            callCancelOrderWS(orderID);

            }else{
                //requestResponseWs's response

//            like success or which nsg used to hide cancel order dialog

                Utils.showRequestResponseDialog(context,payWareModel);

            }

        }
    }

//    public void returnprocessing(List<LstOrderTem> lstOrderTem, int position) {
//
//        for (int i=0; i<lstOrderTemsList.get(position).getLstReturnItems().size();i++){
//
//            ViewGroup.LayoutParams lparams = new ViewGroup.LayoutParams(
//                    ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
//
//            TextView tv = new TextView(context);
//            tv.setLayoutParams(lparams);
//            int no = i+1;
//            tv.setText(no+"."+" Return Processing");
//            tv.setTypeface(null, Typeface.BOLD);
//            tv.setTextColor(Color.parseColor(Constant.themeModel.ThemeColor));
//            holder.lldynamic_returnProcessing.addView(tv);
//
//            Log.d("tesssss",":" +position + lstOrderTemsList.get(position).getItemName());
//        }
//
//    }
//}
