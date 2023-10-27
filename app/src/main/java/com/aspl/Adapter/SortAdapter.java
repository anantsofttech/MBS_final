package com.aspl.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.aspl.Utils.Constant;
import com.aspl.mbs.MainActivity;
import com.aspl.mbs.MainActivityDup;
import com.aspl.mbs.R;
import com.aspl.mbsmodel.MbsDataModel;

import java.util.List;

/**
 * Created by new on 07/13/2017.
 */
public class SortAdapter extends BaseAdapter {

    Context context;
    List<MbsDataModel> FooterList;
    public SortAdapter(Context context, List<MbsDataModel> FooterList) {
        this.context=context;
        this.FooterList=FooterList;
    }

    @Override
    public int getCount() {
        return FooterList.size();
    }

    @Override
    public Object getItem(int i) {
        return getItem(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(final int position, View view, ViewGroup viewGroup) {
        if (view == null) {
            LayoutInflater layoutInflater = (LayoutInflater) this.context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = layoutInflater.inflate(R.layout.raw_sort_filter, null);
        }
        MbsDataModel expandedListText = FooterList.get(position);

        TextView listTitle = (TextView) view
                .findViewById(R.id.listTitle);
        listTitle.setText(expandedListText.Sort_Name);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MbsDataModel expandedListText = FooterList.get(position);

                if(Constant.SCREEN_LAYOUT==1){
                    MainActivity.getInstance().LoadWebVew(expandedListText.Sort_Key);
                    MainActivity.getInstance().dialog.dismiss();
                }else if(Constant.SCREEN_LAYOUT==2) {
                    MainActivityDup.getInstance().LoadWebVew(expandedListText.Sort_Key);
                    MainActivityDup.getInstance().dialog.dismiss();
                }
                // MainActivity.getInstance().listPopupWindow.dismiss();
            }
        });
        return view;
    }
}
