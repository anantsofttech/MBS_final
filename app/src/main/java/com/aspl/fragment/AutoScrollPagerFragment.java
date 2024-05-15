package com.aspl.fragment;

import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.aspl.Utils.Constant;
import com.aspl.mbs.R;
import com.aspl.mbsmodel.BannerModel;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

/**
 * Created by santalu on 09/08/2017.
 */

public class AutoScrollPagerFragment extends Fragment {

    TextView txt_banner;
    ImageView imgbanner;
    public static BannerModel model1;
    private String imgUrl = Constant.IMG_BASE + Constant.IMG_BANNER_URL+Constant.STOREID+"/";
    private String imgNoImageUrl = Constant.IMG_BASE + Constant.IMG_NO_IMAGE + "/";
    public static AutoScrollPagerFragment newInstance(BannerModel model) {
        model1 = model;

        return new AutoScrollPagerFragment();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.rawpager, container, false);
        imgbanner=view.findViewById(R.id.imgbanner);
        txt_banner=view.findViewById(R.id.txt_banner);
        if(model1 != null){
            Log.e("Log","IMG URL="+imgUrl + model1.getImage());
            // Bitmap bitmap = Utils.textAsBitmap("Technical Problem", 28);
            //Drawable d = new BitmapDrawable(getActivity().getResources(), bitmap);
            Glide.with(getContext()).load(imgUrl + model1.getImage())
                    /* .placeholder(R.drawable.progress_bar)*/
                    .diskCacheStrategy(DiskCacheStrategy.NONE)
                    .skipMemoryCache(true).into(imgbanner);

            if (model1.getCaption()!=null && !model1.getCaption().isEmpty()){
                txt_banner.setVisibility(View.VISIBLE);
                txt_banner.setText(model1.getCaption());
            }
        }
        return view;
    }

    public void onDestroyView() {
        super.onDestroyView();
    /*if (isRecyclerRootViewAlways()) {
      mRootView = null;//<--
    }mMyFragmentLifecycle.onFragmentDestroyView(this);*/
    }
}
