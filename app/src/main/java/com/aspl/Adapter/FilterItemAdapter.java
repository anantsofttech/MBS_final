package com.aspl.Adapter;

import android.content.Context;
import android.graphics.Color;
import android.os.Handler;
import androidx.recyclerview.widget.RecyclerView;
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

public class FilterItemAdapter extends RecyclerView.Adapter<FilterItemAdapter.FilterViewHolder> {

    FilterAdapterEvent myFilterAdapterEvent;
    public static List<Filter> liFilter;
    Context context;
    int type;
    private boolean onBind;
    int divider = 5;
//    boolean isSelectallSize = false;

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
//    public static String tempEtMin = "", tempEtMax = "";
    public static float tempMin, tempMinSecond;
//    public String deptType, startDiscount, endDiscount;
    DecimalFormat df = new DecimalFormat("####0.00");


    public interface FilterAdapterEvent {
        void selectedDepartment(String deptId);

        void selectedSubDepartment(String deptId, String subDeptId);

        void selectedPrice(String deptId, String subDeptId, String selectedPrice);

        void selectedSize(String deptId, String subDeptId, String size);
    }

    public FilterItemAdapter(Context context, FilterAdapterEvent myFilterAdapterEvent, List<Filter> liFilter, int type) {
        this.context = context;
        this.myFilterAdapterEvent = myFilterAdapterEvent;
        FilterItemAdapter.liFilter = liFilter;
        this.type = type;
        liDepartment = liFilter.get(0).getLstDepartment();
        df.setMaximumFractionDigits(2);
    }

    public FilterItemAdapter(FilterAdapterEvent myFilterAdapterEvent, List<FilterHomePage> liFilterHomePages, List<Filter> lsFilters, int type) {
        this.myFilterAdapterEvent = myFilterAdapterEvent;
        this.type = type;
        FilterItemAdapter.liFilterHomePages = liFilterHomePages;
        liFilter = lsFilters;
        liDepartment = lsFilters.get(0).getLstDepartment();
        liStyle = lsFilters.get(0).getLstStyle();

        if(MainActivity.iscomeFromHomeViewall ){
            liSizeDuplicate = lsFilters.get(0).getLstSize();
            MainActivity.iscomeFromHomeViewall = false;

        }else if(MainActivity.isComeFromDepartmentFilter){
            liSizeDuplicate = lsFilters.get(0).getLstSize();
            MainActivity.isComeFromDepartmentFilter = false;

        }else if(MainActivity.isComeFromSubdepartmentFilter){
            liSizeDuplicate = lsFilters.get(0).getLstSize();
            MainActivity.isComeFromSubdepartmentFilter = false;
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
        MainActivity.llPriceRange.setVisibility(View.GONE);

        if (type == 0) {
            onBind = true;

            if (!MainActivity.type.isEmpty()) {

                if(MainActivity.type.equals("department")){
                    MainActivity.tempSpecialOfferSelected  = "0";
                }else {

                    for (int i = 0; i < liFilterHomePages.size(); i++) {

//                        if (MainActivity.type.equals(liFilterHomePages.get(i).getBlockActualtext())) {
//                            MainActivity.tempSpecialOfferSelected = String.valueOf(i);
//                        }
                            if(MainActivity.type.equals("promotion") || MainActivity.type.equals("specialoffer")){
                                if(MainActivity.blockDisplayedText.equals(liFilterHomePages.get(i).getBlockDisplaytext()))
                                    MainActivity.tempSpecialOfferSelected = String.valueOf(i);
                            }else{
                                if (MainActivity.type.equals(liFilterHomePages.get(i).getBlockActualtext())) {
                                    MainActivity.tempSpecialOfferSelected = String.valueOf(i);
                                }
                            }


                    }
                }

//                MainActivity.llfilterType = "";
            }

            if (!MainActivity.tempSpecialOfferSelected.isEmpty()) {
                if (Integer.parseInt(MainActivity.tempSpecialOfferSelected) == position) {
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
            if (!MainActivity.deptId.isEmpty()) {

                if (Integer.parseInt(MainActivity.deptId) == liDepartment.get(position).getDeptId()) {
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
            if (MainActivity.subDepartment.contains(String.valueOf(liStyle.get(position).getStyleId()))) {
                holder.deptList.setChecked(true);
            } else {
                holder.deptList.setChecked(liStyle.get(position).getChecked());
            }
            onBind = false;
            holder.deptList.setText(liStyle.get(position).getStyle() + " (" + liStyle.get(position).getStyleCount().toString() + ")");
        } else if (type == 3) {

            //size l
            if(!MainActivity.deptSizes.isEmpty()&& !MainActivity.deptSizes.equals("0")){
                if(MainActivity.deptSizes.contains(",")){
                    String[] deptsizes = MainActivity.deptSizes.split(",");
                    for(int i=0;i<deptsizes.length;i++){
                        if(deptsizes[i].equals(String.valueOf(liSizeDuplicate.get(position).getSizeId()))){
                            holder.deptList.setChecked(liSizeDuplicate.get(position).getChecked());
                        }
                    }
                }
            }
//            else if(liFilter != null && MainActivity.deptId.equals("0")){
//                if(liFilter.get(0).getLstSize() != null){
//                    for (int i = 0; i< liFilter.get(0).getLstSize().size(); i++) {
//                        FilterSelectedItems FSI = new FilterSelectedItems();
//                        FSI.setName(liFilter.get(0).getLstSize().get(i).getSizeName());
//                        MainActivity.liFilterSelectedItems.add(FSI);
//                    }
//                }
//            }

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


            } else if (type == 4) {
            onBind = true;
//            holder.deptList.setText("Items with Images Only" + " (" + liFilter.get(position).getItemWithImageCount().toString() + ")");
            holder.deptList.setText("Items w/ images" + " (" + liFilter.get(position).getItemWithImageCount().toString() + ")");
            if(liFilter.get(position).getItemWithImageCount() == 0){
                holder.deptList.setEnabled(false);
            }else{
                holder.deptList.setEnabled(true);
                holder.deptList.setChecked(liFilter.get(position).getChecked());
                onBind = false;
            }

        } else if (type == 5) {
            MainActivity.llPriceRange.setVisibility(View.VISIBLE);

            if (position == 0) {
                holder.deptList.setText("Select All");
                tempPriceRange = min;
            } else {
                //holder.rbPrice.setText(String.valueOf("$" + tempPriceRange +" - " + "$" + String.valueOf(tempPriceRange = tempPriceRange + divider)));
                holder.deptList.setText(String.valueOf("$" + df.format(tempPriceRange) + " - " + "$" + String.valueOf(df.format(tempPriceRange = tempPriceRange + divider))));
            }
            onBind = true;
            if(tempPosition == 0){//select all
                holder.deptList.setChecked(true); // checked every other checkbox when call multiple time due to adpter
            }else if(tempPosition == position){
                holder.deptList.setChecked(true);
            } else {
                holder.deptList.setChecked(false);
            }

            if (MainActivity.etMinIsFocused) {
                MainActivity.etMinPrice.requestFocus();
            }
            if (MainActivity.etMaxIsFocused) {
                MainActivity.etMaxPrice.requestFocus();
            }

            InputFilterIntRange rangeFilter = new InputFilterIntRange(0/*min*/, max);
            tempMin = 0;
            tempMinSecond = 0;
            tempMin = Float.parseFloat((MainActivity.etMinPrice.getText().toString().isEmpty()) ? "0" : MainActivity.etMinPrice.getText().toString());
            tempMinSecond = Float.parseFloat((MainActivity.etMaxPrice.getText().toString().isEmpty()) ? "0" : MainActivity.etMaxPrice.getText().toString());
            if (tempMin >= min) {
                MainActivity.etMinPrice.setFilters(new InputFilter[]{rangeFilter});
                MainActivity.etMinPrice.setOnFocusChangeListener(rangeFilter);
            }
            if (tempMinSecond >= min) {
                MainActivity.etMaxPrice.setFilters(new InputFilter[]{rangeFilter});
                MainActivity.etMaxPrice.setOnFocusChangeListener(rangeFilter);
            }
            onBind = false;
        }
        addSelectedItem();
    }

    public void addSelectedItem(/*String selectedItemName*/) {

        MainActivity.liFilterSelectedItems.clear();
        if (liFilterHomePages != null) {
            for (int i = 0; i < liFilterHomePages.size(); i++) {
                if (MainActivity.tempSpecialOfferSelected.equals("0"))
                    liFilterHomePages.get(0).setChecked(true);

                if (liFilterHomePages.get(i).getChecked()) {
                    FilterSelectedItems FSI = new FilterSelectedItems();
                    if(i == 0){
                        FSI.setName("View All");
                    }else{
                        FSI.setName(liFilterHomePages.get(i).getBlockDisplaytext());
                    }
                    MainActivity.liFilterSelectedItems.add(FSI);
                }
            }
        }

        if (liDepartment != null) {

            for (int i = 0; i < liDepartment.size(); i++) {
                if (!MainActivity.deptId.isEmpty()) {

                    if(MainActivity.deptId.equals("0")){
                        MainActivity.deptId = String.valueOf(Constant.clickedDeptIdfromhome);
                    }

                    if (Integer.parseInt(MainActivity.deptId) == liDepartment.get(i).getDeptId()) {
                        liDepartment.get(i).setChecked(true);
                    }
                }
                if (liDepartment.get(i).getChecked()) {
                    FilterSelectedItems FSI = new FilterSelectedItems();
                    FSI.setName(liDepartment.get(i).getDeptDesc());
                    MainActivity.liFilterSelectedItems.add(FSI);
                }
            }
        }

        if (liStyle != null) {
            for (int i = 0; i < liStyle.size(); i++) {
                if (MainActivity.subDepartment.contains(String.valueOf(liStyle.get(i).getStyleId()))) {
                    liStyle.get(i).setChecked(true);
                }
                if (liStyle.get(i).getChecked()) {
                    FilterSelectedItems FSI = new FilterSelectedItems();
                    FSI.setName(liStyle.get(i).getStyle());
                    MainActivity.liFilterSelectedItems.add(FSI);
                }
            }
        }

        //here
        if (liSizeDuplicate != null) {
            for (int i = 0; i < liSizeDuplicate.size(); i++) {
                if (liSizeDuplicate.get(i).getChecked()) {
                    FilterSelectedItems FSI = new FilterSelectedItems();
                    FSI.setName(liSizeDuplicate.get(i).getSizeName());
                    MainActivity.liFilterSelectedItems.add(FSI);
                }
            }
        }

        if (liFilter != null) {
            for (int i = 0; i < liFilter.size(); i++) {
                if (liFilter.get(i).getChecked()) {
                    FilterSelectedItems FSI = new FilterSelectedItems();
//                    FSI.setName("Items with Images Only");
                    FSI.setName("Items w/ images");
                    MainActivity.liFilterSelectedItems.add(FSI);
                }
            }
        }

        if (tempPosition != -1){
            if (!priceRange.isEmpty()) {
                FilterSelectedItems FSI = new FilterSelectedItems();
                FSI.setName(priceRange);
                MainActivity.liFilterSelectedItems.add(FSI);
            }
        }
        MainActivity.filterSelectedItemAdapter.notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return getSize();
    }

    public int getSize() {
        int size = 0;

        max = Float.parseFloat((liFilter.get(0).getMaxprice().isEmpty()) ? "0" : liFilter.get(0).getMaxprice());
        min = Float.parseFloat((liFilter.get(0).getMinprice().isEmpty()) ? "0" : liFilter.get(0).getMinprice());
        MainActivity.min = String.valueOf(df.format(min));
        MainActivity.max = String.valueOf(df.format(max));

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
                        MainActivity.tempSpecialOfferSelected = "";
                        MainActivity.deptId = "";
                        MainActivity.subDepartment = "";
                        tempPosition = -1;
                        priceRange = "";
                        MainActivity.priceRange = "";
                        MainActivity.isPriceChanged = "";
                        MainActivity.callClearFilter();
                    }
                }
            }
        }

        if (liDepartment != null) {
            for (int i = 0; i < liDepartment.size(); i++) {
                if (liDepartment.get(i).getChecked()) {
                    if (itemName.contains(liDepartment.get(i).getDeptDesc())) {
                        liDepartment.get(i).setChecked(false);
                        MainActivity.deptId = "";
                        MainActivity.subDepartment = "";
                        tempPosition = -1;
                        priceRange = "";
                        MainActivity.priceRange = "";
                        MainActivity.isPriceChanged = "";
                        MainActivity.callOfferFilter();
                    }
                }
            }
        }

        if (liStyle != null) {
            for (int i = 0; i < liStyle.size(); i++) {
                if (liStyle.get(i).getChecked()) {
                    if (itemName.contains(liStyle.get(i).getStyle())) {
                        liStyle.get(i).setChecked(false);
                        MainActivity.subDepartment = "";
                    }
                }
            }
        }

        if (liSizeDuplicate != null) {
            for (int i = 0; i < liSizeDuplicate.size(); i++) {
                if (liSizeDuplicate.get(i).getChecked()) {
                    if (itemName.contains(liSizeDuplicate.get(i).getSizeName())) {
                        liSizeDuplicate.get(i).setChecked(false);
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
                MainActivity.priceRange = "";
                MainActivity.isPriceChanged = "";
            }
        }

        MainActivity.getInstance().filterItemAdapter.notifyDataSetChanged();
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
                            //MainActivity.isPromotion = true;
                            tempPosition = -1;

                            MainActivity.deptId = "0";
                            MainActivity.subDepartment = "0";
                            MainActivity.deptSizes = "0";
                            liFilter.get(0).setChecked(false);
                            priceRange = "";
                            MainActivity.priceRange = "";
                            MainActivity.isPriceChanged = "";

                            MainActivity.tempSpecialOfferSelected = String.valueOf(i);
                            MainActivity.type = liFilterHomePages.get(i).getBlockActualtext();
                            MainActivity.specialOfferGroup = liFilterHomePages.get(i).getBlockSpecialoffer();
                            MainActivity.valueOne = liFilterHomePages.get(i).getBlockStratprice();
                            MainActivity.valueTwo = liFilterHomePages.get(i).getBlockEndprice();
                            MainActivity.blockDisplayedText = liFilterHomePages.get(i).getBlockDisplaytext();
                            MainActivity.callSpecialOfferFilter(MainActivity.deptId , MainActivity.subDepartment, "0", MainActivity.specialOfferGroup, MainActivity.type, MainActivity.valueOne, MainActivity.valueTwo, MainActivity.blockDisplayedText);
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
//                            MainActivity.TempdeptId = MainActivity.deptId;
                            MainActivity.deptId = String.valueOf(liDepartment.get(getAdapterPosition()).getDeptId());
                            MainActivity.callSpecialOfferFilter(MainActivity.deptId, "0", "0", MainActivity.specialOfferGroup, MainActivity.type, MainActivity.valueOne, MainActivity.valueTwo, MainActivity.blockDisplayedText);
//                     old //MainActivity.callFilter(MainActivity.deptId, "0", "0");
                            MainActivity.subDepartment = "";
                            MainActivity.deptSizes = "";
                            tempPosition = -1;
                            liFilter.get(0).setChecked(false);
                            priceRange = "";
                            MainActivity.priceRange = "";
                            MainActivity.isPriceChanged = "";
                            MainActivity.isComeFromDepartmentFilter = true;

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
                        MainActivity.subDepartment = MainActivity.subDepartment + liStyle.get(getAdapterPosition()).getStyleId().toString() + ",";
                    } else {
                        liStyle.get(getAdapterPosition()).setChecked(false);
                        if (MainActivity.subDepartment.contains(String.valueOf(liStyle.get(getAdapterPosition()).getStyleId()))) {
                            String tempId = String.valueOf(liStyle.get(getAdapterPosition()).getStyleId()) + ",";
                            MainActivity.subDepartment = MainActivity.subDepartment.replace(tempId, "");
                        }
                    }

                    MainActivity.isComeFromSubdepartmentFilter = true;
                    MainActivity.callSpecialOfferFilter(MainActivity.deptId, MainActivity.subDepartment, "0", MainActivity.specialOfferGroup, MainActivity.type, MainActivity.valueOne, MainActivity.valueTwo, MainActivity.blockDisplayedText);
                    //MainActivity.callFilter(MainActivity.deptId, MainActivity.subDepartment, "0");
                }
                if (type == 3) {
                    if (isChecked) {
//
//                        liSize.get(getAdapterPosition()).setChecked(true);
//                        if(MainActivity.deptSizes.equals("0")){
//                            MainActivity.deptSizes = MainActivity.deptSizes + "," + String.valueOf(liSize.get(getAdapterPosition()).getSizeId()) + ",";
//                        }else{
//                            MainActivity.deptSizes = MainActivity.deptSizes + String.valueOf(liSize.get(getAdapterPosition()).getSizeId()) + ",";
//                        }
//
//                    } else {
//                        liSize.get(getAdapterPosition()).setChecked(false);
//                        if (MainActivity.deptSizes.contains(String.valueOf(liSize.get(getAdapterPosition()).getSizeId()))) {
//                            String tempId = String.valueOf(liSize.get(getAdapterPosition()).getSizeId()) + ",";
//                            MainActivity.deptSizes = MainActivity.deptSizes.replace(tempId, "");
//                        }
//                    }


                        if(String.valueOf(liSizeDuplicate.get(getAdapterPosition()).getSizeName()).equals("Select All")){

                                for(int i=0; i<liSizeDuplicate.size();i++){

                                    if(liSizeDuplicate.get(i).getSizeName().equals("Select All")){
                                        liSizeDuplicate.get(i).setChecked(true);
                                        MainActivity.deptSizes = "0";
                                    }else{
                                        liSizeDuplicate.get(i).setChecked(false);
                                    }
                                }

                        }else{

                            liSizeDuplicate.get(getAdapterPosition()).setChecked(true);
                            if(MainActivity.deptSizes.equals("0")){
                                MainActivity.deptSizes = MainActivity.deptSizes + "," + String.valueOf(liSizeDuplicate.get(getAdapterPosition()).getSizeId()) + ",";
                            }else{
                                MainActivity.deptSizes = MainActivity.deptSizes + String.valueOf(liSizeDuplicate.get(getAdapterPosition()).getSizeId()) + ",";
                            }
                        }


                    } else {
                        liSizeDuplicate.get(getAdapterPosition()).setChecked(false);
                        if (MainActivity.deptSizes.contains(String.valueOf(liSizeDuplicate.get(getAdapterPosition()).getSizeId()))) {
                            String tempId = String.valueOf(liSizeDuplicate.get(getAdapterPosition()).getSizeId()) + ",";
                            MainActivity.deptSizes = MainActivity.deptSizes.replace(tempId, "");
                        }
                    }


                    }
                if (type == 4) {
                    if (isChecked) {
                        liFilter.get(getAdapterPosition()).setChecked(true);
                        MainActivity.onlyImage = "1";
                    }
                }
                if (type == 5) {

                    for (int i = 0; i < tempSize; i++) {
                        if (i == getAdapterPosition()) {
                            if (tempPosition == getAdapterPosition()) {
                                tempPosition = -1;
                                priceRange = "";
                                MainActivity.priceRange = "";
                                MainActivity.isPriceChanged = "";
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
                                MainActivity.priceRange = priceRange.replace("$", "");
                                MainActivity.priceRange = MainActivity.priceRange.replace(" - ", ";");
                                MainActivity.isPriceChanged = "Y";
                            }
                        }
                    }
                    if (!MainActivity.etMaxPrice.getText().toString().isEmpty() && !MainActivity.etMinPrice.getText().toString().isEmpty()){
                        String tempMin = MainActivity.etMaxPrice.getText().toString();
                        String tempMax = MainActivity.etMaxPrice.getText().toString();
                        MainActivity.priceRange = tempMin + ";" + tempMax;
                    }


                    //Utils.hideKeybord();
                    Utils.hideFilterKeyboard();

                    MainActivity.etMaxPrice.getText().clear();
                    MainActivity.etMaxPrice.clearFocus();
                    MainActivity.etMaxPrice.setCursorVisible(false);

                    MainActivity.etMinPrice.getText().clear();
                    MainActivity.etMinPrice.clearFocus();
                    MainActivity.etMinPrice.setCursorVisible(false);

                    deptList.requestFocus();
                }

//                notifyDataSetChanged();

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
