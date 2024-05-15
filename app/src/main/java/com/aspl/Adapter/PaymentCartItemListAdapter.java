package com.aspl.Adapter;

import android.content.Context;
import android.graphics.Color;

import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.aspl.Utils.BSTheme;
import com.aspl.Utils.Constant;
import com.aspl.mbs.MainActivity;
import com.aspl.mbs.MainActivityDup;
import com.aspl.mbs.R;
import com.aspl.mbsmodel.ShoppingCardModel;

import java.text.DecimalFormat;
import java.util.List;

/**
 * Created by mic on 1/2/2018.
 */

public class PaymentCartItemListAdapter extends RecyclerView.Adapter<PaymentCartItemListAdapter.PaymentCartItemHolder> {

    //List<TestModel> testModels;
    List<ShoppingCardModel> liShoppingCart;
    //private String imgUrl = Constant.URL + Constant.IMG_BASE_URL + Constant.STOREID + "/";
    //private String imgNoImageUrl = Constant.URL + Constant.IMG_NO_IMAGE + "/";
    //http://192.168.172.211:888/WebStoreImages/NoImage/

    Context context;
    PaymentCartItemListAdapterEvent paymentCartItemListAdapterEvent;
    public interface PaymentCartItemListAdapterEvent {
        void onGiftWrapChecked(int position, List<ShoppingCardModel> liShoppingCart);
    }

    public PaymentCartItemListAdapter(Context context, PaymentCartItemListAdapterEvent paymentCartItemListAdapterEvent, /*List<TestModel> testModelList*/List<ShoppingCardModel> liShoppingCat) {
        this.context = context;
        this.paymentCartItemListAdapterEvent = paymentCartItemListAdapterEvent;
        /*this.testModels = testModelList;*/
        this.liShoppingCart = liShoppingCat;
    }

    @Override
    public PaymentCartItemHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_rv_item_list_payment_fragment, parent, false);
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
        if (!liShoppingCart.get(i).getGiftWrap()){
            holder.checkBox.setVisibility(View.GONE);
            holder.llRootCheckBox.setVisibility(View.GONE);
            holder.tvItemName.setLayoutParams(param);
        }
        holder.tvItemName.setText(liShoppingCart.get(i).getDesc1());
        holder.tvQuantity.setText(liShoppingCart.get(i).getQty());
        Float _total;

        Constant.Test = liShoppingCart;
        if (Float.parseFloat(liShoppingCart.get(i).getPromoPrice()) > 0){
             _total = Float.parseFloat(liShoppingCart.get(i).getPromoPrice()) * Float.parseFloat(liShoppingCart.get(i).getQty());
        }else {
            _total = Float.parseFloat(liShoppingCart.get(i).getCartPrice()) * Float.parseFloat(liShoppingCart.get(i).getQty());
        }
        holder.tvPrice.setText("$" +df.format(_total));
    }

    @Override
    public int getItemCount() {
        return liShoppingCart.size();
    }

    public class PaymentCartItemHolder extends RecyclerView.ViewHolder implements CompoundButton.OnCheckedChangeListener {
        TextView tvItemName, tvQuantity, tvPrice,tvTitleGiftWrap;
        CheckBox checkBox;
        LinearLayout llRootCheckBox;
        View view;

        public PaymentCartItemHolder(View itemView) {
            super(itemView);

            checkBox = itemView.findViewById(R.id.cbx_rv_payment_fragment);
            checkBox.setVisibility(View.VISIBLE);
            BSTheme.setCheckBoxColor(checkBox, Color.parseColor(Constant.themeModel.ThemeColor), Color.GRAY);
            checkBox.setOnCheckedChangeListener(this);
            llRootCheckBox = itemView.findViewById(R.id.ll_check_box_rv_payment_fragment);

            tvItemName = itemView.findViewById(R.id.tv_item_name_rv_payment_fragment);
            tvTitleGiftWrap = itemView.findViewById(R.id.tv_title_gift_wrap_payment_fragment);
            tvTitleGiftWrap.setVisibility(View.GONE);
            tvQuantity = itemView.findViewById(R.id.tv_item_quantity_rv_payment_fragment);
            tvPrice = itemView.findViewById(R.id.tv_item_price_rv_payment_fragment);
            view = itemView.findViewById(R.id.view_rv_cart_item_payment_fragment);
            view.setVisibility(View.VISIBLE);
        }

        @Override
        public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
            if (checkBox.isChecked()){
                if (paymentCartItemListAdapterEvent!=null){
                    paymentCartItemListAdapterEvent.onGiftWrapChecked(getAdapterPosition(),liShoppingCart);
                }
            }
        }
    }
}
