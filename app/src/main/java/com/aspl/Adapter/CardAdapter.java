package com.aspl.Adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.aspl.Utils.Constant;
import com.aspl.Utils.DialogUtils;
import com.aspl.Utils.Utils;
import com.aspl.fragment.CardFragment;
import com.aspl.fragment.Login;
import com.aspl.mbs.MainActivity;
import com.aspl.mbs.MainActivityDup;
import com.aspl.mbs.R;
import com.aspl.mbsmodel.ShoppingCardModel;
import com.aspl.mbsmodel.UserModel;
import com.aspl.mbsmodel.WishList;
import com.aspl.task.TaskDeleteWishList;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.GlideDrawableImageViewTarget;


import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.text.DecimalFormat;
import java.util.List;

/**
 * Created by mic on 12/1/2017.
 */

public class CardAdapter extends RecyclerView.Adapter<CardAdapter.MyCardViewHolder> implements TaskDeleteWishList.TaskDeleteCartItemEvent {

    private int quantity;

    Context context;
    private List<ShoppingCardModel> liShoppingCat;
    private String imgUrl = Constant.IMG_BASE + Constant.IMG_BASE_URL + Constant.STOREID + "/";
    private String imgNoImageUrl = Constant.IMG_BASE + Constant.IMG_NO_IMAGE + "/";

    private CardAdapterEvent myCardAdapterEvent;
    DecimalFormat df = new DecimalFormat("####0.00");
    private float _lPoints = 0, _total = 0;

    public interface CardAdapterEvent {
        void onCardItemRemoved(int position);

        void onCardItemPlus(int position, int quantity);

        void onCardItemMinus(int position, int quantity);

    }

    public CardAdapter() {

    }

    public CardAdapter(Context context, List<ShoppingCardModel> liShoppingCat) {
        this.context = context;
        this.liShoppingCat = liShoppingCat;
    }

    public CardAdapter(Context context, CardAdapterEvent myCardAdapterEvent/*, List<CardModel> testModel*/, List<ShoppingCardModel> liShoppingCat) {
        this.context = context;
        this.myCardAdapterEvent = myCardAdapterEvent;
        this.liShoppingCat = liShoppingCat;//this.cardModels = testModel;
    }

    @Override
    public MyCardViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_rv_shoping_card, parent, false);
        return new MyCardViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyCardViewHolder holder, @SuppressLint("RecyclerView") int i) {

//       **************************** Edited by Varun on 16 Aug 2022 *******************************************

        if (liShoppingCat.get(i).getInvLargeImageFullPath()!=null || !liShoppingCat.get(i).getInvLargeImageFullPath().isEmpty()) {
            Glide.with(context).load(liShoppingCat.get(i).getInvLargeImageFullPath())
                    .placeholder(R.drawable.noimage)
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .skipMemoryCache(true).into(holder.imgProductImage);
        }

//        ****************************** END ******************************

//        if (!liShoppingCat.get(i).getInvLargeImage().isEmpty()) {
//            String ul = "";
//            if (liShoppingCat.get(i).getInvLargeImage().contains("noimage")) {
//                /*Glide.with(context).load(imgNoImageUrl + liShoppingCat.get(i).getInvLargeImage()).*//*placeholder(R.drawable.noimage).*//*placeholder(getProgressBarIndeterminate())
//                        .diskCacheStrategy(DiskCacheStrategy.NONE)
//                        .skipMemoryCache(true).into(holder.imgProductImage);*/
//                ul = imgNoImageUrl + liShoppingCat.get(i).getInvLargeImage();
//                //loadImage(,holder.imgProductImage);
//            } else {
//                /*Glide.with(context).load(imgUrl + liShoppingCat.get(i).getInvLargeImage())*//*.placeholder(R.drawable.progress_bar)*//*.placeholder(getProgressBarIndeterminate())
//                        .diskCacheStrategy(DiskCacheStrategy.NONE)
//                        .skipMemoryCache(true).into(holder.imgProductImage);*/
//                ul = imgUrl + liShoppingCat.get(i).getInvLargeImage();
//
//            }
//            if (ul.contains(" ")) {
//                ul = ul.replace(" ", "%20");
//            }
//            if (ul.contains("’")) {
//                ul = ul.replace("’", "%E2%80%99");
//            }
//            loadImage(ul, holder.imgProductImage);
//        }

//        holder.tvName.setText(liShoppingCat.get(i).getDesc1());

        if (liShoppingCat.get(i).getDesc1() != null && !liShoppingCat.get(i).getDesc1().isEmpty()) {

            if(liShoppingCat.get(i).getDesc1().contains("()")){
                String desc1 = liShoppingCat.get(i).getDesc1().replace("()","");
                holder.tvName.setText(Utils.capitalizeEachWord(desc1.trim()));
            }else{
                holder.tvName.setText(Utils.capitalizeEachWord(liShoppingCat.get(i).getDesc1().trim()));
            }
        }

        if (liShoppingCat.get(i).getGrpcomment() != null && !liShoppingCat.get(i).getGrpcomment().isEmpty()) {
            holder.tvDiscount.setVisibility(View.VISIBLE);
            holder.tvDiscount.setText(liShoppingCat.get(i).getGrpcomment());
            holder.tvDiscount.setPaintFlags(holder.tvDiscount.getPaintFlags()| Paint.UNDERLINE_TEXT_FLAG);
        } else {

            if (liShoppingCat.get(i).getDiscountName() != null && !liShoppingCat.get(i).getDiscountName().isEmpty()) {
                holder.tvDiscount.setVisibility(View.VISIBLE);
                holder.tvDiscount.setText(liShoppingCat.get(i).getDiscountName());
                holder.tvDiscount.setPaintFlags(holder.tvDiscount.getPaintFlags()| Paint.UNDERLINE_TEXT_FLAG);
            }else{
                holder.tvDiscount.setVisibility(View.GONE);
            }

        }

        holder.tvDiscount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(liShoppingCat.get(i).getGrpMemo()!= null && !liShoppingCat.get(i).getGrpMemo().equals("null") && !liShoppingCat.get(i).getGrpMemo().isEmpty()){
                    Utils.showDiscountgroupDialog(context,liShoppingCat.get(i).getDesc1(),liShoppingCat.get(i).getGrpMemo(),"", null);
                }
                else{
                    Utils.showDiscountgroupDialog(context,liShoppingCat.get(i).getDesc1(),"No additional details have been entered by the business","", null);
                }
            }
        });

        String price = "$ " + liShoppingCat.get(i).getCartPrice() + " ea.";
        if (Double.parseDouble(liShoppingCat.get(i).getPromoPrice()) > 0) {
//            String actualPrice = "$ " + liShoppingCat.get(i).getPrice() + " ea.";
            String actualPrice = liShoppingCat.get(i).getPrice() + " ea.";
            holder.tvPromoPrice.setText(Utils.strikeText(actualPrice));
            holder.tvPrice.setText(" " + " $ " + liShoppingCat.get(i).getPromoPrice() + " ea.");
            holder.tvQuantity.setText(/*"Qty : " +*/liShoppingCat.get(i).getQty());
            holder.tvQuantityChange.setText(liShoppingCat.get(i).getQty());
            quantity = Integer.parseInt(holder.tvQuantityChange.getText().toString());
            holder.tvTotal.setText(String.valueOf(df.format(Double.parseDouble(liShoppingCat.get(i).getPromoPrice()) * quantity)));
        } else {
            holder.tvPrice.setText(price);
            holder.tvQuantity.setText(/*"Qty : " +*/liShoppingCat.get(i).getQty());
            holder.tvQuantityChange.setText(liShoppingCat.get(i).getQty());
            quantity = Integer.parseInt(holder.tvQuantityChange.getText().toString());
            holder.tvTotal.setText(String.valueOf(df.format(Double.parseDouble(liShoppingCat.get(i).getCartPrice()) * quantity)));
        }
        /*holder.tvQuantity.setText(*//*"Qty : " +*//*liShoppingCat.get(i).getQty());
        holder.tvQuantityChange.setText(liShoppingCat.get(i).getQty());
        quantity = Integer.parseInt(holder.tvQuantityChange.getText().toString());
        holder.tvTotal.setText(String.valueOf(df.format(Double.parseDouble(liShoppingCat.get(i).getPrice()) * quantity)));*/
        _total = Float.parseFloat(liShoppingCat.get(i).getCartPrice()) * Float.parseFloat(liShoppingCat.get(i).getQty());
//        if (liShoppingCat.get(i).getLoyaltyOn().trim().equals("onItem")) {
//            //tvLoyaltyReward.setText("Loyalty Reward is on");
//            _lPoints = Float.valueOf(liShoppingCat.get(i).getLoyaltyPointsOnItem()) * _total;
//            if (((Float.valueOf(liShoppingCat.get(i).getLoyaltyPointsOnItem()) * _total) % 1) > 0.5) {
//                _lPoints = Float.valueOf(liShoppingCat.get(i).getLoyaltyPointsOnItem()) * _total;
//            }
//        }
//        Edited By Varun For Crash issue when get LoyaltyPointsOnItem is null or Empty
        if (liShoppingCat.get(i).getLoyaltyOn().trim().equals("onItem")) {
            String loyaltyPointsStr = liShoppingCat.get(i).getLoyaltyPointsOnItem();

            // Check if loyaltyPointsStr is not null and not empty
            if (loyaltyPointsStr != null && !loyaltyPointsStr.trim().isEmpty()) {
                float loyaltyPoints = Float.valueOf(loyaltyPointsStr);

                _lPoints = loyaltyPoints + _total;

                // Check for the condition involving the fractional part
                if ((loyaltyPoints * _total) % 1 > 0.5) {
                    _lPoints = loyaltyPoints * _total;
                }
            }
        }
//        END

        if (liShoppingCat.get(i).getLoyaltyType().equals("Internal")
                && liShoppingCat.get(i).getLoyaltyOn().equals("onItem")
                && liShoppingCat.get(i).getIsLoyaltyRewardEnable().equals("Y")
                && !liShoppingCat.get(i).getLoyaltyCardNo().equals("")) {

            holder.tvTitleItemReward.setVisibility(View.GONE);
            holder.tvItemReward.setText(String.valueOf("Reward : " + Math.round(_lPoints) + " Pts"));
            holder.tvItemReward.setVisibility(View.VISIBLE);

            if (holder.tvItemReward.getVisibility() != View.VISIBLE) {
                RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                params.addRule(RelativeLayout.BELOW, R.id.ll_quantity_count);
                params.addRule(RelativeLayout.RIGHT_OF, R.id.tv_item_totle_rv_item);
                params.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
                params.rightMargin = 5;
                params.topMargin = 2;
                holder.tvTax.setLayoutParams(params);
            }
        } else {
            RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            params.addRule(RelativeLayout.BELOW, R.id.ll_quantity_count);
            params.addRule(RelativeLayout.RIGHT_OF, R.id.tv_item_totle_rv_item);
            params.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
            params.rightMargin = 5;
            params.topMargin = 3;
            holder.tvTax.setLayoutParams(params);
        }
        String legend = "";
        if (Float.valueOf(liShoppingCat.get(i).getSalesTax1().trim()) > 0) {
            if (!liShoppingCat.get(i).getTax11().isEmpty())
                legend = legend + liShoppingCat.get(i).getTax11() + "  ";
        }

        if (Float.valueOf(liShoppingCat.get(i).getWineTax2().trim()) > 0) {
            if (!liShoppingCat.get(i).getTax22().isEmpty())
                legend = legend + liShoppingCat.get(i).getTax22() + "  ";
        }

        if (Float.valueOf(liShoppingCat.get(i).getMiscTax3().trim()) > 0) {
            if (!liShoppingCat.get(i).getTax33().isEmpty())
                legend = legend + liShoppingCat.get(i).getTax33() + "  ";
        }
        if (Float.valueOf(liShoppingCat.get(i).getFlat().trim()) > 0) {
            if (!liShoppingCat.get(i).getTax44().isEmpty())
                legend = legend + liShoppingCat.get(i).getTax44() + "  ";
        }
        if (Float.valueOf(liShoppingCat.get(i).getBottledeposit().trim()) > 0) {
            if (!liShoppingCat.get(i).getTax55().isEmpty())
                legend = legend + liShoppingCat.get(i).getTax55();
        }

        if (!liShoppingCat.get(i).getTax66().isEmpty())
            legend = legend + liShoppingCat.get(i).getTax66();

        holder.tvTax.setText(legend);
        //holder.tvTotal.setText(/*"Total : " + "$" + */String.valueOf(Integer.parseInt(cardModels.get(i).getPrice()) * quantity));
    }

    /*public static SpannableString strikeText(String text) {
        if (text == null) text = "";
        SpannableString str = new SpannableString(text);
        str.setSpan(new StrikethroughSpan(), 0, str.length(), Spanned.SPAN_PARAGRAPH);
        return str;
    }

    public static SpannableString underlineText(String text) {
        if (text == null) text = "";
        SpannableString str = new SpannableString(text);
        str.setSpan(new UnderlineSpan(), 0, str.length(), Spanned.SPAN_PARAGRAPH);
        return str;
    }*/

    @Override
    public int getItemCount() {
        return liShoppingCat.size();
    }

    public class MyCardViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        ImageView imgProductImage, imgCancel, imgPlus, imgMinus;
        TextView tvName, tvPrice, tvPromoPrice, tvPriceTitle, tvDiscount, tvQuantity, tvTotal, tvQuantityChange, tvTax;
        //TextView tvRemove, tvMoveToWishList;
        Button tvRemove, tvMoveToWishList;
        TextView tvTitleItemReward, tvItemReward;

        public MyCardViewHolder(View itemView) {
            super(itemView);
            imgProductImage = (ImageView) itemView.findViewById(R.id.img_item_image_rv_card_fragment);
            imgCancel = (ImageView) itemView.findViewById(R.id.img_cancel_rv_card_fragment);
            imgCancel.setOnClickListener(this);
            tvName = (TextView) itemView.findViewById(R.id.tv_item_name_rv_item);
            tvDiscount = (TextView) itemView.findViewById(R.id.tv_discount_name_rv_item);
            tvPriceTitle = (TextView) itemView.findViewById(R.id.tv_item_label_price_rv_item);
            tvPriceTitle.setTextColor(Color.parseColor(Constant.themeModel.ThemeColor));
            tvPrice = (TextView) itemView.findViewById(R.id.tv_item_price_rv_item);
            tvPrice.setTextColor(Color.parseColor(Constant.themeModel.ThemeColor));
            tvPromoPrice = (TextView) itemView.findViewById(R.id.tv_item_promo_price_rv_item);
            tvPromoPrice.setTextColor(Color.parseColor(Constant.themeModel.ThemeColor));
            tvQuantity = (TextView) itemView.findViewById(R.id.tv_item_quantity_rv_item);
            tvQuantity.setOnClickListener(this);
            tvTotal = (TextView) itemView.findViewById(R.id.tv_item_totle_rv_item);
            tvTitleItemReward = (TextView) itemView.findViewById(R.id.tv_item_label_reward_rv_item);
            tvItemReward = (TextView) itemView.findViewById(R.id.tv_item_reward_rv_item);
            tvQuantityChange = (TextView) itemView.findViewById(R.id.tv_item_quantity_change_rv_item);
            imgPlus = (ImageView) itemView.findViewById(R.id.img_plus_rv_card_fragment);
            imgPlus.setOnClickListener(this);
            imgMinus = (ImageView) itemView.findViewById(R.id.img_minus_rv_card_fragment);
            imgMinus.setOnClickListener(this);
            tvTax = (TextView) itemView.findViewById(R.id.tv_tax_total_rv_item);

            //tvRemove = (TextView) itemView.findViewById(R.id.tv_remove_rv_shopping_card);
            //tvMoveToWishList = (TextView) itemView.findViewById(R.id.tv_move_to_wish_list_rv_shopping_card);
            //tvRemove.setTextColor(Color.parseColor(Constant.themeModel.ThemeColor));
            //tvMoveToWishList.setTextColor(Color.parseColor(Constant.themeModel.ThemeColor));

            tvRemove = (Button) itemView.findViewById(R.id.tv_remove_rv_shopping_card);
            GradientDrawable bgShapes = (GradientDrawable) tvRemove.getBackground();
            bgShapes.setColor(Color.parseColor(Constant.themeModel.ThemeColor));
            tvMoveToWishList = (Button) itemView.findViewById(R.id.tv_move_to_wish_list_rv_shopping_card);
            tvRemove.setOnClickListener(this);
            tvMoveToWishList.setOnClickListener(this);
            //tvRemove = (Button) itemView.findViewById(R.id.tv_remove_rv_shopping_card);
            //tvRemove.setTextColor(Color.WHITE);
            //tvMoveToWishList = (Button) itemView.findViewById(R.id.tv_move_to_wish_list_rv_shopping_card);
            //GradientDrawable bgShapes = (GradientDrawable) tvMoveToWishList.getBackground();
            //bgShapes.setColor(Color.parseColor(Constant.themeModel.ThemeColor));
            //tvMoveToWishList.setBackgroundColor(Color.parseColor(Constant.themeModel.ThemeColor));
        }

        @Override
        public void onClick(View view) {
            try {


                quantity = Integer.parseInt(tvQuantityChange.getText().toString());
                if (view.getId() == imgCancel.getId()) {
                    if (myCardAdapterEvent != null) {
                        myCardAdapterEvent.onCardItemRemoved(getAdapterPosition());
                    }
                }

                if (view.getId() == imgPlus.getId()) {
                    quantity = Integer.parseInt(tvQuantityChange.getText().toString());
                    if (quantity >= 1)
                        quantity = quantity + 1;
                    //tvQuantityChange.setText(String.valueOf(quantity));
                    if (Double.parseDouble(liShoppingCat.get(getAdapterPosition()).getPromoPrice()) > 0)
                        tvTotal.setText(String.valueOf(df.format(Double.parseDouble(liShoppingCat.get(getAdapterPosition()).getPromoPrice()) * quantity)));
                    else
                        tvTotal.setText(String.valueOf(df.format(Double.parseDouble(liShoppingCat.get(getAdapterPosition()).getCartPrice()) * quantity)));


                    //tvTotal.setText(String.valueOf(df.format(Double.parseDouble(liShoppingCat.get(getAdapterPosition()).getPrice()) * quantity)));

                    liShoppingCat.get(getAdapterPosition()).setQty(String.valueOf(quantity));
//                Edited by Varun
                    if (Constant.Test_Ounces != 0) {
                        Constant.Test_Ounces = 0;
                    }
                    Constant.Test_Ounces = Integer.parseInt(String.valueOf(liShoppingCat.get(getAdapterPosition()).getOunces()));
//                END
                    if (myCardAdapterEvent != null)
                        myCardAdapterEvent.onCardItemPlus(getAdapterPosition(), quantity);

                }

                if (view.getId() == imgMinus.getId()) {
                    quantity = Integer.parseInt(tvQuantityChange.getText().toString());
                    if (quantity > 1) {
                        quantity = quantity - 1;
                        // tvQuantityChange.setText(String.valueOf(quantity));
                        if (Double.parseDouble(liShoppingCat.get(getAdapterPosition()).getPromoPrice()) > 0)
                            tvTotal.setText(String.valueOf(df.format(Double.parseDouble(liShoppingCat.get(getAdapterPosition()).getPromoPrice()) * quantity)));
                        else
                            tvTotal.setText(/*"Total : " + "$" +*/ String.valueOf(df.format(Double.parseDouble(liShoppingCat.get(getAdapterPosition()).getCartPrice()) * quantity)));

                        liShoppingCat.get(getAdapterPosition()).setQty(String.valueOf(quantity));
                        if (myCardAdapterEvent != null)
                            myCardAdapterEvent.onCardItemMinus(getAdapterPosition(), quantity);
                    }
                }

                if (view.getId() == tvRemove.getId()) {
                    if (myCardAdapterEvent != null) {
                        myCardAdapterEvent.onCardItemRemoved(getAdapterPosition());
                    }
                }
                if (view.getId() == tvMoveToWishList.getId()) {

                    if (UserModel.Cust_mst_ID == null || UserModel.Cust_mst_ID.isEmpty()) {
                        Login.StartLoginDialog("wishlist", context);
                    } else {
                        if (Constant.ISguest) {
                            DialogUtils.showDialog("Option not valid for guest account");
                        } else {
                            moveToCart(getAdapterPosition());
                        }
                    }
                    //Toast.makeText(context, "Move to wishlist : Under Process", Toast.LENGTH_SHORT).show();
                }
            }catch (Exception e){
                Toast.makeText(context, "Please try again later.", Toast.LENGTH_SHORT).show();
            }
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

    @SuppressLint("ResourceType")
    private Drawable getProgressBarIndeterminate() {
        final int[] attrs = {android.R.attr.indeterminateDrawable};
        final int attrs_indeterminateDrawable_index = 0;
        TypedArray a = null;
        if (Constant.SCREEN_LAYOUT == 1) {
            a = MainActivity.getInstance().obtainStyledAttributes(android.R.style.Widget_ProgressBar, attrs);
        } else if (Constant.SCREEN_LAYOUT == 2) {
            a = MainActivityDup.getInstance().obtainStyledAttributes(android.R.style.Widget_ProgressBar, attrs);
        }

        try {
            return a.getDrawable(attrs_indeterminateDrawable_index);
        } finally {
            a.recycle();
        }
    }

    int pos = 0;

    public void moveToCart(int pos) {
        this.pos = pos;
        String itemId = liShoppingCat.get(pos).getItemMstId();
        //InsertCartData/0/Wishlist/customerID/0/1/707/add
        //InsertCartData/0/Wishlist/customerID/$(this).attr("id")/1/storeno/0/add/
        String id = String.valueOf(liShoppingCat.get(pos).getCartID());
        /*String url = Constant.WS_BASE_URL + Constant.DELETE_CART + "0" *//*id*//* + "/" + "Wishlist" + "/" + UserModel.Cust_mst_ID +
                "/" + liShoppingCat.get(pos).getItemMstId().trim() + "/" + "1" +
                "/" + Constant.STOREID + "/" + "0" + "/" + "add/I"*//*//*I*//*;*/


        //Edited by Janvi 17th Oct ********
        String cust_id;
        String url;

        String invType;
        if(liShoppingCat.get(pos).getInvType() != null){
            invType = liShoppingCat.get(pos).getInvType();
        }else{
            invType = "I";
        }


        String itemIdSku = null;
        if (!liShoppingCat.get(pos).getItemMstId().trim().isEmpty() && !liShoppingCat.get(pos).getItemMstId().trim().equals("")) {
            try {
                itemIdSku = URLEncoder.encode(liShoppingCat.get(pos).getItemMstId().trim(), "utf-8");
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }

        if(UserModel.Cust_mst_ID == null){
            cust_id = "0";

            url = Constant.WS_BASE_URL + Constant.DELETE_CART + id + "/" + "Wishlist" + "/" + cust_id +
                    "/" + itemIdSku + "/" + "1" +
                    "/" + Constant.STOREID + "/" + "0" + "/" + "movetowishlist" + "/" + invType;

        }else{
            url = Constant.WS_BASE_URL + Constant.DELETE_CART + id + "/" + "Wishlist" + "/" + UserModel.Cust_mst_ID +
                    "/" + itemIdSku + "/" + "1" +
                    "/" + Constant.STOREID + "/" + "0" + "/" + "movetowishlist" + "/" + invType;
        }

        //end *************

        TaskDeleteWishList deleteWishList = new TaskDeleteWishList(this, "MovetoWishList Cart");
//        deleteWishList.execute(url);
        deleteWishList.executeOnExecutor(TaskDeleteWishList.THREAD_POOL_EXECUTOR,url);
    }

    @Override
    public void onWishListResult(WishList wishList, String string) {
        if (myCardAdapterEvent != null) {
            if (wishList.getResult().equals("success")) {
//          Edited By Varun for pop-up of Added
                if (string!=null && !string.isEmpty()){
                    if (string.equalsIgnoreCase("MovetoWishList Cart")){
                        DialogUtils.showDialog("Added in WishList!");
                    }
                }

//                END
                if (Constant.SCREEN_LAYOUT == 1) {
                    MainActivity.onGetCartData("");
                } else if (Constant.SCREEN_LAYOUT == 2) {
                    MainActivityDup.onGetCartData();
                }
                //MainActivity.getInstance().onShoppingCardResult(liShoppingCat);
                liShoppingCat.remove(pos);

                if (liShoppingCat.size() == 0) {
                    CardFragment.onSetEmpty();
                } else {
                    CardFragment.getInstance().onCalculateTotal(liShoppingCat);
                    CardFragment.getInstance().recyclerView.removeViewAt(pos);
                    notifyItemRemoved(pos);
                    notifyItemRangeChanged(pos, liShoppingCat.size());
                    notifyDataSetChanged();
                }
            } else {
                DialogUtils.showDialog("Already in Wish List!");
            }
        }
    }
}
