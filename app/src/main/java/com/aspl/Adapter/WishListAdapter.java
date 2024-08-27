package com.aspl.Adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.LayerDrawable;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.aspl.Utils.Constant;
import com.aspl.Utils.Utils;
import com.aspl.mbs.MainActivity;
import com.aspl.mbs.MainActivityDup;
import com.aspl.mbs.R;
import com.aspl.mbsmodel.ShoppingCardModel;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.GlideDrawableImageViewTarget;
//import com.bumptech.glide.load.resource.drawable.GlideDrawable;
//import com.bumptech.glide.request.animation.GlideAnimation;
//import com.bumptech.glide.request.target.GlideDrawableImageViewTarget;

import java.util.List;

/**
 * Created by admin on 1/2/2018.
 */

public class WishListAdapter extends RecyclerView.Adapter<WishListAdapter.WishListHolder> {

    //List<TestModel> testModels;
    private double promoprice =  0.00;

    List<ShoppingCardModel> liShoppingCart;
    private String imgUrl = Constant.IMG_BASE + Constant.IMG_BASE_URL + Constant.STOREID + "/";
    private String imgNoImageUrl = Constant.IMG_BASE + Constant.IMG_NO_IMAGE + "/";
    //http://192.168.172.211:888/WebStoreImages/NoImage/

    Context context;
    WishListAdapterEvent wishListAdapterEvent;

    Boolean isfromwishlistRemove = false;
    Boolean isfromwishlistAddtoCart = false;

    public interface WishListAdapterEvent {
        void onRemoveClick(int position, List<ShoppingCardModel> liShoppingCart);

        void onMoveToCartClick(int position, List<ShoppingCardModel> liShoppingCart);
    }

    public WishListAdapter(Context context, WishListAdapterEvent wishListAdapterEvent, /*List<TestModel> testModelList*/List<ShoppingCardModel> liShoppingCat) {
        this.context = context;
        this.wishListAdapterEvent = wishListAdapterEvent;
        /*this.testModels = testModelList;*/
        this.liShoppingCart = liShoppingCat;
    }

    @Override
    public WishListHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_rv_wishlist, parent, false);
        return new WishListHolder(itemView);
    }

    @Override
    public void onBindViewHolder(WishListHolder holder, @SuppressLint("RecyclerView") int position) {
        Bitmap bitmap = Utils.textAsBitmap("Technical Problem", 18);
        Drawable d = new BitmapDrawable(context.getResources(), bitmap);

//        ******************* Edited by Varun on 16 Aug 2022 ************************

        if (liShoppingCart.get(position).getInvLargeImageFullPath()!=null || !liShoppingCart.get(position).getInvLargeImageFullPath().isEmpty()) {
            loadImage(liShoppingCart.get(position).getInvLargeImageFullPath().trim(), holder.img);
        }

//        ******************* END ************************

        //             Edited by Varun for sale indicator

        if (liShoppingCart.get(position).getPromoPrice() != null && !liShoppingCart.get(position).getPromoPrice().isEmpty()){

            if (!liShoppingCart.get(position).getPromoPrice().equalsIgnoreCase("0.00") && !liShoppingCart.get(position).getPromoPrice().equals(promoprice)) {


                if (liShoppingCart.get(position).getItemonPromotionIndicator().equalsIgnoreCase("NI")) {
                    holder.fl_sale_right.setVisibility(View.GONE);
                    holder.fl_sale_right.setVisibility(View.GONE);
                    holder.fl_sale_hang.setVisibility(View.GONE);
                }
                if (liShoppingCart.get(position).getItemonPromotionIndicator().equalsIgnoreCase("LR")) {
                    holder.fl_sale_right.setVisibility(View.VISIBLE);
                }
                if (liShoppingCart.get(position).getItemonPromotionIndicator().equalsIgnoreCase("UL")) {
                    holder.fl_sale_left.setVisibility(View.VISIBLE);
                }
                if (liShoppingCart.get(position).getItemonPromotionIndicator().equalsIgnoreCase("HT")) {
                    holder.fl_sale_hang.setVisibility(View.VISIBLE);
                }
            }
        }else {

        }

//            END

//          Edited by Varun for sale indicator

//        if (!liShoppingCart.get(position).getInvLargeImage().isEmpty()) {
//            if (liShoppingCart.get(position).getInvLargeImage().contains("noimage")) {
//                Log.i("image", "no Image Url: " + imgNoImageUrl + liShoppingCart.get(position)
//                        .getInvLargeImage());
//
//                loadImage(imgNoImageUrl + liShoppingCart.get(position).getInvLargeImage(), holder.img);
//            } else {
//                Log.i("image", "Image Url : " + imgUrl + liShoppingCart.get(position).getInvLargeImage());
//
//                loadImage(imgUrl + liShoppingCart.get(position).getInvLargeImage(), holder.img);
//            }
//        }

//        END

        holder.tvName.setText(liShoppingCart.get(position).getDesc1());
        if (Double.parseDouble(liShoppingCart.get(position).getPromoPrice()) > 0) {
//            String price = "$" + liShoppingCart.get(position).getPrice() /*+ " ea."*/;
            String price =liShoppingCart.get(position).getPrice() /*+ " ea."*/;
            holder.tvPrice.setText("$" + liShoppingCart.get(position).getPromoPrice());
            holder.tvPromotionPrice.setVisibility(View.VISIBLE);
            holder.tvPromotionPrice.setText(Utils.strikeText(price));
        } else {
            holder.tvPromotionPrice.setVisibility(View.GONE);
//            holder.tvPrice.setText("$" + liShoppingCart.get(position).getPrice());
            holder.tvPrice.setText(liShoppingCart.get(position).getPrice());
        }


        if (liShoppingCart.get(position).getGrpcomment() != null && !liShoppingCart.get(position).getGrpcomment().isEmpty()) {
            holder.tvSpecialOffers.setVisibility(View.VISIBLE);
            holder.tvSpecialOffers.setText(liShoppingCart.get(position).getGrpcomment());
            holder.tvSpecialOffers.setPaintFlags(holder.tvSpecialOffers.getPaintFlags()| Paint.UNDERLINE_TEXT_FLAG);
        } else {

            if (liShoppingCart.get(position).getDiscountName() != null && !liShoppingCart.get(position).getDiscountName().isEmpty()) {
                holder.tvSpecialOffers.setVisibility(View.VISIBLE);
                holder.tvSpecialOffers.setText(liShoppingCart.get(position).getDiscountName());
                holder.tvSpecialOffers.setPaintFlags(holder.tvSpecialOffers.getPaintFlags()| Paint.UNDERLINE_TEXT_FLAG);
            }else{
                holder.tvSpecialOffers.setVisibility(View.GONE);
            }
        }

        holder.tvSpecialOffers.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    if (liShoppingCart.get(position).getGrpMemo() != null && !liShoppingCart.get(position).getGrpMemo().equals("null") && !liShoppingCart.get(position).getGrpMemo().isEmpty()) {
                        Utils.showDiscountgroupDialog(context, liShoppingCart.get(position).getDesc1(), liShoppingCart.get(position).getGrpMemo(), "", null);
                    }
                    else{
                        Utils.showDiscountgroupDialog(context, liShoppingCart.get(position).getDesc1(),"No additional details have been entered by the business", "", null);
                    }
                }catch(Exception e){
                    Toast.makeText(context, "Please try again later.", Toast.LENGTH_SHORT).show();
                }
            }
        });

        if (liShoppingCart.get(position).getQtyOnHand().equals("0") || Integer.parseInt(liShoppingCart.get(position).getQtyOnHand())<0) {
            holder./*tvMoveToCart*/btnMoveToCard.setText("OUT OF STOCK");
            holder./*tvMoveToCart*/btnMoveToCard.setTextColor(context.getResources().getColor(R.color.darker_gray));
            holder.btnMoveToCard.setEnabled(false);
            holder.btnMoveToCard.setClickable(false);
        }else{
            holder.btnMoveToCard.setEnabled(true);
            holder.btnMoveToCard.setEnabled(true);
        }
    }

    private Drawable placeholder;

    public void loadImage(String url, ImageView imageView) {

        if(Constant.SCREEN_LAYOUT==1){
            placeholder = ContextCompat.getDrawable(MainActivity.getInstance(), R.drawable.progress_bar);
        }else if(Constant.SCREEN_LAYOUT==2) {
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
    /*  public Bitmap textAsBitmap(String text, float textSize*//*, int textColor*//*) {
        Paint paint = new Paint(ANTI_ALIAS_FLAG);
        paint.setTextSize(textSize);
        paint.setColor(BLACK);
        paint.setTextAlign(Paint.Align.LEFT);
        float baseline = -paint.ascent(); // ascent() is negative
        int width = (int) (paint.measureText(text) + 0.5f); // round
        int height = (int) (baseline + paint.descent() + 0.5f);
        Bitmap image = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(image);
        canvas.drawText(text, 0, 1, paint);
        return image;
    }

    public static SpannableString strikeText(String text) {
        if (text == null) text = "";
        SpannableString str = new SpannableString(text);
        str.setSpan(new StrikethroughSpan(), 0, str.length(), Spanned.SPAN_PARAGRAPH);
        return str;
    }*/

    @Override
    public int getItemCount() {
        return liShoppingCart.size();/* return testModels.size();*/
    }


    public class WishListHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView tvName, tvSpecialOffers, tvPrice, tvPromotionPrice/*,tvRemove,tvMoveToCart*/;
        ImageView img;
        RatingBar ratingBar;
        Button btnMoveToCard, btnRemove;
//         Edited by Varun for sale indicator
        FrameLayout fl_sale_left, fl_sale_right,fl_sale_hang;
        ImageView img_left ,img_right, img_hang ;

        public WishListHolder(View itemView) {
            super(itemView);

//            Edited by Varun for sale indicator

            fl_sale_left= itemView.findViewById(R.id.fl_sale_left);
            fl_sale_right= itemView.findViewById(R.id.fl_sale_right);
            fl_sale_hang = itemView.findViewById(R.id.fl_sale_hang);

            img_hang = itemView.findViewById(R.id.img_onsale_hang);
            img_left = itemView.findViewById(R.id.img_onsale_left);
            img_right = itemView.findViewById(R.id.img_onsale_right);

//            END


            ratingBar = itemView.findViewById(R.id.ratingBar_wish_list);
            LayerDrawable stars = (LayerDrawable) ratingBar.getProgressDrawable();
            stars.getDrawable(2).setColorFilter(Color.parseColor("#FFC120"), PorterDuff.Mode.SRC_ATOP);

            tvName = itemView.findViewById(R.id.tv_item_name_rv_wish_list);
            tvSpecialOffers = itemView.findViewById(R.id.tv_item_special_offers_rv_wish_list);
            /*tvSpecialOffers.setVisibility(View.GONE);*/
            tvPrice = itemView.findViewById(R.id.tv_item_price_rv_wish_list);
            tvPromotionPrice = itemView.findViewById(R.id.tv_item_promotion_price_rv_wish_list);
            img = itemView.findViewById(R.id.img_item_image_wish_list);

            btnRemove = itemView.findViewById(R.id.btn_remove_rv_wish_list);
            GradientDrawable bgShapes = (GradientDrawable) btnRemove.getBackground();
            bgShapes.setColor(Color.parseColor(Constant.themeModel.ThemeColor));
            btnRemove.setOnClickListener(this);

            btnMoveToCard = itemView.findViewById(R.id.btn_move_to_card_wish_list);
            GradientDrawable bgShape = (GradientDrawable) btnMoveToCard.getBackground();
            bgShape.setColor(Color.parseColor(Constant.themeModel.ThemeColor));
            btnMoveToCard.setOnClickListener(this);

            //tvRemove = itemView.findViewById(R.id.tv_remove_rv_wish_list);
            //tvRemove.setTextColor(Color.parseColor(Constant.themeModel.ThemeColor));
            //tvRemove.setOnClickListener(this);
            //tvMoveToCart = itemView.findViewById(R.id.tv_move_to_cart_wish_list);
            //tvMoveToCart.setTextColor(Color.parseColor(Constant.themeModel.ThemeColor));
            //tvMoveToCart.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            try {
                if (view.getId() == /*tvRemove*/btnRemove.getId()) {
                    if (wishListAdapterEvent != null) {

                        isfromwishlistRemove = true;
                        wishListAdapterEvent.onRemoveClick(getAdapterPosition(), liShoppingCart);
                    }
                }

                if (view.getId() == /*tvMoveToCart*/btnMoveToCard.getId()) {
                    if (wishListAdapterEvent != null) {
                        isfromwishlistAddtoCart = true;
                        wishListAdapterEvent.onMoveToCartClick(getAdapterPosition(), liShoppingCart);
                    }
                }
            }catch (Exception e){
                Toast.makeText(context, "Please try again later.", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
