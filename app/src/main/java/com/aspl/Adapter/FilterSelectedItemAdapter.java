package com.aspl.Adapter;

import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.aspl.mbs.R;
import com.aspl.mbsmodel.FilterSelectedItems;

import java.util.List;

/**
 * Created by mic on 3/27/2018.
 */

public class FilterSelectedItemAdapter extends RecyclerView.Adapter<FilterSelectedItemAdapter.FilterSelectedItemViewHolder> {

    List<FilterSelectedItems> liFilterSelectedItems;
    FilterSelectedItems filterSelectedItems;
    /*
    FilterSelectedItemListener myFilterSelectedItemListener;
    public interface FilterSelectedItemListener{
        void unceckedItem(String itemName);
    }
*/

    public FilterSelectedItemAdapter(List<FilterSelectedItems> liFilterSelectedItems){
        this.liFilterSelectedItems = liFilterSelectedItems;
    }

    public FilterSelectedItemAdapter(FilterSelectedItems filterSelectedItems){
        this.filterSelectedItems = filterSelectedItems;
    }

    @Override
    public FilterSelectedItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.raw_filter_selected_item, parent, false);
        return new FilterSelectedItemViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(FilterSelectedItemViewHolder holder, int position) {
        holder.tvName.setText(liFilterSelectedItems.get(position).getName());
    }

    @Override
    public int getItemCount() {
        return liFilterSelectedItems.size();
        //return filterSelectedItems;
    }

    public class FilterSelectedItemViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView tvName;
        ImageView imgDelete;

        public FilterSelectedItemViewHolder(View i) {
            super(i);

            tvName = i.findViewById(R.id.tv_selected_item_name_rv_filter_item_selected);
            imgDelete = i.findViewById(R.id.tmg_delete_selected_filter_item);
            imgDelete.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            FilterItemAdapter.onUnchecked(liFilterSelectedItems.get(getAdapterPosition()).getName());
            liFilterSelectedItems.remove(getAdapterPosition());
            notifyDataSetChanged();
        }
    }
}
