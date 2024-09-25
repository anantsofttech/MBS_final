package com.aspl.Adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.aspl.Utils.Constant;
import com.aspl.mbs.R;
import com.aspl.mbsmodel.LstGiftCard_cart;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import java.util.List;
import java.util.Locale;

public class Gift_Card_CartAdapter extends RecyclerView.Adapter<Gift_Card_CartAdapter.MyCardViewHolder> {

    Context context;
    private List<LstGiftCard_cart> liShoppingCat;
    private Gift_Card_CartAdapterEvent myCardAdapterEvent;

    public Gift_Card_CartAdapter(Context context, Gift_Card_CartAdapterEvent myCardAdapterEvent, List<LstGiftCard_cart> liShoppingCat) {
        this.context = context;
        this.myCardAdapterEvent = myCardAdapterEvent;
        this.liShoppingCat = liShoppingCat;
    }

    public interface Gift_Card_CartAdapterEvent {
        void onCardGiftCardItemRemoved(int position, String giftcard_id);

        void onCartGiftCardItemPlus(int adapterPosition, String giftcardId, String giftcardQty);

        void onCartGiftCardItem_minus(int adapterPosition, String giftcardId, String giftcardQty);
    }

    @Override
    public MyCardViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_rv_shoping_card, parent, false);
        return new MyCardViewHolder(itemView);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(MyCardViewHolder holder, int position) {
        if (liShoppingCat != null && position < liShoppingCat.size()) {
            String image_url_default = Constant.URL + "img/GiftCardDefault/";
            String image_url = Constant.IMG_BASE + Constant.IMG_Gift_Card_URL + Constant.STOREID + "/";

            holder.cv_item.setVisibility(View.GONE);
            holder.cv_giftcard.setVisibility(View.VISIBLE);

            String url = liShoppingCat.get(position).getSelectedGCDesign();
            String url1 = url.contains("Defaultcp") ? image_url_default + url : image_url + url;

            Glide.with(context)
                    .load(url1)
                    .placeholder(R.drawable.no_image_new)
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .skipMemoryCache(true)
                    .into(holder.img_giftcard_image_rv_card_fragment);

            if (liShoppingCat.get(position).getCardType().equals("E")) {
                holder.tv_giftcard_name_rv_item.setText("E-Mail");
                holder.ll_recipient_giftcard_cart.setVisibility(View.VISIBLE);
                holder.ll_delivery_giftcat_cart.setVisibility(View.VISIBLE);
                holder.ll_qty_giftcat_cart_label.setVisibility(View.VISIBLE);
                holder.ll_quantity_count_giftcard_cart.setVisibility(View.GONE);
                holder.tv_recipient_giftcard_cart.setText(liShoppingCat.get(position).getToEmail1());
                holder.tv_delivery_giftcard_cart.setText(liShoppingCat.get(position).getDeliveryDate());

                if (liShoppingCat.get(position).getMessage() != null && !liShoppingCat.get(position).getMessage().isEmpty()) {
                    holder.ll_message_giftcat_cart.setVisibility(View.VISIBLE);
                    holder.tv_message_giftcard_cart.setText(liShoppingCat.get(position).getMessage());
                } else {
                    holder.ll_message_giftcat_cart.setVisibility(View.GONE);
                }

                holder.img_minus_rv_card_fragment_giftcard_cart.setVisibility(View.INVISIBLE);
                holder.img_plus_rv_card_fragment_giftcard_cart.setVisibility(View.INVISIBLE);
                holder.tv_item_quantity_change_rv_item_giftcard_cart.setText(liShoppingCat.get(position).getgCQuantity());
                holder.tv_qty_giftcard_cart.setText(liShoppingCat.get(position).getgCQuantity());

            } else if (liShoppingCat.get(position).getCardType().equals("P")) {
                holder.tv_giftcard_name_rv_item.setText("Physical Card");
                holder.ll_recipient_giftcard_cart.setVisibility(View.GONE);
                holder.ll_delivery_giftcat_cart.setVisibility(View.GONE);
                holder.ll_message_giftcat_cart.setVisibility(View.GONE);
                holder.ll_qty_giftcat_cart_label.setVisibility(View.GONE);
                holder.img_minus_rv_card_fragment_giftcard_cart.setVisibility(View.VISIBLE);
                holder.img_plus_rv_card_fragment_giftcard_cart.setVisibility(View.VISIBLE);
                holder.tv_item_quantity_change_rv_item_giftcard_cart.setText(liShoppingCat.get(position).getgCQuantity());
                holder.tv_qty_giftcard_cart.setText(liShoppingCat.get(position).getgCQuantity());

            } else if (liShoppingCat.get(position).getCardType().equals("T")) {
                holder.tv_giftcard_name_rv_item.setText("Text");
                holder.ll_recipient_giftcard_cart.setVisibility(View.VISIBLE);
                holder.ll_delivery_giftcat_cart.setVisibility(View.VISIBLE);
                holder.ll_qty_giftcat_cart_label.setVisibility(View.VISIBLE);
                holder.ll_quantity_count_giftcard_cart.setVisibility(View.GONE);
                holder.tv_recipient_giftcard_cart.setText(formatPhoneNumberUS(liShoppingCat.get(position).getToPhone()));
                holder.tv_delivery_giftcard_cart.setText(liShoppingCat.get(position).getDeliveryDate());

                if (liShoppingCat.get(position).getMessage() != null && !liShoppingCat.get(position).getMessage().isEmpty()) {
                    holder.ll_message_giftcat_cart.setVisibility(View.VISIBLE);
                    holder.tv_message_giftcard_cart.setText(liShoppingCat.get(position).getMessage());
                } else {
                    holder.ll_message_giftcat_cart.setVisibility(View.GONE);
                }

                holder.img_minus_rv_card_fragment_giftcard_cart.setVisibility(View.INVISIBLE);
                holder.img_plus_rv_card_fragment_giftcard_cart.setVisibility(View.INVISIBLE);
                holder.tv_item_quantity_change_rv_item_giftcard_cart.setText(liShoppingCat.get(position).getgCQuantity());
                holder.tv_qty_giftcard_cart.setText(liShoppingCat.get(position).getgCQuantity());
            }

            float amount = Float.parseFloat(liShoppingCat.get(position).getAmoutPur());
            int quantity = Integer.parseInt(liShoppingCat.get(position).getgCQuantity());
            float total = amount * quantity;
            holder.tv_totle_giftcard_cart.setText(String.format(Locale.US, "%.2f", total));

            Log.e("", "Gift Card Cart screen count :" + liShoppingCat.size());
        }
    }

    public String formatPhoneNumberUS(String phoneNumber) {
        // Remove any non-digit characters from the input string
        phoneNumber = phoneNumber.replaceAll("[^\\d]", "");

        // Format the phone number if it has 10 digits
        if (phoneNumber.length() == 10) {
            return String.format("(%s) %s-%s",
                    phoneNumber.substring(0, 3),  // Area code
                    phoneNumber.substring(3, 6),  // First 3 digits
                    phoneNumber.substring(6, 10)); // Last 4 digits
        } else {
            return phoneNumber; // Return the original if not valid length
        }
    }

    @Override
    public int getItemCount() {
        return liShoppingCat.size();
    }

    public void removeItem(int position) {
        if (position >= 0 && position < liShoppingCat.size()) {
            liShoppingCat.remove(position);  // Remove item from the list
            notifyItemRemoved(position);      // Notify that the item has been removed
            notifyItemRangeChanged(position, liShoppingCat.size() - position); // Update remaining items
        }
    }

    public class MyCardViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        // Declare your views here
        ImageView imgCancel;
        CardView cv_item, cv_giftcard;
        ImageView img_giftcard_image_rv_card_fragment, img_minus_rv_card_fragment_giftcard_cart, img_plus_rv_card_fragment_giftcard_cart;
        TextView tv_giftcard_name_rv_item, tv_recipient_giftcard_cart, tv_delivery_giftcard_cart, tv_message_giftcard_cart,
                tv_item_quantity_change_rv_item_giftcard_cart, tv_totle_giftcard_cart, tv_qty_giftcard_cart;
        LinearLayout ll_recipient_giftcard_cart, ll_delivery_giftcat_cart, ll_message_giftcat_cart , ll_qty_giftcat_cart_label, ll_quantity_count_giftcard_cart;
        Button tv_remove_rv_shopping_card_giftcard_cart;

        public MyCardViewHolder(View itemView) {
            super(itemView);
            // Find your views here
            cv_item = itemView.findViewById(R.id.cv_item);
            cv_giftcard = itemView.findViewById(R.id.cv_giftcard);
            img_giftcard_image_rv_card_fragment = itemView.findViewById(R.id.img_giftcard_image_rv_card_fragment);
            img_minus_rv_card_fragment_giftcard_cart = itemView.findViewById(R.id.img_minus_rv_card_fragment_giftcard_cart);
            img_plus_rv_card_fragment_giftcard_cart = itemView.findViewById(R.id.img_plus_rv_card_fragment_giftcard_cart);
            tv_giftcard_name_rv_item = itemView.findViewById(R.id.tv_giftcard_name_rv_item);
            tv_recipient_giftcard_cart = itemView.findViewById(R.id.tv_recipient_giftcard_cart);
            tv_delivery_giftcard_cart = itemView.findViewById(R.id.tv_delivery_giftcard_cart);
            tv_message_giftcard_cart = itemView.findViewById(R.id.tv_message_giftcard_cart);
            tv_item_quantity_change_rv_item_giftcard_cart = itemView.findViewById(R.id.tv_item_quantity_change_rv_item_giftcard_cart);
            tv_totle_giftcard_cart = itemView.findViewById(R.id.tv_totle_giftcard_cart);
            tv_qty_giftcard_cart = itemView.findViewById(R.id.tv_qty_giftcard_cart);
            ll_recipient_giftcard_cart = itemView.findViewById(R.id.ll_recipient_giftcard_cart);
            ll_delivery_giftcat_cart = itemView.findViewById(R.id.ll_delivery_giftcat_cart);
            ll_qty_giftcat_cart_label = itemView.findViewById(R.id.ll_qty_giftcat_cart_label);
            ll_quantity_count_giftcard_cart = itemView.findViewById(R.id.ll_quantity_count_giftcard_cart);
            ll_message_giftcat_cart = itemView.findViewById(R.id.ll_message_giftcat_cart);
            tv_remove_rv_shopping_card_giftcard_cart = itemView.findViewById(R.id.tv_remove_rv_shopping_card_giftcard_cart);

            // Set click listeners for all buttons
            tv_remove_rv_shopping_card_giftcard_cart.setOnClickListener(this);
            img_plus_rv_card_fragment_giftcard_cart.setOnClickListener(this);
            img_minus_rv_card_fragment_giftcard_cart.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            String giftcard_id = liShoppingCat.get(getAdapterPosition()).getId();
            String giftcard_qty = liShoppingCat.get(getAdapterPosition()).getgCQuantity();

            if (view.getId() == tv_remove_rv_shopping_card_giftcard_cart.getId()) {
                if (myCardAdapterEvent != null) {
                    myCardAdapterEvent.onCardGiftCardItemRemoved(getAdapterPosition(), giftcard_id);
                }
            }

            if (view.getId() == img_plus_rv_card_fragment_giftcard_cart.getId()) {
                if (myCardAdapterEvent != null && giftcard_qty != null && !giftcard_qty.isEmpty()) {
                    int qty = Integer.parseInt(giftcard_qty);
                    if (qty < 10) {
                        myCardAdapterEvent.onCartGiftCardItemPlus(getAdapterPosition(), giftcard_id, String.valueOf(qty));
                    } else {
                        // Show a message or handle the case where max limit is reached
                        Log.d("GiftCardCartAdapter", "Maximum quantity of 10 reached.");
                    }
                }
            }

            if (view.getId() == img_minus_rv_card_fragment_giftcard_cart.getId()) {
                if (myCardAdapterEvent != null && giftcard_qty != null && !giftcard_qty.isEmpty()) {
                    int qty = Integer.parseInt(giftcard_qty);
                    if (qty > 1) {
                        myCardAdapterEvent.onCartGiftCardItem_minus(getAdapterPosition(), giftcard_id, giftcard_qty);
                    }
                }
            }
        }
    }
}
