package com.aspl.Adapter;

import android.content.Context;
import android.graphics.Color;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.aspl.Utils.Constant;
import com.aspl.Utils.DialogUtils;
import com.aspl.fragment.ManageAccountFragment;
import com.aspl.fragment.ShippingAddressFragment;
import com.aspl.mbs.MainActivity;
import com.aspl.mbs.MainActivityDup;
import com.aspl.mbs.R;
import com.aspl.mbsmodel.ShippingData;
import java.util.ArrayList;
import java.util.List;

public class ShippingAddressAdapter extends RecyclerView.Adapter<ShippingAddressAdapter.MyViewholder> {
    Context context;
    List<ShippingData> liShippingAddData = new ArrayList<>();

    public ShippingAddressAdapter(Context context, List<ShippingData> liShippingAddData) {
        this.context = context;
        this.liShippingAddData = liShippingAddData;
    }

    @NonNull
    @Override
    public MyViewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_rv_shipping_address, parent, false);
        return new ShippingAddressAdapter.MyViewholder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewholder holder, int position) {

        ShippingData shippingDataModel = liShippingAddData.get(position);

            colorformatting(holder,position);

            if(liShippingAddData != null && liShippingAddData.size() >0){
                String fullname= "";
                String city_state_zip = "";

                if(liShippingAddData.get(position).getFirstName() != null && !liShippingAddData.get(position).getFirstName().trim().isEmpty()){
                    fullname = liShippingAddData.get(position).getFirstName().trim();
                }
                if(liShippingAddData.get(position).getLastName() != null && !liShippingAddData.get(position).getLastName().trim().isEmpty()){
                    fullname = fullname +" " + liShippingAddData.get(position).getLastName().trim();
                }

                holder.tvName.setText(fullname);

                if(liShippingAddData.get(position).getCompanyName() != null && !liShippingAddData.get(position).getCompanyName().trim().isEmpty()){
                    holder.tvCompanyName.setText(liShippingAddData.get(position).getCompanyName().trim());
                }

                if(liShippingAddData.get(position).getPhone() != null && !liShippingAddData.get(position).getPhone().trim().isEmpty()){
                    holder.tvPhonenumber.setText(liShippingAddData.get(position).getPhone().trim());
                }

                if(liShippingAddData.get(position).getAddress1() != null && !liShippingAddData.get(position).getAddress1().trim().isEmpty()){
                    holder.tvAddress1.setText(liShippingAddData.get(position).getAddress1().trim());
                }

                if(liShippingAddData.get(position).getCity() != null && !liShippingAddData.get(position).getCity().trim().isEmpty()){
                    city_state_zip = liShippingAddData.get(position).getCity().trim();

                    if(!city_state_zip.trim().isEmpty()){
                        city_state_zip = city_state_zip + ", ";
                    }
                }

                if(liShippingAddData.get(position).getState() != null && !liShippingAddData.get(position).getState().trim().isEmpty()){
                    city_state_zip = city_state_zip + liShippingAddData.get(position).getState().trim();
                }

                if(liShippingAddData.get(position).getZip() != null && !liShippingAddData.get(position).getZip().trim().isEmpty()){
                    city_state_zip = city_state_zip + " " + liShippingAddData.get(position).getZip().trim();
                }

                if(liShippingAddData.get(position).getShippingId() != null && !liShippingAddData.get(position).getShippingId().toString().trim().isEmpty()){

                    holder.tvShippingId.setText(liShippingAddData.get(position).getShippingId().toString().trim());
                }

                holder.tvcity_state_zip.setText(city_state_zip);

                holder.tvSetasDefault.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        DialogUtils.showCommonConfirmation(context,"FromSetasDefault",shippingDataModel);

                    }
                });

                holder.tvDelete.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        if(position == 0){
                            DialogUtils.showCommonConfirmation(context,"FromDeletionShippingAddress",null);
                        }else{
                            DialogUtils.showCommonConfirmation(context,"FromDeletionConfirmation",shippingDataModel);
                        }
                    }
                });

                holder.tvEdit.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

//                       DialogUtils.showEditShippingaddress(context);

                        if(Constant.SCREEN_LAYOUT == 1){
                            MainActivity.getInstance().loadEditShippingFragmentDialog(String.valueOf(liShippingAddData.get(position).getShippingId()));
                        }else if(Constant.SCREEN_LAYOUT == 2){
                            MainActivityDup.getInstance().loadEditShippingFragmentDialog(String.valueOf(liShippingAddData.get(position).getShippingId()));
                        }
                    }
                });
        }
    }

    private void colorformatting(MyViewholder holder, int position) {

        if(position == 0){
            holder.ll_main.setBackgroundColor(Color.parseColor(Constant.themeModel.ThemeColor));
            holder.tvName.setTextColor(context.getResources().getColor(R.color.androidWhite));
            holder.tvCompanyName.setTextColor(context.getResources().getColor(R.color.androidWhite));
            holder.tvPhonenumber.setTextColor(context.getResources().getColor(R.color.androidWhite));
            holder.tvcity_state_zip.setTextColor(context.getResources().getColor(R.color.androidWhite));
            holder.tvAddress1.setTextColor(context.getResources().getColor(R.color.androidWhite));
            holder.tvShippingId.setTextColor(context.getResources().getColor(R.color.androidWhite));
            holder.tvSetasDefault.setVisibility(View.GONE);
        }else{
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                holder.ll_main.setBackgroundColor(context.getColor(R.color.androidWhite));
            }
            holder.tvName.setTextColor(context.getResources().getColor(R.color.black));
            holder.tvCompanyName.setTextColor(context.getResources().getColor(R.color.black));
            holder.tvPhonenumber.setTextColor(context.getResources().getColor(R.color.black));
            holder.tvcity_state_zip.setTextColor(context.getResources().getColor(R.color.black));
            holder.tvAddress1.setTextColor(context.getResources().getColor(R.color.black));
            holder.tvShippingId.setTextColor(context.getResources().getColor(R.color.black));
            holder.tvSetasDefault.setVisibility(View.VISIBLE);
        }

//        holder.llmain_container.setBackgroundColor(Color.parseColor(Constant.themeModel.Backgroundcolor));
        holder.llmain_container.setBackgroundColor(context.getResources().getColor(R.color.transparent));
        holder.tvEdit.setTextColor(Color.parseColor(Constant.themeModel.ThemeColor));
        holder.tvDelete.setTextColor(Color.parseColor(Constant.themeModel.ThemeColor));
        holder.tvSetasDefault.setTextColor(Color.parseColor(Constant.themeModel.ThemeColor));
    }

    @Override
    public int getItemCount() {
        return liShippingAddData.size();
    }

    public static class MyViewholder extends RecyclerView.ViewHolder {

        TextView tvEdit,tvDelete,tvSetasDefault;
        TextView tvName,tvCompanyName,tvPhonenumber,tvAddress1,tvcity_state_zip,tvShippingId;
        LinearLayout ll_main,llmain_container;

        public MyViewholder(@NonNull View itemView) {
            super(itemView);

            tvName = (TextView) itemView.findViewById(R.id.tvName);
            tvShippingId = (TextView) itemView.findViewById(R.id.tvShippingId);
            tvCompanyName = (TextView) itemView.findViewById(R.id.tvCompanyName);
            tvPhonenumber = (TextView) itemView.findViewById(R.id.tvPhonenumber);
            tvAddress1 = (TextView) itemView.findViewById(R.id.tvAddress1);
            tvcity_state_zip = (TextView) itemView.findViewById(R.id.tvcity_state_zip);
            tvEdit = (TextView) itemView.findViewById(R.id.tvEdit);
            tvDelete = (TextView) itemView.findViewById(R.id.tvDelete);
            tvSetasDefault = (TextView) itemView.findViewById(R.id.tvSetasDefault);
            ll_main = (LinearLayout) itemView.findViewById(R.id.ll_main);
            llmain_container = (LinearLayout) itemView.findViewById(R.id.llmain_container);
        }
    }
}
