package com.aspl.Adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.os.Build;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.TextView;

import com.aspl.Utils.BSTheme;
import com.aspl.Utils.Constant;
import com.aspl.Utils.Utils;
import com.aspl.mbs.MainActivity;
import com.aspl.mbs.MainActivityDup;
import com.aspl.mbs.R;
import com.aspl.mbsmodel.StoreHour;
import com.aspl.mbsmodel.StoreLocationModel;
import com.aspl.mbsmodel.UserModel;
import com.aspl.task.TaskStoreDeliveryHours;

import java.text.SimpleDateFormat;
import java.util.List;

public class StoreLocationAdapter extends RecyclerView.Adapter<StoreLocationAdapter.MyvieHolder> implements TaskStoreDeliveryHours.StoreDeliveryHoursEvent {
    Context context;
    List<StoreLocationModel> storeLocationList;
    int clickedPosition = -1;
    int favouriteSelectionPosition = -1;
    String closeTimeofStore;
    MyvieHolder holderTemp;
    boolean isFavourite = false;

    public StoreLocationAdapter(Context context, List<StoreLocationModel> storeLocationList) {
        this.context = context;
        this.storeLocationList = storeLocationList;
    }

    @NonNull
    @Override
    public MyvieHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View storeView = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_store_location, parent, false);
        return new MyvieHolder(storeView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyvieHolder holder, @SuppressLint("RecyclerView") int position) {

        StoreLocationModel storeLocationModel = storeLocationList.get(position);

        if (storeLocationModel != null) {
            holder.rb_store.setPadding(15, 0, 0, 0);
            holder.rb_store.setTag(storeLocationModel);
            holder.llStoreLocationDetails.setTag(storeLocationModel);
            holder.iv_favouriteIcon.setTag(storeLocationModel);

            if(clickedPosition == position){
                holder.rb_store.setChecked(true);
            }else{
                holder.rb_store.setChecked(false);
            }

            if(clickedPosition == -1){

                if(Constant.STOREID.equals(storeLocationList.get(position).getStoreno())){
                    holder.rb_store.setChecked(true);
                    clickedPosition = position;
                }
            }

            if (UserModel.Cust_mst_ID != null){

                if(favouriteSelectionPosition == position){
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                        holder.iv_favouriteIcon.setImageDrawable(context.getDrawable(R.drawable.ic_action_favourite));
                    }
                }else{
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                        holder.iv_favouriteIcon.setImageDrawable(context.getDrawable(R.drawable.ic_action_favouriteborder));
                    }
                }

                if(favouriteSelectionPosition == -1) {
                    if (Constant.favstorelocation != null && !Constant.favstorelocation.isEmpty()) {
                        if (Constant.favstorelocation.equals(storeLocationList.get(position).getStoreno())) {
                            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                                holder.iv_favouriteIcon.setImageDrawable(context.getDrawable(R.drawable.ic_action_favourite));
                            }
                            favouriteSelectionPosition = position;
                            isFavourite = true;
                        }
                    }
                }
            }

//             if (storeLocationList.get(position).getStoreLock()){
//                 holder.llStoreLocationDetails.setVisibility(View.GONE);
//             }else {
                 holder.tvStoreName.setText(storeLocationModel.getName());
                 holder.tvStoreadd.setText(storeLocationModel.getAddress());
                 holder.tvStore_cityZip.setText(storeLocationModel.getCity() + ", " + storeLocationModel.getSt() + " " + storeLocationModel.getZip());
//             }

            if(Constant.StoreCloseList != null && Constant.StoreCloseList.size() > 0){
                for(int i=0;i<Constant.StoreCloseList.size();i++){
                    if(Constant.StoreCloseList.get(i).getStoreno().equals(storeLocationList.get(position).getStoreno())){

                        if(Constant.StoreCloseList.get(i).getClosetime() != null && !Constant.StoreCloseList.get(i).getClosetime().equals(""))

                            if(Constant.StoreCloseList.get(i).getClosetime().contains("Reopens")){
                                 holder.tvStoreCloses.setText(Constant.StoreCloseList.get(i).getClosetime());
                                 holder.tvStoreCloses.setTextColor(context.getResources().getColor(R.color.red));
                            }else{
                                 holder.tvStoreCloses.setText(Constant.StoreCloseList.get(i).getClosetime());
                                 holder.tvStoreCloses.setTextColor(Color.parseColor(Constant.themeModel.ThemeColor));
                            }
                        break;
                    }
                }
            }

//            if(storeLocationModel.getStoreno()!= null && !storeLocationModel.getStoreno().isEmpty()){
//                callStorehoursWSForAllStores(storeLocationModel.getStoreno());
//                holderTemp = holder;
//            }

            holder.llStoreLocationDetails.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    StoreLocationModel storeLocationModel1 = (StoreLocationModel) view.getTag();
                    Utils.selectedStoreModel = storeLocationModel1;
                    clickedPosition = holder.getAdapterPosition();
                    notifyDataSetChanged();
                }
            });

            holder.rb_store.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    StoreLocationModel storeLocationModel1 = (StoreLocationModel) view.getTag();
                    Utils.selectedStoreModel = storeLocationModel1;
                    clickedPosition = holder.getAdapterPosition();
                    notifyDataSetChanged();
                }
            });

            holder.iv_favouriteIcon.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(isFavourite){

                        Constant.isclickedFavLocation = true;

                        StoreLocationModel storeLocaModel = (StoreLocationModel) view.getTag();

                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                            holder.iv_favouriteIcon.setImageDrawable(context.getDrawable(R.drawable.ic_action_favourite));
                        }
                        isFavourite = false;
                        favouriteSelectionPosition = holder.getAdapterPosition();
                        notifyDataSetChanged();

                        if (Constant.SCREEN_LAYOUT == 1) {
                            if (Constant.storeLocationList != null && Constant.storeLocationList.size() > 0) {
                                MainActivity.getInstance().callCusDefaultFavLocationWS(context,storeLocaModel.getStoreno());
                            }
                        }else if (Constant.SCREEN_LAYOUT == 2) {
                            if (Constant.storeLocationList != null && Constant.storeLocationList.size() > 0) {
                                MainActivityDup.getInstance().callCusDefaultFavLocationWS(context, storeLocaModel.getStoreno());
                            }
                        }

                    }else{
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                            holder.iv_favouriteIcon.setImageDrawable(context.getDrawable(R.drawable.ic_action_favouriteborder));
                        }
                        isFavourite = true;
                    }
                }
            });
        }
    }

//    private void callStorehoursWSForAllStores(String storeno) {
//        String Url = Constant.WS_BASE_URL + Constant.GET_DELIVERY_HOURS + "/" +  storeno + "/" + "store";
//        TaskStoreDeliveryHours taskStoreDeliveryHours = new TaskStoreDeliveryHours(this,context,"");
//        taskStoreDeliveryHours.execute(Url);
//    }

    @Override
    public int getItemCount() {
        return storeLocationList.size();
    }

    @Override
    public void onGetStoreDeliveryHoursResult(List<StoreHour> storeHourList) {

        if(storeHourList != null && storeHourList.size() > 0) {
            String today = Utils.getCurrentDay();
//                    boolean isStoreClosedtoday = false;

            int pos = 0;

            SimpleDateFormat sdf = new SimpleDateFormat("hh:mm a");

            for (int i = 0; i < storeHourList.size(); i++) {
                if (today.equals(storeHourList.get(i).getStoreDay())) {
                    pos = i;
                    if (storeHourList.get(i).getClosed()) {
//                                isStoreClosedtoday = true;
                    }
                    break;
                }
            }
//                    String openTime = Constant.liOnlyStoreHour.get(pos).getOpenTime();
            closeTimeofStore = storeHourList.get(pos).getCloseTime();
//            if(holderTemp != null){
//                holderTemp.tvStoreCloses.setText("Open until " + closeTime);
//            }

        }
    }

    public class MyvieHolder extends RecyclerView.ViewHolder {

        RadioButton rb_store;
        TextView tvStoreName,tvStoreadd,tvStore_cityZip,tvStoreCloses;
        LinearLayout llStoreLocationDetails;
        ImageView iv_favouriteIcon;

        public MyvieHolder(View storeView) {
            super(storeView);

            rb_store = storeView.findViewById(R.id.rb_store);
            BSTheme.setRaqdioButtonColor(rb_store, Color.parseColor(Constant.themeModel.ThemeColor), Color.BLACK);
            tvStoreName = storeView.findViewById(R.id.tvStoreName);
            tvStoreadd = storeView.findViewById(R.id.tvStoreadd);
            tvStore_cityZip = storeView.findViewById(R.id.tvStore_cityZip);
            tvStoreCloses = storeView.findViewById(R.id.tvStoreCloses);
            tvStoreCloses.setTextColor(Color.parseColor(Constant.themeModel.ThemeColor));
            llStoreLocationDetails = storeView.findViewById(R.id.llStoreLocationDetails);
            iv_favouriteIcon = storeView.findViewById(R.id.iv_favouriteIcon);

            if(UserModel.Cust_mst_ID != null){
                Constant.AppPref.edit().putString("currentCustId", UserModel.Cust_mst_ID).apply();
                iv_favouriteIcon.setVisibility(View.VISIBLE);
            }else{
                iv_favouriteIcon.setVisibility(View.GONE);
            }

        }
    }
}
