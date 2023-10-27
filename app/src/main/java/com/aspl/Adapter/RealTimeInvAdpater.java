package com.aspl.Adapter;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.aspl.Utils.Constant;
import com.aspl.mbs.R;
import com.aspl.mbsmodel.ShoppingCardModel;
import java.util.List;
public class RealTimeInvAdpater extends RecyclerView.Adapter<RealTimeInvAdpater.MyViewHolder> {
    Context context;
    List<ShoppingCardModel> realTimeInvList;
    String validate_instruct;
    int x,y;
    public RealTimeInvAdpater(Context context, List<ShoppingCardModel> realTimeInvList, String validate_instruct) {
        this.context = context;
        this.realTimeInvList =  realTimeInvList;
        this.validate_instruct = validate_instruct;
    }
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_rv_realtime, parent, false);
        return new MyViewHolder(itemView);
    }
    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        if(realTimeInvList.get(position)!= null){
            holder.tv_itemName.setText(realTimeInvList.get(position).getDesc1());
            if (validate_instruct.equals("Real time inventory")) {
                if (Integer.parseInt(realTimeInvList.get(position).getQtyOnHand()) > 0) {
//                    Edited by Varun for Multi pack real time qty issue
                    if (realTimeInvList.get(position).getInvType().equals("M")){
                        x = Integer.parseInt(realTimeInvList.get(position).getQtyOnHand()) / Integer.parseInt((String) realTimeInvList.get(position).getOunces());
                    }else {
                        x = Integer.parseInt(realTimeInvList.get(position).getQtyOnHand());
                    }
//                    END
                     y = Integer.parseInt(realTimeInvList.get(position).getQty());
                    if (y>x) {
                        Log.e("", "onBindViewHolder: 12345 " + x );
//                        holder.tv_QtyChange.setText("Qty " + realTimeInvList.get(position).getQty() + " To " + realTimeInvList.get(position).getQtyOnHand());
                        holder.tv_QtyChange.setText("Qty " + y + " To " + x);
                    }else{
                        holder.tv_QtyChange.setVisibility(View.GONE);
                    }
                    if (Constant.isCHECKOUT){
                        if (!realTimeInvList.get(position).getPromoPrice().equals(Constant.Test.get(position).getPromoPrice())){
                            holder.tv_PriceChange.setVisibility(View.VISIBLE);
                            Log.e("", "onBindViewHolder: 1" );
                        }else if (!realTimeInvList.get(position).getCartPrice().equals(Constant.Test.get(position).getCartPrice())){
//                        holder.tv_QtyChange.setText("Item Price Changed");
                            holder.tv_PriceChange.setVisibility(View.VISIBLE);
                            Log.e("", "onBindViewHolder: 2" );
                        }
                    }else{
                        holder.tv_PriceChange.setVisibility(View.GONE);
                        Log.e("", "onBindViewHolder: 33" );
                    }
                } else if (Integer.parseInt(realTimeInvList.get(position).getQtyOnHand()) <= 0) {
//
                    holder.tv_QtyChange.setText(Html.fromHtml(context.getResources().getString(R.string.NoLonger)));
                    holder.tv_QtyChange.setGravity(Gravity.CENTER);
                    if (Constant.isCHECKOUT) {
                        if (!realTimeInvList.get(position).getPromoPrice().equals(Constant.Test.get(position).getPromoPrice())) {
                            holder.tv_PriceChange.setVisibility(View.VISIBLE);
                            Log.e("", "onBindViewHolder: 3" );
                            Constant.isCHECKOUT=false;
//                        holder.tv_QtyChange.setVisibility(View.GONE);
                        } else if (!realTimeInvList.get(position).getCartPrice().equals(Constant.Test.get(position).getCartPrice())) {
//                            holder.tv_QtyChange.setText("Item Price Changed");
                            holder.tv_PriceChange.setVisibility(View.VISIBLE);
                            Log.e("", "onBindViewHolder: 4" );
                            Constant.isCHECKOUT=false;
                        }
                    }else{
                        holder.tv_PriceChange.setVisibility(View.GONE);
                        Log.e("", "onBindViewHolder: 34" );
                    }
                }
            }
            if (validate_instruct.equals("Price")){
                if (!realTimeInvList.get(position).getPromoPrice().equals(Constant.Test.get(position).getPromoPrice())){
                    holder.tv_PriceChange.setVisibility(View.VISIBLE);
                    Log.e("", "onBindViewHolder: 5" );
                    holder.tv_QtyChange.setVisibility(View.GONE);
                }else if (!realTimeInvList.get(position).getCartPrice().equals(Constant.Test.get(position).getCartPrice())){
//                    holder.tv_QtyChange.setText("Item Price Changed");
                    holder.tv_PriceChange.setVisibility(View.VISIBLE);
                    Log.e("", "onBindViewHolder: 6" );
                    holder.tv_QtyChange.setVisibility(View.GONE);
                }
            }
        }
    }
    @Override
    public int getItemCount() {
        return  realTimeInvList.size();
    }
    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView tv_itemName, tv_QtyChange , tv_PriceChange;
        public MyViewHolder(View itemView) {
            super(itemView);
            tv_itemName = itemView.findViewById(R.id.tv_itemName);
            tv_QtyChange = itemView.findViewById(R.id.tv_QtyChange);
            tv_PriceChange = itemView.findViewById(R.id.tv_PriceChange);
        }
        @Override
        public void onClick(View view) {
        }
    }
}
