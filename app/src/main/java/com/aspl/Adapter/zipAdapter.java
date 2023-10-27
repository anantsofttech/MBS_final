package com.aspl.Adapter;

import android.content.Context;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.aspl.Utils.Constant;
import com.aspl.mbs.R;
import com.aspl.mbsmodel.Zipmodel;

import java.util.List;

/**
 * Created by admin on 8/22/2018.
 */

public class zipAdapter extends BaseAdapter {
    List<Zipmodel> zipList;
    View view;
    Context context;

    public zipAdapter(List<Zipmodel> zipList, Context context) {
        this.zipList = zipList;
        this.context = context;
    }

    @Override
    public int getCount() {
        return zipList.size();
    }

    @Override
    public Object getItem(int i) {
        return zipList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        Log.d("zipadapter",":");
        this.view = view;
        if (view == null) {
            LayoutInflater layoutInflater = (LayoutInflater) this.context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
             view = layoutInflater.inflate(R.layout.rawzip, null);
        }

        TextView txtcity=view.findViewById(R.id.txtcity);
        TextView txtZip=view.findViewById(R.id.txtZip);

        Zipmodel zipmodel=zipList.get(i);
        txtcity.setText(""+zipmodel.getCity());
        txtZip.setText(""+zipmodel.getZip());
        return view;
    }
}
