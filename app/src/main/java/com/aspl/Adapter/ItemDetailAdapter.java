package com.aspl.Adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;
import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.aspl.Utils.Constant;
import com.aspl.Utils.Utils;
import com.aspl.fragment.OrderHistoryFragment;
import com.aspl.mbs.MainActivity;
import com.aspl.mbs.MainActivityDup;
import com.aspl.mbs.R;
import com.aspl.mbsmodel.LstOrderTem;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.GlideDrawableImageViewTarget;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class ItemDetailAdapter extends RecyclerView.Adapter<ItemDetailAdapter.MyViewHolder>{

    List<LstOrderTem> lstOrderTemsList;
    Context context;
    private String imgNoImageUrl = Constant.IMG_BASE + Constant.IMG_NO_IMAGE + "/";
    private String imgUrl;
    //    private String imgUrl = Constant.IMG_BASE + Constant.IMG_BASE_URL + Constant.STOREID + "/";
    int remainingQtyForReturn = 0;
    String storeno_selected;

    public ItemDetailAdapter(Context context, List<LstOrderTem> lstOrderTemsList, String storeno_selected) {

        this.context = context;
        this.lstOrderTemsList = lstOrderTemsList;
        this.storeno_selected = storeno_selected;
        imgUrl = Constant.IMG_BASE + Constant.IMG_BASE_URL + storeno_selected + "/";

    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_order_item_detail, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, @SuppressLint("RecyclerView") int position) {

        holder.setIsRecyclable(false);

        LstOrderTem lstOrderTem = lstOrderTemsList.get(position);

        holder.tv_DateVal.setText(lstOrderTemsList.get(position).getOrderItemDate());

//        holder.tv_itemName.setText(lstOrderTemsList.get(position).getItemName());

        if(lstOrderTemsList.get(position).getItemName().contains("()")){
            String itemname = lstOrderTemsList.get(position).getItemName().replace("()","");
            holder.tv_itemName.setText(itemname.trim());
        }else{
            holder.tv_itemName.setText(lstOrderTemsList.get(position).getItemName().trim());
        }

        if(lstOrderTemsList.get(position).getOrderItemStatus().equalsIgnoreCase("open")){
            holder.tv_statusvalue.setTextColor(context.getResources().getColor(R.color.green));

        }else if(lstOrderTemsList.get(position).getOrderItemStatus().equalsIgnoreCase("cancelled")){
            holder.tv_statusvalue.setTextColor(context.getResources().getColor(R.color.red));

        }else {
            holder.tv_statusvalue.setTextColor(context.getResources().getColor(R.color.blue_search));
        }

        holder.tv_statusvalue.setText(lstOrderTemsList.get(position).getOrderItemStatus());

        if(!lstOrderTemsList.get(position).getOrderItemStatus().isEmpty()){

            if(lstOrderTemsList.get(position).getOrderItemStatus().equalsIgnoreCase("Completed")){
//                holder.tv_startReturn.setVisibility(View.VISIBLE);
//                holder.tv_startReturn.setText("Start a return");

                if(lstOrderTemsList.get(position).getLstReturnItems() != null && lstOrderTemsList.get(position).getLstReturnItems().size()>0){

                    if(lstOrderTemsList.get(position).getLstReturnItems().get(0).getOrderStatus().equalsIgnoreCase("Completed")){
                        //display refund in placed of return processing\

                    }else{

                        for (int i=0; i<lstOrderTemsList.get(position).getLstReturnItems().size();i++){

//                            ViewGroup.LayoutParams lparams = new ViewGroup.LayoutParams(
//                                    ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);

                            LinearLayout.LayoutParams lparams = new LinearLayout.LayoutParams(
                                    LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);

                            lparams.setMargins(0,2,0,2);

                            TextView tv = new TextView(context);
                            tv.setPadding(0,10,0,10);
                            tv.setLayoutParams(lparams);
                            int no = i+1;
                            tv.setText(no+"."+" Return Processing");

                            tv.setTypeface(null, Typeface.BOLD);
                            tv.setTextColor(Color.parseColor(Constant.themeModel.ThemeColor));
                            holder.lldynamic_returnProcessing.addView(tv);

                            int finalI = i;
                            tv.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {

                                    if(lstOrderTemsList.get(position).getLstReturnItems().get(finalI).getOrderReturnID() != null &&
                                    !lstOrderTemsList.get(position).getLstReturnItems().get(finalI).getOrderReturnID().isEmpty()) {

                                        Log.d("test", ":" + lstOrderTemsList.get(position).getLstReturnItems().get(finalI).getOrderReturnID());
                                        String returnedOrderId = lstOrderTemsList.get(position).getLstReturnItems().get(finalI).getOrderReturnID();

                                        OrderHistoryFragment.getInstance().callOrderSummaryResultWebService(returnedOrderId,"ReturnProcessing");
                                    }
                                }
                            });

//                            Log.d("tesssss",":" +position + lstOrderTemsList.get(position).getItemName());
                        }

                        if(lstOrderTemsList.get(position).getLstReturnItems().get(0).getRemainingQty() != null
                                && !lstOrderTemsList.get(position).getLstReturnItems().get(0).getRemainingQty().isEmpty()){
                            String returnedQty = "";
                            int totalItemQty = Integer.parseInt(lstOrderTemsList.get(position).getQty());

                            if(lstOrderTemsList.get(position).getLstReturnItems().get(0).getRemainingQty().contains("-")){
                                returnedQty = lstOrderTemsList.get(position).getLstReturnItems().get(0).getRemainingQty().replace("-","");
                            }

                            remainingQtyForReturn = totalItemQty - Integer.parseInt(returnedQty);

                            if(remainingQtyForReturn > 0){

                                holder.tv_startReturn.setVisibility(View.VISIBLE);
                                holder.tv_startReturn.setText("Start a return");

                            }
                        }
                    }
                } else{

                    if(lstOrderTemsList.get(position).getReturnDate() != null && !lstOrderTemsList.get(position).getReturnDate().isEmpty() ){

                        try {

                            String currentString = lstOrderTemsList.get(position).getReturnDate();
//                            if(currentString.contains(" ")){
//                                String[] separated = currentString.split(" ");
//                                String str1 = separated[0];
//                                String str2 = separated[1];
//                                String str3 = separated[2];
//                                String str4 = separated[3];

                                currentString = currentString.trim();
                                String newString = "";

                                if(currentString.length() > 17){
                                    newString = currentString.substring(currentString.length() - 17);
                                }
//                                String newString = currentString.lastIndexOf(6, 17);
//                                String remainder = currentString.substring(17);

//                                SimpleDateFormat currentDateFmt = new SimpleDateFormat("EEE dd MMM yyyy");
//                                SimpleDateFormat reuiredDateFmt = new SimpleDateFormat("dd/MM/yyyy");

//                                Date date1 = currentDateFmt.parse(newString);
//                                Date str22 = reuiredDateFmt.parse(date1);

//                                SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
//                                String output1 = sdf.format(date1); //
//                            Until Thu, Oct 28, 2021



                            SimpleDateFormat sdf = new SimpleDateFormat("EEE, MMM dd, yyyy",
                                    Locale.ENGLISH);

                            Date parsedDate = sdf.parse(newString);

                            Date c = Calendar.getInstance().getTime();

                            String formattedDate = sdf.format(c);

                            Date todayDate = sdf.parse(formattedDate);


//                            if (System.currentTimeMillis() > strDate.getTime()) {
//                                if (new Date().after(date1)) {

                                if (todayDate.after(parsedDate)) {
                                    holder.tv_startReturn.setVisibility(View.INVISIBLE);
                                }
////                            *************** Edited by Varun ****************
//                            if (todayDate.equals(parsedDate)||todayDate.after(parsedDate)) {
//                                    holder.tv_startReturn.setVisibility(View.INVISIBLE);
//                                }
//                            ****************** END ****************
                                else{
                                     holder.tv_startReturn.setVisibility(View.VISIBLE);
                                    holder.tv_startReturn.setText("Start a return");
                                }
//                            }else{
//
//                            }

                        } catch (ParseException e) {
                            e.printStackTrace();
                        }

                    }

                }

            }else{
                holder.tv_startReturn.setVisibility(View.INVISIBLE);
            }
        }

        if(lstOrderTemsList.get(position).getReturnDate() != null && !lstOrderTemsList.get(position).getOrderItemStatus().equalsIgnoreCase("open")){

            String sourceString = "<b>" + "Return Eligibility:" + "</b> " + lstOrderTemsList.get(position).getReturnDate();
            holder.tv_ReturnEligibilityValue.setText(Html.fromHtml(sourceString));

        }else{

            String sourceString = "<b>" + "Return Eligibility:" + "</b> " + "N/A" ;
            holder.tv_ReturnEligibilityValue.setText(Html.fromHtml(sourceString));

        }

        holder.tv_quntityValue.setText(lstOrderTemsList.get(position).getQty());

////        ******************* Edited by Varun on 16 Aug 2022 ************************

         if (lstOrderTemsList.get(position).getInvLargeImageFullPath()!=null || !lstOrderTemsList.get(position).getInvLargeImageFullPath().isEmpty()) {
             Glide.with(context).load(lstOrderTemsList.get(position).getInvLargeImageFullPath())
                     .placeholder(R.drawable.noimage)
                     .error(R.drawable.no_image_new)
                     .diskCacheStrategy(DiskCacheStrategy.NONE)
                     .skipMemoryCache(true).into(holder.iv_orderImg);
         }

////        ******************* END ************************

//        if (!lstOrderTemsList.get(position).getInvLargeImage().isEmpty()) {
//            {
//                if (lstOrderTemsList.get(position).getInvLargeImage().contains("noimage")) {
//
//                    Glide.with(context).load(imgNoImageUrl + lstOrderTemsList.get(position)
//                            .getInvSmallImage()).placeholder(R.drawable.noimage)
//                            .error(R.drawable.no_image_new)
//                            .diskCacheStrategy(DiskCacheStrategy.NONE)
//                            .skipMemoryCache(true).into(holder.iv_orderImg);
//
//                } else {
//                    Glide.with(context).load(imgUrl + lstOrderTemsList.get(position).getInvSmallImage())
//                            .placeholder(R.drawable.progress_bar)
//                            .error(R.drawable.no_image_new)
//                            .diskCacheStrategy(DiskCacheStrategy.NONE)
//                            .skipMemoryCache(true).into(holder.iv_orderImg);
//                }
//            }
//        }
        holder.tv_buyItagain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

//                Constant.invType = lstOrderTemsList.get(position).getInvType().toString();

//                String orderid = lstOrderTemsList.get(position).getOrderID();
//                String itemid = lstOrderTemsList.get(position).getItemID();
//
//                MainActivity.getInstance().showReOrderConfirmation(orderid,itemid, 1);

                    Utils.showDiscountgroupDialog(context, "","","buyitagain",lstOrderTem);
            }
        });

        holder.tv_startReturn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                OrderHistoryFragment.getInstance().callReturnProcessWs(lstOrderTem);
            }
        });

        holder.itemView.setOnClickListener(view -> {

            if (lstOrderTemsList.get(position).getOrderID() != null && !lstOrderTemsList.get(position).getOrderID().isEmpty()) {
                OrderHistoryFragment.getInstance().callOrderSummaryResultWebService(lstOrderTemsList.get(position).getOrderID(),"");
            }
        });
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
        return lstOrderTemsList.size();
    }


    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView tv_itemName, tv_DateVal, tv_ReturnEligibilityValue,
                tv_quntityValue, tv_statusvalue,tv_startReturn,tv_buyItagain;
        ImageView iv_orderImg;
        public LinearLayout lldynamic_returnProcessing;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            iv_orderImg = itemView.findViewById(R.id.iv_orderImg);
            tv_itemName = itemView.findViewById(R.id.tv_itemName);
            tv_statusvalue = itemView.findViewById(R.id.tv_statusvalue);
            tv_DateVal = itemView.findViewById(R.id.tv_Datevalue);
            tv_ReturnEligibilityValue = itemView.findViewById(R.id.tv_ReturnEligibilityValue);
            tv_quntityValue = itemView.findViewById(R.id.tv_quntityValue);
            tv_startReturn = itemView.findViewById(R.id.tv_startReturn);
            tv_buyItagain = itemView.findViewById(R.id.tv_buyItagain);
            tv_quntityValue = itemView.findViewById(R.id.tv_quntityValue);
            lldynamic_returnProcessing = itemView.findViewById(R.id.lldynamic_returnProcessing);

            tv_startReturn.setTextColor(Color.parseColor(Constant.themeModel.ThemeColor));
            tv_startReturn.setPaintFlags(tv_startReturn.getPaintFlags()| Paint.UNDERLINE_TEXT_FLAG);

            tv_buyItagain.setTextColor(Color.parseColor(Constant.themeModel.ThemeColor));
            tv_buyItagain.setPaintFlags(tv_buyItagain.getPaintFlags()| Paint.UNDERLINE_TEXT_FLAG);

        }
    }
}
