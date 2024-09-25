package com.aspl.Adapter;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.aspl.Utils.BSTheme;
import com.aspl.Utils.Constant;
import com.aspl.mbs.MainActivity;
import com.aspl.mbs.MainActivityDup;
import com.aspl.mbs.R;
import com.aspl.mbsmodel.LstOrderTem;
import com.aspl.mbsmodel.lstGiftCardDetail;

import java.text.DecimalFormat;
import java.util.List;

public class OrderSummaryGiftCardItemAdapter extends RecyclerView.Adapter<OrderSummaryGiftCardItemAdapter.PaymentCartItemHolder> {

    List<lstGiftCardDetail> lstGiftCardDetail;
    boolean isFromOrderdatail = false;
    boolean isFromInstoreOrderdatail = false;
    boolean isFromReturnProcessing = false;
    String orderstatus = "";

    public OrderSummaryGiftCardItemAdapter(List<lstGiftCardDetail> lstGiftCardDetail, boolean isFromOrderdatail, boolean isFromInstoreOrderdatail, String orderstatus, boolean isFromReturnProcessing) {

        this.lstGiftCardDetail=lstGiftCardDetail;
        this.isFromOrderdatail = isFromOrderdatail;
        this.isFromInstoreOrderdatail = isFromInstoreOrderdatail;
        this.orderstatus = orderstatus;
        this.isFromReturnProcessing = isFromReturnProcessing;
    }

    @NonNull
    @Override
    public OrderSummaryGiftCardItemAdapter.PaymentCartItemHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_rv_item_list_order_summary_fragment, parent, false);
        return new OrderSummaryGiftCardItemAdapter.PaymentCartItemHolder(itemView);
    }

    @Override
    public void onBindViewHolder(OrderSummaryGiftCardItemAdapter.PaymentCartItemHolder holder, int i) {
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

        if(lstGiftCardDetail.get(i).getCardType() != null && !lstGiftCardDetail.get(i).getCardType().isEmpty() && lstGiftCardDetail.get(i).getCardType().equals("P")){
            holder.tvItemName.setText("Gift Card Sale:Physical Card");
        }else if(lstGiftCardDetail.get(i).getCardType() != null && !lstGiftCardDetail.get(i).getCardType().isEmpty() && lstGiftCardDetail.get(i).getCardType().equals("E")){
            holder.tvItemName.setText("Gift Card Sale:E-Mailed");
        }else if(lstGiftCardDetail.get(i).getCardType() != null && !lstGiftCardDetail.get(i).getCardType().isEmpty() && lstGiftCardDetail.get(i).getCardType().equals("T")){
            holder.tvItemName.setText("Gift Card Sale:Text Message");
        }else{
            holder.tvItemName.setText("Gift Card Sale:Physical Card");
        }

        if(lstGiftCardDetail.get(i).getgCQuantity() != null) {
            holder.tvQuantity.setText(""+ lstGiftCardDetail.get(i).getgCQuantity());
        }

        Float _total;

        if(lstGiftCardDetail.get(i).getAmountPur() != null) {
//            holder.tvPrice.setText("$" +/*df.format(*/orderTemList.get(i).getPrice())/*)*/;
            double priceval = Double.parseDouble(lstGiftCardDetail.get(i).getAmountPur());
            holder.tvPrice.setText("$" +String.format(df.format(priceval)));
        }

        if(isFromOrderdatail && !isFromReturnProcessing){
            if(lstGiftCardDetail.get(i).getAmountPur() != null && !lstGiftCardDetail.get(i).getAmountPur().isEmpty()){
                holder.tv_item_tv_ext_order_detail.setVisibility(View.VISIBLE);
                double itemPrice = Double.parseDouble(lstGiftCardDetail.get(i).getAmountPur());
                double itemQty = Double.parseDouble(lstGiftCardDetail.get(i).getgCQuantity());
                double itemExtpriceval = itemPrice*itemQty;
                holder.tv_item_tv_ext_order_detail.setText("$" + String.format(df.format(itemExtpriceval)));
            }

            if(orderstatus.equalsIgnoreCase("completed")){

                if(isFromInstoreOrderdatail){

                    holder.tv_return_title.setVisibility(View.GONE);
                    holder.tv_expired_date.setVisibility(View.GONE);

                }else{

                    holder.tv_return_title.setVisibility(View.VISIBLE);
                    holder.tv_expired_date.setVisibility(View.VISIBLE);

                }
            }else{
                holder.tv_return_title.setVisibility(View.GONE);
                holder.tv_expired_date.setVisibility(View.GONE);
            }

        }else{
            if(isFromReturnProcessing) {

                holder.tv_item_tv_ext_order_detail.setVisibility(View.VISIBLE);
                double itemPrice = Double.parseDouble(lstGiftCardDetail.get(i).getAmountPur());
                double itemQty = Double.parseDouble(lstGiftCardDetail.get(i).getgCQuantity());
                double itemExtpriceval = itemPrice*itemQty;

                holder.tv_item_tv_ext_order_detail.setText("$" + String.format(df.format(itemExtpriceval)));

            }else{
                holder.tv_item_tv_ext_order_detail.setVisibility(View.GONE);
                holder.tv_ReturnReason.setVisibility(View.GONE);
                holder.tv_ReturnComments.setVisibility(View.GONE);
            }
        }

        if(!isFromReturnProcessing) {
            holder.imggifwrap.setVisibility(View.INVISIBLE);
        }

    }
    @Override
    public int getItemCount() {
        return lstGiftCardDetail.size();
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
            if (checkBox.isChecked()) {

            }
        }
    }
}
