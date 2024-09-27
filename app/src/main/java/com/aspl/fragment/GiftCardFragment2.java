package com.aspl.fragment;

import static com.aspl.Utils.Constant.ENCODE_TOKEN_ID;
import static com.aspl.Utils.Constant.GET_GIFT_CARD_SETUP_DATA;
import static com.aspl.Utils.Constant.STOREID;
import static com.aspl.Utils.Constant.WS_BASE_URL;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.aspl.Adapter.GiftCardAdapter;
import com.aspl.Utils.Constant;
import com.aspl.Utils.DeviceInfo;
import com.aspl.Utils.Utils;
import com.aspl.mbs.MainActivity;
import com.aspl.mbs.MainActivityDup;
import com.aspl.mbs.R;
import com.aspl.mbsmodel.GCAddtoCartModel;
import com.aspl.mbsmodel.GiftCardSettingsModel;
import com.aspl.mbsmodel.UserModel;
import com.aspl.task.TaskGCAddtoCart;
import com.aspl.task.TaskGetGiftCardData;
import com.bumptech.glide.Glide;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.ArrayList;
import java.util.List;

public class GiftCardFragment2 extends Fragment implements GiftCardAdapter.OnImageClickListener, View.OnClickListener,
        TaskGetGiftCardData.TaskGetGiftCardDataEvent , TaskGCAddtoCart.TaskGCAddtoCartEvent {

    RecyclerView rv_sub_giftcard;
    ImageView iv_main_image;
    LinearLayout ll_email_gift_card , ll_email_layout , ll_text_layout, ll_message_giftcart2, ll_delivery_date_giftcart2 , ll_delivery, ll_to_email_2,ll_to_email_3, ll_QTY ;
    Button btn_email_gift_card, btn_text_gift_card, btn_physical_gift_card, btn_back_gift_card, btn_add_to_cart_gift_card ;
    View view_between_email_gift_card, view_between_btn_text_gift_card;
    EditText edt_price_giftcard, edt_qty_giftcard;
    TextView tvPrice , tv_amount_disc;
    EditText edt_from_email_giftcard, edt_select_date, edtMobile, edt_to_email_giftcard, edt_to_email_giftcard_2, edt_to_email_giftcard_3, edt_message_giftcard;

    TextView tv_50, tv_100, tv_200, tv_250, tv_500;

    int SelectedTimePosition = -1;

    private GiftCardAdapter adapter;
    private List<String> imageUrls = new ArrayList<>();
    GiftCardSettingsModel giftCardSettingsModel;
    LinearLayout ll_gitcard_not_allowed,ll_giftcard2;

    String card_type = "";
    public GiftCardFragment2() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_gift_card2, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        call_WS_getGiftCardData(view);
    }

    private void call_WS_getGiftCardData(View view) {
        // Construct the URL for the web service call to fetch gift card data
        String Url = WS_BASE_URL + GET_GIFT_CARD_SETUP_DATA + STOREID + "/" + ENCODE_TOKEN_ID;

        TaskGetGiftCardData taskGetGiftCardData = new TaskGetGiftCardData(getContext(), this, view);
        taskGetGiftCardData.execute(Url);
    }

    @Override
    public void onGiftCardDataReceived(GiftCardSettingsModel giftCardSettingsModel, View view) {
        // Check if the received GiftCardSettingsModel is not null
        if (giftCardSettingsModel != null) {
            // Store the received gift card settings model in the fragment's instance variable
            this.giftCardSettingsModel = giftCardSettingsModel;

            // Initialize the views in the fragment
            initViews(view);

            // Check if gift cards are allowed based on the settings
            if (giftCardSettingsModel.isAllowGiftCard()) {
                // Add each gift card image URL to the imageUrls list if it's not null or empty
                if (giftCardSettingsModel.getGcImage1() != null && !giftCardSettingsModel.getGcImage1().isEmpty()) {
                    imageUrls.add(giftCardSettingsModel.getGcImage1());
                }
                if (giftCardSettingsModel.getGcImage2() != null && !giftCardSettingsModel.getGcImage2().isEmpty()) {
                    imageUrls.add(giftCardSettingsModel.getGcImage2());
                }
                if (giftCardSettingsModel.getGcImage3() != null && !giftCardSettingsModel.getGcImage3().isEmpty()) {
                    imageUrls.add(giftCardSettingsModel.getGcImage3());
                }
                if (giftCardSettingsModel.getGcImage4() != null && !giftCardSettingsModel.getGcImage4().isEmpty()) {
                    imageUrls.add(giftCardSettingsModel.getGcImage4());
                }
                if (giftCardSettingsModel.getGcImage5() != null && !giftCardSettingsModel.getGcImage5().isEmpty()) {
                    imageUrls.add(giftCardSettingsModel.getGcImage5());
                }
                if (giftCardSettingsModel.getGcImage6() != null && !giftCardSettingsModel.getGcImage6().isEmpty()) {
                    imageUrls.add(giftCardSettingsModel.getGcImage6());
                }
                if (giftCardSettingsModel.getGcImage7() != null && !giftCardSettingsModel.getGcImage7().isEmpty()) {
                    imageUrls.add(giftCardSettingsModel.getGcImage7());
                }
                if (giftCardSettingsModel.getGcImage8() != null && !giftCardSettingsModel.getGcImage8().isEmpty()) {
                    imageUrls.add(giftCardSettingsModel.getGcImage8());
                }
                if (giftCardSettingsModel.getGcImage9() != null && !giftCardSettingsModel.getGcImage9().isEmpty()) {
                    imageUrls.add(giftCardSettingsModel.getGcImage9());
                }

                // Set up the RecyclerView to display the gift card images
                setupRecyclerView();

                // Set up the focus listener for the EditText fields
                setupEditTextFocusListener();
            }
        }
    }

    // Method to initialize views and set up event listeners
    private void initViews(View view) {
        // Layout views for Gift Card
        ll_giftcard2 = view.findViewById(R.id.ll_giftcard2);
        ll_giftcard2.setOnClickListener(this);
        ll_gitcard_not_allowed = view.findViewById(R.id.ll_gitcard_not_allowed);

        // Show or hide gift card layout based on the settings
        if (giftCardSettingsModel.isAllowGiftCard()) {
            ll_giftcard2.setVisibility(View.VISIBLE);
            ll_gitcard_not_allowed.setVisibility(View.GONE);
        } else {
            ll_giftcard2.setVisibility(View.GONE);
            ll_gitcard_not_allowed.setVisibility(View.VISIBLE);
        }

        // RecyclerView and ImageView initialization
        rv_sub_giftcard = view.findViewById(R.id.rv_sub_giftcard);
        iv_main_image = view.findViewById(R.id.img_item_gift_card); // Main image for the gift card

        // TextViews for preset amounts
        tv_50 = view.findViewById(R.id.tv_50);
        tv_100 = view.findViewById(R.id.tv_100);
        tv_200 = view.findViewById(R.id.tv_200);
        tv_250 = view.findViewById(R.id.tv_250);
        tv_500 = view.findViewById(R.id.tv_500);

        // Set click listeners for preset amount TextViews
        tv_50.setOnClickListener(this);
        tv_100.setOnClickListener(this);
        tv_200.setOnClickListener(this);
        tv_250.setOnClickListener(this);
        tv_500.setOnClickListener(this);

        // TextView to display selected price
        tvPrice = view.findViewById(R.id.tvPrice);
        tvPrice.setText("$"+giftCardSettingsModel.getMinGCSale());

        // Display gift card amount range
        tv_amount_disc = view.findViewById(R.id.tv_amount_disc);
        tv_amount_disc.setText("Amount range $" + giftCardSettingsModel.getMinGCSale() + " - $" + giftCardSettingsModel.getMaxGCSale());

        // EditTexts for user input
        edt_price_giftcard = view.findViewById(R.id.edt_price_giftcard);
        edt_price_giftcard.setText(" $" + giftCardSettingsModel.getMinGCSale());;
        edt_from_email_giftcard = view.findViewById(R.id.edt_from_email_giftcard);

        if (UserModel.FirstName != null && !UserModel.FirstName.trim().isEmpty()) {
            edt_from_email_giftcard.setText(UserModel.FirstName.trim());
        }

        edt_select_date = view.findViewById(R.id.edt_select_date);
        edt_qty_giftcard = view.findViewById(R.id.edt_qty_giftcard);
        edt_to_email_giftcard = view.findViewById(R.id.edt_to_email_giftcard);
        edt_to_email_giftcard_2 = view.findViewById(R.id.edt_to_email_giftcard_2);
        edt_to_email_giftcard_3 = view.findViewById(R.id.edt_to_email_giftcard_3);
        edt_message_giftcard = view.findViewById(R.id.edt_message_giftcard);

        // Layouts for additional email fields
        ll_to_email_2 = view.findViewById(R.id.ll_to_email_2);
        ll_to_email_3 = view.findViewById(R.id.ll_to_email_3);

        // Initializing sub-layouts for different delivery methods
        ll_email_layout = view.findViewById(R.id.ll_email_layout);
        ll_text_layout = view.findViewById(R.id.ll_text_layout);
        ll_delivery_date_giftcart2 = view.findViewById(R.id.ll_delivery_date_giftcart2);
        ll_message_giftcart2 = view.findViewById(R.id.ll_message_giftcart2);
        ll_delivery = view.findViewById(R.id.ll_delivery);

        // Views between delivery method buttons
        view_between_btn_text_gift_card = view.findViewById(R.id.view_between_btn_text_gift_card);
        view_between_btn_text_gift_card.setBackgroundColor(Color.parseColor(Constant.themeModel.ThemeColor));
        view_between_email_gift_card = view.findViewById(R.id.view_between_email_gift_card);
        view_between_email_gift_card.setBackgroundColor(Color.parseColor(Constant.themeModel.ThemeColor));

        // Buttons for different delivery methods (email, text, physical)
        btn_back_gift_card = view.findViewById(R.id.btn_back_gift_card);
        btn_add_to_cart_gift_card = view.findViewById(R.id.btn_add_to_cart_gift_card);
        btn_physical_gift_card = view.findViewById(R.id.btn_physical_gift_card);
        btn_email_gift_card = view.findViewById(R.id.btn_email_gift_card);
        btn_text_gift_card = view.findViewById(R.id.btn_text_gift_card);

        // Set background for email gift card button
        ll_email_gift_card = view.findViewById(R.id.ll_email_gift_card);
        GradientDrawable bgShape51 = new GradientDrawable();
        bgShape51.setShape(GradientDrawable.RECTANGLE);
        bgShape51.setStroke(2, Color.parseColor(Constant.themeModel.ThemeColor)); // Set stroke width and color
        bgShape51.setCornerRadius(8); // Optional: Set corner radius if needed
        ll_email_gift_card.setBackground(bgShape51);

        ll_QTY = view.findViewById(R.id.ll_QTY);

        // Set default physical card theme on initial load
        setphysical_card_ThemeColorBgWithRadius();

        // Show or hide delivery method buttons based on the settings
        if (!giftCardSettingsModel.isEmailGC()) {
            btn_email_gift_card.setVisibility(View.GONE);
        }
        if (!giftCardSettingsModel.isPhysicalGC()) {
            btn_physical_gift_card.setVisibility(View.GONE);
        }
        if (!giftCardSettingsModel.isTextGC()) {
            btn_text_gift_card.setVisibility(View.GONE);
        }

        // Phone number formatting logic
        edtMobile = view.findViewById(R.id.edtMobile);
        edtMobile.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    formatPhoneNumber(edtMobile);
                }else{
                    edtMobile.setError(null);
                }
            }
        });
        edtMobile.addTextChangedListener(new PhoneTextWatcher());

        // Quantity validation logic
        edt_qty_giftcard.addTextChangedListener(new QuantityTextWatcher());

        // Set click listeners for delivery method buttons
        btn_email_gift_card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setemail_ThemeColorBgWithRadius();
                card_type = "Email";
            }
        });
        btn_text_gift_card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                settext_ThemeColorBgWithRadius();
                card_type = "Text";
            }
        });
        btn_physical_gift_card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setphysical_card_ThemeColorBgWithRadius();
                card_type = "Physical";
            }
        });

        // Back button logic
        btn_back_gift_card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (Constant.SCREEN_LAYOUT == 1) {
                    MainActivity.getInstance().onBackPressed();
                } else {
                    MainActivityDup.getInstance().onBackPressed();
                }
            }
        });

        btn_add_to_cart_gift_card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(edt_price_giftcard.hasFocus()){
                    edt_price_giftcard.clearFocus();
                }else{
                    validation_of_add_to_cart_WS();
                }
            }
        });

        // Set up the calendar for date selection
        setcalender();
    }

    private void validation_of_add_to_cart_WS() {
        boolean isValid = true; // Track if all validations pass

        if (card_type.equals("Email")) {
            // Validate email fields
            if (!validateEmailField(edt_to_email_giftcard, "Invalid email")) {
                isValid = false;
            }
            if (ll_to_email_2.getVisibility() == View.VISIBLE && !validateEmailField(edt_to_email_giftcard_2, "Invalid email")) {
                isValid = false;
            }
            if (ll_to_email_3.getVisibility() == View.VISIBLE && !validateEmailField(edt_to_email_giftcard_3, "Invalid email")) {
                isValid = false;
            }

            // Validate date field
            if (!validateField(edt_select_date, "Invalid date")) {
                isValid = false;
            }

            // Validate quantity field
            if (!validateQuantityField(edt_qty_giftcard, "Invalid QTY")) {
                isValid = false;
            }

        } else if (card_type.equals("Text")) {
            // Validate mobile and message fields
            if (!validateField(edtMobile, "Invalid Phone")) {
                isValid = false;
            }
            if (!validateField(edt_message_giftcard, "Invalid message")) {
                isValid = false;
            }

            // Validate date and quantity fields
            if (!validateField(edt_select_date, "Invalid date")) {
                isValid = false;
            }
            if (!validateQuantityField(edt_qty_giftcard, "Invalid QTY")) {
                isValid = false;
            }

        } else if (card_type.equals("Physical")) {
            // Validate quantity field
            if (!validateQuantityField(edt_qty_giftcard, "Invalid QTY")) {
                isValid = false;
            }
        }

        if (isValid) {
            call_add_to_cart_for_gift_card();
        }
    }

    // Helper method to validate a generic EditText field
    private boolean validateField(EditText field, String errorMessage) {
        if (field.getText().toString().trim().isEmpty()) {
            field.setError(errorMessage);
            return false;
        }
        return true;
    }

    // Helper method to validate email fields
    private boolean validateEmailField(EditText field, String errorMessage) {
        String email = field.getText().toString().trim();
        if (email.isEmpty() || !android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            field.setError(errorMessage);
            return false;
        }
        return true;
    }

    // Helper method to validate quantity field with special condition (cannot be 0)
    private boolean validateQuantityField(EditText field, String errorMessage) {
        String quantity = field.getText().toString().trim();
        if (quantity.isEmpty() || quantity.equals("0")) {
            field.setError(errorMessage);
            return false;
        }
        return true;
    }

    @SuppressLint("LongLogTag")
    private void call_add_to_cart_for_gift_card() {

        // Check if the price field is visible and process accordingly

        String amount = (edt_price_giftcard.getVisibility() == View.VISIBLE && !edt_price_giftcard.getText().toString().trim().isEmpty())
                ? edt_price_giftcard.getText().toString().trim().replace("$", "")
                : "0";

        String phone = (ll_text_layout.getVisibility() == View.VISIBLE && !edtMobile.getText().toString().trim().isEmpty())
                ? edtMobile.getText().toString().trim().replaceAll("[^0-9]", "")
                : "0";

        String quantity = (ll_QTY.getVisibility() == View.VISIBLE && !edt_qty_giftcard.getText().toString().trim().isEmpty())
                ? edt_qty_giftcard.getText().toString().trim()
                : "0";

        String toEmail1 = (ll_email_layout.getVisibility() == View.VISIBLE && !edt_to_email_giftcard.getText().toString().trim().isEmpty())
                ? edt_to_email_giftcard.getText().toString().trim()
                : "0";

        String toEmail2 = (ll_to_email_2.getVisibility() == View.VISIBLE && !edt_to_email_giftcard_2.getText().toString().trim().isEmpty())
                ? edt_to_email_giftcard_2.getText().toString().trim()
                : "0";

        String toEmail3 = (ll_to_email_3.getVisibility() == View.VISIBLE && !edt_to_email_giftcard_3.getText().toString().trim().isEmpty())
                ? edt_to_email_giftcard_3.getText().toString().trim()
                : "0";

        String userName = (ll_email_layout.getVisibility() == View.VISIBLE && !edt_from_email_giftcard.getText().toString().trim().isEmpty())
                ? edt_from_email_giftcard.getText().toString().trim()
                : "0";

        String message = (ll_message_giftcart2.getVisibility() == View.VISIBLE && !edt_message_giftcard.getText().toString().trim().isEmpty())
                ? edt_message_giftcard.getText().toString().trim()
                : "0";

        String deliveryDate = (ll_delivery_date_giftcart2.getVisibility() == View.VISIBLE && !edt_select_date.getText().toString().trim().isEmpty())
                ? edt_select_date.getText().toString().trim()
                : "0";

        if (!deliveryDate.equals("0")) {
            try {
                SimpleDateFormat inputFormat = new SimpleDateFormat("MM/dd/yyyy", Locale.US);
                SimpleDateFormat outputFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.US);
                Date date = inputFormat.parse(deliveryDate);
                deliveryDate = outputFormat.format(date);
            } catch (ParseException e) {
                deliveryDate = "0"; // Default value if parsing fails
            }
        }

        int selectedGCDesignPosition = adapter.getSelectedPosition();
        selectedGCDesignPosition = selectedGCDesignPosition + 1; // Increment by 1

        String customer_id = (UserModel.Cust_mst_ID != null && !UserModel.Cust_mst_ID.isEmpty())
                ? UserModel.Cust_mst_ID
                : "0";

        String session_id = DeviceInfo.getDeviceId(MainActivity.getInstance()) + "0011";

        String url = "";
        url = WS_BASE_URL + Constant.GC_ADD_TO_CART + STOREID + "/" + customer_id + "/" + session_id + "/" + amount + "/" + card_type
                + "/" + quantity + "/" + toEmail1 + "/" + toEmail2 + "/" + toEmail3 + "/" + phone + "/" + userName + "/" + message + "/"
                + deliveryDate + "/" + selectedGCDesignPosition;

        Log.e("GC_Add_to_Cart", url );

//        Toast.makeText(getContext(), "Add to Cart Functionality is pending!!!", Toast.LENGTH_LONG).show();

        TaskGCAddtoCart taskGCAddtoCart = new TaskGCAddtoCart(getContext(), this);
        taskGCAddtoCart.execute(url);

    }

    @Override
    public void GCaddToCartEventResult(GCAddtoCartModel gcAddtoCartModel) {
        // Check if gcAddtoCartModel is null
        if (gcAddtoCartModel != null && gcAddtoCartModel.getResult() != null) {
            // Check if the result is "success"
            if (gcAddtoCartModel.getResult().equalsIgnoreCase("success")) {
                MainActivity.getInstance().loadCardFragment();
//                Toast.makeText(getContext(), "Added to Cart!", Toast.LENGTH_LONG).show();
            } else {
                // Handle the case where the result is not "success"
//                Toast.makeText(getContext(), "Failed to add to Cart!", Toast.LENGTH_LONG).show();
            }
        } else {
            // Handle the case where gcAddtoCartModel is null
//            Toast.makeText(getContext(), "Cart data is null!", Toast.LENGTH_LONG).show();
        }
    }

    // Helper method to format phone numbers
    private void formatPhoneNumber(EditText edtMobile) {
        String numberStr = edtMobile.getText().toString();
        if (!numberStr.isEmpty()) {
            // Remove any existing formatting characters
            numberStr = numberStr.replaceAll("[()\\-\\s]", "");
            // Reformat the phone number
            String formattedNumber = numberStr.replaceFirst("(\\d{3})(\\d{3})(\\d+)", "($1) $2-$3");
            edtMobile.setText(formattedNumber.trim());
        }
    }

    // TextWatcher class for phone number formatting
    private class PhoneTextWatcher implements TextWatcher {
        int length_before = 0;
        String temp;

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            // No action needed here
        }

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            length_before = s.length();
        }

        @Override
        public void afterTextChanged(Editable s) {
            // Phone number formatting logic
            if (length_before < edtMobile.length()) {
                if (edtMobile.length() == 1) {
                    edtMobile.setText("(" + s);
                    edtMobile.setSelection(2);
                } else if (edtMobile.length() == 5) {
                    String last = edtMobile.getText().toString().substring(edtMobile.length() - 1);
                    edtMobile.setText(temp + ") " + last);
                    edtMobile.setSelection(7);
                } else if (edtMobile.length() == 10) {
                    String last = edtMobile.getText().toString().substring(edtMobile.length() - 1);
                    edtMobile.setText(temp + "-" + last);
                    edtMobile.setSelection(11);
                } else {
                    temp = edtMobile.getText().toString();
                    edtMobile.setText(s);
                    edtMobile.setSelection(edtMobile.length());
                }
            }
        }
    }

    // TextWatcher class for quantity validation
    private class QuantityTextWatcher implements TextWatcher {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            // No action needed here
        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            // No action needed here
            edt_qty_giftcard.setError(null);  // To clear the error
        }

        @Override
        public void afterTextChanged(Editable s) {
            String input = s.toString().trim();
            if (input.isEmpty()) {
                ll_to_email_2.setVisibility(View.GONE);
                ll_to_email_3.setVisibility(View.GONE);
                return; // Exit early if input is empty
            }

            try {
                int quantity = Integer.parseInt(input);

                if (quantity == 1){
                    ll_to_email_2.setVisibility(View.GONE);
                    ll_to_email_3.setVisibility(View.GONE);
                }else if (quantity==2){
                    ll_to_email_2.setVisibility(View.VISIBLE);
                    ll_to_email_3.setVisibility(View.GONE);
                }else if (quantity >= 3){
                    ll_to_email_2.setVisibility(View.VISIBLE);
                    ll_to_email_3.setVisibility(View.VISIBLE);
                }else{
                    ll_to_email_2.setVisibility(View.GONE);
                    ll_to_email_3.setVisibility(View.GONE);
                }

                if (quantity > 10) {
                    // Show error dialog if quantity exceeds 10
                    Utils.newDesignCommonDialog(getContext(), "Invalid Quantity", "Quantity should be between 1 to 10!");
                    edt_qty_giftcard.setText("");
                    edt_qty_giftcard.requestFocus();
                }
            } catch (NumberFormatException e) {
                // Handle the exception (log it or show a message)
                Utils.newDesignCommonDialog(getContext(), "Invalid Input", "Please enter a valid number.");
                edt_qty_giftcard.setText("");
                edt_qty_giftcard.requestFocus();
                ll_to_email_2.setVisibility(View.GONE);
                ll_to_email_3.setVisibility(View.GONE);
            }
        }
    }

    // Method to initialize the calendar for date selection
    private void setcalender() {
        final Calendar myCalendar1 = Calendar.getInstance();

        // Set today's date initially in the EditText
        String myFormat = "MM/dd/yyyy"; // Date format
        SimpleDateFormat dateFormat = new SimpleDateFormat(myFormat, Locale.US);
        edt_select_date.setText(dateFormat.format(myCalendar1.getTime())); // Automatically set today's date

        // Listener for when the date is set
        DatePickerDialog.OnDateSetListener date1 = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int day) {
                myCalendar1.set(Calendar.YEAR, year);
                myCalendar1.set(Calendar.MONTH, month);
                myCalendar1.set(Calendar.DAY_OF_MONTH, day);

                // Format and set the selected date in the EditText
                edt_select_date.setText(dateFormat.format(myCalendar1.getTime()));

                // Reset selected time position
                SelectedTimePosition = -1;
            }
        };

        // Set up the EditText click listener to show the DatePickerDialog
        edt_select_date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Initialize DatePickerDialog with the current date
                DatePickerDialog datePickerDialog = new DatePickerDialog(getContext(), date1,
                        myCalendar1.get(Calendar.YEAR), myCalendar1.get(Calendar.MONTH),
                        myCalendar1.get(Calendar.DAY_OF_MONTH));

                // Restrict the date selection to today and future dates only
                datePickerDialog.getDatePicker().setMinDate(System.currentTimeMillis());
                datePickerDialog.show();
            }
        });
    }

    // Method to set up focus listener for the EditText when the user enters the price
    @SuppressLint("SetTextI18n")
    private void setupEditTextFocusListener() {
        edt_price_giftcard.setOnFocusChangeListener((v, hasFocus) -> {
            if (!hasFocus) {
                // The EditText has lost focus, perform validation and formatting
                String text = edt_price_giftcard.getText().toString().trim();

                // Check if the text is null or empty
                if (text.isEmpty()) {
                    // Set to the minimum value if empty
                    edt_price_giftcard.setText("$" + giftCardSettingsModel.getMinGCSale());
                    validateInput(text);
                } else {
                    // Remove any existing $ sign before validating
                    if (text.startsWith("$")) {
                        text = text.substring(1);
                    }
                    validateInput(text);
                }
            }
        });

    }

    // Method to validate the input and format the price
    private void validateInput(String text) {
        double value;
        boolean isValid = true;

        // Get the minimum and maximum allowed values from settings
        double minimum_value = Double.parseDouble(giftCardSettingsModel.getMinGCSale());
        double maximum_value = Double.parseDouble(giftCardSettingsModel.getMaxGCSale());

        try {
            // Remove dollar sign if present and trim whitespace
            String cleanText = text.replace("$", "").trim();

            // Convert cleaned text to a double
            value = Double.parseDouble(cleanText);

            // Check if the value is within the allowed range
            if (value < minimum_value) {
                isValid = false;
                value = minimum_value; // Set to default minimum value
            } else if (value > maximum_value) {
                isValid = false;
                value = maximum_value; // Set to default maximum value
            }
        } catch (NumberFormatException e) {
            // Handle exception if conversion fails
            isValid = false;
            value = minimum_value; // Set to default value if parsing fails
        }

        if (!isValid) {
            // Show error message if the input was invalid
            showErrorPopup();
        }

        // Format the value as a string with a dollar sign
        String formattedText = String.format("$%.2f", value);

        // Update EditText and TextView with valid value
        edt_price_giftcard.setText(formattedText.trim());
        tvPrice.setText(formattedText.trim());

        // Ensure the cursor position is set correctly
        edt_price_giftcard.setSelection(formattedText.length());
    }

    // Method to show an error popup if the input is invalid
    private void showErrorPopup() {
        String errorMessage = "The Dollar Value must be between $" + giftCardSettingsModel.getMinGCSale() +
                " and $" + giftCardSettingsModel.getMaxGCSale() + ".";
        Utils.newDesignCommonDialog(getContext(), "Gift Card Amount Error", errorMessage);
    }

    // Method to set the background and text color when the "Email Gift Card" option is selected
    private void setemail_ThemeColorBgWithRadius() {
        GradientDrawable shape = new GradientDrawable();
        shape.setShape(GradientDrawable.RECTANGLE);
        shape.setCornerRadii(new float[]{8, 8, 8, 8, 8, 8, 8, 8}); // Set rounded corners
        shape.setColor(Color.parseColor(Constant.themeModel.ThemeColor)); // Set theme color
        btn_email_gift_card.setBackgroundDrawable(shape);
        btn_email_gift_card.setTextColor(getResources().getColor(R.color.androidWhite));

        // Set colors for other buttons
        btn_text_gift_card.setTextColor(Color.parseColor(Constant.themeModel.ThemeColor));
        btn_text_gift_card.setBackgroundColor(getResources().getColor(R.color.transparent));
        btn_physical_gift_card.setTextColor(Color.parseColor(Constant.themeModel.ThemeColor));
        btn_physical_gift_card.setBackgroundColor(getResources().getColor(R.color.transparent));

        // Set visibility for related views
        view_between_email_gift_card.setVisibility(View.GONE);
        view_between_btn_text_gift_card.setVisibility(View.VISIBLE);
        ll_email_layout.setVisibility(View.VISIBLE);
        ll_delivery_date_giftcart2.setVisibility(View.VISIBLE);
        ll_delivery.setVisibility(View.VISIBLE);
        ll_message_giftcart2.setVisibility(View.VISIBLE);
        ll_text_layout.setVisibility(View.GONE);
    }

    // Method to set the background and text color when the "Text Gift Card" option is selected
    private void settext_ThemeColorBgWithRadius() {
        GradientDrawable shape = new GradientDrawable();
        shape.setShape(GradientDrawable.RECTANGLE);
        shape.setCornerRadii(new float[]{8, 8, 0, 0, 0, 0, 8, 8}); // Set rounded corners for the top only
        shape.setColor(Color.parseColor(Constant.themeModel.ThemeColor)); // Set theme color
        btn_text_gift_card.setBackgroundDrawable(shape);
        btn_text_gift_card.setTextColor(getResources().getColor(R.color.androidWhite));

        // Set colors for other buttons
        btn_email_gift_card.setTextColor(Color.parseColor(Constant.themeModel.ThemeColor));
        btn_email_gift_card.setBackgroundColor(getResources().getColor(R.color.transparent));
        btn_physical_gift_card.setTextColor(Color.parseColor(Constant.themeModel.ThemeColor));
        btn_physical_gift_card.setBackgroundColor(getResources().getColor(R.color.transparent));

        // Set visibility for related views
        view_between_btn_text_gift_card.setVisibility(View.GONE);
        view_between_email_gift_card.setVisibility(View.GONE);
        ll_delivery.setVisibility(View.VISIBLE);
        ll_delivery_date_giftcart2.setVisibility(View.VISIBLE);
        ll_message_giftcart2.setVisibility(View.VISIBLE);
        ll_text_layout.setVisibility(View.VISIBLE);
        ll_email_layout.setVisibility(View.GONE);
    }

    // Method to set the background and text color when the "Physical Gift Card" option is selected
    private void setphysical_card_ThemeColorBgWithRadius() {
        GradientDrawable shape = new GradientDrawable();
        shape.setShape(GradientDrawable.RECTANGLE);
        shape.setCornerRadii(new float[]{8, 8, 8, 8, 8, 8, 8, 8}); // Set rounded corners
        shape.setColor(Color.parseColor(Constant.themeModel.ThemeColor)); // Set theme color
        btn_physical_gift_card.setBackgroundDrawable(shape);
        btn_physical_gift_card.setTextColor(getResources().getColor(R.color.androidWhite));

        // Set colors for other buttons
        btn_text_gift_card.setTextColor(Color.parseColor(Constant.themeModel.ThemeColor));
        btn_text_gift_card.setBackgroundColor(getResources().getColor(R.color.transparent));
        btn_email_gift_card.setTextColor(Color.parseColor(Constant.themeModel.ThemeColor));
        btn_email_gift_card.setBackgroundColor(getResources().getColor(R.color.transparent));

        card_type = "Physical";

        // Set visibility for related views
        view_between_email_gift_card.setVisibility(View.VISIBLE);
        view_between_btn_text_gift_card.setVisibility(View.GONE);
        ll_delivery_date_giftcart2.setVisibility(View.GONE);
        ll_message_giftcart2.setVisibility(View.GONE);
        ll_text_layout.setVisibility(View.GONE);
        ll_email_layout.setVisibility(View.GONE);
        ll_delivery.setVisibility(View.VISIBLE);
    }

    // Method to set up the RecyclerView to show different images
    private void setupRecyclerView() {
        rv_sub_giftcard.setVisibility(View.VISIBLE);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        rv_sub_giftcard.setLayoutManager(layoutManager);
        adapter = new GiftCardAdapter(getContext(), imageUrls, this);
        rv_sub_giftcard.setAdapter(adapter);
    }

    // Method to handle image click events from the RecyclerView
    @Override
    public void onImageClick(String imageUrl) {
        // Load the selected image into the main ImageView using Glide
        Glide.with(getContext())
                .load(imageUrl)
                .into(iv_main_image);
    }

    // Method to handle onClick events for different TextViews
    @Override
    public void onClick(View view) {
        // Update price and validate based on the TextView clicked
        if (view.getId() == tv_50.getId()) {
            tvPrice.setText(tv_50.getText().toString().trim());
            validateInput(tv_50.getText().toString().trim());
        } else if (view.getId() == tv_100.getId()) {
            tvPrice.setText(tv_100.getText().toString().trim());
            validateInput(tv_100.getText().toString().trim());
        } else if (view.getId() == tv_200.getId()) {
            tvPrice.setText(tv_200.getText().toString().trim());
            validateInput(tv_200.getText().toString().trim());
        } else if (view.getId() == tv_250.getId()) {
            tvPrice.setText(tv_250.getText().toString().trim());
            validateInput(tv_250.getText().toString().trim());
        } else if (view.getId() == tv_500.getId()) {
            tvPrice.setText(tv_500.getText().toString().trim());
            validateInput(tv_500.getText().toString().trim());
        }else if (view.getId()== ll_giftcard2.getId()){
            edt_qty_giftcard.clearFocus();
            edt_price_giftcard.clearFocus();
            edt_select_date.clearFocus();
            edt_message_giftcard.clearFocus();
            edt_to_email_giftcard.clearFocus();
            edt_to_email_giftcard_2.clearFocus();
            edt_to_email_giftcard_3.clearFocus();
            edtMobile.clearFocus();
            edt_from_email_giftcard.clearFocus();
        }
    }

}
