package com.aspl.Adapter;

import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.aspl.Utils.BSTheme;
import com.aspl.Utils.Constant;
import com.aspl.mbs.MainActivity;
import com.aspl.mbs.MainActivityDup;
import com.aspl.mbs.R;
import com.aspl.mbsmodel.LstOrderTem;

import java.text.DecimalFormat;
import java.util.List;

/**
 * Created by mic on 1/2/2018.
 */

public class OrderSummaryItemAdapter extends RecyclerView.Adapter<OrderSummaryItemAdapter.PaymentCartItemHolder> {

    //List<TestModel> testModels;
    List<LstOrderTem> orderTemList;
    boolean isFromOrderdatail = false;
    boolean isFromInstoreOrderdatail = false;
    boolean isFromReturnProcessing = false;
    String orderstatus = "";

    //private String imgUrl = Constant.URL + Constant.IMG_BASE_URL + Constant.STOREID + "/";
    //private String imgNoImageUrl = Constant.URL + Constant.IMG_NO_IMAGE + "/";
    //http://192.168.172.211:888/WebStoreImages/NoImage/

    /*Context context;*/
    /*OrderSummaryItemAdapterEvent myOrderSummaryItemAdapterEvent;
    public interface OrderSummaryItemAdapterEvent {
        void onGiftWrapChecked(int position, List<LstOrderTem> orderTemList);
    }*/

    public OrderSummaryItemAdapter(/*Context context,*/ /*OrderSummaryItemAdapterEvent myOrderSummaryItemAdapterEvent,*/ /*List<TestModel> testModelList*/
            List<LstOrderTem> lstOrderTemList, boolean isFromOrderdatail, boolean isFromInstoreOrderdatail, String orderstatus, boolean isFromReturnProcessing) {
        /*this.context = context;*/
/*        this.myOrderSummaryItemAdapterEvent = myOrderSummaryItemAdapterEvent;*/
        /*this.testModels = testModelList;*/
        this.orderTemList = lstOrderTemList;
        this.isFromOrderdatail = isFromOrderdatail;
        this.isFromInstoreOrderdatail = isFromInstoreOrderdatail;
        this.orderstatus = orderstatus;
        this.isFromReturnProcessing = isFromReturnProcessing;
    }


    @Override
    public PaymentCartItemHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_rv_item_list_order_summary_fragment, parent, false);
        return new PaymentCartItemHolder(itemView);
    }

    @Override
    public void onBindViewHolder(PaymentCartItemHolder holder, int i) {
        LinearLayout.LayoutParams param = new LinearLayout.LayoutParams(
                0,
                ViewGroup.LayoutParams.WRAP_CONTENT,
                64
        );

        DecimalFormat df = new DecimalFormat("####0.00");
        df.setMaximumFractionDigits(2);

//        DecimalFormat df = new DecimalFormat("####0.00");
//        df.setMaximumFractionDigits(2);
        //df.setRoundingMode(RoundingMode.UP);

        if(Constant.SCREEN_LAYOUT==1){
            if (MainActivity.orderSummaryFragment != null && MainActivity.orderSummaryFragment.isVisible()){
                holder.checkBox.setVisibility(View.GONE);
                holder.llRootCheckBox.setVisibility(View.GONE);
                holder.tvItemName.setLayoutParams(param);
            }
        }else if(Constant.SCREEN_LAYOUT==2) {
            if (MainActivityDup.orderSummaryFragment != null && MainActivityDup.orderSummaryFragment.isVisible()){
                holder.checkBox.setVisibility(View.GONE);
                holder.llRootCheckBox.setVisibility(View.GONE);
                holder.tvItemName.setLayoutParams(param);
            }
        }

        if(!isFromReturnProcessing) {
            if (orderTemList.get(i).getGiftWrap() != null && !orderTemList.get(i).getGiftWrap()) {
                holder.checkBox.setVisibility(View.GONE);
                holder.llRootCheckBox.setVisibility(View.GONE);
                holder.tvItemName.setLayoutParams(param);
            }
        }

//        holder.tvItemName.setText(orderTemList.get(i).getItemName());

        if(orderTemList.get(i).getItemName() != null && orderTemList.get(i).getItemName().contains("()")){
            String desc1 = orderTemList.get(i).getItemName().replace("()","");
            holder.tvItemName.setText(desc1.trim());
        }else{
            if(orderTemList.get(i).getItemName() != null){
                holder.tvItemName.setText(orderTemList.get(i).getItemName().trim());
            }
        }
        if(orderTemList.get(i).getQty() != null) {
            holder.tvQuantity.setText(""+ orderTemList.get(i).getQty());
        }

        Float _total;
        /*if (Float.parseFloat(orderTemList.get(i).getPromoPrice()) > 0){
             _total = Float.parseFloat(liShoppingCart.get(i).getPromoPrice()) * Float.parseFloat(liShoppingCart.get(i).getQty());
        }else {
            _total = Float.parseFloat(liShoppingCart.get(i).getCartPrice()) * Float.parseFloat(liShoppingCart.get(i).getQty());
        }*/
        if(orderTemList.get(i).getPrice() != null) {
//            holder.tvPrice.setText("$" +/*df.format(*/orderTemList.get(i).getPrice())/*)*/;
            double priceval = Double.parseDouble(orderTemList.get(i).getPrice());
            holder.tvPrice.setText("$" +String.format(df.format(priceval)));
        }

        if(isFromOrderdatail && !isFromReturnProcessing){
            if(orderTemList.get(i).getItemPrice() != null && !orderTemList.get(i).getItemPrice().isEmpty()){
                holder.tv_item_tv_ext_order_detail.setVisibility(View.VISIBLE);
//                holder.tv_item_tv_ext_order_detail.setText("$" + orderTemList.get(i).getItemPrice());


                double itemPrice = Double.parseDouble(orderTemList.get(i).getPrice());
                double itemQty = Double.parseDouble(orderTemList.get(i).getQty());
                double itemExtpriceval = itemPrice*itemQty;

//                double itempriceval = Double.parseDouble(orderTemList.get(i).getItemPrice());
                holder.tv_item_tv_ext_order_detail.setText("$" + String.format(df.format(itemExtpriceval)));
            }

            if(orderstatus.equalsIgnoreCase("completed")){

                if(isFromInstoreOrderdatail){

                    holder.tv_return_title.setVisibility(View.GONE);
                    holder.tv_expired_date.setVisibility(View.GONE);

                }else{

                    holder.tv_return_title.setVisibility(View.VISIBLE);
                    holder.tv_expired_date.setVisibility(View.VISIBLE);

                    if(orderTemList.get(i).getReturnDate() != null && !orderTemList.get(i).getReturnDate().isEmpty()){
                        holder.tv_expired_date.setText(orderTemList.get(i).getReturnDate());
                    }
                }
            }else{
                holder.tv_return_title.setVisibility(View.GONE);
                holder.tv_expired_date.setVisibility(View.GONE);
            }

        }else{
            if(isFromReturnProcessing) {

                holder.tv_item_tv_ext_order_detail.setVisibility(View.VISIBLE);
                double itemPrice = Double.parseDouble(orderTemList.get(i).getPrice());
                double itemQty = Double.parseDouble(orderTemList.get(i).getQty());
                double itemExtpriceval = itemPrice*itemQty;

                holder.tv_item_tv_ext_order_detail.setText("$" + String.format(df.format(itemExtpriceval)));

                if(orderTemList.get(i).getReturnReason() != null && !orderTemList.get(i).getReturnReason().isEmpty()){
                    holder.llReturnReason.setVisibility(View.VISIBLE);
                    holder.tv_ReturnReason.setText(orderTemList.get(i).getReturnReason().trim());
                }else{
                    holder.llReturnReason.setVisibility(View.GONE);
                }

                if(orderTemList.get(i).getComments() != null && !orderTemList.get(i).getComments().isEmpty()){
                    holder.llcomments.setVisibility(View.VISIBLE);
                    holder.tv_ReturnCommentsTitle.setText("Comments:");
                    holder.tv_ReturnComments.setText(orderTemList.get(i).getComments());
                }else{

                    holder.llcomments.setVisibility(View.VISIBLE);
                    holder.tv_ReturnCommentsTitle.setText("No Comments Left");
                    holder.tv_ReturnComments.setVisibility(View.GONE);
                }

            }else{
                holder.tv_item_tv_ext_order_detail.setVisibility(View.GONE);
                holder.tv_ReturnReason.setVisibility(View.GONE);
                holder.tv_ReturnComments.setVisibility(View.GONE);
            }
        }

        if(!isFromReturnProcessing) {

            if ( orderTemList.get(i).getGiftWrapSetup() != null && orderTemList.get(i).getGiftWrapSetup()) {
                if (orderTemList.get(i).getGiftWrap() != null && orderTemList.get(i).getGiftWrap()) {
                    holder.imggifwrap.setVisibility(View.VISIBLE);
                    holder.tvGiftWrap.setText("Item Flagged for Gift Wrapping");
                } else {
                    holder.imggifwrap.setVisibility(View.INVISIBLE);
                }
            }else{
                holder.imggifwrap.setVisibility(View.GONE);
            }
        }
    }

    @Override
    public int getItemCount() {
        return orderTemList.size();
    }

    public class PaymentCartItemHolder extends RecyclerView.ViewHolder implements CompoundButton.OnCheckedChangeListener {
        TextView tvItemName, tvQuantity, tvPrice,tvTitleGiftWrap,
                tvGiftWrap, tv_item_tv_ext_order_detail,tv_return_title,tv_expired_date,
                tv_ReturnReason,tv_ReturnComments,tv_ReturnReasonTitle,tv_ReturnCommentsTitle;
        CheckBox checkBox;
        LinearLayout llRootCheckBox,llReturnReason,llcomments;
        ImageView imggifwrap;
        View view;

        public PaymentCartItemHolder(View itemView) {
            super(itemView);

            checkBox = itemView.findViewById(R.id.cbx_rv_summary_fragment);
            checkBox.setVisibility(View.GONE);
            BSTheme.setCheckBoxColor(checkBox, Color.parseColor(Constant.themeModel.ThemeColor), Color.GRAY);
            checkBox.setOnCheckedChangeListener(this);
            llRootCheckBox = itemView.findViewById(R.id.ll_check_box_rv_summary_fragment);
            llReturnReason = itemView.findViewById(R.id.llReturnReason);
            llcomments = itemView.findViewById(R.id.llcomments);

            tvItemName = itemView.findViewById(R.id.tv_item_name_rv_summary_fragment);
            tv_return_title = itemView.findViewById(R.id.tv_return_title);
            tv_expired_date = itemView.findViewById(R.id.tv_expired_date);
            tvTitleGiftWrap = itemView.findViewById(R.id.tv_title_gift_wrap_summary_fragment);
            tvTitleGiftWrap.setVisibility(View.GONE);
            tvQuantity = itemView.findViewById(R.id.tv_item_quantity_rv_summary_fragment);
            tvPrice = itemView.findViewById(R.id.tv_item_price_rv_summary_fragment);
            tvGiftWrap = itemView.findViewById(R.id.tv_gift_wrap_flag_rv_order_summary_fragment);
            tv_item_tv_ext_order_detail = itemView.findViewById(R.id.tv_title_ext_order_detail);
            view = itemView.findViewById(R.id.view_rv_cart_item_summary_fragment);
            view.setVisibility(View.VISIBLE);
            imggifwrap=itemView.findViewById(R.id.imggifwrap);
            tv_ReturnReason = itemView.findViewById(R.id.tv_ReturnReason);
            tv_ReturnReasonTitle = itemView.findViewById(R.id.tv_ReturnReasonTitle);
            tv_ReturnComments = itemView.findViewById(R.id.tv_ReturnComments);
            tv_ReturnCommentsTitle = itemView.findViewById(R.id.tv_ReturnCommentsTitle);

        }

        @Override
        public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
            if (checkBox.isChecked()){
                /*if (myOrderSummaryItemAdapterEvent!=null){
                    myOrderSummaryItemAdapterEvent.onGiftWrapChecked(getAdapterPosition(),orderTemList);
                }*/
            }
        }
    }
}
