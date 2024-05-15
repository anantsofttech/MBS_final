package com.aspl.Adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Build;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.aspl.Utils.Constant;
import com.aspl.Utils.Utils;
import com.aspl.mbs.R;
import com.aspl.mbsmodel.ItemDescModel;
import com.aspl.mbsmodel.lstInventoryModel;

import java.util.List;

import static com.bumptech.glide.gifdecoder.GifHeaderParser.TAG;

public class MultiPackAdapter extends RecyclerView.Adapter<MultiPackAdapter.MyViewHolder>  {

    Context context;
    ItemDescModel itemDescModel;
    MultiPackAdapterEvent multiPackAdapterEvent;
    CompoundButton lastcheckedCheckbox = null;
    int clickedPosition = -1;

    public interface MultiPackAdapterEvent {
        void onMultipackcheck(int position, List<lstInventoryModel> lstInventoryModel);
    }

    public MultiPackAdapter(Context context, ItemDescModel itemDescModel) {
        this.context=context;
        this.itemDescModel=itemDescModel;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View itemView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.custom_multi_pack, viewGroup, false);
        return new MultiPackAdapter.MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, @SuppressLint("RecyclerView") int i) {

        initializeValues(myViewHolder,i);

//        Edited by Varun for check box color changes to theme color when we select the check box
        // Set the custom color as the checkbox color using ColorStateList
        int[] colors = new int[]{Color.parseColor(Constant.themeModel.ThemeColor), Color.parseColor("#000000")}; // Checked and unchecked colors
        int[][] states = new int[][]{{android.R.attr.state_checked}, {}};
        ColorStateList colorStateList = new ColorStateList(states, colors);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            myViewHolder.checkBox.setButtonTintList(colorStateList);
        }
//             END
    }

    private void initializeValues(MyViewHolder myViewHolder, int i) {

        myViewHolder.checkBox.setText(itemDescModel.getLstInventoryModel().get(i).getExtendDesc());

        if ( !itemDescModel.getLstInventoryModel().get(i).getPrice().isEmpty() && itemDescModel.getLstInventoryModel().get(i).getPrice() != null ) {
            myViewHolder.tvPrice.setText(itemDescModel.getLstInventoryModel().get(i).getPrice());
        }

        if (itemDescModel.getLstInventoryModel().get(i).getPromoPrice() != null && !itemDescModel.getLstInventoryModel().get(i).getPromoPrice().isEmpty() && !itemDescModel.getLstInventoryModel().get(i).getPromoPrice().equalsIgnoreCase("0.00")) {
            myViewHolder.tvPromoPrice.setVisibility(View.VISIBLE);
            myViewHolder.tvPromoPrice.setText(itemDescModel.getLstInventoryModel().get(i).getPromoPrice());

            myViewHolder.tvPrice.setPaintFlags(myViewHolder.tvPrice.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
            myViewHolder.tvPrice.setTextColor(Color.GRAY);

        } else {
            myViewHolder.tvPromoPrice.setVisibility(View.GONE);
            myViewHolder.tvPromoPrice.setVisibility(View.GONE);
            myViewHolder.tvPrice.setTextColor(Color.BLACK);
            myViewHolder.tvPrice.setPaintFlags(myViewHolder.tvPrice.getPaintFlags() & (~Paint.STRIKE_THRU_TEXT_FLAG));
            myViewHolder.tvPrice.setGravity(Gravity.CENTER);
        }

//         for discount name pop -up .
        if (itemDescModel.getLstInventoryModel().get(i).getGrpcomment() != null && !itemDescModel.getLstInventoryModel().get(i).getGrpcomment().isEmpty()) {
            myViewHolder.tvDiscountName.setVisibility(View.VISIBLE);
            myViewHolder.tvDiscountName.setText("         " + itemDescModel.getLstInventoryModel().get(i).getGrpcomment());
        } else {
            if (itemDescModel.getLstInventoryModel().get(i).getDiscountName() != null && !itemDescModel.getLstInventoryModel().get(i).getDiscountName().isEmpty()) {
                myViewHolder.tvDiscountName.setVisibility(View.VISIBLE);
                myViewHolder.tvDiscountName.setText("         " + itemDescModel.getLstInventoryModel().get(i).getDiscountName());
            }else{
                myViewHolder.tvDiscountName.setVisibility(View.GONE);
            }
        }

        myViewHolder.tvDiscountName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(itemDescModel.getLstInventoryModel().get(i).getGrpMemo()!= null && !itemDescModel.getLstInventoryModel().get(i).getGrpMemo().equals("null") && !itemDescModel.getLstInventoryModel().get(i).getGrpMemo().isEmpty()){
                    Utils.showDiscountgroupDialog(context,itemDescModel.getLstInventoryModel().get(i).getDesc1(),itemDescModel.getLstInventoryModel().get(i).getGrpMemo(), "", null);
                }

            }
        });

        myViewHolder.checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                if (lastcheckedCheckbox != null && lastcheckedCheckbox.getId() != -1) {

                    if(buttonView != lastcheckedCheckbox){
                        lastcheckedCheckbox.setChecked(false);
                    }

//                    Toast.makeText(context, ""+itemDescModel.getLstInventoryModel().get(clickedPosition).getItemMstId(), Toast.LENGTH_SHORT).show();
//                    Toast.makeText(context, ""+Constant.Test_SKU, Toast.LENGTH_SHORT).show();
                }

                if (isChecked){
                    clickedPosition = myViewHolder.getAdapterPosition();
                    Constant.check_multi_position= clickedPosition;
                    Constant.Test_SKU = itemDescModel.getLstInventoryModel().get(clickedPosition).getItemMstId();
                    Constant.Test_Ounces = (int) itemDescModel.getLstInventoryModel().get(clickedPosition).getOunces();
                }else{
                    Constant.Test_SKU = "";
                    Constant.Test_Ounces = 0;
                }
                Log.e(TAG, "onCheckedChanged: sku-"+Constant.Test_SKU );
                Log.e(TAG, "onCheckedChanged: sku-"+Constant.Test_Ounces );
                lastcheckedCheckbox = buttonView;
            }
        });

        myViewHolder.ll_checkbox_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(myViewHolder.checkBox.isChecked()){
                    myViewHolder.checkBox.setChecked(false);
                }else{
                    myViewHolder.checkBox.setChecked(true);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return itemDescModel.getLstInventoryModel().size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements com.aspl.Adapter.MyViewHolder {
        CheckBox checkBox;
        TextView tvPrice, tvPromoPrice , tvDiscountName;
        LinearLayout ll_checkbox_layout;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            Log.e(TAG, "MyViewHolder: 11" + itemDescModel.getLstInventoryModel().size());
            checkBox = itemView.findViewById(R.id.cbx_multi_pack);
            tvPrice = itemView.findViewById(R.id.tvPrice);
            tvPromoPrice = itemView.findViewById(R.id.tvPromoPrice);
            tvDiscountName = itemView.findViewById(R.id.tvDiscountName);
            ll_checkbox_layout = itemView.findViewById(R.id.ll_checkbox_layout);
        }
    }
}