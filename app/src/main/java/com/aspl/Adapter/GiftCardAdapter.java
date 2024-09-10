package com.aspl.Adapter;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.GradientDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.aspl.Utils.Constant;
import com.aspl.mbs.R;
import com.bumptech.glide.Glide;
import java.util.List;

public class GiftCardAdapter extends RecyclerView.Adapter<GiftCardAdapter.ViewHolder> {

    private Context context;
    private List<String> imageUrls;
    private OnImageClickListener listener;
    private int selectedPosition = RecyclerView.NO_POSITION; // To track selected item
    String image_url_default = Constant.URL + "img/GiftCardDefault/";
    String image_url = Constant.IMG_BASE + Constant.IMG_Gift_Card_URL + Constant.STOREID + "/";

    public GiftCardAdapter(Context context, List<String> imageUrls, OnImageClickListener listener) {
        this.context = context;
        this.imageUrls = imageUrls;
        this.listener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_gift_card, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        String Url = imageUrls.get(position);
        String url1;

        if (Url.contains("Defaultcp")){
            url1= image_url_default + Url;
        }else{
            url1 = image_url+Url;
        }

        Glide.with(context)
                .load(url1)
                .into(holder.imageView);

        // Set border for selected item
        if (position == selectedPosition) {
            GradientDrawable border = new GradientDrawable();
            border.setShape(GradientDrawable.RECTANGLE);
            border.setStroke(8, Color.parseColor(Constant.themeModel.ThemeColor)); // Thin border
            border.setCornerRadius(16);
            holder.imageView.setBackground(border);
        } else {
            GradientDrawable border = new GradientDrawable();
            border.setShape(GradientDrawable.RECTANGLE);
            border.setStroke(2, ContextCompat.getColor(context, R.color.black)); // Thin border for unselected item
            border.setCornerRadius(16);
            holder.imageView.setBackground(border);
        }

        holder.itemView.setOnClickListener(v -> {
            if (listener != null) {
                int previousPosition = selectedPosition;
                selectedPosition = holder.getAdapterPosition();
                notifyItemChanged(previousPosition);
                notifyItemChanged(selectedPosition);
                listener.onImageClick(url1);
            }
        });
    }


    @Override
    public int getItemCount() {
        return imageUrls.size();
    }

    @Override
    public void onAttachedToRecyclerView(@NonNull RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
        // Automatically select the first item if no item is selected
        if (selectedPosition == RecyclerView.NO_POSITION && getItemCount() > 0) {
            selectedPosition = 0;
            notifyItemChanged(selectedPosition);
            if (listener != null) {
                String firstItemUrl = imageUrls.get(selectedPosition);
                String firstItemFullUrl;

                if (firstItemUrl.contains("Defaultcp")){
                    firstItemFullUrl = image_url_default + firstItemUrl;
                } else {
                    firstItemFullUrl = image_url + firstItemUrl;
                }
                listener.onImageClick(firstItemFullUrl);
            }
        }
    }


    public interface OnImageClickListener {
        void onImageClick(String imageUrl);
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.img_item_image);
        }
    }
}
