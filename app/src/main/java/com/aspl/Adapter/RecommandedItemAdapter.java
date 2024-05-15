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
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.aspl.Utils.Constant;
import com.aspl.Utils.Utils;
import com.aspl.fragment.ItemDescriptionsFragment;
import com.aspl.mbs.MainActivity;
import com.aspl.mbs.MainActivityDup;
import com.aspl.mbs.R;
import com.aspl.mbsmodel.ItemDescModel;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.GlideDrawableImageViewTarget;

import java.util.List;


public class RecommandedItemAdapter extends RecyclerView.Adapter<RecommandedItemAdapter.MyViewHolder> {

    Context context;
    List<ItemDescModel> recommandedItemList;
    private String imgNoImageUrl = Constant.IMG_BASE + Constant.IMG_NO_IMAGE + "/";
    private String imgUrl = Constant.IMG_BASE + Constant.IMG_BASE_URL + Constant.STOREID + "/";
    private Double promoprice = 0.00;

//    RecommandedItemInterface recommandedItemevent;
//
//    public interface RecommandedItemInterface{
//        void recommandedItemResult(ItemDescModel recommandedItemDescModel);
//    }

    public RecommandedItemAdapter(Context context, List<ItemDescModel> recommandedItemList) {
        this.context = context;
        this.recommandedItemList =  recommandedItemList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_rv_home_item, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, @SuppressLint("RecyclerView") int position) {

        if(recommandedItemList.get(position)!= null){
//            if (!recommandedItemList.get(position).getInvSmallImage().isEmpty()) {
//                if (recommandedItemList.get(position).getInvSmallImage().contains("noimage")) {
//
//                    Glide.with(context).load(imgNoImageUrl + recommandedItemList.get(position)
//                            .getInvLargeImage()).placeholder(R.drawable.noimage)
//                            .diskCacheStrategy(DiskCacheStrategy.NONE)
//                            .skipMemoryCache(true).into(holder.img_item);
//
//                } else {
//
//                    Glide.with(context).load(imgUrl + recommandedItemList.get(position).getInvLargeImage())
//                            .placeholder(R.drawable.progress_bar)
//                            .diskCacheStrategy(DiskCacheStrategy.NONE)
//                            .skipMemoryCache(true).into(holder.img_item);
//
//                }
//            }

//            ********************* Edited by Varun on 16 Aug 2022 *******************

            if (recommandedItemList.get(position).getInvLargeImageFullPath()!=null || !recommandedItemList.get(position).getInvLargeImageFullPath().isEmpty()) {
                Glide.with(context).load( recommandedItemList.get(position).getInvLargeImageFullPath())
                        .placeholder(R.drawable.progress_bar)
                        .diskCacheStrategy(DiskCacheStrategy.NONE)
                        .skipMemoryCache(true).into(holder.img_item);

            }
//            ********************* END *******************

            if (!recommandedItemList.get(position).getPromoPrice().equalsIgnoreCase("0.00") && !recommandedItemList.get(position).getPromoPrice().equals(promoprice)) {
                if (recommandedItemList.get(position).getItemonPromotionIndicator().equalsIgnoreCase("NI")) {
                    holder.fl_sale_right.setVisibility(View.GONE);
                    holder.fl_sale_right.setVisibility(View.GONE);
                    holder._fl_sale_hang.setVisibility(View.GONE);
                }
                if (recommandedItemList.get(position).getItemonPromotionIndicator().equalsIgnoreCase("LR")) {
                    holder.fl_sale_right.setVisibility(View.VISIBLE);
                }
                if (recommandedItemList.get(position).getItemonPromotionIndicator().equalsIgnoreCase("UL")) {
                    holder.fl_sale_left.setVisibility(View.VISIBLE);
                }
                if (recommandedItemList.get(position).getItemonPromotionIndicator().equalsIgnoreCase("HT")) {
                    holder._fl_sale_hang.setVisibility(View.VISIBLE);
                }
            }

            holder.txtitemtitle.setText(recommandedItemList.get(position).getDesc1());

//            holder.txtprice.setText("$"+ recommandedItemList.get(position).getPrice());

            holder.txtprice.setText("$" + recommandedItemList.get(position).getPrice());

            if (recommandedItemList.get(position).getPromoPrice() != null && !recommandedItemList.get(position).getPromoPrice().toString().isEmpty() && !recommandedItemList.get(position).getPromoPrice().toString().equalsIgnoreCase("0.00")) {
                holder.txtPromoprice.setVisibility(View.VISIBLE);
                holder.txtPromoprice.setText("$" + recommandedItemList.get(position).getPromoPrice());
                holder.txtprice.setPaintFlags(holder.txtPromoprice.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
                holder.txtprice.setTextColor(Color.GRAY);
            } else {
                holder.txtPromoprice.setVisibility(View.GONE);
                holder.txtprice.setTextColor(Color.BLACK);
                holder.txtprice.setPaintFlags(holder.txtPromoprice.getPaintFlags() & (~Paint.STRIKE_THRU_TEXT_FLAG));
                holder.txtprice.setGravity(Gravity.CENTER);
            }

            if (!recommandedItemList.get(position).getInventoryRating().isEmpty() && !recommandedItemList.get(position).getInventoryRating().equals("0")) {
                LayerDrawable stars = (LayerDrawable)holder.ratingBar.getProgressDrawable();
                stars.getDrawable(2).setColorFilter(Color.parseColor("#FFC120"), PorterDuff.Mode.SRC_ATOP);

                holder.ratingBar.setVisibility(View.VISIBLE);
                holder.ratingBar.setNumStars(Integer.parseInt(recommandedItemList.get(position).getInventoryRating()));
                holder.ratingBar.setRating(Float.parseFloat(recommandedItemList.get(position).getInventoryRating()));
            } else {
                holder.ratingBar.setVisibility(View.GONE);
            }

//            if (recommandedItemList.get(position).getDiscountName() != null && !recommandedItemList.get(position).getDiscountName().isEmpty()) {
//                holder.tvDiscountName.setVisibility(View.VISIBLE);
//                holder.tvDiscountName.setText(recommandedItemList.get(position).getDiscountName());
//            } else {
//                holder.tvDiscountName.setVisibility(View.INVISIBLE);
//            }

            if (recommandedItemList.get(position).getGrpcomment() != null && !recommandedItemList.get(position).getGrpcomment().isEmpty()) {
                holder.tvDiscountName.setVisibility(View.VISIBLE);
                holder.tvDiscountName.setText(recommandedItemList.get(position).getGrpcomment());
                holder.tvDiscountName.setPaintFlags(holder.tvDiscountName.getPaintFlags()| Paint.UNDERLINE_TEXT_FLAG);
            } else {

                if (recommandedItemList.get(position).getDiscountName() != null && !recommandedItemList.get(position).getDiscountName().isEmpty()) {
                    holder.tvDiscountName.setVisibility(View.VISIBLE);
                    holder.tvDiscountName.setText(recommandedItemList.get(position).getDiscountName());
                    holder.tvDiscountName.setPaintFlags(holder.tvDiscountName.getPaintFlags()| Paint.UNDERLINE_TEXT_FLAG);
                }else{
                    holder.tvDiscountName.setVisibility(View.INVISIBLE);
                }

            }


            holder.tvDiscountName.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    if(recommandedItemList.get(position).getGrpMemo()!= null && !recommandedItemList.get(position).getGrpMemo().equals("null") && !recommandedItemList.get(position).getGrpMemo().isEmpty()) {

                        Utils.showDiscountgroupDialog(context, recommandedItemList.get(position).getDesc1(), recommandedItemList.get(position).getGrpMemo(), "", null);
                    }
                }
            });

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

        if(recommandedItemList.size() > 5){
            return 5;
        }else{
            return recommandedItemList.size();
        }
    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        ImageView img_item,ivAddtoCart;
        TextView txtitemtitle, txtprice, txtPromoprice, tvDiscountName,txtitemOutOfStock;
        CardView mainCardView;
        RatingBar ratingBar;
        FrameLayout fl_sale_left, fl_sale_right,_fl_sale_hang;

        LinearLayout ll_qtyandCart;
        public int count = 1;

        RelativeLayout rl_wishlist;

        public MyViewHolder(View itemView) {
            super(itemView);


//            Edited by Varun

            rl_wishlist = itemView.findViewById(R.id.rl_wishlist);
            rl_wishlist.setVisibility(View.GONE);
            txtitemOutOfStock = itemView.findViewById(R.id.txtitemOutOfStock);
            txtitemOutOfStock.setVisibility(View.GONE);
            ll_qtyandCart = itemView.findViewById(R.id.ll_qtyandCart);
            ll_qtyandCart.setVisibility(View.GONE);

//            END


            fl_sale_left= itemView.findViewById(R.id.fl_sale_left);
            fl_sale_right= itemView.findViewById(R.id.fl_sale_right);
            _fl_sale_hang = itemView.findViewById(R.id.fl_sale_hang);


            img_item = itemView.findViewById(R.id.img_item);
            txtitemtitle = itemView.findViewById(R.id.txtitemtitle);
            txtprice = itemView.findViewById(R.id.txtprice);
            mainCardView = itemView.findViewById(R.id.MainCardView);
            ratingBar = itemView.findViewById(R.id.ratingBar);
            txtPromoprice = itemView.findViewById(R.id.txtPromoprice);
            tvDiscountName = itemView.findViewById(R.id.tvDiscountName);

            mainCardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    if(recommandedItemList.get(getAdapterPosition()) != null)
                        ItemDescriptionsFragment.getInstance().CallWSForItemDescriptionDetails(recommandedItemList.get(getAdapterPosition()).getItemMstId());
//                        ItemDescriptionsFragment.getInstance().onItemDescResult(recommandedItemList.get(getAdapterPosition()));

                }
            });
        }

        @Override
        public void onClick(View view) {
       }


    }
}
