package com.aspl.Adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.aspl.Utils.BSTheme;
import com.aspl.Utils.Constant;
import com.aspl.fragment.PaymentFragment;
import com.aspl.mbs.MainActivity;
import com.aspl.mbs.MainActivityDup;
import com.aspl.mbs.R;
import com.aspl.mbsmodel.LstGiftCard_cart;

import java.text.DecimalFormat;
import java.util.List;

public class PaymentCartGiftCardListAdapter extends RecyclerView.Adapter<PaymentCartGiftCardListAdapter.PaymentCartItemHolder>{

    Context context;
    List<LstGiftCard_cart> lstGiftCard;

    public PaymentCartGiftCardListAdapter(Context context, PaymentFragment paymentFragment, List<LstGiftCard_cart> lstGiftCard) {

        this.context=context;
        this.lstGiftCard=lstGiftCard;

    }

    @NonNull
    @Override
    public PaymentCartGiftCardListAdapter.PaymentCartItemHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_rv_item_list_payment_fragment, parent, false);
        return new PaymentCartGiftCardListAdapter.PaymentCartItemHolder(itemView);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull PaymentCartGiftCardListAdapter.PaymentCartItemHolder holder, int i) {

        // Layout params for adjusting the visibility and layout of CheckBox and item name
        LinearLayout.LayoutParams param = new LinearLayout.LayoutParams(
                0,
                ViewGroup.LayoutParams.WRAP_CONTENT,
                64
        );

        DecimalFormat df = new DecimalFormat("####0.00");
        df.setMaximumFractionDigits(2);

        // Adjust view based on the SCREEN_LAYOUT value
        if(Constant.SCREEN_LAYOUT == 1){
            if (MainActivity.orderSummaryFragment != null && MainActivity.orderSummaryFragment.isVisible()){
                holder.checkBox.setVisibility(View.GONE);
                holder.llRootCheckBox.setVisibility(View.GONE);
                holder.tvItemName.setLayoutParams(param);
            }
        } else if(Constant.SCREEN_LAYOUT == 2) {
            if (MainActivityDup.orderSummaryFragment != null && MainActivityDup.orderSummaryFragment.isVisible()){
                holder.checkBox.setVisibility(View.GONE);
                holder.llRootCheckBox.setVisibility(View.GONE);
                holder.tvItemName.setLayoutParams(param);
            }
        }

        if (Constant.Inventory_liCardModel.get(0).getGiftWrap()!=null  && !Constant.Inventory_liCardModel.get(0).getGiftWrap()){
            holder.checkBox.setVisibility(View.GONE);
            holder.llRootCheckBox.setVisibility(View.GONE);
            holder.tvItemName.setLayoutParams(param);
        }

        // Set the card type name
        switch (lstGiftCard.get(i).getCardType()) {
            case "P":
                holder.tvItemName.setText("Gift Card Sale: Physical Card");
                break;
            case "T":
                holder.tvItemName.setText("Gift Card Sale: Text");
                break;
            case "E":
                holder.tvItemName.setText("Gift Card Sale: E-Mail");
                break;
        }

        // Set the quantity
        if (lstGiftCard.get(i).getgCQuantity() != null && !lstGiftCard.get(i).getgCQuantity().isEmpty()) {
            holder.tvQuantity.setText(lstGiftCard.get(i).getgCQuantity());
        }

        // Calculate the total price based on the cart details
        Float totalPrice = 0.00f;

        // Loop through the items to calculate the total based on gift card amounts
        if (lstGiftCard.get(i).getAmoutPur() != null && !lstGiftCard.get(i).getAmoutPur().isEmpty()) {
            String amountPur = lstGiftCard.get(i).getAmoutPur();
            String quantity = lstGiftCard.get(i).getgCQuantity();

            if (amountPur != null && !amountPur.trim().isEmpty() && quantity != null && !quantity.trim().isEmpty()) {
                float amount = Float.parseFloat(amountPur.trim());
                float qty = Float.parseFloat(quantity.trim());
                totalPrice = amount * qty;
            }
        }
        // Set the calculated price
        holder.tvPrice.setText("$" + df.format(totalPrice));
    }


    @Override
    public int getItemCount() {
        return lstGiftCard.size();
    }

    public class PaymentCartItemHolder extends RecyclerView.ViewHolder {

        TextView tvItemName, tvQuantity, tvPrice,tvTitleGiftWrap;
        CheckBox checkBox;
        LinearLayout llRootCheckBox;
        View view;

        public PaymentCartItemHolder(@NonNull View itemView) {
            super(itemView);

            checkBox = itemView.findViewById(R.id.cbx_rv_payment_fragment);
            checkBox.setVisibility(View.GONE);
            BSTheme.setCheckBoxColor(checkBox, Color.parseColor(Constant.themeModel.ThemeColor), Color.GRAY);
            llRootCheckBox = itemView.findViewById(R.id.ll_check_box_rv_payment_fragment);

            tvItemName = itemView.findViewById(R.id.tv_item_name_rv_payment_fragment);
            tvTitleGiftWrap = itemView.findViewById(R.id.tv_title_gift_wrap_payment_fragment);
            tvTitleGiftWrap.setVisibility(View.GONE);
            tvQuantity = itemView.findViewById(R.id.tv_item_quantity_rv_payment_fragment);
            tvPrice = itemView.findViewById(R.id.tv_item_price_rv_payment_fragment);
            view = itemView.findViewById(R.id.view_rv_cart_item_payment_fragment);
            view.setVisibility(View.VISIBLE);

        }
    }
}
