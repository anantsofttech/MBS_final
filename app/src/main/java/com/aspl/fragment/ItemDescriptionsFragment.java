package com.aspl.fragment;


import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;
import android.net.Uri;
import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.core.app.ShareCompat;
import androidx.core.content.ContextCompat;
import androidx.core.widget.NestedScrollView;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.text.Html;
import android.util.Log;
import android.view.Display;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RatingBar;
import android.widget.TextView;

import com.aspl.Adapter.MultiPackAdapter;
import com.aspl.Adapter.RecommandedItemAdapter;
import com.aspl.Utils.Constant;
import com.aspl.Utils.DeviceInfo;
import com.aspl.Utils.DialogUtils;
import com.aspl.Utils.Utils;
import com.aspl.mbs.MainActivity;
import com.aspl.mbs.MainActivityDup;
import com.aspl.mbs.R;
import com.aspl.mbsmodel.ItemDescModel;
import com.aspl.mbsmodel.OrderSummary;
import com.aspl.mbsmodel.ReOrderModel;
import com.aspl.mbsmodel.ShippingData;
import com.aspl.mbsmodel.ShoppingCardModel;
import com.aspl.mbsmodel.SiteInfoModel;
import com.aspl.mbsmodel.UpdateCartQuantity;
import com.aspl.mbsmodel.UserModel;
import com.aspl.task.TaskAddtoCart;
import com.aspl.task.TaskCart;
import com.aspl.task.TaskDomain;
import com.aspl.task.TaskDrinkReceipe;
import com.aspl.task.TaskEmailDrinkReceipe;
import com.aspl.task.TaskItemDescription;
import com.aspl.task.TaskRecommandedItems;
import com.aspl.task.TaskSendEmail;
import com.aspl.task.TaskShippingData;
import com.aspl.task.TaskUpdateCartQuantity;
import com.aspl.task.TaskUpdatetoCart;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.GlideDrawableImageViewTarget;


import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class ItemDescriptionsFragment extends Fragment implements View.OnClickListener,
//        MultiPackAdapter.MultiPackAdapterEvent,
        TaskItemDescription.TaskItemDescInterface,
        TaskAddtoCart.TaskAddToCartEvent,
        TaskCart.TaskCardEvent,
        TaskUpdatetoCart.TaskUpdateCart,
        TaskUpdateCartQuantity.TaskUpdateCartQuantityEvent,
        TaskRecommandedItems.TaskRecommandedInterface,
        TaskSendEmail.TaskSendEmailEvent, TaskDrinkReceipe.DrinkRecipeInterface, TaskEmailDrinkReceipe.TaskSendDrinkEmailEvent, TaskShippingData.TaskShippingEvent, TaskDomain.TaskDomainEvent {

    //    public static View vPaymentProcessDialog;
//    public static Dialog paymentProcess;
    public static final String TAG = "ItemDescriptionsFragment";
    private String imgNoImageUrl = Constant.IMG_BASE + Constant.IMG_NO_IMAGE + "/";
    private String imgUrl = Constant.IMG_BASE + Constant.IMG_BASE_URL + Constant.STOREID + "/";

    RecyclerView rec_home;
    LinearLayout llPrice,llQty , llprice_multi, llselect_item;
    TextView tvWishlist, tvShare, tvItemTitleDesc1, tvPrice, tvPromoPrice, tvHeader1, tvHeader2, tvDiscountName, tvItemSku, tvDetailsView;

    ImageView tvAddtoCart;
    public NestedScrollView nestedScrollView;
    String itemIdSku;
    ImageView img_item;
    TextView tvMinus;
    public TextView tvQty;
    TextView tvPlus,tvItemOutofStock;
    Button btnDesc, btnReturnPolicy, btnShippingDetails, btnDrinkRecipe;
    public int count = 1;
    ItemDescModel itemDescModel;
    int buttonpressed = 0;
    long cartId = 0;
    String cartQtyOfItem = "", requestedQty = "";
    RatingBar ratingBar;
    public static ItemDescriptionsFragment itemDescriptionsFragment;
    boolean isComeFomAddTocartBtn = false;
    List<ItemDescModel> recommandedItemList = new ArrayList<>();
    CardView cv_header1_itemDesc, cv_header2_itemDesc, cusWhoBoughtAlsoView;
    PopupWindow popupWindow;
    Context context;
    List<ItemDescModel> drinkReceipeList;
    String domain = "";


//    Edited by Varun

    private double promoprice =  0.00;
    FrameLayout fl_sale_left, fl_sale_right,fl_sale_hang;
    RecyclerView recyclerView;

//     END

//    Edited by Varun for faster shopping experience

    LinearLayout ll_fast , ll_qtyandCart , ll_outofStock;

    public ImageView iv_productimg , img_minus , img_plus , ivAddtoCart_up;
    public TextView tv_title , tv_item_quantity, tv_price, tv_promoprice ;


//    END


//    ProgressBar progressBar;
//    ProgressDialog loading = null;

    public ItemDescriptionsFragment() {
        // Required empty public constructor
        setHasOptionsMenu(true);
    }

    public static ItemDescriptionsFragment getInstance() {
        return itemDescriptionsFragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        itemDescriptionsFragment = this;
        setHasOptionsMenu(true);
        setRetainInstance(true);
        if(Constant.SCREEN_LAYOUT == 2){
            MainActivityDup.getInstance().showbackButton();
        }
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof Activity) {//Name of your activity
            this.context = (Activity) context;
        }
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        //save the activity to a member of this fragment
        context = activity;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        View rootView = inflater.inflate(R.layout.fragment_item_descriptions, container, false);

//        nDialog = new ProgressDialog(getActivity());
////        nDialog.setMessage("Loading..");
////        nDialog.setTitle("Get Data");
//        nDialog.setIndeterminate(false);
//        nDialog.setCancelable(true);
//        nDialog.show();

//        RelativeLayout layout = new RelativeLayout(getActivity());
//        progressBar = new ProgressBar(getActivity(),null,android.R.attr.progressBarStyleLarge);
//        progressBar.setIndeterminate(true);
//        progressBar.setVisibility(View.VISIBLE);
//        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(100,100);
//        params.addRule(RelativeLayout.CENTER_IN_PARENT);
//        layout.addView(progressBar,params);

//        spinner = (ProgressBar)rootView.findViewById(R.id.progressBar1);
//        spinner.setVisibility(View.VISIBLE);

//        loading = new ProgressDialog(getActivity(),R.style.MyprogressDTheme);
//        loading.setCancelable(true);
////        loading.setMessage(Constant.Message.AuthenticatingUser);
//        loading.setProgressStyle(ProgressDialog.STYLE_SPINNER);
//        loading.show();

        initViews(rootView);

        Bundle bundle = this.getArguments();
        if (bundle != null) {

            itemIdSku = bundle.getString("itemMstId", "");
            itemIdSku = itemIdSku.trim();

            CallWSForItemDescriptionDetails(itemIdSku);
        }

        return rootView;
    }


    public void CallWSForItemDescriptionDetails(String itemIdSku) {
        Log.e("itemid",itemIdSku);

        try {
            itemIdSku = URLEncoder.encode(itemIdSku.trim(), "utf-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        if (!itemIdSku.equals("")) {
//            String url = Constant.WS_BASE_URL + Constant.GET_INVERNTORY_BY_ID + "/" + itemIdSku + "/" + Constant.STOREID;
            String url = Constant.WS_BASE_URL + Constant.GET_INVERNTORY_BY_ID_NEW + "/" + itemIdSku + "/" + Constant.STOREID;
            TaskItemDescription taskItemDescription = new TaskItemDescription(this, getActivity());
            taskItemDescription.execute(url);
        }
    }

    @SuppressLint("LongLogTag")
    private void initViews(View view) {


        ll_fast = view.findViewById(R.id.ll_fast);

//        shareDialog = new ShareDialog((Activity) context);
        drinkReceipeList = new ArrayList<>();
        itemDescModel = new ItemDescModel();
        nestedScrollView = view.findViewById(R.id.nested_scroll_ItemDesc_fragment);
        try{
            nestedScrollView.setBackgroundColor(Color.parseColor(Constant.themeModel.Backgroundcolor));
        }catch (Exception e){
            e.printStackTrace();
        }

        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        Display display = wm.getDefaultDisplay();

        int width = display.getWidth();  // deprecated
        int height = 0;
        if (Constant.SCREEN_LAYOUT==1){
             height =  display.getHeight() -  MainActivity.getInstance().getToolBarHeight() - MainActivity.getInstance().llsearch.getHeight()  - ll_fast.getHeight();  // deprecated
        }else{
             height =  display.getHeight() -  MainActivityDup.getInstance().getToolBarHeight() - MainActivityDup.getInstance().llsearch.getHeight()  - ll_fast.getHeight();  // deprecated
        }

        Log.e(TAG, "initViews: "+height);

//        Edited by Varun for faster item Description shopping
        if (nestedScrollView != null) {

            int finalHeight = height;
            nestedScrollView.setOnScrollChangeListener(new NestedScrollView.OnScrollChangeListener() {
                @SuppressLint("LongLogTag")
                @Override
                public void onScrollChange(NestedScrollView v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {

                    if (scrollY < finalHeight) {

//                        MainActivity.getInstance().fast2();
                        ll_fast.setVisibility(View.GONE);
                        Log.i(TAG, "Scroll UP");

                    }

                    if (scrollY >= finalHeight) {
//                        MainActivity.getInstance().fast();
                        ll_fast.setVisibility(View.VISIBLE);
                        Log.i(TAG, "TOP SCROLL");
                    }

                }
            });
        }
//END


        llselect_item=view.findViewById(R.id.ll_select_item);
        llprice_multi=view.findViewById(R.id.llPrice_multi);
        llPrice = view.findViewById(R.id.llPrice);
        llQty = view.findViewById(R.id.llQty);
        tvAddtoCart = view.findViewById(R.id.tvAddtoCart);
        tvItemOutofStock = view.findViewById(R.id.tvItemOutofStock);
        tvItemTitleDesc1 = view.findViewById(R.id.tvItemTitleDesc1);
        tvPrice = view.findViewById(R.id.tvPrice);
        tvDiscountName = view.findViewById(R.id.tvDiscountName);
        tvPromoPrice = view.findViewById(R.id.tvPromoPrice);
        tvItemSku = view.findViewById(R.id.tvItemSku);
        tvDetailsView = view.findViewById(R.id.tvDetailsView);
        img_item = view.findViewById(R.id.img_item);
        tvHeader1 = view.findViewById(R.id.tv_header1_ItemDesc_fragment);
        tvHeader2 = view.findViewById(R.id.tv_header2_ItemDesc_fragment);
        tvMinus = view.findViewById(R.id.tvMinus);
        tvPlus = view.findViewById(R.id.tvPlus);
        tvQty = view.findViewById(R.id.tvQty);
        rec_home = view.findViewById(R.id.rec_home);
        btnDesc = view.findViewById(R.id.btnDesc);
        btnReturnPolicy = view.findViewById(R.id.btnReturnPolicy);
        btnShippingDetails = view.findViewById(R.id.btnShippingDetails);
        btnDrinkRecipe = view.findViewById(R.id.btnDrinkRecipe);
        cv_header1_itemDesc = view.findViewById(R.id.cv_header1_itemDesc);
        cv_header2_itemDesc = view.findViewById(R.id.cv_header2_itemDesc);
        cusWhoBoughtAlsoView = view.findViewById(R.id.cusWhoBoughtAlsoView);
//        cvReturnPolicy = view.findViewById(R.id.cvReturnPolicy);
//        cvBtnDescView = view.findViewById(R.id.cvBtnDescView);
        ratingBar = view.findViewById(R.id.ratingBar);
        tvWishlist = view.findViewById(R.id.tvWishlist);
        tvShare = view.findViewById(R.id.tvShare);


//        Edited by Varun

        fl_sale_left= view.findViewById(R.id.fl_sale_left);
        fl_sale_right= view.findViewById(R.id.fl_sale_right);
        fl_sale_hang = view.findViewById(R.id.fl_sale_hang);

//        Edited by Varun for multi pack
        recyclerView= view.findViewById(R.id.rv_multi_pack);
        Constant.Test_SKU="";
        Constant.Test_Ounces = 0;
//        END


        LayerDrawable stars = (LayerDrawable) ratingBar.getProgressDrawable();
        stars.getDrawable(2).setColorFilter(Color.parseColor("#FFC120"), PorterDuff.Mode.SRC_ATOP);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false);
        rec_home.setLayoutManager(layoutManager);
        rec_home.setHasFixedSize(true);

//        // Convert the color string to an integer representation
//        int originalColor = Color.parseColor(Constant.themeModel.ThemeColor);
//
//// Generate a lighter shade of the original color
//        int lighterShade = Utils.lightenColor(originalColor, 0.7f); // Adjust factor as needed
//
//// Generate a darker shade of the original color
//        int darkerShade = Utils.darkenColor(originalColor, 0.5f); // Adjust factor as needed

        Drawable roundDrawable = getResources().getDrawable(R.drawable.rounded_corner_all);
        roundDrawable.setColorFilter(Color.parseColor(Constant.themeModel.ThemeColor), PorterDuff.Mode.SRC_ATOP);
        tvAddtoCart.setBackground(roundDrawable);


//        Edited by Varun for faster shopping experience
        ll_fast.setVisibility(View.GONE);
        iv_productimg =view. findViewById(R.id.iv_productimg);
        iv_productimg.setOnClickListener(this);
        img_minus =view.findViewById(R.id.img_minus);
        img_minus.setOnClickListener(this);
        img_plus = view.findViewById(R.id.img_plus);
        img_plus.setOnClickListener(this);
        ivAddtoCart_up = view.findViewById(R.id.ivAddtoCart_up);
        ivAddtoCart_up.setOnClickListener(this);
        tv_title = view.findViewById(R.id.tv_title);
        tv_price = view. findViewById(R.id.tv_price);
        tv_item_quantity = view.findViewById(R.id.tv_item_quantity);

        ll_outofStock = view. findViewById(R.id.ll_outofStock);
        ll_qtyandCart = view. findViewById(R.id.ll_qtyandCart);

        tv_promoprice=view.findViewById(R.id.tv_promoprice);


        ivAddtoCart_up.setBackground(roundDrawable);


//            END


        tvQty.setText("1");
        tvPlus.setOnClickListener(this);
        tvMinus.setOnClickListener(this);
        btnDesc.setOnClickListener(this);
        btnShippingDetails.setOnClickListener(this);
        btnReturnPolicy.setOnClickListener(this);
        btnDrinkRecipe.setOnClickListener(this);
        tvWishlist.setOnClickListener(this);
        tvAddtoCart.setOnClickListener(this);
        tvShare.setOnClickListener(this);
    }

    @Override
    public void onItemDescResult(ItemDescModel itemDescModel) {

        if (itemDescModel != null) {
            this.itemDescModel = itemDescModel;

//            Edited by Varun for the share option enable and disable from the global set-up
            if (itemDescModel.getSocialSharing()){
                tvShare.setVisibility(View.VISIBLE);
            }else{
                tvShare.setVisibility(View.GONE);
            }
//          END
            Constant.invType = itemDescModel.getInvType();

            if (itemDescModel.getItemMstId() != null && !itemDescModel.getItemMstId().isEmpty())
                itemIdSku = itemDescModel.getItemMstId().trim();

////        ******************* Edited by Varun on 16 Aug 2022 ************************
//
             if (itemDescModel.getInvLargeImageFullPath()!=null || !itemDescModel.getInvLargeImageFullPath().isEmpty()) {
                 if (context==null){

                 }else{
                     Glide.with(context).load(itemDescModel.getInvLargeImageFullPath())
                             .placeholder(R.drawable.noimage)
                             .error(R.drawable.no_image_new)
                             .diskCacheStrategy(DiskCacheStrategy.NONE)
                             .skipMemoryCache(true).into(img_item);

                     Glide.with(context).load(itemDescModel.getInvLargeImageFullPath())
                             .placeholder(R.drawable.noimage)
                             .error(R.drawable.no_image_new)
                             .diskCacheStrategy(DiskCacheStrategy.NONE)
                             .skipMemoryCache(true).into(iv_productimg);


                 }

            }
////        ******************* END ************************


//            Edited by Varun for sale indicator
            if (itemDescModel.getPromoPrice() != null && !itemDescModel.getPromoPrice().isEmpty()){
                if (!itemDescModel.getPromoPrice().equalsIgnoreCase("0.00") && !itemDescModel.getPromoPrice().equals(promoprice)) {

                    if (itemDescModel.getItemonPromotionIndicator().equalsIgnoreCase("NI")) {
                        fl_sale_right.setVisibility(View.GONE);
                        fl_sale_right.setVisibility(View.GONE);
                        fl_sale_hang.setVisibility(View.GONE);
                    } else if (itemDescModel.getItemonPromotionIndicator().equalsIgnoreCase("LR")) {
                        fl_sale_right.setVisibility(View.VISIBLE);
                    } else if (itemDescModel.getItemonPromotionIndicator().equalsIgnoreCase("UL")) {
                        fl_sale_left.setVisibility(View.VISIBLE);
                    } else if (itemDescModel.getItemonPromotionIndicator().equalsIgnoreCase("HT")) {
                        fl_sale_hang.setVisibility(View.VISIBLE);
                    }
                }
            }else {

            }

//            END



            initializeValues();

            onCallShippingDataTask();

//            CallWSForDrinkReceipe();

        }
    }

    private Drawable placeholder;
    public void loadImage(String url, ImageView imageView) {
        Log.d("URL--", "" + url);
        if(Constant.SCREEN_LAYOUT==1)
        {
            placeholder = ContextCompat.getDrawable(MainActivity.getInstance(), R.drawable.progress_bar);
        }else if(Constant.SCREEN_LAYOUT==2)

        {
            placeholder = ContextCompat.getDrawable(MainActivityDup.getInstance(), R.drawable.progress_bar);
        }
        Glide.with(imageView.getContext())
                .load(url)
                .placeholder(placeholder)
                .diskCacheStrategy(DiskCacheStrategy.NONE)
                .skipMemoryCache(true)
                .fitCenter()
                .into(new GlideDrawableImageViewTarget(imageView) {
                    @Override
                    public void onResourceReady(GlideDrawable drawable, GlideAnimation anim) {
                        super.onResourceReady(drawable, anim);
                        //getProgressBarIndeterminate() = drawable;
                    }
                });
    }

    private void onCallShippingDataTask() {

        if(UserModel.Cust_mst_ID != null){
            String url = Constant.WS_BASE_URL + Constant.GET_SHIPPING_DATA + UserModel.Cust_mst_ID + "/" + Constant.STOREID;
            TaskShippingData taskShippingData = new TaskShippingData(this, false, getActivity());
            taskShippingData.execute(url);
        }else{
            String url = Constant.WS_BASE_URL + Constant.GET_SHIPPING_DATA + 0 + "/" + Constant.STOREID;
            TaskShippingData taskShippingData = new TaskShippingData(this, false, getActivity());
            taskShippingData.execute(url);
        }

    }

    @SuppressLint("LongLogTag")
    private void initializeValues() {

        if(itemDescModel.getShowItemButOutOfStock() != null && itemDescModel.getShowItemButOutOfStock().equals("True")){

            if(itemDescModel.getQtyOnHand() != null && !itemDescModel.getQtyOnHand().isEmpty()
                    && Integer.parseInt(itemDescModel.getQtyOnHand()) <= 0 ){

                tvItemOutofStock.setVisibility(View.VISIBLE);
                tvAddtoCart.setVisibility(View.GONE);
                llQty.setVisibility(View.GONE);

//                Edited by Varun for faster shopping experience
                ll_qtyandCart.setVisibility(View.GONE );
                ll_outofStock.setVisibility(View.VISIBLE);
//                END

            }else{
                tvItemOutofStock.setVisibility(View.GONE);
                tvAddtoCart.setVisibility(View.VISIBLE);
                llQty.setVisibility(View.VISIBLE);

//                Edited by Varun for faster shopping experience

                ll_qtyandCart.setVisibility(View.VISIBLE);
                ll_outofStock.setVisibility(View.GONE);

//                END

            }

        }else{
            tvItemOutofStock.setVisibility(View.GONE);
            tvAddtoCart.setVisibility(View.VISIBLE);
            llQty.setVisibility(View.VISIBLE);
        }
//                    Edited by Varun for multi pack
        Log.e("", "initializeValues: 12"+itemDescModel.getLstInventoryModel().size() );
        if (Constant.invType.equalsIgnoreCase("M")){

            if ( !itemDescModel.getPrice().isEmpty() && itemDescModel.getPrice() != null ) {
                tvPrice.setText(itemDescModel.getPrice());
                tv_price.setText(itemDescModel.getPrice());
            }

            if (itemDescModel.getLstInventoryModel().size()!=0){

                llprice_multi.setVisibility(View.VISIBLE);
                llselect_item.setVisibility(View.VISIBLE);
                Constant.Test_SKU="";
                Constant.Test_Ounces=0;
                RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false);
                recyclerView.setLayoutManager(layoutManager);
                MultiPackAdapter multiPackAdapter = new MultiPackAdapter(context,itemDescModel);
                recyclerView.setAdapter(multiPackAdapter);
                multiPackAdapter.notifyDataSetChanged();

            }
        } else{

//        END
            llprice_multi.setVisibility(View.GONE);
            llselect_item.setVisibility(View.GONE);
            if ( !itemDescModel.getPrice().isEmpty() && itemDescModel.getPrice() != null ) {
                tvPrice.setText(itemDescModel.getPrice());
                tv_price.setText(itemDescModel.getPrice());
            }

            if (itemDescModel.getPromoPrice() != null && !itemDescModel.getPromoPrice().isEmpty() && !itemDescModel.getPromoPrice().equalsIgnoreCase("0.00")) {
                tvPromoPrice.setVisibility(View.VISIBLE);
                tvPromoPrice.setText("$" + itemDescModel.getPromoPrice());

                tv_promoprice.setVisibility(View.VISIBLE);
                tv_promoprice.setText("$" + itemDescModel.getPromoPrice());


                tvPrice.setPaintFlags(tvPrice.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
                tvPrice.setTextColor(Color.GRAY);

                tv_price.setPaintFlags(tv_price.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
                tv_price.setTextColor(Color.GRAY);


            } else {
                tvPromoPrice.setVisibility(View.GONE);
                tv_promoprice.setVisibility(View.GONE);
                tvPrice.setTextColor(Color.BLACK);
                tvPrice.setPaintFlags(tvPrice.getPaintFlags() & (~Paint.STRIKE_THRU_TEXT_FLAG));
                tvPrice.setGravity(Gravity.CENTER);

                tv_price.setTextColor(Color.BLACK);
                tv_price.setPaintFlags(tv_price.getPaintFlags() & (~Paint.STRIKE_THRU_TEXT_FLAG));
                tv_price.setGravity(Gravity.CENTER);
            }

        }




        if (!itemIdSku.isEmpty()) {
            tvItemSku.setText("SKU: " + itemIdSku.trim());
        }

        if (itemDescModel.getDesc1() != null && !itemDescModel.getDesc1().isEmpty()) {

            if(itemDescModel.getDesc1().contains("()")){
                String desc1 = itemDescModel.getDesc1().replace("()","");
                tvItemTitleDesc1.setText(Utils.capitalizeEachWord(desc1.trim()));
            }else{
                tvItemTitleDesc1.setText(Utils.capitalizeEachWord(itemDescModel.getDesc1().trim()));

//                TESTING
                tv_title.setText(Utils.capitalizeEachWord(itemDescModel.getDesc1().trim()));

//                END

            }
        }

//        if (itemDescModel.getDiscountName() != null && !itemDescModel.getDiscountName().isEmpty()) {
//            tvDiscountName.setVisibility(View.VISIBLE);
//            tvDiscountName.setText(itemDescModel.getDiscountName());
//        } else {
//            tvDiscountName.setVisibility(View.GONE);
//        }
//

//        if (itemDescModel.getGrpcomment() != null && !itemDescModel.getGrpcomment().isEmpty()) {
//            tvDiscountName.setVisibility(View.VISIBLE);
//            tvDiscountName.setText(itemDescModel.getGrpcomment());
//        } else {
//            tvDiscountName.setVisibility(View.GONE);
//        }

        if (itemDescModel.getGrpcomment() != null && !itemDescModel.getGrpcomment().isEmpty()) {
            tvDiscountName.setVisibility(View.VISIBLE);
            tvDiscountName.setText(itemDescModel.getGrpcomment());
        } else {
            if (itemDescModel.getDiscountName() != null && !itemDescModel.getDiscountName().isEmpty()) {
                tvDiscountName.setVisibility(View.VISIBLE);
                tvDiscountName.setText(itemDescModel.getDiscountName());
            }else{
                tvDiscountName.setVisibility(View.GONE);
            }
        }

//        Testing

//        if(!itemDescModel.getPromoPrice().isEmpty() && itemDescModel.getPromoPrice()!=null){
//            tv_price.setText("$" + itemDescModel.getPromoPrice());
//        }else{
//            tv_price.setText("$" + itemDescModel.getPrice());
//        }

//        EMD

        tvDiscountName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(itemDescModel.getGrpMemo()!= null && !itemDescModel.getGrpMemo().equals("null") && !itemDescModel.getGrpMemo().isEmpty()){
                    Utils.showDiscountgroupDialog(context,itemDescModel.getDesc1(),itemDescModel.getGrpMemo(), "", null);
                }

            }
        });

        if (itemDescModel.getHeader1() != null && !itemDescModel.getHeader1().isEmpty()) {
            cv_header1_itemDesc.setVisibility(View.VISIBLE);
            tvHeader1.setVisibility(View.VISIBLE);
            tvHeader1.setText(Utils.capitalizeEachWord(itemDescModel.getHeader1()));
        } else {
            tvHeader1.setVisibility(View.GONE);
            cv_header1_itemDesc.setVisibility(View.GONE);
        }

        if (itemDescModel.getHeader2() != null && !itemDescModel.getHeader2().isEmpty()) {
            cv_header2_itemDesc.setVisibility(View.VISIBLE);
            tvHeader2.setVisibility(View.VISIBLE);
            tvHeader2.setText(Utils.capitalizeEachWord(itemDescModel.getHeader2()));
        } else {
            cv_header2_itemDesc.setVisibility(View.GONE);
            tvHeader2.setVisibility(View.GONE);
        }

        if (itemDescModel.getExtendDesc() != null && !itemDescModel.getExtendDesc().isEmpty()) {
            btnDesc.setVisibility(View.VISIBLE);
        } else {
            btnDesc.setVisibility(View.GONE);
        }

        if (itemDescModel.getReturnDesc() != null && !itemDescModel.getReturnDesc().isEmpty()) {
            btnReturnPolicy.setVisibility(View.VISIBLE);
        } else {
            btnReturnPolicy.setVisibility(View.GONE);
        }
//
//        if (itemDescModel.getShippingDesc() != null && !itemDescModel.getShippingDesc().isEmpty()) {
//            btnShippingDetails.setVisibility(View.VISIBLE);
//        } else {
//            btnShippingDetails.setVisibility(View.GONE);
//        }

        if (itemDescModel.getDrinkRecipes() != null && itemDescModel.getDrinkRecipes()) {
            btnDrinkRecipe.setVisibility(View.VISIBLE);
        } else {
            btnDrinkRecipe.setVisibility(View.GONE);
        }

        if (btnDesc.isShown() || btnShippingDetails.isShown() || btnReturnPolicy.isShown()) {
            tvDetailsView.setVisibility(View.VISIBLE);
        } else {
            tvDetailsView.setVisibility(View.GONE);
        }

//        //ratingbar code starts ------------
        if (!itemDescModel.getInventoryRating().isEmpty() && Integer.parseInt(itemDescModel.getInventoryRating()) > 0) {
            ratingBar.setVisibility(View.VISIBLE);
            ratingBar.setNumStars(Integer.parseInt(itemDescModel.getInventoryRating()));
            ratingBar.setRating(Float.parseFloat(itemDescModel.getInventoryRating()));
        } else {
            ratingBar.setVisibility(View.GONE);
        }
        //ratingbar code end ------------

        displayeThemeColorOnButton();

//        nDialog.dismiss();
//        spinner.setVisibility(View.GONE);
//        loading.dismiss();
    }

    private void CallWSForDrinkReceipe() {

        if (btnDrinkRecipe.isShown() && !itemIdSku.equals("")){

            String sku = null;
            try {
                sku = URLEncoder.encode(itemIdSku, "utf-8");
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }

            String url = Constant.WS_BASE_URL + Constant.GET_DRINK_RECIPES + "/" + sku + "/" + Constant.STOREID;
            TaskDrinkReceipe taskDrinkReceipe = new TaskDrinkReceipe(this);
            taskDrinkReceipe.execute(url);
        }else{
                CallWSForDisplayedRecommandedItems();
        }
    }


    private void CallWSForDisplayedRecommandedItems() {

        if (!itemIdSku.isEmpty()) {

            String sku = null;
            try {
                sku = URLEncoder.encode(itemIdSku, "utf-8");
                nestedScrollView.fullScroll(View.FOCUS_UP);
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }

            String url = Constant.WS_BASE_URL + Constant.GET_RECOMMANDEDITEMS + "/" + sku + "/" + Constant.STOREID;
            TaskRecommandedItems taskRecommandedItems = new TaskRecommandedItems(this);
            taskRecommandedItems.execute(url);
        }
    }

    public void displayeThemeColorOnButton() {

        if (btnDesc.isShown()) {
            tvDetailsView.setText(Html.fromHtml(itemDescModel.getExtendDesc()));

            setThemeColorBackgroundToButtonWithRadius(btnDesc);
            setWhiteBackgroundToButtonWithRadius(btnReturnPolicy);
            setWhiteBackgroundToButtonWithRadius(btnShippingDetails);
            setWhiteBackgroundToButtonWithRadius(btnDrinkRecipe);

            btnDesc.setTextColor(Color.WHITE);
            btnReturnPolicy.setTextColor(Color.BLACK);
            btnShippingDetails.setTextColor(Color.BLACK);
            btnDrinkRecipe.setTextColor(Color.BLACK);
        } else if (btnReturnPolicy.isShown()) {

            tvDetailsView.setText(Html.fromHtml(itemDescModel.getReturnDesc()));

            setThemeColorBackgroundToButtonWithRadius(btnReturnPolicy);
            setWhiteBackgroundToButtonWithRadius(btnDesc);
            setWhiteBackgroundToButtonWithRadius(btnShippingDetails);
            setWhiteBackgroundToButtonWithRadius(btnDrinkRecipe);

            btnReturnPolicy.setTextColor(Color.WHITE);
            btnDesc.setTextColor(Color.BLACK);
            btnShippingDetails.setTextColor(Color.BLACK);
            btnDrinkRecipe.setTextColor(Color.BLACK);

        } else if (btnShippingDetails.isShown()) {

            tvDetailsView.setText(Html.fromHtml(itemDescModel.getShippingDesc()));

            setThemeColorBackgroundToButtonWithRadius(btnShippingDetails);
            setWhiteBackgroundToButtonWithRadius(btnDesc);
            setWhiteBackgroundToButtonWithRadius(btnReturnPolicy);
            setWhiteBackgroundToButtonWithRadius(btnDrinkRecipe);

            btnShippingDetails.setTextColor(Color.WHITE);
            btnReturnPolicy.setTextColor(Color.BLACK);
            btnDesc.setTextColor(Color.BLACK);
            btnDrinkRecipe.setTextColor(Color.BLACK);

        } else if (btnDrinkRecipe.isShown()) {

            setThemeColorBackgroundToButtonWithRadius(btnDrinkRecipe);
            setWhiteBackgroundToButtonWithRadius(btnDesc);
            setWhiteBackgroundToButtonWithRadius(btnReturnPolicy);
            setWhiteBackgroundToButtonWithRadius(btnShippingDetails);

            btnDrinkRecipe.setTextColor(Color.WHITE);
            btnReturnPolicy.setTextColor(Color.BLACK);
            btnDesc.setTextColor(Color.BLACK);
            btnShippingDetails.setTextColor(Color.BLACK);
        }
    }

    private void setThemeColorBackgroundToButtonWithRadius(Button btnName) {
        if(isAdded()){
            Drawable roundDrawable = getResources().getDrawable(R.drawable.only_three_side_border_with_radius_nobackground);
            roundDrawable.setColorFilter(Color.parseColor(Constant.themeModel.ThemeColor), PorterDuff.Mode.SRC_ATOP);
            btnName.setBackground(roundDrawable);
        }
    }

    private void setWhiteBackgroundToButtonWithRadius(Button buttonname) {

        if(isAdded()){
            Drawable roundDrawable = getResources().getDrawable(R.drawable.only_three_side_border_with_radius);
            buttonname.setBackground(roundDrawable);
        }
    }


    @SuppressLint("LongLogTag")
    @Override
    public void onClick(View view) {

        switch (view.getId()) {
            case R.id.tvPlus:
                if (count < 999) {
                    count++;
                }
                tvQty.setText("" + count);
//                onUpdateQuantityTask(count);
                break;

            case R.id.tvMinus:
                if (count > 1) {
                    count--;
                }
                tvQty.setText("" + count);
//                onUpdateQuantityTask(count);
                break;

            case R.id.btnDesc:
                buttonpressed = 1;
                displayeThemeColorOnButtonOnclick();
                break;

            case R.id.btnReturnPolicy:
                buttonpressed = 2;
                displayeThemeColorOnButtonOnclick();
                break;

            case R.id.btnShippingDetails:
                buttonpressed = 3;
                displayeThemeColorOnButtonOnclick();
                break;

            case R.id.btnDrinkRecipe:
                buttonpressed = 4;
                displayeThemeColorOnButtonOnclick();

                if(drinkReceipeList != null && drinkReceipeList.size() > 0){
                    DialogUtils.showDrinkReceipeDialog(getActivity(), drinkReceipeList,itemDescModel);
                }
                break;

            case R.id.tvAddtoCart:
                requestedQty = tvQty.getText().toString();
                addTocartData(requestedQty);
                isComeFomAddTocartBtn = true;
                break;

            case R.id.tvWishlist:
                if (itemDescModel != null && !itemDescModel.getItemMstId().isEmpty()) {

                    String sku = null;
                    try {
                        sku = URLEncoder.encode(itemDescModel.getItemMstId().trim(), "utf-8");
                    } catch (UnsupportedEncodingException e) {
                        e.printStackTrace();
                    }

//                    if(Constant.SCREEN_LAYOUT == 1){
//                        MainActivity.getInstance().callAddToWishlistWS(itemDescModel.getItemMstId());
//                    }else{
//                        MainActivityDup.getInstance().callAddToWishlistWS(itemDescModel.getItemMstId());
//                    }

//                     Edited by Varun for guest login
                    if(Constant.SCREEN_LAYOUT == 1) {
                        if (UserModel.Cust_mst_ID == null || UserModel.Cust_mst_ID.isEmpty()) {
                            Login.StartLoginDialog("wishlist", context);
                        } else {
                            if (Constant.ISguest) {
                                DialogUtils.showDialog("Option not valid for guest account");
                            } else {
                                if (itemDescModel.getInvType().equalsIgnoreCase("M")){
                                    MainActivity.getInstance().callAddToWishlistWS(Constant.Test_SKU);
                                }else {
                                    MainActivity.getInstance().callAddToWishlistWS(sku);
                                }
                            }
                        }
                    }else {
                        if (UserModel.Cust_mst_ID == null || UserModel.Cust_mst_ID.isEmpty() ) {
                            Login.StartLoginDialog("wishlist", context);
                        } else {
                            if (Constant.ISguest) {
                                DialogUtils.showDialog("Option not valid for guest account");
                            } else {
                                MainActivityDup.getInstance().callAddToWishlistWS(sku);
                            }
                        }
                    }

//                    /END

                }
                break;



            case R.id.tvShare:

                DialogUtils.onShareDialogOptions(context);

//                showSharePopup(view);
                break;

//                Testing
            case R.id.img_plus:
                if (count < 999) {
                    count++;
                }
                tv_item_quantity.setText("" + count);
                requestedQty = String.valueOf(Integer.parseInt(tv_item_quantity.getText().toString()));
                break;

            case R.id.img_minus:
                if (count > 1) {
                    count--;
                }
                tv_item_quantity.setText("" + count);
                requestedQty = String.valueOf(Integer.parseInt(tv_item_quantity.getText().toString()));
                break;

            case R.id.ivAddtoCart_up:
                requestedQty = String.valueOf(Integer.parseInt(tv_item_quantity.getText().toString()));
                ItemDescriptionsFragment.getInstance().addTocartData(requestedQty);
                ItemDescriptionsFragment.getInstance().isComeFomAddTocartBtn = true;
                break;

//                 END

        }
    }

    private void showSharePopup(View view) {

        View popupView = LayoutInflater.from(context.getApplicationContext()).inflate(R.layout.share_popup_layout, null);
        popupWindow = new PopupWindow(
                popupView,
                ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);

        popupWindow.setBackgroundDrawable(new BitmapDrawable());
        popupWindow.setOutsideTouchable(true);
        popupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                //TODO do sth here on dismiss
            }
        });

        TextView email = popupView.findViewById(R.id.tvEmail);
        TextView facebook = popupView.findViewById(R.id.tvFacebook);
        TextView tvTwitter = popupView.findViewById(R.id.tvTwitter);

        if (tvPrice.getText().toString().isEmpty()) {
            email.setEnabled(false);
        } else {
            email.setEnabled(true);
        }

        email.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                popupWindow.dismiss();
                DialogUtils.showEmailDialog(getActivity(), itemDescModel,"ItemDesc");

            }
        });

        tvTwitter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String domainUrl = "";

                popupWindow.dismiss();

                if(!domain.equals("")){
                    domainUrl = "https://" + domain + "/";
                }else{
                    domainUrl = Constant.URL;
                }

                Uri urlToShare = Uri.parse(domainUrl + "Inventory/ItemDetails/" + Constant.STOREID + "/" + itemDescModel.getItemMstId().trim());
                String tweetUrl = "https://twitter.com/intent/tweet?text=&url="
                        + urlToShare;
                Uri uri = Uri.parse(tweetUrl);
                startActivity(new Intent(Intent.ACTION_VIEW, uri));
            }
        });

        facebook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                popupWindow.dismiss();

//                String domainUrl = "";
//
//                if(!domain.equals("")){
//                    domainUrl = "https://" + domain + "/";
//                }else{
//                    domainUrl = Constant.URL;
//                }
//                String url = domainUrl+ "Inventory/ItemDetails/" + Constant.STOREID + "/" + itemDescModel.getItemMstId().trim();

//              Uri urlToShare = Uri.parse(Constant.URL+ "Inventory/ItemDetails/" + Constant.STOREID + "/" + itemDescModel.getItemMstId().trim());

                //sharing old working code start ************
//                ShareCompat.IntentBuilder.from((Activity) context)
//                        .setType("text/plain")
//                        .setChooserTitle("Share URL")
//                        .setText(url)
//                        .startChooser();
                //end ******************


                // working code sharing strat
//                if (isAppInstalled()) {
//
//                    ShareCompat.IntentBuilder.from((Activity) context)
//                            .setType("text/plain")
//                            .setChooserTitle("Share URL")
//                            .setText(url)
//                            .startChooser();
//
////                    Toast.makeText(getApplicationContext(), "facebook app already installed", Toast.LENGTH_SHORT).show();
//                } else {
//                    Intent facebookIntent = new Intent(Intent.ACTION_VIEW);
//                    String facebookUrl = Utils.getFacebookPageURL(context);
//                    facebookIntent.setData(Uri.parse(facebookUrl));
//                    context.startActivity(facebookIntent);
//
////                    Intent mIntent = new Intent(Intent.ACTION_SEND);
////                    mIntent.setType("text/plain");
////                    mIntent.setPackage("com.facebook.katana");
////                    mIntent.putExtra(Intent.EXTRA_TEXT, url);
////                    try {
////                        context.startActivity(mIntent);
////                    } catch (android.content.ActivityNotFoundException ex) {
////                      ex.printStackTrace();
////                    }
////                    Toast.makeText(getApplicationContext(), "facebook app not installing", Toast.LENGTH_SHORT).show();
//                }

                // end share end

                // very old start **********
//                String packageNameFacebook = "com.facebook.katana";
//                String packageNameFacebookLite = "com.facebook.lite";
//
//                PackageManager pm = context.getPackageManager();
//                boolean app_installed = false;
//
//                if(isPackageInstalled(packageNameFacebook,packageNameFacebookLite,pm)){
//                   //facebok installed
//                }else{
//                    //facebook not installed
//                }


//                Intent intent = new Intent(Intent.ACTION_SEND);
//                boolean facebookAppFound = false;
//                    List<ResolveInfo> matches = Objects.requireNonNull(getActivity()).getPackageManager().queryIntentActivities(intent, 0);
//                    for (ResolveInfo info : matches) {
//                        if (info.activityInfo.packageName.toLowerCase().startsWith("com.facebook")) {
//                            intent.setPackage(info.activityInfo.packageName);
//                            facebookAppFound = true;
//                            break;
//                        }
//                    }
//
//                    if(facebookAppFound){
//                        ShareCompat.IntentBuilder.from((Activity) context)
//                                .setType("text/plain")
//                                .setChooserTitle("Share URL")
//                                .setText(url)
//                                .startChooser();
//
//                    }else if (!facebookAppFound) {
//                        String sharerUrl = "https://www.facebook.com/sharer/sharer.php?u=" + url;
//                        intent = new Intent(Intent.ACTION_VIEW, Uri.parse(sharerUrl));
//                    }
//                        startActivity(intent);

                //very old end
            }

        });

//                Intent intent = new Intent(Intent.ACTION_SEND);
//                intent.setType("text/plain");
//                intent.putExtra(Intent.EXTRA_TEXT, urlToShare);
//                startActivity(Intent.createChooser(intent, "Share with"));

//                    Intent share = new Intent(android.content.Intent.ACTION_SEND);
//                    share.setType("text/plain");
//                    share.addFlags(Intent.FLAG_ACTIVITY_CLEAR_WHEN_TASK_RESET);
//
//                    // Add data to the intent, the receiving app will decide
//                    // what to do with it.
//                    share.putExtra(Intent.EXTRA_SUBJECT, "Title Of The Post");
//                    share.putExtra(Intent.EXTRA_TEXT, urlToShare);
//
//                    startActivity(Intent.createChooser(share, "Share link!"));


//                Intent intent = new Intent(Intent.ACTION_SEND);
//                intent.setType("text/plain");
//                intent.putExtra(Intent.EXTRA_TEXT, urlToShare);
//                startActivity(Intent.createChooser(intent, "Share with"));


//                launchFacebook(urlToShare);

//                ShareLinkContent content = new ShareLinkContent.Builder()
//                        .setContentUrl(urlToShare)
//                        .build();

//                Intent shareIntent = new Intent(Intent.ACTION_SEND);
//                shareIntent.setType("text/plain");
//                shareIntent.putExtra(Intent.EXTRA_TEXT, urlToShare);
//                startActivity(Intent.createChooser(shareIntent, "Share via"));


//                /shareOnFacebook(getActivity(),"",urlToShare);

//                CallbackManager callbackManager;
//                ShareDialog shareDialog;
//                FacebookSdk.sdkInitialize(context);
//                shareDialog = new ShareDialog((Activity) context);
//                callbackManager = CallbackManager.Factory.create();
//                String urlToShare = "Constant.WS_BASE_URL" + "Inventory/ItemDetails/" + Constant.STOREID + itemDescModel.getItemMstId().trim();
//                shareDialog.registerCallback(callbackManager, new FacebookCallback<Sharer.Result>() {
//                    @Override
//                    public void onSuccess(Sharer.Result result) {
//                        Log.e("TAG","Facebook Share Success");
////                        logoutFacebook();
//                    }
//
//                    @Override
//                    public void onCancel() {
//                        Log.e("TAG","Facebook Sharing Cancelled by User");
//                    }
//
//                    @Override
//                    public void onError(FacebookException error) {
//                        Log.e("TAG","Facebook Share Success: Error: " + error.getLocalizedMessage());
//                    }
//                });
//
//
//                if (ShareDialog.canShow(ShareLinkContent.class)) {
//                    ShareLinkContent linkContent = new ShareLinkContent.Builder()
//                            .setQuote("Content goes here")
//                            .setContentUrl(Uri.parse(urlToShare))
//                            .build();
//                    shareDialog.show(linkContent);
//                }


//                if(Constant.SCREEN_LAYOUT==1){
//                    MainActivity.sharewithfacebook(itemDescModel);
//
//                    DialogUtils.showFacebookDialog(getActivity(),itemDescModel);
//                }else{
////                    MainActivityDup.sharewithfacebook();
//                    DialogUtils.showFacebookDialog(getActivity(),itemDescModel);
//                }


//                if(itemDescModel.getItemMstId() != null) {
//
//                    String urlToShare = "Constant.WS_BASE_URL" + "Inventory/ItemDetails/" + Constant.STOREID + itemDescModel.getItemMstId().trim();
////                http://192.168.172.244:888/Inventory/ItemDetails/707/811538010115
//                    Intent intent = new Intent(Intent.ACTION_SEND);
//                    intent.setType("text/plain");
//                    intent.putExtra(Intent.EXTRA_TEXT, urlToShare);
//
//// See if official Facebook app is found
//                    boolean facebookAppFound = false;
//                    List<ResolveInfo> matches = Objects.requireNonNull(getActivity()).getPackageManager().queryIntentActivities(intent, 0);
//                    for (ResolveInfo info : matches) {
//                        if (info.activityInfo.packageName.toLowerCase().startsWith("com.facebook")) {
//                            intent.setPackage(info.activityInfo.packageName);
//                            facebookAppFound = true;
//                            break;
//                        }
//                    }
//
//                    if (!facebookAppFound) {
//                        String sharerUrl = "https://www.facebook.com/sharer/sharer.php?u=" + urlToShare;
//                        intent = new Intent(Intent.ACTION_VIEW, Uri.parse(sharerUrl));
//                    }
//                    startActivity(intent);
//

//                }


        popupWindow.showAsDropDown(view);
    }

    public boolean isAppInstalled() {
//        try {
//            context.getApplicationContext().getPackageManager().getApplicationInfo("com.facebook.katana", 0);
//            return true;
//        } catch (PackageManager.NameNotFoundException e) {
//            return false;
//        }
//
        try {
//            context.getApplicationContext().getPackageManager().getApplicationInfo("com.facebook.katana", 0);
            ApplicationInfo info = context.getPackageManager().
                    getApplicationInfo("com.facebook.android", 0 );
            return true;
        } catch (PackageManager.NameNotFoundException e) {
            return false;
        }
    }



//    private boolean isPackageInstalled(String packageName, String packageNameFacebookLite, PackageManager packageManager) {
//        try {
//            packageManager.getPackageInfo(packageName, 0);
//            packageManager.getPackageInfo(packageNameFacebookLite, 0);
//            return true;
//        } catch (PackageManager.NameNotFoundException e) {
//            return false;
//        }
//    }


//    public final void  launchFacebook(Uri urlToShare) {
//
//        //page id -6858542694333680554-0
//
//        final String urlFb = "fb://post/"+urlToShare;
//        Intent intent = new Intent(Intent.ACTION_VIEW);
//        intent.setData(Uri.parse(urlFb));
//
//        // If a Facebook app is installed, use it. Otherwise, launch
//        // a browser
//        final PackageManager packageManager = context.getPackageManager();
//        List<ResolveInfo> list =
//                packageManager.queryIntentActivities(intent,
//                        PackageManager.MATCH_DEFAULT_ONLY);
//        if (list.size() == 0) {
//            final String urlBrowser = "https://www.facebook.com/"+urlToShare;
//            intent.setData(Uri.parse(urlBrowser));
//        }
//
//        startActivity(intent);
//
//    }


    private void displayeThemeColorOnButtonOnclick() {

        if (btnDesc.isShown() && buttonpressed == 1) {

            tvDetailsView.setText(Html.fromHtml(itemDescModel.getExtendDesc()));

            setThemeColorBackgroundToButtonWithRadius(btnDesc);
            setWhiteBackgroundToButtonWithRadius(btnReturnPolicy);
            setWhiteBackgroundToButtonWithRadius(btnShippingDetails);
            setWhiteBackgroundToButtonWithRadius(btnDrinkRecipe);

            btnDesc.setTextColor(Color.WHITE);
            btnReturnPolicy.setTextColor(Color.BLACK);
            btnShippingDetails.setTextColor(Color.BLACK);
            btnDrinkRecipe.setTextColor(Color.BLACK);

        } else if (btnReturnPolicy.isShown() && buttonpressed == 2) {

            tvDetailsView.setText(Html.fromHtml(itemDescModel.getReturnDesc()));

            setThemeColorBackgroundToButtonWithRadius(btnReturnPolicy);
            setWhiteBackgroundToButtonWithRadius(btnDesc);
            setWhiteBackgroundToButtonWithRadius(btnShippingDetails);
            setWhiteBackgroundToButtonWithRadius(btnDrinkRecipe);

            btnReturnPolicy.setTextColor(Color.WHITE);
            btnDesc.setTextColor(Color.BLACK);
            btnShippingDetails.setTextColor(Color.BLACK);
            btnDrinkRecipe.setTextColor(Color.BLACK);

        } else if (btnShippingDetails.isShown() && buttonpressed == 3) {

            tvDetailsView.setText(Html.fromHtml(itemDescModel.getShippingDesc()));

//            btnShippingDetails.setBackgroundColor(Color.parseColor(Constant.themeModel.ThemeColor));
//            btnReturnPolicy.setBackgroundColor(Color.WHITE);
//            btnDesc.setBackgroundColor(Color.WHITE);
//            btnDrinkRecipe.setBackgroundColor(Color.WHITE);
            setThemeColorBackgroundToButtonWithRadius(btnShippingDetails);
            setWhiteBackgroundToButtonWithRadius(btnDesc);
            setWhiteBackgroundToButtonWithRadius(btnReturnPolicy);
            setWhiteBackgroundToButtonWithRadius(btnDrinkRecipe);

            btnShippingDetails.setTextColor(Color.WHITE);
            btnReturnPolicy.setTextColor(Color.BLACK);
            btnDesc.setTextColor(Color.BLACK);
            btnDrinkRecipe.setTextColor(Color.BLACK);


        } else if (btnDrinkRecipe.isShown() && buttonpressed == 4) {
//            btnDrinkRecipe.setBackgroundColor(Color.parseColor(Constant.themeModel.ThemeColor));
//            btnShippingDetails.setBackgroundColor(Color.WHITE);
//            btnReturnPolicy.setBackgroundColor(Color.WHITE);
//            btnDesc.setBackgroundColor(Color.WHITE);

            setThemeColorBackgroundToButtonWithRadius(btnDrinkRecipe);
            setWhiteBackgroundToButtonWithRadius(btnDesc);
            setWhiteBackgroundToButtonWithRadius(btnReturnPolicy);
            setWhiteBackgroundToButtonWithRadius(btnShippingDetails);

            btnDrinkRecipe.setTextColor(Color.WHITE);
            btnReturnPolicy.setTextColor(Color.BLACK);
            btnDesc.setTextColor(Color.BLACK);
            btnShippingDetails.setTextColor(Color.BLACK);
        }
    }

    public void addTocartData(String requestedQty) {

        this.requestedQty = requestedQty;
//          Edited by Varun for multi pack when add to cart is click
        if (Constant.invType.equals("M")){
            String sku = null;

            if (Constant.Test_SKU.isEmpty() || Constant.Test_SKU==null || Constant.Test_SKU.equals("")){

                Utils.ValidationDialog(context, "Please Select Item");

            }else {
               sku = Constant.Test_SKU;

                if (UserModel.Cust_mst_ID != null) {

//                String cartWSurl = Constant.WS_BASE_URL + Constant.DELETE_CART + "0" + "/" + "Cart" + "/" + UserModel.Cust_mst_ID +
//                        "/" + sku + "/" + this.requestedQty +
//                        "/" + Constant.STOREID + "/" + "0" + "/" + "add" + "/" + Constant.invType;

                    String cartWSurl = Constant.WS_BASE_URL + Constant.DELETE_CART + "0" + "/" + "Cart" + "/" + UserModel.Cust_mst_ID +
                            "/" + sku + "/" + requestedQty +
                            "/" + Constant.STOREID + "/" + "0" + "/" + "add" + "/" + Constant.invType;

                    TaskAddtoCart taskAddToCart = new TaskAddtoCart(this);
                    taskAddToCart.execute(cartWSurl);
                } else {
//                String cartWSurl = Constant.WS_BASE_URL + Constant.DELETE_CART + "0" + "/" + "Cart" + "/" + "0" +
//                        "/" + sku + "/" + this.requestedQty +
//                        "/" + Constant.STOREID + "/" + DeviceInfo.getDeviceId(context) + "0011" + "/" + "add" + "/" + Constant.invType;;

                    String cartWSurl = Constant.WS_BASE_URL + Constant.DELETE_CART + "0" + "/" + "Cart" + "/" + "0" +
                            "/" + sku + "/" + requestedQty +
                            "/" + Constant.STOREID + "/" + DeviceInfo.getDeviceId(context) + "0011" + "/" + "add" + "/" + Constant.invType;
                    ;

                    TaskAddtoCart taskAddToCart = new TaskAddtoCart(this);
                    taskAddToCart.execute(cartWSurl);
                }

            }

        }else {
//            END
            if (itemDescModel != null && !itemDescModel.getItemMstId().isEmpty()) {

                String sku = null;
                try {
                    sku = URLEncoder.encode(itemDescModel.getItemMstId().trim(), "utf-8");
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }

                if (UserModel.Cust_mst_ID != null) {

//                String cartWSurl = Constant.WS_BASE_URL + Constant.DELETE_CART + "0" + "/" + "Cart" + "/" + UserModel.Cust_mst_ID +
//                        "/" + sku + "/" + this.requestedQty +
//                        "/" + Constant.STOREID + "/" + "0" + "/" + "add" + "/" + Constant.invType;

                    String cartWSurl = Constant.WS_BASE_URL + Constant.DELETE_CART + "0" + "/" + "Cart" + "/" + UserModel.Cust_mst_ID +
                            "/" + sku + "/" + requestedQty +
                            "/" + Constant.STOREID + "/" + "0" + "/" + "add" + "/" + Constant.invType;

                    TaskAddtoCart taskAddToCart = new TaskAddtoCart(this);
                    taskAddToCart.execute(cartWSurl);
                } else {
//                String cartWSurl = Constant.WS_BASE_URL + Constant.DELETE_CART + "0" + "/" + "Cart" + "/" + "0" +
//                        "/" + sku + "/" + this.requestedQty +
//                        "/" + Constant.STOREID + "/" + DeviceInfo.getDeviceId(context) + "0011" + "/" + "add" + "/" + Constant.invType;;

                    String cartWSurl = Constant.WS_BASE_URL + Constant.DELETE_CART + "0" + "/" + "Cart" + "/" + "0" +
                            "/" + sku + "/" + requestedQty +
                            "/" + Constant.STOREID + "/" + DeviceInfo.getDeviceId(context) + "0011" + "/" + "add" + "/" + Constant.invType;
                    ;

                    TaskAddtoCart taskAddToCart = new TaskAddtoCart(this);
                    taskAddToCart.execute(cartWSurl);
                }

            }

        }
    }

    public void updateToCartData(UpdateCartQuantity updateCartQuantity) {

        String noteCartId = "0";

        if(updateCartQuantity != null && updateCartQuantity.getNote()!= null){
            noteCartId = updateCartQuantity.getNote();
        }else{
            noteCartId = String.valueOf(cartId);
        }

        if (itemDescModel != null && !itemDescModel.getItemMstId().isEmpty()) {

            String sku = null;
            try {
                sku = URLEncoder.encode(itemDescModel.getItemMstId().trim(), "utf-8");
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }

            if (UserModel.Cust_mst_ID != null) {

//                String cartWSurl = Constant.WS_BASE_URL + Constant.DELETE_CART + cartId + "/" + "Cart" + "/" + UserModel.Cust_mst_ID +
//                        "/" + itemDescModel.getItemMstId().trim() + "/" + "1" +
//                        "/" + Constant.STOREID + "/" + "0" + "/" + "Updatemoreincart/I";

                String cartWSurl = Constant.WS_BASE_URL + Constant.DELETE_CART + noteCartId + "/" + "Cart" + "/" + UserModel.Cust_mst_ID +
                        "/" + sku + "/" + "1" +
                        "/" + Constant.STOREID + "/" + "0" + "/" + "Updatemoreincart" + "/" + Constant.invType; ;

                TaskUpdatetoCart taskUpdatetoCart = new TaskUpdatetoCart(this);
                taskUpdatetoCart.execute(cartWSurl);
            }else{

                String cartWSurl = Constant.WS_BASE_URL + Constant.DELETE_CART + noteCartId + "/" + "Cart" + "/" + "0" +
                        "/" + sku + "/" + "1" +
                        "/" + Constant.STOREID + "/" + "0" + "/" + "Updatemoreincart" + "/" + Constant.invType;;

                TaskUpdatetoCart taskUpdatetoCart = new TaskUpdatetoCart(this);
                taskUpdatetoCart.execute(cartWSurl);
            }
        }
    }

    @SuppressLint("LongLogTag")
    @Override
    public void addToCartEventResult(UpdateCartQuantity addToCart) {

        if (addToCart != null) {

            if (addToCart.getResult().equalsIgnoreCase("success")) {
                DialogUtils.showDialog("Added to cart!");
                Utils.vibrateDevice(context);
                onGetCartData();

            } else if (addToCart.getResult().equalsIgnoreCase("Already added")) {

                if (isComeFomAddTocartBtn) {
                    if (cartQtyOfItem.isEmpty()) {
                        DialogUtils.notEnoughQuantityDialog(context, addToCart, Integer.parseInt(requestedQty), "itemDesc", addToCart.getQty());
                    } else {
                        DialogUtils.notEnoughQuantityDialog(context, addToCart, Integer.parseInt(requestedQty), "itemDesc", cartQtyOfItem);
                    }
                    isComeFomAddTocartBtn = false;
                } else {
                }

            } else if (addToCart.getResult().equalsIgnoreCase("Not enough Stock")) {
//                DialogUtils.notEnoughQuantityDialog(context, addToCart, Integer.parseInt(requestedQty), "NotenoughStock", cartQtyOfItem);
                DialogUtils.notEnoughQuantityNewDialog(context, addToCart, Integer.parseInt(requestedQty), "NotenoughStock", cartQtyOfItem, "fromItemDesc");

//                tvQty.setText(addToCart.getQty());
            } else {
//                Toast.makeText(context, getString(R.string.str_network_message), Toast.LENGTH_SHORT).show();
            }
        }
    }

    public void onGetCartData() {

        String url = null;
        if (UserModel.Cust_mst_ID != null) {
//            url = Constant.WS_BASE_URL + Constant.GET_CUSTOMER_CARD_DATA + UserModel.Cust_mst_ID + "/" + Constant.MY_CART + Constant.STOREID;
            url = Constant.WS_BASE_URL + Constant.GET_CUSTOMER_CARD_DATA_V1 + UserModel.Cust_mst_ID + "/" + Constant.MY_CART + Constant.STOREID + Constant.ENCODE_TOKEN_ID ;
            TaskCart taskCart = new TaskCart(this, "");
            taskCart.execute(url);
        } else {
            if (isAdded()) {
//                url = Constant.WS_BASE_URL + Constant.GET_CUSTOMER_CARD_DATA + DeviceInfo.getDeviceId(context) + "0011" + "/" + Constant.SESSION + Constant.STOREID;
                url = Constant.WS_BASE_URL + Constant.GET_CUSTOMER_CARD_DATA_V1 + DeviceInfo.getDeviceId(context) + "0011" + "/" + Constant.SESSION + Constant.STOREID + Constant.ENCODE_TOKEN_ID;
                TaskCart taskCart = new TaskCart(this, "");
                taskCart.execute(url);
            }
        }
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

                        if (!liShoppingCard.get(i).getItemMstId().isEmpty() && !itemDescModel.getItemMstId().isEmpty()) {

//                            Edited by Varun for multi pack
//                            if (Constant.invType.equals("M")){
//                                if (liShoppingCard.get(i).getItemMstId().equals(itemDescModel.getLstInventoryModel().get(i).getItemMstId())){
//                                    cartQtyOfItem = liShoppingCard.get(i).getQty();
//                                    cartId = liShoppingCard.get(i).getCartID();
//                                }
//                            }else {
//                                END

                                if (liShoppingCard.get(i).getItemMstId().equalsIgnoreCase(itemDescModel.getItemMstId())) {

                                    cartQtyOfItem = liShoppingCard.get(i).getQty();
                                    cartId = liShoppingCard.get(i).getCartID();
                                }
//                            }
                        }
                    }
                    MainActivity.countMenu.setTitle(String.valueOf(quntity));
                    MainActivity.getInstance().onShoppingCardResult(liShoppingCard, s);
                }
            } else if (Constant.SCREEN_LAYOUT == 2) {
                if (liShoppingCard.get(0).getCartID() == 0) {
                    /** Clear Shopping Cart Icon Count **/
                    MainActivityDup.getInstance().updateShoppingCartItemCount(0);
                } else {
                    int quntity = 0;
                    for (int i = 0; i < liShoppingCard.size(); i++) {
                        quntity = quntity + Integer.parseInt(liShoppingCard.get(i).getQty());

                        if (!liShoppingCard.get(i).getItemMstId().isEmpty() && !itemDescModel.getItemMstId().isEmpty()) {

                            if (liShoppingCard.get(i).getItemMstId().equalsIgnoreCase(itemDescModel.getItemMstId())) {

                                cartQtyOfItem = liShoppingCard.get(i).getQty();
                                cartId = liShoppingCard.get(i).getCartID();
                            }

                        }
                    }
                    MainActivityDup.countMenu.setTitle(String.valueOf(quntity));
                    MainActivityDup.getInstance().onShoppingCardResult(liShoppingCard, s);
                }
            }

        }
    }

    @Override
    public void updateCartResult(UpdateCartQuantity updateCart) {

        if (updateCart.getResult().equalsIgnoreCase("success")) {
            Utils.vibrateDevice(context);
            onGetCartData();
        }
    }


//    public void onUpdateQuantityTask(int count) {
//
//        requestedQty = String.valueOf(count);
//        String updateQuantityUrl = "";
////        updateQuantityUrl = Constant.WS_BASE_URL + Constant.DELETE_CART          /*  http://192.168.172.211:889/WebStoreRestService.svc/InsertCartData/    */
////                + cartId + "/"                 /*  3829/         */
////                + "cart/" + UserModel.Cust_mst_ID + "/"                          /*  cart/188723/  */
////                + itemDescModel.getItemMstId() + "/"                 /*  Item Id added  discussion after mamta/         */
////                + requestedQty + "/"                                                 /*  4/            */
////                + Constant.STOREID + "/" + "0/"                                 /*  707/0/        */
////                + "updatecart/I"                                                 /*  updatecart/I  */
////        ;
//
//        updateQuantityUrl = Constant.WS_BASE_URL + Constant.DELETE_CART          /*  http://192.168.172.211:889/WebStoreRestService.svc/InsertCartData/    */
//                + cartId + "/"                 /*  3829/         */
//                + "cart/" + UserModel.Cust_mst_ID + "/"                          /*  cart/188723/  */
//                + itemDescModel.getItemMstId() + "/"                 /*  Item Id added  discussion after mamta/         */
//                + requestedQty + "/"                                                 /*  4/            */
//                + Constant.STOREID + "/" + "0/"                                 /*  707/0/        */
//                + "updatecart" + "/" + Constant.invType;                                                 /*  updatecart/I  */
//        ;
//
//        TaskUpdateCartQuantity updateCartQuantity = new TaskUpdateCartQuantity(this);
//        updateCartQuantity.execute(updateQuantityUrl);
//    }


    @Override
    public void onUpdateQuantityResult(UpdateCartQuantity updateCartQuantity) {

        if (updateCartQuantity.getResult().equalsIgnoreCase("success")) {
            onGetCartData();
        } else if (updateCartQuantity.getResult().equalsIgnoreCase("Not enough Stock")) {
//            DialogUtils.notEnoughQuantityDialog(context, updateCartQuantity, Integer.parseInt(requestedQty), "NotenoughStock", cartQtyOfItem);
            DialogUtils.notEnoughQuantityNewDialog(context, updateCartQuantity, Integer.parseInt(requestedQty), "NotenoughStock", cartQtyOfItem,"fromItemDesc");
//            tvQty.setText(updateCartQuantity.getQty());
        } else {
//            Toast.makeText(context, getString(R.string.str_network_message), Toast.LENGTH_SHORT).show();
        }

    }

    @Override
    public void recommandedItemsResult(List<ItemDescModel> recommandedItemList) {

        Log.e(String.valueOf(ItemDescriptionsFragment.this), "recommandedItemsResult: "+recommandedItemList.size() );
            if (recommandedItemList.size() > 0) {
                cusWhoBoughtAlsoView.setVisibility(View.VISIBLE);

                this.recommandedItemList = recommandedItemList;
                RecommandedItemAdapter recommandedItemAdapter = new RecommandedItemAdapter(context, recommandedItemList);
                rec_home.setAdapter(recommandedItemAdapter);

        }else {
            cusWhoBoughtAlsoView.setVisibility(View.GONE);

//            Edited by Varun for recomemded Screen List GONE
            rec_home.setVisibility(View.GONE);
//            END

        }
        onGetCartData();
    }

//    @Override
//    public void recommandedItemResult(ItemDescModel recommandedItemDescModel) {
//
//        onItemDescResult(recommandedItemDescModel);
//
//    }

    public void SendEmailData(ItemDescModel tempItemDescModel, String email, String secEmail, String personalMsg, String subject, int carboncopy) {

        if (tempItemDescModel != null) {
            if (tempItemDescModel.getItemMstId() != null && !tempItemDescModel.getItemMstId().isEmpty()) {

                String cust_ID;

                if (UserModel.Cust_mst_ID != null && !UserModel.Cust_mst_ID.equals("null")) {
                    cust_ID = UserModel.Cust_mst_ID;
                } else
                {
                    cust_ID = "0";
                }

            //code for sharing proper url uncomment below code till end
                if(personalMsg.isEmpty()){
                    personalMsg = null;
                }
                if(subject.isEmpty()){
                    subject = null;
                }

                String baseStr = "";

                if(!domain.equals("")){
                    baseStr = domain;
                }else{
                    String urlChnage = Constant.URL;
                    urlChnage = urlChnage.replace("/","_");

                    String[] separated = urlChnage.split("__");
                    String basedomain = separated[1];

                    if(basedomain.contains("_")){
                        basedomain = basedomain.replace("_","");
                    }

                    baseStr = basedomain.substring(basedomain.lastIndexOf("//") + 1);
                }

            //end - when uncomment this code comment below two line till ********

//                String baseStr = "";
//                baseStr = Constant.WS_BASE.substring(Constant.WS_BASE.lastIndexOf("/") + 1);
//                ************

                String sku = null;
                try {
                    sku = URLEncoder.encode(tempItemDescModel.getItemMstId().trim(), "utf-8");
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }

                String sendEmailurl = Constant.WS_BASE_URL + Constant.SEND_EMAIL_A_FRIEND + "/"
                        + sku + "/" + Constant.STOREID + "/"
                        + email + "/"
                        + secEmail + "/"
                        + personalMsg + "/"
                        + subject + "/"
                        + carboncopy + "/"
                        + cust_ID + "/" + baseStr;

                TaskSendEmail taskSendEmail = new TaskSendEmail(this);
                taskSendEmail.execute(sendEmailurl);
            }
        }
    }


    @Override
    public void onSendEmailResult(OrderSummary orderSummary) {

        if (orderSummary != null) {
            DialogUtils.showDialog("Your message has been sent.");
        }
//        else {
//            Toast.makeText(context, "Please Fill All Email Details", Toast.LENGTH_SHORT).show();
//        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        if (popupWindow != null) {
            popupWindow.dismiss();
        }
        context = null;

    }

    @Override
    public void onDrinkRecipeResult(List<ItemDescModel> drinkReceipeList) {

        if(drinkReceipeList != null && drinkReceipeList.size() > 0){
            this.drinkReceipeList = drinkReceipeList;
        }

            CallWSForDisplayedRecommandedItems();
    }

    public void onSendEmailDrinkRecipe(String ItemId, String emailTo, String subject, int drinkReceipeId) {


//        old commented line on 23thjan2021 start ___________
//        String baseStr = Constant.WS_BASE.substring(Constant.WS_BASE.lastIndexOf("/") + 1);
        // end _____________

        String baseStr = "";

        if(!domain.equals("")){
            baseStr = domain;
        }else{
            baseStr = Constant.WS_BASE.substring(Constant.WS_BASE.lastIndexOf("/") + 1);
        }

        if(UserModel.Cust_mst_ID != null && !UserModel.Cust_mst_ID.equals("null") && !UserModel.Email.equals("")){

            String sendEmailurl = Constant.WS_BASE_URL + Constant.SEND_DRINK_RECIPES + "/"
                    + ItemId + "/" + Constant.STOREID + "/"
                    + UserModel.Email + "/"
                    + emailTo + "/"
                    + drinkReceipeId + "/"
                    + subject + "/"
                    + UserModel.Cust_mst_ID + "/"
                    + baseStr;

            TaskEmailDrinkReceipe taskEmailDrinkReceipe = new TaskEmailDrinkReceipe(this);
            taskEmailDrinkReceipe.execute(sendEmailurl);

        }else{

            String sendEmailurl = Constant.WS_BASE_URL + Constant.SEND_DRINK_RECIPES + "/"
                    + ItemId + "/" + Constant.STOREID + "/"
                    + emailTo + "/"
                    + emailTo + "/"
                    + drinkReceipeId + "/"
                    + subject + "/"
                    + 0 + "/"
                    + baseStr;

            TaskEmailDrinkReceipe taskEmailDrinkReceipe = new TaskEmailDrinkReceipe(this);
            taskEmailDrinkReceipe.execute(sendEmailurl);
        }
    }

    @Override
    public void onSendDrinkEmailResult(ReOrderModel reOrderModel) {
        displayeThemeColorOnButton();
    }

    @Override
    public void onShippingTaskResult(List<ShippingData> liShippingData) {

//        progressBar.setVisibility(View.GONE);

        if (liShippingData != null && liShippingData.size() > 0) {
            if(liShippingData.get(0).getBSSetupDeliveryOption() == true){
                if (itemDescModel.getShippingDesc() != null && !itemDescModel.getShippingDesc().isEmpty()) {
                    btnShippingDetails.setVisibility(View.VISIBLE);
                } else {
                    btnShippingDetails.setVisibility(View.GONE);
                }
            }
        }

        CallSiteInfoWSForDomain();
        CallWSForDrinkReceipe();
    }


    public void CallSiteInfoWSForDomain() {
        String url = Constant.WS_BASE_URL + Constant.GET_SITE_INFO + "/" + Constant.STOREID;
        TaskDomain taskDomain = new TaskDomain(this,getActivity());
        taskDomain.execute(url);
    }

    @Override
    public void onDomainResult(SiteInfoModel siteInfoModel) {
        if(siteInfoModel != null){
            if(siteInfoModel.getEcomURL()!= null && !siteInfoModel.getEcomURL().isEmpty()){
                domain = siteInfoModel.getEcomURL();
            }
        }
    }

    @Override
    public void onResume() {
        super.onResume();

        if(Constant.SCREEN_LAYOUT == 2){
            MainActivityDup.getInstance().showbackButton();
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


    public void updateToCartData(int finalRequested_Quantity, UpdateCartQuantity updateCartQuantity) {

        String noteCartId = "0";

        if(updateCartQuantity != null && updateCartQuantity.getNote()!= null && !updateCartQuantity.getNote().trim().isEmpty()){
            noteCartId = updateCartQuantity.getNote();
        }else{
            noteCartId = String.valueOf(cartId);
        }

        if (itemDescModel != null && !itemDescModel.getItemMstId().isEmpty()) {
            String sku = null;

            if (Constant.invType.equals("M")){

                if (Constant.Test_SKU.isEmpty() || Constant.Test_SKU==null || Constant.Test_SKU.equals("")){

                    Utils.ValidationDialog(context, "Please Select Item");

                }else {
                    sku = Constant.Test_SKU;
                    }
            }else {

                try {
                    sku = URLEncoder.encode(itemDescModel.getItemMstId().trim(), "utf-8");
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
            }

            if (UserModel.Cust_mst_ID != null) {

                String cartWSurl = Constant.WS_BASE_URL + Constant.DELETE_CART + noteCartId + "/" + "Cart" + "/" + UserModel.Cust_mst_ID +
                        "/" + sku + "/" + finalRequested_Quantity +
                        "/" + Constant.STOREID + "/" + "0" + "/" + "Updatemoreincart" + "/" + Constant.invType;

                TaskUpdatetoCart taskUpdatetoCart = new TaskUpdatetoCart(this);
                taskUpdatetoCart.execute(cartWSurl);
            } else {

                String cartWSurl = Constant.WS_BASE_URL + Constant.DELETE_CART + noteCartId + "/" + "Cart" + "/" + "0" +
                        "/" + sku + "/" + finalRequested_Quantity +
                        "/" + Constant.STOREID + "/" + "0" + "/" + "Updatemoreincart"+ "/" + Constant.invType;


                TaskUpdatetoCart taskUpdatetoCart = new TaskUpdatetoCart(this);
                taskUpdatetoCart.execute(cartWSurl);
            }
        }
    }

    public void openEmailDialog() {

        DialogUtils.showEmailDialog(getActivity(), itemDescModel,"ItemDesc");

    }

    public void shareLinkwithChooser() {

        String domainUrl = "";

        if(!domain.equals("")){
            domainUrl = "https://" + domain + "/";
        }else{
            domainUrl = Constant.URL;
        }

        String url = domainUrl+ "Inventory/ItemDetails/" + Constant.STOREID + "/" + itemDescModel.getItemMstId().trim();

        try {
            Intent i = new Intent(Intent.ACTION_SEND);
            i.setType("text/plain");
            i.putExtra(Intent.EXTRA_SUBJECT, "Share URL");
//            String sAux = "\nWorldâ€™s first ever App powered by Voice and Artificial Intelligence.\n\n";
            String sAux = "";
//                    sAux = sAux + "https://play.google.com/store/apps/details?id=" + getApplication().getPackageName() + "\n\n";
            sAux = sAux + url;
            i.putExtra(Intent.EXTRA_TEXT, sAux);
            startActivity(Intent.createChooser(i, "choose one"));

        } catch (Exception e) {
            //e.toString();
            Log.d("exception:", "" + e);
        }
    }

//    @Override
//    public void onMultipackcheck(int position, List<lstInventoryModel> lstInventoryModel) {
//
//        Toast.makeText(context, ""+position, Toast.LENGTH_SHORT).show();
//    }

}

