package com.aspl.Adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.aspl.Utils.Constant;
import com.aspl.mbs.MainActivity;
import com.aspl.mbs.MainActivityDup;
import com.aspl.mbs.R;
import com.aspl.mbsmodel.DepartmentModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by new on 07/13/2017.
 */
public class DepartmentAdapter extends BaseAdapter {

    Context context;
    List<DepartmentModel> DepartmentList;

    public DepartmentAdapter(Context context, ArrayList<DepartmentModel> DepartmentList) {
        this.context = context;
        this.DepartmentList = DepartmentList;
    }

    @Override
    public int getCount() {
        return DepartmentList.size();
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
            view = layoutInflater.inflate(R.layout.raw_depart, null);
        }
        DepartmentModel model = DepartmentList.get(position);

        TextView listTitle = (TextView) view
                .findViewById(R.id.listTitle);
        listTitle.setBackgroundColor(Color.parseColor(Constant.themeModel.Backgroundcolor));
        listTitle.setText(model.dept_desc);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               /* DepartmentModel model = DepartmentList.get(position);
                MainActivity.getInstance().LoadWebVew(model.Sort_Key);
                MainActivity.getInstance().dialog.dismiss();
*/
                if (Constant.SCREEN_LAYOUT == 1) {
                    MainActivity.getInstance().listPopupWindow.dismiss();
                } else if (Constant.SCREEN_LAYOUT == 2) {
                    MainActivityDup.getInstance().listPopupWindow.dismiss();
                }

            }
        });
        return view;
    }
}
