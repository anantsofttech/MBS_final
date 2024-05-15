package com.aspl.Adapter;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.aspl.Utils.Constant;
import com.aspl.mbs.MainActivity;
import com.aspl.mbs.MainActivityDup;
import com.aspl.mbs.R;
import com.aspl.mbsmodel.DataFrontModel;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import java.util.List;

public class DiscountBlockAdapter extends RecyclerView.Adapter<DiscountBlockAdapter.ViewHolder> {

    private Context context;
    private List<DataFrontModel> data;
    private String imgUrl = Constant.IMG_BASE + "/WebStoreImages/BlockImages/" + Constant.STOREID + "/";

    public DiscountBlockAdapter(Context context, List<DataFrontModel> data) {
        this.context = context;
        this.data = data;
    }

    @NonNull
    @Override
    public DiscountBlockAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.custom_block, viewGroup, false);
        return new ViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        DataFrontModel item = data.get(position);

        if (item.getImage()!=""){
            Glide.with(context).load(imgUrl + item.getImage())
                    .diskCacheStrategy(DiskCacheStrategy.NONE)
                    .skipMemoryCache(true).into(holder.img1);
        }else{
            holder.block1.setBackgroundColor(Color.parseColor(Constant.themeModel.ThemeColor));
        }

        if (item.getBlockDisplaytext()!= null && !item.getBlockDisplaytext().isEmpty()){
            holder.textoffername1.setText(item.getBlockDisplaytext());
            holder.textoffername1.setVisibility(View.VISIBLE);
        }else{
            holder.textoffername1.setVisibility(View.GONE);
        }

        if (item.getBlockDescription()!= null && !item.getBlockDescription().isEmpty()){
            holder. txtofferdesc1.setText(item.getBlockDescription());
            holder.txtofferdesc1.setVisibility(View.VISIBLE);
        }else{
            holder.txtofferdesc1.setVisibility(View.GONE);
        }

        if (item.getFontStyle()!=null &&!item.getFontStyle().isEmpty()){
            if (item.getFontStyle().equalsIgnoreCase("Arial")) {
                Typeface typeface = context.getResources().getFont(R.font.arial);
                holder.textoffername1.setTypeface(typeface);
                holder.txtofferdesc1.setTypeface(typeface);
                holder.txtshop1.setTypeface(typeface);
            }else if (item.getFontStyle().equalsIgnoreCase("Verdana")){
                Typeface typeface = context.getResources().getFont(R.font.verdana);
                holder.textoffername1.setTypeface(typeface);
                holder.txtofferdesc1.setTypeface(typeface);
                holder.txtshop1.setTypeface(typeface);
            }else if (item.getFontStyle().equalsIgnoreCase("Calibri")){
                Typeface typeface = context.getResources().getFont(R.font.calibri);
                holder.textoffername1.setTypeface(typeface);
                holder.txtofferdesc1.setTypeface(typeface);
                holder.txtshop1.setTypeface(typeface);
            }else if (item.getFontStyle().equalsIgnoreCase("Open Sans")){
                Typeface typeface = context.getResources().getFont(R.font.open_sans);
                holder.textoffername1.setTypeface(typeface);
                holder.txtofferdesc1.setTypeface(typeface);
                holder.txtshop1.setTypeface(typeface);
            }else if (item.getFontStyle().equalsIgnoreCase("Cambria")){
                Typeface typeface = context.getResources().getFont(R.font.cambria);
                holder.textoffername1.setTypeface(typeface);
                holder.txtofferdesc1.setTypeface(typeface);
                holder.txtshop1.setTypeface(typeface);
            }else if (item.getFontStyle().equalsIgnoreCase("Eras Light ITC")){
                Typeface typeface = context.getResources().getFont(R.font.eras_light_itc);
                holder.textoffername1.setTypeface(typeface);
                holder.txtofferdesc1.setTypeface(typeface);
                holder.txtshop1.setTypeface(typeface);
            }else if (item.getFontStyle().equalsIgnoreCase("Tahoma")){
                Typeface typeface = context.getResources().getFont(R.font.tahoma);
                holder.textoffername1.setTypeface(typeface);
                holder.txtofferdesc1.setTypeface(typeface);
                holder.txtshop1.setTypeface(typeface);
            }else if (item.getFontStyle().equalsIgnoreCase("Garamond")){
                Typeface typeface = context.getResources().getFont(R.font.garamond);
                holder.textoffername1.setTypeface(typeface);
                holder.txtofferdesc1.setTypeface(typeface);
                holder.txtshop1.setTypeface(typeface);
            }else if (item.getFontStyle().equalsIgnoreCase("Georgia")){
                Typeface typeface = context.getResources().getFont(R.font.georgia);
                holder.textoffername1.setTypeface(typeface);
                holder.txtofferdesc1.setTypeface(typeface);
                holder.txtshop1.setTypeface(typeface);
            }else if (item.getFontStyle().equalsIgnoreCase("Courier New")){
                Typeface typeface = context.getResources().getFont(R.font.courier_new);
                holder.textoffername1.setTypeface(typeface);
                holder.txtofferdesc1.setTypeface(typeface);
                holder.txtshop1.setTypeface(typeface);
            }else if (item.getFontStyle().equalsIgnoreCase("Brush Script MT")){
                Typeface typeface = context.getResources().getFont(R.font.brush_script_mt);
                holder.textoffername1.setTypeface(typeface);
                holder.txtofferdesc1.setTypeface(typeface);
                holder.txtshop1.setTypeface(typeface);
            }else if (item.getFontStyle().equalsIgnoreCase("Times New Roman")){
                Typeface typeface = context.getResources().getFont(R.font.times_new_roman);
                holder.textoffername1.setTypeface(typeface);
                holder.txtofferdesc1.setTypeface(typeface);
                holder.txtshop1.setTypeface(typeface);
            }
        }

        if (item.getBlockActualtext().equalsIgnoreCase("New Additions") || item.getBlockActualtext().equalsIgnoreCase("New Additions")
                || item.getBlockActualtext().equalsIgnoreCase("Items On Promotion") || item.getBlockActualtext().equalsIgnoreCase("Items On Promotion")
                || item.getBlockActualtext().equalsIgnoreCase("Staff Picks") || item.getBlockActualtext().equalsIgnoreCase("Staff Picks")
                || item.getBlockActualtext().equalsIgnoreCase("SpecialOffer 1") || item.getBlockActualtext().equalsIgnoreCase("SpecialOffer 1")
                || item.getBlockActualtext().equalsIgnoreCase("SpecialOffer 2") || item.getBlockActualtext().equalsIgnoreCase("SpecialOffer 2")) {

            if (item.getBlockImgText().equalsIgnoreCase("MR")) {
                holder.textoffername1.setGravity(Gravity.CENTER | Gravity.RIGHT);
                holder.txtofferdesc1.setGravity(Gravity.CENTER | Gravity.RIGHT);
                holder.txtshop1.setGravity(Gravity.CENTER | Gravity.RIGHT);
                holder.block1.setGravity(Gravity.CENTER | Gravity.RIGHT);
            } else if (item.getBlockImgText().equalsIgnoreCase("TR")) {
                holder.textoffername1.setGravity(Gravity.TOP | Gravity.RIGHT);
                holder.txtofferdesc1.setGravity(Gravity.TOP | Gravity.RIGHT);
                holder.txtshop1.setGravity(Gravity.TOP | Gravity.RIGHT);
                holder.block1.setGravity(Gravity.TOP | Gravity.RIGHT);
            } else if (item.getBlockImgText().equalsIgnoreCase("BR")) {
                holder.textoffername1.setGravity(Gravity.BOTTOM | Gravity.RIGHT);
                holder.txtofferdesc1.setGravity(Gravity.BOTTOM | Gravity.RIGHT);
                holder.txtshop1.setGravity(Gravity.BOTTOM | Gravity.RIGHT);
                holder.block1.setGravity(Gravity.BOTTOM | Gravity.RIGHT);
            } else if (item.getBlockImgText().equalsIgnoreCase("ML")) {
                holder.textoffername1.setGravity(Gravity.CENTER | Gravity.LEFT);
                holder.txtofferdesc1.setGravity(Gravity.CENTER | Gravity.LEFT);
                holder.txtshop1.setGravity(Gravity.CENTER | Gravity.LEFT);
                holder.block1.setGravity(Gravity.CENTER | Gravity.LEFT);
            } else if (item.getBlockImgText().equalsIgnoreCase("TL")) {
                holder.textoffername1.setGravity(Gravity.TOP | Gravity.LEFT);
                holder.txtofferdesc1.setGravity(Gravity.TOP | Gravity.LEFT);
                holder.txtshop1.setGravity(Gravity.TOP | Gravity.LEFT);
                holder.block1.setGravity(Gravity.TOP | Gravity.LEFT);
            } else if (item.getBlockImgText().equalsIgnoreCase("BL")) {
                holder.textoffername1.setGravity(Gravity.BOTTOM | Gravity.LEFT);
                holder.txtofferdesc1.setGravity(Gravity.BOTTOM | Gravity.LEFT);
                holder.txtshop1.setGravity(Gravity.BOTTOM | Gravity.LEFT);
                holder.block1.setGravity(Gravity.BOTTOM | Gravity.LEFT);
            } else if (item.getBlockImgText().equalsIgnoreCase("c")) {
                holder.textoffername1.setGravity(Gravity.CENTER);
                holder.txtofferdesc1.setGravity(Gravity.CENTER);
                holder.txtshop1.setGravity(Gravity.CENTER);
                holder.block1.setGravity(Gravity.CENTER);
            }


//            For text color Block 1

            if (!item.getFontColor().equals("") && item.getFontColor()!=null && !item.getFontColor().isEmpty()){

                holder.textoffername1.setTextColor(Color.parseColor(item.getFontColor()));
                holder.txtofferdesc1.setTextColor(Color.parseColor(item.getFontColor()));
                holder.txtshop1.setTextColor(Color.parseColor(item.getFontColor()));
            }
        }

        holder.block1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String startprice = "", endprice = "", blockdisountGroup = "";

                if (item.getBlockStratprice() != null && !item.getBlockStratprice().isEmpty()) {
                    startprice = item.getBlockStratprice();
                } else {
                    startprice = "0";
                }

                if (item.getBlockEndprice() != null && !item.getBlockEndprice().isEmpty()) {
                    endprice = item.getBlockEndprice();
                } else {
                    endprice = "0";
                }

                if (item.getBlockSpecialoffer() != null && !item.getBlockSpecialoffer().isEmpty()) {
                    blockdisountGroup = item.getBlockSpecialoffer();
                } else {
                    blockdisountGroup = "0";
                }

                String type = item.getBlockActualtext();

                if (type.equalsIgnoreCase("Items On Promotion")){
                    type = "promotion";
                }else if (type.equalsIgnoreCase("Staff Picks")){
                    type="staffpick";
                }else if (type.equalsIgnoreCase("New Additions")){
                    type="newddition";
                }

                if (type.contains("Special Offer")){
                    type="Special offer";
                }

                if (Constant.SCREEN_LAYOUT == 1) {
                    MainActivity.getInstance().loadViewAllFragment(type, "0", "0", startprice, endprice, blockdisountGroup, item.getBlockDisplaytext(), "", "");
                } else if (Constant.SCREEN_LAYOUT == 2) {
                    MainActivityDup.getInstance().loadViewAllFragment(type, "0", "0", startprice, endprice, blockdisountGroup, item.getBlockDisplaytext(), "", "");
                }

            }
        });

    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView textoffername1, txtofferdesc1, txtshop1;
        ImageView img1;
        CardView cvBlock1;
        LinearLayout block1;

        public ViewHolder(@NonNull View v1) {
            super(v1);
            textoffername1 = v1.findViewById(R.id.textoffername);
            txtofferdesc1 = v1.findViewById(R.id.txtofferdesc);
            img1 = v1.findViewById(R.id.img);
            txtshop1 = v1.findViewById(R.id.txtshop);
            cvBlock1 = v1.findViewById(R.id.cvBlock);
            block1 = v1.findViewById(R.id.block);
        }
    }
}
