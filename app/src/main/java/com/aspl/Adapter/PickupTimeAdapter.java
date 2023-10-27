package com.aspl.Adapter;

import android.content.Context;
import android.graphics.Color;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.aspl.Utils.BSTheme;
import com.aspl.Utils.Constant;
import com.aspl.Utils.DialogUtils;
import com.aspl.mbs.R;
import com.aspl.mbsmodel.ItemDescModel;
import com.aspl.mbsmodel.PickupModel;

import java.util.ArrayList;

public class PickupTimeAdapter extends RecyclerView.Adapter<PickupTimeAdapter.MyViewHolder> {

    Context context;
    ArrayList<PickupModel> storeTimeList;
    String tomorrow;
    int clickedPosition = -1;

    public PickupTimeAdapter(Context context, ArrayList<PickupModel> storeTimeList, String tomorrow) {
        this.context = context;
        this.storeTimeList = storeTimeList;
        this.tomorrow = tomorrow;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_pickup_time, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        PickupModel pickupModel = storeTimeList.get(position);

        if(clickedPosition == -1 && position == 0 && !storeTimeList.get(0).getTime().equals("Closed")){
            DialogUtils.selectedPickupModel = pickupModel;
            clickedPosition = 0;
        }

        if(clickedPosition == -1 && position == 1 && storeTimeList.get(0).getTime().equals("Closed") && !storeTimeList.get(1).getTime().equals("Closed")){
                DialogUtils.selectedPickupModel = pickupModel;
                clickedPosition = 1;
        }

        if(storeTimeList.get(position).getDay().equals(tomorrow)){

            if(storeTimeList.get(position).getTime().equals("Closed")){
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    holder.tvDay.setTextColor(context.getColor(R.color.dark_gray_medium));
                    holder.tvStoreTime.setTextColor(context.getColor(R.color.dark_gray_medium));
                    holder.checkBox.setEnabled(false);
                    holder.itemView.setEnabled(false);
                }
            }else{
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    holder.tvDay.setTextColor(context.getColor(R.color.green));
                    holder.tvStoreTime.setTextColor(context.getColor(R.color.green));
                }
            }
        }else{

            //Today
            if(storeTimeList.get(position).getTime().equals("Closed")){
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    holder.tvDay.setTextColor(context.getColor(R.color.dark_gray_medium));
                    holder.tvStoreTime.setTextColor(context.getColor(R.color.dark_gray_medium));
                    holder.checkBox.setEnabled(false);
                    holder.itemView.setEnabled(false);
                }
            }else{
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    holder.tvDay.setTextColor(context.getColor(R.color.black));
                    holder.tvStoreTime.setTextColor(context.getColor(R.color.black));
                    holder.checkBox.setEnabled(true);
                    holder.itemView.setEnabled(true);
                }
            }
        }

        holder.tvStoreTime.setText(storeTimeList.get(position).getTime());
        holder.tvDay.setText(storeTimeList.get(position).getDay());

//        if(holder.checkBox.isEnabled() && position == 0){
//            holder.checkBox.setChecked(true);
//            clickedPosition = 0;
//        }

        holder.checkBox.setTag(pickupModel);

        if(clickedPosition == position){
            holder.checkBox.setChecked(true);
        }else{
            holder.checkBox.setChecked(false);
        }

        holder.checkBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                DialogUtils.selectedPickupModel = (PickupModel) view.getTag();
                clickedPosition = holder.getAdapterPosition();
                notifyDataSetChanged();

//                if(clickedPosition != previous_pos){
//                    holder.checkBox.setChecked(false);
//                }else{
//                    holder.checkBox.setChecked(true);
//                }
            }
        });


//        if(!DialogUtils.isMoreitemClicked){
//            if(position < 10){
//                holder.llmain.setVisibility(View.VISIBLE);
//            }else{
//                holder.llmain.setVisibility(View.GONE);
//            }
//        }else if(DialogUtils.isMoreitemClicked){
//            holder.llmain.setVisibility(View.VISIBLE);
//        }

//        ItemDescModel itemDescModel = storeTimeList.get(position);
//        if(itemDescModel != null){
//            holder.tvDrinkReceipeName.setText(itemDescModel.getDesc1());
////            holder.checkBox.setText(itemDescModel.getDesc1());
//            holder.checkBox.setPadding(15, 0, 0, 0);
//            holder.tvDrinkReceipeName.setTag(itemDescModel);
//            holder.checkBox.setTag(itemDescModel);
//            if(clickedPosition == position){
//                holder.checkBox.setChecked(true);
//            }else{
//                holder.checkBox.setChecked(false);
//            }

//            holder.checkBox.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View view) {
//
//                    ItemDescModel itemDescModel1 = (ItemDescModel) view.getTag();
//                    clickedPosition = holder.getAdapterPosition();
//                    DialogUtils.NotifyDrinkReceipe(itemDescModel1);
//                }
//            });
//
//            holder.tvDrinkReceipeName.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View view) {
//                    ItemDescModel itemDescModel1 = (ItemDescModel) view.getTag();
//                    clickedPosition = holder.getAdapterPosition();
//                    DialogUtils.NotifyDrinkReceipe(itemDescModel1);
//                }
//            });

//
//        }
    }

    @Override
    public int getItemCount() {
        return storeTimeList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements CompoundButton.OnCheckedChangeListener{
        CheckBox checkBox;
        TextView tvDay,tvStoreTime;
        LinearLayout llmain;

        public MyViewHolder(View itemView) {
            super(itemView);

            checkBox = itemView.findViewById(R.id.checkbox1);
            BSTheme.setCheckBoxColor(checkBox, Color.parseColor(Constant.themeModel.ThemeColor), Color.GRAY);

            tvDay = itemView.findViewById(R.id.tvDay);
            tvStoreTime = itemView.findViewById(R.id.tvStoreTime);
            llmain = itemView.findViewById(R.id.llmain);
//            checkBox.setOnCheckedChangeListener(this);
//            checkBox.setOnClickListener(this);
        }

        @Override
        public void onCheckedChanged(CompoundButton checkedbtnview, boolean ischecked) {

//            if(ischecked){
//                previous_pos = getAdapterPosition();
//            }

        }

//        @Override
//        public void onClick(View view) {
//            ItemDescModel itemDescModel1 = (ItemDescModel) view.getTag();
//            clickedPosition = getAdapterPosition();
//            DialogUtils.NotifyDrinkReceipe(itemDescModel1);
//            Log.d("clicked pos","onclick" +clickedPosition);
//        }
    }
}
