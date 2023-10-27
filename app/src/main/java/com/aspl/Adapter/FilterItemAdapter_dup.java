package com.aspl.Adapter;

import android.content.Context;
import android.graphics.Color;
import android.os.Handler;
import android.support.v7.widget.RecyclerView;
import android.text.InputFilter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.aspl.Utils.BSTheme;
import com.aspl.Utils.Constant;
import com.aspl.Utils.InputFilterIntRange;
import com.aspl.Utils.Utils;
import com.aspl.mbs.MainActivity;
import com.aspl.mbs.MainActivityDup;
import com.aspl.mbs.R;
import com.aspl.mbsmodel.Filter;
import com.aspl.mbsmodel.FilterHomePage;
import com.aspl.mbsmodel.FilterSelectedItems;
import com.aspl.mbsmodel.LstDepartment;
import com.aspl.mbsmodel.LstSize;
import com.aspl.mbsmodel.LstStyle;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by mic on 3/14/2018.
 */

public class FilterItemAdapter_dup extends RecyclerView.Adapter<FilterItemAdapter_dup.FilterViewHolder> {

    FilterAdapterEvent myFilterAdapterEvent;
    public static List<Filter> liFilter;
    Context context;
    int type;
    private boolean onBind;
    int divider = 5;
    //String subDepartment = "0";

    public static List<LstDepartment> liDepartment = new ArrayList<>();
    public static List<LstStyle> liStyle;
    public static List<LstSize> liSize;
    public static List<LstSize> liSizeDuplicate;
    public static List<FilterHomePage> liFilterHomePages;


    int isFirst = 0;
    public static int tempPosition = -1;
    private float priceRangeMinMax = 0;
    public static float min = 0;
    public static float max = 0;
    public static float tempPriceRange = 0;
    private RadioButton lastCheckedRB = null;
    private int tempSize = 0;
    public static String priceRange = "";
    public static String tempEtMin = "", tempEtMax = "";
    public static float tempMin, tempMinSecond;
    //    public String deptType, startDiscount, endDiscount;
    DecimalFormat df = new DecimalFormat("####0.00");


    public interface FilterAdapterEvent {
        void selectedDepartment(String deptId);

        void selectedSubDepartment(String deptId, String subDeptId);

        void selectedPrice(String deptId, String subDeptId, String selectedPrice);

        void selectedSize(String deptId, String subDeptId, String size);
    }

    public FilterItemAdapter_dup(Context context, FilterAdapterEvent myFilterAdapterEvent, List<Filter> liFilter, int type) {
        this.context = context;
        this.myFilterAdapterEvent = myFilterAdapterEvent;
        FilterItemAdapter_dup.liFilter = liFilter;
        this.type = type;
        liDepartment = liFilter.get(0).getLstDepartment();
        df.setMaximumFractionDigits(2);
    }

    public FilterItemAdapter_dup(FilterAdapterEvent myFilterAdapterEvent, List<FilterHomePage> liFilterHomePages, List<Filter> lsFilters, int type) {
        this.myFilterAdapterEvent = myFilterAdapterEvent;
        this.type = type;
        FilterItemAdapter_dup.liFilterHomePages = liFilterHomePages;
        liFilter = lsFilters;
        liDepartment = lsFilters.get(0).getLstDepartment();
        liStyle = lsFilters.get(0).getLstStyle();

        if(MainActivityDup.iscomeFromHomeViewall ){
            liSizeDuplicate = lsFilters.get(0).getLstSize();
            MainActivityDup.iscomeFromHomeViewall = false;

        }else if(MainActivityDup.isComeFromDepartmentFilter){
            liSizeDuplicate = lsFilters.get(0).getLstSize();
            MainActivityDup.isComeFromDepartmentFilter = false;

        }else if(MainActivityDup.isComeFromSubdepartmentFilter){
            liSizeDuplicate = lsFilters.get(0).getLstSize();
            MainActivityDup.isComeFromSubdepartmentFilter = false;
        }

        liSize = lsFilters.get(0).getLstSize();
        df.setMaximumFractionDigits(2);

    }

    @Override
    public FilterViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.raw_checkbox_filter, parent, false);
        return new FilterViewHolder(itemView);
    }
    @Override
    public void onBindViewHolder(final FilterViewHolder holder, final int position) {
        MainActivityDup.llPriceRange.setVisibility(View.GONE);
        if (type == 0) {
            onBind = true;

            if (!MainActivityDup.type.isEmpty()) {

                if(MainActivityDup.type.equals("department")){
                    MainActivityDup.tempSpecialOfferSelected  = "0";
                }else {

                    for (int i = 0; i < liFilterHomePages.size(); i++) {

//                        if (MainActivityDup.type.equals(liFilterHomePages.get(i).getBlockActualtext())) {
//                            MainActivityDup.tempSpecialOfferSelected = String.valueOf(i);
//                        }
                        if(MainActivityDup.type.equals("promotion") || MainActivityDup.type.equals("specialoffer")){
                            if(MainActivityDup.blockDisplayedText.equals(liFilterHomePages.get(i).getBlockDisplaytext()))
                                MainActivityDup.tempSpecialOfferSelected = String.valueOf(i);
                        }else{
                            if (MainActivityDup.type.equals(liFilterHomePages.get(i).getBlockActualtext())) {
                                MainActivityDup.tempSpecialOfferSelected = String.valueOf(i);
                            }
                        }


                    }
                }

//                MainActivityDup.llfilterType = "";
            }

            if (!MainActivityDup.tempSpecialOfferSelected.isEmpty()) {
                if (Integer.parseInt(MainActivityDup.tempSpecialOfferSelected) == position) {
                    liFilterHomePages.get(position).setChecked(true);
                }
            }
            if (liFilterHomePages.get(position).getChecked()) {
                holder.deptList.setChecked(true);
            } else {
                holder.deptList.setChecked(false);
            }
            onBind = false;

            if(position == 0){
                holder.deptList.setText("View All");
            }else{
                holder.deptList.setText(liFilterHomePages.get(position).getBlockDisplaytext());
            }

        } else if (type == 1) {
            if (!MainActivityDup.deptId.isEmpty()) {

                if (Integer.parseInt(MainActivityDup.deptId) == liDepartment.get(position).getDeptId()) {
                    liDepartment.get(position).setChecked(true);
                }
            }
            onBind = true;
            if (liDepartment.get(position).getChecked()) {
                holder.deptList.setChecked(true);
            } else {
                holder.deptList.setChecked(false);
            }
            onBind = false;

            if (position == 0) {
                holder.deptList.setText(liDepartment.get(position).getDeptDesc());
            } else {
                holder.deptList.setText(liDepartment.get(position).getDeptDesc() + " (" + liDepartment.get(position).getInvCount().toString() + ")");
            }

        } else if (type == 2) {
            onBind = true;
            if (MainActivityDup.subDepartment.contains(String.valueOf(liStyle.get(position).getStyleId()))) {
                holder.deptList.setChecked(true);
            } else {
                holder.deptList.setChecked(liStyle.get(position).getChecked());
            }
            onBind = false;
            holder.deptList.setText(liStyle.get(position).getStyle() + " (" + liStyle.get(position).getStyleCount().toString() + ")");
        } else if (type == 3) {

            //size

            if(!MainActivityDup.deptSizes.isEmpty()&& !MainActivityDup.deptSizes.equals("0")){
                if(MainActivityDup.deptSizes.contains(",")){
                    String[] deptsizes = MainActivityDup.deptSizes.split(",");
                    for(int i=0;i<deptsizes.length;i++){
                        if(deptsizes[i].equals(String.valueOf(liSizeDuplicate.get(position).getSizeId()))){
                            holder.deptList.setChecked(liSizeDuplicate.get(position).getChecked());
                        }
                    }
                }
            }

            onBind = true;
            if (liSizeDuplicate.get(position).getChecked()) {
                holder.deptList.setChecked(true);
            } else {
                holder.deptList.setChecked(false);
            }

            onBind = false;
            if(position == 0){
                holder.deptList.setText(liSizeDuplicate.get(position).getSizeName());
            }else{
                holder.deptList.setText(liSizeDuplicate.get(position).getSizeName() + " (" + liSizeDuplicate.get(position).getSizeCount().toString() + ")");
            }

//            onBind = true;
//            holder.deptList.setChecked(liSize.get(position).getChecked());
//
//            onBind = false;
//            holder.deptList.setText(liSize.get(position).getSizeName() + " (" + liSize.get(position).getSizeCount().toString() + ")");

        } else if (type == 4) {
            onBind = true;
            holder.deptList.setChecked(liFilter.get(position).getChecked());
            onBind = false;
//            holder.deptList.setText("Items with Images Only" + " (" + liFilter.get(position).getItemWithImageCount().toString() + ")");
            holder.deptList.setText("Items w/ images" + " (" + liFilter.get(position).getItemWithImageCount().toString() + ")");
        } else if (type == 5) {
            MainActivityDup.llPriceRange.setVisibility(View.VISIBLE);

            if (position == 0) {
                holder.deptList.setText("Select All");
                tempPriceRange = min;
            } else {
                //holder.rbPrice.setText(String.valueOf("$" + tempPriceRange +" - " + "$" + String.valueOf(tempPriceRange = tempPriceRange + divider)));
                holder.deptList.setText(String.valueOf("$" + df.format(tempPriceRange) + " - " + "$" + String.valueOf(df.format(tempPriceRange = tempPriceRange + divider))));
            }
            onBind = true;
            if (tempPosition == position) {
                holder.deptList.setChecked(true);
            } else {
                holder.deptList.setChecked(false);
            }

            if (MainActivityDup.etMinIsFocused) {
                MainActivityDup.etMinPrice.requestFocus();
            }
            if (MainActivityDup.etMaxIsFocused) {
                MainActivityDup.etMaxPrice.requestFocus();
            }

            InputFilterIntRange rangeFilter = new InputFilterIntRange(0/*min*/, max);
            tempMin = 0;
            tempMinSecond = 0;
            tempMin = Float.parseFloat((MainActivityDup.etMinPrice.getText().toString().isEmpty()) ? "0" : MainActivityDup.etMinPrice.getText().toString());
            tempMinSecond = Float.parseFloat((MainActivityDup.etMaxPrice.getText().toString().isEmpty()) ? "0" : MainActivityDup.etMaxPrice.getText().toString());
            if (tempMin >= min) {
                MainActivityDup.etMinPrice.setFilters(new InputFilter[]{rangeFilter});
                MainActivityDup.etMinPrice.setOnFocusChangeListener(rangeFilter);
            }
            if (tempMinSecond >= min) {
                MainActivityDup.etMaxPrice.setFilters(new InputFilter[]{rangeFilter});
                MainActivityDup.etMaxPrice.setOnFocusChangeListener(rangeFilter);
            }
            onBind = false;
        }
        addSelectedItem();
    }

    public void addSelectedItem(/*String selectedItemName*/) {

        MainActivityDup.liFilterSelectedItems.clear();
        if (liFilterHomePages != null) {
            for (int i = 0; i < liFilterHomePages.size(); i++) {
                if (MainActivityDup.tempSpecialOfferSelected.equals("0"))
                    liFilterHomePages.get(0).setChecked(true);

                if (liFilterHomePages.get(i).getChecked()) {
                    FilterSelectedItems FSI = new FilterSelectedItems();
                    if(i == 0){
                        FSI.setName("View All");
                    }else{
                        FSI.setName(liFilterHomePages.get(i).getBlockDisplaytext());
                    }
                    MainActivityDup.liFilterSelectedItems.add(FSI);
                }
            }
        }

        if (liDepartment != null) {

            for (int i = 0; i < liDepartment.size(); i++) {
                if (!MainActivityDup.deptId.isEmpty()) {

                    if(MainActivityDup.deptId.equals("0")){
                        MainActivityDup.deptId = String.valueOf(Constant.clickedDeptIdfromhome);
                    }

                    if (Integer.parseInt(MainActivityDup.deptId) == liDepartment.get(i).getDeptId()) {
                        liDepartment.get(i).setChecked(true);
                    }
                }
                if (liDepartment.get(i).getChecked()) {
                    FilterSelectedItems FSI = new FilterSelectedItems();
                    FSI.setName(liDepartment.get(i).getDeptDesc());
                    MainActivityDup.liFilterSelectedItems.add(FSI);
                }
            }
        }

        if (liStyle != null) {
            for (int i = 0; i < liStyle.size(); i++) {
                if (MainActivityDup.subDepartment.contains(String.valueOf(liStyle.get(i).getStyleId()))) {
                    liStyle.get(i).setChecked(true);
                }
                if (liStyle.get(i).getChecked()) {
                    FilterSelectedItems FSI = new FilterSelectedItems();
                    FSI.setName(liStyle.get(i).getStyle());
                    MainActivityDup.liFilterSelectedItems.add(FSI);
                }
            }
        }

        if (liSize != null) {
            for (int i = 0; i < liSize.size(); i++) {
                if (liSize.get(i).getChecked()) {
                    FilterSelectedItems FSI = new FilterSelectedItems();
                    FSI.setName(liSize.get(i).getSizeName());
                    MainActivityDup.liFilterSelectedItems.add(FSI);
                }
            }
        }

        if (liFilter != null) {
            for (int i = 0; i < liFilter.size(); i++) {
                if (liFilter.get(i).getChecked()) {
                    FilterSelectedItems FSI = new FilterSelectedItems();
//                    FSI.setName("Items with Images Only");
                    FSI.setName("Items w/ images");
                    MainActivityDup.liFilterSelectedItems.add(FSI);
                }
            }
        }

        if (tempPosition != -1){
            if (!priceRange.isEmpty()) {
                FilterSelectedItems FSI = new FilterSelectedItems();
                FSI.setName(priceRange);
                MainActivityDup.liFilterSelectedItems.add(FSI);
            }
        }
        MainActivityDup.filterSelectedItemAdapter_dup.notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return getSize();
    }

    public int getSize() {
        int size = 0;

        max = Float.parseFloat((liFilter.get(0).getMaxprice().isEmpty()) ? "0" : liFilter.get(0).getMaxprice());
        min = Float.parseFloat((liFilter.get(0).getMinprice().isEmpty()) ? "0" : liFilter.get(0).getMinprice());
        MainActivityDup.min = String.valueOf(df.format(min));
        MainActivityDup.max = String.valueOf(df.format(max));

        if (type == 0) {
            if (liFilterHomePages != null)
                size = liFilterHomePages.size();
        } else if (type == 1) {
            if (liFilter.get(0).getLstDepartment() != null)
                size = liDepartment.size();
        } else if (type == 2) {
            if (liFilter.get(0).getLstStyle() != null)
                size = liStyle.size();
        } else if (type == 3) {
//            if (liFilter.get(0).getLstSize() != null)
//                size = liSize.size();
            if(liSizeDuplicate != null && liSizeDuplicate.size()>0)
                size = liSizeDuplicate.size();
        } else if (type == 4) {
            size = 1;
        } else if (type == 5) {
            size = getDivider(max, min);
            size = size+1;
            tempSize = size+1;
        }
        return size;
    }

    private int getDivider(float max, float min) {
        int size;
        priceRangeMinMax = (int) (max - min);
        if (priceRangeMinMax < 20)
            divider = 5;
        if (priceRangeMinMax >= 20 && priceRangeMinMax < 50)
            divider = 10;
        else if (priceRangeMinMax >= 50 && priceRangeMinMax < 100)
            divider = 20;
        else if (priceRangeMinMax >= 100 && priceRangeMinMax < 500)
            divider = 50;
        else if (priceRangeMinMax >= 500 && priceRangeMinMax < 1000)
            divider = 100;
        else if (priceRangeMinMax >= 1000 && priceRangeMinMax < 5000)
            divider = 500;
        else if (priceRangeMinMax >= 5000 && priceRangeMinMax < 10000)
            divider = 1000;
        else if (priceRangeMinMax >= 10000)
            divider = 2000;
        size = (int) (max - min) / divider;
        if (size > 0)
            size++;

        return size;
    }

    public static void onUnchecked(String itemName) {
        if (liFilterHomePages != null) {
            for (int i = 0; i < liFilterHomePages.size(); i++) {
                if (liFilterHomePages.get(i).getChecked()) {
                    if (itemName.contains(liFilterHomePages.get(i).getBlockDisplaytext())) {
                        liFilterHomePages.get(i).setChecked(false);
                        MainActivityDup.tempSpecialOfferSelected = "";
                        MainActivityDup.deptId = "";
                        MainActivityDup.subDepartment = "";
                        tempPosition = -1;
                        priceRange = "";
                        MainActivityDup.priceRange = "";
                        MainActivityDup.isPriceChanged = "";
                        MainActivityDup.callClearFilter();
                    }
                }
            }
        }

        if (liDepartment != null) {
            for (int i = 0; i < liDepartment.size(); i++) {
                if (liDepartment.get(i).getChecked()) {
                    if (itemName.contains(liDepartment.get(i).getDeptDesc())) {
                        liDepartment.get(i).setChecked(false);
                        MainActivityDup.deptId = "";
                        MainActivityDup.subDepartment = "";
                        tempPosition = -1;
                        priceRange = "";
                        MainActivityDup.priceRange = "";
                        MainActivityDup.isPriceChanged = "";
                        MainActivityDup.callOfferFilter();
                    }
                }
            }
        }

        if (liStyle != null) {
            for (int i = 0; i < liStyle.size(); i++) {
                if (liStyle.get(i).getChecked()) {
                    if (itemName.contains(liStyle.get(i).getStyle())) {
                        liStyle.get(i).setChecked(false);
                        MainActivityDup.subDepartment = "";
                    }
                }
            }
        }

        if (liSize != null) {
            for (int i = 0; i < liSize.size(); i++) {
                if (liSize.get(i).getChecked()) {
                    if (itemName.contains(liSize.get(i).getSizeName())) {
                        liSize.get(i).setChecked(false);
                    }
                }
            }
        }

        if (itemName != null) {
//            if (itemName.contains("Items with Images Only")) {
            if (itemName.contains("Items w/ images")) {
                liFilter.get(0).setChecked(false);
            }
        }

        if (itemName != null) {
            if (itemName.contains("$")) {
                tempPosition = -1;
                priceRange = "";
                MainActivityDup.priceRange = "";
                MainActivityDup.isPriceChanged = "";
            }
        }

        MainActivityDup.getInstance().filterItemAdapter_dup.notifyDataSetChanged();
    }

    public class FilterViewHolder extends RecyclerView.ViewHolder implements CompoundButton.OnCheckedChangeListener, RadioGroup.OnCheckedChangeListener {
        CheckBox deptList;
        //RadioButton rbPrice;

        public FilterViewHolder(View itemView) {
            super(itemView);
            deptList = itemView.findViewById(R.id.chkOption);
            BSTheme.setCheckBoxColor(deptList, Color.parseColor(Constant.themeModel.ThemeColor), Color.GRAY);
            deptList.setOnCheckedChangeListener(this);
            //rbPrice =  (RadioButton) itemView.findViewById(R.id.rbPrice);
            //BSTheme.setRaqdioButtonColor(rbPrice, Color.parseColor(Constant.themeModel.ThemeColor), Color.GRAY);
            //rbPrice.setOnCheckedChangeListener(this);
        }

        @Override
        public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

            if (!onBind) {
                if (type == 0) {
                    for (int i = 0; i < liFilterHomePages.size(); i++) {
                        if (i == getAdapterPosition()) {
                            liFilterHomePages.get(i).setChecked(true);
                            //MainActivityDup.isPromotion = true;
                            tempPosition = -1;

                            MainActivityDup.deptId = "0";
                            MainActivityDup.subDepartment = "0";
                            MainActivityDup.deptSizes = "";
                            liFilter.get(0).setChecked(false);
                            priceRange = "";
                            MainActivityDup.priceRange = "";
                            MainActivityDup.isPriceChanged = "";

                            MainActivityDup.tempSpecialOfferSelected = String.valueOf(i);
                            MainActivityDup.type = liFilterHomePages.get(i).getBlockActualtext();
                            MainActivityDup.specialOfferGroup = liFilterHomePages.get(i).getBlockSpecialoffer();
                            MainActivityDup.valueOne = liFilterHomePages.get(i).getBlockStratprice();
                            MainActivityDup.valueTwo = liFilterHomePages.get(i).getBlockEndprice();
                            MainActivityDup.blockDisplayedText = liFilterHomePages.get(i).getBlockDisplaytext();
                            MainActivityDup.callSpecialOfferFilter(MainActivityDup.deptId , MainActivityDup.subDepartment, "0", MainActivityDup.specialOfferGroup, MainActivityDup.type, MainActivityDup.valueOne, MainActivityDup.valueTwo, MainActivityDup.blockDisplayedText);
                        } else {
                            if (isChecked) {
                                liFilterHomePages.get(i).setChecked(false);
                            }
                        }
                    }
                }
                if (type == 1) {
                    for (int i = 0; i < liDepartment.size(); i++) {
                        if (i == getAdapterPosition()) {
                            liDepartment.get(i).setChecked(true);
                            deptList.setChecked(true);
                            MainActivityDup.deptId = String.valueOf(liDepartment.get(getAdapterPosition()).getDeptId());
                            //MainActivityDup.callFilter(MainActivityDup.deptId, "0", "0");
                            MainActivityDup.callSpecialOfferFilter(MainActivityDup.deptId, "0", "0", MainActivityDup.specialOfferGroup, MainActivityDup.type, MainActivityDup.valueOne, MainActivityDup.valueTwo, MainActivityDup.blockDisplayedText);

                            MainActivityDup.subDepartment = "";
                            MainActivityDup.deptSizes = "";
                            tempPosition = -1;
                            liFilter.get(0).setChecked(false);
                            priceRange = "";
                            MainActivityDup.priceRange = "";
                            MainActivityDup.isPriceChanged = "";
                            MainActivityDup.isComeFromDepartmentFilter = true;

                        } else {
                            if (liDepartment.get(i).getChecked()) {
                                liDepartment.get(i).setChecked(false);
                                deptList.setChecked(false);
                            }
                        }
                    }
                }
                if (type == 2) {
                    if (isChecked) {
                        liStyle.get(getAdapterPosition()).setChecked(true);
                        MainActivityDup.subDepartment = MainActivityDup.subDepartment + liStyle.get(getAdapterPosition()).getStyleId().toString() + ",";
                    } else {
                        liStyle.get(getAdapterPosition()).setChecked(false);
                        if (MainActivityDup.subDepartment.contains(String.valueOf(liStyle.get(getAdapterPosition()).getStyleId()))) {
                            String tempId = String.valueOf(liStyle.get(getAdapterPosition()).getStyleId()) + ",";
                            MainActivityDup.subDepartment = MainActivityDup.subDepartment.replace(tempId, "");
                        }
                    }
                    MainActivityDup.isComeFromSubdepartmentFilter = true;
                    MainActivityDup.callSpecialOfferFilter(MainActivityDup.deptId, MainActivityDup.subDepartment, "0", MainActivityDup.specialOfferGroup, MainActivityDup.type, MainActivityDup.valueOne, MainActivityDup.valueTwo, MainActivityDup.blockDisplayedText);
                    //MainActivityDup.callFilter(MainActivityDup.deptId, MainActivityDup.subDepartment, "0");
                }
                if (type == 3) {
                    if (isChecked) {

                    if(String.valueOf(liSizeDuplicate.get(getAdapterPosition()).getSizeName()).equals("Select All")){

                        for(int i=0; i<liSizeDuplicate.size();i++){

                            if(liSizeDuplicate.get(i).getSizeName().equals("Select All")){
                                liSizeDuplicate.get(i).setChecked(true);
                                MainActivityDup.deptSizes = "0";
                            }else{
                                liSizeDuplicate.get(i).setChecked(false);
                            }
                        }

                    }else{

                        liSizeDuplicate.get(getAdapterPosition()).setChecked(true);
                        if(MainActivityDup.deptSizes.equals("0")){
                            MainActivityDup.deptSizes = MainActivityDup.deptSizes + "," + String.valueOf(liSizeDuplicate.get(getAdapterPosition()).getSizeId()) + ",";
                        }else{
                            MainActivityDup.deptSizes = MainActivityDup.deptSizes + String.valueOf(liSizeDuplicate.get(getAdapterPosition()).getSizeId()) + ",";
                        }
                    }


                } else {
                    liSizeDuplicate.get(getAdapterPosition()).setChecked(false);
                    if (MainActivityDup.deptSizes.contains(String.valueOf(liSizeDuplicate.get(getAdapterPosition()).getSizeId()))) {
                        String tempId = String.valueOf(liSizeDuplicate.get(getAdapterPosition()).getSizeId()) + ",";
                        MainActivityDup.deptSizes = MainActivityDup.deptSizes.replace(tempId, "");
                    }
                }

//                    if (isChecked) {
//                        liSize.get(getAdapterPosition()).setChecked(true);
//                        MainActivityDup.deptSizes = MainActivityDup.deptSizes + liSize.get(getAdapterPosition()).getSizeId().toString() + ",";
//                    } else {
//                        liSize.get(getAdapterPosition()).setChecked(false);
//                        if (MainActivityDup.deptSizes.contains(String.valueOf(liSize.get(getAdapterPosition()).getSizeId()))) {
//                            String tempId = String.valueOf(liSize.get(getAdapterPosition()).getSizeId()) + ",";
//                            MainActivityDup.deptSizes = MainActivityDup.deptSizes.replace(tempId, "");
//                        }
//                    }

                }
                if (type == 4) {
                    if (isChecked) {
                        liFilter.get(getAdapterPosition()).setChecked(true);
                        MainActivityDup.onlyImage = "1";
                    }
                }
                if (type == 5) {

                    for (int i = 0; i < tempSize; i++) {
                        if (i == getAdapterPosition()) {
                            if (tempPosition == getAdapterPosition()) {
                                tempPosition = -1;
                                priceRange = "";
                                MainActivityDup.priceRange = "";
                                MainActivityDup.isPriceChanged = "";
                            } else {
                                if (tempPosition == 0 /*|| deptList.getText().toString().equals("Select All")*/){
                                    tempPosition = getAdapterPosition();
                                    priceRange = min + "-" + max;
                                }else{
                                    tempPosition = getAdapterPosition();
                                    priceRange = deptList.getText().toString();
                                        /*if (deptList.getText().toString().equals("Select All")){
                                            priceRange = min + "-" + max;
                                        }*/
                                }
                                MainActivityDup.priceRange = priceRange.replace("$", "");
                                MainActivityDup.priceRange = MainActivityDup.priceRange.replace(" - ", ";");
                                MainActivityDup.isPriceChanged = "Y";
                            }
                        }
                    }
                    if (!MainActivityDup.etMaxPrice.getText().toString().isEmpty() && !MainActivityDup.etMinPrice.getText().toString().isEmpty()){
                        String tempMin = MainActivityDup.etMaxPrice.getText().toString();
                        String tempMax = MainActivityDup.etMaxPrice.getText().toString();
                        MainActivityDup.priceRange = tempMin + ";" + tempMax;
                    }


                    //Utils.hideKeybord();
                    Utils.hideFilterKeyboard();

                    MainActivityDup.etMaxPrice.getText().clear();
                    MainActivityDup.etMaxPrice.clearFocus();
                    MainActivityDup.etMaxPrice.setCursorVisible(false);

                    MainActivityDup.etMinPrice.getText().clear();
                    MainActivityDup.etMinPrice.clearFocus();
                    MainActivityDup.etMinPrice.setCursorVisible(false);

                    deptList.requestFocus();
                }

                Handler handler = new Handler();

                final Runnable r = new Runnable() {
                    public void run() {
                        notifyDataSetChanged();
                    }
                };

                handler.post(r);
            }
        }

        @Override
        public void onCheckedChanged(RadioGroup group, int checkedId) {
            RadioButton checked_rb = group.findViewById(checkedId);
            if (lastCheckedRB != null) {
                lastCheckedRB.setChecked(false);
            }
            //store the clicked radiobutton
            lastCheckedRB = checked_rb;
        }
    }
}
