package com.aspl.Adapter;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.aspl.Utils.Constant;
import com.aspl.Utils.Utils;
import com.aspl.mbs.R;
import com.aspl.mbsmodel.ShippingData;

import java.util.List;

/**
 * Created by admin on 1/11/2018.
 */

public class AddressesAdapter extends RecyclerView.Adapter<AddressesAdapter.AddressViewHolder> {
    List<ShippingData> liShippingData;
    AddressesAdapterEvent myAddressesAdapterEvent;

    public interface AddressesAdapterEvent {
        void onAddressChoose(List<ShippingData> liShippingData, int position);
    }

    public AddressesAdapter(List<ShippingData> liShippingData,
                            AddressesAdapterEvent myAddressesAdapterEvent) {
        this.liShippingData = liShippingData;
        this.myAddressesAdapterEvent = myAddressesAdapterEvent;
    }


    @Override
    public AddressViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_rv_addresses, parent, false);
        return new AddressViewHolder(view);
    }

    @Override
    public void onBindViewHolder(AddressViewHolder holder, int i) {
        String address = "";

/*
        address = address + liShippingData.get(i).getLastName().trim() + " " + liShippingData.get(i).getFirstName().trim()
                + "\n\n" + liShippingData.get(i).getCompanyName().trim() + "\n"
                + liShippingData.get(i).getPhone().trim() + "\n"
                + liShippingData.get(i).getAddress1().trim() + "\n"
                + liShippingData.get(i).getCity().trim() + ", " + liShippingData.get(i).getState().trim() + " " + liShippingData.get(i).getZip().trim();
*/

        if (!liShippingData.get(i).getLastName().trim().isEmpty())
            address = address + Utils.capitalFirstLatter(liShippingData.get(i).getLastName().trim());
        if (!liShippingData.get(i).getFirstName().trim().isEmpty())
            address = address + " " + Utils.capitalFirstLatter(liShippingData.get(i).getFirstName().trim());
        if (!liShippingData.get(i).getCompanyName().trim().isEmpty())
            address = address + "\n" + Utils.capitalFirstLatter(liShippingData.get(i).getCompanyName().trim());
        if (!liShippingData.get(i).getPhone().trim().isEmpty())
            address = address+ "\n" + Utils.getNumberFormat(liShippingData.get(i).getPhone().trim());
        if (!liShippingData.get(i).getAddress1().trim().isEmpty())
            address = address+ "\n" + Utils.capitalFirstLatter(liShippingData.get(i).getAddress1().trim());
        if (!liShippingData.get(i).getAddress2().trim().isEmpty())
            address = address+ "\n" + Utils.capitalFirstLatter(liShippingData.get(i).getAddress2().trim());
        if (!liShippingData.get(i).getCity().trim().isEmpty())
            address = address+ "\n" + Utils.capitalFirstLatter(liShippingData.get(i).getCity().trim());
        if (!liShippingData.get(i).getState().trim().isEmpty())
            address = address+ ", " + Utils.forceCapitalString(liShippingData.get(i).getState().trim());
        if (!liShippingData.get(i).getZip().trim().isEmpty())
            address = address+ " " + liShippingData.get(i).getZip().trim();


        holder.tvAddress.setText(address);
        //Change CheckBox line Color
        ColorDrawable colorDrawable = new ColorDrawable(Color.parseColor(Constant.themeModel.ThemeColor));
        holder.v.setBackground(colorDrawable);
    }

    @Override
    public int getItemCount() {
        return liShippingData.size();
    }


    public class AddressViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView tvAddress;
        View v;

        public AddressViewHolder(View itemView) {
            super(itemView);

            tvAddress = itemView.findViewById(R.id.tv_address_rv_addresses_dialog);
            v = itemView.findViewById(R.id.v_main_wish_list);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            if (myAddressesAdapterEvent != null)
                myAddressesAdapterEvent.onAddressChoose(liShippingData, getAdapterPosition());
        }
    }
}
