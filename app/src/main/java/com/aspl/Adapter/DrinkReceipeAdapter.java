package com.aspl.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.aspl.Utils.Constant;
import com.aspl.Utils.DialogUtils;
import com.aspl.mbs.R;
import com.aspl.mbsmodel.ItemDescModel;

import java.util.List;

public class DrinkReceipeAdapter extends RecyclerView.Adapter<DrinkReceipeAdapter.MyViewHolder> {
    Context context;
    List<ItemDescModel> drinkReceipeList;
    int clickedPosition = -1;

    public DrinkReceipeAdapter(Context context, List<ItemDescModel> drinkReceipeList) {
        this.context = context;
        this.drinkReceipeList = drinkReceipeList;
    }


    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_drink_receipe, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        ItemDescModel itemDescModel = drinkReceipeList.get(position);
        if(itemDescModel != null){
            holder.tvDrinkReceipeName.setText(itemDescModel.getDesc1());
//            holder.checkBox.setText(itemDescModel.getDesc1());
            holder.checkBox.setPadding(15, 0, 0, 0);
            holder.tvDrinkReceipeName.setTag(itemDescModel);
            holder.checkBox.setTag(itemDescModel);
            if(clickedPosition == position){
                holder.checkBox.setChecked(true);
            }else{
                holder.checkBox.setChecked(false);
            }

            holder.checkBox.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    ItemDescModel itemDescModel1 = (ItemDescModel) view.getTag();
                    clickedPosition = holder.getAdapterPosition();
                    DialogUtils.NotifyDrinkReceipe(itemDescModel1);
                }
            });

            holder.tvDrinkReceipeName.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    ItemDescModel itemDescModel1 = (ItemDescModel) view.getTag();
                    clickedPosition = holder.getAdapterPosition();
                    DialogUtils.NotifyDrinkReceipe(itemDescModel1);
                }
            });

//            holder.checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
//                @Override
//                public void onCheckedChanged(CompoundButton checkedbtnview, boolean ischecked) {
//                    if(ischecked){
//                        ItemDescModel itemDescModel1 = (ItemDescModel) checkedbtnview.getTag();
//                        clickedPosition = holder.getAdapterPosition();
//                        DialogUtils.NotifyDrinkReceipe(itemDescModel1);
//                        Log.d("clicked pos","onclick" +clickedPosition);
//                    }
//                }
//            });

        }
    }

    @Override
    public int getItemCount() {
        return drinkReceipeList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements CompoundButton.OnCheckedChangeListener{
        CheckBox checkBox;
        TextView tvDrinkReceipeName;

        public MyViewHolder(View itemView) {
            super(itemView);

            checkBox = itemView.findViewById(R.id.checkbox1);
            tvDrinkReceipeName = itemView.findViewById(R.id.tvDrinkReceipeName);
//            checkBox.setOnCheckedChangeListener(this);
//            checkBox.setOnClickListener(this);
        }

        @Override
        public void onCheckedChanged(CompoundButton checkedbtnview, boolean ischecked) {

//            if(ischecked){
//                ItemDescModel itemDescModel1 = (ItemDescModel) checkedbtnview.getTag();
//                clickedPosition = getAdapterPosition();
//                DialogUtils.NotifyDrinkReceipe(itemDescModel1);
//                Log.d("clicked pos","onclick" +clickedPosition);
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
