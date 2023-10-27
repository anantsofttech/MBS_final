package com.aspl.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.aspl.mbs.R;
import com.aspl.mbsmodel.PlaceModel;

/**
 * Created by new on 11/17/2017.
 */
public class PlaceAdapter extends ArrayAdapter<PlaceModel> {
    Context mContext;
    PlaceModel[] placeList;
    int layout;


    @Override
    public int getCount() {
        return placeList.length;
    }

    @Override
    public PlaceModel getItem(int position) {
        return placeList[position];
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public PlaceAdapter(Context context, int resource, PlaceModel[] objects) {
        super(context, resource, objects);
        layout = resource;
        this.mContext = context;
        this.placeList = objects;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater)mContext
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        convertView = inflater.inflate(layout, parent, false);

        TextView txtplacesName = (TextView) convertView.findViewById(R.id.txtplacesName);
        PlaceModel model = placeList[position];
        txtplacesName.setText(model.description);
        return convertView;

    }
}


