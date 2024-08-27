package com.aspl.fragment;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.aspl.Adapter.WishListAdapter;
import com.aspl.Utils.Constant;
import com.aspl.Utils.DialogUtils;
import com.aspl.Utils.Utils;
import com.aspl.mbs.MainActivity;
import com.aspl.mbs.MainActivityDup;
import com.aspl.mbs.R;
import com.aspl.mbsmodel.ShoppingCardModel;
import com.aspl.mbsmodel.UserModel;
import com.aspl.mbsmodel.WishList;
import com.aspl.task.TaskCart;
import com.aspl.task.TaskDeleteWishList;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.List;

/**
 * Created by mic on 1/2/2018.
 */

public class WishListFragment extends Fragment
        implements TaskCart.TaskCardEvent
        , WishListAdapter.WishListAdapterEvent
        , View.OnClickListener
        , TaskDeleteWishList.TaskDeleteCartItemEvent{

    public static final String TAG = "WishList";
    RecyclerView recyclerView;
    SwipeRefreshLayout swipeRefreshLayout;
    TextView tvRoot,tvEmpty;
    LinearLayout llRoot, llEmpty;
    Button btnContinueShoping;
    //List<TestModel> testModels = new ArrayList<>();
    List<ShoppingCardModel> liShoppingCard;
    int position;

    WishListAdapter wishListAdapter;
    public static WishListFragment wishListFragment;

    public static WishListFragment getInstance() {
        return wishListFragment;
    }

    WishListFragmentEvent myWishListFragmentEvent;

    public interface WishListFragmentEvent{
        void onContinueShoppingFromWishList();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        myWishListFragmentEvent = (WishListFragmentEvent) context;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        myWishListFragmentEvent = (WishListFragmentEvent) activity;
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

            if(Constant.isFromMic){
                MainActivity.getInstance().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);
                MainActivity.getInstance().mSearchedt.requestFocus();
                MainActivity.getInstance().mSearchedt.setFocusableInTouchMode(true);
                Constant.isFromMic = false;
            }else{
                MainActivity.getInstance().mSearchedt.clearFocus();
                MainActivity.getInstance().mSearchedt.setText("");
                Utils.hideKeyboard();
            }

        }else if (Constant.SCREEN_LAYOUT == 2) {

            MainActivityDup.getInstance().llsearch.setVisibility(View.GONE);

            if(Constant.isFromMicSeclayout){
                MainActivityDup.getInstance().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);
                MainActivityDup.getInstance().mSearchedt.requestFocus();
                MainActivityDup.getInstance().mSearchedt.setFocusableInTouchMode(true);
                Constant.isFromMicSeclayout = false;
            }else{
                MainActivityDup.getInstance().mSearchedt.clearFocus();
                MainActivityDup.getInstance().mSearchedt.setText("");
                Utils.hideKeyboard();
            }
        }

        Utils.hideKeyboard();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        wishListFragment = this;
        View view = inflater.inflate(R.layout.fragment_wishlist, container, false);
        return view /*super.onCreateView(inflater, container, savedInstanceState)*/;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        loadWishListData();
        recyclerView = view.findViewById(R.id.wishlist_recyclerview);
        tvRoot = view.findViewById(R.id.tv_root_wish_list_fragment);
        tvEmpty = view.findViewById(R.id.tv_empty_wish_list_fragment);
        tvEmpty.setTextColor(Color.parseColor(Constant.themeModel.ThemeColor));
        tvRoot.setVisibility(View.GONE);
        tvRoot.setTextColor(Color.parseColor(Constant.themeModel.ThemeColor));

        llEmpty = view.findViewById(R.id.ll_root_empty_wish_list_fragment);
        llRoot = view.findViewById(R.id.ll_root_wish_list_fragment);
        btnContinueShoping = view.findViewById(R.id.btn_continue_shopping_empty_wish_list_fragment);
        GradientDrawable bgShapes = (GradientDrawable) btnContinueShoping.getBackground();
        bgShapes.setColor(Color.parseColor(Constant.themeModel.ThemeColor));
        btnContinueShoping.setOnClickListener(this);

        swipeRefreshLayout = view.findViewById(R.id.swipe_to_refresh_wish_list);
        swipeRefreshLayout.setBackgroundColor(Color.parseColor(Constant.themeModel.Backgroundcolor));
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                loadWishListData();
            }
        });

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);

        /* ArrayList<TestModel> images = prepareData();*/
        //wishListAdapter = new WishListAdapter(getActivity(),testModels);
        //cardAdapter = new CardAdapter(getActivity(),testModels);
        //recyclerView.setAdapter(wishListAdapter);
        //onLoadStaticData();

    }

    public void loadWishListData() {
        String url = null;
        if (UserModel.Cust_mst_ID != null) {
            //URL :- http://192.168.172.211:889/WebStoreRestService.svc/GetCustomerCartData/188856/wishlist/707
//            url = Constant.WS_BASE_URL + Constant.GET_CUSTOMER_CARD_DATA + UserModel.Cust_mst_ID + "/" + Constant.WISH_LIST + Constant.STOREID;
            url = Constant.WS_BASE_URL + Constant.GET_CUSTOMER_CARD_DATA_V1 + UserModel.Cust_mst_ID + "/" + Constant.WISH_LIST + Constant.STOREID + Constant.ENCODE_TOKEN_ID;
            TaskCart taskCart = new TaskCart(WishListFragment.this,"");
//            taskCart.execute(url);
            taskCart.executeOnExecutor(TaskCart.THREAD_POOL_EXECUTOR,url);
        } /*else {
            url = Constant.WS_BASE_URL + Constant.GET_CUSTOMER_CARD_DATA + DeviceInfo.getDeviceId(MainActivity.getInstance()) + "0011" + "/" + Constant.SESSION + Constant.STOREID;
            TaskCart taskCart = new TaskCart(cardFragment);
            taskCart.execute(url);
        }*/
    }

    @Override
    public void onShoppingCardResult(List<ShoppingCardModel> liShoppingCard, String s) {
        this.liShoppingCard = liShoppingCard;
        swipeRefreshLayout.setRefreshing(false);
        if (liShoppingCard.size() == 0) {
            onSetEmpty();
        }else if (liShoppingCard.get(0).getCartID() == 0){
            onSetEmpty();
        } else {
            llEmpty.setVisibility(View.GONE);
            llRoot.setVisibility(View.VISIBLE);
            this.liShoppingCard = liShoppingCard;
            tvRoot.setVisibility(View.VISIBLE);
            wishListAdapter = new WishListAdapter(getActivity(), this, liShoppingCard);
            recyclerView.setAdapter(wishListAdapter);
            wishListAdapter.notifyDataSetChanged();
        }

    }

    @Override
    public void onRemoveClick(int position, List<ShoppingCardModel> liShoppingCart) {
        this.position = position;
        //this.liShoppingCard = liShoppingCart;
        //Web Service :-  ”InsertCartData”  parameters: ID,Type,CustomerID,ItemID,Qty,StoreNo,SessionId OperationType,invType
        //URL :- http://192.168.172.211:889/WebStoreRestService.svc/InsertCartData/3853/Cart/188731/3853/1/707/0/remove/I



        String invType;
        if(liShoppingCard.get(position).getInvType() != null){
            invType = liShoppingCard.get(position).getInvType();
        }else{
            invType = "I";
        }

        String itemIdSku = null;
        if (!liShoppingCard.get(position).getItemMstId().trim().isEmpty() && !liShoppingCart.get(position).getItemMstId().trim().equals("")){
            try {
                itemIdSku = URLEncoder.encode(liShoppingCard.get(position).getItemMstId().trim(), "utf-8");
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }


        String id = String.valueOf(liShoppingCart.get(position).getCartID());
        String url = Constant.WS_BASE_URL + Constant.DELETE_CART + id + "/" + "Cart" + "/" + UserModel.Cust_mst_ID +
                "/" + itemIdSku + "/" + "1" +
                "/" + Constant.STOREID + "/" + "0" + "/" + "remove" +"/" +invType;

        TaskDeleteWishList deleteWishList = new TaskDeleteWishList(this,"Remove Wishlist");
//        deleteWishList.execute(url);
        deleteWishList.executeOnExecutor(TaskDeleteWishList.THREAD_POOL_EXECUTOR,url);

        //liShoppingCart.remove(position);
        /*if (liShoppingCart.size() == 0) {
            onSetEmpty();
        } else {
            recyclerView.removeViewAt(position);
            wishListAdapter.notifyItemRemoved(position);
            wishListAdapter.notifyItemRangeChanged(position, liShoppingCart.size());
            wishListAdapter.notifyDataSetChanged();
        }*/
    }

    @Override
    public void onMoveToCartClick(int position, List<ShoppingCardModel> liShoppingCart) {
        this.liShoppingCard = liShoppingCart;
        this.position = position;
        //Web Service :-  ”InsertCartData”  parameters: ID,Type,CustomerID,ItemID,Qty,StoreNo,SessionId OperationType,invType
        //URL :- http://192.168.172.211:889/WebStoreRestService.svc/InsertCartData/3853/Cart/188731/3853/1/707/0/movetocart/I
        String invType;
        Log.e("test",""+liShoppingCard.get(position).getItemMstId().trim());
        if(liShoppingCard.get(position).getInvType() != null){
            invType = liShoppingCard.get(position).getInvType();
        }else{
            invType = "I";
        }

        String itemIdSku = null;
        try {
            itemIdSku = URLEncoder.encode(liShoppingCard.get(position).getItemMstId().trim(), "utf-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }


        if (!liShoppingCart.get(position).getQtyOnHand().equals("0")){
            String id = String.valueOf(liShoppingCard.get(position).getCartID());
            String url = Constant.WS_BASE_URL + Constant.DELETE_CART + id + "/" + "Cart" + "/" + UserModel.Cust_mst_ID +
                    "/" + itemIdSku + "/" + "1" +
                    "/" + Constant.STOREID + "/" + "0" + "/" + "movetocart" + "/"+invType;

            TaskDeleteWishList deleteWishList = new TaskDeleteWishList(this, "MovetoCart Wishlist");
//            deleteWishList.execute(url);
            deleteWishList.executeOnExecutor(TaskDeleteWishList.THREAD_POOL_EXECUTOR,url);
            //liShoppingCard.remove(position);
        }
    }

    @Override
    public void onWishListResult(WishList wishList, String string) {
        if (wishList.getResult().equals("success")){

//            Edited By Varun for pop-up of Added

            if (string!=null && !string.isEmpty()){
                if (string.equalsIgnoreCase("MovetoCart WishList")){
                    Utils.vibrateDevice(getContext());
                    DialogUtils.showDialog("Added in Cart!");
                }
                if (string.equalsIgnoreCase("Remove Wishlist")){
                    DialogUtils.showDialog("Removed from WishList!");
                }
            }

//            END

            if(Constant.SCREEN_LAYOUT==1){
                MainActivity.onGetCartData("");
            }else if(Constant.SCREEN_LAYOUT==2) {
                MainActivityDup.onGetCartData();
            }
            liShoppingCard.remove(position);
            if (liShoppingCard.size() == 0) {
                onSetEmpty();
            } else {
                recyclerView.removeViewAt(position);
                wishListAdapter.notifyItemRemoved(position);
                wishListAdapter.notifyItemRangeChanged(position, liShoppingCard.size());
                wishListAdapter.notifyDataSetChanged();
            }
        }else{

            DialogUtils.showDialog("Already in Cart!");
            //Toast.makeText(getActivity(), "Item Already in Card", Toast.LENGTH_SHORT).show();
        }
        //WishListFragment.getInstance().onShoppingCardResult(liShoppingCard);
    }

    public void onSetEmpty() {
        llRoot.setVisibility(View.GONE);
        llEmpty.setVisibility(View.VISIBLE);

    }

    @Override
    public void onClick(View view) {
        if (view.getId() == btnContinueShoping.getId()) {
            if (myWishListFragmentEvent != null)
                myWishListFragmentEvent.onContinueShoppingFromWishList();
        }
    }

   /* public void onLoadStaticData(){

        testModels.add(new TestModel("http://192.168.172.211:888/WebStoreImages/Inventory/707/707_83522900068_NativeImage_1.jpg","ABITA AMBER 4/6 NR ABITA AA AMBER 4/6 NR (12 Oz)","200","3"));
        testModels.add(new TestModel("http://192.168.172.211:888/WebStoreImages/Inventory/707/707_08130800340_2_2.jpg","ABITA AMBER 4/6 NR (12 Oz)","200","50"));
        testModels.add(new TestModel("http://192.168.172.211:888/WebStoreImages/Inventory/707/707_08048030252_70707065590130Brazin-wine-bottle-render.jpg","Kettle Bar B Q 5 OZ","150","5"));
        testModels.add(new TestModel("http://192.168.172.211:888/WebStoreImages/Inventory/707/707_08500002156_707.080480400316_1.jpg","MALIBU BANANA RUM (.750L)","55","50"));
        testModels.add(new TestModel("http://192.168.172.211:888/WebStoreImages/Inventory/707/707_83522900078_70700000000590Jacob%E2%80%99s-Creek_1.jpg","CABO WABO DIABLO (.750L)","10","1"));
        testModels.add(new TestModel("http://192.168.172.211:888/WebStoreImages/Inventory/707/707_08500000968_TURNING-LEAF-PINO-75_1.jpg","MALIO WABO DIABLO (.750L)","10","2"));
        testModels.add(new TestModel("http://192.168.172.211:888/WebStoreImages/Inventory/707/707_83522900081_ABSOLUT-ORIENT-APPLE-1L_1.png","WABO DIABLO (.750L)","10","1"));
        testModels.add(new TestModel("http://192.168.172.211:888/WebStoreImages/Inventory/707/707_08500002157_BAREFOOT-MALBEC-1-5LN_1.png", "WABO DIABLO (.750L)", "10", "5"));
        //http://192.168.172.211:888/WebStoreImages/Inventory/707/707_08858600177_6.jpg
        wishListAdapter.notifyDataSetChanged();
    }*/


}
