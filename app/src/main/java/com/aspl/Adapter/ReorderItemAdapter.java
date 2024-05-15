package com.aspl.Adapter;

import android.content.Context;
import android.graphics.Color;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.aspl.Utils.BSTheme;
import com.aspl.Utils.Constant;
import com.aspl.Utils.DialogUtils;
import com.aspl.fragment.CardFragment;
import com.aspl.mbs.R;
import com.aspl.mbsmodel.ReOrderItemModel;

import java.util.List;

public class ReorderItemAdapter extends RecyclerView.Adapter<ReorderItemAdapter.MyViewHolder>{
    Context context;
    List<ReOrderItemModel> reorderList;
    String requestedQty;

    public ReorderItemAdapter(Context context, List<ReOrderItemModel> reorderList) {
        this.context = context;
        this.reorderList = reorderList;
        Constant.selectedReorderList.clear();
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_reorder_item_list, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        ReOrderItemModel reOrderModel = reorderList.get(position);
        if(reOrderModel != null){
            if(reOrderModel.getItemName() != null && !reOrderModel.getItemName().isEmpty()){
                holder.tvItemName.setText(reOrderModel.getItemName());
            }

            if(reOrderModel.getSN().equals("N")){
                holder.llQtyAndcheckbox.setVisibility(View.INVISIBLE);
                holder.tvItemNoLonger.setVisibility(View.VISIBLE);

            }else{

                holder.llQtyAndcheckbox.setVisibility(View.VISIBLE);
                holder.tvItemNoLonger.setVisibility(View.GONE);

                if(Integer.parseInt(reOrderModel.getInventoryQuantity())<=0){

                    holder.etQty.setVisibility(View.GONE);
                    holder.checkBox1.setVisibility(View.GONE);
                    holder.tvItemOutofStock.setVisibility(View.VISIBLE);

                }else{

                    holder.etQty.setVisibility(View.VISIBLE);
                    holder.checkBox1.setVisibility(View.VISIBLE);
                    holder.tvItemOutofStock.setVisibility(View.GONE);

                    if(holder.checkBox1.getVisibility()==View.VISIBLE){
                        holder.checkBox1.setChecked(true);
                        Constant.selectedReorderList.add(reOrderModel);
                    }

                    holder.etQty.setOnFocusChangeListener(new View.OnFocusChangeListener() {
                        @Override
                        public void onFocusChange(View view, boolean hasFocus) {

                            if (!hasFocus) {
                                Log.d("focus", "focus lost");

                                InputMethodManager imm = (InputMethodManager)context.getSystemService(Context.INPUT_METHOD_SERVICE);
                                assert imm != null;
                                imm.hideSoftInputFromWindow(view.getWindowToken(), 0);

                                requestedQty = holder.etQty.getText().toString();

                                if(reOrderModel.getInventoryQuantity() != null && !reOrderModel.getInventoryQuantity().isEmpty()) {

                                    String qtyOnStock = reOrderModel.getInventoryQuantity();

                                    if (Integer.parseInt(requestedQty) > Integer.parseInt(reOrderModel.getInventoryQuantity())) {
                                        holder.etQty.setText(reOrderModel.getInventoryQuantity());
                                        DialogUtils.notEnoughQuantityDialog(context, null, Integer.parseInt(requestedQty), "Reorder", qtyOnStock);
                                    }
                                }
//                                //currently added for take individual qty of each record
//
//                                for(int i=0;i < Constant.selectedReorderList.size(); i++) {
//                                    if (reorderList.get(position).getItemID().equals(Constant.selectedReorderList.get(i).getItemID())) {
//                                        Constant.selectedReorderList.get(i).setQty(holder.etQty.getText().toString());
//                                    }
//                                }

                            } else {
                                Log.d("focus", "focused");
                            }
                        }
                    });

                    onTouchfunctions(holder.etQty,holder.llmain,holder.llrow,holder.checkBox1);


                    holder.etQty.addTextChangedListener(new TextWatcher() {
                        @Override
                        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                        }

                        @Override
                        public void onTextChanged(CharSequence s, int start, int before, int count) {

                        }

                        @Override
                        public void afterTextChanged(Editable s) {

                            //currently added for take individual qty of each record

                            for(int i=0;i < Constant.selectedReorderList.size(); i++) {
                                if (reorderList.get(position).getItemID().equals(Constant.selectedReorderList.get(i).getItemID())) {
                                    Constant.selectedReorderList.get(i).setQty(holder.etQty.getText().toString());
                                }
                            }
                        }
                    });
                }

            }

            holder.checkBox1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton compoundButton, boolean ischecked) {
                    if(ischecked){
                        Constant.selectedReorderList.add(reOrderModel);
                    }else{
//                        if(Constant.selectedReorderList.contains(reOrderModel.getItemID())){

                            for(int i=0;i<Constant.selectedReorderList.size();i++){
                                if(Constant.selectedReorderList.get(i).getItemID().equals(reOrderModel.getItemID())){
                                    Constant.selectedReorderList.remove(i);
                                }
                            }
//                        }
                    }

                }
            });


        }

    }

    private void onTouchfunctions(EditText etQty, LinearLayout llmain, LinearLayout llrow, CheckBox checkBox1) {

        llrow.setOnTouchListener(new View.OnTouchListener() {

            public final static int FINGER_RELEASED = 0;
            public final static int FINGER_TOUCHED = 1;
            public final static int FINGER_DRAGGING = 2;
            public final static int FINGER_UNDEFINED = 3;

            private int fingerState = FINGER_RELEASED;

            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {

                switch (motionEvent.getAction()) {

                    case MotionEvent.ACTION_DOWN:
                        Log.d("down","down::");
                        if (fingerState == FINGER_RELEASED) {
                            fingerState = FINGER_TOUCHED;

                            etQty.clearFocus();

                        }else fingerState = FINGER_UNDEFINED;
                        break;

                    case MotionEvent.ACTION_UP:
                        if (fingerState != FINGER_DRAGGING) {
                            fingerState = FINGER_RELEASED;

//                                Handler handler = new Handler();
//                                handler.postDelayed(new Runnable() {
//                                    @Override
//                                    public void run() {

//                                    }
//                                }, 500);


                        } else if (fingerState == FINGER_DRAGGING) fingerState = FINGER_RELEASED;
                        else fingerState = FINGER_UNDEFINED;
                        break;

                    case MotionEvent.ACTION_MOVE:
                        if (fingerState == FINGER_TOUCHED || fingerState == FINGER_DRAGGING)
                            fingerState = FINGER_DRAGGING;
                        else fingerState = FINGER_UNDEFINED;
                        break;

                    default:
                        fingerState = FINGER_UNDEFINED;
                }

                return false;
            }
        });

        llmain.setOnTouchListener(new View.OnTouchListener() {

            public final static int FINGER_RELEASED = 0;
            public final static int FINGER_TOUCHED = 1;
            public final static int FINGER_DRAGGING = 2;
            public final static int FINGER_UNDEFINED = 3;

            private int fingerState = FINGER_RELEASED;

            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {

                switch (motionEvent.getAction()) {

                    case MotionEvent.ACTION_DOWN:
                        Log.d("down","down::");
                        if (fingerState == FINGER_RELEASED) {
                            fingerState = FINGER_TOUCHED;

                            etQty.clearFocus();

                        }else fingerState = FINGER_UNDEFINED;
                        break;

                    case MotionEvent.ACTION_UP:
                        if (fingerState != FINGER_DRAGGING) {
                            fingerState = FINGER_RELEASED;

//                                Handler handler = new Handler();
//                                handler.postDelayed(new Runnable() {
//                                    @Override
//                                    public void run() {

//                                    }
//                                }, 500);


                        } else if (fingerState == FINGER_DRAGGING) fingerState = FINGER_RELEASED;
                        else fingerState = FINGER_UNDEFINED;
                        break;

                    case MotionEvent.ACTION_MOVE:
                        if (fingerState == FINGER_TOUCHED || fingerState == FINGER_DRAGGING)
                            fingerState = FINGER_DRAGGING;
                        else fingerState = FINGER_UNDEFINED;
                        break;

                    default:
                        fingerState = FINGER_UNDEFINED;
                }

                return false;
            }
        });

        llrow.setOnTouchListener(new View.OnTouchListener() {

            public final static int FINGER_RELEASED = 0;
            public final static int FINGER_TOUCHED = 1;
            public final static int FINGER_DRAGGING = 2;
            public final static int FINGER_UNDEFINED = 3;

            private int fingerState = FINGER_RELEASED;

            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {

                switch (motionEvent.getAction()) {

                    case MotionEvent.ACTION_DOWN:
                        Log.d("down","down::");
                        if (fingerState == FINGER_RELEASED) {
                            fingerState = FINGER_TOUCHED;

                            etQty.clearFocus();

                        }else fingerState = FINGER_UNDEFINED;
                        break;

                    case MotionEvent.ACTION_UP:
                        if (fingerState != FINGER_DRAGGING) {
                            fingerState = FINGER_RELEASED;

//                                Handler handler = new Handler();
//                                handler.postDelayed(new Runnable() {
//                                    @Override
//                                    public void run() {

//                                    }
//                                }, 500);


                        } else if (fingerState == FINGER_DRAGGING) fingerState = FINGER_RELEASED;
                        else fingerState = FINGER_UNDEFINED;
                        break;

                    case MotionEvent.ACTION_MOVE:
                        if (fingerState == FINGER_TOUCHED || fingerState == FINGER_DRAGGING)
                            fingerState = FINGER_DRAGGING;
                        else fingerState = FINGER_UNDEFINED;
                        break;

                    default:
                        fingerState = FINGER_UNDEFINED;
                }

                return false;
            }
        });

        checkBox1.setOnTouchListener(new View.OnTouchListener() {

            public final static int FINGER_RELEASED = 0;
            public final static int FINGER_TOUCHED = 1;
            public final static int FINGER_DRAGGING = 2;
            public final static int FINGER_UNDEFINED = 3;

            private int fingerState = FINGER_RELEASED;

            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {

                switch (motionEvent.getAction()) {

                    case MotionEvent.ACTION_DOWN:
                        Log.d("down","down::");
                        if (fingerState == FINGER_RELEASED) {
                            fingerState = FINGER_TOUCHED;

                            etQty.clearFocus();

                        }else fingerState = FINGER_UNDEFINED;
                        break;

                    case MotionEvent.ACTION_UP:
                        if (fingerState != FINGER_DRAGGING) {
                            fingerState = FINGER_RELEASED;

//                                Handler handler = new Handler();
//                                handler.postDelayed(new Runnable() {
//                                    @Override
//                                    public void run() {

//                                    }
//                                }, 500);


                        } else if (fingerState == FINGER_DRAGGING) fingerState = FINGER_RELEASED;
                        else fingerState = FINGER_UNDEFINED;
                        break;

                    case MotionEvent.ACTION_MOVE:
                        if (fingerState == FINGER_TOUCHED || fingerState == FINGER_DRAGGING)
                            fingerState = FINGER_DRAGGING;
                        else fingerState = FINGER_UNDEFINED;
                        break;

                    default:
                        fingerState = FINGER_UNDEFINED;
                }

                return false;
            }
        });

    }

    @Override
    public int getItemCount() {
        return reorderList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        CheckBox checkBox1;
        TextView tvItemName,tvItemNoLonger,tvItemOutofStock;
        EditText etQty;
        LinearLayout llrow,llmain,llQtyAndcheckbox;

        public MyViewHolder(View itemView) {
            super(itemView);

            checkBox1 = itemView.findViewById(R.id.checkbox1);
            BSTheme.setCheckBoxColor(checkBox1, Color.parseColor(Constant.themeModel.ThemeColor), Color.GRAY);
            checkBox1.setOnClickListener(CardFragment.getInstance());
//            if()
//            checkBox1.setChecked(true);
//            Constant.selectedReorderList = reorderList;

            tvItemName = itemView.findViewById(R.id.tvItemName);
            tvItemOutofStock = itemView.findViewById(R.id.tvItemOutofStock);
            tvItemNoLonger = itemView.findViewById(R.id.tvItemNoLonger);
            etQty = itemView.findViewById(R.id.etQty);
            llrow = itemView.findViewById(R.id.llrow);
            llmain = itemView.findViewById(R.id.llmain);
            llQtyAndcheckbox = itemView.findViewById(R.id.llQtyAndcheckbox);
//            checkBox1.setOnCheckedChangeListener(this);
//            checkBox.setOnClickListener(this);
        }
    }


}
