package com.aspl.Adapter;

import android.content.Context;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.aspl.mbs.MainActivity;
import com.aspl.mbs.R;

import java.util.ArrayList;

public class UsernameAdapter extends RecyclerView.Adapter<UsernameAdapter.MyViewHolder> {
    public ArrayList<String> userEmailList;
    Context context;

    public UsernameAdapter(Context context, ArrayList<String> userEmailList) {
        this.context = context;
        this.userEmailList = userEmailList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View itemView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.dropdown_username, viewGroup, false);
        return new UsernameAdapter.MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int i) {
        holder.tv_country.setText(userEmailList.get(i).toString());
        Log.e("onclick","adpt::"+i);
//            clearView(rvUsername, viewHolder);
        if(MainActivity.isswiped){
            removeItem(holder.getAdapterPosition());
            MainActivity.isswiped =false;
        }
//            usernameAdapter.notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return userEmailList.size();
    }

    public void removeItem(int position) {
        userEmailList.remove(position);
        notifyItemRemoved(position);
        notifyItemRangeChanged(position, userEmailList.size());
    }

//    public void removeItem(int position) {
//        u.players.remove(position);
//        mAdapter.notifyItemRemoved(position);
//        mAdapter.notifyItemRangeChanged(position, mAdapter.getItemCount());
//        Constant.userNameList.remove(position);
//            // notify the item removed by position
//            // to perform recycler view delete animations
//            // NOTE: don't call notifyDataSetChanged()
//        notifyItemRemoved(position);
//
//    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView tv_country;

        public MyViewHolder(View view) {
            super(view);

            tv_country = (TextView) view.findViewById(R.id.tv_country);
        }
    }
}
