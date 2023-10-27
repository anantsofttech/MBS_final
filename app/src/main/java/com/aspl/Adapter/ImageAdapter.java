package com.aspl.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.aspl.Utils.DialogUtils;
import com.aspl.mbs.R;
import com.aspl.mbsmodel.PaymentOptions;

import java.util.ArrayList;

public class ImageAdapter extends BaseAdapter {
        private Context context;
        private ArrayList<String> paymentOptionsList;

        public ImageAdapter(Context context, ArrayList<String> paymentOptionsList) {
            this.context = context;
            this.paymentOptionsList = paymentOptionsList;
        }

        public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View gridView;

        if (convertView == null) {

            gridView = new View(context);

            // get layout from mobile.xml
            gridView = inflater.inflate(R.layout.row_gridview_paymentoptions, null);


            // set image based on selected text
            ImageView imageView = (ImageView) gridView
                    .findViewById(R.id.iv_first_payment_option);

            String paymentOption = paymentOptionsList.get(position);

//            if(paymentOption.equals("ApplePay")){
//                imageView.setImageResource(R.drawable.ic_applepay);
//            }
            if(paymentOption.equalsIgnoreCase("GooglePay")){
                imageView.setImageResource(R.drawable.ic_gpay);
            }else if(paymentOption.equalsIgnoreCase("MasterPass")){
                imageView.setImageResource(R.drawable.ic_masterpass);
            }else if(paymentOption.equalsIgnoreCase("paypal")){
                imageView.setImageResource(R.drawable.ic_paypal);
            }

        } else {
            gridView = (View) convertView;
        }

        return gridView;
    }

        @Override
        public int getCount() {
            return paymentOptionsList.size();
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }
}
