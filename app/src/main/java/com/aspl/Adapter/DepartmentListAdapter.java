package com.aspl.Adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import androidx.core.content.ContextCompat;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.aspl.Utils.Constant;
import com.aspl.Utils.Utils;
import com.aspl.mbs.MainActivity;
import com.aspl.mbs.MainActivityDup;
import com.aspl.mbs.R;
import com.aspl.mbsmodel.JackDepartmentModel;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import java.util.List;

/**
 * Created by admin on 1/2/2018.
 */

public class DepartmentListAdapter extends RecyclerView.Adapter<DepartmentListAdapter.HomeListHolder> {

    //List<TestModel> testModels;
    List<JackDepartmentModel> DepartmentList;
    private String imgUrl = Constant.IMG_BASE + Constant.IMG_DEPARTMENT;
    private String imgNoImageUrl = Constant.IMG_BASE + Constant.IMG_NO_IMAGE + "/";
    //http://192.168.172.211:888/WebStoreImages/NoImage/

    Context context;
    DepartmentListAdapterEvent departmentListAdapterEvent;

    public interface DepartmentListAdapterEvent {
        //  void onRemoveClick(int position, List<ShoppingCardModel> DepartmentList);

        //void onMoveToCartClick(int position, List<ShoppingCardModel> DepartmentList);
    }

    public DepartmentListAdapter(Context context, DepartmentListAdapterEvent departmentListAdapterEvent, List<JackDepartmentModel> liShoppingCat) {
        this.context = context;
        this.departmentListAdapterEvent = departmentListAdapterEvent;
        /*this.testModels = testModelList;*/
        this.DepartmentList = liShoppingCat;

    }

    @Override
    public HomeListHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_rv_home_item, parent, false);
        return new HomeListHolder(itemView);
    }

    @Override
    public void onBindViewHolder(HomeListHolder holder, int position) {
        Bitmap bitmap = Utils.textAsBitmap("Technical Problem", 18);
        Drawable d = new BitmapDrawable(context.getResources(), bitmap);

        if (!DepartmentList.get(position).getDeptImg().isEmpty()) {
            if (DepartmentList.get(position).getDeptImg().contains("noimage")) {
                Log.i("image", "no Image Url: " + imgNoImageUrl + DepartmentList.get(position)
                        .getDeptImg());
                Glide.with(context).load(imgNoImageUrl + DepartmentList.get(position)
                        .getDeptImg()).placeholder(R.drawable.noimage)
                        .diskCacheStrategy(DiskCacheStrategy.NONE)
                        .skipMemoryCache(true).into(holder.img_item);

                // loadImage(imgNoImageUrl + DepartmentList.get(position).getInvLargeImage(), holder.img);
            } else {
                Log.i("image", "Image Url : " + imgUrl + DepartmentList.get(position).getDeptImg());
               Glide.with(context).load(imgUrl + DepartmentList.get(position).getDeptImg())
                        .placeholder(R.drawable.progress_bar).placeholder(d)
                        .diskCacheStrategy(DiskCacheStrategy.NONE)
                        .skipMemoryCache(true).into(holder.img_item);
               // holder.img_item.setImageDrawable(d);
               /* Glide.with(context.getApplicationContext())
                        .asBitmap()
                        .load(iconUrl)
                        .into(object : SimpleTarget<Bitmap>(Target.SIZE_ORIGINAL, Target.SIZE_ORIGINAL) {
                    override fun onResourceReady(resource: Bitmap, transition: com.bumptech.glide.request.transition.Transition<in Bitmap>?) {
                        callback.onReady(createMarkerIcon(resource, iconId))
                    }
                })*/
                // loadImage(imgUrl + DepartmentList.get(position).getInvLargeImage(), holder.img);
            }
        }/*else{
            holder.img.setImageBitmap(bitmap);
        }*/

        holder.tvDiscountName.setVisibility(View.INVISIBLE);
        holder.txtitemtitle.setVisibility(View.GONE);
        holder.txtitemtitle.setText(DepartmentList.get(position).getDeptDesc());
        holder.txtprice.setText( DepartmentList.get(position).getDeptDesc());

        /*if (Double.parseDouble(DepartmentList.get(position).getPromoPrice()) > 0) {
            String price = "$" + DepartmentList.get(position).getPrice() *//*+ " ea."*//*;
            holder.tvPrice.setText("$" + DepartmentList.get(position).getPromoPrice());
            holder.tvPromotionPrice.setVisibility(View.VISIBLE);
            holder.tvPromotionPrice.setText(Utils.strikeText(price));
        } else {
            holder.tvPromotionPrice.setVisibility(View.GONE);
            holder.tvPrice.setText("$" + DepartmentList.get(position).getPrice());
        }
        if (!DepartmentList.get(position).getDiscountName().isEmpty()) {
            holder.tvSpecialOffers.setVisibility(View.VISIBLE);
            holder.tvSpecialOffers.setText(DepartmentList.get(position).getDiscountName());
        }
        if (DepartmentList.get(position).getQtyOnHand().equals("0")) {
            holder.*//*tvMoveToCart*//*btnMoveToCard.setText("OUT OF STOCK");
            holder.*//*tvMoveToCart*//*btnMoveToCard.setTextColor(Color.GRAY);
        }*/
    }


    private Drawable placeholder;

    public void loadImage(String url, ImageView imageView) {

        if (Constant.SCREEN_LAYOUT == 1) {
            placeholder = ContextCompat.getDrawable(MainActivity.getInstance(), R.drawable.progress_bar);
        } else if (Constant.SCREEN_LAYOUT == 2) {
            placeholder = ContextCompat.getDrawable(MainActivityDup.getInstance(), R.drawable.progress_bar);
        }
        /*Glide.with(imageView.getContext())
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
                });*/
    }

    @Override
    public int getItemCount() {
        return DepartmentList.size();/* return testModels.size();*/
    }

    public class HomeListHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        ImageView img_item;
        TextView txtitemtitle, txtprice, tvDiscountName ,txtitemOutOfStock ,txtWhishList;
        CardView MainCardView;
        LinearLayout ll_qtyandCart;

        public HomeListHolder(View itemView) {
            super(itemView);

            //          Edited by Varun

            txtitemOutOfStock = itemView.findViewById(R.id.txtitemOutOfStock);
            txtitemOutOfStock.setVisibility(View.GONE);
            ll_qtyandCart = itemView.findViewById(R.id.ll_qtyandCart);
            ll_qtyandCart.setVisibility(View.GONE);
            txtWhishList = itemView.findViewById(R.id.tvWishlist);
            txtWhishList.setVisibility(View.GONE);

//            END


            img_item = itemView.findViewById(R.id.img_item);
            tvDiscountName = (TextView)itemView.findViewById(R.id.tvDiscountName);
            txtitemtitle = itemView.findViewById(R.id.txtitemtitle);
            txtprice = itemView.findViewById(R.id.txtprice);
            MainCardView = itemView.findViewById(R.id.MainCardView);
            MainCardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    JackDepartmentModel model = DepartmentList.get(getAdapterPosition());

                    if (Constant.SCREEN_LAYOUT == 1) {
                        MainActivity.getInstance().loadViewAllFragment("department",String.valueOf(model.getDeptId()),"0", "0","0", "0", MainActivity.blockDisplayedText, "", "OnlyDepartment");
                    } else if (Constant.SCREEN_LAYOUT == 2) {
                        MainActivityDup.getInstance().loadViewAllFragment("department",String.valueOf(model.getDeptId()),"0", "0", "0", "0", MainActivityDup.blockDisplayedText,"", "OnlyDepartment");
                    }

//                    if (Constant.SCREEN_LAYOUT == 1) {
//                        if (UserModel.Cust_mst_ID != null;) {
//                            /** For Customer ID **/
//                            MainActivity.getInstance().mContainer.removeAllViews();
//                            MainActivity.getInstance().LoadWebVew(Constant.URL + "/inventory/inventoryapp"
//                                    + "?customerid=" + UserModel.Cust_mst_ID
//                                    + "&sessionid=" + "0"
//                                    + "&storeno=" + Constant.STOREID
//                                    + "&deptid=" + model.getDeptId()
//                                    + "&subdeptid=" + "");
//                            Constant.clickedDeptIdfromhome = model.getDeptId();
//                        } else {
//                            /** For Session ID **/
//
//                            MainActivity.getInstance().mContainer.removeAllViews();
//                            MainActivity.getInstance().LoadWebVew(Constant.URL + "/inventory/inventoryapp"
//                                    + "?customerid=" + "0"
//                                    + "&sessionid=" + DeviceInfo.getDeviceId(MainActivity.getInstance()) + "0011"
//                                    + "&storeno=" + Constant.STOREID
//                                    + "&deptid=" + model.getDeptId()
//                                    + "&subdeptid=" + "");
//                            Constant.clickedDeptIdfromhome = model.getDeptId();
//                        }
//                    } else if (Constant.SCREEN_LAYOUT == 2) {
//                        if (UserModel.Cust_mst_ID != null) {
//                            /** For Customer ID **/
//                            MainActivityDup.getInstance().mContainer.removeAllViews();
//                            MainActivityDup.getInstance().LoadWebVew(Constant.URL + "/inventory/inventoryapp"
//                                    + "?customerid=" + UserModel.Cust_mst_ID
//                                    + "&sessionid=" + "0"
//                                    + "&storeno=" + Constant.STOREID
//                                    + "&deptid=" + model.getDeptId()
//                                    + "&subdeptid=" + "");
//                            Constant.clickedDeptIdfromhome = model.getDeptId();
//                        } else {
//                            /** For Session ID **/
//
//                            MainActivityDup.getInstance().mContainer.removeAllViews();
//                            MainActivityDup.getInstance().LoadWebVew(Constant.URL + "/inventory/inventoryapp"
//                                    + "?customerid=" + "0"
//                                    + "&sessionid=" + DeviceInfo.getDeviceId(MainActivityDup.getInstance()) + "0011"
//                                    + "&storeno=" + Constant.STOREID
//                                    + "&deptid=" + model.getDeptId()
//                                    + "&subdeptid=" + "");
//                            Constant.clickedDeptIdfromhome = model.getDeptId();
//                            //MainActivityDup.getInstance().getSupportFragmentManager().popBackStackImmediate();
//                        }
//                    }
                }
            });
        }

        @Override
        public void onClick(View view) {

            JackDepartmentModel model = DepartmentList.get(getAdapterPosition());

        }
    }
}
