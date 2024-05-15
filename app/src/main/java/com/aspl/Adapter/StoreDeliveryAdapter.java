package com.aspl.Adapter;

import android.content.Context;
import android.graphics.Color;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.aspl.Utils.Constant;
import com.aspl.mbs.R;
import com.aspl.mbsmodel.StoreHour;

import java.util.List;

public class StoreDeliveryAdapter extends RecyclerView.Adapter<StoreDeliveryAdapter.MyViewHolder>{
    List<StoreHour> storeHourList;
    Context context;

    public StoreDeliveryAdapter(Context context,List<StoreHour> storeHourList) {
        this.context = context;
        this.storeHourList = storeHourList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_storedeliveryhour, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        if(storeHourList != null && storeHourList.size()>0) {
            holder.tv_days.setText(storeHourList.get(position).getStoreDay());
            if(storeHourList.get(position).getClosed()){
                holder.tv_time.setText("Closed");
            }else{
                holder.tv_time.setText(storeHourList.get(position).getOpenTime() + "-" +storeHourList.get(position).getCloseTime());
            }
            holder.tv_time.setTextColor(Color.parseColor(Constant.themeModel.ThemeColor));
        }
    }

    @Override
    public int getItemCount() {
        return storeHourList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView tv_days,tv_time;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            tv_days = itemView.findViewById(R.id.tv_days);
            tv_time = itemView.findViewById(R.id.tv_time);
        }
    }
}
