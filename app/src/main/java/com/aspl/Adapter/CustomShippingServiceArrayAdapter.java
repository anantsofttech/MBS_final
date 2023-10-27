//package com.aspl.Adapter;
//
//import android.content.Context;
//import android.support.annotation.NonNull;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.ArrayAdapter;
//import android.widget.TextView;
//
//import com.aspl.mbs.R;
//import com.aspl.mbsmodel.UPSServiceModel;
//
//import java.util.ArrayList;
//import java.util.List;
//
//public class CustomShippingServiceArrayAdapter<UPSServiceModel> extends ArrayAdapter<UPSServiceModel> {
//
//    private ArrayList<UPSServiceModel> uPSServiceList;
//    private Context context;
//
////
////    public CustomShippingServiceArrayAdapter(@NonNull Context context, int resource, @NonNull ArrayList<UPSServiceModel> uPSServiceList) {
////        super(context, resource, uPSServiceList);
////        this.context = context;
////        this.uPSServiceList = uPSServiceList;
////    }
//
//    public CustomShippingServiceArrayAdapter(Context context, int simple_spinner_shipping_service, ArrayList<UPSServiceModel> uPSServiceList) {
//
//    }
//
//
//    @Override
//    public View getDropDownView(int position, View convertView,
//                                ViewGroup parent) {
//        return getCustomView(position, convertView, parent);
//    }
//
//    @Override
//    public View getView(int position, View convertView, ViewGroup parent) {
//        return getCustomView(position, convertView, parent);
//    }
//
//    public View getCustomView(int position, View convertView, ViewGroup parent) {
//
//        LayoutInflater inflater=(LayoutInflater) context.getSystemService(  Context.LAYOUT_INFLATER_SERVICE );
//        View row=inflater.inflate(R.layout.simple_spinner_shipping_service, parent, false);
//
//        TextView label=(TextView)row.findViewById(R.id.spinnerTarget);
////        label.setText(uPSServiceList.get(position).g);
//
////        if (position == 0) {//Special style for dropdown header
////            label.setTextColor(context.getResources().getColor(R.color.text_hint_color));
////        }
//        return row;
//    }
//
//}