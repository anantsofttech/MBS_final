package com.aspl.Adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.aspl.Utils.Constant;
import com.aspl.fragment.OrderHistoryFragment;
import com.aspl.mbs.R;
import com.aspl.mbsmodel.LightningOrderModel;
import com.aspl.mbsmodel.OrderSummary;
import com.aspl.mbsmodel.ReOrderItemModel;
import com.aspl.task.TaskGetOrderSummary;

import java.text.DecimalFormat;
import java.util.List;

public class OnlinePurchaseAdapter extends RecyclerView.Adapter<OnlinePurchaseAdapter.MyViewHolder> {
    List<ReOrderItemModel> onlinePurchaseOrderList;
    List<LightningOrderModel> lightningOrderList;
    Context context;
    String fromwhere = "";

    public OnlinePurchaseAdapter(Context context, List<ReOrderItemModel> onlinePurchaseOrderList) {
        this.context = context;
        this.onlinePurchaseOrderList = onlinePurchaseOrderList;
    }

    public OnlinePurchaseAdapter(Context context, List<LightningOrderModel> lightningOrderList, String fromwhere) {
        this.context = context;
        this.lightningOrderList = lightningOrderList;
        this.fromwhere = fromwhere;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_online_purchase, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, @SuppressLint("RecyclerView") int position) {

        if (!fromwhere.isEmpty() && fromwhere.equalsIgnoreCase("InstorePurchase")) {

            if (lightningOrderList != null && lightningOrderList.size() > 0) {

                holder.llstatusOnlinePurchase.setVisibility(View.GONE);
                holder.llstatusInstore.setVisibility(View.VISIBLE);

                if (lightningOrderList.get(position).getStatus().equalsIgnoreCase("open")) {
                    holder.tv_statusvalueInstore.setText(lightningOrderList.get(position).getStatus());
                    holder.tv_statusvalueInstore.setTextColor(context.getResources().getColor(R.color.green));

                } else if (lightningOrderList.get(position).getStatus().equalsIgnoreCase("cancelled")) {
                    holder.tv_statusvalueInstore.setText(lightningOrderList.get(position).getStatus());
                    holder.tv_statusvalueInstore.setTextColor(context.getResources().getColor(R.color.red));
                } else {
                    holder.tv_statusvalueInstore.setText(lightningOrderList.get(position).getStatus());
                    holder.tv_statusvalueInstore.setTextColor(context.getResources().getColor(R.color.blue_search));
                }

                holder.tv_statusvalueInstore.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        if (lightningOrderList.get(position).getInvoiceNo() != null && !lightningOrderList.get(position).getInvoiceNo().isEmpty())
                            OrderHistoryFragment.getInstance().callInstorePurchaseDetail(lightningOrderList.get(position).getInvoiceNo(),position);
                    }
                });

                holder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        if (lightningOrderList.get(position).getInvoiceNo() != null && !lightningOrderList.get(position).getInvoiceNo().isEmpty())
                            OrderHistoryFragment.getInstance().callInstorePurchaseDetail(lightningOrderList.get(position).getInvoiceNo(),position);
                    }
                });

                holder.tv_DateVal.setText(lightningOrderList.get(position).getOrderDate());
                holder.tv_weborder.setText("Invoice#");
                holder.tv_WebOrderVal.setText(lightningOrderList.get(position).getInvoiceNo());
                DecimalFormat df = new DecimalFormat("####0.00");
                df.setMaximumFractionDigits(2);
                Float f = Float.valueOf(lightningOrderList.get(position).getOrderTotal());
                holder.tv_amountVal.setText("$" + String.valueOf(df.format(f)));

            }
        }
        else {

            if (onlinePurchaseOrderList != null && onlinePurchaseOrderList.size() > 0) {

                holder.llstatusOnlinePurchase.setVisibility(View.VISIBLE);
                holder.llstatusInstore.setVisibility(View.GONE);

                if (onlinePurchaseOrderList.get(position).getOrderStatus().equalsIgnoreCase("open")) {
                    holder.tv_statusVal.setText(onlinePurchaseOrderList.get(position).getOrderStatus());
                    holder.tv_statusVal.setTextColor(context.getResources().getColor(R.color.green));

                } else if (onlinePurchaseOrderList.get(position).getOrderStatus().equalsIgnoreCase("cancelled")) {
                    holder.tv_statusVal.setText(onlinePurchaseOrderList.get(position).getOrderStatus());
                    holder.tv_statusVal.setTextColor(context.getResources().getColor(R.color.red));
                } else {
                    holder.tv_statusVal.setText(onlinePurchaseOrderList.get(position).getOrderStatus());
                    holder.tv_statusVal.setTextColor(context.getResources().getColor(R.color.blue_search));
                }

                holder.tv_statusVal.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if (onlinePurchaseOrderList.get(position).getID() != null && !onlinePurchaseOrderList.get(position).getID().isEmpty())
                            OrderHistoryFragment.getInstance().callOrderSummaryResultWebService(onlinePurchaseOrderList.get(position).getID(),"");
                    }
                });

                holder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        if (onlinePurchaseOrderList.get(position).getID() != null && !onlinePurchaseOrderList.get(position).getID().isEmpty())
                            OrderHistoryFragment.getInstance().callOrderSummaryResultWebService(onlinePurchaseOrderList.get(position).getID(),"");
                    }
                });

                holder.tv_DateVal.setText(onlinePurchaseOrderList.get(position).getOrderDate());
                holder.tv_weborder.setText("Web Order#");
                holder.tv_WebOrderVal.setText(onlinePurchaseOrderList.get(position).getOrderID());
                holder.tv_amountVal.setText("$" + onlinePurchaseOrderList.get(position).getOrderTotal());
            }
        }
    }

    @Override
    public int getItemCount() {

        if(!fromwhere.isEmpty() && fromwhere.equalsIgnoreCase("InstorePurchase")) {
            return lightningOrderList.size();
        }else{
            return onlinePurchaseOrderList.size();
        }
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        LinearLayout llstatusInstore,llstatusOnlinePurchase;
        TextView tv_statusVal, tv_DateVal, tv_WebOrderVal, tv_amountVal,
                tv_weborder,tv_statusvalueInstore;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            tv_statusVal = itemView.findViewById(R.id.tv_statusvalue);
            tv_DateVal = itemView.findViewById(R.id.tv_Datevalue);
            tv_weborder = itemView.findViewById(R.id.tv_weborder);
            tv_WebOrderVal = itemView.findViewById(R.id.tv_webordervalue);
            tv_amountVal = itemView.findViewById(R.id.tv_Amtvalue);
            tv_statusvalueInstore = itemView.findViewById(R.id.tv_statusvalueInstore);
            llstatusOnlinePurchase = itemView.findViewById(R.id.llstatusOnlinePurchase);
            llstatusInstore = itemView.findViewById(R.id.llstatusInstore);
        }
    }

}
