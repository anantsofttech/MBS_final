package com.aspl.Adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.aspl.Utils.Constant;
import com.aspl.Utils.Utils;
import com.aspl.mbs.MainActivity;
import com.aspl.mbs.MainActivityDup;
import com.aspl.mbs.R;
import com.aspl.mbsmodel.NotificationModel;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;


import java.util.List;

/**
 * Created by admin on 1/2/2018.
 */

public class NotificationListAdapter extends RecyclerView.Adapter<NotificationListAdapter.HomeListHolder> {

    //List<TestModel> testModels;
    List<NotificationModel> NotificationList;
    private String imgUrl = Constant.URL + Constant.IMG_NOTIFICATION_URL + Constant.STOREID + "/";
    private String imgNoImageUrl = Constant.IMG_BASE + Constant.IMG_NO_IMAGE + "/";
    //http://192.168.172.211:888/WebStoreImages/NoImage/

    Context context;
    NotificationListAdapterEvent notificationListAdapterEvent;

    public interface NotificationListAdapterEvent {
      //  void onRemoveClick(int position, List<ShoppingCardModel> NotificationList);

        //void onMoveToCartClick(int position, List<ShoppingCardModel> NotificationList);
    }

    public NotificationListAdapter(Context context, NotificationListAdapterEvent notificationListAdapterEvent, List<NotificationModel> NotificationList) {
        this.context = context;
        this.notificationListAdapterEvent = notificationListAdapterEvent;
        /*this.testModels = testModelList;*/
        this.NotificationList = NotificationList;

    }

    @Override
    public HomeListHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_rv_notification, parent, false);
        return new HomeListHolder(itemView);
    }

    @Override
    public void onBindViewHolder(HomeListHolder holder, int position) {
        Bitmap bitmap = Utils.textAsBitmap("Technical Problem", 28);
        Drawable d = new BitmapDrawable(context.getResources(), bitmap);

        if (!NotificationList.get(position).getNotificationImage().isEmpty()) {
            if (NotificationList.get(position).getNotificationImage().contains("noimage")) {
               Log.i("image", "no Image Url: " + imgNoImageUrl + NotificationList.get(position)
                        .getNotificationImage());
                Glide.with(context).load(imgNoImageUrl + NotificationList.get(position)
                        .getNotificationImage()).placeholder(R.drawable.noimage)
                        .diskCacheStrategy(DiskCacheStrategy.NONE)
                        .skipMemoryCache(true).into(holder.imgnotif);

            } else {
                Log.i("image", "Image Url : " + imgUrl + NotificationList.get(position).getNotificationImage());
                Glide.with(context).load(imgUrl + NotificationList.get(position).getNotificationImage())
                        .placeholder(R.drawable.progress_bar).placeholder(d)
                        .diskCacheStrategy(DiskCacheStrategy.NONE)
                        .skipMemoryCache(true).into(holder.imgnotif);

            }
        }
        holder.notiftxt1.setText(NotificationList.get(position).getTitle());
        holder.notiftxt2.setText(NotificationList.get(position).getDescription());
    }


    private Drawable placeholder;

    public void loadImage(String url, ImageView imageView) {

        if(Constant.SCREEN_LAYOUT==1){
            placeholder = ContextCompat.getDrawable(MainActivity.getInstance(), R.drawable.progress_bar);
        }else if(Constant.SCREEN_LAYOUT==2) {
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
        return NotificationList.size();/* return testModels.size();*/
    }


    public class HomeListHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        ImageView imgnotif;
        TextView notiftxt1,notiftxt2;

        public HomeListHolder(View itemView) {
            super(itemView);

            imgnotif = itemView.findViewById(R.id.imgnotif);
            notiftxt1 = itemView.findViewById(R.id.notiftxt1);
            notiftxt2= itemView.findViewById(R.id.notiftxt2);
        }

        @Override
        public void onClick(View view) {

            NotificationModel model=NotificationList.get(getAdapterPosition());

            /*if (view.getId() == *//*tvRemove*//*btnRemove.getId()) {
                if (notificationListAdapterEvent != null) {
                   // notificationListAdapterEvent.onRemoveClick(getAdapterPosition(), NotificationList);
                }
            }

            if (view.getId() == *//*tvMoveToCart*//*btnMoveToCard.getId()) {
                if (notificationListAdapterEvent != null) {
                   // notificationListAdapterEvent.onMoveToCartClick(getAdapterPosition(), NotificationList);
                }
            }*/
        }
    }
}
