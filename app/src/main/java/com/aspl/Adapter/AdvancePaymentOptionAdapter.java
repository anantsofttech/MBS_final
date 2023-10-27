package com.aspl.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.aspl.fragment.DeliveryOptionsFragment;
import com.aspl.mbs.R;

import java.util.ArrayList;

//public class AdvancePaymentOptionAdapter extends BaseAdapter {
//
//    private Context context;
//    private ArrayList<String> paymentOptionsList;
//
//    public AdvancePaymentOptionAdapter(Context context, ArrayList<String> paymentOptionsList) {
//        this.context = context;
//        this.paymentOptionsList = paymentOptionsList;
//    }
//
//
//    @Override
//    public int getCount() {
//        return paymentOptionsList.size();
//    }
//
//    @Override
//    public Object getItem(int position) {
//        return getItem(position);
//    }
//
//    @Override
//    public long getItemId(int position) {
//        return position;
//    }
//
////    @Override
////    public int getCount() {
////        return paymentOptionsList.size();
////    }
////
////    @Override
////    public Object getItem(int position) {
////        return null;
////    }
////
////    @Override
////    public long getItemId(int position) {
////        return 0;
////    }
//
//    @Override
//    public View getView(int position, View convertView, ViewGroup parent) {
//
//        if (convertView == null) {
//
//            LayoutInflater inflater = (LayoutInflater) this.context
//                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
//            // get layout from mobile.xml
//            convertView = inflater.inflate(R.layout.row_advance_payment_options,parent, false);
//        }
//        if(paymentOptionsList.size() > 0){
//
//            String paymentOption = paymentOptionsList.get(position);
//
//            // set image based on selected text
//            TextView textView = (TextView) convertView
//                    .findViewById(R.id.tv_paymentOptions);
//
////            textView.setText(paymentOption);
//
//            if(paymentOption.equalsIgnoreCase("GooglePay")){
//                textView.setText(paymentOption);
//            }else if(paymentOption.equalsIgnoreCase("MasterPass")){
//                textView.setText(paymentOption);
//            }else if(paymentOption.equalsIgnoreCase("paypal")){
//                textView.setText(paymentOption);
//            }else if(paymentOption.equalsIgnoreCase("Including:")){
//                textView.setText(paymentOption);
//            }
//        }
//        return convertView;
//
//    }
//}

public class AdvancePaymentOptionAdapter extends RecyclerView.Adapter<AdvancePaymentOptionAdapter.AdvanceViewHolder> {

    private Context context;
    private ArrayList<String> paymentOptionsList;

    public AdvancePaymentOptionAdapter(Context context, ArrayList<String> paymentOptionsList) {
        this.context = context;
        this.paymentOptionsList = paymentOptionsList;
    }

    @NonNull
    @Override
    public AdvancePaymentOptionAdapter.AdvanceViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_advance_payment_options, parent, false);
        return new AdvanceViewHolder(itemView);

    }

    @Override
    public void onBindViewHolder(@NonNull AdvancePaymentOptionAdapter.AdvanceViewHolder holder, int position) {

        if(paymentOptionsList.size() > 0){

            String paymentOption = paymentOptionsList.get(position).toString();

            if(paymentOption.equalsIgnoreCase("GooglePay")){
                holder.textView.setText(paymentOption);
            }else if(paymentOption.equalsIgnoreCase("MasterPass")){
                holder.textView.setText(paymentOption);
            }else if(paymentOption.equalsIgnoreCase("paypal")){
                holder.textView.setText(paymentOption);
            }else if(paymentOption.equalsIgnoreCase("Including:")){
                holder.textView.setText(paymentOption);
            }
        }
    }

    @Override
    public int getItemCount() {
        return paymentOptionsList.size();
    }


    public class AdvanceViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView textView;

        public AdvanceViewHolder(View itemView) {
            super(itemView);

            textView = (TextView) itemView
                    .findViewById(R.id.tv_paymentOptions);
        }

        @Override
        public void onClick(View view) {

//            FilterItemAdapter_dup.onUnchecked(liFilterSelectedItems.get(getAdapterPosition()).getName());
        }
    }
}