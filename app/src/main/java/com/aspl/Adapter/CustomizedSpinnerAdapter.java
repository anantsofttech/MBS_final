package com.aspl.Adapter;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Paint;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.aspl.Utils.Constant;
import com.aspl.mbs.R;
import com.aspl.mbsmodel.GetSearchData;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import java.util.List;

public class CustomizedSpinnerAdapter extends ArrayAdapter<GetSearchData> {

    Context context;
    List<GetSearchData> result;
    private String imgNoImageUrl = Constant.IMG_BASE + Constant.IMG_NO_IMAGE + "/";
    private String imgUrl = Constant.IMG_BASE + Constant.IMG_BASE_URL + Constant.STOREID + "/";


    public CustomizedSpinnerAdapter(Context context, int resource, List<GetSearchData> result) {
        super(context, resource, result);

        this.context = context;
        this.result = result;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable
            View convertView, @NonNull ViewGroup parent)
    {
        return initView(position, convertView, parent);
    }

    @Override
    public View getDropDownView(int position, @Nullable
            View convertView, @NonNull ViewGroup parent)
    {
        return initView(position, convertView, parent);
    }

    private View initView(int position, View convertView, ViewGroup parent) {

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.search_spinner_layout, parent, false);
        }

        if(result != null && result.size() > 0) {

            TextView tv_title = (TextView)convertView.findViewById(R.id.tv_title);
            TextView tv_department = (TextView)convertView.findViewById(R.id.tv_department);
            TextView tv_price = (TextView)convertView.findViewById(R.id.tv_price);
            TextView tv_size = (TextView)convertView.findViewById(R.id.tv_size);
            TextView tv_promoprice = (TextView)convertView.findViewById(R.id.tv_promoprice);
            ImageView iv_productimg = (ImageView)convertView.findViewById(R.id.iv_productimg);
            TextView tv_price1 = (TextView)convertView.findViewById(R.id.tv_price1);
            TextView tv_size1 = (TextView)convertView.findViewById(R.id.tv_size1);
            LinearLayout ll_customize_price = (LinearLayout) convertView.findViewById(R.id.ll_customize_price);
            LinearLayout ll_customize_multi_pack = (LinearLayout) convertView.findViewById(R.id.ll_customize_multi_pack);

//            if (!result.get(position).getInvLargeImage().isEmpty()) {
//                if (result.get(position).getInvLargeImage().contains("noimage")) {
//
//                    Glide.with(context).load(imgNoImageUrl + result.get(position)
//                            .getInvLargeImage()).placeholder(R.drawable.no_image_new)
//                            .diskCacheStrategy(DiskCacheStrategy.ALL)
//                            .into(iv_productimg);
//                } else {
//
//                    Glide.with(context).load(imgUrl + result.get(position).getInvLargeImage())
//                            .placeholder(R.drawable.progress_bar)
//                            .diskCacheStrategy(DiskCacheStrategy.ALL)
//                            .into(iv_productimg);
//
////                    .skipMemoryCache(true)
//
//                }
//            }

////       ************************* Edited by Varun on 16 Aug 2022 ***************************
//
        if (result.get(position).getInvLargeImageFullPath()!=null || !result.get(position).getInvLargeImageFullPath().isEmpty()) {
            Glide.with(context).load(result.get(position).getInvLargeImageFullPath())
                    .placeholder(R.drawable.noimage)
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .skipMemoryCache(true).into(iv_productimg);
        }
//
////        ****************************** END ******************************




            tv_title.setText(result.get(position).getFilterDescription());


            if(result.get(position).getStyle() != null && !result.get(position).getStyle().isEmpty()){
                tv_department.setText(result.get(position).getDepartment() +" | "+ result.get(position).getStyle());
            } else{
                tv_department.setText(result.get(position).getDepartment());
            }

            tv_department.setTextColor(Color.DKGRAY);

            if(result.get(position).getSize() != null && !result.get(position).getSize().isEmpty()){
                tv_size.setVisibility(View.VISIBLE);
                tv_size.setText(result.get(position).getSize());
                tv_size1.setVisibility(View.VISIBLE);
                tv_size1.setText(result.get(position).getSize());
            }else{
//                 Edited by Varun for gravity issue in search bar of Price
//                tv_size.setVisibility(View.GONE);
                tv_size.setVisibility(View.INVISIBLE);
                tv_size1.setVisibility(View.INVISIBLE);

//                END

            }
            tv_size.setTextColor(Color.DKGRAY);
            tv_size1.setTextColor(Color.DKGRAY);

//            Edited by Varun for remove "$" from it
//            tv_price.setText("$" + result.get(position).getPrice());
//            Edited by Varun for multi_pack item comes in search bar
            if (result.get(position).getPrice().contains("Options")){
                ll_customize_multi_pack.setVisibility(View.VISIBLE);
                tv_price1.setText(result.get(position).getPrice());
                tv_price1.setTextColor(Color.DKGRAY);
                ll_customize_price.setVisibility(View.GONE);
            }else {
//                END
                tv_price.setText(result.get(position).getPrice());
                tv_promoprice.setVisibility(View.GONE);
//            END
                tv_price.setTextColor(Color.DKGRAY);
            }

            if (result.get(position).getPromoPrice() != null && !result.get(position).getPromoPrice().toString().isEmpty()
                    && !result.get(position).getPromoPrice().toString().equalsIgnoreCase("0.00")) {
                tv_promoprice.setVisibility(View.VISIBLE);
//                Edited by Varun for remove "$" from it
//                tv_promoprice.setText("$" + result.get(position).getPromoPrice());
                tv_promoprice.setText(result.get(position).getPromoPrice());
//                END
                tv_price.setPaintFlags(tv_promoprice.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
                tv_price.setTextColor(Color.GRAY);
                tv_promoprice.setTextColor(Color.DKGRAY);
            } else {
                tv_promoprice.setVisibility(View.GONE);
                tv_price.setPaintFlags(tv_promoprice.getPaintFlags() & (~Paint.STRIKE_THRU_TEXT_FLAG));
                tv_price.setTextColor(Color.DKGRAY);
            }

        }

        return convertView;
    }

//    @Override
//    public View getDropDownView(int position, @Nullable View v, @NonNull ViewGroup parent) {
//        return super.getDropDownView(position, v, parent);
//
//        View view;
//        view =  View.inflate(context, R.layout.search_spinner_layout, null);
//
//        if(result != null && result.size() > 0) {
//
//            TextView tv_title = (TextView)view.findViewById(R.id.tv_title);
//            TextView tv_department = (TextView)view.findViewById(R.id.tv_department);
//            TextView tv_price = (TextView)view.findViewById(R.id.tv_price);
//            TextView tv_promoprice = (TextView)view.findViewById(R.id.tv_promoprice);
//            ImageView iv_productimg = (ImageView)view.findViewById(R.id.iv_productimg);
//
//            if (!result.get(position).getInvLargeImage().isEmpty()) {
//                if (result.get(position).getInvLargeImage().contains("noimage")) {
//
//                    Glide.with(context).load(imgNoImageUrl + result.get(position)
//                            .getInvLargeImage()).placeholder(R.drawable.noimage)
//                            .diskCacheStrategy(DiskCacheStrategy.NONE)
//                            .skipMemoryCache(true).into(iv_productimg);
//
//                } else {
//
//                    Glide.with(context).load(imgUrl + result.get(position).getInvLargeImage())
//                            .placeholder(R.drawable.progress_bar)
//                            .diskCacheStrategy(DiskCacheStrategy.NONE)
//                            .skipMemoryCache(true).into(iv_productimg);
//
//                }
//            }
//
//            tv_title.setText(result.get(position).getFilterDescription());
//
//
//            if(result.get(position).getStyle() != null && !result.get(position).getStyle().isEmpty()){
//                tv_department.setText(result.get(position).getDepartment() +" | "+ result.get(position).getStyle());
//            } else{
//                tv_department.setText(result.get(position).getDepartment());
//            }
//
//            tv_price.setText("$" + result.get(position).getPrice());
//
//            if (result.get(position).getPromoPrice() != null && !result.get(position).getPromoPrice().toString().isEmpty() && !result.get(position).getPromoPrice().toString().equalsIgnoreCase("0.00")) {
//                tv_promoprice.setVisibility(View.VISIBLE);
//                tv_promoprice.setText("$" + result.get(position).getPromoPrice());
//                tv_price.setPaintFlags(tv_promoprice.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
//                tv_price.setTextColor(Color.GRAY);
//            } else {
//                tv_promoprice.setVisibility(View.GONE);
//                tv_price.setTextColor(Color.BLACK);
//                tv_price.setPaintFlags(tv_promoprice.getPaintFlags() & (~Paint.STRIKE_THRU_TEXT_FLAG));
//                tv_price.setGravity(Gravity.CENTER);
//            }
//
//        }
//
//
//        //put the data in it
////        String item = data[position];
////        if(item != null)
////        {
////            TextView text1 = (TextView) row.findViewById(R.id.rowText);
////            text1.setTextColor(Color.WHITE);
////            text1.setText(item);
////        }
//
//        return view;
//
//    }

}
