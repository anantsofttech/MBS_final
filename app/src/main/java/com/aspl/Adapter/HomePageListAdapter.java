package com.aspl.Adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;
import androidx.core.content.ContextCompat;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.aspl.Utils.Constant;
import com.aspl.Utils.DeviceInfo;
import com.aspl.Utils.DialogUtils;
import com.aspl.Utils.Utils;
import com.aspl.fragment.HomepageFragment;
import com.aspl.fragment.Login;
import com.aspl.mbs.MainActivity;
import com.aspl.mbs.MainActivityDup;
import com.aspl.mbs.R;
import com.aspl.mbsmodel.HomeItemModel;
import com.aspl.mbsmodel.UserModel;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.GlideDrawableImageViewTarget;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.List;

/**
 * Created by admin on 1/2/2018.
 */

public class HomePageListAdapter extends RecyclerView.Adapter<HomePageListAdapter.HomeListHolder> {

    //List<TestModel> testModels;
    private double promoprice =  0.00;
    private int resquantity;
    List<HomeItemModel> listHomrItem;
    private String imgUrl = Constant.IMG_BASE + Constant.IMG_BASE_URL + Constant.STOREID + "/";
    private String imgNoImageUrl = Constant.IMG_BASE + Constant.IMG_NO_IMAGE + "/";
    //http://192.168.172.211:888/WebStoreImages/NoImage/

    Context context;
    HomeListItemAdapterEvent homeListItemAdapterEvent;

    public interface HomeListItemAdapterEvent {
        //  void onRemoveClick(int position, List<ShoppingCardModel> listHomrItem);

        //void onMoveToCartClick(int position, List<ShoppingCardModel> listHomrItem);
    }

    public HomePageListAdapter(Context context, HomeListItemAdapterEvent homeListItemAdapterEvent, List<HomeItemModel> liShoppingCat) {
        this.context = context;
        this.homeListItemAdapterEvent = homeListItemAdapterEvent;
        /*this.testModels = testModelList;*/
        this.listHomrItem = liShoppingCat;

    }

    @Override
    public HomeListHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_rv_home_item, parent, false);
        return new HomeListHolder(itemView);
    }

    @Override
    public void onBindViewHolder(HomeListHolder holder, @SuppressLint("RecyclerView") int position)  {
        //Bitmap bitmap = Utils.textAsBitmap("Technical Problem", 58);
        //Drawable d = new BitmapDrawable(context.getResources(), bitmap);





        if(listHomrItem != null && listHomrItem.size()>0) {

//         ********************** Edited by Varun on 16 Aug 2022 **************************

            if (listHomrItem.get(position).getInvLargeImageFullPath()!=null || !listHomrItem.get(position).getInvLargeImageFullPath().isEmpty()) {
                Glide.with(context).load(listHomrItem.get(position).getInvLargeImageFullPath())
                        .placeholder(R.drawable.noimage)
                        .diskCacheStrategy(DiskCacheStrategy.ALL)
                        .skipMemoryCache(true).into(holder.img_item);
        }
//            ********************* END ***********************

            //             Edited by Varun for sale indicator

            if (listHomrItem.get(position).getPromoPrice() != null && !listHomrItem.get(position).getPromoPrice().isEmpty()){

                if (!listHomrItem.get(position).getPromoPrice().equalsIgnoreCase("0.00") && !listHomrItem.get(position).getPromoPrice().equals(promoprice)) {


                    if (listHomrItem.get(position).getItemonPromotionIndicator().equalsIgnoreCase("NI")) {
                        holder.fl_sale_right.setVisibility(View.GONE);
                        holder.fl_sale_right.setVisibility(View.GONE);
                        holder._fl_sale_hang.setVisibility(View.GONE);
                    }
                    if (listHomrItem.get(position).getItemonPromotionIndicator().equalsIgnoreCase("LR")) {
                        holder.fl_sale_right.setVisibility(View.VISIBLE);
                    }
                    if (listHomrItem.get(position).getItemonPromotionIndicator().equalsIgnoreCase("UL")) {
                        holder.fl_sale_left.setVisibility(View.VISIBLE);
                    }
                    if (listHomrItem.get(position).getItemonPromotionIndicator().equalsIgnoreCase("HT")) {
                        holder._fl_sale_hang.setVisibility(View.VISIBLE);
                    }
                }
            }else {

            }
            Log.e("formatcartprice","formatcartprice:" +listHomrItem.get(position).getFormatcartPrice());
//            if (listHomrItem.get(position).getFormatcartPrice() != null && !listHomrItem.get(position).getFormatcartPrice().isEmpty()) {
//                if (!listHomrItem.get(position).getFormatcartPrice().equalsIgnoreCase("0.00") && !listHomrItem.get(position).getFormatcartPrice().equals(promoprice)) {
//
//                    if (listHomrItem.get(position).getItemonPromotionIndicator().equalsIgnoreCase("NI")) {
//                        holder.fl_sale_right.setVisibility(View.GONE);
//                        holder.fl_sale_right.setVisibility(View.GONE);
//                        holder._fl_sale_hang.setVisibility(View.GONE);
//                    }
//                    if (listHomrItem.get(position).getItemonPromotionIndicator().equalsIgnoreCase("LR")) {
//                        holder.fl_sale_right.setVisibility(View.VISIBLE);
//                    }
//                    if (listHomrItem.get(position).getItemonPromotionIndicator().equalsIgnoreCase("UL")) {
//                        holder.fl_sale_left.setVisibility(View.VISIBLE);
//                    }
//                    if (listHomrItem.get(position).getItemonPromotionIndicator().equalsIgnoreCase("HT")) {
//                        holder._fl_sale_hang.setVisibility(View.VISIBLE);
//                    }
//                }
//            }

//            END
            if (listHomrItem.get(position).getDesc1() != null && !listHomrItem.get(position).getDesc1().isEmpty()) {

                if(listHomrItem.get(position).getDesc1().contains("()")){
                    String desc1 = listHomrItem.get(position).getDesc1().replace("()","");
                    holder.txtitemtitle.setText(Utils.capitalizeEachWord(desc1.trim()));
                }else{
                    holder.txtitemtitle.setText(Utils.capitalizeEachWord(listHomrItem.get(position).getDesc1().trim()));
                }
            }

////            Edited by Varun for multi pack when comes in home page then add to cart and wishlist option Gone
            if (listHomrItem.get(position).getInvType().equals("M")){
                if (listHomrItem.get(position).getType() != null && !listHomrItem.get(position).getType().isEmpty()) {
                    holder.rl_wishlist.setVisibility(View.GONE);
                    holder.ll_qtyandCart.setVisibility(View.GONE);
                    holder.txtprice.setText(listHomrItem.get(position).getPrice());
                    if (listHomrItem.get(position).getPromoPrice() != null && !listHomrItem.get(position).getPromoPrice().toString().isEmpty() && !listHomrItem.get(position).getPromoPrice().toString().equalsIgnoreCase("0.00")) {
                        holder.txtPromoprice.setVisibility(View.VISIBLE);
                        holder.txtPromoprice.setText("$" + listHomrItem.get(position).getPromoPrice());
                        holder.txtprice.setPaintFlags(holder.txtPromoprice.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
                        holder.txtprice.setTextColor(Color.GRAY);
                    } else {
                        holder.txtPromoprice.setVisibility(View.GONE);
                        holder.txtprice.setTextColor(Color.BLACK);
                        holder.txtprice.setPaintFlags(holder.txtPromoprice.getPaintFlags() & (~Paint.STRIKE_THRU_TEXT_FLAG));
                        holder.txtprice.setGravity(Gravity.CENTER);
                    }
                }
                if (listHomrItem.get(position).getPrice()!=null && listHomrItem.get(position).getDescAll()!=null){
                    holder.ll_qtyandCart.setVisibility(View.GONE);
                    holder.txtPromoprice.setVisibility(View.GONE);
                    holder.tvWishlist.setVisibility(View.GONE);
                    holder.ll_option.setVisibility(View.VISIBLE);
                    holder.txt_option.setVisibility(View.VISIBLE);
                    holder.txtprice.setText(listHomrItem.get(position).getPrice());
                    holder.txt_option.setText(listHomrItem.get(position).getDescAll());
                }

                if (listHomrItem.get(position).getIsHomeQuickAddCart() != null && !listHomrItem.get(position).getIsHomeQuickAddCart().isEmpty()) {
                    if (!listHomrItem.get(position).getIsHomeQuickAddCart().trim().equalsIgnoreCase("true")) {
                        holder.ll_option.setVisibility(View.GONE);
                        holder.ll_qtyandCart.setVisibility(View.GONE);
                        holder.tvWishlist.setVisibility(View.GONE);
                    }
                }

            }else {
                holder.ll_option.setVisibility(View.GONE);
                holder.txt_option.setVisibility(View.GONE);
//                holder.txtprice.setText("$" + listHomrItem.get(position).getPrice());
                holder.txtprice.setText(listHomrItem.get(position).getPrice());
////                END
                if (listHomrItem.get(position).getPromoPrice() != null && !listHomrItem.get(position).getPromoPrice().toString().isEmpty() && !listHomrItem.get(position).getPromoPrice().toString().equalsIgnoreCase("0.00")) {
                    holder.txtPromoprice.setVisibility(View.VISIBLE);
                    holder.txtPromoprice.setText("$" + listHomrItem.get(position).getPromoPrice());
                    holder.txtprice.setPaintFlags(holder.txtPromoprice.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
                    holder.txtprice.setTextColor(Color.GRAY);
                } else {
                    holder.txtPromoprice.setVisibility(View.GONE);
                    holder.txtprice.setTextColor(Color.BLACK);
                    holder.txtprice.setPaintFlags(holder.txtPromoprice.getPaintFlags() & (~Paint.STRIKE_THRU_TEXT_FLAG));
                    holder.txtprice.setGravity(Gravity.CENTER);
                }

//            Edited by Varun for homepage quatity

                Log.e("qty", "qty:" + listHomrItem.get(position).getQtyOnHand());
                if (listHomrItem.get(position).getIsHomeQuickAddCart() != null && !listHomrItem.get(position).getIsHomeQuickAddCart().isEmpty()) {

                    if (listHomrItem.get(position).getIsHomeQuickAddCart().equalsIgnoreCase("true")) {

                        if (listHomrItem.get(position).getQtyOnHand() != null && !listHomrItem.get(position).getQtyOnHand().toString().isEmpty()) {

                            if (listHomrItem.get(position).getQtyOnHand().equals("OutOfStock")) {
                                holder.ll_qtyandCart.setVisibility(View.GONE);
                                holder.txtitemOutOfStock.setVisibility(View.VISIBLE);

                            } else {
                                holder.ll_qtyandCart.setVisibility(View.VISIBLE);
                                holder.rl_wishlist.setVisibility(View.VISIBLE);
                                holder.txtitemOutOfStock.setVisibility(View.GONE);
                            }
                        }
                    } else {
                        holder.rl_wishlist.setVisibility(View.GONE);
                        holder.ll_qtyandCart.setVisibility(View.GONE);
                        holder.ll_option.setVisibility(View.GONE);
                    }
                }

                Log.e("type", "type:" + listHomrItem.get(position).getType());
                if (listHomrItem.get(position).getType() != null && !listHomrItem.get(position).getType().isEmpty()) {
//                   holder.ll_qtyandCart.setVisibility(View.GONE);
//                   holder.txtitemOutOfStock.setVisibility(View.GONE);
                    holder.rl_wishlist.setVisibility(View.GONE);
                    holder.ll_qtyandCart.setVisibility(View.GONE);

                }
            }

//              END



//            if (listHomrItem.get(position).getDiscountName() != null && !listHomrItem.get(position).getDiscountName().isEmpty()) {
//                holder.tvDiscountName.setVisibility(View.VISIBLE);
//                holder.tvDiscountName.setText(listHomrItem.get(position).getDiscountName());
//            } else {
//                holder.tvDiscountName.setVisibility(View.INVISIBLE);
//            }

            if (listHomrItem.get(position).getGrpcomment() != null && !listHomrItem.get(position).getGrpcomment().isEmpty()) {
                holder.tvDiscountName.setVisibility(View.VISIBLE);
                holder.tvDiscountName.setText(listHomrItem.get(position).getGrpcomment());
                holder.tvDiscountName.setPaintFlags(holder.tvDiscountName.getPaintFlags()| Paint.UNDERLINE_TEXT_FLAG);
            } else {

                if (listHomrItem.get(position).getDiscountName() != null && !listHomrItem.get(position).getDiscountName().isEmpty()) {
                    holder.tvDiscountName.setVisibility(View.VISIBLE);
                    holder.tvDiscountName.setText(listHomrItem.get(position).getDiscountName());
                    holder.tvDiscountName.setPaintFlags(holder.tvDiscountName.getPaintFlags()| Paint.UNDERLINE_TEXT_FLAG);
                }else{
                    holder.tvDiscountName.setVisibility(View.INVISIBLE);
                    holder.tvDiscountName.setText("buy 12 bottles, get 10% off ");
                }
            }

            holder.tvDiscountName.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    if(listHomrItem.get(position).getGrpMemo()!= null && !listHomrItem.get(position).getGrpMemo().equals("null") && !listHomrItem.get(position).getGrpMemo().isEmpty()) {

                        Utils.showDiscountgroupDialog(context, listHomrItem.get(position).getDesc1(), listHomrItem.get(position).getGrpMemo(), "", null);
                    }
                    else{
                        Utils.showDiscountgroupDialog(context, listHomrItem.get(position).getDesc1(), "No additional details have been entered by the business", "", null);
                    }
                }
            });
        }


        if(Constant.twentyOneYear.getInvRatings()){
            holder.ratingBar.setVisibility(View.VISIBLE);

            String inventoryRating = listHomrItem.get(position).getInventoryRating();

            if (inventoryRating != null && !inventoryRating.isEmpty()) {
                try {
                    int rating = Integer.parseInt(inventoryRating);
                    holder.ratingBar.setNumStars(rating);
                    holder.ratingBar.setRating((float) rating);
                } catch (NumberFormatException e) {
                    // Handle the case where the string is not a valid integer
                    e.printStackTrace(); // Log the exception or handle it according to your needs
                }
            } else {
                // Handle the case where the string is null or empty
                holder.ratingBar.setVisibility(View.GONE);
            }
        } else {
            holder.ratingBar.setVisibility(View.GONE);
        }


        /*if (Double.parseDouble(listHomrItem.get(position).getPromoPrice()) > 0) {
            String price = "$" + listHomrItem.get(position).getPrice() *//*+ " ea."*//*;
            holder.tvPrice.setText("$" + listHomrItem.get(position).getPromoPrice());
            holder.tvPromotionPrice.setVisibility(View.VISIBLE);
            holder.tvPromotionPrice.setText(Utils.strikeText(price));
        } else {
            holder.tvPromotionPrice.setVisibility(View.GONE);
            holder.tvPrice.setText("$" + listHomrItem.get(position).getPrice());
        }
        if (!listHomrItem.get(position).getDiscountName().isEmpty()) {
            holder.tvSpecialOffers.setVisibility(View.VISIBLE);
            holder.tvSpecialOffers.setText(listHomrItem.get(position).getDiscountName());
        }
        if (listHomrItem.get(position).getQtyOnHand().equals("0")) {
            holder.*//*tvMoveToCart*//*btnMoveToCard.setText("OUT OF STOCK");
            holder.*//*tvMoveToCart*//*btnMoveToCard.setTextColor(Color.GRAY);
        }*/
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
                .diskCacheStrategy(DiskCacheStrategy.ALL)
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

//    private Drawable placeholder;

//    public void loadImage(String url, ImageView imageView) {
//
//        if (Constant.SCREEN_LAYOUT == 1) {
//            placeholder = ContextCompat.getDrawable(MainActivity.getInstance(), R.drawable.progress_bar);
//        } else if (Constant.SCREEN_LAYOUT == 2) {
//            placeholder = ContextCompat.getDrawable(MainActivityDup.getInstance(), R.drawable.progress_bar);
//        }
//        /*Glide.with(imageView.getContext())
//                .load(url)
//                .placeholder(placeholder)
//                .diskCacheStrategy(DiskCacheStrategy.NONE)
//                .skipMemoryCache(true)
//                .fitCenter()
//                .into(new GlideDrawableImageViewTarget(imageView) {
//                    @Override
//                    public void onResourceReady(GlideDrawable drawable, GlideAnimation anim) {
//                        super.onResourceReady(drawable, anim);
//                        //getProgressBarIndeterminate() = drawable;
//                    }
//                });*/
//    }

    @Override
    public int getItemCount() {
        return listHomrItem.size();/* return testModels.size();*/
    }


    public class HomeListHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        ImageView img_item,ivAddtoCart,img_minus,img_plus;
        TextView txtitemtitle, txtprice, txtPromoprice,tvDiscountName,txtitemOutOfStock,tv_item_quantity , tvWishlist;
        CardView MainCardView;
        RatingBar ratingBar;
        LinearLayout ll_qtyandCart;
        public int count = 1;
        FrameLayout fl_sale_left, fl_sale_right,_fl_sale_hang;
        RelativeLayout rl_wishlist;
        LinearLayout ll_home_price,ll_option;
        TextView txt_option;

        public HomeListHolder(View itemView) {
            super(itemView);


            ll_home_price = itemView.findViewById(R.id.ll_home_price);
            txt_option = itemView.findViewById(R.id.txt_option);
            ll_option = itemView.findViewById(R.id.ll_option);

//            Edited by Varun
            fl_sale_left= itemView.findViewById(R.id.fl_sale_left);
            fl_sale_right= itemView.findViewById(R.id.fl_sale_right);
            _fl_sale_hang = itemView.findViewById(R.id.fl_sale_hang);
            rl_wishlist = itemView.findViewById(R.id.rl_wishlist);
            tv_item_quantity = itemView.findViewById(R.id.tv_item_quantity);
            tv_item_quantity.setText("1");
            txtitemOutOfStock = itemView.findViewById(R.id.txtitemOutOfStock);
            txtitemOutOfStock.setVisibility(View.GONE);
            ll_qtyandCart = itemView.findViewById(R.id.ll_qtyandCart);
//            ll_qtyandCart.setVisibility(View.GONE);

            ivAddtoCart = itemView.findViewById(R.id.ivAddtoCart);
            tvWishlist = itemView.findViewById(R.id.tvWishlist);
            img_minus = itemView.findViewById(R.id.img_minus);
            img_plus = itemView.findViewById(R.id.img_plus);
            img_minus.setOnClickListener(this);
            tvWishlist.setOnClickListener(this);
            ivAddtoCart.setOnClickListener(this);
            img_plus.setOnClickListener(this);

            Drawable roundDrawable = context.getResources().getDrawable(R.drawable.rounded_corner_all);
            roundDrawable.setColorFilter(Color.parseColor(Constant.themeModel.ThemeColor), PorterDuff.Mode.SRC_ATOP);
            ivAddtoCart.setBackground(roundDrawable);
            txt_option.setTextColor(Color.parseColor(Constant.themeModel.ThemeColor));


//            END

            img_item = itemView.findViewById(R.id.img_item);
            tvDiscountName = (TextView)itemView.findViewById(R.id.tvDiscountName);
            txtitemtitle = itemView.findViewById(R.id.txtitemtitle);
            txtprice = itemView.findViewById(R.id.txtprice);
            txtPromoprice = itemView.findViewById(R.id.txtPromoprice);
            MainCardView = itemView.findViewById(R.id.MainCardView);
            ratingBar = itemView.findViewById(R.id.ratingBar);
            LayerDrawable stars = (LayerDrawable) ratingBar.getProgressDrawable();
            stars.getDrawable(2).setColorFilter(Color.parseColor("#FFC120"), PorterDuff.Mode.SRC_ATOP);
            //ratingBar.setStepSize();

//            if(Constant.twentyOneYear.getInvRatings()){
//                ratingBar.setVisibility(View.VISIBLE);
//            }
            MainCardView.setOnClickListener(new View.OnClickListener() {
                @SuppressLint("SuspiciousIndentation")
                @Override
                public void onClick(View view) {
                    HomeItemModel model = listHomrItem.get(getAdapterPosition());


//                    Edited by Janvi 27th sep *********
                    String customerId = "0";
                    if (UserModel.Cust_mst_ID != null)
                        customerId = UserModel.Cust_mst_ID;
                    else
                        customerId = "0";

                    if (Constant.SCREEN_LAYOUT == 1) {
                        MainActivity.getInstance().showHomePage();

                        //Edited by janvi 16th jan **************

                        if (Constant.isNativePage) {
                            if(model != null && model.getItemMstId() != null)
//                            MainActivity.getInstance().loadItemDescriptionFragment(model.getItemMstId(), "");
                            MainActivity.getInstance().loadItemDescriptionFragment(model.getItemMstId(), "fragment");

                        } else {
                            MainActivity.getInstance().LoadWebVew(Constant.URL + "inventory/AndroidSearchItemDescription"
                                    + "?CustomerId=" + customerId
                                    + "&Storeno=" + Constant.STOREID
                                    + "&sessionid=" + /*"0"*/DeviceInfo.getDeviceId(MainActivity.getInstance()) + "0011"
                                    + "&ItemId=" /*+ "0" */ + model.getItemMstId()
                                    + "&Item=" + model.getDesc1());
                        }
//
                        //end ***********

                        Constant.Detail_webview_Url = true;

                    } else if (Constant.SCREEN_LAYOUT == 2) {
                        MainActivityDup.getInstance().showHomePage();

                        if (Constant.isNativePage) {
                            if(model != null && model.getItemMstId() != null)
                                MainActivityDup.getInstance().loadItemDescriptionFragment(model.getItemMstId(), "fragment");
                        } else {
                            MainActivityDup.getInstance().LoadWebVew(Constant.URL + "inventory/AndroidSearchItemDescription"
                                    + "?CustomerId=" + customerId
                                    + "&Storeno=" + Constant.STOREID
                                    + "&sessionid=" + /*"0"*/DeviceInfo.getDeviceId(MainActivityDup.getInstance()) + "0011"
                                    + "&ItemId=" /*+ "0" */ + model.getItemMstId()
                                    + "&Item=" + model.getDesc1());

                            Constant.Detail_webview_Url = true;

                        }
                    }
                }
            });
        }

        @Override
        public void onClick(View view) {

            resquantity = Integer.parseInt(tv_item_quantity.getText().toString());

            if (view.getId() == img_plus.getId()) {

                if (count < 999) {
                    count++;
                }
                tv_item_quantity.setText("" + count);
                resquantity = Integer.parseInt(tv_item_quantity.getText().toString());

//                if (myCardAdapterEvent != null) {
//                    HomeItemModel homeItemModel = listHomrItem.get(getAdapterPosition());
//                    myCardAdapterEvent.onCardItemPlus(getAdapterPosition(), quantity, homeItemModel);
//
//                }
            }

            if (view.getId() == img_minus.getId()) {

                if (count > 1) {
                    count--;
                }

                tv_item_quantity.setText("" + count);
                resquantity = Integer.parseInt(tv_item_quantity.getText().toString());

//                if (myCardAdapterEvent != null){
//                    HomeItemModel homeItemModel = listHomrItem.get(getAdapterPosition());
//                    myCardAdapterEvent.onCardItemMinus(getAdapterPosition(), quantity, homeItemModel);
//                }

            }

            if(view.getId() == ivAddtoCart.getId()){

                HomeItemModel homeItemModel = listHomrItem.get(getAdapterPosition());
                Constant.invType = homeItemModel.getInvType().toString();
                HomepageFragment.getInstance().isFromadpter_whenclickedonaddtocart = true;
                HomepageFragment.getInstance().addTocartData(homeItemModel,true,resquantity);
            }

            if (view.getId() == tvWishlist.getId()) {

                HomeItemModel homeItemModel = listHomrItem.get(getAdapterPosition());
                Constant.invType = homeItemModel.getInvType().toString();
                if (homeItemModel != null && !homeItemModel.getItemMstId().isEmpty()) {

                    String sku = null;
                    try {
                        sku = URLEncoder.encode(homeItemModel.getItemMstId().trim(), "utf-8");
                    } catch (UnsupportedEncodingException e) {
                        e.printStackTrace();
                    }


                    if(Constant.SCREEN_LAYOUT == 1){
                        if (UserModel.Cust_mst_ID == null || UserModel.Cust_mst_ID.isEmpty()) {
//                            Constant.isclickedwishlistFromViewall = true;
                            Login.StartLoginDialog("wishlist", context);
                        }else {
//                             Edited by Varun for guest login
                            if (Constant.ISguest){
                                DialogUtils.showDialog("Option not valid for guest account");
                            }else {
                                MainActivity.getInstance().callAddToWishlistWS(sku);
                            }
//                            END
                        }
                    }else{

                        if (UserModel.Cust_mst_ID == null || UserModel.Cust_mst_ID.isEmpty()) {
//                            Constant.isclickedwishlistFromViewall = true;

                            Login.StartLoginDialog("wishlist", context);
                        }else {
                            //                             Edited by Varun for guest login
                            if (Constant.ISguest){
                                DialogUtils.showDialog("Option not valid for guest account");
                            }else {
                                MainActivityDup.getInstance().callAddToWishlistWS(sku);
                            }
//                            END
                        }
                    }

                }
                //Toast.makeText(context, "Move to wishlist : Under Process", Toast.LENGTH_SHORT).show();
            }


        }
    }
}
