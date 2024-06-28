package com.aspl.Adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;
import androidx.annotation.NonNull;
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
import android.widget.TextView;
import android.widget.Toast;

import com.aspl.Utils.Constant;
import com.aspl.Utils.DialogUtils;
import com.aspl.Utils.Utils;
import com.aspl.fragment.Login;
import com.aspl.fragment.ViewAllFragment;
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

public class ViewallAdapter extends RecyclerView.Adapter<ViewallAdapter.ViewallHolder>{


    private double promoprice =  0.00;
    List<HomeItemModel> listHomrItem;
    private int resquantity;
    String itemsku;
    private String imgUrl = Constant.IMG_BASE + Constant.IMG_BASE_URL + Constant.STOREID + "/";
    private String imgNoImageUrl = Constant.IMG_BASE + Constant.IMG_NO_IMAGE + "/";
    //http://192.168.172.211:888/WebStoreImages/NoImage/

    Context context;
    CardAdapterEvent myCardAdapterEvent;

    public ViewallAdapter(Context context, CardAdapterEvent myCardAdapterEvent, List<HomeItemModel> viewAllList) {
        this.context = context;
        this.myCardAdapterEvent = myCardAdapterEvent;
        this.listHomrItem = viewAllList;
    }

    public interface CardAdapterEvent {

        void onCardItemPlus(int position, int quantity, HomeItemModel homeItemModel);

        void onCardItemMinus(int position, int quantity, HomeItemModel homeItemModel);

    }

    @NonNull
    @Override
    public ViewallHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_itmlisting_grid, parent, false);
        return new ViewallAdapter.ViewallHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewallHolder holder, @SuppressLint("RecyclerView") int position) {
        if(listHomrItem != null && listHomrItem.size()>0) {

//            Constant.invType = listHomrItem.get(position).getInvType().toString();

            Log.e("", "onBindViewHolder:11 "+listHomrItem.get(position).getInvType().toString() );
//            ******************** Edited by Varun on 16 Aug 2022 *******************

            if (listHomrItem.get(position).getInvLargeImageFullPath()!=null || !listHomrItem.get(position).getInvLargeImageFullPath().isEmpty()) {
                Glide.with(context).load(listHomrItem.get(position).getInvLargeImageFullPath())
                        .placeholder(R.drawable.noimage)
                        .diskCacheStrategy(DiskCacheStrategy.NONE)
                        .skipMemoryCache(true).into(holder.img_item);
            }


//            ********************* END *************************


            //            Edited by Varun for sale indicator

            if (listHomrItem.get(position).getPromoPrice() != null && !listHomrItem.get(position).getPromoPrice().isEmpty()){

                if (!listHomrItem.get(position).getPromoPrice().toString().equalsIgnoreCase("0.00") && !listHomrItem.get(position).getPromoPrice().equals(promoprice)) {

                    if (listHomrItem.get(position).getItemonPromotionIndicator().equalsIgnoreCase("NI")) {
                        holder.fl_sale_right.setVisibility(View.GONE);
                        holder.fl_sale_left.setVisibility(View.GONE);
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

//            END

//            holder.txtitemtitle.setText(listHomrItem.get(position).getDesc1());

            if (listHomrItem.get(position).getDesc1() != null && !listHomrItem.get(position).getDesc1().isEmpty()) {

                if(listHomrItem.get(position).getDesc1().contains("()")){
                    String desc1 = listHomrItem.get(position).getDesc1().replace("()","");
                    holder.txtitemtitle.setText(Utils.capitalizeEachWord(desc1.trim()));
                }else{
                    holder.txtitemtitle.setText(Utils.capitalizeEachWord(listHomrItem.get(position).getDesc1().trim()));
                }
            }

//             Edited by Varun for multi pack when comes in view all then add to cart and wishlist option Gone
            if (listHomrItem.get(position).getInvType().equals("M") && listHomrItem.get(position).getPrice()!=null
                    && listHomrItem.get(position).getDescAll()!=null && !listHomrItem.get(position).getDescAll().equalsIgnoreCase("")){
                    holder.ll_qtyandCart.setVisibility(View.GONE);
                    holder.txtPromoprice.setVisibility(View.GONE);
                    holder.tvWishlist.setVisibility(View.GONE);
                    holder.ll_option.setVisibility(View.VISIBLE);
                    holder.txt_option.setVisibility(View.VISIBLE);
                    holder.txtprice.setText( listHomrItem.get(position).getPrice());
                    holder.txt_option.setText(listHomrItem.get(position).getDescAll());
            } else {
//                holder.txtprice.setText("$" + listHomrItem.get(position).getPrice());
                holder.ll_option.setVisibility(View.GONE);
                holder.txt_option.setVisibility(View.GONE);
                holder.txtprice.setText( listHomrItem.get(position).getPrice());
//                END
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

                    Log.e("qty", "qty:" + listHomrItem.get(position).getQtyOnHand());
                    if (listHomrItem.get(position).getQtyOnHand() != null && !listHomrItem.get(position).getQtyOnHand().toString().isEmpty()) {

                        if (listHomrItem.get(position).getQtyOnHand().equals("OutOfStock")) {
                            holder.ll_qtyandCart.setVisibility(View.GONE);
                            holder.txtitemOutOfStock.setVisibility(View.VISIBLE);
                        } else {
                            holder.ll_qtyandCart.setVisibility(View.VISIBLE);
                            holder.txtitemOutOfStock.setVisibility(View.GONE);
                        }
                    }
                }
            }

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
                    holder.tvDiscountName.setPaintFlags(holder.tvDiscountName.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);

                } else {
                    holder.tvDiscountName.setVisibility(View.INVISIBLE);
                }
            }



            holder.tvDiscountName.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(listHomrItem.get(position).getGrpMemo()!= null && !listHomrItem.get(position).getGrpMemo().equals("null") && !listHomrItem.get(position).getGrpMemo().isEmpty()) {

                        Utils.showDiscountgroupDialog(context, listHomrItem.get(position).getDesc1(), listHomrItem.get(position).getGrpMemo(), "", null);
                    }
                }
            });


            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    if(listHomrItem.get(position).getItemMstId() != null &&
                            !listHomrItem.get(position).getItemMstId().isEmpty()){
                        itemsku = listHomrItem.get(position).getItemMstId();
                    }

                    if (Constant.SCREEN_LAYOUT == 1) {
                        MainActivity.getInstance().loadItemDescriptionFragment(itemsku,"ViewAllFragment");
                    } else if (Constant.SCREEN_LAYOUT == 2) {
                        MainActivityDup.getInstance().loadItemDescriptionFragment(itemsku,"ViewAllFragment");
                    }
                }
            });

        if (!listHomrItem.get(position).getInventoryRating().isEmpty() && Integer.parseInt(listHomrItem.get(position).getInventoryRating()) > 0) {
            holder.ratingBar.setVisibility(View.VISIBLE);
            holder.ratingBar.setNumStars(Integer.parseInt(listHomrItem.get(position).getInventoryRating()));
            holder.ratingBar.setRating(Float.parseFloat(listHomrItem.get(position).getInventoryRating()));
        } else {
            holder.ratingBar.setVisibility(View.GONE);
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


    @Override
    public int getItemCount() {
        return listHomrItem.size();
    }

    public class ViewallHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        ImageView img_item,ivAddtoCart,img_minus,img_plus;
        TextView txtitemtitle, txtprice, txtPromoprice,tvDiscountName,
                tvWishlist,txtitemOutOfStock;
        TextView tv_item_quantity;
        CardView MainCardView;
        RatingBar ratingBar;
        LinearLayout ll_qtyandCart;
        public int count = 1;

        FrameLayout fl_sale_left, fl_sale_right,_fl_sale_hang;
        LinearLayout ll_viewall_price ,ll_option;
        TextView txt_option;


        public ViewallHolder(@NonNull View itemView) {
            super(itemView);

//            Edited by Varun for sale indicator
            fl_sale_left= itemView.findViewById(R.id.fl_sale_left);
            fl_sale_right= itemView.findViewById(R.id.fl_sale_right);
            _fl_sale_hang = itemView.findViewById(R.id.fl_sale_hang);

            ll_viewall_price = itemView.findViewById(R.id.ll_viewall_price);
            txt_option = itemView.findViewById(R.id.txt_option);
            ll_option = itemView.findViewById(R.id.ll_option);
//            END

            img_item = itemView.findViewById(R.id.img_item);
            tvDiscountName = (TextView) itemView.findViewById(R.id.tvDiscountName);
            txtitemtitle = itemView.findViewById(R.id.txtitemtitle);
            txtprice = itemView.findViewById(R.id.txtprice);
            txtPromoprice = itemView.findViewById(R.id.txtPromoprice);
            tv_item_quantity = itemView.findViewById(R.id.tv_item_quantity);
            tv_item_quantity.setText("1");
            ivAddtoCart = itemView.findViewById(R.id.ivAddtoCart);
            tvWishlist = itemView.findViewById(R.id.tvWishlist);
            img_minus = itemView.findViewById(R.id.img_minus);
            img_plus = itemView.findViewById(R.id.img_plus);
            MainCardView = itemView.findViewById(R.id.MainCardView);
            txtitemOutOfStock = itemView.findViewById(R.id.txtitemOutOfStock);
            txtitemOutOfStock.setVisibility(View.GONE);
            ll_qtyandCart = itemView.findViewById(R.id.ll_qtyandCart);
            ll_qtyandCart.setVisibility(View.GONE);
            img_minus.setOnClickListener(this);
            tvWishlist.setOnClickListener(this);
            ivAddtoCart.setOnClickListener(this);
            img_plus.setOnClickListener(this);

//            final int sdk = android.os.Build.VERSION.SDK_INT;
//            if (sdk < android.os.Build.VERSION_CODES.JELLY_BEAN) {
//                MainCardView.setBackgroundDrawable(ContextCompat.getDrawable(context, R.drawable.dark_grey_border_thick));
//            } else {
//                MainCardView.setBackground(ContextCompat.getDrawable(context, R.drawable.dark_grey_border_thick));
//            }

            ratingBar = itemView.findViewById(R.id.ratingBar);
            LayerDrawable stars = (LayerDrawable) ratingBar.getProgressDrawable();
            stars.getDrawable(2).setColorFilter(Color.parseColor("#FFC120"), PorterDuff.Mode.SRC_ATOP);
            ivAddtoCart = itemView.findViewById(R.id.ivAddtoCart);
            tvWishlist = itemView.findViewById(R.id.tvWishlist);
//            DrawableCompat.setTint(tvWishlist.getD, Color.parseColor(String.valueOf(context.getResources().getColor(R.color.light_grey))));
//
            Drawable roundDrawable = context.getResources().getDrawable(R.drawable.rounded_corner_all);
            roundDrawable.setColorFilter(Color.parseColor(Constant.themeModel.ThemeColor), PorterDuff.Mode.SRC_ATOP);
            ivAddtoCart.setBackground(roundDrawable);
            txt_option.setTextColor(Color.parseColor(Constant.themeModel.ThemeColor));

        }


//            img_plus.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View view) {
//                    if (count > 1) {
//                        count--;
//                    }
//                    tv_item_quantity.setText("" + count);
//                    ViewAllFragment.getInstance().onUpdateQuantityTask(count,itemsku);
//                }
//            });
//
//            img_minus.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View view) {
//                    if (count > 1) {
//                        count--;
//                    }
//                    tv_item_quantity.setText("" + count);
//                    ViewAllFragment.getInstance().onUpdateQuantityTask(count,itemsku);
//                }
//            });

//            MainCardView.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View view) {
//
//
//                    if (Constant.SCREEN_LAYOUT == 1) {
//                        MainActivity.getInstance().loadItemDescriptionFragment(itemsku);
//                    } else if (Constant.SCREEN_LAYOUT == 2) {
//                        MainActivityDup.getInstance().loadItemDescriptionFragment(itemsku);
//                    }
//
//                }
//            })



        @Override
        public void onClick(View view) {

            try {

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

                if (view.getId() == ivAddtoCart.getId()) {

                    HomeItemModel homeItemModel = listHomrItem.get(getAdapterPosition());
//                Edited by Varun for INVtype to pass to the add to cart
                    Constant.invType = (String) homeItemModel.getInvType().toString();
//                END
                    ViewAllFragment.getInstance().isFromadpter_whenclickedonaddtocart = true;
                    ViewAllFragment.getInstance().addTocartData(homeItemModel, true, resquantity);
                }

                if (view.getId() == tvWishlist.getId()) {

                    HomeItemModel homeItemModel = listHomrItem.get(getAdapterPosition());

                    Constant.invType = (String) homeItemModel.getInvType().toString();

                    if (homeItemModel != null && !homeItemModel.getItemMstId().isEmpty()) {

                        String sku = null;
                        try {
                            sku = URLEncoder.encode(homeItemModel.getItemMstId().trim(), "utf-8");
                        } catch (UnsupportedEncodingException e) {
                            e.printStackTrace();
                        }


                        if (Constant.SCREEN_LAYOUT == 1) {
                            if (UserModel.Cust_mst_ID == null || UserModel.Cust_mst_ID.isEmpty()) {
                                Login.StartLoginDialog("wishlist", context);
                            } else {
                                if (Constant.ISguest) {
                                    DialogUtils.showDialog("Option not valid for guest account");
                                } else {
                                    Constant.isclickedwishlistFromViewall = true;
                                    MainActivity.getInstance().callAddToWishlistWS(sku);
                                }
                            }
                        } else {

                            if (UserModel.Cust_mst_ID == null || UserModel.Cust_mst_ID.isEmpty()) {
                                Login.StartLoginDialog("wishlist", context);
                            } else {
                                if (Constant.ISguest) {
                                    DialogUtils.showDialog("Option not valid for guest account");
                                } else {
                                    MainActivityDup.getInstance().callAddToWishlistWS(sku);
                                }
                            }
                        }

                    }
                    //Toast.makeText(context, "Move to wishlist : Under Process", Toast.LENGTH_SHORT).show();
                }

            }catch (Exception e){
                Toast.makeText(context, "Please try again later.", Toast.LENGTH_SHORT).show();
            }
        }

//        int pos = 0;
//
//        public void moveToCart(int pos) {
//            this.pos = pos;
//            String itemId = listHomrItem.get(pos).getItemMstId();
//
//            String id = String.valueOf(listHomrItem.get(pos).getCartID());
//            String cust_id;
//            String url;
//            if(UserModel.Cust_mst_ID == null){
//                cust_id = "0";
//
//                url = Constant.WS_BASE_URL + Constant.DELETE_CART + id + "/" + "Wishlist" + "/" + cust_id +
//                        "/" + listHomrItem.get(pos).getItemMstId().trim() + "/" + "1" +
//                        "/" + Constant.STOREID + "/" + "0" + "/" + "movetowishlist/I";
//
//            }else{
//                url = Constant.WS_BASE_URL + Constant.DELETE_CART + id + "/" + "Wishlist" + "/" + UserModel.Cust_mst_ID +
//                        "/" + listHomrItem.get(pos).getItemMstId().trim() + "/" + "1" +
//                        "/" + Constant.STOREID + "/" + "0" + "/" + "movetowishlist/I";
//            }
//
//            TaskDeleteWishList deleteWishList = new TaskDeleteWishList(this);
//            deleteWishList.execute(url);
//        }
//
//        @Override
//        public void onWishListResult(WishList wishList) {
//            if (myCardAdapterEvent != null) {
//                if (wishList.getResult().equals("success")) {
//
//                    if (Constant.SCREEN_LAYOUT == 1) {
//                        MainActivity.onGetCartData();
//                    } else if (Constant.SCREEN_LAYOUT == 2) {
//                        MainActivityDup.onGetCartData();
//                    }
//                    //MainActivity.getInstance().onShoppingCardResult(liShoppingCat);
//                    liShoppingCat.remove(pos);
//
//                    if (liShoppingCat.size() == 0) {
//                        CardFragment.onSetEmpty();
//                    } else {
//                        CardFragment.getInstance().onCalculateTotal(liShoppingCat);
//                        //recyclerView.removeViewAt(pos);
//                        notifyItemRemoved(pos);
//                        notifyItemRangeChanged(pos, liShoppingCat.size());
//                        notifyDataSetChanged();
//                    }
//                } else {
//                    DialogUtils.showDialog("Already in WishList!");
//                }
//            }
//        }
    }



}
