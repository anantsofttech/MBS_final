package com.aspl.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.aspl.Utils.Constant;
import com.aspl.mbs.R;
import com.aspl.mbsmodel.ShoppingCardModel;

import java.util.List;

public class RealTimePriceAdpater extends RecyclerView.Adapter<RealTimePriceAdpater.MyViewHolder> {

        Context context;
        List<ShoppingCardModel> realTimeInvList;



        public RealTimePriceAdpater(Context context, List<ShoppingCardModel> realTimeInvList) {
            this.context = context;
            this.realTimeInvList =  realTimeInvList;
        }

        @NonNull
        @Override
        public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_rv_realtimeprice, parent, false);
            return new MyViewHolder(itemView);
        }

        @Override
        public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
//        Edited by varun for Price change pop-up from set-up

            if(realTimeInvList.get(position)!= null){

//                if (!realTimeInvList.get(position).getPromoPrice().equalsIgnoreCase("0.00") ||
//                        !realTimeInvList.get(position).getPromoPrice().equalsIgnoreCase("")){
//                    if (!Constant.Test.get(position).getPromoPrice().equals(realTimeInvList.get(position).getPromoPrice())){
//                        holder.tv_QtyChange.setText("123456");
//                        holder.tv_itemName.setText(realTimeInvList.get(position).getDesc1());
//                    }else if (realTimeInvList.get(position).getPromoPrice().equalsIgnoreCase("")
//                            || realTimeInvList.get(position).getPromoPrice().isEmpty() || realTimeInvList.get(position).getPromoPrice().equalsIgnoreCase("0.00")){
//                        if (!Constant.Test.get(position).getPrice().equals(realTimeInvList.get(position).getPrice())) {
//                            holder.tv_QtyChange.setText("987654");
//                            holder.tv_itemName.setText(realTimeInvList.get(position).getDesc1());
//                        }
//                    }
//                }
                holder.tv_itemName.setText(realTimeInvList.get(position).getDesc1());
                if (!realTimeInvList.get(position).getPromoPrice().equals(Constant.Test.get(position).getPromoPrice())){
                        holder.tv_QtyChange.setText("Item Price Changed");

                }else if (!realTimeInvList.get(position).getCartPrice().equals(Constant.Test.get(position).getCartPrice())){
                        holder.tv_QtyChange.setText("Item Price Changed");

                    }
            }
        }

        @Override
        public int getItemCount() {

            return  realTimeInvList.size();
        }

        public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

            TextView tv_itemName, tv_QtyChange ,tv_Price;

            public MyViewHolder(View itemView) {
                super(itemView);

                tv_itemName = itemView.findViewById(R.id.tv_itemName);
                tv_QtyChange = itemView.findViewById(R.id.tv_QtyChange);
            }

            @Override
            public void onClick(View view) {
            }
        }


}
